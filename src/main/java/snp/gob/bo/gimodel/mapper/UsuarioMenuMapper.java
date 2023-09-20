/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.mapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import snp.gob.bo.gimodel.entidad.UsuarioMenu;

/**
 *
 * @author levi
 */
public class UsuarioMenuMapper implements RowMapper<UsuarioMenu> {
    @Override
    public UsuarioMenu mapRow(ResultSet rs, int i) throws SQLException {
        UsuarioMenu usuarioMenu = new UsuarioMenu();
        usuarioMenu.setIdusuariomenu(rs.getLong("idusuariomenu"));
        usuarioMenu.setIdusuario(rs.getLong("idusuario"));
        usuarioMenu.setIdmenu(rs.getLong("idmenu"));
        usuarioMenu.setIdlogtrans(rs.getLong("idlogtrans"));
        
        return usuarioMenu;
   }
}
