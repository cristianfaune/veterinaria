package cl.controlador;

import cl.dominio.Doctor;
import cl.dominio.Mascota;
import cl.servicio.Atencion;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
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
@WebServlet(name = "EliminarClienteServlet", urlPatterns = {"/EliminarClienteServlet"})
public class EliminarClienteServlet extends HttpServlet {

    @Resource(mappedName = "jdbc/veterinaria")
    private DataSource ds;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String rutDueno = request.getParameter("rutDueno");

        Doctor doctor = (Doctor) session.getAttribute("doctor");

        if (doctor == null) {
            request.getRequestDispatcher("/ValidarIngreso").forward(request, response);
        } else {

            try (Connection con = ds.getConnection()) {

                Atencion atencion = new Atencion(con);
                
                ArrayList<Mascota> mascotas = atencion.buscarMascotaDuenoRut(rutDueno);
                
                for (Mascota mascota : mascotas) {
                    if (atencion.buscarVacunasIdMascota(mascota.getIdMascota()).size()>0) {
                        atencion.eliminarVacuna(mascota.getIdMascota());
                    }else if (atencion.buscarProcedimientosIdMascota(mascota.getIdMascota()).size()>0) {
                        atencion.eliminarProcedimiento(mascota.getIdMascota());
                    }
                }

                atencion.eliminarMascota(rutDueno);
                atencion.eliminarDueno(rutDueno);

                request.setAttribute("lstClientes", atencion.listaDuenos());
                request.getRequestDispatcher("Menu.jsp").forward(request, response);

            } catch (SQLException e) {
                throw new RuntimeException("Error de conexión", e);
            }
        }

    }

}
