/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.test;

import java.util.ArrayList;
import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import snp.gob.bo.gimodel.entidad.ClaseNiza;
import snp.gob.bo.gimodel.servicio.ClaseNizaService;

/**
 *
 * @author Eddy Valero
 * @version 1.0, 27/08/2016
 */
public class ClaseNizaTest {

    public static void main(String[] args) throws Exception {

        ApplicationContext context = new FileSystemXmlApplicationContext("//home//sushy//Proyecto_Susan//hidra//giview//src//main//webapp//WEB-INF//applicationContext.xml");
                                                                           

        ClaseNizaService claseNizaService = (ClaseNizaService) context.getBean("claseNizaService");
        ClaseNiza claseNiza = new ClaseNiza();

        //        try {
//            claseNiza = claseNizaService.obtenerRegistroClaseNiza(2L);
//            System.out.println(" *** " + claseNiza.getNumeroClaseNiza());
//        } catch (Exception ex) {
//            Logger.getLogger(ClaseNizaTest.class.getName()).log(Level.SEVERE, null, ex);
//        }
        //Listado de Clase Niza
//        try {
//            List<ClaseNiza> listaClaseNiza = claseNizaService.obtenerListadoClaseNiza();
//            for (ClaseNiza claseNiza1 : listaClaseNiza) {
//                System.out.println(" *** " + claseNiza1.getNumeroClaseNiza());
//            }
//        } catch (Exception ex) {
//            Logger.getLogger(ClaseNizaTest.class.getName()).log(Level.SEVERE, null, ex);
//        }
        //variables de entrada
//        int n = 11;
//        int g = 2013;
//
//        //variables de la base
//        int num = 10;
//        int gestion = 2013;
//
//        //variable general
//        int swgestion = 0;
//
//        //prueba de metodo
//        System.out.println("hola");
//
//        
//        //pregunta principal si existe o no registro
//        if (num > n
//                && g == gestion) {
//            
//            //retornar el signomarca encontrado
//            
//            System.out.println("numero *** " + num);
//            System.out.println("gestion *** " + gestion);
//            
//            
//            
//        } else {
//            System.out.println(" no logrado");
//
//            int sw = 0;
//
//            do {
//                g++;
//
//                num = 1;
//                
//                //preguntar si existe el minimo de la siguiente gestion
//                if (num == 1
//                        && g == gestion) {
//                    sw = 1;
//                } else {
//                    
//                    //preguntar si la gestion es valida o no
//                    if (g > gestion) {
//                        sw = 1;
//                        swgestion = 1;
//                    } else {
//                        sw = 0;
//                    }
//                }
//            } while (sw == 1);
//
//            if (swgestion == 1) {
//                
//                //retornar no existe registro
//                System.out.println("no existe registro");
//                
//                
//            } else {
//                System.out.println("numero *** " + num);
//                System.out.println("gestion *** " + gestion);
//            }
//
//        }
//
       // ClaseNiza li = claseNizaService.listarClaseNiza_id(46L);

     //   System.out.println("lista" + li.getIdClaseNiza() + "  " + li.getProteccion());

        List<ClaseNiza> lstClaseNiza = new ArrayList<ClaseNiza>();
        lstClaseNiza = claseNizaService.obtenerListadoClaseNizaVersion("11");
        for (ClaseNiza lstClaseNiza1 : lstClaseNiza) {
            System.out.println("lstClaseNiza1 " + lstClaseNiza1.getNumeroClaseNiza());
        }
    }

}
