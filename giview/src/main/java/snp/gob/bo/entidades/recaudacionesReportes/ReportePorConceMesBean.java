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
import snp.gob.bo.gimodel.entidad.ReciboAgrupacion;
import snp.gob.bo.gimodel.entidad.Regional;
import snp.gob.bo.gimodel.entidad.Usuario;
import snp.gob.bo.gimodel.servicio.ComunService;
import snp.gob.bo.gimodel.servicio.ReciboAgrupacionService;
import snp.gob.bo.gimodel.servicio.RegionalService;
import snp.gob.bo.gimodel.servicio.UsuarioService;

/**
 *
 * @author Ruben Ramirez
 */
@ManagedBean(name = "reportePorConceMesBean")
@ViewScoped
public class ReportePorConceMesBean extends AbstractManagedBean implements Serializable {

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
    private List<ReciboAgrupacion> listaReciboAgrupacion = new ArrayList();
    private List<ResumenConceptoMesPojo> listaResumenConceptoMesPojo = new ArrayList();
    private String imgSenapiR;
    private String fechaPresenteR;
    private String horaPresenteR;
    private List<Regional> listaRegional = new ArrayList<>();

    @ManagedProperty(value = "#{comunService}")
    private ComunService comunService;
    @ManagedProperty(value = "#{reciboAgrupacionService}")
    private ReciboAgrupacionService reciboAgrupacionService;
    @ManagedProperty(value = "#{usuarioService}")
    private UsuarioService usuarioService;
    @ManagedProperty(value = "#{regionalService}")
    private RegionalService regionalService;

    /**
     * Creates a new instance of ReportePorConceMesBean
     */
    public ReportePorConceMesBean() {
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
            Logger.getLogger(ReportePorConceMesBean.class.getName()).log(Level.SEVERE, null, ex);
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
     * Metodo para el despligue de reporte en la vista segun el mes y la gestion
     * indicados.
     *
     * Creado: Ruben Ramirez Fecha: 21/12/2016
     *
     * @throws net.sf.jasperreports.engine.JRException
     * @throws java.io.IOException
     */
    public void imprimir() throws JRException, IOException, Exception {

        reporteRecibo = false;
        BigDecimal total = new BigDecimal("0.00");
        Usuario usuario = usuarioService.buscaUsuarioPorIdUsuario(idUsuario);
        ResumenConceptoMesPojo resumenConceptoMesPojo = new ResumenConceptoMesPojo();

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

                SimpleDateFormat formateador1 = new SimpleDateFormat("dd/MM/yyyy");
                SimpleDateFormat formateador2 = new SimpleDateFormat("EEEEE, d 'de' MMMM, 'de' yyyy", dateFormatSymbols);
                SimpleDateFormat formateador3 = new SimpleDateFormat("EEEEE, d MMMM, yyyy", dateFormatSymbols);
                SimpleDateFormat formateador4 = new SimpleDateFormat("h:mm a");

                if (valorRadio.equals("USU")) {
                    if (mesEntero == 0) {
                        listaReciboAgrupacion = reciboAgrupacionService.listaReciboAgrupacionGestionPorIdUsuario(gestionEntero,usuario.getIdusuario());
                        resumenConceptoMesPojo.setMes(gestion);
                    } else {
                        listaReciboAgrupacion = reciboAgrupacionService.listaReciboAgrupacionMesPorIdUsuario(gestionEntero, mesEntero,usuario.getIdusuario());
                        resumenConceptoMesPojo.setMes(String.format("%2s", mesEntero).replace(' ', '0') + "/" + gestion);
                    }
                }

                if (valorRadio.equals("REG")) {
                    if (mesEntero == 0) {
                        listaReciboAgrupacion = reciboAgrupacionService.listaReciboAgrupacionGestionPorIdRegional(gestionEntero,regional);
                        resumenConceptoMesPojo.setMes(gestion);
                    } else {
                        listaReciboAgrupacion = reciboAgrupacionService.listaReciboAgrupacionMesPorIdRegional(gestionEntero, mesEntero,regional);
                        resumenConceptoMesPojo.setMes(String.format("%2s", mesEntero).replace(' ', '0') + "/" + gestion);
                    }
                }

                if (valorRadio.equals("NAC")) {
                    if (mesEntero == 0) {
                        listaReciboAgrupacion = reciboAgrupacionService.listaReciboAgrupacionGestion(gestionEntero);
                        resumenConceptoMesPojo.setMes(gestion);
                    } else {
                        listaReciboAgrupacion = reciboAgrupacionService.listaReciboAgrupacionMes(gestionEntero, mesEntero);
                        resumenConceptoMesPojo.setMes(String.format("%2s", mesEntero).replace(' ', '0') + "/" + gestion);
                    }
                }

                if (listaReciboAgrupacion.size() > 0) {
                    reporteRecibo = true;

                    for (ReciboAgrupacion item : listaReciboAgrupacion) {
                        total = total.add(item.getTotal());
                    }
                    resumenConceptoMesPojo.setTotalMes(total.doubleValue());
                    listaResumenConceptoMesPojo.add(resumenConceptoMesPojo);

                    imgSenapiR = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/images/logoNuevo.jpg");
                    // fecha actual de al base de datos
                    fechaPresente = comunService.obtenerFechaHoraServidor(1L);

                    if (fechaPresente != null) {
                        fechaPresenteR = formateador3.format(fechaPresente);
                        horaPresenteR = formateador4.format(fechaPresente);
                    }

                    parametros.put("imgSenapi", imgSenapiR);
                    parametros.put("fechaPresente", fechaPresenteR);
                    parametros.put("horaPresente", horaPresenteR);
                    parametros.put("detalleData", getDatos(listaReciboAgrupacion));
                    parametros.put("total", total);
                    parametros.put("mesList", listaResumenConceptoMesPojo);

                    String filename = "ResumenPorConceptoMes.pdf";
                    String jasperPath = "/template/recibo/resumenConceptoMeses.jasper";
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
     * Creado: Ruben Ramirez Fecha: 21/12/2016
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
     * Creado: Ruben Ramirez Fecha: 21/12/2016
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
     * Creado: Ruben Ramirez Fecha: 21/12/2016
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
     * Creado: Ruben Ramirez Fecha: 21/12/2016
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

    public List<ReciboAgrupacion> getListaReciboAgrupacion() {
        return listaReciboAgrupacion;
    }

    public void setListaReciboAgrupacion(List<ReciboAgrupacion> listaReciboAgrupacion) {
        this.listaReciboAgrupacion = listaReciboAgrupacion;
    }

    public ReciboAgrupacionService getReciboAgrupacionService() {
        return reciboAgrupacionService;
    }

    public void setReciboAgrupacionService(ReciboAgrupacionService reciboAgrupacionService) {
        this.reciboAgrupacionService = reciboAgrupacionService;
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

    public List<ResumenConceptoMesPojo> getListaResumenConceptoMesPojo() {
        return listaResumenConceptoMesPojo;
    }

    public void setListaResumenConceptoMesPojo(List<ResumenConceptoMesPojo> listaResumenConceptoMesPojo) {
        this.listaResumenConceptoMesPojo = listaResumenConceptoMesPojo;
    }

    public UsuarioService getUsuarioService() {
        return usuarioService;
    }

    public void setUsuarioService(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
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
