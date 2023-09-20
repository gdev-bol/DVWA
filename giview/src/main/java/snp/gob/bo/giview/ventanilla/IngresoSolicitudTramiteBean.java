/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.giview.ventanilla;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import net.sourceforge.barbecue.linear.twoOfFive.Int2of5Barcode;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import snp.gob.bo.entidades.bean.common.AbstractManagedBean;
import snp.gob.bo.entidades.bean.login.persona;
import snp.gob.bo.gimodel.entidad.ClaseNiza;
import snp.gob.bo.gimodel.entidad.Dominio;
import snp.gob.bo.gimodel.entidad.ElementoFormularioTramite;
import snp.gob.bo.gimodel.entidad.Regional;
import snp.gob.bo.gimodel.entidad.Usuario;
import snp.gob.bo.gimodel.enums.EnumCarpetasTemporales;
import snp.gob.bo.gimodel.enums.EnumNombreDominio;
import snp.gob.bo.gimodel.enums.EnumTipoFormulario;
import snp.gob.bo.gimodel.servicio.ClaseNizaService;
import snp.gob.bo.gimodel.servicio.ComunService;
import snp.gob.bo.gimodel.servicio.DominioService;
import snp.gob.bo.gimodel.servicio.RegionalService;
import snp.gob.bo.gimodel.servicio.UsuarioService;
import snp.gob.bo.gimodel.solicitudes.entidades.FormularioPI100;
import snp.gob.bo.gimodel.solicitudes.entidades.FormularioPI101;
import snp.gob.bo.gimodel.solicitudes.entidades.FormularioPI102;
import snp.gob.bo.gimodel.solicitudes.entidades.FormularioPI103;
import snp.gob.bo.gimodel.solicitudes.entidades.FormularioPI104;
import snp.gob.bo.gimodel.solicitudes.entidades.FormularioPI105;
import snp.gob.bo.gimodel.solicitudes.entidades.Logotipos;
import snp.gob.bo.gimodel.solicitudes.entidades.SmTipoSignos;
import snp.gob.bo.gimodel.solicitudes.servicio.FormularioPI100Service;
import snp.gob.bo.gimodel.solicitudes.servicio.FormularioPI101Service;
import snp.gob.bo.gimodel.solicitudes.servicio.FormularioPI102Service;
import snp.gob.bo.gimodel.solicitudes.servicio.FormularioPI103Service;
import snp.gob.bo.gimodel.solicitudes.servicio.FormularioPI104Service;
import snp.gob.bo.gimodel.solicitudes.servicio.FormularioPI105Service;
import snp.gob.bo.gimodel.solicitudes.servicio.IngresoFormularioService;

/**
 *
 * @author eddy
 */
@ManagedBean(name = "ingresoSolicitudTramiteBean")
@ViewScoped
public class IngresoSolicitudTramiteBean extends AbstractManagedBean implements Serializable {

    //private static final Logger log = Logger.getLogger(RecepcionDeDocumentosBean.class);
//    private static final Logger logger = LogManager.getLogger(IngresoSolicitudTramiteBean.class);
    //<editor-fold defaultstate="collapsed" desc="Definición de atributos">
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

    @ManagedProperty(value = "#{dominioService}")
    private DominioService dominioService;

    @ManagedProperty(value = "#{comunService}")
    private ComunService comunService;

    @ManagedProperty(value = "#{claseNizaService}")
    private ClaseNizaService claseNizaService;

    @ManagedProperty(value = "#{regionalService}")
    private RegionalService regionalService;

    @ManagedProperty(value = "#{usuarioService}")
    private UsuarioService usuarioService;

    private HashMap mapResultado = new HashMap();
    private String numeroFormulario;

    //formularios de solicitud origen
    FormularioPI100 formularioPI100 = new FormularioPI100();
    FormularioPI101 formularioPI101 = new FormularioPI101();
    FormularioPI102 formularioPI102 = new FormularioPI102();
    FormularioPI103 formularioPI103 = new FormularioPI103();
    FormularioPI104 formularioPI104 = new FormularioPI104();
    FormularioPI105 formularioPI105 = new FormularioPI105();

    private List<Dominio> listaOficina = new ArrayList<Dominio>();
    private List<String> images;
    private List<persona> lstPersonas;
    private String oficina;
    private Boolean panelPI100;
    private Boolean panelPI101;
    private Boolean panelPI102;
    private Boolean panelPI103;
    private Boolean panelPI104;
    private Boolean panelPI105;
    private Boolean panelNoEncontrado;
    private Boolean panelEncontrado;
    private Date fechaSolicitud = new Date();
    //variable obtener el tipo de tramite
    private String tipoTramite;
    //variable para la fecha de ingreso del tramite
    private Date fechaIngreso = new Date();
    private Usuario usuario;
    private Long codigoSM = 0L;
    private Long codigoSM2 = 0L;

    //variables para armar el codigo sm
    private Long numero;
    private Long gestion;

    private List<ElementoFormularioTramite> plantillaVentanilla = new ArrayList<ElementoFormularioTramite>();
    private String idFormularioExterno;

    //variables tipoGenero
    private String valorTipoGenero = "";
    private List<Dominio> lstTipoGenero = new ArrayList<Dominio>();

    //variables para mostrar en caso que sea el tiposigno otros
    private Boolean panelOtro = Boolean.FALSE;
    private String textoOtroPI100 = "";

    //variables para el tipo de modificacion que se debe guardar
    private String codigoModificacion = "";
    private String codigoModificacionIngresa = "";

