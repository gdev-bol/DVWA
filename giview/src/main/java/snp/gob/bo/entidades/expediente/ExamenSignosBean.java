package snp.gob.bo.entidades.expediente;

import com.sun.faces.context.FacesFileNotFoundException;
import java.awt.Image;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Timestamp;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
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
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.ImageIcon;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import snp.gob.bo.entidades.bean.common.AbstractManagedBean;
import snp.gob.bo.entidades.observacion.ObservacionTramiteBean;
import snp.gob.bo.gimodel.bdimagen.entidad.ImagenPojo;
import snp.gob.bo.gimodel.bdimagen.entidad.SigImagen;
import snp.gob.bo.gimodel.bdimagen.entidad.SigLogoTipo;
import snp.gob.bo.gimodel.bdimagen.servicio.ImagenPojoService;
import snp.gob.bo.gimodel.bdimagen.servicio.SigImagenService;
import snp.gob.bo.gimodel.bdimagen.servicio.SigLogoTipoService;
import snp.gob.bo.gimodel.entidad.BusquedaHistorialRenovacion;
import snp.gob.bo.gimodel.entidad.ClaseNiza;
import snp.gob.bo.gimodel.entidad.Dominio;
import snp.gob.bo.gimodel.entidad.Etapa;
import snp.gob.bo.gimodel.entidad.LogTrans;
import snp.gob.bo.gimodel.entidad.Notificacion;
import snp.gob.bo.gimodel.entidad.ObservacionTramite;
import snp.gob.bo.gimodel.entidad.Regional;
import snp.gob.bo.gimodel.entidad.RenSolicitudRenovacion;
import snp.gob.bo.gimodel.entidad.Seguimiento;
import snp.gob.bo.gimodel.entidad.SigDireccionDeNotificacion;
import snp.gob.bo.gimodel.entidad.SigPrioridadPreferencia;
import snp.gob.bo.gimodel.entidad.SigSignoClaseNiza;
import snp.gob.bo.gimodel.entidad.SigSignoMarca;
import snp.gob.bo.gimodel.entidad.SigSolicitanteApoderado;
import snp.gob.bo.gimodel.entidad.SigTipoSigno;
import snp.gob.bo.gimodel.entidad.Usuario;
import snp.gob.bo.gimodel.enums.EnumCarpetasTemporales;
import snp.gob.bo.gimodel.enums.EnumEstado;
import snp.gob.bo.gimodel.enums.EnumNombreDominio;
import snp.gob.bo.gimodel.enums.EnumOperacion;
import snp.gob.bo.gimodel.enums.EnumPrefijoTablas;
import snp.gob.bo.gimodel.enums.EnumTipoModificacion;
import snp.gob.bo.gimodel.pojo.HistorialRenovacionModificacionPojo;
import snp.gob.bo.gimodel.servicio.BusquedaHistorialRenovacionService;
import snp.gob.bo.gimodel.servicio.ClaseNizaService;
import snp.gob.bo.gimodel.servicio.ComunService;
import snp.gob.bo.gimodel.servicio.DominioService;
import snp.gob.bo.gimodel.servicio.EtapaService;
import snp.gob.bo.gimodel.servicio.ExpedienteService;
import snp.gob.bo.gimodel.servicio.FlujoSeguimientoService;
import snp.gob.bo.gimodel.servicio.FlujoTiempoService;
import snp.gob.bo.gimodel.servicio.HistorialService;
import snp.gob.bo.gimodel.servicio.LogTransService;
import snp.gob.bo.gimodel.servicio.ModModificacionService;
import snp.gob.bo.gimodel.servicio.NotificacionService;
import snp.gob.bo.gimodel.servicio.ObservacionTramiteService;
import snp.gob.bo.gimodel.servicio.OposicionService;
import snp.gob.bo.gimodel.servicio.RegionalService;
import snp.gob.bo.gimodel.servicio.RenRenovacionService;
import snp.gob.bo.gimodel.servicio.RenSolicitudRenovacionService;
import snp.gob.bo.gimodel.servicio.SeguimientoService;
import snp.gob.bo.gimodel.servicio.SigDireccionDeNotificacionService;
import snp.gob.bo.gimodel.servicio.SigPrioridadPreferenciaService;
import snp.gob.bo.gimodel.servicio.SigSignoClaseNizaService;
import snp.gob.bo.gimodel.servicio.SigSignoMarcaService;
import snp.gob.bo.gimodel.servicio.SigSolicitanteApoderadoService;
import snp.gob.bo.gimodel.servicio.SigTipoSignoService;
import snp.gob.bo.gimodel.servicio.UsuarioPaginaService;
import snp.gob.bo.gimodel.servicio.UsuarioService;

/**
 *
 * @author eddy
 */
@ManagedBean(name = "examenSignosBean")
@ViewScoped
public class ExamenSignosBean extends AbstractManagedBean implements Serializable {

    //<editor-fold defaultstate="collapsed" desc="Definicion de servicios y atributos... verificar estos datos">
//    private List<persona> lstPersonas;
    private Date fechaSolicitud = new Date();
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

//    private String publNum;
//    private String regNum;
//    private String regExt;
    private String template = "./../WEB-INF/facelets/templates/Template.xhtml";
    @ManagedProperty("#{dominioService}")
    private DominioService sMDominioService;

    private List<Regional> lstRegional = new ArrayList<Regional>();

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Definicion de servicios y atributos">
    private SigSignoMarca sigSignoMarca;
    private String valorMarca;
    private String valorDescripcionSigno;
    private Long valorNumeroTitulo;
    private String numeroFormulario;
    private String regional = "";
    private String valorTipoGenero = "";
    private String valorSerieTitulo = "";
    private Date valorFechaIngreso = new Date();
    private List<Dominio> lstOficina = new ArrayList<Dominio>();
    private List<Dominio> lstTipoGenero = new ArrayList<Dominio>();
    private List<Dominio> lstSerieTitulo = new ArrayList<Dominio>();
    private List<Dominio> lstSituacionActual = new ArrayList<Dominio>();
    private List<Dominio> lstUbicacion = new ArrayList<Dominio>();
    private List<Dominio> lstSerieRegistro = new ArrayList<Dominio>();
    private List<Dominio> lstTipoSigno = new ArrayList<Dominio>();
    private List<Dominio> lstTipoDocumento = new ArrayList<Dominio>();
    private List<SigSignoClaseNiza> lstSigSignoClaseNiza = new ArrayList<SigSignoClaseNiza>();
    private List<SigTipoSigno> lstSigTipoSigno = new ArrayList<SigTipoSigno>();
    private List<Dominio> lstPaises = new ArrayList<Dominio>();
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
    //valor direccion
    private SigDireccionDeNotificacion sigDireccionNotificacion = new SigDireccionDeNotificacion();
    private Integer indice = 0;
    //valores lista de solicitantes
    private List<SigSolicitanteApoderado> lstSolicitantes = new ArrayList<SigSolicitanteApoderado>();
    //valores lista de apoderados
    private List<SigSolicitanteApoderado> lstApoderados = new ArrayList<SigSolicitanteApoderado>();
    //valor observaciones
    private ObservacionTramite observacionTramite = new ObservacionTramite();
    //imagenes sigsignomarca
//    private List<String> images;
//    private List<SigLogoTipo> lstSigLogoTipo = new ArrayList<SigLogoTipo>();
//    private List<SigImagen> lstSigImagen = new ArrayList<SigImagen>();
    //disabled botones agregar y modificar formulario solicitud
    private Boolean btnAdicionarDisabled = Boolean.TRUE;
    private Boolean btnModificarDisabled = Boolean.TRUE;
    private Boolean btnSubsanacion = Boolean.FALSE;
    //persona o apoderado seleccionado
    private SigSolicitanteApoderado sigSolicitanteApoderadoSelect = new SigSolicitanteApoderado();
    //claseniza seleccionado
    private SigSignoClaseNiza sigSignoClaseNizaSelect = new SigSignoClaseNiza();
    //Imagen
    private ImagenPojo imagenPojo = new ImagenPojo();
    private List<ImagenPojo> lstImagenPojos = new ArrayList<ImagenPojo>();
    private Boolean botonRecepcionarDisabled = true;
    private Boolean botonFinalizarDisabled = true;
    private Seguimiento ultimoSeguimiento;
    private Seguimiento ultimoSeguimientoopo;

    //parametros del reporte
    private Map<String, Object> parametros = new HashMap<String, Object>();
    private String fechaHoy = "";
    private String horaHoy = "";
    private String marcaX = "";
    private String sm = "";
    private String fechasm = "";
    private String horasm = "";
    private String clase = "";
    private String tipoMarca = "";
    private String tipoSigno = "";
    private String descripSigno = "";
    private String descripLogotipo = "";
    private String productos = "";
    private String imgSenapi = "";
    private String tipoPrioridad = "";
    private String prioridad = "";
    private String lugarPrioridad = "";
    private String fechaPrioridad = "";
    private String andina;
    private String situacionActual = "";
    private String observacion = "";
    private Long noPublicacion;
    private Long noGaceta;
    private String fechaPub = "";
    private String noRegistro = "";
    private String noResolucion = "";
    private String fechaRegistro = "";
    private Integer noRenovacion;
    private Long resoRenovacion;
    private String fechaResoRenovacion;
    private List<SigSignoClaseNiza> lstSigSignoClaseNizaRepo = new ArrayList<>();
    private SigSignoMarca sigSignoMarcaRepo;

    private List<Dominio> lstTipoGeneroXdominio = new ArrayList<Dominio>();
    private SigLogoTipo sigLogoTipo = new SigLogoTipo();
    private SigImagen sigImage;
    private Image imagenes = null;
    private Boolean botonCambioNombreDisabled = true;
    private Boolean botonCambioDomicilioDisabled = true;
    private Boolean botonTransferenciaDisabled = true;
    private Boolean botonFusionDisabled = true;
    private Boolean botonLicenciaDisabled = true;
    private String backgroundCN = "#c7cccd";
    private String backgroundCD = "#c7cccd";
    private String backgroundST = "#c7cccd";
    private String backgroundSF = "#c7cccd";
    private String backgroundLU = "#c7cccd";
    private List<HistorialRenovacionModificacionPojo> lstHistorialRenovacionModificacionPojo = new ArrayList<HistorialRenovacionModificacionPojo>();
    private List<BusquedaHistorialRenovacion> lstHistorialRenovacionModificacion = new ArrayList<BusquedaHistorialRenovacion>();
    //Bloque validaciones de formularios
    private Boolean validarFormulario = Boolean.FALSE;
    private Boolean validarSigSignoMarca = Boolean.FALSE;
    private String mensajeValidacionSigSignoMarca = "";

    //Variables para el boton de oposicion
    private String nombreBotonOpo = "Oposición";
    private String backgroundOPO = "#c7cccd";
    private Boolean botonOposicionDisabled = true;

    //usuario
    private Long idUsuario = null;

    private Long logtrans;
    private Long idSignoMarca;
    private Boolean esRevocatoria = false;
    private Boolean[] RecepcionarFinalizar = new Boolean[4];
    private Boolean seguimientoAutomatico = false;
    private List<Etapa> listEtapaUs;
    private Boolean btnSeguimientoRecepcionarRendered = false;
    private Boolean btnSeguimientoFinalizarRendered = false;
    private String tipoSignoDescripcion = "Elegir";

    private List<RenSolicitudRenovacion> lstRenSolicititudRenovacionNoConc = new ArrayList<RenSolicitudRenovacion>();

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

    @ManagedProperty(value = "#{flujoTiempoService}")
    private FlujoTiempoService flujoTiempoService;

    @ManagedProperty(value = "#{flujoSeguimientoService}")
    private FlujoSeguimientoService flujoSeguimientoService;

    @ManagedProperty(value = "#{historialService}")
    private HistorialService historialService;

    @ManagedProperty(value = "#{busquedaHistorialRenovacionService}")
    private BusquedaHistorialRenovacionService busquedaHistorialRenovacionService;

    @ManagedProperty(value = "#{renRenovacionService}")
    private RenRenovacionService renRenovacionService;

    @ManagedProperty(value = "#{renSolicitudRenovacionService}")
    private RenSolicitudRenovacionService renSolicitudRenovacionService;

    @ManagedProperty(value = "#{claseNizaService}")
    private ClaseNizaService claseNizaService;

    private boolean pintaAdiciona = false;
    private boolean pintaModifica = false;

