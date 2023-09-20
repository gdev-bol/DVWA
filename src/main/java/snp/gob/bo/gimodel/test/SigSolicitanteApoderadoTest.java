/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.test;

import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import snp.gob.bo.gimodel.entidad.SigSolicitanteApoderado;
import snp.gob.bo.gimodel.servicio.SigSolicitanteApoderadoService;

/**
 *
 * @author susana
 */
public class SigSolicitanteApoderadoTest {

    public static void main(String[] args) {
        ApplicationContext context = new FileSystemXmlApplicationContext("//home//susana//Proyecto_Susan//SistemaInterno//giview//src//main//webapp//WEB-INF//applicationContext.xml");
//        ApplicationContext context = new FileSystemXmlApplicationContext("//home//eddy//NetBeansProjects//borrar//SistemaInterno//giview//src//main//webapp//WEB-INF//applicationContext.xml");
        SigSolicitanteApoderadoService sigSolicitanteApoderadoService = (SigSolicitanteApoderadoService) context.getBean("sigSolicitanteApoderadoService");     
        SigSolicitanteApoderado solicitanteapoderado = new SigSolicitanteApoderado();
        SigSolicitanteApoderado nuevo2 = new SigSolicitanteApoderado();
        
        solicitanteapoderado.setIdSolicitanteApoderado(null);
        solicitanteapoderado.setIdSignoMarca(105629l);
        solicitanteapoderado.setIdLogTrans(1l);
        solicitanteapoderado.setTipoTitular("NAT");
        solicitanteapoderado.setTipoPersona("SOLI");
        solicitanteapoderado.setNombreRazonSocial("setNombre_razonsocial");
        solicitanteapoderado.setPrimerApellido("setNombre_razonsocial");
        solicitanteapoderado.setSegundoApellido("setNombre_razonsocial");
        solicitanteapoderado.setNumeroDocumento("454654");
        solicitanteapoderado.setTipoDocumento("CI");
        solicitanteapoderado.setLugarExpedicion("LPZ");
        solicitanteapoderado.setPais("BO");
        solicitanteapoderado.setGenero("MA");
        solicitanteapoderado.setSolicitudDepartamento("LPZ");
        solicitanteapoderado.setPoder("sdd");
        solicitanteapoderado.setDireccion("DIRECCION");
        solicitanteapoderado.setTelefono("TTT");
        solicitanteapoderado.setCelular("CCC");
        solicitanteapoderado.setEmail("EMAIL");
        solicitanteapoderado.setFax("FAX");
        solicitanteapoderado.setEstado("AC");
        
        
        System.out.println("sig " + solicitanteapoderado.toString());
        
        
//        try {
//            nuevo2 = sigSolicitanteApoderadoService.obtenerSigSolicitanteApoderado(294409L, "SOLI");
//            System.out.println("nuevo2 "  +  nuevo2.getIdSolicitanteApoderado());
//        } catch (Exception ex) {
//            Logger.getLogger(SigSolicitanteApoderadoTest.class.getName()).log(Level.SEVERE, null, ex);
//        }
        
        
        
        
    //    String cadena1 = "|idSolicitanteApoderado=294409|idSignoMarca=152060|idLogTrans=1|tipoTitular=NAT|tipoPersona=SOLI|nombreRazonSocial=MARTIN|primerApellido= ALIAGA ALIAGA|segundoApellido=|numeroDocumento=C.I.:11097914LPZ|tipoDocumento=CI|lugarExpedicion=LPZ|pais=BO|genero=MA|solicitudDepartamento=LPZ|poder=null|direccion=AV. PERIFERICA ZONA CHUQUIAGUILLO|telefono=73322877|celular=|email=null|fax=|estado=AC|";
        
//        StringTokenizer token = new StringTokenizer(cadena1, "\\|");
//        
//        while(token.hasMoreTokens()){
//            String str=token.nextToken();
//            System.out.println(" *** " + str);
//            //datos[i]=Double.valueOf(str).doubleValue();
//            //System.out.println(datos[i]);
//            //i++;
//        }
        
        
        //palabras separadas
//        String palabrasSeparadas1[] = cadena1.split("\\|");
//        String palabrasSeparadas2[] = cadena1.split("\\|");
        
//        for (String string : palabrasSeparadas1) {
//            System.out.println(" *** " + string);
//        }
//        
//        for (int i = 0; i < palabrasSeparadas1.length; i++) {
//            System.out.println(" *** " + palabrasSeparadas1[i] + "***" + palabrasSeparadas2[i] );
//        }
//        
        

//        nuevo2 = sigSolicitanteApoderadoService.guardar_modificar_listar_SigSolicitanteApoderado(solicitanteapoderado, 1);
//        System.out.println("NUEVO 2 " + nuevo2.getNombreRazonSocial());
        
        String[] datosSolicitanteApoderado = sigSolicitanteApoderadoService.datos_SigSolicitanteApoderado_concatenado(124857l, "SOLI");
        System.out.println("NOMBRE     "+datosSolicitanteApoderado[0]);
        System.out.println("DIRECCION  "+datosSolicitanteApoderado[1]);
        System.out.println("PAIS       "+datosSolicitanteApoderado[2]);
        System.out.println("PAIS DESCR "+datosSolicitanteApoderado[3]);
        System.out.println("LUGAR      "+datosSolicitanteApoderado[4]);
    }
}
