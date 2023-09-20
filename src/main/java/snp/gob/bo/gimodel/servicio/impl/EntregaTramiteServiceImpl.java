/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.servicio.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import snp.gob.bo.gimodel.entidad.Correlativo;
import snp.gob.bo.gimodel.entidad.LogTrans;
import snp.gob.bo.gimodel.entidad.ModModificacion;
import snp.gob.bo.gimodel.entidad.Procurador;
import snp.gob.bo.gimodel.entidad.Regional;
import snp.gob.bo.gimodel.entidad.RenSolicitudRenovacion;
import snp.gob.bo.gimodel.entidad.Seguimiento;
import snp.gob.bo.gimodel.entidad.SigSignoMarca;
import snp.gob.bo.gimodel.entidad.TramiteEntrega;
import snp.gob.bo.gimodel.entidad.Usuario;
import snp.gob.bo.gimodel.enums.EnumEstado;
import snp.gob.bo.gimodel.enums.EnumOperacion;
import snp.gob.bo.gimodel.enums.EnumPrefijoTablas;
import snp.gob.bo.gimodel.enums.EnumTipoTramite;
import snp.gob.bo.gimodel.mapper.TramiteEntregaMapper;
import snp.gob.bo.gimodel.servicio.ComunService;
import snp.gob.bo.gimodel.servicio.CorrelativoService;
import snp.gob.bo.gimodel.servicio.DominioService;
import snp.gob.bo.gimodel.servicio.EntregaTramiteService;
import snp.gob.bo.gimodel.servicio.FlujoTiempoService;
import snp.gob.bo.gimodel.servicio.HistorialService;
import snp.gob.bo.gimodel.servicio.LogTransService;
import snp.gob.bo.gimodel.servicio.ModModificacionService;
import snp.gob.bo.gimodel.servicio.ProcuradorService;
import snp.gob.bo.gimodel.servicio.RegionalService;
import snp.gob.bo.gimodel.servicio.RenSolicitudRenovacionService;
import snp.gob.bo.gimodel.servicio.SeguimientoService;
import snp.gob.bo.gimodel.servicio.SigSignoMarcaService;
import snp.gob.bo.gimodel.servicio.TramiteEntregaService;

/**
 *
 * @author Chano Rojas
 * @see RenRenovacionDominio
 * @see RenrenovacionServiceImpl
 * @version 1.0, 05/06/2016
 */
@Service("entregaTramiteService")
public class EntregaTramiteServiceImpl implements EntregaTramiteService {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    ProcuradorService procuradorService;
    @Autowired
    ComunService comunService;
    @Autowired
    LogTransService logTransService;
    @Autowired
    RegionalService regionalService;
    @Autowired
    CorrelativoService correlativoService;
    @Autowired
    TramiteEntregaService tramiteEntregaService;
    @Autowired
    SeguimientoService seguimientoService;
    @Autowired
    FlujoTiempoService flujoTiempoService;
    @Autowired
    DominioService dominioService;
    @Autowired
    SigSignoMarcaService sigSignoMarcaService;
    @Autowired
    HistorialService historialService;
    @Autowired
    RenSolicitudRenovacionService renSolicitudRenovacionService;
    @Autowired
    ModModificacionService modModificacionService;

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

    @Override
    public List<TramiteEntrega> listaBuscador(
            String codigoBusqueda,
            String sigla,
            int numeroTramite,
            int gestionTramite,
            String extension,
            String soliApod,
            String datoSoliApod,
            int numeroRegistro,
            String serieRegistro,
            int numero_publicacion) throws Exception {
        List<TramiteEntrega> listaTramiteEntrega;
        try {
            String Sql = "select * from busqueda_tramite_entrega(?,?,?,?,?,?,?,?,?,?)";
            listaTramiteEntrega = jdbcTemplate.query(Sql, new TramiteEntregaMapper(), codigoBusqueda, sigla,
                    numeroTramite, gestionTramite, extension, soliApod,
                    datoSoliApod, numeroRegistro, serieRegistro, numero_publicacion);
            if (!listaTramiteEntrega.isEmpty()) {
                return listaTramiteEntrega;
            }
            return Collections.EMPTY_LIST;

        } catch (Exception e) {
            throw e;
        }

    }

