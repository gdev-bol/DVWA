package snp.gob.bo.entidades.expediente;

import java.awt.Image;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import snp.gob.bo.entidades.bean.common.AbstractManagedBean;
import snp.gob.bo.gimodel.bdimagen.entidad.ImagenPojo;
import snp.gob.bo.gimodel.bdimagen.entidad.SigImagen;
import snp.gob.bo.gimodel.bdimagen.entidad.SigLogoTipo;
import snp.gob.bo.gimodel.bdimagen.servicio.ImagenPojoService;
import snp.gob.bo.gimodel.bdimagen.servicio.SigImagenService;
import snp.gob.bo.gimodel.bdimagen.servicio.SigLogoTipoService;
import snp.gob.bo.gimodel.entidad.ClaseNiza;
import snp.gob.bo.gimodel.entidad.Dominio;
import snp.gob.bo.gimodel.entidad.Etapa;
import snp.gob.bo.gimodel.entidad.LogTrans;
import snp.gob.bo.gimodel.entidad.ObservacionTramite;
import snp.gob.bo.gimodel.entidad.Regional;
import snp.gob.bo.gimodel.entidad.Seguimiento;
import snp.gob.bo.gimodel.entidad.SigDireccionDeNotificacion;
import snp.gob.bo.gimodel.entidad.SigPrioridadPreferencia;
import snp.gob.bo.gimodel.entidad.SigSignoClaseNiza;
import snp.gob.bo.gimodel.entidad.SigSignoMarca;
import snp.gob.bo.gimodel.entidad.SigSolicitanteApoderado;
import snp.gob.bo.gimodel.entidad.SigTipoSigno;
import snp.gob.bo.gimodel.enums.EnumCarpetasTemporales;
import snp.gob.bo.gimodel.enums.EnumNombreDominio;
import snp.gob.bo.gimodel.enums.EnumTipoFormulario;
import snp.gob.bo.gimodel.enums.EnumTipoInteres;
import snp.gob.bo.gimodel.pojo.HistorialRenovacionModificacionPojo;
import snp.gob.bo.gimodel.servicio.ComunService;
import snp.gob.bo.gimodel.servicio.DominioService;
import snp.gob.bo.gimodel.servicio.EtapaService;
import snp.gob.bo.gimodel.servicio.ExpedienteService;
import snp.gob.bo.gimodel.servicio.LogTransService;
import snp.gob.bo.gimodel.servicio.ModModificacionService;
import snp.gob.bo.gimodel.servicio.NotificacionService;
import snp.gob.bo.gimodel.servicio.ObservacionTramiteService;
import snp.gob.bo.gimodel.servicio.OposicionService;
import snp.gob.bo.gimodel.servicio.RegionalService;
import snp.gob.bo.gimodel.servicio.SeguimientoService;
import snp.gob.bo.gimodel.servicio.SigDireccionDeNotificacionService;
import snp.gob.bo.gimodel.servicio.SigPrioridadPreferenciaService;
import snp.gob.bo.gimodel.servicio.SigSignoClaseNizaService;
import snp.gob.bo.gimodel.servicio.SigSignoMarcaService;
import snp.gob.bo.gimodel.servicio.SigSolicitanteApoderadoService;
import snp.gob.bo.gimodel.servicio.SigTipoSignoService;
import snp.gob.bo.gimodel.servicio.UsuarioPaginaService;
import snp.gob.bo.gimodel.servicio.UsuarioService;
import snp.gob.bo.gimodel.solicitudes.entidades.Apoderados;
import snp.gob.bo.gimodel.solicitudes.entidades.ClasesNiza;
import snp.gob.bo.gimodel.solicitudes.entidades.DireccionNotificaciones;
import snp.gob.bo.gimodel.solicitudes.entidades.FormularioPI100;
import snp.gob.bo.gimodel.solicitudes.entidades.FormularioPI101;
import snp.gob.bo.gimodel.solicitudes.entidades.FormularioPI102;
import snp.gob.bo.gimodel.solicitudes.entidades.Logotipos;
import snp.gob.bo.gimodel.solicitudes.entidades.Prioridades;
import snp.gob.bo.gimodel.solicitudes.entidades.SmSignoClaseNizas;
import snp.gob.bo.gimodel.solicitudes.entidades.SmTipoSignos;
import snp.gob.bo.gimodel.solicitudes.entidades.Solicitantes;
import snp.gob.bo.gimodel.solicitudes.servicio.FormularioPI100Service;
import snp.gob.bo.gimodel.solicitudes.servicio.FormularioPI101Service;
import snp.gob.bo.gimodel.solicitudes.servicio.FormularioPI102Service;
import snp.gob.bo.gimodel.solicitudes.servicio.FormularioService;
import snp.gob.bo.giview.ventanilla.IngresoSolicitudTramiteBean;

/**
 *
 * @author placido
 */
@ManagedBean(name = "expedienteSubsanacionBean")
@ViewScoped
public class ExpedienteSubsanacionBean extends AbstractManagedBean implements Serializable {

    //<editor-fold defaultstate="collapsed" desc="Definicion de Atributos">
    private String tipoSolicitante = "";
    private Boolean panelSolicitanteRendered = true;
    private String razonSocial;
    private String numeroDocumento;
    private String tipoDocumento;
    private String lugarExpedicion;
    private String pais;
    private String departamento;
    private String generoPersona;
    private String telefono;
    private String domicilio;
    private String correoElectronico;
    private String nombre;
    private String primerApellido;
    private String segundoApellido;
    private String smNum;
    private String smGestion;
    private String smExt;
    private FormularioPI100 formularioPI100 = new FormularioPI100();
    private FormularioPI101 formularioPI101 = new FormularioPI101();
    private FormularioPI102 formularioPI102 = new FormularioPI102();

    private Boolean panelNoEncontrado;
    private String mensajeBusqueda = "";

    private String template = "./../WEB-INF/facelets/templates/Template.xhtml";
    private SigSignoMarca sigSignoMarca;
    private String valorMarca;
    private String valorDescripcionSigno;
    private Long valorNumeroTitulo;
    private String numeroFormulario;
    private String regional = "";
    private String valorTipoGenero = "";
    private String valorSerieTitulo = "";
    private Date valorFechaIngreso = new Date();
    private List<Dominio> lstOficina = new ArrayList<>();
    private List<Dominio> lstTipoGenero = new ArrayList<>();
    private List<Dominio> lstSerieTitulo = new ArrayList<>();
    private List<Dominio> lstSituacionActual = new ArrayList<>();
    private List<Dominio> lstUbicacion = new ArrayList<>();
    private List<Dominio> lstSerieRegistro = new ArrayList<>();
    private List<Dominio> lstTipoSigno = new ArrayList<>();
    private List<Dominio> lstTipoDocumento = new ArrayList<>();
    private List<SigSignoClaseNiza> lstSigSignoClaseNiza = new ArrayList<>();
    private List<SmSignoClaseNizas> listaSigSignoClaseNizaSipi = new ArrayList<>();
    private List<SigTipoSigno> lstSigTipoSigno = new ArrayList<>();
    private List<SmTipoSignos> listaTipoSigno = new ArrayList<>();
    private List<Dominio> lstPaises = new ArrayList<>();
    //variables radios vista signos
    private String valorRadioOperacionFormulario = "CON";
    private String valorRadioBuscador = "soma";
    //busqueda solicitud de marca sm
    private Boolean buscarSMRendered = true;
    //busqueda numero de publicacion
    private Boolean buscarPURendered = false;
    //busqueda numero de registro
    private Boolean buscarRERendered = false;
    private String numeroExpediente;
    private String gestionExpediente = "";
    private String extensionExpediente = "";
    private String valorNumeroExpediente = "";
    private String valorGestionExpediente = "";
    private String valorExtensionExpediente = "";
    private Boolean valorOpcionRecibo = Boolean.TRUE;
    private Long valorNumeroRecibo;
    private String valorSerieRecibo = "";
    private Date valorFechaDerivacion = new Date();
    private String valorDescripcionDisenio = "";
    private String valorEstadoMarca = "";
    private String valorEstadoMarcaaux = "";
    private String valorUbicacion = "";
    //valores publicacion
    private Long valorNumeroPublicacion = null;
    private Long valorNumeroGaceta = null;
    private Date valorFechaPublicacion;
    //valores registro
    private Long valorNumeroRegistro = null;
    private String valorSerieRegistro = "";
    private String valorResolucionRegistro = "";
    private Date valorFechaRegistro;
    //valores renovacion
    private Integer valorNumeroRenovacion;
    private String valorExtensionRenovacion = "";
    private Long valorResolucionRenovacion;
    private Date valorFechaRenovacion;
    //valores Tipo de signo
    private String[] valorTipoSignoSeleccionado = new String[10];
    private String valorTipoSignoOtro = "";
    private Boolean txtOtroRendered = Boolean.FALSE;
    private Boolean renderDenomina = Boolean.TRUE;
    //valores prioridades
    private SigPrioridadPreferencia prioridadExtranjera = new SigPrioridadPreferencia();
    private SigPrioridadPreferencia prioridadExposicion = new SigPrioridadPreferencia();
    private SigPrioridadPreferencia oposicionAndina = new SigPrioridadPreferencia();

    private Prioridades prioridadExtranjeraSipi = new Prioridades();
    private Prioridades prioridadExposicionSipi = new Prioridades();
    private Prioridades oposicionAndinaSipi = new Prioridades();

    //valor direccion
    private SigDireccionDeNotificacion sigDireccionNotificacion = new SigDireccionDeNotificacion();
    private DireccionNotificaciones direccionNotificacionSipi = new DireccionNotificaciones();
    private Integer indice = 0;
    //valores lista de solicitantes
    private List<SigSolicitanteApoderado> lstSolicitantes = new ArrayList<>();
    private List<Solicitantes> listaSolicitantesSipi = new ArrayList<>();
    private List<Apoderados> listaApoderadosSipi = new ArrayList<>();

