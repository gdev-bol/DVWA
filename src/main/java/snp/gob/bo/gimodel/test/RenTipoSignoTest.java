package snp.gob.bo.gimodel.test;

import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import snp.gob.bo.gimodel.entidad.RenTipoSigno;
import snp.gob.bo.gimodel.servicio.CorrelativoService;
import snp.gob.bo.gimodel.servicio.RenTipoSignoService;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author eddy
 */
public class RenTipoSignoTest {

    public static void main(String[] args) throws Exception {

//        ApplicationContext context = new FileSystemXmlApplicationContext("//home//susana//Proyecto_Susan//SistemaInterno//giview//src//main//webapp//WEB-INF//applicationContext.xml");
        ApplicationContext context = new FileSystemXmlApplicationContext("//home//chano//sipi_v1.0//SistemaInterno//giview//src//main//webapp//WEB-INF//applicationContext.xml");
        CorrelativoService correlativoService = (CorrelativoService) context.getBean("correlativoService");
         RenTipoSignoService renTipoSignoService = (RenTipoSignoService) context.getBean("renTipoSignoService");
    
         
         
         int registro = 1;
         int modificacion = 2;
        RenTipoSigno renTipoSigno = new RenTipoSigno();
        
        
        
        renTipoSigno.setIdtiposigno(3L);
        renTipoSigno.setDescripcion_otro("prueba de imagenes modificado");
        renTipoSigno.setIdsolicitudrenovacion(1L);
        renTipoSigno.setIdlogtrans(1L);
        renTipoSigno.setEstado("AC");
        System.out.println("entro al metodo de guardado de conrrelativo");

//        System.out.println("se guardo correctamente id:::::::::::" + renTipoSignoService.crudRenTipoSigno(renTipoSigno,modificacion).getIdtiposigno());

//     
        
        List<RenTipoSigno>lista=renTipoSignoService.buscaRenTipoSignoPorIdSolicitud(2L);
        for (RenTipoSigno renTipoSigno1 : lista) {
            System.out.println("id"+renTipoSigno1.getIdtiposigno());
            
        }
        
        
        
    }
}
