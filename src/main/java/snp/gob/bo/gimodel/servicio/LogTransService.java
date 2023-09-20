/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package snp.gob.bo.gimodel.servicio;

import javax.sql.DataSource;
import snp.gob.bo.gimodel.entidad.LogTrans;

/**
 *
 * @author Eddy Valero
 * @see Expediente
 * @see ExpedienteService
 * @version 1.0, 22/11/2016
 */

public interface LogTransService {
    
    /**
     * Método que permite setear la conexion con dataSource
     *
     * @param dataSource
     * @throws java.lang.Exception
     */
    public void setDataSource(DataSource dataSource) throws Exception;
    
    /**
     * Método crud LogTrans
     *
     * Creado:Eddy Valero Fecha: 22/11/2016
     * 
     * @param logTrans
     * @param parametro
     * @return SigSignoMarca
     * @throws java.lang.Exception
     */
    public LogTrans crudLogTrans(LogTrans logTrans, Integer parametro) throws Exception;
    
     
}
