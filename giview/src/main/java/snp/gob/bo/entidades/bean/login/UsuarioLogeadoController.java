/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.entidades.bean.login;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.bean.ViewScoped;
import javax.servlet.http.HttpServlet;
import snp.gob.bo.entidades.bean.common.AbstractManagedBean;
import snp.gob.bo.gimodel.entidad.Etapa;
//import snp.gob.bo.entidades.bean.common.SessionStateManagedBean;
import snp.gob.bo.gimodel.entidad.listaMenu;
import snp.gob.bo.gimodel.servicio.EtapaService;
import snp.gob.bo.gimodel.servicio.ListaUsuarioRolService;
import snp.gob.bo.security.VerificaSesion;
import snp.gob.bo.session.SessionManaged;


/**
 *
 * @author levi
 */
@ManagedBean(name = "usuarioLogeadoController")
@ViewScoped
public class UsuarioLogeadoController  extends AbstractManagedBean implements Serializable{
    /*@ManagedProperty("#{sessionManaged}")
    private SessionManaged sessionBean;
    @ManagedProperty("#{sessionState}")
    private SessionStateManagedBean sessionState;
   */
    @ManagedProperty(value = "#{listaUsuarioRolService}")
    private ListaUsuarioRolService listaUsuarioRolService;
    
    @ManagedProperty(value = "#{etapaService}")
    private EtapaService etapaService;
    
    @ManagedProperty(value = "#{verificaSesion}")
    private VerificaSesion verificaSesion;
    
    private List<listaMenu> listaUsuarioRol = new ArrayList<>();
    private listaMenu usuarioRol;
    
    private String login;
    private String pass;
    //Para pintar el combo de roles
    private boolean pintaCombo;
    private Long idEtapa;
    List<Etapa> lisEtapas;
    @PostConstruct
    public void init() {
     
       //se setea null par qe no pinte el menu de arriba , en la pantalla de login
        super.setLoginSession(null);
       // System.out.println("super.getLoginSession() initttttttttt::"+super.getLoginSession());
        if(super.getLoginSession()==null){
                  
        }
        
        pintaCombo=false;
        super.setMuestraMenu(Boolean.FALSE);
       // sessionState.setMuestraMenu(false);
    }
   /* public String ingresa() throws IOException {
    String cadenaValidadora="";    
    FacesContext context = FacesContext.getCurrentInstance();
    ExternalContext extContext = context.getExternalContext();
        System.out.println("El login y pass: "+login +" .... "+ pass);
        
        
     if(listaUsuarioRolService.existeUsuarioRol(login, pass))
     {  
         System.out.println("EXISTE EL USUARIO EN BD");
         listaUsuarioRol=listaUsuarioRolService.getUsuarioRol(login,pass);
         sessionBean.setListaUsuarioRolSesion(listaUsuarioRol);
         sessionBean.setLogin(login);
         
         extContext.getSessionMap().put("listaUsuarioRol", listaUsuarioRol);
          //La linea de abajo si os i debe estar para que pueda enviar a esa pagina la session
         
         
         ////descomentar para ver la pagina original
         //extContext.redirect("modulo.xhtml");
         return "/login/modulo.xhtml";
      //   extContext.redirect("main.xhtml");
     //    return "main.xhtml";
     }    
     else{
         System.out.println("NO EXISTE EL USUARIO EN BD");
         return "index1.xhtml";
     }
    
   
      
    }*/
    public String ingresa() throws IOException, Exception {
           String contrasenia=listaUsuarioRolService.encriptarContrasena(login, pass);
        if (listaUsuarioRolService.existeUsuarioRol(login, contrasenia)) {
              
          //  System.out.println("listaUsuarioRolService.obtieneIdUsuario(login,contrasenia)::"+listaUsuarioRolService.obtieneIdUsuario(login,contrasenia));
             lisEtapas=etapaService.listaEtapaXIdUsuario(listaUsuarioRolService.obtieneIdUsuario(login,contrasenia));
          //En el if de abajo pregunta si tiene mas de una etapa  y si es el caso pinta el combo para que escoja quer rol tener
             if(lisEtapas.size()>1 && super.getLoginSession() == null)
             { //System.out.println("Entra aqui"); 
               pintaCombo=true;
               super.setLoginSession(login);
               
               //Se retorna nulll para que se actulaize la misma pagina
               return null;
             }
             else{
               super.setLoginSession(login);
               super.setPass(contrasenia);
               super.setIdUsuarioSession(listaUsuarioRolService.obtieneIdUsuario(login,contrasenia));
               if(idEtapa==null)
               {  super.setIdEtapaSession(lisEtapas.get(0).getIdEtapa());
               }
               else{
                 super.setIdEtapaSession(idEtapa);
               }
               
               return "modulo";
             }
            
            
            
        } else {
            FacesContext context = FacesContext.getCurrentInstance();

            context.addMessage(null, new FacesMessage("USUARIO INCORRECTO", null));

//            System.out.println("NO EXISTE EL USUARIO EN BD");
            return "";
        }
    }

 /*   public SessionStateManagedBean getSessionState() {
        return sessionState;
    }

    public void setSessionState(SessionStateManagedBean sessionState) {
        this.sessionState = sessionState;
    }
*/
    public listaMenu getUsuarioRol() {
        return usuarioRol;
    }

    public void setUsuarioRol(listaMenu usuarioRol) {
        this.usuarioRol = usuarioRol;
    }

    public List<listaMenu> getListaUsuarioRol() {
        return listaUsuarioRol;
    }

    public void setListaUsuarioRol(List<listaMenu> listaUsuarioRol) {
        this.listaUsuarioRol = listaUsuarioRol;
    }
   

  /*  public SessionManaged getSessionBean() {
        return sessionBean;
    }

    public void setSessionBean(SessionManaged sessionBean) {
        this.sessionBean = sessionBean;
    }*/

    public ListaUsuarioRolService getListaUsuarioRolService() {
        return listaUsuarioRolService;
    }

    public void setListaUsuarioRolService(ListaUsuarioRolService listaUsuarioRolService) {
        this.listaUsuarioRolService = listaUsuarioRolService;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public boolean isPintaCombo() {
        return pintaCombo;
    }

    public void setPintaCombo(boolean pintaCombo) {
        this.pintaCombo = pintaCombo;
    }

    public EtapaService getEtapaService() {
        return etapaService;
    }

    public void setEtapaService(EtapaService etapaService) {
        this.etapaService = etapaService;
    }

    public Long getIdEtapa() {
        return idEtapa;
    }

    public void setIdEtapa(Long idEtapa) {
        this.idEtapa = idEtapa;
    }



    public List<Etapa> getLisEtapas() {
        return lisEtapas;
    }

    public void setLisEtapas(List<Etapa> lisEtapas) {
        this.lisEtapas = lisEtapas;
    }

    public VerificaSesion getVerificaSesion() {
        return verificaSesion;
    }

    public void setVerificaSesion(VerificaSesion verificaSesion) {
        this.verificaSesion = verificaSesion;
    }




    
}
