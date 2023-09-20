/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import snp.gob.bo.gimodel.servicio.SeguimientoService;
import snp.gob.bo.gimodel.servicio.SigSignoMarcaService;

/**
 *
 * @author Eddy Valero
 * @version 1.0,
 */
public class SigSignoMarcaTest {

    public static void main(String[] args) throws Exception {

//        ApplicationContext context = new FileSystemXmlApplicationContext("//home//eddy//NetBeansProjects//borrar//SistemaInterno//giview//src//main//webapp//WEB-INF//applicationContext.xml");
//        ApplicationContext context = new FileSystemXmlApplicationContext("//home//levi//Documentos//PROYECTOS//ProyectoGITRenoOpoSegui//hidra//giview//src//main//webapp//WEB-INF//applicationContext.xml");
                ApplicationContext context = new FileSystemXmlApplicationContext("//home//tenoch//NetBeansProjects//hidra_desarrollo//hidra//giview//src//main//webapp//WEB-INF//applicationContext.xml");

//        ApplicationContext context = new FileSystemXmlApplicationContext("//home//susana//Proyecto_Susan//SistemaInterno//giview//src//main//webapp//WEB-INF//applicationContext.xml");
        SeguimientoService seguimiento = (SeguimientoService) context.getBean("seguimientoService");
        SigSignoMarcaService sigSignoMarcaService = (SigSignoMarcaService) context.getBean("sigSignoMarcaService");
        

//        List<Seguimiento> lista=  seguimiento.listaSeguimientoXIdSignomarca(165860L);
//        System.out.println("lista primera:"+lista.get(0).getEtapa());
        
        System.out.println("id signomarca"+sigSignoMarcaService.listaSigSignoMarcaXRegistroyClaseNiza(20096L,"C", null, 11).getIdSignoMarca());        
        
        
        
        
        
    }

}
