/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import snp.gob.bo.gimodel.entidad.ModDireccionDeNotificacion;

/**
 *
 * @author susana
 */
public class ModDireccionDeNotificacionMapper implements RowMapper<ModDireccionDeNotificacion>{
    
    @Override
    public ModDireccionDeNotificacion mapRow(ResultSet rs, int i) throws SQLException {
        ModDireccionDeNotificacion moddirecciondenotificacion = new ModDireccionDeNotificacion();
        moddirecciondenotificacion.setIddirecciondenotificacion(rs.getLong("iddirecciondenotificacion"));
        moddirecciondenotificacion.setIdmodificacion(rs.getLong("idmodificacion"));        
        moddirecciondenotificacion.setIdlogtrans(rs.getLong("idlogtrans"));
        moddirecciondenotificacion.setCiudad_notificacion(rs.getString("ciudad_notificacion"));
        moddirecciondenotificacion.setZona_barrio(rs.getString("zona_barrio"));
        moddirecciondenotificacion.setAvenida_calle(rs.getString("avenida_calle"));
        moddirecciondenotificacion.setNumero(rs.getString("numero"));
        moddirecciondenotificacion.setEdificio(rs.getString("edificio"));        
        moddirecciondenotificacion.setPiso(rs.getString("piso"));
        moddirecciondenotificacion.setDepartamento(rs.getString("departamento"));
        moddirecciondenotificacion.setCorreo_electronico(rs.getString("correo_electronico"));
        moddirecciondenotificacion.setReferencia_direccion(rs.getString("referencia_direccion"));
        moddirecciondenotificacion.setTelefono(rs.getString("telefono"));
        moddirecciondenotificacion.setCelular(rs.getString("celular"));
        moddirecciondenotificacion.setEstado(rs.getString("estado"));
        
        return moddirecciondenotificacion;        
    }
    
}
