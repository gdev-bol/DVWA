/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.entidades.bean.renovacion;

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
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.primefaces.context.RequestContext;
import snp.gob.bo.entidades.bean.common.AbstractManagedBean;
import snp.gob.bo.gimodel.entidad.ClaseNiza;
import snp.gob.bo.gimodel.entidad.Dominio;
import snp.gob.bo.gimodel.entidad.RenRenovacion;
import snp.gob.bo.gimodel.entidad.RenResolucion;
import snp.gob.bo.gimodel.entidad.RenSolicitanteApoderado;
import snp.gob.bo.gimodel.entidad.RenSolicitudRenovacion;
import snp.gob.bo.gimodel.entidad.Usuario;
import snp.gob.bo.gimodel.enums.EnumDominio;
import snp.gob.bo.gimodel.enums.EnumNombreDominio;
import snp.gob.bo.gimodel.enums.EnumTipoPersona;
import snp.gob.bo.gimodel.servicio.ClaseNizaService;
import snp.gob.bo.gimodel.servicio.ComunService;
import snp.gob.bo.gimodel.servicio.DominioService;
import snp.gob.bo.gimodel.servicio.ModModificacionService;
import snp.gob.bo.gimodel.servicio.ModResolucionService;
import snp.gob.bo.gimodel.servicio.RenRenovacionService;
import snp.gob.bo.gimodel.servicio.RenResolucionService;
import snp.gob.bo.gimodel.servicio.RenSolicitanteApoderadoService;
import snp.gob.bo.gimodel.servicio.RenSolicitudRenovacionService;
import snp.gob.bo.gimodel.servicio.UsuarioService;

/**
 *
 * @author chano rojas
 */
@ManagedBean(name = "buscadorRenovacionBean")
@ViewScoped
public class BuscadorRenovacionBean extends AbstractManagedBean implements Serializable {

    /**
     * Creates a new instance of BuscadorModificacionBean
     */
    public BuscadorRenovacionBean() {
    }

    private String valorRadio = "BS";
    private Boolean buscadorRender = true;
    private String valorRecibido;
    private String template;
    private List<Dominio> lstTipoModificacion;
    private String tipoModificacion;
    private String estadoModificacion;
    private List<Dominio> lstSituacionActual;
    private Date fechaInicio;
    private Date fechaFin;
//    private List<RenRenovacion> lstModificacion;
    private List<RenRenovacion> lstRenovacion = new ArrayList();
    private List<RenSolicitudRenovacion> lstSolicitudRenovacion = new ArrayList();
    private List<RenSolicitudRenovacion> lstSolicitudRenovacionSeleccionados = new ArrayList();
    private String filtroSimple;
    private String textoBusquedaSimple = "";
    private Boolean muestraLink = false;
    private Integer totalBusqueda;
    private Integer numeroRegistro;
    private String serieRegistro = "C";
    private Usuario usuarioLogueado;
    // atributos reportes
    private String fechaHoy;
    private String horaHoy;
    private String campoBusqueda;
    private String criterio;
    private String imgSenapi;
    private String imgEscudo;
    private Map<String, Object> parametros = new HashMap();
    private List<RenovacionPojo> lstRenovacionPojo = new ArrayList();

    @ManagedProperty("#{dominioService}")
    private DominioService dominioService;

    @ManagedProperty("#{modModificacionService}")
    private ModModificacionService modModificacionService;

    @ManagedProperty("#{modResolucionService}")
    private ModResolucionService modResolucionService;

    @ManagedProperty("#{renRenovacionService}")
    private RenRenovacionService renRenovacionService;

    @ManagedProperty("#{renResolucionService}")
    private RenResolucionService renResolucionService;

    @ManagedProperty("#{renSolicitudRenovacionService}")
    private RenSolicitudRenovacionService renSolicitudRenovacionService;

    @ManagedProperty("#{comunService}")
    private ComunService comunService;
    @ManagedProperty("#{usuarioService}")
    private UsuarioService usuarioService;

    @ManagedProperty("#{renSolicitanteApoderadoService}")
    private RenSolicitanteApoderadoService renSolicitanteApoderadoService;
    
    @ManagedProperty("#{claseNizaService}")
    private ClaseNizaService claseNizaService;

