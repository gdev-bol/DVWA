/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.servicio;

import java.util.Date;
import java.util.List;
import javax.sql.DataSource;
import snp.gob.bo.gimodel.entidad.Historial;
import snp.gob.bo.gimodel.entidad.ModDireccionDeNotificacion;
import snp.gob.bo.gimodel.entidad.RenDireccionDeNotificacion;
import snp.gob.bo.gimodel.entidad.SigDireccionDeNotificacion;

/**
 *
 * @author Chano Rojas
 * @see Historial
 * @see HistorialService
 * @version 1.0, 01/10/2016
 */
public interface HistorialService {

    public void setDataSource(DataSource dataSource) throws Exception;

    /**
     *
     * @author Chano Rojas
     * @param historial
     * @param prefijoTramite
     * @param operacion
     * @return
     * @throws java.lang.Exception
     * @version 1.0, 01/10/2016
     */
    Historial crudHistorial(Historial historial, String prefijoTramite, int operacion) throws Exception;

    /**
     *
     * @author Chano Rojas
     * @param historial
     * @param prefijoTramite
     * @param operacion
     * @return
     * @throws java.lang.Exception
     * @version 1.0, 01/10/2016
     */
    Historial crudHistorial_Renovacion(Historial historial, String prefijoTramite, int operacion) throws Exception;

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
    List<Historial> obtenerListaHistorialXId(Long id, String prefijo) throws Exception;

   /**
     * Metodo que sirve para guardar un registro particular en la tabla de historial de los modulos Signos, Modificaciones y Renovaciones
     *
     * Creado: Susana Escobar Paz Fecha: 13/09/2017
     *
     * @param id
     * @param prefijo
     * @param usuario
     * @param seccion
     * @param operacion
     * @param estado
     * @param observacion
     * @param ubicacion
     * @param descripcion
     * @return
     * @version 1.0, 18/11/2016
     */
    public Historial guardarHistorialGenerico(String prefijo, Long id, Long usuario, String seccion,
            String operacion, String estado, String observacion, String ubicacion, String descripcion);
}
