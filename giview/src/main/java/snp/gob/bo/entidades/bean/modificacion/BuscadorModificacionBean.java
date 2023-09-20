/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.entidades.bean.modificacion;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import snp.gob.bo.entidades.bean.common.AbstractManagedBean;
import org.primefaces.model.UploadedFile;
import snp.gob.bo.gimodel.entidad.BusquedaModificacionResultado;
import snp.gob.bo.gimodel.entidad.Documento;
import snp.gob.bo.gimodel.entidad.Dominio;
import snp.gob.bo.gimodel.entidad.ModModificacion;
import snp.gob.bo.gimodel.entidad.ModResolucion;
import snp.gob.bo.gimodel.entidad.ModSolicitanteApoderado;
import snp.gob.bo.gimodel.enums.EnumNombreDominio;
import snp.gob.bo.gimodel.servicio.ComunService;
import snp.gob.bo.gimodel.servicio.DocumentoService;
import snp.gob.bo.gimodel.servicio.DominioService;
import snp.gob.bo.gimodel.servicio.ModModificacionService;
import snp.gob.bo.gimodel.servicio.ModResolucionService;
import snp.gob.bo.gimodel.servicio.ModSolicitanteApoderadoService;

/**
 *
 * @author susana
 */
@ManagedBean(name = "buscadorModificacionBean")
@ViewScoped
public class BuscadorModificacionBean extends AbstractManagedBean implements Serializable {

    /**
     * Creates a new instance of BuscadorModificacionBean
     */
    public BuscadorModificacionBean() {
    }

    private String valorRadio = "BA";
    private Boolean buscadorRender = false;

    private UploadedFile file = null;
    private UploadedFile uploadedFile;
    private List<Documento> listaDocumento = new ArrayList<Documento>();
    private String destination = "\\home\\susana\\Proyecto_Susan\\temp\\";
    private String valorRecibido;
    private String template;
    private List<Dominio> lstTipoModificacion;
    private String tipoModificacion;
    private String estadoModificacion;
    private List<Dominio> lstSituacionActual;
    private Date fechaInicio;
    private Date fechaFin;
    private List<ModModificacion> lstModificacion;
    private List<BusquedaModificacionResultado> listaBusquedaModificacionResultado = new ArrayList();
    private String filtroSimple;
    private String textoBusquedaSimple = "";
    private Boolean muestraLink = false;
    private List<Dominio> lstOficina = new ArrayList<Dominio>();
    private String regional;
    private Integer total = 0;

    // reporte parametros
    private Map<String, Object> parametros = new HashMap();
    private String fechaHoyR;
    private String horaHoyR;
    private String imgSenapiR;
    private String imgEscudoR;
    private Integer totalR;

    @ManagedProperty("#{documentoService}")
    private DocumentoService documentoService;

    @ManagedProperty("#{dominioService}")
    private DominioService dominioService;

    @ManagedProperty("#{modModificacionService}")
    private ModModificacionService modModificacionService;

    @ManagedProperty("#{modSolicitanteApoderadoService}")
    private ModSolicitanteApoderadoService modSolicitanteApoderadoService;

    @ManagedProperty("#{modResolucionService}")
    private ModResolucionService modResolucionService;

    @ManagedProperty("#{comunService}")
    private ComunService comunService;

    @PostConstruct
    public void BuscadorModificacionBeanInit() {
        if (getNavegaPagina() != null) {

        }
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        valorRecibido = params.get("valor");
        if (params.get("valor") != null) {
            template = "./../WEB-INF/facelets/templates/DialogTemplate.xhtml";
        } else {

            template = "./../WEB-INF/facelets/templates/Template.xhtml";

            muestraLink = true;
        }
//        listaDocumento = documentoService.obtenerListadoDocumento();
//        for (Documento imagen : listaDocumento) {
//            convierteImagenYGuardaCarpeta(imagen);
//        }
    }

    public String cerrarDialogo() {
        if (valorRecibido != null) {
            RequestContext.getCurrentInstance().closeDialog("Exit");
            return "";
        } else {
            super.setNavegaPagina("");
            return "examenModificacion";
        }
    }

    public void activaCambios() {
        textoBusquedaSimple = "";
        switch (valorRadio) {
            case "BS":
                buscadorRender = true;
                break;
            case "BA":
                buscadorRender = false;
                break;
            default:
                break;
        }

        listaBusquedaModificacionResultado.clear();
        total = 0;
    }

