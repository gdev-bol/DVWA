/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.entidades.notificacion;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.ParseException;
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
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
//import org.apache.commons.io.FileUtils;
import org.apache.tools.ant.util.FileUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import snp.gob.bo.entidades.bean.common.AbstractManagedBean;
import snp.gob.bo.gimodel.entidad.Dominio;
import snp.gob.bo.gimodel.entidad.LogTrans;
import snp.gob.bo.gimodel.entidad.ModModificacion;
import snp.gob.bo.gimodel.entidad.Notificacion;
import snp.gob.bo.gimodel.entidad.RenSolicitudRenovacion;
import snp.gob.bo.gimodel.entidad.Seguimiento;
import snp.gob.bo.gimodel.entidad.SigSignoMarca;
import snp.gob.bo.gimodel.entidad.Usuario;
import snp.gob.bo.gimodel.servicio.ComunService;
import snp.gob.bo.gimodel.servicio.DominioService;
import snp.gob.bo.gimodel.servicio.LogTransService;
import snp.gob.bo.gimodel.servicio.ModModificacionService;
import snp.gob.bo.gimodel.servicio.NotificacionService;
import snp.gob.bo.gimodel.servicio.RenSolicitudRenovacionService;
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
public class NotiPeticionDialogController extends AbstractManagedBean implements Serializable {

    private Integer numIntoBloque;
    private String descripcion;
    private String demanSolicitante;
    private String demanApoderado;
    private String demanFojas;
    private String demanNotificarCon;
    private String demanCelular;
    private String demanDomicilio;
    private String creaModifieli;
    private String expediente;
    private boolean habAdiciona;
    private boolean habModifica;
    private boolean habElimina;
    private boolean habNotifica;
    private boolean habBuscar;
    private boolean habGuardaCamb;
    private Integer pos;
    private Date fechaIngreso;

    private String numero;
    private String gestion;
    private String extension;
    private boolean habOposocion = true;

    private String danteMarca;
    private String danteSolicitante;
    private String danteApoderado;
    private String danteFojas;
    private String danteNotificarCon;
    private String danteCelular;
    private String danteDomicilio;

    private String dadoMarca;
    private String dadoSolicitante;
    private String dadoApoderado;
    private String dadoFojas;
    private String dadoNotificarCon;
    private String dadoCelular;
    private String dadoDomicilio;
    private String var;

    private String template;
    private String codigoPrin = ""; //Codgio con el que se busca todo , puede ser idTramite
    private String tipoTramite = "";//Con este se ve si es de signos o renovaciones o modificaciones o oposicion
    private String comboExpediente;
    private String obsPeticion;
    private String text;
    private Integer numBloque;
    private Integer numBloqueBusca;

    private Long idUsuarioSesion;
    private List<Dominio> listaTipoNotifi = new ArrayList<Dominio>();
    private List<Notificacion> listaAPeticion = new ArrayList<Notificacion>();
    private List<Notificacion> listaEscoge = new ArrayList<Notificacion>();
    List<HistorialPojo> listHistPo = new ArrayList<HistorialPojo>();
    private List<Usuario> listUsuario = new ArrayList<Usuario>();

    // parametros del reporte
    private Map<String, Object> parametros = new HashMap<String, Object>();
    private String nroBloque;
    private String operador;
    private String usuario;
    private String fechaIng;
    private String horaIng;
    private String imgSenapi;
    private Long logtrans;
    private List<NotiPeticionListPojo> listNotiPeticionList = new ArrayList<NotiPeticionListPojo>();
    private List<Notificacion> listaAPeticionRepo = new ArrayList<>();

    private boolean habImpr;
    @ManagedProperty(value = "#{usuarioPaginaService}")
    private UsuarioPaginaService usuarioPaginaService;
    @ManagedProperty(value = "#{notificacionService}")
    private NotificacionService notificacionService;
    @ManagedProperty(value = "#{dominioService}")
    private DominioService dominioService;
    @ManagedProperty(value = "#{comunService}")
    private ComunService comunService;
    @ManagedProperty(value = "#{usuarioService}")
    private UsuarioService usuarioService;
    @ManagedProperty(value = "#{logTransService}")
    private LogTransService logTransService;
    @ManagedProperty(value = "#{sigSignoMarcaService}")
    private SigSignoMarcaService sigSignoMarcaService;
    @ManagedProperty(value = "#{sigHistorialService}")
    private SigHistorialService sigHistorialService;

    @ManagedProperty(value = "#{modModificacionService}")
    private ModModificacionService modModificacionService;
    @ManagedProperty(value = "#{renSolicitudRenovacionService}")
    private RenSolicitudRenovacionService renSolicitudRenovacionService;

