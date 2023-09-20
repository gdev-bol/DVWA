/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import snp.gob.bo.gimodel.entidad.Usuario;
import snp.gob.bo.gimodel.entidad.UsuarioTramite;
import snp.gob.bo.gimodel.servicio.UsuarioService;
import snp.gob.bo.gimodel.servicio.UsuarioTramiteService;

/**
 *
 * @author levi
 */
public class UsuarioTramiteTest {

    public static void main(String[] args) throws Exception {
//      ApplicationContext context = new FileSystemXmlApplicationContext("//home//levi//Documentos//PROYECTOS//SISInterno//SistemaInterno//giview//src//main//webapp//WEB-INF//applicationContext.xml");
        ApplicationContext context = new FileSystemXmlApplicationContext("//home//eddy//NetBeansProjects//borrar//SistemaInterno//giview//src//main//webapp//WEB-INF//applicationContext.xml");
//        ApplicationContext context = new FileSystemXmlApplicationContext("//home//susana//Proyecto_Susan//SistemaInterno//giview//src//main//webapp//WEB-INF//applicationContext.xml");
        UsuarioTramiteService usuarioTramiteServicio = (UsuarioTramiteService) context.getBean("usuarioTramiteService");

      //List<Usuario> lisUsuario=servicio.listaUsuarioXidPagina(1L);
        //         System.out.println("El id es:"+lisUsuario.get(0).getNombre());
//        System.out.println("tam:" + servicio.listaUsuario().size());
        
//        System.out.println("obtener usuario");
//        Usuario usuario = servicio.obtenerUsuario(38L);
//        
//        System.out.println("erika " + usuario.getNombre());
        
        UsuarioTramite usuarioTramite = new UsuarioTramite();
        usuarioTramite.setIdUsuarioTramite(1L);
        usuarioTramite.setTipoTramite("SIG");
        usuarioTramite.setIdUsuarioExterno(1);
        usuarioTramite.setId(1L);
        usuarioTramite.setIdLogTrans(1L);
        usuarioTramite.setEstado("AC");
        
        
        usuarioTramite = usuarioTramiteServicio.crudUsuarioTramite(usuarioTramite, 1L);
        
        
        System.out.println("usuarioTramite ..." + usuarioTramite.getIdUsuarioTramite());
        
        System.out.println("hola");

    }

}
