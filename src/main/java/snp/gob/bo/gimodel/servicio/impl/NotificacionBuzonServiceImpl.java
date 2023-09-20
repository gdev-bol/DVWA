/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.servicio.impl;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import snp.gob.bo.gimodel.entidad.NotificacionBuzon;
import snp.gob.bo.gimodel.mapper.NotificacionBuzonMapper;
import snp.gob.bo.gimodel.servicio.NotificacionBuzonService;
/**
 *
 * @author levi
 */
@Service("notificacionBuzonService")
public class NotificacionBuzonServiceImpl implements NotificacionBuzonService{
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
     public List<NotificacionBuzon>  listaNotificacionBuzon(){
         Calendar cal = Calendar.getInstance();
          cal.setTime(new Date());
         cal.add(Calendar.DATE, -30);
            Date dateBefore30Days = cal.getTime();
        //String SQL = "select  row_number() over(order by idnotificacion) as conteo, idnotificacion, id_usuario_solicitante, bloque, nro_exped, gestion, extension " +
         String SQL = "select 1 as conteo, n.idnotificacion, n.id_usuario_solicitante, u.nombre||' '||u.primer_apellido as nombre,n.bloque, n.nro_exped,n.tipo_tramite_notificacion,n.expediente, n.gestion, n.extension " +
"        from notificacion n, usuario u " +
"        where n.demandante_cod_estado='ENV' and n.fecha_ingreso >='"+dateBefore30Days+"'"+
"        and n.id_usuario_solicitante = u.idusuario" +
"         order by u.nombre, n.bloque, n.nro_exped";
         
        List<NotificacionBuzon> listaNotiBuz = jdbcTemplate.query(SQL, new NotificacionBuzonMapper());
        if (!listaNotiBuz.isEmpty()) {
        /*    for(int i=0;i<=listaNotiBuz.size()-1;i++)
            { System.out.println("En consulta estado"+listaNotiBuz.get(i).getConteo());
            
            }*/
            return listaNotiBuz;
        }
        return Collections.EMPTY_LIST;
     }
    
    
}
