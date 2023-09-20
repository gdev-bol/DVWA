/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package snp.gob.bo.gimodel.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import snp.gob.bo.gimodel.entidad.OpoActividadplazo;



/**
 *
 * @author Luis Angel Quispe Limachi
 */
public class OpoActividadplazoMapper implements RowMapper<OpoActividadplazo>{

       /**
     * Método que permite mapear la tabla opocatividadplazo
     *
     * @param rs
     * @param i
     * @return opoactividadplazo
     * @throws java.sql.SQLException
     */
    
    @Override
    public OpoActividadplazo mapRow(ResultSet rs, int i) throws SQLException {
        
        try {
            OpoActividadplazo opoactividadplazo=new OpoActividadplazo();
            
            opoactividadplazo.setIdactividadplazo(rs.getLong("idactividadplazo"));
            opoactividadplazo.setIdactividad(rs.getLong("idactividad"));
            opoactividadplazo.setDescri_idactividadplazo(rs.getString("descri_idactividadplazo"));
            opoactividadplazo.setIdactividadanterior(rs.getLong("idactividadanterior"));
            opoactividadplazo.setIdlogtrans(rs.getLong("idlogtrans"));
            opoactividadplazo.setIdarea(rs.getLong("idarea"));
            opoactividadplazo.setPlazo(rs.getInt("plazo"));
            opoactividadplazo.setSumarplazoanterior(rs.getInt("sumarplazoanterior"));
            
            return opoactividadplazo;
        } catch (SQLException e) {
            throw e;
        }
            
            
            
            
            
                    
            
            
                
    }
    

    
}
