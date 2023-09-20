///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//
//package snp.gob.bo.gimodel.test;
//
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.FileSystemXmlApplicationContext;
//import snp.gob.bo.gimodel.entidad.ObservacionTramite;
//import snp.gob.bo.gimodel.entidad.SigLogoTipo;
//import snp.gob.bo.gimodel.servicio.ObservacionTramiteService;
//import snp.gob.bo.gimodel.servicio.SigLogoTipoService;
//
///**
// *
// * @author Eddy Valero
// * @version 1.0, 
// */
//public class SigLogoTipoTest {
//    
//    public static void main(String[] args){
//        
//        ApplicationContext context = new FileSystemXmlApplicationContext("//home//eddy//NetBeansProjects//borrar//SistemaInterno//giview//src//main//webapp//WEB-INF//applicationContext.xml");
//        
//        SigLogoTipoService sigLogoTipoService = (SigLogoTipoService) context.getBean("sigLogoTipoService");
//        
//        
//        try {
//            for (SigLogoTipo sigLogoTipo : sigLogoTipoService.obtenerSigLogoTipoXIdSignoMarca(1L)) {
//                System.out.println(" *** " + sigLogoTipo.getIdLogoTipo());
//            }
//        } catch (Exception ex) {
//            Logger.getLogger(SigLogoTipoTest.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//        System.out.println("hola *** ");
//
//
//
//        
//        
//        
//    }
//    
//}
