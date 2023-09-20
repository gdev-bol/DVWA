/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.servicio.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import snp.gob.bo.gimodel.entidad.OpoMarcademandada;
import snp.gob.bo.gimodel.entidad.OpoMarcademandante;
import snp.gob.bo.gimodel.entidad.OpoNotificacion;
import snp.gob.bo.gimodel.entidad.OpoObjetoDmteDmdoNotiSoliapo;
import snp.gob.bo.gimodel.entidad.OpoSolicitanteapoderado;
import snp.gob.bo.gimodel.entidad.Oposicion;
import snp.gob.bo.gimodel.servicio.OpoGeneralService;
import snp.gob.bo.gimodel.servicio.OpoMarcademandadaService;
import snp.gob.bo.gimodel.servicio.OpoMarcademandanteService;
import snp.gob.bo.gimodel.servicio.OpoNotificacionService;
import snp.gob.bo.gimodel.servicio.OpoSolicitanteaopderadoService;
import snp.gob.bo.gimodel.servicio.OposicionService;

/**
 *
 * @author Luis Angel Quispe Limachi
 */
@Service("opoGeneralService")

public class OpoGeneralServiceImpl implements OpoGeneralService {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    private OposicionService oposicionserviceimple;
    @Autowired
    private OpoMarcademandadaService opomarcademandadaservice;
    @Autowired
    private OpoMarcademandanteService opomarcademandanteservice;
    @Autowired
    private OpoNotificacionService oponotificacionservice;
    @Autowired
    private OpoSolicitanteaopderadoService oposolicitanteapoderadoservice;

