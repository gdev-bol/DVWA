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
 * @author Luis Angel Quispe Limachi
 * @see Oposicion
 * @version 1.0, 06/09/2016
 */
public class Oposicion implements Serializable{

    Long idoposicion;
    Long nro_opo;
    Integer gaceta;
    Date fecha_pub;
    Date fecha_presentacion;
    String ubicacion;
    String observacion;
    String estado;
    Integer orden_o;
    Long id_estado;
    String estadoopo;
    String oficina;
    Long numero_formulario; 
    Integer gestion_opo;
    Long codigo_opo;
    String ingreso_opo;

    public Long getIdoposicion() {
        return idoposicion;
    }

    public void setIdoposicion(Long idoposicion) {
        this.idoposicion = idoposicion;
    }

    public Long getNro_opo() {
        return nro_opo;
    }

    public void setNro_opo(Long nro_opo) {
        this.nro_opo = nro_opo;
    }

    public Integer getGaceta() {
        return gaceta;
    }

    public void setGaceta(Integer gaceta) {
        this.gaceta = gaceta;
    }

    public Date getFecha_pub() {
        return fecha_pub;
    }

    public void setFecha_pub(Date fecha_pub) {
        this.fecha_pub = fecha_pub;
    }

    public Date getFecha_presentacion() {
        return fecha_presentacion;
    }

    public void setFecha_presentacion(Date fecha_presentacion) {
        this.fecha_presentacion = fecha_presentacion;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    
    
    public Long getId_estado() {
        return id_estado;
    }

    public void setId_estado(Long id_estado) {
        this.id_estado = id_estado;
    }

    public Integer getOrden_o() {
        return orden_o;
    }

    public void setOrden_o(Integer orden_o) {
        this.orden_o = orden_o;
    }

    public String getEstadoopo() {
        return estadoopo;
    }

    public void setEstadoopo(String estadoopo) {
        this.estadoopo = estadoopo;
    }

    public Long getNumero_formulario() {
        return numero_formulario;
    }

    public void setNumero_formulario(Long numero_formulario) {
        this.numero_formulario = numero_formulario;
    }

    public Integer getGestion_opo() {
        return gestion_opo;
    }

    public void setGestion_opo(Integer gestion_opo) {
        this.gestion_opo = gestion_opo;
    }

    public Long getCodigo_opo() {
        return codigo_opo;
    }

    public void setCodigo_opo(Long codigo_opo) {
        this.codigo_opo = codigo_opo;
    }

    public String getIngreso_opo() {
        return ingreso_opo;
    }

    public void setIngreso_opo(String ingreso_opo) {
        this.ingreso_opo = ingreso_opo;
    }

    public String getOficina() {
        return oficina;
    }

    public void setOficina(String oficina) {
        this.oficina = oficina;
    }
        
}
