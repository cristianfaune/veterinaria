/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.dto;

import cl.dominio.Mascota;
import cl.dominio.Procedimiento;

/**
 *
 * @author CristianFaune
 */
public class MascotaProcedimientoDTO {
    private Mascota mascota;
    private Procedimiento procedimiento;

    public MascotaProcedimientoDTO(Mascota mascota, Procedimiento procedimiento) {
        this.mascota = mascota;
        this.procedimiento = procedimiento;
    }

    public MascotaProcedimientoDTO() {
    }

    public Mascota getMascota() {
        return mascota;
    }

    public void setMascota(Mascota mascota) {
        this.mascota = mascota;
    }

    public Procedimiento getProcedimiento() {
        return procedimiento;
    }

    public void setProcedimiento(Procedimiento procedimiento) {
        this.procedimiento = procedimiento;
    }
    
    
}
