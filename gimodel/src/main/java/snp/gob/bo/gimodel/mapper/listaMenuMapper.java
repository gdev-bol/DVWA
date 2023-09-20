/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.mapper;

import org.springframework.jdbc.core.RowMapper;
import snp.gob.bo.gimodel.entidad.listaMenu;import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author levi
 */
public class listaMenuMapper implements RowMapper<listaMenu>{
    
    @Override
    public listaMenu mapRow(ResultSet rs, int i) throws SQLException {
        listaMenu usuarioRol = new listaMenu();
        //usuarioRol.setIdExpediente(rs.getLong("idexpediente"));
        usuarioRol.setNombre(rs.getString("nombre"));
        usuarioRol.setDescripcion(rs.getString("descripcion"));
        usuarioRol.setSubmenu(rs.getString("submenu"));
        usuarioRol.setUrl(rs.getString("url"));
        usuarioRol.setAcceso(rs.getString("acceso"));
        //System.out.println("Setea hasta auqi0");
        usuarioRol.setFecha_vig_ini(rs.getTimestamp("fecha_vig_ini"));
       // System.out.println("Setea hasta auqi2");
        usuarioRol.setFecha_vig_fin(rs.getTimestamp("fecha_vig_fin"));
          usuarioRol.setEstado(rs.getString("estado"));
        return usuarioRol;
        
        
        
    }
    
}
