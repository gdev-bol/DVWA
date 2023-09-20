/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.servicio;

import java.util.Date;
import java.util.List;
import javax.sql.DataSource;
import snp.gob.bo.gimodel.entidad.Historial;
import snp.gob.bo.gimodel.entidad.RenDireccionDeNotificacion;
import snp.gob.bo.gimodel.entidad.RenSolicitanteApoderado;
import snp.gob.bo.gimodel.entidad.RenSolicitudRenovacion;
import snp.gob.bo.gimodel.entidad.RenTipoSigno;
import snp.gob.bo.gimodel.entidad.RenTitularRegistrado;
import snp.gob.bo.gimodel.entidad.Usuario;
import snp.gob.bo.gimodel.solicitudes.entidades.Apoderados;
import snp.gob.bo.gimodel.solicitudes.entidades.DireccionNotificaciones;
import snp.gob.bo.gimodel.solicitudes.entidades.FormularioPI104;
import snp.gob.bo.gimodel.solicitudes.entidades.RenTipoSignos;
import snp.gob.bo.gimodel.solicitudes.entidades.RenTitularRegistrados;
import snp.gob.bo.gimodel.solicitudes.entidades.Solicitantes;

/**
 *
 * @author Chano Rojas
 * @see Historial
 * @see SigHistorialServiceImpl
 * @see HistorialService
 * @see HistorialServiceImpl
 * @version 1.0, 28/11/2016
 */
public interface RenHistorialService {

    /**
     * Agregar el dataSource.
     *
     * @author Chano Rojas
     * @param dataSource
     * @throws java.lang.Exception
     * @version 1.0, 28/11/2016
     */
    public void setDataSource(DataSource dataSource) throws Exception;

    /**
     * Método que genera el historial de SigSolicitante en caso que corresponda
     *
     * Creado:Placido Castro Fecha: 04/12/2017
     * 
     * @param renSolicitudRenovacion
     * @param formularioPI104
     * @param idLogTrans
     * @param estado_renovacion
     * @param ubicacion_renovacion
     * @param fechaSistema
     * @param idUsuario
     * @throws java.lang.Exception
     */
    void generarHistorialRenSolicitudRenovacionSubsanacion(RenSolicitudRenovacion renSolicitudRenovacion, FormularioPI104 formularioPI104, Long  idLogTrans,
            String estado_renovacion, String ubicacion_renovacion, Date fechaSistema, Long idUsuario) throws Exception;
    
    /**
     * Método que genera el historial de SigSolicitante en caso que corresponda
     *
     * @author Chano Rojas
     * @param idRenSolcitudRenovacion
     * @param listaSolicitantesSipi
     * @param tipoPersona
     * @param estado_marca
     * @param ubicacion
     * @param fechaSistema
     * @param idLogTrans
     * @param idUsuario
     * @throws java.lang.Exception
     * @version 1.0, 28/11/2016
     */
    void generarHistorialRenSolicitanteSubsanacion(Long idRenSolcitudRenovacion, List<Solicitantes> listaSolicitantesSipi,
            String tipoPersona, String estado_marca, String ubicacion, Date fechaSistema, Long idLogTrans, Long idUsuario) throws Exception;

    /**
     * Método que genera el historial de modificaciones de Solicitante para subsanacion
     *
     * -------------------------------------------------------------------------
     * Creado: Placido Castro Fecha: 12/112/2017
     *
     * @param solicitanteHidra
     * @param solicitanteSipi
     * @return 
     * @throws java.lang.Exception
     */
    public String modificarRenSolicitanteSubsanacion(RenSolicitanteApoderado solicitanteHidra, Solicitantes solicitanteSipi) throws Exception;
    
    /**
     * Método que genera el historial de eliminaciones de Solicitante para subsanacion
     *
     * -------------------------------------------------------------------------
     * Creado: Placido Castro Fecha: 12/112/2017
     *
     * @param solicitanteHidra
     * @return 
     * @throws java.lang.Exception
     */
    public String eliminarRenSolicitanteSubsanacion(RenSolicitanteApoderado solicitanteHidra) throws Exception;
    
