/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.persistencia;

import cl.dominio.Procedimiento;
import cl.dominio.Servicio;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author CristianFaune
 */
public class ServicioDAO {

    Connection con;

    public ServicioDAO(Connection con) {
        this.con = con;
    }

    public ArrayList<Servicio> buscarServicioVacunas() {
        ArrayList<Servicio> lista = new ArrayList<>();
        Servicio servicio;

        String sql = "select * from Servicio where tipo = 1";

        try (PreparedStatement pstmt = con.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery();) {
            while (rs.next()) {
                servicio = new Servicio();

                servicio.setIdServicio(rs.getInt("idServicio"));
                servicio.setDescripcion(rs.getString("descripcion"));
                servicio.setTipo(rs.getInt("tipo"));
                servicio.setPrecio(rs.getDouble("precio"));

                lista.add(servicio);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error en la busqueda de servicios tipo vacuna", e);
        }

        return lista;
    }

    public ArrayList<Servicio> buscarServicioProcedimientos() {
        ArrayList<Servicio> lista = new ArrayList<>();
        Servicio servicio;

        String sql = "select * from Servicio where tipo = 2";

        try (PreparedStatement pstmt = con.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery();) {
            while (rs.next()) {
                servicio = new Servicio();

                servicio.setIdServicio(rs.getInt("idServicio"));
                servicio.setDescripcion(rs.getString("descripcion"));
                servicio.setTipo(rs.getInt("tipo"));
                servicio.setPrecio(rs.getDouble("precio"));

                lista.add(servicio);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error en la busqueda de servicios tipo vacuna", e);
        }

        return lista;
    }
}
