/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.entidades.bean.registro;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
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
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.primefaces.context.RequestContext;
import snp.gob.bo.entidades.bean.common.AbstractManagedBean;
import snp.gob.bo.gimodel.entidad.BusquedaSigRegistro;
import snp.gob.bo.gimodel.entidad.Dominio;
import snp.gob.bo.gimodel.entidad.SigRegistro;
import snp.gob.bo.gimodel.servicio.ComunService;
import snp.gob.bo.gimodel.servicio.DominioService;
import snp.gob.bo.gimodel.servicio.SigRegistroService;

/**
 *
 * @author susana
 */
@ManagedBean(name = "busquedaRegistroBean")
@ViewScoped
public class BusquedaRegistroBean extends AbstractManagedBean implements Serializable {

    /**
     * Creates a new instance of BusquedaRegistroBean
     */
    public BusquedaRegistroBean() {
    }

    private String valorRadio = "RD";
    private Boolean calendarioDialogoRender = false;
    private String tipoMarcaDialogo;
    private Boolean tipoMarcaDialogoRender = false;
    private Boolean titularDialogoRender = false;
    private Boolean signoDialogoRender = false;
    private Boolean encabezado = false;
    private String template;
    private String valorRecibido;
    private List<SigRegistro> lstSigRegistro;
    private String textoBusqueda = "";
    private Date fechaBusqueda;
    private List<Dominio> listaTipoGenero;
    private String valorTipoBusqueda = "BA";
    private Boolean buscadorRender = false;
    private Date fechaInicio;
    private Date fechaFin;
    private List<BusquedaSigRegistro> lstSigRegistroBusqueda = new ArrayList<BusquedaSigRegistro>();

    // atributos reportes
    private String imgSenapi;
    private String imgEscudo;
    private String fechaHoy;
    private String horaHoy;
    private String campoBusqueda;
    private Integer total;
    private Map<String, Object> parametros = new HashMap();
    private List<BusquedaRegistroPojo> lstBusquedaRegistroPojo = new ArrayList();
    private List<Date> listaFechas = new ArrayList();

    @ManagedProperty("#{sigRegistroService}")
    private SigRegistroService sigRegistroService;
    @ManagedProperty("#{dominioService}")
    private DominioService dominioService;
    @ManagedProperty("#{comunService}")
    private ComunService comunService;

    @PostConstruct
    public void BusquedaRegistroBeanInit() {

        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        valorRecibido = params.get("valor");

        if (params.get("valor") != null) {
            template = "./../WEB-INF/facelets/templates/DialogTemplate.xhtml";
        } else {

            template = "./../WEB-INF/facelets/templates/Template.xhtml";

        }
    }

    public void activaCambios() {
        reset();
        switch (valorTipoBusqueda) {
            case "BS":
                buscadorRender = true;
                break;
            case "BA":
                buscadorRender = false;
                break;
            default:
                break;
        }
    }

    public void reset() {
        lstSigRegistro = new ArrayList<SigRegistro>();
        lstSigRegistroBusqueda = new ArrayList<BusquedaSigRegistro>();
        textoBusqueda = "";
        fechaBusqueda = null;
        fechaInicio = null;
        fechaFin = null;
    }

    public void activaCambiosLista() {
        lstBusquedaRegistroPojo = new ArrayList();
        reset();
        System.out.println("lstSigRegistroBusqueda   " + lstSigRegistroBusqueda.size());
        encabezado = true;
        switch (valorRadio) {
            case "RD":
                calendarioDialogoRender = false;
                tipoMarcaDialogoRender = false;
                titularDialogoRender = false;
                signoDialogoRender = false;
                textoBusqueda = "";
                break;
            case "FR":
                calendarioDialogoRender = true;
                tipoMarcaDialogoRender = false;
                titularDialogoRender = false;
                signoDialogoRender = false;
                textoBusqueda = "";
                break;
            case "TM":
                calendarioDialogoRender = false;
                tipoMarcaDialogoRender = true;
                titularDialogoRender = false;
                signoDialogoRender = false;
                textoBusqueda = "Denominación de origen";
                break;
            case "TI":
                calendarioDialogoRender = false;
                tipoMarcaDialogoRender = false;
                titularDialogoRender = true;
                signoDialogoRender = false;
                textoBusqueda = "";
                break;
            case "SI":
                calendarioDialogoRender = false;
                tipoMarcaDialogoRender = false;
                titularDialogoRender = false;
                signoDialogoRender = true;
                textoBusqueda = "";
                break;
            default:
                calendarioDialogoRender = false;
                tipoMarcaDialogoRender = false;
                titularDialogoRender = false;
                signoDialogoRender = false;
                textoBusqueda = "";
                break;
        }

    }

