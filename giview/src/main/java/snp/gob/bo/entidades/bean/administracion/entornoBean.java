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
import snp.gob.bo.entidades.notificacion.NotiPeticionListPojo;
import snp.gob.bo.gimodel.entidad.Menu;
import snp.gob.bo.gimodel.entidad.MenuPagina;
import snp.gob.bo.gimodel.entidad.Pagina;
import snp.gob.bo.gimodel.entidad.Sistema;
import snp.gob.bo.gimodel.servicio.LogTransService;
import snp.gob.bo.gimodel.servicio.MenuPaginaService;
import snp.gob.bo.gimodel.servicio.MenuService;
import snp.gob.bo.gimodel.servicio.PaginaService;
import snp.gob.bo.gimodel.servicio.SistemaService;
import snp.gob.bo.gimodel.entidad.LogTrans;
import snp.gob.bo.gimodel.servicio.ComunService;

/**
 *
 * @author levi
 */
@ManagedBean
@ViewScoped
public class entornoBean extends AbstractManagedBean implements Serializable{
    
    private  String nuevoSistema;
    private  String nuevoModulo;
    private  String nuevaPagina;
    private  String nuevoURL;
    private  Long idSistemaXModulo;
    private  Long idSistemaXPagina;
    private  Long idModuloXPagina;
    private  Long logtrans;
    private List<Sistema> listSistemaTodo = new ArrayList<Sistema>();
    private List<Menu> listMenuTodo = new ArrayList<Menu>();
    private List<Menu> listMenuTodoParaPaginas = new ArrayList<Menu>();
    private List<Pagina> listPaginaTodo = new ArrayList<Pagina>();
     
    
    @ManagedProperty(value = "#{sistemaService}")
    private SistemaService sistemaService;
    @ManagedProperty(value = "#{menuService}")
    private MenuService menuService;
    @ManagedProperty(value = "#{paginaService}")
    private PaginaService paginaService;
    @ManagedProperty(value = "#{menuPaginaService}")
    private MenuPaginaService menuPaginaService;
    @ManagedProperty(value = "#{logTransService}")
    private LogTransService logTransService;
    @ManagedProperty(value = "#{comunService}")
    private ComunService comunService;
    
    
    @PostConstruct
    public void init() {
        Sistema sistema = new Sistema();
         

        try {
               
           LogTrans logTransGuardado = logTransService.crudLogTrans(new LogTrans(super.getIdUsuarioSession(), comunService.obtenerFechaHoraServidor(1L)), 1);
           logtrans = logTransGuardado.getIdLogTrans();
            
            
            listSistemaTodo=sistemaService.listaSistemas();
           listMenuTodo=menuService.listaMenuXIdSistema(listSistemaTodo.get(0).getIdsistema());
           listMenuTodoParaPaginas=menuService.listaMenuXIdSistema(listSistemaTodo.get(0).getIdsistema());
            listPaginaTodo=paginaService.listaMenuXIdSistema(listMenuTodoParaPaginas.get(0).getIdmenu());
             } catch (Exception ex) {
                Logger.getLogger(NotiPeticionDialogController.class.getName()).log(Level.SEVERE, null, ex);
            }

    }
    
