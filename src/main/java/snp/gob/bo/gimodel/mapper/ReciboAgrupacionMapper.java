/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import snp.gob.bo.gimodel.entidad.ReciboAgrupacion;

/**
 *
 * @author Ruben Ramirez
 * @see ReciboAgrupacion
 * @version 1.0, 22/12/2016
 */
public class ReciboAgrupacionMapper implements RowMapper<ReciboAgrupacion> {

    @Override
    public ReciboAgrupacion mapRow(ResultSet r, int i) throws SQLException {
        ReciboAgrupacion recibo = new ReciboAgrupacion();
        recibo.setConcepto(r.getString("concepto"));
        recibo.setCantidad(r.getInt("cantidad"));
        recibo.setTotal(r.getBigDecimal("total"));
        return recibo;
    }

}