    @Transactional
    @Override
    public TramiteEntrega guardaTramiteEntrega(Procurador procurador, List<TramiteEntrega> listaEntrega, String etapa, Usuario usuario) throws Exception {
        Procurador nuevoProcurador;
        TramiteEntrega tramiteEntrega = new TramiteEntrega();
        Date fechaSistema = comunService.obtenerFechaHoraServidor(1L);
        //LogTrans logTransGuardado = logTransService.crudLogTrans(new LogTrans(82l, fechaSistema), 1);
        LogTrans logTransGuardado = logTransService.crudLogTrans(new LogTrans(usuario.getIdusuario(), fechaSistema), 1);
        Date fechaRegistro = comunService.obtenerFechaHoraServidor(1L);
        Regional regional = regionalService.obtenerRegionalPorIdRegiona(usuario.getIdregional());
        Correlativo correlativoEntrega = correlativoService.encuentraCorrelativoTabla(regional.getIdRegional(), EnumTipoTramite.ENTREGA_TRAMITE.getCodigo());
        int numeroactualizacion = correlativoEntrega.getUltimo_numero_asignado();
        correlativoEntrega.setUltimo_numero_asignado(numeroactualizacion+1);
        correlativoService.crudCorrelativo(correlativoEntrega, 2);
        procurador.setIdlogtrans(logTransGuardado.getIdLogTrans());
        procurador.setEstado(EnumEstado.ACTIVO.getCodigo());
        if (procurador.getIdprocurador() == null) {
            nuevoProcurador = procuradorService.guardar_modificar_listar_Procurador(procurador, 1);
        } else {
            nuevoProcurador = procuradorService.guardar_modificar_listar_Procurador(procurador, 2);
        }
        //guardartramiteentrega
        for (TramiteEntrega listaEntregaGuardar : listaEntrega) {
            listaEntregaGuardar.setIdlogtrans(logTransGuardado.getIdLogTrans());
            listaEntregaGuardar.setFecha_entrega(fechaSistema);
            listaEntregaGuardar.setIdprocurador(nuevoProcurador.getIdprocurador());
            listaEntregaGuardar.setIdusuario(usuario.getIdusuario());
            listaEntregaGuardar.setIdregionalentrega(regional.getIdRegional());
            listaEntregaGuardar.setEstado(EnumEstado.ACTIVO.getCodigo());
            listaEntregaGuardar.setNumerobloque(numeroactualizacion);
            listaEntregaGuardar.setGestionbloque(correlativoEntrega.getGestion());
            //metodosuana
            tramiteEntrega = tramiteEntregaService.guardar_modificar_listar_TramiteEntrega(listaEntregaGuardar, 1);
            if (!listaEntregaGuardar.getTipo_tramite().equals("OTR")) {
                creaSeguimiento(tramiteEntrega, etapa, nuevoProcurador);
            }
        }
        return tramiteEntrega;
    }

