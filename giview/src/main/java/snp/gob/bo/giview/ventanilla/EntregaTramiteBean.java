/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.giview.ventanilla;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Writer;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
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
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.primefaces.model.DualListModel;
import org.springframework.ui.context.Theme;
import snp.gob.bo.entidades.bean.common.AbstractManagedBean;
import snp.gob.bo.gimodel.entidad.Dominio;
import snp.gob.bo.gimodel.entidad.Etapa;
import snp.gob.bo.gimodel.entidad.LogTrans;
import snp.gob.bo.gimodel.entidad.ModModificacion;
import snp.gob.bo.gimodel.entidad.Procurador;
import snp.gob.bo.gimodel.entidad.RenSolicitudRenovacion;
import snp.gob.bo.gimodel.entidad.Seguimiento;
import snp.gob.bo.gimodel.entidad.SigSignoMarca;
import snp.gob.bo.gimodel.entidad.TramiteEntrega;
import snp.gob.bo.gimodel.enums.EnumCarpetasTemporales;
import snp.gob.bo.gimodel.entidad.Usuario;
import snp.gob.bo.gimodel.enums.EnumDominio;
import snp.gob.bo.gimodel.enums.EnumEstado;
import snp.gob.bo.gimodel.enums.EnumPrefijoTablas;
import snp.gob.bo.gimodel.enums.EnumTipoTitular;
import snp.gob.bo.gimodel.servicio.ComunService;
import snp.gob.bo.gimodel.servicio.DominioService;
import snp.gob.bo.gimodel.servicio.EntregaTramiteService;
import snp.gob.bo.gimodel.servicio.EtapaService;
import snp.gob.bo.gimodel.servicio.LogTransService;
import snp.gob.bo.gimodel.servicio.ModModificacionService;
import snp.gob.bo.gimodel.servicio.ProcuradorService;
import snp.gob.bo.gimodel.servicio.RegionalService;
import snp.gob.bo.gimodel.servicio.RenSolicitudRenovacionService;
import snp.gob.bo.gimodel.servicio.SeguimientoService;
import snp.gob.bo.gimodel.servicio.SigSignoMarcaService;
import snp.gob.bo.gimodel.servicio.UsuarioService;

/**
 *
 * @author eddy
 */
@ManagedBean(name = "entregaTramiteBean")
@ViewScoped
public class EntregaTramiteBean extends AbstractManagedBean implements Serializable {

    private String template = "./../WEB-INF/facelets/templates/Template.xhtml";
    private String areaRegistro = EnumPrefijoTablas.SIGNO.getCodigo();
    private Long id;
    private Date fechaServidor;
    private String filtroArea = "SIG";
    Date fechaExamenForma = new Date();
    private List<Seguimiento> listaSeguimiento;
    private Boolean buscadorSignosRender = true;
    private Boolean buscadorSolicitanteApoderado = false;
    private Boolean buscadorRegistro = false;
    private Boolean buscadorPublicacion = false;
    private String numeroExpediente;
    private List<Dominio> lstTipoTramiteRecibo = new ArrayList<>();
    private Boolean buscadorOtros = false;
    private String tipoTramiteCombo = " ";
    private Boolean buscadorModificacionesRender = false;
    private Boolean buscadorRenovacionesRender = false;
    private String filtroSolicitud = "SM";
    private Boolean buscadorSigSMRender = true;
    private Boolean buscadorSigPublicacionRender = false;
    private Boolean buscadorSigRegistroRender = false;
    private Boolean buscadorModRender = false;
    private Boolean buscadorRenRender = false;
    private String valorTipoModificacion;
    private String valorTipoSolApod;
    private String soliciApod;
    private Long registro;
    private String valorSerieRegistro;
    private Long publicacion;
    private String comboTipoTitular;
    private String comboTipoDocumento;
    private String comboDepartamento;
    private LogTrans logTransGuardado;
    private String procuNombre;
    private String procuPrimerApe;
    private String procuSegundoApe;
    private String procuTipoTitular;
    private String procuNumeroDocumento;
    private String procuTipoDocumento;
    private String procuLugarExpedicion;
    private String procuTelefono;
    private String procuCelular;
    private String procuEstado;
    private String procuTipoDocAuto;
    private String procuDescDocuAuto;
    private Integer totalBuzon;
    private Integer gestionTramite;
    private boolean muestraImprime = true;
    private boolean muestraBloque = false;
    private boolean muestraAceptar = false;
    private boolean muestraBuscar = false;
    private String bloqueMuestra;
    private String letraRegionalMuestra;
    private String GestionMuestra;
    private String bloqueResult;
    public String extensionExpediente = "";
    private Long numeroSolicitud;
    private Integer gestionSolicitud;
    private SigSignoMarca encontradoSigno = new SigSignoMarca();
    private Long registroSolicitud;
    private String serieRegistro = "C";
    private Long numeroPublicacion;
    private ModModificacion encontradoModificacion;
    private RenSolicitudRenovacion encontradoRenovacion = new RenSolicitudRenovacion();
    private String solicitud = "";
    private Boolean filtroAreaDisabled = false;
    private String tipoTramite;
    private Long platoRealTotal = 0l;
    private Integer plazoTotal = 0;
    private Integer totalPlazoLimite = 0;
    private Long idregional;
    private Integer tiempoPasivo = 0;
    private Integer plazoEstablecido = 0;
    private String marca;
    private Integer numeroClaseNiza;
    private DualListModel<String> cities;
    private DualListModel<Theme> themes;

    private List<TramiteEntrega> tramitesIzq = new ArrayList<TramiteEntrega>();
    private List<TramiteEntrega> tramitesIzqAgarrados = new ArrayList<TramiteEntrega>();
    private List<TramiteEntrega> tramitesDer = new ArrayList<TramiteEntrega>();
    private List<TramiteEntrega> tramitesDerAgarrados = new ArrayList<TramiteEntrega>();

    private List<Dominio> listTipoTitular = new ArrayList<Dominio>();
    private List<Dominio> listTipoDocumento = new ArrayList<Dominio>();
    private List<Dominio> listDepartamento = new ArrayList<Dominio>();
    private List<Dominio> listTipoDocuAutoriza = new ArrayList<Dominio>();
    private Boolean[] validaDatosEntrega = new Boolean[15];

    // parametros del reporte
    private Map<String, Object> parametros = new HashMap();

    @ManagedProperty("#{seguimientoService}")
    private SeguimientoService seguimientoService;
    @ManagedProperty("#{modModificacionService}")
    private ModModificacionService modModificacionService;
    @ManagedProperty("#{sigSignoMarcaService}")
    private SigSignoMarcaService sigSignoMarcaService;
    @ManagedProperty("#{comunService}")
    private ComunService comunService;
    @ManagedProperty("#{renSolicitudRenovacionService}")
    private RenSolicitudRenovacionService renSolicitudRenovacionService;
    @ManagedProperty("#{etapaService}")
    private EtapaService etapaService;

    @ManagedProperty("#{regionalService}")
    private RegionalService regionalService;
    @ManagedProperty("#{dominioService}")
    private DominioService dominioService;

    @ManagedProperty("#{entregaTramiteService}")
    private EntregaTramiteService entregaTramiteService;
    @ManagedProperty("#{procuradorService}")
    private ProcuradorService procuradorService;
    @ManagedProperty("#{usuarioService}")
    private UsuarioService usuarioService;
    @ManagedProperty("#{logTransService}")
    private LogTransService logTransService;

