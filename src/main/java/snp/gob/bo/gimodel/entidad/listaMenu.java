/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.entidad;

import java.util.Date;

/**
 *
 * @author levi
 */
public class listaMenu {
    private String nombre;
    private String descripcion;
    private String submenu;
    private String url;
    private String acceso;

    private Date fecha_vig_ini;
    private Date fecha_vig_fin;
    private String estado;

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getSubmenu() {
        return submenu;
    }

    public void setSubmenu(String submenu) {
        this.submenu = submenu;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAcceso() {
        return acceso;
    }

    public void setAcceso(String acceso) {
        this.acceso = acceso;
    }


    public Date getFecha_vig_ini() {
        return fecha_vig_ini;
    }

    public void setFecha_vig_ini(Date fecha_vig_ini) {
        this.fecha_vig_ini = fecha_vig_ini;
    }

    public Date getFecha_vig_fin() {
        return fecha_vig_fin;
    }

    public void setFecha_vig_fin(Date fecha_vig_fin) {
        this.fecha_vig_fin = fecha_vig_fin;
    }


}