    public void borrarTexto() {
        textoBusquedaSimple = "";
    }

    /**
     * Metodo para listar (busqueda avanzada) solicitud de modificacion segun
     * tipo, estado, fecha inicio, fecha fin y regional
     *
     * Creado: Susana Escobar Fecha: 30/11/2016
     */
    public void accionBuscar() {
        listaBusquedaModificacionResultado = modModificacionService.lista_modmodificacion_avanzada2(tipoModificacion, estadoModificacion, fechaInicio, fechaFin, regional);
        total = listaBusquedaModificacionResultado.size();
    }

    /**
     * Metodo para listar (busqueda simple) solicitud de modificacion segun un
     * filtro y un texto especificado
     *
     * Creado: Susana Escobar Fecha: 01/11/2016
     */
    public void accionBuscarSimple() {
        if (!textoBusquedaSimple.equals("")) {
            listaBusquedaModificacionResultado = modModificacionService.lista_modmodificacion_simple2(filtroSimple, textoBusquedaSimple);
            total = listaBusquedaModificacionResultado.size();
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Escriba un texto para buscar", ""));
        }
    }

    public String devuelveEstadoModificacion(String codigo) throws Exception {
        if (codigo != null && !codigo.equals("")) {
            return dominioService.obtenerListadoDominioXCodigo(EnumNombreDominio.ESTADO_MODIFICACION.getCodigo(), codigo).get(0).getNombre();
        }
        return "";
    }

    public String devuelveNumeroRegistro(Long numero, String serie) {
        if (numero != null && numero != 0) {
            return numero.toString() + "-" + serie;
        }
        return "";
    }

    public String devuelveSolicitante(Long idmodificacion) {
        List<ModSolicitanteApoderado> lstSolicitante = modSolicitanteApoderadoService.listadoSolicitanteXidmodificacion(idmodificacion);
        String textoSolicitante = "";
        for (ModSolicitanteApoderado item : lstSolicitante) {
            String dato = devuelveNombreJuridicoONatural(item.getNombre_razonsocial(), item.getPrimer_apellido(), item.getSegundo_apellido());
            textoSolicitante = textoSolicitante + dato.trim() + ", ";
        }
        if (textoSolicitante.length() > 2) {
            textoSolicitante = textoSolicitante.substring(0, textoSolicitante.length() - 2);
        }
        return textoSolicitante;//SOLICITA        
    }

    public String devuelveApoderado(Long idmodificacion) {
        List<ModSolicitanteApoderado> lstApoderado = modSolicitanteApoderadoService.listadoApoderadoXidmodificacion(idmodificacion);
        String textoApoderado = "";
        for (ModSolicitanteApoderado item : lstApoderado) {
            String dato = devuelveNombreJuridicoONatural(item.getNombre_razonsocial(), item.getPrimer_apellido(), item.getSegundo_apellido());
            textoApoderado = textoApoderado + dato.trim() + ", ";
        }
        if (textoApoderado.length() > 2) {
            textoApoderado = textoApoderado.substring(0, textoApoderado.length() - 2);
        }
        return textoApoderado;
    }

    public String devuelveResolucion(Long idmodificacion) {
        ModResolucion resolucion = modResolucionService.buscar_ModResolucionXidmodificacion(idmodificacion);
        if (resolucion != null) {
            return resolucion.getNumero_resolucion().toString();
        }
        return "";
    }

