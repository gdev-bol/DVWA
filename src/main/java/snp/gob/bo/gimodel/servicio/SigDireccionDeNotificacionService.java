/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.servicio;

import java.util.List;
import javax.sql.DataSource;
import snp.gob.bo.gimodel.entidad.SigDireccionDeNotificacion;
import snp.gob.bo.gimodel.solicitudes.entidades.DireccionNotificaciones;

/**
 *
 * @author Eddy Valero
 * @version 1.0, 29/08/2016
 */
public interface SigDireccionDeNotificacionService {

    public void setDataSource(DataSource dataSource);

    /**
     * Método que realiza el registro de una dirección de notificación
     * 
     * Creado: Eddy Valero Fecha: 29/08/2016
     * @param sigDireccionNotificacion
     * @return SigDireccionNotificacion
     * @throws java.lang.Exception
     */
    public SigDireccionDeNotificacion registrarSigDireccionNotificacion(SigDireccionDeNotificacion sigDireccionNotificacion) throws Exception;
    
    
    /**
     * Método que obtiene el registro de una dirección de notificación
     * 
     * Creado: Eddy Valero Fecha: 29/08/2016
     * @param idSignoMarca
     * @return SigDireccionNotificacion
     * @throws java.lang.Exception
     */
    public SigDireccionDeNotificacion obtenerDireccionNotificacionXSignoMarca(Long idSignoMarca) throws Exception;
    
    
    /**
     * Método que realiza el registro de una dirección de notificación
     * 
     * Creado: Eddy Valero Fecha: 03/10/2016
     * @param sigDireccionDeNotificacion
     * @param idSignoMarca
     * @param operacion
     * @return SigDireccionNotificacion
     * @throws java.lang.Exception
     */
    public SigDireccionDeNotificacion guardarModificarListarSigDireccionDeNotificacion(SigDireccionDeNotificacion sigDireccionDeNotificacion,
                                                                                       Long idSignoMarca, Integer operacion) throws Exception;
    /**
     * Método crud de la tabla sigdireccionnotificacion
     * 
     * Creado: Eddy Valero Fecha: 10/10/2016
     * @param sigDireccionDeNotificacion
     * @param parametro
     * @return SigDireccionNotificacion
     * @throws java.lang.Exception
     */
    public SigDireccionDeNotificacion crudSigDireccionDeNotificacion(SigDireccionDeNotificacion sigDireccionDeNotificacion, int parametro) throws Exception;
    
    /**
     * Método crud de la tabla sigdireccionnotificacion
     * 
     * Creado:Placido Castro Fecha: 24/11/2017
     * @param sigDireccionDeNotificacion
     * @param listaDireccionNotificacionSipi
     * @param parametro
     * @return SigDireccionNotificacion
     * @throws java.lang.Exception
     */
    public SigDireccionDeNotificacion crudSigDireccionDeNotificacionSubsanacion(SigDireccionDeNotificacion sigDireccionDeNotificacion,  List<DireccionNotificaciones> listaDireccionNotificacionSipi, int parametro) throws Exception;
}
