/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.solicitudes.entidades;

import java.util.Date;

/**
 *
 * @author susana
 */
public class ModTipoSignos {

    Long modificacion_id;
    String tiposigno;
    String descripcionotro;
    String estado;
    Date fechacreacion;
    Date fechamod;

    public Long getModificacion_id() {
        return modificacion_id;
    }

    public void setModificacion_id(Long modificacion_id) {
        this.modificacion_id = modificacion_id;
    }

    public String getTiposigno() {
        return tiposigno;
    }

    public void setTiposigno(String tiposigno) {
        this.tiposigno = tiposigno;
    }

    public String getDescripcionotro() {
        return descripcionotro;
    }

    public void setDescripcionotro(String descripcionotro) {
        this.descripcionotro = descripcionotro;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFechacreacion() {
        return fechacreacion;
    }

    public void setFechacreacion(Date fechacreacion) {
        this.fechacreacion = fechacreacion;
    }

    public Date getFechamod() {
        return fechamod;
    }

    public void setFechamod(Date fechamod) {
        this.fechamod = fechamod;
    }
    
    
}
