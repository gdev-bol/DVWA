/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.entidad;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Entidad responsable de la Tabla regional
 *
 * @author Chano Rojas
 * @see DepositoMapper
 * @version 1.0, 11/08/2016
 */
public class Deposito implements Serializable {

    Long idDeposito;
    Long idLogTrans;
    String banco;
    Long numeroDeposito;
    Date fechaDeposito;
    BigDecimal monto;
    Integer deposCodDep;
    Integer deposCodAgencia;
    String nombreDepositante;
    String codAgencia;
    String codDepositante;
    BigDecimal saldo;
    Date fechaRegistroDeposito;
    String sucursalBanco;
    String estado;
    Long idUsuario;

    public Long getIdDeposito() {
        return idDeposito;
    }

    public void setIdDeposito(Long idDeposito) {
        this.idDeposito = idDeposito;
    }

    public Long getIdLogTrans() {
        return idLogTrans;
    }

    public void setIdLogTrans(Long idLogTrans) {
        this.idLogTrans = idLogTrans;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public Long getNumeroDeposito() {
        return numeroDeposito;
    }

    public void setNumeroDeposito(Long numeroDeposito) {
        this.numeroDeposito = numeroDeposito;
    }

    public Date getFechaDeposito() {
        return fechaDeposito;
    }

    public void setFechaDeposito(Date fechaDeposito) {
        this.fechaDeposito = fechaDeposito;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }


    public String getNombreDepositante() {
        return nombreDepositante;
    }

    public void setNombreDepositante(String nombreDepositante) {
        this.nombreDepositante = nombreDepositante;
    }

    public String getCodAgencia() {
        return codAgencia;
    }

    public void setCodAgencia(String codAgencia) {
        this.codAgencia = codAgencia;
    }

    public String getCodDepositante() {
        return codDepositante;
    }

    public void setCodDepositante(String codDepositante) {
        this.codDepositante = codDepositante;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public Date getFechaRegistroDeposito() {
        return fechaRegistroDeposito;
    }

    public void setFechaRegistroDeposito(Date fechaRegistroDeposito) {
        this.fechaRegistroDeposito = fechaRegistroDeposito;
    }

    public String getSucursalBanco() {
        return sucursalBanco;
    }

    public void setSucursalBanco(String sucursalBanco) {
        this.sucursalBanco = sucursalBanco;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getDeposCodDep() {
        return deposCodDep;
    }

    public void setDeposCodDep(Integer deposCodDep) {
        this.deposCodDep = deposCodDep;
    }

    public Integer getDeposCodAgencia() {
        return deposCodAgencia;
    }

    public void setDeposCodAgencia(Integer deposCodAgencia) {
        this.deposCodAgencia = deposCodAgencia;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }
    
    
}
