/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package snp.gob.bo.gimodel.solicitudes.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import snp.gob.bo.gimodel.solicitudes.entidades.Logotipos;

/**
 *
 * @author Eddy Valero
 * @version 1.0, 23/08/2016
 */
public class LogotiposMapper implements RowMapper<Logotipos> {

    @Override
    public Logotipos mapRow(ResultSet rs, int i) throws SQLException {
        
        Logotipos logotipos  = new  Logotipos();
        
        logotipos.setId(rs.getLong("id"));
        logotipos.setUrlimagen(rs.getString("urlimagen"));
        logotipos.setImagen(rs.getBytes("imagen"));
        logotipos.setPrincipal(rs.getBoolean("principal"));
        logotipos.setNombreArchivo(rs.getString("nombrearchivo"));
        logotipos.setExtensionArchivo(rs.getString("extensionarchivo"));
        logotipos.setSignoMarcaId(rs.getLong("signomarca_id"));
        
        
        return logotipos;
        
    }
    
    
    
}
