/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import snp.gob.bo.gimodel.entidad.Usuario;
import snp.gob.bo.gimodel.servicio.UsuarioService;

/**
 *
 * @author levi
 */
public class UsuarioTest {

    public static void main(String[] args) throws Exception {
//      ApplicationContext context = new FileSystemXmlApplicationContext("//home//levi//Documentos//PROYECTOS//SISInterno//SistemaInterno//giview//src//main//webapp//WEB-INF//applicationContext.xml");
        //ApplicationContext context = new FileSystemXmlApplicationContext("//home//eddy//NetBeansProjects//borrar//SistemaInterno//giview//src//main//webapp//WEB-INF//applicationContext.xml");
        ApplicationContext context = new FileSystemXmlApplicationContext("//home//susana//Proyecto_Susan//SistemaInterno//giview//src//main//webapp//WEB-INF//applicationContext.xml");
        UsuarioService servicio = (UsuarioService) context.getBean("usuarioService");

      //List<Usuario> lisUsuario=servicio.listaUsuarioXidPagina(1L);
        //         System.out.println("El id es:"+lisUsuario.get(0).getNombre());
//        System.out.println("tam:" + servicio.listaUsuario().size());
        
//        System.out.println("obtener usuario");
//        Usuario usuario = servicio.obtenerUsuario(38L);
//        
//        System.out.println("erika " + usuario.getNombre());
        System.out.println("----  "+servicio.listarNombreCompletoXIdUsuario(1000l));

    }

}
