/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.solicitudes.servicio;

import java.util.HashMap;
import javax.sql.DataSource;
import snp.gob.bo.gimodel.solicitudes.entidades.FormularioPI100;

/**
 *
 * @author Eddy Valero
 * @see FormularioPI100
 * @see FormularioPI100Service
 * @version 1.0, 24/08/2016
 */
public interface FormularioPI100Service {

    /**
     * Método que permite setear la conexion con dataSource
     *
     * @param dataSource
     * @throws java.lang.Exception
     */
    public void setDataSource(DataSource dataSource) throws Exception;

    /**
     * Método que permite setear la conexion con dataSource
     *
     * @param numeroFormulario
     * @return HashMap
     * @throws java.lang.Exception
     */
    public HashMap obtenerdatosPI100(String numeroFormulario) throws Exception;

    /**
     * Método que verifica la existencia del formulario en la base de datos de
     * signos
     *
     * @param numeroFormulario
     * @return HashMap
     * @throws java.lang.Exception
     */
    public HashMap verificarIngresoTramite(String numeroFormulario) throws Exception;
    
    /**
     * Método que verifica la existencia del formulario en la base de datos de
     * signos
     *
     * Creado:Placido Castro Fecha: 24/11/2017
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
    
     HashMap verificarIngresoTramiteSignos(String numeroFormulario) throws Exception ;

    /**
     * Método que permite obtener el id y el tipo de formulario
     *
     * @param numeroFormulario
     * @return HashMap
     * @throws java.lang.Exception
     */
    public HashMap obtenerNumeroFormulario(String numeroFormulario) throws Exception;

    /**
     * Método que permite obtener el id y el tipo de formulario
     *
     * @param numeroFormulario
     * @return HashMap
     * @throws java.lang.Exception
     */
    public HashMap obtenerNumeroFormularioSubsanacion(String numeroFormulario) throws Exception;
    
    /**
     * 
     * 
     * Método que permite obtener el id y el tipo de formulario
     *
     * @param numeroFormulario
     * @return HashMap
     * @throws java.lang.Exception
     */
    HashMap obtenerNumeroFormularioSignos(String numeroFormulario) throws Exception;
    
    
    

    /**
     * Método que permite obtener la solicitud del PI100
     *
     * *************************************************************
     * Creado: Eddy Valero Fecha: 31/08/2016 Obtener datos desde la base de
     * datos de solicitudes
     * *************************************************************
     * Modificado: Eddy Valero Fecha: 28/01/2016
     * 
     * Recuperar solo los estados ACTIVOS de la solicitud PI100
     *
     * @param idFormulario
     * @return FormularioPI100
     * @throws java.lang.Exception
     *
     * -------------------------------------------------------------------------
     * Modificado: Eddy Valero Fecha: 02/02/2017
     *
     * Corregir errores al obtener datos del formularioPI102.
     *
     */
    public FormularioPI100 obtenerDatosFormularioPI100(Long idFormulario) throws Exception;

}
