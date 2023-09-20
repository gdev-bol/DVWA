/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package snp.gob.bo.gimodel.entidad;

import java.io.Serializable;
/**
 *
 * @author Luis Angel Quispe Limachi
 *  * @see OpoNotificacion
 * @version 1.0, 06/09/2016
 */
public class OpoNotificacion implements Serializable{
    
   Long idnotificacion;
   Long idmarcademandada;
   Long idmarcademandante;
   String ciudad_notificacion;
   String zona_barrio;
   String avenida_calle;
   String numero;
   String edificio;
   String piso;
   String numero_departamento;
   String referencia_direccion;
   String email;
   String telefono;
   String celular;
   String estado;

    public Long getIdnotificacion() {
        return idnotificacion;
    }

    public void setIdnotificacion(Long idnotificacion) {
        this.idnotificacion = idnotificacion;
    }

    public Long getIdmarcademandada() {
        return idmarcademandada;
    }

    public void setIdmarcademandada(Long idmarcademandada) {
        this.idmarcademandada = idmarcademandada;
    }

    public Long getIdmarcademandante() {
        return idmarcademandante;
    }

    public void setIdmarcademandante(Long idmarcademandante) {
        this.idmarcademandante = idmarcademandante;
    }

    public String getCiudad_notificacion() {
        return ciudad_notificacion;
    }

    public void setCiudad_notificacion(String ciudad_notificacion) {
        this.ciudad_notificacion = ciudad_notificacion;
    }

    public String getZona_barrio() {
        return zona_barrio;
    }

    public void setZona_barrio(String zona_barrio) {
        this.zona_barrio = zona_barrio;
    }

    public String getAvenida_calle() {
        return avenida_calle;
    }

    public void setAvenida_calle(String avenida_calle) {
        this.avenida_calle = avenida_calle;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getEdificio() {
        return edificio;
    }

    public void setEdificio(String edificio) {
        this.edificio = edificio;
    }

    public String getPiso() {
        return piso;
    }

    public void setPiso(String piso) {
        this.piso = piso;
    }

    public String getNumero_departamento() {
        return numero_departamento;
    }

    public void setNumero_departamento(String numero_departamento) {
        this.numero_departamento = numero_departamento;
    }

    public String getReferencia_direccion() {
        return referencia_direccion;
    }

    public void setReferencia_direccion(String referencia_direccion) {
        this.referencia_direccion = referencia_direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
  
    
   
   
   
   
    
}
