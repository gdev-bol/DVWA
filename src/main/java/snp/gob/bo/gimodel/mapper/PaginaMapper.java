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
import snp.gob.bo.gimodel.entidad.Pagina;

/**
 *
 * @author levi
 */
public class PaginaMapper implements RowMapper<Pagina>{
    @Override
    public Pagina mapRow(ResultSet rs, int i) throws SQLException {
          Pagina pagina = new Pagina();

          pagina.setIdpagina(rs.getLong("idpagina"));
          pagina.setIdlogtrans(rs.getLong("idlogtrans"));
          pagina.setDescripcion(rs.getString("descripcion"));
          pagina.setUrl(rs.getString("url"));
          pagina.setEstado(rs.getString("estado"));
          return pagina;
          
          
 
}
}
