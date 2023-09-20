/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
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
import snp.gob.bo.entidades.bean.common.SessionStateManagedBean;
import snp.gob.bo.gimodel.entidad.listaMenu;
import snp.gob.bo.gimodel.servicio.ListaUsuarioRolService;
import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;
/**
 *
 * @author victor
 */
@ManagedBean
@ViewScoped
public class ModuloBean extends AbstractManagedBean implements Serializable{
   
     @ManagedProperty(value = "#{listaUsuarioRolService}")
    private ListaUsuarioRolService listaUsuarioRolService;
   
  
    
    private List<listaMenu> listaUsuarioRol = new ArrayList<listaMenu>();
    boolean btnSigno=true;
    
  @PostConstruct
    public void init() {
        System.out.println("Entra a ModuloBean con login session:"+super.getLoginSession());
     
    
    }
public void saveMessage() {
        FacesContext context = FacesContext.getCurrentInstance();
         
        context.addMessage(null, new FacesMessage("Successful",  "Your message: ") );
        context.addMessage(null, new FacesMessage("Second Message", "Additional Message Detail"));
    }
    public String seteaSesionMenu(String idSistema) throws Exception{
       listaUsuarioRol.clear();
       super.setIdSistema(idSistema);
        System.out.println("ID sistema aqui::"+super.getIdSistema());
        //En l linea de abajo se saca el query que hace que pinte todo
        listaUsuarioRol=listaUsuarioRolService.getUsuarioRol(super.getLoginSession(),super.getPass(),super.getIdSistema());
        
        if(listaUsuarioRol.size()!=0)
        {
           if(idSistema.equals("2")){
                    //setera la variable de sesion para que la pagina se renderice por el tipo de modulo
                    setCodigoPaginaSession("RECA");
           }else if(idSistema.equals("1")){
                    setCodigoPaginaSession("REGS");
           }else if(idSistema.equals("3")){
                    setCodigoPaginaSession("NOTI");
           }else if(idSistema.equals("4")){
                    setCodigoPaginaSession("DIGI");
           }else if(idSistema.equals("5")){
                    setCodigoPaginaSession("ADM");
           }
           
               
           super.setMuestraMenu(true);
           return "/login/signosPrincipal.xhtml";
        }    
        else{
            
            FacesContext context = FacesContext.getCurrentInstance();

            context.addMessage(null, new FacesMessage("No Tiene Permisos", "Comuniquese con el administrador de sistemas"));

            return "";
        }
        
        
    }
   

    public ListaUsuarioRolService getListaUsuarioRolService() {
        return listaUsuarioRolService;
    }

    public void setListaUsuarioRolService(ListaUsuarioRolService listaUsuarioRolService) {
        this.listaUsuarioRolService = listaUsuarioRolService;
    }

    public List<listaMenu> getListaUsuarioRol() {
        return listaUsuarioRol;
    }

    public void setListaUsuarioRol(List<listaMenu> listaUsuarioRol) {
        this.listaUsuarioRol = listaUsuarioRol;
    }

   
    
    public void cambiarEstadoSignoFalse()
    {
        this.btnSigno=false;
        System.out.println("cambiado b");
    }
    
     public void cambiarEstadoSignoTrue()
    {
        this.btnSigno=true;
        System.out.println("cambiado a");
    }


    public boolean isBtnSigno() {
        return btnSigno;
    }

    public void setBtnSigno(boolean btnSigno) {
        this.btnSigno = btnSigno;
    }

 
    
    
}
