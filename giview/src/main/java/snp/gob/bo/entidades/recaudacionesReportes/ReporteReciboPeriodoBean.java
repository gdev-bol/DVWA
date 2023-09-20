/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor. 
 */
package snp.gob.bo.entidades.recaudacionesReportes;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import snp.gob.bo.entidades.bean.common.AbstractManagedBean;
import snp.gob.bo.gimodel.entidad.Deposito;
import snp.gob.bo.gimodel.entidad.Dominio;
import snp.gob.bo.gimodel.entidad.Recibo;
import snp.gob.bo.gimodel.entidad.ReciboPorPeriodo;
import snp.gob.bo.gimodel.entidad.Regional;
import snp.gob.bo.gimodel.entidad.Usuario;
import snp.gob.bo.gimodel.servicio.ComunService;
import snp.gob.bo.gimodel.servicio.DepositoService;
import snp.gob.bo.gimodel.servicio.DominioService;
import snp.gob.bo.gimodel.servicio.ReciboAgrupacionService;
import snp.gob.bo.gimodel.servicio.ReciboService;
import snp.gob.bo.gimodel.servicio.RegionalService;
import snp.gob.bo.gimodel.servicio.UsuarioPaginaService;
import snp.gob.bo.gimodel.servicio.UsuarioService;

/**
 *
 * @author Ruben Ramirez
 */
@ManagedBean(name = "reporteReciboPeriodoBean")
@ViewScoped
public class ReporteReciboPeriodoBean extends AbstractManagedBean implements Serializable {

    //<editor-fold defaultstate="collapsed" desc="Definicion de servicios y atributos">
    // variables de la vista
    private Boolean mostrarReporte;
    private Date fechaPresente;
    private Date fechaIni;
    private Date fechaFin;
    private String tipoReportePor;
    private Long idUsuario;
    private Long regional;
    private Boolean mostrarRegional;
    private List<Regional> listaRegional;
    private Long pid;

    private Map<String, Object> parametroReporte;

    @ManagedProperty(value = "#{comunService}")
    private ComunService comunService;
     @ManagedProperty(value = "#{regionalService}")
    private RegionalService regionalService;
     @ManagedProperty(value = "#{reciboAgrupacionService}")
    private ReciboAgrupacionService reciboAgrupacionService;

    public ReporteReciboPeriodoBean() {
        mostrarReporte = false;
        fechaPresente = new Date();
        fechaIni = new Date();
        fechaFin = new Date();
        tipoReportePor = "USU";
        mostrarRegional = false;
        parametroReporte = new HashMap();
        regional = 1L;
    }

