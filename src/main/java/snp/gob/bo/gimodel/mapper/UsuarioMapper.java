/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import snp.gob.bo.gimodel.entidad.Seguimiento;
import snp.gob.bo.gimodel.entidad.Usuario;

/**
 * Entidad responsable de la Tabla Seguimiento
 *
 * @author Eddy Valero
 * @see SigSeguimientoMapper
 * @version 1.0, 08/09/2016
 */
public class UsuarioMapper implements RowMapper<Usuario> {

    @Override
    public Usuario mapRow(ResultSet rs, int i) throws SQLException {
        Usuario usuario = new Usuario();
        

        usuario.setIdusuario(rs.getLong("idusuario"));
        usuario.setIdlogTrans(rs.getLong("idlogtrans"));
        usuario.setNombre(rs.getString("nombre"));
        usuario.setPrimer_apellido(rs.getString("primer_apellido"));
        usuario.setSegundo_apellido(rs.getString("segundo_apellido"));
        usuario.setLogin(rs.getString("login"));
        usuario.setContrasenia(rs.getString("contrasenia"));
        usuario.setCargo(rs.getString("cargo"));
        usuario.setIdregional(rs.getLong("idregional"));
        usuario.setNumero_documento(rs.getString("numero_documento"));
        usuario.setTipo_documento(rs.getString("tipo_documento"));
        usuario.setLugar_expedicion(rs.getString("lugar_expedicion"));
        usuario.setCorreo_electronico(rs.getString("correo_electronico"));
        usuario.setEstado(rs.getString("estado"));
        
        return usuario;
    }

}
