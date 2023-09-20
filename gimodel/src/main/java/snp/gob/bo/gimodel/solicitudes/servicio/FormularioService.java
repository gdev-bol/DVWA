/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package snp.gob.bo.gimodel.solicitudes.servicio;

import java.util.HashMap;
import javax.sql.DataSource;
import snp.gob.bo.gimodel.solicitudes.entidades.ClasesNiza;
import snp.gob.bo.gimodel.solicitudes.entidades.FormularioPI100;
import snp.gob.bo.gimodel.solicitudes.entidades.SmSignoClaseNizas;

/**
 *
 * @author Eddy Valero
 * @see FormularioPI100
 * @see FormularioPI100Service
 * @version 1.0, 15/09/2016
 */

public interface FormularioService {
    
    /**
     * Método que permite setear la conexion con dataSource
     * -------------------------------------------------------------------------
     * Creado: Eddy Valero Fecha: 15/09/2016
     * @param dataSource
     * @throws java.lang.Exception
     */
    public void setDataSource(DataSource dataSource) throws Exception;
    
    /**
     * Método que permite actualizar el estado del formulario
     *
     * @param idFormulario
     * @throws java.lang.Exception
     */
    public void actualizarRegistroFormulario(Long idFormulario) throws Exception;
    
    /**
     * Método que permite actualizar el estado del formulario
     *
     * @param idFormulario
     * @throws java.lang.Exception
     */
    public void actualizarRegistroFormularioSubsanacion(Long idFormulario) throws Exception;
    
    /**
    * Método que permite actualizar el estado del formulario
    *
    *  Creado:Placido Castro Fecha: 24/11/2017
    * 
    * @param idFormulario
    *  @param subsanacion
    * @throws java.lang.Exception
    */
   public void habilitarFormularioSubsanacion(Long idFormulario, String subsanacion) throws Exception;   
   
    /**
    * Método obtener tiposigno y descripcion otro
    * 
    * Creado: Placido Castro Fecha: 10/10/2017
    * @param idSignoMarca
    * @return SigTipoSigno
    * @throws java.lang.Exception
    */
    public String obtenerListaSigTipoSignoExt(Long idSignoMarca) throws Exception;    
    
    /**
     * Método que permite obtener el id y el tipo de formulario
     *
     * @param numeroFormularioSub
     * @param numeroFormularioAct
     * @return HashMap
     * @throws java.lang.Exception
     */
    public HashMap obtenerNumeroFormularioSubsanacion(String numeroFormularioSub, String numeroFormularioAct) throws Exception;    
    
    /**
     * Método que verifica la existencia del formulario en la base de datos de
     * signos
     *
     * @param numeroFormulario
     * @return HashMap
     * @throws java.lang.Exception
     */
    public HashMap verificarIngresoTramiteSubsanacion(String numeroFormulario) throws Exception;
    
    /**
     * Método que verifica la existencia del formulario en la base de datos de
     * signos
     *
     * @param numeroFormulario
     * @return HashMap
     * @throws java.lang.Exception
     */
    public boolean existeSubsanacionSignoMarca(String numeroFormulario) throws Exception;
    
    /**
     * Método que devuelve la clase niza del formulario externo de la base de datos
     *
     * @param idClaseNiza
     * @return SmSignoClaseNizas
     * @throws java.lang.Exception
     */
    public ClasesNiza obtenerClaseNiza(Long idClaseNiza) throws Exception;    
    
}
