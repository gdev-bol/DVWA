/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import snp.gob.bo.gimodel.entidad.OpoNotificacion;

/**
 *
 * @author Luis Angel Quispe Limachi
 */
public class OpoNotificacionMapper implements RowMapper<OpoNotificacion> {

    /**
     * Método que permite mapear la tabla OpoNotificacion
     *
     * @param rs
     * @param i
     * @return oponotificacion
     * @throws java.sql.SQLException
     */
    @Override
    public OpoNotificacion mapRow(ResultSet rs, int i) throws SQLException {
        try {
            OpoNotificacion oponotificacion = new OpoNotificacion();

            oponotificacion.setIdnotificacion(rs.getLong("idnotificacion"));
            oponotificacion.setIdmarcademandada(rs.getLong("idmarcademandada"));
            oponotificacion.setIdmarcademandante(rs.getLong("idmarcademandante"));
            oponotificacion.setCiudad_notificacion(rs.getString("ciudad_notificacion"));
            oponotificacion.setZona_barrio(rs.getString("zona_barrio"));
            oponotificacion.setAvenida_calle(rs.getString("avenida_calle"));
            oponotificacion.setNumero(rs.getString("numero"));
            oponotificacion.setEdificio(rs.getString("edificio"));
            oponotificacion.setPiso(rs.getString("piso"));
            oponotificacion.setNumero_departamento(rs.getString("numero_departamento"));
            oponotificacion.setReferencia_direccion(rs.getString("referencia_direccion"));
            oponotificacion.setEmail(rs.getString("email"));
            oponotificacion.setTelefono(rs.getString("telefono"));
            oponotificacion.setCelular(rs.getString("celular"));
            oponotificacion.setEstado(rs.getString("estado"));
            


            return oponotificacion;
        } catch (SQLException e) {
            throw e;
        }

    }

}
