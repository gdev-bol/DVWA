 
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
import snp.gob.bo.gimodel.entidad.Deposito;
import snp.gob.bo.gimodel.entidad.Dosificacion;
import snp.gob.bo.gimodel.entidad.Tasa;
import snp.gob.bo.gimodel.entidad.Usuario;
import snp.gob.bo.gimodel.servicio.DepositoService;
import snp.gob.bo.gimodel.servicio.DosificacionService;
import snp.gob.bo.gimodel.servicio.TasaService;

/**
 *
 * @author chano Rojas
 */
public class DepositoTest {

    public static void main(String[] args) throws Exception {

//        ApplicationContext context = new FileSystemXmlApplicationContext("//home//eddy//NetBeansProjects//prototipoSpringJSFPrimeFaces//giview//src//main//webapp//WEB-INF//applicationContext.xml");
        ApplicationContext context = new FileSystemXmlApplicationContext("//home//chano//sipi_v1.0//SistemaInterno//giview//src//main//webapp//WEB-INF//applicationContext.xml");
        DepositoService depositoService=(DepositoService)context.getBean("depositoService");
        
        
        
//        Deposito deposito=new Deposito();
//        deposito.setIdDeposito(20L);
//        deposito.setBanco("BUN");
//        deposito.setMonto(new BigDecimal(120));
//        deposito.setDeposCodAgencia(1);
//        deposito.setNombreDepositante("Natalia Quevedo Frias");
//    
        
//        System.out.println("verdad"+depositoService.depositoParaModificarEliminar(deposito));
        
        
        Usuario usuario=new Usuario();
        usuario.setIdusuario(82L);
        
        List<Deposito>lista=depositoService.listaDepositoConSaldoPorUSuarioSaldo(usuario);
        
        System.out.println("lista"+lista.size());
        for (Deposito deposito1 : lista) {
            System.out.println("deposito1."+deposito1.getIdDeposito());
            
        }
        
        
        
        
    }
}
