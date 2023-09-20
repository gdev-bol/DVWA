/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.entidades.bean.registro;

/**
 *
 * @author Ruben Ramirez
 * @version 1.0, 30/12/2016
 */
public class BusquedaRegistroPojo {

    String signo;
    String titular;
    String smDescripcion;
    String fechaIngreso;
    Long numeroRegistro;
    String serie;
    String fechaRegistro;
    String tipoGeneroDescripcion;
    String estadoRegistro;

    public String getSigno() {
        return signo;
    }

    public void setSigno(String signo) {
        this.signo = signo;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public String getSmDescripcion() {
        return smDescripcion;
    }

    public void setSmDescripcion(String smDescripcion) {
        this.smDescripcion = smDescripcion;
    }

    public String getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(String fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Long getNumeroRegistro() {
        return numeroRegistro;
    }

    public void setNumeroRegistro(Long numeroRegistro) {
        this.numeroRegistro = numeroRegistro;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getTipoGeneroDescripcion() {
        return tipoGeneroDescripcion;
    }

    public void setTipoGeneroDescripcion(String tipoGeneroDescripcion) {
        this.tipoGeneroDescripcion = tipoGeneroDescripcion;
    }

    public String getEstadoRegistro() {
        return estadoRegistro;
    }

    public void setEstadoRegistro(String estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
    }
    
}
