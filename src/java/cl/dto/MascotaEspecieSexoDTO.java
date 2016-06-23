/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.dto;

import cl.dominio.Especie;
import cl.dominio.Mascota;
import cl.dominio.Sexo;
import java.io.Serializable;

/**
 *
 * @author CristianFaune
 */
public class MascotaEspecieSexoDTO implements Serializable{
    private Mascota mascota;
    private Especie especie;
    private Sexo sexo;

    public MascotaEspecieSexoDTO(Mascota mascota, 
            Especie especie, Sexo sexo) {
        this.mascota = mascota;
        this.especie = especie;
        this.sexo = sexo;
    }

    public MascotaEspecieSexoDTO() {
    }

    public Mascota getMascota() {
        return mascota;
    }

    public void setMascota(Mascota mascota) {
        this.mascota = mascota;
    }

    public Especie getEspecie() {
        return especie;
    }

    public void setEspecie(Especie especie) {
        this.especie = especie;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }
    
    
}
