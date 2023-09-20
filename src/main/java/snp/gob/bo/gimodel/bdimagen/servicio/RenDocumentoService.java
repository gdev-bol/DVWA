/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.bdimagen.servicio;

import java.util.List;
import javax.sql.DataSource;
import snp.gob.bo.gimodel.bdimagen.entidad.RenDocumento;

/**
 *
 * @author levi
 */
public interface RenDocumentoService {
    
     /**
     * Creado: Levi Laura Fecha: 23/11/2016
     * @param dataSource
     */
    public void setDataSource(DataSource dataSource);
    
     /**
     *
     * Creado: Levi Laura Fecha: 23/11/2016
     * Funcion que realiza el crud de la tabla RenDocumento
     * @param renDocumentoEntrante
     * @param parametro
     * @return 
     * @throws java.lang.Exception
     */
    public RenDocumento crudRenDocumento(RenDocumento renDocumentoEntrante , int parametro) throws Exception;
    /**
     *
     * Creado: Levi Laura Fecha: 23/11/2016
     * Funcion que saca la lista de la tabla renrenovacion dado su idsolicitudrenovacion
     * @param idsolicitudrenovacion
     * @return 
     * @throws java.lang.Exception
     */
    public List<RenDocumento> listaXidsolicitudrenovacion(Long idsolicitudrenovacion) throws Exception ;
    
}
