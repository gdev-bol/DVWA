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
import snp.gob.bo.gimodel.entidad.listaMenu;
import snp.gob.bo.gimodel.servicio.ListaUsuarioRolService;
import snp.gob.bo.gimodel.servicio.SigSignoMarcaService;

/**
 *
 * @author levi
 */
public class ListaUsuarioRolTest {
    public static void main(String[] args) {
        try {
            ApplicationContext context = new FileSystemXmlApplicationContext("//home//levi//Documentos//PROYECTOS//SISInterno//SistemaInterno//giview//src//main//webapp//WEB-INF//applicationContext.xml");
            
            ListaUsuarioRolService servicio = (ListaUsuarioRolService) context.getBean("listaUsuarioRolService");
            
            List<listaMenu> lista=  servicio.getUsuarioRol("Adminotifica", "12345", "3");
            for(int i=0;i<=lista.size()-1;i++)
            { System.out.println("En consulta estado"+lista.get(i).getEstado());
            
            }
        } catch (Exception ex) {
            Logger.getLogger(ListaUsuarioRolTest.class.getName()).log(Level.SEVERE, null, ex);
        }
       }
}
