/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.servicio;

import java.util.List;
import javax.sql.DataSource;
import snp.gob.bo.gimodel.entidad.ModTitularLicenciatarioRegistrado;
import snp.gob.bo.gimodel.entidad.ObjetoEliminadoGenerico;
import snp.gob.bo.gimodel.solicitudes.entidades.TitularLicenciatarios;

/**
 *
 * @author susana
 */
public interface ModTitularLicenciatarioRegistradoService {

    /**
     * Método que permite setear la conexion con dataSource
     *
     * Creado: Susana Escobar Paz Fecha: 08/09/2016
     *
     * @param dataSource
     */
    public void setDataSource(DataSource dataSource);

    /**
     * Metodo encontrar la posicion que ocupa un registro
     * modtitularlicenciatarioregistrado en un listado
     *
     * Creado: Susana Escobar Fecha: 09/09/2016
     *
     * @param listaTitularRegistrado
     * @param titularLicenciatarioRegistrado
     * @return
     */
    public int encuentraPosicionListadoTitularRegistrado(List<ModTitularLicenciatarioRegistrado> listaTitularRegistrado, ModTitularLicenciatarioRegistrado titularLicenciatarioRegistrado);

    /**
     * Metodo para guardar, modificar, listar registro en tabla
     * modtitularlicenciatarioregistrado
     *
     * Creado: Susana Escobar Fecha: 10/09/2016
     *
     * @param modTitularLicenciatarioRegistrado
     * @param opcion
     * @return
     */
    public ModTitularLicenciatarioRegistrado guardar_modificar_listar_ModTitularLicenciatarioRegistrado(ModTitularLicenciatarioRegistrado modTitularLicenciatarioRegistrado, Integer opcion);

    /**
     * Metodo para guardar lista de modtitularlicenciatarioregistrado
     *
     * Creado: Susana Escobar Fecha: 10/09/2016
     *
     * @param listaTitularRegistrado
     * @param idmodificacion
     * @param idLogTrans
     */
    public void guardaListaTitularLicenciatarioRegistrado(List<ModTitularLicenciatarioRegistrado> listaTitularRegistrado, Long idmodificacion, Long idLogTrans);

    /**
     * Metodo para listar modtitularlicenciatarioregistrado por idmodificacion
     *
     * Creado: Susana Escobar Fecha: 10/09/2016
     *
     * @param idmodificacion
     * @return
     */
    public List<ModTitularLicenciatarioRegistrado> listaTitularRegistradoXidmodificacion(Long idmodificacion);

    /**
     * Metodo para listar modtitularlicenciatarioregistrado (titular registrado)
     * por idmodificacion
     *
     * Creado: Susana Escobar Fecha: 10/09/2016
     *
     * @param idmodificacion
     * @return
     */
    public List<ModTitularLicenciatarioRegistrado> listaTitularFusionXidmodificacion(Long idmodificacion);

    /**
     * Metodo para listar modtitularlicenciatarioregistrado (participante de la
     * lista de fucion) por idmodificacion
     *
     * Creado: Susana Escobar Fecha: 11/09/2016
     *
     * @param listaTitularRegistrado
     * @param idmodificacion
     * @param tipo
     * @param idLogTrans
     */
    public void modificarListaTitularLicenciatarioRegistrado(List<ModTitularLicenciatarioRegistrado> listaTitularRegistrado, 
            Long idmodificacion, String tipo, Long idLogTrans);

    /**
     * Metodo para listar modtitularlicenciatarioregistrado (participante de la
     * lista de fucion) por idmodificacion
     *
     * Creado:Placido Castro Fecha: 04/12/2017
     *
     * @param listaTitularLicenciatariosSipi
     * @param idModificacion
     * @param idLogTrans
     * @throws Exception
     */
    public void modificarListaTitularLicenciatarioRegistradoSubsanacion(List<TitularLicenciatarios> listaTitularLicenciatariosSipi, Long idModificacion, Long idLogTrans) throws Exception;
    
    /**
     * Metodo para filtrar (guardar, modificar, aliminar) registros
     * modtitularlicenciatarioregistrado
     *
     * Creado: Susana Escobar Fecha: 11/09/2016
     *
     * @param listaTitularRegistrado
     * @param idmodificacion
     * @param tipo
     */
    public void filtrarListaTitularLicenciatarioRegistrado(List<ModTitularLicenciatarioRegistrado> listaTitularRegistrado, Long idmodificacion, String tipo);

    /**
     * Metodo para  borrar(no activo) registro en tabla modtitularlicenciatarioregistrado
     *
     * Creado: Susana Escobar Fecha: 11/09/2016
     *
     * @param modTitularLicenciatarioRegistrado
     * @param opcion
     */
    public void borrar_ModTitularLicenciatarioRegistrado(ModTitularLicenciatarioRegistrado modTitularLicenciatarioRegistrado, Integer opcion);
    
      /**
     * Metodo para obtener registros eliminados ModTitularLicenciatrioRegistrado, en
     * secciones de titular registrado y participante de la lsta de fusion, el objeto ObjetoEliminadoGenerico
     * se compone del id eliminado y un texto concatenado con la informacion del
     * titular registrado o participante de la lista de fusion
     *
     * Creado: Susana Escobar Fecha: 28/12/2016
     * 
     * @param idmodificacion
     * @param criterioBusqueda
     * @param tipo
     * @return
     * @throws Exception
     */
    public List<ObjetoEliminadoGenerico> obtenerRegistrosEliminadosModTitularRegistrado(Long idmodificacion, String criterioBusqueda, String tipo) throws Exception;
}
