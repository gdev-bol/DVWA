/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.mapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import snp.gob.bo.gimodel.entidad.NotificacionBusqueda;
/**
 *
 * @author levi
 */
public class NotificacionBusquedaMapper implements RowMapper<NotificacionBusqueda>{
    @Override
    public NotificacionBusqueda mapRow(ResultSet rs, int i) throws SQLException {
          NotificacionBusqueda notificacionBus = new NotificacionBusqueda();

          notificacionBus.setIdnotificacion(rs.getLong("idnotificacion"));
          notificacionBus.setNro_exped(rs.getInt("nro_exped"));
          notificacionBus.setBloque(rs.getInt("bloque"));
          notificacionBus.setTipo_tramite_notificacion(rs.getString("tipo_tramite_notificacion"));
          notificacionBus.setExpediente(rs.getString("expediente"));
          notificacionBus.setGestion(rs.getInt("gestion"));
          notificacionBus.setExtension(rs.getString("extension"));
          notificacionBus.setDemandante(rs.getString("demandante"));
          notificacionBus.setDemandado(rs.getString("demandado"));
          notificacionBus.setDemandante_con(rs.getString("demandante_con"));
          notificacionBus.setFecha_ingreso(rs.getDate("fecha_ingreso"));
          notificacionBus.setObs(rs.getString("obs"));
          notificacionBus.setEstado(rs.getString("estado"));
          notificacionBus.setNombre_solicitante(rs.getString("nombre_solicitante"));
          notificacionBus.setNombre_notificador(rs.getString("nombre_notificador"));
          notificacionBus.setFecha_recep(rs.getDate("fecha_recep"));
          notificacionBus.setDemandante_fecha_noti(rs.getTimestamp("demandante_fecha_noti"));
          notificacionBus.setDemandante_fecha_devol(rs.getTimestamp("demandante_fecha_devol"));
          notificacionBus.setDemandado_fecha_noti(rs.getTimestamp("demandado_fecha_noti"));
          notificacionBus.setDemandado_fecha_devol(rs.getTimestamp("demandado_fecha_devol"));
          notificacionBus.setObs_notifi(rs.getString("obs_notifi"));
          
         
          return notificacionBus;
    }
}
