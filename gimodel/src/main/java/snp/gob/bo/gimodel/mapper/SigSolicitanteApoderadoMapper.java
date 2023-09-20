/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import snp.gob.bo.gimodel.entidad.SigSolicitanteApoderado;

/**
 *
 * @author Eddy Valero
 * @version 1.0, 27/08/2016
 */
public class SigSolicitanteApoderadoMapper implements RowMapper<SigSolicitanteApoderado> {

    @Override
    public SigSolicitanteApoderado mapRow(ResultSet rs, int i) throws SQLException {
        
        SigSolicitanteApoderado sigSolicitanteApoderado = new SigSolicitanteApoderado();
        
        sigSolicitanteApoderado.setIdSolicitanteApoderado(rs.getLong("idsolicitanteapoderado"));
        sigSolicitanteApoderado.setIdSignoMarca(rs.getLong("idsignomarca"));
        sigSolicitanteApoderado.setIdLogTrans(rs.getLong("idlogtrans"));
        sigSolicitanteApoderado.setTipoTitular(rs.getString("tipo_titular"));
        sigSolicitanteApoderado.setTipoPersona(rs.getString("tipo_persona"));
        sigSolicitanteApoderado.setNombreRazonSocial(rs.getString("nombre_razonsocial"));
        sigSolicitanteApoderado.setPrimerApellido(rs.getString("primer_apellido"));
        sigSolicitanteApoderado.setSegundoApellido(rs.getString("segundo_apellido"));
        sigSolicitanteApoderado.setNumeroDocumento(rs.getString("numero_documento"));
        sigSolicitanteApoderado.setTipoDocumento(rs.getString("tipo_documento"));
        sigSolicitanteApoderado.setLugarExpedicion(rs.getString("lugar_expedicion"));
        sigSolicitanteApoderado.setPais(rs.getString("pais"));
        sigSolicitanteApoderado.setGenero(rs.getString("genero"));
        sigSolicitanteApoderado.setSolicitudDepartamento(rs.getString("solicitud_departamento"));
        sigSolicitanteApoderado.setPoder(rs.getString("poder"));
        sigSolicitanteApoderado.setDireccion(rs.getString("direccion"));
        sigSolicitanteApoderado.setTelefono(rs.getString("telefono"));
        sigSolicitanteApoderado.setCelular(rs.getString("celular"));
        sigSolicitanteApoderado.setEmail(rs.getString("email"));
        sigSolicitanteApoderado.setFax(rs.getString("fax"));
        sigSolicitanteApoderado.setEstado(rs.getString("estado"));
        sigSolicitanteApoderado.setIdSipi(rs.getLong("id_sipi"));
                
        return sigSolicitanteApoderado;
    }

}
