/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.dominio;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 *
 * @author CristianFaune
 */
public class Procedimiento implements Serializable{

    private int idProcedimiento;
    private String descripcion;
    private String observaciones;
    private Timestamp fecha;
    private String doctorRut;
    private int idMascota;

    public Procedimiento(int idProcedimiento, String descripcion, String observaciones,
            Timestamp fecha,String doctorRut, int idMascota) {
        this.idProcedimiento = idProcedimiento;
        this.descripcion = descripcion;
        this.observaciones = observaciones;
        this.fecha = fecha;
        this.doctorRut = doctorRut;
        this.idMascota = idMascota;
    }

    public Procedimiento() {
    }

    public int getIdProcedimiento() {
        return idProcedimiento;
    }

    public void setIdProcedimiento(int idProcedimiento) {
        this.idProcedimiento = idProcedimiento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }

    public String getDoctorRut() {
        return doctorRut;
    }

    public void setDoctorRut(String doctorRut) {
        this.doctorRut = doctorRut;
    }

    public int getIdMascota() {
        return idMascota;
    }

    public void setIdMascota(int idMascota) {
        this.idMascota = idMascota;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + this.idProcedimiento;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Procedimiento other = (Procedimiento) obj;
        if (this.idProcedimiento != other.idProcedimiento) {
            return false;
        }
        return true;
    }

}
