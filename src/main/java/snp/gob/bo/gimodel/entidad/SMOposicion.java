/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package snp.gob.bo.gimodel.entidad;

import java.util.Date;

/**
 *
 * @author desarrollo
 */
public class SMOposicion {
     Long numero_publicacion;
    String sm_desc;
    Date fecha_ingreso;
    String marca;
    Integer clase;
    String tipo_signo;
    String tipo_marca;
    Integer numero_seccion;
    String subseccion;

    public Long getNumero_publicacion() {
        return numero_publicacion;
    }

    public void setNumero_publicacion(Long numero_publicacion) {
        this.numero_publicacion = numero_publicacion;
    }

    public String getSm_desc() {
        return sm_desc;
    }

    public void setSm_desc(String sm_desc) {
        this.sm_desc = sm_desc;
    }

    public Date getFecha_ingreso() {
        return fecha_ingreso;
    }

    public void setFecha_ingreso(Date fecha_ingreso) {
        this.fecha_ingreso = fecha_ingreso;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Integer getClase() {
        return clase;
    }

    public void setClase(Integer clase) {
        this.clase = clase;
    }

    public String getTipo_signo() {
        return tipo_signo;
    }

    public void setTipo_signo(String tipo_signo) {
        this.tipo_signo = tipo_signo;
    }

    public String getTipo_marca() {
        return tipo_marca;
    }

    public void setTipo_marca(String tipo_marca) {
        this.tipo_marca = tipo_marca;
    }

    public Integer getNumero_seccion() {
        return numero_seccion;
    }

    public void setNumero_seccion(Integer numero_seccion) {
        this.numero_seccion = numero_seccion;
    }

    public String getSubseccion() {
        return subseccion;
    }

    public void setSubseccion(String subseccion) {
        this.subseccion = subseccion;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
