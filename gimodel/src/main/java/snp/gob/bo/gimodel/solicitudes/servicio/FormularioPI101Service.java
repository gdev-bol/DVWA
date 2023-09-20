/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.solicitudes.servicio;

import javax.sql.DataSource;
import snp.gob.bo.gimodel.solicitudes.entidades.FormularioPI101;

/**
 *
 * @author Eddy Valero
 * @see FormularioPI101
 * @see FormularioPI101ServiceImpl
 * @version 1.0, 30/08/2016
 */
public interface FormularioPI101Service {

    /**
     * Método que permite setear la conexion con dataSource
     *
     * @param dataSource
     * @throws java.lang.Exception
     */
    public void setDataSource(DataSource dataSource) throws Exception;

    /**
     * Método que permite obtener la solicitud del PI101
     *
     * *************************************************************
     * Creado: Eddy Valero Fecha: 31/08/2016 Obtener datos desde la base de
     * datos de solicitudes
     * 
     * *************************************************************************
     * Modificado: Eddy Valero Fecha: 28/01/2016
     * 
     * Recuperar solo los estados ACTIVOS de la solicitud PI101
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
    public FormularioPI101 obtenerDatosFormularioPI101(Long idFormulario) throws Exception;

}
