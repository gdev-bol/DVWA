/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.servicio.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import snp.gob.bo.gimodel.entidad.Correlativo;
import snp.gob.bo.gimodel.entidad.Historial;
import snp.gob.bo.gimodel.entidad.LogTrans;
import snp.gob.bo.gimodel.entidad.ObservacionTramite;
import snp.gob.bo.gimodel.entidad.Seguimiento;
import snp.gob.bo.gimodel.entidad.SigDetallePublicacion;
import snp.gob.bo.gimodel.entidad.SigPublicacion;
import snp.gob.bo.gimodel.entidad.SigSignoMarca;
import snp.gob.bo.gimodel.entidad.Usuario;
import snp.gob.bo.gimodel.enums.EnumEstado;
import snp.gob.bo.gimodel.enums.EnumEstadoPublicacion;
import snp.gob.bo.gimodel.enums.EnumPrefijoTablas;
import snp.gob.bo.gimodel.mapper.SigDetallePublicacionMapper;
import snp.gob.bo.gimodel.mapper.SigSignoMarcaMapper;
import snp.gob.bo.gimodel.servicio.ComunService;
import snp.gob.bo.gimodel.servicio.CorrelativoService;
import snp.gob.bo.gimodel.servicio.DominioService;
import snp.gob.bo.gimodel.servicio.EtapaService;
import snp.gob.bo.gimodel.servicio.FlujoTiempoService;
import snp.gob.bo.gimodel.servicio.HistorialService;
import snp.gob.bo.gimodel.servicio.LogTransService;
import snp.gob.bo.gimodel.servicio.ObservacionTramiteService;
import snp.gob.bo.gimodel.servicio.SeguimientoService;
import snp.gob.bo.gimodel.servicio.SigDetallePublicacionService;
import snp.gob.bo.gimodel.servicio.SigPublicacionService;
import snp.gob.bo.gimodel.servicio.SigSignoMarcaService;
import snp.gob.bo.gimodel.servicio.UsuarioService;
import static snp.gob.bo.gimodel.servicio.impl.SeguimientoServiceImpl.diferenciaEnDias;

/**
 *
 * @author susana
 */
@Service("sigDetallePublicacionService")
public class SigDetallePublicacionServiceImpl implements SigDetallePublicacionService {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Override
    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    @Autowired
    private SigSignoMarcaService sigSignoMarcaService;
    @Autowired
    private ComunService comunService;
    @Autowired
    private ObservacionTramiteService observacionTramiteService;
    @Autowired
    private CorrelativoService correlativoService;
    @Autowired
    private SigPublicacionService sigPublicacionService;
    @Autowired
    private SeguimientoService seguimientoService;
    @Autowired
    private HistorialService historialService;
    @Autowired
    private EtapaService etapaService;
    @Autowired
    private LogTransService logTransService;
    @Autowired
    private DominioService dominioService;
    @Autowired
    private FlujoTiempoService flujoTiempoService;
    @Autowired
    private UsuarioService usuarioService;

    @Override

    public SigDetallePublicacion guardar_modificar_listar_SigDetallePublicacion(SigDetallePublicacion sigDetallePublicacion, Integer operacion) {
        String SQL = "select * from crud_sigdetallepublicacion(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
        SigDetallePublicacion sigdetallepublicacion = (SigDetallePublicacion) jdbcTemplate.queryForObject(SQL, new SigDetallePublicacionMapper(),
                sigDetallePublicacion.getIddetallepublicacion(),
                sigDetallePublicacion.getIdpublicacion(),
                sigDetallePublicacion.getIdsignomarca(),
                sigDetallePublicacion.getIdlogtrans(),
                sigDetallePublicacion.getNumero_publicacion(),
                sigDetallePublicacion.getSm(),
                sigDetallePublicacion.getSm_descripcion(),
                sigDetallePublicacion.getFecha_ingreso(),
                sigDetallePublicacion.getMarca(),
                sigDetallePublicacion.getClase(),
                sigDetallePublicacion.getTipo_signo_descripcion(),
                sigDetallePublicacion.getTipo_genero(),
                sigDetallePublicacion.getLista_productos(),
                sigDetallePublicacion.getDescripcion_signo(),
                sigDetallePublicacion.getNombre_titular(),
                sigDetallePublicacion.getDocumento_titular(),
                sigDetallePublicacion.getPais_titular(),
                sigDetallePublicacion.getDescripcion_pais_titular(),
                sigDetallePublicacion.getDescripcion_departamento_titular(),
                sigDetallePublicacion.getDireccion_titular(),
                sigDetallePublicacion.getNombre_apoderado(),
                sigDetallePublicacion.getDocumento_apoderado(),
                sigDetallePublicacion.getDireccion_apoderado(),
                sigDetallePublicacion.getPrioridad(),
                sigDetallePublicacion.getFecha_prioridad(),
                sigDetallePublicacion.getPais_prioridad(),
                sigDetallePublicacion.getPais_prio_descripcion(),
                sigDetallePublicacion.getNumero_seccion(),
                sigDetallePublicacion.getSeccion(),
                sigDetallePublicacion.getSubseccion(),
                sigDetallePublicacion.getPublicado(),
                sigDetallePublicacion.getEstado(), operacion
        );
        return sigdetallepublicacion;
    }

