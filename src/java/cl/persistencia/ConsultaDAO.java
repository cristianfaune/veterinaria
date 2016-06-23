/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.persistencia;

import cl.dominio.Dueno;
import cl.dominio.Especie;
import cl.dominio.Mascota;
import cl.dominio.Procedimiento;
import cl.dominio.Servicio;
import cl.dominio.Sexo;
import cl.dominio.Vacuna;
import cl.dto.MascotaEspecieSexoDTO;
import cl.dto.MascotaProcedimientoDTO;
import cl.dto.MascotaVacunaDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author CristianFaune
 */
public class ConsultaDAO {

    Connection con;

    public ConsultaDAO(Connection con) {
        this.con = con;
    }

    /**
     * Método que retorna objetos mascota, dueño, especie y raza en una lista
     * para la consulta de todas las mascotas por dueño
     *
     * @param duenoRut
     * @return
     */
    public ArrayList<MascotaEspecieSexoDTO> buscarMascotasRutDueno(String duenoRut) {
        ArrayList<MascotaEspecieSexoDTO> lista = new ArrayList<>();
        Mascota mascota;
        Especie especie;
        Sexo sexo;

        String sql = "SELECT * from Mascota m "
                + "join Especie e USING (idEspecie) "
                + "join Sexo s using (idSexo) where m.duenoRut = ?";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, duenoRut);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    mascota = new Mascota();

                    mascota.setIdMascota(rs.getInt("m.idMascota"));
                    mascota.setNombre(rs.getString("m.nombre"));
                    mascota.setFechaNacimiento(rs.getDate("m.fechaNacimiento"));
                    mascota.setEsterilizado(rs.getString("m.esterilizado"));
                    mascota.setDescripcion(rs.getString("m.descripcion"));
                    mascota.setIdSexo(rs.getInt("m.idSexo"));
                    mascota.setIdEspecie(rs.getInt("m.idEspecie"));
                    mascota.setDuenoRut(rs.getString("m.duenoRut"));
                    mascota.setRaza(rs.getString("m.raza"));

                    especie = new Especie();

                    especie.setIdEspecie(rs.getInt("e.idEspecie"));
                    especie.setDescripcion(rs.getString("e.descripcion"));

                    sexo = new Sexo();

                    sexo.setIdSexo(rs.getInt("s.idSexo"));
                    sexo.setDescripcion(rs.getString("s.descripcion"));

