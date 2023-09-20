/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.servicio;

import java.util.List;
import javax.sql.DataSource;
import snp.gob.bo.gimodel.entidad.Dominio;
import snp.gob.bo.gimodel.entidad.OpoResolucion;



/**
 *
 * @author Luis Angel Quispe Limachi
 */
public interface OpoResolucionService {
    
    /**
     * Método que permite setear la conexion con dataSource
     *
     * @param dataSource
     * @throws java.lang.Exception
     */

     public void setDataSource(DataSource dataSource)throws Exception;
    
      /**
     * Metodo para obtener OpoNotificacion por idmarcademandada  
     * Creado: Luis Quispe Fecha: 15/11/2016
     *
     * @param idopo
     * @return OpoResolucion
     * @throws java.lang.Exception
     */
    public OpoResolucion obtenerObjOporesolucion(Long idopo) throws Exception;
    
    /**
     * Metodo para obtener listado resolucion  
     * Creado: Luis Quispe Fecha: 02/12/2016
     *
     * @param tipodominio
     * @return List<Dominio>
     * @throws java.lang.Exception
     */
     public List<Dominio> obtenerListadoOpoRecursoxtipodominio(String tipodominio) throws Exception;
      /**
     * Metodo para guardar oporesolucion 
     * creado: Luis Quispe Fecha: 03/12/2016
     *
     * @param oporesolucion
     * @return OpoResolucion
     * @throws java.lang.Exception
     */
    public OpoResolucion guardaOpoResolucion(OpoResolucion oporesolucion) throws Exception;
    
     /**
     * Metodo para extraer el objeto por el iddelevento
     * creado: Luis Quispe Fecha: 03/12/2016
     *
     * @param ideventores
     * @return OpoResolucion
     * @throws java.lang.Exception
     */
    public OpoResolucion extraeResolucionxidevento(Long ideventores) throws Exception;
    
     /**
     * Metodo para modificar oporesolucion 
     * creado: Luis Quispe Fecha: 03/12/2016
     *
     * @param oporesolucion
     * @return OpoResolucion
     * @throws java.lang.Exception
     */
    public OpoResolucion modificarOporesolucion(OpoResolucion oporesolucion) throws Exception;
    
}