    public void guardaSistema() throws Exception{
       if(this.nuevoSistema!=null && !this.nuevoSistema.equals(""))
       {    
         Sistema sistema= new Sistema();
         sistema.setDescripcion(this.nuevoSistema);
         sistema.setEstado("AC");
         sistema.setIdlogtrans(logtrans);
         //Realizo validaciones
         int sw=0;
         for(int i=0;i<=listSistemaTodo.size()-1;i++)
         {
           if(nuevoSistema.equals(listSistemaTodo.get(i).getDescripcion()))
           {
              FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Nombre del sistema ya existe. No puede añadirse", "");
              FacesContext.getCurrentInstance().addMessage(null, message);
              sw=1;
          
            }
         }
         
         
         
         
         if(sw==0)
         { sistemaService.guardar_modificar_listar_Sistema(sistema, 1);
            //Lo vuelvo a generar pa que actualise todos l os sistemas ya que se adiciono uno
           listSistemaTodo=sistemaService.listaSistemas();
            nuevoSistema="";
         }
       }
       else{
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Inserte Nombre del Nuevo sistema", "");
            FacesContext.getCurrentInstance().addMessage(null, message);
       
       }
    }
     public void guardaMenu() throws Exception{
       if(this.nuevoModulo!=null && !this.nuevoModulo.equals(""))
       {   
           Menu menu = new Menu();
           menu.setIdsistema(idSistemaXModulo);
           menu.setDescripcion(this.nuevoModulo);
           
           menu.setOrden(menuService.cuentaMasUnoMenuSistema(idSistemaXModulo));
           menu.setEstado("AC");
           menu.setIdlogtrans(logtrans);
           int sw = 0;
           for (int i = 0; i <= listMenuTodo.size() - 1; i++) {
               if (nuevoModulo.equals(listMenuTodo.get(i).getDescripcion())) {
                   FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Nombre de el módulo ya existe. No puede añadirse", "");
                   FacesContext.getCurrentInstance().addMessage(null, message);
                   sw = 1;

               }
           }

           if (sw == 0)
           {
           
             menuService.guardar_modificar_listar_Menu(menu, 1);
                   
             this.actualizaTablaModulos();
              //Actualizo el memnu en administracion de 
             listMenuTodoParaPaginas=menuService.listaMenuXIdSistema(idSistemaXPagina);
             nuevoModulo="";
           }
       }
       else{
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Inserte Nombre del Nuevo Menu","");
            FacesContext.getCurrentInstance().addMessage(null, message);
       
       }
     }
     public void guardaPagina() throws Exception{
         
       if(this.nuevaPagina!=null && !this.nuevaPagina.equals(""))
       { 
           Pagina pagina = new Pagina();
           pagina.setDescripcion(nuevaPagina);
           pagina.setUrl(nuevoURL);
           pagina.setEstado("AC");
           pagina.setIdlogtrans(logtrans);
           //valido
           int sw = 0;
           for (int i = 0; i <= listPaginaTodo.size() - 1; i++) {
               if (nuevaPagina.equals(listPaginaTodo.get(i).getDescripcion())) {
                   FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Nombre de la página ya existe. No puede añadirse", "");
                   FacesContext.getCurrentInstance().addMessage(null, message);
                   sw = 1;

               }
           }
           
           
           if(sw==0)
           {   Pagina paginaIsert=paginaService.guardar_modificar_listar_Pagina(pagina, 1);
            
               if(paginaIsert != null)
              {  MenuPagina mp = new MenuPagina();
                // System.out.println("idModuloXPagina"+idModuloXPagina);
                 mp.setIdmenu(idModuloXPagina);
                 mp.setIdpagina(paginaIsert.getIdpagina());
                 int num=menuPaginaService.cuentaMasUnoPagMenu(idModuloXPagina);
                 mp.setOrden(num);
                 mp.setEstado("AC");
                 menuPaginaService.guardar_modificar_listar_Pagina(mp, 1);
              
               }
                listPaginaTodo=paginaService.listaMenuXIdSistema(idModuloXPagina);
                nuevoURL="";
               nuevaPagina="";
           }  
            
            
            
            
            
       }
         else{
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Inserte Nombre de la Nueva Página","");
            FacesContext.getCurrentInstance().addMessage(null, message);
       
       }
         
         
         
         
     }
    
    
    public void modificaSistema(Sistema sistema) throws Exception{
//        System.out.println("ENtra a modifiicar");
//        System.out.println("sistema:::"+sistema.getDescripcion());
//        System.out.println("estado:::"+sistema.getEstado());
//        System.out.println("id:::"+sistema.getIdsistema());
//        System.out.println("idlog:::"+sistema.getIdlogtrans());
        sistemaService.guardar_modificar_listar_Sistema(sistema, 2);
        listSistemaTodo=sistemaService.listaSistemas();
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "El Registro fue Modificado", "");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    public void modificaMenu(Menu menu) throws Exception{
        
        menuService.guardar_modificar_listar_Menu(menu, 2);
        actualizaTablaModulos();
        //Actualizo el memnu en administracion de 
        listMenuTodoParaPaginas=menuService.listaMenuXIdSistema(idSistemaXPagina);
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "El Registro fue Modificado", "");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    public void modificaPagina(Pagina pagina) throws Exception{
        
        paginaService.guardar_modificar_listar_Pagina(pagina, 2);
        actualizaTablaPaginas();
        
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "El Registro fue Modificado", "");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    
    
