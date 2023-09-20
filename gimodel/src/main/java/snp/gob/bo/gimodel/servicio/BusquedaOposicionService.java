/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.servicio;

import java.util.Date;
import java.util.List;
import javax.sql.DataSource;
import snp.gob.bo.gimodel.entidad.BusquedaOposicion;

/**
 *
 * @author Luis Angel Quispe Limachi
 */
public interface BusquedaOposicionService {
     /**
     * Método que permite setear la conexion con dataSource
     *
     * Creado: Luis Angel Quispe Limachi Fecha: 16/11/2016
     * 
     * @param dataSource
     * @throws java.lang.Exception
     */
    public void setDataSource(DataSource dataSource) throws Exception;
    
    
     /**
     * Método que obtiene registros de que se obtienen por numero de oposicion
     *
     * Creado: Luis Angel Quispe Fecha: 16/11/2016
     * 
     * @param publicacion
     * @param soliapo
     * @return List, BusquedaOposicion
     * @throws java.lang.Exception
     */
    public List<BusquedaOposicion> realizarBusquedaxnropublicacion(Long publicacion,String soliapo) throws Exception;
         /**
     * Método que obtiene registros que se obtienen por numero de registro
     *
     * Creado: Luis Angel Quispe Fecha: 16/11/2016
     * 
     * @param registro
     * @param serie
     * @param soliapo
     * @return List, BusquedaOposicion
     * @throws java.lang.Exception
     */
    public List<BusquedaOposicion> realizarBusquedaxnroregistro(Integer registro,String serie,String soliapo) throws Exception;
         /**
     * Método que obtiene registros que se obtienen por numero de sm
     *
     * Creado: Luis Angel Quispe Fecha: 16/11/2016
     * 
     * @param expesm
     * @param expeanio
     * @param soliapo
     * @return List, BusquedaOposicion
     * @throws java.lang.Exception
     */
    public List<BusquedaOposicion> realizarBusquedaxnrosm(Long expesm,Long expeanio,String soliapo) throws Exception;
         /**
     * Método que obtiene registros que se obtienen por marca
     *
     * Creado: Luis Angel Quispe Fecha: 16/11/2016
     * 
     * @param marca
     * @param soliapo 
     * @return List, BusquedaOposicion
     * @throws java.lang.Exception
     */
    public List<BusquedaOposicion> realizarBusquedaxmarca(String marca,String soliapo) throws Exception;
         /**
     * Método que obtiene registros por el nombre de demandado y/o demandante
     *
     * Creado: Luis Angel Quispe Fecha: 16/11/2016
     * 
     * @param nombredmtedmdo
     * @param soliapo
     * @return List, BusquedaOposicion
     * @throws java.lang.Exception
     */
    public List<BusquedaOposicion> realizarBusquedaxdmdodmte(String nombredmtedmdo,String soliapo) throws Exception;
    
     /**
     * Método que obtiene registros por el apoderado
     *
     * Creado: Luis Angel Quispe Fecha: 28/11/2016
     * 
     * @param nombreapoderado
     * @param soliapo
     * @return List, BusquedaOposicion
     * @throws java.lang.Exception
     */
    public List<BusquedaOposicion> realizarBusquedaxapoderado(String nombreapoderado,String soliapo) throws Exception;
        
     /**
     * Método que obtiene registros por la fecha de presentacion
     *
     * Creado: Luis Angel Quispe Fecha: 16/11/2016
     * 
     * @param fechapresent
     * @param soliapo
     * @return List, BusquedaOposicion
     * @throws java.lang.Exception
     */
    public List<BusquedaOposicion> realizarBusquedaxfechapresentacion(Date fechapresent,String soliapo) throws Exception;
    
}
