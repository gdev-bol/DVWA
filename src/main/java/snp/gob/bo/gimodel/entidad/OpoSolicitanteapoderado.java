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
 * @see OpoSolicitanteapoderado
 * @version 1.0, 06/09/2016
 */
public class OpoSolicitanteapoderado implements Serializable,Cloneable{

    Long idsolicitanteapoderado;
    Long idmarcademandada;
    Long idmarcademandante;
    String nombre_razonsocial;  
    String primer_apellido;
    String segundo_apellido;
    String numero_documento;
    String tipo_documento;
    String lugar_expedicion;
    String pais;
    String genero;
    String solicitud_depa;
    String poder;
    String direccion;
    String telefono;
    String celular;
    String email;
    String fax;
    String tiposoliapo;
    String tipo_titular;
    String nropoder;
    String tipo_persona;
    String estado;

    public Long getIdsolicitanteapoderado() {
        return idsolicitanteapoderado;
    }

    public void setIdsolicitanteapoderado(Long idsolicitanteapoderado) {
        this.idsolicitanteapoderado = idsolicitanteapoderado;
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

    public String getNombre_razonsocial() {
        return nombre_razonsocial;
    }

    public void setNombre_razonsocial(String nombre_razonsocial) {
        this.nombre_razonsocial = nombre_razonsocial;
    }

    public String getPrimer_apellido() {
        return primer_apellido;
    }

    public void setPrimer_apellido(String primer_apellido) {
        this.primer_apellido = primer_apellido;
    }

    public String getSegundo_apellido() {
        return segundo_apellido;
    }

    public void setSegundo_apellido(String segundo_apellido) {
        this.segundo_apellido = segundo_apellido;
    }

    public String getNumero_documento() {
        return numero_documento;
    }

    public void setNumero_documento(String numero_documento) {
        this.numero_documento = numero_documento;
    }

    public String getTipo_documento() {
        return tipo_documento;
    }

    public void setTipo_documento(String tipo_documento) {
        this.tipo_documento = tipo_documento;
    }

    public String getLugar_expedicion() {
        return lugar_expedicion;
    }

    public void setLugar_expedicion(String lugar_expedicion) {
        this.lugar_expedicion = lugar_expedicion;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getSolicitud_depa() {
        return solicitud_depa;
    }

    public void setSolicitud_depa(String solicitud_depa) {
        this.solicitud_depa = solicitud_depa;
    }

   

    public String getPoder() {
        return poder;
    }

    public void setPoder(String poder) {
        this.poder = poder;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getTipo_titular() {
        return tipo_titular;
    }

    public void setTipo_titular(String tipo_titular) {
        this.tipo_titular = tipo_titular;
    }

    public String getNropoder() {
        return nropoder;
    }

    public void setNropoder(String nropoder) {
        this.nropoder = nropoder;
    }

    public String getTipo_persona() {
        return tipo_persona;
    }

    public void setTipo_persona(String tipo_persona) {
        this.tipo_persona = tipo_persona;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTiposoliapo() {
        return tiposoliapo;
    }

    public void setTiposoliapo(String tiposoliapo) {
        this.tiposoliapo = tiposoliapo;
    }
    
     @Override
    public Object clone() throws CloneNotSupportedException
    {
        Object clone = null;
        try
        {
            clone = super.clone();
        } 
        catch(CloneNotSupportedException e)
        {
            // No deberia ocurrir
        }
        return clone;
    }

    
    
    
    
}
