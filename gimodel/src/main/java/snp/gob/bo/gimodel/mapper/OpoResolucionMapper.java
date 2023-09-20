/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package snp.gob.bo.gimodel.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import snp.gob.bo.gimodel.entidad.OpoResolucion;

/**
 *
 * @author Luis Angel Quispe Limachi
 */
public class OpoResolucionMapper implements RowMapper<OpoResolucion>{
/**
     * Método que permite mapear la tabla OpoResolucion
     *
     * @param rs
     * @param i
     * @return oporesolucion
     * @throws java.sql.SQLException
     */
    @Override
    public OpoResolucion mapRow(ResultSet rs, int i) throws SQLException {
        try {
        OpoResolucion oporesolucion=new OpoResolucion();
        oporesolucion.setIdresolucion(rs.getLong("idresolucion"));
        oporesolucion.setIdoposicion(rs.getLong("idoposicion"));
        oporesolucion.setIdevento(rs.getLong("idevento"));
        oporesolucion.setIdlogtrans(rs.getLong("idlogtrans"));
        oporesolucion.setNumero_resolucion(rs.getInt("numero_resolucion"));        
        oporesolucion.setFecha(rs.getTimestamp("fecha"));
        oporesolucion.setResolucion_cancelacion(rs.getString("resolucion_cancelacion"));
        oporesolucion.setResolucion_oposicion(rs.getString("resolucion_oposicion"));
        oporesolucion.setResolucion_signo(rs.getString("resolucion_signo"));
        oporesolucion.setOrden_o(rs.getInt("orden_o"));
        oporesolucion.setEstado(rs.getString("estado"));        
        
        return oporesolucion;    
        } catch (SQLException e) {
            throw e;
        }
        
        
        
        
    
    
    
    }
 
    
    
}
