/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.test;

import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import snp.gob.bo.gimodel.entidad.BusquedaModificacionResultado;
import snp.gob.bo.gimodel.servicio.BusquedaModificacionResultadoService;

/**
 *
 * @author susana
 */
public class BusquedaModificacionResultadoTest {
    public static void main(String[] args) {
        ApplicationContext context = new FileSystemXmlApplicationContext("//home//susana//Proyecto_Susan//SistemaInterno//giview//src//main//webapp//WEB-INF//applicationContext.xml");
        
        BusquedaModificacionResultadoService busquedaModificacionResultadoService = (BusquedaModificacionResultadoService) context.getBean("busquedaModificacionResultadoService");
        
        List<BusquedaModificacionResultado> lista = busquedaModificacionResultadoService.lista_BusquedaModificacionResultado_sm_publicacion_registro_or(200100422200l, 102910l, 95680l, "  C");
        for (BusquedaModificacionResultado item : lista) {
            System.out.println("ITEMMMM "+item.getNuevo_titular() +"  ACTUAL   "+item.getSolicitante() +" ---- "+item.getIdmodificacion());
        }
        
        System.out.println("hola");

        
    }
}
