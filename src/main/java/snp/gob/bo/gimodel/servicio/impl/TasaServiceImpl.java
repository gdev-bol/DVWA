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
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import snp.gob.bo.gimodel.entidad.Tasa;
import snp.gob.bo.gimodel.mapper.TasaMapper;
import snp.gob.bo.gimodel.servicio.TasaService;

/**
 *
 * @author Chano Rojas
 * @see TasaService
 * @version 1.0, 18/10/2016
 */
@Service("tasaService")
public class TasaServiceImpl implements TasaService {

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
    public Tasa crudTasa(Tasa tasa, int parametro) throws Exception {
        try {
            Tasa tasaBase = new Tasa();
            String SQL = "select * from crud_tasa("
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
            tasaBase = (Tasa) jdbcTemplate.queryForObject(SQL, new TasaMapper(),
                    tasa.getIdTasa(),
                    tasa.getIdLogTrans(),
                    tasa.getDescripcion(),
                    tasa.getCosto(),
                    tasa.getDescuento(),
                    tasa.getUnidad(),
                    tasa.getEstado(),
                    tasa.getGestion(),
                    tasa.getTipoTramite(),
                    parametro
            );
            return tasaBase;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<Tasa> obtenerListaTasaActivas() throws Exception {
        try {
            String SQL = "select * from tasa where estado='AC' order by  descripcion ";
            if (!jdbcTemplate.query(SQL, new TasaMapper()).isEmpty()) {
                return jdbcTemplate.query(SQL, new TasaMapper());
            }
        } catch (Exception e) {
        }
        return Collections.EMPTY_LIST;
    }

    @Override
    public List<Tasa> obtenerListaTasas() throws Exception {
        try {
            String SQL = "select * from tasa order by estado DESC";
            if (!jdbcTemplate.query(SQL, new TasaMapper()).isEmpty()) {
                return jdbcTemplate.query(SQL, new TasaMapper());
            }
        } catch (Exception e) {
        }
        return Collections.EMPTY_LIST;
    }

    @Override
    public List<Tasa> obtenerListaTasaActivasPorTipoTramite(String tipoTramite) throws Exception {
        try {
            String SQL = "select * from tasa where tipo_tramite=? and estado='AC' ";
            if (!jdbcTemplate.query(SQL, new TasaMapper(), tipoTramite).isEmpty()) {
                return jdbcTemplate.query(SQL, new TasaMapper(), tipoTramite);
            }
        } catch (Exception e) {
            throw e;
        }
        return Collections.EMPTY_LIST;
    }

    @Override
    public Tasa obtenerTasaPorIdTasa(Long idTasa) throws Exception {
        try {
            String SQL = "select * from tasa where idtasa=? and estado='AC' ";
            if (!jdbcTemplate.query(SQL, new TasaMapper(), idTasa).isEmpty()) {
                return jdbcTemplate.query(SQL, new TasaMapper(), idTasa).get(0);
            }
        } catch (Exception e) {
            throw e;
        }
        return new Tasa();
    }

    @Override
    public void guardaGeneralTasa(Tasa tasa, Long IdUsuario) throws Exception {
        try {
            if (tasa.getIdTasa() == null) {
                System.out.println("ingreso a insert tasa");
                int parametro = 1;
                tasa.setIdLogTrans(1L);
                crudTasa(tasa, parametro);
            } else {
                System.out.println("ingreso a update tasa");
                crudTasa(tasa, 2);
            }
        } catch (Exception e) {
        }

    }

    @Override
    public List<Tasa> obtenerListaTasaActivasPorUnidad(String unidad) throws Exception {
        try {
            String SQL = "select * from tasa where unidad=? and estado='AC' ";
            if (!jdbcTemplate.query(SQL, new TasaMapper(), unidad).isEmpty()) {
                return jdbcTemplate.query(SQL, new TasaMapper(), unidad);
            }
        } catch (Exception e) {
            throw e;
        }
        return Collections.EMPTY_LIST;
    }

}
