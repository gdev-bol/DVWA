/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.servicio;

import javax.sql.DataSource;
import snp.gob.bo.gimodel.entidad.UsuarioTramite;

/**
 *
 * @author Eddy Valero
 * @see UsuarioTramite
 * @see UsuarioTramiteServiceImpl
 * @version 1.0, 18/05/2017
 */
public interface UsuarioTramiteService {

    /**
     * Método que permite setear la conexion con dataSource
     *
     * @param dataSource
     * @throws java.lang.Exception
     */
    public void setDataSource(DataSource dataSource) throws Exception;

    /**
     * Método que realiza la insercion y modificacion de usuario
     *
     * @param usuarioTramite
     * @param parametro
     * @return Usuario
     * @throws java.lang.Exception
     */
    public UsuarioTramite crudUsuarioTramite(UsuarioTramite usuarioTramite, Long parametro) throws Exception;

    
}
