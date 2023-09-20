package snp.gob.bo.entidades.bean.common;

import java.util.Date;
import java.util.Locale;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.NoneScoped;
import javax.faces.context.FacesContext;
import org.primefaces.model.StreamedContent;

@ManagedBean
@NoneScoped
public class AbstractManagedBean {

    //3. Inyeccion de un Bean 
    @ManagedProperty(value = "#{sessionState}")
    private SessionStateManagedBean sessionManagedBean;

    public SessionStateManagedBean getSessionManagedBean() {
        return sessionManagedBean;
    }

    public void setSessionManagedBean(SessionStateManagedBean sessionManagedBean) {
        this.sessionManagedBean = sessionManagedBean;
    }

    public String getLoginSession() {
        return getSessionManagedBean().getLogin();
    }

    public void setLoginSession(String login) {
        getSessionManagedBean().setLogin(login);
    }

    public Long getIdEtapaSession() {
        return getSessionManagedBean().getIdEtapa();
    }

    public void setIdEtapaSession(Long idEtapa) {
        getSessionManagedBean().setIdEtapa(idEtapa);
    }

    public StreamedContent getStreamSession() {
        return getSessionManagedBean().getStream();
    }

    public void setStreamSession(StreamedContent stream) {
        getSessionManagedBean().setStream(stream);
    }

    //metodo que realiza la eliminación de variables que fueron creadas.
    public void eliminarVariablesSession() {
        sessionManagedBean.setNumeroFormulario(null);
        sessionManagedBean.setTipoTramiteSolicitud(null);
        sessionManagedBean.setNavegaPagina(null);
        setIdSignoSession(null); 
    }

