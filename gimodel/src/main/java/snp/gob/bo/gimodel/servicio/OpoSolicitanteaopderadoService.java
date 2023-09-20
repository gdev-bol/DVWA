/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.servicio;

import java.util.List;
import javax.sql.DataSource;
import snp.gob.bo.gimodel.entidad.OpoSolicitanteapoderado;

/**
 *
 * @author Luis Angel Quispe Limachi
 */
public interface OpoSolicitanteaopderadoService {
/**
     * Método que permite setear la conexion con dataSource
     *
     * @param dataSource
     * @throws java.lang.Exception
     */
    public void setDataSource(DataSource dataSource)throws Exception;
     /**
     * Metodo para guardar solicitante apoderado 
     * Creado: Luis Quispe Fecha: 10/09/2016
     *
     * @param datsolicitanteapode
     * @return OpoMarcademandada
     * @throws java.lang.Exception
     */
    public OpoSolicitanteapoderado guardarsolicitanteapo(OpoSolicitanteapoderado datsolicitanteapode)throws Exception;
    /**
     * Metodo para guardar  en una lista listasolicitanteapoderado todos los oposolicitantesapoderados 
     * Creado: Luis Quispe Fecha: 15/09/2016
     *
     * @param listasolapode
     * @throws java.lang.Exception
     */ 
    public void guardalistasolicitanteapo(List<OpoSolicitanteapoderado> listasolapode)throws Exception;
   /**
     * Metodo para encontrar la posicion de un listado de solicitante apoderado 
     * Creado: Luis Quispe Fecha: 23/09/2016
     *
     * @param listadoSolicitanteApoderado
     * @param smsolicitanteApoderado
     * @return int
     * @throws java.lang.Exception
     */ 
    public int encuentraPosicionListadoSolicitanteApoderado(List<OpoSolicitanteapoderado> listadoSolicitanteApoderado, OpoSolicitanteapoderado smsolicitanteApoderado)throws Exception;
   /**
     * Metodo para obtener la lista de solicitanteapoderadopormarca demandada
     * Creado: Luis Quispe Fecha: 27/09/2016
     *
     * @param idmarcadmda
     * @return List<OpoSolicitanteapoderado>
     * @throws java.lang.Exception
     */ 
    
    public List<OpoSolicitanteapoderado> obtenerListadoSoliApodmda(Long idmarcadmda) throws Exception;
/**
     * Metodo para obtener la lista de solicitanteapoderadopormarca demandante
     * Creado: Luis Quispe Fecha: 27/09/2016
     *
     * @param idmarcadmte
     * @return List<OpoSolicitanteapoderado>
     * @throws java.lang.Exception
     */ 
    public List<OpoSolicitanteapoderado> obtenerListadoSoliApodmte(Long idmarcadmte) throws Exception;
/**
     * Metodo para obtener la listado de solicitantes demandantes 
     * Creado: Luis Quispe Fecha: 30/09/2016
     *
     * @param idmarcasolidmte
     * @return List<OpoSolicitanteapoderado>
     * @throws java.lang.Exception
     */ 
    
    public List<OpoSolicitanteapoderado> obtenerListadoSoliDmte(Long idmarcasolidmte) throws Exception;
    /**
     * Metodo para obtener la listado de apoderados demandantes
     * Creado: Luis Quispe Fecha: 30/09/2016
     *
     * @param idmarcaapodmte
     * @return List<OpoSolicitanteapoderado>
     * @throws java.lang.Exception
     */ 

    public List<OpoSolicitanteapoderado> obtenerListadoApoDmte(Long idmarcaapodmte) throws Exception;
 /**
     * Metodo para obtener la listado de  marcas solicitantes demandadas
     * Creado: Luis Quispe Fecha: 30/09/2016
     *
     * @param idmarcasolidmda
     * @return List<OpoSolicitanteapoderado>
     * @throws java.lang.Exception
     */ 
    public List<OpoSolicitanteapoderado> obtenerListadoSoliDmda(Long idmarcasolidmda) throws Exception;
 /**
     * Metodo para obtener la listado de  apoderados demandadas por idmarcademandada
     * Creado: Luis Quispe Fecha: 30/09/2016
     *
     * @param idmarcaapodmda
     * @return List<OpoSolicitanteapoderado>
     * @throws java.lang.Exception
     */ 
    public List<OpoSolicitanteapoderado> obtenerListadoApodDmda(Long idmarcaapodmda) throws Exception;
 /**
     * Metodo para obtener la listado de  apoderados demandadas por idmarcademandada
     * Creado: Luis Quispe Fecha: 30/09/2016
     *
     * @param oposolicitanteapoderadomodi
     * @return OpoSolicitanteapoderado
     * @throws java.lang.Exception
     */ 
    public OpoSolicitanteapoderado modificarOposolicitanteapoderado(OpoSolicitanteapoderado oposolicitanteapoderadomodi)throws Exception;
    /**
     * Metodo para eliminar la listado de  apoderados demandadas por idmarcademandada
     * Creado: Luis Quispe Fecha: 05/10/2016
     *
     * @param idnotifica
     * @param iddmda
     * @return String
     * @throws java.lang.Exception
     */
    public String eliminarOposolicitanteapoderadoxdmda(Long idnotifica,Long iddmda) throws Exception;
      /**
     * Metodo para eliminar la listado de  apoderados demandadas por idmarcademandante
     * Creado: Luis Quispe Fecha: 07/10/2016
     *
     * @param idnotifica
     * @param iddmte
     * @return String
     * @throws java.lang.Exception
     */
    public String eliminarOposolicitanteapoderadoxdmte(Long idnotifica,Long iddmte) throws Exception;
    
    /**
     * Metodo para obtener el ultimi numero del idsolicitanteapoderado
     * Creado: Luis Quispe Fecha: 20/01/2017
     *
     * @return Long
     * @throws java.lang.Exception
     */
    public Long obtieneelultimonumerodesolicitanteapoderado() throws Exception;
}
