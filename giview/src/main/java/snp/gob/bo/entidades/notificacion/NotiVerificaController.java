/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.entidades.notificacion;

import com.google.common.collect.ComparisonChain;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import static java.util.Collections.list;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import snp.gob.bo.entidades.bean.common.AbstractManagedBean;
import snp.gob.bo.gimodel.entidad.Dominio;
import snp.gob.bo.gimodel.entidad.LogTrans;
import snp.gob.bo.gimodel.entidad.ModModificacion;
import snp.gob.bo.gimodel.entidad.NotificacionBuzon;
import snp.gob.bo.gimodel.entidad.RenSolicitudRenovacion;
import snp.gob.bo.gimodel.entidad.Seguimiento;
import snp.gob.bo.gimodel.entidad.SigSignoMarca;
import snp.gob.bo.gimodel.entidad.Usuario;
import snp.gob.bo.gimodel.servicio.ComunService;
import snp.gob.bo.gimodel.servicio.DominioService;
import snp.gob.bo.gimodel.servicio.FlujoTiempoService;
import snp.gob.bo.gimodel.servicio.LogTransService;
import snp.gob.bo.gimodel.servicio.ModModificacionService;
import snp.gob.bo.gimodel.servicio.NotificacionBuzonService;
import snp.gob.bo.gimodel.servicio.NotificacionService;
import snp.gob.bo.gimodel.servicio.RenSolicitudRenovacionService;
import snp.gob.bo.gimodel.servicio.SeguimientoService;
import snp.gob.bo.gimodel.servicio.SigSignoMarcaService;
import snp.gob.bo.gimodel.servicio.UsuarioService;

/**
 *
 * @author levi
 */
@ManagedBean
@ViewScoped
public class NotiVerificaController extends AbstractManagedBean implements Serializable {
    private Long idoperador; 
    private Integer bloque;
    private Integer rango1;
    private Integer rango2;
    private Long logtrans;
    @ManagedProperty(value = "#{dominioService}")
    private DominioService dominioService;
    @ManagedProperty(value = "#{comunService}")
    private ComunService comunService;
    @ManagedProperty(value = "#{notificacionBuzonService}")
    private NotificacionBuzonService notificacionBuzonService;
    @ManagedProperty(value = "#{notificacionService}")
    private NotificacionService notificacionService;
    @ManagedProperty(value = "#{usuarioService}")
    private UsuarioService usuarioService;
    @ManagedProperty(value = "#{sigSignoMarcaService}")
    private SigSignoMarcaService sigSignoMarcaService;
     @ManagedProperty(value = "#{logTransService}")
    private LogTransService logTransService;
    @ManagedProperty(value = "#{seguimientoService}")
    private SeguimientoService seguimientoService;
    @ManagedProperty(value = "#{modModificacionService}")
    private ModModificacionService modModificacionService;
    @ManagedProperty(value = "#{renSolicitudRenovacionService}")
    private RenSolicitudRenovacionService renSolicitudRenovacionService;
    @ManagedProperty(value = "#{flujoTiempoService}")
    private FlujoTiempoService flujoTiempoService;
    
    private List<NotificacionBuzon> listaNotiBuzon = new ArrayList<NotificacionBuzon>();
    /*tramites son los que se pintan en la tabla de la izq*/
      private List<NotificacionBuzon> tramites = new ArrayList<NotificacionBuzon>();
      /*tramitesAgarrados son los que se escogen cuando se hace click*/
     private List<NotificacionBuzon> tramitesAgarrados = new ArrayList<NotificacionBuzon>();
     /*tramiteATraspasar son los que estan en la tabla del lado derecho*/
    private List<NotificacionBuzon> tramiteATraspasar = new ArrayList<NotificacionBuzon>();
    /*tramitesAgarrados2 son los que se escogen delado derecho*/
    private List<NotificacionBuzon> tramitesAgarrados2 = new ArrayList<NotificacionBuzon>();
    /*tramites que se agarran por le filtro*/
    private List<NotificacionBuzon> tramitesAgarradosFiltro = new ArrayList<NotificacionBuzon>();
    
