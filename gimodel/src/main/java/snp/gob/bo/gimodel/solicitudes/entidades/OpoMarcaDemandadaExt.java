/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.solicitudes.entidades;

import java.util.Date;

/**
 *
 * @author luis_angel
 */
public class OpoMarcaDemandadaExt {
    
  Long idmarcademandada;
  Integer idformulario;
  Integer dmdo_public;
  Integer gaceta; 
  Date fechacreacion;
  Date fechamod;
  String estado;

    public Long getIdmarcademandada() {
        return idmarcademandada;
    }

    public void setIdmarcademandada(Long idmarcademandada) {
        this.idmarcademandada = idmarcademandada;
    }

    public Integer getIdformulario() {
        return idformulario;
    }

    public void setIdformulario(Integer idformulario) {
        this.idformulario = idformulario;
    }

    public Integer getDmdo_public() {
        return dmdo_public;
    }

    public void setDmdo_public(Integer dmdo_public) {
        this.dmdo_public = dmdo_public;
    }

    public Integer getGaceta() {
        return gaceta;
    }

    public void setGaceta(Integer gaceta) {
        this.gaceta = gaceta;
    }

    public Date getFechacreacion() {
        return fechacreacion;
    }

    public void setFechacreacion(Date fechacreacion) {
        this.fechacreacion = fechacreacion;
    }

    public Date getFechamod() {
        return fechamod;
    }

    public void setFechamod(Date fechamod) {
        this.fechamod = fechamod;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
  
    
}
