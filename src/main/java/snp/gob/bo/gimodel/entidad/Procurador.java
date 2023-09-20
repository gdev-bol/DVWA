/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.entidad;

import java.io.Serializable;

/**
 *
 * @author luisangel
 */
public class Procurador implements Serializable, Cloneable {
    
  Long  idprocurador;
  Long idlogtrans; 
  String nombre_razonsocial;
  String primer_apellido;
  String segundo_apellido;
  String tipo_titular;
  String numero_documento;
  String tipo_documento;
  String lugar_expedicion;
  String telefono;
  String celular;
  String tipodocumentoautorizacion;
  String descripciondocumentoautorizacion;
  String estado;

    public Long getIdprocurador() {
        return idprocurador;
    }

    public void setIdprocurador(Long idprocurador) {
        this.idprocurador = idprocurador;
    }

    public Long getIdlogtrans() {
        return idlogtrans;
    }

    public void setIdlogtrans(Long idlogtrans) {
        this.idlogtrans = idlogtrans;
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

    public String getTipo_titular() {
        return tipo_titular;
    }

    public void setTipo_titular(String tipo_titular) {
        this.tipo_titular = tipo_titular;
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

    public String getTipodocumentoautorizacion() {
        return tipodocumentoautorizacion;
    }

    public void setTipodocumentoautorizacion(String tipodocumentoautorizacion) {
        this.tipodocumentoautorizacion = tipodocumentoautorizacion;
    }

    public String getDescripciondocumentoautorizacion() {
        return descripciondocumentoautorizacion;
    }

    public void setDescripciondocumentoautorizacion(String descripciondocumentoautorizacion) {
        this.descripciondocumentoautorizacion = descripciondocumentoautorizacion;
    }
    
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
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
