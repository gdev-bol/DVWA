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
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import snp.gob.bo.entidades.bean.common.AbstractManagedBean;
import snp.gob.bo.gimodel.entidad.Deposito;
import snp.gob.bo.gimodel.entidad.Dominio;
import snp.gob.bo.gimodel.entidad.Regional;
import snp.gob.bo.gimodel.entidad.Usuario;
import snp.gob.bo.gimodel.servicio.ComunService;
import snp.gob.bo.gimodel.servicio.DepositoService;
import snp.gob.bo.gimodel.servicio.DominioService;
import snp.gob.bo.gimodel.servicio.RegionalService;
import snp.gob.bo.gimodel.servicio.UsuarioPaginaService;
import snp.gob.bo.gimodel.servicio.UsuarioService;

/**
 *
 * @author Ruben Ramirez
 */
@ManagedBean(name = "reporteDiaDepoBean")
@ViewScoped
public class ReporteDiaDepoBean extends AbstractManagedBean implements Serializable {

    //<editor-fold defaultstate="collapsed" desc="Definicion de servicios y atributos">
    // variables de la vista
    private Boolean reporteRecibo;
    private Date fechaPresente;
    private Date fechaIni;
    private Date fechaFin;
    private String valorRadio = "USU";
    private Long idUsuario;
    private List<Dominio> listDominioTipoBanco;
    private List<Dominio> listaAgencia;
    private Long regional;
    private String agencia;
    private Boolean agenciaBoolean = false;
    private Boolean regionalBoolean = false;

    // parametros del reporte
    private Map<String, Object> parametros = new HashMap<String, Object>();
    private String imgSenapi;
    private String fechaIniR;
    private String fechaFinR;
    private String fechaPresenteR;
    private String horaPresenteR;
    private String totalR;
    private List<Deposito> listaDeposito;
    private List<ResumenDiarioPorDepositoPojo> listaResumenDiarioPorDepositoPojos;
    private List<Regional> listaRegional = new ArrayList<>();

    @ManagedProperty(value = "#{usuarioPaginaService}")
    private UsuarioPaginaService usuarioPaginaService;
    @ManagedProperty(value = "#{dominioService}")
    private DominioService dominioService;
    @ManagedProperty(value = "#{comunService}")
    private ComunService comunService;
    @ManagedProperty(value = "#{depositoService}")
    private DepositoService depositoService;
    @ManagedProperty(value = "#{usuarioService}")
    private UsuarioService usuarioService;
    @ManagedProperty(value = "#{regionalService}")
    private RegionalService regionalService;

    /**
     * Creates a new instance of ReporteDiaDepoBean 
     */
    public ReporteDiaDepoBean() {
        fechaPresente = new Date();
        fechaIni = new Date();
        fechaFin = new Date();
        reporteRecibo = false;

        parametros = new HashMap<String, Object>();
        listaDeposito = new ArrayList<Deposito>();
        listaResumenDiarioPorDepositoPojos = new ArrayList<ResumenDiarioPorDepositoPojo>();

    }

