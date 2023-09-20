/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.servicio;

import java.util.Date;
import java.util.List;
import javax.sql.DataSource;
import snp.gob.bo.gimodel.entidad.BusquedaModificacionResultado;
import snp.gob.bo.gimodel.entidad.ModDireccionDeNotificacion;
import snp.gob.bo.gimodel.entidad.ModModificacion;
import snp.gob.bo.gimodel.entidad.ModTipoSigno;
import snp.gob.bo.gimodel.entidad.ModTitularLicenciatarioNuevo;
import snp.gob.bo.gimodel.entidad.ModTitularLicenciatarioRegistrado;
import snp.gob.bo.gimodel.entidad.SigSignoMarca;
import snp.gob.bo.gimodel.solicitudes.entidades.FormularioPI103;

/**
 *
 * @author susana
 */
public interface ModModificacionService {

    /**
     * Método que permite setear la conexion con dataSource
     *
     * Creado: Susana Escobar Paz Fecha: 05/09/2016
     *
     * @param dataSource
     */
    public void setDataSource(DataSource dataSource);

    /**
     * Metodo para guardar, modificar, listar registros en la tabla
     * modmodificacion
     *
     * Creado: Susana Escobar Fecha: 05/09/2016
     *
     * @param modModificacion
     * @param operacion
     * @return
     */
    public ModModificacion guardar_modificar_listar_ModModificacion(ModModificacion modModificacion, Integer operacion);

    /**
     * Metodo para guardar, modificar, listar registros en la tabla
     * modmodificacion
     *
     * Creado: Placido Castro Fecha: 25/11/2017
     *
     * @param modModificacion
     * @param formularioPI103
     * @param idLogTrans
     * @throws Exception
     * @return
     */
    public ModModificacion modificarModModificacionSubsanacion(ModModificacion modModificacion, FormularioPI103 formularioPI103, Long idLogTrans) throws Exception;
    
    /**
     * Metodo para guardar listar modmodificacion por idmodificacion
     *
     * Creado: Susana Escobar Fecha: 05/09/2016
     *
     * @param idmodificacion
     * @return
     */
    public ModModificacion buscarModModificacionXid(Long idmodificacion);

    /**
     * Metodo para guardar listar modmodificacion por sigla, numero y gestion
     *
     * Creado: Susana Escobar Fecha: 10/09/2016
     *
     * @param sigla
     * @param numero
     * @param gestion
     * @return
     */
    public ModModificacion buscarModModificacionXCodigo(String sigla, Long numero, Integer gestion);

    /**
     * Metodo para aceptar Transferencia y Fusion, se dan de baja los titulares
     * actuales y se agregan los nuevo titulares vigentes vinculados a la marca
     * principal
     *
     * Creado: Susana Escobar Fecha: 20/09/2016
     *
     * @param modificacion
     * @param marcaPrincipal
     * @param idlogtrans
     */
    public void aceptarTransferenciaFusion(ModModificacion modificacion, SigSignoMarca marcaPrincipal, Long idlogtrans);

    /**
     * Metodo para guardar respaldos de titulares actuales antes de ejecutar los
     * cambios, estos datos se almacenan en tabla modmodificacion
     *
     * Creado: Susana Escobar Fecha: 23/09/2016
     *
     * @param modificacion
     * @param marcaPrincipal
     */
    public void respaldo_sigsignomarca(ModModificacion modificacion, SigSignoMarca marcaPrincipal);

    /**
     * Metodo para listar ModModificacion resultados de la busqueda avanzada
     *
     * Creado: Susana Escobar Fecha: 23/09/2016
     *
     * @param tipo
     * @param estado
     * @param fechaInicio
     * @param fechaFin
     * @param regional
     * @return
     */
    public List<ModModificacion> lista_modmodificacion_avanzada(String tipo, String estado, Date fechaInicio, Date fechaFin, String regional);

    /**
     * Metodo que lista registros de tabla modmodificacion segun filtro por
     * apoderado, ttular, nombre signo y un texto a buscar
     *
     * Creado: Susana Escobar Fecha: 10/10/2016
     *
     * @param filtro
     * @param textoBusquedaSimple
     * @return
     */
    public List<ModModificacion> lista_modmodificacion_simple(String filtro, String textoBusquedaSimple);

    /**
     * Metodo para listar ModModificacion resultados de la busqueda avanzada
     *
     * Creado: Ruben Ramirez Fecha: 10/01/2017
     *
     * @param tipo
     * @param estado
     * @param fechaInicio
     * @param fechaFin
     * @param regional
     * @return
     */
    public List<BusquedaModificacionResultado> lista_modmodificacion_avanzada2(String tipo, String estado, Date fechaInicio, Date fechaFin, String regional);

    /**
     * Metodo que lista registros de tabla modmodificacion segun filtro por
     * apoderado, ttular, nombre signo y un texto a buscar
     *
     * Creado:Ruben Ramirez Fecha: 10/01/2017
     *
     * @param filtro
     * @param textoBusquedaSimple
     * @return
     */
    public List<BusquedaModificacionResultado> lista_modmodificacion_simple2(String filtro, String textoBusquedaSimple);

