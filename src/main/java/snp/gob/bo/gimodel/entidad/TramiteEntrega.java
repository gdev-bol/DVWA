/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.entidad;

import java.util.Date;

/**
 *
 * @author luisangel
 */
public class TramiteEntrega {

    Long idtramiteentrega;
    Long idlogtrans;
    Long idprocurador;
    Long idtramite;
    String tipo_tramite;
    Integer numerobloque;
    Integer gestionbloque;
    Long sm;
    String sigla;
    Integer numerotramite;
    Integer gestion;
    String extension;
    Long numero_registro;
    String serie_registro;
    Long numero_publicacion;
    String marca;
    Integer clase_niza;
    String estadoanteriortramite;
    String ubicacionanterior;
    Long idusuario;
    Long idregionalentrega;
    Date fecha_entrega;
    String estado;

    public Long getIdtramiteentrega() {
        return idtramiteentrega;
    }

    public void setIdtramiteentrega(Long idtramiteentrega) {
        this.idtramiteentrega = idtramiteentrega;
    }

    public Long getIdlogtrans() {
        return idlogtrans;
    }

    public void setIdlogtrans(Long idlogtrans) {
        this.idlogtrans = idlogtrans;
    }

    public Long getIdprocurador() {
        return idprocurador;
    }

    public void setIdprocurador(Long idprocurador) {
        this.idprocurador = idprocurador;
    }

    public Long getIdtramite() {
        return idtramite;
    }

    public void setIdtramite(Long idtramite) {
        this.idtramite = idtramite;
    }

    public String getTipo_tramite() {
        return tipo_tramite;
    }

    public void setTipo_tramite(String tipo_tramite) {
        this.tipo_tramite = tipo_tramite;
    }

    public Integer getNumerobloque() {
        return numerobloque;
    }

    public void setNumerobloque(Integer numerobloque) {
        this.numerobloque = numerobloque;
    }

    public Integer getGestionbloque() {
        return gestionbloque;
    }

    public void setGestionbloque(Integer gestionbloque) {
        this.gestionbloque = gestionbloque;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public Integer getNumerotramite() {
        return numerotramite;
    }

    public void setNumerotramite(Integer numerotramite) {
        this.numerotramite = numerotramite;
    }

    public Integer getGestion() {
        return gestion;
    }

    public void setGestion(Integer gestion) {
        this.gestion = gestion;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public Long getNumero_registro() {
        return numero_registro;
    }

    public void setNumero_registro(Long numero_registro) {
        this.numero_registro = numero_registro;
    }

    public String getSerie_registro() {
        return serie_registro;
    }

    public void setSerie_registro(String serie_registro) {
        this.serie_registro = serie_registro;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Integer getClase_niza() {
        return clase_niza;
    }

    public void setClase_niza(Integer clase_niza) {
        this.clase_niza = clase_niza;
    }

    public String getEstadoanteriortramite() {
        return estadoanteriortramite;
    }

    public void setEstadoanteriortramite(String estadoanteriortramite) {
        this.estadoanteriortramite = estadoanteriortramite;
    }

    public String getUbicacionanterior() {
        return ubicacionanterior;
    }

    public void setUbicacionanterior(String ubicacionanterior) {
        this.ubicacionanterior = ubicacionanterior;
    }

    public Long getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Long idusuario) {
        this.idusuario = idusuario;
    }

    public Long getIdregionalentrega() {
        return idregionalentrega;
    }

    public void setIdregionalentrega(Long idregionalentrega) {
        this.idregionalentrega = idregionalentrega;
    }
    public Date getFecha_entrega() {
        return fecha_entrega;
    }

    public void setFecha_entrega(Date fecha_entrega) {
        this.fecha_entrega = fecha_entrega;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Long getSm() {
        return sm;
    }

    public void setSm(Long sm) {
        this.sm = sm;
    }

    public Long getNumero_publicacion() {
        return numero_publicacion;
    }

    public void setNumero_publicacion(Long numero_publicacion) {
        this.numero_publicacion = numero_publicacion;
    }
    
    
    

}
