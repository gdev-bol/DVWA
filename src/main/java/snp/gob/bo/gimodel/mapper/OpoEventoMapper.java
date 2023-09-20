/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package snp.gob.bo.gimodel.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import snp.gob.bo.gimodel.entidad.OpoEvento;



/**
 *
 * @author Luis Angel Quispe Limachi
 */
public class OpoEventoMapper implements RowMapper<OpoEvento>{
 /**
     * Método que permite mapear la tabla opoevento
     *
     * @param rs
     * @param i
     * @return opoevento
     * @throws java.sql.SQLException
     */
    @Override
    public OpoEvento mapRow(ResultSet rs, int i) throws SQLException {
        try {
             OpoEvento opoevento=new OpoEvento();        
        opoevento.setIdevento(rs.getLong("idevento"));
        opoevento.setIdactividad(rs.getLong("idactividad"));
        opoevento.setIdoposicion(rs.getLong("idoposicion"));
        opoevento.setIdlogtrans(rs.getLong("idlogtrans"));
        opoevento.setFecha(rs.getTimestamp("fecha"));
        opoevento.setObservacion(rs.getString("observacion"));
        opoevento.setUsuario(rs.getLong("usuario"));
        opoevento.setOrden_o(rs.getInt("orden_o"));
        opoevento.setFecha_operacion(rs.getTimestamp("fecha_operacion"));
      
        return opoevento;
        } catch (SQLException e) {
            throw e;
        }
       
                
        
        
        
        
        
                
        
    }
    
    
    
}
