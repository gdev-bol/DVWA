/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package snp.gob.bo.gimodel.solicitudes.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import snp.gob.bo.gimodel.solicitudes.entidades.LemasComerciales;

/**
 *
 * @author Eddy Valero
 * @version 1.0, 23/08/2016
 */
public class LemasComercialesMapper implements RowMapper<LemasComerciales> {

    @Override
    public LemasComerciales mapRow(ResultSet rs, int i) throws SQLException {
        
        LemasComerciales lemasComerciales  = new  LemasComerciales();
        
        
        lemasComerciales.setId(rs.getLong("id"));
        lemasComerciales.setNombreMarca(rs.getString("nombremarca"));
        lemasComerciales.setNumeroSolicitudMarca(rs.getString("numerosolicitudmarca"));
        lemasComerciales.setNumeroRegistro(rs.getString("numeroregistro"));
        lemasComerciales.setSerie(rs.getString("serie"));
        lemasComerciales.setFechaVigente(rs.getTimestamp("fechavigente"));
        lemasComerciales.setSignoMarcaId(rs.getLong("signomarca_id"));
        
        return lemasComerciales;
        
    }
    
    
    
}
