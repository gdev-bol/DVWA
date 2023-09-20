/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.servicio;

import java.util.Date;
import java.util.List;
import javax.sql.DataSource;
import snp.gob.bo.gimodel.entidad.ModDireccionDeNotificacion;
import snp.gob.bo.gimodel.entidad.ModModificacion;
import snp.gob.bo.gimodel.entidad.ModSolicitanteApoderado;
import snp.gob.bo.gimodel.entidad.ModTipoSigno;
import snp.gob.bo.gimodel.entidad.ModTitularLicenciatarioNuevo;
import snp.gob.bo.gimodel.entidad.ModTitularLicenciatarioRegistrado;
import snp.gob.bo.gimodel.solicitudes.entidades.Apoderados;
import snp.gob.bo.gimodel.solicitudes.entidades.DireccionNotificaciones;
import snp.gob.bo.gimodel.solicitudes.entidades.FormularioPI103;
import snp.gob.bo.gimodel.solicitudes.entidades.ModTipoSignos;
import snp.gob.bo.gimodel.solicitudes.entidades.Solicitantes;
import snp.gob.bo.gimodel.solicitudes.entidades.TitularLicenciatarios;
import snp.gob.bo.gimodel.solicitudes.entidades.TitularLicenciatariosNuevos;

/**
 *
 * @author susana
 */
public interface ModHistorialService {

    /**
     * Agregar el dataSource.
     *
     * @author Eddy Valero
     * @param dataSource
     * @throws java.lang.Exception
     * @version 1.0, 28/11/2016
     */
    public void setDataSource(DataSource dataSource) throws Exception;

    /**
     * Metodo para guardar, historial de cambios de todas las secciones del
     * formulario de modificaciones
     *
     * Creado: Susana Escobar Fecha: 19/12/2016
     *
     * @param modificacion
     * @param modDireccionDeNotificacion
     * @param idUsuario
     * @param lstApoderados
     * @param listaTitularRegistrado
     * @param listaNuevoTitular
     * @param listaParticipanteFusion
     * @param listaModTipoSigno
     * @param lstSolicitantes
     * @return
     */
    public Long guardar_ModHistorial(ModModificacion modificacion, ModDireccionDeNotificacion modDireccionDeNotificacion,
            List<ModSolicitanteApoderado> lstSolicitantes, List<ModSolicitanteApoderado> lstApoderados,
            List<ModTitularLicenciatarioRegistrado> listaTitularRegistrado,
            List<ModTitularLicenciatarioNuevo> listaNuevoTitular, List<ModTitularLicenciatarioRegistrado> listaParticipanteFusion,
            List<ModTipoSigno> listaModTipoSigno, Long idUsuario);

    /**
     * Método para generar historial de cambios de la seccion datos del
     * solicitante y datos del apoderado, sino existe lo crea y guarda historial
     * de adicion
     *
     * Creado:Placido Castro Fecha: 04/12/2017
     *
     * @param idModificacion
     * @param listaSolicitantesSipi
     * @param tipoPersona
     * @param estado_modificacion
     * @param ubicacion_modificacion
     * @param fechaSistema
     * @param idLogTrans
     * @param idUsuario
     * @throws Exception
     */
    public void generarHistorialModSolicitanteSubsanacion(Long idModificacion, List<Solicitantes> listaModSlistaSolicitantesSipiolicitantesExt,
            String tipoPersona, String estado_modificacion, String ubicacion_modificacion, Date fechaSistema, Long idLogTrans, Long idUsuario) throws Exception; 
    
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
    public String modificarModSolicitanteSubsanacion(ModSolicitanteApoderado solicitanteHidra, Solicitantes solicitanteSipi) throws Exception;
    
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
    public String eliminarModSolicitanteSubsanacion(ModSolicitanteApoderado solicitanteHidra) throws Exception;
    
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
    public String adicionarModSolicitanteSubsanacion(Solicitantes solicitanteSipi) throws Exception;
        
    /**
     * Método para generar historial de cambios de la seccion datos del
     * solicitante y datos del apoderado, sino existe lo crea y guarda historial
     * de adicion
     *
     * Creado: Susana Escobar Paz Fecha: 21/12/2016
     *
     * @param idModificacion
     * @param listaModApoderadosExt
     * @param tipoPersona
     * @param estado_modificacion
     * @param ubicacion_modificacion
     * @param fechaSistema
     * @param idLogTrans
     * @param idUsuario
     * @throws Exception
     */
    public void generarHistorialModApoderadoSubsanacion(Long idModificacion, List<Apoderados> listaModApoderadosExt,
            String tipoPersona, String estado_modificacion, String ubicacion_modificacion, Date fechaSistema, Long idLogTrans, Long idUsuario) throws Exception;
    
