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
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import snp.gob.bo.entidades.bean.common.AbstractManagedBean;
import snp.gob.bo.entidades.expediente.ExamenSignosBean;
import snp.gob.bo.gimodel.entidad.ClaseNiza;
import snp.gob.bo.gimodel.entidad.Correlativo;
import snp.gob.bo.gimodel.entidad.Dominio;
import snp.gob.bo.gimodel.entidad.Etapa;
import snp.gob.bo.gimodel.entidad.LogTrans;
import snp.gob.bo.gimodel.entidad.Notificacion;
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
import snp.gob.bo.gimodel.entidad.SigSignoClaseNiza;
import snp.gob.bo.gimodel.entidad.SigSignoMarca;
import snp.gob.bo.gimodel.entidad.SigSolicitanteApoderado;
import snp.gob.bo.gimodel.entidad.SigTipoSigno;
import snp.gob.bo.gimodel.entidad.Usuario;
import snp.gob.bo.gimodel.entidad.ObjetoEliminadoGenerico;
import snp.gob.bo.gimodel.enums.EnumEstado;
import snp.gob.bo.gimodel.enums.EnumEstadoRenovacion;
import snp.gob.bo.gimodel.enums.EnumNombreDominio;
import snp.gob.bo.gimodel.enums.EnumOperacion;
import snp.gob.bo.gimodel.enums.EnumPrefijoTablas;
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

/**
 *
 * @author ChanoRojas
 */
@ManagedBean(name = "renovacionBean")
@ViewScoped
public class RenovacionBean extends AbstractManagedBean implements Serializable {

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

    @ManagedProperty("#{claseNizaService}")
    private ClaseNizaService claseNizaService;

    @ManagedProperty(value = "#{flujoTiempoService}")
    private FlujoTiempoService flujoTiempoService;

    @ManagedProperty(value = "#{historialService}")
    private HistorialService historialService;

//<editor-fold defaultstate="collapsed" desc="-----------------VARIABLES DE ENTORNO--------------">
    private RenRenovacion renRenovacion;
    private SigSignoMarca sigSignoMarca;
    private List<RenRenovacion> listaRenovacion = new ArrayList<RenRenovacion>();
    private RenSolicitudRenovacion renSolicitudRenovacion;
    private RenSolicitudRenovacion renovacion;
    private List<RenSolicitudRenovacion> listaSolicitudRenovacion = new ArrayList<RenSolicitudRenovacion>();
    private RenSolicitanteApoderado renSolicitanteApoderado;
    private RenSolicitanteApoderado renSolicitante;
    private RenSolicitanteApoderado renApoderado;
    private RenSolicitanteApoderado renSolicitanteApoderadoSelect;
    private List<RenSolicitanteApoderado> listarenSolicitanteApoderado = new ArrayList<RenSolicitanteApoderado>();
    private List<RenSolicitanteApoderado> listarenApoderado = new ArrayList<RenSolicitanteApoderado>();
    private List<RenSolicitanteApoderado> listarenSolicitante = new ArrayList<RenSolicitanteApoderado>();
    private RenDireccionDeNotificacion renDireccionDeNotificacion;
    private List<RenDireccionDeNotificacion> listadireccionDeNotificacion = new ArrayList<RenDireccionDeNotificacion>();
    private List<RenTitularRegistrado> listarenTitularRegistrado = new ArrayList<RenTitularRegistrado>();
    private List<RenTitularRegistrado> listaTitularRegistrado = new ArrayList<RenTitularRegistrado>();
    private RenTitularRegistrado renTitularRegistrado;
    private RenResolucion renResolucion;
    private List<RenResolucion> listarenResolucion = new ArrayList<RenResolucion>();
    private Long numeroSr;
    private Integer gestion;
    private String usuario;
    private RenTitularRegistrado titularRegistradoSelect = new RenTitularRegistrado();
    private List<OrdinalesPojo> listaOrdinales = new ArrayList<OrdinalesPojo>();
    private Correlativo correlativo;
    private ObjetoEliminadoGenerico objetoEliminadoGenerico;
    //listado de parametricas
    private List<Dominio> listaTipoRecibo = new ArrayList<Dominio>();
    private List<Dominio> listaTipoTitulo = new ArrayList<Dominio>();
    private List<Dominio> listaCiudadOficina = new ArrayList<Dominio>();
    private List<Dominio> listaSerieRegistro = new ArrayList<Dominio>();
    private List<Dominio> listaSerieRenovacion = new ArrayList<Dominio>();
    private List<Dominio> listaTipoSigno = new ArrayList<Dominio>();
    private List<Dominio> listaTipoMarca = new ArrayList<Dominio>();
    private List<Dominio> lstTipoUbicacion = new ArrayList<Dominio>();
    private List<Dominio> lstTipoSituacion = new ArrayList<Dominio>();
    private List<Dominio> listaLugarExpedicion = new ArrayList<Dominio>();
    private List<Dominio> listaPais = new ArrayList<Dominio>();
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
    private String version;
    private String versionRecla;
    private List<String> lstClaseNizaVersion = new ArrayList<String>();
    private List<String> lstClaseNizaVersionRecla = new ArrayList<String>();
    private List<ClaseNiza> lstClaseNiza = new ArrayList<ClaseNiza>();
    private List<ClaseNiza> lstClaseNizaRecla = new ArrayList<ClaseNiza>();
    private ClaseNiza claseNiza;
    private Boolean botonRecepcionarRendered = false;
    private Boolean botonFinalizarRendered = false;
    private Boolean[] RecepcionarFinalizar = new Boolean[4];
    private Boolean esRevocatoria = false;
    private Boolean seguimientoAutomatico = false;

//</editor-fold>
    private ObservacionTramite observacionTramite = new ObservacionTramite();
    private Long idSolicitudRenovacion;
//</editor-fold>

    public RenovacionBean() {

        // listEtapaUs = new ArrayList<>();
    }

