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
import snp.gob.bo.gimodel.entidad.RenTipoSigno;
import snp.gob.bo.gimodel.entidad.RenTitularRegistrado;
import snp.gob.bo.gimodel.entidad.Usuario;
import snp.gob.bo.gimodel.entidad.ObjetoEliminadoGenerico;
import snp.gob.bo.gimodel.solicitudes.entidades.Apoderados;
import snp.gob.bo.gimodel.solicitudes.entidades.Solicitantes;
import snp.gob.bo.gimodel.solicitudes.entidades.FormularioPI104;

/**
 *
 * @author Chano Rojas
 * @see ReciboService
 * @see ReciboService
 * @version 1.0, 05/06/2016
 */
public interface RenSolicitudRenovacionService {

    /**
     * Método que permite setear la conexion con dataSource
     *
     * @param dataSource
     * @throws java.lang.Exception
     */
    public void setDataSource(DataSource dataSource) throws Exception;

    /**
     * Método que permite listar rensolicitudrenovacion
     *
     * @param renSolicitudRenovacion
     * @param parametro
     * @return
     * @throws java.lang.Exception
     */
    public RenSolicitudRenovacion crudRenSolictudRenovacion(RenSolicitudRenovacion renSolicitudRenovacion, int parametro) throws Exception;

    public List<RenSolicitudRenovacion> obtieneListaRenSolictudRenovacion(RenSolicitudRenovacion renSolicitudRenovacion, int parametro) throws Exception;

    /**
     * Método que permite obtener rensolicitudrenovacion por numero de serie y
     * gestion para sr
     *
     * @param numeroSr
     * @param gestion
     * @return
     * @throws java.lang.Exception
     */
    RenSolicitudRenovacion buscaSolicitudRenovacionPorNumeroSrYGestion(Long numeroSr, int gestion) throws Exception;

    /**
     * Método que permite regsitrar una solictud de renovacion
     * rensolicitudrenovacion
     *
     * @param renSolicitudRenovacion
     * @param listarenSolicitante
     * @param listarenApoderado
     * @param listaTitularRegistrado
     * @param renDireccionDeNotificacion
     * @param lstRenTipoSigno
     * @param usuario
     * @throws java.lang.Exception
     */
    void guardaSolicitudGeneral(RenSolicitudRenovacion renSolicitudRenovacion, List<RenSolicitanteApoderado> listarenSolicitante, List<RenSolicitanteApoderado> listarenApoderado, List<RenTitularRegistrado> listaTitularRegistrado, RenDireccionDeNotificacion renDireccionDeNotificacion, List<RenTipoSigno> lstRenTipoSigno, Usuario usuario) throws Exception;

    /**
     * Método que permite regsitrar una solictud de renovacion
     * rensolicitudrenovacion para subsanacion
     *
     * Creado:Placido Castro Fecha: 24/11/2017
     * 
     * @param renSolicitudRenovacion
     * @param direccionNotificacion
     * @param lstRenTipoSigno
     * @param usuario
     * @param formularioPI104
     * @throws java.lang.Exception
     */
    void actualizarRenovacionPorSubsanacion(RenSolicitudRenovacion renSolicitudRenovacion, RenDireccionDeNotificacion direccionNotificacion, 
            List<RenTipoSigno> lstRenTipoSigno, Usuario usuario, FormularioPI104 formularioPI104) throws Exception;
               
    /**
     *
     * @author Chano Rojas
     * @param renSolicitudRenovacion
     * @param parametro
     * @return
     * @throws java.lang.Exception
     * @see ReciboService
     * @see ReciboService
     * @version 1.0, 05/06/2016
     */
    RenSolicitudRenovacion cruddosRenSolictudRenovacion(RenSolicitudRenovacion renSolicitudRenovacion, int parametro) throws Exception;

    /**
     *
     * @author Placido Castro
     * @param renSolicitudRenovacion
     * @param formularioPI104
     * @param idLogTrans
     * @return
     * @throws java.lang.Exception
     * @see ReciboService
     * @see ReciboService
     * @version 1.0, 01/12/2017
     */
    RenSolicitudRenovacion cruddosRenSolictudRenovacionSubsanacion(RenSolicitudRenovacion renSolicitudRenovacion, FormularioPI104 formularioPI104, Long idLogTrans) throws Exception;
    
