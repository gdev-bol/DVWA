/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.entidades.notificacion;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import snp.gob.bo.entidades.bean.common.AbstractManagedBean;
import snp.gob.bo.entidades.bean.common.SessionStateManagedBean;
import snp.gob.bo.gimodel.entidad.Notificacion;
import snp.gob.bo.gimodel.entidad.Usuario;
import snp.gob.bo.gimodel.servicio.ComunService;
import snp.gob.bo.gimodel.servicio.DominioService;
import snp.gob.bo.gimodel.servicio.NotificacionService;
import snp.gob.bo.gimodel.servicio.UsuarioPaginaService;
import snp.gob.bo.gimodel.servicio.UsuarioService;

/**
 *
 * @author Ruben Ramirez
 */
@ManagedBean(name = "imprimirPeticionBean")
@ViewScoped
public class ImprimirPeticionBean extends AbstractManagedBean implements Serializable {

    //<editor-fold defaultstate="collapsed" desc="Definicion de servicios y atributos">
    // variables de la vista
    private int bloque;
    private Boolean reporteRecibo = false;

    // parametros del reporte
    private Long idUsuarioSesion;
    private List<Notificacion> listaAPeticion = new ArrayList<Notificacion>();
    private List<Usuario> listUsuario = new ArrayList<Usuario>();

    // parametros del reporte
    private Map<String, Object> parametros = new HashMap<String, Object>();
    private String nroBloque;
    private String operador;
    private String fechaIng;
    private String horaIng;
    private String imgSenapi;
    private List<NotiPeticionListPojo> listNotiPeticionList = new ArrayList<NotiPeticionListPojo>();

    @ManagedProperty(value = "#{usuarioPaginaService}")
    private UsuarioPaginaService usuarioPaginaService;
    @ManagedProperty(value = "#{notificacionService}")
    private NotificacionService notificacionService;
    @ManagedProperty(value = "#{dominioService}")
    private DominioService dominioService;
    @ManagedProperty(value = "#{comunService}")
    private ComunService comunService;
    @ManagedProperty(value = "#{usuarioService}")
    private UsuarioService usuarioService;
    @ManagedProperty(value = "#{sessionState}")
    private SessionStateManagedBean sessionManagedBean;

    /**
     * Creates a new instance of BuscadorPublicacionBean
     */
    public ImprimirPeticionBean() {
    }

    @PostConstruct
    public void ImprimirPeticionBeanInit() {

        idUsuarioSesion = super.getIdUsuarioSession();

    }

    /**
     * metodo para generacion de la lista de publicaciones de acuerdo al numero
     * de gaceta.
     *
     * Creado: Ruben Ramirez Fecha: 24/11/2016
     *
     * @throws net.sf.jasperreports.engine.JRException
     * @throws java.io.IOException
     */
    public void imprimir() throws JRException, IOException, Exception {

        listaAPeticion = notificacionService.getNotificacionXbloqueXusuariosol(bloque, idUsuarioSesion);

        if (listaAPeticion.size() > 0) {

            reporteRecibo = true;

            imgSenapi = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/images/logoNuevo.jpg");
            nroBloque = "" + bloque;

            listUsuario = usuarioService.listaUsuarioXidPagina(idUsuarioSesion);
            Usuario user = listUsuario.get(0);

            operador = user.getNombre() + " " + user.getPrimer_apellido();

            // brinda formato a la fecha y hora de registros date.
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

            SimpleDateFormat formateador1 = new SimpleDateFormat("EEEEE, dd MMMM, yyyy", dateFormatSymbols);
            SimpleDateFormat formateador2 = new SimpleDateFormat("hh:mm a");
            SimpleDateFormat formateador3 = new SimpleDateFormat("dd/MM/yyyy");

            // fecha actual de al base de datos
            Date fechaPresente = comunService.obtenerFechaHoraServidor(1L);

            if (fechaPresente != null) {
                fechaIng = formateador1.format(fechaPresente);
                horaIng = formateador2.format(fechaPresente);
            }

            int i = 0;
            for (Notificacion item : listaAPeticion) {

                if (item.getDemandado_cod_estado() != "PREE" && item.getDemandante_cod_estado() != "PREE") {
                    i++;
                    NotiPeticionListPojo noti = new NotiPeticionListPojo();
                    noti.setN("" + i);
                    noti.setTramite(item.getTipo_tramite_notificacion());
                    noti.setNro(item.getExpediente());
                    noti.setGestion(""+item.getGestion());
                    noti.setExtension(item.getExtension());

                    noti.setDemandante(item.getDemandante());
                    noti.setDemandado(item.getDemandado());

                    if (item.getDemandante_fecha_noti() != null) {
                        noti.setDemandanteFecha(formateador3.format(item.getDemandante_fecha_noti()));
                    }
                    if (item.getDemandado_fecha_noti() != null) {
                        noti.setDemandadoFecha(formateador3.format(item.getDemandado_fecha_noti()));
                    }
                    noti.setDemandanteCon(item.getDemandante_con());
                    noti.setDemandadoCon(item.getDemandado_con());

                    noti.setDemandanteFojas(item.getDemandante_fojas());
                    noti.setDemandadoFojas(item.getDemandado_fojas());

                    listNotiPeticionList.add(noti);
                }
            }

            JRBeanCollectionDataSource itemsJRBean = new JRBeanCollectionDataSource(listNotiPeticionList);

            parametros.put("nroBloque", nroBloque);
            parametros.put("operador", operador);
            parametros.put("fechaIngreso", fechaIng);
            parametros.put("horaIngreso", horaIng);
            parametros.put("imgSenapi", imgSenapi);
            parametros.put("dataNoti", itemsJRBean);

            String filename = "ImprimirPeticion.pdf";
            String jasperPath = "/template/notificacion/NotiNotificacion.jasper";
            this.generateReport(parametros, jasperPath, filename);

            listNotiPeticionList.clear();

        } 
    }

