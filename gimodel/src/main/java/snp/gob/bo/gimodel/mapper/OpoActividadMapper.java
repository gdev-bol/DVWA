/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package snp.gob.bo.gimodel.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import snp.gob.bo.gimodel.entidad.OpoActividad;

/**
 *
 * @author Luis Angel Quispe Limachi
 */
public class OpoActividadMapper implements RowMapper<OpoActividad>{

      /**
     * Método que permite mapear la tabla opocatividad
     *
     * @param rs
     * @param i
     * @return opoactividad
     * @throws java.sql.SQLException
     */
    
    @Override
    public OpoActividad mapRow(ResultSet rs, int i) throws SQLException {
        try {
            
             OpoActividad opoactividad=new OpoActividad();
        opoactividad.setIdactividad(rs.getLong("idactividad"));
        opoactividad.setIdestado(rs.getLong("idestado"));
        opoactividad.setIdlogtrans(rs.getLong("idlogtrans"));
        opoactividad.setDescri_idactividad(rs.getString("descri_idactividad"));
       opoactividad.setActividad(rs.getString("actividad"));
              opoactividad.setOrden(rs.getInt("orden"));
        
        return opoactividad;
            
        } catch (SQLException e) {
            throw e;
        }
       
        
    }
    
}
