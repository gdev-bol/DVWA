/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.servicio;

import java.util.List;
import javax.sql.DataSource;
import snp.gob.bo.gimodel.entidad.Regional;

/**
 *
 * @author Eddy Valero
 * @see Regional
 * @see SMDominioServiceImpl
 * @version 1.0, 05/06/2016
 */
public interface RegionalService {

    /**
     * Método que permite setear la conexion con dataSource
     *
     * @param dataSource
     * @throws java.lang.Exception
     */
    public void setDataSource(DataSource dataSource) throws Exception;

    /**
     * Obtener el listado de todas las Regionales
     *
     * @return
     * @throws java.lang.Exception
     */
    public List<Regional> obtenerListadoRegional() throws Exception;

    Regional encuentraRegionalSede();

    Regional obtenerRegionalPorIdRegiona(Long idRegional) throws Exception;

    /**
     * Obtener el listado de todas las Regionales
     *
     * @return
     * @throws java.lang.Exception
     */
    public List<Regional> listadoRegional() throws Exception;

    /**
     * Metodo para obtener el idregional a partir d eun codigo de regional
     *
     * Creado: Susana Escobar Fecha: 28/09/2017
     *
     * @param regional
     * @return
     */
    public Long lista_IdRegional_TipoCiudadNotificacion(String regional);

}
