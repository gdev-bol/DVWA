/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.entidades.bean.administracion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import snp.gob.bo.entidades.bean.common.AbstractManagedBean;
import snp.gob.bo.gimodel.entidad.Dominio;
import snp.gob.bo.gimodel.entidad.Etapa;
import snp.gob.bo.gimodel.entidad.LogTrans;
import snp.gob.bo.gimodel.entidad.MenuPaginaUsuario;
import snp.gob.bo.gimodel.entidad.Regional;
import snp.gob.bo.gimodel.entidad.Sistema;
import snp.gob.bo.gimodel.entidad.Usuario;
import snp.gob.bo.gimodel.entidad.UsuarioEtapa;
import snp.gob.bo.gimodel.entidad.UsuarioMenu;
import snp.gob.bo.gimodel.entidad.UsuarioPagina;
import snp.gob.bo.gimodel.servicio.ComunService;
import snp.gob.bo.gimodel.servicio.DominioService;
import snp.gob.bo.gimodel.servicio.EtapaService;
import snp.gob.bo.gimodel.servicio.ListaUsuarioRolService;
import snp.gob.bo.gimodel.servicio.LogTransService;
import snp.gob.bo.gimodel.servicio.MenuPaginaService;
import snp.gob.bo.gimodel.servicio.RegionalService;
import snp.gob.bo.gimodel.servicio.SistemaService;
import snp.gob.bo.gimodel.servicio.UsuarioEtapaService;
import snp.gob.bo.gimodel.servicio.UsuarioMenuService;
import snp.gob.bo.gimodel.servicio.UsuarioPaginaService;
import snp.gob.bo.gimodel.servicio.UsuarioService;

/**
 *
 * @author levi
 */
@ManagedBean
@ViewScoped
public class usuarioAdmBean extends AbstractManagedBean implements Serializable{
   private String template;
   private String slcPrincipales;
   private Long idusuario;
   private Usuario usuarioSelec;
   private Long idetapa;
   
   private String nombre;
   private String apPaterno;
   private String apMaterno;
   private String cargo;
   private Long   idregional;
   private String login;
   private String contrasenia;
   private String numeroDoc;
   private String tipoDoc;
   private String lugarExped;
   private String correoElec;
   
   
   
   private Long   idsistema;
   private boolean desactivaRecibe;
   private boolean desactivaDevuelve;
   
   private boolean desactivaCrea;
   private boolean desactivaModifica;
   private boolean desactivaElimina;
   
   private boolean dibujaUsuario;
       private  Long logtrans;
   private List<Usuario> listUsuario = new ArrayList<Usuario>();
   private List<Regional> listRegionalUsuaro = new ArrayList<Regional>();
   private List<Sistema> listSistemas = new ArrayList<Sistema>();
   private List<Etapa> listEtapaUsuario = new ArrayList<Etapa>();
   private List<Etapa> listEtapaUsBaseParaModi = new ArrayList<Etapa>();
   private List<Etapa> listEtapas = new ArrayList<Etapa>();
   
   
   private List<MenuPaginaUsuario> listMenuPagIz = new ArrayList<MenuPaginaUsuario>();
   private List<MenuPaginaUsuario> listMenuPagIzAgarra = new ArrayList<MenuPaginaUsuario>();
   private List<MenuPaginaUsuario> listMenuPagDer = new ArrayList<MenuPaginaUsuario>();
   private List<MenuPaginaUsuario> listMenuPagDerAgarra = new ArrayList<MenuPaginaUsuario>();
     
   
    private List<MenuPaginaUsuario> listMenuPagDerBaseParaModi = new ArrayList<MenuPaginaUsuario>();
    private List<Dominio> listTipoDocumento = new ArrayList<Dominio>();
    private List<Dominio> listlugarExpe = new ArrayList<Dominio>();
   //private List<Dominio> listRegional = new ArrayList<Domino>();
   
   @ManagedProperty(value = "#{usuarioService}")
    private UsuarioService usuarioService;
   @ManagedProperty(value = "#{dominioService}")
    private DominioService dominioService;
   @ManagedProperty(value = "#{regionalService}")
    private RegionalService regionalService;
   @ManagedProperty(value = "#{listaUsuarioRolService}")
    private ListaUsuarioRolService listaUsuarioRolService;
   @ManagedProperty(value = "#{sistemaService}")
    private SistemaService sistemaService;
    @ManagedProperty(value = "#{menuPaginaService}")
    private MenuPaginaService menuPaginaService;
    @ManagedProperty(value = "#{etapaService}")
    private EtapaService etapaService;
    @ManagedProperty(value = "#{usuarioEtapaService}")
    private UsuarioEtapaService usuarioEtapaService;
   @ManagedProperty(value = "#{usuarioPaginaService}")
    private UsuarioPaginaService usuarioPaginaService;
   @ManagedProperty(value = "#{usuarioMenuService}")
    private UsuarioMenuService usuarioMenuService;
   @ManagedProperty(value = "#{comunService}")
    private ComunService comunService;
   @ManagedProperty(value = "#{logTransService}")
    private LogTransService logTransService;

