/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.mapper;

import org.springframework.jdbc.core.RowMapper;
import snp.gob.bo.gimodel.entidad.NotificacionHistorial;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author levi
 */
public class NotificacionHistorialMapper implements RowMapper<NotificacionHistorial>{
    @Override
    public NotificacionHistorial mapRow(ResultSet rs, int i) throws SQLException {
          NotificacionHistorial notificacionHis = new NotificacionHistorial();
          notificacionHis.setHistorial(rs.getString("historial"));
          notificacionHis.setFecha(rs.getString("fecha"));
          notificacionHis.setTipo_tramite_notificacion(rs.getString("tipo_tramite_notificacion"));
          notificacionHis.setExpediente(rs.getString("expediente"));
          notificacionHis.setGestion(rs.getInt("gestion"));
          notificacionHis.setExtension(rs.getString("extension"));
          
         
          return notificacionHis;
    }
}