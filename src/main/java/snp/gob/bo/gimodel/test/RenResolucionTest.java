/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.test;

import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import snp.gob.bo.gimodel.entidad.RenResolucion;
import snp.gob.bo.gimodel.servicio.RenRenovacionService;
import snp.gob.bo.gimodel.servicio.RenResolucionService;
import snp.gob.bo.gimodel.servicio.UsuarioPaginaService;

/**
 *
 * @author levi
 */
public class RenResolucionTest {

    public static void main(String[] args) throws Exception {
//      ApplicationContext context = new FileSystemXmlApplicationContext("//home//levi//Documentos//PROYECTOS//SISInterno//SistemaInterno//giview//src//main//webapp//WEB-INF//applicationContext.xml");
        ApplicationContext context = new FileSystemXmlApplicationContext("//home//chano//sipi_v1.0//SistemaInterno//giview//src//main//webapp//WEB-INF//applicationContext.xml");

        UsuarioPaginaService servicio = (UsuarioPaginaService) context.getBean("usuarioPaginaService");
        RenRenovacionService renRenovacionService = (RenRenovacionService) context.getBean("renRenovacionService");
        RenResolucionService renResolucionService = (RenResolucionService) context.getBean("renResolucionService");

//        RenResolucion renResolucion = new RenResolucion();
//        int parametro = 1;
//        renResolucion.setIdresolucion(Long.MIN_VALUE);
//        
//        
        System.out.println("idrenovacion"+renResolucionService.obtieneRenResolucionPorIdRenovacion(1L).getFecha_resolucion());
        System.out.println("idrenovacion"+renResolucionService.obtieneRenResolucionPorIdRenovacion(1L).getIdrenovacion());
        
        
        
        
        
        
        
        
        
        
        
              
        

//        List<RenResolucion> listaRen = renResolucionService.obtieneListadoRenResolucion(renResolucion, parametro);
//        for (RenResolucion renRenovacion1 : listaRen) {
//            if (!listaRen.isEmpty()) {
//                System.out.println("el listado tiene como valor de id" + renRenovacion1.getDocumento_resolucion());
//                System.out.println("orden de renovacion" + renRenovacion1.getObservacion_resolucion());
//            }else{
//                System.out.println("lista vacia");
//            }
//        }

    }
}
