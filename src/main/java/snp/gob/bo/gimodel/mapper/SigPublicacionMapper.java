/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import snp.gob.bo.gimodel.entidad.SigPublicacion;

/**
 *
 * @author susana
 */
public class SigPublicacionMapper implements RowMapper<SigPublicacion> {

    @Override
    public SigPublicacion mapRow(ResultSet rs, int i) throws SQLException {
        SigPublicacion sigPublicacion = new SigPublicacion();
        sigPublicacion.setIdpublicacion(rs.getLong("idpublicacion"));
        sigPublicacion.setIdlogtrans(rs.getLong("idlogtrans"));
        sigPublicacion.setNumero_gaceta(rs.getLong("numero_gaceta"));
        sigPublicacion.setFecha_publicacion(rs.getTimestamp("fecha_publicacion"));
        sigPublicacion.setFecha_envio(rs.getTimestamp("fecha_envio"));
        sigPublicacion.setMes(rs.getString("mes"));
        sigPublicacion.setGestion(rs.getInt("gestion"));
        sigPublicacion.setInicio(rs.getLong("inicio"));
        sigPublicacion.setFin(rs.getLong("fin"));
        sigPublicacion.setTotal(rs.getLong("total"));
        sigPublicacion.setObservacion(rs.getString("observacion"));
        sigPublicacion.setEstado_publicacion(rs.getString("estado_publicacion"));
        sigPublicacion.setEstado(rs.getString("estado"));
        return sigPublicacion;
    }

}
