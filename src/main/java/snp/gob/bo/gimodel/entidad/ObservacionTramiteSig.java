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
 * @author placido
 */
public class ObservacionTramiteSig implements Serializable{

    Long idObservacionTramite;
    Long id;
    String nombreUsuario;
    Long idLogTrans;
    Boolean editable;
    Date fechaObservacion;
    String etapaTramite;
    String descripcion;
    String estado;

    public Long getIdObservacionTramite() {
        return idObservacionTramite;
    }

    public void setIdObservacionTramite(Long idObservacionTramite) {
        this.idObservacionTramite = idObservacionTramite;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public Long getIdLogTrans() {
        return idLogTrans;
    }

    public void setIdLogTrans(Long idLogTrans) {
        this.idLogTrans = idLogTrans;
    }

    public Boolean getEditable() {
        return editable;
    }

    public void setEditable(Boolean editable) {
        this.editable = editable;
    }

    public Date getFechaObservacion() {
        return fechaObservacion;
    }

    public void setFechaObservacion(Date fechaObservacion) {
        this.fechaObservacion = fechaObservacion;
    }

    public String getEtapaTramite() {
        return etapaTramite;
    }

    public void setEtapaTramite(String etapaTramite) {
        this.etapaTramite = etapaTramite;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

}
