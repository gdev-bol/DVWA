/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import snp.gob.bo.gimodel.entidad.Procurador;
import snp.gob.bo.gimodel.servicio.ProcuradorService;

/**
 *
 * @author luisangel
 */
public class ProcuradorTest {
     public static void main(String[] args) throws Exception {
      
    ApplicationContext context = new FileSystemXmlApplicationContext("//home//luisangel//HIDRA//hidra//giview//src//main//webapp//WEB-INF//applicationContext.xml");
 
    ProcuradorService procuradorService = (ProcuradorService) context.getBean("procuradorService");
      
   
  
        Procurador procuradorobj = procuradorService.procuradorPorId(1L);
         System.out.println(procuradorobj.getNombre_razonsocial());
        
        procuradorobj.setIdprocurador(1L);
        procuradorobj.setIdlogtrans(10L);
        procuradorobj.setNombre_razonsocial("Luis Angel");
        procuradorobj.setPrimer_apellido("Quispe");
        procuradorobj.setSegundo_apellido("Limachi");
        procuradorobj.setTipo_titular("TIT");
        procuradorobj.setNumero_documento("2017028594");
        procuradorobj.setTipo_documento("CAT");
        procuradorobj.setLugar_expedicion("LPZ");
        procuradorobj.setTelefono("2845741");
        procuradorobj.setCelular("71845741");
        procuradorobj.setTipodocumentoautorizacion("OTR");
        procuradorobj.setDescripciondocumentoautorizacion("Descripcion");
        procuradorobj.setEstado("AC");
        
        procuradorobj=procuradorService.guardar_modificar_listar_Procurador(procuradorobj, 1);
  
     
     
     }    
}
