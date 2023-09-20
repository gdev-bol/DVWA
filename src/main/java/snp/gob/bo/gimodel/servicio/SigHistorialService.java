/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.servicio;

import java.util.Date;
import java.util.List;
import javax.sql.DataSource;
import snp.gob.bo.gimodel.bdimagen.entidad.ImagenPojo;
import snp.gob.bo.gimodel.entidad.Historial;
import snp.gob.bo.gimodel.entidad.ModModificacion;
import snp.gob.bo.gimodel.entidad.RenSolicitudRenovacion;
import snp.gob.bo.gimodel.entidad.SigDireccionDeNotificacion;
import snp.gob.bo.gimodel.entidad.SigPrioridadPreferencia;
import snp.gob.bo.gimodel.entidad.SigSignoClaseNiza;
import snp.gob.bo.gimodel.entidad.SigSignoMarca;
import snp.gob.bo.gimodel.entidad.SigSolicitanteApoderado;
import snp.gob.bo.gimodel.entidad.SigTipoSigno;
import snp.gob.bo.gimodel.solicitudes.entidades.Apoderados;
import snp.gob.bo.gimodel.solicitudes.entidades.DireccionNotificaciones;
import snp.gob.bo.gimodel.solicitudes.entidades.FormularioPI100;
import snp.gob.bo.gimodel.solicitudes.entidades.FormularioPI101;
import snp.gob.bo.gimodel.solicitudes.entidades.FormularioPI102;
import snp.gob.bo.gimodel.solicitudes.entidades.Prioridades;
import snp.gob.bo.gimodel.solicitudes.entidades.SmSignoClaseNizas;
import snp.gob.bo.gimodel.solicitudes.entidades.SmTipoSignos;
import snp.gob.bo.gimodel.solicitudes.entidades.Solicitantes;
/**
 *
 * @author Eddy Valero
 * @see Historial
 * @see SigHistorialServiceImpl
 * @see HistorialService
 * @see HistorialServiceImpl
 * @version 1.0, 28/11/2016
 */
public interface SigHistorialService {

    /**
     * Agregar el dataSource.
     *
     * -------------------------------------------------------------------------
     * Creado: Eddy Valero Fecha: 28/11/2016 
     * 
     * @param dataSource
     * @throws java.lang.Exception
     */
    public void setDataSource(DataSource dataSource) throws Exception;

      
    /**
     * Método que genera el historial de SigSolicitante en caso que corresponda
     *
     * -------------------------------------------------------------------------
     * Creado: Eddy Valero Fecha: 28/11/2016
     *
     * @param idSignoMarca
     * @param lstSolicitantes
     * @param tipoPersona
     * @param estado_marca
     * @param ubicacion
     * @param fechaSistema
     * @param idLogTrans
     * @param idUsuario
     * @throws java.lang.Exception
     */
    
    public void generarHistorialSigSolicitanteApoderado(Long idSignoMarca, List<SigSolicitanteApoderado> lstSolicitantes,
            String tipoPersona, String estado_marca, String ubicacion, Date fechaSistema, Long idLogTrans, Long idUsuario) throws Exception;

