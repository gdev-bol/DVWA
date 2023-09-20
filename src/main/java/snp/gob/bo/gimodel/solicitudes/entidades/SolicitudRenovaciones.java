/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.solicitudes.entidades;

import snp.gob.bo.gimodel.entidad.*;
import java.util.Date;

/**
 *
 * @author Eddy Valero
 * @version 1.0, 23/08/2016
 */
public class SolicitudRenovaciones {
    Long id;
    String tipoGenero;
    String signoRegistrado;
    Integer claseNizaRegistrado;
    Long numeroRegistro;
    String serieRegistro;
    Date fechaOtorgacionMarca;
    Integer numeroUltimaRenovacion;
    Integer numeroPenultimaRenovacion;
    String listaProductosLimitacion;
    Integer claseNizaReclasificacion;
    String estado;
    Long formularioId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipoGenero() {
        return tipoGenero;
    }

    public void setTipoGenero(String tipoGenero) {
        this.tipoGenero = tipoGenero;
    }

    public String getSignoRegistrado() {
        return signoRegistrado;
    }

    public void setSignoRegistrado(String signoRegistrado) {
        this.signoRegistrado = signoRegistrado;
    }

    public Integer getClaseNizaRegistrado() {
        return claseNizaRegistrado;
    }

    public void setClaseNizaRegistrado(Integer claseNizaRegistrado) {
        this.claseNizaRegistrado = claseNizaRegistrado;
    }

    public Long getNumeroRegistro() {
        return numeroRegistro;
    }

    public void setNumeroRegistro(Long numeroRegistro) {
        this.numeroRegistro = numeroRegistro;
    }

    public Date getFechaOtorgacionMarca() {
        return fechaOtorgacionMarca;
    }

    public void setFechaOtorgacionMarca(Date fechaOtorgacionMarca) {
        this.fechaOtorgacionMarca = fechaOtorgacionMarca;
    }

    public Integer getNumeroUltimaRenovacion() {
        return numeroUltimaRenovacion;
    }

    public void setNumeroUltimaRenovacion(Integer numeroUltimaRenovacion) {
        this.numeroUltimaRenovacion = numeroUltimaRenovacion;
    }

    public Integer getNumeroPenultimaRenovacion() {
        return numeroPenultimaRenovacion;
    }

    public void setNumeroPenultimaRenovacion(Integer numeroPenultimaRenovacion) {
        this.numeroPenultimaRenovacion = numeroPenultimaRenovacion;
    }

    public String getListaProductosLimitacion() {
        return listaProductosLimitacion;
    }

    public void setListaProductosLimitacion(String listaProductosLimitacion) {
        this.listaProductosLimitacion = listaProductosLimitacion;
    }

    public Integer getClaseNizaReclasificacion() {
        return claseNizaReclasificacion;
    }

    public void setClaseNizaReclasificacion(Integer claseNizaReclasificacion) {
        this.claseNizaReclasificacion = claseNizaReclasificacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Long getFormularioId() {
        return formularioId;
    }

    public void setFormularioId(Long formularioId) {
        this.formularioId = formularioId;
    }

    public String getSerieRegistro() {
        return serieRegistro;
    }

    public void setSerieRegistro(String serieRegistro) {
        this.serieRegistro = serieRegistro;
    }
 
}
