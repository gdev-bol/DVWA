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
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import snp.gob.bo.gimodel.entidad.ClaseNiza;
import snp.gob.bo.gimodel.entidad.Correlativo;
import snp.gob.bo.gimodel.entidad.Historial;
import snp.gob.bo.gimodel.entidad.LogTrans;
import snp.gob.bo.gimodel.entidad.RenRenovacion;
import snp.gob.bo.gimodel.entidad.RenResolucion;
import snp.gob.bo.gimodel.entidad.RenSolicitudRenovacion;
import snp.gob.bo.gimodel.entidad.SigSignoClaseNiza;
import snp.gob.bo.gimodel.entidad.SigSignoMarca;
import snp.gob.bo.gimodel.entidad.Usuario;
import snp.gob.bo.gimodel.enums.EnumEstado;
import snp.gob.bo.gimodel.enums.EnumEstadoRenovacion;
import snp.gob.bo.gimodel.enums.EnumTipoTramite;
import snp.gob.bo.gimodel.enums.EnumUbicacion;
import snp.gob.bo.gimodel.mapper.RenRenovacionMapper;
import snp.gob.bo.gimodel.mapper.RenSolicitudRenovacionMapper;
import snp.gob.bo.gimodel.servicio.ClaseNizaService;
import snp.gob.bo.gimodel.servicio.ComunService;
import snp.gob.bo.gimodel.servicio.CorrelativoService;
import snp.gob.bo.gimodel.servicio.HistorialService;
import snp.gob.bo.gimodel.servicio.LogTransService;
import snp.gob.bo.gimodel.servicio.RenRenovacionService;
import snp.gob.bo.gimodel.servicio.RenResolucionService;
import snp.gob.bo.gimodel.servicio.RenSolicitudRenovacionService;
import snp.gob.bo.gimodel.servicio.SigSignoClaseNizaService;
import snp.gob.bo.gimodel.servicio.SigSignoMarcaService;

/**
 *
 * @author Chano Rojas
 * @see RenRenovacionDominio
 * @see RenrenovacionServiceImpl
 * @version 1.0, 05/06/2016
 */
@Service("renRenovacionService")
public class RenRenovacionServiceImpl implements RenRenovacionService {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    RenResolucionService renResolucionService;

    @Autowired
    CorrelativoService correlativoService;

    @Autowired
    RenSolicitudRenovacionService renSolicitudRenovacionService;

    @Autowired
    ComunService comunService;

    @Autowired
    SigSignoMarcaService sigSignoMarcaService;

    @Autowired
    LogTransService logTransService;

    @Autowired
    ClaseNizaService claseNizaService;

    @Autowired
    HistorialService historialService;
    @Autowired
    SigSignoClaseNizaService sigSignoClaseNizaService;

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
    public RenRenovacion crudRenRenovacion(RenRenovacion renRenovacion, int parametro) throws Exception {
        try {
            String SQL = "select * from crud_renrenovacion("
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
            RenRenovacion ren = (RenRenovacion) jdbcTemplate.queryForObject(SQL, new RenRenovacionMapper(),
                    renRenovacion.getIdrenovacion(),
                    renRenovacion.getIdsolicitudrenovacion(),
                    renRenovacion.getIdlogtrans(),
                    renRenovacion.getNumero_renovacion(),
                    renRenovacion.getSerie_renovacion(),
                    renRenovacion.getOrden_renovacion(),
                    renRenovacion.getIdclase_niza_renovacion(),
                    renRenovacion.getFecha_concesion(),
                    renRenovacion.getTitular(),
                    renRenovacion.getApoderado(),
                    renRenovacion.getTipo_marca(),
                    renRenovacion.getTipo_genero(),
                    renRenovacion.getSigno_registrado(),
                    renRenovacion.getEtiqueta_renovacion(),
                    renRenovacion.getNumero_registro(),
                    renRenovacion.getSerie_registro(),
                    renRenovacion.getIdclase_niza_actual(),
                    renRenovacion.getSr_manual(),
                    renRenovacion.getGestion_sr_manual(),
                    renRenovacion.getFecha_registro_manual(),
                    renRenovacion.getFecha_ingreso(),
                    renRenovacion.getLista_producto_actual(),
                    renRenovacion.getLista_producto_actual(),
                    renRenovacion.getVersion_clase_niza(),
                    renRenovacion.getFecha_renovacion(),
                    renRenovacion.getGestion_renovacion(),
                    renRenovacion.getEstado(),
                    parametro);
            return ren;
        } catch (DataAccessException e) {
            throw e;
        }
    }

