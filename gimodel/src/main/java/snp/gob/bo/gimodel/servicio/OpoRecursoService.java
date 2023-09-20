/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.servicio;

import java.util.List;
import javax.sql.DataSource;
import snp.gob.bo.gimodel.entidad.OpoRecurso;

/**
 *
 * @author Luis Angel Quispe Limachi
 */
public interface OpoRecursoService {
    
    /**
     * Método que permite setear la conexion con dataSource
     *
     * @param dataSource
     * @throws java.lang.Exception
     */

     public void setDataSource(DataSource dataSource)throws Exception;
     
    /**
     * Metodo para obtener la lista de oposicion por el numero de oposicion
     * Creado: Luis Quispe Fecha: 30/09/2016
     *
     * @param nroopo
     * @return List<OpoRecurso>
     * @throws java.lang.Exception
     */
    public List<OpoRecurso> obtenerListadoOpoRecursoxnrooposicion(Long nroopo) throws Exception;
     /**
     * Metodo para guardar oporecurso 
     * creado: Luis Quispe Fecha: 03/12/2016
     *
     * @param oporerecurso
     * @return OpoResolucion
     * @throws java.lang.Exception
     */
    public OpoRecurso guardaOpoRecurso(OpoRecurso oporerecurso) throws Exception;
     /**
     * Metodo para extraer el objeto resolucion por el iddelevento
     * creado: Luis Quispe Fecha: 03/12/2016
     *
     * @param ideventorecur
     * @return OpoResolucion
     * @throws java.lang.Exception
     */
    public OpoRecurso extraeRecursoxidevento(Long ideventorecur) throws Exception;
     /**
     * Metodo para modificar oporecurso 
     * creado: Luis Quispe Fecha: 05/12/2016
     *
     * @param oporecurso
     * @return OpoResolucion
     * @throws java.lang.Exception
     */
    public OpoRecurso modificarOporecurso(OpoRecurso oporecurso) throws Exception;
    
     /**
     * Metodo para verificar si el recurso existe
     * creado: Luis Quispe Fecha: 05/12/2016
     *
     * @param numerorecurso
     * @return OpoRecurso
     * @throws java.lang.Exception
     */
    public List<OpoRecurso> validacionrecursorepetido(Integer numerorecurso) throws Exception;
    
}
