        /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package snp.gob.bo.gimodel.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import snp.gob.bo.gimodel.entidad.Correlativo;
import snp.gob.bo.gimodel.entidad.Recibo;

/**
 *
 * @author ChanoRojas
 */
public class CorrelativoMapper implements RowMapper<Correlativo> {
    @Override
    public Correlativo mapRow(ResultSet rs, int i) throws SQLException {
            Correlativo correlativo = new Correlativo();
             correlativo.setIdcorrelativo(rs.getLong("idcorrelativo"));
             correlativo.setIdlogtrans(rs.getLong("idlogtrans"));
             correlativo.setNombre_criterio(rs.getString("nombre_criterio"));
             correlativo.setIncremento(rs.getInt("incremento"));
             correlativo.setUltimo_numero_asignado(rs.getInt("ultimo_numero_asignado"));
             correlativo.setAcronimo(rs.getString("acronimo"));
             correlativo.setSeparador(rs.getString("separador"));
             correlativo.setGestion(rs.getInt("gestion"));
             correlativo.setEstado(rs.getString("estado"));
        return correlativo;
        
    }
    
}
