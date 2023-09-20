/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package snp.gob.bo.gimodel.solicitudes.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import snp.gob.bo.gimodel.solicitudes.entidades.RenTitularRegistrados;

/**
 *
 * @author Eddy Valero
 * @version 1.0, 23/08/2016
 */
public class RenTitularRegistradoMapper implements RowMapper<RenTitularRegistrados> {

    @Override
    public RenTitularRegistrados mapRow(ResultSet rs, int i) throws SQLException {
        
        RenTitularRegistrados renTitularRegistrados  = new  RenTitularRegistrados();
        
        renTitularRegistrados.setId(rs.getLong("id"));
        
        renTitularRegistrados.setTipoPersona(rs.getString("tipopersona"));
        renTitularRegistrados.setNombreRazonSocial(rs.getString("nombre_razonsocial"));
        renTitularRegistrados.setPrimerApellido(rs.getString("primerapellido"));
        renTitularRegistrados.setSegundoApellido(rs.getString("segundoapellido"));
        renTitularRegistrados.setDireccion(rs.getString("direccion"));
        renTitularRegistrados.setEstado(rs.getString("estado"));
        renTitularRegistrados.setSolicitudRenovacionId(rs.getInt("solicitudrenovacion_id"));
        
        return renTitularRegistrados;
        
    }
    
    
    
}
