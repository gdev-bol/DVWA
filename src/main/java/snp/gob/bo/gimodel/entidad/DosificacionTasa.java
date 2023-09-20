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
public class DosificacionTasa {
   Long idDosificacionTasa;
   Long idTasa;
   Long idDosificacion;
   Long idRegional;
   Long idLogTrans;
   String serie;
   String tipoRecibo;
   String estado;

    public Long getIdDosificacionTasa() {
        return idDosificacionTasa;
    }

    public void setIdDosificacionTasa(Long idDosificacionTasa) {
        this.idDosificacionTasa = idDosificacionTasa;
    }

    public Long getIdTasa() {
        return idTasa;
    }

    public void setIdTasa(Long idTasa) {
        this.idTasa = idTasa;
    }

    public Long getIdDosificacion() {
        return idDosificacion;
    }

    public void setIdDosificacion(Long idDosificacion) {
        this.idDosificacion = idDosificacion;
    }

    public Long getIdRegional() {
        return idRegional;
    }

    public void setIdRegional(Long idRegional) {
        this.idRegional = idRegional;
    }

    public Long getIdLogTrans() {
        return idLogTrans;
    }

    public void setIdLogTrans(Long idLogTrans) {
        this.idLogTrans = idLogTrans;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getTipoRecibo() {
        return tipoRecibo;
    }

    public void setTipoRecibo(String tipoRecibo) {
        this.tipoRecibo = tipoRecibo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    
}