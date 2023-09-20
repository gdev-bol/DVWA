/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.servicio.impl;

import static java.nio.charset.StandardCharsets.ISO_8859_1;
import static java.nio.charset.StandardCharsets.UTF_8;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import snp.gob.bo.gimodel.entidad.Historial;
import snp.gob.bo.gimodel.entidad.LogTrans;
import snp.gob.bo.gimodel.entidad.ModModificacion;
import snp.gob.bo.gimodel.entidad.ObjetoEliminadoGenerico;
import snp.gob.bo.gimodel.entidad.RenDireccionDeNotificacion;
import snp.gob.bo.gimodel.entidad.RenRenovacion;
import snp.gob.bo.gimodel.entidad.RenSolicitanteApoderado;
import snp.gob.bo.gimodel.entidad.RenDireccionDeNotificacion;
import snp.gob.bo.gimodel.entidad.RenSolicitudRenovacion;
import snp.gob.bo.gimodel.entidad.RenTipoSigno;
import snp.gob.bo.gimodel.entidad.RenTitularRegistrado;
import snp.gob.bo.gimodel.entidad.Usuario;
import snp.gob.bo.gimodel.enums.EnumEstado;
import snp.gob.bo.gimodel.enums.EnumTipoPersona;
import snp.gob.bo.gimodel.enums.EnumTipoTitular;
import snp.gob.bo.gimodel.mapper.ModModificacionMapper;
import snp.gob.bo.gimodel.mapper.RenRenovacionMapper;
import snp.gob.bo.gimodel.mapper.ObjetoEliminadoGenericoMapper;
import snp.gob.bo.gimodel.mapper.RenSolicitanteApoderadoMapper;
import snp.gob.bo.gimodel.mapper.RenSolicitudRenovacionMapper;
import snp.gob.bo.gimodel.servicio.ComunService;
import snp.gob.bo.gimodel.servicio.HistorialService;
import snp.gob.bo.gimodel.servicio.LogTransService;
import snp.gob.bo.gimodel.servicio.RenDireccionDeNotificacionService;
import snp.gob.bo.gimodel.servicio.RenHistorialService;
import snp.gob.bo.gimodel.servicio.RenSolicitanteApoderadoService;
import snp.gob.bo.gimodel.servicio.RenSolicitudRenovacionService;
import snp.gob.bo.gimodel.servicio.RenTipoSignoService;
import snp.gob.bo.gimodel.servicio.RenTitularRegistradoService;
import snp.gob.bo.gimodel.solicitudes.entidades.Apoderados;
import snp.gob.bo.gimodel.solicitudes.entidades.FormularioPI104;
import snp.gob.bo.gimodel.solicitudes.entidades.Solicitantes;
import snp.gob.bo.gimodel.solicitudes.servicio.FormularioService;

/**
 *
 * @author Chano Rojas
 * @see RenRenovacionDominio
 * @see RenrenovacionServiceImpl
 * @version 1.0, 05/06/2016
 */
@Service("renSolicitudRenovacionService")
public class RenSolicitudRenovacionServiceImpl implements RenSolicitudRenovacionService {

    @Autowired
    private RenSolicitanteApoderadoService renSolicitanteApoderadoService;
    @Autowired
    private RenTipoSignoService renTipoSignoService;
    @Autowired
    private RenTitularRegistradoService renTitularRegistradoService;
    @Autowired
    private RenDireccionDeNotificacionService renDireccionDeNotificacionService;
    @Autowired
    private HistorialService historialService;
    @Autowired
    private RenHistorialService renHistorialService;
    @Autowired
    private LogTransService logTransService;
    @Autowired
    private ComunService comunService;
    @Autowired
    private FormularioService formularioService;

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