    @PostConstruct
    public void init() {
            
            
            
        
        template="./../WEB-INF/facelets/templates/Template.xhtml";
        
        
        slcPrincipales="4";
       try {
              LogTrans logTransGuardado = logTransService.crudLogTrans(new LogTrans(super.getIdUsuarioSession(), comunService.obtenerFechaHoraServidor(1L)), 1);
           logtrans = logTransGuardado.getIdLogTrans();
          listUsuario= usuarioService.listaUsuario();
          
          listSistemas=sistemaService.listaSistemas();
          idsistema=listSistemas.get(0).getIdsistema();
          //dominioService.obtenerListadoDominio("lugar_expedicion");
          listRegionalUsuaro=regionalService.listadoRegional();
          listMenuPagIz=menuPaginaService.obtieneMenusPaginas(1L);
          //De principio no se mostrara nada en la tabla derecha por que  de principio no hay un usuario selecionado
          listMenuPagDer=menuPaginaService.listaMenusPaginasUsuario(null,null);
          listEtapas=etapaService.listaEtapa();
          
          
          listTipoDocumento=dominioService.obtenerListadoDominio("tipo_documento");
          listlugarExpe=dominioService.obtenerListadoDominio("lugar_expedicion");
          desactivaRecibe=true;
          desactivaDevuelve=true;
          desactivaCrea=true;
          desactivaModifica=true;
          desactivaElimina=true;
          dibujaUsuario=true;
       } catch (Exception ex) {
           Logger.getLogger(usuarioAdmBean.class.getName()).log(Level.SEVERE, null, ex);
       }
    }

    public void llenaDatosUsuario() throws Exception{
            //System.out.println("usuarioSelec id::"+usuarioSelec.getIdusuario());
        System.out.println("id::"+idusuario);
        
        usuarioSelec= usuarioService.obtenerUsuario(idusuario);
        nombre=usuarioSelec.getNombre();
        apPaterno=usuarioSelec.getPrimer_apellido();
        apMaterno=usuarioSelec.getSegundo_apellido();
        cargo=usuarioSelec.getCargo();
        numeroDoc=usuarioSelec.getNumero_documento();
         tipoDoc=usuarioSelec.getTipo_documento();
         lugarExped=usuarioSelec.getLugar_expedicion();
         correoElec=usuarioSelec.getCorreo_electronico();
        
        
        idregional=usuarioSelec.getIdregional();
        login=usuarioSelec.getLogin();
        
        contrasenia=listaUsuarioRolService.dencriptarContrasena(login, usuarioSelec.getContrasenia());
//        System.out.println("idsistema:"+idsistema);
//        System.out.println("usuarioService.listaUsuarioXidPagina(super.getIdUsuarioSession()).get(0).getLogin():"+usuarioService.listaUsuarioXidPagina(super.getIdUsuarioSession()).get(0).getLogin());
//        System.out.println("usuarioService.listaUsuarioXidPagina(super.getIdUsuarioSession()).get(0).getContrasenia():"+usuarioService.listaUsuarioXidPagina(super.getIdUsuarioSession()).get(0).getContrasenia());

        /*lleno el rol o roles uqe tenga*/
        
        listEtapaUsuario=etapaService.listaEtapaXIdUsuario(idusuario);
        listEtapaUsBaseParaModi=etapaService.listaEtapaXIdUsuario(idusuario);
        
        System.out.println("tam aqui listEtapaUsBaseParaModi:"+listEtapaUsBaseParaModi.size());
        
        
        
        
        /*lleno el menu derecho de la pantalla*/
        listMenuPagDer=menuPaginaService.listaMenusPaginasUsuario(
                                                                 usuarioService.listaUsuarioXidPagina(idusuario).get(0).getLogin(),
                                                                 usuarioService.listaUsuarioXidPagina(idusuario).get(0).getContrasenia());
        listMenuPagDerBaseParaModi=menuPaginaService.listaMenusPaginasUsuario(
                                                                 usuarioService.listaUsuarioXidPagina(idusuario).get(0).getLogin(),
                                                                 usuarioService.listaUsuarioXidPagina(idusuario).get(0).getContrasenia());
        //Lo inicializo porque sino sale error
        
        if(listMenuPagDer.isEmpty())
        {
         listMenuPagDer = new ArrayList<MenuPaginaUsuario>();
        }
        
    
    }
    
