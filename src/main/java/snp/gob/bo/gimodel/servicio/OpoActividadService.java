/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package snp.gob.bo.gimodel.servicio;

import java.util.List;
import javax.sql.DataSource;
import snp.gob.bo.gimodel.entidad.OpoActividad;

/**
 *
 * @author Luis Angel Quispe Limachi
 * @version 1.0, 20/07/2016
 */
public interface OpoActividadService {
    /**
     * Método que permite setear la conexion con dataSource
     *
     * @param dataSource
     * @throws java.lang.Exception
     */
     public void setDataSource(DataSource dataSource)throws Exception;
       /**
     * Metodo para mostrar la descripcion de la actividad 
     * creado: Luis Quispe Fecha: 05/08/2016
     *
     * @param idacti
     * @return String
     * @throws java.lang.Exception
     */
      public String muestranomdelaactividadxidactividad(Long idacti)throws Exception;
         /**
     * Metodo para mostrar la descripcion de la actividad 
     * creado: Luis Quispe Fecha: 08/09/2016
     *
     * @param idactividad
     * @return String
     * @throws java.lang.Exception
     */
      public Long muestraidestadoxidactividad(Long idactividad)throws Exception;
      /**
     * Metodo para obtener la lista de actividades de la tabla actividad
     * Creado: Luis Quispe Fecha: 27/09/2016
     *
     * @return List<OpoActividad>
     * @throws java.lang.Exception
     */      
      public List<OpoActividad> obtenerListadoActividades() throws Exception;
      
}