    //<editor-fold defaultstate="collapsed" desc="Borrar este bloque">
//
//    public String getIdiomaSession() {
//        Locale locacion = new Locale(getSessionManagedBean().getIdioma());
//        FacesContext.getCurrentInstance().getViewRoot().setLocale(locacion);
//        return getSessionManagedBean().getIdioma();
//    }
//    public void resetSessionVariables() {
//        sessionManagedBean.setIdTramite(null);
//        sessionManagedBean.setFormularioRenovacion(false);
//        sessionManagedBean.setFormularioModificacionNombre(false);
//        sessionManagedBean.setFormularioModificacionDireccion(false);
//        sessionManagedBean.setFormularioTransferencia(false);
//        sessionManagedBean.setFormularioFusion(false);
//        sessionManagedBean.setFormularioLicenciaDeUso(false);
//        sessionManagedBean.setConcedeSuperior(false);
//        sessionManagedBean.setIdFlujo(null);
//        sessionManagedBean.setIdPersona(null);
//        sessionManagedBean.setIdEmpresa(null);
//        sessionManagedBean.setPaginaAnteriorDigitalizacion(null);
//        sessionManagedBean.setIdTramiteModificacion(null);
//        sessionManagedBean.setIdNotificacion(null);
//        sessionManagedBean.setVerificaActivarNotificacion(false);
//        sessionManagedBean.setVerificaActivarObservacionUsuario(false);
//        sessionManagedBean.setActivaPantallaInicio(false);
//        sessionManagedBean.setCodigoTramite(null);
//        sessionManagedBean.setIdRecibo(null);
//        sessionManagedBean.setIdTramiteModificacion(null);
//        sessionManagedBean.setIdTramiteRenovacion(null);
//    }
//
//    public void setIdiomaSession(String idioma) {
//        getSessionManagedBean().setIdioma(idioma);
//    }
//
//    public Long getIdUsuarioLoginSession() {
//        return getSessionManagedBean().getIdUsuarioLogin();
//    }
//
//    public void setIdUsuarioLoginSession(Long idUsuarioLogin) {
//        getSessionManagedBean().setIdUsuarioLogin(idUsuarioLogin);
//    }
//
//    public Long getIdUsuarioLoginVerificaSession() {
//        return getSessionManagedBean().getIdUsuarioLoginVerifica();
//    }
//
//    public void setIdUsuarioLoginVerificaSession(Long idUsuarioLoginVerifica) {
//        getSessionManagedBean().setIdUsuarioLoginVerifica(idUsuarioLoginVerifica);
//    }
//
//    public String getFormatoFecha() {
//        return getSessionManagedBean().getFormatoFecha();
//    }
//
//    public String getFormatoFechaHora() {
//        return getSessionManagedBean().getFormatoFechaHora();
//    }
//
//    public void setPaginaSession(String pagina) {
//        getSessionManagedBean().setPagina(pagina);
//    }
//
//    public String getPaginaSession() {
//        return getSessionManagedBean().getpagina();
//    }
//
//    public Long getIdTramiteSession() {
//        return getSessionManagedBean().getIdTramite();
//    }
//
//    public void setIdTramiteSession(Long idTramite) {
//        getSessionManagedBean().setIdTramite(idTramite);
//    }
//
//    public int getActivaBotonSiguienteWizardSession() {
//        return getSessionManagedBean().getActivaBotonSiguienteWizard();
//    }
//
//    public void setActivaBotonSiguienteWizardSession(int activaBotonSiguienteWizard) {
//        getSessionManagedBean().setActivaBotonSiguienteWizard(activaBotonSiguienteWizard);
//    }
//
//    public Boolean getEditaFormularioSession() {
//        return getSessionManagedBean().getEditaFormulario();
//    }
//
//    public void setEditaFormularioSession(Boolean editaFormulario) {
//        getSessionManagedBean().setEditaFormulario(editaFormulario);
//    }
//
//    public String getHoraSalidaUsuarioInternoSession() {
//        return getSessionManagedBean().getHoraSalidaUsuarioInterno();
//    }
//
//    public void setHoraSalidaUsuarioInternoSession(String horaSalidaUsuarioInterno) {
//        getSessionManagedBean().setHoraSalidaUsuarioInterno(horaSalidaUsuarioInterno);
//    }
//
//    public Long getIdTramiteMySSession() {
//        return getSessionManagedBean().getIdTramiteMyS();
//    }
//
//    public void setIdTramiteMySSession(Long idTramiteMyS) {
//        getSessionManagedBean().setIdTramiteMyS(idTramiteMyS);
//    }
//
//    public int getPestaniaExamenDeFondoSession() {
//        return getSessionManagedBean().getPestaniaExamenDeFondo();
//    }
//
//    public void setPestaniaExamenDeFondoSession(int pestaniaExamenDeFondo) {
//        getSessionManagedBean().setPestaniaExamenDeFondo(pestaniaExamenDeFondo);
//    }
//
//    public Boolean getFormularioPI100Session() {
//        return getSessionManagedBean().getFormularioPI100();
//    }
//
//    public void setFormularioPI100Session(Boolean formularioPI100) {
//        getSessionManagedBean().setFormularioPI100(formularioPI100);
//    }
//
//    public Boolean getFormularioRenovacionSession() {
//        return getSessionManagedBean().getFormularioRenovacion();
//    }
//
//    public void setFormularioRenovacionSession(Boolean formularioRenovacion) {
//        getSessionManagedBean().setFormularioRenovacion(formularioRenovacion);
//    }
//
////<editor-fold defaultstate="collapsed" desc="Variables de sesion Formulario de Modificacion">
//    public Boolean getFormularioModificacionNombreSession() {
//        return getSessionManagedBean().getFormularioModificacionNombre();
//    }
//
//    public void setFormularioModificacionNombreSession(Boolean formularioModificacionNombre) {
//        getSessionManagedBean().setFormularioModificacionNombre(formularioModificacionNombre);
//    }
//
//    public Boolean getFormularioModificacionDireccionSession() {
//        return getSessionManagedBean().getFormularioModificacionDireccion();
//    }
//
//    public void setFormularioModificacionDireccionSession(Boolean formularioModificacionDireccion) {
//        getSessionManagedBean().setFormularioModificacionDireccion(formularioModificacionDireccion);
//    }
//
//    public Boolean getFormularioTransferenciaSession() {
//        return getSessionManagedBean().getFormularioTransferencia();
//    }
//
//    public void setFormularioTransferenciaSession(Boolean formularioTransferencia) {
//        getSessionManagedBean().setFormularioTransferencia(formularioTransferencia);
//    }
//
//    public Boolean getFormularioFusionSession() {
//        return getSessionManagedBean().getFormularioFusion();
//    }
//
//    public void setFormularioFusionSession(Boolean formularioFusion) {
//        getSessionManagedBean().setFormularioFusion(formularioFusion);
//    }
//
//    public Boolean getFormularioLicenciaDeUsoSession() {
//        return getSessionManagedBean().getFormularioLicenciaDeUso();
//    }
//
//    public void setFormularioLicenciaDeUsoSession(Boolean formularioLicenciaDeUso) {
//        getSessionManagedBean().setFormularioLicenciaDeUso(formularioLicenciaDeUso);
//    }
////</editor-fold>
//
//    public void setIdModuloMensajesSession(Long idModuloMensajes) {
//        getSessionManagedBean().setIdModuloMensajes(idModuloMensajes);
//    }
//
//    public Long getIdModuloMensajesSession() {
//        return getSessionManagedBean().getIdModuloMensajes();
//    }
//
//    public Boolean getConcedeSuperiorSession() {
//        return getSessionManagedBean().getConcedeSuperior();
//    }
//
//    public void setConcedeSuperiorSession(Boolean concedeSuperior) {
//        getSessionManagedBean().setConcedeSuperior(concedeSuperior);
//    }
//
////    public Long getIdFlujoSession() {
////        return sessionManagedBean.getIdFlujo();
////    }
////
////    public void setIdFlujoSession(Long idFlujo) {
////        sessionManagedBean.setIdFlujo(idFlujo);
////    }
//    public Long getIdFlujoSession() {
//        return getSessionManagedBean().getIdFlujo();
//    }
//
//    public void setIdFlujoSession(Long idFlujo) {
//        getSessionManagedBean().setIdFlujo(idFlujo);
//    }
//
//    public Long getIdTramiteModificacionSession() {
//        return sessionManagedBean.getIdTramiteModificacion();
//    }
//
//    public void setIdTramiteModificacionSession(Long idTramiteModificacion) {
//        sessionManagedBean.setIdTramiteModificacion(idTramiteModificacion);
//    }
//
//    public Long getIdTramiteRenovacionSession() {
//        return sessionManagedBean.getIdTramiteRenovacion();
//    }
//
//    public void setIdTramiteRenovacionSession(Long idTramiteRenovacion) {
//        sessionManagedBean.setIdTramiteRenovacion(idTramiteRenovacion);
//    }
//
//    public Long getIdPersonaSession() {
//        return sessionManagedBean.getIdPersona();
//    }
//
//    public void setIdPersonaSession(Long idPersona) {
//        sessionManagedBean.setIdPersona(idPersona);
//    }
//
//    public Long getIdEmpresaSession() {
//        return sessionManagedBean.getIdEmpresa();
//    }
//
//    public void setIdEmpresaSession(Long idEmpresa) {
//        sessionManagedBean.setIdEmpresa(idEmpresa);
//    }
//
//
//    public Long getIdNotificacionSession() {
//        return sessionManagedBean.getIdNotificacion();
//    }
//
//    public void setIdNotificacionSession(Long idNotificacion) {
//        sessionManagedBean.setIdNotificacion(idNotificacion);
//    }
//
//    public Boolean getVerificaActivarObservacionUsuario() {
//        return sessionManagedBean.getVerificaActivarObservacionUsuario();
//    }
//
//    public void setVerificaActivarObservacionUsuario(Boolean verificaActivarObservacionUsuario) {
//        sessionManagedBean.setVerificaActivarObservacionUsuario(verificaActivarObservacionUsuario);
//    }
//
//    public Boolean getVerificaActivarNotificacion() {
//        return sessionManagedBean.getVerificaActivarNotificacion();
//    }
//
//    public void setVerificaActivarNotificacion(Boolean verificarActivarNotificacion) {
//        sessionManagedBean.setVerificaActivarNotificacion(verificarActivarNotificacion);
//    }
//
//    public Long getIdUsuarioModificarContraseniaSession() {
//        return getSessionManagedBean().getIdUsuarioModificarContrasenia();
//    }
//
//    public void setIdUsuarioModificarContraseniaSession(Long idUsuarioModificarContrasenia) {
//        getSessionManagedBean().setIdUsuarioModificarContrasenia(idUsuarioModificarContrasenia);
//    }
//
//
//    public void setCodigoTramiteSession(String codigoTramite) {
//        getSessionManagedBean().setCodigoTramite(codigoTramite);
//    }
//
//    public String getCodigoTramiteSession() {
//        return getSessionManagedBean().getCodigoTramite();
//    }
//
//
//    public Long getIdReciboSession() {
//        return sessionManagedBean.getIdRecibo();
//    }
//
//    public void setIdReciboSession(Long idRecibo) {
//        sessionManagedBean.setIdRecibo(idRecibo);
//    }
    /**
     * *************************
     */
    //</editor-fold>
    public String getPaginaAnteriorDigitalizacion() {
        return sessionManagedBean.getPaginaAnteriorDigitalizacion();
    }

