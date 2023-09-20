/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import snp.gob.bo.gimodel.entidad.OpoMarcademandada;

/**
 *
 * @author Luis Angel Quispe Limachi
 */
public class OpoMarcademandadaMapper implements RowMapper<OpoMarcademandada> {
/**
     * Método que permite mapear la tabla oomarcademandada
     *
     * @param rs
     * @param i
     * @return opodemandado
     * @throws java.sql.SQLException
     */
    @Override
    public OpoMarcademandada mapRow(ResultSet rs, int i) throws SQLException {
        try {
            OpoMarcademandada opodemandado = new OpoMarcademandada();

        opodemandado.setIdmarcademandada(rs.getLong("idmarcademandada"));
        opodemandado.setIdoposicion(rs.getLong("idoposicion"));
        opodemandado.setIdlogtrans(rs.getLong("idlogtrans"));
        opodemandado.setNro_opo(rs.getInt("nro_opo"));
        opodemandado.setDmdo_public(rs.getInt("dmdo_public"));
        opodemandado.setGaceta(rs.getInt("gaceta"));        
        opodemandado.setDmdo_clase(rs.getString("dmdo_clase"));
        opodemandado.setFecha_public(rs.getTimestamp("fecha_public"));
        opodemandado.setDmdo_marca_lnv(rs.getString("dmdo_marca_lnv"));
        opodemandado.setDmdo_sm(rs.getString("dmdo_sm"));       
        opodemandado.setFec_lim(rs.getTimestamp("fec_lim"));       
        opodemandado.setVerif(rs.getBoolean("verif"));
        opodemandado.setEstado(rs.getString("estado"));
        return opodemandado;

        } catch (SQLException e) {
      throw e;
        }
        
    }

}
