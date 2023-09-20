/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.entidades.expediente;

import java.awt.Image;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.text.DateFormatSymbols;
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
import javax.swing.ImageIcon;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import snp.gob.bo.entidades.bean.common.AbstractManagedBean;
import snp.gob.bo.entidades.bean.common.SessionStateManagedBean;
import snp.gob.bo.gimodel.bdimagen.entidad.SigImagen;
import snp.gob.bo.gimodel.bdimagen.entidad.SigLogoTipo;
import snp.gob.bo.gimodel.bdimagen.servicio.SigImagenService;
import snp.gob.bo.gimodel.bdimagen.servicio.SigLogoTipoService;
import snp.gob.bo.gimodel.entidad.Dominio;
import snp.gob.bo.gimodel.entidad.ObservacionTramite;
import snp.gob.bo.gimodel.entidad.SigPrioridadPreferencia;
import snp.gob.bo.gimodel.entidad.SigSignoClaseNiza;
import snp.gob.bo.gimodel.entidad.SigSignoMarca;
import snp.gob.bo.gimodel.entidad.SigSolicitanteApoderado;
import snp.gob.bo.gimodel.entidad.SigTipoSigno;
import snp.gob.bo.gimodel.enums.EnumPrefijoTablas;
import snp.gob.bo.gimodel.servicio.ComunService;
import snp.gob.bo.gimodel.servicio.DominioService;
import snp.gob.bo.gimodel.servicio.ExpedienteService;
import snp.gob.bo.gimodel.servicio.ObservacionTramiteService;
import snp.gob.bo.gimodel.servicio.SigPrioridadPreferenciaService;
import snp.gob.bo.gimodel.servicio.SigSignoClaseNizaService;
import snp.gob.bo.gimodel.servicio.SigSignoMarcaService;
import snp.gob.bo.gimodel.servicio.SigSolicitanteApoderadoService;
import snp.gob.bo.gimodel.servicio.SigTipoSignoService;

/**
 *
 * @author Ruben Ramirez
 */
@ManagedBean(name = "imprimirExpedienteBean")
@ViewScoped
public class ImprimirExpedienteBean extends AbstractManagedBean implements Serializable {

    //<editor-fold defaultstate="collapsed" desc="Definicion de servicios y atributos... verificar estos datos">
    private Date fechaSolicitud = new Date();
    private String tipoSolicitante = "J";
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

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Definicion de servicios y atributos">
    // variables de la vista
    private String numeroExpediente;
    private String gestionExpediente;
    private String extensionExpediente = "";
    private String valorRadioBuscador = "soma";

    //busqueda solicitud de marca sm
    private Boolean buscarSMRendered = true;
    //busqueda numero de publicacion
    private Boolean buscarPURendered = false;
    //busqueda numero de registro
    private Boolean buscarRERendered = false;

    private SigSignoMarca sigSignoMarca = null;
    private String marca;
    private String descripcionSigno;
    private Long valorNumeroTitulo;
    private String numeroFormulario;
    private String regional = "LPZ";
    private String valorTipoGenero = "";
    private String valorSerieTitulo = "";
    private Date valorFechaIngreso = new Date();

    private List<Dominio> lstSituacionActual = new ArrayList<Dominio>();
    private List<Dominio> lstTipoSigno = new ArrayList<Dominio>();
    private List<Dominio> lstTipoDocumento = new ArrayList<Dominio>();
    private List<SigSignoClaseNiza> lstSigSignoClaseNiza = new ArrayList<SigSignoClaseNiza>();
    private List<SigTipoSigno> lstSigTipoSigno = new ArrayList<SigTipoSigno>();
    private List<Dominio> lstPaises = new ArrayList<Dominio>();

