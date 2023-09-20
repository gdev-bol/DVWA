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
import snp.gob.bo.gimodel.entidad.ReciboResultado;
import snp.gob.bo.gimodel.entidad.Regional;
import snp.gob.bo.gimodel.entidad.Usuario;
import snp.gob.bo.gimodel.servicio.ComunService;
import snp.gob.bo.gimodel.servicio.ReciboResultadoService;
import snp.gob.bo.gimodel.servicio.RegionalService;
import snp.gob.bo.gimodel.servicio.UsuarioService;

/**
 *
 * @author Ruben Ramirez
 */
@ManagedBean(name = "reportePorConceptoBean")
@ViewScoped
public class ReportePorConceptoBean extends AbstractManagedBean implements Serializable {

    //<editor-fold defaultstate="collapsed" desc="Definicion de servicios y atributos">
    // variables de la vista
    private Boolean reporteRecibo;
    private String gestion;
    private String mes;
    private Date fechaPresente;
    private String valorRadio = "USU";
    private Long idUsuario;
    private List<Integer> anios = new ArrayList<>();
    private Integer anioFin;
    private Integer anioIni;
    private Long regional = 1L;
    private Boolean regionalBoolean = false;

    // parametros del reporte
    private Map<String, Object> parametros = new HashMap<String, Object>();
    private List<ResumenConceptoMesPojo> listaResumenConceptoMesPojo = new ArrayList();
    private String imgSenapiR;
    private String fechaPresenteR;
    private String horaPresenteR;
    private List<Regional> listaRegional = new ArrayList<>();

    @ManagedProperty(value = "#{comunService}")
    private ComunService comunService;
    @ManagedProperty(value = "#{reciboResultadoService}")
    private ReciboResultadoService reciboResultadoService;
    @ManagedProperty(value = "#{usuarioService}")
    private UsuarioService usuarioService;
    @ManagedProperty(value = "#{regionalService}")
    private RegionalService regionalService;

    /**
     * Creates a new instance of ReportePorConceptoBean
     */
    public ReportePorConceptoBean() {
        reporteRecibo = false;
        parametros = new HashMap<String, Object>();

    }

