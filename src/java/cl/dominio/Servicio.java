/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.dominio;

import java.io.Serializable;

/**
 *
 * @author CristianFaune
 */
public class Servicio implements Serializable{
    private int idServicio;
    private int tipo;
    private String descripcion;
    private double precio;

    public Servicio(int idServicio, int tipo, String descripcion, double precio) {
        this.idServicio = idServicio;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.precio = precio;
    }

    public Servicio() {
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public int getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(int idServicio) {
        this.idServicio = idServicio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + this.idServicio;
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
        final Servicio other = (Servicio) obj;
        if (this.idServicio != other.idServicio) {
            return false;
        }
        return true;
    }

    
    
}
