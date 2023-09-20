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
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import snp.gob.bo.gimodel.entidad.Dominio;
import snp.gob.bo.gimodel.entidad.OpoResolucion;
import snp.gob.bo.gimodel.mapper.DominioMapper;
import snp.gob.bo.gimodel.mapper.OpoResolucionMapper;
import snp.gob.bo.gimodel.servicio.OpoResolucionService;

/**
 *
 * @author Luis Angel Quispe Limachi
 */
@Service("opoResolucionService")
public class OpoResolucionServiceImpl implements OpoResolucionService {

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
    public OpoResolucion obtenerObjOporesolucion(Long idopo) throws Exception {
        try {
            String SQL = "select * from oporesolucion where idoposicion=? and estado='AC';";
            if (jdbcTemplate.queryForObject(SQL, new OpoResolucionMapper(), idopo) != null) {
                OpoResolucion objresolu = jdbcTemplate.queryForObject(SQL, new OpoResolucionMapper(), idopo);
                return objresolu;
            }

            return new OpoResolucion();
        } catch (EmptyResultDataAccessException e) {
            return new OpoResolucion();
        } catch (Exception e) {
            throw e;
        }

    }

    @Override
    public List<Dominio> obtenerListadoOpoRecursoxtipodominio(String tipodominio) throws Exception {

        try {
            String SQL = "select * from dominio where dominio=?;";
            List<Dominio> listaDomino = jdbcTemplate.query(SQL, new DominioMapper(), tipodominio);
            if (!listaDomino.isEmpty()) {
                return listaDomino;
            }
            return Collections.EMPTY_LIST;
        } catch (Exception e) {
            throw e;
        }

    }

    @Override
    public OpoResolucion guardaOpoResolucion(OpoResolucion oporesolucion) throws Exception {

        String SQL = "select * from inserta_oporesolucion(?,?,?,?,?,?,?,?,?,?);";
        OpoResolucion oporesolu = (OpoResolucion) jdbcTemplate.queryForObject(SQL, new OpoResolucionMapper(),
                oporesolucion.getIdoposicion(),
                oporesolucion.getIdevento(),
                oporesolucion.getIdlogtrans(),
                oporesolucion.getNumero_resolucion(),
                oporesolucion.getFecha(),
                oporesolucion.getResolucion_cancelacion(),
                oporesolucion.getResolucion_oposicion(),
                oporesolucion.getResolucion_signo(),
                oporesolucion.getOrden_o(),
                oporesolucion.getEstado()
        );
        return oporesolu;
    }

    @Override
    public OpoResolucion extraeResolucionxidevento(Long ideventores) throws Exception {
      try {
            String SQL = "select * from oporesolucion where idevento=? and estado='AC';";
            if (jdbcTemplate.queryForObject(SQL, new OpoResolucionMapper(), ideventores) != null) {
                OpoResolucion objresolu = jdbcTemplate.queryForObject(SQL, new OpoResolucionMapper(), ideventores);
                return objresolu;
            }

            return new OpoResolucion();
        } catch (EmptyResultDataAccessException e) {
            return new OpoResolucion();
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public OpoResolucion modificarOporesolucion(OpoResolucion oporesolucion) throws Exception {
    
   try {
            String SQL = "select * from modifica_oporesolucion(?,?,?,?,?,?,?,?,?,?,?);";
            OpoResolucion modiobjeto1 = (OpoResolucion) jdbcTemplate.queryForObject(SQL, new OpoResolucionMapper(),
                oporesolucion.getIdresolucion(),
                oporesolucion.getIdoposicion(),
                oporesolucion.getIdevento(),
                oporesolucion.getIdlogtrans(),
                oporesolucion.getNumero_resolucion(),
                oporesolucion.getFecha(),
                oporesolucion.getResolucion_cancelacion(),
                oporesolucion.getResolucion_oposicion(),
                oporesolucion.getResolucion_signo(),
                oporesolucion.getOrden_o(),
                oporesolucion.getEstado());
            return modiobjeto1;

        } catch (Exception e) {
            throw e;
        }
    
    
    
    }
    
    
    
    
    
    

}
