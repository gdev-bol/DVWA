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
import snp.gob.bo.gimodel.entidad.Seguimiento;
import snp.gob.bo.gimodel.servicio.SeguimientoService;

/**
 *
 * @author Eddy Valero
 * @version 1.0, 08/09/2016
 */
public class SeguimientoTest {

    public static void main(String[] args) throws Exception {
    int dias;
      //  try {
            //ApplicationContext context = new FileSystemXmlApplicationContext("//home//eddy//NetBeansProjects//borrar//SistemaInterno//giview//src//main//webapp//WEB-INF//applicationContext.xml");
            ApplicationContext context = new FileSystemXmlApplicationContext("//home//levi//Documentos//PROYECTOS//ProyectoGIT//hidra//giview//src//main//webapp//WEB-INF//applicationContext.xml");
            
            SeguimientoService sigSeguimientoService = (SeguimientoService) context.getBean("seguimientoService");
            
            
//            List<Seguimiento> segui=sigSeguimientoService.listaSeguimientoXSM(201600593300L,"RECA");
//            System.out.println("tam lista"+segui.size());
//            for(int i=0;i<=segui.size()-1;i++)
//            {    
//                System.out.println("IdSeguimietno"+segui.get(0).getIdSeguimiento());
//            
            
             SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            String dateInString = "12/18/2017";
            
            System.out.println("dias::"+sigSeguimientoService.cuentaDiasPasivo(formatter.parse(dateInString),
                                                                               "SIG",
                                                                               164252L,
                                                                               1L));

            
//            }
         /*   SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            String dateInString = "15/12/2016";
            
            SimpleDateFormat formatter1 = new SimpleDateFormat("dd/MM/yyyy");
            String dateFinString = "22/12/2016";
            
            sigSeguimientoService.registraSeguimientoSignos(11111L, //El id ya sea de signos, modificacion o renovacion
                                                            139L,//idusuario actual
                                                            1L,
                                                            201611111L,//codigo sm
                                                            "VENT",//etapa
                                                            formatter.parse(dateInString),//fecha recepcion
                                                            formatter.parse(dateFinString),//fehca fin
                                                            "Observacioncita",//observaicon
                                                            "AC"//estado
                                                             );
                                                                                                                         
           */ 
            
            
         /*dias=sigSeguimientoService.diasLaboralesSigno(formatter.parse(dateInString), formatter.parse(dateFinString), 1L);
            System.out.println("dias:"+dias);
           */ 
            
            
            
            
//        } catch (ParseException ex) {
//            Logger.getLogger(SeguimientoTest.class.getName()).log(Level.SEVERE, null, ex);
//        }
        

        
       

    }

}
