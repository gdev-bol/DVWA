package snp.gob.bo.giview.ventanilla;

import static com.google.common.base.Charsets.ISO_8859_1;
import static com.google.common.base.Charsets.UTF_8;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.transaction.annotation.Transactional;
import snp.gob.bo.entidades.bean.common.AbstractManagedBean;
import snp.gob.bo.gimodel.entidad.ClaseNiza;
import snp.gob.bo.gimodel.entidad.Dominio;
import snp.gob.bo.gimodel.entidad.ElementoFormularioTramite;
import snp.gob.bo.gimodel.entidad.ModModificacion;
import snp.gob.bo.gimodel.entidad.ModSolicitanteApoderado;
import snp.gob.bo.gimodel.entidad.ObservacionTramite;
import snp.gob.bo.gimodel.entidad.OpoMarcademandada;
import snp.gob.bo.gimodel.entidad.OpoMarcademandante;
import snp.gob.bo.gimodel.entidad.OpoSolicitanteapoderado;
import snp.gob.bo.gimodel.entidad.Oposicion;
import snp.gob.bo.gimodel.entidad.RenSolicitanteApoderado;
import snp.gob.bo.gimodel.entidad.RenSolicitudRenovacion;
import snp.gob.bo.gimodel.entidad.Seguimiento;
import snp.gob.bo.gimodel.entidad.SigLemaComercial;
import snp.gob.bo.gimodel.entidad.SigSignoClaseNiza;
import snp.gob.bo.gimodel.entidad.SigSignoMarca;
import snp.gob.bo.gimodel.entidad.SigSolicitanteApoderado;
import snp.gob.bo.gimodel.entidad.Usuario;
import snp.gob.bo.gimodel.servicio.ClaseNizaService;
import snp.gob.bo.gimodel.servicio.ComunService;
import snp.gob.bo.gimodel.servicio.DatoElementoFormularioService;
import snp.gob.bo.gimodel.servicio.DominioService;
import snp.gob.bo.gimodel.servicio.ElementoFormularioTramiteService;
import snp.gob.bo.gimodel.servicio.ModModificacionService;
import snp.gob.bo.gimodel.servicio.ModSolicitanteApoderadoService;
import snp.gob.bo.gimodel.servicio.ObservacionTramiteService;
import snp.gob.bo.gimodel.servicio.OpoMarcademandadaService;
import snp.gob.bo.gimodel.servicio.OpoMarcademandanteService;
import snp.gob.bo.gimodel.servicio.OpoSolicitanteaopderadoService;
import snp.gob.bo.gimodel.servicio.OposicionService;
import snp.gob.bo.gimodel.servicio.RenSolicitanteApoderadoService;
import snp.gob.bo.gimodel.servicio.RenSolicitudRenovacionService;
import snp.gob.bo.gimodel.servicio.SeguimientoService;
import snp.gob.bo.gimodel.servicio.SigLemaComercialService;
import snp.gob.bo.gimodel.servicio.SigSignoClaseNizaService;
import snp.gob.bo.gimodel.servicio.SigSignoMarcaService;
import snp.gob.bo.gimodel.servicio.SigSolicitanteApoderadoService;
import snp.gob.bo.gimodel.solicitudes.entidades.DireccionNotificaciones;
import snp.gob.bo.gimodel.solicitudes.entidades.FormularioPI100;
import snp.gob.bo.gimodel.solicitudes.entidades.FormularioPI101;
import snp.gob.bo.gimodel.solicitudes.entidades.FormularioPI102;
import snp.gob.bo.gimodel.solicitudes.entidades.FormularioPI103;
import snp.gob.bo.gimodel.solicitudes.entidades.FormularioPI104;
import snp.gob.bo.gimodel.solicitudes.entidades.FormularioPI105;
import snp.gob.bo.gimodel.solicitudes.servicio.FormularioPI100Service;
import snp.gob.bo.gimodel.solicitudes.servicio.FormularioPI101Service;
import snp.gob.bo.gimodel.solicitudes.servicio.FormularioPI102Service;
import snp.gob.bo.gimodel.solicitudes.servicio.FormularioPI103Service;
import snp.gob.bo.gimodel.solicitudes.servicio.FormularioPI104Service;
import snp.gob.bo.gimodel.solicitudes.servicio.FormularioPI105Service;
import snp.gob.bo.gimodel.solicitudes.servicio.IngresoFormularioService;

/**
 *
 * @author Eddy Valero
 * @version 1.0, 13/08/2016
 */
@ManagedBean(name = "ingresoRecepcionDeDocumentosBean")
@ViewScoped
public class IngresoRecepcionDeDocumentosBean extends AbstractManagedBean implements Serializable {

    private List<SigSignoMarca> listaSigSignoMarca = new ArrayList<SigSignoMarca>();
    private List<SigSignoClaseNiza> listaSigSignoClaseNiza = new ArrayList<SigSignoClaseNiza>();

    private List<SigSolicitanteApoderado> listaSigSolicitante = new ArrayList<SigSolicitanteApoderado>();
    private List<SigSolicitanteApoderado> listaSigApoderado = new ArrayList<SigSolicitanteApoderado>();
    private List<ObservacionTramite> listaObservacionTramite = new ArrayList<ObservacionTramite>();

    private ArrayList<ModSolicitanteApoderado> listaModSolicitante = new ArrayList<ModSolicitanteApoderado>();
    private ArrayList<ModSolicitanteApoderado> listaModApoderado = new ArrayList<ModSolicitanteApoderado>();

    private ArrayList<RenSolicitanteApoderado> listaRenSolicitante = new ArrayList<RenSolicitanteApoderado>();
    private ArrayList<RenSolicitanteApoderado> listaRenApoderado = new ArrayList<RenSolicitanteApoderado>();
    
    private List<OpoSolicitanteapoderado> listaOpoSolicitante = new ArrayList<>();
    private List<OpoSolicitanteapoderado> listaOpoApoderado = new ArrayList<>();

    private List<Dominio> listaTipoModificacion = new ArrayList<Dominio>();

    // parametros del reporte
    private Map<String, Object> parametros = new HashMap<String, Object>();
    private List<Dominio> listaTipoSignoPI100 = new ArrayList<>();
    private List<Dominio> listaTipoSignoPI101 = new ArrayList<>();
    private List<Dominio> listaTipoSignoPI102 = new ArrayList<>();
    private List<SeccionReporte> listaSeccionReporte = new ArrayList<>();
    private String imageSenapi;
    private String imageEscudoBol;
    private String numeroFormulario = "";
    private String fechaIngreso = "";
    private String horaIngreso = "";
    private String titulo = "";
    private String sm = "";
    private String nombreMarca = "";
    private String clase = "";
    private String lemaComercial = "";
    private String solicitantes = "";
    private String apoderados = "";
    private String observacion2 = "";
    private Integer numeroPublicacion;
    private Integer gaceta;
    private Integer nroorden;
    private Boolean habilitabnt;

    private SigSignoMarca sigSignoMarca = new SigSignoMarca();
    private SigLemaComercial sigLemaComercial = new SigLemaComercial();
    private HashMap resultado = new HashMap();

    private ModModificacion modModificacion = new ModModificacion();
    private RenSolicitudRenovacion renSolicitudRenovacion = new RenSolicitudRenovacion();
    private Oposicion oposicion = new Oposicion();
    private OpoMarcademandada opoMarcaDemandada = new OpoMarcademandada();
    private OpoMarcademandante opoMarcaDemandante = new OpoMarcademandante();

    //<editor-fold defaultstate="collapsed" desc="Definicion de servicios y atributos">
    @ManagedProperty(value = "#{datoElementoFormularioService}")
    private DatoElementoFormularioService datoElementoFormularioService;

    @ManagedProperty(value = "#{seguimientoService}")
    private SeguimientoService seguimientoService;

    @ManagedProperty(value = "#{formularioPI100Service}")
    private FormularioPI100Service formularioPI100Service;

    @ManagedProperty(value = "#{formularioPI101Service}")
    private FormularioPI101Service formularioPI101Service;

    @ManagedProperty(value = "#{formularioPI102Service}")
    private FormularioPI102Service formularioPI102Service;

    @ManagedProperty(value = "#{formularioPI103Service}")
    private FormularioPI103Service formularioPI103Service;

    @ManagedProperty(value = "#{formularioPI104Service}")
    private FormularioPI104Service formularioPI104Service;
    
    @ManagedProperty(value = "#{formularioPI105Service}")
    private FormularioPI105Service formularioPI105Service;

    @ManagedProperty(value = "#{ingresoFormularioService}")
    private IngresoFormularioService ingresoFormularioService;

    @ManagedProperty(value = "#{elementoFormularioTramiteService}")
    private ElementoFormularioTramiteService elementoFormularioTramiteService;

    @ManagedProperty(value = "#{observacionTramiteService}")
    private ObservacionTramiteService observacionTramiteService;

    @ManagedProperty(value = "#{sigSignoMarcaService}")
    private SigSignoMarcaService sigSignoMarcaService;

    @ManagedProperty(value = "#{sigLemaComercialService}")
    private SigLemaComercialService sigLemaComercialService;

    @ManagedProperty(value = "#{comunService}")
    private ComunService comunService;

    @ManagedProperty("#{dominioService}")
    private DominioService dominioService;

    @ManagedProperty(value = "#{sigSignoClaseNizaService}")
    private SigSignoClaseNizaService sigSignoClaseNizaService;

    @ManagedProperty(value = "#{sigSolicitanteApoderadoService}")
    private SigSolicitanteApoderadoService sigSolicitanteApoderadoService;

    @ManagedProperty(value = "#{modModificacionService}")
    private ModModificacionService modModificacionService;

    @ManagedProperty(value = "#{modSolicitanteApoderadoService}")
    private ModSolicitanteApoderadoService modSolicitanteApoderadoService;

    @ManagedProperty(value = "#{renSolicitudRenovacionService}")
    private RenSolicitudRenovacionService renSolicitudRenovacionService;

    @ManagedProperty(value = "#{renSolicitanteApoderadoService}")
    private RenSolicitanteApoderadoService renSolicitanteApoderadoService;
    
    @ManagedProperty(value = "#{oposicionService}")
    private OposicionService oposicionService;
    
    @ManagedProperty(value = "#{opoSolicitanteApoderadoService}")
    private OpoSolicitanteaopderadoService opoSolicitanteApoderadoService;
    
    @ManagedProperty(value = "#{opoMarcaDemandanteService}")
    private OpoMarcademandanteService opoMarcaDemandanteService;
    
    @ManagedProperty(value = "#{opoMarcaDemandadaService}")
    private OpoMarcademandadaService opoMarcaDemandadaService;
    
    @ManagedProperty(value = "#{claseNizaService}")
    private ClaseNizaService claseNizaService;

    private Seguimiento sigSeguimiento;
    private String template;
    //private List<DatoElementoFormulario> plantillaVentanilla = new ArrayList<DatoElementoFormulario>();
    private Long codigoSM;
    //formularios de solicitud origen
    private FormularioPI100 formularioPI100 = new FormularioPI100();
    private FormularioPI101 formularioPI101 = new FormularioPI101();
    private FormularioPI102 formularioPI102 = new FormularioPI102();
    private FormularioPI103 formularioPI103 = new FormularioPI103();
    private FormularioPI104 formularioPI104 = new FormularioPI104();
    private FormularioPI105 formularioPI105 = new FormularioPI105();
    private String codigoFormulario;
    private String oficina;
    private HashMap mapResultado = new HashMap();
    //el listado de la plantilla que se enviara
    private List<ElementoFormularioTramite> plantillaVentanilla = new ArrayList<ElementoFormularioTramite>();
    private String observacion;
    Usuario usuario;
    private Long numero;
    private Integer gestion;
    private String SiglaModificacion = "";

