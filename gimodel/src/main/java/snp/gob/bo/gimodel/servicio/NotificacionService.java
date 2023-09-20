/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.servicio;

import java.util.Date;
import java.util.List;
import javax.sql.DataSource;
import snp.gob.bo.gimodel.entidad.Notificacion;
import snp.gob.bo.gimodel.entidad.NotificacionBuzon;

/**
 *
 * @author Levi Laura
 * @see NotificacionServiceImpl
 * @version 1.0, 08/09/2016
 */
public interface NotificacionService {
    
    /**
     * metodo para seterar la fuente de datos
     * 
     * Creado: Levi Laura Fecha: 15/11/2016
     * @param datasource
     */
     public void setDataSource(DataSource dataSource) throws Exception;
     /**
     * Método para guardar un objeto  asociado a la tabla Notificacion
     * 
     * Creado: Levi Laura Fecha: 27/09/2016
     * @param notificacion
     * @return Notificacion
     */
     public Notificacion guardaNotificacion(Notificacion notificacion)  throws Exception;
     /**
     * Método para guardar una lista de notificaciones
     * 
     * Creado: Levi Laura Fecha: 27/09/2016
     * @param listaNotificaciones
     * @throws java.lang.Exception
     */
     public void guardaListaNotificaciones(List<Notificacion> listaNotificaciones) throws Exception;
     /**
     * Método obtiene el nuevo bloque sumado mas uno al anterior segun el idusuario solicitante
     * 
     * Creado: Levi Laura Fecha: 27/09/2016
     * @param idusuariosolicitante
     * @return Integer
     * @throws java.lang.Exception
     */
     public Integer obtieneBloqueNuevo(Integer id_usuariosolicitante) throws Exception;
     /**
     * Método para obtener la notificacion segun el bloque y el idusuaroi que lo solicita la peticion
     * Creado: Levi Laura Fecha: 27/09/2016
     * @param bloque
     * @param id_usuario_solic
     * @param notificacion
     * @return Notificacion
     * @throws java.lang.Exception
     */
     public List<Notificacion> getNotificacionXbloqueXusuariosol(Integer bloque, Long id_usuario_solic) throws Exception;
     /**
     * Método para eliminar una notificacion
     * Creado: Levi Laura Fecha: 27/09/2016
     * @param idnotificacion
     * @return void
     * @throws java.lang.Exception
     */
     public void eliminaNotificacion(Long idNotificacion)  throws Exception;
     /**
     * Método que modifica una lote de notificaciones, elimina y adiciona.
     * Creado: Levi Laura Fecha: 27/09/2016
     * @param listaNotificaciones
     * @return void
     * @throws java.lang.Exception
     */
     public void modificaListaNotificaciones(List<Notificacion> listaNotificaciones) throws Exception;
     /**
     * Método que elimina un registro segun el bloque  y el idusuario
     * Creado: Levi Laura Fecha: 27/09/2016
     * @param bloque
     * @param idusuario
     * @throws java.lang.Exception
     */
     public void eliminaRegistroXBloqueXUsuario(Integer bloque, Long idusuario) throws Exception;
     /**
     * Método que lista datos de un número sm en particular.
     * Creado: Levi Laura Fecha: 27/09/2016
     * @param sm
     * @return List Notificacion
     * @throws java.lang.Exception
     */
      public List<Notificacion> listaNotificacionDatosSM(Long sm)  throws Exception;
     /**
     * Método que lista datos de un número sm en particular.
     * Creado: Levi Laura Fecha: 27/09/2016
     * @param sm
     * @return List Notificacion
     * @throws java.lang.Exception
     */
      public List<Notificacion> listaNotificacionDatosSR(Long sr, Long gestion) throws Exception;
      /**
      * Método que lista registros de notificacion tomando en cuenta la sigla , el numero de trámite y la gestión
      * Creado: Levi Laura Fecha: 27/09/2016
      * @param sigla
      * @param numero
      * @param gestion
      * @return List Notificacion
      * @throws java.lang.Exception
      */
      
      public List<Notificacion> listaNotificacionDatosModi(String sigla, Long numero, Long gestion) throws Exception;
      /**
      * Método que lista registros de notificacion tomando en cuenta la sigla , el numero de trámite y la gestión
      * Creado: Levi Laura Fecha: 27/09/2016
      * @param numero_registro
      * @param serie
      * @return List Notificacion
      * @throws java.lang.Exception
      */
      public List<Notificacion> listaNotificacionDatosSMNumReg(Long numero_registro, String serie) throws Exception;
      /**
      * Método que lista registros de notificacion tomando en cuenta el numero de publicacion
      * Creado: Levi Laura Fecha: 27/09/2016
      * @param numero_registro
      * @return List Notificacion
      * @throws java.lang.Exception
      */
      public List<Notificacion> listaNotificacionDatosSMNumPubl(Long numero_publicacion) throws Exception;
      /**
      * Método que lista registros de notificacion tomando en cuenta número de oposicion, y númeor de orden
      * Creado: Levi Laura Fecha: 27/09/2016
      * @param num_opo
      * @param orden_o
      * @return List Notificacion
      * @throws java.lang.Exception
      */
      public List<Notificacion> listaNotificacionDatosOpo(Long num_opo, Long orden_o) throws Exception;
      
      
      /**
      * Método que cambia el estado  para denotar que el tecnico de notificacion recibio el bloque
      * Creado: Levi Laura Fecha: 27/09/2016
      * @param idnotificacion
      * @param idusuarionotificador
      * @throws java.lang.Exception
      */
      
      
      