    public void creaSeguimiento(TramiteEntrega tramiteEntrega, String etapa, Procurador procurador) throws Exception {
        Seguimiento seguimientoNuevo = new Seguimiento();
        Date fechaPresente = comunService.obtenerFechaHoraServidor(1L);
        String observacion = "";
        String entregaDatos = concatenaVariables(tramiteEntrega, procurador);
        if (tramiteEntrega.getTipo_tramite().equals(EnumPrefijoTablas.SIGNO.getCodigo())) {
            seguimientoNuevo.setId(tramiteEntrega.getIdtramite());
            seguimientoNuevo.setIdUsuario(tramiteEntrega.getIdusuario());
            seguimientoNuevo.setIdLogTrans(tramiteEntrega.getIdlogtrans());
            seguimientoNuevo.setSm(tramiteEntrega.getSm());
            seguimientoNuevo.setNumeroPublicacion(tramiteEntrega.getNumero_publicacion());
            seguimientoNuevo.setNumeroRegistro(tramiteEntrega.getNumero_registro());
            seguimientoNuevo.setSerieRegistro(tramiteEntrega.getSerie_registro());
            seguimientoNuevo.setEtapa(etapa);
            seguimientoNuevo.setFechaFin(fechaPresente);
            seguimientoNuevo.setObservacion(entregaDatos);
            seguimientoNuevo.setEditable(false);
            seguimientoNuevo.setEstado(EnumEstado.ACTIVO.getCodigo());
            seguimientoNuevo.setIdUsuario(tramiteEntrega.getIdusuario());
            Seguimiento seguimientoAnterior = seguimientoService.lista_seguimiento_ultimo(EnumPrefijoTablas.SIGNO.getCodigo(), tramiteEntrega.getIdtramite());
            if (seguimientoAnterior != null) {
                seguimientoNuevo.setFechaRecepcion(new Date(seguimientoAnterior.getFechaFin().getTime() + 0005L));
            } else {
                seguimientoNuevo.setFechaRecepcion(fechaPresente);
            }
            seguimientoNuevo.setPlazo_limite(flujoTiempoService.obtieneFlujoXIdetapa(etapa, 1));
            String descripcionUbicacion = dominioService.obtenerNombreCodigoDominio("ubicacion", tramiteEntrega.getUbicacionanterior());
            String descripcionEstado = dominioService.obtenerNombreCodigoDominio("estado_marca", tramiteEntrega.getEstadoanteriortramite());
            SigSignoMarca sigSignoMarca = sigSignoMarcaService.obtenerSigSignoMarcaXidSignoMarca(tramiteEntrega.getIdtramite());
            if (etapa.equals("DESG")) {
                sigSignoMarca.setEstadoMarca("REG");
                sigSignoMarca.setUbicacion("VENE");
                sigSignoMarcaService.crudSigSignoMarca(sigSignoMarca, 2);
                historialService.guardarHistorialGenerico(EnumPrefijoTablas.SIGNO.getCodigo(), sigSignoMarca.getIdSignoMarca(), tramiteEntrega.getIdusuario(), "STATUS",
                        EnumOperacion.MODIFICAR.getCodigo(), dominioService.obtenerNombreCodigoDominio("estado_marca", sigSignoMarca.getEstadoMarca()),
                        observacion, dominioService.obtenerNombreCodigoDominio("ubicacion", sigSignoMarca.getUbicacion()), "[Status:" + descripcionEstado + "][Ubic.:" + descripcionUbicacion + "]");
            }
            if (etapa.equals("ENTR")) {
                sigSignoMarca.setUbicacion("BIBL");
                sigSignoMarcaService.crudSigSignoMarca(sigSignoMarca, 2);
                historialService.guardarHistorialGenerico(EnumPrefijoTablas.SIGNO.getCodigo(), sigSignoMarca.getIdSignoMarca(), tramiteEntrega.getIdusuario(), "STATUS",
                        EnumOperacion.MODIFICAR.getCodigo(), dominioService.obtenerNombreCodigoDominio("estado_marca", sigSignoMarca.getEstadoMarca()),
                        observacion, dominioService.obtenerNombreCodigoDominio("ubicacion", sigSignoMarca.getUbicacion()), "[Ubic.:" + descripcionUbicacion + "]");
            }

        }

        if (tramiteEntrega.getTipo_tramite().equals(EnumPrefijoTablas.RENOVACION.getCodigo())) {
            seguimientoNuevo.setId(tramiteEntrega.getIdtramite());
            seguimientoNuevo.setIdUsuario(tramiteEntrega.getIdusuario());
            seguimientoNuevo.setIdLogTrans(tramiteEntrega.getIdlogtrans());
            seguimientoNuevo.setSm(tramiteEntrega.getSm());
            seguimientoNuevo.setNumeroPublicacion(tramiteEntrega.getNumero_publicacion());
            seguimientoNuevo.setNumeroRegistro(tramiteEntrega.getNumero_registro());
            seguimientoNuevo.setSerieRegistro(tramiteEntrega.getSerie_registro());
            seguimientoNuevo.setEtapa(etapa);
            seguimientoNuevo.setFechaFin(fechaPresente);
            seguimientoNuevo.setObservacion(entregaDatos);
            seguimientoNuevo.setEditable(false);
            seguimientoNuevo.setEstado(EnumEstado.ACTIVO.getCodigo());
            seguimientoNuevo.setIdUsuario(tramiteEntrega.getIdusuario());
            Seguimiento seguimientoAnterior = seguimientoService.lista_seguimiento_ultimo(EnumPrefijoTablas.RENOVACION.getCodigo(), tramiteEntrega.getIdtramite());
            if (seguimientoAnterior != null) {
                seguimientoNuevo.setFechaRecepcion(new Date(seguimientoAnterior.getFechaFin().getTime() + 0005L));
            } else {
                seguimientoNuevo.setFechaRecepcion(fechaPresente);
            }
            seguimientoNuevo.setPlazo_limite(flujoTiempoService.obtieneFlujoXIdetapa(etapa, 1));
            String descripcionUbicacion = dominioService.obtenerNombreCodigoDominio("ubicacion", tramiteEntrega.getUbicacionanterior());
            String descripcionEstado = dominioService.obtenerNombreCodigoDominio("estado_marca", tramiteEntrega.getEstadoanteriortramite());
            RenSolicitudRenovacion renSolicitudRenovacion = renSolicitudRenovacionService.obtenerRenSolicitudXIdRenSolicitud(tramiteEntrega.getIdtramite());
//            System.out.println("renovacion" + renSolicitudRenovacion.getIdsolicitudrenovacion() + "*************");

            if (etapa.equals("DESG")) {
                renSolicitudRenovacion.setUbicacion_renovacion("Ventanilla Unica (Entrega de documentos)");
                renSolicitudRenovacionService.cruddosRenSolictudRenovacion(renSolicitudRenovacion, 2);
                historialService.guardarHistorialGenerico(EnumPrefijoTablas.RENOVACION.getCodigo(), tramiteEntrega.getIdtramite(), tramiteEntrega.getIdusuario(), "DATOS ADMINISTRATIVOS",
                        EnumOperacion.MODIFICAR.getCodigo(), dominioService.obtenerNombreCodigoDominio("estado_renovacion", renSolicitudRenovacion.getEstado_renovacion()),
                        observacion, "Ventanilla Unica (Entrega de documentos)", "[Ubic.:" + descripcionUbicacion + "]");
            }
            if (etapa.equals("ENTR")) {
                renSolicitudRenovacion.setUbicacion_renovacion("Biblioteca (Concedida)");
                renSolicitudRenovacionService.cruddosRenSolictudRenovacion(renSolicitudRenovacion, 2);
                historialService.guardarHistorialGenerico(EnumPrefijoTablas.RENOVACION.getCodigo(), renSolicitudRenovacion.getIdsolicitudrenovacion(), tramiteEntrega.getIdusuario(), "DATOS ADMINISTRATIVOS",
                        EnumOperacion.MODIFICAR.getCodigo(), dominioService.obtenerNombreCodigoDominio("estado_renovacion", renSolicitudRenovacion.getEstado_renovacion()),
                        observacion, "Biblioteca (Concedida)", "[Ubic.:" + descripcionUbicacion + "]");
            }

        }

        if (tramiteEntrega.getTipo_tramite().equals(EnumPrefijoTablas.MODIFICACION.getCodigo())) {
            seguimientoNuevo.setId(tramiteEntrega.getIdtramite());
            seguimientoNuevo.setIdUsuario(tramiteEntrega.getIdusuario());
            seguimientoNuevo.setIdLogTrans(tramiteEntrega.getIdlogtrans());
            seguimientoNuevo.setSm(tramiteEntrega.getSm());
            seguimientoNuevo.setNumeroPublicacion(tramiteEntrega.getNumero_publicacion());
            seguimientoNuevo.setNumeroRegistro(tramiteEntrega.getNumero_registro());
            seguimientoNuevo.setSerieRegistro(tramiteEntrega.getSerie_registro());
            seguimientoNuevo.setEtapa(etapa);
            seguimientoNuevo.setFechaFin(fechaPresente);
            seguimientoNuevo.setObservacion(entregaDatos);
            seguimientoNuevo.setEditable(false);
            seguimientoNuevo.setEstado(EnumEstado.ACTIVO.getCodigo());
            seguimientoNuevo.setIdUsuario(tramiteEntrega.getIdusuario());
            Seguimiento seguimientoAnterior = seguimientoService.lista_seguimiento_ultimo(EnumPrefijoTablas.RENOVACION.getCodigo(), tramiteEntrega.getIdtramite());
            if (seguimientoAnterior != null) {
                seguimientoNuevo.setFechaRecepcion(new Date(seguimientoAnterior.getFechaFin().getTime() + 0005L));
            } else {
                seguimientoNuevo.setFechaRecepcion(fechaPresente);
            }
            seguimientoNuevo.setPlazo_limite(flujoTiempoService.obtieneFlujoXIdetapa(etapa, 1));
            String descripcionUbicacion = dominioService.obtenerNombreCodigoDominio("ubicacion", tramiteEntrega.getUbicacionanterior());
            String descripcionEstado = dominioService.obtenerNombreCodigoDominio("estado_marca", tramiteEntrega.getEstadoanteriortramite());
            ModModificacion modModificacion = modModificacionService.buscarModModificacionXid(tramiteEntrega.getIdtramite());
            if (etapa.equals("DESG")) {
                modModificacion.setUbicacion_modificacion("VEEM");
                historialService.guardarHistorialGenerico(EnumPrefijoTablas.MODIFICACION.getCodigo(), tramiteEntrega.getIdtramite(), tramiteEntrega.getIdusuario(), "DATOS ADMINISTRATIVOS",
                        EnumOperacion.MODIFICAR.getCodigo(), dominioService.obtenerNombreCodigoDominio("estado_modificacion", tramiteEntrega.getEstadoanteriortramite()),
                        null, dominioService.obtenerNombreCodigoDominio("ubicacion_modificacion", tramiteEntrega.getUbicacionanterior()), "[Ubic.:" + descripcionUbicacion + "]");
            }
            if (etapa.equals("ENTR")) {
                modModificacion.setUbicacion_modificacion("BIBM");
                historialService.guardarHistorialGenerico(EnumPrefijoTablas.MODIFICACION.getCodigo(), tramiteEntrega.getIdtramite(), tramiteEntrega.getIdusuario(), "DATOS ADMINISTRATIVOS",
                        EnumOperacion.MODIFICAR.getCodigo(), dominioService.obtenerNombreCodigoDominio("estado_modificacion", tramiteEntrega.getEstadoanteriortramite()),
                        null, dominioService.obtenerNombreCodigoDominio("ubicacion_modificacion", tramiteEntrega.getUbicacionanterior()), "[Ubic.:" + descripcionUbicacion + "]");
            }
            modModificacionService.guardar_modificar_listar_ModModificacion(modModificacion, 2);
        }

        seguimientoService.guardar_modificar_Seguimiento_etapa(seguimientoNuevo, tramiteEntrega.getTipo_tramite()); //        }
    }