    public String cerrarDialogo() {
        if (valorRecibido != null) {
            RequestContext.getCurrentInstance().closeDialog("Exit");
            return "";
        } else {
            return "registroNuevoAntiguo";
        }
    }

    public void accionBuscarRegistro() {
        lstSigRegistro = sigRegistroService.lista_SigRegistro_filtro(valorRadio, fechaBusqueda, textoBusqueda);

    }

    public void accionBusquedaAvanzadaRegistro() {
        System.out.println("busqueda avanzada    ");
        // lstSigRegistroBsuqueda
        if (fechaInicio != null && fechaFin != null) {
            lstSigRegistroBusqueda = sigRegistroService.lista_SigRegistro_avanzada(fechaInicio, fechaFin);
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Complete datos de fechas", ""));
        }
    }

    public String devuelveEstadoPublicacion(String codigo) throws Exception {
        if (codigo != null && !codigo.equals("")) {
            return dominioService.obtenerListadoDominioXCodigo("estado_registro", codigo).get(0).getNombre();
        }
        return "";
    }

    /**
     * Metodo para llenar los datos del reporte de lista de busquedas de
     * registros emitidos y su generacion en formato pdf
     *
     * Creado: Ruben Ramirez Fecha: 30/12/2016
     *
     * @throws net.sf.jasperreports.engine.JRException
     * @throws java.io.IOException
     */
    public void imprimir() throws JRException, IOException, Exception {

        if (lstSigRegistro.size() > 0) {

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
            SimpleDateFormat formateador3 = new SimpleDateFormat("dd/MM/yyyy");

            imgSenapi = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/images/logoNuevo.jpg");
            imgEscudo = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/images/esc-Bolivia");

            // fecha actual de la base de datos
            Date fechaPresente = comunService.obtenerFechaHoraServidor(1L);

            if (fechaPresente != null) {
                fechaHoy = formateador1.format(fechaPresente);
                horaHoy = formateador2.format(fechaPresente);
            }

            if (valorRadio.equals("RD")) {
                campoBusqueda = "Registros Denegados";
            }
            if (valorRadio.equals("FR")) {
                campoBusqueda = "Fecha de Registro";
            }
            if (valorRadio.equals("TM")) {
                campoBusqueda = "Tipo de Marca";
            }
            if (valorRadio.equals("TI")) {
                campoBusqueda = "Titular";
            }
            if (valorRadio.equals("SI")) {
                campoBusqueda = "Signo";
            }

            total = lstSigRegistro.size();

            for (SigRegistro item : lstSigRegistro) {
                BusquedaRegistroPojo brp = new BusquedaRegistroPojo();
                brp.setSigno(item.getSigno());
                brp.setTitular(item.getTitular());
                brp.setSmDescripcion(item.getSmDescripcion());
                if (item.getFechaIngreso() != null) {
                    brp.setFechaIngreso(formateador3.format(item.getFechaIngreso()));
                }
                brp.setNumeroRegistro(item.getNumeroRegistro());
                brp.setSerie(item.getSerie());
                if (item.getFechaRegistro() != null) {
                    brp.setFechaRegistro(formateador3.format(item.getFechaRegistro()));
                }
                brp.setTipoGeneroDescripcion(item.getTipoGeneroDescripcion());
                brp.setEstadoRegistro(devuelveEstadoPublicacion(item.getEstadoRegistro()));

                lstBusquedaRegistroPojo.add(brp);
            }

            parametros.put("imgSenapi", imgSenapi);
            parametros.put("imgEscudo", imgEscudo);
            parametros.put("fechaHoy", fechaHoy);
            parametros.put("horaHoy", horaHoy);
            parametros.put("campoBusqueda", campoBusqueda);
            parametros.put("total", total);

            String filename = "BusquedaRegistrosEmitidos.pdf";
            String jasperPath = "/template/registro/busquedaRegistrosEmitidos.jasper";
            this.PDF(parametros, jasperPath, lstBusquedaRegistroPojo, filename);
            lstBusquedaRegistroPojo.clear();
        }
    }