    public void eliminaSistema(Sistema sistema) throws Exception{
        
       sistema.setEstado("NA");
       sistemaService.guardar_modificar_listar_Sistema(sistema, 2);
       //ESta funcion pone en NA los menus y paginas asociados a el idsistema
        sistemaService.eliminaMenusPaginas(sistema.getIdsistema());

        listSistemaTodo=sistemaService.listaSistemas();
        //Actualizo la tabla de Menus y tabla de paginas con las dos lineas de abajo
        //listMenuTodo=menuService.listaMenuXIdSistema(listSistemaTodo.get(0).getIdsistema());
       // System.out.println("idSistemaXModulo::"+idSistemaXModulo);
        listMenuTodo=menuService.listaMenuXIdSistema(idSistemaXModulo);
        
        
         listPaginaTodo=paginaService.listaMenuXIdSistema(listMenuTodoParaPaginas.get(0).getIdmenu());
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "El Registro fue Eliminado", "");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    public void eliminaMenu(Menu menu) throws Exception{
        menu.setEstado("NA");
        menuService.guardar_modificar_listar_Menu(menu, 2);
        //ESta funcion pone en NA los menus y paginas asociados a el idsistema
        menuService.eliminaPaginas(menu.getIdmenu());
        actualizaTablaModulos();
        //Actualizo el memnu en administracion de 
        listMenuTodoParaPaginas=menuService.listaMenuXIdSistema(idSistemaXPagina);
//        System.out.println("listMenuTodoParaPaginas.get(0).getIdmenu()::"+listMenuTodoParaPaginas.get(0).getIdmenu());
        
        
        if(!listMenuTodoParaPaginas.isEmpty())
        { listPaginaTodo=paginaService.listaMenuXIdSistema(listMenuTodoParaPaginas.get(0).getIdmenu());
        
        }
        else{
           listPaginaTodo.clear();
        
        }
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "El Registro fue Eliminado", "");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    public void eliminaPagina(Pagina pagina) throws Exception{
        pagina.setEstado("NA");
        paginaService.guardar_modificar_listar_Pagina(pagina, 2);
        //ESta funcion pone en NA los menus y paginas asociados a el idsistema
        //menuService.eliminaPaginas(menu.getIdmenu());
        actualizaTablaPaginas();
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "El Registro fue Eliminado", "");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    //Hace que atualize la tabla del medio de menus
    public void actualizaTablaModulos() throws Exception{
    //    System.out.println("idSistemaXModulo"+idSistemaXModulo);
     listMenuTodo=menuService.listaMenuXIdSistema(idSistemaXModulo);
    
    }
    
    public void actualizaComboMenuDePagina() throws Exception{
    
        listMenuTodoParaPaginas=menuService.listaMenuXIdSistema(idSistemaXPagina);
        if(!listMenuTodoParaPaginas.isEmpty())
        { 
            
            listPaginaTodo=paginaService.listaMenuXIdSistema(listMenuTodoParaPaginas.get(0).getIdmenu());
           
        }
        else{
         //Lo cereo el combo  de Menu de Administracion de Paginas
        listMenuTodoParaPaginas.clear();
        }
    }
    
