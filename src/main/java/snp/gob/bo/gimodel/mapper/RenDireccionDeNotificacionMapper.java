/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package snp.gob.bo.gimodel.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import snp.gob.bo.gimodel.entidad.RenDireccionDeNotificacion;
import snp.gob.bo.gimodel.entidad.RenRenovacion;
import snp.gob.bo.gimodel.entidad.RenSolicitanteApoderado;
import snp.gob.bo.gimodel.entidad.RenSolicitudRenovacion;

/**
 *
 * @author 
 */
public class RenDireccionDeNotificacionMapper implements RowMapper<RenDireccionDeNotificacion> {
       /**
     * Método que permite mapear la tabla rensolicitanteapoderado
     *
     * @param re
     * @param i
     * @return RenRenovacion
     * @throws java.sql.SQLException
     */
    @Override
    public RenDireccionDeNotificacion  mapRow(ResultSet re, int i) throws SQLException {
        try {
            RenDireccionDeNotificacion renDireccionDeNotificacion = new RenDireccionDeNotificacion();
            renDireccionDeNotificacion.setIddirecciondenotificacion(re.getLong("iddirecciondenotificacion"));
            renDireccionDeNotificacion.setIdsolicitudrenovacion(re.getLong("idsolicitudrenovacion"));
            renDireccionDeNotificacion.setIdmodificacion(re.getLong("idmodificacion"));
            renDireccionDeNotificacion.setIdlogtrans(re.getLong("idlogtrans"));
            renDireccionDeNotificacion.setCiudad_notificacion(re.getString("ciudad_notificacion"));
            renDireccionDeNotificacion.setZona_barrio(re.getString("zona_barrio"));
            renDireccionDeNotificacion.setAvenida_calle(re.getString("avenida_calle"));
            renDireccionDeNotificacion.setNumero(re.getString("numero"));
            renDireccionDeNotificacion.setEdificio(re.getString("edificio"));
            renDireccionDeNotificacion.setPiso(re.getString("piso"));
            renDireccionDeNotificacion.setDepartamento(re.getString("departamento"));
            renDireccionDeNotificacion.setReferencia_direccion(re.getString("referencia_direccion"));
            renDireccionDeNotificacion.setCorreo_electronico(re.getString("correo_electronico"));
            renDireccionDeNotificacion.setTelefono(re.getString("telefono"));
            renDireccionDeNotificacion.setCelular(re.getString("celular"));
            renDireccionDeNotificacion.setEstado(re.getString("estado"));
            return renDireccionDeNotificacion;
        } catch (SQLException e) {
            throw e;
        }
    }
}