    @Override
    public List<RenRenovacion> obtenerListadoRenRenovacion(RenRenovacion renRenovacion, int parametro) throws Exception {
        try {
            String SQL = "select * from crud_renrenovacion("
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
            List<RenRenovacion> listaRenRenovacion = jdbcTemplate.query(SQL, new RenRenovacionMapper(),
                    renRenovacion.getIdrenovacion(),
                    renRenovacion.getIdsolicitudrenovacion(),
                    renRenovacion.getIdlogtrans(),
                    renRenovacion.getNumero_renovacion(),
                    renRenovacion.getSerie_renovacion(),
                    renRenovacion.getOrden_renovacion(),
                    renRenovacion.getIdclase_niza_renovacion(),
                    renRenovacion.getFecha_concesion(),
                    renRenovacion.getTitular(),
                    renRenovacion.getApoderado(),
                    renRenovacion.getTipo_marca(),
                    renRenovacion.getTipo_genero(),
                    renRenovacion.getSigno_registrado(),
                    renRenovacion.getEtiqueta_renovacion(),
                    renRenovacion.getNumero_registro(),
                    renRenovacion.getSerie_registro(),
                    renRenovacion.getIdclase_niza_actual(),
                    renRenovacion.getSr_manual(),
                    renRenovacion.getGestion_sr_manual(),
                    renRenovacion.getFecha_registro_manual(),
                    renRenovacion.getFecha_ingreso(),
                    renRenovacion.getLista_producto_actual(),
                    renRenovacion.getLista_producto_actual(),
                    renRenovacion.getVersion_clase_niza(),
                    renRenovacion.getFecha_renovacion(),
                    renRenovacion.getGestion_renovacion(),
                    renRenovacion.getEstado(),
                    parametro);
            if (!listaRenRenovacion.isEmpty()) {
                return listaRenRenovacion;
            }
            return Collections.EMPTY_LIST;
        } catch (DataAccessException e) {
            throw e;
        }

    }

    @Override
    public RenRenovacion obtieneRenovacionPorIdSolicitud(Long idRenSoicitdRenovacion) {
        try {
            String SQL = "select * from obtener_renrenovacion_idsolicitud("
                    + "?);";
            List<RenRenovacion> listaren = jdbcTemplate.query(SQL, new RenRenovacionMapper(),
                    idRenSoicitdRenovacion);
            if (!listaren.isEmpty()) {
                return listaren.get(0);
            } else {
                return new RenRenovacion();
            }
        } catch (DataAccessException e) {
            throw e;
        }
    }

    @Override
    public String numeroOrdinal(int numeroRenovacion) {
        String numeroObtenido;
        String Unidad[] = {"", "PRIMERA", "SEGUNDA", "TERCERA",
            "CUARTA", "QUINTA", "SEXTA", "SEPTIMA", "OCTAVA", "NOVENA"};
        String Decena[] = {"", "DECIMA", "VIGESIMA", "TRIGESIMA",
            "CUADRAGESIMA", "QUINCUAGESIMA", "SEXAGESIMA", "SEPTUAGESIMA",
            "OCTOGESIMA", "NONAGESIMA"};
        String Centena[] = {"", "CENTESIMA", "DUCENTESIMA", "TRICENTESIMO",
            "CUADRINGENTESIMO", "QUINGENTESIMO", "SEXCENTESIMO",
            "SEPTINGENTESIMO", "OCTINGENTESIMO", "NONINGENTESIMO"};
        int N = numeroRenovacion;
        int u = N % 10;
        int d = (N / 10) % 10;
        int c = N / 100;
        if (N >= 100) {
            numeroObtenido = Centena[c] + " " + Decena[d] + " " + Unidad[u];
        } else {
            if (N >= 10) {
                numeroObtenido = Decena[d] + " " + Unidad[u];
            } else {
                numeroObtenido = Unidad[N];
            }
        }
        return numeroObtenido;
    }

    @Override
    public int calculaNumeroDeRenovacion(Date fechaRegistro) {
        try {
//            Date fechaServidor = utilitariosService.obtenerFechaServidor();
            Date fechaServidor = new Date();
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy");
            String fechaInicio = sdf.format(fechaRegistro);
            String fechaActual = sdf.format(fechaServidor);
            String[] aFechaIng = fechaInicio.split("/");
            Integer diaInicio = Integer.parseInt(aFechaIng[0]);
            Integer mesInicio = Integer.parseInt(aFechaIng[1]);
            Integer anioInicio = Integer.parseInt(aFechaIng[2]);
            String[] aFecha = fechaActual.split("/");
            Integer diaActual = Integer.parseInt(aFecha[0]);
            Integer mesActual = Integer.parseInt(aFecha[1]);
            Integer anioActual = Integer.parseInt(aFecha[2]);
            int b = 0;
            int dias = 0;
            int mes = 0;
            int anios = 0;
            int meses = 0;
            mes = mesInicio - 1;
            if (mes == 2) {
                if ((anioActual % 4 == 0) && ((anioActual % 100 != 0) || (anioActual % 400 == 0))) {
                    b = 29;
                } else {
                    b = 28;
                }
            } else if (mes <= 7) {
                if (mes == 0) {
                    b = 31;
                } else if (mes % 2 == 0) {
                    b = 30;
                } else {
                    b = 31;
                }
            } else if (mes > 7) {
                if (mes % 2 == 0) {
                    b = 31;
                } else {
                    b = 30;
                }
            }
            if ((anioInicio > anioActual) || (anioInicio == anioActual && mesInicio > mesActual) || (anioInicio == anioActual && mesInicio == mesActual && diaInicio > diaActual)) {
            } else {
                if (mesInicio <= mesActual) {
                    anios = anioActual - anioInicio;
                    if (diaInicio <= diaActual) {
                        meses = mesActual - mesInicio;
                        dias = b - (diaInicio - diaActual);
                    } else {
                        if (mesActual == mesInicio) {
                            anios = anios - 1;
                        }
                        meses = (mesActual - mesInicio - 1 + 12) % 12;
                        dias = b - (diaInicio - diaActual);
                    }
                } else {
                    anios = anioActual - anioInicio - 1;
                    //System.out.println(anios);
                    if (diaInicio > diaActual) {
                        meses = mesActual - mesInicio - 1 + 12;
                        dias = b - (diaInicio - diaActual);
                    } else {
                        meses = mesActual - mesInicio + 12;
                        dias = diaActual - diaInicio;
                    }
                }
            }
            if (anios < 19) {
                return (1);
            }
            if (anios < 29 && anios > 10) {
                return (2);

            }
            if (anios < 39 && anios > 20) {
                return (3);

            }
            if (anios < 49 && anios > 30) {
                return (4);
            }
            if (anios < 59 && anios > 40) {
                return (5);
            }
            if (anios < 69 && anios > 50) {
                return (6);
            }
            if (anios < 79 && anios > 60) {
                return (7);
            }
            if (anios < 89 && anios > 70) {
                return (8);
            }
            if (anios < 99 && anios > 80) {
                return (9);
            }
            if (anios < 109 && anios > 90) {
                return (10);
            }
            if (anios < 119 && anios > 100) {
                return (11);
            }
            if (anios < 129 && anios > 110) {
                return (12);
            }
            if (anios < 139 && anios > 120) {
                return (13);
            }

            return (anios / 10);
        } catch (NumberFormatException ex) {
            throw ex;
        }
//        return 0;
    }

