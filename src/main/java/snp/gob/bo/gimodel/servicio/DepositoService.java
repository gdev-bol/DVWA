/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.servicio;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import snp.gob.bo.gimodel.entidad.Deposito;
import snp.gob.bo.gimodel.entidad.Recibo;
import snp.gob.bo.gimodel.entidad.ReciboDeposito;
import snp.gob.bo.gimodel.entidad.Tasa;
import snp.gob.bo.gimodel.entidad.Usuario;

/**
 *
 * @author Chano Rojas
 * @see ReciboService
 * @see ReciboService
 * @version 1.0, 05/06/2016
 */
public interface DepositoService {

    /**
     * Método que permite setear la conexion con dataSource
     *
     * @param dataSource
     * @throws java.lang.Exception
     */
    public void setDataSource(DataSource dataSource) throws Exception;

    /**
     * Método que permite setear la conexion con dataSource
     *
     * @param dataSource
     * @throws java.lang.Exception
     */
    /**
     * Método que permite setear la conexion con dataSource
     *
     * @param deposito
     * @param parametro
     * @return
     * @throws java.lang.Exception
     */
    Deposito crudDeposito(Deposito deposito, int parametro) throws Exception;

    /**
     * Método que permite listar la tabla Dosificacion
     *
     * @param deposito
     * @param parametro
     * @return
     * @throws java.lang.Exception
     */
    List<Deposito> lstDeposito(Deposito deposito, int parametro) throws Exception;

    List<Deposito> listaDepositoConSaldoPorUSuario(Usuario usuario) throws Exception;

    List<Deposito> listaDepositoConSaldoPorUSuarioSaldo(Usuario usuario);

    List<Deposito> listaDepositoConSaldo();

    BigDecimal actualizaSaldoDepositoPorTasa(Tasa tasa, Deposito deposito);

    BigDecimal actualizaSaldoDepositoModificar(Recibo rec, Deposito depos);

    ReciboDeposito encuentrareciboDepositoPorIdreciboyIdDeposito(Recibo rec, Deposito deposito);

    List<Deposito> generalistaDepositosConSaldo(List<Deposito> listaDepositos);

    /**
     *
     * @param recibo
     * @return
     * @throws Exception
     */
    String encuentraDepositantePorRecibo(Recibo recibo) throws Exception;

    String encuentraFechaDespositoPorRecibo(Recibo recibo) throws Exception;

    String encuentraCodigoDepositoPorRecibo(Recibo recibo) throws Exception;

    String encuentraDepositosPorRecibo(Recibo recibo) throws Exception;

    List<Deposito> depositosPorRecibo(Recibo recibo) throws Exception;

    int encuentraPosicionDeposito(Deposito deposito, List<Deposito> lstdeposito) throws Exception;

    Boolean depositoParaModificarEliminar(Deposito deposito) throws Exception;

    Deposito depositoPorIdDeposto(Long idDeposito);

    /**
     * Método que permite listar la tabla Deposito de acuerdo al id de recibo
     *
     * @author Ruben Ramirez
     * @version 1.0, 14/12/2016
     * @see DepositoServiceImpl
     * @param recibo
     * @return
     * @throws java.lang.Exception
     */
    List<Deposito> listaDepositosPorRecibo(Recibo recibo) throws Exception;

    /**
     * Método que permite listar la tabla Deposito de acuerdo a un rango de
     * fechas determninada.
     *
     * @author Ruben Ramirez
     * @version 1.0, 16/12/2016
     * @param ini
     * @param fin
     * @see DepositoServiceImpl
     * @return
     * @throws java.lang.Exception
     */
    List<Deposito> listaDepositosPorRangoDeFechas(Date ini, Date fin) throws Exception;

    /**
     * Método que permite listar la tabla Deposito de acuerdo a un rango de
     * fechas determninada y por id usuario.
     *
     * @author Ruben Ramirez
     * @version 1.0, 07/04/2017
     * @param ini
     * @param fin
     * @param idUsuario
     * @see DepositoServiceImpl
     * @return
     * @throws java.lang.Exception
     */
    List<Deposito> listaDepositosPorRangoDeFechasPorIdUsuario(Date ini, Date fin, Long idUsuario) throws Exception;

    /**
     * Método que permite listar la tabla Deposito de acuerdo a un rango de
     * fechas determninada y por id reginal.
     *
     * @author Ruben Ramirez
     * @version 1.0, 07/04/2017
     * @param ini
     * @param fin
     * @param idRegional
     * @see DepositoServiceImpl
     * @return
     * @throws java.lang.Exception
     */
    List<Deposito> listaDepositosPorRangoDeFechasPorIdRegional(Date ini, Date fin, Long idRegional) throws Exception;

    /**
     * Método que permite listar la tabla Deposito de acuerdo a un rango de
     * fechas determninada y por deps_cod_dep (agencia).
     *
     * @author Ruben Ramirez
     * @version 1.0, 07/04/2017
     * @param ini
     * @param fin
     * @param agencia
     * @see DepositoServiceImpl
     * @return
     * @throws java.lang.Exception
     */
    List<Deposito> listaDepositosPorRangoDeFechasPorAgencia(Date ini, Date fin, Integer agencia) throws Exception;

    /**
     * Método que permite obtener el total del monto en un rango de fechas
     * determinadas.
     *
     * @author Ruben Ramirez
     * @version 1.0, 16/12/2016
     * @param ini
     * @param fin
     * @see DepositoServiceImpl
     * @return
     * @throws java.lang.Exception
     */
    BigDecimal totalMontoPorRangoDeFechas(Date ini, Date fin) throws Exception;

    /**
     * Método que permite obtener el total del monto en un rango de fechas
     * determinadas.
     *
     * @author Ruben Ramirez
     * @version 1.0, 16/12/2016
     * @param deposito
     * @see DepositoServiceImpl
     * @return
     */
    Boolean validaNumeroDeposito(Deposito deposito);

}