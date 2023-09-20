/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.servicio;

import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import snp.gob.bo.gimodel.entidad.Correlativo;
import snp.gob.bo.gimodel.entidad.Procurador;
import snp.gob.bo.gimodel.entidad.TramiteEntrega;
import snp.gob.bo.gimodel.entidad.Usuario;

/**
 *
 * @author Chano Rojas
 * @see ReciboService
 * @see ReciboService
 * @version 1.0, 05/06/2016
 */
public interface EntregaTramiteService {

    /**
     * Método que permite setear la conexion con dataSource
     *
     * @author Chano Rojas
     * @see ReciboService
     *
     * @version 1.0, 05/06/2016
     * @param dataSource
     * @throws java.lang.Exception
     */
    public void setDataSource(DataSource dataSource) throws Exception;

    /**
     * Método que permite setear la conexion con dataSource
     *
     * @author Chano Rojas
     * @param codigoBusqueda
     * @param sigla
     * @param numeroTramite
     * @param gestionTramite
     * @param extension
     * @param soliApod
     * @param datoSoliApod
     * @param numeroRegistro
     * @param serieRegistro
     * @param numero_publicacion
     * @see ReciboService

     * @return
     * @throws java.lang.Exception
     */
    
   public List<TramiteEntrega> listaBuscador(String codigoBusqueda, String sigla,
            int numeroTramite, int gestionTramite, String extension, String soliApod,
            String datoSoliApod, int numeroRegistro, String serieRegistro,
            int numero_publicacion) throws Exception;
    /**
     * Método que permite lista de la tabla correlativo
     *
     * @author Chano Rojas
     * @see ReciboService
     * @param correlativo
     * @param parametro
     * @return
     * @throws java.lang.Exception
     */
    
    /**
     * Método que permite lista de la tabla correlativo
     * @author Chano Rojas
     * @param procurador
     * @param listaEntrega
     * @param etapa
     * @param usuario
     * @return 
     * @see ReciboService
     * @throws java.lang.Exception
     */
    public TramiteEntrega guardaTramiteEntrega(Procurador procurador, List<TramiteEntrega> listaEntrega,String etapa, Usuario usuario) throws Exception;
    /**
     * Método que permite obtener un correlativo por id regional y tipotramite
     *
     * @author Chano Rojas
     * @see ReciboService
     * @param idRegional
     * @param tipoTramite
     * @return
     *
     */
    
    /**
     * Método que permite obtener un correlativo por id regional y tipotramite
     * @author Chano Rojas
     * @param procurador
     * @param listaEntrega
     * @param etapa
     * @param usuario
     * @throws java.lang.Exception
     * @see ReciboService
     * @return
     */
    TramiteEntrega modificaTramiteEntrega(Procurador procurador, List<TramiteEntrega> listaEntrega, String etapa, Usuario usuario) throws Exception;
    
      /**
     * Metodo para listar los tramtites por bloque
     *
     * @author Ruben Ramirez
     * @param numero
     * @param gestion
     * @param idregional


     * @see ReciboService

     * @return
     * @throws java.lang.Exception
     */
    
    List<TramiteEntrega> tramitesPorBloque(Integer numero, Integer gestion,Long idregional) throws Exception;
    
   void actualizaMarca(TramiteEntrega entregaSignos,String etapa, Usuario usuario) throws Exception;  
   
   void actualizaRenovacion(TramiteEntrega entregaSignos,String etapa, Usuario usuario) throws Exception;
           
    /**
     *
     * @param entregaSignos
     * @param etapa
     * @param usuario
     * @throws Exception
     */
    void actualizaModificacion(TramiteEntrega entregaSignos,String etapa, Usuario usuario) throws Exception;
    
    Boolean verificaTramiteEntrega(Long idtramite, String tipoTramite) throws Exception;
    
    Boolean verificaTramiteEntregaOtros(  String sigla,Integer numerotramite,Integer gestion) throws Exception;
}
