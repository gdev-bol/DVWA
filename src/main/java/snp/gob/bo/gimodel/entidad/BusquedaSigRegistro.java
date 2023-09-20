/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.entidad;

import java.util.Date;

/**
 *
 * @author susana
 */
public class BusquedaSigRegistro {

    Long posicion;
    Long idregistro;
    Date fecha_registro;
    Long numero_registro;
    String serie;
    Long numero_resolucion;
    String sm_descripcion;
    String signo;
    Integer clase;

    public Long getPosicion() {
        return posicion;
    }

    public void setPosicion(Long posicion) {
        this.posicion = posicion;
    }

    public Date getFecha_registro() {
        return fecha_registro;
    }

    public void setFecha_registro(Date fecha_registro) {
        this.fecha_registro = fecha_registro;
    }
    
    public Long getNumero_registro() {
        return numero_registro;
    }

    public void setNumero_registro(Long numero_registro) {
        this.numero_registro = numero_registro;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public Long getNumero_resolucion() {
        return numero_resolucion;
    }

    public void setNumero_resolucion(Long numero_resolucion) {
        this.numero_resolucion = numero_resolucion;
    }

    public String getSm_descripcion() {
        return sm_descripcion;
    }

    public void setSm_descripcion(String sm_descripcion) {
        this.sm_descripcion = sm_descripcion;
    }

    public String getSigno() {
        return signo;
    }

    public void setSigno(String signo) {
        this.signo = signo;
    }

    public Integer getClase() {
        return clase;
    }

    public void setClase(Integer clase) {
        this.clase = clase;
    }

    public Long getIdregistro() {
        return idregistro;
    }

    public void setIdregistro(Long idregistro) {
        this.idregistro = idregistro;
    }    
}
