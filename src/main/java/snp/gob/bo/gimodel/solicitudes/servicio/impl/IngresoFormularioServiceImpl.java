package snp.gob.bo.gimodel.solicitudes.servicio.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import snp.gob.bo.gimodel.bdimagen.entidad.SigImagen;
import snp.gob.bo.gimodel.bdimagen.entidad.SigLogoTipo;
import snp.gob.bo.gimodel.bdimagen.servicio.SigImagenService;
import snp.gob.bo.gimodel.bdimagen.servicio.SigLogoTipoService;
import snp.gob.bo.gimodel.entidad.ClaseNiza;
import snp.gob.bo.gimodel.entidad.DatoElementoFormulario;
import snp.gob.bo.gimodel.entidad.ElementoFormularioTramite;
import snp.gob.bo.gimodel.entidad.Historial;
import snp.gob.bo.gimodel.entidad.LogTrans;
import snp.gob.bo.gimodel.entidad.ModDireccionDeNotificacion;
import snp.gob.bo.gimodel.entidad.ModModificacion;
import snp.gob.bo.gimodel.entidad.ModSolicitanteApoderado;
import snp.gob.bo.gimodel.entidad.ModTipoSigno;
import snp.gob.bo.gimodel.entidad.ModTitularLicenciatarioNuevo;
import snp.gob.bo.gimodel.entidad.ModTitularLicenciatarioRegistrado;
import snp.gob.bo.gimodel.entidad.ObservacionTramite;
import snp.gob.bo.gimodel.entidad.OpoEvento;
import snp.gob.bo.gimodel.entidad.OpoFechalimite;
import snp.gob.bo.gimodel.entidad.OpoHistorial;
import snp.gob.bo.gimodel.entidad.OpoMarcademandada;
import snp.gob.bo.gimodel.entidad.OpoMarcademandante;
import snp.gob.bo.gimodel.entidad.OpoNotificacion;
import snp.gob.bo.gimodel.entidad.OpoObjetoDmteDmdoNotiSoliapo;
import snp.gob.bo.gimodel.entidad.OpoSolicitanteapoderado;
import snp.gob.bo.gimodel.entidad.Oposicion;
import snp.gob.bo.gimodel.entidad.RenDireccionDeNotificacion;
import snp.gob.bo.gimodel.entidad.RenSolicitanteApoderado;
import snp.gob.bo.gimodel.entidad.RenSolicitudRenovacion;
import snp.gob.bo.gimodel.entidad.RenTipoSigno;
import snp.gob.bo.gimodel.entidad.RenTitularRegistrado;
import snp.gob.bo.gimodel.entidad.Seguimiento;
import snp.gob.bo.gimodel.entidad.SigDireccionDeNotificacion;
import snp.gob.bo.gimodel.entidad.SigLemaComercial;
import snp.gob.bo.gimodel.entidad.SigPrioridadPreferencia;
import snp.gob.bo.gimodel.entidad.SigSignoClaseNiza;
import snp.gob.bo.gimodel.entidad.SigSignoMarca;
import snp.gob.bo.gimodel.entidad.SigSolicitanteApoderado;
import snp.gob.bo.gimodel.entidad.SigTipoSigno;
import snp.gob.bo.gimodel.entidad.Usuario;
import snp.gob.bo.gimodel.entidad.UsuarioTramite;
import snp.gob.bo.gimodel.enums.EnumDominio;
import snp.gob.bo.gimodel.enums.EnumEstado;
import snp.gob.bo.gimodel.enums.EnumEstadoModificacion;
import snp.gob.bo.gimodel.enums.EnumEstadoOposicion;
import snp.gob.bo.gimodel.enums.EnumEstadoRenovacion;
import snp.gob.bo.gimodel.enums.EnumOperacion;
import snp.gob.bo.gimodel.enums.EnumPrefijoTablas;
import snp.gob.bo.gimodel.enums.EnumTipoInteres;
import snp.gob.bo.gimodel.enums.EnumTipoPrioridad;
import snp.gob.bo.gimodel.enums.EnumTipoTramiteUsuario;
import snp.gob.bo.gimodel.enums.EnumUbicacion;
import snp.gob.bo.gimodel.servicio.ClaseNizaService;
import snp.gob.bo.gimodel.servicio.ComunService;
import snp.gob.bo.gimodel.servicio.DatoElementoFormularioService;
import snp.gob.bo.gimodel.servicio.DominioService;
import snp.gob.bo.gimodel.servicio.HistorialService;
import snp.gob.bo.gimodel.servicio.LogTransService;
import snp.gob.bo.gimodel.servicio.ModDireccionDeNotificacionService;
import snp.gob.bo.gimodel.servicio.ModModificacionService;
import snp.gob.bo.gimodel.servicio.ModSolicitanteApoderadoService;
import snp.gob.bo.gimodel.servicio.ModTipoSignoService;
import snp.gob.bo.gimodel.servicio.ModTitularLicenciatarioNuevoService;
import snp.gob.bo.gimodel.servicio.ModTitularLicenciatarioRegistradoService;
import snp.gob.bo.gimodel.servicio.ObservacionTramiteService;
import snp.gob.bo.gimodel.servicio.OpoEventoService;
import snp.gob.bo.gimodel.servicio.OpoFechaLimiteService;
import snp.gob.bo.gimodel.servicio.OpoGeneralService;
import snp.gob.bo.gimodel.servicio.OpoHistorialService;
import snp.gob.bo.gimodel.servicio.OposicionService;
import snp.gob.bo.gimodel.servicio.RenDireccionDeNotificacionService;
import snp.gob.bo.gimodel.servicio.RenSolicitanteApoderadoService;
import snp.gob.bo.gimodel.servicio.RenSolicitudRenovacionService;
import snp.gob.bo.gimodel.servicio.RenTipoSignoService;
import snp.gob.bo.gimodel.servicio.RenTitularRegistradoService;
import snp.gob.bo.gimodel.servicio.SeguimientoService;
import snp.gob.bo.gimodel.servicio.SigDireccionDeNotificacionService;
import snp.gob.bo.gimodel.servicio.SigLemaComercialService;
import snp.gob.bo.gimodel.servicio.SigPrioridadPreferenciaService;
import snp.gob.bo.gimodel.servicio.SigSignoClaseNizaService;
import snp.gob.bo.gimodel.servicio.SigSignoMarcaService;
import snp.gob.bo.gimodel.servicio.SigSolicitanteApoderadoService;
import snp.gob.bo.gimodel.servicio.SigTipoSignoService;
import snp.gob.bo.gimodel.servicio.UsuarioService;
import snp.gob.bo.gimodel.servicio.UsuarioTramiteService;
import snp.gob.bo.gimodel.solicitudes.entidades.Apoderados;
import snp.gob.bo.gimodel.solicitudes.entidades.DireccionNotificaciones;
import snp.gob.bo.gimodel.solicitudes.entidades.FormularioPI100;
import snp.gob.bo.gimodel.solicitudes.entidades.FormularioPI101;
import snp.gob.bo.gimodel.solicitudes.entidades.FormularioPI102;
import snp.gob.bo.gimodel.solicitudes.entidades.FormularioPI103;
import snp.gob.bo.gimodel.solicitudes.entidades.FormularioPI104;
import snp.gob.bo.gimodel.solicitudes.entidades.FormularioPI105;
import snp.gob.bo.gimodel.solicitudes.entidades.LemasComerciales;
import snp.gob.bo.gimodel.solicitudes.entidades.Logotipos;
import snp.gob.bo.gimodel.solicitudes.entidades.ModTipoSignos;
import snp.gob.bo.gimodel.solicitudes.entidades.Prioridades;
import snp.gob.bo.gimodel.solicitudes.entidades.RenTipoSignos;
import snp.gob.bo.gimodel.solicitudes.entidades.RenTitularRegistrados;
import snp.gob.bo.gimodel.solicitudes.entidades.SmSignoClaseNizas;
import snp.gob.bo.gimodel.solicitudes.entidades.SmTipoSignos;
import snp.gob.bo.gimodel.solicitudes.entidades.Solicitantes;
import snp.gob.bo.gimodel.solicitudes.entidades.TitularLicenciatarios;
import snp.gob.bo.gimodel.solicitudes.entidades.TitularLicenciatariosNuevos;
import snp.gob.bo.gimodel.solicitudes.enums.EnumFormularioIngreso;
import snp.gob.bo.gimodel.solicitudes.servicio.FormularioPI100Service;
import snp.gob.bo.gimodel.solicitudes.servicio.FormularioService;
import snp.gob.bo.gimodel.solicitudes.servicio.IngresoFormularioService;

/**
 *
 * @author Eddy Valero
 * @see FormularioPI100
 * @see FormularioPI100Service
 * @version 1.0, 05/06/2016
 */
@Service("ingresoFormularioService")
public class IngresoFormularioServiceImpl implements IngresoFormularioService {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    //inyectar la dependencia de otro servicio
    @Autowired
    private SigSignoMarcaService sigSignoMarcaService;

    @Autowired
    private SigTipoSignoService sigTipoSignoService;

    @Autowired
    private ClaseNizaService claseNizaService;

    @Autowired
    private SigSignoClaseNizaService sigSignoClaseNizaService;

    @Autowired
    private SigLogoTipoService sigLogoTipoService;

    @Autowired
    private SigImagenService sigImagenService;

    @Autowired
    private SigPrioridadPreferenciaService sigPrioridadPreferenciaService;

    @Autowired
    private SigSolicitanteApoderadoService sigSolicitanteApoderadoService;

    @Autowired
    private SigDireccionDeNotificacionService sigDireccionNotificacionService;

    @Autowired
    private SigLemaComercialService sigLemaComercialService;

    @Autowired
    private DatoElementoFormularioService datoElementoFormularioService;

    @Autowired
    private SeguimientoService seguimientoService;

    @Autowired
    private ObservacionTramiteService observacionTramiteService;

    @Autowired
    private FormularioService formularioService;

    @Autowired
    private ComunService comunService;

    @Autowired
    private RenSolicitudRenovacionService renSolicitudRenovacionService;

    @Autowired
    private RenTitularRegistradoService renTitularRegistradoService;

    @Autowired
    private RenDireccionDeNotificacionService renDireccionDeNotificacionService;

    @Autowired
    private RenSolicitanteApoderadoService renSolicitanteApoderadoService;

    //Servicios Modificacion
    @Autowired
    private ModModificacionService modModificacionService;

    @Autowired
    private ModDireccionDeNotificacionService modDireccionNotificacionService;

    @Autowired
    private ModSolicitanteApoderadoService modSolicitanteApoderadoService;

    @Autowired
    private ModTitularLicenciatarioRegistradoService modTitularLicenciatarioRegistradoService;

    @Autowired
    private ModTitularLicenciatarioNuevoService modTitularLicenciatarioNuevoService;

    @Autowired
    private LogTransService logTransService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ModTipoSignoService modTipoSignoService;

    @Autowired
    private RenTipoSignoService renTipoSignoService;

    @Autowired
    private HistorialService historialService;

    @Autowired
    private DominioService dominioService;

    @Autowired
    private UsuarioTramiteService usuarioTramiteService;

    @Autowired
    private OposicionService oposicionService;

    @Autowired
    private SigDireccionDeNotificacionService sigDireccionDeNotificacionService;
    @Autowired
    private OpoGeneralService opoGeneralService;
    @Autowired
    private OpoEventoService opoEventoService;
    @Autowired
    private OpoFechaLimiteService opoFechaLimiteService;
    @Autowired
    private OpoHistorialService opoHistorialService;

