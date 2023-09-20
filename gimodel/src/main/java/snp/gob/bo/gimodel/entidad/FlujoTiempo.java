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
public class FlujoTiempo implements Serializable{
    private Long idflujotiempo;
    private Long idetapa;
    private Integer tiempo;
    private Integer orden;
    String estado;

    public Long getIdflujotiempo() {
        return idflujotiempo;
    }

    public void setIdflujotiempo(Long idflujotiempo) {
        this.idflujotiempo = idflujotiempo;
    }

    public Long getIdetapa() {
        return idetapa;
    }

    public void setIdetapa(Long idetapa) {
        this.idetapa = idetapa;
    }

 

    public Integer getTiempo() {
        return tiempo;
    }

    public void setTiempo(Integer tiempo) {
        this.tiempo = tiempo;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
    
}
