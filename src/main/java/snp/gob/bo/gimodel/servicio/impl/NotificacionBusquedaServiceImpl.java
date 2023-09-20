/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.servicio.impl;

import java.util.Collections;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import snp.gob.bo.gimodel.entidad.NotificacionBusqueda;
import snp.gob.bo.gimodel.mapper.NotificacionBusquedaMapper;
import snp.gob.bo.gimodel.servicio.NotificacionBusquedaService;

/**
 *
 * @author levi
 */
@Service("notificacionBusquedaService")
public class NotificacionBusquedaServiceImpl implements NotificacionBusquedaService{
     private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    @Override
    @Autowired
    public void setDataSource(DataSource dataSource) {
         try {
            this.dataSource = dataSource;
            this.jdbcTemplate = new JdbcTemplate(dataSource);
        } catch (Exception e) {
            throw e;
        }
    }
    @Override
    public List<NotificacionBusqueda> NotificacionBusquedaXTipoExpeGest(String tipo, String numero, Integer gestion){
        
        String SQL="";
        if(gestion == null || gestion.equals("") || gestion == 0)
        {
            SQL = "select idnotificacion,n.nro_exped,n.bloque,n.tipo_tramite_notificacion,n.expediente,n.gestion,n.extension "
                    + "       , n.demandante,n.demandado,n.demandante_con, n.fecha_ingreso, n.obs "
                    + "       , d.estado "
                    + "       ,u.nombre_solicitante "
                    + "       ,u1.nombre_notificador "
                    + "       ,n.fecha_recep,demandante_fecha_noti "
                    + "       , n.demandante_fecha_devol "
                    + "       , n.demandado_fecha_noti "
                    + "       , n.demandado_fecha_devol "
                    + "       , n.obs_notifi "
                    + "from"
                    + "  (select codigo,nombre as estado from dominio where dominio ='estado_notificacion' "
                    + "  ) d "
                    + "  inner  join "
                    + "  (select idnotificacion,nro_exped,bloque,tipo_tramite_notificacion,expediente,gestion,extension "
                    + "       , demandante,demandado,demandante_con, fecha_ingreso, obs ,demandante_cod_estado "
                    + "       , id_usuario_solicitante, id_usuario_notificador,fecha_recep,demandante_fecha_noti "
                    + "       , demandante_fecha_devol "
                    + "       , demandado_fecha_noti "
                    + "       , demandado_fecha_devol "
                    + "       , obs_notifi "
                    + "     from notificacion "
                    + "     where tipo_tramite_notificacion='"+tipo+"' "
                    + "     and expediente='"+numero+"' "
                    + "     and (gestion is null or gestion = 0) "
                    + "   ) n "
                    + "    on(d.codigo=n.demandante_cod_estado) "
                    + "  left outer join "
                    + "  ( "
                    + "      select nombre||' '||primer_apellido||' '||"
                    + "      CASE WHEN segundo_apellido IS NULL THEN '' " 
                    + "      WHEN segundo_apellido IS NOT NULL THEN segundo_apellido " 
                    + "      END "
                    
                    
                    + "      as nombre_solicitante, idusuario from usuario "
                    + "  ) u "
                    + "  on (u.idusuario=n.id_usuario_solicitante) "
                    + "  left outer join "
                    + "  ( "
                    + "     select nombre||' '||primer_apellido||' '||"
                    + "      CASE WHEN segundo_apellido IS NULL THEN '' " 
                    + "      WHEN segundo_apellido IS NOT NULL THEN segundo_apellido " 
                    + "      END "
                    + ""
                    + "      as nombre_notificador, idusuario from usuario "
                    + "  ) u1 "
                    + "  on (u1.idusuario=n.id_usuario_notificador) "
                    + "  order by idnotificacion asc";

        }
        else{
            SQL="select idnotificacion,n.nro_exped,n.bloque,n.tipo_tramite_notificacion,n.expediente,n.gestion,n.extension "
                    + "       , n.demandante,n.demandado,n.demandante_con, n.fecha_ingreso, n.obs "
                    + "       , d.estado "
                    + "       ,u.nombre_solicitante "
                    + "       ,u1.nombre_notificador "
                    + "       ,n.fecha_recep,demandante_fecha_noti "
                    + "       , n.demandante_fecha_devol "
                    + "       , n.demandado_fecha_noti "
                    + "       , n.demandado_fecha_devol "
                    + "       , n.obs_notifi "
                    + "from"
                    + "  (select codigo,nombre as estado from dominio where dominio ='estado_notificacion' "
                    + "  ) d "
                    + "  inner  join "
                    + "  (select idnotificacion,nro_exped,bloque,tipo_tramite_notificacion,expediente,gestion,extension "
                    + "       , demandante,demandado,demandante_con, fecha_ingreso, obs ,demandante_cod_estado "
                    + "       , id_usuario_solicitante, id_usuario_notificador,fecha_recep,demandante_fecha_noti "
                    + "       , demandante_fecha_devol "
                    + "       , demandado_fecha_noti "
                    + "       , demandado_fecha_devol "
                    + "       , obs_notifi "
                    + "     from notificacion "
                    + "     where tipo_tramite_notificacion='"+tipo+"' "
                    + "     and expediente='"+numero+"' "
                    + "     and gestion ="+gestion+" "
                    + "   ) n "
                    + "    on(d.codigo=n.demandante_cod_estado) "
                    + "  left outer join "
                    + "  ( "
                    + "     select nombre||' '||primer_apellido||' '||"
                    + ""
                    + "      CASE WHEN segundo_apellido IS NULL THEN '' " 
                    + "      WHEN segundo_apellido IS NOT NULL THEN segundo_apellido " 
                    + "      END "
                    + "      as nombre_solicitante, idusuario from usuario "
                    + "  ) u "
                    + "  on (u.idusuario=n.id_usuario_solicitante) "
                    + "  left outer join "
                    + "  ( "
                    + "     select nombre||' '||primer_apellido||' '||"
                    + ""
                    + "      CASE WHEN segundo_apellido IS NULL THEN '' " 
                    + "      WHEN segundo_apellido IS NOT NULL THEN segundo_apellido " 
                    + "      END "
                    
                    + "       as nombre_notificador, idusuario from usuario "
                    + "  ) u1 "
                    + "  on (u1.idusuario=n.id_usuario_notificador) "
                    + "  order by idnotificacion asc"
                ;
            
        }
        List<NotificacionBusqueda> listaNotiBus = jdbcTemplate.query(SQL, new NotificacionBusquedaMapper());
        if (!listaNotiBus.isEmpty()) {
           /* for(int i=0;i<=listaNotiBus.size()-1;i++)
            { System.out.println("En consulta historial"+listaNotiBus.get(i).getDemandante());
            
            }*/
            return listaNotiBus;
        }
        return Collections.EMPTY_LIST;
    }
     @Override
    public List<NotificacionBusqueda> NotificacionBusquedaXDemaDado(String demandantedo) {
        String SQL = "";

        SQL = "select idnotificacion,n.nro_exped,n.bloque,n.tipo_tramite_notificacion,n.expediente,n.gestion,n.extension "
                + "           , n.demandante,n.demandado,n.demandante_con, n.fecha_ingreso, n.obs "
                + "           , d.estado "
                + "            ,u.nombre_solicitante "
                + "            ,u1.nombre_notificador "
                + "            ,n.fecha_recep,demandante_fecha_noti "
                + "            , n.demandante_fecha_devol "
                + "            , n.demandado_fecha_noti "
                + "            , n.demandado_fecha_devol "
                + "                    , n.obs_notifi "
                + "                    from "
                + "                     (select codigo,nombre as estado from dominio where dominio ='estado_notificacion' "
                + "                     ) d "
                + "                     inner  join "
                + "                    (select idnotificacion,nro_exped,bloque,tipo_tramite_notificacion,expediente,gestion,extension "
                + "                           , demandante,demandado,demandante_con, fecha_ingreso, obs ,demandante_cod_estado "
                + "                           , id_usuario_solicitante, id_usuario_notificador,fecha_recep,demandante_fecha_noti "
                + "                           , demandante_fecha_devol "
                + "                           , demandado_fecha_noti "
                + "                           , demandado_fecha_devol "
                + "                           , obs_notifi "
                + "                           from notificacion "
                + "                           where demandante='"+demandantedo+"' OR demandado='"+demandantedo+"' "
                + "                           "
                + "                    ) n "
                + "                        on(d.codigo=n.demandante_cod_estado) "
                + "                        left outer join "
                + "                        ( "
                + "                          select nombre||' '||primer_apellido||' '||"
                + "                          CASE WHEN segundo_apellido IS NULL THEN '' " 
                + "                           WHEN segundo_apellido IS NOT NULL THEN segundo_apellido " 
                + "                          END "
                + "                          as nombre_solicitante, idusuario from usuario "
                + "                        ) u "
                + "                        on (u.idusuario=n.id_usuario_solicitante) "
                + "                       left outer join "
                + "                       ( "
                + "                          select nombre||' '||primer_apellido||' '||"
                + "                          CASE WHEN segundo_apellido IS NULL THEN '' " 
                + "                          WHEN segundo_apellido IS NOT NULL THEN segundo_apellido " 
                + "                          END "
                + "                        as nombre_notificador, idusuario from usuario "
                + "                        ) u1 "
                + "                       on (u1.idusuario=n.id_usuario_notificador) "
                + "                      order by idnotificacion asc";
            List<NotificacionBusqueda> listaNotiBus = jdbcTemplate.query(SQL, new NotificacionBusquedaMapper());
            if (!listaNotiBus.isEmpty()) {
               /* for(int i=0;i<=listaNotiBus.size()-1;i++)
                { System.out.println("En consulta historial"+listaNotiBus.get(i).getDemandante());
            
                 }*/
            return listaNotiBus;
        }
        return Collections.EMPTY_LIST;
    }
    @Override
    public List<NotificacionBusqueda> NotificacionBusquedaXOpera(String operador) {
        String SQL = "";
          
        SQL = "select idnotificacion,n.nro_exped,n.bloque,n.tipo_tramite_notificacion,n.expediente,n.gestion,n.extension "
                + "           , n.demandante,n.demandado,n.demandante_con, n.fecha_ingreso, n.obs "
                + "           , d.estado "
                + "           ,u.nombre_solicitante "
                + "           ,u1.nombre_notificador "
                + "           ,n.fecha_recep,demandante_fecha_noti "
                + "           , n.demandante_fecha_devol "
                 + "          , n.demandado_fecha_noti "
                + "           , n.demandado_fecha_devol "
                + "                    , n.obs_notifi "
                + "                    from "
                + "                     (select codigo,nombre as estado from dominio where dominio ='estado_notificacion' "
                + "                     ) d "
                + "                     inner  join "
                + "                    (select idnotificacion,nro_exped,bloque,tipo_tramite_notificacion,expediente,gestion,extension "
                + "                           , demandante,demandado,demandante_con, fecha_ingreso, obs ,demandante_cod_estado "
                + "                           , id_usuario_solicitante, id_usuario_notificador,fecha_recep,demandante_fecha_noti "
                + "                           , demandante_fecha_devol "
                + "                           , demandado_fecha_noti "
                + "                           , demandado_fecha_devol "
                + "                           , obs_notifi "
                + "                           from notificacion "
                + "                           "
                + "                    ) n "
                + "                        on(d.codigo=n.demandante_cod_estado) "
                + "                        left outer join "
                + "                        ( "
                + "                          select nombre||' '||primer_apellido||' '||"
                + "                          CASE WHEN segundo_apellido IS NULL THEN '' " 
                + "                          WHEN segundo_apellido IS NOT NULL THEN segundo_apellido " 
                + "                          END "
                + "                          as nombre_solicitante, idusuario from usuario "
                  
                
                + "                        ) u "
                + "                        on (u.idusuario=n.id_usuario_solicitante) "
                + "                       left outer join "
                + "                       ( "
                + "                          select nombre||' '||primer_apellido||' '||"
                + "                          CASE WHEN segundo_apellido IS NULL THEN '' " 
                + "                          WHEN segundo_apellido IS NOT NULL THEN segundo_apellido " 
                + "                          END "
                + "                        as nombre_notificador, idusuario from usuario "
                + "                        ) u1 "
                + "                       on (u1.idusuario=n.id_usuario_notificador) "
                + "                where u.nombre_solicitante like '%"+operador+"%' or u1.nombre_notificador like '%"+operador+"%' "
                + "                      order by idnotificacion asc";
            List<NotificacionBusqueda> listaNotiBus = jdbcTemplate.query(SQL, new NotificacionBusquedaMapper());
           if (!listaNotiBus.isEmpty()) {
             //   for(int i=0;i<=listaNotiBus.size()-1;i++)
              //  { System.out.println("En consulta historial"+listaNotiBus.get(i).getDemandante());
            
           //      }
            return listaNotiBus;
        }
        return Collections.EMPTY_LIST;
    
    
    }
}
