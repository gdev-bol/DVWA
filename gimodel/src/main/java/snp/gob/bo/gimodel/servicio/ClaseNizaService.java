/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.servicio;

import java.util.List;
import javax.sql.DataSource;
import snp.gob.bo.gimodel.entidad.ClaseNiza;

/**
 *
 * @author Eddy Valero
 * @version 1.0, 26/08/2016
 */
public interface ClaseNizaService {
    
    /**
     * Creado: Eddy Valero Fecha: 26/08/2016
     *
     * @param dataSource
     */
    public void setDataSource(DataSource dataSource);

    /**
     * Creado: Eddy Valero Fecha: 26/08/2016
     *
     * @param numeroClaseNiza
     * @return
     * @throws java.lang.Exception
     */
    public ClaseNiza obtenerRegistroClaseNiza(Long numeroClaseNiza) throws Exception;
    
    
    /*
     * Método para obtener todos los registros de la Clase Niza 
     * 
     * Creado: Eddy Valero Fecha: 04/10/2016
     * @throws java.lang.Exception
     */
    public List<ClaseNiza> obtenerListadoClaseNiza() throws Exception;

    /**
     *
     * @param version
     * @return
     * @throws Exception
     */
    public List<ClaseNiza> obtenerListadoClaseNizaVersion(String version) throws Exception; 
    public ClaseNiza listarClaseNiza_id(Long id);
    public List<String> lista_version_ClaseNiza();
    
    
}