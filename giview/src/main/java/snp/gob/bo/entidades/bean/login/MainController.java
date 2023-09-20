/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.entidades.bean.login;

import java.io.Serializable;
import java.util.ArrayList;
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
import org.primefaces.context.RequestContext;
import snp.gob.bo.entidades.bean.common.AbstractManagedBean;
import snp.gob.bo.gimodel.entidad.listaMenu;
import snp.gob.bo.gimodel.servicio.ListaUsuarioRolService;
import snp.gob.bo.gimodel.servicio.UsuarioService;
import snp.gob.bo.session.SessionManaged;

/**
 *
 * @author levi
 */
@ManagedBean
@ViewScoped
public class MainController extends AbstractManagedBean implements Serializable {

    @ManagedProperty("#{sessionManaged}")
    private SessionManaged sessionBean;

//    @ManagedProperty("#{sessionState}")
//    private SessionStateManagedBean sessionState;
    @ManagedProperty(value = "#{listaUsuarioRolService}")
    private ListaUsuarioRolService listaUsuarioRolService;
    @ManagedProperty(value = "#{usuarioService}")
    private UsuarioService usuarioService;

    //List<HashMap> listado;
    private List<String> listaMenuTitulo = new ArrayList<String>();
    private String pageToDisplay = "index.html";

    private List<listaMenu> listaUsuarioRol = new ArrayList<listaMenu>();
    private Boolean muestra = false;
    private Boolean muestraMenu = false;
    private Boolean renderizaSignos = false;
    private Boolean renderizaRecaudaciones = false;
    private String subMenuEscoge;
    private String nombreUsuario = "";

    @PostConstruct
    public void init() {

        subMenuEscoge = super.getIdSistema();
       // System.out.println("subMenuEscoge::"+subMenuEscoge);

        String borrador = "";
        //System.out.println("getLogin 1:" + super.getLoginSession());
        if (super.getLoginSession() != null) {
            try {
                muestraMenu = true;
                listaUsuarioRol = listaUsuarioRolService.getUsuarioRol(super.getLoginSession(), super.getPass(), super.getIdSistema());
                //      System.out.println("Tamaño" + listaUsuarioRol.size());
                for (int i = 0; i <= listaUsuarioRol.size() - 1; i++) {
                    if (!listaUsuarioRol.get(i).getDescripcion().equals(borrador)) {
                        //            System.out.println("ESTADO::" + listaUsuarioRol.get(i).getEstado());
                        listaMenuTitulo.add(listaUsuarioRol.get(i).getDescripcion());
                        borrador = listaUsuarioRol.get(i).getDescripcion();
                    }
                }                               
                nombreUsuario = usuarioService.listarNombreCompletoXIdUsuario(getIdUsuarioSession());
            } catch (Exception ex) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            muestraMenu = false;
        }
        if (getCodigoPaginaSession() != null) {
            recuperaVariable(getCodigoPaginaSession());
        }

    }

    private void recuperaVariable(String codigoPagina) {
        if (codigoPagina.equals("RECA")) {
            renderizaRecaudaciones = true;
            renderizaSignos = false;
        }
        if (codigoPagina.equals("REGS") || codigoPagina.equals("NOTI") || codigoPagina.equals("DIGI")|| codigoPagina.equals("ADM")) {
            renderizaRecaudaciones = false;
            renderizaSignos = true;
        }
    }

    public String urlDirige(String concatena) {
        String url = "";
        String estado = "AC";
        String[] cadena = concatena.split(",");
        url = cadena[0];
        setPaginaAnteriorDigitalizacion(url);
        estado = cadena[1];
        if (estado.equals("AC")) {
            return url;
        } else {
            FacesContext context = FacesContext.getCurrentInstance();

            context.addMessage(null, new FacesMessage("No Tiene Permisos", "Comuniquese con el administrador de sistemas"));
            return "";
        }

    }

    public String abrirConfirmacion() {
        return "login";
    }

    public void chooseCar() {
        Map<String, Object> options = new HashMap<String, Object>();
        options.put("resizable", false);
        options.put("draggable", false);
        options.put("modal", true);
        RequestContext.getCurrentInstance().openDialog("confirmacionModificatoria", options, null);
    }

    public String derivaLogin() {
        muestraMenu = false;
        setLoginSession(null);
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "login";
    }

    public String navegaPagina(String activo) {
        return pageToDisplay;
    }

    public Boolean getMuestra() {
        return muestra;
    }

    public void setMuestra(Boolean muestra) {
        this.muestra = muestra;
    }

    public Boolean getRenderizaSignos() {
        return renderizaSignos;
    }

    public void setRenderizaSignos(Boolean renderizaSignos) {
        this.renderizaSignos = renderizaSignos;
    }

    public Boolean getRenderizaRecaudaciones() {
        return renderizaRecaudaciones;
    }

    public void setRenderizaRecaudaciones(Boolean renderizaRecaudaciones) {
        this.renderizaRecaudaciones = renderizaRecaudaciones;
    }

    @Override
    public Boolean getMuestraMenu() {
        return muestraMenu;
    }

    @Override
    public void setMuestraMenu(Boolean muestraMenu) {
        this.muestraMenu = muestraMenu;
    }

    public SessionManaged getSessionBean() {
        return sessionBean;
    }

    public void setSessionBean(SessionManaged sessionBean) {
        this.sessionBean = sessionBean;
    }

    public ListaUsuarioRolService getListaUsuarioRolService() {
        return listaUsuarioRolService;
    }

    public void setListaUsuarioRolService(ListaUsuarioRolService listaUsuarioRolService) {
        this.listaUsuarioRolService = listaUsuarioRolService;
    }

    public List<String> getListaMenuTitulo() {
        return listaMenuTitulo;
    }

    public void setListaMenuTitulo(List<String> listaMenuTitulo) {
        this.listaMenuTitulo = listaMenuTitulo;
    }

    public String getPageToDisplay() {
        return pageToDisplay;
    }

    public void setPageToDisplay(String pageToDisplay) {
        this.pageToDisplay = pageToDisplay;
    }

    public List<listaMenu> getListaUsuarioRol() {
        return listaUsuarioRol;
    }

    public void setListaUsuarioRol(List<listaMenu> listaUsuarioRol) {
        this.listaUsuarioRol = listaUsuarioRol;
    }
    /*   private void setPaginaSession(String pageToDisplay) {
     throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
     }
     */
//    public SessionStateManagedBean getSessionState() {
//        return sessionState;
//    }
//
//    public void setSessionState(SessionStateManagedBean sessionState) {
//        this.sessionState = sessionState;
//    }

    public String getSubMenuEscoge() {
        return subMenuEscoge;
    }

    public void setSubMenuEscoge(String subMenuEscoge) {
        this.subMenuEscoge = subMenuEscoge;
    }

    public UsuarioService getUsuarioService() {
        return usuarioService;
    }

    public void setUsuarioService(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

}
