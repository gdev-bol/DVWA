/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.entidades.bean.login;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import snp.gob.bo.entidades.bean.common.AbstractManagedBean;
import snp.gob.bo.gimodel.servicio.ListaUsuarioRolService;
import snp.gob.bo.gimodel.servicio.UsuarioService;

/**
 *
 * @author levi
 */
@ManagedBean
@ViewScoped
public class recuperaContraseniaBean  extends AbstractManagedBean implements Serializable{
     @ManagedProperty(value = "#{listaUsuarioRolService}")
    private ListaUsuarioRolService listaUsuarioRolService;
     @ManagedProperty(value = "#{usuarioService}")
    private UsuarioService usuarioService; 
    
      private String usuario;
     private String passAct;
     private String passNue;
     private String passRep;

     
     public String cambiaContraseña() throws Exception{
         System.out.println("usuario::"+usuario);
         System.out.println("passAct::"+passAct);
         String contrasenia=listaUsuarioRolService.encriptarContrasena(usuario, passAct);
         System.out.println("listaUsuarioRolService.existeUsuarioRol(usuario, passAct):"+listaUsuarioRolService.existeUsuarioRol(usuario, contrasenia));  
     if (listaUsuarioRolService.existeUsuarioRol(usuario, contrasenia)) {
        if(!passNue.equals("") && passNue != null && !passRep.equals("") && passRep != null){
           if(passNue.equals(passRep)){
               
               
               String contraseniaNue=listaUsuarioRolService.encriptarContrasena(usuario, passRep);      
               Long idusuario=listaUsuarioRolService.obtieneIdUsuario(usuario,contrasenia);
               usuarioService.cambiaContrasenia(contraseniaNue,idusuario);
               
               
               
            FacesContext context = FacesContext.getCurrentInstance();

            context.addMessage(null, new FacesMessage("PASSWORD CAMBIADO", null));
                return "login";
               
           }
           else {
         
              FacesContext context = FacesContext.getCurrentInstance();

               context.addMessage(null, new FacesMessage("LA NUEVA CONTRASEÑA NO COINCIDE EN LA REPETICION ", null));
              return "";
            }
        }
        else {
         
            FacesContext context = FacesContext.getCurrentInstance();

            context.addMessage(null, new FacesMessage("INSERTE EL NUEVO PASSWORD Y REPITA EL MISMO", null));
            return "";
        }
     
     }
     else {
         
            FacesContext context = FacesContext.getCurrentInstance();

            context.addMessage(null, new FacesMessage("PASSWORD ACTUAL INCORRECTO", null));
           return "";
//            System.out.println("NO EXISTE EL USUARIO EN BD");
            //return "";
        }
       
     }

    public ListaUsuarioRolService getListaUsuarioRolService() {
        return listaUsuarioRolService;
    }

    public void setListaUsuarioRolService(ListaUsuarioRolService listaUsuarioRolService) {
        this.listaUsuarioRolService = listaUsuarioRolService;
    }
     
     
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassAct() {
        return passAct;
    }

    public void setPassAct(String passAct) {
        this.passAct = passAct;
    }

    public String getPassNue() {
        return passNue;
    }

    public void setPassNue(String passNue) {
        this.passNue = passNue;
    }

    public String getPassRep() {
        return passRep;
    }

    public void setPassRep(String passRep) {
        this.passRep = passRep;
    }

    public UsuarioService getUsuarioService() {
        return usuarioService;
    }

    public void setUsuarioService(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

  
     
     
}
