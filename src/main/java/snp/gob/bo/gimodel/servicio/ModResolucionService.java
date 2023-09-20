/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.servicio;

import java.util.Date;
import javax.sql.DataSource;
import snp.gob.bo.gimodel.entidad.ModModificacion;
import snp.gob.bo.gimodel.entidad.ModResolucion;

/**
 *
 * @author susana
 */
public interface ModResolucionService {

    /**
     * Método que permite setear la conexion con dataSource
     *
     * Creado: Susana Escobar Paz Fecha: 18/10/2016
     *
     * @param dataSource
     */
    public void setDataSource(DataSource dataSource);

    /**
     * Metodo para guardar, modificar, listar registro en tabla modresolucion
     *
     * Creado: Susana Escobar Fecha: 26/09/2016
     *
     * @param modResolucion
     * @param operacion
     * @return
     */
    public ModResolucion guardar_modificar_listar_ModResolucion(ModResolucion modResolucion, Integer operacion);

    /**
     * Metodo para borrar (fisico) registro en tabla modresolucion
     *
     * Creado: Susana Escobar Fecha: 26/09/2016
     *
     * @param modResolucion
     * @param operacion
     */
    public void borrar_modResolucion(ModResolucion modResolucion, Integer operacion);

    /**
     * Metodo para listar modresolucion activo por idmodificacion
     *
     * Creado: Susana Escobar Fecha: 27/09/2016
     *
     * @param idmodificacion
     * @return
     */
    public ModResolucion buscar_ModResolucionXidmodificacion(Long idmodificacion);

    /**
     * Metodo para crear registro en modresolucion, asigancion de numero de
     * resolucion, gestion y fecha
     *
     * Creado: Susana Escobar Fecha: 28/09/2016
     *
     * @param modificacion
     * @return
     */
    public ModResolucion generarResolucion(ModModificacion modificacion);

    /**
     * Metodo que verifica la existencia de una resolucion con el numero,
     * gestiom, tipo de modificacion especificados y estado activo devuelve
     * valor verdadero si se encuentra y falso por el contrario
     *
     * Creado: Susana Escobar Paz Fecha: 29/11/2016
     *
     * @param tipo
     * @param numero
     * @param gestion
     * @param idmodificacion
     * @return
     */
    public Boolean verificaExisteModResolucion(String tipo, Integer numero, Integer gestion,Long idmodificacion);

    /**
     * Metodo que lista modresolucion anulado anteriormente, la cual podra ser
     * reutilizada en el proceso de reasignacion manual de resolucion
     *
     * Creado: Susana Escobar Paz Fecha: 29/11/2016
     *
     * @param tipo
     * @param numero
     * @param gestion
     * @param idmodificacion
     * @return
     */
    public ModResolucion lista_ModResolucionAnulado(String tipo, Integer numero, Integer gestion,Long idmodificacion);

    /**
     * Metodo para asignacion manual de resolucion, consiste en dejar no activo
     * NA la resolucion actual si existe, asignar la resolucion anulada segun el
     * idmodificacion con estado pendiente PE, la resolucion anulada pasa a un
     * estado de RE reutilizado.
     *
     * Creado: Susana Escobar Paz Fecha: 29/11/2016
     *
     * @param idmodificacion
     * @param actual
     * @param anulado
     * @param fecha
     * @return
     */
    public ModResolucion asignacionManualModResolucion(Long idmodificacion, ModResolucion actual, ModResolucion anulado, Date fecha);

    /**
     * Metodo que lista una resolucion pendiente, tras una asignacion manual,
     * esta sera tomada en cuenta a momento de registrar una solicitud de
     * modificacion por tener el estado PE, para pasado el registro tomar el
     * estado AC finalmente
     *
     * Creado: Susana Escobar Paz Fecha: 29/11/2016
     *
     * @param idmodificacion
     * @return
     */
    public ModResolucion buscar_ModResolucionXidmodificacionPendiente(Long idmodificacion);

    /**
     * Metodo adiciona un registro en modresolucion  con los parametros enviados, se utiliza para adicionar tramites antiguos en estado aceptada.
     *
      * Creado: Susana Escobar Paz Fecha: 29/11/2016
     * 
     * @param idmodificacion
     * @param numero
     * @param gestion
     * @param fecha
     */
    public void adicionarModResolucion(Long idmodificacion,Integer numero, Integer gestion, Date fecha);
    
    /**
     * Metodo guarda historial de resolucion anulada
     *
      * Creado: Susana Escobar Paz Fecha: 14/04/2017
     * 
     * @param modresolucion
     * @param idusuario
     * @param modificacion
     */
    public void historial_ModResolucion_anulado(ModResolucion modresolucion, Long idusuario, ModModificacion modificacion);
}
