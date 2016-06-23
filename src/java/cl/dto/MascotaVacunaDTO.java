/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.dto;

import cl.dominio.Mascota;
import cl.dominio.Vacuna;
import java.io.Serializable;

/**
 *
 * @author CristianFaune
 */
public class MascotaVacunaDTO implements Serializable{
    
    private Mascota mascota;
    private Vacuna vacuna;

    public MascotaVacunaDTO(Mascota mascota, Vacuna vacuna) {
        this.mascota = mascota;
        this.vacuna = vacuna;
    }

    public MascotaVacunaDTO() {
    }

    public Mascota getMascota() {
        return mascota;
    }

    public void setMascota(Mascota mascota) {
        this.mascota = mascota;
    }

    public Vacuna getVacuna() {
        return vacuna;
    }

    public void setVacuna(Vacuna vacuna) {
        this.vacuna = vacuna;
    }
    
    
}
