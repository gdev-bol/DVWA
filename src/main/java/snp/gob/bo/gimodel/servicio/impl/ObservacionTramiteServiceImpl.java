/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.servicio.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import snp.gob.bo.gimodel.entidad.ObservacionTramite;
import snp.gob.bo.gimodel.entidad.ObservacionTramiteSig;
import snp.gob.bo.gimodel.entidad.SigObservacionTramite;
import snp.gob.bo.gimodel.mapper.ObservacionTramiteMapper;
import snp.gob.bo.gimodel.mapper.ObservacionTramiteSigMapper;
import snp.gob.bo.gimodel.mapper.SigObservacionTramiteMapper;
import snp.gob.bo.gimodel.servicio.ObservacionTramiteService;

/**
 *
 * @author Eddy Valero
 * @see SigObservacionTramite
 * @see ObservacionTramiteServiceImpl
 * @version 1.0, 20/08/2016
 */
@Service("observacionTramiteService")
public class ObservacionTramiteServiceImpl implements ObservacionTramiteService {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

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
    public void registrarObservacionTramite(Long idObservacionTramite, String prefijo, Long idSignoMarca, Long idUsuario, Long idLogTrans,
            Boolean editable, Date fechaServidor, String etapaTramite, String descripcion, int operacion) throws Exception {

        try {
            String SQL = "select crud_observacion_tramite(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            int valor = jdbcTemplate.queryForObject(SQL, int.class, idObservacionTramite, prefijo, idSignoMarca, idUsuario, idLogTrans,
                    editable, fechaServidor, etapaTramite, descripcion, operacion);

        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<ObservacionTramite> obtListadoObservacionPorTramite(String prefijo, Long idSignoMarca) {
        try {
            String SQL = "select * from lista_observaciones_tramite(?,?)";
            List<ObservacionTramite> listaObservacion = jdbcTemplate.query(SQL, new ObservacionTramiteMapper(), prefijo, idSignoMarca);
            if (!listaObservacion.isEmpty()) {
                return listaObservacion;
            }
            return Collections.EMPTY_LIST;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<ObservacionTramiteSig> obtListadoObservacionPorTramite2(String prefijo, Long idSignoMarca) {
        try {
            String SQL = "select "
                    + "  s.idobservaciontramite as idobservaciontramite, "
                    + "  s.idsignomarca as idsignomarca, "
                    + "  (u.nombre ||' '||u.primer_apellido||' '|| (case when u.segundo_apellido is null then '' else u.segundo_apellido end)) as nombre_completo, "
                    + "  s.idlogtrans as idlogtrans, "
                    + "  s.editable as editable, "
                    + "  s.fecha_observacion as fecha_observacion, "
                    + "  s.etapa_tramite as etapa_tramite, "
                    + "  s.descripcion as descripcion, "
                    + "  s.estado as estado "
                    + "  from sigobservaciontramite s "
                    + "  left join usuario u on (s.idusuario = u.idusuario) where s.idsignomarca = ? and (s.estado = 'HI' or s.estado = 'AC') order by fecha_observacion desc";
            List<ObservacionTramiteSig> listaObservacion = jdbcTemplate.query(SQL, new ObservacionTramiteSigMapper(), idSignoMarca);
            if (!listaObservacion.isEmpty()) {
                return listaObservacion;
            }
            return Collections.EMPTY_LIST;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void eliminarObservacion(Long idSignoMarca) throws Exception {
        try {
            
            String SQL = "update sigobservaciontramite set estado = 'HI' where idsignomarca = ? and estado = 'AC';";
            jdbcTemplate.update(SQL, idSignoMarca);
            
        } catch (Exception e) {
            throw e;
        }        
    }

    @Override
    public void actualizarObservacion(String prefijo, Long idReferencia, String descripcion) throws Exception {
        try {
            String SQL = "select actualiza_reg_observacion(?, ?, ?)";
            String valor = jdbcTemplate.queryForObject(SQL, String.class, prefijo, idReferencia, descripcion);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public ObservacionTramite obtenerUltimaObservacionTramite(String prefijo, Long idReferencia) throws Exception {
        try {
            String SQL = "select * from obtener_observacion_tramite(?, ?);";
            ObservacionTramite sigObservacionTramite = jdbcTemplate.queryForObject(SQL,
                    new Object[]{prefijo, idReferencia}, new ObservacionTramiteMapper());
            return sigObservacionTramite;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public ObservacionTramite guardar_modificar_listar_ObservacionTramite(ObservacionTramite observaciontramite, String prefijo, Integer operacion) {
        // System.out.println("cruud observacion en el modelo ***** OPERACION  " + operacion + "   prefijo  " + prefijo);
        String SQL = "select * from crud_observaciontramite(?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?);";
        ObservacionTramite observacionTramite2 = (ObservacionTramite) jdbcTemplate.queryForObject(SQL, new ObservacionTramiteMapper(),
                observaciontramite.getIdObservacionTramite(),
                observaciontramite.getId(),
                observaciontramite.getIdUsuario(),
                observaciontramite.getIdLogTrans(),
                observaciontramite.getEditable(),
                observaciontramite.getFechaObservacion(),
                observaciontramite.getEtapaTramite(),
                observaciontramite.getDescripcion(),
                observaciontramite.getEstado(),
                prefijo,
                operacion
        );
        return observacionTramite2;
    }

    @Override
    public ObservacionTramite listar_ObservacionTramite_ventanilla(Long idreferencia, String tipoFormulario) {
        try {
            String SQL = "select * from listar_observaciontramite_ventanilla(?, ?);";
            ObservacionTramite sigObservacionTramite = jdbcTemplate.queryForObject(SQL,
                    new Object[]{idreferencia, tipoFormulario}, new ObservacionTramiteMapper());
            return sigObservacionTramite;
        } catch (EmptyResultDataAccessException e) {
            //Como no se encontro ningun registro enviar la instancia
            return new ObservacionTramite();
        } catch (Exception e) {
            throw e;
        }
    }

}
