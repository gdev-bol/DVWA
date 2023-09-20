/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.servicio;
import java.util.Date;
import java.util.List;
import javax.sql.DataSource;
import snp.gob.bo.gimodel.entidad.FlujoSeguimiento;
/**
 *
 * @author levi
 */
public interface FlujoSeguimientoService {
    public void setDataSource(DataSource dataSource);
    
    /**
     * metodo que devuelve la siguiente etapa segun la tabla flujoseguimiento
     *
     * Creado: Levi Laura Fecha: 18/07/2017
     * 
     * @param ordenActual
     * @return
     * @throws java.lang.Exception
     */
    public List<FlujoSeguimiento>  verificaSiguienteEtapa(Integer ordenActual) throws Exception;
    /**
     * metodo que dado la etapa actual devuelve el siguiente o nulo si no existe un siguiente etapa
     *
     * Creado: Levi Laura Fecha: 19/07/2017
     * 
     * @param etapa
     * @return
     * @throws java.lang.Exception
     */
    public List<FlujoSeguimiento>  sigueFlujoSeguimiento(String etapa) throws Exception;
    
    /**
     * metodo que dado la etapa y prefijo (SIG Flujo:1, MOD Flujo:2, REN Flujo:3) devuelve el registro FlujoSeguimiento encontrado
     *
     * Creado: Susana Escobar Paz Fecha: 13/09/2017
     * 
     * @param idetapa
     * @param prefijo
     * @return
     */
    public FlujoSeguimiento lista_FlujoSeguimiento_idetapa(Long idetapa, String prefijo);
    
     /**
     * metodo que dado la etapa y prefijo (SIG Flujo:1, MOD Flujo:2, REN Flujo:3) devuelve el registro FlujoSeguimiento encontrado
     *
     * Creado: Susana Escobar Paz Fecha: 13/09/2017
     * 
     * @param idetapa
     * @param prefijo
     * @return
     */
    public Boolean[] configuracionBotonesRecepcionarFinalizar(Long idetapa, String prefijo); 
    
}
