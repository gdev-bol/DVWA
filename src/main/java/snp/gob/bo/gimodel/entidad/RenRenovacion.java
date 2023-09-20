/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.entidad;

import java.util.Date;

/**
 * Entidad responsable de la Tabla regional
 *
 * @author Chano Rojas
 * @see RenRenovacionMapper
 * @version 1.0, 11/08/2016
 */
public class RenRenovacion {
  Long  idrenovacion;
  Long idsolicitudrenovacion;
  Long idlogtrans;
  Integer numero_renovacion;
  String serie_renovacion;
  Integer orden_renovacion;
  Long idclase_niza_renovacion;
  Date fecha_concesion;
  String titular;
  String apoderado;
  String tipo_marca;
  String tipo_genero;
  String signo_registrado;
  String etiqueta_renovacion;
  Integer numero_registro;
  String serie_registro;
    Long idclase_niza_actual;
  Integer sr_manual;
  Integer gestion_sr_manual;
  Date fecha_registro_manual;
  Date fecha_ingreso;
  String lista_producto_actual;
  String lista_producto_renovacion;
  String version_clase_niza;
  Date fecha_renovacion;
  Integer gestion_renovacion;
  String estado;

    public Long getIdrenovacion() {
        return idrenovacion;
    }

    public void setIdrenovacion(Long idrenovacion) {
        this.idrenovacion = idrenovacion;
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

    public Integer getNumero_renovacion() {
        return numero_renovacion;
    }

    public void setNumero_renovacion(Integer numero_renovacion) {
        this.numero_renovacion = numero_renovacion;
    }

    public String getSerie_renovacion() {
        return serie_renovacion;
    }

    public void setSerie_renovacion(String serie_renovacion) {
        this.serie_renovacion = serie_renovacion;
    }

    public Integer getOrden_renovacion() {
        return orden_renovacion;
    }

    public void setOrden_renovacion(Integer orden_renovacion) {
        this.orden_renovacion = orden_renovacion;
    }

    public Date getFecha_concesion() {
        return fecha_concesion;
    }

    public void setFecha_concesion(Date fecha_concesion) {
        this.fecha_concesion = fecha_concesion;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public String getApoderado() {
        return apoderado;
    }

    public void setApoderado(String apoderado) {
        this.apoderado = apoderado;
    }

    public String getTipo_marca() {
        return tipo_marca;
    }

    public void setTipo_marca(String tipo_marca) {
        this.tipo_marca = tipo_marca;
    }

    public String getTipo_genero() {
        return tipo_genero;
    }

    public void setTipo_genero(String tipo_genero) {
        this.tipo_genero = tipo_genero;
    }

    public String getSigno_registrado() {
        return signo_registrado;
    }

    public void setSigno_registrado(String signo_registrado) {
        this.signo_registrado = signo_registrado;
    }

    public String getEtiqueta_renovacion() {
        return etiqueta_renovacion;
    }

    public void setEtiqueta_renovacion(String etiqueta_renovacion) {
        this.etiqueta_renovacion = etiqueta_renovacion;
    }

    public Integer getNumero_registro() {
        return numero_registro;
    }

    public void setNumero_registro(Integer numero_registro) {
        this.numero_registro = numero_registro;
    }

    public String getSerie_registro() {
        return serie_registro;
    }

    public void setSerie_registro(String serie_registro) {
        this.serie_registro = serie_registro;
    }

    public Integer getSr_manual() {
        return sr_manual;
    }

    public void setSr_manual(Integer sr_manual) {
        this.sr_manual = sr_manual;
    }

    public Integer getGestion_sr_manual() {
        return gestion_sr_manual;
    }

    public void setGestion_sr_manual(Integer gestion_sr_manual) {
        this.gestion_sr_manual = gestion_sr_manual;
    }

    public Date getFecha_registro_manual() {
        return fecha_registro_manual;
    }

    public void setFecha_registro_manual(Date fecha_registro_manual) {
        this.fecha_registro_manual = fecha_registro_manual;
    }

    public Date getFecha_ingreso() {
        return fecha_ingreso;
    }

    public void setFecha_ingreso(Date fecha_ingreso) {
        this.fecha_ingreso = fecha_ingreso;
    }

    public String getLista_producto_actual() {
        return lista_producto_actual;
    }

    public void setLista_producto_actual(String lista_producto_actual) {
        this.lista_producto_actual = lista_producto_actual;
    }

    public String getLista_producto_renovacion() {
        return lista_producto_renovacion;
    }

    public void setLista_producto_renovacion(String lista_producto_renovacion) {
        this.lista_producto_renovacion = lista_producto_renovacion;
    }

    public String getVersion_clase_niza() {
        return version_clase_niza;
    }

    public void setVersion_clase_niza(String version_clase_niza) {
        this.version_clase_niza = version_clase_niza;
    }

    public Date getFecha_renovacion() {
        return fecha_renovacion;
    }

    public void setFecha_renovacion(Date fecha_renovacion) {
        this.fecha_renovacion = fecha_renovacion;
    }

    public Integer getGestion_renovacion() {
        return gestion_renovacion;
    }

    public void setGestion_renovacion(Integer gestion_renovacion) {
        this.gestion_renovacion = gestion_renovacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Long getIdclase_niza_renovacion() {
        return idclase_niza_renovacion;
    }

    public void setIdclase_niza_renovacion(Long idclase_niza_renovacion) {
        this.idclase_niza_renovacion = idclase_niza_renovacion;
    }

    public Long getIdclase_niza_actual() {
        return idclase_niza_actual;
    }

    public void setIdclase_niza_actual(Long idclase_niza_actual) {
        this.idclase_niza_actual = idclase_niza_actual;
    }
 
    
    
  
}