/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.servicio;

import java.util.List;
import javax.sql.DataSource;
import snp.gob.bo.gimodel.entidad.RenDireccionDeNotificacion;
import snp.gob.bo.gimodel.entidad.RenSolicitanteApoderado;
import snp.gob.bo.gimodel.entidad.RenSolicitudRenovacion;
import snp.gob.bo.gimodel.solicitudes.entidades.Apoderados;
import snp.gob.bo.gimodel.solicitudes.entidades.Solicitantes;

/**
 * @author Chano Rojas
 * @see RenSolicitanteApoderadoService
 * @see RenSolicitanteApoderadoService
 * @version 1.0, 05/06/2016
 */
public interface RenSolicitanteApoderadoService {

    /**
     * Método que permite setear la conexion con dataSource
     *
     * @param dataSource
     * @throws java.lang.Exception
     */
    public void setDataSource(DataSource dataSource) throws Exception;

    /**
     * Método que permite realizar el crud a la base de datos los cuales se
     * especifican por un parametro de entrada 1 crear, 2 modificar, 3 eliminar
     * y 4 listar la tabla
     *
     * @param renSolicitanteApoderado
     * @param parametro
     * @return RenSolicitanteApoderado
     * @throws java.lang.Exception
     */
    RenSolicitanteApoderado crudRenSolicitanteApoderado(RenSolicitanteApoderado renSolicitanteApoderado, int parametro) throws Exception;

    /**
     * Método que permite obtener el listado de la entidad
     * RenSolicitanteApoderado
     *
     * @param renSolicitanteApoderado
     * @param parametro
     * @return RenSolicitanteApoderado
     * @throws java.lang.Exception
     */

    List<RenSolicitanteApoderado> obtenerListadoRenSolicitanteApoderado(RenSolicitanteApoderado renSolicitanteApoderado, int parametro) throws Exception;

    /**
     * Método que permite obtener el listado de la entidad
     * RenSolicitanteApoderado por idsolicitudrenovacion y tipopersona
     *
     * @param numeroSr
     * @param tipoPersona
     * @return RenSolicitanteApoderado
     * @throws java.lang.Exception
     */
    List<RenSolicitanteApoderado> buscaSolicitanteApoderadoPoridSolicitudyTipoPersona(Long numeroSr, String tipoPersona) throws Exception;

    /**
     * Método que permite obtener la entidad enDireccionDeNotificacion
     *
     * creado: Placido Castro fecha 30/11/2017
     * @param idSolicitudRenovacion
     * @return RenSolicitanteApoderado
     * @throws java.lang.Exception
     */
    RenDireccionDeNotificacion buscarRenDireccionDeNotificacionXidsolicitudrenovacion(Long idSolicitudRenovacion) throws Exception;    
    
    /**
     * Método que permite obtener el listado de la entidad
     * RenSolicitanteApoderado por idsolicitudrenovacion y tipopersona
     *
     * @param idrenSolicitudRenovacion
     * @param tipoPersona
     * @return RenSolicitanteApoderado
     * @throws java.lang.Exception
     */
    
    List<RenSolicitanteApoderado> buscaSolicitanteApoderadoPoridSolicitudyTipoPersonaRenovacion(Long idrenSolicitudRenovacion, String tipoPersona) throws Exception;
    
     /**
     * Método que permite obtener el listado de la entidad
     * RenSolicitanteApoderado por idsolicitudrenovacion y tipopersona
     *
     * @param renSolicitanteApoderado
     * @param parametro
         * @return RenSolicitanteApoderado
     * @throws java.lang.Exception
     */
    
    RenSolicitanteApoderado crudDosRenSolicitanteApoderado(RenSolicitanteApoderado renSolicitanteApoderado, int parametro) throws Exception;

    /**
     * Método que permite obtener el numero de posicion de un registro en un alista de solicitante apoderado
     * RenSolicitanteApoderado por idsolicitudrenovacion y tipopersona
     *
     * @author Chano Rojas 12/09/16
     * @param listadoSolicitanteApoderado
     * @param rensolicitanteApoderado
     * @return RenSolicitanteApoderado
     */
    int encuentraPosicionListadoSolicitanteApoderado(List<RenSolicitanteApoderado> listadoSolicitanteApoderado, RenSolicitanteApoderado rensolicitanteApoderado);
    
    void modificaListaSolicitanteApoderado(RenSolicitudRenovacion rensolicitudRenovacion, List<RenSolicitanteApoderado> listaSolicitanteApoderado, String tipoSoliOApo)throws Exception;
    
    void modificaListaApoderado(RenSolicitudRenovacion rensolicitudRenovacion, List<RenSolicitanteApoderado> listaSolicitanteApoderado, String tipoSoliOApo) throws Exception;
    
    public RenSolicitanteApoderado obtenerRenSolicitanteApoderado(Long idSolicitanteApoderado, String tipoPersona) throws Exception;
    
    /**
     * Método que permite regsitrar una solictud de renovacion
     * rensolicitudrenovacion para subsanacion
     *
     * Creado:Placido Castro Fecha: 04/12/2017
     * 
     * @param idSolicitudRenovacion
     * @param listaSolicitantesSipi
     * @param tipoPersona
     * @param IdLogTrans
     * @throws java.lang.Exception
     */
    void modificarListaSolicitanteSubsanacion(Long idSolicitudRenovacion, List<Solicitantes> listaSolicitantesSipi, String tipoPersona, Long IdLogTrans) throws Exception;
   
    /**
     * Método que permite regsitrar una solictud de renovacion
     * rensolicitudrenovacion para subsanacion
     *
     * @author Placido Castro
     * 
     * @param idSolicitudRenovacion
     * @param listaApoderadosSipi
     * @param tipoPersona
     * @param IdLogTrans
     * @throws java.lang.Exception
     */
    void modificarListaApoderadoSubsanacion(Long idSolicitudRenovacion, List<Apoderados> listaApoderadosSipi, String tipoPersona, Long IdLogTrans) throws Exception;    
    
    /**
     * Método que permite regsitrar una solictud de renovacion
     * rensolicitudrenovacion para subsanacion
     *
     * @author Placido Castro
     * 
     * @param idSolicitudRenovacion
     * @param tipoPersona
     * @throws java.lang.Exception
     */
    List<RenSolicitanteApoderado> buscarSolicitanteApoderadoPoridSolicitudyTipoPersona(Long idSolicitudRenovacion, String tipoPersona) throws Exception;
    
}
