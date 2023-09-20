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
import snp.gob.bo.gimodel.entidad.BusquedaMarcaResultado;
import snp.gob.bo.gimodel.entidad.SeccionSubPublicacion;
import snp.gob.bo.gimodel.servicio.BusquedaMarcaResultadoService;
import snp.gob.bo.gimodel.servicio.SeccionSubPublicacionService;

/**
 *
 * @author Eddy Valero
 * @version 1.0, 18/10/2016
 */
public class SeccionSubPublicacionTest {

    public static void main(String[] args) {
       ApplicationContext context = new FileSystemXmlApplicationContext("//home//ruben//NetBeansProjects//SistemaInterno//giview//src//main//webapp//WEB-INF//applicationContext.xml");
        SeccionSubPublicacionService seccionSubPublicacionService = (SeccionSubPublicacionService) context.getBean("seccionSubPublicacionService");
        
        List<String> lista = new ArrayList<String>();
        
        
        try {
            lista = seccionSubPublicacionService.listadoSecciones();
            for (String item : lista) {
                System.out.println(" *** " + item); 
            }
            
            lista = seccionSubPublicacionService.listadoSubSecciones();
            for (String item : lista) {
                System.out.println(" *** " + item); 
            }
        } catch (Exception ex) {
            Logger.getLogger(SeccionSubPublicacionTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        
    }
}
