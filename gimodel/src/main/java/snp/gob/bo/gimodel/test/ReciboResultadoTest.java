/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.test;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import snp.gob.bo.gimodel.entidad.ReciboResultado;
import snp.gob.bo.gimodel.servicio.ReciboResultadoService;

/**
 *
 * @author chanoRojas
 */
public class ReciboResultadoTest {

    public static void main(String[] args) throws Exception {
        ApplicationContext context = new FileSystemXmlApplicationContext("//home//ruben//NetBeansProjects//SistemaInterno//giview//src//main//webapp//WEB-INF//applicationContext.xml");
        ReciboResultadoService reciboResultadoService = (ReciboResultadoService) context.getBean("reciboResultadoService");

//        try {
//            
//            List<Date> lista = reciboResultadoService.listaReciboResultadoMes(11,2016);
//            for (Date item : lista) {
//                System.out.println(item);
//                List<ReciboResultado> list = reciboResultadoService.listaReciboResultadoFecha(item);
//                for (ReciboResultado item2 : list) {
//                    System.out.println(item2.getConcepto());
//                }
//            }
//
//        } catch (Exception ex) {
//            Logger.getLogger(SeccionSubPublicacionTest.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }
}
