package snp.gob.bo.gimodel.test;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import snp.gob.bo.gimodel.entidad.Dominio;
import snp.gob.bo.gimodel.enums.EnumNombreDominio;
import snp.gob.bo.gimodel.servicio.DominioService;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author eddy
 */
public class DominioTest {

    public static void main(String[] args) throws Exception {

        ApplicationContext context = new FileSystemXmlApplicationContext("//home//susana//Proyecto_Susan//SistemaInterno//giview//src//main//webapp//WEB-INF//applicationContext.xml");
//        ApplicationContext context = new FileSystemXmlApplicationContext("//home//eddy//NetBeansProjects//borrar//SistemaInterno//giview//src//main//webapp//WEB-INF//applicationContext.xml");
        //      ApplicationContext context = new FileSystemXmlApplicationContext("//home//chano//sipi_v1.0//SistemaInterno//giview//src//main//webapp//WEB-INF//applicationContext.xml");
        DominioService dominioService = (DominioService) context.getBean("dominioService");

//        Dominio sMDominio = new Dominio();
//        sMDominio.setIddominio(null);
//        sMDominio.setIdtiposigno(2L);
//        sMDominio.setIdlogtrans(2L);
//        sMDominio.setDominio("dominio");
//        sMDominio.setCodigo("CODI");
//        sMDominio.setDominioPadre("");
//        sMDominio.setNombre("nombre dominio");
//        sMDominio.setDescripcion("descripcion dominio");
//        sMDominio.setOrden(1);
//        sMDominio.setFechaInicio(new Date());
//        sMDominio.setFechaFin(new Date());
//        try {
//            Dominio smd = sMDominioService.registrarDominio(sMDominio);
//            System.out.println("smd *** " + smd.getIddominio());
//        } catch (Exception ex) {
//            Logger.getLogger(DominioTest.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        try {
////            for (Dominio sMDominio : dominioService.obtenerListadoDominio("tipo_modificacion")) {
////                System.out.println(" *** " + sMDominio.getNombre());
////            }
//        } catch (Exception ex) {
//            Logger.getLogger(DominioTest.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
        try {
//            System.out.println("----------------    " + dominioService.obtenerListadoDominioXCodigo(EnumNombreDominio.PAIS.getCodigo(), "US").get(0).getNombre());

//            for (Dominio sMDominio : dominioService.obtenerListadoDominioXCodigo("pais", "US")) {
//                System.out.println(" ### " + sMDominio.getNombre());
//            }
            String resultado = dominioService.obtenerNombreCodigoDominio("pais", "US");
//            System.out.println("resultado *** " + resultado);

        } catch (Exception ex) {
            Logger.getLogger(DominioTest.class.getName()).log(Level.SEVERE, null, ex);
        }

//        List<Dominio>lista=dominioService.ListaTipoTramiteReciboDominio("tipo_tramite_recibo", "REGS");
//        
//        for (Dominio dominio : lista) {
//            System.out.println("codigo"+dominio.getCodigo());
//        }
    }

}
