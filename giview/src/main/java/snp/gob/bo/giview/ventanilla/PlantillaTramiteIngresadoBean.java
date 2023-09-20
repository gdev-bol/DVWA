package snp.gob.bo.giview.ventanilla;

import static com.google.common.base.Charsets.ISO_8859_1;
import static com.google.common.base.Charsets.UTF_8;
import java.io.File;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
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
import snp.gob.bo.entidades.bean.common.AbstractManagedBean;
import snp.gob.bo.gimodel.entidad.ClaseNiza;
import snp.gob.bo.gimodel.entidad.DatoElementoFormulario;
import snp.gob.bo.gimodel.entidad.Dominio;
import snp.gob.bo.gimodel.entidad.LogTrans;
import snp.gob.bo.gimodel.entidad.ModModificacion;
import snp.gob.bo.gimodel.entidad.ModSolicitanteApoderado;
import snp.gob.bo.gimodel.entidad.Objeto;
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
import snp.gob.bo.gimodel.enums.EnumEstado;
import snp.gob.bo.gimodel.enums.EnumPrefijoTablas;
import snp.gob.bo.gimodel.enums.EnumTipoPersona;
import snp.gob.bo.gimodel.servicio.ClaseNizaService;
import snp.gob.bo.gimodel.servicio.ComunService;
import snp.gob.bo.gimodel.servicio.DatoElementoFormularioService;
import snp.gob.bo.gimodel.servicio.DominioService;
import snp.gob.bo.gimodel.servicio.LogTransService;
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
import snp.gob.bo.gimodel.servicio.UsuarioService;

/**
 *
 * @author Eddy Valero
 * @version 1.0, 17/09/2016
 */
@ManagedBean(name = "plantillaTramiteIngresadoBean")
@ViewScoped
public class PlantillaTramiteIngresadoBean extends AbstractManagedBean implements Serializable {

    /**
     * ****************************
     */
    private static final Logger logger = LogManager.getLogger(PlantillaTramiteIngresadoBean.class);
    private String template;
    private List<DatoElementoFormulario> plantillaVentanilla = new ArrayList<DatoElementoFormulario>();
    private List<SigSignoMarca> listaSigSignoMarca = new ArrayList<SigSignoMarca>();
    private List<SigSignoClaseNiza> listaSigSignoClaseNiza = new ArrayList<SigSignoClaseNiza>();
    private List<SigSolicitanteApoderado> listaSigSolicitante = new ArrayList<SigSolicitanteApoderado>();
    private List<SigSolicitanteApoderado> listaSigApoderado = new ArrayList<SigSolicitanteApoderado>();

    private List<Objeto> listaObjeto1 = new ArrayList<Objeto>();
    private List<Objeto> listaObjeto2 = new ArrayList<Objeto>();

    private SigSignoMarca sigSignoMarca = new SigSignoMarca();
    private SigLemaComercial sigLemaComercial = new SigLemaComercial();

    // parametros del reporte
    private Map<String, Object> parametros = new HashMap();
    private List<Dominio> listaTipoSignoPI100 = new ArrayList<>();
    private List<Dominio> listaTipoSignoPI101 = new ArrayList<>();
    private List<Dominio> listaTipoSignoPI102 = new ArrayList<>();
    private List<SeccionReporte> listaSeccionReporte = new ArrayList<>();
    private String imageSenapi;
    private String imageEscudoBol;
    private String numeroFormulario;
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
    private List<Dominio> listaTipoModificacion = new ArrayList();

    private HashMap resultado = new HashMap();
    private String tipoTramite;
    private ObservacionTramite observacionTramite;
    private ModModificacion modificacion;
    private Long idUsuario;
    private String prefijoTabla = "";
    private List<Usuario> listUsuario;
    private Date fechaSistema;
    private Long idTabla;
    private RenSolicitudRenovacion solicitudRenovacion;
    private Boolean imprimirDisabled = true;
    private List<ModSolicitanteApoderado> listaModSolicitante = new ArrayList<ModSolicitanteApoderado>();
    private List<ModSolicitanteApoderado> listaModApoderado = new ArrayList<ModSolicitanteApoderado>();
    private List<RenSolicitanteApoderado> listaRenApoderado = new ArrayList<RenSolicitanteApoderado>();
    private List<RenSolicitanteApoderado> listaRenSolicitante = new ArrayList<RenSolicitanteApoderado>();
    private List<OpoSolicitanteapoderado> listaOpoSolicitante = new ArrayList<>();
    private List<OpoSolicitanteapoderado> listaOpoApoderado = new ArrayList<>();
     private Oposicion oposicion = new Oposicion();
    private OpoMarcademandada opoMarcaDemandada = new OpoMarcademandada();
    private OpoMarcademandante opoMarcaDemandante = new OpoMarcademandante();


