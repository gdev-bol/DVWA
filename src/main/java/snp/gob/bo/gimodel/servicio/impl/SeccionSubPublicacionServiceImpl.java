/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.servicio.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import snp.gob.bo.gimodel.entidad.SeccionSubPublicacion;
import snp.gob.bo.gimodel.mapper.SeccionSubPublicacionMapper;
import snp.gob.bo.gimodel.servicio.SeccionSubPublicacionService;

/**
 *
 * @author Ruben Ramirez
 * @see SeccinSubPublicacionService
 * @version 1.0, 03/11/2016
 */
@Service("seccionSubPublicacionService")
public class SeccionSubPublicacionServiceImpl implements SeccionSubPublicacionService {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

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
    public List<String> listadoSecciones() throws Exception {
        List<String> listSeccion = new ArrayList<String>();
        try {
            String SQL = "select distinct descripcion,seccion\n"
                    + "from seccionsubpublicacion\n"
                    + "where subseccion is null and estado = 'AC'\n"
                    + "order by seccion";

            listSeccion = jdbcTemplate.query(SQL, new RowMapper() {
                public Object mapRow(ResultSet resultSet, int i) throws SQLException {
                    return resultSet.getString(1);
                }
            });
            return listSeccion;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<String> listadoSubSecciones() throws Exception {
        List<String> listSeccion = new ArrayList<String>();
        try {
//            String SQL = "select distinct subseccion,descripcion\n"
//                    + "from seccionsubpublicacion\n"
//                    + "where seccion is null and estado = 'AC'\n"
//                    + "order by subseccion";
            
            String SQL = "select subseccion,descripcion\n"
                    + "from seccionsubpublicacion\n"
                    + "where seccion is null and estado = 'AC'\n"
                    + "order by descripcion";

            listSeccion = jdbcTemplate.query(SQL, new RowMapper() {
                public Object mapRow(ResultSet resultSet, int i) throws SQLException {
                    return resultSet.getString(2);
                }
            });
            return listSeccion;
        } catch (Exception e) {
            throw e;
        }
    }

}
