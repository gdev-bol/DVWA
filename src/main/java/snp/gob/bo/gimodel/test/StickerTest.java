/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.test;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import snp.gob.bo.gimodel.entidad.Sticker;
import snp.gob.bo.gimodel.servicio.StickerService;

/**
 *
 * @author desarrollo
 */
public class StickerTest {

    public static void main(String[] args) {
        ApplicationContext context = new FileSystemXmlApplicationContext("//home//ruben//NetBeansProjects//SistemaInterno//giview//src//main//webapp//WEB-INF//applicationContext.xml");
        StickerService stickerService = (StickerService) context.getBean("stickerService");

        

        try {
            Sticker sticker = new Sticker();
            sticker.setIdSticker(53L);
            sticker.setIdLogTrans(1L);
            sticker.setTipoTramite("SM-S");
            sticker.setIncremento(2);
            sticker.setPrimerNumeroAsignado(200000);
            sticker.setUltimoNumeroAsignado(201000);
            sticker.setGestion(2015);
            sticker.setEstado("AC");

            int parametro = 3;
            sticker = stickerService.crudSticker(sticker, parametro);
            System.out.println("****************************" + sticker.getIdSticker());
            
            Sticker sticker1 = new Sticker();
             sticker = stickerService.obtenerStickerXTipoTramiteYGestion("SM", 2016);
            if (sticker != null) {
                System.out.println("****************************" + sticker.getIdSticker());
            }
            
        } catch (Exception ex) {
            Logger.getLogger(SeccionSubPublicacionTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
