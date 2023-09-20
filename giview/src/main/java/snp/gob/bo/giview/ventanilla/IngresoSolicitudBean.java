///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package snp.gob.bo.giview.ventanilla;
//
//import java.io.Serializable;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javax.annotation.PostConstruct;
//import javax.faces.bean.ManagedBean;
//import javax.faces.bean.ManagedProperty;
//import javax.faces.bean.ViewScoped;
//import javax.faces.context.FacesContext;
//import org.primefaces.context.RequestContext;
//import snp.gob.bo.entidades.bean.common.AbstractManagedBean;
//import snp.gob.bo.entidades.bean.login.persona;
//import snp.gob.bo.gimodel.entidad.Regional;
//import snp.gob.bo.gimodel.entidad.Dominio;
//import snp.gob.bo.gimodel.entidad.Notificacion;
//import snp.gob.bo.gimodel.servicio.RegionalService;
//import snp.gob.bo.gimodel.servicio.DominioService;
//import snp.gob.bo.gimodel.servicio.NotificacionService;
//
///**
// *
// * @author eddy
// * 
// * se debe borrar este bean ya que se esta clonando en expedienteBean
// */
//@ManagedBean(name = "ingresoSolicitudBean")
//@ViewScoped
//public class IngresoSolicitudBean extends AbstractManagedBean implements Serializable {
//    
//    
//    //<editor-fold defaultstate="collapsed" desc="Definicion de servicios y atributos... verificar estos datos">
//
//    private List<String> images;
//    private Boolean activaPanelExisteTramite;
//    private Boolean activaPanelNoExisteTramite;
//    private Long numeroFormulario = 201600000100L;
//    private List<persona> lstPersonas;
//    private String valorRadioBuscador = "SM";
//    private Boolean buscarSMRendered = true;
//    private Boolean buscarPURendered = false;
//    private Boolean buscarRERendered = false;
//    private Date fechaSolicitud = new Date();
//    private String tipoSolicitante = "J";
//    private Boolean panelSolicitanteRendered = true;
//
//    private String razonSocial;
//    private String numeroDocumento;
//    private String tipoDocumento;
//    private String lugarExpedicion;
//    private String pais;
//    private String departamento;
//    private String generoPersona;
//    private String telefono;
//    private String domicilio;
//    private String correoElectronico;
//    private String nombre;
//    private String primerApellido;
//    private String segundoApellido;
//
//    private String smNum;
//    private String smGestion;
//    private String smExt;
//    
//    
////    private String publNum;
////    private String regNum;
////    private String regExt;
//    
//    
//    @ManagedProperty("#{dominioService}")
//    private DominioService sMDominioService;
//
//    @ManagedProperty("#{regionalService}")
//    private RegionalService regionalService;
//    
//     @ManagedProperty(value = "#{notificacionService}")
//    private NotificacionService notificacionService;
//
//    private List<Dominio> lstUbicacion = new ArrayList<Dominio>();
//    private List<Dominio> lstSituacionActual = new ArrayList<Dominio>();
//    private List<Dominio> lstTipoSigno = new ArrayList<Dominio>();
//    private List<Dominio> lstTipoGenero = new ArrayList<Dominio>();
//    private List<Regional> lstRegional = new ArrayList<Regional>();
//    
//    
//    //</editor-fold>
//    
//
//    @PostConstruct
//    public void init() {
//        
//        
//        images = new ArrayList<String>();
//        images.add("logoMinisterio.png");
//
//        this.activaPanelExisteTramite = Boolean.FALSE;
//        this.activaPanelNoExisteTramite = Boolean.FALSE;
//        crearPersonasEjemplo();
//
//        try {
//            this.lstUbicacion = sMDominioService.obtenerListadoDominio("Ubicacion");
//            this.lstSituacionActual = sMDominioService.obtenerListadoDominio("SituacionActual");
//            this.lstTipoSigno = sMDominioService.obtenerListadoDominio("TipoSigno");
//            this.lstTipoGenero = sMDominioService.obtenerListadoDominio("TipoGenero");
////            this.lstRegional = regionalService.obtenerListadoRegional();
//        } catch (Exception ex) {
//            Logger.getLogger(IngresoSolicitudBean.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//    }
//    
//    
//    /*
//     * Metodo para realizar la busqueda de acuerdo a los siguientes criterios:
//     * SolicitudMarca: soma
//     * Nro Publicacion: nupu
//     * Nro Registro: nure
//     */
//    public void modificarCriterioBusqueda() {
//
//        switch (valorRadioBuscador) {
//            case "soma":
//                buscarSMRendered = true;
//                buscarPURendered = false;
//                buscarRERendered = false;
//                break;
//            case "nupu":
//                buscarSMRendered = false;
//                buscarPURendered = true;
//                buscarRERendered = false;
//                break;
//            case "nure":
//                buscarSMRendered = false;
//                buscarPURendered = false;
//                buscarRERendered = true;
//                break;
//            default:
//                buscarSMRendered = false;
//                buscarPURendered = false;
//                buscarRERendered = false;
//                break;
//        }
//    }
//    
//    
//
//    public void crearPersonasEjemplo() {
//        lstPersonas = new ArrayList<persona>();
//        persona p = new persona();
//        p.setDepartamento("La Paz");
//        p.setDireccion("Av. Buenos Aires");
//        p.setDocumento("5813084");
//        p.setEmail("cruz.gomez.victor@gmail.com");
//        p.setFax("123456");
//        p.setNacionalidad("Bolivia");
//        p.setNombre("Victor Cruz");
//        p.setPyme("6565");
//        p.setTelefono("2484848");
//
//        lstPersonas.add(p);
//
//        persona p2 = new persona();
//        p2.setDepartamento("Cochabamba");
//        p2.setDireccion("Av. Hernando Siles");
//        p2.setDocumento("123456");
//        p2.setEmail("juan.perez@gmail.com");
//        p2.setFax("654321");
//        p2.setNacionalidad("Bolivia");
//        p2.setNombre("Juan Perez");
//        p2.setPyme("654321");
//        p2.setTelefono("3565656");
//
//        lstPersonas.add(p2);
//
//    }
//
//    public void nuevoSolicitante() {
//        tipoSolicitante = "J";
//        razonSocial = "";
//        numeroDocumento = "";
//        tipoDocumento = "CI";
//        lugarExpedicion = "";
//        pais = "Bolivia";
//        departamento = "Oruro";
//        generoPersona = "M";
//        telefono = "";
//        domicilio = "";
//        correoElectronico = "";
//        nombre = "";
//        primerApellido = "";
//        segundoApellido = "";
//    }
//
//    public void adicionaSolicitante() {
//        //preguntar si tiene id para modificar o crear
//        persona p = new persona();
//        p.setNombre(razonSocial);
//        p.setDocumento(numeroDocumento);
//        p.setNacionalidad(pais);
//        p.setDepartamento(departamento);
//        p.setTelefono(telefono);
//        p.setDireccion(domicilio);
//        p.setEmail(correoElectronico);
//        lstPersonas.add(p);
//    }
//
//    public void adicionaSolicitanteJuridico() {
//        persona p = new persona();
//        p.setNombre(razonSocial);
//        p.setDocumento(numeroDocumento);
//        p.setNacionalidad(pais);
//        p.setDepartamento(departamento);
//        p.setTelefono(telefono);
//        p.setDireccion(domicilio);
//        p.setEmail(correoElectronico);
//        lstPersonas.add(p);
//    }
//
//    
//
//    public List<persona> getLstPersonas() {
//        return lstPersonas;
//    }
//
//    public void setLstPersonas(List<persona> lstPersonas) {
//        this.lstPersonas = lstPersonas;
//    }
//
//    public void selectPersona(persona persona) {
//
//
//    }
//
//    public void borrarPersona(persona persona) {
//        lstPersonas.remove(persona);
//    }
//
//    public void activaCamposNaturalJuridico() {
//
//        panelSolicitanteRendered = !tipoSolicitante.equals("N");
//    }
//
//    public String abrirPlantillaExamenForma() {
//
////        System.out.println("Ingreso aqui");
//        //   return "abrirPlantillaVentanilla";
//        return "abrirPlantillaExamenForma";
//        //recepcionDeDocumentos.xhtml
////        return null;
//    }
//
//    public String abrirPlantillaExamenFondo() {
//
////        System.out.println("Ingreso aqui");
//        //   return "abrirPlantillaVentanilla";
//        return "abrirPlantillaExamenFondo";
//        //recepcionDeDocumentos.xhtml
////        return null;
//    }
//
//    public List<String> getImages() {
//        return images;
//    }
//
//    public void setImages(List<String> images) {
//        this.images = images;
//    }
//
//    public Boolean getActivaPanelExisteTramite() {
//        return activaPanelExisteTramite;
//    }
//
//    public void setActivaPanelExisteTramite(Boolean activaPanelExisteTramite) {
//        this.activaPanelExisteTramite = activaPanelExisteTramite;
//    }
//
//    public Boolean getActivaPanelNoExisteTramite() {
//        return activaPanelNoExisteTramite;
//    }
//
//    public void setActivaPanelNoExisteTramite(Boolean activaPanelNoExisteTramite) {
//        this.activaPanelNoExisteTramite = activaPanelNoExisteTramite;
//    }
//
//    public Long getNumeroFormulario() {
//        return numeroFormulario;
//    }
//
//    public void setNumeroFormulario(Long numeroFormulario) {
//        this.numeroFormulario = numeroFormulario;
//    }
//
//    public Date getFechaSolicitud() {
//        return fechaSolicitud;
//    }
//
//    public void setFechaSolicitud(Date fechaSolicitud) {
//        this.fechaSolicitud = fechaSolicitud;
//    }
//
//    public void buscarTramitePorFormulario() {
//        //201600000100
//        if (this.numeroFormulario.equals(201600000100L)) {
//            this.activaPanelExisteTramite = Boolean.TRUE;
//            this.activaPanelNoExisteTramite = Boolean.FALSE;
//        } else {
//            this.activaPanelExisteTramite = Boolean.FALSE;
//            this.activaPanelNoExisteTramite = Boolean.TRUE;
//        }
//
//    }
//
//    public void limpiarCriterioBusqueda() {
//        this.activaPanelNoExisteTramite = Boolean.FALSE;
//        this.activaPanelExisteTramite = Boolean.FALSE;
//        this.numeroFormulario = 201600000100L;
//    }
//
//    public void viewNoti() throws Exception {
//        
//        /*Estos valores son ficticios, pero el proceso estaria bien en teoria*/
////        smNum="200";
////        smGestion="2016";
////        smExt="";
////        Notificacion notifica = new Notificacion();
////        if(this.valorRadioBuscador.equals("SM"))
////        {
////             String sm=smGestion+smNum+smExt;
////             System.out.println("El sm::"+sm);
////             List<Notificacion> listNotifica=notificacionService.listaNotificacionDatosSM(Long.parseLong(sm));
////             notifica.setTipo_tramite_notificacion("SM"); 
////             notifica.setExpediente(smNum);
////             notifica.setGestion(Integer.parseInt(smGestion));
////             notifica.setExtension(smExt);
////             //System.out.println("marca"+listNotifica.get(0).getDemandante());
////             notifica.setDemandante(listNotifica.get(0).getDemandante());
////             //System.out.println("solicitante"+listNotifica.get(0).getDemandante_solic());
////             notifica.setDemandante_solic(listNotifica.get(0).getDemandante_solic());
////             notifica.setDemandante_apod(listNotifica.get(0).getDemandante_apod());
////             notifica.setDemandante_direc(listNotifica.get(0).getDemandante_direc());
////             notifica.setDemandante_cel(listNotifica.get(0).getDemandante_cel());
////        
////        }    
////        if(this.valorRadioBuscador.equals("PU"))
////        {  //En teoria esto deberia ser asi
////             List<Notificacion> listNotifica=notificacionService.listaNotificacionDatosSMNumPubl(Long.parseLong(publNum));
////              notifica.setTipo_tramite_notificacion("NPS"); 
////             notifica.setExpediente(publNum);
////             notifica.setDemandante(listNotifica.get(0).getDemandante());
////             notifica.setDemandante_solic(listNotifica.get(0).getDemandante_solic());
////             notifica.setDemandante_apod(listNotifica.get(0).getDemandante_apod());
////             notifica.setDemandante_direc(listNotifica.get(0).getDemandante_direc());
////             notifica.setDemandante_cel(listNotifica.get(0).getDemandante_cel());
////             
////             
////        }   
////        if(this.valorRadioBuscador.equals("RE"))
////        {   //En teoria esto deberia ser asi
////           List<Notificacion> listNotifica= notificacionService.listaNotificacionDatosSMNumReg(Long.parseLong(regNum),regExt);
////            notifica.setTipo_tramite_notificacion("NR"); 
////             notifica.setExpediente(regNum);
////              notifica.setExtension(regExt);
////             notifica.setDemandante(listNotifica.get(0).getDemandante());
////             notifica.setDemandante_solic(listNotifica.get(0).getDemandante_solic());
////             notifica.setDemandante_apod(listNotifica.get(0).getDemandante_apod());
////             notifica.setDemandante_direc(listNotifica.get(0).getDemandante_direc());
////             notifica.setDemandante_cel(listNotifica.get(0).getDemandante_cel());
////        
////        }   
////        
////        
////        
////        
////        
////        Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
////        sessionMap.put("notifiObj", notifica);
////        
////        
////        
////        
////        
////        Map<String, Object> options = new HashMap<String, Object>();
////        options.put("resizable", true);
////        options.put("height", 650);
////        options.put("width", "90%");
////        options.put("contentWidth", "100%");
////        options.put("contentHeight", "100%");
////        options.put("modal", true);
////        
////        Map<String,List<String>> var= new HashMap<String,List<String>>();
////         List<String> params = new ArrayList();
////         params.add("1");//Pondria el primary key para encontrar todos los datos que necesito, aunque lo mejor es que me pase todo lo que necesito en un objeto
////         params.add("signo");//Para otros tendría que ser modificacion u oposicion o renovación
////         var.put("datosGenerales",params);       
////          RequestContext.getCurrentInstance().openDialog("/notificacion/notiPeticionDialog", options, var);    
//        
//        //RequestContext.getCurrentInstance().openDialog("/notificacion/notiPeticionDialog", options, null);
//        /*esta parte del codigo comentado  sirve para enviar al dialogo  variables para la vista*/
//        /* Map<String,List<String>> var= new HashMap<String,List<String>>();
//         List<String> a1 = new ArrayList();
//         a1.add("./../WEB-INF/facelets/templates/sipiTemplate.xhtml");
//         var.put("var",a1);
//         */
//          
//          
//    }
//
//    public void verPanelExpedienteDigital() {
//        Map<String, Object> options = new HashMap<String, Object>();
//        options.put("resizable", true);
//        options.put("height", 700);
//        options.put("width", "90%");
//        options.put("contentWidth", "100%");
//        options.put("contentHeight", "100%");
//        options.put("modal", true);
//        RequestContext.getCurrentInstance().openDialog("/signo/expedienteDigitalDialog", options, null);
//
//    }
//
//    public String abrirSeguimiento() {
//        this.setNavegaPagina("abrirExpediente");
//        return ("abrirSeguimiento");
//    }
//
//    public String abrirHistorial() {
//        this.setNavegaPagina("abrirExpediente");
//        return ("abrirPlantillaHistorial");
//    }
//
//    public String abrirObservacionTramite() {
//        System.out.println("ingreso persona");
//        this.setNavegaPagina("abrirExpediente");
//        return ("abrirObservacionTramite");
//    }
//
//    public String abrirFinalizar() {
//        return ("abrirPrincipal");
//    }
//
//    public String abrirPoderDepositado() {
//        return ("abrirLibroPoderes");
//    }
//    
//    //<editor-fold defaultstate="collapsed" desc="Definicion de servicios y atributos... verificar estos datos">
//
//    public String getValorRadioBuscador() {
//        return valorRadioBuscador;
//    }
//
//    public void setValorRadioBuscador(String valorRadioBuscador) {
//        this.valorRadioBuscador = valorRadioBuscador;
//    }
//
//    public Boolean getBuscarSMRendered() {
//        return buscarSMRendered;
//    }
//
//    public void setBuscarSMRendered(Boolean buscarSMRendered) {
//        this.buscarSMRendered = buscarSMRendered;
//    }
//
//    public Boolean getBuscarPURendered() {
//        return buscarPURendered;
//    }
//
//    public void setBuscarPURendered(Boolean buscarPURendered) {
//        this.buscarPURendered = buscarPURendered;
//    }
//
//    public Boolean getBuscarRERendered() {
//        return buscarRERendered;
//    }
//
//    public void setBuscarRERendered(Boolean buscarRERendered) {
//        this.buscarRERendered = buscarRERendered;
//    }
//
//    public String getTipoSolicitante() {
//        return tipoSolicitante;
//    }
//
//    public void setTipoSolicitante(String tipoSolicitante) {
//        this.tipoSolicitante = tipoSolicitante;
//    }
//
//    public Boolean getPanelSolicitanteRendered() {
//        return panelSolicitanteRendered;
//    }
//
//    public void setPanelSolicitanteRendered(Boolean panelSolicitanteRendered) {
//        this.panelSolicitanteRendered = panelSolicitanteRendered;
//    }
//
//    public String getRazonSocial() {
//        return razonSocial;
//    }
//
//    public void setRazonSocial(String razonSocial) {
//        this.razonSocial = razonSocial;
//    }
//
//    public String getNumeroDocumento() {
//        return numeroDocumento;
//    }
//
//    public void setNumeroDocumento(String numeroDocumento) {
//        this.numeroDocumento = numeroDocumento;
//    }
//
//    public String getTipoDocumento() {
//        return tipoDocumento;
//    }
//
//    public void setTipoDocumento(String tipoDocumento) {
//        this.tipoDocumento = tipoDocumento;
//    }
//
//    public String getLugarExpedicion() {
//        return lugarExpedicion;
//    }
//
//    public void setLugarExpedicion(String lugarExpedicion) {
//        this.lugarExpedicion = lugarExpedicion;
//    }
//
//    public String getPais() {
//        return pais;
//    }
//
//    public void setPais(String pais) {
//        this.pais = pais;
//    }
//
//    public String getDepartamento() {
//        return departamento;
//    }
//
//    public void setDepartamento(String departamento) {
//        this.departamento = departamento;
//    }
//
//    public String getGeneroPersona() {
//        return generoPersona;
//    }
//
//    public void setGeneroPersona(String generoPersona) {
//        this.generoPersona = generoPersona;
//    }
//
//    public String getTelefono() {
//        return telefono;
//    }
//
//    public void setTelefono(String telefono) {
//        this.telefono = telefono;
//    }
//
//    public String getDomicilio() {
//        return domicilio;
//    }
//
//    public void setDomicilio(String domicilio) {
//        this.domicilio = domicilio;
//    }
//
//    public String getCorreoElectronico() {
//        return correoElectronico;
//    }
//
//    public void setCorreoElectronico(String correoElectronico) {
//        this.correoElectronico = correoElectronico;
//    }
//
//    public String getNombre() {
//        return nombre;
//    }
//
//    public void setNombre(String nombre) {
//        this.nombre = nombre;
//    }
//
//    public String getPrimerApellido() {
//        return primerApellido;
//    }
//
//    public void setPrimerApellido(String primerApellido) {
//        this.primerApellido = primerApellido;
//    }
//
//    public String getSegundoApellido() {
//        return segundoApellido;
//    }
//
//    public void setSegundoApellido(String segundoApellido) {
//        this.segundoApellido = segundoApellido;
//    }
//
//    public List<Dominio> getLstUbicacion() {
//        return lstUbicacion;
//    }
//
//    public void setLstUbicacion(List<Dominio> lstUbicacion) {
//        this.lstUbicacion = lstUbicacion;
//    }
//
//    public List<Dominio> getLstSituacionActual() {
//        return lstSituacionActual;
//    }
//
//    public void setLstSituacionActual(List<Dominio> lstSituacionActual) {
//        this.lstSituacionActual = lstSituacionActual;
//    }
//
//    public List<Dominio> getLstTipoSigno() {
//        return lstTipoSigno;
//    }
//
//    public void setLstTipoSigno(List<Dominio> lstTipoSigno) {
//        this.lstTipoSigno = lstTipoSigno;
//    }
//
//    public List<Dominio> getLstTipoGenero() {
//        return lstTipoGenero;
//    }
//
//    public void setLstTipoGenero(List<Dominio> lstTipoGenero) {
//        this.lstTipoGenero = lstTipoGenero;
//    }
//
//    public List<Regional> getLstRegional() {
//        return lstRegional;
//    }
//
//    public void setLstRegional(List<Regional> lstRegional) {
//        this.lstRegional = lstRegional;
//    }
//
//    public DominioService getsMDominioService() {
//        return sMDominioService;
//    }
//
//    public void setsMDominioService(DominioService sMDominioService) {
//        this.sMDominioService = sMDominioService;
//    }
//
//    public RegionalService getRegionalService() {
//        return regionalService;
//    }
//
//    public void setRegionalService(RegionalService regionalService) {
//        this.regionalService = regionalService;
//    }
//
//    public String getSmNum() {
//        return smNum;
//    }
//
//    public void setSmNum(String smNum) {
//        this.smNum = smNum;
//    }
//
//    public String getSmGestion() {
//        return smGestion;
//    }
//
//    public void setSmGestion(String smGestion) {
//        this.smGestion = smGestion;
//    }
//
//    public String getSmExt() {
//        return smExt;
//    }
//
//    public void setSmExt(String smExt) {
//        this.smExt = smExt;
//    }
//
//    
//
//    public NotificacionService getNotificacionService() {
//        return notificacionService;
//    }
//
//    public void setNotificacionService(NotificacionService notificacionService) {
//        this.notificacionService = notificacionService;
//    }
//
//    
//    
//    //</editor-fold>
//}
