/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.test;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import snp.gob.bo.gimodel.entidad.ElementoFormularioTramite;
import snp.gob.bo.gimodel.servicio.ElementoFormularioTramiteService;

/**
 *
 * @author Eddy Valero
 * @see ElementoFormularioTramite
 * @see ElementoFormularioTramiteImpl
 * @version 1.0, 06/09/2016
 */
public class ElementoFormularioTramiteTest {

    public static void main(String[] args) {

        ApplicationContext context = new FileSystemXmlApplicationContext("//home//eddy//NetBeansProjects//borrar//SistemaInterno//giview//src//main//webapp//WEB-INF//applicationContext.xml");
        
        ElementoFormularioTramiteService elementoFormularioTramiteService = (ElementoFormularioTramiteService) context.getBean("elementoFormularioTramiteService");
        
        try {
            List<ElementoFormularioTramite> listElementoFormularioTramite = elementoFormularioTramiteService.obtPlantillaVentanilla("SM");
            for (ElementoFormularioTramite elementoFormularioTramite : listElementoFormularioTramite) {
                System.out.println(" *** " + elementoFormularioTramite.getNombreElemento());
            }
        } catch (Exception ex) {
            Logger.getLogger(ElementoFormularioTramiteTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

}
