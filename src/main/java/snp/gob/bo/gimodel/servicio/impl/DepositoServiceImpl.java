/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.servicio.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import snp.gob.bo.gimodel.entidad.Deposito;
import snp.gob.bo.gimodel.entidad.Recibo;
import snp.gob.bo.gimodel.entidad.ReciboDeposito;
import snp.gob.bo.gimodel.entidad.Tasa;
import snp.gob.bo.gimodel.entidad.Usuario;
import snp.gob.bo.gimodel.mapper.DepositoMapper;
import snp.gob.bo.gimodel.mapper.ReciboDepositoMapper;
import snp.gob.bo.gimodel.servicio.DepositoService;
import snp.gob.bo.gimodel.servicio.ReciboService;

/**
 *
 * @author Chano Rojas
 * @see ReciboDominio
 * @see ReciboServiceImpl
 * @version 1.0, 05/06/2016
 */
@Service("depositoService")
public class DepositoServiceImpl implements DepositoService {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    ReciboService reciboService;

    @Override
    @Autowired
    public void setDataSource(DataSource dataSource) throws Exception {
        try {
            this.dataSource = dataSource;
            this.jdbcTemplate = new JdbcTemplate(dataSource);
        } catch (Exception e) {
            throw e;
        }
    }

//
    @Override
    public Deposito crudDeposito(Deposito deposito, int parametro) throws Exception {
        try {
            Deposito dep = new Deposito();
            String SQL = "select * from crud_deposito("
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?);";
            dep = (Deposito) jdbcTemplate.queryForObject(SQL, new DepositoMapper(),
                    deposito.getIdDeposito(),
                    deposito.getIdLogTrans(),
                    deposito.getBanco(),
                    deposito.getNumeroDeposito(),
                    deposito.getFechaDeposito(),
                    deposito.getMonto(),
                    deposito.getDeposCodDep(),
                    deposito.getDeposCodAgencia(),
                    deposito.getNombreDepositante(),
                    deposito.getCodAgencia(),
                    deposito.getCodDepositante(),
                    deposito.getSaldo(),
                    deposito.getFechaRegistroDeposito(),
                    deposito.getSucursalBanco(),
                    deposito.getEstado(),
                    deposito.getIdUsuario(),
                    parametro
            );
            return dep;
        } catch (DataAccessException e) {
            throw e;
        }
    }

    @Override
    public List<Deposito> lstDeposito(Deposito deposito, int parametro) throws Exception {
        try {
            List<Deposito> lstdep = new ArrayList<Deposito>();
            String SQL = "select * from crud_deposito("
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?);";
            lstdep = (List<Deposito>) (Deposito) jdbcTemplate.query(SQL, new DepositoMapper(),
                    deposito.getIdDeposito(),
                    deposito.getIdLogTrans(),
                    deposito.getBanco(),
                    deposito.getNumeroDeposito(),
                    deposito.getFechaDeposito(),
                    deposito.getMonto(),
                    deposito.getDeposCodDep(),
                    deposito.getDeposCodAgencia(),
                    deposito.getNombreDepositante(),
                    deposito.getCodAgencia(),
                    deposito.getCodDepositante(),
                    deposito.getSaldo(),
                    deposito.getFechaRegistroDeposito(),
                    deposito.getSucursalBanco(),
                    deposito.getEstado(),
                    deposito.getIdUsuario(),
                    parametro
            );
            if (!lstdep.isEmpty()) {
                return lstdep;
            }
            return Collections.EMPTY_LIST;
        } catch (DataAccessException e) {
            throw e;
        }
    }

    @Override
    public List<Deposito> listaDepositoConSaldoPorUSuario(Usuario usuario) throws Exception {
        try {
            String SQL = "select * from deposito where estado='AC' and idlogtrans=? order by iddeposito DESC";
            if (!jdbcTemplate.query(SQL, new DepositoMapper(), usuario.getIdusuario()).isEmpty()) {
                return jdbcTemplate.query(SQL, new DepositoMapper(), usuario.getIdusuario());
            }
        } catch (Exception e) {
            throw e;
        }
        return Collections.EMPTY_LIST;
    }

    @Override
    public List<Deposito> listaDepositoConSaldoPorUSuarioSaldo(Usuario usuario) {
        String SQL = "select * from deposito where estado='AC' and saldo > 0 and idusuario=? order by iddeposito DESC";
        if (!jdbcTemplate.query(SQL, new DepositoMapper(), usuario.getIdusuario()).isEmpty()) {
            return jdbcTemplate.query(SQL, new DepositoMapper(), usuario.getIdusuario());
        }
        return Collections.EMPTY_LIST;
    }

    @Override
    public List<Deposito> listaDepositoConSaldo() {
        String SQL = "select * from deposito where estado='AC' and saldo <> 0 order by iddeposito DESC";

        if (!jdbcTemplate.query(SQL, new DepositoMapper()).isEmpty()) {
            return jdbcTemplate.query(SQL, new DepositoMapper());
        }
        return Collections.EMPTY_LIST;
    }

    @Override
    public BigDecimal actualizaSaldoDepositoPorTasa(Tasa tasa, Deposito deposito) {
        BigDecimal montoSaldo = new BigDecimal("0.00");
        if (deposito.getSaldo().compareTo(tasa.getCosto()) == 1) {
            BigDecimal resto = deposito.getSaldo().subtract(tasa.getCosto());
            if (resto.compareTo(new BigDecimal("0.00")) == 1) {

                montoSaldo = deposito.getSaldo().subtract(tasa.getCosto());
            } else {
                montoSaldo = new BigDecimal("-1");
            }

        } else {
            montoSaldo = new BigDecimal("-1");
        }
        return montoSaldo;
    }

    @Override
    public BigDecimal actualizaSaldoDepositoModificar(Recibo rec, Deposito deposito) {
        String SQL = "select * from recibodeposito where iddeposito='" + deposito.getIdDeposito() + "'";
        List<ReciboDeposito> reciboDep = jdbcTemplate.query(SQL, new ReciboDepositoMapper());
        
        BigDecimal resultado= new BigDecimal(BigInteger.ZERO);
        for (ReciboDeposito reciboDep1 : reciboDep) {
            resultado=resultado.add(reciboDep1.getMonto());
        }
        return resultado;

    }

    @Override
    public ReciboDeposito encuentrareciboDepositoPorIdreciboyIdDeposito(Recibo rec, Deposito deposito) {
        String SQL = "select * from recibodeposito where idrecibo='" + rec.getIdRecibo() + "' and iddeposito='" + deposito.getIdDeposito() + "'";
        List<ReciboDeposito> reciboDep = jdbcTemplate.query(SQL, new ReciboDepositoMapper());
        return reciboDep.get(0);

    }

    @Override
    public List<Deposito> generalistaDepositosConSaldo(List<Deposito> listaDepositos) {
        List<Deposito> listaFinal = new ArrayList<Deposito>();
        for (Deposito deposito : listaDepositos) {
            if (deposito.getSaldo().compareTo(BigDecimal.ZERO) > 0) {
                listaFinal.add(deposito);
            }
        }
        return listaFinal;
    }

    @Override
    public String encuentraDepositosPorRecibo(Recibo recibo) throws Exception {
        try {
            String SQL = "select * from deposito where iddeposito in(select iddeposito from recibodeposito where idrecibo=?); ";
            String cadenaDepositos = "";
            List<Deposito> lista = jdbcTemplate.query(SQL, new DepositoMapper(), recibo.getIdRecibo());
            if (!lista.isEmpty()) {
                for (int i = 0; i < lista.size(); i++) {
                    if (i == 0) {
                        cadenaDepositos = cadenaDepositos + lista.get(i).getNumeroDeposito();
                    } else {
                        cadenaDepositos = cadenaDepositos + "," + lista.get(i).getNumeroDeposito();
                    }
                }
            }
            return cadenaDepositos;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public String encuentraDepositantePorRecibo(Recibo recibo) throws Exception {
        try {
            String SQL = "select * from deposito where iddeposito in(select iddeposito from recibodeposito where idrecibo=?); ";
            String cadenaDepositos = "";
            List<Deposito> lista = jdbcTemplate.query(SQL, new DepositoMapper(), recibo.getIdRecibo());
            if (!lista.isEmpty()) {
                for (int i = 0; i < lista.size(); i++) {
                    if (i == 0) {
                        cadenaDepositos = cadenaDepositos + lista.get(i).getNombreDepositante();
                    } else {
                        cadenaDepositos = cadenaDepositos + "," + lista.get(i).getNombreDepositante();
                    }
                }
            }
            return cadenaDepositos;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public String encuentraFechaDespositoPorRecibo(Recibo recibo) throws Exception {
        try {
            String SQL = "select * from deposito where iddeposito in(select iddeposito from recibodeposito where idrecibo=?); ";
            String cadenaDepositos = "";
            List<Deposito> lista = jdbcTemplate.query(SQL, new DepositoMapper(), recibo.getIdRecibo());
            if (!lista.isEmpty()) {
                DateFormat fecha = new SimpleDateFormat("dd/MM/yyyy");
                for (int i = 0; i < lista.size(); i++) {
                    if (i == 0) {
                        cadenaDepositos = cadenaDepositos + fecha.format(lista.get(i).getFechaDeposito());
                    } else {
                        cadenaDepositos = cadenaDepositos + "," + fecha.format(lista.get(i).getFechaDeposito());
                    }
                }
            }
            return cadenaDepositos;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public String encuentraCodigoDepositoPorRecibo(Recibo recibo) throws Exception {
        try {
            String SQL = "select * from deposito where iddeposito in(select iddeposito from recibodeposito where idrecibo=?); ";
            String cadenaDepositos = "";
            List<Deposito> lista = jdbcTemplate.query(SQL, new DepositoMapper(), recibo.getIdRecibo());
            if (!lista.isEmpty()) {
                for (int i = 0; i < lista.size(); i++) {
                    if (i == 0) {
                        cadenaDepositos = cadenaDepositos + lista.get(i).getDeposCodDep();
                    } else {
                        cadenaDepositos = cadenaDepositos + "," + lista.get(i).getDeposCodDep();
                    }
                }
            }
            return cadenaDepositos;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<Deposito> depositosPorRecibo(Recibo recibo) throws Exception {
        try {
            String SQL = "select * from deposito where iddeposito in(select iddeposito from recibodeposito where idrecibo=?); ";
            String cadenaDepositos = "";
            List<Deposito> lista = jdbcTemplate.query(SQL, new DepositoMapper(), recibo.getIdRecibo());
            if (!lista.isEmpty()) {
                for (int i = 0; i < lista.size(); i++) {
                    if (i == 0) {
                        cadenaDepositos = cadenaDepositos + lista.get(i).getNumeroDeposito();
                    } else {
                        cadenaDepositos = cadenaDepositos + "," + lista.get(i).getNumeroDeposito();
                    }
                }
            }
            return lista;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public int encuentraPosicionDeposito(Deposito deposito, List<Deposito> lstDeposito) throws Exception {
        try {
            int posicion = 0;
            int cont = 0;
            for (Deposito deposito1 : lstDeposito) {
                if (Objects.equals(deposito1.getNumeroDeposito(), deposito.getNumeroDeposito())) {
                    posicion = cont;
                }
                cont++;
            }
            return posicion;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Boolean depositoParaModificarEliminar(Deposito deposito) throws Exception {
        try {
            String Sql = "select * from recibodeposito where iddeposito=?";
            Recibo recibo = reciboService.buscaReciboPorIdRecibo(jdbcTemplate.query(Sql, new ReciboDepositoMapper(), deposito.getIdDeposito()).get(0).getIdRecibo());
            if (reciboService.buscaRecibosActivos(recibo.getIdRecibo()) != null) {
                return true;
            }
        } catch (Exception ex) {
        }
        return false;
    }

    @Override
    public Deposito depositoPorIdDeposto(Long idDeposito) {
        String SQL = "select * from deposito where estado='AC' and iddeposito=?";
        List<Deposito> listaDeposito = jdbcTemplate.query(SQL, new DepositoMapper(), idDeposito);
        if (!listaDeposito.isEmpty()) {
            return listaDeposito.get(0);
        }
        return new Deposito();
    }

    @Override
    public List<Deposito> listaDepositosPorRecibo(Recibo recibo) throws Exception {
        try {
            String SQL = "select * from deposito where estado='AC' and iddeposito in(select iddeposito from recibodeposito where idrecibo=?); ";
            if (!jdbcTemplate.query(SQL, new DepositoMapper(), recibo.getIdRecibo()).isEmpty()) {
                return jdbcTemplate.query(SQL, new DepositoMapper(), recibo.getIdRecibo());
            }
        } catch (Exception e) {
            throw e;
        }
        return Collections.EMPTY_LIST;
    }

    @Override
    public List<Deposito> listaDepositosPorRangoDeFechas(Date ini, Date fin) throws Exception {
        try {
            SimpleDateFormat formatoIni = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
            SimpleDateFormat formatoFin = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
            String fechaIni;
            String fechaFin;
            if (ini != null && fin != null) {
                fechaIni = formatoIni.format(ini);
                fechaFin = formatoFin.format(fin);
                String SQL = "select * from deposito where fecha_registro_deposito between '" + fechaIni + "' and '" + fechaFin + "' and estado = 'AC';";
                List<Deposito> listaDeposito = jdbcTemplate.query(SQL, new DepositoMapper());
                if (!listaDeposito.isEmpty()) {
                    return listaDeposito;
                } else {
                    return Collections.EMPTY_LIST;
                }
            } else {
                return Collections.EMPTY_LIST;
            }
        } catch (Exception e) {
        }
        return Collections.EMPTY_LIST;
    }

    @Override
    public List<Deposito> listaDepositosPorRangoDeFechasPorIdUsuario(Date ini, Date fin, Long idUsuario) throws Exception {
        try {
            SimpleDateFormat formatoIni = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
            SimpleDateFormat formatoFin = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
            String fechaIni;
            String fechaFin;
            if (ini != null && fin != null) {
                fechaIni = formatoIni.format(ini);
                fechaFin = formatoFin.format(fin);
                String SQL = "select * from deposito where fecha_registro_deposito between '" + fechaIni + "' and '" + fechaFin + "' and idusuario = " + idUsuario + " and estado = 'AC';";
                List<Deposito> listaDeposito = jdbcTemplate.query(SQL, new DepositoMapper());
                if (!listaDeposito.isEmpty()) {
                    return listaDeposito;
                } else {
                    return Collections.EMPTY_LIST;
                }
            } else {
                return Collections.EMPTY_LIST;
            }
        } catch (Exception e) {
        }
        return Collections.EMPTY_LIST;
    }

    @Override
    public List<Deposito> listaDepositosPorRangoDeFechasPorIdRegional(Date ini, Date fin, Long idRegional) throws Exception {
        try {
            SimpleDateFormat formatoIni = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
            SimpleDateFormat formatoFin = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
            String fechaIni;
            String fechaFin;
            if (ini != null && fin != null) {
                fechaIni = formatoIni.format(ini);
                fechaFin = formatoFin.format(fin);
                String SQL = "select * from deposito where iddeposito in \n"
                        + "(select rd.iddeposito\n"
                        + "from recibo r, recibodeposito rd\n"
                        + "where r.idrecibo = rd.idrecibo and r.estado_recibo='REMI'  and r.fecha_emision_recibo between '" + fechaIni + "' and '" + fechaFin + "' and r.idregional = " + idRegional + ") and estado = 'AC';";
                List<Deposito> listaDeposito = jdbcTemplate.query(SQL, new DepositoMapper());
                if (!listaDeposito.isEmpty()) {
                    return listaDeposito;
                } else {
                    return Collections.EMPTY_LIST;
                }
            } else {
                return Collections.EMPTY_LIST;
            }
        } catch (Exception e) {
        }
        return Collections.EMPTY_LIST;
    }

    @Override
    public BigDecimal totalMontoPorRangoDeFechas(Date ini, Date fin) throws Exception {
        String SQL = "select sum(monto) from deposito where fecha_registro_deposito between ? and ?;";
        return jdbcTemplate.queryForObject(SQL, BigDecimal.class, ini, fin);
    }

    @Override
    public List<Deposito> listaDepositosPorRangoDeFechasPorAgencia(Date ini, Date fin, Integer agencia) throws Exception {
        try {
            SimpleDateFormat formatoIni = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
            SimpleDateFormat formatoFin = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
            String fechaIni;
            String fechaFin;
            if (ini != null && fin != null) {
                fechaIni = formatoIni.format(ini);
                fechaFin = formatoFin.format(fin);
                String SQL = "select * from deposito where fecha_registro_deposito between '" + fechaIni + "' and '" + fechaFin + "' and depos_cod_dep = " + agencia + " and estado = 'AC';";
                List<Deposito> listaDeposito = jdbcTemplate.query(SQL, new DepositoMapper());
                if (!listaDeposito.isEmpty()) {
                    return listaDeposito;
                } else {
                    return Collections.EMPTY_LIST;
                }
            } else {
                return Collections.EMPTY_LIST;
            }
        } catch (Exception e) {
        }
        return Collections.EMPTY_LIST;
    }

    @Override
    public Boolean validaNumeroDeposito(Deposito deposito) {
        Boolean existeDeposito;
        String SQL = "select * from deposito where iddeposito in \n"
                + "(select rd.iddeposito from recibo r, recibodeposito rd where r.idrecibo = rd.idrecibo and r.estado_recibo='REMI')and numero_deposito='" + deposito.getNumeroDeposito() + "' and estado = 'AC';";
        List<Deposito> listaDeposito = jdbcTemplate.query(SQL, new DepositoMapper());
        existeDeposito = !listaDeposito.isEmpty();
        return existeDeposito;
    }

}