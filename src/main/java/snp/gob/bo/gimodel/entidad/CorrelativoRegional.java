/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.entidad;

/**
 * Entidad responsable de la Tabla regional
 *
 * @author Chano Rojas
 * @see DosificacionMapper
 * @version 1.0, 11/08/2016
 */
public class CorrelativoRegional {
 Long idcorrelativoregional;
 Long  idregional;
 Long  idcorrelativo;
 Long  idlogtrans;
 String  tipo_tramite;
 String  estado;

    public Long getIdcorrelativoregional() {
        return idcorrelativoregional;
    }

    public void setIdcorrelativoregional(Long idcorrelativoregional) {
        this.idcorrelativoregional = idcorrelativoregional;
    }

    public Long getIdregional() {
        return idregional;
    }

    public void setIdregional(Long idregional) {
        this.idregional = idregional;
    }

    public Long getIdcorrelativo() {
        return idcorrelativo;
    }

    public void setIdcorrelativo(Long idcorrelativo) {
        this.idcorrelativo = idcorrelativo;
    }

    public Long getIdlogtrans() {
        return idlogtrans;
    }

    public void setIdlogtrans(Long idlogtrans) {
        this.idlogtrans = idlogtrans;
    }

    public String getTipo_tramite() {
        return tipo_tramite;
    }

    public void setTipo_tramite(String tipo_tramite) {
        this.tipo_tramite = tipo_tramite;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
 
 
 
 
 
 
  
  
}