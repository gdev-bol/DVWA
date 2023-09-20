/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.test;

import java.math.BigDecimal;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import snp.gob.bo.gimodel.entidad.Tasa;
import snp.gob.bo.gimodel.servicio.TasaService;

/**
 *
 * @author chano Rojas
 */
public class TasaTest {

    public static void main(String[] args) throws Exception {

//        ApplicationContext context = new FileSystemXmlApplicationContext("//home//eddy//NetBeansProjects//prototipoSpringJSFPrimeFaces//giview//src//main//webapp//WEB-INF//applicationContext.xml");
//        ApplicationContext context = new FileSystemXmlApplicationContext("//home//chano//sipi_v1.0//SistemaInterno//giview//src//main//webapp//WEB-INF//applicationContext.xml"); 
        ApplicationContext context = new FileSystemXmlApplicationContext("//home//rojas//sipi_v1.0//SistemaInterno//giview//src//main//webapp//WEB-INF//applicationContext.xml");
        TasaService tasaService=(TasaService)context.getBean("tasaService");
       
        
//            List<Tasa>lista=tasaService.obtenerListaTasas();
//        for (Tasa tasa : lista) {
//            System.out.println("descripciontasa::::::::::::"+tasa.getDescripcion());
//        }
//        
        
        Tasa tasa=new Tasa();
        tasa.setIdLogTrans(1L);
        tasa.setEstado("AC");
        tasa.setGestion(2016);
        tasa.setCosto(new BigDecimal(10));
        tasa.setDescuento(new BigDecimal(10));
        tasa.setDescripcion("LAMINAS");
        tasa.setTipoTramite("REGS");
        tasa.setUnidad("DEAU");
        tasaService.crudTasa(tasa,1);
        
//        String val="CD-S";
//        String[] arraycodigo=val.split("-");
//      
//        System.out.println("codigo"+arraycodigo[0]);
//        
//        for (int i = 0; i < arraycodigo.length; i++) {
//            System.out.println("codigo"+arraycodigo[i]);
//            
//        }
//        List<Tasa>list=tasaService.obtenerListaTasaActivasPorTipoTramite("REGS");
//        for (Tasa tasa : list) {
//        }
//        
//        historialService.comparaDireccionesHistorial();
        
//        List<RenSolicitanteApoderado>lstSolicitante=renSolicitanteApoderadoService.buscaSolicitanteApoderadoPoridSolicitudyTipoPersona(17L, "SOLI");
//        historialService.historialSectorSolicitante(9L, lstSolicitante);
        
        
    }
}
