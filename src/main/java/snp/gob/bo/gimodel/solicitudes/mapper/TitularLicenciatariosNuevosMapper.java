/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.solicitudes.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import snp.gob.bo.gimodel.solicitudes.entidades.TitularLicenciatarios;
import snp.gob.bo.gimodel.solicitudes.entidades.TitularLicenciatariosNuevos;

/**
 *
 * @author Eddy Valero
 * @version 1.0, 24/10/2016
 */
public class TitularLicenciatariosNuevosMapper implements RowMapper<TitularLicenciatariosNuevos> {

    @Override
    public TitularLicenciatariosNuevos mapRow(ResultSet rs, int i) throws SQLException {

        TitularLicenciatariosNuevos titularLicenciatariosNuevos = new TitularLicenciatariosNuevos();

        titularLicenciatariosNuevos.setId(rs.getLong("id"));
        titularLicenciatariosNuevos.setTipoPersona(rs.getString("tipopersona"));
        titularLicenciatariosNuevos.setTipoTitular(rs.getString("tipotitular"));
        titularLicenciatariosNuevos.setNombreRazonSocial(rs.getString("nombre_razonsocial"));
        titularLicenciatariosNuevos.setPrimerApellido(rs.getString("primerapellido"));
        titularLicenciatariosNuevos.setSegundoApellido(rs.getString("segundoapellido"));
        titularLicenciatariosNuevos.setNroDocumento(rs.getString("nrodocumento"));
        titularLicenciatariosNuevos.setTipoDocumento(rs.getString("tipodocumento"));
        titularLicenciatariosNuevos.setLugarExpedicion(rs.getString("lugarexpedicion"));
        titularLicenciatariosNuevos.setPais(rs.getString("pais"));
        titularLicenciatariosNuevos.setGenero(rs.getString("genero"));
        titularLicenciatariosNuevos.setSolicitudDepartamento(rs.getString("solicituddepartamento"));
        titularLicenciatariosNuevos.setDireccion(rs.getString("direccion"));
        titularLicenciatariosNuevos.setTelefono(rs.getString("telefono"));
        titularLicenciatariosNuevos.setCelular(rs.getString("celular"));
        titularLicenciatariosNuevos.setEmail(rs.getString("email"));
        titularLicenciatariosNuevos.setEstado(rs.getString("estado"));
        titularLicenciatariosNuevos.setDependenciaMod(rs.getInt("dependenciamod"));
        titularLicenciatariosNuevos.setModificacionId(rs.getInt("modificacion_id"));
        

        return titularLicenciatariosNuevos;

    }

}