    public String concatenaVariables(TramiteEntrega tramiteEntrega, Procurador procurador) {
        String campoNombreRazonSocial = " ";
        if (procurador.getNombre_razonsocial() != null && !procurador.getNombre_razonsocial().equals("")) {
            campoNombreRazonSocial = procurador.getNombre_razonsocial();
        }
        if (procurador.getPrimer_apellido() != null && !procurador.getPrimer_apellido().equals("")) {
            campoNombreRazonSocial = campoNombreRazonSocial + " " + procurador.getPrimer_apellido();
        }
        if (procurador.getSegundo_apellido() != null && !procurador.getSegundo_apellido().equals("")) {
            campoNombreRazonSocial = campoNombreRazonSocial + " " + procurador.getSegundo_apellido();
        }

        String labelSigla;
        int ope = (tramiteEntrega.getIdregionalentrega().intValue());
        switch (ope) {
            case 2:
                labelSigla = "-E";
                break;
            case 3:
                labelSigla = "-C";
                break;
            case 4:
                labelSigla = "-S";
                break;
            case 5:
                labelSigla = "-T";
                break;
            case 6:
                labelSigla = "-H";
                break;
            case 7:
                labelSigla = "-O";
                break;
            default:
                labelSigla = "";
                break;
        }
        String bloqueentrega = tramiteEntrega.getNumerobloque() + labelSigla + "-" + tramiteEntrega.getGestion();
        return "Entrega:" + bloqueentrega + "|" + campoNombreRazonSocial;
    }

