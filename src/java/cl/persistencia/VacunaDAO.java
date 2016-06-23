/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.persistencia;

import cl.dominio.Vacuna;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author CristianFaune
 */
public class VacunaDAO {
    Connection con;

    public VacunaDAO(Connection con) {
        this.con = con;
    }
    
    /**
     * Método para hacer el ingreso de un registro de vacuna
     * @param vacuna 
     */
    public void ingresarVacuna (Vacuna vacuna){
        
        String sql = "insert into Vacuna values (?,?,?,?,?)";
        
        try (PreparedStatement pstmt = con.prepareStatement(sql)){
            
            pstmt.setString(1, vacuna.getCodigo());
            pstmt.setString(2, vacuna.getDescripcion());
            pstmt.setTimestamp(3, vacuna.getFecha());
            pstmt.setString(4, vacuna.getDoctorRut());
            pstmt.setInt(5, vacuna.getIdMascota());
            
            pstmt.executeUpdate();
            
        } catch (SQLException e) {
            throw new RuntimeException("Error en el ingreso de vacuna",e);
        }
    }
    
    /**
     * Método para buscar las vacunas realizadas por un doctor
     * @param doctorRut
     * @return 
     */
    public ArrayList<Vacuna> buscarVacunasDoctorRut (String doctorRut){
        ArrayList<Vacuna> lista = new ArrayList<>();
        Vacuna vacuna;
        
        String sql = "select * from Vacuna where doctorRut = ?";
        
        try (PreparedStatement pstmt = con.prepareStatement(sql)){
            
            pstmt.setString(1, doctorRut);
            
            try (ResultSet rs = pstmt.executeQuery()){
                while (rs.next()) {
                    vacuna = new Vacuna();
                    
                    vacuna.setCodigo(rs.getString("codigo"));
                    vacuna.setDescripcion(rs.getString("descripcion"));
                    vacuna.setFecha(rs.getTimestamp("fecha"));
                    vacuna.setDoctorRut(rs.getString("doctorRut"));
                    vacuna.setIdMascota(rs.getInt("idMascota"));
                    
                    lista.add(vacuna);
                }  
            }
            
        } catch (SQLException e) {
            throw new RuntimeException("Error al vacunas por doctor rut",e);
        }
        
        return lista;
    }
    
    public ArrayList<Vacuna> buscarVacunasIdMascota (int idMascota){
        ArrayList<Vacuna> lista = new ArrayList<>();
        Vacuna vacuna;
        
        String sql = "select * from Vacuna where idMascota = ?";
        
        try (PreparedStatement pstmt = con.prepareStatement(sql)){
            
            pstmt.setInt(1, idMascota);
            
            try (ResultSet rs = pstmt.executeQuery()){
                while (rs.next()) {
                    vacuna = new Vacuna();
                    
                    vacuna.setCodigo(rs.getString("codigo"));
                    vacuna.setDescripcion(rs.getString("descripcion"));
                    vacuna.setFecha(rs.getTimestamp("fecha"));
                    vacuna.setDoctorRut(rs.getString("doctorRut"));
                    vacuna.setIdMascota(rs.getInt("idMascota"));
                    
                    lista.add(vacuna);
                }  
            }
            
        } catch (SQLException e) {
            throw new RuntimeException("Error al vacunas por idMascota",e);
        }
        
        return lista;
    }
    
    public void eliminarVacuna(int idMascota){
        
        String sql = "delete from Vacuna where idMascota = ?";
        
        try (PreparedStatement pstmt = con.prepareStatement(sql)){
            
            pstmt.setInt(1, idMascota);
            
            pstmt.executeUpdate();
            
        } catch (SQLException e) {
            throw new RuntimeException("Error en eliminar vacuna id mascota");
        }
    }
}
