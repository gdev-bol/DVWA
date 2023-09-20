/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.entidades.bean.publicacion;

import com.sun.media.jai.codec.MemoryCacheSeekableStream;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.text.DateFormatSymbols;
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
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;
import javax.media.jai.JAI;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import snp.gob.bo.entidades.bean.common.AbstractManagedBean;
import snp.gob.bo.entidades.bean.common.SessionStateManagedBean;
import snp.gob.bo.gimodel.bdimagen.entidad.SigImagen;
import snp.gob.bo.gimodel.bdimagen.entidad.SigLogoTipo;
import snp.gob.bo.gimodel.bdimagen.servicio.SigImagenService;
import snp.gob.bo.gimodel.bdimagen.servicio.SigLogoTipoService;
import snp.gob.bo.gimodel.entidad.SigDetallePublicacion;
import snp.gob.bo.gimodel.entidad.SigPublicacion;
import snp.gob.bo.gimodel.enums.EnumCarpetasTemporales;
import snp.gob.bo.gimodel.servicio.ComunService;
import snp.gob.bo.gimodel.servicio.DominioService;
import snp.gob.bo.gimodel.servicio.SeccionSubPublicacionService;
import snp.gob.bo.gimodel.servicio.SigDetallePublicacionService;
import snp.gob.bo.gimodel.servicio.SigPublicacionService;

/**
 *
 * @author susana
 */
@ManagedBean(name = "buscadorPublicacionBean")
@ViewScoped
public class BuscadorPublicacionBean extends AbstractManagedBean implements Serializable {

    //<editor-fold defaultstate="collapsed" desc="Definicion de servicios y atributos">
    // variables de la vista
    private Long numeroGaceta;
    private Boolean reporteRecibo = false;

    // parametros del reporte
    private Map<String, Object> parametros = new HashMap<String, Object>();
    private List<SigDetallePublicacion> listaDetallePublicacion = new ArrayList<SigDetallePublicacion>();
    private List<DetalleListPublicacionBean> listaDetalleListPublicacionBean = new ArrayList<DetalleListPublicacionBean>();
    private List<DatosTipoSignoReporBean> listaDatosTipoSignoReporBean = new ArrayList<DatosTipoSignoReporBean>();
    private List<String> seccionReport = new ArrayList<String>();
    private List<String> subSeccionReport = new ArrayList<String>();
    private SigPublicacion sigPublicacion = new SigPublicacion();
    private SigLogoTipo sigLogoTipo = new SigLogoTipo();
    private SigImagen sigImagen = new SigImagen();

    private String imagenSenapi = "";
    private String nroGaceta = "";
    private String total = "";
    private String fechaPublicacion = "";
    private String mesTitulo = "";
    private String anioTitulo = "";
    private String fechaIngreso = "";
    private String horaIngreso = "";
    private String imgSenapi;

    @ManagedProperty(value = "#{sessionState}")
    private SessionStateManagedBean sessionManagedBean;

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

    /**
     * Creates a new instance of BuscadorPublicacionBean
     */
    public BuscadorPublicacionBean() {
    }

