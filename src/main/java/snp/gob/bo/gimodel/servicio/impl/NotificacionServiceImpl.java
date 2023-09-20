/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.servicio.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import snp.gob.bo.gimodel.entidad.Notificacion;
import snp.gob.bo.gimodel.entidad.NotificacionBuzon;
import snp.gob.bo.gimodel.mapper.NotificacionBuzonMapper;
import snp.gob.bo.gimodel.mapper.NotificacionMapper;
import snp.gob.bo.gimodel.servicio.NotificacionService;

/**
 *
 * @author levi
 */
@Service("notificacionService")
public class NotificacionServiceImpl implements NotificacionService{
     private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    @Override
    @Autowired
    public void setDataSource(DataSource dataSource) {
         try {
            this.dataSource = dataSource;
            this.jdbcTemplate = new JdbcTemplate(dataSource);
        } catch (Exception e) {
            throw e;
        }
    }
    
     @Override
    public Notificacion guardaNotificacion(Notificacion notificacion)  {
    
      String SQL = "select * from inserta_notificacion(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";//modifica
        Notificacion notifica = (Notificacion) jdbcTemplate.queryForObject(SQL, new NotificacionMapper(),                                             
                notificacion.getIdlogtrans(),
                notificacion.getBloque(),
                notificacion.getNro_exped(),
                notificacion.getId_usuario_solicitante(),
                notificacion.getTipo_tramite_notificacion(),
                notificacion.getExpediente(),
                notificacion.getGestion(),
                notificacion.getExtension(),
                notificacion.getDemandante(),
                notificacion.getDemandante_cod_estado(),
                notificacion.getDemandante_fecha_devol(),
                notificacion.getDemandante_fecha_noti(),
                notificacion.getDemandante_lugar_notificacion(),
                notificacion.getDemandante_solic(),
                notificacion.getDemandante_apod(),
                notificacion.getDemandante_fojas(),
                notificacion.getDemandante_con(),
                notificacion.getDemandante_direc(),
                notificacion.getDemandante_cel(),
                notificacion.getDemandado(),
                notificacion.getDemandado_cod_estado(),
                notificacion.getDemandado_fecha_devol(),
                notificacion.getDemandado_fecha_noti(),
                notificacion.getDemandado_lugar_notificacion(),
                notificacion.getDemandado_solic(),
                notificacion.getDemandado_apod(),
                notificacion.getDemandado_fojas(),
                notificacion.getDemandado_con(),
                notificacion.getDemandado_direc(),
                notificacion.getDemandado_cel(),
                notificacion.getFecha_ingreso(),
                notificacion.getTipo(),
                notificacion.getObs(),
                notificacion.getHistorial(),
                notificacion.getFecha_recep(),
                notificacion.getObs_notifi(),
                notificacion.getId_usuario_notificador(),
               // notificacion.getCiudad_notificacion(),
                notificacion.getEstado_marca(),
                notificacion.getForm_noti_pre(),
                notificacion.getForm_noti_cuerpo()
              
        );
        return notifica;
    
    }
     @Override
    public void guardaListaNotificaciones(List<Notificacion> listaNotificaciones){
        int esta=0;
            for(int i=0;i<=listaNotificaciones.size()-1;i++)
            {     
               /* System.out.println(":::::"+listaNotificaciones.get(i).getBloque()+" "+
                                                   listaNotificaciones.get(i).getId_usuario_solicitante()+" "+
                                                   listaNotificaciones.get(i).getTipo_tramite_notificacion()+" "+
                                                   listaNotificaciones.get(i).getExpediente()+" "+
                                                   listaNotificaciones.get(i).getGestion());
                
                */
                   /*esta=this.obtenerTramiteNotificacion(listaNotificaciones.get(i).getBloque(),
                                                   listaNotificaciones.get(i).getId_usuario_solicitante(),
                                                   listaNotificaciones.get(i).getTipo_tramite_notificacion(),
                                                   listaNotificaciones.get(i).getExpediente(),
                                                   listaNotificaciones.get(i).getGestion());
                */   
                if(esta==0)
                   { guardaNotificacion(listaNotificaciones.get(i));
                   
                   }
            
            }
            
    
    }
    
     @Override
    public Integer obtieneBloqueNuevo(Integer id_usuariosolicitante){
      String SQL = "select * from obtiene_numerobloquenuevonotifica(?);";//Modifica
       Integer numBloque =  jdbcTemplate.queryForObject(SQL, new Object[]{id_usuariosolicitante}, Integer.class);
                
                
        return numBloque;
        
        
    }
    
