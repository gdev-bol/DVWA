/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.servicio.impl;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import snp.gob.bo.gimodel.entidad.ReciboAgrupacion;
import snp.gob.bo.gimodel.entidad.ReciboPorPeriodo;
import snp.gob.bo.gimodel.mapper.ReciboAgrupacionMapper;
import snp.gob.bo.gimodel.mapper.ReciboPorPeriodoMapper;
import snp.gob.bo.gimodel.servicio.ReciboAgrupacionService;

/**
 *
 * @author Ruben Ramirez
 * @see ReciboAgrupacion
 * @see ReciboAgrupacionServiceImpl
 * @version 1.0, 22/12/2016
 */
@Service("reciboAgrupacionService")
public class ReciboAgrupacionServiceImpl implements ReciboAgrupacionService {

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
    public List<ReciboAgrupacion> listaReciboAgrupacionMes(int gestion, int mes) throws Exception {
        try {
            String SQL = "select idtasa, concepto, count(*) as cantidad, sum(monto) as total\n"
                    + "from recibo\n"
                    + "where EXTRACT(MONTH FROM fecha_emision_recibo) = ? and EXTRACT(YEAR FROM fecha_emision_recibo) = ? and (estado_recibo = 'REMI' or estado_recibo = 'REAN')\n"
                    + "group by idtasa,concepto\n"
                    + "order by concepto";
            List<ReciboAgrupacion> listaReciboAgrupacion = jdbcTemplate.query(SQL, new ReciboAgrupacionMapper(), mes, gestion);
            if (!listaReciboAgrupacion.isEmpty()) {
                return listaReciboAgrupacion;
            }
        } catch (Exception e) {
            throw e;
        }
        return Collections.EMPTY_LIST;
    }
    
    @Override
    public List<ReciboAgrupacion> listaReciboAgrupacionMesPorIdUsuario(int gestion, int mes, Long idUsuario) throws Exception {
        try {
            String SQL = "select idtasa, concepto, count(*) as cantidad, sum(monto) as total\n"
                    + "from recibo\n"
                    + "where EXTRACT(MONTH FROM fecha_emision_recibo) = ? and EXTRACT(YEAR FROM fecha_emision_recibo) = ? and (estado_recibo = 'REMI' or estado_recibo = 'REAN') and idusuario = ?\n"
                    + "group by idtasa,concepto\n"
                    + "order by concepto";
            List<ReciboAgrupacion> listaReciboAgrupacion = jdbcTemplate.query(SQL, new ReciboAgrupacionMapper(), mes, gestion,idUsuario);
            if (!listaReciboAgrupacion.isEmpty()) {
                return listaReciboAgrupacion;
            }
        } catch (Exception e) {
            throw e;
        }
        return Collections.EMPTY_LIST;
    }

    @Override
    public List<ReciboAgrupacion> listaReciboAgrupacionMesPorIdRegional(int gestion, int mes, Long idRegional) throws Exception {
        try {
            String SQL = "select idtasa, concepto, count(*) as cantidad, sum(monto) as total\n"
                    + "from recibo\n"
                    + "where EXTRACT(MONTH FROM fecha_emision_recibo) = ? and EXTRACT(YEAR FROM fecha_emision_recibo) = ? and (estado_recibo = 'REMI' or estado_recibo = 'REAN') and idregional = ?\n"
                    + "group by idtasa,concepto\n"
                    + "order by concepto";
            List<ReciboAgrupacion> listaReciboAgrupacion = jdbcTemplate.query(SQL, new ReciboAgrupacionMapper(), mes, gestion,idRegional);
            if (!listaReciboAgrupacion.isEmpty()) {
                return listaReciboAgrupacion;
            }
        } catch (Exception e) {
            throw e;
        }
        return Collections.EMPTY_LIST;
    }

    @Override
    public List<ReciboAgrupacion> listaReciboAgrupacionGestion(int gestion) throws Exception {
        try {
            String SQL = "select idtasa, concepto, count(*) as cantidad, sum(monto) as total\n"
                    + "from recibo\n"
                    + "where EXTRACT(YEAR FROM fecha_emision_recibo) = ? and (estado_recibo = 'REMI' or estado_recibo = 'REAN')\n"
                    + "group by idtasa,concepto\n"
                    + "order by concepto ";
            List<ReciboAgrupacion> listaReciboAgrupacion = jdbcTemplate.query(SQL, new ReciboAgrupacionMapper(), gestion);
            if (!listaReciboAgrupacion.isEmpty()) {
                return listaReciboAgrupacion;
            }
        } catch (Exception e) {
            throw e;
        }
        return Collections.EMPTY_LIST;
    }

    @Override
    public List<ReciboAgrupacion> listaReciboAgrupacionGestionPorIdUsuario(int gestion, Long idUsuario) throws Exception {
        try {
            String SQL = "select idtasa, concepto, count(*) as cantidad, sum(monto) as total\n"
                    + "from recibo\n"
                    + "where EXTRACT(YEAR FROM fecha_emision_recibo) = ? and (estado_recibo = 'REMI' or estado_recibo = 'REAN') and idusuario = ?\n"
                    + "group by idtasa,concepto\n"
                    + "order by concepto ";
            List<ReciboAgrupacion> listaReciboAgrupacion = jdbcTemplate.query(SQL, new ReciboAgrupacionMapper(), gestion,idUsuario);
            if (!listaReciboAgrupacion.isEmpty()) {
                return listaReciboAgrupacion;
            }
        } catch (Exception e) {
            throw e;
        }
        return Collections.EMPTY_LIST;
    }

    @Override
    public List<ReciboAgrupacion> listaReciboAgrupacionGestionPorIdRegional(int gestion, Long idRegional) throws Exception {
        try {
            String SQL = "select idtasa, concepto, count(*) as cantidad, sum(monto) as total\n"
                    + "from recibo\n"
                    + "where EXTRACT(YEAR FROM fecha_emision_recibo) = ? and (estado_recibo = 'REMI' or estado_recibo = 'REAN') and idregional = ?\n"
                    + "group by idtasa,concepto\n"
                    + "order by concepto ";
            List<ReciboAgrupacion> listaReciboAgrupacion = jdbcTemplate.query(SQL, new ReciboAgrupacionMapper(), gestion, idRegional);
            if (!listaReciboAgrupacion.isEmpty()) {
                return listaReciboAgrupacion;
            }
        } catch (Exception e) {
            throw e;
        }
        return Collections.EMPTY_LIST;
    }

    @Override
    public List<ReciboPorPeriodo> listaReciboPorPeriodo(Date fechaIni, Date fechaFin, String criterio, Long id) throws Exception {
        try {
            String SQL = "select * from recibo_por_periodo(?,?,?,?);";
            List<ReciboPorPeriodo> listaReciboPorPeriodo = jdbcTemplate.query(SQL, new ReciboPorPeriodoMapper(),fechaIni, fechaFin, criterio, id);
            return listaReciboPorPeriodo;
        } catch (Exception e) {
            throw e;
        }
    }

}
