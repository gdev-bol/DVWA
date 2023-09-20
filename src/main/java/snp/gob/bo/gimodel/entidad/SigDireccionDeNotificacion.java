/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.entidad;



/**
 *
 * @author Eddy Valero
 * @version 1.0, 29/08/2016
 */
public class SigDireccionDeNotificacion {
    
    Long idDireccionDeNotificacion;
    Long idSignoMarca;
    Long idLogTrans;
    String ciudadNotificacion;
    String zonaBarrio;
    String avenidaCalle;
    String numero;
    String edificio;
    String piso;
    String departamento;
    String referenciaDireccion;
    String correoElectronico;
    String telefono;
    String celular;
    String estado;

    public Long getIdDireccionDeNotificacion() {
        return idDireccionDeNotificacion;
    }

    public void setIdDireccionDeNotificacion(Long idDireccionDeNotificacion) {
        this.idDireccionDeNotificacion = idDireccionDeNotificacion;
    }

    public Long getIdSignoMarca() {
        return idSignoMarca;
    }

    public void setIdSignoMarca(Long idSignoMarca) {
        this.idSignoMarca = idSignoMarca;
    }

    public Long getIdLogTrans() {
        return idLogTrans;
    }

    public void setIdLogTrans(Long idLogTrans) {
        this.idLogTrans = idLogTrans;
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

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getReferenciaDireccion() {
        return referenciaDireccion;
    }

    public void setReferenciaDireccion(String referenciaDireccion) {
        this.referenciaDireccion = referenciaDireccion;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
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
    public String toString(){
    return "|"+"idDireccionDeNotificacion="+idDireccionDeNotificacion
          +"|"+"idSignoMarca="+idSignoMarca
          //+"|"+"idLogTrans="+idLogTrans
          +"|"+"ciudadNotificacion="+ciudadNotificacion
          +"|"+"zonaBarrio="+zonaBarrio
          +"|"+"avenidaCalle="+avenidaCalle
          +"|"+"numero="+numero
          +"|"+"edificio="+edificio
          +"|"+"piso="+piso
          +"|"+"departamento="+departamento
          +"|"+"referenciaDireccion="+referenciaDireccion
          +"|"+"correoElectronico="+correoElectronico
          +"|"+"telefono="+telefono
          +"|"+"celular="+celular
          +"|"+"estado="+estado+"|";
    }
    
}
