/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import snp.gob.bo.gimodel.servicio.MenuPaginaService;
import snp.gob.bo.gimodel.servicio.MenuService;

/**
 *
 * @author levi
 */
public class MenuTest {
     public static void main(String[] args) throws Exception {
        /*Se debe colocar la direccion correcta en el aplication context pudiera ser el 28 o 23*/
      ApplicationContext context = new FileSystemXmlApplicationContext("//home//levi//Documentos//PROYECTOS//ProyectoGITRenoOpoSegui//hidra//giview//src//main//webapp//WEB-INF//applicationContext.xml");
        
      MenuPaginaService servicio = (MenuPaginaService) context.getBean("menuPaginaService");
      //   System.out.println("dato"+servicio.listaMenusPaginasUsuario(1L,"musnayo","r88Pc3glo0gHApwxRa6EAA==").get(0).getMenu());
     }
}