    @PostConstruct
    public void BuscadorPublicacionBeanInit() {

        try {
            // lista de seccion y subseccion
            seccionReport = seccionSubPublicacionService.listadoSecciones();
            subSeccionReport = seccionSubPublicacionService.listadoSubSecciones();
            Set<String> s = new LinkedHashSet<String>(subSeccionReport);
            subSeccionReport.clear();
            subSeccionReport.addAll(s);
        } catch (Exception ex) {
            Logger.getLogger(BuscadorPublicacionBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * metodo para generacion de la gaceta de publicaciones de acuerdo al numero
     * de gaceta.
     *
     * Creado: Ruben Ramirez Fecha: 15/11/2016
     *
     * @throws net.sf.jasperreports.engine.JRException
     * @throws java.io.IOException
     */
    public void imprimirGaceta() throws JRException, IOException, Exception {

        listaDatosTipoSignoReporBean.clear();

        sigPublicacion = sigPublicacionService.obtenerIdPublicacionXNumeroGaceta(numeroGaceta);

        if (sigPublicacion != null) {
            reporteRecibo = true;

            listaDetallePublicacion = sigDetallePublicacionService.listaSigDetallePublicacionXidpublicacion(sigPublicacion.getIdpublicacion());

            if (listaDetallePublicacion.size() > 0) {

                imgSenapi = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/images/logoNuevo.jpg");

                FacesContext facesContext = FacesContext.getCurrentInstance();
                ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();
                String rutaWar = servletContext.getRealPath(EnumCarpetasTemporales.CARPETA_LOGOTIPOS.getCodigo());

                File targetFolder = new File(rutaWar);

                if (!targetFolder.exists()) {
                    targetFolder.mkdirs();
                }

                for (String item : seccionReport) {
                    for (SigDetallePublicacion item1 : listaDetallePublicacion) {
                        if (item.equals(item1.getSeccion())) {
                            for (String item2 : subSeccionReport) {
                                DatosTipoSignoReporBean datosTipoSigno = new DatosTipoSignoReporBean();
                                List<TramitesReporBean> listaDatosSigno = new ArrayList<>();
                                List<TramitesReporBean> listaDatosSignoMixto = new ArrayList<>();
                                int sw = 0;
                                for (SigDetallePublicacion item3 : listaDetallePublicacion) {
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
                parametros.put("anioCaratula", "" + sigPublicacion.getGestion());
                parametros.put("mesCaratula", sigPublicacion.getMes());

                String filename = "Publicacion.pdf";
                String jasperPath = "/template/publicacion/reporPublicacion.jasper";
                this.generateReport(parametros, jasperPath, filename);

            }
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

     /**
     * metodo para generacion de la lista de publicaciones de acuerdo al numero
     * de gaceta.
     *
     * Creado: Ruben Ramirez Fecha: 14/11/2016
     *
     * @throws net.sf.jasperreports.engine.JRException
     * @throws java.io.IOException
     */
    public void imprimirLista() throws JRException, IOException, Exception {

        listaDetalleListPublicacionBean.clear();

        sigPublicacion = sigPublicacionService.obtenerIdPublicacionXNumeroGaceta(numeroGaceta);

        if (sigPublicacion != null) {
            reporteRecibo = true;

            imagenSenapi = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/images/logoNuevo.jpg");

            nroGaceta = "" + numeroGaceta;

            total = "" + sigPublicacion.getTotal();

            mesTitulo = sigPublicacion.getMes();

            anioTitulo = "" + sigPublicacion.getGestion();

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
                fechaIngreso = formateador1.format(fechaPresente);
                horaIngreso = formateador2.format(fechaPresente);
            }

            if (sigPublicacion.getFecha_publicacion() != null) {
                fechaPublicacion = formateador1.format(sigPublicacion.getFecha_publicacion());
            } else {
                fechaPublicacion = formateador1.format(fechaPresente);
            }
            
            listaDetallePublicacion = sigDetallePublicacionService.listaSigDetallePublicacionXidpublicacion(sigPublicacion.getIdpublicacion());

            int i = 0;
            if (listaDetallePublicacion.size() > 0) {
                for (String item : seccionReport) {
                    for (SigDetallePublicacion item1 : listaDetallePublicacion) {
                        if (item.equals(item1.getSeccion())) {
                            for (String item2 : subSeccionReport) {
                                for (SigDetallePublicacion item3 : listaDetallePublicacion) {
                                    if (item2.equals(item3.getSubseccion()) && item.equals(item3.getSeccion())) {
                                        i++;
                                        DetalleListPublicacionBean dpb = new DetalleListPublicacionBean();
                                        HashMap mapResultado = comunService.obtenerNumeroGestionExtensionCodigoSM(item3.getSm());
                                        String valorNumeroExpediente = mapResultado.get("numero").toString();
                                        String valorGestionExpediente = mapResultado.get("gestion").toString();
                                        String valorExtensionExpediente = mapResultado.get("extension").toString();
                                        dpb.setItem(i);
                                        dpb.setSm(valorNumeroExpediente + valorExtensionExpediente + " - " + valorGestionExpediente);
                                        dpb.setNombreMarca(item3.getMarca());
                                        dpb.setClase(item3.getClase());
                                        if (item3.getFecha_ingreso() != null) {
                                            dpb.setFechaSolicitud(formateador3.format(item3.getFecha_ingreso()));
                                        }
                                        dpb.setSeccion(item3.getSeccion());
                                        dpb.setTipoSigno(item3.getSubseccion());
                                        dpb.setPublicacion(item3.getNumero_publicacion());

                                        listaDetalleListPublicacionBean.add(dpb);
                                    }
                                }
                            }
                            break;
                        }
                    }
                }

                JRBeanCollectionDataSource itemsJRBean = new JRBeanCollectionDataSource(listaDetalleListPublicacionBean);

                parametros.put("imageSenapi", imagenSenapi);
                parametros.put("nroGaceta", nroGaceta);
                parametros.put("total", total);
                parametros.put("fechaPublicacion", fechaPublicacion);
                parametros.put("mesTitulo", mesTitulo);
                parametros.put("anioTitulo", anioTitulo);
                parametros.put("fechaIngreso", fechaIngreso);
                parametros.put("horaIngreso", horaIngreso);
                parametros.put("detallePublicacion", itemsJRBean);

                String filename = "ListaPublicacion.pdf";
                String jasperPath = "/template/publicacion/reporListaPublicados.jasper";
                this.generateReport(parametros, jasperPath, filename);

            }

        }
    }

    /**
     * metodo para generacion de la separta de publicaciones de acuerdo al
     * numero de gaceta.
     *
     * Creado: Ruben Ramirez Fecha: 15/11/2016
     *
     * @throws net.sf.jasperreports.engine.JRException
     * @throws java.io.IOException
     */
    public void imprimirSeparata() throws JRException, IOException, Exception {

        listaDatosTipoSignoReporBean.clear();

        sigPublicacion = sigPublicacionService.obtenerIdPublicacionXNumeroGaceta(numeroGaceta);

        if (sigPublicacion != null) {
            reporteRecibo = true;

            listaDetallePublicacion = sigDetallePublicacionService.listaSigDetallePublicacionXidpublicacion(sigPublicacion.getIdpublicacion());

            if (listaDetallePublicacion.size() > 0) {

                FacesContext facesContext = FacesContext.getCurrentInstance();
                ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();
                String rutaWar = servletContext.getRealPath(EnumCarpetasTemporales.CARPETA_LOGOTIPOS.getCodigo());

                File targetFolder = new File(rutaWar);

                if (!targetFolder.exists()) {
                    targetFolder.mkdirs();
                }

                for (String item : seccionReport) {
                    for (SigDetallePublicacion item1 : listaDetallePublicacion) {
                        if (item.equals(item1.getSeccion())) {
                            for (String item2 : subSeccionReport) {
                                DatosTipoSignoReporBean datosTipoSigno = new DatosTipoSignoReporBean();
                                List<TramitesReporBean> listaDatosSigno = new ArrayList<>();
                                List<TramitesReporBean> listaDatosSignoMixto = new ArrayList<>();
                                int sw = 0;
                                for (SigDetallePublicacion item3 : listaDetallePublicacion) {
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
                                                tramitesReporBean.setNombreTitular(item3.getNombre_titular().replace("  ", ""));
                                            }
                                        }

                                        tramitesReporBean.setDireccionTitular(item3.getDireccion_titular());
                                        tramitesReporBean.setPaisTitular(item3.getPais_titular());
                                        tramitesReporBean.setDescripcionPaisTitular(item3.getDescripcion_pais_titular());

                                        if (item3.getNombre_apoderado() != null) {
                                            if (item3.getNombre_apoderado().trim().equals("")) {
                                                tramitesReporBean.setNombreApoderado("UNIPERSONAL");
                                            } else {
                                                tramitesReporBean.setNombreApoderado(item3.getNombre_apoderado().replace("  ", ""));
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

                parametros.put("datosDenominativas", getDatos(listaDatosTipoSignoReporBean));
                parametros.put("mesGaceta", "" + sigPublicacion.getMes());
                parametros.put("anioGaceta", "" + sigPublicacion.getGestion());
                parametros.put("nroGaceta", "" + sigPublicacion.getNumero_gaceta());
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

                String fechaPubli = "";

                if (sigPublicacion.getFecha_publicacion() != null) {
                    SimpleDateFormat formateador1 = new SimpleDateFormat("EEEEE, dd MMMM, yyyy", dateFormatSymbols);
                    fechaPubli = formateador1.format(sigPublicacion.getFecha_publicacion());
                }

                parametros.put("fechaPublicacion", fechaPubli);
                String filename = "Separata.pdf";
                String jasperPath = "/template/publicacion/reporSeparata.jasper";
                this.generateReport(parametros, jasperPath, filename);

            }
        }

    }

    /**
     * Metodo para generar el reporte y pasarle en formato StreamContente a la
     * variable de sesion stream. Para posterior despliegue en la vista.
     *
     * Creado: Ruben Ramirez Fecha: 15/11/2016
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
        sessionManagedBean.setStream(getStreamContentFromOutputStream(outputStream, "application/pdf", fileName));

    }

    /**
     * Metodo para generar el reporte en formato ByteArrayOutputStream.
     *
     * Creado: Ruben Ramirez Fecha: 215/11/2016
     *
     * @param map
     * @param pathJasper
     *
     * @return ByteArrayOutputStream
     * @throws java.lang.Exception
     */
    public static ByteArrayOutputStream getOutputStreamFromReport(Map<String, Object> map, String pathJasper) throws Exception {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        JasperPrint jp = JasperFillManager.fillReport(pathJasper, map, new JREmptyDataSource());
        JasperExportManager.exportReportToPdfStream(jp, os);
        os.flush();
        os.close();
        return os;
    }

    /**
     * Metodo para generar el reporte en formato StreamedContent.
     *
     * Creado: Ruben Ramirez Fecha: 15/11/2016
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

    /**
     * *
     * Metodo que crear un archivo temportal en la ruta indicada
     *
     * Creado: Ruben Ramirez Fecha: 18/11/2016
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
    
    public JRDataSource getDatos(List<?> listaObjeto) {
        return new JRBeanCollectionDataSource(listaObjeto);
    }

    public Long getNumeroGaceta() {
        return numeroGaceta;
    }

    public void setNumeroGaceta(Long numeroGaceta) {
        this.numeroGaceta = numeroGaceta;
    }

    public Boolean getReporteRecibo() {
        return reporteRecibo;
    }

    public void setReporteRecibo(Boolean reporteRecibo) {
        this.reporteRecibo = reporteRecibo;
    }

    public Map<String, Object> getParametros() {
        return parametros;
    }

    public void setParametros(Map<String, Object> parametros) {
        this.parametros = parametros;
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

    public String getImagenSenapi() {
        return imagenSenapi;
    }

    public void setImagenSenapi(String imagenSenapi) {
        this.imagenSenapi = imagenSenapi;
    }

    public String getNroGaceta() {
        return nroGaceta;
    }

    public void setNroGaceta(String nroGaceta) {
        this.nroGaceta = nroGaceta;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(String fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public String getMesTitulo() {
        return mesTitulo;
    }

    public void setMesTitulo(String mesTitulo) {
        this.mesTitulo = mesTitulo;
    }

    public String getAnioTitulo() {
        return anioTitulo;
    }

    public void setAnioTitulo(String anioTitulo) {
        this.anioTitulo = anioTitulo;
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

    public SessionStateManagedBean getSessionManagedBean() {
        return sessionManagedBean;
    }

    public void setSessionManagedBean(SessionStateManagedBean sessionManagedBean) {
        this.sessionManagedBean = sessionManagedBean;
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

    public ComunService getComunService() {
        return comunService;
    }

    public void setComunService(ComunService comunService) {
        this.comunService = comunService;
    }

    public SeccionSubPublicacionService getSeccionSubPublicacionService() {
        return seccionSubPublicacionService;
    }

    public void setSeccionSubPublicacionService(SeccionSubPublicacionService seccionSubPublicacionService) {
        this.seccionSubPublicacionService = seccionSubPublicacionService;
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

    public SigPublicacion getSigPublicacion() {
        return sigPublicacion;
    }

    public void setSigPublicacion(SigPublicacion sigPublicacion) {
        this.sigPublicacion = sigPublicacion;
    }

    public List<SigDetallePublicacion> getListaDetallePublicacion() {
        return listaDetallePublicacion;
    }

    public void setListaDetallePublicacion(List<SigDetallePublicacion> listaDetallePublicacion) {
        this.listaDetallePublicacion = listaDetallePublicacion;
    }

    public List<DetalleListPublicacionBean> getListaDetalleListPublicacionBean() {
        return listaDetalleListPublicacionBean;
    }

    public void setListaDetalleListPublicacionBean(List<DetalleListPublicacionBean> listaDetalleListPublicacionBean) {
        this.listaDetalleListPublicacionBean = listaDetalleListPublicacionBean;
    }

    public List<DatosTipoSignoReporBean> getListaDatosTipoSignoReporBean() {
        return listaDatosTipoSignoReporBean;
    }

    public void setListaDatosTipoSignoReporBean(List<DatosTipoSignoReporBean> listaDatosTipoSignoReporBean) {
        this.listaDatosTipoSignoReporBean = listaDatosTipoSignoReporBean;
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

    public String getImgSenapi() {
        return imgSenapi;
    }

    public void setImgSenapi(String imgSenapi) {
        this.imgSenapi = imgSenapi;
    }
    
}
