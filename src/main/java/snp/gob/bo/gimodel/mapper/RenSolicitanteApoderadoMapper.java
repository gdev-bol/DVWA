/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package snp.gob.bo.gimodel.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import snp.gob.bo.gimodel.entidad.RenRenovacion;
import snp.gob.bo.gimodel.entidad.RenSolicitanteApoderado;
import snp.gob.bo.gimodel.entidad.RenSolicitudRenovacion;

/**
 *
 * @author 
 */
public class RenSolicitanteApoderadoMapper implements RowMapper<RenSolicitanteApoderado> {
       /**
     * Método que permite mapear la tabla rensolicitanteapoderado
     *
     * @param re
     * @param i
     * @return RenRenovacion
     * @throws java.sql.SQLException
     */
    @Override
    public RenSolicitanteApoderado  mapRow(ResultSet re, int i) throws SQLException {
        try {
            RenSolicitanteApoderado renSolicitanteApoderado = new RenSolicitanteApoderado();
            renSolicitanteApoderado.setIdsolicitanteapoderado(re.getLong("idsolicitanteapoderado"));
            renSolicitanteApoderado.setIdsolicitudrenovacion(re.getLong("idsolicitudrenovacion"));
            renSolicitanteApoderado.setIdlogtrans(re.getLong("idlogtrans"));
            renSolicitanteApoderado.setTipo_titular(re.getString("tipo_titular"));
            renSolicitanteApoderado.setTipo_persona(re.getString("tipo_persona"));
            renSolicitanteApoderado.setNombre_razonsocial(re.getString("nombre_razonsocial"));
            renSolicitanteApoderado.setPrimer_apellido(re.getString("primer_apellido"));
            renSolicitanteApoderado.setSegundo_apellido(re.getString("segundo_apellido"));
            renSolicitanteApoderado.setNumero_documento(re.getString("numero_documento"));
            renSolicitanteApoderado.setTipo_documento(re.getString("tipo_documento"));
            renSolicitanteApoderado.setLugar_expedicion(re.getString("lugar_expedicion"));
            renSolicitanteApoderado.setPais(re.getString("pais"));
            renSolicitanteApoderado.setGenero(re.getString("genero"));
            renSolicitanteApoderado.setSolicitud_departamento(re.getString("solicitud_departamento"));
            renSolicitanteApoderado.setPoder(re.getString("poder"));
            renSolicitanteApoderado.setDireccion(re.getString("direccion"));
            renSolicitanteApoderado.setTelefono(re.getString("telefono"));
            renSolicitanteApoderado.setCelular(re.getString("celular"));
            renSolicitanteApoderado.setEmail(re.getString("email"));
            renSolicitanteApoderado.setFax(re.getString("fax"));
            renSolicitanteApoderado.setEstado(re.getString("estado"));
            renSolicitanteApoderado.setIdSipi(re.getLong("id_sipi"));
            return renSolicitanteApoderado;
        } catch (SQLException e) {
            throw e;
        }
    }
}
