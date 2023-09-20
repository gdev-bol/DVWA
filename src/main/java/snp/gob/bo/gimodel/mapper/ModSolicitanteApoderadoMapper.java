/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import snp.gob.bo.gimodel.entidad.ModSolicitanteApoderado;

/**
 *
 * @author susana
 */
public class ModSolicitanteApoderadoMapper implements RowMapper<ModSolicitanteApoderado> {

    @Override
    public ModSolicitanteApoderado mapRow(ResultSet rs, int i) throws SQLException {
        ModSolicitanteApoderado solicitanteApoderado = new ModSolicitanteApoderado();
        solicitanteApoderado.setIdsolicitanteapoderado(rs.getLong("idsolicitanteapoderado"));
        solicitanteApoderado.setIdmodificacion(rs.getLong("idmodificacion"));
        solicitanteApoderado.setIdLogTrans(rs.getLong("idLogTrans"));
        solicitanteApoderado.setTipo_titular(rs.getString("tipo_titular"));
        solicitanteApoderado.setTipo_persona(rs.getString("tipo_persona"));
        solicitanteApoderado.setNombre_razonsocial(rs.getString("nombre_razonsocial"));
        solicitanteApoderado.setPrimer_apellido(rs.getString("primer_apellido"));
        solicitanteApoderado.setSegundo_apellido(rs.getString("segundo_apellido"));
        solicitanteApoderado.setNumero_documento(rs.getString("numero_documento"));
        solicitanteApoderado.setTipo_documento(rs.getString("tipo_documento"));
        solicitanteApoderado.setLugar_expedicion(rs.getString("lugar_expedicion"));
        solicitanteApoderado.setPais(rs.getString("pais"));
        solicitanteApoderado.setGenero(rs.getString("genero"));
        solicitanteApoderado.setSolicitud_departamento(rs.getString("solicitud_departamento"));
        solicitanteApoderado.setPoder(rs.getString("poder"));
        solicitanteApoderado.setDireccion(rs.getString("direccion"));
        solicitanteApoderado.setTelefono(rs.getString("telefono"));
        solicitanteApoderado.setCelular(rs.getString("celular"));
        solicitanteApoderado.setEmail(rs.getString("email"));
        solicitanteApoderado.setFax(rs.getString("fax"));
        solicitanteApoderado.setEstado(rs.getString("estado"));
        solicitanteApoderado.setIdSipi(rs.getLong("id_sipi"));
       // smsolicitanteApoderado.setIdsolicitanteapoderado_modificar(rs.getLong("idsolicitanteapoderado_modificar"));
        return solicitanteApoderado;
    }
}
