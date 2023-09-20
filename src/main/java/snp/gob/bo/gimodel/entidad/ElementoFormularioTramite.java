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
 * @version 1.0, 03/09/2016
 */


public class ElementoFormularioTramite {
    
    Long idElementoFormularioTramite;
    Long idFormularioTramite;
    Long idLogTrans;
    String pestania;
    int seccion;
    int fila;
    String tipoElemento;
    String nombreElemento;
    int orden;
    String ordenLiteral;
    int idpadre;
    Date fechaInicio;
    Date fechaFin;
    String respuesta;
    String opcionRespuesta;
    String estado;

    public Long getIdElementoFormularioTramite() {
        return idElementoFormularioTramite;
    }

    public void setIdElementoFormularioTramite(Long idElementoFormularioTramite) {
        this.idElementoFormularioTramite = idElementoFormularioTramite;
    }

    public Long getIdFormularioTramite() {
        return idFormularioTramite;
    }

    public void setIdFormularioTramite(Long idFormularioTramite) {
        this.idFormularioTramite = idFormularioTramite;
    }
    

    public Long getIdLogTrans() {
        return idLogTrans;
    }

    public void setIdLogTrans(Long idLogTrans) {
        this.idLogTrans = idLogTrans;
    }

    public String getPestania() {
        return pestania;
    }

    public void setPestania(String pestania) {
        this.pestania = pestania;
    }

    public int getSeccion() {
        return seccion;
    }

    public void setSeccion(int seccion) {
        this.seccion = seccion;
    }

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public String getTipoElemento() {
        return tipoElemento;
    }

    public void setTipoElemento(String tipoElemento) {
        this.tipoElemento = tipoElemento;
    }

    public String getNombreElemento() {
        return nombreElemento;
    }

    public void setNombreElemento(String nombreElemento) {
        this.nombreElemento = nombreElemento;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public String getOrdenLiteral() {
        return ordenLiteral;
    }

    public void setOrdenLiteral(String ordenLiteral) {
        this.ordenLiteral = ordenLiteral;
    }
    
    public int getIdpadre() {
        return idpadre;
    }

    public void setIdpadre(int idpadre) {
        this.idpadre = idpadre;
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
    
    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public String getOpcionRespuesta() {
        return opcionRespuesta;
    }

    public void setOpcionRespuesta(String opcionRespuesta) {
        this.opcionRespuesta = opcionRespuesta;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    
    
    
}
