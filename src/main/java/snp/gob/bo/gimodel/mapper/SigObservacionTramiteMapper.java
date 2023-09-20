/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package snp.gob.bo.gimodel.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import snp.gob.bo.gimodel.entidad.SigObservacionTramite;

/**
 *
 * @author Eddy Valero
 * @version 1.0, 26/08/2016
 */
public class SigObservacionTramiteMapper implements RowMapper<SigObservacionTramite> {

    @Override
    public SigObservacionTramite mapRow(ResultSet rs, int i) throws SQLException {
        SigObservacionTramite sigObservacionTramite = new SigObservacionTramite();
        sigObservacionTramite.setIdObservacionTramite(rs.getLong("idobservaciontramite"));
        sigObservacionTramite.setIdSignoMarca(rs.getLong("idsignomarca"));
        sigObservacionTramite.setIdUsuario(rs.getLong("idusuario"));
        sigObservacionTramite.setIdLogTrans(rs.getLong("idlogtrans"));
        sigObservacionTramite.setEditable(rs.getBoolean("editable"));
        sigObservacionTramite.setFechaObservacion(rs.getTimestamp("fecha_observacion"));
        sigObservacionTramite.setEtapaTramite(rs.getString("etapa_tramite"));
        sigObservacionTramite.setDescripcion(rs.getString("descripcion"));
        sigObservacionTramite.setEstado(rs.getString("estado"));
        
        return sigObservacionTramite;
        
    }
    
    
}
