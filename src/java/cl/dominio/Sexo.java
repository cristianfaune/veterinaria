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
public class Sexo implements Serializable{
    private int idSexo;
    private String descripcion;

    public Sexo(int idSexo, String descripcion) {
        this.idSexo = idSexo;
        this.descripcion = descripcion;
    }

    public Sexo() {
    }

    public int getIdSexo() {
        return idSexo;
    }

    public void setIdSexo(int idSexo) {
        this.idSexo = idSexo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + this.idSexo;
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
        final Sexo other = (Sexo) obj;
        if (this.idSexo != other.idSexo) {
            return false;
        }
        return true;
    }
    
    
}
