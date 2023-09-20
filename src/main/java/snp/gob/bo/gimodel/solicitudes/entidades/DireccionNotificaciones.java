/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package snp.gob.bo.gimodel.solicitudes.entidades;

import snp.gob.bo.gimodel.entidad.*;
import java.util.Date;

/**
 *
 * @author Eddy Valero
 * @version 1.0, 23/08/2016
 */
public class DireccionNotificaciones {
    
    Long id;
    String ciudadNotificacion;
    String zonaBarrio;
    String avenidaCalle;
    String numeroDomicilio;
    String nombreEdificio;
    String piso;
    String departamento;
    String telefono;
    String celular;
    String correoelectronico;
    String referencia;
    String estado;
    Long formularioId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCiudadNotificacion() {
        return ciudadNotificacion;
    }

    public void setCiudadNotificacion(String ciudadNotificacion) {
        this.ciudadNotificacion = ciudadNotificacion;
    }

    public String getZonaBarrio() {
        return zonaBarrio;
    }

    public void setZonaBarrio(String zonaBarrio) {
        this.zonaBarrio = zonaBarrio;
    }

    public String getAvenidaCalle() {
        return avenidaCalle;
    }

    public void setAvenidaCalle(String avenidaCalle) {
        this.avenidaCalle = avenidaCalle;
    }

    public String getNumeroDomicilio() {
        return numeroDomicilio;
    }

    public void setNumeroDomicilio(String numeroDomicilio) {
        this.numeroDomicilio = numeroDomicilio;
    }

    public String getNombreEdificio() {
        return nombreEdificio;
    }

    public void setNombreEdificio(String nombreEdificio) {
        this.nombreEdificio = nombreEdificio;
    }

    public String getPiso() {
        return piso;
    }

    public void setPiso(String piso) {
        this.piso = piso;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getCorreoelectronico() {
        return correoelectronico;
    }

    public void setCorreoelectronico(String correoelectronico) {
        this.correoelectronico = correoelectronico;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Long getFormularioId() {
        return formularioId;
    }

    public void setFormularioId(Long formularioId) {
        this.formularioId = formularioId;
    }
    
    
    
    
    
  
  
    
}