    @PostConstruct
    public void initEntregaTramiteBean() {

        try {
            listTipoTitular = dominioService.obtenerListadoDominio("tipo_titular");
            listTipoDocumento = dominioService.obtenerListadoDominio("tipo_documento");
            listDepartamento = dominioService.obtenerListadoDominio("departamento");
            // listTipoDocuAutoriza=dominioService.obtenerListadoDominio("tipo_doc_entrega");
            Date fechaSistema = comunService.obtenerFechaHoraServidor(1L);
            logTransGuardado = logTransService.crudLogTrans(new LogTrans(super.getIdUsuarioSession(), fechaSistema), 1);

            Map<String, String[]> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterValuesMap();
            if (params.get("datosEnviados") != null) {
                template = "./../WEB-INF/facelets/templates/DialogTemplate.xhtml";
                if (params.get("datosEnviados")[0] != null) {
                    areaRegistro = params.get("datosEnviados")[1];
                    filtroArea = areaRegistro;
                    filtroAreaDisabled = true;
                    if (params.get("datosEnviados")[2] != null) {
                        id = Long.parseLong(params.get("datosEnviados")[2]);
                        listaSeguimiento = seguimientoService.lista_SeguimientoXid(areaRegistro, id);
                        solicitud = params.get("datosEnviados")[3];
                        idregional = Long.parseLong(params.get("datosEnviados")[4]);
                        platoRealTotal = seguimientoService.conteoTotalTiempo(listaSeguimiento, areaRegistro, idregional);
                        totalPlazoLimite = seguimientoService.conteoPlazoLimite(areaRegistro, id);
                        tiempoPasivo = seguimientoService.conteoTiempoPasivo(areaRegistro, id);
                        if (areaRegistro.equals(EnumPrefijoTablas.SIGNO.getCodigo())) {
                            plazoEstablecido = 150;
                        }
                        if (areaRegistro.equals(EnumPrefijoTablas.RENOVACION.getCodigo())) {
                            plazoEstablecido = 15;
                        }
                        if (areaRegistro.equals(EnumPrefijoTablas.MODIFICACION.getCodigo())) {
                            plazoEstablecido = 15;
                        }
                        // for (Seguimiento item : listaSeguimiento) {
                        //    platoRealTotal = platoRealTotal + item.getPlazoReal();                                                                                                                                                                                                               
                        // plazoTotal = plazoTotal + etapaService.listar_etapa_tipoTramite_tipoEtapa("", item.getEtapa()).getPlazo();
                        //System.out.println("etapaService.listar_etapa_tipoTramite_tipoEtapa(\"\",item.getEtapa()).getPlazo()::"+etapaService.listar_etapa_tipoTramite_tipoEtapa("",item.getEtapa()).getPlazo());
                        //System.out.println("plazoTotal:"+plazoTotal);                                            
                        //}
//                        if(!listaSeguimiento.isEmpty()){
//                        platoRealTotal=listaSeguimiento.get(listaSeguimiento.size()-1).getTotalTiempo().intValue();}
                    }
                    activaCambios();
                    activaCambiosBuscadorSolicitud();
                }
            } else {
                template = "./../WEB-INF/facelets/templates/Template.xhtml";
                vaciarVariables();

            }
        } catch (Exception ex) {
            Logger.getLogger(EntregaTramiteBean.class.getName()).log(Level.SEVERE, null, ex);
        }

        List<String> citiesSource = new ArrayList<String>();
        List<String> citiesTarget = new ArrayList<String>();

        citiesSource.add("San Francisco");
        citiesSource.add("London");
        citiesSource.add("Paris");
        citiesSource.add("Istanbul");
        citiesSource.add("Berlin");
        citiesSource.add("Barcelona");
        citiesSource.add("Rome");

        //cities = new DualListModel<String>(citiesTarget);
        //Themes
        // List<Theme> themesSource = service.getThemes().subList(0, 5);
        List<Theme> themesTarget = new ArrayList<Theme>();

        themes = new DualListModel<>();

    }

    public void modificarObservacion(Seguimiento seguimiento) {

        seguimientoService.guardar_modificar_listar_Seguimiento(seguimiento, areaRegistro, 2);
    }

    public void vaciarVariables() {
        registroSolicitud = null;
        serieRegistro = "";
        numeroPublicacion = null;
        extensionExpediente = "";
        numeroSolicitud = null;
        gestionSolicitud = null;
        platoRealTotal = 0l;
        plazoTotal = 0;
        totalPlazoLimite = 0;
        tiempoPasivo = 0;
        plazoEstablecido = 0;
        listaSeguimiento = new ArrayList<Seguimiento>();
        solicitud = "";
    }

    public String[] etapaDescripcion(String codigo, Long idseguimiento) throws Exception {

        String obs = "";
        Integer plazoObservado;
        if (codigo.equals("EXFM") || codigo.equals("ANR")) //if(codigo.equals("EXFM"))
        {
            //List<Seguimiento> listSeguimientoUltimos = seguimientoService.listaSeguimientoUltimoXIdsignoEtapaUltMov(listaSeguimiento.get(0).getId(), codigo);
            if (seguimientoService.listaSeguimientoUltimoXIdsignoEtapaUltMov(listaSeguimiento.get(0).getId(), codigo, idseguimiento) != null) {
                obs = "(obs)";
            }

        }

        String[] descripcion = {"", ""};
        Etapa etapa = etapaService.listar_etapa_tipoTramite_tipoEtapa(tipoTramite, codigo);
        if (etapa != null) {
            descripcion[0] = etapa.getDescripcion() + obs;
            //La devolución  del plazo ya seria inservible porq tengo  en la tabla segumiento el campo plazo_limite qeu directo imprimo en la vista  
            descripcion[1] = etapa.getPlazo().toString();

        }
        return descripcion;
    }

    public String colorAlerta(Integer plazoLimite, Integer plazoReal) throws Exception {
        String color = "5AEA12";
        Integer valor = plazoLimite - plazoReal;
        if (valor >= 3) {
            color = "#000000";//azul
        }
        if (valor == 2 || valor == 1 || valor == 0) {
            color = "#32A02F";//lila
        }
        if (valor < 0) {
            color = "#F60310";//rojo
        }

        return color;
    }

    public void activaCambios() {
        switch (filtroArea) {
            case "SIG":
                buscadorSignosRender = true;
                buscadorSolicitanteApoderado = false;
                buscadorRegistro = false;
                buscadorPublicacion = false;
                buscadorOtros = false;
//                filtroSolicitud = "SM";
//                areaRegistro = EnumPrefijoTablas.SIGNO.getCodigo();
//                tipoTramite = EnumTipoTramite.REGISTRO_MARCAS.getCodigo();
                break;
            case "SOL":
                buscadorSignosRender = false;
                buscadorSolicitanteApoderado = true;
                buscadorRegistro = false;
                buscadorPublicacion = false;
                buscadorOtros = false;
//                filtroSolicitud = "MO";
//                areaRegistro = EnumPrefijoTablas.MODIFICACION.getCodigo();
//                tipoTramite = EnumTipoTramite.MODIFICACION.getCodigo();
                break;
            case "NREG":
                buscadorSignosRender = false;
                buscadorSolicitanteApoderado = false;
                buscadorRegistro = true;
                buscadorPublicacion = false;
                buscadorOtros = false;
//                filtroSolicitud = "SR";
//                areaRegistro = EnumPrefijoTablas.RENOVACION.getCodigo();
//                tipoTramite = EnumTipoTramite.RENOVACION.getCodigo();
                break;
            case "NPUB":
                buscadorSignosRender = false;
                buscadorSolicitanteApoderado = false;
                buscadorRegistro = false;
                buscadorPublicacion = true;
                buscadorOtros = false;
//                filtroSolicitud = "SR";
//                areaRegistro = EnumPrefijoTablas.RENOVACION.getCodigo();
//                tipoTramite = EnumTipoTramite.RENOVACION.getCodigo();
                break;
            case "OTR":
                buscadorSignosRender = false;
                buscadorSolicitanteApoderado = false;
                buscadorRegistro = false;
                buscadorPublicacion = false;
                buscadorOtros = true;
//                filtroSolicitud = "SR";
//                areaRegistro = EnumPrefijoTablas.RENOVACION.getCodigo();
//                tipoTramite = EnumTipoTramite.RENOVACION.getCodigo();
                break;
            default:
                buscadorSignosRender = false;
                buscadorSolicitanteApoderado = false;
                buscadorRenovacionesRender = false;
                break;
        }
        activaCambiosBuscadorSolicitud();
        if (filtroAreaDisabled == false) {
            vaciarVariables();
        }
    }