    //</editor-fold>
    @PostConstruct
    public void initIngresoRecepcionDocumentosBean() {

        //se utilizar este template por defecto
        template = "./../../WEB-INF/facelets/templates/Template.xhtml";
        
        HttpServletRequest mirequest = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();

        try {
            //Obtener los datos del usuario a partir de la sesion de ingreso de ventanilla
            usuario = new Usuario();
            usuario.setIdusuario(super.getIdUsuarioSession());
            
            //armar la plantilla de acuerdo al tramite enviado
            plantillaVentanilla = elementoFormularioTramiteService.obtPlantillaVentanilla(mirequest.getAttribute("codigoSolicitud").toString());

            //Ya se tiene el idformularioExterno
            this.codigoSM = Long.parseLong(mirequest.getAttribute("codigoSM").toString());
            this.oficina = mirequest.getAttribute("oficina").toString();
            this.codigoFormulario = mirequest.getAttribute("codigoFormulario").toString();
       

            //recuperar variables de numero, gestion() y codigoModificacion (solo en el caso de modificacion)
            this.numero = Long.parseLong(mirequest.getAttribute("numero").toString());
            this.gestion = Integer.parseInt(mirequest.getAttribute("gestion").toString());
            this.SiglaModificacion = mirequest.getAttribute("codigoModificacion").toString();

            if (this.codigoFormulario.equals("PI100")) {
                generarFormularioPI100(mirequest.getAttribute("idFormularioExterno").toString());
                numeroFormulario = formularioPI100.getFormularios().getNumeroFormulario();
            }
            if (this.codigoFormulario.equals("PI101")) {
                generarFormularioPI101(mirequest.getAttribute("idFormularioExterno").toString());
                numeroFormulario = formularioPI101.getFormularios().getNumeroFormulario();
            }
            if (this.codigoFormulario.equals("PI102")) {
                generarFormularioPI102(mirequest.getAttribute("idFormularioExterno").toString());
                numeroFormulario = formularioPI102.getFormularios().getNumeroFormulario();
            }
            if (this.codigoFormulario.equals("PI103")) {
                generarFormularioPI103(mirequest.getAttribute("idFormularioExterno").toString());
                numeroFormulario = formularioPI103.getFormularios().getNumeroFormulario();
            }
            if (this.codigoFormulario.equals("PI104")) {
                generarFormularioPI104(mirequest.getAttribute("idFormularioExterno").toString());
                numeroFormulario = formularioPI104.getFormularios().getNumeroFormulario();
            }
            if (this.codigoFormulario.equals("PI105")) {
                generarFormularioPI105(mirequest.getAttribute("idFormularioExterno").toString());
                numeroFormulario = formularioPI105.getFormularios().getNumeroFormulario();
            }

            // lista de tipod de modificaciones
            listaTipoModificacion = dominioService.obtenerListadoDominio("tipo_modificacion");
            listaTipoSignoPI100 = dominioService.listar_dominio_dominiopadre("tipo_genero", "PI100");
            listaTipoSignoPI101 = dominioService.listar_dominio_dominiopadre("tipo_genero", "PI101");
            listaTipoSignoPI102 = dominioService.listar_dominio_dominiopadre("tipo_genero", "PI102");
            habilitabnt=false;

        } catch (Exception ex) {
            Logger.getLogger(IngresoRecepcionDeDocumentosBean.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (Exception ex) {
            Logger.getLogger(IngresoRecepcionDeDocumentosBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Método que permite generar el FormularioPI101 creado: Eddy Valero Fecha:
     * 06/09/2016
     *
     * @param idFormulario
     */
    public void generarFormularioPI101(String idFormulario) {

        try {
            formularioPI101 = this.formularioPI101Service.obtenerDatosFormularioPI101(Long.parseLong(idFormulario));

        } catch (Exception ex) {
            Logger.getLogger(IngresoRecepcionDeDocumentosBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Método que permite generar el FormularioPI102
     *
     * Creado: Eddy Valero Fecha: 06/09/2016
     *
     * @param idFormulario
     */
    public void generarFormularioPI102(String idFormulario) {
        try {
            formularioPI102 = this.formularioPI102Service.obtenerDatosFormularioPI102(Long.parseLong(idFormulario));
        } catch (Exception ex) {
            Logger.getLogger(IngresoRecepcionDeDocumentosBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Método que permite generar el FormularioPI103
     *
     * Creado: Eddy Valero Fecha: 27/10/2016
     *
     * @param idFormulario
     */
    public void generarFormularioPI103(String idFormulario) {
        try {
            formularioPI103 = this.formularioPI103Service.obtenerDatosFormularioPI103(Long.parseLong(idFormulario));
        } catch (Exception ex) {
            Logger.getLogger(IngresoRecepcionDeDocumentosBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Método que permite generar el FormularioPI104
     *
     * Creado: Eddy Valero Fecha: 27/10/2016
     *
     * @param idFormulario
     */
    public void generarFormularioPI104(String idFormulario) {
        try {
            formularioPI104 = this.formularioPI104Service.obtenerDatosFormularioPI104(Long.parseLong(idFormulario));
        } catch (Exception ex) {
            Logger.getLogger(IngresoRecepcionDeDocumentosBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Método que permite generar el FormularioPI105
     *
     * Creado: Ruben Ramirez Fecha: 19/07/2017
     *
     * @param idFormulario
     */
    public void generarFormularioPI105(String idFormulario) {
        try {
            formularioPI105 = this.formularioPI105Service.obtenerDatosFormularioPI105(Long.parseLong(idFormulario));
        } catch (Exception ex) {
            Logger.getLogger(IngresoRecepcionDeDocumentosBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //</editor-fold>
    /**
     * *
     * Metodo para ingresar el registro de acuerdo al tramite, se debe enviar el
     * codigoSM, para pintar la plantilla de ventanilla
     *
     */
    @Transactional
    public void accionIngresarRegistro() {

        try {
            byte ptext[] = observacion.getBytes(ISO_8859_1);
            observacion = new String(ptext, UTF_8);
            if (this.codigoFormulario.equals("PI100")) {

                //validar que el codigo sm ya se encuentra en la base de datos
                if (ingresoFormularioService.verificarExistenciaRegistroMarcaXSM(codigoSM)) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("El codigo SM ingresado ya existe, verificar los datos ingresados"));
                } else {
                    //setear el valorde la fechaIngresoSolicitudSession
                    this.mapResultado = this.ingresoFormularioService.guardarFormularioPI100(getFechaIngresoSolicitudSession(), formularioPI100, plantillaVentanilla,
                            usuario.getIdusuario(), codigoSM,
                            oficina, this.observacion);
                    sigSignoMarca = sigSignoMarcaService.obtenerRegistroSigSignoMarcaXNumeroFormulario(numeroFormulario);

                    if (sigSignoMarca != null) {
                        resultado = comunService.obtenerNumeroGestionExtensionCodigoSM(sigSignoMarca.getSm());
                        listaSigSolicitante = sigSolicitanteApoderadoService.listadoSolicitanteXidsignomarca(sigSignoMarca.getIdSignoMarca());
                        listaSigApoderado = sigSolicitanteApoderadoService.listadoApoderadoXidsignomarca(sigSignoMarca.getIdSignoMarca());
                        listaSigSignoClaseNiza = sigSignoClaseNizaService.obtenerListaSigSignoClazeNizaXIdSignoMarca(sigSignoMarca.getIdSignoMarca());
                        //listar las observaciones si existen en la solicitud
                        listaObservacionTramite = observacionTramiteService.obtListadoObservacionPorTramite("SIG", sigSignoMarca.getIdSignoMarca());
                        sigLemaComercial = sigLemaComercialService.obtenerSigLemaComercial(sigSignoMarca.getIdSignoMarca());
                        accionImprimirSig();
                    }
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("El codigo SM " + codigoSM + "de la solicitud de marca, fue ingresado correctamente... "));
                }

            }
            if (this.codigoFormulario.equals("PI101")) {

                //validar que el codigo sm ya se encuentra en la base de datos
                if (ingresoFormularioService.verificarExistenciaRegistroMarcaXSM(codigoSM)) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("El codigo SM ingresado ya existe, verificar los datos ingresados"));
                } else {
                    this.mapResultado = this.ingresoFormularioService.guardarFormularioPI101(getFechaIngresoSolicitudSession(), formularioPI101, plantillaVentanilla,
                            usuario.getIdusuario(), codigoSM,
                            oficina, this.observacion);
                    sigSignoMarca = sigSignoMarcaService.obtenerRegistroSigSignoMarcaXNumeroFormulario(numeroFormulario);

                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("El codigo SM " + codigoSM + " del depósito de nombre comercial, fue ingresado correctamente, "));

                    if (sigSignoMarca != null) {
                        resultado = comunService.obtenerNumeroGestionExtensionCodigoSM(sigSignoMarca.getSm());
                        listaSigSolicitante = sigSolicitanteApoderadoService.listadoSolicitanteXidsignomarca(sigSignoMarca.getIdSignoMarca());
                        listaSigApoderado = sigSolicitanteApoderadoService.listadoApoderadoXidsignomarca(sigSignoMarca.getIdSignoMarca());
                        listaSigSignoClaseNiza = sigSignoClaseNizaService.obtenerListaSigSignoClazeNizaXIdSignoMarca(sigSignoMarca.getIdSignoMarca());
                        //listar las observaciones si existen en la solicitud
                        listaObservacionTramite = observacionTramiteService.obtListadoObservacionPorTramite("SIG", sigSignoMarca.getIdSignoMarca());
                        sigLemaComercial = sigLemaComercialService.obtenerSigLemaComercial(sigSignoMarca.getIdSignoMarca());
                        accionImprimirSig();
                    }
                }
            }

            if (this.codigoFormulario.equals("PI102")) {

                //validar que el codigo sm ya se encuentra en la base de datos
                if (ingresoFormularioService.verificarExistenciaRegistroMarcaXSM(codigoSM)) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("El codigo SM ingresado ya existe, verificar los datos ingresados"));
                } else {
                    this.mapResultado = this.ingresoFormularioService.guardarFormularioPI102(getFechaIngresoSolicitudSession(), formularioPI102, plantillaVentanilla,
                            usuario.getIdusuario(), codigoSM,
                            oficina, this.observacion);
                    sigSignoMarca = sigSignoMarcaService.obtenerRegistroSigSignoMarcaXNumeroFormulario(numeroFormulario);
                    if (sigSignoMarca != null) {
                        resultado = comunService.obtenerNumeroGestionExtensionCodigoSM(sigSignoMarca.getSm());
                        listaSigSolicitante = sigSolicitanteApoderadoService.listadoSolicitanteXidsignomarca(sigSignoMarca.getIdSignoMarca());
                        listaSigApoderado = sigSolicitanteApoderadoService.listadoApoderadoXidsignomarca(sigSignoMarca.getIdSignoMarca());
                        listaSigSignoClaseNiza = sigSignoClaseNizaService.obtenerListaSigSignoClazeNizaXIdSignoMarca(sigSignoMarca.getIdSignoMarca());
                        //listar las observaciones si existen en la solicitud
                        listaObservacionTramite = observacionTramiteService.obtListadoObservacionPorTramite("SIG", sigSignoMarca.getIdSignoMarca());
                        sigLemaComercial = sigLemaComercialService.obtenerSigLemaComercial(sigSignoMarca.getIdSignoMarca());
                        accionImprimirSig();

                    }
                }

            }
            if (this.codigoFormulario.equals("PI103")) {

                if (ingresoFormularioService.verificarExistenciaRegistroModificacion(this.SiglaModificacion, this.numero, Long.valueOf(this.gestion))) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("El codigo de modificación ya existe, verificar los datos ingresados"));
                } else {

                    this.mapResultado = this.ingresoFormularioService.guardarFormularioPI103(getFechaIngresoSolicitudSession(), formularioPI103, plantillaVentanilla,
                            usuario.getIdusuario(), codigoSM,
                            oficina, this.observacion,
                            numero, gestion, SiglaModificacion);

                    modModificacion = modModificacionService.buscarModModificacionXnroFormulario(numeroFormulario);
                    if (modModificacion != null) {
                        listaModSolicitante = (ArrayList<ModSolicitanteApoderado>) modSolicitanteApoderadoService.listadoSolicitanteXidmodificacion(modModificacion.getIdmodificacion());
                        listaModApoderado = (ArrayList<ModSolicitanteApoderado>) modSolicitanteApoderadoService.listadoApoderadoXidmodificacion(modModificacion.getIdmodificacion());
                        listaObservacionTramite = observacionTramiteService.obtListadoObservacionPorTramite("MOD", modModificacion.getIdmodificacion());
                        accionImprimirMod();
                    }
                }

            }
            if (this.codigoFormulario.equals("PI104")) {

                if (ingresoFormularioService.verificarExistenciaRegistroRenovacion(this.numero, Long.valueOf(this.gestion))) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("El codigo de renovación ya existe, verificar los datos ingresados"));

                } else {
                    this.mapResultado = this.ingresoFormularioService.guardarFormularioPI104(getFechaIngresoSolicitudSession(), formularioPI104, plantillaVentanilla,
                            usuario.getIdusuario(), codigoSM,
                            oficina, this.observacion,
                            numero, gestion);
                    renSolicitudRenovacion = renSolicitudRenovacionService.buscarRenSolicitudRenovacionXNroFormulario(Long.parseLong(numeroFormulario));
                    if (renSolicitudRenovacion != null) {
                        listaRenSolicitante = (ArrayList<RenSolicitanteApoderado>) renSolicitanteApoderadoService.buscaSolicitanteApoderadoPoridSolicitudyTipoPersona(renSolicitudRenovacion.getIdsolicitudrenovacion(), "SOLI");
                        listaRenApoderado = (ArrayList<RenSolicitanteApoderado>) renSolicitanteApoderadoService.buscaSolicitanteApoderadoPoridSolicitudyTipoPersona(renSolicitudRenovacion.getIdsolicitudrenovacion(), "APOD");
                        listaObservacionTramite = observacionTramiteService.obtListadoObservacionPorTramite("REN", renSolicitudRenovacion.getIdsolicitudrenovacion());
                        accionImprimirRen();
                    }
                }

            }
 
            if (this.codigoFormulario.equals("PI105")) {
                
                if (ingresoFormularioService.verificarExistenciaRegistroOposicion(this.numero, Long.valueOf(this.gestion))) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("El codigo de oposicion ya existe, verificar los datos ingresados"));

                } else {
                    this.mapResultado = this.ingresoFormularioService.guardarFormularioPI105(getFechaIngresoSolicitudSession(), formularioPI105, plantillaVentanilla,
                            usuario.getIdusuario(), codigoSM,
                            oficina, this.observacion,
                            numero, gestion);
                    
                    oposicion = oposicionService.obtoposicionxnroformulario(Long.parseLong(numeroFormulario));
                    if (oposicion != null) {
                        opoMarcaDemandada = opoMarcaDemandadaService.obtenerOpomarcademandadaobj(oposicion.getIdoposicion());
                        opoMarcaDemandante = opoMarcaDemandanteService.obtieneelobjetodmtexidoposicion(oposicion.getIdoposicion());
                        listaOpoSolicitante = opoSolicitanteApoderadoService.obtenerListadoSoliDmte(opoMarcaDemandante.getIdmarcademandante());
                        listaOpoApoderado =  opoSolicitanteApoderadoService.obtenerListadoApoDmte(opoMarcaDemandante.getIdmarcademandante());
                        listaObservacionTramite = observacionTramiteService.obtListadoObservacionPorTramite("OPO", oposicion.getIdoposicion());
                        accionImprimirOpo();
                    }
                    
                }

            }
            habilitabnt=true;

        } catch (Exception e) {
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocurrio un error al ingresar el registro, comunicarse con el área de Sistemas SENAPI", ""));
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El registro no existe", ""));
            Logger.getLogger(IngresoRecepcionDeDocumentosBean.class.getName()).log(Level.SEVERE, null, e);
        }

//        //si todo esta bien retornar al ingreso solicitud
//        return "ingresoSolicitud";
    }

    /**
     * *
     * Metodo para llenar el formulario 100, 101, 102 de signo marca.
     *
     * Creado: Ruben Ramirez Fecha: 07/11/2016
     *
     * @throws net.sf.jasperreports.engine.JRException
     * @throws java.io.IOException
     */
    public void accionImprimirSig() throws JRException, IOException {

        imageSenapi = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/images/logoNuevo.jpg");
        imageEscudoBol = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/images/esc-Bolivia");

        SimpleDateFormat formateador1 = new SimpleDateFormat("dd/MM/yyyy");
        fechaIngreso = formateador1.format(sigSignoMarca.getFechaIngreso());
        SimpleDateFormat formateador2 = new SimpleDateFormat("HH:mm:ss");
        horaIngreso = formateador2.format(sigSignoMarca.getFechaIngreso());

        sm = "SM-" + String.format("%6s", resultado.get("numero")).replace(' ', '0') + resultado.get("extension") + "-" + resultado.get("gestion");
        
        String tipoSigno = "";
        
        for (Dominio item : listaTipoSignoPI100) {
            if (item.getCodigo().equals(sigSignoMarca.getTipoGenero())) {
                tipoSigno = "REGISTRO DE SIGNO DISTINTIVO";
                break;
            }
        }
        
        for (Dominio item : listaTipoSignoPI101) {
            if (item.getCodigo().equals(sigSignoMarca.getTipoGenero())) {
                tipoSigno = item.getNombre().toUpperCase();
                break;
            }
        }
        
        for (Dominio item : listaTipoSignoPI102) {
            if (item.getCodigo().equals(sigSignoMarca.getTipoGenero())) {
                tipoSigno = item.getNombre().toUpperCase();
                break;
            }
        }

        titulo = "FORMULARIO DE VERIFICACION DE INGRESO DE "+tipoSigno+" "+sm;

        int numListaSigSignoClaseNiza = listaSigSignoClaseNiza.size();
        if (numListaSigSignoClaseNiza > 0) {
            if (numListaSigSignoClaseNiza == 1) {
                clase = "" + listaSigSignoClaseNiza.get(0).getNumeroClaseNiza();
            } else {
                for (int i = 0; i < numListaSigSignoClaseNiza - 1; i++) {
                    clase += listaSigSignoClaseNiza.get(i).getNumeroClaseNiza() + "; ";
                }
                clase += listaSigSignoClaseNiza.get(numListaSigSignoClaseNiza - 1).getNumeroClaseNiza();
            }
        }
        
        if (this.codigoFormulario.equals("PI102")) {
            nombreMarca = sigLemaComercial.getSignoPadre();

            if (sigSignoMarca.getSigno() != null) {
                lemaComercial = sigSignoMarca.getSigno();
            } else {
                lemaComercial = "";
            }
        } else {
            nombreMarca = sigSignoMarca.getSigno();

            if (sigLemaComercial.getIdLemaComercial() != null) {
                lemaComercial = sigLemaComercial.getSignoPadre();
            } else {
                lemaComercial = "";
            }
        }

        int numListaSigSolicitante = listaSigSolicitante.size();
        if (numListaSigSolicitante > 0) {
            if (numListaSigSolicitante == 1) {
                solicitantes = devuelveNombreJuridicoONatural(listaSigSolicitante.get(0).getNombreRazonSocial(), listaSigSolicitante.get(0).getPrimerApellido(), listaSigSolicitante.get(0).getSegundoApellido());
            } else {
                for (int i = 0; i < numListaSigSolicitante - 1; i++) {
                    solicitantes += devuelveNombreJuridicoONatural(listaSigSolicitante.get(i).getNombreRazonSocial(), listaSigSolicitante.get(i).getPrimerApellido(), listaSigSolicitante.get(i).getSegundoApellido()) + "; ";
                }
                solicitantes += devuelveNombreJuridicoONatural(listaSigSolicitante.get(numListaSigSolicitante - 1).getNombreRazonSocial(), listaSigSolicitante.get(numListaSigSolicitante - 1).getPrimerApellido(), listaSigSolicitante.get(numListaSigSolicitante - 1).getSegundoApellido());
            }
        }

        int numListaSigApoderado = listaSigApoderado.size();
        if (numListaSigApoderado > 0) {
            if (numListaSigApoderado == 1) {
                apoderados = devuelveNombreJuridicoONatural(listaSigApoderado.get(0).getNombreRazonSocial(), listaSigApoderado.get(0).getPrimerApellido(), listaSigApoderado.get(0).getSegundoApellido());
            } else {
                for (int i = 0; i < numListaSigApoderado - 1; i++) {
                    apoderados += devuelveNombreJuridicoONatural(listaSigApoderado.get(i).getNombreRazonSocial(), listaSigApoderado.get(i).getPrimerApellido(), listaSigApoderado.get(i).getSegundoApellido()) + "; ";
                }
                apoderados += devuelveNombreJuridicoONatural(listaSigApoderado.get(numListaSigApoderado - 1).getNombreRazonSocial(), listaSigApoderado.get(numListaSigApoderado - 1).getPrimerApellido(), listaSigApoderado.get(numListaSigApoderado - 1).getSegundoApellido());
            }
        }

        int cont = -1;
        for (int i = 0; i < plantillaVentanilla.size(); i++) {
            ElementoFormularioTramite item1 = plantillaVentanilla.get(i);
            if (item1.getTipoElemento().equals("TEH8")) {
                SeccionReporte seccionReporte = new SeccionReporte();
                listaSeccionReporte.add(seccionReporte);
                cont++;
                listaSeccionReporte.get(cont).setTitulo(item1.getNombreElemento());
            } else {
                List<SubseccionReporte> listaSubseccionReporte = new ArrayList<>();
                int aux = i;
                for (int j = i; j < plantillaVentanilla.size(); j++) {
                    aux = j;
                    ElementoFormularioTramite item2 = plantillaVentanilla.get(j);
                    if (item2.getTipoElemento().equals("TEH8")) {
                        aux = j - 1;
                        break;
                    } else {
                        if (item2.getTipoElemento().equals("TEH2")) {
                            SubseccionReporte subseccionReporte = new SubseccionReporte();
                            subseccionReporte.setCampo1(item2.getNombreElemento());
                            subseccionReporte.setFojas(false);
                            listaSubseccionReporte.add(subseccionReporte);
                        }
                        if (item2.getTipoElemento().equals("TEH7")) {
                            if (item2.getIdpadre() == 0) {
                                SubseccionReporte subseccionReporte = new SubseccionReporte();
                                subseccionReporte.setCampo1(item2.getNombreElemento());
                                subseccionReporte.setCampo3(rutaCheck(item2.getOpcionRespuesta()));
                                subseccionReporte.setFojas(false);
                                listaSubseccionReporte.add(subseccionReporte);
                            } else {
                                SubseccionReporte subseccionReporte = new SubseccionReporte();
                                subseccionReporte.setCampo2(item2.getNombreElemento());
                                subseccionReporte.setCampo3(rutaCheck(item2.getOpcionRespuesta()));
                                subseccionReporte.setFojas(false);
                                listaSubseccionReporte.add(subseccionReporte);
                            }
                        }
                        if (item2.getTipoElemento().equals("TEH1")) {
                            SubseccionReporte subseccionReporte = new SubseccionReporte();
                            subseccionReporte.setCampo1(item2.getNombreElemento());
                            subseccionReporte.setCampo2(item2.getRespuesta());
                            subseccionReporte.setFojas(true);
                            listaSubseccionReporte.add(subseccionReporte);
                        }
                    }
                }
                i = aux;
                if (cont > -1) {
                    listaSeccionReporte.get(cont).setDatosSubseccion(listaSubseccionReporte);
                }
            }
        }

        int numListaObservacionTramite = listaObservacionTramite.size();
        if (numListaObservacionTramite > 0) {
            if (numListaObservacionTramite == 1) {
                observacion2 = "" + listaObservacionTramite.get(0).getDescripcion();
            } else {
                for (int i = 0; i < numListaObservacionTramite - 1; i++) {
                    observacion2 += listaObservacionTramite.get(i).getDescripcion() + "; ";
                }
                observacion2 += listaObservacionTramite.get(numListaObservacionTramite - 1).getDescripcion();
            }
        }

        parametros.put("imgSenapi", imageSenapi);
        parametros.put("imgBolivia", imageEscudoBol);
        parametros.put("numeroFormulario", numeroFormulario);
        parametros.put("fechaIngreso", fechaIngreso);
        parametros.put("horaIngreso", horaIngreso);
        parametros.put("titulo", titulo);
        parametros.put("nombreMarca", nombreMarca);
        parametros.put("clase", clase);
        parametros.put("lemaComercial", lemaComercial);
        parametros.put("solicitantes", solicitantes);
        parametros.put("apoderados", apoderados);
        parametros.put("observacion", observacion2);
        parametros.put("seccion", getDatos(listaSeccionReporte));

        String filename = sigSignoMarca.getSm() + ".pdf";
        String jasperPath = "/template/formulario/IngresoRecepcionDeDocumentos.jasper";
        this.PDFSD(parametros, jasperPath, filename);

        clase = "";
        solicitantes = "";
        apoderados = "";
        observacion2 = "";
        listaSeccionReporte.clear();
    }

    /**
     * *
     * Metodo para llenar el formulario 103 de modificaciones.
     *
     * Creado: Ruben Ramirez Fecha: 07/11/2016
     *
     * @throws net.sf.jasperreports.engine.JRException
     * @throws java.io.IOException
     */
    public void accionImprimirMod() throws Exception, JRException, IOException {

        imageSenapi = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/images/logoNuevo.jpg");
        imageEscudoBol = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/images/esc-Bolivia");

        SimpleDateFormat formateador1 = new SimpleDateFormat("dd/MM/yyyy");
        fechaIngreso = formateador1.format(modModificacion.getFecha_ingreso());
        SimpleDateFormat formateador2 = new SimpleDateFormat("HH:mm:ss");
        horaIngreso = formateador2.format(modModificacion.getFecha_ingreso());

        sm = "" + modModificacion.getSigla() + "-" + String.format("%6s", modModificacion.getNumero()).replace(' ', '0') + "-" + modModificacion.getGestion();
        
        for (Dominio item : listaTipoModificacion) {
            if (item.getCodigo().equals(modModificacion.getTipo_modificacion())) {
                titulo = "FORMULARIO DE VERIFICACION DE INGRESO DE " + item.getNombre().toUpperCase() + " " +sm;
                break;
            }
        }

        nombreMarca = modModificacion.getSigno_registrado();
        
        if(modModificacion.getIdclase_registrado()!=null){
            ClaseNiza li=claseNizaService.listarClaseNiza_id(modModificacion.getIdclase_registrado());
            if(li!=null){
                clase = li.getNumeroClaseNiza().toString();
            }    
        }

        lemaComercial = "";

        int numListaModSolicitante = listaModSolicitante.size();
        if (numListaModSolicitante > 0) {
            if (numListaModSolicitante == 1) {
                solicitantes = devuelveNombreJuridicoONatural(listaModSolicitante.get(0).getNombre_razonsocial(), listaModSolicitante.get(0).getPrimer_apellido(), listaModSolicitante.get(0).getSegundo_apellido());
            } else {
                for (int i = 0; i < numListaModSolicitante - 1; i++) {
                    solicitantes += devuelveNombreJuridicoONatural(listaModSolicitante.get(i).getNombre_razonsocial(), listaModSolicitante.get(i).getPrimer_apellido(), listaModSolicitante.get(i).getSegundo_apellido()) + "; ";
                }
                solicitantes += devuelveNombreJuridicoONatural(listaModSolicitante.get(numListaModSolicitante - 1).getNombre_razonsocial(), listaModSolicitante.get(numListaModSolicitante - 1).getPrimer_apellido(), listaModSolicitante.get(numListaModSolicitante - 1).getSegundo_apellido());
            }
        }

        int numListaModApoderado = listaModApoderado.size();
        if (numListaModApoderado > 0) {
            if (numListaModApoderado == 1) {
                apoderados = devuelveNombreJuridicoONatural(listaModApoderado.get(0).getNombre_razonsocial(), listaModApoderado.get(0).getPrimer_apellido(), listaModApoderado.get(0).getSegundo_apellido());
            } else {
                for (int i = 0; i < numListaModApoderado - 1; i++) {
                    apoderados += devuelveNombreJuridicoONatural(listaModApoderado.get(i).getNombre_razonsocial(), listaModApoderado.get(i).getPrimer_apellido(), listaModApoderado.get(i).getSegundo_apellido()) + "; ";
                }
                apoderados += devuelveNombreJuridicoONatural(listaModApoderado.get(numListaModApoderado - 1).getNombre_razonsocial(), listaModApoderado.get(numListaModApoderado - 1).getPrimer_apellido(), listaModApoderado.get(numListaModApoderado - 1).getSegundo_apellido());
            }
        }

        int cont = -1;
        for (int i = 0; i < plantillaVentanilla.size(); i++) {
            ElementoFormularioTramite item1 = plantillaVentanilla.get(i);
            if (item1.getTipoElemento().equals("TEH8")) {
                SeccionReporte seccionReporte = new SeccionReporte();
                listaSeccionReporte.add(seccionReporte);
                cont++;
                listaSeccionReporte.get(cont).setTitulo(item1.getNombreElemento());
            } else {
                List<SubseccionReporte> listaSubseccionReporte = new ArrayList<>();
                int aux = i;
                for (int j = i; j < plantillaVentanilla.size(); j++) {
                    aux = j;
                    ElementoFormularioTramite item2 = plantillaVentanilla.get(j);
                    if (item2.getTipoElemento().equals("TEH8")) {
                        aux = j - 1;
                        break;
                    } else {
                        if (item2.getTipoElemento().equals("TEH2")) {
                            SubseccionReporte subseccionReporte = new SubseccionReporte();
                            subseccionReporte.setCampo1(item2.getNombreElemento());
                            subseccionReporte.setFojas(false);
                            listaSubseccionReporte.add(subseccionReporte);
                        }
                        if (item2.getTipoElemento().equals("TEH7")) {
                            if (item2.getIdpadre() == 0) {
                                SubseccionReporte subseccionReporte = new SubseccionReporte();
                                subseccionReporte.setCampo1(item2.getNombreElemento());
                                subseccionReporte.setCampo3(rutaCheck(item2.getOpcionRespuesta()));
                                subseccionReporte.setFojas(false);
                                listaSubseccionReporte.add(subseccionReporte);
                            } else {
                                SubseccionReporte subseccionReporte = new SubseccionReporte();
                                subseccionReporte.setCampo2(item2.getNombreElemento());
                                subseccionReporte.setCampo3(rutaCheck(item2.getOpcionRespuesta()));
                                subseccionReporte.setFojas(false);
                                listaSubseccionReporte.add(subseccionReporte);
                            }
                        }
                        if (item2.getTipoElemento().equals("TEH1")) {
                            SubseccionReporte subseccionReporte = new SubseccionReporte();
                            subseccionReporte.setCampo1(item2.getNombreElemento());
                            subseccionReporte.setCampo2(item2.getRespuesta());
                            subseccionReporte.setFojas(true);
                            listaSubseccionReporte.add(subseccionReporte);
                        }
                    }
                }
                i = aux;
                if (cont > -1) {
                    listaSeccionReporte.get(cont).setDatosSubseccion(listaSubseccionReporte);
                }
            }
        }

        int numListaObservacionTramite = listaObservacionTramite.size();
        if (numListaObservacionTramite > 0) {
            if (numListaObservacionTramite == 1) {
                observacion2 = "" + listaObservacionTramite.get(0).getDescripcion();
            } else {
                for (int i = 0; i < numListaObservacionTramite - 1; i++) {
                    observacion2 += listaObservacionTramite.get(i).getDescripcion() + "; ";
                }
                observacion2 += listaObservacionTramite.get(numListaObservacionTramite - 1).getDescripcion();
            }
        }

        parametros.put("imgSenapi", imageSenapi);
        parametros.put("imgBolivia", imageEscudoBol);
        parametros.put("numeroFormulario", numeroFormulario);
        parametros.put("fechaIngreso", fechaIngreso);
        parametros.put("horaIngreso", horaIngreso);
        parametros.put("titulo", titulo);
        parametros.put("nombreMarca", nombreMarca);
        parametros.put("clase", clase);
        parametros.put("lemaComercial", lemaComercial);
        parametros.put("solicitantes", solicitantes);
        parametros.put("apoderados", apoderados);
        parametros.put("observacion", observacion2);
        parametros.put("seccion", getDatos(listaSeccionReporte));

        String filename = modModificacion.getSm() + ".pdf";
        String jasperPath = "/template/formulario/IngresoRecepcionDeDocumentos.jasper";
        this.PDFSD(parametros, jasperPath, filename);

        solicitantes = "";
        apoderados = "";
        observacion2 = "";
        listaSeccionReporte.clear();
    }

    /**
     * Metodo para llenar el formulario 104 de solicitud de renovaciones.
     *
     * Creado: Ruben Ramirez Fecha: 07/11/2016
     *
     * @throws net.sf.jasperreports.engine.JRException
     * @throws java.io.IOException
     */
    public void accionImprimirRen() throws Exception, JRException, IOException {

        imageSenapi = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/images/logoNuevo.jpg");
        imageEscudoBol = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/images/esc-Bolivia");

        SimpleDateFormat formateador1 = new SimpleDateFormat("dd/MM/yyyy");
        fechaIngreso = formateador1.format(renSolicitudRenovacion.getFecha_ingreso());
        SimpleDateFormat formateador2 = new SimpleDateFormat("HH:mm:ss");
        horaIngreso = formateador2.format(renSolicitudRenovacion.getFecha_ingreso());

        sm = "SR-" + String.format("%6s", renSolicitudRenovacion.getSr()).replace(' ', '0') + "-" + renSolicitudRenovacion.getGestion_sr();
        
        titulo = "FORMULARIO DE VERIFICACION DE INGRESO PARA REGISTRO DE RENOVACION "+ sm;

        nombreMarca = renSolicitudRenovacion.getSigno_registrado();

        if(renSolicitudRenovacion.getIdclase_niza_registrado()!=null){
            ClaseNiza li=claseNizaService.listarClaseNiza_id(renSolicitudRenovacion.getIdclase_niza_registrado());
            if(li!=null){
                clase = li.getNumeroClaseNiza().toString();
            }    
        }

        lemaComercial = "";
        
        int numListaRenSolicitante = listaRenSolicitante.size();
        if (numListaRenSolicitante > 0) {
            if (numListaRenSolicitante == 1) {
                solicitantes = devuelveNombreJuridicoONatural(listaRenSolicitante.get(0).getNombre_razonsocial(), listaRenSolicitante.get(0).getPrimer_apellido(), listaRenSolicitante.get(0).getSegundo_apellido());
            } else {
                for (int i = 0; i < numListaRenSolicitante - 1; i++) {
                    solicitantes += devuelveNombreJuridicoONatural(listaRenSolicitante.get(i).getNombre_razonsocial(), listaRenSolicitante.get(i).getPrimer_apellido(), listaRenSolicitante.get(i).getSegundo_apellido()) + "; ";
                }
                solicitantes += devuelveNombreJuridicoONatural(listaRenSolicitante.get(numListaRenSolicitante - 1).getNombre_razonsocial(), listaRenSolicitante.get(numListaRenSolicitante - 1).getPrimer_apellido(), listaRenSolicitante.get(numListaRenSolicitante - 1).getSegundo_apellido());
            }
        }

        int numListaRenApoderado = listaRenApoderado.size();
        if (numListaRenApoderado > 0) {
            if (numListaRenApoderado == 1) {
                apoderados = devuelveNombreJuridicoONatural(listaRenApoderado.get(0).getNombre_razonsocial(), listaRenApoderado.get(0).getPrimer_apellido(), listaRenApoderado.get(0).getSegundo_apellido());
            } else {
                for (int i = 0; i < numListaRenApoderado - 1; i++) {
                    apoderados += devuelveNombreJuridicoONatural(listaRenApoderado.get(i).getNombre_razonsocial(), listaRenApoderado.get(i).getPrimer_apellido(), listaRenApoderado.get(i).getSegundo_apellido()) + "; ";
                }
                apoderados += devuelveNombreJuridicoONatural(listaRenApoderado.get(numListaRenApoderado - 1).getNombre_razonsocial(), listaRenApoderado.get(numListaRenApoderado - 1).getPrimer_apellido(), listaRenApoderado.get(numListaRenApoderado - 1).getSegundo_apellido());
            }
        }

        int cont = -1;
        for (int i = 0; i < plantillaVentanilla.size(); i++) {
            ElementoFormularioTramite item1 = plantillaVentanilla.get(i);
            if (item1.getTipoElemento().equals("TEH8")) {
                SeccionReporte seccionReporte = new SeccionReporte();
                listaSeccionReporte.add(seccionReporte);
                cont++;
                listaSeccionReporte.get(cont).setTitulo(item1.getNombreElemento());
            } else {
                List<SubseccionReporte> listaSubseccionReporte = new ArrayList<>();
                int aux = i;
                for (int j = i; j < plantillaVentanilla.size(); j++) {
                    aux = j;
                    ElementoFormularioTramite item2 = plantillaVentanilla.get(j);
                    if (item2.getTipoElemento().equals("TEH8")) {
                        aux = j - 1;
                        break;
                    } else {
                        if (item2.getTipoElemento().equals("TEH2")) {
                            SubseccionReporte subseccionReporte = new SubseccionReporte();
                            subseccionReporte.setCampo1(item2.getNombreElemento());
                            subseccionReporte.setFojas(false);
                            listaSubseccionReporte.add(subseccionReporte);
                        }
                        if (item2.getTipoElemento().equals("TEH7")) {
                            if (item2.getIdpadre() == 0) {
                                SubseccionReporte subseccionReporte = new SubseccionReporte();
                                subseccionReporte.setCampo1(item2.getNombreElemento());
                                subseccionReporte.setCampo3(rutaCheck(item2.getOpcionRespuesta()));
                                subseccionReporte.setFojas(false);
                                listaSubseccionReporte.add(subseccionReporte);
                            } else {
                                SubseccionReporte subseccionReporte = new SubseccionReporte();
                                subseccionReporte.setCampo2(item2.getNombreElemento());
                                subseccionReporte.setCampo3(rutaCheck(item2.getOpcionRespuesta()));
                                subseccionReporte.setFojas(false);
                                listaSubseccionReporte.add(subseccionReporte);
                            }
                        }
                        if (item2.getTipoElemento().equals("TEH1")) {
                            SubseccionReporte subseccionReporte = new SubseccionReporte();
                            subseccionReporte.setCampo1(item2.getNombreElemento());
                            subseccionReporte.setCampo2(item2.getRespuesta());
                            subseccionReporte.setFojas(true);
                            listaSubseccionReporte.add(subseccionReporte);
                        }
                    }
                }
                i = aux;
                if (cont > -1) {
                    listaSeccionReporte.get(cont).setDatosSubseccion(listaSubseccionReporte);
                }
            }
        }
        
        int numListaObservacionTramite = listaObservacionTramite.size();
        if (numListaObservacionTramite > 0) {
            if (numListaObservacionTramite == 1) {
                observacion2 = "" + listaObservacionTramite.get(0).getDescripcion();
            } else {
                for (int i = 0; i < numListaObservacionTramite - 1; i++) {
                    observacion2 += listaObservacionTramite.get(i).getDescripcion() + "; ";
                }
                observacion2 += listaObservacionTramite.get(numListaObservacionTramite - 1).getDescripcion();
            }
        }

        parametros.put("imgSenapi", imageSenapi);
        parametros.put("imgBolivia", imageEscudoBol);
        parametros.put("numeroFormulario", numeroFormulario);
        parametros.put("fechaIngreso", fechaIngreso);
        parametros.put("horaIngreso", horaIngreso);
        parametros.put("titulo", titulo);
        parametros.put("nombreMarca", nombreMarca);
        parametros.put("clase", clase);
        parametros.put("lemaComercial", lemaComercial);
        parametros.put("solicitantes", solicitantes);
        parametros.put("apoderados", apoderados);
        parametros.put("observacion", observacion2);
        parametros.put("seccion", getDatos(listaSeccionReporte));

        String filename = renSolicitudRenovacion.getNumero_formulario()+ ".pdf";
        String jasperPath = "/template/formulario/IngresoRecepcionDeDocumentos.jasper";
        this.PDFSD(parametros, jasperPath, filename);

        solicitantes = "";
        apoderados = "";
        observacion2 = "";
        listaSeccionReporte.clear();
    }
    
    /**
     * Metodo para llenar el formulario 104 de solicitud de renovaciones.
     *
     * Creado: Ruben Ramirez Fecha: 07/11/2016
     *
     * @throws net.sf.jasperreports.engine.JRException
     * @throws java.io.IOException
     */
    public void accionImprimirOpo() throws JRException, IOException {

        imageSenapi = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/images/logoNuevo.jpg");
        imageEscudoBol = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/images/esc-Bolivia");

        SimpleDateFormat formateador1 = new SimpleDateFormat("dd/MM/yyyy");
        fechaIngreso = formateador1.format(oposicion.getFecha_presentacion());
        SimpleDateFormat formateador2 = new SimpleDateFormat("HH:mm:ss");
        horaIngreso = formateador2.format(oposicion.getFecha_presentacion());

        sm = "OP-" + String.format("%6s", oposicion.getCodigo_opo()).replace(' ', '0') + "-" + oposicion.getGestion_opo();
        
        titulo = "FORMULARIO DE VERIFICACION DE INGRESO PARA OPOSICION DE REGISTRO "+ sm;

        numeroPublicacion = opoMarcaDemandada.getDmdo_public();
        gaceta = opoMarcaDemandada.getGaceta();
        nroorden = oposicion.getOrden_o();
        
        int numListaOpoSolicitante = listaOpoSolicitante.size();
        if (numListaOpoSolicitante > 0) {
            if (numListaOpoSolicitante == 1) {
                solicitantes = devuelveNombreJuridicoONatural(listaOpoSolicitante.get(0).getNombre_razonsocial(), listaOpoSolicitante.get(0).getPrimer_apellido(), listaOpoSolicitante.get(0).getSegundo_apellido());
            } else {
                for (int i = 0; i < numListaOpoSolicitante - 1; i++) {
                    solicitantes += devuelveNombreJuridicoONatural(listaOpoSolicitante.get(i).getNombre_razonsocial(), listaOpoSolicitante.get(i).getPrimer_apellido(), listaOpoSolicitante.get(i).getSegundo_apellido()) + "; ";
                }
                solicitantes += devuelveNombreJuridicoONatural(listaOpoSolicitante.get(numListaOpoSolicitante - 1).getNombre_razonsocial(), listaOpoSolicitante.get(numListaOpoSolicitante - 1).getPrimer_apellido(), listaOpoSolicitante.get(numListaOpoSolicitante - 1).getSegundo_apellido());
            }
        }

        int numListaOpoApoderado = listaOpoApoderado.size();
        if (numListaOpoApoderado > 0) {
            if (numListaOpoApoderado == 1) {
                apoderados = devuelveNombreJuridicoONatural(listaOpoApoderado.get(0).getNombre_razonsocial(), listaOpoApoderado.get(0).getPrimer_apellido(), listaOpoApoderado.get(0).getSegundo_apellido());
            } else {
                for (int i = 0; i < numListaOpoApoderado - 1; i++) {
                    apoderados += devuelveNombreJuridicoONatural(listaOpoApoderado.get(i).getNombre_razonsocial(), listaOpoApoderado.get(i).getPrimer_apellido(), listaOpoApoderado.get(i).getSegundo_apellido()) + "; ";
                }
                apoderados += devuelveNombreJuridicoONatural(listaOpoApoderado.get(numListaOpoApoderado - 1).getNombre_razonsocial(), listaOpoApoderado.get(numListaOpoApoderado - 1).getPrimer_apellido(), listaOpoApoderado.get(numListaOpoApoderado - 1).getSegundo_apellido());
            }
        }

        int cont = -1;
        for (int i = 0; i < plantillaVentanilla.size(); i++) {
            ElementoFormularioTramite item1 = plantillaVentanilla.get(i);
            if (item1.getTipoElemento().equals("TEH8")) {
                SeccionReporte seccionReporte = new SeccionReporte();
                listaSeccionReporte.add(seccionReporte);
                cont++;
                listaSeccionReporte.get(cont).setTitulo(item1.getNombreElemento());
            } else {
                List<SubseccionReporte> listaSubseccionReporte = new ArrayList<>();
                int aux = i;
                for (int j = i; j < plantillaVentanilla.size(); j++) {
                    aux = j;
                    ElementoFormularioTramite item2 = plantillaVentanilla.get(j);
                    if (item2.getTipoElemento().equals("TEH8")) {
                        aux = j - 1;
                        break;
                    } else {
                        if (item2.getTipoElemento().equals("TEH2")) {
                            SubseccionReporte subseccionReporte = new SubseccionReporte();
                            subseccionReporte.setCampo1(item2.getNombreElemento());
                            subseccionReporte.setFojas(false);
                            listaSubseccionReporte.add(subseccionReporte);
                        }
                        if (item2.getTipoElemento().equals("TEH7")) {
                            if (item2.getIdpadre() == 0) {
                                SubseccionReporte subseccionReporte = new SubseccionReporte();
                                subseccionReporte.setCampo1(item2.getNombreElemento());
                                subseccionReporte.setCampo3(rutaCheck(item2.getOpcionRespuesta()));
                                subseccionReporte.setFojas(false);
                                listaSubseccionReporte.add(subseccionReporte);
                            } else {
                                SubseccionReporte subseccionReporte = new SubseccionReporte();
                                subseccionReporte.setCampo2(item2.getNombreElemento());
                                subseccionReporte.setCampo3(rutaCheck(item2.getOpcionRespuesta()));
                                subseccionReporte.setFojas(false);
                                listaSubseccionReporte.add(subseccionReporte);
                            }
                        }
                        if (item2.getTipoElemento().equals("TEH1") || item2.getTipoElemento().equals("TEH3")) {
                            SubseccionReporte subseccionReporte = new SubseccionReporte();
                            subseccionReporte.setCampo1(item2.getNombreElemento());
                            subseccionReporte.setCampo2(item2.getRespuesta());
                            subseccionReporte.setFojas(true);
                            listaSubseccionReporte.add(subseccionReporte);
                        }
                    }
                }
                i = aux;
                if (cont > -1) {
                    listaSeccionReporte.get(cont).setDatosSubseccion(listaSubseccionReporte);
                }
            }
        }
        
        int numListaObservacionTramite = listaObservacionTramite.size();
        if (numListaObservacionTramite > 0) {
            if (numListaObservacionTramite == 1) {
                observacion2 = "" + listaObservacionTramite.get(0).getDescripcion();
            } else {
                for (int i = 0; i < numListaObservacionTramite - 1; i++) {
                    observacion2 += listaObservacionTramite.get(i).getDescripcion() + "; ";
                }
                observacion2 += listaObservacionTramite.get(numListaObservacionTramite - 1).getDescripcion();
            }
        }
        
        parametros.put("fechaIngreso", fechaIngreso);
        parametros.put("horaIngreso", horaIngreso);
        parametros.put("numeroFormulario", numeroFormulario);
        parametros.put("imgSenapi", imageSenapi);
        parametros.put("imgBolivia", imageEscudoBol);
        parametros.put("solicitantes", solicitantes);
        parametros.put("observacion", observacion2);
        parametros.put("titulo", titulo);
        parametros.put("apoderados", apoderados);
        parametros.put("seccion", getDatos(listaSeccionReporte));    
        parametros.put("numeroPublicacion", numeroPublicacion);
        parametros.put("gaceta", gaceta);
        parametros.put("nroorden",nroorden);

        String filename = oposicion.getNumero_formulario()+ ".pdf";
        String jasperPath = "/template/formulario/IngresoRecepcionOposicion.jasper";
        this.PDFSD(parametros, jasperPath, filename);

        solicitantes = "";
        apoderados = "";
        observacion2 = "";
        listaSeccionReporte.clear();
    }
    
     /**
     * Metodo para generar el nombre juridico o natural completo de la persona.
     *
     * Creado: Ruben Ramirez Fecha: 12/01/2017
     *
     * @param nombre
     * @param primerApellido
     * @param segundoApellido
     */

    public String devuelveNombreJuridicoONatural(String nombre, String primerApellido, String segundoApellido) {
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

    public JRDataSource getDatos(List<?> listaObjeto) {
        return new JRBeanCollectionDataSource(listaObjeto);
    }

    /**
     * Metodo para generar el reporte en formato pdf sin lista de objetos.
     *
     * Creado: Ruben Ramirez Fecha: 07/11/2016
     *
     * @param params
     * @param jasperPath
     * @param fileName
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

    //Metodos de botones
    public String accionRetornarIngresoTramite() {
        return "ingresoSolicitud";
    }
    
    public String accionGeneraRecibos() {
        return "recibo";
    }

    public String rutaCheck(String check) {
        String ruta = "";
        if (check.equals("true")) {
            ruta = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/images/chkEnable.png");
        } else {
            ruta = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/images/chkDisable.png");
        }
        return ruta;
    }
    
    
    public void accionIngresarRegistroProto() {

        try {
            byte ptext[] = observacion.getBytes(ISO_8859_1);
            observacion = new String(ptext, UTF_8);
            if (this.codigoFormulario.equals("PI100")) {

                //validar que el codigo sm ya se encuentra en la base de datos
                if (ingresoFormularioService.verificarExistenciaRegistroMarcaXSM(codigoSM)) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("El codigo SM ingresado ya existe, verificar los datos ingresados"));
                } else {
                    //setear el valorde la fechaIngresoSolicitudSession
                    this.mapResultado = this.ingresoFormularioService.guardarFormularioPI100(getFechaIngresoSolicitudSession(), formularioPI100, plantillaVentanilla,
                            usuario.getIdusuario(), codigoSM,
                            oficina, this.observacion);
                    sigSignoMarca = sigSignoMarcaService.obtenerRegistroSigSignoMarcaXNumeroFormulario(numeroFormulario);

                    if (sigSignoMarca != null) {
                        resultado = comunService.obtenerNumeroGestionExtensionCodigoSM(sigSignoMarca.getSm());
                        listaSigSolicitante = sigSolicitanteApoderadoService.listadoSolicitanteXidsignomarca(sigSignoMarca.getIdSignoMarca());
                        listaSigApoderado = sigSolicitanteApoderadoService.listadoApoderadoXidsignomarca(sigSignoMarca.getIdSignoMarca());
                        listaSigSignoClaseNiza = sigSignoClaseNizaService.obtenerListaSigSignoClazeNizaXIdSignoMarca(sigSignoMarca.getIdSignoMarca());
                        //listar las observaciones si existen en la solicitud
                        listaObservacionTramite = observacionTramiteService.obtListadoObservacionPorTramite("SIG", sigSignoMarca.getIdSignoMarca());
                        sigLemaComercial = sigLemaComercialService.obtenerSigLemaComercial(sigSignoMarca.getIdSignoMarca());
                        accionImprimirSigProto();
                    }
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("El codigo SM " + codigoSM + "de la solicitud de marca, fue ingresado correctamente... "));
                }

            }
            if (this.codigoFormulario.equals("PI101")) {

                //validar que el codigo sm ya se encuentra en la base de datos
                if (ingresoFormularioService.verificarExistenciaRegistroMarcaXSM(codigoSM)) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("El codigo SM ingresado ya existe, verificar los datos ingresados"));
                } else {
                    this.mapResultado = this.ingresoFormularioService.guardarFormularioPI101(getFechaIngresoSolicitudSession(), formularioPI101, plantillaVentanilla,
                            usuario.getIdusuario(), codigoSM,
                            oficina, this.observacion);
                    sigSignoMarca = sigSignoMarcaService.obtenerRegistroSigSignoMarcaXNumeroFormulario(numeroFormulario);

                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("El codigo SM " + codigoSM + " del depósito de nombre comercial, fue ingresado correctamente, "));

                    if (sigSignoMarca != null) {
                        resultado = comunService.obtenerNumeroGestionExtensionCodigoSM(sigSignoMarca.getSm());
                        listaSigSolicitante = sigSolicitanteApoderadoService.listadoSolicitanteXidsignomarca(sigSignoMarca.getIdSignoMarca());
                        listaSigApoderado = sigSolicitanteApoderadoService.listadoApoderadoXidsignomarca(sigSignoMarca.getIdSignoMarca());
                        listaSigSignoClaseNiza = sigSignoClaseNizaService.obtenerListaSigSignoClazeNizaXIdSignoMarca(sigSignoMarca.getIdSignoMarca());
                        //listar las observaciones si existen en la solicitud
                        listaObservacionTramite = observacionTramiteService.obtListadoObservacionPorTramite("SIG", sigSignoMarca.getIdSignoMarca());
                        sigLemaComercial = sigLemaComercialService.obtenerSigLemaComercial(sigSignoMarca.getIdSignoMarca());
                        accionImprimirSigProto();
                    }
                }
            }

            if (this.codigoFormulario.equals("PI102")) {

                //validar que el codigo sm ya se encuentra en la base de datos
                if (ingresoFormularioService.verificarExistenciaRegistroMarcaXSM(codigoSM)) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("El codigo SM ingresado ya existe, verificar los datos ingresados"));
                } else {
                    this.mapResultado = this.ingresoFormularioService.guardarFormularioPI102(getFechaIngresoSolicitudSession(), formularioPI102, plantillaVentanilla,
                            usuario.getIdusuario(), codigoSM,
                            oficina, this.observacion);
                    sigSignoMarca = sigSignoMarcaService.obtenerRegistroSigSignoMarcaXNumeroFormulario(numeroFormulario);
                    if (sigSignoMarca != null) {
                        resultado = comunService.obtenerNumeroGestionExtensionCodigoSM(sigSignoMarca.getSm());
                        listaSigSolicitante = sigSolicitanteApoderadoService.listadoSolicitanteXidsignomarca(sigSignoMarca.getIdSignoMarca());
                        listaSigApoderado = sigSolicitanteApoderadoService.listadoApoderadoXidsignomarca(sigSignoMarca.getIdSignoMarca());
                        listaSigSignoClaseNiza = sigSignoClaseNizaService.obtenerListaSigSignoClazeNizaXIdSignoMarca(sigSignoMarca.getIdSignoMarca());
                        //listar las observaciones si existen en la solicitud
                        listaObservacionTramite = observacionTramiteService.obtListadoObservacionPorTramite("SIG", sigSignoMarca.getIdSignoMarca());
                        sigLemaComercial = sigLemaComercialService.obtenerSigLemaComercial(sigSignoMarca.getIdSignoMarca());
                        accionImprimirSigProto();

                    }
                }

            }
            if (this.codigoFormulario.equals("PI103")) {

                if (ingresoFormularioService.verificarExistenciaRegistroModificacion(this.SiglaModificacion, this.numero, Long.valueOf(this.gestion))) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("El codigo de modificación ya existe, verificar los datos ingresados"));
                } else {

                    this.mapResultado = this.ingresoFormularioService.guardarFormularioPI103(getFechaIngresoSolicitudSession(), formularioPI103, plantillaVentanilla,
                            usuario.getIdusuario(), codigoSM,
                            oficina, this.observacion,
                            numero, gestion, SiglaModificacion);

                    modModificacion = modModificacionService.buscarModModificacionXnroFormulario(numeroFormulario);
                    if (modModificacion != null) {
                        listaModSolicitante = (ArrayList<ModSolicitanteApoderado>) modSolicitanteApoderadoService.listadoSolicitanteXidmodificacion(modModificacion.getIdmodificacion());
                        listaModApoderado = (ArrayList<ModSolicitanteApoderado>) modSolicitanteApoderadoService.listadoApoderadoXidmodificacion(modModificacion.getIdmodificacion());
                        listaObservacionTramite = observacionTramiteService.obtListadoObservacionPorTramite("MOD", modModificacion.getIdmodificacion());
                        accionImprimirModProto();
                    }
                }

            }
            if (this.codigoFormulario.equals("PI104")) {

                if (ingresoFormularioService.verificarExistenciaRegistroRenovacion(this.numero, Long.valueOf(this.gestion))) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("El codigo de renovación ya existe, verificar los datos ingresados"));

                } else {
                    this.mapResultado = this.ingresoFormularioService.guardarFormularioPI104(getFechaIngresoSolicitudSession(), formularioPI104, plantillaVentanilla,
                            usuario.getIdusuario(), codigoSM,
                            oficina, this.observacion,
                            numero, gestion);
                    renSolicitudRenovacion = renSolicitudRenovacionService.buscarRenSolicitudRenovacionXNroFormulario(Long.parseLong(numeroFormulario));
                    if (renSolicitudRenovacion != null) {
                        listaRenSolicitante = (ArrayList<RenSolicitanteApoderado>) renSolicitanteApoderadoService.buscaSolicitanteApoderadoPoridSolicitudyTipoPersona(renSolicitudRenovacion.getIdsolicitudrenovacion(), "SOLI");
                        listaRenApoderado = (ArrayList<RenSolicitanteApoderado>) renSolicitanteApoderadoService.buscaSolicitanteApoderadoPoridSolicitudyTipoPersona(renSolicitudRenovacion.getIdsolicitudrenovacion(), "APOD");
                        listaObservacionTramite = observacionTramiteService.obtListadoObservacionPorTramite("REN", renSolicitudRenovacion.getIdsolicitudrenovacion());
                        accionImprimirRenProto();
                    }
                }

            }

        } catch (Exception e) {
            Logger.getLogger(IngresoRecepcionDeDocumentosBean.class.getName()).log(Level.SEVERE, null, e);
        }

//        //si todo esta bien retornar al ingreso solicitud
//        return "ingresoSolicitud";
    }
    
     public void accionImprimirSigProto() throws JRException, IOException {

        imageSenapi = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/images/logoNuevo.jpg");
        imageEscudoBol = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/images/esc-Bolivia");

        SimpleDateFormat formateador1 = new SimpleDateFormat("dd-MM-yyyy");
        fechaIngreso = formateador1.format(sigSignoMarca.getFechaIngreso());
        SimpleDateFormat formateador2 = new SimpleDateFormat("hh:mm:ss");
        horaIngreso = formateador2.format(sigSignoMarca.getFechaIngreso());

        sm = "SM-" + String.format("%6s", resultado.get("numero")).replace(' ', '0') + resultado.get("extension") + "-" + resultado.get("gestion");
        
        String tipoSigno = "";
        
        for (Dominio item : listaTipoSignoPI100) {
            if (item.getCodigo().equals(sigSignoMarca.getTipoGenero())) {
                tipoSigno = "REGISTRO DE SIGNO DISTINTIVO";
                break;
            }
        }
        
        for (Dominio item : listaTipoSignoPI101) {
            if (item.getCodigo().equals(sigSignoMarca.getTipoGenero())) {
                tipoSigno = item.getNombre().toUpperCase();
                break;
            }
        }
        
        for (Dominio item : listaTipoSignoPI102) {
            if (item.getCodigo().equals(sigSignoMarca.getTipoGenero())) {
                tipoSigno = item.getNombre().toUpperCase();
                break;
            }
        }

        titulo = "FORMULARIO DE VERIFICACION DE INGRESO DE "+tipoSigno+" "+sm;

        int numListaSigSignoClaseNiza = listaSigSignoClaseNiza.size();
        if (numListaSigSignoClaseNiza > 0) {
            if (numListaSigSignoClaseNiza == 1) {
                clase = "" + listaSigSignoClaseNiza.get(0).getNumeroClaseNiza();
            } else {
                for (int i = 0; i < numListaSigSignoClaseNiza - 1; i++) {
                    clase += listaSigSignoClaseNiza.get(i).getNumeroClaseNiza() + "; ";
                }
                clase += listaSigSignoClaseNiza.get(numListaSigSignoClaseNiza - 1).getNumeroClaseNiza();
            }
        }
        
        if (this.codigoFormulario.equals("PI102")) {
            nombreMarca = sigLemaComercial.getSignoPadre();

            if (sigSignoMarca.getSigno() != null) {
                lemaComercial = sigSignoMarca.getSigno();
            } else {
                lemaComercial = "";
            }
        } else {
            nombreMarca = sigSignoMarca.getSigno();

            if (sigLemaComercial.getIdLemaComercial() != null) {
                lemaComercial = sigLemaComercial.getSignoPadre();
            } else {
                lemaComercial = "";
            }
        }

        int numListaSigSolicitante = listaSigSolicitante.size();
        if (numListaSigSolicitante > 0) {
            if (numListaSigSolicitante == 1) {
                solicitantes = devuelveNombreJuridicoONatural(listaSigSolicitante.get(0).getNombreRazonSocial(), listaSigSolicitante.get(0).getPrimerApellido(), listaSigSolicitante.get(0).getSegundoApellido());
            } else {
                for (int i = 0; i < numListaSigSolicitante - 1; i++) {
                    solicitantes += devuelveNombreJuridicoONatural(listaSigSolicitante.get(i).getNombreRazonSocial(), listaSigSolicitante.get(i).getPrimerApellido(), listaSigSolicitante.get(i).getSegundoApellido()) + "; ";
                }
                solicitantes += devuelveNombreJuridicoONatural(listaSigSolicitante.get(numListaSigSolicitante - 1).getNombreRazonSocial(), listaSigSolicitante.get(numListaSigSolicitante - 1).getPrimerApellido(), listaSigSolicitante.get(numListaSigSolicitante - 1).getSegundoApellido());
            }
        }

        int numListaSigApoderado = listaSigApoderado.size();
        if (numListaSigApoderado > 0) {
            if (numListaSigApoderado == 1) {
                apoderados = devuelveNombreJuridicoONatural(listaSigApoderado.get(0).getNombreRazonSocial(), listaSigApoderado.get(0).getPrimerApellido(), listaSigApoderado.get(0).getSegundoApellido());
            } else {
                for (int i = 0; i < numListaSigApoderado - 1; i++) {
                    apoderados += devuelveNombreJuridicoONatural(listaSigApoderado.get(i).getNombreRazonSocial(), listaSigApoderado.get(i).getPrimerApellido(), listaSigApoderado.get(i).getSegundoApellido()) + "; ";
                }
                apoderados += devuelveNombreJuridicoONatural(listaSigApoderado.get(numListaSigApoderado - 1).getNombreRazonSocial(), listaSigApoderado.get(numListaSigApoderado - 1).getPrimerApellido(), listaSigApoderado.get(numListaSigApoderado - 1).getSegundoApellido());
            }
        }

        parametros.put("imgSenapi", imageSenapi);
        parametros.put("imgBolivia", imageEscudoBol);
        parametros.put("numeroFormulario", numeroFormulario);
        parametros.put("fechaIngreso", fechaIngreso);
        parametros.put("horaIngreso", horaIngreso);
        parametros.put("titulo", titulo);
        parametros.put("sm", sm);
        parametros.put("nombreMarca", nombreMarca);
        parametros.put("clase", clase);
        parametros.put("lemaComercial", lemaComercial);
        parametros.put("solicitantes", solicitantes);
        parametros.put("apoderados", apoderados);

        String filename = sigSignoMarca.getSm() + ".pdf";
        String jasperPath = "/template/formulario/IngresoRecepcionDeDocumentos2.jasper";
        this.PDFSD(parametros, jasperPath, filename);

        clase = "";
        solicitantes = "";
        apoderados = "";
    }

    /**
     * *
     * Metodo para llenar el formulario 103 de modificaciones.
     *
     * Creado: Ruben Ramirez Fecha: 07/11/2016
     *
     * @throws net.sf.jasperreports.engine.JRException
     * @throws java.io.IOException
     */
    public void accionImprimirModProto() throws JRException, IOException {

        imageSenapi = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/images/logoNuevo.jpg");
        imageEscudoBol = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/images/esc-Bolivia");

        SimpleDateFormat formateador1 = new SimpleDateFormat("dd-MM-yyyy");
        fechaIngreso = formateador1.format(modModificacion.getFecha_ingreso());
        SimpleDateFormat formateador2 = new SimpleDateFormat("hh:mm:ss");
        horaIngreso = formateador2.format(modModificacion.getFecha_ingreso());

        sm = "" + modModificacion.getSigla() + "-" + String.format("%6s", modModificacion.getNumero()).replace(' ', '0') + "-" + modModificacion.getGestion();
        
        for (Dominio item : listaTipoModificacion) {
            if (item.getCodigo().equals(modModificacion.getTipo_modificacion())) {
                titulo = "FORMULARIO DE VERIFICACION DE INGRESO DE " + item.getNombre().toUpperCase() + " " +sm;
                break;
            }
        }

        nombreMarca = modModificacion.getSigno_registrado();

        clase = "" + modModificacion.getIdclase_registrado();

        lemaComercial = "";

        int numListaModSolicitante = listaModSolicitante.size();
        if (numListaModSolicitante > 0) {
            if (numListaModSolicitante == 1) {
                solicitantes = devuelveNombreJuridicoONatural(listaModSolicitante.get(0).getNombre_razonsocial(), listaModSolicitante.get(0).getPrimer_apellido(), listaModSolicitante.get(0).getSegundo_apellido());
            } else {
                for (int i = 0; i < numListaModSolicitante - 1; i++) {
                    solicitantes += devuelveNombreJuridicoONatural(listaModSolicitante.get(i).getNombre_razonsocial(), listaModSolicitante.get(i).getPrimer_apellido(), listaModSolicitante.get(i).getSegundo_apellido()) + "; ";
                }
                solicitantes += devuelveNombreJuridicoONatural(listaModSolicitante.get(numListaModSolicitante - 1).getNombre_razonsocial(), listaModSolicitante.get(numListaModSolicitante - 1).getPrimer_apellido(), listaModSolicitante.get(numListaModSolicitante - 1).getSegundo_apellido());
            }
        }

        int numListaModApoderado = listaModApoderado.size();
        if (numListaModApoderado > 0) {
            if (numListaModApoderado == 1) {
                apoderados = devuelveNombreJuridicoONatural(listaModApoderado.get(0).getNombre_razonsocial(), listaModApoderado.get(0).getPrimer_apellido(), listaModApoderado.get(0).getSegundo_apellido());
            } else {
                for (int i = 0; i < numListaModApoderado - 1; i++) {
                    apoderados += devuelveNombreJuridicoONatural(listaModApoderado.get(i).getNombre_razonsocial(), listaModApoderado.get(i).getPrimer_apellido(), listaModApoderado.get(i).getSegundo_apellido()) + "; ";
                }
                apoderados += devuelveNombreJuridicoONatural(listaModApoderado.get(numListaModApoderado - 1).getNombre_razonsocial(), listaModApoderado.get(numListaModApoderado - 1).getPrimer_apellido(), listaModApoderado.get(numListaModApoderado - 1).getSegundo_apellido());
            }
        }

        parametros.put("imgSenapi", imageSenapi);
        parametros.put("imgBolivia", imageEscudoBol);
        parametros.put("numeroFormulario", numeroFormulario);
        parametros.put("fechaIngreso", fechaIngreso);
        parametros.put("horaIngreso", horaIngreso);
        parametros.put("titulo", titulo);
        parametros.put("sm", sm);
        parametros.put("nombreMarca", nombreMarca);
        parametros.put("clase", clase);
        parametros.put("lemaComercial", lemaComercial);
        parametros.put("solicitantes", solicitantes);
        parametros.put("apoderados", apoderados);

        String filename = modModificacion.getSm() + ".pdf";
        String jasperPath = "/template/formulario/IngresoRecepcionDeDocumentos2.jasper";
        this.PDFSD(parametros, jasperPath, filename);

        solicitantes = "";
        apoderados = "";
    }

    /**
     * Metodo para llenar el formulario 104 de solicitud de renovaciones.
     *
     * Creado: Ruben Ramirez Fecha: 07/11/2016
     *
     * @throws net.sf.jasperreports.engine.JRException
     * @throws java.io.IOException
     */
    public void accionImprimirRenProto() throws JRException, IOException {

        imageSenapi = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/images/logoNuevo.jpg");
        imageEscudoBol = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/images/esc-Bolivia");

        SimpleDateFormat formateador1 = new SimpleDateFormat("dd-MM-yyyy");
        fechaIngreso = formateador1.format(renSolicitudRenovacion.getFecha_ingreso());
        SimpleDateFormat formateador2 = new SimpleDateFormat("hh:mm:ss");
        horaIngreso = formateador2.format(renSolicitudRenovacion.getFecha_ingreso());

        sm = "SR-" + String.format("%6s", renSolicitudRenovacion.getSr()).replace(' ', '0') + "-" + renSolicitudRenovacion.getGestion_sr();
        
        titulo = "FORMULARIO DE VERIFICACION DE INGRESO PARA REGISTRO DE RENOVACION "+ sm;

        nombreMarca = renSolicitudRenovacion.getSigno_registrado();
        
        clase = "" + renSolicitudRenovacion.getIdclase_niza_registrado();

        lemaComercial = "";
        
        int numListaRenSolicitante = listaRenSolicitante.size();
        if (numListaRenSolicitante > 0) {
            if (numListaRenSolicitante == 1) {
                solicitantes = devuelveNombreJuridicoONatural(listaRenSolicitante.get(0).getNombre_razonsocial(), listaRenSolicitante.get(0).getPrimer_apellido(), listaRenSolicitante.get(0).getSegundo_apellido());
            } else {
                for (int i = 0; i < numListaRenSolicitante - 1; i++) {
                    solicitantes += devuelveNombreJuridicoONatural(listaRenSolicitante.get(i).getNombre_razonsocial(), listaRenSolicitante.get(i).getPrimer_apellido(), listaRenSolicitante.get(i).getSegundo_apellido()) + "; ";
                }
                solicitantes += devuelveNombreJuridicoONatural(listaRenSolicitante.get(numListaRenSolicitante - 1).getNombre_razonsocial(), listaRenSolicitante.get(numListaRenSolicitante - 1).getPrimer_apellido(), listaRenSolicitante.get(numListaRenSolicitante - 1).getSegundo_apellido());
            }
        }

        int numListaRenApoderado = listaRenApoderado.size();
        if (numListaRenApoderado > 0) {
            if (numListaRenApoderado == 1) {
                apoderados = devuelveNombreJuridicoONatural(listaRenApoderado.get(0).getNombre_razonsocial(), listaRenApoderado.get(0).getPrimer_apellido(), listaRenApoderado.get(0).getSegundo_apellido());
            } else {
                for (int i = 0; i < numListaRenApoderado - 1; i++) {
                    apoderados += devuelveNombreJuridicoONatural(listaRenApoderado.get(i).getNombre_razonsocial(), listaRenApoderado.get(i).getPrimer_apellido(), listaRenApoderado.get(i).getSegundo_apellido()) + "; ";
                }
                apoderados += devuelveNombreJuridicoONatural(listaRenApoderado.get(numListaRenApoderado - 1).getNombre_razonsocial(), listaRenApoderado.get(numListaRenApoderado - 1).getPrimer_apellido(), listaRenApoderado.get(numListaRenApoderado - 1).getSegundo_apellido());
            }
        }

        parametros.put("imgSenapi", imageSenapi);
        parametros.put("imgBolivia", imageEscudoBol);
        parametros.put("numeroFormulario", numeroFormulario);
        parametros.put("fechaIngreso", fechaIngreso);
        parametros.put("horaIngreso", horaIngreso);
        parametros.put("titulo", titulo);
        parametros.put("sm", sm);
        parametros.put("nombreMarca", nombreMarca);
        parametros.put("clase", clase);
        parametros.put("lemaComercial", lemaComercial);
        parametros.put("solicitantes", solicitantes);
        parametros.put("apoderados", apoderados);

        String filename = renSolicitudRenovacion.getNumero_formulario()+ ".pdf";
        String jasperPath = "/template/formulario/IngresoRecepcionDeDocumentos2.jasper";
        this.PDFSD(parametros, jasperPath, filename);

        solicitantes = "";
        apoderados = "";
    }

    //<editor-fold defaultstate="collapsed" desc="Getter y setters del Bean">

    public List<OpoSolicitanteapoderado> getListaOpoSolicitante() {
        return listaOpoSolicitante;
    }

    public void setListaOpoSolicitante(List<OpoSolicitanteapoderado> listaOpoSolicitante) {
        this.listaOpoSolicitante = listaOpoSolicitante;
    }

    public List<OpoSolicitanteapoderado> getListaOpoApoderado() {
        return listaOpoApoderado;
    }

    public void setListaOpoApoderado(List<OpoSolicitanteapoderado> listaOpoApoderado) {
        this.listaOpoApoderado = listaOpoApoderado;
    }

    public OpoMarcademandada getOpoMarcaDemandada() {
        return opoMarcaDemandada;
    }

    public void setOpoMarcaDemandada(OpoMarcademandada opoMarcaDemandada) {
        this.opoMarcaDemandada = opoMarcaDemandada;
    }

    public OpoMarcademandante getOpoMarcaDemandante() {
        return opoMarcaDemandante;
    }

    public void setOpoMarcaDemandante(OpoMarcademandante opoMarcaDemandante) {
        this.opoMarcaDemandante = opoMarcaDemandante;
    }

    public OpoSolicitanteaopderadoService getOpoSolicitanteApoderadoService() {
        return opoSolicitanteApoderadoService;
    }

    public void setOpoSolicitanteApoderadoService(OpoSolicitanteaopderadoService opoSolicitanteApoderadoService) {
        this.opoSolicitanteApoderadoService = opoSolicitanteApoderadoService;
    }

    public OpoMarcademandanteService getOpoMarcaDemandanteService() {
        return opoMarcaDemandanteService;
    }

    public void setOpoMarcaDemandanteService(OpoMarcademandanteService opoMarcaDemandanteService) {
        this.opoMarcaDemandanteService = opoMarcaDemandanteService;
    }

    public OpoMarcademandadaService getOpoMarcaDemandadaService() {
        return opoMarcaDemandadaService;
    }

    public void setOpoMarcaDemandadaService(OpoMarcademandadaService opoMarcaDemandadaService) {
        this.opoMarcaDemandadaService = opoMarcaDemandadaService;
    }

    public Oposicion getOposicion() {
        return oposicion;
    }

    public void setOposicion(Oposicion oposicion) {
        this.oposicion = oposicion;
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

    public List<ElementoFormularioTramite> getPlantillaVentanilla() {
        return plantillaVentanilla;
    }

    public void setPlantillaVentanilla(List<ElementoFormularioTramite> plantillaVentanilla) {
        this.plantillaVentanilla = plantillaVentanilla;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Long getCodigoSM() {
        return codigoSM;
    }

    public void setCodigoSM(Long codigoSM) {
        this.codigoSM = codigoSM;
    }

    public FormularioPI100 getFormularioPI100() {
        return formularioPI100;
    }

    public void setFormularioPI100(FormularioPI100 formularioPI100) {
        this.formularioPI100 = formularioPI100;
    }

    public FormularioPI101 getFormularioPI101() {
        return formularioPI101;
    }

    public void setFormularioPI101(FormularioPI101 formularioPI101) {
        this.formularioPI101 = formularioPI101;
    }

    public FormularioPI102 getFormularioPI102() {
        return formularioPI102;
    }

    public void setFormularioPI102(FormularioPI102 formularioPI102) {
        this.formularioPI102 = formularioPI102;
    }

    public FormularioPI103 getFormularioPI103() {
        return formularioPI103;
    }

    public void setFormularioPI103(FormularioPI103 formularioPI103) {
        this.formularioPI103 = formularioPI103;
    }

    public FormularioPI104 getFormularioPI104() {
        return formularioPI104;
    }

    public void setFormularioPI104(FormularioPI104 formularioPI104) {
        this.formularioPI104 = formularioPI104;
    }

    public String getCodigoFormulario() {
        return codigoFormulario;
    }

    public void setCodigoFormulario(String codigoFormulario) {
        this.codigoFormulario = codigoFormulario;
    }

    public String getOficina() {
        return oficina;
    }

    public void setOficina(String oficina) {
        this.oficina = oficina;
    }

    public HashMap getMapResultado() {
        return mapResultado;
    }

    public void setMapResultado(HashMap mapResultado) {
        this.mapResultado = mapResultado;
    }

    //servicios
    public DatoElementoFormularioService getDatoElementoFormularioService() {
        return datoElementoFormularioService;
    }

    public void setDatoElementoFormularioService(DatoElementoFormularioService datoElementoFormularioService) {
        this.datoElementoFormularioService = datoElementoFormularioService;
    }

    public SeguimientoService getSeguimientoService() {
        return seguimientoService;
    }

    public void setSeguimientoService(SeguimientoService seguimientoService) {
        this.seguimientoService = seguimientoService;
    }

    public Seguimiento getSigSeguimiento() {
        return sigSeguimiento;
    }

    public void setSigSeguimiento(Seguimiento sigSeguimiento) {
        this.sigSeguimiento = sigSeguimiento;
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

    public FormularioPI103Service getFormularioPI103Service() {
        return formularioPI103Service;
    }

    public void setFormularioPI103Service(FormularioPI103Service formularioPI103Service) {
        this.formularioPI103Service = formularioPI103Service;
    }

    public FormularioPI104Service getFormularioPI104Service() {
        return formularioPI104Service;
    }

    public void setFormularioPI104Service(FormularioPI104Service formularioPI104Service) {
        this.formularioPI104Service = formularioPI104Service;
    }

    public IngresoFormularioService getIngresoFormularioService() {
        return ingresoFormularioService;
    }

    public void setIngresoFormularioService(IngresoFormularioService ingresoFormularioService) {
        this.ingresoFormularioService = ingresoFormularioService;
    }

    public ElementoFormularioTramiteService getElementoFormularioTramiteService() {
        return elementoFormularioTramiteService;
    }

    public void setElementoFormularioTramiteService(ElementoFormularioTramiteService elementoFormularioTramiteService) {
        this.elementoFormularioTramiteService = elementoFormularioTramiteService;
    }

    public ObservacionTramiteService getObservacionTramiteService() {
        return observacionTramiteService;
    }

    public void setObservacionTramiteService(ObservacionTramiteService observacionTramiteService) {
        this.observacionTramiteService = observacionTramiteService;
    }

    public List<SigSignoMarca> getListaSigSignoMarca() {
        return listaSigSignoMarca;
    }

    public void setListaSigSignoMarca(List<SigSignoMarca> listaSigSignoMarca) {
        this.listaSigSignoMarca = listaSigSignoMarca;
    }

    public List<SigSignoClaseNiza> getListaSigSignoClaseNiza() {
        return listaSigSignoClaseNiza;
    }

    public void setListaSigSignoClaseNiza(List<SigSignoClaseNiza> listaSigSignoClaseNiza) {
        this.listaSigSignoClaseNiza = listaSigSignoClaseNiza;
    }

    public SigSignoMarca getSigSignoMarca() {
        return sigSignoMarca;
    }

    public void setSigSignoMarca(SigSignoMarca sigSignoMarca) {
        this.sigSignoMarca = sigSignoMarca;
    }

    public SigLemaComercial getSigLemaComercial() {
        return sigLemaComercial;
    }

    public void setSigLemaComercial(SigLemaComercial sigLemaComercial) {
        this.sigLemaComercial = sigLemaComercial;
    }

    public Map<String, Object> getParametros() {
        return parametros;
    }

    public void setParametros(Map<String, Object> parametros) {
        this.parametros = parametros;
    }

    public HashMap getResultado() {
        return resultado;
    }

    public void setResultado(HashMap resultado) {
        this.resultado = resultado;
    }

    public String getNombreMarca() {
        return nombreMarca;
    }

    public void setNombreMarca(String nombreMarca) {
        this.nombreMarca = nombreMarca;
    }

    public String getLemaComercial() {
        return lemaComercial;
    }

    public void setLemaComercial(String lemaComercial) {
        this.lemaComercial = lemaComercial;
    }

    public String getNumeroFormulario() {
        return numeroFormulario;
    }

    public void setNumeroFormulario(String numeroFormulario) {
        this.numeroFormulario = numeroFormulario;
    }

    public String getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(String fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getHoraIngreso() {
        return horaIngreso;
    }

    public void setHoraIngreso(String horaIngreso) {
        this.horaIngreso = horaIngreso;
    }

    public String getSm() {
        return sm;
    }

    public void setSm(String sm) {
        this.sm = sm;
    }

    public String getClase() {
        return clase;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }

    public String getSolicitantes() {
        return solicitantes;
    }

    public void setSolicitantes(String solicitantes) {
        this.solicitantes = solicitantes;
    }

    public String getImageSenapi() {
        return imageSenapi;
    }

    public void setImageSenapi(String imageSenapi) {
        this.imageSenapi = imageSenapi;
    }

    public String getImageEscudoBol() {
        return imageEscudoBol;
    }

    public void setImageEscudoBol(String imageEscudoBol) {
        this.imageEscudoBol = imageEscudoBol;
    }

    public SigSignoMarcaService getSigSignoMarcaService() {
        return sigSignoMarcaService;
    }

    public void setSigSignoMarcaService(SigSignoMarcaService sigSignoMarcaService) {
        this.sigSignoMarcaService = sigSignoMarcaService;
    }

    public SigLemaComercialService getSigLemaComercialService() {
        return sigLemaComercialService;
    }

    public void setSigLemaComercialService(SigLemaComercialService sigLemaComercialService) {
        this.sigLemaComercialService = sigLemaComercialService;
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

    public SigSolicitanteApoderadoService getSigSolicitanteApoderadoService() {
        return sigSolicitanteApoderadoService;
    }

    public void setSigSolicitanteApoderadoService(SigSolicitanteApoderadoService sigSolicitanteApoderadoService) {
        this.sigSolicitanteApoderadoService = sigSolicitanteApoderadoService;
    }

    public List<ObservacionTramite> getListaObservacionTramite() {
        return listaObservacionTramite;
    }

    public void setListaObservacionTramite(List<ObservacionTramite> listaObservacionTramite) {
        this.listaObservacionTramite = listaObservacionTramite;
    }

    public String getObservacion2() {
        return observacion2;
    }

    public void setObservacion2(String observacion2) {
        this.observacion2 = observacion2;
    }

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public Integer getGestion() {
        return gestion;
    }

    public void setGestion(Integer gestion) {
        this.gestion = gestion;
    }

    public String getSiglaModificacion() {
        return SiglaModificacion;
    }

    public void setSiglaModificacion(String SiglaModificacion) {
        this.SiglaModificacion = SiglaModificacion;
    }

    public List<SigSolicitanteApoderado> getListaSigSolicitante() {
        return listaSigSolicitante;
    }

    public void setListaSigSolicitante(List<SigSolicitanteApoderado> listaSigSolicitante) {
        this.listaSigSolicitante = listaSigSolicitante;
    }

    public List<SigSolicitanteApoderado> getListaSigApoderado() {
        return listaSigApoderado;
    }

    public void setListaSigApoderado(List<SigSolicitanteApoderado> listaSigApoderado) {
        this.listaSigApoderado = listaSigApoderado;
    }

    public List<SeccionReporte> getListaSeccionReporte() {
        return listaSeccionReporte;
    }

    public void setListaSeccionReporte(List<SeccionReporte> listaSeccionReporte) {
        this.listaSeccionReporte = listaSeccionReporte;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getApoderados() {
        return apoderados;
    }

    public void setApoderados(String apoderados) {
        this.apoderados = apoderados;
    }

    public ArrayList<ModSolicitanteApoderado> getListaModSolicitante() {
        return listaModSolicitante;
    }

    public void setListaModSolicitante(ArrayList<ModSolicitanteApoderado> listaModSolicitante) {
        this.listaModSolicitante = listaModSolicitante;
    }

    public ArrayList<ModSolicitanteApoderado> getListaModApoderado() {
        return listaModApoderado;
    }

    public void setListaModApoderado(ArrayList<ModSolicitanteApoderado> listaModApoderado) {
        this.listaModApoderado = listaModApoderado;
    }

    public ModModificacion getModModificacion() {
        return modModificacion;
    }

    public void setModModificacion(ModModificacion modModificacion) {
        this.modModificacion = modModificacion;
    }

    public ModModificacionService getModModificacionService() {
        return modModificacionService;
    }

    public void setModModificacionService(ModModificacionService modModificacionService) {
        this.modModificacionService = modModificacionService;
    }

    public ModSolicitanteApoderadoService getModSolicitanteApoderadoService() {
        return modSolicitanteApoderadoService;
    }

    public void setModSolicitanteApoderadoService(ModSolicitanteApoderadoService modSolicitanteApoderadoService) {
        this.modSolicitanteApoderadoService = modSolicitanteApoderadoService;
    }

    public ArrayList<RenSolicitanteApoderado> getListaRenSolicitante() {
        return listaRenSolicitante;
    }

    public void setListaRenSolicitante(ArrayList<RenSolicitanteApoderado> listaRenSolicitante) {
        this.listaRenSolicitante = listaRenSolicitante;
    }

    public ArrayList<RenSolicitanteApoderado> getListaRenApoderado() {
        return listaRenApoderado;
    }

    public void setListaRenApoderado(ArrayList<RenSolicitanteApoderado> listaRenApoderado) {
        this.listaRenApoderado = listaRenApoderado;
    }

    public RenSolicitudRenovacion getRenSolicitudRenovacion() {
        return renSolicitudRenovacion;
    }

    public void setRenSolicitudRenovacion(RenSolicitudRenovacion renSolicitudRenovacion) {
        this.renSolicitudRenovacion = renSolicitudRenovacion;
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

    public List<Dominio> getListaTipoModificacion() {
        return listaTipoModificacion;
    }

    public void setListaTipoModificacion(List<Dominio> listaTipoModificacion) {
        this.listaTipoModificacion = listaTipoModificacion;
    }

    public DominioService getDominioService() {
        return dominioService;
    }

    public void setDominioService(DominioService dominioService) {
        this.dominioService = dominioService;
    }
    
    public List<Dominio> getListaTipoSignoPI100() {
        return listaTipoSignoPI100;
    }

    public void setListaTipoSignoPI100(List<Dominio> listaTipoSignoPI100) {
        this.listaTipoSignoPI100 = listaTipoSignoPI100;
    }

    public List<Dominio> getListaTipoSignoPI101() {
        return listaTipoSignoPI101;
    }

    public void setListaTipoSignoPI101(List<Dominio> listaTipoSignoPI101) {
        this.listaTipoSignoPI101 = listaTipoSignoPI101;
    }

    public List<Dominio> getListaTipoSignoPI102() {
        return listaTipoSignoPI102;
    }

    public void setListaTipoSignoPI102(List<Dominio> listaTipoSignoPI102) {
        this.listaTipoSignoPI102 = listaTipoSignoPI102;
    }

    public FormularioPI105Service getFormularioPI105Service() {
        return formularioPI105Service;
    }

    public void setFormularioPI105Service(FormularioPI105Service formularioPI105Service) {
        this.formularioPI105Service = formularioPI105Service;
    }

    public FormularioPI105 getFormularioPI105() {
        return formularioPI105;
    }

    public void setFormularioPI105(FormularioPI105 formularioPI105) {
        this.formularioPI105 = formularioPI105;
    }

    //</editor-fold>

    public Integer getNumeroPublicacion() {
        return numeroPublicacion;
    }

    public void setNumeroPublicacion(Integer numeroPublicacion) {
        this.numeroPublicacion = numeroPublicacion;
    }

    public Integer getGaceta() {
        return gaceta;
    }

    public void setGaceta(Integer gaceta) {
        this.gaceta = gaceta;
    }

    public Integer getNroorden() {
        return nroorden;
    }

    public void setNroorden(Integer nroorden) {
        this.nroorden = nroorden;
    }

    public Boolean getHabilitabnt() {
        return habilitabnt;
    }

    public void setHabilitabnt(Boolean habilitabnt) {
        this.habilitabnt = habilitabnt;
    }

    public ClaseNizaService getClaseNizaService() {
        return claseNizaService;
    }

    public void setClaseNizaService(ClaseNizaService claseNizaService) {
        this.claseNizaService = claseNizaService;
    }
}