    private List<Usuario> listUsuarioOpera = new ArrayList<Usuario>();
     @PostConstruct
    public void init() {
         try {
             //List<String> citiesSource = new ArrayList<String>();
          //   System.out.println("TAM de tramites::"+notificacionBuzonService.listaNotificacionBuzon().size());
             tramites=notificacionBuzonService.listaNotificacionBuzon();
             //System.out.println("En el init"+tramites.get(0).get);
             //listaEstadoNotifi=dominioService.obtenerListadoDominio("estado_notificacion");
             listUsuarioOpera=usuarioService.listaUsuario();
             
          
             /*Saco para el logtrans*/
              Date fechaSistema = comunService.obtenerFechaHoraServidor(1L);
              List<Usuario> listUsuario=usuarioService.listaUsuarioXidPagina(super.getIdUsuarioSession());
              LogTrans logTransGuardado = logTransService.crudLogTrans(new LogTrans(super.getIdUsuarioSession(), fechaSistema), 1);
              logtrans = logTransGuardado.getIdLogTrans();
           
         } catch (Exception ex) {
             Logger.getLogger(NotiVerificaController.class.getName()).log(Level.SEVERE, null, ex);
         }
        
        
    }
  public void agarraXFiltro(){
      int cont=0;
      tramitesAgarradosFiltro.clear();
      if(this.bloque != null && !this.bloque.equals("") )
      {  if(this.rango1 != null && !this.rango1.equals("") )
         { if(this.rango2 != null && !this.rango2.equals("") )
           { 
              for(int i=0;i<=tramites.size()-1;i++)
              {    System.out.println("idoperador::"+this.idoperador);
                   System.out.println("idusuariosolicitante::"+tramites.get(i).getId_usuario_solicitante());
                   if(this.idoperador.equals(tramites.get(i).getId_usuario_solicitante()))
                   {   System.out.println("this.bloque::"+this.bloque);
                       System.out.println("tramites.get(i).getBloque()::"+tramites.get(i).getBloque());
                       if(this.bloque.equals(tramites.get(i).getBloque()))
                       {   System.out.println("tramites.get(i).getNro_exped()::"+tramites.get(i).getNro_exped());
                           System.out.println("this.rango1 ::"+this.rango1 );
                           System.out.println("this.rango2 ::"+this.rango2 );
                           if(tramites.get(i).getNro_exped() >= this.rango1 && tramites.get(i).getNro_exped() <= this.rango2)
                           {
                              tramitesAgarradosFiltro.add( tramites.get(i));
                              cont++;
                           }
                       }
                           
                           
                           
                   }    
              
              }
               System.out.println("tramites qeu compten::"+tramitesAgarradosFiltro.size());
         
           }
           else{
                 FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Ingrese el numero de Rango 2","");
                FacesContext.getCurrentInstance().addMessage(null, message);
            }
         }
         else{
           FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Ingrese el numero de Rango 1","");
           FacesContext.getCurrentInstance().addMessage(null, message);
         }
      }
      else{
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Ingrese el numero de bloque","");
        FacesContext.getCurrentInstance().addMessage(null, message);
      }
      System.out.println("canti::"+tramitesAgarradosFiltro.size());
      for(int i=0;i<=tramitesAgarradosFiltro.size()-1;i++)
      {
      
        tramites.remove(tramitesAgarradosFiltro.get(i));
        tramiteATraspasar.add(tramitesAgarradosFiltro.get(i));
      }
       ordenaTablaIzq();
       ordenaTablaDer();
  
  }
 public Long insertaIndex(Long cont){
 
     //tramite.setConteo(cont);
     tramites.get(cont.intValue()-1).setConteo(cont-1);
     return cont;
 }
    