    public void activaCambiosBuscadorSolicitud() {
        switch (filtroSolicitud) {
            case "SM":
                buscadorSigSMRender = true;
                //buscadorSigPublicacionRender = false;
                //buscadorSigRegistroRender = false;
                buscadorModRender = false;
                buscadorRenRender = false;
                break;
            case "MOD":
                buscadorSigSMRender = false;
                //buscadorSigPublicacionRender = false;
                //buscadorSigRegistroRender = false;
                buscadorModRender = true;
                buscadorRenRender = false;
                break;
            case "SR":
                buscadorSigSMRender = true;
                //buscadorSigPublicacionRender = false;
                //buscadorSigRegistroRender = false;
                buscadorModRender = false;
                buscadorRenRender = false;
                break;
//            case "PUB":
//                buscadorSigSMRender = false;
//                buscadorSigPublicacionRender = true;
//                buscadorSigRegistroRender = false;
//                buscadorModRender= false;
//                break;
//            case "REG":
//                buscadorSigSMRender = false;
//                buscadorSigPublicacionRender = false;
//                buscadorSigRegistroRender = true;
//                buscadorModRender= false;
//                break;
            default:
                buscadorSigSMRender = false;
                buscadorSigPublicacionRender = false;
                buscadorSigRegistroRender = false;
                buscadorModRender = false;
                break;
        }
    }

    public void llenarOtrosDatos() {
        platoRealTotal = seguimientoService.conteoTotalTiempo(listaSeguimiento, areaRegistro, idregional);
        totalPlazoLimite = seguimientoService.conteoPlazoLimite(areaRegistro, id);
        tiempoPasivo = seguimientoService.conteoTiempoPasivo(areaRegistro, id);
        if (areaRegistro.equals(EnumPrefijoTablas.SIGNO.getCodigo())) {
            plazoEstablecido = 150;
        }
        if (areaRegistro.equals(EnumPrefijoTablas.RENOVACION.getCodigo())) {
            plazoEstablecido = 15;
        }
        if (areaRegistro.equals(EnumPrefijoTablas.MODIFICACION.getCodigo())) {
            plazoEstablecido = 15;
        }
    }

    public void buscaTramiteEmtrega() throws Exception {
        tramitesDer = entregaTramiteService.tramitesPorBloque(Integer.parseInt(valorTipoModificacion), gestionSolicitud, idregional);

    }

