/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.entidades.bean.registro;

import static com.google.common.base.Charsets.ISO_8859_1;
import static com.google.common.base.Charsets.UTF_8;
import java.awt.Image;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
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
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.swing.ImageIcon;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import snp.gob.bo.entidades.bean.common.AbstractManagedBean;
import snp.gob.bo.gimodel.bdimagen.entidad.ImagenPojo;
import snp.gob.bo.gimodel.bdimagen.entidad.SigImagen;
import snp.gob.bo.gimodel.bdimagen.entidad.SigLogoTipo;
import snp.gob.bo.gimodel.bdimagen.servicio.ImagenPojoService;
import snp.gob.bo.gimodel.bdimagen.servicio.SigImagenService;
import snp.gob.bo.gimodel.bdimagen.servicio.SigLogoTipoService;
import snp.gob.bo.gimodel.entidad.Correlativo;
import snp.gob.bo.gimodel.entidad.Dominio;
import snp.gob.bo.gimodel.entidad.Etapa;
import snp.gob.bo.gimodel.entidad.FlujoTiempo;
import snp.gob.bo.gimodel.entidad.LogTrans;
import snp.gob.bo.gimodel.entidad.Seguimiento;
import snp.gob.bo.gimodel.entidad.SigDetallePublicacion;
import snp.gob.bo.gimodel.entidad.SigLemaComercial;
import snp.gob.bo.gimodel.entidad.SigPublicacion;
import snp.gob.bo.gimodel.entidad.SigRegistro;
import snp.gob.bo.gimodel.entidad.SigSignoClaseNiza;
import snp.gob.bo.gimodel.entidad.SigSignoMarca;
import snp.gob.bo.gimodel.entidad.SigTipoSigno;
import snp.gob.bo.gimodel.entidad.Usuario;
import snp.gob.bo.gimodel.enums.EnumCarpetasTemporales;
import snp.gob.bo.gimodel.enums.EnumEstado;
import snp.gob.bo.gimodel.enums.EnumNombreDominio;
import snp.gob.bo.gimodel.enums.EnumPrefijoTablas;
import snp.gob.bo.gimodel.enums.EnumTipoPersona;
import snp.gob.bo.gimodel.servicio.ComunService;
import snp.gob.bo.gimodel.servicio.CorrelativoService;
import snp.gob.bo.gimodel.servicio.DominioService;
import snp.gob.bo.gimodel.servicio.EtapaService;
import snp.gob.bo.gimodel.servicio.FlujoTiempoService;
import snp.gob.bo.gimodel.servicio.LogTransService;
import snp.gob.bo.gimodel.servicio.RegionalService;
import snp.gob.bo.gimodel.servicio.SeguimientoService;
import snp.gob.bo.gimodel.servicio.SigDetallePublicacionService;
import snp.gob.bo.gimodel.servicio.SigLemaComercialService;
import snp.gob.bo.gimodel.servicio.SigPublicacionService;
import snp.gob.bo.gimodel.servicio.SigRegistroService;
import snp.gob.bo.gimodel.servicio.SigSignoClaseNizaService;
import snp.gob.bo.gimodel.servicio.SigSignoMarcaService;
import snp.gob.bo.gimodel.servicio.SigSolicitanteApoderadoService;
import snp.gob.bo.gimodel.servicio.SigTipoSignoService;
import snp.gob.bo.gimodel.servicio.UsuarioService;
import snp.gob.bo.gimodel.servicio.impl.ModResolucionServiceImpl;

/**
 *
 * @author susana
 */
@ManagedBean(name = "registroBean")
@ViewScoped
public class RegistroBean extends AbstractManagedBean implements Serializable {

    /**
     * Creates a new instance of registroBean
     */
    private Boolean encabezado = false;
    private Boolean reasignar = false;
    private Long registro;
    private String serie = "C";
    private Long resolucion;
    private Integer gestion;
    private Date fechaRegistro;
    private String observaciones = "";
    private Date fechaPrimerUso;

    private String marca = "";
    private String tipoMarca = "";
    private Integer clase;
    private String titulares = "";
    private Long registroBuscar;
    private String serieBuscar = "";
    private Integer numero;
    private Long numeroSM;
    private Long gestionSM;
    private String extension = "";
    private String tipoSigno = "";

    private String valorFormato = "registro2011";
    private Boolean checkEncabezadoRender = true;
    private Boolean valorLabel = false;
    private List<Dominio> lstSerieRegistro;
    private SigRegistro sigregistro = new SigRegistro();
    private List<ImagenPojo> lstImagenPojos = new ArrayList<ImagenPojo>();
    private ImagenPojo imagenPojo = new ImagenPojo();

    private String background = "background-image:url(../resources/images/nuevoTitulo.jpg)";
    private SigSignoMarca encontradoSigSignoMarca;
    private String[] datosSolicitanteApoderado = new String[10];
    private String[] datosApoderado = new String[10];
    private String listaProductos = "";
    private Boolean btnAceptarDisabled = true;
    private String descripcionSM = "";
    private String numeroSMAux = "";
    private String gestionSMAux = "";
    private String extensionSM = "";
    private Date fechaIngreso;
    private Long numeroPublicacion;
    private Long numeroGaceta;
    private Date fechaPublicacion;
    private Boolean primerUsoRendered = false;
    private Boolean btnDenegarDisabled = true;
    private Boolean btnRehacerDisabled = true;
    private Boolean btnBorrarDisabled = true;
    private Boolean btnReasignarRendered = false;
    private Boolean chkReasiganrDisabled = true;
    private String fechaLiteral = "";
    private Correlativo correlativo = new Correlativo();
    private Correlativo correlativoResolucion = new Correlativo();
    private Boolean lblFechaRegistroRendered = false;
    private Boolean soloLectura = false;
    private Long registroAux;
    private Long resolucionAux;
    private Integer gestionAux;
    private Boolean[] validador = {false, false, false, false, false, false, false, true, true};
    private Date fechaServidor;
    private Boolean lblDenegadoRnedered = false;
    private Boolean swRehacer = false;
    private String serieAux;
    private SimpleDateFormat formateador = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", new Locale("es"));
    private Long registroBuscarAux;
    private String seriBuscarAux;
    private Long smAux;
    private List<SigSignoMarca> lstSigSignoMarca = new ArrayList<SigSignoMarca>();
    private String formato = "yyyy";
    private Boolean[] validadorRegistroResolucion = {false, false, false, false, false};
    private Boolean[] validadorSM = {false, false, false, false};
    private Long idUsuario;

    //parametros del reporte
    private Map<String, Object> parametrosR = new HashMap();
    private String smDescripcionR;
    private String fechaIngresoR;
    private String horaIngresoR;
    private Long numeroGacetaR;
    private String fechaPublicacionR;
    private String numeroResolucionR;
    private String fechaRegistroR;
    private String signoR;
    private String titularR;
    private String listaProductosR;
    private Integer claseR;
    private String direccionTitularR;
    private String numeroRegistroR;
    private String imgSenapiR;
    private boolean existeLogoR = false;
    private SigLogoTipo sigLogoTipo = new SigLogoTipo();
    private SigImagen sigImage = new SigImagen();
    private Image logoR = null;
    private String marcaAcompanaR;
    private String observacionesR;
    private String primerUso;
    private List<Usuario> listUsuario;
    private String usuario;

    private Map<String, Object> parametrosT = new HashMap();
    private String signoT;
    private String tipoSignoT;
    private String generoSignoT;
    private Integer clasesNizaT;
    private String registroT;
    private String resolucionT;
    private String titularT;
    private String fechaRegistroT;
    private Image fotoT = null;
    private Boolean tituloT = false;
    private Boolean imagenRendered = false;
    private List<SigTipoSigno> lstSigTipoSigno = new ArrayList<SigTipoSigno>();
    private SigSignoMarca encontradoSigSignoMarcaRepo = new SigSignoMarca();
    private SigRegistro sigRegistroRepo = new SigRegistro();
    private List<Dominio> listTipoGeneroPI100;
    private List<Dominio> listTipoGeneroPI101;
    private List<Dominio> listTipoGeneroPI102;
    private SigLemaComercial sigLemaComercial;

    @ManagedProperty("#{dominioService}")
    private DominioService dominioService;
    @ManagedProperty("#{sigRegistroService}")
    private SigRegistroService sigRegistroService;
    @ManagedProperty("#{imagenPojoService}")
    private ImagenPojoService imagenPojoService;
    @ManagedProperty("#{sigSignoMarcaService}")
    private SigSignoMarcaService sigSignoMarcaService;
    @ManagedProperty("#{comunService}")
    private ComunService comunService;
    @ManagedProperty("#{sigTipoSignoService}")
    private SigTipoSignoService sigTipoSignoService;
    @ManagedProperty("#{sigSignoClaseNizaService}")
    private SigSignoClaseNizaService sigSignoClaseNizaService;
    @ManagedProperty("#{sigDetallePublicacionService}")
    private SigDetallePublicacionService sigDetallePublicacionService;
    @ManagedProperty("#{sigPublicacionService}")
    private SigPublicacionService sigPublicacionService;
    @ManagedProperty("#{sigSolicitanteApoderadoService}")
    private SigSolicitanteApoderadoService sigSolicitanteApoderadoService;
    @ManagedProperty("#{correlativoService}")
    private CorrelativoService correlativoService;
    @ManagedProperty("#{sigLogoTipoService}")
    private SigLogoTipoService sigLogoTipoService;
    @ManagedProperty("#{sigImagenService}")
    private SigImagenService sigImagenService;
    @ManagedProperty("#{seguimientoService}")
    private SeguimientoService seguimientoService;
    @ManagedProperty("#{logTransService}")
    private LogTransService logTransService;
    @ManagedProperty("#{usuarioService}")
    private UsuarioService usuarioService;
    @ManagedProperty("#{sigLemaComercialService}")
    private SigLemaComercialService sigLemaComercialService;
    @ManagedProperty("#{etapaService}")
    private EtapaService etapaService;
    @ManagedProperty("#{flujoTiempoService}")
    private FlujoTiempoService flujoTiempoService;
    @ManagedProperty("#{regionalService}")
    private RegionalService regionalService;

