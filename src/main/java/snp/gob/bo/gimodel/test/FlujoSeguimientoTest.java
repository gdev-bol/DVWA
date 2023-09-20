/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.test;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import snp.gob.bo.gimodel.entidad.FlujoSeguimiento;
import snp.gob.bo.gimodel.servicio.FlujoSeguimientoService;

/**
 *
 * @author levi
 */
public class FlujoSeguimientoTest {

    public static void main(String[] args) {

//      ApplicationContext context = new FileSystemXmlApplicationContext("//home//ruben//NetBeansProjects//SistemaInterno//giview//src//main//webapp//WEB-INF//applicationContext.xml");
        ApplicationContext context = new FileSystemXmlApplicationContext("//home//susana/Proyecto_Susan//hidra//giview//src//main//webapp//WEB-INF//applicationContext.xml");
        FlujoSeguimientoService flujoSeguimientoService = (FlujoSeguimientoService) context.getBean("flujoSeguimientoService");

        List<FlujoSeguimientoService> list = new ArrayList<FlujoSeguimientoService>();
        List<FlujoSeguimiento> flujoSeguimientoSig = null;
        try {
            /* try {
             System.out.println("la etapa siguinete::"+flujo.verificaSiguienteEtapa(3).get(0).getEtapa());
             } catch (Exception ex) {
             Logger.getLogger(FlujoSeguimientoTest.class.getName()).log(Level.SEVERE, null, ex);
             }
             */
//             flujoSeguimientoSig=flujo.sigueFlujoSeguimiento("DESG");
//             System.out.println("flujoSeguimientoSig::"+flujoSeguimientoSig.get(0).getEtapa());

            Boolean[] RecepcionarFinalizar = new Boolean[2];
            RecepcionarFinalizar = flujoSeguimientoService.configuracionBotonesRecepcionarFinalizar(20l,"");
            
        } catch (Exception ex) {
            Logger.getLogger(FlujoSeguimientoTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
