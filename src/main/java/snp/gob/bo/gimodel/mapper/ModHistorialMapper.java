/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import snp.gob.bo.gimodel.entidad.ModHistorial;

/**
 *
 * @author Sushy
 */
public class ModHistorialMapper implements RowMapper<ModHistorial> {
   
    @Override
    public ModHistorial mapRow(ResultSet rs, int i) throws SQLException {
        ModHistorial modhistorial = new ModHistorial();
        modhistorial.setIdhistorial(rs.getLong("idhistorial"));
        modhistorial.setIdmodificacion(rs.getLong("idmodificacion"));
        modhistorial.setIdusuario(rs.getLong("idusuario"));
        modhistorial.setIdlogtrans(rs.getLong("idlogtrans"));
        modhistorial.setTipo_tramite(rs.getString("tipo_tramite"));
        modhistorial.setOperacion(rs.getString("operacion"));
        modhistorial.setEstado_marca(rs.getString("estado_marca"));
        modhistorial.setObservacion(rs.getString("observacion"));
        modhistorial.setUbicacion(rs.getString("ubicacion"));
        modhistorial.setSeccion(rs.getString("seccion"));
        modhistorial.setGestion_renovacion(rs.getInt("gestion_renovacion"));
        modhistorial.setDescripcion(rs.getString("descripcion"));
        modhistorial.setDescripcion_lista_productos(rs.getString("descripcion_lista_productos"));
        modhistorial.setFecha_operacion(rs.getTimestamp("fecha_operacion"));
        modhistorial.setEstado(rs.getString("estado"));
        return modhistorial;
    }
}
