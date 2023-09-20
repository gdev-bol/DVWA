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
import snp.gob.bo.gimodel.entidad.SigPrioridadPreferencia;
import snp.gob.bo.gimodel.entidad.SigSignoClaseNiza;
import snp.gob.bo.gimodel.servicio.SigPrioridadPreferenciaService;
import snp.gob.bo.gimodel.servicio.SigSignoClaseNizaService;

/**
 *
 * @author Eddy Valero
 * @version 1.0, 27/08/2016
 */
public class SigPrioridadPreferenciaTest {

    public static void main(String[] args) {

        ApplicationContext context = new FileSystemXmlApplicationContext("//home//eddy//NetBeansProjects//borrar//SistemaInterno//giview//src//main//webapp//WEB-INF//applicationContext.xml");
//        ApplicationContext context = new FileSystemXmlApplicationContext("//home//susana//Proyecto_Susan//SistemaInterno//giview//src//main//webapp//WEB-INF//applicationContext.xml");
        SigPrioridadPreferenciaService sigPrioridadPreferenciaService = (SigPrioridadPreferenciaService) context.getBean("sigPrioridadPreferencia");

        SigPrioridadPreferencia extranjera = new SigPrioridadPreferencia();
        SigPrioridadPreferencia exposicion = new SigPrioridadPreferencia();
        SigPrioridadPreferencia oposicionAndina = new SigPrioridadPreferencia();
        
        
        try {
            extranjera = sigPrioridadPreferenciaService.obtenerPrioridadExtranjera(1L);
            exposicion = sigPrioridadPreferenciaService.obtenerPrioridadExposicion(1L);
            oposicionAndina = sigPrioridadPreferenciaService.obtenerOposicionAndina(1L);
            
            System.out.println("extranjera " + extranjera.getIdPrioridadPreferencia());
            System.out.println("exposicion " + exposicion.getIdPrioridadPreferencia());
            System.out.println("oposicionAndina " + oposicionAndina.getIdPrioridadPreferencia());
        } catch (Exception ex) {
            Logger.getLogger(SigPrioridadPreferenciaTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
