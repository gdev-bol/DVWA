/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.entidades.bean.publicacion;

import com.sun.media.jai.codec.MemoryCacheSeekableStream;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.imageio.ImageIO;
import javax.media.jai.JAI;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import snp.gob.bo.entidades.bean.common.AbstractManagedBean;
import snp.gob.bo.gimodel.bdimagen.entidad.SigImagen;
import snp.gob.bo.gimodel.bdimagen.entidad.SigLogoTipo;
import snp.gob.bo.gimodel.bdimagen.servicio.SigImagenService;
import snp.gob.bo.gimodel.bdimagen.servicio.SigLogoTipoService;
import snp.gob.bo.gimodel.entidad.Correlativo;
import snp.gob.bo.gimodel.entidad.Dominio;
import snp.gob.bo.gimodel.entidad.SigDetallePublicacion;
import snp.gob.bo.gimodel.entidad.SigPublicacion;
import snp.gob.bo.gimodel.enums.EnumCarpetasTemporales;
import snp.gob.bo.gimodel.enums.EnumEstadoPublicacion;
import snp.gob.bo.gimodel.enums.EnumNombreDominio;
import snp.gob.bo.gimodel.servicio.ComunService;
import snp.gob.bo.gimodel.servicio.CorrelativoService;
import snp.gob.bo.gimodel.servicio.DominioService;
import snp.gob.bo.gimodel.servicio.HistorialService;
import snp.gob.bo.gimodel.servicio.SeccionSubPublicacionService;
import snp.gob.bo.gimodel.servicio.SeguimientoService;
import snp.gob.bo.gimodel.servicio.SigDetallePublicacionService;
import snp.gob.bo.gimodel.servicio.SigPublicacionService;

/**
 *
 * @author susana
 */
@ViewScoped
@ManagedBean(name = "publicacionBean")
public class PublicacionBean extends AbstractManagedBean implements Serializable {

    /**
     * Creates a new instance of publicacionBean
     */
    public PublicacionBean() {
        InputStream stream = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/images/boletin.pdf");
        file = new DefaultStreamedContent(stream, "image/pdf", "boletin.pdf");
    }

    private SigPublicacion publicacionSeleccionada = new SigPublicacion();
    private SigPublicacion publicacionActualizar;
    private String estado;
    private Date fechaFinal;
    private List<SigPublicacion> listaPublicacion = new ArrayList<SigPublicacion>();
    private List<SigDetallePublicacion> listaDetallePublicacion = new ArrayList<SigDetallePublicacion>();
    private String labelDialogo = "";
    private SigDetallePublicacion detallePublicacionSelect = new SigDetallePublicacion();
    private List<Integer> listadoGestiones = new ArrayList<>();
    private Integer gestionGenerar;
    private Long numeroPrepublicacion;
    private String corresponde = "";
    private List<Dominio> listaEstadoPublicacion;
    private StreamedContent file;
    private String observacionDetallePublicacion;
    private Long numeroGaceta;
    private String estadoPublicacion;
    private Date fechaEnvio;
    private Date fechaPublicacion;
    private String observacion;
    private Long total;
    private String mes;
    private Integer gestion;
    private Boolean devolverDisabled = true;
    private Boolean fechaPublicacionDisabled = false;
    private Boolean fechaEnvioDisabled = false;
    private Boolean estadoPublicacionDisabled = false;
    private Boolean numeroGacetaDisabled = false;
    // parametros del reporte
    private Map<String, Object> parametros = new HashMap<String, Object>();
    private List<SigDetallePublicacion> listaDetallePublicacion2 = new ArrayList<SigDetallePublicacion>();
    private List<DatosTipoSignoReporBean> listaDatosTipoSignoReporBean = new ArrayList<DatosTipoSignoReporBean>();
    private List<String> seccionReport = new ArrayList<String>();
    private List<String> subSeccionReport = new ArrayList<String>();
    private SigLogoTipo sigLogoTipo = new SigLogoTipo();
    private SigImagen sigImagen = new SigImagen();
    private byte[] blobasbytes;
    private List<Integer> listaNumeroPrepublicacion;
    private List<Integer> listaNumeroPrepublicacionCmb;
    private Long idUsuario;
    private String imgSenapi;

    @ManagedProperty("#{dominioService}")
    private DominioService dominioService;

    @ManagedProperty("#{sigPublicacionService}")
    private SigPublicacionService sigPublicacionService;

    @ManagedProperty("#{sigDetallePublicacionService}")
    private SigDetallePublicacionService sigDetallePublicacionService;

    @ManagedProperty("#{comunService}")
    private ComunService comunService;

    @ManagedProperty("#{seccionSubPublicacionService}")
    private SeccionSubPublicacionService seccionSubPublicacionService;

    @ManagedProperty("#{sigLogoTipoService}")
    private SigLogoTipoService sigLogoTipoService;

    @ManagedProperty("#{sigImagenService}")
    private SigImagenService sigImagenService;

    @ManagedProperty("#{historialService}")
    private HistorialService historialService;

    @ManagedProperty("#{seguimientoService}")
    private SeguimientoService seguimientoService;

    @ManagedProperty("#{correlativoService}")
    private CorrelativoService correlativoService;

