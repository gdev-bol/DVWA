/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.servicio;

import java.util.List;
import javax.sql.DataSource;
import snp.gob.bo.gimodel.entidad.OpoMarcademandada;

/**
 *
 * @author Luis ANgel Quispe Limachi
 */
public interface OpoMarcademandadaService {

    /**
     * Método que permite setear la conexion con dataSource
     *
     * @param dataSource
     * @throws java.lang.Exception
     */
    public void setDataSource(DataSource dataSource) throws Exception;

    /**
     * Metodo para guardar opomarcademandada 
     * creado: Luis Quispe Fecha: 05/08/2016
     *
     * @param opodemandada
     * @return OpoMarcademandada
     * @throws java.lang.Exception
     */
    public OpoMarcademandada guardaOpodemandada(OpoMarcademandada opodemandada) throws Exception;

    /**
     * Metodo para obtener la lista de opomarcademandada 
     * Creado: Luis Quispe Fecha: 27/09/2016
     *
     * @param didoposicion
     * @return List<OpoMarcademandada>
     * @throws java.lang.Exception
     */
    public List<OpoMarcademandada> obtenerListadoOpomarcademandada(Long didoposicion) throws Exception;

    /**
     * Metodo para obtener la lista de actividades de la tabla actividad 
     * Creado:Luis Quispe Fecha: 02/09/2016
     *
     * @param didoposicionmarc
     * @return OpoMarcademandada
     * @throws java.lang.Exception
     */
    public OpoMarcademandada obtenerOpomarcademandadaobj(Long didoposicionmarc) throws Exception;

    /**
     * Metodo para mostrar la descripcion de la actividad 
     * Creado: Luis Quispe Fecha: 05/09/2016
     *
     * @param idoposicionx
     * @return Long
     * @throws java.lang.Exception
     */

    public Long obtenerOpomarcademandadaXidopo(Long idoposicionx) throws Exception;

    /**
     * Metodo para obtener Modificar opomarcademandada 
     * Creado: Luis Quispe Fecha: 10/09/2016
     *
     * @param opodemandadamodi
     * @return OpoMarcademandada
     * @throws java.lang.Exception
     */
    public OpoMarcademandada modificarOpomarcademandada(OpoMarcademandada opodemandadamodi) throws Exception;
    /**
     * Metodo para mostrar la descripcion de la actividad 
     * Creado: Luis Quispe Fecha: 05/09/2016
     *
     * @param idmarcadmda
     * @param idoposicion
     * @return String
     * @throws java.lang.Exception
     */
    public String eliminarOpomarcademandada(Long idmarcadmda, Long idoposicion) throws Exception;

    /**
     * Metodo que verifica que el numero de numero oposicion existe
     * creado: Luis Quispe Fecha: 28/08/2016
     *
     * @param nroopo
     * @return boolean
     * @throws java.lang.Exception
     */
    public Boolean verificasiexistenumeroopodmda(Integer nroopo)throws Exception;
    
}
