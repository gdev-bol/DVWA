/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.entidades.bean.gestionCalidad;

import snp.gob.bo.entidades.bean.busqueda.*;
import static com.google.common.base.Charsets.ISO_8859_1;
import static com.google.common.base.Charsets.UTF_8;
import java.io.ByteArrayOutputStream;
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
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.primefaces.context.RequestContext;
import snp.gob.bo.entidades.bean.common.AbstractManagedBean;
import snp.gob.bo.gimodel.entidad.BusquedaMarcaResultado;
import snp.gob.bo.gimodel.entidad.SigSignoMarca;
import snp.gob.bo.gimodel.servicio.BusquedaMarcaResultadoService;
import snp.gob.bo.gimodel.servicio.ComunService;
import snp.gob.bo.gimodel.servicio.SigSignoMarcaService;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import snp.gob.bo.gimodel.pojo.GestionCalidadPojo;
import snp.gob.bo.gimodel.servicio.GestionCalidadService;

/**
 *
 * @author Eddy Valero
 * @version 1.0, 17/10/2016
 *
 */
@ManagedBean(name = "gestionCalidadBean")
@ViewScoped
public class GestionCalidadBean extends AbstractManagedBean implements Serializable {

    //<editor-fold defaultstate="collapsed" desc="Definicion de servicios y atributos">
    private String valorRadio = "CS";
    private Boolean criterioRendered = true;
    private String fraseBusqueda = "";
    private String criterioBusqueda = "";
    private String parametroBusqueda = "";
    private String seleccionComboBusqueda = "";
    private int valorClaseNiza;
    private int porcenta;
    private String etiqueta;
    private double tiempoEjecucion;
    private SigSignoMarca signoMarca;

    // atributos reportes
    private String imgSenapi = "";
    private String imgEscudo = "";
    private String fechaHoy = "";
    private String horaHoy = "";
    private String campoBusqueda = "";
    private String campo = "";
    private String frase = "";
    private String criterio = "";
    private String clase = "";

    private List<GestionCalidadPojo> listaVentanilla = new ArrayList<GestionCalidadPojo>();

    private Map<String, Object> parametros = new HashMap<String, Object>();

    BusquedaMarcaResultado busquedaMarcaResultado;
    private List<BusquedaMarcaResultado> listaBusquedaMarcaResultado = new ArrayList<BusquedaMarcaResultado>();

    @ManagedProperty("#{busquedaMarcaResultadoService}")
    private BusquedaMarcaResultadoService busquedaMarcaResultadoService;

    @ManagedProperty("#{comunService}")
    private ComunService comunService;

    @ManagedProperty("#{sigSignoMarcaService}")
    private SigSignoMarcaService sigSignoMarcaService;
    @ManagedProperty("#{gestionCalidadService}")
    private GestionCalidadService gestionCalidadService;

    //</editor-fold>
    public GestionCalidadBean() {
    }

    @PostConstruct
    public void BusquedaSignosInit() {
        this.etiqueta = "";

    }

    public void activaCambios() {

        switch (valorRadio) {
            case "CS":
                criterioRendered = true;
                break;
            case "CF":
                criterioRendered = false;
                break;
            default:
                criterioRendered = false;
                break;
        }
    }

    public void accionSeleccionComboBusqueda() {
        ;

    }

    public void criterio() {
        criterioBusqueda = "%" + fraseBusqueda + "%";
    }

