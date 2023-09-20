
package snp.gob.bo.gimodel.solicitudes.servicio;

import javax.sql.DataSource;
import snp.gob.bo.gimodel.solicitudes.entidades.FormularioPI103;

/**
 *
 * @author Eddy Valero
 * @see FormularioPI103
 * @see FormularioPI103ServiceImpl
 * @version 1.0, 24/10/2016
 */

public interface FormularioPI103Service {
    
    /**
     * Método que permite setear la conexion con dataSource
     *
     * Creado: Eddy Valero Fecha: 24/10/2016
     * @param dataSource
     * @throws java.lang.Exception
     */
    public void setDataSource(DataSource dataSource) throws Exception;
    
    /**
     * Método que permite obtener la solicitud del PI103
     *
     * Creado:Eddy Valero Fecha:24/10/2016
     * 
     * @param idFormulario
     * @return FormularioPI103
     * @throws java.lang.Exception
     * 
     * -------------------------------------------------------------------------
     * Modificado: Eddy Valero Fecha: 02/02/2017
     *
     * Corregir errores al obtener datos del formularioPI103.
     *
     */
    public FormularioPI103 obtenerDatosFormularioPI103(Long idFormulario) throws Exception;
    
    
    
    
}
