/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.servicio;

import java.util.Date;
import java.util.List;
import javax.sql.DataSource;
import snp.gob.bo.gimodel.entidad.Sistema;
/**
 *
 * @author Levi Laura
 * @see SistemaServiceImpl
 * @version 1.0, 08/09/2016
 */
public interface SistemaService {
    
     /**
     * Método que permite setear la conexion con dataSource
     *
     * Creado: Levi Laura Fecha: 08/06/2017
     * @param dataSource
     * @throws java.lang.Exception
     */
     public void setDataSource(DataSource dataSource) throws Exception;
    
    /**
     * Metodo para listar guardar modificar eliminar de la tabla sistema
     *
     * Creado: Levi Laura  Fecha: 09/11/2016
     * @param sistema
     * @param operacion
     * @return Sistema
     * @throws java.lang.Exception
     */
    
    
    public Sistema guardar_modificar_listar_Sistema(Sistema sistema, Integer operacion) throws Exception;
    /**
     * Metodo para listar todos los registros de la tabla sistema que esten en estado AC
     *
     * Creado: Levi Laura  Fecha: 09/11/2016
     * @return  List<Sistema> 
     * @throws java.lang.Exception
     */
    
    
    public List<Sistema> listaSistemas() throws Exception;
    
    /**
     * Metodo que cambia el estado a NA a menus y paginas asociados a un idsistema
     *
     * Creado: Levi Laura  Fecha: 08/12/2017
     * @param idsistema
     * @throws java.lang.Exception
     */
    
    
    public void eliminaMenusPaginas(Long idsistema) throws Exception;
}
