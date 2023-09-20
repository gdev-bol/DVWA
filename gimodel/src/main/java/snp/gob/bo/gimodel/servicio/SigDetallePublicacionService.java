/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.servicio;

import java.util.Date;
import java.util.List;
import javax.sql.DataSource;
import snp.gob.bo.gimodel.entidad.SigDetallePublicacion;

/**
 *
 * @author susana
 */
public interface SigDetallePublicacionService {

    /**
     * Creado: Susana Escobar Paz Fecha: 18/10/2016
     *
     * @param dataSource
     */
    public void setDataSource(DataSource dataSource);

    /**
     * metodo para guardar registro en tabla sigdetallepublicacion
     *
     * Creado: Susana Escobar Fecha: 24/10/2016
     *
     * @param sigDetallePublicacion
     * @param operacion
     * @return
     */
    public SigDetallePublicacion guardar_modificar_listar_SigDetallePublicacion(SigDetallePublicacion sigDetallePublicacion, Integer operacion);

    /**
     * metodo listar registros de tabla sigdetallepublicacion por idpublicacion
     *
     * Creado: Susana Escobar Fecha: 25/10/2016
     *
     * @param idpublicacion
     * @return
     */
    public List<SigDetallePublicacion> listaSigDetallePublicacionXidpublicacion(Long idpublicacion);

    /**
     * metodo para devolver tramite a estado Examen de forma y agrega una
     * observacion si es necesario
     *
     * Creado: Susana Escobar Fecha: 25/10/2016
     *
     * @param sigDetallePublicacion
     * @param observacion
     * @param idusuario
     */
    public void devolverDetallePublicacion(SigDetallePublicacion sigDetallePublicacion, String observacion, Long idusuario);

    /**
     * metodo para devolver tramite a estado Examen de forma y agrega una
     * observacion si es necesario
     *
     * Creado: Susana Escobar Fecha: 25/10/2016
     *
     * @param fecha_ingreso
     * @param listaGacetaNoCumple
     * @return
     */
    public List<SigDetallePublicacion> lista_generarSigDetallePublicacion(Date fecha_ingreso, String listaGacetaNoCumple);

    /**
     * metodo para devolver la cantidad de tramites asociados a un idpublicacion
     * observacion si es necesario
     *
     * Creado: Susana Escobar Fecha: 25/10/2016
     *
     * @param idPublicacion
     * @return
     */
    public Long contador_sigDetallePublicacion(Long idPublicacion);

    /**
     * metodo para verificar si existen marcas para publicar segun la fecha
     *
     * Creado: Susana Escobar Fecha: 26/10/2016
     *
     * @param fecha
     * @return
     */
    public Boolean verificarMarcasPublicacion(Date fecha);

    /**
     * metodo que asigna numeracion a tramites en publicacion segun
     * idpublicacion
     *
     * Creado: Susana Escobar Fecha: 26/10/2016
     *
     * @param idPublicacion
     */
    public void numeracionSigDetallePublicacion(Long idPublicacion);
    
    /**
     * metodo que asigna numeracion a tramites en publicacion segun
     * idpublicacion e indicando un numero de inicio
     *
     * Creado: Susana Escobar Fecha: 15/03/2017
     *
     * @param idPublicacion
     * @param inicio
     */
    public void numeracionSigDetallePublicacionXInicio(Long idPublicacion, Long inicio);
    

    /**
     * Metodo que crea seguimiento de publicacion en los tramites
     * correspondientes a una publicacion segun idPublicaacion
     *
     * Creado: Susana Escobar Fecha: 27/10/2016
     *
     * @param idPublicacion
     * @param idusuario
     * @param fechaPublicacion
     * @param fechaEnvio
     */
    public void seguimiento_SigDetallePublicacion(Long idPublicacion, Long idusuario, Date fechaPublicacion, Date fechaEnvio);

    /**
     * Metodo para obtener registro sigdetallepublicacion filtrado por SM
     *
     * Creado: Susana Escobar Fecha: 28/10/2016
     *
     * @param sm
     * @return
     */
    public SigDetallePublicacion listar_SigDetallePublicacion_sm(Long sm);

    /**
     * Metodo para crear historial de cambios en tabla sighistorial del modulo de signos, afecta a la seccion Publicacion
     *
     * Creado: Susana Escobar Fecha: 21/11/2016
     *
     * @param idPublicacion
     * @param gaceta
     * @param fecha
     * @param idusuario
     * @param fechaRecepcion
     */
    public void historial_SigDetallePublicacion_Signos(Long idPublicacion, Long gaceta, Date fecha, Long idusuario, Date fechaRecepcion);

    /**
     * Metodo que actualiza la fecha de recepcion en el ultimo seguimiento de la etapa de Publicacion
     *
     * Creado: Susana Escobar Fecha: 22/11/2016
     *
     * @param idPublicacion
     * @param fechaEnvio
     * @param fechaPublicacion
     */
    public void actualizar_SigSeguimiento_SigDetallePublicacion(Long idPublicacion, Date fechaEnvio, Date fechaPublicacion);

    /**
     * Metodo crear el primer historial de cambios en tabla sighistorial del modulo de signos, afecta a la seccion Publicacion
     *
     * Creado: Susana Escobar Fecha: 22/11/2016
     *
     * @param idPublicacion
     * @param fechaPublicacion
     * @param idusuario
     */
    public void primer_SigHistorial(Long idPublicacion, Date fechaPublicacion, Long idusuario);
}
