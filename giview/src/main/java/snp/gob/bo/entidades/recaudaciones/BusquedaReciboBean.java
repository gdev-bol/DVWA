/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.entidades.recaudaciones;

import java.io.InputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.model.StreamedContent;
import snp.gob.bo.entidades.bean.common.AbstractManagedBean;
import snp.gob.bo.gimodel.entidad.Deposito;
import snp.gob.bo.gimodel.entidad.Dominio;
import snp.gob.bo.gimodel.entidad.Recibo;
import snp.gob.bo.gimodel.entidad.ReciboDeposito;
import snp.gob.bo.gimodel.entidad.Regional;
import snp.gob.bo.gimodel.entidad.Tasa;
import snp.gob.bo.gimodel.entidad.Usuario;
import snp.gob.bo.gimodel.enums.EnumDominio;
import snp.gob.bo.gimodel.servicio.ComunService;
import snp.gob.bo.gimodel.servicio.DepositoService;
import snp.gob.bo.gimodel.servicio.DominioService;
import snp.gob.bo.gimodel.servicio.ModModificacionService;
import snp.gob.bo.gimodel.servicio.ModSolicitanteApoderadoService;
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

@ManagedBean(name = "busquedaReciboBean")
@ViewScoped
public class BusquedaReciboBean extends AbstractManagedBean implements Serializable {

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
    @ManagedProperty("#{usuarioService}")
    private UsuarioService usuarioService;
    @ManagedProperty("#{regionalService}")
    private RegionalService regionalService;
    @ManagedProperty("#{dominioService}")
    private DominioService dominioService;

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
    private String listaTipoCiudadNotificacion;
    private List<String> listaDeRecibosAnulados;
    private List<String> listaDetalleTitulo;
    private List<String> listaTramite;
    private List<String> listaDeposito;
    private List<String> listaTasa;
    private List<String> listaRecibo;
    private String numeroDeRecibo;
    private List<Dominio> lstSucursalBancaria;
    private int cont = 0;
    private StreamedContent streamedContent1;
    private InputStream stream1;
    private String valor;
    private String gestionTramite;
    private Date fechaRecibo;
    private Long claseNiza;

    private String tipoSucursalCombo;

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
    private String valorOpcionesRadioBanco = "BSOL";
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
    private Long numeroReciboBuscar;
    private String serieReciboBuscar;
    private Recibo reciboBuscar;
    private Recibo reciboModificado;
    private Tasa tasaBuscar;
    private Deposito depositoModificar;

    public BusquedaReciboBean() {
        this.listaReciboGenerado = new ArrayList<Recibo>();
        this.lstDesposito = new ArrayList<Deposito>();
        this.lstReciboDeposito = new ArrayList<ReciboDeposito>();
        this.depositoModificar = new Deposito();
    }

