/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import snp.gob.bo.gimodel.entidad.HistorialResultado;
import snp.gob.bo.gimodel.servicio.HistorialResultadoService;

/**
 *
 * @author Eddy Valero
 * @see HistorialResultadoService
 * @see HistorialResultadoServiceImpl
 * @version 1.0, 05/06/2016
 */
public class HistorialResultadoTest {

    public static void main(String[] args) throws Exception {

        //ApplicationContext context = new FileSystemXmlApplicationContext("//home//eddy//NetBeansProjects//borrar//SistemaInterno//giview//src//main//webapp//WEB-INF//applicationContext.xml");
        ApplicationContext context = new FileSystemXmlApplicationContext("//home//susana//Proyecto_Susan//SistemaInterno//giview//src//main//webapp//WEB-INF//applicationContext.xml");
        HistorialResultadoService historialResultadoService = (HistorialResultadoService) context.getBean("historialResultadoService");

//        for (HistorialResultado historial : historialResultadoService.obtenerListaHistorialCompletoXId(152061L, "SIG")) {
//            System.out.println(historial.getPosicion() + " *** " + historial.getIdHistorial());
//        }
//        for (HistorialResultado historial : historialResultadoService.obtenerListaHistorialCompletoXId(1L, "MOD")) {
//            System.out.println(" *** " + historial.getIdHistorial() + " *** " + historial.getObservacion());
//        }
        for (HistorialResultado historial : historialResultadoService.obtenerListaHistorialCompletoXId(25600L, "MOD")) {
            System.out.println(" *** " + historial.getIdHistorial() + " *** " + historial.getDescripcion());
        }
        
        
        

    }
}
