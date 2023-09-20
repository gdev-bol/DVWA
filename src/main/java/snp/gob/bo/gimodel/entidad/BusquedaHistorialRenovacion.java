/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.entidad;

import java.util.Date;


/**
 * Entidad para la tabla temporal de la funcion lista_historial_renovacion() de la base de datos, los datos obtenidos se usan en el historial de renovaciones
 *
 * @author Susana Esobar Paz
 * @see BusquedaHistorialRenovacionMapper
 * @version 1.0, 08/12/2017
 */
public class BusquedaHistorialRenovacion {
    
    Long idmodificacion;
    Date  fecha_resolucion;
    String numero_resolucion;
    Integer orden;
    Long idsolicitudrenovacion;
    String renovacion;
    String resolucion_renovacion;
    Date fecha_resolucion_ren;
    Integer orden_ren;
    Date inicio_concesion;
   

    public Long getIdmodificacion() {
        return idmodificacion;
    }

    public void setIdmodificacion(Long idmodificacion) {
        this.idmodificacion = idmodificacion;
    }

    public Date getFecha_resolucion() {
        return fecha_resolucion;
    }

    public void setFecha_resolucion(Date fecha_resolucion) {
        this.fecha_resolucion = fecha_resolucion;
    }

    public String getNumero_resolucion() {
        return numero_resolucion;
    }

    public void setNumero_resolucion(String numero_resolucion) {
        this.numero_resolucion = numero_resolucion;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    
    public Long getIdsolicitudrenovacion() {
        return idsolicitudrenovacion;
    }

    public void setIdsolicitudrenovacion(Long idsolicitudrenovacion) {
        this.idsolicitudrenovacion = idsolicitudrenovacion;
    }

    public String getRenovacion() {
        return renovacion;
    }

    public void setRenovacion(String renovacion) {
        this.renovacion = renovacion;
    }

    public String getResolucion_renovacion() {
        return resolucion_renovacion;
    }

    public void setResolucion_renovacion(String resolucion_renovacion) {
        this.resolucion_renovacion = resolucion_renovacion;
    }

    public Date getFecha_resolucion_ren() {
        return fecha_resolucion_ren;
    }

    public void setFecha_resolucion_ren(Date fecha_resolucion_ren) {
        this.fecha_resolucion_ren = fecha_resolucion_ren;
    }

    public Integer getOrden_ren() {
        return orden_ren;
    }

    public void setOrden_ren(Integer orden_ren) {
        this.orden_ren = orden_ren;
    }

    public Date getInicio_concesion() {
        return inicio_concesion;
    }

    public void setInicio_concesion(Date inicio_concesion) {
        this.inicio_concesion = inicio_concesion;
    }

    
    
}
