/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.servicio.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import snp.gob.bo.gimodel.entidad.Correlativo;
import snp.gob.bo.gimodel.entidad.Historial;
import snp.gob.bo.gimodel.entidad.LogTrans;
import snp.gob.bo.gimodel.entidad.ModModificacion;
import snp.gob.bo.gimodel.entidad.ModResolucion;
import snp.gob.bo.gimodel.entidad.Usuario;
import snp.gob.bo.gimodel.enums.EnumEstado;
import snp.gob.bo.gimodel.enums.EnumEstadoModificacion;
import snp.gob.bo.gimodel.enums.EnumSeccion;
import snp.gob.bo.gimodel.mapper.ModResolucionMapper;
import snp.gob.bo.gimodel.servicio.ComunService;
import snp.gob.bo.gimodel.servicio.CorrelativoService;
import snp.gob.bo.gimodel.servicio.DominioService;
import snp.gob.bo.gimodel.servicio.HistorialService;
import snp.gob.bo.gimodel.servicio.LogTransService;
import snp.gob.bo.gimodel.servicio.ModModificacionService;
import snp.gob.bo.gimodel.servicio.ModResolucionService;
import snp.gob.bo.gimodel.servicio.RegionalService;
import snp.gob.bo.gimodel.servicio.UsuarioService;

/**
 *
 * @author susana
 */
@Service("modResolucionService")
public class ModResolucionServiceImpl implements ModResolucionService {

    @Autowired
    private ComunService comunService;
    @Autowired
    private CorrelativoService correlativoService;
    @Autowired
    private ModModificacionService modmodificacionService;
    @Autowired
    private LogTransService logTransService;
    @Autowired
    private HistorialService historialService;
    @Autowired
    private DominioService dominioService;
    @Autowired
    private UsuarioService usuarioService;

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
    public ModResolucion guardar_modificar_listar_ModResolucion(ModResolucion modResolucion, Integer opcion) {
        String SQL = "select * from crud_modresolucion(?, ?, ?, ?, ?, ?, ?, ?,?);";
        ModResolucion modresolucion = (ModResolucion) jdbcTemplate.queryForObject(SQL, new ModResolucionMapper(),
                modResolucion.getIdresolucion(),
                modResolucion.getIdmodificacion(),
                modResolucion.getNumero_resolucion(),
                modResolucion.getGestion_resolucion(),
                modResolucion.getFecha_resolucion(),
                modResolucion.getObservacion_resolucion(),
                modResolucion.getDocumento_resolucion(),
                modResolucion.getEstado(), opcion
        );
        return modresolucion;
    }

    @Override
    public void borrar_modResolucion(ModResolucion modResolucion, Integer opcion) {
        String SQL = "select * from crud_modresolucion(?, ?, ?, ?, ?, ?, ?, ?,?);";
        jdbcTemplate.query(SQL, new ModResolucionMapper(),
                modResolucion.getIdresolucion(),
                modResolucion.getIdmodificacion(),
                modResolucion.getNumero_resolucion(),
                modResolucion.getGestion_resolucion(),
                modResolucion.getFecha_resolucion(),
                modResolucion.getObservacion_resolucion(),
                modResolucion.getDocumento_resolucion(),
                modResolucion.getEstado(), opcion
        );
    }

    @Override
    public ModResolucion buscar_ModResolucionXidmodificacion(Long idmodificacion) {
        ModResolucion resolucion = new ModResolucion();
        String SQL = "select * from lista_modresolucion_idmodificacion(?);";
        if (!jdbcTemplate.query(SQL, new ModResolucionMapper(), idmodificacion).isEmpty()) {
            resolucion = (ModResolucion) jdbcTemplate.queryForObject(SQL, new ModResolucionMapper(), idmodificacion);
            return resolucion;
        }
        return null;
    }

