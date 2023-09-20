/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.test;

import java.util.Date;
import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import snp.gob.bo.gimodel.entidad.Historial;
import snp.gob.bo.gimodel.entidad.RenSolicitanteApoderado;
import snp.gob.bo.gimodel.servicio.HistorialService;
import snp.gob.bo.gimodel.servicio.RenSolicitanteApoderadoService;

/**
 *
 * @author chano Rojas
 */
public class HistorialTest {

    public static void main(String[] args) throws Exception {

        ApplicationContext context = new FileSystemXmlApplicationContext("//home//eddy//NetBeansProjects//borrar//SistemaInterno//giview//src//main//webapp//WEB-INF//applicationContext.xml");
//        ApplicationContext context = new FileSystemXmlApplicationContext("//home//chano//sipi_v1.0//SistemaInterno//giview//src//main//webapp//WEB-INF//applicationContext.xml");
        HistorialService historialService = (HistorialService) context.getBean("historialService");
        RenSolicitanteApoderadoService renSolicitanteApoderadoService = (RenSolicitanteApoderadoService) context.getBean("renSolicitanteApoderadoService");

        
        //Prueba de guardado
//        Historial historial = new Historial();
//        historial.setIdUsuario(1l);
//        historial.setIdLogTrans(1l);
//        historial.setId(1l);
//        historial.setTipo("CT");
//        //   historial.setNumeroSmSrMod(11111L);
//        historial.setDescripcion("MARCA DE  ORODUCTIO DE A CLASE NIZA");
//        historial.setObservacion("DSADSADSADSADADSADSADMARCA DE  ORODUCTIO DE A CLASE NIZA");
//        System.out.println("id historial general" + historialService.crudHistorial(historial, "MOD", 1));
        
        
        //Recuperar registros de un listado
//        for (Historial historial : historialService.obtenerListaHistorialXId(152061L, "SIG")) {
//            System.out.println(" *** " + historial.getIdHistorial());
//        }

        for (Historial historial : historialService.obtenerListaHistorialXId(1L, "MOD")) {
            System.out.println(" *** " + historial.getIdHistorial());
        }
        
        
        
        
        

//        historialService.comparaDireccionesHistorial();
//        List<RenSolicitanteApoderado>lstSolicitante=renSolicitanteApoderadoService.buscaSolicitanteApoderadoPoridSolicitudyTipoPersona(17L, "SOLI");
//        historialService.historialSectorSolicitante(9L, lstSolicitante);
    }
}
