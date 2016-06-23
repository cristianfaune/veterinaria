package cl.controlador;

import cl.dominio.Doctor;
import cl.dominio.Procedimiento;
import cl.dto.MascotaEspecieSexoDTO;
import cl.servicio.Atencion;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

/**
 *
 * @author CristianFaune
 */
@WebServlet(name = "ProcedimientoServlet", urlPatterns = {"/ProcedimientoServlet"})
public class ProcedimientoServlet extends HttpServlet {

    @Resource(mappedName = "jdbc/veterinaria")
    private DataSource ds;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        Doctor doctor = (Doctor) session.getAttribute("doctor");

        if (doctor == null) {
            request.getRequestDispatcher("/ValidarIngreso").forward(request, response);
        } else {

            try (Connection con = ds.getConnection()) {

                Atencion atencion = new Atencion(con);

                request.setAttribute("lstProcedimientos", atencion.buscarServicioProcedimiento());
                request.getRequestDispatcher("/Procedimiento.jsp").forward(request, response);

            } catch (SQLException e) {
                throw new RuntimeException("Error conexión", e);
            }
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();

        Doctor doctor = (Doctor) session.getAttribute("doctor");
        MascotaEspecieSexoDTO idMascota;
        idMascota = (MascotaEspecieSexoDTO) session.getAttribute("mascota");
        String descripcion = request.getParameter("descripcion");
        String observaciones = request.getParameter("observaciones");
        Map<String, String> mapMensaje = new HashMap<>();
        Procedimiento procedimiento = new Procedimiento();

        if (doctor == null) {
            request.getRequestDispatcher("/ValidarIngreso").forward(request, response);
        } else {

            try (Connection con = ds.getConnection()) {

                Atencion atencion = new Atencion(con);

                if (descripcion.equals("0")) {
                    mapMensaje.put("errorDescripcion", "** Debe seleccionar una vacuna");
                } else {
                    procedimiento.setDescripcion(descripcion);
                }

                procedimiento.setObservaciones(observaciones);

                procedimiento.setDoctorRut(doctor.getRut());
                procedimiento.setFecha(new Timestamp(System.currentTimeMillis()));
                procedimiento.setIdMascota(idMascota.getMascota().getIdMascota());

                if (!mapMensaje.isEmpty()) {
                    request.setAttribute("mapMensaje", mapMensaje);
                    request.setAttribute("lstProcedimientos", atencion.buscarServicioProcedimiento());
                    request.getRequestDispatcher("/Procedimiento.jsp").forward(request, response);
                } else {
                    atencion.realizarProcedimiento(procedimiento);
                    mapMensaje.put("exito", "Se realizó exitosamente el procedimiento a "
                            + idMascota.getMascota().getNombre());
                    request.setAttribute("lstProcedimientos", atencion.buscarServicioProcedimiento());
                    request.setAttribute("mapMensaje", mapMensaje);
                    request.getRequestDispatcher("/Procedimiento.jsp").forward(request, response);
                }

            } catch (SQLException e) {
                throw new RuntimeException("Error conexión", e);
            }
        }

    }

}
