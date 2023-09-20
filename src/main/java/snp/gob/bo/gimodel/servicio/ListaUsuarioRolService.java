/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.servicio;

import java.util.List;
import javax.sql.DataSource;
import snp.gob.bo.gimodel.entidad.SigSignoMarca;
import snp.gob.bo.gimodel.entidad.listaMenu;

/**
 *
 * @author levi
 */
public interface ListaUsuarioRolService {
      /**
     * Método para setear la conexion de la base de datos
     * Creado: Levi Laura Fecha: 18/11/2016
     * @param datasource
     * @return 
     */
      public void setDataSource(DataSource dataSource);
       /**
     * Método para obtener el menu segun passaword login y el idsistema
     * Creado: Levi Laura Fecha: 18/11/2016
     * @param login
     * @param pass
     * @param idSistema
     * @return   List<listaMenu>
     */
       
    public List<listaMenu> getUsuarioRol(String login,String pass, String idSistema) throws Exception;
        /**
     * Método qeu indica si existe un usuario segun su login y pass
     * Creado: Levi Laura Fecha: 18/11/2016
     * @param login
     * @param pass
     * @return  boolean
     * @throws java.lang.Exception
     */
      public Boolean existeUsuarioRol(String login, String pass) throws Exception;
         /**
     * Método que obtiene el idusuario segun login y pass
     * Creado: Levi Laura Fecha: 18/11/2016
     * @param login
     * @param pass
     * @return  long
     * @throws java.lang.Exception
     */
      public Long obtieneIdUsuario(String login, String pass) throws Exception;
         /**
     * Método que encripta segun su login y passsword
     * Creado: Levi Laura Fecha: 18/11/2016
     * @param login
     * @param pass
     * @return  String
     * @throws java.lang.Exception
     */
      public String encriptarContrasena(String login, String pass) throws Exception;
      
        /**
     * Método que encripta segun su login y passsword
     * Creado: Levi Laura Fecha: 18/11/2016
     * @param login
     * @param pass
     * @return  String
     * @throws java.lang.Exception
     */
      
      public String dencriptarContrasena(String login, String codificado) throws Exception;
}
