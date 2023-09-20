/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.entidades.bean.administracion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import snp.gob.bo.entidades.bean.common.AbstractManagedBean;
import snp.gob.bo.entidades.notificacion.NotiPeticionDialogController;
import snp.gob.bo.gimodel.entidad.Etapa;
import snp.gob.bo.gimodel.servicio.ComunService;
import snp.gob.bo.gimodel.servicio.LogTransService;
import snp.gob.bo.gimodel.entidad.LogTrans;
import snp.gob.bo.gimodel.entidad.Sistema;
import snp.gob.bo.gimodel.servicio.EtapaService;
/**
 *
 * @author levi
 */
@ManagedBean(name = "rolAdmBean")
@ViewScoped
public class rolAdmBean extends AbstractManagedBean implements Serializable{
   private  String nuevoRol;   
   private  String nuevaSigla;  
   private LogTransService logTransService;
    private  Long logtrans;
   
    
    private List<Etapa> listRolTodo = new ArrayList<Etapa>();
   
   
   
    @ManagedProperty(value = "#{comunService}")
    private ComunService comunService;
     @ManagedProperty(value = "#{etapaService}")
    private EtapaService etapaService;
     
     
     
    @PostConstruct
    public void init() {
          try {
               
          //    LogTrans logTransGuardado = logTransService.crudLogTrans(new LogTrans(super.getIdUsuarioSession(), comunService.obtenerFechaHoraServidor(1L)), 1);
          //    logtrans = logTransGuardado.getIdLogTrans();
           System.out.println("entra");
              listRolTodo=etapaService.listaEtapa();
           System.out.println("listRolTodo tam:"+listRolTodo.size());
            
          
             } catch (Exception ex) {
             Logger.getLogger(NotiPeticionDialogController.class.getName()).log(Level.SEVERE, null, ex);
            }
         
    }
    
    public void guardaRol() throws Exception{

      Etapa etapa= new Etapa();
    
     // etapa.setIdLogTrans(1L);
     //etapa.setTipoTramite();
      etapa.setTipoEtapa(nuevaSigla);
      etapa.setDescripcion(nuevoRol);
      etapa.setFechaCreacion(comunService.obtenerFechaHoraServidor(1L));
      etapa.setEstado("AC");
    
      //Puros validaciones
      int sw=0;
      if(nuevaSigla.length()>4)
      {   FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "La sigla no debe contener mas de 4 caracteres", "");
          FacesContext.getCurrentInstance().addMessage(null, message);
         sw=1;
      }
      
      for(int i=0;i<=listRolTodo.size()-1;i++)
      {
         if(nuevoRol.equals(listRolTodo.get(i).getDescripcion()))
         {
             FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Nombre de la descripción ya existe. No puede añadirse", "");
             FacesContext.getCurrentInstance().addMessage(null, message);
          sw=1;
          
         }
         if(nuevaSigla.equals(listRolTodo.get(i).getTipoEtapa()))
         {
             FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Nombre de la sigla ya existe. No puede añadirse", "");
             FacesContext.getCurrentInstance().addMessage(null, message);
          sw=1;
          
         }
         
         
         
      }
      
      if(sw==0)
      {
        etapaService.crudEtapa(etapa, 1);
    
        listRolTodo=etapaService.listaEtapa();
        nuevoRol="";
        nuevaSigla="";
      }
    }




    public void modificaRol(Etapa etapa ) throws Exception{
    
        
        etapaService.crudEtapa(etapa, 2);
        listRolTodo=etapaService.listaEtapa();
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "El Registro fue Modificado", "");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    public void eliminaRol(Etapa etapa) throws Exception{
       etapa.setEstado("NA");
       etapaService.crudEtapa(etapa, 2);
        listRolTodo=etapaService.listaEtapa();
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "El Registro fue Eliminado", "");
        FacesContext.getCurrentInstance().addMessage(null, message);
    
    
    
    }
            
            
            

    public List<Etapa> getListRolTodo() {
        return listRolTodo;
    }

    public void setListRolTodo(List<Etapa> listRolTodo) {
        this.listRolTodo = listRolTodo;
    }

    public EtapaService getEtapaService() {
        return etapaService;
    }

    public void setEtapaService(EtapaService etapaService) {
        this.etapaService = etapaService;
    }
   
   
   

    public String getNuevoRol() {
        return nuevoRol;
    }

    public void setNuevoRol(String nuevoRol) {
        this.nuevoRol = nuevoRol;
    }

    public LogTransService getLogTransService() {
        return logTransService;
    }

    public void setLogTransService(LogTransService logTransService) {
        this.logTransService = logTransService;
    }

    public Long getLogtrans() {
        return logtrans;
    }

    public void setLogtrans(Long logtrans) {
        this.logtrans = logtrans;
    }

    public ComunService getComunService() {
        return comunService;
    }

    public void setComunService(ComunService comunService) {
        this.comunService = comunService;
    }

    public String getNuevaSigla() {
        return nuevaSigla;
    }

    public void setNuevaSigla(String nuevaSigla) {
        this.nuevaSigla = nuevaSigla;
    }
              
              
              
              
}