    /**
     * Método que genera el historial de Solicitante en caso que corresponda
     *
     * -------------------------------------------------------------------------
     * Creado: Placido Castro Fecha: 12/10/2017
     *
     * @param idSignoMarca
     * @param tipoPersona
     * @param estado_marca
     * @param ubicacion
     * @param fechaSistema
     * @param idLogTrans
     * @param idUsuario
     * @param listaSolicitantesSipi
     * @throws java.lang.Exception
     */
    public void generarHistorialSigSolicitanteSubsanacion(Long idSignoMarca, String tipoPersona, String estado_marca, String ubicacion, 
            Date fechaSistema, Long idLogTrans, Long idUsuario, List<Solicitantes> listaSolicitantesSipi) throws Exception;
    
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
    public String modificarSigSolicitanteSubsanacion(SigSolicitanteApoderado solicitanteHidra, Solicitantes solicitanteSipi) throws Exception;
    
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
    public String eliminarSigSolicitanteSubsanacion(SigSolicitanteApoderado solicitanteHidra) throws Exception;
    
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
    public String adicionarSigSolicitanteSubsanacion(Solicitantes solicitanteSipi) throws Exception;
    
    
    /**
     * Método que genera el historial de Apoderado en caso que corresponda
     *
     * -------------------------------------------------------------------------
     * Creado: Placido Castro Fecha: 12/10/2017
     *
     * @param idSignoMarca
     * @param tipoPersona
     * @param estado_marca
     * @param ubicacion
     * @param fechaSistema
     * @param idLogTrans
     * @param idUsuario
     * @param listaApoderadosSipi
     * @throws java.lang.Exception
     */
    public void generarHistorialSigApoderadoSubsanacion(Long idSignoMarca, String tipoPersona, String estado_marca, String ubicacion, 
            Date fechaSistema, Long idLogTrans, Long idUsuario, List<Apoderados> listaApoderadosSipi) throws Exception;

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
    public String modificarSigApoderadoSubsanacion(SigSolicitanteApoderado apoderadoHidra, Apoderados apoderadoSipi) throws Exception;
    
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
    public String eliminarSigApoderadoSubsanacion(SigSolicitanteApoderado apoderadoHidra) throws Exception;
    
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
    public String adicionarSigApoderadoSubsanacion(Apoderados apoderadoSipi) throws Exception;
        
    /**
     * Método que genera el historial de SigDirecciones en caso que corresponda
     *
     * -------------------------------------------------------------------------
     * Creado: Eddy Valero Fecha: 02/12/2016
     *
     * @param idSignoMarca
     * @param sigDireccionDeNotificacion
     * @param estado_marca
     * @param ubicacion
     * @param fechaSistema
     * @param idLogTrans
     * @param idUsuario
     * @throws java.lang.Exception
     */
    public void generarHistorialSigDireccionDeNotificacion(Long idSignoMarca, SigDireccionDeNotificacion sigDireccionDeNotificacion,
            String estado_marca, String ubicacion, Date fechaSistema, Long idLogTrans, Long idUsuario) throws Exception;

    /**
 * Método que genera el historial de SigDirecciones en caso que corresponda
 *
 * -------------------------------------------------------------------------
 * Creado: Placido Castro Fecha: 12/10/2017
 *
 * @param idSignoMarca
 * @param estado_marca
 * @param ubicacion
 * @param fechaSistema
 * @param idLogTrans
 * @param idUsuario
 * @param direccionNotificacionSipi
 * @throws java.lang.Exception
 */
public void generarHistorialSigDireccionDeNotificacionSubsanacion(Long idSignoMarca, String estado_marca, String ubicacion, Date fechaSistema, 
        Long idLogTrans, Long idUsuario, List<DireccionNotificaciones> direccionNotificacionSipi) throws Exception;

    /**
     * Método que genera el historial de SigSignoMarca por secciones
     *
     * -------------------------------------------------------------------------
     * Creado: Eddy Valero Fecha: 02/12/2016
     *
     *
     * @author Eddy Valero
     * @param idSignoMarca
     * @param sigSignoMarca
     * @param lstTipoSignos
     * @param lstSigSignoClaseNiza
     * @param fechaSistema
     * @param idLogTrans
     * @param idUsuario
     * @throws java.lang.Exception
     */
    public void generarHistorialSigSignoMarca(Long idSignoMarca, SigSignoMarca sigSignoMarca, List<SigTipoSigno> lstTipoSignos,
            List<SigSignoClaseNiza> lstSigSignoClaseNiza,
            Date fechaSistema, Long idLogTrans, Long idUsuario) throws Exception;
    
