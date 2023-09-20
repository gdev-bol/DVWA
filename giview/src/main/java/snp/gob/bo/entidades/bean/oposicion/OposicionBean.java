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
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import snp.gob.bo.entidades.bean.common.AbstractManagedBean;
import snp.gob.bo.gimodel.entidad.BusquedaOposicion;
import snp.gob.bo.gimodel.entidad.Dominio;
import snp.gob.bo.gimodel.entidad.Etapa;
import snp.gob.bo.gimodel.entidad.LogTrans;
import snp.gob.bo.gimodel.entidad.Notificacion;
import snp.gob.bo.gimodel.entidad.ObservacionTramite;
import snp.gob.bo.gimodel.entidad.OpoEvento;
import snp.gob.bo.gimodel.entidad.OpoFechalimite;
import snp.gob.bo.gimodel.entidad.OpoHistorial;
import snp.gob.bo.gimodel.entidad.OpoMarcademandada;
import snp.gob.bo.gimodel.entidad.OpoMarcademandante;
import snp.gob.bo.gimodel.entidad.OpoNotificacion;
import snp.gob.bo.gimodel.entidad.OpoObjetoDmteDmdoNotiSoliapo;
import snp.gob.bo.gimodel.entidad.OpoRecurso;
import snp.gob.bo.gimodel.entidad.OpoResolucion;
import snp.gob.bo.gimodel.entidad.OpoSolicitanteapoderado;
import snp.gob.bo.gimodel.entidad.Oposicion;
import snp.gob.bo.gimodel.entidad.Oposicionlist;
import snp.gob.bo.gimodel.entidad.SMOposicion;
import snp.gob.bo.gimodel.entidad.Seguimiento;
import snp.gob.bo.gimodel.entidad.SigDireccionDeNotificacion;
import snp.gob.bo.gimodel.entidad.SigObservacionTramite;
import snp.gob.bo.gimodel.entidad.SigSignoMarca;
import snp.gob.bo.gimodel.entidad.SigSolicitanteApoderado;
import snp.gob.bo.gimodel.enums.EnumNombreDominio;
import snp.gob.bo.gimodel.servicio.BusquedaOposicionService;
import snp.gob.bo.gimodel.servicio.ComunService;
import snp.gob.bo.gimodel.servicio.DominioService;
import snp.gob.bo.gimodel.servicio.EtapaService;
import snp.gob.bo.gimodel.servicio.FlujoTiempoService;
import snp.gob.bo.gimodel.servicio.LogTransService;
import snp.gob.bo.gimodel.servicio.NotificacionService;
import snp.gob.bo.gimodel.servicio.ObservacionTramiteService;
import snp.gob.bo.gimodel.servicio.OpoActividadService;
import snp.gob.bo.gimodel.servicio.OpoEstadoService;
import snp.gob.bo.gimodel.servicio.OpoEventoService;
import snp.gob.bo.gimodel.servicio.OpoFechaLimiteService;
import snp.gob.bo.gimodel.servicio.OpoGeneralService;
import snp.gob.bo.gimodel.servicio.OpoHistorialService;
import snp.gob.bo.gimodel.servicio.OpoMarcademandadaService;
import snp.gob.bo.gimodel.servicio.OpoMarcademandanteService;
import snp.gob.bo.gimodel.servicio.OpoNotificacionService;
import snp.gob.bo.gimodel.servicio.OpoRecursoService;
import snp.gob.bo.gimodel.servicio.OpoResolucionService;
import snp.gob.bo.gimodel.servicio.OpoSolicitanteaopderadoService;
import snp.gob.bo.gimodel.servicio.OposicionService;
import snp.gob.bo.gimodel.servicio.SeguimientoService;
import snp.gob.bo.gimodel.servicio.SigDireccionDeNotificacionService;
import snp.gob.bo.gimodel.servicio.SigSignoMarcaService;
import snp.gob.bo.gimodel.servicio.SigSolicitanteApoderadoService;
import snp.gob.bo.gimodel.servicio.UsuarioService;
//import snp.gob.bo.gimodel.servicio.SMDominioService;

/**
 *
 * @author Luis Angel Quispe Limachi
 */
@ManagedBean(name = "oposicionBean")
@ViewScoped
public class OposicionBean extends AbstractManagedBean implements Serializable {

    public OposicionBean() {
    }

    //VARIABLES DE PARA QUE FUNCIONE LOS RADIOS Y LAS LISTAS
    private int valorPUB = 0;
    private String valorREG = "";
    private String valorEXP = "";
    private String valorMAR = "";
    private String valorDMO = "";
    private String valorDME = "";
    private String valorAPO = "";
    private String valorX1 = "";
    private String valorX2 = "";
    private String valorX3 = "";

    private String tipoMarca;
    private String valorRadio = "CN";
    private String valorint;
    private int numero = 0;
    private int numero1 = 0;
    private int expediente = 0;
    private String demandante = "Juan Arco";
    private String firstname;
    private String lastname;
    private String Pais;
    private String marcasvs = "";
    //variables para que aparesca los bones de adicionar eliminar
    private String nomrad = "CON";
    private Boolean variadi = true;
    private Boolean varieli = true;
    private Boolean varimodi = true;
    private Boolean varisegui = false;
    private Boolean varihisto = false;

    //Variables de la Vista Cajas etc
    private OpoMarcademandante opodemandante = new OpoMarcademandante();
    private OpoMarcademandante opodemandanteok = new OpoMarcademandante();
    private String dirnotidmte = "Calle Indaburo #3414";

    //Variables de direccion de notificacion
    @ManagedProperty("#{opoNotificacionService}")
    private OpoNotificacionService opoNotificacionService;
    @ManagedProperty("#{flujoTiempoService}")
    private FlujoTiempoService flujoTiempoService;
    

    private OpoNotificacion oponoti = new OpoNotificacion();
    private String noticiudaddmte;
    private String notizbarriodmte;
    private String notiacalledmte;
    private String noticelulardmte;
    private String notinumerocasadmte;
    private String notiedificiodmte;
    private String notipisodmte;
    private String notinrodepartamentodmte;
    private String notiemaildmte;

//Variables de listas para cargar los datagrid's
    private List<OpoMarcademandante> listademantante;
    private List<OpoSolicitanteapoderado> listaapoderado;
    private List<OpoMarcademandada> listademandado;

    private Boolean apareceTab = false;
    private Boolean encabezado = Boolean.FALSE;
    private List<SMOposicion> listaOposicion = new ArrayList<>();
    private List<Oposicionlist> listaofi = new ArrayList<>();
    private List<String> listado = new ArrayList<String>();
    private String cadena;
    private List<SelectItem> categories;
    private String selection;

    //VARIABLES DE LA VISTA PARTE DE LA TABLA OPOSICION ** OBJETOS y UNICAS
    private Oposicion datosoposicion = new Oposicion();
    private Oposicion datosoposicionmodi = new Oposicion();
    private Oposicion datoshistorialaux = new Oposicion();
    private OpoMarcademandada datosmarcademandada = new OpoMarcademandada();
    private OpoMarcademandada datosmarcademandadamodi = new OpoMarcademandada();
    private OpoMarcademandada datosmarcademandadamodigral = new OpoMarcademandada();
    private OpoMarcademandada datoshistorialauxmarcademandada = new OpoMarcademandada();
    private OpoMarcademandante datoshistorialauxmarcademandante = new OpoMarcademandante();
    private OpoMarcademandante datosmarcademandante = new OpoMarcademandante();
    private OpoMarcademandante datosmarcademandantemodi = new OpoMarcademandante();
    private OpoHistorial datoshistorialoposicion = new OpoHistorial();
    private OpoNotificacion datosnotificaciondmdo = new OpoNotificacion();
    private OpoNotificacion datosnotificaciondmte = new OpoNotificacion();
    private OpoNotificacion datosnotificaciondmdomodi = new OpoNotificacion();
    private OpoNotificacion datosnotificaciondmtemodi = new OpoNotificacion();
    private OpoResolucion datosresolucion = new OpoResolucion();
    private OpoEvento datosopoevento = new OpoEvento();
    private List<OpoEvento> listaactividades = new ArrayList<>();
    private List<BusquedaOposicion> listaBusquedaOposicion = new ArrayList<>();
    private OpoFechalimite datosfechalimite = new OpoFechalimite();
    private OpoHistorial datoshistorial = new OpoHistorial();
    private List<OpoSolicitanteapoderado> listasolidmdo = new ArrayList<>();
    private List<OpoSolicitanteapoderado> listaapodmdo = new ArrayList<>();
    private List<OpoSolicitanteapoderado> listasolidmte = new ArrayList<>();
    private List<OpoSolicitanteapoderado> listaapodmte = new ArrayList<>();
    private List<Seguimiento> listastaseg = new ArrayList<>();
    private OpoSolicitanteapoderado ObjetoAuxOposoli = new OpoSolicitanteapoderado();
    private OpoSolicitanteapoderado ObjetoAuxOposoliApo = new OpoSolicitanteapoderado();
    private OpoSolicitanteapoderado OpoSolicitanteApoderadoAux = new OpoSolicitanteapoderado();
    private OpoSolicitanteapoderado OpoSolicitanteApoderadoAux1 = new OpoSolicitanteapoderado();
    private OpoSolicitanteapoderado ObjetoAuxOposolidmda = new OpoSolicitanteapoderado();
    private Seguimiento ultimoSeguimientoopo = new Seguimiento();
    private SigSignoMarca SignomarcaAux = new SigSignoMarca();
    private SigSignoMarca SignomarcaAuxiliar = new SigSignoMarca();
    private SigSignoMarca signomarcaActumarca = new SigSignoMarca();
    private SigSignoMarca signomarcaActuObs = new SigSignoMarca();
    private ObservacionTramite sigmarcaObs = new ObservacionTramite();
    private ObservacionTramite sigmarcaObsEdit = new ObservacionTramite();

    private List<Etapa> listEtapaUs;
    @ManagedProperty(value = "#{opoGeneralService}")
    private OpoGeneralService opoGeneralService;

    @ManagedProperty(value = "#{opoEventoService}")
    private OpoEventoService opoEventoService;

    @ManagedProperty(value = "#{opoFechaLimiteService}")
    private OpoFechaLimiteService opoFechaLimiteService;

    @ManagedProperty(value = "#{opoHistorialService}")
    private OpoHistorialService opoHistorialService;

    @ManagedProperty(value = "#{opoSolicitanteApoderadoService}")
    private OpoSolicitanteaopderadoService opoSolicitanteApoderadoService;

    @ManagedProperty("#{smDominioService}")
    private DominioService smDominioService;

    @ManagedProperty(value = "#{opoRecursoService}")
    private OpoRecursoService opoRecursoService;

    @ManagedProperty(value = "#{busquedaOposicionService}")
    private BusquedaOposicionService busquedaOposicionService;

    @ManagedProperty("#{logTransService}")
    private LogTransService logTransService;

    @ManagedProperty("#{seguimientoService}")
    private SeguimientoService seguimientoService;

    @ManagedProperty("#{observacionTramiteService}")
    private ObservacionTramiteService observacionTramiteService;

    private OpoObjetoDmteDmdoNotiSoliapo enlazaobjetos = new OpoObjetoDmteDmdoNotiSoliapo();

    //variables de obj oposicion
    public Long txtnropublidmdo;
    public Date cldfechapresoposicion = new Date();

    public Integer txtorden_o;
    public String txaobservacion;
    //variables demandada
    private Long txtpublidmdo;
    private Integer txtpublidmdoclon = 0;
    private Integer nroulti;
    private Integer txtgacetadmdo;
    private Date cldfecahpubdmdo;
    private String txamarcadmdo;
    private String txtdmdosm;

    private String txtciudaddmdo;
    private String txtzonadmdo;
    private String txtcalledmdo;
    private String txtnrocasadmdo;
    private String txtcelulardmdo;
    private String txtedificiodmdo;
    private String txtpisodmdo;
    private String txtnrodepartamentodmdo;
    private String txttelefonodmdo;
    private String txtemaildmdo;
    private String txtreferenciadmdo;
    private String txtestadodmdo;

    //variables demandante
    private Integer txtpubldmte;
    private Integer txtregistrodmte;
    private String txtsmexpedientedmte;
    private String cmdregistrodmte;
    private String txamarcadmte;
    private String txtusodmte;
    private List<Dominio> lstSerieRegistro;
    private List<Dominio> lstDmteDmdo;
    private String txtciudaddmte;
    private String txtzonadmte;
    private String txtcalledmte;
    private String txtnrocasadmte;
    private String txtceludmte;
    private String txtedificiodmte;
    private String txtpisodmte;
    private String txtnrodepadmte;
    private String txtemaildmte;
    private String txttelefonodmte;
    private String txtreferenciadmte;
    private String cmbregistro;
    private String cmbregistrobusqueda;
    private String cmbtipodmdodmte;
    private Long txtexpedientesm;
    private Long txtgestionexpediente;
    private String smdmte = "";
    private Integer nroopodmteaux = 0;
    private String txtextencionespediente = new String();

    private String txtestadodmte;
    //datos de las listas solicitante apoderado
    private String nom_razonsocialsolidmte;
    private String paissolidmte;
    private String solicitud_depasolidmte;
    private String direccionsolidmte;
    private String telefonosolidmte;
    private String faxsolidmte;
    private String emailsolidmte;

    private String nom_razonsocialapodmte;
    private String paisapodmte;
    private String solicitud_depaapodmte;
    private String direccionapodmte;
    private String telefonoapodmte;
    private String faxapodmte;
    private String emailapodmte;

    private String nom_razonsocialsolidmda;
    private String paissolidmda;
    private String solicitud_depasolidmda;
    private String direccionsolidmda;
    private String telefonosolidmda;
    private String faxsolidmda;
    private String emailsolidmda;

    private String nom_razonsocialopodmda;
    private String paisopodmda;
    private String solicitud_depaopodmda;
    private String direccionopodmda;
    private String telefonoopodmda;
    private String faxopodmda;
    private String emailopodmda;
//Datos para el panel de informacion

    private String txtestadotramite;
    private String txtultimactividad;
    private Date cldfechaultimaactividad;
    //Otros
    private Integer indice = 0;
    private Integer indice1 = 0;
    private Integer indice2 = 0;
    private Integer indice3 = 0;
    private Long clavepriok = 1L;
    private String banderadmtedmdo = "";

    //datos Para La Busqueda de Oposicion
    private Long txtpublicabusqueda;
    private Integer txtregistrobusqueda;
    private Long txtexpedientebusqueda;
    private Long txtexpedientebusquedagestion;
    private String txtmarcabusqueda;
    private String txtdemandantebusqueda;
    private String txtapoderadobusqueda;
    private Date cldfecahpubbusqueda;
    private String nombrefechabusqueda = "NF";

    //variables para resolucion y recurso
    private String txtnrogestionres;
    private String txtopores;
    private String txtsigres;
    private String txtcancelares;
    private Integer txt_auxorden = 1;
    private String txtnrogestionrecurso;
    private String txttiporecurso;
    private String txtresolucionrecurso;

    Calendar gregfech = new GregorianCalendar();
    //variable para agarrar el identificados de secion
    private Long idUsuarioSesion;
    //variables para Cargar datos en la vista
    @ManagedProperty(value = "#{opoMarcaDemandanteService}")
    private OpoMarcademandanteService opoMarcaDemandanteService;

    @ManagedProperty(value = "#{oposicionService}")
    private OposicionService oposicionService;
    @ManagedProperty(value = "#{opoMarcaDemandadaService}")
    private OpoMarcademandadaService opoMarcaDemandadaService;
    @ManagedProperty(value = "#{notificacionService}")
    private NotificacionService notificacionService;
    @ManagedProperty(value = "#{opoActividadService}")
    private OpoActividadService opoActividadService;
    @ManagedProperty(value = "#{opoEstadoService}")
    private OpoEstadoService opoEstadoService;
    @ManagedProperty(value = "#{sigDireccionDeNotificacionService}")
    private SigDireccionDeNotificacionService sigDireccionDeNotificacionService;
    @ManagedProperty(value = "#{opoResolucionService}")
    private OpoResolucionService opoResolucionService;
    @ManagedProperty("#{dominioService}")
    private DominioService dominioService;
    @ManagedProperty(value = "#{usuarioService}")
    private UsuarioService usuarioService;
    @ManagedProperty("#{comunService}")
    private ComunService comunService;

    @ManagedProperty("#{etapaService}")
    private EtapaService etapaService;
    private SigDireccionDeNotificacion notificaciondatossig = new SigDireccionDeNotificacion();
    private SigDireccionDeNotificacion notificaciondatossigdmte = new SigDireccionDeNotificacion();
    private OpoMarcademandante datosmarcademandantexordenopo = new OpoMarcademandante();
    private Oposicion datosoposicionbusqueda = new Oposicion();
    private OpoNotificacion notificadmdavist = new OpoNotificacion();
    private OpoMarcademandante marcadmteseguimiento = new OpoMarcademandante();
    private OpoNotificacion notificadmtevist = new OpoNotificacion();
    private List<Oposicion> listascasosadj = new ArrayList<>();
    private List<Oposicion> listasresultados = new ArrayList<>();
    private List<Dominio> lstCiudadNotificacion;
    private List<Oposicion> listaxdmte = new ArrayList<>();

    private List<OpoRecurso> listarecursos = new ArrayList<>();

    private List<OpoMarcademandante> listademandantevista = new ArrayList<>();
    private OpoMarcademandada marcadadavist = new OpoMarcademandada();
    private OpoMarcademandante marcadmtevist = new OpoMarcademandante();
    private OpoEvento eventodemarca = new OpoEvento();
    private Seguimiento ultimoSeguimiento;
    private Boolean btnSeguimientoOpoRendered = true;
    private Boolean btnInicioTramiteRendered = false;
    //variables para los casos adjuntos
    private Oposicion selectedOpo;

    //Cargar datoa de la tabla de signos  pasndo el mause por una ventana
    @ManagedProperty(value = "#{sigSignoMarcaService}")
    private SigSignoMarcaService sigSignoMarcaService;

    @ManagedProperty(value = "#{sigSolicitanteApoderadoService}")
    private SigSolicitanteApoderadoService sigSolicitanteApoderadoService;

    //@ManagedProperty(value = "#{smDominioService}")
//    private SMDominioService sMDominioService;
//    List<SMDominio> listaSMDominio = new ArrayList<SMDominio>();
    @PostConstruct

    public void ProtoOpoInit() {

        // nombre = "Mensaje Constructivo ";
        try {
            obtenerParametrosBusquedas();
            lstSerieRegistro = dominioService.obtenerListadoDominio("serie_registro");
            lstDmteDmdo = dominioService.obtenerListadoDominio("tiposoliapo");
            lstCiudadNotificacion = dominioService.obtenerListadoDominio("ciudad_notificacion");
            this.cldfechapresoposicion = comunService.obtenerFechaHoraServidor(1L);
            listEtapaUs = etapaService.listadoPorIdUsuario(super.getIdUsuarioSession(), super.getIdEtapaSession());

            nomrad = "CON";

            idUsuarioSesion = super.getIdUsuarioSession();
            btnSeguimientoOpoRendered = true;

        } catch (Exception e) {
        }

    }