    public void buscaSeguimientoSignos() throws Exception {
        switch (filtroArea) {
            case "SIG":
                if (numeroSolicitud != null) {
                    if (filtroSolicitud.equals("SM")) {
                        tramitesIzq = entregaTramiteService.listaBuscador("TITR",
                                "SM",
                                numeroSolicitud.intValue(),
                                gestionSolicitud,
                                extensionExpediente,
                                null,
                                null,
                                0,
                                null,
                                0);
                    }
                    if (filtroSolicitud.equals("SR")) {
                        tramitesIzq = entregaTramiteService.listaBuscador("TITR",
                                "SR",
                                numeroSolicitud.intValue(),
                                gestionSolicitud,
                                null,
                                null,
                                null,
                                0,
                                null,
                                0);
                    }
                    if (filtroSolicitud.equals("MOD")) {
                        tramitesIzq = entregaTramiteService.listaBuscador("TITR",
                                valorTipoModificacion,
                                numeroSolicitud.intValue(),
                                gestionSolicitud,
                                null,
                                null,
                                null,
                                0,
                                null,
                                0);
                    }
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Verifique los datos de la busqueda", ""));

                }

                //System.out.println("tramitesIzq get0"+tramitesIzq.get(0).getIdtramite());
                break;
            case "SOL":
                if (!soliciApod.equals("")) {
                    tramitesIzq = entregaTramiteService.listaBuscador("SOAP",
                            null,
                            0,
                            0,
                            null,
                            valorTipoSolApod,
                            soliciApod,
                            0,
                            null,
                            0);
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Verifique los datos de la busqueda", ""));
                }
                break;
            case "NREG":

                if (registro != null) {
                    tramitesIzq = entregaTramiteService.listaBuscador("NURE",
                            null,
                            0,
                            0,
                            null,
                            null,
                            null,
                            registro.intValue(),
                            valorSerieRegistro,
                            0);
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Verifique los datos de la busqueda", ""));
                }
                break;
            case "NPUB":
                if (publicacion != null) {
                    tramitesIzq = entregaTramiteService.listaBuscador("NUPU",
                            null,
                            0,
                            0,
                            null,
                            null,
                            null,
                            0,
                            null,
                            publicacion.intValue());
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Verifique los datos de la busqueda", ""));
                }
                break;
        }
        if (tramitesIzq.isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "El tramite no fue encontrado", ""));
        }
        if (tramitesIzq.size() == 1) {
            if (entregaTramiteService.verificaTramiteEntrega(tramitesIzq.get(0).getIdtramite(), tramitesIzq.get(0).getTipo_tramite())) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "El tramite ya fue entregado", ""));
                tramitesIzq = new ArrayList<TramiteEntrega>();
            }
        }
        totalBuzon = tramitesIzq.size();

        //   System.out.println("tramitesIzq:"+tramitesIzq.get(0).getIdtramite());
    }

    public void agarraValores() {
        //   System.out.println("tramitesIzqAgarrados:"+tramitesIzqAgarrados.get(0).getNumerotramite());
        int sw = 0;
        for (int i = 0; i <= tramitesIzqAgarrados.size() - 1; i++) {
            for (int j = 0; j <= tramitesDer.size() - 1; j++) {
                // System.out.println("tramitesDer.get(j).getNumerotramite():"+tramitesDer.get(j).getNumerotramite());
                //  System.out.println("tramitesIzqAgarrados.get(i).getNumerotramite():"+tramitesIzqAgarrados.get(i).getNumerotramite());
                if (tramitesDer.get(j).getIdtramite().equals(tramitesIzqAgarrados.get(i).getIdtramite())) {
                    // System.out.println(" entraaaa");
                    sw = 1;
                }
            }
            tramitesIzq.remove(tramitesIzqAgarrados.get(i));
            //System.out.println("tramite agarrados conteo:"+tramitesIzqAgarrados.get(i).getConteo());
            if (sw == 0) {
                tramitesDer.add(tramitesIzqAgarrados.get(i));
            }
        }
        //  System.out.println("tramiteATraspasar primer elemento"+tramitesDer.get(0).getIdtramite());
        //  ordenaTablaIzq();
        //  ordenaTablaDer();
        tramitesIzqAgarrados.clear();

    }

    public void AdicionarListaDerecha() throws Exception {

        if (!tipoTramiteCombo.equals("") && !numeroExpediente.equals("") && (gestionTramite != null)) {

            if (!entregaTramiteService.verificaTramiteEntregaOtros(tipoTramiteCombo, Integer.parseInt(numeroExpediente), gestionTramite)) {
                TramiteEntrega tramiteEntregaOtro = new TramiteEntrega();
                tramiteEntregaOtro.setSigla(tipoTramiteCombo);
                tramiteEntregaOtro.setNumerotramite(Integer.parseInt(numeroExpediente));
                tramiteEntregaOtro.setGestion(gestionTramite);
                tramiteEntregaOtro.setMarca(marca);
                tramiteEntregaOtro.setClase_niza(numeroClaseNiza);
                tramiteEntregaOtro.setIdtramite(1L);
                tramiteEntregaOtro.setTipo_tramite("OTR");
                tramitesDer.add(tramiteEntregaOtro);
                tipoTramiteCombo = numeroExpediente = marca = null;
                numeroClaseNiza = null;
                gestionTramite = null;
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "El tramite ya fue entregado", ""));
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Verifique las casillas", ""));

        }

    }

    public void agarraValoreTodos() {
        for (int i = 0; i <= tramitesIzq.size() - 1; i++) {
            tramitesDer.add(tramitesIzq.get(i));
        }
        tramitesIzq.clear();
    }

    public void devuelveValores() {
        for (int i = 0; i <= tramitesDerAgarrados.size() - 1; i++) {  // System.out.println("tramite de vuelta::"+tramitesAgarrados2.get(i).getOperador());
            tramitesDer.remove(encuentraposTramiteEntrega(tramitesDerAgarrados.get(i), tramitesDer));
            tramitesIzq.add(tramitesDerAgarrados.get(i));
        }

        tramitesDerAgarrados.clear();
    }

    public int encuentraposTramiteEntrega(TramiteEntrega tramiteEliminar, List<TramiteEntrega> listaDerecha) {
        int pos = 0;

        for (int i = 0; i <= listaDerecha.size() - 1; i++) {
            if (listaDerecha.get(i).getSigla().equals(tramiteEliminar.getSigla())
                    && listaDerecha.get(i).getNumerotramite().equals(tramiteEliminar.getNumerotramite())
                    && listaDerecha.get(i).getGestion().equals(tramiteEliminar.getGestion())) {
                pos = i;
            }
        }
        return pos;

    }

    public void devuelveValoresTodos() {

        for (int i = 0; i <= tramitesDer.size() - 1; i++) {

            tramitesIzq.add(tramitesDer.get(i));

        }
        tramitesDer.clear();

    }

    public void insertaEntrega() throws Exception {
        //    System.out.println("ENtra.......");

        validaDatosEntrega = validadorGeneral();

        Procurador procu = new Procurador();

        if (validaDatosEntrega[4]) {

            procu.setNombre_razonsocial(procuNombre);

            procu.setNumero_documento(procuNumeroDocumento);

            if (procuPrimerApe != null && !procuPrimerApe.equals("")) {
                procu.setPrimer_apellido(procuPrimerApe);

            }
            if (procuSegundoApe != null && !procuSegundoApe.equals("")) {
                procu.setSegundo_apellido(procuSegundoApe);

            }
            procu.setTipo_titular(procuTipoTitular);
            procu.setTipo_documento(procuTipoDocumento);
            procu.setLugar_expedicion(procuLugarExpedicion);
            procu.setTelefono(procuTelefono);
            procu.setCelular(procuCelular);
            procu.setEstado(EnumEstado.ACTIVO.getCodigo());
            procu.setTipodocumentoautorizacion(procuTipoDocAuto);
            procu.setDescripciondocumentoautorizacion(procuDescDocuAuto);

            TramiteEntrega tra = entregaTramiteService.guardaTramiteEntrega(procu, tramitesDer, "ENTR", usuarioService.listaUsuarioXidPagina(super.getIdUsuarioSession()).get(0));

            //  System.out.println("tra regional"+tra.getIdregionalentrega());
            bloqueMuestra = tra.getNumerobloque().toString();
            letraRegionalMuestra = codigoRegional(usuarioService.listaUsuarioXidPagina(super.getIdUsuarioSession()).get(0));
            GestionMuestra = tra.getGestionbloque().toString();
            bloqueResult = bloqueMuestra + letraRegionalMuestra + "-" + GestionMuestra;
            muestraImprime = false;
            muestraBloque = true;
            muestraAceptar = true;
            //   muestraBuscar=true;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Datos guardados", ""));

        } else {
            muestraMensajes(validaDatosEntrega);
        }
        //  System.out.println("logTransGuardado.getIdLogTrans()::"+logTransGuardado.getIdLogTrans());
        //  System.out.println("usuarioService.listaUsuarioXidPagina(super.getIdUsuarioSession()).get(0)::"+usuarioService.listaUsuarioXidPagina(super.getIdUsuarioSession()).get(0).getIdusuario());

    }

    public String codigoRegional(Usuario usuario) {
        String labelSigla;
        int ope;
        ope = (usuario.getIdregional().intValue());
        switch (ope) {
            case 2:
                labelSigla = "-E";
                break;
            case 3:
                labelSigla = "-C";
                break;
            case 4:
                labelSigla = "-S";
                break;
            case 5:
                labelSigla = "-T";
                break;
            case 6:
                labelSigla = "-H";
                break;
            case 7:
                labelSigla = "-O";
                break;
            default:
                labelSigla = "";
                break;
        }
        return labelSigla;
    }

    public void generaNuevaEntrega() {

        numeroSolicitud = null;
        gestionSolicitud = null;
        extensionExpediente = null;
        soliciApod = "";
        publicacion = null;
        registro = null;
        tramitesIzq.clear();
        tramitesIzqAgarrados.clear();
        tramitesDer.clear();
        tramitesDerAgarrados.clear();
        procuNombre = "";
        procuPrimerApe = "";
        procuSegundoApe = "";
        procuNumeroDocumento = "";
        procuTelefono = "";
        procuCelular = "";
        //procuTipoDocAuto;
        procuDescDocuAuto = "";
        muestraImprime = true;
        muestraBloque = false;
        muestraAceptar = false;
        tipoTramiteCombo = numeroExpediente = null;
        gestionTramite = null;

    }

    public String convierteStr(TramiteEntrega tra) {
        String valor = "";
        if (!tra.getTipo_tramite().equals("OTR")) {
            valor = tra.getNumero_registro() + "-" + tra.getSerie_registro();
        }
        String str = String.valueOf(valor);
        return str;
    }

    public void buscaSeguimientoModificacion() throws Exception {
        switch (filtroSolicitud) {
            case "MO":
                if (numeroSolicitud != null && gestionSolicitud != null) {//&& gestionExpediente != null                       
                    encontradoModificacion = modModificacionService.buscarModModificacionXtipo(valorTipoModificacion, numeroSolicitud, gestionSolicitud);
                }
                break;
            case "REG":
                if (registroSolicitud != null) {
                    encontradoSigno = sigSignoMarcaService.listaSigSignoMarcaXRegistro(registroSolicitud, serieRegistro, " ");
                }
                break;
            case "PUB":
                if (numeroPublicacion != null) {
                    encontradoSigno = sigSignoMarcaService.listaSigSignoMarcaXPublicacion(numeroPublicacion);
                }
                break;
            default:
                break;
        }

        if (encontradoModificacion != null) {
            listaSeguimiento = seguimientoService.lista_SeguimientoXid(filtroArea, encontradoModificacion.getIdmodificacion());
            idregional = regionalService.lista_IdRegional_TipoCiudadNotificacion(encontradoModificacion.getOficina());
            id = encontradoModificacion.getIdmodificacion();
            solicitud = encontradoModificacion.getSigla() + "-" + String.format("%6s", numeroSolicitud).replace(' ', '0') + "-" + gestionSolicitud;
            llenarOtrosDatos();
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El tramite no existe", ""));
        }
    }

    public void buscaSeguimientoRenovacion() throws Exception {
        // System.out.println("filtro  " + filtroSolicitud);
        switch (filtroSolicitud) {
            case "SR":
                if (numeroSolicitud != null && gestionSolicitud != null) {//&& gestionExpediente != null                    
                    encontradoRenovacion = renSolicitudRenovacionService.buscaSolicitudRenovacionPorNumeroSrYGestion(numeroSolicitud, gestionSolicitud);
                }
                break;
            case "REG":
                if (registroSolicitud != null) {

                }
                break;
            case "PUB":
                if (numeroPublicacion != null) {

                }
                break;
            default:
                break;
        }
        if (encontradoRenovacion != null) {
            listaSeguimiento = seguimientoService.lista_SeguimientoXid(filtroArea, encontradoRenovacion.getIdsolicitudrenovacion());
            idregional = regionalService.lista_IdRegional_TipoCiudadNotificacion(encontradoRenovacion.getOficina());
            id = encontradoRenovacion.getIdsolicitudrenovacion();
            solicitud = "SR-" + String.format("%6s", numeroSolicitud).replace(' ', '0') + "-" + gestionSolicitud;
            llenarOtrosDatos();
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El tramite no existe", ""));
        }
    }

    public Long creaSM(String textoSM, Long numeroSM, Integer gestionSM, String extension) throws Exception {
        Long sm = 0l;
        //Long extension = new Long(Long.parseLong(extensionSM));
        if (numeroSM != null && gestionSM != null) {
            //sm = numeroSM + gestionSM;
            sm = comunService.codificarCodigoSM(numeroSM.toString(), gestionSM.toString(), extension);
        }
        return sm;
    }

    public void Imprimir() throws JRException, IOException, Exception {

        FacesContext facesContext = FacesContext.getCurrentInstance();
        ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();
        String rutaCarpeta = servletContext.getRealPath(EnumCarpetasTemporales.CARPETA_QR.getCodigo());
        File carpeta = new File(rutaCarpeta);
        if (!carpeta.exists()) {
            carpeta.mkdirs();
        }
        File[] ficheros = carpeta.listFiles();
        for (File fichero : ficheros) {
            fichero.delete();
        }

        String nroBloque = bloqueResult;

        List<TramiteEntrega> lista = entregaTramiteService.tramitesPorBloque(Integer.parseInt(bloqueMuestra), Integer.parseInt(GestionMuestra), usuarioService.listaUsuarioXidPagina(super.getIdUsuarioSession()).get(0).getIdregional());
        if (lista.size() > 0) {
            Procurador p = procuradorService.procuradorPorId(lista.get(0).getIdprocurador());

            String imageSenapi = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/images/logoNuevo.jpg");
            String imageEscudoBol = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/images/esc-Bolivia");

            Date fechaSistema = comunService.obtenerFechaHoraServidor(1L);

            SimpleDateFormat formateador1 = new SimpleDateFormat("dd/MM/yyyy");
            String fechaIngreso = formateador1.format(fechaSistema);
            SimpleDateFormat formateador2 = new SimpleDateFormat("HH:mm:ss");
            String horaIngreso = formateador2.format(fechaSistema);

            Integer total = lista.size();

            String solicitante = this.devuelveNombreJuridicoONatural(p.getNombre_razonsocial(), p.getPrimer_apellido(), p.getSegundo_apellido());
            String documento = this.devuelveNombreJuridicoONatural(p.getTipo_documento(), p.getNumero_documento(), p.getLugar_expedicion());

            String autorizacion = "";

            System.out.println("fgfgfgfgfg" + p.getTipodocumentoautorizacion());
            if (p.getTipodocumentoautorizacion() != null) {
                if (p.getDescripciondocumentoautorizacion() != null && !p.getDescripciondocumentoautorizacion().equals("")) {
                    autorizacion = dominioService.obtenerNombrePorCodigoDominio(p.getTipodocumentoautorizacion(), "tipo_doc_entrega") + ", " + p.getDescripciondocumentoautorizacion();
                } else {
                    if (!p.getTipodocumentoautorizacion().equals("null") && !p.getTipodocumentoautorizacion().equals("")) {
                        autorizacion = dominioService.obtenerNombrePorCodigoDominio(p.getTipodocumentoautorizacion(), "tipo_doc_entrega");
                    }
                }
            } else if (p.getCelular() != null) {
                autorizacion = p.getDescripciondocumentoautorizacion();
            }

            String telefono = "";
            if (p.getTelefono() != null) {
                if (p.getCelular() != null) {
                    telefono = p.getTelefono() + "-" + p.getCelular();
                } else {
                    telefono = p.getTelefono();
                }
            } else if (p.getCelular() != null) {
                telefono = p.getCelular();
            }

            int i = 0;
            List<TramitePojo> listaTramite = new ArrayList<>();
            for (TramiteEntrega item : lista) {
                TramitePojo tramite = new TramitePojo();
                i++;
                tramite.setNumero(i);
                tramite.setSigla(item.getSigla());
                tramite.setNumerotramite(item.getNumerotramite());
                tramite.setGestion(item.getGestion());
                String numeroreg = "";
                if (item.getNumero_registro() != null && item.getNumero_registro() != 0) {
                    if (item.getSerie_registro() != null) {
                        numeroreg = item.getNumero_registro() + "-" + item.getSerie_registro();
                    } else {
                        numeroreg = item.getNumero_registro().toString();
                    }
                }
                tramite.setNumeroregistro(numeroreg);
                tramite.setMarca(item.getMarca());
                tramite.setClase_niza(item.getClase_niza());
                listaTramite.add(tramite);
            }
            String codigo = solicitante + "|" + documento + "|" + fechaIngreso + "|" + nroBloque + "|" + total;
            String imagenDibuja = rutaCarpeta + File.separator + nroBloque;
            this.creaCodigoQR(codigo, imagenDibuja);
            String imgQR = imagenDibuja;

            parametros.put("imageSenapi", imageSenapi);
            parametros.put("imageEscudoBol", imageEscudoBol);
            parametros.put("nroBloque", nroBloque);
            parametros.put("total", total);
            parametros.put("fechaIngreso", fechaIngreso);
            parametros.put("horaIngreso", horaIngreso);
            parametros.put("solicitante", solicitante);
            parametros.put("documento", documento);
            parametros.put("telefono", telefono);
            parametros.put("imgQR", imgQR);
            parametros.put("autorizacion", autorizacion);

            String filename = "listaTramites.pdf";
            String jasperPath = "/template/formulario/reporListaEntregados.jasper";
            this.PDF(parametros, jasperPath, listaTramite, filename);
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "No existe el bloque", ""));
        }
    }

    public void creaCodigoQR(String texto, String ruta) throws WriterException, IOException {
        int width = 100;
        int height = 100;
        String imageFormat = "png";
        Writer writer = new QRCodeWriter();
        BitMatrix matriz = writer.encode(texto, BarcodeFormat.QR_CODE, width, height);
        FileOutputStream outputStream = new FileOutputStream(new File(ruta));
        MatrixToImageWriter.writeToStream(matriz, imageFormat, outputStream);
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
     * Metodo para generar el reporte en formato pdf
     *
     * Creado: Ruben Ramirez Fecha: 20/10/2016
     *
     * @param params
     * @param jasperPath
     * @param dataSource
     * @param fileName
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

    public Boolean[] validadorGeneral() {

        Boolean swGeneral = true;
        Boolean[] validaSectoresAux = {false, false, false, false, swGeneral};

        validaSectoresAux[0] = !tramitesDer.isEmpty();
        if (procuNombre != null && !procuNombre.equals("")) {
            validaSectoresAux[1] = true;
        }
        if (procuTipoTitular.equals(EnumTipoTitular.NATURAL.getCodigo())) {
            if (procuPrimerApe != null && !procuPrimerApe.equals("")) {
                validaSectoresAux[2] = true;
            }
        } else {
            validaSectoresAux[2] = true;
        }

        if (procuNumeroDocumento != null && !procuNumeroDocumento.equals("")) {
            validaSectoresAux[3] = true;
        }

        for (Boolean validaSector : validaSectoresAux) {
            if (validaSector == false) {
                swGeneral = false;
                validaSectoresAux[4] = swGeneral;
            }
        }

        return validaSectoresAux;
    }

    public int devuelveNumero(Integer index) {
        index++;
        return index;
    }

    public void muestraMensajes(Boolean[] validaDatosEntrega) {
        if (validaDatosEntrega[0] == false) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "No hay registros en la lista de entrega.", ""));
        }
        if (validaDatosEntrega[1] == false) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ingrese Nombre/Razón Social", ""));
        }
        if (validaDatosEntrega[2] == false) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ingrese apellido", ""));
        }

        if (validaDatosEntrega[3] == false) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ingrese Número de Documento", ""));
        }

    }

    //<editor-fold defaultstate="collapsed" desc="-----------------------------GET  SET----------------------">
    public boolean isMuestraAceptar() {
        return muestraAceptar;
    }

    public void setMuestraAceptar(boolean muestraAceptar) {
        this.muestraAceptar = muestraAceptar;
    }

    public Boolean[] getValidaDatosEntrega() {
        return validaDatosEntrega;
    }

    public void setValidaDatosEntrega(Boolean[] validaDatosEntrega) {
        this.validaDatosEntrega = validaDatosEntrega;
    }

    public String retornarPagina() {

        return getNavegaPagina();
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public SeguimientoService getSeguimientoService() {
        return seguimientoService;
    }

    public void setSeguimientoService(SeguimientoService seguimientoService) {
        this.seguimientoService = seguimientoService;
    }

    public List<Seguimiento> getListaSeguimiento() {
        return listaSeguimiento;
    }

    public void setListaSeguimiento(List<Seguimiento> listaSeguimiento) {
        this.listaSeguimiento = listaSeguimiento;
    }

    public String getFiltroArea() {
        return filtroArea;
    }

    public void setFiltroArea(String filtroArea) {
        this.filtroArea = filtroArea;
    }

    public Boolean getBuscadorSignosRender() {
        return buscadorSignosRender;
    }

    public void setBuscadorSignosRender(Boolean buscadorSignosRender) {
        this.buscadorSignosRender = buscadorSignosRender;
    }

    public Boolean getBuscadorModificacionesRender() {
        return buscadorModificacionesRender;
    }

    public void setBuscadorModificacionesRender(Boolean buscadorModificacionesRender) {
        this.buscadorModificacionesRender = buscadorModificacionesRender;
    }

    public Boolean getBuscadorRenovacionesRender() {
        return buscadorRenovacionesRender;
    }

    public void setBuscadorRenovacionesRender(Boolean buscadorRenovacionesRender) {
        this.buscadorRenovacionesRender = buscadorRenovacionesRender;
    }

    public String getFiltroSolicitud() {
        return filtroSolicitud;
    }

    public void setFiltroSolicitud(String filtroSolicitud) {
        this.filtroSolicitud = filtroSolicitud;
    }

    public Boolean getBuscadorSigSMRender() {
        return buscadorSigSMRender;
    }

    public void setBuscadorSigSMRender(Boolean buscadorSigSMRender) {
        this.buscadorSigSMRender = buscadorSigSMRender;
    }

    public Boolean getBuscadorSigPublicacionRender() {
        return buscadorSigPublicacionRender;
    }

    public void setBuscadorSigPublicacionRender(Boolean buscadorSigPublicacionRender) {
        this.buscadorSigPublicacionRender = buscadorSigPublicacionRender;
    }

    public Boolean getBuscadorSigRegistroRender() {
        return buscadorSigRegistroRender;
    }

    public void setBuscadorSigRegistroRender(Boolean buscadorSigRegistroRender) {
        this.buscadorSigRegistroRender = buscadorSigRegistroRender;
    }

    public List<TramiteEntrega> getTramitesIzq() {
        return tramitesIzq;
    }

    public void setTramitesIzq(List<TramiteEntrega> tramitesIzq) {
        this.tramitesIzq = tramitesIzq;
    }

    public List<TramiteEntrega> getTramitesIzqAgarrados() {
        return tramitesIzqAgarrados;
    }

    public void setTramitesIzqAgarrados(List<TramiteEntrega> tramitesIzqAgarrados) {
        this.tramitesIzqAgarrados = tramitesIzqAgarrados;
    }

    public List<TramiteEntrega> getTramitesDer() {
        return tramitesDer;
    }

    public void setTramitesDer(List<TramiteEntrega> tramitesDer) {
        this.tramitesDer = tramitesDer;
    }

    public List<TramiteEntrega> getTramitesDerAgarrados() {
        return tramitesDerAgarrados;
    }

    public void setTramitesDerAgarrados(List<TramiteEntrega> tramitesDerAgarrados) {
        this.tramitesDerAgarrados = tramitesDerAgarrados;
    }

    public String getValorTipoModificacion() {
        return valorTipoModificacion;
    }

    public void setValorTipoModificacion(String valorTipoModificacion) {
        this.valorTipoModificacion = valorTipoModificacion;
    }

    public ModModificacionService getModModificacionService() {
        return modModificacionService;
    }

    public void setModModificacionService(ModModificacionService modModificacionService) {
        this.modModificacionService = modModificacionService;
    }

    public SigSignoMarcaService getSigSignoMarcaService() {
        return sigSignoMarcaService;
    }

    public void setSigSignoMarcaService(SigSignoMarcaService sigSignoMarcaService) {
        this.sigSignoMarcaService = sigSignoMarcaService;
    }

    public String getExtensionExpediente() {
        return extensionExpediente;
    }

    public void setExtensionExpediente(String extensionExpediente) {
        this.extensionExpediente = extensionExpediente;
    }

    public ComunService getComunService() {
        return comunService;
    }

    public void setComunService(ComunService comunService) {
        this.comunService = comunService;
    }

    public Long getNumeroSolicitud() {
        return numeroSolicitud;
    }

    public void setNumeroSolicitud(Long numeroSolicitud) {
        this.numeroSolicitud = numeroSolicitud;
    }

    public Integer getGestionSolicitud() {
        return gestionSolicitud;
    }

    public void setGestionSolicitud(Integer gestionSolicitud) {
        this.gestionSolicitud = gestionSolicitud;
    }

    public Long getRegistroSolicitud() {
        return registroSolicitud;
    }

    public void setRegistroSolicitud(Long registroSolicitud) {
        this.registroSolicitud = registroSolicitud;
    }

    public String getSerieRegistro() {
        return serieRegistro;
    }

    public void setSerieRegistro(String serieRegistro) {
        this.serieRegistro = serieRegistro;
    }

    public Long getNumeroPublicacion() {
        return numeroPublicacion;
    }

    public void setNumeroPublicacion(Long numeroPublicacion) {
        this.numeroPublicacion = numeroPublicacion;
    }

    public RenSolicitudRenovacionService getRenSolicitudRenovacionService() {
        return renSolicitudRenovacionService;
    }

    public void setRenSolicitudRenovacionService(RenSolicitudRenovacionService renSolicitudRenovacionService) {
        this.renSolicitudRenovacionService = renSolicitudRenovacionService;
    }

    public String getSolicitud() {
        return solicitud;
    }

    public void setSolicitud(String solicitud) {
        this.solicitud = solicitud;
    }

    public Boolean getFiltroAreaDisabled() {
        return filtroAreaDisabled;
    }

    public void setFiltroAreaDisabled(Boolean filtroAreaDisabled) {
        this.filtroAreaDisabled = filtroAreaDisabled;
    }

    public EtapaService getEtapaService() {
        return etapaService;
    }

    public void setEtapaService(EtapaService etapaService) {
        this.etapaService = etapaService;
    }

    public Long getPlatoRealTotal() {
        return platoRealTotal;
    }

    public void setPlatoRealTotal(Long platoRealTotal) {
        this.platoRealTotal = platoRealTotal;
    }

    public Integer getPlazoTotal() {
        return plazoTotal;
    }

    public void setPlazoTotal(Integer plazoTotal) {
        this.plazoTotal = plazoTotal;
    }

    public Integer getTotalPlazoLimite() {
        return totalPlazoLimite;
    }

    public void setTotalPlazoLimite(Integer totalPlazoLimite) {
        this.totalPlazoLimite = totalPlazoLimite;
    }

    public Integer getTiempoPasivo() {
        return tiempoPasivo;
    }

    public void setTiempoPasivo(Integer tiempoPasivo) {
        this.tiempoPasivo = tiempoPasivo;
    }

    public Integer getPlazoEstablecido() {
        return plazoEstablecido;
    }

    public void setPlazoEstablecido(Integer plazoEstablecido) {
        this.plazoEstablecido = plazoEstablecido;
    }

    public RegionalService getRegionalService() {
        return regionalService;
    }

    public void setRegionalService(RegionalService regionalService) {
        this.regionalService = regionalService;
    }

    public String getAreaRegistro() {
        return areaRegistro;
    }

    public void setAreaRegistro(String areaRegistro) {
        this.areaRegistro = areaRegistro;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFechaServidor() {
        return fechaServidor;
    }

    public void setFechaServidor(Date fechaServidor) {
        this.fechaServidor = fechaServidor;
    }

    public Date getFechaExamenForma() {
        return fechaExamenForma;
    }

    public void setFechaExamenForma(Date fechaExamenForma) {
        this.fechaExamenForma = fechaExamenForma;
    }

    public SigSignoMarca getEncontradoSigno() {
        return encontradoSigno;
    }

    public void setEncontradoSigno(SigSignoMarca encontradoSigno) {
        this.encontradoSigno = encontradoSigno;
    }

    public ModModificacion getEncontradoModificacion() {
        return encontradoModificacion;
    }

    public void setEncontradoModificacion(ModModificacion encontradoModificacion) {
        this.encontradoModificacion = encontradoModificacion;
    }

    public RenSolicitudRenovacion getEncontradoRenovacion() {
        return encontradoRenovacion;
    }

    public void setEncontradoRenovacion(RenSolicitudRenovacion encontradoRenovacion) {
        this.encontradoRenovacion = encontradoRenovacion;
    }

    public String getTipoTramite() {
        return tipoTramite;
    }

    public void setTipoTramite(String tipoTramite) {
        this.tipoTramite = tipoTramite;
    }

    public Long getIdregional() {
        return idregional;
    }

    public void setIdregional(Long idregional) {
        this.idregional = idregional;
    }

    public DualListModel<String> getCities() {
        return cities;
    }

    public void setCities(DualListModel<String> cities) {
        this.cities = cities;
    }

    public DualListModel<Theme> getThemes() {
        return themes;
    }

    public void setThemes(DualListModel<Theme> themes) {
        this.themes = themes;
    }

    public Boolean getBuscadorModRender() {
        return buscadorModRender;
    }

    public void setBuscadorModRender(Boolean buscadorModRender) {
        this.buscadorModRender = buscadorModRender;
    }

    public Boolean getBuscadorSolicitanteApoderado() {
        return buscadorSolicitanteApoderado;
    }

    public void setBuscadorSolicitanteApoderado(Boolean buscadorSolicitanteApoderado) {
        this.buscadorSolicitanteApoderado = buscadorSolicitanteApoderado;
    }

    public Boolean getBuscadorRegistro() {
        return buscadorRegistro;
    }

    public void setBuscadorRegistro(Boolean buscadorRegistro) {
        this.buscadorRegistro = buscadorRegistro;
    }

    public Boolean getBuscadorPublicacion() {
        return buscadorPublicacion;
    }

    public void setBuscadorPublicacion(Boolean buscadorPublicacion) {
        this.buscadorPublicacion = buscadorPublicacion;
    }

    public String getValorTipoSolApod() {
        return valorTipoSolApod;
    }

    public void setValorTipoSolApod(String valorTipoSolApod) {
        this.valorTipoSolApod = valorTipoSolApod;
    }

    public String getSoliciApod() {
        return soliciApod;
    }

    public void setSoliciApod(String soliciApod) {
        this.soliciApod = soliciApod;
    }

    public Long getRegistro() {
        return registro;
    }

    public void setRegistro(Long registro) {
        this.registro = registro;
    }

    public String getValorSerieRegistro() {
        return valorSerieRegistro;
    }

    public void setValorSerieRegistro(String valorSerieRegistro) {
        this.valorSerieRegistro = valorSerieRegistro;
    }

    public Long getPublicacion() {
        return publicacion;
    }

    public void setPublicacion(Long publicacion) {
        this.publicacion = publicacion;
    }

    public Boolean getBuscadorRenRender() {
        return buscadorRenRender;
    }

    public void setBuscadorRenRender(Boolean buscadorRenRender) {
        this.buscadorRenRender = buscadorRenRender;
    }

    public String getComboTipoTitular() {
        return comboTipoTitular;
    }

    public void setComboTipoTitular(String comboTipoTitular) {
        this.comboTipoTitular = comboTipoTitular;
    }

    public List<Dominio> getListTipoTitular() throws Exception {
        return listTipoTitular = dominioService.obtenerListadoDominio("tipo_titular");
    }

    public void setListTipoTitular(List<Dominio> listTipoTitular) {
        this.listTipoTitular = listTipoTitular;
    }

    public DominioService getDominioService() {
        return dominioService;
    }

    public void setDominioService(DominioService dominioService) {
        this.dominioService = dominioService;
    }

    public String getComboTipoDocumento() {
        return comboTipoDocumento;
    }

    public void setComboTipoDocumento(String comboTipoDocumento) {
        this.comboTipoDocumento = comboTipoDocumento;
    }

    public List<Dominio> getListTipoDocumento() {
        return listTipoDocumento;
    }

    public void setListTipoDocumento(List<Dominio> listTipoDocumento) {
        this.listTipoDocumento = listTipoDocumento;
    }

    public String getComboDepartamento() {
        return comboDepartamento;
    }

    public void setComboDepartamento(String comboDepartamento) {
        this.comboDepartamento = comboDepartamento;
    }

    public List<Dominio> getListDepartamento() {
        return listDepartamento;
    }

    public void setListDepartamento(List<Dominio> listDepartamento) {
        this.listDepartamento = listDepartamento;
    }

    public Map<String, Object> getParametros() {
        return parametros;
    }

    public void setParametros(Map<String, Object> parametros) {
        this.parametros = parametros;
    }

    public EntregaTramiteService getEntregaTramiteService() {
        return entregaTramiteService;
    }

    public void setEntregaTramiteService(EntregaTramiteService entregaTramiteService) {
        this.entregaTramiteService = entregaTramiteService;
    }

    public ProcuradorService getProcuradorService() {
        return procuradorService;
    }

    public void setProcuradorService(ProcuradorService procuradorService) {
        this.procuradorService = procuradorService;
    }

    public String getProcuNombre() {
        return procuNombre;
    }

    public void setProcuNombre(String procuNombre) {
        this.procuNombre = procuNombre;
    }

    public String getProcuPrimerApe() {
        return procuPrimerApe;
    }

    public void setProcuPrimerApe(String procuPrimerApe) {
        this.procuPrimerApe = procuPrimerApe;
    }

    public String getProcuSegundoApe() {
        return procuSegundoApe;
    }

    public void setProcuSegundoApe(String procuSegundoApe) {
        this.procuSegundoApe = procuSegundoApe;
    }

    public String getProcuTipoTitular() {
        return procuTipoTitular;
    }

    public void setProcuTipoTitular(String procuTipoTitular) {
        this.procuTipoTitular = procuTipoTitular;
    }

    public String getProcuNumeroDocumento() {
        return procuNumeroDocumento;
    }

    public void setProcuNumeroDocumento(String procuNumeroDocumento) {
        this.procuNumeroDocumento = procuNumeroDocumento;
    }

    public String getProcuTipoDocumento() {
        return procuTipoDocumento;
    }

    public void setProcuTipoDocumento(String procuTipoDocumento) {
        this.procuTipoDocumento = procuTipoDocumento;
    }

    public String getProcuLugarExpedicion() {
        return procuLugarExpedicion;
    }

    public void setProcuLugarExpedicion(String procuLugarExpedicion) {
        this.procuLugarExpedicion = procuLugarExpedicion;
    }

    public String getProcuTelefono() {
        return procuTelefono;
    }

    public void setProcuTelefono(String procuTelefono) {
        this.procuTelefono = procuTelefono;
    }

    public String getProcuCelular() {
        return procuCelular;
    }

    public void setProcuCelular(String procuCelular) {
        this.procuCelular = procuCelular;
    }

    public String getProcuEstado() {
        return procuEstado;
    }

    public void setProcuEstado(String procuEstado) {
        this.procuEstado = procuEstado;
    }

    public UsuarioService getUsuarioService() {
        return usuarioService;
    }

    public void setUsuarioService(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    public LogTrans getLogTransGuardado() {
        return logTransGuardado;
    }

    public void setLogTransGuardado(LogTrans logTransGuardado) {
        this.logTransGuardado = logTransGuardado;
    }

    public LogTransService getLogTransService() {
        return logTransService;
    }

    public void setLogTransService(LogTransService logTransService) {
        this.logTransService = logTransService;
    }

    public boolean isMuestraImprime() {
        return muestraImprime;
    }

    public void setMuestraImprime(boolean muestraImprime) {
        this.muestraImprime = muestraImprime;
    }

    public String getBloqueMuestra() {
        return bloqueMuestra;
    }

    public void setBloqueMuestra(String bloqueMuestra) {
        this.bloqueMuestra = bloqueMuestra;
    }

    public String getLetraRegionalMuestra() {
        return letraRegionalMuestra;
    }

    public void setLetraRegionalMuestra(String letraRegionalMuestra) {
        this.letraRegionalMuestra = letraRegionalMuestra;
    }

    public String getGestionMuestra() {
        return GestionMuestra;
    }

    public void setGestionMuestra(String GestionMuestra) {
        this.GestionMuestra = GestionMuestra;
    }

    public String getBloqueResult() {
        return bloqueResult;
    }

    public void setBloqueResult(String bloqueResult) {
        this.bloqueResult = bloqueResult;
    }

    public boolean isMuestraBloque() {
        return muestraBloque;
    }

    public void setMuestraBloque(boolean muestraBloque) {
        this.muestraBloque = muestraBloque;
    }

    public Integer getTotalBuzon() {
        return totalBuzon;
    }

    public void setTotalBuzon(Integer totalBuzon) {
        this.totalBuzon = totalBuzon;
    }

    //</editor-fold>    
    public boolean isMuestraBuscar() {
        return muestraBuscar;
    }

    public void setMuestraBuscar(boolean muestraBuscar) {
        this.muestraBuscar = muestraBuscar;
    }

    public Boolean getBuscadorOtros() {
        return buscadorOtros;
    }

    public void setBuscadorOtros(Boolean buscadorOtros) {
        this.buscadorOtros = buscadorOtros;
    }

    public void setLstTipoTramiteRecibo(List<Dominio> lstTipoTramiteRecibo) {
        this.lstTipoTramiteRecibo = lstTipoTramiteRecibo;
    }

    public List<Dominio> getLstTipoTramiteRecibo() throws Exception {
        try {
            return lstTipoTramiteRecibo = dominioService.obtenerListadoTipoTramite(EnumDominio.TIPO_TRAMITE_RECIBO.getCodigo());
        } catch (Exception e) {
            throw e;
        }
    }

    public String getTipoTramiteCombo() {
        return tipoTramiteCombo;
    }

    public void setTipoTramiteCombo(String tipoTramiteCombo) {
        this.tipoTramiteCombo = tipoTramiteCombo;
    }

    public String getNumeroExpediente() {
        return numeroExpediente;
    }

    public void setNumeroExpediente(String numeroExpediente) {
        this.numeroExpediente = numeroExpediente;
    }

    public Integer getGestionTramite() {
        return gestionTramite;
    }

    public void setGestionTramite(Integer gestionTramite) {
        this.gestionTramite = gestionTramite;
    }

    public String getProcuTipoDocAuto() {
        return procuTipoDocAuto;
    }

    public void setProcuTipoDocAuto(String procuTipoDocAuto) {
        this.procuTipoDocAuto = procuTipoDocAuto;
    }

    public String getProcuDescDocuAuto() {
        return procuDescDocuAuto;
    }

    public void setProcuDescDocuAuto(String procuDescDocuAuto) {
        this.procuDescDocuAuto = procuDescDocuAuto;
    }

    public List<Dominio> getListTipoDocuAutoriza() throws Exception {
        return listTipoDocuAutoriza = dominioService.obtenerListadoDominio("tipo_doc_entrega");
    }

    public void setListTipoDocuAutoriza(List<Dominio> listTipoDocuAutoriza) {
        this.listTipoDocuAutoriza = listTipoDocuAutoriza;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Integer getNumeroClaseNiza() {
        return numeroClaseNiza;
    }

    public void setNumeroClaseNiza(Integer numeroClaseNiza) {
        this.numeroClaseNiza = numeroClaseNiza;
    }

}
