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
public class Dosificacion {
  Long idDosificacion;
  Long idLogTrans;
  int valorInicio;
  int valorFinal;
  int siguiente;
  int gestion;
  Date fechaDosificacion;
 String estado;

    public Long getIdDosificacion() {
        return idDosificacion;
    }

    public void setIdDosificacion(Long idDosificacion) {
        this.idDosificacion = idDosificacion;
    }

    public Long getIdLogTrans() {
        return idLogTrans;
    }

    public void setIdLogTrans(Long idLogTrans) {
        this.idLogTrans = idLogTrans;
    }

    public int getValorInicio() {
        return valorInicio;
    }

    public void setValorInicio(int valorInicio) {
        this.valorInicio = valorInicio;
    }

    public int getValorFinal() {
        return valorFinal;
    }

    public void setValorFinal(int valorFinal) {
        this.valorFinal = valorFinal;
    }

    public int getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(int siguiente) {
        this.siguiente = siguiente;
    }

    public int getGestion() {
        return gestion;
    }

    public void setGestion(int gestion) {
        this.gestion = gestion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFechaDosificacion() {
        return fechaDosificacion;
    }

    public void setFechaDosificacion(Date fechaDosificacion) {
        this.fechaDosificacion = fechaDosificacion;
    }

     
    
  
  
  
    
}
