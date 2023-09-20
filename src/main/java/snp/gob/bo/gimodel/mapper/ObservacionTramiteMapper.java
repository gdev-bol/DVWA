/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package snp.gob.bo.gimodel.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import snp.gob.bo.gimodel.entidad.ObservacionTramite;

/**
 *
 * @author eddy
 */
public class ObservacionTramiteMapper implements RowMapper<ObservacionTramite> {

    @Override
    public ObservacionTramite mapRow(ResultSet rs, int i) throws SQLException {
        ObservacionTramite observacionTramite = new ObservacionTramite();
        observacionTramite.setIdObservacionTramite(rs.getLong("idobservaciontramite"));
        observacionTramite.setId(rs.getLong("idsignomarca"));
        observacionTramite.setIdUsuario(rs.getLong("idusuario"));
        observacionTramite.setIdLogTrans(rs.getLong("idlogtrans"));
        observacionTramite.setEditable(rs.getBoolean("editable"));
        observacionTramite.setFechaObservacion(rs.getTimestamp("fecha_observacion"));
        observacionTramite.setEtapaTramite(rs.getString("etapa_tramite"));
        observacionTramite.setDescripcion(rs.getString("descripcion"));
        observacionTramite.setEstado(rs.getString("estado"));
        
        return observacionTramite;
        
    }
    
    
}
