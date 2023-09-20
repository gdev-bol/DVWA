/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.entidad;

import java.util.Date;

/**
 * Entidad responsable de la Tabla regional
 *
 * @author Chano Rojas
 * @see DosificacionMapper
 * @version 1.0, 11/08/2016
 */
public class RenSeguimiento {
  Long idseguimiento;
  Long idrenovaciontramite;
  Long idusuario;
  Long idlogtrans;
  Long sm;
  Long numero_publicacion;
  Long numero_registro;
  String serie_registro;
  Date fecha_recepcion;
  Date fecha_fin;
  int plazo_real;
  String observacion;
  Boolean editable;
  String estado;

    public Long getIdseguimiento() {
        return idseguimiento;
    }

    public void setIdseguimiento(Long idseguimiento) {
        this.idseguimiento = idseguimiento;
    }

    public Long getIdrenovaciontramite() {
        return idrenovaciontramite;
    }

    public void setIdrenovaciontramite(Long idrenovaciontramite) {
        this.idrenovaciontramite = idrenovaciontramite;
    }

    public Long getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Long idusuario) {
        this.idusuario = idusuario;
    }

    public Long getIdlogtrans() {
        return idlogtrans;
    }

    public void setIdlogtrans(Long idlogtrans) {
        this.idlogtrans = idlogtrans;
    }

    public Long getSm() {
        return sm;
    }

    public void setSm(Long sm) {
        this.sm = sm;
    }

    public Long getNumero_publicacion() {
        return numero_publicacion;
    }

    public void setNumero_publicacion(Long numero_publicacion) {
        this.numero_publicacion = numero_publicacion;
    }

    public Long getNumero_registro() {
        return numero_registro;
    }

    public void setNumero_registro(Long numero_registro) {
        this.numero_registro = numero_registro;
    }

    public String getSerie_registro() {
        return serie_registro;
    }

    public void setSerie_registro(String serie_registro) {
        this.serie_registro = serie_registro;
    }

    public Date getFecha_recepcion() {
        return fecha_recepcion;
    }

    public void setFecha_recepcion(Date fecha_recepcion) {
        this.fecha_recepcion = fecha_recepcion;
    }

    public Date getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(Date fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public int getPlazo_real() {
        return plazo_real;
    }

    public void setPlazo_real(int plazo_real) {
        this.plazo_real = plazo_real;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Boolean isEditable() {
        return editable;
    }

    public void setEditable(Boolean editable) {
        this.editable = editable;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
  
  
  
  
  
}