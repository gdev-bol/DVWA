/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.servicio;

import java.util.List;
import javax.sql.DataSource;
import snp.gob.bo.gimodel.entidad.DatoElementoFormulario;
import snp.gob.bo.gimodel.entidad.Usuario;

/**
 *
 * @author Levi Laura
 * @see Usuario
 * @see UsuarioServiceImpl
 * @version 1.0, 08/09/2016
 */
public interface UsuarioService {

    /**
     * Método que permite setear la conexion con dataSource
     *
     * Creado: Levi Laura Fecha: 08/06/2017
     * @param dataSource
     * @throws java.lang.Exception
     */
    public void setDataSource(DataSource dataSource) throws Exception;

    /**
     * Función que Lista usuarios según el idusuario
     *
     * Creado: Levi Laura Fecha: 08/06/2017
     * @param idusuario
     * @return List<Usuario>
     *
     * @throws java.lang.Exception
     */
    public List<Usuario> listaUsuarioXidPagina(Long idusuario) throws Exception;

    /**
     * Función que Lista  todos los usuarios.
     *
     * Creado: Levi Laura Fecha: 08/06/2017
     * @return  List<Usuario> 
     * @throws java.lang.Exception
     */
    public List<Usuario> listaUsuario() throws Exception;

    /**
     *  Función que realiza la inserción y modificación de la tabla de  usuarios.
     *
     * Creado: Levi Laura Fecha: 08/06/2017
     * @param usuario
     * @param parametro
     * @return Usuario
     * @throws java.lang.Exception
     */
    public Usuario crudUsuario(Usuario usuario, int parametro) throws Exception;

    /**
     * Función que realiza la búsqueda de un usuario por el id con estado activo.
     *
     * Creado: Levi Laura Fecha: 08/06/2017
     * @param idUsuario
     * @return Usuario
     * @throws java.lang.Exception
     */
    public Usuario obtenerUsuario(Long idUsuario) throws Exception;

    /**
     * Función que realiza la búsqueda de un usuario por el id cualquiera sea su estado.
     * 
     
     * Creado: Levi Laura Fecha: 08/06/2017
     * @param idUsuario
     * @return Usuario
     * @throws java.lang.Exception
     */
    Usuario buscaUsuarioPorIdUsuario(Long idUsuario) throws Exception;

    /**
     * Metodo que lista el nombre completo de un usuario segun un idusuario, se
     * concatenan los nombres y apellidos en un String
     *
     * Creado: Levi Laura Fecha: 08/06/2017
     * 
     * @param idusuario
     * @return
     */
    public String listarNombreCompletoXIdUsuario(Long idusuario);
    
    /**
     * Metodo que Modifica la contraseña del usuario
     *
     * Creado: Levi Laura Fecha: 08/06/2017
     * @param contrasenia
     * @param idusuario
     * @throws java.lang.Exception
     */  
    public void cambiaContrasenia(String contrasenia, Long idusuario) throws Exception;;
}
