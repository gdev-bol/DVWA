/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package snp.gob.bo.gimodel.entidad;

import java.io.Serializable;

/**
 *
 * @author Luis Angel Quispe Limachi
 *  * @see OpoActividadplazo
 * @version 1.0, 06/09/2016
 */
public class OpoActividadplazo implements Serializable{
  Long idactividadplazo;
  Long idactividad;
  String descri_idactividadplazo;
  Long idactividadanterior;
  Long idlogtrans;
  Long idarea;
  Integer plazo;
  Integer sumarplazoanterior;

    public Long getIdactividadplazo() {
        return idactividadplazo;
    }

    public void setIdactividadplazo(Long idactividadplazo) {
        this.idactividadplazo = idactividadplazo;
    }

    public Long getIdactividad() {
        return idactividad;
    }

    public void setIdactividad(Long idactividad) {
        this.idactividad = idactividad;
    }

    public String getDescri_idactividadplazo() {
        return descri_idactividadplazo;
    }

    public void setDescri_idactividadplazo(String descri_idactividadplazo) {
        this.descri_idactividadplazo = descri_idactividadplazo;
    }

   

    public Long getIdactividadanterior() {
        return idactividadanterior;
    }

    public void setIdactividadanterior(Long idactividadanterior) {
        this.idactividadanterior = idactividadanterior;
    }

    public Long getIdlogtrans() {
        return idlogtrans;
    }

    public void setIdlogtrans(Long idlogtrans) {
        this.idlogtrans = idlogtrans;
    }

    public Long getIdarea() {
        return idarea;
    }

    public void setIdarea(Long idarea) {
        this.idarea = idarea;
    }

    public Integer getPlazo() {
        return plazo;
    }

    public void setPlazo(Integer plazo) {
        this.plazo = plazo;
    }

    public Integer getSumarplazoanterior() {
        return sumarplazoanterior;
    }

    public void setSumarplazoanterior(Integer sumarplazoanterior) {
        this.sumarplazoanterior = sumarplazoanterior;
    }
  
  
  
  
}
