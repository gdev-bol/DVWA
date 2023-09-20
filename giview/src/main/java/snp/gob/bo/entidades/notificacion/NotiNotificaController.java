/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.entidades.notificacion;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import snp.gob.bo.entidades.bean.common.AbstractManagedBean;
import snp.gob.bo.gimodel.entidad.Dominio;
import snp.gob.bo.gimodel.entidad.Etapa;
import snp.gob.bo.gimodel.entidad.LogTrans;
import snp.gob.bo.gimodel.entidad.ModModificacion;
import snp.gob.bo.gimodel.entidad.Notificacion;
import snp.gob.bo.gimodel.entidad.RenSolicitudRenovacion;
import snp.gob.bo.gimodel.entidad.Seguimiento;
import snp.gob.bo.gimodel.entidad.SigSignoMarca;
import snp.gob.bo.gimodel.entidad.Usuario;
import snp.gob.bo.gimodel.servicio.ComunService;
import snp.gob.bo.gimodel.servicio.DominioService;
import snp.gob.bo.gimodel.servicio.EtapaService;
import snp.gob.bo.gimodel.servicio.FlujoTiempoService;
import snp.gob.bo.gimodel.servicio.LogTransService;
import snp.gob.bo.gimodel.servicio.ModModificacionService;
import snp.gob.bo.gimodel.servicio.NotificacionService;
import snp.gob.bo.gimodel.servicio.RenSolicitudRenovacionService;
import snp.gob.bo.gimodel.servicio.SeguimientoService;
import snp.gob.bo.gimodel.servicio.SigHistorialService;
import snp.gob.bo.gimodel.servicio.SigSignoMarcaService;
import snp.gob.bo.gimodel.servicio.UsuarioPaginaService;
import snp.gob.bo.gimodel.servicio.UsuarioService;

/**
 *
 * @author levi
 */
@ManagedBean
@ViewScoped
public class NotiNotificaController extends AbstractManagedBean implements Serializable {

    private List<Notificacion> tramites = new ArrayList<Notificacion>();
    private List<Notificacion> tramitesAgarrados = new ArrayList<Notificacion>();
    private List<Usuario> listUsuarioOpera = new ArrayList<Usuario>();
    private Etapa etapa;
    private boolean pintaOposicion = false;
    private String descripcion;
    private String solicitante;
    private String apoderado;
    private String fojas;
    private String notificaCon;
    private String celular;
    private String domicilio;
    private String comboEstado;
    private Date fechaIngreso;
    private Date fechaRecepcion;
    private boolean habCampos;
    private String bloque;
    private Integer numCorrela;
    private String tramite;
    private String numero;
    private String gestion;
    private Date fechaNotificacion;
    private Date fechaDevolucion;
    private String comboNotificadoEn;
    private String obsSolicitante;
    private String operadorSolicitante;
    private Long idoperador;
    private Integer bloqueBuscar;
    private String comboCiudadNotifi;
    private String obsNotificador;
    private boolean botonActiva;
    /**
     * ****************Para oposiciones****
     */
    /**
     * Para Demandante
     */
    private Date fechaNotificacionDante;
    private Date fechaDevolucionDante;
    private String comboEstadoDante;
    private String comboNotificadoEnDante;
    private String comboCiudadNotifiDante;
    private String obsNotificadorDante;
    private String descripcionDante;
    private String solicitanteDante;
    private String apoderadoDante;
    private String fojasDante;
    private String notificaConDante;
    private String celularDante;
    private String domicilioDante;
    /**
     * Para Demandado
     */
    private Date fechaNotificacionDado;
    private Date fechaDevolucionDado;
    private String comboEstadoDado;
    private String comboNotificadoEnDado;
    private String comboCiudadNotifiDado;
    private String obsNotificadorDado;
    private String descripcionDado;
    private String solicitanteDado;
    private String apoderadoDado;
    private String fojasDado;
    private String notificaConDado;
    private String celularDado;
    private String domicilioDado;
    private String imgSenapi;
    private String imgMinisterio;

    private Long logtrans;
    //Este boolenao de aqui abajo dice si podran modificarse  los campos fechas por parte de los notificadores
    private boolean activaFechasDeman;
    private boolean activaFechasDado;
    private boolean activaFecha;
    //   private Integer pos;
    List<HistorialPojo> listHistPo = new ArrayList<HistorialPojo>();
    List<TramiteTransaccion> listTramite = new ArrayList<TramiteTransaccion>();

    // parametros del reporte
    private Map<String, Object> parametros = new HashMap<String, Object>();
    private List<NotiFormularioPojo> listNotiFormularioPojo = new ArrayList<NotiFormularioPojo>();
    private List<Dominio> lstCiudadNotificacion;

