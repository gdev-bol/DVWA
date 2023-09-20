/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import snp.gob.bo.gimodel.entidad.ModObservacionTramite;

/**
 *
 * @author Sushy
 */
public class ModObservacionTramiteMapper implements RowMapper<ModObservacionTramite> {
    @Override
    public ModObservacionTramite mapRow(ResultSet rs, int i) throws SQLException {
        ModObservacionTramite modobservaciontramite = new ModObservacionTramite();
        modobservaciontramite.setIdobservaciontramite(rs.getLong("idobservaciontramite"));
        modobservaciontramite.setIdmodificacion(rs.getLong("idmodificacion"));
        modobservaciontramite.setIdusuario(rs.getLong("idusuario"));
        modobservaciontramite.setIdlogtrans(rs.getLong("idlogtrans"));
        modobservaciontramite.setEditable(rs.getBoolean("editable"));
        modobservaciontramite.setFecha_observacion(rs.getTimestamp("fecha_observacion"));
        modobservaciontramite.setEtapa_tramite(rs.getString("etapa_tramite"));
        modobservaciontramite.setDescripcion(rs.getString("descripcion"));
        modobservaciontramite.setEstado(rs.getString("estado"));
        return modobservaciontramite;
    }
}
