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
 *  * @see OpoFlujoactividad
 * @version 1.0, 06/09/2016
 */
public class OpoFlujoactividad implements Serializable{
  Long idflujoactividad;
  Long idactividad;
  Long idlogtrans;
  String tipo;

    public Long getIdflujoactividad() {
        return idflujoactividad;
    }

    public void setIdflujoactividad(Long idflujoactividad) {
        this.idflujoactividad = idflujoactividad;
    }

    public Long getIdactividad() {
        return idactividad;
    }

    public void setIdactividad(Long idactividad) {
        this.idactividad = idactividad;
    }

    public Long getIdlogtrans() {
        return idlogtrans;
    }

    public void setIdlogtrans(Long idlogtrans) {
        this.idlogtrans = idlogtrans;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
  
  
  
}
