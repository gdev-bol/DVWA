/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.servicio;

import java.util.List;
import javax.sql.DataSource;
import snp.gob.bo.gimodel.entidad.MenuPagina;
import snp.gob.bo.gimodel.entidad.MenuPaginaUsuario;

/**
 *
 * @author Levi Laura Ramos
 * @see MenuPaginaServiceImpl
 * @version 1.0, 08/09/2016
 */
public interface MenuPaginaService {
    /**
     * Método que permite setear la conexion con dataSource
     *
     * Creado: Levi Laura Fecha: 08/06/2017
     * @param dataSource
     * @throws java.lang.Exception
     */
    public void setDataSource(DataSource dataSource) throws Exception;
     /**
       * Metodo para guardar, modificar, eliminar registro de la tablla MenuPagina
       *
       * Creado: Levi Laura  Fecha: 09/11/2016
       * @param menuPagina
       * @param c
       * @return MenuPagina
       * @throws java.lang.Exception
       */
    public MenuPagina guardar_modificar_listar_Pagina(MenuPagina menuPagina, Integer c) throws Exception;
    
     /**
       * Metodo para contar mas uno el id consecutivo.
       *
       * Creado: Levi Laura  Fecha: 09/11/2016
       * @param idMenu
       * @return int
       * @throws java.lang.Exception
       */
    public int cuentaMasUnoPagMenu(Long idMenu) throws Exception;
    /**
       * Función para obtener las páginas y menus activos asociados  a un idsistema
       *
       * Creado: Levi Laura  Fecha: 09/11/2016
       * @param idsistema
       * @return List<MenuPaginaUsuario> 
       * @throws java.lang.Exception
       */
    public List<MenuPaginaUsuario> obtieneMenusPaginas(Long idsistema) throws Exception;
     
    /**
       * Función que despliega una lista de MenuPagina tomando en cuenta el login y contraseña
       *
       * Creado: Levi Laura  Fecha: 09/11/2016
       * @param idMenu
       * @param idMenu
       * @return List<MenuPaginaUsuario>
       * @throws java.lang.Exception
       */
    
    
    public List<MenuPaginaUsuario> listaMenusPaginasUsuario(String login, String contrasenia) throws Exception;
}
    