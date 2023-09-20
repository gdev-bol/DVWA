/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package snp.gob.bo.gimodel.servicio;

import java.util.List;
import javax.sql.DataSource;
import snp.gob.bo.gimodel.entidad.RenSolicitudRenovacion;
import snp.gob.bo.gimodel.entidad.RenTitularRegistrado;
import snp.gob.bo.gimodel.solicitudes.entidades.RenTitularRegistrados;

/**
 *
 * @author Chano Rojas
 * @see RenResolucionService
  * @version 1.0, 05/06/2016
 */

public interface RenTitularRegistradoService {
    
    /**
     * Método que permite setear la conexion con dataSource
     *
     * @param dataSource
     * @throws java.lang.Exception
     */
    public void setDataSource(DataSource dataSource) throws Exception;
    
    
    /**
     * Método que permite realizar  crud a la tabala renresolucion
     *
     * @param renTitularRegistrado
     * @param parametro
     * @return 
     * @throws java.lang.Exception
     */
      RenTitularRegistrado crudRenTitularRegistrado(RenTitularRegistrado renTitularRegistrado,int parametro) throws Exception;
      RenTitularRegistrado crudDosRenTitularRegistrado(RenTitularRegistrado renTitularRegistrado,int parametro) throws Exception;
    
        /**
     * Método que permite realizar  listar la tabla rentitularregistrado
     *
     * @param renTitularRegistrado
     * @param parametro
     * @return 
     * @throws java.lang.Exception
     */
    List<RenTitularRegistrado> obtieneListadoRenTitularRegistrado(RenTitularRegistrado renTitularRegistrado,int parametro) throws Exception;
        /**
     * Método que permite realizar  listar la tabla rentitularregistrado por numero de solicitud 
     *
     * @param idSolicitud
     * @return 
     * @throws java.lang.Exception
     */
    List<RenTitularRegistrado> buscaRenTitularRegistradoPorIdSolicitud(Long  idSolicitud) ;
    
    
    int encuentraPosicionListadoTitularRegistrado(List<RenTitularRegistrado> listaTitularRegistrado, RenTitularRegistrado titularRegistrado);
    
    void modificaListaTitularRegistrado(RenSolicitudRenovacion rensolicitudRenovacion, List<RenTitularRegistrado>listaTitularRegistrado)throws Exception;
  
    RenTitularRegistrado buscaRenTitularRegistradoPorIdSolicitud(RenTitularRegistrado renTitularRegistrado);
    
    
    /**
     * Método que permite regsitrar una solictud de renovacion
     * rensolicitudrenovacion para subsanacion
     *
     * Creado:Placido Castro Fecha: 24/11/2017
     * 
     * @param idSolicitudRenovacion
     * @param listaTitularRegistradosSipi
     * @param idLogTrans
     * @throws java.lang.Exception
     */
    void modificarListaTitularRegistradoSubsanacion(Long idSolicitudRenovacion, List<RenTitularRegistrados> listaTitularRegistradosSipi,Long idLogTrans) throws Exception;
        
}
