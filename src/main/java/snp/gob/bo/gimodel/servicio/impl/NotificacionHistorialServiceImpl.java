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
import snp.gob.bo.gimodel.entidad.NotificacionHistorial;
import snp.gob.bo.gimodel.mapper.NotificacionHistorialMapper;
import snp.gob.bo.gimodel.servicio.NotificacionHistorialService;

/**
 *
 * @author levi
 */
@Service("notificacionHistorialService")
public class NotificacionHistorialServiceImpl implements NotificacionHistorialService{
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
    public List<NotificacionHistorial> NotificacionXTipoExpeGest(String tipo, String numero, Integer gestion){
        String SQL="";
        if(gestion == null || gestion.equals(""))
        {
          SQL = "select  n[1] as historial,n[2] as fecha,tipo_tramite_notificacion,expediente,gestion,extension " +
                       " from ( " +
                       "         select  " +
                       "                regexp_split_to_array(unnest(string_to_array(historial, ';')), ',') , " +
                       "                tipo_tramite_notificacion, " +
                       "                expediente, " +
                       "                gestion, " +
                       "                extension " +
                       "                from notificacion " +
                       "                where tipo_tramite_notificacion='"+tipo+"' " +
                       "                and expediente = '" +numero+ "' " +
                       "                and gestion is null " +                       "                order by idnotificacion asc " +
                       "                 ) as dt(n) order by fecha desc";
          
        }
        else{
         SQL = "select  n[1] as historial,n[2] as fecha,tipo_tramite_notificacion,expediente,gestion,extension " +
                       " from ( " +
                       "         select  " +
                       "                regexp_split_to_array(unnest(string_to_array(historial, ';')), ',') , " +
                       "                tipo_tramite_notificacion, " +
                       "                expediente, " +
                       "                gestion, " +
                       "                extension " +
                       "                from notificacion " +
                       "                where tipo_tramite_notificacion='"+tipo+"' " +
                       "                and expediente = '" +numero+ "' " +
                       "                and gestion = " +gestion+  "                order by idnotificacion asc " +
                       "                 ) as dt(n) order by fecha desc";
                  
        }
          
        List<NotificacionHistorial> listaNotiHis = jdbcTemplate.query(SQL, new NotificacionHistorialMapper());
        if (!listaNotiHis.isEmpty()) {
            for(int i=0;i<=listaNotiHis.size()-1;i++)
            { System.out.println("En consulta historial"+listaNotiHis.get(i).getHistorial());
            
            }
            return listaNotiHis;
        }
        return Collections.EMPTY_LIST;
    
    
    
    
    }
           
}