    /**
     * Método que genera el historial de modificaciones de Apoderado para subsanacion
     *
     * -------------------------------------------------------------------------
     * Creado: Placido Castro Fecha: 12/112/2017
     *
     * @param apoderadoHidra
     * @param apoderadoSipi
     * @return 
     * @throws java.lang.Exception
     */
    public String modificarModApoderadoSubsanacion(ModSolicitanteApoderado apoderadoHidra, Apoderados apoderadoSipi) throws Exception;
    
    /**
     * Método que genera el historial de eliminaciones de Apoderado para subsanacion
     *
     * -------------------------------------------------------------------------
     * Creado: Placido Castro Fecha: 12/112/2017
     *
     * @param apoderadoHidra
     * @return 
     * @throws java.lang.Exception
     */
    public String eliminarModApoderadoSubsanacion(ModSolicitanteApoderado apoderadoHidra) throws Exception;
    
    /**
     * Método que genera el historial de adiciones de Apoderado para subsanacion
     *
     * -------------------------------------------------------------------------
     * Creado: Placido Castro Fecha: 12/112/2017
     *
     * @param apoderadoSipi
     * @return 
     * @throws java.lang.Exception
     */
    public String adicionarModApoderadoSubsanacion(Apoderados apoderadoSipi) throws Exception;
            
    /**
     * Método para generar historial de cambios de la seccion direccion de
     * notificacion, sino existe la crea y guarda historial de adicion
     *
     * Creado: Susana Escobar Paz Fecha: 20/12/2016
     *
     * @param idModificacion
     * @param direccionNotificacionSipi
     * @param estado_modificacion
     * @param ubicacion_modificacion
     * @param fechaSistema
     * @param idLogTrans
     * @param idUsuario
     * @throws java.lang.Exception
     * @version 1.0, 02/12/2016
     */
    public void generarHistorialModDireccionDeNotificacionSubsanacion(Long idModificacion, DireccionNotificaciones direccionNotificacionSipi,
            String estado_modificacion, String ubicacion_modificacion, Date fechaSistema, Long idLogTrans, Long idUsuario) throws Exception;
    
    /**
     * Método para generar historial de cambios de la seccion direccion de
     * notificacion, sino existe la crea y guarda historial de adicion
     *
     * Creado: Susana Escobar Paz Fecha: 20/12/2016
     *
     * @param idModificacion
     * @param modDireccionDeNotificacion
     * @param estado_modificacion
     * @param ubicacion_modificacion
     * @param fechaSistema
     * @param idLogTrans
     * @param idUsuario
     * @throws java.lang.Exception
     * @version 1.0, 02/12/2016
     */
    public void generarHistorialModDireccionDeNotificacion(Long idModificacion, ModDireccionDeNotificacion modDireccionDeNotificacion,
            String estado_modificacion, String ubicacion_modificacion, Date fechaSistema, Long idLogTrans, Long idUsuario) throws Exception;

    /**
     * Método para generar historial de cambios de la seccion datos del
     * solicitante y datos del apoderado, sino existe lo crea y guarda historial
     * de adicion
     *
     * Creado: Susana Escobar Paz Fecha: 21/12/2016
     *
     * @param idModificacion
     * @param lstSolicitantes
     * @param tipoPersona
     * @param estado_modificacion
     * @param ubicacion_modificacion
     * @param fechaSistema
     * @param idLogTrans
     * @param idUsuario
     * @throws Exception
     */
    public void generarHistorialModSolicitanteApoderado(Long idModificacion, List<ModSolicitanteApoderado> lstSolicitantes,
            String tipoPersona, String estado_modificacion, String ubicacion_modificacion, Date fechaSistema, Long idLogTrans, Long idUsuario) throws Exception;

    /**
     * Método para generar historial de cambios de la seccion referencia de la
     * solicitud de modificacion
     *
     * Creado: Susana Escobar Paz Fecha: 21/12/2016
     *
     * @param modificacion
     * @param estado_modificacion
     * @param ubicacion_modificacion
     * @param fechaSistema
     * @param idLogTrans
     * @param idUsuario
     * @throws Exception
     */
    public void generarHistorialReferenciaModificacion(ModModificacion modificacion, String estado_modificacion, String ubicacion_modificacion,
            Date fechaSistema, Long idLogTrans, Long idUsuario) throws Exception;

