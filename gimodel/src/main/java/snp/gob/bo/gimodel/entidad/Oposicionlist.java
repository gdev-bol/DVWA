/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package snp.gob.bo.gimodel.entidad;

import java.util.Date;

/**
 *
 * @author desarrollo
 */
public class Oposicionlist {
    String actividad;
    Date fecha_x;
    String observacion;
    Integer dias;

    public String getActividad() {
        return actividad;
    }

    public void setActividad(String actividad) {
        this.actividad = actividad;
    }

    public Date getFecha_x() {
        return fecha_x;
    }

    public void setFecha_x(Date fecha_x) {
        this.fecha_x = fecha_x;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Integer getDias() {
        return dias;
    }

    public void setDias(Integer dias) {
        this.dias = dias;
    }
   
}
