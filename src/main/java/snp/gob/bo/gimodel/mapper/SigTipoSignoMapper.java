/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package snp.gob.bo.gimodel.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import snp.gob.bo.gimodel.entidad.Dominio;
import snp.gob.bo.gimodel.entidad.SigTipoSigno;

/**
 *
 * @author Eddy Valero
 * @version 1.0, 26/08/2016
 */
public class SigTipoSignoMapper implements RowMapper<SigTipoSigno> {

    @Override
    public SigTipoSigno mapRow(ResultSet rs, int i) throws SQLException {
        
        SigTipoSigno sigTipoSigno = new SigTipoSigno();
        
        sigTipoSigno.setIdTipoSigno(rs.getLong("idtiposigno"));
        sigTipoSigno.setIdSignoMarca(rs.getLong("idsignomarca"));
        sigTipoSigno.setIdLogTrans(rs.getLong("idlogtrans"));
        sigTipoSigno.setTipoSigno(rs.getString("tipo_signo"));
        sigTipoSigno.setDescripcionOtro(rs.getString("descripcion_otro"));
        sigTipoSigno.setEstado(rs.getString("estado"));
        
                
        return sigTipoSigno;
        
    }
    
    
    
}