    @PostConstruct
    public void RegistroBeanInit() {
        try {
            fechaServidor = comunService.obtenerFechaHoraServidor(1l);
            idUsuario = super.getIdUsuarioSession();
            listTipoGeneroPI100 = dominioService.ListaTipoTramiteReciboDominio("tipo_genero", "PI100");
            listTipoGeneroPI101 = dominioService.ListaTipoTramiteReciboDominio("tipo_genero", "PI101");
            listTipoGeneroPI102 = dominioService.ListaTipoTramiteReciboDominio("tipo_genero", "PI102");
            listUsuario = usuarioService.listaUsuarioXidPagina(super.getIdUsuarioSession());
        } catch (Exception ex) {
            Logger.getLogger(RegistroBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        reset();
    }

    /**
     * Metodo para habilitar y deshabilitar opciones para realizar accion
     * reasignar resolucion
     *
     * Creado: Susana Escobar Paz
     *
     * Fecha: 13/12/2016
     */
    public void addMessage() {
        try {
            String summary = reasignar ? "Reasignar habilitado" : "Reasignar deshabilitado";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(summary));

            if (reasignar) {
                registroAux = registro;
                resolucionAux = resolucion;
                gestionAux = gestion;

                lblFechaRegistroRendered = true;
                btnReasignarRendered = true;
                btnAceptarDisabled = true;
                soloLectura = true;

            } else {

                lblFechaRegistroRendered = false;
                btnReasignarRendered = false;
                btnAceptarDisabled = false;
                registro = registroAux;
                resolucion = resolucionAux;
                gestion = gestionAux;
                serie = "C";
            }
        } catch (Exception ex) {
            Logger.getLogger(RegistroBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Metodo validador para reasignar, verifica la existencia de los numeros de
     * registro y resolucion antes de asignar numeracion manual
     *
     * Creado: Susana Escobar Paz
     *
     * Fecha: 12/12/2016
     */
    public void validadorReasignar() {
        Boolean validadorRegistro[] = sigRegistroService.verificaRegistroResolucionExistente(registro, serie, resolucion, gestion);
        validadorRegistroResolucion = validadorRegistroRsolucion();
        if (validadorRegistroResolucion[4]) {
            if (!validadorRegistro[0] && !validadorRegistro[1]) {//ambos no existen
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Datos asignados", ""));
                btnAceptarDisabled = false;
                soloLectura = false;
            } else {
                if (validadorRegistro[0]) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El registro ya existe", ""));
                }
                if (validadorRegistro[1]) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "La resolucion ya existe", ""));
                }
                btnAceptarDisabled = true;
                soloLectura = true;
            }
        } else {
            muestraMensajesValidadorRegistroResolucion(validadorRegistroResolucion);
        }
    }

    public Boolean[] validadorRegistroRsolucion() {
        Boolean[] validadorR = {false, false, false, false, true};
        validadorR[0] = registro != null && registro != 0;
        validadorR[1] = resolucion != null && resolucion != 0;
        validadorR[2] = gestion != null && gestion != 0;
        validadorR[3] = fechaRegistro != null;
        validadorR[4] = validadorR[0] && validadorR[1] && validadorR[2] && validadorR[3];
        return validadorR;
    }