    @PostConstruct
    public void ReporteDiaDepoBeanInit() {
        try {
            fechaPresente = comunService.obtenerFechaServidor(1L);
            fechaIni = fechaPresente;
            fechaFin = fechaPresente;
            listDominioTipoBanco = dominioService.obtenerListadoDominio("tipo_banco");
            listaAgencia = dominioService.obtenerListadoDominio("sucursal_bancaria_recibos");
            listaRegional = regionalService.listadoRegional();
            idUsuario = super.getIdUsuarioSession();
        } catch (Exception ex) {
            Logger.getLogger(ReporteDiaDepoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void cambiaOpcionesFormulario() {
        switch (valorRadio) {
            case "USU":
                regionalBoolean = false;
                agenciaBoolean = false;
                break;
            case "REG":
                regionalBoolean = true;
                agenciaBoolean = false;
                break;
            case "AGE":
                regionalBoolean = false;
                agenciaBoolean = true;
                break;
            case "NAC":
                regionalBoolean = false;
                agenciaBoolean = false;
                break;
            default:
                regionalBoolean = false;
                agenciaBoolean = false;
                break;
        }
    }

    /**
     * Metodo para la generación del reporte de resumen diario por deposito segun el rango de fechas indicado.
     *
     * Creado: Ruben Ramirez Fecha: 15/12/2016
     *
     * @throws net.sf.jasperreports.engine.JRException
     * @throws java.io.IOException
     */
    public void imprimir() throws JRException, IOException, Exception {

        reporteRecibo = false;
        Usuario usuario = usuarioService.buscaUsuarioPorIdUsuario(idUsuario);
        if (fechaIni != null && fechaFin != null) {
            if (fechaFin.after(fechaIni) || fechaFin.equals(fechaIni)) {
                
                if(valorRadio.equals("USU")){
                    listaDeposito = depositoService.listaDepositosPorRangoDeFechasPorIdUsuario(fechaIni, fechaFin,usuario.getIdusuario());
                }

                if(valorRadio.equals("REG")){
                    listaDeposito = depositoService.listaDepositosPorRangoDeFechasPorIdRegional(fechaIni, fechaFin,regional);
                }
                
                if(valorRadio.equals("AGE")){
                    listaDeposito = depositoService.listaDepositosPorRangoDeFechasPorAgencia(fechaIni, fechaFin,Integer.parseInt(agencia));
                }

                if(valorRadio.equals("NAC")){
                    listaDeposito = depositoService.listaDepositosPorRangoDeFechas(fechaIni, fechaFin);
                }
               

                if (listaDeposito.size() > 0) {
                                       
                    BigDecimal total = new BigDecimal("0.00");

                    reporteRecibo = true;

                    imgSenapi = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/images/logoNuevo.jpg");

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

                    fechaIniR = formateador2.format(fechaIni);

                    // fecha actual de al base de datos
                    fechaPresente = comunService.obtenerFechaHoraServidor(1L);

                    if (fechaPresente != null) {
                        fechaPresenteR = formateador3.format(fechaPresente);
                        horaPresenteR = formateador4.format(fechaPresente);
                    }
                  
                    for (Deposito item : listaDeposito) {
                        ResumenDiarioPorDepositoPojo rdpd = new ResumenDiarioPorDepositoPojo();
                        for (Dominio item1 : listDominioTipoBanco) {
                            if (item.getBanco().equals(item1.getCodigo())) {
                                rdpd.setBanco(item1.getNombre());
                                break;
                            }
                        }
                        if (item.getFechaRegistroDeposito() != null) {
                            rdpd.setFechaRegistroDeposito(formateador1.format(item.getFechaRegistroDeposito()));
                        }
                        if(item.getDeposCodDep() != null){
                            rdpd.setCodAgencia(item.getDeposCodDep().toString());
                        }
                        if(item.getDeposCodAgencia()!=null){
                            rdpd.setSucursalBanco(item.getDeposCodAgencia().toString());
                        }
                        if (item.getFechaDeposito() != null) {
                            rdpd.setFechaDeposito(formateador1.format(item.getFechaDeposito()));
                        }

                        rdpd.setNumeroDeposito("" + item.getNumeroDeposito());

                        rdpd.setMonto("" + item.getMonto());
                        
                        total = total.add(item.getMonto());

                        listaResumenDiarioPorDepositoPojos.add(rdpd);
                    }
                    
                    totalR = ""+total;

                    parametros.put("imgSenapi", imgSenapi);
                    parametros.put("fechaIni", fechaIniR);
                    parametros.put("fechaFin", fechaFinR);
                    parametros.put("fechaPresente", fechaPresenteR);
                    parametros.put("horaPresente", horaPresenteR);
                    parametros.put("total", totalR);

                    String filename = "ResumenDiarioPorDeposito.pdf";
                    String jasperPath = "/template/recibo/resumenDeposito.jasper";
                    this.generateReport(parametros, jasperPath, filename, listaResumenDiarioPorDepositoPojos);
                    listaResumenDiarioPorDepositoPojos.clear();

                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("No se encontraron registros.", ""));
                }

            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Selecciones un rango de fechas valida.", ""));
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Seleccione un rango de fechas.", ""));
        }
    }

    /**
     * Metodo para generar el reporte y pasarle en formato StreamContente a la
     * variable de sesion stream. Para posterior despliegue en la vista.
     *
     * Creado: Ruben Ramirez Fecha: 15/12/2016
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
     * Creado: Ruben Ramirez Fecha: 15/12/2016
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
     * Creado: Ruben Ramirez Fecha: 15/12/2016
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

    public String getFechaIniR() {
        return fechaIniR;
    }

    public void setFechaIniR(String fechaIniR) {
        this.fechaIniR = fechaIniR;
    }

    public String getFechaFinR() {
        return fechaFinR;
    }

    public void setFechaFinR(String fechaFinR) {
        this.fechaFinR = fechaFinR;
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

    public String getTotalR() {
        return totalR;
    }

    public void setTotalR(String totalR) {
        this.totalR = totalR;
    }

    public List<ResumenDiarioPorDepositoPojo> getListaResumenDiarioPorDepositoPojos() {
        return listaResumenDiarioPorDepositoPojos;
    }

    public void setListaResumenDiarioPorDepositoPojos(List<ResumenDiarioPorDepositoPojo> listaResumenDiarioPorDepositoPojos) {
        this.listaResumenDiarioPorDepositoPojos = listaResumenDiarioPorDepositoPojos;
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

    public String getImgSenapi() {
        return imgSenapi;
    }

    public void setImgSenapi(String imgSenapi) {
        this.imgSenapi = imgSenapi;
    }

    public List<Deposito> getListaDeposito() {
        return listaDeposito;
    }

    public void setListaDeposito(List<Deposito> listaDeposito) {
        this.listaDeposito = listaDeposito;
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

    public List<Dominio> getListaAgencia() {
        return listaAgencia;
    }

    public void setListaAgencia(List<Dominio> listaAgencia) {
        this.listaAgencia = listaAgencia;
    }

    public Long getRegional() {
        return regional;
    }

    public void setRegional(Long regional) {
        this.regional = regional;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public Boolean getAgenciaBoolean() {
        return agenciaBoolean;
    }

    public void setAgenciaBoolean(Boolean agenciaBoolean) {
        this.agenciaBoolean = agenciaBoolean;
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
