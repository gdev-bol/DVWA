package snp.gob.bo.gimodel.test;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import snp.gob.bo.gimodel.entidad.CorrelativoRegional;
import snp.gob.bo.gimodel.entidad.Dominio;
import snp.gob.bo.gimodel.servicio.CorrelativoRegionalService;
import snp.gob.bo.gimodel.servicio.CorrelativoService;
import snp.gob.bo.gimodel.servicio.DominioService;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author eddy
 */
public class CorrelativoRegionalTest {

    public static void main(String[] args) throws Exception {

//        ApplicationContext context = new FileSystemXmlApplicationContext("//home//susana//Proyecto_Susan//SistemaInterno//giview//src//main//webapp//WEB-INF//applicationContext.xml");
        ApplicationContext context = new FileSystemXmlApplicationContext("//home//chano//sipi_v1.0//SistemaInterno//giview//src//main//webapp//WEB-INF//applicationContext.xml");
     
        CorrelativoRegionalService correlativoRegionalService=(CorrelativoRegionalService)context.getBean("correlativoRegionalService");

//           CorrelativoService correlativoService = (CorrelativoService) context.getBean("correlativoService");
        int parametro = 2;
        CorrelativoRegional correlativo = new CorrelativoRegional();
        correlativo.setIdcorrelativoregional(3L);
        correlativo.setIdregional(1L);
        correlativo.setIdcorrelativo(1L);
        correlativo.setIdlogtrans(1L);
        correlativo.setTipo_tramite("RENO");
        correlativo.setEstado("NC");
        System.out.println("entro al metodo de guardado de conrrelativo");

        System.out.println("se guardo correctamente id:::::::::::" + correlativoRegionalService.crudCorrelativoRegional(correlativo, parametro).getIdcorrelativoregional());
        
        }
}
