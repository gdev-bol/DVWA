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
 *  * @see OpoActividad
 * @version 1.0, 06/09/2016
 */
public class OpoActividad implements Serializable{
  Long idactividad;
  Long idestado;
  Long idlogtrans;
  String descri_idactividad;
  String actividad;
  Integer orden;

    public Long getIdactividad() {
        return idactividad;
    }

    public void setIdactividad(Long idactividad) {
        this.idactividad = idactividad;
    }

    public Long getIdestado() {
        return idestado;
    }

    public void setIdestado(Long idestado) {
        this.idestado = idestado;
    }

    

    public Long getIdlogtrans() {
        return idlogtrans;
    }

    public void setIdlogtrans(Long idlogtrans) {
        this.idlogtrans = idlogtrans;
    }

    public String getDescri_idactividad() {
        return descri_idactividad;
    }

    public void setDescri_idactividad(String descri_idactividad) {
        this.descri_idactividad = descri_idactividad;
    }

    public String getActividad() {
        return actividad;
    }

    public void setActividad(String actividad) {
        this.actividad = actividad;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }
  
  
  
}