    @Override
    public ModResolucion generarResolucion(ModModificacion modificacion) {
        ModResolucion resolucionNueva = new ModResolucion();
        try {

            Integer numero = 0;
            ModResolucion resolucion = new ModResolucion();
            Correlativo correlativo = new Correlativo();
            Date fechaServidor = new Date();
            try {
                fechaServidor = comunService.obtenerFechaHoraServidor(1l);                
            } catch (Exception ex) {
                Logger.getLogger(ModResolucionServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            String formato = "yyyy";
            SimpleDateFormat dateFormat = new SimpleDateFormat(formato);
            Integer gestionn = Integer.parseInt(dateFormat.format(fechaServidor));
            resolucion.setIdmodificacion(modificacion.getIdmodificacion());
            resolucion.setGestion_resolucion(gestionn);
            resolucion.setFecha_resolucion(fechaServidor);
            resolucion.setEstado("AC");

            
            Usuario usuarioregional= usuarioService.buscaUsuarioPorIdUsuario(modificacion.getUsuario());
            
            
            switch (modificacion.getTipo_modificacion()) {
                case "CANO":
                    correlativo = correlativoService.encuentraCorrelativoTabla(usuarioregional.getIdregional(), "CANO");
                    numero = correlativo.getUltimo_numero_asignado() + 1;
                    break;
                case "CADO":
                    correlativo = correlativoService.encuentraCorrelativoTabla(usuarioregional.getIdregional(), "CADO");
                    numero = correlativo.getUltimo_numero_asignado() + 1;
                    break;
                case "CATR":
                    correlativo = correlativoService.encuentraCorrelativoTabla(usuarioregional.getIdregional(), "CATR");
                    numero = correlativo.getUltimo_numero_asignado() + 1;

                    break;
                case "CAFU":
                    correlativo = correlativoService.encuentraCorrelativoTabla(usuarioregional.getIdregional(), "CAFU");
                    numero = correlativo.getUltimo_numero_asignado() + 1;
                    break;
                case "CALU":
                    correlativo = correlativoService.encuentraCorrelativoTabla(usuarioregional.getIdregional(), "CALU");
                    numero = correlativo.getUltimo_numero_asignado() + 1;
                    break;
                default:
                    break;
            }

            if (resolucion.getIdresolucion() == null) {
                resolucion.setNumero_resolucion(numero);
                resolucionNueva = guardar_modificar_listar_ModResolucion(resolucion, 1);
                modificacion.setEstado_modificacion(EnumEstadoModificacion.ACEPTADA.getCodigo());
                modmodificacionService.guardar_modificar_listar_ModModificacion(modificacion, 2);
                correlativo.setUltimo_numero_asignado(numero);
                correlativoService.crudCorrelativo(correlativo, 2);
            }

            resolucionNueva = resolucion;

        } catch (Exception ex) {
            Logger.getLogger(ModResolucionServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return resolucionNueva;
    }

    @Override
    public Boolean verificaExisteModResolucion(String tipo, Integer numero, Integer gestion, Long idmodificacion) {
        String SQL = "select * from modresolucion mre\n"
                + "join modmodificacion mod on (mre.idmodificacion=mod.idmodificacion and tipo_modificacion = '" + tipo + "') "
                + "where "
                + "mre.numero_resolucion = " + numero + " and mre.gestion_resolucion = " + gestion + " and mre.estado='AC'";
        return jdbcTemplate.query(SQL, new ModResolucionMapper()).isEmpty();
    }

    @Override
    public ModResolucion lista_ModResolucionAnulado(String tipo, Integer numero, Integer gestion, Long idmodificacion) {
        ModResolucion resolucion = new ModResolucion();
//        String SQL = "select * from modresolucion mre\n"
//                + "join modmodificacion mod on (mre.idmodificacion=mod.idmodificacion and tipo_modificacion = '" + tipo + "' )\n"
//                + "where mre.idmodificacion = " + idmodificacion + " \n"
//                + "and mre.numero_resolucion = " + numero + " and mre.gestion_resolucion = " + gestion + " and mre.estado='NA' ";
        String SQL = "select  mre.* from modresolucion mre join modmodificacion mod \n"
                + "on (mre.idmodificacion= mod.idmodificacion and tipo_modificacion = '" + tipo + "') "
                + "where mre.numero_resolucion = " + numero + " and mre.gestion_resolucion = " + gestion + " and mre.estado='"+EnumEstado.NO_ACTIVO.getCodigo()+"'";
        
        if (!jdbcTemplate.query(SQL, new ModResolucionMapper()).isEmpty()) {
            resolucion = (ModResolucion) jdbcTemplate.queryForObject(SQL, new ModResolucionMapper());
            return resolucion;
        }
        return null;
    }

    @Override
    public ModResolucion asignacionManualModResolucion(Long idmodificacion, ModResolucion actual, ModResolucion anulado, Date fecha) {
        if (actual != null) {

            actual.setEstado(EnumEstado.NO_ACTIVO.getCodigo());
            borrar_modResolucion(actual, 2);
        }
        anulado.setEstado("RE");
        borrar_modResolucion(anulado, 2);
        ModResolucion modresolucion = new ModResolucion();
        modresolucion.setIdmodificacion(idmodificacion);
        modresolucion.setNumero_resolucion(anulado.getNumero_resolucion());
        modresolucion.setGestion_resolucion(anulado.getGestion_resolucion());
        modresolucion.setFecha_resolucion(fecha);
        modresolucion.setEstado("PE");
        ModResolucion modresolucionCreado = guardar_modificar_listar_ModResolucion(modresolucion, 1);
        return modresolucionCreado;

    }

    @Override
    public ModResolucion buscar_ModResolucionXidmodificacionPendiente(Long idmodificacion) {
        ModResolucion resolucion = new ModResolucion();
        String SQL = "select * from modresolucion where idmodificacion = " + idmodificacion + " and estado='PE';";
        if (!jdbcTemplate.query(SQL, new ModResolucionMapper()).isEmpty()) {
            resolucion = (ModResolucion) jdbcTemplate.queryForObject(SQL, new ModResolucionMapper());
            return resolucion;
        }
        return null;
    }

    @Override
    public void adicionarModResolucion(Long idmodificacion, Integer numero, Integer gestion, Date fecha) {
        if (numero != null && gestion != null && fecha != null) {
            ModResolucion resolucion = new ModResolucion();
            resolucion.setIdmodificacion(idmodificacion);
            resolucion.setNumero_resolucion(numero);
            resolucion.setGestion_resolucion(gestion);
            resolucion.setFecha_resolucion(fecha);
            resolucion.setEstado(EnumEstado.ACTIVO.getCodigo());
            ModResolucion resolucionNueva = guardar_modificar_listar_ModResolucion(resolucion, 1);
        }
    }

    @Override
    public void historial_ModResolucion_anulado(ModResolucion modresolucion, Long idusuario, ModModificacion modificacion) {
        
        try {
            String ubicacion_modificacion = "";
            String estado_modificacion = dominioService.obtenerNombreCodigoDominio("estado_modificacion", modificacion.getEstado_modificacion());
            if (modificacion.getUbicacion_modificacion() != null && !modificacion.getUbicacion_modificacion().equals("")) {
                ubicacion_modificacion = dominioService.obtenerNombreCodigoDominio("ubicacion_modificacion", modificacion.getUbicacion_modificacion());
            }
            //   LogTrans logTransGuardado = logTransService.crudLogTrans(new LogTrans(idusuario, fechaRegistro), 1);
            Map<String, String> datosMap = new LinkedHashMap<String, String>();
            String descripcion = "";
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            System.out.println("modresolucion.getNumero_resolucion()  " + modresolucion.getNumero_resolucion());
            datosMap.put("[Resol.Mod:", modresolucion.getNumero_resolucion().toString() + "]");
            datosMap.put("[Gestion:", modresolucion.getGestion_resolucion().toString() + "]");
            datosMap.put("[Fecha:", dateFormat.format(modresolucion.getFecha_resolucion()) + "]");

            for (Map.Entry e : datosMap.entrySet()) {
                descripcion = descripcion + e.getKey() + " " + e.getValue();
            }

            datosMap = new LinkedHashMap<String, String>();
            Date fechaSistema = comunService.obtenerFechaHoraServidor(1L);
            LogTrans logTransGuardado = logTransService.crudLogTrans(new LogTrans(idusuario, fechaSistema), 1);

            Historial historialMod = new Historial();
            historialMod.setId(modificacion.getIdmodificacion());
            historialMod.setIdUsuario(idusuario);
            historialMod.setIdLogTrans(logTransGuardado.getIdLogTrans());
            historialMod.setOperacion("ANULACIÓN DE REGISTRO");
            historialMod.setEstadoMarcaDescripcion(estado_modificacion);
            historialMod.setObservacion(modresolucion.getNumero_resolucion().toString() + "/" + modresolucion.getNumero_resolucion().toString());
            historialMod.setUbicacionDescripcion(ubicacion_modificacion);
            historialMod.setDescripcion(descripcion);
            historialMod.setSeccion(EnumSeccion.DATOS_ADMINISTRATIVOS.getCodigo());
            historialMod.setFechaOperacion(fechaSistema);
            historialMod.setEstado(EnumEstado.ACTIVO.getCodigo());
            historialService.crudHistorial(historialMod, "MOD", 1);
        } catch (Exception ex) {
            Logger.getLogger(ModResolucionServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
