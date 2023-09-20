/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import snp.gob.bo.gimodel.entidad.OpoFlujoactividad;

/**
 *
 * @author Luis Angel Quispe Limachi
 */
public class OpoFlujoactividadMapper implements RowMapper<OpoFlujoactividad> {
 /**
     * Método que permite mapear la tabla opoflujoactividad
     *
     * @param rs
     * @param i
     * @return opoflujoactividad
     * @throws java.sql.SQLException
     */
    @Override
    public OpoFlujoactividad mapRow(ResultSet rs, int i) throws SQLException {
        try {
            OpoFlujoactividad opoflujoactividad = new OpoFlujoactividad();
        opoflujoactividad.setIdflujoactividad(rs.getLong("idflujoactividad"));
        opoflujoactividad.setIdactividad(rs.getLong("idactividad"));
        opoflujoactividad.setIdlogtrans(rs.getLong("idlogtrans"));
        opoflujoactividad.setTipo(rs.getString("tipo"));
        
        return opoflujoactividad;
        } catch (SQLException e) {
       throw e;
        }
        
        
        
    }

}
