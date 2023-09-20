/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.servicio.impl;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import snp.gob.bo.gimodel.servicio.OpoEstadoService;

/**
 *
 * @author Luis Angel Quispe Limachi
 */
@Service("opoEstadoService")
public class OpoEstadoServiceImpl implements OpoEstadoService {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    @Autowired
    public void setDataSource(DataSource dataSource) {
        try {
            this.dataSource = dataSource;
            this.jdbcTemplate = new JdbcTemplate(dataSource);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public String devuelvenombredelestadoxidestado(Long idesta) {
    try {
    String SQL = "select estado from opoestado where idestado=?;";
        if(idesta!=0L){
            return jdbcTemplate.queryForObject(SQL, String.class, idesta);
        }
        return "";
        }
     catch (EmptyResultDataAccessException et) {
            return "";
     }         
    catch (Exception e) {
            throw e;
        }
    }
    
    
    
    
    

}