    public void actualizaTablaIzqDer() throws Exception{
    
         listMenuPagIz=menuPaginaService.obtieneMenusPaginas(idsistema);
          //listMenuPagDer=menuPaginaService.listaMenusPaginasUsuario(usuarioService.listaUsuarioXidPagina(idusuario).get(0).getLogin(),
//                                                                    usuarioService.listaUsuarioXidPagina(idusuario).get(0).getContrasenia());
    
    
    }
    
    
    
    
    public void cambiaPrincipal() throws Exception{
        System.out.println("slcPrincipales:"+slcPrincipales);
        //Actualizlista de usuarios ya que puede hhaverse creado uno nuevo y devo actualizarlo
         listUsuario= usuarioService.listaUsuario();
       switch (slcPrincipales) {
            case "1":  
                    desactivaRecibe= false;
                    desactivaDevuelve=false;
                    desactivaCrea=false;
                    desactivaModifica=true;
                     desactivaElimina=true;
                     dibujaUsuario=false;
                     /*Limpio campos*/
                     nombre="";
                     apPaterno="";
                     apMaterno="";
                     cargo="";                             
                     idregional=1L;
                     login="";
                     contrasenia="";
                     numeroDoc="";
                     tipoDoc="";
                     lugarExped="";
                     correoElec="";
                     listEtapaUsuario.clear();
                     listMenuPagDer.clear();
                     listMenuPagDer = new ArrayList<MenuPaginaUsuario>();
                     
                     break;
           case "2":  
                    desactivaRecibe= false;
                    desactivaDevuelve=false;
                    desactivaCrea=true;
                    desactivaModifica=false;
                    desactivaElimina=true;
                    dibujaUsuario=true;
                    
                    /*Limpio campos*/
                     nombre="";
                     apPaterno="";
                     apMaterno="";
                     cargo="";                             
                     idregional=1L;
                     login="";
                     contrasenia="";
                     numeroDoc="";
                     tipoDoc="";
                     lugarExped="";
                     correoElec="";
                     listEtapaUsuario.clear();
                     listMenuPagDer.clear();
                     listMenuPagDer = new ArrayList<MenuPaginaUsuario>();
                     idusuario=null;
                     
                     break;
            case "3":  
                    desactivaRecibe= true;
                    desactivaDevuelve=true;
                    desactivaCrea=true;
                    desactivaModifica=true;
                    desactivaElimina=false;
                    dibujaUsuario=true;
                    /*Limpio campos*/
                     nombre="";
                     apPaterno="";
                     apMaterno="";
                     cargo="";                             
                     idregional=1L;
                     login="";
                     contrasenia="";
                     numeroDoc="";
                     tipoDoc="";
                     lugarExped="";
                     correoElec="";
                     listEtapaUsuario.clear();
                     listMenuPagDer.clear();
                     listMenuPagDer = new ArrayList<MenuPaginaUsuario>();
                     idusuario=null;
                     break;
            case "4":  
                    desactivaRecibe= true;
                    desactivaDevuelve=true;
                    desactivaCrea=true;
                    desactivaModifica=true;
                    desactivaElimina=true;
                    dibujaUsuario=true;
                    /*Limpio campos*/
                     nombre="";
                     apPaterno="";
                     apMaterno="";
                     cargo="";                             
                     idregional=1L;
                     login="";
                     contrasenia="";
                     numeroDoc="";
                     tipoDoc="";
                     lugarExped="";
                     correoElec="";
                     listEtapaUsuario.clear();
                     listMenuPagDer.clear();
                     listMenuPagDer = new ArrayList<MenuPaginaUsuario>();
                     idusuario=null;
                     break;
         default: 
                     break;
        }
    
    }
    public void cargaRolUsuario() throws Exception{
     listEtapaUsuario.add(etapaService.etapaXIdEtapa(this.idetapa));
    
    }
    public void quitaRolUsuario(Etapa et) throws Exception{ 
        
        
        listEtapaUsuario.remove(et);
        
    }
    public void agarraValores(){
        
       
       // System.out.println("listMenuPagIzAgarra.size()::"+listMenuPagIzAgarra.size());
    for(int i=0;i<=listMenuPagIzAgarra.size()-1;i++)
       {
           
           listMenuPagIzAgarra.get(i).setMuestra(true);
           //listMenuPagDer.add(listMenuPagIzAgarra.get(i));
       }
    
    for(int i=0;i<=listMenuPagIzAgarra.size()-1;i++)
       {
           
           
           if(detectasiEsta(listMenuPagIzAgarra.get(i))==0)
           { listMenuPagDer.add(listMenuPagIzAgarra.get(i));
           
           }
       }
        listMenuPagIzAgarra.clear();
     
    
    }
    
    public int detectasiEsta(MenuPaginaUsuario menuPagUs){
    
        int sw=0;
        for(int i=0;i<=listMenuPagDer.size()-1;i++)
        {
           if(listMenuPagDer.get(i).getIdpagina().equals(menuPagUs.getIdpagina()))
           {
             sw=1;
           }   
        
        
        }
            return sw;
    
    }
    
    
    
