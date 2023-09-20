/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package snp.gob.bo.gimodel.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import snp.gob.bo.gimodel.entidad.Oposicion;

/**
 *
 * @author Luis Angel Quispe Limachi
 */
public class OposicionMapper implements RowMapper<Oposicion>{
/**
     * Método que permite mapear la tabla Oposicion 
     *
     * @param rs
     * @param i
     * @return oposicion
     * @throws java.sql.SQLException
     */
    @Override
    public Oposicion mapRow(ResultSet rs, int i) throws SQLException {        
        try {
            
            Oposicion oposicion=new Oposicion();
        oposicion.setIdoposicion(rs.getLong("idoposicion"));
        oposicion.setNro_opo(rs.getLong("nro_opo"));
        oposicion.setGaceta(rs.getInt("gaceta"));
        oposicion.setFecha_pub(rs.getTimestamp("fecha_pub"));
        oposicion.setFecha_presentacion(rs.getTimestamp("fecha_presentacion"));
        oposicion.setUbicacion(rs.getString("ubicacion"));
        oposicion.setObservacion(rs.getString("observacion"));
        oposicion.setEstado(rs.getString("estado"));
        oposicion.setOrden_o(rs.getInt("orden_o"));
        oposicion.setId_estado(rs.getLong("id_estado"));
        oposicion.setEstadoopo(rs.getString("estadoopo"));
        oposicion.setNumero_formulario(rs.getLong("numero_formulario"));
        oposicion.setGestion_opo(rs.getInt("gestion_opo"));
        oposicion.setCodigo_opo(rs.getLong("codigo_opo")); 
        oposicion.setIngreso_opo(rs.getString("ingreso_opo"));
        oposicion.setOficina(rs.getString("oficina"));
        return oposicion;
        } catch (SQLException e) {
            throw e;
        }
        
    }
    
}
