/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package snp.gob.bo.gimodel.servicio;

import java.util.List;
import javax.sql.DataSource;
import snp.gob.bo.gimodel.entidad.DatoElementoFormulario;

/**
 *
 * @author Eddy Valero
 * @see DatoElementoFormulario
 * @see DatoElementoFormularioServiceImpl
 * @version 1.0, 05/09/2016
 */

public interface DatoElementoFormularioService {
    
    /**
     * Método que permite setear la conexion con dataSource
     *
     * @param dataSource
     * @throws java.lang.Exception
     */
    public void setDataSource(DataSource dataSource) throws Exception;
    
    /**
     * Método que verifica si existen registros de ventanilla asociados al metodo
     *
     * @param idSeguimiento
     * @param idFormulario
     * @return List<DatoElementoFormulario>
     * @throws java.lang.Exception
     */
    public int generarRegistrosPlantillaVentanilla(Long idSeguimiento, Long idFormulario) throws Exception;
    
    /**
     * Método que permite setear la conexion con dataSource
     *
     * @param idSeguimiento
     * @param nombreTabla
     * @return List<DatoElementoFormulario>
     * @throws java.lang.Exception
     */
//    public List<DatoElementoFormulario> obtplantillaventanillaseguimiento(Long idSeguimiento, String nombreTabla) throws Exception;
    
    /**
     * Modificado: Susana Escobar Paz  Fecha: 11/01/2017
     * Método que permite actualizar datos de registros de las plantillas generadas al ingreso por ventanilla, para solicitudes de Signos, Renovaciones y Modificaciones
     *
     * @param listaPlantilla
     * @param prefijoTabla
     * @throws java.lang.Exception
     */
    public void actualizarRegistrosPlantillaVentanilla(List<DatoElementoFormulario> listaPlantilla, String prefijoTabla) throws Exception;
    
//    public boolean actPlantillaRequerimientos(List<SMDatoElementoFormulario> listaPlantilla) throws Exception;
    
    
    public void guardarRegistrosPlantillaVentanilla(List<DatoElementoFormulario> listaPlantilla, String prefijo) throws Exception;
    
    /**
     * Método que permite setear la conexion con dataSource
     *
     * @param numeroFormulario
     * @param tipoFormulario
     * @return List<DatoElementoFormulario> listado de todos los datosde datoelementoformulario
     * @throws java.lang.Exception
     * ------------------------------------------------------------------------
     * Creado: Eddy Valero Fecha: 19/09/2016
     * Obtener el listado de datotramiteventanillaeforma ingresado.
     */
    public List<DatoElementoFormulario> obtplantillaventanillatramiteingresado(String numeroFormulario, String tipoFormulario) throws Exception;
    
    
}
