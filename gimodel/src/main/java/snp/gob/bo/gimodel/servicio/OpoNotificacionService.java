/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.servicio;

import java.util.List;
import javax.sql.DataSource;
import snp.gob.bo.gimodel.entidad.OpoNotificacion;

/**
 *
 * @author Luis Angel Quispe Limachi
 */
public interface OpoNotificacionService {
/**
     * Método que permite setear la conexion con dataSource
     *
     * @param dataSource
     * @throws java.lang.Exception
     */
    public void setDataSource(DataSource dataSource)throws Exception;
 /**
     * Metodo para guardar oponotificacion
     * Creado: Luis Quispe Fecha: 10/10/2016
     *
     * @param oponotificacion
     * @return OpoNotificacion
     * @throws java.lang.Exception
     */
    public OpoNotificacion guardardirnoti(OpoNotificacion oponotificacion)throws Exception;
 /**
     * Metodo para obtener la lista de oponotificacion por id de la marcademandada
     * Creado: Luis Quispe Fecha: 11/10/2016
     *
     * @param idmarcadmda
     * @return List<OpoNotificacion>
     * @throws java.lang.Exception
     */
    public List<OpoNotificacion> obtenerListadoNotificaciondmda(Long idmarcadmda) throws Exception;

     /**
     * Metodo para obtener la lista de oponotificacion por id de la marcademandante
     * Creado: Luis Quispe Fecha: 11/10/2016
     *
     * @param idmarcadmte
     * @return List<OpoNotificacion>
     * @throws java.lang.Exception
     */
    public List<OpoNotificacion> obtenerListadoNotificaciondmte(Long idmarcadmte) throws Exception;
     /**
     * Metodo para obtener OpoNotificacion por idmarcademandada  
     * Creado: Luis Quispe Fecha: 12/10/2016
     *
     * @param idmarcadmda
     * @return OpoNotificacion
     * @throws java.lang.Exception
     */
    public OpoNotificacion obtenerNotificacionxmarcadmda(Long idmarcadmda) throws Exception;
/**
     * Metodo para obtener OpoNotificacion por idmarcademandante  
     * Creado: Luis Quispe Fecha: 12/10/2016
     *
     * @param idmarcadnte
     * @return OpoNotificacion
     * @throws java.lang.Exception
     */
    public OpoNotificacion obtenerNotificacionxmarcadmte(Long idmarcadnte) throws Exception;
/**
     * Metodo para modificar  OpoNotificacion   
     * Creado: Luis Quispe Fecha: 13/10/2016
     *
     * @param oponotificacionmodi
     * @return OpoNotificacion
     * @throws java.lang.Exception
     */
    public OpoNotificacion modificarOponotificacionmodi(OpoNotificacion oponotificacionmodi) throws Exception;
    /**
     * Metodo para obtener notificacin por idmarcademandada   
     * Creado: Luis Quispe Fecha: 13/10/2016
     *
     * @param idmarcademandmda
     * @return Long
     * @throws java.lang.Exception
     */
    public Long obtenreidnotificacionxmarcadmda(Long idmarcademandmda)throws Exception;
     /**
     * Metodo para obtener notificacin por idmarcademandante 
     * Creado: Luis Quispe Fecha: 13/10/2016
     *
     * @param idmarcademandmte
     * @return Long
     * @throws java.lang.Exception
     */
    public Long obtenreidnotificacionxmarcadmte(Long idmarcademandmte)throws Exception;
      /**
     * Metodo para eliminar oponotificacion de la marca demandada por idnotificacion y idmarcademandada 
     * Creado: Luis Quispe Fecha: 14/10/2016
     *
     * @param idnotifica
     * @param iddmda
     * @return String
     * @throws java.lang.Exception
     */
    public String eliminarOponotificacionxdmda(Long idnotifica,Long iddmda) throws Exception;
    /**
     * Metodo para eliminar oponotificacion de la marca demandante por idnotificacion y idmarcademandante 
     * Creado: Luis Quispe Fecha: 15/10/2016
     *
     * @param idnotifica
     * @param iddmte
     * @return String
     * @throws java.lang.Exception
     */
    public String eliminarOponotificacionxdmte(Long idnotifica,Long iddmte) throws Exception;
    

}
