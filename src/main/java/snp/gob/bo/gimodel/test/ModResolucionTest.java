/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.test;

import java.util.Date;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import snp.gob.bo.gimodel.entidad.ModResolucion;
import snp.gob.bo.gimodel.servicio.ModResolucionService;

/**
 *
 * @author susana
 */
public class ModResolucionTest {

    public static void main(String[] args) {
        ApplicationContext context = new FileSystemXmlApplicationContext("//home//susana//Proyecto_Susan//SistemaInterno//giview//src//main//webapp//WEB-INF//applicationContext.xml");
        ModResolucionService modResolucionService = (ModResolucionService) context.getBean("modResolucionService");

//        ModResolucion modResolucion = new ModResolucion();
//
//        modResolucion.setIdresolucion(null);
//        modResolucion.setIdmodificacion(4l);
//        modResolucion.setNumero_resolucion(545);
//        modResolucion.setGestion_resolucion(4666);
//        modResolucion.setFecha_resolucion(new Date());
//
//        modResolucion.setObservacion_resolucion("sdlkfjsdhfsd");
//        modResolucion.setDocumento_resolucion("skjdfhjksdhfjkdshfjkds");
//        modResolucion.setEstado("AC");

//     ModResolucion resolucion2 = modResolucionService.guardar_modificar_listar_ModResolucion(modResolucion, 1);
//         System.out.println("----------------     "+resolucion2.getIdresolucion());
//     }
//        ModResolucion resolucion3 = modResolucionService.buscar_ModResolucionXidmodificacion(7l);
//        System.out.println("ENCONTRARO " + resolucion3);
      //  System.out.println("VALIDADOR  "+modResolucionService.verificaExisteModResolucion("CAFU", 177, 2016));
        
        
        ModResolucion resolucionpendiente =  modResolucionService.buscar_ModResolucionXidmodificacionPendiente(25596l);
        System.out.println("resolucionpendiente     "+resolucionpendiente);
        
    }

}
