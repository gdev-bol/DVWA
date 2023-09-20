/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package snp.gob.bo.gimodel.servicio;

import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import snp.gob.bo.gimodel.entidad.ObservacionTramite;


/**
 *
 * @author Eddy Valero
 * @see SMDominio
 * @see SMDominioService
 * @version 1.0, 05/06/2016
 */

public interface SMDominioService {
    
    /**
     * Método que permite setear la conexion con dataSource
     *
     * @param dataSource
     * @throws java.lang.Exception
     */
    public void setDataSource(DataSource dataSource) throws Exception;
    
//    public int registrarSMDominio(SMDominio sMDominio);
//    
//    public SMDominio registrarDominio(SMDominio sMDominio) throws Exception;
//    
//    //Obtener el listado de todas los dominios
//    public List<SMDominio> obtenerListaDominios();
    
    
    
    
    
  //  public List<SMDominio> obtenerListadoDominio(String dominio) throws Exception;
    
  //     public List<SMDominio> obtenerListadopaices() throws Exception;
       
}
