/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.persistencia;

import cl.dominio.Dueno;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author CristianFaune
 */
public class DuenoDAO {

    Connection con;

    public DuenoDAO(Connection con) {
        this.con = con;
    }

    /**
     * Método para ingresar a un nuevo dueño al registro
     *
     * @param dueno
     */
    public void ingresarDueno(Dueno dueno) {

        String sql = "insert into Dueño values (?,?,?,?,?,?)";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, dueno.getRut());
            pstmt.setString(2, dueno.getNombre());
            pstmt.setString(3, dueno.getApellidos());
            pstmt.setInt(4, dueno.getTelefono());
            pstmt.setString(5, dueno.getDireccion());
            pstmt.setString(6, dueno.getMail());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error en el ingreso de nuevo Dueño", e);
        }
    }

    /**
     * Método para eliminar dueño por su rut
     *
     * @param rut
     */
    public void eliminarDueno(String rut) {

        String sql = "delete from Dueño where rut = ?";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, rut);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar dueño", e);
        }
    }

    /**
     * Método que modifica los datos del dueño
     *
     * @param dueno
     */
    public void modificarDueno(Dueno dueno) {

        String sql = "update Dueño set nombre = ?, "
                + "apellidos = ?, telefono = ?, direccion = ?, "
                + "mail = ? where rut = ?";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, dueno.getNombre());
            pstmt.setString(2, dueno.getApellidos());
            pstmt.setInt(3, dueno.getTelefono());
            pstmt.setString(4, dueno.getDireccion());
            pstmt.setString(5, dueno.getMail());
            pstmt.setString(6, dueno.getRut());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error al modificar dueño", e);
        }
    }

    /**
     * Método que retorna un dueño buscando por su rut
     *
     * @param rut
     * @return
     */
    public Dueno buscarDuenoRut(String rut) {
        Dueno dueno = null;

        String sql = "select * from Dueño where rut = ?";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, rut);

            try (ResultSet rs = pstmt.executeQuery();) {

                if (rs.next()) {
                    dueno = new Dueno();

                    dueno.setRut(rs.getString("rut"));
                    dueno.setNombre(rs.getString("nombre"));
                    dueno.setApellidos(rs.getString("apellidos"));
                    dueno.setDireccion(rs.getString("direccion"));
                    dueno.setMail(rs.getString("mail"));
                    dueno.setTelefono(rs.getInt("telefono"));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error en buscar a dueño por rut", e);
        }

        return dueno;
    }

    /**
     * Método que lista a todos los dueños
     *
     * @return
     */
    public ArrayList<Dueno> listaDuenos() {
        ArrayList<Dueno> lista = new ArrayList<>();
        Dueno dueno;

        String sql = "select * from Dueño";

        try (PreparedStatement pstmt = con.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery();) {
            while (rs.next()) {
                dueno = new Dueno();

                dueno.setRut(rs.getString("rut"));
                dueno.setNombre(rs.getString("nombre"));
                dueno.setApellidos(rs.getString("apellidos"));
                dueno.setTelefono(rs.getInt("telefono"));
                dueno.setDireccion(rs.getString("direccion"));
                dueno.setMail(rs.getString("mail"));

                lista.add(dueno);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error en la búsqueda de todos los dueños");
        }

        return lista;
    }

}
