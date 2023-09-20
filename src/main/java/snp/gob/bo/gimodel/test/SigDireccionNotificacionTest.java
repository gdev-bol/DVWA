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
import snp.gob.bo.gimodel.entidad.SigDireccionDeNotificacion;
import snp.gob.bo.gimodel.servicio.SigDireccionDeNotificacionService;

/**
 *
 * @author Eddy Valero
 * @version 1.0, 27/08/2016
 */
public class SigDireccionNotificacionTest {

    public static void main(String[] args) {

        ApplicationContext context = new FileSystemXmlApplicationContext("//home//eddy//NetBeansProjects//borrar//SistemaInterno//giview//src//main//webapp//WEB-INF//applicationContext.xml");
//        ApplicationContext context = new FileSystemXmlApplicationContext("//home//susana//Proyecto_Susan//SistemaInterno//giview//src//main//webapp//WEB-INF//applicationContext.xml");
        
        SigDireccionDeNotificacionService sigDireccionNotificacionService = (SigDireccionDeNotificacionService) context.getBean("sigDireccionNotificacionService");
        
        
        
        try {
            SigDireccionDeNotificacion sigDireccionNotificacion = sigDireccionNotificacionService.obtenerDireccionNotificacionXSignoMarca(1L);
            System.out.println(" *** " + sigDireccionNotificacion.getIdSignoMarca());
        } catch (Exception ex) {
            Logger.getLogger(SigDireccionNotificacionTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
