/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.servicio.impl;

import java.util.Date;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import snp.gob.bo.gimodel.bdimagen.entidad.ImagenPojo;
import snp.gob.bo.gimodel.bdimagen.entidad.SigImagen;
import snp.gob.bo.gimodel.bdimagen.entidad.SigLogoTipo;
import snp.gob.bo.gimodel.bdimagen.servicio.ImagenPojoService;
import snp.gob.bo.gimodel.entidad.ClaseNiza;
import snp.gob.bo.gimodel.entidad.Historial;
import snp.gob.bo.gimodel.entidad.LogTrans;
import snp.gob.bo.gimodel.entidad.SigDireccionDeNotificacion;
import snp.gob.bo.gimodel.entidad.SigPrioridadPreferencia;
import snp.gob.bo.gimodel.entidad.SigSignoClaseNiza;
import snp.gob.bo.gimodel.entidad.SigSignoMarca;
import snp.gob.bo.gimodel.entidad.SigSolicitanteApoderado;
import snp.gob.bo.gimodel.entidad.SigTipoSigno;
import snp.gob.bo.gimodel.enums.EnumDominio;
import snp.gob.bo.gimodel.enums.EnumEstado;
import snp.gob.bo.gimodel.enums.EnumOperacion;
import snp.gob.bo.gimodel.enums.EnumPrefijoTablas;
import snp.gob.bo.gimodel.enums.EnumTipoGenero;
import snp.gob.bo.gimodel.enums.EnumTipoInteres;
import snp.gob.bo.gimodel.enums.EnumTipoPersona;
import snp.gob.bo.gimodel.enums.EnumTipoPrioridad;
import snp.gob.bo.gimodel.enums.EnumTipoSigno;
import snp.gob.bo.gimodel.mapper.ClaseNizaMapper;
import snp.gob.bo.gimodel.mapper.SigSignoMarcaMapper;
import snp.gob.bo.gimodel.servicio.ComunService;
import snp.gob.bo.gimodel.servicio.DominioService;
import snp.gob.bo.gimodel.servicio.ExpedienteService;
import snp.gob.bo.gimodel.servicio.HistorialService;
import snp.gob.bo.gimodel.servicio.LogTransService;
import snp.gob.bo.gimodel.servicio.SigDireccionDeNotificacionService;
import snp.gob.bo.gimodel.servicio.SigHistorialService;
import snp.gob.bo.gimodel.servicio.SigPrioridadPreferenciaService;
import snp.gob.bo.gimodel.servicio.SigSignoClaseNizaService;
import snp.gob.bo.gimodel.servicio.SigSignoMarcaService;
import snp.gob.bo.gimodel.servicio.SigSolicitanteApoderadoService;
import snp.gob.bo.gimodel.servicio.SigTipoSignoService;
import snp.gob.bo.gimodel.solicitudes.entidades.FormularioPI100;
import snp.gob.bo.gimodel.solicitudes.entidades.FormularioPI101;
import snp.gob.bo.gimodel.solicitudes.entidades.FormularioPI102;
import snp.gob.bo.gimodel.solicitudes.entidades.Logotipos;
import snp.gob.bo.gimodel.solicitudes.enums.EnumFormularioIngreso;
import snp.gob.bo.gimodel.solicitudes.servicio.FormularioService;

/**
 *
 * @author Eddy Valero
 * @see ExpedienteServiceImpl
 * @version 1.0, 19/09/2016
 */
@Service("expedienteService")
public class ExpedienteServiceImpl implements ExpedienteService {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    ComunService comunService;

    @Autowired
    SigSignoMarcaService sigSignoMarcaService;

    @Autowired
    SigSolicitanteApoderadoService sigSolicitanteApoderadoService;

    @Autowired
    SigTipoSignoService sigTipoSignoService;

    @Autowired
    SigDireccionDeNotificacionService sigDireccionDeNotificacionService;

    @Autowired
    SigPrioridadPreferenciaService sigPrioridadPreferenciaService;

    @Autowired
    SigSignoClaseNizaService sigSignoClaseNizaService;

    @Autowired
    ImagenPojoService imagenPojoService;

    @Autowired
    LogTransService logTransService;

    @Autowired
    HistorialService historialService;

    @Autowired
    SigHistorialService sighistorialService;

    @Autowired
    DominioService dominioService;

    @Autowired
    private FormularioService formularioService;

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
    public SigSignoMarca obtenerSigsignomarca(String numeroExpediente, String gestionExpediente, String extensionExpediente) throws Exception {
        try {

            Long codigoSM = comunService.codificarCodigoSM(numeroExpediente, gestionExpediente, extensionExpediente);

            String SQL = "select * from sigsignomarca where sm = ? and estado='AC'";
            SigSignoMarca sigSignoMarca = jdbcTemplate.queryForObject(SQL, new Object[]{codigoSM}, new SigSignoMarcaMapper());

            return sigSignoMarca;

        } catch (EmptyResultDataAccessException e) {
            //Como no se encontro ningun registro enviar la instancia de la nueva marca.
            return new SigSignoMarca();
        } catch (Exception e) {

            throw e;
        }
    }

