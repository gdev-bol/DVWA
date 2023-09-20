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
 * @author Susana Escobar
 * @version 1.0, 29/10/2016
 */
public class SigRegistro implements Serializable{

    Long idRegistro;
    Long idSignoMarca;
    Long idLogTrans;
    Long numeroRegistro;
    String serie;
    Long sm;
    String smDescripcion;
    String estadoRegistro;
    Date fechaIngreso;
    Integer idTipoResolucion;
    String documentoResolucion;
    String signo;
    String tipoSignoDescripcion;
    String tipoGeneroDescripcion;
    Integer clase;
    Long numeroResolucion;
    Integer gestionResolucion;
    Date fechaRegistro;
    Long numeroPublicacion;
    Date fechaPublicacion;
    Long numeroGaceta;
    Date fechaPrimerUso;
    String titular;
    String direccionTitular;
    String paisTitular;
    String paisTitularDescripcion;
    String nombreApoderado;
    String direccionApoderado;
    String listaProductos;
    String observacion;
    String estado;

    public Long getIdRegistro() {
        return idRegistro;
    }

    public void setIdRegistro(Long idRegistro) {
        this.idRegistro = idRegistro;
    }

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

    public Long getNumeroRegistro() {
        return numeroRegistro;
    }

    public void setNumeroRegistro(Long numeroRegistro) {
        this.numeroRegistro = numeroRegistro;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public Long getSm() {
        return sm;
    }

    public void setSm(Long sm) {
        this.sm = sm;
    }

    public String getEstadoRegistro() {
        return estadoRegistro;
    }

    public void setEstadoRegistro(String estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Integer getIdTipoResolucion() {
        return idTipoResolucion;
    }

    public void setIdTipoResolucion(Integer idTipoResolucion) {
        this.idTipoResolucion = idTipoResolucion;
    }

    public String getDocumentoResolucion() {
        return documentoResolucion;
    }

    public void setDocumentoResolucion(String documentoResolucion) {
        this.documentoResolucion = documentoResolucion;
    }

    public String getSigno() {
        return signo;
    }

    public void setSigno(String signo) {
        this.signo = signo;
    }

    public String getTipoSignoDescripcion() {
        return tipoSignoDescripcion;
    }

    public void setTipoSignoDescripcion(String tipoSignoDescripcion) {
        this.tipoSignoDescripcion = tipoSignoDescripcion;
    }

    public String getTipoGeneroDescripcion() {
        return tipoGeneroDescripcion;
    }

    public void setTipoGeneroDescripcion(String tipoGeneroDescripcion) {
        this.tipoGeneroDescripcion = tipoGeneroDescripcion;
    }

    public Integer getClase() {
        return clase;
    }

    public void setClase(Integer clase) {
        this.clase = clase;
    }

    public Long getNumeroResolucion() {
        return numeroResolucion;
    }

    public void setNumeroResolucion(Long numeroResolucion) {
        this.numeroResolucion = numeroResolucion;
    }

    public Integer getGestionResolucion() {
        return gestionResolucion;
    }

    public void setGestionResolucion(Integer gestionResolucion) {
        this.gestionResolucion = gestionResolucion;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
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

    public Long getNumeroGaceta() {
        return numeroGaceta;
    }

    public void setNumeroGaceta(Long numeroGaceta) {
        this.numeroGaceta = numeroGaceta;
    }

    public Date getFechaPrimerUso() {
        return fechaPrimerUso;
    }

    public void setFechaPrimerUso(Date fechaPrimerUso) {
        this.fechaPrimerUso = fechaPrimerUso;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public String getDireccionTitular() {
        return direccionTitular;
    }

    public void setDireccionTitular(String direccionTitular) {
        this.direccionTitular = direccionTitular;
    }

    public String getPaisTitularDescripcion() {
        return paisTitularDescripcion;
    }

    public void setPaisTitularDescripcion(String paisTitularDescripcion) {
        this.paisTitularDescripcion = paisTitularDescripcion;
    }

    public String getNombreApoderado() {
        return nombreApoderado;
    }

    public void setNombreApoderado(String nombreApoderado) {
        this.nombreApoderado = nombreApoderado;
    }

    public String getDireccionApoderado() {
        return direccionApoderado;
    }

    public void setDireccionApoderado(String direccionApoderado) {
        this.direccionApoderado = direccionApoderado;
    }

    public String getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(String listaProductos) {
        this.listaProductos = listaProductos;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getSmDescripcion() {
        return smDescripcion;
    }

    public void setSmDescripcion(String smDescripcion) {
        this.smDescripcion = smDescripcion;
    }

    public String getPaisTitular() {
        return paisTitular;
    }

    public void setPaisTitular(String paisTitular) {
        this.paisTitular = paisTitular;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }   
}
