/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.entidad;

import java.io.Serializable;

/**
 *
 * @author Ruben Ramirez
 * @version 1.0, 05/12/2016
 * 
 */
public class Sticker implements Serializable{

    Long idSticker;
    Long idLogTrans;
    String tipoTramite;
    Integer incremento;
    Integer primerNumeroAsignado;
    Integer ultimoNumeroAsignado;
    Integer gestion;
    String estado;

    public Long getIdSticker() {
        return idSticker;
    }

    public void setIdSticker(Long idSticker) {
        this.idSticker = idSticker;
    }

    public Long getIdLogTrans() {
        return idLogTrans;
    }

    public void setIdLogTrans(Long idLogTrans) {
        this.idLogTrans = idLogTrans;
    }

    public String getTipoTramite() {
        return tipoTramite;
    }

    public void setTipoTramite(String tipoTramite) {
        this.tipoTramite = tipoTramite;
    }

    public Integer getIncremento() {
        return incremento;
    }

    public void setIncremento(Integer incremento) {
        this.incremento = incremento;
    }

    public Integer getPrimerNumeroAsignado() {
        return primerNumeroAsignado;
    }

    public void setPrimerNumeroAsignado(Integer primerNumeroAsignado) {
        this.primerNumeroAsignado = primerNumeroAsignado;
    }

    public Integer getUltimoNumeroAsignado() {
        return ultimoNumeroAsignado;
    }

    public void setUltimoNumeroAsignado(Integer ultimoNumeroAsignado) {
        this.ultimoNumeroAsignado = ultimoNumeroAsignado;
    }

    public Integer getGestion() {
        return gestion;
    }

    public void setGestion(Integer gestion) {
        this.gestion = gestion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