    //valores lista de apoderados
    private List<SigSolicitanteApoderado> lstApoderados = new ArrayList<>();
    //valor observaciones
    private ObservacionTramite observacionTramite = new ObservacionTramite();
    //imagenes sigsignomarca
    private Boolean btnAdicionarDisabled = Boolean.TRUE;
    private Boolean btnModificarDisabled = Boolean.TRUE;
    //persona o apoderado seleccionado
    private SigSolicitanteApoderado sigSolicitanteApoderadoSelect = new SigSolicitanteApoderado();
    //claseniza seleccionado
    private SigSignoClaseNiza sigSignoClaseNizaSelect = new SigSignoClaseNiza();
    //Imagen
    private ImagenPojo imagenPojo = new ImagenPojo();
    private List<ImagenPojo> lstImagenPojos = new ArrayList<>();
    private Boolean botonRecepcionarDisabled = true;
    private Boolean botonFinalizarDisabled = true;
    private Seguimiento ultimoSeguimiento;
    private Seguimiento ultimoSeguimientoopo;
    private List<SigSignoClaseNiza> lstSigSignoClaseNizaRepo = new ArrayList<>();
    private SigSignoMarca sigSignoMarcaRepo;
    private List<Dominio> lstTipoGeneroXdominio = new ArrayList<>();
    private SigLogoTipo sigLogoTipo = new SigLogoTipo();
    private SigImagen sigImage;
    private Image imagenes = null;
    //Bloque validaciones de formularios
    private Boolean validarFormulario = Boolean.FALSE;
    private Boolean validarSigSignoMarca = Boolean.FALSE;
    private Boolean panelOtro = Boolean.FALSE;
    private String textoOtroPI100 = "";
    private String mensajeValidacionSigSignoMarca = "";
    //Variables para el boton de oposicion
    private String nombreBotonOpo = "Oposición";
    private String backgroundOPO = "#c7cccd";
    private Boolean botonOposicionDisabled = true;
    //usuario
    private Long idUsuario = null;
    private Long logtrans;
    private List<Etapa> listEtapaUs;
    private Boolean btnSeguimientoRendered = false;
    private String tipoSignoDescripcion = "Elegir";
    private List<Regional> lstRegional = new ArrayList<>();
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Definicion de Servicios">
    @ManagedProperty("#{dominioService}")
    private DominioService sMDominioService;
    @ManagedProperty(value = "#{formularioPI100Service}")
    private FormularioPI100Service formularioPI100Service;

    @ManagedProperty(value = "#{formularioPI101Service}")
    private FormularioPI101Service formularioPI101Service;

    @ManagedProperty(value = "#{formularioPI102Service}")
    private FormularioPI102Service formularioPI102Service;

    @ManagedProperty("#{dominioService}")
    private DominioService dominioService;

    @ManagedProperty("#{expedienteService}")
    private ExpedienteService expedienteService;

    @ManagedProperty("#{comunService}")
    private ComunService comunService;

    @ManagedProperty("#{sigSignoClaseNizaService}")
    private SigSignoClaseNizaService sigSignoClaseNizaService;

    @ManagedProperty("#{sigTipoSignoService}")
    private SigTipoSignoService sigTipoSignoService;

    @ManagedProperty("#{sigPrioridadPreferencia}")
    private SigPrioridadPreferenciaService sigPrioridadPreferenciaService;

    @ManagedProperty("#{sigDireccionDeNotificacionService}")
    private SigDireccionDeNotificacionService sigDireccionNotificacionService;

    @ManagedProperty("#{sigSolicitanteApoderadoService}")
    private SigSolicitanteApoderadoService sigSolicitanteApoderadoService;

    @ManagedProperty("#{observacionTramiteService}")
    private ObservacionTramiteService observacionTramiteService;

    @ManagedProperty("#{sigLogoTipoService}")
    private SigLogoTipoService sigLogoTipoService;

    @ManagedProperty("#{sigImagenService}")
    private SigImagenService sigImagenService;

    @ManagedProperty("#{sigSignoMarcaService}")
    private SigSignoMarcaService sigSignoMarcaService;

    @ManagedProperty("#{seguimientoService}")
    private SeguimientoService seguimientoService;

    @ManagedProperty("#{imagenPojoService}")
    private ImagenPojoService imagenPojoService;

    @ManagedProperty("#{modModificacionService}")
    private ModModificacionService modModificacionService;

    @ManagedProperty("#{oposicionService}")
    private OposicionService oposicionService;

    @ManagedProperty("#{logTransService}")
    private LogTransService logTransService;

    @ManagedProperty("#{usuarioService}")
    private UsuarioService usuarioService;

    @ManagedProperty("#{etapaService}")
    private EtapaService etapaService;

    @ManagedProperty("#{regionalService}")
    private RegionalService regionalService;

    @ManagedProperty(value = "#{notificacionService}")
    private NotificacionService notificacionService;

    @ManagedProperty(value = "#{usuarioPaginaService}")
    private UsuarioPaginaService usuarioPaginaService;

    @ManagedProperty(value = "#{formularioService}")
    private FormularioService formularioService;
    //</editor-fold>

    @PostConstruct
    public void init() {

        try {
            idUsuario = super.getIdUsuarioSession();

            obtenerParametrosBusquedaExpediente();// recupera los valores idSignomarca y idUsuario

            inicializarVista();
            Date fechaSistema = comunService.obtenerFechaHoraServidor(1L);

            LogTrans logTransGuardado = logTransService.crudLogTrans(new LogTrans(idUsuario, fechaSistema), 1);
            logtrans = logTransGuardado.getIdLogTrans();

            listEtapaUs = etapaService.listadoPorIdUsuario(super.getIdUsuarioSession(), super.getIdEtapaSession());

        } catch (Exception e) {
            Logger.getLogger(AgregarClaseNizaBean.class.getName()).log(Level.SEVERE, null, e);
        }

    }

