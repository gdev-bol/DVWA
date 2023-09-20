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
import snp.gob.bo.gimodel.entidad.DatoElementoFormulario;
import snp.gob.bo.gimodel.servicio.DatoElementoFormularioService;

/**
 *
 * @author Eddy Valero
 * @see DatoElementoFormulario
 * @see DatoElementoFormularioServiceImpl
 * @version 1.0, 05/09/2016
 */
public class DatoElementoFormularioTest {

    public static void main(String[] args) {

        ApplicationContext context = new FileSystemXmlApplicationContext("//home//eddy//NetBeansProjects//borrar//SistemaInterno//giview//src//main//webapp//WEB-INF//applicationContext.xml");
        
        DatoElementoFormularioService datoElementoFormularioService = (DatoElementoFormularioService) context.getBean("datoElementoFormularioService");
        
//        try {
//            int resultado = datoElementoFormularioService.generarRegistrosPlantillaVentanilla(1L, 1L);
//            
//            
//            System.out.println("int ***** " + resultado);
//        } catch (Exception ex) {
//            Logger.getLogger(DatoElementoFormularioTest.class.getName()).log(Level.SEVERE, null, ex);
//        }
             
        try {
            List<DatoElementoFormulario> lista = datoElementoFormularioService.obtplantillaventanillatramiteingresado("201608314", "PI101");
            
            for (DatoElementoFormulario sMDatoElementoFormulario : lista) {
                System.out.println(" *** " + sMDatoElementoFormulario.getIdDatoElementoFormulario());
            }

            
        } catch (Exception ex) {
            Logger.getLogger(DatoElementoFormularioTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

}
