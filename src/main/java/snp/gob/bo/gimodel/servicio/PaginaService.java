/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.servicio;

import java.util.List;
import javax.sql.DataSource;
import snp.gob.bo.gimodel.entidad.Pagina;

/**
 *
 * @author Levi Laura Ramos
 * @see PaginaServiceImpl
 * @version 1.0, 08/09/2016
 */
public interface PaginaService {
     /**
     * Método que permite setear la conexion con dataSource
     *
     * Creado: Levi Laura Fecha: 08/06/2017
     * @param dataSource
     * @throws java.lang.Exception
     */
     public void setDataSource(DataSource dataSource) throws Exception;
      /**
       * Metodo para listar registros de páginas según idmenu.
       *
       * Creado: Levi Laura  Fecha: 09/11/2016
       * @param idmenu
       * @return List<Pagina>
       * @throws java.lang.Exception
       */
     
     public List<Pagina> listaMenuXIdSistema(Long idmenu) throws Exception;
      /**
       * Metodo para guardar, modificar, eliminar registros de la tabla Pagina.
       *
       * Creado: Levi Laura  Fecha: 09/11/2016
       * @param pagina
       * @param operacion
       * @return Pagina
       * @throws java.lang.Exception
      */
     
     public Pagina guardar_modificar_listar_Pagina(Pagina pagina, Integer operacion) throws Exception;
}
