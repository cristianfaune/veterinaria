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
@WebServlet(name = "ModificarDatosServlet", urlPatterns = {"/ModificarDatosServlet"})
public class ModificarDatosServlet extends HttpServlet {

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

        HttpSession session = request.getSession();

        request.setCharacterEncoding("UTF-8");

        String rut = request.getParameter("rut");
        String nombre = request.getParameter("nombre");
        String apellidos = request.getParameter("apellidos");
        String direccion = request.getParameter("direccion");
        String telefono = request.getParameter("telefono");
        String mail = request.getParameter("mail");
        int num;

        Map<String, String> mapMensaje = new HashMap<>();

        Dueno dueno = new Dueno();

        try (Connection con = ds.getConnection()) {

            Atencion atencion = new Atencion(con);

            if (rut.isEmpty()) {
                mapMensaje.put("errorRut", "** Debe ingresar su Rut");
            } else {

                dueno.setRut(rut);

            }

            if (nombre.isEmpty()) {
                mapMensaje.put("errorNombre", "** Debe ingresar su nombre");
            } else if (!nombre.isEmpty()) {
                for (int i = 0; i < nombre.length(); i++) {
                    if (Character.isDigit(nombre.charAt(i))) {
                        mapMensaje.put("errorNombre", "** Debe ingresar solo letras");
                        break;
                    } else {
                        dueno.setNombre(nombre);
                    }
                }
            }

            if (apellidos.isEmpty()) {
                mapMensaje.put("errorApellidos", "** Debe ingresar sus apellidos");
            } else if (!apellidos.isEmpty()) {
                for (int i = 0; i < apellidos.length(); i++) {
                    if (Character.isDigit(apellidos.charAt(i))) {
                        mapMensaje.put("errorApellidos", "** Debe ingresar solo letras");
                        break;
                    } else {
                        dueno.setApellidos(apellidos);
                    }
                }
            }

            if (direccion.isEmpty()) {
                mapMensaje.put("errorDireccion", "** Debe ingresar su dirección");
            } else {
                dueno.setDireccion(direccion);
            }

            if (telefono.isEmpty()) {
                mapMensaje.put("errorTelefono", "** Debe ingresar un teléfono");
            } else if (!telefono.isEmpty()) {
                try {
                    num = Integer.parseInt(telefono);
                    dueno.setTelefono(num);

                } catch (NumberFormatException e) {
                    mapMensaje.put("errorTelefono", "** Debe ingresar solo números");
                }
            }

            dueno.setMail(mail);
            

            if (!mapMensaje.isEmpty()) {
                request.setAttribute("mapMensaje", mapMensaje);
                request.getRequestDispatcher("ModificarDatos.jsp").forward(request, response);
            } else {
                atencion.actualizarDatos(dueno);
                mapMensaje.put("exito", "Datos actualizados con éxito");
                request.setAttribute("mapMensaje", mapMensaje);
                request.getRequestDispatcher("/ModificarDatos.jsp").forward(request, response);
            }

        } catch (SQLException e) {

            throw new RuntimeException("Error en la concexión registro dueño", e);
        }
    }

}
