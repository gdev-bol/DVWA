/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.servicio;

import java.util.Date;
import java.util.List;
import javax.sql.DataSource;
import snp.gob.bo.gimodel.entidad.ObservacionTramite;
import snp.gob.bo.gimodel.entidad.ObservacionTramiteSig;
import snp.gob.bo.gimodel.entidad.SigObservacionTramite;

/**
 *
 * @author Eddy Valero
 * @see SigObservacionTramite
 * @see ObservacionTramiteServiceImpl
 * @version 1.0, 20/08/2016
 */
public interface ObservacionTramiteService {

    /**
     * Metodo que inyecta el datasource ----------------------------------
     * Creado: Eddy Valero Fecha: 20/08/2016
     */
    public void setDataSource(DataSource dataSource) throws Exception;

    /**
     * Creado: Eddy Valero Fecha: 14/09/2016
     *
     * @param idObservacionTramite
     * @param prefijo
     * @param idSignoMarca
     * @param idUsuario
     * @param idLogTrans
     * @param editable
     * @param fechaServidor
     * @param etapaTramite
     * @param descripcion
     * @param operacion
     * @throws java.lang.Exception
     */
    public void registrarObservacionTramite(Long idObservacionTramite, String prefijo, Long idSignoMarca, Long idUsuario, Long idLogTrans,
            Boolean editable, Date fechaServidor, String etapaTramite, String descripcion, int operacion) throws Exception;

    /**
     * Creado: Eddy Valero Fecha: 14/09/2016
     *
     * @param prefijo
     * @param idSignoMarca
     * @return
     */
    
    public List<ObservacionTramite> obtListadoObservacionPorTramite(String prefijo, Long idSignoMarca);

    /**
     * Creado: Eddy Valero Fecha: 14/09/2016
     *
     * @param prefijo
     * @param idSignoMarca
     * @return
     */
    public List<ObservacionTramiteSig> obtListadoObservacionPorTramite2(String prefijo, Long idSignoMarca);
    
    /**
     * Creado: Eddy Valero Fecha: 14/09/2016
     *
     * @param idSignoMarca
     * @throws java.lang.Exception
     */
    public void eliminarObservacion(Long idSignoMarca) throws Exception;

    /**
     * Creado: Eddy Valero Fecha: 14/09/2016
     *
     * @param prefijo
     * @param idReferencia
     * @param descripcion
     * @throws java.lang.Exception
     */
    public void actualizarObservacion(String prefijo, Long idReferencia, String descripcion) throws Exception;

    /**
     * Método para obtener la ultima observación del tramite Creado: Eddy Valero
     * Fecha: 14/09/2016
     *
     * @param prefijo
     * @param idReferencia
     * @return SigObservacionTramite
     * @throws java.lang.Exception
     */
    public ObservacionTramite obtenerUltimaObservacionTramite(String prefijo, Long idReferencia) throws Exception;

    /**
     * Metodo para guardar modificar y eliminar regitros en tablas
     * sigobservaciontramite, modobservaciontramite, renobservaciontramite
     *
     * Creado: Susana Escobar Paz
     *
     * Fecha: 29/09/2016
     *
     * @param observacionTramite
     * @param prefijo
     * @param operacion
     * @return
     */
    public ObservacionTramite guardar_modificar_listar_ObservacionTramite(ObservacionTramite observacionTramite, String prefijo, Integer operacion);

    /**
     * Metodo para listar la ultima observaciontramite de la etapa de Ventanilla
     * filtrado por el id referencia y el Modulo (Signos, Modificaciones o
     * Renovaciones)
     *
     * Creado: Susana Escobar Paz
     *
     * Fecha: 11/01/2017
     *
     * @param idreferencia
     * @param tipoFormulario
     * @return
     */
    public ObservacionTramite listar_ObservacionTramite_ventanilla(Long idreferencia, String tipoFormulario);

}