    public void devuelveValores(){
    
    
    
     
       for(int i=0;i<=listMenuPagDerAgarra.size()-1;i++)
       {  // System.out.println("tramite de vuelta::"+tramitesAgarrados2.get(i).getOperador());
           listMenuPagDer.remove(listMenuPagDerAgarra.get(i));
       }
      
     
       listMenuPagDerAgarra.clear();
       
    
    
    }
    public void creaUsuario() throws Exception{
       
         List<MenuPaginaUsuario> menuUsuarioInserta ;
        Usuario usuarioInsertado= new Usuario();
        System.out.println("nombre::"+nombre);
      if(!nombre.equals("") && nombre !=null)  
      {
         if(apPaterno !=null && !apPaterno.equals(""))  
         {
      
            //usuarioService.crudUsuario();
            if(login !=null && !login.equals(""))  
            {
                if(contrasenia !=null && !contrasenia.equals(""))  
                {
                    
                 if(!listEtapaUsuario.isEmpty())  
                 { Usuario us = new Usuario();
                    us.setNombre(nombre);
                    us.setPrimer_apellido(apPaterno);
                    us.setSegundo_apellido(apMaterno);
                    us.setCargo(cargo);
                    us.setIdregional(idregional);
                    us.setLogin(login);
                    us.setContrasenia(listaUsuarioRolService.encriptarContrasena(login,contrasenia));
                    us.setNumero_documento(numeroDoc);
                    us.setTipo_documento(tipoDoc);
                    us.setLugar_expedicion(lugarExped);
                    us.setCorreo_electronico(correoElec);
                    us.setEstado("AC");
                    usuarioInsertado=usuarioService.crudUsuario(us,1);
                    for(int i=0;i<=listEtapaUsuario.size()-1;i++)
                    { UsuarioEtapa usEtapa = new UsuarioEtapa();
                      usEtapa.setIdLogTrans(1L);
                      usEtapa.setIdUsuario(usuarioInsertado.getIdusuario());
                      usEtapa.setIdEtapa(listEtapaUsuario.get(i).getIdEtapa());
                      usEtapa.setEstado("AC");
                      usuarioEtapaService.crudUsuarioEtapa(usEtapa,1);
                    }
                    if(!listMenuPagDer.isEmpty())
                    {                    
                    
                       menuUsuarioInserta=eliminaDuplicados(listMenuPagDer);
                       for(int i=0;i<=menuUsuarioInserta.size()-1;i++)
                       {  
                           /*Inserto a usuarioMenu*/
                    
                        UsuarioMenu usuarioMenu= new UsuarioMenu();
                        usuarioMenu.setIdusuario(usuarioInsertado.getIdusuario());
                        usuarioMenu.setIdmenu(menuUsuarioInserta.get(i).getIdmenu());
                        usuarioMenu.setIdlogtrans(logtrans);
                        usuarioMenuService.crudUsuarioMenu(usuarioMenu, 1);
                       }
                        for(int i=0;i<=listMenuPagDer.size()-1;i++){
                        /*Inserto a usuariPagina*/
                        UsuarioPagina usuarioPagina= new UsuarioPagina();
                        usuarioPagina.setIdUsuario(usuarioInsertado.getIdusuario());
                        usuarioPagina.setIdPagina(listMenuPagDer.get(i).getIdpagina());
                        usuarioPagina.setIdLogTrans(logtrans);
                        usuarioPagina.setMuestra(listMenuPagDer.get(i).getMuestra());
                        usuarioPagina.setEstado(listMenuPagDer.get(i).getEstado());
                        
                        usuarioPaginaService.crudUsuarioPagina(usuarioPagina, 1);
//                        usuarioPaginaService.
                        
                        }
                    }  
                   slcPrincipales="1";
                    cambiaPrincipal();
                /*    usuarioMenuService.crudUsuarioMenu(null, parametro)
                    
                    usuarioPaginaService.
                  */       
                     FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario Insertado!!!!!", "");
                  FacesContext.getCurrentInstance().addMessage(null, message);
                 }
                 else{
                 
                  FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Debe llenar un Rol!!", "");
                  FacesContext.getCurrentInstance().addMessage(null, message);
                 
                 }
                    
                }
                else{
                  FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Debe insertar la contrasenia", "");
                  FacesContext.getCurrentInstance().addMessage(null, message);
                
                }
                
            }
            else{
             FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Debe insertar el Login", "");
              FacesContext.getCurrentInstance().addMessage(null, message);
            
            }
         
         }
         else{
             FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Debe insertar el Ap Paterno", "");
            FacesContext.getCurrentInstance().addMessage(null, message);
         
         }
      }   
      else{
      
         FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Debe insertar el Nombre", "");
            FacesContext.getCurrentInstance().addMessage(null, message);
      }  
      
    }
    public void modificaUsuario() throws Exception{
        
         List<MenuPaginaUsuario> menuUsuarioInserta ;
        Usuario usuarioInsertado= new Usuario();
         if(!nombre.equals("") && nombre !=null)  
      {
         if(apPaterno !=null && !apPaterno.equals(""))  
         {
      
            //usuarioService.crudUsuario();
            if(login !=null && !login.equals(""))  
            {
                if(contrasenia !=null && !contrasenia.equals(""))  
                {
                    
                 if(!listEtapaUsuario.isEmpty())  
                 {
                     Usuario us = new Usuario();
                    us.setIdusuario(idusuario);
                    us.setNombre(nombre);
                    us.setPrimer_apellido(apPaterno);
                    us.setSegundo_apellido(apMaterno);
                    us.setCargo(cargo);
                    us.setIdregional(idregional);
                    us.setLogin(login);
                    us.setContrasenia(listaUsuarioRolService.encriptarContrasena(login,contrasenia));
                    us.setNumero_documento(numeroDoc);
                    us.setTipo_documento(tipoDoc);
                    us.setLugar_expedicion(lugarExped);
                    us.setCorreo_electronico(correoElec);
                    us.setEstado("AC");
                    usuarioInsertado=usuarioService.crudUsuario(us,2);
                    
                    
                    int swborra=0;
                    /*********************************************/
                     //PRIMERO CONTROLO LOS ROLES ADICIONADOS Y MODIFICADOS
                    /********************************************/
                    //Recorro el for para updeter a NA lso que se quitaron
                    // System.out.println("listEtapaUsBaseParaModi.size():"+listEtapaUsBaseParaModi.size());
                    for(int i=0;i<=listEtapaUsBaseParaModi.size()-1;i++)
                    {   swborra=0;
                      //  System.out.println("/*****************/");
                        for(int j=0;j<=listEtapaUsuario.size()-1;j++)
                        {// System.out.println("listEtapaUsBaseParaModi.get(i).getIdEtapa()"+listEtapaUsBaseParaModi.get(i).getIdEtapa());
                         //   System.out.println("listEtapaUsuario.get(j).getIdEtapa():"+listEtapaUsuario.get(j).getIdEtapa());
                           if(listEtapaUsBaseParaModi.get(i).getIdEtapa().equals(listEtapaUsuario.get(j).getIdEtapa()))
                           { // System.out.println("entar a borra");
                              swborra=1;
                            }
                           
                           
                        }
                        if(swborra==0)
                           { //updatea a NA
                             //  System.out.println("listEtapaUsBaseParaModi.get(i).getIdEtapa()::"+listEtapaUsBaseParaModi.get(i).getIdEtapa());
                             //  System.out.println("iduausrio:"+idusuario);
                            usuarioEtapaService.modificaEstadoUsuario(idusuario,listEtapaUsBaseParaModi.get(i).getIdEtapa());
                           }
                        
                    }
                    int swadiciona=0;
                    //Recorro el for para adicionar si fuese el caso
                    for(int i=0;i<=listEtapaUsuario.size()-1;i++)                                                                                                                      
                    { swadiciona=0;
                        for(int j=0;j<=listEtapaUsBaseParaModi.size()-1;j++)
                        {   
                          if(listEtapaUsuario.get(i).getIdEtapa().equals(listEtapaUsBaseParaModi.get(j).getIdEtapa()))
                          {
                              swadiciona=1;
                          }
                           
                        }
                        if(swadiciona==0)
                           { //adiciona
                             UsuarioEtapa usEtapa = new UsuarioEtapa();
                             usEtapa.setIdLogTrans(1L);
                             usEtapa.setIdUsuario(idusuario);
                             usEtapa.setIdEtapa(listEtapaUsuario.get(i).getIdEtapa());
                             usEtapa.setEstado("AC");
                             usuarioEtapaService.crudUsuarioEtapa(usEtapa,1);
                           }
                        
                    }
                    /*********************************************************************/
                    //CONTROLO AHORA LA TABLA DE LA DERECHA LSO ELIMINADO S Y ADICIONADOS
                    /*********************************************************************/
                    int swborraTbl=0;
                     for(int i=0;i<=listMenuPagDerBaseParaModi.size()-1;i++)
                    {   swborraTbl=0;
                       // System.out.println("/*****************/");
                        for(int j=0;j<=listMenuPagDer.size()-1;j++)
                        { //System.out.println("listMenuPagDerBaseParaModi.get(i).getIdPaginaa()"+listMenuPagDerBaseParaModi.get(i).getIdpagina());
                          //  System.out.println("listMenuPagDer.get(j).getIdEtapa():"+listMenuPagDer.get(j).getIdpagina());
                           if(listMenuPagDerBaseParaModi.get(i).getIdpagina().equals(listMenuPagDer.get(j).getIdpagina()))
                           { // System.out.println("entar a borra");
                              swborraTbl=1;
                            }
                           
                           
                        }
                        if(swborraTbl==0)
                        { //updatea a NA
                          //  System.out.println("listMenuPagDerBaseParaModi.get(i).getIdpagina::"+listMenuPagDerBaseParaModi.get(i).getIdpagina());
                          //  System.out.println("iduausrio:"+idusuario);
                            usuarioPaginaService.quitaEstadoUsuPagina(idusuario,listMenuPagDerBaseParaModi.get(i).getIdpagina());
                        }
                        
                    }
                    
                    //Recorro el for para adicionar si fuese el caso
                     int swadicionatbl=0;
                    for(int i=0;i<=listMenuPagDer.size()-1;i++)                                                                                                                      
                    { swadicionatbl=0;
                        for(int j=0;j<=listMenuPagDerBaseParaModi.size()-1;j++)
                        {   
                          if(listMenuPagDer.get(i).getIdpagina().equals(listMenuPagDerBaseParaModi.get(j).getIdpagina()))
                          {
                              swadicionatbl=1;
                          }
                           
                        }
                        if(swadicionatbl==0)
                           { //adiciona
                               //Verifica si el usuario tiene ya un menu asociado a el y si es asi no lo crea caos contrario lo crea lo crea
                               System.out.println("idusuario"+idusuario);
                               System.out.println("istMenuPagDer.get(i).getIdmenu()::"+listMenuPagDer.get(i).getIdmenu());
                               System.out.println("usuarioMenuService.verificaExisteMenu(idusuario,listMenuPagDer.get(i).getIdmenu())"+
                                       usuarioMenuService.verificaExisteMenu(idusuario,listMenuPagDer.get(i).getIdmenu()));
                               if(!usuarioMenuService.verificaExisteMenu(idusuario,listMenuPagDer.get(i).getIdmenu()))
                               {System.out.println(" entra a crear!!");
                                UsuarioMenu usuarioMenu= new UsuarioMenu();
                                usuarioMenu.setIdusuario(idusuario);
                                usuarioMenu.setIdmenu(listMenuPagDer.get(i).getIdmenu());
                                usuarioMenu.setIdlogtrans(1L);
                                usuarioMenuService.crudUsuarioMenu(usuarioMenu, 1);
                               }
                                
                                   
                               if(!usuarioPaginaService.verificaExistePagina(idusuario,listMenuPagDer.get(i).getIdpagina()))
                               {    
                                 UsuarioPagina usuarioPagina= new UsuarioPagina();
                                 usuarioPagina.setIdUsuario(idusuario);
                                 usuarioPagina.setIdPagina(listMenuPagDer.get(i).getIdpagina());
                                 usuarioPagina.setIdLogTrans(1L);
                                 usuarioPagina.setMuestra(listMenuPagDer.get(i).getMuestra());
                                 usuarioPagina.setEstado(listMenuPagDer.get(i).getEstado());
                        
                                usuarioPaginaService.crudUsuarioPagina(usuarioPagina, 1);
                               }
                           }
                        //Modifico basicamnete 'muestra', 'estado '
                        usuarioPaginaService.modificaUsuairoPagEstadoMues(listMenuPagDer.get(i).getMuestra(),
                                                                           listMenuPagDer.get(i).getEstado(),
                                                                           listMenuPagDer.get(i).getIdpagina(),
                                                                           idusuario);
                        
                        
                    }
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario Modificado!!!!!", "");
                        FacesContext.getCurrentInstance().addMessage(null, message);
                   
                 }
                 else{
                 
                  FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Debe llenar un Rol!!", "");
                  FacesContext.getCurrentInstance().addMessage(null, message);
                 
                 }
                    
                }
                else{
                  FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Debe insertar la contrasenia", "");
                  FacesContext.getCurrentInstance().addMessage(null, message);
                
                }
                
            }
            else{
            
            
              FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Debe insertar el Login", "");
              FacesContext.getCurrentInstance().addMessage(null, message);
            
            }
         
         }
         else{
             FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Debe insertar el Ap Paterno", "");
            FacesContext.getCurrentInstance().addMessage(null, message);
         
         }
      }   
      else{
      
         FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Debe insertar el Nombre", "");
            FacesContext.getCurrentInstance().addMessage(null, message);
      }  
        
    }
    public void eliminaUsuario() throws Exception {
    
             
    
      usuarioSelec.setEstado("NA");
      usuarioService.crudUsuario(usuarioSelec,2);
    
    
      slcPrincipales="3";
      cambiaPrincipal();
    
    
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario Eliminado!!!!!", "");
        FacesContext.getCurrentInstance().addMessage(null, message);

    

   }
    
    
    
    
    
    
    
