/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.servicio;

import java.util.List;
import javax.sql.DataSource;
import snp.gob.bo.gimodel.entidad.BusquedaHistorialRenovacion;
import snp.gob.bo.gimodel.entidad.BusquedaModificacionResultado;

/**
 *
 * @author susana
 */
public interface BusquedaHistorialRenovacionService {
     /**
     * Método que permite setear la conexion con dataSource
     *
     * Creado: Susana Escobar Paz Fecha: 08/12/2017
     * 
     * @param dataSource
     * @throws java.lang.Exception
     */
    public void setDataSource(DataSource dataSource) throws Exception;
    
    /**
     * Metodo para buscar registros de renovaciones concedidas filtrados por sm o numero de registro
     *
     * Creado: Susana Escobar Paz  Fecha: 08/12/2017
     * 
         * @param sm
     * @param publicacion
     * @param registro
     * @param serie     
     * @return
     */
    public List<BusquedaHistorialRenovacion> lista_HistorialRenovacion_sm_publicacion_registro_or(Long sm, Long publicacion, Long registro, String serie);
    
     /**
     * Metodo para listado de solicitudes de renovaciones no concedidas
     *
     * Creado: Susana Esobar Paz Fecha: 08/12/2017
     *
     * @param index
     * @param tamaniolista
     * @return ObjetoEliminadoGenerico
     * @version 1.0, 08/12/2017
     */
    public String ordenModificacionLiteral(Integer index, Integer tamaniolista);
}
