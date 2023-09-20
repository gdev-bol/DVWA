package snp.gob.bo.gimodel.solicitudes.test;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import snp.gob.bo.gimodel.solicitudes.entidades.FormularioPI104;
import snp.gob.bo.gimodel.solicitudes.entidades.RenTitularRegistrados;
import snp.gob.bo.gimodel.solicitudes.entidades.Solicitantes;
import snp.gob.bo.gimodel.solicitudes.entidades.SolicitudRenovaciones;
import snp.gob.bo.gimodel.solicitudes.servicio.FormularioPI104Service;

/**
 *
 * @author Eddy Valero
 * @version 1.0, 02/09/2016
 */
public class FormularioPI104Test {

    public static void main(String[] args) {

        ApplicationContext context = new FileSystemXmlApplicationContext("//home//eddy//NetBeansProjects//borrar//SistemaInterno//giview//src//main//webapp//WEB-INF//applicationContext.xml");
        FormularioPI104Service formularioPI104Service = (FormularioPI104Service) context.getBean("formularioPI104Service");

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
            
            FormularioPI104 formularioPI104 = formularioPI104Service.obtenerDatosFormularioPI104(43L);
            
            //RECORRER TODOS SUS SOLICITANTES
//            for (Solicitantes solicitantes : formularioPI104.getSolicitantes()) {
//                System.out.println(" *** "+ solicitantes.getTipoDocumento());
//                System.out.println(" *** "+ solicitantes.getNombreRazonSocial());
//            }
            //recorre su lista de solicitudrenovaciones
//            for (SolicitudRenovaciones solicitudRenovaciones : formularioPI104.getSolicitudRenovaciones()) {
//                System.out.println(" **** " + solicitudRenovaciones.getSignoRegistrado());
//            }
            
            for (RenTitularRegistrados renTitularRegistrado : formularioPI104.getRenTitularRegistrados()) {
                System.out.println(" **** " + renTitularRegistrado.getNombreRazonSocial());
            }
            
            
        } catch (Exception ex) {
            Logger.getLogger(FormularioPI104Test.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
