/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package snp.gob.bo.gimodel.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import snp.gob.bo.gimodel.entidad.ReciboDeposito;

/**
 *
 * @author Chano Rojas
 */
public class ReciboDepositoMapper implements RowMapper<ReciboDeposito> {

    @Override
    public ReciboDeposito mapRow(ResultSet r, int i) throws SQLException {
        ReciboDeposito reciboDeposito = new ReciboDeposito();
        reciboDeposito.setIdReciboDeposito(r.getLong("idrecibodeposito"));
        reciboDeposito.setIdRecibo(r.getLong("idrecibo"));
        reciboDeposito.setIdDeposito(r.getLong("iddeposito"));
        reciboDeposito.setIdLogTrans(r.getLong("idlogtrans"));
        reciboDeposito.setMonto(r.getBigDecimal("monto"));
        return reciboDeposito;
    }
    
}
