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

/**
 *
 * @author susana
 */
public class ModTipoSignosMapper implements RowMapper<ModTipoSignos> {

    @Override
    public ModTipoSignos mapRow(ResultSet rs, int i) throws SQLException {
        ModTipoSignos modTipoSignos = new ModTipoSignos();
        modTipoSignos.setModificacion_id(rs.getLong("modificacion_id"));
        modTipoSignos.setTiposigno(rs.getString("tiposigno"));
        modTipoSignos.setDescripcionotro(rs.getString("descripcionotro"));
        modTipoSignos.setEstado(rs.getString("estado"));
        modTipoSignos.setFechacreacion(rs.getTimestamp("fechacreacion"));
        modTipoSignos.setFechamod(rs.getTimestamp("fechamod"));
        return modTipoSignos;
    }

}
