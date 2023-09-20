/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.test;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import snp.gob.bo.gimodel.entidad.BusquedaMarcaResultado;
import snp.gob.bo.gimodel.servicio.BusquedaMarcaResultadoService;

/**
 *
 * @author Eddy Valero
 * @version 1.0, 18/10/2016
 */
public class BusquedaMarcaResultadoTest {

    public static void main(String[] args) {
//        ApplicationContext context = new FileSystemXmlApplicationContext("//home//eddy//NetBeansProjects//borrar//SistemaInterno//giview//src//main//webapp//WEB-INF//applicationContext.xml");
        ApplicationContext context = new FileSystemXmlApplicationContext("//home//eddy//NetBeansProjects//proyectoHidra//SistemaInterno//giview//src//main//webapp//WEB-INF//applicationContext.xml");
        BusquedaMarcaResultadoService busquedaMarcaResultadoService = (BusquedaMarcaResultadoService) context.getBean("busquedaMarcaResultadoService");
        
        
//        try {
//            
//            for (BusquedaMarcaResultado busquedaMarcaResultado : busquedaMarcaResultadoService.realizarBusquedaPorCriterios("APOD", "%SORUCO%", 5)) {
////                System.out.println(" *** " + busquedaMarcaResultado.getIdSignoMarca());
//                System.out.println(" *** " + busquedaMarcaResultado.getMarca());
//            }
//        } catch (Exception ex) {
//            Logger.getLogger(BusquedaMarcaResultadoTest.class.getName()).log(Level.SEVERE, null, ex);
//        }

//        try {
//            
//            for (BusquedaMarcaResultado busquedaMarcaResultado : busquedaMarcaResultadoService.realizarBusquedaMarca("pan", 5)) {
////                System.out.println(" *** " + busquedaMarcaResultado.getIdSignoMarca());
//                System.out.println(" *** " + busquedaMarcaResultado.getMarca());
//            }
//        } catch (Exception ex) {
//            Logger.getLogger(BusquedaMarcaResultadoTest.class.getName()).log(Level.SEVERE, null, ex);
//        }

//        System.out.println("hola");

        
    }
}
