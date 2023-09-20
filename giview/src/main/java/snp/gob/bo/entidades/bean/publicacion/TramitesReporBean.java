/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.entidades.bean.publicacion;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author roco
 */
public class TramitesReporBean implements Serializable {

    private Long numeroPublicacion;
    private String nombreSigno;
    private String generoSigno;
    private String tipoSigno;
    private String numeroSolicitud;
    private Date fechaSolicitud;
    private String nombreTitular;
    private String direccionTitular;
    private String paisTitular;
    private String nombreApoderado;
    private String direccionApoderado;
    private String descripcionLogo;
    private String claseInternacional;
    private String productos;
    private String descripcionPaisTitular;
    private String prioridad;
    private String fechaPrioridad;
    private String paisPrioridad;
    private String paisPrioDescripcion;
    private String imgLogo;

    public Long getNumeroPublicacion() {
        return numeroPublicacion;
    }

    public void setNumeroPublicacion(Long numeroPublicacion) {
        this.numeroPublicacion = numeroPublicacion;
    }

    public String getNombreSigno() {
        return nombreSigno;
    }

    public void setNombreSigno(String nombreSigno) {
        this.nombreSigno = nombreSigno;
    }

    public String getGeneroSigno() {
        return generoSigno;
    }

    public void setGeneroSigno(String generoSigno) {
        this.generoSigno = generoSigno;
    }

    public String getTipoSigno() {
        return tipoSigno;
    }

    public void setTipoSigno(String tipoSigno) {
        this.tipoSigno = tipoSigno;
    }

    public String getNumeroSolicitud() {
        return numeroSolicitud;
    }

    public void setNumeroSolicitud(String numeroSolicitud) {
        this.numeroSolicitud = numeroSolicitud;
    }

    public Date getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(Date fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public String getNombreTitular() {
        return nombreTitular;
    }

    public void setNombreTitular(String nombreTitular) {
        this.nombreTitular = nombreTitular;
    }

    public String getDireccionTitular() {
        return direccionTitular;
    }

    public void setDireccionTitular(String direccionTitular) {
        this.direccionTitular = direccionTitular;
    }

    public String getPaisTitular() {
        return paisTitular;
    }

    public void setPaisTitular(String paisTitular) {
        this.paisTitular = paisTitular;
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

    public String getDescripcionLogo() {
        return descripcionLogo;
    }

    public void setDescripcionLogo(String descripcionLogo) {
        this.descripcionLogo = descripcionLogo;
    }

    public String getClaseInternacional() {
        return claseInternacional;
    }

    public void setClaseInternacional(String claseInternacional) {
        this.claseInternacional = claseInternacional;
    }

    public String getProductos() {
        return productos;
    }

    public void setProductos(String productos) {
        this.productos = productos;
    }

    public String getDescripcionPaisTitular() {
        return descripcionPaisTitular;
    }

    public void setDescripcionPaisTitular(String descripcionPaisTitular) {
        this.descripcionPaisTitular = descripcionPaisTitular;
    }

    public String getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }

    public String getFechaPrioridad() {
        return fechaPrioridad;
    }

    public void setFechaPrioridad(String fechaPrioridad) {
        this.fechaPrioridad = fechaPrioridad;
    }

    public String getPaisPrioridad() {
        return paisPrioridad;
    }

    public void setPaisPrioridad(String paisPrioridad) {
        this.paisPrioridad = paisPrioridad;
    }

    public String getPaisPrioDescripcion() {
        return paisPrioDescripcion;
    }

    public void setPaisPrioDescripcion(String paisPrioDescripcion) {
        this.paisPrioDescripcion = paisPrioDescripcion;
    }

    public String getImgLogo() {
        return imgLogo;
    }

    public void setImgLogo(String imgLogo) {
        this.imgLogo = imgLogo;
    }
}
