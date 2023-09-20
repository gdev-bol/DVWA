/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package snp.gob.bo.gimodel.pojo;

import snp.gob.bo.gimodel.entidad.ModModificacion;
import snp.gob.bo.gimodel.entidad.ModResolucion;
import snp.gob.bo.gimodel.entidad.RenRenovacion;
import snp.gob.bo.gimodel.entidad.RenResolucion;
import snp.gob.bo.gimodel.entidad.RenSolicitudRenovacion;

/**
 *
 * @author chanoRojas
 */
public class HistorialRenovacionModificacionPojo {
    
    
    private ModModificacion modModificacion;
    private RenSolicitudRenovacion renSolicitudRenovacion;
    private ModResolucion modResolucion;
    private RenRenovacion renRenovacion;
    private RenResolucion renResolucion;

    public ModModificacion getModModificacion() {
        return modModificacion;
    }

    public void setModModificacion(ModModificacion modModificacion) {
        this.modModificacion = modModificacion;
    }

    public ModResolucion getModResolucion() {
        return modResolucion;
    }

    public void setModResolucion(ModResolucion modResolucion) {
        this.modResolucion = modResolucion;
    }

    public RenRenovacion getRenRenovacion() {
        return renRenovacion;
    }

    public void setRenRenovacion(RenRenovacion renRenovacion) {
        this.renRenovacion = renRenovacion;
    }

    public RenResolucion getRenResolucion() {
        return renResolucion;
    }

    public void setRenResolucion(RenResolucion renResolucion) {
        this.renResolucion = renResolucion;
    }

    public RenSolicitudRenovacion getRenSolicitudRenovacion() {
        return renSolicitudRenovacion;
    }

    public void setRenSolicitudRenovacion(RenSolicitudRenovacion renSolicitudRenovacion) {
        this.renSolicitudRenovacion = renSolicitudRenovacion;
    }
    
}