    /**
     * Método que genera el historial de SigSignoMarca por secciones
     *
     * -------------------------------------------------------------------------
     * Creado: Placido Castro Fecha: 27/11/2017
     *
     *
     * @author Eddy Valero
     * @param sigSignoMarca
     * @param fechaSistema
     * @param estado_marca
     * @param ubicacion_marca
     * @param idLogTrans
     * @param idUsuario
     * @param formularioPI100
     * @throws java.lang.Exception
     */
    public void guardarSigHistorialSubsanacionPI100(SigSignoMarca sigSignoMarca, Date fechaSistema, String estado_marca, String ubicacion_marca,
                    Long idLogTrans, Long idUsuario, FormularioPI100 formularioPI100) throws Exception;
    
    /**
     * Método que genera el historial de SigSignoMarca por secciones
     *
     * -------------------------------------------------------------------------
     * Creado: Placido Castro Fecha: 27/11/2017
     *
     *
     * @author Eddy Valero
     * @param sigSignoMarca
     * @param fechaSistema
     * @param estado_marca
     * @param ubicacion_marca
     * @param idLogTrans
     * @param idUsuario
     * @param formularioPI101
     * @throws java.lang.Exception
     */
    public void guardarSigHistorialSubsanacionPI101(SigSignoMarca sigSignoMarca, Date fechaSistema, String estado_marca, String ubicacion_marca,
                    Long idLogTrans, Long idUsuario, FormularioPI101 formularioPI101) throws Exception;
    
    /**
     * Método que genera el historial de SigSignoMarca por secciones
     *
     * -------------------------------------------------------------------------
     * Creado: Placido Castro Fecha: 27/11/2017
     *
     *
     * @author Eddy Valero
     * @param sigSignoMarca
     * @param fechaSistema
     * @param estado_marca
     * @param ubicacion_marca
     * @param idLogTrans
     * @param idUsuario
     * @param formularioPI102
     * @throws java.lang.Exception
     */
    public void guardarSigHistorialSubsanacionPI102(SigSignoMarca sigSignoMarca, Date fechaSistema, String estado_marca, String ubicacion_marca,
                    Long idLogTrans, Long idUsuario, FormularioPI102 formularioPI102) throws Exception;
    
    /**
     * Método que genera el historial de SigSignoMarca por secciones
     *
     * -------------------------------------------------------------------------
     * Creado: Placido Castro Fecha: 27/11/2017
     *
     *
     * @author Eddy Valero
     * @param sigSignoMarca
     * @param fechaSistema
     * @param idLogTrans
     * @param idUsuario
     * @param formularioPI100
     * @throws java.lang.Exception
     */
    public void generarHistorialSigSignoMarcaSubsanacionPI100(SigSignoMarca sigSignoMarca,
            Date fechaSistema, Long idLogTrans, Long idUsuario, FormularioPI100 formularioPI100) throws Exception;
    
    /**
     * Método que genera el historial de SigSignoMarca por secciones
     *
     * -------------------------------------------------------------------------
     * Creado: Placido Castro Fecha: 27/11/2017
     *
     *
     * @author Eddy Valero
     * @param sigSignoMarca
     * @param fechaSistema
     * @param idLogTrans
     * @param idUsuario
     * @param formularioPI101
     * @throws java.lang.Exception
     */
    public void generarHistorialSigSignoMarcaSubsanacionPI101(SigSignoMarca sigSignoMarca,
            Date fechaSistema, Long idLogTrans, Long idUsuario, FormularioPI101 formularioPI101) throws Exception;
    
    /**
     * Método que genera el historial de SigSignoMarca por secciones
     *
     * -------------------------------------------------------------------------
     * Creado: Placido Castro Fecha: 27/11/2017
     *
     *
     * @author Eddy Valero
     * @param sigSignoMarca
     * @param fechaSistema
     * @param idLogTrans
     * @param idUsuario
     * @param formularioPI102
     * @throws java.lang.Exception
     */
    public void generarHistorialSigSignoMarcaSubsanacionPI102(SigSignoMarca sigSignoMarca,
            Date fechaSistema, Long idLogTrans, Long idUsuario, FormularioPI102 formularioPI102) throws Exception;
    
