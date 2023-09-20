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
import snp.gob.bo.gimodel.entidad.MenuPagina;
/**
 *
 * @author levi
 */
public class MenuPaginaMapper implements RowMapper<MenuPagina>{
    
      @Override
    public MenuPagina mapRow(ResultSet rs, int i) throws SQLException {
          MenuPagina mpagina = new MenuPagina();

          mpagina.setIdmenupagina(rs.getLong("idmenupagina"));
          mpagina.setIdmenu(rs.getLong("idmenu"));
          mpagina.setIdpagina(rs.getLong("idpagina"));
          mpagina.setIdlogtrans(rs.getLong("idlogtrans"));
          mpagina.setOrden(rs.getInt("orden"));
          mpagina.setEstado(rs.getString("estado"));
          return mpagina;
          
          
 
}
    
}
