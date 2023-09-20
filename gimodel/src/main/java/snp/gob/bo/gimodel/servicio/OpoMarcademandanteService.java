/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.servicio;

import java.util.List;
import javax.sql.DataSource;
import snp.gob.bo.gimodel.entidad.OpoMarcademandante;

/**
 *
 * @author desarrollo
 */
public interface OpoMarcademandanteService {

    /**
     * Método que permite setear la conexion con dataSource
     *
     * @param dataSource
     * @throws java.lang.Exception
     */
    public void setDataSource(DataSource dataSource) throws Exception;

    /**
     * Metodo para obtener Guardar opomarcademandante Creado: Luis Quispe Fecha:
     * 10/09/2016
     *
     * @param opodemandante
     * @return OpoMarcademandante
     * @throws java.lang.Exception
     */

    public OpoMarcademandante guardaOpodemandante(OpoMarcademandante opodemandante) throws Exception;

    /**
     * Metodo para guardar opomarcademandante objeto a una lista Creado: Luis
     * Quispe Fecha: 20/09/2016
     *
     * @param listadodema
     * @throws java.lang.Exception
     */
    public void guardalista(List<OpoMarcademandante> listadodema) throws Exception;

    /**
     * Metodo para guardar opomarcademandante Creado: Luis Quispe Fecha:
     * 25/09/2016
     *
     * @param datosdemandante
     * @return OpoMarcademandante
     * @throws java.lang.Exception
     */
    public OpoMarcademandante guardardemandante(OpoMarcademandante datosdemandante) throws Exception;

    /**
     * Metodo para modificar opomarcademandante Creado: Luis Quispe Fecha:
     * 30/09/2016
     *
     * @param datosdemandantemodi
     * @return OpoMarcademandante
     * @throws java.lang.Exception
     */
    public OpoMarcademandante modificardemandante(OpoMarcademandante datosdemandantemodi) throws Exception;

    /**
     * Metodo para obtener la listado de opomarcademandante por idoposicion
     * Creado: Luis Quispe Fecha: 01/10/2016
     *
     * @param didoposicion
     * @return List<OpoMarcademandante>
     * @throws java.lang.Exception
     */
    public List<OpoMarcademandante> obtenerListadoOpomarcademandante(Long didoposicion) throws Exception;

    /**
     * Metodo para obtener la listado de opomarcademandante por numerooposicion
     * Creado: Luis Quispe Fecha: 02/10/2016
     *
     * @param dnroopo
     * @return List<OpoMarcademandante>
     * @throws java.lang.Exception
     */
    public List<OpoMarcademandante> obtenerListadoOpomarcademandantexnroopo(Long dnroopo) throws Exception;

    /**
     * Metodo para obtener los demandantes por el idoposicion Creado: Luis
     * Quispe Fecha: 02/10/2016
     *
     * @param didopo
     * @return OpoMarcademandante
     * @throws java.lang.Exception
     */
    public OpoMarcademandante obtieneelobjetodmtexidoposicion(Long didopo) throws Exception;

    /**
     * Metodo para obtener los demandantes por el idoposicion Creado: Luis
     * Quispe Fecha: 03/10/2016
     *
     * @param idoposicionx
     * @return OpoMarcademandante
     * @throws java.lang.Exception
     */
    public Long obtenerOpomarcademandnteXidopo(Long idoposicionx) throws Exception;

    /**
     * Metodo para modificar opomarcademandane Creado: Luis Quispe Fecha:
     * 03/10/2016
     *
     * @param opodemandantemodi
     * @return OpoMarcademandante
     * @throws java.lang.Exception
     */
    public OpoMarcademandante modificarOpomarcademandnte(OpoMarcademandante opodemandantemodi) throws Exception;

    /**
     * Metodo para Eliminar opomarcademandante opomarcademandane Creado: Luis
     * Quispe Fecha: 05/10/2016
     *
     * @param idmarcadmte
     * @param idoposicion
     * @return String
     * @throws java.lang.Exception
     */
    public String eliminarOpomarcademandante(Long idmarcadmte, Long idoposicion) throws Exception;
    
 /**
     * Metodo para encontrar Creado:
     * 
     * Luis Quispe 
     *
     * Fecha: 03/10/2016
     *
     * @param idoposicion
     * @return OpoMarcademandante
     * @throws java.lang.Exception
     */
    public OpoMarcademandante obtieneobjetoxmarcadmte(Long idoposicion) throws Exception;
}