    /**
     * Metodo para generar el reporte y pasarle en formato StreamContente a la
     * variable de sesion stream. Para posterior despliegue en la vista.
     *
     * Creado: Ruben Ramirez Fecha: 24/11/2016
     *
     * @param params
     * @param jasperPath
     * @param fileName
     *
     */
    public void generateReport(Map<String, Object> params, String jasperPath, String fileName) throws IOException, JRException, Exception {
        String relativeWebPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath(jasperPath);
        File file = new File(relativeWebPath);
        ByteArrayOutputStream outputStream = getOutputStreamFromReport(params, file.getPath());
        sessionManagedBean.setStream(getStreamContentFromOutputStream(outputStream, "application/pdf", fileName));

    }

    /**
     * Metodo para generar el reporte en formato ByteArrayOutputStream.
     *
     * Creado: Ruben Ramirez Fecha: 24/11/2016
     *
     * @param map
     * @param pathJasper
     *
     * @return ByteArrayOutputStream
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
     * Creado: Ruben Ramirez Fecha: 24/11/2016
     *
     * @param os
     * @param contentType
     * @param nameFile
     *
     * @return StreamedContent
     */
    public static StreamedContent getStreamContentFromOutputStream(ByteArrayOutputStream os, String contentType, String nameFile) throws Exception, IOException {
        StreamedContent file = null;
        InputStream is = new ByteArrayInputStream(os.toByteArray());
        file = new DefaultStreamedContent(is, "application/pdf", nameFile);
        return file;
    }

    public int getBloque() {
        return bloque;
    }

    public void setBloque(int bloque) {
        this.bloque = bloque;
    }

    public Boolean getReporteRecibo() {
        return reporteRecibo;
    }

    public void setReporteRecibo(Boolean reporteRecibo) {
        this.reporteRecibo = reporteRecibo;
    }

    public Long getIdUsuarioSesion() {
        return idUsuarioSesion;
    }

    public void setIdUsuarioSesion(Long idUsuarioSesion) {
        this.idUsuarioSesion = idUsuarioSesion;
    }

    public List<Notificacion> getListaAPeticion() {
        return listaAPeticion;
    }

    public void setListaAPeticion(List<Notificacion> listaAPeticion) {
        this.listaAPeticion = listaAPeticion;
    }

    public List<Usuario> getListUsuario() {
        return listUsuario;
    }

    public void setListUsuario(List<Usuario> listUsuario) {
        this.listUsuario = listUsuario;
    }

    public Map<String, Object> getParametros() {
        return parametros;
    }

    public void setParametros(Map<String, Object> parametros) {
        this.parametros = parametros;
    }

    public String getNroBloque() {
        return nroBloque;
    }

    public void setNroBloque(String nroBloque) {
        this.nroBloque = nroBloque;
    }

    public String getOperador() {
        return operador;
    }

    public void setOperador(String operador) {
        this.operador = operador;
    }

    public String getFechaIng() {
        return fechaIng;
    }

    public void setFechaIng(String fechaIng) {
        this.fechaIng = fechaIng;
    }

    public String getHoraIng() {
        return horaIng;
    }

    public void setHoraIng(String horaIng) {
        this.horaIng = horaIng;
    }

    public String getImgSenapi() {
        return imgSenapi;
    }

    public void setImgSenapi(String imgSenapi) {
        this.imgSenapi = imgSenapi;
    }

    public List<NotiPeticionListPojo> getListNotiPeticionList() {
        return listNotiPeticionList;
    }

    public void setListNotiPeticionList(List<NotiPeticionListPojo> listNotiPeticionList) {
        this.listNotiPeticionList = listNotiPeticionList;
    }

    public UsuarioPaginaService getUsuarioPaginaService() {
        return usuarioPaginaService;
    }

    public void setUsuarioPaginaService(UsuarioPaginaService usuarioPaginaService) {
        this.usuarioPaginaService = usuarioPaginaService;
    }

    public NotificacionService getNotificacionService() {
        return notificacionService;
    }

    public void setNotificacionService(NotificacionService notificacionService) {
        this.notificacionService = notificacionService;
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

    public UsuarioService getUsuarioService() {
        return usuarioService;
    }

    public void setUsuarioService(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }
    
    public SessionStateManagedBean getSessionManagedBean() {
        return sessionManagedBean;
    }
 
    public void setSessionManagedBean(SessionStateManagedBean sessionManagedBean) {
        this.sessionManagedBean = sessionManagedBean;
    }

}
