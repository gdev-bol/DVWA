
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.entidades.bean.modificacion;

import java.io.Serializable;
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
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import snp.gob.bo.entidades.bean.common.AbstractManagedBean;
import snp.gob.bo.entidades.bean.login.persona;
import snp.gob.bo.gimodel.entidad.ModModificacion;
import snp.gob.bo.gimodel.entidad.Dominio;
import snp.gob.bo.gimodel.entidad.Etapa;
import snp.gob.bo.gimodel.enums.EnumTipoTitular;
import snp.gob.bo.gimodel.enums.EnumTipoModificacion;
import snp.gob.bo.gimodel.enums.EnumEstadoModificacion;
import snp.gob.bo.gimodel.enums.EnumNombreDominio;
import snp.gob.bo.gimodel.entidad.ModDireccionDeNotificacion;
import snp.gob.bo.gimodel.entidad.ModSolicitanteApoderado;
import snp.gob.bo.gimodel.entidad.ModTipoSigno;
import snp.gob.bo.gimodel.entidad.ModTitularLicenciatarioNuevo;
import snp.gob.bo.gimodel.entidad.ModTitularLicenciatarioRegistrado;
import snp.gob.bo.gimodel.entidad.SigSignoClaseNiza;
import snp.gob.bo.gimodel.entidad.SigSignoMarca;
import snp.gob.bo.gimodel.entidad.SigSolicitanteApoderado;
import snp.gob.bo.gimodel.entidad.SigTipoSigno;
import snp.gob.bo.gimodel.enums.EnumTipoPersona;
import snp.gob.bo.gimodel.servicio.RegionalService;
import snp.gob.bo.gimodel.servicio.DominioService;
import snp.gob.bo.gimodel.servicio.ModDireccionDeNotificacionService;
import snp.gob.bo.gimodel.servicio.ModModificacionService;
import snp.gob.bo.gimodel.servicio.ModResolucionService;
import snp.gob.bo.gimodel.servicio.ModSolicitanteApoderadoService;
import snp.gob.bo.gimodel.servicio.ModTipoSignoService;
import snp.gob.bo.gimodel.servicio.ModTitularLicenciatarioNuevoService;
import snp.gob.bo.gimodel.servicio.ModTitularLicenciatarioRegistradoService;
import snp.gob.bo.gimodel.servicio.SigSignoClaseNizaService;
import snp.gob.bo.gimodel.servicio.SigSignoMarcaService;
import snp.gob.bo.gimodel.servicio.SigSolicitanteApoderadoService;
import snp.gob.bo.gimodel.servicio.SigTipoSignoService;
import snp.gob.bo.gimodel.entidad.ModResolucion;
import snp.gob.bo.gimodel.entidad.ObservacionTramite;
import snp.gob.bo.gimodel.entidad.Seguimiento;
import snp.gob.bo.gimodel.entidad.Usuario;
import snp.gob.bo.gimodel.enums.EnumEstado;
import snp.gob.bo.gimodel.enums.EnumOperacion;
import snp.gob.bo.gimodel.enums.EnumPrefijoTablas;
import snp.gob.bo.gimodel.enums.EnumTipoFormulario;
import snp.gob.bo.gimodel.servicio.ComunService;
import snp.gob.bo.gimodel.servicio.EtapaService;
import snp.gob.bo.gimodel.servicio.FlujoSeguimientoService;
import snp.gob.bo.gimodel.servicio.FlujoTiempoService;
import snp.gob.bo.gimodel.servicio.HistorialService;
import snp.gob.bo.gimodel.servicio.LogTransService;
import snp.gob.bo.gimodel.servicio.ModHistorialService;
import snp.gob.bo.gimodel.servicio.ObservacionTramiteService;
import snp.gob.bo.gimodel.servicio.SeguimientoService;
import snp.gob.bo.gimodel.servicio.UsuarioService;
import snp.gob.bo.gimodel.solicitudes.entidades.Apoderados;
import snp.gob.bo.gimodel.solicitudes.entidades.DireccionNotificaciones;
import snp.gob.bo.gimodel.solicitudes.entidades.FormularioPI103;
import snp.gob.bo.gimodel.solicitudes.entidades.ModTipoSignos;
import snp.gob.bo.gimodel.solicitudes.entidades.Modificaciones;
import snp.gob.bo.gimodel.solicitudes.entidades.Solicitantes;
import snp.gob.bo.gimodel.solicitudes.entidades.TitularLicenciatarios;
import snp.gob.bo.gimodel.solicitudes.entidades.TitularLicenciatariosNuevos;
import snp.gob.bo.gimodel.solicitudes.servicio.FormularioPI103Service;
import snp.gob.bo.gimodel.solicitudes.servicio.FormularioService;
import snp.gob.bo.giview.ventanilla.IngresoSolicitudTramiteBean;

/**
 *
 * @author susana
 */
@ManagedBean(name = "examenModSubsanacionBean")
@ViewScoped
public class ExamenModSubsanacionBean extends AbstractManagedBean implements Serializable {

    /**
     * Creates a new instance
     */
    public ExamenModSubsanacionBean() {
    }

    private String extensionSM;
    private Boolean radioNombre = false;
    private Boolean radioDomicilio = false;
    private Boolean radioTransferencia = false;
    private Boolean radioFusion = false;
    private Boolean radioLU = false;
    private Boolean panelLema = false;

    private String valorRadio = "CN";
    private String valorSigla = "CN";
    private String tipoMarca = "";
    private String numeroFormulario;

    private Integer resolucion;
    private Integer gestion;
    private Date fechaResolucion;
    private Integer adicionarResolucion;
    private Integer adicionarGestion;
    private Date adicionarFecha;

    private List<Dominio> lstOficina = new ArrayList<>();
    private List<persona> lstPersonas;
    private List<ModSolicitanteApoderado> listaSolicitanteApoderado = new ArrayList<>();
    private List<ModSolicitanteApoderado> listaApoderado = new ArrayList<>();
    private List<ModTitularLicenciatarioNuevo> listaNuevoTitular = new ArrayList<>();
    private List<TitularLicenciatariosNuevos> listaNuevoTitularSipi = new ArrayList<>();
    private List<ModTitularLicenciatarioRegistrado> listaTitularRegistrado = new ArrayList<>();
    private List<TitularLicenciatarios> listaTitularRegistradoSipi = new ArrayList<>();
    private List<Solicitantes> listaModSolicitantesSipi = new ArrayList<>();
    private List<Apoderados> listaModApoderadosSipi = new ArrayList<>();
    private Modificaciones modificacionSipi = new Modificaciones();
    private DireccionNotificaciones direccionNotificacionSipi = new DireccionNotificaciones();
    private ModSolicitanteApoderado modSolicitanteApoderadoSelect = new ModSolicitanteApoderado();
    private Integer indice = 0;
    private ModTitularLicenciatarioNuevo titularLicencitarioNuevoSelect;
    private ModTitularLicenciatarioRegistrado titularLicenciatarioRegistradoSelect = new ModTitularLicenciatarioRegistrado();
    private String participanteFusion = "";
    private List<ModTitularLicenciatarioRegistrado> listaParticipanteFusion = new ArrayList<>();
    private ModModificacion modificacion = new ModModificacion();
    private String regional = "LPZ";
    private String textoSM = "SM";
    private Long numeroSM;
    private Long gestionSM;

    private ModDireccionDeNotificacion direccionNotificacion = new ModDireccionDeNotificacion();
    private Long numeroMod;
    private Integer gestionMod;
    private List<Dominio> listaPais;
    private List<Dominio> listaLugarExpedicion;
    private List<Dominio> listaTipoGenero;
    private List<Dominio> listaTipoSigno;
    private List<Dominio> lstSituacionActual;
    private List<Dominio> lstCiudadNotificacion;
    private List<Dominio> lstTipoRecibo;
    private List<Dominio> lstSerieRegistro;
    private List<Dominio> lstUbicacionModificacion;

    private Boolean btnAdicionarDisabled = false;
    private Boolean btnModificarDisabled = false;
    private String valorOpcionesRadio = "CON";
    private String tipoModificacion = EnumTipoModificacion.CAMBIO_NOMBRE.getCodigo();
    private Long nroPublicacionExpediente;
    private Long registroExpediente;
    private String serieRegExpediente = "";
    private String serieRenoExpediente = "";
    private Long renovacionExpediente;
    private Long smExpediente;
    private Long gestionExpediente;
    private String extensionExpediente = new String();
    private List<ModTipoSigno> lstModTipoSigno = new ArrayList<>();

    private Boolean txtOtroRendered = false;
    private String[] tipoGeneroSeleccionado;
    private String textoOtro = null;
    private ModResolucion modresolucion = new ModResolucion();
    private Boolean[] validaSectores = {false, false, false, false, false, false, false, false, true, true, true};
    private Boolean[] validaModificacionResultado = new Boolean[15];
    private Boolean[] validaCambioDomicilioResultado = new Boolean[15];
    private Boolean[] validaDireccionNotificacionResultado = new Boolean[15];
    private Boolean[] validadorSMRegistroPublicacion = new Boolean[5];
    private String textoObservacionResol;
    private Boolean btnObservacionRendered = false;
    private ObservacionTramite observacionTramite = new ObservacionTramite();
    private Seguimiento ultimoSeguimiento = new Seguimiento();
    private Boolean botonRecepcionarDisabled = true;
    private Boolean botonFinalizarDisabled = true;
    private List<SigSolicitanteApoderado> lstTitularActual = new ArrayList<>();
    private Boolean panelSeleccionarIdReferencia = true;
    private Boolean btnRegistrarDisabled = true;
    private Boolean productosRendered = false;
    private Boolean botonRecepcionarRendered = false;
    private Boolean botonFinalizarRendered = false;
    private Boolean linkReasignarRendered = false;
    private Long idUsuario;
    private Date fechaServidor;
    private SigSignoMarca signoMarca;
    private Long idLogTrans;
    private List<Etapa> listEtapaUs;
    private List<Usuario> listUsuario;

    // parametros reportes
    private String reporte;
    private Map<String, Object> parametros = new HashMap();

    private String imgSenapi;
    private String tramite;
    private String fechaSolicitud;
    private String titulo;
    private String numResolucion;
    private String fechaActual;
    private String textConsiderado;
    private String textResuelve;
    private String observacion;
    private String titulares;
    private String representantes;
    private String usuario;

    private String tipoResolucion = "";

    private List<ModTitularLicenciatarioNuevo> listaNuevoTitularR = new ArrayList();
    private List<ModTitularLicenciatarioRegistrado> listaTitularRegistradoR = new ArrayList();
    private List<ModSolicitanteApoderado> listaTitulares = new ArrayList();
    private List<ModSolicitanteApoderado> listaRepresentantes = new ArrayList();
    private List<Dominio> lstTipoModificacion = new ArrayList();
    private String tipoSignoDescripcion = "Elegir";
    private Boolean resolucionDisabled = true;
    private String template = "./../WEB-INF/facelets/templates/Template.xhtml";
    private Boolean esRevocatoria = false;
    private FormularioPI103 formularioPI103 = new FormularioPI103();
    private String valorTipoGenero = "";
    private String oficina = "";
    private String tipoTramite;
    private Boolean panelPI103;
    private Boolean panelEncontrado;
    private Boolean panelNoEncontrado;
    private String signoDistintivoExt = "";
    private Long valorClaseNizaExt;
    private Long idSolicitudRenovacion;
    private String mensajeBusqueda = "";
    private String versionClaseNiza = "";

    @ManagedProperty("#{dominioService}")
    private DominioService dominioService;

    @ManagedProperty("#{regionalService}")
    private RegionalService regionalService;

    @ManagedProperty("#{modSolicitanteApoderadoService}")
    private ModSolicitanteApoderadoService modSolicitanteApoderadoService;

    @ManagedProperty("#{modTitularLicencitarioNuevoService}")
    private ModTitularLicenciatarioNuevoService modTitularLicencitarioNuevoService;

    @ManagedProperty("#{modTitularLicenciatarioRegistradoService}")
    private ModTitularLicenciatarioRegistradoService modTitularLicenciatarioRegistradoService;

    @ManagedProperty("#{modModificacionService}")
    private ModModificacionService modModificacionService;

    @ManagedProperty("#{smDominioService}")
    private DominioService smDominioService;

    @ManagedProperty("#{modDireccionDeNotificacionService}")
    private ModDireccionDeNotificacionService modDireccionDeNotificacionService;

    @ManagedProperty("#{sigSignoMarcaService}")
    private SigSignoMarcaService sigSignoMarcaService;

    @ManagedProperty("#{sigSignoClaseNizaService}")
    private SigSignoClaseNizaService sigSignoClaseNizaService;

    @ManagedProperty("#{sigTipoSignoService}")
    private SigTipoSignoService sigTipoSignoService;

    @ManagedProperty("#{sigSolicitanteApoderadoService}")
    private SigSolicitanteApoderadoService sigSolicitanteApoderadoService;

    @ManagedProperty("#{modTipoSignoService}")
    private ModTipoSignoService modTipoSignoService;

    @ManagedProperty("#{modResolucionService}")
    private ModResolucionService modResolucionService;

    @ManagedProperty("#{comunService}")
    private ComunService comunService;

    @ManagedProperty("#{observacionTramiteService}")
    private ObservacionTramiteService observacionTramiteService;

    @ManagedProperty("#{seguimientoService}")
    private SeguimientoService seguimientoService;

    @ManagedProperty("#{modHistorialService}")
    private ModHistorialService modHistorialService;

    @ManagedProperty("#{logTransService}")
    private LogTransService logTransService;

    @ManagedProperty("#{etapaService}")
    private EtapaService etapaService;

    @ManagedProperty("#{usuarioService}")
    private UsuarioService usuarioService;

    @ManagedProperty("#{flujoSeguimientoService}")
    private FlujoSeguimientoService flujoSeguimientoService;

    @ManagedProperty("#{flujoTiempoService}")
    private FlujoTiempoService flujoTiempoService;

    @ManagedProperty("#{historialService}")
    private HistorialService historialService;

    @ManagedProperty(value = "#{formularioService}")
    private FormularioService formularioService;

