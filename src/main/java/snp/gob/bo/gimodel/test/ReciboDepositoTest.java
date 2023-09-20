/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.test;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import snp.gob.bo.gimodel.entidad.Recibo;
import snp.gob.bo.gimodel.entidad.ReciboDeposito;
import snp.gob.bo.gimodel.servicio.ReciboDepositoService;
import snp.gob.bo.gimodel.servicio.ReciboService;
import snp.gob.bo.gimodel.servicio.RenSolicitudRenovacionService;

/**
 *
 * @author levi
 */
public class ReciboDepositoTest {

    public static void main(String[] args) throws Exception {
//      ApplicationContext context = new FileSystemXmlApplicationContext("//home//levi//Documentos//PROYECTOS//SISInterno//SistemaInterno//giview//src//main//webapp//WEB-INF//applicationContext.xml");
        ApplicationContext context = new FileSystemXmlApplicationContext("//home//chano//sipi_v1.0//SistemaInterno//giview//src//main//webapp//WEB-INF//applicationContext.xml");

//        UsuarioPaginaService servicio = (UsuarioPaginaService) context.getBean("usuarioPaginaService");
//        RenRenovacionService renRenovacionService = (RenRenovacionService) context.getBean("renRenovacionService");
//        RenSolicitanteApoderadoService renSolicitanteApoderadoService = (RenSolicitanteApoderadoService) context.getBean("renSolicitanteApoderadoService");
        RenSolicitudRenovacionService renSolicitudRenovacionService = (RenSolicitudRenovacionService) context.getBean("renSolicitudRenovacionService");
        ReciboDepositoService reciboDepositoService=(ReciboDepositoService)context.getBean("reciboDepositoService");
//        RenSolicitudRenovacion renSolicitudRenovacion = new RenSolicitudRenovacion();
//        Long parametro = 1820L;
//        int parametro1 = 1;
         
         
//        ReciboDeposito recibo=new ReciboDeposito();
//        recibo.setIdRecibo(8L);
//        recibo.setIdDeposito(1L);        
//        recibo.setIdLogTrans(1L);        
//        recibo.setMonto(new BigDecimal(121));
//        System.out.println("idrecibodeposito"+reciboDepositoService.crudReciboDeposito(recibo,1).getIdReciboDeposito());
        
        
        List<ReciboDeposito>lst=reciboDepositoService.lstReciboDepositoPorIdRecibo(2L);
        if(!lst.isEmpty()){
            for (ReciboDeposito reciboDeposito : lst) {
                System.out.println("id recibo deposito" + reciboDeposito.getIdDeposito());
            }
        }else{
            System.out.println("no se encontro registros");
        }
    
    }
}
