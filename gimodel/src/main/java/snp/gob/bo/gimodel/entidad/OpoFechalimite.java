/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package snp.gob.bo.gimodel.entidad;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Luis Angel Quispe Limachi
 *  * @see OpoFechalimite
 * @version 1.0, 06/09/2016
 */
public class OpoFechalimite implements Serializable{
    
  Long idfechalimite;
  Long idevento;
  Long idactividadplazo;
  Long idoposicion;
  Long idlogtrans;
  Integer orden;
  Date fechalimite;
  Integer orden_o;

    public Long getIdfechalimite() {
        return idfechalimite;
    }

    public void setIdfechalimite(Long idfechalimite) {
        this.idfechalimite = idfechalimite;
    }

    public Long getIdevento() {
        return idevento;
    }

    public void setIdevento(Long idevento) {
        this.idevento = idevento;
    }

    public Long getIdactividadplazo() {
        return idactividadplazo;
    }

    public void setIdactividadplazo(Long idactividadplazo) {
        this.idactividadplazo = idactividadplazo;
    }

    public Long getIdoposicion() {
        return idoposicion;
    }

    public void setIdoposicion(Long idoposicion) {
        this.idoposicion = idoposicion;
    }

    public Long getIdlogtrans() {
        return idlogtrans;
    }

    public void setIdlogtrans(Long idlogtrans) {
        this.idlogtrans = idlogtrans;
    }

        public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    public Date getFechalimite() {
        return fechalimite;
    }

    public void setFechalimite(Date fechalimite) {
        this.fechalimite = fechalimite;
    }

    public Integer getOrden_o() {
        return orden_o;
    }

    public void setOrden_o(Integer orden_o) {
        this.orden_o = orden_o;
    }
    
    
    
    
    
}
