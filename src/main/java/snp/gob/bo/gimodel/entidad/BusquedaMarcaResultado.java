/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package snp.gob.bo.gimodel.entidad;

import java.util.Date;


/**
 *
 * @author Eddy Valero
 * @version 1.0, 17/10/2016
 */
public class BusquedaMarcaResultado {
    
    //String datoBusqueda;
    Long idSignoMarca;
    Long sm;
    int numero;
    int gestion;      
    String marca;
    int clase;
    int parecido;
    Date fechaSolicitud; //-------
    Long nroPublicacion;
    Long nroRegistro;
    String serieRegistro;
    String solicitante;
    String apoderado;
    String estado;
    Date fechaRegistro;
    Date srFechaResolucion;
    String srTitular;
    String sr;
    int gestionSr;

    public Long getIdSignoMarca() {
        return idSignoMarca;
    }

    public void setIdSignoMarca(Long idSignoMarca) {
        this.idSignoMarca = idSignoMarca;
    }

    public Long getSm() {
        return sm;
    }

    public void setSm(Long sm) {
        this.sm = sm;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getGestion() {
        return gestion;
    }

    public void setGestion(int gestion) {
        this.gestion = gestion;
    }
    
    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getClase() {
        return clase;
    }

    public void setClase(int clase) {
        this.clase = clase;
    }

    public int getParecido() {
        return parecido;
    }

    public void setParecido(int parecido) {
        this.parecido = parecido;
    }
    
    public Date getFechaSolicitud() {
        //DateFormat dateFormat=new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        //return dateFormat.format(fechaSolicitud);
        return fechaSolicitud;
    }

    public void setFechaSolicitud(Date fechaSolicitud) {
        //DateFormat dateFormat=new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        //this.fechaSolicitud = dateFormat.format(fechaSolicitud);
        this.fechaSolicitud = fechaSolicitud;
    }

    public Long getNroPublicacion() {
        return nroPublicacion;
    }

    public void setNroPublicacion(Long nroPublicacion) {
        this.nroPublicacion = nroPublicacion;
    }

    public Long getNroRegistro() {
        return nroRegistro;
    }

    public void setNroRegistro(Long nroRegistro) {
        this.nroRegistro = nroRegistro;
    }

    public String getSerieRegistro() {
        return serieRegistro;
    }

    public void setSerieRegistro(String serieRegistro) {
        this.serieRegistro = serieRegistro;
    }

    public String getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(String solicitante) {
        this.solicitante = solicitante;
    }

    public String getApoderado() {
        return apoderado;
    }

    public void setApoderado(String apoderado) {
        this.apoderado = apoderado;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Date getSrFechaResolucion() {
        return srFechaResolucion;
    }

    public void setSrFechaResolucion(Date srFechaResolucion) {
        this.srFechaResolucion = srFechaResolucion;
    }

    public String getSrTitular() {
        return srTitular;
    }

    public void setSrTitular(String srTitular) {
        this.srTitular = srTitular;
    }

    public String getSr() {
        return sr;
    }

    public void setSr(String sr) {
        this.sr = sr;
    }

    public int getGestionSr() {
        return gestionSr;
    }

    public void setGestionSr(int gestionSr) {
        this.gestionSr = gestionSr;
    }
   
}
