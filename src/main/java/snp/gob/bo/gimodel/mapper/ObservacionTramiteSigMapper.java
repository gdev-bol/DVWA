/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package snp.gob.bo.gimodel.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import snp.gob.bo.gimodel.entidad.ObservacionTramiteSig;

/**
 *
 * @author placido
 */
public class ObservacionTramiteSigMapper implements RowMapper<ObservacionTramiteSig> {

    @Override
    public ObservacionTramiteSig mapRow(ResultSet rs, int i) throws SQLException {
        ObservacionTramiteSig observacionTramite = new ObservacionTramiteSig();
        observacionTramite.setIdObservacionTramite(rs.getLong("idobservaciontramite"));
        observacionTramite.setId(rs.getLong("idsignomarca"));
        observacionTramite.setNombreUsuario(rs.getString("nombre_completo"));
        observacionTramite.setIdLogTrans(rs.getLong("idlogtrans"));
        observacionTramite.setEditable(rs.getBoolean("editable"));
        observacionTramite.setFechaObservacion(rs.getTimestamp("fecha_observacion"));
        observacionTramite.setEtapaTramite(rs.getString("etapa_tramite"));
        observacionTramite.setDescripcion(rs.getString("descripcion"));
        observacionTramite.setEstado(rs.getString("estado"));
        
        return observacionTramite;
        
    }
    
    
}