    /**
     *
     * @param renSolicitudRenovacion
     * @param parametro
     * @return
     * @throws Exception
     */
    @Override
    public RenSolicitudRenovacion crudRenSolictudRenovacion(RenSolicitudRenovacion renSolicitudRenovacion, int parametro) throws Exception {
        try {
            RenSolicitudRenovacion ren = new RenSolicitudRenovacion();
            String SQL = "select * from crud_rensolicitudrenovacion("
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
            ren = (RenSolicitudRenovacion) jdbcTemplate.query(SQL, new RenSolicitudRenovacionMapper(),
                    renSolicitudRenovacion.getIdsolicitudrenovacion(),
                    renSolicitudRenovacion.getIdlogtrans(),
                    renSolicitudRenovacion.getId_recibo_solicitud(),
                    renSolicitudRenovacion.getId_recibo_titulo(),
                    renSolicitudRenovacion.getSr(),
                    renSolicitudRenovacion.getGestion_sr(),
                    renSolicitudRenovacion.getFecha_ingreso(),
                    renSolicitudRenovacion.getNumero_formulario(),
                    renSolicitudRenovacion.getEstado_renovacion(),
                    renSolicitudRenovacion.getUbicacion_renovacion(),
                    renSolicitudRenovacion.getNumero_ultima_renovacion(),
                    renSolicitudRenovacion.getSerie_ultima_renovacion(),
                    renSolicitudRenovacion.getNumero_penultima_renovacion(),
                    renSolicitudRenovacion.getSerie_penultima_renovacion(),
                    renSolicitudRenovacion.getOficina(),
                    renSolicitudRenovacion.getNumero_recibo(),
                    renSolicitudRenovacion.getSerie_recibo(),
                    renSolicitudRenovacion.getNumero_titulo(),
                    renSolicitudRenovacion.getSerie_titulo(),
                    renSolicitudRenovacion.getIdclase_niza_reclasificacion(),
                    renSolicitudRenovacion.getLista_productos_limitacion(),
                    renSolicitudRenovacion.getSm(),
                    renSolicitudRenovacion.getSigno_registrado(),
                    renSolicitudRenovacion.getIdclase_niza_registrado(),
                    renSolicitudRenovacion.getTipo_genero(),
                    renSolicitudRenovacion.getNumero_registro_registrado(),
                    renSolicitudRenovacion.getSerie_registro_registrado(),
                    renSolicitudRenovacion.getFecha_registro_registrado(),
                    renSolicitudRenovacion.getMarca_acomp(),
                    renSolicitudRenovacion.getReg_lc_registrado(),
                    renSolicitudRenovacion.getSerie_lc_registrado(),
                    renSolicitudRenovacion.getFecha_lc_registrado(),
                    renSolicitudRenovacion.getEstado(),
                    parametro);
            return ren;
        } catch (DataAccessException e) {
            throw e;
        }
    }

    @Override
    public List<RenSolicitudRenovacion> obtieneListaRenSolictudRenovacion(RenSolicitudRenovacion renSolicitudRenovacion, int parametro) throws Exception {
        try {
            String SQL = "select * from crud_rensolicitudrenovacion(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
            List<RenSolicitudRenovacion> listaren = jdbcTemplate.query(SQL, new RenSolicitudRenovacionMapper(),
                    renSolicitudRenovacion.getIdsolicitudrenovacion(),
                    renSolicitudRenovacion.getIdlogtrans(),
                    renSolicitudRenovacion.getId_recibo_solicitud(),
                    renSolicitudRenovacion.getId_recibo_titulo(),
                    renSolicitudRenovacion.getSr(),
                    renSolicitudRenovacion.getGestion_sr(),
                    renSolicitudRenovacion.getFecha_ingreso(),
                    renSolicitudRenovacion.getNumero_formulario(),
                    renSolicitudRenovacion.getEstado_renovacion(),
                    renSolicitudRenovacion.getUbicacion_renovacion(),
                    renSolicitudRenovacion.getNumero_ultima_renovacion(),
                    renSolicitudRenovacion.getSerie_ultima_renovacion(),
                    renSolicitudRenovacion.getNumero_penultima_renovacion(),
                    renSolicitudRenovacion.getSerie_penultima_renovacion(),
                    renSolicitudRenovacion.getOficina(),
                    renSolicitudRenovacion.getNumero_recibo(),
                    renSolicitudRenovacion.getSerie_recibo(),
                    renSolicitudRenovacion.getNumero_titulo(),
                    renSolicitudRenovacion.getSerie_titulo(),
                    renSolicitudRenovacion.getIdclase_niza_reclasificacion(),
                    renSolicitudRenovacion.getLista_productos_limitacion(),
                    renSolicitudRenovacion.getSm(),
                    renSolicitudRenovacion.getSigno_registrado(),
                    renSolicitudRenovacion.getIdclase_niza_registrado(),
                    renSolicitudRenovacion.getTipo_genero(),
                    renSolicitudRenovacion.getNumero_registro_registrado(),
                    renSolicitudRenovacion.getSerie_registro_registrado(),
                    renSolicitudRenovacion.getFecha_registro_registrado(),
                    renSolicitudRenovacion.getMarca_acomp(),
                    renSolicitudRenovacion.getReg_lc_registrado(),
                    renSolicitudRenovacion.getSerie_lc_registrado(),
                    renSolicitudRenovacion.getFecha_lc_registrado(),
                    renSolicitudRenovacion.getEstado(),
                    parametro);
            if (!listaren.isEmpty()) {
                return listaren;
            }
            return Collections.EMPTY_LIST;
        } catch (DataAccessException e) {
            throw e;
        }
    }

