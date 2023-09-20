/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.entidad;

import java.io.Serializable;

/**
 * Entidad responsable de la Tabla regional
 *
 * @author Chano Rojas
 * @see RenRenovacionMapper
 * @version 1.0, 11/08/2016
 */
public class RenTitularRegistrado implements Serializable {

    Long idtitularregistrado;
    Long idsolicitudrenovacion;
    Long idlogtrans;
    String nombre_razonsocial;
    String primer_apellido;
    String segundo_apellido;
    String direccion;
    String estado;
    Long idSipi;

    public Long getIdtitularregistrado() {
        return idtitularregistrado;
    }

    public void setIdtitularregistrado(Long idtitularregistrado) {
        this.idtitularregistrado = idtitularregistrado;
    }

    public Long getIdsolicitudrenovacion() {
        return idsolicitudrenovacion;
    }

    public void setIdsolicitudrenovacion(Long idsolicitudrenovacion) {
        this.idsolicitudrenovacion = idsolicitudrenovacion;
    }

    public Long getIdlogtrans() {
        return idlogtrans;
    }

    public void setIdlogtrans(Long idlogtrans) {
        this.idlogtrans = idlogtrans;
    }

    public String getNombre_razonsocial() {
        return nombre_razonsocial;
    }

    public void setNombre_razonsocial(String nombre_razonsocial) {
        this.nombre_razonsocial = nombre_razonsocial;
    }

    public String getPrimer_apellido() {
        return primer_apellido;
    }

    public void setPrimer_apellido(String primer_apellido) {
        this.primer_apellido = primer_apellido;
    }

    public String getSegundo_apellido() {
        return segundo_apellido;
    }

    public void setSegundo_apellido(String segundo_apellido) {
        this.segundo_apellido = segundo_apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Long getIdSipi() {
        return idSipi;
    }

    public void setIdSipi(Long idSipi) {
        this.idSipi = idSipi;
    }

}
