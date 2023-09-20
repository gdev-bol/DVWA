package snp.gob.bo.entidades.bean.common;

import java.io.Serializable;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@ManagedBean(name = "sessionState")
@SessionScoped
public class SessionStateManagedBean implements Serializable {

    //Variable que almacena el login del usuario
    private String login;
    private String pass = "";
    private String idSistema = "1";
    private Long idUsuario;
    private Long idEtapa;
    private Long idSigno;
    //Bandera que indica si el usuario esta autenticado
    private boolean authenticated;
    private final String pathTemplate = "/WEB-INF/facelets/templates/Template.xhtml";
    private final String pathTemplateLogin = "/WEB-INF/facelets/templates/TemplateLogin.xhtml";
    private String formatoFecha = "dd/MM/yyyy";
    private String formatoFechaHora = "dd/MM/yyyy HH:mm:ss";
    private String formatoFechaHoraSinSegundo = "dd/MM/yyyy HH:mm";
    private String formatoHora = "HH:mm:ss";
    private String paginaAnteriorDigitalizacion;
    private Boolean activaPantallaInicio = true;
    private String codigoPagina;
    private String navegaPagina = "";
    private Boolean muestraMenu = false;
    private Long idmodificacion;
    private Long idSolicitudRenovacion;
    private StreamedContent stream;
    private Date fechaIngresoSolicitud;
    //com

    //atributos session tipotramite y numeroformulario, modulo de ventanilla
    private String numeroFormularioSolicitud;
    private String tipoTramiteSolicitud;
    
    public SessionStateManagedBean() {
    }
    
//<editor-fold defaultstate="collapsed" desc="Getters y setters atributos">
//    private final String pathsipiTemplate = "/WEB-INF/facelets/templates/sipiTemplate.xhtml";
//    private final String pathTemplateDos = "/WEB-INF/facelets/templates/siTemplate.xhtml";
//    private String skinLayout = "bootstrap";
//    private String idioma = "es";
//    private Long idUsuarioLogin;
//    private Long idUsuarioLoginVerifica;
//    
//    private Long idTramite;
//    private int activaBotonSiguienteWizard;
//    private Boolean editaFormulario = false;
//    private String horaSalidaUsuarioInterno;
//    private Long idTramiteMyS;
//    private int pestaniaExamenDeFondo;
//    private Boolean formularioPI100;
//    private Boolean formularioRenovacion;
//    private Boolean formularioModificacionNombre;
//    private Boolean formularioModificacionDireccion;
//    private Boolean formularioTransferencia;
//    private Boolean formularioFusion;
//    private Boolean formularioLicenciaDeUso;
//    private Long idModuloMensajes;
//    private String pagina;
//    private Boolean concedeSuperior = false;
//    private Long idFlujo;
//    private Long idPersona;
//    private Long idEmpresa;
//    private Long idTramiteModificacion;
//    private Long idTramiteRenovacion;
//    private Long idNotificacion;
//    private Long idRecibo;
//    //Modulo Digitalizacion
    //variable de session que permite la navegación de la página de dig a otra
    
//    private Boolean verificaActivarNotificacion = false;
//    private Boolean verificaActivarObservacionUsuario = false;
//    //variable de session que realiza el control de Impresion en Ventanilla
//    private Boolean verificaImpresionVentanilla = false;
//    //Variable de sesion para que el usuario pueda realizar el cambio de contraseña
//    private Long idUsuarioModificarContrasenia;
//    //variable de Sesion para que el usuario le aparezca su bandeja de principio
// 
    
//    private String codigoTramite;
    
//    private DefaultStreamedContent StreamArchivo;
//    private StreamedContent StreamArchivoContent;
//
//    //Modulo Recaudaciones
//    private String rutaArchivoPdf;
//    private StreamedContent streamArchivoDos = null;
//
//    //Modulo Busquedas Foneticas
//    private StreamedContent streamImagen = null;
//    
//    
    //navegacion pagina
    

    
//    
//    
//    public Long getIdRecibo() {
//        return idRecibo;
//    }
//
//    public void setIdRecibo(Long idRecibo) {
//        this.idRecibo = idRecibo;
//    }