    /**
     * Metodo que lista registro de la tabla modmodificacion por tipo
     * modificacion, numero, gestion
     *
     * Creado: Susana Escobar Fecha: 10/10/2016
     *
     * @param tipo
     * @param numero
     * @param gestion
     * @return
     */
    public ModModificacion buscarModModificacionXtipo(String tipo, Long numero, Integer gestion);

    /**
     * Metodo para aceptar Cambio de nombre y Cambio de Domicilio, se dan de
     * baja los datos de nombre y direccion actuales y se agregan los nuevo
     * titulares vigentes vinculados a la marca principal Modificacion metodo
     * para aceptar el cambio de nombre o domicilio, tomando en cuenta
     * transformacion en numero de titulares y tipo_persona
     *
     * Creado: Susana Escobar Fecha: 11/09/2016 Modificado Susana Escobar Fecha:
     * 28/01/2017
     *
     * @param modificacion
     * @param marcaPrincipal
     * @param listaNuevoTitular
     * @param idlogtrans
     */
    public void aceptarCambioNombreDomicilio(ModModificacion modificacion, SigSignoMarca marcaPrincipal,
            List<ModTitularLicenciatarioNuevo> listaNuevoTitular, Long idlogtrans);

    /**
     * Metodo para listar modmodificacion por numero de formulario
     *
     * Creado: Ruben Ramirez Fecha: 09/11/2016
     *
     * @param nroFormulario
     * @return
     */
    public ModModificacion buscarModModificacionXnroFormulario(String nroFormulario);

    /**
     * Metodo para listar modmodificacion por numero de formulario
     *
     * Creado: Chano Rojas Fecha: 09/11/2016
     *
     * @param numeroFormulario
     * @return
     * @throws java.lang.Exception
     */
    public ModModificacion buscarModModificacion_NumeroFormulario(String numeroFormulario) throws Exception;

    /**
     * Metodo para verificar existencia de modmodificacion filtrado por sm o
     * registro o publicacion, se utiliza para el historial de modificacion en
     * la vista de signos
     *
     * Creado: Susana Escobar Paz Fecha: 15/11/2016
     *
     * @param sm
     * @param publicacion
     * @param registro
     * @param serie
     * @param tipo
     * @return
     */
    public Boolean[] existe_ModModificacion_sm_publicacion_registro_or(Long sm, Long publicacion, Long registro, String serie, String tipo);

    /**
     * Metodo para buscar registros modmodificacion filtrados por sm o registro
     * o publicacion, se utiliza para el dialogo historial de modificacion en la
     * vista de signos
     *
     * Creado: Susana Escobar Paz Fecha: 15/11/2016
     *
     * @param sm
     * @param publicacion
     * @param registro
     * @param serie
     * @return
     */
    public List<ModModificacion> lista_ModModificacion_sm_publicacion_registro_or(Long sm, Long publicacion, Long registro, String serie);

    /**
     * Metodo para guardar historial de cambios en tabla sighistorial, con datos
     * de la reolucion y tipo de modificacion
     *
     * Creado: Susana Escobar Paz Fecha: 15/11/2016 Modificado: Susana Escobar
     * Paz Fecha: 29/11/2016
     *
     * @param modificacion
     * @param idSignoMarca
     * @param valorRadio
     * @param numero
     * @param gestion
     * @param idusuario
     * @param fechaRegistro
     * @param idlogtrans
     */
    public void historial_ModModificacion_Signos(ModModificacion modificacion, Long idSignoMarca, String valorRadio, Integer numero, Integer gestion,
            Long idusuario, Date fechaRegistro, Long idlogtrans);
    
    /**
     * Metodo para encontrar el siguiente registro modmodificacion segun datos de sigla, numero y gestion
     *
     * Creado: Susana Escobar Paz 
     * Fecha: 08/02/2017
     * 
     * @param sigla
     * @param numero
     * @param gestion
     * @return
     */
    public ModModificacion obtenerSiguienteRegistroModModificacion(String sigla, Long numero, Integer gestion);
    
    /**
     * Metodo para encontrar el anterior registro modmodificacion segun datos de sigla, numero y gestion
     *
     * Creado: Susana Escobar Paz 
     * Fecha: 08/02/2017
     * 
     * @param sigla
     * @param numero
     * @param gestion
     * @return
     */
    public ModModificacion obtenerAnteriorRegistroModModificacion(String sigla, Long numero, Integer gestion);
    
    /**
     * Metodo para guardar, historial de cambios de todas las secciones del
     * formulario de modificaciones subsanacion
     *
     * Creado: Placido Castro Fecha: 21/11/2017
     *
     * @param modificacion
     * @param direccionNotificacion
     * @param idUsuario
     * @param listaTitularRegistrado
     * @param listaNuevoTitular
     * @param listaParticipanteFusion
     * @param listaModTipoSigno
     * @param formularioPI103
     * @throws Exception
     */
    public void actualizarModificacionPorSubsanacion(ModModificacion modificacion, ModDireccionDeNotificacion direccionNotificacion, List<ModTipoSigno> listaModTipoSigno, Long idUsuario, FormularioPI103 formularioPI103) throws Exception;    
}
