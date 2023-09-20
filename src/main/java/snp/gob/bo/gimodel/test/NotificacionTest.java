/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.test;

import java.util.Date;
import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import snp.gob.bo.gimodel.entidad.Notificacion;
import snp.gob.bo.gimodel.servicio.NotificacionService;

/**
 *
 * @author levi
 */
public class NotificacionTest {
     public static void main(String[] args) throws Exception {
      ApplicationContext context = new FileSystemXmlApplicationContext("//home//levi//Documentos//PROYECTOS//SISInterno//SistemaInterno//giview//src//main//webapp//WEB-INF//applicationContext.xml");
     
       NotificacionService servicio = (NotificacionService) context.getBean("notificacionService");
        Notificacion noti= new Notificacion();
        noti.setBloque(2);
        noti.setNro_exped(5);
        noti.setDemandante_fecha_devol(new Date());
        noti.setId_usuario_solicitante(1L);
        /*
        
        Notificacion notificacion=  servicio.guardaNotificacion(noti);
         System.out.println("guardado y su id es:: "+notificacion);*/
       
       
         
         /*List<Notificacion> list=servicio.getNotificacionXbloqueXusuariosol(8,1L);
         System.out.println("tam lista"+list.size());*/
       
         //servicio.eliminaNotificacion(53L);
     }
}
