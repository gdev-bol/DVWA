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
import snp.gob.bo.gimodel.entidad.RenRenovacion;
import snp.gob.bo.gimodel.entidad.RenResolucion;
import snp.gob.bo.gimodel.mapper.RenRenovacionMapper;
import snp.gob.bo.gimodel.mapper.RenResolucionMapper;
import snp.gob.bo.gimodel.servicio.RenResolucionService;

/**
 *
 * @author Chano Rojas
 * @see RenRenovacionDominio
 * @see RenrenovacionServiceImpl
 * @version 1.0, 05/06/2016
 */
@Service("renResolucionService")
public class RenResolucionServiceImpl implements RenResolucionService {

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
    public RenResolucion crudRenResolucion(RenResolucion renResolucion,int parametro) throws Exception {
        try {
            String SQL = "select * from crud_renresolucion("
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?);";
            RenResolucion ren = (RenResolucion) jdbcTemplate.queryForObject(SQL, new RenResolucionMapper(),
            renResolucion.getIdresolucion(),
            renResolucion.getIdrenovacion(),
            renResolucion.getNumero_resolucion(),
            renResolucion.getGestion_resolucion(),
            renResolucion.getFecha_resolucion(),
            renResolucion.getObservacion_resolucion(),
            renResolucion.getDocumento_resolucion(),
            renResolucion.getEstado(),
            parametro);
           return ren;
        } catch (DataAccessException e) {
            throw e;
        }
    }
    
    @Override
    public List<RenResolucion> obtieneListadoRenResolucion(RenResolucion renResolucion,int parametro) throws Exception {
        try {
            String SQL = "select * from crud_renresolucion("
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?);";
            List<RenResolucion> listaren = jdbcTemplate.query(SQL, new RenResolucionMapper(),
            renResolucion.getIdresolucion(),
            renResolucion.getIdrenovacion(),
            renResolucion.getNumero_resolucion(),
            renResolucion.getGestion_resolucion(),
            renResolucion.getFecha_resolucion(),
            renResolucion.getObservacion_resolucion(),
            renResolucion.getDocumento_resolucion(),
            renResolucion.getEstado(),
            parametro);
            
            if(!listaren.isEmpty()){
                return listaren;
            }
            return Collections.EMPTY_LIST;
            
        } catch (DataAccessException e) {
            throw e;
        }
    }
  
    
    @Override
    public RenResolucion obtieneRenResolucionPorIdRenovacion(Long idRenRenovacion){
        try {
            String SQL = "select * from obtener_renresolucion_idrenovacion("
                    + "?);";
            List<RenResolucion> listaren = jdbcTemplate.query(SQL, new RenResolucionMapper(),
                    idRenRenovacion);
            if (!listaren.isEmpty()) {
                return listaren.get(0);
            } else {
                return new RenResolucion();
            }
        } catch (DataAccessException e) {
            throw e;
        }
    }
    @Override
    public List<RenResolucion> obtieneRenResolucionPorIdResolucion(Long idRenResolucion){
        try {
            String SQL = "select * from renresolucion where idresolucion='"+idRenResolucion+"'";
            List<RenResolucion> listaren = jdbcTemplate.query(SQL, new RenResolucionMapper());
            if (!listaren.isEmpty()) {
                return listaren;
            } else {
                return null ;
            }
        } catch (DataAccessException e) {
            throw e;
        }
    }
    
    
    @Override
    public List<RenResolucion> obtienelistaRenovacion(){
        try {
            String SQL = "select * from renresolucion";
            List<RenResolucion> listaren = jdbcTemplate.query(SQL, new RenResolucionMapper());
            if (!listaren.isEmpty()) {
                return listaren;
            } else{
                return null;
            }
        } catch (DataAccessException e) {
            throw e;
        }
    }
    
    
    
    
    
}
