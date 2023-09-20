/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.solicitudes.servicio;

import javax.sql.DataSource;
import snp.gob.bo.gimodel.solicitudes.entidades.FormularioPI102;

/**
 *
 * @author Eddy Valero
 * @see FormularioPI102
 * @see FormularioPI102ServiceImpl
 * @version 1.0, 02/08/2016
 */
public interface FormularioPI102Service {

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
     * @param idFormulario
     * @return FormularioPI100
     * @throws java.lang.Exception
     *
     * *************************************************************************
     * Creado: Eddy Valero Fecha: 02/08/2016
     *
     * Obtener datos del formulario 102, desde la base de datos de solicitudes
     *
     * -------------------------------------------------------------------------
     * Modificado: Eddy Valero Fecha: 02/02/2017
     *
     * Corregir errores al obtener datos del formularioPI102.
     *
     */
    public FormularioPI102 obtenerDatosFormularioPI102(Long idFormulario) throws Exception;

}
