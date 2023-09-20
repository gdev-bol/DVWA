/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package snp.gob.bo.gimodel.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import snp.gob.bo.gimodel.entidad.ModResolucion;

/**
 *
 * @author Sushy
 */
public class ModResolucionMapper implements RowMapper<ModResolucion> {
    @Override
    public ModResolucion mapRow(ResultSet rs, int i) throws SQLException {
        ModResolucion modresolucion = new ModResolucion();
        modresolucion.setIdresolucion(rs.getLong("idresolucion"));
        modresolucion.setIdmodificacion(rs.getLong("idmodificacion"));
        modresolucion.setNumero_resolucion(rs.getInt("numero_resolucion"));
        modresolucion.setGestion_resolucion(rs.getInt("gestion_resolucion"));
        modresolucion.setFecha_resolucion(rs.getTimestamp("fecha_resolucion"));
        modresolucion.setObservacion_resolucion(rs.getString("observacion_resolucion"));
        modresolucion.setDocumento_resolucion(rs.getString("documento_resolucion"));
        modresolucion.setEstado(rs.getString("estado"));
        return modresolucion;
    }
}
