    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.entidad;

import java.util.Date;

/**
 *
 * @author eddy
 */
public class SigObservacionTramite {

    Long idObservacionTramite;
    Long idSignoMarca;
    Long idUsuario;
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

    public Long getIdSignoMarca() {
        return idSignoMarca;
    }

    public void setIdSignoMarca(Long idSignoMarca) {
        this.idSignoMarca = idSignoMarca;
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
