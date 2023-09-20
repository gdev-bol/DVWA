/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.servicio;

import java.util.List;
import javax.sql.DataSource;
import snp.gob.bo.gimodel.entidad.SigPrioridadPreferencia;
import snp.gob.bo.gimodel.solicitudes.entidades.Prioridades;

/**
 *
 * @author Eddy Valero
 * @version 1.0, 29/08/2016
 */
public interface SigPrioridadPreferenciaService {

    public void setDataSource(DataSource dataSource);

    /**
     *
     * Creado: Eddy Valero Fecha: 29/08/2016
     * @param sigPrioridadPreferencia
     * @return SigPrioridadPreferencia
     * @throws java.lang.Exception
     */
    public SigPrioridadPreferencia registrarSigPrioridadPreferencia(SigPrioridadPreferencia sigPrioridadPreferencia) throws Exception;
    
    /**
     * Metodo que obtiene la prioridad extranjera
     * Creado: Eddy Valero Fecha: 26/09/2016
     * @param idSignoMarca
     * @return SigPrioridadPreferencia
     * @throws java.lang.Exception
     */
    
    public SigPrioridadPreferencia obtenerPrioridadExtranjera(Long idSignoMarca) throws Exception;
    
    /**
     * Metodo que obtiene la prioridad de exposicion
     * Creado: Eddy Valero Fecha: 26/09/2016
     * @param idSignoMarca
     * @return SigPrioridadPreferencia
     * @throws java.lang.Exception
     */
    public SigPrioridadPreferencia obtenerPrioridadExposicion(Long idSignoMarca) throws Exception;
    
    /**
     * Metodo que obtiene la prioridad de exposicion
     * Creado: Eddy Valero Fecha: 26/09/2016
     * @param idSignoMarca
     * @return SigPrioridadPreferencia
     * @throws java.lang.Exception
     */
    public SigPrioridadPreferencia obtenerOposicionAndina(Long idSignoMarca) throws Exception;
    
    
    /**
     * Método crud de la tabla sigprioridadpreferencia
     * 
     * Creado: Eddy Valero Fecha: 10/10/2016
     * @param sigPrioridadPreferencia
     * @param parametro
     * @return SigDireccionNotificacion
     * @throws java.lang.Exception
     */
    public SigPrioridadPreferencia crudSigPrioridadPreferencia(SigPrioridadPreferencia sigPrioridadPreferencia, int parametro) throws Exception;

    /**
     * Método que obtiene sigprioridadpreferencia
     *
     * Creado: Placido Castro Fecha: 10/11/2017
     *
     * @param idSignoMarca
     * @return SigDireccionNotificacion
     * @throws java.lang.Exception
     */
    public List<SigPrioridadPreferencia> obtenerListaPrioridadPreferencia(Long idSignoMarca) throws Exception;

    /**
     * Método crud de la tabla sigprioridadpreferencia
     *
     * Creado: Placido Castro Fecha: 10/11/2017
     *
     * @param idSignoMarca
     * @param listaPrioridadPreferenciaSipi
     * @param idLogTrans
     * @throws java.lang.Exception
     */
    public void modificarPrioridadPreferenciaSubsanacion(Long idSignoMarca, List<Prioridades> listaPrioridadPreferenciaSipi, Long idLogTrans) throws Exception;
    
}
