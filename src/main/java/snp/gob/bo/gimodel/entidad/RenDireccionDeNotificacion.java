/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.entidad;

import java.io.Serializable;



/**
 * Entidad responsable de la Tabla regional
 *
 * @author Chano Rojas
 * @see RenDireccionDeNotificacion
 * @version 1.0, 11/08/2016
 */
public class RenDireccionDeNotificacion implements Serializable,Cloneable {

  Long iddirecciondenotificacion;
  Long idsolicitudrenovacion;
  Long idmodificacion;
  Long idlogtrans;
  String ciudad_notificacion;
  String zona_barrio;
  String avenida_calle;
  String numero;
  String edificio;
  String piso;
  String departamento;
  String referencia_direccion;
  String correo_electronico;
  String telefono;
  String celular;
  String estado;

    public Long getIddirecciondenotificacion() {
        return iddirecciondenotificacion;
    }

    public void setIddirecciondenotificacion(Long iddirecciondenotificacion) {
        this.iddirecciondenotificacion = iddirecciondenotificacion;
    }

    public Long getIdsolicitudrenovacion() {
        return idsolicitudrenovacion;
    }

    public void setIdsolicitudrenovacion(Long idsolicitudrenovacion) {
        this.idsolicitudrenovacion = idsolicitudrenovacion;
    }

    public Long getIdmodificacion() {
        return idmodificacion;
    }

    public void setIdmodificacion(Long idmodificacion) {
        this.idmodificacion = idmodificacion;
    }

    public Long getIdlogtrans() {
        return idlogtrans;
    }

    public void setIdlogtrans(Long idlogtrans) {
        this.idlogtrans = idlogtrans;
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

    public String getPiso() {
        return piso;
    }

    public void setPiso(String piso) {
        this.piso = piso;
    }


    public String getEdificio() {
        return edificio;
    }

    public void setEdificio(String edificio) {
        this.edificio = edificio;
    }


    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getReferencia_direccion() {
        return referencia_direccion;
    }

    public void setReferencia_direccion(String referencia_direccion) {
        this.referencia_direccion = referencia_direccion;
    }

    public String getCorreo_electronico() {
        return correo_electronico;
    }

    public void setCorreo_electronico(String correo_electronico) {
        this.correo_electronico = correo_electronico;
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