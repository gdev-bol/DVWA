/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.test;

import java.util.ArrayList;
import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import snp.gob.bo.gimodel.entidad.ModSolicitanteApoderado;
import snp.gob.bo.gimodel.entidad.ModTitularLicenciatarioNuevo;
import snp.gob.bo.gimodel.servicio.ModTitularLicenciatarioNuevoService;

/**
 *
 * @author susana
 */
public class ModTitularLicenciatarioNuevoTest {

    public static void main(String[] args) {
        ApplicationContext context = new FileSystemXmlApplicationContext("//home//susana//Proyecto_Susan//SistemaInterno//giview//src//main//webapp//WEB-INF//applicationContext.xml");
        ModTitularLicenciatarioNuevoService modTitularLicencitarioNuevoService = (ModTitularLicenciatarioNuevoService) context.getBean("modTitularLicencitarioNuevoService");

        ModTitularLicenciatarioNuevo modtitularlicencitarionuevo = new ModTitularLicenciatarioNuevo();
        modtitularlicencitarionuevo.setIdmodificacion(1l);
        modtitularlicencitarionuevo.setIdlogtrans(1l);
        modtitularlicencitarionuevo.setTipo_persona("NTIT");
        modtitularlicencitarionuevo.setTipo_titular("NAT");
        modtitularlicencitarionuevo.setNombre_razonsocial("nombre_razonsocial");
        modtitularlicencitarionuevo.setPrimer_apellido("primer_apellido");
        modtitularlicencitarionuevo.setSegundo_apellido("segundo_apellido");
        modtitularlicencitarionuevo.setNumero_documento("numero_documento");
        modtitularlicencitarionuevo.setTipo_documento("RUN");
        modtitularlicencitarionuevo.setLugar_expedicion("LPZ");
        modtitularlicencitarionuevo.setPais("pais");
        modtitularlicencitarionuevo.setGenero("F");
        modtitularlicencitarionuevo.setSolicitud_departamento("ORU");
        modtitularlicencitarionuevo.setDireccion("direccion");
        modtitularlicencitarionuevo.setTelefono("telefono");
        modtitularlicencitarionuevo.setCelular("celular");
        modtitularlicencitarionuevo.setEmail("email");
        modtitularlicencitarionuevo.setFax("fax");
        modtitularlicencitarionuevo.setEstado("AC");

      //  ModTitularLicenciatarioNuevo nuevo = new ModTitularLicenciatarioNuevo();
     //   nuevo  = modTitularLicencitarioNuevoService.guardar_modificar_listar_ModTitularLicenciatarioNuevo(modtitularlicencitarionuevo,1);
     //     System.out.println("NUEVO ES    "+nuevo.getIdtitularlicenciatario());

        List<ModTitularLicenciatarioNuevo> lista = new ArrayList<ModTitularLicenciatarioNuevo>();
        lista = modTitularLicencitarioNuevoService.listaNuevoTitularXidmodificacion(1l);
        for (ModTitularLicenciatarioNuevo listaSolicitanteApoderado1 : lista) {
            System.out.println("DATO NUEVO " + listaSolicitanteApoderado1.getNombre_razonsocial());
        }
    }
}
