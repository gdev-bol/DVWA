/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.servicio;

import java.util.List;
import javax.sql.DataSource;
import snp.gob.bo.gimodel.bdimagen.entidad.ImagenPojo;
import snp.gob.bo.gimodel.entidad.ClaseNiza;
import snp.gob.bo.gimodel.entidad.SigDireccionDeNotificacion;
import snp.gob.bo.gimodel.entidad.SigPrioridadPreferencia;
import snp.gob.bo.gimodel.entidad.SigSignoClaseNiza;
import snp.gob.bo.gimodel.entidad.SigSignoMarca;
import snp.gob.bo.gimodel.entidad.SigSolicitanteApoderado;
import snp.gob.bo.gimodel.entidad.SigTipoSigno;
import snp.gob.bo.gimodel.solicitudes.entidades.FormularioPI100;
import snp.gob.bo.gimodel.solicitudes.entidades.FormularioPI101;
import snp.gob.bo.gimodel.solicitudes.entidades.FormularioPI102;

/**
 *
 * @author Eddy Valero
 * @see Expediente
 * @see ExpedienteService
 * @version 1.0, 23/08/2016
 */
public interface ExpedienteService {

    /**
     * Método que permite setear la conexion con dataSource
     *
     * @param dataSource
     * @throws java.lang.Exception
     */
    public void setDataSource(DataSource dataSource) throws Exception;

    /**
     * Método para obtener el registro de sigsignomarca
     *
     * Creado:Eddy Valero Fecha: 08/10/2016
     *
     * @param numeroExpediente
     * @param gestionExpediente
     * @param extensionExpediente
     * @return SigSignoMarca
     * @throws java.lang.Exception
     */
    public SigSignoMarca obtenerSigsignomarca(String numeroExpediente, String gestionExpediente, String extensionExpediente) throws Exception;

    /**
     * Método para ingresar un registro de marca
     *
     * Creado:Eddy Valero Fecha: 22/11/2016
     *
     * @param sigSignoMarca
     * @param lstImagenPojo
     * @param lstSigSignoClaseNiza
     * @param lstSolicitantes
     * @param lstApoderados
     * @param lstTipoSignos
     * @param sigDireccion
     * @param oposicion
     * @param prioridadExtranjera
     * @param prioridadExposicion
     * @param idUsuario
     * @return
     * @throws java.lang.Exception
     */
    public SigSignoMarca IngresarRegistroSolicitudSignoMarca(SigSignoMarca sigSignoMarca, List<ImagenPojo> lstImagenPojo,
            List<SigSignoClaseNiza> lstSigSignoClaseNiza, List<SigSolicitanteApoderado> lstSolicitantes,
            List<SigSolicitanteApoderado> lstApoderados, List<SigTipoSigno> lstTipoSignos,
            SigDireccionDeNotificacion sigDireccion, SigPrioridadPreferencia oposicion,
            SigPrioridadPreferencia prioridadExtranjera, SigPrioridadPreferencia prioridadExposicion,
            Long idUsuario) throws Exception;

