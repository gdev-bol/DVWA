/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import snp.gob.bo.gimodel.entidad.BusquedaModificacionResultado;
import snp.gob.bo.gimodel.entidad.ModModificacion;
import snp.gob.bo.gimodel.servicio.ModModificacionService;

/**
 *
 * @author susana
 */
public class ModModificacionTest {

    public static void main(String[] args) throws Exception {
        ApplicationContext context = new FileSystemXmlApplicationContext("//home//susana//Proyecto_Susan//SistemaInterno//giview//src//main//webapp//WEB-INF//applicationContext.xml");
        //ApplicationContext context = new FileSystemXmlApplicationContext("//home//chano//sipi_v1.0//SistemaInterno//giview//src//main//webapp//WEB-INF//applicationContext.xml");

        ModModificacionService modModificacionService = (ModModificacionService) context.getBean("modModificacionService");

        ModModificacion modmodificacion = new ModModificacion();
        modmodificacion.setIdmodificacion(null);
        modmodificacion.setIdlogtrans(1l);
        modmodificacion.setSigla("CN");
        modmodificacion.setNumero(123l);
        modmodificacion.setGestion(2016);
        modmodificacion.setSm(2146565l);
        modmodificacion.setFecha_ingreso(new Date());
        modmodificacion.setNro_recibo(963l);
        modmodificacion.setSerie_recibo("A-01");
        modmodificacion.setNro_formulario("46643500");
        modmodificacion.setOficina("ofic");
        modmodificacion.setNumero_registro(11321l);
        modmodificacion.setSerie_registro("C");
        modmodificacion.setNumero_renovacion(784L);
        modmodificacion.setSerie_renovacion("A");
        modmodificacion.setNumero_publicacion(7878L);
        modmodificacion.setSigno_registrado("signo_registrado");
        modmodificacion.setIdclase_registrado(28L);
        modmodificacion.setTipo_genero_registrado("gene");
//
//        modmodificacion.setMarca_acomp("marca_acomp");
//        modmodificacion.setReg_lc_registrado(64544L);
//        modmodificacion.setSerie_lc_registrado("W");
//        modmodificacion.setFecha_lc_registrado(new Date());
//        modmodificacion.setNuevo_domicilio("nuevo_domicilio");
//        modmodificacion.setNueva_nacionalidad("naci");
//        modmodificacion.setNuevo_departamento("depa");
//        modmodificacion.setLuinicio(new Date());
//        modmodificacion.setLu_fin(new Date());
//        modmodificacion.setFecha_ultima_operacion(new Date());
//        modmodificacion.setUsuario(1l);
//        modmodificacion.setTitular_signo("titular_signo");
//        modmodificacion.setNacionalidad_signo("naci");
//        modmodificacion.setDepartamento_signo("depa");
//        modmodificacion.setDomicilio_signo("domicilio_signo");
//        modmodificacion.setTelefono_signo("telefono_signo");
//        modmodificacion.setFax_signo("fax_signo");
//        modmodificacion.setEmail_signo("email_signo");
//        modmodificacion.setTipo_modificacion("tipo");
//        modmodificacion.setEstado_modificacion("ACEP");
//        modmodificacion.setUbicacion_modificacion("ubiC");
//        modmodificacion.setLista_producto("lista_producto CREADO");
//        modmodificacion.setEstado("AC");

        //    modModificacionService.guardar_modificar_listar_ModModificacion(modmodificacion, 4);
//        ModModificacion nuevo2 = modModificacionService.guardar_modificar_listar_ModModificacion(modmodificacion, 1);
//        System.out.println("ENCONTRADO ------  " + nuevo2.getLista_producto());
//        ModModificacion nuevo = modModificacionService.buscarModModificacionXid(8l);
//        nuevo.setEstado_modificacion("SSSS");
//        System.out.println("encontradoo   " + nuevo.getEstado_modificacion());
//        ModModificacion nuevo = modModificacionService.buscarModModificacionXid(19l);
//        System.out.println("encontradoo   "+nuevo.getEstado_modificacion());
//        ModModificacion nuevo = modModificacionService.buscarModModificacionXtipo("CATR", 1L, 2016);
//        System.out.println("encontradoo   " + nuevo.getIdmodificacion());
//        Boolean valor[] = modModificacionService.existe_ModModificacion_sm_publicacion_registro_or(200800052700l, 0l, 140573l, "C", "CATR");
//        System.out.println("BUSCANDOO ------  " + valor[0]+"  %%%%%%%%%%%%   "+valor[1]);
//         List<ModModificacion> listaModificacion = modModificacionService.lista_ModModificacion_sm_publicacion_registro_or(200800052700l, 128836l, 140573l, "C");
//         System.out.println("listaModificacion   "+listaModificacion.size());
//         for (ModModificacion listaModificacion1 : listaModificacion) {
//             System.out.println("---- "+listaModificacion1.getSigla());
//        }
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        try {
            Date nuevaFecha = sdf.parse("01-04-2017");
            Date nuevaFecha2 = sdf.parse("24-04-2017");
            System.out.println("N   " + new Date());
            List<BusquedaModificacionResultado> listaModificacion = modModificacionService.lista_modmodificacion_avanzada2("CANO", "ACEP", nuevaFecha, nuevaFecha2, "");
            System.out.println("listaModificacion   " + listaModificacion.size());
            for (BusquedaModificacionResultado listaModificacion1 : listaModificacion) {
                System.out.println("---- " + listaModificacion1.getSigla());
            }
        } catch (ParseException pe) {
        }

    }
}
