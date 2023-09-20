/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.servicio.impl;

import java.util.Collections;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import snp.gob.bo.gimodel.entidad.OpoSolicitanteapoderado;
import snp.gob.bo.gimodel.enums.EnumTipoTitular;
import snp.gob.bo.gimodel.mapper.OpoSolicitanteapoderadoMapper;
import snp.gob.bo.gimodel.servicio.OpoSolicitanteaopderadoService;

/**
 *
 * @author Luis Angel Quispe Limachi
 */
@Service("opoSolicitanteApoderadoService")
public class OpoSolicitanteapoderadoServiceImpl implements OpoSolicitanteaopderadoService {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

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
    public OpoSolicitanteapoderado guardarsolicitanteapo(OpoSolicitanteapoderado datsolicitanteapode) {

        String SQL = "select * from inserta_oposolicitanteapoderado(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";

        OpoSolicitanteapoderado oposoli = (OpoSolicitanteapoderado) jdbcTemplate.queryForObject(SQL, new OpoSolicitanteapoderadoMapper(),
                datsolicitanteapode.getIdmarcademandada(),
                datsolicitanteapode.getIdmarcademandante(),
                datsolicitanteapode.getNombre_razonsocial(),
                datsolicitanteapode.getPrimer_apellido(),
                datsolicitanteapode.getSegundo_apellido(),
                datsolicitanteapode.getNumero_documento(),
                datsolicitanteapode.getTipo_documento(),
                datsolicitanteapode.getLugar_expedicion(),
                datsolicitanteapode.getPais(),
                datsolicitanteapode.getGenero(),
                datsolicitanteapode.getSolicitud_depa(),
                datsolicitanteapode.getPoder(),
                datsolicitanteapode.getDireccion(),
                datsolicitanteapode.getTelefono(),
                datsolicitanteapode.getCelular(),
                datsolicitanteapode.getEmail(),
                datsolicitanteapode.getFax(),
                datsolicitanteapode.getTiposoliapo(),
                datsolicitanteapode.getTipo_titular(),
                datsolicitanteapode.getNropoder(),
                datsolicitanteapode.getTipo_persona(),
                datsolicitanteapode.getEstado()
        );

        return oposoli;
    }

