/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.servicio.impl;

import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import snp.gob.bo.gimodel.entidad.SigPrioridadPreferencia;
import snp.gob.bo.gimodel.enums.EnumEstado;
import snp.gob.bo.gimodel.mapper.SigPrioridadPreferenciaMapper;
import snp.gob.bo.gimodel.servicio.SigPrioridadPreferenciaService;
import snp.gob.bo.gimodel.solicitudes.entidades.Prioridades;

/**
 *
 * @author Eddy Valero
 * @version 1.0, 29/08/2016
 */
@Service("sigPrioridadPreferencia")
public class SigPrioridadPreferenciaServiceImpl implements SigPrioridadPreferenciaService {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Override
    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public SigPrioridadPreferencia registrarSigPrioridadPreferencia(SigPrioridadPreferencia sigPrioridadPreferencia) throws Exception {
        try {
            String SQL = "select * from reg_sigprioridadpreferencia(?, ?, ?, ?, ?, ?, ?, ?, ?);";
            SigPrioridadPreferencia sigPrioPref = (SigPrioridadPreferencia) jdbcTemplate.queryForObject(
                    SQL,
                    new SigPrioridadPreferenciaMapper(),
                    sigPrioridadPreferencia.getIdSignoMarca(),
                    sigPrioridadPreferencia.getIdLogTrans(),
                    sigPrioridadPreferencia.getTipoPrioridad(),
                    sigPrioridadPreferencia.getTipoInteres(),
                    sigPrioridadPreferencia.getNombreNumero(),
                    sigPrioridadPreferencia.getFecha(),
                    sigPrioridadPreferencia.getLugar(),
                    sigPrioridadPreferencia.getEstado(),
                    sigPrioridadPreferencia.getIdSipi()
            );
            return sigPrioPref;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public SigPrioridadPreferencia obtenerPrioridadExtranjera(Long idSignoMarca) throws Exception {
        try {
            String SQL = "select * from sigprioridadpreferencia "
                    + " where idsignomarca = ? "
                    + " and estado='AC' "
                    + " and tipo_prioridad='PRI' "
                    + " and tipo_interes='EXT' ";
            return jdbcTemplate.queryForObject(SQL,
                    new Object[]{idSignoMarca},
                    new SigPrioridadPreferenciaMapper()
            );

        } catch (EmptyResultDataAccessException e) {
            return new SigPrioridadPreferencia();
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public SigPrioridadPreferencia obtenerPrioridadExposicion(Long idSignoMarca) throws Exception {
        try {
            String SQL = "select * from sigprioridadpreferencia "
                    + " where idsignomarca = ? "
                    + " and estado='AC' "
                    + " and tipo_prioridad='PRI' "
                    + " and tipo_interes='EXP' ";
            return jdbcTemplate.queryForObject(SQL,
                    new Object[]{idSignoMarca},
                    new SigPrioridadPreferenciaMapper()
            );
        } catch (EmptyResultDataAccessException e) {
            return new SigPrioridadPreferencia();
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public SigPrioridadPreferencia obtenerOposicionAndina(Long idSignoMarca) throws Exception {
        try {
            String SQL = "select * from sigprioridadpreferencia "
                    + " where idsignomarca = ? "
                    + " and estado='AC' "
                    + " and tipo_prioridad='OA' "
                    + "  ";
            return jdbcTemplate.queryForObject(SQL,
                    new Object[]{idSignoMarca},
                    new SigPrioridadPreferenciaMapper()
            );
        } catch (EmptyResultDataAccessException e) {
            return new SigPrioridadPreferencia();
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public SigPrioridadPreferencia crudSigPrioridadPreferencia(SigPrioridadPreferencia sigPrioridadPreferencia, int parametro) throws Exception {
        try {

            String SQL = "select * from crud_sigprioridadpreferencia("
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
            List<SigPrioridadPreferencia> listaSigPrioridadPreferencia = jdbcTemplate.query(SQL, new SigPrioridadPreferenciaMapper(),
                    sigPrioridadPreferencia.getIdPrioridadPreferencia(),
                    sigPrioridadPreferencia.getIdSignoMarca(),
                    sigPrioridadPreferencia.getIdLogTrans(),
                    sigPrioridadPreferencia.getTipoPrioridad(),
                    sigPrioridadPreferencia.getTipoInteres(),
                    sigPrioridadPreferencia.getNombreNumero(),
                    sigPrioridadPreferencia.getFecha(),
                    sigPrioridadPreferencia.getLugar(),
                    sigPrioridadPreferencia.getEstado(),
                    sigPrioridadPreferencia.getIdSipi(),
                    parametro);
            if (!listaSigPrioridadPreferencia.isEmpty()) {
                return listaSigPrioridadPreferencia.get(0);
            }
            return new SigPrioridadPreferencia();
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<SigPrioridadPreferencia> obtenerListaPrioridadPreferencia(Long idSignoMarca) throws Exception {
        try {
            String SQL = "select * from sigprioridadpreferencia "
                    + " where idsignomarca = ? "
                    + " and estado='AC'";
            List<SigPrioridadPreferencia> listaSigPrioridadPreferencia = jdbcTemplate.query(SQL,
                    new SigPrioridadPreferenciaMapper(),
                    idSignoMarca);

            return listaSigPrioridadPreferencia;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void modificarPrioridadPreferenciaSubsanacion(Long idSignoMarca, List<Prioridades> listaPrioridadPreferenciaSipi, Long idLogTrans) throws Exception {
        int operacionAdicionar = 1;
        int operacionModificar = 2;
        int sw;

        // Recuperamos prioridades desde la base de datos
        List<SigPrioridadPreferencia> listaPrioridadPreferenciaHidra = obtenerListaPrioridadPreferencia(idSignoMarca);

        // modificar
        for (Prioridades prioridadPreferenciaSipi : listaPrioridadPreferenciaSipi) {
            sw = 0;
            for (SigPrioridadPreferencia prioridadPreferenciaHidra : listaPrioridadPreferenciaHidra) {
                if (prioridadPreferenciaHidra.getTipoInteres().equals(prioridadPreferenciaSipi.getTipoInteres())) {
                    if (prioridadPreferenciaHidra.getIdSipi().equals(prioridadPreferenciaSipi.getIdPadre())) {
                        prioridadPreferenciaHidra.setIdSignoMarca(idSignoMarca);
                        prioridadPreferenciaHidra.setIdLogTrans(idLogTrans);
                        prioridadPreferenciaHidra.setTipoPrioridad(prioridadPreferenciaSipi.getTipoPrioridad());
                        prioridadPreferenciaHidra.setTipoInteres(prioridadPreferenciaSipi.getTipoInteres());
                        prioridadPreferenciaHidra.setNombreNumero(prioridadPreferenciaSipi.getNombre());
                        prioridadPreferenciaHidra.setFecha(prioridadPreferenciaSipi.getFecha());
                        prioridadPreferenciaHidra.setLugar(prioridadPreferenciaSipi.getLugar());
                        prioridadPreferenciaHidra.setIdSipi(prioridadPreferenciaSipi.getId());
                        
                        crudSigPrioridadPreferencia(prioridadPreferenciaHidra, operacionModificar);
                    }
                    sw = 1;
                    break;
                }
            }
            if (sw == 0) { // adicionar
                SigPrioridadPreferencia sigPrioridadPreferencia = new SigPrioridadPreferencia();
                sigPrioridadPreferencia.setIdSignoMarca(idSignoMarca);
                sigPrioridadPreferencia.setIdLogTrans(idLogTrans);
                sigPrioridadPreferencia.setTipoPrioridad(prioridadPreferenciaSipi.getTipoPrioridad());
                sigPrioridadPreferencia.setTipoInteres(prioridadPreferenciaSipi.getTipoInteres());
                sigPrioridadPreferencia.setNombreNumero(prioridadPreferenciaSipi.getNombre());
                sigPrioridadPreferencia.setFecha(prioridadPreferenciaSipi.getFecha());
                sigPrioridadPreferencia.setLugar(prioridadPreferenciaSipi.getLugar());
                sigPrioridadPreferencia.setEstado(EnumEstado.ACTIVO.getCodigo());
                sigPrioridadPreferencia.setIdSipi(prioridadPreferenciaSipi.getId());
                crudSigPrioridadPreferencia(sigPrioridadPreferencia, operacionAdicionar);
            }
        }

        for (SigPrioridadPreferencia prioridadPreferenciaHidra : listaPrioridadPreferenciaHidra) {
            sw = 0;
            for (Prioridades prioridadPreferenciaSipi : listaPrioridadPreferenciaSipi) {
                if (prioridadPreferenciaHidra.getTipoPrioridad().equals(prioridadPreferenciaSipi.getTipoPrioridad())) {
                    sw = 1;
                    break;
                }
            }
            if (sw == 0) { // eliminar
                prioridadPreferenciaHidra.setEstado(EnumEstado.NO_ACTIVO.getCodigo());
                crudSigPrioridadPreferencia(prioridadPreferenciaHidra, operacionModificar);
            }
        }
    }
}
