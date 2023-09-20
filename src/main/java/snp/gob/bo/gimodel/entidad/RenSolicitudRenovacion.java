/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.entidad;

import java.io.Serializable;
import java.util.Date;

/**
 * Entidad responsable de la Tabla regional
 *
 * @author Chano Rojas
 * @see RenSolictudRenovacionMapper
 * @version 1.0, 11/08/2016
 */
public class RenSolicitudRenovacion implements Serializable {

    Long idsolicitudrenovacion;
    Long idlogtrans;
    Long id_recibo_solicitud;
    Long id_recibo_titulo;
    Long sr;
    Integer gestion_sr;
    Date fecha_ingreso;
    Long numero_formulario;
    String estado_renovacion;
    String ubicacion_renovacion;
    Integer numero_ultima_renovacion;
    String serie_ultima_renovacion;
    Integer numero_penultima_renovacion;
    String serie_penultima_renovacion;
    String oficina;
    Long numero_recibo;
    String serie_recibo;
    Long numero_titulo;
    String serie_titulo;
    Long  idclase_niza_reclasificacion;
    String lista_productos_limitacion;
    Long sm;
    String signo_registrado;
    Long idclase_niza_registrado;
    String tipo_genero;
    Long numero_registro_registrado;
    String serie_registro_registrado;
    Date fecha_registro_registrado;
    String marca_acomp;
    Long reg_lc_registrado;
    String serie_lc_registrado;
    Date fecha_lc_registrado;

    String estado;

    public Long getIdsolicitudrenovacion() {
        return idsolicitudrenovacion;
    }

    public void setIdsolicitudrenovacion(Long idsolicitudrenovacion) {
        this.idsolicitudrenovacion = idsolicitudrenovacion;
    }

    public Long getNumero_formulario() {
        return numero_formulario;
    }

    public void setNumero_formulario(Long numero_formulario) {
        this.numero_formulario = numero_formulario;
    }


    public Long getIdlogtrans() {
        return idlogtrans;
    }

    public void setIdlogtrans(Long idlogtrans) {
        this.idlogtrans = idlogtrans;
    }

    public Long getId_recibo_solicitud() {
        return id_recibo_solicitud;
    }

    public void setId_recibo_solicitud(Long id_recibo_solicitud) {
        this.id_recibo_solicitud = id_recibo_solicitud;
    }

    public Long getId_recibo_titulo() {
        return id_recibo_titulo;
    }

    public void setId_recibo_titulo(Long id_recibo_titulo) {
        this.id_recibo_titulo = id_recibo_titulo;
    }

    public Long getSr() {
        return sr;
    }

    public void setSr(Long sr) {
        this.sr = sr;
    }

    public Date getFecha_ingreso() {
        return fecha_ingreso;
    }

    public void setFecha_ingreso(Date fecha_ingreso) {
        this.fecha_ingreso = fecha_ingreso;
    }

    public String getEstado_renovacion() {
        return estado_renovacion;
    }

    public void setEstado_renovacion(String estado_renovacion) {
        this.estado_renovacion = estado_renovacion;
    }

    public String getUbicacion_renovacion() {
        return ubicacion_renovacion;
    }

    public void setUbicacion_renovacion(String ubicacion_renovacion) {
        this.ubicacion_renovacion = ubicacion_renovacion;
    }

    public Integer getNumero_ultima_renovacion() {
        return numero_ultima_renovacion;
    }

    public void setNumero_ultima_renovacion(Integer numero_ultima_renovacion) {
        this.numero_ultima_renovacion = numero_ultima_renovacion;
    }

    public String getSerie_ultima_renovacion() {
        return serie_ultima_renovacion;
    }

    public void setSerie_ultima_renovacion(String serie_ultima_renovacion) {
        this.serie_ultima_renovacion = serie_ultima_renovacion;
    }

    public String getSerie_penultima_renovacion() {
        return serie_penultima_renovacion;
    }

    public void setSerie_penultima_renovacion(String serie_penultima_renovacion) {
        this.serie_penultima_renovacion = serie_penultima_renovacion;
    }

