/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package snp.gob.bo.gimodel.solicitudes.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import snp.gob.bo.gimodel.solicitudes.entidades.Solicitantes;

/**
 *
 * @author Eddy Valero
 * @version 1.0, 23/08/2016
 */
public class SolicitantesMapper implements RowMapper<Solicitantes> {

    @Override
    public Solicitantes mapRow(ResultSet rs, int i) throws SQLException {
        
        Solicitantes solicitantes  = new  Solicitantes();
        
        solicitantes.setId(rs.getLong("id"));
        solicitantes.setTipoPersona(rs.getString("tipopersona"));
        solicitantes.setTipoDocumento(rs.getString("tipodocumento"));
        solicitantes.setNumeroDocumento(rs.getString("numerodocumento"));
        solicitantes.setLugarExpedicion(rs.getString("lugarexpedicion"));
        solicitantes.setPais(rs.getString("pais"));
        solicitantes.setDepartamentoSolicitud(rs.getString("departamentosolicitud"));
        solicitantes.setNombreRazonSocial(rs.getString("nombrerazonsocial"));
        solicitantes.setPrimerApellido(rs.getString("primerapellido"));
        solicitantes.setSegundoApellido(rs.getString("segundoapellido"));
        solicitantes.setGenero(rs.getString("genero"));
        solicitantes.setDomicilio(rs.getString("domicilio"));
        solicitantes.setTelefono(rs.getString("telefono"));
        solicitantes.setCelular(rs.getString("celular"));
        solicitantes.setCorreoElectronico(rs.getString("correoelectronico"));
        solicitantes.setEstado(rs.getString("estado"));
        solicitantes.setFormularioId(rs.getLong("formulario_id"));
        
        return solicitantes;
        
    }
    
    
    
}
