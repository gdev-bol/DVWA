/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package snp.gob.bo.gimodel.servicio;

import java.util.List;
import javax.sql.DataSource;
import snp.gob.bo.gimodel.entidad.OpoHistorial;

/**
 *
 * @author Luis Angel Quispe Limachi
 */
public interface OpoHistorialService {
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
     * @param opohistorial
     * @return OpoHistorial
     * @throws java.lang.Exception
     */
     public OpoHistorial guardaOpoHistorial(OpoHistorial opohistorial) throws Exception;
         /**
     * Metodo listar el historial por el numero de oposicion 
     * creado: Luis Quispe Fecha: 07/11/2016
     *
     * @param nroopo
     * @return OpoHistorial
     * @throws java.lang.Exception
     */
     public List<OpoHistorial> listarhistorialxnroopo(Long nroopo) throws Exception;
     
      
     /**
     * Metodo listar el historial por el numero de oposicion 
     * creado: Luis Quispe Fecha: 22/03/2017
     *
     * @param idestudiante
     * @return String
     * @throws java.lang.Exception
     */
     public String mostrarusuarioxid(Long idestudiante) throws Exception;
     
     
}
