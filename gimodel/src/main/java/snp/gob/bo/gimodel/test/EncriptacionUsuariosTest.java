/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.test;

import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import snp.gob.bo.gimodel.entidad.Usuario;
import snp.gob.bo.gimodel.servicio.ListaUsuarioRolService;
import snp.gob.bo.gimodel.servicio.UsuarioService;

/**
 *
 * @author levi
 */
public class EncriptacionUsuariosTest {

    public static void main(String[] args) throws Exception {
        /*Se debe colocar la direccion correcta en el aplication context pudiera ser el 28 o 23*/
//        ApplicationContext context = new FileSystemXmlApplicationContext("//home//levi//Documentos//PROYECTOS//ProyectoGIT//hidra//giview//src//main//webapp//WEB-INF//applicationContext.xml");
        ApplicationContext context = new FileSystemXmlApplicationContext("//home//tenoch//NetBeansProjects//hidra_desarrollo//hidra//giview//src//main//webapp//WEB-INF//applicationContext.xml");
        
        
       
        
        
        ListaUsuarioRolService servicio = (ListaUsuarioRolService) context.getBean("listaUsuarioRolService");
        UsuarioService usuarioServ = (UsuarioService) context.getBean("usuarioService");
        String codificado;
        String decodificado;

        List<Usuario> listusuarios = usuarioServ.listaUsuario();
//       System.out.println("listusuarios.size()::" + listusuarios.size());
//        
//       decodificado = servicio.dencriptarContrasena("jhigorre", "\"f+03aRkC06ScR/jyXmaRrw==\"");
//       System.out.println("decodificado" + decodificado);
        
        for (int i = 0; i <= listusuarios.size() - 1; i++) {
//            System.out.println("ecriptado::" + servicio.encriptarContrasena(listusuarios.get(i).getLogin(), "12345"));
            codificado = servicio.encriptarContrasena(listusuarios.get(i).getLogin(), "12345");
            listusuarios.get(i).setContrasenia(codificado);
            usuarioServ.crudUsuario(listusuarios.get(i), 2);

        }
//         
//  
    }
}
