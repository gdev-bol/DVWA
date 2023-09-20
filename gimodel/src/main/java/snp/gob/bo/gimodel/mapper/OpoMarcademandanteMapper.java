/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import snp.gob.bo.gimodel.entidad.OpoMarcademandante;

/**
 *
 * @author Luis Angel Quispe Limachi
 */
public class OpoMarcademandanteMapper implements RowMapper<OpoMarcademandante> {

    /**
     * Método que permite mapear la tabla OpoMarcademandante
     *
     * @param rs
     * @param i
     * @return opodemandante
     * @throws java.sql.SQLException
     */
    @Override
    public OpoMarcademandante mapRow(ResultSet rs, int i) throws SQLException {
        try {
            OpoMarcademandante opodemandante = new OpoMarcademandante();

            opodemandante.setIdmarcademandante(rs.getLong("idmarcademandante"));
            opodemandante.setIdoposicion(rs.getLong("idoposicion"));
            opodemandante.setIdtramite(rs.getLong("idtramite"));
            opodemandante.setIdpatente(rs.getLong("idpatente"));
            opodemandante.setIdarea(rs.getLong("idarea"));
            opodemandante.setIdmarca(rs.getLong("idmarca"));
            opodemandante.setIdlogtrans(rs.getLong("idlogtrans"));
            opodemandante.setOrden_opo(rs.getInt("orden_opo"));

            if (rs.getString("dmte_reg") != null) {
                opodemandante.setDmte_reg(rs.getInt("dmte_reg"));
            } else {
                opodemandante.setDmte_reg(null);
            }
            opodemandante.setDmte_serie(rs.getString("dmte_serie"));

            if (rs.getString("dmte_public") != null) {

                opodemandante.setDmte_public(rs.getInt("dmte_public"));
            } else {
                opodemandante.setDmte_public(null);
            }

            if (rs.getString("dmte_sm") != null) {
                opodemandante.setDmte_sm(rs.getLong("dmte_sm"));
            } else {
                opodemandante.setDmte_sm(null);
            }
            
            opodemandante.setDmte_sp(rs.getInt("dmte_sp"));
            opodemandante.setDmte_marca_lnv(rs.getString("dmte_marca_lnv"));
            opodemandante.setDmte_uso(rs.getString("dmte_uso"));
            opodemandante.setDmte_clase(rs.getInt("dmte_clase"));
            opodemandante.setEstado(rs.getString("estado"));

            return opodemandante;
        } catch (SQLException e) {
            throw e;
        }

    }

}
