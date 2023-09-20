/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.servicio.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import snp.gob.bo.gimodel.entidad.Deposito;
import snp.gob.bo.gimodel.entidad.Dosificacion;
import snp.gob.bo.gimodel.entidad.DosificacionTasa;
import snp.gob.bo.gimodel.entidad.LogTrans;
import snp.gob.bo.gimodel.entidad.ModModificacion;
import snp.gob.bo.gimodel.entidad.Recibo;
import snp.gob.bo.gimodel.entidad.ReciboDeposito;
import snp.gob.bo.gimodel.entidad.Regional;
import snp.gob.bo.gimodel.entidad.RenSolicitudRenovacion;
import snp.gob.bo.gimodel.entidad.Seguimiento;
import snp.gob.bo.gimodel.entidad.SigSignoMarca;
import snp.gob.bo.gimodel.entidad.Tasa;
import snp.gob.bo.gimodel.entidad.Usuario;
import snp.gob.bo.gimodel.enums.EnumEstado;
import snp.gob.bo.gimodel.enums.EnumEstadoMarca;
import snp.gob.bo.gimodel.enums.EnumEstadoModificacion;
import snp.gob.bo.gimodel.enums.EnumEstadoRecibo;
import snp.gob.bo.gimodel.enums.EnumTipoRecibo;
import snp.gob.bo.gimodel.enums.EnumTipoTramite;
import snp.gob.bo.gimodel.enums.EnumTipoTramiteRecibo;
import snp.gob.bo.gimodel.enums.EnumUbicacion;
import snp.gob.bo.gimodel.mapper.DosificacionMapper;
import snp.gob.bo.gimodel.mapper.DosificacionTasaMapper;
import snp.gob.bo.gimodel.mapper.ReciboMapper;
import snp.gob.bo.gimodel.servicio.ComunService;
import snp.gob.bo.gimodel.servicio.DepositoService;
import snp.gob.bo.gimodel.servicio.DosificacionService;
import snp.gob.bo.gimodel.servicio.DosificacionTasaService;
import snp.gob.bo.gimodel.servicio.ExpedienteService;
import snp.gob.bo.gimodel.servicio.FlujoTiempoService;
import snp.gob.bo.gimodel.servicio.LogTransService;
import snp.gob.bo.gimodel.servicio.ModModificacionService;
import snp.gob.bo.gimodel.servicio.ReciboDepositoService;
import snp.gob.bo.gimodel.servicio.ReciboService;
import snp.gob.bo.gimodel.servicio.RegionalService;
import snp.gob.bo.gimodel.servicio.RenSolicitudRenovacionService;
import snp.gob.bo.gimodel.servicio.SeguimientoService;
import snp.gob.bo.gimodel.servicio.SigSignoMarcaService;

/**
 *
 * @author Chano Rojas
 * @see ReciboDominio
 * @see ReciboServiceImpl
 * @version 1.0, 05/06/2016
 */
@Service("reciboService")
public class ReciboServiceImpl implements ReciboService {

    @Autowired
    ComunService comunService;

    @Autowired
    DosificacionTasaService dosificacionTasaService;

    @Autowired
    DosificacionService dosificacionService;

    @Autowired
    RegionalService regionalService;

    @Autowired
    DepositoService depositoService;

    @Autowired
    ReciboDepositoService reciboDepositoService;

    @Autowired
    SigSignoMarcaService sigSignoMarcaService;

    @Autowired
    RenSolicitudRenovacionService renSolicitudRenovacionService;

    @Autowired
    ModModificacionService modModificacionService;

    @Autowired
    SeguimientoService seguimientoService;

    @Autowired
    ExpedienteService expedienteService;

    @Autowired
    LogTransService logTransService;

