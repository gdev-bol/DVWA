/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.test;

import java.util.Date;
import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import snp.gob.bo.gimodel.entidad.Poder;
import snp.gob.bo.gimodel.servicio.PoderService;




import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
/**
 *
 * @author levi
 */
public class PoderTest {
    public static void main(String[] args) throws Exception {
      ApplicationContext context = new FileSystemXmlApplicationContext("//home//levi//Documentos//PROYECTOS//SISInterno//SistemaInterno//giview//src//main//webapp//WEB-INF//applicationContext.xml");
     
      /*  PoderService servicio = (PoderService) context.getBean("poderService");
        Poder po= new Poder();
        po.setIdpoder(13L);
        po.setIdlogtrans(1L);
        po.setTipo_poder("PODE");
        po.setTipo_tramite("SM");
        po.setNro_exped(2L);
        po.setGestion(2016);
        po.setNro_testimonio("056/2016");
        po.setFecha_testimonio(new Date());
        po.setNotario("Juan peres santos");
        po.setNom_confiere_poder("Cating now");
        po.setDom_confiere_poder("Doicilio del afectado");
        po.setApoderado("Apoderaditosss");
        po.setPoder_revocado("NO");
        po.setObs("Observacioncita");
        po.setEstado("AC");
        
       // Poder poder=servicio.crudPoder(po, 4);
    
        List<Poder> poderes=servicio.listaPoderBusqueda("SI",7);
        for(int i=0;i<=poderes.size()-1;i++){
        
            System.out.println("datp:"+poderes.get(i).getApoderado());
        
        }*/
      
      try{
                     //Crea un a llave DES
		    KeyGenerator keygenerator = KeyGenerator.getInstance("DES");
		    SecretKey myDesKey = keygenerator.generateKey();
                    //Instancia  el objeto tipo Cipher
		    Cipher desCipher;

		    // Create the cipher
		    desCipher = Cipher.getInstance("DES/ECB/PKCS5Padding");

		    // Initialize the cipher for encryption
		    desCipher.init(Cipher.ENCRYPT_MODE, myDesKey);

		    //sensitive information
		    byte[] text = "No body can see me".getBytes();

		    System.out.println("Text [Byte Format] : " + text);
		    System.out.println("Text : " + new String(text));

		    // Encrypt the text
		    byte[] textEncrypted = desCipher.doFinal(text);

		    System.out.println("Text Encryted : " + textEncrypted);

		    // Initialize the same cipher for decryption
		    desCipher.init(Cipher.DECRYPT_MODE, myDesKey);

		    // Decrypt the text
		    byte[] textDecrypted = desCipher.doFinal(textEncrypted);

		    System.out.println("Text Decryted : " + new String(textDecrypted));

		}catch(NoSuchAlgorithmException e){
			e.printStackTrace();
		}catch(NoSuchPaddingException e){
			e.printStackTrace();
		}catch(InvalidKeyException e){
			e.printStackTrace();
		}catch(IllegalBlockSizeException e){
			e.printStackTrace();
		}catch(BadPaddingException e){
			e.printStackTrace();
		}


     }
}