    @Override
    public List<SigDetallePublicacion> listaSigDetallePublicacionXidpublicacion(Long idpublicacion) {
        String SQL = "select * from lista_sigdetallepublicacion_idpublicacion(?);";
        List<SigDetallePublicacion> listaDetallePublicacion = jdbcTemplate.query(SQL, new SigDetallePublicacionMapper(), idpublicacion);
        if (!listaDetallePublicacion.isEmpty()) {
            return listaDetallePublicacion;
        }
        return new ArrayList<SigDetallePublicacion>();
    }

    @Override
    public void devolverDetallePublicacion(SigDetallePublicacion sigDetallePublicacion, String observacion, Long idusuario) {
        try {
            Date fechaServidor = comunService.obtenerFechaHoraServidor(1L);
            LogTrans logTransGuardado = logTransService.crudLogTrans(new LogTrans(idusuario, fechaServidor), 1);
            sigDetallePublicacion.setEstado(EnumEstado.NO_ACTIVO.getCodigo());
            guardar_modificar_listar_SigDetallePublicacion(sigDetallePublicacion, 2);
            String SQL = "select * from sigsignomarca where idsignomarca=" + sigDetallePublicacion.getIdsignomarca() + " and estado= 'AC'";
            SigSignoMarca signoMarca = (SigSignoMarca) jdbcTemplate.queryForObject(SQL, new SigSignoMarcaMapper());

            if (signoMarca != null) {

                Historial historial = new Historial();
                historial.setId(signoMarca.getIdSignoMarca());
                historial.setIdUsuario(idusuario);
                historial.setIdLogTrans(logTransGuardado.getIdLogTrans());
                historial.setOperacion("MOD");
                String estado = dominioService.obtenerNombrePorCodigoDominio(signoMarca.getEstadoMarca(), "estado_marca");
                historial.setEstadoMarcaDescripcion("OBSERVADA");             //historial.setEstadoMarcaDescripcion(estado);       
                historial.setSeccion("SIGNO");
                historial.setFechaOperacion(fechaServidor);
                historial.setDescripcion("[Situación actual:" + estado + "]");
                historial.setEstado(EnumEstado.ACTIVO.getCodigo());
                historialService.crudHistorial(historial, "SIG", 1);

                signoMarca.setEstadoMarca("OBS");
                sigSignoMarcaService.crudSigSignoMarca(signoMarca, 2);

                if (observacion != null) {
                    observacion = observacion.trim();
                    if (!observacion.equals("")) {
                        ObservacionTramite observacionTramite = new ObservacionTramite();
                        observacionTramite.setIdObservacionTramite(logTransGuardado.getIdLogTrans());
                        observacionTramite.setId(signoMarca.getIdSignoMarca());
                        observacionTramite.setIdUsuario(idusuario);
                        observacionTramite.setIdLogTrans(logTransGuardado.getIdLogTrans());
                        observacionTramite.setEditable(true);
                        observacionTramite.setFechaObservacion(fechaServidor);
                        observacionTramite.setEtapaTramite("PPP");
                        observacionTramite.setDescripcion(observacion);
                        observacionTramite.setEstado(EnumEstado.ACTIVO.getCodigo());
                        observacionTramiteService.guardar_modificar_listar_ObservacionTramite(observacionTramite, "SIG", 1);
                    }
                }
                Seguimiento seguimienoBorrar = seguimientoService.listar_SigSeguimiento_etapa_iniciado(signoMarca.getIdSignoMarca(), "RPP");
                if (seguimienoBorrar != null) {
                    seguimienoBorrar.setEstado(EnumEstado.NO_ACTIVO.getCodigo());
                    seguimientoService.guardar_modificar_listar_Seguimiento(seguimienoBorrar, EnumPrefijoTablas.SIGNO.getCodigo(), 2);
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(SigDetallePublicacionServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<SigDetallePublicacion> lista_generarSigDetallePublicacion(Date fecha_ingreso_vent, String listaGacetaNoCumple) {
        String fecha_ingreso = "";
        try {
            //  Date fecha_ingreso_vent = comunService.obtenerFechaServidor(1l);
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            fecha_ingreso = formato.format(fecha_ingreso_vent.getTime());

        } catch (Exception ex) {
            Logger.getLogger(SigDetallePublicacionServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        String SQL = "select \n"
                + "null as iddetallepublicacion, null as idpublicacion,  sig.idsignomarca, \n"
                + "null as idlogtrans, sig.numero_publicacion, sig.sm, null as sm_descripcion,\n"
                + "sig.fecha_ingreso, sig.signo as marca, ssc.numero_clase_niza as clase,   \n"
                + " (lista_sigtiposigno_idsignomarca_descripcion(sig.idsignomarca)) as tipo_signo_descripcion, \n"
                + "domi.nombre as tipo_genero, \n"
                + "ssc.lista_producto as lista_productos, sig.descripcion_signo ,\n"
                + "soap.nombres nombre_titular, soap.documento_titular,\n"
                + "soap.pais_titular, soap.descripcion_pais_titular, \n"
                + "soap.descripcion_departamento_titular, soap.direccion_titular,\n"
                + "apo.nombres nombre_apoderado, apo.documento_apoderado, apo.direccion_apoderado, \n"
                + "prip.prioridad, prip.fecha_prioridad, \n"
                + "prip.pais_prioridad, prip.pais_prio_descripcion,\n"
                + "--null as prioridad, null fecha_prioridad, \n"
                + "--null as pais_prioridad, null as prio_descripcion,\n"
                + "(select ssp.seccion from seccionsubpublicacion ssp, dominio dom \n"
                + "where dom.dominio='estado_marca' \n"
                + "            and dom.codigo=(select dom.codigo from dominio dom \n"
                + "            where dom.dominio = 'estado_marca' \n"
                + "            and dom.codigo = sig.estado_marca) \n"
                + "            and dom.iddominio = ssp.iddominio) as numero_seccion,\n"
                + "-----------------\n"
                + " (select ssp.descripcion from seccionsubpublicacion ssp, dominio dom \n"
                + " where dom.dominio='estado_marca' \n"
                + "            and dom.codigo=(select dom.codigo from dominio dom \n"
                + "            where dom.dominio = 'estado_marca' \n"
                + "            and dom.codigo = sig.estado_marca) \n"
                + "            and dom.iddominio = ssp.iddominio) as seccion,   \n"
                + "------------------\n"
                + "(select ssp.descripcion from seccionsubpublicacion ssp, dominio dom \n"
                + "where dom.dominio='tipo_signo' \n"
                + "            and dom.nombre=(lista_sigtiposigno_idsignomarca_descripcion(sig.idsignomarca)) \n"
                + "            and dom.iddominio = ssp.iddominio) as subseccion,\n"
                + "  false as publicado, null as estado,\n"
                + "domest.codigo as estado_marca\n"
                + "\n"
                + "from sigsignomarca sig  \n"
                + "join sigsignoclaseniza ssc on \n"
                + "(ssc.idsignomarca = sig.idsignomarca and ssc.estado='AC')\n"
                + "join (select idsignomarca,\n"
                + "string_agg(concat((select dom.codigo from dominio dom where dom.codigo = sol.pais\n"
                + "            and dom.dominio='pais' and estado = 'AC')\n"
                + "), '; ') pais_titular, \n"
                + "string_agg(concat((select dom.nombre from dominio dom where dom.codigo = sol.pais\n"
                + "            and dom.dominio='pais' and estado = 'AC')\n"
                + "), '; ') descripcion_pais_titular,\n"
                + "string_agg(concat((select dom.nombre from dominio dom where dom.codigo = sol.solicitud_departamento\n"
                + "            and dom.dominio='lugar_expedicion' and estado = 'AC')\n"
                + "), '; ') descripcion_departamento_titular, \n"
                + "string_agg(concat(trim(from nombre_razonsocial),' ',trim(from primer_apellido),' ',trim(from segundo_apellido)), '; ') nombres, \n"
                + "string_agg(concat(numero_documento), '; ') documento_titular,\n"
                + "string_agg(concat(direccion), '; ') direccion_titular    \n"
                + "from sigsolicitanteapoderado sol \n"
                + "where sol.idsignomarca = idsignomarca  \n"
                + "and sol.tipo_persona = 'SOLI' and sol.estado = 'AC' group by 1 order by 1 asc) \n"
                + "soap on ( sig.idsignomarca = soap.idsignomarca ) \n"
                + "\n"
                + "full outer join (select idsignomarca, string_agg(concat(trim(from nombre_razonsocial),' ',\n"
                + "trim(from primer_apellido),' ',trim(from segundo_apellido)), '; ') nombres,\n"
                + "string_agg(concat(numero_documento), '; ') documento_apoderado, \n"
                + "string_agg(concat(direccion), '; ') direccion_apoderado \n"
                + "from sigsolicitanteapoderado apod   \n"
                + "where apod.idsignomarca = idsignomarca   and apod.tipo_persona = 'APOD'   \n"
                + "and apod.estado = 'AC'   group by 1) \n"
                + "apo on ( sig.idsignomarca = apo.idsignomarca ) \n"
                + "\n"
                + "join (select idsignomarca, string_agg(concat(\n"
                + "      (select dom.nombre from dominio dom where dom.codigo = sts.tipo_signo\n"
                + "            and dom.dominio='tipo_signo' and estado = 'AC')\n"
                + "), '; ') descripcion  \n"
                + "from sigtiposigno sts \n"
                + "where sts.idsignomarca = idsignomarca   \n"
                + "and sts.estado = 'AC' group by 1) \n"
                + "tsig on ( sig.idsignomarca = tsig.idsignomarca )\n"
                + "\n"
                + "full outer join (select idsignomarca, \n"
                + "string_agg(concat(nombre_numero), '; ') prioridad,\n"
                + "string_agg(concat(fecha), '; ') fecha_prioridad,\n"
                + "string_agg(concat((select dom.codigo from dominio dom where dom.codigo = ppr.lugar\n"
                + "            and dom.dominio='pais' and estado = 'AC')\n"
                + "), '; ') pais_prioridad,\n"
                + "string_agg(concat((select dom.nombre from dominio dom where dom.codigo = ppr.lugar\n"
                + "            and dom.dominio='pais' and estado = 'AC')\n"
                + "), '; ') pais_prio_descripcion   \n"
                + "from sigprioridadpreferencia ppr \n"
                + "where ppr.idsignomarca = idsignomarca  --and ppr.idprioridadpreferencia=3808\n"
                + "and ppr.tipo_prioridad='PRI' AND ppr.tipo_interes='EXT' AND ppr.estado = 'AC' group by 1)\n"
                + "prip on (sig.idsignomarca = prip.idsignomarca ) \n"
                + "\n"
                + "join dominio domi on (domi.dominio='tipo_genero' and domi.estado = 'AC')\n"
                + "join dominio domest  on domest.dominio = 'estado_marca' and domest.estado = 'AC'\n"
                + "\n"
                + "\n"
                + "where domi.codigo = sig.tipo_genero and domi.estado = 'AC'  \n"
                + "and sig.estado = 'AC' and sig.estado_marca=domest.codigo and domest.estado = 'AC'  \n"
                + "and DATE_TRUNC('day', sig.fecha_ingreso)  <= '" + fecha_ingreso + "'::timestamp  \n"
                + "\n"
                + "and sig.estado_marca in ('PP','SPP','PPO','FEPP') \n"
                + "and sig.idsignomarca not in ("
                + "select idsignomarca from sigdetallepublicacion dpub \n"
                + "join sigpublicacion spub on (dpub.idpublicacion = spub.idpublicacion \n"
                + "and spub.numero_gaceta in (" + listaGacetaNoCumple + ") and dpub.estado = 'AC')"
                + ")\n"
                + "ORDER BY numero_seccion asc, subseccion, sig.numero, sig.gestion, sig.extension_marca";

        List<SigDetallePublicacion> listaDetallePublicacion = jdbcTemplate.query(SQL, new SigDetallePublicacionMapper());
        if (!listaDetallePublicacion.isEmpty()) {
            return listaDetallePublicacion;
        }
        return new ArrayList<SigDetallePublicacion>();
    }

    @Override
    public Long contador_sigDetallePublicacion(Long idSigPublicacion) {
        Long cantidad = 0l;
        if (idSigPublicacion != null) {
            String sqlTotal = "SELECT count(*) FROM sigdetallepublicacion where idpublicacion=" + idSigPublicacion + " and estado='AC'";
            cantidad = jdbcTemplate.queryForObject(sqlTotal, Long.class);
        }
        return cantidad;
    }

    @Override
    public Boolean verificarMarcasPublicacion(Date fecha) {
        String fecha_ingreso = "";
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        fecha_ingreso = formato.format(fecha.getTime());

        String SQL = "select *  "
                + " from sigsignomarca ssm where DATE_TRUNC('day', ssm.fecha_ingreso)  <= '" + fecha_ingreso + "'::timestamp "
                + " and ssm.estado_marca in ('PP','PPO','SPP','FEPP') "
                + " --and ssm.idsignomarca not in (select idsignomarca from sigdetallepublicacion where estado='AC')"
                + " and ssm.estado= 'AC'";

        return !jdbcTemplate.query(SQL, new SigSignoMarcaMapper()).isEmpty();
    }

    @Override
    public void numeracionSigDetallePublicacion(Long idPublicacion) {
        List<SigDetallePublicacion> listaDetallePublicacion = listaSigDetallePublicacionXidpublicacion(idPublicacion);
        SigPublicacion publicacionAux = new SigPublicacion();
        publicacionAux.setIdpublicacion(idPublicacion);
        SigPublicacion publicacion = sigPublicacionService.guardar_modificar_listar_SigPublicacion(publicacionAux, 4);
        Correlativo correlativo = correlativoService.encuentraCorrelativoTabla(1L, "NPUB");
        Long numero = 1 + (long) correlativo.getUltimo_numero_asignado();
        Long inicio = 1 + (long) correlativo.getUltimo_numero_asignado();
        try {
            for (SigDetallePublicacion item : listaDetallePublicacion) {

                if (item.getNumero_publicacion() == 0 || item.getNumero_publicacion() == null) {
                    item.setNumero_publicacion(numero);
                    guardar_modificar_listar_SigDetallePublicacion(item, 2);
                    numero++;
                }
                //POR ELSE DEBERIAMOS PREGUNTAR SI ES FEPP si es asi se mantiene el numero sino se renumera (posible anulacion de obrados)
                //pero tb puede ser error de estado por tanto no es cerrado para numerar o no numerar el tecnico debe revisar siempre la 
                //la numeracion asignada por el sistema
            }
            Long fin = numero - 1;
            publicacion.setInicio(inicio);
            publicacion.setFin(fin);
//            System.out.println("INICIO  " + inicio + "   fin  " + fin);
            sigPublicacionService.guardar_modificar_listar_SigPublicacion(publicacion, 2);
            correlativo.setUltimo_numero_asignado(fin.intValue());
            correlativoService.crudCorrelativo(correlativo, 2);
        } catch (Exception ex) {
            Logger.getLogger(SigDetallePublicacionServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void seguimiento_SigDetallePublicacion(Long idPublicacion, Long idusuario, Date fechaPublicacion, Date fechaEnvio) {
        
        SigPublicacion publicacionBuscar = new SigPublicacion();
        publicacionBuscar.setIdpublicacion(idPublicacion);
        SigPublicacion publicacion = sigPublicacionService.guardar_modificar_listar_SigPublicacion(publicacionBuscar, 4);
        List<SigDetallePublicacion> listaDetallePublicacion = listaSigDetallePublicacionXidpublicacion(idPublicacion);
        Map<String, String> datosPublicacionMap = new LinkedHashMap<String, String>();
        String descripcion = "";

        int conteoDiasHabiles;
        Long diasAcumulados;
        for (SigDetallePublicacion item : listaDetallePublicacion) {
            diasAcumulados = 0l;
            conteoDiasHabiles = 0;

            try {
                List<Usuario> listUsuario = usuarioService.listaUsuarioXidPagina(idusuario);
                Date fechaServidor = comunService.obtenerFechaHoraServidor(1L);
                LogTrans logTransGuardado = logTransService.crudLogTrans(new LogTrans(idusuario, fechaServidor), 1);
                /**
                 * *cerrar seguimiento de revision si existe***
                 */
                Seguimiento seguimienoCerrar = seguimientoService.listar_SigSeguimiento_etapa_iniciado(item.getIdsignomarca(), "RPP");
                if (seguimienoCerrar != null) {

                    diasAcumulados = seguimientoService.conteoPLazoReal(EnumPrefijoTablas.SIGNO.getCodigo(), item.getIdsignomarca());
                    seguimienoCerrar.setFechaFin(fechaEnvio);
                    conteoDiasHabiles = seguimientoService.diasLaboralesSigno(seguimienoCerrar.getFechaRecepcion(), seguimienoCerrar.getFechaFin(), 1L);
                    seguimienoCerrar.setPlazoReal(conteoDiasHabiles);
                    seguimienoCerrar.setTotalTiempo(diasAcumulados + conteoDiasHabiles);
                    seguimientoService.guardar_modificar_listar_Seguimiento(seguimienoCerrar, EnumPrefijoTablas.SIGNO.getCodigo(), 2);
                }

                diasAcumulados = seguimientoService.conteoPLazoReal(EnumPrefijoTablas.SIGNO.getCodigo(), item.getIdsignomarca());
                //CERRAR SEGUIMIENTO                
                Seguimiento seguimientoNuevo = new Seguimiento();
                seguimientoNuevo.setId(item.getIdsignomarca());
                seguimientoNuevo.setIdUsuario((idusuario));
                seguimientoNuevo.setIdLogTrans(logTransGuardado.getIdLogTrans());
                seguimientoNuevo.setSm(item.getSm());
                seguimientoNuevo.setNumeroPublicacion(item.getNumero_publicacion());
                seguimientoNuevo.setNumeroRegistro(null);
                seguimientoNuevo.setSerieRegistro(null);
                seguimientoNuevo.setEtapa("PPP");
                seguimientoNuevo.setFechaRecepcion(fechaEnvio);
                seguimientoNuevo.setFechaFin(fechaPublicacion);

                conteoDiasHabiles = seguimientoService.diasLaboralesSigno(seguimientoNuevo.getFechaRecepcion(), seguimientoNuevo.getFechaFin(), 1L);
                seguimientoNuevo.setPlazoReal(conteoDiasHabiles);
                seguimientoNuevo.setTotalTiempo(diasAcumulados + conteoDiasHabiles);
                seguimientoNuevo.setObservacion(null);
                seguimientoNuevo.setEditable(false);
                int plazoLimite = flujoTiempoService.obtieneFlujoXIdetapa("PPP", 1);
                seguimientoNuevo.setPlazo_limite(plazoLimite);
                seguimientoNuevo.setEstado(EnumEstado.ACTIVO.getCodigo());
                seguimientoNuevo.setDia_pasivo(seguimientoService.cuentaDiasPasivo(seguimientoNuevo.getFechaRecepcion(),
                        EnumPrefijoTablas.SIGNO.getCodigo(),
                        item.getIdsignomarca(),
                        listUsuario.get(0).getIdregional()));

                Seguimiento nuevo = seguimientoService.guardar_modificar_Seguimiento_etapa(seguimientoNuevo, "SIG");

                /**
                 * *crear seguimiento de tiempo de espera de oposicioon el
                 * seguimiento de publicacion esta cerrado***
                 */
                //Seguimiento seguimientoDeTopo = seguimientoService.ultimo_Seguimiento_etapa(EnumPrefijoTablas.SIGNO.getCodigo(), item.getIdsignomarca(), "TOPO");
                Seguimiento seguimientoDeTopo = seguimientoService.listar_SigSeguimiento_etapa_iniciado(item.getIdsignomarca(), "TOPO");

                if (nuevo.getFechaFin() != null && seguimientoDeTopo == null) {

                    Seguimiento seguimientoTopo = new Seguimiento();
                    seguimientoTopo.setId(item.getIdsignomarca());
                    seguimientoTopo.setIdUsuario((idusuario));
                    seguimientoTopo.setIdLogTrans(logTransGuardado.getIdLogTrans());
                    seguimientoTopo.setSm(item.getSm());
                    seguimientoTopo.setNumeroPublicacion(item.getNumero_publicacion());
                    seguimientoTopo.setNumeroRegistro(null);
                    seguimientoTopo.setSerieRegistro(null);
                    seguimientoTopo.setEtapa("TOPO");
                    seguimientoTopo.setFechaRecepcion(fechaPublicacion);
                    seguimientoTopo.setFechaFin(null);
                    seguimientoTopo.setObservacion(null);
                    seguimientoTopo.setEditable(false);
                    int plazoLimiteOpo = flujoTiempoService.obtieneFlujoXIdetapa("TOPO", 1);
                    seguimientoTopo.setPlazo_limite(plazoLimiteOpo);
                    seguimientoTopo.setEstado(EnumEstado.ACTIVO.getCodigo());
                    seguimientoNuevo.setDia_pasivo(seguimientoService.cuentaDiasPasivo(seguimientoNuevo.getFechaRecepcion(),
                            EnumPrefijoTablas.SIGNO.getCodigo(),
                            item.getIdsignomarca(),
                            listUsuario.get(0).getIdregional()));
                    Seguimiento nuevoSeg = seguimientoService.guardar_modificar_listar_Seguimiento(seguimientoTopo, EnumPrefijoTablas.SIGNO.getCodigo(), 1);
                } else {
                    //por el otro lado actualizar el inicio de la etapa OPO

                    Seguimiento seguimienoIniciadoTopo = seguimientoService.listar_SigSeguimiento_etapa_iniciado(item.getIdsignomarca(), "TOPO");
                    if (seguimienoIniciadoTopo != null) {
                        seguimienoIniciadoTopo.setFechaRecepcion(fechaPublicacion);
                        seguimientoService.guardar_modificar_listar_Seguimiento(seguimienoIniciadoTopo, EnumPrefijoTablas.SIGNO.getCodigo(), 2);
                    }
                }

                //ACTUALIZAR EN TABLA SIGSIGNOMARCA
                SigSignoMarca signo = sigSignoMarcaService.obtenerSigSignoMarcaXidSignoMarca(item.getIdsignomarca());
                signo.setIdSignoMarca(item.getIdsignomarca());
                signo.setNumeroPublicacion(item.getNumero_publicacion());

                if (publicacion.getEstado_publicacion().equals(EnumEstadoPublicacion.PUBLICADO.getCodigo())) {
                    signo.setEstadoMarca("P");
                    signo.setUbicacion("PIPE");
                    signo.setFechaPublicacion(publicacion.getFecha_publicacion());
                }
                signo.setNumeroGaceta(publicacion.getNumero_gaceta());
                sigSignoMarcaService.crudSigSignoMarca(signo, 2);

                //CREACION DEL PRIMER HISTORIAL
//                descripcion = "";
//                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//                datosPublicacionMap.put("[Nro:", signo.getNumeroPublicacion().toString() + "]");
//                datosPublicacionMap.put("[Gaceta:", signo.getNumeroGaceta().toString() + "]");
//                datosPublicacionMap.put("[Fecha:", dateFormat.format(signo.getFechaPublicacion()) + "]");
//
//                System.out.println("*******************************************************      ");
//                for (Map.Entry e : datosPublicacionMap.entrySet()) {
//                    //System.out.println("RESULTADOOOO      " + e.getKey() + " " + e.getValue());
//                    descripcion = descripcion + e.getKey() + " " + e.getValue();
//                }
//                System.out.println("DESCRIPCION FINAL primera " + signo.getIdSignoMarca() + "       " + descripcion);
//                datosPublicacionMap = new LinkedHashMap<String, String>();
//                Historial historial = new Historial();
//                historial.setIdTramite(signo.getIdSignoMarca());
//                historial.setIdUsuario(idusuario);
//                historial.setIdLogTrans(1l);
//                historial.setOperacion("PUB");
//                historial.setEstadoMarcaDescripcion("PUBLICADA");
//
//                historial.setUbicacionDescripcion("PROPIEDAD INDUSTRIAL (PERIODO DEMANDA OPOSICIONES)");
//                historial.setSeccion("PUBLICACION");
//
//                historial.setDescripcion(descripcion);
//
//                historial.setFechaOperacion(fechaServidor);
//                historial.setEtado(EnumEstado.ACTIVO.getCodigo());
//                historial.setObservacion("creado por sushy");
//                historialService.crudHistorial(historial, "SIG", 1);
            } catch (Exception ex) {
                Logger.getLogger(SigDetallePublicacionServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    @Override
    public SigDetallePublicacion listar_SigDetallePublicacion_sm(Long sm) {

        try {
            String SQL = "select dpub.* from sigsignomarca sig\n"
                    + "join sigdetallepublicacion dpub on dpub.idsignomarca = sig.idsignomarca \n"
                    + "where sig.sm= " + sm + "  and sig.estado = 'AC' and dpub.estado= 'AC' order by 1 desc limit 1";
            SigDetallePublicacion detallePublicacion = jdbcTemplate.queryForObject(SQL, new SigDetallePublicacionMapper());

            return detallePublicacion;
        } catch (EmptyResultDataAccessException e) {
            //Como no se encontro ningun registro enviar la instancia de la nueva marca.
            return null;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void historial_SigDetallePublicacion_Signos(Long idPublicacion, Long gaceta, Date fecha, Long idusuario, Date fechaRecepcion) {

        try {
            String descripcion = "";
            Date fechaServidor = comunService.obtenerFechaHoraServidor(idusuario);
            List<SigDetallePublicacion> listaDetallePublicacion = listaSigDetallePublicacionXidpublicacion(idPublicacion);
            Map<String, String> datosPublicacionMap = new LinkedHashMap<String, String>();
            LogTrans logTransGuardado = logTransService.crudLogTrans(new LogTrans(idusuario, fechaServidor), 1);
            for (SigDetallePublicacion item : listaDetallePublicacion) {
                SigSignoMarca signomarcabase = sigSignoMarcaService.listaSigSignoMarcaXSM(item.getSm());
                //HashMap<String, String> datosPublicacionMap = new HashMap<String, String>();
                descripcion = "";
                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                if (signomarcabase != null) {
                    if (!(signomarcabase.getNumeroPublicacion().equals(item.getNumero_publicacion()))) {
                        datosPublicacionMap.put("[Nro:", signomarcabase.getNumeroPublicacion().toString() + "]");
                    }
                    if (!(signomarcabase.getNumeroGaceta().equals(gaceta))) {
                        datosPublicacionMap.put("[Gaceta:", signomarcabase.getNumeroGaceta().toString() + "]");
                    }
                    if (signomarcabase.getFechaPublicacion() != null) {
                        if (!(signomarcabase.getFechaPublicacion().equals(fecha))) {
                            datosPublicacionMap.put("[Fecha:", dateFormat.format(signomarcabase.getFechaPublicacion()) + "]");
                        }
                    } else {
                        datosPublicacionMap.put("[Fecha:", " ]");
                    }

                    for (Map.Entry e : datosPublicacionMap.entrySet()) {
                        //System.out.println("RESULTADOOOO      " + e.getKey() + " " + e.getValue());
                        descripcion = descripcion + e.getKey() + " " + e.getValue();
                    }

                    datosPublicacionMap = new LinkedHashMap<String, String>();

                    signomarcabase.setNumeroGaceta(gaceta);
                    signomarcabase.setFechaPublicacion(fecha);
                    sigSignoMarcaService.crudSigSignoMarca(signomarcabase, 2);

//                    Seguimiento seguimientoEtapaRevision = seguimientoService.ultimo_Seguimiento_etapa(EnumPrefijoTablas.SIGNO.getCodigo(), signomarcabase.getIdSignoMarca(), "RPP");
//                    if (seguimientoEtapaRevision != null) {
//                        int dias = diferenciaEnDias(fechaRecepcion, seguimientoEtapaRevision.getFechaRecepcion());
//                        seguimientoEtapaRevision.setFechaFin(fechaRecepcion);
//                        seguimientoEtapaRevision.setPlazoReal(dias);
//                        seguimientoService.guardar_modificar_listar_Seguimiento(seguimientoEtapaRevision, EnumPrefijoTablas.SIGNO.getCodigo(), 2);
//                    }
                    Seguimiento seguimientoEtapa = seguimientoService.ultimo_Seguimiento_etapa(EnumPrefijoTablas.SIGNO.getCodigo(), signomarcabase.getIdSignoMarca(), "PPP");
                    if (seguimientoEtapa != null) {
                        descripcion = descripcion + "[Fecha de envio: " + seguimientoEtapa.getFechaRecepcion() + "]";
                        System.out.println("DESCRIPCION --------------------------   " + descripcion);
                        int dias = diferenciaEnDias(fecha, fechaRecepcion);
                        seguimientoEtapa.setFechaRecepcion(fechaRecepcion);
                        seguimientoEtapa.setFechaFin(fecha);
                        seguimientoEtapa.setPlazoReal(dias);
                        seguimientoService.guardar_modificar_listar_Seguimiento(seguimientoEtapa, EnumPrefijoTablas.SIGNO.getCodigo(), 2);

                    }

                    Seguimiento seguimientoOpo = seguimientoService.ultimo_Seguimiento_etapa(EnumPrefijoTablas.SIGNO.getCodigo(), signomarcabase.getIdSignoMarca(), "TOPO");
                    if (seguimientoOpo != null) {
                        seguimientoOpo.setFechaRecepcion(fecha);
                        seguimientoService.guardar_modificar_listar_Seguimiento(seguimientoOpo, EnumPrefijoTablas.SIGNO.getCodigo(), 2);
                    }
                    
                   

                    fechaServidor = comunService.obtenerFechaHoraServidor(1L);

                    if (!descripcion.equals("")) {
                        Historial historial = new Historial();
                        historial.setId(signomarcabase.getIdSignoMarca());
                        historial.setIdUsuario(idusuario);
                        historial.setIdLogTrans(logTransGuardado.getIdLogTrans());
                        historial.setOperacion("PUB");
                        String estado = dominioService.obtenerNombreCodigoDominio("estado_marca", signomarcabase.getEstadoMarca());
                        historial.setEstadoMarcaDescripcion(estado);
                        String ubicacion = dominioService.obtenerNombreCodigoDominio("ubicacion_marca", signomarcabase.getUbicacion());
                        historial.setUbicacionDescripcion(ubicacion);
                        historial.setSeccion("PUBLICACION");
                        historial.setDescripcion(descripcion);
                        historial.setFechaOperacion(fechaServidor);
                        historial.setEstado(EnumEstado.ACTIVO.getCodigo());
                        //historial.setObservacion("creado por sushy");
                        System.out.println("DESCRIPCION -------------------------- 222  " + descripcion);
                        historialService.crudHistorial(historial, "SIG", 1);
                    }

                }
            }
        } catch (Exception ex) {
            Logger.getLogger(HistorialServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        //fin lista
    }

    @Override
    public void actualizar_SigSeguimiento_SigDetallePublicacion(Long idPublicacion, Date fechaEnvio, Date fechaPublicacion) {
        List<SigDetallePublicacion> listaDetallePublicacion = listaSigDetallePublicacionXidpublicacion(idPublicacion);
        for (SigDetallePublicacion item : listaDetallePublicacion) {
            try {
                //CREAR SEGUIMIENTO
                Seguimiento seguimientoEncontrado = seguimientoService.ultimo_Seguimiento_etapa(EnumPrefijoTablas.SIGNO.getCodigo(), item.getIdsignomarca(), "PPP");
                if (seguimientoEncontrado != null) {
                    seguimientoEncontrado.setFechaRecepcion(fechaEnvio);
                    Seguimiento seguimientoFinal = seguimientoService.guardar_modificar_listar_Seguimiento(seguimientoEncontrado, EnumPrefijoTablas.SIGNO.getCodigo(), 2);
                }
            } catch (Exception ex) {
                Logger.getLogger(SigDetallePublicacionServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    @Override
    public void primer_SigHistorial(Long idPublicacion, Date fechaPublicacion, Long idusuario) {

        List<SigDetallePublicacion> listaDetallePublicacion = listaSigDetallePublicacionXidpublicacion(idPublicacion);
        Map<String, String> datosPublicacionMap = new LinkedHashMap<String, String>();
        String descripcion = "";
        try {
            Date fechaServidor = comunService.obtenerFechaHoraServidor(1L);
            LogTrans logTransGuardado = logTransService.crudLogTrans(new LogTrans(idusuario, fechaServidor), 1);
            for (SigDetallePublicacion item : listaDetallePublicacion) {

                SigSignoMarca signo = sigSignoMarcaService.obtenerSigSignoMarcaXidSignoMarca(item.getIdsignomarca());
                // CREACION DEL PRIMER HISTORIAL
                descripcion = "";
                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                datosPublicacionMap.put("[Nro:", signo.getNumeroPublicacion().toString() + "]");
                datosPublicacionMap.put("[Gaceta:", signo.getNumeroGaceta().toString() + "]");
                datosPublicacionMap.put("[Fecha:", dateFormat.format(signo.getFechaPublicacion()) + "]");

                for (Map.Entry e : datosPublicacionMap.entrySet()) {
                    descripcion = descripcion + e.getKey() + " " + e.getValue();
                }

                datosPublicacionMap = new LinkedHashMap<String, String>();
                Historial historial = new Historial();
                historial.setId(signo.getIdSignoMarca());
                historial.setIdUsuario(idusuario);
                historial.setIdLogTrans(logTransGuardado.getIdLogTrans());
                historial.setOperacion("PUB");
                historial.setEstadoMarcaDescripcion("PUBLICADA");

                historial.setUbicacionDescripcion("PROPIEDAD INDUSTRIAL (PERIODO DEMANDA OPOSICIONES)");
                historial.setSeccion("PUBLICACION");

                historial.setDescripcion(descripcion);

                historial.setFechaOperacion(fechaServidor);
                historial.setEstado(EnumEstado.ACTIVO.getCodigo());
                //historial.setObservacion("creado por sushy");
                historialService.crudHistorial(historial, "SIG", 1);

            }
        } catch (Exception ex) {
            Logger.getLogger(SigDetallePublicacionServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void numeracionSigDetallePublicacionXInicio(Long idPublicacion, Long inicioAnterior) {
        List<SigDetallePublicacion> listaDetallePublicacion = listaSigDetallePublicacionXidpublicacion(idPublicacion);
        SigPublicacion publicacionAux = new SigPublicacion();
        publicacionAux.setIdpublicacion(idPublicacion);
        SigPublicacion publicacion = sigPublicacionService.guardar_modificar_listar_SigPublicacion(publicacionAux, 4);
        Correlativo correlativo = correlativoService.encuentraCorrelativoTabla(1L, "NPUB");
        Long numero = inicioAnterior;
        Long inicio = inicioAnterior;
        try {
            for (SigDetallePublicacion item : listaDetallePublicacion) {

                if (item.getNumero_seccion() != 4) {
                    item.setNumero_publicacion(numero);
                    guardar_modificar_listar_SigDetallePublicacion(item, 2);
                    numero++;
                }
            }
            Long fin = numero - 1;
            publicacion.setInicio(inicio);
            publicacion.setFin(fin);
            publicacion.setTotal(contador_sigDetallePublicacion(idPublicacion));
            sigPublicacionService.guardar_modificar_listar_SigPublicacion(publicacion, 2);
            correlativo.setUltimo_numero_asignado(fin.intValue());
            correlativoService.crudCorrelativo(correlativo, 2);
        } catch (Exception ex) {
            Logger.getLogger(SigDetallePublicacionServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
