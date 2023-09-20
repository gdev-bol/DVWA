/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.servicio.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import snp.gob.bo.gimodel.entidad.DosificacionTasa;
import snp.gob.bo.gimodel.entidad.Regional;
import snp.gob.bo.gimodel.entidad.Tasa;
import snp.gob.bo.gimodel.mapper.DosificacionTasaMapper;
import snp.gob.bo.gimodel.servicio.DominioService;
import snp.gob.bo.gimodel.servicio.DosificacionTasaService;
import snp.gob.bo.gimodel.servicio.ModTitularLicenciatarioNuevoService;
import snp.gob.bo.gimodel.servicio.SigSolicitanteApoderadoService;

/**
 *
 * @author Chano Rojas
 */
@Service("dosificacionTasaService")
public class DosificacionTasaServiceImpl implements DosificacionTasaService {

    @Autowired
    private DominioService dominioService;
    @Autowired
    private SigSolicitanteApoderadoService sigSolicitanteApoderadoService;
    @Autowired
    private ModTitularLicenciatarioNuevoService modTitularLicenciatarioNuevoService;

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
    public List<DosificacionTasa> listaDosificacionTasa() {
          String SQL = "select * from dosificaciontasa where estado='AC' ";
        if (!jdbcTemplate.query(SQL, new DosificacionTasaMapper()).isEmpty()) {
            return jdbcTemplate.query(SQL, new DosificacionTasaMapper());
        }
        return Collections.EMPTY_LIST;
    }

    @Override
    public DosificacionTasa encuentraDosificacionTasaPorTasa(Tasa tasa, Regional regional) {
        String SQL = "select * from dosificaciontasa where idtasa=? and idregional=?  and  estado='AC' ";
        if (!jdbcTemplate.query(SQL, new DosificacionTasaMapper(), tasa.getIdTasa(), regional.getIdRegional()).isEmpty()) {
            return jdbcTemplate.query(SQL, new DosificacionTasaMapper(), tasa.getIdTasa(), regional.getIdRegional()).get(0);
        }
        return new DosificacionTasa();
    }


    @Override
    public List<DosificacionTasa> encuentraListaDosificacionTasaPorTasa(Tasa tasa, Regional regional) {
       
        List<DosificacionTasa> listaDosificacionTasa = new ArrayList<DosificacionTasa>();
        try {
            String SQL = "select * from dosificaciontasa where idtasa=? and idregional=? and tiporecibo='RECI' and  estado='AC' ";
            if (!jdbcTemplate.query(SQL, new DosificacionTasaMapper(), tasa.getIdTasa(), regional.getIdRegional()).isEmpty()) {
                listaDosificacionTasa.add(jdbcTemplate.query(SQL, new DosificacionTasaMapper(), tasa.getIdTasa(), regional.getIdRegional()).get(0));
            }
            String SQLDOS = "select * from dosificaciontasa where idtasa=? and idregional=? and tiporecibo='TITU' and  estado='AC' ";
            if (!jdbcTemplate.query(SQLDOS, new DosificacionTasaMapper(), tasa.getIdTasa(), regional.getIdRegional()).isEmpty()) {
                listaDosificacionTasa.add(jdbcTemplate.query(SQLDOS, new DosificacionTasaMapper(), tasa.getIdTasa(), regional.getIdRegional()).get(0));
            }
            if (!listaDosificacionTasa.isEmpty()) {
                return listaDosificacionTasa;
            }
            return Collections.EMPTY_LIST;
        } catch (Exception e) {
            throw e;
        }
    }

}
