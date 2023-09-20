/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.test;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import snp.gob.bo.gimodel.entidad.Etapa;
import snp.gob.bo.gimodel.entidad.SigSignoMarca;
import snp.gob.bo.gimodel.enums.EnumTipoTramite;
import snp.gob.bo.gimodel.servicio.EtapaService;
import snp.gob.bo.gimodel.servicio.SigSignoMarcaService;

/**
 *
 * @author Ruben Ramirez
 * @version 1.0,
 */
public class EtapaTest {

    public static void main(String[] args) {

//      ApplicationContext context = new FileSystemXmlApplicationContext("//home//ruben//NetBeansProjects//SistemaInterno//giview//src//main//webapp//WEB-INF//applicationContext.xml");
        ApplicationContext context = new FileSystemXmlApplicationContext("//home//susana//Proyecto_Susan//SistemaInterno//giview//src//main//webapp//WEB-INF//applicationContext.xml");
        EtapaService etapaService = (EtapaService) context.getBean("etapaService");

        List<Etapa> listEtapa = new ArrayList<Etapa>();

//        try {
//            listEtapa = etapaService.listadoPorIdUsuario(3L);
//            for (Etapa etapa : listEtapa) {
//                System.out.println(" *** " + etapa.getIdEtapa()); 
//            }
//        } catch (Exception ex) {
//            Logger.getLogger(BusquedaMarcaResultadoTest.class.getName()).log(Level.SEVERE, null, ex);
//        }
        
        Etapa encontrado = etapaService.listar_etapa_tipoTramite_tipoEtapa(EnumTipoTramite.REGISTRO_MARCAS.getCodigo(),"PPP");
        System.out.println("ENCONTRADO   "+encontrado.getDescripcion());
    }

}