    public void setPaginaAnteriorDigitalizacion(String paginaAnteriorDigitalizacion) {
        sessionManagedBean.setPaginaAnteriorDigitalizacion(paginaAnteriorDigitalizacion);
    }

    public Boolean getActivaPantallaInicioSession() {
        return getSessionManagedBean().getActivaPantallaInicio();
    }

    public void setActivaPantallaInicioSession(Boolean activaPantallaInicio) {
        getSessionManagedBean().setActivaPantallaInicio(activaPantallaInicio);
    }

    public void setCodigoPaginaSession(String codigoPagina) {
        getSessionManagedBean().setCodigoPagina(codigoPagina);
    }

    public String getCodigoPaginaSession() {
        return getSessionManagedBean().getCodigoPagina();
    }

    public String getNavegaPagina() {
        return sessionManagedBean.getNavegaPagina();
    }

    public void setNavegaPagina(String navegaPagina) {
        this.sessionManagedBean.setNavegaPagina(navegaPagina);
    }

    public String getPass() {
        return sessionManagedBean.getPass();
    }

    public void setPass(String pass) {
        this.sessionManagedBean.setPass(pass);
    }

    public Boolean getMuestraMenu() {
        return sessionManagedBean.getMuestraMenu();
    }

    public void setMuestraMenu(Boolean muestraMenu) {
        this.sessionManagedBean.setMuestraMenu(muestraMenu);
    }