    @ManagedProperty(value = "#{seguimientoService}")
    private SeguimientoService seguimientoService;
    private Seguimiento sigSeguimiento;

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

    @ManagedProperty(value = "#{datoElementoFormularioService}")
    private DatoElementoFormularioService datoElementoFormularioService;

    @ManagedProperty(value = "#{observacionTramiteService}")
    private ObservacionTramiteService observacionTramiteService;

    @ManagedProperty(value = "#{modModificacionService}")
    private ModModificacionService modModificacionService;

    @ManagedProperty(value = "#{usuarioService}")
    private UsuarioService usuarioService;

    @ManagedProperty(value = "#{logTransService}")
    private LogTransService logTransService;

    @ManagedProperty(value = "#{renSolicitudRenovacionService}")
    private RenSolicitudRenovacionService renSolicitudRenovacionService;

    @ManagedProperty(value = "#{modSolicitanteApoderadoService}")
    private ModSolicitanteApoderadoService modSolicitanteApoderadoService;

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

    /**
     * ***************************
     */
    @PostConstruct
    public void iniciarPlantillaTramiteIngresadoBean() {
        //se utilizar este template por defecto
        template = "./../../WEB-INF/facelets/templates/Template.xhtml";

        //preguntar e insertar registros si es que corresponde
        try {
            idUsuario = super.getIdUsuarioSession();
            listUsuario = usuarioService.listaUsuarioXidPagina(super.getIdUsuarioSession());
            numeroFormulario = getNumeroFormularioSolicitudSession();
            tipoTramite = getTipoTramiteSolicitudSession();
            fechaSistema = comunService.obtenerFechaHoraServidor(1l);

            //de acuerdo al tramite ingresado se debe
            if (tipoTramite.equals("PI100")
                    || tipoTramite.equals("PI101")
                    || tipoTramite.equals("PI102")) {

                plantillaVentanilla = datoElementoFormularioService.obtplantillaventanillatramiteingresado(getNumeroFormularioSolicitudSession(), getTipoTramiteSolicitudSession());
                sigSignoMarca = sigSignoMarcaService.obtenerRegistroSigSignoMarcaXNumeroFormulario(getNumeroFormularioSolicitudSession());
                resultado = comunService.obtenerNumeroGestionExtensionCodigoSM(sigSignoMarca.getSm());
                listaSigSolicitante = sigSolicitanteApoderadoService.listadoSolicitanteXidsignomarca(sigSignoMarca.getIdSignoMarca());
                listaSigApoderado = sigSolicitanteApoderadoService.listadoApoderadoXidsignomarca(sigSignoMarca.getIdSignoMarca());
                listaSigSignoClaseNiza = sigSignoClaseNizaService.obtenerListaSigSignoClazeNizaXIdSignoMarca(sigSignoMarca.getIdSignoMarca());
                sigLemaComercial = sigLemaComercialService.obtenerSigLemaComercial(sigSignoMarca.getIdSignoMarca());
                observacionTramite = observacionTramiteService.listar_ObservacionTramite_ventanilla(sigSignoMarca.getIdSignoMarca(), tipoTramite);

            }

            if (tipoTramite.equals("PI103")) {
                plantillaVentanilla = datoElementoFormularioService.obtplantillaventanillatramiteingresado(getNumeroFormularioSolicitudSession(), getTipoTramiteSolicitudSession());
                modificacion = modModificacionService.buscarModModificacionXnroFormulario(numeroFormulario);
                listaModSolicitante = modSolicitanteApoderadoService.listadoSolicitanteXidmodificacion(modificacion.getIdmodificacion());
                listaModApoderado = modSolicitanteApoderadoService.listadoApoderadoXidmodificacion(modificacion.getIdmodificacion());
                observacionTramite = observacionTramiteService.listar_ObservacionTramite_ventanilla(modificacion.getIdmodificacion(), tipoTramite);

            }

            if (tipoTramite.equals("PI104")) {

                plantillaVentanilla = datoElementoFormularioService.obtplantillaventanillatramiteingresado(getNumeroFormularioSolicitudSession(), getTipoTramiteSolicitudSession());
                solicitudRenovacion = renSolicitudRenovacionService.buscarRenSolicitud_NumeroFormulario(numeroFormulario);
                listaRenSolicitante = (List<RenSolicitanteApoderado>) renSolicitanteApoderadoService.buscaSolicitanteApoderadoPoridSolicitudyTipoPersona(solicitudRenovacion.getIdsolicitudrenovacion(), EnumTipoPersona.SOLICITANTE.getCodigo());
                listaRenApoderado = (List<RenSolicitanteApoderado>) renSolicitanteApoderadoService.buscaSolicitanteApoderadoPoridSolicitudyTipoPersona(solicitudRenovacion.getIdsolicitudrenovacion(), EnumTipoPersona.APODERADO.getCodigo());
                observacionTramite = observacionTramiteService.listar_ObservacionTramite_ventanilla(solicitudRenovacion.getIdsolicitudrenovacion(), tipoTramite);

            }
            
            if (tipoTramite.equals("PI105")) {

                plantillaVentanilla = datoElementoFormularioService.obtplantillaventanillatramiteingresado(getNumeroFormularioSolicitudSession(), getTipoTramiteSolicitudSession());
                oposicion = oposicionService.obtoposicionxnroformulario(Long.parseLong(numeroFormulario));
                if (oposicion != null) {
                    opoMarcaDemandada = opoMarcaDemandadaService.obtenerOpomarcademandadaobj(oposicion.getIdoposicion());
                    opoMarcaDemandante = opoMarcaDemandanteService.obtieneelobjetodmtexidoposicion(oposicion.getIdoposicion());
                    listaOpoSolicitante = opoSolicitanteApoderadoService.obtenerListadoSoliDmte(opoMarcaDemandante.getIdmarcademandante());
                    listaOpoApoderado = opoSolicitanteApoderadoService.obtenerListadoApoDmte(opoMarcaDemandante.getIdmarcademandante());
                    observacionTramite = observacionTramiteService.listar_ObservacionTramite_ventanilla(oposicion.getIdoposicion(), tipoTramite);
                }
            }

            listaTipoModificacion = dominioService.obtenerListadoDominio("tipo_modificacion");
            listaTipoSignoPI100 = dominioService.listar_dominio_dominiopadre("tipo_genero", "PI100");
            listaTipoSignoPI101 = dominioService.listar_dominio_dominiopadre("tipo_genero", "PI101");
            listaTipoSignoPI102 = dominioService.listar_dominio_dominiopadre("tipo_genero", "PI102");

        } catch (Exception ex) {
            //Logger.getLogger(RecepcionDeDocumentosBean.class.getName()).log(Level.SEVERE, null, ex);
            logger.error(ex);

        } finally {
            eliminarVariablesSession();
        }

    }

