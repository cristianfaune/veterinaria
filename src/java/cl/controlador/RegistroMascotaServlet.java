package cl.controlador;

import cl.dominio.Doctor;
import cl.dominio.Dueno;
import cl.dominio.Mascota;
import cl.servicio.Atencion;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
@WebServlet(name = "RegistroMascotaServlet", urlPatterns = {"/RegistroMascotaServlet"})
public class RegistroMascotaServlet extends HttpServlet {

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

                request.setAttribute("listaEspecies", atencion.buscarEspecies());
                request.getRequestDispatcher("/RegistroMascota.jsp").forward(request, response);

            } catch (SQLException e) {
                throw new RuntimeException("Error de conexión Registro Mascotas", e);

            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        session.getAttribute("dueno");
        Map<String, String> mapMensaje = new HashMap<>();

        String nombre = request.getParameter("nombreMascota");
        String rut = request.getParameter("rutDueno");
        String raza = request.getParameter("raza");
        String especies = request.getParameter("especies");
        String fecha = request.getParameter("fechaNacimiento");
        String esterilizado = request.getParameter("esterilizado");
        String sexo = request.getParameter("sexo");
        String descripcion = request.getParameter("descripcion");

        Mascota mascota = new Mascota();

        try (Connection con = ds.getConnection()) {

            Atencion atencion = new Atencion(con);

            Dueno dueno = atencion.buscarDuenoRut(rut);

            request.setAttribute("listaEspecies", atencion.buscarEspecies());

            if (rut.isEmpty()) {
                mapMensaje.put("errorRut", "** Debe ingresar Rut");
            } else if (dueno == null) {
                mapMensaje.put("errorRut", "El dueño no está registrado");
            } else {
                mascota.setDuenoRut(rut);
            }

            if (nombre.isEmpty()) {
                mapMensaje.put("errorNombre", "** Debe ingresar su nombre");
            } else if (!nombre.isEmpty()) {
                for (int i = 0; i < nombre.length(); i++) {
                    if (Character.isDigit(nombre.charAt(i))) {
                        mapMensaje.put("errorNombre", "** Debe ingresar solo letras");
                        break;
                    } else {
                        mascota.setNombre(nombre);
                    }
                }
            }

            if (raza.isEmpty()) {
                mapMensaje.put("errorRaza", "** Debe ingresar la raza");
            } else if (!raza.isEmpty()) {
                for (int i = 0; i < raza.length(); i++) {
                    if (Character.isDigit(raza.charAt(i))) {
                        mapMensaje.put("errorRaza", "** Debe ingresar solo letras");
                        break;
                    } else {
                        mascota.setRaza(raza);
                    }
                }
            }

            if (especies.isEmpty() || especies.equals("0")) {
                mapMensaje.put("errorEspecies", "** Debe seleccionar una especie");
            } else {
                mascota.setIdEspecie(Integer.parseInt(especies));
            }

            if (fecha.isEmpty()) {
                mapMensaje.put("errorFecha", "** Debe seleccionar una fecha");
            } else {
                try {
                    SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
                    java.util.Date date = sdf1.parse(fecha);
                    java.sql.Date fechaDate = new java.sql.Date(date.getTime());

                    mascota.setFechaNacimiento(fechaDate);

                } catch (ParseException e) {
                    throw new RuntimeException("error", e);
                }

            }

            if (esterilizado.equals("1")) {
                mascota.setEsterilizado("Si");
            } else {
                mascota.setEsterilizado("No");
            }

            if (sexo.equals("1")) {
                mascota.setIdSexo(Integer.parseInt(sexo));
            } else {
                mascota.setIdSexo(Integer.parseInt(sexo));
            }

            mascota.setDescripcion(descripcion);

            if (!mapMensaje.isEmpty()) {
                request.setAttribute("mapMensaje", mapMensaje);
                request.getRequestDispatcher("RegistroMascota.jsp").forward(request, response);
            } else {
                atencion.ingresarMascota(mascota);
                mapMensaje.put("exito", "Su mascota fue ingresada con éxito");
                //session.setAttribute("mascota", mascota);
                request.setAttribute("mapMensaje", mapMensaje);
                request.getRequestDispatcher("RegistroMascota.jsp").forward(request, response);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error en la conexión de Registro Mascota Servlet", e);
        }

    }

}