    @Override
    public List<TramiteEntrega> tramitesPorBloque(Integer numero, Integer gestion, Long idregional) throws Exception {
        try {
            String SQL = "select * from tramiteentrega where numerobloque = ? and gestionbloque = ? and idregionalentrega=? and estado='AC'";
            List<TramiteEntrega> listaTramite = jdbcTemplate.query(SQL, new TramiteEntregaMapper(), numero, gestion, idregional);
            if (!listaTramite.isEmpty()) {
                return listaTramite;
            }
        } catch (Exception e) {
        }
        return Collections.EMPTY_LIST;
    }

    @Transactional
    @Override
    public TramiteEntrega modificaTramiteEntrega(Procurador procurador, List<TramiteEntrega> listaEntrega, String etapa, Usuario usuario) throws Exception {
        try {
            Procurador nuevoProcurador;
            TramiteEntrega tramiteEntrega = new TramiteEntrega();
            Date fechaSistema = comunService.obtenerFechaHoraServidor(1L);
            LogTrans logTransGuardado = logTransService.crudLogTrans(new LogTrans(usuario.getIdusuario(), fechaSistema), 1);
            Date fechaRegistro = comunService.obtenerFechaHoraServidor(1L);
            Regional regional = regionalService.obtenerRegionalPorIdRegiona(usuario.getIdregional());
            procurador.setIdlogtrans(logTransGuardado.getIdLogTrans());
            procurador.setEstado(EnumEstado.ACTIVO.getCodigo());
            if (procurador.getIdprocurador() == null) {
                nuevoProcurador = procuradorService.guardar_modificar_listar_Procurador(procurador, 1);
            } else {
                nuevoProcurador = procuradorService.guardar_modificar_listar_Procurador(procurador, 2);
            }
//        modificar tramite entrega y adicionar lista de eliminados

            List<TramiteEntrega> lstBaseAuxiliar = tramitesPorBloque(listaEntrega.get(0).getNumerobloque(), listaEntrega.get(0).getGestionbloque(), usuario.getIdregional());
            List<TramiteEntrega> lstTramiteEliminado = new ArrayList<>();
            int swsegui = 0;
            for (TramiteEntrega lstBasetramite : lstBaseAuxiliar) {
                int sw = 0;
                for (TramiteEntrega lstTramiteVista : listaEntrega) {
                    if (Objects.equals(lstBasetramite.getIdtramiteentrega(), lstTramiteVista.getIdtramiteentrega())) {
                        sw = 1;
                    }
                    if (swsegui == 0) {
                        lstTramiteVista.setIdlogtrans(logTransGuardado.getIdLogTrans());
                        lstTramiteVista.setIdusuario(usuario.getIdusuario());
                        lstTramiteVista.setIdregionalentrega(regional.getIdRegional());
                        tramiteEntrega = tramiteEntregaService.guardar_modificar_listar_TramiteEntrega(lstTramiteVista, 2);
                    }
                }
                swsegui = 1;
                if (sw == 0) {
                    actualizaEtapaUbicacioneliminadoEntrega(lstBasetramite, etapa, usuario);
                    lstBasetramite.setIdlogtrans(logTransGuardado.getIdLogTrans());
                    lstBasetramite.setIdusuario(usuario.getIdusuario());
                    lstBasetramite.setEstado(EnumEstado.NO_ACTIVO.getCodigo());
                    tramiteEntregaService.guardar_modificar_listar_TramiteEntrega(lstBasetramite, 2);
                }
            }
            return tramiteEntrega;
        } catch (Exception e) {
            throw e;
        }
    }

