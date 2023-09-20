/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import snp.gob.bo.gimodel.entidad.UsuarioTramite;

/**
 * Entidad responsable del mapeo de la Tabla UsuarioTramite
 *
 * @author Eddy Valero
 * @see UsuarioTramiteMapper
 * @version 1.0, 18/05/2017
 */
public class UsuarioTramiteMapper implements RowMapper<UsuarioTramite> {

    @Override
    public UsuarioTramite mapRow(ResultSet rs, int i) throws SQLException {
        
        
        UsuarioTramite usuarioTramite = new UsuarioTramite();
        
        usuarioTramite.setIdUsuarioTramite(rs.getLong("idusuariotramite"));
        usuarioTramite.setTipoTramite(rs.getString("tipo_tramite"));
        usuarioTramite.setIdUsuarioExterno(rs.getInt("idusuarioexterno"));
        usuarioTramite.setId(rs.getLong("id"));
        usuarioTramite.setIdLogTrans(rs.getLong("idlogtrans"));
        usuarioTramite.setEstado(rs.getString("estado"));
        
        return usuarioTramite;
    }

}
