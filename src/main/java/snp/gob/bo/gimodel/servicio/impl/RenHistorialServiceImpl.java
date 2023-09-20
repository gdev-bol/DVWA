/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.servicio.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import snp.gob.bo.gimodel.entidad.ClaseNiza;
import snp.gob.bo.gimodel.entidad.Historial;
import snp.gob.bo.gimodel.entidad.LogTrans;
import snp.gob.bo.gimodel.entidad.ModModificacion;
import snp.gob.bo.gimodel.entidad.ObjetoEliminadoGenerico;
import snp.gob.bo.gimodel.entidad.RenDireccionDeNotificacion;
import snp.gob.bo.gimodel.entidad.RenSolicitanteApoderado;
import snp.gob.bo.gimodel.entidad.RenSolicitudRenovacion;
import snp.gob.bo.gimodel.entidad.RenTipoSigno;
import snp.gob.bo.gimodel.entidad.RenTitularRegistrado;
import snp.gob.bo.gimodel.entidad.SigSolicitanteApoderado;
import snp.gob.bo.gimodel.entidad.Usuario;
import snp.gob.bo.gimodel.enums.EnumEstado;
import snp.gob.bo.gimodel.enums.EnumOperacion;
import snp.gob.bo.gimodel.enums.EnumSeccion;
import snp.gob.bo.gimodel.enums.EnumTipoPersona;
import snp.gob.bo.gimodel.mapper.HistorialMapper;
import snp.gob.bo.gimodel.servicio.ClaseNizaService;
import snp.gob.bo.gimodel.servicio.ComunService;
import snp.gob.bo.gimodel.servicio.DominioService;
import snp.gob.bo.gimodel.servicio.HistorialService;
import snp.gob.bo.gimodel.servicio.LogTransService;
import snp.gob.bo.gimodel.servicio.RenDireccionDeNotificacionService;
import snp.gob.bo.gimodel.servicio.RenHistorialService;
import snp.gob.bo.gimodel.servicio.RenSolicitanteApoderadoService;
import snp.gob.bo.gimodel.servicio.RenSolicitudRenovacionService;
import snp.gob.bo.gimodel.servicio.RenTipoSignoService;
import snp.gob.bo.gimodel.servicio.RenTitularRegistradoService;
import snp.gob.bo.gimodel.servicio.SigDireccionDeNotificacionService;
import snp.gob.bo.gimodel.servicio.SigSignoClaseNizaService;
import snp.gob.bo.gimodel.servicio.SigSignoMarcaService;
import snp.gob.bo.gimodel.servicio.SigSolicitanteApoderadoService;
import snp.gob.bo.gimodel.servicio.SigTipoSignoService;
import snp.gob.bo.gimodel.solicitudes.entidades.Apoderados;
import snp.gob.bo.gimodel.solicitudes.entidades.DireccionNotificaciones;
import snp.gob.bo.gimodel.solicitudes.entidades.FormularioPI104;
import snp.gob.bo.gimodel.solicitudes.entidades.RenTipoSignos;
import snp.gob.bo.gimodel.solicitudes.entidades.RenTitularRegistrados;
import snp.gob.bo.gimodel.solicitudes.entidades.Solicitantes;
import snp.gob.bo.gimodel.solicitudes.entidades.SolicitudRenovaciones;

/**
 *
 * @author ChanoRojas
 * @see Historial
 * @see SigHistorialServiceImpl
 * @see HistorialService
 * @see HistorialServiceImpl
 * @version 1.0, 01/02/2017
 */
@Service("renHistorialService")
public class RenHistorialServiceImpl implements RenHistorialService {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;
//    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    SigSolicitanteApoderadoService sigSolicitanteApoderadoService;

    @Autowired
    SigDireccionDeNotificacionService sigDireccionDeNotificacionService;

    @Autowired
    RenSolicitanteApoderadoService renSolicitanteApoderadoService;

    @Autowired
    SigSignoMarcaService sigSignoMarcaService;

    @Autowired
    HistorialService historialService;

    @Autowired
    DominioService dominioService;

    @Autowired
    SigTipoSignoService sigTipoSignoService;

    @Autowired
    SigSignoClaseNizaService sigSignoClaseNizaService;

    @Autowired
    RenDireccionDeNotificacionService renDireccionDeNotificacionService;

    @Autowired
    RenSolicitudRenovacionService renSolicitudRenovacionService;
    @Autowired
    RenTitularRegistradoService renTitularRegistradoService;
    @Autowired
    RenTipoSignoService renTipoSignoService;

    @Autowired
    ClaseNizaService claseNizaService;

    @Autowired
    ComunService comunService;

    @Autowired
    LogTransService logTransService;

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