    @PostConstruct
    public void publicacionBeanInit() {
        try {
            idUsuario = super.getIdUsuarioSession();
            llenar();
            Date fecha = null;
            fecha = comunService.obtenerFechaServidor(1L);

            String formato = "yyyy";
            SimpleDateFormat dateFormat = new SimpleDateFormat(formato);
            Integer gestionn = Integer.parseInt(dateFormat.format(fecha));
            gestionGenerar = gestionn;
//        for (int i = gestionn - 5; i < gestionn + 5; i++) {
//            listadoGestiones.add(i);
//        }
            for (int i = gestionn - 100; i <= gestionn; i++) {
                listadoGestiones.add(i);
            }
            listaPrepublicacionDisponible();
            // lista de seccion y subseccion
            seccionReport = seccionSubPublicacionService.listadoSecciones();
            subSeccionReport = seccionSubPublicacionService.listadoSubSecciones();
            Set<String> s = new LinkedHashSet<String>(subSeccionReport);
            subSeccionReport.clear();
            subSeccionReport.addAll(s);
            fechaFinal = comunService.obtenerFechaServidor(1l);
        } catch (Exception ex) {
            Logger.getLogger(PublicacionBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void listaPrepublicacionDisponible() {
        listaNumeroPrepublicacion = sigPublicacionService.lista_Gaceta_Prepublicacion();
        listaNumeroPrepublicacionCmb = new ArrayList<Integer>();
        for (int i = 0; i <= 9; i++) {
            if (!(listaNumeroPrepublicacion).contains(i)) {
                listaNumeroPrepublicacionCmb.add(i);
            }

        }
        String valor = listaGacetaNoCumpleInit();
    }

    public StreamedContent getFile() {
        return file;
    }

    /**
     * metodo que captura el evento al seleccionar una publicacion del listado
     * de sigpublicacion en la vista
     *
     * Creado: Susana Escobar Fecha: 18/10/2016
     *
     * @param event
     */
    public void onRowSelect(SelectEvent event) {
        //FacesMessage msg = new FacesMessage("Publicacion Seleccionada", ((Publicacion) event.getObject()).getIdPublicacion().toString());

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(((SigPublicacion) event.getObject()).getNumero_gaceta().toString()));
        publicacionSeleccionada = ((SigPublicacion) event.getObject());
        listaDetallePublicacion = sigDetallePublicacionService.listaSigDetallePublicacionXidpublicacion(publicacionSeleccionada.getIdpublicacion());
        if (buscarNumeroPrepublicado(publicacionSeleccionada.getNumero_gaceta())) {
            numeroPrepublicacion = publicacionSeleccionada.getNumero_gaceta();
        }
        devolverDisabled = !publicacionSeleccionada.getEstado_publicacion().equals(EnumEstadoPublicacion.PREPUBLICADO.getCodigo());
//        FacesMessage msg = new FacesMessage("Publicacion Seleccionada", ((SigPublicacion) event.getObject()).getNumero_gaceta().toString());
//        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowUnselect(UnselectEvent event) {
        FacesMessage msg = new FacesMessage("Publicacion No Selecccionada", ((SigPublicacion) event.getObject()).getNumero_gaceta().toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);

    }

    public void onRowSelectDetallePublicacion(SelectEvent event) {

    }

    public Boolean btnEliminarDisabled(String estado) {
        return !estado.equals(EnumEstadoPublicacion.PREPUBLICADO.getCodigo());
    }

    /**
     * metodo para inicializar listado de sigpublicacion y asigancion de
     * sigdetallepublicacion inicial
     *
     * Creado: Susana Escobar Paz Fecha: 20/10/2016
     *
     */
    public void llenar() {
        listaPublicacion = sigPublicacionService.listaSigPublicacion();
        if (!listaPublicacion.isEmpty()) {
            publicacionSeleccionada = listaPublicacion.get(0);
            listaDetallePublicacion = sigDetallePublicacionService.listaSigDetallePublicacionXidpublicacion(publicacionSeleccionada.getIdpublicacion());
            devolverDisabled = !publicacionSeleccionada.getEstado_publicacion().equals(EnumEstadoPublicacion.PREPUBLICADO.getCodigo());
        } else {
            listaDetallePublicacion.clear();
        }

    }

    /**
     * metodo que muestra datos en el dialogo actualizar datos de publicacion
     *
     * Creado: Susana Escobar Paz Fecha: 22/10/2016
     *
     * @param event
     * @param publicacionSeleccionadaDialogo
     * @throws java.lang.Exception
     */
    public void cargaPublicacion(ActionEvent event, SigPublicacion publicacionSeleccionadaDialogo) throws Exception {
        publicacionActualizar = publicacionSeleccionadaDialogo;
        numeroGaceta = publicacionSeleccionadaDialogo.getNumero_gaceta();
        estadoPublicacion = publicacionSeleccionadaDialogo.getEstado_publicacion();
        fechaEnvio = publicacionSeleccionadaDialogo.getFecha_envio();
        fechaPublicacion = publicacionSeleccionadaDialogo.getFecha_publicacion();
        observacion = publicacionSeleccionadaDialogo.getObservacion();
        total = publicacionSeleccionadaDialogo.getTotal();
        mes = publicacionSeleccionadaDialogo.getMes();
        gestion = publicacionSeleccionadaDialogo.getGestion();
        if (estadoPublicacion.equals(EnumEstadoPublicacion.PUBLICADO.getCodigo())) {
            estadoPublicacionDisabled = true;
            numeroGacetaDisabled = false;

        } else {
            estadoPublicacionDisabled = false;
            numeroGacetaDisabled = true;

        }

        cambiaComboEstado();
    }

    public Boolean buscarNumeroPrepublicado(Long dato) {
        if (dato == null) {
            return false;
        }
        int[] valores = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        for (int i = 0; i < valores.length; i++) {
            if (valores[i] == dato) {
                return true;
            }
        }
        return false;
    }

    public String listaGacetaNoCumpleInit() {
        String lista = "";
        for (Integer item : listaNumeroPrepublicacion) {

            if ("".equals(lista)) {
                lista = lista + item.toString();
            } else {
                lista = lista + ", " + item.toString();
            }
        }

        return lista;
    }

    public String listaGacetaNoCumple(Long numeroPrepublicacion) {
        String lista = "";
        for (Integer item : listaNumeroPrepublicacion) {

            if ("".equals(lista)) {
                lista = lista + item.toString();
            } else {
                lista = lista + ", " + item.toString();
            }
        }

        if (listaNumeroPrepublicacion.isEmpty()) {

            lista = numeroPrepublicacion.toString();
        }

        return lista;
    }

    /**
     * metodo para generar publicacion con datos recogidos en estados para
     * publicacion
     *
     * Creado: Susana Escobar Fecha: 18/10/2016
     *
     * @throws java.lang.Exception
     */
    public void generarPublicacion() throws Exception {
        if (fechaFinal == null) {
            fechaFinal = comunService.obtenerFechaServidor(1l);
        }
        SigPublicacion publicacionExistente = sigPublicacionService.listar_SigPublicacion_numero_estado(numeroPrepublicacion, EnumEstadoPublicacion.PREPUBLICADO.getCodigo());

        if (publicacionExistente != null) {
            if (sigDetallePublicacionService.verificarMarcasPublicacion(fechaFinal)) {
                publicacionSeleccionada = sigPublicacionService.generarSigPublicacion(publicacionExistente, fechaFinal, numeroPrepublicacion, corresponde, gestionGenerar, listaGacetaNoCumple(numeroPrepublicacion), idUsuario);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Se agregaron datos a la publicacion"));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "No hay marcas para publicar", ""));

            }
        } else {
            Boolean[] validacionGenerar = validaGenerar(numeroPrepublicacion, fechaFinal, corresponde);
            if (validacionGenerar[3]) {
                publicacionSeleccionada = sigPublicacionService.generarSigPublicacion(new SigPublicacion(), fechaFinal, numeroPrepublicacion, corresponde, gestionGenerar, listaGacetaNoCumple(numeroPrepublicacion), idUsuario);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Publicacion generada exitosamente", ""));
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Numeracion automatica", ""));
            } else {
                muestraMensajesValidadorGenerar(validacionGenerar);
            }
        }
        llenar();
        listaPrepublicacionDisponible();
    }

    public Boolean[] validaGenerar(Long numeroGaceta, Date fechaFinal, String correspondemes) {

        Boolean[] validador = {false, false, false, true};

        validador[0] = sigPublicacionService.verificaExistenciaPublicacion_numero(numeroGaceta);
        validador[1] = sigDetallePublicacionService.verificarMarcasPublicacion(fechaFinal);
        if (correspondemes != null && !correspondemes.equals("")) {
            validador[2] = true;
        }

        for (int i = 0; i <= 2; i++) {
            if (!validador[i]) {
                validador[3] = false;
            }
        }
        return validador;
    }

    public void muestraMensajesValidadorGenerar(Boolean[] validador) {
        if (validador[0] == false) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El numero de gaceta ya existe", ""));
        }
        if (validador[1] == false) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "No hay marcas para publicar", ""));
        }
        if (validador[2] == false) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Escriba el mes correspondiente", ""));
        }

    }

    public void devolverTramite() {
        //listaDetallePublicacion.remove(detallePublicacionSelect);    
        Long inicioRecuperado = publicacionSeleccionada.getInicio();
        sigDetallePublicacionService.devolverDetallePublicacion(detallePublicacionSelect, observacionDetallePublicacion, idUsuario);
        sigDetallePublicacionService.numeracionSigDetallePublicacionXInicio(publicacionSeleccionada.getIdpublicacion(), inicioRecuperado);
        listaPublicacion = sigPublicacionService.listaSigPublicacion();
        listaDetallePublicacion = sigDetallePublicacionService.listaSigDetallePublicacionXidpublicacion(publicacionSeleccionada.getIdpublicacion());
        listaPrepublicacionDisponible();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Se quito el tramite", ""));
    }

    public void dialogoDevolver(ActionEvent event, SigDetallePublicacion detallePublicacion) {
        labelDialogo = detallePublicacion.getSm_descripcion();
        detallePublicacionSelect = detallePublicacion;
        observacionDetallePublicacion = "";

    }

    /**
     * metodo para actualizar datos de publicacion, se validan distintos campos
     * para los tres estados Prepublicado, Enviado y Publicado
     *
     * Creado: Susana Escobar Fecha: 19/10/2016
     *
     */
    public void actualizarDatosPublicacion() {
        publicacionSeleccionada = sigPublicacionService.guardar_modificar_listar_SigPublicacion(publicacionActualizar, 4);

        Long cantidad = sigDetallePublicacionService.contador_sigDetallePublicacion(publicacionSeleccionada.getIdpublicacion());
        String estadoAnterior = publicacionSeleccionada.getEstado_publicacion();
        switch (estadoPublicacion) {
            case "PPUB":

                Boolean[] validacionPrePublicado = validaActualizarPrePublicado(numeroGaceta, publicacionSeleccionada.getEstado_publicacion());
                // System.out.println("valor   ppub  Validador  " + validacionPrePublicado[3]);
                if (validacionPrePublicado[3]) {
                    if (estadoAnterior.equals(EnumEstadoPublicacion.PREPUBLICADO.getCodigo())) {
                        publicacionSeleccionada.setEstado_publicacion(estadoPublicacion);
                        publicacionSeleccionada.setNumero_gaceta(numeroGaceta);
                        publicacionSeleccionada.setObservacion(observacion);
                        publicacionSeleccionada.setMes(mes);
                        publicacionSeleccionada.setGestion(gestion);
                        publicacionSeleccionada.setTotal(cantidad);
                        sigPublicacionService.guardar_modificar_listar_SigPublicacion(publicacionSeleccionada, 2);
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Datos modificados exitosamente"));
                        listaPublicacion = sigPublicacionService.listaSigPublicacion();
                    } else {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "No es posible volver al estado Prepublicado", ""));
                    }
                } else {
                    //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El numero de gaceta no corresponde al estado Prepublicado", ""));
                    muestraMensajesValidadorPrePublicado(validacionPrePublicado);
                }
                break;
            case "ENVI":
                Boolean[] validacionEnviado = validaActualizarEnviado(numeroGaceta, mes, gestion);
                // System.out.println("valor   ENVIADO  Validador  " + validacionEnviado[4]);
                if (validacionEnviado[4]) {
                    publicacionSeleccionada.setEstado_publicacion(estadoPublicacion);
                    publicacionSeleccionada.setNumero_gaceta(numeroGaceta);
                    publicacionSeleccionada.setObservacion(observacion);
                    publicacionSeleccionada.setMes(mes);
                    publicacionSeleccionada.setGestion(gestion);
                    publicacionSeleccionada.setFecha_envio(fechaEnvio);
                    publicacionSeleccionada.setTotal(cantidad);
                    sigPublicacionService.guardar_modificar_listar_SigPublicacion(publicacionSeleccionada, 2);
                    if (publicacionSeleccionada.getInicio() == 0) {
                        sigDetallePublicacionService.numeracionSigDetallePublicacion(publicacionSeleccionada.getIdpublicacion());
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Numeracion automatica"));
                    }
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Datos modificados exitosamente"));
                    listaPublicacion = sigPublicacionService.listaSigPublicacion();
                    devolverDisabled = !publicacionSeleccionada.getEstado_publicacion().equals(EnumEstadoPublicacion.PREPUBLICADO.getCodigo());
                    if (!estadoAnterior.equals(EnumEstadoPublicacion.ENVIADO.getCodigo())) {
                        //CREAR POR UNICA VEZ EL SEGUIMIENTO 

                        sigDetallePublicacionService.seguimiento_SigDetallePublicacion(publicacionSeleccionada.getIdpublicacion(), idUsuario, null, publicacionSeleccionada.getFecha_envio());
                        // sigDetallePublicacionService.primerSigSeguimiento_SigDetallePublicacion_Signos(publicacionSeleccionada.getIdpublicacion(), publicacionSeleccionada.getFecha_envio());
                    } else {
                        sigDetallePublicacionService.actualizar_SigSeguimiento_SigDetallePublicacion(publicacionSeleccionada.getIdpublicacion(), publicacionSeleccionada.getFecha_envio(), null);
                    }

                } else {
                    muestraMensajesValidadorEnviado(validacionEnviado);
                }
                break;
            case "PUBL":
                Boolean[] validacionPublicado = validaActualizarPublicado(numeroGaceta, fechaEnvio, fechaPublicacion, mes, gestion, publicacionSeleccionada.getInicio());

                if (validacionPublicado[8]) {

                    publicacionSeleccionada.setEstado_publicacion(estadoPublicacion);
                    publicacionSeleccionada.setNumero_gaceta(numeroGaceta);
                    publicacionSeleccionada.setFecha_envio(fechaEnvio);
                    publicacionSeleccionada.setFecha_publicacion(fechaPublicacion);
                    publicacionSeleccionada.setTotal(cantidad);
                    publicacionSeleccionada.setObservacion(observacion);
                    publicacionSeleccionada.setMes(mes);
                    publicacionSeleccionada.setGestion(gestion);
                    sigPublicacionService.guardar_modificar_listar_SigPublicacion(publicacionSeleccionada, 2);
//                    if (publicacionSeleccionada.getInicio() == 0) {
//                        sigDetallePublicacionService.numeracionSigDetallePublicacion(publicacionSeleccionada.getIdpublicacion());
//                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Numeracion automática"));
//                    }
                    if (!estadoAnterior.equals(EnumEstadoPublicacion.PUBLICADO.getCodigo())) {
                        sigDetallePublicacionService.seguimiento_SigDetallePublicacion(publicacionSeleccionada.getIdpublicacion(), idUsuario, publicacionSeleccionada.getFecha_publicacion(), publicacionSeleccionada.getFecha_envio());
                        sigDetallePublicacionService.primer_SigHistorial(publicacionSeleccionada.getIdpublicacion(), publicacionSeleccionada.getFecha_publicacion(), idUsuario);
                    } else {
                        sigDetallePublicacionService.historial_SigDetallePublicacion_Signos(publicacionSeleccionada.getIdpublicacion(), numeroGaceta, fechaPublicacion, idUsuario, fechaEnvio);
                    }
                    listaPublicacion = sigPublicacionService.listaSigPublicacion();
                    devolverDisabled = !publicacionSeleccionada.getEstado_publicacion().equals(EnumEstadoPublicacion.PREPUBLICADO.getCodigo());
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Datos modificados exitosamente"));

                } else {
                    muestraMensajesValidadorPublicado(validacionPublicado);
                }
                break;
            default:
                break;
        }
        listaDetallePublicacion = sigDetallePublicacionService.listaSigDetallePublicacionXidpublicacion(publicacionSeleccionada.getIdpublicacion());
    }

    public Boolean[] validaActualizarEnviado(Long numeroGaceta, String mes, Integer gestion) {
        Boolean[] validador = {false, false, false, false, true};
        validador[0] = buscarNumeroPrepublicado(numeroGaceta);
        if (fechaEnvio != null) {
            validador[1] = true;
        }

        if (mes != null && !mes.equals("")) {
            validador[2] = true;
        }

        if (gestion != null) {
            validador[3] = true;
        }

        for (int i = 0; i <= 3; i++) {

            if (!validador[i]) {
                validador[4] = false;
            }
        }
        return validador;
    }

    public void muestraMensajesValidadorEnviado(Boolean[] validador) {
        if (validador[0] == false) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El numero de gaceta no es correcto", ""));
        }
        if (validador[1] == false) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Seleccione fecha envio", ""));
        }
        if (validador[2] == false) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Escriba el mes al que corresponde la publicacion", ""));
        }
        if (validador[3] == false) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Escriba la gestion a la que corresponde la publicacion", ""));
        }
    }

    public Boolean[] validaActualizarPrePublicado(Long numeroGaceta, String estadoBase) {
        Boolean[] validador = {false, false, false, true};
        validador[0] = buscarNumeroPrepublicado(numeroGaceta);
//        SigPublicacion publicacionExistente = sigPublicacionService.listar_SigPublicacion_numero_estado(numeroPrepublicacion, EnumEstadoPublicacion.PREPUBLICADO.getCodigo());
//        validador[2] = publicacionExistente == null;

        if (!estadoBase.equals(EnumEstadoPublicacion.ENVIADO.getCodigo())) {
            validador[1] = true;
        }
        if (!estadoBase.equals(EnumEstadoPublicacion.PUBLICADO.getCodigo())) {
            validador[2] = true;
        }
        for (int i = 0; i <= 2; i++) {

            if (!validador[i]) {
                validador[3] = false;
            }
        }
        return validador;
    }

    public void muestraMensajesValidadorPrePublicado(Boolean[] validador) {
        if (validador[0] == false) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El numero de gaceta no es correcto", ""));
        }
        if (validador[1] == false) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "No es posible volver al estado Prepublicado", ""));
        }
        if (validador[2] == false) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "No es posible volver al estado Prepublicado", ""));
        }
    }

    public Boolean[] validaActualizarPublicado(Long numeroGaceta, Date fechaEnvio, Date fechaPublicacion, String mes, Integer gestion, Long inicio) {
        Boolean[] validador = {false, false, false, false, false, false, false, false, true};
        validador[0] = !buscarNumeroPrepublicado(numeroGaceta);
        if (numeroGaceta != null) {
            validador[1] = true;
        }

        SigPublicacion publicacionExistente = sigPublicacionService.listar_SigPublicacion_numero_estado(numeroPrepublicacion, EnumEstadoPublicacion.PUBLICADO.getCodigo());
        validador[2] = publicacionExistente == null;

        if (fechaEnvio != null) {
            validador[3] = true;
        }

        if (fechaPublicacion != null) {
            validador[4] = true;
        }

        if (mes != null && !mes.equals("")) {
            validador[5] = true;
        }

        if (gestion != null) {
            validador[6] = true;
        }

        validador[7] = inicio != 0;

        for (int i = 0; i <= 7; i++) {
            //    System.out.println("VALIDADOR PARA PUBLICADO  " + validador[i]);
            if (!validador[i]) {
                validador[8] = false;
            }
        }
        return validador;
    }

    public void muestraMensajesValidadorPublicado(Boolean[] validador) {
        if (validador[0] == false) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El numero de gaceta no es correcto", ""));
        }
        if (validador[1] == false) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Escriba el numero de gaceta", ""));
        }
        if (validador[2] == false) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El numero de gaceta ya existe", ""));
        }
        if (validador[3] == false) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Seleccione fecha envio", ""));
        }
        if (validador[4] == false) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Seleccione fecha publicacion", ""));
        }
        if (validador[5] == false) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Escriba el mes al que corresponde la publicacion", ""));
        }
        if (validador[6] == false) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Escriba la gestion a la que corresponde la publicacion", ""));
        }
        if (validador[7] == false) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Debe seguir el proceso con el estado ENVIADO", ""));
        }
    }

    public void cambiaComboEstado() {
        switch (estadoPublicacion) {
            case "PPUB":

                fechaPublicacionDisabled = true;
                fechaEnvioDisabled = true;
                numeroGacetaDisabled = true;
                break;
            case "ENVI":

                fechaPublicacionDisabled = true;
                fechaEnvioDisabled = false;
                numeroGacetaDisabled = true;
                break;
            case "PUBL":

                fechaPublicacionDisabled = false;
                fechaEnvioDisabled = false;
                numeroGacetaDisabled = false;
                break;
            default:
                fechaPublicacionDisabled = false;
                fechaEnvioDisabled = false;
                break;

        }

    }

    public String devuelveEstadoPublicacion(String codigo) throws Exception {
        if (codigo != null && !codigo.equals("")) {
            return dominioService.obtenerListadoDominioXCodigo(EnumNombreDominio.ESTADO_PUBLICACION.getCodigo(), codigo).get(0).getNombre();
        }
        return "";
    }

    /**
     * metodo para borrado logico de sigdetallepublicacion y sigpublicacion
     *
     * Creado: Susana Escobar Fecha: 27/10/2016
     *
     * @param sigPublicacionEliminar
     */
    public void eliminarPrepublicacion(SigPublicacion sigPublicacionEliminar) throws Exception {

        if (sigPublicacionEliminar.getEstado_publicacion().equals(EnumEstadoPublicacion.PREPUBLICADO.getCodigo())) {
            Long inicio = sigPublicacionEliminar.getInicio() - 1;
            sigPublicacionService.eliminar_SigPublicacion_SigDetallePublicacion(sigPublicacionEliminar.getIdpublicacion());
            Correlativo correlativo = correlativoService.encuentraCorrelativoTabla(1L, "NPUB");
            correlativo.setUltimo_numero_asignado(inicio.intValue());
            correlativoService.crudCorrelativo(correlativo, 2);

            llenar();
            listaPrepublicacionDisponible();
        }
    }

    /**
     * metodo para el paso de parametros al reporte mediante la publicacion
     * seleccionada en la vista
     *
     * Creado: Ruben Ramirez Fecha: 15/10/2016
     *
     * @param publicacion
     * @throws net.sf.jasperreports.engine.JRException
     * @throws java.io.IOException
     */
    public void generarReporte(SigPublicacion publicacion) throws JRException, IOException, Exception {

        listaDetallePublicacion2 = sigDetallePublicacionService.listaSigDetallePublicacionXidpublicacion(publicacion.getIdpublicacion());

        if (listaDetallePublicacion2.size() > 0) {

            imgSenapi = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/images/logoNuevo.jpg");

            FacesContext facesContext = FacesContext.getCurrentInstance();
            ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();
            String rutaWar = servletContext.getRealPath(EnumCarpetasTemporales.CARPETA_LOGOTIPOS.getCodigo());

            File targetFolder = new File(rutaWar);

            if (!targetFolder.exists()) {
                targetFolder.mkdirs();
            }

            for (String item : seccionReport) {
                for (SigDetallePublicacion item1 : listaDetallePublicacion2) {
                    if (item.equals(item1.getSeccion())) {
                        for (String item2 : subSeccionReport) {
                            DatosTipoSignoReporBean datosTipoSigno = new DatosTipoSignoReporBean();
                            List<TramitesReporBean> listaDatosSigno = new ArrayList<TramitesReporBean>();
                            List<TramitesReporBean> listaDatosSignoMixto = new ArrayList<TramitesReporBean>();
                            int sw = 0;
                            for (SigDetallePublicacion item3 : listaDetallePublicacion2) {
                                if (item2.equals(item3.getSubseccion()) && item.equals(item3.getSeccion())) {
                                    sw = 1;
                                    datosTipoSigno.setNumeroSeccion(item1.getNumero_seccion());
                                    datosTipoSigno.setTipoSeccion(item1.getSeccion());
                                    datosTipoSigno.setTiposSignos(item3.getSubseccion());

                                    TramitesReporBean tramitesReporBean = new TramitesReporBean();
                                    tramitesReporBean.setNumeroPublicacion(item3.getNumero_publicacion());
                                    tramitesReporBean.setNombreSigno(item3.getMarca());
                                    tramitesReporBean.setGeneroSigno(item3.getTipo_genero());
                                    tramitesReporBean.setTipoSigno(item3.getTipo_signo_descripcion());
                                    tramitesReporBean.setNumeroSolicitud(item3.getSm_descripcion());
                                    tramitesReporBean.setFechaSolicitud(item3.getFecha_ingreso());

                                    if (item3.getNombre_titular() != null) {
                                        if (!item3.getNombre_titular().trim().equals("")) {
                                            tramitesReporBean.setNombreTitular(item3.getNombre_titular().replace("  ;", ";"));
                                        }
                                    }

                                    tramitesReporBean.setDireccionTitular(item3.getDireccion_titular());
                                    tramitesReporBean.setPaisTitular(item3.getPais_titular());
                                    tramitesReporBean.setDescripcionPaisTitular(item3.getDescripcion_pais_titular());

                                    if (item3.getNombre_apoderado() != null) {
                                        if (item3.getNombre_apoderado().trim().equals("")) {
                                            tramitesReporBean.setNombreApoderado("UNIPERSONAL");
                                        } else {
                                            tramitesReporBean.setNombreApoderado(item3.getNombre_apoderado().replace("  ;", ";"));
                                            tramitesReporBean.setDireccionApoderado(item3.getDireccion_apoderado());
                                        }
                                    } else {
                                        tramitesReporBean.setNombreApoderado("UNIPERSONAL");
                                    }

                                    if (item3.getPrioridad() != null) {
                                        if (!item3.getPrioridad().trim().equals("")) {
                                            tramitesReporBean.setPrioridad(item3.getPrioridad());
                                            if (item3.getFecha_prioridad() != null) {
                                                if (!item3.getFecha_prioridad().trim().equals("")) {
                                                    Date fechaPrioridad = ParseFecha(item3.getFecha_prioridad());
                                                    if (fechaPrioridad != null) {
                                                        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                                                        tramitesReporBean.setFechaPrioridad(formato.format(fechaPrioridad));
                                                    }
                                                }
                                            }
                                            tramitesReporBean.setPaisPrioridad(item3.getPais_prioridad());
                                            tramitesReporBean.setPaisPrioDescripcion(item3.getPais_prio_descripcion());
                                        }
                                    }

                                    tramitesReporBean.setClaseInternacional("" + item3.getClase());
                                    if (item3.getLista_productos() != null) {
                                        if (!item3.getLista_productos().trim().equals("")) {
                                            tramitesReporBean.setProductos(item3.getLista_productos().trim());
                                        }
                                    }
                                    if (item3.getDescripcion_signo() != null) {
                                        if (!item3.getDescripcion_signo().trim().equals("")) {
                                            tramitesReporBean.setDescripcionLogo(item3.getDescripcion_signo().trim());
                                        }
                                    }

                                    if (item2.equals("DENOMINATIVAS")) {
                                        listaDatosSigno.add(tramitesReporBean);
                                    } else {
                                        sigLogoTipo = sigLogoTipoService.obtenerRegistroPrincipalSigLogoTipoXIdSignoMarca(item3.getIdsignomarca());
                                        if (sigLogoTipo.getIdLogoTipo() != null) {
                                            sigImagen = sigImagenService.obtenerSigImagenXIdSigLogoTipo(sigLogoTipo.getIdLogoTipo());
                                            if (sigImagen.getImagen() != null) {
                                                byte[] fotoByte = sigImagen.getImagen();
                                                String imagenDibuja = rutaWar + File.separator + item3.getSm();
                                                crearArchivo(fotoByte, imagenDibuja);
                                                tramitesReporBean.setImgLogo(imagenDibuja);
                                            }
                                        }
                                        listaDatosSignoMixto.add(tramitesReporBean);
                                    }
                                }

                            }
                            if (listaDatosSigno.size() > 0) {
                                datosTipoSigno.setDatosDeTipoSigno(listaDatosSigno);
                            }
                            if (listaDatosSignoMixto.size() > 0) {
                                datosTipoSigno.setDatosDeTipoSignoMixto(listaDatosSignoMixto);
                            }
                            if (sw == 1) {
                                listaDatosTipoSignoReporBean.add(datosTipoSigno);
                            }
                        }
                        break;
                    }
                }
            }

            parametros.put("senapi", imgSenapi);
            parametros.put("datosDenominativas", getDatos(listaDatosTipoSignoReporBean));
            parametros.put("anioCaratula", "" + publicacion.getGestion());
            parametros.put("mesCaratula", publicacion.getMes());

            String filename = "Publicacion";
            String jasperPath = "/template/publicacion/reporPublicacion.jasper";
            this.PDFSD(parametros, jasperPath, filename);

            listaDatosTipoSignoReporBean.clear();

        }
    }

    public Date ParseFecha(String fecha) {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date fechaDate = null;
        try {
            fechaDate = formato.parse(fecha);
        } catch (ParseException ex) {
            // System.out.println(ex);
        }
        return fechaDate;
    }

    public JRDataSource getDatos(List<?> listaObjeto) {
        return new JRBeanCollectionDataSource(listaObjeto);
    }

    /**
     * Metodo para generar el reporte en formato pdf
     *
     * Creado: Ruben Ramirez Fecha: 15/10/2016
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
        response.addHeader("Content-Disposition", "inline; filename=" + fileName + ".pdf");
        ServletOutputStream stream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(print, stream);
        FacesContext.getCurrentInstance().responseComplete();
    }

    /**
     * Metodo para generar el reporte en formato pdf
     *
     * Creado: Ruben Ramirez Fecha: 15/10/2016
     *
     * @param params
     * @param jasperPath
     * @param fileName
     *
     * @throws net.sf.jasperreports.engine.JRException
     * @throws java.io.IOException
     */
    public void WORDSD(Map<String, Object> params, String jasperPath, String fileName) throws JRException, IOException {
        
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-Disposition", "attachment; filename=" + fileName + ".docx");
        ServletOutputStream out = response.getOutputStream();
        
        String relativeWebPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath(jasperPath);
        File file = new File(relativeWebPath);
        JasperPrint print = JasperFillManager.fillReport(file.getPath(), params, new JREmptyDataSource());

        JRDocxExporter exporter = new JRDocxExporter();
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, out);
        exporter.exportReport();

        FacesContext.getCurrentInstance().responseComplete();
    }

    /**
     * *
     * Metodo que crear un archivo temportal en la ruta indicada
     *
     * Creado: Ruben Ramirez Fecha: 17/11/2016
     *
     * @param bytes
     * @param archivo
     *
     * @return
     */
    private void crearArchivo(byte[] bytes, String archivo) throws FileNotFoundException, IOException {
        File f = new File(archivo);
        if (!f.exists()) {
            BufferedImage image = null;
            try {
                InputStream in = new ByteArrayInputStream(bytes);
                MemoryCacheSeekableStream mc = new MemoryCacheSeekableStream(in);
                image = JAI.create("stream", mc).getAsBufferedImage();

            } catch (Exception e) {
                e.printStackTrace();
            }
            ImageIO.write(image, "jpg", new File(archivo));
        }
    }

    //////////////////////////////GET SET/////////////////////////
    public SigPublicacion getPublicacionSeleccionada() {
        return publicacionSeleccionada;
    }

    public void setPublicacionSeleccionada(SigPublicacion publicacionSeleccionada) {
        this.publicacionSeleccionada = publicacionSeleccionada;
    }

    public Date getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List<SigPublicacion> getListaPublicacion() {
        return listaPublicacion;
    }

    public void setListaPublicacion(List<SigPublicacion> listaPublicacion) {
        this.listaPublicacion = listaPublicacion;
    }

    public List<SigDetallePublicacion> getListaDetallePublicacion() {
        return listaDetallePublicacion;
    }

    public void setListaDetallePublicacion(List<SigDetallePublicacion> listaDetallePublicacion) {
        this.listaDetallePublicacion = listaDetallePublicacion;
    }

    public String getLabelDialogo() {
        return labelDialogo;
    }

    public void setLabelDialogo(String labelDialogo) {
        this.labelDialogo = labelDialogo;
    }

    public List<Integer> getListadoGestiones() {
        return listadoGestiones;
    }

    public void setListadoGestiones(List<Integer> listadoGestiones) {
        this.listadoGestiones = listadoGestiones;
    }

    public Integer getGestionGenerar() {
        return gestionGenerar;
    }

    public void setGestionGenerar(Integer gestionGenerar) {
        this.gestionGenerar = gestionGenerar;
    }

    public Long getNumeroPrepublicacion() {
        return numeroPrepublicacion;
    }

    public void setNumeroPrepublicacion(Long numeroPrepublicacion) {
        this.numeroPrepublicacion = numeroPrepublicacion;
    }

    public String getCorresponde() {
        return corresponde;
    }

    public void setCorresponde(String corresponde) {
        this.corresponde = corresponde;
    }

    public DominioService getDominioService() {
        return dominioService;
    }

    public void setDominioService(DominioService dominioService) {
        this.dominioService = dominioService;
    }

    public SigPublicacionService getSigPublicacionService() {
        return sigPublicacionService;
    }

    public void setSigPublicacionService(SigPublicacionService sigPublicacionService) {
        this.sigPublicacionService = sigPublicacionService;
    }

    public SigDetallePublicacionService getSigDetallePublicacionService() {
        return sigDetallePublicacionService;
    }

    public void setSigDetallePublicacionService(SigDetallePublicacionService sigDetallePublicacionService) {
        this.sigDetallePublicacionService = sigDetallePublicacionService;
    }

    public SigDetallePublicacion getDetallePublicacionSelect() {
        return detallePublicacionSelect;
    }

    public void setDetallePublicacionSelect(SigDetallePublicacion detallePublicacionSelect) {
        this.detallePublicacionSelect = detallePublicacionSelect;
    }

    public List<Dominio> getListaEstadoPublicacion() throws Exception {
        return listaEstadoPublicacion = dominioService.obtenerListadoDominio("estado_publicacion");
    }

    public void setListaEstadoPublicacion(List<Dominio> listaEstadoPublicacion) {
        this.listaEstadoPublicacion = listaEstadoPublicacion;
    }

    public String getObservacionDetallePublicacion() {
        return observacionDetallePublicacion;
    }

    public void setObservacionDetallePublicacion(String observacionDetallePublicacion) {
        this.observacionDetallePublicacion = observacionDetallePublicacion;
    }

    public ComunService getComunService() {
        return comunService;
    }

    public void setComunService(ComunService comunService) {
        this.comunService = comunService;
    }

    public Long getNumeroGaceta() {
        return numeroGaceta;
    }

    public void setNumeroGaceta(Long numeroGaceta) {
        this.numeroGaceta = numeroGaceta;
    }

    public String getEstadoPublicacion() {
        return estadoPublicacion;
    }

    public void setEstadoPublicacion(String estadoPublicacion) {
        this.estadoPublicacion = estadoPublicacion;
    }

    public Date getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaEnvio(Date fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }

    public Date getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(Date fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public Integer getGestion() {
        return gestion;
    }

    public void setGestion(Integer gestion) {
        this.gestion = gestion;
    }

    public Boolean getDevolverDisabled() {
        return devolverDisabled;
    }

    public void setDevolverDisabled(Boolean devolverDisabled) {
        this.devolverDisabled = devolverDisabled;
    }

    public Boolean getFechaPublicacionDisabled() {
        return fechaPublicacionDisabled;
    }

    public void setFechaPublicacionDisabled(Boolean fechaPublicacionDisabled) {
        this.fechaPublicacionDisabled = fechaPublicacionDisabled;
    }

    public Boolean getFechaEnvioDisabled() {
        return fechaEnvioDisabled;
    }

    public void setFechaEnvioDisabled(Boolean fechaEnvioDisabled) {
        this.fechaEnvioDisabled = fechaEnvioDisabled;
    }

    public Boolean getEstadoPublicacionDisabled() {
        return estadoPublicacionDisabled;
    }

    public void setEstadoPublicacionDisabled(Boolean estadoPublicacionDisabled) {
        this.estadoPublicacionDisabled = estadoPublicacionDisabled;
    }

    public Boolean getNumeroGacetaDisabled() {
        return numeroGacetaDisabled;
    }

    public void setNumeroGacetaDisabled(Boolean numeroGacetaDisabled) {
        this.numeroGacetaDisabled = numeroGacetaDisabled;
    }

    public Map<String, Object> getParametros() {
        return parametros;
    }

    public void setParametros(Map<String, Object> parametros) {
        this.parametros = parametros;
    }

    public List<SigDetallePublicacion> getListaDetallePublicacion2() {
        return listaDetallePublicacion2;
    }

    public void setListaDetallePublicacion2(List<SigDetallePublicacion> listaDetallePublicacion2) {
        this.listaDetallePublicacion2 = listaDetallePublicacion2;
    }

    public List<DatosTipoSignoReporBean> getListaDatosTipoSignoReporBean() {
        return listaDatosTipoSignoReporBean;
    }

    public void setListaDatosTipoSignoReporBean(List<DatosTipoSignoReporBean> listaDatosTipoSignoReporBean) {
        this.listaDatosTipoSignoReporBean = listaDatosTipoSignoReporBean;
    }

    public SeccionSubPublicacionService getSeccionSubPublicacionService() {
        return seccionSubPublicacionService;
    }

    public void setSeccionSubPublicacionService(SeccionSubPublicacionService seccionSubPublicacionService) {
        this.seccionSubPublicacionService = seccionSubPublicacionService;
    }

    public List<String> getSeccionReport() {
        return seccionReport;
    }

    public void setSeccionReport(List<String> seccionReport) {
        this.seccionReport = seccionReport;
    }

    public List<String> getSubSeccionReport() {
        return subSeccionReport;
    }

    public void setSubSeccionReport(List<String> subSeccionReport) {
        this.subSeccionReport = subSeccionReport;
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

    public SigPublicacion getPublicacionActualizar() {
        return publicacionActualizar;
    }

    public void setPublicacionActualizar(SigPublicacion publicacionActualizar) {
        this.publicacionActualizar = publicacionActualizar;
    }

    public SigLogoTipo getSigLogoTipo() {
        return sigLogoTipo;
    }

    public void setSigLogoTipo(SigLogoTipo sigLogoTipo) {
        this.sigLogoTipo = sigLogoTipo;
    }

    public SigImagen getSigImagen() {
        return sigImagen;
    }

    public void setSigImagen(SigImagen sigImagen) {
        this.sigImagen = sigImagen;
    }

    public byte[] getBlobasbytes() {
        return blobasbytes;
    }

    public void setBlobasbytes(byte[] blobasbytes) {
        this.blobasbytes = blobasbytes;
    }

    public HistorialService getHistorialService() {
        return historialService;
    }

    public void setHistorialService(HistorialService historialService) {
        this.historialService = historialService;
    }

    public List<Integer> getListaNumeroPrepublicacionCmb() {
        return listaNumeroPrepublicacionCmb;
    }

    public void setListaNumeroPrepublicacionCmb(List<Integer> listaNumeroPrepublicacionCmb) {
        this.listaNumeroPrepublicacionCmb = listaNumeroPrepublicacionCmb;
    }

    public SeguimientoService getSeguimientoService() {
        return seguimientoService;
    }

    public void setSeguimientoService(SeguimientoService seguimientoService) {
        this.seguimientoService = seguimientoService;
    }

    public CorrelativoService getCorrelativoService() {
        return correlativoService;
    }

    public void setCorrelativoService(CorrelativoService correlativoService) {
        this.correlativoService = correlativoService;
    }

}