                    lista.add(new MascotaEspecieSexoDTO(mascota, especie, sexo));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error en la consulta mascotas por rut dueño", e);
        }

        return lista;
    }

    /**
     * Método que busca una mascota con sus relaciones a las tablas especie y
     * sexo por ID
     *
     * @param idMascota
     * @return
     */
    public MascotaEspecieSexoDTO buscarMascotaId(int idMascota) {
        MascotaEspecieSexoDTO datosMascota = null;
        Mascota mascota;
        Especie especie;
        Sexo sexo;

        String sql = "SELECT * from Mascota m "
                + "join Especie e USING (idEspecie) "
                + "join Sexo s using (idSexo) where m.idMascota = ?";

        try (PreparedStatement pstmt = con.prepareStatement(sql);) {

            pstmt.setInt(1, idMascota);

            try (ResultSet rs = pstmt.executeQuery();) {
                if (rs.next()) {
                    mascota = new Mascota();

                    mascota.setIdMascota(rs.getInt("m.idMascota"));
                    mascota.setNombre(rs.getString("m.nombre"));
                    mascota.setFechaNacimiento(rs.getDate("m.fechaNacimiento"));
                    mascota.setEsterilizado(rs.getString("m.esterilizado"));
                    mascota.setDescripcion(rs.getString("m.descripcion"));
                    mascota.setRaza(rs.getString("m.raza"));
                    mascota.setIdSexo(rs.getInt("m.idSexo"));
                    mascota.setIdEspecie(rs.getInt("m.idEspecie"));
                    mascota.setDuenoRut(rs.getString("m.duenoRut"));

                    especie = new Especie();

                    especie.setIdEspecie(rs.getInt("m.idEspecie"));
                    especie.setDescripcion(rs.getString("e.descripcion"));

                    sexo = new Sexo();

                    sexo.setIdSexo(rs.getInt("m.idSexo"));
                    sexo.setDescripcion(rs.getString("s.descripcion"));

                    datosMascota = new MascotaEspecieSexoDTO(mascota, especie, sexo);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error en la consulta mascotas por ID Mascota", e);
        }

        return datosMascota;
    }

    /**
     * Método que permite la búsqueda del registro de vacunas por mascota
     *
     * @param idMascota
     * @return
     */
    public ArrayList<MascotaVacunaDTO> buscarVacunasMascota(int idMascota) {
        ArrayList<MascotaVacunaDTO> lista = new ArrayList<>();
        Mascota mascota;
        Vacuna vacuna;

        String sql = "SELECT * from Vacuna v JOIN Mascota m "
                + "USING (idMascota) where m.idMascota = ?";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setInt(1, idMascota);

            try (ResultSet rs = pstmt.executeQuery()) {
                mascota = new Mascota();

                mascota.setIdMascota(rs.getInt("m.idMascota"));
                mascota.setNombre(rs.getString("m.nombre"));
                mascota.setFechaNacimiento(rs.getDate("m.fechaNacimiento"));
                mascota.setEsterilizado(rs.getString("m.esterilizado"));
                mascota.setDescripcion(rs.getString("m.descripcion"));
                mascota.setRaza(rs.getString("m.raza"));
                mascota.setIdSexo(rs.getInt("m.idSexo"));
                mascota.setIdEspecie(rs.getInt("m.idEspecie"));
                mascota.setDuenoRut(rs.getString("m.duenoRut"));

                vacuna = new Vacuna();

                vacuna.setCodigo(rs.getString("v.codigo"));
                vacuna.setDescripcion(rs.getString("v.descripcion"));
                vacuna.setFecha(rs.getTimestamp("v.fecha"));
                vacuna.setDoctorRut(rs.getString("v.doctorRut"));
                vacuna.setIdMascota(rs.getInt("m.idMascota"));

                lista.add(new MascotaVacunaDTO(mascota, vacuna));

            }

        } catch (SQLException e) {
            throw new RuntimeException("Error en la búsqueda de vacunas por mascota", e);
        }

        return lista;
    }

    /**
     * Método para la búsqueda de los registros de procedimientos por mascota
     *
     * @param idMascota
     * @return
     */
    public ArrayList<MascotaProcedimientoDTO> buscarProcedimientosMascota(int idMascota) {
        ArrayList<MascotaProcedimientoDTO> lista = new ArrayList<>();
        Mascota mascota;
        Procedimiento procedimiento;

        String sql = "SELECT * from Procedimiento p JOIN Mascota m "
                + "USING (idMascota) where idMascota = ?";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setInt(1, idMascota);

            try (ResultSet rs = pstmt.executeQuery()) {
                mascota = new Mascota();

                mascota.setIdMascota(rs.getInt("idMascota"));
                mascota.setNombre(rs.getString("m.nombre"));
                mascota.setFechaNacimiento(rs.getDate("fechaNacimiento"));
                mascota.setEsterilizado(rs.getString("esterilizado"));
                mascota.setDescripcion(rs.getString("m.descripcion"));
                mascota.setIdSexo(rs.getInt("m.idSexo"));
                mascota.setIdEspecie(rs.getInt("m.idEspecie"));
                mascota.setDuenoRut(rs.getString("duenoRut"));
                mascota.setRaza(rs.getString("m.raza"));

                procedimiento = new Procedimiento();

                procedimiento.setIdProcedimiento(rs.getInt("idProcedimiento"));
                procedimiento.setDescripcion(rs.getString("p.descripcion"));
                procedimiento.setFecha(rs.getTimestamp("p.fecha"));
                procedimiento.setObservaciones(rs.getString("observaciones"));
                procedimiento.setDoctorRut(rs.getString("p.doctorRut"));
                procedimiento.setIdMascota(rs.getInt("p.idMascota"));

                lista.add(new MascotaProcedimientoDTO(mascota, procedimiento));

            }

        } catch (SQLException e) {
            throw new RuntimeException("Error en la búsqueda de procedimientos por mascota", e);
        }

        return lista;
    }
    
}
