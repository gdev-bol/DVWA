/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.servicio;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.sql.DataSource;
import snp.gob.bo.gimodel.entidad.Deposito;
import snp.gob.bo.gimodel.entidad.Recibo;
import snp.gob.bo.gimodel.entidad.Regional;
import snp.gob.bo.gimodel.entidad.Tasa;
import snp.gob.bo.gimodel.entidad.Usuario;

/**
 *
 * @author Chano Rojas
 * @see ReciboService
 * @see ReciboService
 * @version 1.0, 05/06/2016
 */
public interface ReciboService {

    /**
     * Método que permite setear la conexion con dataSource
     *
     * @param dataSource
     * @throws java.lang.Exception
     */
    public void setDataSource(DataSource dataSource) throws Exception;

    /**
     * Método que permite realizar abm en la tabla Recibo
     *
     * @param recibo
     * @param parametro
     * @return
     * @throws java.lang.Exception
     */
    Recibo crudRecibo(Recibo recibo, int parametro) throws Exception;

    /**
     * Método que permite realizar abm en la tabla Recibo
     *
     * @param recibo
     * @param parametro
     * @return
     * @throws java.lang.Exception
     */
    List<Recibo> listaRecibo(Recibo recibo, int parametro) throws Exception;

    /**
     * Método que permite realizar la generacion de recibos
     *
     * @param recibo
     * @param tasa
     * @param listaDepositos
     * @param regional
     * @param usuario
     * @param cantidad
     * @param reciboRescatado
     * @param tituloRescatado
     * @param activaRecibo
     * @param activaTitulo
     * @throws java.lang.Exception
     */
    void guardaReciboDepositoTramiteCantidad(Recibo recibo, Tasa tasa, List<Deposito> listaDepositos, Regional regional, Usuario usuario, int cantidad, Recibo reciboRescatado, Recibo tituloRescatado, Boolean activaRecibo, Boolean activaTitulo) throws Exception;

    public Recibo modificaRecibo(Recibo reciboBuscar, List<Deposito> lstDesposito, Usuario usuario, Regional regional, int cantidad, Tasa tasa) throws Exception;
    
    
    Recibo guardaReciboDepositoTramiteCantidadRecibo(Recibo recibo, Tasa tasa, List<Deposito> listaDepositos, Regional regional, Usuario usuario, int cantidad, Recibo reciboRescatado, Recibo tituloRescatado, Boolean activaRecibo, Boolean activaTitulo) throws Exception;

    List<Recibo> listaReciboEmitido() throws Exception;

    public List<Recibo> listaReciboEmitidoPorFechas(Date fechaBuscar, Usuario usuario) throws Exception;

    List<Recibo> listaReciboEmitidoPorFecha(Date fechaBuscar, Usuario usuario);

    List<Recibo> listaReciboEmitidoPorFechaYUsuario(Date fechaBuscar, Usuario usuario);

    Boolean validaRegistroDeRecibos(Recibo recibo);

    Recibo buscaReciboPorIdRecibo(Long idrecibo) throws Exception;

    void eliminaRecibo(Recibo recibo) throws Exception;

    Recibo buscaRecibosActivos(Long idrecibo) throws Exception;

    void anularRecibo(Recibo recibo) throws Exception;

    Recibo buscaReciboPorNumeroYSerieRecibo(int numeroRecibo, String serieRecibo) throws Exception;

    Recibo buscaReciboPorNumeroYSerieTitulo(int numeroTitulo, String serieTitulo) throws Exception;

    /**
     * Método que permite listar los recibos en una fecha determinada.
     *
     * @author Ruben Ramirez
     * @version 1.0, 23/12/2016
     * @param fechaBuscar
     * @see ReciboServiceImpl
     * @return
     * @throws java.lang.Exception
     */
    List<Recibo> listaReciboPorFecha(Date fechaBuscar) throws Exception;

    /**
     * Método que permite listar los recibos en una fecha determinada y el id
     * usuario.
     *
     * @author Ruben Ramirez
     * @version 1.0, 06/04/2017
     * @param fechaBuscar
     * @param idusuario
     * @see ReciboServiceImpl
     * @return
     * @throws java.lang.Exception
     */
    List<Recibo> listaReciboPorFechaPorIdUsuario(Date fechaBuscar, Long idusuario) throws Exception;

    /**
     * Método que permite listar los recibos en una fecha determinada y por el
     * id regional.
     *
     * @author Ruben Ramirez
     * @version 1.0, 06/04/2017
     * @param fechaBuscar
     * @param idregional
     * @see ReciboServiceImpl
     * @return
     * @throws java.lang.Exception
     */
    List<Recibo> listaReciboPorFechaPorRegional(Date fechaBuscar, Long idregional) throws Exception;

    /**
     * Método que permite listar los recibos en una fecha determinada.
     *
     * @author Chano Rojas
     * @version 1.0, 23/12/2016
     * @param fechaBuscar
     * @see ReciboServiceImpl
     * @return
     * @throws java.lang.Exception
     */
    /**
     * Método que permite listar los recibos en una fecha determinada.
     *
     * @author Chano Rojas
     * @version 1.0, 23/12/2016
     * @param numeroRecibo
     * @param serieRecibo
     * @see ReciboServiceImpl
     * @return
     * @throws java.lang.Exception
     */
    Recibo buscaReciboPorNumeroYSerieReciboSinEstado(int numeroRecibo, String serieRecibo) throws Exception;

    /**
     * Método que permite listar los recibos en una fecha determinada.
     *
     * @author Chano Rojas
     * @version 1.0, 23/12/2016
     * @param idDeposito
     * @see ReciboServiceImpl
     * @return
     * @throws java.lang.Exception
     */
    public List<Recibo> listaReciboPorDeposito(long idDeposito) throws Exception;

    /**
     * Método que permite obtener el total del monto de recibos en una fecha
     * determinada.
     *
     * @author Ruben Ramirez
     * @version 1.0, 23/12/2016
     * @param fechaBuscar
     * @see ReciboServiceImpl
     * @return
     * @throws java.lang.Exception
     */
    BigDecimal totalMontoReciboPorFecha(Date fechaBuscar) throws Exception;

    /**
     * Método que permite obtener el total del monto de recibos en una fecha
     * determinada.
     *
     * @author Chano Rojas
     * @version 1.0, 23/12/2016
     * @param tasa
     * @param listaDepositos
     * @param cantidad
     * @see ReciboServiceImpl
     * @return
     * @throws java.lang.Exception
     */
    Boolean activaGuardadoReciboCantidad(Tasa tasa, List<Deposito> listaDepositos, int cantidad) throws Exception;

}
