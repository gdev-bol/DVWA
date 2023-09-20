package snp.gob.bo.gimodel.servicio;

import java.util.List;
import javax.sql.DataSource;
import snp.gob.bo.gimodel.entidad.SigTipoSigno;
import snp.gob.bo.gimodel.solicitudes.entidades.SmTipoSignos;

/**
 *
 * @author Eddy Valero
 * @version 1.0, 26/08/2016
 */
public interface SigTipoSignoService {

    /**
     *
     * Creado Eddy Valero Fecha: 26/08/2016
     * @param dataSource
     */
    public void setDataSource(DataSource dataSource);
    
    /**
     * Método crud de la tabla SigTipoSigno
     * 
     * Creado Eddy Valero Fecha: 10/10/2016
     * @param sigTipoSigno
     * @param parametro
     * @return SigTipoSigno
     * @throws java.lang.Exception
     */
    
    public SigTipoSigno crudSigTipoSigno(SigTipoSigno sigTipoSigno, int parametro) throws Exception;

    /**
     * Método 
     * 
     * Creado Eddy Valero Fecha: 26/08/2016
     * @param sigTipoSigno
     * @return 
     * @throws java.lang.Exception
     */
    public SigTipoSigno registrarSigTipoSigno(SigTipoSigno sigTipoSigno) throws Exception;

    /**
     * Metodo de busca lista de objetos SigTipoSigno por idsignomarca Susana
     * Escobar Paz Fecha: 14/09/2016
     *
     * @param idsignoMarca
     * @return
     */
    public List<SigTipoSigno> listaSigTipoSignoXidSIgnoMarca(Long idsignoMarca);
    
    
    /**
     * Método que permite realizar la insercion de un listado de SigTipoSigno
     * 
     * Creado: Eddy Valero Fecha: 01/10/2016
     * @param listaTipoSigno
     * @param idLogTrans
     * @param idSignoMarca
     * @param parametro
     * @throws java.lang.Exception
     */
    public void guardarListaSigTipoSigno(List<SigTipoSigno> listaTipoSigno, Long idLogTrans, Long idSignoMarca, int parametro ) throws Exception;
    
    
    /**
     * Método que permite realizar un crud a la tabla rentiposigno
     * 
     * Creado: Eddy Valero Fecha: 01/10/2016
     * @param sigTipoSigno
     * @param parametro
     * @return SigTipoSigno
     */
     public SigTipoSigno guardarListarModificarSigTipoSigno(SigTipoSigno sigTipoSigno, int parametro);
     
     
     /**
     * Método que permite realizar un crud a la tabla rentiposigno
     * 
     * Creado: Eddy Valero Fecha: 01/10/2016
     * @param idSignoMarca
     * @throws java.lang.Exception
     */
     public void eliminarListaSigTipoSignoXIdSignoMarca(Long idSignoMarca) throws Exception;
     
     
     /**
     * Método que permite eliminar un registro de sigTipoSigno
     * 
     * Creado: Eddy Valero Fecha: 01/10/2016
     * @param sigTipoSigno
     * @param parametro
     * @throws java.lang.Exception
     */
     public void eliminarSigTipoSigno(SigTipoSigno sigTipoSigno, int parametro) throws Exception;
     
     
     /**
     * Método que permite modificar una lista de SigTipoSigno
     * 
     * Creado: Eddy Valero Fecha: 10/10/2016
     * @param idSignoMarca
     * @param lstSigTipoSigno
     * @param idLogTrans
     * @throws java.lang.Exception
     */
     
     public void modificarSigTipoSigno(Long idSignoMarca, List<SigTipoSigno> lstSigTipoSigno, Long idLogTrans) throws Exception;
         
     /**
     * Método que permite modificar una lista de SigTipoSigno
     * 
     * Creado:Placido Castro Fecha: 24/11/2017
     * @param idSignoMarca
     * @param lstSigTipoSigno
     * @param listaTipoSignoSipi
     * @param idLogTrans
     * @throws java.lang.Exception
     */
     
     public void modificarSigTipoSignoSubsanacion(Long idSignoMarca, List<SigTipoSigno> lstSigTipoSigno, List<SmTipoSignos> listaTipoSignoSipi, Long idLogTrans) throws Exception;
          
    /**
     * Método crear texto concatenado con valores de busqueda 
     * por idsignomarca en tabla sigtiposigno
     * Se modifico el metodo para obtener el listado de tiposigno concatenado de Signos, Modificaciones y Renovacion.
     * 
     * Creado Susana Escobar Paz Fecha: 04/11/2016
     * Modificado: Susana Escobar Paz Fecha: 26/01/2017
     * @param prefijo
     * @param idSignoMarca
     * @return SigTipoSigno
     */
     public String lista_SigTipoSigno_concatenado(String prefijo, Long idSignoMarca);
     
     
     /**
     * Método obtener tiposigno y descripcion otro
     * 
     * Creado: Eddy Valero Fecha: 04/11/2016
     * @param idSignoMarca
     * @return SigTipoSigno
     * @throws java.lang.Exception
     */
     public String obtenerListaSigTipoSigno(Long idSignoMarca) throws Exception;
     
    /**
     *
     * @param idSignoMarca
     * @return
     */
    public Boolean preguntaEsDenominativo(Long idSignoMarca);     
}