    /**
     * Metodo para llenar los datos del reporte de busqueda avanzada de
     * registros emitidos.
     *
     * Creado: Ruben Ramirez Fecha: 06/01/2017
     *
     * @throws net.sf.jasperreports.engine.JRException
     * @throws java.io.IOException
     */
    public void imprimirAvanzado() throws JRException, IOException, Exception {

        if (fechaInicio != null && fechaFin != null) {
            if (lstSigRegistroBusqueda.size() > 0) {
                listaFechas = sigRegistroService.listadoFechasRegistro(fechaInicio, fechaFin);
                for (Date item1 : listaFechas) {
                    int i = 0;
                    for (BusquedaSigRegistro item2 : lstSigRegistroBusqueda) {
                        if (item1.equals(item2.getFecha_registro())) {
                            BusquedaSigRegistro bsr = new BusquedaSigRegistro();
                            bsr.setFecha_registro(item1);
                            lstSigRegistroBusqueda.add(i, bsr);
                            break;
                        }
                        i++;
                    }
                }
                JRBeanCollectionDataSource itemsJRBean = new JRBeanCollectionDataSource(lstSigRegistroBusqueda);

                parametros.put("detalleRegistros", itemsJRBean);

                String filename = "BusquedaAvanzadaRegistrosEmitidos.pdf";
                String jasperPath = "/template/registro/busquedaAvanzadaRegistrosEmitidos.jasper";
                this.PDFSD(parametros, jasperPath, filename);
            }
        }
    }

    /**
     * Metodo para generar y descargar el reporte en formato pdf.
     *
     * Creado: Ruben Ramirez Fecha: 30/12/2016
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
     * Metodo para generar y descarga del reporte en formato pdf sin una lista
     * de datos.
     *
     * Creado: Ruben Ramirez Fecha: 06/01/2017
     *
     * @param params
     * @param jasperPath
     * @param fileName
     * @throws net.sf.jasperreports.engine.JRException
     * @throws java.io.IOException
     */
    public void PDFSD(Map<String, Object> params, String jasperPath, String fileName) throws JRException, IOException {
        String relativeWebPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath(jasperPath);
        File file = new File(relativeWebPath);
        JasperPrint print = JasperFillManager.fillReport(file.getPath(), params, new JREmptyDataSource());
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-Disposition", "attachment; filename=" + fileName);
        ServletOutputStream stream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(print, stream);
        FacesContext.getCurrentInstance().responseComplete();
    }

    public String getValorRadio() {
        return valorRadio;
    }

    public void setValorRadio(String valorRadio) {
        this.valorRadio = valorRadio;
    }

    public Boolean getCalendarioDialogoRender() {
        return calendarioDialogoRender;
    }

    public void setCalendarioDialogoRender(Boolean calendarioDialogoRender) {
        this.calendarioDialogoRender = calendarioDialogoRender;
    }

    public String getTipoMarcaDialogo() {
        return tipoMarcaDialogo;
    }

    public void setTipoMarcaDialogo(String tipoMarcaDialogo) {
        this.tipoMarcaDialogo = tipoMarcaDialogo;
    }

    public Boolean getTipoMarcaDialogoRender() {
        return tipoMarcaDialogoRender;
    }

    public void setTipoMarcaDialogoRender(Boolean tipoMarcaDialogoRender) {
        this.tipoMarcaDialogoRender = tipoMarcaDialogoRender;
    }

    public Boolean getTitularDialogoRender() {
        return titularDialogoRender;
    }

    public void setTitularDialogoRender(Boolean titularDialogoRender) {
        this.titularDialogoRender = titularDialogoRender;
    }

