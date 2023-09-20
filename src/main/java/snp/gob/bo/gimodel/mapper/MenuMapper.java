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
import snp.gob.bo.gimodel.entidad.Menu;
/**
 *
 * @author levi
 */
public class MenuMapper implements RowMapper<Menu>{
    @Override
    public Menu mapRow(ResultSet rs, int i) throws SQLException {
          Menu menu = new Menu();

          menu.setIdmenu(rs.getLong("idmenu"));
          menu.setIdsistema(rs.getLong("idsistema"));
          menu.setIdlogtrans(rs.getLong("idlogtrans"));
          menu.setDescripcion(rs.getString("descripcion"));
          menu.setOrden(rs.getInt("orden"));
          menu.setEstado(rs.getString("estado"));
          
          

          
           return menu;

    }
}

