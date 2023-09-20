/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.servicio.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import snp.gob.bo.gimodel.entidad.ReciboResultado;
import snp.gob.bo.gimodel.mapper.ReciboResultadoMapper;
import snp.gob.bo.gimodel.servicio.ReciboResultadoService;

/**
 *
 * @author Ruben Ramirez
 * @see ReciboResultado
 * @see ReciboResultadoServiceImpl
 * @version 1.0, 19/12/2016
 */
@Service("reciboResultadoService")
public class ReciboResultadoServiceImpl implements ReciboResultadoService {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    @Autowired
    public void setDataSource(DataSource dataSource) throws Exception {
        try {
            this.dataSource = dataSource;
            this.jdbcTemplate = new JdbcTemplate(dataSource);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<String> listaReciboResultadoMes(int gestion, int mes) throws Exception {
        try {
            String SQL = "select to_char(fecha_emision_recibo, 'dd/MM/yyyy') as fecha\n"
                    + "from recibo\n"
                    + "where EXTRACT(MONTH FROM fecha_emision_recibo) = " + mes + " and EXTRACT(YEAR FROM fecha_emision_recibo) = " + gestion + " and (estado_recibo = 'REMI' or estado_recibo = 'REAN')\n"
                    + "group by to_char(fecha_emision_recibo, 'dd/MM/yyyy')\n"
                    + "order by to_char(fecha_emision_recibo, 'dd/MM/yyyy')";

            List<String> listaFecha = jdbcTemplate.query(SQL, new RowMapper() {
                public Object mapRow(ResultSet resultSet, int i) throws SQLException {
                    return resultSet.getString(1);
                }
            });
            if (!listaFecha.isEmpty()) {
                return listaFecha;
            }
        } catch (Exception e) {
            throw e;
        }
        return Collections.EMPTY_LIST;
    }

    @Override
    public List<String> listaReciboResultadoMesPorIdUsuario(int gestion, int mes, Long idUsuario) throws Exception {
        try {
            String SQL = "select to_char(fecha_emision_recibo, 'dd/MM/yyyy') as fecha\n"
                    + "from recibo\n"
                    + "where EXTRACT(MONTH FROM fecha_emision_recibo) = " + mes + " and EXTRACT(YEAR FROM fecha_emision_recibo) =  " + gestion + " and (estado_recibo = 'REMI' or estado_recibo = 'REAN') and idusuario =  " + idUsuario + " \n"
                    + "group by to_char(fecha_emision_recibo, 'dd/MM/yyyy')\n"
                    + "order by to_char(fecha_emision_recibo, 'dd/MM/yyyy')";

            List<String> listaFecha = jdbcTemplate.query(SQL, new RowMapper() {
                public Object mapRow(ResultSet resultSet, int i) throws SQLException {
                    return resultSet.getString(1);
                }
            });
            if (!listaFecha.isEmpty()) {
                return listaFecha;
            }
        } catch (Exception e) {
            throw e;
        }
        return Collections.EMPTY_LIST;
    }

    @Override
    public List<String> listaReciboResultadoMesPorIdRegional(int gestion, int mes, Long idRegional) throws Exception {
        try {
            String SQL = "select to_char(fecha_emision_recibo, 'dd/MM/yyyy') as fecha\n"
                    + "from recibo\n"
                    + "where EXTRACT(MONTH FROM fecha_emision_recibo) = " + mes + " and EXTRACT(YEAR FROM fecha_emision_recibo) =  " + gestion + " and (estado_recibo = 'REMI' or estado_recibo = 'REAN') and idregional =  " + idRegional + " \n"
                    + "group by to_char(fecha_emision_recibo, 'dd/MM/yyyy')\n"
                    + "order by to_char(fecha_emision_recibo, 'dd/MM/yyyy')";

            List<String> listaFecha = jdbcTemplate.query(SQL, new RowMapper() {
                public Object mapRow(ResultSet resultSet, int i) throws SQLException {
                    return resultSet.getString(1);
                }
            });
            if (!listaFecha.isEmpty()) {
                return listaFecha;
            }
        } catch (Exception e) {
            throw e;
        }
        return Collections.EMPTY_LIST;
    }

    @Override
    public List<ReciboResultado> listaReciboResultadoFecha(String fecha) throws Exception {
        try {
            String SQL = "select idtasa, to_char(fecha_emision_recibo, 'dd/MM/yyyy') as fecha, concepto, count(*) as cantidad, sum(monto) as total\n"
                    + "from recibo\n"
                    + "where to_char(fecha_emision_recibo, 'dd/MM/yyyy') = '"+fecha+"' and (estado_recibo = 'REMI' or estado_recibo = 'REAN') \n"
                    + "group by to_char(fecha_emision_recibo, 'dd/MM/yyyy'),idtasa,concepto\n"
                    + "order by to_char(fecha_emision_recibo, 'dd/MM/yyyy'), concepto";
            List<ReciboResultado> listaReciboResultado = jdbcTemplate.query(SQL, new ReciboResultadoMapper());
            if (!listaReciboResultado.isEmpty()) {
                return listaReciboResultado;
            }
        } catch (Exception e) {
            throw e;
        }
        return Collections.EMPTY_LIST;
    }

    @Override
    public List<ReciboResultado> listaReciboResultadoFechaIdUsuario(String fecha, Long idUsuario) throws Exception {
        try {
            String SQL = "select idtasa, to_char(fecha_emision_recibo, 'dd/MM/yyyy') as fecha, concepto, count(*) as cantidad, sum(monto) as total\n"
                    + "from recibo\n"
                    + "where to_char(fecha_emision_recibo, 'dd/MM/yyyy') = '"+fecha+"' and (estado_recibo = 'REMI' or estado_recibo = 'REAN') and idusuario =  " + idUsuario + " \n"
                    + "group by to_char(fecha_emision_recibo, 'dd/MM/yyyy'),idtasa,concepto\n"
                    + "order by to_char(fecha_emision_recibo, 'dd/MM/yyyy'), concepto";
            List<ReciboResultado> listaReciboResultado = jdbcTemplate.query(SQL, new ReciboResultadoMapper());
            if (!listaReciboResultado.isEmpty()) {
                return listaReciboResultado;
            }
        } catch (Exception e) {
            throw e;
        }
        return Collections.EMPTY_LIST;
    }

    @Override
    public List<ReciboResultado> listaReciboResultadoFechaPorIdRegional(String fecha, Long idRegional) throws Exception {
        try {
            String SQL = "select idtasa, to_char(fecha_emision_recibo, 'dd/MM/yyyy') as fecha, concepto, count(*) as cantidad, sum(monto) as total\n"
                    + "from recibo\n"
                    + "where to_char(fecha_emision_recibo, 'dd/MM/yyyy') = '"+fecha+"' and (estado_recibo = 'REMI' or estado_recibo = 'REAN') and idregional =  " + idRegional + " \n"
                    + "group by to_char(fecha_emision_recibo, 'dd/MM/yyyy'),idtasa,concepto\n"
                    + "order by to_char(fecha_emision_recibo, 'dd/MM/yyyy'), concepto";
            List<ReciboResultado> listaReciboResultado = jdbcTemplate.query(SQL, new ReciboResultadoMapper());
            if (!listaReciboResultado.isEmpty()) {
                return listaReciboResultado;
            }
        } catch (Exception e) {
            throw e;
        }
        return Collections.EMPTY_LIST;
    }

}