    /**
     * Método para generar historial de cambios de la seccion de datos del signo
     * registrado; titular registrado sino existe lo crea y guarda historial de
     * creacion
     *
     * Creado: Susana Escobar Paz Fecha: 22/12/2016
     *
     * @param modificacion
     * @param listaTitularRegistrado
     * @param estado_modificacion
     * @param ubicacion_modificacion
     * @param fechaSistema
     * @param idLogTrans
     * @param tipoPersonRegistrado
     * @param idUsuario
     * @throws Exception
     */
    public void generarHistorialTitularSignoRegistrado(ModModificacion modificacion, List<ModTitularLicenciatarioRegistrado> listaTitularRegistrado,
            String estado_modificacion, String ubicacion_modificacion, Date fechaSistema, Long idLogTrans, String tipoPersonRegistrado,
            Long idUsuario) throws Exception;

    /**
     * Método para generar historial de cambios de la seccion de datos del signo
     * registrado; titular registrado sino existe lo crea y guarda historial de
     * creacion
     *
     * Creado: Placido Castro Fecha: 22/12/2017
     *
     * @param idModificacion
     * @param listaTitularLicenciatariosSipi
     * @param estado_modificacion
     * @param ubicacion_modificacion
     * @param fechaSistema
     * @param idLogTrans
     * @param tipoPersonRegistrado
     * @param idUsuario
     * @throws Exception
     */
    public void generarHistorialTitularLicenciatarioRegistradoSubsanacion(Long idModificacion, List<TitularLicenciatarios> listaTitularLicenciatariosSipi,
            String estado_modificacion, String ubicacion_modificacion, Date fechaSistema, Long idLogTrans, String tipoPersonRegistrado, Long idUsuario) throws Exception;
    
    /**
     * Método para generar historial de cambios de la seccion de datos del signo
     * registrado
     *
     * Creado: Susana Escobar Paz Fecha: 22/12/2016
     *
     * @param modificacion
     * @param estado_modificacion
     * @param ubicacion_modificacion
     * @param fechaSistema
     * @param idLogTrans
     * @param idUsuario
     * @throws Exception
     */
    public void generarHistorialDatosSignoRegistrado(ModModificacion modificacion, String estado_modificacion, String ubicacion_modificacion,
            Date fechaSistema, Long idLogTrans, Long idUsuario) throws Exception;

    /**
     * Método para generar historial de cambios de la seccion de datos del nuevo
     * titular, sino existe lo crea y guarda historial de creacion
     *
     * Creado: Susana Escobar Paz Fecha: 23/12/2016
     *
     * @param idModificacion
     * @param listaNuevoTitular
     * @param estado_modificacion
     * @param ubicacion_modificacion
     * @param fechaSistema
     * @param idLogTrans
     * @param idUsuario
     * @throws Exception
     */
    public void generarHistorialModTitularLicenciatarioNuevo(Long idModificacion, List<ModTitularLicenciatarioNuevo> listaNuevoTitular,
            String estado_modificacion, String ubicacion_modificacion, Date fechaSistema, Long idLogTrans, Long idUsuario) throws Exception;

    /**
     * Método para generar historial de cambios de la seccion de datos del nuevo
     * titular, sino existe lo crea y guarda historial de creacion
     *
     * Creado: Placido Castro Fecha: 22/12/2017
     *
     * @param idModificacion
     * @param listaTitularLicenciatariosNuevosSipi
     * @param estado_modificacion
     * @param ubicacion_modificacion
     * @param fechaSistema
     * @param idLogTrans
     * @param tipoPersonRegistrado
     * @param idUsuario
     * @throws Exception
     */
    public void generarHistorialTitularLicenciatarioNuevoSubsanacion(Long idModificacion, List<TitularLicenciatariosNuevos> listaTitularLicenciatariosNuevosSipi,
            String estado_modificacion, String ubicacion_modificacion, Date fechaSistema, Long idLogTrans,  String tipoPersonRegistrado, Long idUsuario) throws Exception;
    
        /**
     * Método que genera el historial de modificaciones de Solicitante para subsanacion
     *
     * -------------------------------------------------------------------------
     * Creado: Placido Castro Fecha: 12/112/2017
     *
     * @param titularLicenciatarioNuevoHidra
     * @param titularLicenciatarioNuevoSipi
     * @return 
     * @throws java.lang.Exception
     */
    public String modificarModTitularLicenciatarioNuevoSubsanacion(ModTitularLicenciatarioNuevo titularLicenciatarioNuevoHidra, 
            TitularLicenciatariosNuevos titularLicenciatarioNuevoSipi) throws Exception;
    
