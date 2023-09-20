/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import snp.gob.bo.gimodel.entidad.Regional;

/**
 * Mapeador responsable de sincronizar la Tabla regional con la entidad Regional
 *
 * @author Eddy Valero
 * @see RegionalMapper
 * @version 1.0, 25/07/2016
 */
public class RegionalMapper implements RowMapper<Regional> {

    /**
     * Método que permite mapear la tabla regional con la entidad Regional
     *
     * @param rs
     * @param i
     * @return Regional
     * @throws java.sql.SQLException
     */
    @Override
    public Regional mapRow(ResultSet rs, int i) throws SQLException {
        try {
            Regional regional = new Regional();

            regional.setIdRegional(rs.getLong("idregional"));
            regional.setIdLogTrans(rs.getLong("idlogtrans"));
            regional.setCentral(rs.getBoolean("central"));
            regional.setNombre(rs.getString("nombre"));
            regional.setDirección(rs.getString("direccion"));
            regional.setTelefono(rs.getString("telefono"));
            regional.setFax(rs.getString("fax"));
            regional.setTipoCiudadNotificacion(rs.getString("tipo_ciudad_notificacion"));
            regional.setEstado(rs.getString("estado"));

            return regional;

        } catch (Exception e) {
            throw e;
        }

    }

}
