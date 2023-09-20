package snp.gob.bo.gimodel.test;

import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import snp.gob.bo.gimodel.servicio.ComunService;
import snp.gob.bo.gimodel.servicio.DominioService;

/**
 *
 * @author Eddy Valero
 * @see ComunService, ComunServiceImpl
 * @version 1.0, 16/09/2016
 */
public class ComunTest {

    public static void main(String[] args) {

        ApplicationContext context = new FileSystemXmlApplicationContext("//home//eddy//NetBeansProjects//borrar//SistemaInterno//giview//src//main//webapp//WEB-INF//applicationContext.xml");
//        ApplicationContext context = new FileSystemXmlApplicationContext("//home//susana//Proyecto_Susan//SistemaInterno//giview//src//main//webapp//WEB-INF//applicationContext.xml");
        DominioService dominioService = (DominioService) context.getBean("dominioService");

        ComunService comunService = (ComunService) context.getBean("comunService");

        try {
            Date fecha = comunService.obtenerFechaServidor(1L);
            System.out.println(" **** " + fecha.toString());
            fecha = comunService.obtenerFechaHoraServidor(1L);
            System.out.println(" **** " + fecha.toString());

        } catch (Exception ex) {
            Logger.getLogger(ComunTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            Long numero = comunService.codificarCodigoSM("10", "2016", "A");
            System.out.println("numero *** " + numero);
            
            HashMap resultado = comunService.obtenerNumeroGestionExtensionNumerico(201600593300L);
            
            System.out.println(" map gestion: " + resultado.get("gestion"));
            System.out.println(" map numero: " + resultado.get("numero"));
            System.out.println(" map extension: " + resultado.get("extension"));

        } catch (Exception ex) {
            Logger.getLogger(ComunTest.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

}
