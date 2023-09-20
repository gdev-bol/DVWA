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
 *  * @see OpoMarcademandada
 * @version 1.0, 06/09/2016
 */
public class OpoMarcademandada implements Serializable{
   
   Long idmarcademandada;
   Long idoposicion;
   Long idlogtrans;
   Integer nro_opo;
   Integer dmdo_public;
   Integer gaceta;   
   String dmdo_clase;
   Date fecha_public;
   String dmdo_marca_lnv;
   String dmdo_sm;   
   Date fec_lim;   
   Boolean verif;
   String estado;
  

    public Long getIdmarcademandada() {
        return idmarcademandada;
    }

    public void setIdmarcademandada(Long idmarcademandada) {
        this.idmarcademandada = idmarcademandada;
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

    public Integer getNro_opo() {
        return nro_opo;
    }

public void setNro_opo(Integer nro_opo) {
        this.nro_opo = nro_opo;
    }

    public Integer getDmdo_public() {
        return dmdo_public;
    }

    public void setDmdo_public(Integer dmdo_public) {
        this.dmdo_public = dmdo_public;
    }

    public Integer getGaceta() {
        return gaceta;
    }

    public void setGaceta(Integer gaceta) {
        this.gaceta = gaceta;
    }
   
    public String getDmdo_clase() {
        return dmdo_clase;
    }

    public void setDmdo_clase(String dmdo_clase) {
        this.dmdo_clase = dmdo_clase;
    }

    public Date getFecha_public() {
        return fecha_public;
    }

    public void setFecha_public(Date fecha_public) {
        this.fecha_public = fecha_public;
    }

    public String getDmdo_marca_lnv() {
        return dmdo_marca_lnv;
    }

    public void setDmdo_marca_lnv(String dmdo_marca_lnv) {
        this.dmdo_marca_lnv = dmdo_marca_lnv;
    }

    public String getDmdo_sm() {
        return dmdo_sm;
    }

    public void setDmdo_sm(String dmdo_sm) {
        this.dmdo_sm = dmdo_sm;
    }
 

    public Date getFec_lim() {
        return fec_lim;
    }

    public void setFec_lim(Date fec_lim) {
        this.fec_lim = fec_lim;
    }

   

    public Boolean getVerif() {
        return verif;
    }

    public void setVerif(Boolean verif) {
        this.verif = verif;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

      
    
}
