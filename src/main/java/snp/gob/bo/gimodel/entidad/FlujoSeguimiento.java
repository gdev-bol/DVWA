/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.entidad;

import java.io.Serializable;

/**
 *
 * @author levi
 */
public class FlujoSeguimiento implements Serializable{
    
    private Long idflujoseguimiento;
    private Integer idflujo;      
    private Long idetapa;
    private Integer orden;
    private String etapa;      
    private String tipo_etapa;
    private String estado;

    public Long getIdflujoseguimiento() {
        return idflujoseguimiento;
    }

    public void setIdflujoseguimiento(Long idflujoseguimiento) {
        this.idflujoseguimiento = idflujoseguimiento;
    }

    public Integer getIdflujo() {
        return idflujo;
    }

    public void setIdflujo(Integer idflujo) {
        this.idflujo = idflujo;
    }

    public Long getIdetapa() {
        return idetapa;
    }

    public void setIdetapa(Long idetapa) {
        this.idetapa = idetapa;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    public String getEtapa() {
        return etapa;
    }

    public void setEtapa(String etapa) {
        this.etapa = etapa;
    }

    public String getTipo_etapa() {
        return tipo_etapa;
    }

    public void setTipo_etapa(String tipo_etapa) {
        this.tipo_etapa = tipo_etapa;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
   
    
    
    
}