    @Override
    public int calculaNumeroDeRenovacionSegunFechaIngresoRenovacion(Date fechaRegistro, Date fechaIngresoRenovacion) {
        try {
//            Date fechaServidor = utilitariosService.obtenerFechaServidor();
            Date fechaServidor = fechaIngresoRenovacion;
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy");
            String fechaInicio = sdf.format(fechaRegistro);
            String fechaActual = sdf.format(fechaServidor);
            String[] aFechaIng = fechaInicio.split("/");
            Integer diaInicio = Integer.parseInt(aFechaIng[0]);
            Integer mesInicio = Integer.parseInt(aFechaIng[1]);
            Integer anioInicio = Integer.parseInt(aFechaIng[2]);
            String[] aFecha = fechaActual.split("/");
            Integer diaActual = Integer.parseInt(aFecha[0]);
            Integer mesActual = Integer.parseInt(aFecha[1]);
            Integer anioActual = Integer.parseInt(aFecha[2]);
            int b = 0;
            int dias = 0;
            int mes = 0;
            int anios = 0;
            int meses = 0;
            mes = mesInicio - 1;
            if (mes == 2) {
                if ((anioActual % 4 == 0) && ((anioActual % 100 != 0) || (anioActual % 400 == 0))) {
                    b = 29;
                } else {
                    b = 28;
                }
            } else if (mes <= 7) {
                if (mes == 0) {
                    b = 31;
                } else if (mes % 2 == 0) {
                    b = 30;
                } else {
                    b = 31;
                }
            } else if (mes > 7) {
                if (mes % 2 == 0) {
                    b = 31;
                } else {
                    b = 30;
                }
            }
            if ((anioInicio > anioActual) || (anioInicio == anioActual && mesInicio > mesActual) || (anioInicio == anioActual && mesInicio == mesActual && diaInicio > diaActual)) {
            } else {
                if (mesInicio <= mesActual) {
                    anios = anioActual - anioInicio;
                    if (diaInicio <= diaActual) {
                        meses = mesActual - mesInicio;
                        dias = b - (diaInicio - diaActual);
                    } else {
                        if (mesActual == mesInicio) {
                            anios = anios - 1;
                        }
                        meses = (mesActual - mesInicio - 1 + 12) % 12;
                        dias = b - (diaInicio - diaActual);
                    }
                } else {
                    anios = anioActual - anioInicio - 1;
                    //System.out.println(anios);
                    if (diaInicio > diaActual) {
                        meses = mesActual - mesInicio - 1 + 12;
                        dias = b - (diaInicio - diaActual);
                    } else {
                        meses = mesActual - mesInicio + 12;
                        dias = diaActual - diaInicio;
                    }
                }
            }
            if (anios < 19) {
                return (1);
            }
            if (anios < 29 && anios > 10) {
                return (2);

            }
            if (anios < 39 && anios > 20) {
                return (3);

            }
            if (anios < 49 && anios > 30) {
                return (4);
            }
            if (anios < 59 && anios > 40) {
                return (5);
            }
            if (anios < 69 && anios > 50) {
                return (6);
            }
            if (anios < 79 && anios > 60) {
                return (7);
            }
            if (anios < 89 && anios > 70) {
                return (8);
            }
            if (anios < 99 && anios > 80) {
                return (9);
            }
            if (anios < 109 && anios > 90) {
                return (10);
            }
            if (anios < 119 && anios > 100) {
                return (11);
            }
            if (anios < 129 && anios > 110) {
                return (12);
            }
            if (anios < 139 && anios > 120) {
                return (13);
            }

            return (anios / 10);
        } catch (NumberFormatException ex) {
            throw ex;
        }
//        return 0;
    }

