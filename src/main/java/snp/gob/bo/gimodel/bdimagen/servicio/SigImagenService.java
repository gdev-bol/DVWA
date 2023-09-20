
package snp.gob.bo.gimodel.bdimagen.servicio;

import java.util.List;
import javax.sql.DataSource;
import snp.gob.bo.gimodel.bdimagen.entidad.SigImagen;

/**
 *
 * @author Eddy Valero
 * @version 1.0, 27/08/2016
 */
public interface SigImagenService {
    
    /**
     *
     * Creado: Eddy Valero Fecha: 27/08/2016
     * @param dataSource
     */
    public void setDataSource(DataSource dataSource);
    
    /**s
     *
     * Creado: Eddy Valero Fecha: 27/08/2016
     * 
     * @param sigImagen
     * @return 
     * @throws java.lang.Exception
     */
    public SigImagen registrarSigImagen(SigImagen sigImagen) throws Exception;
    
    
    /**
     * Método para obtener las imagenes asociadas a un logotipo
     * 
     * Creado: Eddy Valero Fecha: 27/09/2016
     * 
     * @param idSigLogoTipo
     * @return List<SigImagen>
     * @throws java.lang.Exception
     */
    public List<SigImagen> obtenerListaSigImagenXIdSigLogoTipo(Long idSigLogoTipo) throws Exception;
    
    /**
     * Método para obtener la imagen asociada a un logotipo
     * 
     * Creado: Eddy Valero Fecha: 27/09/2016
     * 
     * @param idSigLogoTipo
     * @return SigImagen
     * @throws java.lang.Exception
     */
    public SigImagen obtenerSigImagenXIdSigLogoTipo(Long idSigLogoTipo) throws Exception;
    
    
    /**
     * Método para realizar el registro o modificación de SigImagen
     * 
     * Creado: Eddy Valero Fecha: 11/10/2016
     * 
     * @param sigImagen
     * @param operacion
     * @return SigImagen
     * @throws java.lang.Exception
     */
    public SigImagen guardar_modificar_listar_SigImagen(SigImagen sigImagen, Integer operacion) throws Exception;
    
    
    
    
    
}
