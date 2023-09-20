/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.test;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import snp.gob.bo.gimodel.entidad.SigSignoMarca;
import snp.gob.bo.gimodel.entidad.SigTipoSigno;
import snp.gob.bo.gimodel.servicio.SigSignoMarcaService;
import snp.gob.bo.gimodel.servicio.SigTipoSignoService;

/**
 *
 * @author Eddy Valero
 * @version 1.0,
 */
public class SigTipoSignoTest {

    public static void main(String[] args) {

        ApplicationContext context = new FileSystemXmlApplicationContext("//home//eddy//NetBeansProjects//borrar//SistemaInterno//giview//src//main//webapp//WEB-INF//applicationContext.xml");
//        ApplicationContext context = new FileSystemXmlApplicationContext("//home//susana//Proyecto_Susan//SistemaInterno//giview//src//main//webapp//WEB-INF//applicationContext.xml");
        
        SigTipoSignoService sigTipoSignoService = (SigTipoSignoService) context.getBean("sigTipoSignoService");
//        SigTipoSigno sigTipoSigno = new SigTipoSigno();
//        
//        sigTipoSigno.setIdSignoMarca(2L);
//        sigTipoSigno.setIdLogTrans(1L);
//        sigTipoSigno.setTipoSigno("DO");
//        sigTipoSigno.setDescripcionOtro("descripcion ");
//        sigTipoSigno.setEstado("AC");
//        
//        try {
//          //  sigTipoSigno = sigTipoSignoService.registrarSigTipoSigno(sigTipoSigno);
//        } catch (Exception ex) {
//            Logger.getLogger(SigTipoSignoTest.class.getName()).log(Level.SEVERE, null, ex);
//        }
        try {
            //        List<SigTipoSigno> listatipoSigno = sigTipoSignoService.listaSigTipoSignoXidSIgnoMarca(1l);
//        for (SigTipoSigno sigTipoSigno : listatipoSigno) {
//            System.out.println(" **** " + sigTipoSigno.getIdSignoMarca());
//            System.out.println(" **** " + sigTipoSigno.getTipoSigno());
//        }
            
            
//        System.out.println("exito"+sigTipoSignoService.lista_SigTipoSigno_concatenado(105629l));
            System.out.println("cadena recuperada "+sigTipoSignoService.obtenerListaSigTipoSigno(152060L));
        } catch (Exception ex) {
            Logger.getLogger(SigTipoSignoTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        
        
    }

}