    //genera historial rensolicitudRenovacion
    @Override
    @Transactional
    public void generarHistorialRenSolicitudRenovacionSubsanacion(RenSolicitudRenovacion renSolicitudRenovacion, FormularioPI104 formularioPI104,
            Long idLogTrans, String estado_renovacion, String ubicacion_renovacion, Date fechaSistema, Long idUsuario) throws Exception {
        try {

            // obtener el historial
            String historial = "";
            int sw = 0;

            // Obtener la solicitud renovacion
            SolicitudRenovaciones solicitudRenovacionSipi = formularioPI104.getSolicitudRenovaciones();

            // Preguntar si la direccion obtenida de Base es distinta de null
            if (renSolicitudRenovacion.getIdsolicitudrenovacion() != null) {
                // Preguntar si la direccion obtenida de Externo es distinta de null
                if (solicitudRenovacionSipi.getId() != null) {

                    // Guardar en historico, si cambió el nuemero de formulario
                    if (formularioPI104.getFormularios().getNumeroFormulario() != null) {
                        if (renSolicitudRenovacion.getNumero_formulario() != null) {
                            if (!formularioPI104.getFormularios().getNumeroFormulario().equals("")) {
                                if (!formularioPI104.getFormularios().getNumeroFormulario().equals(renSolicitudRenovacion.getNumero_formulario().toString())) {
                                    historial = historial + "Formulario=" + renSolicitudRenovacion.getNumero_formulario() + "; ";
                                    sw = 1;
                                }
                            }
                        }
                    }

                    if (solicitudRenovacionSipi.getTipoGenero() != null) {
                        if (renSolicitudRenovacion.getTipo_genero() != null) {
                            if (!renSolicitudRenovacion.getTipo_genero().equals("")) {
                                if (!solicitudRenovacionSipi.getTipoGenero().equals(renSolicitudRenovacion.getTipo_genero())) {
                                    historial = historial + "Tipo Genero=" + dominioService.obtenerNombreCodigoDominio("tipo_genero", renSolicitudRenovacion.getTipo_genero()) + "; ";
                                    sw = 1;
                                }
                            }
                        }
                    }

                    if (solicitudRenovacionSipi.getSignoRegistrado() != null) {
                        if (renSolicitudRenovacion.getSigno_registrado() != null) {
                            if (!renSolicitudRenovacion.getSigno_registrado().equals("")) {
                                if (!solicitudRenovacionSipi.getSignoRegistrado().equals(renSolicitudRenovacion.getSigno_registrado())) {
                                    historial = historial + "Signo=" + renSolicitudRenovacion.getSigno_registrado() + "; ";
                                    sw = 1;
                                }
                            }
                        }
                    }

                    if (solicitudRenovacionSipi.getClaseNizaRegistrado() != null) {
                        if (renSolicitudRenovacion.getIdclase_niza_registrado() != null) {
                            if (solicitudRenovacionSipi.getClaseNizaRegistrado() != renSolicitudRenovacion.getIdclase_niza_registrado().intValue()) {
                                historial = historial + "Clase registrado=" + renSolicitudRenovacion.getIdclase_niza_registrado() + "; ";
                                sw = 1;
                            }
                        }
                    }

                    if (solicitudRenovacionSipi.getNumeroRegistro() != null) {
                        if (renSolicitudRenovacion.getNumero_registro_registrado() != null) {
                            if (!solicitudRenovacionSipi.getNumeroRegistro().equals(renSolicitudRenovacion.getNumero_registro_registrado())) {
                                historial = historial + "N° registro=" + renSolicitudRenovacion.getNumero_registro_registrado() + "; ";
                                sw = 1;
                            }
                        }
                    }

                    if (solicitudRenovacionSipi.getSerieRegistro() != null) {
                        if (renSolicitudRenovacion.getSerie_registro_registrado() != null) {
                            if (!renSolicitudRenovacion.getSerie_registro_registrado().equals("")) {
                                if (!solicitudRenovacionSipi.getSerieRegistro().equals(renSolicitudRenovacion.getSerie_registro_registrado())) {
                                    historial = historial + "Serie registro=" + renSolicitudRenovacion.getSerie_registro_registrado() + "; ";
                                    sw = 1;
                                }
                            }
                        }
                    }

                    if (solicitudRenovacionSipi.getListaProductosLimitacion() != null) {
                        if (renSolicitudRenovacion.getLista_productos_limitacion() != null) {
                            if (!renSolicitudRenovacion.getLista_productos_limitacion().equals("")) {
                                if (!solicitudRenovacionSipi.getListaProductosLimitacion().equals(renSolicitudRenovacion.getLista_productos_limitacion())) {
                                    historial = historial + "Lista Productos=" + renSolicitudRenovacion.getLista_productos_limitacion() + "; ";
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

                if (solicitudRenovacionSipi.getTipoGenero() != null) {
                    if (!solicitudRenovacionSipi.getTipoGenero().equals("")) {
                        historial = historial + "Tipo Genero=" + solicitudRenovacionSipi.getTipoGenero();
                    }
                }

                if (solicitudRenovacionSipi.getSignoRegistrado() != null) {
                    if (!solicitudRenovacionSipi.getSignoRegistrado().equals("")) {
                        historial = historial + "Signo=" + solicitudRenovacionSipi.getSignoRegistrado();
                    }
                }

                if (solicitudRenovacionSipi.getClaseNizaRegistrado() != null) {
                    if (!solicitudRenovacionSipi.getClaseNizaRegistrado().equals("")) {
                        historial = historial + "Clase registrado=" + solicitudRenovacionSipi.getClaseNizaRegistrado();
                    }
                }

                if (solicitudRenovacionSipi.getNumeroRegistro() != null) {
                    if (!solicitudRenovacionSipi.getNumeroRegistro().equals("")) {
                        historial = historial + "N° registro=" + solicitudRenovacionSipi.getNumeroRegistro();
                    }
                }

                if (solicitudRenovacionSipi.getSerieRegistro() != null) {
                    if (!solicitudRenovacionSipi.getSerieRegistro().equals("")) {
                        historial = historial + "Serie registro=" + solicitudRenovacionSipi.getSerieRegistro();
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
                historialTramite.setId(renSolicitudRenovacion.getIdsolicitudrenovacion());
                historialTramite.setIdUsuario(idUsuario);
                historialTramite.setIdLogTrans(idLogTrans);
                historialTramite.setOperacion(EnumOperacion.MODIFICAR.getCodigo());

                //obtener la descripcion de la marca
                historialTramite.setEstadoMarcaDescripcion(estado_renovacion);
                historialTramite.setUbicacionDescripcion(ubicacion_renovacion);
                historialTramite.setSeccion(EnumSeccion.DATOS_SIGNO_REGISTRADO.getCodigo());

                historialTramite.setDescripcion(historial);
                historialTramite.setFechaOperacion(fechaSistema);
                historialTramite.setEstado(EnumEstado.ACTIVO.getCodigo());

                historialService.crudHistorial(historialTramite, "REN", 1);
            }

        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void generarHistorialRenSolicitanteSubsanacion(Long idRenSolcitudRenovacion, List<Solicitantes> listaSolicitantesSipi,
            String tipoPersona, String estado_marca, String ubicacion, Date fechaSistema, Long idLogTrans, Long idUsuario) throws Exception {
        try {
            String historial = "";
            int swExiste = 0;

            // Recuperamos el Solicitante desde la base de datos
            List<RenSolicitanteApoderado> listaSolicitantesHidra = renSolicitanteApoderadoService.buscaSolicitanteApoderadoPoridSolicitudyTipoPersona(idRenSolcitudRenovacion, tipoPersona);

            if (!listaSolicitantesHidra.isEmpty()) {
                if (!listaSolicitantesSipi.isEmpty()) {

                    for (RenSolicitanteApoderado solicitanteHidra : listaSolicitantesHidra) {
                        swExiste = 0;
                        for (Solicitantes solicitanteSipi : listaSolicitantesSipi) {
                            if (solicitanteHidra.getIdSipi().equals(solicitanteSipi.getIdPadre())) {
                                // modificar
                                historial = historial + modificarRenSolicitanteSubsanacion(solicitanteHidra, solicitanteSipi);
                                swExiste = 1;
                                break;
                            }
                        }
                        if (swExiste == 0) {
                            // eliminar
                            historial = historial + eliminarRenSolicitanteSubsanacion(solicitanteHidra);
                        }
                    }

                    for (Solicitantes solicitanteSipi : listaSolicitantesSipi) {
                        swExiste = 0;
                        for (RenSolicitanteApoderado solicitanteHidra : listaSolicitantesHidra) {
                            if (solicitanteHidra.getIdSipi().equals(solicitanteSipi.getIdPadre())) {
                                // solo virificar que exista
                                swExiste = 1;
                                break;
                            }
                        }
                        if (swExiste == 0) {
                            // adicionar
                            historial = historial + adicionarRenSolicitanteSubsanacion(solicitanteSipi);
                        }
                    }
                } else {
                    // eliminar
                    for (RenSolicitanteApoderado solicitanteHidra : listaSolicitantesHidra) {
                        historial = historial + eliminarRenSolicitanteSubsanacion(solicitanteHidra);
                    }
                }
            } else {
                // adicionar
                for (Solicitantes solicitanteSipi : listaSolicitantesSipi) {
                    historial = historial + adicionarRenSolicitanteSubsanacion(solicitanteSipi);

                }
            }

            historial = historial.trim();

            //si historial es vacío, entonces hay al menos una modificacion
            if (!historial.equals("")) {
                historial = "[" + historial + "]";

                //si todo esta bien guardar la observacion
                Historial historialTramite = new Historial();
                historialTramite.setId(idRenSolcitudRenovacion);
                historialTramite.setIdUsuario(idUsuario);
                historialTramite.setIdLogTrans(idLogTrans);
                historialTramite.setOperacion(EnumOperacion.MODIFICAR.getCodigo());
                //obtener la descripcion de la marca
                historialTramite.setEstadoMarcaDescripcion(estado_marca);
                historialTramite.setUbicacionDescripcion(ubicacion);
                historialTramite.setSeccion(EnumSeccion.SOLICITANTE.getCodigo());
                historialTramite.setDescripcion(historial);
                historialTramite.setFechaOperacion(fechaSistema);
                historialTramite.setEstado(EnumEstado.ACTIVO.getCodigo());

                historialService.crudHistorial(historialTramite, "REN", 1);
            }
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public String modificarRenSolicitanteSubsanacion(RenSolicitanteApoderado solicitanteHidra, Solicitantes solicitanteSipi) throws Exception {

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
    public String eliminarRenSolicitanteSubsanacion(RenSolicitanteApoderado solicitanteHidra) throws Exception {

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
    public String adicionarRenSolicitanteSubsanacion(Solicitantes solicitanteSipi) throws Exception {

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
    public void generarHistorialRenApoderadoSubsanacion(Long idRenSolcitudRenovacion, List<Apoderados> listaApoderadosSipi,
            String tipoPersona, String estado_marca, String ubicacion, Date fechaSistema, Long idLogTrans, Long idUsuario) throws Exception {
        try {
            String historial = "";
            int swExiste = 0;

            // Recuperamos al Apoderado desde la base de datos
            List<RenSolicitanteApoderado> listaApoderadosHidra = renSolicitanteApoderadoService.buscaSolicitanteApoderadoPoridSolicitudyTipoPersona(idRenSolcitudRenovacion, tipoPersona);

            if (!listaApoderadosHidra.isEmpty()) {
                if (!listaApoderadosSipi.isEmpty()) {

                    for (RenSolicitanteApoderado apoderadoHidra : listaApoderadosHidra) {
                        swExiste = 0;
                        for (Apoderados apoderadoSipi : listaApoderadosSipi) {
                            if (apoderadoHidra.getIdSipi().equals(apoderadoSipi.getIdPadre())) {
                                // modificar
                                historial = historial + modificarRenApoderadoSubsanacion(apoderadoHidra, apoderadoSipi);
                                swExiste = 1;
                                break;
                            }
                        }
                        if (swExiste == 0) {
                            // eliminar
                            historial = historial + eliminarRenApoderadoSubsanacion(apoderadoHidra);
                        }
                    }

                    for (Apoderados apoderadoSipi : listaApoderadosSipi) {
                        swExiste = 0;
                        for (RenSolicitanteApoderado apoderadoHidra : listaApoderadosHidra) {
                            if (apoderadoHidra.getIdSipi().equals(apoderadoSipi.getIdPadre())) {
                                // solo virificar que exista
                                swExiste = 1;
                                break;
                            }
                        }
                        if (swExiste == 0) {
                            // adicionar
                            historial = historial + adicionarRenApoderadoSubsanacion(apoderadoSipi);
                        }
                    }

                } else {
                    // eliminar
                    for (RenSolicitanteApoderado apoderadoHidra : listaApoderadosHidra) {
                        historial = historial + eliminarRenApoderadoSubsanacion(apoderadoHidra);
                    }
                }

            } else {
                // adicionar
                for (Apoderados apoderadoSipi : listaApoderadosSipi) {
                    historial = historial + adicionarRenApoderadoSubsanacion(apoderadoSipi);
                }
            }

            historial = historial.trim();

            //si historial es vacío, entonces hay al menos una modificacion
            if (!historial.equals("")) {
                historial = "[" + historial + "]";

                Historial historialTramite = new Historial();
                historialTramite.setId(idRenSolcitudRenovacion);
                historialTramite.setIdUsuario(idUsuario);
                historialTramite.setIdLogTrans(idLogTrans);
                historialTramite.setOperacion(EnumOperacion.MODIFICAR.getCodigo());
                historialTramite.setEstadoMarcaDescripcion(estado_marca);
                historialTramite.setUbicacionDescripcion(ubicacion);
                historialTramite.setSeccion(EnumSeccion.APODERADO.getCodigo());
                historialTramite.setDescripcion(historial);
                historialTramite.setFechaOperacion(fechaSistema);
                historialTramite.setEstado(EnumEstado.ACTIVO.getCodigo());

                historialService.crudHistorial(historialTramite, "REN", 1);
            }

        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public String modificarRenApoderadoSubsanacion(RenSolicitanteApoderado apoderadoHidra, Apoderados apoderadoSipi) throws Exception {

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
    public String eliminarRenApoderadoSubsanacion(RenSolicitanteApoderado apoderadoHidra) throws Exception {

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

        if (apoderadoHidra.getFax() != null) {
            if (!apoderadoHidra.getFax().equals("")) {
                historial = historial + "Fax=" + apoderadoHidra.getFax() + "; ";
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
    public String adicionarRenApoderadoSubsanacion(Apoderados apoderadoSipi) throws Exception {

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

    //genera historial direccion de notificacion
    @Override
    @Transactional
    public void generarHistorialRenDireccionDeNotificacionSubsanacion(Long idRenSolicitudRenovacion, DireccionNotificaciones direccionNotificacionSipi,
            String estado_marca, String ubicacion, Date fechaSistema, Long idLogTrans, Long idUsuario) throws Exception {
        try {

            // obtener el historial para la direccion de notificacion
            String historial = "";
            int sw = 0;

            // Obtener la direccion de notificacion de la Base
            RenDireccionDeNotificacion direccionNotificacionHidra = renSolicitanteApoderadoService.buscarRenDireccionDeNotificacionXidsolicitudrenovacion(idRenSolicitudRenovacion);

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
                historialTramite.setId(idRenSolicitudRenovacion);
                historialTramite.setIdUsuario(idUsuario);
                historialTramite.setIdLogTrans(idLogTrans);
                historialTramite.setOperacion(EnumOperacion.MODIFICAR.getCodigo());

                //obtener la descripcion de la marca
                historialTramite.setEstadoMarcaDescripcion(estado_marca);
                historialTramite.setUbicacionDescripcion(ubicacion);
                historialTramite.setSeccion(EnumSeccion.DIRECCION_DE_NOTIFICACION.getCodigo());

                historialTramite.setDescripcion(historial);
                historialTramite.setFechaOperacion(fechaSistema);
                historialTramite.setEstado(EnumEstado.ACTIVO.getCodigo());

                historialService.crudHistorial(historialTramite, "REN", 1);
            }

        } catch (Exception e) {
            throw e;
        }
    }

    //genera historial apoderado signos
    //    public void generarHistorialRenSolicitanteApoderado(Long idRenSolcitudRenovacion, List<RenSolicitanteApoderado> lstSolicitantes,
//            String tipoPersona, String estado_marca, String ubicacion, Date fechaSistema, Long idLogTrans, Long idUsuario) throws Exception {
    @Override
    public String generarHistorialRenSolicitanteApoderado(Long idRenSolcitudRenovacion, List<RenSolicitanteApoderado> lstSolicitantes,
            String tipoPersona, String estado_marca, String ubicacion, Date fechaSistema, Long idLogTrans, Long idUsuario) throws Exception {
        try {

            //variable descripcion
            String descripcion = "";

            //obtener el historial por cada solicitante encontrado
            String historial = "";
            String historialEliminacion = "";

            //recorrer la lista que viene desde la vista
            for (RenSolicitanteApoderado solicitante : lstSolicitantes) {
                if (solicitante.getIdsolicitanteapoderado() != null) {

                    //Obtener el solicitante
                    SigSolicitanteApoderado sigSolicitanteApoderado
                            = sigSolicitanteApoderadoService.obtenerSigSolicitanteApoderado(solicitante.getIdsolicitanteapoderado(), tipoPersona);

                    if (sigSolicitanteApoderado.getIdSolicitanteApoderado() != null) {

                        //obtener desde la vista
                        String x[] = solicitante.toString().split("\\|");
                        //obtener desde la base de datos
                        String y[] = sigSolicitanteApoderado.toString().split("\\|");

                        //Armar la cadena de observaciones
                        //no tiene observaciones
                        int sw = 0;
                        historial = "[";
                        historial = historial + "No. doc.:" + sigSolicitanteApoderado.getNumeroDocumento() + ";";
                        //historial = historial + "id:" + sigSolicitanteApoderado.getIdSolicitanteApoderado() + ";";
                        historialEliminacion = historialEliminacion + sigSolicitanteApoderado.getIdSolicitanteApoderado() + ",";

                        for (int i = 0; i < x.length; i++) {
                            if (!x[i].equals(y[i])) {
                                historial = historial + y[i] + ";";
                                sw = 1;
                            }
                        }
                        historial = historial + "]";

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
                        cadenaApellidos = " " + cadenaApellidos + solicitante.getSegundo_apellido() + "";
                    }

                    historial = "[ADD: nombreCompleto = " + solicitante.getNombre_razonsocial()
                            + " " + cadenaApellidos
                            + "; numeroDocumento:  " + solicitante.getNumero_documento()
                            + "]";
                }
                //variable del c    ampo descripcion
                descripcion = descripcion + historial;
            }

            //busca si no hubo eliminacion
            //en caso que no se encuentra nada en la base se debe preguntar eso
            try {

                ObjetoEliminadoGenerico objetoEliminadoGenerico
                        = sigSolicitanteApoderadoService.obtenerRegistrosEliminadosSigSolicitanteApoderado(idRenSolcitudRenovacion, historialEliminacion.substring(0, historialEliminacion.length() - 1), tipoPersona);

                if (objetoEliminadoGenerico.getId() != null) {
                    //concatenar con la descripcion
                    descripcion = descripcion + "[DEL:" + objetoEliminadoGenerico.getObjetoEliminado() + "]";
                }
            } catch (Exception e) {
                //no se hace nada en este caso:  "no habia nada en la base ***"
            }

            //se debe proceder a guardar el historial siempre y cuando haya existido por lo menos una modificacion
            if (!descripcion.equals("")) {

                //si todo esta bien guardar la observacion
                Historial historialTramite = new Historial();
                historialTramite.setId(idRenSolcitudRenovacion);
                historialTramite.setIdUsuario(idUsuario);
                historialTramite.setIdLogTrans(idLogTrans);
                historialTramite.setOperacion(EnumOperacion.MODIFICAR.getCodigo());
                //obtener la descripcion de la marca
                historialTramite.setEstadoMarcaDescripcion(estado_marca);
                historialTramite.setUbicacionDescripcion(ubicacion);
                if (tipoPersona.equals(EnumTipoPersona.SOLICITANTE.getCodigo())) {
                    historialTramite.setSeccion(EnumSeccion.SOLICITANTE.getCodigo());
                } else {
                    historialTramite.setSeccion(EnumSeccion.APODERADO.getCodigo());
                }
                historialTramite.setDescripcion(descripcion);
                historialTramite.setFechaOperacion(fechaSistema);
                historialTramite.setEstado(EnumEstado.ACTIVO.getCodigo());

                historialService.crudHistorial_Renovacion(historialTramite, "REN", 1);
            }
            return descripcion;
        } catch (Exception e) {
            throw e;
        }
    }

    //genera historial direccion de notificacion
    @Override
    @Transactional
    public String generarHistorialRenDireccionDeNotificacion(Long idRenSolicitudRenovacion, RenDireccionDeNotificacion renDireccionDeNotificacion,
            String estado_marca, String ubicacion, Date fechaSistema,
            Long idLogTrans, Long idUsuario) throws Exception {
        try {

            System.out.println("direccion de notificacion");

            //variable descripcion
            String descripcion = "";
            //obtener el historial para la direccion de notificacion
            String historial = "";
            String historialEliminacion = "";
            //preguntar si el objeto que viene su id es diferente de nulo
            if (renDireccionDeNotificacion.getIddirecciondenotificacion() != null) {
                //Obtener la direccion de notificacion de la Base
                //Preguntar si la direccion obtenida es distinta de null, no es necesario preguntar por si es nulo, ese caso no deberia ocurrir
                if (renDireccionDeNotificacionService.buscaDireccionDeNotificacionPorIdSolicitud(idRenSolicitudRenovacion).getIddirecciondenotificacion() != null) {
                    //obtener direccion desde la vista
                    String x[] = renDireccionDeNotificacion.toString().split("\\|");

//                    System.out.println("hdjksahkdsha"+x.length);
                    //obtener desde la base de datos
                    RenDireccionDeNotificacion renBase = renDireccionDeNotificacionService.buscaDireccionDeNotificacionPorIdSolicitud(idRenSolicitudRenovacion);

                    //Armar la cadena de observaciones
                    int sw = 0;
                    historial = "";
//                    historial = historial + "id:" + renDireccionDeNotificacionService.buscaDireccionDeNotificacionPorIdSolicitud(idRenSolicitudRenovacion).getIddirecciondenotificacion() + ";";
                    //los valores que son diferentes se guardaran en el historial
                    if (!renBase.getCiudad_notificacion().equals(renDireccionDeNotificacion.getCiudad_notificacion())) {
                        historial = historial + "[Ciudad Notificacion:" + renBase.getCiudad_notificacion() + "]";
                        sw = 1;
                    }
                    if (!renBase.getZona_barrio().equals(renDireccionDeNotificacion.getZona_barrio())) {
                        historial = historial + "[Barrio/Zona:" + renBase.getZona_barrio() + "]";
                        sw = 1;
                    }
                    if (!renBase.getAvenida_calle().equals(renDireccionDeNotificacion.getAvenida_calle())) {
                        historial = historial + "[Av/Calle:" + renBase.getAvenida_calle() + "]";
                        sw = 1;
                    }
                    if (!renBase.getNumero().equals(renDireccionDeNotificacion.getNumero())) {
                        historial = historial + "[N°:" + renBase.getNumero() + "]";
                        sw = 1;
                    }
                    if (!renBase.getCelular().equals(renDireccionDeNotificacion.getCelular())) {
                        historial = historial + "[Cel:" + renBase.getCelular() + "]";
                        sw = 1;
                    }

                    System.out.println("1" + renBase.getTelefono());
                    System.out.println("2" + renDireccionDeNotificacion.getTelefono());
                    if (!renBase.getTelefono().equals(renDireccionDeNotificacion.getTelefono())) {
                        historial = historial + "[Tel:" + renBase.getTelefono() + "]";
                        sw = 1;
                    }
                    if (!renBase.getReferencia_direccion().equals(renDireccionDeNotificacion.getReferencia_direccion())) {
                        historial = historial + "[Ref:" + renBase.getReferencia_direccion() + "]";
                        sw = 1;
                    }
                    if (!renBase.getEdificio().equals(renDireccionDeNotificacion.getEdificio())) {
                        historial = historial + "[Edificio:" + renBase.getEdificio() + "]";
                        sw = 1;
                    }
                    if (!renBase.getPiso().equals(renDireccionDeNotificacion.getPiso())) {
                        historial = historial + "[Piso:" + renBase.getPiso() + "]";
                        sw = 1;
                    }
                    if (!renBase.getDepartamento().equals(renDireccionDeNotificacion.getDepartamento())) {
                        historial = historial + "[Depto.:" + renBase.getDepartamento() + "]";
                        sw = 1;
                    }
                    if (!renBase.getCorreo_electronico().equals(renDireccionDeNotificacion.getCorreo_electronico())) {
                        historial = historial + "[Correo:" + renBase.getCorreo_electronico() + "]";
                        sw = 1;
                    }

//                    historial = historial + "]";
                    //si no existe ninguna diferencia la cadena sera vacia.
                    if (sw == 0) {
                        historial = "";
                    }
                }
            } else {
                //Agregar un nuevo elemento de sigdirecciondenotificacion
                String cadenaDireccion = "";

                if (renDireccionDeNotificacion.getCiudad_notificacion() != null) {
                    cadenaDireccion = renDireccionDeNotificacion.getCiudad_notificacion();
                }

                if (renDireccionDeNotificacion.getZona_barrio() != null) {
                    cadenaDireccion = " " + cadenaDireccion + renDireccionDeNotificacion.getZona_barrio();
                }

                if (renDireccionDeNotificacion.getAvenida_calle() != null) {
                    cadenaDireccion = " " + cadenaDireccion + renDireccionDeNotificacion.getAvenida_calle();
                }

                if (renDireccionDeNotificacion.getNumero() != null) {
                    cadenaDireccion = " " + cadenaDireccion + renDireccionDeNotificacion.getNumero();
                }

                historial = "[ADD: direccion = " + cadenaDireccion
                        + "]";
            }
            //variable del campo descripcion
            descripcion = descripcion + historial;
            //se debe proceder a guardar el historial siempre y cuando haya existido por lo menos una modificacion
            if (!descripcion.equals("")) {
                //si todo esta bien guardar la observacion
                Historial historialTramite = new Historial();
                historialTramite.setId(idRenSolicitudRenovacion);
                historialTramite.setIdUsuario(idUsuario);
                historialTramite.setIdLogTrans(idLogTrans);
                historialTramite.setOperacion(EnumOperacion.MODIFICAR.getCodigo());
                //obtener la descripcion de la marca
                historialTramite.setEstadoMarcaDescripcion(estado_marca);
                historialTramite.setUbicacionDescripcion(ubicacion);
                historialTramite.setSeccion(EnumSeccion.DIRECCION_DE_NOTIFICACION.getCodigo());
                historialTramite.setDescripcion(descripcion);
                historialTramite.setFechaOperacion(fechaSistema);
                historialTramite.setEstado(EnumEstado.ACTIVO.getCodigo());
                historialService.crudHistorial_Renovacion(historialTramite, "REN", 1);
            }

            return descripcion;

        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void generarHistorialTitularSignoRegistrado(RenSolicitudRenovacion renSolicitudRenovacion, List<RenTitularRegistrado> listaTitularRegistrado,
            String estado_renovacion, String ubicacion_renovacion, Date fechaSistema, Long idLogTrans, String tipoPersonRegistrado, Long idUsuario) throws Exception {
        try {

            //variable descripcion
            String descripcion = "";
            String operacion = "";
            //obtener el historial por cada solicitante encontrado
            String historial = "";
            String historialEliminacion = "";
            String titulo = "";

            //recorrer la lista que viene desde la vista
            for (RenTitularRegistrado titular : listaTitularRegistrado) {
                historial = "";
                descripcion = "";
                if (titular.getIdtitularregistrado() != null) {
                    //Obtener el solicitante
                    RenTitularRegistrado renTitularRegistradoBase = renTitularRegistradoService.buscaRenTitularRegistradoPorIdSolicitud(titular);
                    if (renTitularRegistradoBase.getIdtitularregistrado() != null) {

                        //obtener desde la vista
                        String x[] = titular.toString().split("\\|");
                        //obtener desde la base de datos
                        String y[] = renTitularRegistradoBase.toString().split("\\|");

                        //Armar la cadena de observaciones
                        //no tiene observaciones
                        int sw = 0;
                        historial = "[";
                        historial = historial + "id:" + renTitularRegistradoBase.getIdtitularregistrado() + "; ";
                        historialEliminacion = historialEliminacion + renTitularRegistradoBase.getIdtitularregistrado() + ",";

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

                    if (titular.getNombre_razonsocial() != null) {
                        titulo = "Titular Registrado: ";
                        historial = " [" + titulo + titular.getNombre_razonsocial()
                                + " " + cadenaApellidos
                                + ";  direccion:  " + titular.getDireccion()
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
                    historialTramite.setId(renSolicitudRenovacion.getIdsolicitudrenovacion());
                    historialTramite.setIdUsuario(idUsuario);
                    if (operacion.equals(EnumOperacion.ADICIONAR.getCodigo())) {
                        historialTramite.setIdLogTrans(idLogTrans);
                    } else {
                        historialTramite.setIdLogTrans(titular.getIdlogtrans());
                    }
                    historialTramite.setOperacion(operacion);
                    //obtener la descripcion de la marca
                    historialTramite.setEstadoMarcaDescripcion(estado_renovacion);
                    historialTramite.setUbicacionDescripcion(ubicacion_renovacion);
                    historialTramite.setDescripcion(descripcion);
                    historialTramite.setFechaOperacion(fechaSistema);
                    historialTramite.setEstado(EnumEstado.ACTIVO.getCodigo());
                    historialService.crudHistorial_Renovacion(historialTramite, "REN", 1);
                }
            }////FIN FOR
            descripcion = "";
            //obtener el historial por cada solicitante encontrado
            historial = "";
            //busca si no hubo eliminacion
            //en caso que no se encuentra nada en la base se debe preguntar eso
            List<ObjetoEliminadoGenerico> objetoEliminadoGenerico = new ArrayList<ObjetoEliminadoGenerico>();

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
                    historialTramite.setId(renSolicitudRenovacion.getIdsolicitudrenovacion());
                    historialTramite.setIdUsuario(idUsuario);
                    historialTramite.setIdLogTrans(renSolicitudRenovacion.getIdsolicitudrenovacion());
                    historialTramite.setOperacion(EnumOperacion.ELIMINAR.getCodigo());
                    historialTramite.setEstadoMarcaDescripcion(estado_renovacion);
                    historialTramite.setUbicacionDescripcion(ubicacion_renovacion);
                    if (tipoPersonRegistrado.equals("TREG")) {
                        //PAR AMBOS FUSION Y LO DEMAS
                        historialTramite.setSeccion(EnumSeccion.DATOS_SIGNO_REGISTRADO.getCodigo());
                    } else {
                        historialTramite.setSeccion(EnumSeccion.DATOS_NUEVO_TITULAR.getCodigo());

                    }
                    historialTramite.setDescripcion(descripcion);
                    historialTramite.setFechaOperacion(fechaSistema);
                    historialTramite.setEstado(EnumEstado.ACTIVO.getCodigo());
                    historialService.crudHistorial_Renovacion(historialTramite, "REN", 1);
                }
            }

        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void generarHistorialRenTitularRegistradoSubsanacion(Long idSolicitudRenovacion, String estado_renovacion, String ubicacion_renovacion, Date fechaSistema,
            Long idLogTrans, String tipoPersonRegistrado, Long idUsuario, List<RenTitularRegistrados> listaTitularRegistradoSipi) throws Exception {
        try {

            String historial = "";
            int sw = 0;
            int swExiste = 0;

            // Recuperamos al titular registrado desde la base de datos
            List<RenTitularRegistrado> listaTitularRegistradoHidra = renTitularRegistradoService.buscaRenTitularRegistradoPorIdSolicitud(idSolicitudRenovacion);

            if (!listaTitularRegistradoHidra.isEmpty()) {
                if (!listaTitularRegistradoSipi.isEmpty()) {
                    // modificar
                    historial = "MOD: ";
                    for (RenTitularRegistrado titularRegistradoHidra : listaTitularRegistradoHidra) {
                        swExiste = 0;
                        for (RenTitularRegistrados titularRegistradoSipi : listaTitularRegistradoSipi) {
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

                    for (RenTitularRegistrados titularRegistradoSipi : listaTitularRegistradoSipi) {
                        swExiste = 0;
                        for (RenTitularRegistrado titularRegistradoHidra : listaTitularRegistradoHidra) {
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
                    for (RenTitularRegistrado titularRegistradoHidra : listaTitularRegistradoHidra) {
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
                for (RenTitularRegistrados titularRegistradoSipi : listaTitularRegistradoSipi) {
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
                historialTramite.setId(idSolicitudRenovacion);
                historialTramite.setIdUsuario(idUsuario);
                historialTramite.setIdLogTrans(idLogTrans);
                historialTramite.setOperacion(EnumOperacion.MODIFICAR.getCodigo());
                historialTramite.setEstadoMarcaDescripcion(estado_renovacion);
                historialTramite.setUbicacionDescripcion(ubicacion_renovacion);
                historialTramite.setSeccion(EnumSeccion.DATOS_SIGNO_REGISTRADO.getCodigo());
                historialTramite.setSeccion(EnumSeccion.SIGNO.getCodigo());
                historialTramite.setDescripcion(historial);
                historialTramite.setFechaOperacion(fechaSistema);
                historialTramite.setEstado(EnumEstado.ACTIVO.getCodigo());

                historialService.crudHistorial(historialTramite, "REN", 1);
            }

        } catch (Exception e) {
            throw e;
        }
    }

    //genera historial de notificacion seccion administracion
    @Transactional
    @Override
    public String generarHistorialRenAdministracion(RenSolicitudRenovacion renSolicitudRenovacionVista,
            String ubicacion, Date fechaSistema,
            Long idLogTrans, Long idUsuario) throws Exception {
        try {
            String descripcion = null;
            HashMap<String, String> datosAdministrativos = new HashMap<>();
            RenSolicitudRenovacion renSolicitudRenovacionBase = renSolicitudRenovacionService.obtenerRenSolicitudXIdRenSolicitud(renSolicitudRenovacionVista.getIdsolicitudrenovacion());
            if (!(renSolicitudRenovacionBase.getOficina().equals(renSolicitudRenovacionVista.getOficina()))) {
                datosAdministrativos.put("Oficina", renSolicitudRenovacionBase.getOficina());
            }
            if (renSolicitudRenovacionBase.getNumero_recibo().equals(renSolicitudRenovacionVista.getNumero_recibo())) {
                datosAdministrativos.put("N° recibo", renSolicitudRenovacionBase.getNumero_recibo().toString());
            }

//            System.out.println("serie recibo"+renSolicitudRenovacionBase.getSerie_recibo());
//            System.out.println("serie reciboB"+renSolicitudRenovacionVista.getSerie_recibo());
//            if (!(renSolicitudRenovacionBase.getSerie_recibo().equals(renSolicitudRenovacionVista.getSerie_recibo()))) {
//                datosAdministrativos.put("Serie recibo", renSolicitudRenovacionBase.getSerie_recibo());
//            }
            if (renSolicitudRenovacionBase.getNumero_titulo().equals(renSolicitudRenovacionVista.getNumero_titulo())) {
                datosAdministrativos.put("N° titulo", renSolicitudRenovacionBase.getNumero_titulo().toString());
            }
//            if (!(renSolicitudRenovacionBase.getSerie_recibo().equalsIgnoreCase(renSolicitudRenovacionVista.getSerie_recibo()))) {
//                datosAdministrativos.put("Serie titulo", renSolicitudRenovacionBase.getSerie_recibo());
//            }
            Iterator it = datosAdministrativos.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry e = (Map.Entry) it.next();
                descripcion = descripcion + "[" + (e.getKey() + " " + e.getValue()) + "]";
            }

            System.out.println("descripcion de historial de renovaciones" + descripcion);
            return descripcion;
        } catch (Exception e) {
            throw e;
        }
    }

    //genera historial tipo signo registrado
    @Override
    public void guardar_RenHistorialRenTipoSigno(RenSolicitudRenovacion renSolicitudRenovacion, List<RenTipoSigno> listaRenTipoSigno, Long idsolicitudRenovacion, String estado_renovacion, String ubicacion_renovacion, Date fechaSistema, Long idLogTrans, Long idUsuario) {
        try {
            List<RenTipoSigno> lstRenTipoSignoBorrar = renTipoSignoService.buscaRenTipoSignoPorIdSolicitud(idsolicitudRenovacion);
            RenSolicitudRenovacion renBase = renSolicitudRenovacionService.obtenerRenSolicitudXIdRenSolicitud(renSolicitudRenovacion.getIdsolicitudrenovacion());

            String descripcion = "";
            Integer sw = 0;
            for (RenTipoSigno item : lstRenTipoSignoBorrar) {
                if (item != null) {
                    if (item.getTipo_signo() != null) {
                        descripcion = descripcion + item.getTipo_signo() + "; ";
                        if (item.getTipo_signo().equals("OTRO")) {
                            descripcion = "Otro: " + descripcion + item.getDescripcion_otro() + "; ";
                        }
                    }
                }

            }
            String descripcionVista = "";
            for (RenTipoSigno item : listaRenTipoSigno) {
                descripcionVista = descripcionVista + item.getTipo_signo() + "; ";
                if (item.getTipo_signo().equals("OTRO")) {
                    descripcionVista = "Otro: " + descripcionVista + item.getDescripcion_otro() + "; ";
                }
            }

            if (!descripcion.equals(descripcionVista)) {
                sw = 1;
                descripcion = "[Tipo Signo: " + descripcion;
                descripcion = descripcion + "]";
            }
            if (sw == 0) {
                descripcion = "";
            }

            //tipo marca
            if (!renBase.getTipo_genero().equals(renSolicitudRenovacion.getTipo_genero())) {
                sw = 1;
                descripcion = descripcion + "" + "[Tipo genero:" + renBase.getTipo_genero() + "]";
            }

            //signo distintivo
            System.out.println("1" + renBase.getSigno_registrado());
            System.out.println("2" + renSolicitudRenovacion.getSigno_registrado());

            if (!renBase.getSigno_registrado().equals(renSolicitudRenovacion.getSigno_registrado())) {
                sw = 1;
                descripcion = descripcion + "" + "[Signo Distintivo:" + renBase.getSigno_registrado() + "]";
            }

            //Clase niza
            /////////////////////////setearnizanum
            if (!renBase.getIdclase_niza_registrado().equals(renSolicitudRenovacion.getIdclase_niza_registrado())) {
                sw = 1;
                descripcion = descripcion + "" + "[Clase Niza:" + renBase.getIdclase_niza_registrado() + "]";
            }

            //fecha de concesion
            if (!renBase.getFecha_registro_registrado().equals(renSolicitudRenovacion.getFecha_registro_registrado())) {
                sw = 1;
                descripcion = descripcion + "" + "[Fecha Concesion:" + renBase.getFecha_registro_registrado() + "]";
            }

            if (sw == 0) {
                descripcion = "";
            }

            if (!descripcion.equals("")) {
                Historial historialTramite = new Historial();
                historialTramite.setId(idsolicitudRenovacion);
                historialTramite.setIdUsuario(idUsuario);
                historialTramite.setIdLogTrans(idLogTrans);
                historialTramite.setOperacion(EnumOperacion.MODIFICAR.getCodigo());
                historialTramite.setEstadoMarcaDescripcion(estado_renovacion);
                historialTramite.setUbicacionDescripcion(ubicacion_renovacion);
                historialTramite.setSeccion(EnumSeccion.DATOS_SIGNO_REGISTRADO.getCodigo());
                historialTramite.setDescripcion(descripcion);
                historialTramite.setFechaOperacion(fechaSistema);
                historialTramite.setEstado(EnumEstado.ACTIVO.getCodigo());
                historialService.crudHistorial_Renovacion(historialTramite, "REN", 1);
            }
        } catch (Exception ex) {
            Logger.getLogger(ModHistorialServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //genera historial tipo signo registrado
    @Override
    public void generarHistorialRenTipoSignoSubsanacion(RenSolicitudRenovacion renSolicitudRenovacion, List<RenTipoSigno> listaTipoSignosHidra,
            String estado_renovacion, String ubicacion_renovacion, Date fechaSistema, Long idLogTrans, Long idUsuario, List<RenTipoSignos> listaTipoSignosSipi) {
        try {

            //verificar la cadena origen
            String historial = "";
            int sw = 0, swExiste;

            if (!listaTipoSignosHidra.isEmpty()) {
                if (!listaTipoSignosSipi.isEmpty()) {
                    // modificar
                    historial = "MOD: ";
                    // si tipo signo externo no se encuentra en base, entonces adicionar
                    for (RenTipoSignos tipoSignoSipi : listaTipoSignosSipi) {
                        swExiste = 0;
                        for (RenTipoSigno tipoSignoHidra : listaTipoSignosHidra) {
                            if (tipoSignoHidra.getTipo_signo().equals(tipoSignoSipi.getTiposigno())) {
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
                    for (RenTipoSigno tipoSignoHidra : listaTipoSignosHidra) {
                        swExiste = 0;
                        for (RenTipoSignos tipoSignoSipi : listaTipoSignosSipi) {
                            if (tipoSignoSipi.getTiposigno().equals(tipoSignoHidra.getTipo_signo())) {
                                swExiste = 1;
                                break;
                            }
                        }
                        if (swExiste == 0) {
                            if (tipoSignoHidra.getTipo_signo().equals("OTRO")) {
                                historial = historial + dominioService.obtenerNombreCodigoDominio("tipo_signo", tipoSignoHidra.getTipo_signo()) + ":" + tipoSignoHidra.getDescripcion_otro() + "; ";
                                sw = 1;
                            } else {
                                historial = historial + dominioService.obtenerNombreCodigoDominio("tipo_signo", tipoSignoHidra.getTipo_signo()) + "; ";
                                sw = 1;
                            }
                        }
                    }

                } else {
                    // eliminar
                    historial = "DEL: ";
                    for (RenTipoSigno tipoSignoHidra : listaTipoSignosHidra) {
                        if (!tipoSignoHidra.getTipo_signo().equals("")) {
                            if (tipoSignoHidra.getTipo_signo().equals("OTRO")) {
                                historial = historial + dominioService.obtenerNombreCodigoDominio("tipo_signo", tipoSignoHidra.getTipo_signo()) + ":" + tipoSignoHidra.getDescripcion_otro() + "; ";
                                sw = 1;
                            } else {
                                historial = historial + dominioService.obtenerNombreCodigoDominio("tipo_signo", tipoSignoHidra.getTipo_signo()) + "; ";
                                sw = 1;
                            }
                        }
                    }
                }
            } else {
                // adicionar
                historial = "ADD: ";
                for (RenTipoSignos tipoSignoSipi : listaTipoSignosSipi) {
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
                historialTramite.setId(renSolicitudRenovacion.getIdsolicitudrenovacion());
                historialTramite.setIdUsuario(idUsuario);
                historialTramite.setIdLogTrans(idLogTrans);
                historialTramite.setOperacion(EnumOperacion.MODIFICAR.getCodigo());
                //obtener la descripcion de la marca
                historialTramite.setEstadoMarcaDescripcion(estado_renovacion);
                historialTramite.setUbicacionDescripcion(ubicacion_renovacion);
                historialTramite.setSeccion(EnumSeccion.DATOS_SIGNO_REGISTRADO.getCodigo());
                historialTramite.setDescripcion(historial);
                historialTramite.setFechaOperacion(fechaSistema);
                historialTramite.setEstado(EnumEstado.ACTIVO.getCodigo());

                historialService.crudHistorial(historialTramite, "REN", 1);
            }

        } catch (Exception ex) {
            Logger.getLogger(ModHistorialServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void generarHistorialReferenciaRenovacion(RenSolicitudRenovacion renSolicitudRenovacion, String estado_renovacion, String ubicacion_renovacion,
            Date fechaSistema, Long idLogTrans, Long idUsuario) throws Exception {
        try {
            //variable descripcion
            String descripcion = "";
            String operacion = "";
            //obtener el historial para la direccion de notificacion
            String historial = "";
            String historialEliminacion = "";

            //preguntar si el objeto que viene su id es diferente de nulo
            if (renSolicitudRenovacion.getIdsolicitudrenovacion() != null) {

                RenSolicitudRenovacion renSolicitudRenovacionBase = renSolicitudRenovacionService.obtenerRenSolicitudXIdRenSolicitud(renSolicitudRenovacion.getIdsolicitudrenovacion());

                //Preguntar si la direccion obtenida es distinta de null, no es necesario preguntar por si es nulo, ese caso no deberia ocurrir
                if (renSolicitudRenovacionBase.getIdsolicitudrenovacion() != null) {
                    String x[] = renSolicitudRenovacion.toString().split("\\|");
                    //obtener desde la base de datos
                    String y[] = renSolicitudRenovacionBase.toString().split("\\|");
                    int sw = 0;
                    historial = "[";
                    historial = historial + "id:" + renSolicitudRenovacionBase.getIdsolicitudrenovacion() + "; ";
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
                }

            } else {
                //Agregar un nuevo elemento de moddirecciondenotificacion
                String cadena = "";

                if (renSolicitudRenovacion.getSm() != null) {
                    cadena = "SM: " + cadena + renSolicitudRenovacion.getSm() + " ";
                }

                if (renSolicitudRenovacion.getSr() != null) {
                    cadena = cadena + renSolicitudRenovacion.getSr() + " ";
                }

                if (renSolicitudRenovacion.getGestion_sr() != null) {
                    cadena = cadena + renSolicitudRenovacion.getGestion_sr() + " ";
                }

                if (renSolicitudRenovacion.getNumero_formulario() != null) {
                    cadena = "N° Form.: " + cadena + renSolicitudRenovacion.getNumero_formulario() + " ";
                }

                if (renSolicitudRenovacion.getFecha_ingreso() != null) {
                    cadena = "Fecha Ingr: " + cadena + renSolicitudRenovacion.getFecha_ingreso() + " ";
                }

                if (renSolicitudRenovacion.getNumero_recibo() != null) {
                    cadena = "Recibo: " + cadena + renSolicitudRenovacion.getNumero_recibo() + " ";
                }

                if (renSolicitudRenovacion.getSerie_recibo() != null) {
                    cadena = cadena + renSolicitudRenovacion.getSerie_recibo() + " ";
                }
                if (renSolicitudRenovacion.getNumero_titulo() != null) {
                    cadena = "Nro titulo: " + cadena + renSolicitudRenovacion.getNumero_titulo() + " ";
                }

                if (renSolicitudRenovacion.getSerie_titulo() != null) {
                    cadena = cadena + renSolicitudRenovacion.getSerie_titulo() + " ";
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
                historialTramite.setId(renSolicitudRenovacion.getIdsolicitudrenovacion());
                historialTramite.setIdUsuario(idUsuario);

                historialTramite.setOperacion(operacion);
                if (operacion.equals(EnumOperacion.ADICIONAR.getCodigo())) {
                    historialTramite.setIdLogTrans(idLogTrans);
                } else {
                    historialTramite.setIdLogTrans(renSolicitudRenovacion.getIdlogtrans());
                }
                //obtener la descripcion de la marca
                historialTramite.setEstadoMarcaDescripcion(estado_renovacion);
                historialTramite.setUbicacionDescripcion(ubicacion_renovacion);
                historialTramite.setSeccion(EnumSeccion.REFERENCIA_SOLICITUD.getCodigo());
                historialTramite.setDescripcion(descripcion);
                historialTramite.setFechaOperacion(fechaSistema);
                historialTramite.setEstado(EnumEstado.ACTIVO.getCodigo());
                historialService.crudHistorial_Renovacion(historialTramite, "REN", 1);
            }

        } catch (Exception e) {
            throw e;
        }
    }

    public void generarHistorialDatosAdminitrativosRenovacion(RenSolicitudRenovacion renSolicitudRenovacion, String estado_renovacion, String ubicacion_renovacion, Date fechaSistema, Long idLogTrans, Long idUsuario) throws Exception {
        try {
            String operacion = "";
            String historial = "";
            String descripcion = "";
//            String historiallu = "";
//            String descripcionlu = "";

            if (renSolicitudRenovacion.getIdsolicitudrenovacion() != null) {
                RenSolicitudRenovacion renSolicitudRenovacionBase = renSolicitudRenovacionService.obtenerRenSolicitudXIdRenSolicitud(renSolicitudRenovacion.getIdsolicitudrenovacion());
                //Preguntar si la direccion obtenida es distinta de null, no es necesario preguntar por si es nulo, ese caso no deberia ocurrir
                if (renSolicitudRenovacionBase.getIdsolicitudrenovacion() != null) {

                    String x[] = renSolicitudRenovacion.toString().split("\\|");
                    String y[] = renSolicitudRenovacionBase.toString().split("\\|");
                    int sw = 0;
                    historial = "";
//                    historial = historial + "id:" + renSolicitudRenovacionBase.getIdsolicitudrenovacion() + "; ";

                    if (!renSolicitudRenovacion.getEstado_renovacion().equals(renSolicitudRenovacionBase.getEstado_renovacion())) {
                        historial = "[Estado: " + historial + renSolicitudRenovacionBase.getEstado_renovacion() + "] ";
                        sw = 1;
                    }

                    if (renSolicitudRenovacion.getUbicacion_renovacion() != null || renSolicitudRenovacionBase.getUbicacion_renovacion() != null) {
                        if (!renSolicitudRenovacion.getUbicacion_renovacion().equals(renSolicitudRenovacionBase.getUbicacion_renovacion())) {
                            historial = "[Ubicacion: " + historial + renSolicitudRenovacion.getUbicacion_renovacion() + "] ";
                            sw = 1;
                        }
                    }
                    if (historial.length() > 2) {
                        historial = historial.substring(0, historial.length() - 2);
                    }
//                    historial = historial + "]";

                    if (sw == 0) {
                        historial = "";
                    }
                    //////////////////////////////////////////////////para fecha inicio y fin en caso de Licencia de Uso
                    int swlu = 0;

                    operacion = EnumOperacion.MODIFICAR.getCodigo();
                }

            } else {
                //Agregar un nuevo elemento de moddirecciondenotificacion
                String cadena = "";

                if (renSolicitudRenovacion.getEstado_renovacion() != null) {
                    cadena = cadena + renSolicitudRenovacion.getEstado_renovacion() + " ";
                }

                if (renSolicitudRenovacion.getUbicacion_renovacion() != null) {
                    cadena = "Ubicacion: " + cadena + renSolicitudRenovacion.getUbicacion_renovacion() + " ";
                }

                historial = "[Datos Admin. = Estado: " + cadena
                        + "]";
                operacion = EnumOperacion.ADICIONAR.getCodigo();
            }

            //variable del campo descripcion
            descripcion = descripcion + historial;

            if (!descripcion.equals("")) {
                Historial historialTramite = new Historial();
                historialTramite.setId(renSolicitudRenovacion.getIdsolicitudrenovacion());
                historialTramite.setIdUsuario(idUsuario);
                if (operacion.equals(EnumOperacion.ADICIONAR.getCodigo())) {
                    historialTramite.setIdLogTrans(idLogTrans);
                } else {
                    historialTramite.setIdLogTrans(renSolicitudRenovacion.getIdlogtrans());
                }
                historialTramite.setOperacion(operacion);
                historialTramite.setEstadoMarcaDescripcion(estado_renovacion);
                historialTramite.setUbicacionDescripcion(ubicacion_renovacion);
                historialTramite.setSeccion(EnumSeccion.DATOS_ADMINISTRATIVOS.getCodigo());
                historialTramite.setDescripcion(descripcion);
                historialTramite.setFechaOperacion(fechaSistema);
                historialTramite.setEstado(EnumEstado.ACTIVO.getCodigo());
                historialService.crudHistorial_Renovacion(historialTramite, "REN", 1);
            }

        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void generarHistoriallistaProductosDatosdeMarca(RenSolicitudRenovacion renSolicitudRenovacion, String estado_renovacion, String ubicacion_renovacion, Date fechaSistema, Long idLogTrans, Long idUsuario) throws Exception {
        try {
            String operacion = "";
            String historial = "";
            String descripcion = "";
//            String historiallu = "";
//            String descripcionlu = "";

            if (renSolicitudRenovacion.getIdsolicitudrenovacion() != null) {

                RenSolicitudRenovacion renSolicitudRenovacionBase = renSolicitudRenovacionService.obtenerRenSolicitudXIdRenSolicitud(renSolicitudRenovacion.getIdsolicitudrenovacion());

                //Preguntar si la direccion obtenida es distinta de null, no es necesario preguntar por si es nulo, ese caso no deberia ocurrir
                if (renSolicitudRenovacionBase.getIdsolicitudrenovacion() != null) {

//                    String x[] = renSolicitudRenovacion.toString().split("\\|");
//                    String y[] = renSolicitudRenovacionBase.toString().split("\\|");
                    int sw = 0;
                    historial = "";
//                    historial = historial + "id:" + renSolicitudRenovacionBase.getIdsolicitudrenovacion() + "; ";

                    if (!renSolicitudRenovacion.getLista_productos_limitacion().equals(renSolicitudRenovacionBase.getLista_productos_limitacion())) {
                        historial = "[Productos Limitacion: " + renSolicitudRenovacionBase.getLista_productos_limitacion() + "] ";
                        sw = 1;
                    }

                    /////////////////////////setearnizanum
                    if (!renSolicitudRenovacion.getIdclase_niza_reclasificacion().equals(renSolicitudRenovacionBase.getIdclase_niza_reclasificacion())) {
                        ClaseNiza nizaClase = claseNizaService.listarClaseNiza_id(renSolicitudRenovacionBase.getIdclase_niza_reclasificacion());
                        historial = historial + "[Reclasificacion Solicitada: " + nizaClase.getNumeroClaseNiza() + " " + "Vers." + nizaClase.getNumeroEdicion() + "] ";
                        sw = 1;
                    }
                    if (!renSolicitudRenovacion.getNumero_registro_registrado().equals(renSolicitudRenovacionBase.getNumero_registro_registrado())) {
                        historial = historial + "[N° Registro: " + renSolicitudRenovacion.getNumero_registro_registrado() + "] ";
                        sw = 1;
                    }
                    if (!renSolicitudRenovacion.getNumero_ultima_renovacion().equals(renSolicitudRenovacionBase.getNumero_ultima_renovacion())) {
                        historial = historial + "[Ultima renovacion " + renSolicitudRenovacion.getNumero_penultima_renovacion() + "] ";
                        sw = 1;
                    }
                    if (!renSolicitudRenovacion.getNumero_penultima_renovacion().equals(renSolicitudRenovacionBase.getNumero_penultima_renovacion())) {
                        historial = historial + "[Penultima renovación: " + renSolicitudRenovacion.getNumero_penultima_renovacion() + "] ";
                        sw = 1;
                    }

                    if (sw == 0) {
                        historial = "";
                    }
                    //////////////////////////////////////////////////para fecha inicio y fin en caso de Licencia de Uso
                    int swlu = 0;

                    operacion = EnumOperacion.MODIFICAR.getCodigo();
                }

            } else {
                //Agregar un nuevo elemento de moddirecciondenotificacion
                String cadena = "[ADD]";

//                if (renSolicitudRenovacion.getEstado_renovacion() != null) {
//                    cadena = cadena + renSolicitudRenovacion.getEstado_renovacion() + " ";
//                }
//
//                if (renSolicitudRenovacion.getUbicacion_renovacion() != null) {
//                    cadena = "Ubicacion: " + cadena + renSolicitudRenovacion.getUbicacion_renovacion() + " ";
//                }
//
//                historial = "[Datos Admin. = Estado: " + cadena
//                        + "]";
                operacion = EnumOperacion.ADICIONAR.getCodigo();
            }

            //variable del campo descripcion
            descripcion = descripcion + historial;

            if (!descripcion.equals("")) {
                Historial historialTramite = new Historial();
                historialTramite.setId(renSolicitudRenovacion.getIdsolicitudrenovacion());
                historialTramite.setIdUsuario(idUsuario);
                if (operacion.equals(EnumOperacion.ADICIONAR.getCodigo())) {
                    historialTramite.setIdLogTrans(idLogTrans);
                } else {
                    historialTramite.setIdLogTrans(renSolicitudRenovacion.getIdlogtrans());
                }
                historialTramite.setOperacion(operacion);
                historialTramite.setEstadoMarcaDescripcion(estado_renovacion);
                historialTramite.setUbicacionDescripcion(ubicacion_renovacion);
                historialTramite.setSeccion("LISTA PRODUCTOS LIMITACION|DATOS DE LA MARCA");
                historialTramite.setDescripcion(descripcion);
                historialTramite.setFechaOperacion(fechaSistema);
                historialTramite.setEstado(EnumEstado.ACTIVO.getCodigo());
                historialService.crudHistorial_Renovacion(historialTramite, "REN", 1);
            }

        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Historial guardaHistorialRenovacion(Historial historial, RenSolicitudRenovacion renSolicitudRenovacion, List<RenSolicitanteApoderado> listarenApoderado, List<RenSolicitanteApoderado> listarenSolicitante, List<RenTitularRegistrado> listaTitularRegistrado, RenDireccionDeNotificacion renDireccionDeNotificacion, List<RenTipoSigno> lstRenTipoSigno, Long idUsuario) throws Exception {
        try {
            // genera la seccion de solicitante
//            System.out.println("historial de renovaciones");
            Long idLogTrans;
            String descripcionGeneral = "null";
            Date fechaSistema = comunService.obtenerFechaHoraServidor(1L);
//            System.out.println("idusuario" + idUsuario + "******************************");
            LogTrans logTransGuardado = logTransService.crudLogTrans(new LogTrans(idUsuario, fechaSistema), 1);
            idLogTrans = logTransGuardado.getIdLogTrans();
            String estado_renovacion = dominioService.obtenerNombrePorCodigoDominio(renSolicitudRenovacion.getEstado_renovacion(), "estado_renovacion");
            String ubicacion_renovacion = renSolicitudRenovacion.getUbicacion_renovacion();
//            descripcionGeneral = generaHistorialDatosSignoRegistrado(renSolicitudRenovacion, ubicacion_renovacion, fechaSistema, idUsuario);
//            descripcionGeneral = descripcionGeneral + " " + generarHistorialRenAdministracion(renSolicitudRenovacion, ubicacion_renovacion, fechaSistema, idLogTrans, idUsuario);
//            descripcionGeneral = descripcionGeneral + " " + generarHistorialRenDireccionDeNotificacion(idUsuario, renDireccionDeNotificacion, estado_renovacion, ubicacion_renovacion, fechaSistema, idLogTrans, idUsuario);

            //            descripcionGeneral = descripcionGeneral + " " + generarHistorialRenSolicitanteApoderado(idUsuario, listarenSolicitante, estado_renovacion, estado_renovacion, ubicacion_renovacion, fechaSistema, idLogTrans, idUsuario);
//            descripcionGeneral = descripcionGeneral + " " + generarHistorialRenSolicitanteApoderado(idUsuario, listarenApoderado, estado_renovacion, estado_renovacion, ubicacion_renovacion, fechaSistema, idLogTrans, idUsuario);
            if (!descripcionGeneral.equals("")) {
//                   System.out.println("descripcion " + descripcionGeneral);
                Historial historialTramite = new Historial();
                historialTramite.setId(renSolicitudRenovacion.getIdsolicitudrenovacion());
                historialTramite.setIdUsuario(idUsuario);
                historialTramite.setOperacion(EnumOperacion.MODIFICACION.getCodigo());
                historialTramite.setEstadoMarcaDescripcion(estado_renovacion);
                historialTramite.setUbicacionDescripcion(ubicacion_renovacion);
                historialTramite.setDescripcion(descripcionGeneral);
                historialTramite.setFechaOperacion(fechaSistema);
                historialTramite.setEstado(EnumEstado.ACTIVO.getCodigo());
                historialService.crudHistorial_Renovacion(historialTramite, "REN", 1);
            }
        } catch (Exception e) {
            throw e;
        }
        return new Historial();
    }

    @Override
    public Long guardar_RenHistorial(RenSolicitudRenovacion renSolicitudRenovacion, RenDireccionDeNotificacion renDireccionDeNotificacion, List<RenSolicitanteApoderado> lstSolicitantes,
            List<RenSolicitanteApoderado> lstApoderados, List<RenTitularRegistrado> listaTitularRegistrado, List<RenTipoSigno> listaRenTipoSigno, Usuario usuario) {
        Long idLogTrans = 0l;
        try {
            //List<Usuario> listUsuario=usuarioService.listaUsuarioXidPagina(idUsuario);
            Date fechaSistema = comunService.obtenerFechaHoraServidor(1L);
            LogTrans logTransGuardado = logTransService.crudLogTrans(new LogTrans(usuario.getIdusuario(), fechaSistema), 1);
            idLogTrans = logTransGuardado.getIdLogTrans();
            String estado_renovacion = dominioService.obtenerNombrePorCodigoDominio(renSolicitudRenovacion.getEstado_renovacion(), "estado_renovacion");
            String ubicacion_renovacion = renSolicitudRenovacion.getUbicacion_renovacion();

//            generarHistorialRenSolicitanteApoderado(renSolicitudRenovacion.getIdsolicitudrenovacion(),
//                    lstSolicitantes,
//                    EnumTipoPersona.SOLICITANTE.getCodigo(),
//                    estado_renovacion,
//                    ubicacion_renovacion,
//                    fechaSistema,
//                    logTransGuardado.getIdLogTrans(),
//                    usuario.getIdusuario());
            //generar el historial del apoderado
            //generar el historial del apoderado
//            generarHistorialRenSolicitanteApoderado(renSolicitudRenovacion.getIdsolicitudrenovacion(),
//                    lstApoderados,
//                    EnumTipoPersona.SOLICITANTE.getCodigo(),
//                    estado_renovacion,
//                    ubicacion_renovacion,
//                    fechaSistema,
//                    logTransGuardado.getIdLogTrans(),
//                    usuario.getIdusuario());
//            generar el historial de la dirección
//            generarHistorialRenDireccionDeNotificacion(renSolicitudRenovacion.getIdsolicitudrenovacion(),
//                    renDireccionDeNotificacion,
//                    estado_renovacion,
//                    ubicacion_renovacion,
//                    fechaSistema,
//                    logTransGuardado.getIdLogTrans(),
//                    usuario.getIdusuario());
            //generar historial de referencia solicitud
//            generarHistorialReferenciaRenovacion(renSolicitudRenovacion,
//                    estado_renovacion,
//                    ubicacion_renovacion,
//                    fechaSistema,
//                    logTransGuardado.getIdLogTrans(),
//                    usuario.getIdusuario());
            //generar historial de datos del titular registrado en seccion DATOS SIGNO REGISTRADO
//            generarHistorialTitularSignoRegistrado(renSolicitudRenovacion, listaTitularRegistrado,
//                    estado_renovacion,
//                    ubicacion_renovacion,
//                    fechaSistema,
//                    idLogTrans, "TREG",
//                    usuario.getIdusuario());
//          historial limitacion  y datos de la marca
            generarHistoriallistaProductosDatosdeMarca(renSolicitudRenovacion, estado_renovacion, ubicacion_renovacion, fechaSistema, idLogTrans, usuario.getIdusuario());

            //historia de tiposigno
            guardar_RenHistorialRenTipoSigno(renSolicitudRenovacion, listaRenTipoSigno, renSolicitudRenovacion.getIdsolicitudrenovacion(),
                    estado_renovacion, ubicacion_renovacion, fechaSistema, idLogTrans, usuario.getIdusuario());

            generarHistorialDatosAdminitrativosRenovacion(renSolicitudRenovacion, estado_renovacion, ubicacion_renovacion, fechaSistema,
                    idLogTrans, usuario.getIdusuario());

        } catch (Exception ex) {
            Logger.getLogger(RenHistorialServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return idLogTrans;
    }

    @Override
    public void guardarRenHistorialSubsanacion(RenSolicitudRenovacion renSolicitudRenovacion,
            FormularioPI104 formularioPI104, List<RenTipoSigno> listaRenTipoSigno, Usuario usuario) {

        try {

            Date fechaSistema = comunService.obtenerFechaHoraServidor(1L);
            LogTrans logTransGuardado = logTransService.crudLogTrans(new LogTrans(usuario.getIdusuario(), fechaSistema), 1);

            String estado_renovacion = "";

            if (renSolicitudRenovacion.getEstado_renovacion() != null) {
                if (!renSolicitudRenovacion.getEstado_renovacion().equals("")) {
                    estado_renovacion = dominioService.obtenerNombrePorCodigoDominio(renSolicitudRenovacion.getEstado_renovacion(), "estado_renovacion");
                }
            }

            String ubicacion_renovacion = renSolicitudRenovacion.getUbicacion_renovacion();

            // guardar el historial de RenSolicitudRenovacion
            generarHistorialRenSolicitudRenovacionSubsanacion(renSolicitudRenovacion,
                    formularioPI104,
                    logTransGuardado.getIdLogTrans(),
                    estado_renovacion,
                    ubicacion_renovacion,
                    fechaSistema,
                    usuario.getIdusuario());

            //historial de tipo signo
            generarHistorialRenTipoSignoSubsanacion(renSolicitudRenovacion,
                    listaRenTipoSigno,
                    estado_renovacion,
                    ubicacion_renovacion,
                    fechaSistema,
                    logTransGuardado.getIdLogTrans(),
                    usuario.getIdusuario(),
                    formularioPI104.getRenTipoSignos());

            //generar el historial del solicitante
            generarHistorialRenSolicitanteSubsanacion(renSolicitudRenovacion.getIdsolicitudrenovacion(),
                    formularioPI104.getSolicitantes(),
                    EnumTipoPersona.SOLICITANTE.getCodigo(),
                    estado_renovacion,
                    ubicacion_renovacion,
                    fechaSistema,
                    logTransGuardado.getIdLogTrans(),
                    usuario.getIdusuario());

            //generar el historial del apoderado
            generarHistorialRenApoderadoSubsanacion(renSolicitudRenovacion.getIdsolicitudrenovacion(),
                    formularioPI104.getApoderados(),
                    EnumTipoPersona.APODERADO.getCodigo(),
                    estado_renovacion,
                    ubicacion_renovacion,
                    fechaSistema,
                    logTransGuardado.getIdLogTrans(),
                    usuario.getIdusuario());

            //generar el historial de la dirección notificacion
            generarHistorialRenDireccionDeNotificacionSubsanacion(renSolicitudRenovacion.getIdsolicitudrenovacion(),
                    formularioPI104.getDirecciones().get(0),
                    estado_renovacion,
                    ubicacion_renovacion,
                    fechaSistema,
                    logTransGuardado.getIdLogTrans(),
                    usuario.getIdusuario());

            //generar historial de datos del titular registrado
            generarHistorialRenTitularRegistradoSubsanacion(renSolicitudRenovacion.getIdsolicitudrenovacion(),
                    estado_renovacion,
                    ubicacion_renovacion,
                    fechaSistema,
                    logTransGuardado.getIdLogTrans(),
                    "TREG",
                    usuario.getIdusuario(),
                    formularioPI104.getRenTitularRegistrados());

        } catch (Exception ex) {
            Logger.getLogger(RenHistorialServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Historial> obtienenListaHistorialPorRenSolicitudRenovacion(Long idsolicitudrenovacion, String operacion) throws Exception {
        try {
            String SQL = "select * from renhistorial where idsolicitudrenovacion = " + idsolicitudrenovacion + " and operacion = '" + operacion + "' and estado = 'AC'";
            List<Historial> listaHistorial = jdbcTemplate.query(SQL, new HistorialMapper());
            if (!listaHistorial.isEmpty()) {
                return listaHistorial;
            } else {
                return Collections.EMPTY_LIST;
            }
        } catch (Exception e) {
        }
        return Collections.EMPTY_LIST;
    }

}
