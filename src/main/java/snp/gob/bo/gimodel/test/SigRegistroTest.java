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
import snp.gob.bo.gimodel.entidad.BusquedaSigRegistro;
import snp.gob.bo.gimodel.entidad.ModModificacion;
import snp.gob.bo.gimodel.entidad.SigRegistro;
import snp.gob.bo.gimodel.servicio.SigRegistroService;

/**
 *
 * @author susana
 */
public class SigRegistroTest {

    public static void main(String[] args) {
        ApplicationContext context = new FileSystemXmlApplicationContext("//home//susana//Proyecto_Susan//SistemaInterno//giview//src//main//webapp//WEB-INF//applicationContext.xml");
        SigRegistroService sigRegistroService = (SigRegistroService) context.getBean("sigRegistroService");

        SigRegistro registro = new SigRegistro();
        registro.setIdRegistro(null);
        registro.setIdSignoMarca(30839l);
        registro.setIdLogTrans(1l);
        registro.setNumeroRegistro(1l);
        registro.setSerie("C");
        registro.setSm(456465456L);
        registro.setSmDescripcion("DESCRipcionSM");
        registro.setEstadoRegistro("ACEP");
        registro.setFechaIngreso(new Date());
        registro.setIdTipoResolucion(1);
        registro.setDocumentoResolucion("");
        registro.setSigno("SIGNO");
//        registro.setTipoSignoDescripcion();
//        registro.setTipoGeneroDescripcion();
//        registro.setClase();
//        registro.setNumeroResolucion();
//        registro.setGestionResolucion();
//        registro.setFechaRegistro();
//        registro.setNumeroPublicacion();
//        registro.setFechaPublicacion();
//        registro.setNumeroGaceta();
//        registro.setFechaPrimerUso();
//        registro.setTitular();
//        registro.setDireccionTitular();
//        registro.setPaisTitularDescripcion();
//        registro.setNombreApoderado();
        registro.setDireccionApoderado("DIRECCION");
        registro.setListaProductos("LISTA");
        registro.setObservacion("MI OBSERVACION SU");
        registro.setEstado("AN");
//
//        SigRegistro nuevo = sigRegistroService.guardar_modificar_listar_SigRegistro(registro, 1);
//        System.out.println("nuevo   " + nuevo.getIdRegistro());
        //     SigRegistro registroS = sigRegistroService.listar_sigRegistro_numero(9434l, "C");
        //     System.out.println("nuevo   " + registroS);

        String SQL = ""
                + "select "
                + "sig.idsignomarca, "
                + "sig.sm, "
                + "sig.signo as marca, "
                + "ssc.numero_clase_niza as claseniza, "
                + "sig.fecha_solicitud as fecha_solicitud, "
                + "sig.numero_publicacion as numero_publicacion, "
                + "sig.numero_registro as numero_registro, "
                + "soap.nombres solicitante, "
                + "apo.nombres apoderado, "
                + "dom.nombre as estado, "
                + "sig.fecha_registro as fecha_registro, "
                + "sig.fecha_renovacion as fecha_renovacion, "
                + "reno.titular as titular, "
                + "reno.numero_renovacion as sr, "
                + "renso.gestion_sr as gestion_sr "
                + "from sigsignomarca sig "
                + " join dominio dom "
                + " on "
                + "    dom.dominio = 'estado_marca' "
                + "join sigsignoclaseniza ssc "
                + "on ssc.idsignomarca = sig.idsignomarca "
                + "join ( "
                + "  select idsignomarca, string_agg(concat(nombre_razonsocial, primer_apellido, segundo_apellido), '; ') nombres "
                + "  from sigsolicitanteapoderado sol "
                + "  where sol.idsignomarca = idsignomarca "
                + "  and sol.tipo_persona = 'SOLI' "
                + "  and sol.estado = 'AC' "
                + "  group by 1 "
                + "  ) soap "
                + "on ( "
                + "sig.idsignomarca = soap.idsignomarca "
                + ") "
                + "join ( "
                + "  select idsignomarca, string_agg(concat(nombre_razonsocial, primer_apellido, segundo_apellido), '; ') nombres "
                + "  from sigsolicitanteapoderado apod "
                + "  where apod.idsignomarca = idsignomarca "
                + "  and apod.tipo_persona = 'APOD' "
                + "  and apod.estado = 'AC' "
                + "  group by 1 "
                + "  ) apo "
                + "on ( "
                + "sig.idsignomarca = apo.idsignomarca "
                + ") "
                + "full outer join "
                + "rensolicitudrenovacion renso "
                + "on ( "
                + "renso.sm = sig.sm "
                + ") "
                + "full outer join "
                + "renrenovacion reno "
                + "on( "
                + "reno.idsolicitudrenovacion = renso.idsolicitudrenovacion "
                + ") "
                + "where "
                //      + consultaCondicion
                + "  and dom.codigo = sig.estado_marca "
                + "  and dom.estado = 'AC' "
                + "  and sig.estado = 'AC'"
                + ""
                + "";
        //      System.out.println("SQL    "+SQL);

//        Boolean validador[] = sigRegistroService.verificaRegistroResolucionExistente(1l, "C", 9999L, 2016);
//        
//        System.out.println("000000000000    "+validador[0]);
//        System.out.println("111111111111    "+validador[1]);
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
//        try {
//            Date nuevaFecha = sdf.parse("21-04-2017");
//            System.out.println("N   " + new Date());
//            System.out.println("FECHA   " + nuevaFecha);
//
//            List<BusquedaSigRegistro> listaRegistro = sigRegistroService.lista_SigRegistro_avanzada(nuevaFecha, nuevaFecha);
//            System.out.println("listarRegistro   " + listaRegistro.size());
//            for (BusquedaSigRegistro item : listaRegistro) {
//                System.out.println("valor  " + item.getPosicion() + " ---  " + item.getSigno());
//            }
//        } catch (ParseException pe) {
//        }
        System.out.println("ULTIMO NUMERO  DE REGISTRO   "+sigRegistroService.lista_UltimoRegistro_gestion());
    }
}
