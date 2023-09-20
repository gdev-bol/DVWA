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
 * @author Luis Angel Quispe Limachi
 *  * @see OpoEvento
 * @version 1.0, 06/09/2016
 */
public class OpoEvento implements Serializable{
    
  Long idevento;
  Long idactividad;
  Long idoposicion;
  Long idlogtrans;
  Date fecha;
  String observacion;
  Long usuario;
  Integer orden_o;
  Date fecha_operacion;
  String estado;

    public Long getIdevento() {
        return idevento;
    }

    public void setIdevento(Long idevento) {
        this.idevento = idevento;
    }

    public Long getIdactividad() {
        return idactividad;
    }

    public void setIdactividad(Long idactividad) {
        this.idactividad = idactividad;
    }

    public Long getIdoposicion() {
        return idoposicion;
    }

    public void setIdoposicion(Long idoposicion) {
        this.idoposicion = idoposicion;
    }

    public Long getIdlogtrans() {
        return idlogtrans;
    }

    public void setIdlogtrans(Long idlogtrans) {
        this.idlogtrans = idlogtrans;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Long getUsuario() {
        return usuario;
    }

    public void setUsuario(Long usuario) {
        this.usuario = usuario;
    }

    public Integer getOrden_o() {
        return orden_o;
    }

    public void setOrden_o(Integer orden_o) {
        this.orden_o = orden_o;
    }

    public Date getFecha_operacion() {
        return fecha_operacion;
    }

    public void setFecha_operacion(Date fecha_operacion) {
        this.fecha_operacion = fecha_operacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
  
    
}
