/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.persistencia;

import cl.dominio.Mascota;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author CristianFaune
 */
public class MascotaDAO {

    Connection con;

    public MascotaDAO(Connection con) {
        this.con = con;
    }

    /**
     * Método que ingresa una nueva mascota al registro
     *
     * @param mascota
     */
    public void ingresarMascota(Mascota mascota) {

        String sql = "insert into Mascota (nombre,fechaNacimiento,esterilizado,"
                + "descripcion,raza,idSexo,idEspecie,duenoRut) "
                + "values (?,?,?,?,?,?,?,?)";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, mascota.getNombre());
            pstmt.setDate(2, mascota.getFechaNacimiento());
            pstmt.setString(3, mascota.getEsterilizado());
            pstmt.setString(4, mascota.getDescripcion());
            pstmt.setString(5, mascota.getRaza());
            pstmt.setInt(6, mascota.getIdSexo());
            pstmt.setInt(7, mascota.getIdEspecie());
            pstmt.setString(8, mascota.getDuenoRut());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error en el ingreso de nueva mascota", e);
        }
    }

    /**
     * Método para eliminar todas las mascotas de un dueño
     *
     * @param rut
     */
    public void eliminarMascotaRutDueno(String rut) {

        String sql = "delete from Mascota where duenoRut = ?";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, rut);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar las mascota por rut de dueño", e);
        }
    }

    /**
     * Método para eliminar a una sola mascota por su ID
     *
     * @param idMascota
     */
    public void eliminarMascotaId(int idMascota) {

        String sql = "delete from Mascota where idMascota = ?";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setInt(1, idMascota);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar la mascota por su ID", e);
        }
    }

    /**
     * Método que busca toda la tabla de mascota por su ID
     *
     * @param idMascota
     * @return
     */
    public Mascota buscarMascotaId(int idMascota) {
        Mascota mascota = null;

        String sql = "select * from Mascota where idMascota = ?";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setInt(1, idMascota);

            try (ResultSet rs = pstmt.executeQuery();) {

                if (rs.next()) {
                    mascota = new Mascota();

                    mascota.setIdMascota(rs.getInt("idMascota"));
                    mascota.setNombre(rs.getString("nombre"));
                    mascota.setFechaNacimiento(rs.getDate("fechaNacimiento"));
                    mascota.setEsterilizado(rs.getString("esterilizado"));
                    mascota.setDescripcion(rs.getString("descripcion"));
                    mascota.setIdSexo(rs.getInt("idSexo"));
                    mascota.setIdEspecie(rs.getInt("idEspecie"));
                    mascota.setDuenoRut(rs.getString("duenoRut"));
                    mascota.setRaza(rs.getString("raza"));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar mascota por ID", e);
        }

        return mascota;
    }

    /**
     * Método que lista a todas las mascotas y todos sus datos
     *
     * @return
     */
    public ArrayList<Mascota> buscarTodasLasMascotas() {
        ArrayList<Mascota> lista = new ArrayList<>();
        Mascota mascota;

        String sql = "select * from Mascota";

        try (PreparedStatement pstmt = con.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery();) {
            while (rs.next()) {
                mascota = new Mascota();

                mascota.setIdMascota(rs.getInt("idMascota"));
                mascota.setNombre(rs.getString("nombre"));
                mascota.setFechaNacimiento(rs.getDate("fechaNacimiento"));
                mascota.setEsterilizado(rs.getString("esterilizado"));
                mascota.setDescripcion(rs.getString("descripcion"));
                mascota.setIdSexo(rs.getInt("idSexo"));
                mascota.setIdEspecie(rs.getInt("idEspecie"));
                mascota.setDuenoRut(rs.getString("duenoRut"));
                mascota.setRaza(rs.getString("raza"));

                lista.add(mascota);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar todas las mascotas", e);
        }

        return lista;
    }

    public ArrayList<Mascota> buscarMascotaDuenoRut(String duenoRut) {
        ArrayList<Mascota> lista = new ArrayList<>();
        Mascota mascota = null;

        String sql = "select * from Mascota where duenoRut = ?";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, duenoRut);

            try (ResultSet rs = pstmt.executeQuery();) {

                while (rs.next()) {
                    mascota = new Mascota();

                    mascota.setIdMascota(rs.getInt("idMascota"));
                    mascota.setNombre(rs.getString("nombre"));
                    mascota.setFechaNacimiento(rs.getDate("fechaNacimiento"));
                    mascota.setEsterilizado(rs.getString("esterilizado"));
                    mascota.setDescripcion(rs.getString("descripcion"));
                    mascota.setIdSexo(rs.getInt("idSexo"));
                    mascota.setIdEspecie(rs.getInt("idEspecie"));
                    mascota.setDuenoRut(rs.getString("duenoRut"));
                    mascota.setRaza(rs.getString("raza"));

                    lista.add(mascota);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar mascota por ID", e);
        }

        return lista;
    }
}
