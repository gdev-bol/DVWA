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
import snp.gob.bo.gimodel.entidad.FlujoTiempo;
import snp.gob.bo.gimodel.mapper.FlujoTiempoMapper;
import snp.gob.bo.gimodel.servicio.FlujoTiempoService;

/**
 *
 * @author levi
 */
public class FlujoTiempoServiceImpl implements FlujoTiempoService{
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    
     @Autowired
    @Override
    public void setDataSource(DataSource dataSource) {
        try {
            this.dataSource = dataSource;
            this.jdbcTemplate = new JdbcTemplate(dataSource);
        } catch (Exception e) {
            throw e;
        }
    }
    
 
    
    @Override
    public Integer obtieneFlujoXIdetapa(String etapa, Integer orden ){
      String SQL = "select * from obtener_flujotiempo(?,?);";//Modifica
       Integer numBloque =  jdbcTemplate.queryForObject(SQL, new Object[]{etapa,orden}, Integer.class);                                
        return numBloque;
                
    }
    
    
    
}
