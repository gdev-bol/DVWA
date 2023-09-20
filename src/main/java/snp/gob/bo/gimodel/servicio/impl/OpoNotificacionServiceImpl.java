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
import snp.gob.bo.gimodel.entidad.OpoNotificacion;
import snp.gob.bo.gimodel.mapper.OpoNotificacionMapper;
import snp.gob.bo.gimodel.servicio.OpoNotificacionService;

/**
 *
 * @author Luis Angel Quispe Limachi
 */
@Service("opoNotificacionService")
public class OpoNotificacionServiceImpl implements OpoNotificacionService {

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
    public OpoNotificacion guardardirnoti(OpoNotificacion oponotificacion) {

        String SQL = "select * from inserta_oponotificacion(?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
        OpoNotificacion oponoti = (OpoNotificacion) jdbcTemplate.queryForObject(SQL, new OpoNotificacionMapper(),
                oponotificacion.getIdmarcademandada(),
                oponotificacion.getIdmarcademandante(),
                oponotificacion.getCiudad_notificacion(),
                oponotificacion.getZona_barrio(),
                oponotificacion.getAvenida_calle(),
                oponotificacion.getNumero(),
                oponotificacion.getEdificio(),
                oponotificacion.getPiso(),
                oponotificacion.getNumero_departamento(),
                oponotificacion.getReferencia_direccion(),
                oponotificacion.getEmail(),
                oponotificacion.getTelefono(),
                oponotificacion.getCelular(),
                oponotificacion.getEstado()
        );

        return oponoti;

    }

    @Override
    public List<OpoNotificacion> obtenerListadoNotificaciondmda(Long idmarcadmda) throws Exception {
        try {
            String SQL = "select * from lista_oponotificacion_idmarcademandada(?);";
            List<OpoNotificacion> listaNotidmda = jdbcTemplate.query(SQL, new OpoNotificacionMapper(), idmarcadmda);
            if (!listaNotidmda.isEmpty()) {
                return listaNotidmda;
            }
            return Collections.EMPTY_LIST;
        } catch (Exception e) {
            throw e;
        }

    }

    @Override
    public List<OpoNotificacion> obtenerListadoNotificaciondmte(Long idmarcadmte) throws Exception {

        try {
            String SQL = "select * from lista_oponotificacion_idmarcademandante(?);";
            List<OpoNotificacion> listaNotidmte = jdbcTemplate.query(SQL, new OpoNotificacionMapper(), idmarcadmte);
            if (!listaNotidmte.isEmpty()) {
                return listaNotidmte;
            }
            return Collections.EMPTY_LIST;
        } catch (Exception e) {
            throw e;
        }

    }

    @Override
    public OpoNotificacion obtenerNotificacionxmarcadmda(Long idmarcadmda) throws Exception {

        try {
            String SQL = "select * from lista_oponotificacion_idmarcademandada(?);";
            OpoNotificacion notidmda = jdbcTemplate.queryForObject(SQL, new OpoNotificacionMapper(), idmarcadmda);
            return notidmda;
        } catch (Exception e) {
            throw e;
        }

    }

    @Override
    public OpoNotificacion obtenerNotificacionxmarcadmte(Long idmarcadnte) throws Exception {

        try {
            String SQL = "select * from lista_oponotificacion_idmarcademandante(?);";
            OpoNotificacion notidmte = jdbcTemplate.queryForObject(SQL, new OpoNotificacionMapper(), idmarcadnte);
            return notidmte;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public OpoNotificacion modificarOponotificacionmodi(OpoNotificacion oponotificacionmodi) throws Exception {
        try {
            String SQL = "select * from modifica_oponotificacion(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
            OpoNotificacion modiobjeto1 = (OpoNotificacion) jdbcTemplate.queryForObject(SQL, new OpoNotificacionMapper(),
                    oponotificacionmodi.getIdnotificacion(),
                    oponotificacionmodi.getIdmarcademandada(),
                    oponotificacionmodi.getIdmarcademandante(),
                    oponotificacionmodi.getCiudad_notificacion(),
                    oponotificacionmodi.getZona_barrio(),
                    oponotificacionmodi.getAvenida_calle(),
                    oponotificacionmodi.getNumero(),
                    oponotificacionmodi.getEdificio(),
                    oponotificacionmodi.getPiso(),
                    oponotificacionmodi.getNumero_departamento(),
                    oponotificacionmodi.getReferencia_direccion(),
                    oponotificacionmodi.getEmail(),
                    oponotificacionmodi.getTelefono(),
                    oponotificacionmodi.getCelular(),
                    oponotificacionmodi.getEstado()
            );
            return modiobjeto1;

        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Long obtenreidnotificacionxmarcadmda(Long idmarcademandmda) throws Exception {
        String SQL = "select idnotificacion from oponotificacion where idmarcademandada=?;";
        return jdbcTemplate.queryForObject(SQL, Long.class, idmarcademandmda);

    }

    @Override
    public Long obtenreidnotificacionxmarcadmte(Long idmarcademandmte) throws Exception {
        String SQL = "select idnotificacion from oponotificacion where idmarcademandante=?;";
        return jdbcTemplate.queryForObject(SQL, Long.class, idmarcademandmte);

    }

    @Override
    public String eliminarOponotificacionxdmda(Long idnotifica, Long iddmda) throws Exception {
        String SQL = "select * from elimina_oponotificaciondemandada(?,?);";
        return jdbcTemplate.queryForObject(SQL, String.class, idnotifica, iddmda);
       
    }

    @Override
    public String eliminarOponotificacionxdmte(Long idnotifica, Long iddmte) throws Exception {
       String SQL = "select * from elimina_oponotificaciondemandante(?,?);";
        return jdbcTemplate.queryForObject(SQL, String.class, idnotifica, iddmte);
    }


}