    @Override
    public Date devuelveFechaConcesion(Date fechaRegistro) throws Exception {
        Date fechaConcesion = null;
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy");
        String fechaInicio = sdf.format(fechaRegistro);
        String[] aFechaIng = fechaInicio.split("/");
        Integer diaInicio = Integer.parseInt(aFechaIng[0]);
        Integer mesInicio = Integer.parseInt(aFechaIng[1]);
        Integer anioInicio = Integer.parseInt(aFechaIng[2]);
        if (calculaNumeroDeRenovacion(fechaRegistro) == 0) {
            anioInicio = anioInicio + (10);
        } else {
            anioInicio = anioInicio + (10 * (calculaNumeroDeRenovacion(fechaRegistro)));
        }

        String fechaFinal = diaInicio + "/" + mesInicio + "/" + anioInicio;
        fechaConcesion = sdf.parse(fechaFinal);
        return fechaConcesion;
    }
    
    @Override
    public Date devuelveFechaConcesionFechaSolicitud(Date fechaRegistro,Date fechasolicitud) throws Exception {
        Date fechaConcesion = null;
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy");
        String fechaInicio = sdf.format(fechaRegistro);
        String[] aFechaIng = fechaInicio.split("/");
        Integer diaInicio = Integer.parseInt(aFechaIng[0]);
        Integer mesInicio = Integer.parseInt(aFechaIng[1]);
        Integer anioInicio = Integer.parseInt(aFechaIng[2]);
        if (calculaNumeroDeRenovacion(fechaRegistro) == 0) {
            anioInicio = anioInicio + (10);
        } else {
            anioInicio = anioInicio + (10 * (calculaNumeroDeRenovacionSegunFechaIngresoRenovacion(fechaRegistro,fechasolicitud)));
        }

        String fechaFinal = diaInicio + "/" + mesInicio + "/" + anioInicio;
        fechaConcesion = sdf.parse(fechaFinal);
        return fechaConcesion;
    }

