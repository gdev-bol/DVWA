/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.entidades.recaudaciones;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Writer;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DateFormatSymbols;
import java.text.Format;
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
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.transaction.annotation.Transactional;
import snp.gob.bo.entidades.bean.common.AbstractManagedBean;
import snp.gob.bo.gimodel.entidad.Deposito;
import snp.gob.bo.gimodel.entidad.Dominio;
import snp.gob.bo.gimodel.entidad.ModModificacion;
import snp.gob.bo.gimodel.entidad.Oposicion;
import snp.gob.bo.gimodel.entidad.Recibo;
import snp.gob.bo.gimodel.entidad.ReciboDeposito;
import snp.gob.bo.gimodel.entidad.Regional;
import snp.gob.bo.gimodel.entidad.RenSolicitudRenovacion;
import snp.gob.bo.gimodel.entidad.SigSignoMarca;
import snp.gob.bo.gimodel.entidad.Tasa;
import snp.gob.bo.gimodel.entidad.Usuario;
import snp.gob.bo.gimodel.enums.EnumCarpetasTemporales;
import snp.gob.bo.gimodel.enums.EnumDominio;
import snp.gob.bo.gimodel.enums.EnumTipoTramite;
import snp.gob.bo.gimodel.enums.EnumTipoTramiteRecibo;
import snp.gob.bo.gimodel.servicio.ClaseNizaService;
import snp.gob.bo.gimodel.servicio.ComunService;
import snp.gob.bo.gimodel.servicio.DepositoService;
import snp.gob.bo.gimodel.servicio.DominioService;
import snp.gob.bo.gimodel.servicio.ModModificacionService;
import snp.gob.bo.gimodel.servicio.ModSolicitanteApoderadoService;
import snp.gob.bo.gimodel.servicio.OposicionService;
import snp.gob.bo.gimodel.servicio.ReciboDepositoService;
import snp.gob.bo.gimodel.servicio.ReciboService;
import snp.gob.bo.gimodel.servicio.RegionalService;
import snp.gob.bo.gimodel.servicio.RenSolicitanteApoderadoService;
import snp.gob.bo.gimodel.servicio.RenSolicitudRenovacionService;
import snp.gob.bo.gimodel.servicio.SigSignoClaseNizaService;
import snp.gob.bo.gimodel.servicio.SigSignoMarcaService;
import snp.gob.bo.gimodel.servicio.SigSolicitanteApoderadoService;
import snp.gob.bo.gimodel.servicio.TasaService;
import snp.gob.bo.gimodel.servicio.UsuarioService;

@ManagedBean(name = "reciboBean")
@ViewScoped
public class ReciboBean extends AbstractManagedBean implements Serializable {

    @ManagedProperty("#{sigSignoMarcaService}")
    private SigSignoMarcaService sigSignoMarcaService;
    @ManagedProperty("#{sigSolicitanteApoderadoService}")
    private SigSolicitanteApoderadoService sigSolicitanteApoderadoService;

    @ManagedProperty("#{comunService}")
    private ComunService comunService;

    @ManagedProperty("#{sigSignoClaseNizaService}")
    private SigSignoClaseNizaService sigSignoClaseNizaService;

    @ManagedProperty("#{renSolicitudRenovacionService}")
    private RenSolicitudRenovacionService renSolicitudRenovacionService;

    @ManagedProperty("#{modModificacionService}")
    private ModModificacionService modModificacionService;

    @ManagedProperty("#{tasaService}")
    private TasaService tasaService;

    @ManagedProperty("#{oposicionService}")
    private OposicionService oposicionService;

    @ManagedProperty("#{reciboService}")
    private ReciboService reciboService;

    @ManagedProperty("#{depositoService}")
    private DepositoService depositoService;

    @ManagedProperty("#{reciboDepositoService}")
    private ReciboDepositoService reciboDepositoService;

    @ManagedProperty("#{renSolicitanteApoderadoService}")
    private RenSolicitanteApoderadoService renSolicitanteApoderadoService;

    @ManagedProperty("#{modSolicitanteApoderadoService}")
    private ModSolicitanteApoderadoService modSolicitanteApoderadoService;

    @ManagedProperty(value = "#{usuarioService}")
    private UsuarioService usuarioService;

    @ManagedProperty(value = "#{regionalService}")
    private RegionalService regionalService;

    @ManagedProperty(value = "#{dominioService}")
    private DominioService dominioService;
    
    @ManagedProperty(value = "#{claseNizaService}")
    private ClaseNizaService claseNizaService;
    
    
    
    
    private Boolean renderizaBotonesModificatoria;
    private String numeroSerieRecibos;
    private String numeroSerieTitulos;
    private Long tasa;
    private Boolean activaBotonGeneraCostoTotal;
    private String regionalConbo;
    private String tipoTramiteDescripcion;
    private Boolean cambiaColorNumeroDeposito;
    private Boolean cambiaColorPersonaDeposito;
    private Boolean cambiaColorFechaDeposito;
    private Boolean cambiaColorBancoDeposito;
    private Boolean cambiaColormontoDeposito;

    private String tipoBanco;

    private Boolean activaEliminaModificaDeposito;
    private Boolean activaBotonDeposito;
    private Boolean campoTitulo;
    private Boolean cargarSerieTiulo = false;
    private Boolean ocultaCargaDeTitulos = false;
    private Boolean ocultaCargaDeRecibo = false;
    private Boolean muestraFormularioDeposito = true;
    private Boolean cargarNumeroRecibo = false;
    private Boolean ocultaCargaDeRecibos = false;
    private Boolean variableRenderizaTramitesRegistrados = false;
    private Boolean cargaTramitesNoRegistrados = false;
    private Boolean activaPanelesSiExisteTramite;
    private Boolean activaPanelesSiExisteTramiteNoEncontrado;
    private String serieRecuperada;
    private int numeroTitulosAnulados;
    private int cantidad;
    private int numeroRecibo;
    private int numeroRecibosModificados;
    private Date fechaImpresion;
    private Date fecha;
    private BigDecimal totalRecibo;
    private int numeorSiguienteRecibo;
    private int numeorSiguienteTitulo;
    private Boolean reporteRecibo;
    private String mes;
    private String anio;
    private String regionalDato1;
    private String regionalDato2;
    private String tipoSucursalCombo;
    private String listaTipoCiudadNotificacion;
    private List<String> listaDeRecibosAnulados;
    private List<String> listaDetalleTitulo;
    private List<String> listaTramite;
    private List<String> listaDeposito;
    private List<String> listaTasa;
    private List<String> listaRecibo;
    private String numeroDeRecibo;

    private int cont = 0;
    private StreamedContent streamedContent1;
    private InputStream stream1;
    private String valor;
    private String gestionTramite;
    private Date fechaRecibo;
    private Long claseNiza;
    private String claseNizaInt;

    //variables
    private Recibo recibo;
    private Recibo reciboElegido;
    private ReciboDeposito reciboDeposito;
    private List<ReciboDeposito> lstReciboDeposito;
    private List<Recibo> listaReciboGenerado;
    private String codigoTramite;
    private String numeroDeFormulario;
    private String tipoTramite;
    private String marcaTramite;
    private List<Tasa> lstTasa;
    private String tipoTasa;
    private Long codigoTasa;
    private BigDecimal montoTotal;
    private Deposito deposito;
    private Deposito depositoSeleccionado;
    private List<Deposito> lstDesposito;
    private String numerosDepositos;
    private String nombresDeposito;
    private Date fechasDeposito;
    private String montosDeposito;
    private BigDecimal sumaMontos;
    private BigDecimal saldoTotal;
    private String valorOpcionesRadioBanco = "BUNI";
    private String codDepositante;
    private Deposito depositoGuardar;
    private Usuario usuario;
    private Regional regional;
    private Tasa tasaRecibo;
    private String serieRecibo;
    private String serieTitulo;
    private int numeroTitulo;
    private String apoderadoTramite;
    private String solicitanteTramite;
    private String descripcionTramite;
    private String numeroExpediente;
    private String codigoExtension;
    private Recibo reciboEliminar;
    private Recibo reciboAnular;
    private Recibo reciboReasignado;
    private Recibo tituloReasignado;
    private Boolean activaRecibo = false;
    private Boolean activaCamposRecibo;
    private Boolean activaTitulo = false;
    private Boolean activaCamposTitulo;

    // parametros del reporte
    private Long idUsuarioSesion;
    private String imgSenapi = "";
    private Boolean report = false;
    private Map<String, Object> parametros;
    private String montoNumeralR;
    private String nombreDepositanteR;
    private String montoLiteralR;
    private String conceptoR;
    private String tipoTramiteR;
    private String diaR;
    private String mesR;
    private String anioR;
    private String imageFirmaR;
    private String nombreFirmaR;
    private String lugaryFecha;
    private String serieReciboR;
    private String nroReciboR;
    private List<Deposito> lstDepositoR;
    private List<Usuario> listUsuario;
    private List<Dominio> lstTipoTramiteRecibo;
    private List<Dominio> lstSucursalBancaria;
    private Boolean panelCatalogoRendered = false;
    private Boolean panelVerRendered = false;
    private String valorRadio = "USU";
    private String tipoTramiteCombo = " ";
    private Date fechaBuscar;

    public ReciboBean() {
        this.listaReciboGenerado = new ArrayList<Recibo>();
        this.lstTipoTramiteRecibo = new ArrayList<Dominio>();
        this.lstDesposito = new ArrayList<Deposito>();
        this.lstReciboDeposito = new ArrayList<ReciboDeposito>();

        this.parametros = new HashMap<String, Object>();
        this.lstDepositoR = new ArrayList<Deposito>();
        listUsuario = new ArrayList<Usuario>();
    }

    @PostConstruct
    public void initReciboBean() {
        try {
            if (getPaginaAnteriorDigitalizacion() != null) {
                renderizaBotonesModificatoria = verificaVariablePagina(getPaginaAnteriorDigitalizacion());
            }
            if (getIdUsuarioSession() != null) {
                usuario = usuarioService.buscaUsuarioPorIdUsuario(getIdUsuarioSession());
                regional = regionalService.obtenerRegionalPorIdRegiona(usuario.getIdregional());
            }
//        inicializaValoresPrueba();
            cantidad = 1;
            activaBotonGeneraCostoTotal = true;
            reporteRecibo = false;
            fechaImpresion = comunService.obtenerFechaHoraServidor(1L);
            Format formatterMes = new SimpleDateFormat("MM");
            Format formatterAnio = new SimpleDateFormat("yyyy");
            try {
            } catch (Exception ex) {
                Logger.getLogger(ReciboBean.class.getName()).log(Level.SEVERE, null, ex);
            }
            deposito = new Deposito();
            depositoGuardar = new Deposito();
            depositoGuardar.setFechaDeposito(comunService.obtenerFechaHoraServidor(1L));
            regional = new Regional();
            recibo = new Recibo();
            numeroTitulo = 0;
            numeroRecibo = 0;
            serieRecibo = null;
            serieTitulo = null;
            solicitanteTramite = "";
            apoderadoTramite = "";
            lstTasa = tasaService.obtenerListaTasaActivas();
            idUsuarioSesion = super.getIdUsuarioSession();
            if (!depositoService.listaDepositoConSaldoPorUSuarioSaldo(usuario).isEmpty()) {
                lstDesposito = depositoService.listaDepositoConSaldoPorUSuarioSaldo(usuario);
            } else {
                lstDesposito = new ArrayList<>();
            }
            listaReciboGenerado = reciboService.listaReciboEmitidoPorFechas(comunService.obtenerFechaHoraServidor(1L), usuario);
        } catch (Exception e) {
        }

    }

    public Boolean verificaVariablePagina(String pagina) {
        Boolean seRenderiza = false;
        if (pagina.indexOf("=") != (-1)) {
            String[] cadena = pagina.split("=");
            if (!cadena[1].isEmpty() && !cadena[1].equals("")) {
                if (cadena[1].equals("MODRENO")) {
                    seRenderiza = true;
                }
            }
        }
        return seRenderiza;
    }

    public void calculaMonto(int cantidad) throws Exception {
        montoTotal = new BigDecimal(cantidad).multiply(tasaService.obtenerTasaPorIdTasa(tasa).getCosto());
    }

    public void limpiaFormulario() throws Exception {
        numeroDeFormulario = null;
        claseNizaInt = "";
        inicializaValoresVista();
        cantidad = 1;
        calculaMonto(cantidad);
    }

