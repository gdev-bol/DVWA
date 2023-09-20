/*
 * To change this license header; choose License Headers in Project Properties.
 * To change this template file; choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.test;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import snp.gob.bo.gimodel.entidad.ModSolicitanteApoderado;
import snp.gob.bo.gimodel.servicio.ModSolicitanteApoderadoService;

/**
 *
 * @author susana
 */
public class ModSolicitanteApoderadoTest {

    public static void main(String[] args) {
        ApplicationContext context = new FileSystemXmlApplicationContext("//home//susana//Proyecto_Susan//SistemaInterno//giview//src//main//webapp//WEB-INF//applicationContext.xml");
        ModSolicitanteApoderadoService modSolicitanteApoderadoService = (ModSolicitanteApoderadoService) context.getBean("modSolicitanteApoderadoService");

        ModSolicitanteApoderado modsolicitanteapoderado = new ModSolicitanteApoderado();
        ModSolicitanteApoderado nuevo2 = new ModSolicitanteApoderado();

        modsolicitanteapoderado.setIdmodificacion(3l);

        modsolicitanteapoderado.setIdLogTrans(1l);
        modsolicitanteapoderado.setTipo_titular("J");
        modsolicitanteapoderado.setTipo_persona("SOLI");
        modsolicitanteapoderado.setNombre_razonsocial("setNombre_razonsocial");
        modsolicitanteapoderado.setPrimer_apellido("setNombre_razonsocial");
        modsolicitanteapoderado.setSegundo_apellido("setNombre_razonsocial");
        modsolicitanteapoderado.setNumero_documento("454654");
        modsolicitanteapoderado.setTipo_documento("RUM");
        modsolicitanteapoderado.setLugar_expedicion("ORU");
        modsolicitanteapoderado.setPais("BOLI");
        modsolicitanteapoderado.setGenero("M");
        modsolicitanteapoderado.setSolicitud_departamento("LPZ");
        modsolicitanteapoderado.setPoder("PODER");
        modsolicitanteapoderado.setDireccion("DIRECCION");
        modsolicitanteapoderado.setTelefono("TTT");
        modsolicitanteapoderado.setCelular("CCC");
        modsolicitanteapoderado.setEmail("EMAIL");
        modsolicitanteapoderado.setFax("FAX");
        modsolicitanteapoderado.setEstado("AC");

        try {
            //        nuevo2 = modSolicitanteApoderadoService.guardar_modificar_listar_SolicitanteApoderado(modsolicitanteapoderado,1);
//        System.out.println("NUEVO 2 "+nuevo2.getNombre_razonsocial());
//        List<ModSolicitanteApoderado> listaSolicitanteApoderado = new ArrayList<ModSolicitanteApoderado>();
//        listaSolicitanteApoderado = modSolicitanteApoderadoService.listadoSolicitanteXidmodificacion(23L);
//        for (ModSolicitanteApoderado listaSolicitanteApoderado1 : listaSolicitanteApoderado) {
//            System.out.println("DATO  " + listaSolicitanteApoderado1.getNombre_razonsocial());
//        }
//
//        List<ModSolicitanteApoderado> listaApoderado = new ArrayList<ModSolicitanteApoderado>();
//        listaApoderado = modSolicitanteApoderadoService.listadoApoderadoXidmodificacion(23l);
//        for (ModSolicitanteApoderado listaSolicitanteApoderado1 : listaApoderado) {
//            System.out.println("DATO apo " + listaSolicitanteApoderado1.getNombre_razonsocial());
//        }
            
            System.out.println("EliminadosModSolicitanteApoderado "+modSolicitanteApoderadoService.obtenerRegistrosEliminadosModSolicitanteApoderado(26752L, "53250", "SOLI").size());
        } catch (Exception ex) {
            Logger.getLogger(ModSolicitanteApoderadoTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
