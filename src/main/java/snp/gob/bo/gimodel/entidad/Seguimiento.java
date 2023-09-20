/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.entidad;

import java.util.Date;

/**
 * Entidad responsable de la Tabla Seguimiento
 *
 * @author Eddy Valero
 * @see SigSeguimientoMapper
 * @version 1.0, 08/09/2016
 */
public class Seguimiento {

    Long idSeguimiento;
    Long id;
    Long idUsuario;
    Long idLogTrans;
    Long sm;
    Long numeroPublicacion;
    Long numeroRegistro;
    String serieRegistro;
    String etapa;
    Date fechaRecepcion;
    Date fechaFin;
    int plazoReal;
    Long totalTiempo;
    String observacion;
    Boolean editable;
    String estado;
    int plazo_limite;
    int dia_pasivo;
    public Long getIdSeguimiento() {
        return idSeguimiento;
    }

    public void setIdSeguimiento(Long idSeguimiento) {
        this.idSeguimiento = idSeguimiento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Long getIdLogTrans() {
        return idLogTrans;
    }

    public void setIdLogTrans(Long idLogTrans) {
        this.idLogTrans = idLogTrans;
    }

    public Long getSm() {
        return sm;
    }

    public void setSm(Long sm) {
        this.sm = sm;
    }

    public Long getNumeroPublicacion() {
        return numeroPublicacion;
    }

    public void setNumeroPublicacion(Long numeroPublicacion) {
        this.numeroPublicacion = numeroPublicacion;
    }

    public Long getNumeroRegistro() {
        return numeroRegistro;
    }

    public void setNumeroRegistro(Long numeroRegistro) {
        this.numeroRegistro = numeroRegistro;
    }

    public String getSerieRegistro() {
        return serieRegistro;
    }

    public void setSerieRegistro(String serieRegistro) {
        this.serieRegistro = serieRegistro;
    }

    public String getEtapa() {
        return etapa;
    }

    public void setEtapa(String etapa) {
        this.etapa = etapa;
    }
    
    public Date getFechaRecepcion() {
        return fechaRecepcion;
    }

    public void setFechaRecepcion(Date fechaRecepcion) {
        this.fechaRecepcion = fechaRecepcion;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public int getPlazoReal() {
        return plazoReal;
    }

    public void setPlazoReal(int plazoReal) {
        this.plazoReal = plazoReal;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Boolean getEditable() {
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

    public Long getTotalTiempo() {
        return totalTiempo;
    }

    public void setTotalTiempo(Long totalTiempo) {
        this.totalTiempo = totalTiempo;
    }

    public Integer getPlazo_limite() {
        return plazo_limite;
    }

    public void setPlazo_limite(Integer plazo_limite) {
        this.plazo_limite = plazo_limite;
    }

    public int getDia_pasivo() {
        return dia_pasivo;
    }

    public void setDia_pasivo(int dia_pasivo) {
        this.dia_pasivo = dia_pasivo;
    }

}