    @Override
    public void guardalistasolicitanteapo(List<OpoSolicitanteapoderado> listasolapode) {
        for (int i = 0; i <= listasolapode.size() - 1; i++) {

            guardarsolicitanteapo(listasolapode.get(i));
        }

    }
//
//    @Override
//    public int encuentraPosicionListadoSolicitanteApoderado(List<OpoSolicitanteapoderado> listadoSolicitanteApoderado, OpoSolicitanteapoderado smsolicitanteApoderado) {
//        int posicion = 0;
//        int cont = 0;
//        for (OpoSolicitanteapoderado item : listadoSolicitanteApoderado) {
//
//            if (!smsolicitanteApoderado.getTipo_titular().equals("NAT")) {
//                if (item.getNombre_razonsocial().equals(smsolicitanteApoderado.getNombre_razonsocial())) {
//                    System.out.println("ES persona JURIDICA");
//                    posicion = cont;
//                }
//            } else {
//                if (smsolicitanteApoderado.getPrimer_apellido() != null && smsolicitanteApoderado.getSegundo_apellido() != null) {
//                   if (item.getNombre_razonsocial().equals(smsolicitanteApoderado.getNombre_razonsocial())
//                           && item.getPrimer_apellido().equals(smsolicitanteApoderado.getPrimer_apellido())
//                           && item.getSegundo_apellido().equals(smsolicitanteApoderado.getSegundo_apellido())) {
//                       posicion = cont;
//                   }
//               } else if (smsolicitanteApoderado.getPrimer_apellido() != null) {
//                   if (item.getNombre_razonsocial().equals(smsolicitanteApoderado.getNombre_razonsocial())
//                           && item.getPrimer_apellido().equals(smsolicitanteApoderado.getPrimer_apellido())) {
//                       posicion = cont;
//                   }
//               } else {
//                   if (item.getNombre_razonsocial().equals(smsolicitanteApoderado.getNombre_razonsocial())) {
//                       posicion = cont;
//                   }
//               }
//            }
//
//            cont++;
//        }
//        return posicion;
//    }
    
    
     @Override
   public int encuentraPosicionListadoSolicitanteApoderado(List<OpoSolicitanteapoderado> listadoSolicitanteApoderado, OpoSolicitanteapoderado smsolicitanteApoderado) {
       int posicion = 0;
       int cont = 0;
       for (OpoSolicitanteapoderado item : listadoSolicitanteApoderado) {
         
           
           if (!smsolicitanteApoderado.getTipo_titular().equals(EnumTipoTitular.NATURAL.getCodigo())) {
               if (item.getNombre_razonsocial().equals(smsolicitanteApoderado.getNombre_razonsocial())) {
                   posicion = cont;
               }
           } else {
                              
               if (smsolicitanteApoderado.getPrimer_apellido() != null && smsolicitanteApoderado.getSegundo_apellido() != null) {
                   if (item.getNombre_razonsocial().equals(smsolicitanteApoderado.getNombre_razonsocial())
                           && item.getPrimer_apellido().equals(smsolicitanteApoderado.getPrimer_apellido())
                           && item.getSegundo_apellido().equals(smsolicitanteApoderado.getSegundo_apellido())) {
                       posicion = cont;
                   }
               } else if (smsolicitanteApoderado.getPrimer_apellido() != null) {
                   if (item.getNombre_razonsocial().equals(smsolicitanteApoderado.getNombre_razonsocial())
                           && item.getPrimer_apellido().equals(smsolicitanteApoderado.getPrimer_apellido())) {
                       posicion = cont;
                   }
               } else {
                   if (item.getNombre_razonsocial().equals(smsolicitanteApoderado.getNombre_razonsocial())) {
                       posicion = cont;
                   }
               }
           }

           cont++;
       }
       return posicion;
   }
    
    
       @Override
    public List<OpoSolicitanteapoderado> obtenerListadoSoliApodmda(Long idmarcadmda) throws Exception {

        try {
            String SQL = "select * from lista_oposolicitanteapoderado_idmarcademandada(?);";
            List<OpoSolicitanteapoderado> listaSolApodmda = jdbcTemplate.query(SQL, new OpoSolicitanteapoderadoMapper(), idmarcadmda);
            if (!listaSolApodmda.isEmpty()) {
                return listaSolApodmda;
            }
            return Collections.EMPTY_LIST;
        } catch (Exception e) {
            throw e;
        }

    }

    @Override
    public List<OpoSolicitanteapoderado> obtenerListadoSoliApodmte(Long idmarcadmte) throws Exception {
        try {
            String SQL = "select * from lista_oposolicitanteapoderado_idmarcademandante(?);";
            List<OpoSolicitanteapoderado> listaSolApodmte = jdbcTemplate.query(SQL, new OpoSolicitanteapoderadoMapper(), idmarcadmte);
            if (!listaSolApodmte.isEmpty()) {
                return listaSolApodmte;
            }
            return Collections.EMPTY_LIST;
        } catch (Exception e) {
            throw e;
        }

    }