    public List<MenuPaginaUsuario> eliminaDuplicados(List<MenuPaginaUsuario> lista){
      int sw=0;
        List<MenuPaginaUsuario> resul = new  ArrayList<MenuPaginaUsuario> ();
           resul.add(lista.get(0));
     for(int i=0;i<=lista.size()-1;i++)
      {  sw=0;
         // System.out.println("**********************************");
         // System.out.println("el i:"+i);
          for(int j=0;j<=resul.size()-1;j++)
         { //  System.out.println("el j:"+j);
           //  System.out.println("lista.get(i).getIdmenu():"+lista.get(i).getIdmenu());
           //  System.out.println("resul.get(j).getIdmenu():"+resul.get(j).getIdmenu());
             if(lista.get(i).getIdmenu().equals(resul.get(j).getIdmenu()))
             { sw=1;
             }
             
          }
     
         if(sw==0) 
         {
           //  System.out.println("añade::"+lista.get(i).getIdmenu());  
          resul.add(lista.get(i));
         }    
        }
      
      return resul;
    
    
    
    }
    
    
    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public String getSlcPrincipales() {
        return slcPrincipales;
    }

    public void setSlcPrincipales(String slcPrincipales) {
        this.slcPrincipales = slcPrincipales;
    }

    public UsuarioService getUsuarioService() {
        return usuarioService;
    }