    private static final Long idRegional = 1L;
    //Declarando datos de oposicion

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
    @Transactional
    public HashMap guardarFormularioPI100(Date fechaIngreso, FormularioPI100 formularioPI100, List<ElementoFormularioTramite> plantillaVentanilla, Long idUsuario, Long codigoSM, String oficina, String observacion) throws Exception {

        HashMap mapResultado = new HashMap();
        try {

            //obtener el logtrans con el que se realizara la transaccion
            Date fechaServidor = comunService.obtenerFechaHoraServidor(idRegional);

            List<Usuario> listUsuario = usuarioService.listaUsuarioXidPagina(idUsuario);
            LogTrans logTransGuardado = logTransService.crudLogTrans(new LogTrans(idUsuario, fechaServidor), 1);

            Long logtrans = logTransGuardado.getIdLogTrans();

            //obtener la fecha actual del servidor en base a la regional de La Paz
            //Date fechaServidor = comunService.obtenerFechaHoraServidor(idRegional);
            //guardar registro signomarca
            SigSignoMarca sigSignoMarca = new SigSignoMarca();
            sigSignoMarca.setNumeroFormulario(formularioPI100.getFormularios().getNumeroFormulario());
            sigSignoMarca.setSm(codigoSM);

            /**
             * Metodo para guardar el codigoSM ingresado
             */
            HashMap mapValorSM = comunService.obtenerNumeroGestionExtensionNumerico(codigoSM);

            sigSignoMarca.setNumero(Integer.parseInt(mapValorSM.get("numero").toString()));
            sigSignoMarca.setGestion(Integer.parseInt(mapValorSM.get("gestion").toString()));
            sigSignoMarca.setExtensionMarca(Integer.parseInt(mapValorSM.get("extension").toString()));
            sigSignoMarca.setSigno(formularioPI100.getSignoMarca().getMarca());
            sigSignoMarca.setIdLogTrans(logtrans);
            sigSignoMarca.setTipoGenero(formularioPI100.getSignoMarca().getTipoGenero());
            sigSignoMarca.setDescripcionSigno(formularioPI100.getSignoMarca().getDescripcionLogo());
            sigSignoMarca.setOficina(oficina);

            //setear la fechaIngresoSistema
            sigSignoMarca.setFechaIngreso(fechaIngreso);

            sigSignoMarca.setFechaSolicitud(formularioPI100.getFormularios().getFechaSolicitud());
            //ubicacion y estado de la marca
            sigSignoMarca.setEstadoMarca("SO");

            sigSignoMarca.setUbicacion(EnumUbicacion.VENT.getCodigo());
            sigSignoMarca.setEstado(EnumEstado.ACTIVO.getCodigo());

            sigSignoMarca = sigSignoMarcaService.registrarSigSignoMarca(sigSignoMarca);

            //Falta guardar el seguimiento de este tramite
            Seguimiento sigSeguimiento = new Seguimiento();
            sigSeguimiento = seguimientoService.registraSeguimientoSignos(sigSignoMarca.getIdSignoMarca(),
                    idUsuario,
                    logtrans,////
                    codigoSM,
                    "VENT",
                    new Timestamp(fechaServidor.getTime()),
                    new Timestamp(fechaServidor.getTime()),
                    "",
                    EnumEstado.ACTIVO.getCodigo(),
                    1
            );

            //guardar los tipos de signo
            for (SmTipoSignos sMTipoSignos : formularioPI100.getSmTipoSignos()) {
                SigTipoSigno sigTipoSigno = new SigTipoSigno();

                sigTipoSigno.setIdSignoMarca(sigSignoMarca.getIdSignoMarca());
                sigTipoSigno.setIdLogTrans(logtrans);
                sigTipoSigno.setTipoSigno(sMTipoSignos.getTipoSigno());
                sigTipoSigno.setDescripcionOtro(sMTipoSignos.getDescripcionOtro());
                sigTipoSigno.setEstado(EnumEstado.ACTIVO.getCodigo());

                sigTipoSignoService.registrarSigTipoSigno(sigTipoSigno);
            }

            //guardar la relacion con la Clase Niza
            for (SmSignoClaseNizas smSignoClaseNizas : formularioPI100.getSmSignoClaseNizases()) {
                //Obtener la ClaseNiza
//                ClaseNiza claseNiza = claseNizaService.obtenerRegistroClaseNiza(smSignoClaseNizas.getNumeroClaseNiza());
                ClaseNiza claseNiza = claseNizaService.obtenerRegistroClaseNiza(smSignoClaseNizas.getClaseNizaId());

                //Guardar el registro en la Clase Niza
                SigSignoClaseNiza sigSignoClaseNiza = new SigSignoClaseNiza();
                sigSignoClaseNiza.setIdSignoMarca(sigSignoMarca.getIdSignoMarca());
                sigSignoClaseNiza.setIdClaseNiza(claseNiza.getIdClaseNiza());
                sigSignoClaseNiza.setIdLogTrans(logtrans);
                sigSignoClaseNiza.setNumeroClaseNiza(smSignoClaseNizas.getNumeroClaseNiza());
                sigSignoClaseNiza.setListaProducto(smSignoClaseNizas.getListaProductos());
                sigSignoClaseNiza.setEstado(EnumEstado.ACTIVO.getCodigo());
                sigSignoClaseNiza.setIdSipi(smSignoClaseNizas.getId());

                sigSignoClaseNizaService.registrarSigSignoMarca(sigSignoClaseNiza);
            }

            //guardar la relacion de SigLogoTipo
            for (Logotipos logoTipo : formularioPI100.getLogotipos()) {
                SigLogoTipo sigLogoTipo = new SigLogoTipo();

                sigLogoTipo.setIdSignoMarca(sigSignoMarca.getIdSignoMarca());
                sigLogoTipo.setIdLogTrans(logtrans);
                sigLogoTipo.setUrlLogoTipo(logoTipo.getUrlimagen());
                sigLogoTipo.setPrincipal(logoTipo.getPrincipal());
                sigLogoTipo.setNombreArchivo(codigoSM.toString());
//                sigLogoTipo.setNombreArchivo(logoTipo.getNombreArchivo());
                sigLogoTipo.setExtensionArchivo(logoTipo.getExtensionArchivo());
                sigLogoTipo.setEstado(EnumEstado.ACTIVO.getCodigo());

                sigLogoTipo = sigLogoTipoService.registrarSigLogoTipo(sigLogoTipo);

                SigImagen sigImagen = new SigImagen();

                sigImagen.setIdLogoTipo(sigLogoTipo.getIdLogoTipo());
                sigImagen.setIdLogTrans(logtrans);
                sigImagen.setImagen(logoTipo.getImagen());

                sigImagen = sigImagenService.registrarSigImagen(sigImagen);

            }

            //guardar las prioridades y preferencias
            for (Prioridades prioridad : formularioPI100.getPrioridades()) {
                SigPrioridadPreferencia sigPrioridadPreferencia = new SigPrioridadPreferencia();
                sigPrioridadPreferencia.setIdSignoMarca(sigSignoMarca.getIdSignoMarca());
                sigPrioridadPreferencia.setIdLogTrans(logtrans);
                sigPrioridadPreferencia.setTipoPrioridad(prioridad.getTipoPrioridad());
                sigPrioridadPreferencia.setTipoInteres(prioridad.getTipoInteres());
                sigPrioridadPreferencia.setNombreNumero(prioridad.getNombre());
                sigPrioridadPreferencia.setFecha(prioridad.getFecha());
                sigPrioridadPreferencia.setIdSipi(prioridad.getId());

                //Obtener el dominio a partir del nombre
                if (prioridad.getTipoInteres().equals(EnumTipoInteres.EXTRANJERA.getCodigo())) {
                    sigPrioridadPreferencia.setLugar(dominioService.obtenerCodigoDominioPorNombre("pais", prioridad.getLugar()));
                } else {
                    sigPrioridadPreferencia.setLugar(prioridad.getLugar());
                }

                sigPrioridadPreferencia.setEstado(EnumEstado.ACTIVO.getCodigo());

                sigPrioridadPreferencia = sigPrioridadPreferenciaService.registrarSigPrioridadPreferencia(sigPrioridadPreferencia);

            }

            //guardar las direcciones
            for (DireccionNotificaciones direccion : formularioPI100.getDirecciones()) {

                SigDireccionDeNotificacion sigDireccionNotificacion = new SigDireccionDeNotificacion();

                sigDireccionNotificacion.setIdSignoMarca(sigSignoMarca.getIdSignoMarca());
                sigDireccionNotificacion.setIdLogTrans(logtrans);
                sigDireccionNotificacion.setCiudadNotificacion(direccion.getCiudadNotificacion());
                sigDireccionNotificacion.setZonaBarrio(direccion.getZonaBarrio());
                sigDireccionNotificacion.setAvenidaCalle(direccion.getAvenidaCalle());
                sigDireccionNotificacion.setNumero(direccion.getNumeroDomicilio());
                sigDireccionNotificacion.setEdificio(direccion.getNombreEdificio());
                sigDireccionNotificacion.setPiso(direccion.getPiso());
                sigDireccionNotificacion.setDepartamento(direccion.getDepartamento());
                sigDireccionNotificacion.setReferenciaDireccion(direccion.getReferencia());
                sigDireccionNotificacion.setCorreoElectronico(direccion.getCorreoelectronico());
                sigDireccionNotificacion.setTelefono(direccion.getTelefono());
                sigDireccionNotificacion.setCelular(direccion.getCelular());
                sigDireccionNotificacion.setEstado(EnumEstado.ACTIVO.getCodigo());

                sigDireccionNotificacion = sigDireccionNotificacionService.registrarSigDireccionNotificacion(sigDireccionNotificacion);
            }

            //guardar el solicitante y/o apoderado
            for (Solicitantes solicitantes : formularioPI100.getSolicitantes()) {
                SigSolicitanteApoderado sigSolicitanteApoderado = new SigSolicitanteApoderado();

                sigSolicitanteApoderado.setIdSignoMarca(sigSignoMarca.getIdSignoMarca());
                sigSolicitanteApoderado.setIdLogTrans(logtrans);
                sigSolicitanteApoderado.setTipoTitular(solicitantes.getTipoPersona());
                sigSolicitanteApoderado.setTipoPersona("SOLI");
                sigSolicitanteApoderado.setNombreRazonSocial(solicitantes.getNombreRazonSocial());
                sigSolicitanteApoderado.setPrimerApellido(solicitantes.getPrimerApellido());
                sigSolicitanteApoderado.setSegundoApellido(solicitantes.getSegundoApellido());
                sigSolicitanteApoderado.setNumeroDocumento(solicitantes.getNumeroDocumento());
                sigSolicitanteApoderado.setTipoDocumento(solicitantes.getTipoDocumento());
                sigSolicitanteApoderado.setLugarExpedicion(solicitantes.getLugarExpedicion());
                sigSolicitanteApoderado.setPais(solicitantes.getPais());
                sigSolicitanteApoderado.setGenero(solicitantes.getGenero());
                sigSolicitanteApoderado.setSolicitudDepartamento(solicitantes.getDepartamentoSolicitud());
                sigSolicitanteApoderado.setPoder(null);
                sigSolicitanteApoderado.setDireccion(solicitantes.getDomicilio());
                sigSolicitanteApoderado.setTelefono(solicitantes.getTelefono());
                sigSolicitanteApoderado.setCelular(solicitantes.getCelular());
                sigSolicitanteApoderado.setEmail(solicitantes.getCorreoElectronico());
                sigSolicitanteApoderado.setFax(null);
                sigSolicitanteApoderado.setEstado(EnumEstado.ACTIVO.getCodigo());
                sigSolicitanteApoderado.setIdSipi(solicitantes.getId());

                sigSolicitanteApoderado = sigSolicitanteApoderadoService.registrarSigSigSolicitanteApoderado(sigSolicitanteApoderado);
            }

            for (Apoderados apoderados : formularioPI100.getApoderados()) {
                SigSolicitanteApoderado sigSolicitanteApoderado = new SigSolicitanteApoderado();
                sigSolicitanteApoderado.setIdSignoMarca(sigSignoMarca.getIdSignoMarca());
                sigSolicitanteApoderado.setIdLogTrans(logtrans);
                sigSolicitanteApoderado.setTipoTitular("NAT");
                sigSolicitanteApoderado.setTipoPersona("APOD");
                sigSolicitanteApoderado.setNombreRazonSocial(apoderados.getNombres());
                sigSolicitanteApoderado.setPrimerApellido(apoderados.getPrimerApellido());
                sigSolicitanteApoderado.setSegundoApellido(apoderados.getSegundoApellido());
                sigSolicitanteApoderado.setNumeroDocumento(apoderados.getNumeroDocumento());
                sigSolicitanteApoderado.setTipoDocumento(apoderados.getTipoDocumento());
                sigSolicitanteApoderado.setLugarExpedicion(apoderados.getLugarExpedicion());
                sigSolicitanteApoderado.setPais(null);
                sigSolicitanteApoderado.setGenero(apoderados.getGenero());
                sigSolicitanteApoderado.setSolicitudDepartamento(null);
                sigSolicitanteApoderado.setPoder(apoderados.getNumeroPoder());
//                sigSolicitanteApoderado.setPoder();
                sigSolicitanteApoderado.setDireccion(apoderados.getDomicilio());
                sigSolicitanteApoderado.setTelefono(apoderados.getTelefono());
                sigSolicitanteApoderado.setCelular(apoderados.getCelular());
                sigSolicitanteApoderado.setEmail(apoderados.getCorreoElectronico());
                sigSolicitanteApoderado.setFax(null);
                sigSolicitanteApoderado.setEstado(EnumEstado.ACTIVO.getCodigo());
                sigSolicitanteApoderado.setIdSipi(apoderados.getId());

                sigSolicitanteApoderado = sigSolicitanteApoderadoService.registrarSigSigSolicitanteApoderado(sigSolicitanteApoderado);
            }

            //guardar los registros del listado de requisitos
            List<DatoElementoFormulario> listaDatoElementoFormulario = new ArrayList<DatoElementoFormulario>();

            //Armar la lista
            for (ElementoFormularioTramite elementoFormularioTramite : plantillaVentanilla) {
                DatoElementoFormulario datoElementoFormulario = new DatoElementoFormulario();

                datoElementoFormulario.setNombreTabla("sigseguimiento");
                datoElementoFormulario.setIdSeguimiento(sigSeguimiento.getIdSeguimiento());
                datoElementoFormulario.setIdLogTrans(logtrans);
                datoElementoFormulario.setPestania(elementoFormularioTramite.getPestania());
                datoElementoFormulario.setSeccion(elementoFormularioTramite.getSeccion());
                datoElementoFormulario.setFila(elementoFormularioTramite.getFila());
                datoElementoFormulario.setTipoElemento(elementoFormularioTramite.getTipoElemento());
                datoElementoFormulario.setNombreElemento(elementoFormularioTramite.getNombreElemento());
                datoElementoFormulario.setOrden(elementoFormularioTramite.getOrden());
                datoElementoFormulario.setOrdenLiteral(elementoFormularioTramite.getOrdenLiteral());
                datoElementoFormulario.setIdpadre(elementoFormularioTramite.getIdpadre());
                datoElementoFormulario.setRespuesta(elementoFormularioTramite.getRespuesta());
                datoElementoFormulario.setOpcionRespuesta(elementoFormularioTramite.getOpcionRespuesta());
                datoElementoFormulario.setEstado(EnumEstado.ACTIVO.getCodigo());

                listaDatoElementoFormulario.add(datoElementoFormulario);
            }

            datoElementoFormularioService.guardarRegistrosPlantillaVentanilla(listaDatoElementoFormulario, EnumPrefijoTablas.SIGNO.getCodigo());

            if (!observacion.equals("")) {
                //finalmente guardar la observacion del tramite.
                observacionTramiteService.registrarObservacionTramite(null, "SIG", sigSignoMarca.getIdSignoMarca(),
                        idUsuario, logtrans, Boolean.FALSE, fechaServidor,
                        "VENT", observacion, 1);
            }

            //Guardar el historial del ingreso del formulario PI100
            Historial historial = new Historial();
            historial.setIdLogTrans(logTransGuardado.getIdLogTrans());
            historial.setId(sigSignoMarca.getIdSignoMarca());
            historial.setIdUsuario(idUsuario);
            historial.setOperacion(EnumOperacion.ADICIONAR.getCodigo());
            historial.setEstadoMarcaDescripcion(dominioService.obtenerNombreCodigoDominio(EnumDominio.ESTADO_MARCA.getCodigo(), sigSignoMarca.getEstadoMarca()));
            historial.setDescripcion(EnumFormularioIngreso.PI100.getCodigo());
            historial.setFechaOperacion(fechaServidor);
            historial.setEstado(EnumEstado.ACTIVO.getCodigo());
            historialService.crudHistorial(historial, EnumPrefijoTablas.SIGNO.getCodigo(), 1);

            //realizar el registro en la tabla UsuarioTramite.
            UsuarioTramite usuarioTramite = new UsuarioTramite();

            usuarioTramite.setTipoTramite(EnumTipoTramiteUsuario.SIGNOS.getCodigo());
            usuarioTramite.setIdUsuarioExterno(formularioPI100.getFormularios().getIdUsuarioExterno());
            usuarioTramite.setId(sigSignoMarca.getIdSignoMarca());
            usuarioTramite.setIdLogTrans(logtrans);
            usuarioTramite.setEstado(EnumEstado.ACTIVO.getCodigo());
            usuarioTramiteService.crudUsuarioTramite(usuarioTramite, 1L);

            //Actualizar el estado del formulario
            formularioService.actualizarRegistroFormulario(formularioPI100.getFormularios().getId());

            mapResultado.put("nombreTabla", "sigseguimiento");
            mapResultado.put("idTabla", sigSeguimiento.getIdSeguimiento().toString());
            mapResultado.put("idSignoMarca", sigSignoMarca.getIdSignoMarca());

            return mapResultado;
        } catch (Exception e) {
            throw e;
        }

    }