    @PostConstruct
    public void init() {

        try {
            /*estas dos lineas de codigo de abajo  reflejan que  se puede sacar texto html de un archivo, par que lo plasme  en el objeto editor de primefaces,
             y de porsi el primefaces ya lo  formatea
             */
            /*String varhtml = FileUtils.readFully(new FileReader(new File("/home/levi/Documentos/PROYECTOS/SISInterno/SistemaInterno/giview/src/main/java/snp/gob/bo/entidades/notificacion/html_1.txt")));
             text=varhtml;
            
             */
            /*el codigo de abajo es para definir  desde base  si este boton esta habilitado o no*/
           // habImpr=usuarioPaginaService.estadoBotonUsuario(super.getIdUsuarioSession().toString(), "btnNotificaImpr");

            System.out.println("ID USUARIO::" + super.getIdUsuarioSession());
            System.out.println("id etapa:::" + super.getIdEtapaSession());
            idUsuarioSesion = super.getIdUsuarioSession();
            numBloque = notificacionService.obtieneBloqueNuevo(idUsuarioSesion.intValue());
            //System.out.println("numBloque es:"+numBloque);
            //Estas 4 lineas de aqui abajo espara que saque  el idlogtranas y se intserte en tabla de notificaicon
            Date fechaSistema = comunService.obtenerFechaHoraServidor(1L);
            List<Usuario> listUsuario = usuarioService.listaUsuarioXidPagina(idUsuarioSesion);
            LogTrans logTransGuardado = logTransService.crudLogTrans(new LogTrans(idUsuarioSesion, fechaSistema), 1);
            logtrans = logTransGuardado.getIdLogTrans();

            if (numBloque == null) {
                numBloque = 1;

            }
            listaTipoNotifi = dominioService.obtenerListadoDominio("tipo_tramite_notificacion");

            Map<String, String[]> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterValuesMap();
            if (params.get("datosGenerales") == null || params.get("datosGenerales").equals("")) {
                template = "./../WEB-INF/facelets/templates/Template.xhtml";

            } else {

                template = "./../WEB-INF/facelets/templates/DialogTemplate.xhtml";
                codigoPrin = params.get("datosGenerales")[0];
                tipoTramite = params.get("datosGenerales")[1];
                System.out.println("codigoPrin:" + codigoPrin);
                System.out.println("tipoTramite:" + tipoTramite);
                Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

                Notificacion notifica = (Notificacion) sessionMap.get("notifiObj");
                if (!tipoTramite.equals("oposicion")) {
                    habOposocion = true;

                    //Aqui llenaria según lo que me pasen ya sea en un objeto o en variables
                    comboExpediente = notifica.getTipo_tramite_notificacion();

                    numero = notifica.getExpediente();
                    if (notifica.getGestion() == null || notifica.getGestion().equals("")) {
                        gestion = "";
                    } else {
                        gestion = notifica.getGestion().toString();
                    }

                    extension = notifica.getExtension();
                    descripcion = notifica.getDemandante();
                    demanSolicitante = notifica.getDemandante_solic();
                    demanApoderado = notifica.getDemandante_apod();
                    demanFojas = notifica.getDemandante_fojas();
                    demanNotificarCon = notifica.getDemandante_con();
                    demanCelular = notifica.getDemandante_cel();
                    demanDomicilio = notifica.getDemandante_direc();
                } else {
                    System.out.println("Es oposicion::" + comboExpediente);
                    habOposocion = false;
                    comboExpediente = "OPOSICION SIG";
                    numero = notifica.getExpediente();
                    extension = notifica.getExtension();
                    danteMarca = notifica.getDemandante();
                    danteSolicitante = notifica.getDemandante_solic();
                    danteApoderado = notifica.getDemandante_apod();
                    danteFojas = notifica.getDemandante_fojas();
                    danteNotificarCon = notifica.getDemandante_con();
                    danteCelular = notifica.getDemandante_cel();
                    danteDomicilio = notifica.getDemandante_direc();

                    dadoMarca = notifica.getDemandado();
                    dadoSolicitante = notifica.getDemandado_solic();
                    dadoApoderado = notifica.getDemandado_apod();
                    dadoFojas = notifica.getDemandado_fojas();
                    dadoNotificarCon = notifica.getDemandado_con();
                    dadoCelular = notifica.getDemandado_cel();
                    dadoDomicilio = notifica.getDemandado_direc();

                }
                sessionMap.remove("notifiObj");

            }

            fechaIngreso = new Date();
            numIntoBloque = 1;
            creaModifieli = "1";
            habModifica = false;
            habAdiciona = false;
            habElimina = false;
            habBuscar = true;

            /**
             * *****estos dato son para exclusivamente llenar
             * oposiciones*******
             */
            /* danteMarca = "AVG";
             danteSolicitante = "Marcelo Saldaña";
             danteApoderado = "Victor Cruz";
             danteFojas = "10";
             danteNotificarCon = "Decreto";
             danteCelular = "2484848";
             danteDomicilio = "LA PAZ, JUANA AZURDUY DE PADILLA, ROSENDO GUTIERREZ, 1000";
            
             dadoMarca = "AVG";
             dadoSolicitante = "Sergio Estrada";
             dadoApoderado = "Juan Manuel SOlorzano";
             dadoFojas = "12";
             dadoNotificarCon = "Decreto";
             dadoCelular = "22342342";
             dadoDomicilio = "LA PAZ, Zona Alto Tejar Calle las Palomillas, 213";*/
        } catch (Exception ex) {
            Logger.getLogger(NotiPeticionDialogController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    //Metodo cuando apreta Buscar en la parte superior primera
    public void buscaModi() throws Exception {
        listaAPeticion.clear();
        //Actualiza en numero de bloque que se escogio, y su correlativo del tramite a la lista de la tabla derecha

        numBloque = numBloqueBusca;
        listaAPeticion = notificacionService.getNotificacionXbloqueXusuariosol(numBloqueBusca, idUsuarioSesion);//Elk idUsuario A Revisar

        numIntoBloque = (Integer) listaAPeticion.size() + 1;
        //Pregunto por opcion de  modificar ya que tambien existe la opcion consulta
        if (creaModifieli.equals("2")) {

            for (int i = 0; i <= listaAPeticion.size() - 1; i++) {
                //Pregunto por preenviado , basta que encuentre uno para que se deshabilite el boton de  "Adicionar" ya que cuando esta en etapa de modificacion y 
                // en estado   enviado no puede adicionar mas tramites. Iual lo hace el sistema antiguo.
                if (listaAPeticion.get(i).getDemandante_cod_estado().equals("PREE")) {
                    habAdiciona = false;
                    habElimina = false;
                    habGuardaCamb = false;
                    habNotifica = false;
                }
                if (listaAPeticion.get(i).getDemandante_cod_estado().equals("ENV")) {
                    habAdiciona = false;
                    habElimina = false;
                    habGuardaCamb = true;
                    habNotifica = false;
                }
                if (!listaAPeticion.get(i).getDemandante_cod_estado().equals("PREE") && !listaAPeticion.get(i).getDemandante_cod_estado().equals("ENV")) {
                    habAdiciona = true;
                    habElimina = true;
                    habGuardaCamb = true;
                    habNotifica = true;
                    break;
                }

                /* if (!listaAPeticion.get(i).getDemandante_cod_estado().equals("PREE") && !listaAPeticion.get(i).getDemandante_cod_estado().equals("ENV")) {
                 habAdiciona = true;
                 habElimina = true;
                 habGuardaCamb = true;

                 } else {
                 habAdiciona = false;
                 habElimina = false;
                 habGuardaCamb = false;
                 }*/
            }
        }
        //System.out.println("tam listaAPeticion::"+listaAPeticion.size());

    }

    public void vaAnteriorLote() throws Exception {
        numBloque = numBloqueBusca - 1;
        listaAPeticion = notificacionService.getNotificacionXbloqueXusuariosol(numBloque, idUsuarioSesion);//Elk idUsuario A Revisar
        numIntoBloque = (Integer) listaAPeticion.size() + 1;
        numBloqueBusca = numBloque;
        if (creaModifieli.equals("2")) {

            for (int i = 0; i <= listaAPeticion.size() - 1; i++) {
                //Pregunto por preenviado , basta que encuentre uno para que se deshabilite el boton de  "Adicionar" ya que cuando esta en etapa de modificacion y 
                // en estado   enviado no puede adicionar mas tramites. Iual lo hace el sistema antiguo.

                if (listaAPeticion.get(i).getDemandante_cod_estado().equals("PREE")) {
                    habAdiciona = false;
                    habElimina = false;
                    habGuardaCamb = false;
                    habNotifica = false;
                }
                if (listaAPeticion.get(i).getDemandante_cod_estado().equals("ENV")) {
                    habAdiciona = false;
                    habElimina = false;
                    habGuardaCamb = true;
                    habNotifica = false;
                }
                if (!listaAPeticion.get(i).getDemandante_cod_estado().equals("PREE") && !listaAPeticion.get(i).getDemandante_cod_estado().equals("ENV")) {
                    habAdiciona = true;
                    habElimina = true;
                    habGuardaCamb = true;
                    habNotifica = true;
                }

                /* if (!listaAPeticion.get(i).getDemandante_cod_estado().equals("PREE")) {
                 habAdiciona = true;
                 habElimina = true;
                 habGuardaCamb = true;

                 } else {
                 habAdiciona = false;
                 habElimina = false;
                 habGuardaCamb = false;
                 }
                 */
            }
        }

    }

    public void vaSiguienteLote() throws Exception {
        numBloque = numBloqueBusca + 1;
        listaAPeticion = notificacionService.getNotificacionXbloqueXusuariosol(numBloque, idUsuarioSesion);//Elk idUsuario A Revisar
        numIntoBloque = (Integer) listaAPeticion.size() + 1;
        numBloqueBusca = numBloque;
        if (creaModifieli.equals("2")) {

            for (int i = 0; i <= listaAPeticion.size() - 1; i++) {
                //Pregunto por preenviado , basta que encuentre uno para que se deshabilite el boton de  "Adicionar" ya que cuando esta en etapa de modificacion y 
                // en estado   enviado no puede adicionar mas tramites. Iual lo hace el sistema antiguo.
                if (listaAPeticion.get(i).getDemandante_cod_estado().equals("PREE")) {
                    habAdiciona = false;
                    habElimina = false;
                    habGuardaCamb = false;
                    habNotifica = false;
                }
                if (listaAPeticion.get(i).getDemandante_cod_estado().equals("ENV")) {
                    habAdiciona = false;
                    habElimina = false;
                    habGuardaCamb = true;
                    habNotifica = false;
                }
                if (!listaAPeticion.get(i).getDemandante_cod_estado().equals("PREE") && !listaAPeticion.get(i).getDemandante_cod_estado().equals("ENV")) {
                    habAdiciona = true;
                    habElimina = true;
                    habGuardaCamb = true;
                    habNotifica = true;
                }

                /*if (!listaAPeticion.get(i).getDemandante_cod_estado().equals("PREE")) {
                 habAdiciona = true;
                 habElimina = true;
                 habGuardaCamb = true;

                 } else {
                 habAdiciona = false;
                 habElimina = false;
                 habGuardaCamb = false;
                 }*/
            }
        }
    }

    public void busca() throws Exception {
        //Esta parte se desencripta con su codigo de VICTOR
        Long smBuscar;
        //  System.out.println("En buscar el combo su valor::" + comboExpediente);

        if (comboExpediente.equals("SM")) {
            smBuscar = comunService.codificarCodigoSM(numero, gestion, extension);
            //  System.out.println("SM codificacdo ::" + smBuscar);
            habOposocion = true;
            /*    if(this.numero.length() == 1)
             {
             numero="00000"+numero;
             }
             if(extension.equals(""))
             {
             extension="00";
             }
             smBuscar =this.gestion+this.numero+this.extension;*/
            System.out.println("El sm a buscar::" + smBuscar);
            List<Notificacion> listNotiDatosDemandante = new ArrayList<Notificacion>();

            listNotiDatosDemandante = notificacionService.listaNotificacionDatosSM(smBuscar);
            //System.out.println("DEMANDADO::"+listNotiDatosDemandante.get(0).getDemandante());

            descripcion = listNotiDatosDemandante.get(0).getDemandante();
            demanSolicitante = listNotiDatosDemandante.get(0).getDemandante_solic();
            demanApoderado = listNotiDatosDemandante.get(0).getDemandante_apod();
            //demanFojas = ;
            // demanNotificarCon = "";
            demanCelular = listNotiDatosDemandante.get(0).getDemandante_cel();
            demanDomicilio = listNotiDatosDemandante.get(0).getDemandante_direc();

        }
        if (comboExpediente.equals("SR")) {
            habOposocion = true;
            List<Notificacion> listNotiDatosDemandante = new ArrayList<Notificacion>();
            listNotiDatosDemandante = notificacionService.listaNotificacionDatosSR(Long.valueOf(this.numero), Long.valueOf(this.gestion));
            descripcion = listNotiDatosDemandante.get(0).getDemandante();
            demanSolicitante = listNotiDatosDemandante.get(0).getDemandante_solic();
            demanApoderado = listNotiDatosDemandante.get(0).getDemandante_apod();
            //demanFojas = ;
            // demanNotificarCon = "";
            demanCelular = listNotiDatosDemandante.get(0).getDemandante_cel();
            demanDomicilio = listNotiDatosDemandante.get(0).getDemandante_direc();
        }
        if (comboExpediente.equals("ST-S") || comboExpediente.equals("ST-C") || comboExpediente.equals("ST-E") || comboExpediente.equals("ST")|| comboExpediente.equals("ST-H")
                || comboExpediente.equals("SF-S") || comboExpediente.equals("SF-C") || comboExpediente.equals("SF-E") || comboExpediente.equals("SF") || comboExpediente.equals("SF-H")
                || comboExpediente.equals("LU-S") || comboExpediente.equals("LU-C") || comboExpediente.equals("LU-E") || comboExpediente.equals("LU") || comboExpediente.equals("LU-H") 
                || comboExpediente.equals("CD-S") || comboExpediente.equals("CD-C") || comboExpediente.equals("CD-E") || comboExpediente.equals("CD") || comboExpediente.equals("CD-H")
                || comboExpediente.equals("CN-S") || comboExpediente.equals("CN-C") || comboExpediente.equals("CN-E") || comboExpediente.equals("CN") || comboExpediente.equals("CN-H")
             ) {
            habOposocion = true;
            List<Notificacion> listNotiDatosDemandante = new ArrayList<Notificacion>();
            listNotiDatosDemandante = notificacionService.listaNotificacionDatosModi(comboExpediente, Long.valueOf(numero), Long.valueOf(gestion));

            descripcion = listNotiDatosDemandante.get(0).getDemandante();
            demanSolicitante = listNotiDatosDemandante.get(0).getDemandante_solic();
            demanApoderado = listNotiDatosDemandante.get(0).getDemandante_apod();
            //demanFojas = ;
            // demanNotificarCon = "";
            demanCelular = listNotiDatosDemandante.get(0).getDemandante_cel();
            demanDomicilio = listNotiDatosDemandante.get(0).getDemandante_direc();
        }
        //if(comboExpediente.equals("NR"))//Para el caso de registros
        if (comboExpediente.equals("N° REG")) {
            habOposocion = true;
            List<Notificacion> listNotiDatosDemandante = new ArrayList<Notificacion>();
            listNotiDatosDemandante = notificacionService.listaNotificacionDatosSMNumReg(Long.valueOf(numero), this.extension);
            descripcion = listNotiDatosDemandante.get(0).getDemandante();
            demanSolicitante = listNotiDatosDemandante.get(0).getDemandante_solic();
            demanApoderado = listNotiDatosDemandante.get(0).getDemandante_apod();
            //demanFojas = ;
            // demanNotificarCon = "";
            demanCelular = listNotiDatosDemandante.get(0).getDemandante_cel();
            demanDomicilio = listNotiDatosDemandante.get(0).getDemandante_direc();
        }
        //if(comboExpediente.equals("NPS"))//Para el caso de registros
        if (comboExpediente.equals("N° PUB SIGNO")) {
           // System.out.println("entra por numero publicacion");
            habOposocion = true;
            List<Notificacion> listNotiDatosDemandante = new ArrayList<Notificacion>();
            listNotiDatosDemandante = notificacionService.listaNotificacionDatosSMNumPubl(Long.valueOf(numero));
            descripcion = listNotiDatosDemandante.get(0).getDemandante();
            demanSolicitante = listNotiDatosDemandante.get(0).getDemandante_solic();
            demanApoderado = listNotiDatosDemandante.get(0).getDemandante_apod();
            //demanFojas = ;
            // demanNotificarCon = "";
            demanCelular = listNotiDatosDemandante.get(0).getDemandante_cel();
            demanDomicilio = listNotiDatosDemandante.get(0).getDemandante_direc();
        }
        //if(comboExpediente.equals("OS"))
        if (comboExpediente.equals("OPOSICION SIG")) {
            habOposocion = false;
            List<Notificacion> listNotiDatosOpo = new ArrayList<Notificacion>();
            listNotiDatosOpo = notificacionService.listaNotificacionDatosOpo(Long.valueOf(numero), Long.valueOf(extension));
            danteMarca = listNotiDatosOpo.get(0).getDemandante();
            danteSolicitante = listNotiDatosOpo.get(0).getDemandante_solic();
            danteApoderado = listNotiDatosOpo.get(0).getDemandante_apod();
            danteFojas = listNotiDatosOpo.get(0).getDemandante_fojas();
            danteNotificarCon = listNotiDatosOpo.get(0).getDemandante_con();
            danteCelular = listNotiDatosOpo.get(0).getDemandante_cel();
            danteDomicilio = listNotiDatosOpo.get(0).getDemandante_direc();

            dadoMarca = listNotiDatosOpo.get(0).getDemandado();
            dadoSolicitante = listNotiDatosOpo.get(0).getDemandado_solic();
            dadoApoderado = listNotiDatosOpo.get(0).getDemandado_apod();
            dadoFojas = listNotiDatosOpo.get(0).getDemandado_fojas();
            dadoNotificarCon = listNotiDatosOpo.get(0).getDemandado_con();
            dadoCelular = listNotiDatosOpo.get(0).getDemandado_cel();
            dadoDomicilio = listNotiDatosOpo.get(0).getDemandado_direc();

        }
        if (comboExpediente.equals("CANCELACION") || comboExpediente.equals("NULIDAD SIG")) {
            habOposocion = false;

            List<Notificacion> listNotiDatosDemandante = new ArrayList<Notificacion>();
            listNotiDatosDemandante = notificacionService.listaNotificacionDatosSMNumReg(Long.valueOf(numero), this.extension);
            danteMarca = "";
            danteSolicitante = "";
            danteApoderado = "";
            danteFojas = "";
            danteNotificarCon = "";
            danteCelular = "";
            danteDomicilio = "";
            dadoMarca = listNotiDatosDemandante.get(0).getDemandante();;
            dadoSolicitante = listNotiDatosDemandante.get(0).getDemandante_solic();;
            dadoApoderado = listNotiDatosDemandante.get(0).getDemandante_apod();

            dadoCelular = listNotiDatosDemandante.get(0).getDemandante_cel();
            dadoDomicilio = listNotiDatosDemandante.get(0).getDemandante_direc();

        }
        if (comboExpediente.equals("IF") || comboExpediente.equals("MEF")) {
            
             habOposocion = false;
             danteMarca = "";
            danteSolicitante = "";
            danteApoderado = "";
            danteFojas = "";
            danteNotificarCon = "";
            danteCelular = "";
            danteDomicilio = "";

            dadoMarca = "";
            dadoSolicitante = "";
            dadoApoderado = "";
            dadoFojas = "";
            dadoNotificarCon = "";
            dadoCelular = "";
            dadoDomicilio = "";
            
            
        }

    }

    public void adicionaTabla() throws Exception {

        Notificacion notifica = new Notificacion();
        notifica.setIdlogtrans(1L);//por Revisar
        //Pregunta si en eel combo esta elegido oposicion
        //if(!comboExpediente.equals("OS"))
        if (!comboExpediente.equals("OPOSICION SIG") && !comboExpediente.equals("CANCELACION") && !comboExpediente.equals("NULIDAD SIG")
             && !comboExpediente.equals("IF") && !comboExpediente.equals("MEF")) {
            if (creaModifieli.equals("1")) {
                Integer bloque = notificacionService.obtieneBloqueNuevo(this.idUsuarioSesion.intValue());//por Revisar
                if (bloque == null) {
                    bloque = 1;

                }

                notifica.setBloque(bloque);
            }
            if (creaModifieli.equals("2")) {
                notifica.setBloque(numBloque);
            }
            notifica.setNro_exped(listaAPeticion.size() + 1);
            notifica.setId_usuario_solicitante(idUsuarioSesion);//por Revisar
            notifica.setTipo_tramite_notificacion(this.comboExpediente);
            notifica.setExpediente(this.numero);
            if (this.gestion != null && !this.gestion.equals("")) {
                notifica.setGestion(Integer.parseInt(this.gestion));

            }
            notifica.setExtension(this.extension);
            notifica.setDemandante(this.descripcion);
            notifica.setDemandante_cod_estado("PREE");
            notifica.setDemandante_solic(demanSolicitante);
            notifica.setDemandante_apod(demanApoderado);
            notifica.setDemandante_fojas(demanFojas);
            notifica.setDemandante_con(demanNotificarCon);
            notifica.setDemandante_direc(demanDomicilio);
            notifica.setDemandante_cel(demanCelular);
            notifica.setFecha_ingreso(fechaIngreso);
            notifica.setObs(this.obsPeticion);
            notifica.setHistorial("");//por Revisar
            listaAPeticion.add(notifica);
            limpiaVar();
            numIntoBloque = (Integer) listaAPeticion.size() + 1;
        } //Cuando es oposciion accede a este else
        else {
            if (creaModifieli.equals("1")) {
                Integer bloque = notificacionService.obtieneBloqueNuevo(this.idUsuarioSesion.intValue());//por Revisar
                if (bloque == null) {
                    bloque = 1;

                }
                notifica.setBloque(bloque);
            }
            if (creaModifieli.equals("2")) {
                notifica.setBloque(numBloque);
            }
            notifica.setNro_exped(listaAPeticion.size() + 1);
            notifica.setId_usuario_solicitante(idUsuarioSesion);//por Revisar
            notifica.setTipo_tramite_notificacion(this.comboExpediente);
            notifica.setExpediente(this.numero);
            if (this.gestion != null && !this.gestion.equals("")) {
                notifica.setGestion(Integer.parseInt(this.gestion));

            }
            notifica.setExtension(this.extension);

            notifica.setDemandante(danteMarca);
            notifica.setDemandante_cod_estado("PREE");
            notifica.setDemandante_solic(danteSolicitante);
            notifica.setDemandante_apod(danteApoderado);
            notifica.setDemandante_fojas(danteFojas);
            notifica.setDemandante_con(danteNotificarCon);
            notifica.setDemandante_direc(danteDomicilio);
            notifica.setDemandante_cel(danteCelular);
            notifica.setDemandado(dadoMarca);
            notifica.setDemandado_cod_estado("PREE");
            notifica.setDemandado_solic(dadoSolicitante);
            notifica.setDemandado_apod(dadoApoderado);
            notifica.setDemandado_fojas(dadoFojas);
            notifica.setDemandado_con(dadoNotificarCon);
            notifica.setDemandado_direc(dadoDomicilio);
            notifica.setDemandado_cel(dadoCelular);
            notifica.setObs(this.obsPeticion);
            notifica.setHistorial("");//por Revisar
            listaAPeticion.add(notifica);
            limpiaVarOposiciones();
            numIntoBloque = (Integer) listaAPeticion.size() + 1;

        }
    }

    //ESTE METODO lo UTILIZABA ANTERIORMENTE PARA  CONVERTIR  agarrar el campo nombre dado el codigo , pero no es necesario ya que aurita solo
    //estoy trabajando con el campo nombre para todo, quizas depsues lo necesite
    public String getNombreTipoNotificacion(String codigo) throws Exception {
        List<Dominio> listaDominio = dominioService.obtenerListadoDominioXCodigo("tipo_tramite_notificacion", codigo);
        if (listaDominio.size() != 0) {
            return listaDominio.get(0).getNombre();
        } else {
            return "";
        }

    }

    public void modificaTabla() throws Exception {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        if (!listaAPeticion.get(pos).getDemandante_cod_estado().equals("PREE") && !listaAPeticion.get(pos).getDemandante_cod_estado().equals("ENV")) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "No puede modificar, este trámite ya esta del lado del Notificador", "");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            /*
           
           
           
           
           
           
             */
            //  System.out.println("tipo_::" + listaAPeticion.get(pos).getTipo_tramite_notificacion());

            Notificacion notiAModifi = listaAPeticion.get(pos);
            notiAModifi.setTipo_tramite_notificacion(this.comboExpediente);
            notiAModifi.setExpediente(this.numero);
            if (!this.gestion.endsWith("")) {
                notiAModifi.setGestion(Integer.parseInt(this.gestion));

            }
            notiAModifi.setExtension(this.extension);

            if (!listaAPeticion.get(pos).getTipo_tramite_notificacion().equals("OPOSICION SIG")
                    && !comboExpediente.equals("CANCELACION")
                    && !comboExpediente.equals("NULIDAD SIG")
                    && !comboExpediente.equals("IF")
                    && !comboExpediente.equals("MEF")) {

                notiAModifi.setDemandante(descripcion);
                notiAModifi.setDemandante_solic(demanSolicitante);
                notiAModifi.setDemandante_apod(demanApoderado);
                notiAModifi.setDemandante_fojas(demanFojas);
                notiAModifi.setDemandante_con(demanNotificarCon);
                notiAModifi.setDemandante_cel(demanCelular);
                notiAModifi.setDemandante_direc(demanDomicilio);
                notiAModifi.setObs(obsPeticion);
            } else {
                notiAModifi.setDemandante(danteMarca);
//             notiAModifi.setDemandante_cod_estado("PREE");
                notiAModifi.setDemandante_solic(danteSolicitante);
                notiAModifi.setDemandante_apod(danteApoderado);
                notiAModifi.setDemandante_fojas(danteFojas);
                notiAModifi.setDemandante_con(danteNotificarCon);
                notiAModifi.setDemandante_direc(danteDomicilio);
                notiAModifi.setDemandante_cel(danteCelular);
                notiAModifi.setDemandado(dadoMarca);
                //            notiAModifi.setDemandado_cod_estado("PREE");
                notiAModifi.setDemandado_solic(dadoSolicitante);
                notiAModifi.setDemandado_apod(dadoApoderado);
                notiAModifi.setDemandado_fojas(dadoFojas);
                notiAModifi.setDemandado_con(dadoNotificarCon);
                notiAModifi.setDemandado_direc(dadoDomicilio);
                notiAModifi.setDemandado_cel(dadoCelular);

            }
            if (creaModifieli.equals("2")) {   //Aqui verifico si ya esta en estado enviado , entonces coloco adiciono en el campo historial como modificado
                if (listaAPeticion.get(pos).getDemandante_cod_estado().equals("ENV")) {
                    listaAPeticion.get(pos).setHistorial(listaAPeticion.get(pos).getHistorial() + ";"
                            + "MODIFICADO: "
                            + usuarioService.listaUsuarioXidPagina(listaAPeticion.get(pos).getId_usuario_solicitante()).get(0).getNombre()
                            + " "
                            + usuarioService.listaUsuarioXidPagina(listaAPeticion.get(pos).getId_usuario_solicitante()).get(0).getPrimer_apellido()
                            // listaAPeticion.get(pos).getId_usuario_solicitante() 

                            + "," + df.format(new Date()));
                }

            }

            listaAPeticion.set(pos, notiAModifi);
        }

    }

    public void onRowSelect(SelectEvent event) {

        try {

            //Esta variable de pos se queda en memoria del ultimo doble click que haga//
            pos = ((Notificacion) event.getObject()).getNro_exped() - 1;
            //System.out.println("pos"+pos);
            //    System.out.println("posicion::"+posicion);
            this.comboExpediente = listaEscoge.get(0).getTipo_tramite_notificacion();
            if (!comboExpediente.equals("OPOSICION SIG") && !comboExpediente.equals("CANCELACION")
                    && !comboExpediente.equals("NULIDAD SIG")
                    && !comboExpediente.equals("IF")
                    && !comboExpediente.equals("MEF")) {
                //  System.out.println("Entra a oposiciones1");
                habOposocion = true;
                this.comboExpediente = listaEscoge.get(0).getTipo_tramite_notificacion();
                this.numero = listaEscoge.get(0).getExpediente();
                if (listaEscoge.get(0).getGestion() != null && !listaEscoge.get(0).getGestion().equals("")) {
                    this.gestion = listaEscoge.get(0).getGestion().toString();
                } else {
                    this.gestion = "";
                }

                this.extension = listaEscoge.get(0).getExtension();
                this.descripcion = listaEscoge.get(0).getDemandante();
                this.demanSolicitante = listaEscoge.get(0).getDemandante_solic();
                this.demanApoderado = listaEscoge.get(0).getDemandante_apod();
                this.demanFojas = listaEscoge.get(0).getDemandante_fojas();
                this.demanNotificarCon = listaEscoge.get(0).getDemandante_con();
                this.demanCelular = listaEscoge.get(0).getDemandante_cel();
                this.demanDomicilio = listaEscoge.get(0).getDemandante_direc();
                this.obsPeticion = listaEscoge.get(0).getObs();
                //expediente = listaEscoge.get(0).getExpediente();
                llenaDatosAdmni(listaEscoge.get(0));
            } else {
                // System.out.println("Entra a oposiciones2");
                habOposocion = false;
                this.comboExpediente = listaEscoge.get(0).getTipo_tramite_notificacion();
                this.numero = listaEscoge.get(0).getExpediente();
                //this.gestion=listaEscoge.get(0).getGestion().toString();
                this.extension = listaEscoge.get(0).getExtension();
                this.danteMarca = listaEscoge.get(0).getDemandante();
                this.danteSolicitante = listaEscoge.get(0).getDemandante_solic();
                this.danteApoderado = listaEscoge.get(0).getDemandante_apod();
                this.danteFojas = listaEscoge.get(0).getDemandante_fojas();
                this.danteNotificarCon = listaEscoge.get(0).getDemandante_con();
                this.danteDomicilio = listaEscoge.get(0).getDemandante_direc();
                this.danteCelular = listaEscoge.get(0).getDemandante_cel();
                this.dadoMarca = listaEscoge.get(0).getDemandado();
                this.dadoSolicitante = listaEscoge.get(0).getDemandado_solic();
                this.dadoApoderado = listaEscoge.get(0).getDemandado_apod();
                this.dadoFojas = listaEscoge.get(0).getDemandado_fojas();
                this.dadoNotificarCon = listaEscoge.get(0).getDemandado_con();
                this.dadoDomicilio = listaEscoge.get(0).getDemandado_direc();
                this.dadoCelular = listaEscoge.get(0).getDemandado_cel();
                this.obsPeticion = listaEscoge.get(0).getObs();
                llenaDatosAdmni(listaEscoge.get(0));

            }

        } catch (ParseException ex) {
            Logger.getLogger(NotiPeticionDialogController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void llenaDatosAdmni(Notificacion notiEscogido) throws ParseException {

        listHistPo.clear();
        if (notiEscogido.getHistorial() != null && !notiEscogido.getHistorial().equals("")) {
            String[] listPadre = notiEscogido.getHistorial().split(";");

            for (int i = 0; i <= listPadre.length - 1; i++) {
                HistorialPojo historial = new HistorialPojo();
                String[] hijo = listPadre[i].split(",");
                historial.setNum(i + 1);
                historial.setHistorial(hijo[0]);
                //historial.setFecha(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(hijo[1]));//A revisar

                historial.setFecha(hijo[1]);//A revisar
                listHistPo.add(historial);
            }
        }

    }

    public void eliminaTabla() {

        // System.out.println("tam listaEscoge" + listaEscoge.size());
        for (int i = 0; i <= listaEscoge.size() - 1; i++) {
            listaAPeticion.remove(listaEscoge.get(i));
        }
        ordenaTabla();
        numIntoBloque = (Integer) listaAPeticion.size() + 1;
        limpiaVar();
        //System.out.println("Nuevo tamaño::" + listaAPeticion.size());

    }

    public void ordenaTabla() {
        for (int i = 0; i <= listaAPeticion.size() - 1; i++) {
            listaAPeticion.get(i).setNro_exped(i + 1);

        }

    }

    public void limpiaVar() {
        comboExpediente = "SM";
        numero = "";
        gestion = "";
        extension = "";
        obsPeticion = "";
        descripcion = "";
        demanSolicitante = "";
        demanApoderado = "";
        demanFojas = "";
        demanNotificarCon = "";
        demanCelular = "";
        demanDomicilio = "";
        obsPeticion = "";
    }

    public void limpiaVarOposiciones() {
        danteMarca = "";
        danteSolicitante = "";
        danteApoderado = "";
        danteFojas = "";
        danteNotificarCon = "";
        danteCelular = "";
        danteDomicilio = "";

        dadoMarca = "";
        dadoSolicitante = "";
        dadoApoderado = "";
        dadoFojas = "";
        dadoNotificarCon = "";
        dadoCelular = "";
        dadoDomicilio = "";

        obsPeticion = "";

    }

    public void limpiaTodo() throws Exception {
        descripcion = "";
        demanSolicitante = "";
        demanApoderado = "";
        demanFojas = "";
        demanNotificarCon = "";
        demanCelular = "";
        demanDomicilio = "";
        listaAPeticion.clear();

    }

    public void cambiaRadioButon() throws Exception {

        if (creaModifieli.equals("1")) {
            habAdiciona = false;
            habElimina = false;
            habModifica = false;
            habBuscar = true;
            numBloqueBusca = null;
            numBloque = notificacionService.obtieneBloqueNuevo(idUsuarioSesion.intValue());
            numIntoBloque = 1;
            habGuardaCamb = false;
            limpiaVar();

            listaAPeticion.clear();//Tambien debo limpiar mi tabla de la derecha , esto no pongo en el metodo limpiaVar() ya que otros metodos  usan esto y siempre que se ejecuta borrair aa mi tabal derecha , y no es el caso
        }
        if (creaModifieli.equals("2")) {
            habAdiciona = false;
            habElimina = false;
            habModifica = false;
            habBuscar = false;
            //cereamos el numero de bloque y su correlativo solo cuando cambia a modificar para que de a entender que no eligio ningun bloque
            numBloque = 0;
            numIntoBloque = 0;
            listaAPeticion.clear();
            //   limpiaVar();  No limpiar porque quizas al bloque que escogio quiera insertar el agarrado
        }
        if (creaModifieli.equals("3")) {
            habAdiciona = true;
            habElimina = true;
            habModifica = true;
            habBuscar = false;
            limpiaVar();
        }
    }

    public void imprime2() throws JRException, IOException, Exception {
        ////////////////////////////IMPRIMO EL REPORTE E NSI////////////////////////////////
        String imageSenapi = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/imagen/Logo senapi");

        Map<String, Object> parametros;
        parametros = new HashMap<String, Object>();
        String var1 = "{\\rtf1\\ansi\\deff0"
                + "{\\colortbl;\\red0\\green0\\blue0;\\red255\\green0\\blue0;}"
                + "This line is the default color\\line"
                + "\\cf2"
                + "This line is red\\line"
                + "\\cf1"
                + "This line is the default color"
                + "}";
        //El codigo de abajo muestra que podemos agarrar un archivo cuyo contenido es texto en formato RTF
        //String rtfText = FileUtils.readFully(new FileReader(new File("/home/levi/Documentos/PROYECTOS/SISInterno/SistemaInterno/giview/src/main/java/snp/gob/bo/entidades/notificacion/rtf1.txt")));
        //se ha creado un servicio solo para probar qeu en un campo de BD tipo String tiene contenido en formato RTF e igual ese lo puede agarrar, para este ejemplo en la tabla usuariopagina
        String rtfText = usuarioPaginaService.obtieneHabalitado();

        /**
         * *********Para html*********
         */
        //se agarra un .txt con formato adentro de html, pero no funciona no agarra
        String varhtml = FileUtils.readFully(new FileReader(new File("/home/levi/Documentos/PROYECTOS/SISInterno/SistemaInterno/giview/src/main/java/snp/gob/bo/entidades/notificacion/html_1.txt")));

        parametros.put("variable", rtfText);
       // System.out.println("varhtml"+varhtml);
        // parametros.put("varhtml", varhtml);

        ///////imprimer el reporte en si/////////////////
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/prueba/MyReports/prueba.jasper"));
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        //con esta instruccion se crea otra ventana en el que se mostrara el reporte
        response.addHeader("Content-Disposition", "attachment; filename=Publicacion.pdf");

        //Se enviaran al reporte solamente parametros, de los cuales el que corresponde a la Collection se enviara como vacio
        byte[] bytes;

        bytes = JasperRunManager.runReportToPdf(jasper.getPath(), parametros, new JREmptyDataSource());

        response.setContentLength(bytes.length);

        ServletOutputStream outStream = response.getOutputStream();

        outStream.write(bytes, 0, bytes.length);
        outStream.flush();
        outStream.close();
        FacesContext.getCurrentInstance().responseComplete();

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
     * metodo para el paso de parametros al reporte lista de peticiones de
     * acuerdo al bloque de peticiones por usuario.
     *
     * Creado: Ruben Ramirez Fecha: 24/11/2016
     *
     * @throws net.sf.jasperreports.engine.JRException
     * @throws java.io.IOException
     */
    public void imprime() throws JRException, IOException, Exception {
      //  System.out.println("IMPRIME LA PETICION  NOTIFICACION");
        listaAPeticionRepo = notificacionService.getNotificacionXbloqueXusuariosol(numBloque, idUsuarioSesion);

        if (listaAPeticionRepo.size() > 0) {

            imgSenapi = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/images/logoNuevo.jpg");
            nroBloque = "" + numBloque;

            listUsuario = usuarioService.listaUsuarioXidPagina(idUsuarioSesion);
            Usuario user = listUsuario.get(0);

            operador = user.getNombre() + " " + user.getPrimer_apellido();

            usuario = devuelveNombreJuridicoONatural(user.getNombre(), user.getPrimer_apellido(), user.getSegundo_apellido());

            if (usuario != null) {
                usuario = usuario.toUpperCase();
            }

            // brinda formato a la fecha y hora de registros date.
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

            SimpleDateFormat formateador1 = new SimpleDateFormat("EEEEE, dd MMMM, yyyy", dateFormatSymbols);
            SimpleDateFormat formateador2 = new SimpleDateFormat("hh:mm a");
            SimpleDateFormat formateador3 = new SimpleDateFormat("dd/MM/yyyy");

            // fecha actual de al base de datos
            Date fechaPresente = comunService.obtenerFechaHoraServidor(1L);

            if (fechaPresente != null) {
                fechaIng = formateador1.format(fechaPresente);
                horaIng = formateador2.format(fechaPresente);
            }

            int i = 0;
            for (Notificacion item : listaAPeticionRepo) {
                if (item.getDemandado_cod_estado() != "PREE" && item.getDemandante_cod_estado() != "PREE") {
                    i++;
                    NotiPeticionListPojo noti = new NotiPeticionListPojo();
                    noti.setN("" + i);
                    noti.setTramite(item.getTipo_tramite_notificacion());
                    noti.setNro(item.getExpediente());
                    if (item.getGestion() != null && item.getGestion() > 0) {
                        noti.setGestion("" + item.getGestion());
                    }
                    if (item.getExtension() != null) {
                        if (!item.getExtension().trim().equals("")) {
                            if (this.validar(item.getExtension())) {
                                noti.setExtension(item.getExtension());
                            }
                        }
                    }

                    noti.setDemandante(item.getDemandante());
                    noti.setDemandado(item.getDemandado());

                    if (item.getDemandante_fecha_noti() != null) {
                        noti.setDemandanteFecha(formateador3.format(item.getDemandante_fecha_noti()));
                    }
                    if (item.getDemandado_fecha_noti() != null) {
                        noti.setDemandadoFecha(formateador3.format(item.getDemandado_fecha_noti()));
                    }
                    noti.setDemandanteCon(item.getDemandante_con());
                    noti.setDemandadoCon(item.getDemandado_con());

                    noti.setDemandanteFojas(item.getDemandante_fojas());
                    noti.setDemandadoFojas(item.getDemandado_fojas());

                    listNotiPeticionList.add(noti);
                }
            }
            if (listNotiPeticionList.size() > 0) {
                JRBeanCollectionDataSource itemsJRBean = new JRBeanCollectionDataSource(listNotiPeticionList);
                parametros.put("nroBloque", nroBloque);
                parametros.put("operador", operador);
                parametros.put("fechaIngreso", fechaIng);
                parametros.put("horaIngreso", horaIng);
                parametros.put("imgSenapi", imgSenapi);
                parametros.put("dataNoti", itemsJRBean);
                parametros.put("usuario", usuario);
                String filename = "Notificacion.pdf";
                String jasperPath = "/template/notificacion/NotiNotificacion.jasper";
                this.PDFSD(parametros, jasperPath, filename);
                listNotiPeticionList.clear();
            } else {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "BLOQUE NO SELECCIONADO", "Seleccione un bloque.");
                FacesContext.getCurrentInstance().addMessage(null, message);
            }
        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "BLOQUE NO SELECCIONADO", "Seleccione un bloque.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    /**
     * Metodo para generar el reporte en formato pdf
     *
     * Creado: Ruben Ramirez Fecha: 24/11/2016
     *
     * @param params
     * @param jasperPath
     * @param fileName
     *
     * @throws net.sf.jasperreports.engine.JRException
     * @throws java.io.IOException
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

    public void guardaNotificacion() throws Exception {
      //  System.out.println("/************************/");
      //  System.out.println("ENTRA A GUARDAR LA PETICION DE NOTIFICACIÓN.....");
        if (creaModifieli.equals("1")) {
            notificacionService.guardaListaNotificaciones(listaAPeticion);
            //He limpiado todo y creado nuevo numero de bloque para no tener que  ver en BD: si existe bloque se updetea, y si no se crea
            //prefiero obligar al usuario a que entre a la opcion modificar , si es qeu se ha equivocado con el insertado
            numIntoBloque = 1;
            numBloque = notificacionService.obtieneBloqueNuevo(idUsuarioSesion.intValue());

            limpiaTodo();

            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bloque creado", "Se creará un nuevo bloque ahora.");
            FacesContext.getCurrentInstance().addMessage(null, message);
//         listaAPeticion
        }
        if (creaModifieli.equals("2")) {
            // System.out.println("bloque::"+listaAPeticion.get(0).getBloque());
            // System.out.println("seciencua::"+listaAPeticion.get(0).getNro_exped());
            notificacionService.modificaListaNotificaciones(listaAPeticion);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bloque modificado", "");
            FacesContext.getCurrentInstance().addMessage(null, message);

        }

    }

    //Guarda en el historial de signos

    public void guardaHistorial(List<Notificacion> lista) throws Exception {
        Long sm;

        for (int i = 0; i <= lista.size() - 1; i++) {
            String desc = "[Status: ";

            //  System.out.println("desc::"+desc);
            if (lista.get(i).getTipo_tramite_notificacion().equals("SM")) {
                if (lista.get(i).getExtension() == null) {
                    sm = comunService.codificarCodigoSM(lista.get(i).getExpediente(), lista.get(i).getGestion().toString(), "");
                } else {

                    sm = comunService.codificarCodigoSM(lista.get(i).getExpediente(), lista.get(i).getGestion().toString(), lista.get(i).getExtension());
                }

                SigSignoMarca signoMarca = sigSignoMarcaService.listaSigSignoMarcaXSM(sm);
                //   System.out.println("lista.get(i).getEstado_marca()::"+lista.get(i).getEstado_marca());
                desc = desc + dominioService.obtenerNombrePorCodigoDominio(lista.get(i).getEstado_marca(), "estado_marca") + "]";
                sigHistorialService.generarHistorialNotificacion(signoMarca, idUsuarioSesion, "PETICION DE NOTIFICACION", desc);
            }
            if (lista.get(i).getTipo_tramite_notificacion().equals("N° REG")) {
                SigSignoMarca signoMarca = sigSignoMarcaService.listaSigSignoMarcaXRegistro(Long.parseLong(lista.get(i).getExpediente()), lista.get(i).getExtension(), "");
                if(lista.get(i).getEstado_marca() != null && lista.get(i).getEstado_marca()!="")
                {    
                  desc = desc + dominioService.obtenerNombrePorCodigoDominio(lista.get(i).getEstado_marca(), "estado_marca") + "]";
                  sigHistorialService.generarHistorialNotificacion(signoMarca, idUsuarioSesion, "PETICION DE NOTIFICACION", desc);
                }
            }
            if (lista.get(i).getTipo_tramite_notificacion().equals("N° PUB SIGNO")) {
                SigSignoMarca signoMarca = sigSignoMarcaService.listaSigSignoMarcaXPublicacion(Long.parseLong(lista.get(i).getExpediente()));
                desc = desc + dominioService.obtenerNombrePorCodigoDominio(lista.get(i).getEstado_marca(), "estado_marca") + "]";
                sigHistorialService.generarHistorialNotificacion(signoMarca, idUsuarioSesion, "PETICION DE NOTIFICACION", desc);
            }
            if ((!lista.get(i).getTipo_tramite_notificacion().equals("SM"))
                    && (!lista.get(i).getTipo_tramite_notificacion().equals("SR"))
                    && (!lista.get(i).getTipo_tramite_notificacion().equals("N° REG"))
                    && (!lista.get(i).getTipo_tramite_notificacion().equals("N° PUB SIGNO"))
                    && (!lista.get(i).getTipo_tramite_notificacion().equals("CANCELACION"))
                    && (!lista.get(i).getTipo_tramite_notificacion().equals("OPOSICION SIG"))
                    && (!lista.get(i).getTipo_tramite_notificacion().equals("NULIDAD SIG"))
                    && (!lista.get(i).getTipo_tramite_notificacion().equals("BQ"))
                    && (!lista.get(i).getTipo_tramite_notificacion().equals("CE"))
                    && (!lista.get(i).getTipo_tramite_notificacion().equals("IF"))
                    && (!lista.get(i).getTipo_tramite_notificacion().equals("MEF"))) {
                ModModificacion modi = modModificacionService.buscarModModificacionXCodigo(lista.get(i).getTipo_tramite_notificacion(),
                        Long.parseLong(lista.get(i).getExpediente()),
                        lista.get(i).getGestion());
              //  System.out.println("estado_marca::" + lista.get(i).getEstado_marca());
                if(lista.get(i).getEstado_marca() != null && !lista.get(i).getEstado_marca().equals(""))
                { desc = desc + dominioService.obtenerNombrePorCodigoDominio(lista.get(i).getEstado_marca(), "estado_modificacion") + "]";
                //  System.out.println("desc::" + desc);
                  sigHistorialService.generarHistorialModifiNotificacion(modi, idUsuarioSesion, "PETICION DE NOTIFICACION", desc);
                }
            }
            if (lista.get(i).getTipo_tramite_notificacion().equals("SR")) {
                RenSolicitudRenovacion ren = renSolicitudRenovacionService.buscaSolicitudRenovacionPorNumeroSrYGestion(Long.parseLong(lista.get(i).getExpediente()),
                        lista.get(i).getGestion());
                
                if(lista.get(i).getEstado_marca() != null && !lista.get(i).getEstado_marca().equals(""))
                {
                 // System.out.println("lista.get(i).getEstado_marca()::"+lista.get(i).getEstado_marca());
                  if(lista.get(i).getEstado_marca().equals("SO"))
                  {  desc ="SOLI";
                  }    
                  else{
                    desc = desc + dominioService.obtenerNombrePorCodigoDominio(lista.get(i).getEstado_marca(), "estado_renovacion") + "]";
                  }
                  sigHistorialService.generarHistorialRenoNotificacion(ren, idUsuarioSesion, "PETICION DE NOTIFICACION", desc);
                }
            }

        }
    }

    public void enviaNotificacion() throws Exception {

       // System.out.println("/************************/");
       // System.out.println("ENVIA LA NOTIFICACIÓN.....");
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        //System.out.println("Entra a EnviaNotificacaoin");  
        String bandera = "no";
        if (creaModifieli.equals("1")) {
            //He limpiado todo y creado nuevo numero de bloque para no tener que  ver en BD: si existe bloque se updetea, y si no se crea
            //prefiero obligar al usuario a que entre a la opcion modificar , si es qeu se ha equivocado con el insertado

            for (int i = 0; i <= listaAPeticion.size() - 1; i++) {
               // System.out.println("tipo_tramite0::::" + listaAPeticion.get(i).getTipo_tramite_notificacion());
                listaAPeticion.get(i).setFecha_ingreso(new Date());
                if (!listaAPeticion.get(i).getTipo_tramite_notificacion().equals("OPOSICION SIG") && !listaAPeticion.get(i).getTipo_tramite_notificacion().equals("CANCELACION")
                        && !listaAPeticion.get(i).getTipo_tramite_notificacion().equals("NULIDAD SIG")
                        && !listaAPeticion.get(i).getTipo_tramite_notificacion().equals("IF")
                        && !listaAPeticion.get(i).getTipo_tramite_notificacion().equals("MEF")) {
                    listaAPeticion.get(i).setDemandante_cod_estado("ENV");

                } else {
                    listaAPeticion.get(i).setDemandante_cod_estado("ENV");
                    listaAPeticion.get(i).setDemandado_cod_estado("ENV");
                }
                listaAPeticion.get(i).setHistorial("ADICIONADO: "
                        + usuarioService.listaUsuarioXidPagina(listaAPeticion.get(i).getId_usuario_solicitante()).get(0).getNombre()
                        + " "
                        + usuarioService.listaUsuarioXidPagina(listaAPeticion.get(i).getId_usuario_solicitante()).get(0).getPrimer_apellido()
                        + "," + df.format(new Date()));
                listaAPeticion.get(i).setIdlogtrans(logtrans);
                //seteo el ultimo estado en que se encuentra el tramie par aque al devolver  de notificacion se vuelva a su estado original el campo estado_marca
                listaAPeticion.get(i).setEstado_marca(obtieneEstadoActualTramite(listaAPeticion.get(i)));
            }
            notificacionService.guardaListaNotificaciones(listaAPeticion);
            numIntoBloque = 1;
            numBloque = notificacionService.obtieneBloqueNuevo(idUsuarioSesion.intValue());
//            System.out.println("tipo_tramite1::::" + listaAPeticion.get(0).getTipo_tramite_notificacion());
//            System.out.println("antes de entatr0:::" + listaAPeticion.size());
            //updetea el estado_marca a 'NOT' a todos de la lista
            guardaEstadoNotifica(listaAPeticion);
            //////////////////////////////
            guardaHistorial(listaAPeticion);
            limpiaTodo();

            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "BLOQUE ENVIADO A NOTIFICACION", "Se creará un nuevo bloque ahora.");
            FacesContext.getCurrentInstance().addMessage(null, message);
            //logtrans
        }
        if (creaModifieli.equals("2")) {
            //En este for se valida que si hay algun estado con Pree su siguiente estado por default seria ENV, por eso la bandera
            //En caso contrario debe dejar pasar su estado por default ya que su estado no debe modificarse, excepto cuando es PREE
            for (int j = 0; j <= listaAPeticion.size() - 1; j++) {
                if (listaAPeticion.get(j).getDemandante_cod_estado().equals("PREE")) {
                    bandera = "si";
                }

            }
            if (bandera.equals("si")) {
                for (int i = 0; i <= listaAPeticion.size() - 1; i++) {
                    if (!listaAPeticion.get(i).getTipo_tramite_notificacion().equals("OPOSICION SIG")
                        && !listaAPeticion.get(i).getTipo_tramite_notificacion().equals("IF")
                        && !listaAPeticion.get(i).getTipo_tramite_notificacion().equals("MEF")) {
                        listaAPeticion.get(i).setDemandante_cod_estado("ENV");

                    } else {
                        listaAPeticion.get(i).setDemandante_cod_estado("ENV");
                        listaAPeticion.get(i).setDemandado_cod_estado("ENV");
                    }
                    listaAPeticion.get(i).setHistorial("ADICIONADO: "
                            + usuarioService.listaUsuarioXidPagina(listaAPeticion.get(i).getId_usuario_solicitante()).get(0).getNombre()
                            + " "
                            + usuarioService.listaUsuarioXidPagina(listaAPeticion.get(i).getId_usuario_solicitante()).get(0).getPrimer_apellido()
                            + "," + df.format(new Date()));
                    listaAPeticion.get(i).setIdlogtrans(logtrans);
                    listaAPeticion.get(i).setFecha_ingreso(new Date());
                    //seteo el ultimo estado en que se encuentra el tramie par aque al devolver  de notificacion se vuelva a su estado original el campo estado_marca
                    listaAPeticion.get(i).setEstado_marca(obtieneEstadoActualTramite(listaAPeticion.get(i)));
                }
                notificacionService.modificaListaNotificaciones(listaAPeticion);
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "BLOQUE MODIFICADO Y ENVIADO A NOTIFICACION", "");
                FacesContext.getCurrentInstance().addMessage(null, message);
                habAdiciona = true;
                habElimina = true;
                habGuardaCamb = true;

            } else {

                notificacionService.modificaListaNotificaciones(listaAPeticion);
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "BLOQUE MODIFICADO Y ENVIADO A NOTIFICACION", "");
                FacesContext.getCurrentInstance().addMessage(null, message);

            }
          //  System.out.println("antes de entatr1:::" + listaAPeticion.size());
            guardaEstadoNotifica(listaAPeticion);
            guardaHistorial(listaAPeticion);
        }

    }

    //devolvera el estado actual del tamite, para que cuando devuelva notificacion retome ese mismo estado

    public String obtieneEstadoActualTramite(Notificacion noti) throws Exception {
       // System.out.println("ENTRAAA::::" + noti.getTipo_tramite_notificacion());
        String estado = null;
        Long sm;
       // if(super.getIdEtapaSession()==3 ||super.getIdEtapaSession()==7)
        // {
        if (noti.getTipo_tramite_notificacion().equals("SM")) {
            if (noti.getExtension() == null) {
                sm = comunService.codificarCodigoSM(noti.getExpediente(), noti.getGestion().toString(), "");
            } else {

                sm = comunService.codificarCodigoSM(noti.getExpediente(), noti.getGestion().toString(), noti.getExtension());
            }
           
            SigSignoMarca signoMarca = sigSignoMarcaService.listaSigSignoMarcaXSM(sm);
           
            estado = signoMarca.getEstadoMarca();

        }
        if (noti.getTipo_tramite_notificacion().equals("N° REG")) {
          //  System.out.println("noti.getExpediente()::"+noti.getExpediente());
           // System.out.println("noti.getExtension()::"+noti.getExtension());
            SigSignoMarca signoMarca = sigSignoMarcaService.listaSigSignoMarcaXRegistro(Long.parseLong(noti.getExpediente()), noti.getExtension(), "");
            if(signoMarca != null)
            { estado = signoMarca.getEstadoMarca();
            
            }

        }
        if (noti.getTipo_tramite_notificacion().equals("N° PUB SIGNO")) {
            SigSignoMarca signoMarca = sigSignoMarcaService.listaSigSignoMarcaXPublicacion(Long.parseLong(noti.getExpediente()));
            estado = signoMarca.getEstadoMarca();

        }
        if ((!noti.getTipo_tramite_notificacion().equals("SM"))
                && (!noti.getTipo_tramite_notificacion().equals("SR"))
                && (!noti.getTipo_tramite_notificacion().equals("N° REG"))
                && (!noti.getTipo_tramite_notificacion().equals("N° PUB SIGNO"))
                && (!noti.getTipo_tramite_notificacion().equals("CANCELACION"))
                && (!noti.getTipo_tramite_notificacion().equals("OPOSICION SIG"))
                && (!noti.getTipo_tramite_notificacion().equals("NULIDAD SIG"))
                && (!noti.getTipo_tramite_notificacion().equals("BQ"))
                && (!noti.getTipo_tramite_notificacion().equals("CE"))
                && (!noti.getTipo_tramite_notificacion().equals("IF"))
                && (!noti.getTipo_tramite_notificacion().equals("MEF"))) {
            ModModificacion modi = modModificacionService.buscarModModificacionXCodigo(noti.getTipo_tramite_notificacion(),
                    Long.parseLong(noti.getExpediente()),
                    noti.getGestion());
            if(modi != null)
            {  //System.out.println("El estado de modi aqui:" + modi.getEstado_modificacion());
                        estado = modi.getEstado_modificacion();
            }
            else{
             //   System.out.println("El exoediente:"+   Long.parseLong(noti.getExpediente())+" es nulo.");
            }
        }
        if (noti.getTipo_tramite_notificacion().equals("SR")) {
            RenSolicitudRenovacion ren = renSolicitudRenovacionService.buscaSolicitudRenovacionPorNumeroSrYGestion(Long.parseLong(noti.getExpediente()),
                    noti.getGestion());
            estado = ren.getEstado_renovacion();
        }

        //}
        return estado;

    }

    //este metodo cambia el estado de "Situacion actual" a "notificacion" en la pantalla de examen de signos
    public void guardaEstadoNotifica(List<Notificacion> lista) throws Exception {
        Long sm;
        //System.out.println("tipo_tramite1::::"+lista.get(0).getTipo_tramite_notificacion());
        //System.out.println("Entra....");
        for (int i = 0; i <= lista.size() - 1; i++) {
            if (lista.get(i).getTipo_tramite_notificacion().equals("SM")) {
                // System.out.println("tipo_tramite::"+lista.get(i).getTipo_tramite_notificacion()+"gestion::"+ lista.get(i).getGestion().toString());
                if (listaAPeticion.get(i).getExtension() == null) {
                    sm = comunService.codificarCodigoSM(lista.get(i).getExpediente(), lista.get(i).getGestion().toString(), "");
                } else {

                    sm = comunService.codificarCodigoSM(lista.get(i).getExpediente(), lista.get(i).getGestion().toString(), lista.get(i).getExtension());
                }

                SigSignoMarca signoMarca = sigSignoMarcaService.listaSigSignoMarcaXSM(sm);

                signoMarca.setEstadoMarca("NOT");
                sigSignoMarcaService.crudSigSignoMarca(signoMarca, 2);

            }
            if (lista.get(i).getTipo_tramite_notificacion().equals("N° REG")) {
                SigSignoMarca signoMarca = sigSignoMarcaService.listaSigSignoMarcaXRegistro(Long.parseLong(lista.get(i).getExpediente()), lista.get(i).getExtension(), "");
                if(signoMarca != null)
                { signoMarca.setEstadoMarca("NOT");
                 sigSignoMarcaService.crudSigSignoMarca(signoMarca, 2);
                }
            }
            if (lista.get(i).getTipo_tramite_notificacion().equals("N° PUB SIGNO")) {
                SigSignoMarca signoMarca = sigSignoMarcaService.listaSigSignoMarcaXPublicacion(Long.parseLong(lista.get(i).getExpediente()));
                signoMarca.setEstadoMarca("NOT");
                sigSignoMarcaService.crudSigSignoMarca(signoMarca, 2);
            }

        }

    }

    public void saleDialogo() {

        RequestContext.getCurrentInstance().closeDialog("a");

    }

    public boolean isHabImpr() {
        return habImpr;
    }

    public void setHabImpr(boolean habImpr) {
        this.habImpr = habImpr;
    }

    public ComunService getComunService() {
        return comunService;
    }

    public void setComunService(ComunService comunService) {
        this.comunService = comunService;
    }

    public void eliminaBloque() {
        this.listaAPeticion.clear();

    }

    public List<HistorialPojo> getListHistPo() {
        return listHistPo;
    }

    public void setListHistPo(List<HistorialPojo> listHistPo) {
        this.listHistPo = listHistPo;
    }

    public Integer getNumBloqueBusca() {
        return numBloqueBusca;
    }

    public void setNumBloqueBusca(Integer numBloqueBusca) {
        this.numBloqueBusca = numBloqueBusca;
    }

    public List<Dominio> getListaTipoNotifi() {
        return listaTipoNotifi;
    }

    public void setListaTipoNotifi(List<Dominio> listaTipoNotifi) {
        this.listaTipoNotifi = listaTipoNotifi;
    }

    public Integer getNumBloque() {
        return numBloque;
    }

    public void setNumBloque(Integer numBloque) {
        this.numBloque = numBloque;
    }

    public String getText() {
        System.out.println("en el get:" + text);
        return text;
    }

    public void setText(String text) {
        System.out.println("en el set:" + text);
        this.text = text;
    }

    public String getComboExpediente() {
        return comboExpediente;
    }

    public void setComboExpediente(String comboExpediente) {
        this.comboExpediente = comboExpediente;
    }

    public String getCodigoPrin() {
        return codigoPrin;
    }

    public void setCodigoPrin(String codigoPrin) {
        this.codigoPrin = codigoPrin;
    }

    public String getTipoTramite() {
        return tipoTramite;
    }

    public void setTipoTramite(String tipoTramite) {
        this.tipoTramite = tipoTramite;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public String getVar() {
        return var;
    }

    public void setVar(String var) {
        this.var = var;
    }

    public boolean getHabOposocion() {
        return habOposocion;
    }

    public void setHabOposocion(boolean habOposocion) {
        this.habOposocion = habOposocion;
    }

    public boolean getHabBuscar() {
        return habBuscar;
    }

    public void setHabBuscar(boolean habBuscar) {
        this.habBuscar = habBuscar;
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

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getExpediente() {
        return expediente;
    }

    public void setExpediente(String expediente) {
        this.expediente = expediente;
    }

    public Integer getPos() {
        return pos;
    }

    public void setPos(Integer pos) {
        this.pos = pos;
    }

    public boolean getHabAdiciona() {
        return habAdiciona;
    }

    public void setHabAdiciona(boolean habAdiciona) {
        this.habAdiciona = habAdiciona;
    }

    public boolean getHabModifica() {
        return habModifica;
    }

    public void setHabModifica(boolean habModifica) {
        this.habModifica = habModifica;
    }

    public boolean isHabNotifica() {
        return habNotifica;
    }

    public void setHabNotifica(boolean habNotifica) {
        this.habNotifica = habNotifica;
    }

    public List<NotiPeticionListPojo> getListNotiPeticionList() {
        return listNotiPeticionList;
    }

    public void setListNotiPeticionList(List<NotiPeticionListPojo> listNotiPeticionList) {
        this.listNotiPeticionList = listNotiPeticionList;
    }

    public boolean getHabElimina() {
        return habElimina;
    }

    public void setHabElimina(boolean habElimina) {
        this.habElimina = habElimina;
    }

    public Integer getNumIntoBloque() {
        return numIntoBloque;
    }

    public void setNumIntoBloque(Integer numIntoBloque) {
        this.numIntoBloque = numIntoBloque;
    }

    public String getCreaModifieli() {
        return creaModifieli;
    }

    public void setCreaModifieli(String creaModifieli) {
        this.creaModifieli = creaModifieli;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDemanSolicitante() {
        return demanSolicitante;
    }

    public void setDemanSolicitante(String demanSolicitante) {
        this.demanSolicitante = demanSolicitante;
    }

    public String getDemanApoderado() {
        return demanApoderado;
    }

    public void setDemanApoderado(String demanApoderado) {
        this.demanApoderado = demanApoderado;
    }

    public String getDemanFojas() {
        return demanFojas;
    }

    public void setDemanFojas(String demanFojas) {
        this.demanFojas = demanFojas;
    }

    public String getDemanNotificarCon() {
        return demanNotificarCon;
    }

    public void setDemanNotificarCon(String demanNotificarCon) {
        this.demanNotificarCon = demanNotificarCon;
    }

    public String getDemanCelular() {
        return demanCelular;
    }

    public void setDemanCelular(String demanCelular) {
        this.demanCelular = demanCelular;
    }

    public String getDemanDomicilio() {
        return demanDomicilio;
    }

    public void setDemanDomicilio(String demanDomicilio) {
        this.demanDomicilio = demanDomicilio;
    }

    public List<Notificacion> getListaAPeticion() {
        return listaAPeticion;
    }

    public void setListaAPeticion(List<Notificacion> listaAPeticion) {
        this.listaAPeticion = listaAPeticion;
    }

    public List<Notificacion> getListaEscoge() {
        return listaEscoge;
    }

    public void setListaEscoge(List<Notificacion> listaEscoge) {
        this.listaEscoge = listaEscoge;
    }

    public boolean getHabGuardaCamb() {
        return habGuardaCamb;
    }

    public void setHabGuardaCamb(boolean habGuardaCamb) {
        this.habGuardaCamb = habGuardaCamb;
    }

    public String getDanteMarca() {
        return danteMarca;
    }

    public void setDanteMarca(String danteMarca) {
        this.danteMarca = danteMarca;
    }

    public String getDanteSolicitante() {
        return danteSolicitante;
    }

    public void setDanteSolicitante(String danteSolicitante) {
        this.danteSolicitante = danteSolicitante;
    }

    public String getDanteApoderado() {
        return danteApoderado;
    }

    public void setDanteApoderado(String danteApoderado) {
        this.danteApoderado = danteApoderado;
    }

    public String getDanteFojas() {
        return danteFojas;
    }

    public void setDanteFojas(String danteFojas) {
        this.danteFojas = danteFojas;
    }

    public String getDanteNotificarCon() {
        return danteNotificarCon;
    }

    public void setDanteNotificarCon(String danteNotificarCon) {
        this.danteNotificarCon = danteNotificarCon;
    }

    public String getDanteCelular() {
        return danteCelular;
    }

    public void setDanteCelular(String danteCelular) {
        this.danteCelular = danteCelular;
    }

    public String getDanteDomicilio() {
        return danteDomicilio;
    }

    public void setDanteDomicilio(String danteDomicilio) {
        this.danteDomicilio = danteDomicilio;
    }

    public String getDadoMarca() {
        return dadoMarca;
    }

    public void setDadoMarca(String dadoMarca) {
        this.dadoMarca = dadoMarca;
    }

    public String getDadoSolicitante() {
        return dadoSolicitante;
    }

    public void setDadoSolicitante(String dadoSolicitante) {
        this.dadoSolicitante = dadoSolicitante;
    }

    public String getDadoApoderado() {
        return dadoApoderado;
    }

    public void setDadoApoderado(String dadoApoderado) {
        this.dadoApoderado = dadoApoderado;
    }

    public String getDadoFojas() {
        return dadoFojas;
    }

    public void setDadoFojas(String dadoFojas) {
        this.dadoFojas = dadoFojas;
    }

    public String getDadoNotificarCon() {
        return dadoNotificarCon;
    }

    public void setDadoNotificarCon(String dadoNotificarCon) {
        this.dadoNotificarCon = dadoNotificarCon;
    }

    public String getDadoCelular() {
        return dadoCelular;
    }

    public void setDadoCelular(String dadoCelular) {
        this.dadoCelular = dadoCelular;
    }

    public String getDadoDomicilio() {
        return dadoDomicilio;
    }

    public void setDadoDomicilio(String dadoDomicilio) {
        this.dadoDomicilio = dadoDomicilio;
    }

    public UsuarioPaginaService getUsuarioPaginaService() {
        return usuarioPaginaService;
    }

    public void setUsuarioPaginaService(UsuarioPaginaService usuarioPaginaService) {
        this.usuarioPaginaService = usuarioPaginaService;
    }

    public String getObsPeticion() {
        return obsPeticion;
    }

    public void setObsPeticion(String obsPeticion) {
        this.obsPeticion = obsPeticion;
    }

    public NotificacionService getNotificacionService() {
        return notificacionService;
    }

    public void setNotificacionService(NotificacionService notificacionService) {
        this.notificacionService = notificacionService;
    }

    public DominioService getDominioService() {
        return dominioService;
    }

    public void setDominioService(DominioService dominioService) {
        this.dominioService = dominioService;
    }

    public Long getIdUsuarioSesion() {
        return idUsuarioSesion;
    }

    public void setIdUsuarioSesion(Long idUsuarioSesion) {
        this.idUsuarioSesion = idUsuarioSesion;
    }

    public List<Usuario> getListUsuario() {
        return listUsuario;
    }

    public void setListUsuario(List<Usuario> listUsuario) {
        this.listUsuario = listUsuario;
    }

    public Map<String, Object> getParametros() {
        return parametros;
    }

    public void setParametros(Map<String, Object> parametros) {
        this.parametros = parametros;
    }

    public String getNroBloque() {
        return nroBloque;
    }

    public void setNroBloque(String nroBloque) {
        this.nroBloque = nroBloque;
    }

    public String getOperador() {
        return operador;
    }

    public void setOperador(String operador) {
        this.operador = operador;
    }

    public String getFechaIng() {
        return fechaIng;
    }

    public void setFechaIng(String fechaIng) {
        this.fechaIng = fechaIng;
    }

    public String getHoraIng() {
        return horaIng;
    }

    public void setHoraIng(String horaIng) {
        this.horaIng = horaIng;
    }

    public String getImgSenapi() {
        return imgSenapi;
    }

    public void setImgSenapi(String imgSenapi) {
        this.imgSenapi = imgSenapi;
    }

    public UsuarioService getUsuarioService() {
        return usuarioService;
    }

    public void setUsuarioService(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    public Long getLogtrans() {
        return logtrans;
    }

    public void setLogtrans(Long logtrans) {
        this.logtrans = logtrans;
    }

    public LogTransService getLogTransService() {
        return logTransService;
    }

    public void setLogTransService(LogTransService logTransService) {
        this.logTransService = logTransService;
    }

    public SigSignoMarcaService getSigSignoMarcaService() {
        return sigSignoMarcaService;
    }

    public void setSigSignoMarcaService(SigSignoMarcaService sigSignoMarcaService) {
        this.sigSignoMarcaService = sigSignoMarcaService;
    }

    public SigHistorialService getSigHistorialService() {
        return sigHistorialService;
    }

    public void setSigHistorialService(SigHistorialService sigHistorialService) {
        this.sigHistorialService = sigHistorialService;
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

}
