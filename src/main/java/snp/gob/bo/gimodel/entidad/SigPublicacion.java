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
 * @author susana
 */
public class SigPublicacion implements Serializable {
  Long idpublicacion;
  Long idlogtrans;
  Long numero_gaceta;
  Date fecha_publicacion;
  Date fecha_envio;
  String mes;
  Integer gestion;
  Long inicio;
  Long fin;
  Long total;
  String observacion;
  String estado_publicacion;
  String estado;

    public Long getIdpublicacion() {
        return idpublicacion;
    }

    public void setIdpublicacion(Long idpublicacion) {
        this.idpublicacion = idpublicacion;
    }

    public Long getIdlogtrans() {
        return idlogtrans;
    }

    public void setIdlogtrans(Long idlogtrans) {
        this.idlogtrans = idlogtrans;
    }

    public Long getNumero_gaceta() {
        return numero_gaceta;
    }

    public void setNumero_gaceta(Long numero_gaceta) {
        this.numero_gaceta = numero_gaceta;
    }

    public Date getFecha_publicacion() {
        return fecha_publicacion;
    }

    public void setFecha_publicacion(Date fecha_publicacion) {
        this.fecha_publicacion = fecha_publicacion;
    }

    public Date getFecha_envio() {
        return fecha_envio;
    }

    public void setFecha_envio(Date fecha_envio) {
        this.fecha_envio = fecha_envio;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public Integer getGestion() {
        return gestion;
    }

    public void setGestion(Integer gestion) {
        this.gestion = gestion;
    }

    public Long getInicio() {
        return inicio;
    }

    public void setInicio(Long inicio) {
        this.inicio = inicio;
    }

    public Long getFin() {
        return fin;
    }

    public void setFin(Long fin) {
        this.fin = fin;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getEstado_publicacion() {
        return estado_publicacion;
    }

    public void setEstado_publicacion(String estado_publicacion) {
        this.estado_publicacion = estado_publicacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
  
  
  
}
