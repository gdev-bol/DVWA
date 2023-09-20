/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.entidades.bean.login;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import snp.gob.bo.entidades.bean.common.AbstractManagedBean;
import snp.gob.bo.gimodel.entidad.listaMenu;
import snp.gob.bo.gimodel.servicio.ListaUsuarioRolService;
import snp.gob.bo.session.SessionManaged;


@ManagedBean(name = "loginUsuarioBean")
@ViewScoped
public class LoginUsuarioBean extends AbstractManagedBean implements Serializable {

    
   public String login;
   public String password;
   
    @ManagedProperty("#{sessionManaged}")
    private SessionManaged sessionBean;

    @ManagedProperty(value = "#{listaUsuarioRolService}")
    private ListaUsuarioRolService listaUsuarioRolService;

    //List<HashMap> listado;
    private List<String> listaMenuTitulo = new ArrayList<>();
    private String pageToDisplay = "index.html";
    private List<listaMenu> listaUsuarioRol = new ArrayList<>();
    public LoginUsuarioBean() {
    }

    @PostConstruct
    public void initLoginUsuarioBean() {
//        System.out.println(" el tam en sesion lista en maincotroller::" + sessionBean.getListaUsuarioRolSesion());
//        String borrador = "";
//        listaUsuarioRol = sessionBean.getListaUsuarioRolSesion();
//        for (int i = 0; i <= listaUsuarioRol.size() - 1; i++) {
//            if (!listaUsuarioRol.get(i).getDescripcion().equals(borrador)) {
//                listaMenuTitulo.add(listaUsuarioRol.get(i).getDescripcion());
//                borrador = listaUsuarioRol.get(i).getDescripcion();
//            }
//        }
    
    }

    public void irCambiaContrasenia(){
    
    }
    
    public void cerrarSesion(){
    
    }
    
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    
    
    


}
