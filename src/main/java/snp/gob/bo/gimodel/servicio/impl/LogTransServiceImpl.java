/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.servicio.impl;

import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import snp.gob.bo.gimodel.entidad.LogTrans;
import snp.gob.bo.gimodel.mapper.LogTransMapper;
import snp.gob.bo.gimodel.servicio.LogTransService;

/**
 *
 * @author Eddy Valero
 * @see LogTransServiceImpl
 * @version 1.0, 22/11/2016
 */
@Service("logTransService")
public class LogTransServiceImpl implements LogTransService {

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
    public LogTrans crudLogTrans(LogTrans logTrans, Integer parametro) throws Exception {
        try {
            String SQL = "select * from crud_logtrans(?, ?, ?, ?);";
            List<LogTrans> listaLogTrans = jdbcTemplate.query(SQL,
                    new LogTransMapper(),
                    logTrans.getIdLogTrans(),
                    logTrans.getIdUsuario(),
                    logTrans.getFecha(),
                    parametro
                    );
            if (!listaLogTrans.isEmpty()) {
                return listaLogTrans.get(0);
            }
            
            return new LogTrans();
        } catch (Exception e) {
            throw e;
        }
    }

    
}
