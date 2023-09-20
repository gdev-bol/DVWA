package snp.gob.bo.gimodel.entidad;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Entidad responsable de la Tabla recibo
 *
 * @author Ruben Ramirez
 * @version 1.0, /10/2017
 */
public class ReciboPorPeriodo implements Serializable{
  String fecha;
  String estadoRecibo;
  Long idtasa;
  Long numeroRecibo;
  String serieRecibo;
  String agenciaDeposito;
  String fechaDeposito;
  String tramite;
  String usuario;
  String numeroDeposito;
  String concepto;
  BigDecimal monto;

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getEstadoRecibo() {
        return estadoRecibo;
    }

    public void setEstadoRecibo(String estadoRecibo) {
        this.estadoRecibo = estadoRecibo;
    }

    public Long getIdtasa() {
        return idtasa;
    }

    public void setIdtasa(Long idtasa) {
        this.idtasa = idtasa;
    }

    public Long getNumeroRecibo() {
        return numeroRecibo;
    }

    public void setNumeroRecibo(Long numeroRecibo) {
        this.numeroRecibo = numeroRecibo;
    }

    public String getSerieRecibo() {
        return serieRecibo;
    }

    public void setSerieRecibo(String serieRecibo) {
        this.serieRecibo = serieRecibo;
    }

    public String getAgenciaDeposito() {
        return agenciaDeposito;
    }

    public void setAgenciaDeposito(String agenciaDeposito) {
        this.agenciaDeposito = agenciaDeposito;
    }

    public String getFechaDeposito() {
        return fechaDeposito;
    }

    public void setFechaDeposito(String fechaDeposito) {
        this.fechaDeposito = fechaDeposito;
    }

    public String getTramite() {
        return tramite;
    }

    public void setTramite(String tramite) {
        this.tramite = tramite;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getNumeroDeposito() {
        return numeroDeposito;
    }

    public void setNumeroDeposito(String numeroDeposito) {
        this.numeroDeposito = numeroDeposito;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

}