    @PostConstruct
    public void initBuscadorRenovacionBean() {
        try {
            if (getIdUsuarioSession() != null) {
                usuarioLogueado = usuarioService.buscaUsuarioPorIdUsuario(getIdUsuarioSession());
//            regional = regionalService.obtenerRegionalPorIdRegiona(usuario.getIdregional());
            }

            Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
            valorRecibido = params.get("valor");
//        if (params.get("valor") != null) {
            template = "./../WEB-INF/facelets/templates/DialogTemplate.xhtml";
//        } else {
//            
//            template = "./../WEB-INF/facelets/templates/Template.xhtml";
//            System.out.println("############################       P A G I N A");
//            muestraLink=true;
//        }

        } catch (Exception e) {
        }
        if (getNavegaPagina() != null) {

        }

    }

    public String cerrarDialogo() {
        if (valorRecibido != null) {
            RequestContext.getCurrentInstance().closeDialog("Exit");
            return "";
        } else {
            return "examenModificacion";
        }
    }

    public void activaCambios() {

        //System.out.println("valor radio" + valorRadio);
        switch (valorRadio) {
            case "BS":
                buscadorRender = true;
                break;
            case "BC":
                buscadorRender = false;
                break;
            case "BR":
                buscadorRender = false;
                break;
            default:
                break;
        }

        //System.out.println("buscador render" + buscadorRender);
    }

    public void accionBuscar() {
        //System.out.println("filtros " + tipoModificacion + "  " + estadoModificacion + "  inicio " + fechaInicio + "  fecha fin " + fechaFin);
//        lstRenovacion = renRenovacionService.lista_modmodificacion_avanzada(tipoModificacion, estadoModificacion, fechaInicio, fechaFin);
        //System.out.println("tamanio de la lista  " + lstRenovacion.size());
    }

    public void Limpiar() {
        numeroRegistro = null;
        lstRenovacion = new ArrayList<>();
//        
//        
//        
//        System.out.println("filtros " + tipoModificacion + "  " + estadoModificacion + "  inicio " + fechaInicio + "  fecha fin " + fechaFin);
////        lstRenovacion = renRenovacionService.lista_modmodificacion_avanzada(tipoModificacion, estadoModificacion, fechaInicio, fechaFin);
//        System.out.println("tamanio de la lista  " + lstRenovacion.size());
    }

    public void limpiarRegistro() {
        textoBusquedaSimple = "";
        lstRenovacion = new ArrayList<>();
//        
//        
//        
//        System.out.println("filtros " + tipoModificacion + "  " + estadoModificacion + "  inicio " + fechaInicio + "  fecha fin " + fechaFin);
////        lstRenovacion = renRenovacionService.lista_modmodificacion_avanzada(tipoModificacion, estadoModificacion, fechaInicio, fechaFin);
//        System.out.println("tamanio de la lista  " + lstRenovacion.size());
    }

