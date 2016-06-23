package cl.controlador;

import cl.dominio.Doctor;
import cl.dominio.Mascota;
import cl.dominio.Vacuna;
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
@WebServlet(name = "VacunacionServlet", urlPatterns = {"/VacunacionServlet"})
public class VacunacionServlet extends HttpServlet {

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

                request.setAttribute("lstVacunas", atencion.buscarServicioVacunas());
                request.getRequestDispatcher("/Vacunacion.jsp").forward(request, response);

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
        String codigo = request.getParameter("codigo");
        String descripcion = request.getParameter("descripcion");
        Map<String, String> mapMensaje = new HashMap<>();
        Vacuna vacuna = new Vacuna();
        MascotaEspecieSexoDTO idMascota;
        idMascota = (MascotaEspecieSexoDTO) session.getAttribute("mascota");

        if (doctor == null) {
            request.getRequestDispatcher("/ValidarIngreso").forward(request, response);
        } else {

            try (Connection con = ds.getConnection()) {

                Atencion atencion = new Atencion(con);

                if (descripcion.equals("0")) {
                    mapMensaje.put("errorDescripcion", "** Debe seleccionar una vacuna");
                } else {
                    vacuna.setDescripcion(descripcion);
                }

                if (codigo.isEmpty() || codigo == null) {
                    mapMensaje.put("errorCodigo", "** Ingrese código");
                } else if (codigo.length()>10) {
                    mapMensaje.put("errorCodigo", "** Ingrese máx. 10 caractéres");
                }else{
                    vacuna.setCodigo(codigo);
                }

                vacuna.setDoctorRut(doctor.getRut());

                vacuna.setIdMascota(idMascota.getMascota().getIdMascota());

                vacuna.setFecha(new Timestamp(System.currentTimeMillis()));
                

                if (!mapMensaje.isEmpty()) {
                    request.setAttribute("mapMensaje", mapMensaje);
                    request.setAttribute("lstVacunas", atencion.buscarServicioVacunas());
                    request.getRequestDispatcher("/Vacunacion.jsp").forward(request, response);
                } else {
                    atencion.vacunar(vacuna);
                    mapMensaje.put("exito", "El paciente "+idMascota.getMascota().getNombre()+" fue vacunado exitosamente");
                    request.setAttribute("mapMensaje", mapMensaje);
                    request.setAttribute("lstVacunas", atencion.buscarServicioVacunas());
                    request.getRequestDispatcher("/Vacunacion.jsp").forward(request, response);
                }

            } catch (SQLException e) {
                throw new RuntimeException("Error conexión", e);
            }
        }

    }

}
