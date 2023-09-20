/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.entidad;

import java.util.Date;

/**
 *
 * @author Eddy Valero
 * @version 1.0, 26/08/2016
 */
public class ClaseNiza {

    Long idClaseNiza;
    Long idLogTrans;
    Long numeroClaseNiza;
    String proteccion;
    Date fechaInicio;
    Date fechaFin;
    String tipoNiza;
    String notaTipoClaseNiza;
    String numeroEdicion;
    String version;
    String estado;

    public Long getIdClaseNiza() {
        return idClaseNiza;
    }

    public void setIdClaseNiza(Long idClaseNiza) {
        this.idClaseNiza = idClaseNiza;
    }

    public Long getIdLogTrans() {
        return idLogTrans;
    }

    public void setIdLogTrans(Long idLogTrans) {
        this.idLogTrans = idLogTrans;
    }

    public Long getNumeroClaseNiza() {
        return numeroClaseNiza;
    }

    public void setNumeroClaseNiza(Long numeroClaseNiza) {
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

    public String getTipoNiza() {
        return tipoNiza;
    }

    public void setTipoNiza(String tipoNiza) {
        this.tipoNiza = tipoNiza;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    
    
}