    /**
     * Método que genera el historial de adiciones de Solicitante para subsanacion
     *
     * -------------------------------------------------------------------------
     * Creado: Placido Castro Fecha: 12/112/2017
     *
     * @param solicitanteSipi
     * @return 
     * @throws java.lang.Exception
     */
    public String adicionarRenSolicitanteSubsanacion(Solicitantes solicitanteSipi) throws Exception;
        
    /**
     * Método que genera el historial de SigSolicitante en caso que corresponda
     *
     * @author Chano Rojas
     * @param idRenSolcitudRenovacion
     * @param listaRenApoderadosExt
     * @param tipoPersona
     * @param estado_marca
     * @param ubicacion
     * @param fechaSistema
     * @param idLogTrans
     * @param idUsuario
     * @throws java.lang.Exception
     * @version 1.0, 28/11/2016
     */
    void generarHistorialRenApoderadoSubsanacion(Long idRenSolcitudRenovacion, List<Apoderados> listaRenApoderadosExt,
            String tipoPersona, String estado_marca, String ubicacion, Date fechaSistema, Long idLogTrans, Long idUsuario) throws Exception;

    /**
     * Método que genera el historial de modificaciones de Solicitante para subsanacion
     *
     * -------------------------------------------------------------------------
     * Creado: Placido Castro Fecha: 12/112/2017
     *
     * @param apoderadoHidra
     * @param apoderadoSipi
     * @return 
     * @throws java.lang.Exception
     */
    public String modificarRenApoderadoSubsanacion(RenSolicitanteApoderado apoderadoHidra, Apoderados apoderadoSipi) throws Exception;
    
    /**
     * Método que genera el historial de eliminaciones de Solicitante para subsanacion
     *
     * -------------------------------------------------------------------------
     * Creado: Placido Castro Fecha: 12/112/2017
     *
     * @param apoderadoHidra
     * @return 
     * @throws java.lang.Exception
     */
    public String eliminarRenApoderadoSubsanacion(RenSolicitanteApoderado apoderadoHidra) throws Exception;
    
    /**
     * Método que genera el historial de adiciones de Solicitante para subsanacion
     *
     * -------------------------------------------------------------------------
     * Creado: Placido Castro Fecha: 12/112/2017
     *
     * @param apoderadoSipi
     * @return 
     * @throws java.lang.Exception
     */
    public String adicionarRenApoderadoSubsanacion(Apoderados apoderadoSipi) throws Exception;
    
    /**
     * Método que genera el historial de SigSolicitante en caso que corresponda
     *
     * @author Chano Rojas
     * @param idRenSolicitudRenovacion
     * @param direccionNotificacionSipi
     * @param estado_marca
     * @param ubicacion
     * @param fechaSistema
     * @param idLogTrans
     * @param idUsuario
     * @throws java.lang.Exception
     * @version 1.0, 02/12/2016
     */
    void generarHistorialRenDireccionDeNotificacionSubsanacion(Long idRenSolicitudRenovacion, DireccionNotificaciones direccionNotificacionSipi,
            String estado_marca, String ubicacion, Date fechaSistema,Long idLogTrans, Long idUsuario) throws Exception;
    
    /**
     * Método que genera el historial de SigSolicitante en caso que corresponda
     *
     * @author Chano Rojas
     * @param idRenSolcitudRenovacion
     * @param lstSolicitantes
     * @param tipoPersona
     * @param estado_marca
     * @param ubicacion
     * @param fechaSistema
     * @param idLogTrans
     * @param idUsuario
     * @throws java.lang.Exception
     * @version 1.0, 28/11/2016
     */
    String generarHistorialRenSolicitanteApoderado(Long idRenSolcitudRenovacion, List<RenSolicitanteApoderado> lstSolicitantes,
            String tipoPersona, String estado_marca, String ubicacion, Date fechaSistema, Long idLogTrans, Long idUsuario) throws Exception;

