/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.test;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import snp.gob.bo.gimodel.entidad.UsuarioPagina;
import snp.gob.bo.gimodel.servicio.ListaUsuarioRolService;
import snp.gob.bo.gimodel.servicio.UsuarioPaginaService;

/**
 *
 * @author levi
 */
public class UsuarioRolTest {
      public static void main(String[] args) throws Exception {
      ApplicationContext context = new FileSystemXmlApplicationContext("//home//levi//Documentos//PROYECTOS//SISInterno//SistemaInterno//giview//src//main//webapp//WEB-INF//applicationContext.xml");
        
      ListaUsuarioRolService servicio = (ListaUsuarioRolService) context.getBean("listaUsuarioRolService");
        
      Long id=  servicio.obtieneIdUsuario("senapi","12345");
          System.out.println("El id es:"+id);
  
      
      
      /* for(int i=0;i<=lista.size()-1;i++)
            { System.out.println("En consulta estado"+lista.get(i).getHabilitado());
              
              // Format formatter = new SimpleDateFormat("H:m:s");
              //  System.out.println("formateada::"+formatter.format(lista.get(i).getHora_vig_ini()));
            }
      
      
      
       }
*/
      }
      
       

}
