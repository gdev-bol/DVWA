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
public class ModTipoSigno implements Serializable {
    Long idtiposigno;
    Long idmodificacion;
    Long idlogtrans;
    String tipo_signo;
    String descripcion_otro;
    String estado;

    public Long getIdtiposigno() {
        return idtiposigno;
    }

    public void setIdtiposigno(Long idtiposigno) {
        this.idtiposigno = idtiposigno;
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

    public String getTipo_signo() {
        return tipo_signo;
    }

    public void setTipo_signo(String tipo_signo) {
        this.tipo_signo = tipo_signo;
    }

    public String getDescripcion_otro() {
        return descripcion_otro;
    }

    public void setDescripcion_otro(String descripcion_otro) {
        this.descripcion_otro = descripcion_otro;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
}