    public void muestraMensajesValidadorRegistroResolucion(Boolean[] validador) {
        if (validador[0] == false) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Escriba en numero de registro", ""));
        }
        if (validador[1] == false) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Escriba el numero de resolucion", ""));
        }
        if (validador[2] == false) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Escriba la gestion", ""));
        }
        if (validador[3] == false) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Seleccione la fecha", ""));
        }
    }

    /**
     * metodo para buscar SM pendiente de registro
     *
     * Creado: Susana Escobar Fecha: 07/11/2016
     *
     * @throws java.lang.Exception
     */
    public void buscarNuevoRegistro() throws Exception {
        int dias = 0;
        int tiempoOpo = 0;
        validadorSM = validadorSM();
        if (validadorSM[3]) {//&& gestionExpediente != null
            Long expediente = creaSM("SM", numeroSM, gestionSM, extension);
            encontradoSigSignoMarca = sigSignoMarcaService.lista_SigSignoMarca_SigRegistro(expediente);
            if (encontradoSigSignoMarca != null) {
                dias = seguimientoService.diasLaboralesSigno(encontradoSigSignoMarca.getFechaPublicacion(), fechaServidor, 1L);
                //System.out.println("dias contados " + dias);
                //Etapa etapaopo = etapaService.listar_etapa_tipoTramite_tipoEtapa("", "OPO");
                tiempoOpo = flujoTiempoService.obtieneFlujoXIdetapa("TOPO", 1);
//                if (tiempoOpo.getPlazo() != null) {
//                    tiempoOpo = etapaopo.getPlazo();
//                }
                //MEJORAR SACAR EL 30 DE LOS DIAS DE LA ETAPA
                if (dias >= tiempoOpo) {
                    inicializarRegistro(encontradoSigSignoMarca);
                    chkReasiganrDisabled = false;
                    swRehacer = false;
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "No se ha completado el tiempo para presentacion de oposiciones", ""));
                    reset();
                }

            } else {
                encontradoSigSignoMarca = sigSignoMarcaService.listaSigSignoMarcaXSM(expediente);
               
                if (encontradoSigSignoMarca != null) {
               
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El registro ya fue generado, abrir expediente", ""));
                    abrirExpedienteSignos();
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "La solicitud no existe", ""));
                }
                reset();
            }
        } else {
            //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Escriba el numero y gestion de SM", ""))
            muestraMensajesValidadorSM(validadorSM);
        }
    }

    public Boolean[] validadorSM() {

        Boolean[] validadorSMAux = {false, false, false, false};
        validadorSMAux[0] = numeroSM != null && numeroSM != 0;
        validadorSMAux[1] = gestionSM != null && gestionSM != 0;
        validadorSMAux[2] = !extension.equals(" ");

        validadorSMAux[3] = validadorSMAux[0] && validadorSMAux[1] && validadorSMAux[2];
        return validadorSMAux;
    }

    public void muestraMensajesValidadorSM(Boolean[] validador) {
        if (validador[0] == false) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Escriba en numero de SM", ""));
        }
        if (validador[1] == false) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Escriba la gestion del SM", ""));
        }
        if (validador[2] == false) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "La extension no puede ser un espacio vacio", ""));
        }
    }

    public String devuelveTipoGenero(String codigo) throws Exception {
        if (codigo != null && !codigo.equals("")) {
            return dominioService.obtenerListadoDominioXCodigo(EnumNombreDominio.TIPO_GENERO.getCodigo(), codigo).get(0).getNombre();
        }
        return "";
    }

    /**
     * metodo para mostrar o no la fecha de primer uso segun el tipo genero
     *
     * Creado: Susana Escobar Paz Fecha: 11/11/2016
     *
     * @param event
     */
    public void cambioDialogo(ActionEvent event) {
        //System.out.println("IMPORTANTE   "+tipoMarca);
        if (tipoMarca.equals("Nombre Comercial")
                || tipoMarca.equals("Rótulo Comercial")
                || tipoMarca.equals("Enseña Comercial")) {

            primerUsoRendered = true;
        }
    }

    /**
     * metodo mostrar en la vista datos del SM pendiente de registro que fue
     * buscado
     *
     * Creado Susana Escobar Paz Fecha: 11/11/2016 Modificado: 12/12/2016
     *
     * @param encontradoSigno
     * @throws java.lang.Exception
     */
    public void inicializarRegistro(SigSignoMarca encontradoSigno) throws Exception {

        registroBuscar = null;
        serieBuscar = "";
        marca = "";
        tipoSigno = "";
        tipoMarca = "";
        clase = null;
        titulares = "";
        encabezado = true;
        lstImagenPojos.clear();

        //cargar las imagenes de la marca        
        //encontradoSigSignoMarca = new SigSignoMarca();
        datosSolicitanteApoderado = new String[5];
        datosApoderado = new String[5];
        listaProductos = "";

        descripcionSM = "";
        numeroSMAux = "";
        gestionSMAux = "";
        extensionSM = "";
        fechaIngreso = null;
        numeroPublicacion = null;
        numeroGaceta = null;
        fechaPublicacion = null;
        primerUsoRendered = false;

        correlativo = correlativoService.encuentraCorrelativoTabla(1L, "NREG");
        registro = (long) correlativo.getUltimo_numero_asignado() + 1;
        serie = "C";

        try {
            fechaRegistro = comunService.obtenerFechaHoraServidor(1l);
        } catch (Exception ex) {
            Logger.getLogger(ModResolucionServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        correlativoResolucion = correlativoService.encuentraCorrelativoTabla(1L, "RREG");
        resolucion = (long) correlativoResolucion.getUltimo_numero_asignado() + 1;
        SimpleDateFormat dateFormat = new SimpleDateFormat(formato);

        gestion = Integer.parseInt(dateFormat.format(fechaRegistro));
        fechaRegistro = comunService.obtenerFechaServidor(1l);
        fechaLiteral = formateador.format(fechaRegistro);

        if (encontradoSigno.getSm() != null) {
            HashMap resultado = comunService.obtenerNumeroGestionExtensionCodigoSM(encontradoSigno.getSm());
            numeroSMAux = (resultado.get("numero").toString());
            gestionSMAux = (resultado.get("gestion").toString());
            extensionSM = resultado.get("extension").toString();
            descripcionSM = numeroSMAux + " - " + gestionSMAux;
            if (!extensionSM.equals("")) {
                descripcionSM = descripcionSM + " - " + extensionSM;
            }

        }

        fechaIngreso = encontradoSigno.getFechaIngreso();
        marca = encontradoSigno.getSigno();
        tipoSigno = sigTipoSignoService.lista_SigTipoSigno_concatenado(EnumPrefijoTablas.SIGNO.getCodigo(), encontradoSigno.getIdSignoMarca());

        tipoMarca = devuelveTipoGenero(encontradoSigno.getTipoGenero());
        List<SigSignoClaseNiza> listaclase = sigSignoClaseNizaService.obtenerListaSigSignoClazeNizaXIdSignoMarca(encontradoSigno.getIdSignoMarca());
        SigSignoClaseNiza sigSignoClaseNiza = listaclase.get(0);
        clase = sigSignoClaseNiza.getNumeroClaseNiza().intValue();
        listaProductos = sigSignoClaseNiza.getListaProducto();
        SigPublicacion publicacion = sigPublicacionService.listar_SigPublicacion_sm(encontradoSigno.getSm());
        SigDetallePublicacion detallePublicacion = sigDetallePublicacionService.listar_SigDetallePublicacion_sm(encontradoSigno.getSm());
        if (detallePublicacion != null) {
            numeroPublicacion = detallePublicacion.getNumero_publicacion();
        }
        if (publicacion != null) {
            fechaPublicacion = publicacion.getFecha_publicacion();
            numeroGaceta = publicacion.getNumero_gaceta();
        }

        datosSolicitanteApoderado = sigSolicitanteApoderadoService.datos_SigSolicitanteApoderado_concatenado(encontradoSigno.getIdSignoMarca(), EnumTipoPersona.SOLICITANTE.getCodigo());
        titulares = datosSolicitanteApoderado[0];

        //cargar las imagenes de la marca
        this.imagenPojo = imagenPojoService.obtenerImagePojoXSignoMarca(encontradoSigno.getIdSignoMarca());
        if (imagenPojo.getSigLogoTipo().getIdLogoTipo() != null) {
            //se debe subir la imagen en el servidor
            //enviar el imagenPojo a la vista, antes hacemos una previsualizacion

            this.lstImagenPojos.add(imagenPojo);

            cargarArchivoWAR();
        }
        imagenRendered = !sigTipoSignoService.preguntaEsDenominativo(encontradoSigno.getIdSignoMarca());
        //System.out.println("imagenRendered   " + imagenRendered);
        btnAceptarDisabled = false;
        //listaSigTipoSignoXidSIgnoMarca
    }

    public Boolean validadorSigRegistro() {
        //   System.out.println("fechaLiteral   "+fechaLiteral);
        //System.out.println("fechaRegistro  ###  " + fechaRegistro+"  sistema   "+fechaServidor);
        validador[8] = true;
        validador[0] = fechaRegistro.before(fechaServidor) || fechaRegistro.equals(fechaServidor);
        Boolean[] validaRegistroResolucion = sigRegistroService.verificaRegistroResolucionExistente(registro, serie, resolucion, gestion);
        validador[1] = !validaRegistroResolucion[0];
        validador[2] = !validaRegistroResolucion[1];
        //validador[3] = validador[0] && validador[1] && validador[2];
        validador[3] = fechaIngreso != null;
        validador[4] = numeroPublicacion != null;//numeroPublicacion != 0 && 
        validador[5] = numeroGaceta != null;//numeroGaceta != 0 && 
        validador[6] = fechaPublicacion != null;

        if (encontradoSigSignoMarca.getTipoGenero().equals("RC")
                || encontradoSigSignoMarca.getTipoGenero().equals("NC")
                || encontradoSigSignoMarca.getTipoGenero().equals("EC")) {
            if (fechaPrimerUso == null) {
                validador[7] = false;
            } else {
                validador[7] = true;
            }
        }
        for (int i = 0; i <= 7; i++) {
            //System.out.println("VALIDADOR PARA REGISTRAR  " +i+"....... "+ validador[i]);
            if (!validador[i]) {
                validador[8] = false;
            }
        }
        return validador[8];

    }

    /**
     * metodo encargado de crear el registro en tabla sigregistro, obtener los
     * correlativos para numero de registro y resolucion, actualizar valores en
     * el correlativo
     *
     * Creado: Susana Escobar Paz Fecha: 11/11/2016 Modificado: 12/12/2016
     *
     * @throws java.lang.Exception
     */
    public void asignarRegistro() throws Exception {
//        validar fecha antes
        if (validadorSigRegistro()) {
            sigregistro = new SigRegistro();
            sigregistro.setIdSignoMarca(encontradoSigSignoMarca.getIdSignoMarca());
            //List<Usuario> listUsuario = usuarioService.listaUsuarioXidPagina(idUsuario);
            LogTrans logTransGuardado = logTransService.crudLogTrans(new LogTrans(idUsuario, fechaServidor), 1);
            sigregistro.setIdLogTrans(logTransGuardado.getIdLogTrans());
            sigregistro.setNumeroRegistro(registro);
            if (correlativoResolucion.getAcronimo() != null && correlativoResolucion.getAcronimo().equals("")) {
                sigregistro.setSerie(correlativoResolucion.getAcronimo());
                
            } else {
                sigregistro.setSerie(serie);
            }
            sigregistro.setSm(encontradoSigSignoMarca.getSm());

            sigregistro.setSmDescripcion(descripcionSM);
            sigregistro.setEstadoRegistro("ACEP");
            sigregistro.setFechaIngreso(fechaIngreso);
            //sigregistro.setIdTipoResolucion(1);
            sigregistro.setDocumentoResolucion(null);
            sigregistro.setSigno(marca);
            sigregistro.setTipoSignoDescripcion(tipoSigno);
            sigregistro.setTipoGeneroDescripcion(tipoMarca);
            sigregistro.setClase(clase);
            sigregistro.setNumeroResolucion(resolucion);
            sigregistro.setGestionResolucion(gestion);
            sigregistro.setFechaRegistro(fechaRegistro);

            sigregistro.setNumeroPublicacion(numeroPublicacion);

            sigregistro.setFechaPublicacion(fechaPublicacion);
            sigregistro.setNumeroGaceta(numeroGaceta);

            sigregistro.setFechaPrimerUso(fechaPrimerUso);
            sigregistro.setTitular(titulares);
            sigregistro.setDireccionTitular(datosSolicitanteApoderado[1]);
            sigregistro.setPaisTitular(datosSolicitanteApoderado[2]);

            sigregistro.setPaisTitularDescripcion(datosSolicitanteApoderado[3]);
            datosApoderado = sigSolicitanteApoderadoService.datos_SigSolicitanteApoderado_concatenado(encontradoSigSignoMarca.getIdSignoMarca(), EnumTipoPersona.APODERADO.getCodigo());

            sigregistro.setNombreApoderado(datosApoderado[0]);
            sigregistro.setDireccionApoderado(datosApoderado[1]);
            sigregistro.setListaProductos(listaProductos);
            sigregistro.setObservacion(observaciones);

            sigregistro.setEstado(EnumEstado.ACTIVO.getCodigo());

            SigRegistro existente = sigRegistroService.listar_sigRegistro_idsignomarca(encontradoSigSignoMarca.getIdSignoMarca());

            if (existente != null) {
                existente.setEstado(EnumEstado.NO_ACTIVO.getCodigo());
                sigRegistroService.guardar_modificar_listar_SigRegistro(existente, 2);
            }
            SigRegistro creado = sigRegistroService.guardar_modificar_listar_SigRegistro(sigregistro, 1);
            sigregistro = creado;
            btnAceptarDisabled = true;

            if (reasignar || swRehacer) {
                System.out.println("no actualizart correlativo");
            } else {
                correlativo.setUltimo_numero_asignado(registro.intValue());
                correlativoService.crudCorrelativo(correlativo, 2);
                correlativoResolucion.setUltimo_numero_asignado(resolucion.intValue());
                correlativoService.crudCorrelativo(correlativoResolucion, 2);
            }

            encontradoSigSignoMarca.setNumeroRegistro(registro);
            encontradoSigSignoMarca.setSerieRegistro(serie);
            encontradoSigSignoMarca.setResolucionRegistro(resolucion.toString());
            encontradoSigSignoMarca.setFechaRegistro(fechaRegistro);
            //encontradoSigSignoMarca.setEstadoMarca("REG");
            encontradoSigSignoMarca.setEstadoMarca("PDE");
            encontradoSigSignoMarca.setUbicacion("PIDS");
            sigSignoMarcaService.crudSigSignoMarca(encontradoSigSignoMarca, 2);
            sigRegistroService.historial_SigRegistro_Signos(sigregistro, idUsuario, "PARA DESGLOSE", "DIRECCION DE PROPIEDAD INDUSTRIAL");
            sigRegistroService.seguimiento_SigRegistro_Signos(encontradoSigSignoMarca.getSm(), idUsuario);
            btnDenegarDisabled = false;
            btnRehacerDisabled = false;
            btnBorrarDisabled = false;
            chkReasiganrDisabled = true;
            btnReasignarRendered = false;
            reasignar = false;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Registro Generado Exitosamente"));
        } else {

            muestraMensajesValidadorSigRegistro(validador);
        }
    }

    public void muestraMensajesValidadorSigRegistro(Boolean[] validador) {
        if (validador[0] == false) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "La fecha no es correcta", ""));
        }
        if (validador[1] == false) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El numero de registro ya existe", ""));
        }
        if (validador[2] == false) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "La resolucion ya existe", ""));
        }
        if (validador[3] == false) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Escriba la fecha y hora de ingreso", ""));
        }
        if (validador[4] == false) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Escriba el numero de publicación", ""));
        }
        if (validador[5] == false) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Escriba el número de gaceta", ""));
        }
        if (validador[6] == false) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Escriba la fecha de publicación", ""));
        }
        if (validador[7] == false) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Escriba la fecha de primer uso", ""));
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

    /**
     * metodo que abre el dialogo seguimiento con las configuraciones y
     * variables enviadas
     *
     * Creado: Susana Escobar Paz Fecha: 01/11/2016
     *
     */
    public void abrirSeguimiento() {
        //if (sigregistro.getIdRegistro() != null) {
        if (encontradoSigSignoMarca.getIdSignoMarca() != null) {
            Map<String, Object> options = new HashMap<String, Object>();
            options.put("closable", true);
            options.put("resizable", false);
            options.put("height", 630);
            options.put("width", 1130);
            options.put("contentWidth", "100%");
            options.put("contentHeight", "100%");
            options.put("modal", true);
            Map<String, List<String>> var = new HashMap<String, List<String>>();
            List<String> params = new ArrayList();
            params.add("1");
            params.add(EnumPrefijoTablas.SIGNO.getCodigo());
            //params.add(sigregistro.getIdSignoMarca().toString());
            params.add(encontradoSigSignoMarca.getIdSignoMarca().toString());
            String ext = "";
            if (encontradoSigSignoMarca.getExtensionMarca() == 00) {
            } else {
                char ch = (char) (encontradoSigSignoMarca.getExtensionMarca() + 55);
                ext = "-" + ch;
            }
            Long idregional = regionalService.lista_IdRegional_TipoCiudadNotificacion(encontradoSigSignoMarca.getOficina());
            params.add("SM-" + String.format("%6s", encontradoSigSignoMarca.getNumero()).replace(' ', '0') + "-" + encontradoSigSignoMarca.getGestion() + ext);
            //params.add(serie);
            params.add(idregional.toString());
            var.put("datosEnviados", params);
            RequestContext.getCurrentInstance().openDialog("/seguimiento/controlPlazos.xhtml", options, var);
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Realice una busqueda previamente", ""));

        }
    }

    public void terminaDialogoSeguimiento(SelectEvent event) throws Exception {
        if (event.getObject() != "Exit") {
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cerrado"));
        }
    }

    /**
     * metodo que abre el dialogo historial con las configuraciones y variables
     * enviadas
     *
     * Creado: Susana Escobar Paz Fecha: 12/12/2016
     */
    public void abrirHistorial() {
        // if (sigregistro.getIdRegistro() != null) {
        if (encontradoSigSignoMarca.getIdSignoMarca() != null) {
            Map<String, Object> options = new HashMap<String, Object>();
            options.put("closable", true);
            options.put("resizable", false);
           options.put("height", 400);//tamaño del dialogo vertical del cuadro interno
            options.put("width", 900); //tammaño horizontal del dialogo adentro en el formulario
            options.put("contentWidth", "100%"); //MANTENER EL 100% se usa asi si diste x puntos dice cuantos de porcentaje de los xpuntos usaras
            options.put("contentHeight", "100%");//tamaño del dialogo vertical del cuadro externo
            options.put("modal", true);
            Map<String, List<String>> var = new HashMap<String, List<String>>();
            List<String> params = new ArrayList();
            //Enviar el id del objeto
            //params.add(sigregistro.getIdSignoMarca().toString());
            params.add(encontradoSigSignoMarca.getIdSignoMarca().toString());
            //en el caso de marcas enviar el tipo SIG
            params.add(EnumPrefijoTablas.SIGNO.getCodigo());
            //params.add("Enviar el SM EN STRING");
            var.put("datosEnviados", params);
            RequestContext.getCurrentInstance().openDialog("/historial/historialTramite.xhtml", options, var);
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Realice una busqueda previamente", ""));
        }

    }

    public void activaCambiosFormato() {

        switch (valorFormato) {
            case "registro2011":
                checkEncabezadoRender = true;
                valorLabel = false;
                background = "background-image:url(../resources/images/nuevoTitulo.jpg)";
                encabezado = true;
                break;
            case "registroNuevo":
                checkEncabezadoRender = false;
                valorLabel = false;
                background = "background-image:url(../resources/images/antiguo.jpg)";
                encabezado = false;
                break;
            case "registroAntiguo":
                checkEncabezadoRender = false;
                valorLabel = true;
                background = "";
                encabezado = false;
                break;

            default:
                checkEncabezadoRender = true;
                valorLabel = false;
                break;
        }
    }

    /**
     * metodo para buscar registro asignado anteriormente
     *
     * Creado: Susana Escobar Paz Fecha: 08/11/2016 Modificado: 12/12/2016
     *
     * @throws java.lang.Exception
     */
    public void buscadorRegistroExistente() throws Exception {
        lstSigSignoMarca = new ArrayList<SigSignoMarca>();
        sigregistro = new SigRegistro();
        imagenPojo = new ImagenPojo();
        lstImagenPojos.clear();
        sigregistro = sigRegistroService.listar_sigRegistro_numero(registroBuscar, serieBuscar);
        //   System.out.println("encontrado " + sigregistro);
        if (sigregistro != null) {
            marca = sigregistro.getSigno();
            tipoSigno = sigregistro.getTipoSignoDescripcion();
            tipoMarca = sigregistro.getTipoGeneroDescripcion();
            clase = sigregistro.getClase();
            registro = sigregistro.getNumeroRegistro();

            serie = sigregistro.getSerie();
            resolucion = sigregistro.getNumeroResolucion();
            gestion = sigregistro.getGestionResolucion();
            titulares = sigregistro.getTitular();
            fechaLiteral = formateador.format(sigregistro.getFechaRegistro());

            //cargar las imagenes de la marca
            this.imagenPojo = imagenPojoService.obtenerImagePojoXSignoMarca(sigregistro.getIdSignoMarca());
            if (imagenPojo.getSigLogoTipo().getIdLogoTipo() != null) {
                //se debe subir la imagen en el servidor
                //enviar el imagenPojo a la vista, antes hacemos una previsualizacion
                this.lstImagenPojos.add(imagenPojo);
                cargarArchivoWAR();
            }
            btnDenegarDisabled = false;
            btnRehacerDisabled = false;
            btnBorrarDisabled = false;
            chkReasiganrDisabled = true;
            lblFechaRegistroRendered = false;
            lblDenegadoRnedered = sigregistro.getEstadoRegistro().equals("DENR");
            HashMap resultado = comunService.obtenerNumeroGestionExtensionCodigoSM(sigregistro.getSm());
            numeroSM = Long.parseLong(resultado.get("numero").toString());
            gestionSM = Long.parseLong(resultado.get("gestion").toString());
            extension = resultado.get("extension").toString();
            //encontradoSigSignoMarca = sigSignoMarcaService.lista_SigSignoMarca_SigRegistro(sigregistro.getSm());
            encontradoSigSignoMarca = sigSignoMarcaService.listaSigSignoMarcaXSM(sigregistro.getSm());
            observaciones = sigregistro.getObservacion();
            imagenRendered = !sigTipoSignoService.preguntaEsDenominativo(encontradoSigSignoMarca.getIdSignoMarca());
            encabezado = true;
        } else {
            buscarSignos();
            if (lstSigSignoMarca.isEmpty()) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El registro no existe", ""));
            } else {

            }
        }
        //observaciones =  sigregistro
    }

    public void buscarUltimo() throws Exception {
        registroBuscar = sigRegistroService.lista_UltimoRegistro_gestion();
        buscadorRegistroExistente();
    }

    /**
     * Metodo que abre el dialogo expediente de signos con las configuraciones y
     * variables enviadas
     *
     * Creado: Susana Escobar Paz Fecha: 12/12/2016
     */
    public void abrirExpedienteSignos() {

        if (encontradoSigSignoMarca.getIdSignoMarca() != null) {
            setIdSignoSession(encontradoSigSignoMarca.getIdSignoMarca());
            setNavegaPagina("template");
            Map<String, Object> options = new HashMap<String, Object>();
            options.put("closable", true);
            options.put("resizable", false);
            options.put("height", 700);
            options.put("width", 1220);
            options.put("contentWidth", "100%");
            options.put("contentHeight", "100%");
            options.put("modal", true);
            RequestContext.getCurrentInstance().openDialog("examenSignos", options, null);
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Debe realizar una busqueda", ""));
        }
    }

    public void buscarSignos() {
        lstSigSignoMarca = sigSignoMarcaService.listar_SigSignoMarca_registro(registroBuscar, serieBuscar);
    }

    /**
     * Metodo para generar datos de registro no generados automaticamente
     *
     * Creado: Susana Escobar Paz Fecha: 08/11/2016 Modificado: 12/12/2016
     *
     * @throws java.lang.Exception
     */
    public void generarNoExistente(SigSignoMarca signo) throws Exception {
        encontradoSigSignoMarca = signo;
        inicializarRegistro(encontradoSigSignoMarca);
        chkReasiganrDisabled = false;
        registro = signo.getNumeroRegistro();
        resolucion = Long.parseLong(signo.getResolucionRegistro());
        serie = signo.getSerieRegistro();
        fechaRegistro = signo.getFechaRegistro();
        fechaLiteral = formateador.format(fechaRegistro);
        SimpleDateFormat dateFormat = new SimpleDateFormat(formato);
        gestion = Integer.parseInt(dateFormat.format(fechaRegistro));

        lstSigSignoMarca = new ArrayList<SigSignoMarca>();
        HashMap resultado = comunService.obtenerNumeroGestionExtensionCodigoSM(encontradoSigSignoMarca.getSm());
        numeroSM = Long.parseLong(resultado.get("numero").toString());
        gestionSM = Long.parseLong(resultado.get("gestion").toString());
        extension = resultado.get("extension").toString();
        swRehacer = true;

    }

    public void activaBotones(String estado) {
        if (estado.equals("ACEP")) {
            btnDenegarDisabled = false;
            btnRehacerDisabled = false;
            btnBorrarDisabled = false;
            chkReasiganrDisabled = true;
        }
    }

    public void cargarArchivoWAR() {

        FacesContext facesContext = FacesContext.getCurrentInstance();
        ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();
        String rutaWar = servletContext.getRealPath(EnumCarpetasTemporales.CARPETA_IMAGENES.getCodigo());

        File targetFolder = new File(rutaWar);

        if (!targetFolder.exists()) {
            targetFolder.mkdirs();
        }

        byte[] fotoByte = this.lstImagenPojos.get(0).getSigImagen().getImagen();

        imagenPojo = new ImagenPojo();
        //    imagenPojo = lstImagenPojos.get(0);
        String imagenDibuja = rutaWar + File.separator + this.lstImagenPojos.get(0).getSigLogoTipo().getNombreArchivo();
        //enviar el imagenPojo a la vista, antes hacemos una previsualizacion
        crearArchivo(fotoByte, imagenDibuja);

    }

    private void crearArchivo(byte[] bytes, String archivo) {
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(archivo);
            fos.write(bytes);

            fos.flush();
            fos.close();

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodo para inicializar el formualrio
     *
     * Creado: Susana Escobar Paz Fecha: 01/11/2016 Modificado: 12/12/2016
     *
     * @return
     */
    public String reset() {
        sigregistro = new SigRegistro();
        registroBuscar = null;
        serieBuscar = "";
        marca = "";
        tipoSigno = "";
        tipoMarca = "";
        clase = null;
        registro = null;
        serie = "";
        resolucion = null;
        gestion = null;
        titulares = "";
        imagenPojo = new ImagenPojo();
        lstImagenPojos.clear();

        //cargar las imagenes de la marca        
        encontradoSigSignoMarca = new SigSignoMarca();
        datosSolicitanteApoderado = new String[5];
        datosApoderado = new String[5];
        listaProductos = "";
        btnAceptarDisabled = true;
        descripcionSM = "";
        numeroSMAux = "";
        gestionSMAux = "";
        extensionSM = "";
        fechaIngreso = null;
        numeroPublicacion = null;
        numeroGaceta = null;
        fechaPublicacion = null;
        primerUsoRendered = false;

        numero = null;
        numeroSM = null;
        gestionSM = null;
        extension = "";
        btnDenegarDisabled = true;
        btnRehacerDisabled = true;
        btnBorrarDisabled = true;
        chkReasiganrDisabled = true;
        lblFechaRegistroRendered = false;
        lblDenegadoRnedered = false;
        observaciones = null;
        imagenRendered = false;
        lstSigTipoSigno = new ArrayList<SigTipoSigno>();
        lstSigSignoMarca = new ArrayList<SigSignoMarca>();
        return "registroNuevoAntiguo";
    }

    /**
     * metodo que abre el dialogo buscador de registros con las configuraciones
     * y variables enviadas
     *
     * Creado: Susana Escobar Paz Fecha: 12/12/2016
     */
    public void abrirListaBuscador() {
        Map<String, Object> options = new HashMap<String, Object>();
        options.put("closable", false);
        options.put("resizable", false);
        options.put("height", 630);
        options.put("width", 1110);
        options.put("contentWidth", "100%");
        options.put("contentHeight", "100%");
        options.put("modal", true);
        Map<String, List<String>> params = new HashMap<String, List<String>>();
        List<String> list = new ArrayList<String>();
        list.add("registroNuevoAntiguo");
        params.put("valor", list);
        RequestContext.getCurrentInstance().openDialog("buscadorRegistro", options, params);
    }

    /**
     * Metodo que actualiza un registro a estado Denegado
     *
     * Creado: Susana Escobar Paz Fecha: 12/12/2016
     *
     * @throws java.lang.Exception
     */
    public void accionDenegar() throws Exception {
        if (sigregistro != null) {

            sigregistro.setEstadoRegistro("DENR");
            SigRegistro modificado = sigRegistroService.guardar_modificar_listar_SigRegistro(sigregistro, 2);
            sigregistro = modificado;
            encontradoSigSignoMarca = sigSignoMarcaService.listaSigSignoMarcaXSM(sigregistro.getSm());
            encontradoSigSignoMarca.setEstadoMarca("DENE");
            encontradoSigSignoMarca.setUbicacion("AA");

            sigSignoMarcaService.crudSigSignoMarca(encontradoSigSignoMarca, 2);
            lblDenegadoRnedered = true;
            //AQUI CREAR EL HISTORIAL PARA REGISTRAR, DENEGAR Y PARA BORRAR ,DEBBEN MOSTRARSE EN SIGNOS
            sigRegistroService.historial_SigRegistro_Signos(sigregistro, 1l, "DENEGADA", "ARCHIVO (ABANDONADA)");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "El registro fue DENEGADO", ""));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "No existe el registro", ""));
        }
    }

    /**
     * Metodo que cambia de estado activo, a no activo el registro seleccionado
     *
     * Creado: Susana Escobar Paz Fecha: 12/12/2016
     *
     * @throws java.lang.Exception
     */
    public void accionBorrar() throws Exception {
        if (sigregistro != null) {
            sigregistro.setEstado(EnumEstado.NO_ACTIVO.getCodigo());
            SigRegistro modificado = sigRegistroService.guardar_modificar_listar_SigRegistro(sigregistro, 2);
            sigregistro = modificado;
            encontradoSigSignoMarca = sigSignoMarcaService.listaSigSignoMarcaXSM(sigregistro.getSm());
            encontradoSigSignoMarca.setEstadoMarca("P");
            encontradoSigSignoMarca.setUbicacion("PIAR");
            encontradoSigSignoMarca.setNumeroRegistro(0l);
            encontradoSigSignoMarca.setSerieRegistro(null);
            encontradoSigSignoMarca.setResolucionRegistro(null);
            encontradoSigSignoMarca.setFechaRegistro(null);

            sigRegistroService.historial_SigRegistro_Signos(sigregistro, idUsuario, "ELIMINACION", "");
            sigSignoMarcaService.crudSigSignoMarca(encontradoSigSignoMarca, 2);
            Seguimiento seguimiento = seguimientoService.ultimo_Seguimiento_etapa(EnumPrefijoTablas.SIGNO.getCodigo(), encontradoSigSignoMarca.getIdSignoMarca(), "ERA");
            seguimiento.setEstado(EnumEstado.NO_ACTIVO.getCodigo());

            seguimientoService.guardar_modificar_listar_Seguimiento(seguimiento, EnumPrefijoTablas.SIGNO.getCodigo(), 2);
            lblDenegadoRnedered = true;
            //AQUI CREAR EL HISTORIAL PARA REGISTRAR, DENEGAR Y PARA BORRAR ,DEBBEN MOSTRARSE EN SIGNOS
            reset();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "El registro fue BORRADO", ""));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "No existe el registro", ""));
        }
    }

    /**
     * Metodo que cambia de estado activo, a no activo el regisdtro seleccionado
     * seguidamente genera nuevamente los datos manteniendo el numero de
     * registro en el momento.
     *
     * Creado: Susana Escobar Paz Fecha: 12/12/2016
     *
     * @throws java.lang.Exception
     */
    public void accionRehacer() throws Exception {
        if (sigregistro != null) {
            registroAux = sigregistro.getNumeroRegistro();
            resolucionAux = sigregistro.getNumeroResolucion();
            gestionAux = sigregistro.getGestionResolucion();
            serieAux = sigregistro.getSerie();
            smAux = sigregistro.getSm();
            Date fechaRegistroAux = sigregistro.getFechaRegistro();
            accionBorrar();
            encontradoSigSignoMarca = sigSignoMarcaService.lista_SigSignoMarca_SigRegistro(smAux);
            if (encontradoSigSignoMarca != null) {
                inicializarRegistro(encontradoSigSignoMarca);
                chkReasiganrDisabled = false;
                registro = registroAux;
                resolucion = resolucionAux;
                gestion = gestionAux;
                serie = serieAux;
                fechaLiteral = formateador.format(fechaRegistroAux);
                fechaRegistro = fechaRegistroAux;
                swRehacer = true;
                HashMap resultado = comunService.obtenerNumeroGestionExtensionCodigoSM(encontradoSigSignoMarca.getSm());
                numeroSM = Long.parseLong(resultado.get("numero").toString());
                gestionSM = Long.parseLong(resultado.get("gestion").toString());
                extension = resultado.get("extension").toString();
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El registro ya fue generado", ""));
                reset();
            }

            //REVISAR SI SE BORRA SOLO ANTES DEL REGISTRO O TAMBIEN DESPUES REVISAR
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Se generaron nuevamente los datos, con el mismo numero de registro", ""));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "No existe el registro", ""));
        }
    }

    public void accionAnterior() throws Exception {
        if (registroBuscar != null) {
            registroBuscarAux = registroBuscar;
            seriBuscarAux = serieBuscar;
            reset();
            registroBuscar = registroBuscarAux - 1;
            serieBuscar = seriBuscarAux;
            buscadorRegistroExistente();
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Escriba un numero de registro a buscar", ""));
        }
    }

    public void accionSiguiente() throws Exception {
        if (registroBuscar != null) {
            registroBuscarAux = registroBuscar;
            seriBuscarAux = serieBuscar;
            reset();
            registroBuscar = registroBuscarAux + 1;
            serieBuscar = seriBuscarAux;
            buscadorRegistroExistente();
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Escriba un numero de registro a buscar", ""));
        }
    }

    public void terminaDialogoBuscar(SelectEvent event) {
        if (event.getObject() != "Exit") {
        } else {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Cerrado", ""));
        }
    }

    /**
     * Metodo para inicializar el reporte
     *
     * Creado: Ruben Ramirez Fecha: 28/12/2016
     */
    public void limpiar() {
        smDescripcionR = "";
        fechaIngresoR = "";
        horaIngresoR = "";
        numeroGacetaR = 0L;
        numeroResolucionR = "";
        fechaRegistroR = "";
        signoR = "";
        titularR = "";
        listaProductosR = "";
        claseR = 0;
        direccionTitularR = "";
        numeroRegistroR = "";
        imgSenapiR = "";
        existeLogoR = false;
        logoR = null;
        marcaAcompanaR = "";
        signoT = "";
        tipoSignoT = "";
        generoSignoT = "";
        clasesNizaT = 0;
        registroT = "";
        resolucionT = "";
        titularT = "";
        fechaRegistroT = "";
        fotoT = null;
        tituloT = false;
    }

    public void cambia() throws UnsupportedEncodingException {
        System.out.println("GUARDADO     " + observaciones);
        byte ptext[] = observaciones.getBytes(ISO_8859_1);
        String value = new String(ptext, UTF_8);
        System.out.println("CODIFICADOOO    " + value);
        sigregistro.setObservacion(value);
        sigRegistroService.guardar_modificar_listar_SigRegistro(sigregistro, 2);
    }

    /**
     * Metodo para llenar los datos del reporte de resoluciones y su generacion
     * en formato pdf
     *
     * Creado: Ruben Ramirez Fecha: 28/12/2016
     *
     * @throws net.sf.jasperreports.engine.JRException
     * @throws java.io.IOException
     */
    public void imprimirResolucion() throws JRException, IOException, Exception {

        if (sigregistro.getIdRegistro() != null) {

            byte ptext[] = observaciones.getBytes(ISO_8859_1);
            String value = new String(ptext, UTF_8);
            sigregistro.setObservacion(value);
            sigRegistroService.guardar_modificar_listar_SigRegistro(sigregistro, 2);
            sigRegistroRepo = sigRegistroService.listar_sigRegistro_numero(sigregistro.getNumeroRegistro(), sigregistro.getSerie());
            if (sigRegistroRepo.getIdRegistro() != null) {
                encontradoSigSignoMarcaRepo = sigSignoMarcaService.listaSigSignoMarcaXSM(sigRegistroRepo.getSm());
                if (encontradoSigSignoMarcaRepo.getIdSignoMarca() != null) {

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

                    SimpleDateFormat formateador1 = new SimpleDateFormat("dd/MM/yyyy");
                    SimpleDateFormat formateador2 = new SimpleDateFormat("HH:mm:ss");

                    Boolean tipoGeneroPI100 = false;
                    for (Dominio item : listTipoGeneroPI100) {
                        if (item.getCodigo().equals(encontradoSigSignoMarcaRepo.getTipoGenero())) {
                            tipoGeneroPI100 = true;
                            break;
                        }
                    }

                    Boolean tipoGeneroPI101 = false;
                    for (Dominio item : listTipoGeneroPI101) {
                        if (item.getCodigo().equals(encontradoSigSignoMarcaRepo.getTipoGenero())) {
                            tipoGeneroPI101 = true;
                            break;
                        }
                    }

                    Boolean tipoGeneroPI102 = false;
                    for (Dominio item : listTipoGeneroPI102) {
                        if (item.getCodigo().equals(encontradoSigSignoMarcaRepo.getTipoGenero())) {
                            tipoGeneroPI102 = true;
                            break;
                        }
                    }

                    imgSenapiR = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/images/logoNuevo.jpg");

                    smDescripcionR = sigRegistroRepo.getSmDescripcion();

                    if (sigRegistroRepo.getFechaIngreso() != null) {
                        fechaIngresoR = formateador1.format(sigRegistroRepo.getFechaIngreso());
                        horaIngresoR = formateador2.format(sigRegistroRepo.getFechaIngreso());
                    }

                    numeroGacetaR = sigRegistroRepo.getNumeroGaceta();

                    if (sigRegistroRepo.getFechaPublicacion() != null) {
                        fechaPublicacionR = formateador1.format(sigRegistroRepo.getFechaPublicacion());
                    }

                    numeroResolucionR = "" + sigRegistroRepo.getNumeroResolucion() + "/" + sigRegistroRepo.getGestionResolucion();

                    if (sigRegistroRepo.getFechaRegistro() != null) {
                        fechaRegistroR = formateador1.format(sigRegistroRepo.getFechaRegistro());
                    }

                    if (tipoGeneroPI101) {
                        signoR = "<style isBold='true'>" + sigRegistroRepo.getTipoGeneroDescripcion() + ": </style>" + sigRegistroRepo.getSigno() + " (" + sigRegistroRepo.getTipoSignoDescripcion() + ")";
                    } else {
                        signoR = sigRegistroRepo.getSigno() + " (" + sigRegistroRepo.getTipoSignoDescripcion() + ")";
                    }
                    titularR = sigRegistroRepo.getTitular();

                    String listaProductos = "";

                    if (sigRegistroRepo.getListaProductos() != null) {
                        if (!sigRegistroRepo.getListaProductos().trim().isEmpty()) {
                            listaProductos = "\"" + sigRegistroRepo.getListaProductos() + "\"";
                        }
                    }

                    if (tipoGeneroPI101) {
                        listaProductosR = "Para Distinguir las siguientes actividades comerciales: " + listaProductos;
                        if (sigRegistroRepo.getFechaPrimerUso() != null) {
                            primerUso = formateador1.format(sigRegistroRepo.getFechaPrimerUso());
                        } else {
                            primerUso = null;
                        }
                    } else {
                        listaProductosR = "Para Distinguir: " + listaProductos;
                    }

                    claseR = sigRegistroRepo.getClase();

                    direccionTitularR = sigRegistroRepo.getDireccionTitular();

                    numeroRegistroR = "" + sigRegistroRepo.getNumeroRegistro() + " - " + sigRegistroRepo.getSerie();

                    if (sigRegistroRepo.getObservacion() != null) {
                        if (!sigRegistroRepo.getObservacion().trim().isEmpty()) {
                            observacionesR = sigRegistroRepo.getObservacion();
                        } else {
                            observacionesR = null;
                        }
                    } else {
                        observacionesR = null;
                    }

                    if (tipoGeneroPI102) {
                        sigLemaComercial = sigLemaComercialService.obtenerSigLemaComercial(encontradoSigSignoMarcaRepo.getIdSignoMarca());
                        if (sigLemaComercial != null) {
                            if (sigLemaComercial.getNumeroRegistroPadre() != null) {
                                if (sigLemaComercial.getSerieRegistroPadre() != null && !sigLemaComercial.getSerieRegistroPadre().equals("")) {
                                    marcaAcompanaR = sigLemaComercial.getSignoPadre() + " (" + sigLemaComercial.getNumeroRegistroPadre() + "-" + sigLemaComercial.getSerieRegistroPadre() + ")";
                                } else {
                                    marcaAcompanaR = sigLemaComercial.getSignoPadre() + " (" + sigLemaComercial.getNumeroRegistroPadre() + ")";
                                }
                            } else {
                                marcaAcompanaR = sigLemaComercial.getSignoPadre();
                            }
                        }
                    } else {
                        sigLogoTipo = sigLogoTipoService.obtenerRegistroPrincipalSigLogoTipoXIdSignoMarca(sigRegistroRepo.getIdSignoMarca());
                        if (sigLogoTipo.getIdLogoTipo() != null) {
                            sigImage = sigImagenService.obtenerSigImagenXIdSigLogoTipo(sigLogoTipo.getIdLogoTipo());
                            if (sigImage.getImagen() != null) {
                                existeLogoR = true;
                                byte[] blobasbytes = sigImage.getImagen();
                                ImageIcon icon = new ImageIcon((byte[]) blobasbytes);
                                logoR = icon.getImage();
                            }
                        }
                    }

                    Usuario user = listUsuario.get(0);
                    usuario = "/" + devuelveNombreCorto(user.getNombre(), user.getPrimer_apellido(), user.getSegundo_apellido());

                    parametrosR.put("imgSenapi", imgSenapiR);
                    parametrosR.put("smDescripcion", smDescripcionR);
                    parametrosR.put("fechaIngreso", fechaIngresoR);
                    parametrosR.put("horaIngreso", horaIngresoR);
                    parametrosR.put("numeroGaceta", numeroGacetaR);
                    parametrosR.put("fechaPublicacion", fechaPublicacionR);
                    parametrosR.put("numeroResolucion", numeroResolucionR);
                    parametrosR.put("fechaRegistro", fechaRegistroR);
                    parametrosR.put("signo", signoR);
                    parametrosR.put("titular", titularR);
                    parametrosR.put("listaProductos", listaProductosR);
                    parametrosR.put("clase", claseR);
                    parametrosR.put("direccionTitular", direccionTitularR);
                    parametrosR.put("numeroRegistro", numeroRegistroR);
                    parametrosR.put("logo", logoR);
                    parametrosR.put("existeLogo", existeLogoR);
                    parametrosR.put("marcaAcompana", marcaAcompanaR);
                    parametrosR.put("observaciones", observacionesR);
                    parametrosR.put("primerUso", primerUso);
                    parametrosR.put("usuario", usuario);

                    String filename = null;
                    String jasperPath = null;

                    if (tipoGeneroPI102) {
                        filename = "ResolucionRegistroLemaComercial.pdf";
                        jasperPath = "/template/registro/resolucionRegistroLemaComercial.jasper";
                    } else {
                        if (tipoGeneroPI101) {
                            filename = "ResolucionRegistroComercial.pdf";
                            jasperPath = "/template/registro/resolucionRegistroNombreComercial.jasper";
                        } else {
                            if (tipoGeneroPI100) {
                                filename = "ResolucionRegistroMarca.pdf";
                                jasperPath = "/template/registro/resolucionRegistroMarca.jasper";
                            }
                        }
                    }

                    this.PDFSD(parametrosR, jasperPath, filename);
                    limpiar();
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "No existe el signo marca", ""));
                }
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "No existe el registro", ""));
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "No existe el registro", ""));
        }
    }

    /**
     * Metodo para llenar los datos del reporte de titulos y su generacion en
     * formato pdf
     *
     * Creado: Ruben Ramirez Fecha: 28/12/2016
     *
     * @throws net.sf.jasperreports.engine.JRException
     * @throws java.io.IOException
     */
    public void imprimirTitulo() throws JRException, IOException, Exception {
        if (sigregistro.getIdRegistro() != null) {
            byte ptext[] = observaciones.getBytes(ISO_8859_1);
            String value = new String(ptext, UTF_8);
            sigregistro.setObservacion(value);
            sigRegistroService.guardar_modificar_listar_SigRegistro(sigregistro, 2);

            signoT = sigregistro.getSigno().trim();

            tipoSignoT = "(" + sigregistro.getTipoSignoDescripcion() + ")";

            generoSignoT = sigregistro.getTipoGeneroDescripcion();

            clasesNizaT = sigregistro.getClase();

            registroT = sigregistro.getNumeroRegistro() + " - " + sigregistro.getSerie();

            resolucionT = sigregistro.getNumeroResolucion() + " - " + sigregistro.getGestionResolucion();

            titularT = sigregistro.getTitular();

            if (sigregistro.getFechaRegistro() != null) {
                fechaRegistroT = formateador.format(sigregistro.getFechaRegistro());
            }

            sigLogoTipo = sigLogoTipoService.obtenerRegistroPrincipalSigLogoTipoXIdSignoMarca(sigregistro.getIdSignoMarca());
            if (sigLogoTipo.getIdLogoTipo() != null) {
                sigImage = sigImagenService.obtenerSigImagenXIdSigLogoTipo(sigLogoTipo.getIdLogoTipo());
                if (sigImage.getImagen() != null) {
                    byte[] blobasbytes = sigImage.getImagen();
                    ImageIcon icon = new ImageIcon((byte[]) blobasbytes);
                    fotoT = icon.getImage();
                }
            }

            if (encabezado) {
                tituloT = true;
            }

            parametrosT.put("signo", signoT);
            parametrosT.put("tipoSigno", tipoSignoT);
            parametrosT.put("generoSigno", generoSignoT);
            parametrosT.put("clasesNiza", clasesNizaT);
            parametrosT.put("registro", registroT);
            parametrosT.put("resolucion", resolucionT);
            parametrosT.put("titular", titularT);
            parametrosT.put("fechaRegistro", fechaRegistroT);
            parametrosT.put("foto", fotoT);
            parametrosT.put("titulo", tituloT);

            String filename = null;
            String jasperPath = null;

            if (valorFormato.equals("registro2011")) {
                filename = "ImpresionTituloNuevo2011.pdf";
                jasperPath = "/template/registro/impresionTituloNuevo2011.jasper";
                this.PDFSD(parametrosT, jasperPath, filename);
            }

            if (valorFormato.equals("registroNuevo")) {
                filename = "ImpresionTituloNuevo.pdf";
                jasperPath = "/template/registro/impresionTituloNuevo.jasper";
                this.PDFSD(parametrosT, jasperPath, filename);
            }

            if (valorFormato.equals("registroAntiguo")) {
                filename = "ImpresionTituloAntiguo.pdf";
                jasperPath = "/template/registro/impresionTituloAntiguo.jasper";
                this.PDFSD(parametrosT, jasperPath, filename);
            }

            limpiar();
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "No existe el registro", ""));
        }
    }

    /**
     * Metodo para generar el reporte en formato pdf
     *
     * Creado: Ruben Ramirez Fecha: 28/12/2016
     *
     * @param params
     * @param jasperPath
     * @param fileName
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

    public Boolean getEncabezado() {
        return encabezado;
    }

    public void setEncabezado(Boolean encabezado) {
        this.encabezado = encabezado;
    }

    public Boolean getReasignar() {
        return reasignar;
    }

    public void setReasignar(Boolean reasignar) {
        this.reasignar = reasignar;
    }

    public Long getRegistro() {
        return registro;
    }

    public void setRegistro(Long registro) {
        this.registro = registro;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public Long getResolucion() {
        return resolucion;
    }

    public void setResolucion(Long resolucion) {
        this.resolucion = resolucion;
    }

    public Integer getGestion() {
        return gestion;
    }

    public void setGestion(Integer gestion) {
        this.gestion = gestion;
    }

    public SigSignoClaseNizaService getSigSignoClaseNizaService() {
        return sigSignoClaseNizaService;
    }

    public void setSigSignoClaseNizaService(SigSignoClaseNizaService sigSignoClaseNizaService) {
        this.sigSignoClaseNizaService = sigSignoClaseNizaService;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Date getFechaPrimerUso() {
        return fechaPrimerUso;
    }

    public void setFechaPrimerUso(Date fechaPrimerUso) {
        this.fechaPrimerUso = fechaPrimerUso;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getTipoMarca() {
        return tipoMarca;
    }

    public void setTipoMarca(String tipoMarca) {
        this.tipoMarca = tipoMarca;
    }

    public Integer getClase() {
        return clase;
    }

    public void setClase(Integer clase) {
        this.clase = clase;
    }

    public String getTitulares() {
        return titulares;
    }

    public void setTitulares(String titulares) {
        this.titulares = titulares;
    }

    public Long getRegistroBuscar() {
        return registroBuscar;
    }

    public void setRegistroBuscar(Long registroBuscar) {
        this.registroBuscar = registroBuscar;
    }

    public String getSerieBuscar() {
        return serieBuscar;
    }

    public void setSerieBuscar(String serieBuscar) {
        this.serieBuscar = serieBuscar;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
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

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getTipoSigno() {
        return tipoSigno;
    }

    public void setTipoSigno(String tipoSigno) {
        this.tipoSigno = tipoSigno;
    }

    public String getValorFormato() {
        return valorFormato;
    }

    public void setValorFormato(String valorFormato) {
        this.valorFormato = valorFormato;
    }

    public Boolean getCheckEncabezadoRender() {
        return checkEncabezadoRender;
    }

    public void setCheckEncabezadoRender(Boolean checkEncabezadoRender) {
        this.checkEncabezadoRender = checkEncabezadoRender;
    }

    public Boolean getValorLabel() {
        return valorLabel;
    }

    public void setValorLabel(Boolean valorLabel) {
        this.valorLabel = valorLabel;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public List<Dominio> getLstSerieRegistro() throws Exception {
        return lstSerieRegistro = dominioService.obtenerListadoDominio("serie_registro");
    }

    public void setLstSerieRegistro(List<Dominio> lstSerieRegistro) {
        this.lstSerieRegistro = lstSerieRegistro;
    }

    public DominioService getDominioService() {
        return dominioService;
    }

    public void setDominioService(DominioService dominioService) {
        this.dominioService = dominioService;
    }

    public SigRegistroService getSigRegistroService() {
        return sigRegistroService;
    }

    public void setSigRegistroService(SigRegistroService sigRegistroService) {
        this.sigRegistroService = sigRegistroService;
    }

    public SigRegistro getSigregistro() {
        return sigregistro;
    }

    public void setSigregistro(SigRegistro sigregistro) {
        this.sigregistro = sigregistro;
    }

    public ImagenPojoService getImagenPojoService() {
        return imagenPojoService;
    }

    public void setImagenPojoService(ImagenPojoService imagenPojoService) {
        this.imagenPojoService = imagenPojoService;
    }

    public List<ImagenPojo> getLstImagenPojos() {
        return lstImagenPojos;
    }

    public void setLstImagenPojos(List<ImagenPojo> lstImagenPojos) {
        this.lstImagenPojos = lstImagenPojos;
    }

    public SigSignoMarcaService getSigSignoMarcaService() {
        return sigSignoMarcaService;
    }

    public void setSigSignoMarcaService(SigSignoMarcaService sigSignoMarcaService) {
        this.sigSignoMarcaService = sigSignoMarcaService;
    }

    public ComunService getComunService() {
        return comunService;
    }

    public void setComunService(ComunService comunService) {
        this.comunService = comunService;
    }

    public SigTipoSignoService getSigTipoSignoService() {
        return sigTipoSignoService;
    }

    public void setSigTipoSignoService(SigTipoSignoService sigTipoSignoService) {
        this.sigTipoSignoService = sigTipoSignoService;
    }

    public SigDetallePublicacionService getSigDetallePublicacionService() {
        return sigDetallePublicacionService;
    }

    public void setSigDetallePublicacionService(SigDetallePublicacionService sigDetallePublicacionService) {
        this.sigDetallePublicacionService = sigDetallePublicacionService;
    }

    public SigPublicacionService getSigPublicacionService() {
        return sigPublicacionService;
    }

    public void setSigPublicacionService(SigPublicacionService sigPublicacionService) {
        this.sigPublicacionService = sigPublicacionService;
    }

    public SigSolicitanteApoderadoService getSigSolicitanteApoderadoService() {
        return sigSolicitanteApoderadoService;
    }

    public void setSigSolicitanteApoderadoService(SigSolicitanteApoderadoService sigSolicitanteApoderadoService) {
        this.sigSolicitanteApoderadoService = sigSolicitanteApoderadoService;
    }

    public Boolean getBtnAceptarDisabled() {
        return btnAceptarDisabled;
    }

    public void setBtnAceptarDisabled(Boolean btnAceptarDisabled) {
        this.btnAceptarDisabled = btnAceptarDisabled;
    }

    public String getDescripcionSM() {
        return descripcionSM;
    }

    public void setDescripcionSM(String descripcionSM) {
        this.descripcionSM = descripcionSM;
    }

    public String getGestionSMAux() {
        return gestionSMAux;
    }

    public void setGestionSMAux(String gestionSMAux) {
        this.gestionSMAux = gestionSMAux;
    }

    public String getExtensionSM() {
        return extensionSM;
    }

    public void setExtensionSM(String extensionSM) {
        this.extensionSM = extensionSM;
    }

    public String getNumeroSMAux() {
        return numeroSMAux;
    }

    public void setNumeroSMAux(String numeroSMAux) {
        this.numeroSMAux = numeroSMAux;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Long getNumeroPublicacion() {
        return numeroPublicacion;
    }

    public void setNumeroPublicacion(Long numeroPublicacion) {
        this.numeroPublicacion = numeroPublicacion;
    }

    public Long getNumeroGaceta() {
        return numeroGaceta;
    }

    public void setNumeroGaceta(Long numeroGaceta) {
        this.numeroGaceta = numeroGaceta;
    }

    public Date getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(Date fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public Boolean getPrimerUsoRendered() {
        return primerUsoRendered;
    }

    public void setPrimerUsoRendered(Boolean primerUsoRendered) {
        this.primerUsoRendered = primerUsoRendered;
    }

    public ImagenPojo getImagenPojo() {
        return imagenPojo;
    }

    public void setImagenPojo(ImagenPojo imagenPojo) {
        this.imagenPojo = imagenPojo;
    }

    public CorrelativoService getCorrelativoService() {
        return correlativoService;
    }

    public void setCorrelativoService(CorrelativoService correlativoService) {
        this.correlativoService = correlativoService;
    }

    public Boolean getBtnDenegarDisabled() {
        return btnDenegarDisabled;
    }

    public void setBtnDenegarDisabled(Boolean btnDenegarDisabled) {
        this.btnDenegarDisabled = btnDenegarDisabled;
    }

    public Boolean getBtnRehacerDisabled() {
        return btnRehacerDisabled;
    }

    public void setBtnRehacerDisabled(Boolean btnRehacerDisabled) {
        this.btnRehacerDisabled = btnRehacerDisabled;
    }

    public Boolean getBtnBorrarDisabled() {
        return btnBorrarDisabled;
    }

    public void setBtnBorrarDisabled(Boolean btnBorrarDisabled) {
        this.btnBorrarDisabled = btnBorrarDisabled;
    }

    public Boolean getBtnReasignarRendered() {
        return btnReasignarRendered;
    }

    public void setBtnReasignarRendered(Boolean btnReasignarRendered) {
        this.btnReasignarRendered = btnReasignarRendered;
    }

    public Boolean getChkReasiganrDisabled() {
        return chkReasiganrDisabled;
    }

    public void setChkReasiganrDisabled(Boolean chkReasiganrDisabled) {
        this.chkReasiganrDisabled = chkReasiganrDisabled;
    }

    public String getFechaLiteral() {
        return fechaLiteral;
    }

    public void setFechaLiteral(String fechaLiteral) {
        this.fechaLiteral = fechaLiteral;
    }

    public Boolean getLblFechaRegistroRendered() {
        return lblFechaRegistroRendered;
    }

    public void setLblFechaRegistroRendered(Boolean lblFechaRegistroRendered) {
        this.lblFechaRegistroRendered = lblFechaRegistroRendered;
    }

    public Boolean getSoloLectura() {
        return soloLectura;
    }

    public void setSoloLectura(Boolean soloLectura) {
        this.soloLectura = soloLectura;
    }

    public Boolean getLblDenegadoRnedered() {
        return lblDenegadoRnedered;
    }

    public void setLblDenegadoRnedered(Boolean lblDenegadoRnedered) {
        this.lblDenegadoRnedered = lblDenegadoRnedered;
    }

    public List<SigSignoMarca> getLstSigSignoMarca() {
        return lstSigSignoMarca;
    }

    public void setLstSigSignoMarca(List<SigSignoMarca> lstSigSignoMarca) {
        this.lstSigSignoMarca = lstSigSignoMarca;
    }

    public SigSignoMarca getEncontradoSigSignoMarca() {
        return encontradoSigSignoMarca;
    }

    public void setEncontradoSigSignoMarca(SigSignoMarca encontradoSigSignoMarca) {
        this.encontradoSigSignoMarca = encontradoSigSignoMarca;
    }

    public String[] getDatosSolicitanteApoderado() {
        return datosSolicitanteApoderado;
    }

    public void setDatosSolicitanteApoderado(String[] datosSolicitanteApoderado) {
        this.datosSolicitanteApoderado = datosSolicitanteApoderado;
    }

    public String[] getDatosApoderado() {
        return datosApoderado;
    }

    public void setDatosApoderado(String[] datosApoderado) {
        this.datosApoderado = datosApoderado;
    }

    public String getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(String listaProductos) {
        this.listaProductos = listaProductos;
    }

    public Correlativo getCorrelativo() {
        return correlativo;
    }

    public void setCorrelativo(Correlativo correlativo) {
        this.correlativo = correlativo;
    }

    public Correlativo getCorrelativoResolucion() {
        return correlativoResolucion;
    }

    public void setCorrelativoResolucion(Correlativo correlativoResolucion) {
        this.correlativoResolucion = correlativoResolucion;
    }

    public Long getRegistroAux() {
        return registroAux;
    }

    public void setRegistroAux(Long registroAux) {
        this.registroAux = registroAux;
    }

    public Long getResolucionAux() {
        return resolucionAux;
    }

    public void setResolucionAux(Long resolucionAux) {
        this.resolucionAux = resolucionAux;
    }

    public Integer getGestionAux() {
        return gestionAux;
    }

    public void setGestionAux(Integer gestionAux) {
        this.gestionAux = gestionAux;
    }

    public Boolean[] getValidador() {
        return validador;
    }

    public void setValidador(Boolean[] validador) {
        this.validador = validador;
    }

    public Date getFechaServidor() {
        return fechaServidor;
    }

    public void setFechaServidor(Date fechaServidor) {
        this.fechaServidor = fechaServidor;
    }

    public Boolean getSwRehacer() {
        return swRehacer;
    }

    public void setSwRehacer(Boolean swRehacer) {
        this.swRehacer = swRehacer;
    }

    public String getSerieAux() {
        return serieAux;
    }

    public void setSerieAux(String serieAux) {
        this.serieAux = serieAux;
    }

    public SimpleDateFormat getFormateador() {
        return formateador;
    }

    public void setFormateador(SimpleDateFormat formateador) {
        this.formateador = formateador;
    }

    public Long getRegistroBuscarAux() {
        return registroBuscarAux;
    }

    public void setRegistroBuscarAux(Long registroBuscarAux) {
        this.registroBuscarAux = registroBuscarAux;
    }

    public String getSeriBuscarAux() {
        return seriBuscarAux;
    }

    public void setSeriBuscarAux(String seriBuscarAux) {
        this.seriBuscarAux = seriBuscarAux;
    }

    public Long getSmAux() {
        return smAux;
    }

    public void setSmAux(Long smAux) {
        this.smAux = smAux;
    }

    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

    public Boolean[] getValidadorRegistroResolucion() {
        return validadorRegistroResolucion;
    }

    public void setValidadorRegistroResolucion(Boolean[] validadorRegistroResolucion) {
        this.validadorRegistroResolucion = validadorRegistroResolucion;
    }

    public Boolean[] getValidadorSM() {
        return validadorSM;
    }

    public void setValidadorSM(Boolean[] validadorSM) {
        this.validadorSM = validadorSM;
    }

    public Map<String, Object> getParametrosR() {
        return parametrosR;
    }

    public void setParametros(Map<String, Object> parametrosR) {
        this.parametrosR = parametrosR;
    }

    public String getSmDescripcionR() {
        return smDescripcionR;
    }

    public void setSmDescripcionR(String smDescripcionR) {
        this.smDescripcionR = smDescripcionR;
    }

    public String getFechaIngresoR() {
        return fechaIngresoR;
    }

    public void setFechaIngresoR(String fechaIngresoR) {
        this.fechaIngresoR = fechaIngresoR;
    }

    public String getHoraIngresoR() {
        return horaIngresoR;
    }

    public void setHoraIngresoR(String horaIngresoR) {
        this.horaIngresoR = horaIngresoR;
    }

    public Long getNumeroGacetaR() {
        return numeroGacetaR;
    }

    public void setNumeroGacetaR(Long numeroGacetaR) {
        this.numeroGacetaR = numeroGacetaR;
    }

    public String getFechaPublicacionR() {
        return fechaPublicacionR;
    }

    public void setFechaPublicacionR(String fechaPublicacionR) {
        this.fechaPublicacionR = fechaPublicacionR;
    }

    public String getNumeroResolucionR() {
        return numeroResolucionR;
    }

    public void setNumeroResolucionR(String numeroResolucionR) {
        this.numeroResolucionR = numeroResolucionR;
    }

    public String getFechaRegistroR() {
        return fechaRegistroR;
    }

    public void setFechaRegistroR(String fechaRegistroR) {
        this.fechaRegistroR = fechaRegistroR;
    }

    public String getSignoR() {
        return signoR;
    }

    public void setSignoR(String signoR) {
        this.signoR = signoR;
    }

    public String getTitularR() {
        return titularR;
    }

    public void setTitularR(String titularR) {
        this.titularR = titularR;
    }

    public String getListaProductosR() {
        return listaProductosR;
    }

    public void setListaProductosR(String listaProductosR) {
        this.listaProductosR = listaProductosR;
    }

    public Integer getClaseR() {
        return claseR;
    }

    public void setClaseR(Integer claseR) {
        this.claseR = claseR;
    }

    public String getDireccionTitularR() {
        return direccionTitularR;
    }

    public void setDireccionTitularR(String direccionTitularR) {
        this.direccionTitularR = direccionTitularR;
    }

    public String getNumeroRegistroR() {
        return numeroRegistroR;
    }

    public void setNumeroRegistroR(String numeroRegistroR) {
        this.numeroRegistroR = numeroRegistroR;
    }

    public String getImgSenapiR() {
        return imgSenapiR;
    }

    public void setImgSenapiR(String imgSenapiR) {
        this.imgSenapiR = imgSenapiR;
    }

    public boolean isExisteLogoR() {
        return existeLogoR;
    }

    public void setExisteLogoR(boolean existeLogoR) {
        this.existeLogoR = existeLogoR;
    }

    public SigLogoTipo getSigLogoTipo() {
        return sigLogoTipo;
    }

    public void setSigLogoTipo(SigLogoTipo sigLogoTipo) {
        this.sigLogoTipo = sigLogoTipo;
    }

    public SigImagen getSigImage() {
        return sigImage;
    }

    public void setSigImage(SigImagen sigImage) {
        this.sigImage = sigImage;
    }

    public Image getLogoR() {
        return logoR;
    }

    public void setLogoR(Image logoR) {
        this.logoR = logoR;
    }

    public SigLogoTipoService getSigLogoTipoService() {
        return sigLogoTipoService;
    }

    public void setSigLogoTipoService(SigLogoTipoService sigLogoTipoService) {
        this.sigLogoTipoService = sigLogoTipoService;
    }

    public SigImagenService getSigImagenService() {
        return sigImagenService;
    }

    public void setSigImagenService(SigImagenService sigImagenService) {
        this.sigImagenService = sigImagenService;
    }

    public String getMarcaAcompanaR() {
        return marcaAcompanaR;
    }

    public void setMarcaAcompanaR(String marcaAcompanaR) {
        this.marcaAcompanaR = marcaAcompanaR;
    }

    public Map<String, Object> getParametrosT() {
        return parametrosT;
    }

    public void setParametrosT(Map<String, Object> parametrosT) {
        this.parametrosT = parametrosT;
    }

    public String getSignoT() {
        return signoT;
    }

    public void setSignoT(String signoT) {
        this.signoT = signoT;
    }

    public String getTipoSignoT() {
        return tipoSignoT;
    }

    public void setTipoSignoT(String tipoSignoT) {
        this.tipoSignoT = tipoSignoT;
    }

    public String getGeneroSignoT() {
        return generoSignoT;
    }

    public void setGeneroSignoT(String generoSignoT) {
        this.generoSignoT = generoSignoT;
    }

    public Integer getClasesNizaT() {
        return clasesNizaT;
    }

    public void setClasesNizaT(Integer clasesNizaT) {
        this.clasesNizaT = clasesNizaT;
    }

    public String getRegistroT() {
        return registroT;
    }

    public void setRegistroT(String registroT) {
        this.registroT = registroT;
    }

    public String getResolucionT() {
        return resolucionT;
    }

    public void setResolucionT(String resolucionT) {
        this.resolucionT = resolucionT;
    }

    public String getTitularT() {
        return titularT;
    }

    public void setTitularT(String titularT) {
        this.titularT = titularT;
    }

    public String getFechaRegistroT() {
        return fechaRegistroT;
    }

    public void setFechaRegistroT(String fechaRegistroT) {
        this.fechaRegistroT = fechaRegistroT;
    }

    public Image getFotoT() {
        return fotoT;
    }

    public void setFotoT(Image fotoT) {
        this.fotoT = fotoT;
    }

    public Boolean getTituloT() {
        return tituloT;
    }

    public void setTituloT(Boolean tituloT) {
        this.tituloT = tituloT;
    }

    public SeguimientoService getSeguimientoService() {
        return seguimientoService;
    }

    public void setSeguimientoService(SeguimientoService seguimientoService) {
        this.seguimientoService = seguimientoService;
    }

    public LogTransService getLogTransService() {
        return logTransService;
    }

    public void setLogTransService(LogTransService logTransService) {
        this.logTransService = logTransService;
    }

    public UsuarioService getUsuarioService() {
        return usuarioService;
    }

    public void setUsuarioService(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    public Boolean getImagenRendered() {
        return imagenRendered;
    }

    public void setImagenRendered(Boolean imagenRendered) {
        this.imagenRendered = imagenRendered;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public List<SigTipoSigno> getLstSigTipoSigno() {
        return lstSigTipoSigno;
    }

    public void setLstSigTipoSigno(List<SigTipoSigno> lstSigTipoSigno) {
        this.lstSigTipoSigno = lstSigTipoSigno;
    }

    public SigSignoMarca getEncontradoSigSignoMarcaRepo() {
        return encontradoSigSignoMarcaRepo;
    }

    public void setEncontradoSigSignoMarcaRepo(SigSignoMarca encontradoSigSignoMarcaRepo) {
        this.encontradoSigSignoMarcaRepo = encontradoSigSignoMarcaRepo;
    }

    public SigRegistro getSigRegistroRepo() {
        return sigRegistroRepo;
    }

    public void setSigRegistroRepo(SigRegistro sigRegistroRepo) {
        this.sigRegistroRepo = sigRegistroRepo;
    }

    public List<Dominio> getListTipoGeneroPI101() {
        return listTipoGeneroPI101;
    }

    public void setListTipoGeneroPI101(List<Dominio> listTipoGeneroPI101) {
        this.listTipoGeneroPI101 = listTipoGeneroPI101;
    }

    public List<Dominio> getListTipoGeneroPI100() {
        return listTipoGeneroPI100;
    }

    public void setListTipoGeneroPI100(List<Dominio> listTipoGeneroPI100) {
        this.listTipoGeneroPI100 = listTipoGeneroPI100;
    }

    public List<Dominio> getListTipoGeneroPI102() {
        return listTipoGeneroPI102;
    }

    public void setListTipoGeneroPI102(List<Dominio> listTipoGeneroPI102) {
        this.listTipoGeneroPI102 = listTipoGeneroPI102;
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

    public EtapaService getEtapaService() {
        return etapaService;
    }

    public void setEtapaService(EtapaService etapaService) {
        this.etapaService = etapaService;
    }

    public FlujoTiempoService getFlujoTiempoService() {
        return flujoTiempoService;
    }

    public void setFlujoTiempoService(FlujoTiempoService flujoTiempoService) {
        this.flujoTiempoService = flujoTiempoService;
    }

    public RegionalService getRegionalService() {
        return regionalService;
    }

    public void setRegionalService(RegionalService regionalService) {
        this.regionalService = regionalService;
    }

}
