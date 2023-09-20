/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.servicio;

import java.util.List;
import javax.sql.DataSource;
import snp.gob.bo.gimodel.entidad.UsuarioEtapa;

/**
 *
 * @author Ruben Ramirez
 * @see UsuarioEtapaServiceImpl
 * @version 1.0, 26/10/2016
 */
public interface UsuarioEtapaService {

    /**
     * Método que permite setear la conexion con dataSource
     *
     * Creado: Ruben Ramirez Fecha: 26/10/2016
     *
     * @param dataSource
     * @throws java.lang.Exception
     */
    public void setDataSource(DataSource dataSource) throws Exception;
    
    
    /**
     * Método que permite setear la conexion con dataSource
     *
     * Creado: Eddy Valero Fecha: 21/12/2016
     *
     * @param usuarioEtapa
     * @param parametro
     * @return UsuarioEtapa
     * @throws java.lang.Exception
     */
    public UsuarioEtapa crudUsuarioEtapa(UsuarioEtapa usuarioEtapa, int parametro) throws Exception;
 /**
     * Método que pLista objeto usuarioetapa dado el idusuario
     *
     * Creado: Eddy Valero Fecha: 21/12/2016
     *
     * @param idUsuario
     * @return UsuarioEtapa
     * @throws java.lang.Exception
     */
    public List<UsuarioEtapa> listaUsuarioEtapaXIdUsuario(Long idUsuario)throws Exception;
    
    
    /**
     * Método que modiifca el registro usuario etapa 
     *
     * Creado: Levi Laura: 21/12/2016
     *
     * @param idUsuario
     * @param idETapa
     * @return UsuarioEtapa
     * @throws java.lang.Exception
     */
    public void modificaEstadoUsuario(Long idusuario,Long idetapa) throws Exception ;
        
    
    
    
    
    
}
