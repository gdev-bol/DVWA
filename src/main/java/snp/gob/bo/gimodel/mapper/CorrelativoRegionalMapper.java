        /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import snp.gob.bo.gimodel.entidad.CorrelativoRegional;

/**
 *
 * @author ChanoRojas
 */
public class CorrelativoRegionalMapper implements RowMapper<CorrelativoRegional> {
    @Override
    public CorrelativoRegional mapRow(ResultSet rs, int i) throws SQLException {
        CorrelativoRegional correlativoRegional = new CorrelativoRegional();
        correlativoRegional.setIdcorrelativoregional(rs.getLong("idcorrelativoregional"));
        correlativoRegional.setIdregional(rs.getLong("idregional"));
        correlativoRegional.setIdcorrelativo(rs.getLong("idcorrelativo"));
        correlativoRegional.setIdlogtrans(rs.getLong("idlogtrans"));
        correlativoRegional.setTipo_tramite(rs.getString("tipo_tramite"));
        correlativoRegional.setEstado(rs.getString("estado"));
        return correlativoRegional;
    }
}
