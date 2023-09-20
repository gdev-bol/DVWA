/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package snp.gob.bo.gimodel.solicitudes.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import snp.gob.bo.gimodel.solicitudes.entidades.Prioridades;

/**
 *
 * @author Eddy Valero
 * @version 1.0, 23/08/2016
 */
public class PrioridadesMapper implements RowMapper<Prioridades> {

    @Override
    public Prioridades mapRow(ResultSet rs, int i) throws SQLException {
        
        Prioridades prioridades  = new  Prioridades();
        
        
        prioridades.setFormularioId(rs.getLong("formulario_id"));
        prioridades.setTipoPrioridad(rs.getString("tipoprioridad"));
        prioridades.setTipoInteres(rs.getString("tipointeres"));
        prioridades.setNombre(rs.getString("nombre"));
        prioridades.setFecha(rs.getTimestamp("fecha"));
        prioridades.setLugar(rs.getString("lugar"));
        
                
        return prioridades;
        
    }
    
    
    
}
