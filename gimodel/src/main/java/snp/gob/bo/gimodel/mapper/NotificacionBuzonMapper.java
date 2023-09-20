/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.mapper;

import org.springframework.jdbc.core.RowMapper;
import snp.gob.bo.gimodel.entidad.NotificacionBuzon;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author levi
 */
public class NotificacionBuzonMapper implements RowMapper<NotificacionBuzon>{
    @Override
    public NotificacionBuzon mapRow(ResultSet rs, int i) throws SQLException {
          NotificacionBuzon notificacionBuz = new NotificacionBuzon();

          notificacionBuz.setConteo(rs.getLong("conteo"));
          notificacionBuz.setIdnotificacion(rs.getLong("idnotificacion"));
          notificacionBuz.setNombre(rs.getString("nombre"));
          notificacionBuz.setId_usuario_solicitante(rs.getLong("id_usuario_solicitante"));
          notificacionBuz.setBloque(rs.getInt("bloque"));
          notificacionBuz.setNro_exped(rs.getInt("nro_exped"));
          notificacionBuz.setTipo_tramite_notificacion(rs.getString("tipo_tramite_notificacion"));
          notificacionBuz.setExpediente(rs.getString("expediente"));
          notificacionBuz.setGestion(rs.getInt("gestion"));
          notificacionBuz.setExtension(rs.getString("extension"));
          
          return notificacionBuz;
    }
}
