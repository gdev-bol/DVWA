/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.bdimagen.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import snp.gob.bo.gimodel.bdimagen.servicio.ImagenPojoService;

/**
 *
 * @author eddy
 */
public class ImagenPojoTest {

    public static void main(String[] args) {
        
        
        ApplicationContext context = new FileSystemXmlApplicationContext("//home//eddy//NetBeansProjects//proyectoSIPI//proyectoInterno//SistemaInterno//giview//src//main//webapp//WEB-INF//applicationContext.xml");
        
        ImagenPojoService sigImagenService = (ImagenPojoService) context.getBean("imagenPojoService");
        System.out.println("hola /**** ");        
        
    }

}
