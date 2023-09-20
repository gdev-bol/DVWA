/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.entidad;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Entidad responsable de la Tabla recibo
 *
 * @author Chano Rojas
 * @version 1.0, 13/10/2016
 */
public class Recibo implements Serializable, Cloneable{
  Long idRecibo;
  Long idTasa;
  Long idLogTrans;
  Long numeroRecibo;
  String serieRecibo;
  Date fechaEmisionRecibo;
  BigDecimal monto;
  String literalMonto;
  String concepto;
  String datoRecibo;
  int cantidad;
  String estadoRecibo;
  String origen;
  String estado;
  String solicitante;
  String apoderado;
  Long numeroTitulo;
  String serieTitulo;
  String tipoTitulo;
  String tipoTramiteIngresado;
  String numeroTramiteIngresado;
  String gestionTramiteIngresado;
  String extensionTramiteIngresado;
  Long claseIngresado;
  String descIngresado;
  String apoderadoIngresado;
  Long depIngresado;
  Long tituloIngresado;
  String serieTituloIngresado;
  String tipoTituloIngresado;
  String tramite;
  Long numeroTramite;
  Long gestionTramite;
  int claseTramite;
  String extensionTramite;
  String descripcionMarca;
  Long expediente;
  String tipoTramite;
  String sigla;
  String observacion;
  Long idRegional;
  Long idUsuario;

  public Long getIdRecibo() {
        return idRecibo;
    }

  
  
    public void setIdRecibo(Long idRecibo) {
        this.idRecibo = idRecibo;
    }

    public Long getIdTasa() {
        return idTasa;
    }

    public void setIdTasa(Long idTasa) {
        this.idTasa = idTasa;
    }

    public Long getIdLogTrans() {
        return idLogTrans;
    }

    public void setIdLogTrans(Long idLogTrans) {
        this.idLogTrans = idLogTrans;
    }

    public Long getNumeroRecibo() {
        return numeroRecibo;
    }

    public void setNumeroRecibo(Long numeroRecibo) {
        this.numeroRecibo = numeroRecibo;
    }

    public String getSerieRecibo() {
        return serieRecibo;
    }

    public void setSerieRecibo(String serieRecibo) {
        this.serieRecibo = serieRecibo;
    }

    public Date getFechaEmisionRecibo() {
        return fechaEmisionRecibo;
    }

