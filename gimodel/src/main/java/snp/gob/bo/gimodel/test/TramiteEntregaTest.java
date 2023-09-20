/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.test;

import java.util.ArrayList;
import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import snp.gob.bo.gimodel.entidad.TramiteEntrega;
import snp.gob.bo.gimodel.servicio.EntregaTramiteService;
import snp.gob.bo.gimodel.servicio.TramiteEntregaService;

/**
 *
 * @author luisangel
 */
public class TramiteEntregaTest {
     public static void main(String[] args) throws Exception {
      ApplicationContext context = new FileSystemXmlApplicationContext("//home//ruben//NetBeansProjects//hidra//giview//src//main//webapp//WEB-INF//applicationContext.xml");
      TramiteEntregaService tramiteEntregaService = (TramiteEntregaService) context.getBean("tramiteEntregaService");
      
      EntregaTramiteService entregaramiteService = (EntregaTramiteService) context.getBean("entregaTramiteService");
  
//      TramiteEntrega tramiteentregaobj = new TramiteEntrega();
//      
//      tramiteentregaobj.setIdtramiteentrega(2L);
//      tramiteentregaobj.setIdlogtrans(10L);
//      tramiteentregaobj.setIdprocurador(3L);
//      tramiteentregaobj.setIdtramite(15L);
//      tramiteentregaobj.setTipo_tramite("CAT");
//      tramiteentregaobj.setNumerobloque(8);
//      tramiteentregaobj.setGestionbloque(2017);
//      tramiteentregaobj.setSm(1313L);
//      tramiteentregaobj.setSigla("GO");
//      tramiteentregaobj.setNumerotramite(84);
//      tramiteentregaobj.setGestion(2004);
//      tramiteentregaobj.setExtension("A");
//      tramiteentregaobj.setNumero_registro(50L);
//      tramiteentregaobj.setSerie_registro("C");
//      tramiteentregaobj.setNumero_publicacion(161614L);
//      tramiteentregaobj.setMarca("MERCEDESSSSSSSSSSSSSSS");
//      tramiteentregaobj.setClase_niza(8);
//      tramiteentregaobj.setEstadoanteriortramite("nos");
//      tramiteentregaobj.setUbicacionanterior("tal");
//      tramiteentregaobj.setIdusuario(7L);
//      tramiteentregaobj.setIdregionalentrega(5L);
//      tramiteentregaobj.setFecha_entrega(null);
//      tramiteentregaobj.setEstado("AC");
//      
//tramiteEntregaService.guardar_modificar_listar_TramiteEntrega(tramiteentregaobj, 2);

      List<TramiteEntrega> lista = new ArrayList<>();
      
//      lista = entregaramiteService.tramitesPorBloque(8,2017);
      
         for (TramiteEntrega item : lista) {
             System.out.println(">>>>"+item.getSigla());
         }
      
     
     } 
}
