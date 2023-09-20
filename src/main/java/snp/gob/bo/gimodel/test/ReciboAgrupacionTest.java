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
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import snp.gob.bo.gimodel.entidad.ReciboAgrupacion;
import snp.gob.bo.gimodel.entidad.ReciboPorPeriodo;
import snp.gob.bo.gimodel.servicio.ReciboAgrupacionService;

/**
 *
 * @author Ruben Ramirez
 */
public class ReciboAgrupacionTest {

    public static void main(String[] args) throws Exception {
        ApplicationContext context = new FileSystemXmlApplicationContext("//home//ruben//NetBeansProjects//hidra//giview//src//main//webapp//WEB-INF//applicationContext.xml");
        ReciboAgrupacionService reciboAgrupacionService = (ReciboAgrupacionService) context.getBean("reciboAgrupacionService");

        try {
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String ini = "2017-04-28 00:00:00";
            String fin = "2017-05-31 23:59:59";
            Date fechaini = formato.parse(ini);
            Date fechafin = formato.parse(fin);


            List<ReciboPorPeriodo> lista = reciboAgrupacionService.listaReciboPorPeriodo(fechaini,fechafin ,"USU", 82L);
            int i =0;
            for (ReciboPorPeriodo item : lista) {
                i++;
                System.out.println(i+"      "+item.getFecha()+"       "+item.getConcepto());
            }

        } catch (Exception ex) {
            Logger.getLogger(SeccionSubPublicacionTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
