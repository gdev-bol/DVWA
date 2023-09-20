package snp.gob.bo.gimodel.test;


import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import snp.gob.bo.gimodel.entidad.LogTrans;
import snp.gob.bo.gimodel.entidad.SigSignoMarca;
import snp.gob.bo.gimodel.servicio.ExpedienteService;
import snp.gob.bo.gimodel.servicio.LogTransService;

/**
 *
 * @author Eddy Valero
 * @see LogTransService
 * @see LogTransServiceImpl
 * @version 1.0, 22/11/2016
 */
public class LogTransTest {
    public static void main(String[] args){
        
        
        ApplicationContext context = new FileSystemXmlApplicationContext("//home//eddy//NetBeansProjects//borrar//SistemaInterno//giview//src//main//webapp//WEB-INF//applicationContext.xml");
        
        LogTransService logTransService = (LogTransService) context.getBean("logTransService");
        
        System.out.println(" **** hola");
        
        LogTrans logTrans = new LogTrans();
        logTrans.setIdUsuario(1L);
        logTrans.setFecha(new Date());
        
        
        try {
            logTrans = logTransService.crudLogTrans(logTrans, 1);
            System.out.println(" *** " + logTrans.getIdLogTrans());
            
        } catch (Exception ex) {
            Logger.getLogger(LogTransTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
                
    }
    
}