    /**
     * Método para realizar el registro de sigsignomarca
     *
     * Creado:Eddy Valero Fecha: 08/10/2016
     *
     * @param sigSignoMarca
     * @param lstImagenPojo
     * @param lstSigSignoClaseNiza
     * @param lstSolicitantes
     * @param lstApoderados
     * @param lstTipoSignos
     * @param sigDireccion
     * @param oposicion
     * @param prioridadExtranjera
     * @param prioridadExposicion
     * @param idUsuario 
     * @throws java.lang.Exception
     */
    public void actualizarRegistroSolicitudSignoMarca(SigSignoMarca sigSignoMarca, List<ImagenPojo> lstImagenPojo,
            List<SigSignoClaseNiza> lstSigSignoClaseNiza, List<SigSolicitanteApoderado> lstSolicitantes,
            List<SigSolicitanteApoderado> lstApoderados, List<SigTipoSigno> lstTipoSignos, SigDireccionDeNotificacion sigDireccion,
            SigPrioridadPreferencia oposicion, SigPrioridadPreferencia prioridadExtranjera, SigPrioridadPreferencia prioridadExposicion,
            Long idUsuario) throws Exception;
    /**
     * Método para realizar el registro de sigsignomarca
     *
     * Creado:Placido Castro Fecha: 12/10/2017
     *
     * @param sigSignoMarca
     * @param lstImagenPojo
     * @param lstTipoSignos
     * @param sigDireccion
     * @param idUsuario 
     * @param formularioPI100
     * @throws java.lang.Exception
     */
    public void actualizarSubsanacionSignoMarcaPI100(SigSignoMarca sigSignoMarca, List<ImagenPojo> lstImagenPojo, List<SigTipoSigno> lstTipoSignos, SigDireccionDeNotificacion sigDireccion,
            Long idUsuario, FormularioPI100 formularioPI100) throws Exception; 
    
    /**
     * Método para realizar el registro de sigsignomarca
     *
     * Creado:Placido Castro Fecha: 12/10/2017
     *
     * @param sigSignoMarca
     * @param lstImagenPojo
     * @param lstTipoSignos
     * @param sigDireccion
     * @param idUsuario 
     * @param formularioPI101
     * @throws java.lang.Exception
     */
    public void actualizarSubsanacionSignoMarcaPI101(SigSignoMarca sigSignoMarca, List<ImagenPojo> lstImagenPojo, List<SigTipoSigno> lstTipoSignos, SigDireccionDeNotificacion sigDireccion,
            Long idUsuario, FormularioPI101 formularioPI101) throws Exception; 
    
    /**
     * Método para realizar el registro de sigsignomarca
     *
     * Creado:Placido Castro Fecha: 12/10/2017
     *
     * @param sigSignoMarca
     * @param lstImagenPojo
     * @param lstTipoSignos
     * @param sigDireccion
     * @param idUsuario 
     * @param formularioPI102
     * @throws java.lang.Exception
     */
    public void actualizarSubsanacionSignoMarcaPI102(SigSignoMarca sigSignoMarca, List<ImagenPojo> lstImagenPojo, List<SigTipoSigno> lstTipoSignos, SigDireccionDeNotificacion sigDireccion,
            Long idUsuario, FormularioPI102 formularioPI102) throws Exception; 
    
//    /**
//     * Método para obtener el siguiente registro del numero ingresado
//     *
//     * Creado:Eddy Valero Fecha: 16/11/2016
//     * 
//     * @param numeroExpediente
//     * @param gestionExpediente
//     * @param extensionExpediente
//     * @return SigSignoMarca
//     * @throws java.lang.Exception
//     */
//    public SigSignoMarca obtenerSiguienteRegistroSigSignoMarca(Long numeroExpediente, Long gestionExpediente, Long extensionExpediente) throws Exception;
    /**
     * Método para obtener el siguiente registro del numero ingresado
     *
     * Creado:Eddy Valero Fecha: 16/11/2016
     *
     * @param idSignoMarca
     * @return SigSignoMarca
     * @throws java.lang.Exception
     */
    public SigSignoMarca obtenerSiguienteRegistroSigSignoMarca(Long idSignoMarca) throws Exception;

    /**
     * Método para obtener el anterior registro del numero ingresado
     *
     * Creado:Eddy Valero Fecha: 17/11/2016
     *
     * @param idSignoMarca
     * @return SigSignoMarca
     * @throws java.lang.Exception
     */
//    public SigSignoMarca obtenerAnteriorRegistroSigSignoMarca(Long numeroExpediente, Long gestionExpediente, Long extensionExpediente) throws Exception;
    public SigSignoMarca obtenerAnteriorRegistroSigSignoMarca(Long idSignoMarca) throws Exception;