    //metodos
    public void accionGuardarFormularioIngreso() {
        try {

            if (tipoTramite.equals("PI100")
                    || tipoTramite.equals("PI101")
                    || tipoTramite.equals("PI102")) {
                datoElementoFormularioService.actualizarRegistrosPlantillaVentanilla(plantillaVentanilla, EnumPrefijoTablas.SIGNO.getCodigo());
                prefijoTabla = EnumPrefijoTablas.SIGNO.getCodigo();
                idTabla = sigSignoMarca.getIdSignoMarca();
            }

            if (tipoTramite.equals("PI103")) {

                datoElementoFormularioService.actualizarRegistrosPlantillaVentanilla(plantillaVentanilla, EnumPrefijoTablas.MODIFICACION.getCodigo());
                prefijoTabla = EnumPrefijoTablas.MODIFICACION.getCodigo();
                idTabla = modificacion.getIdmodificacion();
            }

            if (tipoTramite.equals("PI104")) {

                datoElementoFormularioService.actualizarRegistrosPlantillaVentanilla(plantillaVentanilla, EnumPrefijoTablas.RENOVACION.getCodigo());
                prefijoTabla = EnumPrefijoTablas.RENOVACION.getCodigo();
                idTabla = solicitudRenovacion.getIdsolicitudrenovacion();
            }
            
            if (tipoTramite.equals("PI105")) {
                datoElementoFormularioService.actualizarRegistrosPlantillaVentanilla(plantillaVentanilla, EnumPrefijoTablas.OPOSICION.getCodigo());
                prefijoTabla = EnumPrefijoTablas.OPOSICION.getCodigo();
                idTabla = oposicion.getIdoposicion();
            }

            if (observacionTramite.getIdObservacionTramite() != null) {

                observacionTramite.setFechaObservacion(fechaSistema);
                observacionTramiteService.guardar_modificar_listar_ObservacionTramite(observacionTramite, prefijoTabla, 2);
            } else {
                if (!observacionTramite.getDescripcion().equals("")) {
                    LogTrans logTransGuardado = logTransService.crudLogTrans(new LogTrans(idUsuario, fechaSistema), 1);
                    //finalmente guardar la observacion del tramite.                                        
                    observacionTramite.setId(idTabla);
                    observacionTramite.setIdUsuario(idUsuario);
                    observacionTramite.setIdLogTrans(logTransGuardado.getIdLogTrans());
                    observacionTramite.setEditable(true);
                    observacionTramite.setFechaObservacion(fechaSistema);
                    observacionTramite.setEtapaTramite("VENT");
                    observacionTramite.setEstado(EnumEstado.ACTIVO.getCodigo());
                    observacionTramiteService.guardar_modificar_listar_ObservacionTramite(observacionTramite, prefijoTabla, 1);
                }

            }
            imprimirDisabled = false;

        } catch (Exception ex) {
//            Logger.getLogger(RecepcionDeDocumentosBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        //return "ingresoSolicitud";
    }

    /**
     * *
     * Metodo para generar el reporte del formulario 100, 101, 102, 103, 104.
     *
     * Creado: Ruben Ramirez Fecha: 12/01/2017
     *
     * @throws net.sf.jasperreports.engine.JRException
     * @throws java.io.IOException
     */
    public void accionImprimir() throws JRException, IOException, Exception {

        if (tipoTramite.equals("PI100") || tipoTramite.equals("PI101") || tipoTramite.equals("PI102")) {
            accionImprimirSig();
        }

        if (tipoTramite.equals("PI103")) {
            accionImprimirMod();
        }

        if (tipoTramite.equals("PI104")) {
            accionImprimirRen();
        }
        
        if (tipoTramite.equals("PI105")) {
            accionImprimirOpo();
        }
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

        titulo = "FORMULARIO DE VERIFICACION DE INGRESO DE " + tipoSigno + " " + sm;

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
        if (tipoTramite.equals("PI102")) {
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
            DatoElementoFormulario item1 = plantillaVentanilla.get(i);
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
                    DatoElementoFormulario item2 = plantillaVentanilla.get(j);
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

        observacion2 = observacionTramite.getDescripcion();

        byte ptext[] = observacion2.getBytes(ISO_8859_1);
        observacion2 = new String(ptext, UTF_8);

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
        fechaIngreso = formateador1.format(modificacion.getFecha_ingreso());
        SimpleDateFormat formateador2 = new SimpleDateFormat("HH:mm:ss");
        horaIngreso = formateador2.format(modificacion.getFecha_ingreso());

        sm = "" + modificacion.getSigla() + "-" + String.format("%6s", modificacion.getNumero()).replace(' ', '0') + "-" + modificacion.getGestion();

        for (Dominio item : listaTipoModificacion) {
            if (item.getCodigo().equals(modificacion.getTipo_modificacion())) {
                titulo = "FORMULARIO DE VERIFICACION DE INGRESO DE " + item.getNombre().toUpperCase() + " " + sm;
                break;
            }
        }

        nombreMarca = modificacion.getSigno_registrado();
        
        if(modificacion.getIdclase_registrado()!=null){
            ClaseNiza li=claseNizaService.listarClaseNiza_id(modificacion.getIdclase_registrado());
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
            DatoElementoFormulario item1 = plantillaVentanilla.get(i);
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
                    DatoElementoFormulario item2 = plantillaVentanilla.get(j);
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

        observacion2 = observacionTramite.getDescripcion();

        byte ptext[] = observacion2.getBytes(ISO_8859_1);
        observacion2 = new String(ptext, UTF_8);

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

        String filename = modificacion.getSm() + ".pdf";
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
        fechaIngreso = formateador1.format(solicitudRenovacion.getFecha_ingreso());
        SimpleDateFormat formateador2 = new SimpleDateFormat("HH:mm:ss");
        horaIngreso = formateador2.format(solicitudRenovacion.getFecha_ingreso());

        sm = "SR-" + String.format("%6s", solicitudRenovacion.getSr()).replace(' ', '0') + "-" + solicitudRenovacion.getGestion_sr();

        titulo = "FORMULARIO DE VERIFICACION DE INGRESO PARA REGISTRO DE RENOVACION " + sm;

        nombreMarca = solicitudRenovacion.getSigno_registrado();
        
        if(solicitudRenovacion.getIdclase_niza_registrado()!=null){
            ClaseNiza li=claseNizaService.listarClaseNiza_id(solicitudRenovacion.getIdclase_niza_registrado());
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
            DatoElementoFormulario item1 = plantillaVentanilla.get(i);
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
                    DatoElementoFormulario item2 = plantillaVentanilla.get(j);
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
        observacion2 = observacionTramite.getDescripcion();

        byte ptext[] = observacion2.getBytes(ISO_8859_1);
        observacion2 = new String(ptext, UTF_8);

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

        String filename = solicitudRenovacion.getNumero_formulario() + ".pdf";
        String jasperPath = "/template/formulario/IngresoRecepcionDeDocumentos.jasper";
        this.PDFSD(parametros, jasperPath, filename);

        solicitantes = "";
        apoderados = "";
        observacion2 = "";
        listaSeccionReporte.clear();
    }
    
    /**
     * Metodo para llenar el formulario 104 de solicitud de oposicion.
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
            DatoElementoFormulario item1 = plantillaVentanilla.get(i);
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
                    DatoElementoFormulario item2 = plantillaVentanilla.get(j);
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
        observacion2 = observacionTramite.getDescripcion();

        byte ptext[] = observacion2.getBytes(ISO_8859_1);
        observacion2 = new String(ptext, UTF_8);

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
    public String retornarPagina() {
        return "ingresoSolicitud";

    }

    //<editor-fold defaultstate="collapsed" desc="Getter y setters del Bean">
    //atributos
    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
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

    public JRDataSource getDatos1() {
        return new JRBeanCollectionDataSource(listaObjeto1);
    }

    public JRDataSource getDatos2() {
        return new JRBeanCollectionDataSource(listaObjeto2);
    }

    public List<DatoElementoFormulario> getPlantillaVentanilla() {
        return plantillaVentanilla;
    }

    public void setPlantillaVentanilla(List<DatoElementoFormulario> plantillaVentanilla) {
        this.plantillaVentanilla = plantillaVentanilla;
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

    //</editor-fold>
    public List<SigSignoMarca> getListaSigSignoMarca() {
        return listaSigSignoMarca;
    }

    public void setListaSigSignoMarca(List<SigSignoMarca> listaSigSignoMarca) {
        this.listaSigSignoMarca = listaSigSignoMarca;
    }

    public SigSignoMarca getSigSignoMarca() {
        return sigSignoMarca;
    }

    public void setSigSignoMarca(SigSignoMarca sigSignoMarca) {
        this.sigSignoMarca = sigSignoMarca;
    }

    public SigSignoMarcaService getSigSignoMarcaService() {
        return sigSignoMarcaService;
    }

    public void setSigSignoMarcaService(SigSignoMarcaService sigSignoMarcaService) {
        this.sigSignoMarcaService = sigSignoMarcaService;
    }

    public Map<String, Object> getParametros() {
        return parametros;
    }

    public void setParametros(Map<String, Object> parametros) {
        this.parametros = parametros;
    }

    public SigLemaComercial getSigLemaComercial() {
        return sigLemaComercial;
    }

    public void setSigLemaComercial(SigLemaComercial sigLemaComercial) {
        this.sigLemaComercial = sigLemaComercial;
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

    public String getTipoTramite() {
        return tipoTramite;
    }

    public void setTipoTramite(String tipoTramite) {
        this.tipoTramite = tipoTramite;
    }

    public List<SigSignoClaseNiza> getListaSigSignoClaseNiza() {
        return listaSigSignoClaseNiza;
    }

    public void setListaSigSignoClaseNiza(List<SigSignoClaseNiza> listaSigSignoClaseNiza) {
        this.listaSigSignoClaseNiza = listaSigSignoClaseNiza;
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

    public ModModificacionService getModModificacionService() {
        return modModificacionService;
    }

    public void setModModificacionService(ModModificacionService modModificacionService) {
        this.modModificacionService = modModificacionService;
    }

    public RenSolicitudRenovacion getSolicitudRenovacion() {
        return solicitudRenovacion;
    }

    public void setSolicitudRenovacion(RenSolicitudRenovacion solicitudRenovacion) {
        this.solicitudRenovacion = solicitudRenovacion;
    }

    public UsuarioService getUsuarioService() {
        return usuarioService;
    }

    public void setUsuarioService(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    public LogTransService getLogTransService() {
        return logTransService;
    }

    public void setLogTransService(LogTransService logTransService) {
        this.logTransService = logTransService;
    }

    public RenSolicitudRenovacionService getRenSolicitudRenovacionService() {
        return renSolicitudRenovacionService;
    }

    public void setRenSolicitudRenovacionService(RenSolicitudRenovacionService renSolicitudRenovacionService) {
        this.renSolicitudRenovacionService = renSolicitudRenovacionService;
    }

    public Boolean getImprimirDisabled() {
        return imprimirDisabled;
    }

    public void setImprimirDisabled(Boolean imprimirDisabled) {
        this.imprimirDisabled = imprimirDisabled;
    }

    public ModSolicitanteApoderadoService getModSolicitanteApoderadoService() {
        return modSolicitanteApoderadoService;
    }

    public void setModSolicitanteApoderadoService(ModSolicitanteApoderadoService modSolicitanteApoderadoService) {
        this.modSolicitanteApoderadoService = modSolicitanteApoderadoService;
    }

    public RenSolicitanteApoderadoService getRenSolicitanteApoderadoService() {
        return renSolicitanteApoderadoService;
    }

    public void setRenSolicitanteApoderadoService(RenSolicitanteApoderadoService renSolicitanteApoderadoService) {
        this.renSolicitanteApoderadoService = renSolicitanteApoderadoService;
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

    public List<Objeto> getListaObjeto1() {
        return listaObjeto1;
    }

    public void setListaObjeto1(List<Objeto> listaObjeto1) {
        this.listaObjeto1 = listaObjeto1;
    }

    public List<Objeto> getListaObjeto2() {
        return listaObjeto2;
    }

    public void setListaObjeto2(List<Objeto> listaObjeto2) {
        this.listaObjeto2 = listaObjeto2;
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

    public String getObservacion2() {
        return observacion2;
    }

    public void setObservacion2(String observacion2) {
        this.observacion2 = observacion2;
    }

    public List<Dominio> getListaTipoModificacion() {
        return listaTipoModificacion;
    }

    public void setListaTipoModificacion(List<Dominio> listaTipoModificacion) {
        this.listaTipoModificacion = listaTipoModificacion;
    }

    public ModModificacion getModificacion() {
        return modificacion;
    }

    public void setModificacion(ModModificacion modificacion) {
        this.modificacion = modificacion;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getPrefijoTabla() {
        return prefijoTabla;
    }

    public void setPrefijoTabla(String prefijoTabla) {
        this.prefijoTabla = prefijoTabla;
    }

    public List<Usuario> getListUsuario() {
        return listUsuario;
    }

    public void setListUsuario(List<Usuario> listUsuario) {
        this.listUsuario = listUsuario;
    }

    public Date getFechaSistema() {
        return fechaSistema;
    }

    public void setFechaSistema(Date fechaSistema) {
        this.fechaSistema = fechaSistema;
    }

    public Long getIdTabla() {
        return idTabla;
    }

    public void setIdTabla(Long idTabla) {
        this.idTabla = idTabla;
    }

    public List<ModSolicitanteApoderado> getListaModSolicitante() {
        return listaModSolicitante;
    }

    public void setListaModSolicitante(List<ModSolicitanteApoderado> listaModSolicitante) {
        this.listaModSolicitante = listaModSolicitante;
    }

    public List<ModSolicitanteApoderado> getListaModApoderado() {
        return listaModApoderado;
    }

    public void setListaModApoderado(List<ModSolicitanteApoderado> listaModApoderado) {
        this.listaModApoderado = listaModApoderado;
    }

    public List<RenSolicitanteApoderado> getListaRenApoderado() {
        return listaRenApoderado;
    }

    public void setListaRenApoderado(List<RenSolicitanteApoderado> listaRenApoderado) {
        this.listaRenApoderado = listaRenApoderado;
    }

    public List<RenSolicitanteApoderado> getListaRenSolicitante() {
        return listaRenSolicitante;
    }

    public void setListaRenSolicitante(List<RenSolicitanteApoderado> listaRenSolicitante) {
        this.listaRenSolicitante = listaRenSolicitante;
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

    public Oposicion getOposicion() {
        return oposicion;
    }

    public void setOposicion(Oposicion oposicion) {
        this.oposicion = oposicion;
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

    public OposicionService getOposicionService() {
        return oposicionService;
    }

    public void setOposicionService(OposicionService oposicionService) {
        this.oposicionService = oposicionService;
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

    public ClaseNizaService getClaseNizaService() {
        return claseNizaService;
    }

    public void setClaseNizaService(ClaseNizaService claseNizaService) {
        this.claseNizaService = claseNizaService;
    }
    
}
