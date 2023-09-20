/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.entidades.bean.renovacion;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.text.DateFormatSymbols;
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
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import snp.gob.bo.entidades.bean.common.AbstractManagedBean;
import snp.gob.bo.gimodel.entidad.ClaseNiza;
import snp.gob.bo.gimodel.entidad.Dominio;
import snp.gob.bo.gimodel.entidad.Historial;
import snp.gob.bo.gimodel.entidad.Notificacion;
import snp.gob.bo.gimodel.entidad.RenDireccionDeNotificacion;
import snp.gob.bo.gimodel.entidad.RenRenovacion;
import snp.gob.bo.gimodel.entidad.RenResolucion;
import snp.gob.bo.gimodel.entidad.RenSolicitanteApoderado;
import snp.gob.bo.gimodel.entidad.RenSolicitudRenovacion;
import snp.gob.bo.gimodel.entidad.RenTipoSigno;
import snp.gob.bo.gimodel.entidad.RenTitularRegistrado;
import snp.gob.bo.gimodel.entidad.Usuario;
import snp.gob.bo.gimodel.enums.EnumEstado;
import snp.gob.bo.gimodel.enums.EnumNombreDominio;
import snp.gob.bo.gimodel.enums.EnumTipoPersona;
import snp.gob.bo.gimodel.pojo.OrdinalesPojo;
import snp.gob.bo.gimodel.servicio.ClaseNizaService;
import snp.gob.bo.gimodel.servicio.ComunService;
import snp.gob.bo.gimodel.servicio.DominioService;
import snp.gob.bo.gimodel.servicio.EtapaService;
import snp.gob.bo.gimodel.servicio.RegionalService;
import snp.gob.bo.gimodel.servicio.RenDireccionDeNotificacionService;
import snp.gob.bo.gimodel.servicio.RenHistorialService;
import snp.gob.bo.gimodel.servicio.RenRenovacionService;
import snp.gob.bo.gimodel.servicio.RenResolucionService;
import snp.gob.bo.gimodel.servicio.RenSolicitanteApoderadoService;
import snp.gob.bo.gimodel.servicio.RenSolicitudRenovacionService;
import snp.gob.bo.gimodel.servicio.RenTipoSignoService;
import snp.gob.bo.gimodel.servicio.RenTitularRegistradoService;
import snp.gob.bo.gimodel.servicio.UsuarioService;

/**
 *
 * @author ChanoRojas
 */
@ManagedBean(name = "busquedaRenovacionBean")
@ViewScoped
public class BusquedaRenovacionBean extends AbstractManagedBean implements Serializable {

//<editor-fold defaultstate="collapsed" desc="---------------INYECCION DE SERViCIOS----------------">
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

    @ManagedProperty("#{comunService}")
    private ComunService comunService;

    @ManagedProperty("#{etapaService}")
    private EtapaService etapaService;

    @ManagedProperty("#{usuarioService}")
    private UsuarioService usuarioService;

    @ManagedProperty("#{renHistorialService}")
    private RenHistorialService renHistorialService;

    @ManagedProperty("#{claseNizaService}")
    private ClaseNizaService claseNizaService;
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="-----------------VARIABLES DE ENTORNO--------------">
    private RenRenovacion renRenovacion;
    private RenRenovacion renRenovacionGuardar;
    private List<RenRenovacion> listaRenovacion = new ArrayList<RenRenovacion>();
    private RenSolicitudRenovacion renSolicitudRenovacion;
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
    private int gestion;
    private String usuario;
    private RenTitularRegistrado titularRegistradoSelect = new RenTitularRegistrado();
    private List<OrdinalesPojo> listaOrdinales = new ArrayList<OrdinalesPojo>();
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
    private Boolean panelCatalogoRendered = false;
    private Boolean panelVerRendered = false;
    private Boolean mostrarPorAsignar = false;

    private Boolean txtOtroRendered = false;
    private String[] tipoGeneroSeleccionado;
    private String textoOtro = null;
    private String signoRegistradoNuevo;
    private String ordenRenovacion = "";
    private String cadenaTitular = "";
    private String cadenaApoderado = "";
    private String serieRegistroRenovacion;
    private String serieRenovacionNuevo;
    private String cadenaTipoGenero;
    private String cadenaTipoSigno;
    private String numeroAsignadoConfirmacion;
    private int numeroRegistrobuscador;
    private List<RenTipoSigno> lstModTipoSigno = new ArrayList<RenTipoSigno>();

    // parametros del reporte
    private Boolean reporteRecibo1 = false;
    private Boolean reporteRecibo2 = false;
    private Map<String, Object> parametros = new HashMap();
    private String imgSenapiRepo;
    private String tramiteRepo;
    private String fechaSolicitudRepo;
    private String tituloRepo;
    private String numResolucionRepo;
    private String fechaActualRepo;
    private String textoUnicoRepo;
    private String registroRepo;
    private String obsRepo;
    private String usuarioRepo;
    private String titularesRepo = "";
    private String representantesRepo = "";
    private List<RenSolicitanteApoderado> listarenApoderadoRepo = new ArrayList<>();
    private List<RenSolicitanteApoderado> listarenSolicitanteRepo = new ArrayList<>();

    private String signoRepo;
    private String tipoSignoRepo;
    private String generoSignoRepo;
    private Integer clasesNizaRepo;
    private String registrosRepo;
    private String resolucionRepo;
    private String titularRepo;
    private String fechaRegistroRepo;
    private Image fotoRepo = null;
    private String encabesadoRepo;
    private String renovacionRepo;
    private RenRenovacion renRenovacionRepo;
    private RenResolucion renResolucionRepo;
    private RenSolicitudRenovacion renSolicitudRenovacionRepo;
    private List<Historial> ltsHistorial = new ArrayList<>();
    private Usuario usuarioLogueado;
    private Boolean activaAceptarRenovacion;
    private Usuario user;
    private ClaseNiza claseNiza;

//</editor-fold>
    public BusquedaRenovacionBean() {
    }

