/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.entidad;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Sushy
 */
public class ModObservacionTramite  implements Serializable{

    Long idobservaciontramite;
    Long idmodificacion;
    Long idusuario;
    Long idlogtrans;
    Boolean editable;
    Date fecha_observacion;
    String etapa_tramite;
    String descripcion;
    String estado;

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Boolean getEditable() {
        return editable;
    }

    public void setEditable(Boolean editable) {
        this.editable = editable;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

   
    public Date getFecha_observacion() {
        return fecha_observacion;
    }

    public void setFecha_observacion(Date fecha_observacion) {
        this.fecha_observacion = fecha_observacion;
    }

    public Long getIdlogtrans() {
        return idlogtrans;
    }

    public void setIdlogtrans(Long idlogtrans) {
        this.idlogtrans = idlogtrans;
    }

    public Long getIdmodificacion() {
        return idmodificacion;
    }

    public void setIdmodificacion(Long idmodificacion) {
        this.idmodificacion = idmodificacion;
    }

    public Long getIdobservaciontramite() {
        return idobservaciontramite;
    }

    public void setIdobservaciontramite(Long idobservaciontramite) {
        this.idobservaciontramite = idobservaciontramite;
    }

    public Long getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Long idusuario) {
        this.idusuario = idusuario;
    }

    public String getEtapa_tramite() {
        return etapa_tramite;
    }

    public void setEtapa_tramite(String etapa_tramite) {
        this.etapa_tramite = etapa_tramite;
    }
    
    
}
