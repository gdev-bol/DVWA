/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.servicio;

import javax.sql.DataSource;
import snp.gob.bo.gimodel.entidad.ModDireccionDeNotificacion;
import snp.gob.bo.gimodel.solicitudes.entidades.DireccionNotificaciones;

/**
 *
 * @author susana
 */
public interface ModDireccionDeNotificacionService {
    /**
     * Método que permite setear la conexion con dataSource
     * 
     * Creado: Susana Escobar Paz Fecha: 08/09/2016
     *
     * @param dataSource
     */
    public void setDataSource(DataSource dataSource);
    
    /**
     * Metodo para guardar, modificar, listar registros en la tabla moddireccionnotificacion
     *
     * Creado: Susana Escobar Fecha: 08/09/2016
     *
     * @param moddirecciondenotificacion
     * @param idmodificacion
     * @param operacion
     * @param idLogTrans
     * @return
     */
    public ModDireccionDeNotificacion guardar_modificar_listar_ModDireccionDeNotificacion(ModDireccionDeNotificacion moddirecciondenotificacion, Long idmodificacion, Integer operacion, Long idLogTrans);
    
    /**
     * Metodo para guardar, modificar, listar registros en la tabla moddireccionnotificacion
     *
     * Creado: Placido Castro Fecha: 28/11/2017
     *
     * @param direccionNotificacionHidra
     * @param direccionNotificacionSipi
     * @param idmodificacion
     * @param idLogTrans
     * @return
     */
    public ModDireccionDeNotificacion modificarModDireccionDeNotificacionSubsanacion(ModDireccionDeNotificacion direccionNotificacionHidra,
            DireccionNotificaciones direccionNotificacionSipi, Long idmodificacion, Long idLogTrans);
        
    /**
     * Metodo para obtener moddirecccionnotificacion por idmodificacion
     *
     * Creado: Susana Escobar Fecha: 09/09/2016
     *
     * @param idmodificacion
     * @return
     */
    public ModDireccionDeNotificacion buscarModDireccionDeNotificacionXidmodificacion(Long idmodificacion);
    
     /**
     * Metodo para retornar texto concatenado con datos de direccion de notificacion
     *
     * Creado: Susana Escobar Fecha: 09/09/2016
     *
     * @param Ciudad
     * @param zonBarrio
     * @param avenidaCalle
     * @param numero
     * @param referencia
     * @param edificio
     * @param piso
     * @param departamento
     * @param correo
     * @return
     */
    public String datos_notificacion(String Ciudad, String zonBarrio, String avenidaCalle, String numero, 
            String referencia, String edificio, String piso, String departamento, String correo);
    
     /**
     *Metodo que retorna texto concatenado con datos de telefonos y celulares de una persona
     *
     * Creado: Susana Escobar Fecha: 09/09/2016
     *
     * @param celular
     * @param telefono
     * @return
     */
    public String datos_celular(String celular, String telefono);
}