    @Override
    @Autowired
    public void setDataSource(DataSource dataSource) {
        try {
            this.dataSource = dataSource;
            this.jdbcTemplate = new JdbcTemplate(dataSource);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void guardartramiteopo(OpoObjetoDmteDmdoNotiSoliapo objetoddns) throws Exception {

        Oposicion opoobj = new Oposicion();
        OpoMarcademandada opomrdmda = new OpoMarcademandada();
        OpoMarcademandante opomrdmte = new OpoMarcademandante();
        OpoNotificacion oponotifi = new OpoNotificacion();
        OpoNotificacion oponotifi1 = new OpoNotificacion();

        List<OpoSolicitanteapoderado> listaapodmdo1 = new ArrayList<OpoSolicitanteapoderado>();
        List<OpoSolicitanteapoderado> listasolidmte1 = new ArrayList<OpoSolicitanteapoderado>();
        List<OpoSolicitanteapoderado> listaapodmte1 = new ArrayList<OpoSolicitanteapoderado>();
        List<OpoSolicitanteapoderado> listasolidmdo1 = new ArrayList<OpoSolicitanteapoderado>();

        // Esta es la parte que se guarda la oposicion     
        opoobj = objetoddns.getObjopsicion();
        opoobj = oposicionserviceimple.guardaroposicion(opoobj);

        //guarda la marca demandada con ide de la oposicion
       // if(!opomarcademandadaservice.verificasiexistenumeroopodmda(objetoddns.getObjmarcademandada().getNro_opo())){
        opomrdmda = objetoddns.getObjmarcademandada();
        opomrdmda.setIdoposicion(opoobj.getIdoposicion());
        opomrdmda = opomarcademandadaservice.guardaOpodemandada(opomrdmda);        
        //}
       
        
        //guarda marcademandante con id de la oposiion        
        opomrdmte = objetoddns.getObjmarcademandante();
        opomrdmte.setIdoposicion(opoobj.getIdoposicion());
        opomrdmte = opomarcademandanteservice.guardardemandante(opomrdmte);

       
        //guarda los datos de notificacion
       
        oponotifi = objetoddns.getObjnotificaciondmda();
        oponotifi.setIdmarcademandada(opomrdmda.getIdmarcademandada());
        oponotificacionservice.guardardirnoti(oponotifi);

        oponotifi1 = objetoddns.getObjnotificaciondmte();
        oponotifi1.setIdmarcademandante(opomrdmte.getIdmarcademandante());
        oponotificacionservice.guardardirnoti(oponotifi1);

        // guarda el id de su respectiva solicitud
        listaapodmdo1 = objetoddns.getListaapodmdo();

        for (OpoSolicitanteapoderado lista1 : objetoddns.getListaapodmdo()) {
            lista1.setIdmarcademandada(null);
            lista1.setIdmarcademandante(null);
        }

        for (OpoSolicitanteapoderado lista1 : objetoddns.getListaapodmdo()) {
            if (lista1.getTiposoliapo().equals("DMDO")) {

                lista1.setIdmarcademandada(opomrdmda.getIdmarcademandada());
            } else {
                lista1.setIdmarcademandante(opomrdmte.getIdmarcademandante());
            }

        }

        oposolicitanteapoderadoservice.guardalistasolicitanteapo(listaapodmdo1);

        listaapodmte1 = objetoddns.getListaapodmte();

        for (OpoSolicitanteapoderado lista2 : listaapodmte1) {
            lista2.setIdmarcademandada(null);
            lista2.setIdmarcademandante(null);
        }
        for (OpoSolicitanteapoderado lista2 : listaapodmte1) {
            if (lista2.getTiposoliapo().equals("DMDO")) {

                lista2.setIdmarcademandada(opomrdmda.getIdmarcademandada());
            } else {
                lista2.setIdmarcademandante(opomrdmte.getIdmarcademandante());
            }

        }
        oposolicitanteapoderadoservice.guardalistasolicitanteapo(listaapodmte1);

        listasolidmdo1 = objetoddns.getListasolidmdo();

        for (OpoSolicitanteapoderado lista3 : listasolidmdo1) {
            lista3.setIdmarcademandada(null);
            lista3.setIdmarcademandante(null);
        }

        for (OpoSolicitanteapoderado lista3 : listasolidmdo1) {
            if (lista3.getTiposoliapo().equals("DMDO")) {

                lista3.setIdmarcademandada(opomrdmda.getIdmarcademandada());
            } else {
                lista3.setIdmarcademandante(opomrdmte.getIdmarcademandante());
            }

        }
        oposolicitanteapoderadoservice.guardalistasolicitanteapo(listasolidmdo1);

        listasolidmte1 = objetoddns.getListasolidmte();
        for (OpoSolicitanteapoderado lista4 : listasolidmte1) {
            lista4.setIdmarcademandada(null);
            lista4.setIdmarcademandante(null);
        }
        for (OpoSolicitanteapoderado lista4 : listasolidmte1) {
            if (lista4.getTiposoliapo().equals("DMDO")) {

                lista4.setIdmarcademandada(opomrdmda.getIdmarcademandada());
            } else {
                lista4.setIdmarcademandante(opomrdmte.getIdmarcademandante());
            }

        }
        oposolicitanteapoderadoservice.guardalistasolicitanteapo(listasolidmte1);

    }
    
//    @Override
//    public Boolean verificasiexistenumeroopodmda(OpoObjetoDmteDmdoNotiSoliapo nroopo) throws Exception {
//       
//         try {
//            String SQL = "select nro_opo from  opomarcademandada where nro_opo=?;";
//
//            return jdbcTemplate.queryForObject(SQL, String.class, idoposicion);
//
//        } catch (Exception e) {
//            throw e;
//        }
//        
//       
//    }

    @Override
    public OpoObjetoDmteDmdoNotiSoliapo guardartramiteopoext(OpoObjetoDmteDmdoNotiSoliapo objetoddns) throws Exception {
     
        OpoObjetoDmteDmdoNotiSoliapo pojogral=new OpoObjetoDmteDmdoNotiSoliapo();
        
        Oposicion opoobj = new Oposicion();
        OpoMarcademandada opomrdmda = new OpoMarcademandada();
        OpoMarcademandante opomrdmte = new OpoMarcademandante();
        OpoNotificacion oponotifi = new OpoNotificacion();
        OpoNotificacion oponotifi1 = new OpoNotificacion();

        List<OpoSolicitanteapoderado> listaapodmdo1 = new ArrayList<OpoSolicitanteapoderado>();
        List<OpoSolicitanteapoderado> listasolidmte1 = new ArrayList<OpoSolicitanteapoderado>();
        List<OpoSolicitanteapoderado> listaapodmte1 = new ArrayList<OpoSolicitanteapoderado>();
        List<OpoSolicitanteapoderado> listasolidmdo1 = new ArrayList<OpoSolicitanteapoderado>();

        // Esta es la parte que se guarda la oposicion     
        opoobj = objetoddns.getObjopsicion();
        opoobj = oposicionserviceimple.guardaroposicion(opoobj);

        //guarda la marca demandada con ide de la oposicion
       // if(!opomarcademandadaservice.verificasiexistenumeroopodmda(objetoddns.getObjmarcademandada().getNro_opo())){
        opomrdmda = objetoddns.getObjmarcademandada();
        opomrdmda.setIdoposicion(opoobj.getIdoposicion());
        opomrdmda = opomarcademandadaservice.guardaOpodemandada(opomrdmda);        
        //}
       
        
        //guarda marcademandante con id de la oposiion        
        opomrdmte = objetoddns.getObjmarcademandante();
        opomrdmte.setIdoposicion(opoobj.getIdoposicion());
        opomrdmte = opomarcademandanteservice.guardardemandante(opomrdmte);

       
        //guarda los datos de notificacion
       
        oponotifi = objetoddns.getObjnotificaciondmda();
        oponotifi.setIdmarcademandada(opomrdmda.getIdmarcademandada());
        oponotificacionservice.guardardirnoti(oponotifi);

        oponotifi1 = objetoddns.getObjnotificaciondmte();
        oponotifi1.setIdmarcademandante(opomrdmte.getIdmarcademandante());
        oponotificacionservice.guardardirnoti(oponotifi1);

        // guarda el id de su respectiva solicitud
        listaapodmdo1 = objetoddns.getListaapodmdo();

        for (OpoSolicitanteapoderado lista1 : objetoddns.getListaapodmdo()) {
            lista1.setIdmarcademandada(null);
            lista1.setIdmarcademandante(null);
        }

        for (OpoSolicitanteapoderado lista1 : objetoddns.getListaapodmdo()) {
            if (lista1.getTiposoliapo().equals("DMDO")) {

                lista1.setIdmarcademandada(opomrdmda.getIdmarcademandada());
            } else {
                lista1.setIdmarcademandante(opomrdmte.getIdmarcademandante());
            }

        }

        oposolicitanteapoderadoservice.guardalistasolicitanteapo(listaapodmdo1);

        listaapodmte1 = objetoddns.getListaapodmte();

        for (OpoSolicitanteapoderado lista2 : listaapodmte1) {
            lista2.setIdmarcademandada(null);
            lista2.setIdmarcademandante(null);
        }
        for (OpoSolicitanteapoderado lista2 : listaapodmte1) {
            if (lista2.getTiposoliapo().equals("DMDO")) {

                lista2.setIdmarcademandada(opomrdmda.getIdmarcademandada());
            } else {
                lista2.setIdmarcademandante(opomrdmte.getIdmarcademandante());
            }

        }
        oposolicitanteapoderadoservice.guardalistasolicitanteapo(listaapodmte1);

        listasolidmdo1 = objetoddns.getListasolidmdo();

        for (OpoSolicitanteapoderado lista3 : listasolidmdo1) {
            lista3.setIdmarcademandada(null);
            lista3.setIdmarcademandante(null);
        }

        for (OpoSolicitanteapoderado lista3 : listasolidmdo1) {
            if (lista3.getTiposoliapo().equals("DMDO")) {

                lista3.setIdmarcademandada(opomrdmda.getIdmarcademandada());
            } else {
                lista3.setIdmarcademandante(opomrdmte.getIdmarcademandante());
            }

        }
        oposolicitanteapoderadoservice.guardalistasolicitanteapo(listasolidmdo1);

        listasolidmte1 = objetoddns.getListasolidmte();
        for (OpoSolicitanteapoderado lista4 : listasolidmte1) {
            lista4.setIdmarcademandada(null);
            lista4.setIdmarcademandante(null);
        }
        for (OpoSolicitanteapoderado lista4 : listasolidmte1) {
            if (lista4.getTiposoliapo().equals("DMDO")) {

                lista4.setIdmarcademandada(opomrdmda.getIdmarcademandada());
            } else {
                lista4.setIdmarcademandante(opomrdmte.getIdmarcademandante());
            }

        }
        oposolicitanteapoderadoservice.guardalistasolicitanteapo(listasolidmte1);
        
        pojogral.setObjopsicion(opoobj);
        pojogral.setObjmarcademandada(opomrdmda);
        pojogral.setObjmarcademandante(opomrdmte);
        pojogral.setObjnotificaciondmda(oponotifi);
        pojogral.setObjnotificaciondmte(oponotifi1);
        
        pojogral.setListasolidmdo(listasolidmdo1);
        pojogral.setListaapodmdo(listaapodmdo1);
        pojogral.setListasolidmte(listasolidmte1);
        pojogral.setListaapodmte(listaapodmte1);
        
        return pojogral;       
        
        
        
    }

}
