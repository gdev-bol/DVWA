/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.entidades.bean.oposicion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javassist.CtMethod.ConstParameter.integer;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.criteria.CriteriaBuilder;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import snp.gob.bo.entidades.bean.common.AbstractManagedBean;
import snp.gob.bo.gimodel.entidad.Dominio;
import snp.gob.bo.gimodel.entidad.LogTrans;
import snp.gob.bo.gimodel.entidad.ObservacionTramite;
import snp.gob.bo.gimodel.entidad.OpoActividad;
import snp.gob.bo.gimodel.entidad.OpoEvento;
import snp.gob.bo.gimodel.entidad.OpoHistorial;
import snp.gob.bo.gimodel.entidad.OpoMarcademandada;
import snp.gob.bo.gimodel.entidad.OpoMarcademandante;
import snp.gob.bo.gimodel.entidad.OpoRecurso;
import snp.gob.bo.gimodel.entidad.OpoResolucion;
import snp.gob.bo.gimodel.entidad.Oposicion;
import snp.gob.bo.gimodel.entidad.SigSignoMarca;
import snp.gob.bo.gimodel.servicio.ComunService;
import snp.gob.bo.gimodel.servicio.LogTransService;
import snp.gob.bo.gimodel.servicio.ObservacionTramiteService;
import snp.gob.bo.gimodel.servicio.OpoActividadService;
import snp.gob.bo.gimodel.servicio.OpoEstadoService;
import snp.gob.bo.gimodel.servicio.OpoEventoService;
import snp.gob.bo.gimodel.servicio.OpoHistorialService;
import snp.gob.bo.gimodel.servicio.OpoMarcademandadaService;
import snp.gob.bo.gimodel.servicio.OpoMarcademandanteService;
import snp.gob.bo.gimodel.servicio.OpoRecursoService;
import snp.gob.bo.gimodel.servicio.OpoResolucionService;
import snp.gob.bo.gimodel.servicio.OposicionService;
import snp.gob.bo.gimodel.servicio.SigSignoMarcaService;

/**
 *
 * @author Luis Angel Quispe Limachi
 */
@ManagedBean(name = "seguimientoOpoBean")
@ViewScoped
public class SeguimientoOpoBean extends AbstractManagedBean implements Serializable {

    private String xx = "HOLA";
    private String template = "./../WEB-INF/facelets/templates/Template.xhtml";
    private Long numeropubliseg;
    private Integer ordenoposeg;
    private Long cmbactividades;
    private String cmbseguimientoopo;
    private String cmbseguimientosig;
    private String cmbseguimientocance;
    private String cmbseguimientrecurso;
    private String txttiporecurso;
    private Long idUsuarioSesion;
    private Boolean objeto1;
    private Boolean objeto2;
    private Boolean objeto3;
    private Integer txtnroresolucion;
    private Integer txtnrorecurso;
    private Date cldfechaactividadseg;
    private String txaobservacionseg;
    private OpoHistorial datoshistorialseg = new OpoHistorial();
    private OpoEvento datosopoeventoseg = new OpoEvento();
    private OpoRecurso datosrecurso = new OpoRecurso();
    private OpoResolucion datosresolucion = new OpoResolucion();
    private OpoRecurso datosrecursocont = new OpoRecurso();
    private OpoResolucion datosresolucioncont = new OpoResolucion();
    private OpoResolucion datosresolucionedit = new OpoResolucion();
    private OpoRecurso datosrecursoedit = new OpoRecurso();
    private OpoEvento selectedOpo;
    private SigSignoMarca signomarcaActumarca = new SigSignoMarca();
    private OpoMarcademandante marcaDemandante = new OpoMarcademandante();
    private OpoMarcademandante marcaDemte = new OpoMarcademandante();
    private OpoMarcademandada marcaDemandada = new OpoMarcademandada();
    private Oposicion datosoposicion = new Oposicion();
    private SigSignoMarca signomarcaActuObs = new SigSignoMarca();
    private ObservacionTramite sigmarcaObs = new ObservacionTramite();
    private ObservacionTramite sigmarcaObsEdit = new ObservacionTramite();
    private ObservacionTramite sigmarcaObsNew = new ObservacionTramite();
    private String marcasvs = "";
    private List<OpoActividad> lstOpoActividad;

    private List<OpoEvento> listaevento = new ArrayList<>();
    private List<Dominio> listarecursoOpo = new ArrayList<>();
    private List<Dominio> listarecursoSig = new ArrayList<>();
    private List<Dominio> listarecursoCance = new ArrayList<>();
    private List<Dominio> listarecurso = new ArrayList<>();

    //private List<Dominio> listarecursoOpo = new ArrayList<>();
    @ManagedProperty(value = "#{opoEventoService}")
    private OpoEventoService opoEventoService;

    @ManagedProperty(value = "#{opoResolucionService}")
    private OpoResolucionService opoResolucionService;

    @ManagedProperty(value = "#{oposicionService}")
    private OposicionService oposicionService;

    @ManagedProperty(value = "#{opoActividadService}")
    private OpoActividadService opoActividadService;

    @ManagedProperty(value = "#{opoHistorialService}")
    private OpoHistorialService opoHistorialService;

    @ManagedProperty(value = "#{opoRecursoService}")
    private OpoRecursoService opoRecursoService;

    @ManagedProperty("#{comunService}")
    private ComunService comunService;

    @ManagedProperty("#{logTransService}")
    private LogTransService logTransService;

    @ManagedProperty(value = "#{sigSignoMarcaService}")
    private SigSignoMarcaService sigSignoMarcaService;

    @ManagedProperty(value = "#{opoMarcaDemandanteService}")
    private OpoMarcademandanteService opoMarcaDemandanteService;

    @ManagedProperty(value = "#{opoMarcaDemandadaService}")
    private OpoMarcademandadaService opoMarcaDemandadaService;

    @ManagedProperty("#{observacionTramiteService}")
    private ObservacionTramiteService observacionTramiteService;

    @ManagedProperty(value = "#{opoEstadoService}")
    private OpoEstadoService opoEstadoService;

    public SeguimientoOpoBean() {
    }

