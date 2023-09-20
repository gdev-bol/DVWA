/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.servicio;

import java.util.Date;
import java.util.List;
import javax.sql.DataSource;
import snp.gob.bo.gimodel.entidad.ReciboAgrupacion;
import snp.gob.bo.gimodel.entidad.ReciboPorPeriodo;

/**
 *
 * @author Ruben Ramirez
 * @see ReciboAgrupacionServiceImpl
 * @version 1.0, 22/12/2016
 */
public interface ReciboAgrupacionService {

    /**
     * Método que permite setear la conexion con dataSource
     *
     * @author Ruben Ramirez
     * @param dataSource
     * @throws java.lang.Exception
     */
    public void setDataSource(DataSource dataSource) throws Exception;

    /**
     * Método que permite realizar el listado de recibo agrupado por tasa de
     * acuerdo a un mes y gestion especifico.
     *
     * @author Ruben Ramirez
     * @param gestion
     * @param mes
     * @return
     * @throws java.lang.Exception
     */
    public List<ReciboAgrupacion> listaReciboAgrupacionMes(int gestion, int mes) throws Exception;
    
    /**
     * Método que permite realizar el listado de recibo agrupado por tasa de
     * acuerdo a un mes y gestion especifico e id usuario. 
     *
     * @author Ruben Ramirez
     * @param gestion
     * @param mes
     * @param idUsuario
     * @return
     * @throws java.lang.Exception
     */
    public List<ReciboAgrupacion> listaReciboAgrupacionMesPorIdUsuario(int gestion, int mes,Long idUsuario) throws Exception;
    
    /**
     * Método que permite realizar el listado de recibo agrupado por tasa de
     * acuerdo a un mes y gestion especifico e id regional.
     *
     * @author Ruben Ramirez
     * @param gestion
     * @param mes
     * @param idRegional
     * @return
     * @throws java.lang.Exception
     */
    public List<ReciboAgrupacion> listaReciboAgrupacionMesPorIdRegional(int gestion, int mes, Long idRegional) throws Exception;

    /**
     * Método que permite realizar el listado de recibo agrupado por tasa de
     * acuerdo a una gestion especifica.
     *
     * @author Ruben Ramirez
     * @param gestion
     * @return
     * @throws java.lang.Exception
     */
    public List<ReciboAgrupacion> listaReciboAgrupacionGestion(int gestion) throws Exception;
    
    /**
     * Método que permite realizar el listado de recibo agrupado por tasa de
     * acuerdo a una gestion especifica e id usuario. 
     *
     * @author Ruben Ramirez
     * @param gestion
     * @param idUsuario
     * @return
     * @throws java.lang.Exception
     */
    public List<ReciboAgrupacion> listaReciboAgrupacionGestionPorIdUsuario(int gestion,Long idUsuario) throws Exception;
    
    /**
     * Método que permite realizar el listado de recibo agrupado por tasa de
     * acuerdo a una gestion especifica e id regional.
     *
     * @author Ruben Ramirez
     * @param gestion
     * @param idRegional
     * @return
     * @throws java.lang.Exception
     */
    public List<ReciboAgrupacion> listaReciboAgrupacionGestionPorIdRegional(int gestion, Long idRegional) throws Exception;
    
     /**
     * Método que permite realizar el listado de recibo agrupado por tasa de
     * acuerdo a una gestion especifica e id regional.
     *
     * @author Ruben Ramirez
     * @param gestion
     * @param idRegional
     * @return
     * @throws java.lang.Exception
     */
    public List<ReciboPorPeriodo> listaReciboPorPeriodo(Date fechaIni, Date fechaFin, String criterio, Long id) throws Exception;

}
