/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.entidades.bean.renovacion;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import org.primefaces.event.SelectEvent;
import snp.gob.bo.entidades.bean.common.AbstractManagedBean;
import snp.gob.bo.gimodel.entidad.ClaseNiza;
import snp.gob.bo.gimodel.entidad.Correlativo;
import snp.gob.bo.gimodel.entidad.Dominio;
import snp.gob.bo.gimodel.entidad.Etapa;
import snp.gob.bo.gimodel.entidad.ObservacionTramite;
import snp.gob.bo.gimodel.entidad.Regional;
import snp.gob.bo.gimodel.entidad.RenDireccionDeNotificacion;
import snp.gob.bo.gimodel.entidad.RenRenovacion;
import snp.gob.bo.gimodel.entidad.RenResolucion;
import snp.gob.bo.gimodel.entidad.RenSolicitanteApoderado;
import snp.gob.bo.gimodel.entidad.RenSolicitudRenovacion;
import snp.gob.bo.gimodel.entidad.RenTipoSigno;
import snp.gob.bo.gimodel.entidad.RenTitularRegistrado;
import snp.gob.bo.gimodel.entidad.Seguimiento;
import snp.gob.bo.gimodel.entidad.SigSignoMarca;
import snp.gob.bo.gimodel.entidad.Usuario;
import snp.gob.bo.gimodel.entidad.ObjetoEliminadoGenerico;
import snp.gob.bo.gimodel.enums.EnumEstadoRenovacion;
import snp.gob.bo.gimodel.enums.EnumNombreDominio;
import snp.gob.bo.gimodel.enums.EnumTipoFormulario;
import snp.gob.bo.gimodel.enums.EnumTipoPersona;
import snp.gob.bo.gimodel.pojo.OrdinalesPojo;
import snp.gob.bo.gimodel.servicio.ClaseNizaService;
import snp.gob.bo.gimodel.servicio.ComunService;
import snp.gob.bo.gimodel.servicio.DominioService;
import snp.gob.bo.gimodel.servicio.EtapaService;
import snp.gob.bo.gimodel.servicio.ExpedienteService;
import snp.gob.bo.gimodel.servicio.FlujoSeguimientoService;
import snp.gob.bo.gimodel.servicio.FlujoTiempoService;
import snp.gob.bo.gimodel.servicio.HistorialService;
import snp.gob.bo.gimodel.servicio.LogTransService;
import snp.gob.bo.gimodel.servicio.ModDireccionDeNotificacionService;
import snp.gob.bo.gimodel.servicio.RegionalService;
import snp.gob.bo.gimodel.servicio.RenDireccionDeNotificacionService;
import snp.gob.bo.gimodel.servicio.RenRenovacionService;
import snp.gob.bo.gimodel.servicio.RenResolucionService;
import snp.gob.bo.gimodel.servicio.RenSolicitanteApoderadoService;
import snp.gob.bo.gimodel.servicio.RenSolicitudRenovacionService;
import snp.gob.bo.gimodel.servicio.RenTipoSignoService;
import snp.gob.bo.gimodel.servicio.RenTitularRegistradoService;
import snp.gob.bo.gimodel.servicio.SeguimientoService;
import snp.gob.bo.gimodel.servicio.SigSignoClaseNizaService;
import snp.gob.bo.gimodel.servicio.SigSignoMarcaService;
import snp.gob.bo.gimodel.servicio.SigSolicitanteApoderadoService;
import snp.gob.bo.gimodel.servicio.SigTipoSignoService;
import snp.gob.bo.gimodel.servicio.UsuarioService;
import snp.gob.bo.gimodel.servicio.ObservacionTramiteService;
import snp.gob.bo.gimodel.solicitudes.entidades.Apoderados;
import snp.gob.bo.gimodel.solicitudes.entidades.DireccionNotificaciones;
import snp.gob.bo.gimodel.solicitudes.entidades.FormularioPI104;
import snp.gob.bo.gimodel.solicitudes.entidades.RenTipoSignos;
import snp.gob.bo.gimodel.solicitudes.entidades.RenTitularRegistrados;
import snp.gob.bo.gimodel.solicitudes.entidades.Solicitantes;
import snp.gob.bo.gimodel.solicitudes.entidades.SolicitudRenovaciones;
import snp.gob.bo.gimodel.solicitudes.servicio.FormularioPI104Service;
import snp.gob.bo.gimodel.solicitudes.servicio.FormularioService;
import snp.gob.bo.giview.ventanilla.IngresoSolicitudTramiteBean;

/**
 *
 * @author ChanoRojas
 */
@ManagedBean(name = "examenRenSubsanacionBean")
@ViewScoped
public class ExamenRenSubsanacionBean extends AbstractManagedBean implements Serializable {

//<editor-fold defaultstate="collapsed" desc="---------------INYECCION DE SERVCIOS----------------">
    //parametricas
    @ManagedProperty("#{dominioService}")
    private DominioService dominioService;

    @ManagedProperty("#{renTipoSignoService}")
    private RenTipoSignoService renTipoSignoService;

    @ManagedProperty("#{renRenovacionService}")
    private RenRenovacionService renRenovacionService;

    @ManagedProperty("#{regionalService}")
    private RegionalService regionalService;

    @ManagedProperty("#{renSolicitudRenovacionService}")
    private RenSolicitudRenovacionService renSolicitudRenovacionService;

    @ManagedProperty("#{renSolicitanteApoderadoService}")
    private RenSolicitanteApoderadoService renSolicitanteApoderadoService;

    @ManagedProperty("#{renDireccionDeNotificacionService}")
    private RenDireccionDeNotificacionService renDireccionDeNotificacionService;

    @ManagedProperty("#{renResolucionService}")
    private RenResolucionService renResolucionService;

    @ManagedProperty("#{renTitularRegistradoService}")
    private RenTitularRegistradoService renTitularRegistradoService;

    @ManagedProperty("#{modDireccionDeNotificacionService}")
    private ModDireccionDeNotificacionService modDireccionDeNotificacionService;

    @ManagedProperty("#{sigSignoMarcaService}")
    private SigSignoMarcaService sigSignoMarcaService;

    @ManagedProperty("#{comunService}")
    private ComunService comunService;

    @ManagedProperty("#{expedienteService}")
    private ExpedienteService expedienteService;

    @ManagedProperty("#{seguimientoService}")
    private SeguimientoService seguimientoService;

    @ManagedProperty("#{usuarioService}")
    private UsuarioService usuarioService;

    @ManagedProperty("#{sigSignoClaseNizaService}")
    private SigSignoClaseNizaService sigSignoClaseNizaService;

    @ManagedProperty("#{sigTipoSignoService}")
    private SigTipoSignoService sigTipoSignoService;

    @ManagedProperty("#{sigSolicitanteApoderadoService}")
    private SigSolicitanteApoderadoService sigSolicitanteApoderadoService;

    @ManagedProperty("#{logTransService}")
    private LogTransService logTransService;

    @ManagedProperty("#{flujoSeguimientoService}")
    private FlujoSeguimientoService flujoSeguimientoService;

    @ManagedProperty("#{observacionTramiteService}")
    private ObservacionTramiteService observacionTramiteService;

    @ManagedProperty("#{etapaService}")
    private EtapaService etapaService;

    @ManagedProperty(value = "#{flujoTiempoService}")
    private FlujoTiempoService flujoTiempoService;

    @ManagedProperty(value = "#{historialService}")
    private HistorialService historialService;

    @ManagedProperty(value = "#{formularioService}")
    private FormularioService formularioService;

