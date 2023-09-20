/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package snp.gob.bo.gimodel.servicio;

import javax.sql.DataSource;
import snp.gob.bo.gimodel.entidad.OpoObjetoDmteDmdoNotiSoliapo;

/**
 *
 * @author Luis Angel Quispe Limachi
 */
public interface OpoGeneralService {
    /**
     * Método que permite setear la conexion con dataSource
     *
     * @param dataSource
     * @throws java.lang.Exception
     */
     public void setDataSource(DataSource dataSource)throws Exception;
        /**
     * Metodo para guardar tramite de oposicion por partes
     * creado: Luis Quispe Fecha: 05/08/2016
     *
     * @param objetoddns
     * @throws java.lang.Exception
     */
      public void guardartramiteopo(OpoObjetoDmteDmdoNotiSoliapo objetoddns)throws Exception;
      
      /**
     * Metodo para guardar tramite de oposicion devuelve el mismo objto
     * creado: Luis Quispe Fecha: 05/08/2016
     *
     * @param objetoddns
     * @throws java.lang.Exception
     */
      public OpoObjetoDmteDmdoNotiSoliapo guardartramiteopoext(OpoObjetoDmteDmdoNotiSoliapo objetoddns)throws Exception;
       
}