    @Override
    public SigSignoMarca IngresarRegistroSolicitudSignoMarca(SigSignoMarca sigSignoMarca, List<ImagenPojo> lstImagenPojo,
            List<SigSignoClaseNiza> lstSigSignoClaseNiza, List<SigSolicitanteApoderado> lstSolicitantes,
            List<SigSolicitanteApoderado> lstApoderados, List<SigTipoSigno> lstTipoSignos,
            SigDireccionDeNotificacion sigDireccion, SigPrioridadPreferencia oposicion,
            SigPrioridadPreferencia prioridadExtranjera, SigPrioridadPreferencia prioridadExposicion,
            Long idUsuario) throws Exception {
        try {

            //obtener la fecha del Servidor
            Date fechaProceso = comunService.obtenerFechaHoraServidor(1L);

            //generar el log correspondiente al proceso
            LogTrans logTransGuardado = logTransService.crudLogTrans(new LogTrans(idUsuario, fechaProceso), 1);

            /**
             * Para realizar el almacenamiento se procede en el siguiente orden
             * guardar SigSignoMarca guardar solicitante guardar apoderado
             * guardar direccion guardar oposicion andina guardar prioridad
             * extranjera guardar prioridad exposicion guardar imagen guardar
             * clase niza
             */
            sigSignoMarca.setIdLogTrans(logTransGuardado.getIdLogTrans());

            //Guardar el SigSignoMarca
            SigSignoMarca sigSignoMarcaAux = sigSignoMarcaService.registrarSigSignoMarca(sigSignoMarca);
            //guardar solicitantes
            sigSolicitanteApoderadoService.guardaListaSolicitantes(lstSolicitantes, sigSignoMarcaAux.getIdSignoMarca(), logTransGuardado.getIdLogTrans());
            //guardar apoderados
            sigSolicitanteApoderadoService.guardaListaSolicitantes(lstApoderados, sigSignoMarcaAux.getIdSignoMarca(), logTransGuardado.getIdLogTrans());

            sigDireccion.setIdLogTrans(logTransGuardado.getIdLogTrans());
            sigDireccionDeNotificacionService.guardarModificarListarSigDireccionDeNotificacion(sigDireccion, sigSignoMarcaAux.getIdSignoMarca(), 1);

            //guardar oposicion andina, siempre y cuando sea diferente de vacio
            if (oposicion.getNombreNumero().equals("")
                    || oposicion.getNombreNumero().equals("0")) {
                //en este caso no realizar ninguna accion
            } else {
                oposicion.setIdLogTrans(logTransGuardado.getIdLogTrans());
                oposicion.setTipoPrioridad(EnumTipoPrioridad.OPOSICION_ANDINA.getCodigo());
                oposicion.setTipoInteres(EnumTipoInteres.OPOSICION_ANDINA.getCodigo());
                oposicion.setIdSignoMarca(sigSignoMarcaAux.getIdSignoMarca());
                oposicion.setEstado(EnumEstado.ACTIVO.getCodigo());
                sigPrioridadPreferenciaService.registrarSigPrioridadPreferencia(oposicion);
            }

            //guardar prioridad extranjera
            if (prioridadExtranjera.getNombreNumero().equals("")
                    || prioridadExtranjera.getNombreNumero().equals("0")) {
                //en este caso no realizar ninguna accion
            } else {
                prioridadExtranjera.setIdLogTrans(logTransGuardado.getIdLogTrans());
                prioridadExtranjera.setTipoPrioridad(EnumTipoPrioridad.PRIORIDAD.getCodigo());
                prioridadExtranjera.setTipoInteres(EnumTipoInteres.EXTRANJERA.getCodigo());
                prioridadExtranjera.setIdSignoMarca(sigSignoMarcaAux.getIdSignoMarca());
                prioridadExtranjera.setEstado(EnumEstado.ACTIVO.getCodigo());
                sigPrioridadPreferenciaService.registrarSigPrioridadPreferencia(prioridadExtranjera);
            }

            //guardar prioridad exposición
            if (prioridadExposicion.getNombreNumero().equals("")
                    || prioridadExposicion.getNombreNumero().equals("0")) {
                //en este caso no realizar ninguna accion
            } else {
                prioridadExposicion.setIdLogTrans(logTransGuardado.getIdLogTrans());
                prioridadExposicion.setTipoPrioridad(EnumTipoPrioridad.PRIORIDAD.getCodigo());
                prioridadExposicion.setTipoInteres(EnumTipoInteres.EXPOSICION.getCodigo());
                prioridadExposicion.setIdSignoMarca(sigSignoMarcaAux.getIdSignoMarca());
                prioridadExposicion.setEstado(EnumEstado.ACTIVO.getCodigo());
                sigPrioridadPreferenciaService.registrarSigPrioridadPreferencia(prioridadExposicion);
            }

            //guardar todas las imagenes asociadas a la marca
            for (ImagenPojo pojo : lstImagenPojo) {
                if (pojo.getSigLogoTipo().getNombreArchivo() != null) {
                    pojo.getSigLogoTipo().setIdSignoMarca(sigSignoMarcaAux.getIdSignoMarca());
                    pojo.getSigLogoTipo().setIdLogTrans(logTransGuardado.getIdLogTrans());
                    pojo.getSigLogoTipo().setNombreArchivo(sigSignoMarcaAux.getSm().toString());

                    //  imagenPojoService.registrarImagePojo(pojo);
                    imagenPojoService.actualizarRegistroImagenPojoXSignoMarca(pojo);
                }
            }

            //guardar la clase niza
            for (SigSignoClaseNiza signoClaseNiza : lstSigSignoClaseNiza) {
                signoClaseNiza.setIdSignoMarca(sigSignoMarcaAux.getIdSignoMarca());
                signoClaseNiza.setIdLogTrans(logTransGuardado.getIdLogTrans());
                sigSignoClaseNizaService.crudSigSignoClaseNiza(signoClaseNiza, 1);
            }

            //guardar el tipo de signo
            sigTipoSignoService.guardarListaSigTipoSigno(lstTipoSignos, logTransGuardado.getIdLogTrans(), sigSignoMarcaAux.getIdSignoMarca(), 1);

            //guardar en la tabla historial
            Historial historial = new Historial();
            historial.setIdLogTrans(logTransGuardado.getIdLogTrans());
            historial.setId(sigSignoMarcaAux.getIdSignoMarca());
            historial.setIdUsuario(idUsuario);
            historial.setOperacion(EnumOperacion.ADICIONAR.getCodigo());
            historial.setEstadoMarcaDescripcion(dominioService.obtenerNombreCodigoDominio(EnumDominio.ESTADO_MARCA.getCodigo(), sigSignoMarcaAux.getEstadoMarca()));
            historial.setDescripcion(null);
            historial.setFechaOperacion(fechaProceso);

            if (sigSignoMarcaAux.getTipoGenero().equals(EnumTipoGenero.LEMA_COMERCIAL.getCodigo())) {
                historial.setDescripcion(EnumFormularioIngreso.PI102.getCodigo());
            }
            if (sigSignoMarcaAux.getTipoGenero().equals(EnumTipoGenero.ENSENIA_COMERCIAL.getCodigo())
                    || sigSignoMarcaAux.getTipoGenero().equals(EnumTipoGenero.ROTULO_COMERCIAL.getCodigo())
                    || sigSignoMarcaAux.getTipoGenero().equals(EnumTipoGenero.NOMBRE_COMERCIAL.getCodigo())) {
                historial.setDescripcion(EnumFormularioIngreso.PI101.getCodigo());
            }
            if (sigSignoMarcaAux.getTipoGenero().equals(EnumTipoGenero.DENOMINACION_ORIGEN.getCodigo())
                    || sigSignoMarcaAux.getTipoGenero().equals(EnumTipoGenero.MARCA_COLECTIVA.getCodigo())
                    || sigSignoMarcaAux.getTipoGenero().equals(EnumTipoGenero.MARCA_CERTIFICACION.getCodigo())
                    || sigSignoMarcaAux.getTipoGenero().equals(EnumTipoGenero.MARCA_PRODUCTO.getCodigo())
                    || sigSignoMarcaAux.getTipoGenero().equals(EnumTipoGenero.MARCA_SERVICIO.getCodigo())) {
                historial.setDescripcion(EnumFormularioIngreso.PI100.getCodigo());
            }

            historial.setEstado(EnumEstado.ACTIVO.getCodigo());

            historialService.crudHistorial(historial, EnumPrefijoTablas.SIGNO.getCodigo(), 1);
            return sigSignoMarcaAux;
        } catch (Exception e) {
            throw e;
        }

    }

