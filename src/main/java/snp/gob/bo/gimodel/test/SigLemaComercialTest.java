/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.test;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import snp.gob.bo.gimodel.entidad.SigLemaComercial;
import snp.gob.bo.gimodel.entidad.SigTipoSigno;
import snp.gob.bo.gimodel.servicio.SigLemaComercialService;
import snp.gob.bo.gimodel.servicio.SigTipoSignoService;

/**
 *
 * @author Eddy Valero
 * @version 1.0, 02/09/2016
 */
public class SigLemaComercialTest {

    public static void main(String[] args) {

        ApplicationContext context = new FileSystemXmlApplicationContext("//home//eddy//NetBeansProjects//borrar//SistemaInterno//giview//src//main//webapp//WEB-INF//applicationContext.xml");

        
        SigLemaComercialService sigLemaComercialService = (SigLemaComercialService) context.getBean("sigLemaComercialService");
        
        SigLemaComercial sigLemaComercial = new SigLemaComercial();
        
        
        sigLemaComercial.setIdSignoMarca(2L);
        sigLemaComercial.setSignoPadre("signo padre");
        sigLemaComercial.setSmPadre(12256325421L);
        sigLemaComercial.setCodigoSmPadre("SM-2016-0000");
        
        try {
            sigLemaComercialService.guardarSigLemaComercial(sigLemaComercial);
        } catch (Exception ex) {
            Logger.getLogger(SigLemaComercialTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        

        
        System.out.println("exito");

    }

}