    public void accion_buscarSolicitudFormulario() throws Exception {
        try {

            inicializaValoresVista();
            int sw = 0;
            if (!numeroDeFormulario.equals(null) && !numeroDeFormulario.equals("")) {
                // sigsignomarca
                if (sigSignoMarcaService.buscarSignoMarca_NumeroFormulario(numeroDeFormulario).getIdSignoMarca() != null) {
                    codigoTramite = "SM";
                    sw = 1;

                    SigSignoMarca signoMarca = sigSignoMarcaService.buscarSignoMarca_NumeroFormulario(numeroDeFormulario);

//                    tipoTramiteCombo = encuentraTipoTramiteSIgnos(sigSignoMarcaService.buscarSignoMarca_NumeroFormulario(numeroDeFormulario).getOficina());
                    tipoTramiteCombo = encuentraTipoTramiteSIgnos(signoMarca.getOficina());
//                    numeroExpediente = obtieneNumeroSM(sigSignoMarcaService.buscarSignoMarca_NumeroFormulario(numeroDeFormulario).getSm());
                    numeroExpediente = obtieneNumeroSM(signoMarca.getSm());
//                    codigoExtension = obtieneExtensionSM(sigSignoMarcaService.buscarSignoMarca_NumeroFormulario(numeroDeFormulario).getSm());
                    codigoExtension = obtieneExtensionSM(signoMarca.getSm());
//                   
                    ///modificaciones-niza
//                    claseNizaInt = sigSignoClaseNizaService.obtenerListaSigSignoClazeNizaXIdSignoMarca(signoMarca.getIdSignoMarca()).get(0).getNumeroClaseNiza().toString();
                    claseNizaInt = sigSignoClaseNizaService.obtenerListaSigSignoClazeNizaXIdSignoMarca(signoMarca.getIdSignoMarca()).get(0).getNumeroClaseNiza().toString();

//                    claseNiza = sigSignoClaseNizaService.obtenerListaSigSignoClazeNizaXIdSignoMarca(signoMarca.getIdSignoMarca()).get(0).getNumeroClaseNiza();
//                  
                    gestionTramite = obtieneGestion(signoMarca.getSm());
                    gestionTramite = obtieneGestion(signoMarca.getSm());
                    marcaTramite = signoMarca.getSigno();
                    tipoTramite = EnumTipoTramite.REGISTRO_MARCAS.getCodigo();
//                    List<SigSolicitanteApoderado> lstSolicitante = sigSolicitanteApoderadoService.listadoSolicitanteXidsignomarca(signoMarca.getIdSignoMarca());
//                    if (!lstSolicitante.isEmpty()) {
//                        for (SigSolicitanteApoderado lst1 : lstSolicitante) {
//                            if (solicitanteTramite.equals(null) || solicitanteTramite.equals("")) {
//                                solicitanteTramite = lst1.getNombreRazonSocial();
//                                if (lst1.getPrimerApellido() != null) {
//                                    solicitanteTramite = solicitanteTramite + " " + lst1.getPrimerApellido();
//                                }
//                                if (lst1.getSegundoApellido() != null) {
//                                    solicitanteTramite = solicitanteTramite + " " + lst1.getSegundoApellido();
//                                }
//                            } else {
//                                solicitanteTramite = solicitanteTramite + "/" + " " + lst1.getNombreRazonSocial();
//                                if (lst1.getPrimerApellido() != null) {
//                                    solicitanteTramite = solicitanteTramite + " " + lst1.getPrimerApellido();
//                                }
//                                if (lst1.getSegundoApellido() != null) {
//                                    solicitanteTramite = solicitanteTramite + " " + lst1.getSegundoApellido();
//                                }
//                            }
//                        }
//                    }
//                    List<SigSolicitanteApoderado> listaApoderado = sigSolicitanteApoderadoService.listadoApoderadoXidsignomarca(signoMarca.getIdSignoMarca());
//                    if (!listaApoderado.isEmpty()) {
//                        for (SigSolicitanteApoderado lst1 : listaApoderado) {
//                            if (apoderadoTramite.equals("")) {
//                                apoderadoTramite = lst1.getNombreRazonSocial();
//                                if (lst1.getPrimerApellido() != null) {
//                                    apoderadoTramite = apoderadoTramite + " " + lst1.getPrimerApellido();
//                                }
//                                if (lst1.getSegundoApellido() != null) {
//                                    apoderadoTramite = apoderadoTramite + " " + lst1.getSegundoApellido();
//                                }
//                            } else {
//                                apoderadoTramite = apoderadoTramite + "/" + " " + lst1.getNombreRazonSocial();
//                                if (lst1.getPrimerApellido() != null) {
//                                    apoderadoTramite = apoderadoTramite + " " + lst1.getPrimerApellido();
//                                }
//                                if (lst1.getSegundoApellido() != null) {
//                                    apoderadoTramite = apoderadoTramite + " " + lst1.getSegundoApellido();
//                                }
//                            }
//                        }
//                    }
                } else {

                }
                //renrenovacion
                if (renSolicitudRenovacionService.buscarRenSolicitud_NumeroFormulario(numeroDeFormulario).getIdsolicitudrenovacion() != null) {
                    sw = 1;
                    RenSolicitudRenovacion renSoli = renSolicitudRenovacionService.buscarRenSolicitud_NumeroFormulario(numeroDeFormulario);
                    tipoTramiteCombo = encuentraTipoTramiteRenovaciones(renSoli.getOficina());
                    codigoTramite = "SR" + "-" + renSoli.getSr().toString() + "-" + renSoli.getGestion_sr();
                    numeroExpediente = renSoli.getSr().toString();
                    //modificacionniza
                    claseNizaInt = claseNizaService.listarClaseNiza_id(renSoli.getIdclase_niza_registrado()).getNumeroClaseNiza().toString();
//                    claseNiza = new Long(renSoli.getClase_niza_registrado());
                    gestionTramite = String.valueOf(renSoli.getGestion_sr());
                    marcaTramite = renSoli.getSigno_registrado();
                    tipoTramite = EnumTipoTramite.RENOVACION.getCodigo();
                    //solicitante apoderado
//                    List<RenSolicitanteApoderado> lstSolicitanteRenovacion = renSolicitanteApoderadoService.buscaSolicitanteApoderadoPoridSolicitudyTipoPersona(renSoli.getIdsolicitudrenovacion(), EnumTipoPersona.SOLICITANTE.getCodigo());
//                    for (RenSolicitanteApoderado lst1 : lstSolicitanteRenovacion) {
//                        if (solicitanteTramite.equals("")) {
//                            solicitanteTramite = lst1.getNombre_razonsocial();
//                            if (lst1.getPrimer_apellido() != null) {
//                                solicitanteTramite = solicitanteTramite + " " + lst1.getPrimer_apellido();
//                            }
//                            if (lst1.getSegundo_apellido() != null) {
//                                solicitanteTramite = solicitanteTramite + " " + lst1.getSegundo_apellido();
//                            }
//                        } else {
//                            solicitanteTramite = solicitanteTramite + "" + "/" + "" + lst1.getNombre_razonsocial();
//                            if (lst1.getPrimer_apellido() != null) {
//                                solicitanteTramite = solicitanteTramite + " " + lst1.getPrimer_apellido();
//                            }
//                            if (lst1.getSegundo_apellido() != null) {
//                                solicitanteTramite = solicitanteTramite + " " + lst1.getSegundo_apellido();
//                            }
//                        }
//                    }
//                    List<RenSolicitanteApoderado> listaApoderado = renSolicitanteApoderadoService.buscaSolicitanteApoderadoPoridSolicitudyTipoPersona(renSoli.getIdsolicitudrenovacion(), EnumTipoPersona.APODERADO.getCodigo());
//                    for (RenSolicitanteApoderado lst1 : listaApoderado) {
//                        if (apoderadoTramite.equals("")) {
//                            apoderadoTramite = lst1.getNombre_razonsocial();
//                            if (lst1.getPrimer_apellido() != null) {
//                                apoderadoTramite = apoderadoTramite + " " + lst1.getPrimer_apellido();
//                            }
//                            if (lst1.getSegundo_apellido() != null) {
//                                apoderadoTramite = apoderadoTramite + " " + lst1.getSegundo_apellido();
//                            }
//                        } else {
//                            apoderadoTramite = apoderadoTramite + "" + "/" + "" + lst1.getNombre_razonsocial();
//                            if (lst1.getPrimer_apellido() != null) {
//                                apoderadoTramite = apoderadoTramite + " " + lst1.getPrimer_apellido();
//                            }
//                            if (lst1.getSegundo_apellido() != null) {
//                                apoderadoTramite = apoderadoTramite + " " + lst1.getSegundo_apellido();
//                            }
//                        }
//                    }

                } else {

                }
                //modmodifacion
                if (modModificacionService.buscarModModificacion_NumeroFormulario(numeroDeFormulario).getIdmodificacion() != null) {

                    ModModificacion modi = modModificacionService.buscarModModificacion_NumeroFormulario(numeroDeFormulario);
                    sw = 1;
                    codigoTramite = modi.getSigla() + "-" + modi.getNumero().toString() + "-" + modi.getGestion();
                    numeroExpediente = modi.getNumero().toString();
                    //modificaiconniza

                    claseNizaInt = claseNizaService.listarClaseNiza_id(modi.getIdclase_registrado()).getNumeroClaseNiza().toString();

//                  claseNiza = new Long(modi.getClase_registrado());
                    gestionTramite = modi.getGestion().toString();
                    marcaTramite = modi.getSigno_registrado();
                    tipoTramiteCombo = tipoTramite = modi.getSigla();
//                    List<ModSolicitanteApoderado> lstSolicitanteMod = modSolicitanteApoderadoService.listadoSolicitanteXidmodificacion(modi.getIdmodificacion());
//                    for (ModSolicitanteApoderado lst1 : lstSolicitanteMod) {
//                        if (solicitanteTramite.equals("")) {
//                            solicitanteTramite = lst1.getNombre_razonsocial();
//                            if (lst1.getPrimer_apellido() != null) {
//                                solicitanteTramite = solicitanteTramite + " " + lst1.getPrimer_apellido();
//                            }
//                            if (lst1.getSegundo_apellido() != null) {
//                                solicitanteTramite = solicitanteTramite + " " + lst1.getSegundo_apellido();
//                            }
//                        } else {
//                            solicitanteTramite = solicitanteTramite + "" + "/" + "" + lst1.getNombre_razonsocial();
//                            if (lst1.getPrimer_apellido() != null) {
//                                solicitanteTramite = solicitanteTramite + " " + lst1.getPrimer_apellido();
//                            }
//                            if (lst1.getSegundo_apellido() != null) {
//                                solicitanteTramite = solicitanteTramite + " " + lst1.getSegundo_apellido();
//                            }
//                        }
//                    }
//                    List<ModSolicitanteApoderado> listaApoderado = modSolicitanteApoderadoService.listadoApoderadoXidmodificacion(modi.getIdmodificacion());
//                    for (ModSolicitanteApoderado lst1 : listaApoderado) {
//                        if (apoderadoTramite.equals("")) {
//                            apoderadoTramite = lst1.getNombre_razonsocial();
//                            if (lst1.getPrimer_apellido() != null) {
//                                apoderadoTramite = apoderadoTramite + " " + lst1.getPrimer_apellido();
//                            }
//                            if (lst1.getSegundo_apellido() != null) {
//                                apoderadoTramite = apoderadoTramite + " " + lst1.getSegundo_apellido();
//                            }
//                        } else {
//                            apoderadoTramite = apoderadoTramite + "" + "/" + "" + lst1.getNombre_razonsocial();
//                            if (lst1.getPrimer_apellido() != null) {
//                                apoderadoTramite = apoderadoTramite + " " + lst1.getPrimer_apellido();
//                            }
//                            if (lst1.getSegundo_apellido() != null) {
//                                apoderadoTramite = apoderadoTramite + " " + lst1.getSegundo_apellido();
//                            }
//                        }
//                    }
                }
//                parametros de oposiciones
//                   if (sigSignoMarcaService.buscarSignoMarca_NumeroFormulario(numeroDeFormulario).getIdSignoMarca() != null) {
//                   
//                   
//                   
//                   
//                   }

                //metodo que busca oposicion y muestra 
                if (oposicionService.obtoposicionxnroformularioRecaudaciones(Long.parseLong(numeroDeFormulario)).getIdoposicion() != null) {
//                    ModModificacion modi = modModificacionService.buscarModModificacion_NumeroFormulario(numeroDeFormulario);
                    Oposicion oposicion = oposicionService.obtoposicionxnroformulario(Long.parseLong(numeroDeFormulario));
                    sw = 1;
                    codigoTramite = "OP" + "-" + oposicion.getCodigo_opo().toString() + "-" + oposicion.getGestion_opo();
                    numeroExpediente = oposicion.getCodigo_opo().toString();
                    //modificaiconniza
                    tipoTramiteCombo = encuentraTipoTramiteOposicion(oposicion.getOficina());
//                    claseNizaInt = modi.getClase_registrado().toString();
//                  claseNiza = new Long(modi.getClase_registrado());
                    gestionTramite = oposicion.getGestion_opo().toString();
                    marcaTramite = oposicionService.obtenernombremarcaxidopo2(oposicion.getIdoposicion());

                    System.out.println("marcatramite" + marcaTramite);

                    tipoTramite = EnumTipoTramite.OPOSICION.getCodigo();
//                    List<ModSolicitanteApoderado> lstSolicitanteMod = modSolicitanteApoderadoService.listadoSolicitanteXidmodificacion(modi.getIdmodificacion());
//                    for (ModSolicitanteApoderado lst1 : lstSolicitanteMod) {
//                        if (solicitanteTramite.equals("")) {
//                            solicitanteTramite = lst1.getNombre_razonsocial();
//                            if (lst1.getPrimer_apellido() != null) {
//                                solicitanteTramite = solicitanteTramite + " " + lst1.getPrimer_apellido();
//                            }
//                            if (lst1.getSegundo_apellido() != null) {
//                                solicitanteTramite = solicitanteTramite + " " + lst1.getSegundo_apellido();
//                            }
//                        } else {
//                            solicitanteTramite = solicitanteTramite + "" + "/" + "" + lst1.getNombre_razonsocial();
//                            if (lst1.getPrimer_apellido() != null) {
//                                solicitanteTramite = solicitanteTramite + " " + lst1.getPrimer_apellido();
//                            }
//                            if (lst1.getSegundo_apellido() != null) {
//                                solicitanteTramite = solicitanteTramite + " " + lst1.getSegundo_apellido();
//                            }
//                        }
//                    }
//                    List<ModSolicitanteApoderado> listaApoderado = modSolicitanteApoderadoService.listadoApoderadoXidmodificacion(modi.getIdmodificacion());
//                    for (ModSolicitanteApoderado lst1 : listaApoderado) {
//                        if (apoderadoTramite.equals("")) {
//                            apoderadoTramite = lst1.getNombre_razonsocial();
//                            if (lst1.getPrimer_apellido() != null) {
//                                apoderadoTramite = apoderadoTramite + " " + lst1.getPrimer_apellido();
//                            }
//                            if (lst1.getSegundo_apellido() != null) {
//                                apoderadoTramite = apoderadoTramite + " " + lst1.getSegundo_apellido();
//                            }
//                        } else {
//                            apoderadoTramite = apoderadoTramite + "" + "/" + "" + lst1.getNombre_razonsocial();
//                            if (lst1.getPrimer_apellido() != null) {
//                                apoderadoTramite = apoderadoTramite + " " + lst1.getPrimer_apellido();
//                            }
//                            if (lst1.getSegundo_apellido() != null) {
//                                apoderadoTramite = apoderadoTramite + " " + lst1.getSegundo_apellido();
//                            }
//                        }
//                    }
                }

                if (sw == 0) {
                    inicializaValoresVista();
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "El nú"
                            + "mero de formulario no esta asociado a una solicitud  web", ""));
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Busqueda exitosa", ""));
                }

                System.out.println("tipotramite" + tipoTramite);
                if (tipoTramite != null && !tipoTramite.isEmpty()) {
                    String[] arraycodigo = tipoTramite.split("-");
                    lstTasa = tasaService.obtenerListaTasaActivasPorTipoTramite(arraycodigo[0]);
                } else {
                    lstTasa = tasaService.obtenerListaTasaActivas();
                }
                tasa = lstTasa.get(0).getIdTasa();
                calculaMonto(cantidad);
                numeroTitulo = 0;
                numeroRecibo = 0;
                serieRecibo = null;
                serieTitulo = null;
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Escriba el nú"
                        + "mero de formualrio que desea buscar", ""));
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public String encuentraTipoTramiteRenovaciones(String oficina) {
        String tipoTramiteDevolver = "null";
        switch (oficina) {
            case "CBA":
                tipoTramiteDevolver = EnumTipoTramiteRecibo.RENOVACION_COCHABAMBA.getCodigo();
                break;
            case "LPZ":
                tipoTramiteDevolver = EnumTipoTramiteRecibo.RENOVACION.getCodigo();
                break;
            case "SCZ":
                tipoTramiteDevolver = EnumTipoTramiteRecibo.RENOVACION_SANTA_CRUZ.getCodigo();
                break;
            case "ALT":
                tipoTramiteDevolver = EnumTipoTramiteRecibo.RENOVACION_EL_ALTO.getCodigo();
                break;
            case "ORU":
                tipoTramiteDevolver = EnumTipoTramiteRecibo.RENOVACION_ORURO.getCodigo();
                break;
            case "TJA":
                tipoTramiteDevolver = EnumTipoTramiteRecibo.RENOVACION_TARIJA.getCodigo();
                break;
            case "CHQ":
                tipoTramiteDevolver = EnumTipoTramiteRecibo.RENOVACION_SUCRE.getCodigo();
                break;

        }

        return tipoTramiteDevolver;
    }

    public String encuentraTipoTramiteOposicion(String oficina) {
        String tipoTramiteDevolver = "null";
        switch (oficina) {
            case "CBA":
                tipoTramiteDevolver = EnumTipoTramiteRecibo.OPOSICION_COCHABAMABA.getCodigo();
                break;
            case "LPZ":
                tipoTramiteDevolver = EnumTipoTramiteRecibo.OPOSICION.getCodigo();
                break;
            case "SCZ":
                tipoTramiteDevolver = EnumTipoTramiteRecibo.OPOSICION_SANTA_CRUZ.getCodigo();
                break;
            case "ALT":
                tipoTramiteDevolver = EnumTipoTramiteRecibo.OPOSICION_EL_ALTO.getCodigo();
                break;
            case "ORU":
                tipoTramiteDevolver = EnumTipoTramiteRecibo.OPOSICION_ORURO.getCodigo();
                break;
            case "TJA":
                tipoTramiteDevolver = EnumTipoTramiteRecibo.OPOSICION_TARIJA.getCodigo();
                break;
            case "CHQ":
                tipoTramiteDevolver = EnumTipoTramiteRecibo.OPOSICION_CHIQUISACA.getCodigo();
                break;

        }

        return tipoTramiteDevolver;
    }

    public String encuentraTipoTramiteSIgnos(String oficina) {
        String tipoTramiteDevolver = "null";
        switch (oficina) {
            case "CBA":
                tipoTramiteDevolver = EnumTipoTramiteRecibo.REGISTRO_MARCAS_COCHABAMBA.getCodigo();
                break;
            case "LPZ":
                tipoTramiteDevolver = EnumTipoTramiteRecibo.REGISTRO_MARCAS.getCodigo();
                break;
            case "SCZ":
                tipoTramiteDevolver = EnumTipoTramiteRecibo.REGISTRO_MARCAS_SANTA_CRUZ.getCodigo();
                break;
            case "ALT":
                tipoTramiteDevolver = EnumTipoTramiteRecibo.REGISTRO_MARCAS_EL_ALTO.getCodigo();
                break;
            case "ORU":
                tipoTramiteDevolver = EnumTipoTramiteRecibo.REGISTRO_MARCAS_ORURO.getCodigo();
                break;
            case "TJA":
                tipoTramiteDevolver = EnumTipoTramiteRecibo.REGISTRO_MARCAS_TARIJA.getCodigo();
                break;
            case "CHQ":
                tipoTramiteDevolver = EnumTipoTramiteRecibo.REGISTRO_MARCAS_SUCRE.getCodigo();
                break;

        }

        return tipoTramiteDevolver;
    }

    public void inicializaValoresVista() throws Exception {
        codigoTramite = numeroExpediente = gestionTramite = tipoTramite = marcaTramite = solicitanteTramite = apoderadoTramite = "";
        //modificacionniza
        //        claseNiza = new Long(0);
        calculaMonto(cantidad);
        lstTasa = tasaService.obtenerListaTasaActivas();

        if (!depositoService.listaDepositoConSaldoPorUSuarioSaldo(usuario).isEmpty()) {
            lstDesposito = depositoService.listaDepositoConSaldoPorUSuarioSaldo(usuario);
        } else {
            lstDesposito = new ArrayList<>();
        }
        tasa = lstTasa.get(0).getIdTasa();
    }

    public String obtieneCodigoSm(Long numeroSm) throws Exception {
        HashMap resultado = comunService.obtenerNumeroGestionExtensionCodigoSM(numeroSm);
        if (!resultado.get("extension").equals("") && !resultado.get("extension").equals(null)) {
            return "SM" + "-" + (resultado.get("numero").toString()) + "-" + (resultado.get("gestion").toString()) + "-" + resultado.get("extension").toString();
        } else {
            return "SM" + "-" + (resultado.get("numero").toString()) + "-" + (resultado.get("gestion").toString());
        }
    }

    public String obtieneNumeroSM(Long numeroSm) throws Exception {
        HashMap resultado = comunService.obtenerNumeroGestionExtensionCodigoSM(numeroSm);
        return (resultado.get("numero")).toString();
    }

    public String obtieneExtensionSM(Long numeroSm) throws Exception {
        HashMap resultado = comunService.obtenerNumeroGestionExtensionCodigoSM(numeroSm);
        return (resultado.get("extension")).toString();
    }

    public String obtieneGestion(Long numeroSm) throws Exception {
        return comunService.obtenerNumeroGestionExtensionCodigoSM(numeroSm).get("gestion").toString();
    }

    public String vistaArchivo() {

        return "reciboclon";
    }

    @Transactional
    public void accion_GeneraRecibo() throws Exception {
        try {
            if (tasa != null) {
                if (Integer.toString(cantidad) != null && cantidad > 0) {
                    if (reciboService.activaGuardadoReciboCantidad(tasaService.obtenerTasaPorIdTasa(tasa), lstDesposito, cantidad)) {
//                        if (activaRecibo) {
//                            reciboReasignado = obtieneReciboReasignar(numeroRecibo, serieRecibo);
//                        }
//                        if (activaTitulo) {
//                            tituloReasignado = obtieneTituloReasignar(numeroTitulo, serieTitulo);
//                        }
                        regional.setIdRegional(usuario.getIdregional());
                        recibo.setNumeroTramiteIngresado(numeroDeFormulario);
                        //modificacionrecibo
                        if (!claseNizaInt.isEmpty()) {
                            recibo.setClaseTramite(Integer.parseInt(claseNizaInt));
                        }
                        //                        recibo.setClaseTramite(claseNiza.intValue());
                        recibo.setGestionTramiteIngresado(gestionTramite);
                        recibo.setTipoTramiteIngresado(tipoTramiteCombo);
                        recibo.setNumeroTramiteIngresado(numeroExpediente);
                        if (!gestionTramite.equals("")) {
                            recibo.setGestionTramite(new Long(gestionTramite));
                        }
                        recibo.setApoderado(apoderadoTramite);
                        recibo.setSolicitante(solicitanteTramite);
                        recibo.setDescripcionMarca(marcaTramite);
                        recibo.setTipoTramite(tipoTramiteCombo);
                        recibo.setIdRegional(usuario.getIdregional());
                        reciboService.guardaReciboDepositoTramiteCantidad(recibo, tasaService.obtenerTasaPorIdTasa(tasa), lstDesposito, regional, usuario, cantidad, reciboReasignado, tituloReasignado, activaRecibo, activaTitulo);
//                        imprimir(reciboService.guardaReciboDepositoTramiteCantidadRecibo(recibo, tasaService.obtenerTasaPorIdTasa(tasa), lstDesposito, regional, usuario, cantidad, reciboReasignado, tituloReasignado, activaRecibo, activaTitulo));
                        lstDesposito = depositoService.listaDepositoConSaldoPorUSuarioSaldo(usuario);
                        if (!lstDesposito.isEmpty()) {
                            if (tipoTramite != null) {
                                lstTasa = tasaService.obtenerListaTasaActivasPorTipoTramite(tipoTramite);
                            } else {
                                lstTasa = tasaService.obtenerListaTasaActivas();
                            }
                        } else {
                            lstTasa = tasaService.obtenerListaTasaActivas();
                            lstDesposito = new ArrayList<>();
                        }
                        recibo = new Recibo();
                        reciboReasignado = null;
                        tituloReasignado = null;
                        numeroRecibo = 0;
                        serieRecibo = "null";
                        activaRecibo = false;
                        numeroTitulo = 0;
                        serieTitulo = "null";
                        activaTitulo = null;
                        depositoGuardar = new Deposito();
                        Date fechaDia = comunService.obtenerFechaServidor(1L);
                        depositoGuardar.setFechaDeposito(fechaDia);
//                        listaReciboGenerado = reciboService.listaReciboEmitidoPorFechaYUsuario(fechaDia, usuario);
                        listaReciboGenerado = reciboService.listaReciboEmitidoPorFechas(comunService.obtenerFechaHoraServidor(1L), usuario);
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "El recibo fue generado.", ""));
                    } else {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "No es suficiente la cantidad para cubrir el costo del recibos.", ""));
                    }
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "La cantidad debe ser un número y mayor a cero.", ""));
                }
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Es necesario seleccionar una tasa.", ""));
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public void validaCamposRecibo() {

    }

    public void activaCambios() {

    }

    public Recibo obtieneReciboReasignar(int numeroRecibo, String serieRecibo) throws Exception {
        try {
            if (reciboService.buscaReciboPorNumeroYSerieRecibo(numeroRecibo, serieRecibo).getIdRecibo() != null) {
                return reciboService.buscaReciboPorNumeroYSerieRecibo(numeroRecibo, serieRecibo);
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El número de recibo no existe o fue anulado.", ""));
                return new Recibo();
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public Recibo obtieneTituloReasignar(int numeroTitulo, String serietitulo) throws Exception {
        try {
            if (reciboService.buscaReciboPorNumeroYSerieTitulo(numeroTitulo, serieTitulo).getIdRecibo() != null) {
                return reciboService.buscaReciboPorNumeroYSerieTitulo(numeroTitulo, serieTitulo);

            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El número de titulo no existe.", ""));
                return new Recibo();
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public String muestraNumerosDeposito(Recibo recibo) throws Exception {
        return depositoService.encuentraDepositosPorRecibo(recibo);
    }

    public String muestraDepositante(Recibo recibo) throws Exception {
        return depositoService.encuentraDepositantePorRecibo(recibo);
    }

    public String muestraCodigoDepos(Recibo recibo) throws Exception {
        return depositoService.encuentraCodigoDepositoPorRecibo(recibo);
    }

    public String muestrafechaDepos(Recibo recibo) throws Exception {
        return depositoService.encuentraFechaDespositoPorRecibo(recibo);
    }

    public void modificaDeposito(Deposito depositoSeleccionado) throws Exception {
        depositoGuardar = depositoSeleccionado;
        tipoSucursalCombo = depositoGuardar.getDeposCodDep().toString();
        lstDesposito.remove(depositoService.encuentraPosicionDeposito(depositoGuardar, lstDesposito));
    }

    public void eliminaDeposito(Deposito depositoSeleccionado) throws Exception {
        depositoGuardar = depositoSeleccionado;
        lstDesposito.remove(depositoService.encuentraPosicionDeposito(depositoGuardar, lstDesposito));
    }

    public Boolean activaModificarEliminarDeposito(Deposito deposito) throws Exception {
        return depositoService.depositoParaModificarEliminar(deposito);
    }

//    public void accion_adicionaDeposito() {
//
//        depositoGuardar.setSaldo(depositoGuardar.getMonto());
//        depositoGuardar.setBanco(valorOpcionesRadioBanco);
//        lstDesposito.add(depositoGuardar);
//        depositoGuardar = new Deposito();
//
//    }
    public void accion_adicionaDeposito() throws Exception {
        Boolean camposValidadosDeposito[];
        camposValidadosDeposito = validaCamposDeposito(depositoGuardar);
        cambiaColorNumeroDeposito = camposValidadosDeposito[1];
        cambiaColorPersonaDeposito = camposValidadosDeposito[2];
        cambiaColorFechaDeposito = camposValidadosDeposito[3];
        cambiaColormontoDeposito = camposValidadosDeposito[4];
        if (camposValidadosDeposito[0]) {
            if (!depositoService.validaNumeroDeposito(depositoGuardar)) {
                try {
                    depositoGuardar.setSaldo(depositoGuardar.getMonto());
                    depositoGuardar.setBanco(valorOpcionesRadioBanco);
                    depositoGuardar.setDeposCodDep(Integer.parseInt(tipoSucursalCombo));
                    depositoGuardar.setNombreDepositante(depositoGuardar.getNombreDepositante().toUpperCase());
                    lstDesposito.add(depositoGuardar);
                    depositoGuardar = new Deposito();
                    depositoGuardar.setFechaDeposito(comunService.obtenerFechaHoraServidor(1L));
                } catch (Exception e) {
                    e.printStackTrace();
                }
              //  depositoGuardar = new Deposito();
                // depositoGuardar.setFechaDeposito(comunService.obtenerFechaHoraServidor(1L));

            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El numero de deposito ya esta regsitrado", ""));

            }
        } else {
            validaCamposDepositoMuestraMensajes(camposValidadosDeposito);
        }
    }

    public void validaCamposDepositoMuestraMensajes(Boolean[] camposValidadosDeposito) {
        if (camposValidadosDeposito[1]) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Se requiere llenar el campo de numero de Deposito", ""));
        }
        if (camposValidadosDeposito[2]) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Se requiere llenar el dato del depositante", ""));

        }
        if (camposValidadosDeposito[3]) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Se requiere seleccionar la fecha que se realizo el deposito", ""));
        }
        if (camposValidadosDeposito[4]) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Se requiere llenar el dato del moto de deposito", ""));

        }
    }

    public Boolean[] validaCamposDeposito(Deposito deposito) {
        Boolean sw = true;
        Boolean numeroDeposito = false;
        Boolean personaDeposito = false;
        Boolean fechaDeposito = false;
        Boolean montoDeposito = false;

        if (deposito.getNumeroDeposito() == null || deposito.getNumeroDeposito() == 0) {
            sw = false;
            numeroDeposito = true;
        }

        if (deposito.getNombreDepositante() == null || deposito.getNombreDepositante().isEmpty()) {
            sw = false;
            personaDeposito = true;
        }

        if (deposito.getFechaDeposito() == null) {
            sw = false;
            fechaDeposito = true;
        }

        if (deposito.getMonto() == null) {
            sw = false;
            montoDeposito = true;
        }
        Boolean[] listaError = {sw, numeroDeposito, personaDeposito, fechaDeposito, montoDeposito};
        return listaError;
    }

    public String devuelveCodigo(Recibo recibo) {
        String variable = "";

        if (recibo.getTipoTramiteIngresado().length() == 1) {
            variable = recibo.getNumeroTramiteIngresado();

        } else {
            variable = recibo.getTipoTramiteIngresado() + "-" + recibo.getNumeroTramiteIngresado() + "-" + recibo.getGestionTramiteIngresado();
        }

        return variable;
    }

    public String devuelveTituloRecibo(Recibo recibo) {
        String titulo = "";
        if (recibo.getTituloIngresado() > 0) {
            titulo = recibo.getTituloIngresado() + "-" + recibo.getSerieTituloIngresado();

        }

        return titulo;

    }

    public void eliminarReciboFinal() {
        try {
            cont = 0;
            reciboService.eliminaRecibo(reciboEliminar);
//            listaDeposito = depositoService.listaDepositoConSaldo();
//            lstDesposito = depositoService.listaDepositoConSaldoPorUSuario(usuario);
            lstDesposito = depositoService.listaDepositoConSaldoPorUSuarioSaldo(usuario);
            System.out.println("listaDEPSOTIOS" + lstDesposito.size());
            listaReciboGenerado = reciboService.listaReciboEmitidoPorFechas(comunService.obtenerFechaHoraServidor(usuario.getIdregional()), usuario);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "El recibo fue Eliminado", ""));

//            listaRecibo = reciboService.listaReciboEmitidoPorFechaPorUsuarioYRegional(new Date(), usuario, regional);
//            listaDeRecibosAnulados = reciboService.listaReciboEmitidoAnulado(usuario);
//            numeroRecibosModificados = reciboService.listaReciboEmitidoAnulado(usuario).size();
//            numeroTitulosAnulados = reciboService.listadetalleTituloAnuladosPoRecibo(usuario).size();
//            listaDetalleTitulo = reciboService.listadetalleTituloAnuladosPoRecibo(usuario);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void eliminarReciboFinal(Recibo recibo) {

    }

    public void cargaValoresAEliminarRecibo(Recibo recibo) throws Exception {
        reciboEliminar = reciboService.buscaReciboPorIdRecibo(recibo.getIdRecibo());

    }

    public void cargaValoresAAnularRecibo(Recibo recibo) throws Exception {
        reciboAnular = reciboService.buscaReciboPorIdRecibo(recibo.getIdRecibo());
    }

    public void anularReciboFinal() {
        try {
            cont = 0;
            reciboService.anularRecibo(reciboAnular);
//            listaDeposito = depositoService.listaDepositoConSaldo();
//            lstDesposito = depositoService.listaDepositoConSaldoPorUSuario(usuario);
            lstDesposito = depositoService.listaDepositoConSaldoPorUSuarioSaldo(usuario);
            listaReciboGenerado = reciboService.listaReciboEmitidoPorFechas(comunService.obtenerFechaHoraServidor(usuario.getIdregional()), usuario);
//            listaRecibo = reciboService.listaReciboEmitidoPorFechaPorUsuarioYRegional(new Date(), usuario, regional);
//            listaDeRecibosAnulados = reciboService.listaReciboEmitidoAnulado(usuario);
//            numeroRecibosModificados = reciboService.listaReciboEmitidoAnulado(usuario).size();
//            numeroTitulosAnulados = reciboService.listadetalleTituloAnuladosPoRecibo(usuario).size();
//            listaDetalleTitulo = reciboService.listadetalleTituloAnuladosPoRecibo(usuario);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "El recibo fue Anulado", ""));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void postProcessXLS(Object document) {
        HSSFWorkbook wb = (HSSFWorkbook) document;
        HSSFSheet sheet = wb.getSheetAt(0);
        CellStyle style = wb.createCellStyle();
        style.setFillBackgroundColor(IndexedColors.AQUA.getIndex());

        for (Row row : sheet) {
            for (Cell cell : row) {
                cell.setCellValue(cell.getStringCellValue().toUpperCase());
                cell.setCellStyle(style);
            }
        }
    }

    public void listaPorFechas() throws Exception {
        System.out.println("ingreso metodo de derivacion");
        listaReciboGenerado = reciboService.listaReciboEmitidoPorFechas(fechaBuscar, usuario);

    }

    public void activaCamposRecibo() {
        activaCamposRecibo = activaRecibo;
    }

    public void activaCamposTitulo() {
        activaCamposTitulo = activaTitulo;
    }

    public void cargaSerie() {

    }

    public void imprimeResumenConcepto() {

    }

    public void imprimeResumenConceptoDia() {

    }

    public void imprimeDiarioDeposito() {

    }

    public void imprimeDiarioRecibo() {

    }

    public void imprimirRecibo() {

    }

    public void adicionaDepositoBanco() {

    }

    public void modificaDeposito() {

    }

    public void cancelarDeposito() {

    }

    public void seleccionDepositoParaModificar() {

    }

    public void generaReciboTramiteNoRegistrado() {

    }

    public void generaRecibo() {

    }

    /**
     * metodo para impresion del recibo seleccionado en la vista.
     *
     * Creado: Ruben Ramirez Fecha: 14/12/2016
     *
     * @param recibo
     * @throws net.sf.jasperreports.engine.JRException
     * @throws java.io.IOException
     */
    public void imprimir(Recibo recibo) throws JRException, IOException, Exception {
        if (recibo != null) {
            imgSenapi = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/images/logoNuevo.jpg");
            serieReciboR = "SERIE " + recibo.getSerieRecibo();
            montoNumeralR = "Bs." + recibo.getMonto();
            nroReciboR = "No. " + recibo.getNumeroRecibo();
            String depositos = "";
            nombreDepositanteR = "";
            lstDepositoR = depositoService.listaDepositosPorRecibo(recibo);
            int nroLstDeposti = lstDepositoR.size();
            if (nroLstDeposti > 0) {
                if (nroLstDeposti == 1) {
                    nombreDepositanteR += lstDepositoR.get(0).getNombreDepositante();
                    depositos += "CTA. FISCAL BANCO UNION SA Dep: " + lstDepositoR.get(0).getNumeroDeposito();
                } else {
                    for (int i = 0; i < nroLstDeposti - 1; i++) {
                        nombreDepositanteR += lstDepositoR.get(i).getNombreDepositante() + "; ";
                        depositos += "CTA. FISCAL BANCO UNION SA Dep: " + lstDepositoR.get(i).getNumeroDeposito() + "; ";
                    }
                    nombreDepositanteR += lstDepositoR.get(nroLstDeposti - 1).getNombreDepositante();
                    depositos += "CTA. FISCAL BANCO UNION SA Dep: " + lstDepositoR.get(nroLstDeposti - 1).getNumeroDeposito();
                }
            }
            nombreDepositanteR = nombreDepositanteR.replace("&", "&amp;");
            nombreDepositanteR = "<style isBold='true'>La Unidad Administrativa del SENAPI ha recibido de : </style> <style isItalic='true'>" + nombreDepositanteR + "</style>";

            NumeroLetra numeroLetra = new NumeroLetra();

            String monto = numeroLetra.Convertir(montoNumeralR, false);

            if (monto != null) {
                //montoLiteralR = monto + " Cta. Fiscal BANCO UNION SA Dep." + depositos;
                montoLiteralR = "<style isBold='true'>La suma de : </style>" + monto;
            }
            depositos = "<style isBold='true'>Deposito(s) : </style>  <style isItalic='true'>" + depositos + "</style>";

            String conc = "", desc_descripcion_marca = "", clase_tramite = "", titulo_ingresado = "", serie_titulo_ingresado = "";
            if (recibo.getConcepto() != null) {
                conc = recibo.getConcepto();
            }
            if (recibo.getDescripcionMarca() != null) {
                if (!recibo.getDescripcionMarca().trim().equals("")) {
                    desc_descripcion_marca = recibo.getDescripcionMarca() + ",  ";
                }
            }

            clase_tramite = "CLASE: " + recibo.getClaseTramite();

//            if (recibo.getTituloIngresado() != null) {
//                if (recibo.getTituloIngresado() > 0) {
//                    titulo_ingresado = "TITULO N°: " + recibo.getTituloIngresado();
//                }
//            }
//
//            if (recibo.getSerieTituloIngresado() != null) {
//                if (!recibo.getSerieTituloIngresado().trim().equals("")) {
//                    serie_titulo_ingresado = "SERIE: " + recibo.getSerieTituloIngresado();
//                }
//            }
//
//            if (!titulo_ingresado.equals("")) {
//                if (!serie_titulo_ingresado.equals("")) {
//                    conceptoR = conc + ", " + desc_descripcion_marca + clase_tramite + ", " + titulo_ingresado + " " + serie_titulo_ingresado;
//                } else {
//                    conceptoR = conc + ", " + desc_descripcion_marca + clase_tramite + ", " + titulo_ingresado;
//                }
//            } else {
//                conceptoR = conc + ", " + desc_descripcion_marca + clase_tramite;
//            }
            conceptoR = conc + ", " + desc_descripcion_marca + clase_tramite;
            conceptoR = "<style isBold='true'>Por concepto de : </style> <style isItalic='true'>" + conceptoR + "</style> <style isBold='true'>por prestación de Servicios y/o Reposición de materiales.</style>";

            String tipo_tramite_ingresado = "", numero_tramite_ingresado = "", gestion_tramite_ingresado = "";
            if (recibo.getTipoTramite() != null) {
                tipo_tramite_ingresado = recibo.getTipoTramiteIngresado() + " ";
            }
            if (recibo.getNumeroTramiteIngresado() != null) {
                numero_tramite_ingresado = recibo.getNumeroTramiteIngresado();
            }
            if (recibo.getGestionTramiteIngresado() != null) {
                gestion_tramite_ingresado = recibo.getGestionTramiteIngresado();
            }

            numero_tramite_ingresado = String.format("%6s", numero_tramite_ingresado).replace(' ', '0');

            tipoTramiteR = "<style isBold='true'>Del expediente : </style>" + tipo_tramite_ingresado + "-" + numero_tramite_ingresado + "-" + gestion_tramite_ingresado;

            // brinda formato a la fecha y hora de registros date.
            DateFormatSymbols dateFormatSymbols = new DateFormatSymbols();
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

            SimpleDateFormat formateador1 = new SimpleDateFormat("d");
            SimpleDateFormat formateador2 = new SimpleDateFormat("MMMM", dateFormatSymbols);
            SimpleDateFormat formateador3 = new SimpleDateFormat("yyyy");
            SimpleDateFormat formateador4 = new SimpleDateFormat("dd-MM-yyyy/HH:mm:ss");

            // fecha actual de al base de datos
            Date fechaPresente = comunService.obtenerFechaHoraServidor(1L);

            if (fechaPresente != null) {
                diaR = formateador1.format(fechaPresente);
                mesR = formateador2.format(fechaPresente);
                anioR = formateador3.format(fechaPresente);
            }
            String fechaSistema = formateador4.format(fechaPresente);

            listUsuario = usuarioService.listaUsuarioXidPagina(idUsuarioSesion);
            Usuario user = listUsuario.get(0);

            if (user.getIdusuario() == 82) {
                imageFirmaR = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/images/limberAlarcon.png");
            } else {
                imageFirmaR = null;
            }

            //nombreFirmaR = user.getNombre() + " " + user.getPrimer_apellido() + " " + user.getSegundo_apellido();
            nombreFirmaR = devuelveNombreJuridicoONatural(user.getNombre(), user.getPrimer_apellido(), user.getSegundo_apellido());
            lugaryFecha = "<style isBold='true'>La Paz</style> " + diaR + " <style isBold='true'>de</style> " + mesR + " <style isBold='true'>del</style> " + anioR;

            parametros.put("montoNumeral", montoNumeralR);
            parametros.put("nombreDepositante", nombreDepositanteR);
            parametros.put("montoLiteral", montoLiteralR);
            parametros.put("depositos", depositos);
            parametros.put("concepto", conceptoR);
            parametros.put("tipoTramite", tipoTramiteR);
            parametros.put("lugarFecha", lugaryFecha);
            parametros.put("fechaSistema", fechaSistema);
            parametros.put("marcadeAgua", imgSenapi);
            parametros.put("imageFirma", imageFirmaR);
            parametros.put("nombreFirma", nombreFirmaR);
            parametros.put("serieRecibo", serieReciboR);
            parametros.put("nroRecibo", nroReciboR);

            String filename = "ReciboOficial.pdf";
            String jasperPath = "/template/recibo/reciboOficial.jasper";
            this.generateReport(parametros, jasperPath, filename);
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", ""));
        }
    }

    public void verPdf(Recibo recibo) throws JRException, IOException, Exception {
        if (recibo != null) {
            imgSenapi = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/images/logoNuevo.jpg");
            serieReciboR = "SERIE " + recibo.getSerieRecibo();
            montoNumeralR = "" + recibo.getMonto();
            nroReciboR = "No. " + recibo.getNumeroRecibo();
            String depositos = "";
            nombreDepositanteR = "";
            lstDepositoR = depositoService.listaDepositosPorRecibo(recibo);
            int nroLstDeposti = lstDepositoR.size();
            if (nroLstDeposti > 0) {
                if (nroLstDeposti == 1) {
                    nombreDepositanteR += lstDepositoR.get(0).getNombreDepositante();
                    depositos += "CTA. FISCAL BANCO UNION SA Dep: " + lstDepositoR.get(0).getNumeroDeposito();
                } else {
                    for (int i = 0; i < nroLstDeposti - 1; i++) {
                        nombreDepositanteR += lstDepositoR.get(i).getNombreDepositante() + "; ";
                        depositos += "CTA. FISCAL BANCO UNION SA Dep: " + lstDepositoR.get(i).getNumeroDeposito() + "; ";
                    }
                    nombreDepositanteR += lstDepositoR.get(nroLstDeposti - 1).getNombreDepositante();
                    depositos += "CTA. FISCAL BANCO UNION SA Dep: " + lstDepositoR.get(nroLstDeposti - 1).getNumeroDeposito();
                }
            }
            nombreDepositanteR = nombreDepositanteR.replace("&", "&amp;");
            nombreDepositanteR = "<style isBold='true'>La Unidad Administrativa del SENAPI ha recibido de : </style> <style isItalic='true'>" + nombreDepositanteR + "</style>";

            NumeroLetra numeroLetra = new NumeroLetra();

            String monto = numeroLetra.Convertir(montoNumeralR, false);

            if (monto != null) {
                //montoLiteralR = monto + " Cta. Fiscal BANCO UNION SA Dep." + depositos;
                montoLiteralR = "<style isBold='true'>La suma de : </style>" + monto;
            }
            depositos = "<style isBold='true'>Deposito(s) : </style>  <style isItalic='true'>" + depositos + "</style>";

            String conc = "", desc_descripcion_marca = "", clase_tramite = "", titulo_ingresado = "", serie_titulo_ingresado = "";
            if (recibo.getConcepto() != null) {
                conc = recibo.getConcepto();
            }
            if (recibo.getDescripcionMarca() != null) {
                if (!recibo.getDescripcionMarca().trim().equals("")) {
                    desc_descripcion_marca = recibo.getDescripcionMarca() + ",  ";
                }
            }

            clase_tramite = "CLASE: " + recibo.getClaseTramite();

            if (recibo.getTituloIngresado() != null) {
                if (recibo.getTituloIngresado() > 0) {
                    titulo_ingresado = "TITULO N°: " + recibo.getTituloIngresado();
                }
            }

            if (recibo.getSerieTituloIngresado() != null) {
                if (!recibo.getSerieTituloIngresado().trim().equals("")) {
                    serie_titulo_ingresado = "SERIE: " + recibo.getSerieTituloIngresado();
                }
            }

            if (!titulo_ingresado.equals("")) {
                if (!serie_titulo_ingresado.equals("")) {
                    conceptoR = conc + ", " + desc_descripcion_marca + clase_tramite + ", " + titulo_ingresado + " " + serie_titulo_ingresado;
                } else {
                    conceptoR = conc + ", " + desc_descripcion_marca + clase_tramite + ", " + titulo_ingresado;
                }
            } else {
                conceptoR = conc + ", " + desc_descripcion_marca + clase_tramite;
            }
            conceptoR = "<style isBold='true'>Por concepto de : </style> <style isItalic='true'>" + conceptoR + "</style> <style isBold='true'>por prestación de Servicios y/o Reposición de materiales.</style>";

            String tipo_tramite_ingresado = "", numero_tramite_ingresado = "", gestion_tramite_ingresado = "";
            if (recibo.getTipoTramite() != null) {
                tipo_tramite_ingresado = recibo.getTipoTramiteIngresado() + " ";
            }
            if (recibo.getNumeroTramiteIngresado() != null) {
                numero_tramite_ingresado = recibo.getNumeroTramiteIngresado();
            }
            if (recibo.getGestionTramiteIngresado() != null) {
                gestion_tramite_ingresado = recibo.getGestionTramiteIngresado();
            }

            numero_tramite_ingresado = String.format("%6s", numero_tramite_ingresado).replace(' ', '0');

            tipoTramiteR = "<style isBold='true'>Del expediente : </style>" + tipo_tramite_ingresado + "-" + numero_tramite_ingresado + "-" + gestion_tramite_ingresado;

            // brinda formato a la fecha y hora de registros date.
            DateFormatSymbols dateFormatSymbols = new DateFormatSymbols();
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

            SimpleDateFormat formateador1 = new SimpleDateFormat("d");
            SimpleDateFormat formateador2 = new SimpleDateFormat("MMMM", dateFormatSymbols);
            SimpleDateFormat formateador3 = new SimpleDateFormat("yyyy");
            SimpleDateFormat formateador4 = new SimpleDateFormat("dd-MM-yyyy/HH:mm:ss");

            // fecha actual de al base de datos
            Date fechaPresente = comunService.obtenerFechaHoraServidor(1L);

            if (fechaPresente != null) {
                diaR = formateador1.format(fechaPresente);
                mesR = formateador2.format(fechaPresente);
                anioR = formateador3.format(fechaPresente);
            }
            String fechaSistema = formateador4.format(fechaPresente);

            listUsuario = usuarioService.listaUsuarioXidPagina(idUsuarioSesion);
            Usuario user = listUsuario.get(0);

            if (user.getIdusuario() == 82) {
                imageFirmaR = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/images/limberAlarcon.png");
            } else {
                imageFirmaR = null;
            }

            nombreFirmaR = user.getNombre() + " " + user.getPrimer_apellido() + " " + user.getSegundo_apellido();
            lugaryFecha = "<style isBold='true'>La Paz</style> " + diaR + " <style isBold='true'>de</style> " + mesR + " <style isBold='true'>del</style> " + anioR;

            parametros.put("montoNumeral", montoNumeralR);
            parametros.put("nombreDepositante", nombreDepositanteR);
            parametros.put("montoLiteral", montoLiteralR);
            parametros.put("depositos", depositos);
            parametros.put("concepto", conceptoR);
            parametros.put("tipoTramite", tipoTramiteR);
            parametros.put("lugarFecha", lugaryFecha);
            parametros.put("fechaSistema", fechaSistema);
            parametros.put("marcadeAgua", imgSenapi);
            parametros.put("imageFirma", imageFirmaR);
            parametros.put("nombreFirma", nombreFirmaR);
            parametros.put("serieRecibo", serieReciboR);
            parametros.put("nroRecibo", nroReciboR);

            String filename = "ReciboOficial.pdf";
            String jasperPath = "/template/recibo/reciboOficial.jasper";
            this.PDFSD(parametros, jasperPath, filename);
        } else {
            // mensaje
        }
    }

    /**
     * Metodo para generar el reporte y pasarle en formato StreamContente a la
     * variable de sesion stream. Para posterior despliegue en la vista.
     *
     * Creado: Ruben Ramirez Fecha: 14/12/2016
     *
     * @param params
     * @param jasperPath
     * @param fileName
     * @throws java.io.IOException
     * @throws net.sf.jasperreports.engine.JRException
     *
     */
    public void generateReport(Map<String, Object> params, String jasperPath, String fileName) throws IOException, JRException, Exception {
        String relativeWebPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath(jasperPath);
        File file = new File(relativeWebPath);
        ByteArrayOutputStream outputStream = getOutputStreamFromReport(params, file.getPath());
        setStreamSession(getStreamContentFromOutputStream(outputStream, "application/pdf", fileName));

    }

    /**
     * Metodo para generar el reporte en formato ByteArrayOutputStream con
     * codigo javascript en el pdf para generacion de impresion silencionsa.
     *
     * Creado: Ruben Ramirez Fecha: 14/12/2016
     *
     * @param map
     * @param pathJasper
     *
     * @return ByteArrayOutputStream
     * @throws java.lang.Exception
     */
    public static ByteArrayOutputStream getOutputStreamFromReport(Map<String, Object> map, String pathJasper) throws Exception {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        FacesContext context = FacesContext.getCurrentInstance();

        JasperPrint jp = JasperFillManager.fillReport(pathJasper, map, new JREmptyDataSource());
        JRPdfExporter exporter = new JRPdfExporter();
        exporter.setParameter(JRPdfExporterParameter.JASPER_PRINT, jp);
        exporter.setParameter(JRPdfExporterParameter.OUTPUT_STREAM, os);
        exporter.setParameter(JRPdfExporterParameter.PDF_JAVASCRIPT, "app.setTimeOut('this.print({bUI: true,bSilent: false,bShrinkToFit: true});',200);");
        exporter.exportReport();

        os.flush();
        os.close();
        return os;
    }

    public void PDFSD(Map<String, Object> params, String jasperPath, String fileName) throws JRException, IOException {
        String relativeWebPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath(jasperPath);
        File file = new File(relativeWebPath);
        JasperPrint print = JasperFillManager.fillReport(file.getPath(), params, new JREmptyDataSource());
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-Disposition", "inline; filename=" + fileName);
        ServletOutputStream stream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(print, stream);
        FacesContext.getCurrentInstance().responseComplete();
    }

    /**
     * Metodo para generar el reporte en formato StreamedContent.
     *
     * Creado: Ruben Ramirez Fecha: 14/12/2016
     *
     * @param os
     * @param contentType
     * @param nameFile
     *
     * @return StreamedContent
     * @throws java.lang.Exception
     * @throws java.io.IOException
     */
    public static StreamedContent getStreamContentFromOutputStream(ByteArrayOutputStream os, String contentType, String nameFile) throws Exception, IOException {
        StreamedContent file = null;
        InputStream is = new ByteArrayInputStream(os.toByteArray());
        file = new DefaultStreamedContent(is, "application/pdf", nameFile);
        return file;
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

    /**
     * metodo para impresion del recibo seleccionado en la vista.
     *
     * Creado: Ruben Ramirez Fecha: 14/12/2016
     *
     * @param recibo
     * @param tipo
     * @throws net.sf.jasperreports.engine.JRException
     * @throws java.io.IOException
     */
    public void imprimir(Recibo recibo, String tipo) throws JRException, IOException, Exception {
//        System.out.println("imprimir recibo");

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
        if (recibo != null) {
            DateFormatSymbols dateFormatSymbols = new DateFormatSymbols();
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

            SimpleDateFormat formateador1 = new SimpleDateFormat("d");
            SimpleDateFormat formateador2 = new SimpleDateFormat("MMMM", dateFormatSymbols);
            SimpleDateFormat formateador3 = new SimpleDateFormat("yyyy");
            SimpleDateFormat formateador4 = new SimpleDateFormat("dd/MM/yyyy");

            String codigoQR;
            serieReciboR = "SERIE " + recibo.getSerieRecibo();
            codigoQR = serieReciboR + "|";
            montoNumeralR = "Bs." + recibo.getMonto();
            codigoQR += montoNumeralR + "|";
            nroReciboR = "No. " + recibo.getNumeroRecibo();
            codigoQR += nroReciboR + "|";
            String depositos = "";
            String depositosQR = "";
            nombreDepositanteR = "";
            lstDepositoR = depositoService.listaDepositosPorRecibo(recibo);
            int nroLstDeposti = lstDepositoR.size();
            if (nroLstDeposti > 0) {
                if (nroLstDeposti == 1) {
                    nombreDepositanteR += lstDepositoR.get(0).getNombreDepositante();
                    depositos += "CTA. FISCAL BANCO UNION SA Dep: " + lstDepositoR.get(0).getNumeroDeposito();
                    depositosQR += "Dep: " + lstDepositoR.get(0).getNumeroDeposito();
                } else {
                    for (int i = 0; i < nroLstDeposti - 1; i++) {
                        nombreDepositanteR += lstDepositoR.get(i).getNombreDepositante() + "; ";
                        depositos += "CTA. FISCAL BANCO UNION SA Dep: " + lstDepositoR.get(i).getNumeroDeposito() + "; ";
                        depositosQR += "Dep: " + lstDepositoR.get(i).getNumeroDeposito() + "; ";
                    }
                    nombreDepositanteR += lstDepositoR.get(nroLstDeposti - 1).getNombreDepositante();
                    depositos += "CTA. FISCAL BANCO UNION SA Dep: " + lstDepositoR.get(nroLstDeposti - 1).getNumeroDeposito();
                    depositosQR += "Dep: " + lstDepositoR.get(nroLstDeposti - 1).getNumeroDeposito();
                }
            }
            nombreDepositanteR = nombreDepositanteR.replace("&", "&amp;");
            codigoQR += nombreDepositanteR + "|";
            nombreDepositanteR = "<style isBold='true'>La Unidad Administrativa del SENAPI ha recibido de : </style> <style isItalic='true'>" + nombreDepositanteR + "</style>";
            codigoQR += depositosQR + "|";
            NumeroLetra numeroLetra = new NumeroLetra();
            String monto = numeroLetra.Convertir(recibo.getMonto().toString(), false);
            if (monto != null) {
                montoLiteralR = "<style isBold='true'>La suma de : </style>" + monto;
            }
            depositos = "<style isBold='true'>Deposito(s) : </style>  <style isItalic='true'>" + depositos + "</style>";

            String conc = "", desc_descripcion_marca = "";
            if (recibo.getConcepto() != null) {
                conc = recibo.getConcepto();
            }
            if (recibo.getDescripcionMarca() != null) {
                if (!recibo.getDescripcionMarca().trim().equals("")) {
//                    desc_descripcion_marca = recibo.getDescripcionMarca() + ",  ";
                    desc_descripcion_marca = recibo.getDescripcionMarca().replace("&", "&amp;") + ",  ";
                }
            }
            String clase_tramite = "CLASE: " + recibo.getClaseTramite();
            conceptoR = conc + ", " + desc_descripcion_marca + clase_tramite;
            conceptoR = "<style isBold='true'>Por concepto de : </style> <style isItalic='true'>" + conceptoR + "</style> <style isBold='true'>por prestación de Servicios y/o Reposición de materiales.</style>";

            String tipo_tramite_ingresado = "", numero_tramite_ingresado = "", gestion_tramite_ingresado = "";
            if (recibo.getTipoTramite() != null) {
                tipo_tramite_ingresado = recibo.getTipoTramiteIngresado() + " ";
            }
            if (recibo.getNumeroTramiteIngresado() != null) {
                numero_tramite_ingresado = recibo.getNumeroTramiteIngresado();
            }
            if (recibo.getGestionTramiteIngresado() != null) {
                gestion_tramite_ingresado = recibo.getGestionTramiteIngresado();
            }
            numero_tramite_ingresado = String.format("%6s", numero_tramite_ingresado).replace(' ', '0');
            tipoTramiteR = "<style isBold='true'>Del expediente : </style>" + tipo_tramite_ingresado + "-" + numero_tramite_ingresado + "-" + gestion_tramite_ingresado;
            codigoQR += tipo_tramite_ingresado + "-" + numero_tramite_ingresado + "-" + gestion_tramite_ingresado + "|";

//            Date fechaPresente = comunService.obtenerFechaHoraServidor(1L);
            Date fechaPresente = recibo.getFechaEmisionRecibo();
            if (fechaPresente != null) {
                diaR = formateador1.format(fechaPresente);
                mesR = formateador2.format(fechaPresente);
                anioR = formateador3.format(fechaPresente);
                codigoQR += formateador4.format(fechaPresente);
            }
            lugaryFecha = "<style isBold='true'>La Paz</style> " + diaR + " <style isBold='true'>de</style> " + mesR + " <style isBold='true'>del</style> " + anioR;

            listUsuario = usuarioService.listaUsuarioXidPagina(idUsuarioSesion);
            Usuario user = listUsuario.get(0);
            nombreFirmaR = devuelveNombreJuridicoONatural(user.getNombre(), user.getPrimer_apellido(), user.getSegundo_apellido());

            //aumetado para firmas
            String rutaImgFirma = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/images/" + user.getLogin() + ".png");

            File f = new File(rutaImgFirma);
            if (f.exists()) {
                imageFirmaR = rutaImgFirma;
            } else {
                imageFirmaR = null;
            }

            String imagenDibuja = rutaCarpeta + File.separator + nroReciboR;
            this.creaCodigoQR(codigoQR, imagenDibuja);
            String imgQR = imagenDibuja;

            parametros.put("montoNumeral", montoNumeralR);
            parametros.put("nombreDepositante", nombreDepositanteR);
            parametros.put("montoLiteral", montoLiteralR);
            parametros.put("depositos", depositos);
            parametros.put("concepto", conceptoR);
            parametros.put("tipoTramite", tipoTramiteR);
            parametros.put("lugarFecha", lugaryFecha);
            parametros.put("marcadeAgua", imgSenapi);
            parametros.put("nombreFirma", nombreFirmaR);
            parametros.put("serieRecibo", serieReciboR);
            parametros.put("nroRecibo", nroReciboR);
            parametros.put("imagenFirma", imageFirmaR);
            parametros.put("imagenQr", imgQR);

            String filename = "ReciboOficial.pdf";
            String jasperPath = "/template/recibo/reciboOficial.jasper";

            if (tipo.equals("IMP")) {
                this.generateReport(parametros, jasperPath, filename);
            }
            if (tipo.equals("VER")) {
                this.PDFSD(parametros, jasperPath, filename);
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", ""));
        }

//        System.out.println("salio de imprimir recibo");
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

    public String getNumeroSerieRecibos() {
        return numeroSerieRecibos;
    }

    public void setNumeroSerieRecibos(String numeroSerieRecibos) {
        this.numeroSerieRecibos = numeroSerieRecibos;
    }

    public String getNumeroSerieTitulos() {
        return numeroSerieTitulos;
    }

    public void setNumeroSerieTitulos(String numeroSerieTitulos) {
        this.numeroSerieTitulos = numeroSerieTitulos;
    }

    public Long getTasa() {
        return tasa;
    }

    public void setTasa(Long tasa) {
        this.tasa = tasa;
    }

    public Boolean getActivaBotonGeneraCostoTotal() {
        return activaBotonGeneraCostoTotal;
    }

    public void setActivaBotonGeneraCostoTotal(Boolean activaBotonGeneraCostoTotal) {
        this.activaBotonGeneraCostoTotal = activaBotonGeneraCostoTotal;
    }

    public String getRegionalConbo() {
        return regionalConbo;
    }

    public void setRegionalConbo(String regionalConbo) {
        this.regionalConbo = regionalConbo;
    }

    public String getTipoTramiteDescripcion() {
        return tipoTramiteDescripcion;
    }

    public void setTipoTramiteDescripcion(String tipoTramiteDescripcion) {
        this.tipoTramiteDescripcion = tipoTramiteDescripcion;
    }

    public Boolean getCambiaColorNumeroDeposito() {
        return cambiaColorNumeroDeposito;
    }

    public void setCambiaColorNumeroDeposito(Boolean cambiaColorNumeroDeposito) {
        this.cambiaColorNumeroDeposito = cambiaColorNumeroDeposito;
    }

    public Boolean getCambiaColorPersonaDeposito() {
        return cambiaColorPersonaDeposito;
    }

    public void setCambiaColorPersonaDeposito(Boolean cambiaColorPersonaDeposito) {
        this.cambiaColorPersonaDeposito = cambiaColorPersonaDeposito;
    }

    public Boolean getCambiaColorFechaDeposito() {
        return cambiaColorFechaDeposito;
    }

    public void setCambiaColorFechaDeposito(Boolean cambiaColorFechaDeposito) {
        this.cambiaColorFechaDeposito = cambiaColorFechaDeposito;
    }

    public Boolean getCambiaColorBancoDeposito() {
        return cambiaColorBancoDeposito;
    }

    public void setCambiaColorBancoDeposito(Boolean cambiaColorBancoDeposito) {
        this.cambiaColorBancoDeposito = cambiaColorBancoDeposito;
    }

    public Boolean getCambiaColormontoDeposito() {
        return cambiaColormontoDeposito;
    }

    public void setCambiaColormontoDeposito(Boolean cambiaColormontoDeposito) {
        this.cambiaColormontoDeposito = cambiaColormontoDeposito;
    }

    public String getTipoBanco() {
        return tipoBanco;
    }

    public void setTipoBanco(String tipoBanco) {
        this.tipoBanco = tipoBanco;
    }

    public Boolean getActivaEliminaModificaDeposito() {
        return activaEliminaModificaDeposito;
    }

    public void setActivaEliminaModificaDeposito(Boolean activaEliminaModificaDeposito) {
        this.activaEliminaModificaDeposito = activaEliminaModificaDeposito;
    }

    public Boolean getActivaBotonDeposito() {
        return activaBotonDeposito;
    }

    public void setActivaBotonDeposito(Boolean activaBotonDeposito) {
        this.activaBotonDeposito = activaBotonDeposito;
    }

    public Boolean getCampoTitulo() {
        return campoTitulo;
    }

    public void setCampoTitulo(Boolean campoTitulo) {
        this.campoTitulo = campoTitulo;
    }

    public Boolean getCargarSerieTiulo() {
        return cargarSerieTiulo;
    }

    public void setCargarSerieTiulo(Boolean cargarSerieTiulo) {
        this.cargarSerieTiulo = cargarSerieTiulo;
    }

    public Boolean getOcultaCargaDeTitulos() {
        return ocultaCargaDeTitulos;
    }

    public void setOcultaCargaDeTitulos(Boolean ocultaCargaDeTitulos) {
        this.ocultaCargaDeTitulos = ocultaCargaDeTitulos;
    }

    public Boolean getOcultaCargaDeRecibo() {
        return ocultaCargaDeRecibo;
    }

    public void setOcultaCargaDeRecibo(Boolean ocultaCargaDeRecibo) {
        this.ocultaCargaDeRecibo = ocultaCargaDeRecibo;
    }

    public Boolean getCargarNumeroRecibo() {
        return cargarNumeroRecibo;
    }

    public void setCargarNumeroRecibo(Boolean cargarNumeroRecibo) {
        this.cargarNumeroRecibo = cargarNumeroRecibo;
    }

    public Boolean getOcultaCargaDeRecibos() {
        return ocultaCargaDeRecibos;
    }

    public void setOcultaCargaDeRecibos(Boolean ocultaCargaDeRecibos) {
        this.ocultaCargaDeRecibos = ocultaCargaDeRecibos;
    }

    public Boolean getMuestraFormularioDeposito() {
        return muestraFormularioDeposito;
    }

    public void setMuestraFormularioDeposito(Boolean muestraFormularioDeposito) {
        this.muestraFormularioDeposito = muestraFormularioDeposito;
    }

    public Boolean isVariableRenderizaTramitesRegistrados() {
        return variableRenderizaTramitesRegistrados;
    }

    public void setVariableRenderizaTramitesRegistrados(Boolean variableRenderizaTramitesRegistrados) {
        this.variableRenderizaTramitesRegistrados = variableRenderizaTramitesRegistrados;
    }

    public Boolean isCargaTramitesNoRegistrados() {
        return cargaTramitesNoRegistrados;
    }

    public void setCargaTramitesNoRegistrados(Boolean cargaTramitesNoRegistrados) {
        this.cargaTramitesNoRegistrados = cargaTramitesNoRegistrados;
    }

    public Boolean isActivaPanelesSiExisteTramite() {
        return activaPanelesSiExisteTramite;
    }

    public void setActivaPanelesSiExisteTramite(Boolean activaPanelesSiExisteTramite) {
        this.activaPanelesSiExisteTramite = activaPanelesSiExisteTramite;
    }

    public Boolean isActivaPanelesSiExisteTramiteNoEncontrado() {
        return activaPanelesSiExisteTramiteNoEncontrado;
    }

    public void setActivaPanelesSiExisteTramiteNoEncontrado(Boolean activaPanelesSiExisteTramiteNoEncontrado) {
        this.activaPanelesSiExisteTramiteNoEncontrado = activaPanelesSiExisteTramiteNoEncontrado;
    }

    public String getSerieRecuperada() {
        return serieRecuperada;
    }

    public void setSerieRecuperada(String serieRecuperada) {
        this.serieRecuperada = serieRecuperada;
    }

    public int getNumeroTitulosAnulados() {
        return numeroTitulosAnulados;
    }

    public void setNumeroTitulosAnulados(int numeroTitulosAnulados) {
        this.numeroTitulosAnulados = numeroTitulosAnulados;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getNumeroRecibo() {
        return numeroRecibo;
    }

    public void setNumeroRecibo(int numeroRecibo) {
        this.numeroRecibo = numeroRecibo;
    }

    public int getNumeroRecibosModificados() {
        return numeroRecibosModificados;
    }

    public void setNumeroRecibosModificados(int numeroRecibosModificados) {
        this.numeroRecibosModificados = numeroRecibosModificados;
    }

    public Date getFechaImpresion() {
        return fechaImpresion;
    }

    public void setFechaImpresion(Date fechaImpresion) {
        this.fechaImpresion = fechaImpresion;
    }

    public BigDecimal getTotalRecibo() {
        return totalRecibo;
    }

    public void setTotalRecibo(BigDecimal totalRecibo) {
        this.totalRecibo = totalRecibo;
    }

    public int getNumeorSiguienteRecibo() {
        return numeorSiguienteRecibo;
    }

    public void setNumeorSiguienteRecibo(int numeorSiguienteRecibo) {
        this.numeorSiguienteRecibo = numeorSiguienteRecibo;
    }

    public int getNumeorSiguienteTitulo() {
        return numeorSiguienteTitulo;
    }

    public void setNumeorSiguienteTitulo(int numeorSiguienteTitulo) {
        this.numeorSiguienteTitulo = numeorSiguienteTitulo;
    }

    public Boolean isReporteRecibo() {
        return reporteRecibo;
    }

    public void setReporteRecibo(Boolean reporteRecibo) {
        this.reporteRecibo = reporteRecibo;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public String getRegionalDato1() {
        return regionalDato1;
    }

    public void setRegionalDato1(String regionalDato1) {
        this.regionalDato1 = regionalDato1;
    }

    public String getRegionalDato2() {
        return regionalDato2;
    }

    public void setRegionalDato2(String regionalDato2) {
        this.regionalDato2 = regionalDato2;
    }

    public int getCont() {
        return cont;
    }

    public void setCont(int cont) {
        this.cont = cont;
    }

    public StreamedContent getStreamedContent1() {
        return streamedContent1;
    }

    public void setStreamedContent1(StreamedContent streamedContent1) {
        this.streamedContent1 = streamedContent1;
    }

    public InputStream getStream1() {
        return stream1;
    }

    public void setStream1(InputStream stream1) {
        this.stream1 = stream1;
    }

    public List<String> getListaDeRecibosAnulados() {
        return listaDeRecibosAnulados;
    }

    public void setListaDeRecibosAnulados(List<String> listaDeRecibosAnulados) {
        this.listaDeRecibosAnulados = listaDeRecibosAnulados;
    }

    public List<String> getListaDetalleTitulo() {
        return listaDetalleTitulo;
    }

    public void setListaDetalleTitulo(List<String> listaDetalleTitulo) {
        this.listaDetalleTitulo = listaDetalleTitulo;
    }

    public List<String> getListaTramite() {
        return listaTramite;
    }

    public void setListaTramite(List<String> listaTramite) {
        this.listaTramite = listaTramite;
    }

    public String getListaTipoCiudadNotificacion() {
        return listaTipoCiudadNotificacion;
    }

    public void setListaTipoCiudadNotificacion(String listaTipoCiudadNotificacion) {
        this.listaTipoCiudadNotificacion = listaTipoCiudadNotificacion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public List<String> getListaDeposito() {
        return listaDeposito;
    }

    public void setListaDeposito(List<String> listaDeposito) {
        this.listaDeposito = listaDeposito;
    }

    public void eliminaDeposito() {

    }

    public List<String> getListaTasa() {
        return listaTasa;
    }

    public void setListaTasa(List<String> listaTasa) {
        this.listaTasa = listaTasa;
    }

    public List<String> getListaRecibo() {
        return listaRecibo;
    }

    public void setListaRecibo(List<String> listaRecibo) {
        this.listaRecibo = listaRecibo;
    }

    public String getNumeroDeRecibo() {
        return numeroDeRecibo;
    }

    public void setNumeroDeRecibo(String numeroDeRecibo) {
        this.numeroDeRecibo = numeroDeRecibo;
    }

    public Boolean getRenderizaBotonesModificatoria() {
        return renderizaBotonesModificatoria;
    }

    public void setRenderizaBotonesModificatoria(Boolean renderizaBotonesModificatoria) {
        this.renderizaBotonesModificatoria = renderizaBotonesModificatoria;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public Date getFechaRecibo() {
        return fechaRecibo;
    }

    public void setFechaRecibo(Date fechaRecibo) {
        this.fechaRecibo = fechaRecibo;
    }

    public List<Recibo> getListaReciboGenerado() throws Exception {
//        Date fechaDia = comunService.obtenerFechaServidor(usuario.getIdregional());
//        return listaReciboGenerado = reciboService.listaReciboEmitidoPorFechas(fechaDia, usuario);
//        
        return listaReciboGenerado;
    }

    public void setListaReciboGenerado(List<Recibo> listaReciboGenerado) {
        this.listaReciboGenerado = listaReciboGenerado;
    }

    public Recibo getRecibo() {
        return recibo;
    }

    public void setRecibo(Recibo recibo) {
        this.recibo = recibo;
    }

    public ReciboDeposito getReciboDeposito() {
        return reciboDeposito;
    }

    public void setReciboDeposito(ReciboDeposito reciboDeposito) {
        this.reciboDeposito = reciboDeposito;
    }

    public List<ReciboDeposito> getLstReciboDeposito() {
        return lstReciboDeposito;
    }

    public void setLstReciboDeposito(List<ReciboDeposito> lstReciboDeposito) {
        this.lstReciboDeposito = lstReciboDeposito;
    }

    public String getCodigoTramite() {
        return codigoTramite;
    }

    public void setCodigoTramite(String codigoTramite) {
        this.codigoTramite = codigoTramite;
    }

    public SigSignoMarcaService getSigSignoMarcaService() {
        return sigSignoMarcaService;
    }

    public void setSigSignoMarcaService(SigSignoMarcaService sigSignoMarcaService) {
        this.sigSignoMarcaService = sigSignoMarcaService;
    }

    public String getNumeroDeFormulario() {
        return numeroDeFormulario;
    }

    public void setNumeroDeFormulario(String numeroDeFormulario) {
        this.numeroDeFormulario = numeroDeFormulario;
    }

    public ComunService getComunService() {
        return comunService;
    }

    public void setComunService(ComunService comunService) {
        this.comunService = comunService;
    }

    public String getTipoTramite() {
        return tipoTramite;
    }

    public void setTipoTramite(String tipoTramite) {
        this.tipoTramite = tipoTramite;
    }

    public String getMarcaTramite() {
        return marcaTramite;
    }

    public void setMarcaTramite(String marcaTramite) {
        this.marcaTramite = marcaTramite;
    }

    public SigSignoClaseNizaService getSigSignoClaseNizaService() {
        return sigSignoClaseNizaService;
    }

    public void setSigSignoClaseNizaService(SigSignoClaseNizaService sigSignoClaseNizaService) {
        this.sigSignoClaseNizaService = sigSignoClaseNizaService;
    }

    public Long getClaseNiza() {
        return claseNiza;
    }

    public void setClaseNiza(Long claseNiza) {
        this.claseNiza = claseNiza;
    }

    public String getGestionTramite() {
        return gestionTramite;
    }

    public void setGestionTramite(String gestionTramite) {
        this.gestionTramite = gestionTramite;
    }

    public RenSolicitudRenovacionService getRenSolicitudRenovacionService() {
        return renSolicitudRenovacionService;
    }

    public void setRenSolicitudRenovacionService(RenSolicitudRenovacionService renSolicitudRenovacionService) {
        this.renSolicitudRenovacionService = renSolicitudRenovacionService;
    }

    public ModModificacionService getModModificacionService() {
        return modModificacionService;
    }

    public void setModModificacionService(ModModificacionService modModificacionService) {
        this.modModificacionService = modModificacionService;
    }

    public List<Tasa> getLstTasa() throws Exception {
        return lstTasa;
    }

    public void setLstTasa(List<Tasa> lstTasa) {
        this.lstTasa = lstTasa;
    }

    public TasaService getTasaService() {
        return tasaService;
    }

    public void setTasaService(TasaService tasaService) {
        this.tasaService = tasaService;
    }

    public String getTipoTasa() {
        return tipoTasa;
    }

    public void setTipoTasa(String tipoTasa) {
        this.tipoTasa = tipoTasa;
    }

    public Long getCodigoTasa() {
        return codigoTasa;
    }

    public void setCodigoTasa(Long codigoTasa) {
        this.codigoTasa = codigoTasa;
    }

    public ReciboService getReciboService() {
        return reciboService;
    }

    public void setReciboService(ReciboService reciboService) {
        this.reciboService = reciboService;
    }

    public BigDecimal getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(BigDecimal montoTotal) {
        this.montoTotal = montoTotal;
    }

    public List<Deposito> getLstDesposito() {
//        return lstDesposito=depositoService.listaDepositoConSaldo();
        return lstDesposito;
    }

    public void setLstDesposito(List<Deposito> lstDesposito) {
        this.lstDesposito = lstDesposito;
    }

    public Deposito getDeposito() {
        return deposito;
    }

    public void setDeposito(Deposito deposito) {
        this.deposito = deposito;
    }

    public String getNumerosDepositos() {
        return numerosDepositos;
    }

    public void setNumerosDepositos(String numerosDepositos) {
        this.numerosDepositos = numerosDepositos;
    }

    public String getNombresDeposito() {
        return nombresDeposito;
    }

    public void setNombresDeposito(String nombresDeposito) {
        this.nombresDeposito = nombresDeposito;
    }

    public Date getFechasDeposito() {
        return fechasDeposito;
    }

    public void setFechasDeposito(Date fechasDeposito) {
        this.fechasDeposito = fechasDeposito;
    }

    public String getMontosDeposito() {
        return montosDeposito;
    }

    public void setMontosDeposito(String montosDeposito) {
        this.montosDeposito = montosDeposito;
    }

    public BigDecimal getSumaMontos() {
        return sumaMontos;
    }

    public void setSumaMontos(BigDecimal sumaMontos) {
        this.sumaMontos = sumaMontos;
    }

    public BigDecimal getSaldoTotal() {
        return saldoTotal;
    }

    public void setSaldoTotal(BigDecimal saldoTotal) {
        this.saldoTotal = saldoTotal;
    }

    public String getValorOpcionesRadioBanco() {
        return valorOpcionesRadioBanco;
    }

    public void setValorOpcionesRadioBanco(String valorOpcionesRadioBanco) {
        this.valorOpcionesRadioBanco = valorOpcionesRadioBanco;
    }

    public Deposito getDepositoSeleccionado() {
        return depositoSeleccionado;
    }

    public void setDepositoSeleccionado(Deposito depositoSeleccionado) {
        this.depositoSeleccionado = depositoSeleccionado;
    }

    public Deposito getDepositoGuardar() {
        return depositoGuardar;
    }

    public void setDepositoGuardar(Deposito depositoGuardar) {
        this.depositoGuardar = depositoGuardar;
    }

    public String getCodDepositante() {
        return codDepositante;
    }

    public void setCodDepositante(String codDepositante) {
        this.codDepositante = codDepositante;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Regional getRegional() {
        return regional;
    }

    public void setRegional(Regional regional) {
        this.regional = regional;
    }

    public Tasa getTasaRecibo() {
        return tasaRecibo;
    }

    public void setTasaRecibo(Tasa tasaRecibo) {
        this.tasaRecibo = tasaRecibo;
    }

    public Recibo getReciboElegido() {
        return reciboElegido;
    }

    public void setReciboElegido(Recibo reciboElegido) {
        this.reciboElegido = reciboElegido;
    }

    public DepositoService getDepositoService() {
        return depositoService;
    }

    public void setDepositoService(DepositoService depositoService) {
        this.depositoService = depositoService;
    }

    public String getSerieRecibo() {
        return serieRecibo;
    }

    public void setSerieRecibo(String serieRecibo) {
        this.serieRecibo = serieRecibo;
    }

    public String getSerieTitulo() {
        return serieTitulo;
    }

    public void setSerieTitulo(String serieTitulo) {
        this.serieTitulo = serieTitulo;
    }

    public int getNumeroTitulo() {
        return numeroTitulo;
    }

    public void setNumeroTitulo(int numeroTitulo) {
        this.numeroTitulo = numeroTitulo;
    }

    public String getApoderadoTramite() {
        return apoderadoTramite;
    }

    public void setApoderadoTramite(String apoderadoTramite) {
        this.apoderadoTramite = apoderadoTramite;
    }

    public String getSolicitanteTramite() {
        return solicitanteTramite;
    }

    public void setSolicitanteTramite(String solicitanteTramite) {
        this.solicitanteTramite = solicitanteTramite;
    }

    public SigSolicitanteApoderadoService getSigSolicitanteApoderadoService() {
        return sigSolicitanteApoderadoService;
    }

    public void setSigSolicitanteApoderadoService(SigSolicitanteApoderadoService sigSolicitanteApoderadoService) {
        this.sigSolicitanteApoderadoService = sigSolicitanteApoderadoService;
    }

    public String getDescripcionTramite() {
        return descripcionTramite;
    }

    public void setDescripcionTramite(String descripcionTramite) {
        this.descripcionTramite = descripcionTramite;
    }

    public String getNumeroExpediente() {
        return numeroExpediente;
    }

    public void setNumeroExpediente(String numeroExpediente) {
        this.numeroExpediente = numeroExpediente;
    }

    public RenSolicitanteApoderadoService getRenSolicitanteApoderadoService() {
        return renSolicitanteApoderadoService;
    }

    public void setRenSolicitanteApoderadoService(RenSolicitanteApoderadoService renSolicitanteApoderadoService) {
        this.renSolicitanteApoderadoService = renSolicitanteApoderadoService;
    }

    public ModSolicitanteApoderadoService getModSolicitanteApoderadoService() {
        return modSolicitanteApoderadoService;
    }

    public void setModSolicitanteApoderadoService(ModSolicitanteApoderadoService modSolicitanteApoderadoService) {
        this.modSolicitanteApoderadoService = modSolicitanteApoderadoService;
    }

    public ReciboDepositoService getReciboDepositoService() {
        return reciboDepositoService;
    }

    public void setReciboDepositoService(ReciboDepositoService reciboDepositoService) {
        this.reciboDepositoService = reciboDepositoService;
    }

    public String getCodigoExtension() {
        return codigoExtension;
    }

    public void setCodigoExtension(String codigoExtension) {
        this.codigoExtension = codigoExtension;
    }

    public Recibo getReciboEliminar() {
        return reciboEliminar;
    }

    public void setReciboEliminar(Recibo reciboEliminar) {
        this.reciboEliminar = reciboEliminar;
    }

    public Recibo getReciboAnular() {
        return reciboAnular;
    }

    public void setReciboAnular(Recibo reciboAnular) {
        this.reciboAnular = reciboAnular;
    }

    public Boolean getActivaRecibo() {
        return activaRecibo;
    }

    public void setActivaRecibo(Boolean activaRecibo) {
        this.activaRecibo = activaRecibo;
    }

    public Boolean getActivaCamposRecibo() {
        return activaCamposRecibo;
    }

    public void setActivaCamposRecibo(Boolean activaCamposRecibo) {
        this.activaCamposRecibo = activaCamposRecibo;
    }

    public Boolean getActivaTitulo() {
        return activaTitulo;
    }

    public void setActivaTitulo(Boolean activaTitulo) {
        this.activaTitulo = activaTitulo;
    }

    public Boolean getActivaCamposTitulo() {
        return activaCamposTitulo;
    }

    public void setActivaCamposTitulo(Boolean activaCamposTitulo) {
        this.activaCamposTitulo = activaCamposTitulo;
    }

    public Recibo getReciboReasignado() {
        return reciboReasignado;
    }

    public void setReciboReasignado(Recibo reciboReasignado) {
        this.reciboReasignado = reciboReasignado;
    }

    public Recibo getTituloReasignado() {
        return tituloReasignado;
    }

    public void setTituloReasignado(Recibo tituloReasignado) {
        this.tituloReasignado = tituloReasignado;
    }

    public UsuarioService getUsuarioService() {
        return usuarioService;
    }

    public void setUsuarioService(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    public Boolean getReport() {
        return report;
    }

    public Map<String, Object> getParametros() {
        return parametros;
    }

    public void setParametros(Map<String, Object> parametros) {
        this.parametros = parametros;
    }

    public void setReport(Boolean report) {
        this.report = report;
    }

    public Boolean getVariableRenderizaTramitesRegistrados() {
        return variableRenderizaTramitesRegistrados;
    }

    public Boolean getCargaTramitesNoRegistrados() {
        return cargaTramitesNoRegistrados;
    }

    public Boolean getActivaPanelesSiExisteTramite() {
        return activaPanelesSiExisteTramite;
    }

    public Boolean getActivaPanelesSiExisteTramiteNoEncontrado() {
        return activaPanelesSiExisteTramiteNoEncontrado;
    }

    public Boolean getReporteRecibo() {
        return reporteRecibo;
    }

    public Long getIdUsuarioSesion() {
        return idUsuarioSesion;
    }

    public void setIdUsuarioSesion(Long idUsuarioSesion) {
        this.idUsuarioSesion = idUsuarioSesion;
    }

    public String getMontoNumeralR() {
        return montoNumeralR;
    }

    public void setMontoNumeralR(String montoNumeralR) {
        this.montoNumeralR = montoNumeralR;
    }

    public String getNombreDepositanteR() {
        return nombreDepositanteR;
    }

    public void setNombreDepositanteR(String nombreDepositanteR) {
        this.nombreDepositanteR = nombreDepositanteR;
    }

    public String getMontoLiteralR() {
        return montoLiteralR;
    }

    public void setMontoLiteralR(String montoLiteralR) {
        this.montoLiteralR = montoLiteralR;
    }

    public String getConceptoR() {
        return conceptoR;
    }

    public void setConceptoR(String conceptoR) {
        this.conceptoR = conceptoR;
    }

    public String getTipoTramiteR() {
        return tipoTramiteR;
    }

    public void setTipoTramiteR(String tipoTramiteR) {
        this.tipoTramiteR = tipoTramiteR;
    }

    public String getDiaR() {
        return diaR;
    }

    public void setDiaR(String diaR) {
        this.diaR = diaR;
    }

    public String getMesR() {
        return mesR;
    }

    public void setMesR(String mesR) {
        this.mesR = mesR;
    }

    public String getAnioR() {
        return anioR;
    }

    public void setAnioR(String anioR) {
        this.anioR = anioR;
    }

    public String getImageFirmaR() {
        return imageFirmaR;
    }

    public void setImageFirmaR(String imageFirmaR) {
        this.imageFirmaR = imageFirmaR;
    }

    public String getNombreFirmaR() {
        return nombreFirmaR;
    }

    public void setNombreFirmaR(String nombreFirmaR) {
        this.nombreFirmaR = nombreFirmaR;
    }

    public String getSerieReciboR() {
        return serieReciboR;
    }

    public void setSerieReciboR(String serieReciboR) {
        this.serieReciboR = serieReciboR;
    }

    public String getNroReciboR() {
        return nroReciboR;
    }

    public void setNroReciboR(String nroReciboR) {
        this.nroReciboR = nroReciboR;
    }

    public List<Deposito> getLstDepositoR() {
        return lstDepositoR;
    }

    public void setLstDepositoR(List<Deposito> lstDepositoR) {
        this.lstDepositoR = lstDepositoR;
    }

    public List<Usuario> getListUsuario() {
        return listUsuario;
    }

    public void setListUsuario(List<Usuario> listUsuario) {
        this.listUsuario = listUsuario;
    }

    public String getValorRadio() {
        return valorRadio;
    }

    public void setValorRadio(String valorRadio) {
        this.valorRadio = valorRadio;
    }

    public Boolean getPanelCatalogoRendered() {
        return panelCatalogoRendered;
    }

    public void setPanelCatalogoRendered(Boolean panelCatalogoRendered) {
        this.panelCatalogoRendered = panelCatalogoRendered;
    }

    public Boolean getPanelVerRendered() {
        return panelVerRendered;
    }

    public void setPanelVerRendered(Boolean panelVerRendered) {
        this.panelVerRendered = panelVerRendered;
    }

    public RegionalService getRegionalService() {
        return regionalService;
    }

    public void setRegionalService(RegionalService regionalService) {
        this.regionalService = regionalService;
    }

    public List<Dominio> getLstTipoTramiteRecibo() throws Exception {
        try {
            return lstTipoTramiteRecibo = dominioService.obtenerListadoTipoTramite(EnumDominio.TIPO_TRAMITE_RECIBO.getCodigo());
        } catch (Exception e) {
            throw e;
        }
    }

    public void setLstTipoTramiteRecibo(List<Dominio> lstTipoTramiteRecibo) {
        this.lstTipoTramiteRecibo = lstTipoTramiteRecibo;
    }

    public DominioService getDominioService() {
        return dominioService;
    }

    public void setDominioService(DominioService dominioService) {
        this.dominioService = dominioService;
    }

    public String getTipoTramiteCombo() {
        return tipoTramiteCombo;
    }

    public void setTipoTramiteCombo(String tipoTramiteCombo) {
        this.tipoTramiteCombo = tipoTramiteCombo;
    }

    public Date getFechaBuscar() {
        return fechaBuscar;
    }

    public void setFechaBuscar(Date fechaBuscar) {
        this.fechaBuscar = fechaBuscar;
    }

    public String getTipoSucursalCombo() {
        return tipoSucursalCombo;
    }

    public void setTipoSucursalCombo(String tipoSucursalCombo) {
        this.tipoSucursalCombo = tipoSucursalCombo;
    }

    public List<Dominio> getLstSucursalBancaria() throws Exception {
        return lstSucursalBancaria = dominioService.obtenerListadoDominio(EnumDominio.SUCURSAL_BANCARIA.getCodigo());
    }

    public void setLstSucursalBancaria(List<Dominio> lstSucursalBancaria) {
        this.lstSucursalBancaria = lstSucursalBancaria;
    }

    public String getClaseNizaInt() {
        return claseNizaInt;
    }

    public void setClaseNizaInt(String claseNizaInt) {
        this.claseNizaInt = claseNizaInt;
    }

    public OposicionService getOposicionService() {
        return oposicionService;
    }

    public void setOposicionService(OposicionService oposicionService) {
        this.oposicionService = oposicionService;
    }


    public String getImgSenapi() {
        return imgSenapi;
    }

    public void setImgSenapi(String imgSenapi) {
        this.imgSenapi = imgSenapi;
    }

    public String getLugaryFecha() {
        return lugaryFecha;
    }

    public void setLugaryFecha(String lugaryFecha) {
        this.lugaryFecha = lugaryFecha;
    }

    public ClaseNizaService getClaseNizaService() {
        return claseNizaService;
    }

    public void setClaseNizaService(ClaseNizaService claseNizaService) {
        this.claseNizaService = claseNizaService;
    }
   
}
