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
import snp.gob.bo.gimodel.solicitudes.entidades.DireccionNotificaciones;
import snp.gob.bo.gimodel.solicitudes.entidades.FormularioPI100;
import snp.gob.bo.gimodel.solicitudes.entidades.SmTipoSignos;
import snp.gob.bo.gimodel.solicitudes.entidades.Solicitantes;
import snp.gob.bo.gimodel.solicitudes.servicio.FormularioPI100Service;
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
public class FormularioPI100Test {

    public static void main(String[] args) {

        ApplicationContext context = new FileSystemXmlApplicationContext("//home//eddy//NetBeansProjects//borrar//SistemaInterno//giview//src//main//webapp//WEB-INF//applicationContext.xml");
        FormularioPI100Service formularioPI100Service = (FormularioPI100Service) context.getBean("formularioPI100Service");



        try {
//            HashMap hashMap = formularioPI100Service.obtenerdatosPI100("201608231");
            //HashMap hashMap = formularioPI100Service.obtenerNumeroFormulario("2");
            
            
//            System.out.println(" *** " + hashMap.get("mensaje"));
//            System.out.println(" *** " + hashMap.get("continuarFlujo"));
//            System.out.println(" *** " + hashMap.get("idFormulario"));
            
            FormularioPI100 formularioPI100 = formularioPI100Service.obtenerDatosFormularioPI100(2L);
            
            
            
            
            //RECORRER TODOS SUS SOLICITANTES
//            for (Solicitantes solicitantes : formularioPI100.getSolicitantes()) {
//                System.out.println(" *** "+ solicitantes.getTipoDocumento());
//                System.out.println(" *** "+ solicitantes.getNombreRazonSocial());
//            }
            
            //recorrer las direcciones
            for (DireccionNotificaciones direccion : formularioPI100.getDirecciones()) {
                System.out.println("direccion  :" + direccion.getId());
                System.out.println("direccion  :" + direccion.getZonaBarrio());
                System.out.println("direccion  :" + direccion.getPiso());
            }
            
            
            


            
        } catch (Exception ex) {
            Logger.getLogger(FormularioPI100Test.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
