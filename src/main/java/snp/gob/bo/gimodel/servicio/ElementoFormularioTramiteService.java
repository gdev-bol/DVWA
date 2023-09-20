/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package snp.gob.bo.gimodel.servicio;

import java.util.List;
import javax.sql.DataSource;
import snp.gob.bo.gimodel.entidad.DatoElementoFormulario;
import snp.gob.bo.gimodel.entidad.ElementoFormularioTramite;

/**
 *
 * @author Eddy Valero
 * @see ElementoFormularioTramite
 * @see ElementoFormularioTramiteServiceImpl
 * @version 1.0, 06/09/2016
 */

public interface ElementoFormularioTramiteService {
    
    /**
     * Método que permite setear la conexion con dataSource
     *
     * @param dataSource
     * @throws java.lang.Exception
     */
    public void setDataSource(DataSource dataSource) throws Exception;
    
    /**
     * Método que permite obtener toda la plantilla de ventanilla de un determinado tramite
     *
     * @param codigo
     * @return List<ElementoFormularioTramite>
     * @throws java.lang.Exception
     */
    public List<ElementoFormularioTramite> obtPlantillaVentanilla(String codigo) throws Exception;
    
    
    
}
