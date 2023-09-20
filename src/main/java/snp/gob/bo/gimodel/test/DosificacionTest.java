 
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import snp.gob.bo.gimodel.entidad.Dosificacion;
import snp.gob.bo.gimodel.entidad.Tasa;
import snp.gob.bo.gimodel.servicio.DosificacionService;
import snp.gob.bo.gimodel.servicio.TasaService;

/**
 *
 * @author chano Rojas
 */
public class DosificacionTest {

    public static void main(String[] args) throws Exception {

//        ApplicationContext context = new FileSystemXmlApplicationContext("//home//eddy//NetBeansProjects//prototipoSpringJSFPrimeFaces//giview//src//main//webapp//WEB-INF//applicationContext.xml");
        ApplicationContext context = new FileSystemXmlApplicationContext("//home//chano//sipi_v1.0//SistemaInterno//giview//src//main//webapp//WEB-INF//applicationContext.xml");
        DosificacionService dosificacionService=(DosificacionService)context.getBean("dosificacionService");
        
        
        
//        Dosificacion dosificacion=new Dosificacion();
//        dosificacion.setIdLogTrans(1L);
//        dosificacion.setGestion(2016);
//        dosificacion.setValorFinal(70);
//        dosificacion.setValorInicio(1);
//        dosificacion.setEstado("AC");
        
//        System.out.println("iddosificacion"+dosificacionService.crudDosificacion(dosificacion, 1));
        
       

        Dosificacion dosificacion=dosificacionService.encuentraDosificacionPorIdDosificacion(2L);
        System.out.println("iddosificacion"+dosificacion.getIdDosificacion());
                dosificacion.setValorFinal(700);
                Dosificacion dosificaMOD=dosificacionService.crudDosificacion(dosificacion,2);
           
            
        System.out.println("iddosificacieewewfwefewfewon"+dosificaMOD.getValorFinal());

    }
}
