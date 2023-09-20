/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.servicio;

import java.util.List;
import javax.sql.DataSource;
import snp.gob.bo.gimodel.entidad.BusquedaModificacionResultado;

/**
 *
 * @author susana
 */
public interface BusquedaModificacionResultadoService {
     /**
     * Método que permite setear la conexion con dataSource
     *
     * Creado: Susana Escobar Paz Fecha: 16/11/2016
     * 
     * @param dataSource
     * @throws java.lang.Exception
     */
    public void setDataSource(DataSource dataSource) throws Exception;
    
    /**
     * Metodo para buscar registros modmodificacion, modtitularlicencitarionuevo, modresolucion filtrados por sm o registro o publicacion, se utiliza para el dialogo historial de modificacion en la vista de signos
     *
     * Creado: Susana Escobar Paz  Fecha: 15/11/2016
     * 
     * Modificaciones: Susana Escobar Paz  Fecha: 27/04/2017
     * @param sm
     * @param publicacion
     * @param registro
     * @param serie     
     * @return
     */
    public List<BusquedaModificacionResultado> lista_BusquedaModificacionResultado_sm_publicacion_registro_or(Long sm, Long publicacion, Long registro, String serie);
}