    @PostConstruct
    public void initBusquedaReciboBean() {
        try {
            if (getIdUsuarioSession() != null) {
                usuario = usuarioService.buscaUsuarioPorIdUsuario(getIdUsuarioSession());
                regional = regionalService.obtenerRegionalPorIdRegiona(usuario.getIdregional());
            }

            if (getPaginaAnteriorDigitalizacion() != null) {
                renderizaBotonesModificatoria = verificaVariablePagina(getPaginaAnteriorDigitalizacion());
            }
//      inicializaValoresPrueba();
            cantidad = 1;
            activaBotonGeneraCostoTotal = true;
            reporteRecibo = false;
            fechaImpresion = new Date();
            Format formatterMes = new SimpleDateFormat("MM");
            Format formatterAnio = new SimpleDateFormat("yyyy");
            try {
            } catch (Exception ex) {
                Logger.getLogger(BusquedaReciboBean.class.getName()).log(Level.SEVERE, null, ex);
            }
            setPaginaAnteriorDigitalizacion(null);
            deposito = new Deposito();
            depositoGuardar = new Deposito();
            regional = new Regional();
            recibo = new Recibo();
//        reciboElegido=new Recibo();
            numeroTitulo = 0;
            numeroRecibo = 0;
            serieRecibo = null;
            serieTitulo = null;
            solicitanteTramite = "";
            apoderadoTramite = "";
            reciboBuscar = new Recibo();

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

    public void calculaMonto() throws Exception {
        montoTotal = new BigDecimal(cantidad).multiply(tasaService.obtenerTasaPorIdTasa(tasa).getCosto());
    }

    public void busca() throws Exception {
        if (numeroReciboBuscar != 0 && !serieReciboBuscar.equals(" ")) {
            reciboBuscar = reciboService.buscaReciboPorNumeroYSerieReciboSinEstado((numeroReciboBuscar).intValue(), serieReciboBuscar);
//            System.out.println("recibo buscar"+reciboBuscar.getIdRecibo());
            tasaBuscar = tasaService.obtenerTasaPorIdTasa(reciboBuscar.getIdTasa());
//            System.out.println("tasa"+tasaBuscar.getIdTasa());
            cantidad = reciboBuscar.getCantidad();
            lstTasa = tasaService.obtenerListaTasaActivas();
            tasa = tasaBuscar.getIdTasa();
            calculaMonto();
            lstDesposito = depositoService.depositosPorRecibo(reciboBuscar);

        } else {

        }

    }

    public void modificaReciboDeposito() throws Exception {
        try {
            System.out.println("id tasa" + tasaBuscar.getIdTasa());
            tasaBuscar = tasaService.obtenerTasaPorIdTasa(tasa);
            reciboBuscar = reciboService.modificaRecibo(reciboBuscar, lstDesposito, usuario, regional, cantidad, tasaBuscar);
            lstDesposito = depositoService.depositosPorRecibo(reciboBuscar);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "El recibo fue Modificado.", ""));
        } catch (Exception e) {
        }

    }

    public void cargaValoresDeposito() throws Exception {

        depositoModificar = depositoGuardar;
        //cargar lista de recibos que tienen el mismo deposito

        listaReciboGenerado = reciboService.listaReciboPorDeposito(depositoModificar.getIdDeposito());

    }

    public void cargaValoresDepositoListado(Deposito depo) throws Exception {
        System.out.println("cargo valores de deposito" + depo.getIdDeposito());
        depositoModificar = depo;
        //cargar lista de recibos que tienen el mismo deposito

        listaReciboGenerado = reciboService.listaReciboPorDeposito(depo.getIdDeposito());
        System.out.println("tamanio de la lista de recibos" + listaReciboGenerado.size());
    }

    public BigDecimal actulizaSaldoDeposito(Deposito depos) {
//        System.out.println("rec" + reciboBuscar.getIdRecibo());
//        System.out.println("dep" + depos.getIdDeposito());
        depos.setSaldo(depositoService.actualizaSaldoDepositoModificar(reciboBuscar, depos));
//        System.out.println("deposito monto "+ depos.getSaldo());
        return depos.getSaldo();

    }

    public void modificaDepositoOficial() throws Exception {
        //depositoModificar.setSaldo(depositoModificar.getMonto().subtract(depositoService.actualizaSaldoDepositoModificar(reciboBuscar, depositoModificar)));
        depositoModificar = depositoService.crudDeposito(depositoModificar, 2);
    }

    public void actualizaDepositosVista() {

        depositoGuardar = depositoModificar;
        System.out.println("se actualizo los datos del deposito");

    }

    public String muestraDescripcionTasa(Long Idtasa) throws Exception {

        return tasaService.obtenerTasaPorIdTasa(Idtasa).getDescripcion();
    }

