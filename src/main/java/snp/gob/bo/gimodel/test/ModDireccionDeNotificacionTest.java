/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import snp.gob.bo.gimodel.entidad.ModDireccionDeNotificacion;
import snp.gob.bo.gimodel.servicio.ModDireccionDeNotificacionService;

/**
 *
 * @author susana
 */
public class ModDireccionDeNotificacionTest {

    public static void main(String[] args) {
        ApplicationContext context = new FileSystemXmlApplicationContext("//home//susana//Proyecto_Susan//SistemaInterno//giview//src//main//webapp//WEB-INF//applicationContext.xml");
        ModDireccionDeNotificacionService modDireccionDeNotificacionService = (ModDireccionDeNotificacionService) context.getBean("modDireccionDeNotificacionService");

        ModDireccionDeNotificacion moddirecciondenotificacion = new ModDireccionDeNotificacion();

        moddirecciondenotificacion.setIdmodificacion(1l);
        moddirecciondenotificacion.setIdlogtrans(1l);
        moddirecciondenotificacion.setCiudad_notificacion("LPZ");
        moddirecciondenotificacion.setZona_barrio("zona_barrio");
        moddirecciondenotificacion.setAvenida_calle("avenida_calle");
        moddirecciondenotificacion.setNumero("numero");
        moddirecciondenotificacion.setEdificio("edificio");
        moddirecciondenotificacion.setPiso("piso");
        moddirecciondenotificacion.setDepartamento("depa10");
        moddirecciondenotificacion.setCorreo_electronico("correo_electronico");
        moddirecciondenotificacion.setReferencia_direccion("referencia_direcciom");
        moddirecciondenotificacion.setTelefono("telefono");
        moddirecciondenotificacion.setCelular("celular");
        moddirecciondenotificacion.setEstado("ac");

        //  ModDireccionDeNotificacion nuevo = modDireccionDeNotificacionService.guardar_modificar_listar_ModDireccionDeNotificacion(moddirecciondenotificacion, 1l, 1);
        //   System.out.println("NUEVO ES "+nuevo.getIddirecciondenotificacion());
        
        ModDireccionDeNotificacion nuevo = modDireccionDeNotificacionService.buscarModDireccionDeNotificacionXidmodificacion(5L);
        System.out.println("NUEVO ES "+nuevo.getIddirecciondenotificacion());
//        String direccion = modDireccionDeNotificacionService.datos_notificacion("LPZ","zonabarrio", "avcalle", "numero", "referencia", "edificio", "piso", "dpto", "correo");
//        
//        System.out.println("DIRECCION "+direccion);
//        
//        
//        String celular = modDireccionDeNotificacionService.datos_celular("LPZ","zonabarrio");
//        
//        System.out.println("celular "+celular);

    }
}
