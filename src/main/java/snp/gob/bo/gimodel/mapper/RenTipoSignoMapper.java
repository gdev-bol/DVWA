        /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package snp.gob.bo.gimodel.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
    import snp.gob.bo.gimodel.entidad.RenTipoSigno;

/**
 *
 * @author ChanoRojas
 */
public class RenTipoSignoMapper implements RowMapper<RenTipoSigno> {
    @Override
    public RenTipoSigno mapRow(ResultSet rs, int i) throws SQLException {
            RenTipoSigno renTipoSigno = new RenTipoSigno();
             renTipoSigno.setIdtiposigno(rs.getLong("idtiposigno"));
             renTipoSigno.setIdsolicitudrenovacion(rs.getLong("idsolicitudrenovacion"));
             renTipoSigno.setIdlogtrans(rs.getLong("idlogtrans"));
             renTipoSigno.setTipo_signo(rs.getString("tipo_signo"));
             renTipoSigno.setDescripcion_otro(rs.getString("descripcion_otro"));
             renTipoSigno.setEstado(rs.getString("estado"));
        return renTipoSigno;
        
    }
    
}
