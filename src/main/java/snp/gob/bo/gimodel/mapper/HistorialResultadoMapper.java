/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package snp.gob.bo.gimodel.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import snp.gob.bo.gimodel.entidad.HistorialResultado;

/**
 *
 * @author Eddy Valero
 * @version 1.0, 19/11/2016
 */
public class HistorialResultadoMapper implements RowMapper<HistorialResultado> {

    @Override
    public HistorialResultado mapRow(ResultSet rs, int i) throws SQLException {
        
        HistorialResultado historial = new HistorialResultado();
        historial.setPosicion(rs.getLong("posicion"));
        historial.setIdHistorial(rs.getLong("idhistorial"));
        historial.setId(rs.getLong("id"));
        historial.setIdUsuario(rs.getLong("idlogtrans"));
        historial.setIdLogTrans(rs.getLong("idusuario"));
        historial.setTipo(rs.getString("tipo"));
        historial.setOperacion(rs.getString("operacion"));
        historial.setEstadoMarcaDescripcion(rs.getString("estado_marca_descripcion"));
        historial.setObservacion(rs.getString("observacion"));
        historial.setUbicacionDescripcion(rs.getString("ubicacion_descripcion"));
        historial.setSeccion(rs.getString("seccion"));
        historial.setGestionRenovación(rs.getInt("gestion_renovacion"));
        historial.setDescripcion(rs.getString("descripcion"));
        historial.setDescripcionListaProductos(rs.getString("descripcion_lista_productos"));
        historial.setFechaOperacion(rs.getTimestamp("fecha_operacion"));
        historial.setUsuarioNombreCompleto(rs.getString("usuario_nombre_completo"));
        historial.setUsuario(rs.getString("usuario"));
        historial.setEstado(rs.getString("estado"));
        return historial;
    }
    
    
}
