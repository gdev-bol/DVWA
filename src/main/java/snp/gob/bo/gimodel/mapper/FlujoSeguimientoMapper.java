/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import snp.gob.bo.gimodel.entidad.FlujoSeguimiento;

/**
 *
 * @author levi
 */
public class FlujoSeguimientoMapper implements RowMapper<FlujoSeguimiento> {
     @Override
    public FlujoSeguimiento mapRow(ResultSet rs, int i) throws SQLException {
          FlujoSeguimiento flujoSeguimiento = new FlujoSeguimiento();

          flujoSeguimiento.setIdflujoseguimiento(rs.getLong("idflujoseguimiento"));
          flujoSeguimiento.setIdflujo(rs.getInt("idflujo"));
          flujoSeguimiento.setIdetapa(rs.getLong("idetapa"));
          flujoSeguimiento.setOrden(rs.getInt("orden"));
          flujoSeguimiento.setEtapa(rs.getString("etapa"));
          flujoSeguimiento.setTipo_etapa(rs.getString("tipo_etapa"));
          flujoSeguimiento.setEstado(rs.getString("estado"));
          
          return flujoSeguimiento;
          
      }
}