  public void agarraValores(){
    
      
      
       for(int i=0;i<=tramitesAgarrados.size()-1;i++)
       {
            tramites.remove(tramitesAgarrados.get(i));
           System.out.println("tramite agarrados conteo:"+tramitesAgarrados.get(i).getConteo());
            tramiteATraspasar.add(tramitesAgarrados.get(i));
       }
        System.out.println("tramiteATraspasar primer elemento"+tramiteATraspasar.get(0).getIdnotificacion());
       ordenaTablaIzq();
       ordenaTablaDer();
      tramitesAgarrados.clear();
  
  }
 
  public void devuelveValores(){
      System.out.println("tam tramitesAgarrados2::"+tramitesAgarrados2.size());
       for(int i=0;i<=tramitesAgarrados2.size()-1;i++)
       {  // System.out.println("tramite de vuelta::"+tramitesAgarrados2.get(i).getOperador());
           tramiteATraspasar.remove(tramitesAgarrados2.get(i));
       }
      
       for(int i=0;i<=tramitesAgarrados2.size()-1;i++){
           tramites.add(tramitesAgarrados2.get(i));
           
           //System.out.println("se adiciona:::"+tramitesAgarrados2.get(i).getOperador());
       }
       tramitesAgarrados2.clear();
       
       
   
       
       /*
       Collections.sort(tramites, new Comparator() {
	@Override
	public int compare(Object o1, Object o2) {
             
            NotificacionBuzon tra1=(NotificacionBuzon)o1;
            NotificacionBuzon tra2=(NotificacionBuzon)o2;
		return tra1.getNombre().compareTo(tra2.getNombre());
	}
      });*/
       /*Se va ordenando prmero por nombre, luego ahi mismo por bloque, luego por numero de expediente */
     Collections.sort(tramites, new Comparator<NotificacionBuzon>() {  
    @Override  
    public int compare(NotificacionBuzon p1, NotificacionBuzon p2) {  
        return ComparisonChain.start().compare(p1.getNombre(), p2.getNombre()).compare(p1.getBloque(), p2.getBloque()).compare(p1.getNro_exped(), p2.getNro_exped()).result();  
        // or in case the fields can be null:  
        /* 
        return ComparisonChain.start() 
           .compare(p1.size, p2.size, Ordering.natural().nullsLast()) 
           .compare(p1.nrOfToppings, p2.nrOfToppings, Ordering.natural().nullsLast()) 
           .compare(p1.name, p2.name, Ordering.natural().nullsLast()) 
           .result(); 
        */  
    }  
     });      
       
  /*las funciones de abajo van colocando el numero en el campo conteo  QUE CREO YA NO ES NECESARIO YA QUE  USO INDE X EN LA VISTA*/
       ordenaTablaIzq();
       ordenaTablaDer();
  }
  
     public void ordenaTablaIzq(){
        for(int i=0;i<=tramites.size()-1;i++){
            
            tramites.get(i).setConteo((i+1L));
        
        }
    
    }
     public void ordenaTablaDer(){
        for(int i=0;i<=tramiteATraspasar.size()-1;i++){
            tramiteATraspasar.get(i).setConteo(i+1L);
        
        }
    
    }
     
  

