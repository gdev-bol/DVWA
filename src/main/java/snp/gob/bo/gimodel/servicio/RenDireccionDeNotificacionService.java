/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package snp.gob.bo.gimodel.servicio;

import java.util.List;
import javax.sql.DataSource;
import snp.gob.bo.gimodel.entidad.RenDireccionDeNotificacion;
import snp.gob.bo.gimodel.solicitudes.entidades.DireccionNotificaciones;

/**
 *
 * @author Chano Rojas
 * @see RenDireccionDeNotificacionService
  * @version 1.0, 05/06/2016
 */

public interface RenDireccionDeNotificacionService {
    
    /**
     * Método que permite setear la conexion con dataSource
     *
     * @param dataSource
     * @throws java.lang.Exception
     */
    public void setDataSource(DataSource dataSource) throws Exception;
    
    
    /**
     * Método que permite realizar  el  el registro modificacion y eliminacion de la tabla rendirecciondenotificacion
     *
     * @param renDireccionDeNotificacion
     * @param parametro
     * @return 
     * @throws java.lang.Exception
     */
 RenDireccionDeNotificacion crudRenDireccionDeNotificacion(RenDireccionDeNotificacion renDireccionDeNotificacion,int parametro) throws Exception ;    

    /**
     * Método que permite realizar  el  el registro modificacion y eliminacion de la tabla rendirecciondenotificacion
     *
     * * Creado:Placido Castro Fecha: 04/12/2017
     * 
     * @param direccionNotificacion
     * @param direccionExt
     * @param idLogTrans
     * @return 
     * @throws java.lang.Exception
     */
 RenDireccionDeNotificacion crudRenDireccionDeNotificacionSubsanacion(RenDireccionDeNotificacion direccionNotificacion, DireccionNotificaciones direccionExt, Long idLogTrans) throws Exception ;   
 
    /**
     * Método que permite realizar  el listado de la tabla rendirecciondenotificacion
     *
     * @param renDireccionDeNotificacion
     * @param parametro
     * @return 
     * @throws java.lang.Exception
     */
List<RenDireccionDeNotificacion> obtieneListaRenDireccionDeNotificacion(RenDireccionDeNotificacion renDireccionDeNotificacion,int parametro) throws Exception ;
    /**
     * Método que permite realizar  el listado de la tabla rendirecciondenotificacion por idsolicitudrenovacion
     *
     * @param idSolicitud
     * @return 
     * @throws java.lang.Exception
     */
RenDireccionDeNotificacion buscaDireccionDeNotificacionPorIdSolicitud(Long  idSolicitud) throws Exception;


RenDireccionDeNotificacion crudDosRenDireccionDeNotificacion(RenDireccionDeNotificacion renDireccionDeNotificacion,int parametro) throws Exception;
    
}
