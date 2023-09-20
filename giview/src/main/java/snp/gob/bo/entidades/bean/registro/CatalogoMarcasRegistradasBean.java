/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.entidades.bean.registro;

import com.sun.media.jai.codec.MemoryCacheSeekableStream;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
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
import javax.imageio.ImageIO;
import javax.media.jai.JAI;
import javax.servlet.ServletContext;
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
import snp.gob.bo.gimodel.bdimagen.entidad.SigImagen;
import snp.gob.bo.gimodel.bdimagen.entidad.SigLogoTipo;
import snp.gob.bo.gimodel.bdimagen.servicio.SigImagenService;
import snp.gob.bo.gimodel.bdimagen.servicio.SigLogoTipoService;
import snp.gob.bo.gimodel.entidad.SigRegistro;
import snp.gob.bo.gimodel.enums.EnumCarpetasTemporales;
import snp.gob.bo.gimodel.servicio.ComunService;
import snp.gob.bo.gimodel.servicio.SigRegistroService;

/**
 *
 * @author Ruben Ramirez
 */
@ManagedBean(name = "catalogoMarcasRegistradasBean")
@ViewScoped
public class CatalogoMarcasRegistradasBean extends AbstractManagedBean implements Serializable {

    //<editor-fold defaultstate="collapsed" desc="Definicion de servicios y atributos">
    // variables de la vista
    private int gestion;
    private int mes;
    private Boolean reporteRecibo = false;

    // parametros del reporte
    private Map<String, Object> parametros = new HashMap();
    private List<CatalogoMesPojo> listaCatalogoMes = new ArrayList();
    private SigLogoTipo sigLogoTipo = new SigLogoTipo();
    private SigImagen sigImagen = new SigImagen();
    private String senapi;

    private List<SigRegistro> listaSigRegistro = new ArrayList();

    @ManagedProperty("#{sigLogoTipoService}")
    private SigLogoTipoService sigLogoTipoService;

    @ManagedProperty("#{sigImagenService}")
    private SigImagenService sigImagenService;

    @ManagedProperty("#{sigRegistroService}")
    private SigRegistroService sigRegistroService;

    @ManagedProperty("#{comunService}")
    private ComunService comunService;

    /**
     * Creates a new instance of BuscadorPublicacionBean
     */
    public CatalogoMarcasRegistradasBean() {
    }

