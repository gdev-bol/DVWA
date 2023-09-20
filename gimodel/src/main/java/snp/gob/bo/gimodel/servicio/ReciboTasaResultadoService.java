/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.servicio;

import java.util.List;
import javax.sql.DataSource;
import snp.gob.bo.gimodel.entidad.ReciboTasaResultado;

/**
 *
 * @author Ruben Ramirez
 * @see ReciboResultadoServiceImpl
 * @version 1.0, 19/12/2016
 */
public interface ReciboTasaResultadoService {

    /**
     * Método que permite setear la conexion con dataSource
     *
     * @param dataSource
     * @throws java.lang.Exception
     */
    public void setDataSource(DataSource dataSource) throws Exception;
    
     /**
     * Método que permite realizar el listado de recibo agrupado por fecha de acuerod a la tasa, mes y gestion
     *
     * @author Ruben Ramirez
     * @param id
     * @param mes
     * @param gestion
     * @return
     * @throws java.lang.Exception
     */
    public List<ReciboTasaResultado> listaReciboResultadoFecha(Long id, int mes , int gestion) throws Exception; 

}
