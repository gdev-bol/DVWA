/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.entidad;

import java.util.Date;

/**
 * Entidad responsable de la Tabla regional
 *
 * @author Chano Rojas
 * @see DosificacionMapper
 * @version 1.0, 11/08/2016
 */
public class RenResolucion {
 Long idresolucion;
  Long idrenovacion;
  Integer numero_resolucion;
  Integer gestion_resolucion;
  Date fecha_resolucion;
  String observacion_resolucion;
  String documento_resolucion ;
  String estado ;
  
      public Long getIdresolucion() {
        return idresolucion;
    }

    public void setIdresolucion(Long idresolucion) {
        this.idresolucion = idresolucion;
    }
        
    public Long getIdrenovacion() {
        return idrenovacion;
    }

    public void setIdrenovacion(Long idrenovacion) {
        this.idrenovacion = idrenovacion;
    }

    public Integer getNumero_resolucion() {
        return numero_resolucion;
    }

    public void setNumero_resolucion(Integer numero_resolucion) {
        this.numero_resolucion = numero_resolucion;
    }

    public Integer getGestion_resolucion() {
        return gestion_resolucion;
    }

    public void setGestion_resolucion(Integer gestion_resolucion) {
        this.gestion_resolucion = gestion_resolucion;
    }

    public Date getFecha_resolucion() {
        return fecha_resolucion;
    }

    public void setFecha_resolucion(Date fecha_resolucion) {
        this.fecha_resolucion = fecha_resolucion;
    }

    public String getObservacion_resolucion() {
        return observacion_resolucion;
    }

    public void setObservacion_resolucion(String observacion_resolucion) {
        this.observacion_resolucion = observacion_resolucion;
    }

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
    
  
  
}