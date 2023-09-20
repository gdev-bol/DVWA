/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.entidad;



/**
 * Entidad responsable de la Tabla UsuarioTramite
 *
 * @author Eddy Valero
 * @see UsuarioTramiteMapper
 * @version 1.0, 18/05/2017
 */
public class UsuarioTramite {
    
    Long idUsuarioTramite;
    String tipoTramite;
    Integer idUsuarioExterno;
    Long Id;
    Long idLogTrans;
    String estado;

    public Long getIdUsuarioTramite() {
        return idUsuarioTramite;
    }

    public void setIdUsuarioTramite(Long idUsuarioTramite) {
        this.idUsuarioTramite = idUsuarioTramite;
    }

    public String getTipoTramite() {
        return tipoTramite;
    }

    public void setTipoTramite(String tipoTramite) {
        this.tipoTramite = tipoTramite;
    }

    public Integer getIdUsuarioExterno() {
        return idUsuarioExterno;
    }

    public void setIdUsuarioExterno(Integer idUsuarioExterno) {
        this.idUsuarioExterno = idUsuarioExterno;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long Id) {
        this.Id = Id;
    }

    public Long getIdLogTrans() {
        return idLogTrans;
    }

    public void setIdLogTrans(Long idLogTrans) {
        this.idLogTrans = idLogTrans;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
      


}
