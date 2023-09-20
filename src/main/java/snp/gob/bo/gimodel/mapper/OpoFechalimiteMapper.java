/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package snp.gob.bo.gimodel.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import snp.gob.bo.gimodel.entidad.OpoFechalimite;

/**
 *
 * @author Luis Angel Quispe Limachi
 */
public class OpoFechalimiteMapper implements RowMapper<OpoFechalimite>{
 /**
     * Método que permite mapear la tabla opofechalimite
     *
     * @param rs
     * @param i
     * @return opofechalimite
     * @throws java.sql.SQLException
     */
    @Override
    public OpoFechalimite mapRow(ResultSet rs, int i) throws SQLException {
        try {
            OpoFechalimite opofechalimite=new OpoFechalimite();
        opofechalimite.setIdfechalimite(rs.getLong("idfechalimite"));
        opofechalimite.setIdevento(rs.getLong("idevento"));
        opofechalimite.setIdactividadplazo(rs.getLong("idactividadplazo"));
        opofechalimite.setIdoposicion(rs.getLong("idoposicion"));
        opofechalimite.setIdlogtrans(rs.getLong("idlogtrans"));
        opofechalimite.setOrden(rs.getInt("orden"));
        opofechalimite.setFechalimite(rs.getDate("fechalimite"));
        opofechalimite.setOrden_o(rs.getInt("orden_o"));
return opofechalimite;
        } catch (SQLException e) {
            throw e;
        }
  
       
    }
    
}
