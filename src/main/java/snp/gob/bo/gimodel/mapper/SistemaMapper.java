/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import org.springframework.jdbc.core.RowMapper;
import snp.gob.bo.gimodel.entidad.Sistema;

/**
 *
 * @author levi
 */
public class SistemaMapper implements RowMapper<Sistema>{
    @Override
    public Sistema mapRow(ResultSet rs, int i) throws SQLException {
          Sistema sistema = new Sistema();

          sistema.setIdsistema(rs.getLong("idsistema"));
          sistema.setIdlogtrans(rs.getLong("idlogtrans"));
          sistema.setDescripcion(rs.getString("descripcion"));
          sistema.setEstado(rs.getString("estado"));
          
          
          
          
           return sistema;

    }
}
