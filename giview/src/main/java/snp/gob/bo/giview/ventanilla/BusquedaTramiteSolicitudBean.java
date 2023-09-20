package snp.gob.bo.giview.ventanilla;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.servlet.ServletContext;
//import org.gob.snp.entidades.DatosContacto;
//import org.gob.snp.entidades.Empresa;
//import org.gob.snp.entidades.Modificacion;
//import org.gob.snp.entidades.ObservacionTramite;
//import org.gob.snp.entidades.Persona;
//import org.gob.snp.entidades.PersonaEmpresaTramite;
//import org.gob.snp.entidades.Poder;
//import org.gob.snp.entidades.RenovacionTramite;
//import org.gob.snp.entidades.SMImagen;
//import org.gob.snp.entidades.SMLogotipo;
//import org.gob.snp.entidades.SMSignoClaseNiza;
//import org.gob.snp.entidades.SMSignoMarca;
//import org.gob.snp.entidades.SMTipoSignoSolicitado;
//import org.gob.snp.entidades.Tramite;
//import org.gob.snp.entidades.TramiteUsuario;
//import org.gob.snp.entidades.Usuario;
//import org.gob.snp.entidades.vistas.TipoGenero;
//import org.gob.snp.entidades.vistas.TipoModificacion;
//import org.gob.snp.entidades.vistas.TipoPais;
//import org.gob.snp.entidades.vistas.TipoSolicitudDeposito;
//import org.gob.snp.enums.EnumColorLabelCampo;
//import org.gob.snp.enums.EnumTipoContacto;
//import org.gob.snp.enums.EnumTipoGenero;
//import org.gob.snp.enums.EnumTipoModificacion;
//import org.gob.snp.enums.EnumTipoSigno;
//import org.gob.snp.enums.EnumTipoSolicitudSigno;
//import org.gob.snp.enums.EnumTipoTramite;
//import org.gob.snp.pojo.ArchivoNizaPojo;
//import org.gob.snp.pojo.NaturalJuridicoPojo;
//import org.gob.snp.pojo.NaturalPoderPojo;
//import org.gob.snp.servicios.ArchivoTxtNizaService;
//import org.gob.snp.servicios.ClaseNizaService;
//import org.gob.snp.servicios.ClasificadorService;
//import org.gob.snp.servicios.DatosContactoService;
//import org.gob.snp.servicios.EmpresaService;
//import org.gob.snp.servicios.ModificacionService;
//import org.gob.snp.servicios.ObservacionTramiteService;
//import org.gob.snp.servicios.PersonaEmpresaTramiteService;
//import org.gob.snp.servicios.PersonaService;
//import org.gob.snp.servicios.PoderService;
//import org.gob.snp.servicios.RegistroService;
//import org.gob.snp.servicios.RenovacionTramiteService;
//import org.gob.snp.servicios.SmImagenService;
//import org.gob.snp.servicios.SmLogotipoService;
//import org.gob.snp.servicios.SmSignoClaseNizaService;
//import org.gob.snp.servicios.SmSignoMarcaService;
//import org.gob.snp.servicios.SmTipoSignoSolicitadoService;
//import org.gob.snp.servicios.TramiteUsuarioService;
//import org.gob.snp.servicios.UsuarioService;
//import org.gob.snp.servicios.util.BusquedaService;
//import snp.gob.bo.entidades.bean.common.AbstractManagedBean;
//import snp.gob.bo.entidades.bean.lemaComercial.SolicitudLemaComercialBean;
//import snp.gob.bo.entidades.bean.modificacion.SolicitudGeneralModificacionBean;

/**
 * Bean de la vista busquedaTramiteSolicitud.xhtml
 *
 * @author Eddy Valero
 * @see busquedaTramiteSolicitud.xhtml
 * @version 1.0, 07/12/2015
 *
 */
