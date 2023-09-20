/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import snp.gob.bo.gimodel.entidad.UsuarioPagina;
import org.springframework.jdbc.core.RowMapper;
/**
 *
 * @author levi
 */
public class UsuarioPaginaMapper implements RowMapper<UsuarioPagina>{
    
    
     @Override
    public UsuarioPagina mapRow(ResultSet rs, int i) throws SQLException {
        UsuarioPagina usuarioPagina = new UsuarioPagina();
          usuarioPagina.setIdUsuarioPagina(rs.getLong("idusuariopagina"));
          usuarioPagina.setIdUsuario(rs.getLong("idusuario"));
          usuarioPagina.setIdPagina(rs.getLong("idpagina"));
          usuarioPagina.setIdLogTrans(rs.getLong("idlogtrans"));
          usuarioPagina.setAcceso(rs.getString("habilitado"));
          usuarioPagina.setHabilitado(rs.getString("habilitado"));
          usuarioPagina.setFecha_vig_ini(rs.getDate("fecha_vig_ini"));
          usuarioPagina.setFecha_vig_fin(rs.getDate("fecha_vig_fin"));
          //Aqui se puede ver una solucion para tatar con horas   http://stackoverflow.com/questions/10086053/comparing-hours-in-java
          usuarioPagina.setHora_vig_ini(rs.getString("hora_vig_ini"));
          usuarioPagina.setHora_vig_fin(rs.getString("hora_vig_fin"));
          usuarioPagina.setMuestra(rs.getBoolean("muestra"));
          usuarioPagina.setEstado(rs.getString("estado"));
          
          
          
          
          
        
        return usuarioPagina;
        
        
        
    }
}