    @ManagedProperty("#{dominioService}")
    private DominioService dominioService;
    @ManagedProperty(value = "#{usuarioService}")
    private UsuarioService usuarioService;
    @ManagedProperty(value = "#{notificacionService}")
    private NotificacionService notificacionService;
    @ManagedProperty(value = "#{usuarioPaginaService}")
    private UsuarioPaginaService usuarioPaginaService;
    @ManagedProperty(value = "#{comunService}")
    private ComunService comunService;
    @ManagedProperty(value = "#{sigSignoMarcaService}")
    private SigSignoMarcaService sigSignoMarcaService;
    @ManagedProperty(value = "#{logTransService}")
    private LogTransService logTransService;
    @ManagedProperty(value = "#{seguimientoService}")
    private SeguimientoService seguimientoService;
    @ManagedProperty(value = "#{modModificacionService}")
    private ModModificacionService modModificacionService;
    @ManagedProperty(value = "#{renSolicitudRenovacionService}")
    private RenSolicitudRenovacionService renSolicitudRenovacionService;
    @ManagedProperty(value = "#{etapaService}")
    private EtapaService etapaService;
    @ManagedProperty(value = "#{sigHistorialService}")
    private SigHistorialService sigHistorialService;
    @ManagedProperty(value = "#{flujoTiempoService}")
    private FlujoTiempoService flujoTiempoService;
    @PostConstruct
    public void init() {
        try {
            //es para prueba de botonq ue se puede habilitar o deshabilitar por usuario , mostrado a susy
            // botonActiva = usuarioPaginaService.estadoBotonUsuariusuarioPaginaServiceo(super.getIdUsuarioSession().toString(),"Boton Prueba");
             /*Es para cuando el tramite esta en estado DEV no deberia activarse enla pantalla*/
            activaFechasDeman = false;
            activaFechasDado = false;

            lstCiudadNotificacion = dominioService.obtenerListadoDominio("ciudad_notificacion");

            for (int i = 0; i <= 100; i++) {
                TramiteTransaccion tra = new TramiteTransaccion();
                tra.setBloque("A");
                tra.setOperador("Juan Lucia");
                listTramite.add(tra);
            }

            listUsuarioOpera = usuarioService.listaUsuario();

            etapa = etapaService.etapaXIdEtapa(super.getIdEtapaSession());

            /*Saco para el logtrans*/
            Date fechaSistema = comunService.obtenerFechaHoraServidor(1L);
            List<Usuario> listUsuario = usuarioService.listaUsuarioXidPagina(super.getIdUsuarioSession());
            LogTrans logTransGuardado = logTransService.crudLogTrans(new LogTrans(super.getIdUsuarioSession(), fechaSistema), 1);
            logtrans = logTransGuardado.getIdLogTrans();

        } catch (Exception ex) {
            Logger.getLogger(NotiNotificaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Esta funcion es para ejemplo de susuana que quiere habilitar o deshabilitar un bootn emn especifico
    public boolean activaDesactiva() throws Exception {
        Boolean activado = usuarioPaginaService.estadoBotonUsuario(super.getIdUsuarioSession().toString(), "Boton Prueba");
        return activado;

    }

    public void despintaColumna() {
        /*Este for coloca en vacio el Demandado_cod_estado para que no pinte enla vista loque pordefecto voy llenando en camino, esto es para los que no son oposicion*/
        /*No deberia hacerse asi ya que desde el princiipio del flujo deberia colocarse al demandado vacio para los uqe no son oposiciones.*/
        for (int i = 0; i <= tramites.size() - 1; i++) {  //System.out.println("VALOR:::"+tramites.get(i).getTipo_tramite_notificacion().indexOf("OP"));
            //if ((tramites.get(i).getTipo_tramite_notificacion().indexOf("OP")) == -1)
            if (!tramites.get(i).getTipo_tramite_notificacion().equals("OPOSICION SIG")
                    && !tramites.get(i).getTipo_tramite_notificacion().equals("CANCELACION")
                    && !tramites.get(i).getTipo_tramite_notificacion().equals("NULIDAD SIG")
                    && !tramites.get(i).getTipo_tramite_notificacion().equals("IF")
                    && !tramites.get(i).getTipo_tramite_notificacion().equals("MEF")) { //Se setea para las tuplas que no sonoposiciones
                tramites.get(i).setDemandado_cod_estado("");

            }

        }

    }

    public void busca() throws Exception {
        
        tramites.clear();
        /*System.out.println("entra.....");
         System.out.println("idoperador:"+idoperador);
         System.out.println("bloquebuscar:"+bloqueBuscar);
         */
        tramites = notificacionService.listaNotificacionParaNotificar(idoperador, bloqueBuscar);

        for (int i = 0; i <= tramites.size() - 1; i++) {

        }
        despintaColumna();

    }

    public void anterior() throws Exception {
        limpiaDatosPeticionSingular();
        limpiaDatosPeticionOposicion();
        limpiaDatosTablaTramitesHistorial();
        bloqueBuscar = bloqueBuscar - 1;

        tramites.clear();
        tramites = notificacionService.listaNotificacionParaNotificar(idoperador, bloqueBuscar);
        despintaColumna();
    }

    public void siguiente() throws Exception {
        limpiaDatosPeticionSingular();
        limpiaDatosPeticionOposicion();
        limpiaDatosTablaTramitesHistorial();
        bloqueBuscar = bloqueBuscar + 1;

        tramites.clear();
        tramites = notificacionService.listaNotificacionParaNotificar(idoperador, bloqueBuscar);
        despintaColumna();
    }

    public void limpiaDatosPeticionSingular() {
        this.bloque = "";
        this.numCorrela = null;
        this.tramite = "";
        this.numero = "";
        this.gestion = "";
        this.comboEstado = "";
        this.fechaNotificacion = null;
        this.fechaDevolucion = null;
        this.comboEstado = "";
        this.comboNotificadoEn = "";
        this.comboCiudadNotifi = "";
        this.fechaIngreso = null;
        this.fechaRecepcion = null;
        this.obsNotificador = "";
        this.descripcion = "";
        this.solicitante = "";
        this.apoderado = "";
        this.fojas = "";
        this.notificaCon = "";
        this.celular = "";
        this.domicilio = "";
        this.obsSolicitante = "";
        this.operadorSolicitante = "";

    }

    public void limpiaDatosPeticionOposicion() {
        this.bloque = "";
        this.numCorrela = null;
        this.tramite = "";
        this.numero = "";
        this.gestion = "";
        this.fechaIngreso = null;
        this.fechaRecepcion = null;
        this.comboEstadoDante = "";
        this.fechaNotificacionDante = null;
        this.fechaDevolucionDante = null;
        this.comboNotificadoEnDante = "";
        this.comboCiudadNotifiDante = "";
        this.obsNotificadorDante = "";
        this.descripcionDante = "";
        this.solicitanteDante = "";
        this.apoderadoDante = "";
        this.fojasDante = "";
        this.notificaConDante = "";
        this.celularDante = "";
        this.domicilioDante = "";

        this.comboEstadoDado = "";
        this.fechaNotificacionDado = null;
        this.fechaDevolucionDado = null;
        this.comboNotificadoEnDado = "";
        this.comboCiudadNotifiDado = "";
        this.obsNotificadorDado = "";
        this.descripcionDado = "";
        this.solicitanteDado = "";
        this.apoderadoDado = "";
        this.fojasDado = "";
        this.notificaConDado = "";
        this.celularDado = "";
        this.domicilioDado = "";

        this.obsSolicitante = "";
        this.operadorSolicitante = "";
    }

    public void limpiaDatosTablaTramitesHistorial() {
        tramites.clear();
        listHistPo.clear();
        operadorSolicitante = "";
    }

    public void actualiza() throws Exception {
        limpiaDatosPeticionSingular();
        limpiaDatosPeticionOposicion();
        int resultado = tramitesAgarrados.get(0).getTipo_tramite_notificacion().indexOf("OP");
        //   System.out.println("Valor de resultado::" + resultado);

        if (tramitesAgarrados.get(0).getTipo_tramite_notificacion().equals("OPOSICION SIG")
                || tramitesAgarrados.get(0).getTipo_tramite_notificacion().equals("CANCELACION")
                || tramitesAgarrados.get(0).getTipo_tramite_notificacion().equals("NULIDAD SIG")
                || tramitesAgarrados.get(0).getTipo_tramite_notificacion().equals("IF")
                || tramitesAgarrados.get(0).getTipo_tramite_notificacion().equals("MEF")
                ) {
            //if (resultado != -1) {

            pintaOposicion = true;
            this.bloque = bloqueBuscar.toString();
            this.numCorrela = tramitesAgarrados.get(0).getNro_exped();
            this.tramite = tramitesAgarrados.get(0).getTipo_tramite_notificacion();
            this.numero = tramitesAgarrados.get(0).getExpediente();
            this.gestion = tramitesAgarrados.get(0).getGestion().toString();
            this.fechaIngreso = tramitesAgarrados.get(0).getFecha_ingreso();
            this.fechaRecepcion = tramitesAgarrados.get(0).getFecha_recep();
            /**
             * *Para el demandante**
             */
            if (tramitesAgarrados.get(0).getDemandante_fecha_noti() != null && !tramitesAgarrados.get(0).getDemandante_fecha_noti().equals("")) {
                this.fechaNotificacionDante = tramitesAgarrados.get(0).getDemandante_fecha_noti();
            }
            if (tramitesAgarrados.get(0).getDemandante_fecha_devol() != null && !tramitesAgarrados.get(0).getDemandante_fecha_devol().equals("")) {
                this.fechaDevolucionDante = tramitesAgarrados.get(0).getDemandante_fecha_devol();
            }
            if (tramitesAgarrados.get(0).getDemandante_cod_estado() != null && !tramitesAgarrados.get(0).getDemandante_cod_estado().equals("")) {
                if (tramitesAgarrados.get(0).getDemandante_cod_estado().equals("DEV")) {
                    activaFechasDeman = true;
                } else {
                    activaFechasDeman = false;
                }
                this.comboEstadoDante = tramitesAgarrados.get(0).getDemandante_cod_estado();

            }
            if (tramitesAgarrados.get(0).getDemandante_lugar_notificacion() != null && !tramitesAgarrados.get(0).getDemandante_lugar_notificacion().equals("")) {
                this.comboNotificadoEnDante = tramitesAgarrados.get(0).getDemandante_lugar_notificacion();
            }
            if (tramitesAgarrados.get(0).getDemandante_ciudad_notif() != null && !tramitesAgarrados.get(0).getDemandante_ciudad_notif().equals("")) {
                this.comboCiudadNotifiDante = tramitesAgarrados.get(0).getDemandante_ciudad_notif();
            }
            this.descripcionDante = tramitesAgarrados.get(0).getDemandante();
            this.solicitanteDante = tramitesAgarrados.get(0).getDemandante_solic();
            this.apoderadoDante = tramitesAgarrados.get(0).getDemandante_apod();
            this.fojasDante = tramitesAgarrados.get(0).getDemandante_fojas();
            this.notificaConDante = tramitesAgarrados.get(0).getDemandante_con();
            this.celularDante = tramitesAgarrados.get(0).getDemandante_cel();
            this.domicilioDante = tramitesAgarrados.get(0).getDemandante_direc();
            /**
             * *Para el demandado**
             */
            if (tramitesAgarrados.get(0).getDemandado_fecha_noti() != null && !tramitesAgarrados.get(0).getDemandado_fecha_noti().equals("")) {
                this.fechaNotificacionDado = tramitesAgarrados.get(0).getDemandado_fecha_noti();
            }
            if (tramitesAgarrados.get(0).getDemandado_fecha_devol() != null && !tramitesAgarrados.get(0).getDemandado_fecha_devol().equals("")) {
                this.fechaDevolucionDado = tramitesAgarrados.get(0).getDemandado_fecha_devol();
            }
            if (tramitesAgarrados.get(0).getDemandado_cod_estado() != null && !tramitesAgarrados.get(0).getDemandado_cod_estado().equals("")) {
                if (tramitesAgarrados.get(0).getDemandado_cod_estado().equals("DEV")) {
                    activaFechasDado = true;
                } else {
                    activaFechasDado = false;
                }

                this.comboEstadoDado = tramitesAgarrados.get(0).getDemandado_cod_estado();

            }
            if (tramitesAgarrados.get(0).getDemandado_lugar_notificacion() != null && !tramitesAgarrados.get(0).getDemandado_lugar_notificacion().equals("")) {
                this.comboNotificadoEnDado = tramitesAgarrados.get(0).getDemandado_lugar_notificacion();
            }
            if (tramitesAgarrados.get(0).getDemandado_ciudad_notif() != null && !tramitesAgarrados.get(0).getDemandado_ciudad_notif().equals("")) {
                this.comboCiudadNotifiDado = tramitesAgarrados.get(0).getDemandado_ciudad_notif();
            }
            this.descripcionDado = tramitesAgarrados.get(0).getDemandado();
            this.solicitanteDado = tramitesAgarrados.get(0).getDemandado_solic();
            this.apoderadoDado = tramitesAgarrados.get(0).getDemandado_apod();
            this.fojasDado = tramitesAgarrados.get(0).getDemandado_fojas();
            this.notificaConDado = tramitesAgarrados.get(0).getDemandado_con();
            this.celularDado = tramitesAgarrados.get(0).getDemandado_cel();
            this.domicilioDado = tramitesAgarrados.get(0).getDemandado_direc();
            this.obsSolicitante = tramitesAgarrados.get(0).getObs();
            List<Usuario> usuario = usuarioService.listaUsuarioXidPagina(tramitesAgarrados.get(0).getId_usuario_solicitante());
            if (usuario.size() != 0) {
                this.operadorSolicitante = usuario.get(0).getNombre() + " " + usuario.get(0).getPrimer_apellido();

            }
            llenaDatosAdmni(tramitesAgarrados.get(0));
        } else {

            pintaOposicion = false;

            //     System.out.println("Entra a que no es oposicion");
            this.bloque = bloqueBuscar.toString();
            this.numCorrela = tramitesAgarrados.get(0).getNro_exped();
            this.tramite = tramitesAgarrados.get(0).getTipo_tramite_notificacion();
            this.numero = tramitesAgarrados.get(0).getExpediente();
            this.gestion = tramitesAgarrados.get(0).getGestion().toString();
            this.comboEstado = tramitesAgarrados.get(0).getDemandante_cod_estado();
            // System.out.println("Fecha de noti de la tabla::"+tramitesAgarrados.get(0).getDemandante_fecha_noti());
            if (tramitesAgarrados.get(0).getDemandante_fecha_noti() != null && !tramitesAgarrados.get(0).getDemandante_fecha_noti().equals("")) {
                this.fechaNotificacion = tramitesAgarrados.get(0).getDemandante_fecha_noti();
            }
            if (tramitesAgarrados.get(0).getDemandante_fecha_devol() != null && !tramitesAgarrados.get(0).getDemandante_fecha_devol().equals("")) {
                this.fechaDevolucion = tramitesAgarrados.get(0).getDemandante_fecha_devol();
            }
            if (tramitesAgarrados.get(0).getDemandante_cod_estado() != null && !tramitesAgarrados.get(0).getDemandante_cod_estado().equals("")) {
                if (tramitesAgarrados.get(0).getDemandante_cod_estado().equals("DEV")) {
                    this.activaFecha = true;
                } else {
                    this.activaFecha = false;
                }
                this.comboEstado = tramitesAgarrados.get(0).getDemandante_cod_estado();

            }
            if (tramitesAgarrados.get(0).getDemandante_lugar_notificacion() != null && !tramitesAgarrados.get(0).getDemandante_lugar_notificacion().equals("")) {
                this.comboNotificadoEn = tramitesAgarrados.get(0).getDemandante_lugar_notificacion();
            }
            if (tramitesAgarrados.get(0).getDemandante_ciudad_notif() != null && !tramitesAgarrados.get(0).getDemandante_ciudad_notif().equals("")) {
                this.comboCiudadNotifi = tramitesAgarrados.get(0).getDemandante_ciudad_notif();
            }

            this.fechaIngreso = tramitesAgarrados.get(0).getFecha_ingreso();
            this.fechaRecepcion = tramitesAgarrados.get(0).getFecha_recep();
            if (tramitesAgarrados.get(0).getObs_notifi() != null && !tramitesAgarrados.get(0).getObs_notifi().equals("")) {
                this.obsNotificador = tramitesAgarrados.get(0).getObs_notifi();
            }

            this.descripcion = tramitesAgarrados.get(0).getDemandante();
            this.solicitante = tramitesAgarrados.get(0).getDemandante_solic();
            this.apoderado = tramitesAgarrados.get(0).getDemandante_apod();
            this.fojas = tramitesAgarrados.get(0).getDemandante_fojas();
            this.notificaCon = tramitesAgarrados.get(0).getDemandante_con();
            this.celular = tramitesAgarrados.get(0).getDemandante_cel();
            this.domicilio = tramitesAgarrados.get(0).getDemandante_direc();
            //    System.out.println("LA observacion aqui:::" + tramitesAgarrados.get(0).getObs());
            this.obsSolicitante = tramitesAgarrados.get(0).getObs();
            //este opeoradorSolicitante es de la pantalla l aparte de derecha abajo

            List<Usuario> usuario = usuarioService.listaUsuarioXidPagina(tramitesAgarrados.get(0).getId_usuario_solicitante());
            if (usuario.size() != 0) {
                this.operadorSolicitante = usuario.get(0).getNombre() + " " + usuario.get(0).getPrimer_apellido();

            }
            llenaDatosAdmni(tramitesAgarrados.get(0));
        }

    }

    public void llenaDatosAdmni(Notificacion notiEscogido) throws ParseException {
        //   System.out.println("Entra aquisito..." + notiEscogido.getHistorial());
        //   System.out.println("ID" + notiEscogido.getIdnotificacion());
        listHistPo.clear();
        if (notiEscogido.getHistorial() != null && !notiEscogido.getHistorial().equals("")) {
            //   System.out.println("0");
            String[] listPadre = notiEscogido.getHistorial().split(";");
            //     System.out.println("0");
            for (int i = 0; i <= listPadre.length - 1; i++) {
                //     System.out.println("Entra al forsito");
                HistorialPojo historial = new HistorialPojo();
                String[] hijo = listPadre[i].split(",");
                historial.setNum(i + 1);
                historial.setHistorial(hijo[0]);
                //historial.setFecha(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(hijo[1]));//A revisar

                historial.setFecha(hijo[1]);//A revisar
                //   System.out.println("historial.getNum::" + historial.getNum());
                //   System.out.println("historial.getHsitorial::" + historial.getHistorial());
                //   System.out.println("historial.getfecha::" + historial.getFecha());
                listHistPo.add(historial);
            }
        }

    }

    public void modificar() {
      //  System.out.println("MODIFICA ESTADOS SOLO EN VISTA");
        Integer pos;
        for (int i = 0; i <= tramitesAgarrados.size() - 1; i++) {
            if (tramitesAgarrados.get(0).getTipo_tramite_notificacion().equals("OPOSICION SIG")
                    || tramitesAgarrados.get(0).getTipo_tramite_notificacion().equals("CANCELACION")
                    || tramitesAgarrados.get(0).getTipo_tramite_notificacion().equals("NULIDAD SIG")
                    || tramitesAgarrados.get(0).getTipo_tramite_notificacion().equals("IF")
                    || tramitesAgarrados.get(0).getTipo_tramite_notificacion().equals("MEF")) {
                //   if (tramitesAgarrados.get(0).getTipo_tramite_notificacion().indexOf("OP") != -1) {   //Es oposicion loq ue se modificara
                //PAra el demandante
                Notificacion notifica = tramitesAgarrados.get(i);
                //notifica.setDemandante_apod(this.apoderadoDante);
                //notifica.setDemandante_fojas(this.fojasDante);
                //notifica.setDemandante_con(this.notificaConDante);
                // notifica.setDemandante_cel(this.celularDante);
                notifica.setDemandante_direc(this.domicilioDante);

                notifica.setDemandante_fecha_noti(this.fechaNotificacionDante);
                notifica.setDemandante_fecha_devol(this.fechaDevolucionDante);
                notifica.setDemandante_cod_estado(this.comboEstadoDante);
                notifica.setDemandante_lugar_notificacion(this.comboNotificadoEnDante);
                notifica.setDemandante_ciudad_notif(this.comboCiudadNotifiDante);
                //Para el demandado

                // notifica.setDemandado_apod(this.apoderadoDado);
                // notifica.setDemandado_fojas(this.fojasDado);
                // notifica.setDemandado_con(this.notificaConDado);
                // notifica.setDemandado_cel(this.celularDado);
                notifica.setDemandado_direc(this.domicilioDado);

                notifica.setDemandado_fecha_noti(this.fechaNotificacionDado);
                notifica.setDemandado_fecha_devol(this.fechaDevolucionDado);
                notifica.setDemandado_cod_estado(this.comboEstadoDado);
                notifica.setDemandado_lugar_notificacion(this.comboNotificadoEnDado);
                notifica.setDemandado_ciudad_notif(this.comboCiudadNotifiDado);

                //pos = tramitesAgarrados.get(i).getNro_exped() - 1;
                pos = this.agarraPosicion(notifica, tramites);
                tramites.set(pos, notifica);
            } else {
                Notificacion notifica = tramitesAgarrados.get(i);
          //      notifica.setDemandante_apod(this.apoderado);
          //      notifica.setDemandante_fojas(this.fojas);
          //      notifica.setDemandante_con(this.notificaCon);
          //      notifica.setDemandante_cel(this.celular);
         //       notifica.setDemandante_direc(this.domicilio);

                notifica.setDemandante_fecha_noti(this.fechaNotificacion);
                notifica.setDemandante_fecha_devol(this.fechaDevolucion);
                notifica.setDemandante_cod_estado(this.comboEstado);
                notifica.setDemandante_lugar_notificacion(this.comboNotificadoEn);
                notifica.setDemandante_ciudad_notif(this.comboCiudadNotifi);
                notifica.setObs_notifi(this.obsNotificador);
                //pos = tramitesAgarrados.get(i).getNro_exped() - 1;

                pos = this.agarraPosicion(notifica, tramites);
                //pos=i;
                //    System.out.println("pos::" + pos);
                tramites.set(pos, notifica);
            }
        }
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "SE MODIFICO CORRECTAMENTE", "Debe  Apretar el botón Aceptar para que se guarde en Base de Datos");
        FacesContext.getCurrentInstance().addMessage(null, message);

    }

    public Integer agarraPosicion(Notificacion traAgarrado, List<Notificacion> tramites) {
        Integer posicion;
        posicion = 0;
        for (int i = 0; i <= tramites.size() - 1; i++) {
            if (tramites.get(i).getIdnotificacion().equals(traAgarrado.getIdnotificacion())) {
                posicion = i;

            }

        }
        return posicion;
    }

    //Modifica el estado_marca en la tabla sigsignomarca al estado anteror antes de notificacion
    public void modificaSignoEstadoAlUltimo(Notificacion noti) throws Exception {
        Long sm;
        // System.out.println("noti.getDemandado_cod_estado()"+noti.getDemandante_cod_estado());
        if (noti.getDemandante_cod_estado().equals("DEV") || noti.getDemandante_cod_estado().equals("NOT")) {
            if (noti.getTipo_tramite_notificacion().equals("SM")) {
                // System.out.println("El motiiii.." + noti.getIdnotificacion());
                if (noti.getExtension() == null) {
                    sm = comunService.codificarCodigoSM(noti.getExpediente(), noti.getGestion().toString(), "");
                } else {
                    sm = comunService.codificarCodigoSM(noti.getExpediente(), noti.getGestion().toString(), noti.getExtension());
                }

                SigSignoMarca signoMarca = sigSignoMarcaService.listaSigSignoMarcaXSM(sm);
                // System.out.println("Aqui el id:::::" + signoMarca.getIdSignoMarca());
                //Pregunta si su estado  en signos es notificacion , si es asi inserta su estado anterior que estaba.
                if (notificacionService.obtenerVerificaSiEstadoNotifica(signoMarca.getIdSignoMarca()) == 1) {
                    
                    signoMarca.setEstadoMarca(noti.getEstado_marca());
                    sigSignoMarcaService.crudSigSignoMarca(signoMarca, 2);
                }

            }
            if (noti.getTipo_tramite_notificacion().equals("N° REG")) {
                // System.out.println("ENtra..");
                SigSignoMarca signoMarca = sigSignoMarcaService.listaSigSignoMarcaXRegistro(Long.parseLong(noti.getExpediente()), noti.getExtension(), "");
                //Pregunta si su estado  en signos es notificacion , si es asi inserta su estado anterior que estaba.
                
                // System.out.println("esta en noti::"+notificacionService.obtenerVerificaSiEstadoNotifica(signoMarca.getIdSignoMarca()));
                if(signoMarca != null)
                {
                  if (notificacionService.obtenerVerificaSiEstadoNotifica(signoMarca.getIdSignoMarca()) == 1) {//  System.out.println("estado::marca::"+noti.getEstado_marca());
                    
                    signoMarca.setEstadoMarca(noti.getEstado_marca());
                    sigSignoMarcaService.crudSigSignoMarca(signoMarca, 2);
                   }
                }
            }
            if (noti.getTipo_tramite_notificacion().equals("N° PUB SIGNO")) {
                SigSignoMarca signoMarca = sigSignoMarcaService.listaSigSignoMarcaXPublicacion(Long.parseLong(noti.getExpediente()));
                if (notificacionService.obtenerVerificaSiEstadoNotifica(signoMarca.getIdSignoMarca()) == 1) {
                    
                    signoMarca.setEstadoMarca(noti.getEstado_marca());
                    sigSignoMarcaService.crudSigSignoMarca(signoMarca, 2);
                }

            }
        }

    }

    public void guardaHistorial(Notificacion noti) throws Exception {
        Long sm;
        String status = "";
        String descripcion = "[Status:";
        //POR LO PRONTO GUARDA HISTORIAL PARA SM, REGISTROS,PUBLICACIONES FALTA RENO Y MODISS
        if (noti.getTipo_tramite_notificacion().equals("SM")) {
            if (noti.getExtension() == null) {
                sm = comunService.codificarCodigoSM(noti.getExpediente(), noti.getGestion().toString(), "");
            } else {

                sm = comunService.codificarCodigoSM(noti.getExpediente(), noti.getGestion().toString(), noti.getExtension());
            }
            SigSignoMarca signoMarca = sigSignoMarcaService.listaSigSignoMarcaXSM(sm);
            
            //pone en historial cuando devuelve y notfica(NOT);;; y devuelve sin notificar(DEV)
            if (noti.getDemandante_cod_estado().equals("DEV")) {
                descripcion = descripcion + "DEVUELTO Y NOTIFICADO]";
              if (noti.getEstado_marca() != null) {
                status = dominioService.obtenerNombrePorCodigoDominio(noti.getEstado_marca(), "estado_marca");

                //pone en historial de signos lo de notificacion sies qeu no tiene uno antes igualito
                //if(notificacionService.obtenerUltimoRegistroNotificaSigno(signoMarca.getIdSignoMarca(), descripcion, status,"SIG") == 0){
                System.out.println("devuelve::" + notificacionService.obtenerEstadoTramiteNotificacion(noti.getBloque(), noti.getId_usuario_solicitante(), noti.getTipo_tramite_notificacion(), noti.getExpediente()));
                if (notificacionService.obtenerEstadoTramiteNotificacion(noti.getBloque(), noti.getId_usuario_solicitante(), noti.getTipo_tramite_notificacion(), noti.getExpediente()) == 0) {

                    sigHistorialService.generarHistorialNotificacion(signoMarca, super.getIdUsuarioSession(), status, descripcion);

                }
              }
            }

            if (noti.getDemandante_cod_estado().equals("NOT")) {

                descripcion = descripcion + "DEVUELTO SIN NOTIFICAR]";
              if (noti.getEstado_marca() != null) {
                status = dominioService.obtenerNombrePorCodigoDominio(noti.getEstado_marca(), "estado_marca");
                //System.out.println("esss1::"+notificacionService.obtenerUltimoRegistroNotificaSigno(signoMarca.getIdSignoMarca(), descripcion, status));
                //pone en historial de signos lo de notificacion sies qeu no tiene uno antes igualito
                //if(notificacionService.obtenerUltimoRegistroNotificaSigno(signoMarca.getIdSignoMarca(), descripcion, status, "SIG") == 0){
                if (notificacionService.obtenerEstadoTramiteNotificacion(noti.getBloque(), noti.getId_usuario_solicitante(), noti.getTipo_tramite_notificacion(), noti.getExpediente()) == 0) {

                    sigHistorialService.generarHistorialNotificacion(signoMarca, super.getIdUsuarioSession(), status, descripcion);

                }
              }
            }

        }
        if (noti.getTipo_tramite_notificacion().equals("N° REG")) {
            SigSignoMarca signoMarca = sigSignoMarcaService.listaSigSignoMarcaXRegistro(Long.parseLong(noti.getExpediente()), noti.getExtension(), "");
            //pone en historial cuando devuelve y notfica(NOT);;; y devuelve sin notificar(DEV)
           if(signoMarca != null)
           { if (noti.getDemandante_cod_estado().equals("DEV")) {
                descripcion = descripcion + "DEVUELTO Y NOTIFICADO]";
              if (noti.getEstado_marca() != null) {
                status = dominioService.obtenerNombrePorCodigoDominio(noti.getEstado_marca(), "estado_marca");
                // if(notificacionService.obtenerUltimoRegistroNotificaSigno(signoMarca.getIdSignoMarca(), descripcion, status, "SIG") == 0){
                if (notificacionService.obtenerEstadoTramiteNotificacion(noti.getBloque(), noti.getId_usuario_solicitante(), noti.getTipo_tramite_notificacion(), noti.getExpediente()) == 0) {
                    sigHistorialService.generarHistorialNotificacion(signoMarca, super.getIdUsuarioSession(), status, descripcion);
                }
              }
            }
            
            //Pone en historial cuando no notifica y solo lo devuelve
            if (noti.getDemandante_cod_estado().equals("NOT")) {

                descripcion = descripcion + "DEVUELTO SIN NOTIFICAR]";
               if (noti.getEstado_marca() != null) {
                status = dominioService.obtenerNombrePorCodigoDominio(noti.getEstado_marca(), "estado_marca");
                // if(notificacionService.obtenerUltimoRegistroNotificaSigno(signoMarca.getIdSignoMarca(), descripcion, status, "SIG") == 0){
                if (notificacionService.obtenerEstadoTramiteNotificacion(noti.getBloque(), noti.getId_usuario_solicitante(), noti.getTipo_tramite_notificacion(), noti.getExpediente()) == 0) {
                    sigHistorialService.generarHistorialNotificacion(signoMarca, super.getIdUsuarioSession(), status, descripcion);
                }
               }
             }
           }
        }
        //System.out.println("noti.getTipo_tramite_notificacion()::" + noti.getTipo_tramite_notificacion());
        //pone en historial cuando devuelve y notfica(NOT);;; y devuelve sin notificar(DEV)
        if (noti.getTipo_tramite_notificacion().equals("N° PUB SIGNO")) {
            SigSignoMarca signoMarca = sigSignoMarcaService.listaSigSignoMarcaXPublicacion(Long.parseLong(noti.getExpediente()));
            //System.out.println("noti.getDemandante_cod_estado()::"+noti.getDemandante_cod_estado());
            if (noti.getDemandante_cod_estado().equals("DEV")) {
                descripcion = descripcion + "DEVUELTO Y NOTIFICADO]";
                System.out.println("noti.getEstado_marca()::"+noti.getEstado_marca());         
               //Si no tiene estado anterior no guardara en historial de signos
               if (noti.getEstado_marca() != null) {
                
               
                  status = dominioService.obtenerNombrePorCodigoDominio(noti.getEstado_marca(), "estado_marca");
               
               //pone en historial de signos lo de notificacion sies qeu no tiene uno antes igualito, esto es porque cada rato apreta la vero click click y cada rato se guarda

                //  if(notificacionService.obtenerUltimoRegistroNotificaSigno(signoMarca.getIdSignoMarca(), descripcion, status, "SIG") == 0){
                  if (notificacionService.obtenerEstadoTramiteNotificacion(noti.getBloque(), noti.getId_usuario_solicitante(), noti.getTipo_tramite_notificacion(), noti.getExpediente()) == 0) {

                      sigHistorialService.generarHistorialNotificacion(signoMarca, super.getIdUsuarioSession(), status, descripcion);
                   }
               }
            }
            //Pone en historial cuando no notifica y solo lo devuelve
            if (noti.getDemandante_cod_estado().equals("NOT")) {

                descripcion = descripcion + "DEVUELTO SIN NOTIFICAR]";
                if (noti.getEstado_marca() != null) {
                   status = dominioService.obtenerNombrePorCodigoDominio(noti.getEstado_marca(), "estado_marca");
                //pone en historial de signos lo de notificacion sies qeu no tiene uno antes igualito
                //  if(notificacionService.obtenerUltimoRegistroNotificaSigno(signoMarca.getIdSignoMarca(), descripcion, status, "SIG") == 0){
                   if (notificacionService.obtenerEstadoTramiteNotificacion(noti.getBloque(), noti.getId_usuario_solicitante(), noti.getTipo_tramite_notificacion(), noti.getExpediente()) == 0) {
                       sigHistorialService.generarHistorialNotificacion(signoMarca, super.getIdUsuarioSession(), status, descripcion);
                   }
                }
            }
        }

        if ((!noti.getTipo_tramite_notificacion().equals("SM"))
                && (!noti.getTipo_tramite_notificacion().equals("SR"))
                && (!noti.getTipo_tramite_notificacion().equals("N° REG"))
                && (!noti.getTipo_tramite_notificacion().equals("N° PUB SIGNO"))) { //System.out.println("Entra a modificaciones....");
            ModModificacion modi = modModificacionService.buscarModModificacionXCodigo(noti.getTipo_tramite_notificacion(),
                    Long.parseLong(noti.getExpediente()),
                    noti.getGestion());
            // System.out.println("idmodificacion::"+modi.getIdmodificacion());
            if (noti.getDemandante_cod_estado().equals("DEV")) {
                descripcion = descripcion + "DEVUELTO Y NOTIFICADO]";
                System.out.println("estado::" + noti.getEstado_marca());
                if (noti.getEstado_marca() != null) {
                    status = dominioService.obtenerNombrePorCodigoDominio(noti.getEstado_marca(), "estado_modificacion");
                    //   System.out.println("status::"+status);
                    // if(notificacionService.obtenerUltimoRegistroNotificaSigno(modi.getIdmodificacion(), descripcion, status, "MOD") == 0){
                    if (notificacionService.obtenerEstadoTramiteNotificacion(noti.getBloque(), noti.getId_usuario_solicitante(), noti.getTipo_tramite_notificacion(), noti.getExpediente()) == 0) {
                        sigHistorialService.generarHistorialModifiNotificacion(modi, super.getIdUsuarioSession(), status, descripcion);
                    }
                }
            }
            if (noti.getDemandante_cod_estado().equals("NOT")) {

                descripcion = descripcion + "DEVUELTO SIN NOTIFICAR]";
                if (noti.getEstado_marca() != null) {
                    status = dominioService.obtenerNombrePorCodigoDominio(noti.getEstado_marca(), "estado_modificacion");
                    //pone en historial de signos lo de notificacion sies qeu no tiene uno antes igualito
                    // if(notificacionService.obtenerUltimoRegistroNotificaSigno(modi.getIdmodificacion(), descripcion, status, "MOD") == 0){
                    if (notificacionService.obtenerEstadoTramiteNotificacion(noti.getBloque(), noti.getId_usuario_solicitante(), noti.getTipo_tramite_notificacion(), noti.getExpediente()) == 0) {
                        sigHistorialService.generarHistorialModifiNotificacion(modi, super.getIdUsuarioSession(), status, descripcion);
                    }
                }
            }
        }
        if (noti.getTipo_tramite_notificacion().equals("SR")) {
            RenSolicitudRenovacion ren = renSolicitudRenovacionService.buscaSolicitudRenovacionPorNumeroSrYGestion(Long.parseLong(noti.getExpediente()),
                    noti.getGestion());

            if (noti.getDemandante_cod_estado().equals("DEV")) {
                descripcion = descripcion + "DEVUELTO Y NOTIFICADO]";
           //     System.out.println("noti.getEstado_marca():::"+noti.getEstado_marca());
              //  System.out.println("noti.getExpediente():::"+noti.getExpediente());
                
                
                
                if (noti.getEstado_marca() != null) {
                    String estado="";
                    //Este fi es por validacion por problemas de la base  madre con Chano que jala ACEP y este no esta en nuestra bse nueva
                    if(noti.getEstado_marca().equals("ACEP")|| noti.getEstado_marca().equals("SO"))
                    {
                      estado="SOLI";
                    }    
                    else{
                     estado=noti.getEstado_marca() ;
                    }
                    
                    status = dominioService.obtenerNombrePorCodigoDominio(estado, "estado_renovacion");
                    //   System.out.println("status::"+status);
                    //if(notificacionService.obtenerUltimoRegistroNotificaSigno(ren.getIdsolicitudrenovacion(), descripcion, status, "REN") == 0){
                    if (notificacionService.obtenerEstadoTramiteNotificacion(noti.getBloque(), noti.getId_usuario_solicitante(), noti.getTipo_tramite_notificacion(), noti.getExpediente()) == 0) {
                        sigHistorialService.generarHistorialRenoNotificacion(ren, super.getIdUsuarioSession(), status, descripcion);
                    }

                }
            }
            if (noti.getDemandante_cod_estado().equals("NOT")) {

                descripcion = descripcion + "DEVUELTO SIN NOTIFICAR]";
                if (noti.getEstado_marca() != null) {
                    status = dominioService.obtenerNombrePorCodigoDominio(noti.getEstado_marca(), "estado_renovacion");
                    //pone en historial de signos lo de notificacion sies qeu no tiene uno antes igualito
                    //if(notificacionService.obtenerUltimoRegistroNotificaSigno(ren.getIdsolicitudrenovacion(), descripcion, status, "REN") == 0){
                    if (notificacionService.obtenerEstadoTramiteNotificacion(noti.getBloque(), noti.getId_usuario_solicitante(), noti.getTipo_tramite_notificacion(), noti.getExpediente()) == 0) {
                        sigHistorialService.generarHistorialRenoNotificacion(ren, super.getIdUsuarioSession(), status, descripcion);
                    }
                }
            }
        }
    }
    public void modificaCambiosFinales1() throws Exception {
       for (int i = 0; i <= tramites.size() - 1; i++) {

            //Esta parte llena lo de historial
            if (tramites.get(i).getTipo_tramite_notificacion().equals("OPOSICION SIG")
                    || tramites.get(i).getTipo_tramite_notificacion().equals("CANCELACION")
                    || tramites.get(i).getTipo_tramite_notificacion().equals("NULIDAD SIG")
                    || tramites.get(i).getTipo_tramite_notificacion().equals("IF")
                    || tramites.get(i).getTipo_tramite_notificacion().equals("MEF")) {
                notificacionService.recibeListaHistorialNotificaciones(tramites.get(i), super.getIdUsuarioSession(), fechaNotificacionDante, fechaDevolucionDante);
            } else {
                //Modifica el estado_marca en la tabla sigsignomarca al estado anteror antes de notificacion
               // modificaSignoEstadoAlUltimo(tramites.get(i));
                //Guarda el historial  a signos
               // guardaHistorial(tramites.get(i));
                //Guarda historial en notificaciones
              //  notificacionService.recibeListaHistorialNotificaciones(tramites.get(i), super.getIdUsuarioSession(), fechaNotificacion, fechaDevolucion);

            }

            //Guarda las modificaciones hechas
            notificacionService.modificaNotificacion(tramites.get(i));

        }
    }
    public void modificaCambiosFinales() throws Exception {
        System.out.println("ACEPTA LOS CAMBIOS REALIZADOS EN NOTIFICACIONES....");
        for (int i = 0; i <= tramites.size() - 1; i++) {

            //Esta parte llena lo de historial
            if (tramites.get(i).getTipo_tramite_notificacion().equals("OPOSICION SIG")
                    || tramites.get(i).getTipo_tramite_notificacion().equals("CANCELACION")
                    || tramites.get(i).getTipo_tramite_notificacion().equals("NULIDAD SIG")
                    || tramites.get(i).getTipo_tramite_notificacion().equals("IF")
                    || tramites.get(i).getTipo_tramite_notificacion().equals("MEF")
                    ) {
                notificacionService.recibeListaHistorialNotificaciones(tramites.get(i), super.getIdUsuarioSession(), fechaNotificacionDante, fechaDevolucionDante);
            } else {
                //Modifica el estado_marca en la tabla sigsignomarca al estado anteror antes de notificacion
                modificaSignoEstadoAlUltimo(tramites.get(i));
                //Guarda el historial  a signos
                guardaHistorial(tramites.get(i));
                //Guarda historial en notificaciones
                notificacionService.recibeListaHistorialNotificaciones(tramites.get(i), super.getIdUsuarioSession(), fechaNotificacion, fechaDevolucion);

            }

            //Guarda las modificaciones hechas
            notificacionService.modificaNotificacion(tramites.get(i));

        }
        //Se ma adicionanado al seguimiento de signos

        for (int i = 0; i <= tramites.size() - 1; i++) {

            if (!tramites.get(i).getTipo_tramite_notificacion().equals("OPOSICION SIG")
                    && !tramites.get(i).getTipo_tramite_notificacion().equals("CANCELACION")
                    && !tramites.get(i).getTipo_tramite_notificacion().equals("NULIDAD SIG")
                    && !tramites.get(i).getTipo_tramite_notificacion().equals("IF")
                    && !tramites.get(i).getTipo_tramite_notificacion().equals("MEF")
                    //!tramites.get(i).getTipo_tramite_notificacion().equals("N° REG")&&
                    //!tramites.get(i).getTipo_tramite_notificacion().equals("N° PUB SIGNO")
                    ) {
                // System.out.println("tipoTRamiteNoti::"+tramites.get(i).getTipo_tramite_notificacion());

                if (tramites.get(i).getTipo_tramite_notificacion().equals("N° PUB SIGNO")) {
                    if (tramites.get(i).getDemandante_cod_estado().equals("DEV") || tramites.get(i).getDemandante_cod_estado().equals("NOT")) {
                        SigSignoMarca signoMarca = sigSignoMarcaService.listaSigSignoMarcaXPublicacion(Long.parseLong(tramites.get(i).getExpediente()));
                        Seguimiento seguimientoNuevo = new Seguimiento();
                        seguimientoNuevo.setId(signoMarca.getIdSignoMarca());
                        seguimientoNuevo.setIdUsuario(super.getIdUsuarioSession());

                        seguimientoNuevo.setIdLogTrans(logtrans);

                        seguimientoNuevo.setSm(signoMarca.getSm());
                        seguimientoNuevo.setNumeroPublicacion(signoMarca.getNumeroPublicacion());
                        seguimientoNuevo.setNumeroRegistro(signoMarca.getNumeroRegistro());
                        seguimientoNuevo.setSerieRegistro(signoMarca.getSerieRegistro());
                        seguimientoNuevo.setEtapa("NOT");

                        Seguimiento segui = seguimientoService.ultimo_Seguimiento_etapa("SIG", signoMarca.getIdSignoMarca(), "NOT");
                        //  System.out.println("segui" + segui.getId());
                        if (segui != null) {
                            //    System.out.println("segui fecha fin::" + segui.getFechaFin());
                            if (segui.getFechaFin() == null) {
                                //         System.out.println(" entra a guardar");
                                seguimientoService.guardar_modificar_Seguimiento_etapa(seguimientoNuevo, "SIG");

                            }
                        }

                    }
                }
                if (tramites.get(i).getTipo_tramite_notificacion().equals("N° REG")) {
                    if (tramites.get(i).getDemandante_cod_estado().equals("DEV") || tramites.get(i).getDemandante_cod_estado().equals("NOT")) {
                        SigSignoMarca signoMarca = sigSignoMarcaService.listaSigSignoMarcaXRegistro(Long.parseLong(tramites.get(i).getExpediente()), tramites.get(i).getExtension(), "");

                        Seguimiento seguimientoNuevo = new Seguimiento();
                        seguimientoNuevo.setId(signoMarca.getIdSignoMarca());
                        seguimientoNuevo.setIdUsuario(super.getIdUsuarioSession());

                        seguimientoNuevo.setIdLogTrans(logtrans);

                        seguimientoNuevo.setSm(signoMarca.getSm());
                        seguimientoNuevo.setNumeroPublicacion(signoMarca.getNumeroPublicacion());
                        seguimientoNuevo.setNumeroRegistro(signoMarca.getNumeroRegistro());
                        seguimientoNuevo.setSerieRegistro(signoMarca.getSerieRegistro());
                        seguimientoNuevo.setEtapa("NOT");

                        Seguimiento segui = seguimientoService.ultimo_Seguimiento_etapa("SIG", signoMarca.getIdSignoMarca(), "NOT");
                    //    System.out.println("segui" + segui.getId());
                        if (segui != null) {
                            System.out.println("segui fecha fin::" + segui.getFechaFin());
                            if (segui.getFechaFin() == null) {
                                System.out.println(" entra a guardar");
                                seguimientoService.guardar_modificar_Seguimiento_etapa(seguimientoNuevo, "SIG");

                            }
                        }
                    }
                }

                if (tramites.get(i).getTipo_tramite_notificacion().equals("SM")) {
                    if (tramites.get(i).getDemandante_cod_estado().equals("DEV") || tramites.get(i).getDemandante_cod_estado().equals("NOT")) {

                        Seguimiento seguimientoNuevo = new Seguimiento();
                        Long sm;
                        if (tramites.get(i).getExtension() == null) {
                            sm = comunService.codificarCodigoSM(tramites.get(i).getExpediente(), tramites.get(i).getGestion().toString(), "");
                        } else {
                            sm = comunService.codificarCodigoSM(tramites.get(i).getExpediente(), tramites.get(i).getGestion().toString(), tramites.get(i).getExtension());
                        }
                        System.out.println("sm" + sm);
                        SigSignoMarca signoMarca = sigSignoMarcaService.listaSigSignoMarcaXSM(sm);
                        // System.out.println("idSignoMarca:"+signoMarca.getIdSignoMarca());
                        seguimientoNuevo.setId(signoMarca.getIdSignoMarca());
                        seguimientoNuevo.setIdUsuario(super.getIdUsuarioSession());

                        seguimientoNuevo.setIdLogTrans(logtrans);

                        seguimientoNuevo.setSm(sm);
                        seguimientoNuevo.setNumeroPublicacion(signoMarca.getNumeroPublicacion());
                        seguimientoNuevo.setNumeroRegistro(signoMarca.getNumeroRegistro());
                        seguimientoNuevo.setSerieRegistro(signoMarca.getSerieRegistro());
                        seguimientoNuevo.setEtapa("NOT");
                        Date fechaSistema = comunService.obtenerFechaHoraServidor(1L);
                        seguimientoNuevo.setFechaFin(fechaSistema);

                        //  List<Etapa>  listEtapaUs=etapaService.listadoPorIdUsuario(super.getIdUsuarioSession());
                        //Se busca el ultimo registrado coin ese numero de idsignomarca en la tabla seguimiento, y que tenga fecha fin nulo pa que
                        //registre su fecha fin, ya uqe
                        //de no ser asi  puede insertar un nuevo registro si es que ya tiene fecha fin colocado.
                        //Todo esto puede pasar cuando una primera vez ya cambie estados de "devuelto" por ejemplo, peor le falte  a otros poner  el estado, entonces
                        //los primeros  ya estaran en seguimiento con fecha fin y eso vera y creara otro registro.
                        //
                        System.out.println("signoMarca.getIdSignoMarca()::" + signoMarca.getIdSignoMarca());
                        Seguimiento segui = seguimientoService.ultimo_Seguimiento_etapa("SIG", signoMarca.getIdSignoMarca(), "NOT");
//                        System.out.println("segui" + segui.getId());
                        if (segui != null) {
                            // System.out.println("segui fecha fin::" + segui.getFechaFin());
                            if (segui.getFechaFin() == null) {
                                //   System.out.println(" entra a guardar");
                                seguimientoService.guardar_modificar_Seguimiento_etapa(seguimientoNuevo, "SIG");

                            }
                        }
                        
                    }
                }
                
                
                
                
                
                if ((!tramites.get(i).getTipo_tramite_notificacion().equals("SM"))
                        && (!tramites.get(i).getTipo_tramite_notificacion().equals("SR"))
                        && (!tramites.get(i).getTipo_tramite_notificacion().equals("N° REG"))
                        && (!tramites.get(i).getTipo_tramite_notificacion().equals("N° PUB SIGNO"))
                        && (!tramites.get(i).getTipo_tramite_notificacion().equals("BQ"))
                        && (!tramites.get(i).getTipo_tramite_notificacion().equals("CE"))) {  // System.out.println("codEstado::"+tramites.get(i).getDemandante_cod_estado());
                    if (tramites.get(i).getDemandante_cod_estado().equals("DEV") || tramites.get(i).getDemandante_cod_estado().equals("NOT")) {
                        
                       // System.out.println("tramites.get(i).getTipo_tramite_notificacion()::"+tramites.get(i).getTipo_tramite_notificacion());
                       // System.out.println("tramites.get(i).getExpediente()::"+tramites.get(i).getExpediente());
                       // System.out.println("tramites.get(i).getGestion()::"+tramites.get(i).getGestion());
                        ModModificacion modi = modModificacionService.buscarModModificacionXCodigo(tramites.get(i).getTipo_tramite_notificacion(),
                                Long.parseLong(tramites.get(i).getExpediente()),
                                tramites.get(i).getGestion());
                        if(modi != null)
                        { Seguimiento seguimientoNuevo = new Seguimiento();
                          seguimientoNuevo.setId(modi.getIdmodificacion());
                        seguimientoNuevo.setIdUsuario(super.getIdUsuarioSession());

                        seguimientoNuevo.setIdLogTrans(logtrans);

                        seguimientoNuevo.setSm(modi.getSm());
                        seguimientoNuevo.setNumeroPublicacion(modi.getNumero_publicacion());
                        seguimientoNuevo.setNumeroRegistro(modi.getNumero_registro());
                        seguimientoNuevo.setSerieRegistro(modi.getSerie_registro());

                        seguimientoNuevo.setEtapa("NOT");
                        seguimientoNuevo.setObservacion(null);
                        seguimientoNuevo.setEditable(false);
                        seguimientoNuevo.setEstado("AC");
                        Date fechaSistema = comunService.obtenerFechaHoraServidor(1L);
                        seguimientoNuevo.setFechaFin(fechaSistema);

                        Seguimiento segui = seguimientoService.ultimo_Seguimiento_etapa("MOD", modi.getIdmodificacion(), "NOT");
//                        System.out.println("segui" + segui.getId());
                        
                        if (segui != null) {
                            //   System.out.println("segui fecha fin::" + segui.getFechaFin());
                            if (segui.getFechaFin() == null) {
                                //     System.out.println(" entra a guardar");
                                seguimientoService.guardar_modificar_Seguimiento_etapa(seguimientoNuevo, "MOD");

                            }
                        }
                        }
                       
                    }
                }
                if (tramites.get(i).getTipo_tramite_notificacion().equals("SR")) {
                    if (tramites.get(i).getDemandante_cod_estado().equals("DEV") || tramites.get(i).getDemandante_cod_estado().equals("NOT")) {
                        RenSolicitudRenovacion ren = renSolicitudRenovacionService.buscaSolicitudRenovacionPorNumeroSrYGestion(Long.parseLong(tramites.get(i).getExpediente()),
                                tramites.get(i).getGestion());
                        if(ren!=null)
                        {Seguimiento seguimientoNuevo = new Seguimiento();
                        seguimientoNuevo.setId(ren.getIdsolicitudrenovacion());
                        seguimientoNuevo.setIdUsuario(super.getIdUsuarioSession());

                        seguimientoNuevo.setIdLogTrans(logtrans);

                        seguimientoNuevo.setSm(ren.getSm());
                        //seguimientoNuevo.setNumeroPublicacion(ren.get);
                        seguimientoNuevo.setNumeroRegistro(ren.getNumero_registro_registrado());
                        seguimientoNuevo.setSerieRegistro(ren.getSerie_registro_registrado());

                        seguimientoNuevo.setEtapa("NOT");
                        seguimientoNuevo.setObservacion(null);
                        seguimientoNuevo.setEditable(false);
                        seguimientoNuevo.setEstado("AC");
                        Date fechaSistema = comunService.obtenerFechaHoraServidor(1L);
                        seguimientoNuevo.setFechaFin(fechaSistema);

                        Seguimiento segui = seguimientoService.ultimo_Seguimiento_etapa("REN", ren.getIdsolicitudrenovacion(), "NOT");
                        //      System.out.println("segui" + segui.getId());
                        if (segui != null) {
                            //     System.out.println("segui fecha fin::" + segui.getFechaFin());
                            if (segui.getFechaFin() == null) {
                                //     System.out.println(" entra a guardar");
                                seguimientoService.guardar_modificar_Seguimiento_etapa(seguimientoNuevo, "REN");

                            }
                        }
                        }
                    }

                    

                }
            }
        }

        //Las dos lineas de abajo se sacaron de  el buscar , pero en el buscar tambien está despintaColumna(); que pone en nulo  un campo de oposiciones del demandado,y no estoy seguro de porque lo hize,
        //entonces no si poner o no, entonces prefiero no ponerlo  porlopronto ni las dos lineas de abajo, hasta que no me lo exigan
        //  tramites.clear();
        //  tramites = notificacionService.listaNotificacionParaNotificar(idoperador, bloqueBuscar);
// limpiaDatosTablaTramitesHistorial();
        //limpiaDatosPeticionSingular();
        //limpiaDatosPeticionOposicion();
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
        return cadena.matches("[A-Z].*");
    }

    /**
     * metodo para el paso de parametros al reporte formulario de notificacion
     * del tramite seleccionado.
     *
     * Creado: Ruben Ramirez Fecha: 25/11/2016
     *
     * @throws net.sf.jasperreports.engine.JRException
     * @throws java.io.IOException
     */
    public void imprime() throws JRException, IOException, Exception {
        if (tramitesAgarrados.size() > 0) {

            imgSenapi = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/images/logoNuevo.jpg");
            imgMinisterio = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/images/min.jpg");

            Notificacion item = tramitesAgarrados.get(0);
            NotiFormularioPojo noti = new NotiFormularioPojo();

            Boolean demandado = true;
            if (item.getDemandado() == null) {
                demandado = false;
            } else {
                if (item.getDemandado().trim().equals("")) {
                    demandado = false;
                }
            }

            noti.setDemandado(demandado);
            noti.setBloque("" + item.getBloque());

            String smTramite = "";
            if (item.getTipo_tramite_notificacion() != null) {
                if (item.getGestion() != null) {
                    if (item.getGestion() != 0) {
                        smTramite = item.getExpediente() + "-" + item.getGestion();
                    } else {
                        smTramite = item.getExpediente();
                    }
                } else {
                    smTramite = item.getExpediente();
                }
                if (item.getExtension() != null) {
                    if (!item.getExtension().trim().equals("")) {
                        if (this.validar(item.getExtension())) {
                            smTramite += "-" + item.getExtension();
                        }
                    }
                }
                noti.setProcesoAdministrativo(item.getTipo_tramite_notificacion());
            }
            noti.setExpedienteNro(smTramite);

            // fecha actual de al base de datos
            SimpleDateFormat formateador = new SimpleDateFormat("yyyy");
            Date fechaPresente = comunService.obtenerFechaHoraServidor(1L);
            String fecha = "";
            if (fechaPresente != null) {
                fecha = formateador.format(fechaPresente);
            }

            String titulo = "";

            if (demandado) {
                titulo = item.getDemandante() + "   c/   " + item.getDemandado();
                String ciudadDemandado = "...................................................,";

                if (item.getDemandado_ciudad_notif() != null) {
                    if (!item.getDemandado_ciudad_notif().isEmpty()) {
                        for (Dominio item2 : lstCiudadNotificacion) {
                            if (item2.getCodigo().equals(item.getDemandado_ciudad_notif())) {
                                ciudadDemandado = item2.getNombre();
                                break;
                            }
                        }
                    }
                }
                String direccionDemandado = "Dirección: ";
                if (item.getDemandado_direc() != null) {
                    if (!item.getDemandado_direc().isEmpty()) {
                        direccionDemandado += item.getDemandado_direc();
                    }
                }
                
                if (item.getDemandado_cel() != null) {
                    if (!item.getDemandado_cel().isEmpty()) {
                        direccionDemandado += " Teléfono/Celular: " + item.getDemandado_cel();
                    }
                }
                

                String demandadoApod = "";
                if (item.getDemandado_apod() != null) {
                    demandadoApod = item.getDemandado_apod();
                }
                String demandadoSolic = "";
                if (item.getDemandado_solic() != null) {
                    demandadoSolic = item.getDemandado_solic();
                }
                String demandadoCon = "";
                if (item.getDemandado_con() != null) {
                    demandadoCon = item.getDemandado_con();
                }
                String demandadoFojas = "";
                if (item.getDemandado_fojas() != null) {
                    demandadoFojas = item.getDemandado_fojas();
                }

                String contenido2 = "En la ciudad de " + ciudadDemandado + " a horas....................................................\n"
                        + "día .................................................. del mes de .............................................. de " + fecha + " años,\n"
                        + "notifique a:   <style isBold='true'>" + demandadoApod + "</style>\n"
                        + "Representante legal de   <style isBold='true'>" + demandadoSolic.replaceAll("&", "&amp;") + "</style>\n"
                        + "Con   " + demandadoCon + ".\n"
                        + "Cursante a fs   " + demandadoFojas + "   , en su domicilio señalado, de lo que certifico.";

                noti.setContenido2(contenido2);
                noti.setDireccion2(direccionDemandado);

            } else {
                titulo = item.getDemandante();
            }

            noti.setTitulo(titulo);
            String ciudadDemandante = "...................................................,";

            if (item.getDemandante_ciudad_notif() != null) {
                if (!item.getDemandante_ciudad_notif().isEmpty()) {
                    for (Dominio item2 : lstCiudadNotificacion) {
                        if (item2.getCodigo().equals(item.getDemandante_ciudad_notif())) {
                            ciudadDemandante = item2.getNombre();
                            break;
                        }
                    }
                }
            }
            
            String direccionDemandante = "Dirección: ";
            if (item.getDemandante_direc() != null) {
                if (!item.getDemandante_direc().isEmpty()) {
                    direccionDemandante += item.getDemandante_direc();
                }
            }

            if (item.getDemandante_cel() != null) {
                if (!item.getDemandante_cel().isEmpty()) {
                    direccionDemandante += " Teléfono/Celular: " + item.getDemandante_cel();
                }
            }

            String demandanteApod = "";
            if (item.getDemandante_apod() != null) {
                demandanteApod = item.getDemandante_apod();
            }
            String demandanteSolic = "";
            if (item.getDemandante_solic() != null) {
                demandanteSolic = item.getDemandante_solic();
            }
            String demandanteCon = "";
            if (item.getDemandante_con() != null) {
                demandanteCon = item.getDemandante_con();
            }
            String demandanteFojas = "";
            if (item.getDemandante_fojas() != null) {
                demandanteFojas = item.getDemandante_fojas();
            }

            String contenido1 = "En la ciudad de " + ciudadDemandante + " a horas....................................................\n"
                    + "día .................................................. del mes de .............................................. de " + fecha + " años,\n"
                    + "notifique a:   <style isBold='true'>" + demandanteApod + "</style>\n"
                    + "Representante legal de   <style isBold='true'>" + demandanteSolic.replaceAll("&", "&amp;") + "</style>\n"
                    + "Con   " + demandanteCon + ".\n"
                    + "Cursante a fs   " + demandanteFojas + "   , en su domicilio señalado, de lo que certifico.";

            noti.setContenido1(contenido1);
            noti.setDireccion1(direccionDemandante);
            noti.setNum(item.getNro_exped());

            listNotiFormularioPojo.add(noti);

            parametros.put("imgSenapi", imgSenapi);
            parametros.put("imgMinisterio", imgMinisterio);

            String filename = "FormularioNotificacion.pdf";
            String jasperPath = "/template/notificacion/FormularioNotificacion.jasper";
            this.PDF(parametros, jasperPath, listNotiFormularioPojo, filename);

            listNotiFormularioPojo.clear();

        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "TRAMITE NO SELECCIONADO", "Seleccione un tramite.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }

    }

    /**
     * metodo para el paso de parametros al reporte de formulario de
     * notificacion por bloque.
     *
     * Creado: Ruben Ramirez Fecha: 25/11/2016
     *
     * @throws net.sf.jasperreports.engine.JRException
     * @throws java.io.IOException
     */
    public void imprimeBloque() throws JRException, IOException, Exception {
        if (tramites.size() > 0) {
            imgSenapi = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/images/logoNuevo.jpg");
            imgMinisterio = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/images/min.jpg");
            int i = 0;
            for (Notificacion item : tramites) {
                i++;
                NotiFormularioPojo noti = new NotiFormularioPojo();
                Boolean demandado = true;
                if (item.getDemandado() == null) {
                    demandado = false;
                } else {
                    if (item.getDemandado().trim().equals("")) {
                        demandado = false;
                    }
                }
                noti.setDemandado(demandado);
                noti.setBloque("" + item.getBloque());
                String smTramite = "";
                if (item.getTipo_tramite_notificacion() != null) {
                    if (item.getGestion() != null && item.getGestion() > 0) {
                        if (item.getGestion() != 0) {
                            smTramite = item.getExpediente() + "-" + item.getGestion();
                        } else {
                            smTramite = item.getExpediente();
                        }
                    } else {
                        smTramite = item.getExpediente();
                    }
                    if (item.getExtension() != null) {
                        if (!item.getExtension().trim().equals("")) {
                            if (this.validar(item.getExtension())) {
                                smTramite += "-" + item.getExtension();
                            }
                        }
                    }
                    noti.setProcesoAdministrativo(item.getTipo_tramite_notificacion());
                }
                noti.setExpedienteNro(smTramite);
                SimpleDateFormat formateador = new SimpleDateFormat("yyyy");
                Date fechaPresente = comunService.obtenerFechaHoraServidor(1L);
                String fecha = "";
                if (fechaPresente != null) {
                    fecha = formateador.format(fechaPresente);
                }
                String titulo = "";
                if (demandado) {
                    titulo = item.getDemandante() + "   c/   " + item.getDemandado();
                    String ciudadDemandado = "...................................................,";

                    if (item.getDemandado_ciudad_notif() != null) {
                        if (!item.getDemandado_ciudad_notif().isEmpty()) {
                            for (Dominio item2 : lstCiudadNotificacion) {
                                if (item2.getCodigo().equals(item.getDemandado_ciudad_notif())) {
                                    ciudadDemandado = item2.getNombre();
                                    break;
                                }
                            }
                        }
                    }
                   
                    String direccionDemandado = "Dirección: ";
                    if (item.getDemandado_direc() != null) {
                        if (!item.getDemandado_direc().isEmpty()) {
                            direccionDemandado += item.getDemandado_direc();
                        }
                    }

                    if (item.getDemandado_cel() != null) {
                        if (!item.getDemandado_cel().isEmpty()) {
                            direccionDemandado += " Teléfono/Celular: " + item.getDemandado_cel();
                        }
                    }

                    String demandadoApod = "";
                    if (item.getDemandado_apod() != null) {
                        demandadoApod = item.getDemandado_apod();
                    }
                    String demandadoSolic = "";
                    if (item.getDemandado_solic() != null) {
                        demandadoSolic = item.getDemandado_solic();
                    }
                    String demandadoCon = "";
                    if (item.getDemandado_con() != null) {
                        demandadoCon = item.getDemandado_con();
                    }
                    String demandadoFojas = "";
                    if (item.getDemandado_fojas() != null) {
                        demandadoFojas = item.getDemandado_fojas();
                    }

                    String contenido2 = "En la ciudad de " + ciudadDemandado + " a horas....................................................\n"
                            + "día .................................................. del mes de .............................................. de " + fecha + " años,\n"
                            + "notifique a:   <style isBold='true'>" + demandadoApod + "</style>\n"
                            + "Representante legal de   <style isBold='true'>" + demandadoSolic.replaceAll("&", "&amp;") + "</style>\n"
                            + "Con   " + demandadoCon + ".\n"
                            + "Cursante a fs   " + demandadoFojas + "   , en su domicilio señalado, de lo que certifico.";

                    noti.setContenido2(contenido2);
                    noti.setDireccion2(direccionDemandado);

                } else {
                    titulo = item.getDemandante();
                }

                noti.setTitulo(titulo);

                String ciudadDemandante = "...................................................,";

                if (item.getDemandante_ciudad_notif() != null) {
                    if (!item.getDemandante_ciudad_notif().isEmpty()) {
                        for (Dominio item2 : lstCiudadNotificacion) {
                            if (item2.getCodigo().equals(item.getDemandante_ciudad_notif())) {
                                ciudadDemandante = item2.getNombre();
                                break;
                            }
                        }
                    }
                }

                String direccionDemandante = "Dirección: ";
                if (item.getDemandante_direc() != null) {
                    if (!item.getDemandante_direc().isEmpty()) {
                        direccionDemandante += item.getDemandante_direc();
                    }
                }

                if (item.getDemandante_cel() != null) {
                    if (!item.getDemandante_cel().isEmpty()) {
                        direccionDemandante += " Teléfono/Celular: " + item.getDemandante_cel();
                    }
                }

                String demandanteApod = "";
                if (item.getDemandante_apod() != null) {
                    demandanteApod = item.getDemandante_apod();
                }
                String demandanteSolic = "";
                if (item.getDemandante_solic() != null) {
                    demandanteSolic = item.getDemandante_solic();
                }
                String demandanteCon = "";
                if (item.getDemandante_con() != null) {
                    demandanteCon = item.getDemandante_con();
                }
                String demandanteFojas = "";
                if (item.getDemandante_fojas() != null) {
                    demandanteFojas = item.getDemandante_fojas();
                }

                String contenido1 = "En la ciudad de " + ciudadDemandante + " a horas....................................................\n"
                        + "día .................................................. del mes de .............................................. de " + fecha + " años,\n"
                        + "notifique a:   <style isBold='true'>" + demandanteApod + "</style>\n"
                        + "Representante legal de   <style isBold='true'>" + demandanteSolic.replaceAll("&", "&amp;") + "</style>\n"
                        + "Con   " + demandanteCon + ".\n"
                        + "Cursante a fs   " + demandanteFojas + "   , en su domicilio señalado, de lo que certifico.";

                noti.setContenido1(contenido1);
                noti.setDireccion1(direccionDemandante);
                noti.setNum(i);

                listNotiFormularioPojo.add(noti);
            }
            parametros.put("imgSenapi", imgSenapi);
            parametros.put("imgMinisterio", imgMinisterio);
            String filename = "FormularioNotificacion.pdf";
            String jasperPath = "/template/notificacion/FormularioNotificacion.jasper";
            this.PDF(parametros, jasperPath, listNotiFormularioPojo, filename);
            listNotiFormularioPojo.clear();
        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "BLOQUE NO SELECCIONADO", "Seleccione un bloque.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }

    }

    /**
     * Metodo para generar el reporte en formato pdf
     *
     * Creado: Ruben Ramirez Fecha: 25/11/2016
     *
     * @param params
     * @param jasperPath
     * @param fileName
     *
     * @throws net.sf.jasperreports.engine.JRException
     * @throws java.io.IOException
     */
    public void PDF(Map<String, Object> params, String jasperPath, List<?> dataSource, String fileName) throws JRException, IOException {
        String relativeWebPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath(jasperPath);
        File file = new File(relativeWebPath);
        JasperPrint print = new JasperPrint();
        JRBeanCollectionDataSource source = new JRBeanCollectionDataSource(dataSource, false);
        print = JasperFillManager.fillReport(file.getPath(), params, source);
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-Disposition", "attachment; filename=" + fileName);
        ServletOutputStream stream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(print, stream);
        FacesContext.getCurrentInstance().responseComplete();
    }

    public Boolean BuscarCiudad(String texto) {
        Boolean respuesta = false;
        for (Dominio item : lstCiudadNotificacion) {
            Pattern regex = Pattern.compile("\\b" + Pattern.quote(item.getNombre()) + "\\b", Pattern.CASE_INSENSITIVE);
            Matcher match = regex.matcher(texto);
            if (match.find()) {
                respuesta = true;
                break;
            }
        }
        return respuesta;
    }

    public JRDataSource getDatos(List<?> listaObjeto) {
        return new JRBeanCollectionDataSource(listaObjeto);
    }

    public List<TramiteTransaccion> getListTramite() {
        return listTramite;
    }

    public void setListTramite(List<TramiteTransaccion> listTramite) {
        this.listTramite = listTramite;
    }

    public boolean getHabCampos() {
        return habCampos;
    }

    public void setHabCampos(boolean habCampos) {
        this.habCampos = habCampos;
    }

    public List<Notificacion> getTramites() {
        return tramites;
    }

    public void setTramites(List<Notificacion> tramites) {
        this.tramites = tramites;
    }

    public List<Notificacion> getTramitesAgarrados() {
        return tramitesAgarrados;
    }

    public void setTramitesAgarrados(List<Notificacion> tramitesAgarrados) {
        this.tramitesAgarrados = tramitesAgarrados;
    }

    public NotificacionService getNotificacionService() {
        return notificacionService;
    }

    public void setNotificacionService(NotificacionService notificacionService) {
        this.notificacionService = notificacionService;
    }

    public boolean getPintaOposicion() {
        return pintaOposicion;
    }

    public void setPintaOposicion(boolean pintaOposicion) {
        this.pintaOposicion = pintaOposicion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(String solicitante) {
        this.solicitante = solicitante;
    }

    public String getApoderado() {
        return apoderado;
    }

    public void setApoderado(String apoderado) {
        this.apoderado = apoderado;
    }

    public String getFojas() {
        return fojas;
    }

    public void setFojas(String fojas) {
        this.fojas = fojas;
    }

    public String getNotificaCon() {
        return notificaCon;
    }

    public void setNotificaCon(String notificaCon) {
        this.notificaCon = notificaCon;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getComboEstado() {
        return comboEstado;
    }

    public void setComboEstado(String comboEstado) {
        this.comboEstado = comboEstado;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Date getFechaRecepcion() {
        return fechaRecepcion;
    }

    public void setFechaRecepcion(Date fechaRecepcion) {
        this.fechaRecepcion = fechaRecepcion;
    }

    public String getBloque() {
        return bloque;
    }

    public void setBloque(String bloque) {
        this.bloque = bloque;
    }

    public Integer getNumCorrela() {
        return numCorrela;
    }

    public void setNumCorrela(Integer numCorrela) {
        this.numCorrela = numCorrela;
    }

    public String getTramite() {
        return tramite;
    }

    public void setTramite(String tramite) {
        this.tramite = tramite;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getGestion() {
        return gestion;
    }

    public void setGestion(String gestion) {
        this.gestion = gestion;
    }

    public List<Usuario> getListUsuarioOpera() {
        return listUsuarioOpera;
    }

    public void setListUsuarioOpera(List<Usuario> listUsuarioOpera) {
        this.listUsuarioOpera = listUsuarioOpera;
    }

    public UsuarioService getUsuarioService() {
        return usuarioService;
    }

    public void setUsuarioService(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    public Long getIdoperador() {
        return idoperador;
    }

    public void setIdoperador(Long idoperador) {
        this.idoperador = idoperador;
    }

    public Integer getBloqueBuscar() {
        return bloqueBuscar;
    }

    public void setBloqueBuscar(Integer bloqueBuscar) {
        this.bloqueBuscar = bloqueBuscar;
    }

    public Date getFechaNotificacion() {
        return fechaNotificacion;
    }

    public void setFechaNotificacion(Date fechaNotificacion) {
        this.fechaNotificacion = fechaNotificacion;
    }

    public Date getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(Date fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public String getComboNotificadoEn() {
        return comboNotificadoEn;
    }

    public void setComboNotificadoEn(String comboNotificadoEn) {
        this.comboNotificadoEn = comboNotificadoEn;
    }

    public String getObsSolicitante() {
        return obsSolicitante;
    }

    public void setObsSolicitante(String obsSolicitante) {
        this.obsSolicitante = obsSolicitante;
    }

    public String getOperadorSolicitante() {
        return operadorSolicitante;
    }

    public void setOperadorSolicitante(String operadorSolicitante) {
        this.operadorSolicitante = operadorSolicitante;
    }

    public List<HistorialPojo> getListHistPo() {
        return listHistPo;
    }

    public void setListHistPo(List<HistorialPojo> listHistPo) {
        this.listHistPo = listHistPo;
    }

    public String getComboCiudadNotifi() {
        return comboCiudadNotifi;
    }

    public void setComboCiudadNotifi(String comboCiudadNotifi) {
        this.comboCiudadNotifi = comboCiudadNotifi;
    }

    public String getObsNotificador() {
        return obsNotificador;
    }

    public void setObsNotificador(String obsNotificador) {
        this.obsNotificador = obsNotificador;
    }

    public UsuarioPaginaService getUsuarioPaginaService() {
        return usuarioPaginaService;
    }

    public void setUsuarioPaginaService(UsuarioPaginaService usuarioPaginaService) {
        this.usuarioPaginaService = usuarioPaginaService;
    }

    public boolean getBotonActiva() {
        return botonActiva;
    }

    public void setBotonActiva(boolean botonActiva) {
        this.botonActiva = botonActiva;
    }

    public Date getFechaNotificacionDante() {
        return fechaNotificacionDante;
    }

    public void setFechaNotificacionDante(Date fechaNotificacionDante) {
        this.fechaNotificacionDante = fechaNotificacionDante;
    }

    public Date getFechaDevolucionDante() {
        return fechaDevolucionDante;
    }

    public void setFechaDevolucionDante(Date fechaDevolucionDante) {
        this.fechaDevolucionDante = fechaDevolucionDante;
    }

    public String getComboEstadoDante() {
        return comboEstadoDante;
    }

    public void setComboEstadoDante(String comboEstadoDante) {
        this.comboEstadoDante = comboEstadoDante;
    }

    public String getComboNotificadoEnDante() {
        return comboNotificadoEnDante;
    }

    public void setComboNotificadoEnDante(String comboNotificadoEnDante) {
        this.comboNotificadoEnDante = comboNotificadoEnDante;
    }

    public String getComboCiudadNotifiDante() {
        return comboCiudadNotifiDante;
    }

    public void setComboCiudadNotifiDante(String comboCiudadNotifiDante) {
        this.comboCiudadNotifiDante = comboCiudadNotifiDante;
    }

    public String getObsNotificadorDante() {
        return obsNotificadorDante;
    }

    public void setObsNotificadorDante(String obsNotificadorDante) {
        this.obsNotificadorDante = obsNotificadorDante;
    }

    public String getDescripcionDante() {
        return descripcionDante;
    }

    public void setDescripcionDante(String descripcionDante) {
        this.descripcionDante = descripcionDante;
    }

    public String getSolicitanteDante() {
        return solicitanteDante;
    }

    public void setSolicitanteDante(String solicitanteDante) {
        this.solicitanteDante = solicitanteDante;
    }

    public String getApoderadoDante() {
        return apoderadoDante;
    }

    public void setApoderadoDante(String apoderadoDante) {
        this.apoderadoDante = apoderadoDante;
    }

    public String getFojasDante() {
        return fojasDante;
    }

    public void setFojasDante(String fojasDante) {
        this.fojasDante = fojasDante;
    }

    public String getNotificaConDante() {
        return notificaConDante;
    }

    public void setNotificaConDante(String notificaConDante) {
        this.notificaConDante = notificaConDante;
    }

    public String getCelularDante() {
        return celularDante;
    }

    public void setCelularDante(String celularDante) {
        this.celularDante = celularDante;
    }

    public String getDomicilioDante() {
        return domicilioDante;
    }

    public void setDomicilioDante(String domicilioDante) {
        this.domicilioDante = domicilioDante;
    }

    public Date getFechaNotificacionDado() {
        return fechaNotificacionDado;
    }

    public void setFechaNotificacionDado(Date fechaNotificacionDado) {
        this.fechaNotificacionDado = fechaNotificacionDado;
    }

    public Date getFechaDevolucionDado() {
        return fechaDevolucionDado;
    }

    public void setFechaDevolucionDado(Date fechaDevolucionDado) {
        this.fechaDevolucionDado = fechaDevolucionDado;
    }

    public String getComboEstadoDado() {
        return comboEstadoDado;
    }

    public void setComboEstadoDado(String comboEstadoDado) {
        this.comboEstadoDado = comboEstadoDado;
    }

    public String getComboNotificadoEnDado() {
        return comboNotificadoEnDado;
    }

    public void setComboNotificadoEnDado(String comboNotificadoEnDado) {
        this.comboNotificadoEnDado = comboNotificadoEnDado;
    }

    public String getComboCiudadNotifiDado() {
        return comboCiudadNotifiDado;
    }

    public void setComboCiudadNotifiDado(String comboCiudadNotifiDado) {
        this.comboCiudadNotifiDado = comboCiudadNotifiDado;
    }

    public String getObsNotificadorDado() {
        return obsNotificadorDado;
    }

    public void setObsNotificadorDado(String obsNotificadorDado) {
        this.obsNotificadorDado = obsNotificadorDado;
    }

    public String getDescripcionDado() {
        return descripcionDado;
    }

    public void setDescripcionDado(String descripcionDado) {
        this.descripcionDado = descripcionDado;
    }

    public String getSolicitanteDado() {
        return solicitanteDado;
    }

    public void setSolicitanteDado(String solicitanteDado) {
        this.solicitanteDado = solicitanteDado;
    }

    public String getApoderadoDado() {
        return apoderadoDado;
    }

    public void setApoderadoDado(String apoderadoDado) {
        this.apoderadoDado = apoderadoDado;
    }

    public String getFojasDado() {
        return fojasDado;
    }

    public void setFojasDado(String fojasDado) {
        this.fojasDado = fojasDado;
    }

    public String getNotificaConDado() {
        return notificaConDado;
    }

    public void setNotificaConDado(String notificaConDado) {
        this.notificaConDado = notificaConDado;
    }

    public String getCelularDado() {
        return celularDado;
    }

    public void setCelularDado(String celularDado) {
        this.celularDado = celularDado;
    }

    public String getDomicilioDado() {
        return domicilioDado;
    }

    public void setDomicilioDado(String domicilioDado) {
        this.domicilioDado = domicilioDado;
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

    public String getImgMinisterio() {
        return imgMinisterio;
    }

    public void setImgMinisterio(String imgMinisterio) {
        this.imgMinisterio = imgMinisterio;
    }

    public List<NotiFormularioPojo> getListNotiFormularioPojo() {
        return listNotiFormularioPojo;
    }

    public void setListNotiFormularioPojo(List<NotiFormularioPojo> listNotiFormularioPojo) {
        this.listNotiFormularioPojo = listNotiFormularioPojo;
    }

    public ComunService getComunService() {
        return comunService;
    }

    public void setComunService(ComunService comunService) {
        this.comunService = comunService;
    }

    public Long getLogtrans() {
        return logtrans;
    }

    public void setLogtrans(Long logtrans) {
        this.logtrans = logtrans;
    }

    public SigSignoMarcaService getSigSignoMarcaService() {
        return sigSignoMarcaService;
    }

    public void setSigSignoMarcaService(SigSignoMarcaService sigSignoMarcaService) {
        this.sigSignoMarcaService = sigSignoMarcaService;
    }

    public LogTransService getLogTransService() {
        return logTransService;
    }

    public void setLogTransService(LogTransService logTransService) {
        this.logTransService = logTransService;
    }

    public SeguimientoService getSeguimientoService() {
        return seguimientoService;
    }

    public void setSeguimientoService(SeguimientoService seguimientoService) {
        this.seguimientoService = seguimientoService;
    }

    public List<Dominio> getLstCiudadNotificacion() {
        return lstCiudadNotificacion;
    }

    public void setLstCiudadNotificacion(List<Dominio> lstCiudadNotificacion) {
        this.lstCiudadNotificacion = lstCiudadNotificacion;
    }

    public DominioService getDominioService() {
        return dominioService;
    }

    public void setDominioService(DominioService dominioService) {
        this.dominioService = dominioService;
    }

    public boolean isActivaFechasDeman() {
        return activaFechasDeman;
    }

    public void setActivaFechasDeman(boolean activaFechasDeman) {
        this.activaFechasDeman = activaFechasDeman;
    }

    public boolean isActivaFechasDado() {
        return activaFechasDado;
    }

    public void setActivaFechasDado(boolean activaFechasDado) {
        this.activaFechasDado = activaFechasDado;
    }

    public boolean isActivaFecha() {
        return activaFecha;
    }

    public void setActivaFecha(boolean activaFecha) {
        this.activaFecha = activaFecha;
    }

    public ModModificacionService getModModificacionService() {
        return modModificacionService;
    }

    public void setModModificacionService(ModModificacionService modModificacionService) {
        this.modModificacionService = modModificacionService;
    }

    public RenSolicitudRenovacionService getRenSolicitudRenovacionService() {
        return renSolicitudRenovacionService;
    }

    public void setRenSolicitudRenovacionService(RenSolicitudRenovacionService renSolicitudRenovacionService) {
        this.renSolicitudRenovacionService = renSolicitudRenovacionService;
    }

    public Etapa getEtapa() {
        return etapa;
    }

    public void setEtapa(Etapa etapa) {
        this.etapa = etapa;
    }

    public EtapaService getEtapaService() {
        return etapaService;
    }

    public void setEtapaService(EtapaService etapaService) {
        this.etapaService = etapaService;
    }

    public SigHistorialService getSigHistorialService() {
        return sigHistorialService;
    }

    public void setSigHistorialService(SigHistorialService sigHistorialService) {
        this.sigHistorialService = sigHistorialService;
    }

    public FlujoTiempoService getFlujoTiempoService() {
        return flujoTiempoService;
    }

    public void setFlujoTiempoService(FlujoTiempoService flujoTiempoService) {
        this.flujoTiempoService = flujoTiempoService;
    }

}