    /**
     *
     * @author Chano Rojas
     * @param idRenSolicitud metodo que devuelve un objeto tipo
     * rensolicitudrenovacion por idrensolicitudrenovacion
     * @return
     * @throws java.lang.Exception
     * @see ReciboService
     * @see ReciboService
     * @version 1.0, 05/06/2016
     */
    RenSolicitudRenovacion obtenerRenSolicitudXIdRenSolicitud(Long idRenSolicitud) throws Exception;

    /**
     * metodo para listar rensolicitudrenovacion por numero de formulario
     * Creado: Ruben Ramirez Fecha: 09/11/2016
     *
     * @param nroFormulario
     * @return
     * @version 1.0, 09/11/2016
     * @throws java.lang.Exception
     */
    public RenSolicitudRenovacion buscarRenSolicitudRenovacionXNroFormulario(Long nroFormulario) throws Exception;

    /**
     * metodo para listar rensolicitudrenovacion por numero de formulario
     * Creado: Chano Rojas Fecha: 09/11/2016
     *
     * @param numeroFormulario
     * @return
     * @version 1.0, 09/11/2016
     * @throws java.lang.Exception
     */
    RenSolicitudRenovacion buscarRenSolicitud_NumeroFormulario(String numeroFormulario) throws Exception;

    /**
     * metodo para listar rensolicitudrenovacion por numero de formulario
     * Creado: Chano Rojas Fecha: 09/11/2016
     *
     * @param numero
     * @param gestion
     * @return
     * @version 1.0, 09/11/2016
     */
    RenSolicitudRenovacion obtenerAnteriorRegistroRenSolicitudRenovacion(Long numero, Integer gestion);

    /**
     * metodo para listar rensolicitudrenovacion por numero de formulario
     * Creado: Chano Rojas Fecha: 09/11/2016
     *
     * @param numero
     * @param gestion
     * @return
     * @version 1.0, 09/11/2016
     */
    RenSolicitudRenovacion obtenerSiguienteRegistroRenSolicitudRenovacion(Long numero, Integer gestion);

       /**
     * metodo para listar rensolicitudrenovacion por numero de formulario
     * Creado: Chano Rojas Fecha: 09/11/2016
     *
     * @param renSolicitudRenovacion
     * @return
     * @version 1.0, 09/11/2016
     * @throws java.lang.Exception
     */
    Boolean validaNumeroRenovacionRegistro(RenSolicitudRenovacion renSolicitudRenovacion) throws Exception;
    
     /**
     * metodo para listar rensolicitudrenovacion por numero de formulario
     * Creado: Placido Castro  Fecha: 27/03/2017
     *
     * @param numero
     * @param gestion
     * @throws java.lang.Exception
     * @return RenSolicitudRenovacion
     * @version 1.0, 27/03/2017
     */
    RenSolicitudRenovacion obtenerRegistroRenSolicitudRenovacion(Long numero, Integer gestion) throws Exception;
    
    /**
     * Metodo para realizar la eliminacion del formulario PI-104.
     *
     * Creado: Placido Castro Fecha: 28/03/2017
     *
     * @param nroFormulario
     * @return ObjetoEliminadoGenerico
     * @throws java.lang.Exception
     * @version 1.0, 28/03/2017
     */
    ObjetoEliminadoGenerico obtenerRegistroEliminadoRenSolicitudRenovacion(Long nroFormulario) throws Exception;    
    
     /**
     * Metodo para listado de solicitudes de renovaciones no concedidas
     *
     * Creado: Susana Esobar Paz Fecha: 08/12/2017
     *
     * @param sm
     * @param registro
     * @param serie
     * @return ObjetoEliminadoGenerico
     * @version 1.0, 28/03/2017
     */
    public List<RenSolicitudRenovacion> lista_rensolicitudrenovacion_noconcedidas(Long sm, Long registro, String serie);

}
