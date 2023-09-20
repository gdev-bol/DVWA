/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.entidades.bean.registro;

import java.io.Serializable;

/**
 *
 * @author Ruben Ramirez
 */
public class CatalogoDetallePojo implements Serializable {

    private String nroRegistro;
    private String resolucion;
    private String fechaRegistro;
    private String signo;
    private String tipoMarca;
    private String genero;
    private String nroSolicitud;
    private String fechaSolicitud;
    private String horaSolicitud;
    private Long nroPublicacion;
    private String fechaPublicacion;
    private Long nroGaceta;
    private String titular;
    private String paisTitular;
    private String direccionTitular;
    private String apoderado;
    private String direccionApoderado;
    private Integer clase;
    private String producto;
    private String logo;
    private Boolean conImagen;

    public String getNroRegistro() {
        return nroRegistro;
    }

    public void setNroRegistro(String nroRegistro) {
        this.nroRegistro = nroRegistro;
    }

    public String getResolucion() {
        return resolucion;
    }

    public void setResolucion(String resolucion) {
        this.resolucion = resolucion;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getSigno() {
        return signo;
    }

    public void setSigno(String signo) {
        this.signo = signo;
    }

    public String getTipoMarca() {
        return tipoMarca;
    }

    public void setTipoMarca(String tipoMarca) {
        this.tipoMarca = tipoMarca;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getNroSolicitud() {
        return nroSolicitud;
    }

    public void setNroSolicitud(String nroSolicitud) {
        this.nroSolicitud = nroSolicitud;
    }

    public String getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(String fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public String getHoraSolicitud() {
        return horaSolicitud;
    }

    public void setHoraSolicitud(String horaSolicitud) {
        this.horaSolicitud = horaSolicitud;
    }

    public Long getNroPublicacion() {
        return nroPublicacion;
    }

    public void setNroPublicacion(Long nroPublicacion) {
        this.nroPublicacion = nroPublicacion;
    }

    public String getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(String fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public Long getNroGaceta() {
        return nroGaceta;
    }

    public void setNroGaceta(Long nroGaceta) {
        this.nroGaceta = nroGaceta;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public String getPaisTitular() {
        return paisTitular;
    }

    public void setPaisTitular(String paisTitular) {
        this.paisTitular = paisTitular;
    }

    public String getDireccionTitular() {
        return direccionTitular;
    }

    public void setDireccionTitular(String direccionTitular) {
        this.direccionTitular = direccionTitular;
    }

    public String getApoderado() {
        return apoderado;
    }

    public void setApoderado(String apoderado) {
        this.apoderado = apoderado;
    }

    public String getDireccionApoderado() {
        return direccionApoderado;
    }

    public void setDireccionApoderado(String direccionApoderado) {
        this.direccionApoderado = direccionApoderado;
    }

    public Integer getClase() {
        return clase;
    }

    public void setClase(Integer clase) {
        this.clase = clase;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Boolean getConImagen() {
        return conImagen;
    }

    public void setConImagen(Boolean conImagen) {
        this.conImagen = conImagen;
    }

}