    /**
     * *
     * Método para realizar la busqueda de signos
     *
     * Creado: Eddy Valero Fecha:17/10/2016
     *
     */
    public void accionRealizarBusquedaMarca() {

        try {
            Long timeStart, timeEnd;

            timeStart = System.currentTimeMillis();
            //ingresar a realizar las busqueda
            this.listaBusquedaMarcaResultado = this.busquedaMarcaResultadoService.realizarBusquedaMarca(
                    this.seleccionComboBusqueda,
                    this.criterioBusqueda,
                    this.valorClaseNiza);

            timeEnd = System.currentTimeMillis();
            this.setTiempoEjecucion((timeEnd - timeStart) / 100);

        } catch (Exception ex) {
            Logger.getLogger(GestionCalidadBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * *
     * Método para realizar la navegación a la vista de signos
     *
     * Creado: Eddy Valero Fecha:31/10/2016
     *
     * @param idSignoMarca
     * @return
     */
    //ver si se puede aplicar la logica de un popup externo
    public String accionNavegarExpedienteSigno(Long idSignoMarca) {

        setIdSignoSession(idSignoMarca);
        // RequestContext.getCurrentInstance().closeDialog("Exit");
//        return "examenModificacion";
        return "examenSignos";

    }

    public String urlInformacionSM(String idSignoMarca, String idUsuarioLogin) {
        return "this.form.target = '_blank'; window.open('../signo/examenSignos.xhtml?ThrEimhaJd5=" + idSignoMarca + "&UkYJ0g3jLwc=" + idUsuarioLogin + "');";
    }

    public String urlInformacionSM2(String idSignoMarca, String idUsuarioLogin) {
        return "this.form.target = '_blank'; window.open('../signo/examenSignos.xhtml?ThrEimhaJd5=" + idSignoMarca + "&UkYJ0g3jLwc=" + idUsuarioLogin + "');";
    }

    public void listaReporte() {
        System.out.println("entro a metodo de listado");
        listaVentanilla = gestionCalidadService.lista_gestionCalidadPojos();
        System.out.println("el tamaniolist" + listaVentanilla.size());
    }

    /**
     * Metodo para llenar los datos del reporte y su generacion en formato pdf
     *
     * Creado: Ruben Ramirez Fecha: 20/10/2016
     *
     * @param IdSignoM
     */
    public void abrirExpedienteSignos(Long IdSignoM) {
        if (IdSignoM != null) {
            setIdSignoSession(IdSignoM);
            setNavegaPagina("template");
            Map<String, Object> options = new HashMap<String, Object>();
            options.put("closable", true);
            options.put("resizable", false);
            options.put("height", 700);
            options.put("width", 1220);
            options.put("contentWidth", "100%");
            options.put("contentHeight", "100%");
            options.put("modal", true);
//            Map<String, List<String>> var = new HashMap<String, List<String>>();
//            List<String> params = new ArrayList();
//            params.add("1");
//            params.add(EnumPrefijoTablas.MODIFICACION.getCodigo());
//            params.add(modificacion.getIdmodificacion().toString());
//            params.add(modificacion.getSigla() + "-" + String.format("%6s", modificacion.getNumero()).replace(' ', '0') + "-" + modificacion.getGestion());
//
//            var.put("datosEnviados", params);
            RequestContext.getCurrentInstance().openDialog("examenSignos", options, null);
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "No se ha podido encontrar el expediemte del Signo Principal", ""));
        }
//        System.out.println("METODO PARA NAVEGAR AL EXPEDIENTE  "+signoMarca);
//        if(signoMarca!=null){
//            setIdSignoSession(signoMarca.getIdSignoMarca());
//            setNavegaPagina("template");
//            setIdmodificacion(modificacion.getIdmodificacion());
//            System.out.println("idsession   "+getIdSignoSession()+"  PAGINA  "+getNavegaPagina());
//            return "examenSignos";
//        }
//        else{
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "No se ha podido encontrar el expediemte del Signo", ""));
//            return "";            
//        }

    }

    public void imprimir() throws JRException, IOException, Exception {

        if (listaBusquedaMarcaResultado.size() > 0) {
            imgSenapi = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/images/logoNuevo.jpg");
            imgEscudo = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/images/esc-Bolivia");

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
            SimpleDateFormat formateador3 = new SimpleDateFormat("EEEEE, dd MMMMM, yyyy", dateFormatSymbols);
            SimpleDateFormat formateador4 = new SimpleDateFormat("h:mm a");

            // fecha actual de la base de datos
            Date fechaPresente = comunService.obtenerFechaHoraServidor(1L);

            if (fechaPresente != null) {
                fechaHoy = formateador3.format(fechaPresente);
                horaHoy = formateador4.format(fechaPresente).toLowerCase();
            }

            if (valorRadio.equals("CS")) {
                campoBusqueda = "Por comparacion de sílabas";
            }

            if (valorRadio.equals("CF")) {
                campoBusqueda = "Por comparacion Fonetica";
            }

            if (seleccionComboBusqueda.equals("SIGN")) {
                campo = "Nombre de Marca (ó Signo Distintivo) y clase";
            }

            if (seleccionComboBusqueda.equals("SOLI")) {
                campo = "Nombre del Solicitante o Titular";
            }

            if (seleccionComboBusqueda.equals("APOD")) {
                campo = "Nombre del Apoderado o Representante";
            }

            if (criterioBusqueda != null) {
                criterio = criterioBusqueda;
            }

            if (fraseBusqueda != null) {
                frase = fraseBusqueda;
            }

            clase = "" + valorClaseNiza;

            parametros.put("imgSenapi", imgSenapi);
            parametros.put("imgEscudo", imgEscudo);
            parametros.put("fechaHoy", fechaHoy);
            parametros.put("horaHoy", horaHoy);
            parametros.put("campoBusqueda", campoBusqueda);
            parametros.put("campo", campo);
            byte pfrase[] = frase.getBytes(ISO_8859_1);
            frase = new String(pfrase, UTF_8);
            parametros.put("frase", frase.toUpperCase());
            byte pcriterio[] = criterio.getBytes(ISO_8859_1);
            criterio = new String(pcriterio, UTF_8);
            parametros.put("criterio", criterio.toUpperCase());
            parametros.put("clase", clase);

            String filename = "BusquedaSignos.pdf";
            String jasperPath = "/template/solicitudes/BusquedaSignos.jasper";
            this.PDF(parametros, jasperPath, listaBusquedaMarcaResultado, filename);
        }
    }

    /**
     * Metodo para generar el reporte en formato pdf
     *
     * Creado: Ruben Ramirez Fecha: 20/10/2016
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
        JasperPrint print = new JasperPrint();
        JRBeanCollectionDataSource source = new JRBeanCollectionDataSource(dataSource, false);
        print = JasperFillManager.fillReport(file.getPath(), params, source);
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-Disposition", "attachment; filename=" + fileName);
        ServletOutputStream stream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(print, stream);
        FacesContext.getCurrentInstance().responseComplete();
    }

    /**
     * Metodo para generar el reporte en formato pdf
     *
     * Creado: Ruben Ramirez Fecha: 20/10/2016
     *
     */
    public JRDataSource getDatos(List<?> listaObjeto) {
        return new JRBeanCollectionDataSource(listaObjeto);
    }

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

    public String calcularColor(int parecido) {

        if (parecido == 100) {
            return "fb956c"; // color naranja FF6400 anterior EDBB5C de7b55, fca752
        } else {
            return "FEFFD2"; // color amarillo FEFFD2 anterior FAFAAF
        }
    }

    //<editor-fold defaultstate="collapsed" desc="Definicion de getters y setters">
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

    public String getCampo() {
        return campo;
    }

    public void setCampo(String campo) {
        this.campo = campo;
    }

    public String getFrase() {
        return frase;
    }

    public void setFrase(String frase) {
        this.frase = frase;
    }

    public String getCriterio() {
        return criterio;
    }

    public void setCriterio(String criterio) {
        this.criterio = criterio;
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

    public String getValorRadio() {
        return valorRadio;
    }

    public void setValorRadio(String valorRadio) {
        this.valorRadio = valorRadio;
    }

    public Boolean getCriterioRendered() {
        return criterioRendered;
    }

    public void setCriterioRendered(Boolean criterioRendered) {
        this.criterioRendered = criterioRendered;
    }

    public String getFraseBusqueda() {
        return fraseBusqueda;
    }

    public void setFraseBusqueda(String fraseBusqueda) {
        this.fraseBusqueda = fraseBusqueda;
    }

    public String getCriterioBusqueda() {
        return criterioBusqueda;
    }

    public void setCriterioBusqueda(String criterioBusqueda) {
        this.criterioBusqueda = criterioBusqueda;
    }

    public String getParametroBusqueda() {
        return parametroBusqueda;
    }

    public void setParametroBusqueda(String parametroBusqueda) {
        this.parametroBusqueda = parametroBusqueda;
    }

    public String getSeleccionComboBusqueda() {
        return seleccionComboBusqueda;
    }

    public void setSeleccionComboBusqueda(String seleccionComboBusqueda) {
        this.seleccionComboBusqueda = seleccionComboBusqueda;
    }

    public int getValorClaseNiza() {
        return valorClaseNiza;
    }

    public void setValorClaseNiza(int valorClaseNiza) {
        this.valorClaseNiza = valorClaseNiza;
    }

    public int getPorcenta() {
        return porcenta;
    }

    public void setPorcenta(int porcenta) {
        this.porcenta = porcenta;
    }

    public String getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    public double getTiempoEjecucion() {
        return tiempoEjecucion;
    }

    public void setTiempoEjecucion(double tiempoEjecucion) {
        this.tiempoEjecucion = tiempoEjecucion;
    }

    public BusquedaMarcaResultado getBusquedaMarcaResultado() {
        return busquedaMarcaResultado;
    }

    public SigSignoMarca getSignoMarca() {
        return signoMarca;
    }

    public void setSignoMarca(SigSignoMarca signoMarca) {
        this.signoMarca = signoMarca;
    }

    public SigSignoMarcaService getSigSignoMarcaService() {
        return sigSignoMarcaService;
    }

    public void setSigSignoMarcaService(SigSignoMarcaService sigSignoMarcaService) {
        this.sigSignoMarcaService = sigSignoMarcaService;
    }

    public void setBusquedaMarcaResultado(BusquedaMarcaResultado busquedaMarcaResultado) {
        this.busquedaMarcaResultado = busquedaMarcaResultado;
    }

    public List<BusquedaMarcaResultado> getListaBusquedaMarcaResultado() {
        return listaBusquedaMarcaResultado;
    }

    public void setListaBusquedaMarcaResultado(List<BusquedaMarcaResultado> listaBusquedaMarcaResultado) {
        this.listaBusquedaMarcaResultado = listaBusquedaMarcaResultado;
    }

    public BusquedaMarcaResultadoService getBusquedaMarcaResultadoService() {
        return busquedaMarcaResultadoService;
    }

    public void setBusquedaMarcaResultadoService(BusquedaMarcaResultadoService busquedaMarcaResultadoService) {
        this.busquedaMarcaResultadoService = busquedaMarcaResultadoService;
    }

    //</editor-fold>
    public String getClase() {
        return clase;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }

    public List<GestionCalidadPojo> getListaVentanilla() {
        System.out.println("ingreso a metodo vista");

//        return listaVentanilla=gestionCalidadService.lista_gestionCalidadPojos();
        return listaVentanilla;

    }

    public void setListaVentanilla(List<GestionCalidadPojo> listaVentanilla) {
        this.listaVentanilla = listaVentanilla;
    }

    public GestionCalidadService getGestionCalidadService() {
        return gestionCalidadService;
    }

    public void setGestionCalidadService(GestionCalidadService gestionCalidadService) {
        this.gestionCalidadService = gestionCalidadService;
    }

}