    public void setFechaEmisionRecibo(Date fechaEmisionRecibo) {
        this.fechaEmisionRecibo = fechaEmisionRecibo;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public String getLiteralMonto() {
        return literalMonto;
    }

    public void setLiteralMonto(String literalMonto) {
        this.literalMonto = literalMonto;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public String getDatoRecibo() {
        return datoRecibo;
    }

    public void setDatoRecibo(String datoRecibo) {
        this.datoRecibo = datoRecibo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getEstadoRecibo() {
        return estadoRecibo;
    }

    public void setEstadoRecibo(String estadoRecibo) {
        this.estadoRecibo = estadoRecibo;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(String solicitante) {
        this.solicitante = solicitante;
    }

    public String getApoderado() {
        return apoderado;
    }

    public void setApoderado(String apoderado) {
        this.apoderado = apoderado;
    }

    public String getSerieTitulo() {
        return serieTitulo;
    }

    public void setSerieTitulo(String serieTitulo) {
        this.serieTitulo = serieTitulo;
    }

    public String getTipoTitulo() {
        return tipoTitulo;
    }

    public void setTipoTitulo(String tipoTitulo) {
        this.tipoTitulo = tipoTitulo;
    }

    public String getTipoTituloIngresado() {
        return tipoTituloIngresado;
    }

    public void setTipoTituloIngresado(String tipoTituloIngresado) {
        this.tipoTituloIngresado = tipoTituloIngresado;
    }



    public String getTipoTramiteIngresado() {
        return tipoTramiteIngresado;
    }

    public void setTipoTramiteIngresado(String tipoTramiteIngresado) {
        this.tipoTramiteIngresado = tipoTramiteIngresado;
    }

    public String getNumeroTramiteIngresado() {
        return numeroTramiteIngresado;
    }

    public void setNumeroTramiteIngresado(String numeroTramiteIngresado) {
        this.numeroTramiteIngresado = numeroTramiteIngresado;
    }

    public String getGestionTramiteIngresado() {
        return gestionTramiteIngresado;
    }

    public void setGestionTramiteIngresado(String gestionTramiteIngresado) {
        this.gestionTramiteIngresado = gestionTramiteIngresado;
    }

    public String getExtensionTramiteIngresado() {
        return extensionTramiteIngresado;
    }

    public void setExtensionTramiteIngresado(String extensionTramiteIngresado) {
        this.extensionTramiteIngresado = extensionTramiteIngresado;
    }

    public Long getClaseIngresado() {
        return claseIngresado;
    }

    public void setClaseIngresado(Long claseIngresado) {
        this.claseIngresado = claseIngresado;
    }

    public String getDescIngresado() {
        return descIngresado;
    }

    public void setDescIngresado(String descIngresado) {
        this.descIngresado = descIngresado;
    }

    public String getApoderadoIngresado() {
        return apoderadoIngresado;
    }

    public void setApoderadoIngresado(String apoderadoIngresado) {
        this.apoderadoIngresado = apoderadoIngresado;
    }

    public Long getDepIngresado() {
        return depIngresado;
    }

    public void setDepIngresado(Long depIngresado) {
        this.depIngresado = depIngresado;
    }

    public Long getTituloIngresado() {
        return tituloIngresado;
    }

    public void setTituloIngresado(Long tituloIngresado) {
        this.tituloIngresado = tituloIngresado;
    }

    public String getSerieTituloIngresado() {
        return serieTituloIngresado;
    }

    public void setSerieTituloIngresado(String serieTituloIngresado) {
        this.serieTituloIngresado = serieTituloIngresado;
    }

    public String getTramite() {
        return tramite;
    }

    public void setTramite(String tramite) {
        this.tramite = tramite;
    }



    public Long getNumeroTramite() {
        return numeroTramite;
    }

    public void setNumeroTramite(Long numeroTramite) {
        this.numeroTramite = numeroTramite;
    }

    public Long getNumeroTitulo() {
        return numeroTitulo;
    }

    public void setNumeroTitulo(Long numeroTitulo) {
        this.numeroTitulo = numeroTitulo;
    }

    public Long getGestionTramite() {
        return gestionTramite;
    }

    public void setGestionTramite(Long gestionTramite) {
        this.gestionTramite = gestionTramite;
    }

    public int getClaseTramite() {
        return claseTramite;
    }

    public void setClaseTramite(int claseTramite) {
        this.claseTramite = claseTramite;
    }

    public String getExtensionTramite() {
        return extensionTramite;
    }

    public void setExtensionTramite(String extensionTramite) {
        this.extensionTramite = extensionTramite;
    }

    public String getDescripcionMarca() {
        return descripcionMarca;
    }

    public void setDescripcionMarca(String descripcionMarca) {
        this.descripcionMarca = descripcionMarca;
    }

    public Long getExpediente() {
        return expediente;
    }

    public void setExpediente(Long expediente) {
        this.expediente = expediente;
    }

    public String getTipoTramite() {
        return tipoTramite;
    }

    public void setTipoTramite(String tipoTramite) {
        this.tipoTramite = tipoTramite;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Long getIdRegional() {
        return idRegional;
    }

    public void setIdRegional(Long idRegional) {
        this.idRegional = idRegional;
    }
    
    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }
    
    
    @Override
    public Object clone() throws CloneNotSupportedException
    {
        Object clone = null;
        try
        {
            clone = super.clone();
        } 
        catch(CloneNotSupportedException e)
        {
            // No deberia ocurrir
        }
        return clone;
    }

}
