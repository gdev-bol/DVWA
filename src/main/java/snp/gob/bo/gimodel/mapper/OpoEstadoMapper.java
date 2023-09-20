/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import snp.gob.bo.gimodel.entidad.OpoEstado;

/**
 *
 * @author Luis Angel Quispe Limachi
 */
public class OpoEstadoMapper implements RowMapper<OpoEstado> {

    /**
     * Método que permite mapear la tabla opoestado
     *
     * @param rs
     * @param i
     * @return opoestado
     * @throws java.sql.SQLException
     */
    @Override
    public OpoEstado mapRow(ResultSet rs, int i) throws SQLException {
        try {
            OpoEstado opoestado = new OpoEstado();
            opoestado.setIdestado(rs.getLong("idestado"));
            opoestado.setIdarea(rs.getLong("idarea"));
            opoestado.setIdlogtrans(rs.getLong("idlogtrans"));
            opoestado.setDescri_idestadooposicion("descri_idestadooposicion");
            opoestado.setEstado(rs.getString("estado"));
            opoestado.setOrden(rs.getInt("orden"));
            opoestado.setEstado(rs.getString("estado"));
            return opoestado;
        } catch (SQLException e) {
            throw e;
        }

    }

}