    @Override
    public RenRenovacion guardaRenovacion(RenSolicitudRenovacion renSolicitudRenovacion, RenRenovacion renRenovacion, RenResolucion renResolucion, Usuario usuario) throws Exception {
        //System.out.println("renovacionesRegsitro" + usuario.getIdusuario());
        try {
            Date fechaRegistro = comunService.obtenerFechaServidor(1L);
            LogTrans logTransGuardado = logTransService.crudLogTrans(new LogTrans(usuario.getIdusuario(), fechaRegistro), 1);

            RenRenovacion renRenovacionGuardarBase = new RenRenovacion();
            Correlativo correlativoRenovacion = correlativoService.encuentraCorrelativoTabla(usuario.getIdregional(), EnumTipoTramite.NUMERO_RENOVACION.getCodigo());
            Correlativo correlativoResolucion = correlativoService.encuentraCorrelativoTabla(usuario.getIdregional(), EnumTipoTramite.RENOVACION_RESOLUCION.getCodigo());
            int numeroRenovacion = correlativoRenovacion.getUltimo_numero_asignado();
            int numeroResolucion = correlativoResolucion.getUltimo_numero_asignado();
            int parametro;
            if (renSolicitudRenovacion.getIdsolicitudrenovacion() != null) {
                parametro = 1;
                if (renRenovacion.getIdrenovacion() == null) {
                    renRenovacion.setNumero_renovacion(numeroRenovacion);
                    renRenovacion.setSerie_renovacion(correlativoRenovacion.getAcronimo());
                    renRenovacion.setIdsolicitudrenovacion(renSolicitudRenovacion.getIdsolicitudrenovacion());
                    renRenovacion.setEstado(EnumEstado.ACTIVO.getCodigo());
                    renRenovacion.setIdlogtrans(logTransGuardado.getIdLogTrans());
                    renRenovacion.setGestion_renovacion(renSolicitudRenovacion.getGestion_sr());
                    renRenovacionGuardarBase = crudRenRenovacion(renRenovacion, parametro);
                    renResolucion.setIdrenovacion(renRenovacionGuardarBase.getIdrenovacion());
                    renResolucion.setNumero_resolucion(numeroResolucion);
                    renResolucion.setGestion_resolucion(correlativoResolucion.getGestion());
                    renResolucion.setEstado(EnumEstado.ACTIVO.getCodigo());
                    renRenovacion.setIdlogtrans(logTransGuardado.getIdLogTrans());
                    renResolucion.setFecha_resolucion(comunService.obtenerFechaHoraServidor(1l));
                    
                    byte ptext[] = renResolucion.getObservacion_resolucion().getBytes(ISO_8859_1);
                    String value = new String(ptext, UTF_8);
                    renResolucion.setObservacion_resolucion(value);

                    renResolucionService.crudRenResolucion(renResolucion, parametro);
                    correlativoRenovacion.setUltimo_numero_asignado(numeroRenovacion + 1);
                    correlativoResolucion.setUltimo_numero_asignado(numeroResolucion + 1);
                    correlativoService.crudCorrelativo(correlativoResolucion, 2);
                    correlativoService.crudCorrelativo(correlativoRenovacion, 2);
                    renSolicitudRenovacion.setEstado_renovacion(EnumEstadoRenovacion.CONCEDIDO.getCodigo());
//                    renSolicitudRenovacion.setUbicacion_renovacion("Propiedad Industrial (Desglose)");
                    RenSolicitudRenovacion renSolicitudRenovacionAceptada = renSolicitudRenovacionService.cruddosRenSolictudRenovacion(renSolicitudRenovacion, 2);
                    guardahistorialRenovacion(renSolicitudRenovacionAceptada, usuario);
                    SigSignoMarca sigSignoMarca = sigSignoMarcaService.listaSigSignoMarcaXRegistroyClaseNiza(renSolicitudRenovacion.getNumero_registro_registrado(), renSolicitudRenovacion.getSerie_registro_registrado(), "signo",renSolicitudRenovacion.getIdclase_niza_registrado());
                    if (sigSignoMarca != null) {
                        sigSignoMarca.setNumeroRenovacion(renRenovacionGuardarBase.getNumero_renovacion());
                        sigSignoMarca.setExtensionRenovacion(renRenovacionGuardarBase.getSerie_renovacion());
                        sigSignoMarca.setOrdenRenovacion(renRenovacionGuardarBase.getOrden_renovacion());
                        sigSignoMarca.setFechaRenovacion(renRenovacionGuardarBase.getFecha_renovacion());
                        sigSignoMarca.setNumeroResolucionRenovacion(renResolucion.getNumero_resolucion().longValue());
                        sigSignoMarca.setUbicacion(EnumUbicacion.BIBLIOTECA.getCodigo());
                        sigSignoMarca.setEstadoMarca("RENO");
                        SigSignoMarca sigSignoMarcaAceptado = sigSignoMarcaService.crudSigSignoMarca(sigSignoMarca, 2);
                        guardaSignoMarcaHistorial(renRenovacionGuardarBase, sigSignoMarcaAceptado, usuario, logTransGuardado, fechaRegistro);
                        //actualiza la clase niza da de baja el anterior y introduce el nuevo
                        SigSignoClaseNiza claseNizaBaja = (SigSignoClaseNiza) sigSignoClaseNizaService.obtenerListaSigSignoClazeNizaXIdSignoMarcaNumeroNiza(sigSignoMarca.getIdSignoMarca(), renSolicitudRenovacionAceptada.getIdclase_niza_registrado().intValue());
                        SigSignoClaseNiza nuevoClaseNizaClon = (SigSignoClaseNiza) claseNizaBaja.clone();
                        if (renSolicitudRenovacion.getIdclase_niza_reclasificacion() != null) {
                            if (renSolicitudRenovacion.getIdclase_niza_reclasificacion() !=46 && renSolicitudRenovacion.getIdclase_niza_reclasificacion() !=0) {
                                nuevoClaseNizaClon.setNumeroClaseNiza((renSolicitudRenovacion.getIdclase_niza_reclasificacion()));
                                nuevoClaseNizaClon.setIdClaseNiza(claseNizaService.listarClaseNiza_id(renSolicitudRenovacion.getIdclase_niza_reclasificacion()).getIdClaseNiza());
                                if (renSolicitudRenovacion.getLista_productos_limitacion() == null) {
                                    nuevoClaseNizaClon.setListaProducto(((ClaseNiza) claseNizaService.listarClaseNiza_id(renSolicitudRenovacion.getIdclase_niza_reclasificacion())).getProteccion());
                                }

                            }
                        }
                        if (renSolicitudRenovacion.getLista_productos_limitacion() != null) {
                            if (!renSolicitudRenovacion.getLista_productos_limitacion().isEmpty()) {
                                nuevoClaseNizaClon.setListaProducto(renSolicitudRenovacion.getLista_productos_limitacion());
                            }
                        }
                        nuevoClaseNizaClon.setIdLogTrans(logTransGuardado.getIdLogTrans());
                        nuevoClaseNizaClon.setIdSignoClaseNiza(null);
                        System.out.println("nuevo"+nuevoClaseNizaClon.getIdSignoMarca());
                        sigSignoClaseNizaService.crudSigSignoClaseNiza(nuevoClaseNizaClon, 1);
                        claseNizaBaja.setEstado(EnumEstado.NO_ACTIVO.getCodigo());
                        sigSignoClaseNizaService.crudSigSignoClaseNiza(claseNizaBaja, 2);
                    }

                } else {
//           modificacion 
                    parametro = 2;
                }
            } else {
//            alertas para mensaje de solicitud no existente
            }
            return renRenovacionGuardarBase;
        } catch (Exception e) {
            throw e;
        }

    }

    public Historial guardaSignoMarcaHistorial(RenRenovacion renRenovacion, SigSignoMarca sigSignoMarca, Usuario usuario, LogTrans logTransGuardado, Date fechaRegistro) throws Exception {
        try {

            ClaseNiza nizaClase = claseNizaService.listarClaseNiza_id(renRenovacion.getIdclase_niza_renovacion());

            Historial historial = new Historial();
            historial.setIdUsuario(usuario.getIdusuario());
            historial.setId(sigSignoMarca.getIdSignoMarca());
            historial.setIdLogTrans(logTransGuardado.getIdLogTrans());
            historial.setEstado("AC");
            Date fechaHora = comunService.obtenerFechaHoraServidor(1L);
            historial.setFechaOperacion(fechaHora);
            historial.setOperacion("RENOVACION");
//            RenRenovacion renRenovacion = renRenovacionService.obtieneRenovacionPorIdSolicitud(renSolicitudRenovacion.getIdsolicitudrenovacion());
            String numeroRenovacion = numeroOrdinal(sigSignoMarca.getOrdenRenovacion());
            String descripcion = numeroRenovacion + " " + "" + "RENOVACION N° " + " " + sigSignoMarca.getNumeroRenovacion() + "-" + sigSignoMarca.getExtensionRenovacion() + " " + "Clase:" + nizaClase.getNumeroClaseNiza() + "/Vers." + nizaClase.getNumeroEdicion();
            historial.setDescripcion(descripcion);
            historial.setEstadoMarcaDescripcion("RENOVADA");
            historial.setUbicacionDescripcion("BIBLIOTECA");
            historial.setObservacion(descripcion);
            historial.setGestionRenovación(sigSignoMarca.getGestion());
            Historial historilGuardado = historialService.crudHistorial_Renovacion(historial, "SIG", 1);
            return historilGuardado;
        } catch (Exception e) {
        }
        return null;

    }

