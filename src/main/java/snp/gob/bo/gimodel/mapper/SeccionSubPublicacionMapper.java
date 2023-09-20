/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import snp.gob.bo.gimodel.entidad.BusquedaMarcaResultado;
import snp.gob.bo.gimodel.entidad.SeccionSubPublicacion;

/**
 *
 * @author Ruben Ramirez
 * @version 1.0, 03/11/2016
 * @see SeccionSubPublicar
 */
public class SeccionSubPublicacionMapper implements RowMapper {
    
    @Override
    public SeccionSubPublicacion mapRow(ResultSet rs, int i) throws SQLException {
        
        SeccionSubPublicacion seccionSubPublicacion = new SeccionSubPublicacion();
        
        seccionSubPublicacion.setIdSeccionSubPublicacion(rs.getLong("idseccionsubpublicacion"));
        seccionSubPublicacion.setIdDominio(rs.getLong("iddominio"));
        seccionSubPublicacion.setIdLogTrans(rs.getLong("idlogtrans"));
        seccionSubPublicacion.setSeccion(rs.getInt("seccion"));
        seccionSubPublicacion.setSubSeccion(rs.getInt("subseccion"));
        seccionSubPublicacion.setDescripcion(rs.getString("descripcion"));
        seccionSubPublicacion.setEstado(rs.getString("estado"));

        return seccionSubPublicacion;
    }
    
}
