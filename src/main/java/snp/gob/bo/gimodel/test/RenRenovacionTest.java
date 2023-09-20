/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import snp.gob.bo.gimodel.entidad.RenRenovacion;
import snp.gob.bo.gimodel.servicio.RenRenovacionService;
import snp.gob.bo.gimodel.servicio.UsuarioPaginaService;

/**
 *
 * @author levi
 */
public class RenRenovacionTest {

    public static void main(String[] args) throws Exception {
//      ApplicationContext context = new FileSystemXmlApplicationContext("//home//levi//Documentos//PROYECTOS//SISInterno//SistemaInterno//giview//src//main//webapp//WEB-INF//applicationContext.xml");
        ApplicationContext context = new FileSystemXmlApplicationContext("//home//chano//sipi_v1.0//SistemaInterno//giview//src//main//webapp//WEB-INF//applicationContext.xml");
//        ApplicationContext context = new FileSystemXmlApplicationContext("//home//rojas//sipi_v1.0//SistemaInterno//giview//src//main//webapp//WEB-INF//applicationContext.xml");

        UsuarioPaginaService servicio = (UsuarioPaginaService) context.getBean("usuarioPaginaService");
        RenRenovacionService renRenovacionService = (RenRenovacionService) context.getBean("renRenovacionService");

//        RenRenovacion renRenovacion = new RenRenovacion();
//        int parametro = 4;
             // List<ListaModificacion> lista =listaModificacionService.listadoListaModificacion();
        //System.out.println("Tam lista:"+lista.size());
        // for(int i=0;i<=lista.size()-1;i++)
        // {
        //     System.out.println("Tramite:: "+lista.get(i).getTramite());
        // }    
        /**
         * ********
         */
//        DateFormat format1 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
//        Date dateI = format1.parse("23-06-1988 00:00:00");

        DateFormat formatoooo = new SimpleDateFormat("dd/MM/yyyy");
        Date date1 = formatoooo.parse("1998-06-23 00:00:00.0");
        System.out.println("date1"+date1);
        

//        System.out.println("numero de renovacion" + (renRenovacionService.calculaNumeroDeRenovacion(dateI)));

//        System.out.println("obtiene renovacion"+renRenovacionService.obtieneRenRenovacionPorNumeroYSerieRenovacion(123, "A").getIdrenovacion());
    }
}
