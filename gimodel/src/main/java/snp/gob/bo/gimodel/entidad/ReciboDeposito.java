/*
 * To change this license header; choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.entidad;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Entidad responsable de la Tabla regional
 *
 * @author Eddy Valero
 * @see RegionalMapper
 * @version 1.0, 25/07/2016
 */
public class ReciboDeposito {
 Long idReciboDeposito;
 Long  idRecibo;
 Long  idDeposito;
 Long  idLogTrans;
 BigDecimal  monto;

    public Long getIdReciboDeposito() {
        return idReciboDeposito;
    }

    public void setIdReciboDeposito(Long idReciboDeposito) {
        this.idReciboDeposito = idReciboDeposito;
    }

    public Long getIdRecibo() {
        return idRecibo;
    }

    public void setIdRecibo(Long idRecibo) {
        this.idRecibo = idRecibo;
    }

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

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

 
    
}
