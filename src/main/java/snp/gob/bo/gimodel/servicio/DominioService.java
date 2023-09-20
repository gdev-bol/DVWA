/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.servicio;

import java.util.List;
import javax.sql.DataSource;
import snp.gob.bo.gimodel.entidad.Dominio;

/**
 *
 * @author Eddy Valero
 * @see Dominio
 * @see DominioService
 * @version 1.0, 23/08/2016
 */
public interface DominioService {

    /**
     * Método que permite setear la conexion con dataSource
     *
     * @param dataSource
     * @throws java.lang.Exception
     */
    public void setDataSource(DataSource dataSource) throws Exception;

//    public int registrarSMDominio(Dominio sMDominio);
//    
//    public Dominio registrarDominio(Dominio sMDominio) throws Exception;
//    
//    //Obtener el listado de todas los dominios
//    public List<SMDominio> obtenerListaDominios();
    public List<Dominio> obtenerListadoDominio(String dominio) throws Exception;

    /**
     * Método que permite obtener objeto dominio metiendo como parametros el
     * campo dominio y el codigo
     *
     * Levi Laura
     *
     * @param dominio
     * @param codigo
     * @return
     * @throws java.lang.Exception
     */
    public List<Dominio> obtenerListadoTipoTramite(String dominio) throws Exception; 
            
    /**
     * Método que permite obtener objeto dominio metiendo como parametros el
     * campo dominio y el codigo
     *
     * Levi Laura
     *
     * @param dominio
     * @param codigo
     * @return
     */

    public List<Dominio> obtenerListadoDominioXCodigo(String dominio, String codigo);

    /**
     * Método que permite obtener lista de dominios por dominio y dominio padre
     *
     * Susana Escobar Paz
     *
     * @param dominio
     * @param dominiopadre
     * @return
     * @throws java.lang.Exception
     */
    public List<Dominio> listar_dominio_dominiopadre(String dominio, String dominiopadre) throws Exception;

    /**
     * Método que obtiene el nombre desglosado de un Dominio de acuerdo a un
     * determinado codigo cambiar almetodo
     *
     * *****************************************REVISAR
     * METODO***************************************
     *
     * Creado: Eddy Valero 
     * Modificado: Susana Escobar Fecha: 18/09/2016
     *
     * @param dominio
     * @param codigo
     * @return String
     * @throws java.lang.Exception
     * @version 1.0 30/11/2016
     */
    String obtenerNombreCodigoDominio(String dominio, String codigo) throws Exception;

    /**
     * Método que obtiene el nombre desglosado de un Dominio de acuerdo a un
     * determinado codigo
     *
     * Eddy Valero
     *
     * @param dominio
     * @param codigo
     * @return String
     * @throws java.lang.Exception
     * @version 1.0 30/11/2016
     */
    String obtenerNombrePorCodigoDominio(String codigo, String dominio) throws Exception;

    /**
     * Método que obtiene el listado del tipo_tramite_recibo
     *
     * Chano Rojas
     *
     * @param dominioPadre
     * @param dominio
     * @return String
     * @throws java.lang.Exception
     * @version 1.0 16/01/2017
     */
    public List<Dominio> ListaTipoTramiteReciboDominio(String dominio, String dominioPadre) throws Exception;

    /**
     * Método que obtiene el nombre desglosado de un Dominio de acuerdo a un
     * determinado codigo
     *
     * ------------------------------------------------------------------------
     * Creado: Eddy Valero Fecha 13/03/2017
     *
     * @param dominio
     * @param nombre
     * @return String
     * @throws java.lang.Exception
     */
    public String obtenerCodigoDominioPorNombre(String dominio, String nombre) throws Exception;

    /**
     * Método que obtiene el nombre desglosado de un Dominio de acuerdo a un
     * determinado codigo
     *
     * ------------------------------------------------------------------------
     * Creado: Eddy Valero Fecha 13/03/2017
     *
     * @param dominio
     * @param nombre
     * @return String
     * @throws java.lang.Exception
     */
  
}
