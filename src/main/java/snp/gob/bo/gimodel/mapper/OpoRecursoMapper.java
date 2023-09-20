/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package snp.gob.bo.gimodel.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import snp.gob.bo.gimodel.entidad.OpoRecurso;

/**
 *
 * @author Luis Angel Quispe Limachi
 */
public class OpoRecursoMapper implements RowMapper<OpoRecurso>{
/**
     * Método que permite mapear la tabla OpoRecurso
     *
     * @param rs
     * @param i
     * @return oporecurso
     * @throws java.sql.SQLException
     */
    @Override
    public OpoRecurso mapRow(ResultSet rs, int i) throws SQLException {
        try {
        OpoRecurso oporecurso=new OpoRecurso();
        oporecurso.setIdrecurso(rs.getLong("idrecurso"));
        oporecurso.setIdevento(rs.getLong("idevento"));
        oporecurso.setIdoposicion(rs.getLong("idoposicion"));
        oporecurso.setIdlogtrans(rs.getLong("idlogtrans"));
        oporecurso.setNumero_resolucion(rs.getInt("numero_resolucion"));
        oporecurso.setFecha_resolucion(rs.getTimestamp("fecha_resolucion"));
        oporecurso.setTipo(rs.getString("tipo"));
        oporecurso.setResolucion(rs.getString("resolucion"));
        oporecurso.setOrden_op(rs.getInt("orden_op"));
        oporecurso.setEstado(rs.getString("estado"));
        
        return oporecurso;
            
        } catch (SQLException e) {
        throw e;
        }
        
        
        
    }

    
    
}
