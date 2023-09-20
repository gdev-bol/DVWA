/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package snp.gob.bo.gimodel.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import snp.gob.bo.gimodel.entidad.OpoHistorial;

/**
 *
 * @author Luis Angel Quispe Limachi
 */
public class OpoHistorialMapper implements RowMapper<OpoHistorial>{
 /**
     * Método que permite mapear la tabla opohistorial
     *
     * @param rs
     * @param i
     * @return opohistorial
     * @throws java.sql.SQLException
     */
    @Override
    public OpoHistorial mapRow(ResultSet rs, int i) throws SQLException {
    
        
        try {
    OpoHistorial opohistorial=new OpoHistorial();
    
    opohistorial.setIdhistorial(rs.getLong("idhistorial"));
    opohistorial.setIdoposicion(rs.getLong("idoposicion"));
    opohistorial.setIdlogtrans(rs.getLong("idlogtrans"));
    opohistorial.setEstado(rs.getString("estado"));
    opohistorial.setFecha_lim(rs.getTimestamp("fecha_lim"));
    opohistorial.setObservacion(rs.getString("observacion"));
    opohistorial.setUbicacion(rs.getString("ubicacion"));
    opohistorial.setApoderado(rs.getString("apoderado"));
    opohistorial.setOperacion(rs.getString("operacion"));
    opohistorial.setFecha_operacion(rs.getTimestamp("fecha_operacion"));
    opohistorial.setId_usuario(rs.getLong("id_usuario"));
    
    return opohistorial;
            
        } catch (SQLException e) {
    throw e;
        }
    
    
    
    
    }

    
}
