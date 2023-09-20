package snp.gob.bo.gimodel.solicitudes.test;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import snp.gob.bo.gimodel.solicitudes.entidades.FormularioPI101;
import snp.gob.bo.gimodel.solicitudes.entidades.FormularioPI102;
import snp.gob.bo.gimodel.solicitudes.servicio.FormularioPI101Service;
import snp.gob.bo.gimodel.solicitudes.servicio.FormularioPI102Service;

/**
 *
 * @author Eddy Valero
 * @version 1.0, 02/09/2016
 */
public class FormularioPI102Test {

    public static void main(String[] args) {

        ApplicationContext context = new FileSystemXmlApplicationContext("//home//eddy//NetBeansProjects//borrar//SistemaInterno//giview//src//main//webapp//WEB-INF//applicationContext.xml");
        FormularioPI102Service formularioPI102Service = (FormularioPI102Service) context.getBean("formularioPI102Service");

//        try {
//            HashMap hashMap = formularioPI100Service.obtenerNumeroFormulario("2016082312");
//            System.out.println("hashMap" + hashMap.get("mensaje"));
//            
//        } catch (Exception ex) {
//            Logger.getLogger(FormularioPI100Test.class.getName()).log(Level.SEVERE, null, ex);
//        }

        try {
//            HashMap hashMap = formularioPI100Service.obtenerdatosPI100("201608231");
//            HashMap hashMap = formularioPI100Service.obtenerdatosPI100("201608231");
//            System.out.println(" *** " + hashMap.get("mensaje"));
            
            FormularioPI102 formularioPI102 = formularioPI102Service.obtenerDatosFormularioPI102(2L);
            
            //RECORRER TODOS SUS SOLICITANTES
//            for (Solicitantes solicitantes : formularioPI100.getSolicitantes()) {
//                System.out.println(" *** "+ solicitantes.getTipoDocumento());
//                System.o ut.println(" *** "+ solicitantes.getNombreRazonSocial());
//            }
            
            
            


            
        } catch (Exception ex) {
            Logger.getLogger(FormularioPI102Test.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
