package snp.gob.bo.gimodel.solicitudes.servicio;

import javax.sql.DataSource;
import snp.gob.bo.gimodel.solicitudes.entidades.FormularioPI104;

/**
 *
 * @author Eddy Valero
 * @see FormularioPI104
 * @see FormularioPI104ServiceImpl
 * @version 1.0, 26/10/2016
 */
public interface FormularioPI104Service {

    /**
     * Método que permite setear la conexion con dataSource
     *
     * Creado: Eddy Valero Fecha: 26/10/2016
     *
     * @param dataSource
     * @throws java.lang.Exception
     */
    public void setDataSource(DataSource dataSource) throws Exception;

    /**
     * Método que permite obtener la solicitud del PI104
     *
     * Creado:Eddy Valero Fecha:26/10/2016
     *
     * @param idFormulario
     * @return FormularioPI104
     * @throws java.lang.Exception
     *
     * -------------------------------------------------------------------------
     * Modificado: Eddy Valero Fecha: 02/02/2017
     *
     * Corregir errores al obtener datos del formularioPI104.
     *
     * -------------------------------------------------------------------------
     * Modificado: Eddy Valero Fecha: 08/02/2017
     *
     * Se modifica para que el campo numeroultimarenovacion y numeropenultimarenovacion
     * de la tabla origen formulario.solicitudrenovaciones guarde los valores nulo, en
     * el caso que los valores sean diferentes de un formato numerico es decir: 125-A o 
     * SM-15263-C
     *
     */
    public FormularioPI104 obtenerDatosFormularioPI104(Long idFormulario) throws Exception;

}
