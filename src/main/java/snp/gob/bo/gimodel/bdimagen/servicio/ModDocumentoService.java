/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.bdimagen.servicio;

import java.util.List;
import javax.sql.DataSource;
import snp.gob.bo.gimodel.bdimagen.entidad.ModDocumento;

/**
 *
 * @author levi
 */
public interface ModDocumentoService {
       /**
     *
     * Creado: Levi Laura Fecha: 23/11/2016
     * @param dataSource
     */
    public void setDataSource(DataSource dataSource);
    
     /**
     *
     * Creado: Levi Laura Fecha: 23/11/2016
     * Funcion que realiza el crud de la tabla ModDocumento
     * @param sigDocumentoEntrante
     * @param parametro
     */
    public ModDocumento crudModDocumento(ModDocumento sigDocumentoEntrante , int parametro) throws Exception;
    /**
     *
     * Creado: Levi Laura Fecha: 23/11/2016
     * Funcion que saca la lista de la tabla modmodificacion dado su idmodifcacion
     * @param sigDocumentoEntrante
     * @param parametro
     */
    public List<ModDocumento> listaXidModificacion(Long idmodificacion) throws Exception ;
}
