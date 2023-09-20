/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.solicitudes.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import snp.gob.bo.gimodel.solicitudes.entidades.OpoMarcaDemandanteExt;

/**
 *
 * @author luis_angel
 */
public class OpoMarcaDemandanteExtMapper implements RowMapper<OpoMarcaDemandanteExt>  {

    @Override
    public OpoMarcaDemandanteExt mapRow(ResultSet rs, int i) throws SQLException {
              
        OpoMarcaDemandanteExt opoMarcaDemandanteExt  = new  OpoMarcaDemandanteExt();  
        
        opoMarcaDemandanteExt.setIdmarcademandante(rs.getLong("idmarcademandante"));
        opoMarcaDemandanteExt.setIdformulario(rs.getInt("idformulario"));
        opoMarcaDemandanteExt.setDmte_reg(rs.getInt("dmte_reg"));
        opoMarcaDemandanteExt.setDmte_serie(rs.getString("dmte_serie"));
        opoMarcaDemandanteExt.setDmte_public(rs.getInt("dmte_public"));
        opoMarcaDemandanteExt.setDmte_sm(rs.getLong("dmte_sm"));
        opoMarcaDemandanteExt.setDmte_marca(rs.getString("dmte_marca"));
        opoMarcaDemandanteExt.setDmte_sin_marca(rs.getString("dmte_sin_marca"));
        opoMarcaDemandanteExt.setDmte_opoandina(rs.getString("dmte_opoandina"));
        opoMarcaDemandanteExt.setFechacreacion(rs.getTimestamp("fechacreacion"));
        opoMarcaDemandanteExt.setFechamod(rs.getTimestamp("fechamod"));
        opoMarcaDemandanteExt.setEstado(rs.getString("estado"));
        
        return opoMarcaDemandanteExt;
        
   }
    
    
}