     @Override
    public  List<Notificacion> getNotificacionXbloqueXusuariosol(Integer bloque, Long id_usuario_solic){
        List<Notificacion> listNotifica=null;
    String SQL = "select * from obtiene_listanotifi_bloqueusuariosol(?,?);";//Modifica
      listNotifica = (List<Notificacion>) jdbcTemplate.query(SQL, new NotificacionMapper(),                                             
                bloque,
                id_usuario_solic
                );
      
      return listNotifica;
    }
    
     @Override
    public void eliminaNotificacion(Long idNotificacion){
       
    String SQL = "select * from elimina_registro_idnotificacion("+idNotificacion+");";//Modifica
              jdbcTemplate.execute(SQL                                     
                
                              );  
    
    }
    public void eliminaListaNotificaciones(List<Notificacion> listaNotificaciones){
            for(int i=0;i<=listaNotificaciones.size()-1;i++)
            {
                  eliminaNotificacion(listaNotificaciones.get(i).getIdnotificacion());
            
            }
    
    }  
     @Override
    public void eliminaRegistroXBloqueXUsuario(Integer bloque, Long idusuario){
        
        String SQL = "select * from elimina_notificacion_bloqueusuario("+bloque+","+idusuario+");";//Modifica
              jdbcTemplate.execute(SQL   
                              );  
        
    }
     @Override
    public void modificaListaNotificaciones(List<Notificacion> listaNotificaciones){
        eliminaRegistroXBloqueXUsuario(listaNotificaciones.get(0).getBloque(),listaNotificaciones.get(0).getId_usuario_solicitante());
        guardaListaNotificaciones(listaNotificaciones);
        
    }
    
     @Override
    public List<Notificacion> listaNotificacionDatosSM(Long sm){
        List<Notificacion> listNotifica=null;
        String SQL = "select * from lista_notificaciondatossm_sm(?);";//modificado
        listNotifica = (List<Notificacion>) jdbcTemplate.query(SQL, new NotificacionMapper(),                                             
                sm
                );
      
      return listNotifica;
    }
    //listanotificaciondatossr_sr
     @Override
     public List<Notificacion> listaNotificacionDatosSR(Long sr, Long gestion){
        List<Notificacion> listNotifica=null;
        String SQL = "select * from lista_notificaciondatossr_sr(?,?);";//Modificado
        listNotifica = (List<Notificacion>) jdbcTemplate.query(SQL, new NotificacionMapper(),                                             
                sr,gestion
                );
      
      return listNotifica;
    }
     @Override
      public List<Notificacion> listaNotificacionDatosModi(String sigla, Long numero, Long gestion){
        List<Notificacion> listNotifica=null;
        String SQL = "select * from lista_notificaciondatosmodi_modisigla(?,?,?);";//Modificado  a su standart
        listNotifica = (List<Notificacion>) jdbcTemplate.query(SQL, new NotificacionMapper(),                                             
                sigla,numero,gestion
                );
      
      return listNotifica;
    }
     @Override
    public List<Notificacion> listaNotificacionDatosSMNumReg(Long numero_registro, String serie){
        List<Notificacion> listNotifica=null;
        String SQL = "select * from lista_notificaciondatossm_numreg(?,?);";//Modificado
        listNotifica = (List<Notificacion>) jdbcTemplate.query(SQL, new NotificacionMapper(),                                             
                numero_registro,serie
                );
      
      return listNotifica;
    }
     @Override
     public List<Notificacion> listaNotificacionDatosSMNumPubl(Long numero_publicacion){
        List<Notificacion> listNotifica=null;
        String SQL = "select * from lista_notificaciondatossm_numpubl(?);";//Modificado
        listNotifica = (List<Notificacion>) jdbcTemplate.query(SQL, new NotificacionMapper(),                                             
                numero_publicacion
                );
      
      return listNotifica;
    }
     @Override
     public List<Notificacion> listaNotificacionDatosOpo(Long num_opo, Long orden_o){
        List<Notificacion> listNotifica=null;
        String SQL = "select * from lista_notificaciondatosopo_nopo(?,?);";//Modificado
        listNotifica = (List<Notificacion>) jdbcTemplate.query(SQL, new NotificacionMapper(),                                             
                num_opo,orden_o
                );
      
      return listNotifica;
    }
     @Override
     public void recibeEntradaNotificaciones(Long idnotificacion,Long idusuarionotificador) 
     {
           String SQL = "select * from modifica_notificacion_datosbuzon("+idnotificacion+","+idusuarionotificador+");";//Modificado
           jdbcTemplate.execute(SQL);
           
     }
     @Override
       public void recibeListaEntradaNotificaciones(List<NotificacionBuzon> listaNotificaBuzon, Long idusuario){
            for(int i=0;i<=listaNotificaBuzon.size()-1;i++)
            {
                  recibeEntradaNotificaciones(listaNotificaBuzon.get(i).getIdnotificacion(),idusuario);
            
            }
    
    }
    @Override
       public List<Notificacion> listaNotificacionParaNotificar(Long idusuarioSoli, Integer bloque){
          List<Notificacion> listNotifica=null;
          String SQL = "select * from lista_notificacion_idusuariobloque(?,?);";//Modificado
          listNotifica = (List<Notificacion>) jdbcTemplate.query(SQL, new NotificacionMapper(),                                             
                idusuarioSoli,bloque
                );
      
          return listNotifica;
         
       
       
       }
       
