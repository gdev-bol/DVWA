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
import org.springframework.jdbc.core.RowMapper;
import snp.gob.bo.gimodel.entidad.MenuPaginaUsuario;


/**
 *
 * @author levi
 */
public class MenuPaginaUsuarioMapper implements RowMapper<MenuPaginaUsuario>{
    
    
    @Override
    public MenuPaginaUsuario mapRow(ResultSet rs, int i) throws SQLException {
          MenuPaginaUsuario mpagina = new MenuPaginaUsuario();
          mpagina.setSistema(rs.getString("sistema"));
          mpagina.setIdmenu(rs.getLong("idmenu"));
          mpagina.setMenu(rs.getString("menu"));
          mpagina.setIdpagina(rs.getLong("idpagina"));
          mpagina.setPagina(rs.getString("pagina"));
          mpagina.setMuestra(rs.getBoolean("muestra"));
          mpagina.setEstado(rs.getString("estado"));
          return mpagina;
    }                            
}
