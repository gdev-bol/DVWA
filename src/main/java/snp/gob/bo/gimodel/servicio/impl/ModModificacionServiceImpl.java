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
//import jdk.nashorn.internal.objects.NativeString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import snp.gob.bo.gimodel.entidad.BusquedaModificacionResultado;
import snp.gob.bo.gimodel.entidad.Dominio;
import snp.gob.bo.gimodel.entidad.Historial;
import snp.gob.bo.gimodel.entidad.LogTrans;
import snp.gob.bo.gimodel.entidad.ModDireccionDeNotificacion;
import snp.gob.bo.gimodel.entidad.ModModificacion;
import snp.gob.bo.gimodel.entidad.ModResolucion;
import snp.gob.bo.gimodel.entidad.ModTipoSigno;
import snp.gob.bo.gimodel.entidad.ModTitularLicenciatarioNuevo;
import snp.gob.bo.gimodel.entidad.ModTitularLicenciatarioRegistrado;
import snp.gob.bo.gimodel.entidad.SigSignoMarca;
import snp.gob.bo.gimodel.entidad.SigSolicitanteApoderado;
import snp.gob.bo.gimodel.enums.EnumEstado;
import snp.gob.bo.gimodel.enums.EnumSeccion;
import snp.gob.bo.gimodel.enums.EnumTipoModificacion;
import snp.gob.bo.gimodel.mapper.ModModificacionMapper;
import snp.gob.bo.gimodel.servicio.ModModificacionService;
import snp.gob.bo.gimodel.servicio.SigSolicitanteApoderadoService;
import snp.gob.bo.gimodel.enums.EnumTipoPersona;
import snp.gob.bo.gimodel.mapper.BusquedaModificacionResultadoMapper;
import snp.gob.bo.gimodel.mapper.ModResolucionMapper;
import snp.gob.bo.gimodel.servicio.ComunService;
import snp.gob.bo.gimodel.servicio.DominioService;
import snp.gob.bo.gimodel.servicio.HistorialService;
import snp.gob.bo.gimodel.servicio.LogTransService;
import snp.gob.bo.gimodel.servicio.ModDireccionDeNotificacionService;
import snp.gob.bo.gimodel.servicio.ModTitularLicenciatarioNuevoService;
import snp.gob.bo.gimodel.servicio.ModTitularLicenciatarioRegistradoService;
import snp.gob.bo.gimodel.servicio.ModHistorialService;
import snp.gob.bo.gimodel.servicio.ModResolucionService;
import snp.gob.bo.gimodel.servicio.ModSolicitanteApoderadoService;
import snp.gob.bo.gimodel.servicio.ModTipoSignoService;
import snp.gob.bo.gimodel.solicitudes.entidades.FormularioPI103;
import snp.gob.bo.gimodel.solicitudes.servicio.FormularioService;

/**
 *
 * @author susana
 */
@Service("modModificacionService")
public class ModModificacionServiceImpl implements ModModificacionService {

    @Autowired
    private DominioService dominioService;
    @Autowired
    private SigSolicitanteApoderadoService sigSolicitanteApoderadoService;
    @Autowired
    private ModTitularLicenciatarioNuevoService modTitularLicenciatarioNuevoService;
    @Autowired
    private ModResolucionService modResolucionService;
    @Autowired
    private HistorialService historialService;
    @Autowired
    private LogTransService logTransService;
    @Autowired
    private ComunService comunService;
    @Autowired
    private ModHistorialService modHistorialService;
    @Autowired
    private ModTipoSignoService modTipoSignoService;
    @Autowired
    private ModSolicitanteApoderadoService modSolicitanteApoderadoService;
    @Autowired
    private ModDireccionDeNotificacionService modDireccionDeNotificacionService;
    @Autowired
    private ModTitularLicenciatarioRegistradoService modTitularLicenciatarioRegistradoService;
    @Autowired
    private FormularioService formularioService;

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    @Override
    public void setDataSource(DataSource dataSource) {
        try {
            this.dataSource = dataSource;
            this.jdbcTemplate = new JdbcTemplate(dataSource);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    //@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public ModModificacion guardar_modificar_listar_ModModificacion(ModModificacion modModificacion, Integer operacion) {

        ModModificacion modModificacionNuevo = new ModModificacion();
        String SQL = "select * from crud_modmodificacion(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
        modModificacionNuevo = (ModModificacion) jdbcTemplate.queryForObject(SQL, new ModModificacionMapper(),
                modModificacion.getIdmodificacion(),
                modModificacion.getIdlogtrans(),
                modModificacion.getSigla(),
                modModificacion.getNumero(),
                modModificacion.getGestion(),
                modModificacion.getSm(),
                modModificacion.getFecha_ingreso(),
                modModificacion.getNro_formulario(),
                modModificacion.getOficina(),
                modModificacion.getNumero_registro(),
                modModificacion.getSerie_registro(),
                modModificacion.getNumero_renovacion(),
                modModificacion.getSerie_renovacion(),
                modModificacion.getNumero_publicacion(),
                modModificacion.getSigno_registrado(),
                modModificacion.getIdclase_registrado(),
                modModificacion.getTipo_genero_registrado(),
                modModificacion.getMarca_acomp(),
                modModificacion.getReg_lc_registrado(),
                modModificacion.getSerie_lc_registrado(),
                modModificacion.getFecha_lc_registrado(),
                modModificacion.getNuevo_domicilio(),
                modModificacion.getNueva_nacionalidad(),
                modModificacion.getNuevo_pais_constitucion(),
                modModificacion.getNuevo_departamento(),
                modModificacion.getLuinicio(),
                modModificacion.getLu_fin(),
                modModificacion.getFecha_ultima_operacion(),
                modModificacion.getUsuario(),
                modModificacion.getTitular_signo(),
                modModificacion.getNacionalidad_signo(),
                modModificacion.getDepartamento_signo(),
                modModificacion.getDomicilio_signo(),
                modModificacion.getTelefono_signo(),
                modModificacion.getFax_signo(),
                modModificacion.getEmail_signo(),
                modModificacion.getTipo_modificacion(),
                modModificacion.getEstado_modificacion(),
                modModificacion.getUbicacion_modificacion(),
                modModificacion.getLista_producto(),
                modModificacion.getEstado(),
                modModificacion.getNro_recibo(),
                modModificacion.getSerie_recibo(), operacion
        );
        return modModificacionNuevo;
    }

    @Override
    //@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public ModModificacion modificarModModificacionSubsanacion(ModModificacion modModificacion, FormularioPI103 formularioPI103, Long idLogTrans) {

        int operacionModificar = 2;
        ModModificacion modModificacionNuevo = new ModModificacion();
        String SQL = "select * from crud_modmodificacion(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
        modModificacionNuevo = (ModModificacion) jdbcTemplate.queryForObject(SQL, new ModModificacionMapper(),
                modModificacion.getIdmodificacion(),
                idLogTrans,
                modModificacion.getSigla(),
                modModificacion.getNumero(),
                modModificacion.getGestion(),
                modModificacion.getSm(),
                modModificacion.getFecha_ingreso(),
                formularioPI103.getFormularios().getNumeroFormulario(),//modModificacion.getNro_formulario(),
                modModificacion.getOficina(),
                formularioPI103.getModificaciones().getNumeroRegistro(),//modModificacion.getNumero_registro(),
                formularioPI103.getModificaciones().getSerieRegistro(),//modModificacion.getSerie_registro(),
                formularioPI103.getModificaciones().getNumeroRenovacion(),//modModificacion.getNumero_renovacion(),
                formularioPI103.getModificaciones().getSerieRenovacion(),//modModificacion.getSerie_renovacion(),
                formularioPI103.getModificaciones().getNumeroPublicacion(),//modModificacion.getNumero_publicacion(),
                formularioPI103.getModificaciones().getSignoRegistro(),//modModificacion.getSigno_registrado(),
                formularioPI103.getModificaciones().getClaseRegistrado(),//modModificacion.getClase_registrado(),
                formularioPI103.getModificaciones().getTipoGeneroRegistrado(),//modModificacion.getTipo_genero_registrado(),
                formularioPI103.getModificaciones().getMarcaAcomp(),//modModificacion.getMarca_acomp(),
                formularioPI103.getModificaciones().getRegLcRegistrado(),//modModificacion.getReg_lc_registrado(),
                formularioPI103.getModificaciones().getSerieLcRegistrado(),//modModificacion.getSerie_lc_registrado(),
                formularioPI103.getModificaciones().getFechaLcRegistrado(),//modModificacion.getFecha_lc_registrado(),
                modModificacion.getNuevo_domicilio(),
                modModificacion.getNueva_nacionalidad(),
                formularioPI103.getModificaciones().getNuevoPaisConstitucion(),//modModificacion.getNuevo_pais_constitucion(),
                modModificacion.getNuevo_departamento(),
                formularioPI103.getModificaciones().getLuInicio(),//modModificacion.getLuinicio(),
                formularioPI103.getModificaciones().getLuFin(),//modModificacion.getLu_fin(),
                modModificacion.getFecha_ultima_operacion(),
                modModificacion.getUsuario(),
                modModificacion.getTitular_signo(),
                modModificacion.getNacionalidad_signo(),
                modModificacion.getDepartamento_signo(),
                modModificacion.getDomicilio_signo(),
                modModificacion.getTelefono_signo(),
                modModificacion.getFax_signo(),
                modModificacion.getEmail_signo(),
                formularioPI103.getModificaciones().getTipoModificacion(),//modModificacion.getTipo_modificacion(),
                modModificacion.getEstado_modificacion(),
                modModificacion.getUbicacion_modificacion(),
                formularioPI103.getModificaciones().getListaProducto(),//modModificacion.getLista_producto(),
                formularioPI103.getModificaciones().getEstado(),//modModificacion.getEstado(),
                modModificacion.getNro_recibo(),
                modModificacion.getSerie_recibo(),
                operacionModificar
        );
        return modModificacionNuevo;
    }

