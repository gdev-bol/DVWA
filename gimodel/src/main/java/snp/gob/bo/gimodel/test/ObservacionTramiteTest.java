/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.test;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import snp.gob.bo.gimodel.entidad.ObservacionTramite;
import snp.gob.bo.gimodel.entidad.SigObservacionTramite;
import snp.gob.bo.gimodel.enums.EnumPrefijoTablas;
import snp.gob.bo.gimodel.servicio.ObservacionTramiteService;

/**
 *
 * @author Eddy Valero
 * @version 1.0,
 */
public class ObservacionTramiteTest {
    
    public static void main(String[] args) {

        ApplicationContext context = new FileSystemXmlApplicationContext("//home//luis_angel//NetBeansProjects//HIDRA//hidra//giview//src//main//webapp//WEB-INF//applicationContext.xml");
//        ApplicationContext context = new FileSystemXmlApplicationContext("//home//susana//Proyecto_Susan//SistemaInterno//giview//src//main//webapp//WEB-INF//applicationContext.xml");

//       SigSignoMarcaServicee sMSignoMarcaService =SigSignoMarcaServicece) context.getBean("observacionTramiteService");
        ObservacionTramiteService observacionTramiteService = (ObservacionTramiteService) context.getBean("observacionTramiteService");
        
        try {

//            observacionTramiteService.regObservacionTramite("SIG", 1L, 1L, 1L, Boolean.TRUE, "hola", "amigos");
//            observacionTramiteService.registrarObservacionTramite(null, "SIG", 1L, 1L, 1L, Boolean.TRUE, "hola", "amigos", 1);
//            observacionTramiteService.registrarObservacionTramite(null, "SIG", 1L, 1L, 1L, Boolean.TRUE, "hola", "amigos", 1);
//            observacionTramiteService.registrarObservacionTramite(1L, "SIG", 1L, 1L, 1L, Boolean.TRUE, new Date(),"VENT", "amigos", 1);
//            observacionTramiteService.registrarObservacionTramiteUno(1L, "SIG", 1L, 1L, 1L, Boolean.TRUE, "VENT", "amigos", 1);
//            ObservacionTramite nuevo = new ObservacionTramite();
//            nuevo.setIdObservacionTramite(1l);
//                  nuevo.setId(1l);
//            nuevo.setIdUsuario(1l);
//            nuevo.setIdLogTrans(1l);
//            nuevo.setEditable(true);
//            nuevo.setFechaObservacion(null);
//            nuevo.setEtapaTramite("EXMO");
//            nuevo.setDescripcion("OBERVACION DE LA MODIFICACION");
//            nuevo.setEstado("AC");
//
//            ObservacionTramite nuevo2 = observacionTramiteService.guardar_modificar_listar_ObservacionTramite(nuevo,"SIG", 2);
//           System.out.println("creadooo " + nuevo2.getDescripcion());

                ObservacionTramite sigObservacionTramite = observacionTramiteService.obtenerUltimaObservacionTramite("SIG", 162984L);
               System.out.println(" *-*** " + sigObservacionTramite.getDescripcion());
        } catch (Exception ex) {
            Logger.getLogger(ObservacionTramiteTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        //     Listado de observacion Tramite
        //eliminacion de registro
//        try {
//            observacionTramiteService.eliminarObservacion("SIG", 11L);
//        } catch (Exception ex) {
//            Logger.getLogger(ObservacionTramiteTest.class.getName()).log(Level.SEVERE, null, ex);
//        }
        //actualizar registro
//        try {
//            observacionTramiteService.actualizarObservacion("SIG", 24L, "Observacion 24");
//        } catch (Exception ex) {
//            Logger.getLogger(ObservacionTramiteTest.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }
    

//        List<ObservacionTramite> listaobservacion = observacionTramiteService.obtListadoObservacionPorTramite("REN", 1L);
//
//        for (ObservacionTramite sIGObservacionTramite : listaobservacion) {
//            System.out.println(" ** " + sIGObservacionTramite.getDescripcion());
//        }
}