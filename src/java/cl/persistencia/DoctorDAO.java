/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.persistencia;

import cl.dominio.Doctor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author CristianFaune
 */
public class DoctorDAO {

    Connection con;

    public DoctorDAO(Connection con) {
        this.con = con;
    }

    /**
     * Método que retorna a un objeto Doctor buscándolo por su rut, sino es null
     *
     * @param doctorRut
     * @return
     */
    public Doctor buscarDoctorRut(String doctorRut) {
        Doctor doctor = null;

        String sql = "select * from Doctor where rut = ?";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, doctorRut);

            try (ResultSet rs = pstmt.executeQuery()) {

                if (rs.next()) {
                    doctor = new Doctor();

                    doctor.setRut(rs.getString("rut"));
                    doctor.setNombre(rs.getString("nombre"));
                    doctor.setApellidos(rs.getString("apellidos"));
                    doctor.setTelefono(rs.getInt("telefono"));
                    doctor.setDireccion(rs.getString("direccion"));
                    doctor.setMail(rs.getString("mail"));
                    doctor.setPassword(rs.getString("password"));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error en la búsqueda de doctor por rut");
        }

        return doctor;
    }
}