   /**
     * Método que genera el historial de Tipo Signo
     *
     * -------------------------------------------------------------------------
     * Creado: Placido Castro Fecha: 06/11/2017
     *
     *
     * @author Placido Castro
     * @param sigSignoMarca
     * @param fechaSistema
     * @param idLogTrans
     * @param idUsuario
     * @param listaTipoSignosSipi
     * @throws java.lang.Exception
     */
    public void generarHistorialSigTipoSignoSubsanacion(SigSignoMarca sigSignoMarca, Date fechaSistema, 
            Long idLogTrans, Long idUsuario, List<SmTipoSignos> listaTipoSignosSipi) throws Exception;

   /**
     * Método que genera el historial de Tipo Signo
     *
     * -------------------------------------------------------------------------
     * Creado: Placido Castro Fecha: 06/11/2017
     *
     *
     * @author Placido Castro
     * @param sigSignoMarca
     * @param fechaSistema
     * @param idLogTrans
     * @param idUsuario
     * @param listaSignoClaseNizaSipi
     * @throws java.lang.Exception
     */
    public void generarHistorialSigClaseNizaSubsanacion(SigSignoMarca sigSignoMarca, Date fechaSistema, 
            Long idLogTrans, Long idUsuario,  List<SmSignoClaseNizas> listaSignoClaseNizaSipi) throws Exception;
    
    /**
     * Método que genera el historial de SigPrioridadPreferencia de una
     * oposicion andina
     *
     * -------------------------------------------------------------------------
     * Creado: Eddy Valero Fecha: 11/03/2017
     *
     * @author Eddy Valero
     * @param idSignoMarca
     * @param oposicion
     * @param estado_marca
     * @param ubicacion
     * @param fechaSistema
     * @param idLogTrans
     * @param idUsuario
     * @throws java.lang.Exception
     */
    public void generarHistorialOposicionAndina(Long idSignoMarca, SigPrioridadPreferencia oposicion,
            String estado_marca, String ubicacion, Date fechaSistema, Long idLogTrans, Long idUsuario) throws Exception;

    /**
     * Método que genera el historial de Prioridades
     * 
     * -------------------------------------------------------------------------
     * Creado: Placido Castro Fecha: 22/11/2017
     *
     *
     * @param idSignoMarca
     * @param listaPrioridadPreferenciaSipi
     * @param estado_marca
     * @param ubicacion
     * @param fechaSistema
     * @param idLogTrans
     * @param idUsuario
     * @throws java.lang.Exception
     */
    public void generarHistorialPrioridadesSubsanacion(Long idSignoMarca, List<Prioridades> listaPrioridadPreferenciaSipi,
            String estado_marca, String ubicacion, Date fechaSistema, Long idLogTrans, Long idUsuario) throws Exception;
    
    
    /**
     * Método que obtiene sigprioridadpreferencia
     *
     * Creado: Placido Castro Fecha: 10/11/2017
     *
     * @param idSignoMarca
     * @return SigDireccionNotificacion
     * @throws java.lang.Exception
     */
    public List<SigPrioridadPreferencia> obtenerListaPrioridadPreferencia(Long idSignoMarca) throws Exception;
    
    /**
     * Método que genera el historial de SigPrioridadPreferencia de una
     * prioridadExtranjera
     *
     * -------------------------------------------------------------------------
     * Creado: Eddy Valero Fecha: 11/03/2017
     *
     *
     * @param idSignoMarca
     * @param prioridadExtranjera
     * @param estado_marca
     * @param ubicacion
     * @param fechaSistema
     * @param idLogTrans
     * @param idUsuario
     * @throws java.lang.Exception
     */
    public void generarHistorialPrioridadExtranjera(Long idSignoMarca, SigPrioridadPreferencia prioridadExtranjera,
            String estado_marca, String ubicacion, Date fechaSistema, Long idLogTrans, Long idUsuario) throws Exception;

