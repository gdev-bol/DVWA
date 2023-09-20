/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.test;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import snp.gob.bo.gimodel.entidad.SigDetallePublicacion;
import snp.gob.bo.gimodel.entidad.SigSignoMarca;
import snp.gob.bo.gimodel.servicio.ComunService;
import snp.gob.bo.gimodel.servicio.SigDetallePublicacionService;
import snp.gob.bo.gimodel.servicio.SigSignoMarcaService;

/**
 *
 * @author susana
 */
public class SigDetallePublicacionTest {

    public static void main(String[] args) throws Exception {
//        ApplicationContext context = new FileSystemXmlApplicationContext("//home//susana//Proyecto_Susan//SistemaInterno//giview//src//main//webapp//WEB-INF//applicationContext.xml");
        ApplicationContext context = new FileSystemXmlApplicationContext("//home//tenoch//NetBeansProjects//hidra_desarrollo//hidra//giview//src//main//webapp//WEB-INF//applicationContext.xml");
        SigDetallePublicacionService sigDetallePublicacionService = (SigDetallePublicacionService) context.getBean("sigDetallePublicacionService");
        SigSignoMarcaService sigSignoMarcaService = (SigSignoMarcaService) context.getBean("sigSignoMarcaService");
        ComunService comunService = (ComunService) context.getBean("comunService");

        String fecha = "2017-11-30";
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaF = null;
        try {
            fechaF = formato.parse(fecha);
        } catch (ParseException ex) {
            Logger.getLogger(SigDetallePublicacionTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        List<SigDetallePublicacion> listaTitularRegistrado = sigDetallePublicacionService.lista_generarSigDetallePublicacion(fechaF, "0");
        System.out.println("listaTitular"+listaTitularRegistrado.size());
        List<SigSignoMarca>listaValidos = new ArrayList<>();
        List<SigSignoMarca>listanoValidos = new ArrayList<>();
       
        for (SigDetallePublicacion listaTitularRegistrado1 : listaTitularRegistrado) {
            SigSignoMarca sigiSigSignoMarca = sigSignoMarcaService.listaSigSignoMarcaXSM(listaTitularRegistrado1.getSm());
            if (listaTitularRegistrado1 != null) {
                if (listaTitularRegistrado1.getSm()!= null) {
                    HashMap mapResultado = comunService.obtenerNumeroGestionExtensionCodigoSM(listaTitularRegistrado1.getSm());
                    Long numeroSM = Long.parseLong(mapResultado.get("numero").toString());
                    Long gestionSM = Long.parseLong(mapResultado.get("gestion").toString());
                    String extensionSM = mapResultado.get("extension").toString();
                    
                    if ((numeroSM==Long.parseLong(sigiSigSignoMarca.getNumero().toString())) && (gestionSM==Long. parseLong(sigiSigSignoMarca.getGestion().toString()))) {
                        listaValidos.add(sigiSigSignoMarca);
                    }else{
                        listanoValidos.add(sigiSigSignoMarca);
                    }
                }
            }
        }
        System.out.println("lista validos"+listanoValidos);
        if(!listanoValidos.isEmpty()){
            for (SigSignoMarca listanoValido : listanoValidos) {
                System.out.println("sm="+listanoValido.getSm()+"--"+listanoValido.getNumero()+"-"+listanoValido.getGestion());
            }
        }
        if(!listanoValidos.isEmpty()){
            for (SigSignoMarca listaValido : listaValidos) {
                System.out.println("sm="+listaValido.getSm()+"--"+listaValido.getNumero()+"-"+listaValido.getGestion());
            }
        }
    }
}
