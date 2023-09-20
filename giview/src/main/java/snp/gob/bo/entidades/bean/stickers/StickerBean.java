/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.entidades.bean.stickers;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.Code128Writer;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
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
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import snp.gob.bo.entidades.bean.common.AbstractManagedBean;
import snp.gob.bo.entidades.bean.common.SessionStateManagedBean;
import snp.gob.bo.gimodel.entidad.Dominio;
import snp.gob.bo.gimodel.entidad.Sticker;
import snp.gob.bo.gimodel.enums.EnumCarpetasTemporales;
import snp.gob.bo.gimodel.servicio.ComunService;
import snp.gob.bo.gimodel.servicio.DominioService;
import snp.gob.bo.gimodel.servicio.StickerService;
import snp.gob.bo.gimodel.servicio.UsuarioPaginaService;

/**
 *
 * @author Ruben Ramirez
 */
@ManagedBean(name = "stickerBean")

@ViewScoped
public class StickerBean extends AbstractManagedBean implements Serializable {

    //<editor-fold defaultstate="collapsed" desc="Definicion de servicios y atributos">
    // variables de la vista
    private Boolean reporteRecibo = false;
    private Date fechaActual = new Date();
    private String tipoTramite;
    private int gestion;
    private int nroInicial;
    private int nroFinal;
    private int razon;
    private List<Dominio> listDominioTipoTramite = new ArrayList<Dominio>();
    private Sticker sticker = null;
    private String valorRadio = "SIM";

    // parametros del reporte
    private Map<String, Object> parametros = new HashMap<String, Object>();
    private String titulo;
    private List<CodigoPojo> listaCodigo = new ArrayList<CodigoPojo>();

    @ManagedProperty(value = "#{usuarioPaginaService}")
    private UsuarioPaginaService usuarioPaginaService;
    @ManagedProperty(value = "#{dominioService}")
    private DominioService dominioService;
    @ManagedProperty(value = "#{comunService}")
    private ComunService comunService;
    @ManagedProperty(value = "#{sessionState}")
    private SessionStateManagedBean sessionManagedBean;
    @ManagedProperty(value = "#{stickerService}")
    private StickerService stickerService;

    /**
     * Creates a new instance of BuscadorPublicacionBean
     */
    public StickerBean() {
    }

