/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.servicio;

import java.util.List;
import javax.sql.DataSource;
import snp.gob.bo.gimodel.entidad.Historial;
import snp.gob.bo.gimodel.entidad.HistorialResultado;
import snp.gob.bo.gimodel.entidad.ModDireccionDeNotificacion;
import snp.gob.bo.gimodel.entidad.RenDireccionDeNotificacion;
import snp.gob.bo.gimodel.entidad.SigDireccionDeNotificacion;

/**
 *
 * @author Eddy Valero
 * @see Historial
 * @see HistorialService
 * @version 1.0, 19/11/2016
 */
public interface HistorialResultadoService {
    
    

    public void setDataSource(DataSource dataSource) throws Exception;

    /**
     * Método para obtener la lista de historial
     * 
     * @author Eddy Valero
     * 
     * @param id
     * @param prefijo
     * @return
     * @throws java.lang.Exception
     * @version 1.0, 18/11/2016
     */
    List<HistorialResultado> obtenerListaHistorialCompletoXId(Long id, String prefijo) throws Exception;
    

}