    //variables para la clase niza de renovaciones
    private Long idClaseNiza;
    private List<ClaseNiza> lstClaseNiza;
    private Long valorClaseNiza;
    private Long valornumeroRegistroRegistrado;
    private String valorSerieRegistroRegistrado;
    private Date fechaOtorgacionMarca;
    private Integer valorNumeroUltimaRenovacion;
    private Integer valorNumeroPenultimaRenovacion;
    private String valorListaProductosLimitacion;
    private String numeroClaseRenovacionReclasificacion;
    private String numeroClaseRenovacion;
    private Integer valorClaseNizaReclasificacion;
    private String labelSigla = "SM";
    private String labelSiglaopo = "OP";
    private String labelSiglaRenovacion = "SR";
    private String numeroClaseModificacion = "";

    //</editor-fold>
    @PostConstruct
    public void init() {

        images = new ArrayList<String>();

        try {
            fechaIngreso = comunService.obtenerFechaHoraServidor(1l);
            usuario = usuarioService.obtenerUsuario(getIdUsuarioSession());

            if (usuario.getIdusuario() != null) {
                //obtener la regional
                Regional regional = regionalService.obtenerRegionalPorIdRegiona(usuario.getIdregional());
                //this.oficina = regional.getTipoCiudadNotificacion();
            } else {
                //this.oficina = "LPZ";
            }
//            this.cambiarRegionalSM();

            //cargar los datos de la Oficina
            listaOficina = dominioService.obtenerListadoDominio("oficina");
            lstTipoGenero = dominioService.obtenerListadoDominio("tipo_genero");
            lstClaseNiza = claseNizaService.obtenerListadoClaseNiza();

            //inicializar los paneles
            this.panelPI100 = Boolean.FALSE;
            this.panelPI101 = Boolean.FALSE;
            this.panelPI102 = Boolean.FALSE;
            this.panelPI103 = Boolean.FALSE;
            this.panelPI104 = Boolean.FALSE;
            this.panelPI105 = Boolean.FALSE;

        } catch (Exception ex) {
            Logger.getLogger(IngresoSolicitudTramiteBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     *
     * Metodo que obtiene el listado que se envia del popup de requisitos
     *
     * Creado: Eddy Valero Fecha: 28/03/2017
     *
     */
    public void cambiarRegionalSM() {

        switch (oficina) {
            case "SCZ":
                labelSigla = "SM-S";
                break;
            case "CBA":
                labelSigla = "SM-C";
                break;
            case "ALT":
                labelSigla = "SM-E";
                break;
            case "TJA":
                labelSigla = "SM-T";
                break;
            case "CHQ":
                labelSigla = "SM-H";
                break;
            case "ORU":
                labelSigla = "SM-O";
                break;
            default:
                labelSigla = "SM";
                break;

        }

    }

    public void cambiarRegionalSR() {

        switch (oficina) {
            case "SCZ":
                labelSiglaRenovacion = "SR-S";
                break;
            case "CBA":
                labelSiglaRenovacion = "SR-C";
                break;
            case "ALT":
                labelSiglaRenovacion = "SR-E";
                break;
            case "TJA":
                labelSiglaRenovacion = "SR-T";
                break;
            case "CHQ":
                labelSiglaRenovacion = "SR-H";
                break;
            case "ORU":
                labelSiglaRenovacion = "SR-O";
                break;
            default:
                labelSiglaRenovacion = "SR";
                break;
        }

    }

    public void cambiarRegionalOP() {

        switch (oficina) {
            case "SCZ":
                labelSiglaopo = "OP-S";
                break;
            case "CBA":
                labelSiglaopo = "OP-C";
                break;
            case "ALT":
                labelSiglaopo = "OP-E";
                break;
            case "TJA":
                labelSiglaopo = "OP-T";
                break;
            case "CHQ":
                labelSiglaopo = "OP-H";
                break;
            case "ORU":
                labelSiglaopo = "OP-O";
                break;

            default:
                labelSiglaopo = "OP";
                break;
        }

    }

    /**
     * *
     * Metodo que obtiene el listado que se envia del popup de requisitos
     * Creado: Eddy Valero Fecha: 06/09/2016
     */
    public void obtenerListadoPlantilla(SelectEvent event) {
        if (event.getObject() != "Exit") {
            plantillaVentanilla = (List<ElementoFormularioTramite>) event.getObject();
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cerrado"));
        }
    }

    /**
     * *
     * Metodo que realiza la busque de un formulario de acuerdo a su tramite
     * Creado: Eddy Valero Fecha: 06/09/2016
     *
     *
     */
    public void busquedaFormularioTipoTramite() {
        this.plantillaVentanilla = new ArrayList<ElementoFormularioTramite>();
        if (!numeroFormulario.equalsIgnoreCase("")) {
            //obtener el formulario y su tipo de tramite
            HashMap mapResultado = new HashMap();

            //eliminar el listado de imagenes
            formularioPI100 = new FormularioPI100();
            formularioPI101 = new FormularioPI101();
            formularioPI102 = new FormularioPI102();
            formularioPI103 = new FormularioPI103();
            formularioPI104 = new FormularioPI104();
            formularioPI105 = new FormularioPI105();

            try {
                fechaIngreso = comunService.obtenerFechaHoraServidor(1l);
                DateFormat df = new SimpleDateFormat("yyyy");
                this.setGestion(Long.valueOf(df.format(fechaIngreso)));
                this.setNumero(null);
                mapResultado = this.formularioPI100Service.obtenerNumeroFormulario(numeroFormulario);

                if (mapResultado.get("continuarFlujo").equals("true")) {
                    if (mapResultado.get("idFormulario").equals("0")) {
                        //si es vacio
                        this.panelEncontrado = Boolean.TRUE;
                        this.panelNoEncontrado = Boolean.FALSE;
                        this.panelPI100 = Boolean.FALSE;
                        this.panelPI101 = Boolean.FALSE;
                        this.panelPI102 = Boolean.FALSE;
                        this.panelPI103 = Boolean.FALSE;
                        this.panelPI104 = Boolean.FALSE;
                        this.panelPI105 = Boolean.FALSE;
                        //guardar el tipo de tramite del formulario
                        this.tipoTramite = mapResultado.get("tipoTramite").toString();

                    } else {
                        if (mapResultado.get("mensaje").equals("")) {

                            this.idFormularioExterno = mapResultado.get("idFormulario").toString();

                            //obtener el o los formularios dependiendo
                            if (mapResultado.get("tipoFormulario").equals(EnumTipoFormulario.REGISTRO_SIGNO_DISTINTIVO.getCodigo())) {
                                this.panelPI100 = Boolean.TRUE;
                                this.panelPI101 = Boolean.FALSE;
                                this.panelPI102 = Boolean.FALSE;
                                this.panelPI103 = Boolean.FALSE;
                                this.panelPI104 = Boolean.FALSE;
                                this.panelPI105 = Boolean.FALSE;
                                this.panelEncontrado = Boolean.FALSE;
                                this.panelNoEncontrado = Boolean.FALSE;

                                //obtener el formularioPI100
                                generarFormularioPI100(mapResultado.get("idFormulario").toString());

                            }
                            if (mapResultado.get("tipoFormulario").equals(EnumTipoFormulario.REGISTRO_NOMBRE_COMERCIAL.getCodigo())) {
                                this.panelPI100 = Boolean.FALSE;
                                this.panelPI101 = Boolean.TRUE;
                                this.panelPI102 = Boolean.FALSE;
                                this.panelPI103 = Boolean.FALSE;
                                this.panelPI104 = Boolean.FALSE;
                                this.panelPI105 = Boolean.FALSE;
                                this.panelEncontrado = Boolean.FALSE;
                                this.panelNoEncontrado = Boolean.FALSE;
                                //obtener el formularioPI101
                                generarFormularioPI101(mapResultado.get("idFormulario").toString());

                            }

                            if (mapResultado.get("tipoFormulario").equals(EnumTipoFormulario.REGISTRO_LEMA_COMERCIAL.getCodigo())) {
                                this.panelPI100 = Boolean.FALSE;
                                this.panelPI101 = Boolean.FALSE;
                                this.panelPI102 = Boolean.TRUE;
                                this.panelPI103 = Boolean.FALSE;
                                this.panelPI104 = Boolean.FALSE;
                                this.panelPI105 = Boolean.FALSE;
                                this.panelEncontrado = Boolean.FALSE;
                                this.panelNoEncontrado = Boolean.FALSE;
                                //obtener el formularioPI102
                                generarFormularioPI102(mapResultado.get("idFormulario").toString());
                            }

                            if (mapResultado.get("tipoFormulario").equals(EnumTipoFormulario.REGISTRO_MODIFICACION.getCodigo())) {
                                this.panelPI100 = Boolean.FALSE;
                                this.panelPI101 = Boolean.FALSE;
                                this.panelPI102 = Boolean.FALSE;
                                this.panelPI103 = Boolean.TRUE;
                                this.panelPI104 = Boolean.FALSE;
                                this.panelPI105 = Boolean.FALSE;
                                this.panelEncontrado = Boolean.FALSE;
                                this.panelNoEncontrado = Boolean.FALSE;
                                //obtener el formularioPI103
                                generarFormularioPI103(mapResultado.get("idFormulario").toString());
                            }

                            if (mapResultado.get("tipoFormulario").equals(EnumTipoFormulario.REGISTRO_RENOVACION.getCodigo())) {
                                this.panelPI100 = Boolean.FALSE;
                                this.panelPI101 = Boolean.FALSE;
                                this.panelPI102 = Boolean.FALSE;
                                this.panelPI103 = Boolean.FALSE;
                                this.panelPI104 = Boolean.TRUE;
                                this.panelPI105 = Boolean.FALSE;
                                this.panelEncontrado = Boolean.FALSE;
                                this.panelNoEncontrado = Boolean.FALSE;
                                //obtener el formularioPI104
                                generarFormularioPI104(mapResultado.get("idFormulario").toString());
                            }
                            if (mapResultado.get("tipoFormulario").equals(EnumTipoFormulario.REGISTRO_OPOSICION.getCodigo())) {
                                this.panelPI100 = Boolean.FALSE;
                                this.panelPI101 = Boolean.FALSE;
                                this.panelPI102 = Boolean.FALSE;
                                this.panelPI103 = Boolean.FALSE;
                                this.panelPI104 = Boolean.FALSE;
                                this.panelPI105 = Boolean.TRUE;
                                this.panelEncontrado = Boolean.FALSE;
                                this.panelNoEncontrado = Boolean.FALSE;
                                //obtener el formularioPI105
                                generarFormularioPI105(mapResultado.get("idFormulario").toString());
                            }

                        } else {
                            //si es vacio
                            this.panelPI100 = Boolean.FALSE;
                            this.panelPI101 = Boolean.FALSE;
                            this.panelPI102 = Boolean.FALSE;
                            this.panelPI103 = Boolean.FALSE;
                            this.panelPI104 = Boolean.FALSE;
                            this.panelPI105 = Boolean.FALSE;
                            this.panelEncontrado = Boolean.FALSE;
                            this.panelNoEncontrado = Boolean.TRUE;
                        }

                    }

                } else {
                    //si es vacio
                    this.panelPI100 = Boolean.FALSE;
                    this.panelPI101 = Boolean.FALSE;
                    this.panelPI102 = Boolean.FALSE;
                    this.panelPI103 = Boolean.FALSE;
                    this.panelPI104 = Boolean.FALSE;
                    this.panelPI105 = Boolean.FALSE;
                    this.panelEncontrado = Boolean.FALSE;
                    this.panelNoEncontrado = Boolean.TRUE;

                }

            } catch (Exception ex) {
                Logger.getLogger(IngresoSolicitudTramiteBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            this.panelPI100 = Boolean.FALSE;
            this.panelPI101 = Boolean.FALSE;
            this.panelPI102 = Boolean.FALSE;
            this.panelPI103 = Boolean.FALSE;
            this.panelPI104 = Boolean.FALSE;
            this.panelPI105 = Boolean.FALSE;
            this.panelEncontrado = Boolean.FALSE;
            this.panelNoEncontrado = Boolean.TRUE;
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
            this.oficina = formularioPI100.getDirecciones().get(0).getCiudadNotificacion();
            cambiarRegionalSM();
            this.fechaIngreso = comunService.obtenerFechaHoraServidor(1L);

            //cargar el tipo signo otro
            for (SmTipoSignos smTipoSignos : formularioPI100.getSmTipoSignos()) {
                if (smTipoSignos.getTipoSigno().equals("OTRO")) {
                    this.panelOtro = Boolean.TRUE;
                    this.textoOtroPI100 = smTipoSignos.getDescripcionOtro();

                }
            }

            cargarArchivoWAR(formularioPI100.getLogotipos());
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
            this.oficina = formularioPI101.getDirecciones().get(0).getCiudadNotificacion();
            cambiarRegionalSM();

            cargarArchivoWAR(formularioPI101.getLogotipos());

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
            this.oficina = formularioPI102.getDirecciones().get(0).getCiudadNotificacion();
            cambiarRegionalSM();

        } catch (Exception ex) {
            Logger.getLogger(IngresoSolicitudTramiteBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    
  
    
    
    
    
    
    
    /**
     * Método que permite generar el FormularioPI103
     *
     * creado: Eddy Valero Fecha: 24/10/2016
     *
     * @param idFormulario
     */
    public void generarFormularioPI103(String idFormulario) {

        try {
            formularioPI103 = this.formularioPI103Service.obtenerDatosFormularioPI103(Long.parseLong(idFormulario));
            
            if (formularioPI103.getModificaciones().getClaseRegistrado() != null) {
            
                numeroClaseModificacion = claseNizaService.listarClaseNiza_id(formularioPI103.getModificaciones().getClaseRegistrado().longValue()).getNumeroClaseNiza().toString();
            }
            
            this.oficina = formularioPI103.getDirecciones().get(0).getCiudadNotificacion();
            this.valorTipoGenero = formularioPI103.getModificaciones().getTipoGeneroRegistrado();
//            if (formularioPI103.getModificaciones().getTipoModifica().equals("CANO")) {
//                codigoModificacion = "CN";
//            }
//
//            if (formularioPI103.getModificaciones().getTipoModificacion().equals("CADO")) {
//                codigoModificacion = "CD";
//            }
//
//            if (formularioPI103.getModificaciones().getTipoModificacion().equals("CATR")) {
//                codigoModificacion = "ST";
//            }
//
//            if (formularioPI103.getModificaciones().getTipoModificacion().equals("CAFU")) {
//                codigoModificacion = "SF";
//            }
//
//            if (formularioPI103.getModificaciones().getTipoModificacion().equals("CALU")) {
//                codigoModificacion = "LU";
//            }
//            codigoModificacionIngresa = codigoModificacion;
            cambiarSiglaModificacion();
        } catch (Exception ex) {
            Logger.getLogger(IngresoSolicitudTramiteBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void cambiarSiglaModificacion() {

        if (formularioPI103.getModificaciones().getTipoModificacion().equals("CANO")) {
            codigoModificacion = "CN";
        }

        if (formularioPI103.getModificaciones().getTipoModificacion().equals("CADO")) {
            codigoModificacion = "CD";
        }

        if (formularioPI103.getModificaciones().getTipoModificacion().equals("CATR")) {
            codigoModificacion = "ST";
        }

        if (formularioPI103.getModificaciones().getTipoModificacion().equals("CAFU")) {
            codigoModificacion = "SF";
        }

        if (formularioPI103.getModificaciones().getTipoModificacion().equals("CALU")) {
            codigoModificacion = "LU";
        }
        codigoModificacionIngresa = codigoModificacion;
        switch (oficina) {
            case "ALT":
                codigoModificacionIngresa = codigoModificacionIngresa + "-E";
                break;
            case "CBA":
                codigoModificacionIngresa = codigoModificacionIngresa + "-C";
                break;
            case "SCZ":
                codigoModificacionIngresa = codigoModificacionIngresa + "-S";
                break;
            case "TJA":
                codigoModificacionIngresa = codigoModificacionIngresa + "-T";
                break;
            case "CHQ":
                codigoModificacionIngresa = codigoModificacionIngresa + "-H";
                break;
            case "ORU":
                codigoModificacionIngresa = codigoModificacionIngresa + "-O";
                break;
            case "LPZ":
                codigoModificacionIngresa = codigoModificacionIngresa + "";
                break;
            default:
                break;
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
            if (formularioPI104.getSolicitudRenovaciones().getClaseNizaRegistrado()!= null) {
                numeroClaseRenovacion = claseNizaService.listarClaseNiza_id(formularioPI104.getSolicitudRenovaciones().getClaseNizaRegistrado().longValue()).getNumeroClaseNiza().toString();
            }
            if (formularioPI104.getSolicitudRenovaciones().getClaseNizaReclasificacion()!= null) {
                numeroClaseRenovacionReclasificacion = claseNizaService.listarClaseNiza_id(formularioPI104.getSolicitudRenovaciones().getClaseNizaReclasificacion().longValue()).getNumeroClaseNiza().toString();
                valorClaseNizaReclasificacion=Integer.parseInt(numeroClaseRenovacionReclasificacion);
            }
            this.oficina = formularioPI104.getDirecciones().get(0).getCiudadNotificacion();
            this.valorTipoGenero = formularioPI104.getSolicitudRenovaciones().getTipoGenero();
            this.valorClaseNiza = Long.valueOf(formularioPI104.getSolicitudRenovaciones().getClaseNizaRegistrado());
            cambiarRegionalSR();
            this.valornumeroRegistroRegistrado = formularioPI104.getSolicitudRenovaciones().getNumeroRegistro();
            this.valorSerieRegistroRegistrado = formularioPI104.getSolicitudRenovaciones().getSerieRegistro();
            this.fechaOtorgacionMarca = formularioPI104.getSolicitudRenovaciones().getFechaOtorgacionMarca();
            this.valorNumeroUltimaRenovacion = formularioPI104.getSolicitudRenovaciones().getNumeroUltimaRenovacion();
            this.valorNumeroPenultimaRenovacion = formularioPI104.getSolicitudRenovaciones().getNumeroPenultimaRenovacion();
            this.valorListaProductosLimitacion = formularioPI104.getSolicitudRenovaciones().getListaProductosLimitacion();
            this.valorClaseNizaReclasificacion = formularioPI104.getSolicitudRenovaciones().getClaseNizaReclasificacion();

        } catch (Exception ex) {
            Logger.getLogger(IngresoSolicitudTramiteBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Método que permite generar el FormularioPI105
     *
     * creado: Luis Angel Quispe: 13/07/2017
     *
     * @param idFormulario
     */
    public void generarFormularioPI105(String idFormulario) {

        try {
            formularioPI105 = this.formularioPI105Service.obtenerDatosFormularioPI105(Long.parseLong(idFormulario));
            this.oficina = formularioPI105.getDirecciones().get(0).getCiudadNotificacion();
            cambiarRegionalOP();
        } catch (Exception ex) {
            Logger.getLogger(IngresoSolicitudTramiteBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Definicion de metodos comunes">    
    /**
     *
     * Metodo para devolver el pais
     *
     * Creado: Eddy Valero Kari Fecha: 26/10/2016
     *
     * @param codigo
     * @return
     * @throws java.lang.Exception
     */
    public String devuelvePais(String codigo) throws Exception {
        if (codigo != null && !codigo.equals("")) {
            return dominioService.obtenerListadoDominioXCodigo(EnumNombreDominio.PAIS.getCodigo(), codigo).get(0).getNombre();
        }
        return "";
    }

    /**
     *
     * Metodo para devolver el lugar de expedicion
     *
     * Creado: Eddy Valero Kari Fecha: 26/10/2016
     *
     * @param codigo
     * @return
     * @throws java.lang.Exception
     */
    public String devuelveDepartamento(String codigo) throws Exception {
        if (codigo != null && !codigo.equals("")) {
            return dominioService.obtenerListadoDominioXCodigo(EnumNombreDominio.LUGAR_EXPEDICION.getCodigo(), codigo).get(0).getNombre();
        }
        return "";
    }

    /**
     *
     * Metodo que devuelve el tipo persona
     *
     * Creado: Eddy Valero Kari Fecha: 28/12/2016
     *
     * @param codigo
     * @return
     * @throws java.lang.Exception
     */
    public String devuelveTipoTitular(String codigo) throws Exception {
        if (codigo != null && !codigo.equals("")) {
            return dominioService.obtenerListadoDominioXCodigo(EnumNombreDominio.TIPO_TITULAR.getCodigo(), codigo).get(0).getNombre();
        }
        return "";
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
//        String imagenDibuja = rutaWar + File.separator + this.codigoSM;

            //enviar el imagenPojo a la vista, antes hacemos una previsualizacion
            crearArchivo(fotoByte, imagenDibuja);

        }

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

    //</editor-fold>
    /**
     * *
     * Metodo para ingresar el registro de acuerdo al tramite, se debe enviar el
     * codigoSM, para pintar la plantilla de ventanilla
     *
     * @return String
     */
    public String accionIngresarRegistro() {
        try {
            //validar el formulario ingreso
            if (validarIngresoFormulario()) {
                if (this.oficina.equals("")) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "La oficina no es válido", " por favor verifique los datos"));
                    return null;
                }
                // valida que la numero no sea cero o valor negativo
                if (this.numero < 1) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El número debe ser mayor a cero", " por favor verifique los datos"));
                    return null;
                }
                // valida que la gestion no sea cero o valor negativo
                if (this.gestion < 1) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "La gestión no es válido", " por favor verifique los datos"));
                    return null;
                }

                //codificar la cadena SM
                codigoSM = comunService.codificarCodigoSM(numero.toString(), gestion.toString(), "");
                /*Long numero2 = numero - 1l;

                 // si valor numero2 es cero, no codificar codigoSM2
                 if (numero2 > 0) {
                 codigoSM2 = comunService.codificarCodigoSM((numero2).toString(), gestion.toString(), "");
                 }*/

                HttpServletRequest mirequest = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
                mirequest.setAttribute("codigoSM", this.codigoSM);
                mirequest.setAttribute("oficina", this.oficina);
                mirequest.setAttribute("idFormularioExterno", this.idFormularioExterno);

                //variables para la modificacion: numero gestion y codigoModificacion
                mirequest.setAttribute("numero", this.numero);
                mirequest.setAttribute("gestion", this.gestion);
                mirequest.setAttribute("codigoModificacion", this.codigoModificacionIngresa);

                if (this.panelPI100.equals(Boolean.TRUE)) {
                    mirequest.setAttribute("codigoSolicitud", "REGS");
                    mirequest.setAttribute("codigoFormulario", "PI100");

                    //validar que el sm no se encuentre en la base de datos
                    if (ingresoFormularioService.verificarExistenciaRegistroMarcaXSM(codigoSM)) {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El codigo SM ingresado ya existe", " verificar los datos ingresados"));
                        return null;
                    }

                    /*if (numero2 > 0) {
                     //validar que el sm2 se encuentre en la base de datos
                     if (!ingresoFormularioService.verificarExistenciaRegistroMarcaXSM(codigoSM2)) {
                     FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El Número no es correlativo válido para la oficina: " + dominioService.obtenerNombreCodigoDominio("oficina", this.oficina), " verificar los datos ingresados"));
                     return null;
                     }
                     }*/
                } else if (this.panelPI101.equals(Boolean.TRUE)) {
                    mirequest.setAttribute("codigoSolicitud", "REGS");
                    mirequest.setAttribute("codigoFormulario", "PI101");

                    //validar que el sm no se encuentre en la base de datos
                    if (ingresoFormularioService.verificarExistenciaRegistroMarcaXSM(codigoSM)) {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El codigo SM ingresado ya existe", " verificar los datos ingresados"));
                        return null;
                    }
                    /*if (numero2 > 0) {
                     //validar que el sm2 se encuentre en la base de datos
                     if (!ingresoFormularioService.verificarExistenciaRegistroMarcaXSM(codigoSM2)) {
                     FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El Número no es correlativo válido para la oficina: " + dominioService.obtenerNombreCodigoDominio("oficina", this.oficina), " verificar los datos ingresados"));
                     return null;
                     }
                     }*/
                } else if (this.panelPI102.equals(Boolean.TRUE)) {
                    mirequest.setAttribute("codigoSolicitud", "REGS");
                    mirequest.setAttribute("codigoFormulario", "PI102");

                    //validar que el sm no se encuentre en la base de datos
                    if (ingresoFormularioService.verificarExistenciaRegistroMarcaXSM(codigoSM)) {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El codigo SM ingresado ya existe", " verificar los datos ingresados"));
                        return null;
                    }
                    /*if (numero2 > 0) {
                     //validar que el sm2 se encuentre en la base de datos
                     if (!ingresoFormularioService.verificarExistenciaRegistroMarcaXSM(codigoSM2)) {
                     FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El Número no es correlativo válido para la oficina: " + dominioService.obtenerNombreCodigoDominio("oficina", this.oficina), " verificar los datos ingresados"));
                     return null;
                     }
                     }*/
                } else if (this.panelPI103.equals(Boolean.TRUE)) {
                    mirequest.setAttribute("codigoSolicitud", "MODI");
                    mirequest.setAttribute("codigoFormulario", "PI103");

                    //validar que la modificacion no se encuentre en la base de datos
                    if (ingresoFormularioService.verificarExistenciaRegistroModificacion(this.codigoModificacionIngresa, this.numero, this.gestion)) {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El codigo de modificación ya existe", " verificar los datos ingresados"));
                        return null;
                    }
                    /*if (numero2 > 0) {
                     //validar que la modificacion se encuentre en la base de datos
                     if (!ingresoFormularioService.verificarExistenciaRegistroModificacion(this.codigoModificacionIngresa, numero2, this.gestion)) {
                     FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El código modificación no es correlativo válido para la oficina: " + dominioService.obtenerNombreCodigoDominio("oficina", this.oficina), " verificar los datos ingresados"));
                     return null;
                     }
                     }*/

                } else if (this.panelPI104.equals(Boolean.TRUE)) {
                    mirequest.setAttribute("codigoSolicitud", "RENO");
                    mirequest.setAttribute("codigoFormulario", "PI104");

                    //validar que el sr no se encuentre en la base de datos
                    if (ingresoFormularioService.verificarExistenciaRegistroRenovacion(this.numero, this.gestion)) {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El codigo de renovación ya existe", " verificar los datos ingresados"));
                        return null;
                    }
                } else if (this.panelPI105.equals(Boolean.TRUE)) {
                    mirequest.setAttribute("codigoSolicitud", "REOP");
                    mirequest.setAttribute("codigoFormulario", "PI105");
                    if (ingresoFormularioService.verificarExistenciaRegistroOposicion(this.numero, this.gestion)) {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El codigo oposición ya existe", " verificar los datos ingresados"));
                        return null;
                    }
                }

                //cargar variables de session de oficina y FechaIngreso
                setFechaIngresoSolicitudSession(fechaIngreso);

                return "ingresoRecepcionDeDocumentos";
            }

            //desplegar mensaje de error
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Validación de formulario", "ingresar el numero y la gestión."));
            return null;

        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(IngresoSolicitudTramiteBean.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        return null;
    }

    public Boolean validarIngresoFormulario() {

        //si la validacion es correcta retornar verdadero
        if ((this.numero != null)
                && (this.gestion != null)) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }

    }

    public String accionAbrirPlantillaTramiteIngresado() {

        setNumeroFormularioSolicitudSession(numeroFormulario);
        setTipoTramiteSolicitudSession(tipoTramite);

        return "plantillaTramiteIngresado";

    }

//    public String abrirSeguimiento() {
//        this.setNavegaPagina("abrirExpediente");
//        return ("abrirSeguimiento");
//    }
//    public String abrirHistorial() {
//        this.setNavegaPagina("abrirExpediente");
//        return ("abrirPlantillaHistorial");
//    }
//    public String abrirObservacionTramite() {
//        this.setNavegaPagina("ingresoSolicitud");
//        return ("abrirObservacionTramite");
//    }
//    public String abrirFinalizar() {
//        return ("abrirPrincipal");
//    }
//    public String abrirPoderDepositado() {
//        return ("abrirLibroPoderes");
//    }
    public void accionPanelGenerarPlantillaVentanilla() {
        Map<String, Object> options = new HashMap<String, Object>();
        options.put("resizable", true);
        //options.put("draggable", true);
        options.put("modal", true);
        options.put("height", 650);
        options.put("width", "90%");
        options.put("contentHeight", "100%");
        options.put("contentWidth", "100%");
        options.put("closable", false);

        //parametros a enviarse al dialogo
        Map<String, List<String>> var = new HashMap<String, List<String>>();
        List<String> params = new ArrayList();
//        params.add("1");
//        params.add("signo");

        //enviar el tipo de tramite para el cual debe pintarse la plantilla
        if (this.panelPI100.equals(Boolean.TRUE)
                || this.panelPI101.equals(Boolean.TRUE)
                || this.panelPI102.equals(Boolean.TRUE)) {
            params.add("SM");
        }
        if (this.panelPI103.equals(Boolean.TRUE)) {
            params.add("MOD");
        }

        if (this.panelPI103.equals(Boolean.TRUE)) {
            params.add("REN");
        }

        var.put("datosGenerales", params);

        RequestContext.getCurrentInstance().openDialog("preplantillaingresoSolicitud", options, var);

    }

    public String devuelveLugarExpedicion(String codigo) throws Exception {
        try {
            if (codigo != null && !codigo.equals("")) {
                return dominioService.obtenerListadoDominioXCodigo(EnumNombreDominio.LUGAR_EXPEDICION.getCodigo(), codigo).get(0).getNombre();
            }
            return "";
        } catch (Exception e) {
            return "";
        }
    }

    public String devuelveTipoSigno(String codigo) throws Exception {
        try {
            if (codigo != null && !codigo.equals("")) {
                return dominioService.obtenerListadoDominioXCodigo(EnumNombreDominio.TIPO_SIGNO.getCodigo(), codigo).get(0).getNombre();
            }
            return "";
        } catch (Exception e) {
            return "";
        }
    }

    public String devuelveTipoGenero(String codigo) throws Exception {
        try {
            if (codigo != null && !codigo.equals("")) {
                return dominioService.obtenerListadoDominioXCodigo(EnumNombreDominio.TIPO_GENERO.getCodigo(), codigo).get(0).getNombre();
            }
            return "";
        } catch (Exception e) {
            return "";
        }
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

    //<editor-fold defaultstate="collapsed" desc="Getters y setters atributos">
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

    public DominioService getDominioService() {
        return dominioService;
    }

    public void setDominioService(DominioService dominioService) {
        this.dominioService = dominioService;
    }

    public ComunService getComunService() {
        return comunService;
    }

    public void setComunService(ComunService comunService) {
        this.comunService = comunService;
    }

    public ClaseNizaService getClaseNizaService() {
        return claseNizaService;
    }

    public void setClaseNizaService(ClaseNizaService claseNizaService) {
        this.claseNizaService = claseNizaService;
    }

    public UsuarioService getUsuarioService() {
        return usuarioService;
    }

    public void setUsuarioService(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    public RegionalService getRegionalService() {
        return regionalService;
    }

    public void setRegionalService(RegionalService regionalService) {
        this.regionalService = regionalService;
    }

    public HashMap getMapResultado() {
        return mapResultado;
    }

    public void setMapResultado(HashMap mapResultado) {
        this.mapResultado = mapResultado;
    }

    public String getNumeroFormulario() {
        return numeroFormulario;
    }

    public void setNumeroFormulario(String numeroFormulario) {
        this.numeroFormulario = numeroFormulario;
    }

    public Boolean getPanelPI100() {
        return panelPI100;
    }

    public void setPanelPI100(Boolean panelPI100) {
        this.panelPI100 = panelPI100;

    }

    public Boolean getPanelNoEncontrado() {
        return panelNoEncontrado;
    }

    public void setPanelNoEncontrado(Boolean panelNoEncontrado) {
        this.panelNoEncontrado = panelNoEncontrado;
    }

    public Boolean getPanelEncontrado() {
        return panelEncontrado;
    }

    public void setPanelEncontrado(Boolean panelEncontrado) {
        this.panelEncontrado = panelEncontrado;
    }

    public FormularioPI100Service getFormularioPI100Service() {
        return formularioPI100Service;
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

    public List<persona> getLstPersonas() {
        return lstPersonas;
    }

    public void setLstPersonas(List<persona> lstPersonas) {
        this.lstPersonas = lstPersonas;
    }

    public String getOficina() {
        return oficina;
    }

    public void setOficina(String oficina) {
        this.oficina = oficina;
    }

    public Boolean getPanelPI101() {
        return panelPI101;
    }

    public void setPanelPI101(Boolean panelPI101) {
        this.panelPI101 = panelPI101;
    }

    public Boolean getPanelPI102() {
        return panelPI102;
    }

    public void setPanelPI102(Boolean panelPI102) {
        this.panelPI102 = panelPI102;
    }

    public Boolean getPanelPI103() {
        return panelPI103;
    }

    public void setPanelPI103(Boolean panelPI103) {
        this.panelPI103 = panelPI103;
    }

    public Boolean getPanelPI104() {
        return panelPI104;
    }

    public void setPanelPI104(Boolean panelPI104) {
        this.panelPI104 = panelPI104;
    }

    public String getTipoTramite() {
        return tipoTramite;
    }

    public void setTipoTramite(String tipoTramite) {
        this.tipoTramite = tipoTramite;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
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

    public Long getCodigoSM2() {
        return codigoSM2;
    }

    public void setCodigoSM2(Long codigoSM2) {
        this.codigoSM2 = codigoSM2;
    }

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public Long getGestion() {
        return gestion;
    }

    public void setGestion(Long gestion) {
        this.gestion = gestion;
    }

    public List<Dominio> getListaOficina() {
        return listaOficina;
    }

    public void setListaOficina(List<Dominio> listaOficina) {
        this.listaOficina = listaOficina;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public Date getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(Date fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public List<ElementoFormularioTramite> getPlantillaVentanilla() {
        return plantillaVentanilla;
    }

    public void setPlantillaVentanilla(List<ElementoFormularioTramite> plantillaVentanilla) {
        this.plantillaVentanilla = plantillaVentanilla;
    }

    public String getIdFormularioExterno() {
        return idFormularioExterno;
    }

    public void setIdFormularioExterno(String idFormularioExterno) {
        this.idFormularioExterno = idFormularioExterno;
    }

    public String getValorTipoGenero() {
        return valorTipoGenero;

    }

    public void setValorTipoGenero(String valorTipoGenero) {
        this.valorTipoGenero = valorTipoGenero;
    }

    public List<Dominio> getLstTipoGenero() {
        return lstTipoGenero;
    }

    public void setLstTipoGenero(List<Dominio> lstTipoGenero) {
        this.lstTipoGenero = lstTipoGenero;
    }

    public Boolean getPanelOtro() {
        return panelOtro;
    }

    public void setPanelOtro(Boolean panelOtro) {
        this.panelOtro = panelOtro;
    }

    public String getTextoOtroPI100() {
        return textoOtroPI100;
    }

    public void setTextoOtroPI100(String textoOtroPI100) {
        this.textoOtroPI100 = textoOtroPI100;
    }

    public String getCodigoModificacion() {
        return codigoModificacion;
    }

    public void setCodigoModificacion(String codigoModificacion) {
        this.codigoModificacion = codigoModificacion;
    }

    public String getCodigoModificacionIngresa() {
        return codigoModificacionIngresa;
    }

    public void setCodigoModificacionIngresa(String codigoModificacionIngresa) {
        this.codigoModificacionIngresa = codigoModificacionIngresa;
    }

    public Long getIdClaseNiza() {
        return idClaseNiza;
    }

    public void setIdClaseNiza(Long idClaseNiza) {
        this.idClaseNiza = idClaseNiza;
    }

    public List<ClaseNiza> getLstClaseNiza() {
        return lstClaseNiza;
    }

    public void setLstClaseNiza(List<ClaseNiza> lstClaseNiza) {
        this.lstClaseNiza = lstClaseNiza;
    }

    public Long getValorClaseNiza() {
        return valorClaseNiza;
    }

    public void setValorClaseNiza(Long valorClaseNiza) {
        this.valorClaseNiza = valorClaseNiza;
    }

    public Long getValornumeroRegistroRegistrado() {
        return valornumeroRegistroRegistrado;
    }

    public void setValornumeroRegistroRegistrado(Long valornumeroRegistroRegistrado) {
        this.valornumeroRegistroRegistrado = valornumeroRegistroRegistrado;
    }

    public String getValorSerieRegistroRegistrado() {
        return valorSerieRegistroRegistrado;
    }

    public void setValorSerieRegistroRegistrado(String valorSerieRegistroRegistrado) {
        this.valorSerieRegistroRegistrado = valorSerieRegistroRegistrado;
    }

    public Date getFechaOtorgacionMarca() {
        return fechaOtorgacionMarca;
    }

    public void setFechaOtorgacionMarca(Date fechaOtorgacionMarca) {
        this.fechaOtorgacionMarca = fechaOtorgacionMarca;
    }

    public Integer getValorNumeroUltimaRenovacion() {
        return valorNumeroUltimaRenovacion;
    }

    public void setValorNumeroUltimaRenovacion(Integer valorNumeroUltimaRenovacion) {
        this.valorNumeroUltimaRenovacion = valorNumeroUltimaRenovacion;
    }

    public Integer getValorNumeroPenultimaRenovacion() {
        return valorNumeroPenultimaRenovacion;
    }

    public void setValorNumeroPenultimaRenovacion(Integer valorNumeroPenultimaRenovacion) {
        this.valorNumeroPenultimaRenovacion = valorNumeroPenultimaRenovacion;
    }

    public String getValorListaProductosLimitacion() {
        return valorListaProductosLimitacion;
    }

    public void setValorListaProductosLimitacion(String valorListaProductosLimitacion) {
        this.valorListaProductosLimitacion = valorListaProductosLimitacion;
    }

    public Integer getValorClaseNizaReclasificacion() {
        return valorClaseNizaReclasificacion;
    }

    public void setValorClaseNizaReclasificacion(Integer valorClaseNizaReclasificacion) {
        this.valorClaseNizaReclasificacion = valorClaseNizaReclasificacion;
    }

    //</editor-fold>
    public String getLabelSigla() {
        return labelSigla;
    }

    public void setLabelSigla(String labelSigla) {
        this.labelSigla = labelSigla;
    }

    public String getLabelSiglaRenovacion() {
        return labelSiglaRenovacion;
    }

    public void setLabelSiglaRenovacion(String labelSiglaRenovacion) {
        this.labelSiglaRenovacion = labelSiglaRenovacion;
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

    public Boolean getPanelPI105() {
        return panelPI105;
    }

    public void setPanelPI105(Boolean panelPI105) {
        this.panelPI105 = panelPI105;
    }

    public String getLabelSiglaopo() {
        return labelSiglaopo;
    }

    public void setLabelSiglaopo(String labelSiglaopo) {
        this.labelSiglaopo = labelSiglaopo;
    }

    public String getNumeroClaseRenovacionReclasificacion() {
        return numeroClaseRenovacionReclasificacion;
    }

    public void setNumeroClaseRenovacionReclasificacion(String numeroClaseRenovacionReclasificacion) {
        this.numeroClaseRenovacionReclasificacion = numeroClaseRenovacionReclasificacion;
    }

    public String getNumeroClaseRenovacion() {
        return numeroClaseRenovacion;
    }

    public void setNumeroClaseRenovacion(String numeroClaseRenovacion) {
        this.numeroClaseRenovacion = numeroClaseRenovacion;
    }

   public String getNumeroClaseModificacion() {
        return numeroClaseModificacion;
    }

    public void setNumeroClaseModificacion(String numeroClaseModificacion) {
        this.numeroClaseModificacion = numeroClaseModificacion;
    }


    
    
    
}
