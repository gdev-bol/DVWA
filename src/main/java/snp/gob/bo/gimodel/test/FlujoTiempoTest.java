/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import snp.gob.bo.gimodel.entidad.FlujoTiempo;
import snp.gob.bo.gimodel.servicio.FlujoSeguimientoService;
import snp.gob.bo.gimodel.servicio.FlujoTiempoService;

/**
 *
 * @author levi
 */
public class FlujoTiempoTest {
     public static void main(String[] args) throws Exception {

//      ApplicationContext context = new FileSystemXmlApplicationContext("//home//ruben//NetBeansProjects//SistemaInterno//giview//src//main//webapp//WEB-INF//applicationContext.xml");
        ApplicationContext context = new FileSystemXmlApplicationContext("//home//levi//Documentos//PROYECTOS//ProyectoGIT//hidra//giview//src//main//webapp//WEB-INF//applicationContext.xml");
        FlujoTiempoService flujo = (FlujoTiempoService) context.getBean("flujoTiempoService");
      
        System.out.println("tiempo::"+flujo.obtieneFlujoXIdetapa("EXFM", 1));
        
     
     
     
     }
}

