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
import snp.gob.bo.gimodel.entidad.Regional;
import snp.gob.bo.gimodel.entidad.Usuario;
import snp.gob.bo.gimodel.servicio.ComunService;
import snp.gob.bo.gimodel.servicio.DepositoService;
import snp.gob.bo.gimodel.servicio.DominioService;
import snp.gob.bo.gimodel.servicio.ReciboService;
import snp.gob.bo.gimodel.servicio.RegionalService;
import snp.gob.bo.gimodel.servicio.UsuarioPaginaService;
import snp.gob.bo.gimodel.servicio.UsuarioService;

/**
 *
 * @author Ruben Ramirez
 */
@ManagedBean(name = "reporteDiarioRecibosBean")
@ViewScoped
public class ReporteDiarioRecibosBean extends AbstractManagedBean implements Serializable {

    //<editor-fold defaultstate="collapsed" desc="Definicion de servicios y atributos">
    // variables de la vista
    private Boolean reporteRecibo;
    private Date fechaPresente;
    private Date fecha;
    private List<Dominio> listDominioTipoBanco;
    private String valorRadio = "USU";
    private Long idUsuario;
    private Long regional = 1L;
    private Boolean regionalBoolean = false;

    // parametros del reporte
    private Map<String, Object> parametros = new HashMap<String, Object>();
    private String fechaR;
    private String fechaPresenteR;
    private String horaPresenteR;
    private String imgSenapiR;
    private int recibosR;
    private Long reciboInicialR;
    private Long reciboFinalR;
    private int recibosAnuladosR;
    private int titulosRegistroIntR;
    private int titulosRenovIntR;
    private int titulosRegistroNacR;
    private int titulosRenovNacR;
    private List<Deposito> listaDeposito;
    private List<Recibo> listaRecibo;
    private List<ResumenDiarioReciboPojo> listaResumenDiarioReciboPojos;
    private List<Regional> listaRegional = new ArrayList<>();

    @ManagedProperty(value = "#{usuarioPaginaService}")
    private UsuarioPaginaService usuarioPaginaService;
    @ManagedProperty(value = "#{dominioService}")
    private DominioService dominioService;
    @ManagedProperty(value = "#{comunService}")
    private ComunService comunService;
    @ManagedProperty(value = "#{reciboService}")
    private ReciboService reciboService;
    @ManagedProperty(value = "#{depositoService}")
    private DepositoService depositoService;
    @ManagedProperty(value = "#{usuarioService}")
    private UsuarioService usuarioService;
    @ManagedProperty(value = "#{regionalService}")
    private RegionalService regionalService;

    /**
     * Creates a new instance of ReporteDiarioRecibosBean
     */
    public ReporteDiarioRecibosBean() {
        fechaPresente = new Date();
        fecha = new Date();
        reporteRecibo = false;
        parametros = new HashMap();
        listaDeposito = new ArrayList();
        listaRecibo = new ArrayList();
        listaResumenDiarioReciboPojos = new ArrayList();

    }

