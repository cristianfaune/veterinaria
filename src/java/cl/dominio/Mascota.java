/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.dominio;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author CristianFaune
 */
public class Mascota implements Serializable{

    private int idMascota;
    private String nombre;
    private Date fechaNacimiento;
    private String esterilizado;
    private String descripcion;
    private int idSexo;
    private int idEspecie;
    private String duenoRut;
    private String raza;

    public Mascota(int idMascota, String nombre, Date fechaNacimiento,
            String esterilizado, String descripcion, int idSexo,
            int idEspecie, String duenoRut, String raza) {
        this.idMascota = idMascota;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.esterilizado = esterilizado;
        this.descripcion = descripcion;
        this.idSexo = idSexo;
        this.idEspecie = idEspecie;
        this.duenoRut = duenoRut;
        this.raza = raza;
    }

    public Mascota() {
    }

    public int getIdMascota() {
        return idMascota;
    }

    public void setIdMascota(int idMascota) {
        this.idMascota = idMascota;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getEsterilizado() {
        return esterilizado;
    }

    public void setEsterilizado(String esterilizado) {
        this.esterilizado = esterilizado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getIdSexo() {
        return idSexo;
    }

    public void setIdSexo(int idSexo) {
        this.idSexo = idSexo;
    }

    public int getIdEspecie() {
        return idEspecie;
    }

    public void setIdEspecie(int idEspecie) {
        this.idEspecie = idEspecie;
    }

    public String getDuenoRut() {
        return duenoRut;
    }

    public void setDuenoRut(String duenoRut) {
        this.duenoRut = duenoRut;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + this.idMascota;
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
        final Mascota other = (Mascota) obj;
        if (this.idMascota != other.idMascota) {
            return false;
        }
        return true;
    }

}
