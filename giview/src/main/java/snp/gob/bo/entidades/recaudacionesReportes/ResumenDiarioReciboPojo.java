/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */ 
package snp.gob.bo.entidades.recaudacionesReportes;

import java.math.BigDecimal;
/**
 *
 * @author Ruben Ramirez
 */
public class ResumenDiarioReciboPojo {
    private Long recibo;
    private String serieRecibo;
    private String agencia;
    private String fechaDep;
    private String tramite;
    private String usuario;
    private String deposito;
    private String concepto;
    private BigDecimal monto;

    public Long getRecibo() {
        return recibo;
    }

    public void setRecibo(Long recibo) {
        this.recibo = recibo;
    }

    public String getSerieRecibo() {
        return serieRecibo;
    }

    public void setSerieRecibo(String serieRecibo) {
        this.serieRecibo = serieRecibo;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public String getFechaDep() {
        return fechaDep;
    }

    public void setFechaDep(String fechaDep) {
        this.fechaDep = fechaDep;
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

    public String getDeposito() {
        return deposito;
    }

    public void setDeposito(String deposito) {
        this.deposito = deposito;
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
