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
public class ModHistorial  implements Serializable{
  Long  idhistorial;
  Long idmodificacion;
  Long idusuario;
  Long idlogtrans;
  String tipo_tramite;
  String operacion;
  String estado_marca;
  String observacion;
  String ubicacion;
  String seccion;
  Integer gestion_renovacion;
  String descripcion;
  String descripcion_lista_productos;
  Date fecha_operacion;
  String estado;

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion_lista_productos() {
        return descripcion_lista_productos;
    }

    public void setDescripcion_lista_productos(String descripcion_lista_productos) {
        this.descripcion_lista_productos = descripcion_lista_productos;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getEstado_marca() {
        return estado_marca;
    }

    public void setEstado_marca(String estado_marca) {
        this.estado_marca = estado_marca;
    }

    public Date getFecha_operacion() {
        return fecha_operacion;
    }

    public void setFecha_operacion(Date fecha_operacion) {
        this.fecha_operacion = fecha_operacion;
    }

    public Integer getGestion_renovacion() {
        return gestion_renovacion;
    }

    public void setGestion_renovacion(Integer gestion_renovacion) {
        this.gestion_renovacion = gestion_renovacion;
    }

    public Long getIdhistorial() {
        return idhistorial;
    }

    public void setIdhistorial(Long idhistorial) {
        this.idhistorial = idhistorial;
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

    public Long getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Long idusuario) {
        this.idusuario = idusuario;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getOperacion() {
        return operacion;
    }

    public void setOperacion(String operacion) {
        this.operacion = operacion;
    }

    public String getSeccion() {
        return seccion;
    }

    public void setSeccion(String seccion) {
        this.seccion = seccion;
    }

    public String getTipo_tramite() {
        return tipo_tramite;
    }

    public void setTipo_tramite(String tipo_tramite) {
        this.tipo_tramite = tipo_tramite;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    
}
