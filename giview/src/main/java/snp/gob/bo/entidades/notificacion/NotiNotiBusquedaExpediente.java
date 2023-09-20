/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.entidades.notificacion;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import snp.gob.bo.gimodel.entidad.Dominio;
import snp.gob.bo.gimodel.servicio.DominioService;
import java.util.logging.Logger;
import java.util.logging.Level;
import snp.gob.bo.gimodel.entidad.NotificacionBusqueda;
import snp.gob.bo.gimodel.servicio.NotificacionBusquedaService;
/**
 *
 * @author levi
 */
@ManagedBean
@ViewScoped
public class NotiNotiBusquedaExpediente {
       private boolean pintaOpcion =false;
     private String opcion;
         
     private String comboExpe;
     private String numBusque;
     private Integer gestionBusque;
     private String campoBusque;
     
     
     private List<Dominio> listTipoNotifi = new ArrayList<Dominio>();
     private List<NotificacionBusqueda> listNotifiBus = new ArrayList<NotificacionBusqueda>();
     @ManagedProperty(value = "#{dominioService}")
     private DominioService dominioService;
     @ManagedProperty(value = "#{notificacionBusquedaService}")
     private NotificacionBusquedaService notificacionBusquedaService;
     
     
     
     @PostConstruct
    public void init() {
        
        try {
            listTipoNotifi=dominioService.obtenerListadoDominio("tipo_tramite_notificacion");
            
        } catch (Exception ex) {
            Logger.getLogger(NotiBusqueHistorialController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }   
    public void buscarXTramite() throws Exception{
        listNotifiBus.clear();
       // System.out.println("comboExpe"+comboExpe);
       // System.out.println("numBusque"+numBusque);
       // System.out.println("gestionBusque"+gestionBusque);
        listNotifiBus=notificacionBusquedaService.NotificacionBusquedaXTipoExpeGest(comboExpe, numBusque, gestionBusque);
       // System.out.println("getDemandante_fecha_noti::"+listNotifiBus.get(0).getDemandante_fecha_noti());
    }
    public void buscarXDandedadoOperador() throws Exception{
        listNotifiBus.clear();
        if(opcion.equals("2"))
        {    listNotifiBus=notificacionBusquedaService.NotificacionBusquedaXDemaDado(campoBusque);
        
        }
        if(opcion.equals("3"))
        {
             listNotifiBus=notificacionBusquedaService.NotificacionBusquedaXOpera(campoBusque); 
        }
        
        
    }
    public void actualiza(){
        limpiar();
        System.out.println("opcion elegida:"+opcion);
        if(!opcion.equals("1")){
        
          pintaOpcion=true;
        
        }
        else{
         pintaOpcion=false;
        
        }
       
    }   
    public void limpiar(){
        listNotifiBus.clear();
        this.numBusque="";
        this.gestionBusque=null;
        this.campoBusque="";
    
    }
    public boolean getPintaOpcion() {
        return pintaOpcion;
    }

    public void setPintaOpcion(boolean pintaOpcion) {
        this.pintaOpcion = pintaOpcion;
    }

    public String getOpcion() {
        return opcion;
    }

    public void setOpcion(String opcion) {
        this.opcion = opcion;
    }

    public String getComboExpe() {
        return comboExpe;
    }

    public void setComboExpe(String comboExpe) {
        this.comboExpe = comboExpe;
    }

    public List<Dominio> getListTipoNotifi() {
        return listTipoNotifi;
    }

    public void setListTipoNotifi(List<Dominio> listTipoNotifi) {
        this.listTipoNotifi = listTipoNotifi;
    }

    public DominioService getDominioService() {
        return dominioService;
    }

    public void setDominioService(DominioService dominioService) {
        this.dominioService = dominioService;
    }

    public NotificacionBusquedaService getNotificacionBusquedaService() {
        return notificacionBusquedaService;
    }

    public void setNotificacionBusquedaService(NotificacionBusquedaService notificacionBusquedaService) {
        this.notificacionBusquedaService = notificacionBusquedaService;
    }

    public String getNumBusque() {
        return numBusque;
    }

    public void setNumBusque(String numBusque) {
        this.numBusque = numBusque;
    }

    public Integer getGestionBusque() {
        return gestionBusque;
    }

    public void setGestionBusque(Integer gestionBusque) {
        this.gestionBusque = gestionBusque;
    }

    public List<NotificacionBusqueda> getListNotifiBus() {
        return listNotifiBus;
    }

    public void setListNotifiBus(List<NotificacionBusqueda> listNotifiBus) {
        this.listNotifiBus = listNotifiBus;
    }

    public String getCampoBusque() {
        return campoBusque;
    }

    public void setCampoBusque(String campoBusque) {
        this.campoBusque = campoBusque;
    }
       
       
       
       
       
       
}