@ManagedBean(name = "busquedaTramiteSolicitudBean")
@ViewScoped
public class BusquedaTramiteSolicitudBean implements Serializable {

//    private Tramite tramite;
//    private Usuario usuario;
//    private Persona persona;
//    private Empresa empresa;
//    private String nombreCompletoPersona;
//    private String nombreCompletoEmpresa;
//    private String mensajeObservacionTramite;
//
//    private String codigoDia = "";
//
//    //variable para validar que se haya ingresado o no un codigo dia
//    private Boolean cambiaColorCampoCodigoDia;
//    //variables para activar el panel solo si existe o no existe el tramite
//    private Boolean activaPanelesSiExisteTramite;
//    private Boolean activaPanelesNoExisteTramite;
//
//    private List<NaturalJuridicoPojo> listaNaturalJuridicoPojo = new ArrayList<NaturalJuridicoPojo>();
//    private NaturalJuridicoPojo naturalJuridicoPojo;
//    private List<DatosContacto> listaDatosDeContacto = new ArrayList<DatosContacto>();
//    private String colorLabel = EnumColorLabelCampo.COLOR_LABEL.getCodigo();
//    private List<NaturalPoderPojo> listaNaturalPoderPojo = new ArrayList<NaturalPoderPojo>();
//    private Persona personaApoderado = new Persona();
//    private NaturalPoderPojo naturalPoderPojo;
//    private List<DatosContacto> listaDatosDeContactoRepresentante = new ArrayList<DatosContacto>();
//    private String marca;
//    private SMSignoMarca smsignomarca;
//    private Boolean panelSignoLemaRendered = false;
//    private String generoTramite;
//    private Boolean generoDepositoRendered;
//    private String depositoLemaRotulo;
//    private Boolean denominativo;
//    private Boolean denominativoConGrafia;
//    private Boolean denominacionOrigen;
//    private Boolean figurativa;
//    private Boolean tridimensional;
//    private Boolean auditivo;
//    private Boolean otro;
//    private String tipoSignoOtros;
//    private Boolean campoCheckOtrosRendered;
//    private List<SMSignoClaseNiza> listaSignoClaseNizaAux = new ArrayList<SMSignoClaseNiza>();
//    private List<SMSignoClaseNiza> listaSignoClaseNiza = new ArrayList<SMSignoClaseNiza>();
//    private List<String> listaNumerosClaseNizaProductosServicios = new ArrayList<String>();
//    private List<ArchivoNizaPojo> listArchivoPojo = new ArrayList<ArchivoNizaPojo>();
//    private SMLogotipo smslogotipo;
//    private String nombreRutaImagen;
//    private Boolean campoPanelImagenRendered;
//    private List<SMImagen> listaImagenLogotipos = new ArrayList<SMImagen>();
//    private List<String> images;
//    private List<TipoGenero> listadoTipoGeneroTramite = new ArrayList<TipoGenero>();
//    private List<TipoSolicitudDeposito> listaDepositoLemaRotulo = new ArrayList<TipoSolicitudDeposito>();
//    private String lemaComercial = "";
//    private Boolean noEsMarcaSolicitadaRegistrada = Boolean.TRUE;
//    private String numeroFormularioSolicitud = "";
//    private String nombreMarcaLemaComercial = "";
//    private String codigoSMLemaComercial = "";
//    private String numeroRegistroLemaComercial = "";
//    private String serieLemaComercial = "";
//    private String fechaVigencia = "";
//    private Boolean panelLemaComercialRendered = false;
//    private Boolean panelModificacionRendered = false;
//    private Modificacion modificacion;
//    private TipoModificacion comboTipoModificacion;
//    private NaturalJuridicoPojo naturalJuridicoPojoM;
//    private List<DatosContacto> listaDatosDeContactoM = new ArrayList<DatosContacto>();
//    private List<NaturalJuridicoPojo> listaNaturalJuridicoPojoM = new ArrayList<NaturalJuridicoPojo>();
//    private List<NaturalJuridicoPojo> listaFusionParticipantes = new ArrayList<NaturalJuridicoPojo>();
//    private List<TipoGenero> listadoTipoGeneroTramiteModificacion = new ArrayList<TipoGenero>();
//    private Boolean activaModificacionNombre;
//    private Boolean activaModificacionNombreDataTable;
//    private Boolean activaFusion;
//    private Boolean activaLicenciasDeUso;
//    private Boolean activaModificacionNombreDatosContacto;
//    private Boolean activaModificacionNombreBotones;
//    private Boolean activaModificacionDireccion;
//    private List<TipoPais> cambioDireccionListadoPaisesClasificadorNacionalidad = new ArrayList<TipoPais>();
//    private Boolean panelLemaRendered = false;
//    private Boolean panelRenovacionRendered = false;
//    private Boolean checkTxtNiza;
//    private List<RenovacionTramite> listaRenovacionTramite = new ArrayList<RenovacionTramite>();
//    private RenovacionTramite renovacionTramiteGuaradarBase;
//    private String numeroInternacionalRenovacion;
//    private String generoRenovacion;
//    private String listaProductosTextAreaRenovacion;
//    private String numeroInternacionalReclasificacion;
//    private String nombreArchivoDos;
//    private RenovacionTramite renovacionTramite;
//    private Boolean campoHabilitaTxtNiza;
//    private List<TipoGenero> listadoTipoGeneroTramiteRenovacion = new ArrayList<TipoGenero>();
//
//    @ManagedProperty(value = "#{busquedaService}")
//    private BusquedaService busquedaService;
//    @ManagedProperty(value = "#{usuarioService}")
//    private UsuarioService usuarioService;
//    @ManagedProperty(value = "#{personaService}")
//    private PersonaService personaService;
//    @ManagedProperty(value = "#{empresaService}")
//    private EmpresaService empresaService;
//
//    @ManagedProperty(value = "#{personaEmpresaTramiteService}")
//    private PersonaEmpresaTramiteService personaEmpresaTramiteService;
//    @ManagedProperty(value = "#{datosContactoService}")
//    private DatosContactoService datosContactoService;
//    @ManagedProperty(value = "#{poderService}")
//    private PoderService poderService;
//    @ManagedProperty(value = "#{smSignoMarcaService}")
//    private SmSignoMarcaService smSignoMarcaService;
//    @ManagedProperty(value = "#{smTipoSignoSolicitadoService}")
//    private SmTipoSignoSolicitadoService smTipoSignoSolicitadoService;
//    @ManagedProperty(value = "#{smSignoClaseNizaService}")
//    private SmSignoClaseNizaService smSignoClaseNizaService;
//    @ManagedProperty(value = "#{claseNizaService}")
//    private ClaseNizaService claseNizaService;
//    @ManagedProperty(value = "#{archivoTxtNizaService}")
//    private ArchivoTxtNizaService archivoTxtNizaService;
//    @ManagedProperty(value = "#{smLogotipoService}")
//    private SmLogotipoService smLogotipoService;
//    @ManagedProperty(value = "#{smImagenService}")
//    private SmImagenService smImagenService;
//    @ManagedProperty(value = "#{clasificadorService}")
//    private ClasificadorService clasificadorService;
//    @ManagedProperty(value = "#{tramiteUsuarioService}")
//    private TramiteUsuarioService tramiteUsuarioService;
//    @ManagedProperty(value = "#{observacionTramiteService}")
//    private ObservacionTramiteService observacionTramiteService;
//    @ManagedProperty(value = "#{modificacionService}")
//    private ModificacionService modificacionService;
//    @ManagedProperty(value = "#{renovacionTramiteService}")
//    private RenovacionTramiteService renovacionTramiteService;
//
//    @ManagedProperty(value = "#{registroService}")
//    private RegistroService registroService;
//
//    /**
//     * Método PostConstructor del Bean
//     *
//     *
//     * <pre>
//     * ----------------------------------------------------
//     * Creado: Eddy Valero Fecha: 07/12/2015
//     * Descripcion: De acuerdo a la variable enviada desde la configuracion de la
//     * Página en la BD, se pinta de acuerdo a su parametro.
//     * </pre>
//     */
//    @PostConstruct
//    public void initBusquedaTramiteSolicitudBean() {
//        cambiaColorCampoCodigoDia = false;
//        usuario = usuarioService.find(Usuario.class, getIdUsuarioLoginSession());
//
//        //limpiar las variables de session
//        resetSessionVariables();
//
//    }
//
//    public void buscarTramitePorCodigoDia() {
//        try {
//            tramite = busquedaService.obtenerTramitePorCodigoDia(codigoDia, getIdUsuarioLoginSession());
//            if (tramite.getTramite() != null) {
//                mensajeObservacionTramite = "Solicitud subsanación trámite";
//            } else {
//                mensajeObservacionTramite = "Solicitud ingreso trámite";
//            }
//
//            persona = personaService.encuentraPersonaTramite(tramite);
//            empresa = empresaService.encuentraEmpresaTramite(tramite);
//            if (persona != null) {
//                nombreCompletoPersona = persona.getNombres() + " " + persona.getApellidoPaterno() + " " + persona.getApellidoMaterno();
//            }
//            if (empresa != null) {
//                nombreCompletoEmpresa = empresa.getNombreRepresentante();
//            }
//
//            if (tramite.getIdTramite() != null) {
//                resetColumnas();
//                activaPanelesSiExisteTramite = true;
//                activaPanelesNoExisteTramite = false;
//
//                recuperaValoresSolicitante(tramite);
//                recuperaValoresApoderado(tramite);
//                smsignomarca = smSignoMarcaService.registroSmsignoMarcaXTramite(tramite);
//                if (tramite.getTipoTramite().getCodigo().equals(EnumTipoTramite.REGISTRO_LEMA_COMERCIAL.getCodigo())) {
//                    inicializaValoresLemaComercial(tramite);
//                    panelSignoLemaRendered = false;
//                    panelLemaComercialRendered = true;
//                    panelModificacionRendered = false;
//                    panelRenovacionRendered = false;
//
//                } else if (tramite.getTipoTramite().getCodigo().equals(EnumTipoTramite.REGISTRO_EN_EL_AREA_DE_SIGNOS_DISTINTIVOS.getCodigo())
//                        || tramite.getTipoTramite().getCodigo().equals(EnumTipoTramite.REGISTRO_DEPOSITO_NOMBRE_COMERCIAL_ROTULOS_ENSENAS.getCodigo())) {
//                    if (smsignomarca.getTramite() != null) {
//                        iniDatosBD(tramite);
//                    }
//                    panelSignoLemaRendered = true;
//                    panelLemaComercialRendered = false;
//                    panelModificacionRendered = false;
//                    panelRenovacionRendered = false;
//                } else if (tramite.getTipoTramite().getCodigo().equals(EnumTipoTramite.MODIFICACION.getCodigo())) {
//                    inicializaDatosModificacion(tramite);
//                    panelModificacionRendered = true;
//                    panelSignoLemaRendered = false;
//                    panelLemaComercialRendered = false;
//                    panelRenovacionRendered = false;
//                } else {
//                    panelRenovacionRendered = true;
//                    panelModificacionRendered = false;
//                    panelSignoLemaRendered = false;
//                    panelLemaComercialRendered = false;
//                    inicializaValoresRenovacion(tramite);
//                }
//            } else {
//                activaPanelesSiExisteTramite = false;
//                activaPanelesNoExisteTramite = true;
//            }
//
//        } catch (Exception ex) {
//            Logger.getLogger(BusquedaTramiteSolicitudBean.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//    }
//
//    public void reset() {
//        activaPanelesSiExisteTramite = false;
//        activaPanelesNoExisteTramite = false;
//        codigoDia = "";
//        mensajeObservacionTramite="";
//        limpiaColor();
//        resetColumnas();
//    }
//
//    public String verInformacionTramite() throws Exception {
//        if (tramite.getIdTramite() != null) {
//            setIdTramiteSession(tramite.getIdTramite());
//            setIdUsuarioLoginSession(usuario.getIdUsuario());
//            return "verInformacionTramite";
//        }
//        return null;
//    }
//
//    public void recuperaValoresSolicitante(Tramite tramite) {
//        for (PersonaEmpresaTramite personaEmpresaTramite : personaEmpresaTramiteService.obtieneListaPersonaEmpresaPorTramiteSOLI(tramite)) {
//            naturalJuridicoPojo = new NaturalJuridicoPojo();
//            if (!personaEmpresaTramite.getTipoPersonaSolicitud().getCodigo().equals("APOD")) {
//                if (personaEmpresaTramite.getEmpresa() != null) {
//                    naturalJuridicoPojo.setEmpresaPojo((Empresa) empresaService.find(Empresa.class, personaEmpresaTramite.getEmpresa().getIdEmpresa()));
//                    listaDatosDeContacto = datosContactoService.listaDatosDeContactoPorEmpresa((Empresa) empresaService.find(Empresa.class, personaEmpresaTramite.getEmpresa().getIdEmpresa()));
//                } else {
//                    naturalJuridicoPojo.setPersonaPojo((Persona) personaService.find(Persona.class, personaEmpresaTramite.getPersona().getIdPersona()));
//                    listaDatosDeContacto = datosContactoService.listaDatosDeContactoPorPersona((Persona) personaService.find(Persona.class, personaEmpresaTramite.getPersona().getIdPersona()));
//                }
//                naturalJuridicoPojo.setListDatoContactoPojo(listaDatosDeContacto);
//                listaNaturalJuridicoPojo.add(naturalJuridicoPojo);
//                listaDatosDeContacto = new ArrayList<DatosContacto>();
//
//            }
//        }
//        naturalJuridicoPojo = new NaturalJuridicoPojo();
//    }
//
//    public String obtieneNumeroDeSolicitante(NaturalJuridicoPojo naturalJuridicoPojo) {
//        String numero;
//        if (naturalJuridicoPojo.getEmpresaPojo().getNombreRepresentante() != null) {
//            numero = naturalJuridicoPojo.getEmpresaPojo().getNumeroDocumento();
//        } else {
//
//            numero = naturalJuridicoPojo.getPersonaPojo().getNumeroDocumento();
//        }
//        return numero;
//    }
//
//    public String obtieneNombreDeSolicitante(NaturalJuridicoPojo naturalJuridicoPojo) {
//        String nombre;
//        if (naturalJuridicoPojo.getEmpresaPojo().getNombreRepresentante() != null) {
//            nombre = naturalJuridicoPojo.getEmpresaPojo().getNombreRepresentante();
//        } else {
//            nombre = naturalJuridicoPojo.getPersonaPojo().getNombres() + ' ' + naturalJuridicoPojo.getPersonaPojo().getApellidoPaterno() + ' ' + naturalJuridicoPojo.getPersonaPojo().getApellidoMaterno();
//        }
//        return nombre;
//    }
//
//    public String obtieneTipoSolicitante(NaturalJuridicoPojo naturalJuridicoPojo) {
//        String tipoSol;
//        if (naturalJuridicoPojo.getEmpresaPojo().getNombreRepresentante() != null) {
//            tipoSol = "Juridico";
//        } else {
//            tipoSol = "Natural";
//        }
//        return tipoSol;
//    }
//
//    public String obtieneNacionalidadSolicitante(NaturalJuridicoPojo naturalJuridicoPojo) {
//        String nacionalidad;
//        if (naturalJuridicoPojo.getEmpresaPojo().getNombreRepresentante() != null) {
//            nacionalidad = naturalJuridicoPojo.getEmpresaPojo().getTipoPais().getDescripcion();
//        } else {
//            nacionalidad = naturalJuridicoPojo.getPersonaPojo().getTipoPais().getDescripcion();
//        }
//        return nacionalidad;
//    }
//
//    public String obtieneDireccionSolicitanteM(NaturalJuridicoPojo naturalJuridicoPojoM) {
//        String direccionVistaM = null;
//        for (DatosContacto datosContactoM : naturalJuridicoPojoM.getListDatoContactoPojo()) {
//            if (datosContactoM.getTipoContacto().getCodigo().equals(EnumTipoContacto.DIRECCION.getCodigo())) {
//                direccionVistaM = datosContactoM.getDato();
//            }
//        }
//        return direccionVistaM;
//    }
//
//    public void limpiaColor() {
//
//        cambiaColorCampoCodigoDia = Boolean.FALSE;
//    }
//
//    public String generarPlantilla() throws Exception {
//        setIdTramiteSession(tramite.getIdTramite());
//        return "recepcionDeDocumentos";
//    }
//
//    public String obtieneNombreDeApoderado(NaturalPoderPojo naturalPoderPojo) {
//        String nombre;
//        nombre = naturalPoderPojo.getPersonaPojo().getNombres() + ' ' + naturalPoderPojo.getPersonaPojo().getApellidoPaterno() + ' ' + naturalPoderPojo.getPersonaPojo().getApellidoMaterno();
//        return nombre;
//    }
//
//    public void recuperaValoresApoderado(Tramite tramite) {
//        personaApoderado = new Persona();
//        if (!personaEmpresaTramiteService.obtieneListaApoderadoPersonaPorTramite(tramite).isEmpty()) {
//            for (PersonaEmpresaTramite ApoderadoPoder : personaEmpresaTramiteService.obtieneListaApoderadoPersonaPorTramite(tramite)) {
//                naturalPoderPojo = new NaturalPoderPojo();
//                if (ApoderadoPoder.getPersona().getIdPersona() != null) {
//                    naturalPoderPojo.setPersonaPojo((Persona) personaService.find(Persona.class, ApoderadoPoder.getPersona().getIdPersona()));
//                    listaDatosDeContacto = datosContactoService.listaDatosDeContactoPorPersona((Persona) personaService.find(Persona.class, ApoderadoPoder.getPersona().getIdPersona()));
//                }
//                if (ApoderadoPoder.getPoder().getIdPoder() != null) {
//                    naturalPoderPojo.setPoderPojo((Poder) poderService.find(Poder.class, ApoderadoPoder.getPoder().getIdPoder()));
//                }
//                naturalPoderPojo.setListDatoContactoApoderadoPojo(listaDatosDeContacto);
//                listaNaturalPoderPojo.add(naturalPoderPojo);
//                listaDatosDeContacto = new ArrayList<DatosContacto>();
//            }
//        } else {
//            listaDatosDeContactoRepresentante = new ArrayList<DatosContacto>();
//        }
//        personaApoderado = new Persona();
//        listaDatosDeContactoRepresentante = new ArrayList<DatosContacto>();
//        naturalPoderPojo = new NaturalPoderPojo();
//    }
//
//    public String obtieneDireccionApoderado(NaturalPoderPojo naturalPoderPojo) {
//        String direccionVista = null;
//        for (DatosContacto datosContacto : naturalPoderPojo.getListDatoContactoApoderadoPojo()) {
//            if (datosContacto.getTipoContacto().getCodigo().equals(EnumTipoContacto.DIRECCION.getCodigo())) {
//                direccionVista = datosContacto.getDato();
//            }
//        }
//        return direccionVista;
//    }
//
//    public void iniDatosBD(Tramite tramite) {
//        int sw = 0;
//        marca = smsignomarca.getMarca();
//        try {
//            smsignomarca = smSignoMarcaService.registroSmsignoMarcaXTramite(tramite);
//            if (tramite.getTipoTramite().getCodigo().equals(EnumTipoSolicitudSigno.REGISTRO_NOMBRE_COMERCIAL.getCodigo())) {
//                generoTramite = smsignomarca.getTipoGenero().getCodigo();
//            }
//            if (tramite.getTipoTramite().getCodigo().equals(EnumTipoSolicitudSigno.REGISTRO_SIGNO_MARCA.getCodigo())) {
//                generoTramite = smsignomarca.getTipoGenero().getCodigo();
//            }
//
//            if (tramite.getTipoTramite().getCodigo().equals(EnumTipoTramite.REGISTRO_EN_EL_AREA_DE_SIGNOS_DISTINTIVOS.getCodigo())) {
//                //generoTramite = smsignomarca.getTipoGenero().getCodigo();
//                //depositoLemaRotulo = "";
//                generoDepositoRendered = true;
//
//            } else {
//
//                //generoTramite = smsignomarca.getTipoGenero().getCodigo();
//                // depositoLemaRotulo = smsignomarca.getTipoSolicitudDeposito().getCodigo();
//                generoDepositoRendered = false;
//            }
//
//            depositoLemaRotulo = smsignomarca.getTipoGenero().getCodigo();
//            List<SMTipoSignoSolicitado> listaSMTipoSignoSolicitado = smTipoSignoSolicitadoService.listaSMTipoSignoSolicitadoXSmSignoMarcaSolicitado(smsignomarca);
//
//            denominativo = false;
//            denominativoConGrafia = false;
//            denominacionOrigen = false;
//            figurativa = false;
//            tridimensional = false;
//            auditivo = false;
//            otro = false;
//
//            for (SMTipoSignoSolicitado clasificadormarca : listaSMTipoSignoSolicitado) {
//                if (clasificadormarca.getTipoSigno() != null) {
//                    if (clasificadormarca.getOtro() != null) {
//                        otro = true;
//                        tipoSignoOtros = clasificadormarca.getOtro();
//                        //   campoOtrosDescripcionDisable = false;
//                        campoCheckOtrosRendered = true;
//                    } else if (clasificadormarca.getTipoSigno().getCodigo().equals(EnumTipoSigno.DENOMINATIVO.getCodigo())) {
//                        denominativo = true;
//                        //   campoOtrosDescripcionDisable = true;
//                    } else if (clasificadormarca.getTipoSigno().getCodigo().equals(EnumTipoSigno.DENOMINATIVO_GRAFICA.getCodigo())) {
//                        denominativoConGrafia = true;
//                        //  campoOtrosDescripcionDisable = true;
//                    } else if (clasificadormarca.getTipoSigno().getCodigo().equals(EnumTipoSigno.DENOMINACION_ORIGEN.getCodigo())) {
//                        denominacionOrigen = true;
//                        //   campoOtrosDescripcionDisable = true;
//                    } else if (clasificadormarca.getTipoSigno().getCodigo().equals(EnumTipoSigno.FIGURATIVO.getCodigo())) {
//                        figurativa = true;
//                        //  campoOtrosDescripcionDisable = true;
//                    } else if (clasificadormarca.getTipoSigno().getCodigo().equals(EnumTipoSigno.TRIDIMENSIONAL.getCodigo())) {
//                        tridimensional = true;
//                        //  campoOtrosDescripcionDisable = true;
//                    } else if (clasificadormarca.getTipoSigno().getCodigo().equals(EnumTipoSigno.AUDITIVO.getCodigo())) {
//                        auditivo = true;
//                        //  campoOtrosDescripcionDisable = true;
//                    }
//                }
//            }
//            listaSignoClaseNizaAux = smSignoClaseNizaService.listaSmSignoClaseNizaXSmSignoMarca(smsignomarca);
//            listaSignoClaseNiza = smSignoClaseNizaService.listaSmSignoClaseNizaXSmSignoMarca(smsignomarca);
//            listaNumerosClaseNizaProductosServicios = claseNizaService.listaNumerosClaseNizaProductosServicios();
//            listArchivoPojo = archivoTxtNizaService.cargaArchivoNizaPorTramite(tramite);
//            //  numeroInternacional = listArchivoPojo.get(0).getsMSignoClaseNiza().getNumeroClaseNiza();
//            //  listaProductosTextArea = listArchivoPojo.get(0).getsMSignoClaseNiza().getListaProducto();                                    
////Se debe verificar que en la base se tenga registrado el logotipo
//            smslogotipo = new SMLogotipo();
//            if (smLogotipoService.listaSmLogotipoXSmSignoMarca(smsignomarca).size() > 0) {
//                smslogotipo = smLogotipoService.listaSmLogotipoXSmSignoMarca(smsignomarca).get(0);
//                nombreRutaImagen = smslogotipo.getNombreArchivo();
//                campoPanelImagenRendered = !nombreRutaImagen.isEmpty();
////                listaColores = smColorService.listaColorPorTramite(tramite, usuario);
//
//                listaImagenLogotipos = smImagenService.listaImagenesPorTramite(tramite, usuario);
//                for (SMImagen sMImagenDos : listaImagenLogotipos) {
//                    convierteImagenYGuardaCarpeta(sMImagenDos);
//                }
//                images = new ArrayList<String>();
//                images.add(nombreRutaImagen);
////                proteccionColor = smslogotipo.getProtegeColor();
//            } else {
//                nombreRutaImagen = "";
//                campoPanelImagenRendered = false;
//                images = new ArrayList<String>();
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }
//
//    public void convierteImagenYGuardaCarpeta(SMImagen sMImagen) {
//        byte[] bAvatar = sMImagen.getImagen();
//        ServletContext sContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
//        File folder = new File(sContext.getRealPath("/temp"));
//        if (!folder.exists()) {
//            folder.mkdirs();
//        }
//        String arquivo = sContext.getRealPath("/temp") + File.separator + sMImagen.getIdSMLogotipo().getNombreArchivo();
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
//
//    public void activaCampoTipoSignoOtrosDatoAction(ValueChangeEvent e) {
//        // campoOtrosDescripcionDisable = !otro;
//        campoPanelImagenRendered = figurativa || denominativoConGrafia || denominacionOrigen || tridimensional || auditivo || otro;
//        tipoSignoOtros = !otro ? null : tipoSignoOtros;
//        //campoOtrosDescripcionRendered = otro;
//        campoCheckOtrosRendered = otro;
//    }
//
//    public void resetColumnas() {
//        lemaComercial = "";
//        noEsMarcaSolicitadaRegistrada = Boolean.TRUE;
//        numeroFormularioSolicitud = "";
//        nombreMarcaLemaComercial = "";
//        codigoSMLemaComercial = "";
//        numeroRegistroLemaComercial = "";
//        serieLemaComercial = "";
//        fechaVigencia = "";
//        listaSignoClaseNizaAux = new ArrayList<SMSignoClaseNiza>();
//        depositoLemaRotulo = "";
//        listaImagenLogotipos = new ArrayList<SMImagen>();
//        tipoSignoOtros = "";
//        listaNaturalPoderPojo = new ArrayList<NaturalPoderPojo>();
//        listaNaturalJuridicoPojo = new ArrayList<NaturalJuridicoPojo>();
//        naturalPoderPojo = new NaturalPoderPojo();
//        naturalJuridicoPojo = new NaturalJuridicoPojo();
//        smsignomarca = new SMSignoMarca();
//        generoTramite = "";
//        denominativo = false;
//        denominativoConGrafia = false;
//        denominacionOrigen = false;
//        figurativa = false;
//        tridimensional = false;
//        auditivo = false;
//        otro = false;
//        smslogotipo = new SMLogotipo();
//        listArchivoPojo = new ArrayList<ArchivoNizaPojo>();
//        panelSignoLemaRendered = true;
//        marca = "";
//        personaApoderado = new Persona();
//        colorLabel = EnumColorLabelCampo.COLOR_LABEL.getCodigo();
//        listaNumerosClaseNizaProductosServicios = claseNizaService.listaNumerosClaseNizaServicios();
//        panelSignoLemaRendered = false;
//        panelLemaComercialRendered = false;
//        naturalJuridicoPojoM = new NaturalJuridicoPojo();
//        listaDatosDeContactoM = new ArrayList<DatosContacto>();
//        listaNaturalJuridicoPojoM = new ArrayList<NaturalJuridicoPojo>();
//        listaFusionParticipantes = new ArrayList<NaturalJuridicoPojo>();
//        modificacion = new Modificacion();
//        comboTipoModificacion = new TipoModificacion();
//        activaModificacionNombre = false;
//        activaModificacionNombreDataTable = false;
//        activaFusion = false;
//        activaLicenciasDeUso = false;
//        activaModificacionNombreDatosContacto = false;
//        activaModificacionNombreBotones = false;
//        activaModificacionDireccion = false;
//        panelLemaRendered = false;
//        checkTxtNiza = false;
//        listaRenovacionTramite = new ArrayList<RenovacionTramite>();
//        renovacionTramiteGuaradarBase = new RenovacionTramite();
//        numeroInternacionalRenovacion = "";
//        generoRenovacion = "";
//        listaProductosTextAreaRenovacion = "";
//        numeroInternacionalReclasificacion = "";
//        nombreArchivoDos = "";
//        renovacionTramite = new RenovacionTramite();
//        campoHabilitaTxtNiza = false;
//
//    }
//
//    public void inicializaValoresLemaComercial(Tramite tramite) {
//
//        ObservacionTramite observacionTramite;
//        TramiteUsuario usuarioCreadorTramite = tramiteUsuarioService.obtieneTramiteUsuarioPorTramite(tramite);
//        try {
//            observacionTramite = observacionTramiteService.obtenerObservacionesTramitePorTramiteYUsuario(tramite.getIdTramite(), usuarioCreadorTramite.getUsuario().getIdUsuario());
//            String[] cadenaLemaComercial = observacionTramiteService.obtenerCadena(observacionTramite.getDescripcion());
//
//            lemaComercial = smsignomarca.getMarca();
//            if (!numeroFormularioSolicitud.equals(" ")) {
//                noEsMarcaSolicitadaRegistrada = Boolean.TRUE;
//                numeroFormularioSolicitud = "";
//                nombreMarcaLemaComercial = cadenaLemaComercial[0];
//                codigoSMLemaComercial = cadenaLemaComercial[1];
//                numeroRegistroLemaComercial = cadenaLemaComercial[2];
//                serieLemaComercial = cadenaLemaComercial[3];
//                fechaVigencia = cadenaLemaComercial[4];
//            } else {
//                noEsMarcaSolicitadaRegistrada = Boolean.FALSE;
//                numeroFormularioSolicitud = cadenaLemaComercial[5];
//                nombreMarcaLemaComercial = "";
//                codigoSMLemaComercial = "";
//                numeroRegistroLemaComercial = "";
//                serieLemaComercial = "";
//                fechaVigencia = "";
//            }
//            marca = nombreMarcaLemaComercial;
//        } catch (Exception ex) {
//            Logger.getLogger(SolicitudLemaComercialBean.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//    }
//
//    public void inicializaDatosModificacion(Tramite tramite) {
//        try {
//            modificacion = modificacionService.recuperaModificacionPorTramite(tramite.getIdTramite(), usuario.getIdUsuario());
//            if (modificacion.getTipoSignoTitular().getCodigo().equals(EnumTipoGenero.LEMA_COMERCIAL.getCodigo())) {
//                panelLemaRendered = true;
//            }
//            marca = modificacion.getSignoDistintivoTitular();
//            comboTipoModificacion = modificacion.getTipoModificacion();
//            for (PersonaEmpresaTramite personaEmpresaTramiteM : personaEmpresaTramiteService.obtieneListaPersonaEmpresaPorTramiteNTITLICE(tramite)) {
//                naturalJuridicoPojoM = new NaturalJuridicoPojo();
//                if (personaEmpresaTramiteM.getEmpresa() != null) {
//                    naturalJuridicoPojoM.setEmpresaPojo((Empresa) empresaService.find(Empresa.class, personaEmpresaTramiteM.getEmpresa().getIdEmpresa()));
//                    listaDatosDeContactoM = datosContactoService.listaDatosDeContactoPorEmpresa(personaEmpresaTramiteM.getEmpresa());
//                } else {
//                    naturalJuridicoPojoM.setPersonaPojo((Persona) personaService.find(Persona.class, personaEmpresaTramiteM.getPersona().getIdPersona()));
//                    listaDatosDeContactoM = datosContactoService.listaDatosDeContactoPorPersona(personaEmpresaTramiteM.getPersona());
//                }
//                naturalJuridicoPojoM.setListDatoContactoPojo(listaDatosDeContactoM);
//                listaNaturalJuridicoPojoM.add(naturalJuridicoPojoM);
//                listaDatosDeContactoM = new ArrayList<DatosContacto>();
//            }
//
//            if (comboTipoModificacion.getCodigo().equals(EnumTipoModificacion.FUSION.getCodigo())) {
//                for (NaturalJuridicoPojo x : listaNaturalJuridicoPojoM) {
//                    listaFusionParticipantes.add(x);
//                }
//                Modificacion modi = modificacionService.obtieneModificacion(tramite);
//                StringTokenizer st = new StringTokenizer(modi.getFusionParticipante(), "|");
//                int tamanio = st.countTokens();
//                for (int i = 0; i < tamanio; i++) {
//                    String valor = st.nextToken().trim();
//                    if (valor.substring(valor.length() - 1, valor.length()).equals("*")) {
//                        NaturalJuridicoPojo n = new NaturalJuridicoPojo();
//                        Empresa e = new Empresa();
//                        e.setNombreRepresentante(valor.substring(0, valor.length() - 1));
//                        n.setListDatoContactoPojo(null);
//                        n.setEmpresaPojo(e);
//                        listaFusionParticipantes.add(n);
//                    }
//                }
//            }
//            naturalJuridicoPojoM = new NaturalJuridicoPojo();
//        } catch (Exception ex) {
//            Logger.getLogger(SolicitudGeneralModificacionBean.class
//                    .getName()).log(Level.SEVERE, null, ex);
//        }
//
//        activaCambios(comboTipoModificacion);
//    }
//
//    public void activaCambios(TipoModificacion comboTipoModificacion) {
////        listaDatosDeContactoM = new ArrayList<DatosContacto>();
////        listaNaturalJuridicoPojoM = new ArrayList<NaturalJuridicoPojo>();
////        listaFusionParticipantes = new ArrayList<NaturalJuridicoPojo>();
//
//        if (comboTipoModificacion.getCodigo().equals(EnumTipoModificacion.MODIFICACION_NOMBRE.getCodigo())) {
//            activaModificacionNombre = true;
//            activaModificacionNombreDatosContacto = false;
//            activaModificacionNombreBotones = true;
//            activaModificacionNombreDataTable = true;
//            activaModificacionDireccion = false;
//            activaLicenciasDeUso = false;
//            activaFusion = false;
//        } else {
//            if (comboTipoModificacion.getCodigo().equals(EnumTipoModificacion.MODIFICACION_DIRECCION.getCodigo())) {
//                activaModificacionNombre = false;
//                activaModificacionNombreDatosContacto = false;
//                activaModificacionNombreBotones = false;
//                activaModificacionNombreDataTable = false;
//                activaModificacionDireccion = true;
//                activaLicenciasDeUso = false;
//                activaFusion = false;
//            } else {
//                if (comboTipoModificacion.getCodigo().equals(EnumTipoModificacion.FUSION.getCodigo())) {
//                    activaModificacionNombre = true;
//                    activaModificacionNombreDatosContacto = true;
//                    activaModificacionNombreBotones = true;
//                    activaModificacionNombreDataTable = true;
//                    activaModificacionDireccion = false;
//                    activaLicenciasDeUso = false;
//                    activaFusion = true;
//                } else {
//                    if (comboTipoModificacion.getCodigo().equals(EnumTipoModificacion.TRANSFERENCIA.getCodigo())) {
//                        activaModificacionNombre = true;
//                        activaModificacionNombreDatosContacto = true;
//                        activaModificacionNombreBotones = true;
//                        activaModificacionNombreDataTable = true;
//                        activaModificacionDireccion = false;
//                        activaLicenciasDeUso = false;
//                        activaFusion = false;
//                    } else {
//                        activaModificacionNombre = true;
//                        activaModificacionNombreDatosContacto = true;
//                        activaModificacionNombreBotones = true;
//                        activaModificacionNombreDataTable = true;
//                        activaModificacionDireccion = false;
//                        activaLicenciasDeUso = true;
//                        activaFusion = false;
//                    }
//                }
//            }
//        }
//    }
//
//    public String obtieneNombreDeSolicitanteM(NaturalJuridicoPojo naturalJuridicoPojoMM) {
//        String nombre;
//        if (naturalJuridicoPojoMM.getEmpresaPojo().getNombreRepresentante() != null) {
//            nombre = naturalJuridicoPojoMM.getEmpresaPojo().getNombreRepresentante();
//        } else {
//            if (naturalJuridicoPojoMM.getPersonaPojo() != null) {
//                nombre = naturalJuridicoPojoMM.getPersonaPojo().getNombres() + ' ' + naturalJuridicoPojoMM.getPersonaPojo().getApellidoPaterno() + ' ' + naturalJuridicoPojoMM.getPersonaPojo().getApellidoMaterno();
//            } else {
//                nombre = naturalJuridicoPojoMM.getEmpresaPojo().getNombreRepresentante();
//            }
//        }
//        return nombre;
//    }
//
//    public void inicializaValoresRenovacion(Tramite tramite) throws Exception {
//        checkTxtNiza = Boolean.FALSE;
//        RenovacionTramite renovacionTramiteBase = renovacionTramiteService.ObtenerRegistroRenovacionTramitePorTramite(tramite.getIdTramite(), usuario.getIdUsuario());
//        String[] nombresTitulares = renovacionTramiteBase.getNombreRazonSocialTitular().split("\\|");
//        String[] direccionTitulares = renovacionTramiteBase.getDomicilio().split("\\|");
//        listaRenovacionTramite = new ArrayList<RenovacionTramite>();
//        int i = 0;
//        for (String string : nombresTitulares) {
//            RenovacionTramite renovacionDatoLista = new RenovacionTramite();
//            renovacionDatoLista.setNombreRazonSocialTitular(string);
//            renovacionDatoLista.setDomicilio(direccionTitulares[i]);
//            i++;
//            listaRenovacionTramite.add(renovacionDatoLista);
//        }
//        renovacionTramiteGuaradarBase = renovacionTramiteService.ObtenerRegistroRenovacionTramitePorTramite(tramite.getIdTramite(), usuario.getIdUsuario());
//        numeroInternacionalRenovacion = renovacionTramiteGuaradarBase.getNumeroClaseNiza();
//        generoRenovacion = renovacionTramiteGuaradarBase.getTipoGenero();
//        listaProductosTextAreaRenovacion = renovacionTramiteGuaradarBase.getListaProductosServicios();
//        numeroInternacionalReclasificacion = renovacionTramiteGuaradarBase.getClaseActualNiza();
//        renovacionTramite = new RenovacionTramite();
//        if (renovacionTramiteGuaradarBase.getArchivoListaProducto() != null) {
//            checkTxtNiza = true;
//            nombreArchivoDos = renovacionTramiteGuaradarBase.getNombreArchivoListaProducto();
//            campoHabilitaTxtNiza = checkTxtNiza;
//        }
//        marca = renovacionTramiteGuaradarBase.getSignoDistintivo();
//    }
//
//    public Tramite getTramite() {
//        return tramite;
//    }
//
//    public void setTramite(Tramite tramite) {
//        this.tramite = tramite;
//    }
//
//    public Usuario getUsuario() {
//        return usuario;
//    }
//
//    public void setUsuario(Usuario usuario) {
//        this.usuario = usuario;
//    }
//
//    public Persona getPersona() {
//        return persona;
//    }
//
//    public void setPersona(Persona persona) {
//        this.persona = persona;
//    }
//
//    public Empresa getEmpresa() {
//        return empresa;
//    }
//
//    public void setEmpresa(Empresa empresa) {
//        this.empresa = empresa;
//    }
//
//    public String getNombreCompletoPersona() {
//        return nombreCompletoPersona;
//    }
//
//    public void setNombreCompletoPersona(String nombreCompletoPersona) {
//        this.nombreCompletoPersona = nombreCompletoPersona;
//    }
//
//    public String getNombreCompletoEmpresa() {
//        return nombreCompletoEmpresa;
//    }
//
//    public void setNombreCompletoEmpresa(String nombreCompletoEmpresa) {
//        this.nombreCompletoEmpresa = nombreCompletoEmpresa;
//    }
//
//    public String getMensajeObservacionTramite() {
//        return mensajeObservacionTramite;
//    }
//
//    public void setMensajeObservacionTramite(String mensajeObservacionTramite) {
//        this.mensajeObservacionTramite = mensajeObservacionTramite;
//    }
//
//    public String getCodigoDia() {
//        return codigoDia;
//    }
//
//    public void setCodigoDia(String codigoDia) {
//        this.codigoDia = codigoDia;
//    }
//
//    public Boolean getCambiaColorCampoCodigoDia() {
//        return cambiaColorCampoCodigoDia;
//    }
//
//    public void setCambiaColorCampoCodigoDia(Boolean cambiaColorCampoCodigoDia) {
//        this.cambiaColorCampoCodigoDia = cambiaColorCampoCodigoDia;
//    }
//
//    public Boolean getActivaPanelesSiExisteTramite() {
//        return activaPanelesSiExisteTramite;
//    }
//
//    public void setActivaPanelesSiExisteTramite(Boolean activaPanelesSiExisteTramite) {
//        this.activaPanelesSiExisteTramite = activaPanelesSiExisteTramite;
//    }
//
//    public Boolean getActivaPanelesNoExisteTramite() {
//        return activaPanelesNoExisteTramite;
//    }
//
//    public void setActivaPanelesNoExisteTramite(Boolean activaPanelesNoExisteTramite) {
//        this.activaPanelesNoExisteTramite = activaPanelesNoExisteTramite;
//    }
//
//    public BusquedaService getBusquedaService() {
//        return busquedaService;
//    }
//
//    public void setBusquedaService(BusquedaService busquedaService) {
//        this.busquedaService = busquedaService;
//    }
//
//    public UsuarioService getUsuarioService() {
//        return usuarioService;
//    }
//
//    public void setUsuarioService(UsuarioService usuarioService) {
//        this.usuarioService = usuarioService;
//    }
//
//    public PersonaService getPersonaService() {
//        return personaService;
//    }
//
//    public void setPersonaService(PersonaService personaService) {
//        this.personaService = personaService;
//    }
//
//    public EmpresaService getEmpresaService() {
//        return empresaService;
//    }
//
//    public void setEmpresaService(EmpresaService empresaService) {
//        this.empresaService = empresaService;
//    }
//
//    //////////////////////////////////////////////////////////////////////////////
//    public List<NaturalJuridicoPojo> getListaNaturalJuridicoPojo() {
//        return listaNaturalJuridicoPojo;
//    }
//
//    public void setListaNaturalJuridicoPojo(List<NaturalJuridicoPojo> listaNaturalJuridicoPojo) {
//        this.listaNaturalJuridicoPojo = listaNaturalJuridicoPojo;
//    }
//
//    public PersonaEmpresaTramiteService getPersonaEmpresaTramiteService() {
//        return personaEmpresaTramiteService;
//    }
//
//    public void setPersonaEmpresaTramiteService(PersonaEmpresaTramiteService personaEmpresaTramiteService) {
//        this.personaEmpresaTramiteService = personaEmpresaTramiteService;
//    }
//
//    public DatosContactoService getDatosContactoService() {
//        return datosContactoService;
//    }
//
//    public void setDatosContactoService(DatosContactoService datosContactoService) {
//        this.datosContactoService = datosContactoService;
//    }
//
//    public String getColorLabel() {
//        return colorLabel;
//    }
//
//    public void setColorLabel(String colorLabel) {
//        this.colorLabel = colorLabel;
//    }
//
//    public List<NaturalPoderPojo> getListaNaturalPoderPojo() {
//        return listaNaturalPoderPojo;
//    }
//
//    public void setListaNaturalPoderPojo(List<NaturalPoderPojo> listaNaturalPoderPojo) {
//        this.listaNaturalPoderPojo = listaNaturalPoderPojo;
//    }
//
//    public PoderService getPoderService() {
//        return poderService;
//    }
//
//    public void setPoderService(PoderService poderService) {
//        this.poderService = poderService;
//    }
//
//    public String getMarca() {
//        return marca;
//    }
//
//    public void setMarca(String marca) {
//        this.marca = marca;
//    }
//
//    public SmTipoSignoSolicitadoService getSmTipoSignoSolicitadoService() {
//        return smTipoSignoSolicitadoService;
//    }
//
//    public void setSmTipoSignoSolicitadoService(SmTipoSignoSolicitadoService smTipoSignoSolicitadoService) {
//        this.smTipoSignoSolicitadoService = smTipoSignoSolicitadoService;
//    }
//
//    public Boolean getPanelSignoLemaRendered() {
//        return panelSignoLemaRendered;
//    }
//
//    public void setPanelSignoLemaRendered(Boolean panelSignoLemaRendered) {
//        this.panelSignoLemaRendered = panelSignoLemaRendered;
//    }
//
//    public Boolean getGeneroDepositoRendered() {
//        return generoDepositoRendered;
//    }
//
//    public void setGeneroDepositoRendered(Boolean generoDepositoRendered) {
//        this.generoDepositoRendered = generoDepositoRendered;
//    }
//
//    public Boolean getDenominativo() {
//        return denominativo;
//    }
//
//    public void setDenominativo(Boolean denominativo) {
//        this.denominativo = denominativo;
//    }
//
//    public Boolean getDenominativoConGrafia() {
//        return denominativoConGrafia;
//    }
//
//    public void setDenominativoConGrafia(Boolean denominativoConGrafia) {
//        this.denominativoConGrafia = denominativoConGrafia;
//    }
//
//    public Boolean getDenominacionOrigen() {
//        return denominacionOrigen;
//    }
//
//    public void setDenominacionOrigen(Boolean denominacionOrigen) {
//        this.denominacionOrigen = denominacionOrigen;
//    }
//
//    public Boolean getFigurativa() {
//        return figurativa;
//    }
//
//    public void setFigurativa(Boolean figurativa) {
//        this.figurativa = figurativa;
//    }
//
//    public Boolean getTridimensional() {
//        return tridimensional;
//    }
//
//    public void setTridimensional(Boolean tridimensional) {
//        this.tridimensional = tridimensional;
//    }
//
//    public Boolean getAuditivo() {
//        return auditivo;
//    }
//
//    public void setAuditivo(Boolean auditivo) {
//        this.auditivo = auditivo;
//    }
//
//    public Boolean getOtro() {
//        return otro;
//    }
//
//    public void setOtro(Boolean otro) {
//        this.otro = otro;
//    }
//
//    public String getTipoSignoOtros() {
//        return tipoSignoOtros;
//    }
//
//    public void setTipoSignoOtros(String tipoSignoOtros) {
//        this.tipoSignoOtros = tipoSignoOtros;
//    }
//
//    public Boolean getCampoCheckOtrosRendered() {
//        return campoCheckOtrosRendered;
//    }
//
//    public void setCampoCheckOtrosRendered(Boolean campoCheckOtrosRendered) {
//        this.campoCheckOtrosRendered = campoCheckOtrosRendered;
//    }
//
//    public SmSignoMarcaService getSmSignoMarcaService() {
//        return smSignoMarcaService;
//    }
//
//    public void setSmSignoMarcaService(SmSignoMarcaService smSignoMarcaService) {
//        this.smSignoMarcaService = smSignoMarcaService;
//    }
//
//    public SmSignoClaseNizaService getSmSignoClaseNizaService() {
//        return smSignoClaseNizaService;
//    }
//
//    public void setSmSignoClaseNizaService(SmSignoClaseNizaService smSignoClaseNizaService) {
//        this.smSignoClaseNizaService = smSignoClaseNizaService;
//    }
//
//    public ClaseNizaService getClaseNizaService() {
//        return claseNizaService;
//    }
//
//    public void setClaseNizaService(ClaseNizaService claseNizaService) {
//        this.claseNizaService = claseNizaService;
//    }
//
//    public ArchivoTxtNizaService getArchivoTxtNizaService() {
//        return archivoTxtNizaService;
//    }
//
//    public void setArchivoTxtNizaService(ArchivoTxtNizaService archivoTxtNizaService) {
//        this.archivoTxtNizaService = archivoTxtNizaService;
//    }
//
//    public SmLogotipoService getSmLogotipoService() {
//        return smLogotipoService;
//    }
//
//    public void setSmLogotipoService(SmLogotipoService smLogotipoService) {
//        this.smLogotipoService = smLogotipoService;
//    }
//
//    public SmImagenService getSmImagenService() {
//        return smImagenService;
//    }
//
//    public void setSmImagenService(SmImagenService smImagenService) {
//        this.smImagenService = smImagenService;
//    }
//
//    public Boolean getCampoPanelImagenRendered() {
//        return campoPanelImagenRendered;
//    }
//
//    public void setCampoPanelImagenRendered(Boolean campoPanelImagenRendered) {
//        this.campoPanelImagenRendered = campoPanelImagenRendered;
//    }
//
//    public List<SMImagen> getListaImagenLogotipos() {
//        return listaImagenLogotipos;
//    }
//
//    public void setListaImagenLogotipos(List<SMImagen> listaImagenLogotipos) {
//        this.listaImagenLogotipos = listaImagenLogotipos;
//    }
//
//    public SMSignoMarca getSmsignomarca() {
//        return smsignomarca;
//    }
//
//    public void setSmsignomarca(SMSignoMarca smsignomarca) {
//        this.smsignomarca = smsignomarca;
//    }
//
//    public String getGeneroTramite() {
//        return generoTramite;
//    }
//
//    public void setGeneroTramite(String generoTramite) {
//        this.generoTramite = generoTramite;
//    }
//
//    public List<TipoGenero> getListadoTipoGeneroTramite() {
//        return listadoTipoGeneroTramite = clasificadorService.listadoTipoGeneroTramite();
//    }
//
//    public void setListadoTipoGeneroTramite(List<TipoGenero> listadoTipoGeneroTramite) {
//        this.listadoTipoGeneroTramite = listadoTipoGeneroTramite;
//    }
//
//    public ClasificadorService getClasificadorService() {
//        return clasificadorService;
//    }
//
//    public void setClasificadorService(ClasificadorService clasificadorService) {
//        this.clasificadorService = clasificadorService;
//    }
//
//    public List<TipoSolicitudDeposito> getListaDepositoLemaRotulo() {
//        return listaDepositoLemaRotulo = clasificadorService.listadoSolicitudDeposito();
//    }
//
//    public void setListaDepositoLemaRotulo(List<TipoSolicitudDeposito> listaDepositoLemaRotulo) {
//        this.listaDepositoLemaRotulo = listaDepositoLemaRotulo;
//    }
//
//    public String getDepositoLemaRotulo() {
//        return depositoLemaRotulo;
//    }
//
//    public void setDepositoLemaRotulo(String depositoLemaRotulo) {
//        this.depositoLemaRotulo = depositoLemaRotulo;
//    }
//
//    public List<SMSignoClaseNiza> getListaSignoClaseNizaAux() {
//        return listaSignoClaseNizaAux;
//    }
//
//    public void setListaSignoClaseNizaAux(List<SMSignoClaseNiza> listaSignoClaseNizaAux) {
//        this.listaSignoClaseNizaAux = listaSignoClaseNizaAux;
//    }
//
//    public String getLemaComercial() {
//        return lemaComercial;
//    }
//
//    public void setLemaComercial(String lemaComercial) {
//        this.lemaComercial = lemaComercial;
//    }
//
//    public Boolean getNoEsMarcaSolicitadaRegistrada() {
//        return noEsMarcaSolicitadaRegistrada;
//    }
//
//    public void setNoEsMarcaSolicitadaRegistrada(Boolean noEsMarcaSolicitadaRegistrada) {
//        this.noEsMarcaSolicitadaRegistrada = noEsMarcaSolicitadaRegistrada;
//    }
//
//    public TramiteUsuarioService getTramiteUsuarioService() {
//        return tramiteUsuarioService;
//    }
//
//    public void setTramiteUsuarioService(TramiteUsuarioService tramiteUsuarioService) {
//        this.tramiteUsuarioService = tramiteUsuarioService;
//    }
//
//    public ObservacionTramiteService getObservacionTramiteService() {
//        return observacionTramiteService;
//    }
//
//    public void setObservacionTramiteService(ObservacionTramiteService observacionTramiteService) {
//        this.observacionTramiteService = observacionTramiteService;
//    }
//
//    public String getNombreMarcaLemaComercial() {
//        return nombreMarcaLemaComercial;
//    }
//
//    public void setNombreMarcaLemaComercial(String nombreMarcaLemaComercial) {
//        this.nombreMarcaLemaComercial = nombreMarcaLemaComercial;
//    }
//
//    public String getCodigoSMLemaComercial() {
//        return codigoSMLemaComercial;
//    }
//
//    public void setCodigoSMLemaComercial(String codigoSMLemaComercial) {
//        this.codigoSMLemaComercial = codigoSMLemaComercial;
//    }
//
//    public String getNumeroRegistroLemaComercial() {
//        return numeroRegistroLemaComercial;
//    }
//
//    public void setNumeroRegistroLemaComercial(String numeroRegistroLemaComercial) {
//        this.numeroRegistroLemaComercial = numeroRegistroLemaComercial;
//    }
//
//    public String getSerieLemaComercial() {
//        return serieLemaComercial;
//    }
//
//    public void setSerieLemaComercial(String serieLemaComercial) {
//        this.serieLemaComercial = serieLemaComercial;
//    }
//
//    public String getFechaVigencia() {
//        return fechaVigencia;
//    }
//
//    public void setFechaVigencia(String fechaVigencia) {
//        this.fechaVigencia = fechaVigencia;
//    }
//
//    public Boolean getPanelLemaComercialRendered() {
//        return panelLemaComercialRendered;
//    }
//
//    public void setPanelLemaComercialRendered(Boolean panelLemaComercialRendered) {
//        this.panelLemaComercialRendered = panelLemaComercialRendered;
//    }
//
//    public Boolean getPanelModificacionRendered() {
//        return panelModificacionRendered;
//    }
//
//    public void setPanelModificacionRendered(Boolean panelModificacionRendered) {
//        this.panelModificacionRendered = panelModificacionRendered;
//    }
//
//    public Modificacion getModificacion() {
//        return modificacion;
//    }
//
//    public void setModificacion(Modificacion modificacion) {
//        this.modificacion = modificacion;
//    }
//
//    public ModificacionService getModificacionService() {
//        return modificacionService;
//    }
//
//    public void setModificacionService(ModificacionService modificacionService) {
//        this.modificacionService = modificacionService;
//    }
//
//    public List<TipoGenero> getListadoTipoGeneroTramiteModificacion() {
//        return listadoTipoGeneroTramiteModificacion = clasificadorService.listadoTipoGenero();
//    }
//
//    public void setListadoTipoGeneroTramiteModificacion(List<TipoGenero> listadoTipoGeneroTramiteModificacion) {
//        this.listadoTipoGeneroTramiteModificacion = listadoTipoGeneroTramiteModificacion;
//    }
//
//    public Boolean getActivaModificacionNombre() {
//        return activaModificacionNombre;
//    }
//
//    public void setActivaModificacionNombre(Boolean activaModificacionNombre) {
//        this.activaModificacionNombre = activaModificacionNombre;
//    }
//
//    public Boolean getActivaModificacionNombreDataTable() {
//        return activaModificacionNombreDataTable;
//    }
//
//    public void setActivaModificacionNombreDataTable(Boolean activaModificacionNombreDataTable) {
//        this.activaModificacionNombreDataTable = activaModificacionNombreDataTable;
//    }
//
//    public Boolean getActivaFusion() {
//        return activaFusion;
//    }
//
//    public void setActivaFusion(Boolean activaFusion) {
//        this.activaFusion = activaFusion;
//    }
//
//    public Boolean getActivaLicenciasDeUso() {
//        return activaLicenciasDeUso;
//    }
//
//    public void setActivaLicenciasDeUso(Boolean activaLicenciasDeUso) {
//        this.activaLicenciasDeUso = activaLicenciasDeUso;
//    }
//
//    public Boolean getActivaModificacionNombreDatosContacto() {
//        return activaModificacionNombreDatosContacto;
//    }
//
//    public void setActivaModificacionNombreDatosContacto(Boolean activaModificacionNombreDatosContacto) {
//        this.activaModificacionNombreDatosContacto = activaModificacionNombreDatosContacto;
//    }
//
//    public Boolean getActivaModificacionNombreBotones() {
//        return activaModificacionNombreBotones;
//    }
//
//    public void setActivaModificacionNombreBotones(Boolean activaModificacionNombreBotones) {
//        this.activaModificacionNombreBotones = activaModificacionNombreBotones;
//    }
//
//    public Boolean getActivaModificacionDireccion() {
//        return activaModificacionDireccion;
//    }
//
//    public void setActivaModificacionDireccion(Boolean activaModificacionDireccion) {
//        this.activaModificacionDireccion = activaModificacionDireccion;
//    }
//
//    public List<NaturalJuridicoPojo> getListaNaturalJuridicoPojoM() {
//        return listaNaturalJuridicoPojoM;
//    }
//
//    public void setListaNaturalJuridicoPojoM(List<NaturalJuridicoPojo> listaNaturalJuridicoPojoM) {
//        this.listaNaturalJuridicoPojoM = listaNaturalJuridicoPojoM;
//    }
//
//    public List<NaturalJuridicoPojo> getListaFusionParticipantes() {
//        return listaFusionParticipantes;
//    }
//
//    public void setListaFusionParticipantes(List<NaturalJuridicoPojo> listaFusionParticipantes) {
//        this.listaFusionParticipantes = listaFusionParticipantes;
//    }
//
//    public List<TipoPais> getCambioDireccionListadoPaisesClasificadorNacionalidad() {
//        return cambioDireccionListadoPaisesClasificadorNacionalidad = clasificadorService.listadoTipoPais();
//    }
//
//    public void setCambioDireccionListadoPaisesClasificadorNacionalidad(List<TipoPais> cambioDireccionListadoPaisesClasificadorNacionalidad) {
//        this.cambioDireccionListadoPaisesClasificadorNacionalidad = cambioDireccionListadoPaisesClasificadorNacionalidad;
//    }
//
//    public Boolean getPanelRenovacionRendered() {
//        return panelRenovacionRendered;
//    }
//
//    public void setPanelRenovacionRendered(Boolean panelRenovacionRendered) {
//        this.panelRenovacionRendered = panelRenovacionRendered;
//    }
//
//    public Boolean getPanelLemaRendered() {
//        return panelLemaRendered;
//    }
//
//    public void setPanelLemaRendered(Boolean panelLemaRendered) {
//        this.panelLemaRendered = panelLemaRendered;
//    }
//
//    public RenovacionTramiteService getRenovacionTramiteService() {
//        return renovacionTramiteService;
//    }
//
//    public void setRenovacionTramiteService(RenovacionTramiteService renovacionTramiteService) {
//        this.renovacionTramiteService = renovacionTramiteService;
//    }
//
//    public RegistroService getRegistroService() {
//        return registroService;
//    }
//
//    public void setRegistroService(RegistroService registroService) {
//        this.registroService = registroService;
//    }
//
//    public List<RenovacionTramite> getListaRenovacionTramite() {
//        return listaRenovacionTramite;
//    }
//
//    public void setListaRenovacionTramite(List<RenovacionTramite> listaRenovacionTramite) {
//        this.listaRenovacionTramite = listaRenovacionTramite;
//    }
//
//    public List<TipoGenero> getListadoTipoGeneroTramiteRenovacion() {
//        return listadoTipoGeneroTramiteRenovacion = clasificadorService.listadoTipoGeneroTramiteRenovacion();
//    }
//
//    public void setListadoTipoGeneroTramiteRenovacion(List<TipoGenero> listadoTipoGeneroTramiteRenovacion) {
//        this.listadoTipoGeneroTramiteRenovacion = listadoTipoGeneroTramiteRenovacion;
//    }
//
//    public String getGeneroRenovacion() {
//        return generoRenovacion;
//    }
//
//    public void setGeneroRenovacion(String generoRenovacion) {
//        this.generoRenovacion = generoRenovacion;
//    }
//
//    public String getNumeroInternacionalRenovacion() {
//        return numeroInternacionalRenovacion;
//    }
//
//    public void setNumeroInternacionalRenovacion(String numeroInternacionalRenovacion) {
//        this.numeroInternacionalRenovacion = numeroInternacionalRenovacion;
//    }
//
//    public List<String> getListaNumerosClaseNizaProductosServicios() {
//        return listaNumerosClaseNizaProductosServicios = claseNizaService.listaNumerosClaseNizaProductosServicios();
//    }
//
//    public void setListaNumerosClaseNizaProductosServicios(List<String> listaNumerosClaseNizaProductosServicios) {
//        this.listaNumerosClaseNizaProductosServicios = listaNumerosClaseNizaProductosServicios;
//    }
//
//    public RenovacionTramite getRenovacionTramiteGuaradarBase() {
//        return renovacionTramiteGuaradarBase;
//    }
//
//    public void setRenovacionTramiteGuaradarBase(RenovacionTramite renovacionTramiteGuaradarBase) {
//        this.renovacionTramiteGuaradarBase = renovacionTramiteGuaradarBase;
//    }
//
//    public String getListaProductosTextAreaRenovacion() {
//        return listaProductosTextAreaRenovacion;
//    }
//
//    public void setListaProductosTextAreaRenovacion(String listaProductosTextAreaRenovacion) {
//        this.listaProductosTextAreaRenovacion = listaProductosTextAreaRenovacion;
//    }
//
//    public String getNumeroInternacionalReclasificacion() {
//        return numeroInternacionalReclasificacion;
//    }
//
//    public void setNumeroInternacionalReclasificacion(String numeroInternacionalReclasificacion) {
//        this.numeroInternacionalReclasificacion = numeroInternacionalReclasificacion;
//    }

}
