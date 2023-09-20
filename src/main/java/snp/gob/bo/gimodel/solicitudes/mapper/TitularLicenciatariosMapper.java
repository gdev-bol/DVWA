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

/**
 *
 * @author Eddy Valero
 * @version 1.0, 24/10/2016
 */
public class TitularLicenciatariosMapper implements RowMapper<TitularLicenciatarios> {

    @Override
    public TitularLicenciatarios mapRow(ResultSet rs, int i) throws SQLException {

        TitularLicenciatarios titularLicenciatarios = new TitularLicenciatarios();

        titularLicenciatarios.setId(rs.getLong("id"));
        titularLicenciatarios.setTipoTitular(rs.getString("tipotitular"));
        titularLicenciatarios.setTipoTitularRegistrado(rs.getString("tipotitularregistrado"));
        titularLicenciatarios.setTipoPersona(rs.getString("tipopersona"));
        titularLicenciatarios.setNombreRazonSocial(rs.getString("nombre_razonsocial"));
        titularLicenciatarios.setDireccion(rs.getString("direccion"));
        titularLicenciatarios.setEstado(rs.getString("estado"));
        titularLicenciatarios.setModificacionId(rs.getInt("modificacion_id"));

        return titularLicenciatarios;

    }

}