    public void accion_buscarSolicitudFormulario() throws Exception {
        try {
            int sw = 0;
//            System.out.println("entr al metodo de busqueda");
            if (numeroReciboBuscar != 0 && !serieReciboBuscar.equals(" ")) {
//                System.out.println("recibo buscar" + numeroReciboBuscar + serieReciboBuscar + "/*/*/*/*/*/*/*/*");
                reciboBuscar = reciboService.buscaReciboPorNumeroYSerieRecibo((numeroReciboBuscar).intValue(), serieReciboBuscar);

                // sigsignomarca
//                if (sigSignoMarcaService.buscarSignoMarca_NumeroFormulario(numeroDeFormulario).getIdSignoMarca() != null) {
//                    codigoTramite = "SM";
//                    sw = 1;
//                    numeroExpediente = obtieneNumeroSM(sigSignoMarcaService.buscarSignoMarca_NumeroFormulario(numeroDeFormulario).getSm());
//                    codigoExtension = obtieneExtensionSM(sigSignoMarcaService.buscarSignoMarca_NumeroFormulario(numeroDeFormulario).getSm());
//                    claseNiza = sigSignoClaseNizaService.obtenerListaSigSignoClazeNizaXIdSignoMarca(sigSignoMarcaService.buscarSignoMarca_NumeroFormulario(numeroDeFormulario).getIdSignoMarca()).get(0).getNumeroClaseNiza();
//                    gestionTramite = obtieneGestion(sigSignoMarcaService.buscarSignoMarca_NumeroFormulario(numeroDeFormulario).getSm());
//                    marcaTramite = sigSignoMarcaService.buscarSignoMarca_NumeroFormulario(numeroDeFormulario).getSigno();
//                    tipoTramite = EnumTipoTramite.REGISTRO_MARCAS.getCodigo();
//                    List<SigSolicitanteApoderado> lstSolicitante = sigSolicitanteApoderadoService.listadoSolicitanteXidsignomarca(sigSignoMarcaService.buscarSignoMarca_NumeroFormulario(numeroDeFormulario).getIdSignoMarca());
//                    for (SigSolicitanteApoderado lst1 : lstSolicitante) {
//                        if (solicitanteTramite.equals("")) {
//                            solicitanteTramite = lst1.getNombreRazonSocial();
//                            if (lst1.getPrimerApellido() != null) {
//                                solicitanteTramite = solicitanteTramite + " " + lst1.getPrimerApellido();
//                            }
//                            if (lst1.getSegundoApellido() != null) {
//                                solicitanteTramite = solicitanteTramite + " " + lst1.getSegundoApellido();
//                            }
//                        } else {
//                            solicitanteTramite = "/" + solicitanteTramite + "" + lst1.getNombreRazonSocial();
//                            if (lst1.getPrimerApellido() != null) {
//                                solicitanteTramite = solicitanteTramite + " " + lst1.getPrimerApellido();
//                            }
//                            if (lst1.getSegundoApellido() != null) {
//                                solicitanteTramite = solicitanteTramite + " " + lst1.getSegundoApellido();
//                            }
//                        }
//                    }
//                    List<SigSolicitanteApoderado> listaApoderado = sigSolicitanteApoderadoService.listadoApoderadoXidsignomarca(sigSignoMarcaService.buscarSignoMarca_NumeroFormulario(numeroDeFormulario).getIdSignoMarca());
//                    for (SigSolicitanteApoderado lst1 : listaApoderado) {
//                        if (apoderadoTramite.equals("")) {
//                            apoderadoTramite = lst1.getNombreRazonSocial();
//                            if (lst1.getPrimerApellido() != null) {
//                                apoderadoTramite = apoderadoTramite + " " + lst1.getPrimerApellido();
//                            }
//                            if (lst1.getSegundoApellido() != null) {
//                                apoderadoTramite = apoderadoTramite + " " + lst1.getSegundoApellido();
//                            }
//                        } else {
//                            apoderadoTramite = "/" + apoderadoTramite + "" + lst1.getNombreRazonSocial();
//                            if (lst1.getPrimerApellido() != null) {
//                                apoderadoTramite = apoderadoTramite + " " + lst1.getPrimerApellido();
//                            }
//                            if (lst1.getSegundoApellido() != null) {
//                                apoderadoTramite = apoderadoTramite + " " + lst1.getSegundoApellido();
//                            }
//                        }
//                    }
//                } else {
//                    System.out.println("el numero no fue encontrado");
//                }
//                //renrenovacion
//                if (renSolicitudRenovacionService.buscarRenSolicitud_NumeroFormulario(numeroDeFormulario).getIdsolicitudrenovacion() != null) {
//                    sw = 1;
//                    codigoTramite = "SR" + "-" + renSolicitudRenovacionService.buscarRenSolicitud_NumeroFormulario(numeroDeFormulario).getSr().toString() + "-" + renSolicitudRenovacionService.buscarRenSolicitud_NumeroFormulario(numeroDeFormulario).getGestion_sr();
//                    claseNiza = new Long(renSolicitudRenovacionService.buscarRenSolicitud_NumeroFormulario(numeroDeFormulario).getClase_niza_registrado());
//                    gestionTramite = String.valueOf(renSolicitudRenovacionService.buscarRenSolicitud_NumeroFormulario(numeroDeFormulario).getGestion_sr());
//                    marcaTramite = renSolicitudRenovacionService.buscarRenSolicitud_NumeroFormulario(numeroDeFormulario).getSigno_registrado();
//                    tipoTramite = EnumTipoTramite.RENOVACION.getCodigo();
//                    //solicitante apoderado
//                    List<RenSolicitanteApoderado> lstSolicitanteRenovacion = renSolicitanteApoderadoService.buscaSolicitanteApoderadoPoridSolicitudyTipoPersona(renSolicitudRenovacionService.buscarRenSolicitud_NumeroFormulario(numeroDeFormulario).getIdsolicitudrenovacion(), EnumTipoPersona.SOLICITANTE.getCodigo());
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
//                            solicitanteTramite = "/" + solicitanteTramite + "" + lst1.getNombre_razonsocial();
//                            if (lst1.getPrimer_apellido() != null) {
//                                solicitanteTramite = solicitanteTramite + " " + lst1.getPrimer_apellido();
//                            }
//                            if (lst1.getSegundo_apellido() != null) {
//                                solicitanteTramite = solicitanteTramite + " " + lst1.getSegundo_apellido();
//                            }
//                        }
//                    }
//                    List<RenSolicitanteApoderado> listaApoderado = renSolicitanteApoderadoService.buscaSolicitanteApoderadoPoridSolicitudyTipoPersona(renSolicitudRenovacionService.buscarRenSolicitud_NumeroFormulario(numeroDeFormulario).getIdsolicitudrenovacion(), EnumTipoPersona.APODERADO.getCodigo());
//                    System.out.println("lista apoderado" + listaApoderado.size());
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
//                            apoderadoTramite = "/" + apoderadoTramite + "" + lst1.getNombre_razonsocial();
//                            if (lst1.getPrimer_apellido() != null) {
//                                apoderadoTramite = apoderadoTramite + " " + lst1.getPrimer_apellido();
//                            }
//                            if (lst1.getSegundo_apellido() != null) {
//                                apoderadoTramite = apoderadoTramite + " " + lst1.getSegundo_apellido();
//                            }
//                        }
//                    }
//
//                } else {
//                }
//                //modmodifacion
//                if (modModificacionService.buscarModModificacion_NumeroFormulario(numeroDeFormulario).getIdmodificacion() != null) {
//                    sw = 1;
//                    codigoTramite = modModificacionService.buscarModModificacion_NumeroFormulario(numeroDeFormulario).getSigla() + "-" + modModificacionService.buscarModModificacion_NumeroFormulario(numeroDeFormulario).getNumero().toString() + "-" + modModificacionService.buscarModModificacion_NumeroFormulario(numeroDeFormulario).getGestion();
//                    claseNiza = new Long(modModificacionService.buscarModModificacion_NumeroFormulario(numeroDeFormulario).getClase_registrado());
//                    gestionTramite = modModificacionService.buscarModModificacion_NumeroFormulario(numeroDeFormulario).getGestion().toString();
//                    marcaTramite = modModificacionService.buscarModModificacion_NumeroFormulario(numeroDeFormulario).getSigno_registrado();
//                    tipoTramite = modModificacionService.buscarModModificacion_NumeroFormulario(numeroDeFormulario).getSigla();
//                    List<ModSolicitanteApoderado> lstSolicitanteMod = modSolicitanteApoderadoService.listadoSolicitanteXidmodificacion(modModificacionService.buscarModModificacion_NumeroFormulario(numeroDeFormulario).getIdmodificacion());
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
//                            solicitanteTramite = "/" + solicitanteTramite + "" + lst1.getNombre_razonsocial();
//                            if (lst1.getPrimer_apellido() != null) {
//                                solicitanteTramite = solicitanteTramite + " " + lst1.getPrimer_apellido();
//                            }
//                            if (lst1.getSegundo_apellido() != null) {
//                                solicitanteTramite = solicitanteTramite + " " + lst1.getSegundo_apellido();
//                            }
//                        }
//                    }
//                    List<ModSolicitanteApoderado> listaApoderado = modSolicitanteApoderadoService.listadoApoderadoXidmodificacion(modModificacionService.buscarModModificacion_NumeroFormulario(numeroDeFormulario).getIdmodificacion());
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
//                            apoderadoTramite = "/" + apoderadoTramite + "" + lst1.getNombre_razonsocial();
//                            if (lst1.getPrimer_apellido() != null) {
//                                apoderadoTramite = apoderadoTramite + " " + lst1.getPrimer_apellido();
//                            }
//                            if (lst1.getSegundo_apellido() != null) {
//                                apoderadoTramite = apoderadoTramite + " " + lst1.getSegundo_apellido();
//                            }
//                        }
//                    }
//                } else {
//                }
//                if (sw == 0) {
//                    System.out.println("entro a inicializacion de variables");
//                    inicializaValoresVista();
//                }
//                System.out.println("tipotramite"+tipoTramite);
//                if (tipoTramite!=null) {
//                    String[] arraycodigo = tipoTramite.split("-");
//                    lstTasa = tasaService.obtenerListaTasaActivasPorTipoTramite(arraycodigo[0]);
//                } else {
//                    lstTasa = tasaService.obtenerListaTasaActivas();
//                }
//                tasa = lstTasa.get(0).getIdTasa();
//                calculaMonto();
//                numeroTitulo = 0;
//                numeroRecibo = 0;
//                serieRecibo = null;
//                serieTitulo = null;
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public void inicializaValoresVista() throws Exception {
        codigoTramite = numeroExpediente = gestionTramite = tipoTramite = marcaTramite = solicitanteTramite = apoderadoTramite = null;
        claseNiza = new Long(0);
        lstTasa = tasaService.obtenerListaTasaActivas();
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

    public void accion_GeneraRecibo() throws Exception {
        try {

            if (activaRecibo) {
                reciboReasignado = obtieneReciboReasignar(numeroRecibo, serieRecibo);
            }
            if (activaTitulo) {
                tituloReasignado = obtieneTituloReasignar(numeroTitulo, serieTitulo);
            }
            regional.setIdRegional(1L);
            recibo.setNumeroTramiteIngresado(numeroDeFormulario);
            recibo.setClaseTramite(claseNiza.intValue());
            recibo.setGestionTramiteIngresado(gestionTramite);
            recibo.setTipoTramiteIngresado(codigoTramite);
            recibo.setNumeroTramiteIngresado(numeroExpediente);
            recibo.setGestionTramite(new Long(gestionTramite));
            recibo.setApoderado(apoderadoTramite);
            recibo.setSolicitante(solicitanteTramite);
            recibo.setDescripcionMarca(marcaTramite);
            recibo.setTipoTramite(tipoTramite);
            reciboService.guardaReciboDepositoTramiteCantidad(recibo, tasaService.obtenerTasaPorIdTasa(tasa), lstDesposito, regional, usuario, cantidad, reciboReasignado, tituloReasignado, activaRecibo, activaTitulo);
            lstDesposito = depositoService.listaDepositoConSaldo();
            if (!lstDesposito.isEmpty()) {
                lstTasa = tasaService.obtenerListaTasaActivasPorTipoTramite(tipoTramite);
            } else {
                lstTasa = tasaService.obtenerListaTasaActivas();
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
        } catch (Exception e) {
            throw e;
        }
    }

    public void validaCamposRecibo() {

    }

    public Recibo obtieneReciboReasignar(int numeroRecibo, String serieRecibo) throws Exception {
        try {
            return reciboService.buscaReciboPorNumeroYSerieRecibo(numeroRecibo, serieRecibo);
        } catch (Exception e) {
            throw e;
        }
    }

    public Recibo obtieneTituloReasignar(int numeroTitulo, String serietitulo) throws Exception {
        try {
            return reciboService.buscaReciboPorNumeroYSerieTitulo(numeroTitulo, serieTitulo);
        } catch (Exception e) {
            throw e;
        }
    }

    public String muestraNumerosDeposito(Recibo recibo) throws Exception {
        return depositoService.encuentraDepositosPorRecibo(recibo);
    }

    public void modificaDeposito(Deposito depositoSeleccionado) throws Exception {
        depositoGuardar = depositoSeleccionado;
        tipoSucursalCombo = depositoGuardar.getDeposCodDep().toString();
//        System.out.println("deposito" + depositoSeleccionado.getNombreDepositante());
        lstDesposito.remove(depositoService.encuentraPosicionDeposito(deposito, lstDesposito));
    }

    public void eliminaDeposito(Deposito depositoSeleccionado) throws Exception {
//        System.out.println("lstDepsotio" + lstDesposito.size());
        depositoGuardar = depositoSeleccionado;
        lstDesposito.remove(depositoService.encuentraPosicionDeposito(depositoGuardar, lstDesposito));
    }

    public Boolean activaModificarEliminarDeposito(Deposito deposito) throws Exception {
        return depositoService.depositoParaModificarEliminar(deposito);
    }

    public void accion_adicionaDeposito() {
        depositoGuardar.setDeposCodDep(Integer.parseInt(tipoSucursalCombo));
        depositoGuardar.setNombreDepositante(depositoGuardar.getNombreDepositante().toUpperCase());
        lstDesposito.add(depositoGuardar);
        depositoGuardar = new Deposito();
//        for (Deposito depositos : lstDesposito) {
//            System.out.println("numero de deposito" + depositos.getNumeroDeposito());
//        }
    }

    public void eliminarReciboFinal() {
        try {
            cont = 0;
            reciboService.eliminaRecibo(reciboEliminar);
//            listaDeposito = depositoService.listaDepositoConSaldo();
//            lstDesposito = depositoService.listaDepositoConSaldoPorUSuario(usuario);
            lstDesposito = depositoService.listaDepositoConSaldo();
//            listaRecibo = reciboService.listaReciboEmitidoPorFechaPorUsuarioYRegional(new Date(), usuario, regional);
//            listaDeRecibosAnulados = reciboService.listaReciboEmitidoAnulado(usuario);
//            numeroRecibosModificados = reciboService.listaReciboEmitidoAnulado(usuario).size();
//            numeroTitulosAnulados = reciboService.listadetalleTituloAnuladosPoRecibo(usuario).size();
//            listaDetalleTitulo = reciboService.listadetalleTituloAnuladosPoRecibo(usuario);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void adicionaDeposito() {

    }

    public void eliminarReciboFinal(Recibo recibo) {

//        System.out.println("");
    }

    public void cargaValoresAEliminarRecibo(Recibo recibo) throws Exception {
//        System.out.println("ingreso a la consulta");
//        System.out.println("el id de recibo seleccionado" + recibo.getIdRecibo());
        reciboEliminar = reciboService.buscaReciboPorIdRecibo(recibo.getIdRecibo());
//        System.out.println("recibo a eliminar" + reciboEliminar.getIdRecibo());

    }

    public void cargaValoresAAnularRecibo(Recibo recibo) throws Exception {
//        System.out.println("ingreso a la anular");
//        System.out.println("el id de recibo seleccionado" + recibo.getIdRecibo());
        reciboAnular = reciboService.buscaReciboPorIdRecibo(recibo.getIdRecibo());
//        System.out.println("recibo a eliminar" + reciboEliminar.getIdRecibo());

    }

    public void anularReciboFinal() {
        try {
            cont = 0;
            reciboService.anularRecibo(reciboAnular);
//            listaDeposito = depositoService.listaDepositoConSaldo();
//            lstDesposito = depositoService.listaDepositoConSaldoPorUSuario(usuario);
            lstDesposito = depositoService.listaDepositoConSaldo();
//            listaRecibo = reciboService.listaReciboEmitidoPorFechaPorUsuarioYRegional(new Date(), usuario, regional);
//            listaDeRecibosAnulados = reciboService.listaReciboEmitidoAnulado(usuario);
//            numeroRecibosModificados = reciboService.listaReciboEmitidoAnulado(usuario).size();
//            numeroTitulosAnulados = reciboService.listadetalleTituloAnuladosPoRecibo(usuario).size();
//            listaDetalleTitulo = reciboService.listadetalleTituloAnuladosPoRecibo(usuario);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void activaCamposRecibo() {
//        System.out.println("ingreso al metodo");
        activaCamposRecibo = activaRecibo;
//        System.out.println("activa campos recibo" + activaCamposRecibo);
    }

    public void activaCamposTitulo() {
//        System.out.println("ingreso al metodo");
        activaCamposTitulo = activaTitulo;
//        System.out.println("activa campos recibo" + activaCamposTitulo);

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

    public List<Recibo> getListaReciboGenerado() {
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

    public Long getNumeroReciboBuscar() {
        return numeroReciboBuscar;
    }

    public void setNumeroReciboBuscar(Long numeroReciboBuscar) {
        this.numeroReciboBuscar = numeroReciboBuscar;
    }

    public String getSerieReciboBuscar() {
        return serieReciboBuscar;
    }

    public void setSerieReciboBuscar(String serieReciboBuscar) {
        this.serieReciboBuscar = serieReciboBuscar;
    }

    public Recibo getReciboBuscar() {
        return reciboBuscar;
    }

    public void setReciboBuscar(Recibo reciboBuscar) {
        this.reciboBuscar = reciboBuscar;
    }

    public String getTipoSucursalCombo() {
        return tipoSucursalCombo;
    }

    public void setTipoSucursalCombo(String tipoSucursalCombo) {
        this.tipoSucursalCombo = tipoSucursalCombo;
    }

    public Recibo getReciboModificado() {
        return reciboModificado;
    }

    public void setReciboModificado(Recibo reciboModificado) {
        this.reciboModificado = reciboModificado;
    }

    public Tasa getTasaBuscar() {
        return tasaBuscar;
    }

    public void setTasaBuscar(Tasa tasaBuscar) {
        this.tasaBuscar = tasaBuscar;
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

    public List<Dominio> getLstSucursalBancaria() throws Exception {
        return lstSucursalBancaria = dominioService.obtenerListadoDominio(EnumDominio.SUCURSAL_BANCARIA.getCodigo());
    }

    public void setLstSucursalBancaria(List<Dominio> lstSucursalBancaria) {
        this.lstSucursalBancaria = lstSucursalBancaria;
    }

    public DominioService getDominioService() {
        return dominioService;
    }

    public void setDominioService(DominioService dominioService) {
        this.dominioService = dominioService;
    }

    public Deposito getDepositoModificar() {
        return depositoModificar;
    }

    public void setDepositoModificar(Deposito depositoModificar) {
        this.depositoModificar = depositoModificar;
    }

}