    /**
     * Método que genera el historial de eliminaciones de Solicitante para subsanacion
     *
     * -------------------------------------------------------------------------
     * Creado: Placido Castro Fecha: 12/112/2017
     *
     * @param titularLicenciatarioNuevoHidra
     * @return 
     * @throws java.lang.Exception
     */
    public String eliminarModTitularLicenciatarioNuevoSubsanacion(ModTitularLicenciatarioNuevo titularLicenciatarioNuevoHidra) throws Exception;
    
    /**
     * Método que genera el historial de adiciones de Solicitante para subsanacion
     *
     * -------------------------------------------------------------------------
     * Creado: Placido Castro Fecha: 12/112/2017
     *
     * @param titularLicenciatarioNuevoSipi
     * @return 
     * @throws java.lang.Exception
     */
    public String adicionarModTitularLicenciatarioNuevoSubsanacion(TitularLicenciatariosNuevos titularLicenciatarioNuevoSipi) throws Exception;
    
    public Long guardar_ModHistorialCreacionSolicitud(Long idmodificacion, String estado_modificacion, String ubicacion_modificacion, Long idUsuario);

    /**
     * Método para generar historial de cambios de la creacion de una solicitud de modificacion
     *
     * Creado: Susana Escobar Paz Fecha: 26/12/2016
     *
     * @param modificacion
     * @param estado_modificacion
     * @param ubicacion_modificacion
     * @param fechaSistema
     * @param idLogTrans
     * @param idUsuario
     * @throws Exception
     */
    public void generarHistorialDatosAdminitrativos(ModModificacion modificacion, String estado_modificacion, String ubicacion_modificacion,
            Date fechaSistema, Long idLogTrans, Long idUsuario) throws Exception;

    /**
     * Método para generar historial de cambios para la seccion datos del signo registrado; datos del tipo signo
     *
     * Creado: Susana Escobar Paz Fecha: 29/12/2016
     *
     * @param listaModTipoSigno
     * @param idmodificacion
     * @param estado_modificacion
     * @param ubicacion_modificacion
     * @param fechaSistema
     * @param idLogTrans
     * @param idUsuario
     */
    public void guardar_ModHistorialModTipoSigno(List<ModTipoSigno> listaModTipoSigno, Long idmodificacion, String estado_modificacion, String ubicacion_modificacion, Date fechaSistema, Long idLogTrans, Long idUsuario);
    
    /**
     * Método para generar historial de cambios para la seccion datos del signo registrado; datos del tipo signo
     *
     * Creado: Placido Castro Paz Fecha: 04/12/2017
     *
     * @param modificacion
     * @param fechaSistema
     * @param estado_modificacion
     * @param ubicacion_modificacion
     * @param idLogTrans
     * @param idUsuario
     * @param formularioPI103
     */
    public void generarHistorialModModificacionSubsanacion(ModModificacion modificacion, Date fechaSistema, String estado_modificacion, String ubicacion_modificacion, 
            Long idLogTrans, Long idUsuario, FormularioPI103 formularioPI103);
    
    /**
     * Método para generar historial de cambios para la seccion datos del signo registrado; datos del tipo signo
     *
     * Creado: Placido Castro Paz Fecha: 04/12/2017
     *
     * @param listaModTipoSigno
     * @param idmodificacion
     * @param estado_modificacion
     * @param ubicacion_modificacion
     * @param fechaSistema
     * @param idLogTrans
     * @param idUsuario
     * @param listaTipoSignosSipi
     */
    public void generarHistorialModTipoSignoSubsanacion(List<ModTipoSigno> listaModTipoSigno, Long idmodificacion, String estado_modificacion, 
            String ubicacion_modificacion, Date fechaSistema, Long idLogTrans, Long idUsuario, List<ModTipoSignos> listaTipoSignosSipi);
    
    /**
     * Método que genera el renhistorial de renovacion subsanacion.
     * @author Placido Castro
     * @param modificacion
     * @param listaModTipoSigno
     * @param idUsuario
     * @param formularioPI103
     * @version 1.0, 19/11/2017
     * @throws Exception 
     */
    public void guardarModHistorialSubsanacion(ModModificacion modificacion, List<ModTipoSigno> listaModTipoSigno, Long idUsuario, FormularioPI103 formularioPI103) throws Exception;    
}
