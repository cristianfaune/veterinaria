/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.servicio;

import cl.dominio.Doctor;
import cl.dominio.Dueno;
import cl.dominio.Especie;
import cl.dominio.Mascota;
import cl.dominio.Procedimiento;
import cl.dominio.Servicio;
import cl.dominio.Vacuna;
import cl.dto.MascotaEspecieSexoDTO;
import cl.dto.MascotaVacunaDTO;
import cl.persistencia.ConsultaDAO;
import cl.persistencia.DoctorDAO;
import cl.persistencia.DuenoDAO;
import cl.persistencia.EspecieDAO;
import cl.persistencia.MascotaDAO;
import cl.persistencia.ProcedimientoDAO;
import cl.persistencia.ServicioDAO;
import cl.persistencia.VacunaDAO;
import java.sql.Connection;
import java.util.ArrayList;

/**
 *
 * @author CristianFaune
 */
public class Atencion {

    DoctorDAO doctorDAO;
    ConsultaDAO consultaDAO;
    DuenoDAO duenoDAO;
    MascotaDAO mascotaDAO;
    ProcedimientoDAO procedimientoDAO;
    VacunaDAO vacunaDAO;
    EspecieDAO especieDAO;
    ServicioDAO servicioDAO;

    public Atencion(Connection con) {
        doctorDAO = new DoctorDAO(con);
        consultaDAO = new ConsultaDAO(con);
        duenoDAO = new DuenoDAO(con);
        mascotaDAO = new MascotaDAO(con);
        procedimientoDAO = new ProcedimientoDAO(con);
        vacunaDAO = new VacunaDAO(con);
        especieDAO = new EspecieDAO(con);
        servicioDAO = new ServicioDAO(con);

    }

    public Doctor buscarDoctorRut(String rut) {
        return doctorDAO.buscarDoctorRut(rut);
    }

    public ArrayList<Especie> buscarEspecies() {
        return especieDAO.buscarEspecies();
    }

    public Dueno buscarDuenoRut(String rut) {
        return duenoDAO.buscarDuenoRut(rut);
    }

    public ArrayList<MascotaEspecieSexoDTO> buscarMascotaRut(String duenoRut) {
        return consultaDAO.buscarMascotasRutDueno(duenoRut);
    }

    public ArrayList<Mascota> buscarMascotaDuenoRut(String duenoRut) {
        return mascotaDAO.buscarMascotaDuenoRut(duenoRut);
    }

    public void ingresarDueno(Dueno dueno) {
        duenoDAO.ingresarDueno(dueno);
    }

    public void ingresarMascota(Mascota mascota) {
        mascotaDAO.ingresarMascota(mascota);
    }

    public void actualizarDatos(Dueno dueno) {
        duenoDAO.modificarDueno(dueno);
    }

    public Mascota buscarMascotaId(int idMascota) {
        return mascotaDAO.buscarMascotaId(idMascota);
    }

    public ArrayList<MascotaVacunaDTO> buscarMascotaVacuna(int idMascota) {
        return consultaDAO.buscarVacunasMascota(idMascota);
    }

    public MascotaEspecieSexoDTO buscarMascotaEspecieSexoId(int IdMascota) {
        return consultaDAO.buscarMascotaId(IdMascota);
    }

    public ArrayList<Vacuna> buscarVacunasIdMascota(int idMascota) {
        return vacunaDAO.buscarVacunasIdMascota(idMascota);
    }

    public ArrayList<Procedimiento> buscarProcedimientosIdMascota(int idMascota) {
        return procedimientoDAO.buscarProcedimientosIdMascota(idMascota);
    }

    public ArrayList<Servicio> buscarServicioVacunas() {
        return servicioDAO.buscarServicioVacunas();
    }

    public ArrayList<Servicio> buscarServicioProcedimiento() {
        return servicioDAO.buscarServicioProcedimientos();
    }

    public void vacunar(Vacuna vacuna) {
        vacunaDAO.ingresarVacuna(vacuna);
    }

    public void realizarProcedimiento(Procedimiento procedimiento) {
        procedimientoDAO.ingresarProcedimiento(procedimiento);
    }
    
    public ArrayList<Dueno> listaDuenos(){
        return duenoDAO.listaDuenos();
    }
    
    public void eliminarDueno(String rut){
        duenoDAO.eliminarDueno(rut);
    }
    
    public void eliminarMascota(String rut){
        mascotaDAO.eliminarMascotaRutDueno(rut);
    }
    
    public void eliminarVacuna(int idMascota){
        vacunaDAO.eliminarVacuna(idMascota);
    }
    
    public void eliminarProcedimiento(int idMascota){
        procedimientoDAO.eliminarProcedimiento(idMascota);
    }
}
