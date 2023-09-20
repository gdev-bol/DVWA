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
public class ModResolucion  implements Serializable{
    Long idresolucion;
    Long idmodificacion;
    Integer numero_resolucion;
    Integer gestion_resolucion;
    Date fecha_resolucion;
    String observacion_resolucion;
    String documento_resolucion;
    String estado;

    public String getDocumento_resolucion() {
        return documento_resolucion;
    }

    public void setDocumento_resolucion(String documento_resolucion) {
        this.documento_resolucion = documento_resolucion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFecha_resolucion() {
        return fecha_resolucion;
    }

    public void setFecha_resolucion(Date fecha_resolucion) {
        this.fecha_resolucion = fecha_resolucion;
    }

    public Integer getGestion_resolucion() {
        return gestion_resolucion;
    }

    public void setGestion_resolucion(Integer gestion_resolucion) {
        this.gestion_resolucion = gestion_resolucion;
    }

    public Long getIdmodificacion() {
        return idmodificacion;
    }

    public void setIdmodificacion(Long idmodificacion) {
        this.idmodificacion = idmodificacion;
    }

    public Long getIdresolucion() {
        return idresolucion;
    }

    public void setIdresolucion(Long idresolucion) {
        this.idresolucion = idresolucion;
    }

    public Integer getNumero_resolucion() {
        return numero_resolucion;
    }

    public void setNumero_resolucion(Integer numero_resolucion) {
        this.numero_resolucion = numero_resolucion;
    }

    public String getObservacion_resolucion() {
        return observacion_resolucion;
    }

    public void setObservacion_resolucion(String observacion_resolucion) {
        this.observacion_resolucion = observacion_resolucion;
    }
    
}
