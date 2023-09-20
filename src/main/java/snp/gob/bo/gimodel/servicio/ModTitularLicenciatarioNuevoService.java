/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.servicio;

import java.util.List;
import javax.sql.DataSource;
import snp.gob.bo.gimodel.entidad.ModTitularLicenciatarioNuevo;
import snp.gob.bo.gimodel.entidad.ObjetoEliminadoGenerico;
import snp.gob.bo.gimodel.solicitudes.entidades.TitularLicenciatariosNuevos;

/**
 *
 * @author susana
 */
public interface ModTitularLicenciatarioNuevoService {
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
     * modtitularlicenciatarionuevo en un listado
     *
     * Creado: Susana Escobar Fecha: 09/09/2016
     *
     * @param listaNuevoTitular
     * @param titularLicencitarioNuevo
     * @return
     */
    public int encuentraPosicionListadoNuevoTitular(List<ModTitularLicenciatarioNuevo> listaNuevoTitular, ModTitularLicenciatarioNuevo titularLicencitarioNuevo);

    /**
     * Metodo para guardar, modificar, listar registro en tabla
     * modtitularlicenciatarionuevo
     *
     * Creado: Susana Escobar Fecha: 09/09/2016
     *
     * @param modTitularLicenciatarioNuevo
     * @param opcion
     * @return
     */
    public ModTitularLicenciatarioNuevo guardar_modificar_listar_ModTitularLicenciatarioNuevo(ModTitularLicenciatarioNuevo modTitularLicenciatarioNuevo, Integer opcion);

    /**
     * Metodo para guardar lista de nuevo titular o licenciatario
     *
     * Creado: Susana Escobar Fecha: 30/09/2016
     *
     * @param listaNuevoTitular
     * @param idmodificacion
     */
    public void guardaListaNuevoTitular(List<ModTitularLicenciatarioNuevo> listaNuevoTitular, Long idmodificacion);

    /**
     * Metodo para guardar, lista de registros en tabla
     * modtitularlicenciatarionuevo
     *
     * Creado: Susana Escobar Fecha: 09/09/2016
     *
     * @param idmodificacion
     * @return
     */
    public List<ModTitularLicenciatarioNuevo> listaNuevoTitularXidmodificacion(Long idmodificacion);

    /**
     * Metodo para modificar, lista de registros en tabla
     * modtitularlicenciatarionuevo
     *
     * Creado: Susana Escobar Fecha: 09/09/2016
     *
     * @param listaNuevoTitular
     * @param idmodificacion
     */
    public void modificarListaTitularLicenciatarioNuevo(List<ModTitularLicenciatarioNuevo> listaNuevoTitular, Long idmodificacion);

    /**
     * Metodo para modificar, lista de registros en tabla
     * modtitularlicenciatarionuevo
     *
     * Creado:Placido Castro Fecha: 04/12/2017
     *
     * @param listaTitularLicenciatariosNuevoSipi
     * @param idModificacion
     * @param idLogTrans
     * @throws Exception
     */
    public void modificarListaTitularLicenciatarioNuevoSubsanacion(List<TitularLicenciatariosNuevos> listaTitularLicenciatariosNuevoSipi, Long idModificacion, Long idLogTrans) throws Exception;
    
    /**
     * Metodo para filtrar (borrar, nuevo, modificar) lista de registros en
     * tabla modtitularlicenciatarionuevo
     *
     * Creado: Susana Escobar Fecha: 09/09/2016
     *
     * @param listaNuevoTitular
     * @param idmodificacion
     */
    public void filtrarListaTitularLicenciatarioNuevo(List<ModTitularLicenciatarioNuevo> listaNuevoTitular, Long idmodificacion);

    /**
     * Metodo para borrar(fisico) registro en tabla modtitularlicenciatarionuevo
     *
     * Creado: Susana Escobar Fecha: 09/09/2016
     *
     * @param modTitularLicenciatarioNuevo
     * @param opcion
     */
    public void borrar_modTitularLicenciatarioNuevo(ModTitularLicenciatarioNuevo modTitularLicenciatarioNuevo, Integer opcion);

    /**
     * Metodo para obtener registros eliminados ModTitularLicenciatarioNuevo, en
     * secciones de solicitante y apoderado, el objeto ObjetoEliminadoGenerico
     * se compone del id eliminado y un texto concatenado con la informacion del
     * titular nuevo
     *
     * Creado: Susana Escobar Fecha: 27/12/2016
     *
     * @param idmodificacion
     * @param criterioBusqueda
     * @return
     */
    public List<ObjetoEliminadoGenerico> obtenerRegistrosEliminadosModTitularLicenciatarioNuevo(Long idmodificacion, String criterioBusqueda);
    
     /**
     * Metodo encontrar la posicion que ocupa un registro
     * modtitularlicenciatarionuevo en un listado para casos de cambio de domicilio
     *
     * Creado: Susana Escobar Fecha: 28/01/2017
     * @param listaNuevoTitular
     * @param titularLicencitarioNuevo
     * @return
     */
    public int encuentraPosicionListadoNuevoTitularDomicilio(List<ModTitularLicenciatarioNuevo> listaNuevoTitular, ModTitularLicenciatarioNuevo titularLicencitarioNuevo);
}
