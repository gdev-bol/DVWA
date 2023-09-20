/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.bdimagen.servicio;

import java.util.List;
import javax.sql.DataSource;
import snp.gob.bo.gimodel.bdimagen.entidad.SigDocumento;

/**
 *
 * @author levi
 */
public interface SigDocumentoService {
    
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
     * @return 
     * @throws java.lang.Exception
     */
    public SigDocumento crudSigDocumento(SigDocumento sigDocumentoEntrante , int parametro) throws Exception;
    /**
     *
     * Creado: Levi Laura Fecha: 23/11/2016
     * Funcion que lista lso documentos  que pertenecen a un signo , se busca pro su idsignomarca
     * @param idsignomarca
     * @param sigDocumentoEntrante
     * @param parametro
     * @return 
     * @throws java.lang.Exception
     */
     public List<SigDocumento> listaXidSignoMarca(Long idsignomarca) throws Exception ;
}
