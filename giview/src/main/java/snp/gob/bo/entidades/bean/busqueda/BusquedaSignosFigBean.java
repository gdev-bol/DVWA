/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.entidades.bean.busqueda;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import snp.gob.bo.entidades.bean.common.AbstractManagedBean;
import snp.gob.bo.gimodel.entidad.BusquedaMarcaResultado;
import snp.gob.bo.gimodel.entidad.ClaseNiza;
import snp.gob.bo.gimodel.servicio.BusquedaMarcaResultadoService;
import snp.gob.bo.gimodel.servicio.ClaseNizaService;

import snp.gob.bo.gimodel.entidad.Usuario;
import snp.gob.bo.gimodel.servicio.UsuarioService;
import snp.gob.bo.gimodel.servicio.ComunService;

/**
 *
 * @author Eddy Valero
 * @version 1.0, 17/10/2016
 *
 */
@ManagedBean(name = "busquedaSignosFigBean")
@ViewScoped
public class BusquedaSignosFigBean extends AbstractManagedBean implements Serializable {
    
    private String saludo="saludos de la vista";

    //conexion con el modelo
    
    //instruccion que inyecta el servicio de clasenizaservice, jalando el id desde applicationContext
    @ManagedProperty("#{claseNizaService}")
    private ClaseNizaService claseNizaService;
    
    
    @ManagedProperty("#{usuarioService}")
    private UsuarioService usuarioService;    
    
    private ClaseNiza claseNiza;
    private ClaseNiza claseNiza1;

    
    //obtener un conjunto de datos
     private List<ClaseNiza> listaClaseNiza = new ArrayList();

    //obtener un conjunto de datos
     private List<Usuario> listaUsuarios = new ArrayList();
     
     
    @PostConstruct
    public void inicio(){
        System.out.println("saludos desde el bean.....");
        
        try {
            claseNiza = claseNizaService.obtenerRegistroClaseNiza(1L);
            claseNiza1 = claseNizaService.obtenerRegistroClaseNiza(12L);
            
            listaClaseNiza = claseNizaService.obtenerListadoClaseNiza();
            listaUsuarios = usuarioService.listaUsuario();

            
            for (ClaseNiza claseNiza2 : listaClaseNiza) {
                System.out.println("****" + claseNiza2.getIdClaseNiza());
            }
            
            
            System.out.println("proteccion:"+claseNiza.getProteccion());
            System.out.println("proteccion:"+claseNiza1.getProteccion());
        } catch (Exception ex) {
            Logger.getLogger(BusquedaSignosFigBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        saludo="saludos desde el bean";
        
        
        
    }
    
    
    
    /**
     * @return the saludo
     */
    public String getSaludo() {
        return saludo;
    }

    /**
     * @param saludo the saludo to set
     */
    public void setSaludo(String saludo) {
        this.saludo = saludo;
    }
    
    public ClaseNiza getClaseNiza() {
        return claseNiza;
    }
    
    public void setClaseNiza(ClaseNiza claseNiza){
        this.claseNiza = claseNiza;
    }
    
    
    public ClaseNizaService getClaseNizaService() {
        return claseNizaService;
    }

    public void setClaseNizaService(ClaseNizaService claseNizaService) {
        this.claseNizaService = claseNizaService;
    }

   
      public ClaseNiza getClaseNiza1() {
        return claseNiza1;
    }

    public void setClaseNiza1(ClaseNiza claseNiza1) {
        this.claseNiza1 = claseNiza1;
    }

    public List<ClaseNiza> getListaClaseNiza() {
        return listaClaseNiza;
    }

    public void setListaClaseNiza(List<ClaseNiza> listaClaseNiza) {
        this.listaClaseNiza = listaClaseNiza;
    }

    public UsuarioService getUsuarioService() {
        return usuarioService;
    }

    public void setUsuarioService(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }


    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }



}
