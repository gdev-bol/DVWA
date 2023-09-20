/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import snp.gob.bo.gimodel.entidad.OpoSolicitanteapoderado;

/**
 *
 * @author Luis Angel Quispe Limachi
 */
public class OpoSolicitanteapoderadoMapper implements RowMapper<OpoSolicitanteapoderado> {
/**
     * Método que permite mapear la tabla OpoSolicitanteapoderado
     *
     * @param rs
     * @param i
     * @return OpoSolicitanteapoderado
     * @throws java.sql.SQLException
     */
    @Override
    public OpoSolicitanteapoderado mapRow(ResultSet rs, int i) throws SQLException {
        try {
            OpoSolicitanteapoderado oposolicitanteapoderado = new OpoSolicitanteapoderado();

        oposolicitanteapoderado.setIdsolicitanteapoderado(rs.getLong("idsolicitanteapoderado"));
        oposolicitanteapoderado.setIdmarcademandada(rs.getLong("idmarcademandada"));
        oposolicitanteapoderado.setIdmarcademandante(rs.getLong("idmarcademandante"));
        oposolicitanteapoderado.setNombre_razonsocial(rs.getString("nombre_razonsocial"));
        oposolicitanteapoderado.setPrimer_apellido(rs.getString("primer_apellido"));
        oposolicitanteapoderado.setSegundo_apellido(rs.getString("segundo_apellido"));
        oposolicitanteapoderado.setNumero_documento(rs.getString("numero_documento"));
        oposolicitanteapoderado.setTipo_documento(rs.getString("tipo_documento"));
        oposolicitanteapoderado.setLugar_expedicion(rs.getString("lugar_expedicion"));
        oposolicitanteapoderado.setPais(rs.getString("pais"));
        oposolicitanteapoderado.setGenero(rs.getString("genero"));
        oposolicitanteapoderado.setSolicitud_depa(rs.getString("solicitud_depa"));
        oposolicitanteapoderado.setPoder(rs.getString("poder"));
        oposolicitanteapoderado.setDireccion(rs.getString("direccion"));
        oposolicitanteapoderado.setTelefono(rs.getString("telefono"));
        oposolicitanteapoderado.setCelular(rs.getString("celular"));
        oposolicitanteapoderado.setEmail(rs.getString("email"));
        oposolicitanteapoderado.setFax(rs.getString("fax"));
        oposolicitanteapoderado.setTiposoliapo(rs.getString("tiposoliapo"));
        oposolicitanteapoderado.setTipo_titular(rs.getString("tipo_titular"));
        oposolicitanteapoderado.setNropoder(rs.getString("nropoder"));
        oposolicitanteapoderado.setTipo_persona(rs.getString("tipo_persona"));
        oposolicitanteapoderado.setEstado(rs.getString("estado"));

        return oposolicitanteapoderado;
        } catch (SQLException e) {
            throw e;
        }
        
        
    }

}
