/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package snp.gob.bo.gimodel.solicitudes.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import snp.gob.bo.gimodel.entidad.Dominio;
import snp.gob.bo.gimodel.mapper.*;
import snp.gob.bo.gimodel.solicitudes.entidades.Apoderados;

/**
 *
 * @author Eddy Valero
 * @version 1.0, 23/08/2016
 */
public class ApoderadosMapper implements RowMapper<Apoderados> {

    @Override
    public Apoderados mapRow(ResultSet rs, int i) throws SQLException {
        
        Apoderados apoderado = new Apoderados();
        
        apoderado.setId(rs.getLong("id"));
        apoderado.setNombres(rs.getString("nombres"));
        apoderado.setPrimerApellido(rs.getString("primerapellido"));
        apoderado.setSegundoApellido(rs.getString("segundoapellido"));
        apoderado.setTipoDocumento(rs.getString("tipodocumento"));
        apoderado.setNumeroDocumento(rs.getString("numerodocumento"));
        apoderado.setLugarExpedicion(rs.getString("lugarexpedicion"));
        apoderado.setGenero(rs.getString("genero"));
        apoderado.setDomicilio(rs.getString("domicilio"));
        apoderado.setTelefono(rs.getString("telefono"));
        apoderado.setCelular(rs.getString("celular"));
        apoderado.setNumeroPoder(rs.getString("numeropoder"));
        apoderado.setFechaPoder(rs.getTimestamp("fechapoder"));
        apoderado.setFormularioId(rs.getLong("formulario_id"));
        apoderado.setCorreoElectronico(rs.getString("correoelectronico"));
        
        
        return apoderado;
        
    }
    
    
    
}