    /**
     * Metodo inicializar variables de la vista
     *
     * Creado: Eddy Valero Fecha:20/09/2016
     */
    public void inicializarVista() {
        try {

            //inicializar el objeto principal
            //sigSignoMarca = new SigSignoMarca();
            //Obtener la fecha de derivacion
            //this.valorFechaDerivacion = comunService.obtenerFechaHoraServidor(1L);
            //En la pantalla principal de signos obtener la fecha de derivación como
            Calendar fechaDerivacion = Calendar.getInstance();
            fechaDerivacion.setTime(comunService.obtenerFechaHoraServidor(1L));
            fechaDerivacion.add(Calendar.DAY_OF_YEAR, 1);
            this.valorFechaDerivacion = fechaDerivacion.getTime();

            //obtener el listado de las ciudades de notificación.
            this.lstOficina = dominioService.obtenerListadoDominio("oficina");
            this.lstTipoGenero = dominioService.obtenerListadoDominio("tipo_genero");
            this.lstSerieTitulo = dominioService.obtenerListadoDominio("serie_titulo");
            this.lstSituacionActual = dominioService.obtenerListadoDominio("estado_marca");
            this.lstUbicacion = dominioService.obtenerListadoDominio("ubicacion");
            this.lstSerieRegistro = dominioService.obtenerListadoDominio("serie_registro");
            this.lstTipoSigno = dominioService.obtenerListadoDominio("tipo_signo");
            this.lstPaises = dominioService.obtenerListadoDominio("pais");
            this.lstTipoDocumento = dominioService.obtenerListadoDominio("tipo_documento");
            //variables iniciales de la vista
            this.btnAdicionarDisabled = Boolean.TRUE;
            this.btnModificarDisabled = Boolean.TRUE;
            this.valorRadioOperacionFormulario = "CON";
            this.valorNumeroExpediente = "";
            this.valorExtensionExpediente = "";
            //inicializar variables de la imagen
            //this.imagenPojo.getSigImagen()

            //inicializar formulario si tiene mas de un registro
            if (getIdSignoSession() != null) {

                //obtener sigsignomarca.
                sigSignoMarca = sigSignoMarcaService.obtenerSigSignoMarcaXidSignoMarca(getIdSignoSession());

                //cargar datos de expediente.
                this.valorNumeroTitulo = sigSignoMarca.getNumeroTitulo();
                this.valorSerieTitulo = sigSignoMarca.getSerieTitulo();
                this.valorNumeroRecibo = sigSignoMarca.getNumeroRecibo();
                this.valorSerieRecibo = sigSignoMarca.getSerie();
                this.valorNumeroTitulo = sigSignoMarca.getNumeroTitulo();
                this.valorSerieTitulo = sigSignoMarca.getSerieTitulo();
                this.valorFechaIngreso = sigSignoMarca.getFechaIngreso();

                //Obtener la decodificacion del signo.
                HashMap mapResultado = comunService.obtenerNumeroGestionExtensionCodigoSM(sigSignoMarca.getSm());

                this.valorNumeroExpediente = mapResultado.get("numero").toString();
                this.valorGestionExpediente = mapResultado.get("gestion").toString();
                this.valorExtensionExpediente = mapResultado.get("extension").toString();
            }

            //Obtener la decodificacion del signo.
            HashMap mapResultado = comunService.obtenerNumeroGestionExtensionCodigoSM(sigSignoMarca.getSm());

            this.valorNumeroExpediente = mapResultado.get("numero").toString();
            this.valorGestionExpediente = mapResultado.get("gestion").toString();
            this.valorExtensionExpediente = mapResultado.get("extension").toString();

            if (getNavegaPagina() != null && !getNavegaPagina().equals("")) {
                template = "./../WEB-INF/facelets/templates/DialogTemplate.xhtml";
            }

            eliminarVariablesSession();
        } catch (Exception ex) {
            Logger.getLogger(ExamenSignosBean.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * *
     * Metodo que realiza la busqueda de un formulario externo para subsanacion
     * Creado: Placido Castro Fecha: 25/09/2017
     *
     */
    public void buscarFormularioExterno() {
        if (!numeroFormulario.trim().equalsIgnoreCase("")) {

            HashMap mapResultado = new HashMap();
            formularioPI100 = new FormularioPI100();
            formularioPI101 = new FormularioPI101();
            formularioPI102 = new FormularioPI102();
            this.panelNoEncontrado = Boolean.FALSE;

            try {

                mapResultado = this.formularioService.obtenerNumeroFormularioSubsanacion(numeroFormulario, sigSignoMarca.getNumeroFormulario());

                if (mapResultado.get("continuarFlujo").equals("true")) {

                    if (mapResultado.get("estado").equals("AC")) {

                        //obtener el o los formularios dependiendo
                        if (mapResultado.get("tipoFormulario").equals(EnumTipoFormulario.REGISTRO_SIGNO_DISTINTIVO.getCodigo())) {
                            generarFormularioPI100(mapResultado.get("idFormulario").toString());
                        } else if (mapResultado.get("tipoFormulario").equals(EnumTipoFormulario.REGISTRO_NOMBRE_COMERCIAL.getCodigo())) {
                            generarFormularioPI101(mapResultado.get("idFormulario").toString());
                        } else if (mapResultado.get("tipoFormulario").equals(EnumTipoFormulario.REGISTRO_LEMA_COMERCIAL.getCodigo())) {
                            generarFormularioPI102(mapResultado.get("idFormulario").toString());
                        } else {
                            FacesContext.getCurrentInstance().addMessage(
                                    null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Nro Formulario no corresponde a un trámite PI100 o PI101 o PI102", ""));
                        }
                    } else if (mapResultado.get("estado").equals("IN")) {
                        // El formulario ya fue ingresado
                        this.mensajeBusqueda = mapResultado.get("mensaje").toString();
                        this.panelNoEncontrado = Boolean.TRUE;
                    } else if (mapResultado.get("estado").equals("SU")) {
                        // El formulario ya ingresó como subsanación
                        this.mensajeBusqueda = mapResultado.get("mensaje").toString();
                        this.panelNoEncontrado = Boolean.TRUE;
                    }

                } else {
                    //No existe el formulario
                    this.panelNoEncontrado = Boolean.TRUE;
                    this.mensajeBusqueda = mapResultado.get("mensaje").toString();
                    limpiarExamenSigno();
                }

            } catch (Exception ex) {
                Logger.getLogger(IngresoSolicitudTramiteBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {

            this.panelNoEncontrado = Boolean.FALSE;
            FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Introduzca Nro Formulario", ""));
        }
    }

    //<editor-fold defaultstate="collapsed" desc="Generar formularios de acuerdo al tramite">
    /**
     * Método que permite generar el FormularioPI100 creado: Eddy Valero Fecha:
     * 06/09/2016
     *
     * @param idFormulario
     */
    public void generarFormularioPI100(String idFormulario) {

        try {
            formularioPI100 = this.formularioPI100Service.obtenerDatosFormularioPI100(Long.parseLong(idFormulario));

            //cambiarRegionalSM();
            for (SmTipoSignos smTipoSignos : formularioPI100.getSmTipoSignos()) {
                if (smTipoSignos.getTipoSigno().equals("OTRO")) {
                    this.panelOtro = Boolean.TRUE;
                    this.textoOtroPI100 = smTipoSignos.getDescripcionOtro();

                }
            }
            cargarDatosExpedienteExternoPI100();

            //cargarArchivoWAR(formularioPI100.getLogotipos());
        } catch (Exception ex) {
            Logger.getLogger(IngresoSolicitudTramiteBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Método que permite generar el FormularioPI101
     *
     * creado: Eddy Valero Fecha: 06/09/2016
     *
     * @param idFormulario
     */
    public void generarFormularioPI101(String idFormulario) {

        try {
            formularioPI101 = this.formularioPI101Service.obtenerDatosFormularioPI101(Long.parseLong(idFormulario));
            //cambiarRegionalSM();

            cargarDatosExpedienteExternoPI101();

            //cargarArchivoWAR(formularioPI101.getLogotipos());
        } catch (Exception ex) {
            Logger.getLogger(IngresoSolicitudTramiteBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Método que permite generar el FormularioPI102
     *
     * creado: Eddy Valero Fecha: 06/09/2016
     *
     * @param idFormulario
     */
    public void generarFormularioPI102(String idFormulario) {

        try {

            formularioPI102 = this.formularioPI102Service.obtenerDatosFormularioPI102(Long.parseLong(idFormulario));
            //cambiarRegionalSM();

            cargarDatosExpedienteExternoPI102();

        } catch (Exception ex) {
            Logger.getLogger(IngresoSolicitudTramiteBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     *
     * Metodo que obtiene la imagen y lo carga en el war de la aplicacion
     *
     * Creado: Eddy Valero Kari Fecha: 28/10/2016
     *
     * @param listaLogotipos
     */
    public void cargarArchivoWAR(List<Logotipos> listaLogotipos) {

        if (listaLogotipos.size() > 0) {

            FacesContext facesContext = FacesContext.getCurrentInstance();
            ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();
            String rutaWar = servletContext.getRealPath(EnumCarpetasTemporales.CARPETA_IMAGENES.getCodigo());

            File targetFolder = new File(rutaWar);

            if (!targetFolder.exists()) {
                targetFolder.mkdirs();
            }

            byte[] fotoByte = listaLogotipos.get(0).getImagen();

            String imagenDibuja = rutaWar + File.separator + listaLogotipos.get(0).getNombreArchivo();

            //enviar el imagenPojo a la vista, antes hacemos una previsualizacion
            crearArchivo(fotoByte, imagenDibuja);

        }

    }

    public String devuelveVersion(SmSignoClaseNizas signoClaseNizaSipi) {
        String version = "";
        try {
            ClasesNiza claseNizaSipi = formularioService.obtenerClaseNiza(signoClaseNizaSipi.getClaseNizaId());
            if (claseNizaSipi != null) {
                version = claseNizaSipi.getNumeroEdicion();
            }
        } catch (Exception e) {
            Logger.getLogger(ClaseNiza.class.getName()).log(Level.SEVERE, null, e);
        }

        return version;
    }
    
    public void inicioAdicionarFormulario() {
        try {
            this.numeroExpediente = null;
            this.gestionExpediente = null;
            this.valorNumeroExpediente = null;
            this.valorGestionExpediente = null;
            this.valorExtensionExpediente = null;
            this.valorNumeroRecibo = null;
            this.valorSerieRecibo = null;
            this.valorMarca = "";
            this.valorDescripcionSigno = "";
            this.valorNumeroTitulo = null;
            this.valorSerieTitulo = "";
            this.valorTipoGenero = "";
            this.lstImagenPojos = new ArrayList<ImagenPojo>();
            this.valorTipoSignoSeleccionado = new String[10];
            this.valorTipoSignoOtro = "";
            this.valorFechaIngreso = new Date();
            this.valorFechaDerivacion = comunService.obtenerFechaHoraServidor(1L);
            this.valorDescripcionDisenio = "";
            this.lstSigSignoClaseNiza = new ArrayList<SigSignoClaseNiza>();
            this.lstSolicitantes = new ArrayList<SigSolicitanteApoderado>();
            this.lstApoderados = new ArrayList<SigSolicitanteApoderado>();
            this.sigDireccionNotificacion = new SigDireccionDeNotificacion();
            this.prioridadExtranjera = new SigPrioridadPreferencia();
            this.prioridadExposicion = new SigPrioridadPreferencia();
            this.oposicionAndina = new SigPrioridadPreferencia();
            this.observacionTramite = new ObservacionTramite();
            this.valorNumeroPublicacion = null;
            this.valorNumeroGaceta = null;
            this.valorFechaPublicacion = null;
            this.valorNumeroRegistro = null;
            this.valorResolucionRegistro = null;
            this.valorFechaRegistro = null;
            this.valorNumeroRenovacion = null;
            this.valorResolucionRenovacion = null;
            this.valorFechaRenovacion = null;

            //el valor por defecto
//        this.valorOpcionRecibo = Boolean.TRUE;
        } catch (Exception ex) {
            Logger.getLogger(ExamenSignosBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     *
     * Metodo para obtener parametros de entrada de un linck
     *
     * Creado: Eddy Valero Fecha: 09/12/201
     */
    public void obtenerParametrosBusquedaExpediente() {
        try {

            Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
            String parametroIdSigno = (String) (params.get("ThrEimhaJd5"));
            String parametroIdUsuario = (String) (params.get("UkYJ0g3jLwc"));
            if (parametroIdSigno != null && parametroIdUsuario != null) {
                if (!parametroIdSigno.trim().equals("") && !parametroIdUsuario.trim().equals("") && !parametroIdSigno.equals("null") && !parametroIdUsuario.equals("null")) {
                    setIdUsuario(Long.parseLong(parametroIdUsuario));
                    setIdSignoSession(Long.parseLong(parametroIdSigno));
                }
            }
        } catch (Exception e) {
            Logger.getLogger(ExamenSignosBean.class
                    .getName()).log(Level.SEVERE, null, e);
        }

    }

    public void cargarDatosExpedienteExternoPI100() {
        try {
            sigImage = new SigImagen();
            if (sigSignoMarca != null) {

                if (formularioPI100.getFormularios() != null) {

                    this.valorMarca = formularioPI100.getSignoMarca().getMarca();
                    this.valorDescripcionSigno = formularioPI100.getSignoMarca().getDescripcionLogo();
                    this.numeroFormulario = formularioPI100.getFormularios().getNumeroFormulario();
                    this.valorTipoGenero = formularioPI100.getSignoMarca().getTipoGenero(); //formularioPI100.getTipoGenero();

                    //Obtener el listado de claseniza
                    this.listaSigSignoClaseNizaSipi = formularioPI100.getSmSignoClaseNizases(); 

                    this.valorTipoSignoOtro = "";
                    //this.lstSigTipoSigno = sigTipoSignoService.listaSigTipoSignoXidSIgnoMarca(sigSignoMarca.getIdSignoMarca());
                    this.listaTipoSigno = formularioPI100.getSmTipoSignos();

                    //armar la lista de tipoGeneroSeleccionado
                    this.valorTipoSignoSeleccionado = new String[10];
                    int i = 0;
                    this.txtOtroRendered = Boolean.FALSE;
                    for (SmTipoSignos item : listaTipoSigno) {
                        this.valorTipoSignoSeleccionado[i] = item.getTipoSigno();
                        //preguntar si el item es otro, en caso afirmativo, cargar este valor

                        if (item.getTipoSigno().equals("OTRO")) {
                            this.valorTipoSignoOtro = item.getDescripcionOtro();
                            this.txtOtroRendered = Boolean.TRUE;
                        }
                        i++;
                    }
                    if (lstSigTipoSigno.size() == 1) {
                        renderDenomina = !lstSigTipoSigno.get(0).getTipoSigno().equals("DEN");
                    } else {
                        renderDenomina = true;
                    }

                    tipoSignoDescripcion = formularioService.obtenerListaSigTipoSignoExt(formularioPI100.getSignoMarca().getId());

                    for (Prioridades prioridadExt : formularioPI100.getPrioridades()) {
                        switch (prioridadExt.getTipoInteres()) {
                            case "EXT":
                                this.prioridadExtranjeraSipi = prioridadExt;
                                //Obtener el dominio a partir del nombre
                                if (this.prioridadExtranjeraSipi.getTipoInteres().equals(EnumTipoInteres.EXTRANJERA.getCodigo())) {
                                    this.prioridadExtranjeraSipi.setLugar(dominioService.obtenerCodigoDominioPorNombre("pais", this.prioridadExtranjeraSipi.getLugar()));
                                }
                                break;
                            case "EXP":
                                this.prioridadExposicionSipi = prioridadExt;
                                break;
                            case "OA":
                                this.oposicionAndinaSipi = prioridadExt;
                                break;
                        }
                    }

                    //cargar la direccion interno
                    this.sigDireccionNotificacion = sigDireccionNotificacionService.obtenerDireccionNotificacionXSignoMarca(this.sigSignoMarca.getIdSignoMarca());
                    
                    //cargar la direccion externo    
                    this.direccionNotificacionSipi = formularioPI100.getDirecciones().get(0);

                    //cargar los solicitantes externo
                    this.listaSolicitantesSipi = formularioPI100.getSolicitantes();

                    //cargar los apoderados externo
                    this.listaApoderadosSipi = formularioPI100.getApoderados();

                    //cargar las imágen interna de la marca
                    this.imagenPojo = new ImagenPojo();
                    lstImagenPojos = new ArrayList<>();
                    this.imagenPojo = imagenPojoService.obtenerImagePojoXSignoMarca(this.sigSignoMarca.getIdSignoMarca());

                    if (imagenPojo.getSigLogoTipo().getIdLogoTipo() != null) {

                        //se debe subir la imagen en el servidor
                        //enviar el imagenPojo a la vista, antes hacemos una previsualizacion
                        this.lstImagenPojos.add(imagenPojo);

                        cargarArchivoWAR();
                    }

                    sigLogoTipo = sigLogoTipoService.obtenerRegistroPrincipalSigLogoTipoXIdSignoMarca(this.sigSignoMarca.getIdSignoMarca());
                    if (sigLogoTipo.getIdLogoTipo() != null) {
                        sigImage = sigImagenService.obtenerSigImagenXIdSigLogoTipo(sigLogoTipo.getIdLogoTipo());
                    }

                    cargarArchivoWAR(formularioPI100.getLogotipos());

                }

            } else {

                //limpiar los criterios de busqueda
                inicioAdicionarFormulario();

                if (this.numeroExpediente == null
                        && this.gestionExpediente == null) {

                    /*Mensaje de Error*/
                    FacesContext.getCurrentInstance().addMessage(
                            null, new FacesMessage(FacesMessage.SEVERITY_INFO, "No existe la solicitud de marca, verificar la busqueda", "")
                    );
                } else {
                    /*Mensaje de Error*/
                    FacesContext.getCurrentInstance().addMessage(
                            null, new FacesMessage(FacesMessage.SEVERITY_INFO, "No existe la solicitud de marca: " + this.numeroExpediente + "-" + this.gestionExpediente, "")
                    );
                }

            }

        } catch (Exception ex) {

            Logger.getLogger(ExamenSignosBean.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void cargarDatosExpedienteExternoPI101() {
        try {
            sigImage = new SigImagen();
            if (sigSignoMarca != null) {

                if (formularioPI101.getFormularios() != null) {

                    this.valorMarca = formularioPI101.getSignoMarca().getMarca();
                    this.valorDescripcionSigno = formularioPI101.getSignoMarca().getDescripcionLogo();
                    this.numeroFormulario = formularioPI101.getFormularios().getNumeroFormulario();
                    this.valorTipoGenero = formularioPI101.getSignoMarca().getTipoGenero(); //formularioPI100.getTipoGenero();

                    //Obtener el listado de claseniza
                    this.listaSigSignoClaseNizaSipi = formularioPI101.getSmSignoClaseNizases(); //sigSignoClaseNizaService.obtenerListaSigSignoClazeNizaXIdSignoMarca(sigSignoMarca.getIdSignoMarca());

                    this.valorTipoSignoOtro = "";
                    //this.lstSigTipoSigno = sigTipoSignoService.listaSigTipoSignoXidSIgnoMarca(sigSignoMarca.getIdSignoMarca());
                    this.listaTipoSigno = formularioPI101.getSmTipoSignos();

                    //armar la lista de tipoGeneroSeleccionado
                    this.valorTipoSignoSeleccionado = new String[10];
                    int i = 0;
                    this.txtOtroRendered = Boolean.FALSE;
                    for (SmTipoSignos item : listaTipoSigno) {
                        this.valorTipoSignoSeleccionado[i] = item.getTipoSigno();
                        //preguntar si el item es otro, en caso afirmativo, cargar este valor

                        if (item.getTipoSigno().equals("OTRO")) {
                            this.valorTipoSignoOtro = item.getDescripcionOtro();
                            this.txtOtroRendered = Boolean.TRUE;
                        }
                        i++;
                    }
                    if (lstSigTipoSigno.size() == 1) {
                        renderDenomina = !lstSigTipoSigno.get(0).getTipoSigno().equals("DEN");
                    } else {
                        renderDenomina = true;
                    }

                    tipoSignoDescripcion = formularioService.obtenerListaSigTipoSignoExt(formularioPI101.getSignoMarca().getId());

                    for (Prioridades prioridadExt : formularioPI101.getPrioridades()) {
                        switch (prioridadExt.getTipoInteres()) {
                            case "EXT":
                                this.prioridadExtranjeraSipi = prioridadExt;
                                //Obtener el dominio a partir del nombre
                                if (this.prioridadExtranjeraSipi.getTipoInteres().equals(EnumTipoInteres.EXTRANJERA.getCodigo())) {
                                    this.prioridadExtranjeraSipi.setLugar(dominioService.obtenerCodigoDominioPorNombre("pais", this.prioridadExtranjeraSipi.getLugar()));
                                }
                                break;
                            case "EXP":
                                this.prioridadExposicionSipi = prioridadExt;
                                break;
                            case "OA":
                                this.oposicionAndinaSipi = prioridadExt;
                                break;
                        }
                    }

                    //cargar la direccion interno
                    this.sigDireccionNotificacion = sigDireccionNotificacionService.obtenerDireccionNotificacionXSignoMarca(this.sigSignoMarca.getIdSignoMarca());
                    //cargar la direccion externo    
                    this.direccionNotificacionSipi = formularioPI101.getDirecciones().get(0);

                    //cargar los solicitantes interno
                    this.lstSolicitantes = sigSolicitanteApoderadoService.listadoSolicitanteXidsignomarca(this.sigSignoMarca.getIdSignoMarca());

                    //cargar los solicitantes externo
                    this.listaSolicitantesSipi = formularioPI101.getSolicitantes();

                    //cargar los apoderados interno
                    this.lstApoderados = sigSolicitanteApoderadoService.listadoApoderadoXidsignomarca(this.sigSignoMarca.getIdSignoMarca());

                    //cargar los apoderados externo
                    this.listaApoderadosSipi = formularioPI101.getApoderados();

                    //cargar las imágen interna de la marca
                    this.imagenPojo = new ImagenPojo();
                    lstImagenPojos = new ArrayList<>();
                    this.imagenPojo = imagenPojoService.obtenerImagePojoXSignoMarca(this.sigSignoMarca.getIdSignoMarca());

                    if (imagenPojo.getSigLogoTipo().getIdLogoTipo() != null) {

                        //se debe subir la imagen en el servidor
                        //enviar el imagenPojo a la vista, antes hacemos una previsualizacion
                        this.lstImagenPojos.add(imagenPojo);

                        cargarArchivoWAR();
                    }

                    sigLogoTipo = sigLogoTipoService.obtenerRegistroPrincipalSigLogoTipoXIdSignoMarca(this.sigSignoMarca.getIdSignoMarca());
                    if (sigLogoTipo.getIdLogoTipo() != null) {
                        sigImage = sigImagenService.obtenerSigImagenXIdSigLogoTipo(sigLogoTipo.getIdLogoTipo());
                    }

                    cargarArchivoWAR(formularioPI101.getLogotipos());

                }

            } else {

                //limpiar los criterios de busqueda
                inicioAdicionarFormulario();

                if (this.numeroExpediente == null
                        && this.gestionExpediente == null) {

                    /*Mensaje de Error*/
                    FacesContext.getCurrentInstance().addMessage(
                            null, new FacesMessage(FacesMessage.SEVERITY_INFO, "No existe la solicitud de marca, verificar la busqueda", "")
                    );
                } else {
                    /*Mensaje de Error*/
                    FacesContext.getCurrentInstance().addMessage(
                            null, new FacesMessage(FacesMessage.SEVERITY_INFO, "No existe la solicitud de marca: " + this.numeroExpediente + "-" + this.gestionExpediente, "")
                    );
                }

            }

        } catch (Exception ex) {

            Logger.getLogger(ExamenSignosBean.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void cargarDatosExpedienteExternoPI102() {
        try {
            sigImage = new SigImagen();
            if (sigSignoMarca != null) {

                if (formularioPI102.getFormularios() != null) {

                    this.valorMarca = formularioPI102.getSignoMarca().getMarca();
                    this.valorDescripcionSigno = formularioPI102.getSignoMarca().getDescripcionLogo();
                    this.numeroFormulario = formularioPI102.getFormularios().getNumeroFormulario();
                    this.valorTipoGenero = formularioPI102.getSignoMarca().getTipoGenero(); //formularioPI100.getTipoGenero();

                    //Obtener el listado de claseniza
                    this.listaSigSignoClaseNizaSipi = formularioPI102.getSmSignoClaseNizases(); //sigSignoClaseNizaService.obtenerListaSigSignoClazeNizaXIdSignoMarca(sigSignoMarca.getIdSignoMarca());

                    this.valorTipoSignoOtro = "";
                    //this.lstSigTipoSigno = sigTipoSignoService.listaSigTipoSignoXidSIgnoMarca(sigSignoMarca.getIdSignoMarca());
                    this.listaTipoSigno = formularioPI102.getSmTipoSignos();

                    //armar la lista de tipoGeneroSeleccionado
                    this.valorTipoSignoSeleccionado = new String[10];
                    int i = 0;
                    this.txtOtroRendered = Boolean.FALSE;
                    for (SmTipoSignos item : listaTipoSigno) {
                        this.valorTipoSignoSeleccionado[i] = item.getTipoSigno();
                        //preguntar si el item es otro, en caso afirmativo, cargar este valor

                        if (item.getTipoSigno().equals("OTRO")) {
                            this.valorTipoSignoOtro = item.getDescripcionOtro();
                            this.txtOtroRendered = Boolean.TRUE;
                        }
                        i++;
                    }
                    if (lstSigTipoSigno.size() == 1) {
                        renderDenomina = !lstSigTipoSigno.get(0).getTipoSigno().equals("DEN");
                    } else {
                        renderDenomina = true;
                    }

                    tipoSignoDescripcion = formularioService.obtenerListaSigTipoSignoExt(formularioPI102.getSignoMarca().getId());

                    for (Prioridades prioridadExt : formularioPI102.getPrioridades()) {
                        switch (prioridadExt.getTipoInteres()) {
                            case "EXT":
                                this.prioridadExtranjeraSipi = prioridadExt;
                                //Obtener el dominio a partir del nombre
                                if (this.prioridadExtranjeraSipi.getTipoInteres().equals(EnumTipoInteres.EXTRANJERA.getCodigo())) {
                                    this.prioridadExtranjeraSipi.setLugar(dominioService.obtenerCodigoDominioPorNombre("pais", this.prioridadExtranjeraSipi.getLugar()));
                                }
                                break;
                            case "EXP":
                                this.prioridadExposicionSipi = prioridadExt;
                                break;
                            case "OA":
                                this.oposicionAndinaSipi = prioridadExt;
                                break;
                        }
                    }

                    //cargar la direccion interno
                    this.sigDireccionNotificacion = sigDireccionNotificacionService.obtenerDireccionNotificacionXSignoMarca(this.sigSignoMarca.getIdSignoMarca());
                    //cargar la direccion externo    
                    this.direccionNotificacionSipi = formularioPI102.getDirecciones().get(0);

                    //cargar los solicitantes interno
                    this.lstSolicitantes = sigSolicitanteApoderadoService.listadoSolicitanteXidsignomarca(this.sigSignoMarca.getIdSignoMarca());
                    //cargar los solicitantes externo
                    this.listaSolicitantesSipi = formularioPI102.getSolicitantes();

                    //cargar los apoderados interno
                    this.lstApoderados = sigSolicitanteApoderadoService.listadoApoderadoXidsignomarca(this.sigSignoMarca.getIdSignoMarca());
                    //cargar los apoderados externo
                    this.listaApoderadosSipi = formularioPI102.getApoderados();

                    //cargar las imágen interna de la marca
                    this.imagenPojo = new ImagenPojo();
                    lstImagenPojos = new ArrayList<>();
                    this.imagenPojo = imagenPojoService.obtenerImagePojoXSignoMarca(this.sigSignoMarca.getIdSignoMarca());

                    if (imagenPojo.getSigLogoTipo().getIdLogoTipo() != null) {

                        //se debe subir la imagen en el servidor
                        //enviar el imagenPojo a la vista, antes hacemos una previsualizacion
                        this.lstImagenPojos.add(imagenPojo);

                        cargarArchivoWAR();
                    }

                    sigLogoTipo = sigLogoTipoService.obtenerRegistroPrincipalSigLogoTipoXIdSignoMarca(this.sigSignoMarca.getIdSignoMarca());
                    if (sigLogoTipo.getIdLogoTipo() != null) {
                        sigImage = sigImagenService.obtenerSigImagenXIdSigLogoTipo(sigLogoTipo.getIdLogoTipo());
                    }

                    cargarArchivoWAR(formularioPI102.getLogotipos());

                }

            } else {

                //limpiar los criterios de busqueda
                inicioAdicionarFormulario();

                if (this.numeroExpediente == null
                        && this.gestionExpediente == null) {

                    /*Mensaje de Error*/
                    FacesContext.getCurrentInstance().addMessage(
                            null, new FacesMessage(FacesMessage.SEVERITY_INFO, "No existe la solicitud de marca, verificar la busqueda", "")
                    );
                } else {
                    /*Mensaje de Error*/
                    FacesContext.getCurrentInstance().addMessage(
                            null, new FacesMessage(FacesMessage.SEVERITY_INFO, "No existe la solicitud de marca: " + this.numeroExpediente + "-" + this.gestionExpediente, "")
                    );
                }

            }

        } catch (Exception ex) {

            Logger.getLogger(ExamenSignosBean.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

    }

    // @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public void cargarArchivoWAR() {

        FacesContext facesContext = FacesContext.getCurrentInstance();
        ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();
        String rutaWar = servletContext.getRealPath(EnumCarpetasTemporales.CARPETA_IMAGENES.getCodigo());

        File targetFolder = new File(rutaWar);

        if (!targetFolder.exists()) {
            targetFolder.mkdirs();
        }

        byte[] fotoByte = this.lstImagenPojos.get(0).getSigImagen().getImagen();

        imagenPojo = new ImagenPojo();

        String imagenDibuja = rutaWar + File.separator + this.lstImagenPojos.get(0).getSigLogoTipo().getNombreArchivo();

        //enviar el imagenPojo a la vista, antes hacemos una previsualizacion
        crearArchivo(fotoByte, imagenDibuja);

    }

    /**
     * *
     * Metodo que guarda el archivo en una ruta determinada
     */
    private void crearArchivo(byte[] bytes, String archivo) {
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(archivo);
            fos.write(bytes);

            fos.flush();
            fos.close();

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Boolean validarNumeroExpedienteGestion() {
        if (this.valorNumeroExpediente.trim().equals("")
                || this.valorNumeroExpediente == null
                || this.valorGestionExpediente.trim().equals("")
                || this.valorGestionExpediente == null) {
            return Boolean.FALSE;
        } else {
            return Boolean.TRUE;
        }
    }

    public List<SigTipoSigno> crearListaTipoSigno() {

        lstSigTipoSigno.clear();

        for (String cadena : valorTipoSignoSeleccionado) {
            SigTipoSigno sigTipoSigno = new SigTipoSigno();
            sigTipoSigno.setTipoSigno(cadena);
            sigTipoSigno.setEstado("AC");
            if (sigSignoMarca.getIdSignoMarca() != null) {
                sigTipoSigno.setIdSignoMarca(sigSignoMarca.getIdSignoMarca());
            }
            if (cadena.equals("OTRO")) {
                sigTipoSigno.setDescripcionOtro(this.valorTipoSignoOtro);
            }
            lstSigTipoSigno.add(sigTipoSigno);
        }
        return lstSigTipoSigno;
    }

    /**
     * *
     * Metodo para devolver el nombre de un solicitante
     *
     * Creado: Eddy Valero Kari Fecha: 21/09/2016
     *
     * @param nombre
     * @param primerApellido
     * @param segundoApellido
     * @return String
     * @throws java.lang.Exception
     */
    public String devuelveNombreJuridicoONatural(String nombre, String primerApellido, String segundoApellido) throws Exception {
        String campoNombreRazonSocial = " ";
        if (nombre != null && !nombre.equals("")) {
            campoNombreRazonSocial = nombre;
        }
        if (primerApellido != null && !primerApellido.equals("")) {
            campoNombreRazonSocial = campoNombreRazonSocial + " " + primerApellido;
        }
        if (segundoApellido != null && !segundoApellido.equals("")) {
            campoNombreRazonSocial = campoNombreRazonSocial + " " + segundoApellido;
        }
        return campoNombreRazonSocial;
    }

    /**
     * *
     * Metodo para devolver el tipo de documento
     *
     * Creado: Eddy Valero Kari Fecha: 27/09/2016
     *
     * @param codigo
     * @return
     * @throws java.lang.Exception
     */
    public String devuelveTipoDocumento(String codigo) throws Exception {
        try {

            if (codigo != null && !codigo.equals("")) {
                return dominioService.obtenerListadoDominioXCodigo(EnumNombreDominio.TIPO_DOCUMENTO.getCodigo(), codigo).get(0).getNombre();
            }
            return "";
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * *
     * Metodo para devolver el lugar de expedicion
     *
     * Creado: Eddy Valero Kari Fecha: 27/09/2016
     *
     * @param codigo
     * @return
     * @throws java.lang.Exception
     */
    public String devuelveLugarExpedicion(String codigo) throws Exception {
        try {
            if (codigo == null || "".equals(codigo)) {
                return "";
            } else {
                return dominioService.obtenerListadoDominioXCodigo(EnumNombreDominio.LUGAR_EXPEDICION.getCodigo(), codigo).get(0).getNombre();
            }
        } catch (Exception e) {
            return "";
        }

    }

    /**
     *
     * Metodo para devolver el departamento
     *
     * Creado: Eddy Valero Kari Fecha: 27/09/2016
     *
     * @param codigo
     * @return
     * @throws java.lang.Exception
     */
    public String devuelveDepartamento(String codigo) throws Exception {
        try {

            if (codigo != null && !codigo.equals("")) {
                return dominioService.obtenerListadoDominioXCodigo(EnumNombreDominio.LUGAR_EXPEDICION.getCodigo(), codigo).get(0).getNombre();
            }
            return "";
        } catch (Exception e) {
            return "";
        }
    }

    /**
     *
     * Metodo para devolver el genero
     *
     * Creado: Eddy Valero Kari Fecha: 27/09/2016
     *
     * @param codigo
     * @return
     * @throws java.lang.Exception
     */
    public String devuelveGenero(String codigo) throws Exception {
        try {

            if (codigo != null && !codigo.equals("")) {
                return dominioService.obtenerListadoDominioXCodigo(EnumNombreDominio.GENERO_MAS_FEM.getCodigo(), codigo).get(0).getNombre();
            }
            return "";
        } catch (Exception e) {
            return "";
        }
    }

    /**
     *
     * Metodo para devolver el pais
     *
     * Creado: Eddy Valero Kari Fecha: 27/09/2016
     *
     * @param codigo
     * @return
     * @throws java.lang.Exception
     */
    public String devuelvePais(String codigo) throws Exception {
        try {
            if (codigo != null && !codigo.equals("")) {
                return dominioService.obtenerListadoDominioXCodigo(EnumNombreDominio.PAIS.getCodigo(), codigo).get(0).getNombre();
            }
            return "";

        } catch (Exception e) {
            return "";
        }
    }

    /**
     *
     * Metodo para actualizar un registro de expediente
     *
     * Creado: Placido Castro Fecha: 12/10/2017
     *
     */
    public void subsanarExpedienteSignoMarca() {

        try {

            // prepara la lista de Tipo Signo
            crearListaTipoSigno();

            // realizar la subsanacion del registro
            if (formularioPI100.getFormularios() != null) {
                expedienteService.actualizarSubsanacionSignoMarcaPI100(sigSignoMarca, lstImagenPojos, lstSigTipoSigno, sigDireccionNotificacion, idUsuario, formularioPI100);
            } else if (formularioPI101.getFormularios() != null) {
                expedienteService.actualizarSubsanacionSignoMarcaPI101(sigSignoMarca, lstImagenPojos, lstSigTipoSigno, sigDireccionNotificacion, idUsuario, formularioPI101);
            } else if (formularioPI102.getFormularios() != null) {
                expedienteService.actualizarSubsanacionSignoMarcaPI102(sigSignoMarca, lstImagenPojos, lstSigTipoSigno, sigDireccionNotificacion, idUsuario, formularioPI102);
            }
            //tipoSignoDescripcion = sigTipoSignoService.lista_SigTipoSigno_concatenado(EnumPrefijoTablas.SIGNO.getCodigo(), this.sigSignoMarca.getIdSignoMarca());

            FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Formulario subsanado correctamente", ""));
        } catch (Exception ex) {
            Logger.getLogger(ExamenSignosBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Metodo que se actualiza cada vez que se seleccione u item de tipo de
     * signo Creado: Eddy Valero Fecha: 26/09/2016
     */
    public void muestraOtro() {
        for (String s : valorTipoSignoSeleccionado) {
            if (s.equals("OTRO")) {
                txtOtroRendered = true;
                break;
            } else {
                txtOtroRendered = false;
            }

        }

        if (valorTipoSignoSeleccionado.length == 1) {
            if (valorTipoSignoSeleccionado[0].equals("DEN")) {
                renderDenomina = false;

            }
        } else {
            renderDenomina = true;
        }

    }

    public void activaCamposNaturalJuridico() {

        panelSolicitanteRendered = !tipoSolicitante.equals("N");
    }

    public void limpiarExamenSigno() {
        sigSignoMarca = new SigSignoMarca();
        lstImagenPojos = new ArrayList<ImagenPojo>();
        formularioPI100 = new FormularioPI100();
        formularioPI101 = new FormularioPI101();
        formularioPI102 = new FormularioPI102();
        lstSigSignoClaseNiza = new ArrayList<SigSignoClaseNiza>();
        listaSigSignoClaseNizaSipi = new ArrayList<SmSignoClaseNizas>();
        lstSolicitantes = new ArrayList<SigSolicitanteApoderado>();
        listaSolicitantesSipi = new ArrayList<>();
        listaApoderadosSipi  = new ArrayList<>();
        prioridadExtranjeraSipi = new Prioridades();
        prioridadExposicionSipi = new Prioridades();
        oposicionAndinaSipi = new Prioridades();
        lstApoderados = new ArrayList<SigSolicitanteApoderado>();
        lstSigTipoSigno = new ArrayList<SigTipoSigno>();
        sigDireccionNotificacion = new SigDireccionDeNotificacion();
        direccionNotificacionSipi = new DireccionNotificaciones();
        oposicionAndina = new SigPrioridadPreferencia();
        prioridadExtranjera = new SigPrioridadPreferencia();
        prioridadExposicion = new SigPrioridadPreferencia();
        tipoSolicitante = "";
        panelSolicitanteRendered = true;
        nombreBotonOpo = "Oposición";
        backgroundOPO = "#c7cccd";
        botonOposicionDisabled = true;
        sigLogoTipo = new SigLogoTipo();
        botonRecepcionarDisabled = true;
        botonFinalizarDisabled = true;
        btnAdicionarDisabled = Boolean.TRUE;
        btnModificarDisabled = Boolean.TRUE;
        sigSolicitanteApoderadoSelect = new SigSolicitanteApoderado();
        sigSignoClaseNizaSelect = new SigSignoClaseNiza();
        imagenPojo = new ImagenPojo();
        lstImagenPojos = new ArrayList<ImagenPojo>();
        observacionTramite = new ObservacionTramite();
        valorFechaDerivacion = new Date();
        valorRadioOperacionFormulario = "CON";
        valorRadioBuscador = "soma";

        regional = "LPZ";
        valorNumeroRecibo = null;
        valorSerieRecibo = "";
        valorNumeroExpediente = "";
        valorGestionExpediente = "";
        valorExtensionExpediente = "";
        valorMarca = "";
        valorNumeroTitulo = null;
        valorTipoGenero = "";
        valorTipoGenero = "";
        txtOtroRendered = false;
        valorFechaIngreso = null;
        valorFechaDerivacion = null;
        valorEstadoMarca = "";
        valorUbicacion = "";
        numeroExpediente = "";
        gestionExpediente = "";
        extensionExpediente = "";
        tipoSignoDescripcion = "Elegir";
        valorTipoSignoSeleccionado = new String[10];
        valorDescripcionSigno = "";
        valorNumeroPublicacion = null;
        valorNumeroGaceta = null;
        valorFechaPublicacion = null;
        valorNumeroRegistro = null;
        valorSerieRegistro = "";
        valorResolucionRegistro = "";
        valorFechaRegistro = null;
        valorNumeroRenovacion = null;
        valorResolucionRenovacion = null;
        valorFechaRenovacion = null;
    }

    //<editor-fold defaultstate="collapsed" desc="Definicion de servicios y atributos... verificar estos datos">
    public String getTipoSolicitante() {
        return tipoSolicitante;
    }

    public void setTipoSolicitante(String tipoSolicitante) {
        this.tipoSolicitante = tipoSolicitante;
    }

    public Boolean getPanelSolicitanteRendered() {
        return panelSolicitanteRendered;
    }

    public void setPanelSolicitanteRendered(Boolean panelSolicitanteRendered) {
        this.panelSolicitanteRendered = panelSolicitanteRendered;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getLugarExpedicion() {
        return lugarExpedicion;
    }

    public void setLugarExpedicion(String lugarExpedicion) {
        this.lugarExpedicion = lugarExpedicion;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getGeneroPersona() {
        return generoPersona;
    }

    public void setGeneroPersona(String generoPersona) {
        this.generoPersona = generoPersona;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public List<Dominio> getLstTipoSigno() {
        return lstTipoSigno;
    }

    public void setLstTipoSigno(List<Dominio> lstTipoSigno) {
        this.lstTipoSigno = lstTipoSigno;
    }

    public List<Regional> getLstRegional() {
        return lstRegional;
    }

    public void setLstRegional(List<Regional> lstRegional) {
        this.lstRegional = lstRegional;
    }

    public String getValorTipoGenero() {
        return valorTipoGenero;
    }

    public void setValorTipoGenero(String valorTipoGenero) {
        this.valorTipoGenero = valorTipoGenero;
    }

    public String getValorSerieTitulo() {
        return valorSerieTitulo;
    }

    public void setValorSerieTitulo(String valorSerieTitulo) {
        this.valorSerieTitulo = valorSerieTitulo;
    }

    public Date getValorFechaIngreso() {
        return valorFechaIngreso;
    }

    public void setValorFechaIngreso(Date valorFechaIngreso) {
        this.valorFechaIngreso = valorFechaIngreso;
    }

    public List<Dominio> getLstUbicacion() {
        return lstUbicacion;
    }

    public void setLstUbicacion(List<Dominio> lstUbicacion) {
        this.lstUbicacion = lstUbicacion;
    }

    public List<Dominio> getLstSerieRegistro() {
        return lstSerieRegistro;
    }

    public void setLstSerieRegistro(List<Dominio> lstSerieRegistro) {
        this.lstSerieRegistro = lstSerieRegistro;
    }

    public List<Dominio> getLstSituacionActual() {
        return lstSituacionActual;
    }

    public void setLstSituacionActual(List<Dominio> lstSituacionActual) {
        this.lstSituacionActual = lstSituacionActual;
    }

    public DominioService getsMDominioService() {
        return sMDominioService;
    }

    public void setsMDominioService(DominioService sMDominioService) {
        this.sMDominioService = sMDominioService;
    }

    public RegionalService getRegionalService() {
        return regionalService;
    }

    public void setRegionalService(RegionalService regionalService) {
        this.regionalService = regionalService;
    }

    public String getSmNum() {
        return smNum;
    }

    public void setSmNum(String smNum) {
        this.smNum = smNum;
    }

    public String getSmGestion() {
        return smGestion;
    }

    public void setSmGestion(String smGestion) {
        this.smGestion = smGestion;
    }

    public String getSmExt() {
        return smExt;
    }

    public void setSmExt(String smExt) {
        this.smExt = smExt;
    }

    public NotificacionService getNotificacionService() {
        return notificacionService;
    }

    public void setNotificacionService(NotificacionService notificacionService) {
        this.notificacionService = notificacionService;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Definicion de GET's SET's">
    public String getRegional() {
        return regional;
    }

    public void setRegional(String regional) {
        this.regional = regional;
    }

    public String getValorMarca() {
        return valorMarca;
    }

    public void setValorMarca(String valorMarca) {
        this.valorMarca = valorMarca;
    }

    public String getValorDescripcionSigno() {
        return valorDescripcionSigno;
    }

    public void setValorDescripcionSigno(String valorDescripcionSigno) {
        this.valorDescripcionSigno = valorDescripcionSigno;
    }

    public Long getValorNumeroTitulo() {
        return valorNumeroTitulo;
    }

    public void setValorNumeroTitulo(Long valorNumeroTitulo) {
        this.valorNumeroTitulo = valorNumeroTitulo;
    }

    public String getNumeroFormulario() {
        return numeroFormulario;
    }

    public void setNumeroFormulario(String numeroFormulario) {
        this.numeroFormulario = numeroFormulario;
    }

    public List<Dominio> getLstOficina() {
        return lstOficina;
    }

    public void setLstOficina(List<Dominio> lstOficina) {
        this.lstOficina = lstOficina;
    }

    public List<Dominio> getLstTipoGenero() {
        return lstTipoGenero;
    }

    public void setLstTipoGenero(List<Dominio> lstTipoGenero) {
        this.lstTipoGenero = lstTipoGenero;
    }

    public List<Dominio> getLstSerieTitulo() {
        return lstSerieTitulo;
    }

    public void setLstSerieTitulo(List<Dominio> lstSerieTitulo) {
        this.lstSerieTitulo = lstSerieTitulo;
    }

    public String getValorRadioOperacionFormulario() {
        return valorRadioOperacionFormulario;
    }

    public void setValorRadioOperacionFormulario(String valorRadioOperacionFormulario) {
        this.valorRadioOperacionFormulario = valorRadioOperacionFormulario;
    }

    public String getValorRadioBuscador() {
        return valorRadioBuscador;
    }

    public void setValorRadioBuscador(String valorRadioBuscador) {
        this.valorRadioBuscador = valorRadioBuscador;
    }

    public Boolean getBuscarSMRendered() {
        return buscarSMRendered;
    }

    public void setBuscarSMRendered(Boolean buscarSMRendered) {
        this.buscarSMRendered = buscarSMRendered;
    }

    public Boolean getBuscarPURendered() {
        return buscarPURendered;
    }

    public void setBuscarPURendered(Boolean buscarPURendered) {
        this.buscarPURendered = buscarPURendered;
    }

    public Boolean getBuscarRERendered() {
        return buscarRERendered;
    }

    public void setBuscarRERendered(Boolean buscarRERendered) {
        this.buscarRERendered = buscarRERendered;
    }

    public String getNumeroExpediente() {
        return numeroExpediente;
    }

    public void setNumeroExpediente(String numeroExpediente) {
        this.numeroExpediente = numeroExpediente;
    }

    public String getGestionExpediente() {
        return gestionExpediente;
    }

    public void setGestionExpediente(String gestionExpediente) {
        this.gestionExpediente = gestionExpediente;
    }

    public String getExtensionExpediente() {
        return extensionExpediente;
    }

    public void setExtensionExpediente(String extensionExpediente) {
        this.extensionExpediente = extensionExpediente;
    }

    public String getValorNumeroExpediente() {
        return valorNumeroExpediente;
    }

    public void setValorNumeroExpediente(String valorNumeroExpediente) {
        this.valorNumeroExpediente = valorNumeroExpediente;
    }

    public String getValorGestionExpediente() {
        return valorGestionExpediente;
    }

    public void setValorGestionExpediente(String valorGestionExpediente) {
        this.valorGestionExpediente = valorGestionExpediente;
    }

    public String getValorExtensionExpediente() {
        return valorExtensionExpediente;
    }

    public void setValorExtensionExpediente(String valorExtensionExpediente) {
        this.valorExtensionExpediente = valorExtensionExpediente;
    }

    public Boolean getValorOpcionRecibo() {
        return valorOpcionRecibo;
    }

    public void setValorOpcionRecibo(Boolean valorOpcionRecibo) {
        this.valorOpcionRecibo = valorOpcionRecibo;
    }

    public Long getValorNumeroRecibo() {
        return valorNumeroRecibo;
    }

    public void setValorNumeroRecibo(Long valorNumeroRecibo) {
        this.valorNumeroRecibo = valorNumeroRecibo;
    }

    public String getValorSerieRecibo() {
        return valorSerieRecibo;
    }

    public void setValorSerieRecibo(String valorSerieRecibo) {
        this.valorSerieRecibo = valorSerieRecibo;
    }

    public Date getValorFechaDerivacion() {
        return valorFechaDerivacion;
    }

    public void setValorFechaDerivacion(Date valorFechaDerivacion) {
        this.valorFechaDerivacion = valorFechaDerivacion;
    }

    public String getValorDescripcionDisenio() {
        return valorDescripcionDisenio;
    }

    public void setValorDescripcionDisenio(String valorDescripcionDisenio) {
        this.valorDescripcionDisenio = valorDescripcionDisenio;
    }

    public String getValorEstadoMarca() {
        return valorEstadoMarca;
    }

    public void setValorEstadoMarca(String valorEstadoMarca) {
        this.valorEstadoMarca = valorEstadoMarca;
    }

    public String getValorUbicacion() {
        return valorUbicacion;
    }

    public void setValorUbicacion(String valorUbicacion) {
        this.valorUbicacion = valorUbicacion;
    }

    public String[] getValorTipoSignoSeleccionado() {
        return valorTipoSignoSeleccionado;
    }

    public void setValorTipoSignoSeleccionado(String[] valorTipoSignoSeleccionado) {
        this.valorTipoSignoSeleccionado = valorTipoSignoSeleccionado;
    }

    public String getValorTipoSignoOtro() {
        return valorTipoSignoOtro;
    }

    public void setValorTipoSignoOtro(String valorTipoSignoOtro) {
        this.valorTipoSignoOtro = valorTipoSignoOtro;
    }

    public Long getValorNumeroPublicacion() {
        return valorNumeroPublicacion;
    }

    public void setValorNumeroPublicacion(Long valorNumeroPublicacion) {
        this.valorNumeroPublicacion = valorNumeroPublicacion;
    }

    public Long getValorNumeroGaceta() {
        return valorNumeroGaceta;
    }

    public void setValorNumeroGaceta(Long valorNumeroGaceta) {
        this.valorNumeroGaceta = valorNumeroGaceta;
    }

    public Date getValorFechaPublicacion() {
        return valorFechaPublicacion;
    }

    public void setValorFechaPublicacion(Date valorFechaPublicacion) {
        this.valorFechaPublicacion = valorFechaPublicacion;
    }

    public Long getValorNumeroRegistro() {
        return valorNumeroRegistro;
    }

    public void setValorNumeroRegistro(Long valorNumeroRegistro) {
        this.valorNumeroRegistro = valorNumeroRegistro;
    }

    public String getValorSerieRegistro() {
        return valorSerieRegistro;
    }

    public void setValorSerieRegistro(String valorSerieRegistro) {
        this.valorSerieRegistro = valorSerieRegistro;
    }

    public String getValorResolucionRegistro() {
        return valorResolucionRegistro;
    }

    public void setValorResolucionRegistro(String valorResolucionRegistro) {
        this.valorResolucionRegistro = valorResolucionRegistro;
    }

    public Date getValorFechaRegistro() {
        return valorFechaRegistro;
    }

    public void setValorFechaRegistro(Date valorFechaRegistro) {
        this.valorFechaRegistro = valorFechaRegistro;
    }

    public Integer getValorNumeroRenovacion() {
        return valorNumeroRenovacion;
    }

    public void setValorNumeroRenovacion(Integer valorNumeroRenovacion) {
        this.valorNumeroRenovacion = valorNumeroRenovacion;
    }

    public String getValorExtensionRenovacion() {
        return valorExtensionRenovacion;
    }

    public void setValorExtensionRenovacion(String valorExtensionRenovacion) {
        this.valorExtensionRenovacion = valorExtensionRenovacion;
    }

    public Long getValorResolucionRenovacion() {
        return valorResolucionRenovacion;
    }

    public void setValorResolucionRenovacion(Long valorResolucionRenovacion) {
        this.valorResolucionRenovacion = valorResolucionRenovacion;
    }

    public Date getValorFechaRenovacion() {
        return valorFechaRenovacion;
    }

    public void setValorFechaRenovacion(Date valorFechaRenovacion) {
        this.valorFechaRenovacion = valorFechaRenovacion;
    }

    public Boolean getTxtOtroRendered() {
        return txtOtroRendered;
    }

    public void setTxtOtroRendered(Boolean txtOtroRendered) {
        this.txtOtroRendered = txtOtroRendered;
    }

    public Boolean getRenderDenomina() {
        return renderDenomina;
    }

    public void setRenderDenomina(Boolean renderDenomina) {
        this.renderDenomina = renderDenomina;
    }

    public SigPrioridadPreferencia getPrioridadExtranjera() {
        return prioridadExtranjera;
    }

    public void setPrioridadExtranjera(SigPrioridadPreferencia prioridadExtranjera) {
        this.prioridadExtranjera = prioridadExtranjera;
    }

    public SigPrioridadPreferencia getPrioridadExposicion() {
        return prioridadExposicion;
    }

    public void setPrioridadExposicion(SigPrioridadPreferencia prioridadExposicion) {
        this.prioridadExposicion = prioridadExposicion;
    }

    public SigPrioridadPreferencia getOposicionAndina() {
        return oposicionAndina;
    }

    public void setOposicionAndina(SigPrioridadPreferencia oposicionAndina) {
        this.oposicionAndina = oposicionAndina;
    }

    public SigDireccionDeNotificacion getSigDireccionNotificacion() {
        return sigDireccionNotificacion;
    }

    public void setSigDireccionNotificacion(SigDireccionDeNotificacion sigDireccionNotificacion) {
        this.sigDireccionNotificacion = sigDireccionNotificacion;
    }

    public Integer getIndice() {
        return indice;
    }

    public void setIndice(Integer indice) {
        this.indice = indice;
    }

    public SigSolicitanteApoderadoService getSigSolicitanteApoderadoService() {
        return sigSolicitanteApoderadoService;
    }

    public void setSigSolicitanteApoderadoService(SigSolicitanteApoderadoService sigSolicitanteApoderadoService) {
        this.sigSolicitanteApoderadoService = sigSolicitanteApoderadoService;
    }

    public ObservacionTramiteService getObservacionTramiteService() {
        return observacionTramiteService;
    }

    public void setObservacionTramiteService(ObservacionTramiteService observacionTramiteService) {
        this.observacionTramiteService = observacionTramiteService;
    }

    public SigLogoTipoService getSigLogoTipoService() {
        return sigLogoTipoService;
    }

    public void setSigLogoTipoService(SigLogoTipoService sigLogoTipoService) {
        this.sigLogoTipoService = sigLogoTipoService;
    }

    public SigImagenService getSigImagenService() {
        return sigImagenService;
    }

    public void setSigImagenService(SigImagenService sigImagenService) {
        this.sigImagenService = sigImagenService;
    }

    public SigSignoMarcaService getSigSignoMarcaService() {
        return sigSignoMarcaService;
    }

    public void setSigSignoMarcaService(SigSignoMarcaService sigSignoMarcaService) {
        this.sigSignoMarcaService = sigSignoMarcaService;
    }

    public Boolean getBtnAdicionarDisabled() {
        return btnAdicionarDisabled;
    }

    public void setBtnAdicionarDisabled(Boolean btnAdicionarDisabled) {
        this.btnAdicionarDisabled = btnAdicionarDisabled;
    }

    public Boolean getBtnModificarDisabled() {
        return btnModificarDisabled;
    }

    public void setBtnModificarDisabled(Boolean btnModificarDisabled) {
        this.btnModificarDisabled = btnModificarDisabled;
    }

    public SigSolicitanteApoderado getSigSolicitanteApoderadoSelect() {
        return sigSolicitanteApoderadoSelect;
    }

    public void setSigSolicitanteApoderadoSelect(SigSolicitanteApoderado sigSolicitanteApoderadoSelect) {
        this.sigSolicitanteApoderadoSelect = sigSolicitanteApoderadoSelect;
    }

    public SigSignoClaseNiza getSigSignoClaseNizaSelect() {
        return sigSignoClaseNizaSelect;
    }

    public void setSigSignoClaseNizaSelect(SigSignoClaseNiza sigSignoClaseNizaSelect) {
        this.sigSignoClaseNizaSelect = sigSignoClaseNizaSelect;
    }

    public ImagenPojo getImagenPojo() {
        return imagenPojo;
    }

    public void setImagenPojo(ImagenPojo imagenPojo) {
        this.imagenPojo = imagenPojo;
    }

    public List<ImagenPojo> getLstImagenPojos() {
        return lstImagenPojos;
    }

    public void setLstImagenPojos(List<ImagenPojo> lstImagenPojos) {
        this.lstImagenPojos = lstImagenPojos;
    }

    public List<SigSolicitanteApoderado> getLstSolicitantes() {
        return lstSolicitantes;
    }

    public void setLstSolicitantes(List<SigSolicitanteApoderado> lstSolicitantes) {
        this.lstSolicitantes = lstSolicitantes;
    }

    public List<Solicitantes> getListaSolicitantesSipi() {
        return listaSolicitantesSipi;
    }

    public void setListaSolicitantesSipi(List<Solicitantes> listaSolicitantesSipi) {
        this.listaSolicitantesSipi = listaSolicitantesSipi;
    }

    public List<Apoderados> getListaApoderadosSipi() {
        return listaApoderadosSipi;
    }

    public void setListaApoderadosSipi(List<Apoderados> listaApoderadosSipi) {
        this.listaApoderadosSipi = listaApoderadosSipi;
    }

    public List<SigSolicitanteApoderado> getLstApoderados() {
        return lstApoderados;
    }

    public void setLstApoderados(List<SigSolicitanteApoderado> lstApoderados) {
        this.lstApoderados = lstApoderados;
    }

    public ObservacionTramite getObservacionTramite() {
        return observacionTramite;
    }

    public void setObservacionTramite(ObservacionTramite observacionTramite) {
        this.observacionTramite = observacionTramite;
    }

    public SigSignoMarca getSigSignoMarca() {
        return sigSignoMarca;
    }

    public void setSigSignoMarca(SigSignoMarca sigSignoMarca) {
        this.sigSignoMarca = sigSignoMarca;
    }

    public DominioService getDominioService() {
        return dominioService;
    }

    public void setDominioService(DominioService dominioService) {
        this.dominioService = dominioService;
    }

    public ExpedienteService getExpedienteService() {
        return expedienteService;
    }

    public void setExpedienteService(ExpedienteService expedienteService) {
        this.expedienteService = expedienteService;
    }

    public ComunService getComunService() {
        return comunService;
    }

    public void setComunService(ComunService comunService) {
        this.comunService = comunService;
    }

    public SigSignoClaseNizaService getSigSignoClaseNizaService() {
        return sigSignoClaseNizaService;
    }

    public void setSigSignoClaseNizaService(SigSignoClaseNizaService sigSignoClaseNizaService) {
        this.sigSignoClaseNizaService = sigSignoClaseNizaService;
    }

    public List<SigTipoSigno> getLstSigTipoSigno() {
        return lstSigTipoSigno;
    }

    public void setLstSigTipoSigno(List<SigTipoSigno> lstSigTipoSigno) {
        this.lstSigTipoSigno = lstSigTipoSigno;
    }

    public List<Dominio> getLstPaises() {
        return lstPaises;
    }

    public void setLstPaises(List<Dominio> lstPaises) {
        this.lstPaises = lstPaises;
    }

    public List<SigSignoClaseNiza> getLstSigSignoClaseNiza() {
        return lstSigSignoClaseNiza;
    }

    public void setLstSigSignoClaseNiza(List<SigSignoClaseNiza> lstSigSignoClaseNiza) {
        this.lstSigSignoClaseNiza = lstSigSignoClaseNiza;
    }

    public SigTipoSignoService getSigTipoSignoService() {
        return sigTipoSignoService;
    }

    public void setSigTipoSignoService(SigTipoSignoService sigTipoSignoService) {
        this.sigTipoSignoService = sigTipoSignoService;
    }

    public SigPrioridadPreferenciaService getSigPrioridadPreferenciaService() {
        return sigPrioridadPreferenciaService;
    }

    public void setSigPrioridadPreferenciaService(SigPrioridadPreferenciaService sigPrioridadPreferenciaService) {
        this.sigPrioridadPreferenciaService = sigPrioridadPreferenciaService;
    }

    public SigDireccionDeNotificacionService getSigDireccionNotificacionService() {
        return sigDireccionNotificacionService;
    }

    public void setSigDireccionNotificacionService(SigDireccionDeNotificacionService sigDireccionNotificacionService) {
        this.sigDireccionNotificacionService = sigDireccionNotificacionService;
    }

    public Boolean getBotonRecepcionarDisabled() {
        return botonRecepcionarDisabled;
    }

    public void setBotonRecepcionarDisabled(Boolean botonRecepcionarDisabled) {
        this.botonRecepcionarDisabled = botonRecepcionarDisabled;
    }

    public Boolean getBotonFinalizarDisabled() {
        return botonFinalizarDisabled;
    }

    public void setBotonFinalizarDisabled(Boolean botonFinalizarDisabled) {
        this.botonFinalizarDisabled = botonFinalizarDisabled;
    }

    public SeguimientoService getSeguimientoService() {
        return seguimientoService;
    }

    public void setSeguimientoService(SeguimientoService seguimientoService) {
        this.seguimientoService = seguimientoService;
    }

    public ImagenPojoService getImagenPojoService() {
        return imagenPojoService;
    }

    public void setImagenPojoService(ImagenPojoService imagenPojoService) {
        this.imagenPojoService = imagenPojoService;
    }

    public Seguimiento getUltimoSeguimiento() {
        return ultimoSeguimiento;
    }

    public void setUltimoSeguimiento(Seguimiento ultimoSeguimiento) {
        this.ultimoSeguimiento = ultimoSeguimiento;
    }

    public List<Dominio> getLstTipoGeneroXdominio() {
        return lstTipoGeneroXdominio;
    }

    public void setLstTipoGeneroXdominio(List<Dominio> lstTipoGeneroXdominio) {
        this.lstTipoGeneroXdominio = lstTipoGeneroXdominio;
    }

    public SigLogoTipo getSigLogoTipo() {
        return sigLogoTipo;
    }

    public void setSigLogoTipo(SigLogoTipo sigLogoTipo) {
        this.sigLogoTipo = sigLogoTipo;
    }

    public SigImagen getSigImage() {
        return sigImage;
    }

    public void setSigImage(SigImagen sigImage) {
        this.sigImage = sigImage;
    }

    public ModModificacionService getModModificacionService() {
        return modModificacionService;
    }

    public void setModModificacionService(ModModificacionService modModificacionService) {
        this.modModificacionService = modModificacionService;
    }

    public Boolean isValidarFormulario() {
        return validarFormulario;
    }

    public void setValidarFormulario(Boolean validarFormulario) {
        this.validarFormulario = validarFormulario;
    }

    public Boolean isValidarSigSignoMarca() {
        return validarSigSignoMarca;
    }

    public void setValidarSigSignoMarca(Boolean validarSigSignoMarca) {
        this.validarSigSignoMarca = validarSigSignoMarca;
    }

    public String getMensajeValidacionSigSignoMarca() {
        return mensajeValidacionSigSignoMarca;
    }

    public void setMensajeValidacionSigSignoMarca(String mensajeValidacionSigSignoMarca) {
        this.mensajeValidacionSigSignoMarca = mensajeValidacionSigSignoMarca;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreBotonOpo() {
        return nombreBotonOpo;
    }

    public void setNombreBotonOpo(String nombreBotonOpo) {
        this.nombreBotonOpo = nombreBotonOpo;
    }

    public String getBackgroundOPO() {
        return backgroundOPO;
    }

    public void setBackgroundOPO(String backgroundOPO) {
        this.backgroundOPO = backgroundOPO;
    }

    public Boolean getBotonOposicionDisabled() {
        return botonOposicionDisabled;
    }

    public void setBotonOposicionDisabled(Boolean botonOposicionDisabled) {
        this.botonOposicionDisabled = botonOposicionDisabled;
    }

    public OposicionService getOposicionService() {
        return oposicionService;
    }

    public void setOposicionService(OposicionService oposicionService) {
        this.oposicionService = oposicionService;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public LogTransService getLogTransService() {
        return logTransService;
    }

    public void setLogTransService(LogTransService logTransService) {
        this.logTransService = logTransService;
    }

    public UsuarioService getUsuarioService() {
        return usuarioService;
    }

    public DireccionNotificaciones getDireccionNotificacionSipi() {
        return direccionNotificacionSipi;
    }

    public void setDireccionNotificacionSipi(DireccionNotificaciones direccionNotificacionSipi) {
        this.direccionNotificacionSipi = direccionNotificacionSipi;
    }

    public void setUsuarioService(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    public EtapaService getEtapaService() {
        return etapaService;
    }

    public void setEtapaService(EtapaService etapaService) {
        this.etapaService = etapaService;
    }

    public Boolean getBtnSeguimientoRendered() {
        return btnSeguimientoRendered;
    }

    public void setBtnSeguimientoRendered(Boolean btnSeguimientoRendered) {
        this.btnSeguimientoRendered = btnSeguimientoRendered;
    }

    public String getTipoSignoDescripcion() {
        return tipoSignoDescripcion;
    }

    public void setTipoSignoDescripcion(String tipoSignoDescripcion) {
        this.tipoSignoDescripcion = tipoSignoDescripcion;
    }

    public UsuarioPaginaService getUsuarioPaginaService() {
        return usuarioPaginaService;
    }

    public void setUsuarioPaginaService(UsuarioPaginaService usuarioPaginaService) {
        this.usuarioPaginaService = usuarioPaginaService;
    }

    public List<Dominio> getLstTipoDocumento() {
        return lstTipoDocumento;
    }

    public void setLstTipoDocumento(List<Dominio> lstTipoDocumento) {
        this.lstTipoDocumento = lstTipoDocumento;
    }

    public Image getImagenes() {
        return imagenes;
    }

    public void setImagenes(Image imagenes) {
        this.imagenes = imagenes;
    }

    public Long getLogtrans() {
        return logtrans;
    }

    public void setLogtrans(Long logtrans) {
        this.logtrans = logtrans;
    }

    public List<Etapa> getListEtapaUs() {
        return listEtapaUs;
    }

    public void setListEtapaUs(List<Etapa> listEtapaUs) {
        this.listEtapaUs = listEtapaUs;
    }

    public String getValorEstadoMarcaaux() {
        return valorEstadoMarcaaux;
    }

    public void setValorEstadoMarcaaux(String valorEstadoMarcaaux) {
        this.valorEstadoMarcaaux = valorEstadoMarcaaux;
    }

    public Seguimiento getUltimoSeguimientoopo() {
        return ultimoSeguimientoopo;
    }

    public void setUltimoSeguimientoopo(Seguimiento ultimoSeguimientoopo) {
        this.ultimoSeguimientoopo = ultimoSeguimientoopo;
    }

    public List<SigSignoClaseNiza> getLstSigSignoClaseNizaRepo() {
        return lstSigSignoClaseNizaRepo;
    }

    public void setLstSigSignoClaseNizaRepo(List<SigSignoClaseNiza> lstSigSignoClaseNizaRepo) {
        this.lstSigSignoClaseNizaRepo = lstSigSignoClaseNizaRepo;
    }

    public List<SmSignoClaseNizas> getListaSigSignoClaseNizaSipi() {
        return listaSigSignoClaseNizaSipi;
    }

    public void setListaSigSignoClaseNizaSipi(List<SmSignoClaseNizas> listaSigSignoClaseNizaSipi) {
        this.listaSigSignoClaseNizaSipi = listaSigSignoClaseNizaSipi;
    }

    public SigSignoMarca getSigSignoMarcaRepo() {
        return sigSignoMarcaRepo;
    }

    public void setSigSignoMarcaRepo(SigSignoMarca sigSignoMarcaRepo) {
        this.sigSignoMarcaRepo = sigSignoMarcaRepo;
    }

    public FormularioPI100 getFormularioPI100() {
        return formularioPI100;
    }

    public void setFormularioPI100(FormularioPI100 formularioPI100) {
        this.formularioPI100 = formularioPI100;
    }

    public FormularioPI100Service getFormularioPI100Service() {
        return formularioPI100Service;
    }

    public void setFormularioPI100Service(FormularioPI100Service formularioPI100Service) {
        this.formularioPI100Service = formularioPI100Service;
    }

    public FormularioPI101Service getFormularioPI101Service() {
        return formularioPI101Service;
    }

    public void setFormularioPI101Service(FormularioPI101Service formularioPI101Service) {
        this.formularioPI101Service = formularioPI101Service;
    }

    public FormularioPI102Service getFormularioPI102Service() {
        return formularioPI102Service;
    }

    public void setFormularioPI102Service(FormularioPI102Service formularioPI102Service) {
        this.formularioPI102Service = formularioPI102Service;
    }

    public FormularioService getFormularioService() {
        return formularioService;
    }

    public void setFormularioService(FormularioService formularioService) {
        this.formularioService = formularioService;
    }

    public Prioridades getPrioridadExtranjeraSipi() {
        return prioridadExtranjeraSipi;
    }

    public void setPrioridadExtranjeraSipi(Prioridades prioridadExtranjeraSipi) {
        this.prioridadExtranjeraSipi = prioridadExtranjeraSipi;
    }

    public Prioridades getPrioridadExposicionSipi() {
        return prioridadExposicionSipi;
    }

    public void setPrioridadExposicionSipi(Prioridades prioridadExposicionSipi) {
        this.prioridadExposicionSipi = prioridadExposicionSipi;
    }

    public Prioridades getOposicionAndinaSipi() {
        return oposicionAndinaSipi;
    }

    public void setOposicionAndinaSipi(Prioridades oposicionAndinaSipi) {
        this.oposicionAndinaSipi = oposicionAndinaSipi;
    }

    public Boolean getPanelNoEncontrado() {
        return panelNoEncontrado;
    }

    public void setPanelNoEncontrado(Boolean panelNoEncontrado) {
        this.panelNoEncontrado = panelNoEncontrado;
    }

    public String getMensajeBusqueda() {
        return mensajeBusqueda;
    }

    public void setMensajeBusqueda(String mensajeBusqueda) {
        this.mensajeBusqueda = mensajeBusqueda;
    }
//</editor-fold>    
}