    @PostConstruct
    public void initRenovacionBean() {
        try {
            listEtapaUs = etapaService.listadoPorIdUsuario(super.getIdUsuarioSession(), getIdEtapaSession());
            RecepcionarFinalizar = flujoSeguimientoService.configuracionBotonesRecepcionarFinalizar(super.getIdEtapaSession(), EnumPrefijoTablas.RENOVACION.getCodigo());
            botonRecepcionarRendered = RecepcionarFinalizar[0];
            botonFinalizarRendered = RecepcionarFinalizar[1];
            seguimientoAutomatico = RecepcionarFinalizar[2];
            obtenerParametrosBusquedaExpedienteRenovacion();
            inicializaValores();
            setidRenSolicitudRenovacionSession(null);
//            setIdUsuarioSession(null);
        } catch (Exception e) {
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

//            generaSM(nueva);
            numeroSr = nueva.getSr();
            gestion = nueva.getGestion_sr();
            situacion = nueva.getEstado_renovacion();
            accion_buscaDatosSolicitudRenovacion();
//            mostrarRecepcionarFinalizar();
//            recuperaDatosSolicitudRenovacion(renSolicitudRenovacion);
//            renSolicitudRenovacion = new RenSolicitudRenovacion();

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
        version = "11";
        lstClaseNiza = claseNizaService.obtenerListadoClaseNizaVersion("11");
        versionRecla = "11";
        lstClaseNizaRecla = claseNizaService.obtenerListadoClaseNizaVersion("11");
        listEtapaUs = etapaService.listadoPorIdUsuario(super.getIdUsuarioSession(), getIdEtapaSession());
    }

//    public void generaSM(RenSolicitudRenovacion rensolicitudRenovacion) {
//        if (rensolicitudRenovacion != null) {
//            if (sigSignoMarcaService.listaSigSignoMarcaXRegistroyClaseNiza(rensolicitudRenovacion.getNumero_registro_registrado(), rensolicitudRenovacion.getSerie_registro_registrado(), "signo",rensolicitudRenovacion.getid) != null) {
//                SigSignoMarca sigsgiMarca = sigSignoMarcaService.listaSigSignoMarcaXRegistro(rensolicitudRenovacion.getNumero_registro_registrado(), rensolicitudRenovacion.getSerie_registro_registrado(), "signo");
//                numeroSM = sigSignoMarca.getNumero().longValue();
//                gestionSM = sigSignoMarca.getGestion().longValue();
//                if (sigSignoMarca.getExtensionMarca() != null) {
//                    extensionSM = sigSignoMarca.getExtensionMarca().toString();
//                    if (extensionSM.equals("0")) {
//                        extensionSM = null;
//                    }
//
//                } else {
//                    extensionSM = null;
//                }
//            }
//
//        }
//
//    }
    
     public void generaSM(RenSolicitudRenovacion rensolicitudRenovacion) throws Exception {
        if (rensolicitudRenovacion != null) {
            if (sigSignoMarcaService.listaSigSignoMarcaXRegistroyClaseNiza(rensolicitudRenovacion.getNumero_registro_registrado(), rensolicitudRenovacion.getSerie_registro_registrado(), "signo",rensolicitudRenovacion.getIdclase_niza_registrado()) != null) {
                SigSignoMarca sigsgiMarca = sigSignoMarcaService.listaSigSignoMarcaXRegistroyClaseNiza(rensolicitudRenovacion.getNumero_registro_registrado(), rensolicitudRenovacion.getSerie_registro_registrado(), "signo",rensolicitudRenovacion.getIdclase_niza_registrado());
                inicializaValoresSM(sigSignoMarca);
                
                
                
//                numeroSM = sigSignoMarca.getNumero().longValue();
//                gestionSM = sigSignoMarca.getGestion().longValue();
//                if (sigSignoMarca.getExtensionMarca() != null) {
//                    extensionSM = sigSignoMarca.getExtensionMarca().toString();
//                    if (extensionSM.equals("0")) {
//                        extensionSM = null;
//                    }
//
//                } else {
//                    extensionSM = null;
//                }
            }

        }

    }
    
    
    
    
    
    

     void inicializaValoresSM(SigSignoMarca sigSignoMarca) throws Exception {
        if (sigSignoMarca != null) {
            if (sigSignoMarca.getIdSignoMarca() != null) {
                HashMap mapResultado = comunService.obtenerNumeroGestionExtensionCodigoSM(sigSignoMarca.getSm());
                numeroSM = Long.parseLong(mapResultado.get("numero").toString());
                gestionSM = Long.parseLong(mapResultado.get("gestion").toString());
                extensionSM = mapResultado.get("extension").toString();
            }
        }
    }
    
    
    
    
    
    
    public void obtenerParametrosBusquedaExpedienteRenovacion() {
        try {

            Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
            String parIdSigno = (String) (params.get("ThrEimhaJd5"));
            String parIdUsuario = (String) (params.get("UkYJ0g3jLwc"));
            if (parIdSigno != null && parIdUsuario != null) {
                if (!parIdSigno.trim().equals("") && !parIdUsuario.trim().equals("") && !parIdSigno.equals("null") && !parIdUsuario.equals("null")) {
                    setIdUsuarioSession(Long.parseLong(parIdUsuario));
                    setidRenSolicitudRenovacionSession(Long.parseLong(parIdSigno));
                }
            }
        } catch (Exception e) {
            Logger.getLogger(ExamenSignosBean.class
                    .getName()).log(Level.SEVERE, null, e);
        }

    }

    public void abrirExpedienteSignos(RenSolicitudRenovacion renSolicitudRenovacion) {
        if (renSolicitudRenovacion != null) {
            SigSignoMarca sigsgiMarca = sigSignoMarcaService.listaSigSignoMarcaXRegistroyClaseNiza(renSolicitudRenovacion.getNumero_registro_registrado(), renSolicitudRenovacion.getSerie_registro_registrado(), "signo",renSolicitudRenovacion.getIdclase_niza_registrado());
            if (sigsgiMarca != null) {
                setIdSignoSession(sigsgiMarca.getIdSignoMarca());
                setIdUsuarioSession(usuario1.getIdusuario());
                setNavegaPagina("template");
                Map<String, Object> options = new HashMap<String, Object>();
                options.put("closable", true);
                options.put("resizable", false);
                options.put("height", 700);
                options.put("width", 1220);
                options.put("contentWidth", "100%");
                options.put("contentHeight", "100%");
                options.put("modal", true);
//            Map<String, List<String>> var = new HashMap<String, List<String>>();
//            List<String> params = new ArrayList();
//            params.add("1");
//            params.add(EnumPrefijoTablas.MODIFICACION.getCodigo());
//            params.add(modificacion.getIdmodificacion().toString());
//            params.add(modificacion.getSigla() + "-" + String.format("%6s", modificacion.getNumero()).replace(' ', '0') + "-" + modificacion.getGestion());
//
//            var.put("datosEnviados", params);
                RequestContext.getCurrentInstance().openDialog("examenSignos", options, null);
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "No se ha podido encontrar el expediemte del Signo Principal", ""));
            }
//        System.out.println("METODO PARA NAVEGAR AL EXPEDIENTE  "+signoMarca);
//        if(signoMarca!=null){
//            setIdSignoSession(signoMarca.getIdSignoMarca());
//            setNavegaPagina("template");
//            setIdmodificacion(modificacion.getIdmodificacion());
//            System.out.println("idsession   "+getIdSignoSession()+"  PAGINA  "+getNavegaPagina());
//            return "examenSignos";
//        }
//        else{
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "No se ha podido encontrar el expediemte del Signo", ""));
//            return "";            
//        }
        }

    }

    public void accion_buscaDatosSolicitudRenovacion() throws Exception {
        try {
            if (numeroSr != null && gestion != null) {
                renSolicitudRenovacion = renSolicitudRenovacionService.buscaSolicitudRenovacionPorNumeroSrYGestion(numeroSr, gestion);
                if (renSolicitudRenovacion.getIdsolicitudrenovacion() != null) {
                    System.out.println("rensolicitudrenovacion"+renSolicitudRenovacion.getIdclase_niza_reclasificacion()+"###########");
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
                    this.idSolicitudRenovacion = renSolicitudRenovacion.getIdsolicitudrenovacion();
                    sigSignoMarca = sigSignoMarcaService.listaSigSignoMarcaXRegistroyClaseNiza(renSolicitudRenovacion.getNumero_registro_registrado(), renSolicitudRenovacion.getSerie_registro_registrado(), " ",renSolicitudRenovacion.getIdclase_niza_registrado());
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
                    if (renSolicitudRenovacion.getSm() != null && renSolicitudRenovacion.getSm() != 0) {
                        HashMap resultado = comunService.obtenerNumeroGestionExtensionCodigoSM(renSolicitudRenovacion.getSm());
                        numeroSM = Long.parseLong(resultado.get("numero").toString());
                        gestionSM = Long.parseLong(resultado.get("gestion").toString());
                        extensionSM = resultado.get("extension").toString();
                    } else {
                        numeroSM = null;
                        gestionSM = null;
                        extensionSM = null;
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

                        }
                    }
                    generaSM(renSolicitudRenovacion);
                    //versionRecla = claseNizaService.listarClaseNiza_id(renSolicitudRenovacion.getIdclase_niza_reclasificacion()).getVersion();
                    versionRecla = claseNizaService.listarClaseNiza_id(renSolicitudRenovacion.getIdclase_niza_reclasificacion()).getNumeroEdicion();
                    lstClaseNizaRecla = claseNizaService.obtenerListadoClaseNizaVersion(versionRecla);
                    //version = claseNizaService.listarClaseNiza_id(renSolicitudRenovacion.getIdclase_niza_registrado()).getVersion();
                    version = claseNizaService.listarClaseNiza_id(renSolicitudRenovacion.getIdclase_niza_registrado()).getNumeroEdicion();
                    lstClaseNiza = claseNizaService.obtenerListadoClaseNizaVersion(version);
//              descomentar esta linea para observaciones//////////////////////////////////////////////////////////////////////////////////////////////////////////**/***/**///*/*/*/*/*/*/*/*/*/*/*/*/
//              observacionTramite = observacionTramiteService.obtenerUltimaObservacionTramite(EnumPrefijoTablas.RENOVACION.getCodigo(), renSolicitudRenovacion.getIdsolicitudrenovacion());
                    System.out.println("/*/*/*/*/*/*/*/renSolicitudRenovacion.getIdsolicitudrenovacion()::" + renSolicitudRenovacion.getIdsolicitudrenovacion());
                    mostrarRecepcionarFinalizar();
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
        listarenSolicitante = new ArrayList<>();
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
                sigSignoMarca = sigSignoMarcaService.listaSigSignoMarcaXRegistroyClaseNiza(renSolicitudRenovacion.getNumero_registro_registrado(), renSolicitudRenovacion.getSerie_registro_registrado(), " ",renSolicitudRenovacion.getIdclase_niza_registrado());
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



    public void buscarXPublicacion() throws Exception {
        if (nroPublicacionExpediente != null) {
            SigSignoMarca encontradoSigno = sigSignoMarcaService.listaSigSignoMarcaXPublicacion(nroPublicacionExpediente);
            mostrarDatosEncontrados(encontradoSigno);
        }
    }

    public void mostrarDatosEncontrados(SigSignoMarca encontrado) throws Exception {
        renovacion = new RenSolicitudRenovacion();
        if (encontrado != null) {
            if (encontrado.getSm() != null && encontrado.getSm() != 0) {
                HashMap resultado = comunService.obtenerNumeroGestionExtensionCodigoSM(encontrado.getSm());
                numeroSM = Long.parseLong(resultado.get("numero").toString());
                gestionSM = Long.parseLong(resultado.get("gestion").toString());
                extensionSM = resultado.get("extension").toString();
            }

            renovacion.setSm(encontrado.getSm());
            renovacion.setNumero_registro_registrado(encontrado.getNumeroRegistro());
            renovacion.setSerie_registro_registrado(encontrado.getSerieRegistro());
//            renovacion.setNumero(encontrado.getNumeroPublicacion());
            renovacion.setSigno_registrado(encontrado.getSigno());
            SigSignoClaseNiza sigSignoClaseNiza = sigSignoClaseNizaService.obtenerListaSigSignoClazeNizaXIdSignoMarca(encontrado.getIdSignoMarca()).get(0);
            renovacion.setIdclase_niza_registrado((sigSignoClaseNiza.getNumeroClaseNiza()));
//            renovacion.setLista_productos_limitacion(sigSignoClaseNiza.getListaProducto());
            tipoMarca = encontrado.getTipoGenero();//tipogenero = tipomarca}
            activaPanelLema();
            List<SigTipoSigno> listatipoSigno = sigTipoSignoService.listaSigTipoSignoXidSIgnoMarca(encontrado.getIdSignoMarca());
            if (!listatipoSigno.isEmpty()) {
                int i = 0;
                tipoGeneroSeleccionado = new String[10];
                for (SigTipoSigno item : listatipoSigno) {
                    tipoGeneroSeleccionado[i] = item.getTipoSigno();
                    if (item.getTipoSigno().equals("OTRO")) {
                        textoOtro = item.getDescripcionOtro();
                        txtOtroRendered = true;
                    }
                    i++;
                }
            }

            List<SigSolicitanteApoderado> listaSolicitanteApoderadoSig = sigSolicitanteApoderadoService.listadoSolicitanteXidsignomarca(encontrado.getIdSignoMarca());
            for (SigSolicitanteApoderado item : listaSolicitanteApoderadoSig) {
                try {
                    RenTitularRegistrado titularRegistrado = new RenTitularRegistrado();
                    String nombre = devuelveNombreJuridicoONatural(item.getNombreRazonSocial(), item.getPrimerApellido(), item.getSegundoApellido());
                    titularRegistrado.setNombre_razonsocial(nombre);
                    titularRegistrado.setDireccion(item.getDireccion());
                    listaTitularRegistrado.add(titularRegistrado);

                } catch (Exception ex) {
                    throw ex;
                }
            }
//        QUEDA PENDIENTE RECUPERAR SOLICITANTE Y APODERADO PREGUNTAR SI SERA NECESARIO
        }
//         inicializaTodo();
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

    public void cargaDialogo() {
        Map<String, Object> options = new HashMap<String, Object>();
        options.put("closable", false);
        options.put("resizable", false);
        options.put("height", 510);
        options.put("width", 780);
        options.put("contentWidth", "100%");
        options.put("contentHeight", "100%");
        options.put("modal", true);
        options.put("closable", false);
        RequestContext.getCurrentInstance().openDialog("renAgregarSolicitanteApoderado", options, null);
    }

    public void cargaDialogoRegistro() {
        setIdUsuarioSession(usuario1.getIdusuario());
        Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        Map<String, Object> options = new HashMap<String, Object>();
        
        System.out.println("hdjkashdjkahdjkas"+renSolicitudRenovacion.getIdclase_niza_reclasificacion());
        sessionMap.put("solicitudRenovacion", renSolicitudRenovacion);
        options.put("closable", true);
        options.put("resizable", false);
        options.put("height", 730);
        options.put("width", 1220);
        options.put("contentWidth", "100%");
        options.put("contentHeight", "100%");
        options.put("modal", true);
        RequestContext.getCurrentInstance().openDialog("busquedaRenovacion", options, null);
    }

    public void terminaDialogoRegistro(SelectEvent event) {
//        RenSolicitudRenovacion renSolicitudRenovacionA;
        if (event.getObject() != "Exit") {
//          renSolicitudRenovacionA = (RenSolicitudRenovacion) event.getObject();
            renSolicitudRenovacion = (RenSolicitudRenovacion) event.getObject();
            renRenovacion = renRenovacionService.obtieneRenovacionPorIdSolicitud(renSolicitudRenovacion.getIdsolicitudrenovacion());
            renResolucion = renResolucionService.obtieneRenResolucionPorIdRenovacion(renRenovacion.getIdrenovacion());
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(" "));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cerrado"));
        }
    }

    public void cargaDialogoApoderado() {
        Map<String, List<String>> params = new HashMap<String, List<String>>();
        List<String> list = new ArrayList<String>();
        list.add(EnumTipoPersona.APODERADO.getCodigo());
        params.put("id", list);
        Map<String, Object> options = new HashMap<String, Object>();
        options.put("closable", false);
        options.put("resizable", false);
        options.put("height", 510);
        options.put("width", 785);
        options.put("contentWidth", "100%");
        options.put("contentHeight", "100%");
        options.put("modal", true);
        options.put("closable", false);
        RequestContext.getCurrentInstance().openDialog("renAgregarSolicitanteApoderado", options, params);
    }

    public void cargaDialogoNuevoTitular() {
        Map<String, List<String>> params = new HashMap<String, List<String>>();
        List<String> list = new ArrayList<String>();
        params.put("id", list);
        Map<String, Object> options = new HashMap<String, Object>();
        options.put("resizable", false);
        options.put("draggable", true);
        options.put("modal", true);
        options.put("closable", false);
        options.put("contentHeight", 420);
        options.put("contentWidth", 770);
        RequestContext.getCurrentInstance().openDialog("renAagregarNuevoTitular", options, params);
    }

    public void cargaDialogoTitularRegistrado() {
        Map<String, List<String>> params = new HashMap<String, List<String>>();
        List<String> list = new ArrayList<String>();
        list.add("TREG");
        params.put("id", list);
        Map<String, Object> options = new HashMap<String, Object>();
        options.put("resizable", false);
        options.put("height", 510);
        options.put("width", 780);
        options.put("contentWidth", "100%");
        options.put("contentHeight", "100%");
        options.put("modal", true);
        options.put("closable", false);
        RequestContext.getCurrentInstance().openDialog("renAgregarNuevoTitular", options, params);
    }

    public void terminaDialogo(SelectEvent event) {
        RenSolicitanteApoderado solicitanteApoderado;
        if (event.getObject() != "Exit") {
            solicitanteApoderado = (RenSolicitanteApoderado) event.getObject();
            listarenSolicitante.add(solicitanteApoderado);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(solicitanteApoderado.getNombre_razonsocial()));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cerrado"));
        }
    }

    public void modificarPersona(RenSolicitanteApoderado renSolicitanteApoderado) {
        renSolicitanteApoderadoSelect = renSolicitanteApoderado;
        Map<String, Object> options = new HashMap<String, Object>();
        Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        sessionMap.put("persona", renSolicitanteApoderado);
        options.put("closable", false);
        options.put("resizable", false);
        options.put("height", 510);
        options.put("width", 780);
        options.put("contentWidth", "100%");
        options.put("contentHeight", "100%");
        options.put("modal", true);
        options.put("closable", false);
        RequestContext.getCurrentInstance().openDialog("renAgregarSolicitanteApoderado", options, null);
        indice = renSolicitanteApoderadoService.encuentraPosicionListadoSolicitanteApoderado(listarenSolicitante, renSolicitanteApoderado);

    }

    public void terminaDialogoModificar(SelectEvent event) {
        RenSolicitanteApoderado solicitanteApoderado;
        if (event.getObject() != "Exit") {
            solicitanteApoderado = (RenSolicitanteApoderado) event.getObject();
            listarenSolicitante.remove(renSolicitanteApoderadoSelect);
            listarenSolicitante.add(indice, solicitanteApoderado);
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cerrado"));
        }
        renSolicitanteApoderadoSelect = new RenSolicitanteApoderado();
        Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        sessionMap.remove("persona");
    }

    public void terminaDialogoApoderado(SelectEvent event) {
        RenSolicitanteApoderado solicitanteApoderado;
        if (event.getObject() != "Exit") {
            solicitanteApoderado = (RenSolicitanteApoderado) event.getObject();
            listarenApoderado.add(solicitanteApoderado);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(solicitanteApoderado.getNombre_razonsocial()));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cerrado"));
        }

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

    public void abrirPoderDepositado() throws Exception {
        String testimonio = "";
        String confiere = "";
        String domicilio = "";
        String apoderado = "";
        if (renSolicitudRenovacion.getIdsolicitudrenovacion() != null) {

            for (int i = 0; i <= listarenSolicitante.size() - 1; i++) {
                if (i == 0) {
                    confiere = devuelveNombreJuridicoONatural(listarenSolicitante.get(i).getNombre_razonsocial(),
                            listarenSolicitante.get(i).getPrimer_apellido(),
                            listarenSolicitante.get(i).getSegundo_apellido());
                    domicilio = listarenSolicitante.get(i).getDireccion();

                } else {
                    confiere = confiere + "; " + devuelveNombreJuridicoONatural(listarenSolicitante.get(i).getNombre_razonsocial(),
                            listarenSolicitante.get(i).getPrimer_apellido(),
                            listarenSolicitante.get(i).getSegundo_apellido());
                    domicilio = domicilio + "; " + listarenSolicitante.get(i).getDireccion();
                }

            }

            for (int i = 0; i <= listarenApoderado.size() - 1; i++) {
                if (i == 0) {
                    apoderado = devuelveNombreJuridicoONatural(listarenApoderado.get(i).getNombre_razonsocial(),
                            listarenApoderado.get(i).getPrimer_apellido(),
                            listarenApoderado.get(i).getSegundo_apellido());
                    testimonio = listarenApoderado.get(i).getPoder();
                    //lstApoderados.get(i).get
                } else {
                    apoderado = apoderado + ";" + devuelveNombreJuridicoONatural(listarenApoderado.get(i).getNombre_razonsocial(),
                            listarenApoderado.get(i).getPrimer_apellido(),
                            listarenApoderado.get(i).getSegundo_apellido());
                    testimonio = testimonio + ";" + listarenApoderado.get(i).getPoder();
                }
            }

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
            params.add("SR");//Para otros tendría que ser modificacion u oposicion o renovación
            params.add(renSolicitudRenovacion.getSr().toString());
            params.add(String.valueOf(renSolicitudRenovacion.getGestion_sr()));
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
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Debe realizar una búsqueda previamente", ""));
        }
    }

    public void modificarApoderado(RenSolicitanteApoderado renSolicitanteApoderado) {
        Map<String, List<String>> params = new HashMap<String, List<String>>();
        List<String> list = new ArrayList<String>();
        list.add("APO");
        params.put("id", list);
        renSolicitanteApoderadoSelect = renSolicitanteApoderado;
        Map<String, Object> options = new HashMap<String, Object>();
        Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        sessionMap.put("persona", renSolicitanteApoderado);
        options.put("closable", false);
        options.put("resizable", false);
        options.put("height", 510);
        options.put("width", 780);
        options.put("contentWidth", "100%");
        options.put("contentHeight", "100%");
        options.put("modal", true);
        options.put("closable", false);
        indice = renSolicitanteApoderadoService.encuentraPosicionListadoSolicitanteApoderado(listarenApoderado, renSolicitanteApoderado);
        RequestContext.getCurrentInstance().openDialog("renAgregarSolicitanteApoderado", options, params);
    }

    public void terminaDialogoModificarPersona(SelectEvent event) {
        RenSolicitanteApoderado solicitanteApoderado;
        if (event.getObject() != "Exit") {
            solicitanteApoderado = (RenSolicitanteApoderado) event.getObject();
            listarenApoderado.remove(renSolicitanteApoderadoSelect);
            listarenApoderado.add(indice, solicitanteApoderado);
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cerrado"));
        }
        renSolicitanteApoderadoSelect = new RenSolicitanteApoderado();
        Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        sessionMap.remove("persona");
    }

    public void terminaModificarRegistrado(SelectEvent event) {
        RenTitularRegistrado titularRegistrado;
        if (event.getObject() != "Exit") {
            titularRegistrado = (RenTitularRegistrado) event.getObject();
            listaTitularRegistrado.remove(titularRegistradoSelect);
            listaTitularRegistrado.add(indice, titularRegistrado);

        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cerrado"));
        }
        renSolicitanteApoderadoSelect = new RenSolicitanteApoderado();
        Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        sessionMap.remove("titularRegistrado");
    }

    public void abrirPaginaHistorial() {
        if (renSolicitudRenovacion.getIdsolicitudrenovacion() != null) {
            Map<String, Object> options = new HashMap<String, Object>();
            options.put("closable", true);
            options.put("resizable", false);
            options.put("height", 400);//tamaño del dialogo vertical del cuadro interno
            options.put("width", 900); //tammaño horizontal del dialogo adentro en el formulario
            options.put("contentWidth", "100%"); //MANTENER EL 100% se usa asi si diste x puntos dice cuantos de porcentaje de los xpuntos usaras
            options.put("contentHeight", "100%");//tamaño del dialogo vertical del cuadro externo
            options.put("modal", false);
            options.put("closeOnEscape", true);
            Map<String, List<String>> var = new HashMap<String, List<String>>();
            List<String> params = new ArrayList();
            //Enviar el id del objeto
            params.add(renSolicitudRenovacion.getIdsolicitudrenovacion().toString());
            //en el caso de marcas enviar el tipo SIG
            params.add(EnumPrefijoTablas.RENOVACION.getCodigo());
            //params.add("Enviar el SM EN STRING");
            var.put("datosEnviados", params);
            RequestContext.getCurrentInstance().openDialog("/historial/historialTramite.xhtml", options, var);
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Debe realizar una busqueda previamente"));
        }
    }

    public void accion_siguienteRenovacion() throws Exception {
        if (numeroSr != null && gestion != null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Buscando", ""));
            renSolicitudRenovacion = renSolicitudRenovacionService.obtenerSiguienteRegistroRenSolicitudRenovacion(numeroSr, gestion);
            if (renSolicitudRenovacion.getIdsolicitudrenovacion() != null) {
                recuperarTodo(renSolicitudRenovacion);
                generaSM(renSolicitudRenovacion);
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "No hay solicitudes para mostrar", ""));
                inicializaDatosRenovacion(renSolicitudRenovacion);
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Escriba el numero y gestion de la solicitud", ""));
        }
    }

    public void accion_anteriorRenovacion() throws Exception {

        if (numeroSr != null && gestion != null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Buscando", ""));
            renSolicitudRenovacion = renSolicitudRenovacionService.obtenerAnteriorRegistroRenSolicitudRenovacion(numeroSr, gestion);
            if (renSolicitudRenovacion.getIdsolicitudrenovacion() != null) {
                // reset();
                recuperarTodo(renSolicitudRenovacion);
                generaSM(renSolicitudRenovacion);
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "No hay solicitudes para mostrar", ""));
                inicializaDatosRenovacion(renSolicitudRenovacion);
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Escriba el numero y gestion de la solicitud", ""));
        }
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
            sigSignoMarca = sigSignoMarcaService.listaSigSignoMarcaXRegistroyClaseNiza(renSolicitudRenovacion.getNumero_registro_registrado(), renSolicitudRenovacion.getSerie_registro_registrado(), " ",renSolicitudRenovacion.getIdclase_niza_registrado());
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

    public void viewNoti() throws Exception {
        ///setear las variables para notificacion
        Notificacion notifica = new Notificacion();
//        notifica.setTipo_tramite_notificacion("SM"); 
//        notifica.setExpediente("000001");
//        notifica.setGestion(2016);
//        notifica.setExtension("C");
//        notifica.setDemandante("El Patitio renovado");
//        notifica.setDemandante_solic("La empresa solicitante");
//        notifica.setDemandante_apod("Juan Perez");
//        notifica.setDemandante_direc("La Paz zona sur alto chijini...numero 15");
//        notifica.setDemandante_cel("644515515,4545648");

        if (renSolicitudRenovacion.getIdsolicitudrenovacion() != null) {
            notifica.setTipo_tramite_notificacion("SR");
            notifica.setExpediente(renSolicitudRenovacion.getSr().toString());
            notifica.setGestion(renSolicitudRenovacion.getGestion_sr());
            notifica.setExtension("");
            notifica.setDemandante(renSolicitudRenovacion.getSigno_registrado());
            String textoSolicitante = "";
            for (RenSolicitanteApoderado item : listarenSolicitante) {
                String dato = devuelveNombreJuridicoONatural(item.getNombre_razonsocial(), item.getPrimer_apellido(), item.getSegundo_apellido());
                textoSolicitante = textoSolicitante + dato.trim() + ", ";
            }
            if (textoSolicitante.length() > 2) {
                textoSolicitante = textoSolicitante.substring(0, textoSolicitante.length() - 2);
            }

            notifica.setDemandante_solic(textoSolicitante);//SOLICITA

            String textoApoderado = "";
            for (RenSolicitanteApoderado item : listarenApoderado) {
                String dato = devuelveNombreJuridicoONatural(item.getNombre_razonsocial(), item.getPrimer_apellido(), item.getSegundo_apellido());
                textoApoderado = textoApoderado + dato.trim() + ", ";
            }
            if (textoApoderado.length() > 2) {
                textoApoderado = textoApoderado.substring(0, textoApoderado.length() - 2);
            }
            notifica.setDemandante_apod(textoApoderado);

//            if (renDireccionDeNotificacion.getIddirecciondenotificacion() != null) {
//                String direccion = modDireccionDeNotificacionService.datos_notificacion(
//                        renDireccionDeNotificacion.getCiudad_notificacion(),
//                        renDireccionDeNotificacion.getZona_barrio(), renDireccionDeNotificacion.getAvenida_calle(), renDireccionDeNotificacion.getNumero(),
//                        renDireccionDeNotificacion.getReferencia_direccion(), renDireccionDeNotificacion.getEdificio(), renDireccionDeNotificacion.getPiso(),
//                        renDireccionDeNotificacion.getDepartamento(), renDireccionDeNotificacion.getCorreo_electronico());
//                notifica.setDemandante_direc(direccion);//DE NOTIFICACION
//                notifica.setDemandante_cel(modDireccionDeNotificacionService.datos_celular(renDireccionDeNotificacion.getCelular(), renDireccionDeNotificacion.getCorreo_electronico()));//CEL DE NOTIFICACION Y TELEFIONO
//            }
        }

        Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        sessionMap.put("notifiObj", notifica);
        Map<String, Object> options = new HashMap<String, Object>();
        options.put("resizable", true);
        options.put("height", 700);
        options.put("width", 1220);
        options.put("contentWidth", "100%");
        options.put("contentHeight", "100%");
        options.put("modal", true);

        Map<String, List<String>> var = new HashMap<String, List<String>>();
        List<String> params = new ArrayList();
        params.add("1");//Pondria el primary key para encontrar todos los datos que necesito, aunque lo mejor es que me pase todo lo que necesito en un objeto
        params.add("renovacion");//Para otros tendría que ser modificacion u oposicion o renovación
        var.put("datosGenerales", params);
        RequestContext.getCurrentInstance().openDialog("/notificacion/notiPeticionDialog", options, var);
    }

    public void abrirObservacionTramite() {
//        setNavegaPagina("examenModificacion");
//        return ("abrirObservacionTramite");
        super.setIdUsuarioSession(usuario1.getIdusuario());
        if (renSolicitudRenovacion.getIdsolicitudrenovacion() != null) {
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
            params.add("1");
            params.add(EnumPrefijoTablas.RENOVACION.getCodigo());
            params.add(renSolicitudRenovacion.getIdsolicitudrenovacion().toString());

            var.put("datosGenerales", params);
            RequestContext.getCurrentInstance().openDialog("/observacion/observacionTramite.xhtml", options, var);
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("La solicitud de modificacion no esta registrada"));
        }

    }

    public void terminaDialogoObservacion(SelectEvent event) throws Exception {
        if (event.getObject() != "Exit") {

        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cerrado"));

        }
        observacionTramite = observacionTramiteService.obtenerUltimaObservacionTramite(EnumPrefijoTablas.RENOVACION.getCodigo(), renSolicitudRenovacion.getIdsolicitudrenovacion());

    }

    public void verPanelExpedienteDigital() {
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
        params.add(EnumPrefijoTablas.RENOVACION.getCodigo());
//            params.add(sigSignoMarca.getIdSignoMarca().toString());
//            params.add(sigSignoMarca.getSm().toString());
        var.put("datosEnviados", params);
        RequestContext.getCurrentInstance().openDialog("/digitalizacion/digitalizacion.xhtml", options, var);
    }

    public void terminaDialogoTitularRegistrado(SelectEvent event) {
        RenTitularRegistrado titularRegistrado;
        if (event.getObject() != "Exit") {
            titularRegistrado = (RenTitularRegistrado) event.getObject();
            listaTitularRegistrado.add(titularRegistrado);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(titularRegistrado.getNombre_razonsocial()));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cerrado"));
        }
    }

    public void modificarTitularRegistrado(RenTitularRegistrado titularRegistrado) {
        Map<String, List<String>> params = new HashMap<String, List<String>>();
        List<String> list = new ArrayList<String>();
        list.add("TREG");
        params.put("id", list);
        titularRegistradoSelect = titularRegistrado;
        Map<String, Object> options = new HashMap<String, Object>();
        Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        sessionMap.put("titularRegistrado", titularRegistrado);
        options.put("closable", false);
        options.put("resizable", false);
        options.put("height", 510);
        options.put("width", 780);
        options.put("contentWidth", "100%");
        options.put("contentHeight", "100%");
        options.put("modal", true);
        options.put("closable", false);
        RequestContext.getCurrentInstance().openDialog("renAgregarNuevoTitular", options, params);
        indice = renTitularRegistradoService.encuentraPosicionListadoTitularRegistrado(listaTitularRegistrado, titularRegistrado);
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

    public void navegaVistaSignos() throws Exception {
        if (expedienteService.obtenerSigsignomarca(Long.toString(numeroSM), Long.toString(gestionSM), extensionSM).getIdSignoMarca() != null) {
            setIdSignoSession(expedienteService.obtenerSigsignomarca(Long.toString(numeroSM), Long.toString(gestionSM), extensionSM).getIdSignoMarca());
            setNavegaPagina("template");
            Map<String, Object> options = new HashMap<String, Object>();
            options.put("closable", true);
            options.put("resizable", false);
            options.put("height", 700);
            options.put("width", 1220);
            options.put("contentWidth", "100%");
            options.put("contentHeight", "100%");
            options.put("modal", true);
            RequestContext.getCurrentInstance().openDialog("examenSignos", options, null);
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "No se ha podido encontrar el expedien"
                    + "0te del Signo", ""));
        }

    }

    public void accion_guardaSolicitudDeRenovacion() throws Exception {
        try {
            if (numeroSr != null && gestion != null) {
                if (renSolicitudRenovacionService.buscaSolicitudRenovacionPorNumeroSrYGestion(numeroSr, gestion).getIdsolicitudrenovacion() == null) {
                    renRenovacion = renRenovacionService.obtieneRenRenovacionPorNumeroYgestion(numeroSr, gestion);
                    if (renRenovacion.getIdsolicitudrenovacion() == null) {
                        renSolicitudRenovacion.setSr(numeroSr);
                        renSolicitudRenovacion.setGestion_sr(gestion);
                        renSolicitudRenovacion.setSerie_recibo(serieRecibo);
                        renSolicitudRenovacion.setSm(creaSM(numeroSM, gestionSM, extensionSM));
                        renSolicitudRenovacion.setSerie_titulo(serieTitulo);
                        renSolicitudRenovacion.setSerie_penultima_renovacion(serieRenovacionPenultima);
                        renSolicitudRenovacion.setSerie_ultima_renovacion(serieRenovacion);
                        renSolicitudRenovacion.setSerie_registro_registrado(serieRegistroMarca);
                        renSolicitudRenovacion.setSerie_lc_registrado(serieRegistroMarcaLema);
                        renSolicitudRenovacion.setTipo_genero(tipoMarca);
                        renSolicitudRenovacion.setOficina(oficina);
                        renSolicitudRenovacion.setEstado_renovacion(situacion);
                        renDireccionDeNotificacion.setCiudad_notificacion(ciudadNotificacion);
                        crearListaTipoSigno();
                        renSolicitudRenovacionService.guardaSolicitudGeneral(renSolicitudRenovacion, listarenSolicitante, listarenApoderado, listaTitularRegistrado, renDireccionDeNotificacion, lstModTipoSigno, usuario1);
                        FacesContext.getCurrentInstance().addMessage(null,
                                new FacesMessage(FacesMessage.SEVERITY_WARN, "Solicitud de renovacion fue modificada correctamente", ""));
                    } else {
                        FacesContext.getCurrentInstance().addMessage(null,
                                new FacesMessage(FacesMessage.SEVERITY_WARN, "No se puede modificar la solicitud renovación, porque ya cuenta con número resolución", ""));
                    }
                } else {
                    FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_WARN, "El número de SR ya existe", ""));

                }

            } else {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Ingrese los datos: número solicitud y gestión", ""));
            }
        } catch (Exception e) {
            throw e;
        }
    }

    //modificarenovacion
    public void accion_modificaSolicitudDeRenovacion() throws Exception {
        try {
            if (numeroSr != null && gestion != null) {
                if (renSolicitudRenovacionService.buscaSolicitudRenovacionPorNumeroSrYGestion(numeroSr, gestion).getIdsolicitudrenovacion() != null) {
                    renRenovacion = renRenovacionService.obtieneRenRenovacionPorNumeroYgestion(numeroSr, gestion);
                    if (renRenovacion.getIdsolicitudrenovacion() == null) {
                        renSolicitudRenovacion.setSr(numeroSr);
                        renSolicitudRenovacion.setGestion_sr(gestion);
                        renSolicitudRenovacion.setSerie_recibo(serieRecibo);
                        renSolicitudRenovacion.setSm(creaSM(numeroSM, gestionSM, extensionSM));
                        renSolicitudRenovacion.setSerie_titulo(serieTitulo);
                        renSolicitudRenovacion.setSerie_penultima_renovacion(serieRenovacionPenultima);
                        renSolicitudRenovacion.setSerie_ultima_renovacion(serieRenovacion);
                        renSolicitudRenovacion.setSerie_registro_registrado(serieRegistroMarca);
                        renSolicitudRenovacion.setSerie_lc_registrado(serieRegistroMarcaLema);
                        renSolicitudRenovacion.setTipo_genero(tipoMarca);
                        renSolicitudRenovacion.setOficina(oficina);
                        renSolicitudRenovacion.setEstado_renovacion(situacion);
                        renDireccionDeNotificacion.setCiudad_notificacion(ciudadNotificacion);
                        tipoSignoDescripcion = concatenaLabelTiposigno(crearListaTipoSigno());
                        renSolicitudRenovacionService.guardaSolicitudGeneral(renSolicitudRenovacion, listarenSolicitante, listarenApoderado, listaTitularRegistrado, renDireccionDeNotificacion, crearListaTipoSigno(), usuario1);

                        FacesContext.getCurrentInstance().addMessage(null,
                                new FacesMessage(FacesMessage.SEVERITY_INFO, "Solicitud de renovacion fue modificada correctamente", ""));
                    } else {
                        FacesContext.getCurrentInstance().addMessage(null,
                                new FacesMessage(FacesMessage.SEVERITY_WARN, "No se puede modificar la solicitud renovación, porque ya cuenta con número resolución", ""));
                    }
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

    public void accion_eliminarRegistroSolicitudDeRenovacion() throws Exception {
        try {
            if (numeroSr != null && gestion != null) {
                renRenovacion = renRenovacionService.obtieneRenRenovacionPorNumeroYgestion(numeroSr, gestion);
                if (renRenovacion.getIdsolicitudrenovacion() == null) {
                    renSolicitudRenovacion = renSolicitudRenovacionService.obtenerRegistroRenSolicitudRenovacion(numeroSr, gestion);
                    if (renSolicitudRenovacion.getIdsolicitudrenovacion() != null) {
                        objetoEliminadoGenerico = renSolicitudRenovacionService.obtenerRegistroEliminadoRenSolicitudRenovacion(renSolicitudRenovacion.getNumero_formulario());
                        if (objetoEliminadoGenerico.getId() == 1) {
                            FacesContext.getCurrentInstance().addMessage(null,
                                    new FacesMessage(FacesMessage.SEVERITY_WARN, objetoEliminadoGenerico.getObjetoEliminado(), ""));
                        } else {
                            FacesContext.getCurrentInstance().addMessage(null,
                                    new FacesMessage(FacesMessage.SEVERITY_WARN, objetoEliminadoGenerico.getObjetoEliminado(), ""));
                        }
                    } else {
                        FacesContext.getCurrentInstance().addMessage(null,
                                new FacesMessage(FacesMessage.SEVERITY_WARN, "Y fue eliminado la solicitud renovación ", ""));
                    }

                } else {
                    FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_WARN, "No se puede eliminar la solicitud renovación, porque ya cuenta con número resolución", ""));
                }

            } else {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Ingrese los datos: número solicitud y gestión", ""));
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public List<RenTipoSigno> crearListaTipoSigno() {
        lstModTipoSigno.clear();
        for (String cadena : tipoGeneroSeleccionado) {
            RenTipoSigno tipoSigno = new RenTipoSigno();
            tipoSigno.setTipo_signo(cadena);
            if (cadena.equals("OTRO")) {
                tipoSigno.setDescripcion_otro(textoOtro);
            }
            lstModTipoSigno.add(tipoSigno);
        }
        for (RenTipoSigno renTipoSigno11111 : lstModTipoSigno) {
            //System.out.println("codigo" + renTipoSigno11111.getTipo_signo());
        }
        return lstModTipoSigno;
    }

    //////////////////////////////fecha de creacion 15/09/20106    janoRojas////////////
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

    ///////METODOS BUSCADOR
    public void buscarXRegistro() throws Exception {

        if (registroExpediente != null) {
            SigSignoMarca encontradoSigno = sigSignoMarcaService.listaSigSignoMarcaXRegistro(registroExpediente, serieRegExpediente, " ");
            mostrarDatosEncontrados(encontradoSigno);
        }
    }

    public void buscarXSM() throws Exception {
        if (extensionExpediente == null) {
            extensionExpediente = "";
        }
        if (smExpediente != null && gestionExpediente != null) {//&& gestionExpediente != null
            Long expediente = creaSM(smExpediente, gestionExpediente, extensionExpediente);
            SigSignoMarca encontradoSigno = sigSignoMarcaService.listaSigSignoMarcaXSM(expediente);
            mostrarDatosEncontrados(encontradoSigno);
        }
    }

    public void buscaPorPublicacion() throws Exception {
        if (nroPublicacionExpediente != null) {
            SigSignoMarca encontradoSigno = sigSignoMarcaService.listaSigSignoMarcaXPublicacion(nroPublicacionExpediente); //LIMIT 1 A LA CONSULTA
            mostrarDatosEncontrados(encontradoSigno);
        }
    }

    public void buscaPorRegistro() throws Exception {
        if (registroExpediente != null) {
            SigSignoMarca encontradoSigno = sigSignoMarcaService.listaSigSignoMarcaXRegistroyClaseNiza(registroExpediente, serieRegExpediente, "",renSolicitudRenovacion.getIdclase_niza_registrado()); //LIMIT 1 A LA CONSULTA
            mostrarDatosEncontrados(encontradoSigno);
        }
    }

    public void abrirSeguimiento() {
        if (renSolicitudRenovacion.getIdsolicitudrenovacion() != null) {
            Map<String, Object> options = new HashMap<String, Object>();
            options.put("closable", true);
            options.put("resizable", false);
            options.put("height", 625);
            options.put("width", 1120);
            options.put("contentWidth", "100%");
            options.put("contentHeight", "100%");
            options.put("modal", true);
            Map<String, List<String>> var = new HashMap<String, List<String>>();
            List<String> params = new ArrayList();
            params.add("1");
            params.add(EnumPrefijoTablas.RENOVACION.getCodigo());
            params.add(renSolicitudRenovacion.getIdsolicitudrenovacion().toString());
            params.add("SR" + "-" + String.format("%6s", renSolicitudRenovacion.getSr()).replace(' ', '0') + "-" + renSolicitudRenovacion.getGestion_sr());
            Long idregional = regionalService.lista_IdRegional_TipoCiudadNotificacion(renSolicitudRenovacion.getOficina());
            params.add(idregional.toString());
            var.put("datosEnviados", params);
            RequestContext.getCurrentInstance().openDialog("/seguimiento/controlPlazos.xhtml", options, var);
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Debe realizar una búsqueda previamente", ""));
        }
    }

    public void terminaDialogoSeguimiento(SelectEvent event) throws Exception {
        if (event.getObject() != "Exit") {
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cerrado"));
        }
    }

    public void mostrarRecepcionarFinalizar() {
        //  ultimoSeguimiento = seguimientoService.ultimo_Seguimiento_etapa(EnumPrefijoTablas.RENOVACION.getCodigo(), renSolicitudRenovacion.getIdsolicitudrenovacion(), "EXRE");

//        if (ultimoSeguimiento != null) {
//            if (ultimoSeguimiento.getFechaFin() != null) {
//                botonRecepcionarDisabled = false;
//                botonFinalizarDisabled = true;
//            } else {
//                botonRecepcionarDisabled = true;
//                botonFinalizarDisabled = false;
//            }
//        } else {
//            botonRecepcionarDisabled = false;
//            botonFinalizarDisabled = true;
//        }
        ultimoSeguimiento = seguimientoService.ultimo_Seguimiento_etapa(EnumPrefijoTablas.RENOVACION.getCodigo(), renSolicitudRenovacion.getIdsolicitudrenovacion(), listEtapaUs.get(0).getTipoEtapa());

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

        ultimoSeguimiento = seguimientoService.ultimo_Seguimiento_etapa(EnumPrefijoTablas.RENOVACION.getCodigo(), renSolicitudRenovacion.getIdsolicitudrenovacion(), "RERE");
        if (ultimoSeguimiento != null) {//existe el seguimento de revocatoria 
            if (ultimoSeguimiento.getFechaFin() == null) {//PREGUNTAR SI ESTA INICIADO
                botonRecepcionarDisabled = true;
                botonFinalizarDisabled = false;
                esRevocatoria = true;
            } else {
                esRevocatoria = false;
            }
            //esRevocatoria = ultimoSeguimiento.getEtapa().equals("RERE");
        } else {
            esRevocatoria = false;
        }
        //if (super.getIdEtapaSession() == 18 || super.getIdEtapaSession() == 20 || super.getIdEtapaSession() == 21) {

        System.out.println("");
        if (seguimientoAutomatico) {
            botonFinalizarDisabled = false;
        }
        ultimoSeguimiento = seguimientoService.ultimo_Seguimiento_etapa(EnumPrefijoTablas.RENOVACION.getCodigo(), renSolicitudRenovacion.getIdsolicitudrenovacion(), listEtapaUs.get(0).getTipoEtapa());
        if (ultimoSeguimiento != null) {
            if (ultimoSeguimiento.getFechaFin() != null) {
                if (ultimoSeguimiento.getEtapa().equals("DESG") || ultimoSeguimiento.getEtapa().equals("ENTR") || ultimoSeguimiento.getEtapa().equals("PAFI")) //botonRecepcionarDisabled = false;
                {
                    botonFinalizarDisabled = true;
                }
            }
        }
    }

    public void guardarSeguimiento() throws Exception {
        Date fechaServidor = comunService.obtenerFechaHoraServidor(1L);

        LogTrans logTransGuardado = logTransService.crudLogTrans(new LogTrans(usuario1.getIdusuario(), fechaServidor), 1);
        Seguimiento seguimientoNuevo = new Seguimiento();
        seguimientoNuevo.setId(renSolicitudRenovacion.getIdsolicitudrenovacion());
        seguimientoNuevo.setIdUsuario(usuario1.getIdusuario());
        seguimientoNuevo.setIdLogTrans(logTransGuardado.getIdLogTrans());
        seguimientoNuevo.setSm(renSolicitudRenovacion.getSm());
        //No lo puse porque no tiene  numero de publicacon la tabla rensolicitudrenovacion
        //seguimientoNuevo.setNumeroPublicacion(renSolicitudRenovacion.getNum);
        seguimientoNuevo.setNumeroRegistro(renSolicitudRenovacion.getNumero_registro_registrado());
        seguimientoNuevo.setSerieRegistro(renSolicitudRenovacion.getSerie_registro_registrado());

        if (esRevocatoria) {
            seguimientoNuevo.setEtapa("RERE");
            seguimientoNuevo.setPlazo_limite(flujoTiempoService.obtieneFlujoXIdetapa("RERE", 1));

        } else {
            seguimientoNuevo.setEtapa(listEtapaUs.get(0).getTipoEtapa());
            if (seguimientoService.listaSeguiUltimoXIdrenoEtapaUltMov(renSolicitudRenovacion.getIdsolicitudrenovacion(), listEtapaUs.get(0).getTipoEtapa()) != null) {
                System.out.println("Significa qeu SI tiene mas de un renovacion");
                seguimientoNuevo.setPlazo_limite(flujoTiempoService.obtieneFlujoXIdetapa(listEtapaUs.get(0).getTipoEtapa(), 2));

            } else {
                System.out.println("Significa qeu NO tiene mas de ua renovacion");

                seguimientoNuevo.setPlazo_limite(flujoTiempoService.obtieneFlujoXIdetapa(listEtapaUs.get(0).getTipoEtapa(), 1));
            }
        }

        seguimientoNuevo.setObservacion(null);
        seguimientoNuevo.setEditable(false);
        seguimientoNuevo.setEstado(EnumEstado.ACTIVO.getCodigo());
        if (super.getIdEtapaSession() == 15 || super.getIdEtapaSession() == 16 || esRevocatoria) {
            seguimientoNuevo.setFechaRecepcion(comunService.obtenerFechaHoraServidor(1L));
        } else {
            if (seguimientoService.lista_seguimiento_ultimo(EnumPrefijoTablas.RENOVACION.getCodigo(), renSolicitudRenovacion.getIdsolicitudrenovacion()) != null) {
                Seguimiento seguimientoAnterior = seguimientoService.lista_seguimiento_ultimo(EnumPrefijoTablas.RENOVACION.getCodigo(), renSolicitudRenovacion.getIdsolicitudrenovacion());
                seguimientoNuevo.setFechaRecepcion(new Date(seguimientoAnterior.getFechaFin().getTime() + 0005L));
            } else {

                seguimientoNuevo.setFechaRecepcion(fechaServidor);
            }
        }
        /*if (seguimientoService.listaSeguimientoXIdsegEtapaRenUltMov(renSolicitudRenovacion.getIdsolicitudrenovacion(), listEtapaUs.get(0).getTipoEtapa()) == null) {
         seguimientoNuevo.setFechaRecepcion(fechaServidor);
         } else {
         //            List<Seguimiento> listSeguimientoUltimos = seguimientoService.listaSeguimientoXIdsegEtapaRenUltMov(renSolicitudRenovacion.getIdsolicitudrenovacion(), listEtapaUs.get(0).getTipoEtapa());
         //             Calendar c = Calendar.getInstance();
         //             c.setTime(listSeguimientoUltimos.get(0).getFechaFin());
         //             c.add(Calendar.DATE, 1);
         //             while (c.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY || c.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
         //             c.add(Calendar.DATE, 1);
         //             }

         seguimientoNuevo.setFechaRecepcion(fechaServidor);
         }*/
        ultimoSeguimiento = seguimientoService.guardar_modificar_Seguimiento_etapa(seguimientoNuevo, "REN");
        mostrarRecepcionarFinalizar();
    }

    public void abrirFinalizar() throws Exception {
        Seguimiento seguimientoNuevo = new Seguimiento();
        seguimientoNuevo.setId(renSolicitudRenovacion.getIdsolicitudrenovacion());
        seguimientoNuevo.setIdUsuario((usuario1.getIdusuario()));
        // seguimientoNuevo.setIdLogTrans(1L);
        seguimientoNuevo.setSm(renSolicitudRenovacion.getSm());
        seguimientoNuevo.setNumeroRegistro(renSolicitudRenovacion.getNumero_registro_registrado());
        seguimientoNuevo.setSerieRegistro(renSolicitudRenovacion.getSerie_registro_registrado());
        if (esRevocatoria) {
            seguimientoNuevo.setEtapa("RERE");
            seguimientoNuevo.setPlazo_limite(flujoTiempoService.obtieneFlujoXIdetapa("RERE", 1));

        } else {
            seguimientoNuevo.setEtapa(listEtapaUs.get(0).getTipoEtapa());
            seguimientoNuevo.setPlazo_limite(flujoTiempoService.obtieneFlujoXIdetapa(listEtapaUs.get(0).getTipoEtapa(), 1));
        }
        //seguimientoNuevo.setEtapa(listEtapaUs.get(0).getTipoEtapa());
        if (RecepcionarFinalizar[2]) {
            Seguimiento seguimientoAnterior = seguimientoService.lista_seguimiento_ultimo(EnumPrefijoTablas.RENOVACION.getCodigo(), renSolicitudRenovacion.getIdsolicitudrenovacion());
            if (seguimientoAnterior != null) {
                seguimientoNuevo.setFechaRecepcion(seguimientoAnterior.getFechaFin());
            } else {
                Date fechaSistema = comunService.obtenerFechaHoraServidor(1l);
                seguimientoNuevo.setFechaRecepcion(fechaSistema);
            }
            seguimientoNuevo.setPlazo_limite(flujoTiempoService.obtieneFlujoXIdetapa(listEtapaUs.get(0).getTipoEtapa(), 1));
        }
        seguimientoNuevo.setObservacion(null);
        seguimientoNuevo.setEditable(false);
        seguimientoNuevo.setEstado(EnumEstado.ACTIVO.getCodigo());
        seguimientoNuevo.setFechaFin(comunService.obtenerFechaHoraServidor(1L));
        ultimoSeguimiento = seguimientoService.guardar_modificar_Seguimiento_etapa(seguimientoNuevo, EnumPrefijoTablas.RENOVACION.getCodigo());
        String descripcionUbicacion = ubicacion;
        if (super.getIdEtapaSession() == 18) {
            renSolicitudRenovacion.setUbicacion_renovacion("Ventanilla Unica (Entrega de documentos)");
            ubicacion = "Ventanilla Unica (Entrega de documentos)";
            historialService.guardarHistorialGenerico(EnumPrefijoTablas.RENOVACION.getCodigo(), renSolicitudRenovacion.getIdsolicitudrenovacion(), usuario1.getIdusuario(), "DATOS ADMINISTRATIVOS",
                    EnumOperacion.MODIFICAR.getCodigo(), dominioService.obtenerNombreCodigoDominio("estado_renovacion", renSolicitudRenovacion.getEstado_renovacion()),
                    null, ubicacion, "[Ubic.:" + descripcionUbicacion + "]");
        }
        if (super.getIdEtapaSession() == 20) {
            renSolicitudRenovacion.setUbicacion_renovacion("Biblioteca (Concedida)");
            ubicacion = "Biblioteca (Concedida)";
            historialService.guardarHistorialGenerico(EnumPrefijoTablas.RENOVACION.getCodigo(), renSolicitudRenovacion.getIdsolicitudrenovacion(), usuario1.getIdusuario(), "DATOS ADMINISTRATIVOS",
                    EnumOperacion.MODIFICAR.getCodigo(), dominioService.obtenerNombreCodigoDominio("estado_renovacion", renSolicitudRenovacion.getEstado_renovacion()),
                    null, ubicacion, "[Ubic.:" + descripcionUbicacion + "]");
        }
        if (super.getIdEtapaSession() == 21) {
            renSolicitudRenovacion.setUbicacion_renovacion("Biblioteca (Concedida)");
            ubicacion = "Propiedad Industrial (Desglose)";
            historialService.guardarHistorialGenerico(EnumPrefijoTablas.RENOVACION.getCodigo(), renSolicitudRenovacion.getIdsolicitudrenovacion(), usuario1.getIdusuario(), "DATOS ADMINISTRATIVOS",
                    EnumOperacion.MODIFICAR.getCodigo(), dominioService.obtenerNombreCodigoDominio("estado_renovacion", renSolicitudRenovacion.getEstado_renovacion()),
                    null, ubicacion, "[Ubic.:" + descripcionUbicacion + "]");
        }
        renSolicitudRenovacionService.cruddosRenSolictudRenovacion(renSolicitudRenovacion, 2);
        mostrarRecepcionarFinalizar();

    }

    public String abrirSubsanacion(String idSolicitudRenovacion, String idUsuarioLogin) {
        return "this.form.target = '_blank'; window.open('../renovacion/examenRenSubsanacion.xhtml?ThrEimhaJd5=" + idSolicitudRenovacion + "&UkYJ0g3jLwc=" + idUsuarioLogin + "');";
    }

    public void cambiaListaClase() throws Exception {

        lstClaseNiza = claseNizaService.obtenerListadoClaseNizaVersion(version);
        //claseNiza2 = new ClaseNiza();
    }

    public void cambiaListaClaseRecla() throws Exception {
        lstClaseNizaRecla = new ArrayList<ClaseNiza>();
        lstClaseNizaRecla = claseNizaService.obtenerListadoClaseNizaVersion(versionRecla);

    }

    public Long devuelveClaseNiza(Long idClaseNiza) {
        Long clase_niza = 0l;
        try {
            if (idClaseNiza != null) {
                claseNiza = claseNizaService.listarClaseNiza_id(idClaseNiza);
                if (claseNiza != null) {
                    clase_niza = claseNiza.getNumeroClaseNiza();
                }
            }
        } catch (Exception e) {
            Logger.getLogger(ClaseNiza.class.getName()).log(Level.SEVERE, null, e);
        }

        return clase_niza;
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

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public List<String> getLstClaseNizaVersion() {
        return lstClaseNizaVersion = claseNizaService.lista_version_ClaseNiza();
    }

    public void setLstClaseNizaVersion(List<String> lstClaseNizaVersion) {
        this.lstClaseNizaVersion = lstClaseNizaVersion;
    }

    public ClaseNizaService getClaseNizaService() {
        return claseNizaService;
    }

    public void setClaseNizaService(ClaseNizaService claseNizaService) {
        this.claseNizaService = claseNizaService;
    }

    public List<ClaseNiza> getLstClaseNiza() {
        return lstClaseNiza;
    }

    public void setLstClaseNiza(List<ClaseNiza> lstClaseNiza) {
        this.lstClaseNiza = lstClaseNiza;
    }

    public List<String> getLstClaseNizaVersionRecla() {
        return lstClaseNizaVersionRecla = claseNizaService.lista_version_ClaseNiza();
    }

    public void setLstClaseNizaVersionRecla(List<String> lstClaseNizaVersionRecla) {
        this.lstClaseNizaVersionRecla = lstClaseNizaVersionRecla;
    }

    public String getVersionRecla() {
        return versionRecla;
    }

    public void setVersionRecla(String versionRecla) {
        this.versionRecla = versionRecla;
    }

    public List<ClaseNiza> getLstClaseNizaRecla() {
        return lstClaseNizaRecla;
    }

    public void setLstClaseNizaRecla(List<ClaseNiza> lstClaseNizaRecla) {
        this.lstClaseNizaRecla = lstClaseNizaRecla;
    }

    public FlujoSeguimientoService getFlujoSeguimientoService() {
        return flujoSeguimientoService;
    }

    public void setFlujoSeguimientoService(FlujoSeguimientoService flujoSeguimientoService) {
        this.flujoSeguimientoService = flujoSeguimientoService;
    }

    public FlujoTiempoService getFlujoTiempoService() {
        return flujoTiempoService;
    }

    public void setFlujoTiempoService(FlujoTiempoService flujoTiempoService) {
        this.flujoTiempoService = flujoTiempoService;
    }

    public HistorialService getHistorialService() {
        return historialService;
    }

    public void setHistorialService(HistorialService historialService) {
        this.historialService = historialService;
    }

    public Long getIdSolicitudRenovacion() {
        return idSolicitudRenovacion;
    }

    public void setIdSolicitudRenovacion(Long idSolicitudRenovacion) {
        this.idSolicitudRenovacion = idSolicitudRenovacion;
    }

    public Boolean getBotonRecepcionarRendered() {
        return botonRecepcionarRendered;
    }

    public void setBotonRecepcionarRendered(Boolean botonRecepcionarRendered) {
        this.botonRecepcionarRendered = botonRecepcionarRendered;
    }

    public Boolean getEsRevocatoria() {
        return esRevocatoria;
    }

    public void setEsRevocatoria(Boolean esRevocatoria) {
        this.esRevocatoria = esRevocatoria;
    }

    public Boolean getBotonFinalizarRendered() {
        return botonFinalizarRendered;
    }

    public void setBotonFinalizarRendered(Boolean botonFinalizarRendered) {
        this.botonFinalizarRendered = botonFinalizarRendered;
    }

}
//</editor-fold>

