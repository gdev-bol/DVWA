/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.bdimagen.servicio;

import java.util.List;
import javax.sql.DataSource;
import snp.gob.bo.gimodel.bdimagen.entidad.ModArchivoDig;

/**
 *
 * @author levi
 */
public interface ModArchivoDigService {
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
     * @param sigDocumentoEntrante
     * @param parametro
     */
    public ModArchivoDig crudModArchivoDig(ModArchivoDig modArchivoDigEntrante , int parametro) throws Exception;
  /**
     *
     * Creado: Levi Laura Fecha: 23/11/2016
     * Funcion que rlista modarchivodig dado el iddocumento
     * @param iddocumento
     
     */
    
    public List<ModArchivoDig> listaModArchivoXiddoc(Long iddocumento) throws Exception;
}
