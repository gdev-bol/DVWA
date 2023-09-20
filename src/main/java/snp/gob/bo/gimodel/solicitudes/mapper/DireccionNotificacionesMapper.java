/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package snp.gob.bo.gimodel.solicitudes.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import snp.gob.bo.gimodel.solicitudes.entidades.DireccionNotificaciones;

/**
 *
 * @author Eddy Valero
 * @version 1.0, 23/08/2016
 */
public class DireccionNotificacionesMapper implements RowMapper<DireccionNotificaciones> {

    @Override
    public DireccionNotificaciones mapRow(ResultSet rs, int i) throws SQLException {
        
        DireccionNotificaciones direccionNotificaciones = new DireccionNotificaciones();
        
        
        direccionNotificaciones.setId(rs.getLong("id"));
        direccionNotificaciones.setCiudadNotificacion(rs.getString("ciudadnotificacion"));
        direccionNotificaciones.setZonaBarrio(rs.getString("zonabarrio"));
        direccionNotificaciones.setAvenidaCalle(rs.getString("avenidacalle"));
        direccionNotificaciones.setNumeroDomicilio(rs.getString("numerodomicilio"));
        direccionNotificaciones.setNombreEdificio(rs.getString("nombreedificio"));
        direccionNotificaciones.setPiso(rs.getString("piso"));
        direccionNotificaciones.setDepartamento(rs.getString("departamento"));
        direccionNotificaciones.setTelefono(rs.getString("telefono"));
        direccionNotificaciones.setCelular(rs.getString("celular"));
        direccionNotificaciones.setCorreoelectronico(rs.getString("correoelectronico"));
        direccionNotificaciones.setReferencia(rs.getString("referencia"));
        direccionNotificaciones.setEstado(rs.getString("estado"));
        direccionNotificaciones.setFormularioId(rs.getLong("formulario_id"));
        
        return direccionNotificaciones;
        
    }
    
    
    
}
