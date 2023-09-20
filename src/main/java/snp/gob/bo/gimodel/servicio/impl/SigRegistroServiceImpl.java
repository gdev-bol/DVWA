/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.servicio.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
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
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import snp.gob.bo.gimodel.entidad.BusquedaSigRegistro;
import snp.gob.bo.gimodel.entidad.Historial;
import snp.gob.bo.gimodel.entidad.LogTrans;
import snp.gob.bo.gimodel.entidad.Seguimiento;
import snp.gob.bo.gimodel.entidad.SigRegistro;
import snp.gob.bo.gimodel.entidad.SigSignoMarca;
import snp.gob.bo.gimodel.enums.EnumEstado;
import snp.gob.bo.gimodel.enums.EnumPrefijoTablas;
import snp.gob.bo.gimodel.mapper.BusquedaSigRegistroMapper;
import snp.gob.bo.gimodel.mapper.SigRegistroMapper;
import snp.gob.bo.gimodel.servicio.ComunService;
import snp.gob.bo.gimodel.servicio.FlujoTiempoService;
import snp.gob.bo.gimodel.servicio.HistorialService;
import snp.gob.bo.gimodel.servicio.LogTransService;
import snp.gob.bo.gimodel.servicio.SeguimientoService;
import snp.gob.bo.gimodel.servicio.SigRegistroService;
import snp.gob.bo.gimodel.servicio.SigSignoMarcaService;

/**
 *
 * @author susana
 */
@Service("sigRegistroService")
public class SigRegistroServiceImpl implements SigRegistroService {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private LogTransService logTransService;

    @Override
    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Autowired
    private SigSignoMarcaService sigSignoMarcaService;
    @Autowired
    private HistorialService historialService;
    @Autowired
    private ComunService comunService;
    @Autowired
    private SeguimientoService seguimientoService;
    @Autowired
    private FlujoTiempoService flujoTiempoService;

    @Override
    public SigRegistro guardar_modificar_listar_SigRegistro(SigRegistro sigRegistro, Integer operacion) {
        String SQL = "select * from crud_sigregistro(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
        SigRegistro sigregistro = (SigRegistro) jdbcTemplate.queryForObject(SQL, new SigRegistroMapper(),
                sigRegistro.getIdRegistro(),
                sigRegistro.getIdSignoMarca(),
                sigRegistro.getIdLogTrans(),
                sigRegistro.getNumeroRegistro(),
                sigRegistro.getSerie(),
                sigRegistro.getSm(),
                sigRegistro.getSmDescripcion(),
                sigRegistro.getEstadoRegistro(),
                sigRegistro.getFechaIngreso(),
                sigRegistro.getIdTipoResolucion(),
                sigRegistro.getDocumentoResolucion(),
                sigRegistro.getSigno(),
                sigRegistro.getTipoSignoDescripcion(),
                sigRegistro.getTipoGeneroDescripcion(),
                sigRegistro.getClase(),
                sigRegistro.getNumeroResolucion(),
                sigRegistro.getGestionResolucion(),
                sigRegistro.getFechaRegistro(),
                sigRegistro.getNumeroPublicacion(),
                sigRegistro.getFechaPublicacion(),
                sigRegistro.getNumeroGaceta(),
                sigRegistro.getFechaPrimerUso(),
                sigRegistro.getTitular(),
                sigRegistro.getDireccionTitular(),
                sigRegistro.getPaisTitular(),
                sigRegistro.getPaisTitularDescripcion(),
                sigRegistro.getNombreApoderado(),
                sigRegistro.getDireccionApoderado(),
                sigRegistro.getListaProductos(),
                sigRegistro.getObservacion(),
                sigRegistro.getEstado(), operacion
        );
        return sigregistro;
    }

