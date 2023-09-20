/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import snp.gob.bo.gimodel.entidad.SigDireccionDeNotificacion;

/**
 *
 * @author Eddy Valero
 * @version 1.0, 29/08/2016
 */
public class SigDireccionDeNotificacionMapper implements RowMapper<SigDireccionDeNotificacion> {

    @Override
    public SigDireccionDeNotificacion mapRow(ResultSet rs, int i) throws SQLException {
        
        SigDireccionDeNotificacion sigDireccionNotificacion = new SigDireccionDeNotificacion();
        
        sigDireccionNotificacion.setIdDireccionDeNotificacion(rs.getLong("iddirecciondenotificacion"));
        sigDireccionNotificacion.setIdSignoMarca(rs.getLong("idsignomarca"));
        sigDireccionNotificacion.setIdLogTrans(rs.getLong("idlogtrans"));
        sigDireccionNotificacion.setCiudadNotificacion(rs.getString("ciudad_notificacion"));
        sigDireccionNotificacion.setZonaBarrio(rs.getString("zona_barrio"));
        sigDireccionNotificacion.setAvenidaCalle(rs.getString("avenida_calle"));
        sigDireccionNotificacion.setNumero(rs.getString("numero"));
        sigDireccionNotificacion.setEdificio(rs.getString("edificio"));
        sigDireccionNotificacion.setPiso(rs.getString("piso"));
        sigDireccionNotificacion.setDepartamento(rs.getString("departamento"));
        sigDireccionNotificacion.setReferenciaDireccion(rs.getString("referencia_direccion"));
        sigDireccionNotificacion.setCorreoElectronico(rs.getString("correo_electronico"));
        sigDireccionNotificacion.setTelefono(rs.getString("telefono"));
        sigDireccionNotificacion.setCelular(rs.getString("celular"));
        sigDireccionNotificacion.setEstado(rs.getString("estado"));
        return sigDireccionNotificacion;
    }

}
