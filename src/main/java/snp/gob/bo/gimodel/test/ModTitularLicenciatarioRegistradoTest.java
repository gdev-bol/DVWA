/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.test;

import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import snp.gob.bo.gimodel.entidad.ModTitularLicenciatarioRegistrado;
import snp.gob.bo.gimodel.servicio.ModTitularLicenciatarioRegistradoService;

/**
 *
 * @author susana
 */
public class ModTitularLicenciatarioRegistradoTest {

    public static void main(String[] args) {
        ApplicationContext context = new FileSystemXmlApplicationContext("//home//susana//Proyecto_Susan//SistemaInterno//giview//src//main//webapp//WEB-INF//applicationContext.xml");
        ModTitularLicenciatarioRegistradoService modTitularLicenciatarioRegistradoService = (ModTitularLicenciatarioRegistradoService) context.getBean("modTitularLicenciatarioRegistradoService");

        ModTitularLicenciatarioRegistrado modtitularlicenciatarioregistrado = new ModTitularLicenciatarioRegistrado();
        modtitularlicenciatarioregistrado.setIdmodificacion(1l);
        modtitularlicenciatarioregistrado.setIdlogtrans(1l);
//        modtitularlicenciatarioregistrado.setTipo_persona_registrado("reg");
        modtitularlicenciatarioregistrado.setNombre_razonsocial("NOMBRE");
        modtitularlicenciatarioregistrado.setPrimer_apellido("APELLIDO");
        modtitularlicenciatarioregistrado.setSegundo_apellido("SEGUNDO");
        modtitularlicenciatarioregistrado.setDireccion("DIRECCION");
        modtitularlicenciatarioregistrado.setEstado("AC");        
        ModTitularLicenciatarioRegistrado nuevo = modTitularLicenciatarioRegistradoService.guardar_modificar_listar_ModTitularLicenciatarioRegistrado(modtitularlicenciatarioregistrado,1);        
        System.out.println("CREADO  "+nuevo.getIdtitularlicenciatarioregistrado());
        
//        List<ModTitularLicenciatarioRegistrado> listaTitularRegistrado = modTitularLicenciatarioRegistradoService.listaTitularRegistradoXidmodificacion(1l);
//        for (ModTitularLicenciatarioRegistrado item : listaTitularRegistrado) {
//            System.out.println("DATO   "+item.getNombre_razonsocial());
//        }
//        
//        List<ModTitularLicenciatarioRegistrado> listaTitularFusion = modTitularLicenciatarioRegistradoService.listaTitularFusionXidmodificacion(1l);
//        for (ModTitularLicenciatarioRegistrado item : listaTitularFusion) {
//            System.out.println("DATO 2  "+item.getNombre_razonsocial());
//        }
    }
}