    @PostConstruct
    public void CatalogoMarcasRegistradasBeanInit() {
        try {

            SimpleDateFormat formateador1 = new SimpleDateFormat("MM");
            SimpleDateFormat formateador2 = new SimpleDateFormat("yyyy");

            // fecha actual de la base de datos
            Date fechaPresente = comunService.obtenerFechaHoraServidor(1L);

            if (fechaPresente != null) {
                mes = Integer.parseInt(formateador1.format(fechaPresente));
                gestion = Integer.parseInt(formateador2.format(fechaPresente));
            }

        } catch (Exception ex) {
            Logger.getLogger(CatalogoMarcasRegistradasBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /*
     * Metodo para convertir un numero entre 1 a 12 a mes en literal.
     *
     * Creado: Ruben Ramirez Fecha: 04/01/2017
     * @param meNumeral
     * @return 
     */
    public String mesLiteral(int meNumeral) {
        String mesLiteral = "";
        switch (meNumeral) {
            case 1:
                mesLiteral = "enero";
                break;
            case 2:
                mesLiteral = "febrero";
                break;
            case 3:
                mesLiteral = "marzo";
                break;
            case 4:
                mesLiteral = "abril";
                break;
            case 5:
                mesLiteral = "mayo";
                break;
            case 6:
                mesLiteral = "junio";
                break;
            case 7:
                mesLiteral = "julio";
                break;
            case 8:
                mesLiteral = "agosto";
                break;
            case 9:
                mesLiteral = "septiembre";
                break;
            case 10:
                mesLiteral = "octubre";
                break;
            case 11:
                mesLiteral = "noviembre";
                break;
            case 12:
                mesLiteral = "diciembre";
                break;
        }
        return mesLiteral;
    }

    /**
     * Metodo para generacion del catalogo de marcas registradas de acuerda a la
     * gestion y el mes seleccionado.
     *
     * Creado: Ruben Ramirez Fecha: 04/01/2017
     *
     * @throws net.sf.jasperreports.engine.JRException
     * @throws java.io.IOException
     */
    public void imprimirCatalogo() throws JRException, IOException, Exception {
        reporteRecibo = false;

        if (gestion > 0) {

            FacesContext facesContext = FacesContext.getCurrentInstance();
            ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();
            String rutaWar = servletContext.getRealPath(EnumCarpetasTemporales.CARPETA_LOGOTIPOS.getCodigo());

            File targetFolder = new File(rutaWar);

            if (!targetFolder.exists()) {
                targetFolder.mkdirs();
            }

            SimpleDateFormat formateador1 = new SimpleDateFormat("h:mm:ss");
            SimpleDateFormat formateador2 = new SimpleDateFormat("dd/MM/yyyy");

            if (mes == 0) {

                for (int i = 1; i <= 12; i++) {
                    listaSigRegistro = sigRegistroService.listar_sigRegistro_gestion_mes(gestion, i);

                    if (listaSigRegistro.size() > 0) {
                        reporteRecibo = true;
                        CatalogoMesPojo catalogomes = new CatalogoMesPojo();
                        catalogomes.setMesGestion(mesLiteral(i) + " " + gestion);

                        List<CatalogoDetallePojo> listaCatalogoDetallePojo = new ArrayList();

                        for (SigRegistro item : listaSigRegistro) {

                            CatalogoDetallePojo catalogoDetalle = new CatalogoDetallePojo();

                            catalogoDetalle.setNroRegistro(item.getNumeroRegistro() + " " + item.getSerie());
                            catalogoDetalle.setResolucion(item.getNumeroResolucion() + "/" + item.getGestionResolucion());
                            if (item.getFechaRegistro() != null) {
                                catalogoDetalle.setFechaRegistro(formateador2.format(item.getFechaRegistro()));
                            }
                            catalogoDetalle.setSigno(item.getSigno());
                            catalogoDetalle.setTipoMarca(item.getTipoSignoDescripcion());
                            catalogoDetalle.setGenero(item.getTipoGeneroDescripcion());
                            catalogoDetalle.setNroSolicitud(item.getSmDescripcion());
                            if (item.getFechaIngreso() != null) {
                                catalogoDetalle.setFechaSolicitud(formateador2.format(item.getFechaIngreso()));
                                catalogoDetalle.setHoraSolicitud(formateador1.format(item.getFechaIngreso()));
                            }
                            catalogoDetalle.setNroPublicacion(item.getNumeroPublicacion());
                            if (item.getFechaPublicacion() != null) {
                                catalogoDetalle.setFechaPublicacion(formateador2.format(item.getFechaPublicacion()));
                            }
                            catalogoDetalle.setNroGaceta(item.getNumeroGaceta());
                            catalogoDetalle.setTitular(item.getTitular());
                            catalogoDetalle.setPaisTitular(item.getPaisTitular());
                            catalogoDetalle.setDireccionTitular(item.getDireccionTitular());
                            if(item.getNombreApoderado()!=null){
                                if(!item.getNombreApoderado().isEmpty()){
                                    catalogoDetalle.setApoderado(item.getNombreApoderado());
                                }else{
                                    catalogoDetalle.setApoderado("UNIPERSONAL");
                                }
                            }else{
                                catalogoDetalle.setApoderado("UNIPERSONAL");
                            }                            
                            catalogoDetalle.setDireccionApoderado(item.getDireccionApoderado());
                            catalogoDetalle.setClase(item.getClase());
                            if (item.getListaProductos() != null) {
                                catalogoDetalle.setProducto(item.getListaProductos().trim());
                            }

                            sigLogoTipo = sigLogoTipoService.obtenerRegistroPrincipalSigLogoTipoXIdSignoMarca(item.getIdSignoMarca());
                            if (sigLogoTipo.getIdLogoTipo() != null) {
                                sigImagen = sigImagenService.obtenerSigImagenXIdSigLogoTipo(sigLogoTipo.getIdLogoTipo());
                                if (sigImagen.getImagen() != null) {
                                    byte[] fotoByte = sigImagen.getImagen();
                                    String imagenDibuja = rutaWar + File.separator + sigLogoTipo.getNombreArchivo();
                                    crearArchivo(fotoByte, imagenDibuja);
                                    catalogoDetalle.setLogo(imagenDibuja);
                                    catalogoDetalle.setConImagen(true);
                                }
                            } else {
                                catalogoDetalle.setConImagen(false);
                            }

                            listaCatalogoDetallePojo.add(catalogoDetalle);
                        }

                        catalogomes.setDatosCatalogoDetallePojo(listaCatalogoDetallePojo);

                        listaCatalogoMes.add(catalogomes);
                    }
                }
            } else {

                listaSigRegistro = sigRegistroService.listar_sigRegistro_gestion_mes(gestion, mes);

                if (listaSigRegistro.size() > 0) {
                    reporteRecibo = true;
                    CatalogoMesPojo catalogomes = new CatalogoMesPojo();
                    catalogomes.setMesGestion(mesLiteral(mes) + " " + gestion);

                    List<CatalogoDetallePojo> listaCatalogoDetallePojo = new ArrayList();

                    for (SigRegistro item : listaSigRegistro) {

                        CatalogoDetallePojo catalogoDetalle = new CatalogoDetallePojo();

                        catalogoDetalle.setNroRegistro(item.getNumeroRegistro() + " " + item.getSerie());
                        catalogoDetalle.setResolucion(item.getNumeroResolucion() + "/" + item.getGestionResolucion());
                        if (item.getFechaRegistro() != null) {
                            catalogoDetalle.setFechaRegistro(formateador2.format(item.getFechaRegistro()));
                        }
                        catalogoDetalle.setSigno(item.getSigno());
                        catalogoDetalle.setTipoMarca(item.getTipoSignoDescripcion());
                        catalogoDetalle.setGenero(item.getTipoGeneroDescripcion());
                        catalogoDetalle.setNroSolicitud(item.getSmDescripcion());
                        if (item.getFechaIngreso() != null) {
                            catalogoDetalle.setFechaSolicitud(formateador2.format(item.getFechaIngreso()));
                            catalogoDetalle.setHoraSolicitud(formateador1.format(item.getFechaIngreso()));
                        }
                        catalogoDetalle.setNroPublicacion(item.getNumeroPublicacion());
                        if (item.getFechaPublicacion() != null) {
                            catalogoDetalle.setFechaPublicacion(formateador2.format(item.getFechaPublicacion()));
                        }
                        catalogoDetalle.setNroGaceta(item.getNumeroGaceta());
                        catalogoDetalle.setTitular(item.getTitular());
                        catalogoDetalle.setPaisTitular(item.getPaisTitular());
                        catalogoDetalle.setDireccionTitular(item.getDireccionTitular());
                        if(item.getNombreApoderado()!=null){
                                if(!item.getNombreApoderado().isEmpty()){
                                    catalogoDetalle.setApoderado(item.getNombreApoderado());
                                }else{
                                    catalogoDetalle.setApoderado("UNIPERSONAL");
                                }
                            }else{
                                catalogoDetalle.setApoderado("UNIPERSONAL");
                            } 
                        catalogoDetalle.setDireccionApoderado(item.getDireccionApoderado());
                        catalogoDetalle.setClase(item.getClase());
                        if (item.getListaProductos() != null) {
                            catalogoDetalle.setProducto(item.getListaProductos().trim());
                        }

                        sigLogoTipo = sigLogoTipoService.obtenerRegistroPrincipalSigLogoTipoXIdSignoMarca(item.getIdSignoMarca());
                        if (sigLogoTipo.getIdLogoTipo() != null) {
                            sigImagen = sigImagenService.obtenerSigImagenXIdSigLogoTipo(sigLogoTipo.getIdLogoTipo());
                            if (sigImagen.getImagen() != null) {
                                byte[] fotoByte = sigImagen.getImagen();
                                String imagenDibuja = rutaWar + File.separator + sigLogoTipo.getNombreArchivo();
                                crearArchivo(fotoByte, imagenDibuja);
                                catalogoDetalle.setLogo(imagenDibuja);
                                catalogoDetalle.setConImagen(true);
                            }
                        } else {
                            catalogoDetalle.setConImagen(false);
                        }

                        listaCatalogoDetallePojo.add(catalogoDetalle);
                    }

                    catalogomes.setDatosCatalogoDetallePojo(listaCatalogoDetallePojo);

                    listaCatalogoMes.add(catalogomes);

                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "No se encontraron resultados.", ""));
                }
            }

            if (reporteRecibo) {
                senapi = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/images/senapi.png");
                parametros.put("senapi", senapi);
                parametros.put("gestion", gestion);
                parametros.put("datosCatalogoMes", getDatos(listaCatalogoMes));
                String filename = "Catalogo.pdf";
                String jasperPath = "/template/registro/catalogo.jasper";
                this.generateReport(parametros, jasperPath, filename);

                listaCatalogoMes.clear();
            }

        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Introdusca la gestion y mes.", ""));
        }
    }

    /**
     * Metodo para generar el reporte y pasarle en formato StreamContente a la
     * variable de sesion stream. Para posterior despliegue en la vista.
     *
     * Creado: Ruben Ramirez Fecha: 04/01/2017
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
     * Metodo para generar el reporte en formato ByteArrayOutputStream.
     *
     * Creado: Ruben Ramirez Fecha: 04/01/2017
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
     * Creado: Ruben Ramirez Fecha: 04/01/2017
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
     * Creado: Ruben Ramirez Fecha: 04/01/2017
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
                ImageIO.write(image, "jpg", new File(archivo));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public JRDataSource getDatos(List<?> listaObjeto) {
        return new JRBeanCollectionDataSource(listaObjeto);
    }

    public int getGestion() {
        return gestion;
    }

    public void setGestion(int gestion) {
        this.gestion = gestion;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
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

    public List<CatalogoMesPojo> getListaCatalogoMes() {
        return listaCatalogoMes;
    }

    public void setListaCatalogoMes(List<CatalogoMesPojo> listaCatalogoMes) {
        this.listaCatalogoMes = listaCatalogoMes;
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

    public String getSenapi() {
        return senapi;
    }

    public void setSenapi(String senapi) {
        this.senapi = senapi;
    }

    public List<SigRegistro> getListaSigRegistro() {
        return listaSigRegistro;
    }

    public void setListaSigRegistro(List<SigRegistro> listaSigRegistro) {
        this.listaSigRegistro = listaSigRegistro;
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

    public SigRegistroService getSigRegistroService() {
        return sigRegistroService;
    }

    public void setSigRegistroService(SigRegistroService sigRegistroService) {
        this.sigRegistroService = sigRegistroService;
    }

    public ComunService getComunService() {
        return comunService;
    }

    public void setComunService(ComunService comunService) {
        this.comunService = comunService;
    }

}
