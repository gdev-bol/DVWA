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
public class LemasComerciales {
    
    Long id;
    String nombreMarca;
    String numeroSolicitudMarca;
    String numeroRegistro;
    String serie;
    Date fechaVigente;
    Long signoMarcaId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreMarca() {
        return nombreMarca;
    }

    public void setNombreMarca(String nombreMarca) {
        this.nombreMarca = nombreMarca;
    }

    public String getNumeroSolicitudMarca() {
        return numeroSolicitudMarca;
    }

    public void setNumeroSolicitudMarca(String numeroSolicitudMarca) {
        this.numeroSolicitudMarca = numeroSolicitudMarca;
    }

    public String getNumeroRegistro() {
        return numeroRegistro;
    }

    public void setNumeroRegistro(String numeroRegistro) {
        this.numeroRegistro = numeroRegistro;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public Date getFechaVigente() {
        return fechaVigente;
    }

    public void setFechaVigente(Date fechaVigente) {
        this.fechaVigente = fechaVigente;
    }

    public Long getSignoMarcaId() {
        return signoMarcaId;
    }

    public void setSignoMarcaId(Long signoMarcaId) {
        this.signoMarcaId = signoMarcaId;
    }
    
    
    
    
    
}