    @Autowired
    FlujoTiempoService flujoTiempoService;

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

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
    public Recibo crudRecibo(Recibo recibo, int parametro) throws Exception {
        try {
            Recibo ren = new Recibo();
            String SQL = "select * from crud_recibo("
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
            ren = (Recibo) jdbcTemplate.queryForObject(SQL, new ReciboMapper(),
                    recibo.getIdRecibo(),
                    recibo.getIdTasa(),
                    recibo.getIdLogTrans(),
                    recibo.getNumeroRecibo(),
                    recibo.getSerieRecibo(),
                    recibo.getFechaEmisionRecibo(),
                    recibo.getMonto(),
                    recibo.getLiteralMonto(),
                    recibo.getConcepto(),
                    recibo.getDatoRecibo(),
                    recibo.getCantidad(),
                    recibo.getEstadoRecibo(),
                    recibo.getOrigen(),
                    recibo.getEstado(),
                    recibo.getSolicitante(),
                    recibo.getApoderado(),
                    recibo.getNumeroTitulo(),
                    recibo.getSerieTitulo(),
                    recibo.getTipoTitulo(),
                    recibo.getTipoTramiteIngresado(),
                    recibo.getNumeroTramiteIngresado(),
                    recibo.getGestionTramiteIngresado(),
                    recibo.getExtensionTramiteIngresado(),
                    recibo.getClaseIngresado(),
                    recibo.getDescIngresado(),
                    recibo.getApoderadoIngresado(),
                    recibo.getDepIngresado(),
                    recibo.getTituloIngresado(),
                    recibo.getSerieTituloIngresado(),
                    recibo.getTipoTituloIngresado(),
                    recibo.getTramite(),
                    recibo.getNumeroTramite(),
                    recibo.getGestionTramite(),
                    recibo.getClaseTramite(),
                    recibo.getExtensionTramite(),
                    recibo.getDescripcionMarca(),
                    recibo.getExpediente(),
                    recibo.getTipoTramite(),
                    recibo.getSigla(),
                    recibo.getObservacion(),
                    recibo.getIdRegional(),
                    recibo.getIdUsuario(),
                    parametro);
            return ren;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<Recibo> listaRecibo(Recibo recibo, int parametro) throws Exception {
        try {
            List<Recibo> lstren = new ArrayList<Recibo>();
            String SQL = "select * from crud_recibo("
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
            lstren = (List<Recibo>) (Recibo) jdbcTemplate.query(SQL, new ReciboMapper(),
                    recibo.getIdRecibo(),
                    recibo.getIdTasa(),
                    recibo.getIdLogTrans(),
                    recibo.getNumeroRecibo(),
                    recibo.getSerieRecibo(),
                    recibo.getFechaEmisionRecibo(),
                    recibo.getMonto(),
                    recibo.getLiteralMonto(),
                    recibo.getConcepto(),
                    recibo.getDatoRecibo(),
                    recibo.getCantidad(),
                    recibo.getEstadoRecibo(),
                    recibo.getOrigen(),
                    recibo.getEstado(),
                    recibo.getSolicitante(),
                    recibo.getApoderado(),
                    recibo.getNumeroTitulo(),
                    recibo.getSerieTitulo(),
                    recibo.getTipoTitulo(),
                    recibo.getTipoTramiteIngresado(),
                    recibo.getNumeroTramiteIngresado(),
                    recibo.getGestionTramiteIngresado(),
                    recibo.getExtensionTramiteIngresado(),
                    recibo.getClaseIngresado(),
                    recibo.getDescIngresado(),
                    recibo.getApoderadoIngresado(),
                    recibo.getDepIngresado(),
                    recibo.getTituloIngresado(),
                    recibo.getSerieTituloIngresado(),
                    recibo.getTipoTituloIngresado(),
                    recibo.getTramite(),
                    recibo.getNumeroTramite(),
                    recibo.getGestionTramite(),
                    recibo.getClaseTramite(),
                    recibo.getExtensionTramite(),
                    recibo.getDescripcionMarca(),
                    recibo.getExpediente(),
                    recibo.getTipoTramite(),
                    recibo.getSigla(),
                    recibo.getObservacion(),
                    recibo.getIdRegional(),
                    recibo.getIdUsuario(),
                    parametro);
            if (!lstren.isEmpty()) {
                return lstren;
            }
            return Collections.EMPTY_LIST;

        } catch (DataAccessException e) {
            throw e;
        }
    }

    @Transactional
    @Override
    public void guardaReciboDepositoTramiteCantidad(Recibo recibo, Tasa tasa, List<Deposito> listaDepositos, Regional regional, Usuario usuario, int cantidad, Recibo reciboRescatado, Recibo tituloRescatado, Boolean activaRecibo, Boolean activaTitulo) throws Exception {
        try {
            recibo.setIdTasa(tasa.getIdTasa());
            Date fechaSistema = comunService.obtenerFechaHoraServidor(1L);
            //LogTrans logTransGuardado = logTransService.crudLogTrans(new LogTrans(82l, fechaSistema), 1);
            LogTrans logTransGuardado = logTransService.crudLogTrans(new LogTrans(usuario.getIdusuario(), fechaSistema), 1);
            Date fechaRegistro = comunService.obtenerFechaHoraServidor(1L);
            regional = regionalService.obtenerRegionalPorIdRegiona(usuario.getIdregional());
            System.out.println("regional" + regional.getIdRegional());
            System.out.println("idtasa" + tasa.getIdTasa());
            //logtrans para el registro de usuarios
            //  LogTrans logTransGuardado = logTransService.crudLogTrans(new LogTrans(usuario.getIdusuario(), fechaRegistro), 1);
            Long logtrans = logTransGuardado.getIdLogTrans();
            recibo.setIdLogTrans(logTransGuardado.getIdLogTrans());
            recibo.setEstado(EnumEstado.ACTIVO.getCodigo());
            Recibo reciboFinal = new Recibo();
            //fecha del servidor
            BigDecimal sumaDepositos = sumaMontosListado(listaDepositos);
            List<DosificacionTasa> listaDosificacionTasa;
            int posicionMayor = encuentraSaldoMayor(listaDepositos);
            List<Deposito> listaDepositoBase = new ArrayList<Deposito>();
            for (Deposito dep : listaDepositos) {
                if (dep.getIdDeposito() == null) {
                    dep.setFechaRegistroDeposito(fechaRegistro);
                    dep.setEstado(EnumEstado.ACTIVO.getCodigo());
                    dep.setIdUsuario(usuario.getIdusuario());
                    dep.setIdLogTrans(logTransGuardado.getIdLogTrans());
                    listaDepositoBase.add(depositoService.crudDeposito(dep, 1));
                } else {
                    listaDepositoBase.add(dep);
                }
            }

            System.out.println("regional" + regional.getIdRegional());
            if (!dosificacionTasaService.encuentraListaDosificacionTasaPorTasa(tasa, regional).isEmpty()) {
                listaDosificacionTasa = dosificacionTasaService.encuentraListaDosificacionTasaPorTasa(tasa, regional);
            } else {
                Regional regional1 = regionalService.encuentraRegionalSede();
                recibo.setIdRegional(regional1.getIdRegional());
                listaDosificacionTasa = dosificacionTasaService.encuentraListaDosificacionTasaPorTasa(tasa, regional);
            }
            if (!listaDosificacionTasa.isEmpty()) {
                for (DosificacionTasa dosificacionTasa : listaDosificacionTasa) {
                    if (dosificacionTasa.getTipoRecibo().equals(EnumTipoRecibo.RECIBO.getCodigo())) {
                        recibo.setMonto(tasa.getCosto().multiply(new BigDecimal(cantidad)));
                        recibo.setCantidad(cantidad);
                        recibo.setEstadoRecibo(EnumEstadoRecibo.RECIBO_EMITIDO.getCodigo());
                        recibo.setSerieRecibo(dosificacionTasa.getSerie());
                        recibo.setFechaEmisionRecibo(fechaRegistro);
                        recibo.setConcepto(tasa.getDescripcion());
                        recibo.setIdUsuario(usuario.getIdusuario());
                        recibo.setIdLogTrans(logTransGuardado.getIdLogTrans());
//                        if (reciboRescatado != null && reciboRescatado.getNumeroRecibo() != 0) {
//                            recibo.setNumeroRecibo(reciboRescatado.getNumeroRecibo());
//                            Recibo reciboRescatadoClon = (Recibo) reciboRescatado.clone();
//                            reciboRescatadoClon.setEstadoRecibo(EnumEstadoRecibo.RECIBO_REASIGNADO.getCodigo());
//                            crudRecibo(reciboRescatadoClon, 2);
//                        } else {
                        Dosificacion dosificacionRecibo = dosificacionService.encuentraDosificacionPorIdDosificacion(dosificacionTasa.getIdDosificacion());
                        dosificacionRecibo.setSiguiente(dosificacionRecibo.getSiguiente() + 1);
                        dosificacionService.crudDosificacion(dosificacionRecibo, 2);
                        recibo.setNumeroRecibo(new Long(dosificacionRecibo.getSiguiente() - 1));

//                        }
//                        if (!validaNumeroRecibo(recibo, dosificacionRecibo)) {
//                            dosificacionRecibo.setSiguiente(dosificacionRecibo.getSiguiente() + 1);
//                            dosificacionService.crudDosificacion(dosificacionRecibo, 2);
//                            recibo.setNumeroRecibo(new Long(dosificacionRecibo.getSiguiente()-1));
//                        }
                        reciboFinal = crudRecibo(recibo, 1);

//                        if (reciboFinal.getIdRecibo() != null) {
//                            dosificacionService.crudDosificacion(dosificacionRecibo, 2);
//                        }
                        if (listaDosificacionTasa.size() == 1) {
                            if (!tasa.getTipoTramite().equals("NULL")) {
                                if (!tasa.getTipoTramite().equals("OPOS")) {
                                    actualizaReciboIngresado(reciboFinal, usuario, logTransGuardado);
                                }
                            }
                        }
                    }
                    if (dosificacionTasa.getTipoRecibo().equals(EnumTipoRecibo.TITULO.getCodigo())) {
                        Dosificacion dosificacionReciboTitulo = dosificacionService.encuentraDosificacionPorIdDosificacion(dosificacionTasa.getIdDosificacion());
                        if (tituloRescatado != null && tituloRescatado.getTituloIngresado() != 0) {
                            reciboFinal.setTituloIngresado(tituloRescatado.getTituloIngresado());
                            reciboFinal.setSerieTituloIngresado(tituloRescatado.getSerieTituloIngresado());
                            Recibo tituloRescatadoClon = (Recibo) tituloRescatado.clone();
                            if (tituloRescatadoClon.getEstadoRecibo().equals("REEL")) {
                                tituloRescatadoClon.setEstadoRecibo(EnumEstadoRecibo.RECIBO_REASIGNADO.getCodigo());
                            }
                            crudRecibo(tituloRescatadoClon, 2);
                        } else {
                            reciboFinal.setTituloIngresado(new Long(dosificacionReciboTitulo.getSiguiente()));
                            reciboFinal.setSerieTituloIngresado(dosificacionTasa.getSerie());
                        }
                        reciboFinal = crudRecibo(reciboFinal, 2);
                        dosificacionReciboTitulo.setSiguiente(dosificacionReciboTitulo.getSiguiente() + 1);
                        dosificacionService.crudDosificacion(dosificacionReciboTitulo, 2);
                        actualizaTituloIngresado(reciboFinal);
                    }
                }
            }
            for (int i = 0; i < listaDepositoBase.size(); i++) {
                ReciboDeposito reciboDeposito = new ReciboDeposito();
                reciboDeposito.setIdDeposito(listaDepositoBase.get(i).getIdDeposito());
                reciboDeposito.setIdRecibo(reciboFinal.getIdRecibo());
                if (i == posicionMayor) {
                    if (listaDepositoBase.get(i).getSaldo().subtract(sumaDepositos.subtract(reciboFinal.getMonto())).signum() == -1) {

                        reciboDeposito.setMonto((sumaDepositos.subtract(reciboFinal.getMonto())).subtract(listaDepositoBase.get(i).getSaldo()));
                    } else {
                        reciboDeposito.setMonto(listaDepositoBase.get(i).getSaldo().subtract(sumaDepositos.subtract(reciboFinal.getMonto())));
                    }
                    listaDepositoBase.get(i).setSaldo(sumaDepositos.subtract(reciboFinal.getMonto()));
                } else {
                    reciboDeposito.setMonto(listaDepositoBase.get(i).getSaldo());
                    listaDepositoBase.get(i).setSaldo(new BigDecimal("0.00"));
                }
                depositoService.crudDeposito(listaDepositoBase.get(i), 2);
                reciboDepositoService.crudReciboDeposito(reciboDeposito, 1);
            }
//A PARTIR  DE AUQI SE LEA LO DEL SEGUIMIENTO EN SIGSEGUIMIENTO

        } catch (Exception e) {
            throw e;
        }
    }

    public Boolean validaNumeroRecibo(Recibo recibo, Dosificacion dosificacionRecibo) {
        try {
            String SQL = "select * from recibo where numero_recibo='" + recibo.getNumeroRecibo() + "'and idregional='" + recibo.getIdRegional() + "' and estado_recibo='REMI'";
            if (jdbcTemplate.query(SQL, new ReciboMapper()).isEmpty()) {
                System.out.println("true");
                return true;
            }
        } catch (Exception e) {
            throw e;
        }
        System.out.println("false");
        return false;
    }

    @Override
    public Recibo modificaRecibo(Recibo reciboBuscar, List<Deposito> lstDesposito, Usuario usuario, Regional regional, int cantidad, Tasa tasa) throws Exception {
        try {
            reciboBuscar.setIdTasa(tasa.getIdTasa());
            Date fechaSistema = comunService.obtenerFechaHoraServidor(1L);
            //LogTrans logTransGuardado = logTransService.crudLogTrans(new LogTrans(82l, fechaSistema), 1);
            LogTrans logTransGuardado = logTransService.crudLogTrans(new LogTrans(usuario.getIdusuario(), fechaSistema), 1);
            Date fechaRegistro = comunService.obtenerFechaServidor(1L);
            regional = regionalService.obtenerRegionalPorIdRegiona(usuario.getIdregional());
            //logtrans para el registro de usuarios
            //  LogTrans logTransGuardado = logTransService.crudLogTrans(new LogTrans(usuario.getIdusuario(), fechaRegistro), 1);
            Long logtrans = logTransGuardado.getIdLogTrans();
            reciboBuscar.setIdLogTrans(logTransGuardado.getIdLogTrans());
            reciboBuscar.setEstado(EnumEstado.ACTIVO.getCodigo());
            Recibo reciboFinal = new Recibo();
            //fecha del servidor
            BigDecimal sumaDepositos = sumaMontosListado(lstDesposito);
            List<DosificacionTasa> listaDosificacionTasa;
            int posicionMayor = encuentraSaldoMayor(lstDesposito);
            List<Deposito> listaDepositoBase = new ArrayList<Deposito>();
            for (Deposito dep : lstDesposito) {
                if (dep.getIdDeposito() == null) {
                    dep.setFechaRegistroDeposito(fechaRegistro);
                    dep.setEstado(EnumEstado.ACTIVO.getCodigo());
                    dep.setIdUsuario(usuario.getIdusuario());
                    dep.setIdLogTrans(logTransGuardado.getIdLogTrans());
                    listaDepositoBase.add(depositoService.crudDeposito(dep, 1));
                } else {
                    dep.setEstado(EnumEstado.ACTIVO.getCodigo());
                    listaDepositoBase.add(depositoService.crudDeposito(dep, 2));
                }
            }

            if (!dosificacionTasaService.encuentraListaDosificacionTasaPorTasa(tasa, regional).isEmpty()) {
                listaDosificacionTasa = dosificacionTasaService.encuentraListaDosificacionTasaPorTasa(tasa, regional);
            } else {
                Regional regional1 = regionalService.encuentraRegionalSede();
                reciboBuscar.setIdRegional(regional1.getIdRegional());
                listaDosificacionTasa = dosificacionTasaService.encuentraListaDosificacionTasaPorTasa(tasa, regional);
            }
            if (!listaDosificacionTasa.isEmpty()) {
                for (DosificacionTasa dosificacionTasa : listaDosificacionTasa) {
                    if (dosificacionTasa.getTipoRecibo().equals(EnumTipoRecibo.RECIBO.getCodigo())) {
//                        Dosificacion dosificacionRecibo = dosificacionService.encuentraDosificacionPorIdDosificacion(dosificacionTasa.getIdDosificacion());
                        reciboBuscar.setMonto(tasa.getCosto().multiply(new BigDecimal(cantidad)));
                        reciboBuscar.setCantidad(cantidad);
//                        reciboBuscar.setEstadoRecibo(EnumEstadoRecibo.RECIBO_EMITIDO.getCodigo());
                        reciboBuscar.setSerieRecibo(dosificacionTasa.getSerie());
//                        reciboBuscar.setFechaEmisionRecibo(fechaRegistro);
                        reciboBuscar.setConcepto(tasa.getDescripcion());
                        reciboBuscar.setIdUsuario(usuario.getIdusuario());
                        reciboBuscar.setIdLogTrans(logTransGuardado.getIdLogTrans());
//                        if (reciboRescatado != null && reciboRescatado.getNumeroRecibo() != 0) {
//                            recibo.setNumeroRecibo(reciboRescatado.getNumeroRecibo());
//                            Recibo reciboRescatadoClon = (Recibo) reciboRescatado.clone();
//                            reciboRescatadoClon.setEstadoRecibo(EnumEstadoRecibo.RECIBO_REASIGNADO.getCodigo());
//                            crudRecibo(reciboRescatadoClon, 2);
//                        } else {
//                        reciboBuscar.setNumeroRecibo(new Long(dosificacionRecibo.getSiguiente()));
//                        dosificacionRecibo.setSiguiente(dosificacionRecibo.getSiguiente() + 1);
//                        }
//                        dosificacionService.crudDosificacion(dosificacionRecibo, 2);
                        reciboFinal = crudRecibo(reciboBuscar, 2);

                    }
                    if (dosificacionTasa.getTipoRecibo().equals(EnumTipoRecibo.TITULO.getCodigo())) {
                        Dosificacion dosificacionReciboTitulo = dosificacionService.encuentraDosificacionPorIdDosificacion(dosificacionTasa.getIdDosificacion());
                        reciboFinal.setTituloIngresado(new Long(dosificacionReciboTitulo.getSiguiente()));
                        reciboFinal.setSerieTituloIngresado(dosificacionTasa.getSerie());
//                        }
                        reciboFinal = crudRecibo(reciboFinal, 2);
//                        dosificacionReciboTitulo.setSiguiente(dosificacionReciboTitulo.getSiguiente() + 1);
//                        dosificacionService.crudDosificacion(dosificacionReciboTitulo, 2);
//                        actualizaTituloIngresado(reciboFinal);
                    }
                }
            }
            for (int i = 0; i < listaDepositoBase.size(); i++) {

                ReciboDeposito reciboDeposito = depositoService.encuentrareciboDepositoPorIdreciboyIdDeposito(reciboBuscar, listaDepositoBase.get(i));
                reciboDeposito.setIdDeposito(listaDepositoBase.get(i).getIdDeposito());
                reciboDeposito.setIdRecibo(reciboFinal.getIdRecibo());
                if (i == posicionMayor) {
                    if (listaDepositoBase.get(i).getSaldo().subtract(sumaDepositos.subtract(reciboFinal.getMonto())).signum() == -1) {

                        reciboDeposito.setMonto((sumaDepositos.subtract(reciboFinal.getMonto())).subtract(listaDepositoBase.get(i).getSaldo()));
                    } else {

                        reciboDeposito.setMonto(listaDepositoBase.get(i).getSaldo().subtract(sumaDepositos.subtract(reciboFinal.getMonto())));
                    }
                    listaDepositoBase.get(i).setSaldo(sumaDepositos.subtract(reciboFinal.getMonto()));
                } else {
                    reciboDeposito.setMonto(listaDepositoBase.get(i).getSaldo());
                    listaDepositoBase.get(i).setSaldo(new BigDecimal("0.00"));
                }
                depositoService.crudDeposito(listaDepositoBase.get(i), 2);
                System.out.println("modifico el deposito");
                reciboDepositoService.crudReciboDeposito(reciboDeposito, 2);
                System.out.println("modifico el recibodeposito");
            }
//A PARTIR  DE AUQI SE LEA LO DEL SEGUIMIENTO EN SIGSEGUIMIENTO
            return reciboFinal;

        } catch (Exception e) {
            throw e;
        }

    }

    @Override
    public Recibo guardaReciboDepositoTramiteCantidadRecibo(Recibo recibo, Tasa tasa, List<Deposito> listaDepositos, Regional regional, Usuario usuario, int cantidad, Recibo reciboRescatado, Recibo tituloRescatado, Boolean activaRecibo, Boolean activaTitulo) throws Exception {
        try {
            recibo.setIdTasa(tasa.getIdTasa());
            Date fechaSistema = comunService.obtenerFechaHoraServidor(1L);
            //LogTrans logTransGuardado = logTransService.crudLogTrans(new LogTrans(82l, fechaSistema), 1);
            LogTrans logTransGuardado = logTransService.crudLogTrans(new LogTrans(usuario.getIdusuario(), fechaSistema), 1);
            Date fechaRegistro = comunService.obtenerFechaServidor(1L);
            regional = regionalService.obtenerRegionalPorIdRegiona(usuario.getIdregional());
            //logtrans para el registro de usuarios
            //  LogTrans logTransGuardado = logTransService.crudLogTrans(new LogTrans(usuario.getIdusuario(), fechaRegistro), 1);
            Long logtrans = logTransGuardado.getIdLogTrans();
            recibo.setIdLogTrans(logTransGuardado.getIdLogTrans());
            recibo.setEstado(EnumEstado.ACTIVO.getCodigo());
            Recibo reciboFinal = new Recibo();
            //fecha del servidor
            BigDecimal sumaDepositos = sumaMontosListado(listaDepositos);
            List<DosificacionTasa> listaDosificacionTasa;
            int posicionMayor = encuentraSaldoMayor(listaDepositos);
            List<Deposito> listaDepositoBase = new ArrayList<Deposito>();
            for (Deposito dep : listaDepositos) {
                if (dep.getIdDeposito() == null) {
                    dep.setFechaRegistroDeposito(fechaRegistro);
                    dep.setEstado(EnumEstado.ACTIVO.getCodigo());
                    dep.setIdUsuario(usuario.getIdusuario());
                    dep.setIdLogTrans(logTransGuardado.getIdLogTrans());
                    listaDepositoBase.add(depositoService.crudDeposito(dep, 1));
                } else {
                    dep.setEstado(EnumEstado.ACTIVO.getCodigo());
                    listaDepositoBase.add(depositoService.crudDeposito(dep, 2));
                }
            }
            if (!dosificacionTasaService.encuentraListaDosificacionTasaPorTasa(tasa, regional).isEmpty()) {
                listaDosificacionTasa = dosificacionTasaService.encuentraListaDosificacionTasaPorTasa(tasa, regional);
            } else {
                Regional regional1 = regionalService.encuentraRegionalSede();
                recibo.setIdRegional(regional1.getIdRegional());
                listaDosificacionTasa = dosificacionTasaService.encuentraListaDosificacionTasaPorTasa(tasa, regional);
            }
            if (!listaDosificacionTasa.isEmpty()) {
                for (DosificacionTasa dosificacionTasa : listaDosificacionTasa) {
                    if (dosificacionTasa.getTipoRecibo().equals(EnumTipoRecibo.RECIBO.getCodigo())) {
                        Dosificacion dosificacionRecibo = dosificacionService.encuentraDosificacionPorIdDosificacion(dosificacionTasa.getIdDosificacion());
                        recibo.setMonto(tasa.getCosto().multiply(new BigDecimal(cantidad)));
                        recibo.setCantidad(cantidad);
                        recibo.setEstadoRecibo(EnumEstadoRecibo.RECIBO_EMITIDO.getCodigo());
                        recibo.setSerieRecibo(dosificacionTasa.getSerie());
                        recibo.setFechaEmisionRecibo(fechaRegistro);
                        recibo.setConcepto(tasa.getDescripcion());
                        recibo.setIdUsuario(usuario.getIdusuario());
                        recibo.setIdLogTrans(logTransGuardado.getIdLogTrans());
                        if (reciboRescatado != null && reciboRescatado.getNumeroRecibo() != 0) {
                            recibo.setNumeroRecibo(reciboRescatado.getNumeroRecibo());
                            Recibo reciboRescatadoClon = (Recibo) reciboRescatado.clone();
                            reciboRescatadoClon.setEstadoRecibo(EnumEstadoRecibo.RECIBO_REASIGNADO.getCodigo());
                            crudRecibo(reciboRescatadoClon, 2);
                        } else {
                            recibo.setNumeroRecibo(new Long(dosificacionRecibo.getSiguiente()));
                            dosificacionRecibo.setSiguiente(dosificacionRecibo.getSiguiente() + 1);
                        }
                        dosificacionService.crudDosificacion(dosificacionRecibo, 2);
                        reciboFinal = crudRecibo(recibo, 1);
                        if (listaDosificacionTasa.size() == 1) {
                            System.out.println("tasa.gettipo tramita" + tasa.getTipoTramite());
                            if (tasa.getTipoTramite() != null) {
                                actualizaReciboIngresado(reciboFinal, usuario, logTransGuardado);
                            }

                        }
                    }
                    if (dosificacionTasa.getTipoRecibo().equals(EnumTipoRecibo.TITULO.getCodigo())) {
                        Dosificacion dosificacionReciboTitulo = dosificacionService.encuentraDosificacionPorIdDosificacion(dosificacionTasa.getIdDosificacion());
                        if (tituloRescatado != null) {
                            reciboFinal.setTituloIngresado(tituloRescatado.getNumeroTitulo());
                            reciboFinal.setSerieTituloIngresado(tituloRescatado.getSerieTitulo());
                            Recibo tituloRescatadoClon = (Recibo) tituloRescatado.clone();
                            tituloRescatadoClon.setEstadoRecibo(EnumEstadoRecibo.RECIBO_REASIGNADO.getCodigo());
                            crudRecibo(tituloRescatadoClon, 2);
                        } else {
                            reciboFinal.setTituloIngresado(new Long(dosificacionReciboTitulo.getSiguiente()));
                            reciboFinal.setSerieTituloIngresado(dosificacionTasa.getSerie());
                        }
                        reciboFinal = crudRecibo(reciboFinal, 2);
                        dosificacionReciboTitulo.setSiguiente(dosificacionReciboTitulo.getSiguiente() + 1);
                        dosificacionService.crudDosificacion(dosificacionReciboTitulo, 2);
                        actualizaTituloIngresado(reciboFinal);
                    }
                }
            }
            for (int i = 0; i < listaDepositoBase.size(); i++) {
                ReciboDeposito reciboDeposito = new ReciboDeposito();
                reciboDeposito.setIdDeposito(listaDepositoBase.get(i).getIdDeposito());
                reciboDeposito.setIdRecibo(reciboFinal.getIdRecibo());
                if (i == posicionMayor) {
                    if (listaDepositoBase.get(i).getSaldo().subtract(sumaDepositos.subtract(reciboFinal.getMonto())).signum() == -1) {

                        reciboDeposito.setMonto((sumaDepositos.subtract(reciboFinal.getMonto())).subtract(listaDepositoBase.get(i).getSaldo()));
                    } else {

                        reciboDeposito.setMonto(listaDepositoBase.get(i).getSaldo().subtract(sumaDepositos.subtract(reciboFinal.getMonto())));
                    }
                    listaDepositoBase.get(i).setSaldo(sumaDepositos.subtract(reciboFinal.getMonto()));
                } else {
                    reciboDeposito.setMonto(listaDepositoBase.get(i).getSaldo());
                    listaDepositoBase.get(i).setSaldo(new BigDecimal("0.00"));
                }
                depositoService.crudDeposito(listaDepositoBase.get(i), 2);
                reciboDepositoService.crudReciboDeposito(reciboDeposito, 1);
            }
//A PARTIR  DE AUQI SE LEA LO DEL SEGUIMIENTO EN SIGSEGUIMIENTO
            return reciboFinal;

        } catch (Exception e) {
            throw e;
        }

    }

    public void actualizaReciboIngresado(Recibo recibo, Usuario usuario, LogTrans logTransGuardado) throws Exception {
        try {
            // sigsignomarca
            if (recibo.getTipoTramiteIngresado().equals("SM")
                    || recibo.getTipoTramiteIngresado().equals("SM-C")
                    || recibo.getTipoTramiteIngresado().equals("SM-S")
                    || recibo.getTipoTramiteIngresado().equals("SM-E")
                    || recibo.getTipoTramiteIngresado().equals("SM-T")
                    || recibo.getTipoTramiteIngresado().equals("SM-H")
                    || recibo.getTipoTramiteIngresado().equals("SM-O")) {
                if ((expedienteService.obtenerSigsignomarca(recibo.getNumeroTramiteIngresado(), recibo.getGestionTramiteIngresado(), "")).getIdSignoMarca() != null) {
                    SigSignoMarca signo = (expedienteService.obtenerSigsignomarca(recibo.getNumeroTramiteIngresado(), recibo.getGestionTramiteIngresado(), ""));
                    signo.setNumeroRecibo(recibo.getNumeroRecibo());
                    signo.setSerie(recibo.getSerieRecibo());

                    sigSignoMarcaService.crudSigSignoMarca(signo, 2);
                } else {

                }
                //renrenovacion
            } else {
                if (renSolicitudRenovacionService.buscaSolicitudRenovacionPorNumeroSrYGestion(Long.parseLong(recibo.getNumeroTramiteIngresado()), recibo.getGestionTramite().intValue()).getIdsolicitudrenovacion() != null) {
                    RenSolicitudRenovacion renSolicitudRenovacionActualizar = renSolicitudRenovacionService.buscaSolicitudRenovacionPorNumeroSrYGestion(Long.parseLong(recibo.getNumeroTramiteIngresado()), Integer.parseInt(recibo.getGestionTramiteIngresado()));
                    renSolicitudRenovacionActualizar.setNumero_recibo(recibo.getNumeroRecibo());
                    renSolicitudRenovacionActualizar.setSerie_recibo(recibo.getSerieRecibo());
                    renSolicitudRenovacionService.cruddosRenSolictudRenovacion(renSolicitudRenovacionActualizar, 2);
                } else {

                }
            }

            //modmodifacion
            if (modModificacionService.buscarModModificacionXCodigo(recibo.getTipoTramiteIngresado(), Long.parseLong(recibo.getNumeroTramiteIngresado()), Integer.parseInt(recibo.getGestionTramiteIngresado())) != null) {
                ModModificacion modModificacion = modModificacionService.buscarModModificacionXCodigo(recibo.getTipoTramiteIngresado(), Long.parseLong(recibo.getNumeroTramiteIngresado()), Integer.parseInt(recibo.getGestionTramiteIngresado()));
                modModificacion.setNro_recibo(recibo.getNumeroRecibo());
                modModificacion.setSerie_recibo(recibo.getSerieRecibo());
                modModificacion.setUbicacion_modificacion("MOD");
                modModificacion.setEstado_modificacion(EnumEstadoModificacion.INGRESADA.getCodigo());
                modModificacionService.guardar_modificar_listar_ModModificacion(modModificacion, 2);
            } else {

            }

            ///////////////SEGUIMIENTO//////////////////////////////////
            if (recibo.getTipoTramiteIngresado().contains("SM")) {
                Long sm;
                if (recibo.getExtensionTramiteIngresado() == null) {
                    sm = comunService.codificarCodigoSM(recibo.getNumeroTramiteIngresado(), recibo.getGestionTramiteIngresado(), "");
                } else {
                    sm = comunService.codificarCodigoSM(recibo.getNumeroTramiteIngresado(), recibo.getGestionTramiteIngresado(), recibo.getExtensionTramiteIngresado());
                }
                List<Seguimiento> listaSeg = seguimientoService.listaSeguimientoXSM(sm, "RECA");
                if (listaSeg == null) {
                    SigSignoMarca sigMarca = sigSignoMarcaService.listaSigSignoMarcaXSM(sm);
                    Date fechaServidor = comunService.obtenerFechaHoraServidor(1L);
                    Seguimiento sigSeguimiento = new Seguimiento();
                    if (sigMarca != null) {
                        sigSeguimiento = seguimientoService.registraSeguimientoSignos(sigMarca.getIdSignoMarca(),//sigSignoMarca.getIdSignoMarca(),
                                usuario.getIdusuario(),
                                logTransGuardado.getIdLogTrans(),////
                                sm,
                                "RECA",
                                new Timestamp(fechaServidor.getTime()),
                                new Timestamp(fechaServidor.getTime()),
                                "",
                                "AC",
                                1
                        );

                    }
                }

            }
            if (recibo.getTipoTramiteIngresado().contains("LU")
                    || recibo.getTipoTramiteIngresado().contains("CN")
                    || recibo.getTipoTramiteIngresado().contains("ST")
                    || recibo.getTipoTramiteIngresado().contains("CD")
                    || recibo.getTipoTramiteIngresado().contains("SF")) {
                //  List<Usuario> listUsuario=usuarioService.listaUsuarioXidPagina(idUsuario);
                ModModificacion modi = modModificacionService.buscarModModificacionXCodigo(recibo.getTipoTramiteIngresado(),
                        Long.parseLong(recibo.getNumeroTramiteIngresado()),
                        Integer.parseInt(recibo.getGestionTramiteIngresado()));
                if (modi != null) {
                    Date fechaServidor = comunService.obtenerFechaHoraServidor(1L);
                    Seguimiento modSeguimiento = new Seguimiento();
                    modSeguimiento.setId(modi.getIdmodificacion());
                    modSeguimiento.setIdUsuario(usuario.getIdusuario());
                    modSeguimiento.setIdLogTrans(logTransGuardado.getIdLogTrans());
                    modSeguimiento.setSm(modi.getSm());
                    modSeguimiento.setEtapa("RECA");
                    modSeguimiento.setFechaRecepcion(new Timestamp(fechaServidor.getTime()));
                    modSeguimiento.setFechaFin(new Timestamp(fechaServidor.getTime()));
                    modSeguimiento.setEditable(Boolean.FALSE);
                    modSeguimiento.setEstado("AC");
                    modSeguimiento.setPlazoReal(0);
                    modSeguimiento.setTotalTiempo(0L);
                    modSeguimiento.setPlazo_limite(1);
                    modSeguimiento.setDia_pasivo(0);
                    Seguimiento modSeguimientoIngresado = new Seguimiento();
                    System.out.println("Llega hasta aqui..segui modi");
                    modSeguimientoIngresado = seguimientoService.guardar_modificar_listar_Seguimiento(modSeguimiento, "MOD", 1);
                    modi.setUbicacion_modificacion("UNMO");
                    modModificacionService.guardar_modificar_listar_ModModificacion(modi, 2);
                }
            }
            if (recibo.getTipoTramiteIngresado().contains("SR")) {
                Date fechaServidor = comunService.obtenerFechaHoraServidor(1L);
                RenSolicitudRenovacion ren = renSolicitudRenovacionService.buscaSolicitudRenovacionPorNumeroSrYGestion(Long.parseLong(recibo.getNumeroTramiteIngresado()),
                        Integer.parseInt(recibo.getGestionTramiteIngresado()));
                if (ren.getIdsolicitudrenovacion() != null) {
                    Seguimiento renSeguimiento = new Seguimiento();
                    renSeguimiento.setId(ren.getIdsolicitudrenovacion());
                    renSeguimiento.setIdUsuario(usuario.getIdusuario());
                    renSeguimiento.setIdLogTrans(logTransGuardado.getIdLogTrans());
                    renSeguimiento.setSm(ren.getSm());
                    renSeguimiento.setEtapa("RECA");
                    renSeguimiento.setFechaRecepcion(new Timestamp(fechaServidor.getTime()));
                    renSeguimiento.setFechaFin(new Timestamp(fechaServidor.getTime()));
                    renSeguimiento.setEditable(Boolean.FALSE);
                    renSeguimiento.setEstado("AC");
                    renSeguimiento.setPlazoReal(0);
                    renSeguimiento.setTotalTiempo(0L);
                    renSeguimiento.setPlazo_limite(1);
                    renSeguimiento.setDia_pasivo(0);
                    System.out.println("Llega hasta aqui..segui mreno");
                    Seguimiento modSeguimientoIngresado = new Seguimiento();
                    modSeguimientoIngresado = seguimientoService.guardar_modificar_listar_Seguimiento(renSeguimiento, "REN", 1);
                }
            }

        } catch (Exception e) {
            throw e;
        }

    }

    public void actualizaTituloIngresado(Recibo recibo) throws Exception {
        System.out.println("tipoTramiteingresado" + recibo.getTipoTramite());
        //try {
        if (recibo.getTipoTramiteIngresado().equals("SM")
                || recibo.getTipoTramiteIngresado().equals("SM-C")
                || recibo.getTipoTramiteIngresado().equals("SM-S")
                || recibo.getTipoTramiteIngresado().equals("SM-E")
                || recibo.getTipoTramiteIngresado().equals("SM-T")
                || recibo.getTipoTramiteIngresado().equals("SM-H")
                || recibo.getTipoTramiteIngresado().equals("SM-O")) {
            try {
                if ((expedienteService.obtenerSigsignomarca(recibo.getNumeroTramiteIngresado(), recibo.getGestionTramiteIngresado(), "")).getIdSignoMarca() != null) {
                    SigSignoMarca signo = (expedienteService.obtenerSigsignomarca(recibo.getNumeroTramiteIngresado(), recibo.getGestionTramiteIngresado(), ""));
                    signo.setNumeroTitulo((recibo.getTituloIngresado()));
                    signo.setSerieTitulo(recibo.getSerieTituloIngresado());

                    signo.setUbicacion(EnumUbicacion.PROPIEDAD_INDUSTRIAL.getCodigo());
                    signo.setEstadoMarca(EnumEstadoMarca.SOLICITADO.getCodigo());
                    sigSignoMarcaService.crudSigSignoMarca(signo, 2);
                } else {

                }
                //renrenovacion
                if (renSolicitudRenovacionService.buscaSolicitudRenovacionPorNumeroSrYGestion(Long.parseLong(recibo.getNumeroTramiteIngresado()), recibo.getGestionTramite().intValue()).getIdsolicitudrenovacion() != null) {
                    RenSolicitudRenovacion renSolicitudRenovacionActualizar = renSolicitudRenovacionService.buscaSolicitudRenovacionPorNumeroSrYGestion(Long.parseLong(recibo.getNumeroTramiteIngresado()), Integer.parseInt(recibo.getGestionTramiteIngresado()));
                    renSolicitudRenovacionActualizar.setNumero_titulo(recibo.getTituloIngresado());
                    renSolicitudRenovacionActualizar.setSerie_titulo(recibo.getSerieTituloIngresado());
                    renSolicitudRenovacionActualizar.setUbicacion_renovacion("REN");
                    renSolicitudRenovacionActualizar.setEstado_renovacion("SOLI");
                    renSolicitudRenovacionService.cruddosRenSolictudRenovacion(renSolicitudRenovacionActualizar, 2);
                } else {

                }
                //modmodifacion
                if (modModificacionService.buscarModModificacion_NumeroFormulario(recibo.getNumeroTramiteIngresado()).getIdmodificacion() != null) {
                    modModificacionService.guardar_modificar_listar_ModModificacion(modModificacionService.buscarModModificacion_NumeroFormulario(recibo.getNumeroTramiteIngresado()), 2);
                } else {
                }

            } catch (Exception e) {
                throw e;
            }

        }
    }

    public BigDecimal sumaMontosListado(List<Deposito> listaDepostio) {
        BigDecimal montoTotal = new BigDecimal("0.00");
        for (Deposito deposito : listaDepostio) {
            montoTotal = montoTotal.add(deposito.getSaldo());
        }
        return montoTotal;
    }

    public Integer encuentraSaldoMayor(List<Deposito> listaDeposito) {
        int posicionmayor = 0;
        BigDecimal mayor = listaDeposito.get(0).getSaldo();
        int cont = 0;
        for (int i = 0; i < listaDeposito.size(); i++) {
            for (int j = 0; j < listaDeposito.size() - 1; j++) {
                if (listaDeposito.get(i).getSaldo().compareTo(mayor) == 1) {
                    mayor = listaDeposito.get(i).getSaldo();
                    posicionmayor = i;
                }
            }
        }
        return posicionmayor;
    }

    @Override
    public List<Recibo> listaReciboEmitido() throws Exception {
        try {
            String SQL = "select * from recibo where estado_recibo='REMI' order by idrecibo DESC ";
            if (!jdbcTemplate.query(SQL, new ReciboMapper()).isEmpty()) {
                return jdbcTemplate.query(SQL, new ReciboMapper());
            }
        } catch (Exception e) {
            throw e;
        }
        return Collections.EMPTY_LIST;
    }

    @Override
    public List<Recibo> listaReciboEmitidoPorFechas(Date fechaBuscar, Usuario usuario) throws Exception {
        try {
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd 00:00:00    ");
            SimpleDateFormat formatoDos = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
            String fechaInicialConsulta;
            String fechaFinalConsulta;
            if (fechaBuscar != null) {
                fechaInicialConsulta = formato.format(fechaBuscar);
                fechaFinalConsulta = formatoDos.format(fechaBuscar);
            } else {
                Date fechaDia = comunService.obtenerFechaServidor(usuario.getIdregional());
                fechaInicialConsulta = formato.format(fechaDia);
                fechaFinalConsulta = formatoDos.format(fechaDia);
            }

            String SQL = "select * from recibo where idusuario=? and (estado_recibo<>'REEL'and estado_recibo<>'RERE') and (fecha_emision_recibo between '" + fechaInicialConsulta + "' and '" + fechaFinalConsulta + "')  order by idrecibo DESC ";
            if (!jdbcTemplate.query(SQL, new ReciboMapper(), usuario.getIdusuario()).isEmpty()) {
                return jdbcTemplate.query(SQL, new ReciboMapper(), usuario.getIdusuario());
            }
        } catch (Exception e) {
            throw e;
        }
        return Collections.EMPTY_LIST;
    }

    @Override
    public List<Recibo> listaReciboEmitidoPorFecha(Date fechaBuscar, Usuario usuario) {
        try {
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd 00:00:00    ");
            SimpleDateFormat formatoDos = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
            String fechaInicialConsulta;
            String fechaFinalConsulta;
            if (fechaBuscar != null) {
                fechaInicialConsulta = formato.format(fechaBuscar);
                fechaFinalConsulta = formatoDos.format(fechaBuscar);
            } else {
                Date fechaDia = comunService.obtenerFechaServidor(usuario.getIdregional());
                fechaInicialConsulta = formato.format(fechaDia);
                fechaFinalConsulta = formatoDos.format(fechaDia);
            }
//            String SQL = "select * from recibo where idusuario=? and estado_recibo ='REMI' and (fecha_emision_recibo between '"+fechaInicialConsulta+"' and '"+fechaFinalConsulta+"')  order by idrecibo DESC;";
            String SQL = "select * from recibo where estado_recibo='REMI' order by idrecibo DESC ";
            if (!jdbcTemplate.query(SQL, new ReciboMapper()).isEmpty()) {
                return jdbcTemplate.query(SQL, new ReciboMapper());
            }
            List<Recibo> lista = new ArrayList<Recibo>();
            if (!lista.isEmpty()) {
                return lista;
            }

        } catch (Exception e) {
        }
        return Collections.EMPTY_LIST;
    }

    @Override
    public List<Recibo> listaReciboEmitidoPorFechaYUsuario(Date fechaBuscar, Usuario usuario) {
        try {
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
            SimpleDateFormat formatoDos = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
            String fechaInicialConsulta;
            String fechaFinalConsulta;
            if (fechaBuscar != null) {
                fechaInicialConsulta = formato.format(fechaBuscar.getTime());
                fechaFinalConsulta = formatoDos.format(fechaBuscar.getTime());
            } else {
                Date fechaDia = new Date();
//                Date fechaDia = comunService.obtenerFechaServidor(usuario.getIdregional());
                fechaInicialConsulta = formato.format(fechaDia);
                fechaFinalConsulta = formatoDos.format(fechaDia);
            }
            String SQL = "select * from recibo where estado_recibo ='REMI' and (fecha_emision_recibo between ? and ?) and idusuario=?  order by idrecibo DESC";
            if (!jdbcTemplate.query(SQL, new ReciboMapper(), fechaInicialConsulta, fechaFinalConsulta, usuario.getIdusuario()).isEmpty()) {
                return jdbcTemplate.query(SQL, new ReciboMapper(), fechaInicialConsulta, fechaFinalConsulta, usuario.getIdusuario());
            }
            List<Recibo> lista = new ArrayList<Recibo>();
            if (!lista.isEmpty()) {
                return lista;
            }
        } catch (Exception e) {
        }
        return Collections.EMPTY_LIST;
    }

    @Override
    public Boolean validaRegistroDeRecibos(Recibo recibo) {
        Boolean estaRegistrado = false;
        String SQL = "select * from recibo where estado_recibo='" + EnumEstadoRecibo.RECIBO_EMITIDO.getCodigo() + "' and numero_tramite_ingresado=? order by idrecibo DESC  ";
        List<Recibo> lista = jdbcTemplate.query(SQL, new ReciboMapper(), recibo.getNumeroTramiteIngresado());
        if (recibo.getTipoTramite().equals(EnumTipoTramite.REGISTRO_MARCAS.getCodigo())
                || recibo.getTipoTramite().equals(EnumTipoTramite.DEPOSITO_LEMA_COMERCIAL_ROTULO.getCodigo())
                || recibo.getTipoTramite().equals(EnumTipoTramite.RENOVACION.getCodigo())
                || recibo.getTipoTramite().equals(EnumTipoTramite.LEMACOMERCIAL.getCodigo())) {
            if (!lista.isEmpty()) {
                if (lista.size() >= 2) {
                    estaRegistrado = true;
                }
            }
        } else {
            if (!lista.isEmpty()) {
                if (lista.size() >= 1) {
                    estaRegistrado = true;
                }
            }
        }
        return estaRegistrado;
    }

    @Override
    public Recibo buscaReciboPorIdRecibo(Long idrecibo) throws Exception {
        try {
            String SQL = "select * from recibo where idrecibo=?";
            if (jdbcTemplate.query(SQL, new ReciboMapper(), idrecibo) != null) {
                return (Recibo) jdbcTemplate.query(SQL, new ReciboMapper(), idrecibo).get(0);
            }
        } catch (Exception e) {
        }
        return new Recibo();
    }

    @Override
    public void eliminaRecibo(Recibo recibo) throws Exception {
        System.out.println("elimina recibo");
        try {
            int modificacion = 2;
            Date fechaModificacion = comunService.obtenerFechaHoraServidor(1L);
            recibo.setEstadoRecibo(EnumEstadoRecibo.RECIBO_ELIMINADO.getCodigo());
            recibo.setEstado(EnumEstado.ACTIVO.getCodigo());
            List<ReciboDeposito> lista = reciboDepositoService.lstReciboDepositoPorIdRecibo(recibo.getIdRecibo());
            for (ReciboDeposito reciboDeposito : lista) {
                Deposito deposito = depositoService.depositoPorIdDeposto(reciboDeposito.getIdDeposito());
//                if (deposito.getSaldo().compareTo(reciboDeposito.getMonto()) == -1) {
                deposito.setSaldo(deposito.getSaldo().add(reciboDeposito.getMonto()));
//                }
//                deposito.setEstado("NA");
                depositoService.crudDeposito(deposito, modificacion);
            }

            List<DosificacionTasa> lstDosificacionTasa = encuentraListaDosiTasaPorTasaPorRegional(recibo.getIdTasa(), recibo.getIdRegional());
            for (DosificacionTasa lstDosificacionTasa1 : lstDosificacionTasa) {
                Dosificacion dosificacion = obtieneDosificacionPorId(lstDosificacionTasa1.getIdDosificacion());
                dosificacion.setSiguiente(dosificacion.getSiguiente() - 1);
                dosificacionService.crudDosificacion(dosificacion, modificacion);

            }

            Recibo recibobase = crudRecibo(recibo, modificacion);
        } catch (Exception e) {
            throw e;
        }
    }

    public List<DosificacionTasa> encuentraListaDosiTasaPorTasaPorRegional(Long tasa, Long regional) {
        String SQL = "select * from dosificaciontasa where idtasa='" + tasa + "' and  idregional='" + regional + "' ";
        if (!jdbcTemplate.query(SQL, new DosificacionTasaMapper()).isEmpty()) {
            return jdbcTemplate.query(SQL, new DosificacionTasaMapper());
        }
        return new ArrayList<>();
    }

    public Dosificacion obtieneDosificacionPorId(Long idDosificacion) {
        System.out.println("iddosificaicon tasa" + idDosificacion);
        String SQL = "select * from dosificacion where iddosificacion ='" + idDosificacion + "'";
        if (!jdbcTemplate.query(SQL, new DosificacionMapper()).isEmpty()) {
            return (Dosificacion) jdbcTemplate.query(SQL, new DosificacionMapper()).get(0);
        }
        return new Dosificacion();

    }

    @Override
    public void anularRecibo(Recibo recibo) throws Exception {
        System.out.println("anular recibo");
        try {
            int modificacion = 2;
            Date fechaModificacion = comunService.obtenerFechaHoraServidor(1L);
            recibo.setEstadoRecibo(EnumEstadoRecibo.RECIBO_ANULADO.getCodigo());
//            recibo.setEstado(EnumEstado.NO_ACTIVO.getCodigo());
            recibo.setIdTasa(230L);
            recibo.setLiteralMonto("Cero 00/100 bolivianos");
            recibo.setConcepto("ANULADO");
            recibo.setMonto(BigDecimal.ZERO);

            List<ReciboDeposito> lista = reciboDepositoService.lstReciboDepositoPorIdRecibo(recibo.getIdRecibo());
            for (ReciboDeposito reciboDeposito : lista) {
                Deposito deposito = depositoService.depositoPorIdDeposto(reciboDeposito.getIdDeposito());

                deposito.setSaldo(deposito.getSaldo().add(reciboDeposito.getMonto()));

//                deposito.setEstado("NA");
                depositoService.crudDeposito(deposito, modificacion);
            }
            List<DosificacionTasa> lstDosificacionTasa = encuentraListaDosiTasaPorTasaPorRegional(recibo.getIdTasa(), recibo.getIdRegional());
            for (DosificacionTasa lstDosificacionTasa1 : lstDosificacionTasa) {
                if (lstDosificacionTasa1.getTipoRecibo().equals("TITU")) {
                    Dosificacion dosificacion = obtieneDosificacionPorId(lstDosificacionTasa1.getIdDosificacion());
                    dosificacion.setSiguiente(dosificacion.getSiguiente() - 1);
                    dosificacionService.crudDosificacion(dosificacion, modificacion);
                }

            }

            Recibo recibobase = crudRecibo(recibo, modificacion);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Recibo buscaRecibosActivos(Long idrecibo) throws Exception {
        try {
            String SQL = "select * from recibo where idrecibo=? and estado=AC";
            if (jdbcTemplate.query(SQL, new ReciboMapper(), idrecibo) != null) {
                return (Recibo) jdbcTemplate.query(SQL, new ReciboMapper(), idrecibo).get(0);
            }
        } catch (Exception e) {
        }
        return new Recibo();
    }

    @Override
    public Recibo buscaReciboPorNumeroYSerieRecibo(int numeroRecibo, String serieRecibo) throws Exception {
        try {
            String SQL = "select * from recibo where numero_recibo=? and serie_recibo=? and estado_recibo='" + EnumEstadoRecibo.RECIBO_ELIMINADO.getCodigo() + "'";
            if (jdbcTemplate.query(SQL, new ReciboMapper(), numeroRecibo, serieRecibo) != null) {
                return (Recibo) jdbcTemplate.query(SQL, new ReciboMapper(), numeroRecibo, serieRecibo).get(0);
            }
        } catch (Exception e) {
        }
        return new Recibo();
    }

    @Override
    public Recibo buscaReciboPorNumeroYSerieReciboSinEstado(int numeroRecibo, String serieRecibo) throws Exception {
        try {
            String SQL = "select * from recibo where numero_recibo=? and serie_recibo=?";
            if (jdbcTemplate.query(SQL, new ReciboMapper(), numeroRecibo, serieRecibo) != null) {
                return (Recibo) jdbcTemplate.query(SQL, new ReciboMapper(), numeroRecibo, serieRecibo).get(0);
            }
        } catch (Exception e) {
        }
        return new Recibo();
    }

    @Override
    public Recibo buscaReciboPorNumeroYSerieTitulo(int numeroTitulo, String serieTitulo) throws Exception {
        try {
            String SQL = "select * from recibo where titulo_ingresado=? and serie_titulo_ingresado=? and estado_recibo <> '" + EnumEstadoRecibo.RECIBO_EMITIDO.getCodigo() + "'";
            if (jdbcTemplate.query(SQL, new ReciboMapper(), numeroTitulo, serieTitulo) != null) {
                return (Recibo) jdbcTemplate.query(SQL, new ReciboMapper(), numeroTitulo, serieTitulo).get(0);
            }
        } catch (Exception e) {
        }
        return new Recibo();
    }

    @Override
    public List<Recibo> listaReciboPorFecha(Date fechaBuscar) throws Exception {
        try {
            SimpleDateFormat formatoIni = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
            SimpleDateFormat formatoFin = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
            String fechaIni;
            String fechaFin;
            if (fechaBuscar != null) {
                fechaIni = formatoIni.format(fechaBuscar);
                fechaFin = formatoFin.format(fechaBuscar);
                String SQL = "select * from recibo where (fecha_emision_recibo between '" + fechaIni + "' and '" + fechaFin + "') and estado = 'AC' order by serie_recibo,numero_recibo";
                List<Recibo> listaRecibo = jdbcTemplate.query(SQL, new ReciboMapper());
                if (!listaRecibo.isEmpty()) {
                    return listaRecibo;
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
    public List<Recibo> listaReciboPorFechaPorIdUsuario(Date fechaBuscar, Long idusuario) throws Exception {
        try {
            SimpleDateFormat formatoIni = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
            SimpleDateFormat formatoFin = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
            String fechaIni;
            String fechaFin;
            if (fechaBuscar != null) {
                fechaIni = formatoIni.format(fechaBuscar);
                fechaFin = formatoFin.format(fechaBuscar);
                String SQL = "select * from recibo where (fecha_emision_recibo between '" + fechaIni + "' and '" + fechaFin + "') and idusuario = " + idusuario + " and estado = 'AC' order by serie_recibo,numero_recibo";
                List<Recibo> listaRecibo = jdbcTemplate.query(SQL, new ReciboMapper());
                if (!listaRecibo.isEmpty()) {
                    return listaRecibo;
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
    public List<Recibo> listaReciboPorFechaPorRegional(Date fechaBuscar, Long idregional) throws Exception {
        try {
            SimpleDateFormat formatoIni = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
            SimpleDateFormat formatoFin = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
            String fechaIni;
            String fechaFin;
            if (fechaBuscar != null) {
                fechaIni = formatoIni.format(fechaBuscar);
                fechaFin = formatoFin.format(fechaBuscar);
                String SQL = "select * from recibo where (fecha_emision_recibo between '" + fechaIni + "' and '" + fechaFin + "') and idregional = " + idregional + " and estado = 'AC' order by serie_recibo,numero_recibo";
                List<Recibo> listaRecibo = jdbcTemplate.query(SQL, new ReciboMapper());
                if (!listaRecibo.isEmpty()) {
                    return listaRecibo;
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
    public BigDecimal totalMontoReciboPorFecha(Date fechaBuscar) throws Exception {
        String SQL = "select sum(monto) from recibo where fecha_emision_recibo = ? and estado = 'AC'";
        return jdbcTemplate.queryForObject(SQL, BigDecimal.class, fechaBuscar);
    }

    @Override
    public List<Recibo> listaReciboPorDeposito(long idDeposito) throws Exception {
        System.out.println("iddepo" + idDeposito);

        try {
            String SQL = "select * from recibo where idrecibo in (select idrecibo from recibodeposito where iddeposito ='" + idDeposito + "')";

            List<Recibo> listaRecibo = jdbcTemplate.query(SQL, new ReciboMapper());
            if (!listaRecibo.isEmpty()) {
                return listaRecibo;
            }
        } catch (Exception e) {
        }
        return Collections.EMPTY_LIST;
    }

    @Override
    public Boolean activaGuardadoReciboCantidad(Tasa tasa, List<Deposito> listaDepositos, int cantidad) throws Exception {
        try {
            Boolean activaGuardado;
            int costoTasa;
            int costoDeposito;
            BigDecimal sumaDepositos = new BigDecimal("0.00");
            for (Deposito deposito : listaDepositos) {
                sumaDepositos = sumaDepositos.add(deposito.getSaldo());
            }
            BigDecimal montoFinalTasa = tasa.getCosto().multiply(new BigDecimal(cantidad));
            activaGuardado = sumaDepositos.compareTo(montoFinalTasa) == 0 || sumaDepositos.compareTo(montoFinalTasa) == 1;
            costoTasa = montoFinalTasa.intValue();
            costoDeposito = sumaDepositos.intValue();
            return activaGuardado;
        } catch (Exception e) {
        }
        return false;
    }

}
