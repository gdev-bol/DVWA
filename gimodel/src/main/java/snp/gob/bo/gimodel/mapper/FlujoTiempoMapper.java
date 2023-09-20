/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import snp.gob.bo.gimodel.entidad.FlujoTiempo;

/**
 *
 * @author levi
 */
public class FlujoTiempoMapper implements RowMapper<FlujoTiempo>{
     @Override
    public FlujoTiempo mapRow(ResultSet rs, int i) throws SQLException {
          FlujoTiempo flujoTiempo = new FlujoTiempo();

          flujoTiempo.setIdflujotiempo(rs.getLong("idflujotiempo"));
          flujoTiempo.setIdetapa(rs.getLong("idetapa"));
          flujoTiempo.setTiempo(rs.getInt("tiempo"));
          flujoTiempo.setOrden(rs.getInt("orden"));
          flujoTiempo.setEstado(rs.getString("estado"));
        return flujoTiempo;
    }
}
