/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.servicio.impl;

import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.List;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import snp.gob.bo.gimodel.entidad.Usuario;
import snp.gob.bo.gimodel.entidad.listaMenu;
import snp.gob.bo.gimodel.mapper.listaMenuMapper;
import snp.gob.bo.gimodel.servicio.ListaUsuarioRolService;
import static org.apache.commons.codec.binary.Base64.decodeBase64;

/**
 *
 * @author levi
 */

@Service("listaUsuarioRolService")
public class ListaUsuarioRolServiceImpl implements ListaUsuarioRolService {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Override
    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<listaMenu> getUsuarioRol(String login, String pass, String idSistema) throws Exception {
        /*String SQL = "select u.nombre,r.descripcion,p.descripcion as submenu, p.url,up.acceso, up.fecha_vig_ini, up.fecha_vig_fin, up.estado \n"
         + "  from usuario u,usuariorol ur,rol r, rolpagina rp, pagina p, usuariopagina up , sistema s \n"
         + "  where u.idusuario=ur.idusuario and r.idrol=ur.idrol and rp.idrol=r.idrol and rp.idpagina=p.idpagina \n"
         + "   and u.idusuario=up.idusuario and up.idpagina=p.idpagina and up.muestra=true"
         + " and s.idsistema=ur.idsistema and s.idsistema='" + idSistema + "' and u.login ='" + login + "' and u.password='" + pass + "' order by r.orden asc";*/
        String SQL = "select u.nombre,m.descripcion,p.descripcion as submenu, p.url,up.acceso, up.fecha_vig_ini, up.fecha_vig_fin, up.estado\n"
                + "       from usuario u,usuariomenu um,menu m, menupagina mp, pagina p, usuariopagina up , sistema s \n"
                + "        where u.idusuario=um.idusuario and m.idmenu=um.idmenu and mp.idmenu=m.idmenu and mp.idpagina=p.idpagina \n"
                + "        and u.idusuario=up.idusuario and up.idpagina=p.idpagina and up.muestra=true and p.estado='AC' and s.estado='AC' and u.estado='AC' \n"
                + "        and s.idsistema=m.idsistema and s.idsistema='" + idSistema + "' and u.login ='" + login + "' and u.contrasenia='" + pass + "' order by m.orden asc,mp.orden asc;";
        List<listaMenu> listaUsuarioRol = jdbcTemplate.query(SQL, new listaMenuMapper());
        if (!listaUsuarioRol.isEmpty()) {
            /*for(int i=0;i<=listaUsuarioRol.size()-1;i++)
             { System.out.println("En consulta estado"+listaUsuarioRol.get(i).getEstado());
            
             }*/
            return listaUsuarioRol;
        }
        return Collections.EMPTY_LIST;

    }

    @Override
    public Boolean existeUsuarioRol(String login, String pass) throws Exception {

        String SQL = "select * from usuario where login ='" + login + "' and contrasenia='" + pass + "' and estado='AC'";
        if (!jdbcTemplate.queryForList(SQL).isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public Long obtieneIdUsuario(String login, String pass) throws Exception {
        String SQL = "select idusuario from usuario where login ='" + login + "' and contrasenia='" + pass + "'";
        Long idusuario = jdbcTemplate.queryForObject(SQL, Long.class);

        return idusuario;

    }

    public String encripta(String valor) {
        //Crea un a llave DES
        byte[] textEncrypted = null;
        try {
            KeyGenerator keygenerator = KeyGenerator.getInstance("AES");
            SecretKey myDesKey = keygenerator.generateKey();
            System.out.println("el mydesK:::" + myDesKey);
            //Instancia  el objeto tipo Cipher
            Cipher desCipher;

		    // Create the cipher
            // desCipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
            desCipher = Cipher.getInstance("AES");

            // Initialize the cipher for encryption
            desCipher.init(Cipher.ENCRYPT_MODE, myDesKey);

		    //sensitive information
            byte[] text = valor.getBytes();

                    //byte[] text = "No body can see me".getBytes();
            System.out.println("Text [Byte Format] : " + text);
            System.out.println("Text : " + new String(text));

            // Encrypt the text
            textEncrypted = desCipher.doFinal(text);

            System.out.println("Text Encryted : " + textEncrypted);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return textEncrypted.toString();
    }

    public String obtieneClavePartidaEnDos(String passEncriptado) {
        String claveNueva = "";
        for (int i = 0; i < passEncriptado.substring(0, 16).length(); i++) {
            claveNueva = claveNueva + passEncriptado.charAt(i);
        }
        return claveNueva;
    }

    public String encriptarContrasenaMD5(String contrasena) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(contrasena.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            return number.toString(16);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return new String();
    }

    @Override
    public String encriptarContrasena(String login, String pass) throws Exception {
        String llaveSimetrica1;
        llaveSimetrica1 = obtieneClavePartidaEnDos(encriptarContrasenaMD5(login));
        byte[] aError = null;
        SecretKeySpec key = new SecretKeySpec(llaveSimetrica1.getBytes(), "AES");
        Cipher cipher;
        try {
            System.out.println("key:" + key.toString());
            cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] datosCifrados = cipher.doFinal(pass.getBytes()); //cadena = a texto a cifrar
            return new sun.misc.BASE64Encoder().encode(datosCifrados);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public String dencriptarContrasena(String login, String codificado) throws Exception {
        String llaveSimetrica1 = obtieneClavePartidaEnDos(encriptarContrasenaMD5(login));
        byte[] aError = null;
        SecretKeySpec key = new SecretKeySpec(llaveSimetrica1.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        SecretKeySpec skeySpec = new SecretKeySpec(llaveSimetrica1.getBytes(), "AES");
        byte[] enc = decodeBase64(codificado);
        cipher.init(Cipher.DECRYPT_MODE, skeySpec);
        byte[] decrypted = cipher.doFinal(enc);
        return new String(decrypted);
    }

    /*
     public Integer obtieneBloqueNuevo(Integer id_usuariosolicitante){
     String SQL = "select * from obtiene_numerobloquenuevonotifica(?);";//Modifica
     Integer numBloque =  jdbcTemplate.queryForObject(SQL, new Object[]{id_usuariosolicitante}, Integer.class);
                
                
     return numBloque;
        
        
     }
     
     
     */
}
