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
 * @see OpoResolucion
 * @version 1.0, 06/09/2016
 */
public class OpoResolucion implements Serializable{
    
  Long idresolucion;
  Long idoposicion;
  Long idevento;
  Long idlogtrans;
  Integer numero_resolucion;
  Date fecha;
  String resolucion_cancelacion;
  String resolucion_oposicion;
  String resolucion_signo;
  Integer orden_o;
  String estado;

    public Long getIdresolucion() {
        return idresolucion;
    }

    public void setIdresolucion(Long idresolucion) {
        this.idresolucion = idresolucion;
    }

    public Long getIdoposicion() {
        return idoposicion;
    }

    public void setIdoposicion(Long idoposicion) {
        this.idoposicion = idoposicion;
    }

    public Long getIdevento() {
        return idevento;
    }

    public void setIdevento(Long idevento) {
        this.idevento = idevento;
    }

    public Long getIdlogtrans() {
        return idlogtrans;
    }

    public void setIdlogtrans(Long idlogtrans) {
        this.idlogtrans = idlogtrans;
    }

    public Integer getNumero_resolucion() {
        return numero_resolucion;
    }

    public void setNumero_resolucion(Integer numero_resolucion) {
        this.numero_resolucion = numero_resolucion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getResolucion_cancelacion() {
        return resolucion_cancelacion;
    }

    public void setResolucion_cancelacion(String resolucion_cancelacion) {
        this.resolucion_cancelacion = resolucion_cancelacion;
    }

    public String getResolucion_oposicion() {
        return resolucion_oposicion;
    }

    public void setResolucion_oposicion(String resolucion_oposicion) {
        this.resolucion_oposicion = resolucion_oposicion;
    }

    public String getResolucion_signo() {
        return resolucion_signo;
    }

    public void setResolucion_signo(String resolucion_signo) {
        this.resolucion_signo = resolucion_signo;
    }

    public Integer getOrden_o() {
        return orden_o;
    }

    public void setOrden_o(Integer orden_o) {
        this.orden_o = orden_o;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
  
  
  
}