    @PostConstruct
    public void ReporteDiaDepoBeanInit() {
        try {
            fechaPresente = comunService.obtenerFechaServidor(1L);
            idUsuario = super.getIdUsuarioSession();
            listaRegional = regionalService.listadoRegional();
        } catch (Exception ex) {
            Logger.getLogger(ReporteReciboPeriodoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void mostrarListaRegional() {
        switch (tipoReportePor) {
            case "USU":
                mostrarRegional = false;
                break;
            case "REG":
                mostrarRegional = true;
                break;
            case "NAC":
                mostrarRegional = false;
                break;
            default:
                mostrarRegional = false;
                break;
        }
    }


    /**
     * Metodo para la generación del reporte de resumen diario por recibo segun
     * la fecha indicada.
     *
     * Creado: Ruben Ramirez Fecha: 22/12/2016
     *
     * @param tipo
     * @throws net.sf.jasperreports.engine.JRException
     * @throws java.io.IOException
     */
    public void imprimir(String tipo) throws JRException, IOException, Exception {
        mostrarReporte = false;
        if (fechaIni != null && fechaFin != null) {
            if (fechaFin.after(fechaIni) || fechaFin.equals(fechaIni)) {
                String imgSenapi = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/images/logoNuevo.jpg");
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

                SimpleDateFormat formateador1 = new SimpleDateFormat("EEEEE, d MMMM, yyyy", dateFormatSymbols);
                SimpleDateFormat formateador2 = new SimpleDateFormat("h:mm a");
                
                String fechaHoy = null,horaHoy = null;
                if (fechaPresente != null) {
                    fechaHoy = formateador1.format(fechaPresente);
                    horaHoy = formateador2.format(fechaPresente);
                }
                
                switch (tipoReportePor) {
                    case "USU":
                        pid = idUsuario;
                        break;
                    case "REG":
                        pid = regional;
                        break;
                    case "NAC":
                        pid = 0L;
                        break;
                }
                
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(fechaIni);
                calendar.set(Calendar.SECOND, 0);
                calendar.set(Calendar.MINUTE, 0);
                calendar.set(Calendar.HOUR_OF_DAY, 0);
                Date fini =calendar.getTime();

                calendar.setTime(fechaFin);
                calendar.set(Calendar.SECOND, 59);
                calendar.set(Calendar.MINUTE, 59);
                calendar.set(Calendar.HOUR_OF_DAY, 23);
                Date ffin = calendar.getTime();
                
                BigDecimal total = new BigDecimal("0.00");
                Integer numRecibos = 0,  numRecibosAnulados = 0, numTitulosRegistroInt = 0, numTitulosRenovInt = 0, numTitulosRegistroNac = 0, numTitulosRenovNac = 0;
                Long numReciboIni, numReciboFin;
                List<ReciboPorPeriodo> lista = reciboAgrupacionService.listaReciboPorPeriodo(fini,ffin ,tipoReportePor, pid);
                if(lista.size()>0){
                    mostrarReporte = true;
                    numReciboIni = lista.get(0).getNumeroRecibo();
                    numReciboFin = lista.get(0).getNumeroRecibo();
                    numRecibos = lista.size();
                    List<ReciboPorPeriodoDiaPojo> listaReciboFecha = new ArrayList<>();
                    for (int i = 0; i < lista.size(); i++) {
                        ReciboPorPeriodo item1 = lista.get(i);
                        ReciboPorPeriodoDiaPojo  diaRecibo = new ReciboPorPeriodoDiaPojo();
                        diaRecibo.setFechaRecibo(item1.getFecha());
                        List<ReciboPorPeriodoDetallePojo> listaDetalle = new ArrayList();
                            ReciboPorPeriodoDetallePojo detalle1 = new ReciboPorPeriodoDetallePojo();
                            detalle1.setNumeroRecibo(item1.getNumeroRecibo());
                            detalle1.setSerieRecibo(item1.getSerieRecibo());
                            detalle1.setAgenciaDeposito(item1.getAgenciaDeposito());
                            detalle1.setFechaDeposito(item1.getFechaDeposito());
                            detalle1.setTramite(item1.getTramite());
                            detalle1.setUsuario(item1.getUsuario());
                            detalle1.setNumeroDeposito(item1.getNumeroDeposito());
                            detalle1.setConcepto(item1.getConcepto());
                            if (item1.getEstadoRecibo().equals("REMI")) {
                                detalle1.setMonto(item1.getMonto());
                                total = total.add(item1.getMonto());
                            } else {
                                BigDecimal aux = new BigDecimal("0.00");
                                detalle1.setMonto(aux);
                            }
                        listaDetalle.add(detalle1);
                        
                        if (item1.getNumeroRecibo() > numReciboFin) {
                            numReciboFin = item1.getNumeroRecibo();
                        }

                        if (item1.getNumeroRecibo() < numReciboIni) {
                            numReciboIni = item1.getNumeroRecibo();
                        }

                        if (item1.getEstadoRecibo().equals("REAN")) {
                            numRecibosAnulados++;
                        }
                        if (item1.getIdtasa()==197) {
                            numTitulosRegistroInt++;
                        }
                        if (item1.getIdtasa()==195) {
                            numTitulosRenovInt++;
                        }
                        if (item1.getIdtasa()==198) {
                            numTitulosRegistroNac++;
                        }
                        if (item1.getIdtasa()==196) {
                            numTitulosRenovNac++;
                        }
                        
                        for (int j = i+1; j < lista.size(); j++) {
                            ReciboPorPeriodo item2 = lista.get(j);
                            if(diaRecibo.getFechaRecibo().equals(item2.getFecha())){
                                ReciboPorPeriodoDetallePojo detalle2 = new ReciboPorPeriodoDetallePojo();
                                detalle2.setNumeroRecibo(item2.getNumeroRecibo());
                                detalle2.setSerieRecibo(item2.getSerieRecibo());
                                detalle2.setAgenciaDeposito(item2.getAgenciaDeposito());
                                detalle2.setFechaDeposito(item2.getFechaDeposito());
                                detalle2.setTramite(item2.getTramite());
                                detalle2.setUsuario(item2.getUsuario());
                                detalle2.setNumeroDeposito(item2.getNumeroDeposito());
                                detalle2.setConcepto(item2.getConcepto());
                                if (item2.getEstadoRecibo().equals("REMI")) {
                                    detalle2.setMonto(item2.getMonto());
                                    total = total.add(item2.getMonto());
                                } else {
                                    BigDecimal aux = new BigDecimal("0.00");
                                    detalle2.setMonto(aux);
                                }
                                listaDetalle.add(detalle2);
                                if (item2.getNumeroRecibo() > numReciboFin) {
                                    numReciboFin = item2.getNumeroRecibo();
                                }

                                if (item2.getNumeroRecibo() < numReciboIni) {
                                    numReciboIni = item2.getNumeroRecibo();
                                }

                                if (item2.getEstadoRecibo().equals("REAN")) {
                                    numRecibosAnulados++;
                                }
                                if (item2.getIdtasa()==197) {
                                    numTitulosRegistroInt++;
                                }
                                if (item2.getIdtasa()==195) {
                                    numTitulosRenovInt++;
                                }
                                if (item2.getIdtasa()==198) {
                                    numTitulosRegistroNac++;
                                }
                                if (item2.getIdtasa()==196) {
                                    numTitulosRenovNac++;
                                }
                                i = j;
                            }else{
                                break;
                            }
                        }
                        diaRecibo.setListaRecibo(listaDetalle);
                        listaReciboFecha.add(diaRecibo);
                    }
                    parametroReporte.put("imgSenapi", imgSenapi);
                    parametroReporte.put("fechaPresente", fechaHoy);
                    parametroReporte.put("horaPresente", horaHoy);
                    parametroReporte.put("total", total);
                    parametroReporte.put("numRecibos", numRecibos);
                    parametroReporte.put("numReciboIni", numReciboIni);
                    parametroReporte.put("numReciboFin", numReciboFin);
                    parametroReporte.put("numRecibosAnulados", numRecibosAnulados);
                    parametroReporte.put("numTitulosRegistroInt", numTitulosRegistroInt);
                    parametroReporte.put("numTitulosRenovInt", numTitulosRenovInt);
                    parametroReporte.put("numTitulosRegistroNac", numTitulosRegistroNac);
                    parametroReporte.put("numTitulosRenovNac", numTitulosRenovNac);
                    parametroReporte.put("listaReciboFecha", convierteDataSource(listaReciboFecha));

                    if(tipo.equals("EXCEL")){
                        String filename = "ReciboPorPeriodo.xls";
                        String jasperPath = "/template/recibo/reciboPorPeriodo.jasper";
                        this.excel(parametroReporte, jasperPath, filename);
                    }else{
                        String filename = "ReciboPorPeriodo.pdf";
                        String jasperPath = "/template/recibo/reciboPorPeriodo.jasper";
                        this.generateReport(parametroReporte, jasperPath, filename); 
                    }
                } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("No existen datos para el rango de fechas seleccionada.", ""));
                }        
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Selecciones un rango de fechas valida.", ""));
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Seleccione un rango de fechas.", ""));
        }
    }
    
    public JRDataSource convierteDataSource(List<?> listaObjeto) {
        return new JRBeanCollectionDataSource(listaObjeto);
    }

    /**
     * Metodo para generar el reporte y pasarle en formato StreamContente a la
     * variable de sesion stream. Para posterior despliegue en la vista.
     *
     * Creado: Ruben Ramirez Fecha: 19/12/2016
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
     * Creado: Ruben Ramirez Fecha: 19/12/2016
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
     * Creado: Ruben Ramirez Fecha: 19/12/2016
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
    
    public void excel(Map<String, Object> params, String jasperPath, String fileName) throws JRException, IOException {

        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-Disposition", "attachment; filename=" + fileName);
        response.setContentType("application/vnd.ms-excel");
        ServletOutputStream out = response.getOutputStream();
        String relativeWebPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath(jasperPath);
        File file = new File(relativeWebPath);
        JasperPrint print = JasperFillManager.fillReport(file.getPath(), params, new JREmptyDataSource());

        JRXlsExporter exporterXls = new JRXlsExporter();
        exporterXls.setParameter(JRXlsExporterParameter.JASPER_PRINT, print);
        exporterXls.setParameter(JRXlsExporterParameter.OUTPUT_STREAM, out);
        exporterXls.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
        exporterXls.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
        exporterXls.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
        exporterXls.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
        exporterXls.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS, Boolean.TRUE);
        exporterXls.exportReport();

        FacesContext.getCurrentInstance().responseComplete();
    }

    public Boolean getMostrarReporte() {
        return mostrarReporte;
    }

    public void setMostrarReporte(Boolean mostrarReporte) {
        this.mostrarReporte = mostrarReporte;
    }

    public Date getFechaPresente() {
        return fechaPresente;
    }

    public void setFechaPresente(Date fechaPresente) {
        this.fechaPresente = fechaPresente;
    }

    public Date getFechaIni() {
        return fechaIni;
    }

    public void setFechaIni(Date fechaIni) {
        this.fechaIni = fechaIni;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getTipoReportePor() {
        return tipoReportePor;
    }

    public void setTipoReportePor(String tipoReportePor) {
        this.tipoReportePor = tipoReportePor;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Long getRegional() {
        return regional;
    }

    public void setRegional(Long regional) {
        this.regional = regional;
    }

    public Boolean getMostrarRegional() {
        return mostrarRegional;
    }

    public void setMostrarRegional(Boolean mostrarRegional) {
        this.mostrarRegional = mostrarRegional;
    }

    public List<Regional> getListaRegional() {
        return listaRegional;
    }

    public void setListaRegional(List<Regional> listaRegional) {
        this.listaRegional = listaRegional;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public Map<String, Object> getParametroReporte() {
        return parametroReporte;
    }

    public void setParametroReporte(Map<String, Object> parametroReporte) {
        this.parametroReporte = parametroReporte;
    }

    public ComunService getComunService() {
        return comunService;
    }

    public void setComunService(ComunService comunService) {
        this.comunService = comunService;
    }

    public RegionalService getRegionalService() {
        return regionalService;
    }

    public void setRegionalService(RegionalService regionalService) {
        this.regionalService = regionalService;
    }

    public ReciboAgrupacionService getReciboAgrupacionService() {
        return reciboAgrupacionService;
    }

    public void setReciboAgrupacionService(ReciboAgrupacionService reciboAgrupacionService) {
        this.reciboAgrupacionService = reciboAgrupacionService;
    }
    
}
