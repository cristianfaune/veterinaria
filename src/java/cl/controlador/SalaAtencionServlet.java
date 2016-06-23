package cl.controlador;

import cl.dominio.Doctor;
import cl.dominio.Mascota;
import cl.dominio.Procedimiento;
import cl.dominio.Vacuna;
import cl.dto.MascotaEspecieSexoDTO;
import cl.servicio.Atencion;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Header;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.PdfPTable;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 *
 * @author CristianFaune
 */
@WebServlet(name = "SalaAtencionServlet", urlPatterns = {"/SalaAtencionServlet"})
public class SalaAtencionServlet extends HttpServlet {

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

                MascotaEspecieSexoDTO idMascota, mascota;

                idMascota = (MascotaEspecieSexoDTO) session.getAttribute("mascota");

                mascota = atencion.buscarMascotaEspecieSexoId((idMascota.getMascota().getIdMascota()));

                request.setAttribute("vacunas", atencion.buscarVacunasIdMascota((idMascota.getMascota().getIdMascota())));
                request.setAttribute("procedimientos", atencion.buscarProcedimientosIdMascota((idMascota.getMascota().getIdMascota())));
                request.setAttribute("datos", idMascota);
                request.getRequestDispatcher("SalaAtencion.jsp").forward(request, response);

            } catch (SQLException e) {
                throw new RuntimeException("Error en búsqueda mascota especie sexo", e);
            }
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/pdf");

        HttpSession session = request.getSession();

        Doctor doctor = (Doctor) session.getAttribute("doctor");

        if (doctor == null) {
            request.getRequestDispatcher("/ValidarIngreso").forward(request, response);

        } else {

            try (Connection con = ds.getConnection()) {

                Atencion atencion = new Atencion(con);

                MascotaEspecieSexoDTO idMascota, mascota;

                idMascota = (MascotaEspecieSexoDTO) session.getAttribute("mascota");

                mascota = atencion.buscarMascotaEspecieSexoId((idMascota.getMascota().getIdMascota()));

                ArrayList<Vacuna> vacunas = atencion.buscarVacunasIdMascota((idMascota.getMascota().getIdMascota()));
                ArrayList<Procedimiento> procedimientos = atencion.buscarProcedimientosIdMascota((idMascota.getMascota().getIdMascota()));
                Timestamp time = new Timestamp(System.currentTimeMillis());
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");

                Document document = new Document();

                try {

                    PdfWriter.getInstance(document, response.getOutputStream());
                    PdfWriter.getInstance(document, new FileOutputStream("FichaMedica.pdf"));

                    document.open();

                    document.add(new Paragraph("Ficha Médica", FontFactory.getFont("arial", 20, Font.BOLD)));
                    document.add(new Paragraph("Datos Mascota"));
                    document.add(new Paragraph("Fecha Emisión: " + sdf.format(time), FontFactory.getFont("arial", 9, Font.ITALIC)));
                    document.add(new Paragraph(" "));
                    
                    PdfPTable table = new PdfPTable(3);
                    table.addCell(new Paragraph("Paciente", FontFactory.getFont("arial", 11, Font.BOLDITALIC)));
                    table.addCell(new Paragraph("Especie", FontFactory.getFont("arial", 11, Font.BOLDITALIC)));
                    table.addCell(new Paragraph("Fecha Nacimiento", FontFactory.getFont("arial", 11, Font.BOLDITALIC)));
                    table.addCell(new Paragraph(mascota.getMascota().getNombre(), FontFactory.getFont("arial", 18, Font.BOLDITALIC)));
                    table.addCell(mascota.getEspecie().getDescripcion());
                    table.addCell(sdf2.format(mascota.getMascota().getFechaNacimiento()));
                    table.addCell(" ");
                    table.addCell(" ");
                    table.addCell(" ");
                    table.addCell(new Paragraph("Sexo", FontFactory.getFont("arial", 11, Font.BOLDITALIC)));
                    table.addCell(new Paragraph("Raza", FontFactory.getFont("arial", 11, Font.BOLDITALIC)));
                    table.addCell(new Paragraph("Esterilizado", FontFactory.getFont("arial", 11, Font.BOLDITALIC)));
                    table.addCell(mascota.getSexo().getDescripcion());
                    table.addCell(mascota.getMascota().getRaza());
                    table.addCell(mascota.getMascota().getEsterilizado());

                    PdfPTable table2 = new PdfPTable(4);
                    table2.addCell(new Paragraph("Código", FontFactory.getFont("arial", 11, Font.BOLDITALIC)));
                    table2.addCell(new Paragraph("Descripción", FontFactory.getFont("arial", 11, Font.BOLDITALIC)));
                    table2.addCell(new Paragraph("Fecha", FontFactory.getFont("arial", 11, Font.BOLDITALIC)));
                    table2.addCell(new Paragraph("Rut Doctor", FontFactory.getFont("arial", 11, Font.BOLDITALIC)));
                    for (Vacuna v : vacunas) {
                        table2.addCell(v.getCodigo());
                        table2.addCell(v.getDescripcion());
                        table2.addCell(sdf2.format(v.getFecha()));
                        table2.addCell(v.getDoctorRut());
                    }

                    PdfPTable table3 = new PdfPTable(5);
                    table3.addCell(new Paragraph("Código", FontFactory.getFont("arial", 11, Font.BOLDITALIC)));
                    table3.addCell(new Paragraph("Descripción", FontFactory.getFont("arial", 11, Font.BOLDITALIC)));
                    table3.addCell(new Paragraph("Observaciones", FontFactory.getFont("arial", 11, Font.BOLDITALIC)));
                    table3.addCell(new Paragraph("Fecha", FontFactory.getFont("arial", 11, Font.BOLDITALIC)));
                    table3.addCell(new Paragraph("Rut Doctor", FontFactory.getFont("arial", 11, Font.BOLDITALIC)));
                    for (Procedimiento p : procedimientos) {
                        table3.addCell(String.valueOf(p.getIdProcedimiento()));
                        table3.addCell(p.getDescripcion());
                        table3.addCell(p.getObservaciones());
                        table3.addCell(sdf2.format(p.getFecha()));
                        table3.addCell(p.getDoctorRut());
                    }

                    document.add(table);
                    document.add(new Paragraph(" "));
                    document.add(new Paragraph("Historial Vacunas", FontFactory.getFont("arial", 15, Font.BOLD)));
                    document.add(new Paragraph(" "));
                    document.add(table2);
                    document.add(new Paragraph(" "));
                    document.add(new Paragraph("Historial Procedimientos", FontFactory.getFont("arial", 15, Font.BOLD)));
                    document.add(new Paragraph(" "));
                    document.add(table3);

                    document.close();
                } catch (DocumentException e) {
                    e.printStackTrace();
                }

                request.setAttribute("vacunas", vacunas);
                request.setAttribute("procedimientos", procedimientos);
                request.setAttribute("datos", idMascota);
                request.getRequestDispatcher("SalaAtencion.jsp").forward(request, response);

            } catch (SQLException e) {
                throw new RuntimeException("Error en búsqueda mascota especie sexo", e);
            }
        }

    }

}
