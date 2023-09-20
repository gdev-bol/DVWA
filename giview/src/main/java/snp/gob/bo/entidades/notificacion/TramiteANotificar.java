/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.entidades.notificacion;

/**
 *
 * @author levi
 */
public class TramiteANotificar {
    private Integer  numero;
    private String expediente;
    private String demandante;
    private String demandado;
    private String con;
    private String fojas;
    private String colorFila;

    public String getColorFila() {
        return colorFila;
    }

    public void setColorFila(String colorFila) {
        this.colorFila = colorFila;
    }

    
    
    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getCon() {
        return con;
    }

    public void setCon(String con) {
        this.con = con;
    }

    public String getFojas() {
        return fojas;
    }

    public void setFojas(String fojas) {
        this.fojas = fojas;
    }

  

  
 

    

    public String getExpediente() {
        return expediente;
    }

    public void setExpediente(String expediente) {
        this.expediente = expediente;
    }

    public String getDemandante() {
        return demandante;
    }

    public void setDemandante(String demandante) {
        this.demandante = demandante;
    }

    public String getDemandado() {
        return demandado;
    }

    public void setDemandado(String demandado) {
        this.demandado = demandado;
    }
    
    
}
