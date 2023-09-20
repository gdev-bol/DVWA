/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import snp.gob.bo.gimodel.entidad.BusquedaOposicion;

/**
 *
 * @author Luis Angel Quispe Limachi
 */
public class BusquedaOposicionMapper implements RowMapper {

    @Override
    public BusquedaOposicion mapRow(ResultSet rs, int i) throws SQLException {

        BusquedaOposicion busquedaOposicion = new BusquedaOposicion();

        busquedaOposicion.setPublicacion(rs.getLong("publicacion"));
        busquedaOposicion.setFechapresentacion(rs.getTimestamp("fechapresentacion"));
        busquedaOposicion.setNrocasopubl(rs.getInt("nrocasopubl"));
        busquedaOposicion.setRegistro(rs.getInt("registro"));
        busquedaOposicion.setSerie(rs.getString("serie"));        
        busquedaOposicion.setExpdientesm(rs.getLong("expedientesm"));        
        busquedaOposicion.setMarca(rs.getString("marca"));
        busquedaOposicion.setFirma(rs.getString("firma"));
        busquedaOposicion.setApoderado(rs.getString("apoderado"));  
        busquedaOposicion.setEstado(rs.getString("estado"));
        busquedaOposicion.setFechalim(rs.getTimestamp("fechalim"));        
        busquedaOposicion.setUbicacion(rs.getString("ubicacion"));
        busquedaOposicion.setGaceta(rs.getInt("gaceta"));
       
        return busquedaOposicion;

    }

}
