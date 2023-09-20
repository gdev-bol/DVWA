/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.servicio;

import java.util.List;
import javax.sql.DataSource;
import snp.gob.bo.gimodel.entidad.ModTipoSigno;
import snp.gob.bo.gimodel.solicitudes.entidades.ModTipoSignos;

/**
 *
 * @author Susana Escobar
 * @see ModTiposignoService
 * @version 1.0, 15/09/2016
 */
public interface ModTipoSignoService {

    /**
     * Método que permite setear la conexion con dataSource
     *
     * @param dataSource
     * @throws java.lang.Exception
     */
    public void setDataSource(DataSource dataSource) throws Exception;

    /**
     * Metodo para guardar, modificar, listar registro en tabla modtiposigno
     *
     * Creado: Susana Escobar Fecha: 15/09/2016
     *
     * @param modTipoSigno
     * @param parametro
     * @return 
     */
    public ModTipoSigno guardar_modificar_listar_ModTipoSigno(ModTipoSigno modTipoSigno, int parametro);

     /**
     * Metodo para guardar, lista de registros en tabla modtiposigno
     *
     * Creado: Susana Escobar Fecha: 15/09/2016
     *
     * @param listaModTipoSigno
     * @param idmodificacion
     * @param parametro 
     * @param idLogTrans 
     */
    public void guardar_listaModTipoSigno(List<ModTipoSigno> listaModTipoSigno, Long idmodificacion, int parametro, Long idLogTrans);

     /**
     * Metodo para guardar, lista de registros en tabla modtiposigno
     *
     * Creado: Placido Castro Fecha: 04/12/2017
     *
     * @param listaTipoSignosHidra
     * @param idmodificacion
     * @param idLogTrans 
     * @param listaTipoSignosSipi
     * @throws java.lang.Exception
     */
    public void modificarListaTipoSignoSubsanacion(Long idmodificacion, List<ModTipoSigno> listaTipoSignosHidra,
            List<ModTipoSignos> listaTipoSignosSipi, Long idLogTrans)  throws Exception;
    
     /**
     * Metodo para listar registros tiposigno por idmodificacion
     *
     * Creado: Susana Escobar Fecha: 15/09/2016
     *
     * @param idmodificacion 
     * @return  
     */
    public List<ModTipoSigno> listado_modtiposignoXidmodificacion(Long idmodificacion);

     /**
     * Metodo para borrar(fisico) lista de registros modtiposigno por idmodificacion
     *
     * Creado: Susana Escobar Fecha: 16/09/2016
     *
     * @param idmodificacion  
     */
    public void borrar_listamodtiposignoXidmodificacion(Long idmodificacion);

     /**
     * Metodo para borrar(fisico) registro modtiposigno
     *
     * Creado: Susana Escobar Fecha: 16/09/2016
     *  
     * @param modTipoSigno
     * @param parametro
     */
    public void borrar_modtiposigno(ModTipoSigno modTipoSigno, int parametro);

}
