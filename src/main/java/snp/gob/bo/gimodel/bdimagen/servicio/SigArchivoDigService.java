/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.bdimagen.servicio;

import java.util.List;
import javax.sql.DataSource;
import snp.gob.bo.gimodel.bdimagen.entidad.SigArchivoDig;
import snp.gob.bo.gimodel.bdimagen.entidad.SigDocumento;

/**
 *
 * @author levi
 */
public interface SigArchivoDigService {
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
    public SigArchivoDig crudSigArchivoDig(SigArchivoDig sigArchivoDigEntrante , int parametro) throws Exception;
  
    
    /**
     * Creado: Levi Laura Fecha: 23/11/2016
       Funcion que lista sigarchvoddig dado el iddocumento
     * @param iddocumento
     * @throws java.lang.Exception
     */
    public List<SigArchivoDig> listaSigArchivoXiddoc(Long iddocumento) throws Exception;
}
