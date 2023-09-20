/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.servicio;

import java.util.Date;
import java.util.List;
import javax.sql.DataSource;
import snp.gob.bo.gimodel.entidad.ReciboResultado;

/**
 *
 * @author Ruben Ramirez
 * @see ReciboResultadoServiceImpl
 * @version 1.0, 19/12/2016
 */
public interface ReciboResultadoService {

    /**
     * Método que permite setear la conexion con dataSource
     *
     * @param dataSource
     * @throws java.lang.Exception
     */
    public void setDataSource(DataSource dataSource) throws Exception;

    /**
     * Método que permite realizar el listado de recibo agrupado por tasa de acuerdo a un mes y gestion especifico
     
     * @author Ruben Ramirez
     * @param gestion
     * @param mes
     * @return
     * @throws java.lang.Exception
     */
    public List<String> listaReciboResultadoMes(int gestion,int mes) throws Exception;  
    
    /**
     * Método que permite realizar el listado de recibo agrupado por tasa de acuerdo a un mes y gestion especifico
     
     * @author Ruben Ramirez
     * @param gestion
     * @param mes
     * @param idUsuario
     * @return
     * @throws java.lang.Exception
     */
    public List<String> listaReciboResultadoMesPorIdUsuario(int gestion,int mes,Long idUsuario) throws Exception;  
    
    /**
     * Método que permite realizar el listado de recibo agrupado por tasa de acuerdo a un mes y gestion especifico
     
     * @author Ruben Ramirez
     * @param gestion
     * @param mes
     * @param idRegional
     * @return
     * @throws java.lang.Exception
     */
    public List<String> listaReciboResultadoMesPorIdRegional(int gestion,int mes,Long idRegional) throws Exception;  
    
     /**
     * Método que permite realizar el listado de recibo agrupado por tasa de acuerdo a un mes y gestion especifico
     *
     * @author Ruben Ramirez
     * @param fecha
     * @return
     * @throws java.lang.Exception
     */
    public List<ReciboResultado> listaReciboResultadoFecha(String fecha) throws Exception; 
    
    /**
     * Método que permite realizar el listado de recibo agrupado por tasa de acuerdo a un mes y gestion especifico
     *
     * @author Ruben Ramirez
     * @param fecha
     * @param idUsuario
     * @return
     * @throws java.lang.Exception
     */
    public List<ReciboResultado> listaReciboResultadoFechaIdUsuario(String fecha,Long idUsuario) throws Exception; 
    
    /**
     * Método que permite realizar el listado de recibo agrupado por tasa de acuerdo a un mes y gestion especifico
     *
     * @author Ruben Ramirez
     * @param fecha
     * @param idRegional
     * @return
     * @throws java.lang.Exception
     */
    public List<ReciboResultado> listaReciboResultadoFechaPorIdRegional(String fecha,Long idRegional) throws Exception; 

}
