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
import snp.gob.bo.gimodel.entidad.Notificacion;

/**
 *
 * @author levi
 */
public class NotificacionMapper  implements RowMapper<Notificacion>{
      @Override
    public Notificacion mapRow(ResultSet rs, int i) throws SQLException {
          Notificacion notificacion = new Notificacion();

          notificacion.setIdnotificacion(rs.getLong("idnotificacion"));

          notificacion.setIdlogtrans(rs.getLong("idlogtrans"));
          notificacion.setBloque(rs.getInt("bloque"));
          notificacion.setNro_exped(rs.getInt("nro_exped"));
          notificacion.setId_usuario_solicitante(rs.getLong("id_usuario_solicitante"));
          notificacion.setTipo_tramite_notificacion(rs.getString("tipo_tramite_notificacion"));
          notificacion.setExpediente(rs.getString("expediente"));
          notificacion.setGestion(rs.getInt("gestion"));
          notificacion.setExtension(rs.getString("extension"));
          notificacion.setDemandante(rs.getString("demandante"));
          notificacion.setDemandante_cod_estado(rs.getString("demandante_cod_estado"));
          notificacion.setDemandante_fecha_devol(rs.getTimestamp("demandante_fecha_devol"));
          notificacion.setDemandante_fecha_noti(rs.getTimestamp("demandante_fecha_noti"));
          notificacion.setDemandante_lugar_notificacion(rs.getString("demandante_lugar_notificacion"));
          notificacion.setDemandante_solic(rs.getString("demandante_solic"));
          notificacion.setDemandante_apod(rs.getString("demandante_apod"));
          notificacion.setDemandante_fojas(rs.getString("demandante_fojas"));
          notificacion.setDemandante_con(rs.getString("demandante_con"));
          notificacion.setDemandante_direc(rs.getString("demandante_direc"));
          notificacion.setDemandante_cel(rs.getString("demandante_cel"));
          notificacion.setDemandante_ciudad_notif(rs.getString("demandante_ciudad_notifi"));
          notificacion.setDemandado(rs.getString("demandado"));
          notificacion.setDemandado_cod_estado(rs.getString("demandado_cod_estado"));
          notificacion.setDemandado_fecha_devol(rs.getTimestamp("demandado_fecha_devol"));
          notificacion.setDemandado_fecha_noti(rs.getTimestamp("demandado_fecha_noti"));
          notificacion.setDemandado_lugar_notificacion(rs.getString("demandado_lugar_notificacion"));
          notificacion.setDemandado_solic(rs.getString("demandado_solic"));
          notificacion.setDemandado_apod(rs.getString("demandado_apod"));
          notificacion.setDemandado_fojas(rs.getString("demandado_fojas"));
          notificacion.setDemandado_con(rs.getString("demandado_con"));
          notificacion.setDemandado_direc(rs.getString("demandado_direc"));
          notificacion.setDemandado_cel(rs.getString("demandado_cel"));
          notificacion.setDemandado_ciudad_notif(rs.getString("demandado_ciudad_notifi"));
          notificacion.setFecha_ingreso(rs.getTimestamp("fecha_ingreso"));
          notificacion.setTipo(rs.getInt("tipo"));
          notificacion.setObs(rs.getString("obs"));
          notificacion.setHistorial(rs.getString("historial"));
          notificacion.setFecha_recep(rs.getTimestamp("fecha_recep"));
          notificacion.setObs_notifi(rs.getString("obs_notifi"));
          notificacion.setId_usuario_notificador(rs.getLong("id_usuario_notificador"));
        //  notificacion.setCiudad_notificacion(rs.getString("ciudad_notificacion"));
          notificacion.setEstado_marca(rs.getString("estado_marca"));
          notificacion.setForm_noti_pre(rs.getString("form_noti_pre"));
          notificacion.setForm_noti_cuerpo(rs.getString("form_noti_cuerpo"));

          return notificacion;

    }
  
}
