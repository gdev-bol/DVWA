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
import snp.gob.bo.gimodel.entidad.UsuarioEtapa;
import snp.gob.bo.gimodel.servicio.UsuarioEtapaService;

/**
 *
 * @author Eddy Valero
 * @version 1.0, 21/12/2016
 * 
 * 
 */
public class UsuarioEtapaTest {
      public static void main(String[] args) {
          
      ApplicationContext context = new FileSystemXmlApplicationContext("//home//levi//Documentos//PROYECTOS//SISInterno//SistemaInterno//giview//src//main//webapp//WEB-INF//applicationContext.xml");
        
      UsuarioEtapaService usuarioEtapaService = (UsuarioEtapaService) context.getBean("usuarioEtapaService");
          try {
               List<UsuarioEtapa> list=usuarioEtapaService.listaUsuarioEtapaXIdUsuario(139L);
               System.out.println("USuarioEtapa::"+list.get(0).getIdUsuarioEtapa());
              
              /*  System.out.println("hola ***");
              
              UsuarioEtapa usuarioEtapa = new UsuarioEtapa();
              
              usuarioEtapa.setIdLogTrans(1L);
              usuarioEtapa.setIdUsuario(1L);
              usuarioEtapa.setIdEtapa(3L);
              usuarioEtapa.setEstado("AC");
              
              try {
              UsuarioEtapa usuarioEtapaNuevo = usuarioEtapaService.crudUsuarioEtapa(usuarioEtapa, 1);
              
              System.out.println(" **** " + usuarioEtapaNuevo.getIdUsuarioEtapa());
              
              } catch (Exception ex) {
              Logger.getLogger(UsuarioEtapaTest.class.getName()).log(Level.SEVERE, null, ex);
              }
              */
          } catch (Exception ex) {
              Logger.getLogger(UsuarioEtapaTest.class.getName()).log(Level.SEVERE, null, ex);
          }
      
   
      }
      
       

}