    @PostConstruct
    public void ReportePorConceptoBeanInit() {
        try {
            fechaPresente = comunService.obtenerFechaServidor(1L);
            idUsuario = super.getIdUsuarioSession();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(fechaPresente);
            anioFin = calendar.get(Calendar.YEAR);
            anioIni = 2006;
            listaRegional = regionalService.listadoRegional();
            for (int i = anioFin; i >= anioIni; i--) {
                anios.add(i);
            }

        } catch (Exception ex) {
            Logger.getLogger(ReportePorConceptoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /*
     * Metodo para convertir un numero entre 1 a 12 a mes en literal.
     *
     * Creado: Ruben Ramirez Fecha: 19/12/2016
     * @param cadena
     * @return 
     */
    public String gestionLiteral(int gestion) {
        String gestionLiteral = "";
        switch (gestion) {
            case 1:
                gestionLiteral = "enero";
                break;
            case 2:
                gestionLiteral = "febrero";
                break;
            case 3:
                gestionLiteral = "marzo";
                break;
            case 4:
                gestionLiteral = "abril";
                break;
            case 5:
                gestionLiteral = "mayo";
                break;
            case 6:
                gestionLiteral = "junio";
                break;
            case 7:
                gestionLiteral = "julio";
                break;
            case 8:
                gestionLiteral = "agosto";
                break;
            case 9:
                gestionLiteral = "septiembre";
                break;
            case 10:
                gestionLiteral = "octubre";
                break;
            case 11:
                gestionLiteral = "noviembre";
                break;
            case 12:
                gestionLiteral = "diciembre";
                break;
        }
        return gestionLiteral;
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
     * Metodo para la generación del reporte de resumen diario por concepto
     * segun la gestion y mes o de todo el año seleccionado.
     *
     * Creado: Ruben Ramirez Fecha: 19/12/2016
     *
     * @throws net.sf.jasperreports.engine.JRException
     * @throws java.io.IOException
     */
    public void imprimir() throws JRException, IOException, Exception {

        reporteRecibo = false;
        BigDecimal total = new BigDecimal("0.00");
        Usuario usuario = usuarioService.buscaUsuarioPorIdUsuario(idUsuario);
        if (gestion != null && mes != null) {
            if (validar(mes) && validar(gestion)) {

                int mesEntero = Integer.parseInt(mes);
                int gestionEntero = Integer.parseInt(gestion);

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

                SimpleDateFormat formateador3 = new SimpleDateFormat("EEEEE, d MMMM, yyyy", dateFormatSymbols);
                SimpleDateFormat formateador4 = new SimpleDateFormat("h:mm a");

                int ini = 0;
                int fin = 0;

                if (mesEntero == 0) {
                    ini = 1;
                    fin = 12;
                } else {
                    ini = mesEntero;
                    fin = mesEntero;
                }

                for (int i = ini; i <= fin; i++) {
                    List<String> listaFecha = new ArrayList<>();
                    if (valorRadio.equals("USU")) {
                        listaFecha = reciboResultadoService.listaReciboResultadoMesPorIdUsuario(gestionEntero, i, usuario.getIdusuario());
                    }

                    if (valorRadio.equals("REG")) {
                        listaFecha = reciboResultadoService.listaReciboResultadoMesPorIdRegional(gestionEntero, i, regional);
                    }

                    if (valorRadio.equals("NAC")) {
                        listaFecha = reciboResultadoService.listaReciboResultadoMes(gestionEntero, i);
                    }
                    if (listaFecha.size() > 0) {
                        reporteRecibo = true;
                        ResumenConceptoMesPojo resumenConceptoMesPojo = new ResumenConceptoMesPojo();
                        resumenConceptoMesPojo.setMes(String.format("%2s", i).replace(' ', '0') + "/" + gestion);
                        resumenConceptoMesPojo.setMesGestion(gestionLiteral(i) + " " + gestion);
                        List<ResumenConceptoDiaPojo> listaResumenConceptoDiaPojo = new ArrayList();
                        BigDecimal montoTotalMes = new BigDecimal("0.00");
                        for (String item1 : listaFecha) {
                            ResumenConceptoDiaPojo resumenConceptoDiaPojo = new ResumenConceptoDiaPojo();
                            resumenConceptoDiaPojo.setFecha(item1);
                            List<ReciboResultado> listaReciboResultado = new ArrayList<>();

                            if (valorRadio.equals("USU")) {
                                listaReciboResultado = reciboResultadoService.listaReciboResultadoFechaIdUsuario(item1, usuario.getIdusuario());
                            }

                            if (valorRadio.equals("REG")) {
                                listaReciboResultado = reciboResultadoService.listaReciboResultadoFechaPorIdRegional(item1, regional);
                            }

                            if (valorRadio.equals("NAC")) {
                                listaReciboResultado = reciboResultadoService.listaReciboResultadoFecha(item1);
                            }

                            List<ResumenConceptoDetallePojo> listaResumenConceptoDetallePojo = new ArrayList();

                            BigDecimal montoTotalDia = new BigDecimal("0.00");
                            for (ReciboResultado item2 : listaReciboResultado) {
                                ResumenConceptoDetallePojo resumenConceptoDetallePojo = new ResumenConceptoDetallePojo();
                                resumenConceptoDetallePojo.setFecha(item2.getFecha());
                                resumenConceptoDetallePojo.setConcepto(item2.getConcepto());
                                resumenConceptoDetallePojo.setCantidad("" + item2.getCantidad());
                                resumenConceptoDetallePojo.setTotal("" + item2.getTotal());

                                montoTotalDia = montoTotalDia.add(item2.getTotal());

                                listaResumenConceptoDetallePojo.add(resumenConceptoDetallePojo);
                            }
                            resumenConceptoDiaPojo.setDetalleDataR(listaResumenConceptoDetallePojo);
                            resumenConceptoDiaPojo.setTotal("" + montoTotalDia);

                            montoTotalMes = montoTotalMes.add(montoTotalDia);

                            listaResumenConceptoDiaPojo.add(resumenConceptoDiaPojo);
                        }
                        resumenConceptoMesPojo.setTotalMes(montoTotalMes.doubleValue());
                        resumenConceptoMesPojo.setDiaDataR(listaResumenConceptoDiaPojo);
                        total = total.add(montoTotalMes);
                        listaResumenConceptoMesPojo.add(resumenConceptoMesPojo);
                    }
                }

                if (reporteRecibo) {
                    imgSenapiR = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/images/logoNuevo.jpg");
                    fechaPresente = comunService.obtenerFechaHoraServidor(1L);

                    if (fechaPresente != null) {
                        fechaPresenteR = formateador3.format(fechaPresente);
                        horaPresenteR = formateador4.format(fechaPresente);
                    }
                    parametros.put("imgSenapi", imgSenapiR);
                    parametros.put("fechaPresente", fechaPresenteR);
                    parametros.put("horaPresente", horaPresenteR);
                    parametros.put("mesData", getDatos(listaResumenConceptoMesPojo));
                    parametros.put("mesList", listaResumenConceptoMesPojo);
                    parametros.put("total", total);

                    String filename = "ResumenDiarioPorConcepto.pdf";
                    String jasperPath = "/template/recibo/resumenConcepto.jasper";
                    this.generateReport(parametros, jasperPath, filename);

                    listaResumenConceptoMesPojo.clear();

                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "No se encontraron registros.", ""));
                }

            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Introdusca los datos validos.", ""));
            }

        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Introdusca los datos necesarios.", ""));
        }
    }

    public JRDataSource getDatos(List<?> listaObjeto) {
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

    /*
     * Metodo para validar si una cadena string es un numero.
     *
     * Creado: Ruben Ramirez Fecha: 19/12/2016
     * @param cadena
     * @return 
     */
    public boolean validar(String cadena) {
        if (cadena.equals("") || cadena == null) {
            return false;
        } else {
            return cadena.matches("[0-9]*");
        }
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

    public ComunService getComunService() {
        return comunService;
    }

    public void setComunService(ComunService comunService) {
        this.comunService = comunService;
    }

    public String getGestion() {
        return gestion;
    }

    public void setGestion(String gestion) {
        this.gestion = gestion;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public Date getFechaPresente() {
        return fechaPresente;
    }

    public void setFechaPresente(Date fechaPresente) {
        this.fechaPresente = fechaPresente;
    }

    public String getImgSenapiR() {
        return imgSenapiR;
    }

    public void setImgSenapiR(String imgSenapiR) {
        this.imgSenapiR = imgSenapiR;
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

    public List<ResumenConceptoMesPojo> getListaResumenConceptoMesPojo() {
        return listaResumenConceptoMesPojo;
    }

    public void setListaResumenConceptoMesPojo(List<ResumenConceptoMesPojo> listaResumenConceptoMesPojo) {
        this.listaResumenConceptoMesPojo = listaResumenConceptoMesPojo;
    }

    public ReciboResultadoService getReciboResultadoService() {
        return reciboResultadoService;
    }

    public void setReciboResultadoService(ReciboResultadoService reciboResultadoService) {
        this.reciboResultadoService = reciboResultadoService;
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

    public List<Integer> getAnios() {
        return anios;
    }

    public void setAnios(List<Integer> anios) {
        this.anios = anios;
    }

    public Integer getAnioFin() {
        return anioFin;
    }

    public void setAnioFin(Integer anioFin) {
        this.anioFin = anioFin;
    }

    public Integer getAnioIni() {
        return anioIni;
    }

    public void setAnioIni(Integer anioIni) {
        this.anioIni = anioIni;
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

}
