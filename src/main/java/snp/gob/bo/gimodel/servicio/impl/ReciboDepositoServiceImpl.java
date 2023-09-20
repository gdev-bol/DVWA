/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.servicio.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import snp.gob.bo.gimodel.entidad.Deposito;
import snp.gob.bo.gimodel.entidad.Recibo;
import snp.gob.bo.gimodel.entidad.ReciboDeposito;
import snp.gob.bo.gimodel.mapper.DepositoMapper;
import snp.gob.bo.gimodel.mapper.ReciboDepositoMapper;
import snp.gob.bo.gimodel.mapper.ReciboMapper;
import snp.gob.bo.gimodel.servicio.DepositoService;
import snp.gob.bo.gimodel.servicio.ReciboDepositoService;

/**
 *
 * @author Chano Rojas
 * @see ReciboDominio
 * @see ReciboServiceImpl
 * @version 1.0, 05/06/2016
 */
@Service("reciboDepositoService")
public class ReciboDepositoServiceImpl implements ReciboDepositoService {

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

//
    @Override
    public ReciboDeposito crudReciboDeposito(ReciboDeposito reciboDeposito, int parametro) throws Exception {
        try {
            ReciboDeposito dep = new ReciboDeposito();
            String SQL = "select * from crud_recibodeposito("
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?);";
            dep = (ReciboDeposito) jdbcTemplate.queryForObject(SQL, new ReciboDepositoMapper(),
                    reciboDeposito.getIdReciboDeposito(),
                    reciboDeposito.getIdRecibo(),
                    reciboDeposito.getIdDeposito(),
                    reciboDeposito.getIdLogTrans(),
                    reciboDeposito.getMonto(),
                    parametro
            );
            return dep;
        } catch (DataAccessException e) {
            throw e;
        }
    }

    @Override
    public List<ReciboDeposito> lstReciboDeposito(ReciboDeposito reciboDeposito, int parametro) throws Exception {
        try {
            List<ReciboDeposito> lstrecdep = new ArrayList<ReciboDeposito>();
            String SQL = "select * from crud_recibodeposito("
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?);";
            lstrecdep = (List<ReciboDeposito>) jdbcTemplate.query(SQL, new ReciboDepositoMapper(),
                    reciboDeposito.getIdReciboDeposito(),
                    reciboDeposito.getIdRecibo(),
                    reciboDeposito.getIdDeposito(),
                    reciboDeposito.getIdLogTrans(),
                    reciboDeposito.getMonto(),
                    parametro
            );
            if (!lstrecdep.isEmpty()) {
                return lstrecdep;
            }
            return Collections.EMPTY_LIST;
        } catch (DataAccessException e) {
            throw e;
        }
    }

    @Override
    public List<ReciboDeposito> lstReciboDepositoPorIdRecibo(Long idRecibo) throws Exception {
            String SQL = "select * from recibodeposito where idrecibo=?";
            List<ReciboDeposito> list = jdbcTemplate.query(SQL, new ReciboDepositoMapper(), idRecibo);
            if (!list.isEmpty()) {
                return list;
            }else{
               return Collections.EMPTY_LIST;
            }
        }

    @Override
    public ReciboDeposito reciboDepositoPorIdReciboDeposito(Long idReciboDeposito) throws Exception {
            String SQL = "select * from recibodeposito where idrecibodeposito=?";
            List<ReciboDeposito> list = jdbcTemplate.query(SQL, new ReciboDepositoMapper(), idReciboDeposito);
            if (!list.isEmpty()) {
                return list.get(0);
            }else{
               return new ReciboDeposito();
            }
        }


}