    @Override
    public SigRegistro listar_sigRegistro_numero(Long numero, String serie) {
        try {
            String SQL = "select * from lista_sigregistro_numero(?,?);";
            SigRegistro sigregistro = (SigRegistro) jdbcTemplate.queryForObject(SQL, new SigRegistroMapper(), numero, serie);
            return sigregistro;

        } catch (EmptyResultDataAccessException e) {
            //Como no se encontro ningun registro enviar la instancia de la nueva marca.
            return null;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<SigRegistro> lista_SigRegistro_filtro(String filtro, Date fecha, String texto) {
        String fecha_registro = "";
        String SQL = "";
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        if (fecha != null) {
            fecha_registro = formato.format(fecha.getTime());

        }

        // System.out.println("FILTRO QUE LLEGA    " + filtro + "   fecha  " + fecha + "  TEXTO  " + texto);
        switch (filtro) {
            case "RD":
                SQL = "select * from sigregistro where estado_registro ='DENR' and estado ='AC'";
                break;
            case "FR":
                //fecha
                SQL = "select * from sigregistro where  \n"
                        + "DATE_TRUNC('day', fecha_registro)  = '" + fecha_registro + "'::timestamp and estado = 'AC' order by 4 asc";
                break;
            case "TM":
                SQL = "select * from sigregistro where tipo_genero_descripcion like '%" + texto + "%' and estado = 'AC'";
                break;
            case "TI":
                SQL = "select * from sigregistro where titular like '%" + texto + "%' and estado ='AC'";
                break;
            case "SI":
                SQL = "select * from sigregistro where signo like '%" + texto + "%' and estado ='AC'";
                break;
            default:

                break;
        }

        List<SigRegistro> lista = jdbcTemplate.query(SQL, new SigRegistroMapper());
        if (!lista.isEmpty()) {
            return lista;
        }
        return new ArrayList<SigRegistro>();
    }

    @Override
    public Boolean[] verificaRegistroResolucionExistente(Long numero, String serie, Long resolucion, Integer gestion) {
        Boolean[] validador = {false, false};
        String SQL = "select * from lista_sigregistro_numero(?,?);";
        String SQLres = "select * from sigregistro where numero_resolucion = " + resolucion + " and gestion_resolucion = " + gestion + " and estado='AC'";
        validador[0] = !jdbcTemplate.query(SQL, new SigRegistroMapper(), numero, serie).isEmpty();//existe
        validador[1] = !jdbcTemplate.query(SQLres, new SigRegistroMapper()).isEmpty();//existe
        return validador;

    }

    @Override
    public void historial_SigRegistro_Signos(SigRegistro registro, Long idusuario, String estado, String ubicacion) {
        String descripcion = "";

        Map<String, String> datosRegistroMap = new LinkedHashMap<String, String>();
        SigSignoMarca signo = sigSignoMarcaService.listaSigSignoMarcaXSM(registro.getSm());
        descripcion = "";
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        if (signo != null) {
            try {
                Date fechaServidor = comunService.obtenerFechaHoraServidor(1l);
                LogTrans logTransGuardado = logTransService.crudLogTrans(new LogTrans(idusuario, fechaServidor), 1);
                datosRegistroMap.put("[Nro.Reg:", registro.getNumeroRegistro() + "-" + registro.getSerie() + "]");
                datosRegistroMap.put("[Resol.Mod:", registro.getNumeroResolucion() + "/" + registro.getGestionResolucion() + "]");
                datosRegistroMap.put("[Fecha:", dateFormat.format(registro.getFechaRegistro()) + "]");
                for (Map.Entry e : datosRegistroMap.entrySet()) {
                    descripcion = descripcion + e.getKey() + " " + e.getValue();
                }
                datosRegistroMap = new LinkedHashMap<String, String>();
                Historial historial = new Historial();
                historial.setId(signo.getIdSignoMarca());
                historial.setIdUsuario(idusuario);
                historial.setIdLogTrans(logTransGuardado.getIdLogTrans());
                historial.setOperacion("MODIFICACION");
                historial.setEstadoMarcaDescripcion(estado);
                //historial.setEstadoMarcaDescripcion("REGISTRADA");
                historial.setObservacion(registro.getNumeroRegistro() + "-" + registro.getSerie());
                historial.setUbicacionDescripcion(ubicacion);
                //historial.setUbicacionDescripcion("BIBLIOTECA");
                historial.setDescripcion(descripcion);
                historial.setFechaOperacion(fechaServidor);
                historial.setEstado(EnumEstado.ACTIVO.getCodigo());
                historialService.crudHistorial(historial, "SIG", 1);
            } catch (Exception ex) {
                Logger.getLogger(SigRegistroServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void seguimiento_SigRegistro_Signos(Long sm, Long idusuario) {
        try {

            SigSignoMarca signo = sigSignoMarcaService.listaSigSignoMarcaXSM(sm);
            Seguimiento seguimientoActual = seguimientoService.listar_SigSeguimiento_etapa_iniciado(signo.getIdSignoMarca(), "ERA");
            Date fechaServidor = comunService.obtenerFechaHoraServidor(1L);
            LogTrans logTransGuardado = logTransService.crudLogTrans(new LogTrans(idusuario, fechaServidor), 1);
            if (seguimientoActual != null) {
                // System.out.println("EXISTE EL SEGUIMIENTO   "+seguimientoActual.getFechaRecepcion());
                //CERRAR EL SEGUIMIENTO 
                seguimientoActual.setFechaFin(fechaServidor);
                seguimientoService.guardar_modificar_listar_Seguimiento(seguimientoActual, "SIG", 2);
            } else {

                Seguimiento seguimientoNuevo = new Seguimiento();
                seguimientoNuevo.setId(signo.getIdSignoMarca());
                seguimientoNuevo.setIdUsuario((idusuario));
                seguimientoNuevo.setIdLogTrans(logTransGuardado.getIdLogTrans());
                seguimientoNuevo.setSm(signo.getSm());
                seguimientoNuevo.setNumeroPublicacion(signo.getNumeroPublicacion());
                seguimientoNuevo.setNumeroRegistro(signo.getNumeroRegistro());
                seguimientoNuevo.setSerieRegistro(signo.getSerieRegistro());
                seguimientoNuevo.setEtapa("ERA");
                seguimientoNuevo.setObservacion(null);
                seguimientoNuevo.setEditable(false);
                seguimientoNuevo.setEstado(EnumEstado.ACTIVO.getCodigo());
                Seguimiento ultimo = seguimientoService.lista_seguimiento_ultimo(EnumPrefijoTablas.SIGNO.getCodigo(), signo.getIdSignoMarca());
                if (ultimo != null) {
                    seguimientoNuevo.setFechaRecepcion(ultimo.getFechaFin());
                } else {
                    seguimientoNuevo.setFechaRecepcion(fechaServidor);
                }
                seguimientoNuevo.setFechaFin(fechaServidor);
                seguimientoNuevo.setPlazo_limite(flujoTiempoService.obtieneFlujoXIdetapa("ERA", 1));
                
                Seguimiento nuevo = seguimientoService.guardar_modificar_Seguimiento_etapa(seguimientoNuevo, "SIG");
                //Seguimiento nuevo = seguimientoService.guardar_modificar_listar_Seguimiento(seguimientoNuevo, "SIG", 1);
            }
            /*nuevo.setFechaFin(fechaServidor);
             seguimientoService.guardar_modificar_Seguimiento_etapa(nuevo, "SIG");*/
        } catch (Exception ex) {
            Logger.getLogger(SigRegistroServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public SigRegistro listar_sigRegistro_idsignomarca(Long idsignomarca) {
        try {
            String SQL = "select * from sigregistro where idsignomarca = " + idsignomarca + " and estado = 'AC' ";
            SigRegistro sigregistro = (SigRegistro) jdbcTemplate.queryForObject(SQL, new SigRegistroMapper());
            return sigregistro;

        } catch (EmptyResultDataAccessException e) {
            //Como no se encontro ningun registro enviar la instancia de la nueva marca.
            return null;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<SigRegistro> listar_sigRegistro_gestion_mes(int gestion, int mes) {
        String SQL = "select * from sigregistro where EXTRACT(YEAR FROM fecha_registro) = ? and EXTRACT(MONTH FROM fecha_registro) = ? and estado = 'AC' order by numero_registro";
        List<SigRegistro> lista = jdbcTemplate.query(SQL, new SigRegistroMapper(), gestion, mes);
        if (!lista.isEmpty()) {
            return lista;
        }
        return Collections.EMPTY_LIST;
    }

    @Override
    public List<SigRegistro> listar_sigRegistro_gestion(int gestion) {
        String SQL = "select * from sigregistro where EXTRACT(YEAR FROM fecha_registro) = ? and estado = 'AC' order by numero_registro";
        List<SigRegistro> lista = jdbcTemplate.query(SQL, new SigRegistroMapper(), gestion);
        if (!lista.isEmpty()) {
            return lista;
        }
        return Collections.EMPTY_LIST;
    }

    @Override
    public List<BusquedaSigRegistro> lista_SigRegistro_avanzada(Date fechaInicio, Date fechaFin) {
        List<BusquedaSigRegistro> lista = new ArrayList<BusquedaSigRegistro>();
        String SQL = "";
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        String fechaInicioConsulta = "";
        String fechaFinConsulta = "";
        fechaInicioConsulta = formato.format(fechaInicio.getTime());
        fechaFinConsulta = formato.format(fechaFin.getTime()); //+ 86400000L);

        SQL = "select row_number () over (order by reg.fecha_registro,reg.numero_registro asc) posicion, \n"
                + " reg.idregistro, reg.fecha_registro,reg.numero_registro, reg.serie, reg.numero_resolucion, \n"
                + " reg.sm_descripcion, reg.signo, reg.clase \n"
                + " from sigregistro reg where fecha_registro between '" + fechaInicioConsulta + "' and '" + fechaFinConsulta + "'"
                + " and estado= 'AC'";
        //System.out.println("SQL     " + SQL);
        lista = jdbcTemplate.query(SQL, new BusquedaSigRegistroMapper());
        if (!lista.isEmpty()) {
            return lista;
        }
        return lista;
    }

    @Override
    public List<Date> listadoFechasRegistro(Date fechaInicio, Date fechaFin) {
        try {
            String SQL = "select distinct reg.fecha_registro "
                    + "from sigregistro reg "
                    + "where reg.fecha_registro between  ? and ? "
                    + "order by reg.fecha_registro";

            List<Date> listaDate = jdbcTemplate.query(SQL, new RowMapper() {
                public Object mapRow(ResultSet resultSet, int i) throws SQLException {
                    return resultSet.getDate(1);
                }
            }, fechaInicio, fechaFin);
            if (!listaDate.isEmpty()) {
                return listaDate;
            }
            return Collections.EMPTY_LIST;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Long lista_UltimoRegistro_gestion() {
        Date fechaSistema = new Date();
        try {
            fechaSistema = comunService.obtenerFechaServidor(1l);
        } catch (Exception ex) {
            Logger.getLogger(SigRegistroServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        Long resultado = 0l;
        try {
            String formato = "yyyy";
            SimpleDateFormat dateFormat = new SimpleDateFormat(formato);
            Integer gestionn = Integer.parseInt(dateFormat.format(fechaSistema));
            String SQL = "";
            SQL = "select max(reg.numero_registro) from sigregistro reg\n"
                    + "join sigsignomarca sig on (sig.idsignomarca=reg.idsignomarca) \n"
                    + "where reg.gestion_resolucion = " + gestionn + " and reg.estado='AC'";
            //System.out.println("SQL  "+SQL);
            resultado = jdbcTemplate.queryForObject(SQL, Long.class);
            return resultado;
        } catch (EmptyResultDataAccessException e) {
            //Como no se encontro ningun registro enviar la instancia de la nueva marca.
            return resultado;
        } catch (Exception e) {
            throw e;
        }
    }

}
