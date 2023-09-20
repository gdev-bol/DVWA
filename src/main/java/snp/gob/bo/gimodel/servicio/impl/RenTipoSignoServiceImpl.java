/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.servicio.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import snp.gob.bo.gimodel.entidad.RenSolicitudRenovacion;
import snp.gob.bo.gimodel.entidad.RenTipoSigno;
import snp.gob.bo.gimodel.enums.EnumEstado;
import snp.gob.bo.gimodel.mapper.RenTipoSignoMapper;
import snp.gob.bo.gimodel.servicio.RenTipoSignoService;
import snp.gob.bo.gimodel.solicitudes.entidades.RenTipoSignos;

/**
 *
 * @author Chano Rojas
 * @see RenRenovacionDominio
 * @see RenrenovacionServiceImpl
 * @version 1.0, 05/06/2016
 */
@Service("renTipoSignoService")
public class RenTipoSignoServiceImpl implements RenTipoSignoService {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    
    @Autowired
    @Override
    public void setDataSource(DataSource dataSource) throws Exception {
        try {
            this.dataSource = dataSource;
            this.jdbcTemplate = new JdbcTemplate(dataSource);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public RenTipoSigno crudRenTipoSigno(RenTipoSigno renTipoSigno, int parametro) throws Exception {
        try {
            String SQL = "select * from crud_rentiposigno("
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?);";
            RenTipoSigno rentipo = (RenTipoSigno) jdbcTemplate.queryForObject(SQL, new RenTipoSignoMapper(),
                    renTipoSigno.getIdtiposigno(),
                    renTipoSigno.getIdsolicitudrenovacion(),
                    renTipoSigno.getIdlogtrans(),
                    renTipoSigno.getTipo_signo(),
                    renTipoSigno.getDescripcion_otro(),
                    renTipoSigno.getEstado(),
                    parametro);
            return rentipo;
        } catch (DataAccessException e) {
            throw e;
        }
    }

    @Override
    public List<RenTipoSigno> listaRenTipoSigno(RenTipoSigno renTipoSigno, int parametro) throws Exception {
        try {
            String SQL = "select * from crud_rentiposigno("
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?);";
            List<RenTipoSigno> listarentipo = (List<RenTipoSigno>) (RenTipoSigno) jdbcTemplate.queryForObject(SQL, new RenTipoSignoMapper(),
                    renTipoSigno.getIdtiposigno(),
                    renTipoSigno.getIdsolicitudrenovacion(),
                    renTipoSigno.getIdlogtrans(),
                    renTipoSigno.getTipo_signo(),
                    renTipoSigno.getDescripcion_otro(),
                    renTipoSigno.getEstado(),
                    parametro);
            if (!listarentipo.isEmpty()) {
                return listarentipo;
            } else {
                return Collections.EMPTY_LIST;
            }
        } catch (DataAccessException e) {
            throw e;
        }
    }

    @Override
    public List<RenTipoSigno> buscaRenTipoSignoPorIdSolicitud(Long idRentipoSolicitud) {
        try {
//            System.out.println("id" + idRentipoSolicitud + ";;;;;;;;;;;;;;;;;;::::::::::::::::::::::::::::::::::::::::::::::");
            String SQL = "select * from lista_rentiposigno_idrensolicitudrenovacion("
                    + "?);";
            List<RenTipoSigno> listaren = jdbcTemplate.query(SQL, new RenTipoSignoMapper(),
                    idRentipoSolicitud
            );
            if (!listaren.isEmpty()) {
                return listaren;
            } else {
                return new ArrayList<>();
            }
        } catch (DataAccessException e) {
            throw e;
        }

    }

