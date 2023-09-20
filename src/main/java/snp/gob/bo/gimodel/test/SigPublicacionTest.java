/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import snp.gob.bo.gimodel.entidad.SigPublicacion;
import snp.gob.bo.gimodel.servicio.SigPublicacionService;

/**
 *
 * @author susana
 */
public class SigPublicacionTest {

    public static void main(String[] args) {
        ApplicationContext context = new FileSystemXmlApplicationContext("//home//susana//Proyecto_Susan//SistemaInterno//giview//src//main//webapp//WEB-INF//applicationContext.xml");
        SigPublicacionService sigPublicacionService = (SigPublicacionService) context.getBean("sigPublicacionService");

        SigPublicacion publicacion = new SigPublicacion();
        publicacion.setIdpublicacion(null);
        publicacion.setNumero_gaceta(10L);
        publicacion.setFecha_publicacion(new Date());
        publicacion.setMes("mes");
        publicacion.setGestion(2016);
        publicacion.setEstado("AC");

        //  SigPublicacion nuevo = sigPublicacionService.guardar_modificar_listar_SigPublicacion(publicacion, 1);
        //  System.out.println("nuevo   "+nuevo.getIdpublicacion());
//         List<SigPublicacion> listaPublicacion = sigPublicacionService.listaSigPublicacion();
//         for (SigPublicacion listaPublicacion1 : listaPublicacion) {
//             System.out.println("LISTADO  "+listaPublicacion1.getIdpublicacion());
//        }
//        Boolean valor = sigPublicacionService.verificaExistenciaPublicacion_numero(0l);
//        System.out.println("valor   ........    " + valor);

//        SigPublicacion nuevo = sigPublicacionService.listar_SigPublicacion_numero_gaceta(2l);
//        System.out.println("nuevo   " + nuevo);
//        sigPublicacionService.eliminar_SigPublicacion_SigDetallePublicacion(36L);
//        System.out.println("eliminados   .....");
        
        
        System.out.println("NUMERO DE GACETA  "+sigPublicacionService.listar_SigPublicacion_sm(201500627900l));
        
     //    List<Integer> listSeccion = sigPublicacionService.lista_Gaceta_Prepublicacion();
//         for (Integer listSeccion1 : listSeccion) {
//             System.out.println("listSeccion1    "+listSeccion1);
//        }
    }
}