    @ManagedProperty(value = "#{formularioPI104Service}")
    private FormularioPI104Service formularioPI104Service;

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="-----------------VARIABLES DE ENTORNO--------------">
    private RenRenovacion renRenovacion;
    private SigSignoMarca sigSignoMarca;
    private String numeroFormulario;
    private List<RenRenovacion> listaRenovacion = new ArrayList<>();
    private RenSolicitudRenovacion renSolicitudRenovacion;
    private RenSolicitudRenovacion renovacion;
    private List<RenSolicitudRenovacion> listaSolicitudRenovacion = new ArrayList<>();
    private RenSolicitanteApoderado renSolicitanteApoderado;
    private RenSolicitanteApoderado renSolicitante;
    private RenSolicitanteApoderado renApoderado;
    private RenSolicitanteApoderado renSolicitanteApoderadoSelect;
    private List<RenSolicitanteApoderado> listarenSolicitanteApoderado = new ArrayList<>();
    private List<RenSolicitanteApoderado> listarenApoderado = new ArrayList<>();
    private List<RenSolicitanteApoderado> listarenSolicitante = new ArrayList<>();
    private List<Solicitantes> listaRenSolicitantesSipi = new ArrayList<>();
    private List<Apoderados> listaRenApoderadosSipi = new ArrayList<>();
    private DireccionNotificaciones direccionNotificacionSipi = new DireccionNotificaciones();
    private SolicitudRenovaciones solicitudRenovacionesSipi = new SolicitudRenovaciones();
    private RenDireccionDeNotificacion renDireccionDeNotificacion;
    private List<RenDireccionDeNotificacion> listadireccionDeNotificacion = new ArrayList<>();
    private List<RenTitularRegistrado> listarenTitularRegistrado = new ArrayList<>();
    private List<RenTitularRegistrado> listaTitularRegistrado = new ArrayList<>();
    private List<RenTitularRegistrados> listaTitularRegistradoSipi = new ArrayList<>();
    private RenTitularRegistrado renTitularRegistrado;
    private RenResolucion renResolucion;
    private List<RenResolucion> listarenResolucion = new ArrayList<>();
    private Long numeroSr;
    private Integer gestion;
    private String usuario;
    private RenTitularRegistrado titularRegistradoSelect = new RenTitularRegistrado();
    private List<OrdinalesPojo> listaOrdinales = new ArrayList<>();
    private Correlativo correlativo;
    private ObjetoEliminadoGenerico objetoEliminadoGenerico;
    //listado de parametricas
    private List<Dominio> listaTipoRecibo = new ArrayList<>();
    private List<Dominio> listaTipoTitulo = new ArrayList<>();
    private List<Dominio> listaCiudadOficina = new ArrayList<>();
    private List<Dominio> listaSerieRegistro = new ArrayList<>();
    private List<Dominio> listaSerieRenovacion = new ArrayList<>();
    private List<Dominio> listaTipoSigno = new ArrayList<>();
    private List<Dominio> listaTipoMarca = new ArrayList<>();
    private List<Dominio> lstTipoUbicacion = new ArrayList<>();
    private List<Dominio> lstTipoSituacion = new ArrayList<>();
    private List<Dominio> listaLugarExpedicion = new ArrayList<>();
    private List<Dominio> listaPais = new ArrayList<>();
    private String oficina;
    private String ubicacion;
    private String situacion;
    private String serieTitulo;
    private String serieRecibo;
    private String serieRegistro;
    private String serieRegistroMarca;
    private String serieRegistroMarcaLema;
    private String serieRenovacion;
    private String serieRenovacionPenultima;
    private String tipoSigno;
    private String tipoMarca;
    private String ciudadNotificacion;
    private String serieRenovacionAceptada;
    private Boolean renderizaBotonesModificatoria = false;
    private Boolean radioNombre = false;
    private Boolean radioDomicilio = false;
    private Boolean radioTransferencia = false;
    private Boolean radioFusion = false;
    private Boolean radioLU = false;
    private Boolean panelLema = false;
    private String areaTramite = "REN";
    private Integer indice = 0;
    private String valorOpcionesRadio = "9";
    private Boolean btnAdicionarDisabled = false;
    private Boolean btnModificarDisabled = false;
    private Boolean txtOtroRendered = false;
    private String[] tipoGeneroSeleccionado = new String[10];
    private String textoOtro = null;
    private String signoRegistradoNuevo;
    private String ordenRenovacion = "";
    private String cadenaTitular = "";
    private String serieRegistroRenovacion;
    private String serieRenovacionNuevo;
    private String cadenaTipoGenero;
    private String cadenaTipoSigno;
    private int numeroRegistrobuscador;
    private List<RenTipoSigno> lstModTipoSigno = new ArrayList<RenTipoSigno>();
    private Long numeroSM;
    private Long gestionSM;
    private String extensionSM;
    private Long registroExpediente;
    private Long smExpediente;
    private Long gestionExpediente;
    private String extensionExpediente = new String();
    private Long nroPublicacionExpediente;
    private String serieRegExpediente = "";
    private String serieExtension = "";
    private Boolean botonRecepcionarDisabled = true;
    private Boolean botonFinalizarDisabled = true;
    private Seguimiento ultimoSeguimiento;
    private Boolean calendarioDialogoRender = false;
    private Boolean titularDialogoRender = false;
    private Boolean signoDialogoRender = false;
    private Boolean tipoMarcaDialogoRender = false;
    private Boolean panelCatalogoRendered = false;
    private Boolean panelVerRendered = false;
    private Usuario usuario1;
    private Regional regional;
    private String valorRadio = "RD";
    private String tipoMarcaDialogo;
    private String tipoSignoDescripcion = "Elegir";
    private List<Etapa> listEtapaUs;
    private Boolean botonRecepcionarRendered = false;
    private Boolean botonFinalizarRendered = false;
    private Boolean esRevocatoria = false;
    private FormularioPI104 formularioPI104 = new FormularioPI104();
    private Boolean panelEncontrado;
    private Boolean panelNoEncontrado;
    private String mensajeBusqueda = "";  
    private String versionClaseNiza="";
    private String versionReclasificacion="";
    
//</editor-fold>
    private ObservacionTramite observacionTramite = new ObservacionTramite();

    public ExamenRenSubsanacionBean() {

        // listEtapaUs = new ArrayList<>();
    }

    @PostConstruct
    public void initRenovacionBean() {
        try {
            listEtapaUs = etapaService.listadoPorIdUsuario(super.getIdUsuarioSession(), getIdEtapaSession());

            obtenerParametrosBusquedaExpedienteRenovacion();
            inicializaValores();
            setidRenSolicitudRenovacionSession(null);

        } catch (Exception e) {
        }

    }

