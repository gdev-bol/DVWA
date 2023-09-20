/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.servicio;

import java.util.List;
import javax.sql.DataSource;
import snp.gob.bo.gimodel.entidad.Menu;

/**
 *
 * @author Levi Laura
 * @see MenuServiceImpl
 * @version 1.0, 08/09/2016
 */

public interface MenuService {
    /**
     * Método que permite setear la conexion con dataSource
     *
     * Creado: Levi Laura Fecha: 08/06/2017
     * @param dataSource
     * @throws java.lang.Exception
     */
      public void setDataSource(DataSource dataSource) throws Exception;
      
      
        
    /**
     * Metodo para guardar,modificar,eliminar registros de la tabla Menu
     *
     * Creado: Levi Laura  Fecha: /10/2017
     * @param menu
     * @param operacion
     * @return Menu
     * @throws java.lang.Exception
     */
        
        
        public Menu guardar_modificar_listar_Menu(Menu menu, Integer operacion) throws Exception;
        
     /**
     * Metodo para listar los menus asociados a un sistema
     *
     * Creado: Levi Laura  Fecha: /10/2017
     * @param idsistema
     * @return List<Menu> 
     * @throws java.lang.Exception
     */
        public List<Menu> listaMenuXIdSistema(Long idsistema) throws Exception;
        
     /**
     * Metodo para contar del maximo mas uno la secuencia de menus para un  sistema determinado
     *
     * Creado: Levi Laura  Fecha: /10/2017
     * @param idsistema
     * @return int
     * @throws java.lang.Exception
     */   
        
        public int cuentaMasUnoMenuSistema(Long idSistema) throws Exception;
     /**
     * Metodo para colocar en estado 'NA' las paginas asociados a un menu
     *
     * Creado: Levi Laura  Fecha: /10/2017
     * @param idmenu
     * @throws java.lang.Exception
     */   
         public void eliminaPaginas(Long idmenu) throws Exception;
}
