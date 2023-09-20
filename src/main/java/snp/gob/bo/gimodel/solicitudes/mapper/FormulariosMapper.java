/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package snp.gob.bo.gimodel.solicitudes.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import snp.gob.bo.gimodel.solicitudes.entidades.Formularios;

/**
 *
 * @author Eddy Valero
 * @version 1.0, 23/08/2016
 */
public class FormulariosMapper implements RowMapper<Formularios> {

    @Override
    public Formularios mapRow(ResultSet rs, int i) throws SQLException {
        
        Formularios formularios  = new Formularios();
        
        
        formularios.setId(rs.getLong("id"));
        formularios.setNumeroFormulario(rs.getString("numeroformulario"));
        formularios.setTipoFormulario(rs.getString("tipoformulario"));
        formularios.setFechaSolicitud(rs.getTimestamp("fechasolicitud"));
        formularios.setEstado(rs.getString("estado"));
        formularios.setIdUsuarioExterno(rs.getInt("usuario_id"));
        formularios.setIdPadre(rs.getInt("id_padre"));
        
        return formularios;
        
    }
    
    
    
}
