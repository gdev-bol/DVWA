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
import snp.gob.bo.gimodel.entidad.Correlativo;
import snp.gob.bo.gimodel.entidad.RenRenovacion;
import snp.gob.bo.gimodel.mapper.CorrelativoMapper;
import snp.gob.bo.gimodel.mapper.RenRenovacionMapper;
import snp.gob.bo.gimodel.servicio.CorrelativoService;

/**
 *
 * @author Chano Rojas
 * @see RenRenovacionDominio
 * @see RenrenovacionServiceImpl
 * @version 1.0, 05/06/2016
 */
@Service("correlativoService")
public class CorrelativoServiceImpl implements CorrelativoService {

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
    public Correlativo crudCorrelativo(Correlativo correlativo, int parametro) throws Exception {
        try {
            String SQL = "select * from crud_correlativo("
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?);";
            Correlativo corre = (Correlativo) jdbcTemplate.queryForObject(SQL, new CorrelativoMapper(),
                    correlativo.getIdcorrelativo(),
                    correlativo.getIdlogtrans(),
                    correlativo.getNombre_criterio(),
                    correlativo.getIncremento(),
                    correlativo.getUltimo_numero_asignado(),
                    correlativo.getAcronimo(),
                    correlativo.getSeparador(),
                    correlativo.getGestion(),
                    correlativo.getEstado(),
                    parametro);
            return corre;
        } catch (DataAccessException e) {
            throw e;
        }
    }

    @Override
    public List<Correlativo> listaCorrelativo(Correlativo correlativo, int parametro) throws Exception {
        try {
            String SQL = "select * from crud_correlativo("
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?);";
            List<Correlativo> listacorre = (List<Correlativo>) (Correlativo) jdbcTemplate.queryForObject(SQL, new CorrelativoMapper(),
                    correlativo.getIdcorrelativo(),
                    correlativo.getIdlogtrans(),
                    correlativo.getNombre_criterio(),
                    correlativo.getIncremento(),
                    correlativo.getUltimo_numero_asignado(),
                    correlativo.getAcronimo(),
                    correlativo.getSeparador(),
                    correlativo.getGestion(),
                    correlativo.getEstado(),
                    parametro);
            if (listacorre.isEmpty()) {
                return listacorre;
            } else {
                return Collections.EMPTY_LIST;
            }
        } catch (DataAccessException e) {
            throw e;
        }
    }

    @Override
    public Correlativo encuentraCorrelativoTabla(Long idRegional, String tipoTramite) {
        String SQL = "select * from obtiene_correlativo_regional_tipotramite("
                + "?,"
                + "?);";
        List<Correlativo> listaCorrelativos = jdbcTemplate.query(SQL, new CorrelativoMapper(), idRegional, tipoTramite);

        if (!listaCorrelativos.isEmpty()) {
            return listaCorrelativos.get(0);
        }
        return null;
    }

}
