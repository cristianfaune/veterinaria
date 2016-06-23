/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.dominio;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

/**
 *
 * @author CristianFaune
 */
public class Vacuna implements Serializable{
    private String codigo;
    private Timestamp fecha;
    private String descripcion;
    private String doctorRut;
    private int idMascota;

    public Vacuna(String codigo, Timestamp fecha, 
            String descripcion, String doctorRut, int idMascota) {
        this.codigo = codigo;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.doctorRut = doctorRut;
        this.idMascota = idMascota;
    }

    public Vacuna() {
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + Objects.hashCode(this.codigo);
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
        final Vacuna other = (Vacuna) obj;
        if (!Objects.equals(this.codigo, other.codigo)) {
            return false;
        }
        return true;
    }
    
    
    
}
