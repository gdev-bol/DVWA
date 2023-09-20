/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.servicio.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import snp.gob.bo.gimodel.entidad.ClaseNiza;
import snp.gob.bo.gimodel.entidad.Historial;
import snp.gob.bo.gimodel.entidad.LogTrans;
import snp.gob.bo.gimodel.entidad.ModDireccionDeNotificacion;
import snp.gob.bo.gimodel.entidad.ModModificacion;
import snp.gob.bo.gimodel.entidad.ModSolicitanteApoderado;
import snp.gob.bo.gimodel.entidad.ModTipoSigno;
import snp.gob.bo.gimodel.entidad.ModTitularLicenciatarioNuevo;
import snp.gob.bo.gimodel.entidad.ModTitularLicenciatarioRegistrado;
import snp.gob.bo.gimodel.entidad.ObjetoEliminadoGenerico;
import snp.gob.bo.gimodel.enums.EnumEstado;
import snp.gob.bo.gimodel.enums.EnumOperacion;
import snp.gob.bo.gimodel.enums.EnumSeccion;
import snp.gob.bo.gimodel.enums.EnumTipoPersona;
import snp.gob.bo.gimodel.servicio.ClaseNizaService;
import snp.gob.bo.gimodel.servicio.ComunService;
import snp.gob.bo.gimodel.servicio.DominioService;
import snp.gob.bo.gimodel.servicio.HistorialService;
import snp.gob.bo.gimodel.servicio.LogTransService;
import snp.gob.bo.gimodel.servicio.ModDireccionDeNotificacionService;
import snp.gob.bo.gimodel.servicio.ModHistorialService;
import snp.gob.bo.gimodel.servicio.ModModificacionService;
import snp.gob.bo.gimodel.servicio.ModSolicitanteApoderadoService;
import snp.gob.bo.gimodel.servicio.ModTipoSignoService;
import snp.gob.bo.gimodel.servicio.ModTitularLicenciatarioNuevoService;
import snp.gob.bo.gimodel.servicio.ModTitularLicenciatarioRegistradoService;
import snp.gob.bo.gimodel.servicio.SigSignoMarcaService;
import snp.gob.bo.gimodel.servicio.UsuarioService;
import snp.gob.bo.gimodel.solicitudes.entidades.Apoderados;
import snp.gob.bo.gimodel.solicitudes.entidades.DireccionNotificaciones;
import snp.gob.bo.gimodel.solicitudes.entidades.FormularioPI103;
import snp.gob.bo.gimodel.solicitudes.entidades.ModTipoSignos;
import snp.gob.bo.gimodel.solicitudes.entidades.Modificaciones;
import snp.gob.bo.gimodel.solicitudes.entidades.Solicitantes;
import snp.gob.bo.gimodel.solicitudes.entidades.TitularLicenciatarios;
import snp.gob.bo.gimodel.solicitudes.entidades.TitularLicenciatariosNuevos;

/**
 *
 * @author susana
 */
@Service("modHistorialService")
public class ModHistorialServiceImpl implements ModHistorialService {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    ModSolicitanteApoderadoService modSolicitanteApoderadoService;

    @Autowired
    ModDireccionDeNotificacionService modDireccionDeNotificacionService;

    @Autowired
    ModModificacionService modModificacionService;

    @Autowired
    HistorialService historialService;

    @Autowired
    DominioService dominioService;

    @Autowired
    ModTipoSignoService modTipoSignoService;

    @Autowired
    ComunService comunService;

    @Autowired
    LogTransService logTransService;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    ClaseNizaService claseNizaService;

    @Autowired
    ModTitularLicenciatarioRegistradoService modTitularLicenciatarioRegistradoService;

