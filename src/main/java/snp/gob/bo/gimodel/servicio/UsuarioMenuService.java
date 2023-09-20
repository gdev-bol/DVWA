/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.servicio;

import javax.sql.DataSource;
import snp.gob.bo.gimodel.entidad.UsuarioMenu;

/**
 *
 * @author Levi Laura
 * @see UsuarioMenuServiceImpl
 * @version 1.0, 08/09/2016
 */
public interface UsuarioMenuService {
    
    /**
     * Método que permite setear la conexion con dataSource
     *
     * Creado: Levi Laura Fecha: 08/06/2017
     * @param dataSource
     * @throws java.lang.Exception
     */
    public void setDataSource(DataSource dataSource) throws Exception;
    /**
     * Función que adiciona, modifica, elimina un registro de la tabla usuariomenu
     *
     * Creado: Levi Laura Fecha: 08/06/2017
     * @param usuarioMenu
     * @param parametro
     * @return UsuarioMenu
     * @throws java.lang.Exception
     */
    
    public UsuarioMenu crudUsuarioMenu(UsuarioMenu usuarioMenu, int parametro) throws Exception;
     /**
     * Función que verifica si existe un registro  con los parametros de entrada idusuario, idmenu
     *
     * Creado: Levi Laura Fecha: 08/06/2017
     * @param idusuario
     * @param idmenu
     * @return boolean
     * @throws java.lang.Exception
     */
    public boolean verificaExisteMenu(Long idusuario, Long idmenu) throws Exception ;
    
}