    @Override
    @Transactional
    public HashMap guardarFormularioPI101(Date fechaIngreso, FormularioPI101 formularioPI101, List<ElementoFormularioTramite> plantillaVentanilla, Long idUsuario, Long codigoSM, String oficina, String observacion) throws Exception {
        HashMap mapResultado = new HashMap();

        try {
            Date fechaServidor = comunService.obtenerFechaHoraServidor(idRegional);
            List<Usuario> listUsuario = usuarioService.listaUsuarioXidPagina(idUsuario);
            LogTrans logTransGuardado = logTransService.crudLogTrans(new LogTrans(idUsuario, fechaServidor), 1);

            //obtener el logtrans con el que se realizara la transaccion
            Long logtrans = logTransGuardado.getIdLogTrans();

            //obtener la fecha actual del servidor
//            Date fechaServidor = comunService.obtenerFechaHoraServidor(idRegional);
            //guardar registro signomarca
            SigSignoMarca sigSignoMarca = new SigSignoMarca();

            HashMap mapValorSM = comunService.obtenerNumeroGestionExtensionNumerico(codigoSM);

            sigSignoMarca.setNumero(Integer.parseInt(mapValorSM.get("numero").toString()));
            sigSignoMarca.setGestion(Integer.parseInt(mapValorSM.get("gestion").toString()));
            sigSignoMarca.setExtensionMarca(Integer.parseInt(mapValorSM.get("extension").toString()));
            sigSignoMarca.setNumeroFormulario(formularioPI101.getFormularios().getNumeroFormulario());
            sigSignoMarca.setSm(codigoSM);
            sigSignoMarca.setSigno(formularioPI101.getSignoMarca().getMarca());
            sigSignoMarca.setIdLogTrans(logtrans);
            sigSignoMarca.setTipoGenero(formularioPI101.getSignoMarca().getTipoGenero());
            sigSignoMarca.setDescripcionSigno(formularioPI101.getSignoMarca().getDescripcionLogo());
            sigSignoMarca.setFechaIngreso(fechaIngreso);
            sigSignoMarca.setFechaSolicitud(formularioPI101.getFormularios().getFechaSolicitud());
            sigSignoMarca.setOficina(oficina);
            //falta la ubicacion y el estado de la marca
            sigSignoMarca.setEstadoMarca("SO");
            sigSignoMarca.setUbicacion(EnumUbicacion.VENT.getCodigo());
            sigSignoMarca.setEstado(EnumEstado.ACTIVO.getCodigo());

            sigSignoMarca = sigSignoMarcaService.registrarSigSignoMarca(sigSignoMarca);

            //guardar el seguimiento del tramite
            Seguimiento sigSeguimiento = new Seguimiento();
            sigSeguimiento = seguimientoService.registraSeguimientoSignos(sigSignoMarca.getIdSignoMarca(),
                    idUsuario,
                    logtrans,
                    codigoSM,
                    "VENT",
                    new Timestamp(fechaServidor.getTime()),
                    new Timestamp(fechaServidor.getTime()),
                    "",
                    EnumEstado.ACTIVO.getCodigo(),
                    1
            );

            //guardar los tipos de signo
            for (SmTipoSignos sMTipoSignos : formularioPI101.getSmTipoSignos()) {
                SigTipoSigno sigTipoSigno = new SigTipoSigno();

                sigTipoSigno.setIdSignoMarca(sigSignoMarca.getIdSignoMarca());
                sigTipoSigno.setIdLogTrans(logtrans);
                sigTipoSigno.setTipoSigno(sMTipoSignos.getTipoSigno());
                sigTipoSigno.setDescripcionOtro(sMTipoSignos.getDescripcionOtro());
                sigTipoSigno.setEstado(EnumEstado.ACTIVO.getCodigo());

                sigTipoSignoService.registrarSigTipoSigno(sigTipoSigno);
            }

            //guardar la relacion con la Clase Niza
            for (SmSignoClaseNizas smSignoClaseNizas : formularioPI101.getSmSignoClaseNizases()) {
                //Obtener la ClaseNiza
                ClaseNiza claseNiza = claseNizaService.obtenerRegistroClaseNiza(smSignoClaseNizas.getClaseNizaId());

                //Guardar el registro en la Clase Niza
                SigSignoClaseNiza sigSignoClaseNiza = new SigSignoClaseNiza();
                sigSignoClaseNiza.setIdSignoMarca(sigSignoMarca.getIdSignoMarca());
                sigSignoClaseNiza.setIdClaseNiza(claseNiza.getIdClaseNiza());
                sigSignoClaseNiza.setIdLogTrans(logtrans);
                sigSignoClaseNiza.setNumeroClaseNiza(smSignoClaseNizas.getNumeroClaseNiza());
                sigSignoClaseNiza.setListaProducto(smSignoClaseNizas.getListaProductos());
                sigSignoClaseNiza.setEstado(EnumEstado.ACTIVO.getCodigo());
                sigSignoClaseNiza.setIdSipi(smSignoClaseNizas.getId());

                sigSignoClaseNizaService.registrarSigSignoMarca(sigSignoClaseNiza);
            }

            //guardar la relacion de SigLogoTipo
            for (Logotipos logoTipo : formularioPI101.getLogotipos()) {
                SigLogoTipo sigLogoTipo = new SigLogoTipo();

                sigLogoTipo.setIdSignoMarca(sigSignoMarca.getIdSignoMarca());
                sigLogoTipo.setIdLogTrans(logtrans);
                sigLogoTipo.setUrlLogoTipo(logoTipo.getUrlimagen());
                sigLogoTipo.setPrincipal(logoTipo.getPrincipal());
//                sigLogoTipo.setNombreArchivo(logoTipo.getNombreArchivo());
                sigLogoTipo.setNombreArchivo(codigoSM.toString());

                sigLogoTipo.setExtensionArchivo("jpg");
                sigLogoTipo.setEstado(EnumEstado.ACTIVO.getCodigo());

                sigLogoTipo = sigLogoTipoService.registrarSigLogoTipo(sigLogoTipo);

                SigImagen sigImagen = new SigImagen();

                sigImagen.setIdLogoTipo(sigLogoTipo.getIdLogoTipo());
                sigImagen.setIdLogTrans(logtrans);
                sigImagen.setImagen(logoTipo.getImagen());

                sigImagen = sigImagenService.registrarSigImagen(sigImagen);

            }

            //guardar las prioridades y preferencias
            for (Prioridades prioridad : formularioPI101.getPrioridades()) {
                SigPrioridadPreferencia sigPrioridadPreferencia = new SigPrioridadPreferencia();
                sigPrioridadPreferencia.setIdSignoMarca(sigSignoMarca.getIdSignoMarca());
                sigPrioridadPreferencia.setIdLogTrans(logtrans);
                sigPrioridadPreferencia.setTipoPrioridad(prioridad.getTipoPrioridad());
                sigPrioridadPreferencia.setTipoInteres(prioridad.getTipoInteres());
                sigPrioridadPreferencia.setNombreNumero(prioridad.getNombre());
                sigPrioridadPreferencia.setFecha(prioridad.getFecha());
                sigPrioridadPreferencia.setIdSipi(prioridad.getId());

                //Obtener el dominio a partir del nombre
                if (prioridad.getTipoInteres().equals(EnumTipoInteres.EXTRANJERA.getCodigo())) {
                    sigPrioridadPreferencia.setLugar(dominioService.obtenerCodigoDominioPorNombre("pais", prioridad.getLugar()));
                } else {
                    sigPrioridadPreferencia.setLugar(prioridad.getLugar());
                }

                sigPrioridadPreferencia.setEstado(EnumEstado.ACTIVO.getCodigo());

                sigPrioridadPreferencia = sigPrioridadPreferenciaService.registrarSigPrioridadPreferencia(sigPrioridadPreferencia);

            }

            //guardar las direcciones
            for (DireccionNotificaciones direccion : formularioPI101.getDirecciones()) {

                SigDireccionDeNotificacion sigDireccionNotificacion = new SigDireccionDeNotificacion();

                sigDireccionNotificacion.setIdSignoMarca(sigSignoMarca.getIdSignoMarca());
                sigDireccionNotificacion.setIdLogTrans(logtrans);
                sigDireccionNotificacion.setCiudadNotificacion(direccion.getCiudadNotificacion());
                sigDireccionNotificacion.setZonaBarrio(direccion.getZonaBarrio());
                sigDireccionNotificacion.setAvenidaCalle(direccion.getAvenidaCalle());
                sigDireccionNotificacion.setNumero(direccion.getNumeroDomicilio());
                sigDireccionNotificacion.setEdificio(direccion.getNombreEdificio());
                sigDireccionNotificacion.setPiso(direccion.getPiso());
                sigDireccionNotificacion.setDepartamento(direccion.getDepartamento());
                sigDireccionNotificacion.setReferenciaDireccion(direccion.getReferencia());
                sigDireccionNotificacion.setCorreoElectronico(direccion.getCorreoelectronico());
                sigDireccionNotificacion.setTelefono(direccion.getTelefono());
                sigDireccionNotificacion.setCelular(direccion.getCelular());
                sigDireccionNotificacion.setEstado(EnumEstado.ACTIVO.getCodigo());

                sigDireccionNotificacion = sigDireccionNotificacionService.registrarSigDireccionNotificacion(sigDireccionNotificacion);
            }

            //guardar el solicitante y/o apoderado
            for (Solicitantes solicitantes : formularioPI101.getSolicitantes()) {
                SigSolicitanteApoderado sigSolicitanteApoderado = new SigSolicitanteApoderado();

                sigSolicitanteApoderado.setIdSignoMarca(sigSignoMarca.getIdSignoMarca());
                sigSolicitanteApoderado.setIdLogTrans(logtrans);
                sigSolicitanteApoderado.setTipoTitular(solicitantes.getTipoPersona());
                sigSolicitanteApoderado.setTipoPersona("SOLI");
                sigSolicitanteApoderado.setNombreRazonSocial(solicitantes.getNombreRazonSocial());
                sigSolicitanteApoderado.setPrimerApellido(solicitantes.getPrimerApellido());
                sigSolicitanteApoderado.setSegundoApellido(solicitantes.getSegundoApellido());
                sigSolicitanteApoderado.setNumeroDocumento(solicitantes.getNumeroDocumento());
                sigSolicitanteApoderado.setTipoDocumento(solicitantes.getTipoDocumento());
                sigSolicitanteApoderado.setLugarExpedicion(solicitantes.getLugarExpedicion());
                sigSolicitanteApoderado.setPais(solicitantes.getPais());
                sigSolicitanteApoderado.setGenero(solicitantes.getGenero());
                sigSolicitanteApoderado.setSolicitudDepartamento(solicitantes.getDepartamentoSolicitud());
                sigSolicitanteApoderado.setPoder(null);
                sigSolicitanteApoderado.setDireccion(solicitantes.getDomicilio());
                sigSolicitanteApoderado.setTelefono(solicitantes.getTelefono());
                sigSolicitanteApoderado.setCelular(solicitantes.getCelular());
                sigSolicitanteApoderado.setEmail(solicitantes.getCorreoElectronico());
                sigSolicitanteApoderado.setFax(null);
                sigSolicitanteApoderado.setEstado(EnumEstado.ACTIVO.getCodigo());
                sigSolicitanteApoderado.setIdSipi(solicitantes.getId());

                sigSolicitanteApoderado = sigSolicitanteApoderadoService.registrarSigSigSolicitanteApoderado(sigSolicitanteApoderado);
            }

            for (Apoderados apoderados : formularioPI101.getApoderados()) {
                SigSolicitanteApoderado sigSolicitanteApoderado = new SigSolicitanteApoderado();
                sigSolicitanteApoderado.setIdSignoMarca(sigSignoMarca.getIdSignoMarca());
                sigSolicitanteApoderado.setIdLogTrans(logtrans);
                sigSolicitanteApoderado.setTipoTitular("NAT");
                sigSolicitanteApoderado.setTipoPersona("APOD");
                sigSolicitanteApoderado.setNombreRazonSocial(apoderados.getNombres());
                sigSolicitanteApoderado.setPrimerApellido(apoderados.getPrimerApellido());
                sigSolicitanteApoderado.setSegundoApellido(apoderados.getSegundoApellido());
                sigSolicitanteApoderado.setNumeroDocumento(apoderados.getNumeroDocumento());
                sigSolicitanteApoderado.setTipoDocumento(apoderados.getTipoDocumento());
                sigSolicitanteApoderado.setLugarExpedicion(apoderados.getLugarExpedicion());
                sigSolicitanteApoderado.setPais(null);
                sigSolicitanteApoderado.setGenero(apoderados.getGenero());
                sigSolicitanteApoderado.setSolicitudDepartamento(null);
                sigSolicitanteApoderado.setPoder(apoderados.getNumeroPoder());
//                sigSolicitanteApoderado.setPoder();
                sigSolicitanteApoderado.setDireccion(apoderados.getDomicilio());
                sigSolicitanteApoderado.setTelefono(apoderados.getTelefono());
                sigSolicitanteApoderado.setCelular(apoderados.getCelular());
                sigSolicitanteApoderado.setEmail(apoderados.getCorreoElectronico());
                sigSolicitanteApoderado.setFax(null);
                sigSolicitanteApoderado.setEstado(EnumEstado.ACTIVO.getCodigo());
                sigSolicitanteApoderado.setIdSipi(apoderados.getId());

                sigSolicitanteApoderado = sigSolicitanteApoderadoService.registrarSigSigSolicitanteApoderado(sigSolicitanteApoderado);
            }

            //guardar los registros del listado de requisitos
            List<DatoElementoFormulario> listaDatoElementoFormulario = new ArrayList<DatoElementoFormulario>();

            //Armar la lista
            for (ElementoFormularioTramite elementoFormularioTramite : plantillaVentanilla) {
                DatoElementoFormulario datoElementoFormulario = new DatoElementoFormulario();

                datoElementoFormulario.setNombreTabla("sigseguimiento");
                datoElementoFormulario.setIdSeguimiento(sigSeguimiento.getIdSeguimiento());
                datoElementoFormulario.setIdLogTrans(logtrans);
                datoElementoFormulario.setPestania(elementoFormularioTramite.getPestania());
                datoElementoFormulario.setSeccion(elementoFormularioTramite.getSeccion());
                datoElementoFormulario.setFila(elementoFormularioTramite.getFila());
                datoElementoFormulario.setTipoElemento(elementoFormularioTramite.getTipoElemento());
                datoElementoFormulario.setNombreElemento(elementoFormularioTramite.getNombreElemento());
                datoElementoFormulario.setOrden(elementoFormularioTramite.getOrden());
                datoElementoFormulario.setOrdenLiteral(elementoFormularioTramite.getOrdenLiteral());
                datoElementoFormulario.setIdpadre(elementoFormularioTramite.getIdpadre());
                datoElementoFormulario.setRespuesta(elementoFormularioTramite.getRespuesta());
                datoElementoFormulario.setOpcionRespuesta(elementoFormularioTramite.getOpcionRespuesta());
                datoElementoFormulario.setEstado(EnumEstado.ACTIVO.getCodigo());

                listaDatoElementoFormulario.add(datoElementoFormulario);
            }

            datoElementoFormularioService.guardarRegistrosPlantillaVentanilla(listaDatoElementoFormulario, EnumPrefijoTablas.SIGNO.getCodigo());

            if (!observacion.equals("")) {
                //finalmente guardar la observacion del tramite.
                observacionTramiteService.registrarObservacionTramite(null, "SIG", sigSignoMarca.getIdSignoMarca(),
                        idUsuario, logtrans, Boolean.FALSE, fechaServidor,
                        "VENT", observacion, 1);
            }

            //Guardar el historial del ingreso del formulario PI101
            Historial historial = new Historial();
            historial.setIdLogTrans(logTransGuardado.getIdLogTrans());
            historial.setId(sigSignoMarca.getIdSignoMarca());
            historial.setIdUsuario(idUsuario);
            historial.setOperacion(EnumOperacion.ADICIONAR.getCodigo());
            historial.setEstadoMarcaDescripcion(dominioService.obtenerNombreCodigoDominio(EnumDominio.ESTADO_MARCA.getCodigo(), sigSignoMarca.getEstadoMarca()));
            historial.setDescripcion(EnumFormularioIngreso.PI101.getCodigo());
            historial.setFechaOperacion(fechaServidor);
            historial.setEstado(EnumEstado.ACTIVO.getCodigo());
            historialService.crudHistorial(historial, EnumPrefijoTablas.SIGNO.getCodigo(), 1);

            //realizar el registro en la tabla UsuarioTramite.
            UsuarioTramite usuarioTramite = new UsuarioTramite();

            usuarioTramite.setTipoTramite(EnumTipoTramiteUsuario.SIGNOS.getCodigo());
            usuarioTramite.setIdUsuarioExterno(formularioPI101.getFormularios().getIdUsuarioExterno());
            usuarioTramite.setId(sigSignoMarca.getIdSignoMarca());
            usuarioTramite.setIdLogTrans(logtrans);
            usuarioTramite.setEstado(EnumEstado.ACTIVO.getCodigo());
            usuarioTramiteService.crudUsuarioTramite(usuarioTramite, 1L);

            //Actualizar el estado del formulario
            formularioService.actualizarRegistroFormulario(formularioPI101.getFormularios().getId());

            mapResultado.put("nombreTabla", "sigseguimiento");
            mapResultado.put("idTabla", sigSeguimiento.getIdSeguimiento().toString());
            mapResultado.put("idSignoMarca", sigSignoMarca.getIdSignoMarca());

            return mapResultado;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    @Transactional
    public HashMap guardarFormularioPI102(Date fechaIngreso, FormularioPI102 formularioPI102, List<ElementoFormularioTramite> plantillaVentanilla, Long idUsuario, Long codigoSM, String oficina, String observacion) throws Exception {

        HashMap mapResultado = new HashMap();

        try {

            Date fechaServidor = comunService.obtenerFechaHoraServidor(idRegional);
            List<Usuario> listUsuario = usuarioService.listaUsuarioXidPagina(idUsuario);
            LogTrans logTransGuardado = logTransService.crudLogTrans(new LogTrans(idUsuario, fechaServidor), 1);

            //obtener el logtrans con el que se realizara la transaccion
            Long logtrans = logTransGuardado.getIdLogTrans();

            //guardar registro signomarca
            SigSignoMarca sigSignoMarca = new SigSignoMarca();
            HashMap mapValorSM = comunService.obtenerNumeroGestionExtensionNumerico(codigoSM);

            sigSignoMarca.setNumero(Integer.parseInt(mapValorSM.get("numero").toString()));
            sigSignoMarca.setGestion(Integer.parseInt(mapValorSM.get("gestion").toString()));
            sigSignoMarca.setExtensionMarca(Integer.parseInt(mapValorSM.get("extension").toString()));
            sigSignoMarca.setNumeroFormulario(formularioPI102.getFormularios().getNumeroFormulario());
            sigSignoMarca.setSm(codigoSM);
            sigSignoMarca.setSigno(formularioPI102.getSignoMarca().getMarca());
            sigSignoMarca.setIdLogTrans(logtrans);
            sigSignoMarca.setTipoGenero(formularioPI102.getSignoMarca().getTipoGenero());
            sigSignoMarca.setDescripcionSigno(formularioPI102.getSignoMarca().getDescripcionLogo());
            sigSignoMarca.setFechaIngreso(fechaIngreso);
            sigSignoMarca.setFechaSolicitud(formularioPI102.getFormularios().getFechaSolicitud());
            sigSignoMarca.setOficina(oficina);
            //falta la ubicacion y el estado de la marca
            sigSignoMarca.setEstadoMarca("SO");
            sigSignoMarca.setUbicacion("VENT");
            sigSignoMarca.setEstado(EnumEstado.ACTIVO.getCodigo());

            sigSignoMarca = sigSignoMarcaService.registrarSigSignoMarca(sigSignoMarca);

            //guardar el seguimiento del tramite
            Seguimiento sigSeguimiento = new Seguimiento();
            sigSeguimiento = seguimientoService.registraSeguimientoSignos(sigSignoMarca.getIdSignoMarca(),
                    idUsuario,
                    logtrans,
                    codigoSM,
                    "VENT",
                    new Timestamp(fechaServidor.getTime()),
                    new Timestamp(fechaServidor.getTime()),
                    "",
                    EnumEstado.ACTIVO.getCodigo(),
                    1
            );
            //guardar los tipos de signo
            for (SmTipoSignos sMTipoSignos : formularioPI102.getSmTipoSignos()) {
                SigTipoSigno sigTipoSigno = new SigTipoSigno();

                sigTipoSigno.setIdSignoMarca(sigSignoMarca.getIdSignoMarca());
                sigTipoSigno.setIdLogTrans(logtrans);
                sigTipoSigno.setTipoSigno(sMTipoSignos.getTipoSigno());
                sigTipoSigno.setDescripcionOtro(sMTipoSignos.getDescripcionOtro());
                sigTipoSigno.setEstado(EnumEstado.ACTIVO.getCodigo());

                sigTipoSignoService.registrarSigTipoSigno(sigTipoSigno);
            }

            //guardar la relacion con la Clase Niza
            for (SmSignoClaseNizas smSignoClaseNizas : formularioPI102.getSmSignoClaseNizases()) {
                //Obtener la ClaseNiza
                ClaseNiza claseNiza = claseNizaService.obtenerRegistroClaseNiza(smSignoClaseNizas.getClaseNizaId());

                //Guardar el registro en la Clase Niza
                SigSignoClaseNiza sigSignoClaseNiza = new SigSignoClaseNiza();
                sigSignoClaseNiza.setIdSignoMarca(sigSignoMarca.getIdSignoMarca());
                sigSignoClaseNiza.setIdClaseNiza(claseNiza.getIdClaseNiza());
                sigSignoClaseNiza.setIdLogTrans(logtrans);
                sigSignoClaseNiza.setNumeroClaseNiza(smSignoClaseNizas.getNumeroClaseNiza());
                sigSignoClaseNiza.setListaProducto(smSignoClaseNizas.getListaProductos());
                sigSignoClaseNiza.setEstado(EnumEstado.ACTIVO.getCodigo());
                sigSignoClaseNiza.setIdSipi(smSignoClaseNizas.getId());

                sigSignoClaseNizaService.registrarSigSignoMarca(sigSignoClaseNiza);
            }

            //guardar la relacion de SigLogoTipo
            for (Logotipos logoTipo : formularioPI102.getLogotipos()) {
                SigLogoTipo sigLogoTipo = new SigLogoTipo();

                sigLogoTipo.setIdSignoMarca(sigSignoMarca.getIdSignoMarca());
                sigLogoTipo.setIdLogTrans(logtrans);
                sigLogoTipo.setUrlLogoTipo(logoTipo.getUrlimagen());
                sigLogoTipo.setPrincipal(logoTipo.getPrincipal());
//                sigLogoTipo.setNombreArchivo(logoTipo.getNombreArchivo());
                sigLogoTipo.setNombreArchivo(codigoSM.toString());
                sigLogoTipo.setExtensionArchivo("jpg");
                sigLogoTipo.setEstado(EnumEstado.ACTIVO.getCodigo());

                sigLogoTipo = sigLogoTipoService.registrarSigLogoTipo(sigLogoTipo);

                SigImagen sigImagen = new SigImagen();

                sigImagen.setIdLogoTipo(sigLogoTipo.getIdLogoTipo());
                sigImagen.setIdLogTrans(logtrans);
                sigImagen.setImagen(logoTipo.getImagen());

                sigImagen = sigImagenService.registrarSigImagen(sigImagen);

            }

            //guardar las prioridades y preferencias
            for (Prioridades prioridad : formularioPI102.getPrioridades()) {
                SigPrioridadPreferencia sigPrioridadPreferencia = new SigPrioridadPreferencia();
                sigPrioridadPreferencia.setIdSignoMarca(sigSignoMarca.getIdSignoMarca());
                sigPrioridadPreferencia.setIdLogTrans(logtrans);
                sigPrioridadPreferencia.setTipoPrioridad(prioridad.getTipoPrioridad());
                sigPrioridadPreferencia.setTipoInteres(prioridad.getTipoInteres());
                sigPrioridadPreferencia.setNombreNumero(prioridad.getNombre());
                sigPrioridadPreferencia.setFecha(prioridad.getFecha());
                sigPrioridadPreferencia.setIdSipi(prioridad.getId());

                //Obtener el dominio a partir del nombre
                if (prioridad.getTipoInteres().equals(EnumTipoInteres.EXTRANJERA.getCodigo())) {
                    sigPrioridadPreferencia.setLugar(dominioService.obtenerCodigoDominioPorNombre("pais", prioridad.getLugar()));
                } else {
                    sigPrioridadPreferencia.setLugar(prioridad.getLugar());
                }

                sigPrioridadPreferencia.setEstado(EnumEstado.ACTIVO.getCodigo());

                sigPrioridadPreferencia = sigPrioridadPreferenciaService.registrarSigPrioridadPreferencia(sigPrioridadPreferencia);

            }

            //guardar las direcciones
            for (DireccionNotificaciones direccion : formularioPI102.getDirecciones()) {

                SigDireccionDeNotificacion sigDireccionNotificacion = new SigDireccionDeNotificacion();

                sigDireccionNotificacion.setIdSignoMarca(sigSignoMarca.getIdSignoMarca());
                sigDireccionNotificacion.setIdLogTrans(logtrans);
                sigDireccionNotificacion.setCiudadNotificacion(direccion.getCiudadNotificacion());
                sigDireccionNotificacion.setZonaBarrio(direccion.getZonaBarrio());
                sigDireccionNotificacion.setAvenidaCalle(direccion.getAvenidaCalle());
                sigDireccionNotificacion.setNumero(direccion.getNumeroDomicilio());
                sigDireccionNotificacion.setEdificio(direccion.getNombreEdificio());
                sigDireccionNotificacion.setPiso(direccion.getPiso());
                sigDireccionNotificacion.setDepartamento(direccion.getDepartamento());
                sigDireccionNotificacion.setReferenciaDireccion(direccion.getReferencia());
                sigDireccionNotificacion.setCorreoElectronico(direccion.getCorreoelectronico());
                sigDireccionNotificacion.setTelefono(direccion.getTelefono());
                sigDireccionNotificacion.setCelular(direccion.getCelular());
                sigDireccionNotificacion.setEstado(EnumEstado.ACTIVO.getCodigo());

                sigDireccionNotificacion = sigDireccionNotificacionService.registrarSigDireccionNotificacion(sigDireccionNotificacion);
            }

            //guardar el solicitante y/o apoderado
            for (Solicitantes solicitantes : formularioPI102.getSolicitantes()) {
                SigSolicitanteApoderado sigSolicitanteApoderado = new SigSolicitanteApoderado();

                sigSolicitanteApoderado.setIdSignoMarca(sigSignoMarca.getIdSignoMarca());
                sigSolicitanteApoderado.setIdLogTrans(logtrans);
                sigSolicitanteApoderado.setTipoTitular(solicitantes.getTipoPersona());
                sigSolicitanteApoderado.setTipoPersona("SOLI");
                sigSolicitanteApoderado.setNombreRazonSocial(solicitantes.getNombreRazonSocial());
                sigSolicitanteApoderado.setPrimerApellido(solicitantes.getPrimerApellido());
                sigSolicitanteApoderado.setSegundoApellido(solicitantes.getSegundoApellido());
                sigSolicitanteApoderado.setNumeroDocumento(solicitantes.getNumeroDocumento());
                sigSolicitanteApoderado.setTipoDocumento(solicitantes.getTipoDocumento());
                sigSolicitanteApoderado.setLugarExpedicion(solicitantes.getLugarExpedicion());
                sigSolicitanteApoderado.setPais(solicitantes.getPais());
                sigSolicitanteApoderado.setGenero(solicitantes.getGenero());
                sigSolicitanteApoderado.setSolicitudDepartamento(solicitantes.getDepartamentoSolicitud());
                sigSolicitanteApoderado.setPoder(null);
                sigSolicitanteApoderado.setDireccion(solicitantes.getDomicilio());
                sigSolicitanteApoderado.setTelefono(solicitantes.getTelefono());
                sigSolicitanteApoderado.setCelular(solicitantes.getCelular());
                sigSolicitanteApoderado.setEmail(solicitantes.getCorreoElectronico());
                sigSolicitanteApoderado.setFax(null);
                sigSolicitanteApoderado.setEstado(EnumEstado.ACTIVO.getCodigo());
                sigSolicitanteApoderado.setIdSipi(solicitantes.getId());

                sigSolicitanteApoderado = sigSolicitanteApoderadoService.registrarSigSigSolicitanteApoderado(sigSolicitanteApoderado);
            }

            for (Apoderados apoderados : formularioPI102.getApoderados()) {
                SigSolicitanteApoderado sigSolicitanteApoderado = new SigSolicitanteApoderado();
                sigSolicitanteApoderado.setIdSignoMarca(sigSignoMarca.getIdSignoMarca());
                sigSolicitanteApoderado.setIdLogTrans(logtrans);
                sigSolicitanteApoderado.setTipoTitular("NAT");
                sigSolicitanteApoderado.setTipoPersona("APOD");
                sigSolicitanteApoderado.setNombreRazonSocial(apoderados.getNombres());
                sigSolicitanteApoderado.setPrimerApellido(apoderados.getPrimerApellido());
                sigSolicitanteApoderado.setSegundoApellido(apoderados.getSegundoApellido());
                sigSolicitanteApoderado.setNumeroDocumento(apoderados.getNumeroDocumento());
                sigSolicitanteApoderado.setTipoDocumento(apoderados.getTipoDocumento());
                sigSolicitanteApoderado.setLugarExpedicion(apoderados.getLugarExpedicion());
                sigSolicitanteApoderado.setPais(null);
                sigSolicitanteApoderado.setGenero(apoderados.getGenero());
                sigSolicitanteApoderado.setSolicitudDepartamento(null);
                sigSolicitanteApoderado.setPoder(apoderados.getNumeroPoder());
//                sigSolicitanteApoderado.setPoder();
                sigSolicitanteApoderado.setDireccion(apoderados.getDomicilio());
                sigSolicitanteApoderado.setTelefono(apoderados.getTelefono());
                sigSolicitanteApoderado.setCelular(apoderados.getCelular());
                sigSolicitanteApoderado.setEmail(apoderados.getCorreoElectronico());
                sigSolicitanteApoderado.setFax(null);
                sigSolicitanteApoderado.setEstado(EnumEstado.ACTIVO.getCodigo());
                sigSolicitanteApoderado.setIdSipi(apoderados.getId());

                sigSolicitanteApoderado = sigSolicitanteApoderadoService.registrarSigSigSolicitanteApoderado(sigSolicitanteApoderado);
            }

            //guardar el lema comercial
            LemasComerciales lemasComerciales = formularioPI102.getLemasComerciales();

            SigLemaComercial sigLemaComercial = new SigLemaComercial();

            sigLemaComercial.setIdSignoMarca(sigSignoMarca.getIdSignoMarca());
            sigLemaComercial.setSignoPadre(lemasComerciales.getNombreMarca());
            sigLemaComercial.setSmPadre(null);
            sigLemaComercial.setCodigoSmPadre(lemasComerciales.getNumeroSolicitudMarca());

            if (lemasComerciales.getNumeroRegistro() != null) {
                sigLemaComercial.setNumeroRegistroPadre(Long.parseLong(lemasComerciales.getNumeroRegistro()));
            } else {
                sigLemaComercial.setNumeroRegistroPadre(null);
            }

            sigLemaComercial.setSerieRegistroPadre(lemasComerciales.getSerie());
            if (lemasComerciales.getFechaVigente() != null) {
                sigLemaComercial.setVigencia(lemasComerciales.getFechaVigente().toString());
            } else {
                sigLemaComercial.setVigencia(null);
            }

            sigLemaComercial.setEstado(EnumEstado.ACTIVO.getCodigo());

            sigLemaComercial = sigLemaComercialService.guardarSigLemaComercial(sigLemaComercial);

            //guardar los registros del listado de requisitos
            List<DatoElementoFormulario> listaDatoElementoFormulario = new ArrayList<DatoElementoFormulario>();

            //Armar la lista
            for (ElementoFormularioTramite elementoFormularioTramite : plantillaVentanilla) {
                DatoElementoFormulario datoElementoFormulario = new DatoElementoFormulario();

                datoElementoFormulario.setNombreTabla("sigseguimiento");
                datoElementoFormulario.setIdSeguimiento(sigSeguimiento.getIdSeguimiento());
                datoElementoFormulario.setIdLogTrans(logtrans);
                datoElementoFormulario.setPestania(elementoFormularioTramite.getPestania());
                datoElementoFormulario.setSeccion(elementoFormularioTramite.getSeccion());
                datoElementoFormulario.setFila(elementoFormularioTramite.getFila());
                datoElementoFormulario.setTipoElemento(elementoFormularioTramite.getTipoElemento());
                datoElementoFormulario.setNombreElemento(elementoFormularioTramite.getNombreElemento());
                datoElementoFormulario.setOrden(elementoFormularioTramite.getOrden());
                datoElementoFormulario.setOrdenLiteral(elementoFormularioTramite.getOrdenLiteral());
                datoElementoFormulario.setIdpadre(elementoFormularioTramite.getIdpadre());
                datoElementoFormulario.setRespuesta(elementoFormularioTramite.getRespuesta());
                datoElementoFormulario.setOpcionRespuesta(elementoFormularioTramite.getOpcionRespuesta());
                datoElementoFormulario.setEstado(EnumEstado.ACTIVO.getCodigo());

                listaDatoElementoFormulario.add(datoElementoFormulario);
            }

            datoElementoFormularioService.guardarRegistrosPlantillaVentanilla(listaDatoElementoFormulario, EnumPrefijoTablas.SIGNO.getCodigo());

            //finalmente guardar la observacion del tramite.
            if (!observacion.equals("")) {
                //finalmente guardar la observacion del tramite.
                observacionTramiteService.registrarObservacionTramite(null, "SIG", sigSignoMarca.getIdSignoMarca(),
                        idUsuario, logtrans, Boolean.FALSE, fechaServidor,
                        "VENT", observacion, 1);
            }

            //Guardar el historial del ingreso del formulario PI102
            Historial historial = new Historial();
            historial.setIdLogTrans(logTransGuardado.getIdLogTrans());
            historial.setId(sigSignoMarca.getIdSignoMarca());
            historial.setIdUsuario(idUsuario);
            historial.setOperacion(EnumOperacion.ADICIONAR.getCodigo());
            historial.setEstadoMarcaDescripcion(dominioService.obtenerNombreCodigoDominio(EnumDominio.ESTADO_MARCA.getCodigo(), sigSignoMarca.getEstadoMarca()));
            historial.setDescripcion(EnumFormularioIngreso.PI102.getCodigo());
            historial.setFechaOperacion(fechaServidor);
            historial.setEstado(EnumEstado.ACTIVO.getCodigo());
            historialService.crudHistorial(historial, EnumPrefijoTablas.SIGNO.getCodigo(), 1);

            //realizar el registro en la tabla UsuarioTramite.
            UsuarioTramite usuarioTramite = new UsuarioTramite();

            //usuarioTramite.setIdUsuarioTramite(1L);
            usuarioTramite.setTipoTramite(EnumTipoTramiteUsuario.SIGNOS.getCodigo());
            usuarioTramite.setIdUsuarioExterno(formularioPI102.getFormularios().getIdUsuarioExterno());
            usuarioTramite.setId(sigSignoMarca.getIdSignoMarca());
            usuarioTramite.setIdLogTrans(logtrans);
            usuarioTramite.setEstado(EnumEstado.ACTIVO.getCodigo());
            usuarioTramiteService.crudUsuarioTramite(usuarioTramite, 1L);

            //Actualizar el estado del formulario
            formularioService.actualizarRegistroFormulario(formularioPI102.getFormularios().getId());

            mapResultado.put("nombreTabla", "sigseguimiento");
            mapResultado.put("idTabla", sigSeguimiento.getIdSeguimiento().toString());
            mapResultado.put("idSignoMarca", sigSignoMarca.getIdSignoMarca());

            return mapResultado;

        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    @Transactional
    public HashMap guardarFormularioPI103(Date fechaIngreso, FormularioPI103 formularioPI103, List<ElementoFormularioTramite> plantillaVentanilla,
            Long idUsuario, Long codigoSM, String oficina, String observacion,
            Long numero, Integer gestion, String siglaModificacion) throws Exception {

        HashMap mapResultado = new HashMap();
        try {
            Date fechaServidor = comunService.obtenerFechaHoraServidor(idRegional);
            List<Usuario> listUsuario = usuarioService.listaUsuarioXidPagina(idUsuario);
            LogTrans logTransGuardado = logTransService.crudLogTrans(new LogTrans(idUsuario, fechaServidor), 1);

            //obtener el logtrans con el que se realizara la transaccion
            Long logtrans = logTransGuardado.getIdLogTrans();

            //guardar el registro de modificacion
            ModModificacion modModificacion = new ModModificacion();

            modModificacion.setIdlogtrans(logtrans);
            modModificacion.setSigla(siglaModificacion);
            modModificacion.setNumero(numero);
            modModificacion.setGestion(gestion);
            modModificacion.setSm(formularioPI103.getModificaciones().getSm());
            modModificacion.setFecha_ingreso(fechaIngreso);
            modModificacion.setNro_formulario(formularioPI103.getFormularios().getNumeroFormulario());
            modModificacion.setOficina(oficina);
            modModificacion.setNumero_registro(formularioPI103.getModificaciones().getNumeroRegistro());
            modModificacion.setSerie_registro(formularioPI103.getModificaciones().getSerieRegistro());
            modModificacion.setNumero_renovacion(formularioPI103.getModificaciones().getNumeroRenovacion());
            modModificacion.setSerie_renovacion(formularioPI103.getModificaciones().getSerieRenovacion());
            modModificacion.setNumero_publicacion(formularioPI103.getModificaciones().getNumeroPublicacion());
            modModificacion.setSigno_registrado(formularioPI103.getModificaciones().getSignoRegistro());
            modModificacion.setIdclase_registrado(formularioPI103.getModificaciones().getClaseRegistrado().longValue());
            modModificacion.setTipo_genero_registrado(formularioPI103.getModificaciones().getTipoGeneroRegistrado());
            modModificacion.setMarca_acomp(formularioPI103.getModificaciones().getMarcaAcomp());
            modModificacion.setReg_lc_registrado(formularioPI103.getModificaciones().getRegLcRegistrado());
            modModificacion.setSerie_lc_registrado(formularioPI103.getModificaciones().getSerieLcRegistrado());
            modModificacion.setFecha_lc_registrado(formularioPI103.getModificaciones().getFechaLcRegistrado());
            modModificacion.setLuinicio(formularioPI103.getModificaciones().getLuInicio());
            modModificacion.setLu_fin(formularioPI103.getModificaciones().getLuFin());
            modModificacion.setTipo_modificacion(formularioPI103.getModificaciones().getTipoModificacion());
            modModificacion.setEstado_modificacion(EnumEstadoModificacion.INGRESADA.getCodigo());
            //modModificacion.setUbicacion_modificacion("MOD");
            modModificacion.setLista_producto(formularioPI103.getModificaciones().getListaProducto());
            modModificacion.setEstado(EnumEstado.ACTIVO.getCodigo());
            ModModificacion modModificacionIngresado = new ModModificacion();

            modModificacionIngresado = modModificacionService.guardar_modificar_listar_ModModificacion(modModificacion, 1);

            //guardar el seguimiento del tramite
            Seguimiento modSeguimiento = new Seguimiento();
            modSeguimiento.setId(modModificacionIngresado.getIdmodificacion());
            modSeguimiento.setIdUsuario(idUsuario);
            modSeguimiento.setIdLogTrans(logtrans);
            modSeguimiento.setSm(codigoSM);
            modSeguimiento.setEtapa("VENT");
            modSeguimiento.setFechaRecepcion(new Timestamp(fechaServidor.getTime()));
            modSeguimiento.setFechaFin(new Timestamp(fechaServidor.getTime()));
            modSeguimiento.setEditable(Boolean.FALSE);
            modSeguimiento.setEstado(EnumEstado.ACTIVO.getCodigo());
            modSeguimiento.setPlazoReal(0);
            modSeguimiento.setTotalTiempo(0L);
            modSeguimiento.setPlazo_limite(1);
            modSeguimiento.setDia_pasivo(0);

            Seguimiento modSeguimientoIngresado = new Seguimiento();

            modSeguimientoIngresado = seguimientoService.guardar_modificar_listar_Seguimiento(modSeguimiento, "MOD", 1);

            //guardar la direccion de notificacion
            //guardar las direcciones
            for (DireccionNotificaciones direccion : formularioPI103.getDirecciones()) {
                ModDireccionDeNotificacion modDireccionNotificacion = new ModDireccionDeNotificacion();
                modDireccionNotificacion.setIdmodificacion(modModificacionIngresado.getIdmodificacion());
                modDireccionNotificacion.setIdlogtrans(logtrans);
                modDireccionNotificacion.setCiudad_notificacion(direccion.getCiudadNotificacion());
                modDireccionNotificacion.setZona_barrio(direccion.getZonaBarrio());
                modDireccionNotificacion.setAvenida_calle(direccion.getAvenidaCalle());
                modDireccionNotificacion.setNumero(direccion.getNumeroDomicilio());
                modDireccionNotificacion.setEdificio(direccion.getNombreEdificio());
                modDireccionNotificacion.setPiso(direccion.getPiso());
                modDireccionNotificacion.setDepartamento(direccion.getDepartamento());
                modDireccionNotificacion.setReferencia_direccion(direccion.getReferencia());
                modDireccionNotificacion.setCorreo_electronico(direccion.getCorreoelectronico());
                modDireccionNotificacion.setTelefono(direccion.getTelefono());
                modDireccionNotificacion.setCelular(direccion.getCelular());
                modDireccionNotificacion.setEstado(EnumEstado.ACTIVO.getCodigo());
                ModDireccionDeNotificacion modDireccionNotificacionIngresado = new ModDireccionDeNotificacion();
                modDireccionNotificacionIngresado = modDireccionNotificacionService.guardar_modificar_listar_ModDireccionDeNotificacion(modDireccionNotificacion, modModificacionIngresado.getIdmodificacion(), 1, 1l);
            }

            //guardar el solicitante
            for (Solicitantes solicitantes : formularioPI103.getSolicitantes()) {
                ModSolicitanteApoderado modSolicitanteApoderado = new ModSolicitanteApoderado();

                modSolicitanteApoderado.setIdmodificacion(modModificacionIngresado.getIdmodificacion());
                modSolicitanteApoderado.setIdLogTrans(logtrans);
                modSolicitanteApoderado.setTipo_titular(solicitantes.getTipoPersona());
                modSolicitanteApoderado.setTipo_persona("SOLI");
                modSolicitanteApoderado.setNombre_razonsocial(solicitantes.getNombreRazonSocial());
                modSolicitanteApoderado.setPrimer_apellido(solicitantes.getPrimerApellido());
                modSolicitanteApoderado.setSegundo_apellido(solicitantes.getSegundoApellido());
                modSolicitanteApoderado.setNumero_documento(solicitantes.getNumeroDocumento());
                modSolicitanteApoderado.setTipo_documento(solicitantes.getTipoDocumento());
                modSolicitanteApoderado.setLugar_expedicion(solicitantes.getLugarExpedicion());
                modSolicitanteApoderado.setPais(solicitantes.getPais());
                modSolicitanteApoderado.setGenero(solicitantes.getGenero());
                modSolicitanteApoderado.setSolicitud_departamento(solicitantes.getDepartamentoSolicitud());
                modSolicitanteApoderado.setPoder(null);
                modSolicitanteApoderado.setDireccion(solicitantes.getDomicilio());
                modSolicitanteApoderado.setTelefono(solicitantes.getTelefono());
                modSolicitanteApoderado.setCelular(solicitantes.getCelular());
                modSolicitanteApoderado.setEmail(solicitantes.getCorreoElectronico());
                modSolicitanteApoderado.setFax(null);
                modSolicitanteApoderado.setEstado(EnumEstado.ACTIVO.getCodigo());
                modSolicitanteApoderado.setIdSipi(solicitantes.getId());

                ModSolicitanteApoderado modSolicitanteApoderadoIngresado = new ModSolicitanteApoderado();
                modSolicitanteApoderadoIngresado = modSolicitanteApoderadoService.guardar_modificar_listar_SolicitanteApoderado(modSolicitanteApoderado, 1);
            }

            //guardar el apoderado
            for (Apoderados apoderados : formularioPI103.getApoderados()) {
                ModSolicitanteApoderado modSolicitanteApoderado = new ModSolicitanteApoderado();
                modSolicitanteApoderado.setIdmodificacion(modModificacionIngresado.getIdmodificacion());
                modSolicitanteApoderado.setIdLogTrans(logtrans);
                modSolicitanteApoderado.setTipo_titular("NAT");
                modSolicitanteApoderado.setTipo_persona("APOD");
                modSolicitanteApoderado.setNombre_razonsocial(apoderados.getNombres());
                modSolicitanteApoderado.setPrimer_apellido(apoderados.getPrimerApellido());
                modSolicitanteApoderado.setSegundo_apellido(apoderados.getSegundoApellido());
                modSolicitanteApoderado.setNumero_documento(apoderados.getNumeroDocumento());
                modSolicitanteApoderado.setTipo_documento(apoderados.getTipoDocumento());
                modSolicitanteApoderado.setLugar_expedicion(apoderados.getLugarExpedicion());
                modSolicitanteApoderado.setPais(null);
                modSolicitanteApoderado.setGenero(apoderados.getGenero());
                modSolicitanteApoderado.setSolicitud_departamento(null);
                modSolicitanteApoderado.setPoder(apoderados.getNumeroPoder());
                modSolicitanteApoderado.setDireccion(apoderados.getDomicilio());
                modSolicitanteApoderado.setTelefono(apoderados.getTelefono());
                modSolicitanteApoderado.setCelular(apoderados.getCelular());
                modSolicitanteApoderado.setEmail(apoderados.getCorreoElectronico());
                modSolicitanteApoderado.setFax(null);
                modSolicitanteApoderado.setEstado(EnumEstado.ACTIVO.getCodigo());
                modSolicitanteApoderado.setIdSipi(apoderados.getId());

                ModSolicitanteApoderado modSolicitanteApoderadoIngresado = new ModSolicitanteApoderado();
                modSolicitanteApoderadoIngresado = modSolicitanteApoderadoService.guardar_modificar_listar_SolicitanteApoderado(modSolicitanteApoderado, 1);
            }

            //guardar la tabla modtitularlicenciatario
            for (TitularLicenciatarios titularLicenciatarios : formularioPI103.getTitularLicenciatarios()) {
                ModTitularLicenciatarioRegistrado modTitularLicenciatarioRegistrado = new ModTitularLicenciatarioRegistrado();
                modTitularLicenciatarioRegistrado.setIdmodificacion(modModificacionIngresado.getIdmodificacion());
                modTitularLicenciatarioRegistrado.setIdlogtrans(logtrans);
                modTitularLicenciatarioRegistrado.setTipo_persona_registrado(titularLicenciatarios.getTipoTitularRegistrado());
                modTitularLicenciatarioRegistrado.setTipo_titular(titularLicenciatarios.getTipoTitular());
                modTitularLicenciatarioRegistrado.setNombre_razonsocial(titularLicenciatarios.getNombreRazonSocial());
                modTitularLicenciatarioRegistrado.setDireccion(titularLicenciatarios.getDireccion());
                modTitularLicenciatarioRegistrado.setEstado(EnumEstado.ACTIVO.getCodigo());
                modTitularLicenciatarioRegistrado.setIdSipi(titularLicenciatarios.getId());

                ModTitularLicenciatarioRegistrado modTitularLicenciatarioRegistradoIngresado = new ModTitularLicenciatarioRegistrado();
                modTitularLicenciatarioRegistradoIngresado = modTitularLicenciatarioRegistradoService.guardar_modificar_listar_ModTitularLicenciatarioRegistrado(modTitularLicenciatarioRegistrado, 1);

            }

            //guardar la tabla modtitularlicenciatarionuevo
            for (TitularLicenciatariosNuevos titularLicenciatariosNuevos : formularioPI103.getTiTularLicenciatarioNuevos()) {
                ModTitularLicenciatarioNuevo modTitularLicenciatarioNuevo = new ModTitularLicenciatarioNuevo();

                modTitularLicenciatarioNuevo.setIdmodificacion(modModificacionIngresado.getIdmodificacion());
                modTitularLicenciatarioNuevo.setIdlogtrans(logtrans);
                modTitularLicenciatarioNuevo.setTipo_persona(titularLicenciatariosNuevos.getTipoPersona());
                modTitularLicenciatarioNuevo.setTipo_titular(titularLicenciatariosNuevos.getTipoTitular());
                modTitularLicenciatarioNuevo.setNombre_razonsocial(titularLicenciatariosNuevos.getNombreRazonSocial());
                modTitularLicenciatarioNuevo.setPrimer_apellido(titularLicenciatariosNuevos.getPrimerApellido());
                modTitularLicenciatarioNuevo.setSegundo_apellido(titularLicenciatariosNuevos.getSegundoApellido());
                modTitularLicenciatarioNuevo.setNumero_documento(titularLicenciatariosNuevos.getNroDocumento());
                modTitularLicenciatarioNuevo.setTipo_documento(titularLicenciatariosNuevos.getTipoDocumento());
                modTitularLicenciatarioNuevo.setLugar_expedicion(titularLicenciatariosNuevos.getLugarExpedicion());
                modTitularLicenciatarioNuevo.setPais(titularLicenciatariosNuevos.getPais());
                modTitularLicenciatarioNuevo.setSolicitud_departamento(titularLicenciatariosNuevos.getSolicitudDepartamento());
//                modTitularLicenciatarioNuevo.setPais_constitucion(titularLicenciatariosNuevos.getP);
                modTitularLicenciatarioNuevo.setGenero(titularLicenciatariosNuevos.getGenero());
                modTitularLicenciatarioNuevo.setSolicitud_departamento(titularLicenciatariosNuevos.getSolicitudDepartamento());
                modTitularLicenciatarioNuevo.setDireccion(titularLicenciatariosNuevos.getDireccion());
                modTitularLicenciatarioNuevo.setTelefono(titularLicenciatariosNuevos.getTelefono());
                modTitularLicenciatarioNuevo.setCelular(titularLicenciatariosNuevos.getCelular());
                modTitularLicenciatarioNuevo.setEmail(titularLicenciatariosNuevos.getEmail());
//                modTitularLicenciatarioNuevo.setFax(titularLicenciatariosNuevos.getF);
                modTitularLicenciatarioNuevo.setEstado(EnumEstado.ACTIVO.getCodigo());
                modTitularLicenciatarioNuevo.setModificar(Boolean.TRUE);
                modTitularLicenciatarioNuevo.setId_referencia(null);
                modTitularLicenciatarioNuevo.setIdSipi(titularLicenciatariosNuevos.getId());

                ModTitularLicenciatarioNuevo modTitularLicenciatarioNuevoRegistrado = new ModTitularLicenciatarioNuevo();
                modTitularLicenciatarioNuevoRegistrado = modTitularLicenciatarioNuevoService.guardar_modificar_listar_ModTitularLicenciatarioNuevo(modTitularLicenciatarioNuevo, 1);

            }

            //guardar los tipos de signo
            for (ModTipoSignos modTipoSignos : formularioPI103.getModTipoSignos()) {
                ModTipoSigno modTipoSigno = new ModTipoSigno();

                modTipoSigno.setIdmodificacion(modModificacionIngresado.getIdmodificacion());
                modTipoSigno.setIdlogtrans(logtrans);
                modTipoSigno.setTipo_signo(modTipoSignos.getTiposigno());
                modTipoSigno.setDescripcion_otro(modTipoSignos.getDescripcionotro());
                modTipoSigno.setEstado(EnumEstado.ACTIVO.getCodigo());
                modTipoSignoService.guardar_modificar_listar_ModTipoSigno(modTipoSigno, 1);
            }

            //guardar los registros del listado de requisitos
            List<DatoElementoFormulario> listaDatoElementoFormulario = new ArrayList<DatoElementoFormulario>();

            //Armar la lista
            for (ElementoFormularioTramite elementoFormularioTramite : plantillaVentanilla) {
                DatoElementoFormulario datoElementoFormulario = new DatoElementoFormulario();

                datoElementoFormulario.setNombreTabla("modseguimiento");
                datoElementoFormulario.setIdSeguimiento(modSeguimientoIngresado.getIdSeguimiento());
                datoElementoFormulario.setIdLogTrans(logtrans);
                datoElementoFormulario.setPestania(elementoFormularioTramite.getPestania());
                datoElementoFormulario.setSeccion(elementoFormularioTramite.getSeccion());
                datoElementoFormulario.setFila(elementoFormularioTramite.getFila());
                datoElementoFormulario.setTipoElemento(elementoFormularioTramite.getTipoElemento());
                datoElementoFormulario.setNombreElemento(elementoFormularioTramite.getNombreElemento());
                datoElementoFormulario.setOrden(elementoFormularioTramite.getOrden());
                datoElementoFormulario.setOrdenLiteral(elementoFormularioTramite.getOrdenLiteral());
                datoElementoFormulario.setIdpadre(elementoFormularioTramite.getIdpadre());
                datoElementoFormulario.setRespuesta(elementoFormularioTramite.getRespuesta());
                datoElementoFormulario.setOpcionRespuesta(elementoFormularioTramite.getOpcionRespuesta());
                datoElementoFormulario.setEstado(EnumEstado.ACTIVO.getCodigo());

                listaDatoElementoFormulario.add(datoElementoFormulario);
            }

            datoElementoFormularioService.guardarRegistrosPlantillaVentanilla(listaDatoElementoFormulario, EnumPrefijoTablas.MODIFICACION.getCodigo());

            //finalmente guardar la observacion del tramite.
            if (!observacion.equals("")) {
                //finalmente guardar la observacion del tramite.
                ObservacionTramite observacionTramite = new ObservacionTramite();
                observacionTramite.setId(modModificacionIngresado.getIdmodificacion());
                observacionTramite.setIdUsuario(idUsuario);
                observacionTramite.setIdLogTrans(logtrans);
                observacionTramite.setEditable(Boolean.TRUE);
                observacionTramite.setFechaObservacion(fechaServidor);
                observacionTramite.setEtapaTramite("VENT");
                observacionTramite.setDescripcion(observacion);
                observacionTramite.setEstado(EnumEstado.ACTIVO.getCodigo());
                observacionTramiteService.guardar_modificar_listar_ObservacionTramite(observacionTramite, "MOD", 1);
            }

            //Guardar el historial del ingreso del formulario PI103
            Historial historial = new Historial();
            historial.setIdLogTrans(logTransGuardado.getIdLogTrans());
            historial.setId(modModificacionIngresado.getIdmodificacion());
            historial.setIdUsuario(idUsuario);
            historial.setOperacion(EnumOperacion.ADICIONAR.getCodigo());
            historial.setEstadoMarcaDescripcion(dominioService.obtenerNombreCodigoDominio(EnumDominio.ESTADO_MODIFICACION.getCodigo(), modModificacionIngresado.getEstado_modificacion()));
            historial.setDescripcion(EnumFormularioIngreso.PI103.getCodigo());
            historial.setFechaOperacion(fechaServidor);
            historial.setEstado(EnumEstado.ACTIVO.getCodigo());
            historialService.crudHistorial(historial, EnumPrefijoTablas.MODIFICACION.getCodigo(), 1);

            //realizar el registro en la tabla UsuarioTramite.
            UsuarioTramite usuarioTramite = new UsuarioTramite();

            //usuarioTramite.setIdUsuarioTramite(1L);
            usuarioTramite.setTipoTramite(EnumTipoTramiteUsuario.MOD.getCodigo());
            usuarioTramite.setIdUsuarioExterno(formularioPI103.getFormularios().getIdUsuarioExterno());
            usuarioTramite.setId(modModificacionIngresado.getIdmodificacion());
            usuarioTramite.setIdLogTrans(logtrans);
            usuarioTramite.setEstado(EnumEstado.ACTIVO.getCodigo());
            usuarioTramiteService.crudUsuarioTramite(usuarioTramite, 1L);

            //Actualizar el estado del formulario
            formularioService.actualizarRegistroFormulario(formularioPI103.getFormularios().getId());
            return mapResultado;
        } catch (Exception e) {
            throw e;
        }

    }

    @Override
    @Transactional
    public HashMap guardarFormularioPI104(Date fechaIngreso, FormularioPI104 formularioPI104, List<ElementoFormularioTramite> plantillaVentanilla,
            Long idUsuario, Long codigoSM, String oficina, String observacion,
            Long numero, Integer gestion) throws Exception {
        HashMap mapResultado = new HashMap();
        try {
            Date fechaServidor = comunService.obtenerFechaHoraServidor(idRegional);
            List<Usuario> listUsuario = usuarioService.listaUsuarioXidPagina(idUsuario);
            LogTrans logTransGuardado = logTransService.crudLogTrans(new LogTrans(idUsuario, fechaServidor), 1);

            //obtener el logtrans con el que se realizara la transaccion
            Long logtrans = logTransGuardado.getIdLogTrans();

            //guardar la solicitud
            RenSolicitudRenovacion renSolicitudRenovacion = new RenSolicitudRenovacion();

            renSolicitudRenovacion.setIdlogtrans(logtrans);
            renSolicitudRenovacion.setTipo_genero(formularioPI104.getSolicitudRenovaciones().getTipoGenero());
            renSolicitudRenovacion.setSigno_registrado(formularioPI104.getSolicitudRenovaciones().getSignoRegistrado());
            //se cambio el numero por id en la base guardado en el campo getClaseNizaRegistrado
            renSolicitudRenovacion.setIdclase_niza_registrado(formularioPI104.getSolicitudRenovaciones().getClaseNizaRegistrado().longValue());
            renSolicitudRenovacion.setNumero_registro_registrado(formularioPI104.getSolicitudRenovaciones().getNumeroRegistro());
            renSolicitudRenovacion.setSerie_registro_registrado(formularioPI104.getSolicitudRenovaciones().getSerieRegistro());
            renSolicitudRenovacion.setFecha_registro_registrado(formularioPI104.getSolicitudRenovaciones().getFechaOtorgacionMarca());
            renSolicitudRenovacion.setSr(numero);
            renSolicitudRenovacion.setGestion_sr(gestion);
            renSolicitudRenovacion.setFecha_ingreso(fechaIngreso);
            renSolicitudRenovacion.setNumero_formulario(Long.valueOf(formularioPI104.getFormularios().getNumeroFormulario()));
            renSolicitudRenovacion.setOficina(oficina);

            //renSolicitudRenovacion.setFecha_registro_registrado(formularioPI104.getSolicitudRenovaciones().get(0).getFechaOtorgacionMarca());
            if (formularioPI104.getSolicitudRenovaciones().getNumeroUltimaRenovacion() != null) {
                renSolicitudRenovacion.setNumero_ultima_renovacion(formularioPI104.getSolicitudRenovaciones().getNumeroUltimaRenovacion());
            } else {
                renSolicitudRenovacion.setNumero_ultima_renovacion(formularioPI104.getSolicitudRenovaciones().getNumeroUltimaRenovacion());
            }

            if (formularioPI104.getSolicitudRenovaciones().getNumeroPenultimaRenovacion() != null) {
                renSolicitudRenovacion.setNumero_penultima_renovacion(formularioPI104.getSolicitudRenovaciones().getNumeroPenultimaRenovacion());
            } else {
                renSolicitudRenovacion.setNumero_penultima_renovacion(0);
            }
            if (formularioPI104.getSolicitudRenovaciones().getListaProductosLimitacion() != null) {
                renSolicitudRenovacion.setLista_productos_limitacion(formularioPI104.getSolicitudRenovaciones().getListaProductosLimitacion());
            } else {
                renSolicitudRenovacion.setLista_productos_limitacion(null);
            }

            if (formularioPI104.getSolicitudRenovaciones().getClaseNizaReclasificacion() != null) {

                if (formularioPI104.getSolicitudRenovaciones().getClaseNizaReclasificacion() != null) {
                    renSolicitudRenovacion.setIdclase_niza_reclasificacion(formularioPI104.getSolicitudRenovaciones().getClaseNizaReclasificacion().longValue());
                }

            } else {
                renSolicitudRenovacion.setIdclase_niza_reclasificacion(null);
            }

            renSolicitudRenovacion.setEstado(EnumEstado.ACTIVO.getCodigo());

            RenSolicitudRenovacion renSolicitudRenovacionRegistrado = renSolicitudRenovacionService.cruddosRenSolictudRenovacion(renSolicitudRenovacion, 1);

            //guardar los titulares registrados
            for (RenTitularRegistrados renTitularRegistrados : formularioPI104.getRenTitularRegistrados()) {
                RenTitularRegistrado renTitularRegistrado = new RenTitularRegistrado();
                renTitularRegistrado.setIdsolicitudrenovacion(renSolicitudRenovacionRegistrado.getIdsolicitudrenovacion());
                renTitularRegistrado.setIdlogtrans(logtrans);
                renTitularRegistrado.setNombre_razonsocial(renTitularRegistrados.getNombreRazonSocial());
                renTitularRegistrado.setPrimer_apellido(renTitularRegistrados.getPrimerApellido());
                renTitularRegistrado.setSegundo_apellido(renTitularRegistrados.getSegundoApellido());
                renTitularRegistrado.setDireccion(renTitularRegistrados.getDireccion());
                renTitularRegistrado.setEstado(EnumEstado.ACTIVO.getCodigo());
                renTitularRegistrado.setIdSipi(renTitularRegistrados.getId());

                RenTitularRegistrado renNuevoTitularRegistrado = renTitularRegistradoService.crudDosRenTitularRegistrado(renTitularRegistrado, 1);

            }

            //guardar el seguimiento del tramite
            Seguimiento renSeguimiento = new Seguimiento();
            renSeguimiento.setId(renSolicitudRenovacionRegistrado.getIdsolicitudrenovacion());
            renSeguimiento.setIdUsuario(idUsuario);
            renSeguimiento.setIdLogTrans(logtrans);
            renSeguimiento.setSm(null);
            renSeguimiento.setEtapa("VENT");
            renSeguimiento.setFechaRecepcion(new Timestamp(fechaServidor.getTime()));
            renSeguimiento.setFechaFin(new Timestamp(fechaServidor.getTime()));
            renSeguimiento.setEditable(Boolean.FALSE);
            renSeguimiento.setEstado(EnumEstado.ACTIVO.getCodigo());
            renSeguimiento.setPlazoReal(0);
            renSeguimiento.setTotalTiempo(0L);
            renSeguimiento.setPlazo_limite(1);
            renSeguimiento.setDia_pasivo(0);
            Seguimiento renSeguimientoIngresado = new Seguimiento();
            renSeguimientoIngresado = seguimientoService.guardar_modificar_listar_Seguimiento(renSeguimiento, "REN", 1);

            //guardar la direccion de notificacion
            //guardar las direcciones
            for (DireccionNotificaciones direccion : formularioPI104.getDirecciones()) {
                RenDireccionDeNotificacion renDireccionNotificacion = new RenDireccionDeNotificacion();
                renDireccionNotificacion.setIdsolicitudrenovacion(renSolicitudRenovacionRegistrado.getIdsolicitudrenovacion());
                renDireccionNotificacion.setIdlogtrans(logtrans);
                renDireccionNotificacion.setCiudad_notificacion(direccion.getCiudadNotificacion());
                renDireccionNotificacion.setZona_barrio(direccion.getZonaBarrio());
                renDireccionNotificacion.setAvenida_calle(direccion.getAvenidaCalle());
                renDireccionNotificacion.setNumero(direccion.getNumeroDomicilio());
                renDireccionNotificacion.setEdificio(direccion.getNombreEdificio());
                renDireccionNotificacion.setPiso(direccion.getPiso());
                renDireccionNotificacion.setDepartamento(direccion.getDepartamento());
                renDireccionNotificacion.setReferencia_direccion(direccion.getReferencia());
                renDireccionNotificacion.setCorreo_electronico(direccion.getCorreoelectronico());
                renDireccionNotificacion.setTelefono(direccion.getTelefono());
                renDireccionNotificacion.setCelular(direccion.getCelular());
                renDireccionNotificacion.setEstado(EnumEstado.ACTIVO.getCodigo());
                RenDireccionDeNotificacion renDireccionNotificacionIngresado = new RenDireccionDeNotificacion();
                renDireccionNotificacionIngresado = renDireccionDeNotificacionService.crudDosRenDireccionDeNotificacion(renDireccionNotificacion, 1);

            }

            //guardar el solicitante
            for (Solicitantes solicitantes : formularioPI104.getSolicitantes()) {
                RenSolicitanteApoderado renSolicitanteApoderado = new RenSolicitanteApoderado();

                renSolicitanteApoderado.setIdsolicitudrenovacion(renSolicitudRenovacionRegistrado.getIdsolicitudrenovacion());
                renSolicitanteApoderado.setIdlogtrans(logtrans);
                renSolicitanteApoderado.setTipo_titular(solicitantes.getTipoPersona());
                renSolicitanteApoderado.setTipo_persona("SOLI");
                renSolicitanteApoderado.setNombre_razonsocial(solicitantes.getNombreRazonSocial());
                renSolicitanteApoderado.setPrimer_apellido(solicitantes.getPrimerApellido());
                renSolicitanteApoderado.setSegundo_apellido(solicitantes.getSegundoApellido());
                renSolicitanteApoderado.setNumero_documento(solicitantes.getNumeroDocumento());
                renSolicitanteApoderado.setTipo_documento(solicitantes.getTipoDocumento());
                renSolicitanteApoderado.setLugar_expedicion(solicitantes.getLugarExpedicion());
                renSolicitanteApoderado.setPais(solicitantes.getPais());
                renSolicitanteApoderado.setGenero(solicitantes.getGenero());
                renSolicitanteApoderado.setSolicitud_departamento(solicitantes.getDepartamentoSolicitud());
                renSolicitanteApoderado.setPoder(null);
                renSolicitanteApoderado.setDireccion(solicitantes.getDomicilio());
                renSolicitanteApoderado.setTelefono(solicitantes.getTelefono());
                renSolicitanteApoderado.setCelular(solicitantes.getCelular());
                renSolicitanteApoderado.setEmail(solicitantes.getCorreoElectronico());
                renSolicitanteApoderado.setFax(null);
                renSolicitanteApoderado.setEstado(EnumEstado.ACTIVO.getCodigo());
                renSolicitanteApoderado.setIdSipi(solicitantes.getId());

                RenSolicitanteApoderado renSolicitanteApoderadoIngresado = new RenSolicitanteApoderado();
                renSolicitanteApoderadoIngresado = renSolicitanteApoderadoService.crudDosRenSolicitanteApoderado(renSolicitanteApoderado, 1);

            }

            //guardar el apoderado
            for (Apoderados apoderados : formularioPI104.getApoderados()) {
                RenSolicitanteApoderado renSolicitanteApoderado = new RenSolicitanteApoderado();
                renSolicitanteApoderado.setIdsolicitudrenovacion(renSolicitudRenovacionRegistrado.getIdsolicitudrenovacion());
                renSolicitanteApoderado.setIdlogtrans(logtrans);
                renSolicitanteApoderado.setTipo_titular("NAT");
                renSolicitanteApoderado.setTipo_persona("APOD");
                renSolicitanteApoderado.setNombre_razonsocial(apoderados.getNombres());
                renSolicitanteApoderado.setPrimer_apellido(apoderados.getPrimerApellido());
                renSolicitanteApoderado.setSegundo_apellido(apoderados.getSegundoApellido());
                renSolicitanteApoderado.setNumero_documento(apoderados.getNumeroDocumento());
                renSolicitanteApoderado.setTipo_documento(apoderados.getTipoDocumento());
                renSolicitanteApoderado.setLugar_expedicion(apoderados.getLugarExpedicion());
                renSolicitanteApoderado.setPais(null);
                renSolicitanteApoderado.setGenero(apoderados.getGenero());
                renSolicitanteApoderado.setSolicitud_departamento(null);
                renSolicitanteApoderado.setPoder(apoderados.getNumeroPoder());
                renSolicitanteApoderado.setDireccion(apoderados.getDomicilio());
                renSolicitanteApoderado.setTelefono(apoderados.getTelefono());
                renSolicitanteApoderado.setCelular(apoderados.getCelular());
                renSolicitanteApoderado.setEmail(apoderados.getCorreoElectronico());
                renSolicitanteApoderado.setFax(null);
                renSolicitanteApoderado.setEstado(EnumEstado.ACTIVO.getCodigo());
                renSolicitanteApoderado.setIdSipi(apoderados.getId());

                RenSolicitanteApoderado renSolicitanteApoderadoIngresado = new RenSolicitanteApoderado();
                renSolicitanteApoderadoIngresado = renSolicitanteApoderadoService.crudDosRenSolicitanteApoderado(renSolicitanteApoderado, 1);
            }

            //guardar los tipos de signo
            for (RenTipoSignos modTipoSignos : formularioPI104.getRenTipoSignos()) {

                RenTipoSigno renTipoSigno = new RenTipoSigno();

                renTipoSigno.setIdsolicitudrenovacion(renSolicitudRenovacionRegistrado.getIdsolicitudrenovacion());
                renTipoSigno.setIdlogtrans(logtrans);
                renTipoSigno.setTipo_signo(modTipoSignos.getTiposigno());
                renTipoSigno.setDescripcion_otro(modTipoSignos.getDescripcionotro());
                renTipoSigno.setEstado(EnumEstado.ACTIVO.getCodigo());

                renTipoSignoService.crudRenTipoSigno(renTipoSigno, 1);

            }

            //guardar los registros del listado de requisitos
            List<DatoElementoFormulario> listaDatoElementoFormulario = new ArrayList<DatoElementoFormulario>();

            //Armar la lista
            for (ElementoFormularioTramite elementoFormularioTramite : plantillaVentanilla) {
                DatoElementoFormulario datoElementoFormulario = new DatoElementoFormulario();

                datoElementoFormulario.setNombreTabla("renseguimiento");
                datoElementoFormulario.setIdSeguimiento(renSeguimientoIngresado.getIdSeguimiento());
                datoElementoFormulario.setIdLogTrans(logtrans);
                datoElementoFormulario.setPestania(elementoFormularioTramite.getPestania());
                datoElementoFormulario.setSeccion(elementoFormularioTramite.getSeccion());
                datoElementoFormulario.setFila(elementoFormularioTramite.getFila());
                datoElementoFormulario.setTipoElemento(elementoFormularioTramite.getTipoElemento());
                datoElementoFormulario.setNombreElemento(elementoFormularioTramite.getNombreElemento());
                datoElementoFormulario.setOrden(elementoFormularioTramite.getOrden());
                datoElementoFormulario.setOrdenLiteral(elementoFormularioTramite.getOrdenLiteral());
                datoElementoFormulario.setIdpadre(elementoFormularioTramite.getIdpadre());
                datoElementoFormulario.setRespuesta(elementoFormularioTramite.getRespuesta());
                datoElementoFormulario.setOpcionRespuesta(elementoFormularioTramite.getOpcionRespuesta());
                datoElementoFormulario.setEstado(EnumEstado.ACTIVO.getCodigo());

                listaDatoElementoFormulario.add(datoElementoFormulario);
            }

            datoElementoFormularioService.guardarRegistrosPlantillaVentanilla(listaDatoElementoFormulario, EnumPrefijoTablas.RENOVACION.getCodigo());

            if (!observacion.equals("")) {
                //finalmente guardar la observacion del tramite.
                ObservacionTramite observacionTramite = new ObservacionTramite();
                observacionTramite.setId(renSolicitudRenovacionRegistrado.getIdsolicitudrenovacion());
                observacionTramite.setIdUsuario(idUsuario);
                observacionTramite.setIdLogTrans(logtrans);
                observacionTramite.setEditable(Boolean.TRUE);
                observacionTramite.setFechaObservacion(fechaServidor);
                observacionTramite.setEtapaTramite("VENT");
                observacionTramite.setDescripcion(observacion);
                observacionTramite.setEstado(EnumEstado.ACTIVO.getCodigo());
                observacionTramiteService.guardar_modificar_listar_ObservacionTramite(observacionTramite, "REN", 1);
            }

            //Guardar el historial del ingreso del formulario PI102
            Historial historial = new Historial();
            historial.setIdLogTrans(logTransGuardado.getIdLogTrans());
            historial.setId(renSolicitudRenovacionRegistrado.getIdsolicitudrenovacion());
            historial.setIdUsuario(idUsuario);
            historial.setOperacion(EnumOperacion.ADICIONAR.getCodigo());
            historial.setEstadoMarcaDescripcion(dominioService.obtenerNombreCodigoDominio(EnumDominio.ESTADO_RENOVACION.getCodigo(), EnumEstadoRenovacion.SOLICITADO.getCodigo()));
            historial.setDescripcion(EnumFormularioIngreso.PI104.getCodigo());
            historial.setFechaOperacion(fechaServidor);
            historial.setEstado(EnumEstado.ACTIVO.getCodigo());
            historialService.crudHistorial(historial, EnumPrefijoTablas.RENOVACION.getCodigo(), 1);

            //realizar el registro en la tabla UsuarioTramite.
            UsuarioTramite usuarioTramite = new UsuarioTramite();

            //usuarioTramite.setIdUsuarioTramite(1L);
            usuarioTramite.setTipoTramite(EnumTipoTramiteUsuario.RENOVACION.getCodigo());
            usuarioTramite.setIdUsuarioExterno(formularioPI104.getFormularios().getIdUsuarioExterno());
            usuarioTramite.setId(renSolicitudRenovacionRegistrado.getIdsolicitudrenovacion());
            usuarioTramite.setIdLogTrans(logtrans);
            usuarioTramite.setEstado(EnumEstado.ACTIVO.getCodigo());
            usuarioTramiteService.crudUsuarioTramite(usuarioTramite, 1L);

            //Actualizar el estado del formulario
            formularioService.actualizarRegistroFormulario(formularioPI104.getFormularios().getId());

            return mapResultado;

        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    @Transactional
    public HashMap guardarFormularioPI105(Date fechaIngreso, FormularioPI105 formularioPI105, List<ElementoFormularioTramite> plantillaVentanilla,
            Long idUsuario, Long codigoSM, String oficina, String observacion,
            Long numero, Integer gestion) throws Exception {

        Integer nroopodmteaux = 0;
        Oposicion datosoposicion = new Oposicion();
        OpoMarcademandada datosmarcademandada = new OpoMarcademandada();
        List<OpoSolicitanteapoderado> listasolidmdo = new ArrayList<OpoSolicitanteapoderado>();
        List<OpoSolicitanteapoderado> listaapodmdo = new ArrayList<OpoSolicitanteapoderado>();
        List<OpoSolicitanteapoderado> listasolidmte = new ArrayList<OpoSolicitanteapoderado>();
        List<OpoSolicitanteapoderado> listaapodmte = new ArrayList<OpoSolicitanteapoderado>();
        SigDireccionDeNotificacion notificaciondatossig = new SigDireccionDeNotificacion();
        OpoNotificacion datosnotificaciondmdo = new OpoNotificacion();
        OpoNotificacion datosnotificaciondmte = new OpoNotificacion();
        OpoMarcademandante datosmarcademandante = new OpoMarcademandante();
        OpoObjetoDmteDmdoNotiSoliapo enlazaobjetos = new OpoObjetoDmteDmdoNotiSoliapo();

        OpoObjetoDmteDmdoNotiSoliapo objetogeneral = new OpoObjetoDmteDmdoNotiSoliapo();
        OpoEvento datosopoevento = new OpoEvento();
        OpoFechalimite datosfechalimite = new OpoFechalimite();
        OpoHistorial datoshistorial = new OpoHistorial();

        HashMap mapResultado = new HashMap();
        try {

            Date fechaServidor = comunService.obtenerFechaHoraServidor(idRegional);
            List<Usuario> listUsuario = usuarioService.listaUsuarioXidPagina(idUsuario);
            LogTrans logTransGuardado = logTransService.crudLogTrans(new LogTrans(idUsuario, fechaServidor), 1);

            //obtener el logtrans con el que se realizara la transaccion
            Long logtrans = logTransGuardado.getIdLogTrans();

            // Comienza el guardado
            datosoposicion.setNro_opo((formularioPI105.getOpoMarcademandadaExt().getDmdo_public()).longValue()); //numero opo del demandado
            datosoposicion.setFecha_presentacion(fechaIngreso); //del formulario
            datosoposicion.setGaceta(formularioPI105.getOpoMarcademandadaExt().getGaceta());//numero gaceta de demandado
            datosoposicion.setObservacion(observacion);
            datosoposicion.setId_estado(2L);
            datosoposicion.setEstadoopo("AC");
            datosoposicion.setNumero_formulario(Long.parseLong(formularioPI105.getFormularios().getNumeroFormulario()));
            datosoposicion.setGestion_opo(gestion);
            datosoposicion.setCodigo_opo(numero);
            datosoposicion.setIngreso_opo("NI");
            datosoposicion.setOficina(oficina);

            datosoposicion.setOrden_o(oposicionService.extraerultimonorden((formularioPI105.getOpoMarcademandadaExt().getDmdo_public()).longValue()) + 1);
            nroopodmteaux = oposicionService.extraerultimonorden((formularioPI105.getOpoMarcademandadaExt().getDmdo_public()).longValue()) + 1;

            SigSignoMarca encontradoSigno = sigSignoMarcaService.listaSigSignoMarcaXPublicacion((formularioPI105.getOpoMarcademandadaExt().getDmdo_public()).longValue());
            if (encontradoSigno != null) {

                datosmarcademandada.setDmdo_public(formularioPI105.getOpoMarcademandadaExt().getDmdo_public());
                datosmarcademandada.setNro_opo(formularioPI105.getOpoMarcademandadaExt().getDmdo_public()); //numero opo demandada
                datosmarcademandada.setGaceta((encontradoSigno.getNumeroGaceta()).intValue());//gaseta demandada
                datosmarcademandada.setFecha_public(encontradoSigno.getFechaPublicacion());
                datosmarcademandada.setDmdo_marca_lnv(encontradoSigno.getSigno()); //la marca de del tramite
                datosmarcademandada.setIdlogtrans(logTransGuardado.getIdLogTrans());
                datosmarcademandada.setEstado("AC");

                List<SigSolicitanteApoderado> listaSolicitanteApoderadoSig = sigSolicitanteApoderadoService.listadoSolicitanteXidsignomarca(encontradoSigno.getIdSignoMarca());

                for (SigSolicitanteApoderado item : listaSolicitanteApoderadoSig) {
                    try {

                        OpoSolicitanteapoderado titularRegistrado = new OpoSolicitanteapoderado();
                        //  String nombrex = devuelveNombreJuridicoONatural(item.getNombreRazonSocial(), item.getPrimerApellido(), item.getSegundoApellido());
                        titularRegistrado.setNombre_razonsocial(item.getNombreRazonSocial());
                        titularRegistrado.setPrimer_apellido(item.getPrimerApellido());
                        titularRegistrado.setSegundo_apellido(item.getSegundoApellido());

                        titularRegistrado.setDireccion(item.getDireccion());
                        titularRegistrado.setTipo_documento(item.getTipoDocumento());
                        titularRegistrado.setLugar_expedicion(item.getLugarExpedicion());
                        titularRegistrado.setNumero_documento(item.getNumeroDocumento());
                        titularRegistrado.setPais(item.getPais());
                        titularRegistrado.setSolicitud_depa(item.getSolicitudDepartamento());
                        titularRegistrado.setDireccion(item.getDireccion());
                        titularRegistrado.setCelular(item.getCelular());
                        titularRegistrado.setTelefono(item.getTelefono());
                        titularRegistrado.setFax(item.getFax());
                        titularRegistrado.setGenero(item.getGenero());
                        titularRegistrado.setEmail(item.getEmail());
                        titularRegistrado.setTiposoliapo("DMDO");
                        titularRegistrado.setTipo_persona("SOLI");
                        titularRegistrado.setEstado("AC");

                        listasolidmdo.add(titularRegistrado);

                    } catch (Exception ex) {
                        Logger.getLogger(IngresoFormularioServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                List<SigSolicitanteApoderado> listaApoderadoSig = sigSolicitanteApoderadoService.listadoApoderadoXidsignomarca(encontradoSigno.getIdSignoMarca());

                for (SigSolicitanteApoderado listaapo : listaApoderadoSig) {
                    try {

                        OpoSolicitanteapoderado titularRegistrado = new OpoSolicitanteapoderado();
                        // String nombrex = devuelveNombreJuridicoONatural(listaapo.getNombreRazonSocial(), listaapo.getPrimerApellido(), listaapo.getSegundoApellido());
                        titularRegistrado.setNombre_razonsocial(listaapo.getNombreRazonSocial());
                        titularRegistrado.setPrimer_apellido(listaapo.getPrimerApellido());
                        titularRegistrado.setSegundo_apellido(listaapo.getSegundoApellido());
                        titularRegistrado.setDireccion(listaapo.getDireccion());
                        titularRegistrado.setTipo_documento(listaapo.getTipoDocumento());
                        titularRegistrado.setLugar_expedicion(listaapo.getLugarExpedicion());
                        titularRegistrado.setNumero_documento(listaapo.getNumeroDocumento());
                        titularRegistrado.setGenero(listaapo.getGenero());
                        titularRegistrado.setPais(listaapo.getPais());
                        titularRegistrado.setSolicitud_depa(listaapo.getSolicitudDepartamento());
                        titularRegistrado.setDireccion(listaapo.getDireccion());
                        titularRegistrado.setCelular(listaapo.getCelular());
                        titularRegistrado.setTelefono(listaapo.getTelefono());
                        titularRegistrado.setFax(listaapo.getFax());
                        titularRegistrado.setEmail(listaapo.getEmail());
                        titularRegistrado.setPoder(listaapo.getPoder());
                        titularRegistrado.setTiposoliapo("DMDO");
                        titularRegistrado.setTipo_persona("APOD");
                        titularRegistrado.setEstado("AC");
                        listaapodmdo.add(titularRegistrado);

                    } catch (Exception ex) {
                        Logger.getLogger(IngresoFormularioServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                notificaciondatossig = sigDireccionDeNotificacionService.obtenerDireccionNotificacionXSignoMarca(encontradoSigno.getIdSignoMarca());

                datosnotificaciondmdo.setCiudad_notificacion(notificaciondatossig.getCiudadNotificacion());
                datosnotificaciondmdo.setZona_barrio(notificaciondatossig.getZonaBarrio());
                datosnotificaciondmdo.setAvenida_calle(notificaciondatossig.getAvenidaCalle());
                datosnotificaciondmdo.setNumero(notificaciondatossig.getNumero());
                datosnotificaciondmdo.setCelular(notificaciondatossig.getCelular());
                datosnotificaciondmdo.setEdificio(notificaciondatossig.getEdificio());
                datosnotificaciondmdo.setPiso(notificaciondatossig.getPiso());
                datosnotificaciondmdo.setNumero_departamento(notificaciondatossig.getDepartamento());
                datosnotificaciondmdo.setTelefono(notificaciondatossig.getTelefono());
                datosnotificaciondmdo.setEmail(notificaciondatossig.getCorreoElectronico());
                datosnotificaciondmdo.setReferencia_direccion(notificaciondatossig.getReferenciaDireccion());
                datosnotificaciondmdo.setEstado("AC");

                enlazaobjetos.setObjopsicion(datosoposicion);
                enlazaobjetos.setObjmarcademandada(datosmarcademandada);
                enlazaobjetos.setListaapodmdo(listaapodmdo);
                enlazaobjetos.setListasolidmdo(listasolidmdo);
                enlazaobjetos.setObjnotificaciondmda(datosnotificaciondmdo);

                //  opoGeneralService.guardartramiteopo(enlazaobjetos);
            }
            System.out.println("================---------------------->" + formularioPI105.getOpomarcaDemandanteExt().getDmte_sm());
            /*Validar para los SM si existen*/
            int swdmte = 0;
            if (formularioPI105.getOpomarcaDemandanteExt().getDmte_sm() != null) {//&& gestionExpediente != null
                if (formularioPI105.getOpomarcaDemandanteExt().getDmte_sm() != 0) {

                    SigSignoMarca encontradoSignodmte = sigSignoMarcaService.listaSigSignoMarcaXSM(formularioPI105.getOpomarcaDemandanteExt().getDmte_sm());
                    System.out.println("=========XXXXXXXXXXXXX========$$$$$$$$$$$$$$$$>" + encontradoSignodmte);

                    if (encontradoSignodmte != null) {

                        if (encontradoSignodmte.getSm() != null) {
                            datosmarcademandante.setDmte_sm((encontradoSignodmte.getSm()));
                        }
                        if (encontradoSignodmte.getNumeroPublicacion() != null) {
                            datosmarcademandante.setDmte_public((encontradoSignodmte.getNumeroPublicacion()).intValue());
                        }
                        if (encontradoSignodmte.getNumeroRegistro() != null) {
                            datosmarcademandante.setDmte_reg((encontradoSignodmte.getNumeroRegistro()).intValue());
                        }
                        if (encontradoSignodmte.getSerieRegistro() != null) {
                            datosmarcademandante.setDmte_serie(encontradoSignodmte.getSerieRegistro());
                        }
                        if (encontradoSignodmte.getSigno() != null) {
                            datosmarcademandante.setDmte_marca_lnv(encontradoSignodmte.getSigno());
                        }
                    }

                    datosmarcademandante.setIdlogtrans(logTransGuardado.getIdLogTrans());
                    datosmarcademandante.setEstado("AC");

                    List<Solicitantes> listaSolicitanteApoderadoSigdmte = formularioPI105.getSolicitantes();

                    for (Solicitantes item : listaSolicitanteApoderadoSigdmte) {
                        try {

                            OpoSolicitanteapoderado titularRegistrado = new OpoSolicitanteapoderado();
                            // String nombrex = devuelveNombreJuridicoONatural(item.getNombreRazonSocial(), item.getPrimerApellido(), item.getSegundoApellido());

                            titularRegistrado.setNombre_razonsocial(item.getNombreRazonSocial());
                            titularRegistrado.setPrimer_apellido(item.getPrimerApellido());
                            titularRegistrado.setSegundo_apellido(item.getSegundoApellido());
                            titularRegistrado.setDireccion(item.getDomicilio());
                            titularRegistrado.setTipo_documento(item.getTipoDocumento());
                            titularRegistrado.setNumero_documento(item.getNumeroDocumento());
                            titularRegistrado.setLugar_expedicion(item.getLugarExpedicion());
                            titularRegistrado.setPais(item.getPais());
                            titularRegistrado.setGenero(item.getGenero());
                            titularRegistrado.setSolicitud_depa(item.getDepartamentoSolicitud());
                            titularRegistrado.setCelular(item.getCelular());
                            titularRegistrado.setTelefono(item.getTelefono());
                            titularRegistrado.setEmail(item.getCorreoElectronico());
                            titularRegistrado.setTiposoliapo("DMTE");
                            titularRegistrado.setTipo_persona("SOLI");
                            titularRegistrado.setEstado("AC");

                            listasolidmte.add(titularRegistrado);

                        } catch (Exception ex) {
                            Logger.getLogger(IngresoFormularioServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    List<Apoderados> listaApoderadoSigdmte = formularioPI105.getApoderados();

                    for (Apoderados listaapo : listaApoderadoSigdmte) {
                        try {

                            OpoSolicitanteapoderado titularRegistrado = new OpoSolicitanteapoderado();
                            //  String nombrex = devuelveNombreJuridicoONatural(listaapo.getNombreRazonSocial(), listaapo.getPrimerApellido(), listaapo.getSegundoApellido());
                            titularRegistrado.setNombre_razonsocial(listaapo.getNombres());
                            titularRegistrado.setPrimer_apellido(listaapo.getPrimerApellido());
                            titularRegistrado.setSegundo_apellido(listaapo.getSegundoApellido());
                            titularRegistrado.setDireccion(listaapo.getDomicilio());
                            titularRegistrado.setTipo_documento(listaapo.getTipoDocumento());
                            titularRegistrado.setLugar_expedicion(listaapo.getLugarExpedicion());
                            titularRegistrado.setNumero_documento(listaapo.getNumeroDocumento());
                            titularRegistrado.setCelular(listaapo.getCelular());
                            titularRegistrado.setGenero(listaapo.getGenero());
                            titularRegistrado.setTelefono(listaapo.getTelefono());
                            titularRegistrado.setEmail(listaapo.getCorreoElectronico());
                            titularRegistrado.setPoder(listaapo.getNumeroPoder());
                            titularRegistrado.setTiposoliapo("DMTE");
                            titularRegistrado.setTipo_persona("APOD");
                            titularRegistrado.setEstado("AC");

                            listaapodmte.add(titularRegistrado);

                        } catch (Exception ex) {
                            Logger.getLogger(IngresoFormularioServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }

                    List<DireccionNotificaciones> notificaciondatossigdmte = formularioPI105.getDirecciones();

                    DireccionNotificaciones dirnoti = notificaciondatossigdmte.get(0);

                    datosnotificaciondmte.setCiudad_notificacion(dirnoti.getCiudadNotificacion());
                    datosnotificaciondmte.setZona_barrio(dirnoti.getZonaBarrio());
                    datosnotificaciondmte.setAvenida_calle(dirnoti.getAvenidaCalle());
                    datosnotificaciondmte.setNumero(dirnoti.getNumeroDomicilio());
                    datosnotificaciondmte.setEdificio(dirnoti.getNombreEdificio());
                    datosnotificaciondmte.setPiso(dirnoti.getPiso());
                    datosnotificaciondmte.setNumero_departamento(dirnoti.getDepartamento());
                    datosnotificaciondmte.setTelefono(dirnoti.getTelefono());
                    datosnotificaciondmte.setCelular(dirnoti.getCelular());
                    datosnotificaciondmte.setEmail(dirnoti.getCorreoelectronico());
                    datosnotificaciondmte.setReferencia_direccion(dirnoti.getReferencia());
                    datosnotificaciondmte.setEstado("AC");

                    swdmte = 1;
                }
            }
            if (formularioPI105.getOpomarcaDemandanteExt().getDmte_reg() != null && formularioPI105.getOpomarcaDemandanteExt().getDmte_serie() != null && swdmte == 0) {//&& gestionExpediente != null

                if (!formularioPI105.getOpomarcaDemandanteExt().getDmte_serie().equals("")) {

                    SigSignoMarca encontradoSignodmte = sigSignoMarcaService.listaSigSignoMarcaXRegistro(formularioPI105.getOpomarcaDemandanteExt().getDmte_reg().longValue(), formularioPI105.getOpomarcaDemandanteExt().getDmte_serie(), "SIG");

                    if (encontradoSignodmte != null) {
                        if (encontradoSignodmte.getSm() != null) {
                            datosmarcademandante.setDmte_sm((encontradoSignodmte.getSm()));
                        }
                        if (encontradoSignodmte.getNumeroPublicacion() != null) {
                            datosmarcademandante.setDmte_public((encontradoSignodmte.getNumeroPublicacion()).intValue());
                        }
                        if (encontradoSignodmte.getNumeroRegistro() != null) {
                            datosmarcademandante.setDmte_reg((encontradoSignodmte.getNumeroRegistro()).intValue());
                        }
                        if (encontradoSignodmte.getSerieRegistro() != null) {
                            datosmarcademandante.setDmte_serie(encontradoSignodmte.getSerieRegistro());
                        }
                        if (encontradoSignodmte.getSigno() != null) {
                            datosmarcademandante.setDmte_marca_lnv(encontradoSignodmte.getSigno());
                        }
                    }
                    datosmarcademandante.setIdlogtrans(logTransGuardado.getIdLogTrans());
                    datosmarcademandante.setEstado("AC");

                    List<Solicitantes> listaSolicitanteApoderadoSigdmte = formularioPI105.getSolicitantes();

                    for (Solicitantes item : listaSolicitanteApoderadoSigdmte) {
                        try {

                            OpoSolicitanteapoderado titularRegistrado = new OpoSolicitanteapoderado();
                            // String nombrex = devuelveNombreJuridicoONatural(item.getNombreRazonSocial(), item.getPrimerApellido(), item.getSegundoApellido());

                            titularRegistrado.setNombre_razonsocial(item.getNombreRazonSocial());
                            titularRegistrado.setPrimer_apellido(item.getPrimerApellido());
                            titularRegistrado.setSegundo_apellido(item.getSegundoApellido());
                            titularRegistrado.setDireccion(item.getDomicilio());
                            titularRegistrado.setTipo_documento(item.getTipoDocumento());
                            titularRegistrado.setNumero_documento(item.getNumeroDocumento());
                            titularRegistrado.setLugar_expedicion(item.getLugarExpedicion());
                            titularRegistrado.setPais(item.getPais());
                            titularRegistrado.setGenero(item.getGenero());
                            titularRegistrado.setSolicitud_depa(item.getDepartamentoSolicitud());
                            titularRegistrado.setCelular(item.getCelular());
                            titularRegistrado.setTelefono(item.getTelefono());
                            titularRegistrado.setEmail(item.getCorreoElectronico());
                            titularRegistrado.setTiposoliapo("DMTE");
                            titularRegistrado.setTipo_persona("SOLI");
                            titularRegistrado.setEstado("AC");

                            listasolidmte.add(titularRegistrado);

                        } catch (Exception ex) {
                            Logger.getLogger(IngresoFormularioServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    List<Apoderados> listaApoderadoSigdmte = formularioPI105.getApoderados();

                    for (Apoderados listaapo : listaApoderadoSigdmte) {
                        try {

                            OpoSolicitanteapoderado titularRegistrado = new OpoSolicitanteapoderado();
                            //  String nombrex = devuelveNombreJuridicoONatural(listaapo.getNombreRazonSocial(), listaapo.getPrimerApellido(), listaapo.getSegundoApellido());
                            titularRegistrado.setNombre_razonsocial(listaapo.getNombres());
                            titularRegistrado.setPrimer_apellido(listaapo.getPrimerApellido());
                            titularRegistrado.setSegundo_apellido(listaapo.getSegundoApellido());
                            titularRegistrado.setDireccion(listaapo.getDomicilio());
                            titularRegistrado.setTipo_documento(listaapo.getTipoDocumento());
                            titularRegistrado.setNumero_documento(listaapo.getNumeroDocumento());
                            titularRegistrado.setLugar_expedicion(listaapo.getLugarExpedicion());
                            titularRegistrado.setCelular(listaapo.getCelular());
                            titularRegistrado.setGenero(listaapo.getGenero());
                            titularRegistrado.setTelefono(listaapo.getTelefono());
                            titularRegistrado.setEmail(listaapo.getCorreoElectronico());
                            titularRegistrado.setPoder(listaapo.getNumeroPoder());
                            titularRegistrado.setTiposoliapo("DMTE");
                            titularRegistrado.setTipo_persona("APOD");
                            titularRegistrado.setEstado("AC");

                            listaapodmte.add(titularRegistrado);

                        } catch (Exception ex) {
                            Logger.getLogger(IngresoFormularioServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }

                    List<DireccionNotificaciones> notificaciondatossigdmte = formularioPI105.getDirecciones();

                    DireccionNotificaciones dirnoti = notificaciondatossigdmte.get(0);

                    datosnotificaciondmte.setCiudad_notificacion(dirnoti.getCiudadNotificacion());
                    datosnotificaciondmte.setZona_barrio(dirnoti.getZonaBarrio());
                    datosnotificaciondmte.setAvenida_calle(dirnoti.getAvenidaCalle());
                    datosnotificaciondmte.setNumero(dirnoti.getNumeroDomicilio());
                    datosnotificaciondmte.setEdificio(dirnoti.getNombreEdificio());
                    datosnotificaciondmte.setPiso(dirnoti.getPiso());
                    datosnotificaciondmte.setNumero_departamento(dirnoti.getDepartamento());
                    datosnotificaciondmte.setTelefono(dirnoti.getTelefono());
                    datosnotificaciondmte.setCelular(dirnoti.getCelular());
                    datosnotificaciondmte.setEmail(dirnoti.getCorreoelectronico());
                    datosnotificaciondmte.setReferencia_direccion(dirnoti.getReferencia());
                    datosnotificaciondmte.setEstado("AC");

                    swdmte = 1;
                }

            }

            if (formularioPI105.getOpomarcaDemandanteExt().getDmte_sin_marca() != null && swdmte == 0) {

                if (!formularioPI105.getOpomarcaDemandanteExt().getDmte_sin_marca().equals("")) {

                    datosmarcademandante.setDmte_public(null);
                    datosmarcademandante.setDmte_reg(null);
                    datosmarcademandante.setDmte_serie("");
                    String marcadmdte = dominioService.obtenerNombrePorCodigoDominio(formularioPI105.getOpomarcaDemandanteExt().getDmte_sin_marca(), "sinmarca_opo");
                    datosmarcademandante.setDmte_marca_lnv(marcadmdte);
                    datosmarcademandante.setIdlogtrans(logTransGuardado.getIdLogTrans());
                    datosmarcademandante.setEstado("AC");

                    List<Solicitantes> listaSolicitanteApoderadoSigdmte = formularioPI105.getSolicitantes();

                    for (Solicitantes item : listaSolicitanteApoderadoSigdmte) {
                        try {

                            OpoSolicitanteapoderado titularRegistrado = new OpoSolicitanteapoderado();
                            // String nombrex = devuelveNombreJuridicoONatural(item.getNombreRazonSocial(), item.getPrimerApellido(), item.getSegundoApellido());

                            titularRegistrado.setNombre_razonsocial(item.getNombreRazonSocial());
                            titularRegistrado.setPrimer_apellido(item.getPrimerApellido());
                            titularRegistrado.setSegundo_apellido(item.getSegundoApellido());
                            titularRegistrado.setDireccion(item.getDomicilio());
                            titularRegistrado.setTipo_documento(item.getTipoDocumento());
                            titularRegistrado.setNumero_documento(item.getNumeroDocumento());
                            titularRegistrado.setLugar_expedicion(item.getLugarExpedicion());
                            titularRegistrado.setPais(item.getPais());
                            titularRegistrado.setGenero(item.getGenero());
                            titularRegistrado.setSolicitud_depa(item.getDepartamentoSolicitud());
                            titularRegistrado.setCelular(item.getCelular());
                            titularRegistrado.setTelefono(item.getTelefono());
                            titularRegistrado.setEmail(item.getCorreoElectronico());
                            titularRegistrado.setTiposoliapo("DMTE");
                            titularRegistrado.setTipo_persona("SOLI");
                            titularRegistrado.setEstado("AC");

                            listasolidmte.add(titularRegistrado);

                        } catch (Exception ex) {
                            Logger.getLogger(IngresoFormularioServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    List<Apoderados> listaApoderadoSigdmte = formularioPI105.getApoderados();

                    for (Apoderados listaapo : listaApoderadoSigdmte) {
                        try {

                            OpoSolicitanteapoderado titularRegistrado = new OpoSolicitanteapoderado();
                            //  String nombrex = devuelveNombreJuridicoONatural(listaapo.getNombreRazonSocial(), listaapo.getPrimerApellido(), listaapo.getSegundoApellido());
                            titularRegistrado.setNombre_razonsocial(listaapo.getNombres());
                            titularRegistrado.setPrimer_apellido(listaapo.getPrimerApellido());
                            titularRegistrado.setSegundo_apellido(listaapo.getSegundoApellido());
                            titularRegistrado.setDireccion(listaapo.getDomicilio());
                            titularRegistrado.setTipo_documento(listaapo.getTipoDocumento());
                            titularRegistrado.setNumero_documento(listaapo.getNumeroDocumento());
                            titularRegistrado.setLugar_expedicion(listaapo.getLugarExpedicion());
                            titularRegistrado.setCelular(listaapo.getCelular());
                            titularRegistrado.setGenero(listaapo.getGenero());
                            titularRegistrado.setTelefono(listaapo.getTelefono());
                            titularRegistrado.setEmail(listaapo.getCorreoElectronico());
                            titularRegistrado.setPoder(listaapo.getNumeroPoder());
                            titularRegistrado.setTiposoliapo("DMTE");
                            titularRegistrado.setTipo_persona("APOD");
                            titularRegistrado.setEstado("AC");

                            listaapodmte.add(titularRegistrado);

                        } catch (Exception ex) {
                            Logger.getLogger(IngresoFormularioServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }

                    List<DireccionNotificaciones> notificaciondatossigdmte = formularioPI105.getDirecciones();

                    DireccionNotificaciones dirnoti = notificaciondatossigdmte.get(0);

                    datosnotificaciondmte.setCiudad_notificacion(dirnoti.getCiudadNotificacion());
                    datosnotificaciondmte.setZona_barrio(dirnoti.getZonaBarrio());
                    datosnotificaciondmte.setAvenida_calle(dirnoti.getAvenidaCalle());
                    datosnotificaciondmte.setNumero(dirnoti.getNumeroDomicilio());
                    datosnotificaciondmte.setEdificio(dirnoti.getNombreEdificio());
                    datosnotificaciondmte.setPiso(dirnoti.getPiso());
                    datosnotificaciondmte.setNumero_departamento(dirnoti.getDepartamento());
                    datosnotificaciondmte.setTelefono(dirnoti.getTelefono());
                    datosnotificaciondmte.setCelular(dirnoti.getCelular());
                    datosnotificaciondmte.setEmail(dirnoti.getCorreoelectronico());
                    datosnotificaciondmte.setReferencia_direccion(dirnoti.getReferencia());
                    datosnotificaciondmte.setEstado("AC");

                    enlazaobjetos.setObjnotificaciondmte(datosnotificaciondmte);
                }

            }

            enlazaobjetos.setObjmarcademandante(datosmarcademandante);
            enlazaobjetos.setObjnotificaciondmte(datosnotificaciondmte);
            enlazaobjetos.setListasolidmte(listasolidmte);
            enlazaobjetos.setListaapodmte(listaapodmte);
            objetogeneral = opoGeneralService.guardartramiteopoext(enlazaobjetos);

            //  opoGeneralService.guardartramiteopo(enlazaobjetos);
            /////////////////////////VERIFICAR EL GUARDADO GENERAL   ============> datosoposicion //////////////////////////////////
            //guardar el seguimiento del tramite
            Seguimiento renSeguimiento = new Seguimiento();
            renSeguimiento.setId(objetogeneral.getObjopsicion().getIdoposicion());
            /**
             * *************************************Cambiar****************
             */
            renSeguimiento.setIdUsuario(idUsuario);
            renSeguimiento.setIdLogTrans(logtrans);
            renSeguimiento.setSm(null);
            renSeguimiento.setEtapa("VENT");
            renSeguimiento.setFechaRecepcion(new Timestamp(fechaServidor.getTime()));
            renSeguimiento.setFechaFin(new Timestamp(fechaServidor.getTime()));
            renSeguimiento.setEditable(Boolean.FALSE);
            renSeguimiento.setEstado(EnumEstado.ACTIVO.getCodigo());
            renSeguimiento.setPlazoReal(0);
            renSeguimiento.setTotalTiempo(0L);
            Seguimiento renSeguimientoIngresado = new Seguimiento();
            renSeguimientoIngresado = seguimientoService.guardar_modificar_listar_Seguimiento(renSeguimiento, "OPO", 1);

            //guardar los registros del listado de requisitos
            List<DatoElementoFormulario> listaDatoElementoFormulario = new ArrayList<>();

            //Armar la lista
            for (ElementoFormularioTramite elementoFormularioTramite : plantillaVentanilla) {
                DatoElementoFormulario datoElementoFormulario = new DatoElementoFormulario();
                datoElementoFormulario.setNombreTabla("oposeguimiento");
                datoElementoFormulario.setIdSeguimiento(renSeguimientoIngresado.getIdSeguimiento());
                datoElementoFormulario.setIdLogTrans(logtrans);
                datoElementoFormulario.setPestania(elementoFormularioTramite.getPestania());
                datoElementoFormulario.setSeccion(elementoFormularioTramite.getSeccion());
                datoElementoFormulario.setFila(elementoFormularioTramite.getFila());
                datoElementoFormulario.setTipoElemento(elementoFormularioTramite.getTipoElemento());
                datoElementoFormulario.setNombreElemento(elementoFormularioTramite.getNombreElemento());
                datoElementoFormulario.setOrden(elementoFormularioTramite.getOrden());
                datoElementoFormulario.setOrdenLiteral(elementoFormularioTramite.getOrdenLiteral());
                datoElementoFormulario.setIdpadre(elementoFormularioTramite.getIdpadre());
                datoElementoFormulario.setRespuesta(elementoFormularioTramite.getRespuesta());
                datoElementoFormulario.setOpcionRespuesta(elementoFormularioTramite.getOpcionRespuesta());
                datoElementoFormulario.setEstado(EnumEstado.ACTIVO.getCodigo());

                listaDatoElementoFormulario.add(datoElementoFormulario);
            }

            datoElementoFormularioService.guardarRegistrosPlantillaVentanilla(listaDatoElementoFormulario, EnumPrefijoTablas.OPOSICION.getCodigo());

            if (!observacion.equals("")) {
                //finalmente guardar la observacion del tramite.
                ObservacionTramite observacionTramite = new ObservacionTramite();
                observacionTramite.setId(objetogeneral.getObjopsicion().getIdoposicion());
                /**
                 * *************************************Cambiar****************
                 */
                observacionTramite.setIdUsuario(idUsuario);
                observacionTramite.setIdLogTrans(logtrans);
                observacionTramite.setEditable(Boolean.TRUE);
                observacionTramite.setFechaObservacion(fechaServidor);
                observacionTramite.setEtapaTramite("VENT");
                observacionTramite.setDescripcion(observacion);
                observacionTramite.setEstado(EnumEstado.ACTIVO.getCodigo());
                observacionTramiteService.guardar_modificar_listar_ObservacionTramite(observacionTramite, EnumPrefijoTablas.OPOSICION.getCodigo(), 1);
            }

            //Guardar el historial del ingreso del formulario PI105
            Historial historial = new Historial();
            historial.setIdLogTrans(logTransGuardado.getIdLogTrans());
            historial.setId(objetogeneral.getObjopsicion().getIdoposicion());
            historial.setIdUsuario(idUsuario);
            historial.setOperacion(EnumOperacion.ADICIONAR.getCodigo());
            historial.setEstadoMarcaDescripcion(dominioService.obtenerNombreCodigoDominio(EnumDominio.ESTADO_OPOSICION.getCodigo(), EnumEstadoOposicion.SOLICITADO.getCodigo()));
            historial.setDescripcion(EnumFormularioIngreso.PI105.getCodigo());
            historial.setFechaOperacion(fechaServidor);
            historial.setEstado(EnumEstado.ACTIVO.getCodigo());
            System.out.println("===================================>" + EnumPrefijoTablas.OPOSICION.getCodigo());
            historialService.crudHistorial(historial, EnumPrefijoTablas.OPOSICION.getCodigo(), 1);

            //realizar el registro en la tabla UsuarioTramite.
            UsuarioTramite usuarioTramite = new UsuarioTramite();

            //usuarioTramite.setIdUsuarioTramite(1L);
            usuarioTramite.setTipoTramite(EnumTipoTramiteUsuario.OPOSICION.getCodigo());
            usuarioTramite.setIdUsuarioExterno(formularioPI105.getFormularios().getIdUsuarioExterno());
            usuarioTramite.setId(objetogeneral.getObjopsicion().getIdoposicion());
            usuarioTramite.setIdLogTrans(logtrans);
            usuarioTramite.setEstado(EnumEstado.ACTIVO.getCodigo());
            usuarioTramiteService.crudUsuarioTramite(usuarioTramite, 1L);

            //Segmento para cambiar el estado de sigsignomarca
//            if (formularioPI105.getOpoMarcademandadaExt().getDmdo_public() != null) {
//                if ((formularioPI105.getOpoMarcademandadaExt().getDmdo_public()) != 0) {
//
//                    signomarcaActumarca = sigSignoMarcaService.listaSigSignoMarcaXPublicacion((formularioPI105.getOpoMarcademandadaExt().getDmdo_public()).longValue());
//                    signomarcaActumarca.setUbicacion("PIOP");
//                    signomarcaActumarca.setEstadoMarca("OPO");
//                    sigSignoMarcaService.crudSigSignoMarca(signomarcaActumarca, 2);
//
//                }
//            }
            //llenado de datos externos para opoevento, en la tabla logtrans y opo historial
            if (formularioPI105.getOpoMarcademandadaExt().getDmdo_public() != null) {
                if ((formularioPI105.getOpoMarcademandadaExt().getDmdo_public()) != 0) {

                    datosopoevento.setIdactividad(1L);
                    datosopoevento.setIdoposicion(oposicionService.encuentraclaveprin((formularioPI105.getOpoMarcademandadaExt().getDmdo_public()).longValue(), oposicionService.extraerultimonorden((formularioPI105.getOpoMarcademandadaExt().getDmdo_public()).longValue())));
                    datosopoevento.setFecha(formularioPI105.getOpoMarcademandadaExt().getFechacreacion());
                    datosopoevento.setFecha_operacion(comunService.obtenerFechaHoraServidor(1L));
                    datosopoevento.setOrden_o((formularioPI105.getOpoMarcademandadaExt().getDmdo_public()));
                    datosopoevento.setUsuario(idUsuario);
                    datosopoevento.setEstado("AC");
                    opoEventoService.guardaOpoEvento(datosopoevento);

                    datosopoevento.setIdactividad(2L);
                    datosopoevento.setIdoposicion(oposicionService.encuentraclaveprin((formularioPI105.getOpoMarcademandadaExt().getDmdo_public()).longValue(), oposicionService.extraerultimonorden((formularioPI105.getOpoMarcademandadaExt().getDmdo_public()).longValue())));
                    datosopoevento.setFecha(fechaIngreso);
                    datosopoevento.setFecha_operacion(comunService.obtenerFechaHoraServidor(1L));
                    datosopoevento.setOrden_o((formularioPI105.getOpoMarcademandadaExt().getDmdo_public()));
                    datosopoevento.setUsuario(idUsuario);
                    datosopoevento.setEstado("AC");
                    opoEventoService.guardaOpoEvento(datosopoevento);

                    datosfechalimite.setIdoposicion(oposicionService.encuentraclaveprin((formularioPI105.getOpoMarcademandadaExt().getDmdo_public()).longValue(), oposicionService.extraerultimonorden((formularioPI105.getOpoMarcademandadaExt().getDmdo_public()).longValue())));
                    datosfechalimite.setIdevento(2L);
                    datosfechalimite.setOrden_o((formularioPI105.getOpoMarcademandadaExt().getDmdo_public()));
                    datosfechalimite.setIdactividadplazo(1L);
                    opoFechaLimiteService.guardaOpoFechalimite(datosfechalimite);

                    datoshistorial.setIdoposicion(oposicionService.encuentraclaveprin((formularioPI105.getOpoMarcademandadaExt().getDmdo_public()).longValue(), oposicionService.extraerultimonorden((formularioPI105.getOpoMarcademandadaExt().getDmdo_public()).longValue())));
                    datoshistorial.setIdlogtrans(1L);
                    datoshistorial.setEstado("PRESENTADA");
                    datoshistorial.setFecha_lim(formularioPI105.getOpoMarcademandadaExt().getFechacreacion());
                    datoshistorial.setObservacion("Tramite Iniciado");
                    datoshistorial.setOperacion("ADD");
                    datoshistorial.setFecha_operacion(comunService.obtenerFechaHoraServidor(1L));
                    datoshistorial.setId_usuario(idUsuario);

                    opoHistorialService.guardaOpoHistorial(datoshistorial);

                }
            }

            //Actualizar el estado del formulario
            formularioService.actualizarRegistroFormulario(formularioPI105.getFormularios().getId());

            return mapResultado;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Boolean verificarExistenciaRegistroMarcaXSM(Long codigoSM) throws Exception {
        try {
            String SQL = "select idsignomarca from sigsignomarca where sm = ? and estado = 'AC'";
            Long id = jdbcTemplate.queryForObject(SQL, new Object[]{codigoSM}, Long.class);
            if (id != null) {
                return Boolean.TRUE;
            }

            return Boolean.FALSE;
        } catch (EmptyResultDataAccessException e) {
            //Como no se encontro ningun registro enviar falso, para que se pueda realizar la insercion
            return Boolean.FALSE;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Boolean verificarExistenciaRegistroModificacion(String sigla, Long numero, Long gestion) throws Exception {
        try {
            String SQL = "select idmodificacion from modmodificacion where sigla = ? and numero = ? and gestion = ? and estado = 'AC'";

            Long id = jdbcTemplate.queryForObject(SQL, new Object[]{sigla, numero, gestion}, Long.class);
            if (id != null) {
                return Boolean.TRUE;
            }

            return Boolean.FALSE;
        } catch (EmptyResultDataAccessException e) {
            //Como no se encontro ningun registro enviar falso, para que se pueda realizar la insercion
            return Boolean.FALSE;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Boolean verificarExistenciaRegistroRenovacion(Long numero, Long gestion) throws Exception {
        try {
            String SQL = "select idsolicitudrenovacion from rensolicitudrenovacion where sr = ? and gestion_sr = ? and estado = 'AC'";

            Long id = jdbcTemplate.queryForObject(SQL, new Object[]{numero, gestion}, Long.class);
            if (id != null) {
                return Boolean.TRUE;
            }

            return Boolean.FALSE;
        } catch (EmptyResultDataAccessException e) {
            //Como no se encontro ningun registro enviar falso, para que se pueda realizar la insercion
            return Boolean.FALSE;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Boolean verificarExistenciaRegistroOposicion(Long numero, Long gestion) throws Exception {

        try {
            String SQL = "select idoposicion from oposicion where codigo_opo = ? and gestion_opo = ? and estadoopo = 'AC'";

            Long id = jdbcTemplate.queryForObject(SQL, new Object[]{numero, gestion}, Long.class);
            if (id != null) {
                return Boolean.TRUE;
            }
            return Boolean.FALSE;
        } catch (EmptyResultDataAccessException e) {
            //Como no se encontro ningun registro enviar falso, para que se pueda realizar la insercion
            return Boolean.FALSE;
        } catch (Exception e) {
            throw e;
        }

    }

}
