package snp.gob.bo.gimodel.solicitudes.test;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import snp.gob.bo.gimodel.entidad.Dominio;
import snp.gob.bo.gimodel.servicio.DominioService;
import snp.gob.bo.gimodel.solicitudes.entidades.FormularioPI100;
import snp.gob.bo.gimodel.solicitudes.entidades.FormularioPI101;
import snp.gob.bo.gimodel.solicitudes.entidades.SmTipoSignos;
import snp.gob.bo.gimodel.solicitudes.entidades.Solicitantes;
import snp.gob.bo.gimodel.solicitudes.servicio.FormularioPI100Service;
import snp.gob.bo.gimodel.solicitudes.servicio.FormularioPI101Service;
import snp.gob.bo.gimodel.test.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author eddy
 */
public class FormularioPI101Test {

    public static void main(String[] args) {

        ApplicationContext context = new FileSystemXmlApplicationContext("//home//eddy//NetBeansProjects//borrar//SistemaInterno//giview//src//main//webapp//WEB-INF//applicationContext.xml");
        FormularioPI101Service formularioPI101Service = (FormularioPI101Service) context.getBean("formularioPI101Service");

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
            
            FormularioPI101 formularioPI101 = formularioPI101Service.obtenerDatosFormularioPI101(4L);
            
            //RECORRER TODOS SUS SOLICITANTES
//            for (Solicitantes solicitantes : formularioPI100.getSolicitantes()) {
//                System.out.println(" *** "+ solicitantes.getTipoDocumento());
//                System.out.println(" *** "+ solicitantes.getNombreRazonSocial());
//            }
            
            
            


            
        } catch (Exception ex) {
            Logger.getLogger(FormularioPI101Test.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
