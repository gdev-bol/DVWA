/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package snp.gob.bo.gimodel.solicitudes.entidades;

import java.util.Date;

/**
 *
 * @author Eddy Valero
 * @version 1.0, 23/08/2016
 */
public class ClasesNiza {
    
    Long id;
    String numeroClaseNiza;
    String proteccion;
    Date fechaInicio;
    Date fechaFin;
    String notaTipoClaseNiza;
    String numeroEdicion;
    String version;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroClaseNiza() {
        return numeroClaseNiza;
    }

    public void setNumeroClaseNiza(String numeroClaseNiza) {
        this.numeroClaseNiza = numeroClaseNiza;
    }

    public String getProteccion() {
        return proteccion;
    }

    public void setProteccion(String proteccion) {
        this.proteccion = proteccion;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getNotaTipoClaseNiza() {
        return notaTipoClaseNiza;
    }

    public void setNotaTipoClaseNiza(String notaTipoClaseNiza) {
        this.notaTipoClaseNiza = notaTipoClaseNiza;
    }

    public String getNumeroEdicion() {
        return numeroEdicion;
    }

    public void setNumeroEdicion(String numeroEdicion) {
        this.numeroEdicion = numeroEdicion;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
  
}
