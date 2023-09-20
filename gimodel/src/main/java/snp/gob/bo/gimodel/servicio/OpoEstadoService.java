/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package snp.gob.bo.gimodel.servicio;

import javax.sql.DataSource;

/**
 *
 * @author Luis Angel Quispe Limachi
 */
public interface OpoEstadoService {
    /**
     * Método que permite setear la conexion con dataSource
     *
     * @param dataSource
     * @throws java.lang.Exception
     */
     public void setDataSource(DataSource dataSource)throws Exception;
        /**
     * Metodo para mostrar la descripcion de la actividad 
     * creado: Luis Quispe Fecha: 05/08/2016
     *
     * @param idesta
     * @return String
     * @throws java.lang.Exception
     */
     public String devuelvenombredelestadoxidestado(Long idesta)throws Exception;
}
