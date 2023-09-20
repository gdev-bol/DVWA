/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.bdimagen.servicio;

import java.util.List;
import javax.sql.DataSource;
import snp.gob.bo.gimodel.bdimagen.entidad.RenArchivoDig;

/**
 *
 * @author levi
 */
public interface RenArchivoDigService {
    /**
     *
     * Creado: Levi Laura Fecha: 23/11/2016
     * @param dataSource
     */
    public void setDataSource(DataSource dataSource);
    
     /**
     *
     * Creado: Levi Laura Fecha: 23/11/2016
     * Funcion que realiza el crud de la tabla SigDocumento
     * @param renArchivoDigEntrante
     * @param parametro
     * @return 
     * @throws java.lang.Exception
     */
    public RenArchivoDig crudRenArchivoDig(RenArchivoDig renArchivoDigEntrante , int parametro) throws Exception;
  /**
     *
     * Creado: Levi Laura Fecha: 23/11/2016
     * Funcion que rlista modarchivodig dado el iddocumento
     * @param iddocumento
     
     */
    
    /**
     * Creado: Levi Laura Fecha: 23/11/2016
 Funcion que rlista modarchivodig dado el iddocumento
     * @param iddocumento
     * @return 
     * @throws java.lang.Exception
     */
    public List<RenArchivoDig> listaRenArchivoXiddoc(Long iddocumento) throws Exception;
}

