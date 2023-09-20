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
 *  * @see OpoEstado
 * @version 1.0, 06/09/2016
 */
public class OpoEstado implements Serializable{
   
  Long idestado;
  Long idarea;
  Long idlogtrans;
  String descri_idestadooposicion;
  String estado;
  Integer orden;

    public Long getIdestado() {
        return idestado;
    }

    public void setIdestado(Long idestado) {
        this.idestado = idestado;
    }

  

    public Long getIdarea() {
        return idarea;
    }

    public void setIdarea(Long idarea) {
        this.idarea = idarea;
    }

    public Long getIdlogtrans() {
        return idlogtrans;
    }

    public void setIdlogtrans(Long idlogtrans) {
        this.idlogtrans = idlogtrans;
    }

    public String getDescri_idestadooposicion() {
        return descri_idestadooposicion;
    }

    public void setDescri_idestadooposicion(String descri_idestadooposicion) {
        this.descri_idestadooposicion = descri_idestadooposicion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }
  
  
    
    
    
}
