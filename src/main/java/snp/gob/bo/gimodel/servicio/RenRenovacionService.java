
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.servicio;

import java.util.Date;
import java.util.List;
import javax.sql.DataSource;
import snp.gob.bo.gimodel.entidad.RenRenovacion;
import snp.gob.bo.gimodel.entidad.RenResolucion;
import snp.gob.bo.gimodel.entidad.RenSolicitudRenovacion;
import snp.gob.bo.gimodel.entidad.SigSignoMarca;
import snp.gob.bo.gimodel.entidad.Usuario;

/**
 *
 * @author Chano Rojas
 * @see ReciboService
 * @see ReciboService
 * @version 1.0, 05/06/2016
 */
public interface RenRenovacionService {

    /**
     * Método que permite setear la conexion con dataSource
     *
     * @param dataSource
     * @throws java.lang.Exception
     */
    public void setDataSource(DataSource dataSource) throws Exception;

    /**
     * Método que permite realizar el registro modificacion de la entidad
     * renrenovacion
     *
     * @author Chano Rojas
     * @see ReciboService
     * @param renRenovacion
     * @param parametro
     * @return RenRenovacion
     * @throws java.lang.Exception
     */
    RenRenovacion crudRenRenovacion(RenRenovacion renRenovacion, int parametro) throws Exception;

    /**
     * Método que permite obtener el listado de la entidad RenRenovacion
     *
     * @author Chano Rojas
     * @see ReciboService
     * @param renRenovacion
     * @param parametro
     * @return RenRenovacion
     * @throws java.lang.Exception
     */
    List<RenRenovacion> obtenerListadoRenRenovacion(RenRenovacion renRenovacion, int parametro) throws Exception;

    /**
     * Método que permite obtener un registro de RenRenovacion poridrensolicitud
     *
     * @author Chano Rojas
     * @see ReciboService
     * @param idRenSoicitdRenovacion
     * @return RenRenovacion
     * @throws java.lang.Exception
     */
    RenRenovacion obtieneRenovacionPorIdSolicitud(Long idRenSoicitdRenovacion);

    /**
     * Método que permite obtener el literal de un numero usado para el campo de
     * orden de renovacion
     *
     * @author Chano Rojas
     * @see ReciboService
     * @param numeroRenovacion
     * @return RenRenovacion
     *
     */
    String numeroOrdinal(int numeroRenovacion);

    /**
     * Método que permite calcular el numero de renovacion orden de renovacion
     *
     * @author Chano Rojas
     * @param fechaRegistro
     * @see ReciboService
     * @return RenRenovacion
     *
     */
    int calculaNumeroDeRenovacion(Date fechaRegistro);

    /**
     * Método que permite calcular el numero de renovacion orden de renovacion
     *
     * @author Chano Rojas
     * @param fechaRegistro
     * @param fechaIngresoRenovacion
     * @see ReciboService
     * @return RenRenovacion
     *
     */
    public int calculaNumeroDeRenovacionSegunFechaIngresoRenovacion(Date fechaRegistro, Date fechaIngresoRenovacion);

    /**
     * Método que permite calcular de fechadeConcesion
     *
     * @author Chano Rojas
     * @param fechaRegistro
     * @throws java.text.ParseException
     * @see ReciboService
     * @return RenRenovacion
     *
     */
    Date devuelveFechaConcesion(Date fechaRegistro) throws Exception;

    /**
     * Método que permite calcular de fechadeConcesion
     *
     * @author Chano Rojas
     * @param fechaRegistro
     * @param fechasolicitud
     * @throws java.text.ParseException
     * @see ReciboService
     * @return RenRenovacion
     *
     */

    Date devuelveFechaConcesionFechaSolicitud(Date fechaRegistro, Date fechasolicitud) throws Exception;

    /**
     * Método que permite calcular de fechadeConcesion
     *
     * @author Chano Rojas
     * @param renSolicitudRenovacion
     * @param renRenovacion
     * @param renResolucion
     * @param usuario
     * @throws java.text.ParseException
     * @see ReciboService
     * @return RenRenovacion
     *
     */

    RenRenovacion guardaRenovacion(RenSolicitudRenovacion renSolicitudRenovacion, RenRenovacion renRenovacion, RenResolucion renResolucion, Usuario usuario) throws Exception;

    /**
     * Method descripcion del mes en literal
     *
     * @author Chano Rojas
     * @param mesFecha
     * @return
     */
    String devuelvemesLiteral(int mesFecha
    );

    /**
     * Método que permite calcular de fechadeConcesion
     *
     * @author Chano Rojas
     * @param filtro
     * @param textoBusquedaSimple
     * @see ReciboService
     * @return RenRenovacion
     *
     */
    List<RenRenovacion> lista_renRenovacion_simple(String filtro, String textoBusquedaSimple
    );

    /**
     * Método que permite calcular de fechadeConcesion
     *
     * @author Chano Rojas
     * @param filtro
     * @param textoBusquedaSimple
     * @throws java.lang.Exception
     * @see ReciboService
     * @return ListaRenRenovacion
     *
     */
    List<RenSolicitudRenovacion> lista_renRenovacion_simpleSolicitudes(String filtro, String textoBusquedaSimple) throws Exception;

    /**
     * Método que permite calcular de fechadeConcesion
     *
     * @author Chano Rojas
     * @param filtro
     * @param numeroRegistro
     * @param serie
     * @throws java.lang.Exception
     * @see ReciboService
     * @return ListaRenRenovacion
     *
     */
    public List<RenSolicitudRenovacion> lista_renRenovacion_simpleRegistro(String filtro, int numeroRegistro, String serie) throws Exception;

    /**
     * Método que permite calcular de fechadeConcesion
     *
     * @author Chano Rojas
     * @param numeroRenovacion
     * @param serieRenovacion
     * @throws java.lang.Exception
     * @see ReciboService
     * @return RenRenovacion
     */
    RenRenovacion obtieneRenRenovacionPorNumeroYSerieRenovacion(int numeroRenovacion, String serieRenovacion) throws Exception;

    /**
     * Método que permite calcular de fechadeConcesion
     *
     * @author Chano Rojas
     * @param sigSignoMarca
     * @throws java.lang.Exception
     * @see ReciboService
     * @return RenRenovacion
     *
     */
    List<RenRenovacion> obtieneListRenovaionPorSm(SigSignoMarca sigSignoMarca) throws Exception;

    /**
     * Método que permite verificar si la renovacion tiene numero renovacion
     * Creado: Placido Castro Fecha: 27/03/2017
     *
     * @param numeroRenovacion
     * @param gestionRenovacion
     * @throws java.lang.Exception
     * @return RenRenovacion
     * @version 1.0, 25/03/2017
     */
    RenRenovacion obtieneRenRenovacionPorNumeroYgestion(long numeroRenovacion, int gestionRenovacion) throws Exception;
    /**
     * Método que permite verificar si la renovacion tiene numero renovacion
     * Creado: Placido Castro Fecha: 27/03/2017
     *
     * @param numeroRenovacion
     * @param gestionRenovacion
     * @throws java.lang.Exception
     * @return RenRenovacion
     * @version 1.0, 25/03/2017
     */

}