    //</editor-fold>
//    @ManagedProperty("#{comunService}")
//    private ComunService comunService;
    @PostConstruct
    public void init() {

        try {
            idUsuario = super.getIdUsuarioSession();

            obtenerParametrosBusquedaExpediente();

            inicializarVista();
            Date fechaSistema = comunService.obtenerFechaHoraServidor(1L);

            List<Usuario> listUsuario = usuarioService.listaUsuarioXidPagina(super.getIdUsuarioSession());

            LogTrans logTransGuardado = logTransService.crudLogTrans(new LogTrans(idUsuario, fechaSistema), 1);
            logtrans = logTransGuardado.getIdLogTrans();

            listEtapaUs = etapaService.listadoPorIdUsuario(super.getIdUsuarioSession(), super.getIdEtapaSession());

            RecepcionarFinalizar = flujoSeguimientoService.configuracionBotonesRecepcionarFinalizar(super.getIdEtapaSession(), EnumPrefijoTablas.SIGNO.getCodigo());

            btnSeguimientoRecepcionarRendered = RecepcionarFinalizar[0];
            btnSeguimientoFinalizarRendered = RecepcionarFinalizar[1];

//            if (super.getIdEtapaSession() == 3 || super.getIdEtapaSession() == 7 || super.getIdEtapaSession() == 16) {
//                btnSeguimientoRendered = true;
//            }

            /*Para que  pueda agarr  y vea cual pinta cual no*/
            pintaAdiciona = usuarioPaginaService.estadoBotonUsuario(super.getIdUsuarioSession().toString(), "Examen Signo Adiciona");
            pintaModifica = usuarioPaginaService.estadoBotonUsuario(super.getIdUsuarioSession().toString(), "Examen Signo Modifica");
            // listEtapaUs = etapaService.listadoPorIdUsuario(super.getIdUsuarioSession());
            obtenerParametrosBusquedas();

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
            //Obtener el usuario con el que se realiza la operación

            //inicializar el objeto principal
            sigSignoMarca = new SigSignoMarca();

            //Obtener la fecha de derivacion
//            this.valorFechaDerivacion = comunService.obtenerFechaHoraServidor(1L);
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
//            this.imagenPojo.getSigImagen()

            //inicializar formulario si tiene mas de un registro
            if (getIdSignoSession() != null) {

                //obtener sigsignomarca.
                sigSignoMarca = sigSignoMarcaService.obtenerSigSignoMarcaXidSignoMarca(getIdSignoSession());

                //cargar datos de expediente.
                cargarDatosExpediente();
            }

            if (getNavegaPagina() != null && !getNavegaPagina().equals("")) {
                template = "./../WEB-INF/facelets/templates/DialogTemplate.xhtml";
            }

            eliminarVariablesSession();
        } catch (Exception ex) {
            Logger.getLogger(ExamenSignosBean.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

    }

    public String devuelveVersion(SigSignoClaseNiza sigSignoClaseNiza) {
        String version = "";
        try {
            ClaseNiza claseNiza = expedienteService.obtenerClaseNiza(sigSignoClaseNiza.getIdClaseNiza());
            if (claseNiza != null) {
                //version = claseNiza.getVersion();
                version = claseNiza.getNumeroEdicion();
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

    public void cambiaOpcionesFormulario() {
        //inicializaTodo();
        switch (valorRadioOperacionFormulario) {
            case "ADD":
                btnAdicionarDisabled = Boolean.FALSE;
                btnModificarDisabled = Boolean.TRUE;
//                inicioAdicionarFormulario();
                break;
            case "MOD":
                btnAdicionarDisabled = Boolean.TRUE;
                btnModificarDisabled = Boolean.FALSE;
//                inicioAdicionarFormulario();

                break;
            case "CON":
                btnAdicionarDisabled = Boolean.TRUE;
                btnModificarDisabled = Boolean.TRUE;
                break;
            default:
                break;
        }
    }

    /*
     * Metodo para realizar la busqueda de acuerdo a los siguientes criterios:
     * SolicitudMarca: soma
     * Nro Publicacion: nupu
     * Nro Registro: nure
     * tambien cuando se ingresa a esta opción deben limpiarse las variables
     * Creado: Eddy Valero Fecha: 20/09/2016
     */
    public void modificarCriterioBusqueda() {
        this.numeroExpediente = "";
        this.gestionExpediente = "";
        this.extensionExpediente = "";

        switch (valorRadioBuscador) {
            case "soma":
                buscarSMRendered = true;
                buscarPURendered = false;
                buscarRERendered = false;
                break;
            case "nupu":
                buscarSMRendered = false;
                buscarPURendered = true;
                buscarRERendered = false;
                break;
            case "nure":
                buscarSMRendered = false;
                buscarPURendered = false;
                buscarRERendered = true;
                this.extensionExpediente = "C";
                break;
            default:
                buscarSMRendered = false;
                buscarPURendered = false;
                buscarRERendered = false;
                break;
        }

    }

    /**
     * *
     * Metodo para realizar la busqueda de un expediente
     *
     * Creado: Eddy Valero Kari Fecha: 21/09/2016
     */
    public void accionBuscarExpediente() {
        //quitar los espacios de la cadena a preguntar
        numeroExpediente = numeroExpediente.trim();
        gestionExpediente = gestionExpediente.trim();
        extensionExpediente = extensionExpediente.trim();
        botonCambioNombreDisabled = true;
        botonCambioDomicilioDisabled = true;
        botonTransferenciaDisabled = true;
        botonFusionDisabled = true;
        botonLicenciaDisabled = true;
        backgroundCN = "#c7cccd";
        backgroundCD = "#c7cccd";
        backgroundST = "#c7cccd";
        backgroundSF = "#c7cccd";
        backgroundLU = "#c7cccd";
        //buscar por sm
        if (buscarSMRendered) {
            try {
                //Obtener el signo marca determinado, y cargar variables
                if (extensionExpediente.equals("00")) {
                    extensionExpediente = "";
                }

                if (validarNumeroGestionExtension()) {
                    sigSignoMarca = expedienteService.obtenerSigsignomarca(numeroExpediente, gestionExpediente, extensionExpediente);
                    if (sigSignoMarca.getIdSignoMarca() != null) {
                        cargarDatosExpediente();
                    } else {
                        FacesContext.getCurrentInstance().addMessage("registrationForm:userName",
                                new FacesMessage(FacesMessage.SEVERITY_WARN, "No se encontraron datos para la busqueda", ""));
                    }
                }

            } catch (Exception ex) {
                Logger.getLogger(ExamenSignosBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        } //buscar por publicacion
        else if (buscarPURendered) {
            try {
                if (validarPublicacion()) {
                    sigSignoMarca = sigSignoMarcaService.listaSigSignoMarcaXPublicacion(Long.valueOf(this.numeroExpediente));
                    if (sigSignoMarca != null) {
                        if (sigSignoMarca.getIdSignoMarca() != null) {
                            cargarDatosExpediente();
                        } else {
                            FacesContext.getCurrentInstance().addMessage("registrationForm:userName",
                                    new FacesMessage(FacesMessage.SEVERITY_WARN, "No se encontraron datos para la busqueda", ""));
                        }
                    } else {
                        FacesContext.getCurrentInstance().addMessage("registrationForm:userName",
                                new FacesMessage(FacesMessage.SEVERITY_WARN, "No se encontraron datos para la busqueda", ""));

                    }
                }

            } catch (Exception ex) {
                Logger.getLogger(ExamenSignosBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        } //buscar por registro
        else if (buscarRERendered) {
            if (validarRegistro()) {
                sigSignoMarca = sigSignoMarcaService.listaSigSignoMarcaXRegistro(Long.valueOf(this.numeroExpediente), this.extensionExpediente, "");
                if (sigSignoMarca != null) {
                    cargarDatosExpediente();
                } else {
                    FacesContext.getCurrentInstance().addMessage("registrationForm:userName",
                            new FacesMessage(FacesMessage.SEVERITY_WARN, "No se encontraron datos para la busqueda", ""));
                }
            }
        }

        this.valorEstadoMarcaaux = this.valorEstadoMarca;
        mostrarRecepcionarFinalizar(listEtapaUs.get(0).getTipoEtapa());
    }

    /**
     * *
     * Metodo para realizar la busqueda de un expediente para oposicion ya que
     * el anterior metodo corta la iteraccion y este metodo continua con la
     * etapa
     *
     * Creado: Eddy Valero Kari Fecha: 21/09/2016
     */
    public void accionBuscarExpedienteopo() {

        //quitar los espacios de la cadena a preguntar
        numeroExpediente = numeroExpediente.trim();
        gestionExpediente = gestionExpediente.trim();
        extensionExpediente = extensionExpediente.trim();

        botonCambioNombreDisabled = true;
        botonCambioDomicilioDisabled = true;
        botonTransferenciaDisabled = true;
        botonFusionDisabled = true;
        botonLicenciaDisabled = true;
        backgroundCN = "#c7cccd";
        backgroundCD = "#c7cccd";
        backgroundST = "#c7cccd";
        backgroundSF = "#c7cccd";
        backgroundLU = "#c7cccd";
        //buscar por sm
        if (buscarSMRendered) {
            try {
                //Obtener el signo marca determinado, y cargar variables
                if (extensionExpediente.equals("00")) {
                    extensionExpediente = "";
                }
                sigSignoMarca = expedienteService.obtenerSigsignomarca(numeroExpediente, gestionExpediente, extensionExpediente);
                cargarDatosExpediente();

            } catch (Exception ex) {
                Logger.getLogger(ExamenSignosBean.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
        //buscar por publicacion
        if (buscarPURendered) {
            try {
                sigSignoMarca = sigSignoMarcaService.listaSigSignoMarcaXPublicacion(Long.valueOf(this.numeroExpediente));
                cargarDatosExpediente();

            } catch (Exception ex) {
                Logger.getLogger(ExamenSignosBean.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }

        //buscar por registro
        if (buscarRERendered) {
            sigSignoMarca = sigSignoMarcaService.listaSigSignoMarcaXRegistro(Long.valueOf(this.numeroExpediente), this.extensionExpediente, "");
            cargarDatosExpediente();

        }

    }

    /**
     *
     * Metodo para realizar la busqueda del anterior expediente
     *
     * Creado: Luis Angel Quispe
     *
     * Fecha: 07/12/201
     *
     * @param idTramite
     * @param idUsuarioLogin
     * @return
     */
    public String urlInformacionSM(String idTramite, String idUsuarioLogin) {
        return "this.form.target = '_blank'; window.open('../oposicion/examenoposicion.xhtml?ThrEimhaJd5=" + idTramite + "&UkYJ0g3jLwc=" + idUsuarioLogin + "');";
    }

    /**
     *
     * Metodo para obtener parametros de entrada de un linck
     *
     * Creado: Eddy Valero
     *
     * Fecha: 09/12/201
     */
    public void obtenerParametrosBusquedaExpediente() {
        try {
            valorRadioBuscador = "soma";
            buscarSMRendered = true;
            buscarPURendered = false;
            buscarRERendered = false;
            Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
            String parIdSigno = (String) (params.get("ThrEimhaJd5"));
            String parIdUsuario = (String) (params.get("UkYJ0g3jLwc"));
            if (parIdSigno != null && parIdUsuario != null) {
                if (!parIdSigno.trim().equals("") && !parIdUsuario.trim().equals("") && !parIdSigno.equals("null") && !parIdUsuario.equals("null")) {
                    setIdUsuario(Long.parseLong(parIdUsuario));
                    setIdSignoSession(Long.parseLong(parIdSigno));
                }
            }
        } catch (Exception e) {
            Logger.getLogger(ExamenSignosBean.class
                    .getName()).log(Level.SEVERE, null, e);
        }

    }

    /**
     *
     * Metodo para obtener parametros de entrada de un linck
     *
     * Creado: Luis Angel Quispe
     *
     * Fecha: 07/12/201
     *
     * @throws java.lang.Exception
     */
    public void obtenerParametrosBusquedas() throws Exception {
        Map<String, String> param12 = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String parIdPuntes = (String) (param12.get("UkYJ0g3jLwxxx1"));
        if ("PUBLI1".equals(parIdPuntes)) {

            try {
                valorRadioBuscador = "nupu";
                buscarSMRendered = false;
                buscarPURendered = true;
                buscarRERendered = false;
                Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
                String parIdTramite = (String) (params.get("ThrEimhaJd51"));
                String parIdUsuario = (String) (params.get("UkYJ0g3jLwc1"));
                if (!parIdTramite.trim().equals("") && !parIdUsuario.trim().equals("") && !parIdTramite.equals("null") && !parIdUsuario.equals("null")) {

                    setIdUsuario(Long.parseLong(parIdUsuario));
                    setNumeroExpediente(parIdTramite);
                    accionBuscarExpedienteopo();
                    setNumeroExpediente(parIdTramite);

                }
            } catch (Exception e) {
                // setIdUsuarioLoginSession(null);
                // setIdTramiteSession(null);
            }

        }
        if ("REG".equals(parIdPuntes)) {

            try {
                valorRadioBuscador = "nure";
                buscarSMRendered = false;
                buscarPURendered = false;
                buscarRERendered = true;
                Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
                String parIdTramite = (String) (params.get("ThrEimhaJdx"));
                String parReg = (String) (params.get("ThrEimhaJd1x"));
                String parIdUsuario = (String) (params.get("UkYJ0g3jLwcx"));
                if (!parIdTramite.trim().equals("") && !parIdUsuario.trim().equals("") && !parReg.trim().equals("") && !parIdTramite.equals("null") && !parReg.equals("null") && !parIdUsuario.equals("null")) {

                    setIdUsuario(Long.parseLong(parIdUsuario));
                    setNumeroExpediente(parIdTramite);
                    setExtensionExpediente(parReg);
                    accionBuscarExpedienteopo();
                    setNumeroExpediente(parIdTramite);
                    setExtensionExpediente(parReg);

                }
            } catch (Exception e) {
                // setIdUsuarioLoginSession(null);
                // setIdTramiteSession(null);
            }

        }
        if ("SM".equals(parIdPuntes)) {

            try {
                valorRadioBuscador = "soma";
                buscarSMRendered = true;
                buscarPURendered = false;
                buscarRERendered = false;
                Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
                String parIdTramite = (String) (params.get("ThrEimhaJd1x"));
                String parGes = (String) (params.get("ThrEimhaJd2x"));
                String parExt = (String) (params.get("ThrEimhaJd3x"));
                String parIdUsuario = (String) (params.get("UkYJ0g3jLwcxy"));
                if (!parIdTramite.trim().equals("") && !parIdUsuario.trim().equals("") && !parGes.trim().equals("") && !parIdTramite.equals("null") && !parGes.equals("null") && !parIdUsuario.equals("null")) {

                    setIdUsuario(Long.parseLong(parIdUsuario));
                    setNumeroExpediente(parIdTramite);
                    setGestionExpediente(parGes);
                    setExtensionExpediente(parExt);
                    accionBuscarExpedienteopo();
                    setNumeroExpediente(parIdTramite);
                    setGestionExpediente(parGes);
                    setExtensionExpediente(parExt);

                }
            } catch (Exception e) {
                // setIdUsuarioLoginSession(null);
                // setIdTramiteSession(null);
            }

        }

    }

    /**
     *
     * Metodo para realizar la busqueda del anterior expediente
     *
     * Creado: Eddy Valero Kari Fecha: 17/11/2016
     */
    public void accionBuscarAnteriorExpediente() {
        iniciarBotonesModificacion();

        if (buscarSMRendered) {
            //preguntar por la validacion del Numero Gestion y Extension
            if (validarNumeroGestionExtension()) {

                try {
                    this.sigSignoMarca = expedienteService.obtenerAnteriorRegistroSigSignoMarca(sigSignoMarca.getNumero(), sigSignoMarca.getGestion(), sigSignoMarca.getExtensionMarca());

                    //preguntar si el sigSignoMarca es diferente de null en caso de ser null, significa que no existen mas registros.
                    if (sigSignoMarca.getIdSignoMarca() != null) {
                        cargarDatosExpediente();
                    } else {
                        FacesContext.getCurrentInstance().addMessage(
                                null, new FacesMessage(FacesMessage.SEVERITY_INFO, "No existen mas registros", ""));
                    }

                } catch (Exception ex) {
                    Logger.getLogger(ExamenSignosBean.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else if (buscarPURendered) {
            //preguntar por numero publicacion
            if (validarPublicacion()) {
                try {
                    this.sigSignoMarca = expedienteService.obtenerAnteriorRegistroNumeroPublicacionSigSignoMarca(sigSignoMarca.getIdSignoMarca(), sigSignoMarca.getNumeroPublicacion());

                    //preguntar si el sigSignoMarca es diferente de null en caso de ser null, significa que no existen mas registros.
                    if (sigSignoMarca.getIdSignoMarca() != null) {
                        cargarDatosExpediente();
                    } else {
                        FacesContext.getCurrentInstance().addMessage(
                                null, new FacesMessage(FacesMessage.SEVERITY_INFO, "No existen mas registros", ""));
                    }

                } catch (Exception ex) {
                    Logger.getLogger(ExamenSignosBean.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else if (buscarRERendered) {
            //preguntar por numero publicacion
            if (validarRegistro()) {
                try {
                    this.sigSignoMarca = expedienteService.obtenerAnteriorRegistroNumeroRegistroSigSignoMarca(sigSignoMarca.getIdSignoMarca(), sigSignoMarca.getNumeroRegistro(), this.extensionExpediente);

                    //preguntar si el sigSignoMarca es diferente de null en caso de ser null, significa que no existen mas registros.
                    if (sigSignoMarca.getIdSignoMarca() != null) {
                        cargarDatosExpediente();
                    } else {
                        FacesContext.getCurrentInstance().addMessage(
                                null, new FacesMessage(FacesMessage.SEVERITY_INFO, "No existen mas registros", ""));
                    }

                } catch (Exception ex) {
                    Logger.getLogger(ExamenSignosBean.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        mostrarRecepcionarFinalizar(listEtapaUs.get(0).getTipoEtapa());

    }

    /**
     *
     * Metodo para realizar la busqueda del siguiente expediente
     *
     * Creado: Eddy Valero Kari Fecha: 17/11/2016
     */
    public void accionBuscarSiguienteExpediente() {
        iniciarBotonesModificacion();

        if (buscarSMRendered) {
            if (validarNumeroGestionExtension()) {
                try {
//            this.sigSignoMarca = expedienteService.obtenerSiguienteRegistroSigSignoMarca(sigSignoMarca.getIdSignoMarca());
                    this.sigSignoMarca = expedienteService.obtenerSiguienteRegistroSigSignoMarca(sigSignoMarca.getNumero(), sigSignoMarca.getGestion(), sigSignoMarca.getExtensionMarca());

                    //preguntar si el sigSignoMarca es diferente de null en caso de ser null, significa que no existen mas registros.
                    if (sigSignoMarca.getIdSignoMarca() != null) {
                        cargarDatosExpediente();
                    } else {
                        FacesContext.getCurrentInstance().addMessage(
                                null, new FacesMessage(FacesMessage.SEVERITY_INFO, "No existen mas registros", ""));
                    }

                } catch (Exception ex) {
                    Logger.getLogger(ExamenSignosBean.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else if (buscarPURendered) {
            //preguntar por numero publicacion
            if (validarPublicacion()) {
                try {
                    this.sigSignoMarca = expedienteService.obtenerSiguienteRegistroNumeroPublicacionSigSignoMarca(sigSignoMarca.getIdSignoMarca(), sigSignoMarca.getNumeroPublicacion());

                    //preguntar si el sigSignoMarca es diferente de null en caso de ser null, significa que no existen mas registros.
                    if (sigSignoMarca.getIdSignoMarca() != null) {
                        cargarDatosExpediente();
                    } else {
                        FacesContext.getCurrentInstance().addMessage(
                                null, new FacesMessage(FacesMessage.SEVERITY_INFO, "No existen mas registros", ""));
                    }
                } catch (Exception ex) {
                    Logger.getLogger(ExamenSignosBean.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else if (buscarRERendered) {
            //preguntar por numero publicacion
            if (validarRegistro()) {
                try {
                    this.sigSignoMarca = expedienteService.obtenerSiguienteRegistroNumeroRegistroSigSignoMarca(sigSignoMarca.getIdSignoMarca(), sigSignoMarca.getNumeroRegistro(), this.extensionExpediente);

                    //preguntar si el sigSignoMarca es diferente de null en caso de ser null, significa que no existen mas registros.
                    if (sigSignoMarca.getIdSignoMarca() != null) {
                        cargarDatosExpediente();
                    } else {
                        FacesContext.getCurrentInstance().addMessage(
                                null, new FacesMessage(FacesMessage.SEVERITY_INFO, "No existen mas registros", ""));
                    }

                } catch (Exception ex) {
                    Logger.getLogger(ExamenSignosBean.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        mostrarRecepcionarFinalizar(listEtapaUs.get(0).getTipoEtapa());
    }

    public Boolean validarNumeroGestionExtension() {
        if (this.numeroExpediente.equals("")
                || this.gestionExpediente.equals("")) {

            FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Verifique los valores para el número, gestión y extensión de la Marca", "")
            );
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    public Boolean validarPublicacion() {
        if (this.numeroExpediente.equals("")) {

            FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Verifique el valor para numero de publicación", "")
            );
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    public Boolean validarRegistro() {
        if (this.numeroExpediente.equals("") || this.extensionExpediente.equals("")) {

            FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Verifique el valor para numero de registro y/o extension", "")
            );
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    public void cargarDatosExpediente() {
        try {
            sigImage = new SigImagen();
            if (sigSignoMarca != null) {

                this.idSignoMarca = sigSignoMarca.getIdSignoMarca();
                regional = sigSignoMarca.getOficina();
                this.valorMarca = sigSignoMarca.getSigno();
                this.valorDescripcionSigno = sigSignoMarca.getDescripcionSigno();
                this.valorNumeroTitulo = sigSignoMarca.getNumeroTitulo();
                this.valorSerieTitulo = sigSignoMarca.getSerieTitulo();
                this.numeroFormulario = sigSignoMarca.getNumeroFormulario();
                this.valorTipoGenero = sigSignoMarca.getTipoGenero();
                this.valorNumeroRecibo = sigSignoMarca.getNumeroRecibo();
                this.valorSerieRecibo = sigSignoMarca.getSerie();
                this.valorFechaIngreso = sigSignoMarca.getFechaIngreso();
                this.valorDescripcionDisenio = sigSignoMarca.getDescripcionLogotipo();
                this.valorEstadoMarca = sigSignoMarca.getEstadoMarca();
                this.valorUbicacion = sigSignoMarca.getUbicacion();
                this.valorNumeroPublicacion = sigSignoMarca.getNumeroPublicacion();
                this.valorNumeroGaceta = sigSignoMarca.getNumeroGaceta();
                this.valorFechaPublicacion = sigSignoMarca.getFechaPublicacion();
                this.valorNumeroRegistro = sigSignoMarca.getNumeroRegistro();
                this.valorSerieRegistro = sigSignoMarca.getSerieRegistro();
                this.valorResolucionRegistro = sigSignoMarca.getResolucionRegistro();
                this.valorFechaRegistro = sigSignoMarca.getFechaRegistro();
                this.valorNumeroRenovacion = sigSignoMarca.getNumeroRenovacion();
                this.valorExtensionRenovacion = sigSignoMarca.getExtensionRenovacion();
                this.valorResolucionRenovacion = sigSignoMarca.getNumeroResolucionRenovacion();
                this.valorFechaRenovacion = sigSignoMarca.getFechaRenovacion();

                //Obtener la decodificacion del signo.
                HashMap mapResultado = comunService.obtenerNumeroGestionExtensionCodigoSM(sigSignoMarca.getSm());

                if (buscarSMRendered) {
                    this.numeroExpediente = mapResultado.get("numero").toString();
                    this.gestionExpediente = mapResultado.get("gestion").toString();
                    this.extensionExpediente = mapResultado.get("extension").toString();
                } else if (buscarPURendered) {
                    this.numeroExpediente = this.sigSignoMarca.getNumeroPublicacion().toString();
                    this.gestionExpediente = "";
                    this.extensionExpediente = "";
                } else if (buscarRERendered) {
                    this.numeroExpediente = this.sigSignoMarca.getNumeroRegistro().toString();
                    this.gestionExpediente = "";
                    this.extensionExpediente = this.sigSignoMarca.getSerieRegistro();
                }

                this.valorNumeroExpediente = mapResultado.get("numero").toString();
                this.valorGestionExpediente = mapResultado.get("gestion").toString();
                this.valorExtensionExpediente = mapResultado.get("extension").toString();

                //Obtener el listado de claseniza
                this.lstSigSignoClaseNiza = sigSignoClaseNizaService.obtenerListaSigSignoClazeNizaXIdSignoMarca(sigSignoMarca.getIdSignoMarca());
                this.valorTipoSignoOtro = "";
                this.lstSigTipoSigno = sigTipoSignoService.listaSigTipoSignoXidSIgnoMarca(sigSignoMarca.getIdSignoMarca());
                //armar la lista de tipoGeneroSeleccionado
                this.valorTipoSignoSeleccionado = new String[10];
                int i = 0;
                this.txtOtroRendered = Boolean.FALSE;
                for (SigTipoSigno item : lstSigTipoSigno) {
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

                tipoSignoDescripcion = sigTipoSignoService.lista_SigTipoSigno_concatenado(EnumPrefijoTablas.SIGNO.getCodigo(), this.sigSignoMarca.getIdSignoMarca());
                //Cargar las prioridades y preferencias
                this.prioridadExtranjera = sigPrioridadPreferenciaService.obtenerPrioridadExtranjera(this.sigSignoMarca.getIdSignoMarca());

                this.prioridadExposicion = sigPrioridadPreferenciaService.obtenerPrioridadExposicion(this.sigSignoMarca.getIdSignoMarca());
                this.oposicionAndina = sigPrioridadPreferenciaService.obtenerOposicionAndina(this.sigSignoMarca.getIdSignoMarca());
                if (this.oposicionAndina.getIdSignoMarca() != null) {
                    setBackgroundOPO("#82b548");
                } else {
                    setBackgroundOPO("#c7cccd");
                }

                //cargar la direccion
                this.sigDireccionNotificacion = sigDireccionNotificacionService.obtenerDireccionNotificacionXSignoMarca(this.sigSignoMarca.getIdSignoMarca());
                //cargar los solicitantes
                this.lstSolicitantes = sigSolicitanteApoderadoService.listadoSolicitanteXidsignomarca(this.sigSignoMarca.getIdSignoMarca());
                this.lstApoderados = sigSolicitanteApoderadoService.listadoApoderadoXidsignomarca(this.sigSignoMarca.getIdSignoMarca());
                //cargar la ultima observacion
                this.observacionTramite = observacionTramiteService.obtenerUltimaObservacionTramite(EnumPrefijoTablas.SIGNO.getCodigo(), this.sigSignoMarca.getIdSignoMarca());

                //cargar las imagenes de la marca
                this.imagenPojo = new ImagenPojo();
                lstImagenPojos = new ArrayList<ImagenPojo>();
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
                botonesHistorialModificacion(sigSignoMarca);
                //cargar publicacion
//                this.valorNumeroPublicacion = sigSignoMarca.getNumeroPublicacion();
//                this.valorNumeroGaceta = sigSignoMarca.getNumeroGaceta();
//                this.valorFechaPublicacion = sigSignoMarca.getFechaPublicacion();

                //Cargar el boton de oposicion
                botonOposicion(valorNumeroPublicacion);

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

    public String abrirSubsanacion(String idSignoMarca, String idUsuarioLogin) {
        return "this.form.target = '_blank'; window.open('../signo/expedienteSubsanacion.xhtml?ThrEimhaJd5=" + idSignoMarca + "&UkYJ0g3jLwc=" + idUsuarioLogin + "');";
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
     *
     *
     *
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

    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public void accionGuardarRegistroSigno() {
        try {

            //solo ingresar a registrar suponiendo que el SignoMarca no existe
            if (this.sigSignoMarca.getIdSignoMarca() == null) {
                //buscar en la base si ya existe un sigsignomarca, se debe validar que el numeroexpediente,
                //valorGestionExpediente, valorExtensionExpediente tenga valores

                //preguntar si es valido valorNumeroExpediente, valorGestionExpediente, valorExtensionExpediente
                if (validarNumeroExpedienteGestion()) {
                    sigSignoMarca = expedienteService.obtenerSigsignomarca(valorNumeroExpediente.trim(), valorGestionExpediente.trim(), valorExtensionExpediente.trim());

                    /**
                     *
                     * Para realizar el almacenamiento se procede en el
                     * siguiente orden guardar SigSignoMarca guardar solicitante
                     * guardar apoderado guardar direccion guardar oposicion
                     * andina guardar prioridad extranjera guardar prioridad
                     * exposicion guardar imagen guardar clase niza
                     */
                    if (sigSignoMarca.getIdSignoMarca() == null) {
                        //como es nulo se procede a su registro
                        //ingresar registro de sigsignoMarca, el orden de acuerdo a la tabla

                        //se debe realizar validaciones antes de ingresar
                        if (validaFormularioIngreso()) {
                            //generar el Log

                            sigSignoMarca.setNumeroFormulario(null);
                            sigSignoMarca.setSm(comunService.codificarCodigoSM(valorNumeroExpediente.trim(), valorGestionExpediente.trim(), valorExtensionExpediente.trim()));
                            sigSignoMarca.setSigno(valorMarca);
                            sigSignoMarca.setTipoGenero(valorTipoGenero);
                            sigSignoMarca.setDescripcionSigno(valorDescripcionSigno);
                            sigSignoMarca.setDescripcionLogotipo(valorDescripcionDisenio);
                            sigSignoMarca.setUbicacion(valorUbicacion);
                            sigSignoMarca.setEstadoMarca(valorEstadoMarca);
                            sigSignoMarca.setNumeroRecibo(valorNumeroRecibo);
                            sigSignoMarca.setSerie(valorSerieRecibo);
                            sigSignoMarca.setNumeroTitulo(valorNumeroTitulo);
                            sigSignoMarca.setSerieTitulo(valorSerieTitulo);
//                    sigSignoMarca.setOrigenSolicitud(tipoSolicitante);
                            sigSignoMarca.setNumeroGaceta(valorNumeroGaceta);
                            sigSignoMarca.setNumeroPublicacion(valorNumeroPublicacion);
                            sigSignoMarca.setFechaPublicacion(valorFechaPublicacion);
                            sigSignoMarca.setNumeroRegistro(valorNumeroRegistro);
                            sigSignoMarca.setSerieRegistro(valorSerieRegistro);
                            sigSignoMarca.setResolucionRegistro(valorResolucionRegistro);
                            sigSignoMarca.setFechaRegistro(valorFechaRegistro);
//                    sigSignoMarca.setOrdenRenovacion(valorNumeroRenovacion);
                            sigSignoMarca.setNumeroRenovacion(valorNumeroRenovacion);
                            sigSignoMarca.setExtensionRenovacion(valorExtensionRenovacion);
                            sigSignoMarca.setNumeroResolucionRenovacion(valorResolucionRenovacion);
                            sigSignoMarca.setFechaRenovacion(valorFechaRenovacion);
                            sigSignoMarca.setOficina(regional);
                            sigSignoMarca.setFechaSolicitud(fechaSolicitud);
                            sigSignoMarca.setFechaIngreso(valorFechaIngreso);
                            sigSignoMarca.setEstado("AC");

                            //codificar la extension
                            //Realizar la conversion del valorExtensionExpediente
                            Integer valorExpediente = null;
                            if (!valorExtensionExpediente.trim().equals("")) {
                                valorExpediente = (int) valorExtensionExpediente.charAt(0) - 55;
                            } else {
                                valorExpediente = 0;
                            }

                            sigSignoMarca.setNumero(Integer.valueOf(valorNumeroExpediente.trim()));
                            sigSignoMarca.setGestion(Integer.valueOf(valorGestionExpediente.trim()));
                            sigSignoMarca.setExtensionMarca(valorExpediente);

                            //metodo para capturar todos los tipos de signo elegidos
                            crearListaTipoSigno();

                            sigSignoMarca = expedienteService.IngresarRegistroSolicitudSignoMarca(sigSignoMarca, lstImagenPojos,
                                    lstSigSignoClaseNiza, lstSolicitantes,
                                    lstApoderados, lstSigTipoSigno, sigDireccionNotificacion,
                                    oposicionAndina, prioridadExtranjera, prioridadExposicion,
                                    idUsuario);

                            tipoSignoDescripcion = sigTipoSignoService.lista_SigTipoSigno_concatenado(EnumPrefijoTablas.SIGNO.getCodigo(), this.sigSignoMarca.getIdSignoMarca());
                            FacesContext.getCurrentInstance().addMessage(
                                    null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro adicionado correctamente", ""));

                            //una vez realizado el almacenamiento proceder a cargar los datos.
                            cargarDatosExpediente();

                            //Inserto  en bd las imagenes
                           /* if(!lstImagenPojos.isEmpty())
 
                             lstImagenPojos.get(0).getSigLogoTipo().setIdSignoMarca(sigSignoMarca.getIdSignoMarca());
                             imagenPojoService.actualizarRegistroImagenPojoXSignoMarca(lstImagenPojos.get(0));
                             }*/
                        } else {
                            FacesContext.getCurrentInstance().addMessage(
                                    null, new FacesMessage(FacesMessage.SEVERITY_INFO, this.mensajeValidacionSigSignoMarca, ""));
                        }//finalizar la validacion del formulario

                    } else {
                        //Reiniciar el objeto sigSignoMarca
                        sigSignoMarca = new SigSignoMarca();
                        FacesContext.getCurrentInstance().addMessage(
                                null, new FacesMessage(FacesMessage.SEVERITY_INFO, "La marca solicitada ya existe debe verificar la solicitud ", "")
                        );

                    }//verificar si existe o no la solicitud

                } else {

                    FacesContext.getCurrentInstance().addMessage(
                            null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Debe ingresar un numero de expediente y gestión validos... ", "")
                    );

                } // preguntar si el valorNumeroExpediente y el valorGestion son validos

            }
        } catch (Exception ex) {
            Logger.getLogger(ExamenSignosBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * *
     * Método que realiza la visualizacion del boton de publicacion si es que
     * existe en OPOSICION
     *
     * Creado:Luis Angel Quispe
     *
     * Fecha: 22/11/2016
     *
     * @param nropublicacion
     */
    public void botonOposicion(Long nropublicacion) throws Exception {
        if (oposicionService.verificarexi(nropublicacion)) {

            if (valorEstadoMarca.equals("OPO")) {
                nombreBotonOpo = "Ver Oposicion";
                backgroundOPO = "#FEB0B0";
                botonOposicionDisabled = false;
            } else {
                nombreBotonOpo = "Antecedente de Oposicion";
                backgroundOPO = "#c0c0ff";
                botonOposicionDisabled = false;
            }
        } else {
            botonOposicionDisabled = true;
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

    /**
     * *
     * Método que realiza la validación de todo el formulario
     *
     * Creado:Eddy Valero Fecha: 22/11/2016
     *
     * @return Boolean FALSE, el formulario no cumple algun requisito Boolean
     * TRUE, el formulario cumple los requisitos y puede guardarse
     */
    public Boolean validaFormularioIngreso() {

        this.validarFormulario = Boolean.FALSE;
        mensajeValidacionSigSignoMarca = "";

        //validar datos SigSignoMarca
        //preguntar por el valorMarca
        if (valorMarca.equals("")
                || valorMarca == null) {
            mensajeValidacionSigSignoMarca = "Debe ingresar el valor de la marca ";
        }

        //preguntar por Genero
        if (valorTipoGenero.equals("")) {
            mensajeValidacionSigSignoMarca = mensajeValidacionSigSignoMarca + "\n Debe seleccionar un genero de marca ";
        }

        //preguntar por Tipo Signo
        if (valorTipoSignoSeleccionado.length == 0) {
            mensajeValidacionSigSignoMarca = mensajeValidacionSigSignoMarca + "\n Debe seleccionar Tipo Signo ";
        }

        //preguntar por clase Niza
        if (lstSigSignoClaseNiza.isEmpty()) {
            mensajeValidacionSigSignoMarca = mensajeValidacionSigSignoMarca + "\n Debe agregar clase niza ";
        }

        //preguntar por solicitante o titular
        if (lstSolicitantes.isEmpty()) {
            mensajeValidacionSigSignoMarca = mensajeValidacionSigSignoMarca + "\n Debe agregar solicitante o titular ";
        }

        //preguntar por direccion de notificacion
        if (sigDireccionNotificacion.getCiudadNotificacion().length() == 0) {
            mensajeValidacionSigSignoMarca = mensajeValidacionSigSignoMarca + "\n Debe seleccionar ciudad";
        }

        if (sigDireccionNotificacion.getZonaBarrio().equals("")) {
            mensajeValidacionSigSignoMarca = mensajeValidacionSigSignoMarca + "\n Debe agregar Zona/Barrio";
        }

        if (sigDireccionNotificacion.getAvenidaCalle().equals("")) {
            mensajeValidacionSigSignoMarca = mensajeValidacionSigSignoMarca + "\n Debe agregar Av/Calle";
        }

        if (sigDireccionNotificacion.getTelefono().equals("")) {
            mensajeValidacionSigSignoMarca = mensajeValidacionSigSignoMarca + "\n Debe agregar Telefono";
        }

        // Preguntar el tipo de signo
//        for (String s : valorTipoSignoSeleccionado) {
//            if (s.equals("")) {
//                mensajeValidacionSigSignoMarca = mensajeValidacionSigSignoMarca + "\n Debe seleccionar un tipo de signo de la marca ";
//                break;
//            }
//        }
        //si el mensaje es vacio
        if (!mensajeValidacionSigSignoMarca.trim().equals("")) {
            validarSigSignoMarca = Boolean.FALSE;
        } else {
            validarSigSignoMarca = Boolean.TRUE;
        }

//        return validarFormulario;
        return validarSigSignoMarca;

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
     * Creado: Eddy Valero Kari Fecha: 07/09/2016
     *
     */
    public void accionModificarRegistroExpediente() throws Exception {

        if (validarNumeroExpedienteGestion()) {

            if (txtOtroRendered && valorTipoSignoOtro.trim().equalsIgnoreCase("")) {
                FacesContext.getCurrentInstance().addMessage(
                        null, new FacesMessage(FacesMessage.SEVERITY_INFO, "En Tipo signo, la casilla Otro no debe ser vacio.", ""));
            } else {
                try {
                    sigSignoMarca.setNumeroFormulario(null);
                    sigSignoMarca.setSm(comunService.codificarCodigoSM(valorNumeroExpediente, valorGestionExpediente, valorExtensionExpediente));
                    sigSignoMarca.setNumeroFormulario(numeroFormulario);
                    sigSignoMarca.setSigno(valorMarca);
                    sigSignoMarca.setTipoGenero(valorTipoGenero);
                    sigSignoMarca.setDescripcionSigno(valorDescripcionSigno);
                    sigSignoMarca.setDescripcionLogotipo(valorDescripcionDisenio);
                    sigSignoMarca.setUbicacion(valorUbicacion);
                    sigSignoMarca.setEstadoMarca(valorEstadoMarca);
                    sigSignoMarca.setNumeroRecibo(valorNumeroRecibo);
                    sigSignoMarca.setSerie(valorSerieRecibo);
                    sigSignoMarca.setNumeroTitulo(valorNumeroTitulo);
                    sigSignoMarca.setSerieTitulo(valorSerieTitulo);
//                    sigSignoMarca.setOrigenSolicitud(tipoSolicitante);
                    sigSignoMarca.setNumeroGaceta(valorNumeroGaceta);
                    sigSignoMarca.setNumeroPublicacion(valorNumeroPublicacion);
                    sigSignoMarca.setFechaPublicacion(valorFechaPublicacion);
                    sigSignoMarca.setNumeroRegistro(valorNumeroRegistro);
                    sigSignoMarca.setSerieRegistro(valorSerieRegistro);
                    sigSignoMarca.setResolucionRegistro(valorResolucionRegistro);
                    sigSignoMarca.setFechaRegistro(valorFechaRegistro);
//                    sigSignoMarca.setOrdenRenovacion(valorNumeroRenovacion);

                    sigSignoMarca.setNumeroRenovacion(valorNumeroRenovacion);
                    sigSignoMarca.setExtensionRenovacion(valorExtensionRenovacion);
                    sigSignoMarca.setNumeroResolucionRenovacion(valorResolucionRenovacion);
                    sigSignoMarca.setFechaRenovacion(valorFechaRenovacion);
                    sigSignoMarca.setOficina(regional);
                    sigSignoMarca.setFechaSolicitud(fechaSolicitud);
                    sigSignoMarca.setFechaIngreso(valorFechaIngreso);
                    sigSignoMarca.setEstado("AC");

                    //prepara la lista de Tipo Signo
                    crearListaTipoSigno();

                    //realizar la actualizacion del registro
//            eliminarRegistroImagenPojoXSignoMarca
                    expedienteService.actualizarRegistroSolicitudSignoMarca(sigSignoMarca, lstImagenPojos, lstSigSignoClaseNiza, lstSolicitantes,
                            lstApoderados, lstSigTipoSigno, sigDireccionNotificacion, oposicionAndina, prioridadExtranjera, prioridadExposicion, idUsuario);

                    tipoSignoDescripcion = sigTipoSignoService.lista_SigTipoSigno_concatenado(EnumPrefijoTablas.SIGNO.getCodigo(), this.sigSignoMarca.getIdSignoMarca());
                    //String miIP = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getRemoteAddr();
                    // Volver a cargar datos del expediente (signo o marca)
                    cargarDatosExpediente();

                    FacesContext.getCurrentInstance().addMessage(
                            null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Se modificaron los datos correctamente", ""));
                } catch (Exception ex) {
                    Logger.getLogger(ExamenSignosBean.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }
    }

    //<editor-fold defaultstate="collapsed" desc="Definicion de servicios y atributos">
//    public void convierteImagenYGuardaCarpeta(SigLogoTipo sigLogoTipo) {
//        byte[] bAvatar = sMImagen.getImagen();
//        ServletContext sContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
//        File folder = new File(sContext.getRealPath("/temp"));
//        if (!folder.exists()) {
//            folder.mkdirs();
//        }
//        String arquivo = sContext.getRealPath("/temp") + File.separator + sMImagen.getNombre_archivo();
//        crearArchivo(sMImagen.getImagen(), arquivo);
//    }
//
//    private void crearArchivo(byte[] bytes, String arquivo) {
//        FileOutputStream fos;
//        try {
//            fos = new FileOutputStream(arquivo);
//            fos.write(bytes);
//
//            fos.flush();
//            fos.close();
//
//        } catch (FileNotFoundException ex) {
//            ex.printStackTrace();
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
    //</editor-fold>
    /**
     * Metodo para abrir dialogo de publicaciones
     *
     * Creado: Eddy Valero Fecha:24/09/2016
     *
     */
    public void actionMostrarHistorialPublicacion() {

    }

    /**
     * Metodo para abrir dialogo de renovaciones Creado: Eddy Valero Fecha:
     * 24/09/2016
     */
    public void actionMostrarHistorialRenovacion() {

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

    /**
     * Metodo para habilitar el dialogo para el cargado de solicitantes
     *
     * Creado: Eddy Valero Fecha: 03/10/2016
     */
    public void abrirDialogoSolicitantes() {
        Map<String, Object> opcionesDialogo = new HashMap<String, Object>();
//        opcionesDialogo.put("resizable", false);
//        opcionesDialogo.put("draggable", true);
//        opcionesDialogo.put("modal", true);
//        opcionesDialogo.put("contentHeight", 420);
//        opcionesDialogo.put("contentWidth", 770);
//        opcionesDialogo.put("closable", false);        
        opcionesDialogo.put("closable", false);
        opcionesDialogo.put("resizable", false);
        opcionesDialogo.put("height", 510);
        opcionesDialogo.put("width", 780);
        opcionesDialogo.put("contentWidth", "100%");
        opcionesDialogo.put("contentHeight", "100%");
        opcionesDialogo.put("modal", true);
        opcionesDialogo.put("closable", false);
        RequestContext.getCurrentInstance().openDialog("agregarSolicitanteApoderadoSigno", opcionesDialogo, null);
    }

    public void abrirDialogoApoderado() {

        Map<String, List<String>> params = new HashMap<String, List<String>>();
        List<String> list = new ArrayList<String>();
        list.add("APOD");
        params.put("id", list);

        Map<String, Object> options = new HashMap<String, Object>();
        options.put("closable", false);
        options.put("resizable", false);
        options.put("height", 510);
        options.put("width", 780);
        options.put("contentWidth", "100%");
        options.put("contentHeight", "100%");
        options.put("modal", true);
        options.put("closable", false);
        RequestContext.getCurrentInstance().openDialog("agregarSolicitanteApoderadoSigno", options, params);
    }

    /**
     *
     * Metodo para finalizar el dialogo para el cargado de solicitantes
     *
     * Creado: Eddy Valero Fecha: 03/10/2016
     *
     * @param event
     */
    public void terminaDialogoSolicitante(SelectEvent event) {
        //capturar el objeto enviado del dialogo
        SigSolicitanteApoderado solicitanteApoderado;
        if (event.getObject() != "Exit") {
            solicitanteApoderado = (SigSolicitanteApoderado) event.getObject();
            lstSolicitantes.add(solicitanteApoderado);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Datos del Solicitante fueron agregados correctamente"));
        } else {
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cerrado"));
        }
    }

    /**
     * Metodo para finalizar el dialogo para el cargado de apoderados
     *
     * Creado: Eddy Valero Fecha: 04/10/2016
     *
     * @param event
     */
    public void terminaDialogoApoderado(SelectEvent event) {
        SigSolicitanteApoderado solicitanteApoderado;
        if (event.getObject() != "Exit") {
            solicitanteApoderado = (SigSolicitanteApoderado) event.getObject();
            lstApoderados.add(solicitanteApoderado);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Datos del Apoderado fueron agregados correctamente"));
        } else {
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cerrado"));
        }

    }

    /**
     * Metodo para finalizar el dialogo para la modificacion de apoderados
     *
     * Creado: Eddy Valero Fecha: 04/10/2016
     *
     * @param event
     */
    public void terminaDialogoModificarApoderado(SelectEvent event) {
        SigSolicitanteApoderado solicitanteApoderado;
        if (event.getObject() != "Exit") {
            solicitanteApoderado = (SigSolicitanteApoderado) event.getObject();
            lstApoderados.remove(sigSolicitanteApoderadoSelect);
            lstApoderados.add(indice, solicitanteApoderado);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Datos del Apoderado fueron Modificados correctamente"));
        } else {
            //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cerrado"));
        }
        sigSolicitanteApoderadoSelect = new SigSolicitanteApoderado();
        Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        sessionMap.remove("persona");
    }

    /**
     * Metodo para abrir el dialogo para modificar un solicitante
     *
     * Creado: Eddy Valero Fecha: 03/10/2016 Modificado: Placido Castro Fecha:
     * 25/05/2016
     *
     * @param sigSolicitanteApoderado
     */
    public void abrirDialogoModificarSolicitante(SigSolicitanteApoderado sigSolicitanteApoderado) {
        sigSolicitanteApoderadoSelect = sigSolicitanteApoderado;
        try {
            Map<String, Object> options = new HashMap<String, Object>();
            Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
            sessionMap.put("persona", sigSolicitanteApoderado);
            options.put("closable", false);
            options.put("resizable", false);
            options.put("height", 510);
            options.put("width", 780);
            options.put("contentWidth", "100%");
            options.put("contentHeight", "100%");
            options.put("modal", true);
            options.put("closable", false);
            RequestContext.getCurrentInstance().openDialog("agregarSolicitanteApoderadoSigno", options, null);

            indice = sigSolicitanteApoderadoService.encuentraPosicionListadoSolicitanteApoderado(lstSolicitantes, sigSolicitanteApoderado);
        } catch (Exception ex) {
            Logger.getLogger(ExamenSignosBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Metodo para abrir el dialogo para modificar un apoderado
     *
     * Creado: Eddy Valero Fecha: 04/10/2016 Modificado: Placido Castro Fecha:
     * 25/05/2016
     *
     * @param sigSolicitanteApoderado
     */
    public void abrirDialogoModificarApoderado(SigSolicitanteApoderado sigSolicitanteApoderado) {
        try {
            Map<String, List<String>> params = new HashMap<String, List<String>>();
            List<String> list = new ArrayList<String>();
            list.add("APOD");
            params.put("id", list);
            sigSolicitanteApoderadoSelect = sigSolicitanteApoderado;
            Map<String, Object> options = new HashMap<String, Object>();
            Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
            sessionMap.put("persona", sigSolicitanteApoderado);

            options.put("resizable", false);
            options.put("draggable", true);
            options.put("modal", true);
            options.put("contentHeight", 420);
            options.put("contentWidth", 770);
            options.put("closable", false);
            indice = sigSolicitanteApoderadoService.encuentraPosicionListadoSolicitanteApoderado(lstApoderados, sigSolicitanteApoderado);
            RequestContext.getCurrentInstance().openDialog("agregarSolicitanteApoderadoSigno", options, params);
        } catch (Exception ex) {
            Logger.getLogger(ExamenSignosBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Metodo para finalizar el dialogo para modificar un solicitante
     *
     * Creado: Eddy Valero Fecha: 04/10/2016
     *
     */
    public void abrirDialogoListaClaseNiza() {
        Map<String, Object> opcionesDialogo = new HashMap<String, Object>();
        Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        sessionMap.put("idsigsignomarca", sigSignoMarca.getIdSignoMarca());
        opcionesDialogo.put("closable", true);
        opcionesDialogo.put("resizable", false);
        opcionesDialogo.put("height", 320);
        opcionesDialogo.put("width", 720);
        opcionesDialogo.put("contentWidth", "100%");
        opcionesDialogo.put("contentHeight", "100%");
        opcionesDialogo.put("modal", true);
        RequestContext.getCurrentInstance().openDialog("agregarClaseNiza", opcionesDialogo, null);
    }

    /**
     * Metodo para abrir el dialogo para modificar una clase niza
     *
     * Creado: Eddy Valero Fecha: 07/10/2016
     *
     * @param sigSignoClaseNiza
     */
    public void abrirDialogoModificarClaseNiza(SigSignoClaseNiza sigSignoClaseNiza) {

        sigSignoClaseNizaSelect = sigSignoClaseNiza;

        try {
            Map<String, Object> options = new HashMap<String, Object>();
            Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
            sessionMap.put("claseniza", sigSignoClaseNiza);
            options.put("closable", true);
            options.put("resizable", false);
            options.put("height", 320);
            options.put("width", 720);
            options.put("contentWidth", "100%");
            options.put("contentHeight", "100%");
            options.put("modal", true);
            RequestContext.getCurrentInstance().openDialog("agregarClaseNiza", options, null);
//            indice = sigSolicitanteApoderadoService.encuentraPosicionListadoSolicitanteApoderado(lstSolicitantes, sigSolicitanteApoderado);
            indice = sigSignoClaseNizaService.encuentraPosicionListadoSigSignoClaseNiza(lstSigSignoClaseNiza, sigSignoClaseNiza);
        } catch (Exception ex) {
            Logger.getLogger(ExamenSignosBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * *
     * Metodo para finalizar el dialogo para modificar un solicitante
     *
     * Creado: Eddy Valero Fecha: 03/10/2016
     *
     * @param event
     */
    public void finalizarDialogoModificarClaseNiza(SelectEvent event) {
        SigSignoClaseNiza sigSignoClaseNiza;
        if (event.getObject() != "Exit") {
            sigSignoClaseNiza = (SigSignoClaseNiza) event.getObject();
            lstSigSignoClaseNiza.remove(sigSignoClaseNizaSelect);
            lstSigSignoClaseNiza.add(indice, sigSignoClaseNiza);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Clase Niza Modificado correctamente"));
        } else {
            //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cerrado"));
        }
        sigSignoClaseNizaSelect = new SigSignoClaseNiza();
        Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        sessionMap.remove("claseniza");
    }

    /**
     * Metodo para finalizar el dialogo para el cargado de clases niza
     *
     * Creado: Eddy Valero Fecha: 06/10/2016
     *
     * @param event
     */
    public void terminaDialogoAgregarClaseNiza(SelectEvent event) {
        //capturar el objeto enviado del dialogo
        SigSignoClaseNiza sigSignoClaseNiza;
        if (event.getObject() != "Exit") {
            sigSignoClaseNiza = (SigSignoClaseNiza) event.getObject();
            lstSigSignoClaseNiza.add(sigSignoClaseNiza);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Clase Niza Agregado correctamente"));
            //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(sigSignoClaseNiza.getIdClaseNiza().toString()));
        } else {
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cerrado"));
        }

    }

    /**
     * *
     * Metodo para eliminar un registro de sigsignoclaseniza
     *
     * Creado: Eddy Valero Fecha: 11/10/2016
     *
     * @param sigSignoClaseNiza
     */
    public void borrarSigSignoClaseNiza(SigSignoClaseNiza sigSignoClaseNiza) {
        lstSigSignoClaseNiza.remove(sigSignoClaseNiza);
    }

    /**
     * *
     * Metodo para finalizar el dialogo para modificar un solicitante
     *
     * Creado: Eddy Valero Fecha: 03/10/2016
     *
     * @param event
     */
    public void finalizarDialogoModificar(SelectEvent event) {
        SigSolicitanteApoderado solicitanteApoderado;
        if (event.getObject() != "Exit") {
            solicitanteApoderado = (SigSolicitanteApoderado) event.getObject();
            lstSolicitantes.remove(sigSolicitanteApoderadoSelect);
            lstSolicitantes.add(indice, solicitanteApoderado);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Datos del Solicitante fueron Modificados correctamente"));
        } else {
            //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cerrado"));
        }
        sigSolicitanteApoderadoSelect = new SigSolicitanteApoderado();
        Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        sessionMap.remove("persona");
    }

    /**
     * Metodo para eliminar un registro de solicitante
     *
     * Creado: Eddy Valero Fecha: 03/10/2016
     *
     * @param sigSolicitanteApoderado
     */
    public void borrarSolicitante(SigSolicitanteApoderado sigSolicitanteApoderado) {
        lstSolicitantes.remove(sigSolicitanteApoderado);
    }

    /**
     * Metodo para eliminar un registro de apoderados
     *
     * Creado: Eddy Valero Fecha: 03/10/2016
     *
     * @param sigSolicitanteApoderado
     */
    public void borrarApoderado(SigSolicitanteApoderado sigSolicitanteApoderado) {
        lstApoderados.remove(sigSolicitanteApoderado);
    }

    public void activaCamposNaturalJuridico() {

        panelSolicitanteRendered = !tipoSolicitante.equals("N");
    }

    public String abrirPlantillaExamenForma() {

        //   return "abrirPlantillaVentanilla";
        return "abrirPlantillaExamenForma";
        //recepcionDeDocumentos.xhtml
//        return null;
    }

    public String abrirPlantillaExamenFondo() {

        //   return "abrirPlantillaVentanilla";
        return "abrirPlantillaExamenFondo";
        //recepcionDeDocumentos.xhtml
//        return null;
    }

//    public List<String> getImages() {
//        return images;
//    }
//
//    public void setImages(List<String> images) {
//        this.images = images;
//    }
    public void accionSolicitarPeticionNotificacion() throws Exception {

        if ((!this.valorNumeroExpediente.equals(""))
                && (!this.valorGestionExpediente.equals(""))) {

            Notificacion notifica = new Notificacion();

            List<Notificacion> listNotifica = notificacionService.listaNotificacionDatosSM(this.sigSignoMarca.getSm());

            if (this.valorRadioBuscador.equals("soma")) {
                notifica.setTipo_tramite_notificacion("SM");
                notifica.setExpediente(this.valorNumeroExpediente);
                notifica.setGestion(Integer.parseInt(this.valorGestionExpediente));
                notifica.setExtension(this.valorExtensionExpediente);

            }
            //en caso que se seleccione por numero de publicacion
            if (this.valorRadioBuscador.equals("nupu")) {
                notifica.setTipo_tramite_notificacion("N° PUB SIGNO");
                notifica.setExpediente(this.numeroExpediente);

            }
            if (this.valorRadioBuscador.equals("nure")) {   //En teoria esto deberia ser asi
                notifica.setTipo_tramite_notificacion("N° REG");

                notifica.setExpediente(this.numeroExpediente);
                notifica.setExtension(this.extensionExpediente);
            }

            notifica.setDemandante(listNotifica.get(0).getDemandante());
            notifica.setDemandante_solic(listNotifica.get(0).getDemandante_solic());
            notifica.setDemandante_apod(listNotifica.get(0).getDemandante_apod());
            notifica.setDemandante_direc(listNotifica.get(0).getDemandante_direc());
            notifica.setDemandante_cel(listNotifica.get(0).getDemandante_cel());

            //Parametros para enviar a la vista de notificacion.
            Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
            sessionMap.put("notifiObj", notifica);
            Map<String, Object> options = new HashMap<String, Object>();
            options.put("resizable", true);
            options.put("height", 700);
            options.put("width", 1220);
            options.put("contentWidth", "100%");
            options.put("contentHeight", "100%");
            options.put("closable", true);
            options.put("modal", true);

            Map<String, List<String>> var = new HashMap<String, List<String>>();
            List<String> params = new ArrayList();
            params.add("1");//Pondria el primary key para encontrar todos los datos que necesito, aunque lo mejor es que me pase todo lo que necesito en un objeto
            params.add("signo");//Para otros tendria que ser modificacion u oposicion o renovacion
            var.put("datosGenerales", params);
            RequestContext.getCurrentInstance().openDialog("/notificacion/notiPeticionDialog", options, var);
            //RequestContext.getCurrentInstance().openDialog("/notificacion/notiPeticionDialog", options, null);
        /*esta parte del codigo comentado  sirve para enviar al dialogo  variables para la vista*/
            /* Map<String,List<String>> var= new HashMap<String,List<String>>();
             List<String> a1 = new ArrayList();
             a1.add("./../WEB-INF/facelets/templates/sipiTemplate.xhtml");
             var.put("var",a1);
             */

        } else {
            /*Mensaje de Error*/
            FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Debe realizar una busqueda:" + this.numeroExpediente + "-" + this.gestionExpediente, "")
            );
        }

    }

    public void verPanelExpedienteDigital() {

        //en construccion.
//        Map<String, Object> options = new HashMap<String, Object>();
//        options.put("resizable", true);
//        options.put("height", 700);
//        options.put("width", "90%");
//        options.put("contentWidth", "100%");
//        options.put("contentHeight", "100%");
//        options.put("modal", true);
//        RequestContext.getCurrentInstance().openDialog("/signo/expedienteDigitalDialog", options, null);
        if (this.sigSignoMarca.getIdSignoMarca() != null) {
            FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage(FacesMessage.SEVERITY_INFO, "La opción se encuentra en construcción", "**** ")
            );
        } else {
            FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Se debe realizar el registro de la marca", "")
            );
        }

    }

    public void abrirHistorial() {
        if (sigSignoMarca.getIdSignoMarca() != null) {
            Map<String, Object> options = new HashMap<String, Object>();
            options.put("closable", true);
            options.put("resizable", false);
            options.put("height", 380);//tamaño del dialogo vertical del cuadro interno
            options.put("width", 880); //tammaño horizontal del dialogo adentro en el formulario
            options.put("contentWidth", "100%"); //MANTENER EL 100% se usa asi si diste x puntos dice cuantos de porcentaje de los xpuntos usaras
            options.put("contentHeight", "100%");//tamaño del dialogo vertical del cuadro externo
            options.put("modal", false);
            options.put("closeOnEscape", true);
            Map<String, List<String>> var = new HashMap<String, List<String>>();
            List<String> params = new ArrayList();
            //Enviar el id del objeto
            params.add(sigSignoMarca.getIdSignoMarca().toString());
            //en el caso de marcas enviar el tipo SIG
            params.add(EnumPrefijoTablas.SIGNO.getCodigo());
            //params.add("Enviar el SM EN STRING");
            var.put("datosEnviados", params);
            RequestContext.getCurrentInstance().openDialog("/historial/historialTramite.xhtml", options, var);
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Debe realizar una busqueda previamente", ""));
        }

    }

    public void abrirDigitalizacion() {
        //if (this.sigSignoMarca.getIdSignoMarca() != null) {
        Map<String, Object> options = new HashMap<String, Object>();
        options.put("closable", true);
        options.put("resizable", false);
        options.put("height", 670);
        options.put("width", 1150);
        options.put("contentWidth", "100%");
        options.put("contentHeight", "100%");
        options.put("modal", true);
        Map<String, List<String>> var = new HashMap<String, List<String>>();
        List<String> params = new ArrayList();
        params.add("1");
        params.add(EnumPrefijoTablas.SIGNO.getCodigo());
//            params.add(sigSignoMarca.getIdSignoMarca().toString());
//            params.add(sigSignoMarca.getSm().toString());
        var.put("datosEnviados", params);
        RequestContext.getCurrentInstance().openDialog("/digitalizacion/digitalizacion.xhtml", options, var);
//            }
//        else {
//            FacesContext.getCurrentInstance().addMessage(
//                    null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Debe realizar una busqueda previamente", "")
//            );
//        }
    }

    public void AccionAbrirObservacionTramite() {
        //preguntar si es la primera vez que se quiere registrar una observacin al sigSignoMarca
        if (sigSignoMarca.getIdSignoMarca() != null) {
            //se abre una ventana que no es otra pantalla.
            Map<String, Object> options = new HashMap<String, Object>();
            options.put("closable", true);
            options.put("resizable", false);
            options.put("height", 550);
            options.put("width", 920);
            options.put("contentWidth", "100%");
            options.put("contentHeight", "100%");
            options.put("modal", true);

            Map<String, List<String>> var = new HashMap<String, List<String>>();
            List<String> params = new ArrayList();
            params.add("1");//Pondria el primary key para encontrar todos los datos que necesito, aunque lo mejor es que me pase todo lo que necesito en un objeto

            params.add("SIG");
            params.add(sigSignoMarca.getIdSignoMarca().toString());
            var.put("datosGenerales", params);

            RequestContext.getCurrentInstance().openDialog("/observacion/observacionTramite.xhtml", options, var);
        } else {
            FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Se debe realizar el registro de la marca", "")
            );
        }

    }

    public void AccionEnviarSubsanacionTramiteSigno() {
        //preguntar si es la primera vez que se quiere registrar una observacin al sigSignoMarca
        if (sigSignoMarca.getIdSignoMarca() != null) {
            //se abre una ventana que no es otra pantalla.
            Map<String, Object> options = new HashMap<String, Object>();
            options.put("closable", true);
            options.put("resizable", false);
            options.put("height", 500);
            options.put("width", 400);
            options.put("contentWidth", "100%");
            options.put("contentHeight", "100%");
            options.put("modal", false); // false, para no sombrear el fondo

            Map<String, List<String>> var = new HashMap<String, List<String>>();
            List<String> params = new ArrayList();
            params.add("1");//Pondria el primary key para encontrar todos los datos que necesito, aunque lo mejor es que me pase todo lo que necesito en un objeto

            params.add("SIG");
            params.add(sigSignoMarca.getNumeroFormulario());
            var.put("datosGenerales", params);

            RequestContext.getCurrentInstance().openDialog("./enviarSubsanacion.xhtml", options, var);
        } else {
            FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Se debe realizar el registro de la marca", "")
            );
        }

    }

    public void AccionAbrirObservacionTramiteSigno() {
        //preguntar si es la primera vez que se quiere registrar una observacin al sigSignoMarca
        if (sigSignoMarca.getIdSignoMarca() != null) {
            //se abre una ventana que no es otra pantalla.
            Map<String, Object> options = new HashMap<String, Object>();
            options.put("closable", true);
            options.put("resizable", false);
            options.put("height", 550);
            options.put("width", 920);
            options.put("contentWidth", "100%");
            options.put("contentHeight", "100%");
            options.put("modal", true);

            Map<String, List<String>> var = new HashMap<String, List<String>>();
            List<String> params = new ArrayList();
            params.add("1");//Pondria el primary key para encontrar todos los datos que necesito, aunque lo mejor es que me pase todo lo que necesito en un objeto

            params.add("SIG");
            params.add(sigSignoMarca.getIdSignoMarca().toString());
            var.put("datosGenerales", params);

            RequestContext.getCurrentInstance().openDialog("/observacion/observacionTramiteSigno.xhtml", options, var);
        } else {
            FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Se debe realizar el registro de la marca", "")
            );
        }

    }

    public void terminaDialogoObservacion(SelectEvent event) throws Exception {
        if (event.getObject() != "Exit") {

        } else {
            //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cerrado"));
        }
        observacionTramite = observacionTramiteService.obtenerUltimaObservacionTramite(EnumPrefijoTablas.SIGNO.getCodigo(), this.sigSignoMarca.getIdSignoMarca());
    }

//    public String abrirFinalizar() {
//        return ("busquedaModificacion");
//    }
    public void abrirPoderDepositado() throws Exception {
        String testimonio = "";
        String confiere = "";
        String domicilio = "";
        String apoderado = "";

        if (sigSignoMarca.getIdSignoMarca() != null) {

            for (int i = 0; i <= lstSolicitantes.size() - 1; i++) {
                if (i == 0) {
                    confiere = devuelveNombreJuridicoONatural(lstSolicitantes.get(i).getNombreRazonSocial(),
                            lstSolicitantes.get(i).getPrimerApellido(),
                            lstSolicitantes.get(i).getSegundoApellido());
                    domicilio = lstSolicitantes.get(i).getDireccion();

                } else {
                    confiere = confiere + "; " + devuelveNombreJuridicoONatural(lstSolicitantes.get(i).getNombreRazonSocial(),
                            lstSolicitantes.get(i).getPrimerApellido(),
                            lstSolicitantes.get(i).getSegundoApellido());
                    domicilio = domicilio + "; " + lstSolicitantes.get(i).getDireccion();
                }

            }
            for (int i = 0; i <= lstApoderados.size() - 1; i++) {
                if (i == 0) {
                    apoderado = devuelveNombreJuridicoONatural(lstApoderados.get(i).getNombreRazonSocial(),
                            lstApoderados.get(i).getPrimerApellido(),
                            lstApoderados.get(i).getSegundoApellido());
                    testimonio = lstApoderados.get(i).getPoder();
                    //lstApoderados.get(i).get
                } else {
                    apoderado = apoderado + ";" + devuelveNombreJuridicoONatural(lstApoderados.get(i).getNombreRazonSocial(),
                            lstApoderados.get(i).getPrimerApellido(),
                            lstApoderados.get(i).getSegundoApellido());
                    testimonio = testimonio + ";" + lstApoderados.get(i).getPoder();
                }
            }

            //lstSolicitantes
            //devuelveNombreJuridicoONatural        
            /////////////////////
            Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
            // sessionMap.put("notifiObj", notifica);
            Map<String, Object> options = new HashMap<String, Object>();
            options.put("resizable", true);
            options.put("height", 630);
            options.put("width", 740);
            options.put("contentWidth", "100%");
            options.put("contentHeight", "100%");
            options.put("modal", false);
            options.put("closable", true);

            Map<String, List<String>> var = new HashMap<String, List<String>>();
            List<String> params = new ArrayList();
            params.add("1");//Pondria el primary key para encontrar todos los datos que necesito, aunque lo mejor es que me pase todo lo que necesito en un objeto
            params.add("SM");//Para otros tendria que ser modificacion u oposicion o renovacion
            params.add(sigSignoMarca.getSm().toString());
            params.add(sigSignoMarca.getIdSignoMarca().toString());
            if (testimonio == null) {
                params.add("");
            } else {
                params.add(testimonio);
            }

            params.add(confiere);
            params.add(domicilio);
            params.add(apoderado);

            var.put("datosGenerales", params);
            RequestContext.getCurrentInstance().openDialog("/libropoder/libroDePoderes", options, var);
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Debe realizar una busqueda previamente", ""));
        }
    }

    public void abrirSeguimiento() {
        if (sigSignoMarca.getIdSignoMarca() != null) {
            Map<String, Object> options = new HashMap<String, Object>();
            // options.put("resizable", false);
            // options.put("draggable", true);
            // options.put("modal", true);
            // options.put("contentHeight", 530);
            // options.put("contentWidth", 1020);
            options.put("closable", true);
            options.put("resizable", false);
            options.put("height", 625);
            options.put("width", 1125);
            options.put("contentWidth", "100%");
            options.put("contentHeight", "100%");
            options.put("modal", true);
            Map<String, List<String>> var = new HashMap<String, List<String>>();
            List<String> params = new ArrayList();
            params.add("1");
            params.add(EnumPrefijoTablas.SIGNO.getCodigo());
            params.add(sigSignoMarca.getIdSignoMarca().toString());
            String ext = "";
            if (sigSignoMarca.getExtensionMarca() == 00) {
            } else {
                char ch = (char) (sigSignoMarca.getExtensionMarca() + 55);
                ext = "-" + ch;
            }

            params.add("SM-" + String.format("%6s", sigSignoMarca.getNumero()).replace(' ', '0') + "-" + sigSignoMarca.getGestion() + ext);
            Long idregional = regionalService.lista_IdRegional_TipoCiudadNotificacion(regional);
            params.add(idregional.toString());
            var.put("datosEnviados", params);
            RequestContext.getCurrentInstance().openDialog("/seguimiento/controlPlazos.xhtml", options, var);
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Debe realizar una busqueda previamente", ""));
        }
    }

    public void terminaDialogoSeguimiento(SelectEvent event) throws Exception {
        if (event.getObject() != "Exit") {
        } else {
            //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cerrado"));
        }
    }

    public void mostrarRecepcionarFinalizar(String etapa) {

        if (sigSignoMarca != null) {
            ultimoSeguimiento = seguimientoService.ultimo_Seguimiento_etapa(EnumPrefijoTablas.SIGNO.getCodigo(), sigSignoMarca.getIdSignoMarca(), etapa);
            if (ultimoSeguimiento != null) {
                if (ultimoSeguimiento.getFechaFin() != null) {
                    botonRecepcionarDisabled = false;
                    botonFinalizarDisabled = true;
                } else {
                    botonRecepcionarDisabled = true;
                    botonFinalizarDisabled = false;
                }
            } else {
                botonRecepcionarDisabled = false;
                botonFinalizarDisabled = true;
            }
            if (super.getIdEtapaSession() == 18 || super.getIdEtapaSession() == 20) {
                botonFinalizarDisabled = false;
            }
            if (ultimoSeguimiento != null) {
                if (ultimoSeguimiento.getFechaFin() != null) {
                    if (ultimoSeguimiento.getEtapa().equals("DESG") || ultimoSeguimiento.getEtapa().equals("ENTR")) //botonRecepcionarDisabled = false;
                    {
                        botonFinalizarDisabled = true;
                    }
                }
            }
        }

    }

    public void guardarSeguimiento() throws Exception {

        //preguntramos si la ultima etapa de donde vino tiene fecha fin si es asi puede hacer su recepcion si no le sale n mesaje
        List<Seguimiento> listaAux = seguimientoService.lista_SeguimientoXid("SIG", sigSignoMarca.getIdSignoMarca());
        if (listaAux.get(listaAux.size() - 1).getFechaFin() != null) {

            Seguimiento seguimientoNuevo = new Seguimiento();
            seguimientoNuevo.setId(sigSignoMarca.getIdSignoMarca());
            seguimientoNuevo.setIdUsuario(super.getIdUsuarioSession());

            seguimientoNuevo.setIdLogTrans(logtrans);
            seguimientoNuevo.setSm(sigSignoMarca.getSm());
            seguimientoNuevo.setNumeroPublicacion(sigSignoMarca.getNumeroPublicacion());
            seguimientoNuevo.setNumeroRegistro(sigSignoMarca.getNumeroRegistro());
            seguimientoNuevo.setSerieRegistro(sigSignoMarca.getSerieRegistro());

            //  List<Etapa>  listEtapaUs=etapaService.listadoPorIdUsuario(super.getIdUsuarioSession());
            seguimientoNuevo.setEtapa(listEtapaUs.get(0).getTipoEtapa());
            seguimientoNuevo.setObservacion(null);
            seguimientoNuevo.setEditable(false);
            seguimientoNuevo.setEstado("AC");

            //Seguimiento ultimo = seguimientoService.lista_seguimiento_ultimo(EnumPrefijoTablas.SIGNO.getCodigo(), sigSignoMarca.getIdSignoMarca());
            if (super.getIdEtapaSession() == 15 || super.getIdEtapaSession() == 16) {
                seguimientoNuevo.setFechaRecepcion(comunService.obtenerFechaHoraServidor(1L));
            } else {

                //Pregunta si  la anterior etapa tiene fecha fin si es asi se copia, si no es asi significa que la anterior etapa no lo acabaron  entonces crea fechaservidor
                if (seguimientoService.lista_seguimiento_ultimo(EnumPrefijoTablas.SIGNO.getCodigo(), sigSignoMarca.getIdSignoMarca()) != null) {
                    Seguimiento seguimientoAnterior = seguimientoService.lista_seguimiento_ultimo(EnumPrefijoTablas.SIGNO.getCodigo(), sigSignoMarca.getIdSignoMarca());
                    //Si es examen de registrabilidadle sumo mas un dia al tiemmpo de espera oposicion, su  fecha fin
                    if (super.getIdEtapaSession() == 7) {
                        Calendar c = Calendar.getInstance();
                        c.setTime(new Date(seguimientoAnterior.getFechaFin().getTime() + 0005L));
                        c.add(Calendar.DATE, 1);
                        while (c.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY || c.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
                            c.add(Calendar.DATE, 1);
                        }
                        seguimientoNuevo.setFechaRecepcion(c.getTime());

                    } else {

                        seguimientoNuevo.setFechaRecepcion(new Date(seguimientoAnterior.getFechaFin().getTime() + 0005L));
                    }

                } else {
                    Date fechaServidor = comunService.obtenerFechaHoraServidor(1l);
                    seguimientoNuevo.setFechaRecepcion(fechaServidor);

                }

            }

            if (seguimientoService.listaSeguiUltimoXIdsignoEtapaUltMov(sigSignoMarca.getIdSignoMarca(), listEtapaUs.get(0).getTipoEtapa()) != null) {
                System.out.println("Significa qeu si tiene mas de un examen de forma");
                seguimientoNuevo.setPlazo_limite(flujoTiempoService.obtieneFlujoXIdetapa(listEtapaUs.get(0).getTipoEtapa(), 2));

            } else {
                System.out.println("Significa qeu no tiene mas de un examen de forma");

                seguimientoNuevo.setPlazo_limite(flujoTiempoService.obtieneFlujoXIdetapa(listEtapaUs.get(0).getTipoEtapa(), 1));
            }
            //EN EL CASO DE QUE SE NECESITE COMENTAR LA PARTE DE SUMAR UN DIA SERIA LOS DOS IF SIGUIENTES DONDE PREGUNTAN EXFM Y ANR.-
            //Dentro de este if  se calcula mas un dia si es examen de forma
        /*if (listEtapaUs.get(0).getTipoEtapa().equals("EXFM")) {
             List<Seguimiento> listSeguimientoUltimos = seguimientoService.listaSeguimientoXIdsegEtapaUltMov(sigSignoMarca.getIdSignoMarca(), "EXFM");
           
 
             Calendar c = Calendar.getInstance();
             c.setTime(listSeguimientoUltimos.get(0).getFechaFin());
             c.add(Calendar.DATE, 1);
             while (c.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY || c.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
             c.add(Calendar.DATE, 1);
             }
 
           
             seguimientoNuevo.setFechaRecepcion(c.getTime());
 
             }
             if (listEtapaUs.get(0).getTipoEtapa().equals("ANR")) {
             List<Seguimiento> listSeguimientoUltimos = seguimientoService.listaSeguimientoXIdsegEtapaUltMov(sigSignoMarca.getIdSignoMarca(), "ANR");
             Calendar c = Calendar.getInstance();
             //Si es publicacion se le suma 31 dias  no importa si son habiles o no eso no lo controlo
             if (listSeguimientoUltimos.get(0).getEtapa().equals("PPP")) {
 
             c.setTime(listSeguimientoUltimos.get(0).getFechaFin());
             c.add(Calendar.DATE, 31);
 
             while (c.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY || c.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
             c.add(Calendar.DATE, 1);
             }
 
             } else {
 
             c.setTime(listSeguimientoUltimos.get(0).getFechaFin());
             c.add(Calendar.DATE, 1);
             while (c.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY || c.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
             c.add(Calendar.DATE, 1);
             }
             }
 
 
             seguimientoNuevo.setFechaRecepcion(c.getTime());
             }
             */
            ultimoSeguimiento = seguimientoService.guardar_modificar_Seguimiento_etapa(seguimientoNuevo, "SIG");
            mostrarRecepcionarFinalizar(listEtapaUs.get(0).getTipoEtapa());

        } else {

            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "ETAPA ANTERIOR NO FINALIZADA. Revise su seguimiento", "Revise su seguimiento");
            FacesContext.getCurrentInstance().addMessage(null, message);

        }
    }

    public String abrirFinalizar() throws Exception {
        //  if (listEtapaUs.get(0).getTipoEtapa().equals("EXFM") || listEtapaUs.get(0).getTipoEtapa().equals("ANR")) {
        Seguimiento seguimientoNuevo = new Seguimiento();
        seguimientoNuevo.setId(sigSignoMarca.getIdSignoMarca());
        seguimientoNuevo.setIdUsuario(super.getIdUsuarioSession());
        seguimientoNuevo.setIdLogTrans(logtrans);
        seguimientoNuevo.setSm(sigSignoMarca.getSm());
        seguimientoNuevo.setNumeroPublicacion(sigSignoMarca.getNumeroPublicacion());
        seguimientoNuevo.setNumeroRegistro(sigSignoMarca.getNumeroRegistro());
        seguimientoNuevo.setSerieRegistro(sigSignoMarca.getSerieRegistro());
        //        List<Etapa>  listEtapaUs=etapaService.listadoPorIdUsuario(super.getIdUsuarioSession());
        seguimientoNuevo.setEtapa(listEtapaUs.get(0).getTipoEtapa());
        Date fechaPresente = comunService.obtenerFechaHoraServidor(1L);
        seguimientoNuevo.setFechaFin(fechaPresente);
        seguimientoNuevo.setObservacion(null);
        seguimientoNuevo.setEditable(false);
        seguimientoNuevo.setEstado(EnumEstado.ACTIVO.getCodigo());
        seguimientoNuevo.setIdUsuario(super.getIdUsuarioSession());

        if (RecepcionarFinalizar[2]) {
            Seguimiento seguimientoAnterior = seguimientoService.lista_seguimiento_ultimo(EnumPrefijoTablas.SIGNO.getCodigo(), sigSignoMarca.getIdSignoMarca());
            if (seguimientoAnterior != null) {
                seguimientoNuevo.setFechaRecepcion(new Date(seguimientoAnterior.getFechaFin().getTime() + 0005L));
            } else {
                Date fechaServidor = comunService.obtenerFechaHoraServidor(1l);
                seguimientoNuevo.setFechaRecepcion(fechaServidor);
            }
            seguimientoNuevo.setPlazo_limite(flujoTiempoService.obtieneFlujoXIdetapa(listEtapaUs.get(0).getTipoEtapa(), 1));
        }
        String descripcionUbicacion = dominioService.obtenerNombreCodigoDominio("ubicacion", sigSignoMarca.getUbicacion());
        String descripcionEstado = dominioService.obtenerNombreCodigoDominio("estado_marca", sigSignoMarca.getEstadoMarca());
        if (super.getIdEtapaSession() == 18) {

            valorEstadoMarca = "REG";
            sigSignoMarca.setEstadoMarca("REG");
            valorUbicacion = "VENE";
            sigSignoMarca.setUbicacion("VENE");
            sigSignoMarcaService.crudSigSignoMarca(sigSignoMarca, 2);
            historialService.guardarHistorialGenerico(EnumPrefijoTablas.SIGNO.getCodigo(), sigSignoMarca.getIdSignoMarca(), idUsuario, "STATUS",
                    EnumOperacion.MODIFICAR.getCodigo(), dominioService.obtenerNombreCodigoDominio("estado_marca", sigSignoMarca.getEstadoMarca()),
                    observacion, dominioService.obtenerNombreCodigoDominio("ubicacion", sigSignoMarca.getUbicacion()), "[Status:" + descripcionEstado + "][Ubic.:" + descripcionUbicacion + "]");
        }
        if (super.getIdEtapaSession() == 20) {
            valorUbicacion = "BIBL";
            sigSignoMarca.setUbicacion(valorUbicacion);
            sigSignoMarcaService.crudSigSignoMarca(sigSignoMarca, 2);
            historialService.guardarHistorialGenerico(EnumPrefijoTablas.SIGNO.getCodigo(), sigSignoMarca.getIdSignoMarca(), idUsuario, "STATUS",
                    EnumOperacion.MODIFICAR.getCodigo(), dominioService.obtenerNombreCodigoDominio("estado_marca", sigSignoMarca.getEstadoMarca()),
                    observacion, dominioService.obtenerNombreCodigoDominio("ubicacion", sigSignoMarca.getUbicacion()), "[Ubic.:" + descripcionUbicacion + "]");
        }
        ultimoSeguimiento = seguimientoService.guardar_modificar_Seguimiento_etapa(seguimientoNuevo, EnumPrefijoTablas.SIGNO.getCodigo()); //        }
        //Esta parte esta comentado estaba para q andetro de  ANR se pueda derivar a don adrian pero ya no hay eso
                /*  if(listEtapaUs.get(0).getTipoEtapa().equals("ANR"))
         {        
         Seguimiento seguimientoNuevo = new Seguimiento();
         seguimientoNuevo.setId(sigSignoMarca.getIdSignoMarca());
         seguimientoNuevo.setIdUsuario(super.getIdUsuarioSession());
         seguimientoNuevo.setIdLogTrans(logtrans);
         seguimientoNuevo.setSm(sigSignoMarca.getSm());
         seguimientoNuevo.setNumeroPublicacion(sigSignoMarca.getNumeroPublicacion());
         seguimientoNuevo.setNumeroRegistro(sigSignoMarca.getNumeroRegistro());
         seguimientoNuevo.setSerieRegistro(sigSignoMarca.getSerieRegistro());
         //        List<Etapa>  listEtapaUs=etapaService.listadoPorIdUsuario(super.getIdUsuarioSession());
         seguimientoNuevo.setEtapa(listEtapaUs.get(0).getTipoEtapa());
         Date fechaPresente = comunService.obtenerFechaHoraServidor(1L);
         seguimientoNuevo.setFechaFin(fechaPresente);
         seguimientoNuevo.setObservacion(null);
         seguimientoNuevo.setEditable(false);
         seguimientoNuevo.setEstado("AC");
         seguimientoNuevo.setIdUsuario(super.getIdUsuarioSession());
         ultimoSeguimiento = seguimientoService.guardar_modificar_Seguimiento_etapa(seguimientoNuevo, "SIG");
            
         
         }*/ //el codigo de abajo cambia los boones de habilitados a inhabilitados
        mostrarRecepcionarFinalizar(listEtapaUs.get(0).getTipoEtapa());
        //return ("abrirPrincipal");
        return null;
    }

    /**
     * Metodo para llenar los datos del reporte y su generacion en formato pdf
     *
     * Creado: Ruben Ramirez Fecha: 15/10/2016 Modificado: Placido Castro Fecha:
     * 25/05/2017
     *
     */
    public void imprimir() throws JRException, IOException, Exception {

        if (validarNumeroExpedienteGestion()) {

            sigSignoMarcaRepo = sigSignoMarcaService.obtenerSigSignoMarcaXidSignoMarca(sigSignoMarca.getIdSignoMarca());

            if (sigSignoMarcaRepo.getIdSignoMarca() != null) {
                try {
                    lstTipoGeneroXdominio = dominioService.obtenerListadoDominioXCodigo("tipo_genero", sigSignoMarcaRepo.getTipoGenero());
                } catch (Exception ex) {
                    throw ex;
                }

                imgSenapi = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/images/logoNuevo.jpg");

                DateFormatSymbols dateFormatSymbols = new DateFormatSymbols();
                dateFormatSymbols.setWeekdays(new String[]{
                    "",
                    "domingo",
                    "lunes",
                    "martes",
                    "miércoles",
                    "jueves",
                    "viernes",
                    "sábado"});

                dateFormatSymbols.setMonths(new String[]{
                    "enero",
                    "febrero",
                    "marzo",
                    "abril",
                    "mayo",
                    "junio",
                    "julio",
                    "agosto",
                    "septiembre",
                    "octubre",
                    "noviembre",
                    "diciembre"});

                SimpleDateFormat formateador1 = new SimpleDateFormat("dd/MM/yyyy");
                SimpleDateFormat formateador2 = new SimpleDateFormat("HH:mm:ss");
                SimpleDateFormat formateador3 = new SimpleDateFormat("EEEEE, d MMMM, yyyy", dateFormatSymbols);
                SimpleDateFormat formateador4 = new SimpleDateFormat("H:mm aa");

                // fecha actual de al base de datos
                Date fechaPresente = comunService.obtenerFechaHoraServidor(1L);
                if (fechaPresente != null) {
                    fechaHoy = formateador3.format(fechaPresente);
                    horaHoy = formateador4.format(fechaPresente).toLowerCase();
                }

                fechasm = formateador1.format(sigSignoMarcaRepo.getFechaIngreso());
                horasm = formateador2.format(sigSignoMarcaRepo.getFechaIngreso());
                marcaX = sigSignoMarcaRepo.getSigno();
                sm = valorNumeroExpediente + valorExtensionExpediente + "-" + valorGestionExpediente;

                lstSigSignoClaseNizaRepo = sigSignoClaseNizaService.obtenerListaSigSignoClazeNizaXIdSignoMarca(sigSignoMarcaRepo.getIdSignoMarca());

                int numLstSigSignoClaseNiza = lstSigSignoClaseNizaRepo.size();
                if (numLstSigSignoClaseNiza > 0) {
                    if (numLstSigSignoClaseNiza == 1) {
                        SigSignoClaseNiza sigSignoClaseNiza = lstSigSignoClaseNizaRepo.get(0);
                        clase = "" + sigSignoClaseNiza.getNumeroClaseNiza();
                        if (sigSignoClaseNiza.getListaProducto() != null) {
                            if (!sigSignoClaseNiza.getListaProducto().equals("")) {
                                productos = sigSignoClaseNiza.getListaProducto();
                            } else {
                                productos = "";
                            }
                        } else {
                            productos = "";
                        }
                    } else {
                        for (int i = 0; i < lstSigSignoClaseNizaRepo.size() - 1; i++) {
                            SigSignoClaseNiza sigSignoClaseNiza = lstSigSignoClaseNizaRepo.get(i);
                            clase += "" + sigSignoClaseNiza.getNumeroClaseNiza() + "; ";
                            if (sigSignoClaseNiza.getListaProducto() != null) {
                                if (!sigSignoClaseNiza.getListaProducto().equals("")) {
                                    productos += sigSignoClaseNiza.getListaProducto() + "; ";
                                }
                            }
                        }
                        SigSignoClaseNiza sigSignoClaseNiza = lstSigSignoClaseNizaRepo.get(numLstSigSignoClaseNiza - 1);
                        clase += "" + sigSignoClaseNiza.getNumeroClaseNiza();
                        if (sigSignoClaseNiza.getListaProducto() != null) {
                            if (!sigSignoClaseNiza.getListaProducto().equals("")) {
                                productos += sigSignoClaseNiza.getListaProducto();
                            }
                        }
                    }
                }

                int numLstTipoGeneroXdominio = lstTipoGeneroXdominio.size();
                if (numLstTipoGeneroXdominio > 0) {
                    if (numLstTipoGeneroXdominio == 1) {
                        Dominio dominio = lstTipoGeneroXdominio.get(0);
                        tipoMarca = "" + dominio.getNombre();
                    } else {
                        for (int i = 0; i < lstTipoGeneroXdominio.size() - 1; i++) {
                            Dominio dominio = lstTipoGeneroXdominio.get(i);
                            tipoMarca += "" + dominio.getNombre();
                        }
                        Dominio dominio = lstTipoGeneroXdominio.get(numLstTipoGeneroXdominio - 1);
                        tipoMarca += "" + dominio.getNombre();
                    }
                }

                int numLstSigTipoSigno = lstSigTipoSigno.size();
                if (numLstSigTipoSigno > 0) {
                    if (numLstSigTipoSigno == 1) {
                        SigTipoSigno sigTipoSigno = lstSigTipoSigno.get(0);
                        for (Dominio item : lstTipoSigno) {
                            if (sigTipoSigno.getTipoSigno().equals(item.getCodigo())) {
                                if (item.getCodigo().equals("OTRO")) {
                                    tipoSigno += item.getNombre() + ":" + sigTipoSigno.getDescripcionOtro();
                                } else {
                                    tipoSigno += item.getNombre();
                                }
                                break;
                            }
                        }
                    } else {
                        for (int i = 0; i < lstSigTipoSigno.size(); i++) {
                            SigTipoSigno sigTipoSigno = lstSigTipoSigno.get(i);
                            for (Dominio item : lstTipoSigno) {
                                if (sigTipoSigno.getTipoSigno().equals(item.getCodigo())) {
                                    if (item.getCodigo().equals("OTRO")) {
                                        tipoSigno += item.getNombre() + ":" + sigTipoSigno.getDescripcionOtro() + "; ";
                                    } else {
                                        tipoSigno += item.getNombre() + "; ";
                                    }
                                    break;
                                }
                            }
                        }
                        tipoSigno = tipoSigno.trim();
                        tipoSigno = tipoSigno.substring(0, tipoSigno.length() - 1);
                    }
                }

                descripSigno = sigSignoMarca.getDescripcionSigno(); //valorDescripcionDisenio;
                descripLogotipo = sigSignoMarca.getDescripcionLogotipo();

                List<SigSolicitanteApoderado> lstSolicitantesX = new ArrayList<SigSolicitanteApoderado>();
                for (SigSolicitanteApoderado itemX : lstSolicitantes) {
                    lstSolicitantesX.add((SigSolicitanteApoderado) itemX.clone());
                }
                int numLstSolicitantesX = lstSolicitantesX.size();
                if (numLstSolicitantesX > 0) {
                    for (SigSolicitanteApoderado item : lstSolicitantesX) {
                        if (item.getTipoTitular().equals("NAT")) {
                            if (item.getNombreRazonSocial() == null) {
                                item.setNombreRazonSocial("");
                            }
                            if (item.getPrimerApellido() == null) {
                                item.setPrimerApellido("");
                            }
                            if (item.getSegundoApellido() == null) {
                                item.setSegundoApellido("");
                            }
                            item.setNombreRazonSocial(item.getNombreRazonSocial() + " " + item.getPrimerApellido() + " " + item.getSegundoApellido());
                        }
                        for (Dominio item2 : lstTipoDocumento) {
                            if (item2.getNombre() == null) {
                                item2.setNombre("");
                            }
                            if (item.getNumeroDocumento() == null) {
                                item.setNumeroDocumento("");
                            }
                            if (item.getLugarExpedicion() == null) {
                                item.setLugarExpedicion("");
                            }
                            if (item2.getCodigo().equals(item.getTipoDocumento())) {

                                if (item.getTipoDocumento().equals("OTR")) {
                                    String auxDocumento = item2.getNombre() + ": " + item.getNumeroDocumento();
                                    item.setNumeroDocumento(auxDocumento);

                                } else {
                                    String auxDocumento = item2.getNombre() + ": " + item.getNumeroDocumento() + " " + item.getLugarExpedicion();

                                    item.setNumeroDocumento(auxDocumento);
                                }
                                break;
                            }
                        }
                        for (Dominio item3 : lstPaises) {
                            if (item3.getCodigo().equals(item.getPais())) {
                                item.setPais(item3.getNombre());
                                break;
                            }
                        }
                    }
                }

                List<SigSolicitanteApoderado> lstApoderadosX = new ArrayList<SigSolicitanteApoderado>();
                for (SigSolicitanteApoderado itemX : lstApoderados) {
                    lstApoderadosX.add((SigSolicitanteApoderado) itemX.clone());
                }
                int numLstApoderadosX = lstApoderadosX.size();
                if (numLstApoderadosX > 0) {
                    for (SigSolicitanteApoderado item : lstApoderadosX) {
                        if (item.getTipoTitular().equals("NAT")) {
                            if (item.getNombreRazonSocial() == null) {
                                item.setNombreRazonSocial("");
                            }
                            if (item.getPrimerApellido() == null) {
                                item.setPrimerApellido("");
                            }
                            if (item.getSegundoApellido() == null) {
                                item.setSegundoApellido("");
                            }
                            item.setNombreRazonSocial(item.getNombreRazonSocial() + " " + item.getPrimerApellido() + " " + item.getSegundoApellido());
                        }

                        for (Dominio item2 : lstTipoDocumento) {
                            if (item2.getNombre() == null) {
                                item2.setNombre("");
                            }
                            if (item.getNumeroDocumento() == null) {
                                item.setNumeroDocumento("");
                            }
                            if (item.getLugarExpedicion() == null) {
                                item.setLugarExpedicion("");
                            }
                            if (item2.getCodigo().equals(item.getTipoDocumento())) {
                                if (item.getTipoDocumento().equals("OTR")) {
                                    String auxDocumento = item2.getNombre() + ": " + item.getNumeroDocumento();
                                    item.setNumeroDocumento(auxDocumento);

                                } else {
                                    String auxDocumento = item2.getNombre() + ": " + item.getNumeroDocumento() + " " + item.getLugarExpedicion();
                                    item.setNumeroDocumento(auxDocumento);
                                }
                                break;
                            }
                        }

                    }
                }

                if (prioridadExposicion.getIdPrioridadPreferencia() != null) {
                    tipoPrioridad = "PRIORIDAD DE EXPOSICIÓN";
                    prioridad = prioridadExposicion.getNombreNumero();
                    if (prioridadExposicion.getLugar() != null) {
                        lugarPrioridad = prioridadExposicion.getLugar();
                    }

                    if (prioridadExposicion.getFecha() != null) {
                        fechaPrioridad = formateador1.format(prioridadExposicion.getFecha());
                    }
                }

                if (prioridadExtranjera.getIdPrioridadPreferencia() != null) {
                    tipoPrioridad = "PRIORIDAD EXTRANJERA";
                    prioridad = prioridadExtranjera.getNombreNumero();
                    for (Dominio item : lstPaises) {
                        if (item.getCodigo().equals(prioridadExtranjera.getLugar())) {
                            lugarPrioridad = item.getNombre();
                            break;
                        }
                    }

                    if (prioridadExtranjera.getFecha() != null) {
                        fechaPrioridad = formateador1.format(prioridadExtranjera.getFecha());
                    }
                }

                if (oposicionAndina.getIdPrioridadPreferencia() != null) {
                    SigPrioridadPreferencia oposicionAndinaR = sigPrioridadPreferenciaService.obtenerOposicionAndina(this.sigSignoMarcaRepo.getIdSignoMarca());
                    andina = oposicionAndinaR.getNombreNumero();
                }

                situacionActual = valorEstadoMarca;

                int numLstSituacionAx = lstSituacionActual.size();
                if (numLstSituacionAx > 0) {
                    for (Dominio item : lstSituacionActual) {
                        if (item.getCodigo().equals(situacionActual)) {
                            situacionActual = item.getNombre();
                            break;
                        }
                    }
                }

                ObservacionTramite observacionTramiteR = observacionTramiteService.obtenerUltimaObservacionTramite(EnumPrefijoTablas.SIGNO.getCodigo(), sigSignoMarcaRepo.getIdSignoMarca());

                observacion = observacionTramiteR.getDescripcion();

                noPublicacion = valorNumeroPublicacion;
                noGaceta = valorNumeroGaceta;
                if (valorFechaPublicacion != null) {
                    fechaPub = formateador1.format(valorFechaPublicacion);
                }

                if (valorNumeroRegistro == null) {
                    noRegistro = "";
                } else {
                    if (valorSerieRegistro == null || valorNumeroRegistro == 0) {
                        noRegistro = "" + valorNumeroRegistro;
                    } else {
                        noRegistro = valorNumeroRegistro + " - " + valorSerieRegistro;
                    }
                }

                noResolucion = valorResolucionRegistro;

                if (valorFechaRegistro != null) {
                    fechaRegistro = formateador1.format(valorFechaRegistro);
                }

                noRenovacion = valorNumeroRenovacion;
                resoRenovacion = valorResolucionRenovacion;
                if (valorFechaRenovacion != null) {
                    fechaResoRenovacion = formateador1.format(valorFechaRenovacion);
                }

                if (sigImage.getImagen() != null) {
                    byte[] blobasbytes = sigImage.getImagen();
                    ImageIcon icon = new ImageIcon((byte[]) blobasbytes);
                    imagenes = icon.getImage();
                }

                parametros.put("imgSenapi", imgSenapi);
                parametros.put("fechaHoy", fechaHoy);
                parametros.put("horaHoy", horaHoy);
                parametros.put("marca", marcaX);
                parametros.put("sm", sm);
                parametros.put("fechasm", fechasm);
                parametros.put("horasm", horasm);
                parametros.put("clase", clase);
                parametros.put("tipoMarca", tipoMarca);
                parametros.put("tipoSigno", tipoSigno);
                parametros.put("descripSigno", descripSigno);
                parametros.put("descripLogotipo", descripLogotipo);
                parametros.put("productos", productos);
                parametros.put("solicitantes", getDatos(lstSolicitantesX));
                parametros.put("apoderados", getDatos(lstApoderadosX));
                parametros.put("tipoPrioridad", tipoPrioridad);
                parametros.put("prioridad", prioridad);
                parametros.put("fechaPrioridad", fechaPrioridad);
                parametros.put("lugarPrioridad", lugarPrioridad);
                parametros.put("andina", andina);
                parametros.put("situacionActual", situacionActual);
                parametros.put("observacion", observacion);
                parametros.put("noPublicacion", noPublicacion);
                parametros.put("noGaceta", noGaceta);
                parametros.put("fechaPub", fechaPub);
                parametros.put("noRegistro", noRegistro);
                parametros.put("noResolucion", noResolucion);
                parametros.put("fechaRegistro", fechaRegistro);
                parametros.put("noRenovacion", noRenovacion);
                parametros.put("resoRenovacion", resoRenovacion);
                parametros.put("fechaResoRenovacion", fechaResoRenovacion);
                parametros.put("imgLogo", imagenes);

                String filename = "SignosDistintivos.pdf";
                String jasperPath = "/template/solicitudes/ExamenSignos.jasper";
                this.PDFSD(parametros, jasperPath, filename);

                limpiar();
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Primero realice la busqueda del formulario para imprimir", ""));
        }
    }

    public void limpiar() {
        imgSenapi = "";
        fechaHoy = "";
        horaHoy = "";
        marcaX = "";
        sm = "";
        fechasm = "";
        horasm = "";
        clase = "";
        tipoMarca = "";
        tipoSigno = "";
        descripSigno = "";
        productos = "";
        prioridad = "";
        tipoPrioridad = "";
        lugarPrioridad = "";
        fechaPrioridad = "";
        andina = null;
        situacionActual = "";
        observacion = "";
        noPublicacion = null;
        noGaceta = null;
        fechaPub = "";
        noRegistro = "";
        noResolucion = "";
        fechaRegistro = "";
        noRenovacion = null;
        resoRenovacion = null;
        fechaResoRenovacion = null;
        imagenes = null;
    }

    public JRDataSource getDatos(List<?> listaObjeto) {
        return new JRBeanCollectionDataSource(listaObjeto);
    }

    /**
     * Metodo para generar el reporte en formato pdf
     *
     * Creado: Ruben Ramirez Fecha: 15/10/2016
     *
     */
    public void PDFSD(Map<String, Object> params, String jasperPath, String fileName) throws JRException, IOException {
        String relativeWebPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath(jasperPath);
        File file = new File(relativeWebPath);
        JasperPrint print = JasperFillManager.fillReport(file.getPath(), params, new JREmptyDataSource());
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-Disposition", "attachment; filename=" + fileName);
        ServletOutputStream stream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(print, stream);
        FacesContext.getCurrentInstance().responseComplete();
    }

    ///////Metodos para el historial de modificaciones
    public void abrirHistorialModificacion() {
        if (sigSignoMarca.getIdSignoMarca() != null) {

            Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
            sessionMap.put("signoMarca", sigSignoMarca);

            Map<String, Object> options = new HashMap<String, Object>();
            options.put("closable", true);
            options.put("resizable", false);
            options.put("height", 640);
            options.put("width", 1155);
            options.put("contentWidth", "100%");
            options.put("contentHeight", "100%");
            options.put("modal", true);

//            Map<String, List<String>> var = new HashMap<String, List<String>>();
//            List<String> params = new ArrayList();
//            params.add("1");
//            params.add(EnumPrefijoTablas.SIGNO.getCodigo());
//            params.add(sigSignoMarca.getIdSignoMarca().toString());
//            params.add("Enviar el SM EN STRING");
//            var.put("datosEnviados", params);
            RequestContext.getCurrentInstance().openDialog("abrirHistorialModificacion", options, null);
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("La solicitud de registro de marca no esta registrada"));
        }
    }

    public void botonesHistorialModificacion(SigSignoMarca signomarca) {
        if (sigSignoMarca.getIdSignoMarca() != null) {
            Boolean resultadoCN[] = modModificacionService.existe_ModModificacion_sm_publicacion_registro_or(sigSignoMarca.getSm(), sigSignoMarca.getNumeroPublicacion(), sigSignoMarca.getNumeroRegistro(), sigSignoMarca.getSerieRegistro(), EnumTipoModificacion.CAMBIO_NOMBRE.getCodigo());
            Boolean resultadoCD[] = modModificacionService.existe_ModModificacion_sm_publicacion_registro_or(sigSignoMarca.getSm(), sigSignoMarca.getNumeroPublicacion(), sigSignoMarca.getNumeroRegistro(), sigSignoMarca.getSerieRegistro(), EnumTipoModificacion.CAMBIO_DOMICILIO.getCodigo());
            Boolean resultadoST[] = modModificacionService.existe_ModModificacion_sm_publicacion_registro_or(sigSignoMarca.getSm(), sigSignoMarca.getNumeroPublicacion(), sigSignoMarca.getNumeroRegistro(), sigSignoMarca.getSerieRegistro(), EnumTipoModificacion.TRANSFERENCIA.getCodigo());
            Boolean resultadoSF[] = modModificacionService.existe_ModModificacion_sm_publicacion_registro_or(sigSignoMarca.getSm(), sigSignoMarca.getNumeroPublicacion(), sigSignoMarca.getNumeroRegistro(), sigSignoMarca.getSerieRegistro(), EnumTipoModificacion.FUSION.getCodigo());
            Boolean resultadoLU[] = modModificacionService.existe_ModModificacion_sm_publicacion_registro_or(sigSignoMarca.getSm(), sigSignoMarca.getNumeroPublicacion(), sigSignoMarca.getNumeroRegistro(), sigSignoMarca.getSerieRegistro(), EnumTipoModificacion.LICENCIA_DE_USO.getCodigo());

            botonCambioNombreDisabled = !resultadoCN[0];
            botonCambioDomicilioDisabled = !resultadoCD[0];
            botonTransferenciaDisabled = !resultadoST[0];
            botonFusionDisabled = !resultadoSF[0];
            botonLicenciaDisabled = !resultadoLU[0];

            if (resultadoCN[0]) {
                backgroundCN = "#fcfc34";
            }
            if (resultadoCD[0]) {
                backgroundCD = "#fcfc34";
            }
            if (resultadoST[0]) {
                backgroundST = "#fcfc34";
            }
            if (resultadoSF[0]) {
                backgroundSF = "#fcfc34";
            }
            if (resultadoLU[0]) {
                backgroundLU = "#fcfc34";
            }

            if (resultadoCN[1]) {
                backgroundCN = "#99FF33";
            }
            if (resultadoCD[1]) {
                backgroundCD = "#99FF33";
            }
            if (resultadoST[1]) {
                backgroundST = "#99FF33";
            }
            if (resultadoSF[1]) {
                backgroundSF = "#99FF33";
            }
            if (resultadoLU[1]) {
                backgroundLU = "#99FF33";
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("La solicitud de registro de marca no esta registrada"));
        }
    }

    public void iniciarBotonesModificacion() {
        botonCambioNombreDisabled = true;
        botonCambioDomicilioDisabled = true;
        botonTransferenciaDisabled = true;
        botonFusionDisabled = true;
        botonLicenciaDisabled = true;
        backgroundCN = "#c7cccd";
        backgroundCD = "#c7cccd";
        backgroundST = "#c7cccd";
        backgroundSF = "#c7cccd";
        backgroundLU = "#c7cccd";
    }

    public void reset() {
        sigSignoMarca = new SigSignoMarca();
        lstImagenPojos = new ArrayList<ImagenPojo>();
        lstSigSignoClaseNiza = new ArrayList<SigSignoClaseNiza>();
        lstSolicitantes = new ArrayList<SigSolicitanteApoderado>();
        lstApoderados = new ArrayList<SigSolicitanteApoderado>();
        lstSigTipoSigno = new ArrayList<SigTipoSigno>();
        sigDireccionNotificacion = new SigDireccionDeNotificacion();
        oposicionAndina = new SigPrioridadPreferencia();
        prioridadExtranjera = new SigPrioridadPreferencia();
        prioridadExposicion = new SigPrioridadPreferencia();
        fechaSolicitud = new Date();
        tipoSolicitante = "";
        panelSolicitanteRendered = true;
        nombreBotonOpo = "Oposición";
        backgroundOPO = "#c7cccd";
        botonOposicionDisabled = true;
        backgroundCN = "#c7cccd";
        backgroundCD = "#c7cccd";
        backgroundST = "#c7cccd";
        backgroundSF = "#c7cccd";
        backgroundLU = "#c7cccd";
        botonCambioNombreDisabled = true;
        botonCambioDomicilioDisabled = true;
        botonTransferenciaDisabled = true;
        botonFusionDisabled = true;
        botonLicenciaDisabled = true;
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
        buscarSMRendered = true;
        buscarPURendered = false;
        buscarRERendered = false;
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

    public void abrirHistorialRenovacion() {
        lstHistorialRenovacionModificacion.clear();
        lstRenSolicititudRenovacionNoConc.clear();
        lstHistorialRenovacionModificacion = busquedaHistorialRenovacionService.lista_HistorialRenovacion_sm_publicacion_registro_or(sigSignoMarca.getSm(), sigSignoMarca.getNumeroPublicacion(), sigSignoMarca.getNumeroRegistro(), sigSignoMarca.getSerieRegistro());
        lstRenSolicititudRenovacionNoConc = renSolicitudRenovacionService.lista_rensolicitudrenovacion_noconcedidas(sigSignoMarca.getSm(), sigSignoMarca.getNumeroRegistro(), sigSignoMarca.getSerieRegistro());
    }

    public String ordenModificacionRenovacionLiteral(Integer numero) {
        String literal = "";
        literal = renRenovacionService.numeroOrdinal(numero);
        return literal;
    }

    public Long claseXid(Integer id) {
        ClaseNiza claseniza = claseNizaService.listarClaseNiza_id((long) id);
        Long numeroClase = claseniza.getNumeroClaseNiza();
        return numeroClase;
    }

    //<editor-fold defaultstate="collapsed" desc="Metodos para el tratamiento de imagenes">
    public void abrirDialogoAgregarModificarImagen() {
        //Primero se debe validar que en cualquier caso: adiciona, modificar, consultar. debe estar llenado el campo sm su primera casilla.
        //
        if (valorNumeroExpediente.equals("") || valorNumeroExpediente == null) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Debe llenar el número SM en Datos del Signo ", "");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            //Pregunta si tienes algun  registro en sigsignomarca si no tiene signiifca que el usuario esta llenando uno nuevo  y deberia estar en la opcion ADicionar
            if (this.sigSignoMarca.getIdSignoMarca() != null) {
                Map<String, Object> opcionesDialogo = new HashMap<String, Object>();
                opcionesDialogo.put("resizable", false);
                opcionesDialogo.put("draggable", true);
                opcionesDialogo.put("modal", true);
                opcionesDialogo.put("height", 600);
                opcionesDialogo.put("width", 800);
                opcionesDialogo.put("contentWidth", "100%");
                opcionesDialogo.put("contentHeight", "100%");
                opcionesDialogo.put("closable", true);

                Map<String, List<String>> var = new HashMap<String, List<String>>();
                List<String> params = new ArrayList();
                params.add("si hay id");//hace referencia que si tiene un idsignomarca solo es ara huvicarme yo
                if (!lstImagenPojos.isEmpty()) {
                    params.add(lstImagenPojos.get(0).getSigLogoTipo().getNombreArchivo());
                } else {

                    params.add("no hay imagen");
                }
                params.add(this.sigSignoMarca.getIdSignoMarca().toString());
                var.put("datosBusLogo", params);
                RequestContext.getCurrentInstance().openDialog("agregarImagenSigno", opcionesDialogo, var);
            } else {
                Map<String, Object> opcionesDialogo = new HashMap<String, Object>();
                opcionesDialogo.put("resizable", false);
                opcionesDialogo.put("draggable", true);
                opcionesDialogo.put("modal", true);
                opcionesDialogo.put("contentHeight", 420);
                opcionesDialogo.put("contentWidth", 770);
                opcionesDialogo.put("closable", false);

                Map<String, List<String>> var = new HashMap<String, List<String>>();
                List<String> params = new ArrayList();
                params.add("no hay id");
                if (!lstImagenPojos.isEmpty()) {
                    params.add(lstImagenPojos.get(0).getSigLogoTipo().getNombreArchivo());
                } else {
                    params.add("no hay imagen");
                }
                var.put("datosBusLogo", params);

                RequestContext.getCurrentInstance().openDialog("agregarImagenSigno", opcionesDialogo, var);
                //manda en blanco
            }
        }

    }

    public void terminaDialogoImagen(SelectEvent event) throws Exception {

        //capturar el objeto enviado del dialogo
//        ImagenPojo imagenPojoDialogo;
        if (event.getObject() != "Exit") {
            if (!lstImagenPojos.isEmpty()) {
                lstImagenPojos.clear();
            }

            imagenPojo = (ImagenPojo) event.getObject();

            //  if(imagenPojo.getSigLogoTipo().getNombreArchivo() != null || !imagenPojo.getSigLogoTipo().getNombreArchivo().equals(""))
            // {            
            lstImagenPojos.add(imagenPojo);
            // }

        } else {
            //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cerrado"));

        }
    }

    public void terminaDialogoNotifica(SelectEvent event) throws Exception {

        SigSignoMarca signo = new SigSignoMarca();
        if (buscarSMRendered) {
            try {
                //Obtener el signo marca determinado, y cargar variables
                if (extensionExpediente.equals("00")) {
                    extensionExpediente = "";
                }

                if (validarNumeroGestionExtension()) {
                    signo = expedienteService.obtenerSigsignomarca(numeroExpediente, gestionExpediente, extensionExpediente);
                    if (signo.getIdSignoMarca() != null) {

                    } else {
                        FacesContext.getCurrentInstance().addMessage("registrationForm:userName",
                                new FacesMessage(FacesMessage.SEVERITY_WARN, "No se encontraron datos para la busqueda",
                                        "Mensaje 2 *** "));
                    }

                }

            } catch (Exception ex) {

                Logger.getLogger(ExamenSignosBean.class
                        .getName()).log(Level.SEVERE, null, ex);
            }

        }

        //buscar por publicacion
        if (buscarPURendered) {
            try {
                if (validarPublicacion()) {
                    signo = sigSignoMarcaService.listaSigSignoMarcaXPublicacion(Long.valueOf(this.numeroExpediente));
                    if (signo.getIdSignoMarca() != null) {

                    } else {
                        FacesContext.getCurrentInstance().addMessage("registrationForm:userName",
                                new FacesMessage(FacesMessage.SEVERITY_WARN, "No se encontraron datos para la busqueda",
                                        "Mensaje 2 *** "));
                    }
                }

            } catch (Exception ex) {
                Logger.getLogger(ExamenSignosBean.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }

        //buscar por registro
        if (buscarRERendered) {
            if (validarRegistro()) {
                signo = sigSignoMarcaService.listaSigSignoMarcaXRegistro(Long.valueOf(this.numeroExpediente), this.extensionExpediente, "");
                if (signo != null) {

                } else {
                    FacesContext.getCurrentInstance().addMessage("registrationForm:userName",
                            new FacesMessage(FacesMessage.SEVERITY_WARN, "No se encontraron datos para la busqueda",
                                    "Mensaje 2 *** "));
                }
            }
        }
        this.valorEstadoMarca = signo.getEstadoMarca();
        //this.valorEstadoMarca="NOT";
/*        if (event.getObject() != "Exit") {
         if (!lstImagenPojos.isEmpty()) {
         lstImagenPojos.clear();
         }
         imagenPojo = (ImagenPojo) event.getObject();
         imagenPojo.getSigLogoTipo().setIdSignoMarca(sigSignoMarca.getIdSignoMarca());
         lstImagenPojos.add(imagenPojo);
           
         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(imagenPojo.getSigLogoTipo().getNombreArchivo()));
         } else {
 
 
         }*/
    }

    public void guardarObservacion() {
        if (!this.observacionTramite.getDescripcion().trim().equals("")) {
            try {
             //   observacionTramiteService.regObservacion(areaRegistro, 1L, 1L, 1L, Boolean.FALSE, "etapa", observacion);
                //refrescar el listado de las observaciones

                //listaObservacion = observacionTramiteService.obtListadoObservacionPorTramite(areaRegistro, 1L);
                //limpiar la variable de observacion
                Date fechaServidor = comunService.obtenerFechaHoraServidor(1L);
                observacionTramiteService.eliminarObservacion(sigSignoMarca.getIdSignoMarca());
                //Seguimiento seguimiento = seguimientoService.obtener_Ultimo_seguimiento_signo(sigSignoMarca.getIdSignoMarca());

                observacionTramite.setId(sigSignoMarca.getIdSignoMarca());
                observacionTramite.setIdUsuario(idUsuario);
                observacionTramite.setIdLogTrans(logtrans);
                observacionTramite.setEditable(Boolean.FALSE);
                observacionTramite.setFechaObservacion(fechaServidor);
                //Etapa etapa = etapaService.etapaXIdEtapa(seguimiento.getIdSeguimiento());
                observacionTramite.setEtapaTramite("");
                //observacionTramite.setDescripcion(observacion);
                observacionTramite.setEstado("AC");

                observacionTramiteService.guardar_modificar_listar_ObservacionTramite(observacionTramite, "SIG", 1);
                //listaObservacion = observacionTramiteService.obtListadoObservacionPorTramite(areaRegistro, idarea);

                FacesContext.getCurrentInstance().addMessage("",
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Datos de observaciones fueron agregados correctamente", ""));

            } catch (Exception ex) {
                Logger.getLogger(ObservacionTramiteBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public void modificarObservacion() {
        //observacion = observacionTramite.getDescripcion();
        //Metodo que realiza la actualizacion
        //observacionTramite.setEditable(Boolean.FALSE);

        try {

            Date fechaSistema = comunService.obtenerFechaHoraServidor(1L);
            this.observacionTramite.setId(sigSignoMarca.getIdSignoMarca());
            this.observacionTramite.setFechaObservacion(fechaSistema);
            this.observacionTramite.setIdUsuario(idUsuario);
            this.observacionTramite.setIdLogTrans(logtrans);
            this.observacionTramite.setEstado("AC");
            this.observacionTramite.setEtapaTramite("VENT");
            observacionTramiteService.guardar_modificar_listar_ObservacionTramite(observacionTramite, "SIG", 1);

            FacesContext.getCurrentInstance().addMessage("",
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Datos de observaciones fueron modificados correctamente", ""));
        } catch (Exception ex) {
            Logger.getLogger(ObservacionTramiteBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void accionEliminarImagen() {
        if (!lstImagenPojos.isEmpty()) {
            lstImagenPojos.clear();
        }
    }

    //</editor-fold>
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
    //<editor-fold defaultstate="collapsed" desc="Definicion de servicios y atributos examenSignosBean">
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

    public Map<String, Object> getParametros() {
        return parametros;
    }

    public void setParametros(Map<String, Object> parametros) {
        this.parametros = parametros;
    }

    public String getFechaHoy() {
        return fechaHoy;
    }

    public void setFechaHoy(String fechaHoy) {
        this.fechaHoy = fechaHoy;
    }

    public String getHoraHoy() {
        return horaHoy;
    }

    public void setHoraHoy(String horaHoy) {
        this.horaHoy = horaHoy;
    }

    public String getMarcaX() {
        return marcaX;
    }

    public void setMarcaX(String marcaX) {
        this.marcaX = marcaX;
    }

    public String getSm() {
        return sm;
    }

    public void setSm(String sm) {
        this.sm = sm;
    }

    public String getFechasm() {
        return fechasm;
    }

    public void setFechasm(String fechasm) {
        this.fechasm = fechasm;
    }

    public String getHorasm() {
        return horasm;
    }

    public void setHorasm(String horasm) {
        this.horasm = horasm;
    }

    public String getClase() {
        return clase;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }

    public String getTipoMarca() {
        return tipoMarca;
    }

    public void setTipoMarca(String tipoMarca) {
        this.tipoMarca = tipoMarca;
    }

    public String getTipoSigno() {
        return tipoSigno;
    }

    public void setTipoSigno(String tipoSigno) {
        this.tipoSigno = tipoSigno;
    }

    public String getDescripSigno() {
        return descripSigno;
    }

    public void setDescripSigno(String descripSigno) {
        this.descripSigno = descripSigno;
    }

    public String getProductos() {
        return productos;
    }

    public void setProductos(String productos) {
        this.productos = productos;
    }

    public String getImgSenapi() {
        return imgSenapi;
    }

    public void setImgSenapi(String imgSenapi) {
        this.imgSenapi = imgSenapi;
    }

    public String getTipoPrioridad() {
        return tipoPrioridad;
    }

    public void setTipoPrioridad(String tipoPrioridad) {
        this.tipoPrioridad = tipoPrioridad;
    }

    public String getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }

    public String getLugarPrioridad() {
        return lugarPrioridad;
    }

    public void setLugarPrioridad(String lugarPrioridad) {
        this.lugarPrioridad = lugarPrioridad;
    }

    public String getFechaPrioridad() {
        return fechaPrioridad;
    }

    public void setFechaPrioridad(String fechaPrioridad) {
        this.fechaPrioridad = fechaPrioridad;
    }

    public String getAndina() {
        return andina;
    }

    public void setAndina(String andina) {
        this.andina = andina;
    }

    public String getSituacionActual() {
        return situacionActual;
    }

    public void setSituacionActual(String situacionActual) {
        this.situacionActual = situacionActual;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getFechaPub() {
        return fechaPub;
    }

    public void setFechaPub(String fechaPub) {
        this.fechaPub = fechaPub;
    }

    public String getNoRegistro() {
        return noRegistro;
    }

    public void setNoRegistro(String noRegistro) {
        this.noRegistro = noRegistro;
    }

    public String getNoResolucion() {
        return noResolucion;
    }

    public void setNoResolucion(String noResolucion) {
        this.noResolucion = noResolucion;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
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

    public Boolean getBotonCambioNombreDisabled() {
        return botonCambioNombreDisabled;
    }

    public void setBotonCambioNombreDisabled(Boolean botonCambioNombreDisabled) {
        this.botonCambioNombreDisabled = botonCambioNombreDisabled;
    }

    public Boolean getBotonCambioDomicilioDisabled() {
        return botonCambioDomicilioDisabled;
    }

    public void setBotonCambioDomicilioDisabled(Boolean botonCambioDomicilioDisabled) {
        this.botonCambioDomicilioDisabled = botonCambioDomicilioDisabled;
    }

    public Boolean getBotonTransferenciaDisabled() {
        return botonTransferenciaDisabled;
    }

    public void setBotonTransferenciaDisabled(Boolean botonTransferenciaDisabled) {
        this.botonTransferenciaDisabled = botonTransferenciaDisabled;
    }

    public Boolean getBotonFusionDisabled() {
        return botonFusionDisabled;
    }

    public void setBotonFusionDisabled(Boolean botonFusionDisabled) {
        this.botonFusionDisabled = botonFusionDisabled;
    }

    public Boolean getBotonLicenciaDisabled() {
        return botonLicenciaDisabled;
    }

    public void setBotonLicenciaDisabled(Boolean botonLicenciaDisabled) {
        this.botonLicenciaDisabled = botonLicenciaDisabled;
    }

    public String getBackgroundCN() {
        return backgroundCN;
    }

    public void setBackgroundCN(String backgroundCN) {
        this.backgroundCN = backgroundCN;
    }

    public String getBackgroundCD() {
        return backgroundCD;
    }

    public void setBackgroundCD(String backgroundCD) {
        this.backgroundCD = backgroundCD;
    }

    public String getBackgroundST() {
        return backgroundST;
    }

    public void setBackgroundST(String backgroundST) {
        this.backgroundST = backgroundST;
    }

    public String getBackgroundSF() {
        return backgroundSF;
    }

    public void setBackgroundSF(String backgroundSF) {
        this.backgroundSF = backgroundSF;
    }

    public String getBackgroundLU() {
        return backgroundLU;
    }

    public void setBackgroundLU(String backgroundLU) {
        this.backgroundLU = backgroundLU;
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

//</editor-fold>    
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

    public void setUsuarioService(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    public EtapaService getEtapaService() {
        return etapaService;
    }

    public void setEtapaService(EtapaService etapaService) {
        this.etapaService = etapaService;
    }

    public Date getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(Date fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public Boolean getBtnSeguimientoRecepcionarRendered() {
        return btnSeguimientoRecepcionarRendered;
    }

    public void setBtnSeguimientoRecepcionarRendered(Boolean btnSeguimientoRecepcionarRendered) {
        this.btnSeguimientoRecepcionarRendered = btnSeguimientoRecepcionarRendered;
    }

    public Boolean getBtnSeguimientoFinalizarRendered() {
        return btnSeguimientoFinalizarRendered;
    }

    public void setBtnSeguimientoFinalizarRendered(Boolean btnSeguimientoFinalizarRendered) {
        this.btnSeguimientoFinalizarRendered = btnSeguimientoFinalizarRendered;
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

    public boolean isPintaAdiciona() {
        return pintaAdiciona;
    }

    public void setPintaAdiciona(boolean pintaAdiciona) {
        this.pintaAdiciona = pintaAdiciona;
    }

    public List<Dominio> getLstTipoDocumento() {
        return lstTipoDocumento;
    }

    public void setLstTipoDocumento(List<Dominio> lstTipoDocumento) {
        this.lstTipoDocumento = lstTipoDocumento;
    }

    public Long getNoPublicacion() {
        return noPublicacion;
    }

    public void setNoPublicacion(Long noPublicacion) {
        this.noPublicacion = noPublicacion;
    }

    public Long getNoGaceta() {
        return noGaceta;
    }

    public void setNoGaceta(Long noGaceta) {
        this.noGaceta = noGaceta;
    }

    public Integer getNoRenovacion() {
        return noRenovacion;
    }

    public void setNoRenovacion(Integer noRenovacion) {
        this.noRenovacion = noRenovacion;
    }

    public Long getResoRenovacion() {
        return resoRenovacion;
    }

    public void setResoRenovacion(Long resoRenovacion) {
        this.resoRenovacion = resoRenovacion;
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

    public boolean isPintaModifica() {
        return pintaModifica;
    }

    public void setPintaModifica(boolean pintaModifica) {
        this.pintaModifica = pintaModifica;
    }

    public List<HistorialRenovacionModificacionPojo> getLstHistorialRenovacionModificacionPojo() {
        return lstHistorialRenovacionModificacionPojo;
    }

    public void setLstHistorialRenovacionModificacionPojo(List<HistorialRenovacionModificacionPojo> lstHistorialRenovacionModificacionPojo) {
        this.lstHistorialRenovacionModificacionPojo = lstHistorialRenovacionModificacionPojo;
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

    public SigSignoMarca getSigSignoMarcaRepo() {
        return sigSignoMarcaRepo;
    }

    public void setSigSignoMarcaRepo(SigSignoMarca sigSignoMarcaRepo) {
        this.sigSignoMarcaRepo = sigSignoMarcaRepo;
    }

    public FlujoTiempoService getFlujoTiempoService() {
        return flujoTiempoService;
    }

    public void setFlujoTiempoService(FlujoTiempoService flujoTiempoService) {
        this.flujoTiempoService = flujoTiempoService;
    }

    public Boolean[] getRecepcionarFinalizar() {
        return RecepcionarFinalizar;
    }

    public void setRecepcionarFinalizar(Boolean[] RecepcionarFinalizar) {
        this.RecepcionarFinalizar = RecepcionarFinalizar;
    }

    public FlujoSeguimientoService getFlujoSeguimientoService() {
        return flujoSeguimientoService;
    }

    public void setFlujoSeguimientoService(FlujoSeguimientoService flujoSeguimientoService) {
        this.flujoSeguimientoService = flujoSeguimientoService;
    }

    public HistorialService getHistorialService() {
        return historialService;
    }

    public void setHistorialService(HistorialService historialService) {
        this.historialService = historialService;
    }

    public Long getIdSignoMarca() {
        return idSignoMarca;
    }

    public void setIdSignoMarca(Long idSignoMarca) {
        this.idSignoMarca = idSignoMarca;
    }

    public Boolean getBtnSubsanacion() {
        return btnSubsanacion;
    }

    public void setBtnSubsanacion(Boolean btnSubsanacion) {
        this.btnSubsanacion = btnSubsanacion;

    }

    public BusquedaHistorialRenovacionService getBusquedaHistorialRenovacionService() {
        return busquedaHistorialRenovacionService;
    }

    public void setBusquedaHistorialRenovacionService(BusquedaHistorialRenovacionService busquedaHistorialRenovacionService) {
        this.busquedaHistorialRenovacionService = busquedaHistorialRenovacionService;
    }

    public List<BusquedaHistorialRenovacion> getLstHistorialRenovacionModificacion() {
        return lstHistorialRenovacionModificacion;
    }

    public void setLstHistorialRenovacionModificacion(List<BusquedaHistorialRenovacion> lstHistorialRenovacionModificacion) {
        this.lstHistorialRenovacionModificacion = lstHistorialRenovacionModificacion;
    }

    public RenRenovacionService getRenRenovacionService() {
        return renRenovacionService;
    }

    public void setRenRenovacionService(RenRenovacionService renRenovacionService) {
        this.renRenovacionService = renRenovacionService;
    }

    public List<RenSolicitudRenovacion> getLstRenSolicititudRenovacionNoConc() {
        return lstRenSolicititudRenovacionNoConc;
    }

    public void setLstRenSolicititudRenovacionNoConc(List<RenSolicitudRenovacion> lstRenSolicititudRenovacionNoConc) {
        this.lstRenSolicititudRenovacionNoConc = lstRenSolicititudRenovacionNoConc;
    }

    public RenSolicitudRenovacionService getRenSolicitudRenovacionService() {
        return renSolicitudRenovacionService;
    }

    public void setRenSolicitudRenovacionService(RenSolicitudRenovacionService renSolicitudRenovacionService) {
        this.renSolicitudRenovacionService = renSolicitudRenovacionService;
    }

    public ClaseNizaService getClaseNizaService() {
        return claseNizaService;
    }

    public void setClaseNizaService(ClaseNizaService claseNizaService) {
        this.claseNizaService = claseNizaService;
    }

    public String getFechaResoRenovacion() {
        return fechaResoRenovacion;
    }

    public void setFechaResoRenovacion(String fechaResoRenovacion) {
        this.fechaResoRenovacion = fechaResoRenovacion;

    }

}
