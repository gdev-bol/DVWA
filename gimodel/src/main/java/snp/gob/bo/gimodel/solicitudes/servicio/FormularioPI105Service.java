/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.solicitudes.servicio;

import javax.sql.DataSource;
import snp.gob.bo.gimodel.solicitudes.entidades.FormularioPI105;



/**
 *
 * @author luis_angel
 */
public interface FormularioPI105Service {
     /**
     * Método que permite setear la conexion con dataSource
     *
     * @param dataSource
     * @throws java.lang.Exception
     */
    public void setDataSource(DataSource dataSource) throws Exception;
     /**
     * Método que permite obtener la solicitud del PI100
     *
     * *************************************************************
     * Creado: Luis Angel Quispe Fecha: 12/07/2017 Obtener datos desde la base de
     * datos de solicitudes
     * *************************************************************
    
     * @param idFormulario
     * @return FormularioPI105
     * @throws java.lang.Exception
     *
     */
    public FormularioPI105 obtenerDatosFormularioPI105(Long idFormulario) throws Exception;
    
    
}