    public Historial guardahistorialRenovacion(RenSolicitudRenovacion renSolicitudRenovacion, Usuario usuario) throws Exception {
        try {

//            ClaseNiza nizaClase = claseNizaService.listarClaseNiza_id(renSolicitudRenovacion.getIdclase_niza_reclasificacion());
            Historial historial = new Historial();
            Date fechaRegistro = comunService.obtenerFechaServidor(1L);
            LogTrans logTransGuardado = logTransService.crudLogTrans(new LogTrans(usuario.getIdusuario(), fechaRegistro), 1);
            historial.setIdUsuario(usuario.getIdusuario());
            historial.setId(renSolicitudRenovacion.getIdsolicitudrenovacion());
            historial.setOperacion("REGISTRO DE RENOVACION");
            RenRenovacion renRenovacion = obtieneRenovacionPorIdSolicitud(renSolicitudRenovacion.getIdsolicitudrenovacion());
            String numeroRenovacion = numeroOrdinal(renRenovacion.getOrden_renovacion());
            String descripcion = "REGISTRO DE LA" + " " + numeroRenovacion + " " + "RENOVACION N° " + " " + renRenovacion.getNumero_renovacion() + "-" + renRenovacion.getSerie_renovacion();
            historial.setDescripcion(descripcion);
            historial.setGestionRenovación(renRenovacion.getGestion_renovacion());
            historial.setEstado("AC");
            historial.setEstadoMarcaDescripcion("Concedido(a)");
            historial.setFechaOperacion(renRenovacion.getFecha_renovacion());
            historial.setIdLogTrans(renRenovacion.getIdlogtrans());
            Historial historialGuardado = historialService.crudHistorial_Renovacion(historial, "REN", 1);

            return historialGuardado;
        } catch (Exception e) {
            throw e;
        }

    }

    @Override
    public String devuelvemesLiteral(int mesFecha) {
        String result;
        int mes = 0;
        try {
            mes = mesFecha;
        } catch (Exception ex) {
        }
        switch (mes) {
            case 0: {
                result = "Enero";
                break;
            }
            case 1: {
                result = "Febrero";
                break;
            }
            case 2: {
                result = "Marzo";
                break;
            }
            case 3: {
                result = "Abril";
                break;
            }
            case 4: {
                result = "Mayo";
                break;
            }
            case 5: {
                result = "Junio";
                break;
            }
            case 6: {
                result = "Julio";
                break;
            }
            case 7: {
                result = "Agosto";
                break;
            }
            case 8: {
                result = "Septiembre";
                break;
            }
            case 9: {
                result = "Octubre";
                break;
            }
            case 10: {
                result = "Noviembre";
                break;
            }
            case 11: {
                result = "Diciembre";
                break;
            }
            default: {
                result = "Error";
                break;
            }
        }
        return result;
    }

    @Override
    public List<RenRenovacion> lista_renRenovacion_simple(String filtro, String textoBusquedaSimple) {
        String SQL = "";
        switch (filtro) {
            case "SOLI":
                SQL = "select * from renrenovacion where idsolicitudrenovacion  in "
                        + "(select idsolicitudrenovacion from rensolicitudrenovacion where idsolicitudrenovacion in "
                        + "(select idsolicitudrenovacion from rentitularregistrado where nombre_razonsocial like '%" + textoBusquedaSimple + "%'))";
                break;
            case "APOD":
                SQL = "select * from renrenovacion where idsolicitudrenovacion  in "
                        + "(select idsolicitudrenovacion from rensolicitudrenovacion where idsolicitudrenovacion in "
                        + "(select idsolicitudrenovacion from rensolicitanteapoderado where tipo_persona='APOD' and  nombre_razonsocial like '%" + textoBusquedaSimple + "%'))";

                break;
            case "SIGN":
                SQL = "select * from renrenovacion  where signo_registrado like '%" + textoBusquedaSimple + "%'";
                break;
            default:
                SQL = "select * from renrenovacion where estado_renovacion = 'INGR'";
                break;
        }
        List<RenRenovacion> lista = jdbcTemplate.query(SQL, new RenRenovacionMapper());
        if (!lista.isEmpty()) {
            return lista;
        }
        return new ArrayList<RenRenovacion>();
    }