    @Transactional
    @Override
    public void actualizarRegistroSolicitudSignoMarca(SigSignoMarca sigSignoMarca, List<ImagenPojo> lstImagenPojo, List<SigSignoClaseNiza> lstSigSignoClaseNiza, List<SigSolicitanteApoderado> lstSolicitantes,
            List<SigSolicitanteApoderado> lstApoderados, List<SigTipoSigno> lstTipoSignos, SigDireccionDeNotificacion sigDireccion,
            SigPrioridadPreferencia oposicion, SigPrioridadPreferencia prioridadExtranjera, SigPrioridadPreferencia prioridadExposicion, Long idUsuario) throws Exception {
        //generar la fecha del sistema
        Date fechaSistema = comunService.obtenerFechaHoraServidor(1L);
        LogTrans logTransGuardado = logTransService.crudLogTrans(new LogTrans(idUsuario, fechaSistema), 1);

        //guardar el historial de sigsignomarca
        sighistorialService.generarHistorialSigSignoMarca(sigSignoMarca.getIdSignoMarca(),
                sigSignoMarca,
                lstTipoSignos,
                lstSigSignoClaseNiza,
                fechaSistema,
                logTransGuardado.getIdLogTrans(),
                idUsuario);

        //generar el historial del solicitante    
        sighistorialService.generarHistorialSigSolicitanteApoderado(sigSignoMarca.getIdSignoMarca(),
                lstSolicitantes,
                EnumTipoPersona.SOLICITANTE.getCodigo(),
                dominioService.obtenerNombreCodigoDominio("estado_marca", sigSignoMarca.getEstadoMarca()),
                dominioService.obtenerNombreCodigoDominio("ubicacion", sigSignoMarca.getUbicacion()),
                fechaSistema,
                logTransGuardado.getIdLogTrans(),
                idUsuario);

        //generar el historial del apoderado
        sighistorialService.generarHistorialSigSolicitanteApoderado(sigSignoMarca.getIdSignoMarca(),
                lstApoderados,
                EnumTipoPersona.APODERADO.getCodigo(),
                dominioService.obtenerNombreCodigoDominio("estado_marca", sigSignoMarca.getEstadoMarca()),
                dominioService.obtenerNombreCodigoDominio("ubicacion", sigSignoMarca.getUbicacion()),
                fechaSistema,
                logTransGuardado.getIdLogTrans(),
                idUsuario);

        //generar el historial de la dirección de notificación
        sighistorialService.generarHistorialSigDireccionDeNotificacion(sigSignoMarca.getIdSignoMarca(),
                sigDireccion,
                dominioService.obtenerNombreCodigoDominio("estado_marca", sigSignoMarca.getEstadoMarca()),
                dominioService.obtenerNombreCodigoDominio("ubicacion", sigSignoMarca.getUbicacion()),
                fechaSistema,
                logTransGuardado.getIdLogTrans(),
                idUsuario);

        //guardar el historial de prioridad extranjera
        sighistorialService.generarHistorialPrioridadExtranjera(sigSignoMarca.getIdSignoMarca(),
                prioridadExtranjera,
                dominioService.obtenerNombreCodigoDominio("estado_marca", sigSignoMarca.getEstadoMarca()),
                dominioService.obtenerNombreCodigoDominio("ubicacion", sigSignoMarca.getUbicacion()),
                fechaSistema,
                logTransGuardado.getIdLogTrans(),
                idUsuario);

        //guardar el historial de prioridad exposición
        sighistorialService.generarHistorialPrioridadExposicion(sigSignoMarca.getIdSignoMarca(),
                prioridadExposicion,
                dominioService.obtenerNombreCodigoDominio("estado_marca", sigSignoMarca.getEstadoMarca()),
                dominioService.obtenerNombreCodigoDominio("ubicacion", sigSignoMarca.getUbicacion()),
                fechaSistema,
                logTransGuardado.getIdLogTrans(),
                idUsuario);

        //guardar el historial de oposicion andina
        sighistorialService.generarHistorialOposicionAndina(sigSignoMarca.getIdSignoMarca(),
                oposicion,
                dominioService.obtenerNombreCodigoDominio("estado_marca", sigSignoMarca.getEstadoMarca()),
                dominioService.obtenerNombreCodigoDominio("ubicacion", sigSignoMarca.getUbicacion()),
                fechaSistema,
                logTransGuardado.getIdLogTrans(),
                idUsuario);

        int parametroGuardar = 1, parametroModificar = 2;
        SigSignoMarca sigSignoMarcaGuardar = new SigSignoMarca();

        try {
            sigSignoMarca.setIdLogTrans(logTransGuardado.getIdLogTrans());
            sigSignoMarcaGuardar = sigSignoMarcaService.crudSigSignoMarca(sigSignoMarca, parametroModificar);

            //actualizar solicitante
            sigSolicitanteApoderadoService.modificaListaSolicitanteApoderado(sigSignoMarcaGuardar.getIdSignoMarca(), lstSolicitantes, EnumTipoPersona.SOLICITANTE.getCodigo(), logTransGuardado.getIdLogTrans());

            //actualizar apoderado
            sigSolicitanteApoderadoService.modificaListaSolicitanteApoderado(sigSignoMarcaGuardar.getIdSignoMarca(), lstApoderados, EnumTipoPersona.APODERADO.getCodigo(), logTransGuardado.getIdLogTrans());

            //actualizar tipo signo
            sigTipoSignoService.modificarSigTipoSigno(sigSignoMarcaGuardar.getIdSignoMarca(), lstTipoSignos, logTransGuardado.getIdLogTrans());

            //actualizar direccion notificacion
            sigDireccionDeNotificacionService.crudSigDireccionDeNotificacion(sigDireccion, parametroModificar);

            //actualizar prioridad extranjera
            if (prioridadExtranjera.getIdPrioridadPreferencia() == null) {
                if (!prioridadExtranjera.getNombreNumero().equals("")) {
                    prioridadExtranjera.setIdSignoMarca(sigSignoMarcaGuardar.getIdSignoMarca());
                    prioridadExtranjera.setEstado(EnumEstado.ACTIVO.getCodigo());
                    prioridadExtranjera.setTipoPrioridad(EnumTipoPrioridad.PRIORIDAD.getCodigo());
                    prioridadExtranjera.setTipoInteres(EnumTipoInteres.EXTRANJERA.getCodigo());
                    prioridadExtranjera.setIdLogTrans(logTransGuardado.getIdLogTrans());
                    //se debe realizar la insercion del registro
                    sigPrioridadPreferenciaService.crudSigPrioridadPreferencia(prioridadExtranjera, parametroGuardar);
                }
            } else if (!prioridadExtranjera.getNombreNumero().equals("")) {
                prioridadExtranjera.setIdSignoMarca(sigSignoMarcaGuardar.getIdSignoMarca());
                prioridadExtranjera.setEstado(EnumEstado.ACTIVO.getCodigo());
                prioridadExtranjera.setTipoPrioridad(EnumTipoPrioridad.PRIORIDAD.getCodigo());
                prioridadExtranjera.setTipoInteres(EnumTipoInteres.EXTRANJERA.getCodigo());
                prioridadExtranjera.setIdLogTrans(logTransGuardado.getIdLogTrans());
                //se debe realizar la insercion del registro
                sigPrioridadPreferenciaService.crudSigPrioridadPreferencia(prioridadExtranjera, parametroModificar);
            } else {
                //se debe realizar la actualizacion del registro
                prioridadExtranjera.setEstado(EnumEstado.NO_ACTIVO.getCodigo());
                sigPrioridadPreferenciaService.crudSigPrioridadPreferencia(prioridadExtranjera, parametroModificar);
            }

            //actualizar prioridad exposicion
            if (prioridadExposicion.getIdPrioridadPreferencia() == null) {
                if (!prioridadExposicion.getNombreNumero().equals("")) {
                    prioridadExposicion.setIdSignoMarca(sigSignoMarcaGuardar.getIdSignoMarca());
                    prioridadExposicion.setEstado(EnumEstado.ACTIVO.getCodigo());
                    prioridadExposicion.setTipoPrioridad(EnumTipoPrioridad.PRIORIDAD.getCodigo());
                    prioridadExposicion.setTipoInteres(EnumTipoInteres.EXPOSICION.getCodigo());
                    prioridadExposicion.setIdLogTrans(logTransGuardado.getIdLogTrans());
                    //se debe realizar la insercion del registro
                    sigPrioridadPreferenciaService.crudSigPrioridadPreferencia(prioridadExposicion, parametroGuardar);
                }
            } else if (!prioridadExposicion.getNombreNumero().equals("")) {
                prioridadExposicion.setIdSignoMarca(sigSignoMarcaGuardar.getIdSignoMarca());
                prioridadExposicion.setEstado(EnumEstado.ACTIVO.getCodigo());
                prioridadExposicion.setTipoPrioridad(EnumTipoPrioridad.PRIORIDAD.getCodigo());
                prioridadExposicion.setTipoInteres(EnumTipoInteres.EXPOSICION.getCodigo());
                prioridadExposicion.setIdLogTrans(logTransGuardado.getIdLogTrans());
                //se debe realizar la actualizacion del registro
                sigPrioridadPreferenciaService.crudSigPrioridadPreferencia(prioridadExposicion, parametroModificar);
            } else {
                prioridadExposicion.setEstado(EnumEstado.NO_ACTIVO.getCodigo());
                sigPrioridadPreferenciaService.crudSigPrioridadPreferencia(prioridadExposicion, parametroModificar);
            }

            //actualizar oposicion
            if (oposicion.getIdPrioridadPreferencia() == null) {
                if (!oposicion.getNombreNumero().equals("")) {
                    //se debe realizar la insercion del registro
                    oposicion.setIdSignoMarca(sigSignoMarcaGuardar.getIdSignoMarca());
                    oposicion.setEstado(EnumEstado.ACTIVO.getCodigo());
                    oposicion.setTipoPrioridad(EnumTipoPrioridad.OPOSICION_ANDINA.getCodigo());
                    oposicion.setTipoInteres(EnumTipoInteres.OPOSICION_ANDINA.getCodigo());
                    oposicion.setIdLogTrans(logTransGuardado.getIdLogTrans());
                    //se debe realizar la insercion del registro
                    sigPrioridadPreferenciaService.crudSigPrioridadPreferencia(oposicion, parametroGuardar);
                }
            } else if (!oposicion.getNombreNumero().equals("")) {
                oposicion.setIdSignoMarca(sigSignoMarcaGuardar.getIdSignoMarca());
                oposicion.setEstado(EnumEstado.ACTIVO.getCodigo());
                oposicion.setTipoPrioridad(EnumTipoPrioridad.OPOSICION_ANDINA.getCodigo());
                oposicion.setTipoInteres(EnumTipoInteres.OPOSICION_ANDINA.getCodigo());
                oposicion.setIdLogTrans(logTransGuardado.getIdLogTrans());
                //se debe realizar la actualizacion del registro
                sigPrioridadPreferenciaService.crudSigPrioridadPreferencia(oposicion, parametroModificar);
            } else {
                oposicion.setEstado(EnumEstado.NO_ACTIVO.getCodigo());
                sigPrioridadPreferenciaService.crudSigPrioridadPreferencia(oposicion, parametroModificar);
            }

            sigSignoClaseNizaService.modificaListaSigSignoClaseNiza(sigSignoMarcaGuardar.getIdSignoMarca(), lstSigSignoClaseNiza);

            // Recuperamos la imagen de la base de datos
            ImagenPojo sigImagenBase = imagenPojoService.obtenerImagePojoXSignoMarca(sigSignoMarca.getIdSignoMarca());

            //primero se debe preguntar si tipSigno es 1 y si se cambió a denominativo, si es asi entonces eliminar la imagen
            if (lstTipoSignos.size() == 1 && lstTipoSignos.get(0).getTipoSigno().equals(EnumTipoSigno.DENOMINACION.getCodigo())) {
                if (sigImagenBase.getSigLogoTipo().getIdLogoTipo() != null) {  //existe imagen en la base de datos
                    // generar historial eliminacion de imagen
                    sighistorialService.generarHistorialEliminacionImagen(sigSignoMarca.getIdSignoMarca(),
                            sigImagenBase,
                            dominioService.obtenerNombreCodigoDominio("estado_marca", sigSignoMarca.getEstadoMarca()),
                            dominioService.obtenerNombreCodigoDominio("ubicacion", sigSignoMarca.getUbicacion()),
                            fechaSistema,
                            logTransGuardado.getIdLogTrans(),
                            idUsuario);
                    // eliminar la imagen
                    imagenPojoService.eliminarRegistroImagenPojoXSignoMarca(sigSignoMarcaGuardar.getIdSignoMarca(), logTransGuardado.getIdLogTrans());
                }
            } else if (sigImagenBase.getSigLogoTipo().getIdLogoTipo() != null) {  //existe imagen en la base de datos
                if (!lstImagenPojo.isEmpty()) { // existe imagen en la vista
                    //preguntamos si las longitudes de las imagenes son distintos
                    if (lstImagenPojo.get(0).getSigImagen().getImagen().length != sigImagenBase.getSigImagen().getImagen().length) {

                        lstImagenPojo.get(0).getSigLogoTipo().setNombreArchivo(sigSignoMarca.getSm().toString());
                        lstImagenPojo.get(0).getSigLogoTipo().setIdLogTrans(logTransGuardado.getIdLogTrans());
                        lstImagenPojo.get(0).getSigImagen().setIdLogTrans(logTransGuardado.getIdLogTrans());

                        // generar historial actualizacion de imagen
                        sighistorialService.generarHistorialImagen(sigSignoMarca.getIdSignoMarca(),
                                sigImagenBase,
                                dominioService.obtenerNombreCodigoDominio("estado_marca", sigSignoMarca.getEstadoMarca()),
                                dominioService.obtenerNombreCodigoDominio("ubicacion", sigSignoMarca.getUbicacion()),
                                fechaSistema,
                                logTransGuardado.getIdLogTrans(),
                                idUsuario);

                        // actualizar la imagen
                        imagenPojoService.actualizarRegistroImagenPojoXSignoMarca(lstImagenPojo.get(0));
                    }
                } else {
                    //se debe preguntar si es un solo tipoSigno y si es denominativo
                    if (lstTipoSignos.size() != 1 || !lstTipoSignos.get(0).getTipoSigno().equals(EnumTipoSigno.DENOMINACION.getCodigo())) {

                        // generar historial eliminacion de imagen
                        sighistorialService.generarHistorialEliminacionImagen(sigSignoMarca.getIdSignoMarca(),
                                sigImagenBase,
                                dominioService.obtenerNombreCodigoDominio("estado_marca", sigSignoMarca.getEstadoMarca()),
                                dominioService.obtenerNombreCodigoDominio("ubicacion", sigSignoMarca.getUbicacion()),
                                fechaSistema,
                                logTransGuardado.getIdLogTrans(),
                                idUsuario);

                        // eliminar la imagen
                        imagenPojoService.eliminarRegistroImagenPojoXSignoMarca(sigSignoMarcaGuardar.getIdSignoMarca(), logTransGuardado.getIdLogTrans());
                    }
                }
            } else if (!lstImagenPojo.isEmpty()) { // preguntamos si hay imagen en la vista
                if (!lstImagenPojo.get(0).getSigLogoTipo().getNombreArchivo().equals("")) { // preguntamos si el nombre archivo no es cadena vacia

                    lstImagenPojo.get(0).getSigLogoTipo().setNombreArchivo(sigSignoMarca.getSm().toString());
                    lstImagenPojo.get(0).getSigLogoTipo().setIdSignoMarca(sigSignoMarca.getIdSignoMarca());
                    lstImagenPojo.get(0).getSigLogoTipo().setIdLogTrans(logTransGuardado.getIdLogTrans());
                    lstImagenPojo.get(0).getSigImagen().setIdLogTrans(logTransGuardado.getIdLogTrans());

                    // generar historial de registro imagen
                    sighistorialService.generarHistorialImagen(sigSignoMarca.getIdSignoMarca(),
                            lstImagenPojo.get(0),
                            dominioService.obtenerNombreCodigoDominio("estado_marca", sigSignoMarca.getEstadoMarca()),
                            dominioService.obtenerNombreCodigoDominio("ubicacion", sigSignoMarca.getUbicacion()),
                            fechaSistema,
                            logTransGuardado.getIdLogTrans(),
                            idUsuario);

                    // registar la imagen
                    imagenPojoService.registrarImagePojo(lstImagenPojo.get(0));
                }
            }
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional
    @Override
    public void actualizarSubsanacionSignoMarcaPI100(SigSignoMarca sigSignoMarca, List<ImagenPojo> lstImagenPojo, List<SigTipoSigno> lstTipoSignos,
            SigDireccionDeNotificacion sigDireccion, Long idUsuario, FormularioPI100 formularioPI100) throws Exception {
        try {
            // generar la fecha del sistema y logTrans
            Date fechaSistema = comunService.obtenerFechaHoraServidor(1L);
            LogTrans logTransGuardado = logTransService.crudLogTrans(new LogTrans(idUsuario, fechaSistema), 1);
            String estado_marca = dominioService.obtenerNombreCodigoDominio("estado_marca", sigSignoMarca.getEstadoMarca());
            String ubicacion_marca = dominioService.obtenerNombreCodigoDominio("ubicacion", sigSignoMarca.getUbicacion());

            // GUARDAR DATOS HISTORICOS
            sighistorialService.guardarSigHistorialSubsanacionPI100(sigSignoMarca,
                    fechaSistema,
                    estado_marca,
                    ubicacion_marca,
                    logTransGuardado.getIdLogTrans(),
                    idUsuario,
                    formularioPI100);

            // REALIZAR ACTUALIZACION DE DATOS
            int parametroModificar = 2;

            sigSignoMarca.setIdLogTrans(logTransGuardado.getIdLogTrans());
            sigSignoMarcaService.crudSigSignoMarcaExternoPI100(sigSignoMarca, formularioPI100, parametroModificar);

            // Se realiza la gestion de la imagen
            ImagenPojo sigImagenBase = new ImagenPojo();
            if (!lstImagenPojo.isEmpty()) {
                sigImagenBase = imagenPojoService.obtenerImagePojoXSignoMarca(sigSignoMarca.getIdSignoMarca());
            } else {
                sigImagenBase = null;
            }
            List<Logotipos> logotiposExterno = formularioPI100.getLogotipos();

            if (lstTipoSignos.size() == 1 && lstTipoSignos.get(0).getTipoSigno().equals(EnumTipoSigno.DENOMINACION.getCodigo())) {
                if (sigImagenBase != null) {  //existe imagen en la base de datos
                    // generar historial eliminacion de imagen
                    sighistorialService.generarHistorialEliminacionImagen(sigSignoMarca.getIdSignoMarca(),
                            sigImagenBase,
                            estado_marca,
                            ubicacion_marca,
                            fechaSistema,
                            logTransGuardado.getIdLogTrans(),
                            idUsuario);
                    // eliminar la imagen
                    imagenPojoService.eliminarRegistroImagenPojoXSignoMarca(sigSignoMarca.getIdSignoMarca(), logTransGuardado.getIdLogTrans());
                }
            } else if (sigImagenBase != null) {
                if (!logotiposExterno.isEmpty()) {
                    // modificar
                    if (sigImagenBase.getSigImagen().getImagen().length != logotiposExterno.get(0).getImagen().length) {

                        sigImagenBase.getSigLogoTipo().setNombreArchivo(sigSignoMarca.getSm().toString());
                        sigImagenBase.getSigLogoTipo().setIdLogTrans(logTransGuardado.getIdLogTrans());
                        sigImagenBase.getSigImagen().setIdLogTrans(logTransGuardado.getIdLogTrans());

                        // generar historial actualizacion de imagen
                        sighistorialService.generarHistorialImagen(sigSignoMarca.getIdSignoMarca(),
                                sigImagenBase,
                                estado_marca,
                                ubicacion_marca,
                                fechaSistema,
                                logTransGuardado.getIdLogTrans(),
                                idUsuario);

                        // actualizar la imagen
                        sigImagenBase.getSigImagen().setImagen(logotiposExterno.get(0).getImagen());
                        imagenPojoService.actualizarRegistroImagenPojoXSignoMarca(sigImagenBase);
                    }
                } else {
                    // eliminar
                    // generar historial eliminacion de imagen
                    sighistorialService.generarHistorialEliminacionImagen(sigSignoMarca.getIdSignoMarca(),
                            sigImagenBase,
                            estado_marca,
                            ubicacion_marca,
                            fechaSistema,
                            logTransGuardado.getIdLogTrans(),
                            idUsuario);
                    // eliminar la imagen
                    imagenPojoService.eliminarRegistroImagenPojoXSignoMarca(sigSignoMarca.getIdSignoMarca(), logTransGuardado.getIdLogTrans());
                }
            } else {
                //adicionar
                // generar historial adicion de imagen
                SigLogoTipo sigLogoTipo = new SigLogoTipo();
                SigImagen sigImagen = new SigImagen();
                sigLogoTipo.setIdSignoMarca(sigSignoMarca.getIdSignoMarca());
                sigLogoTipo.setIdLogTrans(logTransGuardado.getIdLogTrans());
                sigLogoTipo.setPrincipal(Boolean.TRUE);
                sigLogoTipo.setNombreArchivo(sigSignoMarca.getSm().toString());
                sigLogoTipo.setEstado(EnumEstado.ACTIVO.getCodigo());

                sigImagen.setIdLogTrans(logTransGuardado.getIdLogTrans());
                sigImagen.setImagen(logotiposExterno.get(0).getImagen());

                ImagenPojo imagenNuevo = new ImagenPojo();
                imagenNuevo.setSigLogoTipo(sigLogoTipo);
                imagenNuevo.setSigImagen(sigImagen);

                sighistorialService.generarHistorialImagen(sigSignoMarca.getIdSignoMarca(),
                        imagenNuevo,
                        estado_marca,
                        ubicacion_marca,
                        fechaSistema,
                        logTransGuardado.getIdLogTrans(),
                        idUsuario);

                imagenPojoService.registrarImagePojo(imagenNuevo);
            }

            // actualizar tipo signo
            sigTipoSignoService.modificarSigTipoSignoSubsanacion(sigSignoMarca.getIdSignoMarca(),
                    lstTipoSignos,
                    formularioPI100.getSmTipoSignos(),
                    logTransGuardado.getIdLogTrans());

            // actualizar clase niza
            sigSignoClaseNizaService.modificarListaSigSignoClaseNizaSubsanacion(sigSignoMarca.getIdSignoMarca(),
                    logTransGuardado.getIdLogTrans(),
                    formularioPI100.getSmSignoClaseNizases());

            // actualizar solicitante
            sigSolicitanteApoderadoService.modificarListaSolicitanteSubsanacion(sigSignoMarca.getIdSignoMarca(),
                    formularioPI100.getSolicitantes(),
                    EnumTipoPersona.SOLICITANTE.getCodigo(),
                    logTransGuardado.getIdLogTrans());

            // actualizar apoderado
            sigSolicitanteApoderadoService.modificarListaApoderadoSubsanacion(sigSignoMarca.getIdSignoMarca(),
                    formularioPI100.getApoderados(),
                    EnumTipoPersona.APODERADO.getCodigo(),
                    logTransGuardado.getIdLogTrans());

            // actualizar direccion notificacion
            sigDireccionDeNotificacionService.crudSigDireccionDeNotificacionSubsanacion(sigDireccion,
                    formularioPI100.getDirecciones(),
                    parametroModificar);

            // actualizar prioridades
            sigPrioridadPreferenciaService.modificarPrioridadPreferenciaSubsanacion(sigSignoMarca.getIdSignoMarca(),
                    formularioPI100.getPrioridades(), logTransGuardado.getIdLogTrans());

            //Actualizar el estado del formulario
            formularioService.actualizarRegistroFormularioSubsanacion(formularioPI100.getFormularios().getId());

        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional
    @Override
    public void actualizarSubsanacionSignoMarcaPI101(SigSignoMarca sigSignoMarca, List<ImagenPojo> lstImagenPojo, List<SigTipoSigno> lstTipoSignos, SigDireccionDeNotificacion sigDireccion,
            Long idUsuario, FormularioPI101 formularioPI101) throws Exception {
        try {
            // generar la fecha del sistema y logTrans
            Date fechaSistema = comunService.obtenerFechaHoraServidor(1L);
            LogTrans logTransGuardado = logTransService.crudLogTrans(new LogTrans(idUsuario, fechaSistema), 1);
            String estado_marca = dominioService.obtenerNombreCodigoDominio("estado_marca", sigSignoMarca.getEstadoMarca());
            String ubicacion_marca = dominioService.obtenerNombreCodigoDominio("ubicacion", sigSignoMarca.getUbicacion());

            // GUARDAR DATOS HISTORICOS
            sighistorialService.guardarSigHistorialSubsanacionPI101(sigSignoMarca,
                    fechaSistema,
                    estado_marca,
                    ubicacion_marca,
                    logTransGuardado.getIdLogTrans(),
                    idUsuario,
                    formularioPI101);

            // REALIZAR ACTUALIZACION DE DATOS
            int parametroModificar = 2;

            sigSignoMarca.setIdLogTrans(logTransGuardado.getIdLogTrans());
            sigSignoMarcaService.crudSigSignoMarcaExternoPI101(sigSignoMarca, formularioPI101, parametroModificar);

            // Se realiza la gestion de la imagen
            ImagenPojo sigImagenBase = new ImagenPojo();
            if (!lstImagenPojo.isEmpty()) {
                sigImagenBase = imagenPojoService.obtenerImagePojoXSignoMarca(sigSignoMarca.getIdSignoMarca());
            } else {
                sigImagenBase = null;
            }
            List<Logotipos> logotiposExterno = formularioPI101.getLogotipos();

            if (lstTipoSignos.size() == 1 && lstTipoSignos.get(0).getTipoSigno().equals(EnumTipoSigno.DENOMINACION.getCodigo())) {
                if (sigImagenBase != null) {  //existe imagen en la base de datos
                    // generar historial eliminacion de imagen
                    sighistorialService.generarHistorialEliminacionImagen(sigSignoMarca.getIdSignoMarca(),
                            sigImagenBase,
                            estado_marca,
                            ubicacion_marca,
                            fechaSistema,
                            logTransGuardado.getIdLogTrans(),
                            idUsuario);
                    // eliminar la imagen
                    imagenPojoService.eliminarRegistroImagenPojoXSignoMarca(sigSignoMarca.getIdSignoMarca(), logTransGuardado.getIdLogTrans());
                }
            } else if (sigImagenBase != null) {
                if (!logotiposExterno.isEmpty()) {
                    // modificar
                    if (sigImagenBase.getSigImagen().getImagen().length != logotiposExterno.get(0).getImagen().length) {

                        sigImagenBase.getSigLogoTipo().setNombreArchivo(sigSignoMarca.getSm().toString());
                        sigImagenBase.getSigLogoTipo().setIdLogTrans(logTransGuardado.getIdLogTrans());
                        sigImagenBase.getSigImagen().setIdLogTrans(logTransGuardado.getIdLogTrans());

                        // generar historial actualizacion de imagen
                        sighistorialService.generarHistorialImagen(sigSignoMarca.getIdSignoMarca(),
                                sigImagenBase,
                                estado_marca,
                                ubicacion_marca,
                                fechaSistema,
                                logTransGuardado.getIdLogTrans(),
                                idUsuario);

                        // actualizar la imagen
                        sigImagenBase.getSigImagen().setImagen(logotiposExterno.get(0).getImagen());
                        imagenPojoService.actualizarRegistroImagenPojoXSignoMarca(sigImagenBase);
                    }
                } else {
                    // eliminar
                    // generar historial eliminacion de imagen
                    sighistorialService.generarHistorialEliminacionImagen(sigSignoMarca.getIdSignoMarca(),
                            sigImagenBase,
                            estado_marca,
                            ubicacion_marca,
                            fechaSistema,
                            logTransGuardado.getIdLogTrans(),
                            idUsuario);
                    // eliminar la imagen
                    imagenPojoService.eliminarRegistroImagenPojoXSignoMarca(sigSignoMarca.getIdSignoMarca(), logTransGuardado.getIdLogTrans());
                }
            } else {
                //adicionar
                // generar historial adicion de imagen
                SigLogoTipo sigLogoTipo = new SigLogoTipo();
                SigImagen sigImagen = new SigImagen();
                sigLogoTipo.setIdSignoMarca(sigSignoMarca.getIdSignoMarca());
                sigLogoTipo.setIdLogTrans(logTransGuardado.getIdLogTrans());
                sigLogoTipo.setPrincipal(Boolean.TRUE);
                sigLogoTipo.setNombreArchivo(sigSignoMarca.getSm().toString());
                sigLogoTipo.setEstado(EnumEstado.ACTIVO.getCodigo());

                sigImagen.setIdLogTrans(logTransGuardado.getIdLogTrans());
                sigImagen.setImagen(logotiposExterno.get(0).getImagen());

                ImagenPojo imagenNuevo = new ImagenPojo();
                imagenNuevo.setSigLogoTipo(sigLogoTipo);
                imagenNuevo.setSigImagen(sigImagen);

                sighistorialService.generarHistorialImagen(sigSignoMarca.getIdSignoMarca(),
                        imagenNuevo,
                        estado_marca,
                        ubicacion_marca,
                        fechaSistema,
                        logTransGuardado.getIdLogTrans(),
                        idUsuario);

                imagenPojoService.registrarImagePojo(imagenNuevo);
            }

            // actualizar tipo signo
            sigTipoSignoService.modificarSigTipoSignoSubsanacion(sigSignoMarca.getIdSignoMarca(),
                    lstTipoSignos,
                    formularioPI101.getSmTipoSignos(),
                    logTransGuardado.getIdLogTrans());

            // actualizar clase niza
            sigSignoClaseNizaService.modificarListaSigSignoClaseNizaSubsanacion(sigSignoMarca.getIdSignoMarca(),
                    logTransGuardado.getIdLogTrans(),
                    formularioPI101.getSmSignoClaseNizases());

            // actualizar solicitante
            sigSolicitanteApoderadoService.modificarListaSolicitanteSubsanacion(sigSignoMarca.getIdSignoMarca(),
                    formularioPI101.getSolicitantes(),
                    EnumTipoPersona.SOLICITANTE.getCodigo(),
                    logTransGuardado.getIdLogTrans());

            // actualizar apoderado
            sigSolicitanteApoderadoService.modificarListaApoderadoSubsanacion(sigSignoMarca.getIdSignoMarca(),
                    formularioPI101.getApoderados(),
                    EnumTipoPersona.APODERADO.getCodigo(),
                    logTransGuardado.getIdLogTrans());

            // actualizar direccion notificacion
            sigDireccionDeNotificacionService.crudSigDireccionDeNotificacionSubsanacion(sigDireccion,
                    formularioPI101.getDirecciones(),
                    parametroModificar);

            // actualizar prioridades
            sigPrioridadPreferenciaService.modificarPrioridadPreferenciaSubsanacion(sigSignoMarca.getIdSignoMarca(),
                    formularioPI101.getPrioridades(), logTransGuardado.getIdLogTrans());

            //Actualizar el estado del formulario
            formularioService.actualizarRegistroFormularioSubsanacion(formularioPI101.getFormularios().getId());

        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional
    @Override
    public void actualizarSubsanacionSignoMarcaPI102(SigSignoMarca sigSignoMarca, List<ImagenPojo> lstImagenPojo, List<SigTipoSigno> lstTipoSignos, SigDireccionDeNotificacion sigDireccion,
            Long idUsuario, FormularioPI102 formularioPI102) throws Exception {
        try {
            // generar la fecha del sistema y logTrans
            Date fechaSistema = comunService.obtenerFechaHoraServidor(1L);
            LogTrans logTransGuardado = logTransService.crudLogTrans(new LogTrans(idUsuario, fechaSistema), 1);
            String estado_marca = dominioService.obtenerNombreCodigoDominio("estado_marca", sigSignoMarca.getEstadoMarca());
            String ubicacion_marca = dominioService.obtenerNombreCodigoDominio("ubicacion", sigSignoMarca.getUbicacion());

            // GUARDAR DATOS HISTORICOS
            sighistorialService.guardarSigHistorialSubsanacionPI102(sigSignoMarca,
                    fechaSistema,
                    estado_marca,
                    ubicacion_marca,
                    logTransGuardado.getIdLogTrans(),
                    idUsuario,
                    formularioPI102);

            // REALIZAR ACTUALIZACION DE DATOS
            int parametroModificar = 2;

            sigSignoMarca.setIdLogTrans(logTransGuardado.getIdLogTrans());
            sigSignoMarcaService.crudSigSignoMarcaExternoPI102(sigSignoMarca, formularioPI102, parametroModificar);

            // Se realiza la gestion de la imagen
            ImagenPojo sigImagenBase = new ImagenPojo();
            if (!lstImagenPojo.isEmpty()) {
                sigImagenBase = imagenPojoService.obtenerImagePojoXSignoMarca(sigSignoMarca.getIdSignoMarca());
            } else {
                sigImagenBase = null;
            }
            List<Logotipos> logotiposExterno = formularioPI102.getLogotipos();

            if (lstTipoSignos.size() == 1 && lstTipoSignos.get(0).getTipoSigno().equals(EnumTipoSigno.DENOMINACION.getCodigo())) {
                if (sigImagenBase != null) {  //existe imagen en la base de datos
                    // generar historial eliminacion de imagen
                    sighistorialService.generarHistorialEliminacionImagen(sigSignoMarca.getIdSignoMarca(),
                            sigImagenBase,
                            estado_marca,
                            ubicacion_marca,
                            fechaSistema,
                            logTransGuardado.getIdLogTrans(),
                            idUsuario);
                    // eliminar la imagen
                    imagenPojoService.eliminarRegistroImagenPojoXSignoMarca(sigSignoMarca.getIdSignoMarca(), logTransGuardado.getIdLogTrans());
                }
            } else if (sigImagenBase != null) {
                if (!logotiposExterno.isEmpty()) {
                    // modificar
                    if (sigImagenBase.getSigImagen().getImagen().length != logotiposExterno.get(0).getImagen().length) {

                        sigImagenBase.getSigLogoTipo().setNombreArchivo(sigSignoMarca.getSm().toString());
                        sigImagenBase.getSigLogoTipo().setIdLogTrans(logTransGuardado.getIdLogTrans());
                        sigImagenBase.getSigImagen().setIdLogTrans(logTransGuardado.getIdLogTrans());

                        // generar historial actualizacion de imagen
                        sighistorialService.generarHistorialImagen(sigSignoMarca.getIdSignoMarca(),
                                sigImagenBase,
                                estado_marca,
                                ubicacion_marca,
                                fechaSistema,
                                logTransGuardado.getIdLogTrans(),
                                idUsuario);

                        // actualizar la imagen
                        sigImagenBase.getSigImagen().setImagen(logotiposExterno.get(0).getImagen());
                        imagenPojoService.actualizarRegistroImagenPojoXSignoMarca(sigImagenBase);
                    }
                } else {
                    // eliminar
                    // generar historial eliminacion de imagen
                    sighistorialService.generarHistorialEliminacionImagen(sigSignoMarca.getIdSignoMarca(),
                            sigImagenBase,
                            estado_marca,
                            ubicacion_marca,
                            fechaSistema,
                            logTransGuardado.getIdLogTrans(),
                            idUsuario);
                    // eliminar la imagen
                    imagenPojoService.eliminarRegistroImagenPojoXSignoMarca(sigSignoMarca.getIdSignoMarca(), logTransGuardado.getIdLogTrans());
                }
            } else {
                //adicionar
                // generar historial adicion de imagen
                SigLogoTipo sigLogoTipo = new SigLogoTipo();
                SigImagen sigImagen = new SigImagen();
                sigLogoTipo.setIdSignoMarca(sigSignoMarca.getIdSignoMarca());
                sigLogoTipo.setIdLogTrans(logTransGuardado.getIdLogTrans());
                sigLogoTipo.setPrincipal(Boolean.TRUE);
                sigLogoTipo.setNombreArchivo(sigSignoMarca.getSm().toString());
                sigLogoTipo.setEstado(EnumEstado.ACTIVO.getCodigo());

                sigImagen.setIdLogTrans(logTransGuardado.getIdLogTrans());
                sigImagen.setImagen(logotiposExterno.get(0).getImagen());

                ImagenPojo imagenNuevo = new ImagenPojo();
                imagenNuevo.setSigLogoTipo(sigLogoTipo);
                imagenNuevo.setSigImagen(sigImagen);

                sighistorialService.generarHistorialImagen(sigSignoMarca.getIdSignoMarca(),
                        imagenNuevo,
                        estado_marca,
                        ubicacion_marca,
                        fechaSistema,
                        logTransGuardado.getIdLogTrans(),
                        idUsuario);

                imagenPojoService.registrarImagePojo(imagenNuevo);
            }

            // actualizar tipo signo
            sigTipoSignoService.modificarSigTipoSignoSubsanacion(sigSignoMarca.getIdSignoMarca(),
                    lstTipoSignos,
                    formularioPI102.getSmTipoSignos(),
                    logTransGuardado.getIdLogTrans());

            // actualizar clase niza
            sigSignoClaseNizaService.modificarListaSigSignoClaseNizaSubsanacion(sigSignoMarca.getIdSignoMarca(),
                    logTransGuardado.getIdLogTrans(),
                    formularioPI102.getSmSignoClaseNizases());

            // actualizar solicitante
            sigSolicitanteApoderadoService.modificarListaSolicitanteSubsanacion(sigSignoMarca.getIdSignoMarca(),
                    formularioPI102.getSolicitantes(),
                    EnumTipoPersona.SOLICITANTE.getCodigo(),
                    logTransGuardado.getIdLogTrans());

            // actualizar apoderado
            sigSolicitanteApoderadoService.modificarListaApoderadoSubsanacion(sigSignoMarca.getIdSignoMarca(),
                    formularioPI102.getApoderados(),
                    EnumTipoPersona.APODERADO.getCodigo(),
                    logTransGuardado.getIdLogTrans());

            // actualizar direccion notificacion
            sigDireccionDeNotificacionService.crudSigDireccionDeNotificacionSubsanacion(sigDireccion,
                    formularioPI102.getDirecciones(),
                    parametroModificar);

            // actualizar prioridades
            sigPrioridadPreferenciaService.modificarPrioridadPreferenciaSubsanacion(sigSignoMarca.getIdSignoMarca(),
                    formularioPI102.getPrioridades(), logTransGuardado.getIdLogTrans());

            //Actualizar el estado del formulario
            formularioService.actualizarRegistroFormularioSubsanacion(formularioPI102.getFormularios().getId());

        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public SigSignoMarca obtenerSiguienteRegistroSigSignoMarca(Long idSignoMarca) throws Exception {
        try {
            SigSignoMarca sigSignoMarca;

            //obtener el registro de acuerdo a su valor directamente
            String SQL = "select "
                    + "	* "
                    + "from sigsignomarca "
                    + "where "
                    + " idsignomarca > ? "
                    + " and estado = 'AC' "
                    + " order by 1 asc"
                    + " limit 1 "
                    + "";

            if (!jdbcTemplate.query(SQL, new SigSignoMarcaMapper(), idSignoMarca).isEmpty()) {
                return jdbcTemplate.query(SQL, new SigSignoMarcaMapper(), idSignoMarca).get(0);

            } else {
                //en caso que no se encuentre se obtiene el id anterior en estado AC
                return new SigSignoMarca();

            }

        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public SigSignoMarca obtenerAnteriorRegistroSigSignoMarca(Long idSignoMarca) throws Exception {
        try {
            SigSignoMarca sigSignoMarca;

            //obtener el registro de acuerdo a su valor directamente
            String SQL = "select "
                    + "	* "
                    + "from sigsignomarca "
                    + "where "
                    + " idsignomarca < ? "
                    + " and estado = 'AC' "
                    + " order by 1 desc"
                    + " limit 1 "
                    + "";

            if (!jdbcTemplate.query(SQL, new SigSignoMarcaMapper(), idSignoMarca).isEmpty()) {
                return jdbcTemplate.query(SQL, new SigSignoMarcaMapper(), idSignoMarca).get(0);

            } else {
                //en caso que no se encuentre se obtiene el id anterior en estado AC
                return new SigSignoMarca();

            }

        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public SigSignoMarca obtenerSiguienteRegistroSigSignoMarca(Integer numero, Integer gestion, Integer extensionMarca) throws Exception {

        try {

            //variables auxiliares
            Integer gestionAuxiliar = gestion;        // variable gestion
            Integer numeroAuxiliar = numero;

            //obtener el registro siguiente, se pregunta primero por su extension de acuerdo a su valor directamente
            String SiguienteExtensionNumeroGestion = "select "
                    + " * "
                    + " from sigsignomarca "
                    + " where "
                    + " numero = ? "
                    + " and gestion = ?"
                    + " and extension_marca > ? "
                    + " and estado = 'AC' "
                    + " order by numero, extension_marca, gestion"
                    + " limit 1 "
                    + " ";

            //preguntar principal si existe o no el registro
            if (!jdbcTemplate.query(SiguienteExtensionNumeroGestion, new SigSignoMarcaMapper(), numero, gestion, extensionMarca).isEmpty()) {
                return jdbcTemplate.query(SiguienteExtensionNumeroGestion, new SigSignoMarcaMapper(), numero, gestion, extensionMarca).get(0);

            } else {
                //preguntar por el numero siguiente
                //obtener el registro de acuerdo a su valor directamente
                String SiguienteNumeroGestionExtension = "select "
                        + " * "
                        + " from sigsignomarca "
                        + " where "
                        + " numero > ? "
                        + " and gestion = ?"
                        + " and estado = 'AC' "
                        + " order by numero, extension_marca, gestion"
                        + " limit 1 "
                        + " ";
                if (!jdbcTemplate.query(SiguienteNumeroGestionExtension, new SigSignoMarcaMapper(), numero, gestion).isEmpty()) {
                    return jdbcTemplate.query(SiguienteNumeroGestionExtension, new SigSignoMarcaMapper(), numero, gestion).get(0);
                } else {

                    int sw = 0;

                    do {

                        gestionAuxiliar++;

                        //armar consulta para obtener el siguiente elemento
                        String SQLMinimoSiguienteGestion = "select "
                                + " * "
                                + "from sigsignomarca "
                                + "where "
                                + " numero = ( "
                                + "          select min(numero) "
                                + "          from sigsignomarca "
                                + "          where gestion = ? "
                                + "             and estado = 'AC' "
                                + "          )"
                                + " and gestion = ?"
                                + " and estado = 'AC' "
                                + " order by numero, extension_marca, gestion"
                                + " limit 1 "
                                + "";
                        //preguntar principal si existe o no el registro
                        if (!jdbcTemplate.query(SQLMinimoSiguienteGestion, new SigSignoMarcaMapper(), gestionAuxiliar, gestionAuxiliar).isEmpty()) {
                            return jdbcTemplate.query(SQLMinimoSiguienteGestion, new SigSignoMarcaMapper(), gestionAuxiliar, gestionAuxiliar).get(0);
                        } else {
                            //preguntar si existe una gestion mayor a la que se esta consultando en este momento
                            String SQLSiguienteGestionValida = "select "
                                    + " * "
                                    + "from sigsignomarca "
                                    + "where "
                                    + " gestion >= ?"
                                    + " and estado = 'AC' "
                                    + " order by numero, extension_marca, gestion"
                                    + " limit 1 "
                                    + "";
                            //preguntar principal si existe o no el registro
                            if (jdbcTemplate.query(SQLSiguienteGestionValida, new SigSignoMarcaMapper(), gestionAuxiliar).isEmpty()) {
                                return new SigSignoMarca();
                            }

                        }

                    } while (sw == 1);

                    return new SigSignoMarca();

                }

            }

        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public SigSignoMarca obtenerAnteriorRegistroSigSignoMarca(Integer numero, Integer gestion, Integer extensionMarca) throws Exception {
        try {
            //variables auxiliares
            Integer gestionAuxiliar = gestion;        // variable gestion

            //obtener el registro siguiente, se pregunta primero por su extension de acuerdo a su valor directamente
            String AnteriorExtensionNumeroGestion = "select "
                    + " * "
                    + " from sigsignomarca "
                    + " where "
                    + " numero = ? "
                    + " and gestion = ?"
                    + " and extension_marca < ? "
                    + " and estado = 'AC' "
                    + " order by numero desc, extension_marca desc, gestion desc"
                    + " limit 1 "
                    + " ";

            if (!jdbcTemplate.query(AnteriorExtensionNumeroGestion, new SigSignoMarcaMapper(), numero, gestion, extensionMarca).isEmpty()) {
                return jdbcTemplate.query(AnteriorExtensionNumeroGestion, new SigSignoMarcaMapper(), numero, gestion, extensionMarca).get(0);
            } else {
                int sw = 0;

                do {

                    //armar consulta para obtener el siguiente elemento
                    String SQLMinimoAnteriorGestion = "select "
                            + " * "
                            + "from sigsignomarca "
                            + "where "
                            + " numero < ?"
                            + " and gestion = ?"
                            + " and estado = 'AC' "
                            + " order by numero desc, extension_marca desc, gestion desc"
                            + " limit 1 "
                            + "";
                    //preguntar principal si existe o no el registro
                    if (!jdbcTemplate.query(SQLMinimoAnteriorGestion, new SigSignoMarcaMapper(), numero, gestionAuxiliar).isEmpty()) {
                        return jdbcTemplate.query(SQLMinimoAnteriorGestion, new SigSignoMarcaMapper(), numero, gestionAuxiliar).get(0);
                    } else {
                        //preguntar si existe una gestion menor a la que se esta consultando en este momento
                        String SQLSiguienteGestionValida = ""
                                + " select * "
                                + "from sigsignomarca "
                                + "where "
                                + " gestion = ("
                                + "     select gestion"
                                + "             from sigsignomarca"
                                + "             where gestion < ?"
                                + "             and estado = 'AC'      "
                                + "             order by 1 desc"
                                + "             limit 1 "
                                + "           )"
                                + " and estado = 'AC' "
                                + " order by numero desc, extension_marca desc, gestion desc"
                                + " limit 1 "
                                + "";
                        //preguntar principal si existe o no el registro
                        if (!jdbcTemplate.query(SQLSiguienteGestionValida, new SigSignoMarcaMapper(), gestionAuxiliar).isEmpty()) {
                            return jdbcTemplate.query(SQLSiguienteGestionValida, new SigSignoMarcaMapper(), gestionAuxiliar).get(0);

                        } else {
                            return new SigSignoMarca();
                        }

                    }

                } while (sw == 1);
            }
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public SigSignoMarca obtenerAnteriorRegistroNumeroPublicacionSigSignoMarca(Long idSignoMarca, Long numeroPublicacion) throws Exception {
        try {
            //obtener el registro anterior de acuerdo al numero de publicacion
            String AnteriorMismoNumeroPublicacionIdSignoMarca = "select "
                    + " * "
                    + " from sigsignomarca "
                    + " where "
                    + " numero_publicacion = ? "
                    + " and estado = 'AC' "
                    + " and idsignomarca < ? "
                    + " order by idsignomarca desc "
                    + " limit 1 "
                    + " ";
            if (!jdbcTemplate.query(AnteriorMismoNumeroPublicacionIdSignoMarca, new SigSignoMarcaMapper(), numeroPublicacion, idSignoMarca).isEmpty()) {
                return jdbcTemplate.query(AnteriorMismoNumeroPublicacionIdSignoMarca, new SigSignoMarcaMapper(), numeroPublicacion, idSignoMarca).get(0);
            } else {
                //como no existe un numero menor en el conjunt ode numero_publicacion, nos saltamos al anterior numero_publicacion
                String AnteriorMismoNumeroPublicacion = "select "
                        + " * "
                        + " from sigsignomarca "
                        + " where "
                        + " numero_publicacion < ? "
                        + " and estado = 'AC' "
                        + " and numero_publicacion <> 0 "
                        + " and numero_publicacion is not null "
                        + " and numero_publicacion <> -1 "
                        + " order by numero_publicacion desc, idsignomarca desc "
                        + " limit 1 "
                        + " ";
                if (!jdbcTemplate.query(AnteriorMismoNumeroPublicacion, new SigSignoMarcaMapper(), numeroPublicacion).isEmpty()) {
                    return jdbcTemplate.query(AnteriorMismoNumeroPublicacion, new SigSignoMarcaMapper(), numeroPublicacion).get(0);
                } else {
                    return new SigSignoMarca();
                }

            }
        } catch (Exception e) {

            throw e;
        }

    }

    @Override
    public SigSignoMarca obtenerSiguienteRegistroNumeroPublicacionSigSignoMarca(Long idSignoMarca, Long numeroPublicacion) throws Exception {
        try {
            //obtener el registro anterior de acuerdo al numero de publicacion
            String SiguienteMismoNumeroPublicacionIdSignoMarca = "select "
                    + " * "
                    + " from sigsignomarca "
                    + " where "
                    + " numero_publicacion = ? "
                    + " and estado = 'AC' "
                    + " and idsignomarca > ? "
                    + " order by idsignomarca desc "
                    + " limit 1 "
                    + " ";
            if (!jdbcTemplate.query(SiguienteMismoNumeroPublicacionIdSignoMarca, new SigSignoMarcaMapper(), numeroPublicacion, idSignoMarca).isEmpty()) {
                return jdbcTemplate.query(SiguienteMismoNumeroPublicacionIdSignoMarca, new SigSignoMarcaMapper(), numeroPublicacion, idSignoMarca).get(0);
            } else {
                //como no existe un numero menor en el conjunt ode numero_publicacion, nos saltamos al anterior numero_publicacion
                String SiguienteMismoNumeroPublicacion = "select "
                        + " * "
                        + " from sigsignomarca "
                        + " where "
                        + " numero_publicacion > ? "
                        + " and estado = 'AC' "
                        + " and numero_publicacion <> 0 "
                        + " and numero_publicacion is not null "
                        + " and numero_publicacion <> -1 "
                        + " order by numero_publicacion asc, idsignomarca asc "
                        + " limit 1 "
                        + " ";
                if (!jdbcTemplate.query(SiguienteMismoNumeroPublicacion, new SigSignoMarcaMapper(), numeroPublicacion).isEmpty()) {
                    return jdbcTemplate.query(SiguienteMismoNumeroPublicacion, new SigSignoMarcaMapper(), numeroPublicacion).get(0);
                } else {
                    return new SigSignoMarca();
                }

            }
        } catch (Exception e) {

            throw e;
        }
    }

    @Override
    public SigSignoMarca obtenerAnteriorRegistroNumeroRegistroSigSignoMarca(Long idSignoMarca, Long numeroRegistro, String serieRegistro) throws Exception {
        try {
            //obtener el registro anterior de acuerdo al numero de publicacion
            String AnteriorMismoNumeroRegistroIdSignoMarca = "select "
                    + " * "
                    + " from sigsignomarca "
                    + " where "
                    + " numero_registro = ? "
                    + " and serie_registro = ? "
                    + " and estado = 'AC' "
                    + " and idsignomarca < ? "
                    + " order by idsignomarca desc "
                    + " limit 1 "
                    + " ";
            if (!jdbcTemplate.query(AnteriorMismoNumeroRegistroIdSignoMarca, new SigSignoMarcaMapper(), numeroRegistro, serieRegistro, idSignoMarca).isEmpty()) {
                return jdbcTemplate.query(AnteriorMismoNumeroRegistroIdSignoMarca, new SigSignoMarcaMapper(), numeroRegistro, serieRegistro, idSignoMarca).get(0);
            } else {
                //como no existe un numero menor en el conjunto de numero_publicacion, nos saltamos al anterior numero_publicacion
                String AnteriorNumeroRegistro = "select "
                        + " * "
                        + " from sigsignomarca "
                        + " where "
                        + " numero_registro < ? "
                        + " and estado = 'AC' "
                        + " and numero_registro <> 0 "
                        + " and numero_registro is not null "
                        //+ " and numero_registro <> -1 "
                        + " order by numero_registro desc, idsignomarca desc "
                        + " limit 1 "
                        + " ";
                if (!jdbcTemplate.query(AnteriorNumeroRegistro, new SigSignoMarcaMapper(), numeroRegistro).isEmpty()) {
                    return jdbcTemplate.query(AnteriorNumeroRegistro, new SigSignoMarcaMapper(), numeroRegistro).get(0);
                } else {
                    return new SigSignoMarca();
                }

            }
        } catch (Exception e) {

            throw e;
        }
    }

    @Override
    public SigSignoMarca obtenerSiguienteRegistroNumeroRegistroSigSignoMarca(Long idSignoMarca, Long numeroRegistro, String serieRegistro) throws Exception {
        try {
            //obtener el registro anterior de acuerdo al numero de publicacion
            String SiguienteMismoNumeroRegistroIdSignoMarca = "select "
                    + " * "
                    + " from sigsignomarca "
                    + " where "
                    + " numero_registro = ? "
                    + " and serie_registro = ? "
                    + " and estado = 'AC' "
                    + " and idsignomarca > ? "
                    + " order by idsignomarca desc "
                    + " limit 1 "
                    + " ";
            if (!jdbcTemplate.query(SiguienteMismoNumeroRegistroIdSignoMarca, new SigSignoMarcaMapper(), numeroRegistro, serieRegistro, idSignoMarca).isEmpty()) {
                return jdbcTemplate.query(SiguienteMismoNumeroRegistroIdSignoMarca, new SigSignoMarcaMapper(), numeroRegistro, serieRegistro, idSignoMarca).get(0);
            } else {
                //como no existe un numero menor en el conjunt ode numero_publicacion, nos saltamos al anterior numero_publicacion
                String SiguienteMismoNumeroPublicacion = "select "
                        + " * "
                        + " from sigsignomarca "
                        + " where "
                        + " numero_registro > ? "
                        + " and estado = 'AC' "
                        + " and numero_registro <> 0 "
                        + " and numero_registro is not null "
                        //+ " and numero_registro <> -1 "
                        + " order by numero_registro asc "
                        + " limit 1 "
                        + " ";
                if (!jdbcTemplate.query(SiguienteMismoNumeroPublicacion, new SigSignoMarcaMapper(), numeroRegistro).isEmpty()) {
                    return jdbcTemplate.query(SiguienteMismoNumeroPublicacion, new SigSignoMarcaMapper(), numeroRegistro).get(0);
                } else {
                    return new SigSignoMarca();
                }

            }
        } catch (Exception e) {

            throw e;
        }
    }

    @Override
    public ClaseNiza obtenerClaseNiza(Long idClaseNiza) throws Exception {
        try {
            String SQL = "select * from claseniza where idclaseniza= ? and estado='AC'";
            ClaseNiza claseNiza = jdbcTemplate.queryForObject(SQL, new ClaseNizaMapper(), idClaseNiza);
            return claseNiza;

        } catch (EmptyResultDataAccessException e) {
            return new ClaseNiza();

        } catch (Exception e) {
            throw e;
        }
    }
}
