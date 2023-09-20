/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.test;

import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import snp.gob.bo.gimodel.entidad.Documento;
import snp.gob.bo.gimodel.servicio.DocumentoService;

/**
 *
 * @author susana
 */
public class DocumentoTest {
     public static void main(String[] args) {
     ApplicationContext context = new FileSystemXmlApplicationContext("//home//susana//Proyecto_Susan//SistemaInterno//giview//src//main//webapp//WEB-INF//applicationContext.xml");
     
     DocumentoService documentoService = (DocumentoService) context.getBean("documentoService");
     
     List<Documento> lista = documentoService.obtenerListadoDocumento();
         for (Documento lista1 : lista) {
             System.out.println("imagen   "+lista1.getNombre_archivo() +"   dddd "+lista1.getFecha_creacion());
         }     
     }
}