    /**
     * Creates a new instance of SessionStateManagedBean
     */
    

    

//    public String getPathTemplateDos() {
//        return pathTemplateDos;
//    }
//    public String getPathsipiTemplate() {
//        return pathsipiTemplate;
//    }
//
//
//    public String getSkinLayout() {
//        return skinLayout;
//    }
//
//    public void setSkinLayout(String skinLayout) {
//        this.skinLayout = skinLayout;
//    }
    

//    public String getIdioma() {
//        return idioma;
//    }
//
//    public void setIdioma(String idioma) {
//        this.idioma = idioma;
//    }
//
//    public Long getIdUsuarioLogin() {
//        return idUsuarioLogin;
//    }
//
//    public void setIdUsuarioLogin(Long idUsuarioLogin) {
//        this.idUsuarioLogin = idUsuarioLogin;
//    }
//
//    public Long getIdUsuarioLoginVerifica() {
//        return idUsuarioLoginVerifica;
//    }
//
//    public void setIdUsuarioLoginVerifica(Long idUsuarioLoginVerifica) {
//        this.idUsuarioLoginVerifica = idUsuarioLoginVerifica;
//    }
    

//    public Long getIdTramite() {
//        return idTramite;
//    }
//
//    public void setIdTramite(Long idTramite) {
//        this.idTramite = idTramite;
//    }
//
//    public int getActivaBotonSiguienteWizard() {
//        return activaBotonSiguienteWizard;
//    }
//
//    public void setActivaBotonSiguienteWizard(int activaBotonSiguienteWizard) {
//        this.activaBotonSiguienteWizard = activaBotonSiguienteWizard;
//    }
//
//    public Boolean getEditaFormulario() {
//        return editaFormulario;
//    }
//
//    public void setEditaFormulario(Boolean editaFormulario) {
//        this.editaFormulario = editaFormulario;
//    }
//
//    public String getHoraSalidaUsuarioInterno() {
//        return horaSalidaUsuarioInterno;
//    }
//
//    public void setHoraSalidaUsuarioInterno(String horaSalidaUsuarioInterno) {
//        this.horaSalidaUsuarioInterno = horaSalidaUsuarioInterno;
//    }
//
//    public Long getIdTramiteMyS() {
//        return idTramiteMyS;
//    }
//
//    public void setIdTramiteMyS(Long idTramiteMyS) {
//        this.idTramiteMyS = idTramiteMyS;
//    }
//
//    public int getPestaniaExamenDeFondo() {
//        return pestaniaExamenDeFondo;
//    }
//
//    public void setPestaniaExamenDeFondo(int pestaniaExamenDeFondo) {
//        this.pestaniaExamenDeFondo = pestaniaExamenDeFondo;
//    }
//
//    public Boolean getFormularioPI100() {
//        return formularioPI100;
//    }
//
//    public void setFormularioPI100(Boolean formularioPI100) {
//        this.formularioPI100 = formularioPI100;
//    }
//
//    public Boolean getFormularioRenovacion() {
//        return formularioRenovacion;
//    }
//
//    public void setFormularioRenovacion(Boolean formularioRenovacion) {
//        this.formularioRenovacion = formularioRenovacion;
//    }
//
//    public Boolean getFormularioModificacionNombre() {
//        return formularioModificacionNombre;
//    }
//
//    public void setFormularioModificacionNombre(Boolean formularioModificacionNombre) {
//        this.formularioModificacionNombre = formularioModificacionNombre;
//    }
//
//    public Boolean getFormularioModificacionDireccion() {
//        return formularioModificacionDireccion;
//    }
//
//    public void setFormularioModificacionDireccion(Boolean formularioModificacionDireccion) {
//        this.formularioModificacionDireccion = formularioModificacionDireccion;
//    }
//
//    public Boolean getFormularioTransferencia() {
//        return formularioTransferencia;
//    }
//
//    public void setFormularioTransferencia(Boolean formularioTransferencia) {
//        this.formularioTransferencia = formularioTransferencia;
//    }
//
//    public Boolean getFormularioFusion() {
//        return formularioFusion;
//    }
//
//    public void setFormularioFusion(Boolean formularioFusion) {
//        this.formularioFusion = formularioFusion;
//    }
//
//    public Boolean getFormularioLicenciaDeUso() {
//        return formularioLicenciaDeUso;
//    }
//
//    public void setFormularioLicenciaDeUso(Boolean formularioLicenciaDeUso) {
//        this.formularioLicenciaDeUso = formularioLicenciaDeUso;
//    }
//
//    public Long getIdModuloMensajes() {
//        return idModuloMensajes;
//    }
//
//    public void setIdModuloMensajes(Long idModuloMensajes) {
//        this.idModuloMensajes = idModuloMensajes;
//    }
//
// 
//    public Boolean getConcedeSuperior() {
//        return concedeSuperior;
//    }
//
//    public void setConcedeSuperior(Boolean concedeSuperior) {
//        this.concedeSuperior = concedeSuperior;
//    }
//
//    public Long getIdFlujo() {
//        return idFlujo;
//    }
//
//    public void setIdFlujo(Long idFlujo) {
//        this.idFlujo = idFlujo;
//    }
//
//    public Long getIdTramiteModificacion() {
//        return idTramiteModificacion;
//    }
//
//    public void setIdTramiteModificacion(Long idTramiteModificacion) {
//        this.idTramiteModificacion = idTramiteModificacion;
//    }
//
//    public Long getIdTramiteRenovacion() {
//        return idTramiteRenovacion;
//    }
//
//    public void setIdTramiteRenovacion(Long idTramiteRenovacion) {
//        this.idTramiteRenovacion = idTramiteRenovacion;
//    }
//
//    public Long getIdPersona() {
//        return idPersona;
//    }
//
//    public void setIdPersona(Long idPersona) {
//        this.idPersona = idPersona;
//    }
//
//    public Long getIdEmpresa() {
//        return idEmpresa;
//    }
//
//    public void setIdEmpresa(Long idEmpresa) {
//        this.idEmpresa = idEmpresa;
//    }
//
    
//
//    public Boolean getVerificaActivarNotificacion() {
//        return verificaActivarNotificacion;
//    }
//
//    public void setVerificaActivarNotificacion(Boolean verificaActivarNotificacion) {
//        this.verificaActivarNotificacion = verificaActivarNotificacion;
//    }
//
//    public Long getIdNotificacion() {
//        return idNotificacion;
//    }
//
//    public void setIdNotificacion(Long idNotificacion) {
//        this.idNotificacion = idNotificacion;
//    }
//
//    public Boolean getVerificaActivarObservacionUsuario() {
//        return verificaActivarObservacionUsuario;
//    }
//
//    public void setVerificaActivarObservacionUsuario(Boolean verificaActivarObservacionUsuario) {
//        this.verificaActivarObservacionUsuario = verificaActivarObservacionUsuario;
//    }

    

//    public Long getIdUsuarioModificarContrasenia() {
//        return idUsuarioModificarContrasenia;
//    }
//
//    public void setIdUsuarioModificarContrasenia(Long idUsuarioModificarContrasenia) {
//        this.idUsuarioModificarContrasenia = idUsuarioModificarContrasenia;
//    }
//
//    public Boolean isVerificaImpresionVentanilla() {
//        return verificaImpresionVentanilla;
//    }
//
//    public void setVerificaImpresionVentanilla(Boolean verificaImpresionVentanilla) {
//        this.verificaImpresionVentanilla = verificaImpresionVentanilla;
//    }
//
//
//    public String getCodigoTramite() {
//        return codigoTramite;
//    }
//
//    public void setCodigoTramite(String codigoTramite) {
//        this.codigoTramite = codigoTramite;
//    }
//    
//
//    public DefaultStreamedContent getStreamArchivo() {
//        return StreamArchivo;
//    }
//
//    public void setStreamArchivo(DefaultStreamedContent StreamArchivo) {
//        this.StreamArchivo = StreamArchivo;
//    }
//
//    public StreamedContent getStreamArchivoContent() {
//        return StreamArchivoContent;
//    }
//
//    public void setStreamArchivoContent(StreamedContent StreamArchivoContent) {
//        this.StreamArchivoContent = StreamArchivoContent;
//    }
//
//    public String getRutaArchivoPdf() {
//        return rutaArchivoPdf;
//    }
//
//    public void setRutaArchivoPdf(String rutaArchivoPdf) {
//        this.rutaArchivoPdf = rutaArchivoPdf;
//    }
//
//    public StreamedContent getStreamArchivoDos() {
//        return streamArchivoDos;
//    }
//
//    public void setStreamArchivoDos(StreamedContent streamArchivoDos) {
//        this.streamArchivoDos = streamArchivoDos;
//    }
//
//    public StreamedContent getStreamImagen() {
//        return streamImagen;
//    }
//
//    public void setStreamImagen(StreamedContent streamImagen) {
//        this.streamImagen = streamImagen;
//    }
//
//    public String getPagina() {
//        return pagina;
//    }
//
//    public void setPagina(String pagina) {
//        this.pagina = pagina;
//    }
    /**
     * **********************
     */
    
