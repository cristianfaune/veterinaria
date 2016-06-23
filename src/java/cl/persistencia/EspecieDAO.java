/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.persistencia;

import cl.dominio.Especie;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author CristianFaune
 */
public class EspecieDAO {
    Connection con;

    public EspecieDAO(Connection con) {
        this.con = con;
    }
    
    /**
     * Método para buscar todas las especies
     * @return 
     */
    public ArrayList<Especie> buscarEspecies (){
        ArrayList<Especie> lista = new ArrayList<>();
        Especie especie;
        
        String sql = "select * from Especie";
        
        try (PreparedStatement pstmt = con.prepareStatement(sql);
            
            ResultSet rs = pstmt.executeQuery();){
            
            while (rs.next()) {
                especie = new Especie();
                
                especie.setIdEspecie(rs.getInt("idEspecie"));
                especie.setDescripcion(rs.getString("descripcion"));
                
                lista.add(especie);
            }
            
        } catch (SQLException e) {
            throw new RuntimeException("Error en la búsqueda de especies",e);
        }
        return lista;
    }
}