    @Override
    public RenSolicitudRenovacion cruddosRenSolictudRenovacion(RenSolicitudRenovacion renSolicitudRenovacion, int parametro) throws Exception {
        try {
            String SQL = "select * from crud_rensolicitudrenovacion(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
            List<RenSolicitudRenovacion> listaren = jdbcTemplate.query(SQL, new RenSolicitudRenovacionMapper(),
                    renSolicitudRenovacion.getIdsolicitudrenovacion(),
                    renSolicitudRenovacion.getIdlogtrans(),
                    renSolicitudRenovacion.getId_recibo_solicitud(),
                    renSolicitudRenovacion.getId_recibo_titulo(),
                    renSolicitudRenovacion.getSr(),
                    renSolicitudRenovacion.getGestion_sr(),
                    renSolicitudRenovacion.getFecha_ingreso(),
                    renSolicitudRenovacion.getNumero_formulario(),
                    renSolicitudRenovacion.getEstado_renovacion(),
                    renSolicitudRenovacion.getUbicacion_renovacion(),
                    renSolicitudRenovacion.getNumero_ultima_renovacion(),
                    renSolicitudRenovacion.getSerie_ultima_renovacion(),
                    renSolicitudRenovacion.getNumero_penultima_renovacion(),
                    renSolicitudRenovacion.getSerie_penultima_renovacion(),
                    renSolicitudRenovacion.getOficina(),
                    renSolicitudRenovacion.getNumero_recibo(),
                    renSolicitudRenovacion.getSerie_recibo(),
                    renSolicitudRenovacion.getNumero_titulo(),
                    renSolicitudRenovacion.getSerie_titulo(),
                    renSolicitudRenovacion.getIdclase_niza_reclasificacion(),
                    renSolicitudRenovacion.getLista_productos_limitacion(),
                    renSolicitudRenovacion.getSm(),
                    renSolicitudRenovacion.getSigno_registrado(),
                    renSolicitudRenovacion.getIdclase_niza_registrado(),
                    renSolicitudRenovacion.getTipo_genero(),
                    renSolicitudRenovacion.getNumero_registro_registrado(),
                    renSolicitudRenovacion.getSerie_registro_registrado(),
                    renSolicitudRenovacion.getFecha_registro_registrado(),
                    renSolicitudRenovacion.getMarca_acomp(),
                    renSolicitudRenovacion.getReg_lc_registrado(),
                    renSolicitudRenovacion.getSerie_lc_registrado(),
                    renSolicitudRenovacion.getFecha_lc_registrado(),
                    renSolicitudRenovacion.getEstado(),
                    parametro);
            if (!listaren.isEmpty()) {
                return listaren.get(0);
            }
            return new RenSolicitudRenovacion();
        } catch (DataAccessException e) {
            throw e;
        }
    }

