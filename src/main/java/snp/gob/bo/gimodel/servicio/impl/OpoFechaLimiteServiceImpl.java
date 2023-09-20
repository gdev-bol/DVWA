/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.servicio.impl;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import snp.gob.bo.gimodel.entidad.OpoFechalimite;
import snp.gob.bo.gimodel.mapper.OpoFechalimiteMapper;
import snp.gob.bo.gimodel.servicio.OpoFechaLimiteService;

/**
 *
 * @author Luis Angel Quispe Limachi
 */

@Service("opoFechaLimiteService")
public class OpoFechaLimiteServiceImpl implements OpoFechaLimiteService {

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
    public OpoFechalimite guardaOpoFechalimite(OpoFechalimite opofechalimite) throws Exception {
        
        
        String SQL = "select * from inserta_opofechalimite(?,?,?,?,?,?,?);";
                
                OpoFechalimite objeven=jdbcTemplate.queryForObject(SQL, new OpoFechalimiteMapper(),
                       opofechalimite.getIdevento(),
                       opofechalimite.getIdactividadplazo(),
                       opofechalimite.getIdoposicion(),
                       opofechalimite.getIdlogtrans(),
                       opofechalimite.getOrden(),
                       opofechalimite.getFechalimite(),
                       opofechalimite.getOrden_o()            
                );
       return objeven;
        
           }
   }
