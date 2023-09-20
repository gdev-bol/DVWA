/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package snp.gob.bo.gimodel.servicio;

import java.util.List;
import javax.sql.DataSource;
import snp.gob.bo.gimodel.entidad.RenSolicitudRenovacion;
import snp.gob.bo.gimodel.entidad.RenTipoSigno;
import snp.gob.bo.gimodel.solicitudes.entidades.RenTipoSignos;

/**
 *
 * @author Chano Rojas
 * @see RenTiposignoService
 * @version 1.0, 15/09/2016
 */

    public interface RenTipoSignoService {
    
    /**
     * Método que permite setear la conexion con dataSource
     *
     * @param dataSource
     * @throws java.lang.Exception
     */
    public void setDataSource(DataSource dataSource) throws Exception;
    
  /**
     * Método que permite realizar un crud a la tabla rentiposigno
     *
     * @param renTipoSigno
     * @param parametro
     * @return 
     * @throws java.lang.Exception
     */
     RenTipoSigno crudRenTipoSigno(RenTipoSigno renTipoSigno, int parametro) throws Exception ;
  /**
     * Método que permite lista de la tabla rentiposigno
     *
     * @param renTipoSigno
     * @param parametro
     * @return 
     * @throws java.lang.Exception
     */
      List<RenTipoSigno> listaRenTipoSigno(RenTipoSigno renTipoSigno, int parametro) throws Exception ;
     
      
      public void modificarTipoSigno(RenSolicitudRenovacion rensolicitudRenovacion,List<RenTipoSigno>lstRenTipoSigno)throws Exception;
      
      
     List<RenTipoSigno> buscaRenTipoSignoPorIdSolicitud(Long idRentipoSolicitud ) ;
     
     List<RenTipoSigno> buscaTipoSigno(Long idsolicitud);

    /**
     * Método que permite regsitrar una solictud de renovacion
     * rensolicitudrenovacion para subsanacion
     *
     * Creado:Placido Castro Fecha: 24/11/2017
     * 
     * @param renSolicitudRenovacion
     * @param listaRenTipoSigno
     * @param listaTipoSignosExt
     * @param idLogTrans
     * @throws java.lang.Exception
     */
    void modificarRenTipoSignoSubsanacion(RenSolicitudRenovacion renSolicitudRenovacion, List<RenTipoSigno> listaRenTipoSigno,
            List<RenTipoSignos> listaTipoSignosExt, Long idLogTrans) throws Exception;     
      
}
