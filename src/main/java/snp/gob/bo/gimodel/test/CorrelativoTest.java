package snp.gob.bo.gimodel.test;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import snp.gob.bo.gimodel.entidad.Correlativo;
import snp.gob.bo.gimodel.entidad.Dominio;
import snp.gob.bo.gimodel.enums.EnumTipoTramite;
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
public class CorrelativoTest {

    public static void main(String[] args) throws Exception {

//        ApplicationContext context = new FileSystemXmlApplicationContext("//home//susana//Proyecto_Susan//SistemaInterno//giview//src//main//webapp//WEB-INF//applicationContext.xml");
        ApplicationContext context = new FileSystemXmlApplicationContext("//home//chano//sipi_v1.0//SistemaInterno//giview//src//main//webapp//WEB-INF//applicationContext.xml");
        CorrelativoService correlativoService = (CorrelativoService) context.getBean("correlativoService");
        int parametro = 2;
        Correlativo correlativo = new Correlativo();
        correlativo.setIdcorrelativo(8L);
        correlativo.setIdlogtrans(1L);
        correlativo.setNombre_criterio("abcd");
        correlativo.setIncremento(1);
        correlativo.setUltimo_numero_asignado(1);
        correlativo.setAcronimo("AB");
        correlativo.setGestion(2016);
        correlativo.setSeparador("/");
        correlativo.setEstado("AC");
        System.out.println("entro al metodo de guardado de conrrelativo");

//        System.out.println("se guardo correctamente id:::::::::::" + correlativoService.crudCorrelativo(correlativo, parametro).getIdcorrelativo());
        System.out.println("correlativo     " + correlativoService.encuentraCorrelativoTabla(1L, "RENR").getIdcorrelativo());
        System.out.println("correlativo     " + correlativoService.encuentraCorrelativoTabla(1L, "RERE").getIdcorrelativo());

//     
    }
}
