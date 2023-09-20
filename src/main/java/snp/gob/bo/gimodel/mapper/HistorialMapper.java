    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package snp.gob.bo.gimodel.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import org.springframework.jdbc.core.RowMapper;
import snp.gob.bo.gimodel.entidad.Historial;

/**
 *
 * @author eddy
 */
public class HistorialMapper implements RowMapper<Historial> {

    @Override
    public Historial mapRow(ResultSet rs, int i) throws SQLException {
        
        Historial historial = new Historial();
        historial.setIdUsuario(rs.getLong("idusuario"));
        historial.setId(rs.getLong("idsolicitudrenovacion"));
        historial.setIdHistorial(rs.getLong("idhistorial"));
        historial.setIdLogTrans(rs.getLong("idlogtrans"));
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
        historial.setEstado(rs.getString("estado"));
     //   historial.setNumeroSmSrMod(rs.getLong("numerosmsrmod"));
        return historial;
        
    }
    
    
}