    @Override
    public Notificacion modificaNotificacion(Notificacion notificacion)  {
     /*   System.out.println("datos::"+
                notificacion.getIdnotificacion()+"|"+
                notificacion.getIdlogtrans()+"|"+
                notificacion.getBloque()+"|"+
                notificacion.getNro_exped()+"|"+
                 notificacion.getId_usuario_solicitante()+"|"+
                notificacion.getTipo_tramite_notificacion()+"|"+
                notificacion.getExpediente()+"|"+
                notificacion.getGestion()+"|"+
                notificacion.getExtension()+"|"+
                notificacion.getDemandante()+"|"+
                notificacion.getDemandante_cod_estado()+"|"+
                notificacion.getDemandante_fecha_devol()+"|"+
                notificacion.getDemandante_fecha_noti()+"|"+
                notificacion.getDemandante_lugar_notificacion()+"|"+
                notificacion.getDemandante_solic()+"|"+
                notificacion.getDemandante_apod()+"|"+
                notificacion.getDemandante_fojas()+"|"+
                notificacion.getDemandante_con()+"|"+
                notificacion.getDemandante_direc()+"|"+
                notificacion.getDemandante_cel()+"|"+
                notificacion.getDemandante_ciudad_notif()+"|"+
                notificacion.getDemandado()+"|"+
                notificacion.getDemandado_cod_estado()+"|"+
                notificacion.getDemandado_fecha_devol()+"|"+
                notificacion.getDemandado_fecha_noti()+"|"+
                notificacion.getDemandado_lugar_notificacion()+"|"+
                notificacion.getDemandado_solic()+"|"+
                notificacion.getDemandado_apod()+"|"+
                notificacion.getDemandado_fojas()+"|"+
                notificacion.getDemandado_con()+"|"+
                notificacion.getDemandado_direc()+"|"+
                notificacion.getDemandado_cel()+""+
                notificacion.getDemandado_ciudad_notif()+"|"+
                notificacion.getFecha_ingreso()+"|"+
                notificacion.getTipo()+"|"+
                notificacion.getObs()+"|"+
                notificacion.getHistorial()+"|"+
                notificacion.getFecha_recep()+"|"+
                notificacion.getObs_notifi()+"|"+
                notificacion.getId_usuario_notificador()+"|"+
                notificacion.getEstado_marca()+"|"+
                notificacion.getForm_noti_pre()+"|"+
                notificacion.getForm_noti_cuerpo()
                
                );
        */
        
        
        
      String SQL = "select * from modifica_notificacion(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";//modifica
        Notificacion notifica = (Notificacion) jdbcTemplate.queryForObject(SQL, new NotificacionMapper(),                                             
                notificacion.getIdnotificacion(),
                notificacion.getIdlogtrans(),
                notificacion.getBloque(),
                notificacion.getNro_exped(),
                 notificacion.getId_usuario_solicitante(),
                notificacion.getTipo_tramite_notificacion(),
                notificacion.getExpediente(),
                notificacion.getGestion(),
                notificacion.getExtension(),
                notificacion.getDemandante(),
                notificacion.getDemandante_cod_estado(),
                notificacion.getDemandante_fecha_devol(),
                notificacion.getDemandante_fecha_noti(),
                notificacion.getDemandante_lugar_notificacion(),
                notificacion.getDemandante_solic(),
                notificacion.getDemandante_apod(),
                notificacion.getDemandante_fojas(),
                notificacion.getDemandante_con(),
                notificacion.getDemandante_direc(),
                notificacion.getDemandante_cel(),
                notificacion.getDemandante_ciudad_notif(),
                notificacion.getDemandado(),
                notificacion.getDemandado_cod_estado(),
                notificacion.getDemandado_fecha_devol(),
                notificacion.getDemandado_fecha_noti(),
                notificacion.getDemandado_lugar_notificacion(),
                notificacion.getDemandado_solic(),
                notificacion.getDemandado_apod(),
                notificacion.getDemandado_fojas(),
                notificacion.getDemandado_con(),
                notificacion.getDemandado_direc(),
                notificacion.getDemandado_cel(),
                notificacion.getDemandado_ciudad_notif(),
                notificacion.getFecha_ingreso(),
                notificacion.getTipo(),
                notificacion.getObs(),
                notificacion.getHistorial(),
                notificacion.getFecha_recep(),
                notificacion.getObs_notifi(),
                notificacion.getId_usuario_notificador(),
               
                notificacion.getEstado_marca(),
                notificacion.getForm_noti_pre(),
                notificacion.getForm_noti_cuerpo()
     );
        
        
        
        return notifica;
    }
    @Override
    public void recibeHistorialNotificaciones(Long idnotificacion,Long idusuarionotificador,String estado,Date fecha_notifica,Date fecha_devol)
    {     // System.out.println("DATOS::"+idnotificacion+","+idusuarionotificador+","+estado+","+
          //  new java.sql.Timestamp(fecha_notifica.getTime())+","+
          //  new java.sql.Timestamp(fecha_devol.getTime()));
           String fechaNotifica= "";
           String fechaDevolucion= "";
           
           if(fecha_notifica != null)
           {  SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SS");
              fechaNotifica=sdf.format(fecha_notifica);
           }   
           if(fecha_devol != null)
           {  SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SS");
              fechaDevolucion=sdf.format(fecha_devol);
           }   
           
           
       
                     String SQL = "select * from modifica_historial_notifica("+idnotificacion+","+idusuarionotificador+",'"+estado+"','"+
            fechaNotifica
            +"','"+
            fechaDevolucion
            +"');";
             jdbcTemplate.execute(SQL);
           
     }
     @Override
       public void recibeListaHistorialNotificaciones(Notificacion listaNotifica, Long idusuario,Date fecha_notifica,Date fecha_devol){
            
                
                  recibeHistorialNotificaciones(listaNotifica.getIdnotificacion(),
                                                idusuario,
                                                listaNotifica.getDemandante_cod_estado(),
                                                fecha_notifica,
                                                fecha_devol
                                                );
            
            
    
    }
    @Override
    public Integer obtenerUltimoRegistroNotificaSigno(Long id,String descripcion,String estado_marca,String opcion)
    { 
         String SQL = "select * from obtener_ultimo_historial_signo(?,?,?,?);";//Modifica
       Integer es =  jdbcTemplate.queryForObject(SQL, new Object[]{id,descripcion,estado_marca,opcion}, Integer.class);
       return es;
     
    } 
     @Override
    public Integer obtenerVerificaSiEstadoNotifica(Long id)
    { 
         String SQL = "select * from obtener_estado_signo_paranotificacion(?);";//Modifica
       Integer es =  jdbcTemplate.queryForObject(SQL, new Object[]{id}, Integer.class);
       return es;
     
    }
    @Override
     public Integer obtenerEstadoTramiteNotificacion(Integer bloque , Long id_iusuario_solicitante ,String tipo_tramite_notificacion , String expediente )
    { 
         String SQL = "select * from obtener_EstadoTramiteNotificacion(?,?,?,?);";//Modifica
       Integer es =  jdbcTemplate.queryForObject(SQL, new Object[]{bloque,id_iusuario_solicitante,tipo_tramite_notificacion,expediente}, Integer.class);
       return es;
     
    } 
     @Override
     public Integer obtenerTramiteNotificacion(Integer bloque , Long id_iusuario_solicitante ,String tipo_tramite_notificacion , String expediente, Integer gestion )
    { 
         String SQL = "select * from obtener_existetramitenotificacion(?,?,?,?,?);";//Modifica
       Integer es =  jdbcTemplate.queryForObject(SQL, new Object[]{bloque,id_iusuario_solicitante,tipo_tramite_notificacion,expediente,gestion}, Integer.class);
       return es;
     
    } 
}

