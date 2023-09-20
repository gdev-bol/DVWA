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
import snp.gob.bo.gimodel.entidad.RenDireccionDeNotificacion;
    import snp.gob.bo.gimodel.mapper.RenDireccionDeNotificacionMapper;
import snp.gob.bo.gimodel.servicio.RenDireccionDeNotificacionService;
import snp.gob.bo.gimodel.solicitudes.entidades.DireccionNotificaciones;

/**
 *
 * @author Chano Rojas
 * @see RenRenovacionDominio
 * @see RenrenovacionServiceImpl
 * @version 1.0, 05/06/2016
 */
@Service("renDireccionDeNotificacionService")
public class RenDireccionDeNotificacionServiceImpl implements RenDireccionDeNotificacionService {

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
    public RenDireccionDeNotificacion crudRenDireccionDeNotificacion(RenDireccionDeNotificacion renDireccionDeNotificacion,int parametro) throws Exception {
        try {
            String SQL = "select * from crud_rendirecciondenotificacion("
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
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
            
            RenDireccionDeNotificacion renDireccion = (RenDireccionDeNotificacion) jdbcTemplate.queryForObject(SQL, new RenDireccionDeNotificacionMapper(),
            renDireccionDeNotificacion.getIddirecciondenotificacion(),
            renDireccionDeNotificacion.getIdsolicitudrenovacion(),
            renDireccionDeNotificacion.getIdmodificacion(),
            renDireccionDeNotificacion.getIdlogtrans(),
            renDireccionDeNotificacion.getCiudad_notificacion(),
            renDireccionDeNotificacion.getZona_barrio(),
            renDireccionDeNotificacion.getAvenida_calle(),
            renDireccionDeNotificacion.getNumero(),
            renDireccionDeNotificacion.getEdificio(),
            renDireccionDeNotificacion.getPiso(),
            renDireccionDeNotificacion.getDepartamento(),
            renDireccionDeNotificacion.getReferencia_direccion(),
            renDireccionDeNotificacion.getCorreo_electronico(),
            renDireccionDeNotificacion.getTelefono(),
            renDireccionDeNotificacion.getCelular(),
            renDireccionDeNotificacion.getEstado(),
            parametro);
           return renDireccion;
        } catch (DataAccessException e) {
            throw e;
        }
    }
    
    @Override
    public RenDireccionDeNotificacion crudRenDireccionDeNotificacionSubsanacion(RenDireccionDeNotificacion renDireccionDeNotificacion, DireccionNotificaciones direccionExt, Long idLogTrans) throws Exception {
        try {
            int operacionModificar=2;
            String SQL = "select * from crud_rendirecciondenotificacion("
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
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
                           
            RenDireccionDeNotificacion renDireccion = (RenDireccionDeNotificacion) jdbcTemplate.queryForObject(SQL, new RenDireccionDeNotificacionMapper(),        
            renDireccionDeNotificacion.getIddirecciondenotificacion(),
            renDireccionDeNotificacion.getIdsolicitudrenovacion(),
            renDireccionDeNotificacion.getIdmodificacion(),
            idLogTrans,
            direccionExt.getCiudadNotificacion(), //renDireccionDeNotificacion.getCiudad_notificacion(),
            direccionExt.getZonaBarrio(),//renDireccionDeNotificacion.getZona_barrio(),
            direccionExt.getAvenidaCalle(),//renDireccionDeNotificacion.getAvenida_calle(),
            direccionExt.getNumeroDomicilio(),//renDireccionDeNotificacion.getNumero(),
            direccionExt.getNombreEdificio(),//renDireccionDeNotificacion.getEdificio(),
            direccionExt.getPiso(),//renDireccionDeNotificacion.getPiso(),
            direccionExt.getDepartamento(),//renDireccionDeNotificacion.getDepartamento(),
            direccionExt.getReferencia(),//renDireccionDeNotificacion.getReferencia_direccion(),
            direccionExt.getCorreoelectronico(),//renDireccionDeNotificacion.getCorreo_electronico(),
            direccionExt.getTelefono(),//renDireccionDeNotificacion.getTelefono(),
            direccionExt.getCelular(),//renDireccionDeNotificacion.getCelular(),
            renDireccionDeNotificacion.getEstado(),
            operacionModificar);
           return renDireccion;
        } catch (DataAccessException e) {
            throw e;
        }
    }
    
