/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.servicio;

import java.util.List;
import javax.sql.DataSource;
import snp.gob.bo.gimodel.entidad.NotificacionBusqueda;

/**
 *
 * @author levi
 */
public interface NotificacionBusquedaService {
    public void setDataSource(DataSource dataSource) throws Exception;
    public List<NotificacionBusqueda> NotificacionBusquedaXTipoExpeGest(String tipo, String numero, Integer gestion) throws Exception;
    public List<NotificacionBusqueda> NotificacionBusquedaXDemaDado(String demandantedo) throws Exception;
    public List<NotificacionBusqueda> NotificacionBusquedaXOpera(String operador) throws Exception;
    
}
