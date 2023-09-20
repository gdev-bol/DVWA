/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.solicitudes.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import snp.gob.bo.gimodel.solicitudes.entidades.ModTipoSignos;
import snp.gob.bo.gimodel.solicitudes.entidades.RenTipoSignos;

/**
 *
 * @author susana
 */
public class RenTipoSignosMapper implements RowMapper<RenTipoSignos> {

    @Override
    public RenTipoSignos mapRow(ResultSet rs, int i) throws SQLException {
        RenTipoSignos renTipoSignos = new RenTipoSignos();

        renTipoSignos.setRenovacion_id(rs.getLong("renovacion_id"));
        renTipoSignos.setTiposigno(rs.getString("tiposigno"));
        renTipoSignos.setDescripcionotro(rs.getString("descripcionotro"));
        renTipoSignos.setEstado(rs.getString("estado"));
        renTipoSignos.setFechacreacion(rs.getTimestamp("fechacreacion"));
        renTipoSignos.setFechamod(rs.getTimestamp("fechamod"));
        return renTipoSignos;
    }

}
