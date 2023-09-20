/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package snp.gob.bo.gimodel.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import snp.gob.bo.gimodel.entidad.SigSignoClaseNiza;

/**
 *
 * @author Eddy Valero
 * @version 1.0, 26/08/2016
 */
public class SigSignoClaseNizaMapper implements RowMapper<SigSignoClaseNiza> {

    @Override
    public SigSignoClaseNiza mapRow(ResultSet rs, int i) throws SQLException {
        
        SigSignoClaseNiza sigSignoClaseNiza = new SigSignoClaseNiza();
        
        sigSignoClaseNiza.setIdSignoClaseNiza(rs.getLong("idsignoclaseniza"));
        sigSignoClaseNiza.setIdSignoMarca(rs.getLong("idsignomarca"));
        sigSignoClaseNiza.setIdClaseNiza(rs.getLong("idclaseniza"));
        sigSignoClaseNiza.setIdLogTrans(rs.getLong("idlogtrans"));
        sigSignoClaseNiza.setNumeroClaseNiza(rs.getLong("numero_clase_niza"));
        sigSignoClaseNiza.setListaProducto(rs.getString("lista_producto"));
        sigSignoClaseNiza.setEstado(rs.getString("estado"));
        sigSignoClaseNiza.setIdSipi(rs.getLong("id_sipi"));
        
                
        return sigSignoClaseNiza;
        
    }
    
    
    
}
