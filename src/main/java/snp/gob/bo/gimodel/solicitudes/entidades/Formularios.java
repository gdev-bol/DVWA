/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package snp.gob.bo.gimodel.solicitudes.entidades;

import java.util.Date;

/**
 *
 * @author Eddy Valero
 * @version 1.0, 23/08/2016
 */
public class Formularios {
    
    Long id;
    String numeroFormulario;
    String tipoFormulario;
    Date fechaSolicitud;
    String estado;
    Integer idUsuarioExterno;
    Integer idPadre;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroFormulario() {
        return numeroFormulario;
    }

    public void setNumeroFormulario(String numeroFormulario) {
        this.numeroFormulario = numeroFormulario;
    }

    public String getTipoFormulario() {
        return tipoFormulario;
    }

    public void setTipoFormulario(String tipoFormulario) {
        this.tipoFormulario = tipoFormulario;
    }

    public Date getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(Date fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getIdUsuarioExterno() {
        return idUsuarioExterno;
    }

    public void setIdUsuarioExterno(Integer idUsuarioExterno) {
        this.idUsuarioExterno = idUsuarioExterno;
    }

    public Integer getIdPadre() {
        return idPadre;
    }

    public void setIdPadre(Integer idPadre) {
        this.idPadre = idPadre;
    }
}
