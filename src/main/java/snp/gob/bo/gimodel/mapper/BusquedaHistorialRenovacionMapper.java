/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import snp.gob.bo.gimodel.entidad.BusquedaHistorialRenovacion;

/**
 *
 * @author Susana Escobar Paz
 * @version 1.0, 08/12/2017
 * @see BusquedaHistorialRenovacion
 */
public class BusquedaHistorialRenovacionMapper implements RowMapper {

    @Override
    public BusquedaHistorialRenovacion mapRow(ResultSet rs, int i) throws SQLException {
        BusquedaHistorialRenovacion busquedaHistorialRenovacion = new BusquedaHistorialRenovacion();
        busquedaHistorialRenovacion.setIdmodificacion(rs.getLong("idmodificacion"));
        busquedaHistorialRenovacion.setNumero_resolucion(rs.getString("numero_resolucion"));
        busquedaHistorialRenovacion.setFecha_resolucion(rs.getTimestamp("fecha_resolucion"));
        busquedaHistorialRenovacion.setOrden(rs.getInt("orden"));

        busquedaHistorialRenovacion.setIdsolicitudrenovacion(rs.getLong("idsolicitudrenovacion"));
        busquedaHistorialRenovacion.setRenovacion(rs.getString("renovacion"));
        busquedaHistorialRenovacion.setResolucion_renovacion(rs.getString("resolucion_renovacion"));
        busquedaHistorialRenovacion.setFecha_resolucion_ren(rs.getTimestamp("fecha_resolucion_ren"));
        busquedaHistorialRenovacion.setOrden_ren(rs.getInt("orden_ren"));
        busquedaHistorialRenovacion.setInicio_concesion(rs.getTimestamp("inicio_concesion"));

        return busquedaHistorialRenovacion;
    }

}
