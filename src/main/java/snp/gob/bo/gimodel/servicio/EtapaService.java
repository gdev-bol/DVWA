/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.servicio;

import java.util.List;
import javax.sql.DataSource;
import snp.gob.bo.gimodel.entidad.Etapa;

/**
 *
 * @author Ruben Ramirez
 * @see EtapaServiceImpl
 * @version 1.0, 26/10/2016
 */
public interface EtapaService {

    /**
     * Método que permite setear la conexion con dataSource
     *
     * Creado: Ruben Ramirez Fecha: 26/10/2016
     *
     * @param dataSource
     * @throws java.lang.Exception
     */
    public void setDataSource(DataSource dataSource) throws Exception;

    /**
     * Método que obtiene el listado de etapas por idusuario
     *
     * Creado: Ruben Ramirez Fecha: 27/10/2016
     *
     * @param idusuario
     * @param idetapa
     * @return List, listado
     * @throws java.lang.Exception
     */
    public List<Etapa> listadoPorIdUsuario(Long idusuario, Long idetapa) throws Exception;

    /**
     * Método que lista etapa por tipoTramite y tipoEtapa
     *
     * Creado: Susana Escobar Paz Fecha: 22/11/2016
     *
     * @param tipoTramite
     * @param tipoEtapa
     * @return List, listado
     */
    public Etapa listar_etapa_tipoTramite_tipoEtapa(String tipoTramite, String tipoEtapa);

    /**
     * Método que lista etapa por usuario todas las etapas que iene para el
     * login
     *
     * Creado: Levi Laura Fecha: 05/01/2016
     *
     * @param idusuario
     * @return List, listado
     * @throws java.lang.Exception
     */
    public List<Etapa> listaEtapaXIdUsuario(Long idusuario) throws Exception;

    /**
     * Método que obtiene la etapa por el id etapa
     *
     * Creado: Ruben Ramirez Fecha: 16/03/2017
     *
     * @param idetapa
     * @return List, listado
     * @throws java.lang.Exception
     */
    public Etapa etapaXIdEtapa(Long idetapa) throws Exception;

    /**
     * Método que obtiene ltodas las etapas Creado: Ruben Ramirez Fecha:
     * 16/03/2017
     *
     * @return 
     * @throws java.lang.Exception
     */
    public List<Etapa> listaEtapa() throws Exception;

    /**
     * Método que realiza el crud de etapa
     *
     * Creado: Levi Laura Fecha: 11/12/2017
     *
     * @param etapa
     * @param parametro
     * @return Etapa
     * @throws java.lang.Exception
     */
    public Etapa crudEtapa(Etapa etapa, int parametro) throws Exception;

    public Long idetapaXCodigo(String etapa);

}