    @PostConstruct
    public void ReporteDiaDepoBeanInit() {
        try {
            fechaPresente = comunService.obtenerFechaServidor(1L);
            fecha = fechaPresente;
            idUsuario = super.getIdUsuarioSession();
            listaRegional = regionalService.listadoRegional();
        } catch (Exception ex) {
            Logger.getLogger(ReporteDiarioRecibosBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void cambiaOpcionesFormulario() {
        switch (valorRadio) {
            case "USU":
                regionalBoolean = false;
                break;
            case "REG":
                regionalBoolean = true;
                break;
            case "NAC":
                regionalBoolean = false;
                break;
            default:
                regionalBoolean = false;
                break;
        }
    }


    /**
     * Metodo para la generación del reporte de resumen diario por recibo segun
     * la fecha indicada.
     *
     * Creado: Ruben Ramirez Fecha: 22/12/2016
     *
     * @throws net.sf.jasperreports.engine.JRException
     * @throws java.io.IOException
     */
    public void imprimir(String tipo) throws JRException, IOException, Exception {

        reporteRecibo = false;
        Usuario usuario = usuarioService.buscaUsuarioPorIdUsuario(idUsuario);
        BigDecimal total = new BigDecimal("0.00");
        if (fecha != null) {

            if (valorRadio.equals("USU")) {
                listaRecibo = reciboService.listaReciboPorFechaPorIdUsuario(fecha, usuario.getIdusuario());
            }

            if (valorRadio.equals("REG")) {
                listaRecibo = reciboService.listaReciboPorFechaPorRegional(fecha, regional);
            }

            if (valorRadio.equals("NAC")) {
                listaRecibo = reciboService.listaReciboPorFecha(fecha);
            }

            if (listaRecibo.size() > 0) {

                reporteRecibo = true;

                imgSenapiR = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/images/logoNuevo.jpg");

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
                SimpleDateFormat formateador2 = new SimpleDateFormat("EEEEE, d 'de' MMMM, 'de' yyyy", dateFormatSymbols);
                SimpleDateFormat formateador3 = new SimpleDateFormat("EEEEE, d MMMM, yyyy", dateFormatSymbols);
                SimpleDateFormat formateador4 = new SimpleDateFormat("h:mm a");

                fechaR = formateador2.format(fecha);
                fechaPresente = comunService.obtenerFechaHoraServidor(1L);

                if (fechaPresente != null) {
                    fechaPresenteR = formateador3.format(fechaPresente);
                    horaPresenteR = formateador4.format(fechaPresente);
                }

                reciboInicialR = listaRecibo.get(0).getNumeroRecibo();
                reciboFinalR = listaRecibo.get(0).getNumeroRecibo();

                for (Recibo item : listaRecibo) {
                    if (item.getEstadoRecibo().equals("REMI") || item.getEstadoRecibo().equals("REAN")) {
                        ResumenDiarioReciboPojo resumenDiarioReciboPojo = new ResumenDiarioReciboPojo();
                        resumenDiarioReciboPojo.setRecibo(item.getNumeroRecibo());
                        resumenDiarioReciboPojo.setSerieRecibo(item.getSerieRecibo());
                        if(item.getIdUsuario()!=null){
                            Usuario user = usuarioService.obtenerUsuario(item.getIdUsuario());
                            if(user.getIdusuario()!=null){
                                resumenDiarioReciboPojo.setUsuario(devuelveNombreJuridicoONatural(user.getNombre(), user.getPrimer_apellido()));
                            }
                        }
                        resumenDiarioReciboPojo.setConcepto(item.getConcepto());
                        if (item.getEstadoRecibo().equals("REMI")) {
                            resumenDiarioReciboPojo.setMonto(item.getMonto());
                            total = total.add(item.getMonto());
                        } else {
                            BigDecimal aux = new BigDecimal("0.00");
                            resumenDiarioReciboPojo.setMonto(aux);
                        }
                        if (item.getIdRegional() != null) {
                            resumenDiarioReciboPojo.setAgencia(item.getIdRegional().toString());
                        }
                        
                        
                        String agencia = "";
                        String fechaDep = "";
                        String deposito = "";
                        
                        listaDeposito = depositoService.listaDepositosPorRecibo(item);
                       
                        int numLista = listaDeposito.size();
                        if (numLista > 0) {
                            if (numLista == 1) {
                                if(listaDeposito.get(0).getDeposCodDep() != null){
                                    agencia = listaDeposito.get(0).getDeposCodDep().toString();
                                }
                                if(listaDeposito.get(0).getFechaDeposito() != null){
                                    fechaDep = formateador1.format(listaDeposito.get(0).getFechaDeposito());
                                }
                                if(listaDeposito.get(0).getNumeroDeposito()!= null){
                                    deposito = listaDeposito.get(0).getNumeroDeposito().toString();
                                }
                            } else {
                                for (int i = 0; i < numLista - 1; i++) {
                                    if(listaDeposito.get(i).getDeposCodDep() != null){
                                        agencia += listaDeposito.get(i).getDeposCodDep().toString()+", ";
                                    }
                                    if(listaDeposito.get(i).getFechaDeposito() != null){
                                        fechaDep += formateador1.format(listaDeposito.get(i).getFechaDeposito())+", ";
                                    }
                                    if(listaDeposito.get(i).getNumeroDeposito()!= null){
                                        deposito += listaDeposito.get(i).getNumeroDeposito().toString()+", ";
                                    }
                                }
                                if(listaDeposito.get(numLista-1).getDeposCodDep() != null){
                                    agencia += listaDeposito.get(numLista-1).getDeposCodDep().toString();
                                }
                                if(listaDeposito.get(numLista-1).getFechaDeposito() != null){
                                    fechaDep += formateador1.format(listaDeposito.get(numLista-1).getFechaDeposito());
                                }
                                if(listaDeposito.get(numLista-1).getNumeroDeposito()!= null){
                                    deposito += listaDeposito.get(numLista-1).getNumeroDeposito().toString();
                                }
                            }
                        }
                        
                        resumenDiarioReciboPojo.setAgencia(agencia);
                        resumenDiarioReciboPojo.setFechaDep(fechaDep);
                        resumenDiarioReciboPojo.setDeposito(deposito);
                                                
                        if (item.getTipoTramiteIngresado() != null) {
                            resumenDiarioReciboPojo.setTramite(item.getTipoTramiteIngresado() + "-" + item.getNumeroTramiteIngresado() + "-" + item.getGestionTramiteIngresado());
                        }

                        if (item.getNumeroRecibo() > reciboFinalR) {
                            reciboFinalR = item.getNumeroRecibo();
                        }

                        if (item.getNumeroRecibo() < reciboInicialR) {
                            reciboInicialR = item.getNumeroRecibo();
                        }

                        if (item.getEstadoRecibo().equals("REAN")) {
                            recibosAnuladosR++;
                        }
                        if (item.getIdTasa()==197) {
                            titulosRegistroIntR++;
                        }
                        if (item.getIdTasa()==195) {
                            titulosRenovIntR++;
                        }
                        if (item.getIdTasa()==198) {
                            titulosRegistroNacR++;
                        }
                        if (item.getIdTasa()==196) {
                            titulosRenovNacR++;
                        }

                        recibosR++;

                        listaResumenDiarioReciboPojos.add(resumenDiarioReciboPojo);
                    }
                }

                parametros.put("fecha", fechaR);
                parametros.put("fechaPresente", fechaPresenteR);
                parametros.put("horaPresente", horaPresenteR);
                parametros.put("total", total);
                parametros.put("imgSenapi", imgSenapiR);
                parametros.put("recibos", recibosR);
                parametros.put("reciboInicial", reciboInicialR);
                parametros.put("reciboFinal", reciboFinalR);
                parametros.put("recibosAnulados", recibosAnuladosR);
                parametros.put("titulosRegistroInt", titulosRegistroIntR);
                parametros.put("titulosRenovInt", titulosRenovIntR);
                parametros.put("titulosRegistroNac", titulosRegistroNacR);
                parametros.put("titulosRenovNac", titulosRenovNacR);

                if(tipo.equals("EXCEL")){
                    String filename = "DetalleDiarioRecibo.xls";
                    String jasperPath = "/template/recibo/resumenReciboExcel.jasper";
                    this.excel(parametros, jasperPath, listaResumenDiarioReciboPojos, filename);
                }else{
                    String filename = "DetalleDiarioRecibo.pdf";
                    String jasperPath = "/template/recibo/resumenRecibo.jasper";
                    this.generateReport(parametros, jasperPath, filename, listaResumenDiarioReciboPojos); 
                }
                
                listaResumenDiarioReciboPojos.clear();
                
                recibosR = 0;
                reciboInicialR = 0L;
                reciboFinalR = 0L;
                recibosAnuladosR = 0;
                titulosRegistroIntR = 0;
                titulosRenovIntR = 0;
                titulosRegistroNacR = 0;
                titulosRenovNacR = 0;

            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("No se encontraron registros.", ""));
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Seleccione una fechas.", ""));
        }
    }

    /**
     * Metodo para generar el reporte y pasarle en formato StreamContente a la
     * variable de sesion stream. Para posterior despliegue en la vista.
     *
     * Creado: Ruben Ramirez Fecha: 22/12/2016
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
        setStreamSession(getStreamContentFromOutputStream(outputStream, "application/pdf", fileName));

    }

    /**
     * Metodo para generar el reporte en formato ByteArrayOutputStream.
     *
     * Creado: Ruben Ramirez Fecha: 22/12/2016
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
     * Creado: Ruben Ramirez Fecha: 22/12/2016
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
    
    public void excel(Map<String, Object> params, String jasperPath, List<?> dataSource, String fileName) throws JRException, IOException {

        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-Disposition", "attachment; filename=" + fileName);
        response.setContentType("application/vnd.ms-excel");
        ServletOutputStream out = response.getOutputStream();
        String relativeWebPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath(jasperPath);
        File file = new File(relativeWebPath);
        JRBeanCollectionDataSource source = new JRBeanCollectionDataSource(dataSource, false);
        JasperPrint print = JasperFillManager.fillReport(file.getPath(), params, source);

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
    
    public String devuelveNombreJuridicoONatural(String nombre, String primerApellido) throws Exception {
        String campoNombreRazonSocial = " ";
        if (nombre != null && !nombre.equals("")) {
            campoNombreRazonSocial = nombre.charAt(0)+ ".";
        }
        if (primerApellido != null && !primerApellido.equals("")) {
            campoNombreRazonSocial = campoNombreRazonSocial + " " + primerApellido;
        }
        
        return campoNombreRazonSocial;
    }
    
//    public void excel(Map<String, Object> params, String jasperPath, List<?> dataSource, String fileName) throws JRException, IOException {
//
//        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
//        response.addHeader("Content-Disposition", "attachment; filename=" + fileName);
//        response.setContentType("application/vnd.ms-excel");
//        ServletOutputStream out = response.getOutputStream();
//        String relativeWebPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath(jasperPath);
//        File file = new File(relativeWebPath);
//        JRBeanCollectionDataSource source = new JRBeanCollectionDataSource(dataSource, false);
//        JasperPrint print = JasperFillManager.fillReport(file.getPath(), params, source);
//
//        JRXlsExporter exporterXls = new JRXlsExporter();
//        exporterXls.setParameter(JRXlsExporterParameter.JASPER_PRINT, print);
//        exporterXls.setParameter(JRXlsExporterParameter.OUTPUT_STREAM, out);
//        exporterXls.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
//        exporterXls.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
//        exporterXls.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
//        exporterXls.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
//        exporterXls.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS, Boolean.TRUE);
//        exporterXls.exportReport();
//
//        FacesContext.getCurrentInstance().responseComplete();
//    }
    
//    public String devuelveNombreJuridicoONatural(String nombre, String primerApellido) throws Exception {
//        String campoNombreRazonSocial = " ";
//        if (nombre != null && !nombre.equals("")) {
//            campoNombreRazonSocial = nombre.charAt(0)+ ".";
//        }
//        if (primerApellido != null && !primerApellido.equals("")) {
//            campoNombreRazonSocial = campoNombreRazonSocial + " " + primerApellido;
//        }
//        
//        return campoNombreRazonSocial;
//    }

    public Boolean getReporteRecibo() {
        return reporteRecibo;
    }

    public void setReporteRecibo(Boolean reporteRecibo) {
        this.reporteRecibo = reporteRecibo;
    }

    public Date getFechaPresente() {
        return fechaPresente;
    }

    public void setFechaPresente(Date fechaPresente) {
        this.fechaPresente = fechaPresente;
    }

    public List<Dominio> getListDominioTipoBanco() {
        return listDominioTipoBanco;
    }

    public void setListDominioTipoBanco(List<Dominio> listDominioTipoBanco) {
        this.listDominioTipoBanco = listDominioTipoBanco;
    }

    public Map<String, Object> getParametros() {
        return parametros;
    }

    public void setParametros(Map<String, Object> parametros) {
        this.parametros = parametros;
    }

    public String getFechaPresenteR() {
        return fechaPresenteR;
    }

    public void setFechaPresenteR(String fechaPresenteR) {
        this.fechaPresenteR = fechaPresenteR;
    }

    public String getHoraPresenteR() {
        return horaPresenteR;
    }

    public void setHoraPresenteR(String horaPresenteR) {
        this.horaPresenteR = horaPresenteR;
    }

    public List<ResumenDiarioReciboPojo> getListaResumenDiarioReciboPojos() {
        return listaResumenDiarioReciboPojos;
    }

    public void setListaResumenDiarioReciboPojos(List<ResumenDiarioReciboPojo> listaResumenDiarioReciboPojos) {
        this.listaResumenDiarioReciboPojos = listaResumenDiarioReciboPojos;
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

    public DepositoService getDepositoService() {
        return depositoService;
    }

    public void setDepositoService(DepositoService depositoService) {
        this.depositoService = depositoService;
    }

    public List<Deposito> getListaDeposito() {
        return listaDeposito;
    }

    public void setListaDeposito(List<Deposito> listaDeposito) {
        this.listaDeposito = listaDeposito;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getFechaR() {
        return fechaR;
    }

    public void setFechaR(String fechaR) {
        this.fechaR = fechaR;
    }

    public String getImgSenapiR() {
        return imgSenapiR;
    }

    public void setImgSenapiR(String imgSenapiR) {
        this.imgSenapiR = imgSenapiR;
    }

    public int getRecibosR() {
        return recibosR;
    }

    public void setRecibosR(int recibosR) {
        this.recibosR = recibosR;
    }

    public Long getReciboInicialR() {
        return reciboInicialR;
    }

    public void setReciboInicialR(Long reciboInicialR) {
        this.reciboInicialR = reciboInicialR;
    }

    public Long getReciboFinalR() {
        return reciboFinalR;
    }

    public void setReciboFinalR(Long reciboFinalR) {
        this.reciboFinalR = reciboFinalR;
    }

    public int getRecibosAnuladosR() {
        return recibosAnuladosR;
    }

    public void setRecibosAnuladosR(int recibosAnuladosR) {
        this.recibosAnuladosR = recibosAnuladosR;
    }

    public int getTitulosRegistroIntR() {
        return titulosRegistroIntR;
    }

    public void setTitulosRegistroIntR(int titulosRegistroIntR) {
        this.titulosRegistroIntR = titulosRegistroIntR;
    }

    public int getTitulosRenovIntR() {
        return titulosRenovIntR;
    }

    public void setTitulosRenovIntR(int titulosRenovIntR) {
        this.titulosRenovIntR = titulosRenovIntR;
    }

    public int getTitulosRegistroNacR() {
        return titulosRegistroNacR;
    }

    public void setTitulosRegistroNacR(int titulosRegistroNacR) {
        this.titulosRegistroNacR = titulosRegistroNacR;
    }

    public int getTitulosRenovNacR() {
        return titulosRenovNacR;
    }

    public void setTitulosRenovNacR(int titulosRenovNacR) {
        this.titulosRenovNacR = titulosRenovNacR;
    }

    public List<Recibo> getListaRecibo() {
        return listaRecibo;
    }

    public void setListaRecibo(List<Recibo> listaRecibo) {
        this.listaRecibo = listaRecibo;
    }

    public ReciboService getReciboService() {
        return reciboService;
    }

    public void setReciboService(ReciboService reciboService) {
        this.reciboService = reciboService;
    }

    public String getValorRadio() {
        return valorRadio;
    }

    public void setValorRadio(String valorRadio) {
        this.valorRadio = valorRadio;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public UsuarioService getUsuarioService() {
        return usuarioService;
    }

    public void setUsuarioService(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    public List<Regional> getListaRegional() {
        return listaRegional;
    }

    public void setListaRegional(List<Regional> listaRegional) {
        this.listaRegional = listaRegional;
    }

    public RegionalService getRegionalService() {
        return regionalService;
    }

    public void setRegionalService(RegionalService regionalService) {
        this.regionalService = regionalService;
    }

    public Long getRegional() {
        return regional;
    }

    public void setRegional(Long regional) {
        this.regional = regional;
    }

    public Boolean getRegionalBoolean() {
        return regionalBoolean;
    }

    public void setRegionalBoolean(Boolean regionalBoolean) {
        this.regionalBoolean = regionalBoolean;
    }
    
}
