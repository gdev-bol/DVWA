/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor. 
 */
package snp.gob.bo.entidades.recaudacionesReportes;
/**
 *
 * @author Ruben Ramirez
 */
public class ResumenDiarioPorDepositoPojo {
    private String banco;
    private String fechaRegistroDeposito;
    private String codAgencia;
    private String sucursalBanco;
    private String fechaDeposito;
    private String numeroDeposito;
    private String monto;

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public String getFechaRegistroDeposito() {
        return fechaRegistroDeposito;
    }

    public void setFechaRegistroDeposito(String fechaRegistroDeposito) {
        this.fechaRegistroDeposito = fechaRegistroDeposito;
    }

    public String getCodAgencia() {
        return codAgencia;
    }

    public void setCodAgencia(String codAgencia) {
        this.codAgencia = codAgencia;
    }

    public String getSucursalBanco() {
        return sucursalBanco;
    }

    public void setSucursalBanco(String sucursalBanco) {
        this.sucursalBanco = sucursalBanco;
    }

    public String getFechaDeposito() {
        return fechaDeposito;
    }

    public void setFechaDeposito(String fechaDeposito) {
        this.fechaDeposito = fechaDeposito;
    }

    public String getNumeroDeposito() {
        return numeroDeposito;
    }

    public void setNumeroDeposito(String numeroDeposito) {
        this.numeroDeposito = numeroDeposito;
    }

    public String getMonto() {
        return monto;
    }

    public void setMonto(String monto) {
        this.monto = monto;
    }
    
    
}