    @Override
    public RenSolicitudRenovacion cruddosRenSolictudRenovacionSubsanacion(RenSolicitudRenovacion renSolicitudRenovacion, FormularioPI104 formularioPI104, Long idLogTrans) throws Exception {
        try {
            int operacionModificar = 2;
            String SQL = "select * from crud_rensolicitudrenovacion(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
            List<RenSolicitudRenovacion> listaren = jdbcTemplate.query(SQL, new RenSolicitudRenovacionMapper(),
                    renSolicitudRenovacion.getIdsolicitudrenovacion(),
                    idLogTrans,
                    renSolicitudRenovacion.getId_recibo_solicitud(),
                    renSolicitudRenovacion.getId_recibo_titulo(),
                    renSolicitudRenovacion.getSr(),
                    renSolicitudRenovacion.getGestion_sr(),
                    renSolicitudRenovacion.getFecha_ingreso(),
                    Long.parseLong(formularioPI104.getFormularios().getNumeroFormulario()),//renSolicitudRenovacion.getNumero_formulario(),
                    renSolicitudRenovacion.getEstado_renovacion(),
                    renSolicitudRenovacion.getUbicacion_renovacion(),
                    formularioPI104.getSolicitudRenovaciones().getNumeroUltimaRenovacion(),//renSolicitudRenovacion.getNumero_ultima_renovacion(),
                    renSolicitudRenovacion.getSerie_ultima_renovacion(),
                    formularioPI104.getSolicitudRenovaciones().getNumeroPenultimaRenovacion(),//renSolicitudRenovacion.getNumero_penultima_renovacion(),
                    renSolicitudRenovacion.getSerie_penultima_renovacion(),
                    renSolicitudRenovacion.getOficina(),
                    renSolicitudRenovacion.getNumero_recibo(),
                    renSolicitudRenovacion.getSerie_recibo(),
                    renSolicitudRenovacion.getNumero_titulo(),
                    renSolicitudRenovacion.getSerie_titulo(),
                    formularioPI104.getSolicitudRenovaciones().getClaseNizaReclasificacion(),//renSolicitudRenovacion.getClase_niza_reclasificacion(),
                    formularioPI104.getSolicitudRenovaciones().getListaProductosLimitacion(),//renSolicitudRenovacion.getLista_productos_limitacion(),
                    renSolicitudRenovacion.getSm(),
                    formularioPI104.getSolicitudRenovaciones().getSignoRegistrado(),//renSolicitudRenovacion.getSigno_registrado(),
                    formularioPI104.getSolicitudRenovaciones().getClaseNizaRegistrado(),//renSolicitudRenovacion.getClase_niza_registrado(),
                    formularioPI104.getSolicitudRenovaciones().getTipoGenero(),//renSolicitudRenovacion.getTipo_genero(),
                    formularioPI104.getSolicitudRenovaciones().getNumeroRegistro(),//renSolicitudRenovacion.getNumero_registro_registrado(),
                    formularioPI104.getSolicitudRenovaciones().getSerieRegistro(),//renSolicitudRenovacion.getSerie_registro_registrado(),
                    formularioPI104.getSolicitudRenovaciones().getFechaOtorgacionMarca(),//renSolicitudRenovacion.getFecha_registro_registrado(),
                    renSolicitudRenovacion.getMarca_acomp(),
                    renSolicitudRenovacion.getReg_lc_registrado(),
                    renSolicitudRenovacion.getSerie_lc_registrado(),
                    renSolicitudRenovacion.getFecha_lc_registrado(),
                    renSolicitudRenovacion.getEstado(),
                    operacionModificar);

            if (!listaren.isEmpty()) {
                return listaren.get(0);
            }
            return new RenSolicitudRenovacion();
        } catch (DataAccessException e) {
            throw e;
        }
    }

    @Override
    public RenSolicitudRenovacion buscaSolicitudRenovacionPorNumeroSrYGestion(Long numeroSr, int gestion) throws Exception {

        try {
            String SQL = "select * from obtiene_rensolicitudrenovacion("
                    + "?,"
                    + "?);";
            List<RenSolicitudRenovacion> listaren = jdbcTemplate.query(SQL, new RenSolicitudRenovacionMapper(),
                    numeroSr,
                    gestion);
            if (!listaren.isEmpty()) {
                return listaren.get(0);
            } else {
                return new RenSolicitudRenovacion();
            }

        } catch (DataAccessException e) {
            throw e;
        }
    }