    public void  aceptaCambios() throws Exception{
        System.out.println("ACEPTA EL NOTIFICADOR LOS TRAMITES EN SU BANDEJA");
        //System.out.println("Del tramite::"+tramiteATraspasar.get(0).getIdnotificacion());
        //System.out.println("Elusuario::"+super.getIdUsuarioSession());
       notificacionService.recibeListaEntradaNotificaciones(tramiteATraspasar  ,super.getIdUsuarioSession());
       
       //Lleno para que se adicione en sigsegumiento, menos d e oposiciona cancelacion y nulidad qeu se establecio que no se veria
       
       for(int i=0;i<=tramiteATraspasar.size()-1;i++)
       {  System.out.println("tramiteATraspasar.get(i).getTipo_tramite_notificacion()::"+tramiteATraspasar.get(i).getTipo_tramite_notificacion());
         if(!tramiteATraspasar.get(i).getTipo_tramite_notificacion().equals("OPOSICION SIG")&&
                 !tramiteATraspasar.get(i).getTipo_tramite_notificacion().equals("CANCELACION") &&
                 !tramiteATraspasar.get(i).getTipo_tramite_notificacion().equals("NULIDAD SIG") &&
                 !tramiteATraspasar.get(i).getTipo_tramite_notificacion().equals("IF") &&
                 !tramiteATraspasar.get(i).getTipo_tramite_notificacion().equals("MEF")
                 //!tramiteATraspasar.get(i).getTipo_tramite_notificacion().equals("N° REG")&&
                 //!tramiteATraspasar.get(i).getTipo_tramite_notificacion().equals("N° PUB SIGNO")
                 ) 
         { 
           
            if(tramiteATraspasar.get(i).getTipo_tramite_notificacion().equals("N° REG"))
           { //Pregunto si econtro el registro en la tabla sigsignomarca
              if(sigSignoMarcaService.listaSigSignoMarcaXRegistro(Long.parseLong(tramiteATraspasar.get(i).getExpediente()),tramiteATraspasar.get(i).getExtension(),"") != null)
              {  SigSignoMarca signoMarca =sigSignoMarcaService.listaSigSignoMarcaXRegistro(Long.parseLong(tramiteATraspasar.get(i).getExpediente()),tramiteATraspasar.get(i).getExtension(),"");  
                 Seguimiento seguimientoNuevo = new Seguimiento();
                 seguimientoNuevo.setId(signoMarca.getIdSignoMarca());
                 seguimientoNuevo.setIdUsuario(super.getIdUsuarioSession());
        
                 seguimientoNuevo.setIdLogTrans(logtrans);
            
                 seguimientoNuevo.setSm(signoMarca.getSm());
                 seguimientoNuevo.setNumeroPublicacion(signoMarca.getNumeroPublicacion());
                 seguimientoNuevo.setNumeroRegistro(signoMarca.getNumeroRegistro());
                 seguimientoNuevo.setSerieRegistro(signoMarca.getSerieRegistro());
                 seguimientoNuevo.setEtapa("NOT");
                 seguimientoNuevo.setObservacion(null);
                 seguimientoNuevo.setEditable(false);
                 seguimientoNuevo.setEstado("AC");
                  seguimientoService.guardar_modificar_Seguimiento_etapa(seguimientoNuevo, "SIG");
              }
           }
            if(tramiteATraspasar.get(i).getTipo_tramite_notificacion().equals("N° PUB SIGNO"))
           {
              if(sigSignoMarcaService.listaSigSignoMarcaXPublicacion(Long.parseLong(tramiteATraspasar.get(i).getExpediente())) != null)
              {  SigSignoMarca signoMarca = sigSignoMarcaService.listaSigSignoMarcaXPublicacion(Long.parseLong(tramiteATraspasar.get(i).getExpediente()));
                 Seguimiento seguimientoNuevo = new Seguimiento();
                 seguimientoNuevo.setId(signoMarca.getIdSignoMarca());
                 seguimientoNuevo.setIdUsuario(super.getIdUsuarioSession());
        
                 seguimientoNuevo.setIdLogTrans(logtrans);
            
                 seguimientoNuevo.setSm(signoMarca.getSm());
                 seguimientoNuevo.setNumeroPublicacion(signoMarca.getNumeroPublicacion());
                 seguimientoNuevo.setNumeroRegistro(signoMarca.getNumeroRegistro());
                 seguimientoNuevo.setSerieRegistro(signoMarca.getSerieRegistro());
                 seguimientoNuevo.setEtapa("NOT");
                 seguimientoNuevo.setObservacion(null);
                 seguimientoNuevo.setEditable(false);
                 seguimientoNuevo.setEstado("AC");
                  seguimientoService.guardar_modificar_Seguimiento_etapa(seguimientoNuevo, "SIG");
              }
           }
           if(tramiteATraspasar.get(i).getTipo_tramite_notificacion().equals("SM"))
           { // System.out.println("entra al  if");
             Seguimiento seguimientoNuevo = new Seguimiento();
             Long sm;
             if(tramiteATraspasar.get(i).getExtension() == null)
             {   sm = comunService.codificarCodigoSM(tramiteATraspasar.get(i).getExpediente(), tramiteATraspasar.get(i).getGestion().toString(),"");
             }
             else{
                 sm = comunService.codificarCodigoSM(tramiteATraspasar.get(i).getExpediente(), tramiteATraspasar.get(i).getGestion().toString(),tramiteATraspasar.get(i).getExtension());
             }
             
             SigSignoMarca signoMarca=sigSignoMarcaService.listaSigSignoMarcaXSM(sm);
              System.out.println("idsignomarca::"+signoMarca.getIdSignoMarca());
             seguimientoNuevo.setId(signoMarca.getIdSignoMarca());
             seguimientoNuevo.setIdUsuario(super.getIdUsuarioSession());
        
             seguimientoNuevo.setIdLogTrans(logtrans);
            
             seguimientoNuevo.setSm(sm);
             seguimientoNuevo.setNumeroPublicacion(signoMarca.getNumeroPublicacion());
             seguimientoNuevo.setNumeroRegistro(signoMarca.getNumeroRegistro());
             seguimientoNuevo.setSerieRegistro(signoMarca.getSerieRegistro());
         
      //  List<Etapa>  listEtapaUs=etapaService.listadoPorIdUsuario(super.getIdUsuarioSession());
        
             seguimientoNuevo.setEtapa("NOT");
             seguimientoNuevo.setObservacion(null);
             seguimientoNuevo.setEditable(false);
             seguimientoNuevo.setEstado("AC");
             seguimientoNuevo.setPlazo_limite(flujoTiempoService.obtieneFlujoXIdetapa("NOT",1));
             seguimientoService.guardar_modificar_Seguimiento_etapa(seguimientoNuevo, "SIG");
          }
          if((!tramiteATraspasar.get(i).getTipo_tramite_notificacion().equals("SM")) && 
              (!tramiteATraspasar.get(i).getTipo_tramite_notificacion().equals("SR")) &&
              (!tramiteATraspasar.get(i).getTipo_tramite_notificacion().equals("N° REG")) &&
              (!tramiteATraspasar.get(i).getTipo_tramite_notificacion().equals("N° PUB SIGNO")) &&
              (!tramiteATraspasar.get(i).getTipo_tramite_notificacion().equals("BQ")) &&
              (!tramiteATraspasar.get(i).getTipo_tramite_notificacion().equals("CE")) &&
              (!tramiteATraspasar.get(i).getTipo_tramite_notificacion().equals("IF")) &&
              (!tramiteATraspasar.get(i).getTipo_tramite_notificacion().equals("MEF"))
               
            )
          {  
              System.out.println("Entra a modificacion supuestamente..");
              ModModificacion modi=modModificacionService.buscarModModificacionXCodigo(tramiteATraspasar.get(i).getTipo_tramite_notificacion(),
                                                                                         Long.parseLong(tramiteATraspasar.get(i).getExpediente()),
                                                                                         tramiteATraspasar.get(i).getGestion());
             
              if(modi != null)
              {  Seguimiento seguimientoNuevo = new Seguimiento();
           
             
                seguimientoNuevo.setIdUsuario(super.getIdUsuarioSession());
                seguimientoNuevo.setId(modi.getIdmodificacion());
                seguimientoNuevo.setIdLogTrans(logtrans);
            
             seguimientoNuevo.setSm(modi.getSm());
             seguimientoNuevo.setNumeroPublicacion(modi.getNumero_publicacion());
             seguimientoNuevo.setNumeroRegistro(modi.getNumero_registro());
             seguimientoNuevo.setSerieRegistro(modi.getSerie_registro());
        
             seguimientoNuevo.setEtapa("NOT");
             seguimientoNuevo.setObservacion(null);
             seguimientoNuevo.setEditable(false);
             seguimientoNuevo.setEstado("AC");
              seguimientoNuevo.setPlazo_limite(flujoTiempoService.obtieneFlujoXIdetapa("NOT",1));
             seguimientoService.guardar_modificar_Seguimiento_etapa(seguimientoNuevo, "MOD");
              }
          }
          if(tramiteATraspasar.get(i).getTipo_tramite_notificacion().equals("SR"))
          {   System.out.println("Entra a renovacion..");
              Date fechaServidor = comunService.obtenerFechaHoraServidor(1L);
              RenSolicitudRenovacion ren= renSolicitudRenovacionService.buscaSolicitudRenovacionPorNumeroSrYGestion(Long.parseLong(tramiteATraspasar.get(i).getExpediente()),
                                                                                         tramiteATraspasar.get(i).getGestion());
              if(ren != null)
              {  
              Seguimiento seguimientoNuevo = new Seguimiento();
             seguimientoNuevo.setId(ren.getIdsolicitudrenovacion());
             seguimientoNuevo.setIdUsuario(super.getIdUsuarioSession());
        
             seguimientoNuevo.setIdLogTrans(logtrans);
            
             seguimientoNuevo.setSm(ren.getSm());
             seguimientoNuevo.setNumeroRegistro(ren.getNumero_registro_registrado());
             seguimientoNuevo.setSerieRegistro(ren.getSerie_registro_registrado());
        
             seguimientoNuevo.setEtapa("NOT");
             seguimientoNuevo.setObservacion(null);
             seguimientoNuevo.setEditable(false);
              seguimientoNuevo.setPlazo_limite(flujoTiempoService.obtieneFlujoXIdetapa("NOT",1));
             seguimientoNuevo.setEstado("AC");
             seguimientoService.guardar_modificar_Seguimiento_etapa(seguimientoNuevo, "REN");
              }
          }
        }
       }
       this.tramiteATraspasar.clear();
       FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "TRAMITES RECIBIDOS","");
       FacesContext.getCurrentInstance().addMessage(null, message);
             
      
    } 
    
    public String nombreUsuarioXID(Long idusuario) throws Exception{
       String nomCompleto="";
      List<Usuario> listUsuario=usuarioService.listaUsuarioXidPagina(idusuario);
      nomCompleto=listUsuario.get(0).getNombre();
      if(listUsuario.get(0).getPrimer_apellido() !=null && !listUsuario.get(0).getPrimer_apellido().equals(""))
      {
          nomCompleto=nomCompleto+" "+listUsuario.get(0).getPrimer_apellido();
      }
      
      return nomCompleto;
      
    
    }
            
     
     
    public DominioService getDominioService() {
        return dominioService;
    }

    public void setDominioService(DominioService dominioService) {
        this.dominioService = dominioService;
    }

    public ComunService getComunService() {
        return comunService;
    }

    public void setComunService(ComunService comunService) {
        this.comunService = comunService;
    }

    public NotificacionBuzonService getNotificacionBuzonService() {
        return notificacionBuzonService;
    }

    public void setNotificacionBuzonService(NotificacionBuzonService notificacionBuzonService) {
        this.notificacionBuzonService = notificacionBuzonService;
    }

    public List<NotificacionBuzon> getListaNotiBuzon() {
        return listaNotiBuzon;
    }

    public void setListaNotiBuzon(List<NotificacionBuzon> listaNotiBuzon) {
        this.listaNotiBuzon = listaNotiBuzon;
    }

    public List<NotificacionBuzon> getTramites() {
        return tramites;
    }

    public void setTramites(List<NotificacionBuzon> tramites) {
        this.tramites = tramites;
    }

    public List<NotificacionBuzon> getTramitesAgarrados() {
        return tramitesAgarrados;
    }

    public void setTramitesAgarrados(List<NotificacionBuzon> tramitesAgarrados) {
        this.tramitesAgarrados = tramitesAgarrados;
    }

    public List<NotificacionBuzon> getTramiteATraspasar() {
        return tramiteATraspasar;
    }

    public void setTramiteATraspasar(List<NotificacionBuzon> tramiteATraspasar) {
        this.tramiteATraspasar = tramiteATraspasar;
    }

    public List<NotificacionBuzon> getTramitesAgarrados2() {
        return tramitesAgarrados2;
    }

    public void setTramitesAgarrados2(List<NotificacionBuzon> tramitesAgarrados2) {
        this.tramitesAgarrados2 = tramitesAgarrados2;
    }

    public NotificacionService getNotificacionService() {
        return notificacionService;
    }

    public void setNotificacionService(NotificacionService notificacionService) {
        this.notificacionService = notificacionService;
    }

    public UsuarioService getUsuarioService() {
        return usuarioService;
    }

    public void setUsuarioService(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    public List<Usuario> getListUsuarioOpera() {
        return listUsuarioOpera;
    }

    public void setListUsuarioOpera(List<Usuario> listUsuarioOpera) {
        this.listUsuarioOpera = listUsuarioOpera;
    }

    public Long getIdoperador() {
        return idoperador;
    }

    public void setIdoperador(Long idoperador) {
        this.idoperador = idoperador;
    }

    public Integer getBloque() {
        return bloque;
    }

    public void setBloque(Integer bloque) {
        this.bloque = bloque;
    }

    public List<NotificacionBuzon> getTramitesAgarradosFiltro() {
        return tramitesAgarradosFiltro;
    }

    public void setTramitesAgarradosFiltro(List<NotificacionBuzon> tramitesAgarradosFiltro) {
        this.tramitesAgarradosFiltro = tramitesAgarradosFiltro;
    }

    public Integer getRango1() {
        return rango1;
    }

    public void setRango1(Integer rango1) {
        this.rango1 = rango1;
    }

    public Integer getRango2() {
        return rango2;
    }

    public void setRango2(Integer rango2) {
        this.rango2 = rango2;
    }

    public Long getLogtrans() {
        return logtrans;
    }

    public void setLogtrans(Long logtrans) {
        this.logtrans = logtrans;
    }

    public SigSignoMarcaService getSigSignoMarcaService() {
        return sigSignoMarcaService;
    }

    public void setSigSignoMarcaService(SigSignoMarcaService sigSignoMarcaService) {
        this.sigSignoMarcaService = sigSignoMarcaService;
    }

    public LogTransService getLogTransService() {
        return logTransService;
    }

    public void setLogTransService(LogTransService logTransService) {
        this.logTransService = logTransService;
    }

    public SeguimientoService getSeguimientoService() {
        return seguimientoService;
    }

    public void setSeguimientoService(SeguimientoService seguimientoService) {
        this.seguimientoService = seguimientoService;
    }

    public ModModificacionService getModModificacionService() {
        return modModificacionService;
    }

    public void setModModificacionService(ModModificacionService modModificacionService) {
        this.modModificacionService = modModificacionService;
    }

    public RenSolicitudRenovacionService getRenSolicitudRenovacionService() {
        return renSolicitudRenovacionService;
    }

    public void setRenSolicitudRenovacionService(RenSolicitudRenovacionService renSolicitudRenovacionService) {
        this.renSolicitudRenovacionService = renSolicitudRenovacionService;
    }

    public FlujoTiempoService getFlujoTiempoService() {
        return flujoTiempoService;
    }

    public void setFlujoTiempoService(FlujoTiempoService flujoTiempoService) {
        this.flujoTiempoService = flujoTiempoService;
    }




   

    
    
}