    public String getOficina() {
        return oficina;
    }

    public void setOficina(String oficina) {
        this.oficina = oficina;
    }

    public Long getNumero_recibo() {
        return numero_recibo;
    }

    public void setNumero_recibo(Long numero_recibo) {
        this.numero_recibo = numero_recibo;
    }

    public String getSerie_recibo() {
        return serie_recibo;
    }

    public void setSerie_recibo(String serie_recibo) {
        this.serie_recibo = serie_recibo;
    }

    public Long getNumero_titulo() {
        return numero_titulo;
    }

    public void setNumero_titulo(Long numero_titulo) {
        this.numero_titulo = numero_titulo;
    }

    public String getSerie_titulo() {
        return serie_titulo;
    }

    public void setSerie_titulo(String serie_titulo) {
        this.serie_titulo = serie_titulo;
    }

    public String getLista_productos_limitacion() {
        return lista_productos_limitacion;
    }

    public void setLista_productos_limitacion(String lista_productos_limitacion) {
        this.lista_productos_limitacion = lista_productos_limitacion;
    }

    public Long getSm() {
        return sm;
    }

    public void setSm(Long sm) {
        this.sm = sm;
    }

    public String getSigno_registrado() {
        return signo_registrado;
    }

    public void setSigno_registrado(String signo_registrado) {
        this.signo_registrado = signo_registrado;
    }

    public String getTipo_genero() {
        return tipo_genero;
    }

    public void setTipo_genero(String tipo_genero) {
        this.tipo_genero = tipo_genero;
    }

    public Long getNumero_registro_registrado() {
        return numero_registro_registrado;
    }

    public void setNumero_registro_registrado(Long numero_registro_registrado) {
        this.numero_registro_registrado = numero_registro_registrado;
    }

    public String getSerie_registro_registrado() {
        return serie_registro_registrado;
    }

    public void setSerie_registro_registrado(String serie_registro_registrado) {
        this.serie_registro_registrado = serie_registro_registrado;
    }

    public Date getFecha_registro_registrado() {
        return fecha_registro_registrado;
    }

    public void setFecha_registro_registrado(Date fecha_registro_registrado) {
        this.fecha_registro_registrado = fecha_registro_registrado;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getMarca_acomp() {
        return marca_acomp;
    }

    public void setMarca_acomp(String marca_acomp) {
        this.marca_acomp = marca_acomp;
    }

    public Long getReg_lc_registrado() {
        return reg_lc_registrado;
    }

    public void setReg_lc_registrado(Long reg_lc_registrado) {
        this.reg_lc_registrado = reg_lc_registrado;
    }

    public String getSerie_lc_registrado() {
        return serie_lc_registrado;
    }

    public void setSerie_lc_registrado(String serie_lc_registrado) {
        this.serie_lc_registrado = serie_lc_registrado;
    }

    public Date getFecha_lc_registrado() {
        return fecha_lc_registrado;
    }

    public void setFecha_lc_registrado(Date fecha_lc_registrado) {
        this.fecha_lc_registrado = fecha_lc_registrado;
    }

    public Integer getGestion_sr() {
        return gestion_sr;
    }

    public void setGestion_sr(Integer gestion_sr) {
        this.gestion_sr = gestion_sr;
    }

    public Integer getNumero_penultima_renovacion() {
        return numero_penultima_renovacion;
    }

    public void setNumero_penultima_renovacion(Integer numero_penultima_renovacion) {
        this.numero_penultima_renovacion = numero_penultima_renovacion;
    }

    public Long getIdclase_niza_reclasificacion() {
        return idclase_niza_reclasificacion;
    }

    public void setIdclase_niza_reclasificacion(Long idclase_niza_reclasificacion) {
        this.idclase_niza_reclasificacion = idclase_niza_reclasificacion;
    }

    public Long getIdclase_niza_registrado() {
        return idclase_niza_registrado;
    }

    public void setIdclase_niza_registrado(Long idclase_niza_registrado) {
        this.idclase_niza_registrado = idclase_niza_registrado;
    }



}