    public void actualizaTablaPaginas() throws Exception{
        
        System.out.println(" entra");
        System.out.println("idModuloXPagina::"+idModuloXPagina);
        listPaginaTodo=paginaService.listaMenuXIdSistema(idModuloXPagina);
    
    }

    public List<Sistema> getListSistemaTodo() {
        return listSistemaTodo;
    }

    public void setListSistemaTodo(List<Sistema> listSistemaTodo) {
        this.listSistemaTodo = listSistemaTodo;
    }

    public SistemaService getSistemaService() {
        return sistemaService;
    }

    public void setSistemaService(SistemaService sistemaService) {
        this.sistemaService = sistemaService;
    }

    public String getNuevoSistema() {
        return nuevoSistema;
    }

    public void setNuevoSistema(String nuevoSistema) {
        this.nuevoSistema = nuevoSistema;
    }

    public Long getIdSistemaXModulo() {
        return idSistemaXModulo;
    }

    public void setIdSistemaXModulo(Long idSistemaXModulo) {
        this.idSistemaXModulo = idSistemaXModulo;
    }

    public List<Menu> getListMenuTodo() {
        return listMenuTodo;
    }

    public void setListMenuTodo(List<Menu> listMenuTodo) {
        this.listMenuTodo = listMenuTodo;
    }

    public MenuService getMenuService() {
        return menuService;
    }

    public void setMenuService(MenuService menuService) {
        this.menuService = menuService;
    }

    public String getNuevoModulo() {
        return nuevoModulo;
    }

    public void setNuevoModulo(String nuevoModulo) {
        this.nuevoModulo = nuevoModulo;
    }

    public Long getIdSistemaXPagina() {
        return idSistemaXPagina;
    }

    public void setIdSistemaXPagina(Long idSistemaXPagina) {
        this.idSistemaXPagina = idSistemaXPagina;
    }

    public List<Menu> getListMenuTodoParaPaginas() {
        return listMenuTodoParaPaginas;
    }

    public void setListMenuTodoParaPaginas(List<Menu> listMenuTodoParaPaginas) {
        this.listMenuTodoParaPaginas = listMenuTodoParaPaginas;
    }

    public Long getIdModuloXPagina() {
        return idModuloXPagina;
    }

    public void setIdModuloXPagina(Long idModuloXPagina) {
        this.idModuloXPagina = idModuloXPagina;
    }

    public List<Pagina> getListPaginaTodo() {
        return listPaginaTodo;
    }

    public void setListPaginaTodo(List<Pagina> listPaginaTodo) {
        this.listPaginaTodo = listPaginaTodo;
    }

    public PaginaService getPaginaService() {
        return paginaService;
    }

    public void setPaginaService(PaginaService paginaService) {
        this.paginaService = paginaService;
    }

    public String getNuevaPagina() {
        return nuevaPagina;
    }

    public void setNuevaPagina(String nuevaPagina) {
        this.nuevaPagina = nuevaPagina;
    }

    public String getNuevoURL() {
        return nuevoURL;
    }

    public void setNuevoURL(String nuevoURL) {
        this.nuevoURL = nuevoURL;
    }

    public MenuPaginaService getMenuPaginaService() {
        return menuPaginaService;
    }

    public void setMenuPaginaService(MenuPaginaService menuPaginaService) {
        this.menuPaginaService = menuPaginaService;
    }

    public Long getLogtrans() {
        return logtrans;
    }

    public void setLogtrans(Long logtrans) {
        this.logtrans = logtrans;
    }

    public LogTransService getLogTransService() {
        return logTransService;
    }

    public void setLogTransService(LogTransService logTransService) {
        this.logTransService = logTransService;
    }

    public ComunService getComunService() {
        return comunService;
    }

    public void setComunService(ComunService comunService) {
        this.comunService = comunService;
    }
      
    
    
    
    
}

