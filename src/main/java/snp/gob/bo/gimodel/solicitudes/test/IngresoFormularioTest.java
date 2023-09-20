package snp.gob.bo.gimodel.solicitudes.test;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import snp.gob.bo.gimodel.solicitudes.entidades.FormularioPI100;
import snp.gob.bo.gimodel.solicitudes.servicio.IngresoFormularioService;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author eddy
 */
public class IngresoFormularioTest {

    public static void main(String[] args) {

        ApplicationContext context = new FileSystemXmlApplicationContext("//home//eddy//NetBeansProjects//borrar//SistemaInterno//giview//src//main//webapp//WEB-INF//applicationContext.xml");
        
        IngresoFormularioService ingresoFormularioService = (IngresoFormularioService) context.getBean("ingresoFormularioService");
        
//        FormularioPI100 formularioPI100= new FormularioPI100();
        
        
        
        
        try {
//            ingresoFormularioService.guardarFormularioPI100(formularioPI100);
            System.out.println(" *** " + ingresoFormularioService.verificarExistenciaRegistroMarcaXSM(201601000001L));
            System.out.println(" *** " + ingresoFormularioService.verificarExistenciaRegistroModificacion("CN", 10017L, 2017L));
            System.out.println(" *** " + ingresoFormularioService.verificarExistenciaRegistroRenovacion(179L, 1997L));
            
        } catch (Exception ex) {
            Logger.getLogger(IngresoFormularioTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        

    }

}
