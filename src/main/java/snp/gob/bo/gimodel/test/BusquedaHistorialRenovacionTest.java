/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.test;

import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import snp.gob.bo.gimodel.entidad.BusquedaHistorialRenovacion;
import snp.gob.bo.gimodel.entidad.BusquedaModificacionResultado;
import snp.gob.bo.gimodel.servicio.BusquedaHistorialRenovacionService;
import snp.gob.bo.gimodel.servicio.BusquedaModificacionResultadoService;

/**
 *
 * @author susana
 */
public class BusquedaHistorialRenovacionTest {
    public static void main(String[] args) {
        ApplicationContext context = new FileSystemXmlApplicationContext("//home//sushy//Proyecto_Susan//hidra//giview//src//main//webapp//WEB-INF//applicationContext.xml");        
        BusquedaHistorialRenovacionService busquedaHistorialRenovacionService = (BusquedaHistorialRenovacionService) context.getBean("busquedaHistorialRenovacionService");        
        List<BusquedaHistorialRenovacion> lista = busquedaHistorialRenovacionService.lista_HistorialRenovacion_sm_publicacion_registro_or(200100422200l, 102910l, 95680l, "C");
//        for (BusquedaHistorialRenovacion item : lista) {
//            System.out.println("ITEMMMM "+item.getFecha_resolucion() +"  ACTUAL   "+item.getResolucion_renovacion() +" ---- "+item.getIdmodificacion());
//        }     
        
        String literal = busquedaHistorialRenovacionService.ordenModificacionLiteral(2, 2);
        System.out.println("LITERAL");
        
        
    }
}
