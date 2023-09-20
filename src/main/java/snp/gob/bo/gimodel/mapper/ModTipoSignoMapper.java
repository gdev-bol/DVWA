/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import snp.gob.bo.gimodel.entidad.ModTipoSigno;

/**
 *
 * @author susana
 */
public class ModTipoSignoMapper implements RowMapper<ModTipoSigno> {

    @Override
    public ModTipoSigno mapRow(ResultSet rs, int i) throws SQLException {
        ModTipoSigno modtiposigno = new ModTipoSigno();
        modtiposigno.setIdtiposigno(rs.getLong("idtiposigno"));
        modtiposigno.setIdmodificacion(rs.getLong("idmodificacion"));
        modtiposigno.setIdlogtrans(rs.getLong("idlogtrans"));
        modtiposigno.setTipo_signo(rs.getString("tipo_signo"));
        modtiposigno.setDescripcion_otro(rs.getString("descripcion_otro"));
        modtiposigno.setEstado(rs.getString("estado"));
        return modtiposigno;
    }
}