    /**
     * Método para mostrar los mensajes del numero de orden Nombre Luis Angel
     * Quispe limachi Fecha 12/09/2016
     *
     * @param summary
     * @param detail
     * @throws java.lang.Exception
     */
    public void addMessage(String summary, String detail) throws Exception {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    /**
     * Método que permite extraer parametros de signos y enviarlos a otra vista
     * en la misma ventana
     *
     * Autor: Luis Angel Quispe Limachi
     *
     * Fecha:25-12-2016
     *
     * @throws java.lang.Exception
     */
    public void obtenerParametrosBusquedas() throws Exception {
        try {
            Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
            String parIdTramite = (String) (params.get("ThrEimhaJd5"));
            String parIdUsuario = (String) (params.get("UkYJ0g3jLwc"));
            if (!parIdTramite.trim().equals("") && !parIdUsuario.trim().equals("") && !parIdTramite.equals("null") && !parIdUsuario.equals("null")) {
                setIdUsuarioSesion(Long.parseLong(parIdUsuario));
                setTxtnropublidmdo(Long.parseLong(parIdTramite));
                buscadorPorNumeroPublicacion();
            }
        } catch (Exception e) {
        }
    }

    /*
     * Método que envia datos de signos con su publicacion y el numero de publicacion
     *
     * @throws java.lang.Exception
     */
    public String urlInformacionOPO(String idTramite, String idUsuarioLogin) {
        return "this.form.target = '_blank'; window.open('../../signo/examenSignos.xhtml?ThrEimhaJd51=" + idTramite + "&UkYJ0g3jLwc1=" + idUsuarioLogin + "&UkYJ0g3jLwxxx1=" + "PUBLI1" + "');";

    }

    /**
     *
     * Metodo para extraer datos del registro
     *
     * Creado: Luis Angel Quispe
     *
     * Fecha: 07/12/201
     *
     * @param registro
     * @param regext
     * @param idUsuarioLogin
     * @return
     */
    public String urlInformacionREG(String registro, String regext, String idUsuarioLogin) {
        return "this.form.target = '_blank'; window.open('../../signo/examenSignos.xhtml?ThrEimhaJdx=" + registro + "&ThrEimhaJd1x=" + regext + "&UkYJ0g3jLwcx=" + idUsuarioLogin + "&UkYJ0g3jLwxxx1=" + "REG" + "');";

    }

    /**
     *
     * Metodo para extraer datos del registro
     *
     * Creado: Luis Angel Quispe
     *
     * Fecha: 07/12/201
     *
     * @param numero
     * @param gestion
     * @param exten
     * @param idUsuarioLogin
     * @return
     */
    public String urlInformacionSMSOL(String numero, String gestion, String exten, String idUsuarioLogin) {
        return "this.form.target = '_blank'; window.open('../../signo/examenSignos.xhtml?ThrEimhaJd1x=" + numero + "&ThrEimhaJd2x=" + gestion + "&ThrEimhaJd3x=" + exten + "&UkYJ0g3jLwcxy=" + idUsuarioLogin + "&UkYJ0g3jLwxxx1=" + "SM" + "');";

    }

    /**
     * Método que permite guardar la oposicion,demandatntes demandados y
     * notificacion
     *
     * @throws java.lang.Exception
     */
    public void guardar_datosoposiciondmtedmdanotisoliapo() throws Exception {

        try {

            if (cldfechapresoposicion != null) {
                Date fechaSistema = comunService.obtenerFechaHoraServidor(1L);
                LogTrans logTransGuardado = logTransService.crudLogTrans(new LogTrans(idUsuarioSesion, fechaSistema), 1);

                datosoposicion.setNro_opo(txtpublidmdo); //numero opo del demandado
                datosoposicion.setFecha_presentacion(cldfechapresoposicion); //del formulario
                datosoposicion.setGaceta(txtgacetadmdo);//numero gaceta de demandado
                datosoposicion.setObservacion(txaobservacion);
                datosoposicion.setId_estado(2L);
                datosoposicion.setEstadoopo("AC");
                datosoposicion.setNumero_formulario(null);
                datosoposicion.setGestion_opo(0);
                datosoposicion.setCodigo_opo(0L);
                datosoposicion.setIngreso_opo("NI");

                datosoposicion.setOrden_o(oposicionService.extraerultimonorden(txtnropublidmdo) + 1);
                nroopodmteaux = oposicionService.extraerultimonorden(txtnropublidmdo) + 1;

//   Integer entero = Integer.valueOf(txtgacetadmdo);
                datosmarcademandada.setDmdo_public(Integer.valueOf(txtpublidmdo + ""));
                datosmarcademandada.setNro_opo(Integer.valueOf(txtpublidmdo + "")); //numero opo demandada
                datosmarcademandada.setGaceta(txtgacetadmdo);//gaseta demandada
                datosmarcademandada.setFecha_public(cldfecahpubdmdo);
                datosmarcademandada.setDmdo_marca_lnv(txamarcadmdo); //la marca de del tramite
                datosmarcademandada.setIdlogtrans(logTransGuardado.getIdLogTrans());
                datosmarcademandada.setEstado("AC");
                //datosmarcademandada.setDmdo_public(546);
                datosmarcademandante.setDmte_public(txtpubldmte);
                datosmarcademandante.setDmte_reg(txtregistrodmte);

                datosmarcademandante.setDmte_uso(txtusodmte);
                datosmarcademandante.setDmte_serie(cmbregistro);
                datosmarcademandante.setDmte_marca_lnv(txamarcadmte);
                datosmarcademandante.setIdlogtrans(logTransGuardado.getIdLogTrans());

                //Segmento para cambiar el estado de sigsignomarca
                if (txtpublidmdo != null && txtpublidmdo != 0) {
                    signomarcaActumarca = sigSignoMarcaService.listaSigSignoMarcaXPublicacion(txtpublidmdo);
                    signomarcaActumarca.setUbicacion("PIOP");
                    signomarcaActumarca.setEstadoMarca("OPO");
                    sigSignoMarcaService.crudSigSignoMarca(signomarcaActumarca, 2);
                }

                if (txtexpedientesm != null && txtgestionexpediente != null) {

                    int canticarct = (Long.toString(txtexpedientesm)).length();
                    String cadesm = "";

                    switch (canticarct) {

                        case 1:
                            cadesm = "00000" + Long.toString(txtexpedientesm);
                            break;
                        case 2:
                            cadesm = "0000" + Long.toString(txtexpedientesm);
                            break;
                        case 3:
                            cadesm = "000" + Long.toString(txtexpedientesm);
                            break;
                        case 4:
                            cadesm = "00" + Long.toString(txtexpedientesm);
                            break;
                        case 5:
                            cadesm = "0" + Long.toString(txtexpedientesm);
                            break;
                        default:
                            cadesm = Long.toString(txtexpedientesm);
                            //Este sm son para las regionales
                            ;
                            break;
                    }

                    if (txtextencionespediente == null || txtextencionespediente.equals("")) {
                        smdmte = Long.toString(txtgestionexpediente) + cadesm + "00";
                    } else {
                        //    char ch = (char) (Integer.valueOf(txtextencionespediente) + 55);
                        System.out.println("&&&&&&&&&&&&&&&&&&&&&!!!!!!!!!!!>" + txtextencionespediente);
                        if (txtextencionespediente.equals("A")) {
                            smdmte = Long.toString(txtgestionexpediente) + cadesm + "10";
                        } else {
                            if (txtextencionespediente.equals("B")) {
                                smdmte = Long.toString(txtgestionexpediente) + cadesm + "11";

                            } else {
                                if (txtextencionespediente.equals("C")) {
                                    smdmte = Long.toString(txtgestionexpediente) + cadesm + "12";
                                } else {
                                    smdmte = Long.toString(txtgestionexpediente) + cadesm + txtextencionespediente;
                                }
                            }
                        }
                        // smdmte = Long.toString(txtgestionexpediente) + cadesm + txtextencionespediente;
                        System.out.println("????????????????????????????????>" + smdmte);
                    }
//                    System.out.println("La =======================================>" + smdmte);
//                    signomarcaActumarca = sigSignoMarcaService.listaSigSignoMarcaXSM(Long.valueOf(smdmte));
//                    signomarcaActumarca.setUbicacion("PIOP");
//                    signomarcaActumarca.setEstadoMarca("OPO");
//                    sigSignoMarcaService.crudSigSignoMarca(signomarcaActumarca, 2);
                }
                System.out.println("La Otraaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa>" + smdmte);
                if (!smdmte.equals("")) {
                    datosmarcademandante.setDmte_sm(Long.valueOf(smdmte));
                }
                datosmarcademandante.setOrden_opo(nroopodmteaux);
                datosmarcademandante.setEstado("AC");

                datosnotificaciondmdo.setCiudad_notificacion(txtciudaddmdo);
                datosnotificaciondmdo.setZona_barrio(txtzonadmdo);
                datosnotificaciondmdo.setAvenida_calle(txtcalledmdo);
                datosnotificaciondmdo.setNumero(txtnrocasadmdo);
                datosnotificaciondmdo.setCelular(txtcelulardmdo);
                datosnotificaciondmdo.setEdificio(txtedificiodmdo);
                datosnotificaciondmdo.setPiso(txtpisodmdo);
                datosnotificaciondmdo.setNumero_departamento(txtnrodepartamentodmdo);
                datosnotificaciondmdo.setTelefono(txttelefonodmdo);
                datosnotificaciondmdo.setEmail(txtemaildmdo);
                datosnotificaciondmdo.setReferencia_direccion(txtreferenciadmdo);
                datosnotificaciondmdo.setEstado("AC");

                datosnotificaciondmte.setCiudad_notificacion(txtciudaddmte);
                datosnotificaciondmte.setZona_barrio(txtzonadmte);
                datosnotificaciondmte.setAvenida_calle(txtcalledmte);
                datosnotificaciondmte.setNumero(txtnrocasadmte);
                datosnotificaciondmte.setCelular(txtceludmte);
                datosnotificaciondmte.setEdificio(txtedificiodmte);
                datosnotificaciondmte.setPiso(txtpisodmte);
                datosnotificaciondmte.setNumero_departamento(txtnrodepadmte);
                datosnotificaciondmte.setTelefono(txttelefonodmte);
                datosnotificaciondmte.setEmail(txtemaildmte);
                datosnotificaciondmte.setReferencia_direccion(txtreferenciadmte);
                datosnotificaciondmte.setEstado("AC");

                enlazaobjetos.setObjopsicion(datosoposicion);
                enlazaobjetos.setObjmarcademandada(datosmarcademandada);
                enlazaobjetos.setObjmarcademandante(datosmarcademandante);

                for (OpoSolicitanteapoderado oposolidmdo : listasolidmdo) {

                    oposolidmdo.setTiposoliapo("DMDO");
                    oposolidmdo.setTipo_persona("SOLI");
                    oposolidmdo.setEstado("AC");
                }
                for (OpoSolicitanteapoderado oposolidmte : listasolidmte) {

                    oposolidmte.setTiposoliapo("DMTE");
                    oposolidmte.setTipo_persona("SOLI");
                    oposolidmte.setEstado("AC");
                }
                for (OpoSolicitanteapoderado opoapodmdo : listaapodmdo) {

                    opoapodmdo.setTiposoliapo("DMDO");
                    opoapodmdo.setTipo_persona("APOD");
                    opoapodmdo.setEstado("AC");
                }
                for (OpoSolicitanteapoderado opoapodmdo : listaapodmte) {

                    opoapodmdo.setTiposoliapo("DMTE");
                    opoapodmdo.setTipo_persona("APOD");
                    opoapodmdo.setEstado("AC");
                }

                Boolean datingresonropubli = oposicionService.verificarexi(Long.valueOf(enlazaobjetos.getObjmarcademandada().getDmdo_public()));
                enlazaobjetos.setListaapodmdo(listaapodmdo);
                enlazaobjetos.setListaapodmte(listaapodmte);
                enlazaobjetos.setListasolidmdo(listasolidmdo);
                enlazaobjetos.setListasolidmte(listasolidmte);

                enlazaobjetos.setObjnotificaciondmda(datosnotificaciondmdo);
                enlazaobjetos.setObjnotificaciondmte(datosnotificaciondmte);

                opoGeneralService.guardartramiteopo(enlazaobjetos);
//llenado de datos externos para opoevento, en la tabla logtrans y opo historial
                adicionaatablasadicionales(txtpublidmdo, txtorden_o);

//Es el cargado de seguimiento al precionar adicionar para numero de publicacion
                int swsegui = 0;
//                if (enlazaobjetos.getObjmarcademandante().getDmte_public() != null && enlazaobjetos.getObjmarcademandante().getDmte_public() != 0) {
//
//                    SignomarcaAux = sigSignoMarcaService.listaSigSignoMarcaXPublicacion(Long.valueOf(enlazaobjetos.getObjmarcademandante().getDmte_public()));
//
//                    Seguimiento seguimientoNuevoOpo = new Seguimiento();
//
//                    if (SignomarcaAux.getIdSignoMarca() != null) {
//                        seguimientoNuevoOpo.setId(SignomarcaAux.getIdSignoMarca());
//                    }
//                    seguimientoNuevoOpo.setIdUsuario(super.getIdUsuarioSession());
//                    seguimientoNuevoOpo.setIdLogTrans(logTransGuardado.getIdLogTrans());
//                    if (enlazaobjetos.getObjmarcademandante().getDmte_sm() != null) {
//                        seguimientoNuevoOpo.setSm(enlazaobjetos.getObjmarcademandante().getDmte_sm());
//                    }
//                    if (enlazaobjetos.getObjmarcademandante().getDmte_public() != null) {
//                        seguimientoNuevoOpo.setNumeroPublicacion((enlazaobjetos.getObjmarcademandante().getDmte_public()).longValue());
//                    }
//                    if (enlazaobjetos.getObjmarcademandante().getDmte_reg() != null) {
//                        seguimientoNuevoOpo.setNumeroRegistro((enlazaobjetos.getObjmarcademandante().getDmte_reg()).longValue());
//                    }
//                    if (!(enlazaobjetos.getObjmarcademandante().getDmte_serie()).equals("")) {
//                        seguimientoNuevoOpo.setSerieRegistro(enlazaobjetos.getObjmarcademandante().getDmte_serie());
//                    }
//
//                    seguimientoNuevoOpo.setEtapa(listEtapaUs.get(0).getTipoEtapa());
//                    seguimientoNuevoOpo.setObservacion(null);
//                    seguimientoNuevoOpo.setEditable(false);
//                    seguimientoNuevoOpo.setEstado("AC");
//                    ultimoSeguimientoopo = seguimientoService.guardar_modificar_Seguimiento_etapa(seguimientoNuevoOpo, "SIG");
//                    swsegui = 1;
//
//                }
//
//                //Es el cargado de seguimiento al precionar adicionar para numero de SM
//                if (enlazaobjetos.getObjmarcademandante().getDmte_sm() != null && enlazaobjetos.getObjmarcademandante().getDmte_sm() != 0 && swsegui == 0) {
//
//                    SignomarcaAux = sigSignoMarcaService.listaSigSignoMarcaXSM(enlazaobjetos.getObjmarcademandante().getDmte_sm());
//
//                    Seguimiento seguimientoNuevoOpo = new Seguimiento();
//
//                    if (SignomarcaAux.getIdSignoMarca() != null) {
//                        seguimientoNuevoOpo.setId(SignomarcaAux.getIdSignoMarca());
//                    }
//                    seguimientoNuevoOpo.setIdUsuario(super.getIdUsuarioSession());
//                    seguimientoNuevoOpo.setIdLogTrans(logTransGuardado.getIdLogTrans());
//                    if (enlazaobjetos.getObjmarcademandante().getDmte_sm() != null) {
//                        seguimientoNuevoOpo.setSm(enlazaobjetos.getObjmarcademandante().getDmte_sm());
//                    }
//                    if (enlazaobjetos.getObjmarcademandante().getDmte_public() != null) {
//                        seguimientoNuevoOpo.setNumeroPublicacion((enlazaobjetos.getObjmarcademandante().getDmte_public()).longValue());
//                    }
//                    if (enlazaobjetos.getObjmarcademandante().getDmte_reg() != null) {
//                        seguimientoNuevoOpo.setNumeroRegistro((enlazaobjetos.getObjmarcademandante().getDmte_reg()).longValue());
//                    }
//                    if (!(enlazaobjetos.getObjmarcademandante().getDmte_serie()).equals("")) {
//                        seguimientoNuevoOpo.setSerieRegistro(enlazaobjetos.getObjmarcademandante().getDmte_serie());
//                    }
//
//                    seguimientoNuevoOpo.setEtapa(listEtapaUs.get(0).getTipoEtapa());
//                    seguimientoNuevoOpo.setObservacion(null);
//                    seguimientoNuevoOpo.setEditable(false);
//                    seguimientoNuevoOpo.setEstado("AC");
//                    ultimoSeguimientoopo = seguimientoService.guardar_modificar_Seguimiento_etapa(seguimientoNuevoOpo, "SIG");
//                    swsegui = 1;
//
////                    signomarcaActumarca = sigSignoMarcaService.listaSigSignoMarcaXSM(enlazaobjetos.getObjmarcademandante().getDmte_sm());
////                    signomarcaActumarca.setUbicacion("PIOP");
////                    signomarcaActumarca.setEstadoMarca("OPO");
////                    sigSignoMarcaService.crudSigSignoMarca(signomarcaActumarca, 2);
//                }
//
//                //Es el cargado de seguimiento al precionar adicionar para numero de registro, serie de registro y toma en cuenta la marca 
//                if (enlazaobjetos.getObjmarcademandante().getDmte_reg() != null && swsegui == 0) {
//
//                    if (!enlazaobjetos.getObjmarcademandante().getDmte_serie().equals("")) {
//                        SignomarcaAux = sigSignoMarcaService.listaSigSignoMarcaXRegistro(Long.valueOf(enlazaobjetos.getObjmarcademandante().getDmte_reg()), "", enlazaobjetos.getObjmarcademandante().getDmte_marca_lnv());
//
//                        //Para cambiar los estados de la tablasigsignomarca        
////                        signomarcaActumarca = sigSignoMarcaService.listaSigSignoMarcaXRegistro(enlazaobjetos.getObjmarcademandante().getDmte_reg().longValue(), enlazaobjetos.getObjmarcademandante().getDmte_serie(), "SIG");
////                        signomarcaActumarca.setUbicacion("PIOP");
////                        signomarcaActumarca.setEstadoMarca("OPO");
////                        sigSignoMarcaService.crudSigSignoMarca(signomarcaActumarca, 2);
//                    } else {
//                        SignomarcaAux = sigSignoMarcaService.listaSigSignoMarcaXPublicacion(Long.valueOf(enlazaobjetos.getObjmarcademandante().getDmte_public()));
//                    }
//
//                    Seguimiento seguimientoNuevoOpo = new Seguimiento();
//
//                    if (SignomarcaAux.getIdSignoMarca() != null) {
//                        seguimientoNuevoOpo.setId(SignomarcaAux.getIdSignoMarca());
//                    }
//                    seguimientoNuevoOpo.setIdUsuario(super.getIdUsuarioSession());
//                    seguimientoNuevoOpo.setIdLogTrans(logTransGuardado.getIdLogTrans());
//                    if (enlazaobjetos.getObjmarcademandante().getDmte_sm() != null) {
//                        seguimientoNuevoOpo.setSm(enlazaobjetos.getObjmarcademandante().getDmte_sm());
//                    }
//                    if (enlazaobjetos.getObjmarcademandante().getDmte_public() != null) {
//                        seguimientoNuevoOpo.setNumeroPublicacion((enlazaobjetos.getObjmarcademandante().getDmte_public()).longValue());
//                    }
//                    if (enlazaobjetos.getObjmarcademandante().getDmte_reg() != null) {
//                        seguimientoNuevoOpo.setNumeroRegistro((enlazaobjetos.getObjmarcademandante().getDmte_reg()).longValue());
//                    }
//                    if (!(enlazaobjetos.getObjmarcademandante().getDmte_serie()).equals("")) {
//                        seguimientoNuevoOpo.setSerieRegistro(enlazaobjetos.getObjmarcademandante().getDmte_serie());
//                    }
//
//                    //    List<Etapa>  listEtapaUs=etapaService.listadoPorIdUsuario(super.getIdUsuarioSession());
//                    seguimientoNuevoOpo.setEtapa(listEtapaUs.get(0).getTipoEtapa());
//                    seguimientoNuevoOpo.setObservacion(null);
//                    seguimientoNuevoOpo.setEditable(false);
//                    seguimientoNuevoOpo.setEstado("AC");
//                    ultimoSeguimientoopo = seguimientoService.guardar_modificar_Seguimiento_etapa(seguimientoNuevoOpo, "SIG");
//                    swsegui = 1;
//
//                }
//
//                swsegui = 0;
//
//                //Es el cargado de seguimiento al precionar adicionar para numero publicacion por parte de signo marca demandada 
//                if (!datingresonropubli) {
//
//                    if (enlazaobjetos.getObjmarcademandada().getDmdo_public() != null && enlazaobjetos.getObjmarcademandada().getDmdo_public() != 0) {
//
//                        SignomarcaAux = sigSignoMarcaService.listaSigSignoMarcaXPublicacion(Long.valueOf(enlazaobjetos.getObjmarcademandada().getDmdo_public()));
//
//                        Seguimiento seguimientoNuevoOpo = new Seguimiento();
//
//                        if (SignomarcaAux.getIdSignoMarca() != null) {
//                            seguimientoNuevoOpo.setId(SignomarcaAux.getIdSignoMarca());
//                        }
//                        seguimientoNuevoOpo.setIdUsuario(super.getIdUsuarioSession());
//                        seguimientoNuevoOpo.setIdLogTrans(logTransGuardado.getIdLogTrans());
//
////                        if (enlazaobjetos.getObjmarcademandada().getDmdo_sm() != null) {
////                            seguimientoNuevoOpo.setSm(enlazaobjetos.getObjmarcademandada().getDmdo_sm());                           
////                        }
//                        if (enlazaobjetos.getObjmarcademandada().getDmdo_public() != null) {
//                            seguimientoNuevoOpo.setNumeroPublicacion((enlazaobjetos.getObjmarcademandada().getDmdo_public()).longValue());
//                        }
//                        seguimientoNuevoOpo.setEtapa(listEtapaUs.get(0).getTipoEtapa());
//                        seguimientoNuevoOpo.setObservacion(null);
//                        seguimientoNuevoOpo.setEditable(false);
//                        seguimientoNuevoOpo.setEstado("AC");
//                        ultimoSeguimientoopo = seguimientoService.guardar_modificar_Seguimiento_etapa(seguimientoNuevoOpo, "SIG");
//                        swsegui = 1;
//
//                    }
//
//                }
                swsegui = 0;

                //Guarda en signos la observacion que se guardo una oposicion
                if (txtpublidmdo != null) {
                    signomarcaActuObs = sigSignoMarcaService.listaSigSignoMarcaXPublicacion(txtpublidmdo);

                    long idsignomarca = signomarcaActuObs.getIdSignoMarca();
                    if (txamarcadmte != null && txamarcadmdo != null) {
                        marcasvs = txamarcadmte + "   V.s.  " + txamarcadmdo;
                    }
                    if (txamarcadmte == null) {
                        marcasvs = "" + "   V.s.  " + txamarcadmdo;
                    }
                    if (txamarcadmdo == null) {
                        marcasvs = txamarcadmte + "   V.s.  " + "";
                    }
                    System.out.println("======id signomarca==========" + idsignomarca);
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
                            sigmarcaObsNew.setDescripcion("-CASO(" + txtorden_o + "):" + marcasvs + " \n -ESTADO:" + " PRESENTADA \n -OBSERVACION: " + txaobservacion + " \n");
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
                            sigmarcaObsNew.setDescripcion(sigmarcaObs.getDescripcion() + "\n -CASO(" + txtorden_o + "):" + marcasvs + " \n -ESTADO:" + " PRESENTADA \n -OBSERVACION: " + txaobservacion + " \n");
                            observacionTramiteService.guardar_modificar_listar_ObservacionTramite(sigmarcaObsNew, "SIG", 1);
                        }
                    }                   
                }

                listascasosadj = oposicionService.obtenerListadoOposicion(txtnropublidmdo);
                addMessage("El registro se guardo exitosamente", "System Error");

            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "No Tiene Fecha de Presentacion", ""));
            }

            //cambio del cargado de datosss
            buscadorPorNumeroPublicacion();
        } catch (Exception e) {
            throw e;
        }

    }

    /**
     * Metodo adicionar tablas adicionales a la vista que se tratan internamente
     * Nombre Luis Angel Quispe limachi Fecha 12/09/2016
     *
     * @param txtpubbli
     * @param ordenopo
     * @throws java.lang.Exception
     */
    public void adicionaatablasadicionales(Long txtpubbli, Integer ordenopo) throws Exception {
        Date fechaSistema = comunService.obtenerFechaHoraServidor(1L);
        LogTrans logTransGuardado = logTransService.crudLogTrans(new LogTrans(idUsuarioSesion, fechaSistema), 1);

        datosopoevento.setIdactividad(1L);
        datosopoevento.setIdoposicion(oposicionService.encuentraclaveprin(txtpubbli, oposicionService.extraerultimonorden(txtpubbli)));
        datosopoevento.setFecha(cldfecahpubdmdo);
        datosopoevento.setIdlogtrans(logTransGuardado.getIdLogTrans());
        datosopoevento.setFecha_operacion(comunService.obtenerFechaHoraServidor(1L));
        datosopoevento.setOrden_o(ordenopo);
        datosopoevento.setUsuario(idUsuarioSesion);
        datosopoevento.setEstado("AC");
        opoEventoService.guardaOpoEvento(datosopoevento);

        datosopoevento.setIdactividad(2L);
        datosopoevento.setIdoposicion(oposicionService.encuentraclaveprin(txtpubbli, oposicionService.extraerultimonorden(txtpubbli)));
        datosopoevento.setFecha(cldfechapresoposicion);
        datosopoevento.setIdlogtrans(logTransGuardado.getIdLogTrans());
        datosopoevento.setFecha_operacion(comunService.obtenerFechaHoraServidor(1L));
        datosopoevento.setOrden_o(ordenopo);
        datosopoevento.setUsuario(idUsuarioSesion);
        datosopoevento.setEstado("AC");
        opoEventoService.guardaOpoEvento(datosopoevento);

        datosfechalimite.setIdoposicion(oposicionService.encuentraclaveprin(txtpubbli, oposicionService.extraerultimonorden(txtpubbli)));
        datosfechalimite.setIdevento(2L);
        datosfechalimite.setIdlogtrans(logTransGuardado.getIdLogTrans());
        datosfechalimite.setOrden_o(ordenopo);
        datosfechalimite.setIdactividadplazo(1L);
        opoFechaLimiteService.guardaOpoFechalimite(datosfechalimite);

        datoshistorial.setIdoposicion(oposicionService.encuentraclaveprin(txtpubbli, oposicionService.extraerultimonorden(txtpubbli)));
        datoshistorial.setIdlogtrans(logTransGuardado.getIdLogTrans());
        datoshistorial.setEstado("PRESENTADA");
        datoshistorial.setFecha_lim(cldfecahpubdmdo);
        datoshistorial.setObservacion("Tramite Iniciado");
        datoshistorial.setOperacion("ADD");
        datoshistorial.setFecha_operacion(comunService.obtenerFechaHoraServidor(1L));
        datoshistorial.setId_usuario(idUsuarioSesion);

        opoHistorialService.guardaOpoHistorial(datoshistorial);

        //        Falta Historial al llenar los datos
    }

    /**
     * Metodo habilitar botones de la parte baja de la bandeja para que se
     * renderice Nombre Luis Angel Quispe limachi Fecha 12/09/2016
     *
     * @throws java.lang.Exception
     */
    public void habilitabotones() throws Exception {

        switch (nomrad) {
            case "ADI":
                this.variadi = Boolean.FALSE;
                this.varieli = Boolean.TRUE;
                this.varimodi = Boolean.TRUE;
                this.varisegui = Boolean.FALSE;
                this.varihisto = Boolean.TRUE;
                this.limpiar_datosoposiciondmtedmdanotisoliapo();
                break;
            case "MODI":
                this.variadi = Boolean.TRUE;
                this.varieli = Boolean.FALSE;
                this.varimodi = Boolean.FALSE;
                this.varisegui = Boolean.FALSE;
                this.varihisto = Boolean.FALSE;
                break;

            case "CON":
                this.variadi = Boolean.TRUE;
                this.varieli = Boolean.TRUE;
                this.varimodi = Boolean.TRUE;
                this.varisegui = Boolean.FALSE;
                this.varihisto = Boolean.FALSE;
                break;
            default:
                this.variadi = Boolean.TRUE;
                this.varieli = Boolean.TRUE;
                this.varimodi = Boolean.TRUE;
                this.varisegui = Boolean.TRUE;
                this.varihisto = Boolean.TRUE;
                ;
        }

    }

    /**
     * Metodo para obtener numero de oposicion
     *
     * Luis Angel Quispe Limachi
     *
     * Fecha :12/10/2016
     *
     * @param nroopo
     * @return Integer
     * @throws java.lang.Exception
     */
    public Integer obtenerregxnroopo(Long nroopo) throws Exception {
        if (nroopo != null) {
            Integer nroreg = oposicionService.obtregistroxnumeroopo(nroopo);
            return nroreg;
        } else {
            return 0;
        }
    }

    /**
     * Metodo para obtener la serie de oposicion Angel Quispe limachi Fecha
     * 12/10/2016
     *
     * @param nroopo
     *
     * @return String
     * @throws java.lang.Exception
     */
    public String obtenerseriexnroopo(Long nroopo) throws Exception {
        if (nroopo != null) {
            String nroreg = oposicionService.obtrseriexnumeroopo(nroopo);
            return nroreg;
        } else {
            return "";
        }
    }

    /**
     * Metodo para obtener expediente de oposicion Angel Quispe limachi Fecha
     * 12/10/2016
     *
     * @param nroopo
     *
     * @return Long
     *
     * @throws java.lang.Exception
     */
    public Long obtenerexpedientexnroopo(Long nroopo) throws Exception {
        if (nroopo != null) {
            Long nroreg = oposicionService.obtexpedientexnroopo(nroopo);
            return nroreg;
        } else {
            return 0L;
        }
    }

    /**
     * Metodo para obtener nombre de la marca Angel Quispe limachi Fecha
     * 12/10/2016
     *
     * @param nroopo
     * @return String
     * @throws java.lang.Exception
     */
    public String obtenermarcaxnroopo(Long nroopo) throws Exception {
        if (cmbtipodmdodmte.equals("DMDO")) {
            String nroreg = oposicionService.obtenernombremarcaxidopo2(nroopo);
            return nroreg;
        }
        if (cmbtipodmdodmte.equals("DMTE")) {
            String nroreg = oposicionService.obtenernombremarcaxidopo(nroopo);
            return nroreg;
        }

        return "";
    }

    /**
     * Metodo para obtener la firma de oposicion Angel Quispe limachi Fecha
     * 12/10/2016
     *
     * @param nroopo
     *
     * @return String
     * @throws java.lang.Exception
     */
    public String obtenerfirmaxnroopo(Long nroopo) throws Exception {
        if (cmbtipodmdodmte.equals("DMDO")) {
            String nroreg = oposicionService.obtapoderadodmdoxnroopo(nroopo);
            return nroreg;
        }
        if (cmbtipodmdodmte.equals("DMTE")) {
            String nroreg = oposicionService.obtapoderadodmtexnroopo(nroopo);
            return nroreg;
        }

        return "";
    }

    /**
     * Metodo para obtener numero de oposicion Angel Quispe limachi Fecha
     *
     * Fecha: 12/10/2016
     *
     * @param nroestado
     *
     * @return String
     * @throws java.lang.Exception
     */
    public String obtestadoxnroopo(Long nroestado) throws Exception {
        if (nroestado != null) {
            String nroreg = oposicionService.obtestadoxnroopo(nroestado);
            return nroreg;
        } else {
            return "";
        }
    }

    public void viewNoti() throws Exception {

        List<Notificacion> listNotiDatosOpo = notificacionService.listaNotificacionDatosOpo(txtnropublidmdo, Long.valueOf(txtorden_o));
        Notificacion notifica = new Notificacion();
        notifica.setTipo_tramite_notificacion("OPOSICION SIG");
        notifica.setExpediente(txtnropublidmdo.toString());
        notifica.setExtension(txtorden_o.toString());
        notifica.setDemandante(listNotiDatosOpo.get(0).getDemandante());
        notifica.setDemandante_solic(listNotiDatosOpo.get(0).getDemandante_solic());
        notifica.setDemandante_apod(listNotiDatosOpo.get(0).getDemandante_apod());
        notifica.setDemandante_fojas(listNotiDatosOpo.get(0).getDemandante_fojas());
        notifica.setDemandante_con(listNotiDatosOpo.get(0).getDemandante_con());
        notifica.setDemandante_cel(listNotiDatosOpo.get(0).getDemandante_cel());
        notifica.setDemandante_direc(listNotiDatosOpo.get(0).getDemandante_direc());

        notifica.setDemandado(listNotiDatosOpo.get(0).getDemandado());
        notifica.setDemandado_solic(listNotiDatosOpo.get(0).getDemandado_solic());
        notifica.setDemandado_apod(listNotiDatosOpo.get(0).getDemandado_apod());
        notifica.setDemandado_fojas(listNotiDatosOpo.get(0).getDemandado_fojas());
        notifica.setDemandado_con(listNotiDatosOpo.get(0).getDemandado_con());
        notifica.setDemandado_cel(listNotiDatosOpo.get(0).getDemandado_cel());
        notifica.setDemandado_direc(listNotiDatosOpo.get(0).getDemandado_direc());

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
        params.add("oposicion");//Para otros tendría que ser modificacion u oposicion o renovación
        var.put("datosGenerales", params);
        RequestContext.getCurrentInstance().openDialog("/notificacion/notiPeticionDialog", options, var);

    }

    /**
     * Metodo para cargar dialogo del Solicitante del demandante
     *
     * Autor: Luis Angel Quispe limachi
     *
     * Fecha 12/10/2016
     *
     */
    public void cargaDialogoSolidmte() {

        Map<String, Object> options = new HashMap<String, Object>();

        options.put("closable", false);
        options.put("resizable", false);
        options.put("height", 510);
        options.put("width", 780);
        options.put("contentWidth", "100%");
        options.put("contentHeight", "100%");
        options.put("modal", true);
        options.put("closable", false);
        banderadmtedmdo = "DMTE";
        RequestContext.getCurrentInstance().openDialog("agregarSolicitanteApoderadoOpo", options, null);

    }

    /**
     * Metodo para cargar dialogo del Solicitante del demandado
     *
     * Autor: Luis Angel Quispe limachi
     *
     * Fecha 12/10/2016
     *
     */
    public void cargaDialogoSolidmdo() {

        Map<String, Object> options = new HashMap<String, Object>();

        options.put("resizable", false);
        options.put("height", 510);
        options.put("width", 780);
        options.put("contentWidth", "100%");
        options.put("contentHeight", "100%");
        options.put("modal", true);
        options.put("closable", false);
        banderadmtedmdo = "DMDO";
        RequestContext.getCurrentInstance().openDialog("agregarSolicitanteApoderadoOpo", options, null);

    }

    /**
     * Metodo para cargar dialogo del Apoderado del demandante
     *
     * Autor: Luis Angel Quispe limachi
     *
     * Fecha 12/10/2016
     *
     */
    public void cargaDialogoApodmte() {

        Map<String, List<String>> params = new HashMap<String, List<String>>();
        List<String> list = new ArrayList<String>();
        list.add("APOD");
        params.put("id", list);

        Map<String, Object> options = new HashMap<String, Object>();
        options.put("resizable", false);
        options.put("height", 510);
        options.put("width", 780);
        options.put("contentWidth", "100%");
        options.put("contentHeight", "100%");
        options.put("modal", true);
        options.put("closable", false);
        banderadmtedmdo = "DMTE";
        RequestContext.getCurrentInstance().openDialog("agregarSolicitanteApoderadoOpo", options, params);
    }

    /**
     * Metodo para cargar dialogo del Apoderado del demandado Autor: Luis Angel
     * Quispe limachi
     *
     * Fecha 12/10/2016
     *
     */
    public void cargaDialogoApodmdo() {

        Map<String, List<String>> params = new HashMap<String, List<String>>();
        List<String> list = new ArrayList<String>();
        list.add("APOD");
        params.put("id", list);

        Map<String, Object> options = new HashMap<String, Object>();
        options.put("resizable", false);
        options.put("height", 510);
        options.put("width", 780);
        options.put("contentWidth", "100%");
        options.put("contentHeight", "100%");
        options.put("modal", true);
        options.put("closable", false);
        banderadmtedmdo = "DMDO";
        RequestContext.getCurrentInstance().openDialog("agregarSolicitanteApoderadoOpo", options, params);
    }

    /**
     * Metodo para cargar terminar dialogo
     *
     * Autor: Luis Angel Quispe limachi
     *
     * Fecha 12/10/2016
     *
     * @param event
     */
    public void terminaDialogo(SelectEvent event) {
        OpoSolicitanteapoderado solicitanteApoderado;

        if (event.getObject() != "Exit") {
            solicitanteApoderado = (OpoSolicitanteapoderado) event.getObject();

            if (banderadmtedmdo.equals("DMTE")) {
                listasolidmte.add(solicitanteApoderado);
            } else {
                listasolidmdo.add(solicitanteApoderado);
            }

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(solicitanteApoderado.getNombre_razonsocial()));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cerrado"));
        }
    }

    /**
     * Metodo para cargar terminar dialogo
     *
     * Autor: Luis Angel Quispe limachi
     *
     * @param event
     *
     * Fecha 12/11/2016
     *
     */
    public void terminaDialogoApoderado(SelectEvent event) {
        OpoSolicitanteapoderado solicitanteApoderado;
        if (event.getObject() != "Exit") {
            solicitanteApoderado = (OpoSolicitanteapoderado) event.getObject();
            if (banderadmtedmdo.equals("DMTE")) {
                listaapodmte.add(solicitanteApoderado);
            } else {
                listaapodmdo.add(solicitanteApoderado);
            }

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(solicitanteApoderado.getNombre_razonsocial()));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cerrado"));
        }

    }

    /**
     * Metodo para Modificar la ventana de solicitanteapoderado
     *
     * Autor: Luis Angel Quispe limachi
     *
     * Fecha 12/11/2016
     *
     * @param opoSolicitanteApoderado
     *
     * @throws java.lang.Exception
     *
     */
    public void modificarPersona(OpoSolicitanteapoderado opoSolicitanteApoderado) throws Exception {
        ObjetoAuxOposoli = opoSolicitanteApoderado;
        Map<String, Object> options = new HashMap<String, Object>();
        Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        Map<String, Object> sessionMapgo = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        sessionMap.put("persona", opoSolicitanteApoderado);
        sessionMapgo.put("clavepri", clavepriok);
        options.put("resizable", false);
        options.put("height", 510);
        options.put("width", 780);
        options.put("contentWidth", "100%");
        options.put("contentHeight", "100%");
        options.put("modal", true);
        options.put("closable", false);
        RequestContext.getCurrentInstance().openDialog("agregarSolicitanteApoderadoOpo", options, null);
        indice = opoSolicitanteApoderadoService.encuentraPosicionListadoSolicitanteApoderado(listasolidmte, opoSolicitanteApoderado);
        indice1 = opoSolicitanteApoderadoService.encuentraPosicionListadoSolicitanteApoderado(listasolidmdo, opoSolicitanteApoderado);

    }

    /**
     * Metodo para Terminar la ventana de solicitanteapoderado
     *
     * Autor: Luis Angel Quispe limachi
     *
     * Fecha 12/11/2016
     *
     * @param event
     *
     */
    public void terminaDialogoModificar(SelectEvent event) {
        OpoSolicitanteapoderado solicitanteApoderado;
        if (event.getObject() != "Exit") {
            solicitanteApoderado = (OpoSolicitanteapoderado) event.getObject();

//            listasolidmte.remove(ObjetoAuxOposoli);
//            listasolidmte.add(indice, solicitanteApoderado);
            if (solicitanteApoderado.getTiposoliapo().equals("DMDO")) {
                listasolidmdo.remove(ObjetoAuxOposoli);
                listasolidmdo.add(indice1, solicitanteApoderado);
            } else {
                listasolidmte.remove(ObjetoAuxOposoli);
                listasolidmte.add(indice, solicitanteApoderado);
            }

        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cerrado"));
        }
        //ObjetoAuxOposoli = new OpoSolicitanteapoderado();
        Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        sessionMap.remove("persona");
    }

    /**
     * Metodo para Modificar persona apoderado
     *
     * Autor: Luis Angel Quispe limachi
     *
     * Fecha 12/11/2016
     *
     * @param opoSolicitanteApoderado
     *
     * @throws java.lang.Exception
     *
     */
    public void modificarPersonaApo(OpoSolicitanteapoderado opoSolicitanteApoderado) throws Exception {
        ObjetoAuxOposoliApo = opoSolicitanteApoderado;
        Map<String, Object> options = new HashMap<String, Object>();
        Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        Map<String, Object> sessionMapgo = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        sessionMap.put("persona", opoSolicitanteApoderado);
        sessionMapgo.put("clavepri", clavepriok);
        options.put("resizable", false);
        options.put("height", 510);
        options.put("width", 780);
        options.put("contentWidth", "100%");
        options.put("contentHeight", "100%");
        options.put("modal", true);
        options.put("closable", false);
        RequestContext.getCurrentInstance().openDialog("agregarSolicitanteApoderadoOpo", options, null);
        indice2 = opoSolicitanteApoderadoService.encuentraPosicionListadoSolicitanteApoderado(listaapodmte, opoSolicitanteApoderado);
        indice3 = opoSolicitanteApoderadoService.encuentraPosicionListadoSolicitanteApoderado(listaapodmdo, opoSolicitanteApoderado);

    }

    /**
     * Metodo para Terminar persona apoderado
     *
     * Autor: Luis Angel Quispe limachi
     *
     * Fecha 12/11/2016
     *
     * @param event
     *
     */
    public void terminaDialogoModificarApo(SelectEvent event) {
        OpoSolicitanteapoderado solicitanteApoderado;
        if (event.getObject() != "Exit") {
            solicitanteApoderado = (OpoSolicitanteapoderado) event.getObject();

//            listasolidmte.remove(ObjetoAuxOposoli);
//            listasolidmte.add(indice, solicitanteApoderado);
            if (solicitanteApoderado.getTiposoliapo().equals("DMDO")) {
                listaapodmdo.remove(ObjetoAuxOposoliApo);
                listaapodmdo.add(indice3, solicitanteApoderado);
            } else {
                listaapodmte.remove(ObjetoAuxOposoliApo);
                listaapodmte.add(indice2, solicitanteApoderado);
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cerrado"));
        }
        //ObjetoAuxOposoli = new OpoSolicitanteapoderado();
        Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        sessionMap.remove("persona");
    }

    public void borrarPersona(OpoSolicitanteapoderado opoSolicitanteApoderado) throws Exception {
        opoSolicitanteApoderado.setIdmarcademandada(null);
        opoSolicitanteApoderado.setEstado("NA");
        opoSolicitanteApoderadoService.modificarOposolicitanteapoderado(opoSolicitanteApoderado);
        listasolidmte.remove(opoSolicitanteApoderado);
    }

    public void borrarApoderadodmte(OpoSolicitanteapoderado opoSolicitanteApoderado) throws Exception {
        opoSolicitanteApoderado.setIdmarcademandada(null);
        opoSolicitanteApoderado.setEstado("NA");
        opoSolicitanteApoderadoService.modificarOposolicitanteapoderado(opoSolicitanteApoderado);
        listaapodmte.remove(opoSolicitanteApoderado);
    }

    public void borrarApoderadodmdo(OpoSolicitanteapoderado opoSolicitanteApoderado) throws Exception {
        opoSolicitanteApoderado.setIdmarcademandante(null);
        opoSolicitanteApoderado.setEstado("NA");
        opoSolicitanteApoderadoService.modificarOposolicitanteapoderado(opoSolicitanteApoderado);
        listaapodmdo.remove(opoSolicitanteApoderado);
    }

    public void borrarPersonadmda(OpoSolicitanteapoderado opoSolicitanteApoderado) throws Exception {
        opoSolicitanteApoderado.setIdmarcademandante(null);
        opoSolicitanteApoderado.setEstado("NA");
        opoSolicitanteApoderadoService.modificarOposolicitanteapoderado(opoSolicitanteApoderado);
        listasolidmdo.remove(opoSolicitanteApoderado);
    }

    public String llevaalpricipal() {
        return "abrirExpediente";
    }

    public void save() {
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("Welcome " + firstname + " " + lastname));
    }

    public void muetramensa(ActionEvent actionEvent) {
        addMessage("Este es un Texto Plano!!!!!!!!!!!!!");
    }

    public void muestramen(ActionEvent actionEvent) {
        addMessage("Welcome to Primefaces!!");
    }

    public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void accionboton() {
        this.cadena = "nuevoValor";
    }

    public void activaCambiosLista() {

        this.encabezado = Boolean.TRUE;
    }

    /**
     * Metodo que busca el numero de publicacion
     *
     * Autor: Luis Angel Quispe limachi
     *
     * Fecha 12/11/2016
     *
     * @throws java.lang.Exception
     */
    public void buscadorPorNumeroPublicacion() throws Exception {
        int swx = 0;

        if (!oposicionService.obtenerListadoOposicion(txtnropublidmdo).isEmpty()) {
            listasolidmdo.clear();
            listaapodmdo.clear();
            listasolidmte.clear();
            listaapodmte.clear();
            listascasosadj = oposicionService.obtenerListadoOposicion(txtnropublidmdo);
            txtorden_o = listascasosadj.get(0).getOrden_o();
            listademandantevista = opoMarcaDemandanteService.obtenerListadoOpomarcademandantexnroopo(txtnropublidmdo);

            clavepriok = oposicionService.encuentraclaveprin(txtnropublidmdo, txtorden_o);
            cargarDatosEnLaVista(listademandantevista.get(0));

            muestraresolucionrecurso();
            cldfechapresoposicion = oposicionService.encuentrafechapres(clavepriok);
            listaactividades = opoEventoService.obtenerListadoeventoxidoposicion(oposicionService.encuentraclaveprin(txtnropublidmdo, txtorden_o));
            //para cargar datos del numero de orden

            this.txtpublidmdoclon = oposicionService.extraerultimonorden(txtpublidmdo) + 1;

            //Habilita el boton de envio a signos
            btnInicioTramiteRendered = verificarEstado(txtnropublidmdo,txtorden_o);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Se Cargo el Tramite", ""));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "No se encontraron tramies con ese numero de Publicacion", ""));

        }
    }

    /**
     * Metodo que busca el numero de publicacion
     *
     * Autor: Luis Angel Quispe limachi
     *
     * Fecha 12/11/2016
     *
     * @throws java.lang.Exception
     */
    public Boolean verificarEstado(Long nropubDmdo,Integer nropop) throws Exception {
        Boolean veridmdo = false;

        if (oposicionService.verificarexi2(nropubDmdo,nropop)) {
            veridmdo = true;
        } 
        return veridmdo;
    }

    /**
     * Metodo que carga todos los datos en la vista en la partedemandada y
     * demandante
     *
     * Autor: Luis Angel Quispe limachi
     *
     * Fecha 12/11/2016
     *
     * @param mardmte
     *
     */
    public void cargarDatosEnLaVista(OpoMarcademandante mardmte) throws Exception {

        marcadadavist = opoMarcaDemandadaService.obtenerOpomarcademandadaobj(mardmte.getIdoposicion());

        txtpublidmdo = marcadadavist.getNro_opo().longValue();

        txtgacetadmdo = marcadadavist.getGaceta();
        cldfecahpubdmdo = marcadadavist.getFecha_public();
        txamarcadmdo = marcadadavist.getDmdo_marca_lnv();
        if (!opoSolicitanteApoderadoService.obtenerListadoSoliDmda(marcadadavist.getIdmarcademandada()).isEmpty()) {
            listasolidmdo = opoSolicitanteApoderadoService.obtenerListadoSoliDmda(marcadadavist.getIdmarcademandada());
        }
        if (!opoSolicitanteApoderadoService.obtenerListadoApodDmda(marcadadavist.getIdmarcademandada()).isEmpty()) {
            listaapodmdo = opoSolicitanteApoderadoService.obtenerListadoApodDmda(marcadadavist.getIdmarcademandada());
        }
        notificadmdavist = opoNotificacionService.obtenerNotificacionxmarcadmda(marcadadavist.getIdmarcademandada());
        txtciudaddmdo = notificadmdavist.getCiudad_notificacion();
        txtzonadmdo = notificadmdavist.getZona_barrio();
        txtcalledmdo = notificadmdavist.getAvenida_calle();
        txtnrocasadmdo = notificadmdavist.getNumero();
        txtcelulardmdo = notificadmdavist.getCelular();
        txtedificiodmdo = notificadmdavist.getEdificio();
        txtpisodmdo = notificadmdavist.getPiso();
        txtnrodepartamentodmdo = notificadmdavist.getNumero_departamento();
        txttelefonodmdo = notificadmdavist.getTelefono();
        txtemaildmdo = notificadmdavist.getEmail();
        txtreferenciadmdo = notificadmdavist.getReferencia_direccion();

        marcadmtevist = opoMarcaDemandanteService.obtieneelobjetodmtexidoposicion(mardmte.getIdoposicion());
        txtpubldmte = marcadmtevist.getDmte_public();

        txtregistrodmte = marcadmtevist.getDmte_reg();
        txtexpedientesm = marcadmtevist.getDmte_sm();

        if (txtexpedientesm != null) {
            String cadenasm = Long.toString(txtexpedientesm);
            if (cadenasm.length() > 1) {
                if (cadenasm.length() == 6) {
                    String cadeges = cadenasm.substring(0, 4);
                    Long lnges = Long.parseLong(cadeges);
                    txtexpedientesm = 0L;
                    txtgestionexpediente = lnges;
                    txtextencionespediente = "00";

                } else {
                    String varext = "00";
                    String cadexpedi = cadenasm.substring(4, 10);
                    String cadeges = cadenasm.substring(0, 4);
                    String cadext = cadenasm.substring(10, 12);
                    Long lnexpediente = Long.parseLong(cadexpedi);
                    Long lnges = Long.parseLong(cadeges);
                    txtexpedientesm = lnexpediente;
                    txtgestionexpediente = lnges;
                    if (cadext.equals("10")) {
                        varext = "A";
                    } else {
                        if (cadext.equals("11")) {
                            varext = "B";

                        } else {
                            if (cadext.equals("12")) {
                                varext = "C";
                            } else {
                                varext = cadext;
                            }
                        }
                    }
                    txtextencionespediente = varext;
                }
            } else {
                txtexpedientesm = null;
                txtgestionexpediente = null;
                txtextencionespediente = null;
            }
        } else {
            txtexpedientesm = null;
            txtgestionexpediente = null;
            txtextencionespediente = null;
        }
        txamarcadmte = marcadmtevist.getDmte_marca_lnv();
        txtusodmte = marcadmtevist.getDmte_uso();
        cmbregistro = marcadmtevist.getDmte_serie();
        if (!opoSolicitanteApoderadoService.obtenerListadoSoliDmte(marcadmtevist.getIdmarcademandante()).isEmpty()) {
            listasolidmte = opoSolicitanteApoderadoService.obtenerListadoSoliDmte(marcadmtevist.getIdmarcademandante());
        }

        if (!opoSolicitanteApoderadoService.obtenerListadoApoDmte(marcadmtevist.getIdmarcademandante()).isEmpty()) {
            listaapodmte = opoSolicitanteApoderadoService.obtenerListadoApoDmte(marcadmtevist.getIdmarcademandante());
        }
        notificadmtevist = opoNotificacionService.obtenerNotificacionxmarcadmte(marcadmtevist.getIdmarcademandante());
        txtciudaddmte = notificadmtevist.getCiudad_notificacion();
        txtzonadmte = notificadmtevist.getZona_barrio();
        txtcalledmte = notificadmtevist.getAvenida_calle();
        txtnrocasadmte = notificadmtevist.getNumero();
        txtceludmte = notificadmtevist.getCelular();
        txtedificiodmte = notificadmtevist.getEdificio();
        txtpisodmte = notificadmtevist.getPiso();
        txtnrodepadmte = notificadmtevist.getNumero_departamento();
        txttelefonodmte = notificadmtevist.getTelefono();
        txtemaildmte = notificadmtevist.getEmail();
        txtreferenciadmte = notificadmtevist.getReferencia_direccion();

        if (opoEventoService.obtenereventoxidpublica(oposicionService.encuentraclaveprin(txtpublidmdo, 1)) != null) {
            eventodemarca = opoEventoService.obtenereventoxidpublica(oposicionService.encuentraclaveprin(txtpublidmdo, 1));

        }

        if (eventodemarca.getIdevento() != null) {
            txtestadotramite = opoEstadoService.devuelvenombredelestadoxidestado(opoActividadService.muestraidestadoxidactividad(eventodemarca.getIdactividad()));
            txtultimactividad = opoActividadService.muestranomdelaactividadxidactividad(eventodemarca.getIdactividad());
            cldfechaultimaactividad = eventodemarca.getFecha_operacion();
            txaobservacion = eventodemarca.getObservacion();

            if (eventodemarca.getObservacion() == null) {
                txaobservacion = oposicionService.obtoposicionxnroopo(mardmte.getIdoposicion()).getObservacion();
            }
            if (eventodemarca.getObservacion() != null && txaobservacion.equals("")) {
                txaobservacion = oposicionService.obtoposicionxnroopo(mardmte.getIdoposicion()).getObservacion();
            }
        } else {
            txtestadotramite = "DESCONOCIDO";
            txtultimactividad = "Desconocida";
            txaobservacion = oposicionService.obtoposicionxnroopo(mardmte.getIdoposicion()).getObservacion();

        }

    }

    /**
     * Metodo que carga todos cuando seleccionas otro caso adjunto telleva a su
     * Vista
     *
     * Autor: Luis Angel Quispe limachi
     *
     * Fecha 12/11/2016
     *
     * @param inidopodmtei
     *
     * @return marcdmte
     */
    public String comvertirnroopo(Long inidopodmtei) throws Exception {
        if (inidopodmtei != 0) {
            String marcdmte = oposicionService.obtenernombremarcaxidopo(inidopodmtei) + "  Vs.  " + oposicionService.obtenernombremarcaxidopo2(inidopodmtei);

            return marcdmte;
        } else {
            return "";
        }
    }

    /**
     * Metodo que carga todos cuando seleccionas otro caso adjunto telleva a su
     * Vista
     *
     * Autor: Luis Angel Quispe limachi
     *
     * Fecha 12/11/2016
     *
     * @param event
     *
     * @throws java.lang.Exception
     *
     */
    public void onRowSelectOposicion(SelectEvent event) throws Exception {
        //System.out.println("========xxxxxxxxxxxxxxxxxx========>" + event.getObject());
        listasolidmdo.clear();
        listaapodmdo.clear();
        listasolidmte.clear();
        listaapodmte.clear();
        Long opos1 = ((Oposicion) event.getObject()).getIdoposicion();
        // System.out.println("========****************========>" + opos1);
        int swx = 0;
        marcadmtevist = opoMarcaDemandanteService.obtieneelobjetodmtexidoposicion(opos1);
        txtorden_o = oposicionService.extranroorden(opos1);
        this.txt_auxorden = txtorden_o;
        //  System.out.println("==================>" + txt_auxorden);
        
      
        
        
        clavepriok = oposicionService.encuentraclaveprin(txtnropublidmdo, txtorden_o);
          btnInicioTramiteRendered = verificarEstado(txtnropublidmdo,txtorden_o);
        marcadadavist = opoMarcaDemandadaService.obtenerOpomarcademandadaobj(opos1);
        cldfechapresoposicion = oposicionService.encuentrafechapres(opos1);
        txtpublidmdo = marcadadavist.getNro_opo().longValue();
        txtgacetadmdo = marcadadavist.getGaceta();
        cldfecahpubdmdo = marcadadavist.getFecha_public();
        txamarcadmdo = marcadadavist.getDmdo_marca_lnv();
        if (!opoSolicitanteApoderadoService.obtenerListadoSoliDmda(marcadadavist.getIdmarcademandada()).isEmpty()) {
            listasolidmdo = opoSolicitanteApoderadoService.obtenerListadoSoliDmda(marcadadavist.getIdmarcademandada());
        }
        if (!opoSolicitanteApoderadoService.obtenerListadoApodDmda(marcadadavist.getIdmarcademandada()).isEmpty()) {
            listaapodmdo = opoSolicitanteApoderadoService.obtenerListadoApodDmda(marcadadavist.getIdmarcademandada());
        }
        notificadmdavist = opoNotificacionService.obtenerNotificacionxmarcadmda(marcadadavist.getIdmarcademandada());
        txtciudaddmdo = notificadmdavist.getCiudad_notificacion();
        txtzonadmdo = notificadmdavist.getZona_barrio();
        txtcalledmdo = notificadmdavist.getAvenida_calle();
        txtnrocasadmdo = notificadmdavist.getNumero();
        txtcelulardmdo = notificadmdavist.getCelular();
        txtedificiodmdo = notificadmdavist.getEdificio();
        txtpisodmdo = notificadmdavist.getPiso();
        txtnrodepartamentodmdo = notificadmdavist.getNumero_departamento();
        txttelefonodmdo = notificadmdavist.getTelefono();
        txtemaildmdo = notificadmdavist.getEmail();
        txtreferenciadmdo = notificadmdavist.getReferencia_direccion();

        txtpubldmte = marcadmtevist.getDmte_public();
        txtregistrodmte = marcadmtevist.getDmte_reg();
        txtexpedientesm = marcadmtevist.getDmte_sm();
        if (txtexpedientesm != null) {
            String cadenasm = Long.toString(txtexpedientesm);
            if (cadenasm.length() > 1) {
                if (cadenasm.length() == 6) {
                    String cadeges = cadenasm.substring(0, 4);
                    Long lnges = Long.parseLong(cadeges);
                    txtexpedientesm = 0L;
                    txtgestionexpediente = lnges;
                    txtextencionespediente = "00";

                } else {
                    String cadexpedi = cadenasm.substring(4, 10);
                    String cadeges = cadenasm.substring(0, 4);
                    String cadext = cadenasm.substring(10, 12);
                    Long lnexpediente = Long.parseLong(cadexpedi);
                    Long lnges = Long.parseLong(cadeges);
                    txtexpedientesm = lnexpediente;
                    txtgestionexpediente = lnges;
                    txtextencionespediente = cadext;
                }
            } else {
                txtexpedientesm = null;
                txtgestionexpediente = null;
                txtextencionespediente = null;
            }
        } else {
            txtexpedientesm = null;
            txtgestionexpediente = null;
            txtextencionespediente = null;
        }
        txtpubldmte = marcadmtevist.getDmte_public();
        txamarcadmte = marcadmtevist.getDmte_marca_lnv();
        txtusodmte = marcadmtevist.getDmte_uso();
        cmbregistro = marcadmtevist.getDmte_serie();
        if (!opoSolicitanteApoderadoService.obtenerListadoSoliDmte(marcadmtevist.getIdmarcademandante()).isEmpty()) {
            listasolidmte = opoSolicitanteApoderadoService.obtenerListadoSoliDmte(marcadmtevist.getIdmarcademandante());
        }
        if (!opoSolicitanteApoderadoService.obtenerListadoApoDmte(marcadmtevist.getIdmarcademandante()).isEmpty()) {
            listaapodmte = opoSolicitanteApoderadoService.obtenerListadoApoDmte(marcadmtevist.getIdmarcademandante());
        }
        notificadmtevist = opoNotificacionService.obtenerNotificacionxmarcadmte(marcadmtevist.getIdmarcademandante());
        txtciudaddmte = notificadmtevist.getCiudad_notificacion();
        txtzonadmte = notificadmtevist.getZona_barrio();
        txtcalledmte = notificadmtevist.getAvenida_calle();
        txtnrocasadmte = notificadmtevist.getNumero();
        txtceludmte = notificadmtevist.getCelular();
        txtedificiodmte = notificadmtevist.getEdificio();
        txtpisodmte = notificadmtevist.getPiso();
        txtnrodepadmte = notificadmtevist.getNumero_departamento();
        txttelefonodmte = notificadmtevist.getTelefono();
        txtemaildmte = notificadmtevist.getEmail();
        txtreferenciadmte = notificadmtevist.getReferencia_direccion();

        eventodemarca = opoEventoService.obtenereventoxidpublica(opos1);

        if (eventodemarca.getIdevento() != null) {
            txtestadotramite = opoEstadoService.devuelvenombredelestadoxidestado(opoActividadService.muestraidestadoxidactividad(eventodemarca.getIdactividad()));
            txtultimactividad = opoActividadService.muestranomdelaactividadxidactividad(eventodemarca.getIdactividad());
            cldfechaultimaactividad = eventodemarca.getFecha_operacion();
            txaobservacion = eventodemarca.getObservacion();
            if (eventodemarca.getObservacion() == null) {
                txaobservacion = oposicionService.obtoposicionxnroopo(opos1).getObservacion();
            }
            if (eventodemarca.getObservacion() != null && txaobservacion.equals("")) {
                txaobservacion = oposicionService.obtoposicionxnroopo(opos1).getObservacion();
            }
        } else {
            txtestadotramite = "DESCONOCIDO";
            txtultimactividad = "Desconocida";
            txaobservacion = oposicionService.obtoposicionxnroopo(opos1).getObservacion();
        }

        listaactividades = opoEventoService.obtenerListadoeventoxidoposicion(opos1);

        if (seguimientoService.verificaseguimientosm(txtexpedientesm) != null) {
            //   swx = 1;
            listastaseg = seguimientoService.verificaseguimientosm(txtexpedientesm);
            for (Seguimiento lisa : listastaseg) {
                if (lisa.getEtapa().equals("OPO") && lisa.getFechaFin() == null) {
                    swx = 1;
                }
            }
        }
        if (txtpubldmte != null) {
            if (seguimientoService.verificaseguimientopub(txtpubldmte.longValue()) != null && swx != 1) {
                listastaseg = seguimientoService.verificaseguimientopub(txtpubldmte.longValue());
                for (Seguimiento lisa : listastaseg) {

                    if (lisa.getEtapa().equals("OPO") && lisa.getFechaFin() == null) {
                        swx = 1;
                    }
                }
                //swx = 1;
            }
        }
        //System.out.println("*-------------------------------------------->" + txtregistrodmte);
        if (txtregistrodmte != null) {
            if (txtregistrodmte != 0) {
                if (seguimientoService.verificaseguimientoreg(txtregistrodmte.longValue(), cmbregistro) != null && swx != 1) {
                    listastaseg = seguimientoService.verificaseguimientoreg(txtregistrodmte.longValue(), cmbregistro);
                    for (Seguimiento lisa : listastaseg) {

                        if (lisa.getEtapa().equals("OPO") && lisa.getFechaFin() == null) {
                            swx = 1;
                        }
                    }
                    //  swx = 1;
                }
            }
        }

        if (swx == 1) {
            btnSeguimientoOpoRendered = false;
        } else {
            btnSeguimientoOpoRendered = true;
        }
        swx = 0;
        muestraresolucionrecurso();

    }

    public void onRowUnselectOposicion(UnselectEvent event) {
//        FacesMessage msg = new FacesMessage("Car Unselected", ((Oposicion) event.getObject()).getIdoposicion()+"");
//        FacesContext.getCurrentInstance().addMessage(null, msg);

    }

    /**
     * Metodo que modifica los cuadros de tex
     *
     * Autor: Luis Angel Quispe limachi
     *
     * Fecha 12/11/2016
     *
     * @throws java.lang.Exception
     */
    public void modificar_datosoposiciondmtedmdanotisoliapo() throws Exception {

        try {

            Long numeropub = oposicionService.encuentraclaveprin(txtnropublidmdo, txtorden_o);
//Linea para el historial todo el tramite           
            datoshistorialaux = oposicionService.obtoposicionxnroopo(numeropub);

            datosoposicionmodi.setIdoposicion(numeropub);
            datosoposicionmodi.setNro_opo(txtpublidmdo);
            datosoposicionmodi.setFecha_presentacion(cldfechapresoposicion);
            datosoposicionmodi.setObservacion(txaobservacion);
            datosoposicionmodi.setOrden_o(txtorden_o);
            datosoposicionmodi.setId_estado(idUsuarioSesion);
            datosoposicionmodi.setEstadoopo("AC");
//Linea para el historial todo el tramite
            if (!datoshistorialaux.getObservacion().equals(txaobservacion)) {
                datoshistorialoposicion.setIdoposicion(numeropub);
                datoshistorialoposicion.setIdlogtrans(1L);
                datoshistorialoposicion.setEstado(datoshistorialaux.getEstado());
                datoshistorialoposicion.setOperacion("MOD-OBS");
                datoshistorialoposicion.setFecha_operacion(comunService.obtenerFechaHoraServidor(1L));
                datoshistorialoposicion.setId_usuario(idUsuarioSesion);
                opoHistorialService.guardaOpoHistorial(datoshistorialoposicion);
            }
            oposicionService.modificaOposicion(datosoposicionmodi);
//Linea para el historial todo el tramite
            datoshistorialauxmarcademandada = opoMarcaDemandadaService.obtenerOpomarcademandadaobj(numeropub);

            datosmarcademandadamodi.setIdmarcademandada(opoMarcaDemandadaService.obtenerOpomarcademandadaXidopo(numeropub));
            datosmarcademandadamodi.setIdoposicion(numeropub);
            datosmarcademandadamodi.setNro_opo(Integer.valueOf(txtpublidmdo + ""));
            datosmarcademandadamodi.setDmdo_public(Integer.valueOf(txtpublidmdo + ""));
            datosmarcademandadamodi.setGaceta(txtgacetadmdo);
            datosmarcademandadamodi.setFecha_public(cldfecahpubdmdo);
            datosmarcademandadamodi.setDmdo_marca_lnv(txamarcadmdo);
            datosmarcademandadamodi.setEstado("AC");
//Linea para el historial todo el tramite            
            if (!datoshistorialauxmarcademandada.getDmdo_marca_lnv().equals(txamarcadmdo)) {
                datoshistorialoposicion.setIdoposicion(numeropub);
                datoshistorialoposicion.setIdlogtrans(1L);
                datoshistorialoposicion.setEstado(datoshistorialaux.getEstado());
                datoshistorialoposicion.setOperacion("MOD-MARCADMDA");
                datoshistorialoposicion.setFecha_operacion(comunService.obtenerFechaHoraServidor(1L));
                datoshistorialoposicion.setId_usuario(idUsuarioSesion);
                opoHistorialService.guardaOpoHistorial(datoshistorialoposicion);

            }
            opoMarcaDemandadaService.modificarOpomarcademandada(datosmarcademandadamodi);
//Linea para el historial todo el tramite            
            datoshistorialauxmarcademandante = opoMarcaDemandanteService.obtieneelobjetodmtexidoposicion(numeropub);

            datosmarcademandantemodi.setIdmarcademandante(opoMarcaDemandanteService.obtenerOpomarcademandnteXidopo(numeropub));
            datosmarcademandantemodi.setIdoposicion(numeropub);
            if (txtpubldmte != null) {
                datosmarcademandantemodi.setDmte_public(Integer.valueOf(txtpubldmte + ""));
            }

            datosmarcademandantemodi.setDmte_reg(txtregistrodmte);

            if (txtexpedientesm != null && txtgestionexpediente != null) {

                int canticarct = (Long.toString(txtexpedientesm)).length();
                String cadesm = "";

                switch (canticarct) {

                    case 1:
                        cadesm = "00000" + Long.toString(txtexpedientesm);
                        break;
                    case 2:
                        cadesm = "0000" + Long.toString(txtexpedientesm);
                        break;
                    case 3:
                        cadesm = "000" + Long.toString(txtexpedientesm);
                        break;
                    case 4:
                        cadesm = "00" + Long.toString(txtexpedientesm);
                        break;
                    case 5:
                        cadesm = "0" + Long.toString(txtexpedientesm);
                        break;
                    default:
                        System.out.println("este sm tiene una cantidad de digitos raro");
                        ;
                        break;

                }

                String numsm = Long.toString(txtgestionexpediente) + cadesm + txtextencionespediente;

                datosmarcademandantemodi.setDmte_sm(Long.parseLong(numsm));
            } else {
                datosmarcademandantemodi.setDmte_sm(null);
            }

            datosmarcademandantemodi.setDmte_marca_lnv(txamarcadmte);
            datosmarcademandantemodi.setDmte_uso(txtusodmte);
            datosmarcademandantemodi.setEstado("AC");
            //Linea para el historial todo el tramite    
            if (datoshistorialauxmarcademandante.getDmte_marca_lnv() != null) {
                if (!datoshistorialauxmarcademandante.getDmte_marca_lnv().equals(txamarcadmte)) {
                    datoshistorialoposicion.setIdoposicion(numeropub);
                    datoshistorialoposicion.setIdlogtrans(1L);
                    datoshistorialoposicion.setEstado(datoshistorialaux.getEstado());
                    datoshistorialoposicion.setOperacion("MOD-MARCADMTE");
                    datoshistorialoposicion.setFecha_operacion(comunService.obtenerFechaHoraServidor(1L));
                    datoshistorialoposicion.setId_usuario(idUsuarioSesion);
                    opoHistorialService.guardaOpoHistorial(datoshistorialoposicion);
                }
            }
            opoMarcaDemandanteService.modificarOpomarcademandnte(datosmarcademandantemodi);

            datosnotificaciondmdomodi.setIdnotificacion(opoNotificacionService.obtenreidnotificacionxmarcadmda(opoMarcaDemandadaService.obtenerOpomarcademandadaXidopo(numeropub)));
            datosnotificaciondmdomodi.setIdmarcademandada(opoMarcaDemandadaService.obtenerOpomarcademandadaXidopo(numeropub));
            datosnotificaciondmdomodi.setCiudad_notificacion(txtciudaddmdo);
            datosnotificaciondmdomodi.setZona_barrio(txtzonadmdo);
            datosnotificaciondmdomodi.setAvenida_calle(txtcalledmdo);
            datosnotificaciondmdomodi.setEdificio(txtedificiodmdo);
            datosnotificaciondmdomodi.setPiso(txtpisodmdo);
            datosnotificaciondmdomodi.setNumero_departamento(txtnrodepartamentodmdo);
            datosnotificaciondmdomodi.setNumero(txtnrocasadmdo);
            datosnotificaciondmdomodi.setReferencia_direccion(txtreferenciadmdo);
            datosnotificaciondmdomodi.setEmail(txtemaildmdo);
            datosnotificaciondmdomodi.setTelefono(txttelefonodmdo);
            datosnotificaciondmdomodi.setCelular(txtcelulardmdo);
            datosnotificaciondmdomodi.setEstado("AC");
            opoNotificacionService.modificarOponotificacionmodi(datosnotificaciondmdomodi);

            datosnotificaciondmtemodi.setIdnotificacion(opoNotificacionService.obtenreidnotificacionxmarcadmte(opoMarcaDemandanteService.obtenerOpomarcademandnteXidopo(numeropub)));
            datosnotificaciondmtemodi.setIdmarcademandante(opoMarcaDemandanteService.obtenerOpomarcademandnteXidopo(numeropub));
            datosnotificaciondmtemodi.setCiudad_notificacion(txtciudaddmte);
            datosnotificaciondmtemodi.setZona_barrio(txtzonadmte);
            datosnotificaciondmtemodi.setAvenida_calle(txtcalledmte);
            datosnotificaciondmtemodi.setEdificio(txtedificiodmte);
            datosnotificaciondmtemodi.setPiso(txtpisodmte);
            datosnotificaciondmtemodi.setNumero_departamento(txtnrodepadmte);
            datosnotificaciondmtemodi.setNumero(txtnrocasadmte);
            datosnotificaciondmtemodi.setReferencia_direccion(txtreferenciadmte);
            datosnotificaciondmtemodi.setEmail(txtedificiodmte);
            datosnotificaciondmtemodi.setTelefono(txttelefonodmte);
            datosnotificaciondmtemodi.setCelular(txtceludmte);
            datosnotificaciondmtemodi.setEstado("AC");
            opoNotificacionService.modificarOponotificacionmodi(datosnotificaciondmtemodi);

//           
            for (OpoSolicitanteapoderado oposolidmdo : listasolidmdo) {
                oposolidmdo.setIdmarcademandada(opoMarcaDemandadaService.obtenerOpomarcademandadaXidopo(numeropub));
                oposolidmdo.setIdmarcademandante(null);
                oposolidmdo.setTipo_persona("SOLI");
                if (oposolidmdo.getIdsolicitanteapoderado() != null) {
                    opoSolicitanteApoderadoService.modificarOposolicitanteapoderado(oposolidmdo);
                } else {
                    oposolidmdo.setIdsolicitanteapoderado(opoSolicitanteApoderadoService.obtieneelultimonumerodesolicitanteapoderado() + 1L);

                    oposolidmdo.setIdmarcademandada(opoMarcaDemandadaService.obtenerOpomarcademandadaXidopo(numeropub));
                    oposolidmdo.setIdmarcademandante(null);
                    oposolidmdo.setTipo_persona("SOLI");
                    opoSolicitanteApoderadoService.guardarsolicitanteapo(oposolidmdo);
                }

            }

            for (OpoSolicitanteapoderado oposolidmte : listasolidmte) {
                oposolidmte.setIdmarcademandada(null);
                oposolidmte.setIdmarcademandante(opoMarcaDemandanteService.obtenerOpomarcademandnteXidopo(numeropub));
                oposolidmte.setTipo_persona("SOLI");
                if (oposolidmte.getIdsolicitanteapoderado() != null) {
                    opoSolicitanteApoderadoService.modificarOposolicitanteapoderado(oposolidmte);
                } else {
                    oposolidmte.setIdsolicitanteapoderado(opoSolicitanteApoderadoService.obtieneelultimonumerodesolicitanteapoderado() + 1L);
                    oposolidmte.setIdmarcademandada(null);
                    oposolidmte.setIdmarcademandante(opoMarcaDemandanteService.obtenerOpomarcademandnteXidopo(numeropub));
                    oposolidmte.setTipo_persona("SOLI");
                    opoSolicitanteApoderadoService.guardarsolicitanteapo(oposolidmte);
                }

            }

            for (OpoSolicitanteapoderado opoapodmdo : listaapodmdo) {
                opoapodmdo.setIdmarcademandada(opoMarcaDemandadaService.obtenerOpomarcademandadaXidopo(numeropub));
                opoapodmdo.setIdmarcademandante(null);
                opoapodmdo.setTipo_persona("APOD");

                if (opoapodmdo.getIdsolicitanteapoderado() != null) {
                    opoSolicitanteApoderadoService.modificarOposolicitanteapoderado(opoapodmdo);
                } else {
                    opoapodmdo.setIdsolicitanteapoderado(opoSolicitanteApoderadoService.obtieneelultimonumerodesolicitanteapoderado() + 1L);
                    opoapodmdo.setIdmarcademandada(opoMarcaDemandadaService.obtenerOpomarcademandadaXidopo(numeropub));
                    opoapodmdo.setIdmarcademandante(null);
                    opoapodmdo.setTipo_persona("APOD");

                    opoSolicitanteApoderadoService.guardarsolicitanteapo(opoapodmdo);
                }

            }

            for (OpoSolicitanteapoderado opoapodmte : listaapodmte) {
                opoapodmte.setIdmarcademandada(null);
                opoapodmte.setIdmarcademandante(opoMarcaDemandanteService.obtenerOpomarcademandnteXidopo(numeropub));
                opoapodmte.setTipo_persona("APOD");

                if (opoapodmte.getIdsolicitanteapoderado() != null) {
                    opoSolicitanteApoderadoService.modificarOposolicitanteapoderado(opoapodmte);
                } else {
                    opoapodmte.setIdsolicitanteapoderado(opoSolicitanteApoderadoService.obtieneelultimonumerodesolicitanteapoderado() + 1L);
                    opoapodmte.setIdmarcademandada(null);
                    opoapodmte.setIdmarcademandante(opoMarcaDemandanteService.obtenerOpomarcademandnteXidopo(numeropub));
                    opoapodmte.setTipo_persona("APOD");

                    opoSolicitanteApoderadoService.guardarsolicitanteapo(opoapodmte);
                }

            }

            // modificar datos del demandado
            if (opoMarcaDemandadaService.verificasiexistenumeroopodmda(txtpublidmdo.intValue())) {

                for (int i = 1; i <= oposicionService.obttotaloposicionesxnro_opo(txtpublidmdo); i++) {
                    Long numeropubdmdo = oposicionService.encuentraclaveprin(txtnropublidmdo, i);
                    datosmarcademandadamodi.setIdmarcademandada(opoMarcaDemandadaService.obtenerOpomarcademandadaXidopo(numeropubdmdo));
                    datosmarcademandadamodi.setIdoposicion(numeropubdmdo);
                    datosmarcademandadamodi.setNro_opo(Integer.valueOf(txtpublidmdo + ""));
                    datosmarcademandadamodi.setDmdo_public(Integer.valueOf(txtpublidmdo + ""));
                    datosmarcademandadamodi.setGaceta(txtgacetadmdo);
                    datosmarcademandadamodi.setFecha_public(cldfecahpubdmdo);
                    datosmarcademandadamodi.setDmdo_marca_lnv(txamarcadmdo);
                    datosmarcademandadamodi.setEstado("AC");
                    opoMarcaDemandadaService.modificarOpomarcademandada(datosmarcademandadamodi);

                    datosnotificaciondmdomodi.setIdnotificacion(opoNotificacionService.obtenreidnotificacionxmarcadmda(opoMarcaDemandadaService.obtenerOpomarcademandadaXidopo(numeropubdmdo)));
                    datosnotificaciondmdomodi.setIdmarcademandada(opoMarcaDemandadaService.obtenerOpomarcademandadaXidopo(numeropubdmdo));
                    datosnotificaciondmdomodi.setCiudad_notificacion(txtciudaddmdo);
                    datosnotificaciondmdomodi.setZona_barrio(txtzonadmdo);
                    datosnotificaciondmdomodi.setAvenida_calle(txtcalledmdo);
                    datosnotificaciondmdomodi.setEdificio(txtedificiodmdo);
                    datosnotificaciondmdomodi.setPiso(txtpisodmdo);
                    datosnotificaciondmdomodi.setNumero_departamento(txtnrodepartamentodmdo);
                    datosnotificaciondmdomodi.setNumero(txtnrocasadmdo);
                    datosnotificaciondmdomodi.setReferencia_direccion(txtreferenciadmdo);
                    datosnotificaciondmdomodi.setEmail(txtemaildmdo);
                    datosnotificaciondmdomodi.setTelefono(txttelefonodmdo);
                    datosnotificaciondmdomodi.setCelular(txtcelulardmdo);
                    datosnotificaciondmdomodi.setEstado("AC");
                    opoNotificacionService.modificarOponotificacionmodi(datosnotificaciondmdomodi);
//                    for (OpoSolicitanteapoderado oposolidmdook : listasolidmdo) {
//
//                        oposolidmdook.setIdmarcademandada(opoMarcaDemandadaService.obtenerOpomarcademandadaXidopo(numeropubdmdo));
//                        oposolidmdook.setIdmarcademandante(null);
//                        opoSolicitanteApoderadoService.modificarOposolicitanteapoderado(oposolidmdook);
//                    }
//                    for (OpoSolicitanteapoderado opoapodmdook : listaapodmdo) {
//                        opoapodmdook.setIdmarcademandada(opoMarcaDemandadaService.obtenerOpomarcademandadaXidopo(numeropubdmdo));
//                        opoapodmdook.setIdmarcademandante(null);
//                        opoSolicitanteApoderadoService.modificarOposolicitanteapoderado(opoapodmdook);
//
//                    }

                }

            } else {

            }

        } catch (Exception e) {
            throw e;
        }

    }

    /**
     * Metodo que Elimina datos del tramite ensi del solicitante apoderado
     * notificacion pero solo logicamente y no asi fisicamente
     *
     * Autor: Luis Angel Quispe limachi
     *
     * Fecha 12/11/2016
     *
     * @throws java.lang.Exception
     */
    public void eliminar_datosoposiciondmtedmdanotisoliapo() throws Exception {
 Oposicion datosoposicionmodix = new Oposicion();
        try {
            Long numeropub = oposicionService.encuentraclaveprin(txtnropublidmdo, txtorden_o);

            datosoposicionmodix=oposicionService.obtoposicionxnroopo(numeropub);
            
            datosoposicionmodix.setIdoposicion(numeropub);
            datosoposicionmodix.setNro_opo(txtpublidmdo);
            datosoposicionmodix.setFecha_presentacion(cldfechapresoposicion);
            datosoposicionmodix.setObservacion(txaobservacion);
            datosoposicionmodix.setOrden_o(txtorden_o);
            datosoposicionmodix.setEstadoopo("NA");
            oposicionService.modificaOposicion(datosoposicionmodix);

            datosmarcademandadamodi.setIdmarcademandada(opoMarcaDemandadaService.obtenerOpomarcademandadaXidopo(numeropub));
            datosmarcademandadamodi.setIdoposicion(numeropub);
            datosmarcademandadamodi.setDmdo_public(Integer.valueOf(txtpublidmdo + ""));
            datosmarcademandadamodi.setNro_opo(Integer.valueOf(txtpublidmdo + ""));
            datosmarcademandadamodi.setGaceta(txtgacetadmdo);
            datosmarcademandadamodi.setFecha_public(cldfecahpubdmdo);
            datosmarcademandadamodi.setDmdo_marca_lnv(txamarcadmdo);
            datosmarcademandadamodi.setEstado("NA");
            opoMarcaDemandadaService.modificarOpomarcademandada(datosmarcademandadamodi);

            datosmarcademandantemodi.setIdmarcademandante(opoMarcaDemandanteService.obtenerOpomarcademandnteXidopo(numeropub));
            datosmarcademandantemodi.setIdoposicion(numeropub);
            datosmarcademandantemodi.setDmte_public(Integer.valueOf(txtpubldmte + ""));
            datosmarcademandantemodi.setDmte_reg(txtregistrodmte);
            datosmarcademandantemodi.setDmte_sm(txtexpedientesm);
            datosmarcademandantemodi.setDmte_marca_lnv(txamarcadmte);
            datosmarcademandantemodi.setDmte_uso(txtusodmte);
            datosmarcademandantemodi.setEstado("NA");
            opoMarcaDemandanteService.modificarOpomarcademandnte(datosmarcademandantemodi);

            datosnotificaciondmdomodi.setIdnotificacion(opoNotificacionService.obtenreidnotificacionxmarcadmda(opoMarcaDemandadaService.obtenerOpomarcademandadaXidopo(numeropub)));
            datosnotificaciondmdomodi.setIdmarcademandada(opoMarcaDemandadaService.obtenerOpomarcademandadaXidopo(numeropub));
            datosnotificaciondmdomodi.setCiudad_notificacion(txtciudaddmdo);
            datosnotificaciondmdomodi.setZona_barrio(txtzonadmdo);
            datosnotificaciondmdomodi.setAvenida_calle(txtcalledmdo);
            datosnotificaciondmdomodi.setEdificio(txtedificiodmdo);
            datosnotificaciondmdomodi.setPiso(txtpisodmdo);
            datosnotificaciondmdomodi.setNumero_departamento(txtnrodepartamentodmdo);
            datosnotificaciondmdomodi.setNumero(txtnrocasadmdo);
            datosnotificaciondmdomodi.setReferencia_direccion(txtreferenciadmdo);
            datosnotificaciondmdomodi.setEmail(txtemaildmdo);
            datosnotificaciondmdomodi.setTelefono(txttelefonodmdo);
            datosnotificaciondmdomodi.setCelular(txtcelulardmdo);
            datosnotificaciondmdomodi.setEstado("NA");
            opoNotificacionService.modificarOponotificacionmodi(datosnotificaciondmdomodi);

            datosnotificaciondmtemodi.setIdnotificacion(opoNotificacionService.obtenreidnotificacionxmarcadmte(opoMarcaDemandanteService.obtenerOpomarcademandnteXidopo(numeropub)));
            datosnotificaciondmtemodi.setIdmarcademandada(opoMarcaDemandanteService.obtenerOpomarcademandnteXidopo(numeropub));
            datosnotificaciondmtemodi.setCiudad_notificacion(txtciudaddmte);
            datosnotificaciondmtemodi.setZona_barrio(txtzonadmte);
            datosnotificaciondmtemodi.setAvenida_calle(txtcalledmte);
            datosnotificaciondmtemodi.setEdificio(txtedificiodmte);
            datosnotificaciondmtemodi.setPiso(txtpisodmte);
            datosnotificaciondmtemodi.setNumero_departamento(txtnrodepadmte);
            datosnotificaciondmtemodi.setNumero(txtnrocasadmte);
            datosnotificaciondmtemodi.setReferencia_direccion(txtreferenciadmte);
            datosnotificaciondmtemodi.setEmail(txtemaildmte);
            datosnotificaciondmtemodi.setTelefono(txttelefonodmte);
            datosnotificaciondmtemodi.setCelular(txtceludmte);
            datosnotificaciondmtemodi.setEstado("NA");
            opoNotificacionService.modificarOponotificacionmodi(datosnotificaciondmtemodi);

            for (OpoSolicitanteapoderado oposolidmdo : listasolidmdo) {
                oposolidmdo.setIdmarcademandada(opoMarcaDemandadaService.obtenerOpomarcademandadaXidopo(numeropub));
                oposolidmdo.setIdmarcademandante(null);
                oposolidmdo.setEstado("NA");
                opoSolicitanteApoderadoService.modificarOposolicitanteapoderado(oposolidmdo);
            }
            for (OpoSolicitanteapoderado oposolidmte : listasolidmte) {
                oposolidmte.setIdmarcademandada(null);
                oposolidmte.setIdmarcademandante(opoMarcaDemandanteService.obtenerOpomarcademandnteXidopo(numeropub));
                oposolidmte.setEstado("NA");
                opoSolicitanteApoderadoService.modificarOposolicitanteapoderado(oposolidmte);

            }
            for (OpoSolicitanteapoderado opoapodmdo : listaapodmdo) {
                opoapodmdo.setIdmarcademandada(opoMarcaDemandadaService.obtenerOpomarcademandadaXidopo(numeropub));
                opoapodmdo.setIdmarcademandante(null);
                opoapodmdo.setEstado("NA");
                opoSolicitanteApoderadoService.modificarOposolicitanteapoderado(opoapodmdo);

            }
            for (OpoSolicitanteapoderado opoapodmte : listaapodmte) {
                opoapodmte.setIdmarcademandada(null);
                opoapodmte.setIdmarcademandante(opoMarcaDemandanteService.obtenerOpomarcademandnteXidopo(numeropub));
                opoapodmte.setEstado("NA");
                opoSolicitanteApoderadoService.modificarOposolicitanteapoderado(opoapodmte);
            }

        } catch (Exception e) {
            throw e;
        }

    }

    /**
     * Metodo que limpia los datos de la vista
     *
     * Autor: Luis Angel Quispe limachi
     *
     * Fecha 12/11/2016
     *
     * @throws java.lang.Exception
     */
    public void limpiar_datosoposiciondmtedmdanotisoliapo() throws Exception {

        txtnropublidmdo = null;
        txtorden_o = null;
        txtpublidmdo = null;
        txtgacetadmdo = null;
        cldfecahpubdmdo = null;
        txamarcadmdo = null;

        txtnrogestionres = null;
        txtopores = null;
        txtsigres = null;
        txtcancelares = null;
        cmbregistro = null;

        txtciudaddmdo = null;
        txtzonadmdo = null;
        txtcalledmdo = null;
        txtnrocasadmdo = null;
        txtcelulardmdo = null;
        txtedificiodmdo = null;
        txtpisodmdo = null;
        txtnrodepartamentodmdo = null;
        txttelefonodmdo = null;
        txtemaildmdo = null;
        txtreferenciadmdo = null;
        txtpubldmte = null;
        txtregistrodmte = null;
        txtexpedientesm = null;
        txamarcadmte = null;
        txtusodmte = null;

        txtciudaddmte = null;
        txtzonadmte = null;
        txtcalledmte = null;
        txtnrocasadmte = null;
        txtceludmte = null;
        txtedificiodmte = null;
        txtpisodmte = null;
        txtnrodepadmte = null;
        txttelefonodmte = null;
        txtemaildmte = null;
        txtreferenciadmte = null;
        txtgestionexpediente = null;
        txtextencionespediente = null;

        txtestadotramite = null;
        txtultimactividad = null;

        txaobservacion = null;
        cldfechaultimaactividad = null;

        listascasosadj = new ArrayList<Oposicion>();
        listaactividades = new ArrayList<OpoEvento>();
        listasolidmdo = new ArrayList<OpoSolicitanteapoderado>();
        listaapodmdo = new ArrayList<OpoSolicitanteapoderado>();
        listasolidmte = new ArrayList<OpoSolicitanteapoderado>();
        listaapodmte = new ArrayList<OpoSolicitanteapoderado>();
        listarecursos = new ArrayList<OpoRecurso>();

    }

    /**
     * Metodo que limpia los datos de la vista de la parte del demandante
     *
     * Autor: Luis Angel Quispe limachi
     *
     * Fecha 12/11/2016
     *
     * @throws java.lang.Exception
     */
    public void limpiar_datosoposiciondmte() throws Exception {

        txtregistrodmte = null;
        txtexpedientesm = null;
        txamarcadmte = null;
        txtusodmte = null;
        cmbregistro = null;
        txtciudaddmte = null;
        txtzonadmte = null;
        txtcalledmte = null;
        txtnrocasadmte = null;
        txtceludmte = null;
        txtedificiodmte = null;
        txtpisodmte = null;
        txtnrodepadmte = null;
        txttelefonodmte = null;
        txtemaildmte = null;
        txtreferenciadmte = null;
        txtgestionexpediente = null;
        txtextencionespediente = null;

        txtestadotramite = null;
        txtultimactividad = null;

        listasolidmdo = new ArrayList<OpoSolicitanteapoderado>();
        listaapodmdo = new ArrayList<OpoSolicitanteapoderado>();

    }

    /**
     * Metodo que busca en la tabla de Signos los datos de publicacion
     *
     * Autor: Luis Angel Quispe limachi
     *
     * Fecha 12/11/2016
     *
     * @throws java.lang.Exception
     */
    public void buscarXPublicacion() throws Exception {

        if (txtpublidmdo != null) {

            SigSignoMarca encontradoSigno = sigSignoMarcaService.listaSigSignoMarcaXPublicacion(txtpublidmdo);

            this.txtpublidmdoclon = oposicionService.extraerultimonorden(txtpublidmdo) + 1;

            limpiar_datosoposiciondmte();
            mostrarDatosEncontrados(encontradoSigno);
        }
        txtnropublidmdo = txtpublidmdo;

    }

    /**
     * Metodo encuentra dodos los datos por numero de publicacion y los muestra
     * en la pantalla de oposiciones
     *
     * Autor: Luis Angel Quispe limachi
     *
     * Fecha 12/11/2016
     *
     * @param encontrado
     *
     * @throws java.lang.Exception
     *
     */
    public void mostrarDatosEncontrados(SigSignoMarca encontrado) throws Exception {
        if (encontrado != null) {
            txtgacetadmdo = Integer.valueOf(encontrado.getNumeroGaceta() + "");
            cldfecahpubdmdo = encontrado.getFechaPublicacion();
            txamarcadmdo = encontrado.getSigno();

            List<SigSolicitanteApoderado> listaSolicitanteApoderadoSig = sigSolicitanteApoderadoService.listadoSolicitanteXidsignomarca(encontrado.getIdSignoMarca());

            for (SigSolicitanteApoderado item : listaSolicitanteApoderadoSig) {
                try {

                    OpoSolicitanteapoderado titularRegistrado = new OpoSolicitanteapoderado();
                    //  String nombrex = devuelveNombreJuridicoONatural(item.getNombreRazonSocial(), item.getPrimerApellido(), item.getSegundoApellido());
                    titularRegistrado.setNombre_razonsocial(item.getNombreRazonSocial());
                    titularRegistrado.setPrimer_apellido(item.getPrimerApellido());
                    titularRegistrado.setSegundo_apellido(item.getSegundoApellido());

                    titularRegistrado.setDireccion(item.getDireccion());
                    titularRegistrado.setTipo_documento(item.getTipoDocumento());
                    titularRegistrado.setNumero_documento(item.getNumeroDocumento());
                    titularRegistrado.setPais(item.getPais());
                    titularRegistrado.setSolicitud_depa(item.getSolicitudDepartamento());
                    titularRegistrado.setDireccion(item.getDireccion());
                    titularRegistrado.setCelular(item.getCelular());
                    titularRegistrado.setTelefono(item.getTelefono());
                    titularRegistrado.setFax(item.getFax());
                    titularRegistrado.setEmail(item.getEmail());

                    listasolidmdo.add(titularRegistrado);

                } catch (Exception ex) {
                    Logger.getLogger(OposicionBean.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
            }
            List<SigSolicitanteApoderado> listaApoderadoSig = sigSolicitanteApoderadoService.listadoApoderadoXidsignomarca(encontrado.getIdSignoMarca());

            for (SigSolicitanteApoderado listaapo : listaApoderadoSig) {
                try {

                    OpoSolicitanteapoderado titularRegistrado = new OpoSolicitanteapoderado();
                    // String nombrex = devuelveNombreJuridicoONatural(listaapo.getNombreRazonSocial(), listaapo.getPrimerApellido(), listaapo.getSegundoApellido());
                    titularRegistrado.setNombre_razonsocial(listaapo.getNombreRazonSocial());
                    titularRegistrado.setPrimer_apellido(listaapo.getPrimerApellido());
                    titularRegistrado.setSegundo_apellido(listaapo.getSegundoApellido());
                    titularRegistrado.setDireccion(listaapo.getDireccion());
                    titularRegistrado.setTipo_documento(listaapo.getTipoDocumento());
                    titularRegistrado.setNumero_documento(listaapo.getNumeroDocumento());
                    titularRegistrado.setGenero(listaapo.getGenero());
                    titularRegistrado.setPais(listaapo.getPais());
                    titularRegistrado.setSolicitud_depa(listaapo.getSolicitudDepartamento());
                    titularRegistrado.setDireccion(listaapo.getDireccion());
                    titularRegistrado.setCelular(listaapo.getCelular());
                    titularRegistrado.setTelefono(listaapo.getTelefono());
                    titularRegistrado.setFax(listaapo.getFax());
                    titularRegistrado.setEmail(listaapo.getEmail());
                    titularRegistrado.setPoder(listaapo.getPoder());
                    listaapodmdo.add(titularRegistrado);

                } catch (Exception ex) {
                    Logger.getLogger(OposicionBean.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
            }

            notificaciondatossig = sigDireccionDeNotificacionService.obtenerDireccionNotificacionXSignoMarca(encontrado.getIdSignoMarca());

            txtzonadmdo = notificaciondatossig.getZonaBarrio();
            txtciudaddmdo = notificaciondatossig.getCiudadNotificacion();
            txtcalledmdo = notificaciondatossig.getAvenidaCalle();
            txtnrocasadmdo = notificaciondatossig.getNumero();
            txtcelulardmdo = notificaciondatossig.getCelular();
            txtedificiodmdo = notificaciondatossig.getEdificio();
            txtpisodmdo = notificaciondatossig.getPiso();
            txtnrodepartamentodmdo = notificaciondatossig.getDepartamento();
            txttelefonodmdo = notificaciondatossig.getTelefono();
            txtemaildmdo = notificaciondatossig.getCorreoElectronico();
            txtreferenciadmdo = notificaciondatossig.getReferenciaDireccion();

        } else {

        }
    }

    /**
     * Metodo busca el numero de publicacion por parte del demandante
     *
     * Autor: Luis Angel Quispe limachi
     *
     * Fecha 20/11/2016
     *
     */
    public void buscarXPublicaciondmte() throws Exception {

        if (txtpubldmte != null) {
            SigSignoMarca encontradoSigno = sigSignoMarcaService.listaSigSignoMarcaXPublicacion(txtpubldmte.longValue());
            listasolidmte = new ArrayList<OpoSolicitanteapoderado>();
            listaapodmte = new ArrayList<OpoSolicitanteapoderado>();
            mostrarDatosEncontradosdmte(encontradoSigno);
        }
    }

    public void mostrarDatosEncontradosdmte(SigSignoMarca encontrado) throws Exception {
        if (encontrado != null) {

            if (encontrado.getSm() != null && encontrado.getSm() != 0) {
                HashMap resultado = comunService.obtenerNumeroGestionExtensionCodigoSM(encontrado.getSm());
                txtexpedientesm = Long.parseLong(resultado.get("numero").toString());
                txtgestionexpediente = Long.parseLong(resultado.get("gestion").toString());
                txtextencionespediente = resultado.get("extension").toString();
            }
            System.out.println("%%%%%%%%%%%%%%%%%%" + txtpubldmte);
            if (txtpubldmte != null) {
                txtpubldmte = Integer.valueOf(encontrado.getNumeroPublicacion() + "");
            } else {
                txtpubldmte = 0;
            }

            if (encontrado.getNumeroRegistro() != null && encontrado.getNumeroRegistro() != 0) {
                txtregistrodmte = Integer.valueOf(encontrado.getNumeroRegistro() + "");
                cmbregistro = encontrado.getSerieRegistro();
            }
            txamarcadmte = encontrado.getSigno();

            List<SigSolicitanteApoderado> listaSolicitanteApoderadoSig = sigSolicitanteApoderadoService.listadoSolicitanteXidsignomarca(encontrado.getIdSignoMarca());

            for (SigSolicitanteApoderado item : listaSolicitanteApoderadoSig) {
                try {

                    OpoSolicitanteapoderado titularRegistrado = new OpoSolicitanteapoderado();
                    // String nombrex = devuelveNombreJuridicoONatural(item.getNombreRazonSocial(), item.getPrimerApellido(), item.getSegundoApellido());

                    titularRegistrado.setNombre_razonsocial(item.getNombreRazonSocial());
                    titularRegistrado.setPrimer_apellido(item.getPrimerApellido());
                    titularRegistrado.setSegundo_apellido(item.getSegundoApellido());

                    titularRegistrado.setDireccion(item.getDireccion());
                    titularRegistrado.setTipo_documento(item.getTipoDocumento());
                    titularRegistrado.setNumero_documento(item.getNumeroDocumento());
                    titularRegistrado.setPais(item.getPais());
                    titularRegistrado.setSolicitud_depa(item.getSolicitudDepartamento());
                    titularRegistrado.setDireccion(item.getDireccion());
                    titularRegistrado.setCelular(item.getCelular());
                    titularRegistrado.setTelefono(item.getTelefono());
                    titularRegistrado.setFax(item.getFax());
                    titularRegistrado.setEmail(item.getEmail());

                    listasolidmte.add(titularRegistrado);

                } catch (Exception ex) {
                    Logger.getLogger(OposicionBean.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
            }

            List<SigSolicitanteApoderado> listaApoderadoSig = sigSolicitanteApoderadoService.listadoApoderadoXidsignomarca(encontrado.getIdSignoMarca());

            for (SigSolicitanteApoderado listaapo : listaApoderadoSig) {
                try {

                    OpoSolicitanteapoderado titularRegistrado = new OpoSolicitanteapoderado();
                    //  String nombrex = devuelveNombreJuridicoONatural(listaapo.getNombreRazonSocial(), listaapo.getPrimerApellido(), listaapo.getSegundoApellido());
                    titularRegistrado.setNombre_razonsocial(listaapo.getNombreRazonSocial());
                    titularRegistrado.setPrimer_apellido(listaapo.getPrimerApellido());
                    titularRegistrado.setSegundo_apellido(listaapo.getSegundoApellido());
                    titularRegistrado.setDireccion(listaapo.getDireccion());
                    titularRegistrado.setTipo_documento(listaapo.getTipoDocumento());
                    titularRegistrado.setNumero_documento(listaapo.getNumeroDocumento());
                    titularRegistrado.setPais(listaapo.getPais());
                    titularRegistrado.setSolicitud_depa(listaapo.getSolicitudDepartamento());
                    titularRegistrado.setDireccion(listaapo.getDireccion());
                    titularRegistrado.setCelular(listaapo.getCelular());
                    titularRegistrado.setTelefono(listaapo.getTelefono());
                    titularRegistrado.setFax(listaapo.getFax());
                    titularRegistrado.setEmail(listaapo.getEmail());
                    titularRegistrado.setPoder(listaapo.getPoder());

                    listaapodmte.add(titularRegistrado);

                } catch (Exception ex) {
                    Logger.getLogger(OposicionBean.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
            }

            notificaciondatossigdmte = sigDireccionDeNotificacionService.obtenerDireccionNotificacionXSignoMarca(encontrado.getIdSignoMarca());

            txtciudaddmte = notificaciondatossigdmte.getCiudadNotificacion();
            txtzonadmte = notificaciondatossigdmte.getZonaBarrio();
            txtcalledmte = notificaciondatossigdmte.getAvenidaCalle();
            txtnrocasadmte = notificaciondatossigdmte.getNumero();
            txtceludmte = notificaciondatossigdmte.getCelular();
            txtedificiodmte = notificaciondatossigdmte.getEdificio();
            txtpisodmte = notificaciondatossigdmte.getPiso();
            txtnrodepadmte = notificaciondatossigdmte.getDepartamento();
            txtemaildmte = notificaciondatossigdmte.getCorreoElectronico();
            txttelefonodmte = notificaciondatossigdmte.getTelefono();
            txtreferenciadmte = notificaciondatossigdmte.getReferenciaDireccion();

        } else {

        }
    }

    /**
     * Metodo que concatena el nombre apellido mat y paterno
     *
     * Autor: Luis Angel Quispe limachi
     *
     * Fecha 20/11/2016
     *
     * @param nombre
     * @param segundoApellido
     * @param primerApellido
     * @return String
     * @throws java.lang.Exception
     *
     */
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

    /**
     * Metodo Busca Por numero de registro
     *
     * Autor: Luis Angel Quispe limachi
     *
     * Fecha 20/11/2016
     *
     * @throws java.lang.Exception
     */
    public void buscarXRegistro() throws Exception {
        System.out.println("===============================================>" + txtregistrodmte + "   " + cmbregistro);
        if (txtregistrodmte != null) {

            SigSignoMarca encontradoSigno = sigSignoMarcaService.listaSigSignoMarcaXRegistro(txtregistrodmte.longValue(), cmbregistro, " ");
            listasolidmte = new ArrayList<OpoSolicitanteapoderado>();
            listaapodmte = new ArrayList<OpoSolicitanteapoderado>();
            mostrarDatosEncontradosdmte(encontradoSigno);
        }
    }

    /**
     * Metodo Busca Por numero de SM
     *
     * Autor: Luis Angel Quispe limachi
     *
     * Fecha 20/11/2016
     *
     * @throws java.lang.Exception
     */
    public void buscarXSM() throws Exception {
        if (txtextencionespediente == null) {
            txtextencionespediente = "";
        }
        if (txtexpedientesm != null && txtgestionexpediente != null) {//&& gestionExpediente != null

            Long expedientesm = creaSM("SM", txtexpedientesm, txtgestionexpediente, txtextencionespediente);

            SigSignoMarca encontradoSigno = sigSignoMarcaService.listaSigSignoMarcaXSM(expedientesm);
            listasolidmte = new ArrayList<OpoSolicitanteapoderado>();
            listaapodmte = new ArrayList<OpoSolicitanteapoderado>();
            mostrarDatosEncontradosdmte(encontradoSigno);
        }
    }

    /**
     * Metodo crear el SM
     *
     * Autor: Luis Angel Quispe limachi
     *
     * Fecha 20/11/2016
     *
     * @param textoSM
     * @param numeroSM
     * @param gestionSM
     * @param extension
     *
     * @return sm
     *
     */
    public Long creaSM(String textoSM, Long numeroSM, Long gestionSM, String extension) throws Exception {
        Long sm = 0l;
        //Long extension = new Long(Long.parseLong(extensionSM));
        if (numeroSM != null && gestionSM != null) {
            //sm = numeroSM + gestionSM;
            sm = comunService.codificarCodigoSM(numeroSM.toString(), gestionSM.toString(), extension);
        }
        return sm;
    }

    /**
     * Metodo para cargar el dialogo de seguimiento
     *
     * Autor: Luis Angel Quispe limachi
     *
     * Fecha 20/11/2016
     *
     */
    public void cargaDialogoSeguimiento() {

        if (txtnropublidmdo != null && txtorden_o != null) {
            Map<String, Object> options = new HashMap<String, Object>();
            options.put("closable", false);
            options.put("resizable", false);
            options.put("height", 595);
            options.put("width", 1100);
            options.put("contentWidth", "100%");
            options.put("contentHeight", "100%");
            options.put("modal", true);
            Map<String, List<String>> var = new HashMap<String, List<String>>();
            List<String> params = new ArrayList<String>();

            params.add(txtnropublidmdo.toString());

            params.add(txtorden_o.toString());
            var.put("enviarDatos", params);
            RequestContext.getCurrentInstance().openDialog("seguimientoOpo", options, var);
            RequestContext.getCurrentInstance().update("frmExamenOpo:tablaflujoactividad");
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "El Tramite no existe verifique si tiene numero de publicacion y numero de orden", ""));
        }

    }

    /**
     * Metodo para terminar el dialogo de seguimiento
     *
     * Autor: Luis Angel Quispe limachi
     *
     * Fecha 20/11/2016
     *
     * @param event
     *
     */
    public void terminaDialogoSeguimiento(SelectEvent event) {

        if (event.getObject() != "Exit") {
            //    solicitanteApoderado = (OpoSolicitanteapoderado) event.getObject();
            // listaapodmte.add(solicitanteApoderado);
            //  FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(solicitanteApoderado.getNombre_razonsocial()));
            //  listaactividades

        } else {
            RequestContext.getCurrentInstance().update("frmExamenOpo:tablaflujoactividad");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cerrado"));
        }

    }

    /**
     * Metodo para cargar el dialogo de historial
     *
     * Autor: Luis Angel Quispe limachi
     *
     * Fecha 15/11/2016
     *
     */
    public void cargaDialogoHistorial() {

        Map<String, Object> options = new HashMap<String, Object>();
        options.put("closable", false);
        options.put("resizable", true);
        options.put("height", 570);
        options.put("width", 1015);
        options.put("contentWidth", "100%");
        options.put("contentHeight", "100%");
        options.put("modal", true);
        Map<String, List<String>> var = new HashMap<String, List<String>>();
        List<String> params = new ArrayList<String>();

        params.add(txtnropublidmdo.toString());

        params.add(txtorden_o.toString());
        var.put("enviarDatosHisto", params);
        RequestContext.getCurrentInstance().openDialog("historialOpo", options, var);

    }

    /**
     * Metodo para terminar el dialogo de historial
     *
     * Autor: Luis Angel Quispe limachi
     *
     * Fecha 15/11/2016
     *
     * @param event
     *
     */
    public void terminaDialogoHistorial(SelectEvent event) {

        if (event.getObject() != "Exit") {
            //    solicitanteApoderado = (OpoSolicitanteapoderado) event.getObject();
            // listaapodmte.add(solicitanteApoderado);
            //  FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(solicitanteApoderado.getNombre_razonsocial()));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cerrado"));
        }

    }

    /**
     * Metodo para cargar el dialogo de busqueda
     *
     * Autor: Luis Angel Quispe limachi
     *
     * Fecha 15/11/2016
     *
     */
    public void cargaDialogoBusqueda() {

        //list.add("APOD");
        //params.put("id", list);
        Map<String, Object> options = new HashMap<String, Object>();
        options.put("closable", false);
        options.put("resizable", false);
        options.put("height", 615);
        options.put("width", 1115);
        options.put("contentWidth", "100%");
        options.put("contentHeight", "100%");
        options.put("modal", true);
        Map<String, List<String>> params = new HashMap<String, List<String>>();
        List<String> list = new ArrayList<String>();
        list.add("examenoposicion");
        params.put("valor", list);
        RequestContext.getCurrentInstance().openDialog("busquedaOpo", options, params);

    }

    /**
     * Metodo para terminar el dialogo de busqueda
     *
     * Autor: Luis Angel Quispe limachi
     *
     * Fecha 15/11/2016
     *
     * @param event
     *
     */
    public void terminaDialogoBusqueda(SelectEvent event) {

        if (event.getObject() != "Exit") {
            //    solicitanteApoderado = (OpoSolicitanteapoderado) event.getObject();
            // listaapodmte.add(solicitanteApoderado);
            //  FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(solicitanteApoderado.getNombre_razonsocial()));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Busqueda cerrada", ""));
        }

    }

    /**
     * Metodo para obtener el idestado por el idestado
     *
     * Autor: Luis Angel Quispe limachi
     *
     * Fecha 15/11/2016
     *
     * @param idestado
     *
     * @return String
     * @throws java.lang.Exception
     *
     */
    public String obtienestadoxidestado(Long idestado) throws Exception {

        if (idestado != 0) {
            return opoEstadoService.devuelvenombredelestadoxidestado(idestado);
        }
        return "";

    }

    /**
     * Metodo para obtener el actividad por idactividad
     *
     * Autor: Luis Angel Quispe limachi
     *
     * Fecha 15/11/2016
     *
     * @param idactividad
     * @throws java.lang.Exception
     *
     */
    public String obtieneactividadxidactividad(Long idactividad) throws Exception {

        return opoActividadService.muestranomdelaactividadxidactividad(idactividad);

    }

    /**
     * Metodo cargar resolucion y recurso
     *
     * Autor: Luis Angel Quispe limachi
     *
     * Fecha 15/11/2016
     *
     * @throws java.lang.Exception
     */
    public void muestraresolucionrecurso() throws Exception {

        Long numeropub1 = oposicionService.encuentraclaveprin(txtnropublidmdo, txtorden_o);
        if (opoResolucionService.obtenerObjOporesolucion(numeropub1) != null) {
            datosresolucion = opoResolucionService.obtenerObjOporesolucion(numeropub1);
        }

        if (datosresolucion.getIdevento() != null) {
            gregfech.setTime(datosresolucion.getFecha());
            int fecharesol = gregfech.get(Calendar.YEAR);
            String concatena = String.valueOf(datosresolucion.getNumero_resolucion()) + " / " + String.valueOf(fecharesol);
            txtnrogestionres = concatena;

            txtopores = datosresolucion.getResolucion_oposicion();
            txtsigres = datosresolucion.getResolucion_signo();
            txtcancelares = datosresolucion.getResolucion_cancelacion();
        }

        if (opoRecursoService.obtenerListadoOpoRecursoxnrooposicion(numeropub1) != null) {
            listarecursos = opoRecursoService.obtenerListadoOpoRecursoxnrooposicion(numeropub1);
        }
    }

    /**
     * Metodo para fusionar el año y la fecha de resolucion
     *
     * Autor: Luis Angel Quispe limachi
     *
     * Fecha 15/11/2016
     *
     * @param nroresol
     * @param fec
     * @return String
     * @throws java.lang.Exception
     */
    public String convertirdateynrorecurso(Date fec, Integer nroresol) throws Exception {

        if (fec != null && nroresol != null) {
            gregfech.setTime(fec);
            int fecharesol = gregfech.get(Calendar.YEAR);
            String concatena = String.valueOf(nroresol) + " / " + String.valueOf(fecharesol);

            return concatena;
        }
        return "";

    }

    /**
     * Metodo para convertir el tipo recurso en la vista
     *
     * Autor: Luis Angel Quispe limachi
     *
     * Fecha 15/11/2016
     *
     * @param inicialtipo
     * @return
     */
    public String conviertetiporecurso(String inicialtipo) {

        if (inicialtipo != null) {
            if (inicialtipo.equals("j")) {
                return "Jerárquico";
            }
            if (inicialtipo.equals("rev")) {
                return "Revocatoria";
            }
        }
        return "";
    }

    /**
     * Metodo para devolver el tipodocumento por su codigo
     *
     * Autor: Luis Angel Quispe limachi
     *
     * Fecha 15/11/2016
     *
     * @param codigo
     * @return
     * @throws java.lang.Exception
     *
     */
    public String devuelveTipoDocumento(String codigo) throws Exception {
        if (codigo != null && !codigo.equals("")) {
            return dominioService.obtenerListadoDominioXCodigo(EnumNombreDominio.TIPO_DOCUMENTO.getCodigo(), codigo).get(0).getNombre();
        }
        return "";
    }

    /**
     * Metodo para devolver el tipodocumento por su codigo
     *
     * Autor: Luis Angel Quispe limachi
     *
     * Fecha 15/11/2016
     *
     * @param codigo
     * @return String
     * @throws java.lang.Exception
     */
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

    /**
     * Metodo para devolver el departamento por su codigo
     *
     * Autor: Luis Angel Quispe limachi
     *
     * Fecha 15/11/2016
     *
     * @param codigo
     *
     * @return
     *
     * @throws java.lang.Exception
     */
    public String devuelveDepartamento(String codigo) throws Exception {

        if (codigo != null && !codigo.equals("")) {
            return dominioService.obtenerListadoDominioXCodigo(EnumNombreDominio.LUGAR_EXPEDICION.getCodigo(), codigo).get(0).getNombre();
        }
        return "";
    }

    /**
     * Metodo para devolver el genero por su codigo
     *
     * Autor: Luis Angel Quispe limachi
     *
     * Fecha 15/11/2016
     *
     * @param codigo
     * @return String
     * @throws java.lang.Exception
     */
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

    /**
     * Metodo para devolver el pais por su codigo
     *
     * Autor: Luis Angel Quispe limachi
     *
     * Fecha 15/11/2016
     *
     * @param codigo
     * @return String
     */
    public String devuelvePais(String codigo) throws Exception {
        if (codigo != null && !codigo.equals("")) {
            return dominioService.obtenerListadoDominioXCodigo(EnumNombreDominio.PAIS.getCodigo(), codigo).get(0).getNombre();
        }
        return "";
    }

    /**
     * Metodo para devolver el pais por su codigo
     *
     * Autor: Luis Angel Quispe limachi
     *
     * Fecha 15/11/2016
     *
     * @param codigo
     * @return String
     */
    public Integer devuelveelnroOrden() throws Exception {
        //  System.out.println("========================>" + txt_auxorden);
        if (txt_auxorden != null) {
            return txt_auxorden;
        }
        return 0;
    }

    /**
     * Metodo para devolver el pais por su codigo
     *
     * Autor: Luis Angel Quispe limachi
     *
     * Fecha 15/11/2016
     *
     * @param codigo
     * @return String
     */
    public void terminaTramiteOpo() throws Exception {
        
        
        /*********seguimiento**********/
        int swsegui = 0;
        System.out.println("txtpublidmdo::"+txtpublidmdo);
         if (txtpublidmdo != null && txtpublidmdo != 0) {
         
          SignomarcaAux = sigSignoMarcaService.listaSigSignoMarcaXPublicacion(Long.valueOf(txtpublidmdo));
             //System.out.println("SignomarcaAux.getIdSignoMarca():"+SignomarcaAux.getIdSignoMarca());
             System.out.println("SignomarcaAux::"+SignomarcaAux.getIdSignoMarca());
           if(seguimientoService.listaSeguimientoXIdSignomarca(SignomarcaAux.getIdSignoMarca()).get(0).getEtapa().equals("OPO"))     
           {   System.out.println("Entra aqui0");
               if(seguimientoService.listaSeguimientoXIdSignomarca(SignomarcaAux.getIdSignoMarca()).get(0).getFechaFin() == null)
               {//System.out.println("Entra aui.......");
                 //SignomarcaAux = sigSignoMarcaService.listaSigSignoMarcaXPublicacion(Long.valueOf(txtpublidmdo));
                      System.out.println("Entra aqui1");
                 Seguimiento seguimientoNuevoOpo = new Seguimiento();
                if (SignomarcaAux.getIdSignoMarca() != null) {
                     seguimientoNuevoOpo.setId(SignomarcaAux.getIdSignoMarca());
                }
                seguimientoNuevoOpo.setIdUsuario(super.getIdUsuarioSession());
                seguimientoNuevoOpo.setSm(SignomarcaAux.getSm());
                if (txtpublidmdo != null) {
                  seguimientoNuevoOpo.setNumeroPublicacion((txtpublidmdo).longValue());
                }
                seguimientoNuevoOpo.setFechaFin(comunService.obtenerFechaHoraServidor(1L));
                seguimientoNuevoOpo.setNumeroRegistro(SignomarcaAux.getNumeroRegistro());
                seguimientoNuevoOpo.setSerieRegistro(SignomarcaAux.getSerieRegistro());
             
                seguimientoNuevoOpo.setEtapa(listEtapaUs.get(0).getTipoEtapa());
                seguimientoNuevoOpo.setObservacion(null);
                seguimientoNuevoOpo.setEditable(false);
            
                seguimientoNuevoOpo.setEstado("AC");
                ultimoSeguimientoopo = seguimientoService.guardar_modificar_Seguimiento_etapa(seguimientoNuevoOpo, "SIG");
            //swsegui = 1;
               }
           }          
            
        }
        
        
        
        
        
        
        
        
        
        
         /******************************/
         
        
        
        
        
        
        
        
        
        /*int swsegui = 0;
        if (txtpubldmte != null && txtpubldmte != 0) {

            SignomarcaAux = sigSignoMarcaService.listaSigSignoMarcaXPublicacion(Long.valueOf(txtpubldmte));
            Seguimiento seguimientoNuevoOpo = new Seguimiento();
            if (SignomarcaAux.getIdSignoMarca() != null) {
                seguimientoNuevoOpo.setId(SignomarcaAux.getIdSignoMarca());
            }
            seguimientoNuevoOpo.setIdUsuario(super.getIdUsuarioSession());
            String unionsm = "0";
            if (txtextencionespediente != null) {
                unionsm = Long.toString(txtgestionexpediente) + Long.toString(txtexpedientesm) + txtextencionespediente;
            } else {
                unionsm = Long.toString(txtgestionexpediente) + Long.toString(txtexpedientesm) + "00";
            }
            if (unionsm.length() == 12) {
                seguimientoNuevoOpo.setSm(Long.parseLong(unionsm));
            }
            if (txtpubldmte != null) {
                seguimientoNuevoOpo.setNumeroPublicacion((txtpubldmte).longValue());
            }
            if (txtregistrodmte != null) {
                seguimientoNuevoOpo.setNumeroRegistro((txtregistrodmte).longValue());
            }
            if (!(cmbregistro).equals("")) {
                seguimientoNuevoOpo.setSerieRegistro(cmbregistro);
            }
            seguimientoNuevoOpo.setEtapa(listEtapaUs.get(0).getTipoEtapa());
            seguimientoNuevoOpo.setObservacion(null);
            seguimientoNuevoOpo.setEditable(false);
            seguimientoNuevoOpo.setEstado("AC");
            ultimoSeguimientoopo = seguimientoService.guardar_modificar_Seguimiento_etapa(seguimientoNuevoOpo, "SIG");

            swsegui = 1;
        }
        String smaux = "0";
        if (txtgestionexpediente != null && txtexpedientesm != null && swsegui == 0) {

            if (txtextencionespediente != null && txtextencionespediente.equals("")) {
                smaux = Long.toString(txtgestionexpediente) + oposicionService.completasmxnroexp(txtexpedientesm) + "00";
            } else {
                smaux = Long.toString(txtgestionexpediente) + oposicionService.completasmxnroexp(txtexpedientesm) + txtextencionespediente;
            }
            Long smauxlo = Long.parseLong(smaux);
            SignomarcaAux = sigSignoMarcaService.listaSigSignoMarcaXSM(smauxlo);
            Seguimiento seguimientoNuevoOpo = new Seguimiento();

            if (SignomarcaAux.getIdSignoMarca() != null) {
                seguimientoNuevoOpo.setId(SignomarcaAux.getIdSignoMarca());
            }
            seguimientoNuevoOpo.setIdUsuario(super.getIdUsuarioSession());
            if (enlazaobjetos.getObjmarcademandante().getDmte_sm() != null) {
                seguimientoNuevoOpo.setSm(smauxlo);
            }
            if (smaux.length() == 12) {
                seguimientoNuevoOpo.setSm(smauxlo);
            }

            if (txtpubldmte != null) {
                seguimientoNuevoOpo.setNumeroPublicacion((txtpubldmte).longValue());
            }
            if (txtregistrodmte != null) {
                seguimientoNuevoOpo.setNumeroRegistro((txtregistrodmte).longValue());
            }
            if (!(cmbregistro).equals("")) {
                seguimientoNuevoOpo.setSerieRegistro(cmbregistro);
            }

            seguimientoNuevoOpo.setEtapa(listEtapaUs.get(0).getTipoEtapa());
            seguimientoNuevoOpo.setObservacion(null);
            seguimientoNuevoOpo.setEditable(false);
            seguimientoNuevoOpo.setEstado("AC");
            ultimoSeguimientoopo = seguimientoService.guardar_modificar_Seguimiento_etapa(seguimientoNuevoOpo, "SIG");
            swsegui = 1;
        }
        if (txtregistrodmte != null && swsegui == 0) {

            if (!cmbregistro.equals("")) {
                SignomarcaAux = sigSignoMarcaService.listaSigSignoMarcaXRegistro(Long.valueOf(txtregistrodmte), "", txamarcadmte);
            }
            Seguimiento seguimientoNuevoOpo = new Seguimiento();

            if (SignomarcaAux.getIdSignoMarca() != null) {
                seguimientoNuevoOpo.setId(SignomarcaAux.getIdSignoMarca());
            }
            seguimientoNuevoOpo.setIdUsuario(super.getIdUsuarioSession());
            String smaux1 = "0";
            if (txtexpedientesm != null && txtexpedientesm != 0 && txtgestionexpediente != null && txtgestionexpediente != 0 && txtextencionespediente != null && txtextencionespediente.equals("")) {
                if (txtextencionespediente != null && txtextencionespediente.equals("")) {
                    smaux1 = Long.toString(txtgestionexpediente) + oposicionService.completasmxnroexp(txtexpedientesm) + "00";
                } else {
                    smaux1 = Long.toString(txtgestionexpediente) + oposicionService.completasmxnroexp(txtexpedientesm) + txtextencionespediente;
                }
                Long smauxlon = Long.parseLong(smaux1);
                seguimientoNuevoOpo.setSm(smauxlon);
            }
            if (txtpubldmte != null) {
                seguimientoNuevoOpo.setNumeroPublicacion((txtpubldmte).longValue());
            }
            if (txtregistrodmte != null) {
                seguimientoNuevoOpo.setNumeroRegistro((txtregistrodmte).longValue());
            }
            if (!(cmbregistro).equals("")) {
                seguimientoNuevoOpo.setSerieRegistro(cmbregistro);
            }
            seguimientoNuevoOpo.setEtapa(listEtapaUs.get(0).getTipoEtapa());
            seguimientoNuevoOpo.setObservacion(null);
            seguimientoNuevoOpo.setEditable(false);
            seguimientoNuevoOpo.setEstado("AC");
            ultimoSeguimientoopo = seguimientoService.guardar_modificar_Seguimiento_etapa(seguimientoNuevoOpo, "SIG");
            swsegui = 1;
        }
        swsegui = 0;
*/
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "El tramite termino con Exito", ""));

        // return ("examenoposicion");
    }

    /**
     * para cambiar el estado en OPOSICION
     *
     * Autor: Luis Angel Quispe limachi
     *
     * Fecha 15/11/2016
     *
     * @param codigo
     * @return String
     */
    public void iniciarTramiteOpo() throws Exception {
        //Segmento para cambiar el estado de sigsignomarca
        Oposicion datosopomodiesta = new Oposicion();
         Date fechaSistema = comunService.obtenerFechaHoraServidor(1L);
                LogTrans logTransGuardado = logTransService.crudLogTrans(new LogTrans(idUsuarioSesion, fechaSistema), 1);
        if (txtnropublidmdo != null) {
            if (txtnropublidmdo != 0) {
                signomarcaActumarca = sigSignoMarcaService.listaSigSignoMarcaXPublicacion(txtnropublidmdo);
                if (!signomarcaActumarca.getEstadoMarca().equals("OPO")) {
                    signomarcaActumarca.setUbicacion("PIOP");
                    signomarcaActumarca.setEstadoMarca("OPO");
                    sigSignoMarcaService.crudSigSignoMarca(signomarcaActumarca, 2);
                }
                   if (txtpublidmdo != null) {
                    signomarcaActuObs = sigSignoMarcaService.listaSigSignoMarcaXPublicacion(txtpublidmdo);

                    long idsignomarca = signomarcaActuObs.getIdSignoMarca();
                    if (txamarcadmte != null && txamarcadmdo != null) {
                        marcasvs = txamarcadmte + "   V.s.  " + txamarcadmdo;
                    }
                    if (txamarcadmte == null) {
                        marcasvs = "" + "   V.s.  " + txamarcadmdo;
                    }
                    if (txamarcadmdo == null) {
                        marcasvs = txamarcadmte + "   V.s.  " + "";
                    }                  
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
                            sigmarcaObsNew.setDescripcion("-CASO(" + txtorden_o + "):" + marcasvs + " \n -ESTADO:" + " PRESENTADA \n -OBSERVACION: " + txaobservacion + " \n");
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
                            sigmarcaObsNew.setDescripcion(sigmarcaObs.getDescripcion() + "\n -CASO(" + txtorden_o + "):" + marcasvs + " \n -ESTADO:" + " PRESENTADA \n -OBSERVACION: " + txaobservacion + " \n");
                            observacionTramiteService.guardar_modificar_listar_ObservacionTramite(sigmarcaObsNew, "SIG", 1);
                        }
                    }                   
                }                

                Long numeropubx = oposicionService.encuentraclaveprin(txtnropublidmdo, txtorden_o);
                datosopomodiesta = oposicionService.obtoposicionxnroopo(numeropubx);
                datosopomodiesta.setIngreso_opo("IN");
                oposicionService.modificaOposicion(datosopomodiesta);

                btnInicioTramiteRendered = true;
                
                
                
                /*********************SEGUIMIENTO*************************************/
                System.out.println("this.txtpublidmdo::"+this.txtpublidmdo);
                if (this.txtpublidmdo != null && this.txtpublidmdo!=0) {
                        
                      SignomarcaAux = sigSignoMarcaService.listaSigSignoMarcaXPublicacion(Long.valueOf(txtpublidmdo));
                      System.out.println("seguimientoService.listaSeguimientoXIdSignomarca(SignomarcaAux.getIdSignoMarca()).get(0).getEtapa().equals(\"OPO\")"+seguimientoService.listaSeguimientoXIdSignomarca(SignomarcaAux.getIdSignoMarca()).get(0).getEtapa());
                      if(!seguimientoService.listaSeguimientoXIdSignomarca(SignomarcaAux.getIdSignoMarca()).get(0).getEtapa().equals("OPO"))  
                   //     System.out.println("SignomarcaAux::"+SignomarcaAux.getIdSignoMarca());
                      {  Seguimiento seguimientoNuevoOpo = new Seguimiento();

                         if (SignomarcaAux.getIdSignoMarca() != null) {
                             seguimientoNuevoOpo.setId(SignomarcaAux.getIdSignoMarca());
                         }
                         seguimientoNuevoOpo.setSm(SignomarcaAux.getSm());
                         seguimientoNuevoOpo.setIdUsuario(super.getIdUsuarioSession());
                         seguimientoNuevoOpo.setIdLogTrans(logTransGuardado.getIdLogTrans());

//                        if (enlazaobjetos.getObjmarcademandada().getDmdo_sm() != null) {
//                            seguimientoNuevoOpo.setSm(enlazaobjetos.getObjmarcademandada().getDmdo_sm());                           
//                        }
                         if (this.txtpublidmdo != null) {
                             seguimientoNuevoOpo.setNumeroPublicacion((txtpublidmdo).longValue());
                         }
                         seguimientoNuevoOpo.setEtapa(listEtapaUs.get(0).getTipoEtapa());
                         seguimientoNuevoOpo.setObservacion(null);
                         seguimientoNuevoOpo.setEditable(false);
                         seguimientoNuevoOpo.setEstado("AC");
                     //   System.out.println("Aqui entraaa......");
                         if (seguimientoService.listaSeguiUltimoXIdsignoEtapaUltMov(SignomarcaAux.getIdSignoMarca(), listEtapaUs.get(0).getTipoEtapa()) != null) {
                       //    System.out.println("Significa qeu si tiene mas de un examen de forma");
                            seguimientoNuevoOpo.setPlazo_limite(flujoTiempoService.obtieneFlujoXIdetapa(listEtapaUs.get(0).getTipoEtapa(), 2));
 
                          } else {
                         //   System.out.println("Significa qeu no tiene mas de un examen de forma");
                         //     System.out.println("tiempo" + flujoTiempoService.obtieneFlujoXIdetapa(listEtapaUs.get(0).getTipoEtapa(), 1));
                             seguimientoNuevoOpo.setPlazo_limite(flujoTiempoService.obtieneFlujoXIdetapa(listEtapaUs.get(0).getTipoEtapa(), 1));
                          }
                       // System.out.println("LLega aqui..");
                        ultimoSeguimientoopo = seguimientoService.guardar_modificar_Seguimiento_etapa(seguimientoNuevoOpo, "SIG");
                     //   swsegui = 1;

                    }
                }
                
                
                
                
                
                
                
                /********************************************************************/
                
                
                
            }
        }

    }

    //<editor-fold defaultstate="collapsed" desc="-----------------------------GET  SET----------------------">
    public void setNotificacionService(NotificacionService notificacionService) {
        this.notificacionService = notificacionService;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getTipoMarca() {
        return tipoMarca;
    }

    public void setTipoMarca(String tipoMarca) {
        this.tipoMarca = tipoMarca;
    }

    public String getValorRadio() {
        return valorRadio;
    }

    public void setValorRadio(String valorRadio) {
        this.valorRadio = valorRadio;
    }

    public String getValorint() {
        return valorint;
    }

    public void setValorint(String valorint) {
        this.valorint = valorint;
    }

    public Boolean getEncabezado() {
        return encabezado;
    }

    public void setEncabezado(Boolean encabezado) {
        this.encabezado = encabezado;
    }

    public List<String> getListado() {
        return listado;
    }

    public void setListado(List<String> listado) {
        this.listado = listado;
    }

    public String getCadena() {
        return cadena;
    }

    public void setCadena(String cadena) {
        this.cadena = cadena;
    }

    public String finalizarOposicion() {
        return "abrirPrincipal";
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public List<SelectItem> getCategories() {
        return categories;
    }

    public void setCategories(List<SelectItem> categories) {
        this.categories = categories;
    }

    public String getSelection() {
        return selection;
    }

    public void setSelection(String selection) {
        this.selection = selection;
    }

//    public SMDominioService getsMDominioService() {
//        return sMDominioService;
//    }
//
//    public void setsMDominioService(SMDominioService sMDominioService) {
//        this.sMDominioService = sMDominioService;
//    }
//
//    public List<SMDominio> getListaSMDominio() {
//        return listaSMDominio;
//    }
//
//    public void setListaSMDominio(List<SMDominio> listaSMDominio) {
//        this.listaSMDominio = listaSMDominio;
//    }
    public String getPais() {
        return Pais;
    }

    public void setPais(String Pais) {
        this.Pais = Pais;
    }

    public int getNumero1() {
        return numero1;
    }

    public void setNumero1(int numero1) {
        this.numero1 = numero1;
    }

    public int getExpediente() {
        return expediente;
    }

    public void setExpediente(int expediente) {
        this.expediente = expediente;
    }

    public String getDemandante() {
        return demandante;
    }

    public void setDemandante(String demandante) {
        this.demandante = demandante;
    }

    public List<SMOposicion> getListaOposicion() {
        return listaOposicion;
    }

    public void setListaOposicion(List<SMOposicion> listaOposicion) {
        this.listaOposicion = listaOposicion;
    }

    public List<Oposicionlist> getListaofi() {
        return listaofi;
    }

    public void setListaofi(List<Oposicionlist> listaofi) {
        this.listaofi = listaofi;
    }

    public int getValorPUB() {
        return valorPUB;
    }

    public void setValorPUB(int valorPUB) {
        this.valorPUB = valorPUB;
    }

    public Boolean getApareceTab() {
        return apareceTab;
    }

    public void setApareceTab(Boolean apareceTab) {
        this.apareceTab = apareceTab;
    }

    public String getValorREG() {
        return valorREG;
    }

    public void setValorREG(String valorREG) {
        this.valorREG = valorREG;
    }

    public String getValorEXP() {
        return valorEXP;
    }

    public void setValorEXP(String valorEXP) {
        this.valorEXP = valorEXP;
    }

    public String getValorMAR() {
        return valorMAR;
    }

    public void setValorMAR(String valorMAR) {
        this.valorMAR = valorMAR;
    }

    public String getValorDMO() {
        return valorDMO;
    }

    public void setValorDMO(String valorDMO) {
        this.valorDMO = valorDMO;
    }

    public String getValorDME() {
        return valorDME;
    }

    public void setValorDME(String valorDME) {
        this.valorDME = valorDME;
    }

    public String getValorAPO() {
        return valorAPO;
    }

    public void setValorAPO(String valorAPO) {
        this.valorAPO = valorAPO;
    }

    public String getValorX1() {
        return valorX1;
    }

    public void setValorX1(String valorX1) {
        this.valorX1 = valorX1;
    }

    public String getValorX2() {
        return valorX2;
    }

    public void setValorX2(String valorX2) {
        this.valorX2 = valorX2;
    }

    public String getValorX3() {
        return valorX3;
    }

    public void setValorX3(String valorX3) {
        this.valorX3 = valorX3;
    }

    public Boolean getVariadi() {
        return variadi;
    }

    public void setVariadi(Boolean variadi) {
        this.variadi = variadi;
    }

    public Boolean getVarieli() {
        return varieli;
    }

    public void setVarieli(Boolean varieli) {
        this.varieli = varieli;
    }

    public String getNomrad() {
        return nomrad;
    }

    public void setNomrad(String nomrad) {
        this.nomrad = nomrad;
    }

    public Boolean getVarimodi() {
        return varimodi;
    }

    public void setVarimodi(Boolean varimodi) {
        this.varimodi = varimodi;
    }

    public Boolean getVarisegui() {
        return varisegui;
    }

    public void setVarisegui(Boolean varisegui) {
        this.varisegui = varisegui;
    }

    public Boolean getVarihisto() {
        return varihisto;
    }

    public void setVarihisto(Boolean varihisto) {
        this.varihisto = varihisto;
    }

    public String getDirnotidmte() {
        return dirnotidmte;
    }

    public void setDirnotidmte(String dirnotidmte) {
        this.dirnotidmte = dirnotidmte;
    }

    public OpoMarcademandante getOpodemandante() {
        return opodemandante;
    }

    public void setOpodemandante(OpoMarcademandante opodemandante) {
        this.opodemandante = opodemandante;
    }

    public OpoMarcademandante getOpodemandanteok() {
        return opodemandanteok;
    }

    public void setOpodemandanteok(OpoMarcademandante opodemandanteok) {
        this.opodemandanteok = opodemandanteok;
    }

    public List<OpoMarcademandante> getListademantante() {
        return listademantante;
    }

    public void setListademantante(List<OpoMarcademandante> listademantante) {
        this.listademantante = listademantante;
    }

    public List<OpoSolicitanteapoderado> getListaapoderado() {
        return listaapoderado;
    }

    public void setListaapoderado(List<OpoSolicitanteapoderado> listaapoderado) {
        this.listaapoderado = listaapoderado;
    }

    public List<OpoMarcademandada> getListademandado() {
        return listademandado;
    }

    public void setListademandado(List<OpoMarcademandada> listademandado) {
        this.listademandado = listademandado;
    }

    public OpoNotificacionService getOpoNotificacionService() {
        return opoNotificacionService;
    }

    public void setOpoNotificacionService(OpoNotificacionService opoNotificacionService) {
        this.opoNotificacionService = opoNotificacionService;
    }

    public OpoGeneralService getOpoGeneralService() {
        return opoGeneralService;
    }

    public void setOpoGeneralService(OpoGeneralService opoGeneralService) {
        this.opoGeneralService = opoGeneralService;
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

    public OposicionService getOposicionService() {
        return oposicionService;
    }

    public void setOposicionService(OposicionService oposicionService) {
        this.oposicionService = oposicionService;
    }

    public OpoMarcademandadaService getOpoMarcaDemandadaService() {
        return opoMarcaDemandadaService;
    }

    public void setOpoMarcaDemandadaService(OpoMarcademandadaService opoMarcaDemandadaService) {
        this.opoMarcaDemandadaService = opoMarcaDemandadaService;
    }

    public OpoNotificacion getOponoti() {
        return oponoti;
    }

    public void setOponoti(OpoNotificacion oponoti) {
        this.oponoti = oponoti;
    }

    public String getNoticiudaddmte() {
        return noticiudaddmte;
    }

    public void setNoticiudaddmte(String noticiudaddmte) {
        this.noticiudaddmte = noticiudaddmte;
    }

    public String getNotizbarriodmte() {
        return notizbarriodmte;
    }

    public void setNotizbarriodmte(String notizbarriodmte) {
        this.notizbarriodmte = notizbarriodmte;
    }

    public String getNotiacalledmte() {
        return notiacalledmte;
    }

    public void setNotiacalledmte(String notiacalledmte) {
        this.notiacalledmte = notiacalledmte;
    }

    public String getNoticelulardmte() {
        return noticelulardmte;
    }

    public void setNoticelulardmte(String noticelulardmte) {
        this.noticelulardmte = noticelulardmte;
    }

    public String getNotinumerocasadmte() {
        return notinumerocasadmte;
    }

    public void setNotinumerocasadmte(String notinumerocasadmte) {
        this.notinumerocasadmte = notinumerocasadmte;
    }

    public String getNotiedificiodmte() {
        return notiedificiodmte;
    }

    public void setNotiedificiodmte(String notiedificiodmte) {
        this.notiedificiodmte = notiedificiodmte;
    }

    public String getNotipisodmte() {
        return notipisodmte;
    }

    public void setNotipisodmte(String notipisodmte) {
        this.notipisodmte = notipisodmte;
    }

    public String getNotinrodepartamentodmte() {
        return notinrodepartamentodmte;
    }

    public void setNotinrodepartamentodmte(String notinrodepartamentodmte) {
        this.notinrodepartamentodmte = notinrodepartamentodmte;
    }

    public String getNotiemaildmte() {
        return notiemaildmte;
    }

    public void setNotiemaildmte(String notiemaildmte) {
        this.notiemaildmte = notiemaildmte;
    }

    public Oposicion getDatosoposicion() {
        return datosoposicion;
    }

    public void setDatosoposicion(Oposicion datosoposicion) {
        this.datosoposicion = datosoposicion;
    }

    public OpoMarcademandada getDatosmarcademandada() {
        return datosmarcademandada;
    }

    public void setDatosmarcademandada(OpoMarcademandada datosmarcademandada) {
        this.datosmarcademandada = datosmarcademandada;
    }

    public OpoMarcademandante getDatosmarcademandante() {
        return datosmarcademandante;
    }

    public void setDatosmarcademandante(OpoMarcademandante datosmarcademandante) {
        this.datosmarcademandante = datosmarcademandante;
    }

    public OpoNotificacion getDatosnotificaciondmdo() {
        return datosnotificaciondmdo;
    }

    public void setDatosnotificaciondmdo(OpoNotificacion datosnotificaciondmdo) {
        this.datosnotificaciondmdo = datosnotificaciondmdo;
    }

    public OpoNotificacion getDatosnotificaciondmte() {
        return datosnotificaciondmte;
    }

    public void setDatosnotificaciondmte(OpoNotificacion datosnotificaciondmte) {
        this.datosnotificaciondmte = datosnotificaciondmte;
    }

    public List<OpoSolicitanteapoderado> getListasolidmdo() {
        return listasolidmdo;
    }

    public void setListasolidmdo(List<OpoSolicitanteapoderado> listasolidmdo) {
        this.listasolidmdo = listasolidmdo;
    }

    public List<OpoSolicitanteapoderado> getListaapodmdo() {
        return listaapodmdo;
    }

    public void setListaapodmdo(List<OpoSolicitanteapoderado> listaapodmdo) {
        this.listaapodmdo = listaapodmdo;
    }

    public List<OpoSolicitanteapoderado> getListasolidmte() {
        return listasolidmte;
    }

    public void setListasolidmte(List<OpoSolicitanteapoderado> listasolidmte) {
        this.listasolidmte = listasolidmte;
    }

    public List<OpoSolicitanteapoderado> getListaapodmte() {
        return listaapodmte;
    }

    public void setListaapodmte(List<OpoSolicitanteapoderado> listaapodmte) {
        this.listaapodmte = listaapodmte;
    }

    public OpoObjetoDmteDmdoNotiSoliapo getEnlazaobjetos() {
        return enlazaobjetos;
    }

    public void setEnlazaobjetos(OpoObjetoDmteDmdoNotiSoliapo enlazaobjetos) {
        this.enlazaobjetos = enlazaobjetos;
    }

    public Long getTxtnropublidmdo() {
        return txtnropublidmdo;
    }

    public void setTxtnropublidmdo(Long txtnropublidmdo) {
        this.txtnropublidmdo = txtnropublidmdo;
    }

    public Date getCldfechapresoposicion() {
        return cldfechapresoposicion;
    }

    public void setCldfechapresoposicion(Date cldfechapresoposicion) {
        this.cldfechapresoposicion = cldfechapresoposicion;
    }

    public Long getTxtpublidmdo() {
        return txtpublidmdo;
    }

    public void setTxtpublidmdo(Long txtpublidmdo) {
        this.txtpublidmdo = txtpublidmdo;
    }

    public Integer getTxtgacetadmdo() {
        return txtgacetadmdo;
    }

    public void setTxtgacetadmdo(Integer txtgacetadmdo) {
        this.txtgacetadmdo = txtgacetadmdo;
    }

    public Date getCldfecahpubdmdo() {
        return cldfecahpubdmdo;
    }

    public void setCldfecahpubdmdo(Date cldfecahpubdmdo) {
        this.cldfecahpubdmdo = cldfecahpubdmdo;
    }

    public String getTxamarcadmdo() {
        return txamarcadmdo;
    }

    public void setTxamarcadmdo(String txamarcadmdo) {
        this.txamarcadmdo = txamarcadmdo;
    }

    public String getTxtciudaddmdo() {
        return txtciudaddmdo;
    }

    public void setTxtciudaddmdo(String txtciudaddmdo) {
        this.txtciudaddmdo = txtciudaddmdo;
    }

    public String getTxtzonadmdo() {
        return txtzonadmdo;
    }

    public void setTxtzonadmdo(String txtzonadmdo) {
        this.txtzonadmdo = txtzonadmdo;
    }

    public String getTxtcalledmdo() {
        return txtcalledmdo;
    }

    public void setTxtcalledmdo(String txtcalledmdo) {
        this.txtcalledmdo = txtcalledmdo;
    }

    public String getTxtnrocasadmdo() {
        return txtnrocasadmdo;
    }

    public void setTxtnrocasadmdo(String txtnrocasadmdo) {
        this.txtnrocasadmdo = txtnrocasadmdo;
    }

    public String getTxtcelulardmdo() {
        return txtcelulardmdo;
    }

    public void setTxtcelulardmdo(String txtcelulardmdo) {
        this.txtcelulardmdo = txtcelulardmdo;
    }

    public String getTxtedificiodmdo() {
        return txtedificiodmdo;
    }

    public void setTxtedificiodmdo(String txtedificiodmdo) {
        this.txtedificiodmdo = txtedificiodmdo;
    }

    public String getTxtpisodmdo() {
        return txtpisodmdo;
    }

    public void setTxtpisodmdo(String txtpisodmdo) {
        this.txtpisodmdo = txtpisodmdo;
    }

    public String getTxtnrodepartamentodmdo() {
        return txtnrodepartamentodmdo;
    }

    public void setTxtnrodepartamentodmdo(String txtnrodepartamentodmdo) {
        this.txtnrodepartamentodmdo = txtnrodepartamentodmdo;
    }

    public String getTxtemaildmdo() {
        return txtemaildmdo;
    }

    public void setTxtemaildmdo(String txtemaildmdo) {
        this.txtemaildmdo = txtemaildmdo;
    }

    public Integer getTxtpubldmte() {
        return txtpubldmte;
    }

    public void setTxtpubldmte(Integer txtpubldmte) {
        this.txtpubldmte = txtpubldmte;
    }

    public Integer getTxtregistrodmte() {
        return txtregistrodmte;
    }

    public void setTxtregistrodmte(Integer txtregistrodmte) {
        this.txtregistrodmte = txtregistrodmte;
    }

    public String getTxtsmexpedientedmte() {
        return txtsmexpedientedmte;
    }

    public void setTxtsmexpedientedmte(String txtsmexpedientedmte) {
        this.txtsmexpedientedmte = txtsmexpedientedmte;
    }

    public String getCmdregistrodmte() {
        return cmdregistrodmte;
    }

    public void setCmdregistrodmte(String cmdregistrodmte) {
        this.cmdregistrodmte = cmdregistrodmte;
    }

    public String getTxamarcadmte() {
        return txamarcadmte;
    }

    public void setTxamarcadmte(String txamarcadmte) {
        this.txamarcadmte = txamarcadmte;
    }

    public String getTxtusodmte() {
        return txtusodmte;
    }

    public void setTxtusodmte(String txtusodmte) {
        this.txtusodmte = txtusodmte;
    }

    public String getTxtciudaddmte() {
        return txtciudaddmte;
    }

    public void setTxtciudaddmte(String txtciudaddmte) {
        this.txtciudaddmte = txtciudaddmte;
    }

    public String getTxtzonadmte() {
        return txtzonadmte;
    }

    public void setTxtzonadmte(String txtzonadmte) {
        this.txtzonadmte = txtzonadmte;
    }

    public String getTxtcalledmte() {
        return txtcalledmte;
    }

    public void setTxtcalledmte(String txtcalledmte) {
        this.txtcalledmte = txtcalledmte;
    }

    public String getTxtnrocasadmte() {
        return txtnrocasadmte;
    }

    public void setTxtnrocasadmte(String txtnrocasadmte) {
        this.txtnrocasadmte = txtnrocasadmte;
    }

    public String getTxtceludmte() {
        return txtceludmte;
    }

    public void setTxtceludmte(String txtceludmte) {
        this.txtceludmte = txtceludmte;
    }

    public String getTxtedificiodmte() {
        return txtedificiodmte;
    }

    public void setTxtedificiodmte(String txtedificiodmte) {
        this.txtedificiodmte = txtedificiodmte;
    }

    public String getTxtpisodmte() {
        return txtpisodmte;
    }

    public void setTxtpisodmte(String txtpisodmte) {
        this.txtpisodmte = txtpisodmte;
    }

    public String getTxtnrodepadmte() {
        return txtnrodepadmte;
    }

    public void setTxtnrodepadmte(String txtnrodepadmte) {
        this.txtnrodepadmte = txtnrodepadmte;
    }

    public String getTxtemaildmte() {
        return txtemaildmte;
    }

    public void setTxtemaildmte(String txtemaildmte) {
        this.txtemaildmte = txtemaildmte;
    }

    public OpoSolicitanteapoderado getObjetoAuxOposoli() {
        return ObjetoAuxOposoli;
    }

    public void setObjetoAuxOposoli(OpoSolicitanteapoderado ObjetoAuxOposoli) {
        this.ObjetoAuxOposoli = ObjetoAuxOposoli;
    }

    public String getNom_razonsocialsolidmte() {
        return nom_razonsocialsolidmte;
    }

    public void setNom_razonsocialsolidmte(String nom_razonsocialsolidmte) {
        this.nom_razonsocialsolidmte = nom_razonsocialsolidmte;
    }

    public String getPaissolidmte() {
        return paissolidmte;
    }

    public void setPaissolidmte(String paissolidmte) {
        this.paissolidmte = paissolidmte;
    }

    public String getSolicitud_depasolidmte() {
        return solicitud_depasolidmte;
    }

    public void setSolicitud_depasolidmte(String solicitud_depasolidmte) {
        this.solicitud_depasolidmte = solicitud_depasolidmte;
    }

    public String getDireccionsolidmte() {
        return direccionsolidmte;
    }

    public void setDireccionsolidmte(String direccionsolidmte) {
        this.direccionsolidmte = direccionsolidmte;
    }

    public String getTelefonosolidmte() {
        return telefonosolidmte;
    }

    public void setTelefonosolidmte(String telefonosolidmte) {
        this.telefonosolidmte = telefonosolidmte;
    }

    public String getFaxsolidmte() {
        return faxsolidmte;
    }

    public void setFaxsolidmte(String faxsolidmte) {
        this.faxsolidmte = faxsolidmte;
    }

    public String getEmailsolidmte() {
        return emailsolidmte;
    }

    public void setEmailsolidmte(String emailsolidmte) {
        this.emailsolidmte = emailsolidmte;
    }

    public String getNom_razonsocialapodmte() {
        return nom_razonsocialapodmte;
    }

    public void setNom_razonsocialapodmte(String nom_razonsocialapodmte) {
        this.nom_razonsocialapodmte = nom_razonsocialapodmte;
    }

    public String getPaisapodmte() {
        return paisapodmte;
    }

    public void setPaisapodmte(String paisapodmte) {
        this.paisapodmte = paisapodmte;
    }

    public String getSolicitud_depaapodmte() {
        return solicitud_depaapodmte;
    }

    public void setSolicitud_depaapodmte(String solicitud_depaapodmte) {
        this.solicitud_depaapodmte = solicitud_depaapodmte;
    }

    public String getDireccionapodmte() {
        return direccionapodmte;
    }

    public void setDireccionapodmte(String direccionapodmte) {
        this.direccionapodmte = direccionapodmte;
    }

    public String getTelefonoapodmte() {
        return telefonoapodmte;
    }

    public void setTelefonoapodmte(String telefonoapodmte) {
        this.telefonoapodmte = telefonoapodmte;
    }

    public String getFaxapodmte() {
        return faxapodmte;
    }

    public void setFaxapodmte(String faxapodmte) {
        this.faxapodmte = faxapodmte;
    }

    public String getEmailapodmte() {
        return emailapodmte;
    }

    public void setEmailapodmte(String emailapodmte) {
        this.emailapodmte = emailapodmte;
    }

    public String getNom_razonsocialsolidmda() {
        return nom_razonsocialsolidmda;
    }

    public void setNom_razonsocialsolidmda(String nom_razonsocialsolidmda) {
        this.nom_razonsocialsolidmda = nom_razonsocialsolidmda;
    }

    public String getPaissolidmda() {
        return paissolidmda;
    }

    public void setPaissolidmda(String paissolidmda) {
        this.paissolidmda = paissolidmda;
    }

    public String getSolicitud_depasolidmda() {
        return solicitud_depasolidmda;
    }

    public void setSolicitud_depasolidmda(String solicitud_depasolidmda) {
        this.solicitud_depasolidmda = solicitud_depasolidmda;
    }

    public String getDireccionsolidmda() {
        return direccionsolidmda;
    }

    public void setDireccionsolidmda(String direccionsolidmda) {
        this.direccionsolidmda = direccionsolidmda;
    }

    public String getTelefonosolidmda() {
        return telefonosolidmda;
    }

    public void setTelefonosolidmda(String telefonosolidmda) {
        this.telefonosolidmda = telefonosolidmda;
    }

    public String getFaxsolidmda() {
        return faxsolidmda;
    }

    public void setFaxsolidmda(String faxsolidmda) {
        this.faxsolidmda = faxsolidmda;
    }

    public String getEmailsolidmda() {
        return emailsolidmda;
    }

    public void setEmailsolidmda(String emailsolidmda) {
        this.emailsolidmda = emailsolidmda;
    }

    public String getNom_razonsocialopodmda() {
        return nom_razonsocialopodmda;
    }

    public void setNom_razonsocialopodmda(String nom_razonsocialopodmda) {
        this.nom_razonsocialopodmda = nom_razonsocialopodmda;
    }

    public String getPaisopodmda() {
        return paisopodmda;
    }

    public void setPaisopodmda(String paisopodmda) {
        this.paisopodmda = paisopodmda;
    }

    public String getSolicitud_depaopodmda() {
        return solicitud_depaopodmda;
    }

    public void setSolicitud_depaopodmda(String solicitud_depaopodmda) {
        this.solicitud_depaopodmda = solicitud_depaopodmda;
    }

    public String getDireccionopodmda() {
        return direccionopodmda;
    }

    public void setDireccionopodmda(String direccionopodmda) {
        this.direccionopodmda = direccionopodmda;
    }

    public String getTelefonoopodmda() {
        return telefonoopodmda;
    }

    public void setTelefonoopodmda(String telefonoopodmda) {
        this.telefonoopodmda = telefonoopodmda;
    }

    public String getFaxopodmda() {
        return faxopodmda;
    }

    public void setFaxopodmda(String faxopodmda) {
        this.faxopodmda = faxopodmda;
    }

    public String getEmailopodmda() {
        return emailopodmda;
    }

    public void setEmailopodmda(String emailopodmda) {
        this.emailopodmda = emailopodmda;
    }

    public Integer getIndice() {
        return indice;
    }

    public void setIndice(Integer indice) {
        this.indice = indice;
    }

    public OpoSolicitanteapoderado getObjetoAuxOposolidmda() {
        return ObjetoAuxOposolidmda;
    }

    public void setObjetoAuxOposolidmda(OpoSolicitanteapoderado ObjetoAuxOposolidmda) {
        this.ObjetoAuxOposolidmda = ObjetoAuxOposolidmda;
    }

    public OpoSolicitanteapoderado getOpoSolicitanteApoderadoAux() {
        return OpoSolicitanteApoderadoAux;
    }

    public void setOpoSolicitanteApoderadoAux(OpoSolicitanteapoderado OpoSolicitanteApoderadoAux) {
        this.OpoSolicitanteApoderadoAux = OpoSolicitanteApoderadoAux;
    }

    public Integer getIndice1() {
        return indice1;
    }

    public void setIndice1(Integer indice1) {
        this.indice1 = indice1;
    }

    public OpoSolicitanteapoderado getOpoSolicitanteApoderadoAux1() {
        return OpoSolicitanteApoderadoAux1;
    }

    public void setOpoSolicitanteApoderadoAux1(OpoSolicitanteapoderado OpoSolicitanteApoderadoAux1) {
        this.OpoSolicitanteApoderadoAux1 = OpoSolicitanteApoderadoAux1;
    }

    public Integer getIndice3() {
        return indice3;
    }

    public void setIndice3(Integer indice3) {
        this.indice3 = indice3;
    }

    public String getCmbregistro() {
        return cmbregistro;
    }

    public void setCmbregistro(String cmbregistro) {
        this.cmbregistro = cmbregistro;
    }

    public Long getTxtexpedientesm() {
        return txtexpedientesm;
    }

    public void setTxtexpedientesm(Long txtexpedientesm) {
        this.txtexpedientesm = txtexpedientesm;
    }

    public OpoMarcademandante getDatosmarcademandantexordenopo() {
        return datosmarcademandantexordenopo;
    }

    public void setDatosmarcademandantexordenopo(OpoMarcademandante datosmarcademandantexordenopo) {
        this.datosmarcademandantexordenopo = datosmarcademandantexordenopo;
    }

    public Oposicion getDatosoposicionbusqueda() {
        return datosoposicionbusqueda;
    }

    public void setDatosoposicionbusqueda(Oposicion datosoposicionbusqueda) {
        this.datosoposicionbusqueda = datosoposicionbusqueda;
    }

    public List<Oposicion> getListascasosadj() {
        return listascasosadj;
    }

    public void setListascasosadj(List<Oposicion> listascasosadj) {
        this.listascasosadj = listascasosadj;
    }

    public List<OpoMarcademandante> getListademandantevista() {
        return listademandantevista;
    }

    public void setListademandantevista(List<OpoMarcademandante> listademandantevista) {
        this.listademandantevista = listademandantevista;
    }

    public OpoMarcademandada getMarcadadavist() {
        return marcadadavist;
    }

    public void setMarcadadavist(OpoMarcademandada marcadadavist) {
        this.marcadadavist = marcadadavist;
    }

    public OpoNotificacion getNotificadmdavist() {
        return notificadmdavist;
    }

    public void setNotificadmdavist(OpoNotificacion notificadmdavist) {
        this.notificadmdavist = notificadmdavist;
    }

    public OpoMarcademandante getMarcadmtevist() {
        return marcadmtevist;
    }

    public void setMarcadmtevist(OpoMarcademandante marcadmtevist) {
        this.marcadmtevist = marcadmtevist;
    }

    public OpoNotificacion getNotificadmtevist() {
        return notificadmtevist;
    }

    public void setNotificadmtevist(OpoNotificacion notificadmtevist) {
        this.notificadmtevist = notificadmtevist;
    }

    public Oposicion getSelectedOpo() {
        return selectedOpo;
    }

    public void setSelectedOpo(Oposicion selectedOpo) {
        this.selectedOpo = selectedOpo;
    }

    public Integer getTxtorden_o() {
        return txtorden_o;
    }

    public void setTxtorden_o(Integer txtorden_o) {
        this.txtorden_o = txtorden_o;
    }

    public String getTxaobservacion() {
        return txaobservacion;
    }

    public void setTxaobservacion(String txaobservacion) {
        this.txaobservacion = txaobservacion;
    }

    public Oposicion getDatosoposicionmodi() {
        return datosoposicionmodi;
    }

    public void setDatosoposicionmodi(Oposicion datosoposicionmodi) {
        this.datosoposicionmodi = datosoposicionmodi;
    }

    public OpoMarcademandada getDatosmarcademandadamodi() {
        return datosmarcademandadamodi;
    }

    public void setDatosmarcademandadamodi(OpoMarcademandada datosmarcademandadamodi) {
        this.datosmarcademandadamodi = datosmarcademandadamodi;
    }

    public String getTxtdmdosm() {
        return txtdmdosm;
    }

    public void setTxtdmdosm(String txtdmdosm) {
        this.txtdmdosm = txtdmdosm;
    }

    public OpoMarcademandante getDatosmarcademandantemodi() {
        return datosmarcademandantemodi;
    }

    public void setDatosmarcademandantemodi(OpoMarcademandante datosmarcademandantemodi) {
        this.datosmarcademandantemodi = datosmarcademandantemodi;
    }

    public String getTxttelefonodmdo() {
        return txttelefonodmdo;
    }

    public void setTxttelefonodmdo(String txttelefonodmdo) {
        this.txttelefonodmdo = txttelefonodmdo;
    }

    public String getTxtreferenciadmdo() {
        return txtreferenciadmdo;
    }

    public void setTxtreferenciadmdo(String txtreferenciadmdo) {
        this.txtreferenciadmdo = txtreferenciadmdo;
    }

    public String getTxttelefonodmte() {
        return txttelefonodmte;
    }

    public void setTxttelefonodmte(String txttelefonodmte) {
        this.txttelefonodmte = txttelefonodmte;
    }

    public String getTxtreferenciadmte() {
        return txtreferenciadmte;
    }

    public void setTxtreferenciadmte(String txtreferenciadmte) {
        this.txtreferenciadmte = txtreferenciadmte;
    }

    public OpoNotificacion getDatosnotificaciondmdomodi() {
        return datosnotificaciondmdomodi;
    }

    public void setDatosnotificaciondmdomodi(OpoNotificacion datosnotificaciondmdomodi) {
        this.datosnotificaciondmdomodi = datosnotificaciondmdomodi;
    }

    public OpoNotificacion getDatosnotificaciondmtemodi() {
        return datosnotificaciondmtemodi;
    }

    public void setDatosnotificaciondmtemodi(OpoNotificacion datosnotificaciondmtemodi) {
        this.datosnotificaciondmtemodi = datosnotificaciondmtemodi;
    }

    public String getTxtestadodmdo() {
        return txtestadodmdo;
    }

    public void setTxtestadodmdo(String txtestadodmdo) {
        this.txtestadodmdo = txtestadodmdo;
    }

    public String getTxtestadodmte() {
        return txtestadodmte;
    }

    public void setTxtestadodmte(String txtestadodmte) {
        this.txtestadodmte = txtestadodmte;
    }

    public OpoEvento getDatosopoevento() {
        return datosopoevento;
    }

    public void setDatosopoevento(OpoEvento datosopoevento) {
        this.datosopoevento = datosopoevento;
    }

    public OpoFechalimite getDatosfechalimite() {
        return datosfechalimite;
    }

    public void setDatosfechalimite(OpoFechalimite datosfechalimite) {
        this.datosfechalimite = datosfechalimite;
    }

    public OpoHistorial getDatoshistorial() {
        return datoshistorial;
    }

    public void setDatoshistorial(OpoHistorial datoshistorial) {
        this.datoshistorial = datoshistorial;
    }

    public OpoEventoService getOpoEventoService() {
        return opoEventoService;
    }

    public void setOpoEventoService(OpoEventoService opoEventoService) {
        this.opoEventoService = opoEventoService;
    }

    public OpoFechaLimiteService getOpoFechaLimiteService() {
        return opoFechaLimiteService;
    }

    public void setOpoFechaLimiteService(OpoFechaLimiteService opoFechaLimiteService) {
        this.opoFechaLimiteService = opoFechaLimiteService;
    }

    public OpoHistorialService getOpoHistorialService() {
        return opoHistorialService;
    }

    public void setOpoHistorialService(OpoHistorialService opoHistorialService) {
        this.opoHistorialService = opoHistorialService;
    }

    public OpoActividadService getOpoActividadService() {
        return opoActividadService;
    }

    public void setOpoActividadService(OpoActividadService opoActividadService) {
        this.opoActividadService = opoActividadService;
    }

    public OpoEvento getEventodemarca() {
        return eventodemarca;
    }

    public void setEventodemarca(OpoEvento eventodemarca) {
        this.eventodemarca = eventodemarca;
    }

    public String getTxtestadotramite() {
        return txtestadotramite;
    }

    public void setTxtestadotramite(String txtestadotramite) {
        this.txtestadotramite = txtestadotramite;
    }

    public String getTxtultimactividad() {
        return txtultimactividad;
    }

    public void setTxtultimactividad(String txtultimactividad) {
        this.txtultimactividad = txtultimactividad;
    }

    public OpoEstadoService getOpoEstadoService() {
        return opoEstadoService;
    }

    public void setOpoEstadoService(OpoEstadoService opoEstadoService) {
        this.opoEstadoService = opoEstadoService;
    }

    public SigSignoMarcaService getSigSignoMarcaService() {
        return sigSignoMarcaService;
    }

    public void setSigSignoMarcaService(SigSignoMarcaService sigSignoMarcaService) {
        this.sigSignoMarcaService = sigSignoMarcaService;
    }

    public SigSolicitanteApoderadoService getSigSolicitanteApoderadoService() {
        return sigSolicitanteApoderadoService;
    }

    public void setSigSolicitanteApoderadoService(SigSolicitanteApoderadoService sigSolicitanteApoderadoService) {
        this.sigSolicitanteApoderadoService = sigSolicitanteApoderadoService;
    }

    public SigDireccionDeNotificacionService getSigDireccionDeNotificacionService() {
        return sigDireccionDeNotificacionService;
    }

    public void setSigDireccionDeNotificacionService(SigDireccionDeNotificacionService sigDireccionDeNotificacionService) {
        this.sigDireccionDeNotificacionService = sigDireccionDeNotificacionService;
    }

    public SigDireccionDeNotificacion getNotificaciondatossig() {
        return notificaciondatossig;
    }

    public void setNotificaciondatossig(SigDireccionDeNotificacion notificaciondatossig) {
        this.notificaciondatossig = notificaciondatossig;
    }

    public SigDireccionDeNotificacion getNotificaciondatossigdmte() {
        return notificaciondatossigdmte;
    }

    public void setNotificaciondatossigdmte(SigDireccionDeNotificacion notificaciondatossigdmte) {
        this.notificaciondatossigdmte = notificaciondatossigdmte;
    }

    public List<Dominio> getLstSerieRegistro() {
        return lstSerieRegistro;
    }

    public void setLstSerieRegistro(List<Dominio> lstSerieRegistro) {
        this.lstSerieRegistro = lstSerieRegistro;
    }

    public DominioService getSmDominioService() {
        return smDominioService;
    }

    public void setSmDominioService(DominioService smDominioService) {
        this.smDominioService = smDominioService;
    }

    public DominioService getDominioService() {
        return dominioService;
    }

    public void setDominioService(DominioService dominioService) {
        this.dominioService = dominioService;
    }

    public Long getTxtgestionexpediente() {
        return txtgestionexpediente;
    }

    public void setTxtgestionexpediente(Long txtgestionexpediente) {
        this.txtgestionexpediente = txtgestionexpediente;
    }

    public String getTxtextencionespediente() {
        return txtextencionespediente;
    }

    public void setTxtextencionespediente(String txtextencionespediente) {
        this.txtextencionespediente = txtextencionespediente;
    }

    public ComunService getComunService() {
        return comunService;
    }

    public void setComunService(ComunService comunService) {
        this.comunService = comunService;
    }

    public String getCmbregistrobusqueda() {
        return cmbregistrobusqueda;
    }

    public void setCmbregistrobusqueda(String cmbregistrobusqueda) {
        this.cmbregistrobusqueda = cmbregistrobusqueda;
    }

    public String getCmbtipodmdodmte() {
        return cmbtipodmdodmte;
    }

    public void setCmbtipodmdodmte(String cmbtipodmdodmte) {
        this.cmbtipodmdodmte = cmbtipodmdodmte;
    }

    public List<Dominio> getLstDmteDmdo() {
        return lstDmteDmdo;
    }

    public void setLstDmteDmdo(List<Dominio> lstDmteDmdo) {
        this.lstDmteDmdo = lstDmteDmdo;
    }

    public List<Oposicion> getListasresultados() {
        return listasresultados;
    }

    public void setListasresultados(List<Oposicion> listasresultados) {
        this.listasresultados = listasresultados;
    }

    public Long getTxtpublicabusqueda() {
        return txtpublicabusqueda;
    }

    public void setTxtpublicabusqueda(Long txtpublicabusqueda) {
        this.txtpublicabusqueda = txtpublicabusqueda;
    }

    public List<Oposicion> getListaxdmte() {
        return listaxdmte;
    }

    public void setListaxdmte(List<Oposicion> listaxdmte) {
        this.listaxdmte = listaxdmte;
    }

    public Integer getTxtregistrobusqueda() {
        return txtregistrobusqueda;
    }

    public void setTxtregistrobusqueda(Integer txtregistrobusqueda) {
        this.txtregistrobusqueda = txtregistrobusqueda;
    }

    public Long getTxtexpedientebusqueda() {
        return txtexpedientebusqueda;
    }

    public void setTxtexpedientebusqueda(Long txtexpedientebusqueda) {
        this.txtexpedientebusqueda = txtexpedientebusqueda;
    }

    public String getTxtmarcabusqueda() {
        return txtmarcabusqueda;
    }

    public void setTxtmarcabusqueda(String txtmarcabusqueda) {
        this.txtmarcabusqueda = txtmarcabusqueda;
    }

    public String getTxtdemandantebusqueda() {
        return txtdemandantebusqueda;
    }

    public void setTxtdemandantebusqueda(String txtdemandantebusqueda) {
        this.txtdemandantebusqueda = txtdemandantebusqueda;
    }

    public String getTxtapoderadobusqueda() {
        return txtapoderadobusqueda;
    }

    public void setTxtapoderadobusqueda(String txtapoderadobusqueda) {
        this.txtapoderadobusqueda = txtapoderadobusqueda;
    }

    public Date getCldfecahpubbusqueda() {
        return cldfecahpubbusqueda;
    }

    public void setCldfecahpubbusqueda(Date cldfecahpubbusqueda) {
        this.cldfecahpubbusqueda = cldfecahpubbusqueda;
    }

    public String getNombrefechabusqueda() {
        return nombrefechabusqueda;
    }

    public void setNombrefechabusqueda(String nombrefechabusqueda) {
        this.nombrefechabusqueda = nombrefechabusqueda;
    }

    public List<OpoEvento> getListaactividades() {
        return listaactividades;
    }

    public void setListaactividades(List<OpoEvento> listaactividades) {
        this.listaactividades = listaactividades;
    }

    public OpoMarcademandada getDatosmarcademandadamodigral() {
        return datosmarcademandadamodigral;
    }

    public void setDatosmarcademandadamodigral(OpoMarcademandada datosmarcademandadamodigral) {
        this.datosmarcademandadamodigral = datosmarcademandadamodigral;
    }

    public String getBanderadmtedmdo() {
        return banderadmtedmdo;
    }

    public void setBanderadmtedmdo(String banderadmtedmdo) {
        this.banderadmtedmdo = banderadmtedmdo;
    }

    public Integer getIndice2() {
        return indice2;
    }

    public void setIndice2(Integer indice2) {
        this.indice2 = indice2;
    }

    public OpoSolicitanteapoderado getObjetoAuxOposoliApo() {
        return ObjetoAuxOposoliApo;
    }

    public void setObjetoAuxOposoliApo(OpoSolicitanteapoderado ObjetoAuxOposoliApo) {
        this.ObjetoAuxOposoliApo = ObjetoAuxOposoliApo;
    }

    public Oposicion getDatoshistorialaux() {
        return datoshistorialaux;
    }

    public void setDatoshistorialaux(Oposicion datoshistorialaux) {
        this.datoshistorialaux = datoshistorialaux;
    }

    public OpoHistorial getDatoshistorialoposicion() {
        return datoshistorialoposicion;
    }

    public void setDatoshistorialoposicion(OpoHistorial datoshistorialoposicion) {
        this.datoshistorialoposicion = datoshistorialoposicion;
    }

    public OpoMarcademandada getDatoshistorialauxmarcademandada() {
        return datoshistorialauxmarcademandada;
    }

    public void setDatoshistorialauxmarcademandada(OpoMarcademandada datoshistorialauxmarcademandada) {
        this.datoshistorialauxmarcademandada = datoshistorialauxmarcademandada;
    }

    public OpoMarcademandante getDatoshistorialauxmarcademandante() {
        return datoshistorialauxmarcademandante;
    }

    public void setDatoshistorialauxmarcademandante(OpoMarcademandante datoshistorialauxmarcademandante) {
        this.datoshistorialauxmarcademandante = datoshistorialauxmarcademandante;
    }

    public Long getClavepriok() {
        return clavepriok;
    }

    public void setClavepriok(Long clavepriok) {
        this.clavepriok = clavepriok;
    }

    public String getTxtnrogestionres() {
        return txtnrogestionres;
    }

    public void setTxtnrogestionres(String txtnrogestionres) {
        this.txtnrogestionres = txtnrogestionres;
    }

    public String getTxtopores() {
        return txtopores;
    }

    public void setTxtopores(String txtopores) {
        this.txtopores = txtopores;
    }

    public String getTxtsigres() {
        return txtsigres;
    }

    public void setTxtsigres(String txtsigres) {
        this.txtsigres = txtsigres;
    }

    public String getTxtcancelares() {
        return txtcancelares;
    }

    public void setTxtcancelares(String txtcancelares) {
        this.txtcancelares = txtcancelares;
    }

    public String getTxtnrogestionrecurso() {
        return txtnrogestionrecurso;
    }

    public void setTxtnrogestionrecurso(String txtnrogestionrecurso) {
        this.txtnrogestionrecurso = txtnrogestionrecurso;
    }

    public String getTxttiporecurso() {
        return txttiporecurso;
    }

    public void setTxttiporecurso(String txttiporecurso) {
        this.txttiporecurso = txttiporecurso;
    }

    public String getTxtresolucionrecurso() {
        return txtresolucionrecurso;
    }

    public void setTxtresolucionrecurso(String txtresolucionrecurso) {
        this.txtresolucionrecurso = txtresolucionrecurso;
    }

    public OpoResolucion getDatosresolucion() {
        return datosresolucion;
    }

    public void setDatosresolucion(OpoResolucion datosresolucion) {
        this.datosresolucion = datosresolucion;
    }

    public OpoResolucionService getOpoResolucionService() {
        return opoResolucionService;
    }

    public void setOpoResolucionService(OpoResolucionService opoResolucionService) {
        this.opoResolucionService = opoResolucionService;
    }

    public List<OpoRecurso> getListarecursos() {
        return listarecursos;
    }

    public void setListarecursos(List<OpoRecurso> listarecursos) {
        this.listarecursos = listarecursos;
    }

    public OpoRecursoService getOpoRecursoService() {
        return opoRecursoService;
    }

    public void setOpoRecursoService(OpoRecursoService opoRecursoService) {
        this.opoRecursoService = opoRecursoService;
    }

    public Long getTxtexpedientebusquedagestion() {
        return txtexpedientebusquedagestion;
    }

    public void setTxtexpedientebusquedagestion(Long txtexpedientebusquedagestion) {
        this.txtexpedientebusquedagestion = txtexpedientebusquedagestion;
    }

    public Calendar getGregfech() {
        return gregfech;
    }

    public void setGregfech(Calendar gregfech) {
        this.gregfech = gregfech;
    }

    public List<BusquedaOposicion> getListaBusquedaOposicion() {
        return listaBusquedaOposicion;
    }

    public void setListaBusquedaOposicion(List<BusquedaOposicion> listaBusquedaOposicion) {
        this.listaBusquedaOposicion = listaBusquedaOposicion;
    }

    public BusquedaOposicionService getBusquedaOposicionService() {
        return busquedaOposicionService;
    }

    public void setBusquedaOposicionService(BusquedaOposicionService busquedaOposicionService) {
        this.busquedaOposicionService = busquedaOposicionService;
    }

    public Date getCldfechaultimaactividad() {
        return cldfechaultimaactividad;
    }

    public void setCldfechaultimaactividad(Date cldfechaultimaactividad) {
        this.cldfechaultimaactividad = cldfechaultimaactividad;
    }

    public Integer getTxtpublidmdoclon() {
        return txtpublidmdoclon;
    }

    public void setTxtpublidmdoclon(Integer txtpublidmdoclon) {
        this.txtpublidmdoclon = txtpublidmdoclon;
    }

    public Integer getNroulti() {
        return nroulti;
    }

    public void setNroulti(Integer nroulti) {
        this.nroulti = nroulti;
    }

//</editor-fold>   
    public Long getIdUsuarioSesion() {
        return idUsuarioSesion;
    }

    public void setIdUsuarioSesion(Long idUsuarioSesion) {
        this.idUsuarioSesion = idUsuarioSesion;
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

    public List<Dominio> getLstCiudadNotificacion() {
        return lstCiudadNotificacion;
    }

    public void setLstCiudadNotificacion(List<Dominio> lstCiudadNotificacion) {
        this.lstCiudadNotificacion = lstCiudadNotificacion;
    }

    public Seguimiento getUltimoSeguimiento() {
        return ultimoSeguimiento;
    }

    public void setUltimoSeguimiento(Seguimiento ultimoSeguimiento) {
        this.ultimoSeguimiento = ultimoSeguimiento;
    }

    public SigSignoMarca getSignomarcaAux() {
        return SignomarcaAux;
    }

    public void setSignomarcaAux(SigSignoMarca SignomarcaAux) {
        this.SignomarcaAux = SignomarcaAux;
    }

    public String getSmdmte() {
        return smdmte;
    }

    public void setSmdmte(String smdmte) {
        this.smdmte = smdmte;
    }

    public Integer getNroopodmteaux() {
        return nroopodmteaux;
    }

    public void setNroopodmteaux(Integer nroopodmteaux) {
        this.nroopodmteaux = nroopodmteaux;
    }

    public List<Etapa> getListEtapaUs() {
        return listEtapaUs;
    }

    public void setListEtapaUs(List<Etapa> listEtapaUs) {
        this.listEtapaUs = listEtapaUs;
    }

    public EtapaService getEtapaService() {
        return etapaService;
    }

    public void setEtapaService(EtapaService etapaService) {
        this.etapaService = etapaService;
    }

    public Seguimiento getUltimoSeguimientoopo() {
        return ultimoSeguimientoopo;
    }

    public void setUltimoSeguimientoopo(Seguimiento ultimoSeguimientoopo) {
        this.ultimoSeguimientoopo = ultimoSeguimientoopo;
    }

    public SeguimientoService getSeguimientoService() {
        return seguimientoService;
    }

    public void setSeguimientoService(SeguimientoService seguimientoService) {
        this.seguimientoService = seguimientoService;
    }

    public OpoMarcademandante getMarcadmteseguimiento() {
        return marcadmteseguimiento;
    }

    public void setMarcadmteseguimiento(OpoMarcademandante marcadmteseguimiento) {
        this.marcadmteseguimiento = marcadmteseguimiento;
    }

    public SigSignoMarca getSignomarcaAuxiliar() {
        return SignomarcaAuxiliar;
    }

    public void setSignomarcaAuxiliar(SigSignoMarca SignomarcaAuxiliar) {
        this.SignomarcaAuxiliar = SignomarcaAuxiliar;
    }

    public Integer getTxt_auxorden() {
        return txt_auxorden;
    }

    public void setTxt_auxorden(Integer txt_auxorden) {
        this.txt_auxorden = txt_auxorden;
    }

    public Boolean getBtnSeguimientoOpoRendered() {
        return btnSeguimientoOpoRendered;
    }

    public void setBtnSeguimientoOpoRendered(Boolean btnSeguimientoOpoRendered) {
        this.btnSeguimientoOpoRendered = btnSeguimientoOpoRendered;
    }

    public List<Seguimiento> getListastaseg() {
        return listastaseg;
    }

    public void setListastaseg(List<Seguimiento> listastaseg) {
        this.listastaseg = listastaseg;
    }

    public SigSignoMarca getSignomarcaActumarca() {
        return signomarcaActumarca;
    }

    public void setSignomarcaActumarca(SigSignoMarca signomarcaActumarca) {
        this.signomarcaActumarca = signomarcaActumarca;
    }

    public SigSignoMarca getSignomarcaActuObs() {
        return signomarcaActuObs;
    }

    public void setSignomarcaActuObs(SigSignoMarca signomarcaActuObs) {
        this.signomarcaActuObs = signomarcaActuObs;
    }

    public ObservacionTramiteService getObservacionTramiteService() {
        return observacionTramiteService;
    }

    public void setObservacionTramiteService(ObservacionTramiteService observacionTramiteService) {
        this.observacionTramiteService = observacionTramiteService;
    }

    public ObservacionTramite getSigmarcaObs() {
        return sigmarcaObs;
    }

    public void setSigmarcaObs(ObservacionTramite sigmarcaObs) {
        this.sigmarcaObs = sigmarcaObs;
    }

    public ObservacionTramite getSigmarcaObsEdit() {
        return sigmarcaObsEdit;
    }

    public void setSigmarcaObsEdit(ObservacionTramite sigmarcaObsEdit) {
        this.sigmarcaObsEdit = sigmarcaObsEdit;
    }

    public NotificacionService getNotificacionService() {
        return notificacionService;
    }

    public Boolean getBtnInicioTramiteRendered() {
        return btnInicioTramiteRendered;
    }

    public void setBtnInicioTramiteRendered(Boolean btnInicioTramiteRendered) {
        this.btnInicioTramiteRendered = btnInicioTramiteRendered;
    }

    public String getMarcasvs() {
        return marcasvs;
    }

    public void setMarcasvs(String marcasvs) {
        this.marcasvs = marcasvs;
    }

    public FlujoTiempoService getFlujoTiempoService() {
        return flujoTiempoService;
    }

    public void setFlujoTiempoService(FlujoTiempoService flujoTiempoService) {
        this.flujoTiempoService = flujoTiempoService;
    }

}
