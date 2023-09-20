/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import snp.gob.bo.gimodel.entidad.UsuarioEtapa;

/**
 *
 * @author Ruben Ramirez
 * @version 1.0, 26/10/2016
 * @see UsuarioEtapa
 */
public class UsuarioEtapaMapper implements RowMapper<UsuarioEtapa> {

    @Override
    public UsuarioEtapa mapRow(ResultSet rs, int i) throws SQLException {
        UsuarioEtapa usuarioEtapa = new UsuarioEtapa();
        usuarioEtapa.setIdUsuarioEtapa(rs.getLong("idusuarioetapa"));
        usuarioEtapa.setIdLogTrans(rs.getLong("idlogtrans"));
        usuarioEtapa.setIdUsuario(rs.getLong("idusuario"));
        usuarioEtapa.setIdEtapa(rs.getLong("idetapa"));
        usuarioEtapa.setEstado(rs.getString("estado"));
        return usuarioEtapa;
   }
    
}
