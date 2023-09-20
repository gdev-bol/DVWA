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
import snp.gob.bo.gimodel.servicio.RenRenovacionService;
import snp.gob.bo.gimodel.servicio.RenSolicitanteApoderadoService;
import snp.gob.bo.gimodel.servicio.UsuarioPaginaService;

/**
 *
 * @author levi
 */
public class RenSolicitanteApoderadoTest {

    public static void main(String[] args) throws Exception {
//      ApplicationContext context = new FileSystemXmlApplicationContext("//home//levi//Documentos//PROYECTOS//SISInterno//SistemaInterno//giview//src//main//webapp//WEB-INF//applicationContext.xml");
        ApplicationContext context = new FileSystemXmlApplicationContext("//home//chano//sipi_v1.0//SistemaInterno//giview//src//main//webapp//WEB-INF//applicationContext.xml");

        UsuarioPaginaService servicio = (UsuarioPaginaService) context.getBean("usuarioPaginaService");
        RenRenovacionService renRenovacionService = (RenRenovacionService) context.getBean("renRenovacionService");
        RenSolicitanteApoderadoService renSolicitanteApoderadoService = (RenSolicitanteApoderadoService) context.getBean("renSolicitanteApoderadoService");
        RenSolicitanteApoderado renSolicitanteApoderado = new RenSolicitanteApoderado();
        int parametro = 2;
        

        
        
        
//        renSolicitanteApoderado.setIdsolicitanteapoderado(14L);
//        renSolicitanteApoderado.setIdsolicitudrenovacion(1L);
//        renSolicitanteApoderado.setIdlogtrans(1L);
//        renSolicitanteApoderado.setNombre_razonsocial("alcos limitada");
//        renSolicitanteApoderado.setNumero_documento("212");
//        renSolicitanteApoderado.setTelefono("12121212127878");
//        renSolicitanteApoderado.setLugar_expedicion("LPZ");
//        renSolicitanteApoderado.setPais("BO");
//        renSolicitanteApoderado.setTipo_persona("SOLI");
//        renSolicitanteApoderado.setTipo_titular("NAT");
//        renSolicitanteApoderado.setTipo_documento("NIT");
//        renSolicitanteApoderado.setGenero("FE");

        
//        System.out.println("Modificacion  apoderado"+renSolicitanteApoderadoService.crudDosRenSolicitanteApoderado(renSolicitanteApoderado, parametro).getIdsolicitanteapoderado());
        
        
        
        
         

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
//        System.out.println("idjhaskhdajks"+renSolicitanteApoderado.getNombre_razonsocial());
        
        
        
        List<RenSolicitanteApoderado>lst=renSolicitanteApoderadoService.buscaSolicitanteApoderadoPoridSolicitudyTipoPersonaRenovacion(16727L,"SOLI");
        for (RenSolicitanteApoderado renSolicitanteApoderado1 : lst) {
            System.out.println("lista nombre"+renSolicitanteApoderado1.getNombre_razonsocial());
            
        }
        
        
        
        

    }
}
