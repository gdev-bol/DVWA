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
//import snp.gob.bo.gimodel.entidad.SigImagen;
//import snp.gob.bo.gimodel.entidad.SigLogoTipo;
//import snp.gob.bo.gimodel.servicio.SigImagenService;
//import snp.gob.bo.gimodel.servicio.SigLogoTipoService;
//
///**
// *
// * @author Eddy Valero
// * @version 1.0, 
// */
//public class SigImagenTest {
//    
//    public static void main(String[] args){
//        
//        ApplicationContext context = new FileSystemXmlApplicationContext("//home//eddy//NetBeansProjects//borrar//SistemaInterno//giview//src//main//webapp//WEB-INF//applicationContext.xml");
//        
//        SigImagenService sigImagenService = (SigImagenService) context.getBean("sigImagenService");
//        
//        try {
//            //obtener un conjunto
//            for (SigImagen sigImagen : sigImagenService.obtenerListaSigImagenXIdSigLogoTipo(1L)) {
//                System.out.println(" *** " + sigImagen.getIdImagen());
//            }
//            
//            //obtener una imagen
//            SigImagen sigImagen = sigImagenService.obtenerSigImagenXIdSigLogoTipo(1L);
//            System.out.println(" *****sigImagen ****  " + sigImagen.getIdImagen());
//            
//        } catch (Exception ex) {
//            Logger.getLogger(SigImagenTest.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//        
//        
//        
//        
//
//
//
//        
//        
//        
//    }
//    
//}