    @PostConstruct
    public void initBusquedaRenovacionBean() {
        try {
            if (getIdUsuarioSession() != null) {
                usuarioLogueado = usuarioService.buscaUsuarioPorIdUsuario(getIdUsuarioSession());
//            regional = regionalService.obtenerRegionalPorIdRegiona(usuario.getIdregional());
            }

            Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
            if (sessionMap.get("solicitudRenovacion") != null) {
                renSolicitudRenovacion = (RenSolicitudRenovacion) sessionMap.get("solicitudRenovacion");
                System.out.println("ffewfewfew" + renSolicitudRenovacion.getIdclase_niza_reclasificacion());
                try {
                    inicializaDatosRenovacion(renSolicitudRenovacion);
                    user = usuarioService.obtenerUsuario(super.getIdUsuarioSession());
                } catch (Exception ex) {
                    Logger.getLogger(BusquedaRenovacionBean.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                inicializaValores();
            }

        } catch (Exception e) {
        }
    }

//<editor-fold defaultstate="collapsed" desc="-------------------METODOS--------------------">
    public void inicializaValores() {
//        renSolicitudRenovacion = new RenSolicitudRenovacion();
//        renSolicitanteApoderado = new RenSolicitanteApoderado();
//        renDireccionDeNotificacion = new RenDireccionDeNotificacion();
        renRenovacion = new RenRenovacion();
        renResolucion = new RenResolucion();
        renRenovacionGuardar = new RenRenovacion();
        mostrarPorAsignar = false;
        activaAceptarRenovacion = false;

    }

    public void accion_buscaDatosSolicitudRenovacion() throws Exception {
        try {

            renSolicitudRenovacion = renSolicitudRenovacionService.buscaSolicitudRenovacionPorNumeroSrYGestion(numeroSr, gestion);
            if (renSolicitudRenovacion.getIdsolicitudrenovacion() != null) {
                listarenApoderado = (List<RenSolicitanteApoderado>) renSolicitanteApoderadoService.buscaSolicitanteApoderadoPoridSolicitudyTipoPersona(renSolicitudRenovacion.getIdsolicitudrenovacion(), EnumTipoPersona.APODERADO.getCodigo());
                listarenSolicitante = (List<RenSolicitanteApoderado>) renSolicitanteApoderadoService.buscaSolicitanteApoderadoPoridSolicitudyTipoPersona(renSolicitudRenovacion.getIdsolicitudrenovacion(), EnumTipoPersona.SOLICITANTE.getCodigo());
                renDireccionDeNotificacion = renDireccionDeNotificacionService.buscaDireccionDeNotificacionPorIdSolicitud(renSolicitudRenovacion.getIdsolicitudrenovacion());
                listaTitularRegistrado = (List<RenTitularRegistrado>) renTitularRegistradoService.buscaRenTitularRegistradoPorIdSolicitud(renSolicitudRenovacion.getIdsolicitudrenovacion());
                lstModTipoSigno = renTipoSignoService.buscaRenTipoSignoPorIdSolicitud(renSolicitudRenovacion.getIdsolicitudrenovacion());
                renRenovacion = renRenovacionService.obtieneRenovacionPorIdSolicitud(renSolicitudRenovacion.getIdsolicitudrenovacion());
                if (renRenovacion.getIdrenovacion() != null) {
                    renResolucion = renResolucionService.obtieneRenResolucionPorIdRenovacion(renRenovacion.getIdrenovacion());
                }
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
                inicializaDatosRenovacion(renSolicitudRenovacion);
                mostrarPorAsignar = true;
                int i = 0;
                for (RenTipoSigno item : lstModTipoSigno) {
                    tipoGeneroSeleccionado[i] = item.getTipo_signo();
                    if (item.getTipo_signo().equals("OTRO")) {
                        textoOtro = item.getDescripcion_otro();
                        txtOtroRendered = true;
                    }
                    i++;
                }
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public void inicializaDatosRenovacion(RenSolicitudRenovacion renSolicitdRenovacion) throws Exception {
        //recupera renovacion y muestra en el pop
        listaTitularRegistrado = (List<RenTitularRegistrado>) renTitularRegistradoService.buscaRenTitularRegistradoPorIdSolicitud(renSolicitudRenovacion.getIdsolicitudrenovacion());
        renRenovacion = renRenovacionService.obtieneRenovacionPorIdSolicitud(renSolicitdRenovacion.getIdsolicitudrenovacion());
        cadenaTitular = concatenaTitular(listaTitularRegistrado);
        cadenaTipoSigno = concatenaTipoSigno(renTipoSignoService.buscaRenTipoSignoPorIdSolicitud(renSolicitudRenovacion.getIdsolicitudrenovacion()));
        cadenaTipoGenero = renSolicitudRenovacion.getTipo_genero();
        signoRegistradoNuevo = renSolicitudRenovacion.getSigno_registrado();
        numeroAsignadoConfirmacion = numeroRegistrobuscador + "-" + renRenovacion.getSerie_renovacion();
        listarenApoderado = (List<RenSolicitanteApoderado>) renSolicitanteApoderadoService.buscaSolicitanteApoderadoPoridSolicitudyTipoPersona(renSolicitudRenovacion.getIdsolicitudrenovacion(), EnumTipoPersona.APODERADO.getCodigo());
        cadenaApoderado = concatenaApoderado(listarenApoderado);
        if (renRenovacion.getIdrenovacion() != null) {
            ordenRenovacion = devuelveLiteralNumero(renRenovacion.getOrden_renovacion());
            numeroRegistrobuscador = renRenovacion.getNumero_renovacion();
            serieRenovacionNuevo = renRenovacion.getSerie_renovacion();
            renResolucion = renResolucionService.obtieneRenResolucionPorIdRenovacion(renRenovacion.getIdrenovacion());
            mostrarPorAsignar = true;
            activaAceptarRenovacion = true;
        } else {
            mostrarPorAsignar = false;
            activaAceptarRenovacion = false;
            //inicializalaentidadrenrenovacion con valores generados desde la base de datos por fecha de consecion
            inicializaValores();

            System.out.println("ffewfewfew" + renSolicitdRenovacion.getIdclase_niza_reclasificacion());
            if (renSolicitdRenovacion.getIdclase_niza_reclasificacion() != 46  && renSolicitdRenovacion.getIdclase_niza_reclasificacion() != 0) {
                renRenovacion.setIdclase_niza_renovacion(renSolicitdRenovacion.getIdclase_niza_reclasificacion());
            } else {
                renRenovacion.setIdclase_niza_renovacion(renSolicitdRenovacion.getIdclase_niza_registrado());
            }
//            renRenovacion.setFecha_concesion(renRenovacionService.devuelveFechaConcesion(renSolicitdRenovacion.getFecha_registro_registrado()));
            renRenovacion.setFecha_concesion(renRenovacionService.devuelveFechaConcesionFechaSolicitud(renSolicitdRenovacion.getFecha_registro_registrado(), renSolicitdRenovacion.getFecha_ingreso()));
            renRenovacion.setTitular(cadenaTitular);
            renRenovacion.setApoderado(cadenaApoderado);
//            renRenovacion.setTipo_marca(cadenaTipoSigno);
            renRenovacion.setTipo_genero(cadenaTipoGenero);
            renRenovacion.setSigno_registrado(signoRegistradoNuevo);
            renRenovacion.setNumero_registro(renSolicitudRenovacion.getNumero_registro_registrado().intValue());
            renRenovacion.setSerie_registro(renSolicitudRenovacion.getSerie_registro_registrado());
            renRenovacion.setLista_producto_renovacion(renSolicitudRenovacion.getLista_productos_limitacion());
            renRenovacion.setFecha_renovacion(comunService.obtenerFechaHoraServidor(1l));
            renRenovacion.setEstado(EnumEstado.ACTIVO.getCodigo());
            numeroRegistrobuscador = 0;
//            renResolucion.setNumero_resolucion(0);
//            renRenovacion.setOrden_renovacion(renRenovacionService.calculaNumeroDeRenovacion(renSolicitdRenovacion.getFecha_registro_registrado()));
            renRenovacion.setOrden_renovacion(renRenovacionService.calculaNumeroDeRenovacionSegunFechaIngresoRenovacion(renSolicitdRenovacion.getFecha_registro_registrado(), renSolicitdRenovacion.getFecha_ingreso()));
        }
    }

    public Long devuelveClaseNiza(Long idClaseNiza) {
        Long clase_niza = 0l;
        System.out.println("            ----------idclaseniza" + idClaseNiza);
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

    public void accion_GuardaRenovacionResolucion() throws Exception {
        renRenovacionGuardar = new RenRenovacion();
        renSolicitudRenovacionRepo = renSolicitudRenovacionService.obtenerRenSolicitudXIdRenSolicitud(renSolicitudRenovacion.getIdsolicitudrenovacion());
        renRenovacionGuardar = renRenovacionService.guardaRenovacion(renSolicitudRenovacion, renRenovacion, renResolucion, usuarioLogueado);
        mostrarPorAsignar = true;
        activaAceptarRenovacion = true;

    }

    public String formatoFecha(Date fecha) {
        String fechaFormato;
        Calendar cal = Calendar.getInstance();
        cal.setTime(fecha);
        fechaFormato = "A partir del" + " " + fecha.getDate() + " " + "de" + " " + renRenovacionService.devuelvemesLiteral(fecha.getMonth()) + " " + "de" + " " + (cal.get(Calendar.YEAR));
        return fechaFormato;
    }

    public String concatenaTipoSigno(List<RenTipoSigno> lstSigno) throws Exception {
        String signo = "";
        int sw = 0;
        for (RenTipoSigno renTipoSigno : lstSigno) {
            if (sw == 0) {
                if (renTipoSigno.getTipo_signo().equals("OTRO")) {
                    if (renTipoSigno.getDescripcion_otro() != null) {
                        signo = renTipoSigno.getDescripcion_otro();
                    }
                } else {
                    signo = dominioService.obtenerListadoDominioXCodigo(EnumNombreDominio.TIPO_SIGNO.getCodigo(), renTipoSigno.getTipo_signo()).get(0).getDescripcion();
                }
            } else {
                if (renTipoSigno.getTipo_signo().equals("OTRO")) {
                    if (renTipoSigno.getDescripcion_otro() != null) {
                        signo = signo + " " + renTipoSigno.getDescripcion_otro();
                    }
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
        options.put("resizable", false);
        options.put("draggable", true);
        options.put("modal", true);
        options.put("contentHeight", 420);
        options.put("contentWidth", 770);
        options.put("closable", false);
        RequestContext.getCurrentInstance().openDialog("renAgregarSolicitanteApoderado", options, null);
    }

    public void cargaDialogoApoderado() {
        Map<String, List<String>> params = new HashMap<String, List<String>>();
        List<String> list = new ArrayList<String>();
        list.add(EnumTipoPersona.APODERADO.getCodigo());
        params.put("id", list);
        Map<String, Object> options = new HashMap<String, Object>();
        options.put("resizable", false);
        options.put("draggable", true);
        options.put("modal", true);
        options.put("contentHeight", 430);
        options.put("contentWidth", 770);
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
        options.put("draggable", true);
        options.put("modal", true);
        options.put("closable", false);
        options.put("contentHeight", 420);
        options.put("contentWidth", 1000);
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
        options.put("resizable", false);
        options.put("draggable", true);
        options.put("modal", true);
        options.put("contentHeight", 420);
        options.put("contentWidth", 770);
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

    public String abrirPoderDepositado() {
        return ("abrirLibroPoderes");
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
        options.put("resizable", false);
        options.put("draggable", true);
        options.put("modal", true);
        options.put("contentHeight", 420);
        options.put("contentWidth", 770);
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
        options.put("height", 650);
        options.put("width", "90%");
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

    public String abrirObservacionTramite() {
        setNavegaPagina("examenModificacion");
        return ("abrirObservacionTramite");
    }

    public void verPanelExpedienteDigital() {
        Map<String, Object> options = new HashMap<String, Object>();
        options.put("resizable", true);
        options.put("height", 700);
        options.put("width", "90%");
        options.put("contentWidth", "100%");
        options.put("contentHeight", "100%");
        options.put("modal", true);
        RequestContext.getCurrentInstance().openDialog("/signo/expedienteDigitalDialog", options, null);
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
        options.put("resizable", false);
        options.put("draggable", true);
        options.put("modal", true);
        options.put("contentHeight", 420);
        options.put("contentWidth", 735);
        options.put("closable", false);
        RequestContext.getCurrentInstance().openDialog("renAgregarNuevoTitular", options, params);
        indice = renTitularRegistradoService.encuentraPosicionListadoTitularRegistrado(listaTitularRegistrado, titularRegistrado);
    }

    public void cierraDialogo() {
        RequestContext.getCurrentInstance().closeDialog(renSolicitudRenovacion);
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
    public void accion_guardaSolicitudDeRenovacion() throws Exception {
        renSolicitudRenovacion.setSr(numeroSr);
        renSolicitudRenovacion.setGestion_sr(gestion);
        renSolicitudRenovacion.setSerie_recibo(serieRecibo);
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
//        renSolicitudRenovacionService.guardaSolicitudGeneral(renSolicitudRenovacion, listarenSolicitante, listarenApoderado, listaTitularRegistrado, renDireccionDeNotificacion, lstModTipoSigno,usuario);
    }

    public List<RenTipoSigno> crearListaTipoSigno() {
        lstModTipoSigno.clear();
        for (String s : tipoGeneroSeleccionado) {
            RenTipoSigno tipoSigno = new RenTipoSigno();
            tipoSigno.setTipo_signo(s);
            if (s.equals("OTRO")) {
                tipoSigno.setDescripcion_otro(textoOtro);
            }
            lstModTipoSigno.add(tipoSigno);
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
        if (numeroOrden >= 1) {
            return renRenovacionService.numeroOrdinal(numeroOrden);
        }
        return "NINGUNA";
    }

    public String concatenaTitular(List<RenTitularRegistrado> listaTitularRegistrado) {
        String cadenaTitularme = "";
        for (RenTitularRegistrado renTitularRegistrado1 : listaTitularRegistrado) {
            String campoNombreRazonSocial = " ";
            if (renTitularRegistrado1.getNombre_razonsocial() != null && !renTitularRegistrado1.getNombre_razonsocial().equals("")) {
                cadenaTitularme = renTitularRegistrado1.getNombre_razonsocial();
            }
//            if (renTitularRegistrado1.getPrimer_apellido() != null && !renTitularRegistrado1.getPrimer_apellido().equals("")) {
//                cadenaTitularme = cadenaTitularme + " " + renTitularRegistrado1.getPrimer_apellido();
//            }
//            if (renTitularRegistrado1.getSegundo_apellido() != null && !renTitularRegistrado1.getSegundo_apellido().equals("")) {
//                cadenaTitularme = cadenaTitularme + " " + renTitularRegistrado1.getSegundo_apellido();
//            }
        }
        return cadenaTitularme;
    }

    public String concatenaApoderado(List<RenSolicitanteApoderado> listaApoderado) {

        String cadenaApoderado = "";
        for (RenSolicitanteApoderado renApoderadoRegistrado1 : listaApoderado) {
            String campoNombreRazonSocial = " ";
            if (renApoderadoRegistrado1.getNombre_razonsocial() != null && !renApoderadoRegistrado1.getNombre_razonsocial().equals("")) {
                cadenaApoderado = renApoderadoRegistrado1.getNombre_razonsocial();
            }
            if (renApoderadoRegistrado1.getPrimer_apellido() != null && !renApoderadoRegistrado1.getPrimer_apellido().equals("")) {
                cadenaApoderado = cadenaApoderado + " " + renApoderadoRegistrado1.getPrimer_apellido();
            }
            if (renApoderadoRegistrado1.getSegundo_apellido() != null && !renApoderadoRegistrado1.getSegundo_apellido().equals("")) {
                cadenaApoderado = cadenaApoderado + " " + renApoderadoRegistrado1.getSegundo_apellido();
            }
        }
        return cadenaApoderado;
    }

    //metodos de la busqueda de renovacion
    public void accionBuscarSimple() {

    }

    /**
     * Metodo para generacion del titulo nuevo y antiguo de renovacion de singos
     * registrados.
     *
     * Creado: Ruben Ramirez Fecha: 10/03/2017
     *
     * @param codigo
     * @param renSolicitudRenovacion
     * @throws net.sf.jasperreports.engine.JRException
     * @throws java.io.IOException
     */
    public void imprimirTitulo(String codigo, RenSolicitudRenovacion renSolicitudRenovacion) throws JRException, IOException, Exception {
        reporteRecibo1 = false;
        renSolicitudRenovacionRepo = renSolicitudRenovacionService.obtenerRenSolicitudXIdRenSolicitud(renSolicitudRenovacion.getIdsolicitudrenovacion());
        if (renSolicitudRenovacionRepo.getIdsolicitudrenovacion() != null) {
            renRenovacionRepo = renRenovacionService.obtieneRenovacionPorIdSolicitud(renSolicitudRenovacionRepo.getIdsolicitudrenovacion());;
            if (renRenovacionRepo.getIdrenovacion() != null) {
                renResolucionRepo = renResolucionService.obtieneRenResolucionPorIdRenovacion(renRenovacionRepo.getIdrenovacion());
                if (renResolucionRepo.getIdrenovacion() != null) {
                    reporteRecibo1 = true;
                    DateFormatSymbols dateFormatSymbols = new DateFormatSymbols();
                    dateFormatSymbols.setMonths(new String[]{
                        "Enero",
                        "Febrero",
                        "Marzo",
                        "Abril",
                        "Mayo",
                        "Junio",
                        "Julio",
                        "Agosto",
                        "Septiembre",
                        "Octubre",
                        "Noviembre",
                        "Diciembre"});

                    SimpleDateFormat formateador1 = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", dateFormatSymbols);

                    signoRepo = renRenovacionRepo.getSigno_registrado();
                    tipoSignoRepo = concatenaTipoSigno(renTipoSignoService.buscaRenTipoSignoPorIdSolicitud(renSolicitudRenovacionRepo.getIdsolicitudrenovacion()));
                    generoSignoRepo = devuelveTipoGenero(renRenovacionRepo.getTipo_genero());
                    clasesNizaRepo = devuelveClaseNiza(renRenovacionRepo.getIdclase_niza_renovacion()).intValue();
                    registrosRepo = renRenovacionRepo.getNumero_renovacion() + " - " + renRenovacionRepo.getSerie_renovacion();
                    resolucionRepo = renResolucionRepo.getNumero_resolucion() + "/" + renResolucionRepo.getGestion_resolucion();
                    titularRepo = renRenovacionRepo.getTitular();

                    if (renRenovacionRepo.getFecha_concesion() != null) {
                        fechaRegistroRepo = "A partir del " + formateador1.format(renRenovacionRepo.getFecha_concesion());
                    }

                    renovacionRepo = renRenovacionService.numeroOrdinal(renRenovacionRepo.getOrden_renovacion());

                    parametros.put("signo", signoRepo);
                    parametros.put("tipoSigno", tipoSignoRepo);
                    parametros.put("generoSigno", generoSignoRepo);
                    parametros.put("clasesNiza", clasesNizaRepo);
                    parametros.put("registro", registrosRepo);
                    parametros.put("resolucion", resolucionRepo);
                    parametros.put("titular", titularRepo);
                    parametros.put("fechaRegistro", fechaRegistroRepo);
                    parametros.put("foto", fotoRepo);
                    parametros.put("encabesado", encabesadoRepo);
                    parametros.put("renovacion", renovacionRepo);
                    String filename = null;
                    String jasperPath = null;
                    if (codigo.equals("TA")) {
                        filename = "TituloAntiguo.pdf";
                        jasperPath = "/template/renovacion/reporTituloAntiguo.jasper";
                        this.PDFSD(parametros, jasperPath, filename);
                    }
                    if (codigo.equals("TS")) {
                        filename = "TituloSemiNuevo.pdf";
                        jasperPath = "/template/renovacion/reporTituloSemiNuevo.jasper";
                        this.PDFSD(parametros, jasperPath, filename);
                    }
                    if (codigo.equals("TN")) {
                        filename = "TituloNuevo.pdf";
                        jasperPath = "/template/renovacion/reporTituloNuevo.jasper";
                        this.PDFSD(parametros, jasperPath, filename);
                    }
                }
            }
        }
    }

    /**
     * Metodo para generacion de la resolucion de renovacion nueva, seminueva y
     * antigua de signo distintivo
     *
     * Creado: Ruben Ramirez Fecha: 10/03/2017
     *
     * @param codigo
     * @param renSolicitudRenovacion
     * @throws net.sf.jasperreports.engine.JRException
     * @throws java.io.IOException
     */
    public void imprimirResolucion(String codigo, RenSolicitudRenovacion renSolicitudRenovacion) throws JRException, IOException, Exception {
        reporteRecibo2 = false;
        renSolicitudRenovacionRepo = renSolicitudRenovacionService.obtenerRenSolicitudXIdRenSolicitud(renSolicitudRenovacion.getIdsolicitudrenovacion());
        if (renSolicitudRenovacionRepo.getIdsolicitudrenovacion() != null) {
            renRenovacionRepo = renRenovacionService.obtieneRenovacionPorIdSolicitud(renSolicitudRenovacionRepo.getIdsolicitudrenovacion());
            if (renRenovacionRepo.getIdrenovacion() != null) {
                renResolucionRepo = renResolucionService.obtieneRenResolucionPorIdRenovacion(renRenovacionRepo.getIdrenovacion());
                if (renResolucionRepo.getIdrenovacion() != null) {
                    reporteRecibo2 = true;
                    DateFormatSymbols dateFormatSymbols = new DateFormatSymbols();
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

                    SimpleDateFormat formateador1 = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", dateFormatSymbols);
                    SimpleDateFormat formateador3 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

                    imgSenapiRepo = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/images/logoNuevo.jpg");
                    tramiteRepo = renSolicitudRenovacionRepo.getSr() + " - " + renSolicitudRenovacionRepo.getGestion_sr();
                    if (renSolicitudRenovacionRepo.getFecha_ingreso() != null) {
                        fechaSolicitudRepo = formateador3.format(renSolicitudRenovacionRepo.getFecha_ingreso());
                    }

                    tituloRepo = "RESOLUCIÓN DE RENOVACIÓN DE SIGNO DISTINTIVO";

                    numResolucionRepo = "Resolución N° <style isBold='true'>" + renResolucionRepo.getNumero_resolucion() + "/" + renResolucionRepo.getGestion_resolucion() + "</style>";

                    if (renResolucionRepo.getFecha_resolucion() != null) {
                        fechaActualRepo = "La Paz, " + formateador1.format(renResolucionRepo.getFecha_resolucion());
                    }

                    String fechaSolicitudAux = "";
                    if (renRenovacionRepo.getFecha_concesion() != null) {
                        fechaSolicitudAux = formateador1.format(renRenovacionRepo.getFecha_concesion());
                    }

                    String fechaConcesionAux = "";
                    if (renSolicitudRenovacionRepo.getFecha_registro_registrado() != null) {
                        fechaConcesionAux = formateador1.format(renSolicitudRenovacionRepo.getFecha_registro_registrado());
                    }

                    String signosRepo = "";
                    List<RenTipoSigno> lstSignoRepo = renTipoSignoService.buscaRenTipoSignoPorIdSolicitud(renSolicitudRenovacionRepo.getIdsolicitudrenovacion());
                    if (lstSignoRepo.size() > 0) {
                        signosRepo = concatenaTipoSigno(lstSignoRepo);
                        if (signosRepo.trim().isEmpty()) {
                            signosRepo = "";
                        } else {
                            signosRepo = " (" + signosRepo + ")";
                        }
                    }

                    textoUnicoRepo = "<style isBold='true'>ÚNICO: INSCRIBIR </style> la <style isBold='true'>" + renRenovacionService.numeroOrdinal(renRenovacionRepo.getOrden_renovacion())
                            + " RENOVACIÓN</style> del Signo Distintivo <style isBold='true' isUnderline='true'>" + renRenovacionRepo.getSigno_registrado().replaceAll("&", "&amp;")
                            + signosRepo + "</style>, Clase Int. "
                            + devuelveClaseNiza(renRenovacionRepo.getIdclase_niza_renovacion()) + " y el número de registro " + renRenovacionRepo.getNumero_registro() + " - " + renRenovacionRepo.getSerie_registro()
                            + " de fecha " + fechaConcesionAux + ", por el plazo de diez años contando a partir del <style isBold='true'>" + fechaSolicitudAux + "</style>.";

                    if (renResolucionRepo.getObservacion_resolucion() != null) {
                        if (!renResolucionRepo.getObservacion_resolucion().trim().equals("")) {
                            obsRepo = "<style isBold='true'>*Observaciones:</style> " + renResolucionRepo.getObservacion_resolucion();
                        }
                    }

                    registroRepo = renRenovacionRepo.getNumero_renovacion() + " - " + renRenovacionRepo.getSerie_renovacion();

                    ltsHistorial = renHistorialService.obtienenListaHistorialPorRenSolicitudRenovacion(renSolicitudRenovacionRepo.getIdsolicitudrenovacion(), "REGISTRO DE RENOVACION");

                    if (ltsHistorial.size() > 0) {
                        user = usuarioService.obtenerUsuario(ltsHistorial.get(0).getIdUsuario());
                        if (user != null) {
                            usuarioRepo = "/" + devuelveNombreCorto(user.getNombre(), user.getPrimer_apellido(), user.getSegundo_apellido());
                        }
                    } else {
                        usuarioRepo = "";
                    }

                    if (codigo.equals("RA")) {
                        listarenApoderadoRepo = (List<RenSolicitanteApoderado>) renSolicitanteApoderadoService.buscaSolicitanteApoderadoPoridSolicitudyTipoPersona(renSolicitudRenovacionRepo.getIdsolicitudrenovacion(), EnumTipoPersona.APODERADO.getCodigo());
                        if (listarenApoderadoRepo.size() > 0) {
                            if (listarenApoderadoRepo.size() == 1) {
                                RenSolicitanteApoderado item = listarenApoderadoRepo.get(0);
                                representantesRepo = this.devuelveNombreJuridicoONatural(item.getNombre_razonsocial(), item.getPrimer_apellido(), item.getSegundo_apellido());
                            } else {
                                for (int i = 0; i < listarenApoderadoRepo.size() - 1; i++) {
                                    RenSolicitanteApoderado item = listarenApoderadoRepo.get(i);
                                    representantesRepo += this.devuelveNombreJuridicoONatural(item.getNombre_razonsocial(), item.getPrimer_apellido(), item.getSegundo_apellido()) + ", ";
                                }
                                RenSolicitanteApoderado item = listarenApoderadoRepo.get(listarenApoderadoRepo.size() - 1);
                                representantesRepo += this.devuelveNombreJuridicoONatural(item.getNombre_razonsocial(), item.getPrimer_apellido(), item.getSegundo_apellido());
                            }
                        }
                        listarenSolicitanteRepo = (List<RenSolicitanteApoderado>) renSolicitanteApoderadoService.buscaSolicitanteApoderadoPoridSolicitudyTipoPersona(renSolicitudRenovacionRepo.getIdsolicitudrenovacion(), EnumTipoPersona.SOLICITANTE.getCodigo());
                        if (listarenSolicitanteRepo.size() > 0) {
                            if (listarenSolicitanteRepo.size() == 1) {
                                RenSolicitanteApoderado item = listarenSolicitanteRepo.get(0);
                                titularesRepo = this.devuelveNombreJuridicoONatural(item.getNombre_razonsocial(), item.getPrimer_apellido(), item.getSegundo_apellido());
                            } else {
                                for (int i = 0; i < listarenSolicitanteRepo.size() - 1; i++) {
                                    RenSolicitanteApoderado item = listarenSolicitanteRepo.get(i);
                                    titularesRepo += this.devuelveNombreJuridicoONatural(item.getNombre_razonsocial(), item.getPrimer_apellido(), item.getSegundo_apellido()) + ", ";
                                }
                                RenSolicitanteApoderado item = listarenSolicitanteRepo.get(listarenSolicitanteRepo.size() - 1);
                                titularesRepo += this.devuelveNombreJuridicoONatural(item.getNombre_razonsocial(), item.getPrimer_apellido(), item.getSegundo_apellido());
                            }
                        }
                    }

                    parametros.put("imageSenapi", imgSenapiRepo);
                    parametros.put("tramite", tramiteRepo);
                    parametros.put("fechaSolicitud", fechaSolicitudRepo);
                    parametros.put("titulo", tituloRepo);
                    parametros.put("numResolucion", numResolucionRepo);
                    parametros.put("fechaActual", fechaActualRepo);
                    parametros.put("textoUnico", textoUnicoRepo);
                    parametros.put("registro", registroRepo);
                    parametros.put("obs", obsRepo);
                    parametros.put("usuario", usuarioRepo);
                    parametros.put("titulares", titularesRepo);
                    parametros.put("representantes", representantesRepo);

                    String filename = null;
                    String jasperPath = null;
                    if (codigo.equals("RA")) {
                        filename = "ResolucionAntigua.pdf";
                        jasperPath = "/template/renovacion/reporResolucionMembretado1.jasper";
                        this.PDFSD(parametros, jasperPath, filename);
                    }
                    if (codigo.equals("RN")) {
                        filename = "ResolucionNueva.pdf";
                        jasperPath = "/template/renovacion/reporResolucion1.jasper";
                        this.PDFSD(parametros, jasperPath, filename);
                    }

                    titularesRepo = "";
                    representantesRepo = "";
                    obsRepo = "";
                }
            }
        }
    }

    /**
     * Metodo para generar el reporte en formato pdf
     *
     * Creado: Ruben Ramirez Fecha: 16/01/2017
     *
     * @param params
     * @param jasperPath
     * @param fileName
     * @throws net.sf.jasperreports.engine.JRException
     * @throws java.io.IOException
     */
    public void PDFSD(Map<String, Object> params, String jasperPath, String fileName) throws JRException, IOException {
        String relativeWebPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath(jasperPath);
        File file = new File(relativeWebPath);
        JasperPrint print = JasperFillManager.fillReport(file.getPath(), params, new JREmptyDataSource());
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-Disposition", "inline; filename=" + fileName);
        ServletOutputStream stream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(print, stream);
        FacesContext.getCurrentInstance().responseComplete();
    }

    public JRDataSource getDatos(List<?> listaObjeto) {
        return new JRBeanCollectionDataSource(listaObjeto);
    }

    /**
     * Metodo para generar el nombre corto del usuario logueado.
     *
     * Creado: Ruben Ramirez Fecha: 10/03/2017
     *
     * @param nombre
     * @param primerApellido
     * @param segundoApellido
     * @return
     */
    public String devuelveNombreCorto(String nombre, String primerApellido, String segundoApellido) {
        String campoNombre = "";
        if (nombre != null && !nombre.equals("")) {
            campoNombre = nombre.substring(0, 1).toUpperCase();
        }
        if (primerApellido != null && !primerApellido.equals("")) {
            campoNombre = campoNombre + primerApellido.substring(0, 1).toUpperCase();
        }
        if (segundoApellido != null && !segundoApellido.equals("")) {
            campoNombre = campoNombre + segundoApellido.substring(0, 1).toUpperCase();
        }
        return campoNombre;
    }

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="-----------------------------GET'S  AND SET'S----------------------">
    public Boolean getActivaAceptarRenovacion() {
        return activaAceptarRenovacion;
    }

    public void setActivaAceptarRenovacion(Boolean activaAceptarRenovacion) {
        this.activaAceptarRenovacion = activaAceptarRenovacion;
    }

    public Boolean getPanelCatalogoRendered() {
        return panelCatalogoRendered;
    }

    public void setPanelCatalogoRendered(Boolean panelCatalogoRendered) {
        this.panelCatalogoRendered = panelCatalogoRendered;
    }

    public Boolean getPanelVerRendered() {
        return panelVerRendered;
    }

    public void setPanelVerRendered(Boolean panelVerRendered) {
        this.panelVerRendered = panelVerRendered;
    }

    public RenRenovacion getRenRenovacionGuardar() {
        return renRenovacionGuardar;
    }

    public void setRenRenovacionGuardar(RenRenovacion renRenovacionGuardar) {
        this.renRenovacionGuardar = renRenovacionGuardar;
    }

    public RenRenovacionService getRenRenovacionService() {
        return renRenovacionService;
    }

    public void setRenRenovacionService(RenRenovacionService renRenovacionService) {
        this.renRenovacionService = renRenovacionService;
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

    public Usuario getUsuarioLogueado() {
        return usuarioLogueado;
    }

    public void setUsuarioLogueado(Usuario usuarioLogueado) {
        this.usuarioLogueado = usuarioLogueado;
    }

    public void setTextoOtro(String textoOtro) {
        this.textoOtro = textoOtro;
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

    public DominioService getsMDominioService() {
        return dominioService;
    }

    public void setsMDominioService(DominioService dominioService) {
        this.dominioService = dominioService;
    }

    public RegionalService getRegionalService() {
        return regionalService;
    }

    public void setRegionalService(RegionalService regionalService) {
        this.regionalService = regionalService;
    }

    public RenRenovacion getRenRenovacion() {
        return renRenovacion;
    }

    public void setRenRenovacion(RenRenovacion renRenovacion) {
        this.renRenovacion = renRenovacion;
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

    public RenSolicitudRenovacionService getRenSolicitudRenovacionService() {
        return renSolicitudRenovacionService;
    }

    public void setRenSolicitudRenovacionService(RenSolicitudRenovacionService renSolicitudRenovacionService) {
        this.renSolicitudRenovacionService = renSolicitudRenovacionService;
    }

    public RenSolicitanteApoderadoService getRenSolicitanteApoderadoService() {
        return renSolicitanteApoderadoService;
    }

    public void setRenSolicitanteApoderadoService(RenSolicitanteApoderadoService RenSolicitanteApoderadoService) {
        this.renSolicitanteApoderadoService = RenSolicitanteApoderadoService;
    }

    public RenResolucionService getRenResolucionService() {
        return renResolucionService;
    }

    public void setRenResolucionService(RenResolucionService renResolucionService) {
        this.renResolucionService = renResolucionService;
    }

    public List<RenSolicitanteApoderado> getListarenSolicitanteApoderado() {
        return listarenSolicitanteApoderado;
    }

    public void setListarenSolicitanteApoderado(List<RenSolicitanteApoderado> listarenSolicitanteApoderado) {
        this.listarenSolicitanteApoderado = listarenSolicitanteApoderado;
    }

    public RenTitularRegistradoService getRenTitularRegistradoService() {
        return renTitularRegistradoService;
    }

    public void setRenTitularRegistradoService(RenTitularRegistradoService renTitularRegistradoService) {
        this.renTitularRegistradoService = renTitularRegistradoService;
    }

    public RenDireccionDeNotificacionService getRenDireccionDeNotificacionService() {
        return renDireccionDeNotificacionService;
    }

    public void setRenDireccionDeNotificacionService(RenDireccionDeNotificacionService renDireccionDeNotificacionService) {
        this.renDireccionDeNotificacionService = renDireccionDeNotificacionService;
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

    public DominioService getDominioService() {
        return dominioService;
    }

    public void setDominioService(DominioService dominioService) {
        this.dominioService = dominioService;
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

    public List<Dominio> getListaLugarExpedicion() throws Exception {
        return listaLugarExpedicion = dominioService.obtenerListadoDominio(EnumNombreDominio.LUGAR_EXPEDICION.getCodigo());
    }

    public void setListaLugarExpedicion(List<Dominio> listaLugarExpedicion) {
        this.listaLugarExpedicion = listaLugarExpedicion;
    }

    public String getOficina() {
        return oficina;
    }

    public void setOficina(String oficina) {
        this.oficina = oficina;
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

    public Boolean getRenderizaBotonesModificatoria() {
        return renderizaBotonesModificatoria;
    }

    public void setRenderizaBotonesModificatoria(Boolean renderizaBotonesModificatoria) {
        this.renderizaBotonesModificatoria = renderizaBotonesModificatoria;
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

    public String getSerieRegistro() {
        return serieRegistro;
    }

    public void setSerieRegistro(String serieRegistro) {
        this.serieRegistro = serieRegistro;
    }

    public List<Dominio> getListaSerieRenovacion() throws Exception {
        return listaSerieRenovacion = dominioService.obtenerListadoDominio(EnumNombreDominio.SERIE_RENOVACION.getCodigo());
    }

    public void setListaSerieRenovacion(List<Dominio> listaSerieRenovacion) {
        this.listaSerieRenovacion = listaSerieRenovacion;
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

    public List<Dominio> getLstTipoUbicacion() throws Exception {
        return lstTipoUbicacion = dominioService.obtenerListadoDominio(EnumNombreDominio.ESTADO_RENOVACION.getCodigo());
    }

    public void setLstTipoUbicacion(List<Dominio> lstTipoUbicacion) {
        this.lstTipoUbicacion = lstTipoUbicacion;
    }

    public String getCiudadNotificacion() {
        return ciudadNotificacion;
    }

    public void setCiudadNotificacion(String ciudadNotificacion) {
        this.ciudadNotificacion = ciudadNotificacion;
    }

    public void setSerieRenovacionPenultima(String serieRenovacionPenultima) {
        this.serieRenovacionPenultima = serieRenovacionPenultima;
    }

    public String getSerieRegistroMarca() {
        return serieRegistroMarca;
    }

    public void setSerieRegistroMarca(String serieRegistroMarca) {
        this.serieRegistroMarca = serieRegistroMarca;
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

    public Long getNumeroSr() {
        return numeroSr;
    }

    public void setNumeroSr(Long numeroSr) {
        this.numeroSr = numeroSr;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public List<RenTitularRegistrado> getListarenTitularRegistrado() {
        return listarenTitularRegistrado;
    }

    public void setListarenTitularRegistrado(List<RenTitularRegistrado> listarenTitularRegistrado) {
        this.listarenTitularRegistrado = listarenTitularRegistrado;
    }

    public RenTitularRegistrado getRenTitularRegistrado() {
        return renTitularRegistrado;
    }

    public void setRenTitularRegistrado(RenTitularRegistrado renTitularRegistrado) {
        this.renTitularRegistrado = renTitularRegistrado;
    }

    public int getGestion() {
        return gestion;
    }

    public void setGestion(int gestion) {
        this.gestion = gestion;
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

    public String getSerieRenovacionAceptada() {
        return serieRenovacionAceptada;
    }

    public void setSerieRenovacionAceptada(String serieRenovacionAceptada) {
        this.serieRenovacionAceptada = serieRenovacionAceptada;
    }

    public String getAreaTramite() {
        return areaTramite;
    }

    public void setAreaTramite(String areaTramite) {
        this.areaTramite = areaTramite;
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

    public List<RenTitularRegistrado> getListaTitularRegistrado() {
        return listaTitularRegistrado;
    }

    public void setListaTitularRegistrado(List<RenTitularRegistrado> listaTitularRegistrado) {
        this.listaTitularRegistrado = listaTitularRegistrado;
    }

    public List<Dominio> getListaPais() throws Exception {
        return listaPais = dominioService.obtenerListadoDominio(EnumNombreDominio.PAIS.getCodigo());
    }

    public void setListaPais(List<Dominio> listaPais) {
        this.listaPais = listaPais;
    }

    public List<Dominio> getLstTipoSituacion() throws Exception {
        return lstTipoSituacion = dominioService.obtenerListadoDominio(EnumNombreDominio.ESTADO_RENOVACION.getCodigo());
    }

    public void setLstTipoSituacion(List<Dominio> lstTipoSituacion) {
        this.lstTipoSituacion = lstTipoSituacion;
    }

    public String getSituacion() {
        return situacion;
    }

    public void setSituacion(String situacion) {
        this.situacion = situacion;
    }

    public String getSerieRegistroMarcaLema() {
        return serieRegistroMarcaLema;
    }

    public void setSerieRegistroMarcaLema(String serieRegistroMarcaLema) {
        this.serieRegistroMarcaLema = serieRegistroMarcaLema;
    }

    public RenTitularRegistrado getTitularRegistradoSelect() {
        return titularRegistradoSelect;
    }

    public void setTitularRegistradoSelect(RenTitularRegistrado titularRegistradoSelect) {
        this.titularRegistradoSelect = titularRegistradoSelect;
    }

    public String getValorOpcionesRadio() {
        return valorOpcionesRadio;
    }

    public void setValorOpcionesRadio(String valorOpcionesRadio) {
        this.valorOpcionesRadio = valorOpcionesRadio;
    }

    public Integer getIndice() {
        return indice;
    }

    public void setIndice(Integer indice) {
        this.indice = indice;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public List<OrdinalesPojo> getListaOrdinales() {
        return listaOrdinales;
    }

    public void setListaOrdinales(List<OrdinalesPojo> listaOrdinales) {
        this.listaOrdinales = listaOrdinales;
    }

    public List<RenTipoSigno> getLstModTipoSigno() {
        return lstModTipoSigno;
    }

    public void setLstModTipoSigno(List<RenTipoSigno> lstModTipoSigno) {
        this.lstModTipoSigno = lstModTipoSigno;
    }

//    public ModDireccionDeNotificacionService getModDireccionDeNotificacionService() {
//        return modDireccionDeNotificacionService;
//    }
//
//    public void setModDireccionDeNotificacionService(ModDireccionDeNotificacionService modDireccionDeNotificacionService) {
//        this.modDireccionDeNotificacionService = modDireccionDeNotificacionService;
//    }
    public String getOrdenRenovacion() {
        return ordenRenovacion;
    }

    public void setOrdenRenovacion(String ordenRenovacion) {
        this.ordenRenovacion = ordenRenovacion;
    }

    public String getSignoRegistradoNuevo() {
        return signoRegistradoNuevo;
    }

    public void setSignoRegistradoNuevo(String signoRegistradoNuevo) {
        this.signoRegistradoNuevo = signoRegistradoNuevo;
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

    public int getNumeroRegistrobuscador() {
        return numeroRegistrobuscador;
    }

    public void setNumeroRegistrobuscador(int numeroRegistrobuscador) {
        this.numeroRegistrobuscador = numeroRegistrobuscador;
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

    public String getCadenaApoderado() {
        return cadenaApoderado;
    }

    public void setCadenaApoderado(String cadenaApoderado) {
        this.cadenaApoderado = cadenaApoderado;
    }

    public ComunService getComunService() {
        return comunService;
    }

    public void setComunService(ComunService comunService) {
        this.comunService = comunService;
    }

    public Boolean getMostrarPorAsignar() {
        return mostrarPorAsignar;
    }

    public void setMostrarPorAsignar(Boolean mostrarPorAsignar) {
        this.mostrarPorAsignar = mostrarPorAsignar;
    }

    public String getNumeroAsignadoConfirmacion() {
        return numeroAsignadoConfirmacion;
    }

    public void setNumeroAsignadoConfirmacion(String numeroAsignadoConfirmacion) {
        this.numeroAsignadoConfirmacion = numeroAsignadoConfirmacion;
    }

    public RenTipoSignoService getRenTipoSignoService() {
        return renTipoSignoService;
    }

    public void setRenTipoSignoService(RenTipoSignoService renTipoSignoService) {
        this.renTipoSignoService = renTipoSignoService;
    }

    public Map<String, Object> getParametros() {
        return parametros;
    }

    public void setParametros(Map<String, Object> parametros) {
        this.parametros = parametros;
    }

    public EtapaService getEtapaService() {
        return etapaService;
    }

    public void setEtapaService(EtapaService etapaService) {
        this.etapaService = etapaService;
    }

    public UsuarioService getUsuarioService() {
        return usuarioService;
    }

    public void setUsuarioService(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    public ClaseNizaService getClaseNizaService() {
        return claseNizaService;
    }

    public void setClaseNizaService(ClaseNizaService claseNizaService) {
        this.claseNizaService = claseNizaService;
    }

    public String getImgSenapiRepo() {
        return imgSenapiRepo;
    }

    public void setImgSenapiRepo(String imgSenapiRepo) {
        this.imgSenapiRepo = imgSenapiRepo;
    }

    public String getTramiteRepo() {
        return tramiteRepo;
    }

    public void setTramiteRepo(String tramiteRepo) {
        this.tramiteRepo = tramiteRepo;
    }

    public String getFechaSolicitudRepo() {
        return fechaSolicitudRepo;
    }

    public void setFechaSolicitudRepo(String fechaSolicitudRepo) {
        this.fechaSolicitudRepo = fechaSolicitudRepo;
    }

    public String getTituloRepo() {
        return tituloRepo;
    }

    public void setTituloRepo(String tituloRepo) {
        this.tituloRepo = tituloRepo;
    }

    public String getNumResolucionRepo() {
        return numResolucionRepo;
    }

    public void setNumResolucionRepo(String numResolucionRepo) {
        this.numResolucionRepo = numResolucionRepo;
    }

    public String getFechaActualRepo() {
        return fechaActualRepo;
    }

    public void setFechaActualRepo(String fechaActualRepo) {
        this.fechaActualRepo = fechaActualRepo;
    }

    public String getTextoUnicoRepo() {
        return textoUnicoRepo;
    }

    public void setTextoUnicoRepo(String textoUnicoRepo) {
        this.textoUnicoRepo = textoUnicoRepo;
    }

    public String getRegistroRepo() {
        return registroRepo;
    }

    public void setRegistroRepo(String registroRepo) {
        this.registroRepo = registroRepo;
    }

    public String getObsRepo() {
        return obsRepo;
    }

    public void setObsRepo(String obsRepo) {
        this.obsRepo = obsRepo;
    }

    public String getUsuarioRepo() {
        return usuarioRepo;
    }

    public void setUsuarioRepo(String usuarioRepo) {
        this.usuarioRepo = usuarioRepo;
    }

    public RenRenovacion getRenRenovacionRepo() {
        return renRenovacionRepo;
    }

    public void setRenRenovacionRepo(RenRenovacion renRenovacionRepo) {
        this.renRenovacionRepo = renRenovacionRepo;
    }

    public RenResolucion getRenResolucionRepo() {
        return renResolucionRepo;
    }

    public void setRenResolucionRepo(RenResolucion renResolucionRepo) {
        this.renResolucionRepo = renResolucionRepo;
    }

    public RenSolicitudRenovacion getRenSolicitudRenovacionRepo() {
        return renSolicitudRenovacionRepo;
    }

    public void setRenSolicitudRenovacionRepo(RenSolicitudRenovacion renSolicitudRenovacionRepo) {
        this.renSolicitudRenovacionRepo = renSolicitudRenovacionRepo;
    }

    public Boolean getReporteRecibo1() {
        return reporteRecibo1;
    }

    public void setReporteRecibo1(Boolean reporteRecibo1) {
        this.reporteRecibo1 = reporteRecibo1;
    }

    public Boolean getReporteRecibo2() {
        return reporteRecibo2;
    }

    public void setReporteRecibo2(Boolean reporteRecibo2) {
        this.reporteRecibo2 = reporteRecibo2;
    }

    public String getSignoRepo() {
        return signoRepo;
    }

    public void setSignoRepo(String signoRepo) {
        this.signoRepo = signoRepo;
    }

    public String getTipoSignoRepo() {
        return tipoSignoRepo;
    }

    public void setTipoSignoRepo(String tipoSignoRepo) {
        this.tipoSignoRepo = tipoSignoRepo;
    }

    public String getGeneroSignoRepo() {
        return generoSignoRepo;
    }

    public void setGeneroSignoRepo(String generoSignoRepo) {
        this.generoSignoRepo = generoSignoRepo;
    }

    public Integer getClasesNizaRepo() {
        return clasesNizaRepo;
    }

    public void setClasesNizaRepo(Integer clasesNizaRepo) {
        this.clasesNizaRepo = clasesNizaRepo;
    }

    public String getRegistrosRepo() {
        return registrosRepo;
    }

    public void setRegistrosRepo(String registrosRepo) {
        this.registrosRepo = registrosRepo;
    }

    public String getResolucionRepo() {
        return resolucionRepo;
    }

    public void setResolucionRepo(String resolucionRepo) {
        this.resolucionRepo = resolucionRepo;
    }

    public String getTitularRepo() {
        return titularRepo;
    }

    public void setTitularRepo(String titularRepo) {
        this.titularRepo = titularRepo;
    }

    public String getFechaRegistroRepo() {
        return fechaRegistroRepo;
    }

    public void setFechaRegistroRepo(String fechaRegistroRepo) {
        this.fechaRegistroRepo = fechaRegistroRepo;
    }

    public Image getFotoRepo() {
        return fotoRepo;
    }

    public void setFotoRepo(Image fotoRepo) {
        this.fotoRepo = fotoRepo;
    }

    public String getEncabesadoRepo() {
        return encabesadoRepo;
    }

    public void setEncabesadoRepo(String encabesadoRepo) {
        this.encabesadoRepo = encabesadoRepo;
    }

    public String getRenovacionRepo() {
        return renovacionRepo;
    }

    public void setRenovacionRepo(String renovacionRepo) {
        this.renovacionRepo = renovacionRepo;
    }

    public RenHistorialService getRenHistorialService() {
        return renHistorialService;
    }

    public void setRenHistorialService(RenHistorialService renHistorialService) {
        this.renHistorialService = renHistorialService;
    }

    public String getTitularesRepo() {
        return titularesRepo;
    }

    public void setTitularesRepo(String titularesRepo) {
        this.titularesRepo = titularesRepo;
    }

    public String getRepresentantesRepo() {
        return representantesRepo;
    }

    public void setRepresentantesRepo(String representantesRepo) {
        this.representantesRepo = representantesRepo;
    }

    public List<RenSolicitanteApoderado> getListarenApoderadoRepo() {
        return listarenApoderadoRepo;
    }

    public void setListarenApoderadoRepo(List<RenSolicitanteApoderado> listarenApoderadoRepo) {
        this.listarenApoderadoRepo = listarenApoderadoRepo;
    }

    public List<RenSolicitanteApoderado> getListarenSolicitanteRepo() {
        return listarenSolicitanteRepo;
    }

    public void setListarenSolicitanteRepo(List<RenSolicitanteApoderado> listarenSolicitanteRepo) {
        this.listarenSolicitanteRepo = listarenSolicitanteRepo;
    }

    public List<Historial> getLtsHistorial() {
        return ltsHistorial;
    }

    public void setLtsHistorial(List<Historial> ltsHistorial) {
        this.ltsHistorial = ltsHistorial;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

//</editor-fold>    
}
