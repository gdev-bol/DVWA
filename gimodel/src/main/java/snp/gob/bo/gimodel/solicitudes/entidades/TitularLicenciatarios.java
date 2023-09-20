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
 * @version 1.0, 24/10/2016
 */
public class TitularLicenciatarios {
    
    Long id;
    String tipoTitular;
    String tipoPersona;
    String tipoTitularRegistrado;
    String nombreRazonSocial;
    String direccion;
    String estado;
    Integer modificacionId;
    Long idPadre;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipoTitular() {
        return tipoTitular;
    }

    public void setTipoTitular(String tipoTitular) {
        this.tipoTitular = tipoTitular;
    }

    public String getTipoPersona() {
        return tipoPersona;
    }

    public String getTipoTitularRegistrado() {
        return tipoTitularRegistrado;
    }

    public void setTipoTitularRegistrado(String tipoTitularRegistrado) {
        this.tipoTitularRegistrado = tipoTitularRegistrado;
    }
    
    public void setTipoPersona(String tipoPersona) {
        this.tipoPersona = tipoPersona;
    }

    public String getNombreRazonSocial() {
        return nombreRazonSocial;
    }

    public void setNombreRazonSocial(String nombreRazonSocial) {
        this.nombreRazonSocial = nombreRazonSocial;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getModificacionId() {
        return modificacionId;
    }

    public void setModificacionId(Integer modificacionId) {
        this.modificacionId = modificacionId;
    }

    public Long getIdPadre() {
        return idPadre;
    }

    public void setIdPadre(Long idPadre) {
        this.idPadre = idPadre;
    }
    
}

