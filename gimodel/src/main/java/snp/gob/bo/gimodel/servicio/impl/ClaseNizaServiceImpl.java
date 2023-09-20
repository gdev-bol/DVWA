/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.servicio.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import snp.gob.bo.gimodel.entidad.ClaseNiza;
import snp.gob.bo.gimodel.mapper.ClaseNizaMapper;
import snp.gob.bo.gimodel.servicio.ClaseNizaService;

/**
 *
 * @author Eddy Valero
 * @version 1.0, 26/08/2016
 */
@Service("claseNizaService")
public class ClaseNizaServiceImpl implements ClaseNizaService {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Override
    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public ClaseNiza obtenerRegistroClaseNiza(Long idClaseNiza) throws Exception {

        System.out.println("idclaseniza" + idClaseNiza);
        try {
            String SQL = "select * from obt_claseniza(?);";
            ClaseNiza claseNiza = (ClaseNiza) jdbcTemplate.queryForObject(
                    SQL, new ClaseNizaMapper(),
                    idClaseNiza);
            return claseNiza;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<ClaseNiza> obtenerListadoClaseNiza() throws Exception {
        try {
            String SQL = "select * from claseniza where estado='AC' and numero_clase_niza>=0 order by numero_clase_niza asc ";
            List<ClaseNiza> listaClaseNiza = jdbcTemplate.query(SQL, new ClaseNizaMapper());
            return listaClaseNiza;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<ClaseNiza> obtenerListadoClaseNizaVersion(String version) throws Exception {
        try {
            //String SQL = "select * from claseniza where estado = 'AC' and numero_clase_niza>=0 and version = '" + version + "' order by numero_clase_niza asc ";
            String SQL = "select * from claseniza where estado = 'AC' and numero_clase_niza>=0 and numero_edicion = '" + version + "' order by numero_clase_niza asc ";
            //System.out.println("SQL ............... "+SQL);
            List<ClaseNiza> listaClaseNiza = jdbcTemplate.query(SQL, new ClaseNizaMapper());

            return listaClaseNiza;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public ClaseNiza listarClaseNiza_id(Long id) {
        System.out.println("long"+id);
        try {
            if (id > 0) {
                String SQL = "select * from claseniza where idclaseniza = ?;";
                ClaseNiza claseNiza = (ClaseNiza) jdbcTemplate.queryForObject(
                        SQL, new ClaseNizaMapper(), id);
                return claseNiza;

            }
        } catch (Exception e) {
            throw e;
        }
        return new ClaseNiza();
    }

    @Override
    public List<String> lista_version_ClaseNiza() {
        List<String> listVersion = new ArrayList<String>();
        try {
            String SQL = "select distinct numero_edicion from claseniza --where estado ='AC'";

            listVersion = jdbcTemplate.query(SQL, new RowMapper() {
                public Object mapRow(ResultSet resultSet, int i) throws SQLException {
                    return resultSet.getString(1);
                }
            });
            return listVersion;
        } catch (EmptyResultDataAccessException e) {
            //System.out.println("vacio no existe publicacion para la marca");
            return new ArrayList<String>();
        } catch (Exception e) {
            throw e;
        }
    }

}
