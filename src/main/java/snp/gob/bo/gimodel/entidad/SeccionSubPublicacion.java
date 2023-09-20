/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.entidad;

/**
 *
 * @author Ruben Ramirez
 * @version 1.0, 03/11/2016
 */
public class SeccionSubPublicacion {
    Long idSeccionSubPublicacion;
    Long idDominio;
    Long idLogTrans;
    Integer seccion;
    Integer subSeccion;
    String Descripcion;
    String estado;

    public Long getIdSeccionSubPublicacion() {
        return idSeccionSubPublicacion;
    }

    public void setIdSeccionSubPublicacion(Long idSeccionSubPublicacion) {
        this.idSeccionSubPublicacion = idSeccionSubPublicacion;
    }

    public Long getIdDominio() {
        return idDominio;
    }

    public void setIdDominio(Long idDominio) {
        this.idDominio = idDominio;
    }

    public Long getIdLogTrans() {
        return idLogTrans;
    }

    public void setIdLogTrans(Long idLogTrans) {
        this.idLogTrans = idLogTrans;
    }

    public Integer getSeccion() {
        return seccion;
    }

    public void setSeccion(Integer seccion) {
        this.seccion = seccion;
    }

    public Integer getSubSeccion() {
        return subSeccion;
    }

    public void setSubSeccion(Integer subSeccion) {
        this.subSeccion = subSeccion;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estasdo) {
        this.estado = estasdo;
    }
    
}