    @Override
    public List<RenDireccionDeNotificacion> obtieneListaRenDireccionDeNotificacion(RenDireccionDeNotificacion renDireccionDeNotificacion,int parametro) throws Exception {
        try {
            String SQL = "select * from crud_rendirecciondenotificacion("
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
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
            List<RenDireccionDeNotificacion> listarenDireccion = jdbcTemplate.query(SQL, new RenDireccionDeNotificacionMapper(),
            renDireccionDeNotificacion.getIddirecciondenotificacion(),
            renDireccionDeNotificacion.getIdsolicitudrenovacion(),
            renDireccionDeNotificacion.getIdmodificacion(),
            renDireccionDeNotificacion.getIdlogtrans(),
            renDireccionDeNotificacion.getCiudad_notificacion(),
            renDireccionDeNotificacion.getZona_barrio(),
            renDireccionDeNotificacion.getAvenida_calle(),
            renDireccionDeNotificacion.getNumero(),
            renDireccionDeNotificacion.getEdificio(),
            renDireccionDeNotificacion.getPiso(),
            renDireccionDeNotificacion.getDepartamento(),
            renDireccionDeNotificacion.getReferencia_direccion(),
            renDireccionDeNotificacion.getCorreo_electronico(),
            renDireccionDeNotificacion.getTelefono(),
            renDireccionDeNotificacion.getCelular(),
            renDireccionDeNotificacion.getEstado(),
            parametro);
            if(!listarenDireccion.isEmpty()){
                return listarenDireccion;
            }
           return Collections.EMPTY_LIST;
        } catch (DataAccessException e) {
            throw e;
        }
    }
    
    @Override
    public RenDireccionDeNotificacion crudDosRenDireccionDeNotificacion(RenDireccionDeNotificacion renDireccionDeNotificacion,int parametro) throws Exception {
        try {
            String SQL = "select * from crud_rendirecciondenotificacion("
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
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
            List<RenDireccionDeNotificacion> listarenDireccion = jdbcTemplate.query(SQL, new RenDireccionDeNotificacionMapper(),
            renDireccionDeNotificacion.getIddirecciondenotificacion(),
            renDireccionDeNotificacion.getIdsolicitudrenovacion(),
            renDireccionDeNotificacion.getIdmodificacion(),
            renDireccionDeNotificacion.getIdlogtrans(),
            renDireccionDeNotificacion.getCiudad_notificacion(),
            renDireccionDeNotificacion.getZona_barrio(),
            renDireccionDeNotificacion.getAvenida_calle(),
            renDireccionDeNotificacion.getNumero(),
            renDireccionDeNotificacion.getEdificio(),
            renDireccionDeNotificacion.getPiso(),
            renDireccionDeNotificacion.getDepartamento(),
            renDireccionDeNotificacion.getReferencia_direccion(),
            renDireccionDeNotificacion.getCorreo_electronico(),
            renDireccionDeNotificacion.getTelefono(),
            renDireccionDeNotificacion.getCelular(),
            renDireccionDeNotificacion.getEstado(),
            parametro);
            if(!listarenDireccion.isEmpty()){
                return listarenDireccion.get(0);
            }
           return new RenDireccionDeNotificacion();
        } catch (DataAccessException e) {
            throw e;
        }
    }
        
    @Override
    public RenDireccionDeNotificacion buscaDireccionDeNotificacionPorIdSolicitud(Long  idSolicitud) throws Exception {
        try {
            String SQL = "select * from obtiene_rendirecciondenotificacionporidsolicitud("
                    + "?);";
           List<RenDireccionDeNotificacion> listaren =jdbcTemplate.query(SQL, new RenDireccionDeNotificacionMapper(),
               idSolicitud
               );
           if(!listaren.isEmpty()){
               return listaren.get(0);
           }else{
               return new RenDireccionDeNotificacion();
           }
        } catch (DataAccessException e) {
            throw e;
        }
    }
    
    
    
    
    
    
    
}
