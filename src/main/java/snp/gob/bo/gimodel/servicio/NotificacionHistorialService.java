/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.servicio;

import java.util.List;
import javax.sql.DataSource;
import snp.gob.bo.gimodel.entidad.NotificacionHistorial;

/**
 *
 * @author levi
 */
public interface NotificacionHistorialService {
      public void setDataSource(DataSource dataSource) throws Exception;
       /**
        * Saca un listado de notificaciones por tipo ej SM, numero, y gestión,pero su particularidad es que el historial lo subdivide por ";" en  registros
        * varios cuantos  sea necesario, esta función us el reorte de "Historial", para notificaciones
        * Creado: Levi Laura Fecha: 14/10/2016
        * @param tipo, numero, gestion
        * @return List Notificacion
       */
      public List<NotificacionHistorial> NotificacionXTipoExpeGest(String tipo, String numero, Integer gestion) throws Exception;
      
}
