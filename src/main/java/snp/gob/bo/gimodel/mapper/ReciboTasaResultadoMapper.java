/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import snp.gob.bo.gimodel.entidad.ReciboTasaResultado;

/**
 *
 * @author Ruben Ramirez
 * @version 1.0, 10/05/2017
 * @see ReciboTasaResultado
 */
public class ReciboTasaResultadoMapper implements RowMapper<ReciboTasaResultado> {

    @Override
    public ReciboTasaResultado mapRow(ResultSet r, int i) throws SQLException {
        ReciboTasaResultado recibo = new ReciboTasaResultado();
        recibo.setFecha(r.getString("fecha"));
        recibo.setCantidad(r.getInt("cantidad"));
        recibo.setTotal(r.getBigDecimal("total"));
        return recibo;
    }

}
