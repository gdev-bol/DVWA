/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package snp.gob.bo.gimodel.entidad;

/**
 * Entidad responsable de la Tabla regional
 * 
 * @author Eddy Valero
 * @see RegionalMapper
 * @version 1.0, 25/07/2016
 */
public class Regional {
    
    Long idRegional;
    Long idLogTrans;
    Boolean central;
    String nombre;
    String dirección;
    String telefono;
    String fax;
    String tipoCiudadNotificacion;
    String estado;

    public Long getIdRegional() {
        return idRegional;
    }

    public void setIdRegional(Long idRegional) {
        this.idRegional = idRegional;
    }

    public Long getIdLogTrans() {
        return idLogTrans;
    }

    public void setIdLogTrans(Long idLogTrans) {
        this.idLogTrans = idLogTrans;
    }

    public Boolean isCentral() {
        return central;
    }

    public void setCentral(Boolean central) {
        this.central = central;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDirección() {
        return dirección;
    }

    public void setDirección(String dirección) {
        this.dirección = dirección;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getTipoCiudadNotificacion() {
        return tipoCiudadNotificacion;
    }

    public void setTipoCiudadNotificacion(String tipoCiudadNotificacion) {
        this.tipoCiudadNotificacion = tipoCiudadNotificacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
    
    
    
}
