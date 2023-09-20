/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.servicio;

import java.util.List;
import javax.sql.DataSource;
import snp.gob.bo.gimodel.entidad.Poder;

/**
 *
 * @author Levi Laura
 * @see PoderServiceImpl
 * @version 1.0, 08/09/2016
 */
public interface PoderService {
    
    /**
     * metodo para seterar la fuente de datos
     * 
     * Creado: Levi Laura Fecha: 15/11/2016
     * @param datasource
     */
     public void setDataSource(DataSource dataSource) throws Exception;
     /**
     * metodo que realiza la adicion, modificacon, eliminado, y listado de poderes
     *
     * Creado: Levi Laura Fecha: 15/11/2016
     * @param poder
     * @param parametro
     * @return Poder
     * @throws java.lang.Exception
     */
     public Poder crudPoder(Poder poder, int parametro) throws Exception ;
     /**
     * metodo que lista Poderes segun el tipo de tramite numero de expediente y su  gestion
     *
     * Creado: Levi Laura Fecha: 15/11/2016
     *
     * @param tipoTramite
     * @param nroExped
     * @param gestion
     * @return List<Poder>
     */ 
     public List<Poder> listaPoderXFiltro(String tipoTramite,Long nroExped,Integer gestion) throws Exception;
     /**
     * Metodo que lista Poderes que dado una opcion de búsqueda busca el dato requerido
     *
     * Creado: Levi Laura Fecha: 15/11/2016
     *
     * @param dato
     * @param opcion
     * @return List<Poder> 
     */ 
     public List<Poder> listaPoderBusqueda(String dato,int opcion) throws Exception ;
}