    public void accionBuscarSimple() throws Exception {

        if (!textoBusquedaSimple.equals("")) {
            lstSolicitudRenovacion = renRenovacionService.lista_renRenovacion_simpleSolicitudes(filtroSimple, textoBusquedaSimple);
            if (!lstSolicitudRenovacion.isEmpty()) {
                totalBusqueda = lstSolicitudRenovacion.size();

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Total de registros encontrados" + "=" + lstSolicitudRenovacion.size(), ""));

            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "No se encontraron registros de la busqueda", ""));

            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Escriba un texto para buscar", ""));
        }

    }

    public void accionBuscarRegistro() throws Exception {
        //System.out.println("numero de registro" + numeroRegistro);
        filtroSimple="";
        if (numeroRegistro != null) {
            lstSolicitudRenovacion = renRenovacionService.lista_renRenovacion_simpleRegistro(filtroSimple, numeroRegistro, serieRegistro);
            if (!lstSolicitudRenovacion.isEmpty()) {
                totalBusqueda = lstSolicitudRenovacion.size();

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Total de registros encontrados" + "=" + lstSolicitudRenovacion.size(), ""));

            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "No se encontraron registros de la busqueda", ""));

            }
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

    
    //modificar el metodo
    public String devuelveClaseNiza(RenSolicitudRenovacion renSolicitudRenovacion){
        
    if(renSolicitudRenovacion.getIdclase_niza_reclasificacion()!=0){
    
        return renSolicitudRenovacion.getIdclase_niza_reclasificacion().toString();
    }
        return renSolicitudRenovacion.getIdclase_niza_registrado().toString();
    
    }
    
    
    public String numeroRenovacion(RenSolicitudRenovacion renSolicitudRenovacion) {
        RenRenovacion renRenovacion = renRenovacionService.obtieneRenovacionPorIdSolicitud(renSolicitudRenovacion.getIdsolicitudrenovacion());
        String numeroRenovacion = "";
        if (renRenovacion.getIdrenovacion() != null) {
            numeroRenovacion = renRenovacion.getNumero_renovacion() + "-" + renRenovacion.getSerie_renovacion();
        }
        return numeroRenovacion;
    }

    public String devuelveOrdenRenovacion(RenSolicitudRenovacion renSolicitudRenovacion) {
        RenRenovacion renRenovacion = renRenovacionService.obtieneRenovacionPorIdSolicitud(renSolicitudRenovacion.getIdsolicitudrenovacion());
        String ordenRenovacion = "";
        if (renRenovacion.getIdrenovacion() != null) {
            ordenRenovacion = devuelveLiteralNumero(renRenovacion.getOrden_renovacion());
        }
        return ordenRenovacion;
    }

    public String devuelveLiteralNumero(int numeroOrden) {
        if (numeroOrden > 0) {
            return renRenovacionService.numeroOrdinal(numeroOrden);
        }
        return "?";
    }

    public String devuelveSolicitante(RenSolicitudRenovacion renSolicitudRenovacion) throws Exception {
        List<RenSolicitanteApoderado> lstSolicitante = renSolicitanteApoderadoService.buscaSolicitanteApoderadoPoridSolicitudyTipoPersona(renSolicitudRenovacion.getIdsolicitudrenovacion(), EnumTipoPersona.SOLICITANTE.getCodigo());
        String textoSolicitante = "";
        for (RenSolicitanteApoderado item : lstSolicitante) {
            String dato = devuelveNombreJuridicoONatural(item.getNombre_razonsocial(), item.getPrimer_apellido(), item.getSegundo_apellido());
            textoSolicitante = textoSolicitante + dato.trim() + ", ";
        }
        if (textoSolicitante.length() > 2) {
            textoSolicitante = textoSolicitante.substring(0, textoSolicitante.length() - 2);
        }
        return textoSolicitante;
    }

    public String devuelveApoderado(RenSolicitudRenovacion renSolicitudRenovacion) throws Exception {
        List<RenSolicitanteApoderado> lstSolicitante = renSolicitanteApoderadoService.buscaSolicitanteApoderadoPoridSolicitudyTipoPersona(renSolicitudRenovacion.getIdsolicitudrenovacion(), EnumTipoPersona.APODERADO.getCodigo());
        String textoSolicitante = "";
        for (RenSolicitanteApoderado item : lstSolicitante) {
            String dato = devuelveNombreJuridicoONatural(item.getNombre_razonsocial(), item.getPrimer_apellido(), item.getSegundo_apellido());
            textoSolicitante = textoSolicitante + dato.trim() + ", ";
        }
        if (textoSolicitante.length() > 2) {
            textoSolicitante = textoSolicitante.substring(0, textoSolicitante.length() - 2);
        }
        return textoSolicitante;
    }

    public int devuelveResolucion(Long idrenovacion) {
        RenResolucion resolucion = renResolucionService.obtieneRenResolucionPorIdRenovacion(idrenovacion);
        if (resolucion.getIdresolucion() != null) {
            return resolucion.getNumero_resolucion();
        }
        return 0;
    }

    public int devuelveGestionRes(Long idrenovacion) {
        RenResolucion resolucion = renResolucionService.obtieneRenResolucionPorIdRenovacion(idrenovacion);
        if (resolucion.getIdresolucion() != null) {
            return resolucion.getNumero_resolucion();
        }
        return 0;
    }

    public String devuelveEstado(RenSolicitudRenovacion renSolicitudRenovacion) {
        String Estado = "";
        return Estado;
    }

    public String devueveEstadoRenovacion(String codigo) throws Exception {
        if (codigo != null && !codigo.equals("")) {
            return dominioService.obtenerNombrePorCodigoDominio(codigo, EnumDominio.ESTADO_RENOVACION.getCodigo());
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

    public String accionNavegarExpedienteRenovacion(RenSolicitudRenovacion renRenovacion) {
        //System.out.println("idrensolicitudrenovacion" + renRenovacion.getIdsolicitudrenovacion());
        setidRenSolicitudRenovacionSession(renRenovacion.getIdsolicitudrenovacion());
        return "examenRenovacion";
    }

    public String urlInformacionRenovacion(RenSolicitudRenovacion renSolicitudRenovacion, Long idUsuarioLogin) {

        String valUSuario = usuarioLogueado.getIdusuario().toString();
        String val = "";

        if (renSolicitudRenovacion != null) {
            val = renSolicitudRenovacion.getIdsolicitudrenovacion().toString();
            if (idUsuarioLogin != null) {
                valUSuario = idUsuarioLogin.toString();
            }

            return "this.form.target = '_blank'; window.open('../renovacion/examenRenovacion.xhtml?ThrEimhaJd5=" + val + "&UkYJ0g3jLwc=" + valUSuario + "');";
        }
        return "null";
        //setidRenSolicitudRenovacionSession(renSolicitudRenovacion.getIdsolicitudrenovacion());
        //System.out.println("idsolicitud" + renSolicitudRenovacion.getIdsolicitudrenovacion());
    }

    public String encuentraNumeroSr(Long idRenSolictudRenovacion) throws Exception {
        RenSolicitudRenovacion renSolicitudRenovacionVista = new RenSolicitudRenovacion();
        return String.format("%6s", renSolicitudRenovacionService.obtenerRenSolicitudXIdRenSolicitud(idRenSolictudRenovacion).getSr()).replace(' ', '0');
        // renSolicitudRenovacionService.obtenerRenSolicitudXIdRenSolicitud(idRenSolictudRenovacion).getSr();
    }

    public int encuentraGestion(Long idRenSolictudRenovacion) throws Exception {
        RenSolicitudRenovacion renSolicitudRenovacionVista = new RenSolicitudRenovacion();
        return renSolicitudRenovacionService.obtenerRenSolicitudXIdRenSolicitud(idRenSolictudRenovacion).getGestion_sr();
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

        if (lstSolicitudRenovacion.size() > 0) {
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
            SimpleDateFormat formateador2 = new SimpleDateFormat("hh:mm:ss a");
            SimpleDateFormat formateador3 = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a");

            imgSenapi = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/images/logoNuevo.jpg");
            imgEscudo = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/images/esc-Bolivia");

            Date fechaPresente = comunService.obtenerFechaHoraServidor(1L);

            if (fechaPresente != null) {
                fechaHoy = formateador1.format(fechaPresente);
                horaHoy = formateador2.format(fechaPresente).toLowerCase();
            }

            if (filtroSimple.equals("SOLI")) {
                campoBusqueda = "Nombre del Titular";
            } else if (filtroSimple.equals("APOD")) {
                campoBusqueda = "Apoderado";
            } else if (filtroSimple.equals("SIGN")) {
                campoBusqueda = "Nombre del Signo";
            }

            criterio = textoBusquedaSimple;

            
            for (RenSolicitudRenovacion item : lstSolicitudRenovacion) {
                RenovacionPojo rp = new RenovacionPojo();

                rp.setSr("SR - " + encuentraNumeroSr(item.getIdsolicitudrenovacion()) + "-" + encuentraGestion(item.getIdsolicitudrenovacion()));
                rp.setMarca(item.getSigno_registrado());
                rp.setClase(this.devuelveClaseNiza(item.getIdclase_niza_registrado()).intValue());
                if (item.getFecha_ingreso() != null) {
                    rp.setFechaSolicitud(formateador3.format(item.getFecha_ingreso()).toLowerCase());
                }
                rp.setNroRenovacion(numeroRenovacion(item));
                rp.setOrden(devuelveOrdenRenovacion(item));
                if (item.getNumero_registro_registrado() != 0) {
                    if (item.getSerie_registro_registrado() != null) {
                        rp.setNroRegistro(item.getNumero_registro_registrado() + "-" + item.getSerie_registro_registrado());
                    } else {
                        rp.setNroRegistro("" + item.getNumero_registro_registrado());
                    }
                }
                rp.setSolicitante(devuelveSolicitante(item));
                rp.setApoderado(devuelveApoderado(item));
                rp.setEstado(devueveEstadoRenovacion(item.getEstado_renovacion()));

                lstRenovacionPojo.add(rp);
            }

            parametros.put("fechaHoy", fechaHoy);
            parametros.put("horaHoy", horaHoy);
            parametros.put("campoBusqueda", campoBusqueda);
            parametros.put("criterio", criterio);
            parametros.put("imgSenapi", imgSenapi);
            parametros.put("imgEscudo", imgEscudo);

            String filename = "BusquedaRenovacion.pdf";
            String jasperPath = "/template/renovacion/busquedaRenovacion.jasper";
            this.PDF(parametros, jasperPath, lstRenovacionPojo, filename);
            lstRenovacionPojo.clear();
        }
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
    
    public Long devuelveClaseNiza(Long idClaseNiza) {
        Long clase_niza = 0l;
        try {
            if (idClaseNiza != null) {
                ClaseNiza claseNiza = claseNizaService.listarClaseNiza_id(idClaseNiza);
                if (claseNiza != null) {
                    clase_niza = claseNiza.getNumeroClaseNiza();
                }
            }
        } catch (Exception e) {
            Logger.getLogger(ClaseNiza.class.getName()).log(Level.SEVERE, null, e);
        }
        return clase_niza;
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

    public ModModificacionService getModModificacionService() {
        return modModificacionService;
    }

    public void setModModificacionService(ModModificacionService modModificacionService) {
        this.modModificacionService = modModificacionService;
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

    public String getValorRecibido() {
        return valorRecibido;
    }

    public void setValorRecibido(String valorRecibido) {
        this.valorRecibido = valorRecibido;
    }

    public List<RenRenovacion> getLstRenovacion() {
        return lstRenovacion;
    }

    public void setLstRenovacion(List<RenRenovacion> lstRenovacion) {
        this.lstRenovacion = lstRenovacion;
    }

    public RenRenovacionService getRenRenovacionService() {
        return renRenovacionService;
    }

    public void setRenRenovacionService(RenRenovacionService renRenovacionService) {
        this.renRenovacionService = renRenovacionService;
    }

    public RenResolucionService getRenResolucionService() {
        return renResolucionService;
    }

    public void setRenResolucionService(RenResolucionService renResolucionService) {
        this.renResolucionService = renResolucionService;
    }

    public RenSolicitudRenovacionService getRenSolicitudRenovacionService() {
        return renSolicitudRenovacionService;
    }

    public void setRenSolicitudRenovacionService(RenSolicitudRenovacionService renSolicitudRenovacionService) {
        this.renSolicitudRenovacionService = renSolicitudRenovacionService;
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

    public String getCriterio() {
        return criterio;
    }

    public void setCriterio(String criterio) {
        this.criterio = criterio;
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

    public Map<String, Object> getParametros() {
        return parametros;
    }

    public void setParametros(Map<String, Object> parametros) {
        this.parametros = parametros;
    }

    public List<RenovacionPojo> getLstRenovacionPojo() {
        return lstRenovacionPojo;
    }

    public void setLstRenovacionPojo(List<RenovacionPojo> lstRenovacionPojo) {
        this.lstRenovacionPojo = lstRenovacionPojo;
    }

    public ComunService getComunService() {
        return comunService;
    }

    public void setComunService(ComunService comunService) {
        this.comunService = comunService;
    }

    public List<RenSolicitudRenovacion> getLstSolicitudRenovacion() {
        return lstSolicitudRenovacion;
    }

    public void setLstSolicitudRenovacion(List<RenSolicitudRenovacion> lstSolicitudRenovacion) {
        this.lstSolicitudRenovacion = lstSolicitudRenovacion;
    }

    public RenSolicitanteApoderadoService getRenSolicitanteApoderadoService() {
        return renSolicitanteApoderadoService;
    }

    public void setRenSolicitanteApoderadoService(RenSolicitanteApoderadoService renSolicitanteApoderadoService) {
        this.renSolicitanteApoderadoService = renSolicitanteApoderadoService;
    }

    public List<RenSolicitudRenovacion> getLstSolicitudRenovacionSeleccionados() {
        return lstSolicitudRenovacionSeleccionados;
    }

    public void setLstSolicitudRenovacionSeleccionados(List<RenSolicitudRenovacion> lstSolicitudRenovacionSeleccionados) {
        this.lstSolicitudRenovacionSeleccionados = lstSolicitudRenovacionSeleccionados;
    }

    public Integer getTotalBusqueda() {
        return totalBusqueda;
    }

    public void setTotalBusqueda(Integer totalBusqueda) {
        this.totalBusqueda = totalBusqueda;
    }

    public Integer getNumeroRegistro() {
        return numeroRegistro;
    }

    public void setNumeroRegistro(Integer numeroRegistro) {
        this.numeroRegistro = numeroRegistro;
    }

    public String getSerieRegistro() {
        return serieRegistro;
    }

    public void setSerieRegistro(String serieRegistro) {
        this.serieRegistro = serieRegistro;
    }

    public Usuario getUsuarioLogueado() {
        return usuarioLogueado;
    }

    public void setUsuarioLogueado(Usuario usuarioLogueado) {
        this.usuarioLogueado = usuarioLogueado;
    }

    public UsuarioService getUsuarioService() {
        return usuarioService;
    }

    public void setUsuarioService(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    public ClaseNizaService getClaseNizaService() {
        return claseNizaService;
    }

    public void setClaseNizaService(ClaseNizaService claseNizaService) {
        this.claseNizaService = claseNizaService;
    }

}
