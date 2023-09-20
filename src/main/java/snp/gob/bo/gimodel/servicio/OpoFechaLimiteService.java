/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package snp.gob.bo.gimodel.servicio;

import javax.sql.DataSource;
import snp.gob.bo.gimodel.entidad.OpoFechalimite;

/**
 *
 * @author Luis Angel Quispe Limachi
 */
public interface OpoFechaLimiteService {
    
    /**
     * Método que permite setear la conexion con dataSource
     *
     * @param dataSource
     * @throws java.lang.Exception
     */
  public void setDataSource(DataSource dataSource)throws Exception;
  /**
     * Metodo para guardar la opoFechaLimite
     * creado: Luis Quispe Fecha: 03/10/2016
     *   
     * @param opofechalimite
     * @return OpoFechalimite
     * @throws java.lang.Exception
     */
  public OpoFechalimite guardaOpoFechalimite(OpoFechalimite opofechalimite) throws Exception;
  
  
  
}
