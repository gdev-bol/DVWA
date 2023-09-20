package snp.gob.bo.gimodel.test;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import snp.gob.bo.gimodel.entidad.Regional;
import snp.gob.bo.gimodel.entidad.Dominio;
import snp.gob.bo.gimodel.servicio.RegionalService;
import snp.gob.bo.gimodel.servicio.DominioService;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author eddy
 */
public class RegionalTest {

    public static void main(String[] args) throws Exception {

//        ApplicationContext context = new FileSystemXmlApplicationContext("//home//eddy//NetBeansProjects//borrar//SistemaInterno//giview//src//main//webapp//WEB-INF//applicationContext.xml");
        ApplicationContext context = new FileSystemXmlApplicationContext("//home//chano//sipi_v1.0//SistemaInterno//giview//src//main//webapp//WEB-INF//applicationContext.xml");
        RegionalService regionalService = (RegionalService) context.getBean("regionalService");

//        System.out.println("hola ****");
//        try {
//            for (Regional regional : regionalService.obtenerListadoRegional()) {
//                System.out.println(" *** " + regional.getNombre());
//            }
//        } catch (Exception ex) {
//            Logger.getLogger(RegionalTest.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        System.out.println("regional" + regionalService.obtenerRegionalPorIdRegiona(1L).getIdRegional());
        
        System.out.println("regional" + regionalService.encuentraRegionalSede().getIdRegional());

        
        
        
        
    }

}
