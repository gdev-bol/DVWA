/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import org.springframework.jdbc.core.RowMapper;
import snp.gob.bo.gimodel.entidad.Poder;

/**
 *
 * @author levi
 */
public class PoderMapper implements RowMapper<Poder>{
    @Override
    public Poder mapRow(ResultSet rs, int i) throws SQLException {
          Poder poder = new Poder();
          poder.setIdpoder(rs.getLong("idpoder"));
          poder.setIdlogtrans(rs.getLong("idlogtrans"));
          poder.setTipo_poder(rs.getString("tipo_poder"));
          poder.setTipo_tramite(rs.getString("tipo_tramite"));
          poder.setNro_exped(rs.getLong("nro_exped"));
          poder.setGestion(rs.getInt("gestion"));
          poder.setNro_testimonio(rs.getString("nro_testimonio"));
          poder.setFecha_testimonio(rs.getTimestamp("fecha_testimonio"));
          poder.setNotario(rs.getString("notario"));
          poder.setNom_confiere_poder(rs.getString("nom_confiere_poder"));
          poder.setDom_confiere_poder(rs.getString("dom_confiere_poder"));
          poder.setApoderado(rs.getString("apoderado"));
          poder.setPoder_revocado(rs.getString("poder_revocado"));
          poder.setObs(rs.getString("obs"));
          poder.setEstado(rs.getString("estado"));
          
          
          /*notificacion.setIdnotificacion(rs.getLong("idnotificacion"));

          notificacion.setIdlogtrans(rs.getLong("idlogtrans"));
          notificacion.setBloque(rs.getInt("bloque"));
          notificacion.setNro_exped(rs.getInt("nro_exped"));
          notificacion.setId_usuario_solicitante(rs.getLong("id_usuario_solicitante"));
      */

          return poder;

    }
  
}