    public void setUsuarioService(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    public Long getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Long idusuario) {
        this.idusuario = idusuario;
    }

  

    public List<Usuario> getListUsuario() {
        return listUsuario;
    }

    public void setListUsuario(List<Usuario> listUsuario) {
        this.listUsuario = listUsuario;
    }

    public Usuario getUsuarioSelec() {
        return usuarioSelec;
    }

    public void setUsuarioSelec(Usuario usuarioSelec) {
        this.usuarioSelec = usuarioSelec;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApPaterno() {
        return apPaterno;
    }

    public void setApPaterno(String apPaterno) {
        this.apPaterno = apPaterno;
    }

    public String getApMaterno() {
        return apMaterno;
    }

    public void setApMaterno(String apMaterno) {
        this.apMaterno = apMaterno;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public Long getIdregional() {
        return idregional;
    }

    public void setIdregional(Long idregional) {
        this.idregional = idregional;
    }



    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public DominioService getDominioService() {
        return dominioService;
    }

    public void setDominioService(DominioService dominioService) {
        this.dominioService = dominioService;
    }

    public RegionalService getRegionalService() {
        return regionalService;
    }

    public void setRegionalService(RegionalService regionalService) {
        this.regionalService = regionalService;
    }

    public List<Regional> getListRegionalUsuaro() {
        return listRegionalUsuaro;
    }

    public void setListRegionalUsuaro(List<Regional> listRegionalUsuaro) {
        this.listRegionalUsuaro = listRegionalUsuaro;
    }

    public ListaUsuarioRolService getListaUsuarioRolService() {
        return listaUsuarioRolService;
    }

    public void setListaUsuarioRolService(ListaUsuarioRolService listaUsuarioRolService) {
        this.listaUsuarioRolService = listaUsuarioRolService;
    }

    public List<Sistema> getListSistemas() {
        return listSistemas;
    }

    public void setListSistemas(List<Sistema> listSistemas) {
        this.listSistemas = listSistemas;
    }

    public SistemaService getSistemaService() {
        return sistemaService;
    }

    public void setSistemaService(SistemaService sistemaService) {
        this.sistemaService = sistemaService;
    }

    public Long getIdsistema() {
        return idsistema;
    }

    public void setIdsistema(Long idsistema) {
        this.idsistema = idsistema;
    }

    public List<MenuPaginaUsuario> getListMenuPagIz() {
        return listMenuPagIz;
    }

    public void setListMenuPagIz(List<MenuPaginaUsuario> listMenuPagIz) {
        this.listMenuPagIz = listMenuPagIz;
    }

    public List<MenuPaginaUsuario> getListMenuPagIzAgarra() {
        return listMenuPagIzAgarra;
    }

    public void setListMenuPagIzAgarra(List<MenuPaginaUsuario> listMenuPagIzAgarra) {
        this.listMenuPagIzAgarra = listMenuPagIzAgarra;
    }

    public MenuPaginaService getMenuPaginaService() {
        return menuPaginaService;
    }

    public void setMenuPaginaService(MenuPaginaService menuPaginaService) {
        this.menuPaginaService = menuPaginaService;
    }

    public List<MenuPaginaUsuario> getListMenuPagDer() {
        return listMenuPagDer;
    }

    public void setListMenuPagDer(List<MenuPaginaUsuario> listMenuPagDer) {
        this.listMenuPagDer = listMenuPagDer;
    }

    public List<MenuPaginaUsuario> getListMenuPagDerAgarra() {
        return listMenuPagDerAgarra;
    }

    public void setListMenuPagDerAgarra(List<MenuPaginaUsuario> listMenuPagDerAgarra) {
        this.listMenuPagDerAgarra = listMenuPagDerAgarra;
    }

    public boolean isDesactivaRecibe() {
        return desactivaRecibe;
    }

    public void setDesactivaRecibe(boolean desactivaRecibe) {
        this.desactivaRecibe = desactivaRecibe;
    }

    public boolean isDesactivaDevuelve() {
        return desactivaDevuelve;
    }

    public void setDesactivaDevuelve(boolean desactivaDevuelve) {
        this.desactivaDevuelve = desactivaDevuelve;
    }

    public boolean isDesactivaCrea() {
        return desactivaCrea;
    }

    public void setDesactivaCrea(boolean desactivaCrea) {
        this.desactivaCrea = desactivaCrea;
    }

    public boolean isDesactivaModifica() {
        return desactivaModifica;
    }

    public void setDesactivaModifica(boolean desactivaModifica) {
        this.desactivaModifica = desactivaModifica;
    }

    public boolean isDesactivaElimina() {
        return desactivaElimina;
    }

    public void setDesactivaElimina(boolean desactivaElimina) {
        this.desactivaElimina = desactivaElimina;
    }

    public List<Etapa> getListEtapaUsuario() {
        return listEtapaUsuario;
    }

    public void setListEtapaUsuario(List<Etapa> listEtapaUsuario) {
        this.listEtapaUsuario = listEtapaUsuario;
    }

    public EtapaService getEtapaService() {
        return etapaService;
    }

    public void setEtapaService(EtapaService etapaService) {
        this.etapaService = etapaService;
    }

    public Long getIdetapa() {
        return idetapa;
    }

    public void setIdetapa(Long idetapa) {
        this.idetapa = idetapa;
    }

    public List<Etapa> getListEtapas() {
        return listEtapas;
    }

    public void setListEtapas(List<Etapa> listEtapas) {
        this.listEtapas = listEtapas;
    }

    public boolean isDibujaUsuario() {
        return dibujaUsuario;
    }

    public void setDibujaUsuario(boolean dibujaUsuario) {
        this.dibujaUsuario = dibujaUsuario;
    }

    public UsuarioEtapaService getUsuarioEtapaService() {
        return usuarioEtapaService;
    }

    public void setUsuarioEtapaService(UsuarioEtapaService usuarioEtapaService) {
        this.usuarioEtapaService = usuarioEtapaService;
    }

    public UsuarioPaginaService getUsuarioPaginaService() {
        return usuarioPaginaService;
    }

    public void setUsuarioPaginaService(UsuarioPaginaService usuarioPaginaService) {
        this.usuarioPaginaService = usuarioPaginaService;
    }

    public UsuarioMenuService getUsuarioMenuService() {
        return usuarioMenuService;
    }

    public void setUsuarioMenuService(UsuarioMenuService usuarioMenuService) {
        this.usuarioMenuService = usuarioMenuService;
    }

    public List<Etapa> getListEtapaUsBaseParaModi() {
        return listEtapaUsBaseParaModi;
    }

    public void setListEtapaUsBaseParaModi(List<Etapa> listEtapaUsBaseParaModi) {
        this.listEtapaUsBaseParaModi = listEtapaUsBaseParaModi;
    }

    public Long getLogtrans() {
        return logtrans;
    }

    public void setLogtrans(Long logtrans) {
        this.logtrans = logtrans;
    }

    public List<MenuPaginaUsuario> getListMenuPagDerBaseParaModi() {
        return listMenuPagDerBaseParaModi;
    }

    public void setListMenuPagDerBaseParaModi(List<MenuPaginaUsuario> listMenuPagDerBaseParaModi) {
        this.listMenuPagDerBaseParaModi = listMenuPagDerBaseParaModi;
    }

    public ComunService getComunService() {
        return comunService;
    }

    public void setComunService(ComunService comunService) {
        this.comunService = comunService;
    }

    public LogTransService getLogTransService() {
        return logTransService;
    }

    public void setLogTransService(LogTransService logTransService) {
        this.logTransService = logTransService;
    }

    public String getNumeroDoc() {
        return numeroDoc;
    }

    public void setNumeroDoc(String numeroDoc) {
        this.numeroDoc = numeroDoc;
    }

    public String getTipoDoc() {
        return tipoDoc;
    }

    public void setTipoDoc(String tipoDoc) {
        this.tipoDoc = tipoDoc;
    }

    public String getLugarExped() {
        return lugarExped;
    }

    public void setLugarExped(String lugarExped) {
        this.lugarExped = lugarExped;
    }

    public String getCorreoElec() {
        return correoElec;
    }

    public void setCorreoElec(String correoElec) {
        this.correoElec = correoElec;
    }

    public List<Dominio> getListTipoDocumento() {
        return listTipoDocumento;
    }

    public void setListTipoDocumento(List<Dominio> listTipoDocumento) {
        this.listTipoDocumento = listTipoDocumento;
    }

    public List<Dominio> getListlugarExpe() {
        return listlugarExpe;
    }

    public void setListlugarExpe(List<Dominio> listlugarExpe) {
        this.listlugarExpe = listlugarExpe;
    }

  
   
}







