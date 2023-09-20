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
import snp.gob.bo.gimodel.entidad.OpoRecurso;
import snp.gob.bo.gimodel.mapper.OpoRecursoMapper;
import snp.gob.bo.gimodel.servicio.OpoRecursoService;

/**
 *
 * @author Luis Angel Quispe Limachi
 */

@Service("opoRecursoService")
public class OpoRecursoServiceImpl implements OpoRecursoService{

    
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
    public List<OpoRecurso> obtenerListadoOpoRecursoxnrooposicion(Long nroopo) throws Exception {
   
      try {
            String SQL = "select * from oporecurso where idoposicion=? and estado='AC';";
            List<OpoRecurso> listaOprecurso = jdbcTemplate.query(SQL, new OpoRecursoMapper(), nroopo);
            if (!listaOprecurso.isEmpty()) {
                return listaOprecurso;
            }
            return Collections.EMPTY_LIST;
        } catch (Exception e) {
            throw e;
        }
    
    
    
    }

    @Override
    public OpoRecurso guardaOpoRecurso(OpoRecurso oporerecurso) throws Exception {
   
     String SQL = "select * from inserta_oporecurso(?,?,?,?,?,?,?,?,?);";
        OpoRecurso oporecurso = (OpoRecurso) jdbcTemplate.queryForObject(SQL, new OpoRecursoMapper(),
               oporerecurso.getIdevento(),
               oporerecurso.getIdoposicion(),
               oporerecurso.getIdlogtrans(),
               oporerecurso.getNumero_resolucion(),
               oporerecurso.getFecha_resolucion(),
               oporerecurso.getTipo(),
               oporerecurso.getResolucion(),
               oporerecurso.getOrden_op(),
               oporerecurso.getEstado()
        );
        return oporecurso;
    }

    @Override
    public OpoRecurso extraeRecursoxidevento(Long ideventorecur) throws Exception {
    
         try {
            String SQL = "select * from oporecurso where idevento=? and estado='AC';";
            if (jdbcTemplate.queryForObject(SQL, new OpoRecursoMapper(), ideventorecur) != null) {
                OpoRecurso objrecur = jdbcTemplate.queryForObject(SQL, new OpoRecursoMapper(), ideventorecur);
                return objrecur;
            }

            return new OpoRecurso();
        } catch (EmptyResultDataAccessException e) {
            return new OpoRecurso();
        } catch (Exception e) {
            throw e;
        }}

    @Override
    public OpoRecurso modificarOporecurso(OpoRecurso oporecurso) throws Exception {
        try {
            String SQL = "select * from modifica_oporecurso(?,?,?,?,?,?,?,?,?,?);";
            OpoRecurso modiobjeto1 = (OpoRecurso) jdbcTemplate.queryForObject(SQL, new OpoRecursoMapper(),
               oporecurso.getIdrecurso(),
               oporecurso.getIdevento(),
               oporecurso.getIdoposicion(),
               oporecurso.getIdlogtrans(),
               oporecurso.getNumero_resolucion(),
               oporecurso.getFecha_resolucion(),
               oporecurso.getTipo(),
               oporecurso.getResolucion(),
               oporecurso.getOrden_op(),
               oporecurso.getEstado());
            return modiobjeto1;

        } catch (Exception e) {
            throw e;
        }
    
    
    }



    @Override
    public List<OpoRecurso> validacionrecursorepetido(Integer numerorecurso) throws Exception {
  
    try {
            String SQL = "select * from oporecurso where numero_resolucion=? and estado='AC';";
            List<OpoRecurso> listaOprecurso = jdbcTemplate.query(SQL, new OpoRecursoMapper(), numerorecurso);
            if (!listaOprecurso.isEmpty()) {
                return listaOprecurso;
            }
            return Collections.EMPTY_LIST;
        } catch (Exception e) {
            throw e;
        }
    
    
    }
}
    