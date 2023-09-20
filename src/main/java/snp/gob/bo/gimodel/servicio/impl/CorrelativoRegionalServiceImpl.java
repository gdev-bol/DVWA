/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.servicio.impl;
import java.util.Collections;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import snp.gob.bo.gimodel.entidad.CorrelativoRegional;
import snp.gob.bo.gimodel.mapper.CorrelativoRegionalMapper;
import snp.gob.bo.gimodel.servicio.CorrelativoRegionalService;

/**
 *
 * @author Chano Rojas
 * @see RenRenovacionDominio
 * @see RenrenovacionServiceImpl
 * @version 1.0, 05/06/2016
 */
@Service("correlativoRegionalService")
public class CorrelativoRegionalServiceImpl implements CorrelativoRegionalService {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    
    @Autowired
    @Override
    public void setDataSource(DataSource dataSource) throws Exception {
        try {
            this.dataSource = dataSource;
            this.jdbcTemplate = new JdbcTemplate(dataSource);
        } catch (Exception e) {
            throw e;
        }
    }

    
    @Override
    public CorrelativoRegional crudCorrelativoRegional(CorrelativoRegional correlativoRegional, int parametro) throws Exception {
        try {
            String SQL = "select * from crud_correlativoregional("
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?);";
            CorrelativoRegional corre = (CorrelativoRegional) jdbcTemplate.queryForObject(SQL, new CorrelativoRegionalMapper(),
                    correlativoRegional.getIdcorrelativoregional(),
                    correlativoRegional.getIdregional(),
                    correlativoRegional.getIdcorrelativo(),
                    correlativoRegional.getIdlogtrans(),
                    correlativoRegional.getTipo_tramite(),
                    correlativoRegional.getEstado(),
                    parametro);
            return corre;
        } catch (DataAccessException e) {
            throw e;
        }
    }
    
    @Override
    public List<CorrelativoRegional> listaCorrelativoRegional(CorrelativoRegional correlativoRegional, int parametro) throws Exception {
        try {
            String SQL = "select * from crud_correlativoregional("
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?);";
            List<CorrelativoRegional> listacorre = (List<CorrelativoRegional>) (CorrelativoRegional) jdbcTemplate.queryForObject(SQL, new CorrelativoRegionalMapper(),
                    correlativoRegional.getIdcorrelativoregional(),
                    correlativoRegional.getIdregional(),
                    correlativoRegional.getIdcorrelativo(),
                    correlativoRegional.getIdlogtrans(),
                    correlativoRegional.getTipo_tramite(),
                    correlativoRegional.getEstado(),
                    parametro);
            if(!listacorre.isEmpty()){
                return listacorre;
            }else{
                return Collections.EMPTY_LIST;
            }
        } catch (DataAccessException e) {
            throw e;
        }
    }

    
    @Override
    public CorrelativoRegional crud_CorrelativoRegional(CorrelativoRegional correlativoRegional, int parametro) throws Exception {
        try {
            String SQL = "select * from crud_correlativoregional("
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?);";
            List<CorrelativoRegional> listacorre = (List<CorrelativoRegional>) (CorrelativoRegional) jdbcTemplate.queryForObject(SQL, new CorrelativoRegionalMapper(),
                    correlativoRegional.getIdcorrelativoregional(),
                    correlativoRegional.getIdregional(),
                    correlativoRegional.getIdcorrelativo(),
                    correlativoRegional.getIdlogtrans(),
                    correlativoRegional.getTipo_tramite(),
                    correlativoRegional.getEstado(),
                    parametro);
            if(!listacorre.isEmpty()){
                return listacorre.get(0);
            }else{
                return new CorrelativoRegional();
            }
        } catch (DataAccessException e) {
            throw e;
        }
    }

   
    
}
