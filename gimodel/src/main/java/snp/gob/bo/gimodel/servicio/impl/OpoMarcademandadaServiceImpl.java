/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.servicio.impl;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import java.util.Collections;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import snp.gob.bo.gimodel.entidad.OpoMarcademandada;
import snp.gob.bo.gimodel.mapper.OpoMarcademandadaMapper;
import snp.gob.bo.gimodel.servicio.OpoMarcademandadaService;

/**
 *
 * @author Luis Angel Quispe Limachi
 */
@Service("opoMarcaDemandadaService")
public class OpoMarcademandadaServiceImpl implements OpoMarcademandadaService {

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
    public OpoMarcademandada guardaOpodemandada(OpoMarcademandada opodemandada) {

        String SQL = "select * from inserta_marcademandada(?,?,?,?,?,?,?,?,?,?,?,?);";
        OpoMarcademandada opomarcdmda = (OpoMarcademandada) jdbcTemplate.queryForObject(SQL, new OpoMarcademandadaMapper(),
                opodemandada.getIdoposicion(),
                opodemandada.getIdlogtrans(),
                opodemandada.getNro_opo(),
                opodemandada.getDmdo_public(),
                opodemandada.getGaceta(),
                opodemandada.getDmdo_clase(),
                opodemandada.getFecha_public(),
                opodemandada.getDmdo_marca_lnv(),
                opodemandada.getDmdo_sm(),
                opodemandada.getFec_lim(),
                opodemandada.getVerif(),
                opodemandada.getEstado());
        return opomarcdmda;

    }

    @Override
    public List<OpoMarcademandada> obtenerListadoOpomarcademandada(Long didoposicion) throws Exception {

        try {
            String SQL = "select * from lista_opomarcademandada_idoposicion(?);";
            List<OpoMarcademandada> listaOpoMarcademandada = jdbcTemplate.query(SQL, new OpoMarcademandadaMapper(), didoposicion);
            if (!listaOpoMarcademandada.isEmpty()) {
                return listaOpoMarcademandada;
            }
            return Collections.EMPTY_LIST;
        } catch (Exception e) {
            throw e;
        }

    }

    @Override
    public OpoMarcademandada obtenerOpomarcademandadaobj(Long didoposicionmarc) throws Exception {

        try {
            String SQL = "select * from lista_opomarcademandada_idoposicion(?);";
            OpoMarcademandada objpoMarcademandada = (OpoMarcademandada) jdbcTemplate.queryForObject(SQL, new OpoMarcademandadaMapper(), didoposicionmarc);
            return objpoMarcademandada;
        } catch (Exception e) {
            throw e;
        }

    }

    @Override
    public Long obtenerOpomarcademandadaXidopo(Long idoposicionx) throws Exception {

        String SQL = "select idmarcademandada from opomarcademandada where idoposicion=?;";
        return jdbcTemplate.queryForObject(SQL, Long.class, idoposicionx);

    }

    @Override
    public OpoMarcademandada modificarOpomarcademandada(OpoMarcademandada opodemandadamodi) throws Exception {
        try {
            String SQL = "select * from modifica_marcademandada(?,?,?,?,?,?,?,?,?,?,?,?,?);";
            OpoMarcademandada modiobjeto1 = (OpoMarcademandada) jdbcTemplate.queryForObject(SQL, new OpoMarcademandadaMapper(),
                    opodemandadamodi.getIdmarcademandada(),
                    opodemandadamodi.getIdoposicion(),
                    opodemandadamodi.getIdlogtrans(),
                    opodemandadamodi.getNro_opo(),
                    opodemandadamodi.getDmdo_public(),
                    opodemandadamodi.getGaceta(),
                    opodemandadamodi.getDmdo_clase(),
                    opodemandadamodi.getFecha_public(),
                    opodemandadamodi.getDmdo_marca_lnv(),
                    opodemandadamodi.getDmdo_sm(),
                    opodemandadamodi.getFec_lim(),
                    opodemandadamodi.getVerif(),
                    opodemandadamodi.getEstado()
            );
            return modiobjeto1;

        } catch (Exception e) {
            throw e;
        }

    }

    @Override
    public String eliminarOpomarcademandada(Long idmarcadmda, Long idoposicion) throws Exception {

        String SQL = "select * from elimina_opomarcademandada(?,?);";
        return jdbcTemplate.queryForObject(SQL, String.class, idmarcadmda, idoposicion);

    }

    @Override
    public Boolean verificasiexistenumeroopodmda(Integer nroopo) throws Exception {
        try {
            String SQL = "select * from opomarcademandada where nro_opo=? AND estado='AC';";
            List<OpoMarcademandada> listaOpoMarcademandada = jdbcTemplate.query(SQL, new OpoMarcademandadaMapper(), nroopo);
            if (!listaOpoMarcademandada.isEmpty()) {
                return TRUE;
            } else {
                return FALSE;
            }
        } catch (Exception e) {
            throw e;
        }
       
    }

}