    public String getIdSistema() {
        return sessionManagedBean.getIdSistema();
    }

    public void setIdSistema(String idSistema) {
        this.sessionManagedBean.setIdSistema(idSistema);
    }

    public String getNumeroFormularioSolicitudSession() {
        return getSessionManagedBean().getNumeroFormulario();
    }

    public void setNumeroFormularioSolicitudSession(String numeroFormulario) {
        this.getSessionManagedBean().setNumeroFormulario(numeroFormulario);
    }

    public String getTipoTramiteSolicitudSession() {
        return getSessionManagedBean().getTipoTramiteSolicitud();
    }

    public void setTipoTramiteSolicitudSession(String tipoTramiteSolicitud) {
        this.getSessionManagedBean().setTipoTramiteSolicitud(tipoTramiteSolicitud);
    }

    public Long getIdUsuarioSession() {
        return getSessionManagedBean().getIdUsuario();
    }

    public void setIdUsuarioSession(Long idUsuario) {
        this.getSessionManagedBean().setIdUsuario(idUsuario);
    }

    public Long getIdmodificacion() {
        return sessionManagedBean.getIdmodificacion();
    }

    public void setIdmodificacion(Long idmodificacion) {
        this.sessionManagedBean.setIdmodificacion(idmodificacion);
    }

    public Long getidRenSolicitudRenovacionSession() {
        return sessionManagedBean.getIdSolicitudRenovacion();
    }

    public void setidRenSolicitudRenovacionSession(Long idRenSolicitudRenovacion) {
        this.sessionManagedBean.setIdSolicitudRenovacion(idRenSolicitudRenovacion);
    }

    public Long getIdSignoSession() {
        return getSessionManagedBean().getIdSigno();
    }

    public void setIdSignoSession(Long idSigno) {
        this.getSessionManagedBean().setIdSigno(idSigno);
    }
    
    //variable session para la fecha Ingreso session
    public Date getFechaIngresoSolicitudSession(){
        return getSessionManagedBean().getFechaIngresoSolicitud();
    }
    
    public void setFechaIngresoSolicitudSession(Date fechaIngresoSolicitud){
        this.getSessionManagedBean().setFechaIngresoSolicitud(fechaIngresoSolicitud);
    }
}
