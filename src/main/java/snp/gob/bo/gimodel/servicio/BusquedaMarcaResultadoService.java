/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package snp.gob.bo.gimodel.servicio;

import java.util.List;
import javax.sql.DataSource;
import snp.gob.bo.gimodel.entidad.BusquedaMarcaResultado;

/**
 *
 * @author Eddy Valero
 * @see BusquedaServiceImpl
 * @version 1.0, 17/10/2016
 */

public interface BusquedaMarcaResultadoService {
    
    /**
     * Método que permite setear la conexion con dataSource
     *
     * Creado: Eddy Valero Fecha: 17/10/2016
     * 
     * @param dataSource
     * @throws java.lang.Exception
     */
    public void setDataSource(DataSource dataSource) throws Exception;
    
    
    
    /**
     * Método que obtiene registros de una determinada busqueda
     *
     * Creado: Eddy Valero Fecha: 18/10/2016
     * 
     * @param criterioBusqueda
     * @param campo
     * @param claseNiza
     * @return List, listado
     * @throws java.lang.Exception
     */
    public List<BusquedaMarcaResultado> realizarBusquedaPorCriterios(String criterioBusqueda, String campo, int claseNiza) throws Exception;
    
    public List<BusquedaMarcaResultado> realizarBusquedaMarca(String criterioBusqueda, String campo, int claseNiza) throws Exception;
    
    
    
    
    
    
    
    
    
    
    
    

    
     
}
