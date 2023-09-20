/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package snp.gob.bo.gimodel.test;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import snp.gob.bo.gimodel.entidad.SigSignoClaseNiza;
import snp.gob.bo.gimodel.entidad.SigSignoMarca;
import snp.gob.bo.gimodel.servicio.SigSignoClaseNizaService;
import snp.gob.bo.gimodel.servicio.SigSignoMarcaService;

/**
 *
 * @author Eddy Valero
 * @version 1.0, 27/08/2016
 */
public class SigSignoClaseNizaTest {
    
    public static void main(String[] args) {

        ApplicationContext context = new FileSystemXmlApplicationContext("//home//eddy//NetBeansProjects//borrar//SistemaInterno//giview//src//main//webapp//WEB-INF//applicationContext.xml");
//        ApplicationContext context = new FileSystemXmlApplicationContext("//home//susana//Proyecto_Susan//SistemaInterno//giview//src//main//webapp//WEB-INF//applicationContext.xml");
        SigSignoClaseNizaService sigSignoClaseNizaService = (SigSignoClaseNizaService) context.getBean("sigSignoClaseNizaService");
        
        SigSignoClaseNiza sigSignoClaseNiza = new SigSignoClaseNiza();
        
        sigSignoClaseNiza.setIdSignoMarca(2L);
        sigSignoClaseNiza.setIdClaseNiza(1L);
        sigSignoClaseNiza.setIdLogTrans(1L);
        sigSignoClaseNiza.setNumeroClaseNiza(2L);
        sigSignoClaseNiza.setListaProducto("Lista de productos x");
        sigSignoClaseNiza.setEstado("AC");

//        try {
//            sigSignoClaseNizaService.registrarSigSignoMarca(sigSignoClaseNiza);
//        } catch (Exception ex) {
//            Logger.getLogger(SigSignoClaseNizaTest.class.getName()).log(Level.SEVERE, null, ex);
//        }
        
        try {
            for (SigSignoClaseNiza sigSignoClaseNiza1 : sigSignoClaseNizaService.obtenerListaSigSignoClazeNizaXIdSignoMarca(1L)) {
                System.out.println(" *** " + sigSignoClaseNiza1.getIdClaseNiza());
            }
            
        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
        }
        
//        System.out.println("exito" + sigSignoClaseNiza.getListaProducto());
        
    }
    
}
