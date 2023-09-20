/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package snp.gob.bo.gimodel.solicitudes.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import snp.gob.bo.gimodel.solicitudes.entidades.SmTipoSignos;

/**
 *
 * @author Eddy Valero
 * @version 1.0, 23/08/2016
 */
public class SmTipoSignosMapper implements RowMapper<SmTipoSignos> {

    @Override
    public SmTipoSignos mapRow(ResultSet rs, int i) throws SQLException {
        
        SmTipoSignos smTipoSignos  = new  SmTipoSignos();
        
        smTipoSignos.setSignoMarcaId(rs.getLong("signomarca_id"));
        smTipoSignos.setTipoSigno(rs.getString("tiposigno"));
        smTipoSignos.setDescripcionOtro(rs.getString("descripcionotro"));
        
        
                
        return smTipoSignos;
        
    }
    
    
    
}
