/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package snp.gob.bo.gimodel.solicitudes.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import snp.gob.bo.gimodel.solicitudes.entidades.SmSignoClaseNizas;

/**
 *
 * @author Eddy Valero
 * @version 1.0, 23/08/2016
 */
public class SmSignoClasesNizasMapper implements RowMapper<SmSignoClaseNizas> {

    @Override
    public SmSignoClaseNizas mapRow(ResultSet rs, int i) throws SQLException {
        
        SmSignoClaseNizas smSignoClaseNizas  = new  SmSignoClaseNizas();
        
        smSignoClaseNizas.setClaseNizaId(rs.getLong("claseniza_id"));
        smSignoClaseNizas.setSignoMarcaId(rs.getLong("signomarca_id"));
        smSignoClaseNizas.setNumeroClaseNiza(rs.getLong("numeroclaseniza"));
        smSignoClaseNizas.setListaProductos(rs.getString("listaproductos"));
        
                
        return smSignoClaseNizas;
        
    }
    
    
    
}