    @Override
    public List<RenSolicitudRenovacion> lista_renRenovacion_simpleSolicitudes(String filtro, String textoBusquedaSimple) throws Exception {
        try {
            String SQL = "";
            //System.out.println("texo1" + textoBusquedaSimple);
            textoBusquedaSimple = (textoBusquedaSimple.toUpperCase());
            //System.out.println("texo2" + textoBusquedaSimple);

            switch (filtro) {
                case "SOLI":
                    /*SQL = "select * from rensolicitudrenovacion where idsolicitudrenovacion  in "
                     + "(select idsolicitudrenovacion from rensolicitudrenovacion where idsolicitudrenovacion in "
                     + "(select idsolicitudrenovacion from rentitularregistrado where nombre_razonsocial like '%" + textoBusquedaSimple + "%'))";*/

                    // Consulta que extrae la clase niza desde tabla claseniza mediante idclaseniza
                    SQL = "SELECT idsolicitudrenovacion, r.idlogtrans, id_recibo_solicitud, id_recibo_titulo, "
                            + "sr, gestion_sr, fecha_ingreso, nro_formulario, estado_renovacion, "
                            + "ubicacion_renovacion, numero_ultima_renovacion, serie_ultima_renovacion, "
                            + "numero_penultima_renovacion, serie_penultima_renovacion, oficina, "
                            + "numero_recibo, serie_recibo, numero_titulo, serie_titulo, idclase_niza_reclasificacion, "
                            + "lista_productos_limitacion, sm, signo_registrado, c.numero_clase_niza AS idclase_niza_registrado, "
                            + "tipo_genero, numero_registro_registrado, serie_registro_registrado, "
                            + "fecha_registro_registrado, marca_acomp, reg_lc_registrado, serie_lc_registrado, "
                            + "fecha_lc_registrado, r.estado "
                            + "FROM rensolicitudrenovacion r, claseniza c "
                            + "WHERE r.idclase_niza_registrado = c.idclaseniza "
                            + "AND idsolicitudrenovacion  IN "
                            + "(select idsolicitudrenovacion FROM rensolicitudrenovacion WHERE idsolicitudrenovacion IN "
                            + "(select idsolicitudrenovacion FROM rentitularregistrado WHERE nombre_razonsocial LIKE '%" + textoBusquedaSimple + "%'))";
                    break;
                case "APOD":
                    /*SQL = "select * from rensolicitudrenovacion where idsolicitudrenovacion  in "
                     + "(select idsolicitudrenovacion from rensolicitudrenovacion where idsolicitudrenovacion in "
                     + "(select idsolicitudrenovacion from rensolicitanteapoderado where tipo_persona='APOD' and  nombre_razonsocial like '%" + textoBusquedaSimple + "%'))";*/

                    // Consulta que extrae la clase niza desde tabla claseniza mediante idclaseniza
                    SQL = "SELECT idsolicitudrenovacion, r.idlogtrans, id_recibo_solicitud, id_recibo_titulo, "
                            + "sr, gestion_sr, fecha_ingreso, nro_formulario, estado_renovacion, "
                            + "ubicacion_renovacion, numero_ultima_renovacion, serie_ultima_renovacion, "
                            + "numero_penultima_renovacion, serie_penultima_renovacion, oficina, "
                            + "numero_recibo, serie_recibo, numero_titulo, serie_titulo, idclase_niza_reclasificacion, "
                            + "lista_productos_limitacion, sm, signo_registrado, c.numero_clase_niza AS idclase_niza_registrado, "
                            + "tipo_genero, numero_registro_registrado, serie_registro_registrado, "
                            + "fecha_registro_registrado, marca_acomp, reg_lc_registrado, serie_lc_registrado, "
                            + "fecha_lc_registrado, r.estado "
                            + "FROM rensolicitudrenovacion r, claseniza c "
                            + "WHERE r.idclase_niza_registrado = c.idclaseniza "
                            + "AND idsolicitudrenovacion  IN "
                            + "(SELECT idsolicitudrenovacion FROM rensolicitudrenovacion WHERE idsolicitudrenovacion IN "
                            + "(SELECT idsolicitudrenovacion FROM rensolicitanteapoderado WHERE tipo_persona='APOD' AND  nombre_razonsocial LIKE '%" + textoBusquedaSimple + "%'))";
                    break;
                case "SIGN":
                    //SQL = "select * from rensolicitudrenovacion  where signo_registrado like '%" + textoBusquedaSimple + "%'";

                    // Consulta que extrae la clase niza desde tabla claseniza mediante idclaseniza
                    SQL = "SELECT idsolicitudrenovacion, r.idlogtrans, id_recibo_solicitud, id_recibo_titulo,"
                            + "sr, gestion_sr, fecha_ingreso, nro_formulario, estado_renovacion,"
                            + "ubicacion_renovacion, numero_ultima_renovacion, serie_ultima_renovacion,"
                            + "numero_penultima_renovacion, serie_penultima_renovacion, oficina,"
                            + "numero_recibo, serie_recibo, numero_titulo, serie_titulo, idclase_niza_reclasificacion,"
                            + "lista_productos_limitacion, sm, signo_registrado, c.numero_clase_niza AS idclase_niza_registrado,"
                            + "tipo_genero, numero_registro_registrado, serie_registro_registrado,"
                            + "fecha_registro_registrado, marca_acomp, reg_lc_registrado, serie_lc_registrado,"
                            + "fecha_lc_registrado, r.estado "
                            + "FROM rensolicitudrenovacion r, claseniza c "
                            + "WHERE r.idclase_niza_registrado = c.idclaseniza AND r.signo_registrado LIKE '%" + textoBusquedaSimple + "%'";
//                    SQL = "SELECT * FROM rensolicitudrenovacion WHERE soundex('" + textoBusquedaSimple + "' ) = soundex((signo_registrado) )";
//                    SQL = "SELECT * FROM rensolicitudrenovacion WHERE CONTAINS( signo_registrado,'"+textoBusquedaSimple+"*' )";
                    break;
                default:
                    SQL = "select * from rensolicitudrenovacion where estado_renovacion = 'INGR'";

                    break;
            }
            List<RenSolicitudRenovacion> lista = jdbcTemplate.query(SQL, new RenSolicitudRenovacionMapper());
            if (!lista.isEmpty()) {
                return lista;
            }
            return new ArrayList<RenSolicitudRenovacion>();

        } catch (Exception e) {
        }
        return null;
    }

