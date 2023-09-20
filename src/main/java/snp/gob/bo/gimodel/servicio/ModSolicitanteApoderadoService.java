/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.servicio;

import java.util.List;
import javax.sql.DataSource;
import snp.gob.bo.gimodel.entidad.ModSolicitanteApoderado;
import snp.gob.bo.gimodel.entidad.ObjetoEliminadoGenerico;
import snp.gob.bo.gimodel.solicitudes.entidades.Apoderados;
import snp.gob.bo.gimodel.solicitudes.entidades.Solicitantes;

/**
 *
 * @author susana
 */
public interface ModSolicitanteApoderadoService {

    /**
     * Método que permite setear la conexion con dataSource
     *
     * Creado: Susana Escobar Paz Fecha: 10/09/2016
     *
     * @param dataSource
     */
    public void setDataSource(DataSource dataSource);

    /**
     * Metodo para guardar, modificar, listar registros en tabla
     * modsolicitanteapoderado
     *
     * Creado: Susana Escobar Fecha: 10/09/2016
     *
     * @param modsolicitanteApoderado
     * @param operacion
     * @return
     */
    public ModSolicitanteApoderado guardar_modificar_listar_SolicitanteApoderado(ModSolicitanteApoderado modsolicitanteApoderado, Integer operacion);

    /**
     * Metodo para listar registros modsolicitanteapoderado - Solicitantes por
     * idmodificacion
     *
     * Creado: Susana Escobar Fecha: 10/09/2016
     *
     * @param idmodificacion
     * @return
     */
    public List<ModSolicitanteApoderado> listadoSolicitanteXidmodificacion(Long idmodificacion);

    /**
     * Metodo para listar registros modsolicitanteapoderado - Apoderados por
     * idmodificacion
     *
     * Creado: Susana Escobar Fecha: 10/09/2016
     *
     * @param idmodificacion
     * @return
     */
    public List<ModSolicitanteApoderado> listadoApoderadoXidmodificacion(Long idmodificacion);

    /**
     * Metodo encontrar la posicion que ocupa un registro en un listado
     *
     * Creado: Susana Escobar Fecha: 11/09/2016
     *
     * @param listadoSolicitanteApoderado
     * @param smsolicitanteApoderado
     * @return
     */
    public int encuentraPosicionListadoSolicitanteApoderado(List<ModSolicitanteApoderado> listadoSolicitanteApoderado, ModSolicitanteApoderado smsolicitanteApoderado);

    /**
     * Metodo para guardar lista de solicitantes o apoderados vinculados a un
     * idmodificacion
     *
     * Creado: Susana Escobar Fecha: 11/09/2016
     *
     * @param listadoSolicitanteApoderado
     * @param idmodificacion
     * @param idLogTrans
     */
    public void guardaListaSolicitantes(List<ModSolicitanteApoderado> listadoSolicitanteApoderado, Long idmodificacion, Long idLogTrans);

    /**
     * Metodo para modificar lista de solicitantes o apoderados vinculados a un
     * idmodificacion
     *
     * Creado: Susana Escobar Fecha: 11/09/2016 Modificado: Susana Escobar
     * Fecha: 27/12/2016
     *
     * @param listadoSolicitanteApoderado
     * @param idmodificacion
     * @param tipo
     * @param idLogTrans
     */
    public void modificarListaSolicitanteApoderado(List<ModSolicitanteApoderado> listadoSolicitanteApoderado, Long idmodificacion, String tipo, Long idLogTrans);

    /**
     * Metodo para modificar lista de solicitantes subsanacion
     *
     * Creado: Placido Castro
     * Fecha: 28/11/2017
     *
     * @param listaSolicitantesSipi
     * @param idModificacion
     * @param idLogTrans
     * @throws java.lang.Exception
     */
    public void modificarListaSolicitanteSubsanacion(List<Solicitantes> listaSolicitantesSipi, Long idModificacion, Long idLogTrans)  throws Exception;
    
    
    /**
     * Metodo para modificar lista de solicitantes subsanacion
     *
     * Creado: Placido Castro
     * Fecha: 28/11/2017
     *
     * @param listaApoderadosSipi
     * @param idModificacion
     * @param idLogTrans
     * @throws java.lang.Exception
     */
    public void modificarListaApoderadoSubsanacion(List<Apoderados> listaApoderadosSipi, Long idModificacion, Long idLogTrans)  throws Exception;
    
    
    /**
     * Metodo para filtrar (eliminados, nuevos y modificacion) en lista de
     * solicitantes o apoderados vinculados a un idmodificacion
     *
     * Creado: Susana Escobar Fecha: 11/09/2016
     *
     * @param listadoSolicitanteApoderado
     * @param idmodificacion
     * @param tipo
     */
    public void filtrarListaSolicitanteApoderado(List<ModSolicitanteApoderado> listadoSolicitanteApoderado, Long idmodificacion, String tipo);

    /**
     * Metodo para borrar registros en tabla modsolicitanteapoderado
     *
     * Creado: Susana Escobar Fecha: 10/09/2016
     *
     * @param modsolicitanteapoderado
     * @param operacion
     */
    public void borrar_solicitanteApoderado(ModSolicitanteApoderado modsolicitanteapoderado, Integer operacion);

    /**
     * Metodo para listar ModSolicitanteApoderado por id
     *
     * Creado: Susana Escobar Fecha: 27/12/2016
     *
     * @param idSolicitanteApoderado
     * @param tipoPersona
     * @return
     */
    public ModSolicitanteApoderado listar_ModSolicitanteApoderado_id(Long idSolicitanteApoderado, String tipoPersona);

    /**
     * Metodo para obtener registros eliminados ModSolicitanteApoderado, en
     * secciones de solicitante y apoderado, el objeto ObjetoEliminadoGenerico
     * se compone del id eliminado y un texto concatenado con la informacion del
     * solicitante o apoderado
     *
     * Creado: Susana Escobar Fecha: 27/12/2016
     *
     * @param idmodificacion
     * @param criterioBusqueda
     * @param tipoPersona
     * @return
     * @throws Exception
     */
    public List<ObjetoEliminadoGenerico> obtenerRegistrosEliminadosModSolicitanteApoderado(Long idmodificacion, String criterioBusqueda, String tipoPersona) throws Exception;
}