    @PostConstruct
    public void SeguimientoOposBeanInit() {

        try {
            Map<String, String[]> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterValuesMap();
            //template = "./../WEB-INF/facelets/templates/DialogTemplate.xhtml";
            if (params.get("enviarDatos") != null) {

                numeropubliseg = Long.parseLong(params.get("enviarDatos")[0]);
                ordenoposeg = Integer.parseInt(params.get("enviarDatos")[1]);
                lstOpoActividad = opoActividadService.obtenerListadoActividades();
                idUsuarioSesion = super.getIdUsuarioSession();
                listaevento = opoEventoService.obtenerListadoeventoxidoposicion(oposicionService.encuentraclaveprin(numeropubliseg, ordenoposeg));

            } else {

            }

//         numeropubliseg=Long.parseLong(params.get("enviarDatos")[0]);
//         ordenoposeg=Integer.parseInt(params.get("enviarDatos")[1]);
        } catch (Exception ex) {
            Logger.getLogger(SeguimientoOpoBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * *
     * Método verificar el numero de recuso si es repetido
     *
     * Creado: Luis Quispe Fecha:15/10/2016
     *
     * @throws java.lang.Exception
     */
    public boolean verificar_numerorecursorepetido(Integer numerorec) throws Exception {
        boolean ban = false;
        Calendar gregfech = new GregorianCalendar();
        Calendar gregfechaactu = new GregorianCalendar();
        Date Fecha = new Date();
        List<OpoRecurso> listarecurso = new ArrayList<>();
        listarecurso = opoRecursoService.validacionrecursorepetido(numerorec);
        if (!listarecurso.isEmpty()) {
            int coa = 1;
            int coj = 0;
            int cor = 0;
            for (OpoRecurso varia : listarecurso) {
                gregfech.setTime(varia.getFecha_resolucion());
                gregfechaactu.setTime(Fecha);
                if (gregfech.get(Calendar.YEAR) == gregfechaactu.get(Calendar.YEAR)) {
                    coa++;
                    if (varia.getTipo().equals("j")) {
                        coj++;
                    }
                    if (varia.getTipo().equals("rev")) {
                        cor++;
                    }
                }
                if (coa <= 2 && coj <= 1 && cor <= 1) {
                    ban = true;
                } else {
                    ban = false;
                }

            }

        }
        return ban;
    }

    /**
     * *
     * Método Adiciona Guardar datos del seguimiento
     *
     * Creado: Luis Quispe Fecha:15/10/2016
     *
     * @throws java.lang.Exception
     */
    public void guardar_datosOpoSeguimiento() throws Exception {

        Date fechaSistema = comunService.obtenerFechaHoraServidor(1L);
        LogTrans logTransGuardado = logTransService.crudLogTrans(new LogTrans(idUsuarioSesion, fechaSistema), 1);

        datosopoeventoseg.setIdactividad(cmbactividades);
        datosopoeventoseg.setIdoposicion(oposicionService.encuentraclaveprin(numeropubliseg, ordenoposeg));
        datosopoeventoseg.setIdlogtrans(logTransGuardado.getIdLogTrans());
        datosopoeventoseg.setFecha(cldfechaactividadseg);
        datosopoeventoseg.setObservacion(txaobservacionseg);
        datosopoeventoseg.setUsuario(idUsuarioSesion);
        datosopoeventoseg.setOrden_o(ordenoposeg);
        datosopoeventoseg.setFecha_operacion(comunService.obtenerFechaHoraServidor(1L));
        datosopoeventoseg.setEstado("AC");
        opoEventoService.guardaOpoEvento(datosopoeventoseg);
        listaevento = opoEventoService.obtenerListadoeventoxidoposicion(oposicionService.encuentraclaveprin(numeropubliseg, ordenoposeg));

        datoshistorialseg.setIdoposicion(oposicionService.encuentraclaveprin(numeropubliseg, ordenoposeg));
        datoshistorialseg.setIdlogtrans(logTransGuardado.getIdLogTrans());
        datoshistorialseg.setEstado(opoActividadService.muestranomdelaactividadxidactividad(cmbactividades));
        datoshistorialseg.setFecha_lim(cldfechaactividadseg);
        datoshistorialseg.setObservacion(txaobservacionseg);
        datoshistorialseg.setId_usuario(idUsuarioSesion);
        datoshistorialseg.setOperacion("ADD ACTIVIDAD");
        datoshistorialseg.setFecha_operacion(comunService.obtenerFechaHoraServidor(1L));
        datoshistorialseg.setId_usuario(idUsuarioSesion);
        opoHistorialService.guardaOpoHistorial(datoshistorialseg);

        //colocar el estado donde se encuentra
        datosoposicion = oposicionService.obtoposicionxnroopo(oposicionService.encuentraclaveprin(numeropubliseg, ordenoposeg));
        Long activiante = datosoposicion.getId_estado();
        Long idopo = datosoposicion.getIdoposicion();
        datosoposicion.setId_estado(opoActividadService.muestraidestadoxidactividad(cmbactividades));
        oposicionService.modificaOposicion(datosoposicion);

        //parte para adjuntar a las observaciones en signos
        marcaDemte = opoMarcaDemandanteService.obtieneelobjetodmtexidoposicion(idopo);
        marcaDemandada = opoMarcaDemandadaService.obtenerOpomarcademandadaobj(idopo);
        if (marcaDemte != null && marcaDemandada != null) {
            marcasvs = marcaDemte.getDmte_marca_lnv() + "   V.s.  " + marcaDemandada.getDmdo_marca_lnv();
        }
        //si hay algun proglema con esta parte cambiar que la actividad sea mayor (casos execpcionales)   ESTE GOOO

        if (activiante != opoActividadService.muestraidestadoxidactividad(cmbactividades)) {
            signomarcaActuObs = sigSignoMarcaService.listaSigSignoMarcaXPublicacion(numeropubliseg);
            long idsignomarca = signomarcaActuObs.getIdSignoMarca();
            sigmarcaObs = observacionTramiteService.obtenerUltimaObservacionTramite("SIG", idsignomarca);
            if (sigmarcaObs != null) {
                System.out.println("::::OK");
                if (sigmarcaObs.getIdObservacionTramite() == 0) {
                    ObservacionTramite sigmarcaObsNew = new ObservacionTramite();
                    sigmarcaObsNew.setId(idsignomarca);
                    sigmarcaObsNew.setIdLogTrans(logTransGuardado.getIdLogTrans());
                    sigmarcaObsNew.setEditable(Boolean.FALSE);
                    sigmarcaObsNew.setFechaObservacion(fechaSistema);
                    sigmarcaObsNew.setIdUsuario(idUsuarioSesion);
                    sigmarcaObsNew.setEstado("AC");
                    sigmarcaObsNew.setDescripcion("-CASO(" + ordenoposeg + "):" + marcasvs + " \n -ESTADO:" + opoEstadoService.devuelvenombredelestadoxidestado(opoActividadService.muestraidestadoxidactividad(cmbactividades)) + "  \n -OBSERVACION: " + txaobservacionseg + " \n");
                    observacionTramiteService.guardar_modificar_listar_ObservacionTramite(sigmarcaObsNew, "SIG", 1);
                } else {
                    observacionTramiteService.eliminarObservacion(sigmarcaObs.getId());
                    ObservacionTramite sigmarcaObsNew = new ObservacionTramite();
                    sigmarcaObsNew.setId(idsignomarca);
                    sigmarcaObsNew.setIdLogTrans(logTransGuardado.getIdLogTrans());
                    sigmarcaObsNew.setEditable(Boolean.FALSE);
                    sigmarcaObsNew.setFechaObservacion(fechaSistema);
                    sigmarcaObsNew.setIdUsuario(idUsuarioSesion);
                    sigmarcaObsNew.setEstado("AC");
                    sigmarcaObsNew.setDescripcion(sigmarcaObs.getDescripcion() + "\n -CASO(" + ordenoposeg + "):" + marcasvs + " \n -ESTADO:" + opoEstadoService.devuelvenombredelestadoxidestado(opoActividadService.muestraidestadoxidactividad(cmbactividades)) + "  \n -OBSERVACION: " + txaobservacionseg + " \n");
                    observacionTramiteService.guardar_modificar_listar_ObservacionTramite(sigmarcaObsNew, "SIG", 1);
                }
            }

        } else {
            System.out.println("Es igual " + activiante + "    " + opoActividadService.muestraidestadoxidactividad(cmbactividades));

        }

        //System.out.println("==========ESTADO ACTI===========>"+opoActividadService.muestraidestadoxidactividad(cmbactividades));
        if (cmbactividades.intValue() == 41) {
            datosresolucion.setIdoposicion(oposicionService.encuentraclaveprin(numeropubliseg, ordenoposeg));
            datosresolucion.setIdevento(opoEventoService.encuentramaximoOpoEventoxnroopo(oposicionService.encuentraclaveprin(numeropubliseg, ordenoposeg)));
            datosresolucion.setIdlogtrans(logTransGuardado.getIdLogTrans());
            datosresolucion.setNumero_resolucion(txtnroresolucion);
            datosresolucion.setFecha(cldfechaactividadseg);
            datosresolucion.setResolucion_cancelacion(cmbseguimientocance);
            datosresolucion.setResolucion_oposicion(cmbseguimientoopo);
            datosresolucion.setResolucion_signo(cmbseguimientosig);
            datosresolucion.setOrden_o(ordenoposeg);
            datosresolucion.setEstado("AC");
            opoResolucionService.guardaOpoResolucion(datosresolucion);

        } else {
            
            if(verificar_numerorecursorepetido(txtnrorecurso)){  
                                 
            if (cmbactividades.intValue() == 9 || cmbactividades.intValue() == 32 || cmbactividades.intValue() == 48 || cmbactividades.intValue() == 49) {

                datosrecurso.setIdevento(opoEventoService.encuentramaximoOpoEventoxnroopo(oposicionService.encuentraclaveprin(numeropubliseg, ordenoposeg)));
                datosrecurso.setIdoposicion(oposicionService.encuentraclaveprin(numeropubliseg, ordenoposeg));
                datosrecurso.setIdlogtrans(logTransGuardado.getIdLogTrans());
                datosrecurso.setNumero_resolucion(txtnrorecurso);
                datosrecurso.setFecha_resolucion(cldfechaactividadseg);
                datosrecurso.setTipo("rev");
                datosrecurso.setResolucion(cmbseguimientrecurso);
                datosrecurso.setOrden_op(ordenoposeg);
                datosrecurso.setEstado("AC");
                opoRecursoService.guardaOpoRecurso(datosrecurso);

            } else {
                if (cmbactividades.intValue() == 13 || cmbactividades.intValue() == 36 || cmbactividades.intValue() == 56 || cmbactividades.intValue() == 57) {

                    datosrecurso.setIdevento(opoEventoService.encuentramaximoOpoEventoxnroopo(oposicionService.encuentraclaveprin(numeropubliseg, ordenoposeg)));
                    datosrecurso.setIdoposicion(oposicionService.encuentraclaveprin(numeropubliseg, ordenoposeg));
                    datosrecurso.setIdlogtrans(logTransGuardado.getIdLogTrans());
                    datosrecurso.setNumero_resolucion(txtnrorecurso);
                    datosrecurso.setFecha_resolucion(cldfechaactividadseg);
                    datosrecurso.setTipo("j");
                    datosrecurso.setResolucion(cmbseguimientrecurso);
                    datosrecurso.setOrden_op(ordenoposeg);
                    datosrecurso.setEstado("AC");
                    opoRecursoService.guardaOpoRecurso(datosrecurso);

                }
            }            
            }else
            {                             
                 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El numero de recurso ya existe",""));
            }
                       
        }

        if (cmbactividades.intValue() == 60 || cmbactividades.intValue() == 63) {
            signomarcaActumarca = sigSignoMarcaService.listaSigSignoMarcaXPublicacion(numeropubliseg);
            signomarcaActumarca.setUbicacion("DESC");
            signomarcaActumarca.setEstadoMarca("P");
            sigSignoMarcaService.crudSigSignoMarca(signomarcaActumarca, 2);
            int swco = 0;
            Long idoposicionx = oposicionService.encuentraclaveprin(numeropubliseg, ordenoposeg);
//            marcaDemandante = opoMarcaDemandanteService.obtieneelobjetodmtexidoposicion(idoposicionx);
//            if (marcaDemandante.getDmte_public() != null) {
//                if (marcaDemandante.getDmte_public() != 0) {
//
//                    signomarcaActumarca = sigSignoMarcaService.listaSigSignoMarcaXPublicacion(marcaDemandante.getDmte_public().longValue());
//                    signomarcaActumarca.setUbicacion("DESC");
//                    signomarcaActumarca.setEstadoMarca("P");
//                    sigSignoMarcaService.crudSigSignoMarca(signomarcaActumarca, 2);
//                    swco = 1;
//
//                }
//            }
//            if (marcaDemandante.getDmte_sm() != null && swco != 1) {
//                if (marcaDemandante.getDmte_sm() != 0) {
//
//                    signomarcaActumarca = sigSignoMarcaService.listaSigSignoMarcaXSM(marcaDemandante.getDmte_public().longValue());
//                    signomarcaActumarca.setUbicacion("DESC");
//                    signomarcaActumarca.setEstadoMarca("P");
//                    sigSignoMarcaService.crudSigSignoMarca(signomarcaActumarca, 2);
//                    swco = 1;
//
//                }
//            }
//            if (marcaDemandante.getDmte_reg() != null && swco != 1) {
//                if (marcaDemandante.getDmte_reg() != 0 && marcaDemandante.getDmte_serie().equals("")) {
//
//                    signomarcaActumarca = sigSignoMarcaService.listaSigSignoMarcaXRegistro(marcaDemandante.getDmte_reg().longValue(), marcaDemandante.getDmte_serie(), "SIG");
//                    signomarcaActumarca.setUbicacion("DESC");
//                    signomarcaActumarca.setEstadoMarca("P");
//                    sigSignoMarcaService.crudSigSignoMarca(signomarcaActumarca, 2);
//                    swco = 1;
//
//                }
//            }

        }

        if (cmbactividades.intValue() == 61) {
            signomarcaActumarca = sigSignoMarcaService.listaSigSignoMarcaXPublicacion(numeropubliseg);
            signomarcaActumarca.setUbicacion("AO");
            signomarcaActumarca.setEstadoMarca("EADO");
            sigSignoMarcaService.crudSigSignoMarca(signomarcaActumarca, 2);
            int swco = 0;
            Long idoposicionx = oposicionService.encuentraclaveprin(numeropubliseg, ordenoposeg);
//            marcaDemandante = opoMarcaDemandanteService.obtieneelobjetodmtexidoposicion(idoposicionx);
//            if (marcaDemandante.getDmte_public() != null) {
//                if (marcaDemandante.getDmte_public() != 0) {
//
//                    signomarcaActumarca = sigSignoMarcaService.listaSigSignoMarcaXPublicacion(marcaDemandante.getDmte_public().longValue());
//                    signomarcaActumarca.setUbicacion("AO");
//                    signomarcaActumarca.setEstadoMarca("EADO");
//                    sigSignoMarcaService.crudSigSignoMarca(signomarcaActumarca, 2);
//                    swco = 1;
//
//                }
//            }
//            if (marcaDemandante.getDmte_sm() != null && swco != 1) {
//                if (marcaDemandante.getDmte_sm() != 0) {
//
//                    signomarcaActumarca = sigSignoMarcaService.listaSigSignoMarcaXSM(marcaDemandante.getDmte_public().longValue());
//                    signomarcaActumarca.setUbicacion("AO");
//                    signomarcaActumarca.setEstadoMarca("EADO");
//                    sigSignoMarcaService.crudSigSignoMarca(signomarcaActumarca, 2);
//                    swco = 1;
//
//                }
//            }
//            if (marcaDemandante.getDmte_reg() != null && swco != 1) {
//                if (marcaDemandante.getDmte_reg() != 0 && marcaDemandante.getDmte_serie().equals("")) {
//
//                    signomarcaActumarca = sigSignoMarcaService.listaSigSignoMarcaXRegistro(marcaDemandante.getDmte_reg().longValue(), marcaDemandante.getDmte_serie(), "SIG");
//                    signomarcaActumarca.setUbicacion("AO");
//                    signomarcaActumarca.setEstadoMarca("EADO");
//                    sigSignoMarcaService.crudSigSignoMarca(signomarcaActumarca, 2);
//                    swco = 1;
//
//                }
//            }

        }
        limpiardatos();
    }

    public void limpiardatos() throws Exception {

        txaobservacionseg = null;
        cldfechaactividadseg = null;
        // lstOpoActividad = opoActividadService.obtenerListadoActividades();        

    }

    public void onRowSelectOposicion(SelectEvent event) throws Exception {

        Long idacti = ((OpoEvento) event.getObject()).getIdactividad();
        Long idevento = ((OpoEvento) event.getObject()).getIdevento();

        int idacti2 = idacti.intValue();
        if (idacti2 == 41) {
            datosresolucionedit = opoResolucionService.extraeResolucionxidevento(idevento);
            objeto1 = Boolean.TRUE;
            objeto2 = Boolean.FALSE;
            objeto3 = Boolean.FALSE;

            txtnroresolucion = datosresolucionedit.getNumero_resolucion();
            cmbseguimientoopo = datosresolucionedit.getResolucion_oposicion();
            cmbseguimientosig = datosresolucionedit.getResolucion_signo();
            cmbseguimientocance = datosresolucionedit.getResolucion_cancelacion();

            listarecursoSig = opoResolucionService.obtenerListadoOpoRecursoxtipodominio("tipoSignoSeguimiento");
            listarecursoOpo = opoResolucionService.obtenerListadoOpoRecursoxtipodominio("tipoOposicionSeguimiento");
            listarecursoCance = opoResolucionService.obtenerListadoOpoRecursoxtipodominio("tipoCancelacionSeguimiento");
        } else {
            if (idacti2 == 9 || idacti2 == 32 || idacti2 == 48 || idacti2 == 49) {
                datosrecursoedit = opoRecursoService.extraeRecursoxidevento(idevento);
                objeto1 = Boolean.FALSE;
                objeto2 = Boolean.TRUE;
                objeto3 = Boolean.FALSE;

                txtnrorecurso = datosrecursoedit.getNumero_resolucion();
                cmbseguimientrecurso = datosrecursoedit.getResolucion();
                txttiporecurso = "rev";
                listarecurso = opoResolucionService.obtenerListadoOpoRecursoxtipodominio("tipoResuelveSeguimiento");

            } else {
                if (idacti2 == 13 || idacti2 == 36 || idacti2 == 56 || idacti2 == 57) {
                    datosrecursoedit = opoRecursoService.extraeRecursoxidevento(idevento);
                    objeto1 = Boolean.FALSE;
                    objeto2 = Boolean.TRUE;
                    objeto3 = Boolean.FALSE;

                    txtnrorecurso = datosrecursoedit.getNumero_resolucion();
                    cmbseguimientrecurso = datosrecursoedit.getResolucion();
                    txttiporecurso = "j";
                    listarecurso = opoResolucionService.obtenerListadoOpoRecursoxtipodominio("tipoResuelveSeguimiento");
                } else {
                    objeto1 = Boolean.FALSE;
                    objeto2 = Boolean.FALSE;
                    objeto3 = Boolean.FALSE;

                }

            }
        }
    }

    /**
     * *
     * Método Modificar datos del seguimiento
     *
     * Creado: Luis Quispe Fecha:20/10/2016
     *
     * @param objopoevento
     *
     * @throws java.lang.Exception
     *
     */
    public void modificarseguimiento(OpoEvento objopoevento) throws Exception {

        Date fechaSistema = comunService.obtenerFechaHoraServidor(1L);
        LogTrans logTransGuardado = logTransService.crudLogTrans(new LogTrans(idUsuarioSesion, fechaSistema), 1);

        Long idactix = objopoevento.getIdactividad();
        int idacti2x = idactix.intValue();

        if (idacti2x == 41) {

            datosresolucionedit.setNumero_resolucion(txtnroresolucion);
            datosresolucionedit.setResolucion_cancelacion(cmbseguimientocance);
            datosresolucionedit.setResolucion_oposicion(cmbseguimientoopo);
            datosresolucionedit.setResolucion_signo(cmbseguimientosig);
            datosresolucionedit.setIdlogtrans(logTransGuardado.getIdLogTrans());
            opoResolucionService.modificarOporesolucion(datosresolucionedit);
            datosoposicion = oposicionService.obtoposicionxnroopo(oposicionService.encuentraclaveprin(numeropubliseg, ordenoposeg));
            datosoposicion.setId_estado(opoActividadService.muestraidestadoxidactividad(objopoevento.getIdactividad()));
            oposicionService.modificaOposicion(datosoposicion);
            listarecursoSig = opoResolucionService.obtenerListadoOpoRecursoxtipodominio("tipoSignoSeguimiento");
            listarecursoOpo = opoResolucionService.obtenerListadoOpoRecursoxtipodominio("tipoOposicionSeguimiento");
            listarecursoCance = opoResolucionService.obtenerListadoOpoRecursoxtipodominio("tipoCancelacionSeguimiento");

        } else {
            if (idacti2x == 9 || idacti2x == 32 || idacti2x == 48 || idacti2x == 49) {

                datosrecursoedit.setNumero_resolucion(txtnrorecurso);
                datosrecursoedit.setResolucion(cmbseguimientrecurso);
                opoRecursoService.modificarOporecurso(datosrecursoedit);

                listarecurso = opoResolucionService.obtenerListadoOpoRecursoxtipodominio("tipoResuelveSeguimiento");
            } else {
                if (idacti2x == 13 || idacti2x == 36 || idacti2x == 56 || idacti2x == 57) {
                    datosrecursoedit.setNumero_resolucion(txtnrorecurso);
                    datosrecursoedit.setResolucion(cmbseguimientrecurso);
                    opoRecursoService.modificarOporecurso(datosrecursoedit);
                    listarecurso = opoResolucionService.obtenerListadoOpoRecursoxtipodominio("tipoResuelveSeguimiento");
                } else {
                    objeto1 = Boolean.FALSE;
                    objeto2 = Boolean.FALSE;
                    objeto3 = Boolean.FALSE;

                }

            }

            if (cmbactividades != null) {
                if (cmbactividades.intValue() == 61 || cmbactividades.intValue() == 60) {
                    signomarcaActumarca = sigSignoMarcaService.listaSigSignoMarcaXPublicacion(numeropubliseg);
                    signomarcaActumarca.setUbicacion("AO");
                    signomarcaActumarca.setEstadoMarca("EADO");
                    sigSignoMarcaService.crudSigSignoMarca(signomarcaActumarca, 2);
                }
            }
        }

        objopoevento.setEstado("AC");
        opoEventoService.modificarOpoEvento(objopoevento);
        datoshistorialseg.setIdoposicion(oposicionService.encuentraclaveprin(numeropubliseg, ordenoposeg));
        datoshistorialseg.setIdlogtrans(logTransGuardado.getIdLogTrans());
        datoshistorialseg.setEstado(opoActividadService.muestranomdelaactividadxidactividad(objopoevento.getIdactividad()));
        datoshistorialseg.setFecha_lim(cldfechaactividadseg);
        datoshistorialseg.setObservacion(objopoevento.getObservacion());
        datoshistorialseg.setOperacion("MOD ACTIVIDAD");
        datoshistorialseg.setFecha_operacion(comunService.obtenerFechaHoraServidor(1L));
        datoshistorialseg.setId_usuario(idUsuarioSesion);

        opoHistorialService.guardaOpoHistorial(datoshistorialseg);

    }

    /**
     * *
     * Método Eliminar daos del seguimiento
     *
     * Creado: Luis Quispe Fecha:20/10/2016
     *
     * @param objopoeventoeli
     * @throws java.lang.Exception
     */
    public void eliminarseguimiento(OpoEvento objopoeventoeli) throws Exception {
        Date fechaSistema = comunService.obtenerFechaHoraServidor(1L);
        LogTrans logTransGuardado = logTransService.crudLogTrans(new LogTrans(idUsuarioSesion, fechaSistema), 1);

        objopoeventoeli.setEstado("NA");
        opoEventoService.modificarOpoEvento(objopoeventoeli);
        listaevento = opoEventoService.obtenerListadoeventoxidoposicion(oposicionService.encuentraclaveprin(numeropubliseg, ordenoposeg));
        Long nroidenento = objopoeventoeli.getIdevento();
        if (objopoeventoeli.getIdevento() != null && (objopoeventoeli.getIdactividad() == 41L
                || objopoeventoeli.getIdactividad() == 9L || objopoeventoeli.getIdactividad() == 32L
                || objopoeventoeli.getIdactividad() == 48L || objopoeventoeli.getIdactividad() == 49L
                || objopoeventoeli.getIdactividad() == 13L || objopoeventoeli.getIdactividad() == 36L
                || objopoeventoeli.getIdactividad() == 56L || objopoeventoeli.getIdactividad() == 57L)) {
            datosresolucioncont = opoResolucionService.extraeResolucionxidevento(objopoeventoeli.getIdevento());

            if (datosresolucioncont.getIdresolucion() != null) {

                datosresolucioncont.setEstado("NA");
                opoResolucionService.modificarOporesolucion(datosresolucioncont);
            }
            datosrecursocont = opoRecursoService.extraeRecursoxidevento(objopoeventoeli.getIdevento());
            if (datosrecursocont.getIdrecurso() != null) {
                datosrecursocont.setEstado("NA");
                opoRecursoService.modificarOporecurso(datosrecursocont);
            }

        }
        if (objopoeventoeli.getIdactividad() == 61L || objopoeventoeli.getIdactividad() == 60L || objopoeventoeli.getIdactividad() == 63L) {
            signomarcaActumarca = sigSignoMarcaService.listaSigSignoMarcaXPublicacion(numeropubliseg);
            signomarcaActumarca.setUbicacion("PIOP");
            signomarcaActumarca.setEstadoMarca("OPO");
            sigSignoMarcaService.crudSigSignoMarca(signomarcaActumarca, 2);

            int swco = 0;
            Long idoposicionx = oposicionService.encuentraclaveprin(numeropubliseg, ordenoposeg);
//            marcaDemandante = opoMarcaDemandanteService.obtieneelobjetodmtexidoposicion(idoposicionx);
//            if (marcaDemandante.getDmte_public() != null) {
//                if (marcaDemandante.getDmte_public() != 0) {
//
//                    signomarcaActumarca = sigSignoMarcaService.listaSigSignoMarcaXPublicacion(marcaDemandante.getDmte_public().longValue());
//                    signomarcaActumarca.setUbicacion("PIOP");
//                    signomarcaActumarca.setEstadoMarca("OPO");
//                    sigSignoMarcaService.crudSigSignoMarca(signomarcaActumarca, 2);
//                    swco = 1;
//
//                }
//            }
//            if (marcaDemandante.getDmte_sm() != null && swco != 1) {
//                if (marcaDemandante.getDmte_sm() != 0) {
//
//                    signomarcaActumarca = sigSignoMarcaService.listaSigSignoMarcaXSM(marcaDemandante.getDmte_public().longValue());
//                    signomarcaActumarca.setUbicacion("PIOP");
//                    signomarcaActumarca.setEstadoMarca("OPO");
//                    sigSignoMarcaService.crudSigSignoMarca(signomarcaActumarca, 2);
//                    swco = 1;
//
//                }
//            }
//            if (marcaDemandante.getDmte_reg() != null && swco != 1) {
//                if (marcaDemandante.getDmte_reg() != 0 && marcaDemandante.getDmte_serie().equals("")) {
//
//                    signomarcaActumarca = sigSignoMarcaService.listaSigSignoMarcaXRegistro(marcaDemandante.getDmte_reg().longValue(), marcaDemandante.getDmte_serie(), "SIG");
//                    signomarcaActumarca.setUbicacion("PIOP");
//                    signomarcaActumarca.setEstadoMarca("OPO");
//                    sigSignoMarcaService.crudSigSignoMarca(signomarcaActumarca, 2);
//                    swco = 1;
//
//                }
//            }
        }

        opoEventoService.modificarOpoEvento(objopoeventoeli);
        datoshistorialseg.setIdoposicion(oposicionService.encuentraclaveprin(numeropubliseg, ordenoposeg));
        datoshistorialseg.setIdlogtrans(logTransGuardado.getIdLogTrans());
        datoshistorialseg.setEstado("ADMITIDA");
        datoshistorialseg.setFecha_lim(cldfechaactividadseg);
        datoshistorialseg.setObservacion(objopoeventoeli.getObservacion());
        datoshistorialseg.setOperacion("REM ACTIVIDAD");
        datoshistorialseg.setFecha_operacion(comunService.obtenerFechaHoraServidor(1L));
        datoshistorialseg.setId_usuario(idUsuarioSesion);
        opoHistorialService.guardaOpoHistorial(datoshistorialseg);
    }

    /**
     * *
     * Método Retorna Actividad
     *
     * Creado: Luis Quispe Fecha:20/10/201
     *
     * @param idactividad
     * @return
     * @throws java.lang.Exception
     */
    public String obtieneactividadxidactividad(Long idactividad) throws Exception {

        return opoActividadService.muestranomdelaactividadxidactividad(idactividad);

    }

    public void cerrarDialogo() {
        //pass back to root
        RequestContext.getCurrentInstance().closeDialog("Exit");
    }

    public void metodocarga() throws Exception {

        if (cmbactividades.intValue() == 41) {
            objeto1 = Boolean.TRUE;
            objeto2 = Boolean.FALSE;
            objeto3 = Boolean.FALSE;
            listarecursoSig = opoResolucionService.obtenerListadoOpoRecursoxtipodominio("tipoSignoSeguimiento");
            listarecursoOpo = opoResolucionService.obtenerListadoOpoRecursoxtipodominio("tipoOposicionSeguimiento");
            listarecursoCance = opoResolucionService.obtenerListadoOpoRecursoxtipodominio("tipoCancelacionSeguimiento");

        } else {
            if (cmbactividades.intValue() == 9 || cmbactividades.intValue() == 32 || cmbactividades.intValue() == 48 || cmbactividades.intValue() == 49) {
                objeto1 = Boolean.FALSE;
                objeto2 = Boolean.TRUE;
                objeto3 = Boolean.FALSE;
                txttiporecurso = "rev";
                listarecurso = opoResolucionService.obtenerListadoOpoRecursoxtipodominio("tipoResuelveSeguimiento");

            } else {
                if (cmbactividades.intValue() == 13 || cmbactividades.intValue() == 36 || cmbactividades.intValue() == 56 || cmbactividades.intValue() == 57) {
                    objeto1 = Boolean.FALSE;
                    objeto2 = Boolean.TRUE;
                    objeto3 = Boolean.FALSE;
                    txttiporecurso = "j";
                    listarecurso = opoResolucionService.obtenerListadoOpoRecursoxtipodominio("tipoResuelveSeguimiento");
                } else {
                    objeto1 = Boolean.FALSE;
                    objeto2 = Boolean.FALSE;
                    objeto3 = Boolean.FALSE;

                }

            }
        }

    }

    //Geters y Seters//
    public Long getNumeropubliseg() {
        return numeropubliseg;
    }

    public void setNumeropubliseg(Long numeropubliseg) {
        this.numeropubliseg = numeropubliseg;
    }

    public Integer getOrdenoposeg() {
        return ordenoposeg;
    }

    public void setOrdenoposeg(Integer ordenoposeg) {
        this.ordenoposeg = ordenoposeg;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public String getXx() {
        return xx;
    }

    public void setXx(String xx) {
        this.xx = xx;
    }

    public List<OpoEvento> getListaevento() {
        return listaevento;
    }

    public void setListaevento(List<OpoEvento> listaevento) {
        this.listaevento = listaevento;
    }

    public OpoEventoService getOpoEventoService() {
        return opoEventoService;
    }

    public void setOpoEventoService(OpoEventoService opoEventoService) {
        this.opoEventoService = opoEventoService;
    }

    public OposicionService getOposicionService() {
        return oposicionService;
    }

    public void setOposicionService(OposicionService oposicionService) {
        this.oposicionService = oposicionService;
    }

    public Long getCmbactividades() {
        return cmbactividades;
    }

    public void setCmbactividades(Long cmbactividades) {
        this.cmbactividades = cmbactividades;
    }

    public List<OpoActividad> getLstOpoActividad() {
        return lstOpoActividad;
    }

    public void setLstOpoActividad(List<OpoActividad> lstOpoActividad) {
        this.lstOpoActividad = lstOpoActividad;
    }

    public OpoActividadService getOpoActividadService() {
        return opoActividadService;
    }

    public void setOpoActividadService(OpoActividadService opoActividadService) {
        this.opoActividadService = opoActividadService;
    }

    public Date getCldfechaactividadseg() {
        return cldfechaactividadseg;
    }

    public void setCldfechaactividadseg(Date cldfechaactividadseg) {
        this.cldfechaactividadseg = cldfechaactividadseg;
    }

    public String getTxaobservacionseg() {
        return txaobservacionseg;
    }

    public void setTxaobservacionseg(String txaobservacionseg) {
        this.txaobservacionseg = txaobservacionseg;
    }

    public OpoEvento getDatosopoeventoseg() {
        return datosopoeventoseg;
    }

    public void setDatosopoeventoseg(OpoEvento datosopoeventoseg) {
        this.datosopoeventoseg = datosopoeventoseg;
    }

    public OpoHistorial getDatoshistorialseg() {
        return datoshistorialseg;
    }

    public void setDatoshistorialseg(OpoHistorial datoshistorialseg) {
        this.datoshistorialseg = datoshistorialseg;
    }

    public OpoHistorialService getOpoHistorialService() {
        return opoHistorialService;
    }

    public void setOpoHistorialService(OpoHistorialService opoHistorialService) {
        this.opoHistorialService = opoHistorialService;
    }

    public ComunService getComunService() {
        return comunService;
    }

    public void setComunService(ComunService comunService) {
        this.comunService = comunService;
    }

    public Boolean getObjeto1() {
        return objeto1;
    }

    public void setObjeto1(Boolean objeto1) {
        this.objeto1 = objeto1;
    }

    public Boolean getObjeto2() {
        return objeto2;
    }

    public void setObjeto2(Boolean objeto2) {
        this.objeto2 = objeto2;
    }

    public Boolean getObjeto3() {
        return objeto3;
    }

    public void setObjeto3(Boolean objeto3) {
        this.objeto3 = objeto3;
    }

    public OpoResolucionService getOpoResolucionService() {
        return opoResolucionService;
    }

    public void setOpoResolucionService(OpoResolucionService opoResolucionService) {
        this.opoResolucionService = opoResolucionService;
    }

    public List<Dominio> getListarecursoOpo() {
        return listarecursoOpo;
    }

    public void setListarecursoOpo(List<Dominio> listarecursoOpo) {
        this.listarecursoOpo = listarecursoOpo;
    }

    public List<Dominio> getListarecursoSig() {
        return listarecursoSig;
    }

    public void setListarecursoSig(List<Dominio> listarecursoSig) {
        this.listarecursoSig = listarecursoSig;
    }

    public List<Dominio> getListarecursoCance() {
        return listarecursoCance;
    }

    public void setListarecursoCance(List<Dominio> listarecursoCance) {
        this.listarecursoCance = listarecursoCance;
    }

    public String getCmbseguimientoopo() {
        return cmbseguimientoopo;
    }

    public void setCmbseguimientoopo(String cmbseguimientoopo) {
        this.cmbseguimientoopo = cmbseguimientoopo;
    }

    public String getCmbseguimientosig() {
        return cmbseguimientosig;
    }

    public void setCmbseguimientosig(String cmbseguimientosig) {
        this.cmbseguimientosig = cmbseguimientosig;
    }

    public String getCmbseguimientocance() {
        return cmbseguimientocance;
    }

    public void setCmbseguimientocance(String cmbseguimientocance) {
        this.cmbseguimientocance = cmbseguimientocance;
    }

    public List<Dominio> getListarecurso() {
        return listarecurso;
    }

    public void setListarecurso(List<Dominio> listarecurso) {
        this.listarecurso = listarecurso;
    }

    public Integer getTxtnroresolucion() {
        return txtnroresolucion;
    }

    public void setTxtnroresolucion(Integer txtnroresolucion) {
        this.txtnroresolucion = txtnroresolucion;
    }

    public OpoRecurso getDatosrecurso() {
        return datosrecurso;
    }

    public void setDatosrecurso(OpoRecurso datosrecurso) {
        this.datosrecurso = datosrecurso;
    }

    public OpoResolucion getDatosresolucion() {
        return datosresolucion;
    }

    public void setDatosresolucion(OpoResolucion datosresolucion) {
        this.datosresolucion = datosresolucion;
    }

    public Integer getTxtnrorecurso() {
        return txtnrorecurso;
    }

    public void setTxtnrorecurso(Integer txtnrorecurso) {
        this.txtnrorecurso = txtnrorecurso;
    }

    public String getCmbseguimientrecurso() {
        return cmbseguimientrecurso;
    }

    public void setCmbseguimientrecurso(String cmbseguimientrecurso) {
        this.cmbseguimientrecurso = cmbseguimientrecurso;
    }

    public String getTxttiporecurso() {
        return txttiporecurso;
    }

    public void setTxttiporecurso(String txttiporecurso) {
        this.txttiporecurso = txttiporecurso;
    }

    public OpoRecursoService getOpoRecursoService() {
        return opoRecursoService;
    }

    public void setOpoRecursoService(OpoRecursoService opoRecursoService) {
        this.opoRecursoService = opoRecursoService;
    }

    public OpoRecurso getDatosrecursocont() {
        return datosrecursocont;
    }

    public void setDatosrecursocont(OpoRecurso datosrecursocont) {
        this.datosrecursocont = datosrecursocont;
    }

    public OpoResolucion getDatosresolucioncont() {
        return datosresolucioncont;
    }

    public void setDatosresolucioncont(OpoResolucion datosresolucioncont) {
        this.datosresolucioncont = datosresolucioncont;
    }

    public Long getIdUsuarioSesion() {
        return idUsuarioSesion;
    }

    public void setIdUsuarioSesion(Long idUsuarioSesion) {
        this.idUsuarioSesion = idUsuarioSesion;
    }

    public LogTransService getLogTransService() {
        return logTransService;
    }

    public void setLogTransService(LogTransService logTransService) {
        this.logTransService = logTransService;
    }

    public OpoEvento getSelectedOpo() {
        return selectedOpo;
    }

    public void setSelectedOpo(OpoEvento selectedOpo) {
        this.selectedOpo = selectedOpo;
    }

    public OpoResolucion getDatosresolucionedit() {
        return datosresolucionedit;
    }

    public void setDatosresolucionedit(OpoResolucion datosresolucionedit) {
        this.datosresolucionedit = datosresolucionedit;
    }

    public OpoRecurso getDatosrecursoedit() {
        return datosrecursoedit;
    }

    public void setDatosrecursoedit(OpoRecurso datosrecursoedit) {
        this.datosrecursoedit = datosrecursoedit;
    }

    public SigSignoMarca getSignomarcaActumarca() {
        return signomarcaActumarca;
    }

    public void setSignomarcaActumarca(SigSignoMarca signomarcaActumarca) {
        this.signomarcaActumarca = signomarcaActumarca;
    }

    public SigSignoMarcaService getSigSignoMarcaService() {
        return sigSignoMarcaService;
    }

    public void setSigSignoMarcaService(SigSignoMarcaService sigSignoMarcaService) {
        this.sigSignoMarcaService = sigSignoMarcaService;
    }

    public OpoMarcademandante getMarcaDemandante() {
        return marcaDemandante;
    }

    public void setMarcaDemandante(OpoMarcademandante marcaDemandante) {
        this.marcaDemandante = marcaDemandante;
    }

    public OpoMarcademandanteService getOpoMarcaDemandanteService() {
        return opoMarcaDemandanteService;
    }

    public void setOpoMarcaDemandanteService(OpoMarcademandanteService opoMarcaDemandanteService) {
        this.opoMarcaDemandanteService = opoMarcaDemandanteService;
    }

    public Oposicion getDatosoposicion() {
        return datosoposicion;
    }

    public void setDatosoposicion(Oposicion datosoposicion) {
        this.datosoposicion = datosoposicion;
    }

    public OpoMarcademandante getMarcaDemte() {
        return marcaDemte;
    }

    public void setMarcaDemte(OpoMarcademandante marcaDemte) {
        this.marcaDemte = marcaDemte;
    }

    public OpoMarcademandada getMarcaDemandada() {
        return marcaDemandada;
    }

    public void setMarcaDemandada(OpoMarcademandada marcaDemandada) {
        this.marcaDemandada = marcaDemandada;
    }

    public OpoMarcademandadaService getOpoMarcaDemandadaService() {
        return opoMarcaDemandadaService;
    }

    public void setOpoMarcaDemandadaService(OpoMarcademandadaService opoMarcaDemandadaService) {
        this.opoMarcaDemandadaService = opoMarcaDemandadaService;
    }

    public SigSignoMarca getSignomarcaActuObs() {
        return signomarcaActuObs;
    }

    public void setSignomarcaActuObs(SigSignoMarca signomarcaActuObs) {
        this.signomarcaActuObs = signomarcaActuObs;
    }

    public ObservacionTramite getSigmarcaObs() {
        return sigmarcaObs;
    }

    public void setSigmarcaObs(ObservacionTramite sigmarcaObs) {
        this.sigmarcaObs = sigmarcaObs;
    }

    public ObservacionTramiteService getObservacionTramiteService() {
        return observacionTramiteService;
    }

    public void setObservacionTramiteService(ObservacionTramiteService observacionTramiteService) {
        this.observacionTramiteService = observacionTramiteService;
    }

    public OpoEstadoService getOpoEstadoService() {
        return opoEstadoService;
    }

    public void setOpoEstadoService(OpoEstadoService opoEstadoService) {
        this.opoEstadoService = opoEstadoService;
    }

    public ObservacionTramite getSigmarcaObsEdit() {
        return sigmarcaObsEdit;
    }

    public void setSigmarcaObsEdit(ObservacionTramite sigmarcaObsEdit) {
        this.sigmarcaObsEdit = sigmarcaObsEdit;
    }

    public String getMarcasvs() {
        return marcasvs;
    }

    public void setMarcasvs(String marcasvs) {
        this.marcasvs = marcasvs;
    }

    public ObservacionTramite getSigmarcaObsNew() {
        return sigmarcaObsNew;
    }

    public void setSigmarcaObsNew(ObservacionTramite sigmarcaObsNew) {
        this.sigmarcaObsNew = sigmarcaObsNew;
    }

}