      public void recibeEntradaNotificaciones(Long idnotificacion,Long idusuarionotificador) throws Exception;
      /**
      * Método que cambia el estado  para denotar que el tecnico de notificacion recibio el bloque
      * Creado: Levi Laura Fecha: 27/09/2016
      * @param listaNotificaBuzon
      * @param idusuario
      * @throws java.lang.Exception
      */
      public void recibeListaEntradaNotificaciones(List<NotificacionBuzon> listaNotificaBuzon, Long idusuario) throws Exception;
      /**
      * Método que lista registros de notificacion tomando en cuenta el usuario solicitante, y su número de bloque
      * Creado: Levi Laura Fecha: 27/09/2016
      * @param idusuarioSoli
      * @param bloque
      * @return List Notificacion
      * @throws java.lang.Exception
      */
      
      
      public List<Notificacion> listaNotificacionParaNotificar(Long idusuarioSoli, Integer bloque) throws Exception;
      
      /**
      * Método que modifica un registro de notificacion enviandole el registro a notificar.
      * Creado: Levi Laura Fecha: 27/09/2016
      * @param notificacion
      * @return  Notificacion
      * @throws java.lang.Exception
      */
      
      
      public Notificacion modificaNotificacion(Notificacion notificacion) throws Exception;
      /**
      * Método que registra el historial de notificaciones
      * Creado: Levi Laura Fecha: 27/09/2016
      * @param idnotificacion
      * @param idusuarionotificador
      * @param estado
      * @param fecha_notifica
      * @param fecha_devol
      * @throws java.lang.Exception
      */
      public void recibeHistorialNotificaciones(Long idnotificacion,Long idusuarionotificador,String estado,Date fecha_notifica,Date fecha_devol) throws Exception;
        /**
      * Método que registra el historial de notificaciones tomando en cuenta el listado del bloque, el usuario notificador, fecha de notificaicon y fecha de devolucion
      * Creado: Levi Laura Fecha: 27/09/2016
      * @param listaNotifica
      * @param idusuario
      * @param fecha_notifica
      * @param fecha_devol
      * @throws java.lang.Exception
      */
      public void recibeListaHistorialNotificaciones(Notificacion listaNotifica, Long idusuario,Date fecha_notifica,Date fecha_devol) throws Exception;
     /**
     * metodo para saver si en sighistorial su ultimo registro insertado es igual al metido segun dos campos descripcion, y estado_marca_descripcion
     * devuelve 1 si encuentra el mismo
     * Creado: Levi Laura Fecha: 02/05/2017
     * @param id
     * @param descripcion
     * @param estado_marca
     * @param opcion
     * @return List Notificacion
     * @throws java.lang.Exception
     */
     public Integer obtenerUltimoRegistroNotificaSigno(Long id,String descripcion,String estado_marca, String opcion) throws Exception;
     /**
     * metodo ue veridifica si en signos  el tramite esta en estado de notificacion
     * devuelve 1 si encuentra el mismo
     * Creado: Levi Laura Fecha: 02/05/2017
     * @param id
     * @return List Notificacion
     * @throws java.lang.Exception
     */
     public Integer obtenerVerificaSiEstadoNotifica(Long id) throws Exception;
     /**
     * metodo ue verifica si en signos  el tramite esta en estado de notificacion
     * devuelve 1 si encuentra el mismo
     * Creado: Levi Laura Fecha: 02/05/2017
     * @param id
     * @return List Notificacion
     * @throws java.lang.Exception
     */
     public Integer obtenerEstadoTramiteNotificacion(Integer bloque , Long id_iusuario_solicitante ,String tipo_tramite_notificacion , String expediente )  throws Exception;
     /**
     * metodo ue verifica si existe 
     * Creado: Levi Laura Fecha: 02/05/2017
     * @param bloque
     * @param id
     * @return List Notificacion
     * @throws java.lang.Exception
     */
     public Integer obtenerTramiteNotificacion(Integer bloque , Long id_iusuario_solicitante ,String tipo_tramite_notificacion , String expediente, Integer gestion ) throws Exception;

}

