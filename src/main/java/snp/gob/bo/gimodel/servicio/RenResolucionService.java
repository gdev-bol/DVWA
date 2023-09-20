/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.servicio;

import java.util.List;
import javax.sql.DataSource;
import snp.gob.bo.gimodel.entidad.Recibo;
import snp.gob.bo.gimodel.entidad.RenResolucion;

/**
 *
 * @author Chano Rojas
 * @see RenResolucionService
 * @version 1.0, 05/06/2016
 */
public interface RenResolucionService {

    /**
     * Método que permite setear la conexion con dataSource
     *
     * @param dataSource
     *     * @version 1.0
     * @throws java.lang.Exception
     */
    public void setDataSource(DataSource dataSource) throws Exception;

    /**
     * Método que permite realizar crud a la tabala renresolucion
     *
     * @author Chano Rojas
     * @see RenResolucionService
     * @param renResolucion
     * @param parametro
     * @return
     * @version 1.0
     * @throws java.lang.Exception
     */
    RenResolucion crudRenResolucion(RenResolucion renResolucion, int parametro) throws Exception;

    /**
     * Método que permite realizar listar la tabla renresolucion
     *
     * @author Chano Rojas
     * @see RenResolucionService
     * @param renResolucion
     * @param parametro
     * @return
     * @throws java.lang.Exception
     * @version 1.0
     *
     */
    public List<RenResolucion> obtieneListadoRenResolucion(RenResolucion renResolucion, int parametro) throws Exception;

    /**
     * Método que permite realizar la busqueda de renresolucion por id
     * renovacion
     *
     * @author Chano Rojas
     * @see RenResolucionService
     * @param idRenRenovacion
     * @return
     * @version 1.0 20/09/2016
     *
     */
    RenResolucion obtieneRenResolucionPorIdRenovacion(Long idRenRenovacion);
    
    List<RenResolucion> obtieneRenResolucionPorIdResolucion(Long idRenResolucion);
    
    /**
     *
     * @return
     */
    public List<RenResolucion> obtienelistaRenovacion();

}