    @Override
    public List<RenSolicitudRenovacion> lista_renRenovacion_simpleRegistro(String filtro, int numeroRegistro, String serie) throws Exception {
        try {
            String SQL = "";

            //SQL = "select * from rensolicitudrenovacion  where  numero_registro_registrado='" + numeroRegistro + "' and serie_registro_registrado='" + serie + "'";
            
            // Consulta que extrae la clase niza desde tabla claseniza mediante idclaseniza
            SQL = "SELECT idsolicitudrenovacion, r.idlogtrans, id_recibo_solicitud, id_recibo_titulo,"
                    + "sr, gestion_sr, fecha_ingreso, nro_formulario, estado_renovacion,"
                    + "ubicacion_renovacion, numero_ultima_renovacion, serie_ultima_renovacion,"
                    + "numero_penultima_renovacion, serie_penultima_renovacion, oficina,"
                    + "numero_recibo, serie_recibo, numero_titulo, serie_titulo, idclase_niza_reclasificacion,"
                    + "lista_productos_limitacion, sm, signo_registrado, c.numero_clase_niza AS idclase_niza_registrado,"
                    + "tipo_genero, numero_registro_registrado, serie_registro_registrado,"
                    + "fecha_registro_registrado, marca_acomp, reg_lc_registrado, serie_lc_registrado,"
                    + "fecha_lc_registrado, r.estado "
                    + "FROM rensolicitudrenovacion r, claseniza c "
                    + "WHERE r.idclase_niza_registrado = c.idclaseniza "
                    + "AND r.numero_registro_registrado='" + numeroRegistro + "' and r.serie_registro_registrado='" + serie + "'";

            List<RenSolicitudRenovacion> lista = jdbcTemplate.query(SQL, new RenSolicitudRenovacionMapper());
            if (!lista.isEmpty()) {
                return lista;
            }
            return new ArrayList<RenSolicitudRenovacion>();

        } catch (Exception e) {
        }
        return null;
    }

    @Override
    public RenRenovacion obtieneRenRenovacionPorNumeroYSerieRenovacion(int numeroRenovacion, String serieRenovacion) throws Exception {
        try {
            String SQL = "select * from renrenovacion where numero_renovacion=? and serie_renovacion=? and estado='" + EnumEstado.ACTIVO.getCodigo() + "'";
            if (jdbcTemplate.query(SQL, new RenRenovacionMapper(), numeroRenovacion, serieRenovacion) != null) {
                return (RenRenovacion) jdbcTemplate.query(SQL, new RenRenovacionMapper(), numeroRenovacion, serieRenovacion).get(0);
            }
        } catch (Exception e) {
            throw e;
        }
        return new RenRenovacion();

    }

    public List<RenRenovacion> obtieneListRenovaionPorSm(SigSignoMarca sigSignoMarca) throws Exception {
        try {
//            System.out.println("listaaaaaaaaaaaa" + sigSignoMarca.getIdSignoMarca());
//            System.out.println("listaaaaaaaaaaaa" + sigSignoMarca.getNumero());
            String Sql = "select * from renrenovacion where estado='" + EnumEstado.ACTIVO.getCodigo() + "' and idsolicitudrenovacion in (select idsolicitudrenovacion from rensolicitudrenovacion where sm=? );";
            if (jdbcTemplate.query(Sql, new RenRenovacionMapper(), sigSignoMarca.getNumero()).isEmpty()) {
                return jdbcTemplate.query(Sql, new RenRenovacionMapper(), sigSignoMarca.getNumero());
            }
        } catch (Exception e) {
            throw e;
        }
        List<RenRenovacion> lstRenRenovacion = new ArrayList<>();
        return lstRenRenovacion;
    }

    @Override
    public RenRenovacion obtieneRenRenovacionPorNumeroYgestion(long numeroRenovacion, int gestionRenovacion) throws Exception {
        try {
            String SQL = "SELECT r.* FROM renrenovacion r WHERE r.idsolicitudrenovacion IN\n"
                    + "(SELECT rs.idsolicitudrenovacion FROM rensolicitudrenovacion rs WHERE rs.sr = ? AND rs.gestion_sr = ? AND rs.estado='AC') AND r.estado='AC';";
            if (!jdbcTemplate.query(SQL, new RenRenovacionMapper(), numeroRenovacion, gestionRenovacion).isEmpty()) {
                return (RenRenovacion) jdbcTemplate.query(SQL, new RenRenovacionMapper(), numeroRenovacion, gestionRenovacion).get(0);
            }

        } catch (Exception e) {
            throw e;
        }

        return new RenRenovacion();
    }

}
