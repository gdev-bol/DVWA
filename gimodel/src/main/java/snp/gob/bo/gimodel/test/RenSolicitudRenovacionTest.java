/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.test;

import java.util.Date;
import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import snp.gob.bo.gimodel.entidad.RenSolicitudRenovacion;
import snp.gob.bo.gimodel.servicio.RenSolicitudRenovacionService;

/**
 *
 * @author levi
 */
public class RenSolicitudRenovacionTest {

    public static void main(String[] args) throws Exception {
//      ApplicationContext context = new FileSystemXmlApplicationContext("//home//levi//Documentos//PROYECTOS//SISInterno//SistemaInterno//giview//src//main//webapp//WEB-INF//applicationContext.xml");
        ApplicationContext context = new FileSystemXmlApplicationContext("//home//chano//sipi_v1.0//SistemaInterno//giview//src//main//webapp//WEB-INF//applicationContext.xml");

//        UsuarioPaginaService servicio = (UsuarioPaginaService) context.getBean("usuarioPaginaService");
//        RenRenovacionService renRenovacionService = (RenRenovacionService) context.getBean("renRenovacionService");
//        RenSolicitanteApoderadoService renSolicitanteApoderadoService = (RenSolicitanteApoderadoService) context.getBean("renSolicitanteApoderadoService");
        RenSolicitudRenovacionService renSolicitudRenovacionService = (RenSolicitudRenovacionService) context.getBean("renSolicitudRenovacionService");
        RenSolicitudRenovacion renSolicitudRenovacion = new RenSolicitudRenovacion();
        Long parametro = 1820L;
        int parametro1 = 1;
        
//        renSolicitudRenovacion=renSolicitudRenovacionService.buscaSolicitudRenovacionPorNumeroSrYGestion( parametro,parametro1);
//        System.out.println("rensolicitud"+renSolicitudRenovacion.getIdsolicitudrenovacion());
//        System.out.println("rensolicitud"+renSolicitudRenovacion.getSerie_recibo());
//        

            
                    

//        renSolicitudRenovacion.getIdsolicitudrenovacion();
                    renSolicitudRenovacion.setIdsolicitudrenovacion(16L);
//                    renSolicitudRenovacion.setIdlogtrans(14L);
//                    renSolicitudRenovacion.setId_recibo_solicitud(1L);
//                    renSolicitudRenovacion.setId_recibo_titulo(1L);
//                    renSolicitudRenovacion.setSr(11111111L);
//                    renSolicitudRenovacion.setGestion_sr(2026);
//                    renSolicitudRenovacion.setFecha_ingreso(new Date());
//                    renSolicitudRenovacion.setEstado_renovacion("AC");
//                    renSolicitudRenovacion.setUbicacion();
//                    renSolicitudRenovacion.setNumero_ultima_renovacion();
//                    renSolicitudRenovacion.setSerie_ultima_renovacion();
//                    renSolicitudRenovacion.setNumero_penultima_renovacion();
//                    renSolicitudRenovacion.setSerie_penultima_renovacion();
//                    renSolicitudRenovacion.setOficina();
//                    renSolicitudRenovacion.setNumero_recibo();
//                    renSolicitudRenovacion.setSerie_recibo();
//                    renSolicitudRenovacion.setNumero_titulo();
//                    renSolicitudRenovacion.setSerie_titulo();
//                    renSolicitudRenovacion.setClase_niza_reclasificacion();
//                    renSolicitudRenovacion.setLista_productos_limitacion();
//                    renSolicitudRenovacion.setSm();
//                    renSolicitudRenovacion.setSigno_registrado();
//                    renSolicitudRenovacion.setClase_niza_registrado();
//                    renSolicitudRenovacion.setTipo_genero();
//                    renSolicitudRenovacion.setTipo_signo();
//                    renSolicitudRenovacion.setNumero_registro_registrado();
//                    renSolicitudRenovacion.setSerie_registro_registrado();
//                    renSolicitudRenovacion.setFecha_registro_registrado();
//                    renSolicitudRenovacion.setEstado();
                                        
                    
                    
                    
                    Long numero=Long.valueOf("212121");
                    System.out.println("dsadsadsa"+renSolicitudRenovacionService.buscarRenSolicitud_NumeroFormulario("78787878").getIdsolicitudrenovacion());
                    
              
        
    }
}
