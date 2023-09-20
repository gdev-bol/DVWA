/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.entidad;

import java.io.Serializable;

/**
 *
 * @author susana
 */
public class ModTitularLicenciatarioRegistrado implements Serializable {

    Long idtitularlicenciatarioregistrado;
    Long idmodificacion;
    Long idlogtrans;
    String tipo_persona_registrado;
    String tipo_titular;
    String nombre_razonsocial;
    String primer_apellido;
    String segundo_apellido;
    String direccion;
    String estado;
    Long idSipi;

    public Long getIdtitularlicenciatarioregistrado() {
        return idtitularlicenciatarioregistrado;
    }

    public void setIdtitularlicenciatarioregistrado(Long idtitularlicenciatarioregistrado) {
        this.idtitularlicenciatarioregistrado = idtitularlicenciatarioregistrado;
    }

    public Long getIdmodificacion() {
        return idmodificacion;
    }

    public void setIdmodificacion(Long idmodificacion) {
        this.idmodificacion = idmodificacion;
    }

    public Long getIdlogtrans() {
        return idlogtrans;
    }

    public void setIdlogtrans(Long idlogtrans) {
        this.idlogtrans = idlogtrans;
    }

    public String getTipo_persona_registrado() {
        return tipo_persona_registrado;
    }

    public void setTipo_persona_registrado(String tipo_persona_registrado) {
        this.tipo_persona_registrado = tipo_persona_registrado;
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

    public String getTipo_titular() {
        return tipo_titular;
    }

    public void setTipo_titular(String tipo_titular) {
        this.tipo_titular = tipo_titular;
    }

    public Long getIdSipi() {
        return idSipi;
    }

    public void setIdSipi(Long idSipi) {
        this.idSipi = idSipi;
    }

    @Override
    public String toString() {
        return "|" + "idtitularlicenciatarioregistrado=" + idtitularlicenciatarioregistrado
                + "|" + "idmodificacion=" + idmodificacion
              //  + "|" + "idlogtrans=" + idlogtrans
                + "|" + "tipo_persona_registrado=" + tipo_persona_registrado
                + "|" + "tipo_titular=" + tipo_titular
                + "|" + "nombre_razonsocial=" + nombre_razonsocial
                + "|" + "primer_apellido=" + primer_apellido
                + "|" + "segundo_apellido=" + segundo_apellido
                + "|" + "direccion=" + direccion
                + "|" + "estado=" + estado + "|";
    }
}
