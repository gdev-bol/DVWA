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
import snp.gob.bo.gimodel.entidad.Regional;
import snp.gob.bo.gimodel.entidad.Dominio;
import snp.gob.bo.gimodel.enums.EnumEstado;
import snp.gob.bo.gimodel.mapper.RegionalMapper;
import snp.gob.bo.gimodel.servicio.RegionalService;
import snp.gob.bo.gimodel.servicio.DominioService;

/**
 *
 * @author Eddy Valero
 * @see Dominio
 * @see DominioService
 * @version 1.0, 05/06/2016
 */
@Service("regionalService")
public class RegionalServiceImpl implements RegionalService {

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
    public List<Regional> obtenerListadoRegional() throws Exception {
        try {
            String SQL = "select * from obtenerlistaregional();";
            List<Regional> listaRegional = jdbcTemplate.query(SQL, new RegionalMapper());

            if (!listaRegional.isEmpty()) {
                return listaRegional;
            }

            return Collections.EMPTY_LIST;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Regional encuentraRegionalSede() {
        String SQL = "select * from regional where central=true and estado='" + EnumEstado.ACTIVO.getCodigo() + "'";
        if (!jdbcTemplate.query(SQL, new RegionalMapper()).isEmpty()) {
            return jdbcTemplate.query(SQL, new RegionalMapper()).get(0);
        }
        return null;
    }

    @Override
    public Regional obtenerRegionalPorIdRegiona(Long idRegional) throws Exception {
        try {
            String SQL = "select * from regional where idregional=? and estado='AC' ";
            if (!jdbcTemplate.query(SQL, new RegionalMapper(), idRegional).isEmpty()) {
                return jdbcTemplate.query(SQL, new RegionalMapper(), idRegional).get(0);
            }
        } catch (Exception e) {
            throw e;
        }
        return new Regional();
    }

    @Override
    public List<Regional> listadoRegional() throws Exception {
        try {
            String SQL = "select * from regional where estado='AC'";
            List<Regional> listaRegional = jdbcTemplate.query(SQL, new RegionalMapper());
            if (!listaRegional.isEmpty()) {
                return listaRegional;
            }
            return Collections.EMPTY_LIST;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Long lista_IdRegional_TipoCiudadNotificacion(String regional) {
        Long IdRegional= 1l;
        try {
            String SQL = "select * from regional where tipo_ciudad_notificacion = ? and estado = 'AC';";
            IdRegional = jdbcTemplate.queryForObject(SQL, Long.class, regional);
            return IdRegional;
        } catch (DataAccessException e) {
            return IdRegional;
        }
    }

}
