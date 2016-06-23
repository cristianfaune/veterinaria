package cl.controlador;

import cl.dominio.Doctor;
import cl.dominio.Dueno;
import cl.servicio.Atencion;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
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
@WebServlet(name = "BuscarModificarServlet", urlPatterns = {"/BuscarModificarServlet"})
public class BuscarModificarServlet extends HttpServlet {

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
            request.getRequestDispatcher("ModificarDatos.jsp").forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String rut = request.getParameter("rutBuscar");
        Map<String, String> mapMensajeRut = new HashMap<>();

        HttpSession session = request.getSession();

        Doctor doctor = (Doctor) session.getAttribute("doctor");

        if (doctor == null) {
            request.getRequestDispatcher("/ValidarIngreso").forward(request, response);
        }

        try (Connection con = ds.getConnection()) {

            Atencion atencion = new Atencion(con);

            Dueno dueno = atencion.buscarDuenoRut(rut);

            if (rut.isEmpty() || rut == null) {
                mapMensajeRut.put("errorRut", "Debe ingresar un Rut");
            } else if (dueno == null) {
                mapMensajeRut.put("errorRut", "El dueño no está registrado");
            }

            if (!mapMensajeRut.isEmpty()) {
                request.setAttribute("mapMensajeRut", mapMensajeRut);
                request.getRequestDispatcher("/ModificarDatos.jsp").forward(request, response);
            } else {
                request.setAttribute("dueno", dueno);
                request.getRequestDispatcher("/ModificarDatos.jsp").forward(request, response);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error en la conexión", e);
        }
    }
}
