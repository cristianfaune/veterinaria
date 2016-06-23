/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.persistencia;

import cl.dominio.Procedimiento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author CristianFaune
 */
public class ProcedimientoDAO {

    Connection con;

    public ProcedimientoDAO(Connection con) {
        this.con = con;
    }

    /**
     * Método que recibe un objeto de tipo Procedimiento y lo ingresa a la tabla
     *
     * @param procedimiento
     */
    public void ingresarProcedimiento(Procedimiento procedimiento) {

        String sql = "insert into Procedimiento (fecha, descripcion, observaciones, "
                + "doctorRut, idMascota) values (?,?,?,?,?)";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setTimestamp(1, procedimiento.getFecha());
            pstmt.setString(2, procedimiento.getDescripcion());
            pstmt.setString(3, procedimiento.getObservaciones());
            pstmt.setString(4, procedimiento.getDoctorRut());
            pstmt.setInt(5, procedimiento.getIdMascota());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error al ingresar procedimiento", e);
        }
    }

    public ArrayList<Procedimiento> buscarTodosLosProcedimientos() {
        ArrayList<Procedimiento> lista = new ArrayList<>();
        Procedimiento procedimiento;

        String sql = "select * from Procedimiento";

        try (PreparedStatement pstmt = con.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery();) {

            while (rs.next()) {
                procedimiento = new Procedimiento();

                procedimiento.setIdProcedimiento(rs.getInt("idProcedimiento"));
                procedimiento.setFecha(rs.getTimestamp("fecha"));
                procedimiento.setDescripcion(rs.getString("descripcion"));
                procedimiento.setObservaciones(rs.getString("observaciones"));
                procedimiento.setDoctorRut(rs.getString("doctorRut"));
                procedimiento.setIdMascota(rs.getInt("idMascota"));

                lista.add(procedimiento);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error en la búsqueda de todos los procedimientos", e);
        }

        return lista;
    }

    /**
     * Método para retornar una lista de procedimientos de un doctor
     * @param doctorRut
     * @return 
     */
    public ArrayList<Procedimiento> buscarProcedimientosDoctorRut(String doctorRut) {
        ArrayList<Procedimiento> lista = new ArrayList<>();
        Procedimiento procedimiento;

        String sql = "select * from Procedimiento where doctorRut = ?";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, doctorRut);

            try (ResultSet rs = pstmt.executeQuery();) {

                while (rs.next()) {
                    procedimiento = new Procedimiento();

                    procedimiento.setFecha(rs.getTimestamp("fecha"));
                    procedimiento.setDescripcion(rs.getString("descripcion"));
                    procedimiento.setObservaciones(rs.getString("observaciones"));
                    procedimiento.setDoctorRut(rs.getString("doctorRut"));
                    procedimiento.setIdMascota(rs.getInt("idMascota"));

                    lista.add(procedimiento);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error en la búsqueda de procedimientos por rut de doctor", e);
        }

        return lista;
    }
    
    public ArrayList<Procedimiento> buscarProcedimientosIdMascota(int idMascota) {
        ArrayList<Procedimiento> lista = new ArrayList<>();
        Procedimiento procedimiento;

        String sql = "select * from Procedimiento where idMascota = ?";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setInt(1, idMascota);

            try (ResultSet rs = pstmt.executeQuery();) {

                while (rs.next()) {
                    procedimiento = new Procedimiento();

                    procedimiento.setIdProcedimiento(rs.getInt("idProcedimiento"));
                    procedimiento.setFecha(rs.getTimestamp("fecha"));
                    procedimiento.setDescripcion(rs.getString("descripcion"));
                    procedimiento.setObservaciones(rs.getString("observaciones"));
                    procedimiento.setDoctorRut(rs.getString("doctorRut"));
                    procedimiento.setIdMascota(rs.getInt("idMascota"));

                    lista.add(procedimiento);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error en la búsqueda de procedimientos por id mascota", e);
        }

        return lista;
    }
    
    public void eliminarProcedimiento(int idMascota){
        
        String sql = "delete from Procedimiento where idMascota = ?";
        
        try (PreparedStatement pstmt = con.prepareStatement(sql)){
            
            pstmt.setInt(1, idMascota);
            
            pstmt.executeUpdate();
            
        } catch (SQLException e) {
            throw new RuntimeException("Error en eliminar procedimiento id mascota");
        }
    }
}
