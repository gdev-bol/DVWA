/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.test;

import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import snp.gob.bo.gimodel.entidad.RenDireccionDeNotificacion;
import snp.gob.bo.gimodel.entidad.RenSolicitanteApoderado;
import snp.gob.bo.gimodel.servicio.RenDireccionDeNotificacionService;
import snp.gob.bo.gimodel.servicio.RenRenovacionService;
import snp.gob.bo.gimodel.servicio.RenSolicitanteApoderadoService;
import snp.gob.bo.gimodel.servicio.UsuarioPaginaService;

/**
 *
 * @author levi
 */
public class RenDireccionDeNotificacionTest {

    public static void main(String[] args) throws Exception {
//      ApplicationContext context = new FileSystemXmlApplicationContext("//home//levi//Documentos//PROYECTOS//SISInterno//SistemaInterno//giview//src//main//webapp//WEB-INF//applicationContext.xml");
        ApplicationContext context = new FileSystemXmlApplicationContext("//home//chano//sipi_v1.0//SistemaInterno//giview//src//main//webapp//WEB-INF//applicationContext.xml");

        UsuarioPaginaService servicio = (UsuarioPaginaService) context.getBean("usuarioPaginaService");
        RenRenovacionService renRenovacionService = (RenRenovacionService) context.getBean("renRenovacionService");
        RenSolicitanteApoderadoService renSolicitanteApoderadoService = (RenSolicitanteApoderadoService) context.getBean("renSolicitanteApoderadoService");
        RenDireccionDeNotificacionService renDireccionDeNotificacionService = (RenDireccionDeNotificacionService) context.getBean("renDireccionDeNotificacionService");

        RenDireccionDeNotificacion renDireccionDeNotificacion = new RenDireccionDeNotificacion();
        int parametro = 1;

        renDireccionDeNotificacion.setIdsolicitudrenovacion(1L);
        renDireccionDeNotificacion.setIdlogtrans(1L);
        renDireccionDeNotificacion.setIdmodificacion(1L);
        renDireccionDeNotificacion.setCiudad_notificacion("LPZ");
        renDireccionDeNotificacion.setZona_barrio("LAS PLAZA MURILLO");
        renDireccionDeNotificacion.setAvenida_calle("AV LAS AMERICAS");
        renDireccionDeNotificacion.setNumero("15");
        renDireccionDeNotificacion.setEdificio("1sddsadsadsadsa5");
        renDireccionDeNotificacion.setDepartamento("1s");
        renDireccionDeNotificacion.setReferencia_direccion("cerca a unas lineas amearillas");
        renDireccionDeNotificacion.setCorreo_electronico("cerca@hotmail.com");
        renDireccionDeNotificacion.setTelefono("1231");
        renDireccionDeNotificacion.setCelular("456546546");
        renDireccionDeNotificacion.setEstado("AC");
        
        

        
        System.out.println("rendireccion"+renDireccionDeNotificacionService.crudRenDireccionDeNotificacion(renDireccionDeNotificacion, parametro).getIddirecciondenotificacion());
        
        
        
        
        
        
//        List<RenDireccionDeNotificacion> listaRSA = renDireccionDeNotificacionService.obtieneListaRenDireccionDeNotificacion(renDireccionDeNotificacion,4);
//        for (RenDireccionDeNotificacion renRenovacion1 : listaRSA) {
//            if (!listaRSA.isEmpty()) {
//                System.out.println("el listado tiene como valor de id" + renRenovacion1.getCiudad_notificacion());
//                System.out.println("orden de renovacion" + renRenovacion1.getDepartamento());
//            }else{
//                System.out.println("lista vacia");
//            }
//        }

    }
}
