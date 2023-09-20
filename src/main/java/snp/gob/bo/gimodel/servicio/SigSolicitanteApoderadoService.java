/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.servicio;

import java.util.List;
import javax.sql.DataSource;
import snp.gob.bo.gimodel.entidad.ObjetoEliminadoGenerico;
import snp.gob.bo.gimodel.entidad.SigSolicitanteApoderado;
import snp.gob.bo.gimodel.solicitudes.entidades.Apoderados;
import snp.gob.bo.gimodel.solicitudes.entidades.Solicitantes;

/**
 *
 * @author Eddy Valero
 * @version 1.0, 29/08/2016
 */
public interface SigSolicitanteApoderadoService {

    public void setDataSource(DataSource dataSource);

    public SigSolicitanteApoderado registrarSigSigSolicitanteApoderado(SigSolicitanteApoderado sigSolicitanteApoderado) throws Exception;

    /**
     * Metodo de busca un objetos SigSolicitanteApoderado por idsignomarca
     * Susana Escobar Paz Fecha: 14/09/2016
     *
     * @param idsignomarca
     * @return
     */
    public List<SigSolicitanteApoderado> listadoSolicitanteXidsignomarca(Long idsignomarca);

    /**
     * Metodo para obtener los apoderados activos de una determinada marca
     * Creado: Eddy Valero Fecha: 27/09/2016
     *
     * @param idsignomarca
     * @return List<SigSolicitanteApoderado>
     * @throws java.lang.Exception
     */
    public List<SigSolicitanteApoderado> listadoApoderadoXidsignomarca(Long idsignomarca) throws Exception;

    /**
     * Metodo para crear, modificar, listar registros en tabla
     * sigsolicitanteapoderado Susana Escobar Paz Fecha: 20/09/2016
     *
     * @param sigsolicitanteApoderado
     * @param operacion
     * @return
     */
    public SigSolicitanteApoderado guardar_modificar_listar_SigSolicitanteApoderado(SigSolicitanteApoderado sigsolicitanteApoderado, Integer operacion);

    /**
     * Metodo para guardar la lista de solicitantes enviadas desde la vista
     * creado: Eddy Valero Fecha: 03/10/2016
     *
     * @param listadoSolicitanteApoderado
     * @param idSigSignoMarca
     * @throws java.lang.Exception
     */
    public void guardaListaSolicitantes(List<SigSolicitanteApoderado> listadoSolicitanteApoderado, Long idSigSignoMarca) throws Exception;
    
    /**
     * Metodo para guardar la lista de solicitantes enviadas desde la vista
     * creado: Eddy Valero Fecha: 22/11/2016
     *
     * @param listadoSolicitanteApoderado
     * @param idSigSignoMarca
     * @param idLogTrans
     * @throws java.lang.Exception
     */
    public void guardaListaSolicitantes(List<SigSolicitanteApoderado> listadoSolicitanteApoderado, Long idSigSignoMarca, Long idLogTrans) throws Exception;

    /**
     * Metodo para guardar la lista de solicitantes enviadas desde la vista
     * creado: Eddy Valero Fecha: 03/10/2016
     *
     * @param listadoSolicitanteApoderado
     * @param sigSolicitanteApoderado
     * @return int
     * @throws java.lang.Exception
     */
    public int encuentraPosicionListadoSolicitanteApoderado(List<SigSolicitanteApoderado> listadoSolicitanteApoderado,
            SigSolicitanteApoderado sigSolicitanteApoderado) throws Exception;

    /**
     * Metodo para actualizar los datos de Solicitantes y Apoderados creado:
     * Eddy Valero Fecha: 08/10/2016
     *
     * @param numeroSr
     * @param tipoPersona
     * @return
     * @throws java.lang.Exception
     */
    public List<SigSolicitanteApoderado> buscaSolicitanteApoderadoPoridSolicitudyTipoPersona(Long numeroSr, String tipoPersona) throws Exception;

    /**
     * Metodo para actualizar los datos de Solicitantes y Apoderados
     *
     * creado: Eddy Valero Fecha: 08/10/2016
     *
     * @param idSignoMarca
     * @param listaSolicitanteApoderado
     * @param tipoSoliOApo
     * @param IdLogTrans
     * @throws java.lang.Exception
     */
    public void modificaListaSolicitanteApoderado(Long idSignoMarca, List<SigSolicitanteApoderado> listaSolicitanteApoderado, String tipoSoliOApo, Long IdLogTrans) throws Exception;

    /**
     * Metodo para actualizar los datos de Solicitantes y Apoderados
     *
     * Creado:Placido Castro Fecha: 24/11/2017
     *
     * @param idSignoMarca
     * @param listaSolicitantesSipi
     * @param tipoSoliOApo
     * @param IdLogTrans
     * @throws java.lang.Exception
     */
    public void modificarListaSolicitanteSubsanacion(Long idSignoMarca, List<Solicitantes> listaSolicitantesSipi, String tipoSoliOApo, Long IdLogTrans) throws Exception;


    /**
     * Metodo para actualizar los datos de Solicitantes y Apoderados
     *
     * creado: Eddy Valero Fecha: 08/10/2016
     *
     * @param idSignoMarca
     * @param listaApoderadosSipi
     * @param tipoSoliOApo
     * @param IdLogTrans
     * @throws java.lang.Exception
     */
    public void modificarListaApoderadoSubsanacion(Long idSignoMarca, List<Apoderados> listaApoderadosSipi, String tipoSoliOApo, Long IdLogTrans) throws Exception;

   
    /**
     * Metodo crud de la tabla sigsolicitanteapoderado
     *
     * creado: Eddy Valero Fecha: 08/10/2016
     *
     * @param sigSolicitanteApoderado     
     * @param parametro     
     * @return SigSolicitanteApoderado
     * @throws java.lang.Exception
     */
    public SigSolicitanteApoderado crudSigSolicitanteApoderado(SigSolicitanteApoderado sigSolicitanteApoderado, int parametro) throws Exception;
    
    /**
     * Obtener datos concatenados de Nombre, Pais, Pais descripcion, Departamento, Departamento descripcion
     *
     * creado: Susana Escobar Fecha: 15/12/2016
     *
     * @param idsignomarca
     * @param tipo
     * @return
     */
    public String[] datos_SigSolicitanteApoderado_concatenado(Long idsignomarca, String tipo);
    
    /**
     * Obtener un registro de acuerdo a su idSolicitanteApoderado
     *
     * creado: Eddy Valero Fecha: 28/11/2016
     *
     * @param idSolicitanteApoderado
     * @param tipoPersona
     * @return SigSolicitanteApoderado
     * @throws java.lang.Exception
     */
    public SigSolicitanteApoderado obtenerSigSolicitanteApoderado(Long idSolicitanteApoderado, String tipoPersona) throws Exception;
    
    
    /**
     * Buscar si se elimino algun registro
     *
     * creado: Eddy Valero Fecha: 28/11/2016
     *
     * @param idSignoMarca
     * @param criterioBusqueda
     * @param tipoPersona
     * @return SigSolicitanteApoderado
     * @throws java.lang.Exception
     */
    public ObjetoEliminadoGenerico obtenerRegistrosEliminadosSigSolicitanteApoderado(Long idSignoMarca, String criterioBusqueda, String tipoPersona) throws Exception;
    
    
}
