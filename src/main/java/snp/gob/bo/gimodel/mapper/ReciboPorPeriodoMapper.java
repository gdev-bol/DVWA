/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import snp.gob.bo.gimodel.entidad.BusquedaMarcaResultado;
import snp.gob.bo.gimodel.entidad.ReciboPorPeriodo;

/**
 *
 * @author Eddy Valero
 * @version 1.0, 17/10/2016
 * @see BusquedaMarcaResultado
 */
public class ReciboPorPeriodoMapper implements RowMapper {

    @Override
    public ReciboPorPeriodo mapRow(ResultSet rs, int i) throws SQLException {
        
        ReciboPorPeriodo reciboPorPeriodo = new ReciboPorPeriodo();
        
        reciboPorPeriodo.setFecha(rs.getString("fecha"));
        reciboPorPeriodo.setEstadoRecibo(rs.getString("estado_recibo"));
        reciboPorPeriodo.setIdtasa(rs.getLong("idtasa"));
        reciboPorPeriodo.setNumeroRecibo(rs.getLong("numero_recibo"));
        reciboPorPeriodo.setSerieRecibo(rs.getString("serie_recibo"));
        reciboPorPeriodo.setAgenciaDeposito(rs.getString("agencia_deposito"));
        reciboPorPeriodo.setFechaDeposito(rs.getString("fecha_deposito"));
        reciboPorPeriodo.setTramite(rs.getString("tramite"));
        reciboPorPeriodo.setUsuario(rs.getString("usuario"));
        reciboPorPeriodo.setNumeroDeposito(rs.getString("numero_deposito"));
        reciboPorPeriodo.setConcepto(rs.getString("concepto"));
        reciboPorPeriodo.setMonto(rs.getBigDecimal("monto"));

        return reciboPorPeriodo;
    }

}
