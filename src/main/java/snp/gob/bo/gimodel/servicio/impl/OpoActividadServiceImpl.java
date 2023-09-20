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
import snp.gob.bo.gimodel.entidad.OpoActividad;
import snp.gob.bo.gimodel.mapper.OpoActividadMapper;
import snp.gob.bo.gimodel.servicio.OpoActividadService;

/**
 *
 * @author Luis Angel Quispe Limachi
 */
@Service("opoActividadService")
public class OpoActividadServiceImpl implements OpoActividadService {

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
    public String muestranomdelaactividadxidactividad(Long idacti) throws Exception {
 String SQL = "select actividad from opoactividad where idactividad=?";
        
        return jdbcTemplate.queryForObject(SQL, String.class, idacti);
    }

    @Override
    public Long muestraidestadoxidactividad(Long idactividad) throws Exception {
        String SQL = "select idestado from opoactividad where idactividad=?";
        if(jdbcTemplate.queryForObject(SQL, Long.class, idactividad)!=null){
        return jdbcTemplate.queryForObject(SQL, Long.class, idactividad);    
        }
        return 0L;    
        
    }

    @Override
    public List<OpoActividad> obtenerListadoActividades() throws Exception {
        
         try {
            String SQL = "select * from opoactividad order by idactividad;";
            List<OpoActividad> listaActividad = jdbcTemplate.query(SQL, new OpoActividadMapper());
            if (!listaActividad.isEmpty()) {
                return listaActividad;
            }
            return Collections.EMPTY_LIST;
        } catch (Exception e) {
            throw e;
        }
        
    }

}
