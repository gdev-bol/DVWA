 
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.test;

import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import snp.gob.bo.gimodel.entidad.Dosificacion;
import snp.gob.bo.gimodel.entidad.DosificacionTasa;
import snp.gob.bo.gimodel.entidad.Tasa;
import snp.gob.bo.gimodel.servicio.DosificacionService;
import snp.gob.bo.gimodel.servicio.DosificacionTasaService;
import snp.gob.bo.gimodel.servicio.RegionalService;
import snp.gob.bo.gimodel.servicio.TasaService;

/**
 *
 * @author chano Rojas
 */
public class DosificacionTasaTest {

    public static void main(String[] args) throws Exception {

//        ApplicationContext context = new FileSystemXmlApplicationContext("//home//eddy//NetBeansProjects//prototipoSpringJSFPrimeFaces//giview//src//main//webapp//WEB-INF//applicationContext.xml");
        ApplicationContext context = new FileSystemXmlApplicationContext("//home//chano//sipi_v1.0//SistemaInterno//giview//src//main//webapp//WEB-INF//applicationContext.xml");
        DosificacionTasaService dosificacionTasaService=(DosificacionTasaService)context.getBean("dosificacionTasaService");
        TasaService tasaService=(TasaService)context.getBean("tasaService");
        RegionalService regionalService=(RegionalService)context.getBean("regionalService");
        
       
       
       
        List<DosificacionTasa> lstdosificacion=dosificacionTasaService.encuentraListaDosificacionTasaPorTasa(tasaService.obtenerTasaPorIdTasa(1L),regionalService.obtenerRegionalPorIdRegiona(1L));
        for (DosificacionTasa dosificacionTasa : lstdosificacion) {
            System.out.println("hfdjksfjksd"+dosificacionTasa.getTipoRecibo());
        }
        

    }
}