    @PostConstruct
    public void ImprimirPeticionBeanInit() {

        try {
            fechaActual = comunService.obtenerFechaServidor(1L);
            listDominioTipoTramite = dominioService.obtenerListadoDominio("tipo_tramite_sticker");

        } catch (Exception ex) {
            Logger.getLogger(StickerBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * metodo para la busqueda del sticker de acuerdo a la gestion y el tipo de
     * tramite.
     *
     * Creado: Ruben Ramirez Fecha: 06/12/2016
     *
     */
    public void busqueda() {
        try {
            this.gestion = 0;
            this.nroFinal = 0;
            this.nroInicial = 0;
            this.sticker = null;
            reporteRecibo = false;
            if (fechaActual != null) {
                Calendar gregfech = new GregorianCalendar();
                gregfech.setTime(fechaActual);
                int anio = gregfech.get(Calendar.YEAR);

                sticker = stickerService.obtenerStickerXTipoTramiteYGestion(tipoTramite, anio);

                if (sticker != null) {
                    this.gestion = sticker.getGestion();
                    this.nroInicial = sticker.getUltimoNumeroAsignado() + 1;
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("No se encontro", "Ningun Resultado"));
                }
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Inserte la fecha", "Para realizar la busqueda"));
            }
        } catch (Exception ex) {
            Logger.getLogger(StickerBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void limpia() {
        this.tipoTramite = null;
        this.gestion = 0;
        this.nroFinal = 0;
        this.nroInicial = 0;
        this.sticker = null;
        reporteRecibo = false;
    }

    /**
     * Metodo para la generación del reporte de sticker segun el tipo de tramite
     * y la gestión.
     *
     * Creado: Ruben Ramirez Fecha: 05/12/2016
     *
     * @param tipo
     * @throws net.sf.jasperreports.engine.JRException
     * @throws java.io.IOException
     */
    public void imprimir(String tipo) throws JRException, IOException, Exception {
        
        reporteRecibo = false;
        
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();
        String rutaCarpeta = servletContext.getRealPath(EnumCarpetasTemporales.CARPETA_STICKERS.getCodigo());
        File carpeta = new File(rutaCarpeta);

        if (!carpeta.exists()) {
            carpeta.mkdirs();
        }
        File[] ficheros = carpeta.listFiles();
        for (int x = 0; x < ficheros.length; x++) {
            ficheros[x].delete();
        }

        sticker = stickerService.crudSticker(sticker, 2);

        if (sticker != null) {
            int fin = sticker.getUltimoNumeroAsignado();
            int cont = sticker.getUltimoNumeroAsignado().toString().length();
            
            String jasperPath = "";
            if (valorRadio.equals("SIM")) {
                jasperPath = "/template/stickers/stickerSimple.jasper";
                razon = 30;
            }
            if (valorRadio.equals("DOB")) {
                jasperPath = "/template/stickers/stickerDoble.jasper";
                razon = 15;
            }
            
            int inicial;
            int ultimo;
            
            if(cont >= 5){
                inicial = nroInicial % (int)Math.pow(10,cont-1);
                ultimo =  nroFinal % (int)Math.pow(10,cont-1);
            }else{
                inicial = nroInicial;
                ultimo = nroFinal;
            }

            if (nroInicial >= sticker.getPrimerNumeroAsignado()) {
                if (nroFinal > nroInicial) {
                    reporteRecibo = true;
                    if (nroFinal > fin) {
                        sticker.setUltimoNumeroAsignado(nroFinal);
                        sticker = stickerService.crudSticker(sticker, 2);
                    }
                    for (Dominio item : listDominioTipoTramite) {
                        if (sticker.getTipoTramite().equals(item.getCodigo())) {
                            titulo = item.getNombre() + "  -  " + item.getCodigo();
                            break;
                        }
                    }
                    if (cont < 5) {
                        cont = 5;
                    }
                    for (int i = nroInicial; i <= nroFinal; i++) {
                        CodigoPojo cp = new CodigoPojo();
                        cp.setCodigo(sticker.getTipoTramite() + " " + String.format("%" + cont + "s", "" + i).replace(' ', '0') + "-" + sticker.getGestion());
                        String imagenDibuja = rutaCarpeta + File.separator + cp.getCodigo();
                        this.creaCodigoBarras(cp.getCodigo(), imagenDibuja);
                        cp.setImagen(imagenDibuja);
                        listaCodigo.add(cp);
                    }

                    parametros.put("titulo", titulo);
                    parametros.put("ini", nroInicial);
                    parametros.put("fin", nroFinal);
                    parametros.put("razon", razon);
                    
                    if(tipo.equals("PDF")){
                        String filename = "Stickers.pdf";
                        this.generateReport(parametros, jasperPath, filename, listaCodigo); 
                    }
                    
                    if(tipo.equals("WORD")){
                        String filename = "Stickers.docx";
                        this.word(parametros, jasperPath, listaCodigo, filename); 
                    }
                    
                    listaCodigo.clear();
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Genere los stikcers", "El valor final tiene que ser mayor que el inicial"));
                }
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Genere los stikcers", "Introdusca un valor inicial y final validos"));
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Seleccione un tipo de tramite", "para generar los stickers."));
        }
    }

    public void creaCodigoBarras(String text, String pathname) throws WriterException, IOException {
        int width = 158;
        int height = 23;
        String imageFormat = "png"; // "jpeg" "gif" "tiff"
        BitMatrix bitMatrix = new Code128Writer().encode(text, BarcodeFormat.CODE_128, width, height);
        FileOutputStream outputStream = new FileOutputStream(new File(pathname));
        MatrixToImageWriter.writeToStream(bitMatrix, imageFormat, outputStream);
    }
    
    public void word(Map<String, Object> params, String jasperPath, List<?> dataSource, String fileName) throws JRException, IOException {
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-Disposition", "attachment; filename=" + fileName);
        ServletOutputStream out = response.getOutputStream();
        String relativeWebPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath(jasperPath);
        File file = new File(relativeWebPath);
        JRBeanCollectionDataSource source = new JRBeanCollectionDataSource(dataSource, false);
        JasperPrint print = JasperFillManager.fillReport(file.getPath(), params, source);

        JRDocxExporter exporterXls = new JRDocxExporter();
        exporterXls.setParameter(JRExporterParameter.JASPER_PRINT, print);
        exporterXls.setParameter(JRExporterParameter.OUTPUT_STREAM, out);
        exporterXls.exportReport();

        FacesContext.getCurrentInstance().responseComplete();
    }

    /**
     * Metodo para generar el reporte y pasarle en formato StreamContente a la
     * variable de sesion stream. Para posterior despliegue en la vista.
     *
     * Creado: Ruben Ramirez Fecha: 05/12/2016
     *
     * @param params
     * @param jasperPath
     * @param fileName
     * @param dataSource
     * @throws java.io.IOException
     * @throws net.sf.jasperreports.engine.JRException
     *
     */
    public void generateReport(Map<String, Object> params, String jasperPath, String fileName, List<?> dataSource) throws IOException, JRException, Exception {
        String relativeWebPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath(jasperPath);
        File file = new File(relativeWebPath);
        ByteArrayOutputStream outputStream = getOutputStreamFromReport(params, file.getPath(), dataSource);
        sessionManagedBean.setStream(getStreamContentFromOutputStream(outputStream, "application/pdf", fileName));

    }

    /**
     * Metodo para generar el reporte en formato ByteArrayOutputStream.
     *
     * Creado: Ruben Ramirez Fecha: 05/12/2016
     *
     * @param map
     * @param pathJasper
     * @param dataSource
     *
     * @return ByteArrayOutputStream
     * @throws java.lang.Exception
     */
    public static ByteArrayOutputStream getOutputStreamFromReport(Map<String, Object> map, String pathJasper, List<?> dataSource) throws Exception {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        JRBeanCollectionDataSource source = new JRBeanCollectionDataSource(dataSource, false);
        JasperPrint jp = JasperFillManager.fillReport(pathJasper, map, source);
        JasperExportManager.exportReportToPdfStream(jp, os);
        os.flush();
        os.close();
        return os;
    }

    /**
     * Metodo para generar el reporte en formato StreamedContent.
     *
     * Creado: Ruben Ramirez Fecha: 05/12/2016
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

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<CodigoPojo> getListaCodigo() {
        return listaCodigo;
    }

    public void setListaCodigo(List<CodigoPojo> listaCodigo) {
        this.listaCodigo = listaCodigo;
    }

    public UsuarioPaginaService getUsuarioPaginaService() {
        return usuarioPaginaService;
    }

    public void setUsuarioPaginaService(UsuarioPaginaService usuarioPaginaService) {
        this.usuarioPaginaService = usuarioPaginaService;
    }

    public DominioService getDominioService() {
        return dominioService;
    }

    public void setDominioService(DominioService dominioService) {
        this.dominioService = dominioService;
    }

    public ComunService getComunService() {
        return comunService;
    }

    public void setComunService(ComunService comunService) {
        this.comunService = comunService;
    }

    public SessionStateManagedBean getSessionManagedBean() {
        return sessionManagedBean;
    }

    public void setSessionManagedBean(SessionStateManagedBean sessionManagedBean) {
        this.sessionManagedBean = sessionManagedBean;
    }

    public String getTipoTramite() {
        return tipoTramite;
    }

    public void setTipoTramite(String tipoTramite) {
        this.tipoTramite = tipoTramite;
    }

    public Date getFechaActual() {
        return fechaActual;
    }

    public void setFechaActual(Date fechaActual) {
        this.fechaActual = fechaActual;
    }

    public List<Dominio> getListDominioTipoTramite() {
        return listDominioTipoTramite;
    }

    public void setListDominioTipoTramite(List<Dominio> listDominioTipoTramite) {
        this.listDominioTipoTramite = listDominioTipoTramite;
    }

    public int getGestion() {
        return gestion;
    }

    public void setGestion(int gestion) {
        this.gestion = gestion;
    }

    public int getNroInicial() {
        return nroInicial;
    }

    public void setNroInicial(int nroInicial) {
        this.nroInicial = nroInicial;
    }

    public int getNroFinal() {
        return nroFinal;
    }

    public void setNroFinal(int nroFinal) {
        this.nroFinal = nroFinal;
    }

    public StickerService getStickerService() {
        return stickerService;
    }

    public void setStickerService(StickerService stickerService) {
        this.stickerService = stickerService;
    }

    public Sticker getSticker() {
        return sticker;
    }

    public void setSticker(Sticker sticker) {
        this.sticker = sticker;
    }

    public String getValorRadio() {
        return valorRadio;
    }

    public void setValorRadio(String valorRadio) {
        this.valorRadio = valorRadio;
    }

}