    //busqueda numero de registro
    private String valorNumeroExpediente = "";
    private String valorGestionExpediente = "";
    private String valorExtensionExpediente = "";
    private Long valorNumeroRecibo;
    private String valorSerieRecibo = "";
    private String valorDescripcionDisenio = "";
    private String valorEstadoMarca = "";
    private String valorUbicacion = "";
    //valores publicacion
    private Long valorNumeroPublicacion = 0L;
    private Long valorNumeroGaceta = 0L;
    private Date valorFechaPublicacion;
    //valores registro
    private Long valorNumeroRegistro = 0L;
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
    //valores prioridades
    private SigPrioridadPreferencia prioridadExtranjera = new SigPrioridadPreferencia();
    private SigPrioridadPreferencia prioridadExposicion = new SigPrioridadPreferencia();
    private SigPrioridadPreferencia oposicionAndina = new SigPrioridadPreferencia();
    //valores lista de solicitantes
    private List<SigSolicitanteApoderado> lstSolicitantes = new ArrayList<SigSolicitanteApoderado>();
    //valores lista de apoderados
    private List<SigSolicitanteApoderado> lstApoderados = new ArrayList<SigSolicitanteApoderado>();
    //valor observaciones
    private ObservacionTramite observacionTramite = new ObservacionTramite();

    private Boolean reporteRecibo = false;

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
    private String productos = "";
    private String imgSenapi = "";
    private String tipoPrioridad = "";
    private String prioridad = "";
    private String lugarPrioridad = "";
    private String fechaPrioridad = "";
    private String andina;
    private String situacionActual = "";
    private String vistaSituacionActual = "";
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
    private List<Dominio> lstTipoGeneroXdominio = new ArrayList<Dominio>();
    private SigLogoTipo sigLogoTipo = new SigLogoTipo();
    private SigImagen sigImage = new SigImagen();
    private Image imagenes = null;

    @ManagedProperty(value = "#{sessionState}")
    private SessionStateManagedBean sessionManagedBean;

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

    /**
     * Creates a new instance of BuscadorPublicacionBean
     */
    public ImprimirExpedienteBean() {
    }

    @PostConstruct
    public void BuscadorPublicacionBeanInit() {

        try {
            inicializarVista();

        } catch (Exception e) {
            Logger.getLogger(AgregarClaseNizaBean.class.getName()).log(Level.SEVERE, null, e);
        }

    }

