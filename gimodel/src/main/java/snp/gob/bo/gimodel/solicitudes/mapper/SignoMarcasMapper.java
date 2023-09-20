/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package snp.gob.bo.gimodel.solicitudes.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import snp.gob.bo.gimodel.solicitudes.entidades.SignoMarcas;

/**
 *
 * @author Eddy Valero
 * @version 1.0, 23/08/2016
 */
public class SignoMarcasMapper implements RowMapper<SignoMarcas> {

    @Override
    public SignoMarcas mapRow(ResultSet rs, int i) throws SQLException {
        
        SignoMarcas signoMarcas  = new  SignoMarcas();
        
        
        signoMarcas.setId(rs.getLong("id"));
        signoMarcas.setMarca(rs.getString("marca"));
        signoMarcas.setTipoGenero(rs.getString("tipogenero"));
        signoMarcas.setDescripcionLogo(rs.getString("descripcionlogo"));
        signoMarcas.setEstado(rs.getString("estado"));
        signoMarcas.setFormularioId(rs.getLong("formulario_id"));
                
        return signoMarcas;
        
    }
    
    
    
}