    @Override
    public List<OpoSolicitanteapoderado> obtenerListadoSoliDmte(Long idmarcasolidmte) throws Exception {
        try {
            String SQL = "select * from lista_oposolicitanteapoderado_solid_idmarcademandante(?);";
            List<OpoSolicitanteapoderado> listaSoldmte = jdbcTemplate.query(SQL, new OpoSolicitanteapoderadoMapper(), idmarcasolidmte);
            if (!listaSoldmte.isEmpty()) {
                return listaSoldmte;
            }
            return Collections.EMPTY_LIST;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<OpoSolicitanteapoderado> obtenerListadoApoDmte(Long idmarcaapodmte) throws Exception {
        try {
            String SQL = "select * from lista_oposolicitanteapoderado_apod_idmarcademandante(?);";
            List<OpoSolicitanteapoderado> listaApodmte = jdbcTemplate.query(SQL, new OpoSolicitanteapoderadoMapper(), idmarcaapodmte);
            if (!listaApodmte.isEmpty()) {
                return listaApodmte;
            }
            return Collections.EMPTY_LIST;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<OpoSolicitanteapoderado> obtenerListadoSoliDmda(Long idmarcasolidmda) throws Exception {

        try {
            String SQL = "select * from lista_oposolicitanteapoderado_solid_idmarcademandada(?);";
            List<OpoSolicitanteapoderado> listaSolidmda = jdbcTemplate.query(SQL, new OpoSolicitanteapoderadoMapper(), idmarcasolidmda);
            if (!listaSolidmda.isEmpty()) {
                return listaSolidmda;
            }
            return Collections.EMPTY_LIST;
        } catch (Exception e) {
            throw e;
        }

    }

    @Override
    public List<OpoSolicitanteapoderado> obtenerListadoApodDmda(Long idmarcaapodmda) throws Exception {

        try {
            String SQL = "select * from lista_oposolicitanteapoderado_apod_idmarcademandada(?);";
            List<OpoSolicitanteapoderado> listaApodmda = jdbcTemplate.query(SQL, new OpoSolicitanteapoderadoMapper(), idmarcaapodmda);
            if (!listaApodmda.isEmpty()) {
                return listaApodmda;
            }
            return Collections.EMPTY_LIST;
        } catch (Exception e) {
            throw e;
        }

    }

    @Override
    public OpoSolicitanteapoderado modificarOposolicitanteapoderado(OpoSolicitanteapoderado oposolicitanteapoderadomodi) throws Exception {

        try {
            String SQL = "select * from modifica_oposolicitanteapoderado(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
            OpoSolicitanteapoderado modiobjeto1 = (OpoSolicitanteapoderado) jdbcTemplate.queryForObject(SQL, new OpoSolicitanteapoderadoMapper(),
                    oposolicitanteapoderadomodi.getIdsolicitanteapoderado(),
                    oposolicitanteapoderadomodi.getIdmarcademandada(),
                    oposolicitanteapoderadomodi.getIdmarcademandante(),
                    oposolicitanteapoderadomodi.getNombre_razonsocial(),
                    oposolicitanteapoderadomodi.getPrimer_apellido(),
                    oposolicitanteapoderadomodi.getSegundo_apellido(),
                    oposolicitanteapoderadomodi.getNumero_documento(),
                    oposolicitanteapoderadomodi.getTipo_documento(),
                    oposolicitanteapoderadomodi.getLugar_expedicion(),
                    oposolicitanteapoderadomodi.getPais(),
                    oposolicitanteapoderadomodi.getGenero(),
                    oposolicitanteapoderadomodi.getSolicitud_depa(),
                    oposolicitanteapoderadomodi.getPoder(),
                    oposolicitanteapoderadomodi.getDireccion(),
                    oposolicitanteapoderadomodi.getTelefono(),
                    oposolicitanteapoderadomodi.getCelular(),
                    oposolicitanteapoderadomodi.getEmail(),
                    oposolicitanteapoderadomodi.getFax(),
                    oposolicitanteapoderadomodi.getTiposoliapo(),
                    oposolicitanteapoderadomodi.getTipo_titular(),
                    oposolicitanteapoderadomodi.getNropoder(),
                    oposolicitanteapoderadomodi.getTipo_persona(),
                    oposolicitanteapoderadomodi.getEstado());
            return modiobjeto1;

        } catch (Exception e) {
            throw e;
        }

    }

    @Override
    public String eliminarOposolicitanteapoderadoxdmda(Long idnotifica, Long iddmda) throws Exception {

        String SQL = "select * from elimina_oposolicitanteapoderadodemandada(?,?);";
        return jdbcTemplate.queryForObject(SQL, String.class, idnotifica, iddmda);

    }

    @Override
    public String eliminarOposolicitanteapoderadoxdmte(Long idnotifica, Long iddmte) throws Exception {

        String SQL = "select * from elimina_oposolicitanteapoderadodemandante(?,?);";
        return jdbcTemplate.queryForObject(SQL, String.class, idnotifica, iddmte);

    }

    @Override
    public Long obtieneelultimonumerodesolicitanteapoderado() throws Exception {
          String SQL = " select idsolicitanteapoderado from oposolicitanteapoderado ORDER BY idsolicitanteapoderado desc limit 1;";
        return jdbcTemplate.queryForObject(SQL, Long.class);
    }
    
    

}
