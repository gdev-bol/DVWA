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
 * @version 1.0, 26/10/2016
 */
public class UsuarioEtapa implements Serializable {
    Long idUsuarioEtapa;
    Long idLogTrans;
    Long idUsuario;
    Long idEtapa;
    String estado;

    public Long getIdUsuarioEtapa() {
        return idUsuarioEtapa;
    }

    public void setIdUsuarioEtapa(Long idUsuarioEtapa) {
        this.idUsuarioEtapa = idUsuarioEtapa;
    }

    public Long getIdLogTrans() {
        return idLogTrans;
    }

    public void setIdLogTrans(Long idLogTrans) {
        this.idLogTrans = idLogTrans;
    }
    
    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Long getIdEtapa() {
        return idEtapa;
    }

    public void setIdEtapa(Long idEtapa) {
        this.idEtapa = idEtapa;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
}