    /**
     * Método que genera el historial de SigPrioridadPreferencia de una
     * prioridadExposicion
     *
     * -------------------------------------------------------------------------
     * Creado: Eddy Valero Fecha: 13/03/2017
     *
     *
     * @param idSignoMarca
     * @param prioridadExposicion
     * @param estado_marca
     * @param ubicacion
     * @param fechaSistema
     * @param idLogTrans
     * @param idUsuario
     * @throws java.lang.Exception
     */
    public void generarHistorialPrioridadExposicion(Long idSignoMarca, SigPrioridadPreferencia prioridadExposicion,
            String estado_marca, String ubicacion, Date fechaSistema, Long idLogTrans, Long idUsuario) throws Exception;

    /**
     * Método que genera el historial de modificaciones de SigImagen
     *
     *
     * -------------------------------------------------------------------------
     * Creado: Eddy Valero Fecha: 14/03/2017
     *
     *
     * @param idSignoMarca
     * @param imagenPojo
     * @param estado_marca
     * @param ubicacion
     * @param fechaSistema
     * @param idLogTrans
     * @param idUsuario
     * @throws java.lang.Exception
     */
    public void generarHistorialImagen(Long idSignoMarca, ImagenPojo imagenPojo,
            String estado_marca, String ubicacion, Date fechaSistema, Long idLogTrans, Long idUsuario) throws Exception;
    
    
    /**
     * Método que genera el historial de eliminación de SigImagen
     *
     *
     * -------------------------------------------------------------------------
     * Creado: Eddy Valero Fecha: 14/03/2017
     *
     *
     * @param idSignoMarca
     * @param imagenPojo
     * @param estado_marca
     * @param ubicacion
     * @param fechaSistema
     * @param idLogTrans
     * @param idUsuario
     * @throws java.lang.Exception
     */
    public void generarHistorialEliminacionImagen(Long idSignoMarca, ImagenPojo imagenPojo,
            String estado_marca, String ubicacion, Date fechaSistema, Long idLogTrans, Long idUsuario) throws Exception;
    /**
     * Método que inserta el historial para cuando vaya a notificacion y devuelva tambien de notificacion  elusivo para signos
     *
     * -------------------------------------------------------------------------
     * Creado: LEvi Laura  Fecha: 14/03/2017
     *
     *
     * @param signoMarca     
     * @param idusuario     
     * @param estadoMarca     
     * @param descripcion     
     * @throws java.lang.Exception
     */
   public void generarHistorialNotificacion(SigSignoMarca signoMarca, Long idusuario, String estadoMarca, String descripcion) throws Exception;
   /**
     * Método que inserta el historial para cuando vaya a notificacion y devuelva tambien de notificacion  elusivo para Modificaciones
     *
     * -------------------------------------------------------------------------
     * Creado: LEvi Laura  Fecha: 14/03/2017
     *
     *     
     * @param modi
     * @param idusuario     
     * @param estadoMarca     
     * @param descripcion     
     * @throws java.lang.Exception
     */
   public void generarHistorialModifiNotificacion(ModModificacion modi, Long idusuario, String estadoMarca, String descripcion) throws Exception;
   /**
     * Método que inserta el historial para cuando vaya a notificacion y devuelva tambien de notificacion  elusivo para Modificaciones
     *
     * -------------------------------------------------------------------------
     * Creado: LEvi Laura  Fecha: 14/03/2017
     *
     *
     * @param signoMarca     
     * @param idusuario     
     * @param estadoMarca     
     * @param descripcion     
     * @throws java.lang.Exception
     */
   
    /**
     * Método que inserta el historial para cuando vaya a notificacion y devuelva tambien de notificacion  elusivo para Modificaciones

 -------------------------------------------------------------------------
 Creado: LEvi Laura  Fecha: 14/03/2017
     * @param ren
     * @param idusuario
     * @param estadoMarca
     * @param descripcion
     * @throws java.lang.Exception
     */
    public void generarHistorialRenoNotificacion(RenSolicitudRenovacion ren, Long idusuario, String estadoMarca, String descripcion) throws Exception;
}
