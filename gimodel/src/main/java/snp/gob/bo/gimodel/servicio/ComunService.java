package snp.gob.bo.gimodel.servicio;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.util.Date;
import java.util.HashMap;
import javax.sql.DataSource;

/**
 *
 * @author Eddy Valero
 * @see ComunServiceImpl
 * @version 1.0, 16/09/2016
 */

public interface ComunService {
    
    /**
     * Método que permite setear la conexion con dataSource
     * -------------------------------------------------------------------------
     * Creado: Eddy Valero Fecha: 16/09/2016
     * 
     * @param dataSource
     * @throws java.lang.Exception
     */
    public void setDataSource(DataSource dataSource) throws Exception;

    
    /**
     * Método que permite obtener la fecha del Servidor
     * -------------------------------------------------------------------------
     * Creado: Eddy Valero Fecha: 16/09/2016
     * 
     * @param idRegional
     * @return Date
     * @throws java.lang.Exception
     */
    public Date obtenerFechaServidor(Long idRegional) throws Exception;
    
    
    /**
     * Método que permite obtener la fecha y hora del Servidor
     * -------------------------------------------------------------------------
     * Creado: Eddy Valero Fecha: 16/09/2016
     * 
     * @param idRegional
     * @return Date
     * @throws java.lang.Exception
     */
    public Date obtenerFechaHoraServidor(Long idRegional) throws Exception;
    
    
    
    /**
     * Método que permite codificar un SM, a partir del numero, gestion y 
     * extensión
     * -------------------------------------------------------------------------
     * Creado: Eddy Valero Fecha: 20/09/2016
     * 
     * @param numero
     * @param gestion
     * @param extension
     * @return Long
     * @throws java.lang.Exception
     */
    public Long codificarCodigoSM(String numero, String gestion, String extension) throws Exception;
    
    
    /**
     * Método que permite codificar un SM, a partir del numero, gestion y 
     * extensión
     * -------------------------------------------------------------------------
     * Creado: Eddy Valero Fecha: 20/09/2016
     * 
     * @param codigoSM
     * @return Long
     * @throws java.lang.Exception
     */
    public HashMap obtenerNumeroGestionExtensionCodigoSM(Long codigoSM) throws Exception;
    
    
    /**
     * Método que permite obtener el numero gestion y la extension (formato numerico),
     * a partir de un codigo SM ingresado
     * -------------------------------------------------------------------------
     * Creado: Eddy Valero Fecha: 27/12/2016
     * 
     * @param codigoSM
     * @return Long
     * @throws java.lang.Exception
     */
    public HashMap obtenerNumeroGestionExtensionNumerico(Long codigoSM) throws Exception;
    
    
}
