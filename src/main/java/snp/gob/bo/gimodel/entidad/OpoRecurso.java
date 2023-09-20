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
 * @see OpoRecurso
 * @version 1.0, 06/09/2016
 */
public class OpoRecurso implements Serializable{
 
  Long idrecurso;
  Long idevento;
  Long idoposicion;
  Long idlogtrans;
  Integer numero_resolucion;
  Date fecha_resolucion;
  String tipo;
  String resolucion;
  Integer orden_op;
  String estado;

    public Long getIdrecurso() {
        return idrecurso;
    }

    public void setIdrecurso(Long idrecurso) {
        this.idrecurso = idrecurso;
    }

    public Long getIdevento() {
        return idevento;
    }

    public void setIdevento(Long idevento) {
        this.idevento = idevento;
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

    public Integer getNumero_resolucion() {
        return numero_resolucion;
    }

    public void setNumero_resolucion(Integer numero_resolucion) {
        this.numero_resolucion = numero_resolucion;
    }

    public Date getFecha_resolucion() {
        return fecha_resolucion;
    }

    public void setFecha_resolucion(Date fecha_resolucion) {
        this.fecha_resolucion = fecha_resolucion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getResolucion() {
        return resolucion;
    }

    public void setResolucion(String resolucion) {
        this.resolucion = resolucion;
    }

    public Integer getOrden_op() {
        return orden_op;
    }

    public void setOrden_op(Integer orden_op) {
        this.orden_op = orden_op;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
  
  
    
}
