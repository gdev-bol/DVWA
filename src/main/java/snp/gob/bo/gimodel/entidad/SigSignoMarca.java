/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.entidad;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Eddy Valero
 * @version 1.0, 09/08/2016
 */
public class SigSignoMarca implements Serializable{

    Long idSignoMarca;
    Long idLogTrans;
    String numeroFormulario;
    Long sm;
    String signo;
    String tipoGenero;
    String descripcionSigno;
    String descripcionLogotipo;
    String ubicacion;
    String estadoMarca;
    Long numeroRecibo;
    String serie;
    Long numeroTitulo;
    String serieTitulo;
    String origenSolicitud;
    Long numeroGaceta;
    Long numeroPublicacion;
    Date fechaPublicacion;
    Long numeroRegistro;
    String serieRegistro;
    String resolucionRegistro;
    Date fechaRegistro;
    Integer ordenRenovacion;
    Integer numeroRenovacion;
    String extensionRenovacion;
    Long numeroResolucionRenovacion;
    Date fechaRenovacion;
    String oficina;
    Date fechaSolicitud;
    Date fechaIngreso;
    Boolean entregadoUsuario;
    Boolean notoriedadMarca;
    String estado;
    Integer numero;
    Integer gestion;
    Integer extensionMarca;
    

    public Long getIdSignoMarca() {
        return idSignoMarca;
    }

    public void setIdSignoMarca(Long idSignoMarca) {
        this.idSignoMarca = idSignoMarca;
    }

    public Long getIdLogTrans() {
        return idLogTrans;
    }

    public void setIdLogTrans(Long idLogTrans) {
        this.idLogTrans = idLogTrans;
    }

    public String getNumeroFormulario() {
        return numeroFormulario;
    }

    public void setNumeroFormulario(String numeroFormulario) {
        this.numeroFormulario = numeroFormulario;
    }

    public Long getSm() {
        return sm;
    }

    public void setSm(Long sm) {
        this.sm = sm;
    }

    public String getSigno() {
        return signo;
    }

    public void setSigno(String signo) {
        this.signo = signo;
    }

    public String getTipoGenero() {
        return tipoGenero;
    }

    public void setTipoGenero(String tipoGenero) {
        this.tipoGenero = tipoGenero;
    }

    public String getDescripcionSigno() {
        return descripcionSigno;
    }

    public void setDescripcionSigno(String descripcionSigno) {
        this.descripcionSigno = descripcionSigno;
    }

    public String getDescripcionLogotipo() {
        return descripcionLogotipo;
    }

    public void setDescripcionLogotipo(String descripcionLogotipo) {
        this.descripcionLogotipo = descripcionLogotipo;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getEstadoMarca() {
        return estadoMarca;
    }

    public void setEstadoMarca(String estadoMarca) {
        this.estadoMarca = estadoMarca;
    }

    public Long getNumeroRecibo() {
        return numeroRecibo;
    }

    public void setNumeroRecibo(Long numeroRecibo) {
        this.numeroRecibo = numeroRecibo;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public Long getNumeroTitulo() {
        return numeroTitulo;
    }

    public void setNumeroTitulo(Long numeroTitulo) {
        this.numeroTitulo = numeroTitulo;
    }

    public String getSerieTitulo() {
        return serieTitulo;
    }

    public void setSerieTitulo(String serieTitulo) {
        this.serieTitulo = serieTitulo;
    }

    public String getOrigenSolicitud() {
        return origenSolicitud;
    }

    public void setOrigenSolicitud(String origenSolicitud) {
        this.origenSolicitud = origenSolicitud;
    }

    public Long getNumeroGaceta() {
        return numeroGaceta;
    }

    public void setNumeroGaceta(Long numeroGaceta) {
        this.numeroGaceta = numeroGaceta;
    }

    public Long getNumeroPublicacion() {
        return numeroPublicacion;
    }

    public void setNumeroPublicacion(Long numeroPublicacion) {
        this.numeroPublicacion = numeroPublicacion;
    }

    public Date getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(Date fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
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

    public String getResolucionRegistro() {
        return resolucionRegistro;
    }

    public void setResolucionRegistro(String resolucionRegistro) {
        this.resolucionRegistro = resolucionRegistro;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Integer getOrdenRenovacion() {
        return ordenRenovacion;
    }

    public void setOrdenRenovacion(Integer ordenRenovacion) {
        this.ordenRenovacion = ordenRenovacion;
    }

    public Integer getNumeroRenovacion() {
        return numeroRenovacion;
    }

    public void setNumeroRenovacion(Integer numeroRenovacion) {
        this.numeroRenovacion = numeroRenovacion;
    }

    public String getExtensionRenovacion() {
        return extensionRenovacion;
    }

    public void setExtensionRenovacion(String extensionRenovacion) {
        this.extensionRenovacion = extensionRenovacion;
    }

    public Long getNumeroResolucionRenovacion() {
        return numeroResolucionRenovacion;
    }

    public void setNumeroResolucionRenovacion(Long numeroResolucionRenovacion) {
        this.numeroResolucionRenovacion = numeroResolucionRenovacion;
    }

    public Date getFechaRenovacion() {
        return fechaRenovacion;
    }

    public void setFechaRenovacion(Date fechaRenovacion) {
        this.fechaRenovacion = fechaRenovacion;
    }

    public String getOficina() {
        return oficina;
    }

    public void setOficina(String oficina) {
        this.oficina = oficina;
    }

    public Date getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(Date fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Boolean getEntregadoUsuario() {
        return entregadoUsuario;
    }

    public void setEntregadoUsuario(Boolean entregadoUsuario) {
        this.entregadoUsuario = entregadoUsuario;
    }

    public Boolean getNotoriedadMarca() {
        return notoriedadMarca;
    }

    public void setNotoriedadMarca(Boolean notoriedadMarca) {
        this.notoriedadMarca = notoriedadMarca;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Integer getGestion() {
        return gestion;
    }

    public void setGestion(Integer gestion) {
        this.gestion = gestion;
    }

    public Integer getExtensionMarca() {
        return extensionMarca;
    }

    public void setExtensionMarca(Integer extensionMarca) {
        this.extensionMarca = extensionMarca;
    }

    
    
}
