/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.servicio;

import java.util.List;
import javax.sql.DataSource;
import snp.gob.bo.gimodel.entidad.SeccionSubPublicacion;

/**
 *
 * @author Ruben Ramirez
 * @see SeccionSubPublicacionServiceImpl
 * @version 1.0, 03/11/2016
 */
public interface SeccionSubPublicacionService {
    
   /**
     * Método que permite setear la conexion con dataSource
     *
     * Creado: Ruben Ramirez Fecha: 03/11/2016
     * 
     * @param dataSource
     * @throws java.lang.Exception
     */
    public void setDataSource(DataSource dataSource) throws Exception;
    
    
    
    /**
     * Método que obtiene registros de una determinada busqueda
     *
     * Creado: Ruben Ramirez Fecha: 03/11/2016
     * 
     * @return List, listado
     * @throws java.lang.Exception
     */
    public List<String> listadoSecciones() throws Exception;
    
    
    /**
     * Método que obtiene registros de una determinada busqueda
     *
     * Creado: Ruben Ramirez Fecha: 03/11/2016
     * 
     * @return List, listado
     * @throws java.lang.Exception
     */
    public List<String> listadoSubSecciones() throws Exception;
    
   
    
}
