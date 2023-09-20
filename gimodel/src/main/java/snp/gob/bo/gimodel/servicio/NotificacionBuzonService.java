/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.servicio;

import java.util.List;
import javax.sql.DataSource;
import snp.gob.bo.gimodel.entidad.NotificacionBuzon;

/**
 *
 * @author levi
 */
public interface NotificacionBuzonService {
     public void setDataSource(DataSource dataSource) throws Exception;
     public List<NotificacionBuzon>  listaNotificacionBuzon()  throws Exception;
     
}
