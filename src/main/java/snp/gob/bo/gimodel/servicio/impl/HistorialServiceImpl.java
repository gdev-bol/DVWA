/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.servicio.impl;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import snp.gob.bo.gimodel.entidad.Historial;
import snp.gob.bo.gimodel.entidad.LogTrans;
import snp.gob.bo.gimodel.enums.EnumEstado;
import snp.gob.bo.gimodel.enums.EnumOperacion;
import snp.gob.bo.gimodel.mapper.HistorialMapper;
import snp.gob.bo.gimodel.mapper.HistorialRenovacionMapper;
import snp.gob.bo.gimodel.servicio.ComunService;
import snp.gob.bo.gimodel.servicio.HistorialService;
import snp.gob.bo.gimodel.servicio.LogTransService;
import snp.gob.bo.gimodel.servicio.ModDireccionDeNotificacionService;
import snp.gob.bo.gimodel.servicio.RenDireccionDeNotificacionService;
import snp.gob.bo.gimodel.servicio.RenSolicitanteApoderadoService;
import snp.gob.bo.gimodel.servicio.SigDireccionDeNotificacionService;
import snp.gob.bo.gimodel.servicio.SigSignoMarcaService;

/**
 *
 * @author Chano Rojas
 * @see RenRenovacionDominio
 * @see RenrenovacionServiceImpl
 * @version 1.0, 05/06/2016
 */
@Service("historialService")
public class HistorialServiceImpl implements HistorialService {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    RenDireccionDeNotificacionService renDireccionDeNotificacionService;

    @Autowired
    ModDireccionDeNotificacionService modDireccionDeNotificacionService;

    @Autowired
    SigDireccionDeNotificacionService sigDireccionDeNotificacionService;

    @Autowired
    RenSolicitanteApoderadoService renSolicitanteApoderadoService;

    @Autowired
    SigSignoMarcaService sigSignoMarcaService;

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

    @Override
    public Historial crudHistorial(Historial historial, String prefijoTramite, int operacion) throws Exception {
        try {
            String SQL = "select * from crud_historial_general("
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
            Historial his = (Historial) jdbcTemplate.queryForObject(SQL, new HistorialMapper(),
                    historial.getIdHistorial(),
                    historial.getId(),
                    historial.getIdUsuario(),
                    historial.getIdLogTrans(),
                    historial.getTipo(),
                    historial.getOperacion(),
                    historial.getEstadoMarcaDescripcion(),
                    historial.getObservacion(),
                    historial.getUbicacionDescripcion(),
                    historial.getSeccion(),
                    historial.getGestionRenovación(),
                    historial.getDescripcion(),
                    historial.getDescripcionListaProductos(),
                    historial.getFechaOperacion(),
                    historial.getEstado(),
                    prefijoTramite,
                    operacion);
            return his;
        } catch (DataAccessException e) {
            throw e;
        }
    }

    @Override
    public Historial crudHistorial_Renovacion(Historial historial, String prefijoTramite, int operacion) throws Exception {
        try {
            String SQL = "select * from crud_historial("
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
            Historial his = (Historial) jdbcTemplate.queryForObject(SQL, new HistorialRenovacionMapper(),
                    historial.getIdHistorial(),
                    historial.getIdUsuario(),
                    historial.getId(),
                    historial.getIdLogTrans(),
                    historial.getTipo(),
                    historial.getOperacion(),
                    historial.getEstadoMarcaDescripcion(),
                    historial.getObservacion(),
                    historial.getUbicacionDescripcion(),
                    historial.getSeccion(),
                    historial.getGestionRenovación(),
                    historial.getDescripcion(),
                    historial.getDescripcionListaProductos(),
                    historial.getFechaOperacion(),
                    historial.getEstado(),
                    prefijoTramite,
                    operacion);
            return his;
        } catch (DataAccessException e) {
            throw e;
        }
    }

    @Override
    public List<Historial> obtenerListaHistorialXId(Long id, String prefijo) throws Exception {
        try {
            String SQL = "";
            List<Historial> listaHistorial;

            SQL = "select * from lista_historial_id(?,?);";
            listaHistorial = jdbcTemplate.query(SQL, new HistorialMapper(), prefijo, id);

            if (!listaHistorial.isEmpty()) {
                return listaHistorial;
            }

            return Collections.EMPTY_LIST;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Historial guardarHistorialGenerico(String prefijo, Long id, Long usuario, String seccion,
            String operacion, String estado, String observacion, String ubicacion, String descripcion) {        
        try {
            Date fechaServidor = comunService.obtenerFechaHoraServidor(1l);            
            LogTrans logTransGuardado = logTransService.crudLogTrans(new LogTrans(usuario, fechaServidor), 1);           
            Historial historial = new Historial();
            historial.setId(id);
            historial.setIdUsuario(usuario);
            historial.setIdLogTrans(logTransGuardado.getIdLogTrans());
            historial.setOperacion(operacion);
            historial.setEstadoMarcaDescripcion(estado);
            historial.setSeccion(seccion);            
            historial.setUbicacionDescripcion(ubicacion);
            historial.setDescripcion(descripcion);
            historial.setFechaOperacion(fechaServidor);
            historial.setEstado(EnumEstado.ACTIVO.getCodigo());
            crudHistorial(historial, prefijo, 1);
        } catch (Exception ex) {
            Logger.getLogger(SigRegistroServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
