/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.solicitudes.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import snp.gob.bo.gimodel.solicitudes.entidades.OpoMarcaDemandadaExt;

/**
 *
 * @author luis_angel
 */
public class OpoMarcaDemandadaExtMapper implements RowMapper<OpoMarcaDemandadaExt> {

    @Override
    public OpoMarcaDemandadaExt mapRow(ResultSet rs, int i) throws SQLException {

        OpoMarcaDemandadaExt opoMarcaDemandadaExt = new OpoMarcaDemandadaExt();

        opoMarcaDemandadaExt.setIdmarcademandada(rs.getLong("idmarcademandada"));
        opoMarcaDemandadaExt.setIdformulario(rs.getInt("idformulario"));
        opoMarcaDemandadaExt.setDmdo_public(rs.getInt("dmdo_public"));
        opoMarcaDemandadaExt.setGaceta(rs.getInt("gaceta"));
        opoMarcaDemandadaExt.setFechacreacion(rs.getTimestamp("fechacreacion"));
        opoMarcaDemandadaExt.setFechamod(rs.getTimestamp("fechamod"));
        opoMarcaDemandadaExt.setEstado(rs.getString("estado"));
        
        return opoMarcaDemandadaExt;
    }

}
