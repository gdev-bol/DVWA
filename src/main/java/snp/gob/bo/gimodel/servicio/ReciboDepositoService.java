/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package snp.gob.bo.gimodel.servicio;

import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import snp.gob.bo.gimodel.entidad.Deposito;
import snp.gob.bo.gimodel.entidad.Recibo;
import snp.gob.bo.gimodel.entidad.ReciboDeposito;
import snp.gob.bo.gimodel.mapper.ReciboMapper;


/**
 *
 * @author Chano Rojas
 * @see ReciboService
 * @see  ReciboService
 * @version 1.0, 05/06/2016
 */

public interface ReciboDepositoService {
    
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
     * @param dataSource
     * @throws java.lang.Exception
     */
    
    /**
     * Método que permite setear la conexion con dataSource
     * @param reciboDeposito
     * @param parametro
     * @return 
     * @throws java.lang.Exception
     */
   ReciboDeposito crudReciboDeposito(ReciboDeposito reciboDeposito, int parametro) throws Exception;
    
    /**
     * Método que permite listar la tabla Dosificacion
     *
     * @param reciboDeposito
     * @param parametro
     * @return 
     * @throws java.lang.Exception
     */
    List<ReciboDeposito> lstReciboDeposito(ReciboDeposito reciboDeposito, int parametro) throws Exception;
    
    /**
     * Método que permite listar la tabla Dosificacion
     *
     * @param idRecibo
     * @return 
     * @throws java.lang.Exception
     */
    
   List<ReciboDeposito>lstReciboDepositoPorIdRecibo(Long idRecibo)throws Exception;
    
    /**
     * Método que permite listar la tabla Dosificacion
     *
     * @param idReciboDeposito
     * @return 
     * @throws java.lang.Exception
     */
   
   ReciboDeposito reciboDepositoPorIdReciboDeposito(Long idReciboDeposito) throws Exception;
    
    
    
    
    
    
    
    
    
}
