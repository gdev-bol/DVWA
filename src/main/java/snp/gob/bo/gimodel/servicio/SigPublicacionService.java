/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.servicio;

import java.util.Date;
import java.util.List;
import javax.sql.DataSource;
import snp.gob.bo.gimodel.entidad.SigPublicacion;

/**
 *
 * @author susana
 */
public interface SigPublicacionService {

    /**
     * Método que permite setear la conexion con dataSource
     *
     * Creado: Susana Escobar Paz Fecha: 18/10/2016
     *
     * @param dataSource
     * @throws java.lang.Exception
     */
    public void setDataSource(DataSource dataSource) throws Exception;

    /**
     * metodo para guardar, modificar y listar crear registro en tabla
     * sigpublicacion
     *
     * Creado: Susana Escobar Fecha: 25/10/2016
     *
     * @param sigPublicacion
     * @param operacion
     * @return
     */
    public SigPublicacion guardar_modificar_listar_SigPublicacion(SigPublicacion sigPublicacion, Integer operacion);

    /**
     * metodo para listar todas las publicaciones activas
     *
     * Creado: Susana Escobar Fecha: 25/10/2016
     *
     * @return
     */
    public List<SigPublicacion> listaSigPublicacion();

    /**
     * metodo para verificar si un numero de publicacion ya existe
     *
     * Creado: Susana Escobar Fecha: 26/10/2016
     *
     * @param numero
     * @return
     */
    public Boolean verificaExistenciaPublicacion_numero(Long numero);

    /**
     * metodo que crear una publicacion y genera datos para la tabla
     * sigdetallepublicacion, tomando en cuenta la fecha limite
     *
     * Creado: Susana Escobar Fecha: 26/10/2016
     *
     * @param publicacion
     * @param fecha_final
     * @param numero_prepublicacion
     * @param mes
     * @param gestion
     * @param listaGacetaNoCumple
     * @param idUsuario
     * @return
     */
    public SigPublicacion generarSigPublicacion(SigPublicacion publicacion, Date fecha_final, Long numero_prepublicacion, 
            String mes, Integer gestion, String listaGacetaNoCumple, Long idUsuario);

    /**
     * metodo que permite listar sigpublicacion por numero_gaceta y estado
     *
     * Creado: Susana Escobar Fecha: 27/10/2016
     *
     * @param numero_gaceta
     * @param estado_publicacion
     * @return
     */
    public SigPublicacion listar_SigPublicacion_numero_estado(Long numero_gaceta, String estado_publicacion);

    /**
     * metodo que elimina (estado no activo) un registro en tablas
     * sigdetallepublicacion y sigpublicacion
     *
     * Creado: Susana Escobar Fecha: 27/10/2016
     *
     * @param idPublicacion
     */
    public void eliminar_SigPublicacion_SigDetallePublicacion(Long idPublicacion);

    /**
     * metodo que obtiene objeto sigpublicacion filtrado por sm
     *
     * Creado: Susana Escobar Paz Fecha: 10/11/2016
     *
     * @param sm
     * @return
     */
    public SigPublicacion listar_SigPublicacion_sm(Long sm);

    /**
     * metodo para devolver el id de la publicacion de acuerdo al numero de
     * gaceta.
     *
     * Creado: Ruben Ramirez Fecha: 12/11/2016
     *
     * @param numero_gaceta
     * @return
     */
    public SigPublicacion obtenerIdPublicacionXNumeroGaceta(Long numero_gaceta);

    /**
     * metodo que obtiene lista de enteros con numeros de prepublicaciones activas 
     *
     * Creado: Susana Escobar Paz Fecha: 20/11/2016
     *
     * @return
     */
    public List<Integer> lista_Gaceta_Prepublicacion();

}