    //metodo que actualiza las tablas de modificacion, renovacion, marcas y seguimiento
    public void actualizaEtapaUbicacioneliminadoEntrega(TramiteEntrega tramiteEliminado, String etapa, Usuario usuario) throws Exception {
        try {
            int ope;
            switch (tramiteEliminado.getTipo_tramite()) {
                case "SIG":
//                    System.out.println("llama a signos");
                    actualizaMarca(tramiteEliminado, etapa, usuario);
                    break;
                case "REN":
//                    System.out.println("llama a renovacion");
                    actualizaRenovacion(tramiteEliminado, etapa, usuario);
                    break;
                case "MOD":
//                    System.out.println("llama a modificacion");
                    actualizaModificacion(tramiteEliminado, etapa, usuario);
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
        }

    }

    @Override
    public void actualizaMarca(TramiteEntrega entregaSignos, String etapa, Usuario usuario) throws Exception {
        SigSignoMarca sigSignoMarca = sigSignoMarcaService.obtenerSigSignoMarcaXidSignoMarca(entregaSignos.getIdtramite());
//        seguimientoNuevo.setPlazo_limite(flujoTiempoService.obtieneFlujoXIdetapa(etapa, 1));
        String descripcionUbicacion = dominioService.obtenerNombreCodigoDominio("ubicacion", sigSignoMarca.getUbicacion());
        String descripcionEstado = dominioService.obtenerNombreCodigoDominio("estado_marca", sigSignoMarca.getEstadoMarca());
        String observacion = "";
        Seguimiento seguimienoBorrar = new Seguimiento();
        if (etapa.equals("DESG")) {
            sigSignoMarca.setEstadoMarca(entregaSignos.getEstadoanteriortramite());
            sigSignoMarca.setUbicacion(entregaSignos.getUbicacionanterior());
            sigSignoMarcaService.crudSigSignoMarca(sigSignoMarca, 2);
            historialService.guardarHistorialGenerico(EnumPrefijoTablas.SIGNO.getCodigo(), sigSignoMarca.getIdSignoMarca(), usuario.getIdusuario(), "STATUS",
                    EnumOperacion.MODIFICAR.getCodigo(), dominioService.obtenerNombreCodigoDominio("estado_marca", sigSignoMarca.getEstadoMarca()),
                    observacion, dominioService.obtenerNombreCodigoDominio("ubicacion", sigSignoMarca.getUbicacion()), "[Status:" + descripcionEstado + "][Ubic.:" + descripcionUbicacion + "]");
            seguimienoBorrar = seguimientoService.ultimo_Seguimiento_etapa(EnumPrefijoTablas.SIGNO.getCodigo(), sigSignoMarca.getIdSignoMarca(), "DESG");
        }
        if (etapa.equals("ENTR")) {
            sigSignoMarca.setUbicacion(entregaSignos.getUbicacionanterior());
            sigSignoMarcaService.crudSigSignoMarca(sigSignoMarca, 2);
            historialService.guardarHistorialGenerico(EnumPrefijoTablas.SIGNO.getCodigo(), sigSignoMarca.getIdSignoMarca(), usuario.getIdusuario(), "STATUS",
                    EnumOperacion.MODIFICAR.getCodigo(), dominioService.obtenerNombreCodigoDominio("estado_marca", sigSignoMarca.getEstadoMarca()),
                    observacion, dominioService.obtenerNombreCodigoDominio("ubicacion", sigSignoMarca.getUbicacion()), "[Ubic.:" + descripcionUbicacion + "]");
            seguimienoBorrar = seguimientoService.ultimo_Seguimiento_etapa(EnumPrefijoTablas.SIGNO.getCodigo(), sigSignoMarca.getIdSignoMarca(), "ENTR");
        }
        //archivo
        if (seguimienoBorrar != null) {
            seguimienoBorrar.setEstado(EnumEstado.NO_ACTIVO.getCodigo());
            seguimientoService.guardar_modificar_listar_Seguimiento(seguimienoBorrar, EnumPrefijoTablas.SIGNO.getCodigo(), 2);
        }
    }

    @Override
    public void actualizaModificacion(TramiteEntrega entregaModifiacion, String etapa, Usuario usuario) throws Exception {
        ModModificacion modificacion = modModificacionService.buscarModModificacionXid(entregaModifiacion.getIdtramite());
        String descripcionUbicacion = dominioService.obtenerNombreCodigoDominio("ubicacion", modificacion.getUbicacion_modificacion());
        String descripcionEstado = dominioService.obtenerNombreCodigoDominio("estado_marca", modificacion.getEstado_modificacion());
        String observacion = "";
        Seguimiento seguimienoBorrar = new Seguimiento();
        if (etapa.equals("DESG")) {
            modificacion.setUbicacion_modificacion(entregaModifiacion.getEstadoanteriortramite());
            modificacion.setUbicacion_modificacion(entregaModifiacion.getUbicacionanterior());
            modModificacionService.guardar_modificar_listar_ModModificacion(modificacion, 2);
            historialService.guardarHistorialGenerico(EnumPrefijoTablas.MODIFICACION.getCodigo(), modificacion.getIdmodificacion(), usuario.getIdusuario(), "STATUS",
                    EnumOperacion.MODIFICAR.getCodigo(), dominioService.obtenerNombreCodigoDominio("estado_marca", modificacion.getEstado_modificacion()),
                    observacion, dominioService.obtenerNombreCodigoDominio("ubicacion", modificacion.getUbicacion_modificacion()), "[Status:" + descripcionEstado + "][Ubic.:" + descripcionUbicacion + "]");
            seguimienoBorrar = seguimientoService.ultimo_Seguimiento_etapa(EnumPrefijoTablas.MODIFICACION.getCodigo(), modificacion.getIdmodificacion(), "DESG");
        }
        if (etapa.equals("ENTR")) {
            modificacion.setUbicacion_modificacion(entregaModifiacion.getUbicacionanterior());
            modModificacionService.guardar_modificar_listar_ModModificacion(modificacion, 2);
            historialService.guardarHistorialGenerico(EnumPrefijoTablas.MODIFICACION.getCodigo(), modificacion.getIdmodificacion(), usuario.getIdusuario(), "STATUS",
                    EnumOperacion.MODIFICAR.getCodigo(), dominioService.obtenerNombreCodigoDominio("estado_marca", modificacion.getEstado_modificacion()),
                    observacion, dominioService.obtenerNombreCodigoDominio("ubicacion", modificacion.getUbicacion_modificacion()), "[Ubic.:" + descripcionUbicacion + "]");
            seguimienoBorrar = seguimientoService.ultimo_Seguimiento_etapa(EnumPrefijoTablas.MODIFICACION.getCodigo(), modificacion.getIdmodificacion(), "ENTR");
        }
        //archivo

        if (seguimienoBorrar != null) {
            seguimienoBorrar.setEstado(EnumEstado.NO_ACTIVO.getCodigo());
            seguimientoService.guardar_modificar_listar_Seguimiento(seguimienoBorrar, EnumPrefijoTablas.MODIFICACION.getCodigo(), 2);
        }
    }
    

    @Override
    public void actualizaRenovacion(TramiteEntrega entregaRenovacion, String etapa, Usuario usuario) throws Exception {
        RenSolicitudRenovacion solicitudRenovacion = renSolicitudRenovacionService.obtenerRenSolicitudXIdRenSolicitud(entregaRenovacion.getIdtramite());
        String descripcionUbicacion = dominioService.obtenerNombreCodigoDominio("ubicacion", solicitudRenovacion.getUbicacion_renovacion());
        String descripcionEstado = dominioService.obtenerNombreCodigoDominio("estado_marca", solicitudRenovacion.getEstado_renovacion());
        String observacion = "";
        Seguimiento seguimienoBorrar = new Seguimiento();
        if (etapa.equals("DESG")) {
            solicitudRenovacion.setEstado_renovacion(entregaRenovacion.getEstadoanteriortramite());
            solicitudRenovacion.setUbicacion_renovacion(entregaRenovacion.getUbicacionanterior());
            renSolicitudRenovacionService.crudRenSolictudRenovacion(solicitudRenovacion, 2);
            historialService.guardarHistorialGenerico(EnumPrefijoTablas.RENOVACION.getCodigo(), solicitudRenovacion.getIdsolicitudrenovacion(), usuario.getIdusuario(), "STATUS",
                    EnumOperacion.MODIFICAR.getCodigo(), dominioService.obtenerNombreCodigoDominio("estado_marca", solicitudRenovacion.getEstado_renovacion()),
                    observacion, dominioService.obtenerNombreCodigoDominio("ubicacion", solicitudRenovacion.getUbicacion_renovacion()), "[Status:" + descripcionEstado + "][Ubic.:" + descripcionUbicacion + "]");
            seguimienoBorrar = seguimientoService.ultimo_Seguimiento_etapa(EnumPrefijoTablas.RENOVACION.getCodigo(), solicitudRenovacion.getIdsolicitudrenovacion(), "DESG");
        }
        if (etapa.equals("ENTR")) {
            solicitudRenovacion.setUbicacion_renovacion(entregaRenovacion.getUbicacionanterior());
            renSolicitudRenovacionService.crudRenSolictudRenovacion(solicitudRenovacion, 2);
            historialService.guardarHistorialGenerico(EnumPrefijoTablas.RENOVACION.getCodigo(), solicitudRenovacion.getIdsolicitudrenovacion(), usuario.getIdusuario(), "STATUS",
                    EnumOperacion.MODIFICAR.getCodigo(), dominioService.obtenerNombreCodigoDominio("estado_marca", solicitudRenovacion.getEstado_renovacion()),
                    observacion, dominioService.obtenerNombreCodigoDominio("ubicacion", solicitudRenovacion.getUbicacion_renovacion()), "[Ubic.:" + descripcionUbicacion + "]");
            seguimienoBorrar = seguimientoService.ultimo_Seguimiento_etapa(EnumPrefijoTablas.RENOVACION.getCodigo(), solicitudRenovacion.getIdsolicitudrenovacion(), "ENTR");
        }
        //archivo

        if (seguimienoBorrar != null) {
            seguimienoBorrar.setEstado(EnumEstado.NO_ACTIVO.getCodigo());
            seguimientoService.guardar_modificar_listar_Seguimiento(seguimienoBorrar, EnumPrefijoTablas.RENOVACION.getCodigo(), 2);
        }
    }

    @Override
    public Boolean verificaTramiteEntrega(Long idtramite, String tipoTramite) throws Exception {
        try {
            String SQL = "select * from tramiteentrega where idtramite = ? and tipo_tramite = ? and estado='AC'";
            List<TramiteEntrega> listaTramite = jdbcTemplate.query(SQL, new TramiteEntregaMapper(), idtramite, tipoTramite);
            return !listaTramite.isEmpty();
        } catch (Exception e) {
            throw e;
        }
    }
    
    
    @Override
    public Boolean verificaTramiteEntregaOtros(  String sigla,Integer numerotramite,Integer gestion) throws Exception {
        try {
            String SQL = "select * from tramiteentrega where sigla = ? and numerotramite = ? and gestion=? and estado='AC'";
            List<TramiteEntrega> listaTramite = jdbcTemplate.query(SQL, new TramiteEntregaMapper(), sigla, numerotramite,gestion);
            return !listaTramite.isEmpty();
        } catch (Exception e) {
            throw e;
        }
    }
    
}