    /**
     * Metodo inicializar variables de la vista Creado: Eddy Valero Fecha:
     * 20/09/2016
     */
    public void inicializarVista() {
        try {
            //inicializar el objeto principal
            sigSignoMarca = new SigSignoMarca();
            this.lstSituacionActual = dominioService.obtenerListadoDominio("estado_marca");
            this.lstTipoSigno = dominioService.obtenerListadoDominio("tipo_signo");
            this.lstPaises = dominioService.obtenerListadoDominio("pais");
            this.lstTipoDocumento = dominioService.obtenerListadoDominio("tipo_documento");
        } catch (Exception ex) {
            Logger.getLogger(ExamenSignosBean.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

    }

    /*
     * Metodo para realizar la busqueda de acuerdo a los siguientes criterios:
     * SolicitudMarca: soma
     * Nro Publicacion: nupu
     * Nro Registro: nure
     * tambien cuando se ingresa a esta opción deben limpiarse las variables
     * Creado: Ruben Ramirez Fecha: 08/12/2016
     */
    public void modificarCriterioBusqueda() {
        this.numeroExpediente = "";
        this.gestionExpediente = "";
        this.extensionExpediente = "";
        this.vistaSituacionActual = "";
        this.sigSignoMarca = null;
        reporteRecibo = false;

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
                break;
            default:
                buscarSMRendered = false;
                buscarPURendered = false;
                buscarRERendered = false;
                break;
        }

    }

    /**
     * Metodo para llenado de los datos.
     *
     * Creado: Ruben Ramirez Fecha: 08/12/2016
     *
     */
    public void cargarDatosExpediente() {
        try {
            sigImage = new SigImagen();
            if (sigSignoMarca.getIdSignoMarca() != null) {
                regional = sigSignoMarca.getOficina();
                this.marca = sigSignoMarca.getSigno();
                this.descripcionSigno = sigSignoMarca.getDescripcionSigno();
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
                this.valorNumeroExpediente = mapResultado.get("numero").toString();
                this.valorGestionExpediente = mapResultado.get("gestion").toString();
                this.valorExtensionExpediente = mapResultado.get("extension").toString();

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
                this.situacionActual = sigSignoMarca.getUbicacion();
                //Obtener el listado de claseniza
                this.lstSigSignoClaseNiza = sigSignoClaseNizaService.obtenerListaSigSignoClazeNizaXIdSignoMarca(sigSignoMarca.getIdSignoMarca());
                this.lstSigTipoSigno = sigTipoSignoService.listaSigTipoSignoXidSIgnoMarca(sigSignoMarca.getIdSignoMarca());
                //armar la lista de tipoGeneroSeleccionado
                this.valorTipoSignoSeleccionado = new String[10];
                int i = 0;
                for (SigTipoSigno item : lstSigTipoSigno) {
                    this.valorTipoSignoSeleccionado[i] = item.getTipoSigno();
                    //preguntar si el item es otro, en caso afirmativo, cargar este valor
                    if (item.getTipoSigno().equals("OTRO")) {
                        this.valorTipoSignoOtro = item.getDescripcionOtro();
                        this.txtOtroRendered = Boolean.TRUE;
                    }
                    i++;
                }
                //Cargar las prioridades y preferencias
                this.prioridadExtranjera = sigPrioridadPreferenciaService.obtenerPrioridadExtranjera(this.sigSignoMarca.getIdSignoMarca());
                this.prioridadExposicion = sigPrioridadPreferenciaService.obtenerPrioridadExposicion(this.sigSignoMarca.getIdSignoMarca());
                this.oposicionAndina = sigPrioridadPreferenciaService.obtenerOposicionAndina(this.sigSignoMarca.getIdSignoMarca());

                //cargar los solicitantes
                this.lstSolicitantes = sigSolicitanteApoderadoService.listadoSolicitanteXidsignomarca(this.sigSignoMarca.getIdSignoMarca());
                this.lstApoderados = sigSolicitanteApoderadoService.listadoApoderadoXidsignomarca(this.sigSignoMarca.getIdSignoMarca());
                //cargar la ultima observacion
                this.observacionTramite = observacionTramiteService.obtenerUltimaObservacionTramite(EnumPrefijoTablas.SIGNO.getCodigo(), this.sigSignoMarca.getIdSignoMarca());

                // cargar siglogo
                sigLogoTipo = sigLogoTipoService.obtenerRegistroPrincipalSigLogoTipoXIdSignoMarca(this.sigSignoMarca.getIdSignoMarca());
                if (sigLogoTipo.getIdLogoTipo() != null) {
                    sigImage = sigImagenService.obtenerSigImagenXIdSigLogoTipo(sigLogoTipo.getIdLogoTipo());
                }
            }
        } catch (Exception ex) {

            Logger.getLogger(ExamenSignosBean.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     *
     * Metodo para realizar la busqueda del anterior expediente
     *
     * Creado: Ruben Ramirez Fecha: 09/12/2016
     */
    public void accionBuscarAnteriorExpediente() {
        try {
            reporteRecibo = false;
            if (sigSignoMarca.getIdSignoMarca() != null) {
                this.sigSignoMarca = expedienteService.obtenerAnteriorRegistroSigSignoMarca(sigSignoMarca.getIdSignoMarca());
                if (sigSignoMarca.getIdSignoMarca() != null) {
                    cargarDatosExpediente();
                    imprimir();
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "No existen mas registros.", ""));
                }
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Realize la busqueda de los registros.", ""));
            }
        } catch (Exception ex) {
            Logger.getLogger(ExamenSignosBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     *
     * Metodo para realizar la busqueda del siguiente expediente
     *
     * Creado: Ruben Ramirez Fecha: 09/12/2016
     */
    public void accionBuscarSiguienteExpediente() {
        try {
            reporteRecibo = false;
            if (sigSignoMarca.getIdSignoMarca() != null) {
                this.sigSignoMarca = expedienteService.obtenerSiguienteRegistroSigSignoMarca(sigSignoMarca.getIdSignoMarca());
                if (sigSignoMarca.getIdSignoMarca() != null) {
                    cargarDatosExpediente();
                    imprimir();
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "No existen mas registros.", ""));
                }
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Realize la busqueda de los registros.", ""));
            }

        } catch (Exception ex) {
            Logger.getLogger(ExamenSignosBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * Metodo para realizar la busqueda del siguiente expediente
     *
     * Creado: Ruben Ramirez Fecha: 08/12/2016
     */
    public void accionBuscarExpediente() {
        //quitar los espacios de la cadena a preguntar
        numeroExpediente = numeroExpediente.trim();
        gestionExpediente = gestionExpediente.trim();
        extensionExpediente = extensionExpediente.trim();
        reporteRecibo = false;

        try {
            //buscar por sm
            if (buscarSMRendered) {
                if (validar(numeroExpediente) && validar(gestionExpediente)) {
                    sigSignoMarca = expedienteService.obtenerSigsignomarca(numeroExpediente, gestionExpediente, extensionExpediente);
                }
                //buscar por publicacion
            } else if (buscarPURendered) {
                if (validar(numeroExpediente)) {
                    sigSignoMarca = sigSignoMarcaService.listaSigSignoMarcaXPublicacion(Long.valueOf(this.numeroExpediente));
                }
                //buscar por registro
            } else if (buscarRERendered) {
                if (validar(numeroExpediente)) {
                    sigSignoMarca = sigSignoMarcaService.listaSigSignoMarcaXRegistro(Long.valueOf(this.numeroExpediente), this.extensionExpediente, "");
                }
            }

            if (sigSignoMarca != null) {
                cargarDatosExpediente();
                imprimir();
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "No se encontraron registros con los parametros indicados.", ""));
            }

        } catch (Exception ex) {
            Logger.getLogger(ExamenSignosBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * Metodo para validar si una cadena string es un numero.
     *
     * Creado: Ruben Ramirez Fecha: 09/12/2016
     *
     * @param cadena
     * @return
     */
    public boolean validar(String cadena) {
        if (cadena.equals("") || cadena == null) {
            return false;
        } else {
            return cadena.matches("[0-9]*");
        }
    }

    /**
     * Metodo para llenar los datos del reporte y su generacion en formato pdf
     *
     * Creado: Ruben Ramirez Fecha: 26/10/2016
     * Modificado: Placido Castro Fecha: 25/05/2017
     *
     * @throws net.sf.jasperreports.engine.JRException
     * @throws java.io.IOException
     */
    public void imprimir() throws JRException, IOException, Exception {
        if (sigSignoMarca.getIdSignoMarca() != null) {
            reporteRecibo = true;
            //cargarDatosExpediente();

            try {
                lstTipoGeneroXdominio = dominioService.obtenerListadoDominioXCodigo("tipo_genero", sigSignoMarca.getTipoGenero());
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
            SimpleDateFormat formateador3 = new SimpleDateFormat("EEEEE, d MMMM yyyy", dateFormatSymbols);
            SimpleDateFormat formateador4 = new SimpleDateFormat("H:mm a");

            // fecha actual de al base de datos
            Date fechaPresente = comunService.obtenerFechaHoraServidor(1L);
            if (fechaPresente != null) {
                fechaHoy = formateador3.format(fechaPresente);
                horaHoy = formateador4.format(fechaPresente).toLowerCase();
            }

            fechasm = formateador1.format(sigSignoMarca.getFechaIngreso());
            horasm = formateador2.format(sigSignoMarca.getFechaIngreso());
            marcaX = sigSignoMarca.getSigno();
            sm = valorNumeroExpediente + valorExtensionExpediente + "-" + valorGestionExpediente;

            int numLstSigSignoClaseNiza = lstSigSignoClaseNiza.size();
            if (numLstSigSignoClaseNiza > 0) {
                if (numLstSigSignoClaseNiza == 1) {
                    SigSignoClaseNiza sigSignoClaseNiza = lstSigSignoClaseNiza.get(0);
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
                    for (int i = 0; i < lstSigSignoClaseNiza.size() - 1; i++) {
                        SigSignoClaseNiza sigSignoClaseNiza = lstSigSignoClaseNiza.get(i);
                        clase += "" + sigSignoClaseNiza.getNumeroClaseNiza() + "; ";
                        if (sigSignoClaseNiza.getListaProducto() != null) {
                            if (!sigSignoClaseNiza.getListaProducto().equals("")) {
                                productos += sigSignoClaseNiza.getListaProducto() + "; ";
                            }
                        }
                    }
                    SigSignoClaseNiza sigSignoClaseNiza = lstSigSignoClaseNiza.get(numLstSigSignoClaseNiza - 1);
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
                            tipoSigno = "" + item.getNombre();
                            if (item.getCodigo().equals("OTRO")) {
                                tipoSigno += ": " + sigTipoSigno.getDescripcionOtro();
                            }
                            break;
                        }
                    }
                } else {
                    for (int i = 0; i < lstSigTipoSigno.size() - 1; i++) {
                        SigTipoSigno sigTipoSigno = lstSigTipoSigno.get(i);
                        for (Dominio item : lstTipoSigno) {
                            if (sigTipoSigno.getTipoSigno().equals(item.getCodigo())) {
                                tipoSigno += "" + item.getNombre() + "; ";
                                if (item.getCodigo().equals("OTRO")) {
                                    tipoSigno += ": " + sigTipoSigno.getDescripcionOtro() + "; ";
                                } else {
                                    tipoSigno += "; ";
                                }
                                break;
                            }
                        }
                    }
                    SigTipoSigno sigTipoSigno = lstSigTipoSigno.get(numLstSigTipoSigno - 1);
                    for (Dominio item : lstTipoSigno) {
                        if (sigTipoSigno.getTipoSigno().equals(item.getCodigo())) {
                            tipoSigno += "" + item.getNombre();
                            if (item.getCodigo().equals("OTRO")) {
                                tipoSigno += ": " + sigTipoSigno.getDescripcionOtro();
                            }
                            break;
                        }
                    }
                }
            }

            descripSigno = valorDescripcionDisenio;

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
                if (prioridadExtranjera.getLugar() != null) {
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
                andina = oposicionAndina.getNombreNumero();
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
            this.vistaSituacionActual = situacionActual;

            observacion = observacionTramite.getDescripcion();

            noPublicacion = valorNumeroPublicacion;
            noGaceta = valorNumeroGaceta;
            if (valorFechaPublicacion != null) {
                fechaPub = formateador1.format(valorFechaPublicacion);
            }

            if (valorNumeroRegistro == null) {
                noRegistro = "";
            } else {
                if (valorSerieRegistro == null) {
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
            this.generateReport(parametros, jasperPath, filename);

            limpiar();
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

    /**
     * Metodo para generar el reporte y pasarle en formato StreamContente a la
     * variable de sesion stream. Para posterior despliegue en la vista.
     *
     * Creado: Ruben Ramirez Fecha: 26/10/2016
     *
     * @param params
     * @param jasperPath
     * @param fileName
     *
     */
    public void generateReport(Map<String, Object> params, String jasperPath, String fileName) throws IOException, JRException, Exception {
        String relativeWebPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath(jasperPath);
        File file = new File(relativeWebPath);
        ByteArrayOutputStream outputStream = getOutputStreamFromReport(params, file.getPath());
        sessionManagedBean.setStream(getStreamContentFromOutputStream(outputStream, "application/pdf", fileName));
    }

    /**
     * Metodo para generar el reporte en formato ByteArrayOutputStream.
     *
     * Creado: Ruben Ramirez Fecha: 26/10/2016
     *
     * @param map
     * @param pathJasper
     *
     * @return ByteArrayOutputStream
     */
    public static ByteArrayOutputStream getOutputStreamFromReport(Map<String, Object> map, String pathJasper) throws Exception {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        JasperPrint jp = JasperFillManager.fillReport(pathJasper, map, new JREmptyDataSource());
        JasperExportManager.exportReportToPdfStream(jp, os);
        os.flush();
        os.close();
        return os;
    }

    /**
     * Metodo para generar el reporte en formato StreamedContent.
     *
     * Creado: Ruben Ramirez Fecha: 26/10/2016
     *
     * @param os
     * @param contentType
     * @param nameFile
     *
     * @return StreamedContent
     */
    public static StreamedContent getStreamContentFromOutputStream(ByteArrayOutputStream os, String contentType, String nameFile) throws Exception, IOException {
        StreamedContent file = null;
        InputStream is = new ByteArrayInputStream(os.toByteArray());
        file = new DefaultStreamedContent(is, "application/pdf", "reporte.pdf");
        return file;
    }

    public JRDataSource getDatos(List<?> listaObjeto) {
        return new JRBeanCollectionDataSource(listaObjeto);
    }

    public SessionStateManagedBean getSessionManagedBean() {
        return sessionManagedBean;
    }

    public void setSessionManagedBean(SessionStateManagedBean sessionManagedBean) {
        this.sessionManagedBean = sessionManagedBean;
    }

    public Date getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(Date fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

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

    public SigSignoMarca getSigSignoMarca() {
        return sigSignoMarca;
    }

    public void setSigSignoMarca(SigSignoMarca sigSignoMarca) {
        this.sigSignoMarca = sigSignoMarca;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getDescripcionSigno() {
        return descripcionSigno;
    }

    public void setDescripcionSigno(String descripcionSigno) {
        this.descripcionSigno = descripcionSigno;
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

    public String getRegional() {
        return regional;
    }

    public void setRegional(String regional) {
        this.regional = regional;
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

    public List<Dominio> getLstSituacionActual() {
        return lstSituacionActual;
    }

    public void setLstSituacionActual(List<Dominio> lstSituacionActual) {
        this.lstSituacionActual = lstSituacionActual;
    }

    public List<Dominio> getLstTipoSigno() {
        return lstTipoSigno;
    }

    public void setLstTipoSigno(List<Dominio> lstTipoSigno) {
        this.lstTipoSigno = lstTipoSigno;
    }

    public List<Dominio> getLstTipoDocumento() {
        return lstTipoDocumento;
    }

    public void setLstTipoDocumento(List<Dominio> lstTipoDocumento) {
        this.lstTipoDocumento = lstTipoDocumento;
    }

    public List<SigSignoClaseNiza> getLstSigSignoClaseNiza() {
        return lstSigSignoClaseNiza;
    }

    public void setLstSigSignoClaseNiza(List<SigSignoClaseNiza> lstSigSignoClaseNiza) {
        this.lstSigSignoClaseNiza = lstSigSignoClaseNiza;
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

    public int getValorNumeroRenovacion() {
        return valorNumeroRenovacion;
    }

    public void setValorNumeroRenovacion(int valorNumeroRenovacion) {
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

    public Boolean getTxtOtroRendered() {
        return txtOtroRendered;
    }

    public void setTxtOtroRendered(Boolean txtOtroRendered) {
        this.txtOtroRendered = txtOtroRendered;
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

    public Boolean getReporteRecibo() {
        return reporteRecibo;
    }

    public void setReporteRecibo(Boolean reporteRecibo) {
        this.reporteRecibo = reporteRecibo;
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

    public Image getImagenes() {
        return imagenes;
    }

    public void setImagenes(Image imagenes) {
        this.imagenes = imagenes;
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

    public String getVistaSituacionActual() {
        return vistaSituacionActual;
    }

    public void setVistaSituacionActual(String vistaSituacionActual) {
        this.vistaSituacionActual = vistaSituacionActual;
    }
    
}