    /**
     * Método que genera el historial de SigSolicitante en caso que corresponda
     *
     * @author Chano Rojas
     * @param idRenSolicitudRenovacion
     * @param renDireccionDeNotificacion
     * @param estado_marca
     * @param ubicacion
     * @param fechaSistema
     * @param idLogTrans
     * @param idUsuario
     * @return 
     * @throws java.lang.Exception
     * @version 1.0, 02/12/2016
     */
    String generarHistorialRenDireccionDeNotificacion(Long idRenSolicitudRenovacion, RenDireccionDeNotificacion renDireccionDeNotificacion,
            String estado_marca, String ubicacion, Date fechaSistema,
            Long idLogTrans, Long idUsuario) throws Exception;
    
    /**
     * Método que genera el historial de SigSignoMarca por secciones
     *
     * @author Chano Rojas
     * @param renSolicitudRenovacionVista
     * @param ubicacion
     * @param fechaSistema
     * @param idLogTrans
     * @param idUsuario
     * @version 1.0, 02/12/2016
     * @return
     * @throws java.lang.Exception
     */

    String generarHistorialRenAdministracion(RenSolicitudRenovacion renSolicitudRenovacionVista,
            String ubicacion, Date fechaSistema, Long idLogTrans, Long idUsuario) throws Exception;
    /**
     * Método que genera el historial de SigSignoMarca por secciones
     *
     * @author Chano Rojas
     * @param renSolicitudRenovacion
     * @param listaTitularRegistrado
     * @param estado_renovacion
     * @param ubicacion_renovacion
     * @param fechaSistema
     * @param idLogTrans
     * @param tipoPersonRegistrado
     * @param idUsuario
     * @version 1.0, 02/12/2016
     * @throws java.lang.Exception
     */
void generarHistorialTitularSignoRegistrado(RenSolicitudRenovacion renSolicitudRenovacion, List<RenTitularRegistrado> listaTitularRegistrado,
            String estado_renovacion, String ubicacion_renovacion, Date fechaSistema, Long idLogTrans, String tipoPersonRegistrado, Long idUsuario) throws Exception;
    
    /**
     * Método que genera el historial de SigSignoMarca por secciones
     *
     * @author Chano Rojas
     * @param idSolicitudRenovacion
     * @param estado_renovacion
     * @param ubicacion_renovacion
     * @param fechaSistema
     * @param idLogTrans
     * @param tipoPersonRegistrado
     * @param idUsuario
     * @param listaTitularRegistradoSipi
     * @version 1.0, 02/12/2016
     * @throws java.lang.Exception
     */
void generarHistorialRenTitularRegistradoSubsanacion(Long idSolicitudRenovacion, String estado_renovacion, String ubicacion_renovacion, Date fechaSistema, Long idLogTrans, 
        String tipoPersonRegistrado, Long idUsuario, List<RenTitularRegistrados> listaTitularRegistradoSipi) throws Exception;

    /**
     * Método que genera el historial de SigSignoMarca por secciones
     *
     * @author Chano Rojas
     * @param historial
     * @param renSolicitudRenovacion
     * @param listarenApoderado
     * @param listarenSolicitante
     * @param listaTitularRegistrado
     * @param renDireccionDeNotificacion
     * @param lstRenTipoSigno
     * @param idUsuario
     * @version 1.0, 02/12/2016
     * @return
     * @throws java.lang.Exception
     */    
    Historial guardaHistorialRenovacion(Historial historial, RenSolicitudRenovacion renSolicitudRenovacion, List<RenSolicitanteApoderado> listarenApoderado, List<RenSolicitanteApoderado> listarenSolicitante, List<RenTitularRegistrado> listaTitularRegistrado, RenDireccionDeNotificacion renDireccionDeNotificacion, List<RenTipoSigno> lstRenTipoSigno, Long idUsuario) throws Exception;
    
