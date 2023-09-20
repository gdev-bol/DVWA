/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.test;

import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import snp.gob.bo.gimodel.entidad.RenSolicitanteApoderado;
import snp.gob.bo.gimodel.entidad.RenTitularRegistrado;
import snp.gob.bo.gimodel.servicio.RenRenovacionService;
import snp.gob.bo.gimodel.servicio.RenSolicitanteApoderadoService;
import snp.gob.bo.gimodel.servicio.RenTitularRegistradoService;
import snp.gob.bo.gimodel.servicio.UsuarioPaginaService;

/**
 *
 * @author levi
 */
public class RenTitularRegistradoTest {

    public static void main(String[] args) throws Exception {
//      ApplicationContext context = new FileSystemXmlApplicationContext("//home//levi//Documentos//PROYECTOS//SISInterno//SistemaInterno//giview//src//main//webapp//WEB-INF//applicationContext.xml");
        ApplicationContext context = new FileSystemXmlApplicationContext("//home//chano//sipi_v1.0//SistemaInterno//giview//src//main//webapp//WEB-INF//applicationContext.xml");

        UsuarioPaginaService servicio = (UsuarioPaginaService) context.getBean("usuarioPaginaService");
        RenRenovacionService renRenovacionService = (RenRenovacionService) context.getBean("renRenovacionService");
        RenSolicitanteApoderadoService renSolicitanteApoderadoService = (RenSolicitanteApoderadoService) context.getBean("renSolicitanteApoderadoService");
        RenTitularRegistradoService renTitularRegistradoService=(RenTitularRegistradoService) context.getBean("renTitularRegistradoService");
        
        
        RenTitularRegistrado renSolicitanteApoderado= new RenTitularRegistrado();
        
        int parametro = 1;
        
     
        
        renSolicitanteApoderado.setIdsolicitudrenovacion(1L);
        renSolicitanteApoderado.setIdlogtrans(1L);
        renSolicitanteApoderado.setNombre_razonsocial("alcos limitada");
        renSolicitanteApoderado.setDireccion("villalalalaqlalal");
        renSolicitanteApoderado.setEstado("AC");
        
        
        

        
        System.out.println("solictud apoderado"+renTitularRegistradoService.crudDosRenTitularRegistrado(renSolicitanteApoderado, parametro).getIdtitularregistrado());
        
        
        
        
        

//        List<RenSolicitanteApoderado> listaRSA = renSolicitanteApoderadoService.obtenerListadoRenSolicitanteApoderado(renSolicitanteApoderado, parametro);
//        for (RenSolicitanteApoderado renRenovacion1 : listaRSA) {
//            if (!listaRSA.isEmpty()) {
//                System.out.println("el listado tiene como valor de id" + renRenovacion1.getIdsolicitanteapoderado());
//                System.out.println("orden de renovacion" + renRenovacion1.getDireccion());
//            }else{
//                System.out.println("lista vacia");
//            }
//        }
        
        
        
        
//        renSolicitanteApoderado=renSolicitanteApoderadoService.buscaSolicitanteApoderadoPoridSolicitudyTipoPersona(1L, "SOLI");
//        renSolicitanteApoderado=renSolicitanteApoderadoService.buscaSolicitanteApoderadoPoridSolicitudyTipoPersona(1L, "");
        System.out.println("idjhaskhdajks"+renSolicitanteApoderado.getNombre_razonsocial());
        
        
        
        
        
        
        

    }
}
