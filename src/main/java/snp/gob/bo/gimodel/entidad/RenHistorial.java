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
 * @author Eddy Valero
 * @version 1.0
 */
public class RenHistorial implements Serializable{

    Long idHistorial;
    Long idUsuario;
    Long id;
    Long idLogTrans;
    String tipo;
    String operacion;
    String estadoMarcaDescripcion;
    String observacion;
    String ubicacionDescripcion;
    String seccion;
    Integer gestionRenovación;
    String descripcion;
    String descripcionListaProductos;
    Date fechaOperacion;
    String estado;

    public Long getIdHistorial() {
        return idHistorial;
    }

    public void setIdHistorial(Long idHistorial) {
        this.idHistorial = idHistorial;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getOperacion() {
        return operacion;
    }

    public void setOperacion(String operacion) {
        this.operacion = operacion;
    }

    public String getEstadoMarcaDescripcion() {
        return estadoMarcaDescripcion;
    }

    public void setEstadoMarcaDescripcion(String estadoMarcaDescripcion) {
        this.estadoMarcaDescripcion = estadoMarcaDescripcion;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getUbicacionDescripcion() {
        return ubicacionDescripcion;
    }

    public void setUbicacionDescripcion(String ubicacionDescripcion) {
        this.ubicacionDescripcion = ubicacionDescripcion;
    }

    public String getSeccion() {
        return seccion;
    }

    public void setSeccion(String seccion) {
        this.seccion = seccion;
    }

    public Integer getGestionRenovación() {
        return gestionRenovación;
    }

    public void setGestionRenovación(Integer gestionRenovación) {
        this.gestionRenovación = gestionRenovación;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcionListaProductos() {
        return descripcionListaProductos;
    }

    public void setDescripcionListaProductos(String descripcionListaProductos) {
        this.descripcionListaProductos = descripcionListaProductos;
    }

    public Date getFechaOperacion() {
        return fechaOperacion;
    }

    public void setFechaOperacion(Date fechaOperacion) {
        this.fechaOperacion = fechaOperacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

}