    public String devuelveGestionRes(Long idmodificacion) {
        ModResolucion resolucion = modResolucionService.buscar_ModResolucionXidmodificacion(idmodificacion);
        if (resolucion != null) {
            return resolucion.getGestion_resolucion().toString();
        }
        return "";
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
     * Metodo para navegar a vista examenModificacion
     *
     * Creado: Susana Escobar Fecha: 25/11/2016
     *
     * @param idmodificacion
     * @return
     */
    public void accionNavegarExpedienteModificacion(Long idmodificacion) {
        setIdmodificacion(idmodificacion);

//        return "examenModificacion";
        setNavegaPagina("template");
        Map<String, Object> options = new HashMap<String, Object>();
        options.put("closable", true);
        options.put("resizable", false);
        options.put("height", 700);
        options.put("width", 1220);
        options.put("contentWidth", "100%");
        options.put("contentHeight", "100%");
        options.put("modal", true);
        RequestContext.getCurrentInstance().openDialog("examenModificacion", options, null);
    }

    //////////////////////////COSAS DE LA IMAGEN////////////////////////////////////
    public void convierteImagenYGuardaCarpeta(Documento sMImagen) {
        byte[] bAvatar = sMImagen.getImagen();
        ServletContext sContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        File folder = new File(sContext.getRealPath("/temp"));
        if (!folder.exists()) {
            folder.mkdirs();
        }
        String arquivo = sContext.getRealPath("/temp") + File.separator + sMImagen.getNombre_archivo();
        crearArchivo(sMImagen.getImagen(), arquivo);
    }

    private void crearArchivo(byte[] bytes, String arquivo) {
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(arquivo);
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

    public void metodo() throws IOException {

        //System.out.println("Uploaded File Name Is :: " + file.getFileName() + " :: Uploaded File Size :: " + file.getSize());
        String fileName = file.getFileName();
        String contentType = file.getContentType();
        byte[] contents = file.getContents(); // Or getInputStream()
        InputStream bytes = file.getInputstream();
        //System.out.println(fileName + "     BINARIO  " + contents + "   otro  " + bytes);
        Documento documentoCrear = new Documento();
        documentoCrear.setIdarea(1l);
        documentoCrear.setIdtramite(1l);
        documentoCrear.setIdlogtrans(1l);
        documentoCrear.setNombre_archivo(fileName);
        documentoCrear.setDescripcion(fileName);
        documentoCrear.setNro_folios("1");
        documentoCrear.setFecha_creacion(new Date());
        documentoCrear.setTipo_archivo("jpg");
        documentoCrear.setTipo_documentacion("IM");
        documentoCrear.setImagen(contents);
        documentoCrear.setEstado("AC");
        Documento documento = documentoService.guardarDocumento(documentoCrear);
        listaDocumento = documentoService.obtenerListadoDocumento();
    }

    public void upload2(FileUploadEvent event) {
        System.out.println("aqui otro    " + event.getFile());
        uploadedFile = event.getFile();
        String fileName = uploadedFile.getFileName();
        String contentType = uploadedFile.getContentType();
        byte[] contents = uploadedFile.getContents(); // Or getInputStream()
        System.out.println(fileName + "     BINARIO  ");
        // ... Save it, now!
    }

    public void upload(FileUploadEvent event) {
        System.out.println("metodo de la carpeta ");
        try {
            copyFile(event.getFile().getFileName(), event.getFile().getInputstream());
            FacesMessage message = new FacesMessage("El archivo se ha subido con éxito!");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void copyFile(String fileName, InputStream in) {
        try {
            OutputStream out = new FileOutputStream(new File(destination + fileName));
            int read = 0;
            byte[] bytes = new byte[1024];
            while ((read = in.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            in.close();
            out.flush();
            out.close();
            System.out.println("El archivo se ha creado con éxito!");

            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH_mm_ss");
            Date date = new Date();
            String ruta1 = destination + fileName;
            String ruta2 = destination + dateFormat.format(date) + "-" + fileName;
            System.out.println("Archivo: " + ruta1 + " Renombrado a: " + ruta2);
            File archivo = new File(ruta1);
            archivo.renameTo(new File(ruta2));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public String dummyAction() throws IOException {

        System.out.println("NUEVOO ** " + file);
        if (file != null) {
            String fileName = file.getFileName();
            String contentType = file.getContentType();
            byte[] contents = file.getContents(); // Or getInputStream()
            InputStream bytes = file.getInputstream();
            System.out.println(fileName + "     BINARIO  " + contents + "   otro  " + bytes);
            Documento documentoCrear = new Documento();
            documentoCrear.setIdarea(1l);
            documentoCrear.setIdtramite(1l);
            documentoCrear.setIdlogtrans(1l);
            documentoCrear.setNombre_archivo(fileName);
            documentoCrear.setDescripcion(fileName);
            documentoCrear.setNro_folios("1");
            documentoCrear.setFecha_creacion(new Date());
            documentoCrear.setTipo_archivo("jpg");
            documentoCrear.setTipo_documentacion("IM");
            documentoCrear.setImagen(contents);
            documentoCrear.setEstado("AC");
            Documento documento = documentoService.guardarDocumento(documentoCrear);
            listaDocumento = documentoService.obtenerListadoDocumento();
        }

        return "busquedaModificacion";
    }

    /**
     * Metodo para llenar los datos del reporte de lista de busquedas de
     * modificaciones simple y avanzada.
     *
     * Creado: Ruben Ramirez Fecha: 09/01/2017
     *
     * @throws net.sf.jasperreports.engine.JRException
     * @throws java.io.IOException
     */
    public void imprimir() throws JRException, IOException, Exception {

        if (listaBusquedaModificacionResultado.size() > 0) {

            DateFormatSymbols dateFormatSymbols = new DateFormatSymbols();
            dateFormatSymbols.setWeekdays(new String[]{
                "Unused",
                "domingo",
                "lunes",
                "martes",
                "miercoles",
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

            SimpleDateFormat formateador1 = new SimpleDateFormat("EEEEE, d 'de' MMMM 'de' yyyy", dateFormatSymbols);
            SimpleDateFormat formateador2 = new SimpleDateFormat("h:mm:ss a");

            imgSenapiR = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/images/logoNuevo.jpg");
            imgEscudoR = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/images/esc-Bolivia");

            // fecha actual de la base de datos
            Date fechaPresente = comunService.obtenerFechaHoraServidor(1L);

            if (fechaPresente != null) {
                fechaHoyR = formateador1.format(fechaPresente);
                horaHoyR = formateador2.format(fechaPresente).toLowerCase();
            }

            totalR = listaBusquedaModificacionResultado.size();

            parametros.put("imgSenapi", imgSenapiR);
            parametros.put("imgEscudo", imgEscudoR);
            parametros.put("fechaHoy", fechaHoyR);
            parametros.put("horaHoy", horaHoyR);
            parametros.put("total", totalR);

            String filename = "";
            String jasperPath = "";
            if (valorRadio.equals("BS")) {
                jasperPath = "/template/modificaciones/busquedaModificacionSimple.jasper";
                filename = "BusquedaModificaionesSimple.pdf";
            } else {
                jasperPath = "/template/modificaciones/busquedaModificacionAvanzada.jasper";
                filename = "BusquedaModificaionesAvanzada.pdf";
            }
            this.PDF(parametros, jasperPath, listaBusquedaModificacionResultado, filename);
        }
    }

    /**
     * Metodo para generar y descargar el reporte en formato pdf.
     *
     * Creado: Ruben Ramirez Fecha: 09/01/2017
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
        JRBeanCollectionDataSource source = new JRBeanCollectionDataSource(dataSource, false);
        JasperPrint print = JasperFillManager.fillReport(file.getPath(), params, source);
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-Disposition", "attachment; filename=" + fileName);
        ServletOutputStream stream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(print, stream);
        FacesContext.getCurrentInstance().responseComplete();
    }
    
      /**
     * Metodo descargar informacion de la tabla de la vista en formato excel
     *
     * Creado: Susana Escobar Paz Fecha: 20/06/2017
     *
     */
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

    public String getValorRadio() {
        return valorRadio;
    }

    public void setValorRadio(String valorRadio) {
        this.valorRadio = valorRadio;
    }

    public Boolean getBuscadorRender() {
        return buscadorRender;
    }

    public void setBuscadorRender(Boolean buscadorRender) {
        this.buscadorRender = buscadorRender;
    }

    public UploadedFile getUploadedFile() {
        return uploadedFile;
    }

    public void setUploadedFile(UploadedFile uploadedFile) {
        this.uploadedFile = uploadedFile;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public DocumentoService getDocumentoService() {
        return documentoService;
    }

    public void setDocumentoService(DocumentoService documentoService) {
        this.documentoService = documentoService;
    }

    public List<Documento> getListaDocumento() {
        return listaDocumento;
    }

    public void setListaDocumento(List<Documento> listaDocumento) {
        this.listaDocumento = listaDocumento;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public List<Dominio> getLstTipoModificacion() throws Exception {
        return lstTipoModificacion = dominioService.obtenerListadoDominio("tipo_modificacion");
    }

    public void setLstTipoModificacion(List<Dominio> lstTipoModificacion) {
        this.lstTipoModificacion = lstTipoModificacion;
    }

    public DominioService getDominioService() {
        return dominioService;
    }

    public void setDominioService(DominioService dominioService) {
        this.dominioService = dominioService;
    }

    public String getTipoModificacion() {
        return tipoModificacion;
    }

    public void setTipoModificacion(String tipoModificacion) {
        this.tipoModificacion = tipoModificacion;
    }

    public String getEstadoModificacion() {
        return estadoModificacion;
    }

    public void setEstadoModificacion(String estadoModificacion) {
        this.estadoModificacion = estadoModificacion;
    }

    public List<Dominio> getLstSituacionActual() throws Exception {
        return lstSituacionActual = dominioService.obtenerListadoDominio("estado_modificacion");
    }

    public void setLstSituacionActual(List<Dominio> lstSituacionActual) {
        this.lstSituacionActual = lstSituacionActual;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public List<ModModificacion> getLstModificacion() {
        return lstModificacion;
    }

    public void setLstModificacion(List<ModModificacion> lstModificacion) {
        this.lstModificacion = lstModificacion;
    }

    public ModModificacionService getModModificacionService() {
        return modModificacionService;
    }

    public void setModModificacionService(ModModificacionService modModificacionService) {
        this.modModificacionService = modModificacionService;
    }

    public ModSolicitanteApoderadoService getModSolicitanteApoderadoService() {
        return modSolicitanteApoderadoService;
    }

    public void setModSolicitanteApoderadoService(ModSolicitanteApoderadoService modSolicitanteApoderadoService) {
        this.modSolicitanteApoderadoService = modSolicitanteApoderadoService;
    }

    public ModResolucionService getModResolucionService() {
        return modResolucionService;
    }

    public void setModResolucionService(ModResolucionService modResolucionService) {
        this.modResolucionService = modResolucionService;
    }

    public String getFiltroSimple() {
        return filtroSimple;
    }

    public void setFiltroSimple(String filtroSimple) {
        this.filtroSimple = filtroSimple;
    }

    public String getTextoBusquedaSimple() {
        return textoBusquedaSimple;
    }

    public void setTextoBusquedaSimple(String textoBusquedaSimple) {
        this.textoBusquedaSimple = textoBusquedaSimple;
    }

    public Boolean getMuestraLink() {
        return muestraLink;
    }

    public void setMuestraLink(Boolean muestraLink) {
        this.muestraLink = muestraLink;
    }

    public List<Dominio> getLstOficina() throws Exception {
        return lstOficina = dominioService.obtenerListadoDominio("oficina");
    }

    public void setLstOficina(List<Dominio> lstOficina) {
        this.lstOficina = lstOficina;
    }

    public String getRegional() {
        return regional;
    }

    public void setRegional(String regional) {
        this.regional = regional;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getValorRecibido() {
        return valorRecibido;
    }

    public void setValorRecibido(String valorRecibido) {
        this.valorRecibido = valorRecibido;
    }

    public Map<String, Object> getParametros() {
        return parametros;
    }

    public void setParametros(Map<String, Object> parametros) {
        this.parametros = parametros;
    }

    public String getFechaHoyR() {
        return fechaHoyR;
    }

    public void setFechaHoyR(String fechaHoyR) {
        this.fechaHoyR = fechaHoyR;
    }

    public String getHoraHoyR() {
        return horaHoyR;
    }

    public void setHoraHoyR(String horaHoyR) {
        this.horaHoyR = horaHoyR;
    }

    public String getImgSenapiR() {
        return imgSenapiR;
    }

    public void setImgSenapiR(String imgSenapiR) {
        this.imgSenapiR = imgSenapiR;
    }

    public String getImgEscudoR() {
        return imgEscudoR;
    }

    public void setImgEscudoR(String imgEscudoR) {
        this.imgEscudoR = imgEscudoR;
    }

    public Integer getTotalR() {
        return totalR;
    }

    public void setTotalR(Integer totalR) {
        this.totalR = totalR;
    }

    public ComunService getComunService() {
        return comunService;
    }

    public void setComunService(ComunService comunService) {
        this.comunService = comunService;
    }

    public List<BusquedaModificacionResultado> getListaBusquedaModificacionResultado() {
        return listaBusquedaModificacionResultado;
    }

    public void setListaBusquedaModificacionResultado(List<BusquedaModificacionResultado> listaBusquedaModificacionResultado) {
        this.listaBusquedaModificacionResultado = listaBusquedaModificacionResultado;
    }
}
