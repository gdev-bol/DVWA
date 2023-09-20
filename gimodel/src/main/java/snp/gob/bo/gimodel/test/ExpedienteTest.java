package snp.gob.bo.gimodel.test;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import snp.gob.bo.gimodel.entidad.Dominio;
import snp.gob.bo.gimodel.entidad.SigSignoMarca;
import snp.gob.bo.gimodel.servicio.DominioService;
import snp.gob.bo.gimodel.servicio.ExpedienteService;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author eddy
 */
public class ExpedienteTest {

    public static void main(String[] args) {

        ApplicationContext context = new FileSystemXmlApplicationContext("//home//eddy//NetBeansProjects//borrar//SistemaInterno//giview//src//main//webapp//WEB-INF//applicationContext.xml");

        ExpedienteService expedienteService = (ExpedienteService) context.getBean("expedienteService");

        try {
//            SigSignoMarca sigSignoMarca =  expedienteService.obtenerSigsignomarca("10007", "2016", "");
//            System.out.println("sigSignoMarca " + sigSignoMarca.getIdSignoMarca());
//            System.out.println(" numeroRegistro " + sigSignoMarca.getNumeroRegistro());
//            SigSignoMarca sigSignoMarca =  expedienteService.obtenerSiguienteRegistroSigSignoMarca(100500L, 2016L, 0L);
//            SigSignoMarca sigSignoMarca = expedienteService.obtenerSiguienteRegistroSigSignoMarca(new Integer(1), new Integer(2016), new Integer(0));
//            SigSignoMarca sigSignoMarca = expedienteService.obtenerSiguienteRegistroSigSignoMarca(new Integer(1034), new Integer(1917), new Integer(0));
//            SigSignoMarca sigSignoMarca = expedienteService.obtenerAnteriorRegistroSigSignoMarca(new Integer(953), new Integer(1917), new Integer(0));
//            SigSignoMarca sigSignoMarca = expedienteService.obtenerAnteriorRegistroSigSignoMarca(new Integer(1), new Integer(2016), new Integer(0));
//            System.out.println("Sigsignomarca:" + sigSignoMarca.getIdSignoMarca());
            
            
            // Pruebas obtener el anterior numero publicacion
//            SigSignoMarca sigSignoMarca = expedienteService.obtenerAnteriorRegistroNumeroPublicacionSigSignoMarca(43319L, 76744L);
//            System.out.println(" anteriorPublicacion : " + sigSignoMarca.getIdSignoMarca());
            
            
            // Pruebas obtener el siguiente numero publicacion
//            SigSignoMarca sigSignoMarca = expedienteService.obtenerSiguienteRegistroNumeroPublicacionSigSignoMarca(42945L, 76744L);
//            System.out.println(" siguientePublicacion : " + sigSignoMarca.getIdSignoMarca());
            
            // Pruebas obtener el anterior numero registro
//            SigSignoMarca sigSignoMarca = expedienteService.obtenerAnteriorRegistroNumeroRegistroSigSignoMarca(52715L, 79885L, "C");
//            SigSignoMarca sigSignoMarca = expedienteService.obtenerAnteriorRegistroNumeroRegistroSigSignoMarca(52625L, 79885L, "C");
//            System.out.println(" anteriorPublicacion : " + sigSignoMarca.getIdSignoMarca());
            
            // Pruebas obtener el anterior numero registro
//            SigSignoMarca sigSignoMarca = expedienteService.obtenerSiguienteRegistroSigSignoMarca(53245L, 79886L, "C");
            SigSignoMarca sigSignoMarca = expedienteService.obtenerSiguienteRegistroNumeroRegistroSigSignoMarca(52715L, 79885L, "C");
            
            System.out.println(" siguientePublicacion : " + sigSignoMarca.getIdSignoMarca());
            

        } catch (Exception ex) {
            Logger.getLogger(ExpedienteTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