    @Override
    public List<RenTipoSigno> buscaTipoSigno(Long idsolicitud) {
        try {
//            System.out.println("id" + idsolicitud + ";;;;;;;;;;;;;;;;;;::::::::::::::::::::::::::::::::::::::::::::::");
            String SQL = "select * from rentiposigno where idsolicitudrenovacion=? and estado='AC'";
            List<RenTipoSigno> listaren = jdbcTemplate.query(SQL, new RenTipoSignoMapper(),
                    idsolicitud
            );
            if (!listaren.isEmpty()) {
                return listaren;
            } else {
                return new ArrayList<>();
            }
        } catch (DataAccessException e) {
            throw e;
        }

    }

    @Override
    public void modificarTipoSigno(RenSolicitudRenovacion rensolicitudRenovacion, List<RenTipoSigno> lstRenTipoSigno) throws Exception {
        try {
            int parametro = 2;
            int parametroGuardar = 1;
            List<RenTipoSigno> listaTipoSignoBase;
            listaTipoSignoBase = buscaTipoSigno(rensolicitudRenovacion.getIdsolicitudrenovacion());
            for (RenTipoSigno renTitularRegistradoBase : listaTipoSignoBase) {
                renTitularRegistradoBase.setEstado(EnumEstado.NO_ACTIVO.getCodigo());
                crudRenTipoSigno(renTitularRegistradoBase, parametro);
            }
            for (RenTipoSigno rensTitularRegistradoVistaDos : lstRenTipoSigno) {
                rensTitularRegistradoVistaDos.setIdsolicitudrenovacion(rensolicitudRenovacion.getIdsolicitudrenovacion());
                rensTitularRegistradoVistaDos.setIdlogtrans(1L);
                rensTitularRegistradoVistaDos.setEstado(EnumEstado.ACTIVO.getCodigo());
                crudRenTipoSigno(rensTitularRegistradoVistaDos, parametroGuardar);
            }
        } catch (Exception ex) {
            Logger.getLogger(RenSolicitanteApoderadoServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
    }

    @Override
    public void modificarRenTipoSignoSubsanacion(RenSolicitudRenovacion renSolicitudRenovacion, List<RenTipoSigno> listaRenTipoSigno,
            List<RenTipoSignos> listaTipoSignosExt, Long idLogTrans) throws Exception {
        try {

            int operacionAdicionar = 1;
            int operacionModificar = 2;

            int sw;

            for (RenTipoSigno tipoSignoBase : listaRenTipoSigno) {
                sw = 0;
                for (RenTipoSignos tipoSignoExt : listaTipoSignosExt) {
                    if (tipoSignoBase.getTipo_signo().equals(tipoSignoExt.getTiposigno())) {
                        sw = 1;
                        break;
                    }
                }
                if (sw == 0) {  // Eliminación logica del tipo signo
                    tipoSignoBase.setEstado(EnumEstado.NO_ACTIVO.getCodigo());
                    crudRenTipoSigno(tipoSignoBase, operacionModificar);
                }
            }

            for (RenTipoSignos tipoSignoExt : listaTipoSignosExt) {
                sw = 0;
                for (RenTipoSigno tipoSignoBase : listaRenTipoSigno) {
                    if (tipoSignoExt.getTiposigno().equals(tipoSignoBase.getTipo_signo())) {
                        sw = 1;
                        break;
                    }
                }
                if (sw == 0) { // Adicionar nuevo tipo signo
                    RenTipoSigno tipoSignoNuevo = new RenTipoSigno();                
                    tipoSignoNuevo.setIdsolicitudrenovacion(renSolicitudRenovacion.getIdsolicitudrenovacion());
                    tipoSignoNuevo.setTipo_signo(tipoSignoExt.getTiposigno());
                    tipoSignoNuevo.setDescripcion_otro(tipoSignoExt.getDescripcionotro());
                    //tipoSignoNuevo.setIdlogtrans(idl);
                    tipoSignoNuevo.setEstado(EnumEstado.ACTIVO.getCodigo());
                    crudRenTipoSigno(tipoSignoNuevo, operacionAdicionar);
                }
            }
        } catch (Exception e) {
            throw e;
        }
    }    
}
