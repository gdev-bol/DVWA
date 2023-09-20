/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.servicio;
import java.util.List;
import javax.sql.DataSource;
import snp.gob.bo.gimodel.entidad.UsuarioPagina;
import snp.gob.bo.gimodel.mapper.UsuarioPaginaMapper;
/**
 *
 * @author Levi Laura
 * @see UsuarioPaginaImpl
 * @version 1.0, 08/09/2016
 */
public interface UsuarioPaginaService {
    
    /**
     * Método que permite setear la conexion con dataSource
     *
     * Creado: Levi Laura Fecha: 08/06/2017
     * @param dataSource
     * @throws java.lang.Exception
     */
    public void setDataSource(DataSource dataSource) throws Exception;
     /**
     * Función que Lista usuarioPagina todos
     *
     * Creado: Levi Laura Fecha: 08/06/2017
     * @return List<UsuarioPagina>
     * @throws java.lang.Exception
     */
     public List<UsuarioPagina>  listaUsuarioPagina() throws Exception;
     /**
     * Este metodo sirve para que pinte o no el boton que este registrado en tabla pagina  y usuariopagina según se quiera.
     * Devuelve false para que los casos en que no existe el registro en base de datos, o si el campo 'estado' exista y estado
     * este en estado AC, y devuelve true si esta en estado 'AN'
     *
     * Creado: Levi Laura Fecha: 08/06/2017
     * @return Boolean
     * @param idUsuario
     * @param descripcion
     * @throws java.lang.Exception
     */
    
      public Boolean  estadoBotonUsuario(String idUsuario, String descripcion) throws Exception;
      
      /**
      * Este metodo sirve para que pinte o no el boton que este registrado en tabla pagina  y usuariopagina según se quiera.
      * Devuelve false para que los casos en que no exista el registro en la base de datos, o si el campo 'estado' exista.
      * 
      * Creado: Levi Laura Fecha: 08/06/2017
      * @throws java.lang.Exception
      */
      
      public String  obtieneHabalitado() throws Exception;
      /**
     * Esta función realiza la adición, modificación y eliminación de la tabla UsuarioPagina
     *
     * Creado: Levi Laura Fecha: 08/06/2017
     * @return UsuarioPagina
     * @param usuarioPagina
     * @param parametro
     * @throws java.lang.Exception
     */
      
      public UsuarioPagina crudUsuarioPagina(UsuarioPagina usuarioPagina, int parametro) throws Exception;
       /**
        * Esta función elimina el registro de la tabla usuariopagina
        *
        * Creado: Levi Laura Fecha: 21/08/2017
        * @param idusuario
        * @param idpagina
        * @throws java.lang.Exception
       */
      
      public void quitaEstadoUsuPagina(Long idusuario,Long idpagina) throws Exception;
       /**
        * Esta función verifica si existe un registro con los parametros enviados de idusuario e idpagina
        *
        * Creado: Levi Laura Fecha: 21/11/2017
        * @return boolean
        * @param idusuario
        * @param idpagina
        * @throws java.lang.Exception
        */ 
      
      public boolean verificaExistePagina(Long idusuario, Long idpagina) throws Exception;
      /**
        * Esta función modifica los campos de muestra, estado según el registro encontrado según el idpagina e idusuario
        *
        * Creado: Levi Laura Fecha: 21/11/2017
        * @param muestra
        * @param estado
        * @param idusuario
        * @param idpagina
        * @throws java.lang.Exception
        */
      
      public void modificaUsuairoPagEstadoMues(boolean muestra, String estado, Long idpagina, Long idusuario) throws Exception;
}