    @Override
    public void guardaSolicitudGeneral(RenSolicitudRenovacion renSolicitudRenovacion, List<RenSolicitanteApoderado> listarenSolicitante, List<RenSolicitanteApoderado> listarenApoderado, List<RenTitularRegistrado> listaTitularRegistrado, RenDireccionDeNotificacion renDireccionDeNotificacion, List<RenTipoSigno> lstRenTipoSigno, Usuario usuario) throws Exception {
        try {
            System.out.println("renovaciones" + usuario.getIdusuario());

            RenSolicitudRenovacion renSolicitudRenovacionGuardar = new RenSolicitudRenovacion();
            RenDireccionDeNotificacion renDireccionDeNotificacionGuardar = new RenDireccionDeNotificacion();
            LogTrans logTransGuardado = logTransService.crudLogTrans(new LogTrans(usuario.getIdusuario(), comunService.obtenerFechaHoraServidor(1L)), 1);
            int parametro;
            if (renSolicitudRenovacion.getIdsolicitudrenovacion() == null) {
                parametro = 1;
                renSolicitudRenovacion.setIdlogtrans(1L);
                renSolicitudRenovacion.setEstado(EnumEstado.ACTIVO.getCodigo());
                renSolicitudRenovacion.setEstado(EnumEstado.ACTIVO.getCodigo());
                byte ptext[] = renSolicitudRenovacion.getSigno_registrado().getBytes(ISO_8859_1);
                String value = new String(ptext, UTF_8);
                renSolicitudRenovacion.setSigno_registrado(value);

                renSolicitudRenovacionGuardar = cruddosRenSolictudRenovacion(renSolicitudRenovacion, parametro);
                for (RenSolicitanteApoderado renSolicitante : listarenSolicitante) {
                    RenSolicitanteApoderado renSolicitanteApoderadoGuardar = new RenSolicitanteApoderado();
                    renSolicitante.setIdsolicitudrenovacion(renSolicitudRenovacionGuardar.getIdsolicitudrenovacion());
                    renSolicitante.setIdlogtrans(logTransGuardado.getIdLogTrans());
                    renSolicitante.setEstado(EnumEstado.ACTIVO.getCodigo());
                    renSolicitanteApoderadoGuardar = renSolicitanteApoderadoService.crudDosRenSolicitanteApoderado(renSolicitante, parametro);
                }
                for (RenSolicitanteApoderado renApoderado : listarenApoderado) {
                    RenSolicitanteApoderado renSolicitanteApoderadoGuardar = new RenSolicitanteApoderado();
                    renApoderado.setIdsolicitudrenovacion(renSolicitudRenovacionGuardar.getIdsolicitudrenovacion());
                    renApoderado.setIdlogtrans(logTransGuardado.getIdLogTrans());
                    renApoderado.setEstado(EnumEstado.ACTIVO.getCodigo());
                    renSolicitanteApoderadoGuardar = renSolicitanteApoderadoService.crudDosRenSolicitanteApoderado(renApoderado, parametro);
                }
                for (RenTitularRegistrado renTitularRegistrado : listaTitularRegistrado) {
                    RenTitularRegistrado renTitularRegistradoGuardar = new RenTitularRegistrado();
                    renTitularRegistrado.setIdsolicitudrenovacion(renSolicitudRenovacionGuardar.getIdsolicitudrenovacion());
                    renTitularRegistrado.setIdlogtrans(logTransGuardado.getIdLogTrans());
                    renTitularRegistrado.setEstado(EnumEstado.ACTIVO.getCodigo());
                    renTitularRegistradoGuardar = renTitularRegistradoService.crudDosRenTitularRegistrado(renTitularRegistrado, parametro);
                }
                renDireccionDeNotificacion.setIdsolicitudrenovacion(renSolicitudRenovacionGuardar.getIdsolicitudrenovacion());
                renDireccionDeNotificacion.setReferencia_direccion("referencia");
                byte ptextdir[] = renDireccionDeNotificacion.getZona_barrio().getBytes(ISO_8859_1);
                String valuedos = new String(ptextdir, UTF_8);
                renDireccionDeNotificacion.setZona_barrio(valuedos);
                byte ptextavenida[] = renDireccionDeNotificacion.getAvenida_calle().getBytes(ISO_8859_1);
                String valueavenida = new String(ptextavenida, UTF_8);
                renDireccionDeNotificacion.setZona_barrio(valueavenida);
                byte ptextedificio[] = renDireccionDeNotificacion.getEdificio().getBytes(ISO_8859_1);
                String valueedificio = new String(ptextedificio, UTF_8);
                renDireccionDeNotificacion.setZona_barrio(valueedificio);

                renDireccionDeNotificacion.setIdlogtrans(logTransGuardado.getIdLogTrans());
                renDireccionDeNotificacion.setEstado(EnumEstado.ACTIVO.getCodigo());
                renDireccionDeNotificacionGuardar = renDireccionDeNotificacionService.crudDosRenDireccionDeNotificacion(renDireccionDeNotificacion, parametro);
                for (RenTipoSigno renTipoSigno : lstRenTipoSigno) {
                    renTipoSigno.setIdsolicitudrenovacion(renSolicitudRenovacionGuardar.getIdsolicitudrenovacion());
                    renTipoSigno.setEstado(EnumEstado.ACTIVO.getCodigo());

                    renTipoSignoService.crudRenTipoSigno(renTipoSigno, parametro);
                }
            } else {
                parametro = 2;
                System.out.println("modifica renovacion");
//                Historial historial = new Historial();
                Historial historial;
                renHistorialService.guardar_RenHistorial(renSolicitudRenovacion, renDireccionDeNotificacion, listarenSolicitante, listarenApoderado, listaTitularRegistrado, lstRenTipoSigno, usuario);

//                Historial historialGuardado = renHistorialService.guardar_RenHistorial(renSolicitudRenovacion, listarenApoderado, listarenSolicitante, listaTitularRegistrado, renDireccionDeNotificacion, lstRenTipoSigno, usuario.getIdusuario());
                renSolicitudRenovacionGuardar = cruddosRenSolictudRenovacion(renSolicitudRenovacion, parametro);
                renSolicitanteApoderadoService.modificaListaSolicitanteApoderado(renSolicitudRenovacion, listarenSolicitante, "SOLI");
                renSolicitanteApoderadoService.modificaListaApoderado(renSolicitudRenovacion, listarenApoderado, "APOD");
                renTitularRegistradoService.modificaListaTitularRegistrado(renSolicitudRenovacion, listaTitularRegistrado);
                renTipoSignoService.modificarTipoSigno(renSolicitudRenovacion, lstRenTipoSigno);
                renDireccionDeNotificacionGuardar = renDireccionDeNotificacionService.crudDosRenDireccionDeNotificacion(renDireccionDeNotificacion, parametro);
            }
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void actualizarRenovacionPorSubsanacion(RenSolicitudRenovacion renSolicitudRenovacion, RenDireccionDeNotificacion direccionNotificacion, 
            List<RenTipoSigno> lstRenTipoSigno, Usuario usuario, FormularioPI104 formularioPI104) throws Exception {
        try {

            // generar la fecha del sistema y logTrans
            Date fechaSistema = comunService.obtenerFechaHoraServidor(1L);
            LogTrans logTransGuardado = logTransService.crudLogTrans(new LogTrans(usuario.getIdusuario(), fechaSistema), 1);

            // GUARDAR DATOS HISTORICOS
            renHistorialService.guardarRenHistorialSubsanacion(renSolicitudRenovacion, 
                    formularioPI104, 
                    lstRenTipoSigno, 
                    usuario);

            // REALIZAR ACTUALIZACION DE DATOS
            // actualizar solicitud renovacion
            cruddosRenSolictudRenovacionSubsanacion(renSolicitudRenovacion,
                    formularioPI104,
                    logTransGuardado.getIdLogTrans());

            // actualizar tipo signo
            renTipoSignoService.modificarRenTipoSignoSubsanacion(renSolicitudRenovacion,
                    lstRenTipoSigno,
                    formularioPI104.getRenTipoSignos(),
                    logTransGuardado.getIdLogTrans());

            // actualizar solicitante
            renSolicitanteApoderadoService.modificarListaSolicitanteSubsanacion(renSolicitudRenovacion.getIdsolicitudrenovacion(),
                    formularioPI104.getSolicitantes(),
                    EnumTipoPersona.SOLICITANTE.getCodigo(),
                    logTransGuardado.getIdLogTrans());

            // actualizar apoderado
            renSolicitanteApoderadoService.modificarListaApoderadoSubsanacion(renSolicitudRenovacion.getIdsolicitudrenovacion(),
                    formularioPI104.getApoderados(),
                    EnumTipoPersona.APODERADO.getCodigo(),
                    logTransGuardado.getIdLogTrans());

            // actualizar direccion notificacion
            renDireccionDeNotificacionService.crudRenDireccionDeNotificacionSubsanacion(direccionNotificacion,
                    formularioPI104.getDirecciones().get(0),
                    logTransGuardado.getIdLogTrans());

            // actualizar titular registrado
            renTitularRegistradoService.modificarListaTitularRegistradoSubsanacion(renSolicitudRenovacion.getIdsolicitudrenovacion(),
                    formularioPI104.getRenTitularRegistrados(),
                    logTransGuardado.getIdLogTrans());

            //Actualizar el estado del formulario
            formularioService.actualizarRegistroFormularioSubsanacion(formularioPI104.getFormularios().getId());

        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public RenSolicitudRenovacion obtenerRenSolicitudXIdRenSolicitud(Long idRenSolicitud) throws Exception {
        try {
            String SQL = "select * from rensolicitudrenovacion where idsolicitudrenovacion = ?";
            List<RenSolicitudRenovacion> lstRenSolicitud = jdbcTemplate.query(SQL, new RenSolicitudRenovacionMapper(), idRenSolicitud);
            if (!lstRenSolicitud.isEmpty()) {
                return lstRenSolicitud.get(0);
            }
            return new RenSolicitudRenovacion();
        } catch (Exception e) {
            throw e;
        }

    }

    @Override
    public RenSolicitudRenovacion buscarRenSolicitud_NumeroFormulario(String numeroFormulario) throws Exception {
        try {
            numeroFormulario = (numeroFormulario).trim();
            Long numero = Long.valueOf(numeroFormulario);
            String SQL = "select * from rensolicitudrenovacion where nro_formulario=? and estado='AC' ";
            if (!jdbcTemplate.query(SQL, new RenSolicitudRenovacionMapper(), numero).isEmpty()) {
                return (RenSolicitudRenovacion) jdbcTemplate.queryForObject(SQL, new RenSolicitudRenovacionMapper(), numero);
            } else {
                return new RenSolicitudRenovacion();
            }
        } catch (DataAccessException e) {
            throw e;
        }
    }

    @Override
    public RenSolicitudRenovacion buscarRenSolicitudRenovacionXNroFormulario(Long nroFormulario) throws Exception {
        try {
            RenSolicitudRenovacion renSolicitudRenovacion;
            String SQL = "select * from rensolicitudrenovacion where nro_formulario = ? and estado = 'AC' limit 1;";

            if (!jdbcTemplate.query(SQL, new RenSolicitudRenovacionMapper(), nroFormulario).isEmpty()) {
                renSolicitudRenovacion = (RenSolicitudRenovacion) jdbcTemplate.queryForObject(SQL, new RenSolicitudRenovacionMapper(), nroFormulario);
                return renSolicitudRenovacion;
            }
            return null;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public RenSolicitudRenovacion obtenerSiguienteRegistroRenSolicitudRenovacion(Long numero, Integer gestion) {
        try {
            Integer g = gestion;        // variable gestion           
            String SQL = "select * "
                    + " from rensolicitudrenovacion "
                    + " where "
                    + " sr > ? "
                    + " and gestion_sr = ? "
                    + " and estado = 'AC' "
                    + " order by sr, gestion_sr"
                    + " limit 1 ";
            if (!jdbcTemplate.query(SQL, new RenSolicitudRenovacionMapper(), numero, gestion).isEmpty()) {
                return jdbcTemplate.query(SQL, new RenSolicitudRenovacionMapper(), numero, gestion).get(0);
            } else {

                int sw = 0;
                //en caso que NO se encuentre se obtiene el id anterior en estado AC
                g++;
                //armar consulta para obtener el siguiente elemento
                String SQLMinimoSiguienteGestion = "select * "
                        + "from rensolicitudrenovacion "
                        + " where "
                        + " sr = ( "
                        + "          select min(sr) "
                        + "          from rensolicitudrenovacion "
                        + "          where gestion_sr = ? "
                        + "             and estado = 'AC' "
                        + "          )"
                        + " and gestion_sr = ?"
                        + " and estado = 'AC' "
                        + " order by sr, gestion_sr"
                        + " limit 1 ";
                if (!jdbcTemplate.query(SQLMinimoSiguienteGestion, new RenSolicitudRenovacionMapper(), g, g).isEmpty()) {
                    return jdbcTemplate.query(SQLMinimoSiguienteGestion, new RenSolicitudRenovacionMapper(), g, g).get(0);
                } else {
                    String SQLSiguienteGestionValida = "select * "
                            + "from rensolicitudrenovacion "
                            + "where "
                            + " gestion_sr >= ?"
                            + " and estado = 'AC' "
                            + " order by sr, gestion_sr"
                            + " limit 1 ";
                    if (!jdbcTemplate.query(SQLSiguienteGestionValida, new RenSolicitudRenovacionMapper(), g).isEmpty()) {
                        return jdbcTemplate.query(SQLSiguienteGestionValida, new RenSolicitudRenovacionMapper(), g).get(0);
                    } else {
                        return new RenSolicitudRenovacion();
                    }
                }
            }
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public RenSolicitudRenovacion obtenerAnteriorRegistroRenSolicitudRenovacion(Long numero, Integer gestion) {
        try {
            Integer g = gestion;        // variable gestion           
            String SQL = "select * "
                    + " from rensolicitudrenovacion "
                    + " where "
                    + " sr < ? "
                    + " and gestion_sr ='" + g + "' "
                    + " and estado = 'AC' "
                    + " order by sr desc "
                    + " limit 1 ";
            if (!jdbcTemplate.query(SQL, new RenSolicitudRenovacionMapper(), numero).isEmpty()) {
                return jdbcTemplate.query(SQL, new RenSolicitudRenovacionMapper(), numero).get(0);
            } else {
                g--;
                String SQLMinimoSiguienteGestion = "select * "
                        + "from rensolicitudrenovacion "
                        + " where "
                        + " sr = ( "
                        + "          select max(sr) "
                        + "          from rensolicitudrenovacion "
                        + "          where gestion_sr ="
                        + "             and estado = 'AC' "
                        + "          )"
                        + "and gestion_sr='" + g + "'"
                        + " and estado = 'AC' "
                        + " order by sr,gestion_sr"
                        + " limit 1 ";
                if (!jdbcTemplate.query(SQLMinimoSiguienteGestion, new RenSolicitudRenovacionMapper()).isEmpty()) {
                    return jdbcTemplate.query(SQLMinimoSiguienteGestion, new RenSolicitudRenovacionMapper()).get(0);
                } else {
                    String SQLSiguienteGestionValida = "select * "
                            + "from  rensolicitudrenovacion"
                            + "where "
                            + " gestion_sr < '" + g + "'"
                            + " and estado = 'AC' "
                            + " order by gestion_sr desc, sr desc"
                            + " limit 1 ";
                    //preguntar principal si existe o NO el registro
                    if (!jdbcTemplate.query(SQLSiguienteGestionValida, new RenSolicitudRenovacionMapper()).isEmpty()) {
                        return jdbcTemplate.query(SQLSiguienteGestionValida, new RenSolicitudRenovacionMapper()).get(0);
                    } else {
                        return new RenSolicitudRenovacion();
                    }
                }
                //} while (sw == 1);
                // return new ModModificacion();
            }
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Boolean validaNumeroRenovacionRegistro(RenSolicitudRenovacion renSolicitudRenovacion) throws Exception {
        try {
            Boolean valida = false;
            String SQLSiguienteGestionValida = "select * "
                    + "from rensolicitudrenovacion "
                    + "where"
                    + " gestion_sr ='" + renSolicitudRenovacion.getSr() + "'  "
                    + " and sr ='" + renSolicitudRenovacion.getGestion_sr() + "'  ";
            if (!jdbcTemplate.query(SQLSiguienteGestionValida, new RenSolicitudRenovacionMapper()).isEmpty()) {
                valida = true;
            } else {
                valida = false;
            }

            return valida;
        } catch (Exception e) {
            throw e;
        }

    }

    @Override
    public RenSolicitudRenovacion obtenerRegistroRenSolicitudRenovacion(Long numero, Integer gestion) throws Exception {
        try {
            if (numero != null && gestion != null) {
                String SQL = "select * "
                        + " from rensolicitudrenovacion "
                        + " where "
                        + " sr = ? "
                        + " and gestion_sr = ? "
                        + " and estado = 'AC' "
                        + " and nro_formulario <> 0";
                if (!jdbcTemplate.query(SQL, new RenSolicitudRenovacionMapper(), numero, gestion).isEmpty()) {
                    return jdbcTemplate.query(SQL, new RenSolicitudRenovacionMapper(), numero, gestion).get(0);
                }
            }
        } catch (Exception e) {
            throw e;
        }
        return new RenSolicitudRenovacion();
    }

    @Override
    public ObjetoEliminadoGenerico obtenerRegistroEliminadoRenSolicitudRenovacion(Long nroFormulario) throws Exception {
        System.out.println("elimina");
        try {
            if (nroFormulario != null) {
                String SQL = "select codigo_retorno as id, mensaje_retorno as objeto_eliminado from elimina_formulario_pi(?)";
                return jdbcTemplate.query(SQL, new ObjetoEliminadoGenericoMapper(), nroFormulario).get(0);
            }
        } catch (Exception e) {
            throw e;
        }
        return new ObjetoEliminadoGenerico();
    }

    @Override
    public List<RenSolicitudRenovacion> lista_rensolicitudrenovacion_noconcedidas(Long sm, Long registro, String serie) {
        try {
            String SQL = "select * from rensolicitudrenovacion renso\n"
                    + "where estado_renovacion = 'SOLI'\n"
                    + "and (renso.sm=? \n"
                    + "OR (renso.numero_registro_registrado = ?  and renso.serie_registro_registrado = ? ) )\n"
                    + "order by renso.fecha_ingreso desc";

            List<RenSolicitudRenovacion> lstRenSolicitud = jdbcTemplate.query(SQL, new RenSolicitudRenovacionMapper(), sm, registro, serie);

            if (!lstRenSolicitud.isEmpty()) {
                return lstRenSolicitud;
            }
            return new ArrayList<RenSolicitudRenovacion>();
        } catch (EmptyResultDataAccessException e) {
            //Como no se encontro ningun registro enviar la instancia de la nueva marca.
            return new ArrayList<RenSolicitudRenovacion>();
        } catch (Exception e) {
            throw e;
        }
    }

}
