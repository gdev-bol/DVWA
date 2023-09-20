/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.servicio.impl;

import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import snp.gob.bo.gimodel.mapper.GestionCalidadPojoMapper;
import snp.gob.bo.gimodel.pojo.GestionCalidadPojo;
import snp.gob.bo.gimodel.servicio.GestionCalidadService;

/**
 *
 * @author susana
 */
@Service("gestionCalidadService")
public class GestionCalidadServiceImpl implements GestionCalidadService {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

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
    public List<GestionCalidadPojo> lista_gestionCalidadPojos() {
        List<GestionCalidadPojo> listGestionCalidadPojo;
        try {
            String sql= "select * from j_ventanilla_sm";
            listGestionCalidadPojo = jdbcTemplate.query(sql, new GestionCalidadPojoMapper());
            if (!listGestionCalidadPojo.isEmpty()) {
                System.out.println("devuelve listado");
                System.out.println("lista"+listGestionCalidadPojo.size());
                return listGestionCalidadPojo;
            }
        } catch (Exception e) {
            throw e;
        }

        return new ArrayList<>();

    }
   
  

}