    @ManagedProperty(value = "#{formularioPI103Service}")
    private FormularioPI103Service formularioPI103Service;

    @PostConstruct
    public void ExamenModificacionBeanInit() {
        if (super.getIdUsuarioSession() != null) {
            idUsuario = super.getIdUsuarioSession();
        }
        if (getNavegaPagina() != null && !getNavegaPagina().equals("")) {
            template = "./../WEB-INF/facelets/templates/DialogTemplate.xhtml";
        }

        radioNombre = true;
        try {
            listaLugarExpedicion = dominioService.obtenerListadoDominio("lugar_expedicion");
            listaPais = dominioService.obtenerListadoDominio("pais");
            listaTipoGenero = dominioService.obtenerListadoDominio("tipo_genero");
            listaTipoSigno = dominioService.obtenerListadoDominio("tipo_signo");
            lstSituacionActual = dominioService.obtenerListadoDominio("estado_modificacion");
            lstOficina = dominioService.obtenerListadoDominio("oficina");
            lstCiudadNotificacion = dominioService.obtenerListadoDominio("ciudad_notificacion");
            lstTipoRecibo = dominioService.obtenerListadoDominio("serie_recibo");
            lstSerieRegistro = dominioService.obtenerListadoDominio("serie_registro");
            lstUbicacionModificacion = dominioService.obtenerListadoDominio("ubicacion_modificacion");
            lstTipoModificacion = dominioService.obtenerListadoDominio("tipo_modificacion");
            listEtapaUs = etapaService.listadoPorIdUsuario(super.getIdUsuarioSession(), getIdEtapaSession());
            listUsuario = usuarioService.listaUsuarioXidPagina(super.getIdUsuarioSession());
        } catch (Exception ex) {
            Logger.getLogger(ExamenModificacionBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        cambiaOpcionesFormulario();

        obtenerParametrosBusquedaExpedienteModificacion();

        if (getIdmodificacion() != null) {
            try {
                inicializaTodo();

                modificacion.setIdmodificacion(getIdmodificacion());
                modificacion = modModificacionService.guardar_modificar_listar_ModModificacion(modificacion, 4);
                valorRadio = modificacion.getSigla().substring(0, 2);

                activaCambios();
                cambiarSigla();
                recuperarTodo(modificacion);
                setIdmodificacion(null);
                cambiaOpcionesFormulario();

            } catch (Exception ex) {
                Logger.getLogger(ExamenModificacionBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        setNavegaPagina(null);
    }

    public void cambiarSigla() {//REVISAR
        switch (valorRadio) {
            case "CN":
                valorSigla = "CN";
                break;
            case "CD":
                valorSigla = "CD";
                break;
            case "ST":
                valorSigla = "ST";
                break;
            case "SF":
                valorSigla = "SF";
                break;
            case "LU":
                valorSigla = "LU";
                break;
            default:
                break;
        }

        switch (regional) {
            case "ALT":
                valorSigla = valorSigla + "-E";
                break;
            case "CBA":
                valorSigla = valorSigla + "-C";
                break;
            case "SCZ":
                valorSigla = valorSigla + "-S";
                break;
            case "TJA":
                valorSigla = valorSigla + "-T";//"-T"
                break;
            case "CHQ":
                valorSigla = valorSigla + "-H";//"-Q"
                break;
            case "ORU":
                valorSigla = valorSigla + "-O";//"-O"
                break;
            default:
                break;
        }
    }

    public void activaCambios() {
        switch (valorRadio) {
            case "CN":
                radioNombre = true;
                radioDomicilio = false;
                radioTransferencia = false;
                radioFusion = false;
                radioLU = false;
                valorSigla = "CN";
                tipoModificacion = EnumTipoModificacion.CAMBIO_NOMBRE.getCodigo();
                listaParticipanteFusion.clear();
                modificacion.setLuinicio(null);
                modificacion.setLu_fin(null);
                modificacion.setNuevo_domicilio(null);
                modificacion.setNueva_nacionalidad(null);
                modificacion.setNuevo_departamento(null);
                productosRendered = false;
                break;
            case "CD":
                radioNombre = false;
                radioDomicilio = true;
                radioTransferencia = false;
                radioFusion = false;
                radioLU = false;
                valorSigla = "CD";
                tipoModificacion = EnumTipoModificacion.CAMBIO_DOMICILIO.getCodigo();
                listaParticipanteFusion.clear();
                modificacion.setLuinicio(null);
                modificacion.setLu_fin(null);
                //  listaNuevoTitular.clear();
                productosRendered = false;
                break;
            case "ST":
                radioNombre = false;
                radioDomicilio = false;
                radioTransferencia = true;
                radioFusion = false;
                radioLU = false;
                valorSigla = "ST";
                tipoModificacion = EnumTipoModificacion.TRANSFERENCIA.getCodigo();
                listaParticipanteFusion.clear();
                modificacion.setLuinicio(null);
                modificacion.setLu_fin(null);
                modificacion.setNuevo_domicilio(null);
                modificacion.setNueva_nacionalidad(null);
                modificacion.setNuevo_departamento(null);
                productosRendered = true;
                break;
            case "SF":
                radioNombre = false;
                radioDomicilio = false;
                radioTransferencia = true;
                radioFusion = true;
                radioLU = false;
                valorSigla = "SF";
                tipoModificacion = EnumTipoModificacion.FUSION.getCodigo();
                modificacion.setLuinicio(null);
                modificacion.setLu_fin(null);
                modificacion.setNuevo_domicilio(null);
                modificacion.setNueva_nacionalidad(null);
                modificacion.setNuevo_departamento(null);
                productosRendered = false;
                break;
            case "LU":
                radioNombre = false;
                radioDomicilio = false;
                radioTransferencia = true;
                radioFusion = false;
                radioLU = true;
                valorSigla = "LU";
                tipoModificacion = EnumTipoModificacion.LICENCIA_DE_USO.getCodigo();
                listaParticipanteFusion.clear();
                listaNuevoTitular.clear();
                modificacion.setNuevo_domicilio(null);
                modificacion.setNueva_nacionalidad(null);
                modificacion.setNuevo_departamento(null);
                productosRendered = true;
                break;
            default:
                radioNombre = false;
                radioDomicilio = false;
                radioTransferencia = false;
                radioFusion = false;
                radioLU = false;
                productosRendered = false;
                break;
        }
        regional = "LPZ";
    }

    public void borrarPersona(ModSolicitanteApoderado modSolicitanteApoderado) {
        listaSolicitanteApoderado.remove(modSolicitanteApoderado);
    }

    public void borrarApoderado(ModSolicitanteApoderado modSolicitanteApoderado) {
        listaApoderado.remove(modSolicitanteApoderado);
    }

    public void borrarNuevoTitular(ModTitularLicenciatarioNuevo titularLicencitarioNuevo) {
        listaNuevoTitular.remove(titularLicencitarioNuevo);
    }

    public void borrartTitularRegistrado(ModTitularLicenciatarioRegistrado titularLicenciatarioRegistrado) {
        if (valorRadio.equals("CD") || valorRadio.equals("CN")) {
            Integer pos = modTitularLicenciatarioRegistradoService.encuentraPosicionListadoTitularRegistrado(listaTitularRegistrado, titularLicenciatarioRegistrado);

            int cont = 0;
            ModTitularLicenciatarioNuevo borrar = new ModTitularLicenciatarioNuevo();
            for (ModTitularLicenciatarioNuevo item : listaNuevoTitular) {
                if (pos == cont) {
                    borrar = item;
                }
                cont++;
            }
            listaNuevoTitular.remove(borrar);
        }
        listaTitularRegistrado.remove(titularLicenciatarioRegistrado);
    }

    public void activaPanelLema() {

        panelLema = tipoMarca.equals("LC");

    }

    /**
     * metodo para crear solicitantes para solicitud de cambio de nombre y
     * domicilio
     *
     * Creado: Susana Escobar Fecha: 05/10/2016
     *
     * @param valorRadio
     * @param objeto
     */
    public void crearModTitularLicenciatarioNuevoCambioNombreDomicilio(String valorRadio, ModTitularLicenciatarioRegistrado objeto) {
        ModTitularLicenciatarioNuevo titularCambioNombre = new ModTitularLicenciatarioNuevo();
        switch (valorRadio) {
            case "CN":

                titularCambioNombre.setTipo_titular(objeto.getTipo_titular());
                titularCambioNombre.setTipo_persona("NTIT");
                titularCambioNombre.setNombre_razonsocial(objeto.getNombre_razonsocial());
                titularCambioNombre.setPrimer_apellido(objeto.getPrimer_apellido());
                titularCambioNombre.setSegundo_apellido(objeto.getSegundo_apellido());
                titularCambioNombre.setModificar(false);
                listaNuevoTitular.add(titularCambioNombre);
                break;
            case "CD":
                titularCambioNombre.setTipo_persona("NTIT");
                titularCambioNombre.setModificar(false);
                titularCambioNombre.setDireccion(objeto.getDireccion());
                listaNuevoTitular.add(titularCambioNombre);
                break;
            default:
                break;
        }

    }

    /**
     * metodo para modificar datos del titular registado sincronizado con datos
     * de nuevo titular, caso cambio de nombre
     *
     * Creado: Susana Escobar Fecha: 05/10/2016
     *
     * @param indice
     * @param objeto
     */
    public void modificaNuevoNombreDomicilio(Integer indice, ModTitularLicenciatarioRegistrado objeto) {

        int cont = 0;
        for (ModTitularLicenciatarioNuevo titularCambioNombre : listaNuevoTitular) {

            if (indice == cont && !titularCambioNombre.getModificar()) {
                switch (valorRadio) {
                    case "CN":
                        //      System.out.println("CN ----------");
                        titularCambioNombre.setTipo_titular(objeto.getTipo_titular());
                        titularCambioNombre.setTipo_persona("NTIT");
                        titularCambioNombre.setNombre_razonsocial(objeto.getNombre_razonsocial());
                        titularCambioNombre.setPrimer_apellido(objeto.getPrimer_apellido());
                        titularCambioNombre.setSegundo_apellido(objeto.getSegundo_apellido());
                        break;
                    case "CD":
                        //    System.out.println("CD ----------");
                        titularCambioNombre.setDireccion(objeto.getDireccion());
                        break;
                    default:
                        break;
                }
            }
            cont++;
        }

    }

    public void addParticipanteFusion() {
        if (!participanteFusion.equals("")) {
            ModTitularLicenciatarioRegistrado nuevo = new ModTitularLicenciatarioRegistrado();
            nuevo.setNombre_razonsocial(participanteFusion);
            nuevo.setTipo_persona_registrado("PFUS");
            nuevo.setEstado("AC");
            listaParticipanteFusion.add(nuevo);
            participanteFusion = "";
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Escriba el nombre del participante de Fusión", ""));
        }

    }

    public void borrarParticipanteFusion(ModTitularLicenciatarioRegistrado item) {
        listaParticipanteFusion.remove(item);
    }

    public String nombreTitular(ModSolicitanteApoderado persona) {

        String nombre = "";
        if (!persona.getTipo_titular().equals(EnumTipoTitular.NATURAL.getCodigo())) {
            nombre = persona.getNombre_razonsocial();
        } else {
            if (persona.getNombre_razonsocial() != null) {
                nombre = persona.getNombre_razonsocial();
            }
            if (persona.getPrimer_apellido() != null) {
                nombre = nombre + "  " + persona.getPrimer_apellido();
            }
            if (persona.getSegundo_apellido() != null) {
                nombre = nombre + "  " + persona.getSegundo_apellido();
            }
        }

        return nombre;
    }

    public String nombreTitularNuevo(ModTitularLicenciatarioNuevo persona) {

        String nombre = "";
        if (!persona.getTipo_titular().equals(EnumTipoTitular.NATURAL.getCodigo())) {
            nombre = persona.getNombre_razonsocial();
        } else {
            if (persona.getNombre_razonsocial() != null) {
                nombre = persona.getNombre_razonsocial();
            }
            if (persona.getPrimer_apellido() != null) {
                nombre = nombre + "  " + persona.getPrimer_apellido();
            }
            if (persona.getSegundo_apellido() != null) {
                nombre = nombre + "  " + persona.getSegundo_apellido();
            }
        }

        return nombre;
    }

    public void borrarDepartamento(ModTitularLicenciatarioNuevo item) {
        if (!item.getPais().equals("BO")) {
            item.setSolicitud_departamento(null);
        }
    }

    public void vaciarTextoObservacionResolucion() {
        textoObservacionResol = null;
    }

    public Boolean[] validaRegistrarCambioNombreDomicilio() {
        Boolean valor = false;
        Boolean valorPais = true;
        Boolean valorNombre = true;
        Boolean[] validador = {valor, valorPais, valorNombre, true};

        for (ModTitularLicenciatarioNuevo item : listaNuevoTitular) {
            // System.out.println("modificar  " + item.getModificar() + "  -----  " + item.getId_referencia());
            if ((item.getId_referencia() != null && item.getId_referencia() != 0)) {
                valor = true;
            }
            if (modificacion.getTipo_modificacion().equals(EnumTipoModificacion.CAMBIO_DOMICILIO.getCodigo())) {
                //  System.out.println("validar   CAMBIO DE NOMBRE" + item.getPais());

                if (item.getPais() != null && !item.getPais().equals("")) {
                    if (item.getPais().equals("BO")) {
                        if (item.getSolicitud_departamento() != null && !item.getSolicitud_departamento().equals("")) {
                            valorPais = true;
                        } else {
                            valorPais = false;

                        }
                    } else {
                        valorPais = true;
                    }

                } else {
                    //System.out.println(" no hay pais  ");
                }
            }
            if (modificacion.getTipo_modificacion().equals(EnumTipoModificacion.CAMBIO_NOMBRE.getCodigo())) {
                if (item.getTipo_titular().equals(EnumTipoTitular.JURIDICO.getCodigo())) {
                    valorNombre = !item.getNombre_razonsocial().equals("");
                }
                if (item.getTipo_titular().equals(EnumTipoTitular.NATURAL.getCodigo())) {
                    //EN fecha 17/04/2017 se encontro error null en esta parte se retiro la validacion de apellido por migrados
                    valorNombre = !item.getNombre_razonsocial().equals("") && item.getNombre_razonsocial() != null;// && (!item.getPrimer_apellido().equals("") || item.getPrimer_apellido() !=null);
                }

            }
        }

        validador[0] = valor;
        validador[1] = valorPais;
        validador[2] = valorNombre;
        validador[3] = valor && valorPais && valorNombre;
        return validador;
    }

    public void muestraMensajesValidadorNombreDomicilio(Boolean[] validador) {
        if (validador[0] == false) {
            if (modificacion.getTipo_modificacion().equals(EnumTipoModificacion.CAMBIO_DOMICILIO.getCodigo())) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Escoja al menos un domicilio para modifcar", ""));
            }
            if (modificacion.getTipo_modificacion().equals(EnumTipoModificacion.CAMBIO_NOMBRE.getCodigo())) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Escoja al menos un nombre para modifcar", ""));
            }

        }
        if (validador[1] == false) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Seleccione un pais (departamento si corresponde) para el nuevo domicilio", ""));
        }
        if (validador[2] == false) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Complete datos del nuevo nombre o razón social", ""));
        }
    }

    public Boolean[] validaSigSignoMarcaPrincipal(ModModificacion modificacion, SigSignoMarca signoMarca, ModResolucion modresolucion) throws Exception {

        Boolean[] validador = {false, false, false, false, true, false, true, true};

        if (modresolucion == null) {
            validador[0] = true;
        } else {
//            if (modresolucion.getNumero_resolucion() != 0 ) {
//                validador[0] = false;
//            } else {
//                validador[0] = true;
//            }
            System.out.println("ES PENDIENTE EN EL VALIDADOR " + modresolucion.getEstado());
            if (modresolucion.getEstado().equals("PE")) {
                // System.out.println("ES PENDIENTE EN EL VALIDADOR " + modresolucion.getEstado());
                validador[0] = true;
            } else {
                validador[0] = false;
            }
        }

        if (signoMarca != null) {

//            String marcasigno = toUpperCase(signoMarca.getSigno());
//            String marcamod = toUpperCase(modificacion.getSigno_registrado());
//            if(marcamod.matches(".*"+marcasigno+".*")){}            
            validador[1] = true;
            // SigSignoClaseNiza sigSignoClaseNiza = sigSignoClaseNizaService.listaSigSignoClaseNizaXidSIgnoMarca(signoMarca.getIdSignoMarca());
            List<SigSignoClaseNiza> listaclase = sigSignoClaseNizaService.obtenerListaSigSignoClazeNizaXIdSignoMarca(signoMarca.getIdSignoMarca());
            SigSignoClaseNiza sigSignoClaseNiza = listaclase.get(0);
            if (modificacion.getIdclase_registrado() == sigSignoClaseNiza.getNumeroClaseNiza().intValue()) {
                validador[2] = true;
            } else {
                validador[2] = true;//MEJORAR EL VALIDADOR DE CLASE IMPORTANTE  *******
            }
        }

        if (modificacion.getEstado_modificacion().equals(EnumEstadoModificacion.ACEPTADA.getCodigo())) {
            validador[3] = true;
        }

        if (modificacion.getTipo_modificacion().equals(EnumTipoModificacion.CAMBIO_DOMICILIO.getCodigo())
                || modificacion.getTipo_modificacion().equals(EnumTipoModificacion.CAMBIO_NOMBRE.getCodigo())) {
            validador[4] = validaRegistrarCambioNombreDomicilio()[3];//3
        }

        validador[5] = !listaNuevoTitular.isEmpty();
        for (ModTitularLicenciatarioNuevo item : listaNuevoTitular) {
            if (item.getIdtitularlicenciatario() == null) {

                validador[6] = false;
            }
        }

        //validar que guarde si cada nuevo titular tiene 
        for (int i = 0; i <= 6; i++) {
            if (!validador[i]) {
                validador[7] = false;
            }
        }

        return validador;
    }

    public void muestraMensajesValidadorSigSignoMarcaPrincipal(Boolean[] validador) {
        if (validador[0] == false) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "La solicitud ya fue registrada", ""));
        }
        if (validador[1] == false) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "No existen datos de la Marca principal", ""));
        }
        if (validador[2] == false) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Los datos de la solicitud no coinciden con datos de la Marca principal", ""));
        }
        if (validador[3] == false) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El estado del tramite deber ser ACEPTADA, no se puede registrar", ""));
        }
        if (validador[4] == false) {
            // FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Escoja al menos un nombre/domicilio para modifcar", ""));
            Boolean[] validadorAux = validaRegistrarCambioNombreDomicilio();
            muestraMensajesValidadorNombreDomicilio(validadorAux);
        }
        if (validador[5] == false) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "La lista de nuevos titulares esta vacia", ""));
        }
        if (validador[6] == false) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Presione el boton Modificar, para guardar cambios en lista de nuevo titular", ""));
        }
    }

    public Long creaSM(String textoSM, Long numeroSM, Long gestionSM, String extension) throws Exception {
        Long sm = 0l;
        //Long extension = new Long(Long.parseLong(extensionSM));
        if (numeroSM != null && gestionSM != null) {
            //sm = numeroSM + gestionSM;
            sm = comunService.codificarCodigoSM(numeroSM.toString(), gestionSM.toString(), extension);
        }
        return sm;
    }

    public Boolean[] validadorSMRegistroPublicacion(Long numero, Long gestion, Long registro, String serie, Long publicacion) {
        Boolean[] validaSMRegistroPublicacion = {false, false, false, false};
        validaSMRegistroPublicacion[0] = numero != null && numero != 0 && gestion != null && gestion != 0;
        validaSMRegistroPublicacion[1] = registro != null && registro != 0 && !serie.equals("");
        validaSMRegistroPublicacion[2] = publicacion != null && publicacion != 0;

        if (validaSMRegistroPublicacion[0] || validaSMRegistroPublicacion[1] || validaSMRegistroPublicacion[2]) {
            validaSMRegistroPublicacion[3] = true;
        }

        return validaSMRegistroPublicacion;
    }

    public void muestraMensajesSMRegistroPublicacion(Boolean[] validaSMRegistroPublicacion) {
        if (validaSMRegistroPublicacion[3] == false) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Escriba al menos el numero de SM, Registro o Publicacion", ""));
        }

    }

    /**
     * metodo validador recorre todas secciones y muestra alertas en caso de
     * datos erroneos o faltantes
     *
     * Creado: Susana Escobar Fecha: 23/09//2016
     *
     */
    public Boolean[] validadorGeneral() {
        validaModificacionResultado = new Boolean[15];
        validaDireccionNotificacionResultado = new Boolean[15];

        Boolean swGeneral = true;
        Boolean[] validaSectoresAux = {false, false, false, false, false, false, false, false, true, true, true, true, swGeneral};

        validaSectoresAux[0] = !listaSolicitanteApoderado.isEmpty();

        validadorSMRegistroPublicacion = validadorSMRegistroPublicacion(numeroSM, gestionSM, modificacion.getNumero_registro(), modificacion.getSerie_registro(), modificacion.getNumero_publicacion());
        validaSectoresAux[1] = validadorSMRegistroPublicacion[3];

        validaSectoresAux[2] = !listaTitularRegistrado.isEmpty();

//        if (!valorRadio.equals("CD")) {
//se quito validacion por desdoblado de datos de nueva direccion en tabla modtitularlicenciatarionuevo
        validaSectoresAux[3] = !listaNuevoTitular.isEmpty();

//        } else {
//            validaSectoresAux[3] = true;
//        }
        if (valorRadio.equals("SF")) {
            validaSectoresAux[4] = listaParticipanteFusion.size() > 1;
        } else {
            validaSectoresAux[4] = true;
        }

        validaSectoresAux[5] = numeroMod != null && gestionMod != null; //VALIDADOR DE NUEMRO DE MODIFICACION POR REGIONAL

        validaModificacionResultado = validadorModificacion(modificacion);
        validaSectoresAux[6] = validaModificacionResultado[7];

        validaDireccionNotificacionResultado = validadorDireccionNotificacion(direccionNotificacion);
        validaSectoresAux[7] = validaDireccionNotificacionResultado[2];

//        if (valorRadio.equals("CD")) {
//
//            validaCambioDomicilioResultado = new Boolean[5];
//            validaCambioDomicilioResultado = validadorCambioDomicilio(modificacion.getNuevo_domicilio(), modificacion.getNueva_nacionalidad(), modificacion.getNuevo_departamento());
//            validaSectoresAux[8] = validaCambioDomicilioResultado[3];
//        } else {
//            validaCambioDomicilioResultado[0] = true;
//            validaCambioDomicilioResultado[1] = true;
//            validaCambioDomicilioResultado[2] = true;
//            validaCambioDomicilioResultado[3] = true;
//        }
        validaSectoresAux[8] = validaNumeroRegional(numeroMod, regional);

        if (valorRadio.equals("LU")) {
            validaSectoresAux[9] = modificacion.getLuinicio() != null;
        }

        //System.out.println("resolucion  " + adicionarResolucion + " gestion " + adicionarGestion + "fecharesol " + adicionarFecha);
        if (adicionarResolucion != null && adicionarGestion != null && adicionarFecha != null) {
            if (modResolucionService.verificaExisteModResolucion(tipoModificacion, adicionarResolucion, adicionarGestion, modificacion.getIdmodificacion())) {
                //System.out.println("EXISTE LA RESOLUCION");
                validaSectoresAux[11] = false;
            }
            //System.out.println("LA RESOLUCION ESTA COMPLETA");
            //adicionalment ?reguntar si ya existe  public Boolean verificaExisteModResolucion(String tipo, Integer numero, Integer gestion);
        } else {
            if (adicionarResolucion != null || adicionarGestion != null || adicionarFecha != null) {
                validaSectoresAux[10] = false;
                //System.out.println("LA RESOLUCION NO ESTA COMPLETA");
            } else {
                if (adicionarResolucion == null && adicionarGestion == null && adicionarFecha == null) {
                    validaSectoresAux[10] = true;
                    //  System.out.println("LA RESOLUCION NO ESTA nno se necesita");
                }
            }
        }

        for (Boolean validaSector : validaSectoresAux) {
            // System.out.println("VALIDA SECTORES  " + validaSector);
            if (validaSector == false) {
                swGeneral = false;
                validaSectoresAux[12] = swGeneral;
            }
        }

        return validaSectoresAux;
    }

    public Boolean validaNumeroRegional(Long numeroMod, String regional) {

        Boolean validador = false;
        String cadena = "";
        String numero = "";
        Integer cont = 0;

        if (numeroMod != null) {
            cadena = numeroMod.toString();
            numero = cadena.substring(0, 1);
            cont = cadena.length();

        }

        switch (regional) {

            case "LPZ":
                validador = true;
                break;

            case "SCZ":
                //2validar el numero dos y seis digitos
                if (numero.equals("2") && cont == 6) {
                    validador = true;
                }
                break;

            case "CBA":
                //1
                if (numero.equals("1") && cont == 6) {
                    validador = true;
                }
                break;

            case "ALT":
                //3
                if (numero.equals("3") && cont == 6) {
                    validador = true;
                }
                break;
            case "CHQ":
                //5
                if (numero.equals("4") && cont == 6) {
                    validador = true;
                }
                break;
            case "TJA":
                //4
                if (numero.equals("5") && cont == 6) {
                    validador = true;
                }
                break;
            case "ORU":
                //6
                if (numero.equals("6") && cont == 6) {
                    validador = true;
                }
                break;
            default:
                validador = false;
                break;

        }
        return validador;
    }

    /**
     * metodo para crear la solicitud de modificacion
     *
     * Creado: Susana Escobar Fecha: 08/09//2016 Modificado: Susana Escobar Paz
     * Fecha: 30/12/2016
     *
     * @throws java.lang.Exception
     */
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public void guardadoGeneral() throws Exception {

        validaSectores = validadorGeneral();
        //     if (swGeneral == 1) {
        if (validaSectores[12]) {
            if (modificacion.getIdmodificacion() == null) {
                // System.out.println("ES PARA CREARRRRRRRRRRR");
                ModModificacion modificacionAux = modModificacionService.buscarModModificacionXCodigo(valorSigla, numeroMod, gestionMod);
                //idUsuario

                if (modificacionAux == null) {
                    //llamar al historial
                    modificacion.setSm(creaSM(textoSM, numeroSM, gestionSM, extensionSM));
                    modificacion.setOficina(regional);
                    modificacion.setTipo_modificacion(valorRadio);
                    modificacion.setSigla(valorSigla);
                    modificacion.setNumero(numeroMod);
                    modificacion.setGestion(gestionMod);
                    modificacion.setTipo_genero_registrado(tipoMarca);
                    modificacion.setTipo_modificacion(tipoModificacion);
                    modificacion.setUsuario(idUsuario);
                    modificacion.setEstado(EnumEstado.ACTIVO.getCodigo());
                    ModModificacion nuevo = modModificacionService.guardar_modificar_listar_ModModificacion(modificacion, 1);
                    idLogTrans = modHistorialService.guardar_ModHistorialCreacionSolicitud(nuevo.getIdmodificacion(), nuevo.getEstado_modificacion(), nuevo.getUbicacion_modificacion(), idUsuario);
                    // crear un unico historial de creacion
                    nuevo.setIdlogtrans(idLogTrans);

                    modificacion = modModificacionService.guardar_modificar_listar_ModModificacion(nuevo, 2);
                    modSolicitanteApoderadoService.guardaListaSolicitantes(listaSolicitanteApoderado, modificacion.getIdmodificacion(), idLogTrans);
                    modSolicitanteApoderadoService.guardaListaSolicitantes(listaApoderado, modificacion.getIdmodificacion(), idLogTrans);
                    modTitularLicencitarioNuevoService.guardaListaNuevoTitular(listaNuevoTitular, modificacion.getIdmodificacion());
                    modTitularLicenciatarioRegistradoService.guardaListaTitularLicenciatarioRegistrado(listaTitularRegistrado, modificacion.getIdmodificacion(), idLogTrans);
                    modTitularLicenciatarioRegistradoService.guardaListaTitularLicenciatarioRegistrado(listaParticipanteFusion, modificacion.getIdmodificacion(), idLogTrans);
                    crearListaTipoSigno();
                    modTipoSignoService.guardar_listaModTipoSigno(lstModTipoSigno, modificacion.getIdmodificacion(), 1, idLogTrans);
                    modDireccionDeNotificacionService.guardar_modificar_listar_ModDireccionDeNotificacion(direccionNotificacion, modificacion.getIdmodificacion(), 1, idLogTrans);
                    tipoSignoDescripcion = sigTipoSignoService.lista_SigTipoSigno_concatenado(EnumPrefijoTablas.MODIFICACION.getCodigo(), modificacion.getIdmodificacion());
                    modResolucionService.adicionarModResolucion(modificacion.getIdmodificacion(), adicionarResolucion, adicionarGestion, adicionarFecha);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Se guardo la solicitud", ""));
                    inicializaTodo();
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "La solicitud ya existe", ""));
                }
            } else {
                //System.out.println("ES PARA MODIFICAR");
                ModModificacion modificacionAux = modModificacionService.buscarModModificacionXCodigo(modificacion.getSigla(), numeroMod, modificacion.getGestion());
                if (modificacionAux != null) {
                    modificacion.setSm(creaSM(textoSM, numeroSM, gestionSM, extensionSM));
                    modificacion.setOficina(regional);
                    modificacion.setTipo_modificacion(valorRadio);
                    modificacion.setSigla(valorSigla);
                    modificacion.setNumero(numeroMod);
                    modificacion.setGestion(gestionMod);
                    modificacion.setTipo_genero_registrado(tipoMarca);
                    modificacion.setTipo_modificacion(tipoModificacion);
                    modificacion.setUsuario(idUsuario);
                    modificacion.setEstado(EnumEstado.ACTIVO.getCodigo());
                    crearListaTipoSigno();
                    idLogTrans = modHistorialService.guardar_ModHistorial(modificacion, direccionNotificacion,
                            listaSolicitanteApoderado, listaApoderado, listaTitularRegistrado, listaNuevoTitular,
                            listaParticipanteFusion, lstModTipoSigno, idUsuario);
                    ModModificacion nuevo = modModificacionService.guardar_modificar_listar_ModModificacion(modificacion, 2);
                    modificacion = nuevo;
                    modSolicitanteApoderadoService.modificarListaSolicitanteApoderado(listaSolicitanteApoderado, modificacion.getIdmodificacion(), EnumTipoPersona.SOLICITANTE.getCodigo(), idLogTrans);

                    modSolicitanteApoderadoService.modificarListaSolicitanteApoderado(listaApoderado, modificacion.getIdmodificacion(), EnumTipoPersona.APODERADO.getCodigo(), idLogTrans);
                    //System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%NUEVO TITULAR%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%   " + listaTitularRegistrado.size());
                    modTitularLicencitarioNuevoService.modificarListaTitularLicenciatarioNuevo(listaNuevoTitular, modificacion.getIdmodificacion());
                    //System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%REGISTRADO%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%   " + listaTitularRegistrado.size());
                    modTitularLicenciatarioRegistradoService.modificarListaTitularLicenciatarioRegistrado(listaTitularRegistrado, modificacion.getIdmodificacion(), "TREG", idLogTrans);
                    //System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%FUSION%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%  " + listaParticipanteFusion.size());
                    modTitularLicenciatarioRegistradoService.modificarListaTitularLicenciatarioRegistrado(listaParticipanteFusion, modificacion.getIdmodificacion(), "PFUS", idLogTrans);
                    modTipoSignoService.guardar_listaModTipoSigno(lstModTipoSigno, modificacion.getIdmodificacion(), 1, idLogTrans);
                    if (direccionNotificacion.getIddirecciondenotificacion() != null) {
                        modDireccionDeNotificacionService.guardar_modificar_listar_ModDireccionDeNotificacion(direccionNotificacion, modificacion.getIdmodificacion(), 2, idLogTrans);
                    } else {
                        modDireccionDeNotificacionService.guardar_modificar_listar_ModDireccionDeNotificacion(direccionNotificacion, modificacion.getIdmodificacion(), 1, idLogTrans);
                    }
                    tipoSignoDescripcion = sigTipoSignoService.lista_SigTipoSigno_concatenado(EnumPrefijoTablas.MODIFICACION.getCodigo(), modificacion.getIdmodificacion());
                    listaNuevoTitular = modTitularLicencitarioNuevoService.listaNuevoTitularXidmodificacion(modificacion.getIdmodificacion());
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Datos Modificados", ""));
                    buscadorGeneral();
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "La solicitud no existe", ""));
                }
            }
        } else {
            //muestraMensajes(validaSectores);
            muestraMensajesSMRegistroPublicacion(validadorSMRegistroPublicacion);
            muestraMensajesModificacion(validaModificacionResultado);
            muestraMensajesDireccionNotificacion(validaDireccionNotificacionResultado);
        }

    }

    /**
     * metodo para crear la solicitud de modificacion
     *
     * Creado: Placido Castro Fecha: 20/10/2017
     *
     * @throws java.lang.Exception
     */
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public void subsanarExpedienteModificacion() throws Exception {

        if (modificacion.getIdmodificacion() != null) {
            // ES PARA MODIFICAR POR SUBSANACION
            ModModificacion modificacionAux = modModificacionService.buscarModModificacionXCodigo(modificacion.getSigla(), numeroMod, modificacion.getGestion());
            if (modificacionAux != null) {
                modificacion.setSm(creaSM(textoSM, numeroSM, gestionSM, extensionSM));
                modificacion.setOficina(regional);
                modificacion.setTipo_modificacion(valorRadio);
                //modificacion.setSigla(valorSigla);
                modificacion.setNumero(numeroMod);
                modificacion.setGestion(gestionMod);
                modificacion.setTipo_genero_registrado(tipoMarca);
                modificacion.setTipo_modificacion(tipoModificacion);
                modificacion.setUsuario(idUsuario);
                modificacion.setEstado(EnumEstado.ACTIVO.getCodigo());
                crearListaTipoSigno();

                modModificacionService.actualizarModificacionPorSubsanacion(modificacion, direccionNotificacion, lstModTipoSigno, idUsuario, formularioPI103);

                FacesContext.getCurrentInstance().addMessage(
                        null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Formulario de modificación subsanado correctamente", ""));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "La solicitud no existe", ""));
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage(FacesMessage.SEVERITY_INFO, "No se pudo subsanar el Formulario", ""));
        }
    }

    public Boolean[] validadorModificacion(ModModificacion miModificacion) {//EL VALORM DEL FINAL DEL 8 CAUSA ERROR
        Boolean[] validaModificacion = {false, false, false, false, false, false, false, true};
        validaModificacion[0] = modificacion.getFecha_ingreso() != null;
        validaModificacion[1] = !"".equals(modificacion.getSigno_registrado());
        validaModificacion[2] = modificacion.getIdclase_registrado() != null;
        //validaModificacion[3] = tipoGeneroSeleccionado.length > 0;   //se solicito dejar libre la validacion del tipo signo
        validaModificacion[3] = true;
        validaModificacion[4] = !"".equals(tipoMarca);
        validaModificacion[5] = !"".equals(modificacion.getEstado_modificacion());
        validaModificacion[6] = !"".equals(modificacion.getUbicacion_modificacion());
        for (int i = 0; i <= 6; i++) {
            // System.out.println("VALIDADOR MODIFICACION  i " + i + "  ---   " + validaModificacion[i]);
            if (!validaModificacion[i]) {
                validaModificacion[7] = false;
            }
        }
        return validaModificacion;
    }

    public void muestraMensajesModificacion(Boolean[] validaModificacion) {
        if (validaModificacion[0] == false) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Seleccione fecha de ingreso de la solicitud", ""));
        }
        if (validaModificacion[1] == false) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Escriba el nombre del signo distintivo", ""));
        }
        if (validaModificacion[2] == false) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Escriba el numero de clase niza", ""));
        }
        if (validaModificacion[3] == false) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Seleccione el tipo de signo.", ""));
        }
        if (validaModificacion[4] == false) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Seleccione el tipo de genero.", ""));
        }
        if (validaModificacion[5] == false) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Seleccione situacion actual.", ""));
        }
        if (validaModificacion[6] == false) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Seleccione ubicacion de la solicitud.", ""));
        }
    }

    public Boolean[] validadorDireccionNotificacion(ModDireccionDeNotificacion miDireccionNotificacion) {
        Boolean[] validaDireccionNotificacion = {false, false, false};
        validaDireccionNotificacion[0] = !miDireccionNotificacion.getCiudad_notificacion().equals("");
        validaDireccionNotificacion[1] = !miDireccionNotificacion.getZona_barrio().equals("");
        validaDireccionNotificacion[2] = validaDireccionNotificacion[0] && validaDireccionNotificacion[1];
        return validaDireccionNotificacion;
    }

    public void muestraMensajesDireccionNotificacion(Boolean[] validaDireccion) {
        if (validaDireccion[0] == false) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Seleccione la ciudad para notificacion", ""));
        }
        if (validaDireccion[1] == false) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Escriba datos de la direccion de notificacion", ""));
        }
    }

    public Boolean[] validadorCambioDomicilio(String domicilio, String pais, String departamento) {
        Boolean[] validaCambioDomicilio = {false, false, true, true};
        validaCambioDomicilio[0] = !"".equals(modificacion.getNuevo_domicilio());
        validaCambioDomicilio[1] = !"".equals(modificacion.getNueva_nacionalidad());
        if (pais.equals("BO")) {
            validaCambioDomicilio[2] = !"".equals(modificacion.getNuevo_departamento());
        }
        for (int i = 0; i <= 2; i++) {
            if (!validaCambioDomicilio[i]) {
                validaCambioDomicilio[3] = false;
            }
        }

        return validaCambioDomicilio;
    }

    public void muestraMensajesCambioDomicilio(Boolean[] validaCambioDomicilio) {
        if (validaCambioDomicilio[0] == false) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Escriba el nuevo domicilio", ""));
        }
        if (validaCambioDomicilio[1] == false) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Seleccione nueva nacionalidad", ""));
        }
        if (validaCambioDomicilio[2] == false) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Seleccione nuevo departamento", ""));
        }
    }

    /**
     * metodo para buscar solicitud de modificacion por sigla, numero y gestion
     *
     * Creado: Susana Escobar Fecha: 16/09//2016
     *
     * @throws java.lang.Exception
     */
    public void buscadorGeneral() throws Exception {
        if (numeroMod != null && gestionMod != null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Buscando", ""));
            modificacion = modModificacionService.buscarModModificacionXCodigo(valorSigla, numeroMod, gestionMod);
            if (modificacion != null) {
//                System.out.println(" IDMODIFICACION  ---------------------     " + modificacion.getIdmodificacion());
                recuperarTodo(modificacion);
                btnObservacionRendered = true;
                linkReasignarRendered = true;
                //mostrarRecepcionarFinalizar();
                cambiaOpcionesFormulario();
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "La Solicitud no existe", ""));
                modificacion = new ModModificacion();
                //inicializaTodo();
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Escriba el numero y gestion de la solicitud", ""));
        }
    }

    /**
     * metodo para desplegar en la vista todos los datos recogidos con el
     * buscador de solicitudes de modificacion
     *
     * Creado: Susana Escobar Fecha: 17/09//2016
     *
     * @param miModificacion
     * @throws java.lang.Exception
     */
    public void recuperarTodo(ModModificacion miModificacion) throws Exception {
        textoOtro = "";
        txtOtroRendered = false;
        if (miModificacion.getSm() != null && miModificacion.getSm() != 0) {
            HashMap resultado = comunService.obtenerNumeroGestionExtensionCodigoSM(miModificacion.getSm());
            numeroSM = Long.parseLong(resultado.get("numero").toString());
            //String numero = String.format("%6s", resultado.get("numero")).replace(' ', '0');
            //numeroSM = Long.parseLong(numero);
            gestionSM = Long.parseLong(resultado.get("gestion").toString());
            extensionSM = resultado.get("extension").toString();
        } else {
            numeroSM = null;
            gestionSM = null;
            extensionSM = "";
        }
        regional = modificacion.getOficina();
        numeroMod = modificacion.getNumero();
        gestionMod = modificacion.getGestion();
        tipoMarca = modificacion.getTipo_genero_registrado();
        direccionNotificacion = modDireccionDeNotificacionService.buscarModDireccionDeNotificacionXidmodificacion(miModificacion.getIdmodificacion());
        listaSolicitanteApoderado = modSolicitanteApoderadoService.listadoSolicitanteXidmodificacion(miModificacion.getIdmodificacion());
        listaApoderado = modSolicitanteApoderadoService.listadoApoderadoXidmodificacion(miModificacion.getIdmodificacion());
        listaNuevoTitular = modTitularLicencitarioNuevoService.listaNuevoTitularXidmodificacion(miModificacion.getIdmodificacion());
        listaTitularRegistrado = modTitularLicenciatarioRegistradoService.listaTitularRegistradoXidmodificacion(miModificacion.getIdmodificacion());
        listaParticipanteFusion = modTitularLicenciatarioRegistradoService.listaTitularFusionXidmodificacion(miModificacion.getIdmodificacion());
        lstModTipoSigno = modTipoSignoService.listado_modtiposignoXidmodificacion(miModificacion.getIdmodificacion());
        tipoGeneroSeleccionado = new String[10];
        int i = 0;
        for (ModTipoSigno item : lstModTipoSigno) {
            tipoGeneroSeleccionado[i] = item.getTipo_signo();
            if (item.getTipo_signo().equals("OTRO")) {
                textoOtro = item.getDescripcion_otro();
                txtOtroRendered = true;
            }
            i++;
        }
        if (!lstModTipoSigno.isEmpty()) {
            tipoSignoDescripcion = sigTipoSignoService.lista_SigTipoSigno_concatenado(EnumPrefijoTablas.MODIFICACION.getCodigo(), miModificacion.getIdmodificacion());
        } else {
            tipoSignoDescripcion = "Elegir";
        }

        modresolucion = modResolucionService.buscar_ModResolucionXidmodificacion(miModificacion.getIdmodificacion());
        if (modresolucion == null) {
            modresolucion = modResolucionService.buscar_ModResolucionXidmodificacionPendiente(miModificacion.getIdmodificacion());
        }
        if (modresolucion != null) {
            if (modresolucion.getNumero_resolucion() != 0) {
                resolucion = modresolucion.getNumero_resolucion();
                gestion = modresolucion.getGestion_resolucion();
                fechaResolucion = modresolucion.getFecha_resolucion();
                textoObservacionResol = modresolucion.getObservacion_resolucion();
                panelSeleccionarIdReferencia = false;
                // btnRegistrarDisabled = true;
            } else {
                modresolucion = new ModResolucion();
                resolucion = null;
                gestion = null;
                fechaResolucion = null;
                //btnRegistrarDisabled = false;
            }
        } else {
            resolucion = null;
            gestion = null;
            fechaResolucion = null;
            panelSeleccionarIdReferencia = true;
        }

        if (modresolucion != null) {
            if (modresolucion.getEstado().equals("PE")) {
                panelSeleccionarIdReferencia = true;
            }
        }
        activaPanelLema();
        observacionTramite = observacionTramiteService.obtenerUltimaObservacionTramite(EnumPrefijoTablas.MODIFICACION.getCodigo(), modificacion.getIdmodificacion());

        signoMarca = sigSignoMarcaService.buscar_SigSignoMarca_sm_publicacion_registro_or(modificacion.getSm(),
                modificacion.getNumero_publicacion(), modificacion.getNumero_registro(), modificacion.getSerie_registro(), modificacion.getSigno_registrado());
        if (signoMarca != null) {
            lstTitularActual = sigSolicitanteApoderadoService.listadoSolicitanteXidsignomarca(signoMarca.getIdSignoMarca());
        } else {
            lstTitularActual = new ArrayList<SigSolicitanteApoderado>();
            panelSeleccionarIdReferencia = false;
        }
        mostrarRecepcionarFinalizar();
    }

    public void limpiarExamenModificacion() {
        valorOpcionesRadio = "CON";
        cambiaOpcionesFormulario();
        formularioPI103 = new FormularioPI103();
        listaModSolicitantesSipi = new ArrayList<>();
        listaModApoderadosSipi = new ArrayList<>();
        direccionNotificacionSipi = new DireccionNotificaciones();
        listaTitularRegistradoSipi = new ArrayList<>();
        listaNuevoTitularSipi = new ArrayList<TitularLicenciatariosNuevos>();
        signoDistintivoExt = "";
        valorClaseNizaExt = null;
        panelLema = false;
        tipoMarca = "";
        listaSolicitanteApoderado = new ArrayList<ModSolicitanteApoderado>();
        listaApoderado = new ArrayList<ModSolicitanteApoderado>();
        listaNuevoTitular = new ArrayList<ModTitularLicenciatarioNuevo>();
        listaTitularRegistrado = new ArrayList<ModTitularLicenciatarioRegistrado>();
        modSolicitanteApoderadoSelect = new ModSolicitanteApoderado();
        titularLicencitarioNuevoSelect = new ModTitularLicenciatarioNuevo();
        titularLicenciatarioRegistradoSelect = new ModTitularLicenciatarioRegistrado();
        participanteFusion = "";
        listaParticipanteFusion = new ArrayList<ModTitularLicenciatarioRegistrado>();
        modificacion = new ModModificacion();
        textoSM = "SM";
        numeroSM = null;
        gestionSM = null;
        direccionNotificacion = new ModDireccionDeNotificacion();
        numeroMod = null;
        gestionMod = null;
        txtOtroRendered = false;
        textoOtro = "";
        resolucion = null;
        gestion = null;
        fechaResolucion = null;
        panelSeleccionarIdReferencia = false;
        observacionTramite = new ObservacionTramite();
        tipoGeneroSeleccionado = new String[5];
        tipoSignoDescripcion = "Elegir";

        registroExpediente = null;
        serieRegExpediente = "";
        renovacionExpediente = null;
        serieRenoExpediente = "";
        smExpediente = null;
        gestionExpediente = null;
        extensionExpediente = null;
        nroPublicacionExpediente = null;
        textoObservacionResol = null;
    }

    public void inicializarFormulario() {
        valorOpcionesRadio = "ADD";
        cambiaOpcionesFormulario();
        panelLema = false;
        tipoMarca = "";
        listaSolicitanteApoderado = new ArrayList<ModSolicitanteApoderado>();
        listaApoderado = new ArrayList<ModSolicitanteApoderado>();
        listaNuevoTitular = new ArrayList<ModTitularLicenciatarioNuevo>();
        listaNuevoTitularSipi = new ArrayList<TitularLicenciatariosNuevos>();
        listaTitularRegistrado = new ArrayList<ModTitularLicenciatarioRegistrado>();
        listaTitularRegistradoSipi = new ArrayList<TitularLicenciatarios>();
        modSolicitanteApoderadoSelect = new ModSolicitanteApoderado();
        titularLicencitarioNuevoSelect = new ModTitularLicenciatarioNuevo();
        titularLicenciatarioRegistradoSelect = new ModTitularLicenciatarioRegistrado();
        participanteFusion = "";
        listaParticipanteFusion = new ArrayList<ModTitularLicenciatarioRegistrado>();
        modificacion = new ModModificacion();
        textoSM = "SM";
        numeroSM = null;
        gestionSM = null;
        direccionNotificacion = new ModDireccionDeNotificacion();
        numeroMod = null;
        gestionMod = null;
        txtOtroRendered = false;
        textoOtro = "";
        resolucion = null;
        gestion = null;
        fechaResolucion = null;
        panelSeleccionarIdReferencia = false;
        observacionTramite = new ObservacionTramite();
        tipoGeneroSeleccionado = new String[5];
        tipoSignoDescripcion = "Elegir";
    }

    /**
     * metodo para inicializar vista formulario de modificaciones
     *
     * Creado: Susana Escobar Fecha: 17/09//2016
     *
     */
    public void inicializaTodo() {
        textoOtro = "";
        textoSM = "";
        numeroSM = null;
        gestionSM = null;
        extensionSM = "";
        radioNombre = true;
        radioDomicilio = false;
        radioTransferencia = false;
        radioFusion = false;
        radioLU = false;
        panelLema = false;
        valorRadio = "CN";
        valorSigla = "CN";
        tipoMarca = "";
        listaSolicitanteApoderado = new ArrayList<ModSolicitanteApoderado>();
        listaApoderado = new ArrayList<ModSolicitanteApoderado>();
        listaNuevoTitular = new ArrayList<ModTitularLicenciatarioNuevo>();
        listaNuevoTitularSipi = new ArrayList<TitularLicenciatariosNuevos>();
        listaTitularRegistrado = new ArrayList<ModTitularLicenciatarioRegistrado>();
        listaTitularRegistradoSipi = new ArrayList<TitularLicenciatarios>();
        modSolicitanteApoderadoSelect = new ModSolicitanteApoderado();
        titularLicencitarioNuevoSelect = new ModTitularLicenciatarioNuevo();
        titularLicenciatarioRegistradoSelect = new ModTitularLicenciatarioRegistrado();
        participanteFusion = "";
        listaParticipanteFusion = new ArrayList<ModTitularLicenciatarioRegistrado>();
        modificacion = new ModModificacion();
        regional = "LPZ";
        textoSM = "SM";
        numeroSM = null;
        gestionSM = null;

        direccionNotificacion = new ModDireccionDeNotificacion();
        numeroMod = null;
        gestionMod = null;
        tipoModificacion = EnumTipoModificacion.CAMBIO_NOMBRE.getCodigo();
        extensionSM = "";
        nroPublicacionExpediente = null;
        registroExpediente = null;
        serieRegExpediente = "";
        serieRenoExpediente = "";
        renovacionExpediente = null;
        smExpediente = null;
        gestionExpediente = null;
        extensionExpediente = null;
        lstModTipoSigno = new ArrayList<ModTipoSigno>();
        tipoGeneroSeleccionado = null;
        modresolucion = new ModResolucion();
        txtOtroRendered = false;
        signoMarca = null;
        cambiaOpcionesFormulario();
        adicionarResolucion = null;
        adicionarGestion = null;
        resolucion = null;
        gestion = null;
        esRevocatoria = false;
    }

    public void cambiaOpcionesFormulario() {

        //  inicializaTodo();
        switch (valorOpcionesRadio) {
            case "ADD":
                btnAdicionarDisabled = false;
                btnModificarDisabled = true;
                btnObservacionRendered = false;
                btnRegistrarDisabled = true;
                botonRecepcionarRendered = false;
                panelSeleccionarIdReferencia = false;
                resolucionDisabled = false;
                break;
            case "MOD":
                btnAdicionarDisabled = true;
                btnModificarDisabled = false;
                btnObservacionRendered = true;
                btnRegistrarDisabled = modificacion.getIdmodificacion() == null;
                botonRecepcionarRendered = true;
                panelSeleccionarIdReferencia = true;
                resolucionDisabled = true;
                break;
            case "CON":
                btnAdicionarDisabled = true;
                btnModificarDisabled = true;
                btnObservacionRendered = true;
                btnRegistrarDisabled = true;
                //    botonRecepcionarRendered = false;
                panelSeleccionarIdReferencia = false;
                resolucionDisabled = true;
                break;
            default:
                break;
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

    public String devuelveTipoDocumento(String codigo) throws Exception {
        if (codigo != null && !codigo.equals("")) {
            return dominioService.obtenerListadoDominioXCodigo(EnumNombreDominio.TIPO_DOCUMENTO.getCodigo(), codigo).get(0).getNombre();
        }
        return "";
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

    public String devuelveTipoModificacion() throws Exception {
        try {
            if (tipoModificacion != null && !tipoModificacion.equals("")) {
                return dominioService.obtenerListadoDominioXCodigo(EnumNombreDominio.TIPO_MODIFICACION.getCodigo(), tipoModificacion).get(0).getNombre();
            }
            return "";
        } catch (Exception e) {
            return "";
        }
    }

    public String devuelveDepartamento(String codigo) throws Exception {

        if (codigo != null && !codigo.equals("")) {
            return dominioService.obtenerListadoDominioXCodigo(EnumNombreDominio.LUGAR_EXPEDICION.getCodigo(), codigo).get(0).getNombre();
        }
        return "";
    }

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

    public void buscarXPublicacion() throws Exception {
        inicializarFormulario();
        if (nroPublicacionExpediente != null) {

            SigSignoMarca encontradoSigno = sigSignoMarcaService.listaSigSignoMarcaXPublicacion(nroPublicacionExpediente);
            mostrarDatosEncontrados(encontradoSigno);
        }
    }

    public void buscarXRenovacion() throws Exception {
        inicializarFormulario();
        if (renovacionExpediente != null) {

            SigSignoMarca encontradoSigno = sigSignoMarcaService.listaSigSignoMarcaXRenovacion(renovacionExpediente, serieRenoExpediente);
            mostrarDatosEncontrados(encontradoSigno);
        }
    }

    public void buscarXRegistro() throws Exception {
        inicializarFormulario();
        if (registroExpediente != null) {

            SigSignoMarca encontradoSigno = sigSignoMarcaService.listaSigSignoMarcaXRegistro(registroExpediente, serieRegExpediente, ""); //LIMIT 1 A LA CONSULTA
            mostrarDatosEncontrados(encontradoSigno);
        }
    }

    public void buscarXSM() throws Exception {
        inicializarFormulario();
        if (extensionExpediente == null) {
            extensionExpediente = "";
        }

        if (smExpediente != null && gestionExpediente != null) {//&& gestionExpediente != null
            Long expediente = creaSM("SM", smExpediente, gestionExpediente, extensionExpediente);
            SigSignoMarca encontradoSigno = sigSignoMarcaService.listaSigSignoMarcaXSM(expediente);
            mostrarDatosEncontrados(encontradoSigno);
        }
    }

    /**
     * metodo para desplegar en la vista todos los datos recogidos con el
     * buscador superior derecho accionado por evento blur
     *
     * Creado: Susana Escobar Fecha: 26/09//2016
     *
     * @param encontrado
     * @throws java.lang.Exception
     */
    public void mostrarDatosEncontrados(SigSignoMarca encontrado) throws Exception {
        modificacion = new ModModificacion();
        if (encontrado != null) {
            if (encontrado.getSm() != null && encontrado.getSm() != 0) {
                HashMap resultado = comunService.obtenerNumeroGestionExtensionCodigoSM(encontrado.getSm());
                numeroSM = Long.parseLong(resultado.get("numero").toString());
                gestionSM = Long.parseLong(resultado.get("gestion").toString());
                extensionSM = resultado.get("extension").toString();
            }
            //     System.out.println("encontrado SMSMSMSM " + numeroSM);
            modificacion.setSm(encontrado.getSm());
            modificacion.setNumero_registro(encontrado.getNumeroRegistro());
            modificacion.setSerie_registro(encontrado.getSerieRegistro());
            if (encontrado.getNumeroRenovacion() != null) {
                modificacion.setNumero_renovacion((long) encontrado.getNumeroRenovacion());
            }
            modificacion.setSerie_renovacion(encontrado.getExtensionRenovacion());
            modificacion.setNumero_publicacion(encontrado.getNumeroPublicacion());
            modificacion.setSigno_registrado(encontrado.getSigno());
            //SigSignoClaseNiza sigSignoClaseNiza = sigSignoClaseNizaService.listaSigSignoClaseNizaXidSIgnoMarca(encontrado.getIdSignoMarca());
            List<SigSignoClaseNiza> listaclase = sigSignoClaseNizaService.obtenerListaSigSignoClazeNizaXIdSignoMarca(encontrado.getIdSignoMarca());
            SigSignoClaseNiza sigSignoClaseNiza = listaclase.get(0);
            modificacion.setIdclase_registrado(sigSignoClaseNiza.getNumeroClaseNiza());
            modificacion.setLista_producto(sigSignoClaseNiza.getListaProducto());
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
            tipoSignoDescripcion = sigTipoSignoService.lista_SigTipoSigno_concatenado(EnumPrefijoTablas.SIGNO.getCodigo(), encontrado.getIdSignoMarca());

            List<SigSolicitanteApoderado> listaSolicitanteApoderadoSig = sigSolicitanteApoderadoService.listadoSolicitanteXidsignomarca(encontrado.getIdSignoMarca());
            for (SigSolicitanteApoderado item : listaSolicitanteApoderadoSig) {
                try {
                    ModTitularLicenciatarioRegistrado titularRegistrado = new ModTitularLicenciatarioRegistrado();
                    //String nombre = devuelveNombreJuridicoONatural(item.getNombreRazonSocial(), item.getPrimerApellido(), item.getSegundoApellido());
                    titularRegistrado.setNombre_razonsocial(item.getNombreRazonSocial());
                    titularRegistrado.setPrimer_apellido(item.getPrimerApellido());
                    titularRegistrado.setSegundo_apellido(item.getSegundoApellido());
                    if (item.getDireccion() != null) {
                        titularRegistrado.setDireccion(item.getDireccion());
                    } else {
                        titularRegistrado.setDireccion("");
                    }
                    titularRegistrado.setTipo_titular(item.getTipoTitular());
                    titularRegistrado.setTipo_persona_registrado("TREG");
                    listaTitularRegistrado.add(titularRegistrado);
                    // crearModTitularLicenciatarioNuevoCambioNombreDomicilio(valorRadio, titularRegistrado);

                } catch (Exception ex) {
                    Logger.getLogger(ExamenModificacionBean.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
            }
            //QUEDA PENDIENTE RECUPERAR SOLICITANTE Y APODERADO PREGUNTAR SI SERA NECESARIO
        }
        // inicializaTodo();

    }

    public void muestraOtro() {
        for (String s : tipoGeneroSeleccionado) {
            //  System.out.println(" *** " + s);
            if (s.equals("OTRO")) {
                txtOtroRendered = true;
                //textoOtro="";
            } else {
                txtOtroRendered = false;
            }
        }
    }

    public List<ModTipoSigno> crearListaTipoSigno() {
        lstModTipoSigno.clear();
        for (String s : tipoGeneroSeleccionado) {
            ModTipoSigno tipoSigno = new ModTipoSigno();
            tipoSigno.setTipo_signo(s);
            if (s.equals("OTRO")) {
                tipoSigno.setDescripcion_otro(textoOtro);
            }
            lstModTipoSigno.add(tipoSigno);
        }
        return lstModTipoSigno;
    }

    public void mostrarRecepcionarFinalizar() {
        ultimoSeguimiento = seguimientoService.ultimo_Seguimiento_etapa(EnumPrefijoTablas.MODIFICACION.getCodigo(), modificacion.getIdmodificacion(), listEtapaUs.get(0).getTipoEtapa());

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

        ultimoSeguimiento = seguimientoService.ultimo_Seguimiento_etapa(EnumPrefijoTablas.MODIFICACION.getCodigo(), modificacion.getIdmodificacion(), "RERE");
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

        ultimoSeguimiento = seguimientoService.ultimo_Seguimiento_etapa(EnumPrefijoTablas.MODIFICACION.getCodigo(), modificacion.getIdmodificacion(), listEtapaUs.get(0).getTipoEtapa());
        if (ultimoSeguimiento != null) {
            if (ultimoSeguimiento.getFechaFin() != null) {
                if (ultimoSeguimiento.getEtapa().equals("DESG") || ultimoSeguimiento.getEtapa().equals("ENTR") || ultimoSeguimiento.getEtapa().equals("PAFI")) //botonRecepcionarDisabled = false;
                {
                    botonFinalizarDisabled = true;
                }
            }
        }
    }

    public void abrirFinalizar() throws Exception {
        fechaServidor = comunService.obtenerFechaHoraServidor(1l);
        Seguimiento seguimientoNuevo = new Seguimiento();
        seguimientoNuevo.setId(modificacion.getIdmodificacion());
        seguimientoNuevo.setIdUsuario((idUsuario));
        //seguimientoNuevo.setIdLogTrans(1L);
        seguimientoNuevo.setSm(modificacion.getSm());
        seguimientoNuevo.setNumeroPublicacion(modificacion.getNumero_publicacion());
        seguimientoNuevo.setNumeroRegistro(modificacion.getNumero_registro());
        seguimientoNuevo.setSerieRegistro(modificacion.getSerie_registro());
        if (esRevocatoria) {
            seguimientoNuevo.setEtapa("RERE");
            seguimientoNuevo.setPlazo_limite(flujoTiempoService.obtieneFlujoXIdetapa("RERE", 1));

        } else {
            seguimientoNuevo.setEtapa(listEtapaUs.get(0).getTipoEtapa());
            seguimientoNuevo.setPlazo_limite(flujoTiempoService.obtieneFlujoXIdetapa(listEtapaUs.get(0).getTipoEtapa(), 1));
        }

        seguimientoNuevo.setFechaFin(fechaServidor);
        seguimientoNuevo.setObservacion(null);
        seguimientoNuevo.setEditable(false);
        seguimientoNuevo.setEstado(EnumEstado.ACTIVO.getCodigo());
        //ultimoSeguimiento = seguimientoService.ultimo_Seguimiento_etapa(EnumPrefijoTablas.MODIFICACION.getCodigo(), modificacion.getIdmodificacion(), "EXMO");
        ultimoSeguimiento = seguimientoService.guardar_modificar_Seguimiento_etapa(seguimientoNuevo, "MOD");
        modificacion.setUsuario(idUsuario);

        String descripcionUbicacion = dominioService.obtenerNombreCodigoDominio("ubicacion_modificacion", modificacion.getUbicacion_modificacion());
        if (super.getIdEtapaSession() == 18) {
            modificacion.setUbicacion_modificacion("VEEM");
            historialService.guardarHistorialGenerico(EnumPrefijoTablas.MODIFICACION.getCodigo(), modificacion.getIdmodificacion(), idUsuario, "DATOS ADMINISTRATIVOS",
                    EnumOperacion.MODIFICAR.getCodigo(), dominioService.obtenerNombreCodigoDominio("estado_modificacion", modificacion.getEstado_modificacion()),
                    null, dominioService.obtenerNombreCodigoDominio("ubicacion_modificacion", modificacion.getUbicacion_modificacion()), "[Ubic.:" + descripcionUbicacion + "]");
        }
        if (super.getIdEtapaSession() == 20) {
            modificacion.setUbicacion_modificacion("BIBM");
            historialService.guardarHistorialGenerico(EnumPrefijoTablas.MODIFICACION.getCodigo(), modificacion.getIdmodificacion(), idUsuario, "DATOS ADMINISTRATIVOS",
                    EnumOperacion.MODIFICAR.getCodigo(), dominioService.obtenerNombreCodigoDominio("estado_modificacion", modificacion.getEstado_modificacion()),
                    null, dominioService.obtenerNombreCodigoDominio("ubicacion_modificacion", modificacion.getUbicacion_modificacion()), "[Ubic.:" + descripcionUbicacion + "]");
        }
        if (super.getIdEtapaSession() == 21) {
            modificacion.setUbicacion_modificacion("PIDM");
            historialService.guardarHistorialGenerico(EnumPrefijoTablas.MODIFICACION.getCodigo(), modificacion.getIdmodificacion(), idUsuario, "DATOS ADMINISTRATIVOS",
                    EnumOperacion.MODIFICAR.getCodigo(), dominioService.obtenerNombreCodigoDominio("estado_modificacion", modificacion.getEstado_modificacion()),
                    null, dominioService.obtenerNombreCodigoDominio("ubicacion_modificacion", modificacion.getUbicacion_modificacion()), "[Ubic.:" + descripcionUbicacion + "]");
        }
        modModificacionService.guardar_modificar_listar_ModModificacion(modificacion, 2);
        mostrarRecepcionarFinalizar();

    }

    public void anularResolucion() {
        if (modresolucion != null) {
            modResolucionService.historial_ModResolucion_anulado(modresolucion, idUsuario, modificacion);
            modresolucion.setEstado(EnumEstado.NO_ACTIVO.getCodigo());
            modResolucionService.guardar_modificar_listar_ModResolucion(modresolucion, 2);
            modresolucion = null;
            resolucion = null;
            gestion = null;
            fechaResolucion = null;

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Los datos de resolucion fueron anulados", ""));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "La solicitud no tiene resolucion", ""));
            resolucion = null;
            gestion = null;
            fechaResolucion = null;
        }
    }

    public void asignarResolucion() {
        //System.out.println("VALORES  " + resolucion + " gestion  " + gestion + "  fecha  " + fechaResolucion);
        if (fechaResolucion == null) {
            try {
                fechaResolucion = comunService.obtenerFechaHoraServidor(1l);

            } catch (Exception ex) {
                Logger.getLogger(ExamenModificacionBean.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
        ModResolucion anulado = modResolucionService.lista_ModResolucionAnulado(tipoModificacion, resolucion, gestion, modificacion.getIdmodificacion());

        modresolucion = modResolucionService.buscar_ModResolucionXidmodificacion(modificacion.getIdmodificacion());
        if (anulado != null && modResolucionService.verificaExisteModResolucion(tipoModificacion, resolucion, gestion, modificacion.getIdmodificacion())) {

            ModResolucion asignado = modResolucionService.asignacionManualModResolucion(modificacion.getIdmodificacion(), modresolucion, anulado, fechaResolucion);
            modresolucion = modResolucionService.buscar_ModResolucionXidmodificacionPendiente(modificacion.getIdmodificacion());

        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El numero y gestion no estan disponibles para asignar", ""));
            resolucion = null;
            gestion = null;
            fechaResolucion = null;
        }

    }

    public void activaCampoDomicilio(Boolean valor) {
        // System.out.println("metodoooo   " + valor);
    }

    public Boolean muestrPanelCambioNombreDomicilio() {
        return lstTitularActual.size() > 1;
    }

    /**
     * Metodo para generar el nombre corto del usuario logueado.
     *
     * Creado: Ruben Ramirez Fecha: 16/01/2017
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

    public void resolucionAntigua() {
        tipoResolucion = "antigua";
    }

    public void resolucionNueva() {
        tipoResolucion = "nueva";
    }

    /**
     *
     * Metodo que realiza la busque de un formulario de acuerdo a su tramite
     *
     * Creado: Placido Castro Fecha: 25/09/2017
     */
    public void buscarFormularioExterno() {
        if (!numeroFormulario.trim().equalsIgnoreCase("")) {

            HashMap mapResultado = new HashMap();
            formularioPI103 = new FormularioPI103();

            try {
                mapResultado = this.formularioService.obtenerNumeroFormularioSubsanacion(numeroFormulario, modificacion.getNro_formulario());

                if (mapResultado.get("continuarFlujo").equals("true")) {

                    if (mapResultado.get("estado").equals("AC")) {

                        //obtener el o los formularios dependiendo
                        if (mapResultado.get("tipoFormulario").equals(EnumTipoFormulario.REGISTRO_MODIFICACION.getCodigo())) {
                            this.panelPI103 = Boolean.TRUE;
                            this.panelEncontrado = Boolean.FALSE;
                            this.panelNoEncontrado = Boolean.FALSE;
                            generarFormularioPI103(mapResultado.get("idFormulario").toString());
                        } else {
                            FacesContext.getCurrentInstance().addMessage(
                                    null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Nro Formulario no corresponde a un trámite PI103", ""));
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
                    limpiarExamenModificacion();
                }

            } catch (Exception ex) {
                Logger.getLogger(IngresoSolicitudTramiteBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {

            this.panelNoEncontrado = Boolean.TRUE;
            FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Introduzca Nro Formulario", ""));
        }
    }

    /**
     * Método que permite generar el FormularioPI103
     *
     * creado: Placido Castro Fecha: 20/11/2017
     *
     * @param idFormulario
     */
    public void generarFormularioPI103(String idFormulario) {

        try {
            formularioPI103 = this.formularioPI103Service.obtenerDatosFormularioPI103(Long.parseLong(idFormulario));
            this.oficina = formularioPI103.getDirecciones().get(0).getCiudadNotificacion();
            this.modificacionSipi = formularioPI103.getModificaciones();
            this.valorTipoGenero = formularioPI103.getModificaciones().getTipoGeneroRegistrado();
            this.listaModSolicitantesSipi = formularioPI103.getSolicitantes();
            this.listaModApoderadosSipi = formularioPI103.getApoderados();
            this.listaTitularRegistradoSipi = formularioPI103.getTitularLicenciatarios();
            this.listaNuevoTitularSipi = formularioPI103.getTiTularLicenciatarioNuevos();

            this.direccionNotificacionSipi = formularioPI103.getDirecciones().get(0);

            this.signoDistintivoExt = formularioPI103.getModificaciones().getSignoRegistro();
            this.valorClaseNizaExt = Long.valueOf(formularioPI103.getModificaciones().getClaseRegistrado());

            this.tipoMarca = formularioPI103.getModificaciones().getTipoGeneroRegistrado();
            this.tipoSignoDescripcion = concatenaLabelTiposignoSubsanacion(formularioPI103.getModTipoSignos());
            this.versionClaseNiza = "11";

        } catch (Exception ex) {
            Logger.getLogger(IngresoSolicitudTramiteBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    String concatenaLabelTiposignoSubsanacion(List<ModTipoSignos> listaTipoSigno) throws Exception {
        String cadenaString = "Ninguna";

        for (ModTipoSignos renTipoSigno : listaTipoSigno) {
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

    public void obtenerParametrosBusquedaExpedienteModificacion() {
        try {

            Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
            String ParIdModificacion = (String) (params.get("ThrEimhaJd5"));
            String parIdUsuario = (String) (params.get("UkYJ0g3jLwc"));
            if (ParIdModificacion != null && parIdUsuario != null) {
                if (!ParIdModificacion.trim().equals("") && !parIdUsuario.trim().equals("") && !ParIdModificacion.equals("null") && !parIdUsuario.equals("null")) {
                    setIdUsuarioSession(Long.parseLong(parIdUsuario));
                    //setidModificacionSession(Long.parseLong(idModificacion));
                    setIdmodificacion(Long.parseLong(ParIdModificacion));
                }
            }
        } catch (Exception e) {
            Logger.getLogger(ExamenModSubsanacionBean.class
                    .getName()).log(Level.SEVERE, null, e);
        }
    }

    //<editor-fold defaultstate="collapsed" desc="-----------------------------GET  SET----------------------">
    public Boolean getRadioNombre() {
        return radioNombre;
    }

    public void setRadioNombre(Boolean radioNombre) {
        this.radioNombre = radioNombre;
    }

    public String getValorRadio() {
        return valorRadio;
    }

    public void setValorRadio(String valorRadio) {
        this.valorRadio = valorRadio;
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

    public String getValorSigla() {
        return valorSigla;
    }

    public void setValorSigla(String valorSigla) {
        this.valorSigla = valorSigla;
    }

    public Boolean getPanelLema() {
        return panelLema;
    }

    public void setPanelLema(Boolean panelLema) {
        this.panelLema = panelLema;
    }

    public String getTipoMarca() {
        return tipoMarca;
    }

    public void setTipoMarca(String tipoMarca) {
        this.tipoMarca = tipoMarca;
    }

    public Integer getResolucion() {
        return resolucion;
    }

    public void setResolucion(Integer resolucion) {
        this.resolucion = resolucion;
    }

    public Integer getGestion() {
        return gestion;
    }

    public void setGestion(Integer gestion) {
        this.gestion = gestion;
    }

    public Date getFechaResolucion() {
        return fechaResolucion;
    }

    public void setFechaResolucion(Date fechaResolucion) {
        this.fechaResolucion = fechaResolucion;
    }

    public List<Dominio> getLstOficina() {
        return lstOficina;
    }

    public void setLstOficina(List<Dominio> lstOficina) {
        this.lstOficina = lstOficina;
    }

    public List<Dominio> getListaPais() {
        return listaPais;
    }

    public void setListaPais(List<Dominio> listaPais) {
        this.listaPais = listaPais;
    }

    public List<Dominio> getListaLugarExpedicion() {
        return listaLugarExpedicion;
    }

    public void setListaLugarExpedicion(List<Dominio> listaLugarExpedicion) {
        this.listaLugarExpedicion = listaLugarExpedicion;
    }

    public DominioService getDominioService() {
        return dominioService;
    }

    public void setDominioService(DominioService dominioService) {
        this.dominioService = dominioService;
    }

    public RegionalService getRegionalService() {
        return regionalService;
    }

    public void setRegionalService(RegionalService regionalService) {
        this.regionalService = regionalService;
    }

    public List<persona> getLstPersonas() {
        return lstPersonas;
    }

    public void setLstPersonas(List<persona> lstPersonas) {
        this.lstPersonas = lstPersonas;
    }

    public ModSolicitanteApoderadoService getSmSolicitanteApoderadoService() {
        return modSolicitanteApoderadoService;
    }

    public void setSmSolicitanteApoderadoService(ModSolicitanteApoderadoService modSolicitanteApoderadoService) {
        this.modSolicitanteApoderadoService = modSolicitanteApoderadoService;
    }

    public List<ModSolicitanteApoderado> getListaSolicitanteApoderado() {
        return listaSolicitanteApoderado;
    }

    public void setListaSolicitanteApoderado(List<ModSolicitanteApoderado> listaSolicitanteApoderado) {
        this.listaSolicitanteApoderado = listaSolicitanteApoderado;
    }

    public ModSolicitanteApoderadoService getModSolicitanteApoderadoService() {
        return modSolicitanteApoderadoService;
    }

    public void setModSolicitanteApoderadoService(ModSolicitanteApoderadoService modSolicitanteApoderadoService) {
        this.modSolicitanteApoderadoService = modSolicitanteApoderadoService;
    }

    public List<ModSolicitanteApoderado> getListaApoderado() {
        return listaApoderado;
    }

    public void setListaApoderado(List<ModSolicitanteApoderado> listaApoderado) {
        this.listaApoderado = listaApoderado;
    }

    public List<ModTitularLicenciatarioNuevo> getListaNuevoTitular() {
        return listaNuevoTitular;
    }

    public void setListaNuevoTitular(List<ModTitularLicenciatarioNuevo> listaNuevoTitular) {
        this.listaNuevoTitular = listaNuevoTitular;
    }

    public ModTitularLicenciatarioNuevoService getModTitularLicencitarioNuevoService() {
        return modTitularLicencitarioNuevoService;
    }

    public void setModTitularLicencitarioNuevoService(ModTitularLicenciatarioNuevoService modTitularLicencitarioNuevoService) {
        this.modTitularLicencitarioNuevoService = modTitularLicencitarioNuevoService;
    }

    public List<ModTitularLicenciatarioRegistrado> getListaTitularRegistrado() {
        return listaTitularRegistrado;
    }

    public void setListaTitularRegistrado(List<ModTitularLicenciatarioRegistrado> listaTitularRegistrado) {
        this.listaTitularRegistrado = listaTitularRegistrado;
    }

    public ModTitularLicenciatarioRegistradoService getModTitularLicenciatarioRegistradoService() {
        return modTitularLicenciatarioRegistradoService;
    }

    public void setModTitularLicenciatarioRegistradoService(ModTitularLicenciatarioRegistradoService modTitularLicenciatarioRegistradoService) {
        this.modTitularLicenciatarioRegistradoService = modTitularLicenciatarioRegistradoService;
    }

    public String getParticipanteFusion() {
        return participanteFusion;
    }

    public void setParticipanteFusion(String participanteFusion) {
        this.participanteFusion = participanteFusion;
    }

    public List<ModTitularLicenciatarioRegistrado> getListaParticipanteFusion() {
        return listaParticipanteFusion;
    }

    public void setListaParticipanteFusion(List<ModTitularLicenciatarioRegistrado> listaParticipanteFusion) {
        this.listaParticipanteFusion = listaParticipanteFusion;
    }

    public ModModificacion getModificacion() {
        return modificacion;
    }

    public void setModificacion(ModModificacion modificacion) {
        this.modificacion = modificacion;
    }

    public String getRegional() {
        return regional;
    }

    public void setRegional(String regional) {
        this.regional = regional;
    }

    public String getTextoSM() {
        return textoSM;
    }

    public void setTextoSM(String textoSM) {
        this.textoSM = textoSM;
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

    public ModModificacionService getModModificacionService() {
        return modModificacionService;
    }

    public void setModModificacionService(ModModificacionService modModificacionService) {
        this.modModificacionService = modModificacionService;
    }

    public ModDireccionDeNotificacion getDireccionNotificacion() {
        return direccionNotificacion;
    }

    public void setDireccionNotificacion(ModDireccionDeNotificacion direccionNotificacion) {
        this.direccionNotificacion = direccionNotificacion;
    }

    public DominioService getSmDominioService() {
        return smDominioService;
    }

    public void setSmDominioService(DominioService smDominioService) {
        this.smDominioService = smDominioService;
    }

    public ModDireccionDeNotificacionService getModDireccionDeNotificacionService() {
        return modDireccionDeNotificacionService;
    }

    public void setModDireccionDeNotificacionService(ModDireccionDeNotificacionService modDireccionDeNotificacionService) {
        this.modDireccionDeNotificacionService = modDireccionDeNotificacionService;
    }

    public Long getNumeroMod() {
        return numeroMod;
    }

    public void setNumeroMod(Long numeroMod) {
        this.numeroMod = numeroMod;
    }

    public Integer getGestionMod() {
        return gestionMod;
    }

    public void setGestionMod(Integer gestionMod) {
        this.gestionMod = gestionMod;
    }

    public List<Dominio> getListaTipoGenero() {
        return listaTipoGenero;
    }

    public void setListaTipoGenero(List<Dominio> listaTipoGenero) {
        this.listaTipoGenero = listaTipoGenero;
    }

    public List<Dominio> getListaTipoSigno() {
        return listaTipoSigno;
    }

    public void setListaTipoSigno(List<Dominio> listaTipoSigno) {
        this.listaTipoSigno = listaTipoSigno;
    }

    public List<Dominio> getLstSituacionActual() {
        return lstSituacionActual;
    }

    public void setLstSituacionActual(List<Dominio> lstSituacionActual) {
        this.lstSituacionActual = lstSituacionActual;
    }

    public List<Dominio> getLstCiudadNotificacion() {
        return lstCiudadNotificacion;
    }

    public void setLstCiudadNotificacion(List<Dominio> lstCiudadNotificacion) {
        this.lstCiudadNotificacion = lstCiudadNotificacion;
    }

    public List<Dominio> getLstTipoRecibo() {
        return lstTipoRecibo;
    }

    public void setLstTipoRecibo(List<Dominio> lstTipoRecibo) {
        this.lstTipoRecibo = lstTipoRecibo;
    }

    public List<Dominio> getLstSerieRegistro() {
        return lstSerieRegistro;
    }

    public void setLstSerieRegistro(List<Dominio> lstSerieRegistro) {
        this.lstSerieRegistro = lstSerieRegistro;
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

    public String getValorOpcionesRadio() {
        return valorOpcionesRadio;
    }

    public void setValorOpcionesRadio(String valorOpcionesRadio) {
        this.valorOpcionesRadio = valorOpcionesRadio;
    }

    public String getExtensionSM() {
        return extensionSM;
    }

    public void setExtensionSM(String extensionSM) {
        this.extensionSM = extensionSM;
    }

    public Long getNroPublicacionExpediente() {
        return nroPublicacionExpediente;
    }

    public void setNroPublicacionExpediente(Long nroPublicacionExpediente) {
        this.nroPublicacionExpediente = nroPublicacionExpediente;
    }

    public Long getRegistroExpediente() {
        return registroExpediente;
    }

    public void setRegistroExpediente(Long registroExpediente) {
        this.registroExpediente = registroExpediente;
    }

    public String getSerieRegExpediente() {
        return serieRegExpediente;
    }

    public void setSerieRegExpediente(String serieRegExpediente) {
        this.serieRegExpediente = serieRegExpediente;
    }

    public String getSerieRenoExpediente() {
        return serieRenoExpediente;
    }

    public void setSerieRenoExpediente(String serieRenoExpediente) {
        this.serieRenoExpediente = serieRenoExpediente;
    }

    public Long getRenovacionExpediente() {
        return renovacionExpediente;
    }

    public void setRenovacionExpediente(Long renovacionExpediente) {
        this.renovacionExpediente = renovacionExpediente;
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

    public SigSignoMarcaService getSigSignoMarcaService() {
        return sigSignoMarcaService;
    }

    public void setSigSignoMarcaService(SigSignoMarcaService sigSignoMarcaService) {
        this.sigSignoMarcaService = sigSignoMarcaService;
    }

    public SigSignoClaseNizaService getSigSignoClaseNizaService() {
        return sigSignoClaseNizaService;
    }

    public void setSigSignoClaseNizaService(SigSignoClaseNizaService sigSignoClaseNizaService) {
        this.sigSignoClaseNizaService = sigSignoClaseNizaService;
    }

    public String getTextoObservacionResol() {
        return textoObservacionResol;
    }

    public void setTextoObservacionResol(String textoObservacionResol) {
        this.textoObservacionResol = textoObservacionResol;
    }

    public ModResolucion getModresolucion() {
        return modresolucion;
    }

    public void setModresolucion(ModResolucion modresolucion) {
        this.modresolucion = modresolucion;
    }

    public SigTipoSignoService getSigTipoSignoService() {
        return sigTipoSignoService;
    }

    public void setSigTipoSignoService(SigTipoSignoService sigTipoSignoService) {
        this.sigTipoSignoService = sigTipoSignoService;
    }

    public ModTipoSignoService getModTipoSignoService() {
        return modTipoSignoService;
    }

    public void setModTipoSignoService(ModTipoSignoService modTipoSignoService) {
        this.modTipoSignoService = modTipoSignoService;
    }

    public ModResolucionService getModResolucionService() {
        return modResolucionService;
    }

    public void setModResolucionService(ModResolucionService modResolucionService) {
        this.modResolucionService = modResolucionService;
    }

    public ComunService getComunService() {
        return comunService;
    }

    public void setComunService(ComunService comunService) {
        this.comunService = comunService;
    }

    public SigSolicitanteApoderadoService getSigSolicitanteApoderadoService() {
        return sigSolicitanteApoderadoService;
    }

    public void setSigSolicitanteApoderadoService(SigSolicitanteApoderadoService sigSolicitanteApoderadoService) {
        this.sigSolicitanteApoderadoService = sigSolicitanteApoderadoService;
    }

    public String[] getTipoGeneroSeleccionado() {
        return tipoGeneroSeleccionado;
    }

    public void setTipoGeneroSeleccionado(String[] tipoGeneroSeleccionado) {
        this.tipoGeneroSeleccionado = tipoGeneroSeleccionado;
    }

    public Boolean getTxtOtroRendered() {
        return txtOtroRendered;
    }

    public void setTxtOtroRendered(Boolean txtOtroRendered) {
        this.txtOtroRendered = txtOtroRendered;
    }

    public String getTextoOtro() {
        return textoOtro;
    }

    public void setTextoOtro(String textoOtro) {
        this.textoOtro = textoOtro;
    }

    public List<Dominio> getLstUbicacionModificacion() {
        return lstUbicacionModificacion;
    }

    public void setLstUbicacionModificacion(List<Dominio> lstUbicacionModificacion) {
        this.lstUbicacionModificacion = lstUbicacionModificacion;
    }

    public Boolean getBtnObservacionRendered() {
        return btnObservacionRendered;
    }

    public void setBtnObservacionRendered(Boolean btnObservacionRendered) {
        this.btnObservacionRendered = btnObservacionRendered;
    }

    public ObservacionTramite getObservacionTramite() {
        return observacionTramite;
    }

    public void setObservacionTramite(ObservacionTramite observacionTramite) {
        this.observacionTramite = observacionTramite;
    }

    public ObservacionTramiteService getObservacionTramiteService() {
        return observacionTramiteService;
    }

    public void setObservacionTramiteService(ObservacionTramiteService observacionTramiteService) {
        this.observacionTramiteService = observacionTramiteService;
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

    public List<SigSolicitanteApoderado> getLstTitularActual() {
        return lstTitularActual;
    }

    public void setLstTitularActual(List<SigSolicitanteApoderado> lstTitularActual) {
        this.lstTitularActual = lstTitularActual;
    }

    public Boolean getPanelSeleccionarIdReferencia() {
        return panelSeleccionarIdReferencia;
    }

    public void setPanelSeleccionarIdReferencia(Boolean panelSeleccionarIdReferencia) {
        this.panelSeleccionarIdReferencia = panelSeleccionarIdReferencia;
    }

    public Boolean getBtnRegistrarDisabled() {
        return btnRegistrarDisabled;
    }

    public void setBtnRegistrarDisabled(Boolean btnRegistrarDisabled) {
        this.btnRegistrarDisabled = btnRegistrarDisabled;
    }

    public Boolean getProductosRendered() {
        return productosRendered;
    }

    public void setProductosRendered(Boolean productosRendered) {
        this.productosRendered = productosRendered;
    }

    public Boolean getBotonRecepcionarRendered() {
        return botonRecepcionarRendered;
    }

    public void setBotonRecepcionarRendered(Boolean botonRecepcionarRendered) {
        this.botonRecepcionarRendered = botonRecepcionarRendered;
    }

    public Boolean getLinkReasignarRendered() {
        return linkReasignarRendered;
    }

    public void setLinkReasignarRendered(Boolean linkReasignarRendered) {
        this.linkReasignarRendered = linkReasignarRendered;
    }

    public ModHistorialService getModHistorialService() {
        return modHistorialService;
    }

    public void setModHistorialService(ModHistorialService modHistorialService) {
        this.modHistorialService = modHistorialService;
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

    public UsuarioService getUsuarioService() {
        return usuarioService;
    }

    public void setUsuarioService(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    public ModSolicitanteApoderado getModSolicitanteApoderadoSelect() {
        return modSolicitanteApoderadoSelect;
    }

    public void setModSolicitanteApoderadoSelect(ModSolicitanteApoderado modSolicitanteApoderadoSelect) {
        this.modSolicitanteApoderadoSelect = modSolicitanteApoderadoSelect;
    }

    public Integer getIndice() {
        return indice;
    }

    public void setIndice(Integer indice) {
        this.indice = indice;
    }

    public ModTitularLicenciatarioNuevo getTitularLicencitarioNuevoSelect() {
        return titularLicencitarioNuevoSelect;
    }

    public void setTitularLicencitarioNuevoSelect(ModTitularLicenciatarioNuevo titularLicencitarioNuevoSelect) {
        this.titularLicencitarioNuevoSelect = titularLicencitarioNuevoSelect;
    }

    public ModTitularLicenciatarioRegistrado getTitularLicenciatarioRegistradoSelect() {
        return titularLicenciatarioRegistradoSelect;
    }

    public void setTitularLicenciatarioRegistradoSelect(ModTitularLicenciatarioRegistrado titularLicenciatarioRegistradoSelect) {
        this.titularLicenciatarioRegistradoSelect = titularLicenciatarioRegistradoSelect;
    }

    public String getTipoModificacion() {
        return tipoModificacion;
    }

    public void setTipoModificacion(String tipoModificacion) {
        this.tipoModificacion = tipoModificacion;
    }

    public List<ModTipoSigno> getLstModTipoSigno() {
        return lstModTipoSigno;
    }

    public void setLstModTipoSigno(List<ModTipoSigno> lstModTipoSigno) {
        this.lstModTipoSigno = lstModTipoSigno;
    }

    public Boolean[] getValidaSectores() {
        return validaSectores;
    }

    public void setValidaSectores(Boolean[] validaSectores) {
        this.validaSectores = validaSectores;
    }

    public Boolean[] getValidaModificacionResultado() {
        return validaModificacionResultado;
    }

    public void setValidaModificacionResultado(Boolean[] validaModificacionResultado) {
        this.validaModificacionResultado = validaModificacionResultado;
    }

    public Boolean[] getValidaCambioDomicilioResultado() {
        return validaCambioDomicilioResultado;
    }

    public void setValidaCambioDomicilioResultado(Boolean[] validaCambioDomicilioResultado) {
        this.validaCambioDomicilioResultado = validaCambioDomicilioResultado;
    }

    public Boolean[] getValidaDireccionNotificacionResultado() {
        return validaDireccionNotificacionResultado;
    }

    public void setValidaDireccionNotificacionResultado(Boolean[] validaDireccionNotificacionResultado) {
        this.validaDireccionNotificacionResultado = validaDireccionNotificacionResultado;
    }

    public Boolean[] getValidadorSMRegistroPublicacion() {
        return validadorSMRegistroPublicacion;
    }

    public void setValidadorSMRegistroPublicacion(Boolean[] validadorSMRegistroPublicacion) {
        this.validadorSMRegistroPublicacion = validadorSMRegistroPublicacion;
    }

    public Seguimiento getUltimoSeguimiento() {
        return ultimoSeguimiento;
    }

    public void setUltimoSeguimiento(Seguimiento ultimoSeguimiento) {
        this.ultimoSeguimiento = ultimoSeguimiento;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Date getFechaServidor() {
        return fechaServidor;
    }

    public void setFechaServidor(Date fechaServidor) {
        this.fechaServidor = fechaServidor;
    }

    public SigSignoMarca getSignoMarca() {
        return signoMarca;
    }

    public void setSignoMarca(SigSignoMarca signoMarca) {
        this.signoMarca = signoMarca;
    }

    public Long getIdLogTrans() {
        return idLogTrans;
    }

    public void setIdLogTrans(Long idLogTrans) {
        this.idLogTrans = idLogTrans;
    }

    public List<Etapa> getListEtapaUs() {
        return listEtapaUs;
    }

    public void setListEtapaUs(List<Etapa> listEtapaUs) {
        this.listEtapaUs = listEtapaUs;
    }

    public List<Usuario> getListUsuario() {
        return listUsuario;
    }

    public void setListUsuario(List<Usuario> listUsuario) {
        this.listUsuario = listUsuario;
    }

    public String getReporte() {
        return reporte;
    }

    public void setReporte(String reporte) {
        this.reporte = reporte;
    }

    public Map<String, Object> getParametros() {
        return parametros;
    }

    public void setParametros(Map<String, Object> parametros) {
        this.parametros = parametros;
    }

    public String getImgSenapi() {
        return imgSenapi;
    }

    public void setImgSenapi(String imgSenapi) {
        this.imgSenapi = imgSenapi;
    }

    public String getTramite() {
        return tramite;
    }

    public void setTramite(String tramite) {
        this.tramite = tramite;
    }

    public String getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(String fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getNumResolucion() {
        return numResolucion;
    }

    public void setNumResolucion(String numResolucion) {
        this.numResolucion = numResolucion;
    }

    public String getFechaActual() {
        return fechaActual;
    }

    public void setFechaActual(String fechaActual) {
        this.fechaActual = fechaActual;
    }

    public String getTextConsiderado() {
        return textConsiderado;
    }

    public void setTextConsiderado(String textConsiderado) {
        this.textConsiderado = textConsiderado;
    }

    public String getTextResuelve() {
        return textResuelve;
    }

    public void setTextResuelve(String textResuelve) {
        this.textResuelve = textResuelve;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public List<Dominio> getLstTipoModificacion() {
        return lstTipoModificacion;
    }

    public void setLstTipoModificacion(List<Dominio> lstTipoModificacion) {
        this.lstTipoModificacion = lstTipoModificacion;
    }

    public String getTitulares() {
        return titulares;
    }

    public void setTitulares(String titulares) {
        this.titulares = titulares;
    }

    public String getRepresentantes() {
        return representantes;
    }

    public void setRepresentantes(String representantes) {
        this.representantes = representantes;
    }

    public String getTipoResolucion() {
        return tipoResolucion;
    }

    public void setTipoResolucion(String tipoResolucion) {
        this.tipoResolucion = tipoResolucion;
    }

    public List<ModTitularLicenciatarioNuevo> getListaNuevoTitularR() {
        return listaNuevoTitularR;
    }

    public void setListaNuevoTitularR(List<ModTitularLicenciatarioNuevo> listaNuevoTitularR) {
        this.listaNuevoTitularR = listaNuevoTitularR;
    }

    public List<ModTitularLicenciatarioRegistrado> getListaTitularRegistradoR() {
        return listaTitularRegistradoR;
    }

    public void setListaTitularRegistradoR(List<ModTitularLicenciatarioRegistrado> listaTitularRegistradoR) {
        this.listaTitularRegistradoR = listaTitularRegistradoR;
    }

    public List<TitularLicenciatarios> getListaTitularRegistradoSipi() {
        return listaTitularRegistradoSipi;
    }

    public void setListaTitularRegistradoSipi(List<TitularLicenciatarios> listaTitularRegistradoSipi) {
        this.listaTitularRegistradoSipi = listaTitularRegistradoSipi;
    }

    public List<TitularLicenciatariosNuevos> getListaNuevoTitularSipi() {
        return listaNuevoTitularSipi;
    }

    public void setListaNuevoTitularSipi(List<TitularLicenciatariosNuevos> listaNuevoTitularSipi) {
        this.listaNuevoTitularSipi = listaNuevoTitularSipi;
    }

    public void setListaTitulares(List<ModSolicitanteApoderado> listaTitulares) {
        this.listaTitulares = listaTitulares;
    }

    public List<ModSolicitanteApoderado> getListaRepresentantes() {
        return listaRepresentantes;
    }

    public void setListaRepresentantes(List<ModSolicitanteApoderado> listaRepresentantes) {
        this.listaRepresentantes = listaRepresentantes;
    }

    public String getTipoSignoDescripcion() {
        return tipoSignoDescripcion;
    }

    public void setTipoSignoDescripcion(String tipoSignoDescripcion) {
        this.tipoSignoDescripcion = tipoSignoDescripcion;
    }

    public Boolean getResolucionDisabled() {
        return resolucionDisabled;
    }

    public void setResolucionDisabled(Boolean resolucionDisabled) {
        this.resolucionDisabled = resolucionDisabled;
    }

    public Integer getAdicionarResolucion() {
        return adicionarResolucion;
    }

    public void setAdicionarResolucion(Integer adicionarResolucion) {
        this.adicionarResolucion = adicionarResolucion;
    }

    public Integer getAdicionarGestion() {
        return adicionarGestion;
    }

    public void setAdicionarGestion(Integer adicionarGestion) {
        this.adicionarGestion = adicionarGestion;
    }

    public Date getAdicionarFecha() {
        return adicionarFecha;
    }

    public void setAdicionarFecha(Date adicionarFecha) {
        this.adicionarFecha = adicionarFecha;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public Boolean getEsRevocatoria() {
        return esRevocatoria;
    }

    public void setEsRevocatoria(Boolean esRevocatoria) {
        this.esRevocatoria = esRevocatoria;
    }

    public FlujoSeguimientoService getFlujoSeguimientoService() {
        return flujoSeguimientoService;
    }

    public void setFlujoSeguimientoService(FlujoSeguimientoService flujoSeguimientoService) {
        this.flujoSeguimientoService = flujoSeguimientoService;
    }

    public Boolean getBotonFinalizarRendered() {
        return botonFinalizarRendered;
    }

    public void setBotonFinalizarRendered(Boolean botonFinalizarRendered) {
        this.botonFinalizarRendered = botonFinalizarRendered;
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

    public String getNumeroFormulario() {
        return numeroFormulario;
    }

    public void setNumeroFormulario(String numeroFormulario) {
        this.numeroFormulario = numeroFormulario;
    }

    public FormularioService getFormularioService() {
        return formularioService;
    }

    public void setFormularioService(FormularioService formularioService) {
        this.formularioService = formularioService;
    }

    public FormularioPI103 getFormularioPI103() {
        return formularioPI103;
    }

    public void setFormularioPI103(FormularioPI103 formularioPI103) {
        this.formularioPI103 = formularioPI103;
    }

    public FormularioPI103Service getFormularioPI103Service() {
        return formularioPI103Service;
    }

    public void setFormularioPI103Service(FormularioPI103Service formularioPI103Service) {
        this.formularioPI103Service = formularioPI103Service;
    }

    public List<Solicitantes> getListaModSolicitantesSipi() {
        return listaModSolicitantesSipi;
    }

    public void setListaModSolicitantesSipi(List<Solicitantes> listaModSolicitantesSipi) {
        this.listaModSolicitantesSipi = listaModSolicitantesSipi;
    }

    public List<Apoderados> getListaModApoderadosSipi() {
        return listaModApoderadosSipi;
    }

    public void setListaModApoderadosSipi(List<Apoderados> listaModApoderadosSipi) {
        this.listaModApoderadosSipi = listaModApoderadosSipi;
    }

    public DireccionNotificaciones getDireccionNotificacionSipi() {
        return direccionNotificacionSipi;
    }

    public void setDireccionNotificacionSipi(DireccionNotificaciones direccionNotificacionSipi) {
        this.direccionNotificacionSipi = direccionNotificacionSipi;
    }

    public Boolean getPanelEncontrado() {
        return panelEncontrado;
    }

    public void setPanelEncontrado(Boolean panelEncontrado) {
        this.panelEncontrado = panelEncontrado;
    }

    public String getSignoDistintivoExt() {
        return signoDistintivoExt;
    }

    public void setSignoDistintivoExt(String signoDistintivoExt) {
        this.signoDistintivoExt = signoDistintivoExt;
    }

    public Long getValorClaseNizaExt() {
        return valorClaseNizaExt;
    }

    public void setValorClaseNizaExt(Long valorClaseNizaExt) {
        this.valorClaseNizaExt = valorClaseNizaExt;
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

    public Modificaciones getModificacionSipi() {
        return modificacionSipi;
    }

    public void setModificacionSipi(Modificaciones modificacionSipi) {
        this.modificacionSipi = modificacionSipi;
    }

    public String getVersionClaseNiza() {
        return versionClaseNiza;
    }

    public void setVersionClaseNiza(String versionClaseNiza) {
        this.versionClaseNiza = versionClaseNiza;
    }

//</editor-fold>    
}
