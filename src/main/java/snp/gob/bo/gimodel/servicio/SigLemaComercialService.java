/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package snp.gob.bo.gimodel.servicio;

import javax.sql.DataSource;
import snp.gob.bo.gimodel.entidad.SigLemaComercial;

/**
 *
 * @author Eddy Valero
 * @version 1.0, 02/09/2016
 */
public interface SigLemaComercialService {
    
    public void setDataSource(DataSource dataSource);
    
    public SigLemaComercial guardarSigLemaComercial(SigLemaComercial sigLemaComercial) throws Exception;
    
     /**
     * Metodo de busca un objeto SigLemaComercial por idssignomarca 
     * Creado: Ruben Ramirez Fecha: 11/10/2016
     * @param idsignomarca
     * @return sigLemaComercial
     * @throws java.lang.Exception
     */
    public SigLemaComercial obtenerSigLemaComercial(Long idsignomarca) throws Exception;
    
}