    /**
     * Método que genera el renhistorial de acuerdo al id renSolicitudRenovacion.
     *
     * @author chano rojas
     * @param idsolicitudrenovacion
     * @param operacion
     * @version 1.0, 19/04/2017
     * @return
     * @throws java.lang.Exception
     */
    public List<Historial> obtienenListaHistorialPorRenSolicitudRenovacion(Long idsolicitudrenovacion, String operacion) throws Exception;
    /**
     * Método que genera el renhistorial de acuerdo al id renSolicitudRenovacion.
     *
     * @author chano rojas
     * @param renSolicitudRenovacion
     * @param listaRenTipoSigno
     * @param idsolicitudRenovacion
     * @param estado_renovacion
     * @param ubicacion_renovacion
     * @param fechaSistema
     * @param idLogTrans
     * @param idUsuario
     * @version 1.0, 19/04/2017
     */
    void guardar_RenHistorialRenTipoSigno(RenSolicitudRenovacion renSolicitudRenovacion,List<RenTipoSigno> listaRenTipoSigno, Long idsolicitudRenovacion, String estado_renovacion, String ubicacion_renovacion, Date fechaSistema, Long idLogTrans, Long idUsuario);
    
    /**
     * Método que genera el renhistorial de acuerdo al id renSolicitudRenovacion.
     *
     * @author chano rojas
     * @param renSolicitudRenovacion
     * @param listaRenTipoSigno
     * @param estado_renovacion
     * @param ubicacion_renovacion
     * @param fechaSistema
     * @param idLogTrans
     * @param idUsuario
     * @param listaTipoSignosExt
     * @version 1.0, 19/04/2017
     */
    void generarHistorialRenTipoSignoSubsanacion(RenSolicitudRenovacion renSolicitudRenovacion,List<RenTipoSigno> listaRenTipoSigno, String estado_renovacion, 
            String ubicacion_renovacion, Date fechaSistema, Long idLogTrans, Long idUsuario, List<RenTipoSignos> listaTipoSignosExt);
    
    /**
     * Método que genera el renhistorial de acuerdo al id renSolicitudRenovacion.
     *
     * @author chano rojas
     * @param listaRenTipoSigno
     * @param idsolicitudRenovacion
     * @param estado_renovacion
     * @param ubicacion_renovacion
     * @param fechaSistema
     * @param idLogTrans
     * @param idUsuario
     * @version 1.0, 19/04/2017
     */
    
    /**
     * Método que genera el renhistorial de acuerdo al id renSolicitudRenovacion.
     * @author chano rojas
     * @param renSolicitudRenovacion
     * @param estado_renovacion
     * @param ubicacion_renovacion
     * @param fechaSistema
     * @param idLogTrans
     * @param idUsuario
     * @version 1.0, 19/04/2017
     * @throws java.lang.Exception
     */
    void generarHistoriallistaProductosDatosdeMarca(RenSolicitudRenovacion renSolicitudRenovacion, String estado_renovacion, String ubicacion_renovacion, Date fechaSistema, Long idLogTrans, Long idUsuario) throws Exception ;
    
       
    /**
     * Método que genera el renhistorial de acuerdo al id renSolicitudRenovacion.
     * @author chano rojas
     * @param renSolicitudRenovacion
     * @param renDireccionDeNotificacion
     * @param lstSolicitantes
     * @param listaRenTipoSigno
     * @param lstApoderados
     * @param listaTitularRegistrado
     * @param usuario
     * @version 1.0, 19/04/2017
     * @return 
     */
    Long guardar_RenHistorial(RenSolicitudRenovacion renSolicitudRenovacion, RenDireccionDeNotificacion renDireccionDeNotificacion, List<RenSolicitanteApoderado> lstSolicitantes,
            List<RenSolicitanteApoderado> lstApoderados, List<RenTitularRegistrado> listaTitularRegistrado, List<RenTipoSigno> listaRenTipoSigno, Usuario usuario);
 
    /**
     * Método que genera el renhistorial de renovacion subsanacion.
     * @author Placido Castro
     * @param renSolicitudRenovacion
     * @param listaTitularRegistrado
     * @param formularioPI104
     * @param listaRenTipoSigno
     * @param usuario
     * @version 1.0, 19/11/2017
     */
    void guardarRenHistorialSubsanacion(RenSolicitudRenovacion renSolicitudRenovacion,
             FormularioPI104 formularioPI104, List<RenTipoSigno> listaRenTipoSigno, Usuario usuario);
        
}
