/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.test;

import java.util.ArrayList;
import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import snp.gob.bo.gimodel.entidad.Procurador;
import snp.gob.bo.gimodel.entidad.TramiteEntrega;
import snp.gob.bo.gimodel.entidad.Usuario;
import snp.gob.bo.gimodel.servicio.EntregaTramiteService;
import snp.gob.bo.gimodel.servicio.ProcuradorService;
import snp.gob.bo.gimodel.servicio.UsuarioService;

/**
 *
 * @author chano Rojas
 */
public class EntregaTramiteTest {

    public static void main(String[] args) throws Exception {

        ApplicationContext context = new FileSystemXmlApplicationContext("//home//tenoch//NetBeansProjects//hidra_desarrollo//hidra//giview//src//main//webapp//WEB-INF//applicationContext.xml");
        EntregaTramiteService entregaTramiteService = (EntregaTramiteService) context.getBean("entregaTramiteService");
        UsuarioService usuarioService = (UsuarioService) context.getBean("usuarioService");
        ProcuradorService procuradorService=(ProcuradorService)context.getBean("procuradorService");

//        Procurador procurador = new Procurador();
//        procurador.setNombre_razonsocial("MIRSA");
//        procurador.setPrimer_apellido("MORALES");
//        procurador.setSegundo_apellido("GUTI");
//        procurador.setLugar_expedicion("LPZ");
//        procurador.setTipo_titular("NAT");
//        procurador.setTipo_documento("CI");
//        procurador.setLugar_expedicion("LPZ");
//        procurador.setTelefono("78867731");
//        procurador.setCelular("2232054");
//        List<TramiteEntrega> lista = entregaTramiteService.listaBuscador(
//                "SOAP",
//                "SR",
//                525,
//                2017,
//                "C",
//                "APOD",
//                "LANDIVAR",
//                154895,
//                "A",
//                265841);
//        System.out.println("lista" + lista.size());
//        List<TramiteEntrega> listaAuxiliar = new ArrayList<>();
//        for (int i = 0; i < 5; i++) {
//            listaAuxiliar.add(lista.get(i));
//        }
//        for (TramiteEntrega lista1 : listaAuxiliar) {
//            System.out.println("" + lista1.getMarca() + "" + lista1.getSigla() + "-" + lista1.getNumerotramite() + "-" + lista1.getGestion() + "-->" + lista1.getClase_niza());
//        }
//      
//        
//        Usuario usuario = usuarioService.buscaUsuarioPorIdUsuario(29l);
//        System.out.println("ingreso metodo de entrega");
//        TramiteEntrega entrega=entregaTramiteService.guardaTramiteEntrega(procurador, listaAuxiliar, "ENTR", usuario);
//        System.out.println("tramite"+entrega.getIdtramiteentrega());
//        System.out.println("numero"+entrega.getNumerobloque());
//        System.out.println("gestion"+entrega.getGestionbloque());
//        
        
        
//        List<TramiteEntrega> listbase = entregaTramiteService.tramitesPorBloque(3, 2017, 4L);
//        System.out.println("lista" + listbase.size());
//
//        List<TramiteEntrega> listaAuxiliar = new ArrayList<>();
//        for (int i = 0; i < 3; i++) {
//            listaAuxiliar.add(listbase.get(i));
//        }
//        Procurador procurador=procuradorService.procuradorPorId(53l);
//        System.out.println("procurador"+procurador.getNombre_razonsocial());     
//        procurador.setNombre_razonsocial("modificado");
//        procuradorService.guardar_modificar_listar_Procurador(procurador,2);
           
//        System.out.println("metodo valida"+entregaTramiteService.verificaTramiteEntrega(141260L, "SIG"));
//        TramiteEntrega tramiteEntrega=entregaTramiteService.modificaTramiteEntrega(procurador,listaAuxiliar,"ENTR",usuario);
//        System.out.println("tramite entrega"+tramiteEntrega);
        
       
        
        
        
          System.out.println("metodo valida"+entregaTramiteService.verificaTramiteEntregaOtros("BQ",4545455,2017));
         
        
    }

}
