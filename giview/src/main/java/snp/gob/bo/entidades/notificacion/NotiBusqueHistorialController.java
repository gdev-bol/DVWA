/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.entidades.notificacion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import snp.gob.bo.entidades.bean.common.AbstractManagedBean;
import snp.gob.bo.gimodel.entidad.Dominio;
import snp.gob.bo.gimodel.entidad.Notificacion;
import snp.gob.bo.gimodel.entidad.NotificacionHistorial;
import snp.gob.bo.gimodel.servicio.DominioService;
import snp.gob.bo.gimodel.servicio.NotificacionHistorialService;
import snp.gob.bo.gimodel.servicio.NotificacionService;

/**
 *
 * @author levi
 */
@ManagedBean(name = "notiBusqueHistorialController")
@ViewScoped
public class NotiBusqueHistorialController extends AbstractManagedBean implements Serializable{
    private  String comboExpe;
    private String numeroBusca;
    private Integer gestionBusca;
    
    
    
    
    private List<Dominio> listTipoNotifi = new ArrayList<Dominio>();
    
    
    List<Notificacion> listaNotificaciones= new ArrayList<Notificacion>();
    List<NotificacionHistorial> listaNotiHisto= new ArrayList<NotificacionHistorial>();
    
     @ManagedProperty(value = "#{dominioService}")
    private DominioService dominioService;
     @ManagedProperty(value = "#{notificacionService}")
    private NotificacionService notificacionService; 
      @ManagedProperty(value = "#{notificacionHistorialService}")
    private NotificacionHistorialService notificacionHistorialService; 
     
    @PostConstruct
    public void init() {
        
        try {
            listTipoNotifi=dominioService.obtenerListadoDominio("tipo_tramite_notificacion");
            
        } catch (Exception ex) {
            Logger.getLogger(NotiBusqueHistorialController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    public void buscar() throws Exception{
       
        listaNotiHisto =notificacionHistorialService.NotificacionXTipoExpeGest(comboExpe, numeroBusca, gestionBusca);
       
       
    
    }
    public void limpiar(){
       listaNotiHisto.clear();
       numeroBusca= "";
       gestionBusca= null;        
        
    }
            

    public String getComboExpe() {
        return comboExpe;
    }

    public void setComboExpe(String comboExpe) {
        this.comboExpe = comboExpe;
    }

    public String getNumeroBusca() {
        return numeroBusca;
    }

    public void setNumeroBusca(String numeroBusca) {
        this.numeroBusca = numeroBusca;
    }

    public Integer getGestionBusca() {
        return gestionBusca;
    }

    public void setGestionBusca(Integer gestionBusca) {
        this.gestionBusca = gestionBusca;
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

    public List<Notificacion> getListaNotificaciones() {
        return listaNotificaciones;
    }

    public void setListaNotificaciones(List<Notificacion> listaNotificaciones) {
        this.listaNotificaciones = listaNotificaciones;
    }

    public NotificacionService getNotificacionService() {
        return notificacionService;
    }

    public void setNotificacionService(NotificacionService notificacionService) {
        this.notificacionService = notificacionService;
    }

    public NotificacionHistorialService getNotificacionHistorialService() {
        return notificacionHistorialService;
    }

    public void setNotificacionHistorialService(NotificacionHistorialService notificacionHistorialService) {
        this.notificacionHistorialService = notificacionHistorialService;
    }

    public List<NotificacionHistorial> getListaNotiHisto() {
        return listaNotiHisto;
    }

    public void setListaNotiHisto(List<NotificacionHistorial> listaNotiHisto) {
        this.listaNotiHisto = listaNotiHisto;
    }


            
}