    /**
     * *
     * Metodo que realiza la busque de un formulario de acuerdo a su tramite
     * Creado: Placido Castro Fecha: 25/09/2017
     *
     *
     */
    public void buscarFormularioExterno() {
        if (!numeroFormulario.trim().equalsIgnoreCase("")) {

            HashMap mapResultado = new HashMap();
            formularioPI104 = new FormularioPI104();

            try {
                mapResultado = this.formularioService.obtenerNumeroFormularioSubsanacion(numeroFormulario, renSolicitudRenovacion.getNumero_formulario().toString());

                if (mapResultado.get("continuarFlujo").equals("true")) {

                    if (mapResultado.get("estado").equals("AC")) {

                        //obtener el o los formularios dependiendo
                        if (mapResultado.get("tipoFormulario").equals(EnumTipoFormulario.REGISTRO_RENOVACION.getCodigo())) {

                            this.panelNoEncontrado = Boolean.FALSE;
                            generarFormularioPI104(mapResultado.get("idFormulario").toString());
                        } else {
                            FacesContext.getCurrentInstance().addMessage(
                                    null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Nro Formulario no corresponde a un trámite PI104", ""));
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
                    limpiarExamenRenovacion();
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

    /**
     * Método que permite generar el FormularioPI104
     *
     * creado: Eddy Valero Fecha: 26/10/2016
     *
     * @param idFormulario
     */
    public void generarFormularioPI104(String idFormulario) {
        try {

            
            formularioPI104 = this.formularioPI104Service.obtenerDatosFormularioPI104(Long.parseLong(idFormulario));
            this.oficina = formularioPI104.getDirecciones().get(0).getCiudadNotificacion();

            this.listaRenSolicitantesSipi = formularioPI104.getSolicitantes();
            this.listaRenApoderadosSipi = formularioPI104.getApoderados();
            this.direccionNotificacionSipi = formularioPI104.getDirecciones().get(0);
            this.listaTitularRegistradoSipi = formularioPI104.getRenTitularRegistrados();
            this.solicitudRenovacionesSipi = formularioPI104.getSolicitudRenovaciones();
            
            this.versionReclasificacion = "11";
            
            this.tipoMarca = formularioPI104.getSolicitudRenovaciones().getTipoGenero();
            this.tipoSignoDescripcion = concatenaLabelTiposignoSubsanacion(formularioPI104.getRenTipoSignos());
            
            //this.tipoSignoDescripcion = concatenaLabelTiposigno(lstModTipoSigno);

        } catch (Exception ex) {
            Logger.getLogger(IngresoSolicitudTramiteBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void subsanarExpedienteRenovacion() throws Exception {
        try {
            if (numeroSr != null && gestion != null) {
                if (renSolicitudRenovacionService.buscaSolicitudRenovacionPorNumeroSrYGestion(numeroSr, gestion).getIdsolicitudrenovacion() != null) {

                    renSolicitudRenovacionService.actualizarRenovacionPorSubsanacion(renSolicitudRenovacion, renDireccionDeNotificacion, 
                            crearListaTipoSigno(), usuario1, formularioPI104);

                    FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_INFO, "Solicitud de renovacion fue modificada correctamente", ""));
                } else {
                    FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_WARN, "Guarde el número de SR para modificar", ""));
                }

            } else {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Ingrese los datos: número solicitud y gestión", ""));
            }
        } catch (Exception e) {
            throw e;
        }
    }

//<editor-fold defaultstate="collapsed" desc="-------------------METODOS--------------------">
    public void inicializaValores() throws Exception {
        if (getIdUsuarioSession() != null) {
            usuario1 = usuarioService.buscaUsuarioPorIdUsuario(getIdUsuarioSession());
            regional = regionalService.obtenerRegionalPorIdRegiona(usuario1.getIdregional());
        }
        if (getidRenSolicitudRenovacionSession() != null) {
            RenSolicitudRenovacion nueva = renSolicitudRenovacionService.obtenerRenSolicitudXIdRenSolicitud(getidRenSolicitudRenovacionSession());

            numeroSr = nueva.getSr();
            gestion = nueva.getGestion_sr();
            situacion = nueva.getEstado_renovacion();
            accion_buscaDatosSolicitudRenovacion();
        } else {
            renSolicitudRenovacion = new RenSolicitudRenovacion();
            renSolicitanteApoderado = new RenSolicitanteApoderado();
            renDireccionDeNotificacion = new RenDireccionDeNotificacion();
            renRenovacion = new RenRenovacion();
            renResolucion = new RenResolucion();
            correlativo = new Correlativo();
            numeroSM = null;
            gestionSM = null;
            extensionSM = "";
        }
    }


    public void obtenerParametrosBusquedaExpedienteRenovacion() {
        try {

            Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
            String parIdRenSolicitudRenovacion = (String) (params.get("ThrEimhaJd5"));
            String parIdUsuario = (String) (params.get("UkYJ0g3jLwc"));
            if (parIdRenSolicitudRenovacion != null && parIdUsuario != null) {
                if (!parIdRenSolicitudRenovacion.trim().equals("") && !parIdUsuario.trim().equals("") && !parIdRenSolicitudRenovacion.equals("null") && !parIdUsuario.equals("null")) {
                    setIdUsuarioSession(Long.parseLong(parIdUsuario));
                    setidRenSolicitudRenovacionSession(Long.parseLong(parIdRenSolicitudRenovacion));
                }
            }
        } catch (Exception e) {
            Logger.getLogger(ExamenRenSubsanacionBean.class
                    .getName()).log(Level.SEVERE, null, e);
        }

    }

    public void accion_buscaDatosSolicitudRenovacion() throws Exception {
        try {
            if (numeroSr != null && gestion != null) {
                renSolicitudRenovacion = renSolicitudRenovacionService.buscaSolicitudRenovacionPorNumeroSrYGestion(numeroSr, gestion);
                if (renSolicitudRenovacion.getIdsolicitudrenovacion() != null) {

                    renDireccionDeNotificacion = renDireccionDeNotificacionService.buscaDireccionDeNotificacionPorIdSolicitud(renSolicitudRenovacion.getIdsolicitudrenovacion());
                    lstModTipoSigno = renTipoSignoService.buscaRenTipoSignoPorIdSolicitud(renSolicitudRenovacion.getIdsolicitudrenovacion());
                    sigSignoMarca = sigSignoMarcaService.listaSigSignoMarcaXRegistro(renSolicitudRenovacion.getNumero_registro_registrado(), renSolicitudRenovacion.getSerie_registro_registrado(), " ");
                    tipoMarca = renSolicitudRenovacion.getTipo_genero();
                    serieRegistroMarca = renSolicitudRenovacion.getSerie_registro_registrado();
                    serieRegistroMarcaLema = renSolicitudRenovacion.getSerie_lc_registrado();
                    serieRecibo = renSolicitudRenovacion.getSerie_recibo();
                    serieTitulo = renSolicitudRenovacion.getSerie_titulo();
                    situacion = renSolicitudRenovacion.getEstado_renovacion();
                    ubicacion = renSolicitudRenovacion.getUbicacion_renovacion();
                    serieRenovacionPenultima = renSolicitudRenovacion.getSerie_penultima_renovacion();
                    serieRenovacion = renSolicitudRenovacion.getSerie_ultima_renovacion();
                    ciudadNotificacion = renDireccionDeNotificacion.getCiudad_notificacion();
                    oficina = renSolicitudRenovacion.getOficina();
                    tipoGeneroSeleccionado = new String[lstModTipoSigno.size()];
                    tipoSignoDescripcion = concatenaLabelTiposigno(lstModTipoSigno);

                    if (sigSignoMarca.getSm() != null && sigSignoMarca.getSm() != 0) {
                        HashMap resultado = comunService.obtenerNumeroGestionExtensionCodigoSM(sigSignoMarca.getSm());
                        numeroSM = Long.parseLong(resultado.get("numero").toString());
                        gestionSM = Long.parseLong(resultado.get("gestion").toString());
                        extensionSM = resultado.get("extension").toString();
                    } else {
                        numeroSM = null;
                        gestionSM = null;
                        extensionSM = null;
                    }
                    //inicializaDatosRenovacion(renSolicitudRenovacion);
                    int i = 0;
                    for (RenTipoSigno item : lstModTipoSigno) {
                        if (item.getTipo_signo() != null) {
                            tipoGeneroSeleccionado[i] = item.getTipo_signo();
                            if (item.getTipo_signo().equals("OTRO")) {
                                textoOtro = item.getDescripcion_otro();
                                txtOtroRendered = true;
                            }
                            i++;
                        }
                    }

                } else {
                    FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_INFO, "No existe la solicitud, Intente con otro número de solicitud y gestión", ""));
                }
            } else {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Ingrese los datos: número de solicitud y gestión", ""));
            }
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void limpiarExamenRenovacion() {
        renSolicitudRenovacion = new RenSolicitudRenovacion();
        renSolicitante = new RenSolicitanteApoderado();
        listarenApoderado = new ArrayList<>();
        listaRenApoderadosSipi = new ArrayList<>();
        formularioPI104 = new FormularioPI104();
        listarenSolicitante = new ArrayList<>();
        listaRenSolicitantesSipi = new ArrayList<>();
        direccionNotificacionSipi = new DireccionNotificaciones();
        listaTitularRegistradoSipi = new ArrayList<>();
        solicitudRenovacionesSipi = new SolicitudRenovaciones();
        listarenTitularRegistrado = new ArrayList<>();
        listaTitularRegistrado = new ArrayList<>();
        renSolicitanteApoderado = new RenSolicitanteApoderado();
        renDireccionDeNotificacion = new RenDireccionDeNotificacion();
        renTitularRegistrado = new RenTitularRegistrado();
        renRenovacion = new RenRenovacion();
        renResolucion = new RenResolucion();
        correlativo = new Correlativo();
        numeroSM = null;
        gestionSM = null;
        extensionSM = "";
        numeroSr = null;
        gestion = null;
        tipoMarca = "Elegir";
        situacion = EnumEstadoRenovacion.SOLICITADO.getCodigo();
    }

    public void recuperaDatosSolicitudRenovacion(RenSolicitudRenovacion renSolicitudRenovacion) throws Exception {
        try {
            if (renSolicitudRenovacion.getIdsolicitudrenovacion() != null) {
                numeroSr = renSolicitudRenovacion.getSr();
                gestion = renSolicitudRenovacion.getGestion_sr();
                listarenApoderado = (List<RenSolicitanteApoderado>) renSolicitanteApoderadoService.buscaSolicitanteApoderadoPoridSolicitudyTipoPersona(renSolicitudRenovacion.getIdsolicitudrenovacion(), EnumTipoPersona.APODERADO.getCodigo());
                listarenSolicitante = (List<RenSolicitanteApoderado>) renSolicitanteApoderadoService.buscaSolicitanteApoderadoPoridSolicitudyTipoPersona(renSolicitudRenovacion.getIdsolicitudrenovacion(), EnumTipoPersona.SOLICITANTE.getCodigo());
                renDireccionDeNotificacion = renDireccionDeNotificacionService.buscaDireccionDeNotificacionPorIdSolicitud(renSolicitudRenovacion.getIdsolicitudrenovacion());
                listaTitularRegistrado = (List<RenTitularRegistrado>) renTitularRegistradoService.buscaRenTitularRegistradoPorIdSolicitud(renSolicitudRenovacion.getIdsolicitudrenovacion());
                lstModTipoSigno = renTipoSignoService.buscaRenTipoSignoPorIdSolicitud(renSolicitudRenovacion.getIdsolicitudrenovacion());
                renRenovacion = renRenovacionService.obtieneRenovacionPorIdSolicitud(renSolicitudRenovacion.getIdsolicitudrenovacion());
                if (renRenovacion.getIdrenovacion() != null) {
                    renResolucion = renResolucionService.obtieneRenResolucionPorIdRenovacion(renRenovacion.getIdrenovacion());
                    ordenRenovacion = devuelveLiteralNumero(renRenovacion.getOrden_renovacion());
                } else {
                    renResolucion = new RenResolucion();
                    ordenRenovacion = "";
                }
                tipoSignoDescripcion = concatenaLabelTiposigno(lstModTipoSigno);

                sigSignoMarca = sigSignoMarcaService.listaSigSignoMarcaXRegistro(renSolicitudRenovacion.getNumero_registro_registrado(), renSolicitudRenovacion.getSerie_registro_registrado(), " ");
                tipoMarca = renSolicitudRenovacion.getTipo_genero();
                serieRegistroMarca = renSolicitudRenovacion.getSerie_registro_registrado();
                serieRegistroMarcaLema = renSolicitudRenovacion.getSerie_lc_registrado();
                serieRecibo = renSolicitudRenovacion.getSerie_recibo();
                serieTitulo = renSolicitudRenovacion.getSerie_titulo();
                situacion = renSolicitudRenovacion.getEstado_renovacion();
                ubicacion = renSolicitudRenovacion.getUbicacion_renovacion();
                serieRenovacionPenultima = renSolicitudRenovacion.getSerie_penultima_renovacion();
                serieRenovacion = renSolicitudRenovacion.getSerie_ultima_renovacion();
                ciudadNotificacion = renDireccionDeNotificacion.getCiudad_notificacion();
                oficina = renSolicitudRenovacion.getOficina();
                if (renSolicitudRenovacion.getSm() != null && renSolicitudRenovacion.getSm() != 0) {
                    HashMap resultado = comunService.obtenerNumeroGestionExtensionCodigoSM(renSolicitudRenovacion.getSm());
                    numeroSM = Long.parseLong(resultado.get("numero").toString());
                    gestionSM = Long.parseLong(resultado.get("gestion").toString());
                    extensionSM = resultado.get("extension").toString();
                } else {
                    numeroSM = null;
                    gestionSM = null;
                    extensionSM = "";
                }
                int i = 0;
                for (RenTipoSigno item : lstModTipoSigno) {
                    tipoGeneroSeleccionado[i] = item.getTipo_signo();
                    if (item.getTipo_signo().equals("OTRO")) {
                        textoOtro = item.getDescripcion_otro();
                        txtOtroRendered = true;
                    }
                    i++;
                }
                inicializaDatosRenovacion(renSolicitudRenovacion);
            }
        } catch (Exception e) {
            throw e;
        }
    }

    String concatenaLabelTiposigno(List<RenTipoSigno> listaTipoSigno) throws Exception {
        String cadenaString = "Ninguna";

        for (RenTipoSigno renTipoSigno : listaTipoSigno) {
            if (renTipoSigno.getTipo_signo() != null) {
                if (cadenaString.equals("Ninguna")) {
                    cadenaString = dominioService.obtenerNombrePorCodigoDominio(renTipoSigno.getTipo_signo(), "tipo_signo");
                } else {
                    cadenaString = cadenaString + "-" + dominioService.obtenerNombrePorCodigoDominio(renTipoSigno.getTipo_signo(), "tipo_signo");
                }

            }
        }
        return cadenaString;
    }
    
    String concatenaLabelTiposignoSubsanacion(List<RenTipoSignos> listaTipoSigno) throws Exception {
        String cadenaString = "Ninguna";

        for (RenTipoSignos renTipoSigno : listaTipoSigno) {
            if (renTipoSigno.getTiposigno() != null) {
                if (cadenaString.equals("Ninguna")) {
                    cadenaString = dominioService.obtenerNombrePorCodigoDominio(renTipoSigno.getTiposigno(), "tipo_signo");
                } else {
                    cadenaString = cadenaString + "-" + dominioService.obtenerNombrePorCodigoDominio(renTipoSigno.getTiposigno(), "tipo_signo");
                }

            }
        }
        return cadenaString;
    }
    
    public void inicializaDatosRenovacion(RenSolicitudRenovacion renSolicitdRenovacion) throws Exception {
        renRenovacion = renRenovacionService.obtieneRenovacionPorIdSolicitud(renSolicitdRenovacion.getIdsolicitudrenovacion());
        if (renRenovacion.getIdrenovacion() != null) {
            signoRegistradoNuevo = renSolicitudRenovacion.getSigno_registrado();
//           ordenRenovacion = devuelveLiteralNumero(renRenovacion.getOrden_renovacion());
            cadenaTitular = concatenaTitular(listaTitularRegistrado);
            cadenaTipoSigno = concatenaTipoSigno(renTipoSignoService.buscaRenTipoSignoPorIdSolicitud(renSolicitudRenovacion.getIdsolicitudrenovacion()));
            numeroRegistrobuscador = renRenovacion.getNumero_renovacion();
            serieRenovacionNuevo = renRenovacion.getSerie_registro();
            cadenaTipoGenero = renSolicitudRenovacion.getTipo_genero();
            //recupera renovacion y muestra en el pop
        } else {
        }
    }

    public String concatenaTipoSigno(List<RenTipoSigno> lstSigno) throws Exception {
        String signo = "";
        int sw = 0;
        for (RenTipoSigno renTipoSigno : lstSigno) {
            if (sw == 0) {
                if (renTipoSigno.getTipo_signo().equals("OTRO")) {
                    signo = renTipoSigno.getDescripcion_otro();
                } else {
                    signo = dominioService.obtenerListadoDominioXCodigo(EnumNombreDominio.TIPO_SIGNO.getCodigo(), renTipoSigno.getTipo_signo()).get(0).getDescripcion();
                }
            } else {
                if (renTipoSigno.getTipo_signo().equals("OTRO")) {
                    signo = signo + " " + renTipoSigno.getDescripcion_otro();
                } else {
                    signo = signo + " " + dominioService.obtenerListadoDominioXCodigo(EnumNombreDominio.TIPO_SIGNO.getCodigo(), renTipoSigno.getTipo_signo()).get(0).getDescripcion();
                }
            }
            sw = 1;
        }
        return signo;
    }

    public void borrarPersona(RenSolicitanteApoderado renSolicitanteApoderado) {
        listarenSolicitante.remove(renSolicitanteApoderado);
    }

    public void borrarApoderado(RenSolicitanteApoderado renSolicitanteApoderado) {
        listarenApoderado.remove(renSolicitanteApoderado);
    }

    public void borrarNuevoTitular(RenTitularRegistrado titularNuevo) {
        listaTitularRegistrado.remove(titularNuevo);
    }

    public void recuperarTodo(RenSolicitudRenovacion renSolicitudRenovacion) throws Exception {
        if (renSolicitudRenovacion.getIdsolicitudrenovacion() != null) {
            numeroSr = renSolicitudRenovacion.getSr();
            gestion = renSolicitudRenovacion.getGestion_sr();
            listarenApoderado = (List<RenSolicitanteApoderado>) renSolicitanteApoderadoService.buscaSolicitanteApoderadoPoridSolicitudyTipoPersona(renSolicitudRenovacion.getIdsolicitudrenovacion(), EnumTipoPersona.APODERADO.getCodigo());
            listarenSolicitante = (List<RenSolicitanteApoderado>) renSolicitanteApoderadoService.buscaSolicitanteApoderadoPoridSolicitudyTipoPersona(renSolicitudRenovacion.getIdsolicitudrenovacion(), EnumTipoPersona.SOLICITANTE.getCodigo());
            renDireccionDeNotificacion = renDireccionDeNotificacionService.buscaDireccionDeNotificacionPorIdSolicitud(renSolicitudRenovacion.getIdsolicitudrenovacion());
            listaTitularRegistrado = (List<RenTitularRegistrado>) renTitularRegistradoService.buscaRenTitularRegistradoPorIdSolicitud(renSolicitudRenovacion.getIdsolicitudrenovacion());
            lstModTipoSigno = renTipoSignoService.buscaRenTipoSignoPorIdSolicitud(renSolicitudRenovacion.getIdsolicitudrenovacion());
            renRenovacion = renRenovacionService.obtieneRenovacionPorIdSolicitud(renSolicitudRenovacion.getIdsolicitudrenovacion());
            if (renRenovacion.getIdrenovacion() != null) {
                renResolucion = renResolucionService.obtieneRenResolucionPorIdRenovacion(renRenovacion.getIdrenovacion());
                ordenRenovacion = devuelveLiteralNumero(renRenovacion.getOrden_renovacion());
            } else {
                renResolucion = new RenResolucion();
                ordenRenovacion = " ";
            }
            sigSignoMarca = sigSignoMarcaService.listaSigSignoMarcaXRegistro(renSolicitudRenovacion.getNumero_registro_registrado(), renSolicitudRenovacion.getSerie_registro_registrado(), " ");
            tipoMarca = renSolicitudRenovacion.getTipo_genero();
            serieRegistroMarca = renSolicitudRenovacion.getSerie_registro_registrado();
            serieRegistroMarcaLema = renSolicitudRenovacion.getSerie_lc_registrado();
            serieRecibo = renSolicitudRenovacion.getSerie_recibo();
            serieTitulo = renSolicitudRenovacion.getSerie_titulo();
            situacion = renSolicitudRenovacion.getEstado_renovacion();
            ubicacion = renSolicitudRenovacion.getUbicacion_renovacion();
            serieRenovacionPenultima = renSolicitudRenovacion.getSerie_penultima_renovacion();
            serieRenovacion = renSolicitudRenovacion.getSerie_ultima_renovacion();
            ciudadNotificacion = renDireccionDeNotificacion.getCiudad_notificacion();
            oficina = renSolicitudRenovacion.getOficina();
            tipoGeneroSeleccionado = new String[lstModTipoSigno.size()];
            if (renSolicitudRenovacion.getSm() != null && renSolicitudRenovacion.getSm() != 0) {
                HashMap resultado = comunService.obtenerNumeroGestionExtensionCodigoSM(renSolicitudRenovacion.getSm());
                numeroSM = Long.parseLong(resultado.get("numero").toString());
                gestionSM = Long.parseLong(resultado.get("gestion").toString());
                extensionSM = resultado.get("extension").toString();
            } else {
                numeroSM = null;
                gestionSM = null;
                extensionSM = "";
            }
            inicializaDatosRenovacion(renSolicitudRenovacion);
            int i = 0;
            for (RenTipoSigno item : lstModTipoSigno) {
                if (item.getTipo_signo() != null) {
                    tipoGeneroSeleccionado[i] = item.getTipo_signo();
                    if (item.getTipo_signo().equals("OTRO")) {
                        textoOtro = item.getDescripcion_otro();
                        txtOtroRendered = true;
                    }
                    i++;
//                
                }
            }
        }
    }

    public String devuelveFechaFormat(Date fecha) {
        if (fecha != null) {
            DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
            return formatter.format(fecha);
        }
        return null;
    }

    public void borrartTitularRegistrado(RenTitularRegistrado titularLicenciatarioRegistrado) {
        listaTitularRegistrado.remove(titularLicenciatarioRegistrado);
    }

    public void activaPanelLema() {
        panelLema = tipoMarca.equals("LC");
    }

    public String devuelveTipoDocumento(String codigo) throws Exception {
        if (codigo != null && !codigo.equals("")) {
            return dominioService.obtenerListadoDominioXCodigo(EnumNombreDominio.TIPO_DOCUMENTO.getCodigo(), codigo).get(0).getNombre();
        }
        return "";
    }

    public String devuelveTipoGenero(String codigo) throws Exception {
        if (codigo != null && !codigo.equals("")) {
            return dominioService.obtenerListadoDominioXCodigo(EnumNombreDominio.TIPO_GENERO.getCodigo(), codigo).get(0).getNombre();
        }
        return "";
    }

    public String devuelveLugarExpedicion(String codigo) throws Exception {
        if (codigo != null && !codigo.equals("")) {
            return dominioService.obtenerListadoDominioXCodigo(EnumNombreDominio.LUGAR_EXPEDICION.getCodigo(), codigo).get(0).getNombre();
        }
        return "";
    }

    public String devuelveDepartamento(String codigo) throws Exception {

        if (codigo != null && !codigo.equals("")) {
            return dominioService.obtenerListadoDominioXCodigo(EnumNombreDominio.LUGAR_EXPEDICION.getCodigo(), codigo).get(0).getNombre();
        }
        return "";
    }

    public String devuelveGenero(String codigo) throws Exception {

        if (codigo != null && !codigo.equals("")) {
            return dominioService.obtenerListadoDominioXCodigo(EnumNombreDominio.GENERO_MAS_FEM.getCodigo(), codigo).get(0).getNombre();
        }
        return "";
    }

    public String devuelvePais(String codigo) throws Exception {
        if (codigo != null && !codigo.equals("")) {
            return dominioService.obtenerListadoDominioXCodigo(EnumNombreDominio.PAIS.getCodigo(), codigo).get(0).getNombre();
        }
        return "";
    }

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

//    METODO DE GUARDADO DE LA SOLICITUD DE RENOVACION 08/09/2106
    public Long creaSM(Long numeroSM, Long gestionSM, String extension) throws Exception {
        Long sm = 0l;
        //VALIDAR que las regionales concuerden con los numero que introduce el usuario
        if (numeroSM != null && gestionSM != null) {
            if (!extension.equals("")) {
                sm = comunService.codificarCodigoSM(numeroSM.toString(), gestionSM.toString(), extension);
            } else {
                sm = comunService.codificarCodigoSM(numeroSM.toString(), gestionSM.toString(), "");
            }
        }
        return sm;

    }

    public List<RenTipoSigno> crearListaTipoSigno() {

        lstModTipoSigno.clear();
        System.out.println("tipogenero" + tipoGeneroSeleccionado.length);
        for (String cadena : tipoGeneroSeleccionado) {
            RenTipoSigno tipoSigno = new RenTipoSigno();
            tipoSigno.setIdsolicitudrenovacion(renSolicitudRenovacion.getIdsolicitudrenovacion());
            tipoSigno.setTipo_signo(cadena);
            if (cadena.equals("OTRO")) {
                tipoSigno.setDescripcion_otro(textoOtro);
            }
            lstModTipoSigno.add(tipoSigno);
        }

        return lstModTipoSigno;
    }


    public void cambiaOpcionesFormulario() {
//        inicializaTodo();
        switch (valorOpcionesRadio) {
            case "ADD":
                btnAdicionarDisabled = false;
                btnModificarDisabled = true;
                break;
            case "MOD":
                btnAdicionarDisabled = true;
                btnModificarDisabled = false;
                break;
            case "CON":
                btnAdicionarDisabled = true;
                btnModificarDisabled = true;
                break;
            default:
                break;
        }
    }

    public String convertirFechaString(RenRenovacion renRenovacionvista) {
        if (renRenovacionvista.getFecha_concesion() != null) {
            return new SimpleDateFormat("dd-MM-yyyy").format(renRenovacionvista.getFecha_concesion());
        } else {
            return "";
        }
    }

    public void muestraOtro() {
        for (String s : tipoGeneroSeleccionado) {
            if (s.equals("OTRO")) {
                txtOtroRendered = true;
            } else {
                txtOtroRendered = false;
                textoOtro = null;
            }
        }
    }

    public void inicializaListaOrdinales() {
        for (int i = 1; i <= 20; i++) {
            OrdinalesPojo ordinal = new OrdinalesPojo();
            ordinal.setNumeroOrden(i);
            ordinal.setNumeroOrdinal(renRenovacionService.numeroOrdinal(i));
            listaOrdinales.add(ordinal);
        }
    }

    public String devuelveLiteralNumero(int numeroOrden) {
        if (numeroOrden > 0) {
            return renRenovacionService.numeroOrdinal(numeroOrden);
        }
        return "NINGUNO";
    }

    public String concatenaTitular(List<RenTitularRegistrado> listaTitularRegistrado) {
        String cadenaTitularme = "";
        for (RenTitularRegistrado renTitularRegistrado1 : listaTitularRegistrado) {
            String campoNombreRazonSocial = " ";
            if (renTitularRegistrado1.getNombre_razonsocial() != null && !renTitularRegistrado1.getNombre_razonsocial().equals("")) {
                cadenaTitularme = renTitularRegistrado1.getNombre_razonsocial();
            }
            if (renTitularRegistrado1.getPrimer_apellido() != null && !renTitularRegistrado1.getPrimer_apellido().equals("")) {
                cadenaTitularme = cadenaTitularme + " " + renTitularRegistrado1.getPrimer_apellido();
            }
            if (renTitularRegistrado1.getSegundo_apellido() != null && !renTitularRegistrado1.getSegundo_apellido().equals("")) {
                cadenaTitularme = cadenaTitularme + " " + renTitularRegistrado1.getSegundo_apellido();
            }
        }
        return cadenaTitularme;
    }


    public void terminaDialogoSeguimiento(SelectEvent event) throws Exception {
        if (event.getObject() != "Exit") {
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cerrado"));
        }
    }

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="-----------------------------GET'S  AND SET'S----------------------">
    public SigSignoClaseNizaService getSigSignoClaseNizaService() {
        return sigSignoClaseNizaService;
    }

    public void setSigSignoClaseNizaService(SigSignoClaseNizaService sigSignoClaseNizaService) {
        this.sigSignoClaseNizaService = sigSignoClaseNizaService;
    }

    public SigTipoSignoService getSigTipoSignoService() {
        return sigTipoSignoService;
    }

    public void setSigTipoSignoService(SigTipoSignoService sigTipoSignoService) {
        this.sigTipoSignoService = sigTipoSignoService;
    }

    public Boolean getPanelVerRendered() {
        return panelVerRendered;
    }

    public void setPanelVerRendered(Boolean panelVerRendered) {
        this.panelVerRendered = panelVerRendered;
    }

    public Boolean getPanelCatalogoRendered() {
        return panelCatalogoRendered;
    }

    public void setPanelCatalogoRendered(Boolean panelCatalogoRendered) {
        this.panelCatalogoRendered = panelCatalogoRendered;
    }

    public Boolean getCalendarioDialogoRender() {
        return calendarioDialogoRender;
    }

    public void setCalendarioDialogoRender(Boolean calendarioDialogoRender) {
        this.calendarioDialogoRender = calendarioDialogoRender;
    }

    public Boolean getTitularDialogoRender() {
        return titularDialogoRender;
    }

    public void setTitularDialogoRender(Boolean titularDialogoRender) {
        this.titularDialogoRender = titularDialogoRender;
    }

    public Boolean getSignoDialogoRender() {
        return signoDialogoRender;
    }

    public void setSignoDialogoRender(Boolean signoDialogoRender) {
        this.signoDialogoRender = signoDialogoRender;
    }

    public String getValorRadio() {
        return valorRadio;
    }

    public void setValorRadio(String valorRadio) {
        this.valorRadio = valorRadio;
    }

    public String getTipoMarcaDialogo() {
        return tipoMarcaDialogo;
    }

    public void setTipoMarcaDialogo(String tipoMarcaDialogo) {
        this.tipoMarcaDialogo = tipoMarcaDialogo;
    }

    public Long getRegistroExpediente() {
        return registroExpediente;
    }

    public void setRegistroExpediente(Long registroExpediente) {
        this.registroExpediente = registroExpediente;
    }

    public Long getSmExpediente() {
        return smExpediente;
    }

    public void setSmExpediente(Long smExpediente) {
        this.smExpediente = smExpediente;
    }

    public Long getGestionExpediente() {
        return gestionExpediente;
    }

    public void setGestionExpediente(Long gestionExpediente) {
        this.gestionExpediente = gestionExpediente;
    }

    public String getExtensionExpediente() {
        return extensionExpediente;
    }

    public void setExtensionExpediente(String extensionExpediente) {
        this.extensionExpediente = extensionExpediente;
    }

    public Long getNroPublicacionExpediente() {
        return nroPublicacionExpediente;
    }

    public void setNroPublicacionExpediente(Long nroPublicacionExpediente) {
        this.nroPublicacionExpediente = nroPublicacionExpediente;
    }

    public DominioService getDominioService() {
        return dominioService;
    }

    public void setDominioService(DominioService dominioService) {
        this.dominioService = dominioService;
    }

    public RenTipoSignoService getRenTipoSignoService() {
        return renTipoSignoService;
    }

    public void setRenTipoSignoService(RenTipoSignoService renTipoSignoService) {
        this.renTipoSignoService = renTipoSignoService;
    }

    public RenRenovacionService getRenRenovacionService() {
        return renRenovacionService;
    }

    public void setRenRenovacionService(RenRenovacionService renRenovacionService) {
        this.renRenovacionService = renRenovacionService;
    }

    public RegionalService getRegionalService() {
        return regionalService;
    }

    public void setRegionalService(RegionalService regionalService) {
        this.regionalService = regionalService;
    }

    public RenSolicitudRenovacionService getRenSolicitudRenovacionService() {
        return renSolicitudRenovacionService;
    }

    public void setRenSolicitudRenovacionService(RenSolicitudRenovacionService renSolicitudRenovacionService) {
        this.renSolicitudRenovacionService = renSolicitudRenovacionService;
    }

    public RenSolicitanteApoderadoService getRenSolicitanteApoderadoService() {
        return renSolicitanteApoderadoService;
    }

    public void setRenSolicitanteApoderadoService(RenSolicitanteApoderadoService renSolicitanteApoderadoService) {
        this.renSolicitanteApoderadoService = renSolicitanteApoderadoService;
    }

    public RenDireccionDeNotificacionService getRenDireccionDeNotificacionService() {
        return renDireccionDeNotificacionService;
    }

    public void setRenDireccionDeNotificacionService(RenDireccionDeNotificacionService renDireccionDeNotificacionService) {
        this.renDireccionDeNotificacionService = renDireccionDeNotificacionService;
    }

    public RenResolucionService getRenResolucionService() {
        return renResolucionService;
    }

    public void setRenResolucionService(RenResolucionService renResolucionService) {
        this.renResolucionService = renResolucionService;
    }

    public RenTitularRegistradoService getRenTitularRegistradoService() {
        return renTitularRegistradoService;
    }

    public void setRenTitularRegistradoService(RenTitularRegistradoService renTitularRegistradoService) {
        this.renTitularRegistradoService = renTitularRegistradoService;
    }

    public ModDireccionDeNotificacionService getModDireccionDeNotificacionService() {
        return modDireccionDeNotificacionService;
    }

    public void setModDireccionDeNotificacionService(ModDireccionDeNotificacionService modDireccionDeNotificacionService) {
        this.modDireccionDeNotificacionService = modDireccionDeNotificacionService;
    }

    public SigSignoMarcaService getSigSignoMarcaService() {
        return sigSignoMarcaService;
    }

    public void setSigSignoMarcaService(SigSignoMarcaService sigSignoMarcaService) {
        this.sigSignoMarcaService = sigSignoMarcaService;
    }

    public ComunService getComunService() {
        return comunService;
    }

    public void setComunService(ComunService comunService) {
        this.comunService = comunService;
    }

    public ExpedienteService getExpedienteService() {
        return expedienteService;
    }

    public void setExpedienteService(ExpedienteService expedienteService) {
        this.expedienteService = expedienteService;
    }

    public RenRenovacion getRenRenovacion() {
        return renRenovacion;
    }

    public void setRenRenovacion(RenRenovacion renRenovacion) {
        this.renRenovacion = renRenovacion;
    }

    public SigSignoMarca getSigSignoMarca() {
        return sigSignoMarca;
    }

    public void setSigSignoMarca(SigSignoMarca sigSignoMarca) {
        this.sigSignoMarca = sigSignoMarca;
    }

    public List<RenRenovacion> getListaRenovacion() {
        return listaRenovacion;
    }

    public void setListaRenovacion(List<RenRenovacion> listaRenovacion) {
        this.listaRenovacion = listaRenovacion;
    }

    public RenSolicitudRenovacion getRenSolicitudRenovacion() {
        return renSolicitudRenovacion;
    }

    public void setRenSolicitudRenovacion(RenSolicitudRenovacion renSolicitudRenovacion) {
        this.renSolicitudRenovacion = renSolicitudRenovacion;
    }

    public List<RenSolicitudRenovacion> getListaSolicitudRenovacion() {
        return listaSolicitudRenovacion;
    }

    public void setListaSolicitudRenovacion(List<RenSolicitudRenovacion> listaSolicitudRenovacion) {
        this.listaSolicitudRenovacion = listaSolicitudRenovacion;
    }

    public RenSolicitanteApoderado getRenSolicitanteApoderado() {
        return renSolicitanteApoderado;
    }

    public void setRenSolicitanteApoderado(RenSolicitanteApoderado renSolicitanteApoderado) {
        this.renSolicitanteApoderado = renSolicitanteApoderado;
    }

    public RenSolicitanteApoderado getRenSolicitante() {
        return renSolicitante;
    }

    public void setRenSolicitante(RenSolicitanteApoderado renSolicitante) {
        this.renSolicitante = renSolicitante;
    }

    public RenSolicitanteApoderado getRenApoderado() {
        return renApoderado;
    }

    public void setRenApoderado(RenSolicitanteApoderado renApoderado) {
        this.renApoderado = renApoderado;
    }

    public RenSolicitanteApoderado getRenSolicitanteApoderadoSelect() {
        return renSolicitanteApoderadoSelect;
    }

    public void setRenSolicitanteApoderadoSelect(RenSolicitanteApoderado renSolicitanteApoderadoSelect) {
        this.renSolicitanteApoderadoSelect = renSolicitanteApoderadoSelect;
    }

    public List<RenSolicitanteApoderado> getListarenSolicitanteApoderado() {
        return listarenSolicitanteApoderado;
    }

    public void setListarenSolicitanteApoderado(List<RenSolicitanteApoderado> listarenSolicitanteApoderado) {
        this.listarenSolicitanteApoderado = listarenSolicitanteApoderado;
    }

    public List<RenSolicitanteApoderado> getListarenApoderado() {
        return listarenApoderado;
    }

    public void setListarenApoderado(List<RenSolicitanteApoderado> listarenApoderado) {
        this.listarenApoderado = listarenApoderado;
    }

    public List<RenSolicitanteApoderado> getListarenSolicitante() {
        return listarenSolicitante;
    }

    public void setListarenSolicitante(List<RenSolicitanteApoderado> listarenSolicitante) {
        this.listarenSolicitante = listarenSolicitante;
    }

    public RenDireccionDeNotificacion getRenDireccionDeNotificacion() {
        return renDireccionDeNotificacion;
    }

    public void setRenDireccionDeNotificacion(RenDireccionDeNotificacion renDireccionDeNotificacion) {
        this.renDireccionDeNotificacion = renDireccionDeNotificacion;
    }

    public List<RenDireccionDeNotificacion> getListadireccionDeNotificacion() {
        return listadireccionDeNotificacion;
    }

    public void setListadireccionDeNotificacion(List<RenDireccionDeNotificacion> listadireccionDeNotificacion) {
        this.listadireccionDeNotificacion = listadireccionDeNotificacion;
    }

    public List<RenTitularRegistrado> getListarenTitularRegistrado() {
        return listarenTitularRegistrado;
    }

    public void setListarenTitularRegistrado(List<RenTitularRegistrado> listarenTitularRegistrado) {
        this.listarenTitularRegistrado = listarenTitularRegistrado;
    }

    public List<RenTitularRegistrado> getListaTitularRegistrado() {
        return listaTitularRegistrado;
    }

    public void setListaTitularRegistrado(List<RenTitularRegistrado> listaTitularRegistrado) {
        this.listaTitularRegistrado = listaTitularRegistrado;
    }

    public List<RenTitularRegistrados> getListaTitularRegistradoSipi() {
        return listaTitularRegistradoSipi;
    }

    public void setListaTitularRegistradoSipi(List<RenTitularRegistrados> listaTitularRegistradoSipi) {
        this.listaTitularRegistradoSipi = listaTitularRegistradoSipi;
    }

    public RenTitularRegistrado getRenTitularRegistrado() {
        return renTitularRegistrado;
    }

    public void setRenTitularRegistrado(RenTitularRegistrado renTitularRegistrado) {
        this.renTitularRegistrado = renTitularRegistrado;
    }

    public RenResolucion getRenResolucion() {
        return renResolucion;
    }

    public void setRenResolucion(RenResolucion renResolucion) {
        this.renResolucion = renResolucion;
    }

    public List<RenResolucion> getListarenResolucion() {
        return listarenResolucion;
    }

    public void setListarenResolucion(List<RenResolucion> listarenResolucion) {
        this.listarenResolucion = listarenResolucion;
    }

    public Long getNumeroSr() {
        return numeroSr;
    }

    public void setNumeroSr(Long numeroSr) {
        this.numeroSr = numeroSr;
    }

    public Integer getGestion() {
        return gestion;
    }

    public void setGestion(Integer gestion) {
        this.gestion = gestion;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public RenTitularRegistrado getTitularRegistradoSelect() {
        return titularRegistradoSelect;
    }

    public void setTitularRegistradoSelect(RenTitularRegistrado titularRegistradoSelect) {
        this.titularRegistradoSelect = titularRegistradoSelect;
    }

    public List<OrdinalesPojo> getListaOrdinales() {
        return listaOrdinales;
    }

    public void setListaOrdinales(List<OrdinalesPojo> listaOrdinales) {
        this.listaOrdinales = listaOrdinales;
    }

    public Correlativo getCorrelativo() {
        return correlativo;
    }

    public void setCorrelativo(Correlativo correlativo) {
        this.correlativo = correlativo;
    }

    public List<Dominio> getListaTipoRecibo() throws Exception {
        return listaTipoRecibo = dominioService.obtenerListadoDominio(EnumNombreDominio.SERIE_RECIBO.getCodigo());
    }

    public void setListaTipoRecibo(List<Dominio> listaTipoRecibo) {
        this.listaTipoRecibo = listaTipoRecibo;
    }

    public List<Dominio> getListaTipoTitulo() throws Exception {
        return listaTipoTitulo = dominioService.obtenerListadoDominio(EnumNombreDominio.SERIE_TITULO.getCodigo());
    }

    public void setListaTipoTitulo(List<Dominio> listaTipoTitulo) {
        this.listaTipoTitulo = listaTipoTitulo;
    }

    public List<Dominio> getListaCiudadOficina() throws Exception {
        return listaCiudadOficina = dominioService.obtenerListadoDominio(EnumNombreDominio.CIUDAD_NOTIFICACION.getCodigo());
    }

    public void setListaCiudadOficina(List<Dominio> listaCiudadOficina) {
        this.listaCiudadOficina = listaCiudadOficina;
    }

    public List<Dominio> getListaSerieRegistro() throws Exception {
        return listaSerieRegistro = dominioService.obtenerListadoDominio(EnumNombreDominio.SERIE_REGISTRO.getCodigo());
    }

    public void setListaSerieRegistro(List<Dominio> listaSerieRegistro) {
        this.listaSerieRegistro = listaSerieRegistro;
    }

    public List<Dominio> getListaSerieRenovacion() throws Exception {
        return listaSerieRenovacion = dominioService.obtenerListadoDominio(EnumNombreDominio.SERIE_RENOVACION.getCodigo());
    }

    public void setListaSerieRenovacion(List<Dominio> listaSerieRenovacion) {
        this.listaSerieRenovacion = listaSerieRenovacion;
    }

    public List<Dominio> getListaTipoSigno() throws Exception {
        return listaTipoSigno = dominioService.obtenerListadoDominio(EnumNombreDominio.TIPO_SIGNO.getCodigo());
    }

    public void setListaTipoSigno(List<Dominio> listaTipoSigno) {
        this.listaTipoSigno = listaTipoSigno;
    }

    public List<Dominio> getListaTipoMarca() throws Exception {
        return listaTipoMarca = dominioService.obtenerListadoDominio(EnumNombreDominio.TIPO_GENERO.getCodigo());
    }

    public void setListaTipoMarca(List<Dominio> listaTipoMarca) {
        this.listaTipoMarca = listaTipoMarca;
    }

    public List<Dominio> getLstTipoUbicacion() throws Exception {
        return lstTipoUbicacion = dominioService.obtenerListadoDominio(EnumNombreDominio.ESTADO_RENOVACION.getCodigo());
    }

    public void setLstTipoUbicacion(List<Dominio> lstTipoUbicacion) {
        this.lstTipoUbicacion = lstTipoUbicacion;
    }

    public List<Dominio> getLstTipoSituacion() throws Exception {
        return lstTipoSituacion = dominioService.obtenerListadoDominio(EnumNombreDominio.ESTADO_RENOVACION.getCodigo());
    }

    public void setLstTipoSituacion(List<Dominio> lstTipoSituacion) {
        this.lstTipoSituacion = lstTipoSituacion;
    }

    public List<Dominio> getListaLugarExpedicion() throws Exception {
        return listaLugarExpedicion = dominioService.obtenerListadoDominio(EnumNombreDominio.LUGAR_EXPEDICION.getCodigo());
    }

    public void setListaLugarExpedicion(List<Dominio> listaLugarExpedicion) {
        this.listaLugarExpedicion = listaLugarExpedicion;

    }

    public List<Dominio> getListaPais() throws Exception {
        return listaPais = dominioService.obtenerListadoDominio(EnumNombreDominio.PAIS.getCodigo());
    }

    public void setListaPais(List<Dominio> listaPais) {
        this.listaPais = listaPais;
    }

    public String getOficina() {
        return oficina;
    }

    public void setOficina(String oficina) {
        this.oficina = oficina;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getSituacion() {
        return situacion;
    }

    public void setSituacion(String situacion) {
        this.situacion = situacion;
    }

    public String getSerieTitulo() {
        return serieTitulo;
    }

    public void setSerieTitulo(String serieTitulo) {
        this.serieTitulo = serieTitulo;
    }

    public String getSerieRecibo() {
        return serieRecibo;
    }

    public void setSerieRecibo(String serieRecibo) {
        this.serieRecibo = serieRecibo;
    }

    public String getSerieRegistro() {
        return serieRegistro;
    }

    public void setSerieRegistro(String serieRegistro) {
        this.serieRegistro = serieRegistro;
    }

    public String getSerieRegistroMarca() {
        return serieRegistroMarca;
    }

    public void setSerieRegistroMarca(String serieRegistroMarca) {
        this.serieRegistroMarca = serieRegistroMarca;
    }

    public String getSerieRegistroMarcaLema() {
        return serieRegistroMarcaLema;
    }

    public void setSerieRegistroMarcaLema(String serieRegistroMarcaLema) {
        this.serieRegistroMarcaLema = serieRegistroMarcaLema;
    }

    public String getSerieRenovacion() {
        return serieRenovacion;
    }

    public void setSerieRenovacion(String serieRenovacion) {
        this.serieRenovacion = serieRenovacion;
    }

    public String getSerieRenovacionPenultima() {
        return serieRenovacionPenultima;
    }

    public void setSerieRenovacionPenultima(String serieRenovacionPenultima) {
        this.serieRenovacionPenultima = serieRenovacionPenultima;
    }

    public String getTipoSigno() {
        return tipoSigno;
    }

    public void setTipoSigno(String tipoSigno) {
        this.tipoSigno = tipoSigno;
    }

    public String getTipoMarca() {
        return tipoMarca;
    }

    public void setTipoMarca(String tipoMarca) {
        this.tipoMarca = tipoMarca;
    }

    public String getCiudadNotificacion() {
        return ciudadNotificacion;
    }

    public void setCiudadNotificacion(String ciudadNotificacion) {
        this.ciudadNotificacion = ciudadNotificacion;
    }

    public String getSerieRenovacionAceptada() {
        return serieRenovacionAceptada;
    }

    public void setSerieRenovacionAceptada(String serieRenovacionAceptada) {
        this.serieRenovacionAceptada = serieRenovacionAceptada;
    }

    public Boolean getRenderizaBotonesModificatoria() {
        return renderizaBotonesModificatoria;
    }

    public void setRenderizaBotonesModificatoria(Boolean renderizaBotonesModificatoria) {
        this.renderizaBotonesModificatoria = renderizaBotonesModificatoria;
    }

    public Boolean getRadioNombre() {
        return radioNombre;
    }

    public void setRadioNombre(Boolean radioNombre) {
        this.radioNombre = radioNombre;
    }

    public Boolean getRadioDomicilio() {
        return radioDomicilio;
    }

    public void setRadioDomicilio(Boolean radioDomicilio) {
        this.radioDomicilio = radioDomicilio;
    }

    public Boolean getRadioTransferencia() {
        return radioTransferencia;
    }

    public void setRadioTransferencia(Boolean radioTransferencia) {
        this.radioTransferencia = radioTransferencia;
    }

    public Boolean getRadioFusion() {
        return radioFusion;
    }

    public void setRadioFusion(Boolean radioFusion) {
        this.radioFusion = radioFusion;
    }

    public Boolean getRadioLU() {
        return radioLU;
    }

    public void setRadioLU(Boolean radioLU) {
        this.radioLU = radioLU;
    }

    public Boolean getPanelLema() {
        return panelLema;
    }

    public void setPanelLema(Boolean panelLema) {
        this.panelLema = panelLema;
    }

    public String getAreaTramite() {
        return areaTramite;
    }

    public void setAreaTramite(String areaTramite) {
        this.areaTramite = areaTramite;
    }

    public Integer getIndice() {
        return indice;
    }

    public void setIndice(Integer indice) {
        this.indice = indice;
    }

    public String getValorOpcionesRadio() {
        return valorOpcionesRadio;
    }

    public void setValorOpcionesRadio(String valorOpcionesRadio) {
        this.valorOpcionesRadio = valorOpcionesRadio;
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

    public Boolean getTxtOtroRendered() {
        return txtOtroRendered;
    }

    public void setTxtOtroRendered(Boolean txtOtroRendered) {
        this.txtOtroRendered = txtOtroRendered;
    }

    public String[] getTipoGeneroSeleccionado() {
        return tipoGeneroSeleccionado;
    }

    public void setTipoGeneroSeleccionado(String[] tipoGeneroSeleccionado) {
        this.tipoGeneroSeleccionado = tipoGeneroSeleccionado;
    }

    public String getTextoOtro() {
        return textoOtro;
    }

    public void setTextoOtro(String textoOtro) {
        this.textoOtro = textoOtro;
    }

    public String getSignoRegistradoNuevo() {
        return signoRegistradoNuevo;
    }

    public void setSignoRegistradoNuevo(String signoRegistradoNuevo) {
        this.signoRegistradoNuevo = signoRegistradoNuevo;
    }

    public String getOrdenRenovacion() {
        return ordenRenovacion;
    }

    public void setOrdenRenovacion(String ordenRenovacion) {
        this.ordenRenovacion = ordenRenovacion;
    }

    public String getCadenaTitular() {
        return cadenaTitular;
    }

    public void setCadenaTitular(String cadenaTitular) {
        this.cadenaTitular = cadenaTitular;
    }

    public String getSerieRegistroRenovacion() {
        return serieRegistroRenovacion;
    }

    public void setSerieRegistroRenovacion(String serieRegistroRenovacion) {
        this.serieRegistroRenovacion = serieRegistroRenovacion;
    }

    public String getSerieRenovacionNuevo() {
        return serieRenovacionNuevo;
    }

    public void setSerieRenovacionNuevo(String serieRenovacionNuevo) {
        this.serieRenovacionNuevo = serieRenovacionNuevo;
    }

    public String getCadenaTipoGenero() {
        return cadenaTipoGenero;
    }

    public void setCadenaTipoGenero(String cadenaTipoGenero) {
        this.cadenaTipoGenero = cadenaTipoGenero;
    }

    public String getCadenaTipoSigno() {
        return cadenaTipoSigno;
    }

    public void setCadenaTipoSigno(String cadenaTipoSigno) {
        this.cadenaTipoSigno = cadenaTipoSigno;
    }

    public int getNumeroRegistrobuscador() {
        return numeroRegistrobuscador;
    }

    public void setNumeroRegistrobuscador(int numeroRegistrobuscador) {
        this.numeroRegistrobuscador = numeroRegistrobuscador;
    }

    public List<RenTipoSigno> getLstModTipoSigno() {
        return lstModTipoSigno;
    }

    public void setLstModTipoSigno(List<RenTipoSigno> lstModTipoSigno) {
        this.lstModTipoSigno = lstModTipoSigno;
    }

    public Long getNumeroSM() {
        return numeroSM;
    }

    public void setNumeroSM(Long numeroSM) {
        this.numeroSM = numeroSM;
    }

    public Long getGestionSM() {
        return gestionSM;
    }

    public void setGestionSM(Long gestionSM) {
        this.gestionSM = gestionSM;
    }

    public String getExtensionSM() {
        return extensionSM;
    }

    public void setExtensionSM(String extensionSM) {
        this.extensionSM = extensionSM;
    }

    public String getSerieRegExpediente() {
        return serieRegExpediente;
    }

    public void setSerieRegExpediente(String serieRegExpediente) {
        this.serieRegExpediente = serieRegExpediente;
    }

    public RenSolicitudRenovacion getRenovacion() {
        return renovacion;
    }

    public void setRenovacion(RenSolicitudRenovacion renovacion) {
        this.renovacion = renovacion;
    }

    public String getSerieExtension() {
        return serieExtension;
    }

    public void setSerieExtension(String serieExtension) {
        this.serieExtension = serieExtension;
    }

    public ObservacionTramiteService getObservacionTramiteService() {
        return observacionTramiteService;
    }

    public void setObservacionTramiteService(ObservacionTramiteService observacionTramiteService) {
        this.observacionTramiteService = observacionTramiteService;
    }

    public ObservacionTramite getObservacionTramite() {
        return observacionTramite;
    }

    public void setObservacionTramite(ObservacionTramite observacionTramite) {
        this.observacionTramite = observacionTramite;
    }

    public SeguimientoService getSeguimientoService() {
        return seguimientoService;
    }

    public void setSeguimientoService(SeguimientoService seguimientoService) {
        this.seguimientoService = seguimientoService;
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

    public Seguimiento getUltimoSeguimiento() {
        return ultimoSeguimiento;
    }

    public void setUltimoSeguimiento(Seguimiento ultimoSeguimiento) {
        this.ultimoSeguimiento = ultimoSeguimiento;
    }

    public Boolean getTipoMarcaDialogoRender() {
        return tipoMarcaDialogoRender;
    }

    public void setTipoMarcaDialogoRender(Boolean tipoMarcaDialogoRender) {
        this.tipoMarcaDialogoRender = tipoMarcaDialogoRender;
    }

    public Usuario getUsuario1() {
        return usuario1;
    }

    public void setUsuario1(Usuario usuario1) {
        this.usuario1 = usuario1;
    }

    public UsuarioService getUsuarioService() {
        return usuarioService;
    }

    public void setUsuarioService(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    public Regional getRegional() {
        return regional;
    }

    public void setRegional(Regional regional) {
        this.regional = regional;
    }

    public SigSolicitanteApoderadoService getSigSolicitanteApoderadoService() {
        return sigSolicitanteApoderadoService;
    }

    public void setSigSolicitanteApoderadoService(SigSolicitanteApoderadoService sigSolicitanteApoderadoService) {
        this.sigSolicitanteApoderadoService = sigSolicitanteApoderadoService;
    }

    public LogTransService getLogTransService() {
        return logTransService;
    }

    public void setLogTransService(LogTransService logTransService) {
        this.logTransService = logTransService;
    }

    public EtapaService getEtapaService() {
        return etapaService;
    }

    public void setEtapaService(EtapaService etapaService) {
        this.etapaService = etapaService;
    }

    public List<Etapa> getListEtapaUs() {
        return listEtapaUs;
    }

    public void setListEtapaUs(List<Etapa> listEtapaUs) {
        this.listEtapaUs = listEtapaUs;
    }

    public ObjetoEliminadoGenerico getObjetoEliminadoGenerico() {
        return objetoEliminadoGenerico;
    }

    public void setObjetoEliminadoGenerico(ObjetoEliminadoGenerico objetoEliminadoGenerico) {
        this.objetoEliminadoGenerico = objetoEliminadoGenerico;
    }

    public String getTipoSignoDescripcion() {
        return tipoSignoDescripcion;
    }

    public void setTipoSignoDescripcion(String tipoSignoDescripcion) {
        this.tipoSignoDescripcion = tipoSignoDescripcion;
    }

    public FlujoTiempoService getFlujoTiempoService() {
        return flujoTiempoService;
    }

    public void setFlujoTiempoService(FlujoTiempoService flujoTiempoService) {
        this.flujoTiempoService = flujoTiempoService;
    }

    public Boolean getBotonRecepcionarRendered() {
        return botonRecepcionarRendered;
    }

    public void setBotonRecepcionarRendered(Boolean botonRecepcionarRendered) {
        this.botonRecepcionarRendered = botonRecepcionarRendered;
    }

    public Boolean getBotonFinalizarRendered() {
        return botonFinalizarRendered;
    }

    public void setBotonFinalizarRendered(Boolean botonFinalizarRendered) {
        this.botonFinalizarRendered = botonFinalizarRendered;
    }

    public FlujoSeguimientoService getFlujoSeguimientoService() {
        return flujoSeguimientoService;
    }

    public void setFlujoSeguimientoService(FlujoSeguimientoService flujoSeguimientoService) {
        this.flujoSeguimientoService = flujoSeguimientoService;
    }

    public Boolean getEsRevocatoria() {
        return esRevocatoria;
    }

    public void setEsRevocatoria(Boolean esRevocatoria) {
        this.esRevocatoria = esRevocatoria;
    }

    public HistorialService getHistorialService() {
        return historialService;
    }

    public void setHistorialService(HistorialService historialService) {
        this.historialService = historialService;
    }

    public String getNumeroFormulario() {
        return numeroFormulario;
    }

    public void setNumeroFormulario(String numeroFormulario) {
        this.numeroFormulario = numeroFormulario;
    }

    public Boolean getPanelEncontrado() {
        return panelEncontrado;
    }

    public void setPanelEncontrado(Boolean panelEncontrado) {
        this.panelEncontrado = panelEncontrado;
    }

    public FormularioPI104Service getFormularioPI104Service() {
        return formularioPI104Service;
    }

    public void setFormularioPI104Service(FormularioPI104Service formularioPI104Service) {
        this.formularioPI104Service = formularioPI104Service;
    }

    public FormularioPI104 getFormularioPI104() {
        return formularioPI104;
    }

    public void setFormularioPI104(FormularioPI104 formularioPI104) {
        this.formularioPI104 = formularioPI104;
    }

    public FormularioService getFormularioService() {
        return formularioService;
    }

    public void setFormularioService(FormularioService formularioService) {
        this.formularioService = formularioService;
    }

    public List<Solicitantes> getListaRenSolicitantesSipi() {
        return listaRenSolicitantesSipi;
    }

    public void setListaRenSolicitantesSipi(List<Solicitantes> listaRenSolicitantesSipi) {
        this.listaRenSolicitantesSipi = listaRenSolicitantesSipi;
    }

    public List<Apoderados> getListaRenApoderadosSipi() {
        return listaRenApoderadosSipi;
    }

    public void setListaRenApoderadosSipi(List<Apoderados> listaRenApoderadosSipi) {
        this.listaRenApoderadosSipi = listaRenApoderadosSipi;
    }

    public DireccionNotificaciones getDireccionNotificacionSipi() {
        return direccionNotificacionSipi;
    }

    public void setDireccionNotificacionSipi(DireccionNotificaciones direccionNotificacionSipi) {
        this.direccionNotificacionSipi = direccionNotificacionSipi;
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

    public SolicitudRenovaciones getSolicitudRenovacionesSipi() {
        return solicitudRenovacionesSipi;
    }

    public void setSolicitudRenovacionesSipi(SolicitudRenovaciones solicitudRenovacionesSipi) {
        this.solicitudRenovacionesSipi = solicitudRenovacionesSipi;
    }

    public String getVersionReclasificacion() {
        return versionReclasificacion;
    }

    public void setVersionReclasificacion(String versionReclasificacion) {
        this.versionReclasificacion = versionReclasificacion;
    }

}
//</editor-fold>