    /**
     * Método: algoritmo que obtiene el siguiente registro de acuerdo al numero
     * gestion y extensión ingresados
     *
     * Creado:Eddy Valero Fecha: 03/02/2017
     *
     * @param numero
     * @param gestion
     * @param extensionMarca
     * @return SigSignoMarca
     * @throws java.lang.Exception
     */
    public SigSignoMarca obtenerSiguienteRegistroSigSignoMarca(Integer numero, Integer gestion, Integer extensionMarca) throws Exception;

    /**
     * Método: algoritmo que obtiene el anterior registro de acuerdo al numero
     * gestion y extensión ingresados
     *
     * Creado:Eddy Valero Fecha: 06/02/2017
     *
     * @param numero
     * @param gestion
     * @param extensionMarca
     * @return SigSignoMarca
     * @throws java.lang.Exception
     */
    public SigSignoMarca obtenerAnteriorRegistroSigSignoMarca(Integer numero, Integer gestion, Integer extensionMarca) throws Exception;

    /**
     * Método: algoritmo que obtiene el anterior registro de acuerdo al numero
     * de publicacion, en el caso de que haya mas de un numero de publicacion
     * similar, obtener el anterior por el idsignomarca
     *
     * -------------------------------------------------------------------------
     * Creado:Eddy Valero Fecha: 20/03/2017
     *
     * @param idSignoMarca
     * @param numeroPublicacion
     *
     * @return SigSignoMarca
     * @throws java.lang.Exception
     */
    public SigSignoMarca obtenerAnteriorRegistroNumeroPublicacionSigSignoMarca(Long idSignoMarca, Long numeroPublicacion) throws Exception;

    /**
     * Método: algoritmo que obtiene el siguiente registro de acuerdo al numero
     * de publicacion, en el caso de que haya mas de un numero de publicacion
     * similar, obtener el siguiente por el idsignomarca
     *
     * -------------------------------------------------------------------------
     * Creado:Eddy Valero Fecha: 21/03/2017
     *
     * @param idSignoMarca
     * @param numeroPublicacion
     *
     * @return SigSignoMarca
     * @throws java.lang.Exception
     */
    public SigSignoMarca obtenerSiguienteRegistroNumeroPublicacionSigSignoMarca(Long idSignoMarca, Long numeroPublicacion) throws Exception;

    /**
     * Método: algoritmo que obtiene el anterior registro de acuerdo al
     * numero_registro y la serie, en el caso de que haya mas de un numero de
     * registro similar, obtener el anterior por el idsignomarca
     *
     * -------------------------------------------------------------------------
     * Creado:Eddy Valero Fecha: 21/03/2017
     *
     * @param idSignoMarca
     * @param numeroRegistro
     * @param serieRegistro
     *
     * @return SigSignoMarca
     * @throws java.lang.Exception
     */
    public SigSignoMarca obtenerAnteriorRegistroNumeroRegistroSigSignoMarca(Long idSignoMarca, Long numeroRegistro, String serieRegistro) throws Exception;
    
    
    /**
     * Método: algoritmo que obtiene el siguiente registro de acuerdo al
     * numero_registro y la serie, en el caso de que haya mas de un numero de
     * registro similar, obtener el anterior por el idsignomarca
     *
     * -------------------------------------------------------------------------
     * Creado:Eddy Valero Fecha: 21/03/2017
     *
     * @param idSignoMarca
     * @param numeroRegistro
     * @param serieRegistro
     *
     * @return SigSignoMarca
     * @throws java.lang.Exception
     */
    public SigSignoMarca obtenerSiguienteRegistroNumeroRegistroSigSignoMarca(Long idSignoMarca, Long numeroRegistro, String serieRegistro) throws Exception;

    /**
     * Método: algoritmo que obtiene claseniza en funcion idsignomarca
     *
     * -------------------------------------------------------------------------
     * Creado:Placido Castro Fecha: 11/08/2017
     *
     * @param idClaseNiza
     *
     * @return ClaseNiza
     * @throws java.lang.Exception
     */
    public ClaseNiza obtenerClaseNiza(Long idClaseNiza) throws Exception;    
}