    public Boolean getSignoDialogoRender() {
        return signoDialogoRender;
    }

    public void setSignoDialogoRender(Boolean signoDialogoRender) {
        this.signoDialogoRender = signoDialogoRender;
    }

    public Boolean getEncabezado() {
        return encabezado;
    }

    public void setEncabezado(Boolean encabezado) {
        this.encabezado = encabezado;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public SigRegistroService getSigRegistroService() {
        return sigRegistroService;
    }

    public void setSigRegistroService(SigRegistroService sigRegistroService) {
        this.sigRegistroService = sigRegistroService;
    }

    public List<SigRegistro> getLstSigRegistro() {
        return lstSigRegistro;
    }

    public void setLstSigRegistro(List<SigRegistro> lstSigRegistro) {
        this.lstSigRegistro = lstSigRegistro;
    }

    public String getTextoBusqueda() {
        return textoBusqueda;
    }

    public void setTextoBusqueda(String textoBusqueda) {
        this.textoBusqueda = textoBusqueda;
    }

    public Date getFechaBusqueda() {
        return fechaBusqueda;
    }

    public void setFechaBusqueda(Date fechaBusqueda) {
        this.fechaBusqueda = fechaBusqueda;
    }

    public List<Dominio> getListaTipoGenero() throws Exception {
        return listaTipoGenero = dominioService.obtenerListadoDominio("tipo_genero");
    }

    public void setListaTipoGenero(List<Dominio> listaTipoGenero) {
        this.listaTipoGenero = listaTipoGenero;
    }

    public DominioService getDominioService() {
        return dominioService;
    }

    public void setDominioService(DominioService dominioService) {
        this.dominioService = dominioService;
    }

    public String getValorTipoBusqueda() {
        return valorTipoBusqueda;
    }

    public void setValorTipoBusqueda(String valorTipoBusqueda) {
        this.valorTipoBusqueda = valorTipoBusqueda;
    }

    public Boolean getBuscadorRender() {
        return buscadorRender;
    }

    public void setBuscadorRender(Boolean buscadorRender) {
        this.buscadorRender = buscadorRender;
    }

    public String getValorRecibido() {
        return valorRecibido;
    }

    public void setValorRecibido(String valorRecibido) {
        this.valorRecibido = valorRecibido;
    }

    public String getImgSenapi() {
        return imgSenapi;
    }

    public void setImgSenapi(String imgSenapi) {
        this.imgSenapi = imgSenapi;
    }

    public String getImgEscudo() {
        return imgEscudo;
    }

    public void setImgEscudo(String imgEscudo) {
        this.imgEscudo = imgEscudo;
    }

    public String getFechaHoy() {
        return fechaHoy;
    }

    public void setFechaHoy(String fechaHoy) {
        this.fechaHoy = fechaHoy;
    }

    public String getHoraHoy() {
        return horaHoy;
    }

    public void setHoraHoy(String horaHoy) {
        this.horaHoy = horaHoy;
    }

    public String getCampoBusqueda() {
        return campoBusqueda;
    }

    public void setCampoBusqueda(String campoBusqueda) {
        this.campoBusqueda = campoBusqueda;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Map<String, Object> getParametros() {
        return parametros;
    }

    public void setParametros(Map<String, Object> parametros) {
        this.parametros = parametros;
    }

    public List<BusquedaRegistroPojo> getLstBusquedaRegistroPojo() {
        return lstBusquedaRegistroPojo;
    }

    public void setLstBusquedaRegistroPojo(List<BusquedaRegistroPojo> lstBusquedaRegistroPojo) {
        this.lstBusquedaRegistroPojo = lstBusquedaRegistroPojo;
    }

    public ComunService getComunService() {
        return comunService;
    }

    public void setComunService(ComunService comunService) {
        this.comunService = comunService;
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

    public List<BusquedaSigRegistro> getLstSigRegistroBusqueda() {
        return lstSigRegistroBusqueda;
    }

    public void setLstSigRegistroBusqueda(List<BusquedaSigRegistro> lstSigRegistroBusqueda) {
        this.lstSigRegistroBusqueda = lstSigRegistroBusqueda;
    }

}
