/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.session;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.List;
import snp.gob.bo.gimodel.entidad.listaMenu;
/**
 *
 * @author levi
 */
@ManagedBean(name="sessionManaged")
@SessionScoped
public class SessionManaged implements Serializable{
     private List<listaMenu> listaUsuarioRolSesion;
    private Boolean apareceMenu = false;
    public List<listaMenu> getListaUsuarioRolSesion() {
        return listaUsuarioRolSesion;
    }

    public void setListaUsuarioRolSesion(List<listaMenu> listaUsuarioRolSesion) {
        this.listaUsuarioRolSesion = listaUsuarioRolSesion;
    }
    
    private String login;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Boolean getApareceMenu() {
        return apareceMenu;
    }

    public void setApareceMenu(Boolean apareceMenu) {
        this.apareceMenu = apareceMenu;
    }

  
    
    
}
