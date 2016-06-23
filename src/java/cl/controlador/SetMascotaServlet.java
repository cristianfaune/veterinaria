package cl.controlador;

import cl.dominio.Doctor;
import cl.dto.MascotaEspecieSexoDTO;
import cl.servicio.Atencion;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
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
@WebServlet(name = "SetMascotaServlet", urlPatterns = {"/SetMascotaServlet"})
public class SetMascotaServlet extends HttpServlet {

    @Resource(mappedName = "jdbc/veterinaria")
    private DataSource ds;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();

        String idMas = request.getParameter("idMascota");

        Doctor doctor = (Doctor) session.getAttribute("doctor");

        try (Connection con = ds.getConnection()) {
            
            Atencion atencion = new Atencion(con);

            MascotaEspecieSexoDTO mascota;
            
            mascota = atencion.buscarMascotaEspecieSexoId(Integer.parseInt(idMas));
            
            session.setAttribute("mascota", mascota);
            request.getRequestDispatcher("/SalaAtencionServlet").forward(request, response);
            
        } catch (Exception e) {
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