    @Override
    public ModModificacion buscarModModificacionXid(Long idmodificacion) {
        try {
            ModModificacion modificacion = new ModModificacion();
            String SQL = "select * from modmodificacion where idmodificacion = ? and estado = 'AC';";
            modificacion = (ModModificacion) jdbcTemplate.queryForObject(SQL, new ModModificacionMapper(), idmodificacion);
            return modificacion;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public ModModificacion buscarModModificacionXCodigo(String sigla, Long numero, Integer gestion) {
        try {
            ModModificacion modificacion;
            String SQL = "select * from lista_modmodificacion_codigo(?,?,?);";
            if (!jdbcTemplate.query(SQL, new ModModificacionMapper(), sigla, numero, gestion).isEmpty()) {
                modificacion = (ModModificacion) jdbcTemplate.queryForObject(SQL, new ModModificacionMapper(), sigla, numero, gestion);
                return modificacion;
            }
            return null;
        } catch (Exception e) {
            throw e;
        }
    }

    // @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    @Override
    public void aceptarTransferenciaFusion(ModModificacion modificacion, SigSignoMarca marcaPrincipal, Long idlogtrans) {
        List<SigSolicitanteApoderado> titularActual = sigSolicitanteApoderadoService.listadoSolicitanteXidsignomarca(marcaPrincipal.getIdSignoMarca());
        for (SigSolicitanteApoderado itemActual : titularActual) {
            //   System.out.println("no activo  " + itemActual.getIdSolicitanteApoderado() + "  " + itemActual.getNombreRazonSocial());
            itemActual.setEstado(EnumEstado.NO_ACTIVO.getCodigo());
            sigSolicitanteApoderadoService.guardar_modificar_listar_SigSolicitanteApoderado(itemActual, 2);
        }
        List<ModTitularLicenciatarioNuevo> titularNuevo = modTitularLicenciatarioNuevoService.listaNuevoTitularXidmodificacion(modificacion.getIdmodificacion());
        for (ModTitularLicenciatarioNuevo itemNuevo : titularNuevo) {
            SigSolicitanteApoderado titular = new SigSolicitanteApoderado();
            titular.setIdSolicitanteApoderado(null);
            titular.setIdSignoMarca(marcaPrincipal.getIdSignoMarca());
            titular.setIdLogTrans(idlogtrans);
            titular.setTipoTitular(itemNuevo.getTipo_titular());
            titular.setTipoPersona(EnumTipoPersona.SOLICITANTE.getCodigo());
            titular.setNombreRazonSocial(itemNuevo.getNombre_razonsocial());
            titular.setPrimerApellido(itemNuevo.getPrimer_apellido());
            titular.setSegundoApellido(itemNuevo.getSegundo_apellido());
            titular.setNumeroDocumento(itemNuevo.getNumero_documento());
            titular.setTipoDocumento(itemNuevo.getTipo_documento());
            titular.setLugarExpedicion(itemNuevo.getLugar_expedicion());
            titular.setPais(itemNuevo.getPais());
            titular.setGenero(itemNuevo.getGenero());
            titular.setSolicitudDepartamento(itemNuevo.getSolicitud_departamento());
            titular.setPoder(null);
            titular.setDireccion(itemNuevo.getDireccion());
            titular.setTelefono(itemNuevo.getTelefono());
            titular.setCelular(itemNuevo.getCelular());
            titular.setEmail(itemNuevo.getEmail());
            titular.setFax(itemNuevo.getFax());
            titular.setEstado(EnumEstado.ACTIVO.getCodigo());
            // System.out.println("PARA CREAR   " + titular.getNombreRazonSocial());
            sigSolicitanteApoderadoService.guardar_modificar_listar_SigSolicitanteApoderado(titular, 1);
        }
    }

    @Override
    public void respaldo_sigsignomarca(ModModificacion modificacion, SigSignoMarca marcaPrincipal) {
        if (modificacion.getIdmodificacion() != null) {
            List<SigSolicitanteApoderado> listaSolicitanteApoderadoSig = sigSolicitanteApoderadoService.listadoSolicitanteXidsignomarca(marcaPrincipal.getIdSignoMarca());
            String textoSolicitante = "";
            String textoNacionalidad = "";
            String textoDepartamento = "";
            String textoDomicilio = "";
            String textoTelefono = "";
            String textoFax = "";
            String textoEmail = "";
            //System.out.println("listaSolicitanteApoderadoSig "+listaSolicitanteApoderadoSig.size());
//            for (SigSolicitanteApoderado item : listaSolicitanteApoderadoSig) {
//                String campoNombreRazonSocial = " ";
//
//                if (item.getNombreRazonSocial() != null && !item.getNombreRazonSocial().equals("")) {
//                    campoNombreRazonSocial = item.getNombreRazonSocial();
//                }
//                if (item.getPrimerApellido() != null && !item.getPrimerApellido().equals("")) {
//                    campoNombreRazonSocial = campoNombreRazonSocial + " " + item.getPrimerApellido();
//                }
//                if (item.getSegundoApellido() != null && !item.getSegundoApellido().equals("")) {
//                    campoNombreRazonSocial = campoNombreRazonSocial + " " + item.getSegundoApellido();
//                }
//                textoSolicitante = textoSolicitante + campoNombreRazonSocial.trim() + ", ";
//                //////////////////////////////////////////////////////////////////////////////////////
//                String nacionalidad = "";
//                System.out.println("item.getPais()   " + item.getPais()+"   -----------  "+item.getNombreRazonSocial());
//                if (item.getPais() != null && !item.getPais().equals("")) {
//
//                    try {
//                        //nacionalidad = dominioService.obtenerListadoDominioXCodigo(EnumNombreDominio.PAIS.getCodigo(), item.getPais()).get(0).getNombre();
//                        List<Dominio> listaDominio = dominioService.obtenerListadoDominioXCodigo(EnumNombreDominio.PAIS.getCodigo(), item.getPais());
//                        if (!listaDominio.isEmpty()) {
//                            nacionalidad = listaDominio.get(0).getNombre();
//                        } else {
//                            nacionalidad = "";
//                        }
//                        System.out.println("nacionalidad  " + nacionalidad);
//                    } catch (Exception ex) {
//                        nacionalidad = "";
//                        Logger.getLogger(ModModificacionServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//
//                    textoNacionalidad = textoNacionalidad + nacionalidad + ", ";
//
//                }
//                /////////////////////////////////////////////////////////////////////////////////////
//                String departamento = "";
//                if (item.getSolicitudDepartamento() != null && !item.getSolicitudDepartamento().equals("")) {
//                    try {
//                        departamento = dominioService.obtenerListadoDominioXCodigo(EnumNombreDominio.LUGAR_EXPEDICION.getCodigo(), item.getSolicitudDepartamento()).get(0).getNombre();
//                    } catch (Exception ex) {
//                        Logger.getLogger(ModModificacionServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
//                        departamento = " ";
//                    }
//                    textoDepartamento = textoDepartamento + departamento + ", ";
//                }
//                ////////////////////////////////////////////////////////////////////////////////////////
//                if (item.getDireccion() != null && !item.getDireccion().equals("")) {
//                    textoDomicilio = textoDomicilio + item.getDireccion() + ", ";
//                }
//                if (item.getTelefono() != null && !item.getTelefono().equals("")) {
//                    textoTelefono = textoTelefono + item.getTelefono() + ", ";
//                }
//                if (item.getFax() != null && !item.getFax().equals("")) {
//                    textoFax = textoFax + item.getFax() + ", ";
//                }
//                if (item.getEmail() != null && !item.getEmail().equals("")) {
//                    textoEmail = textoEmail + item.getEmail() + ", ";
//                }
//            }
//
//            if (textoSolicitante.length() > 1) {
//                textoSolicitante = textoSolicitante.substring(0, textoSolicitante.length() - 2);
//            }
//            if (textoNacionalidad.length() > 1) {
//                textoNacionalidad = textoNacionalidad.substring(0, textoNacionalidad.length() - 2);
//            }
//            if (textoDepartamento.length() > 1) {
//                textoDepartamento = textoDepartamento.substring(0, textoDepartamento.length() - 2);
//            }
//            if (textoDomicilio.length() > 1) {
//                textoDomicilio = textoDomicilio.substring(0, textoDomicilio.length() - 2);
//            }
//            if (textoDomicilio.length() > 1) {
//                textoDomicilio = textoDomicilio.substring(0, textoDomicilio.length() - 2);
//            }
//            if (textoTelefono.length() > 1) {
//                textoTelefono = textoTelefono.substring(0, textoTelefono.length() - 2);
//            }
//            if (textoFax.length() > 1) {
//                textoFax = textoFax.substring(0, textoFax.length() - 2);
//            }
//            if (textoEmail.length() > 1) {
//                textoEmail = textoEmail.substring(0, textoEmail.length() - 2);
//            }

            String[] datos = sigSolicitanteApoderadoService.datos_SigSolicitanteApoderado_concatenado(marcaPrincipal.getIdSignoMarca(), EnumTipoPersona.SOLICITANTE.getCodigo());
            textoSolicitante = datos[0];
            textoNacionalidad = datos[2];
            textoDepartamento = datos[4];
            textoDomicilio = datos[1];
            textoTelefono = datos[5];
            //System.out.println("textoSolicitante   " + textoSolicitante);
            modificacion.setTitular_signo(textoSolicitante);
            modificacion.setNacionalidad_signo(textoNacionalidad);
            modificacion.setDepartamento_signo(textoDepartamento);
            modificacion.setDomicilio_signo(textoDomicilio);
            modificacion.setTelefono_signo(textoTelefono);
            modificacion.setFax_signo(textoFax);
            modificacion.setEmail_signo(textoEmail);
            guardar_modificar_listar_ModModificacion(modificacion, 2);

        }
    }

    @Override
    public List<ModModificacion> lista_modmodificacion_avanzada(String tipo, String estado, Date fechaInicio, Date fechaFin, String regional) {
        //AGREGAR UN ADN PARA LA REGIONAL Y EN EL COMBO TODO TAMBIEN EN REGIONAL
        String fechaInicioConsulta = "";
        String fechaFinConsulta = "";
        String fechasConsulta = "";
        String consulta = "";
        String filtroRegional = "";
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        String SQL = "";
        if (!estado.equals("ACEP")) {
            consulta = " where tipo_modificacion = '" + tipo + "' and estado_modificacion ='" + estado + "'";
            if (fechaInicio != null) {
                fechaInicioConsulta = formato.format(fechaInicio.getTime());

                if (fechaFin != null) {
                    fechaFinConsulta = formato.format(fechaFin.getTime() + 86400000L);
                    fechasConsulta = " and (fecha_ingreso between '" + fechaInicioConsulta + "' and '" + fechaFinConsulta + "'"
                            + ")";
                } else {
                    fechasConsulta = " and fecha_ingreso = '" + fechaInicioConsulta + "'";
                }
            } else {
                if (fechaFin != null) {
                    fechaFinConsulta = formato.format(fechaFin.getTime() + 86400000L);

                    fechasConsulta = " and fecha_ingreson <= '" + fechaFinConsulta + "'";
                }
            }
            consulta = consulta + fechasConsulta;
            if (!"".equals(regional)) {
                filtroRegional = " and oficina ='" + regional + "'";
            }

            SQL = "select * from modmodificacion " + consulta + filtroRegional;

        } else {

            String inicio = "";
            String fin = "";

            try {
                //  Date fecha_ingreso_vent = comunService.obtenerFechaServidor(1l);
                SimpleDateFormat miformato = new SimpleDateFormat("yyyy-MM-dd");
                inicio = miformato.format(fechaInicio.getTime());
                fin = miformato.format(fechaFin.getTime());

            } catch (Exception ex) {
                Logger.getLogger(SigDetallePublicacionServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (!"".equals(regional)) {
                filtroRegional = " and mod.oficina ='" + regional + "' ";
            } else {
                filtroRegional = "";
            }

            SQL = "select * from modmodificacion mod join modresolucion mre \n"
                    + "on (mre.idmodificacion=mod.idmodificacion \n"
                    + "and mod.tipo_modificacion = '" + tipo + "' and mod.estado_modificacion = 'ACEP'" + filtroRegional + ")\n"
                    + "where mre.estado = 'AC' \n"
                    + "and DATE_TRUNC('day', mre.fecha_resolucion) \n"
                    + "between '" + inicio + "'::timestamp" + " and '" + fin + "'::timestamp";

        }
        //System.out.println("sql   >>>  " + SQL);
        List<ModModificacion> lista = jdbcTemplate.query(SQL, new ModModificacionMapper());

        if (!lista.isEmpty()) {
            return lista;
        }
        return new ArrayList<ModModificacion>();
    }

    @Override
    public List<ModModificacion> lista_modmodificacion_simple(String filtro, String textoBusquedaSimple) {
        String SQL = "";
        switch (filtro) {
            case "SOLI":
                SQL = "select * from modmodificacion where idmodificacion in (\n"
                        + "select idmodificacion from modsolicitanteapoderado where tipo_persona = 'SOLI'\n"
                        + "and nombre_razonsocial like '%" + textoBusquedaSimple + "%') and estado ='AC'";
                //System.out.println("buscar por solicitante \n" + SQL);
                break;
            case "APOD":
                SQL = "select * from modmodificacion where idmodificacion in (\n"
                        + "select idmodificacion from modsolicitanteapoderado where tipo_persona = 'APOD'\n"
                        + "and nombre_razonsocial like '%" + textoBusquedaSimple + "%') and estado ='AC'";
                //System.out.println("buscar por apoderado \n" + SQL);
                break;
            case "SIGN":
                SQL = "select * from modmodificacion where signo_registrado like '%" + textoBusquedaSimple + "%' and estado ='AC'";
                //System.out.println("buscar por signo \n " + SQL);
                break;
            default:
                SQL = "select * from modmodificacion where estado_modificacion = 'INGR' and estado ='AC'";
                break;
        }

        List<ModModificacion> lista = jdbcTemplate.query(SQL, new ModModificacionMapper());
        if (!lista.isEmpty()) {
            return lista;
        }
        return new ArrayList<ModModificacion>();
    }

    @Override
    public List<BusquedaModificacionResultado> lista_modmodificacion_avanzada2(String tipo, String estado, Date fechaInicio, Date fechaFin, String regional) {
        String fechaInicioConsulta = "";
        String fechaFinConsulta = "";
        String fechasConsulta = "";
        String consulta = "";
        String filtroRegional = "";
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        String SQL = "";
        if (!estado.equals("ACEP")) {
            consulta = " where mod.tipo_modificacion = '" + tipo + "' and mod.estado_modificacion ='" + estado + "'";
            if (fechaInicio != null) {
                fechaInicioConsulta = formato.format(fechaInicio.getTime());

                if (fechaFin != null) {
                    fechaFinConsulta = formato.format(fechaFin.getTime() + 86400000L);
                    fechasConsulta = " and (mod.fecha_ingreso between '" + fechaInicioConsulta + "' and '" + fechaFinConsulta + "'"
                            + ")";
                } else {
                    fechasConsulta = " and mod.fecha_ingreso = '" + fechaInicioConsulta + "'";
                }
            } else {
                if (fechaFin != null) {
                    fechaFinConsulta = formato.format(fechaFin.getTime() + 86400000L);

                    fechasConsulta = " and mod.fecha_ingreso <= '" + fechaFinConsulta + "'";
                }
            }

            consulta = consulta + fechasConsulta;
            if (!"".equals(regional)) {
                filtroRegional = " and mod.oficina ='" + regional + "'";
            }
            SQL = "select mod.idmodificacion,\n"
                    + "       mod.sigla,\n"
                    + "       mod.numero,\n"
                    + "       mod.gestion,\n"
                    + "       mod.sm,\n"
                    + "       mod.fecha_ingreso,\n"
                    + "       mod.oficina as oficina,\n"
                    + "       mod.numero_registro,\n"
                    + "       mod.serie_registro,\n"
                    + "       mod.numero_renovacion,\n"
                    + "       mod.serie_renovacion,\n"
                    + "       mod.numero_publicacion,\n"
                    + "       mod.signo_registrado,\n"
                    + "       (CASE WHEN idclase_registrado IS NULL THEN 0 ELSE (SELECT numero_clase_niza FROM claseniza WHERE idclaseniza=mod.idclase_registrado) END) AS idclase_registrado,\n"
                    + "       mod.tipo_genero_registrado,\n"
                    + "       soap.nombres as solicitante,\n"
                    + "       apo.nombres as apoderado,\n"
                    + "       nti.nombres as nuevo_titular,\n"
                    + "       null as pais_nuevo_titular,\n"
                    + "       null as descripcion_departamento_titular,\n"
                    + "       nti.direccion_nuevot as direccion_titular,\n"
                    + "       mre.numero_resolucion,\n"
                    + "       mre.gestion_resolucion,\n"
                    + "       mre.fecha_resolucion,\n"
                    + "       mod.tipo_modificacion as tipo_modificacion,\n"
                    + "       domi.nombre AS estado_modificacion,\n"
                    + "       mod.usuario as usuario,\n"
                    + "\n     reg.nombres as titular_registrado,"
                    + "\n     reg.direccion_registrado as direccion_registrado"
                    + "       from modmodificacion mod \n"
                    + "\n"
                    + "      join dominio domi ON (domi.dominio='estado_modificacion' AND domi.estado = 'AC' AND mod.estado_modificacion = domi.codigo)\n"
                    + "\n"
                    + "      full outer join modresolucion mre on (mod.idmodificacion=mre.idmodificacion and mre.estado = 'AC')  \n"
                    + "\n"
                    + "      join (select idmodificacion,\n"
                    + "      string_agg(concat(nombre_razonsocial, ' ',primer_apellido, ' ',segundo_apellido), '; ') nombres,\n"
                    + "      string_agg(concat(direccion), '; ') direccion_titular    \n"
                    + "      from modsolicitanteapoderado sol\n"
                    + "      where sol.idmodificacion = idmodificacion  \n"
                    + "      and sol.tipo_persona = 'SOLI' and sol.estado = 'AC' group by 1)\n"
                    + "      soap on ( mod.idmodificacion = soap.idmodificacion )  \n"
                    + "\n"
                    + "      full outer join (select idmodificacion, string_agg(concat(nombre_razonsocial, ' ',\n"
                    + "      primer_apellido, ' ',segundo_apellido), '; ') nombres,\n"
                    + "      string_agg(concat(numero_documento), '; ') documento_apoderado,\n"
                    + "      string_agg(concat(direccion), '; ') direccion_apoderado\n"
                    + "      from modsolicitanteapoderado apod  \n"
                    + "      where apod.idmodificacion = idmodificacion   and apod.tipo_persona = 'APOD'  \n"
                    + "      and apod.estado = 'AC'   group by 1)\n"
                    + "      apo on ( mod.idmodificacion = apo.idmodificacion ) "
                    + "      full outer join (select idmodificacion, string_agg(concat(nombre_razonsocial, ' ',\n"
                    + "      primer_apellido, ' ',segundo_apellido), '; ') nombres,\n"
                    + "      string_agg(concat(direccion), '; ') direccion_nuevot\n"
                    + "      from modtitularlicenciatarionuevo ntit  \n"
                    + "      where ntit.idmodificacion = idmodificacion  \n"
                    + "      and ntit.estado = 'AC'   group by 1)\n"
                    + "      nti on ( mod.idmodificacion = nti.idmodificacion )\n "
                    + "       full outer join (select idmodificacion, string_agg(concat(nombre_razonsocial, ' ',\n"
                    + "      primer_apellido, ' ',segundo_apellido), '; ') nombres,\n"
                    + "      string_agg(concat(direccion), '; ') direccion_registrado\n"
                    + "      from modtitularlicenciatarioregistrado treg\n"
                    + "      where treg.idmodificacion = idmodificacion and treg.tipo_persona_registrado = 'TREG'  \n"
                    + "      and treg.estado = 'AC'   group by 1)\n"
                    + "      reg on ( mod.idmodificacion = reg.idmodificacion )"
                    + consulta + filtroRegional + " and mod.estado ='AC' order by mod.fecha_ingreso asc;";
        } else {
            String inicio = "";
            String fin = "";
            try {
                //  Date fecha_ingreso_vent = comunService.obtenerFechaServidor(1l);
                SimpleDateFormat miformato = new SimpleDateFormat("yyyy-MM-dd");
                inicio = miformato.format(fechaInicio.getTime());
                fin = miformato.format(fechaFin.getTime());

            } catch (Exception ex) {
                Logger.getLogger(SigDetallePublicacionServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }

            consulta = " where mod.tipo_modificacion = '" + tipo + "' and mod.estado_modificacion ='" + estado + "'";
            if (!"".equals(regional)) {
                filtroRegional = " and mod.oficina ='" + regional + "' ";
            } else {
                filtroRegional = "";
            }

            SQL = "select mod.idmodificacion,\n"
                    + "       mod.sigla,\n"
                    + "       mod.numero,\n"
                    + "       mod.gestion,\n"
                    + "       mod.sm,\n"
                    + "       mod.fecha_ingreso,\n"
                    + "       mod.oficina as oficina,\n"
                    + "       mod.numero_registro,\n"
                    + "       mod.serie_registro,\n"
                    + "       mod.numero_renovacion,\n"
                    + "       mod.serie_renovacion,\n"
                    + "       mod.numero_publicacion,\n"
                    + "       mod.signo_registrado,\n"
                    + "       (CASE WHEN idclase_registrado IS NULL THEN 0 ELSE (SELECT numero_clase_niza FROM claseniza WHERE idclaseniza=mod.idclase_registrado) END) AS idclase_registrado,\n"
                    + "       mod.tipo_genero_registrado,\n"
                    + "       soap.nombres as solicitante,\n"
                    + "       apo.nombres as apoderado,\n"
                    + "       nti.nombres as nuevo_titular,\n"
                    + "       null as pais_nuevo_titular,\n"
                    + "       null as descripcion_departamento_titular,\n"
                    + "       nti.direccion_nuevot as direccion_titular,\n"
                    + "       mre.numero_resolucion,\n"
                    + "       mre.gestion_resolucion,\n"
                    + "       mre.fecha_resolucion,\n"
                    + "       mod.tipo_modificacion as tipo_modificacion,\n"
                    + "       domi.nombre AS estado_modificacion,\n"
                    + "       mod.usuario as usuario,\n"
                    + "\n     reg.nombres as titular_registrado,"
                    + "\n     reg.direccion_registrado as direccion_registrado"
                    + "      from modmodificacion mod \n"
                    + "\n"
                    + "      join dominio domi ON (domi.dominio='estado_modificacion' AND domi.estado = 'AC' AND mod.estado_modificacion = domi.codigo)\n"
                    + "\n"
                    + "      join modresolucion mre on (mod.idmodificacion=mre.idmodificacion and mre.estado = 'AC' "
                    + "     and DATE_TRUNC('day', mre.fecha_resolucion) \n"
                    + "     between '" + inicio + "'::timestamp" + " and '" + fin + "'::timestamp"
                    + ")\n"
                    + "\n"
                    + "      join (select idmodificacion,\n"
                    + "      string_agg(concat(nombre_razonsocial, ' ',primer_apellido, ' ',segundo_apellido), '; ') nombres,\n"
                    + "      string_agg(concat(direccion), '; ') direccion_titular    \n"
                    + "      from modsolicitanteapoderado sol\n"
                    + "      where sol.idmodificacion = idmodificacion  \n"
                    + "      and sol.tipo_persona = 'SOLI' and sol.estado = 'AC' group by 1)\n"
                    + "      soap on ( mod.idmodificacion = soap.idmodificacion )  \n"
                    + "\n"
                    + "      full outer join (select idmodificacion, string_agg(concat(nombre_razonsocial, ' ',\n"
                    + "      primer_apellido, ' ',segundo_apellido), '; ') nombres,\n"
                    + "      string_agg(concat(numero_documento), '; ') documento_apoderado,\n"
                    + "      string_agg(concat(direccion), '; ') direccion_apoderado\n"
                    + "      from modsolicitanteapoderado apod  \n"
                    + "      where apod.idmodificacion = idmodificacion   and apod.tipo_persona = 'APOD'  \n"
                    + "      and apod.estado = 'AC'   group by 1)\n"
                    + "      apo on ( mod.idmodificacion = apo.idmodificacion ) \n"
                    + "      full outer join (select idmodificacion, string_agg(concat(nombre_razonsocial, ' ',\n"
                    + "      primer_apellido, ' ',segundo_apellido), '; ') nombres,\n"
                    + "      string_agg(concat(direccion), '; ') direccion_nuevot\n"
                    + "      from modtitularlicenciatarionuevo ntit  \n"
                    + "      where ntit.idmodificacion = idmodificacion  \n"
                    + "      and ntit.estado = 'AC'   group by 1)\n"
                    + "      nti on ( mod.idmodificacion = nti.idmodificacion ) \n"
                    + "       full outer join (select idmodificacion, string_agg(concat(nombre_razonsocial, ' ',\n"
                    + "      primer_apellido, ' ',segundo_apellido), '; ') nombres,\n"
                    + "      string_agg(concat(direccion), '; ') direccion_registrado\n"
                    + "      from modtitularlicenciatarioregistrado treg\n"
                    + "      where treg.idmodificacion = idmodificacion and treg.tipo_persona_registrado = 'TREG'  \n"
                    + "      and treg.estado = 'AC'   group by 1)\n"
                    + "      reg on ( mod.idmodificacion = reg.idmodificacion )     "
                    + consulta + filtroRegional + " and mod.estado ='AC' order by numero_resolucion asc, gestion_resolucion asc;";
        }

        //System.out.println("SQL >>>>>>>> " + SQL);
        List<BusquedaModificacionResultado> lista = jdbcTemplate.query(SQL, new BusquedaModificacionResultadoMapper());

        if (!lista.isEmpty()) {
            return lista;
        }
        return new ArrayList<BusquedaModificacionResultado>();
    }

    @Override
    public List<BusquedaModificacionResultado> lista_modmodificacion_simple2(String filtro, String textoBusquedaSimple) {
        String SQL = "select mod.idmodificacion,\n"
                + "       mod.sigla,\n"
                + "       mod.numero,\n"
                + "       mod.gestion,\n"
                + "       mod.sm,\n"
                + "       mod.fecha_ingreso,\n"
                + "       mod.oficina as oficina,\n"
                + "       mod.numero_registro,\n"
                + "       mod.serie_registro,\n"
                + "       mod.numero_renovacion,\n"
                + "       mod.serie_renovacion,\n"
                + "       mod.numero_publicacion,\n"
                + "       mod.signo_registrado,\n"
                + "       (CASE WHEN idclase_registrado IS NULL THEN 0 ELSE (SELECT numero_clase_niza FROM claseniza WHERE idclaseniza=mod.idclase_registrado) END) AS idclase_registrado,\n"
                + "       mod.tipo_genero_registrado,\n"
                + "       soap.nombres as solicitante,\n"
                + "       apo.nombres as apoderado,\n"
                + "       nti.nombres as nuevo_titular,\n"
                + "       null as pais_nuevo_titular,\n"
                + "       null as descripcion_departamento_titular,\n"
                + "       nti.direccion_nuevot as direccion_titular,\n"
                + "       mre.numero_resolucion,\n"
                + "       mre.gestion_resolucion,\n"
                + "       mre.fecha_resolucion,\n"
                + "       mod.tipo_modificacion as tipo_modificacion,\n"
                + "       domi.nombre AS estado_modificacion,\n"
                + "       mod.usuario as usuario,\n"
                + "\n     reg.nombres as titular_registrado,"
                + "\n     reg.direccion_registrado as direccion_registrado"
                + "\n"
                + "      from modmodificacion mod \n"
                + "\n"
                + "      join dominio domi ON (domi.dominio='estado_modificacion' AND domi.estado = 'AC' AND mod.estado_modificacion = domi.codigo)\n"
                + "\n"
                + "      full outer join modresolucion mre on (mod.idmodificacion=mre.idmodificacion and mre.estado = 'AC')  \n"
                + "\n"
                + "      join (select idmodificacion,\n"
                + "      string_agg(concat(nombre_razonsocial, ' ',primer_apellido, ' ',segundo_apellido), '; ') nombres,\n"
                + "      string_agg(concat(direccion), '; ') direccion_titular    \n"
                + "      from modsolicitanteapoderado sol\n"
                + "      where sol.idmodificacion = idmodificacion  \n"
                + "      and sol.tipo_persona = 'SOLI' and sol.estado = 'AC' group by 1)\n"
                + "      soap on ( mod.idmodificacion = soap.idmodificacion )  \n"
                + "\n"
                + "      full outer join (select idmodificacion, string_agg(concat(nombre_razonsocial, ' ',\n"
                + "      primer_apellido, ' ',segundo_apellido), '; ') nombres,\n"
                + "      string_agg(concat(numero_documento), '; ') documento_apoderado,\n"
                + "      string_agg(concat(direccion), '; ') direccion_apoderado\n"
                + "      from modsolicitanteapoderado apod  \n"
                + "      where apod.idmodificacion = idmodificacion   and apod.tipo_persona = 'APOD'  \n"
                + "      and apod.estado = 'AC'   group by 1)\n"
                + "      apo on ( mod.idmodificacion = apo.idmodificacion )\n  "
                + "       full outer join (select idmodificacion, string_agg(concat(nombre_razonsocial, ' ',\n"
                + "      primer_apellido, ' ',segundo_apellido), '; ') nombres,\n"
                + "      string_agg(concat(direccion), '; ') direccion_nuevot\n"
                + "      from modtitularlicenciatarionuevo ntit  \n"
                + "      where ntit.idmodificacion = idmodificacion  \n"
                + "      and ntit.estado = 'AC'   group by 1)\n"
                + "      nti on ( mod.idmodificacion = nti.idmodificacion ) \n"
                + "       full outer join (select idmodificacion, string_agg(concat(nombre_razonsocial, ' ',\n"
                + "      primer_apellido, ' ',segundo_apellido), '; ') nombres,\n"
                + "      string_agg(concat(direccion), '; ') direccion_registrado\n"
                + "      from modtitularlicenciatarioregistrado treg\n"
                + "      where treg.idmodificacion = idmodificacion and treg.tipo_persona_registrado = 'TREG'  \n"
                + "      and treg.estado = 'AC'   group by 1)\n"
                + "      reg on ( mod.idmodificacion = reg.idmodificacion )    ";

        switch (filtro) {
            case "SOLI":
                SQL += "where soap.nombres like '%" + textoBusquedaSimple + "%' and mod.estado = 'AC' order by gestion asc;";
                break;
            case "APOD":
                SQL += "where apo.nombres like '%" + textoBusquedaSimple + "%' and mod.estado = 'AC' order by gestion asc;";
                break;
            case "SIGN":
                SQL += "where mod.signo_registrado like '%" + textoBusquedaSimple + "%' and mod.estado = 'AC' order by gestion asc;";
                break;
            default:
                SQL += "where mod.estado_modificacion = 'INGR' and mod.estado ='AC';";
                break;
        }

        List<BusquedaModificacionResultado> lista = jdbcTemplate.query(SQL, new BusquedaModificacionResultadoMapper());
        if (!lista.isEmpty()) {
            return lista;
        }
        return new ArrayList<BusquedaModificacionResultado>();
    }

    @Override
    public ModModificacion buscarModModificacionXtipo(String tipo, Long numero, Integer gestion) {
        try {
            ModModificacion modificacion;
            String SQL = "select * from lista_modmodificacion_tipo(?,?,?);";
            if (!jdbcTemplate.query(SQL, new ModModificacionMapper(), tipo, numero, gestion).isEmpty()) {
                modificacion = (ModModificacion) jdbcTemplate.queryForObject(SQL, new ModModificacionMapper(), tipo, numero, gestion);
                return modificacion;
            }
            return null;
        } catch (Exception e) {
            throw e;
        }
    }

    //@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    @Override
    public void aceptarCambioNombreDomicilio(ModModificacion modificacion, SigSignoMarca marcaPrincipal,
            List<ModTitularLicenciatarioNuevo> titularNuevo, Long idlogtrans) {
        //LISTAR TITULAR NUEVO E IR SETEANDO NUEVO VALORE Y CREAR
        //SETEAR DEPENDE SI ES CD O CN
        Long valor = 0l;
        List<SigSolicitanteApoderado> titularActual = sigSolicitanteApoderadoService.listadoSolicitanteXidsignomarca(marcaPrincipal.getIdSignoMarca());
        //List<ModTitularLicenciatarioNuevo> titularNuevo = modTitularLicenciatarioNuevoService.listaNuevoTitularXidmodificacion(modificacion.getIdmodificacion());
        for (ModTitularLicenciatarioNuevo item : titularNuevo) {
            //buscar el -1 todos           
            if (item.getId_referencia() == null) {
                item.setId_referencia(0L);
            }
            if (item.getId_referencia() == -1) {
                valor = -1l;
            }
        }

        //   if (titularActual.size() == titularNuevo.size()) {
        if (valor == 0) {
            // System.out.println("PRIMER ALGORITMO  =====  ");
            for (ModTitularLicenciatarioNuevo itemNuevo : titularNuevo) {
                for (SigSolicitanteApoderado itemActual : titularActual) {
                    if (itemNuevo.getId_referencia().equals(itemActual.getIdSolicitanteApoderado())) {
                        //System.out.println("=======   SON IGUALES  ====================     ");
                        //System.out.println("no activo  " + itemActual.getIdSolicitanteApoderado() + "  " + itemActual.getNombreRazonSocial());
                        itemActual.setEstado(EnumEstado.NO_ACTIVO.getCodigo());
                        sigSolicitanteApoderadoService.guardar_modificar_listar_SigSolicitanteApoderado(itemActual, 2);
                        itemActual.setIdSolicitanteApoderado(null);
                        itemActual.setIdSignoMarca(marcaPrincipal.getIdSignoMarca());
                        itemActual.setTipoPersona(EnumTipoPersona.SOLICITANTE.getCodigo());
                        itemActual.setIdLogTrans(idlogtrans);

                        //AQUI MEJORAR POR TIPO NAT JUR
                        if (modificacion.getTipo_modificacion().equals(EnumTipoModificacion.CAMBIO_NOMBRE.getCodigo())) {

                            itemActual.setTipoTitular(itemNuevo.getTipo_titular());
                            itemActual.setNombreRazonSocial(itemNuevo.getNombre_razonsocial());
                            itemActual.setPrimerApellido(itemNuevo.getPrimer_apellido());
                            itemActual.setSegundoApellido(itemNuevo.getSegundo_apellido());
                            if (itemNuevo.getTipo_titular() != null && !itemNuevo.getTipo_titular().equals("")) {
                                itemActual.setTipoTitular(itemNuevo.getTipo_titular());
                            }
                            if (itemNuevo.getTipo_documento() != null && !itemNuevo.getTipo_documento().equals("")) {
                                itemActual.setTipoDocumento(itemNuevo.getTipo_documento());
                            }
                            if (itemNuevo.getNumero_documento() != null && !itemNuevo.getNumero_documento().equals("")) {
                                itemActual.setNumeroDocumento(itemNuevo.getNumero_documento());
                            }
                            if (itemNuevo.getTipo_documento().equals("CI")) {
                                if (itemNuevo.getLugar_expedicion() != null && !itemNuevo.getLugar_expedicion().equals("")) {
                                    itemActual.setLugarExpedicion(itemNuevo.getLugar_expedicion());
                                }
                            }
                            if (itemNuevo.getTipo_titular().equals("NAT")) {
                                if (itemNuevo.getGenero() != null && !itemNuevo.getGenero().equals("")) {
                                    itemActual.setGenero(itemNuevo.getGenero());
                                }
                            }
                            if (itemNuevo.getTipo_titular().equals("JUR")) {
                                itemActual.setGenero(null);
                            }
                        }
                        if (modificacion.getTipo_modificacion().equals(EnumTipoModificacion.CAMBIO_DOMICILIO.getCodigo())) {
                            itemActual.setPais(itemNuevo.getPais());
                            itemActual.setSolicitudDepartamento(itemNuevo.getSolicitud_departamento());
                            itemActual.setDireccion(itemNuevo.getDireccion());
                        }
                        itemActual.setEstado(EnumEstado.ACTIVO.getCodigo());

                        sigSolicitanteApoderadoService.guardar_modificar_listar_SigSolicitanteApoderado(itemActual, 1);
                    }
                }
                modTitularLicenciatarioNuevoService.guardar_modificar_listar_ModTitularLicenciatarioNuevo(itemNuevo, 2);
            }
        }//fin if
        else {
            //System.out.println("-------------------------- SEGUNDO ALGORITMO ----------------------- ");
            switch (modificacion.getTipo_modificacion()) {
                case "CANO":
                    String domicilio = "sin domicilio";
                    String primerPais = titularActual.get(0).getPais();
                    String primerDepartamento = titularActual.get(0).getSolicitudDepartamento();
                    String telefono = "";
                    String celular = "";
                    String email = "";
                    String fax = "";
                    if (!titularActual.isEmpty()) {

                        domicilio = sigSolicitanteApoderadoService.datos_SigSolicitanteApoderado_concatenado(marcaPrincipal.getIdSignoMarca(), "SOLI")[1];

                    }
                    for (SigSolicitanteApoderado itemActual : titularActual) {

                        if (itemActual.getPais() != null && !itemActual.getPais().equals("")) {
                            primerPais = itemActual.getPais();
                        }
                        if (itemActual.getSolicitudDepartamento() != null && !itemActual.getSolicitudDepartamento().equals("")) {
                            primerDepartamento = itemActual.getSolicitudDepartamento();
                        }
                        if (itemActual.getTelefono() != null && !itemActual.getTelefono().equals("")) {
                            telefono = telefono + itemActual.getTelefono() + "; ";
                        }
                        if (itemActual.getCelular() != null && !itemActual.getCelular().equals("")) {
                            celular = celular + itemActual.getCelular() + "; ";
                        }
                        if (itemActual.getEmail() != null && !itemActual.getEmail().equals("")) {
                            email = email + itemActual.getEmail() + "; ";
                        }
                        if (itemActual.getFax() != null && !itemActual.getFax().equals("")) {
                            fax = fax + itemActual.getFax() + "; ";
                        }
                        //System.out.println("no activo  " + itemActual.getIdSolicitanteApoderado() + "  " + itemActual.getNombreRazonSocial());
                        itemActual.setEstado(EnumEstado.NO_ACTIVO.getCodigo());
                        sigSolicitanteApoderadoService.guardar_modificar_listar_SigSolicitanteApoderado(itemActual, 2);
                    }

                    if (telefono.length() > 1) {
                        telefono = telefono.substring(0, telefono.length() - 2);
                    }
                    if (celular.length() > 1) {
                        celular = celular.substring(0, celular.length() - 2);
                    }
                    if (email.length() > 1) {
                        email = email.substring(0, email.length() - 2);
                    }
                    if (fax.length() > 1) {
                        fax = fax.substring(0, fax.length() - 2);
                    }

                    for (ModTitularLicenciatarioNuevo itemNuevo : titularNuevo) {
                        SigSolicitanteApoderado titularFinal = new SigSolicitanteApoderado();
                        titularFinal.setIdSolicitanteApoderado(null);
                        titularFinal.setIdSignoMarca(marcaPrincipal.getIdSignoMarca());
                        titularFinal.setIdLogTrans(idlogtrans);
                        titularFinal.setTipoTitular(itemNuevo.getTipo_titular());
                        titularFinal.setTipoPersona(EnumTipoPersona.SOLICITANTE.getCodigo());
                        titularFinal.setNombreRazonSocial(itemNuevo.getNombre_razonsocial());
                        titularFinal.setPrimerApellido(itemNuevo.getPrimer_apellido());
                        titularFinal.setSegundoApellido(itemNuevo.getSegundo_apellido());
                        titularFinal.setNumeroDocumento(itemNuevo.getNumero_documento());
                        titularFinal.setTipoDocumento(itemNuevo.getTipo_documento());
                        titularFinal.setLugarExpedicion(itemNuevo.getLugar_expedicion());
                        titularFinal.setPais(primerPais);
                        titularFinal.setGenero(itemNuevo.getGenero());
                        titularFinal.setSolicitudDepartamento(primerDepartamento);
                        titularFinal.setDireccion(domicilio);
                        titularFinal.setTelefono(telefono);
                        titularFinal.setCelular(celular);
                        titularFinal.setEmail(email);
                        titularFinal.setFax(fax);
                        titularFinal.setEstado(EnumEstado.ACTIVO.getCodigo());
                        sigSolicitanteApoderadoService.guardar_modificar_listar_SigSolicitanteApoderado(titularFinal, 1);
                        //System.out.println("CREADOO NUEVO TITULAR  por cn " + titularFinal.getDireccion());
                        modTitularLicenciatarioNuevoService.guardar_modificar_listar_ModTitularLicenciatarioNuevo(itemNuevo, 2);
                    }
                    break;
                case "CADO":

                    String textoDomicilio = "";
                    String pais = null;
                    String departamento = null;
                    for (ModTitularLicenciatarioNuevo itemNuevo : titularNuevo) {
                        if (itemNuevo.getDireccion() != null && !itemNuevo.getDireccion().equals("")) {
                            textoDomicilio = textoDomicilio + itemNuevo.getDireccion() + "; ";
                        }
                        if (itemNuevo.getPais() != null && !itemNuevo.getPais().equals("")) {
                            pais = itemNuevo.getPais();
                            if (itemNuevo.getPais().equals("BO")) {
                                if (itemNuevo.getSolicitud_departamento() != null && !itemNuevo.getSolicitud_departamento().equals("")) {
                                    departamento = itemNuevo.getSolicitud_departamento();
                                }
                            }
                        }

                        modTitularLicenciatarioNuevoService.guardar_modificar_listar_ModTitularLicenciatarioNuevo(itemNuevo, 2);
                    }
                    if (textoDomicilio.length() > 1) {
                        textoDomicilio = textoDomicilio.substring(0, textoDomicilio.length() - 2);
                    }

                    for (SigSolicitanteApoderado itemActual : titularActual) {
                        // System.out.println("no activo  " + itemActual.getIdSolicitanteApoderado() + "  " + itemActual.getNombreRazonSocial());
                        SigSolicitanteApoderado titularFinal = new SigSolicitanteApoderado();
                        itemActual.setEstado(EnumEstado.NO_ACTIVO.getCodigo());
                        sigSolicitanteApoderadoService.guardar_modificar_listar_SigSolicitanteApoderado(itemActual, 2);
                        /**
                         * ********************************
                         */
                        titularFinal.setIdSignoMarca(marcaPrincipal.getIdSignoMarca());
                        titularFinal.setIdLogTrans(idlogtrans);
                        titularFinal.setTipoTitular(itemActual.getTipoTitular());
                        titularFinal.setTipoPersona(EnumTipoPersona.SOLICITANTE.getCodigo());
                        titularFinal.setNombreRazonSocial(itemActual.getNombreRazonSocial());
                        titularFinal.setPrimerApellido(itemActual.getPrimerApellido());
                        titularFinal.setSegundoApellido(itemActual.getSegundoApellido());
                        titularFinal.setNumeroDocumento(itemActual.getNumeroDocumento());
                        titularFinal.setTipoDocumento(itemActual.getTipoDocumento());
                        titularFinal.setLugarExpedicion(itemActual.getLugarExpedicion());
                        titularFinal.setPais(pais);
                        titularFinal.setGenero(itemActual.getGenero());
                        titularFinal.setSolicitudDepartamento(departamento);
                        titularFinal.setDireccion(textoDomicilio);
                        titularFinal.setTelefono(itemActual.getTelefono());
                        titularFinal.setCelular(itemActual.getCelular());
                        titularFinal.setEmail(itemActual.getEmail());
                        titularFinal.setFax(itemActual.getFax());
                        titularFinal.setEstado(EnumEstado.ACTIVO.getCodigo());
                        sigSolicitanteApoderadoService.guardar_modificar_listar_SigSolicitanteApoderado(titularFinal, 1);
                        //System.out.println("CREADOO NUEVO domicilio  por cD " + titularFinal.getDireccion());
                    }

                    break;

                default:
                    //System.out.println("no se ha podido ejecutar CN o CD");
                    break;
            }
        }
    }

    @Override
    public ModModificacion buscarModModificacion_NumeroFormulario(String numeroFormulario) throws Exception {
        try {
            numeroFormulario = (numeroFormulario).trim();

            String SQL = "select * from modmodificacion where nro_formulario=? and estado='AC' ";
            List<ModModificacion> listMod = jdbcTemplate.query(SQL, new ModModificacionMapper(), numeroFormulario);
            if (!listMod.isEmpty()) {
                return listMod.get(0);
            } else {

                return new ModModificacion();
            }
        } catch (DataAccessException e) {
            throw e;
        }
    }

    @Override
    public ModModificacion buscarModModificacionXnroFormulario(String nroFormulario) {
        try {
            ModModificacion modificacion;
            String SQL = "select * from modmodificacion where nro_formulario = ? and estado = 'AC' LIMIT 1;";

            if (!jdbcTemplate.query(SQL, new ModModificacionMapper(), nroFormulario).isEmpty()) {
                modificacion = (ModModificacion) jdbcTemplate.queryForObject(SQL, new ModModificacionMapper(), nroFormulario);
                return modificacion;
            }
            return null;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Boolean[] existe_ModModificacion_sm_publicacion_registro_or(Long sm, Long publicacion, Long registro, String serie, String tipo) {
        ModModificacion modificacion;
        ModResolucion modresolucion;
        Boolean[] resultado = {false, false};
        try {
            String SQL = "";
            String SQLm = "";
            //System.out.println("REGISTRO  " + registro + "  publicacion  " + publicacion + "    sm   " + sm + " SERIE  " + serie);
            if (registro == null) {
                registro = 0l;

            }
            if (publicacion == null) {
                publicacion = 0l;

            }

            if (registro == 0 || registro == null) {

                SQL = "select * from modmodificacion where ("
                        + "(numero_publicacion = " + publicacion + " and numero_publicacion<>0) OR sm=" + sm
                        + ") and tipo_modificacion = '" + tipo + "'  and estado = 'AC' order by 1 desc limit 1;";
                SQLm = "select mre.idresolucion, mre.idmodificacion,mre.numero_resolucion,\n"
                        + "mre.gestion_resolucion, mre.fecha_resolucion, mre.observacion_resolucion,\n"
                        + "mre.documento_resolucion, mre.estado from modresolucion mre\n"
                        + "join modmodificacion mod on (mre.idmodificacion=mod.idmodificacion \n"
                        + "and tipo_modificacion = '" + tipo + "') \n"
                        + "where ( \n"
                        + " (mod.numero_publicacion = " + publicacion + " and mod.numero_publicacion<>0) \n"
                        + "OR mod.sm=" + sm + ") \n"
                        + "and mre.estado='AC' order by mre.fecha_resolucion asc limit 1";

            } else {

                SQL = "select * from modmodificacion where ((numero_registro = " + registro + ")"
                        + " OR (numero_publicacion = " + publicacion + " and numero_publicacion<>0) OR sm=" + sm
                        + ") and tipo_modificacion = '" + tipo + "'  and estado = 'AC' order by 1 desc limit 1;";
                SQLm = "select mre.idresolucion, mre.idmodificacion,mre.numero_resolucion,\n"
                        + "mre.gestion_resolucion, mre.fecha_resolucion, mre.observacion_resolucion,\n"
                        + "mre.documento_resolucion, mre.estado from modresolucion mre\n"
                        + "join modmodificacion mod on (mre.idmodificacion=mod.idmodificacion \n"
                        + "and tipo_modificacion = '" + tipo + "') \n"
                        + "where ((mod.numero_registro = " + registro + ") \n"
                        + "OR (mod.numero_publicacion = " + publicacion + " and mod.numero_publicacion<>0) \n"
                        + "OR mod.sm=" + sm + ") \n"
                        + "and mre.estado='AC' order by mre.fecha_resolucion asc limit 1";

            }

            // System.out.println("SQL ----->>>  " + SQLm);
            modificacion = (ModModificacion) jdbcTemplate.queryForObject(SQL, new ModModificacionMapper());
            if (modificacion != null) {
                resultado[0] = modificacion.getIdmodificacion() != null;
                //modresolucion = modResolucionService.buscar_ModResolucionXidmodificacion(modificacion.getIdmodificacion());
                modresolucion = (ModResolucion) jdbcTemplate.queryForObject(SQLm, new ModResolucionMapper());
                if (modresolucion != null) {
                    resultado[1] = modresolucion.getNumero_resolucion() != 0L && modresolucion.getNumero_resolucion() != null;
                }
//                if (modificacion.getEstado_modificacion().equals("OBSE")) { //PREGUNTAR SI AUN EN OBSE ES VERDE IMPORTANTE
//                    resultado[1] = true;
//                }
            }

//            for (Boolean boolean1 : resultado) {
//                System.out.println("resultado-------------:" + boolean1);
//            }
            return resultado;
        } catch (EmptyResultDataAccessException e) {
            //Como no se encontro ningun registro enviar la instancia de la nueva marca.
            return resultado;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<ModModificacion> lista_ModModificacion_sm_publicacion_registro_or(Long sm, Long publicacion, Long registro, String serie) {
        List<ModModificacion> listaModificacion;
        try {
            String SQL = "";

            SQL = "select * "
                    + "from modmodificacion mod\n"
                    + " where ((numero_registro = " + registro + ")"
                    + " OR (numero_publicacion = " + publicacion + " and numero_publicacion<>0) OR sm=" + sm + ") and mod.estado = 'AC' order by gestion asc;";

            //System.out.println("SQL ----->>>  " + SQL);
            listaModificacion = jdbcTemplate.query(SQL, new ModModificacionMapper());

            if (!listaModificacion.isEmpty()) {
                return listaModificacion;
            }
            return new ArrayList<ModModificacion>();
        } catch (EmptyResultDataAccessException e) {
            //Como no se encontro ningun registro enviar la instancia de la nueva marca.
            return new ArrayList<ModModificacion>();
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void historial_ModModificacion_Signos(ModModificacion modificacion, Long idSignoMarca, String valorRadio, Integer numero, Integer gestion,
            Long idusuario, Date fechaRegistro, Long idlogtrans) {
        try {
            String estado_modificacion = dominioService.obtenerNombreCodigoDominio("estado_modificacion", modificacion.getEstado_modificacion());
            String ubicacion_modificacion = dominioService.obtenerNombreCodigoDominio("ubicacion_modificacion", modificacion.getUbicacion_modificacion());
            //   LogTrans logTransGuardado = logTransService.crudLogTrans(new LogTrans(idusuario, fechaRegistro), 1);
            Map<String, String> datosPublicacionMap = new LinkedHashMap<String, String>();
            String descripcion = "";
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            datosPublicacionMap.put("[Resol.Mod:", numero + "]");
            datosPublicacionMap.put("[Gestion:", gestion + "]");
            datosPublicacionMap.put("[Fecha:", dateFormat.format(fechaRegistro) + "]");

            for (Map.Entry e : datosPublicacionMap.entrySet()) {
                descripcion = descripcion + e.getKey() + " " + e.getValue();
            }

            datosPublicacionMap = new LinkedHashMap<String, String>();
            Historial historial = new Historial();
            historial.setId(idSignoMarca);
            historial.setIdUsuario(idusuario);
            historial.setIdLogTrans(idlogtrans);
            historial.setOperacion("MODIFICACION DE REGISTRO  " + valorRadio);
            historial.setEstadoMarcaDescripcion("REGISTRADA");
            historial.setObservacion(numero.toString() + "/" + gestion.toString());
            historial.setUbicacionDescripcion("BIBLIOTECA");

            historial.setDescripcion(descripcion);

            historial.setFechaOperacion(comunService.obtenerFechaHoraServidor(1l));
            historial.setEstado(EnumEstado.ACTIVO.getCodigo());

            historialService.crudHistorial(historial, "SIG", 1);

            Historial historialMod = new Historial();
            historialMod.setId(modificacion.getIdmodificacion());
            historialMod.setIdUsuario(idusuario);
            historialMod.setIdLogTrans(idlogtrans);
            historialMod.setOperacion("MODIFICACION DE REGISTRO  " + valorRadio);
            historialMod.setEstadoMarcaDescripcion(estado_modificacion);
            historialMod.setObservacion(numero.toString() + "/" + gestion.toString());
            historialMod.setUbicacionDescripcion(ubicacion_modificacion);
            historialMod.setDescripcion(descripcion);
            historialMod.setSeccion(EnumSeccion.DATOS_ADMINISTRATIVOS.getCodigo());
            historialMod.setFechaOperacion(comunService.obtenerFechaHoraServidor(1l));
            historialMod.setEstado(EnumEstado.ACTIVO.getCodigo());
            historialService.crudHistorial(historialMod, "MOD", 1);

        } catch (Exception ex) {
            Logger.getLogger(ModModificacionServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public ModModificacion obtenerSiguienteRegistroModModificacion(String sigla, Long numero, Integer gestion) {
        try {
            Integer g = gestion;        // variable gestion           
            //obtener el registro de acuerdo a su valor directamente
            String SQL = "select * "
                    + " from modmodificacion "
                    + " where "
                    + " numero > ? "
                    + " and gestion = ? "
                    + " and sigla = ? "
                    + " and estado = 'AC' "
                    + " order by sigla, numero, gestion"
                    + " limit 1 ";
            //preguntar principal si existe o NO el registro
            if (!jdbcTemplate.query(SQL, new ModModificacionMapper(), numero, gestion, sigla).isEmpty()) {
                return jdbcTemplate.query(SQL, new ModModificacionMapper(), numero, gestion, sigla).get(0);
            } else {
                //en caso que NO se encuentre se obtiene el id anterior en estado AC
                System.out.println("no logrado buscar siguiente");
                g++;
                //armar consulta para obtener el siguiente elemento
                String SQLMinimoSiguienteGestion = "select * "
                        + "from modmodificacion "
                        + " where "
                        + " numero = ( "
                        + "          select min(numero) "
                        + "          from modmodificacion "
                        + "          where gestion = ? "
                        + "          and sigla = ?"
                        + "             and estado = 'AC' "
                        + "          )"
                        + " and gestion = ?"
                        + " and sigla = ?"
                        + " and estado = 'AC' "
                        + " order by numero, sigla, gestion"
                        + " limit 1 ";

                //preguntar principal si existe o NO el registro
                if (!jdbcTemplate.query(SQLMinimoSiguienteGestion, new ModModificacionMapper(), g, sigla, g, sigla).isEmpty()) {
                    //System.out.println("preguntar principal si existe o no el registro     " + SQLMinimoSiguienteGestion);
                    return jdbcTemplate.query(SQLMinimoSiguienteGestion, new ModModificacionMapper(), g, sigla, g, sigla).get(0);
                } else {
                    //preguntar si existe una gestion mayor a la que se esta consultando en este momento
                    //System.out.println("preguntar si existe una gestion mayor a la que se esta consultando en este momento");
                    String SQLSiguienteGestionValida = "select * "
                            + "from modmodificacion "
                            + "where "
                            + " gestion >= ?"
                            + " and sigla = ?"
                            + " and estado = 'AC' "
                            + " order by numero, sigla, gestion"
                            + " limit 1 ";

                    //preguntar principal si existe o NO el registro
                    if (!jdbcTemplate.query(SQLSiguienteGestionValida, new ModModificacionMapper(), g, sigla).isEmpty()) {
                        //System.out.println("No existen mas registros       " + SQLSiguienteGestionValida);
                        return jdbcTemplate.query(SQLSiguienteGestionValida, new ModModificacionMapper(), g, sigla).get(0);
                    } else {
                        //System.out.println("no hay nada");
                        return null;
                    }
                }
            }
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public ModModificacion obtenerAnteriorRegistroModModificacion(String sigla, Long numero, Integer gestion) {
        try {

            Integer g = gestion;        // variable gestion           

            String SQL = "select * "
                    + " from modmodificacion "
                    + " where "
                    + " numero < ? "
                    + " and gestion = ? "
                    + " and sigla = ? "
                    + " and estado = 'AC' "
                    + " order by numero desc "
                    + " limit 1 ";
            if (!jdbcTemplate.query(SQL, new ModModificacionMapper(), numero, gestion, sigla).isEmpty()) {
                return jdbcTemplate.query(SQL, new ModModificacionMapper(), numero, gestion, sigla).get(0);
            } else {
                g--;
                String SQLMinimoSiguienteGestion = "select * "
                        + "from modmodificacion "
                        + " where "
                        + " numero = ( "
                        + "          select max(numero) "
                        + "          from modmodificacion "
                        + "          where gestion = ? "
                        + "          and sigla = ?"
                        + "             and estado = 'AC' "
                        + "          )"
                        + " and gestion = ?"
                        + " and sigla = ?"
                        + " and estado = 'AC' "
                        + " order by numero, sigla, gestion"
                        + " limit 1 ";
                if (!jdbcTemplate.query(SQLMinimoSiguienteGestion, new ModModificacionMapper(), g, sigla, g, sigla).isEmpty()) {
                    return jdbcTemplate.query(SQLMinimoSiguienteGestion, new ModModificacionMapper(), g, sigla, g, sigla).get(0);
                } else {
                    String SQLSiguienteGestionValida = "select * "
                            + "from modmodificacion "
                            + "where "
                            + " gestion < ?"
                            + " and sigla = ?"
                            + " and estado = 'AC' "
                            + " order by gestion desc, numero desc"
                            + " limit 1 ";

                    //preguntar principal si existe o NO el registro
                    if (!jdbcTemplate.query(SQLSiguienteGestionValida, new ModModificacionMapper(), g, sigla).isEmpty()) {

                        return jdbcTemplate.query(SQLSiguienteGestionValida, new ModModificacionMapper(), g, sigla).get(0);
                    } else {

                        return null;
                        //corregir esta cosa
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
    public void actualizarModificacionPorSubsanacion(ModModificacion modificacion, ModDireccionDeNotificacion direccionNotificacion,
            List<ModTipoSigno> listaModTipoSigno, Long idUsuario, FormularioPI103 formularioPI103) throws Exception {

        try {

            Date fechaSistema = comunService.obtenerFechaHoraServidor(1L);
            LogTrans logTransGuardado = logTransService.crudLogTrans(new LogTrans(idUsuario, fechaSistema), 1);
            Long idLogTrans = logTransGuardado.getIdLogTrans();

            // GUARDAR DATOS HISTORICOS
            modHistorialService.guardarModHistorialSubsanacion(modificacion,
                    listaModTipoSigno, 
                    idUsuario, 
                    formularioPI103);

            // REALIZAR ACTUALIZACION DE DATOS
            // modificar ModModificacion
            modificarModModificacionSubsanacion(modificacion,
                    formularioPI103,
                    idLogTrans);

            // modificar tipo signo
            modTipoSignoService.modificarListaTipoSignoSubsanacion(modificacion.getIdmodificacion(),
                    listaModTipoSigno,
                    formularioPI103.getModTipoSignos(),
                    idLogTrans);

            // modificar solicitante
            modSolicitanteApoderadoService.modificarListaSolicitanteSubsanacion(formularioPI103.getSolicitantes(),
                    modificacion.getIdmodificacion(),
                    idLogTrans);

            // modificar apoderado
            modSolicitanteApoderadoService.modificarListaApoderadoSubsanacion(formularioPI103.getApoderados(),
                    modificacion.getIdmodificacion(),
                    idLogTrans);

            // modificar direccion notificacion
            modDireccionDeNotificacionService.modificarModDireccionDeNotificacionSubsanacion(direccionNotificacion,
                    formularioPI103.getDirecciones().get(0),
                    modificacion.getIdmodificacion(),
                    idLogTrans);

            // modificar titular registrado
            modTitularLicenciatarioRegistradoService.modificarListaTitularLicenciatarioRegistradoSubsanacion(formularioPI103.getTitularLicenciatarios(),
                    modificacion.getIdmodificacion(),
                    idLogTrans);

            // modificar nuevo titular
            modTitularLicenciatarioNuevoService.modificarListaTitularLicenciatarioNuevoSubsanacion(formularioPI103.getTiTularLicenciatarioNuevos(),
                    modificacion.getIdmodificacion(),
                    idLogTrans);

            //Actualizar el estado del formulario
            formularioService.actualizarRegistroFormularioSubsanacion(formularioPI103.getFormularios().getId());

        } catch (Exception ex) {
            Logger.getLogger(ModHistorialServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
