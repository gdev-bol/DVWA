package snp.gob.bo.gimodel.test;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import snp.gob.bo.gimodel.entidad.Correlativo;
import snp.gob.bo.gimodel.entidad.Dominio;
import snp.gob.bo.gimodel.entidad.ModTipoSigno;
import snp.gob.bo.gimodel.entidad.RenTipoSigno;
import snp.gob.bo.gimodel.servicio.CorrelativoService;
import snp.gob.bo.gimodel.servicio.DominioService;
import snp.gob.bo.gimodel.servicio.ModTipoSignoService;
import snp.gob.bo.gimodel.servicio.RenTipoSignoService;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author chano rojas
 */
public class ModTipoSignoTest {

    public static void main(String[] args) throws Exception {

        ApplicationContext context = new FileSystemXmlApplicationContext("//home//susana//Proyecto_Susan//SistemaInterno//giview//src//main//webapp//WEB-INF//applicationContext.xml");
        //ApplicationContext context = new FileSystemXmlApplicationContext("//home//chano//sipi_v1.0//SistemaInterno//giview//src//main//webapp//WEB-INF//applicationContext.xml");
        CorrelativoService correlativoService = (CorrelativoService) context.getBean("correlativoService");
         ModTipoSignoService modTipoSignoService = (ModTipoSignoService) context.getBean("modTipoSignoService");
    
         
         
         int registro = 1;
         int modificacion = 2;
        ModTipoSigno modTipoSigno = new ModTipoSigno();
        modTipoSigno.setIdtiposigno(3L);
        modTipoSigno.setDescripcion_otro("prueba de imagenes modificadofdsfdsfdsfdsfsd");
        modTipoSigno.setIdmodificacion(1L);
        modTipoSigno.setTipo_signo("LC");
        modTipoSigno.setIdlogtrans(1L);
        modTipoSigno.setEstado("AC");
        System.out.println("entro al metodo de guardado de conrrelativo");

    //    System.out.println("se guardo correctamente id:::::::::::" + modTipoSignoService.guardar_modificar_listar(modTipoSigno,modificacion).getIdtiposigno());
        
        List<ModTipoSigno> listaTipoSigno = modTipoSignoService.listado_modtiposignoXidmodificacion(4L);
        for (ModTipoSigno item : listaTipoSigno) {
            System.out.println("valores   "+item.getTipo_signo());
        }

//     
    }
}