    //</editor-fold>
    
    
    /**
     * @return the login
     */
    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getIdSistema() {
        return idSistema;
    }

    public void setIdSistema(String idSistema) {
        this.idSistema = idSistema;
    }

    public Boolean getMuestraMenu() {
        return muestraMenu;
    }

    public void setMuestraMenu(Boolean muestraMenu) {
        this.muestraMenu = muestraMenu;
    }

    public Date getFechaIngresoSolicitud() {
        return fechaIngresoSolicitud;
    }

    public void setFechaIngresoSolicitud(Date fechaIngresoSolicitud) {
        this.fechaIngresoSolicitud = fechaIngresoSolicitud;
    }
    
    public String getNumeroFormulario() {
        return numeroFormularioSolicitud;
    }

    public void setNumeroFormulario(String numeroFormulario) {
        this.numeroFormularioSolicitud = numeroFormulario;
    }

    public String getTipoTramiteSolicitud() {
        return tipoTramiteSolicitud;
    }

    public void setTipoTramiteSolicitud(String tipoTramiteSolicitud) {
        this.tipoTramiteSolicitud = tipoTramiteSolicitud;
    }
    
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * @return the authenticated
     */
    public boolean isAuthenticated() {
        return authenticated;
    }

    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
    }

    public String getPathTemplate() {
        return pathTemplate;
    }

    public String getPathTemplateLogin() {
        return pathTemplateLogin;
    }
    
    public boolean isAnyError() {
        return FacesContext.getCurrentInstance().getMessageList().size() > 0;
    }
    
    public String getFormatoFecha() {
        return formatoFecha;
    }

    public void setFormatoFecha(String formatoFecha) {
        this.formatoFecha = formatoFecha;
    }

    public String getFormatoFechaHora() {
        return formatoFechaHora;
    }

    public void setFormatoFechaHora(String formatoFechaHora) {
        this.formatoFechaHora = formatoFechaHora;
    }

    public String getFormatoHora() {
        return formatoHora;
    }

    public void setFormatoHora(String formatoHora) {
        this.formatoHora = formatoHora;
    }
    
    public String getFormatoFechaHoraSinSegundo() {
        return formatoFechaHoraSinSegundo;
    }

    public void setFormatoFechaHoraSinSegundo(String formatoFechaHoraSinSegundo) {
        this.formatoFechaHoraSinSegundo = formatoFechaHoraSinSegundo;
    }
    void setpagina(String pagina) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    String getpagina() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getNavegaPagina() {
        return navegaPagina;
    }

    public void setNavegaPagina(String navegaPagina) {
        this.navegaPagina = navegaPagina;
    }

    public Boolean getActivaPantallaInicio() {
        return activaPantallaInicio;
    }

    public void setActivaPantallaInicio(Boolean activaPantallaInicio) {
        this.activaPantallaInicio = activaPantallaInicio;
    }

    public String getCodigoPagina() {
        return codigoPagina;
    }

    public void setCodigoPagina(String codigoPagina) {
        this.codigoPagina = codigoPagina;
    }
    
    public String getPaginaAnteriorDigitalizacion() {
        return paginaAnteriorDigitalizacion;
    }

    public void setPaginaAnteriorDigitalizacion(String paginaAnteriorDigitalizacion) {
        this.paginaAnteriorDigitalizacion = paginaAnteriorDigitalizacion;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Long getIdmodificacion() {
        return idmodificacion;
    }

    public void setIdmodificacion(Long idmodificacion) {
        this.idmodificacion = idmodificacion;
    }
 
        public Long getIdSigno() {
        return idSigno;
    }

    public void setIdSigno(Long idSigno) {
        this.idSigno = idSigno;
    }

    public Long getIdSolicitudRenovacion() {
        return idSolicitudRenovacion;
    }

    public void setIdSolicitudRenovacion(Long idSolicitudRenovacion) {
        this.idSolicitudRenovacion = idSolicitudRenovacion;
    }

    public StreamedContent getStream() {
        return stream;
    }

    public void setStream(StreamedContent stream) {
        this.stream = stream;
    }

    public Long getIdEtapa() {
        return idEtapa;
    }

    public void setIdEtapa(Long idEtapa) {
        this.idEtapa = idEtapa;
    }
    
    
}