    @Autowired
    ModTitularLicenciatarioNuevoService modTitularLicencitarioNuevoService;

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
    public Long guardar_ModHistorial(ModModificacion modificacion, ModDireccionDeNotificacion modDireccionDeNotificacion, List<ModSolicitanteApoderado> lstSolicitantes,
            List<ModSolicitanteApoderado> lstApoderados, List<ModTitularLicenciatarioRegistrado> listaTitularRegistrado, List<ModTitularLicenciatarioNuevo> listaNuevoTitular,
            List<ModTitularLicenciatarioRegistrado> listaParticipanteFusion, List<ModTipoSigno> listaModTipoSigno, Long idUsuario) {
        Long idLogTrans = 0l;
        try {
            //List<Usuario> listUsuario=usuarioService.listaUsuarioXidPagina(idUsuario);
            Date fechaSistema = comunService.obtenerFechaHoraServidor(1L);
            LogTrans logTransGuardado = logTransService.crudLogTrans(new LogTrans(idUsuario, fechaSistema), 1);
            idLogTrans = logTransGuardado.getIdLogTrans();
            String estado_modificacion = dominioService.obtenerNombreCodigoDominio("estado_modificacion", modificacion.getEstado_modificacion());
            String ubicacion_modificacion = dominioService.obtenerNombreCodigoDominio("ubicacion_modificacion", modificacion.getUbicacion_modificacion());

            //generar el historial del solicitante
            generarHistorialModSolicitanteApoderado(modificacion.getIdmodificacion(),
                    lstSolicitantes,
                    EnumTipoPersona.SOLICITANTE.getCodigo(),
                    estado_modificacion,
                    ubicacion_modificacion,
                    fechaSistema,
                    logTransGuardado.getIdLogTrans(),
                    idUsuario);

            //generar el historial del apoderado
            generarHistorialModSolicitanteApoderado(modificacion.getIdmodificacion(),
                    lstApoderados,
                    EnumTipoPersona.APODERADO.getCodigo(),
                    estado_modificacion,
                    ubicacion_modificacion,
                    fechaSistema,
                    logTransGuardado.getIdLogTrans(),
                    idUsuario);
            //generar el historial de la dirección
            generarHistorialModDireccionDeNotificacion(modificacion.getIdmodificacion(),
                    modDireccionDeNotificacion,
                    estado_modificacion,
                    ubicacion_modificacion,
                    fechaSistema,
                    logTransGuardado.getIdLogTrans(),
                    idUsuario);

            //generar historial de referencia solicitud
            generarHistorialReferenciaModificacion(modificacion,
                    estado_modificacion,
                    ubicacion_modificacion,
                    fechaSistema,
                    logTransGuardado.getIdLogTrans(),
                    idUsuario);
            //generar historial de datos del titular registrado en seccion DATOS SIGNO REGISTRADO

            generarHistorialTitularSignoRegistrado(modificacion,
                    listaTitularRegistrado,
                    estado_modificacion,
                    ubicacion_modificacion,
                    fechaSistema,
                    idLogTrans, "TREG",
                    idUsuario);

            //historia de tiposigno
            guardar_ModHistorialModTipoSigno(listaModTipoSigno,
                    modificacion.getIdmodificacion(),
                    estado_modificacion,
                    ubicacion_modificacion,
                    fechaSistema,
                    idLogTrans,
                    idUsuario);

            //generar historial de datos del signo registrado 
            generarHistorialTitularSignoRegistrado(modificacion,
                    listaParticipanteFusion,
                    estado_modificacion,
                    ubicacion_modificacion,
                    fechaSistema,
                    idLogTrans, "PFUS",
                    idUsuario);

            generarHistorialDatosSignoRegistrado(modificacion,
                    estado_modificacion,
                    ubicacion_modificacion,
                    fechaSistema,
                    idLogTrans,
                    idUsuario);

            generarHistorialModTitularLicenciatarioNuevo(modificacion.getIdmodificacion(),
                    listaNuevoTitular,
                    estado_modificacion,
                    ubicacion_modificacion,
                    fechaSistema,
                    idLogTrans,
                    idUsuario);

            generarHistorialDatosAdminitrativos(modificacion,
                    estado_modificacion,
                    ubicacion_modificacion,
                    fechaSistema,
                    idLogTrans,
                    idUsuario);

        } catch (Exception ex) {
            Logger.getLogger(ModHistorialServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return idLogTrans;
    }

    @Override
    public void generarHistorialModSolicitanteSubsanacion(Long idModificacion, List<Solicitantes> listaSolicitantesSipi, String tipoPersona,
            String estado_modificacion, String ubicacion_modificacion, Date fechaSistema, Long idLogTrans, Long idUsuario) throws Exception {
        try {
            String historial = "";
            int swExiste = 0;

            // Recuperamos el Solicitante desde la base de datos
            List<ModSolicitanteApoderado> listaSolicitantesHidra = modSolicitanteApoderadoService.listadoSolicitanteXidmodificacion(idModificacion);

            if (!listaSolicitantesHidra.isEmpty()) {
                if (!listaSolicitantesSipi.isEmpty()) {

                    for (ModSolicitanteApoderado solicitanteHidra : listaSolicitantesHidra) {
                        swExiste = 0;
                        for (Solicitantes solicitanteSipi : listaSolicitantesSipi) {
                            if (solicitanteHidra.getIdSipi().equals(solicitanteSipi.getIdPadre())) {
                                // modificar
                                historial = historial + modificarModSolicitanteSubsanacion(solicitanteHidra, solicitanteSipi);
                                swExiste = 1;
                                break;
                            }
                        }
                        if (swExiste == 0) {
                            // eliminar
                            historial = historial + eliminarModSolicitanteSubsanacion(solicitanteHidra);
                        }
                    }

                    for (Solicitantes solicitanteSipi : listaSolicitantesSipi) {
                        swExiste = 0;
                        for (ModSolicitanteApoderado solicitanteHidra : listaSolicitantesHidra) {
                            if (solicitanteHidra.getIdSipi().equals(solicitanteSipi.getIdPadre())) {
                                // solo virificar que exista
                                swExiste = 1;
                                break;
                            }
                        }
                        if (swExiste == 0) {
                            // adicionar
                            historial = historial + adicionarModSolicitanteSubsanacion(solicitanteSipi);
                        }
                    }

                } else {
                    // eliminar
                    historial = "DEL: ";
                    for (ModSolicitanteApoderado solicitanteHidra : listaSolicitantesHidra) {
                        historial = historial + eliminarModSolicitanteSubsanacion(solicitanteHidra);
                    }
                }

            } else {
                // adicionar
                historial = "ADD: ";
                for (Solicitantes solicitanteSipi : listaSolicitantesSipi) {
                    historial = historial + adicionarModSolicitanteSubsanacion(solicitanteSipi);
                }
            }

            historial = historial.trim();

            //si historial es vacío, entonces hay al menos una modificacion
            if (!historial.equals("")) {
                historial = "[" + historial + "]";

                Historial historialTramite = new Historial();
                historialTramite.setId(idModificacion);
                historialTramite.setIdUsuario(idUsuario);
                historialTramite.setIdLogTrans(idLogTrans);
                historialTramite.setOperacion(EnumOperacion.MODIFICAR.getCodigo());
                historialTramite.setEstadoMarcaDescripcion(estado_modificacion);
                historialTramite.setUbicacionDescripcion(ubicacion_modificacion);
                historialTramite.setSeccion(EnumSeccion.SOLICITANTE.getCodigo());
                historialTramite.setDescripcion(historial);
                historialTramite.setFechaOperacion(fechaSistema);
                historialTramite.setEstado(EnumEstado.ACTIVO.getCodigo());

                historialService.crudHistorial(historialTramite, "MOD", 1);
            }

        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public String modificarModSolicitanteSubsanacion(ModSolicitanteApoderado solicitanteHidra, Solicitantes solicitanteSipi) throws Exception {

        String historial = "MOD: ";
        int sw = 0;

        if (solicitanteSipi.getNombreRazonSocial() != null) {
            if (solicitanteHidra.getNombre_razonsocial() != null) {
                if (!solicitanteHidra.getNombre_razonsocial().equals("")) {
                    if (!solicitanteSipi.getNombreRazonSocial().equals(solicitanteHidra.getNombre_razonsocial())) {
                        historial = historial + "Nombre=" + solicitanteHidra.getNombre_razonsocial() + "; ";
                        sw = 1;
                    }
                }
            }
        }

        if (solicitanteSipi.getPrimerApellido() != null) {
            if (solicitanteHidra.getPrimer_apellido() != null) {
                if (!solicitanteHidra.getPrimer_apellido().equals("")) {
                    if (!solicitanteSipi.getPrimerApellido().equals(solicitanteHidra.getPrimer_apellido())) {
                        historial = historial + "Primer Apellido=" + solicitanteHidra.getPrimer_apellido() + "; ";
                        sw = 1;
                    }
                }
            }
        }

        if (solicitanteSipi.getSegundoApellido() != null) {
            if (solicitanteHidra.getSegundo_apellido() != null) {
                if (!solicitanteHidra.getSegundo_apellido().equals("")) {
                    if (!solicitanteSipi.getSegundoApellido().equals(solicitanteHidra.getSegundo_apellido())) {
                        historial = historial + "Segundo Apellido=" + solicitanteHidra.getSegundo_apellido() + "; ";
                        sw = 1;
                    }
                }
            }
        }

        if (solicitanteSipi.getNumeroDocumento() != null) {
            if (solicitanteHidra.getNumero_documento() != null) {
                if (!solicitanteHidra.getNumero_documento().equals("")) {
                    if (!solicitanteSipi.getNumeroDocumento().equals(solicitanteHidra.getNumero_documento())) {
                        historial = historial + "N° Doc.=" + solicitanteHidra.getNumero_documento() + "; ";
                        sw = 1;
                    }
                }
            }
        }

        if (solicitanteSipi.getTipoDocumento() != null) {
            if (solicitanteHidra.getTipo_documento() != null) {
                if (!solicitanteHidra.getTipo_documento().equals("")) {
                    if (!solicitanteSipi.getTipoDocumento().equals(solicitanteHidra.getTipo_documento())) {
                        historial = historial + "Tipo Doc.=" + dominioService.obtenerNombreCodigoDominio("tipo_documento", solicitanteHidra.getTipo_documento()) + "; ";
                        sw = 1;
                    }
                }
            }
        }

        if (solicitanteSipi.getGenero() != null) {
            if (solicitanteHidra.getGenero() != null) {
                if (!solicitanteHidra.getGenero().equals("")) {
                    if (!solicitanteSipi.getGenero().equals(solicitanteHidra.getGenero())) {
                        historial = historial + "Genero=" + solicitanteHidra.getGenero() + "; ";
                        sw = 1;
                    }
                }
            }
        }

        if (solicitanteSipi.getLugarExpedicion() != null) {
            if (solicitanteHidra.getLugar_expedicion() != null) {
                if (!solicitanteHidra.getLugar_expedicion().equals("")) {
                    if (!solicitanteSipi.getLugarExpedicion().equals(solicitanteHidra.getLugar_expedicion())) {
                        historial = historial + "Lugar Exp.=" + dominioService.obtenerNombreCodigoDominio("lugar_expedicion", solicitanteHidra.getLugar_expedicion()) + "; ";
                        sw = 1;
                    }
                }
            }
        }

        if (solicitanteSipi.getPais() != null) {
            if (solicitanteHidra.getPais() != null) {
                if (!solicitanteHidra.getPais().equals("")) {
                    if (!solicitanteSipi.getPais().equals(solicitanteHidra.getPais())) {
                        historial = historial + "País=" + dominioService.obtenerNombreCodigoDominio("pais", solicitanteHidra.getPais()) + "; ";
                        sw = 1;
                    }
                }
            }
        }

        if (solicitanteSipi.getDepartamentoSolicitud() != null) {
            if (solicitanteHidra.getSolicitud_departamento() != null) {
                if (!solicitanteHidra.getSolicitud_departamento().equals("")) {
                    if (!solicitanteSipi.getDepartamentoSolicitud().equals(solicitanteHidra.getSolicitud_departamento())) {
                        historial = historial + "Departamento=" + dominioService.obtenerNombreCodigoDominio("departamento", solicitanteHidra.getSolicitud_departamento()) + "; ";
                        sw = 1;
                    }
                }
            }
        }

        if (solicitanteSipi.getTelefono() != null) {
            if (solicitanteHidra.getTelefono() != null) {
                if (!solicitanteHidra.getTelefono().equals("")) {
                    if (!solicitanteSipi.getTelefono().equals(solicitanteHidra.getTelefono())) {
                        historial = historial + "Teléfono=" + solicitanteHidra.getTelefono() + "; ";
                        sw = 1;
                    }
                }
            }
        }

        if (solicitanteSipi.getCelular() != null) {
            if (solicitanteHidra.getCelular() != null) {
                if (!solicitanteHidra.getCelular().equals("")) {
                    if (!solicitanteSipi.getCelular().equals(solicitanteHidra.getCelular())) {
                        historial = historial + "Celular=" + solicitanteHidra.getCelular() + "; ";
                        sw = 1;
                    }
                }
            }
        }

        if (solicitanteSipi.getDomicilio() != null) {
            if (solicitanteHidra.getDireccion() != null) {
                if (!solicitanteHidra.getDireccion().equals("")) {
                    if (!solicitanteSipi.getDomicilio().equals(solicitanteHidra.getDireccion())) {
                        historial = historial + "Dirección=" + solicitanteHidra.getDireccion() + "; ";
                        sw = 1;
                    }
                }
            }
        }

        if (solicitanteSipi.getCorreoElectronico() != null) {
            if (solicitanteHidra.getEmail() != null) {
                if (!solicitanteHidra.getEmail().equals("")) {
                    if (!solicitanteSipi.getCorreoElectronico().equals(solicitanteHidra.getEmail())) {
                        historial = historial + "E-mail=" + solicitanteHidra.getEmail() + "; ";
                        sw = 1;
                    }
                }
            }
        }
        if (sw == 1) {
            return historial;
        } else {
            return "";
        }
    }

    @Override
    public String eliminarModSolicitanteSubsanacion(ModSolicitanteApoderado solicitanteHidra) throws Exception {

        String historial = "DEL: ";
        int sw = 0;

        if (solicitanteHidra.getNombre_razonsocial() != null) {
            if (!solicitanteHidra.getNombre_razonsocial().equals("")) {
                historial = historial + "Nombre=" + solicitanteHidra.getNombre_razonsocial() + "; ";
                sw = 1;
            }
        }

        if (solicitanteHidra.getPrimer_apellido() != null) {
            if (!solicitanteHidra.getPrimer_apellido().equals("")) {
                historial = historial + "Primer Apellido=" + solicitanteHidra.getPrimer_apellido() + "; ";
                sw = 1;
            }
        }

        if (solicitanteHidra.getSegundo_apellido() != null) {
            if (!solicitanteHidra.getSegundo_apellido().equals("")) {
                historial = historial + "Segundo Apellido=" + solicitanteHidra.getSegundo_apellido() + "; ";
                sw = 1;
            }
        }

        if (solicitanteHidra.getNumero_documento() != null) {
            if (!solicitanteHidra.getNumero_documento().equals("")) {
                historial = historial + "N° Doc.=" + solicitanteHidra.getNumero_documento() + "; ";
                sw = 1;
            }
        }

        if (solicitanteHidra.getTipo_documento() != null) {
            if (!solicitanteHidra.getTipo_documento().equals("")) {
                historial = historial + "Tipo Doc.=" + solicitanteHidra.getTipo_documento() + "; ";
                sw = 1;
            }
        }

        if (solicitanteHidra.getGenero() != null) {
            if (!solicitanteHidra.getGenero().equals("")) {
                historial = historial + "Genero=" + solicitanteHidra.getGenero() + "; ";
                sw = 1;
            }
        }

        if (solicitanteHidra.getLugar_expedicion() != null) {
            if (!solicitanteHidra.getLugar_expedicion().equals("")) {
                historial = historial + "Lugar Exp.=" + dominioService.obtenerNombreCodigoDominio("lugar_expedicion", solicitanteHidra.getLugar_expedicion()) + "; ";
                sw = 1;
            }
        }

        if (solicitanteHidra.getPais() != null) {
            if (!solicitanteHidra.getPais().equals("")) {
                historial = historial + "País=" + dominioService.obtenerNombreCodigoDominio("pais", solicitanteHidra.getPais()) + "; ";
                sw = 1;
            }
        }

        if (solicitanteHidra.getSolicitud_departamento() != null) {
            if (!solicitanteHidra.getSolicitud_departamento().equals("")) {
                historial = historial + "Departamento=" + dominioService.obtenerNombreCodigoDominio("departamento", solicitanteHidra.getSolicitud_departamento()) + "; ";
                sw = 1;
            }
        }

        if (solicitanteHidra.getTelefono() != null) {
            if (!solicitanteHidra.getTelefono().equals("")) {
                historial = historial + "Teléfono=" + solicitanteHidra.getTelefono() + "; ";
                sw = 1;
            }
        }

        if (solicitanteHidra.getCelular() != null) {
            if (!solicitanteHidra.getCelular().equals("")) {
                historial = historial + "Celular=" + solicitanteHidra.getCelular() + "; ";
                sw = 1;
            }
        }

        if (solicitanteHidra.getFax() != null) {
            if (!solicitanteHidra.getFax().equals("")) {
                historial = historial + "Fax=" + solicitanteHidra.getFax() + "; ";
                sw = 1;
            }
        }

        if (solicitanteHidra.getDireccion() != null) {
            if (!solicitanteHidra.getDireccion().equals("")) {
                historial = historial + "Dirección=" + solicitanteHidra.getDireccion() + "; ";
                sw = 1;
            }
        }

        if (solicitanteHidra.getEmail() != null) {
            if (!solicitanteHidra.getEmail().equals("")) {
                historial = historial + "E-mail=" + solicitanteHidra.getEmail() + "; ";
                sw = 1;
            }
        }
        if (sw == 1) {
            return historial;
        } else {
            return "";
        }
    }

    @Override
    public String adicionarModSolicitanteSubsanacion(Solicitantes solicitanteSipi) throws Exception {

        String historial = "ADD: ";
        int sw = 0;

        if (solicitanteSipi.getNombreRazonSocial() != null) {
            if (!solicitanteSipi.getNombreRazonSocial().equals("")) {
                historial = historial + "Nombre=" + solicitanteSipi.getNombreRazonSocial() + "; ";
                sw = 1;
            }
        }

        if (solicitanteSipi.getPrimerApellido() != null) {
            if (!solicitanteSipi.getPrimerApellido().equals("")) {
                historial = historial + "Primer Apellido=" + solicitanteSipi.getPrimerApellido() + "; ";
                sw = 1;
            }
        }

        if (solicitanteSipi.getSegundoApellido() != null) {
            if (!solicitanteSipi.getSegundoApellido().equals("")) {
                historial = historial + "Segundo Apellido=" + solicitanteSipi.getSegundoApellido() + "; ";
                sw = 1;
            }
        }

        if (solicitanteSipi.getNumeroDocumento() != null) {
            if (!solicitanteSipi.getNumeroDocumento().equals("")) {
                historial = historial + "N° Doc.=" + solicitanteSipi.getNumeroDocumento() + "; ";
                sw = 1;
            }
        }

        if (solicitanteSipi.getTipoDocumento() != null) {
            if (!solicitanteSipi.getTipoDocumento().equals("")) {
                historial = historial + "Tipo Doc.=" + solicitanteSipi.getTipoDocumento() + "; ";
                sw = 1;
            }
        }

        if (solicitanteSipi.getGenero() != null) {
            if (!solicitanteSipi.getGenero().equals("")) {
                historial = historial + "Genero=" + solicitanteSipi.getGenero() + "; ";
                sw = 1;
            }
        }

        if (solicitanteSipi.getLugarExpedicion() != null) {
            if (!solicitanteSipi.getLugarExpedicion().equals("")) {
                historial = historial + "Lugar Exp.=" + dominioService.obtenerNombreCodigoDominio("lugar_expedicion", solicitanteSipi.getLugarExpedicion()) + "; ";
                sw = 1;
            }
        }

        if (solicitanteSipi.getPais() != null) {
            if (!solicitanteSipi.getPais().equals("")) {
                historial = historial + "País=" + dominioService.obtenerNombreCodigoDominio("pais", solicitanteSipi.getPais()) + "; ";
                sw = 1;
            }
        }

        if (solicitanteSipi.getDepartamentoSolicitud() != null) {
            if (!solicitanteSipi.getDepartamentoSolicitud().equals("")) {
                historial = historial + "Departamento=" + dominioService.obtenerNombreCodigoDominio("departamento", solicitanteSipi.getDepartamentoSolicitud()) + "; ";
                sw = 1;
            }
        }

        if (solicitanteSipi.getTelefono() != null) {
            if (!solicitanteSipi.getTelefono().equals("")) {
                historial = historial + "Teléfono=" + solicitanteSipi.getTelefono() + "; ";
                sw = 1;
            }
        }

        if (solicitanteSipi.getCelular() != null) {
            if (!solicitanteSipi.getCelular().equals("")) {
                historial = historial + "Celular=" + solicitanteSipi.getCelular() + "; ";
                sw = 1;
            }
        }

        if (solicitanteSipi.getDomicilio() != null) {
            if (!solicitanteSipi.getDomicilio().equals("")) {
                historial = historial + "Dirección=" + solicitanteSipi.getDomicilio() + "; ";
                sw = 1;
            }
        }

        if (solicitanteSipi.getCorreoElectronico() != null) {
            if (!solicitanteSipi.getCorreoElectronico().equals("")) {
                historial = historial + "E-mail=" + solicitanteSipi.getCorreoElectronico() + "; ";
                sw = 1;
            }
        }
        if (sw == 1) {
            return historial;
        } else {
            return "";
        }
    }

    @Override
    public void generarHistorialModApoderadoSubsanacion(Long idModificacion, List<Apoderados> listaApoderadosSipi, String tipoPersona, String estado_modificacion,
            String ubicacion_modificacion, Date fechaSistema, Long idLogTrans, Long idUsuario) throws Exception {
        try {
            String historial = "";
            int swExiste = 0;

            // Recuperamos al Apoderado desde la base de datos
            List<ModSolicitanteApoderado> listaApoderadosHidra = modSolicitanteApoderadoService.listadoApoderadoXidmodificacion(idModificacion);

            if (!listaApoderadosHidra.isEmpty()) {
                if (!listaApoderadosSipi.isEmpty()) {

                    for (ModSolicitanteApoderado apoderadoHidra : listaApoderadosHidra) {
                        swExiste = 0;
                        for (Apoderados apoderadoSipi : listaApoderadosSipi) {
                            if (apoderadoHidra.getIdSipi().equals(apoderadoSipi.getIdPadre())) {
                                // modificar
                                historial = historial + modificarModApoderadoSubsanacion(apoderadoHidra, apoderadoSipi);
                                swExiste = 1;
                                break;
                            }
                        }
                        if (swExiste == 0) {
                            // eliminar
                            historial = historial + eliminarModSolicitanteSubsanacion(apoderadoHidra);
                        }
                    }

                    for (Apoderados apoderadoSipi : listaApoderadosSipi) {
                        swExiste = 0;
                        for (ModSolicitanteApoderado apoderadoHidra : listaApoderadosHidra) {
                            if (apoderadoHidra.getIdSipi().equals(apoderadoSipi.getIdPadre())) {
                                // solo virificar que exista
                                swExiste = 1;
                                break;
                            }
                        }
                        if (swExiste == 0) {
                            // adicionar
                            historial = historial + adicionarModApoderadoSubsanacion(apoderadoSipi);
                        }
                    }
                } else {
                    // eliminar
                    for (ModSolicitanteApoderado apoderadoHidra : listaApoderadosHidra) {
                        historial = historial + eliminarModSolicitanteSubsanacion(apoderadoHidra);
                    }
                }

            } else {
                // adicionar
                for (Apoderados apoderadoSipi : listaApoderadosSipi) {
                    historial = historial + adicionarModApoderadoSubsanacion(apoderadoSipi);
                }
            }

            historial = historial.trim();

            //si historial es vacío, entonces hay al menos una modificacion
            if (!historial.equals("")) {
                historial = "[" + historial + "]";

                Historial historialTramite = new Historial();
                historialTramite.setId(idModificacion);
                historialTramite.setIdUsuario(idUsuario);
                historialTramite.setIdLogTrans(idLogTrans);
                historialTramite.setOperacion(EnumOperacion.MODIFICAR.getCodigo());
                historialTramite.setEstadoMarcaDescripcion(estado_modificacion);
                historialTramite.setUbicacionDescripcion(ubicacion_modificacion);
                historialTramite.setSeccion(EnumSeccion.APODERADO.getCodigo());
                historialTramite.setDescripcion(historial);
                historialTramite.setFechaOperacion(fechaSistema);
                historialTramite.setEstado(EnumEstado.ACTIVO.getCodigo());

                historialService.crudHistorial(historialTramite, "MOD", 1);
            }

        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public String modificarModApoderadoSubsanacion(ModSolicitanteApoderado apoderadoHidra, Apoderados apoderadoSipi) throws Exception {

        String historial = "MOD: ";
        int sw = 0;

        if (apoderadoSipi.getNombres() != null) {
            if (apoderadoHidra.getNombre_razonsocial() != null) {
                if (!apoderadoHidra.getNombre_razonsocial().equals("")) {
                    if (!apoderadoSipi.getNombres().equals(apoderadoHidra.getNombre_razonsocial())) {
                        historial = historial + "Nombre=" + apoderadoHidra.getNombre_razonsocial() + "; ";
                        sw = 1;
                    }
                }
            }
        }

        if (apoderadoSipi.getPrimerApellido() != null) {
            if (apoderadoHidra.getPrimer_apellido() != null) {
                if (!apoderadoHidra.getPrimer_apellido().equals("")) {
                    if (!apoderadoSipi.getPrimerApellido().equals(apoderadoHidra.getPrimer_apellido())) {
                        historial = historial + "Primer Apellido=" + apoderadoHidra.getPrimer_apellido() + "; ";
                        sw = 1;
                    }
                }
            }
        }

        if (apoderadoSipi.getSegundoApellido() != null) {
            if (apoderadoHidra.getSegundo_apellido() != null) {
                if (!apoderadoHidra.getSegundo_apellido().equals("")) {
                    if (!apoderadoSipi.getSegundoApellido().equals(apoderadoHidra.getSegundo_apellido())) {
                        historial = historial + "Segundo Apellido=" + apoderadoHidra.getSegundo_apellido() + "; ";
                        sw = 1;
                    }
                }
            }
        }

        if (apoderadoSipi.getNumeroDocumento() != null) {
            if (apoderadoHidra.getNumero_documento() != null) {
                if (!apoderadoHidra.getNumero_documento().equals("")) {
                    if (!apoderadoSipi.getNumeroDocumento().equals(apoderadoHidra.getNumero_documento())) {
                        historial = historial + "N° Doc.=" + apoderadoHidra.getNumero_documento() + "; ";
                        sw = 1;
                    }
                }
            }
        }

        if (apoderadoSipi.getTipoDocumento() != null) {
            if (apoderadoHidra.getTipo_documento() != null) {
                if (!apoderadoHidra.getTipo_documento().equals("")) {
                    if (!apoderadoSipi.getTipoDocumento().equals(apoderadoHidra.getTipo_documento())) {
                        historial = historial + "Tipo Doc.=" + dominioService.obtenerNombreCodigoDominio("tipo_documento", apoderadoHidra.getTipo_documento()) + "; ";
                        sw = 1;
                    }
                }
            }
        }

        if (apoderadoSipi.getGenero() != null) {
            if (apoderadoHidra.getGenero() != null) {
                if (!apoderadoHidra.getGenero().equals("")) {
                    if (!apoderadoSipi.getGenero().equals(apoderadoHidra.getGenero())) {
                        historial = historial + "Genero=" + apoderadoHidra.getGenero() + "; ";
                        sw = 1;
                    }
                }
            }
        }

        if (apoderadoSipi.getLugarExpedicion() != null) {
            if (apoderadoHidra.getLugar_expedicion() != null) {
                if (!apoderadoHidra.getLugar_expedicion().equals("")) {
                    if (!apoderadoSipi.getLugarExpedicion().equals(apoderadoHidra.getLugar_expedicion())) {
                        historial = historial + "Lugar Exp.=" + dominioService.obtenerNombreCodigoDominio("lugar_expedicion", apoderadoHidra.getLugar_expedicion()) + "; ";
                        sw = 1;
                    }
                }
            }
        }

        if (apoderadoSipi.getTelefono() != null) {
            if (apoderadoHidra.getTelefono() != null) {
                if (!apoderadoHidra.getTelefono().equals("")) {
                    if (!apoderadoSipi.getTelefono().equals(apoderadoHidra.getTelefono())) {
                        historial = historial + "Teléfono=" + apoderadoHidra.getTelefono() + "; ";
                        sw = 1;
                    }
                }
            }
        }

        if (apoderadoSipi.getCelular() != null) {
            if (apoderadoHidra.getCelular() != null) {
                if (!apoderadoHidra.getCelular().equals("")) {
                    if (!apoderadoSipi.getCelular().equals(apoderadoHidra.getCelular())) {
                        historial = historial + "Celular=" + apoderadoHidra.getCelular() + "; ";
                        sw = 1;
                    }
                }
            }
        }

        if (apoderadoSipi.getNumeroPoder() != null) {
            if (apoderadoHidra.getPoder() != null) {
                if (!apoderadoHidra.getPoder().equals("")) {
                    if (!apoderadoSipi.getNumeroPoder().equals(apoderadoHidra.getPoder())) {
                        historial = historial + "Poder=" + apoderadoHidra.getPoder() + "; ";
                        sw = 1;
                    }
                }
            }
        }

        if (apoderadoSipi.getDomicilio() != null) {
            if (apoderadoHidra.getDireccion() != null) {
                if (!apoderadoHidra.getDireccion().equals("")) {
                    if (!apoderadoSipi.getDomicilio().equals(apoderadoHidra.getDireccion())) {
                        historial = historial + "Dirección=" + apoderadoHidra.getDireccion() + "; ";
                        sw = 1;
                    }
                }
            }
        }

        if (apoderadoSipi.getCorreoElectronico() != null) {
            if (apoderadoHidra.getEmail() != null) {
                if (!apoderadoHidra.getEmail().equals("")) {
                    if (!apoderadoSipi.getCorreoElectronico().equals(apoderadoHidra.getEmail())) {
                        historial = historial + "E-mail=" + apoderadoHidra.getEmail() + "; ";
                        sw = 1;
                    }
                }
            }
        }
        if (sw == 1) {
            return historial;
        } else {
            return "";
        }
    }

    @Override
    public String eliminarModApoderadoSubsanacion(ModSolicitanteApoderado apoderadoHidra) throws Exception {

        String historial = "DEL: ";
        int sw = 0;

        if (apoderadoHidra.getNombre_razonsocial() != null) {
            if (!apoderadoHidra.getNombre_razonsocial().equals("")) {
                historial = historial + "Nombre=" + apoderadoHidra.getNombre_razonsocial() + "; ";
                sw = 1;
            }
        }

        if (apoderadoHidra.getPrimer_apellido() != null) {
            if (!apoderadoHidra.getPrimer_apellido().equals("")) {
                historial = historial + "Primer Apellido=" + apoderadoHidra.getPrimer_apellido() + "; ";
                sw = 1;
            }
        }

        if (apoderadoHidra.getSegundo_apellido() != null) {
            if (!apoderadoHidra.getSegundo_apellido().equals("")) {
                historial = historial + "Segundo Apellido=" + apoderadoHidra.getSegundo_apellido() + "; ";
                sw = 1;
            }
        }

        if (apoderadoHidra.getNumero_documento() != null) {
            if (!apoderadoHidra.getNumero_documento().equals("")) {
                historial = historial + "N° Doc.=" + apoderadoHidra.getNumero_documento() + "; ";
                sw = 1;
            }
        }

        if (apoderadoHidra.getTipo_documento() != null) {
            if (!apoderadoHidra.getTipo_documento().equals("")) {
                historial = historial + "Tipo Doc.=" + apoderadoHidra.getTipo_documento() + "; ";
                sw = 1;
            }
        }

        if (apoderadoHidra.getGenero() != null) {
            if (!apoderadoHidra.getGenero().equals("")) {
                historial = historial + "Genero=" + apoderadoHidra.getGenero() + "; ";
                sw = 1;
            }
        }

        if (apoderadoHidra.getLugar_expedicion() != null) {
            if (!apoderadoHidra.getLugar_expedicion().equals("")) {
                historial = historial + "Lugar Exp.=" + dominioService.obtenerNombreCodigoDominio("lugar_expedicion", apoderadoHidra.getLugar_expedicion()) + "; ";
                sw = 1;
            }
        }

        if (apoderadoHidra.getPais() != null) {
            if (!apoderadoHidra.getPais().equals("")) {
                historial = historial + "País=" + dominioService.obtenerNombreCodigoDominio("pais", apoderadoHidra.getPais()) + "; ";
                sw = 1;
            }
        }

        if (apoderadoHidra.getSolicitud_departamento() != null) {
            if (!apoderadoHidra.getSolicitud_departamento().equals("")) {
                historial = historial + "Departamento=" + dominioService.obtenerNombreCodigoDominio("departamento", apoderadoHidra.getSolicitud_departamento()) + "; ";
                sw = 1;
            }
        }

        if (apoderadoHidra.getTelefono() != null) {
            if (!apoderadoHidra.getTelefono().equals("")) {
                historial = historial + "Teléfono=" + apoderadoHidra.getTelefono() + "; ";
                sw = 1;
            }
        }

        if (apoderadoHidra.getCelular() != null) {
            if (!apoderadoHidra.getCelular().equals("")) {
                historial = historial + "Celular=" + apoderadoHidra.getCelular() + "; ";
                sw = 1;
            }
        }

        if (apoderadoHidra.getPoder() != null) {
            if (!apoderadoHidra.getPoder().equals("")) {
                historial = historial + "Poder=" + apoderadoHidra.getPoder() + "; ";
                sw = 1;
            }
        }

        if (apoderadoHidra.getDireccion() != null) {
            if (!apoderadoHidra.getDireccion().equals("")) {
                historial = historial + "Dirección=" + apoderadoHidra.getDireccion() + "; ";
                sw = 1;
            }
        }

        if (apoderadoHidra.getEmail() != null) {
            if (!apoderadoHidra.getEmail().equals("")) {
                historial = historial + "E-mail=" + apoderadoHidra.getEmail() + "; ";
                sw = 1;
            }
        }
        if (sw == 1) {
            return historial;
        } else {
            return "";
        }
    }

    @Override
    public String adicionarModApoderadoSubsanacion(Apoderados apoderadoSipi) throws Exception {

        String historial = "ADD: ";
        int sw = 0;

        if (apoderadoSipi.getNombres() != null) {
            if (!apoderadoSipi.getNombres().equals("")) {
                historial = historial + "Nombre=" + apoderadoSipi.getNombres() + "; ";
                sw = 1;
            }
        }

        if (apoderadoSipi.getPrimerApellido() != null) {
            if (!apoderadoSipi.getPrimerApellido().equals("")) {
                historial = historial + "Primer Apellido=" + apoderadoSipi.getPrimerApellido() + "; ";
                sw = 1;
            }
        }

        if (apoderadoSipi.getSegundoApellido() != null) {
            if (!apoderadoSipi.getSegundoApellido().equals("")) {
                historial = historial + "Segundo Apellido=" + apoderadoSipi.getSegundoApellido() + "; ";
                sw = 1;
            }
        }

        if (apoderadoSipi.getNumeroDocumento() != null) {
            if (!apoderadoSipi.getNumeroDocumento().equals("")) {
                historial = historial + "N° Doc.=" + apoderadoSipi.getNumeroDocumento() + "; ";
                sw = 1;
            }
        }

        if (apoderadoSipi.getTipoDocumento() != null) {
            if (!apoderadoSipi.getTipoDocumento().equals("")) {
                historial = historial + "Tipo Doc.=" + apoderadoSipi.getTipoDocumento() + "; ";
                sw = 1;
            }
        }

        if (apoderadoSipi.getGenero() != null) {
            if (!apoderadoSipi.getGenero().equals("")) {
                historial = historial + "Genero=" + apoderadoSipi.getGenero() + "; ";
                sw = 1;
            }
        }

        if (apoderadoSipi.getLugarExpedicion() != null) {
            if (!apoderadoSipi.getLugarExpedicion().equals("")) {
                historial = historial + "Lugar Exp.=" + dominioService.obtenerNombreCodigoDominio("lugar_expedicion", apoderadoSipi.getLugarExpedicion()) + "; ";
                sw = 1;
            }
        }

        if (apoderadoSipi.getTelefono() != null) {
            if (!apoderadoSipi.getTelefono().equals("")) {
                historial = historial + "Teléfono=" + apoderadoSipi.getTelefono() + "; ";
                sw = 1;
            }
        }

        if (apoderadoSipi.getCelular() != null) {
            if (!apoderadoSipi.getCelular().equals("")) {
                historial = historial + "Celular=" + apoderadoSipi.getCelular() + "; ";
                sw = 1;
            }
        }

        if (apoderadoSipi.getNumeroPoder() != null) {
            if (!apoderadoSipi.getNumeroPoder().equals("")) {
                historial = historial + "Poder=" + apoderadoSipi.getNumeroPoder() + "; ";
                sw = 1;
            }
        }

        if (apoderadoSipi.getDomicilio() != null) {
            if (!apoderadoSipi.getDomicilio().equals("")) {
                historial = historial + "Dirección=" + apoderadoSipi.getDomicilio() + "; ";
                sw = 1;
            }
        }

        if (apoderadoSipi.getCorreoElectronico() != null) {
            if (!apoderadoSipi.getCorreoElectronico().equals("")) {
                historial = historial + "E-mail=" + apoderadoSipi.getCorreoElectronico() + "; ";
                sw = 1;
            }
        }
        if (sw == 1) {
            return historial;
        } else {
            return "";
        }
    }

    @Override
    @Transactional
    public void generarHistorialModDireccionDeNotificacionSubsanacion(Long idModificacion, DireccionNotificaciones direccionNotificacionSipi,
            String estado_modificacion, String ubicacion_modificacion, Date fechaSistema, Long idLogTrans, Long idUsuario) throws Exception {
        try {

            // obtener el historial para la direccion de notificacion
            String historial = "";
            int sw = 0;

            // Obtener la direccion de notificacion de la Base
            ModDireccionDeNotificacion direccionNotificacionHidra = modDireccionDeNotificacionService.buscarModDireccionDeNotificacionXidmodificacion(idModificacion);

            // Preguntar si la direccion obtenida de Base es distinta de null
            if (direccionNotificacionHidra.getIddirecciondenotificacion() != null) {
                // Preguntar si la direccion obtenida de Externo es distinta de null
                if (direccionNotificacionSipi.getId() != null) {

                    //comenzar a preguntar por todos sus campos o atributos de direccion de notificacion
                    if (direccionNotificacionSipi.getCiudadNotificacion() != null) {
                        if (direccionNotificacionHidra.getCiudad_notificacion() != null) {
                            if (!direccionNotificacionHidra.getCiudad_notificacion().equals("")) {
                                if (!direccionNotificacionSipi.getCiudadNotificacion().equals(direccionNotificacionHidra.getCiudad_notificacion())) {
                                    historial = historial + "Ciudad=" + dominioService.obtenerNombreCodigoDominio("oficina", direccionNotificacionHidra.getCiudad_notificacion()) + "; ";
                                    sw = 1;
                                }
                            }
                        }
                    }

                    if (direccionNotificacionSipi.getZonaBarrio() != null) {
                        if (direccionNotificacionHidra.getZona_barrio() != null) {
                            if (!direccionNotificacionHidra.getZona_barrio().equals("")) {
                                if (!direccionNotificacionSipi.getZonaBarrio().equals(direccionNotificacionHidra.getZona_barrio())) {
                                    historial = historial + "Barrio/Zona=" + direccionNotificacionHidra.getZona_barrio() + "; ";
                                    sw = 1;
                                }
                            }
                        }
                    }

                    if (direccionNotificacionSipi.getAvenidaCalle() != null) {
                        if (direccionNotificacionHidra.getAvenida_calle() != null) {
                            if (!direccionNotificacionHidra.getAvenida_calle().equals("")) {
                                if (!direccionNotificacionSipi.getAvenidaCalle().equals(direccionNotificacionHidra.getAvenida_calle())) {
                                    historial = historial + "Av/Calle=" + direccionNotificacionHidra.getAvenida_calle() + "; ";
                                    sw = 1;
                                }
                            }
                        }
                    }

                    if (direccionNotificacionSipi.getNumeroDomicilio() != null) {
                        if (direccionNotificacionHidra.getNumero() != null) {
                            if (!direccionNotificacionHidra.getNumero().equals("")) {
                                if (!direccionNotificacionSipi.getNumeroDomicilio().equals(direccionNotificacionHidra.getNumero())) {
                                    historial = historial + "N°=" + direccionNotificacionHidra.getNumero() + "; ";
                                    sw = 1;
                                }
                            }
                        }
                    }

                    if (direccionNotificacionSipi.getNombreEdificio() != null) {
                        if (direccionNotificacionHidra.getEdificio() != null) {
                            if (!direccionNotificacionHidra.getEdificio().equals("")) {
                                if (!direccionNotificacionSipi.getNombreEdificio().equals(direccionNotificacionHidra.getEdificio())) {
                                    historial = historial + "Edificio=" + direccionNotificacionHidra.getEdificio() + "; ";
                                    sw = 1;
                                }
                            }
                        }
                    }

                    if (direccionNotificacionSipi.getPiso() != null) {
                        if (direccionNotificacionHidra.getPiso() != null) {
                            if (!direccionNotificacionHidra.getPiso().equals("")) {
                                if (!direccionNotificacionSipi.getPiso().equals(direccionNotificacionHidra.getPiso())) {
                                    historial = historial + "Piso=" + direccionNotificacionHidra.getPiso() + "; ";
                                    sw = 1;
                                }
                            }
                        }
                    }

                    if (direccionNotificacionSipi.getDepartamento() != null) {
                        if (direccionNotificacionHidra.getDepartamento() != null) {
                            if (!direccionNotificacionHidra.getDepartamento().equals("")) {
                                if (!direccionNotificacionSipi.getDepartamento().equals(direccionNotificacionHidra.getDepartamento())) {
                                    historial = historial + "Dpto=" + direccionNotificacionHidra.getDepartamento() + "; ";
                                    sw = 1;
                                }
                            }
                        }
                    }

                    if (direccionNotificacionSipi.getCelular() != null) {
                        if (direccionNotificacionHidra.getCelular() != null) {
                            if (!direccionNotificacionHidra.getCelular().equals("")) {
                                if (!direccionNotificacionSipi.getCelular().equals(direccionNotificacionHidra.getCelular())) {
                                    historial = historial + "Celular=" + direccionNotificacionHidra.getCelular() + "; ";
                                    sw = 1;
                                }
                            }
                        }
                    }

                    if (direccionNotificacionSipi.getTelefono() != null) {
                        if (direccionNotificacionHidra.getTelefono() != null) {
                            if (!direccionNotificacionHidra.getTelefono().equals("")) {
                                if (!direccionNotificacionSipi.getTelefono().equals(direccionNotificacionHidra.getTelefono())) {
                                    historial = historial + "Teléfono=" + direccionNotificacionHidra.getTelefono() + "; ";
                                    sw = 1;
                                }
                            }
                        }
                    }

                    if (direccionNotificacionSipi.getCorreoelectronico() != null) {
                        if (direccionNotificacionHidra.getCorreo_electronico() != null) {
                            if (!direccionNotificacionHidra.getCorreo_electronico().equals("")) {
                                if (!direccionNotificacionSipi.getCorreoelectronico().equals(direccionNotificacionHidra.getCorreo_electronico())) {
                                    historial = historial + "E-mail=" + direccionNotificacionHidra.getCorreo_electronico() + "; ";
                                    sw = 1;
                                }
                            }
                        }
                    }

                    if (direccionNotificacionSipi.getReferencia() != null) {
                        if (direccionNotificacionHidra.getReferencia_direccion() != null) {
                            if (!direccionNotificacionHidra.getReferencia_direccion().equals("")) {
                                if (!direccionNotificacionSipi.getReferencia().equals(direccionNotificacionHidra.getReferencia_direccion())) {
                                    historial = historial + "Referencia=" + direccionNotificacionHidra.getReferencia_direccion() + "; ";
                                    sw = 1;
                                }
                            }
                        }
                    }

                    //si sw=1, entonces hay al menos una modificacion
                    if (sw == 1) {
                        historial = historial.trim();
                        historial = historial.substring(0, historial.length() - 1);
                        historial = "[MOD: " + historial + "]";
                    }
                }

            } else {
                //Agregar un nuevo elemento de sigdirecciondenotificacion

                if (direccionNotificacionSipi.getCiudadNotificacion() != null) {
                    if (!direccionNotificacionSipi.getCiudadNotificacion().equals("")) {
                        historial = historial + "Ciudad=" + direccionNotificacionSipi.getCiudadNotificacion();
                    }
                }

                if (direccionNotificacionSipi.getZonaBarrio() != null) {
                    if (!direccionNotificacionSipi.getZonaBarrio().equals("")) {
                        historial = historial + "Barrio/Zona=" + direccionNotificacionSipi.getZonaBarrio();
                    }
                }

                if (direccionNotificacionSipi.getAvenidaCalle() != null) {
                    if (!direccionNotificacionSipi.getAvenidaCalle().equals("")) {
                        historial = historial + "Av/Calle=" + direccionNotificacionSipi.getAvenidaCalle();
                    }
                }

                if (direccionNotificacionSipi.getNumeroDomicilio() != null) {
                    if (!direccionNotificacionSipi.getNumeroDomicilio().equals("")) {
                        historial = historial + "N°=" + direccionNotificacionSipi.getNumeroDomicilio();
                    }
                }

                if (direccionNotificacionSipi.getNombreEdificio() != null) {
                    if (!direccionNotificacionSipi.getNombreEdificio().equals("")) {
                        historial = historial + "Edificio=" + direccionNotificacionSipi.getNombreEdificio();
                    }
                }

                if (direccionNotificacionSipi.getPiso() != null) {
                    if (!direccionNotificacionSipi.getPiso().equals("")) {
                        historial = historial + "Piso=" + direccionNotificacionSipi.getPiso();
                    }
                }

                if (direccionNotificacionSipi.getDepartamento() != null) {
                    if (!direccionNotificacionSipi.getDepartamento().equals("")) {
                        historial = historial + "Dpto=" + direccionNotificacionSipi.getDepartamento();
                    }
                }

                if (direccionNotificacionSipi.getCelular() != null) {
                    if (!direccionNotificacionSipi.getCelular().equals("")) {
                        historial = historial + "Celular=" + direccionNotificacionSipi.getCelular();
                    }
                }

                if (direccionNotificacionSipi.getTelefono() != null) {
                    if (!direccionNotificacionSipi.getTelefono().equals("")) {
                        historial = historial + "Teléfono=" + direccionNotificacionSipi.getTelefono();
                    }
                }

                if (direccionNotificacionSipi.getCorreoelectronico() != null) {
                    if (!direccionNotificacionSipi.getCorreoelectronico().equals("")) {
                        historial = historial + "E-mail=" + direccionNotificacionSipi.getCorreoelectronico();
                    }
                }

                if (direccionNotificacionSipi.getReferencia() != null) {
                    if (!direccionNotificacionSipi.getReferencia().equals("")) {
                        historial = historial + "Referencia=" + direccionNotificacionSipi.getReferencia();
                    }
                }
                historial = historial.trim();
                historial = historial.substring(0, historial.length() - 1);
                historial = "[ADD: " + historial + "]";
            }

            //se debe proceder a guardar el historial siempre y cuando haya existido por lo menos una modificacion
            if (!historial.equals("")) {

                //si todo esta bien guardar la observacion
                Historial historialTramite = new Historial();
                historialTramite.setId(idModificacion);
                historialTramite.setIdUsuario(idUsuario);
                historialTramite.setIdLogTrans(idLogTrans);
                historialTramite.setOperacion(EnumOperacion.MODIFICAR.getCodigo());

                //obtener la descripcion de la marca
                historialTramite.setEstadoMarcaDescripcion(estado_modificacion);
                historialTramite.setUbicacionDescripcion(ubicacion_modificacion);
                historialTramite.setSeccion(EnumSeccion.DIRECCION_DE_NOTIFICACION.getCodigo());

                historialTramite.setDescripcion(historial);
                historialTramite.setFechaOperacion(fechaSistema);
                historialTramite.setEstado(EnumEstado.ACTIVO.getCodigo());

                historialService.crudHistorial(historialTramite, "MOD", 1);
            }

        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    @Transactional
    public void generarHistorialModDireccionDeNotificacion(Long idModificacion, ModDireccionDeNotificacion modDireccionDeNotificacion, String estado_modificacion, String ubicacion_modificacion, Date fechaSistema, Long idLogTrans, Long idUsuario) throws Exception {
        try {
            //variable descripcion
            String descripcion = "";
            String operacion = "";
            //obtener el historial para la direccion de notificacion
            String historial = "";
            String historialEliminacion = "";

            //preguntar si el objeto que viene su id es diferente de nulo
            if (modDireccionDeNotificacion.getIddirecciondenotificacion() != null) {

                //Obtener la direccion de notificacion de la Base
                ModDireccionDeNotificacion modDireccionDeNotificacionBase = modDireccionDeNotificacionService.buscarModDireccionDeNotificacionXidmodificacion(idModificacion);

                //Preguntar si la direccion obtenida es distinta de null, no es necesario preguntar por si es nulo, ese caso no deberia ocurrir
                if (modDireccionDeNotificacionBase.getIddirecciondenotificacion() != null) {
                    //obtener direccion desde la vista
                    String x[] = modDireccionDeNotificacion.toString().split("\\|");
                    //obtener desde la base de datos
                    String y[] = modDireccionDeNotificacionBase.toString().split("\\|");
                    //Armar la cadena de observaciones
                    int sw = 0;
                    historial = "[";
                    historial = historial + "id:" + modDireccionDeNotificacionBase.getIddirecciondenotificacion() + "; ";

                    //los valores que son diferentes se guardaran en el historial
                    for (int i = 0; i < x.length; i++) {
                        if (!x[i].equals(y[i])) {
                            //se debe guardar lo que esta en la base
                            //adecuar para null
                            historial = historial + y[i] + "; ";
                            sw = 1;
                        }
                    }
                    if (historial.length() > 2) {
                        historial = historial.substring(0, historial.length() - 2);
                    }
                    historial = historial + "]";

                    operacion = EnumOperacion.MODIFICAR.getCodigo();
                    //si no existe ninguna diferencia la cadena sera vacia.
                    if (sw == 0) {
                        historial = "";
                    }
                }

            } else {
                //Agregar un nuevo elemento de moddirecciondenotificacion
                String cadenaDireccion = "";

                if (modDireccionDeNotificacion.getCiudad_notificacion() != null) {
                    cadenaDireccion = modDireccionDeNotificacion.getCiudad_notificacion() + "; ";
                }

                if (modDireccionDeNotificacion.getZona_barrio() != null) {
                    cadenaDireccion = " " + cadenaDireccion + modDireccionDeNotificacion.getZona_barrio();
                }

                if (modDireccionDeNotificacion.getAvenida_calle() != null) {
                    cadenaDireccion = " " + cadenaDireccion + modDireccionDeNotificacion.getAvenida_calle();
                }

                if (modDireccionDeNotificacion.getNumero() != null) {
                    cadenaDireccion = " " + cadenaDireccion + modDireccionDeNotificacion.getNumero();
                }

                historial = "[" + cadenaDireccion
                        + "]";
                operacion = EnumOperacion.ADICIONAR.getCodigo();
            }

            //variable del campo descripcion
            descripcion = descripcion + historial;

            //se debe proceder a guardar el historial siempre y cuando haya existido por lo menos una modificacion
            if (!descripcion.equals("")) {

                //si todo esta bien guardar la observacion
                Historial historialTramite = new Historial();
                historialTramite.setId(idModificacion);
                historialTramite.setIdUsuario(idUsuario);
                historialTramite.setIdLogTrans(idLogTrans);
                historialTramite.setOperacion(operacion);

                //obtener la descripcion de la marca
                historialTramite.setEstadoMarcaDescripcion(estado_modificacion);
                historialTramite.setUbicacionDescripcion(ubicacion_modificacion);
                historialTramite.setSeccion(EnumSeccion.DIRECCION_DE_NOTIFICACION.getCodigo());

                historialTramite.setDescripcion(descripcion);
                historialTramite.setFechaOperacion(fechaSistema);
                historialTramite.setEstado(EnumEstado.ACTIVO.getCodigo());

                historialService.crudHistorial(historialTramite, "MOD", 1);
            }

        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void generarHistorialModSolicitanteApoderado(Long idModificacion, List<ModSolicitanteApoderado> lstSolicitantes, String tipoPersona, String estado_modificacion, String ubicacion_modificacion, Date fechaSistema, Long idLogTrans, Long idUsuario) throws Exception {
        try {
            //variable descripcion
            String descripcion = "";
            String operacion = EnumOperacion.MODIFICAR.getCodigo();
            //obtener el historial por cada solicitante encontrado
            String historial = "";
            String historialEliminacion = "";
            //recorrer la lista que viene desde la vista
            for (ModSolicitanteApoderado solicitante : lstSolicitantes) {
                historial = "";
                descripcion = "";
                if (solicitante.getIdsolicitanteapoderado() != null) {
                    //Obtener el solicitante
                    ModSolicitanteApoderado modSolicitanteApoderado
                            = modSolicitanteApoderadoService.listar_ModSolicitanteApoderado_id(solicitante.getIdsolicitanteapoderado(), tipoPersona);
                    if (modSolicitanteApoderado.getIdsolicitanteapoderado() != null) {
                        //obtener desde la vista
                        String x[] = solicitante.toString().split("\\|");
                        //obtener desde la base de datos
                        String y[] = modSolicitanteApoderado.toString().split("\\|");

                        //Armar la cadena de observaciones
                        //no tiene observaciones
                        int sw = 0;
                        historial = "[";
                        historial = historial + "id:" + modSolicitanteApoderado.getIdsolicitanteapoderado() + "; ";
                        historialEliminacion = historialEliminacion + modSolicitanteApoderado.getIdsolicitanteapoderado() + ",";

                        for (int i = 0; i < x.length; i++) {
                            if (!x[i].equals(y[i])) {
                                historial = historial + y[i] + "; ";
                                sw = 1;
                            }
                        }
                        if (historial.length() > 2) {
                            historial = historial.substring(0, historial.length() - 2);
                        }
                        historial = historial + "]";

                        operacion = EnumOperacion.MODIFICAR.getCodigo();
                        //si no existe ninguna diferencia la cadena sera vacia.
                        if (sw == 0) {
                            historial = "";
                        }

                    } else {
                        //no se hace nada en este caso " en la base se recupero nulo"
                    }

                } else {

                    //Agregar un nuevo elemento
                    String cadenaApellidos = "";
                    if (solicitante.getPrimer_apellido() != null) {
                        cadenaApellidos = cadenaApellidos + solicitante.getPrimer_apellido();
                    }
                    if (solicitante.getSegundo_apellido() != null) {
                        cadenaApellidos = " " + cadenaApellidos + " " + solicitante.getSegundo_apellido() + "";
                    }

                    historial = "[nombreCompleto = " + solicitante.getNombre_razonsocial()
                            + " " + cadenaApellidos
                            + "; numeroDocumento:  " + solicitante.getNumero_documento()
                            + "]";
                    operacion = EnumOperacion.ADICIONAR.getCodigo();
                }

                //variable del campo descripcion
                descripcion = descripcion + historial;

                ///////////////////////////////////////////////////////////////////////////////////////////
                //deberia mejorarse para arreglar cada uno uno de los solictiantes afectados no todo en una sola cadena es confuso            
                if (!descripcion.equals("")) {
                    //si todo esta bien guardar la observacion
                    Historial historialTramite = new Historial();
                    historialTramite.setId(idModificacion);
                    historialTramite.setIdUsuario(idUsuario);
                    if (operacion.equals(EnumOperacion.ADICIONAR.getCodigo())) {
                        historialTramite.setIdLogTrans(idLogTrans);
                    } else {
                        historialTramite.setIdLogTrans(solicitante.getIdLogTrans());
                    }
                    historialTramite.setOperacion(operacion);
                    //obtener la descripcion de la marca
                    historialTramite.setEstadoMarcaDescripcion(estado_modificacion);
                    historialTramite.setUbicacionDescripcion(ubicacion_modificacion);
                    if (tipoPersona.equals(EnumTipoPersona.SOLICITANTE.getCodigo())) {
                        historialTramite.setSeccion(EnumSeccion.SOLICITANTE.getCodigo());
                    } else {
                        historialTramite.setSeccion(EnumSeccion.APODERADO.getCodigo());
                    }
                    historialTramite.setDescripcion(descripcion);
                    historialTramite.setFechaOperacion(fechaSistema);
                    historialTramite.setEstado(EnumEstado.ACTIVO.getCodigo());
                    historialService.crudHistorial(historialTramite, "MOD", 1);
                }

            }

            //busca si no hubo eliminacion
            //en caso que no se encuentra nada en la base se debe preguntar eso
            descripcion = "";
            //obtener el historial por cada solicitante encontrado
            historial = "";
            List<ObjetoEliminadoGenerico> objetoEliminadoGenerico = new ArrayList<ObjetoEliminadoGenerico>();
            if (!historialEliminacion.equals("")) {
                objetoEliminadoGenerico
                        = modSolicitanteApoderadoService.obtenerRegistrosEliminadosModSolicitanteApoderado(idModificacion, historialEliminacion.substring(0, historialEliminacion.length() - 1), tipoPersona);
            }

            if (!objetoEliminadoGenerico.isEmpty()) {
                // se agrega mas texto a la descripcion si hay eliminacion
                //concatenar con la descripcion
                //descripcion = descripcion + "[DEL:" + objetoEliminadoGenerico.getObjetoEliminado() + "]";
                descripcion = descripcion + "[DEL: ";
                for (ObjetoEliminadoGenerico item : objetoEliminadoGenerico) {
                    descripcion = descripcion + item.getObjetoEliminado() + "; ";
                }
                if (descripcion.length() > 2) {
                    descripcion = descripcion.substring(0, descripcion.length() - 2);
                }
                descripcion = descripcion + "]";
                //deberia mejorarse para arreglar cada uno uno de los solictiantes afectados no todo en una sola cadena es confuso            
                if (!descripcion.equals("")) {
                    //si todo esta bien guardar la observacion
                    Historial historialTramite = new Historial();
                    historialTramite.setId(idModificacion);
                    historialTramite.setIdUsuario(idUsuario);
                    //   historialTramite.setIdLogTrans(idLogTrans); PREGUNTAR QUE HACER
                    historialTramite.setOperacion(EnumOperacion.ELIMINAR.getCodigo());
                    //obtener la descripcion de la marca
                    historialTramite.setEstadoMarcaDescripcion(estado_modificacion);
                    historialTramite.setUbicacionDescripcion(ubicacion_modificacion);
                    if (tipoPersona.equals(EnumTipoPersona.SOLICITANTE.getCodigo())) {
                        historialTramite.setSeccion(EnumSeccion.SOLICITANTE.getCodigo());
                    } else {
                        historialTramite.setSeccion(EnumSeccion.APODERADO.getCodigo());
                    }
                    historialTramite.setDescripcion(descripcion);
                    historialTramite.setFechaOperacion(fechaSistema);
                    historialTramite.setEstado(EnumEstado.ACTIVO.getCodigo());
                    historialService.crudHistorial(historialTramite, "MOD", 1);
                }
            }

        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void generarHistorialReferenciaModificacion(ModModificacion modificacion, String estado_modificacion, String ubicacion_modificacion,
            Date fechaSistema, Long idLogTrans, Long idUsuario) throws Exception {
        try {
            //variable descripcion
            String descripcion = "";
            String operacion = "";
            //obtener el historial para la direccion de notificacion
            String historial = "";
            String historialEliminacion = "";

            //preguntar si el objeto que viene su id es diferente de nulo
            if (modificacion.getIdmodificacion() != null) {

                ModModificacion modificacionBase = modModificacionService.guardar_modificar_listar_ModModificacion(modificacion, 4);

                //Preguntar si la direccion obtenida es distinta de null, no es necesario preguntar por si es nulo, ese caso no deberia ocurrir
                if (modificacionBase.getIdmodificacion() != null) {
                    String x[] = modificacion.toString().split("\\|");
                    //obtener desde la base de datos
                    String y[] = modificacionBase.toString().split("\\|");
                    int sw = 0;
                    historial = "[";
                    historial = historial + "id:" + modificacionBase.getIdmodificacion() + "; ";
                    for (int i = 0; i < 17; i++) {
                        if (!x[i].equals(y[i])) {
                            historial = historial + y[i] + "; ";
                            sw = 1;
                        }
                    }

                    if (historial.length() > 2) {
                        historial = historial.substring(0, historial.length() - 2);
                    }
                    historial = historial + "]";
                    operacion = EnumOperacion.MODIFICAR.getCodigo();
                    //si no existe ninguna diferencia la cadena sera vacia.
                    if (sw == 0) {
                        historial = "";
                    }
                }

            } else {
                //Agregar un nuevo elemento de moddirecciondenotificacion
                String cadena = "";

                if (modificacion.getSm() != null) {
                    cadena = "SM: " + cadena + modificacion.getSm() + " ";
                }

                if (modificacion.getNumero_registro() != null) {
                    cadena = "Registro: " + cadena + modificacion.getNumero_registro() + " ";
                }

                if (modificacion.getSerie_registro() != null) {
                    cadena = cadena + modificacion.getSerie_registro() + " ";
                }

                if (modificacion.getNumero_renovacion() != null) {
                    cadena = "Renov.: " + cadena + modificacion.getNumero_renovacion() + " ";
                }

                if (modificacion.getSerie_renovacion() != null) {
                    cadena = cadena + modificacion.getSerie_renovacion() + " ";
                }

                if (modificacion.getNumero_publicacion() != null) {
                    cadena = "Publ. " + cadena + modificacion.getNumero_publicacion() + " ";
                }

                if (modificacion.getSigla() != null) {
                    cadena = "N° Sol.: " + cadena + modificacion.getSigla() + " ";
                }

                if (modificacion.getNumero() != null) {
                    cadena = cadena + modificacion.getNumero() + " ";
                }

                if (modificacion.getGestion() != null) {
                    cadena = cadena + modificacion.getGestion() + " ";
                }

                if (modificacion.getNro_formulario() != null) {
                    cadena = "N° Form.: " + cadena + modificacion.getNro_formulario() + " ";
                }

                if (modificacion.getFecha_ingreso() != null) {
                    cadena = "Fecha Ingr: " + cadena + modificacion.getFecha_ingreso() + " ";
                }

                if (modificacion.getNro_recibo() != null) {
                    cadena = "Recibo: " + cadena + modificacion.getNro_recibo() + " ";
                }

                if (modificacion.getSerie_recibo() != null) {
                    cadena = cadena + modificacion.getSerie_recibo() + " ";
                }

                historial = "[" + cadena
                        + "]";
                operacion = EnumOperacion.ADICIONAR.getCodigo();
            }

            //variable del campo descripcion
            descripcion = descripcion + historial;

            //se debe proceder a guardar el historial siempre y cuando haya existido por lo menos una modificacion
            if (!descripcion.equals("")) {
                //si todo esta bien guardar la observacion
                Historial historialTramite = new Historial();
                historialTramite.setId(modificacion.getIdmodificacion());
                historialTramite.setIdUsuario(idUsuario);

                historialTramite.setOperacion(operacion);
                if (operacion.equals(EnumOperacion.ADICIONAR.getCodigo())) {
                    historialTramite.setIdLogTrans(idLogTrans);
                } else {
                    historialTramite.setIdLogTrans(modificacion.getIdlogtrans());
                }
                //obtener la descripcion de la marca
                historialTramite.setEstadoMarcaDescripcion(estado_modificacion);
                historialTramite.setUbicacionDescripcion(ubicacion_modificacion);
                historialTramite.setSeccion(EnumSeccion.REFERENCIA_SOLICITUD.getCodigo());
                historialTramite.setDescripcion(descripcion);
                historialTramite.setFechaOperacion(fechaSistema);
                historialTramite.setEstado(EnumEstado.ACTIVO.getCodigo());
                historialService.crudHistorial(historialTramite, "MOD", 1);
            }

        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void generarHistorialTitularSignoRegistrado(ModModificacion modificacion, List<ModTitularLicenciatarioRegistrado> listaTitularRegistrado,
            String estado_modificacion, String ubicacion_modificacion, Date fechaSistema, Long idLogTrans, String tipoPersonRegistrado, Long idUsuario) throws Exception {
        try {

            //variable descripcion
            String descripcion = "";
            String operacion = "";
            //obtener el historial por cada solicitante encontrado
            String historial = "";
            String historialEliminacion = "";
            String titulo = "";

            //recorrer la lista que viene desde la vista
            for (ModTitularLicenciatarioRegistrado titular : listaTitularRegistrado) {
                historial = "";
                descripcion = "";
                if (titular.getIdtitularlicenciatarioregistrado() != null) {
                    //Obtener el solicitante
                    ModTitularLicenciatarioRegistrado modTitularRegistradoBase = modTitularLicenciatarioRegistradoService.guardar_modificar_listar_ModTitularLicenciatarioRegistrado(titular, 4);
                    if (modTitularRegistradoBase.getIdtitularlicenciatarioregistrado() != null) {

                        //obtener desde la vista
                        String x[] = titular.toString().split("\\|");
                        //obtener desde la base de datos
                        String y[] = modTitularRegistradoBase.toString().split("\\|");

                        //Armar la cadena de observaciones
                        //no tiene observaciones
                        int sw = 0;
                        historial = "[";
                        historial = historial + "id:" + modTitularRegistradoBase.getIdtitularlicenciatarioregistrado() + "; ";
                        historialEliminacion = historialEliminacion + modTitularRegistradoBase.getIdtitularlicenciatarioregistrado() + ",";

                        for (int i = 0; i < x.length; i++) {
                            if (!x[i].equals(y[i])) {
                                historial = historial + y[i] + "; ";
                                sw = 1;
                            }
                        }
                        if (historial.length() > 2) {
                            historial = historial.substring(0, historial.length() - 2);
                        }
                        historial = historial + "]";
                        operacion = EnumOperacion.MODIFICAR.getCodigo();
                        //si no existe ninguna diferencia la cadena sera vacia.
                        if (sw == 0) {
                            historial = "";
                        }

                    } else {
                        //no se hace nada en este caso " en la base se recupero nulo"
                    }

                } else {
                    //Agregar un nuevo elemento

                    String cadenaApellidos = "";
                    if (titular.getPrimer_apellido() != null) {
                        cadenaApellidos = cadenaApellidos + titular.getPrimer_apellido();
                    }
                    if (titular.getSegundo_apellido() != null) {
                        cadenaApellidos = " " + cadenaApellidos + titular.getSegundo_apellido() + "";
                    }

                    if (tipoPersonRegistrado.equals("TREG")) {
                        titulo = "Titular Registrado: ";
                        historial = " [" + titulo + titular.getNombre_razonsocial()
                                + " " + cadenaApellidos
                                + ";  direccion:  " + titular.getDireccion()
                                + "]";
                    } else {
                        titulo = "Lista Fusion: ";
                        historial = " [" + titulo + titular.getNombre_razonsocial()
                                + " " + cadenaApellidos
                                // + ";  direccion:  " + titular.getDireccion()
                                + "]";
                    }

                    operacion = EnumOperacion.ADICIONAR.getCodigo();
                }

                //variable del campo descripcion
                descripcion = descripcion + historial;
                //se debe proceder a guardar el historial siempre y cuando haya existido por lo menos una modificacion
                if (!descripcion.equals("")) {
                    //si todo esta bien guardar la observacion
                    Historial historialTramite = new Historial();
                    historialTramite.setId(modificacion.getIdmodificacion());
                    historialTramite.setIdUsuario(idUsuario);
                    if (operacion.equals(EnumOperacion.ADICIONAR.getCodigo())) {
                        historialTramite.setIdLogTrans(idLogTrans);
                    } else {
                        historialTramite.setIdLogTrans(titular.getIdlogtrans());
                    }
                    historialTramite.setOperacion(operacion);
                    //obtener la descripcion de la marca
                    historialTramite.setEstadoMarcaDescripcion(estado_modificacion);
                    historialTramite.setUbicacionDescripcion(ubicacion_modificacion);
                    if (tipoPersonRegistrado.equals("TREG")) {
                        //PAR AMBOS FUSION Y LO DEMAS
                        historialTramite.setSeccion(EnumSeccion.DATOS_SIGNO_REGISTRADO.getCodigo());
                    } else {
                        historialTramite.setSeccion(EnumSeccion.DATOS_NUEVO_TITULAR.getCodigo());

                    }
                    historialTramite.setDescripcion(descripcion);
                    historialTramite.setFechaOperacion(fechaSistema);
                    historialTramite.setEstado(EnumEstado.ACTIVO.getCodigo());
                    historialService.crudHistorial(historialTramite, "MOD", 1);
                }
            }////FIN FOR
            descripcion = "";
            //obtener el historial por cada solicitante encontrado
            historial = "";
            //busca si no hubo eliminacion
            //en caso que no se encuentra nada en la base se debe preguntar eso
            List<ObjetoEliminadoGenerico> objetoEliminadoGenerico = new ArrayList<ObjetoEliminadoGenerico>();

            if (!historialEliminacion.equals("")) {
                objetoEliminadoGenerico
                        = modTitularLicenciatarioRegistradoService.obtenerRegistrosEliminadosModTitularRegistrado(modificacion.getIdmodificacion(), historialEliminacion.substring(0, historialEliminacion.length() - 1), tipoPersonRegistrado);
            }

            if (!objetoEliminadoGenerico.isEmpty()) {
                //concatenar con la descripcion
                //descripcion = descripcion + "[DEL:" + objetoEliminadoGenerico.getObjetoEliminado() + "]";
                descripcion = descripcion + "[DEL: ";
                for (ObjetoEliminadoGenerico item : objetoEliminadoGenerico) {
                    descripcion = descripcion + item.getObjetoEliminado() + "; ";
                }
                if (descripcion.length() > 2) {
                    descripcion = descripcion.substring(0, descripcion.length() - 2);
                }
                descripcion = descripcion + "]";
                ////////////////////////////////
                if (!descripcion.equals("")) {
                    //si todo esta bien guardar la observacion
                    Historial historialTramite = new Historial();
                    historialTramite.setId(modificacion.getIdmodificacion());
                    historialTramite.setIdUsuario(idUsuario);
                    historialTramite.setIdLogTrans(modificacion.getIdlogtrans());
                    historialTramite.setOperacion(EnumOperacion.ELIMINAR.getCodigo());
                    historialTramite.setEstadoMarcaDescripcion(estado_modificacion);
                    historialTramite.setUbicacionDescripcion(ubicacion_modificacion);
                    if (tipoPersonRegistrado.equals("TREG")) {
                        //PAR AMBOS FUSION Y LO DEMAS
                        historialTramite.setSeccion(EnumSeccion.DATOS_SIGNO_REGISTRADO.getCodigo());
                    } else {
                        historialTramite.setSeccion(EnumSeccion.DATOS_NUEVO_TITULAR.getCodigo());

                    }
                    historialTramite.setDescripcion(descripcion);
                    historialTramite.setFechaOperacion(fechaSistema);
                    historialTramite.setEstado(EnumEstado.ACTIVO.getCodigo());
                    historialService.crudHistorial(historialTramite, "MOD", 1);
                }
            }

        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void generarHistorialTitularLicenciatarioRegistradoSubsanacion(Long idModificacion, List<TitularLicenciatarios> listaTitularLicenciatariosSipi,
            String estado_modificacion, String ubicacion_modificacion, Date fechaSistema, Long idLogTrans, String tipoPersonRegistrado, Long idUsuario) throws Exception {
        try {
            String historial = "";
            int sw = 0;
            int swExiste = 0;

            // Recuperamos el TitularLicenciatarios desde la base de datos
            List<ModTitularLicenciatarioRegistrado> listaTitularLicenciatariosHidra = modTitularLicenciatarioRegistradoService.listaTitularRegistradoXidmodificacion(idModificacion);

            if (!listaTitularLicenciatariosHidra.isEmpty()) {
                if (!listaTitularLicenciatariosSipi.isEmpty()) {
                    // modificar
                    historial = "MOD: ";
                    for (ModTitularLicenciatarioRegistrado titularRegistradoHidra : listaTitularLicenciatariosHidra) {
                        swExiste = 0;
                        for (TitularLicenciatarios titularRegistradoSipi : listaTitularLicenciatariosSipi) {
                            if (titularRegistradoHidra.getIdSipi().equals(titularRegistradoSipi.getIdPadre())) {
                                if (!titularRegistradoSipi.getNombreRazonSocial().equals(titularRegistradoHidra.getNombre_razonsocial())) {
                                    historial = historial + "Titular=" + titularRegistradoHidra.getNombre_razonsocial() + "; ";
                                    sw = 1;
                                }

                                if (!titularRegistradoSipi.getDireccion().trim().equals(titularRegistradoHidra.getDireccion().trim())) {
                                    historial = historial + "Dirección=" + titularRegistradoHidra.getDireccion() + "; ";
                                    sw = 1;
                                }
                                swExiste = 1;
                                break;
                            }
                        }
                        if (swExiste == 0) {
                            // eliminar
                            historial = "DEL: ";
                            if (titularRegistradoHidra.getNombre_razonsocial() != null) {
                                historial = historial + "Titular=" + titularRegistradoHidra.getNombre_razonsocial() + "; ";
                                sw = 1;
                            }
                            if (!titularRegistradoHidra.getDireccion().trim().equals("")) {
                                historial = historial + "Dirección=" + titularRegistradoHidra.getDireccion() + "; ";
                                sw = 1;
                            }
                        }
                    }

                    for (TitularLicenciatarios titularRegistradoSipi : listaTitularLicenciatariosSipi) {
                        swExiste = 0;
                        for (ModTitularLicenciatarioRegistrado titularRegistradoHidra : listaTitularLicenciatariosHidra) {
                            if (titularRegistradoHidra.getIdSipi().equals(titularRegistradoSipi.getIdPadre())) {
                                // solo virificar que exista
                                swExiste = 1;
                                break;
                            }
                        }
                        if (swExiste == 0) {
                            // adicionar
                            historial = "ADD: ";
                            if (titularRegistradoSipi.getNombreRazonSocial() != null) {
                                historial = historial + "Titular=" + titularRegistradoSipi.getNombreRazonSocial() + "; ";
                                sw = 1;
                            }
                            if (!titularRegistradoSipi.getDireccion().trim().equals("")) {
                                historial = historial + "Dirección=" + titularRegistradoSipi.getDireccion().trim() + "; ";
                                sw = 1;
                            }
                        }
                    }

                } else {
                    // eliminar
                    historial = "DEL: ";
                    for (ModTitularLicenciatarioRegistrado titularRegistradoHidra : listaTitularLicenciatariosHidra) {
                        if (titularRegistradoHidra.getNombre_razonsocial() != null) {
                            historial = historial + "Titular=" + titularRegistradoHidra.getNombre_razonsocial() + "; ";
                            sw = 1;
                        }
                        if (!titularRegistradoHidra.getDireccion().trim().equals("")) {
                            historial = historial + "Dirección=" + titularRegistradoHidra.getDireccion() + "; ";
                            sw = 1;
                        }
                    }
                }
            } else {
                // adicionar
                historial = "ADD: ";
                for (TitularLicenciatarios titularRegistradoSipi : listaTitularLicenciatariosSipi) {
                    if (titularRegistradoSipi.getNombreRazonSocial() != null) {
                        historial = historial + "Titular=" + titularRegistradoSipi.getNombreRazonSocial() + "; ";
                        sw = 1;
                    }
                    if (!titularRegistradoSipi.getDireccion().trim().equals("")) {
                        historial = historial + "Dirección=" + titularRegistradoSipi.getDireccion().trim() + "; ";
                        sw = 1;
                    }
                }
            }

            //si sw=1, entonces hay al menos una modificacion
            if (sw == 1) {
                historial = historial.trim();
                historial = historial.substring(0, historial.length() - 1);
            } else {
                historial = "";
            }

            //se debe proceder a guardar el historial siempre y cuando haya existido por lo menos una modificacion
            if (!historial.equals("")) {
                historial = "[" + historial + "]";

                Historial historialTramite = new Historial();
                historialTramite.setId(idModificacion);
                historialTramite.setIdUsuario(idUsuario);
                historialTramite.setIdLogTrans(idLogTrans);
                historialTramite.setOperacion(EnumOperacion.MODIFICAR.getCodigo());
                historialTramite.setEstadoMarcaDescripcion(estado_modificacion);
                historialTramite.setUbicacionDescripcion(ubicacion_modificacion);
                if (tipoPersonRegistrado.equals("TREG")) {
                    historialTramite.setSeccion(EnumSeccion.DATOS_SIGNO_REGISTRADO.getCodigo());
                } else {
                    historialTramite.setSeccion(EnumSeccion.DATOS_NUEVO_TITULAR.getCodigo());
                }
                historialTramite.setDescripcion(historial);
                historialTramite.setFechaOperacion(fechaSistema);
                historialTramite.setEstado(EnumEstado.ACTIVO.getCodigo());

                historialService.crudHistorial(historialTramite, "MOD", 1);
            }

        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void generarHistorialDatosSignoRegistrado(ModModificacion modificacion, String estado_modificacion, String ubicacion_modificacion,
            Date fechaSistema, Long idLogTrans, Long idUsuario) throws Exception {
        try {
            //variable descripcion
            String descripcion = "";
            String operacion = "";
            //obtener el historial para la direccion de notificacion
            String historial = "";
            String historialEliminacion = "";

            //preguntar si el objeto que viene su id es diferente de nulo
            if (modificacion.getIdmodificacion() != null) {

                ModModificacion modificacionBase = modModificacionService.guardar_modificar_listar_ModModificacion(modificacion, 4);

                //Preguntar si la direccion obtenida es distinta de null, no es necesario preguntar por si es nulo, ese caso no deberia ocurrir
                if (modificacionBase.getIdmodificacion() != null) {
                    String x[] = modificacion.toString().split("\\|");
                    //obtener desde la base de datos
                    String y[] = modificacionBase.toString().split("\\|");
                    int sw = 0;
                    historial = "[";
                    historial = historial + "id:" + modificacionBase.getIdmodificacion() + "; ";
                    for (int i = 17; i < 23; i++) {
                        if (!x[i].equals(y[i])) {
                            if (i == 18) {
                                ClaseNiza nizaClase = claseNizaService.listarClaseNiza_id(modificacionBase.getIdclase_registrado());
                                y[i] = "clase_registrado=" + nizaClase.getNumeroClaseNiza().toString();
                            }
                            historial = historial + y[i] + "; ";
                            sw = 1;
                        }
                    }
                    if (historial.length() > 2) {
                        historial = historial.substring(0, historial.length() - 2);
                    }
                    historial = historial + "]";
                    operacion = EnumOperacion.MODIFICAR.getCodigo();
                    //si no existe ninguna diferencia la cadena sera vacia.
                    if (sw == 0) {
                        historial = "";
                    }
                }

            } else {
                //Agregar un nuevo elemento de moddirecciondenotificacion
                String cadena = "";

                if (modificacion.getSigno_registrado() != null) {
                    cadena = cadena + modificacion.getSigno_registrado() + " ";
                }

                if (modificacion.getIdclase_registrado() != null) {
                    if (claseNizaService.listarClaseNiza_id(modificacion.getIdclase_registrado()) != null) {
                        ClaseNiza nizaClase = claseNizaService.listarClaseNiza_id(modificacion.getIdclase_registrado());
                        cadena = "Clase: " + cadena + nizaClase.getNumeroClaseNiza() + " Vers. " + nizaClase.getNumeroEdicion();
                    }
                }

                if (modificacion.getTipo_genero_registrado() != null) {
                    cadena = "Genero: " + cadena + modificacion.getTipo_genero_registrado() + " ";
                }

                if (modificacion.getMarca_acomp() != null) {
                    cadena = "Marca Acomp.: " + cadena + modificacion.getMarca_acomp() + " ";
                }

                if (modificacion.getReg_lc_registrado() != null && modificacion.getReg_lc_registrado() != 0) {
                    cadena = cadena + modificacion.getReg_lc_registrado() + " ";
                }

                if (modificacion.getSerie_lc_registrado() != null) {
                    cadena = cadena + modificacion.getSerie_lc_registrado() + " ";
                }

                if (modificacion.getFecha_lc_registrado() != null) {
                    cadena = cadena + modificacion.getFecha_lc_registrado() + " ";
                }

                historial = "[Signo Reg. = " + cadena
                        + "]";
                operacion = EnumOperacion.ADICIONAR.getCodigo();
            }

            //variable del campo descripcion
            descripcion = descripcion + historial;

            //se debe proceder a guardar el historial siempre y cuando haya existido por lo menos una modificacion
            if (!descripcion.equals("")) {
                //si todo esta bien guardar la observacion
                Historial historialTramite = new Historial();
                historialTramite.setId(modificacion.getIdmodificacion());
                historialTramite.setIdUsuario(idUsuario);
                if (operacion.equals(EnumOperacion.ADICIONAR.getCodigo())) {
                    historialTramite.setIdLogTrans(idLogTrans);
                } else {
                    historialTramite.setIdLogTrans(modificacion.getIdlogtrans());
                }
                historialTramite.setOperacion(operacion);
                //obtener la descripcion de la marca
                historialTramite.setEstadoMarcaDescripcion(estado_modificacion);
                historialTramite.setUbicacionDescripcion(ubicacion_modificacion);
                historialTramite.setSeccion(EnumSeccion.DATOS_SIGNO_REGISTRADO.getCodigo());
                historialTramite.setDescripcion(descripcion);
                historialTramite.setFechaOperacion(fechaSistema);
                historialTramite.setEstado(EnumEstado.ACTIVO.getCodigo());
                historialService.crudHistorial(historialTramite, "MOD", 1);
            }

        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void generarHistorialModTitularLicenciatarioNuevo(Long idModificacion, List<ModTitularLicenciatarioNuevo> listaNuevoTitular, String estado_modificacion,
            String ubicacion_modificacion, Date fechaSistema, Long idLogTrans, Long idUsuario) throws Exception {
        try {
            //variable descripcion
            String descripcion = "";
            String operacion = EnumOperacion.MODIFICAR.getCodigo();
            //obtener el historial por cada solicitante encontrado
            String historial = "";
            String historialEliminacion = "";
            //recorrer la lista que viene desde la vista
            for (ModTitularLicenciatarioNuevo titularNuevo : listaNuevoTitular) {
                historial = "";
                descripcion = "";
                if (titularNuevo.getIdtitularlicenciatario() != null) {
                    //Obtener el solicitante
                    ModTitularLicenciatarioNuevo titularNuevoBase
                            = modTitularLicencitarioNuevoService.guardar_modificar_listar_ModTitularLicenciatarioNuevo(titularNuevo, 4);
                    if (titularNuevoBase.getIdtitularlicenciatario() != null) {
                        //obtener desde la vista
                        String x[] = titularNuevo.toString().split("\\|");
                        //obtener desde la base de datos
                        String y[] = titularNuevoBase.toString().split("\\|");

                        //Armar la cadena de observaciones
                        //no tiene observaciones
                        int sw = 0;
                        historial = "[";
                        historial = historial + "id:" + titularNuevoBase.getIdtitularlicenciatario() + "; ";
                        historialEliminacion = historialEliminacion + titularNuevoBase.getIdtitularlicenciatario() + ",";

                        for (int i = 0; i < x.length; i++) {
                            if (!x[i].equals(y[i])) {
                                historial = historial + y[i] + "; ";
                                sw = 1;
                            }
                        }
                        if (historial.length() > 2) {
                            historial = historial.substring(0, historial.length() - 2);
                        }
                        historial = historial + "]";
                        operacion = EnumOperacion.MODIFICAR.getCodigo();
                        //si no existe ninguna diferencia la cadena sera vacia.
                        if (sw == 0) {
                            historial = "";
                        }

                    } else {
                        //no se hace nada en este caso " en la base se recupero nulo"
                    }

                } else {

                    //Agregar un nuevo elemento
                    String cadenaApellidos = "";
                    if (titularNuevo.getPrimer_apellido() != null) {
                        cadenaApellidos = cadenaApellidos + titularNuevo.getPrimer_apellido();
                    }
                    if (titularNuevo.getSegundo_apellido() != null) {
                        cadenaApellidos = " " + cadenaApellidos + titularNuevo.getSegundo_apellido() + "";
                    }

                    historial = "[nombreCompleto = " + titularNuevo.getNombre_razonsocial()
                            + " " + cadenaApellidos
                            + "; numeroDocumento:  " + titularNuevo.getNumero_documento()
                            + "]";
                    operacion = EnumOperacion.ADICIONAR.getCodigo();
                }

                //variable del campo descripcion
                descripcion = descripcion + historial;

                ///////////////////////////////////////////////////////////////////////////////////////////
                //deberia mejorarse para arreglar cada uno uno de los solictiantes afectados no todo en una sola cadena es confuso            
                if (!descripcion.equals("")) {
                    //si todo esta bien guardar la observacion
                    Historial historialTramite = new Historial();
                    historialTramite.setId(idModificacion);
                    historialTramite.setIdUsuario(idUsuario);
                    if (operacion.equals(EnumOperacion.ADICIONAR.getCodigo())) {
                        historialTramite.setIdLogTrans(idLogTrans);
                    } else {
                        historialTramite.setIdLogTrans(titularNuevo.getIdlogtrans());
                    }
                    historialTramite.setOperacion(operacion);
                    //obtener la descripcion de la marca
                    historialTramite.setEstadoMarcaDescripcion(estado_modificacion);
                    historialTramite.setUbicacionDescripcion(ubicacion_modificacion);
                    historialTramite.setSeccion(EnumSeccion.DATOS_NUEVO_TITULAR.getCodigo());
                    historialTramite.setDescripcion(descripcion);
                    historialTramite.setFechaOperacion(fechaSistema);
                    historialTramite.setEstado(EnumEstado.ACTIVO.getCodigo());
                    historialService.crudHistorial(historialTramite, "MOD", 1);
                }

            }

            //busca si no hubo eliminacion
            //en caso que no se encuentra nada en la base se debe preguntar eso
            descripcion = "";
            //obtener el historial por cada solicitante encontrado
            historial = "";

            List<ObjetoEliminadoGenerico> objetoEliminadoGenerico = new ArrayList<ObjetoEliminadoGenerico>();
            if (!historialEliminacion.equals("")) {
                objetoEliminadoGenerico
                        = modTitularLicencitarioNuevoService.obtenerRegistrosEliminadosModTitularLicenciatarioNuevo(idModificacion, historialEliminacion.substring(0, historialEliminacion.length() - 1));
            }

            if (!objetoEliminadoGenerico.isEmpty()) {
                // se agrega mas texto a la descripcion si hay eliminacion
                //concatenar con la descripcion
                //descripcion = descripcion + "[DEL:" + objetoEliminadoGenerico.getObjetoEliminado() + "]";
                descripcion = descripcion + "[DEL: ";
                for (ObjetoEliminadoGenerico item : objetoEliminadoGenerico) {
                    descripcion = descripcion + item.getObjetoEliminado() + "; ";
                }
                if (descripcion.length() > 2) {
                    descripcion = descripcion.substring(0, descripcion.length() - 2);
                }
                descripcion = descripcion + "]";
                //deberia mejorarse para arreglar cada uno uno de los solictiantes afectados no todo en una sola cadena es confuso            
                if (!descripcion.equals("")) {
                    //si todo esta bien guardar la observacion
                    Historial historialTramite = new Historial();
                    historialTramite.setId(idModificacion);
                    historialTramite.setIdUsuario(idUsuario);
                    //   historialTramite.setIdLogTrans(idLogTrans); PREGUNTAR QUE HACER
                    historialTramite.setOperacion(EnumOperacion.ELIMINAR.getCodigo());
                    //obtener la descripcion de la marca
                    historialTramite.setEstadoMarcaDescripcion(estado_modificacion);
                    historialTramite.setUbicacionDescripcion(ubicacion_modificacion);
                    historialTramite.setSeccion(EnumSeccion.DATOS_NUEVO_TITULAR.getCodigo());
                    historialTramite.setDescripcion(descripcion);
                    historialTramite.setFechaOperacion(fechaSistema);
                    historialTramite.setEstado(EnumEstado.ACTIVO.getCodigo());
                    historialService.crudHistorial(historialTramite, "MOD", 1);
                }
            }

        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void generarHistorialTitularLicenciatarioNuevoSubsanacion(Long idModificacion, List<TitularLicenciatariosNuevos> listaTitularLicenciatariosNuevosSipi,
            String estado_modificacion, String ubicacion_modificacion, Date fechaSistema, Long idLogTrans, String tipoPersonRegistrado, Long idUsuario) throws Exception {
        try {

            String historial = "";
            int swExiste = 0;

            // Recuperamos el Solicitante desde la base de datos
            List<ModTitularLicenciatarioNuevo> listaTitularLicenciatariosNuevosHidra = modTitularLicencitarioNuevoService.listaNuevoTitularXidmodificacion(idModificacion);

            if (!listaTitularLicenciatariosNuevosHidra.isEmpty()) {
                if (!listaTitularLicenciatariosNuevosSipi.isEmpty()) {

                    for (ModTitularLicenciatarioNuevo titularLicenciatarioNuevoHidra : listaTitularLicenciatariosNuevosHidra) {
                        swExiste = 0;
                        for (TitularLicenciatariosNuevos titularLicenciatarioNuevoSipi : listaTitularLicenciatariosNuevosSipi) {
                            if (titularLicenciatarioNuevoHidra.getIdSipi().equals(titularLicenciatarioNuevoSipi.getIdPadre())) {
                                // modificar
                                historial = historial + modificarModTitularLicenciatarioNuevoSubsanacion(titularLicenciatarioNuevoHidra, titularLicenciatarioNuevoSipi);
                                swExiste = 1;
                                break;
                            }
                        }
                        if (swExiste == 0) {
                            // eliminar
                            historial = historial + eliminarModTitularLicenciatarioNuevoSubsanacion(titularLicenciatarioNuevoHidra);
                        }
                    }

                    for (TitularLicenciatariosNuevos titularLicenciatarioNuevoSipi : listaTitularLicenciatariosNuevosSipi) {
                        swExiste = 0;
                        for (ModTitularLicenciatarioNuevo titularLicenciatarioNuevoHidra : listaTitularLicenciatariosNuevosHidra) {
                            if (titularLicenciatarioNuevoHidra.getIdSipi().equals(titularLicenciatarioNuevoSipi.getIdPadre())) {
                                // solo virificar que exista
                                swExiste = 1;
                                break;
                            }
                        }
                        if (swExiste == 0) {
                            // adicionar
                            historial = historial + adicionarModTitularLicenciatarioNuevoSubsanacion(titularLicenciatarioNuevoSipi);
                        }
                    }
                } else {
                    // eliminar
                    for (ModTitularLicenciatarioNuevo titularLicenciatarioNuevoHidra : listaTitularLicenciatariosNuevosHidra) {
                        historial = historial + eliminarModTitularLicenciatarioNuevoSubsanacion(titularLicenciatarioNuevoHidra);
                    }
                }
            } else {
                // adicionar
                for (TitularLicenciatariosNuevos titularLicenciatarioNuevoSipi : listaTitularLicenciatariosNuevosSipi) {
                    historial = historial + adicionarModTitularLicenciatarioNuevoSubsanacion(titularLicenciatarioNuevoSipi);

                }
            }

            historial = historial.trim();

            //si historial es vacío, entonces hay al menos una modificacion
            if (!historial.equals("")) {
                historial = "[" + historial + "]";

                Historial historialTramite = new Historial();
                historialTramite.setId(idModificacion);
                historialTramite.setIdUsuario(idUsuario);
                historialTramite.setIdLogTrans(idLogTrans);
                historialTramite.setOperacion(EnumOperacion.MODIFICAR.getCodigo());
                historialTramite.setEstadoMarcaDescripcion(estado_modificacion);
                historialTramite.setUbicacionDescripcion(ubicacion_modificacion);
                historialTramite.setSeccion(EnumSeccion.DATOS_NUEVO_TITULAR.getCodigo());
                historialTramite.setDescripcion(historial);
                historialTramite.setFechaOperacion(fechaSistema);
                historialTramite.setEstado(EnumEstado.ACTIVO.getCodigo());

                historialService.crudHistorial(historialTramite, "MOD", 1);
            }
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public String modificarModTitularLicenciatarioNuevoSubsanacion(ModTitularLicenciatarioNuevo titularLicenciatarioNuevoHidra,
            TitularLicenciatariosNuevos titularLicenciatarioNuevoSipi) throws Exception {

        String historial = "MOD: ";
        int sw = 0;

        if (titularLicenciatarioNuevoSipi.getNombreRazonSocial() != null) {
            if (titularLicenciatarioNuevoHidra.getNombre_razonsocial() != null) {
                if (!titularLicenciatarioNuevoHidra.getNombre_razonsocial().equals("")) {
                    if (!titularLicenciatarioNuevoSipi.getNombreRazonSocial().equals(titularLicenciatarioNuevoHidra.getNombre_razonsocial())) {
                        historial = historial + "Nombre=" + titularLicenciatarioNuevoHidra.getNombre_razonsocial() + "; ";
                        sw = 1;
                    }
                }
            }
        }

        if (titularLicenciatarioNuevoSipi.getPrimerApellido() != null) {
            if (titularLicenciatarioNuevoHidra.getPrimer_apellido() != null) {
                if (!titularLicenciatarioNuevoHidra.getPrimer_apellido().equals("")) {
                    if (!titularLicenciatarioNuevoSipi.getPrimerApellido().equals(titularLicenciatarioNuevoHidra.getPrimer_apellido())) {
                        historial = historial + "Primer Apellido=" + titularLicenciatarioNuevoHidra.getPrimer_apellido() + "; ";
                        sw = 1;
                    }
                }
            }
        }

        if (titularLicenciatarioNuevoSipi.getSegundoApellido() != null) {
            if (titularLicenciatarioNuevoHidra.getSegundo_apellido() != null) {
                if (!titularLicenciatarioNuevoHidra.getSegundo_apellido().equals("")) {
                    if (!titularLicenciatarioNuevoSipi.getSegundoApellido().equals(titularLicenciatarioNuevoHidra.getSegundo_apellido())) {
                        historial = historial + "Segundo Apellido=" + titularLicenciatarioNuevoHidra.getSegundo_apellido() + "; ";
                        sw = 1;
                    }
                }
            }
        }

        if (titularLicenciatarioNuevoSipi.getNroDocumento() != null) {
            if (titularLicenciatarioNuevoHidra.getNumero_documento() != null) {
                if (!titularLicenciatarioNuevoHidra.getNumero_documento().equals("")) {
                    if (!titularLicenciatarioNuevoSipi.getNroDocumento().equals(titularLicenciatarioNuevoHidra.getNumero_documento())) {
                        historial = historial + "N° Doc.=" + titularLicenciatarioNuevoHidra.getNumero_documento() + "; ";
                        sw = 1;
                    }
                }
            }
        }

        if (titularLicenciatarioNuevoSipi.getTipoDocumento() != null) {
            if (titularLicenciatarioNuevoHidra.getTipo_documento() != null) {
                if (!titularLicenciatarioNuevoHidra.getTipo_documento().equals("")) {
                    if (!titularLicenciatarioNuevoSipi.getTipoDocumento().equals(titularLicenciatarioNuevoHidra.getTipo_documento())) {
                        historial = historial + "Tipo Doc.=" + dominioService.obtenerNombreCodigoDominio("tipo_documento", titularLicenciatarioNuevoHidra.getTipo_documento()) + "; ";
                        sw = 1;
                    }
                }
            }
        }

        if (titularLicenciatarioNuevoSipi.getGenero() != null) {
            if (titularLicenciatarioNuevoHidra.getGenero() != null) {
                if (!titularLicenciatarioNuevoHidra.getGenero().equals("")) {
                    if (!titularLicenciatarioNuevoSipi.getGenero().equals(titularLicenciatarioNuevoHidra.getGenero())) {
                        historial = historial + "Genero=" + titularLicenciatarioNuevoHidra.getGenero() + "; ";
                        sw = 1;
                    }
                }
            }
        }

        if (titularLicenciatarioNuevoSipi.getLugarExpedicion() != null) {
            if (titularLicenciatarioNuevoHidra.getLugar_expedicion() != null) {
                if (!titularLicenciatarioNuevoHidra.getLugar_expedicion().equals("")) {
                    if (!titularLicenciatarioNuevoSipi.getLugarExpedicion().equals(titularLicenciatarioNuevoHidra.getLugar_expedicion())) {
                        historial = historial + "Lugar Exp.=" + dominioService.obtenerNombreCodigoDominio("lugar_expedicion", titularLicenciatarioNuevoHidra.getLugar_expedicion()) + "; ";
                        sw = 1;
                    }
                }
            }
        }

        if (titularLicenciatarioNuevoSipi.getPais() != null) {
            if (titularLicenciatarioNuevoHidra.getPais() != null) {
                if (!titularLicenciatarioNuevoHidra.getPais().equals("")) {
                    if (!titularLicenciatarioNuevoSipi.getPais().equals(titularLicenciatarioNuevoHidra.getPais())) {
                        historial = historial + "País=" + dominioService.obtenerNombreCodigoDominio("pais", titularLicenciatarioNuevoHidra.getPais()) + "; ";
                        sw = 1;
                    }
                }
            }
        }

        if (titularLicenciatarioNuevoSipi.getSolicitudDepartamento() != null) {
            if (titularLicenciatarioNuevoHidra.getSolicitud_departamento() != null) {
                if (!titularLicenciatarioNuevoHidra.getSolicitud_departamento().equals("")) {
                    if (!titularLicenciatarioNuevoSipi.getSolicitudDepartamento().equals(titularLicenciatarioNuevoHidra.getSolicitud_departamento())) {
                        historial = historial + "Departamento=" + dominioService.obtenerNombreCodigoDominio("departamento", titularLicenciatarioNuevoHidra.getSolicitud_departamento()) + "; ";
                        sw = 1;
                    }
                }
            }
        }

        if (titularLicenciatarioNuevoSipi.getTelefono() != null) {
            if (titularLicenciatarioNuevoHidra.getTelefono() != null) {
                if (!titularLicenciatarioNuevoHidra.getTelefono().equals("")) {
                    if (!titularLicenciatarioNuevoSipi.getTelefono().equals(titularLicenciatarioNuevoHidra.getTelefono())) {
                        historial = historial + "Teléfono=" + titularLicenciatarioNuevoHidra.getTelefono() + "; ";
                        sw = 1;
                    }
                }
            }
        }

        if (titularLicenciatarioNuevoSipi.getCelular() != null) {
            if (titularLicenciatarioNuevoHidra.getCelular() != null) {
                if (!titularLicenciatarioNuevoHidra.getCelular().equals("")) {
                    if (!titularLicenciatarioNuevoSipi.getCelular().equals(titularLicenciatarioNuevoHidra.getCelular())) {
                        historial = historial + "Celular=" + titularLicenciatarioNuevoHidra.getCelular() + "; ";
                        sw = 1;
                    }
                }
            }
        }

        if (titularLicenciatarioNuevoSipi.getDireccion() != null) {
            if (titularLicenciatarioNuevoHidra.getDireccion() != null) {
                if (!titularLicenciatarioNuevoHidra.getDireccion().equals("")) {
                    if (!titularLicenciatarioNuevoSipi.getDireccion().equals(titularLicenciatarioNuevoHidra.getDireccion())) {
                        historial = historial + "Dirección=" + titularLicenciatarioNuevoHidra.getDireccion() + "; ";
                        sw = 1;
                    }
                }
            }
        }

        if (titularLicenciatarioNuevoSipi.getEmail() != null) {
            if (titularLicenciatarioNuevoHidra.getEmail() != null) {
                if (!titularLicenciatarioNuevoHidra.getEmail().equals("")) {
                    if (!titularLicenciatarioNuevoSipi.getEmail().equals(titularLicenciatarioNuevoHidra.getEmail())) {
                        historial = historial + "E-mail=" + titularLicenciatarioNuevoHidra.getEmail() + "; ";
                        sw = 1;
                    }
                }
            }
        }

        if (titularLicenciatarioNuevoSipi.getTipoPersona() != null) {
            if (titularLicenciatarioNuevoHidra.getTipo_persona() != null) {
                if (!titularLicenciatarioNuevoHidra.getTipo_persona().equals("")) {
                    if (!titularLicenciatarioNuevoSipi.getTipoPersona().equals(titularLicenciatarioNuevoHidra.getTipo_persona())) {
                        historial = historial + "Tipo Persona=" + titularLicenciatarioNuevoHidra.getTipo_persona() + "; ";
                        sw = 1;
                    }
                }
            }
        }

        if (titularLicenciatarioNuevoSipi.getTipoTitular() != null) {
            if (titularLicenciatarioNuevoHidra.getTipo_titular() != null) {
                if (!titularLicenciatarioNuevoHidra.getTipo_titular().equals("")) {
                    if (!titularLicenciatarioNuevoSipi.getTipoTitular().equals(titularLicenciatarioNuevoHidra.getTipo_titular())) {
                        historial = historial + "Tipo Titular=" + titularLicenciatarioNuevoHidra.getTipo_titular() + "; ";
                        sw = 1;
                    }
                }
            }
        }

        if (sw == 1) {
            return historial;
        } else {
            return "";
        }
    }

    @Override
    public String eliminarModTitularLicenciatarioNuevoSubsanacion(ModTitularLicenciatarioNuevo titularLicenciatarioNuevoHidra) throws Exception {

        String historial = "DEL: ";
        int sw = 0;

        if (titularLicenciatarioNuevoHidra.getNombre_razonsocial() != null) {
            if (!titularLicenciatarioNuevoHidra.getNombre_razonsocial().equals("")) {
                historial = historial + "Nombre=" + titularLicenciatarioNuevoHidra.getNombre_razonsocial() + "; ";
                sw = 1;
            }
        }

        if (titularLicenciatarioNuevoHidra.getPrimer_apellido() != null) {
            if (!titularLicenciatarioNuevoHidra.getPrimer_apellido().equals("")) {
                historial = historial + "Primer Apellido=" + titularLicenciatarioNuevoHidra.getPrimer_apellido() + "; ";
                sw = 1;
            }
        }

        if (titularLicenciatarioNuevoHidra.getSegundo_apellido() != null) {
            if (!titularLicenciatarioNuevoHidra.getSegundo_apellido().equals("")) {
                historial = historial + "Segundo Apellido=" + titularLicenciatarioNuevoHidra.getSegundo_apellido() + "; ";
                sw = 1;
            }
        }

        if (titularLicenciatarioNuevoHidra.getNumero_documento() != null) {
            if (!titularLicenciatarioNuevoHidra.getNumero_documento().equals("")) {
                historial = historial + "N° Doc.=" + titularLicenciatarioNuevoHidra.getNumero_documento() + "; ";
                sw = 1;
            }
        }

        if (titularLicenciatarioNuevoHidra.getTipo_documento() != null) {
            if (!titularLicenciatarioNuevoHidra.getTipo_documento().equals("")) {
                historial = historial + "Tipo Doc.=" + titularLicenciatarioNuevoHidra.getTipo_documento() + "; ";
                sw = 1;
            }
        }

        if (titularLicenciatarioNuevoHidra.getGenero() != null) {
            if (!titularLicenciatarioNuevoHidra.getGenero().equals("")) {
                historial = historial + "Genero=" + titularLicenciatarioNuevoHidra.getGenero() + "; ";
                sw = 1;
            }
        }

        if (titularLicenciatarioNuevoHidra.getLugar_expedicion() != null) {
            if (!titularLicenciatarioNuevoHidra.getLugar_expedicion().equals("")) {
                historial = historial + "Lugar Exp.=" + dominioService.obtenerNombreCodigoDominio("lugar_expedicion", titularLicenciatarioNuevoHidra.getLugar_expedicion()) + "; ";
                sw = 1;
            }
        }

        if (titularLicenciatarioNuevoHidra.getPais() != null) {
            if (!titularLicenciatarioNuevoHidra.getPais().equals("")) {
                historial = historial + "País=" + dominioService.obtenerNombreCodigoDominio("pais", titularLicenciatarioNuevoHidra.getPais()) + "; ";
                sw = 1;
            }
        }

        if (titularLicenciatarioNuevoHidra.getSolicitud_departamento() != null) {
            if (!titularLicenciatarioNuevoHidra.getSolicitud_departamento().equals("")) {
                historial = historial + "Departamento=" + dominioService.obtenerNombreCodigoDominio("departamento", titularLicenciatarioNuevoHidra.getSolicitud_departamento()) + "; ";
                sw = 1;
            }
        }

        if (titularLicenciatarioNuevoHidra.getTelefono() != null) {
            if (!titularLicenciatarioNuevoHidra.getTelefono().equals("")) {
                historial = historial + "Teléfono=" + titularLicenciatarioNuevoHidra.getTelefono() + "; ";
                sw = 1;
            }
        }

        if (titularLicenciatarioNuevoHidra.getCelular() != null) {
            if (!titularLicenciatarioNuevoHidra.getCelular().equals("")) {
                historial = historial + "Celular=" + titularLicenciatarioNuevoHidra.getCelular() + "; ";
                sw = 1;
            }
        }

        if (titularLicenciatarioNuevoHidra.getDireccion() != null) {
            if (!titularLicenciatarioNuevoHidra.getDireccion().equals("")) {
                historial = historial + "Dirección=" + titularLicenciatarioNuevoHidra.getDireccion() + "; ";
                sw = 1;
            }
        }

        if (titularLicenciatarioNuevoHidra.getEmail() != null) {
            if (!titularLicenciatarioNuevoHidra.getEmail().equals("")) {
                historial = historial + "E-mail=" + titularLicenciatarioNuevoHidra.getEmail() + "; ";
                sw = 1;
            }
        }

        if (titularLicenciatarioNuevoHidra.getTipo_persona() != null) {
            if (!titularLicenciatarioNuevoHidra.getTipo_persona().equals("")) {
                historial = historial + "Tipo Persona=" + titularLicenciatarioNuevoHidra.getTipo_persona() + "; ";
                sw = 1;
            }
        }

        if (titularLicenciatarioNuevoHidra.getTipo_titular() != null) {
            if (!titularLicenciatarioNuevoHidra.getTipo_titular().equals("")) {
                historial = historial + "Tipo Titular=" + titularLicenciatarioNuevoHidra.getTipo_titular() + "; ";
                sw = 1;
            }
        }

        if (sw == 1) {
            return historial;
        } else {
            return "";
        }
    }

    @Override
    public String adicionarModTitularLicenciatarioNuevoSubsanacion(TitularLicenciatariosNuevos titularLicenciatarioNuevoSipi) throws Exception {

        String historial = "ADD: ";
        int sw = 0;

        if (titularLicenciatarioNuevoSipi.getNombreRazonSocial() != null) {
            if (!titularLicenciatarioNuevoSipi.getNombreRazonSocial().equals("")) {
                historial = historial + "Nombre=" + titularLicenciatarioNuevoSipi.getNombreRazonSocial() + "; ";
                sw = 1;
            }
        }

        if (titularLicenciatarioNuevoSipi.getPrimerApellido() != null) {
            if (!titularLicenciatarioNuevoSipi.getPrimerApellido().equals("")) {
                historial = historial + "Primer Apellido=" + titularLicenciatarioNuevoSipi.getPrimerApellido() + "; ";
                sw = 1;
            }
        }

        if (titularLicenciatarioNuevoSipi.getSegundoApellido() != null) {
            if (!titularLicenciatarioNuevoSipi.getSegundoApellido().equals("")) {
                historial = historial + "Segundo Apellido=" + titularLicenciatarioNuevoSipi.getSegundoApellido() + "; ";
                sw = 1;
            }
        }

        if (titularLicenciatarioNuevoSipi.getNroDocumento() != null) {
            if (!titularLicenciatarioNuevoSipi.getNroDocumento().equals("")) {
                historial = historial + "N° Doc.=" + titularLicenciatarioNuevoSipi.getNroDocumento() + "; ";
                sw = 1;
            }
        }

        if (titularLicenciatarioNuevoSipi.getTipoDocumento() != null) {
            if (!titularLicenciatarioNuevoSipi.getTipoDocumento().equals("")) {
                historial = historial + "Tipo Doc.=" + titularLicenciatarioNuevoSipi.getTipoDocumento() + "; ";
                sw = 1;
            }
        }

        if (titularLicenciatarioNuevoSipi.getGenero() != null) {
            if (!titularLicenciatarioNuevoSipi.getGenero().equals("")) {
                historial = historial + "Genero=" + titularLicenciatarioNuevoSipi.getGenero() + "; ";
                sw = 1;
            }
        }

        if (titularLicenciatarioNuevoSipi.getLugarExpedicion() != null) {
            if (!titularLicenciatarioNuevoSipi.getLugarExpedicion().equals("")) {
                historial = historial + "Lugar Exp.=" + dominioService.obtenerNombreCodigoDominio("lugar_expedicion", titularLicenciatarioNuevoSipi.getLugarExpedicion()) + "; ";
                sw = 1;
            }
        }

        if (titularLicenciatarioNuevoSipi.getPais() != null) {
            if (!titularLicenciatarioNuevoSipi.getPais().equals("")) {
                historial = historial + "País=" + dominioService.obtenerNombreCodigoDominio("pais", titularLicenciatarioNuevoSipi.getPais()) + "; ";
                sw = 1;
            }
        }

        if (titularLicenciatarioNuevoSipi.getSolicitudDepartamento() != null) {
            if (!titularLicenciatarioNuevoSipi.getSolicitudDepartamento().equals("")) {
                historial = historial + "Departamento=" + dominioService.obtenerNombreCodigoDominio("departamento", titularLicenciatarioNuevoSipi.getSolicitudDepartamento()) + "; ";
                sw = 1;
            }
        }

        if (titularLicenciatarioNuevoSipi.getTelefono() != null) {
            if (!titularLicenciatarioNuevoSipi.getTelefono().equals("")) {
                historial = historial + "Teléfono=" + titularLicenciatarioNuevoSipi.getTelefono() + "; ";
                sw = 1;
            }
        }

        if (titularLicenciatarioNuevoSipi.getCelular() != null) {
            if (!titularLicenciatarioNuevoSipi.getCelular().equals("")) {
                historial = historial + "Celular=" + titularLicenciatarioNuevoSipi.getCelular() + "; ";
                sw = 1;
            }
        }

        if (titularLicenciatarioNuevoSipi.getDireccion() != null) {
            if (!titularLicenciatarioNuevoSipi.getDireccion().equals("")) {
                historial = historial + "Dirección=" + titularLicenciatarioNuevoSipi.getDireccion() + "; ";
                sw = 1;
            }
        }

        if (titularLicenciatarioNuevoSipi.getEmail() != null) {
            if (!titularLicenciatarioNuevoSipi.getEmail().equals("")) {
                historial = historial + "E-mail=" + titularLicenciatarioNuevoSipi.getEmail() + "; ";
                sw = 1;
            }
        }

        if (titularLicenciatarioNuevoSipi.getTipoPersona() != null) {
            if (!titularLicenciatarioNuevoSipi.getTipoPersona().equals("")) {
                historial = historial + "Tipo Persona=" + titularLicenciatarioNuevoSipi.getTipoPersona() + "; ";
                sw = 1;
            }
        }

        if (titularLicenciatarioNuevoSipi.getTipoTitular() != null) {
            if (!titularLicenciatarioNuevoSipi.getTipoTitular().equals("")) {
                historial = historial + "Tipo Titular=" + titularLicenciatarioNuevoSipi.getTipoTitular() + "; ";
                sw = 1;
            }
        }

        if (sw == 1) {
            return historial;
        } else {
            return "";
        }
    }

    @Override
    public Long guardar_ModHistorialCreacionSolicitud(Long idmodificacion, String estado_modificacion, String ubicacion_modificacion, Long idUsuario) {
        LogTrans logTransGuardado = new LogTrans();
        try {
            // List<Usuario> listUsuario=usuarioService.listaUsuarioXidPagina(idUsuario);
            Date fechaSistema = comunService.obtenerFechaHoraServidor(1L);
            logTransGuardado = logTransService.crudLogTrans(new LogTrans(idUsuario, fechaSistema), 1);
            String estadoModificacion = dominioService.obtenerNombreCodigoDominio("estado_modificacion", estado_modificacion);
            String ubicacionModificacion = dominioService.obtenerNombreCodigoDominio("ubicacion_modificacion", ubicacion_modificacion);
            Historial historialTramite = new Historial();
            historialTramite.setId(idmodificacion);
            historialTramite.setIdUsuario(idUsuario);
            //   historialTramite.setIdLogTrans(idLogTrans); PREGUNTAR QUE HACER
            historialTramite.setOperacion(EnumOperacion.ADICIONAR.getCodigo());
            //obtener la descripcion de la marca
            historialTramite.setEstadoMarcaDescripcion(estadoModificacion);
            historialTramite.setUbicacionDescripcion(ubicacionModificacion);
            //historialTramite.setSeccion("INGRESADA");
            historialTramite.setDescripcion("FORM - PI103");
            historialTramite.setFechaOperacion(fechaSistema);
            historialTramite.setEstado(EnumEstado.ACTIVO.getCodigo());
            historialService.crudHistorial(historialTramite, "MOD", 1);
        } catch (Exception ex) {
            Logger.getLogger(ModHistorialServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return logTransGuardado.getIdLogTrans();
    }

    @Override
    public void generarHistorialDatosAdminitrativos(ModModificacion modificacion, String estado_modificacion, String ubicacion_modificacion, Date fechaSistema, Long idLogTrans, Long idUsuario) throws Exception {
        try {
            String operacion = "";
            String historial = "";
            String descripcion = "";
            String historiallu = "";
            String descripcionlu = "";

            if (modificacion.getIdmodificacion() != null) {

                ModModificacion modificacionBase = modModificacionService.guardar_modificar_listar_ModModificacion(modificacion, 4);

                //Preguntar si la direccion obtenida es distinta de null, no es necesario preguntar por si es nulo, ese caso no deberia ocurrir
                if (modificacionBase.getIdmodificacion() != null) {

                    String x[] = modificacion.toString().split("\\|");
                    String y[] = modificacionBase.toString().split("\\|");
                    int sw = 0;
                    historial = "[";
                    historial = historial + "id:" + modificacionBase.getIdmodificacion() + "; ";

                    if (!modificacion.getEstado_modificacion().equals(modificacionBase.getEstado_modificacion())) {
                        historial = "Estado: " + historial + modificacionBase.getEstado_modificacion() + "; ";
                        sw = 1;
                    }

                    if (!modificacion.getUbicacion_modificacion().equals(modificacionBase.getUbicacion_modificacion())) {
                        historial = "Ubicacion: " + historial + modificacionBase.getUbicacion_modificacion() + "; ";
                        sw = 1;
                    }
                    if (historial.length() > 2) {
                        historial = historial.substring(0, historial.length() - 2);
                    }
                    historial = historial + "]";

                    if (sw == 0) {
                        historial = "";
                    }
                    //////////////////////////////////////////////////para fecha inicio y fin en caso de Licencia de Uso
                    int swlu = 0;
                    historiallu = "[";
                    historiallu = historiallu + "id:" + modificacionBase.getIdmodificacion() + "; ";
                    if (!x[27].equals(y[27])) {
                        historiallu = historiallu + y[27] + "; ";
                        swlu = 1;
                    }
                    if (!x[28].equals(y[28])) {
                        historiallu = historiallu + y[28] + "; ";
                        swlu = 1;
                    }

                    if (historiallu.length() > 2) {
                        historiallu = historiallu.substring(0, historiallu.length() - 1);
                    }
                    historiallu = historiallu + "]";

                    if (swlu == 0) {
                        historiallu = "";
                    }
                    operacion = EnumOperacion.MODIFICAR.getCodigo();
                }

            } else {
                //Agregar un nuevo elemento de moddirecciondenotificacion
                String cadena = "";

                if (modificacion.getEstado_modificacion() != null) {
                    cadena = cadena + modificacion.getEstado_modificacion() + " ";
                }

                if (modificacion.getUbicacion_modificacion() != null) {
                    cadena = "Ubicacion: " + cadena + modificacion.getUbicacion_modificacion() + " ";
                }

                if (modificacion.getLuinicio() != null) {
                    cadena = "Inicio LU: " + cadena + modificacion.getLuinicio() + " ";
                }

                if (modificacion.getLu_fin() != null) {
                    cadena = "Fin LU: " + cadena + modificacion.getLu_fin() + " ";
                }

                historial = "[Datos Admin. = Estado: " + cadena
                        + "]";
                operacion = EnumOperacion.ADICIONAR.getCodigo();
            }

            //variable del campo descripcion
            descripcion = descripcion + historial;

            if (!descripcion.equals("")) {
                Historial historialTramite = new Historial();
                historialTramite.setId(modificacion.getIdmodificacion());
                historialTramite.setIdUsuario(idUsuario);
                if (operacion.equals(EnumOperacion.ADICIONAR.getCodigo())) {
                    historialTramite.setIdLogTrans(idLogTrans);
                } else {
                    historialTramite.setIdLogTrans(modificacion.getIdlogtrans());
                }
                historialTramite.setOperacion(operacion);
                historialTramite.setEstadoMarcaDescripcion(estado_modificacion);
                historialTramite.setUbicacionDescripcion(ubicacion_modificacion);
                historialTramite.setSeccion(EnumSeccion.DATOS_ADMINISTRATIVOS.getCodigo());
                historialTramite.setDescripcion(descripcion);
                historialTramite.setFechaOperacion(fechaSistema);
                historialTramite.setEstado(EnumEstado.ACTIVO.getCodigo());
                historialService.crudHistorial(historialTramite, "MOD", 1);
            }

            descripcionlu = descripcionlu + historiallu;
            if (!descripcionlu.equals("")) {
                Historial historialTramite = new Historial();
                historialTramite.setId(modificacion.getIdmodificacion());
                historialTramite.setIdUsuario(idUsuario);
                if (operacion.equals(EnumOperacion.ADICIONAR.getCodigo())) {
                    historialTramite.setIdLogTrans(idLogTrans);
                } else {
                    historialTramite.setIdLogTrans(modificacion.getIdlogtrans());
                }
                historialTramite.setOperacion(operacion);
                historialTramite.setEstadoMarcaDescripcion(estado_modificacion);
                historialTramite.setUbicacionDescripcion(ubicacion_modificacion);
                historialTramite.setSeccion(EnumSeccion.DATOS_NUEVO_TITULAR.getCodigo());
                historialTramite.setDescripcion(descripcionlu);
                historialTramite.setFechaOperacion(fechaSistema);
                historialTramite.setEstado(EnumEstado.ACTIVO.getCodigo());
                historialService.crudHistorial(historialTramite, "MOD", 1);
            }

        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void guardar_ModHistorialModTipoSigno(List<ModTipoSigno> listaModTipoSigno, Long idmodificacion, String estado_modificacion, String ubicacion_modificacion, Date fechaSistema, Long idLogTrans, Long idUsuario) {
        try {
            List<ModTipoSigno> lstModTipoSignoBorrar = modTipoSignoService.listado_modtiposignoXidmodificacion(idmodificacion);
            String descripcion = "";
            Integer sw = 0;
            for (ModTipoSigno item : lstModTipoSignoBorrar) {
                descripcion = descripcion + item.getTipo_signo() + "; ";
                if (item.getTipo_signo().equals("OTRO")) {
                    descripcion = "Otro: " + descripcion + item.getDescripcion_otro() + "; ";
                }

            }
            String descripcionVista = "";
            for (ModTipoSigno item : listaModTipoSigno) {
                descripcionVista = descripcionVista + item.getTipo_signo() + "; ";
                if (item.getTipo_signo().equals("OTRO")) {
                    descripcionVista = "Otro: " + descripcionVista + item.getDescripcion_otro() + "; ";
                }
            }

            if (!descripcion.equals(descripcionVista)) {
                sw = 1;
                descripcion = "[Tipo Signo: " + descripcion;
            }

            if (descripcion.length() > 2) {
                descripcion = descripcion.substring(0, descripcion.length() - 2);
            }
            descripcion = descripcion + "]";
            if (sw == 0) {
                descripcion = "";
            }

            if (!descripcion.equals("")) {
                Historial historialTramite = new Historial();
                historialTramite.setId(idmodificacion);
                historialTramite.setIdUsuario(idUsuario);
                historialTramite.setIdLogTrans(idLogTrans);
                historialTramite.setOperacion(EnumOperacion.MODIFICAR.getCodigo());
                historialTramite.setEstadoMarcaDescripcion(estado_modificacion);
                historialTramite.setUbicacionDescripcion(ubicacion_modificacion);
                historialTramite.setSeccion(EnumSeccion.DATOS_SIGNO_REGISTRADO.getCodigo());
                historialTramite.setDescripcion(descripcion);
                historialTramite.setFechaOperacion(fechaSistema);
                historialTramite.setEstado(EnumEstado.ACTIVO.getCodigo());
                historialService.crudHistorial(historialTramite, "MOD", 1);
            }
        } catch (Exception ex) {
            Logger.getLogger(ModHistorialServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void generarHistorialModModificacionSubsanacion(ModModificacion modificacion, Date fechaSistema, String estado_modificacion, String ubicacion_modificacion,
            Long idLogTrans, Long idUsuario, FormularioPI103 formularioPI103) {
        try {

            //verificar la cadena origen
            String historial = "";
            int sw = 0;

            //List<ModTipoSigno> lstModTipoSignoBorrar = modTipoSignoService.listado_modtiposignoXidmodificacion(idmodificacion);
            if (modificacion.getIdmodificacion() != null) {
                if (formularioPI103.getModificaciones() != null) {

                    // Obtener SigSignoMarca de la Base de datos
                    Modificaciones modificacionExt = formularioPI103.getModificaciones();

                    if (modificacion.getSm() != null) {
                        if (modificacionExt.getSm() != null) {
                            if (!modificacion.getSm().equals(modificacionExt.getSm())) {
                                historial = historial + "SM=" + modificacion.getSm() + "; ";
                                sw = 1;
                            }
                        }
                    }

                    // Guardar en historico, si cambió el nuemero de formulario
                    if (modificacion.getNro_formulario() != null) {
                        if (formularioPI103.getFormularios().getNumeroFormulario() != null) {
                            if (!formularioPI103.getFormularios().getNumeroFormulario().equals("")) {
                                if (!modificacion.getNro_formulario().equals(formularioPI103.getFormularios().getNumeroFormulario())) {
                                    historial = historial + "N° formulario=" + modificacion.getNro_formulario() + "; ";
                                    sw = 1;
                                }
                            }
                        }
                    }
                    // Guardar en historico, si cambió el nuemero de registro
                    if (modificacion.getNumero_registro() != null) {
                        if (modificacionExt.getNumeroRegistro() != null) {
                            if (!modificacion.getNumero_registro().equals(modificacionExt.getNumeroRegistro())) {
                                historial = historial + "N° registro=" + modificacion.getNumero_registro() + "; ";
                                sw = 1;
                            }
                        }
                    }

                    // Guardar en historico, si cambió el serie de registro
                    if (modificacion.getSerie_registro() != null) {
                        if (modificacionExt.getSerieRegistro() != null) {
                            if (!modificacionExt.getSerieRegistro().equals("")) {
                                if (!modificacion.getSerie_registro().equals(modificacionExt.getSerieRegistro())) {
                                    historial = historial + "Serie registro=" + modificacion.getSerie_registro() + "; ";
                                    sw = 1;
                                }
                            }
                        }
                    }

                    // Guardar en historico, si cambió el nuemero de renovacion
                    if (modificacion.getNumero_renovacion() != null) {
                        if (modificacionExt.getNumeroRenovacion() != null) {
                            if (!modificacion.getNumero_renovacion().equals(modificacionExt.getNumeroRenovacion())) {
                                historial = historial + "N° renovacion=" + modificacion.getNumero_renovacion() + "; ";
                                sw = 1;
                            }
                        }
                    }

                    // Guardar en historico, si cambió el serie renovacion
                    if (modificacion.getSerie_renovacion() != null) {
                        if (modificacionExt.getSerieRenovacion() != null) {
                            if (!modificacionExt.getSerieRenovacion().equals("")) {
                                if (!modificacion.getSerie_renovacion().equals(modificacionExt.getSerieRenovacion())) {
                                    historial = historial + "Serie renovacion=" + modificacion.getSerie_renovacion() + "; ";
                                    sw = 1;
                                }
                            }
                        }
                    }

                    // Guardar en historico, si cambió el numero publicacion
                    if (modificacion.getNumero_publicacion() != null) {
                        if (modificacionExt.getNumeroPublicacion() != null) {
                            if (!modificacion.getNumero_publicacion().equals(modificacionExt.getNumeroPublicacion())) {
                                historial = historial + "N° publicacion=" + modificacion.getNumero_publicacion() + "; ";
                                sw = 1;
                            }
                        }
                    }

                    // Guardar en historico, si cambió el numero publicacion
                    if (modificacion.getNumero_publicacion() != null) {
                        if (modificacionExt.getNumeroPublicacion() != null) {
                            if (!modificacion.getNumero_publicacion().equals(modificacionExt.getNumeroPublicacion())) {
                                historial = historial + "N° publicacion=" + modificacion.getNumero_publicacion() + "; ";
                                sw = 1;
                            }
                        }
                    }

                    // Guardar en historico, si cambió el signo
                    if (modificacion.getSigno_registrado() != null) {
                        if (modificacionExt.getSignoRegistro() != null) {
                            if (!modificacionExt.getSignoRegistro().equals("")) {
                                if (!modificacion.getSigno_registrado().equals(modificacionExt.getSignoRegistro())) {
                                    historial = historial + "Signo=" + modificacion.getSigno_registrado() + "; ";
                                    sw = 1;
                                }
                            }
                        }
                    }

                    // Guardar en historico, si cambió clase niza registrado
                    if (modificacion.getIdclase_registrado() != null) {
                        if (modificacionExt.getClaseRegistrado() != null) {
                            if (modificacion.getIdclase_registrado() != modificacionExt.getClaseRegistrado().intValue()) {
                                historial = historial + "Clase registrado=" + modificacion.getIdclase_registrado() + "; ";
                                sw = 1;
                            }
                        }
                    }

                    // Guardar en historico, si cambió tipo genero
                    if (modificacion.getTipo_genero_registrado() != null) {
                        if (modificacionExt.getTipoGeneroRegistrado() != null) {
                            if (!modificacionExt.getTipoGeneroRegistrado().equals("")) {
                                if (!modificacion.getTipo_genero_registrado().equals(modificacionExt.getTipoGeneroRegistrado())) {
                                    historial = historial + "Tipo genero=" + modificacion.getTipo_genero_registrado() + "; ";
                                    sw = 1;
                                }
                            }
                        }
                    }

                    // Guardar en historico, si cambió marca acomp
                    if (modificacion.getMarca_acomp() != null) {
                        if (modificacionExt.getMarcaAcomp() != null) {
                            if (!modificacionExt.getMarcaAcomp().equals("")) {
                                if (!modificacion.getMarca_acomp().equals(modificacionExt.getMarcaAcomp())) {
                                    historial = historial + "Marca acomp=" + modificacion.getMarca_acomp() + "; ";
                                    sw = 1;
                                }
                            }
                        }
                    }

                    // Guardar en historico, si cambió Reg Lc registrado
                    if (modificacion.getReg_lc_registrado() != null) {
                        if (modificacionExt.getRegLcRegistrado() != null) {
                            if (!modificacion.getReg_lc_registrado().equals(modificacionExt.getRegLcRegistrado())) {
                                historial = historial + "Reg Lc registrado=" + modificacion.getReg_lc_registrado() + "; ";
                                sw = 1;
                            }
                        }
                    }

                    // Guardar en historico, si cambió nuevo pais constitucion
                    if (modificacion.getNuevo_pais_constitucion() != null) {
                        if (modificacionExt.getNuevoPaisConstitucion() != null) {
                            if (!modificacionExt.getNuevoPaisConstitucion().equals("")) {
                                if (!modificacion.getNuevo_pais_constitucion().equals(modificacionExt.getNuevoPaisConstitucion())) {
                                    historial = historial + "Nuevo Pais=" + modificacion.getNuevo_pais_constitucion() + "; ";
                                    sw = 1;
                                }
                            }
                        }
                    }

                    // Guardar en historico, si cambió licencia de uso inicio
                    if (modificacion.getLuinicio() != null) {
                        if (modificacionExt.getLuInicio() != null) {
                            if (!modificacion.getLuinicio().equals(modificacionExt.getLuInicio())) {
                                historial = historial + "LU inicio=" + modificacion.getLuinicio() + "; ";
                                sw = 1;
                            }
                        }
                    }

                    // Guardar en historico, si cambió licencia de uso final
                    if (modificacion.getLu_fin() != null) {
                        if (modificacionExt.getLuFin() != null) {
                            if (!modificacion.getLu_fin().equals(modificacionExt.getLuFin())) {
                                historial = historial + "LU final=" + modificacion.getLu_fin() + "; ";
                                sw = 1;
                            }
                        }
                    }
                }
            }

            //si sw=1, entonces hay al menos una modificacion
            if (sw == 1) {
                historial = historial.trim();
                historial = historial.substring(0, historial.length() - 1);
                historial = "[" + historial + "]";
            } else {
                historial = "";
            }

            //se debe proceder a guardar el historial siempre y cuando haya existido por lo menos una modificacion
            if (!historial.equals("")) {

                Historial historialTramite = new Historial();
                historialTramite.setId(modificacion.getIdmodificacion());
                historialTramite.setIdUsuario(idUsuario);
                historialTramite.setIdLogTrans(idLogTrans);
                historialTramite.setOperacion(EnumOperacion.MODIFICAR.getCodigo());
                //obtener la descripcion de la marca
                historialTramite.setEstadoMarcaDescripcion(estado_modificacion);
                historialTramite.setUbicacionDescripcion(ubicacion_modificacion);
                historialTramite.setSeccion(EnumSeccion.DATOS_SIGNO_REGISTRADO.getCodigo());
                historialTramite.setDescripcion(historial);
                historialTramite.setFechaOperacion(fechaSistema);
                historialTramite.setEstado(EnumEstado.ACTIVO.getCodigo());

                historialService.crudHistorial(historialTramite, "MOD", 1);
            }
        } catch (Exception ex) {
            Logger.getLogger(ModHistorialServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void generarHistorialModTipoSignoSubsanacion(List<ModTipoSigno> listaModTipoSigno, Long idmodificacion, String estado_modificacion,
            String ubicacion_modificacion, Date fechaSistema, Long idLogTrans, Long idUsuario, List<ModTipoSignos> listaTipoSignosSipi) {
        try {

            //verificar la cadena origen
            String historial = "";
            int sw = 0, swExiste;

            if (!listaModTipoSigno.isEmpty()) {
                if (!listaTipoSignosSipi.isEmpty()) {
                    // modificar
                    historial = "MOD: ";
                    // si tipo signo externo no se encuentra en base, entonces adicionar
                    for (ModTipoSignos tipoSignoSipi : listaTipoSignosSipi) {
                        swExiste = 0;
                        for (ModTipoSigno tipoSignoBase : listaModTipoSigno) {
                            if (tipoSignoBase.getTipo_signo().equals(tipoSignoSipi.getTiposigno())) {
                                swExiste = 1;
                                break;
                            }
                        }
                        if (swExiste == 0) {
                            if (tipoSignoSipi.getTiposigno().equals("OTRO")) {
                                historial = historial + dominioService.obtenerNombreCodigoDominio("tipo_signo", tipoSignoSipi.getTiposigno()) + ":" + tipoSignoSipi.getDescripcionotro() + "; ";
                                sw = 1;
                            } else {
                                historial = historial + dominioService.obtenerNombreCodigoDominio("tipo_signo", tipoSignoSipi.getTiposigno()) + "; ";
                                sw = 1;
                            }
                        }
                    }
                    // si tipo signo base no se encuentra en externo, entonces eliminar
                    for (ModTipoSigno tipoSignoBase : listaModTipoSigno) {
                        swExiste = 0;
                        for (ModTipoSignos tipoSignoSipi : listaTipoSignosSipi) {
                            if (tipoSignoSipi.getTiposigno().equals(tipoSignoBase.getTipo_signo())) {
                                swExiste = 1;
                                break;
                            }
                        }
                        if (swExiste == 0) {
                            if (tipoSignoBase.getTipo_signo().equals("OTRO")) {
                                historial = historial + dominioService.obtenerNombreCodigoDominio("tipo_signo", tipoSignoBase.getTipo_signo()) + ":" + tipoSignoBase.getDescripcion_otro() + "; ";
                                sw = 1;
                            } else {
                                historial = historial + dominioService.obtenerNombreCodigoDominio("tipo_signo", tipoSignoBase.getTipo_signo()) + "; ";
                                sw = 1;
                            }
                        }
                    }

                } else {
                    // eliminar
                    historial = "DEL: ";
                    for (ModTipoSigno tipoSignoBase : listaModTipoSigno) {
                        if (!tipoSignoBase.getTipo_signo().equals("")) {
                            if (tipoSignoBase.getTipo_signo().equals("OTRO")) {
                                historial = historial + dominioService.obtenerNombreCodigoDominio("tipo_signo", tipoSignoBase.getTipo_signo()) + ":" + tipoSignoBase.getDescripcion_otro() + "; ";
                                sw = 1;
                            } else {
                                historial = historial + dominioService.obtenerNombreCodigoDominio("tipo_signo", tipoSignoBase.getTipo_signo()) + "; ";
                                sw = 1;
                            }
                        }
                    }
                }
            } else {
                // adicionar
                historial = "ADD: ";
                for (ModTipoSignos tipoSignoSipi : listaTipoSignosSipi) {
                    if (!tipoSignoSipi.getTiposigno().equals("")) {
                        if (tipoSignoSipi.getTiposigno().equals("OTRO")) {
                            historial = historial + dominioService.obtenerNombreCodigoDominio("tipo_signo", tipoSignoSipi.getTiposigno()) + ":" + tipoSignoSipi.getDescripcionotro() + "; ";
                            sw = 1;
                        } else {
                            historial = historial + dominioService.obtenerNombreCodigoDominio("tipo_signo", tipoSignoSipi.getTiposigno()) + "; ";
                            sw = 1;
                        }
                    }
                }
            }

            //si sw=1, entonces hay al menos una modificacion
            if (sw == 1) {
                historial = historial.trim();
                historial = historial.substring(0, historial.length() - 1);
                historial = "[" + historial + "]";
            } else {
                historial = "";
            }

            //se debe proceder a guardar el historial siempre y cuando haya existido por lo menos una modificacion
            if (!historial.equals("")) {

                Historial historialTramite = new Historial();
                historialTramite.setId(idmodificacion);
                historialTramite.setIdUsuario(idUsuario);
                historialTramite.setIdLogTrans(idLogTrans);
                historialTramite.setOperacion(EnumOperacion.MODIFICAR.getCodigo());
                //obtener la descripcion de la marca
                historialTramite.setEstadoMarcaDescripcion(estado_modificacion);
                historialTramite.setUbicacionDescripcion(ubicacion_modificacion);
                historialTramite.setSeccion(EnumSeccion.DATOS_SIGNO_REGISTRADO.getCodigo());
                historialTramite.setDescripcion(historial);
                historialTramite.setFechaOperacion(fechaSistema);
                historialTramite.setEstado(EnumEstado.ACTIVO.getCodigo());

                historialService.crudHistorial(historialTramite, "MOD", 1);
            }
        } catch (Exception ex) {
            Logger.getLogger(ModHistorialServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void guardarModHistorialSubsanacion(ModModificacion modificacion, List<ModTipoSigno> listaModTipoSigno, Long idUsuario, FormularioPI103 formularioPI103) throws Exception {
        try {
            Date fechaSistema = comunService.obtenerFechaHoraServidor(1L);
            LogTrans logTransGuardado = logTransService.crudLogTrans(new LogTrans(idUsuario, fechaSistema), 1);
            String estado_modificacion = dominioService.obtenerNombreCodigoDominio("estado_modificacion", modificacion.getEstado_modificacion());
            String ubicacion_modificacion = dominioService.obtenerNombreCodigoDominio("ubicacion_modificacion", modificacion.getUbicacion_modificacion());

            // generar historial de modificacion
            generarHistorialModModificacionSubsanacion(modificacion,
                    fechaSistema,
                    estado_modificacion,
                    ubicacion_modificacion,
                    logTransGuardado.getIdLogTrans(),
                    idUsuario,
                    formularioPI103);

            // generar historial de tipo signo
            generarHistorialModTipoSignoSubsanacion(listaModTipoSigno,
                    modificacion.getIdmodificacion(),
                    estado_modificacion,
                    ubicacion_modificacion,
                    fechaSistema,
                    logTransGuardado.getIdLogTrans(),
                    idUsuario,
                    formularioPI103.getModTipoSignos());

            // generar el historial del solicitante
            generarHistorialModSolicitanteSubsanacion(modificacion.getIdmodificacion(),
                    formularioPI103.getSolicitantes(),
                    EnumTipoPersona.SOLICITANTE.getCodigo(),
                    estado_modificacion,
                    ubicacion_modificacion,
                    fechaSistema,
                    logTransGuardado.getIdLogTrans(),
                    idUsuario);

            //generar el historial del apoderado
            generarHistorialModApoderadoSubsanacion(modificacion.getIdmodificacion(),
                    formularioPI103.getApoderados(),
                    EnumTipoPersona.APODERADO.getCodigo(),
                    estado_modificacion,
                    ubicacion_modificacion,
                    fechaSistema,
                    logTransGuardado.getIdLogTrans(),
                    idUsuario);

            //generar el historial de dirección notificacion
            generarHistorialModDireccionDeNotificacionSubsanacion(modificacion.getIdmodificacion(),
                    formularioPI103.getDirecciones().get(0),
                    estado_modificacion,
                    ubicacion_modificacion,
                    fechaSistema,
                    logTransGuardado.getIdLogTrans(),
                    idUsuario);

            //generar historial de datos del titular registrado en seccion DATOS SIGNO REGISTRADO
            generarHistorialTitularLicenciatarioRegistradoSubsanacion(modificacion.getIdmodificacion(),
                    formularioPI103.getTitularLicenciatarios(),
                    estado_modificacion,
                    ubicacion_modificacion,
                    fechaSistema,
                    logTransGuardado.getIdLogTrans(),
                    "TREG",
                    idUsuario);

            //generar historial de datos del signo registrado 
            generarHistorialTitularLicenciatarioNuevoSubsanacion(modificacion.getIdmodificacion(),
                    formularioPI103.getTiTularLicenciatarioNuevos(),
                    estado_modificacion,
                    ubicacion_modificacion,
                    fechaSistema,
                    logTransGuardado.getIdLogTrans(),
                    "PFUS",
                    idUsuario);

            /*
             generarHistorialModTitularLicenciatarioNuevo(modificacion.getIdmodificacion(),
             listaNuevoTitular, estado_modificacion, ubicacion_modificacion, fechaSistema, logTransGuardado.getIdLogTrans(), idUsuario);
             */
        } catch (Exception ex) {
            Logger.getLogger(ModHistorialServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
