/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.servicio.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
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
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import snp.gob.bo.gimodel.entidad.LogTrans;
import snp.gob.bo.gimodel.entidad.Seguimiento;
import snp.gob.bo.gimodel.entidad.SigDetallePublicacion;
import snp.gob.bo.gimodel.entidad.SigPublicacion;
import snp.gob.bo.gimodel.entidad.Usuario;
import snp.gob.bo.gimodel.enums.EnumEstado;
import snp.gob.bo.gimodel.enums.EnumEstadoPublicacion;
import snp.gob.bo.gimodel.enums.EnumPrefijoTablas;
import snp.gob.bo.gimodel.mapper.SigPublicacionMapper;
import snp.gob.bo.gimodel.servicio.ComunService;
import snp.gob.bo.gimodel.servicio.FlujoTiempoService;
import snp.gob.bo.gimodel.servicio.LogTransService;
import snp.gob.bo.gimodel.servicio.SeguimientoService;
import snp.gob.bo.gimodel.servicio.SigDetallePublicacionService;
import snp.gob.bo.gimodel.servicio.SigPublicacionService;
import snp.gob.bo.gimodel.servicio.UsuarioService;

/**
 *
 * @author susana
 */
@Service("sigPublicacionService")
public class SigPublicacionServiceImpl implements SigPublicacionService {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Override
    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Autowired
    private SigDetallePublicacionService sigDetallePublicacionService;
    @Autowired
    private ComunService comunService;
    @Autowired
    private LogTransService logTransService;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private SeguimientoService seguimientoService;
    @Autowired
    private FlujoTiempoService flujoTiempoService;    
    

    @Override
    public SigPublicacion guardar_modificar_listar_SigPublicacion(SigPublicacion sigPublicacion, Integer operacion) {
        String SQL = "select * from crud_sigpublicacion(?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
        SigPublicacion sigpublicacion = (SigPublicacion) jdbcTemplate.queryForObject(SQL, new SigPublicacionMapper(),
                sigPublicacion.getIdpublicacion(),
                sigPublicacion.getIdlogtrans(),
                sigPublicacion.getNumero_gaceta(),
                sigPublicacion.getFecha_publicacion(),
                sigPublicacion.getFecha_envio(),
                sigPublicacion.getMes(),
                sigPublicacion.getGestion(),
                sigPublicacion.getInicio(),
                sigPublicacion.getFin(),
                sigPublicacion.getTotal(),
                sigPublicacion.getObservacion(),
                sigPublicacion.getEstado_publicacion(),
                sigPublicacion.getEstado(), operacion
        );
        return sigpublicacion;
    }

    @Override
    public List<SigPublicacion> listaSigPublicacion() {
        String SQL = "select * from lista_sigpublicacion();";
        List<SigPublicacion> listaPublicacion = jdbcTemplate.query(SQL, new SigPublicacionMapper());
        if (!listaPublicacion.isEmpty()) {
            return listaPublicacion;
        }
        return new ArrayList<SigPublicacion>();
    }

    @Override
    public Boolean verificaExistenciaPublicacion_numero(Long numero) {
        String SQL = "select * from sigpublicacion where numero_gaceta=" + numero + " and estado= 'AC'";
        //System.out.println("sql " + SQL);
        return jdbcTemplate.query(SQL, new SigPublicacionMapper()).isEmpty();
    }

    @Override
    public SigPublicacion generarSigPublicacion(SigPublicacion publicacion, Date fecha_final, Long numero_gaceta, String mes, Integer gestion,
            String listaGacetaNoCumple, Long idUsuario) {

        SigPublicacion publicacionNueva = new SigPublicacion();
        try {
            // crear publucacion 
            List<Usuario> listUsuario = usuarioService.listaUsuarioXidPagina(idUsuario);
            Date fechaServidor = comunService.obtenerFechaHoraServidor(1l);
            LogTrans logTransGuardado = logTransService.crudLogTrans(new LogTrans(idUsuario, fechaServidor), 1);
            List<SigDetallePublicacion> listaDetallePublicacion = sigDetallePublicacionService.lista_generarSigDetallePublicacion(fecha_final, listaGacetaNoCumple);
            if (!listaDetallePublicacion.isEmpty()) {
                publicacion.setIdlogtrans(logTransGuardado.getIdLogTrans());
                publicacion.setNumero_gaceta(numero_gaceta);
                publicacion.setMes(mes);
                publicacion.setGestion(gestion);
                publicacion.setTotal((long) listaDetallePublicacion.size());
                publicacion.setEstado_publicacion(EnumEstadoPublicacion.PREPUBLICADO.getCodigo());
                publicacion.setEstado(EnumEstado.ACTIVO.getCodigo());
                if (publicacion.getIdpublicacion() == null) {
                    publicacionNueva = guardar_modificar_listar_SigPublicacion(publicacion, 1);
                } else {
                    publicacionNueva = publicacion;
                }
                String descripcion = "";
                String numeroSM = "";
                String gestionSM = "";
                String extensionSM = "";
                for (SigDetallePublicacion item : listaDetallePublicacion) {
                    // System.out.println("PARA APILAR guardar ---REGENERAR " + item.getMarca());
                    item.setIdpublicacion(publicacionNueva.getIdpublicacion());
                    item.setIdlogtrans(logTransGuardado.getIdLogTrans());
                    if (item.getSm() != null) {
                        HashMap resultado = comunService.obtenerNumeroGestionExtensionCodigoSM(item.getSm());
                        numeroSM = (resultado.get("numero").toString());
                        gestionSM = (resultado.get("gestion").toString());
                        extensionSM = resultado.get("extension").toString();
                        descripcion = numeroSM + " - " + gestionSM;
                        if (!extensionSM.equals("")) {
                            descripcion = descripcion + " - " + extensionSM;
                        }
                    }
                    if (item.getNombre_apoderado() != null) {
                        if (item.getNombre_apoderado().trim().equals("")) {

                            item.setNombre_apoderado("UNIPERSONAL");
                        }
                    } else {

                        item.setNombre_apoderado("UNIPERSONAL");
                    }
                    item.setSm_descripcion(descripcion);
                    item.setEstado(EnumEstado.ACTIVO.getCodigo());
                    sigDetallePublicacionService.guardar_modificar_listar_SigDetallePublicacion(item, 1);

                    Seguimiento seguimientoNuevo = new Seguimiento();
                    seguimientoNuevo.setId(item.getIdsignomarca());
                    seguimientoNuevo.setIdUsuario((idUsuario));
                    seguimientoNuevo.setIdLogTrans(logTransGuardado.getIdLogTrans());
                    seguimientoNuevo.setSm(item.getSm());
                    seguimientoNuevo.setNumeroPublicacion(item.getNumero_publicacion());
                    seguimientoNuevo.setNumeroRegistro(null);
                    seguimientoNuevo.setSerieRegistro(null);
                    seguimientoNuevo.setEtapa("RPP");
                    seguimientoNuevo.setFechaRecepcion(fechaServidor);
                    seguimientoNuevo.setFechaFin(null);
                    seguimientoNuevo.setObservacion(null);
                    seguimientoNuevo.setEditable(false);
                    seguimientoNuevo.setEstado(EnumEstado.ACTIVO.getCodigo());
                    int plazoLimite = flujoTiempoService.obtieneFlujoXIdetapa("RPP",1);
                    seguimientoNuevo.setPlazo_limite(plazoLimite);
                    seguimientoNuevo.setDia_pasivo(seguimientoService.cuentaDiasPasivo(seguimientoNuevo.getFechaRecepcion(),
                            EnumPrefijoTablas.SIGNO.getCodigo(),
                            item.getIdsignomarca(),
                            listUsuario.get(0).getIdregional()));
                    
                    Seguimiento nuevo = seguimientoService.guardar_modificar_listar_Seguimiento(seguimientoNuevo, EnumPrefijoTablas.SIGNO.getCodigo(), 1);
                     
                }
                sigDetallePublicacionService.numeracionSigDetallePublicacion(publicacionNueva.getIdpublicacion());
            }
        } catch (Exception ex) {
            Logger.getLogger(SigPublicacionServiceImpl.class.getName()).log(Level.SEVERE, "Error al crear detalle publicacion", ex);
        }
        return publicacionNueva;
    }

    @Override
    public SigPublicacion listar_SigPublicacion_numero_estado(Long numero_gaceta, String estado_publicacion) {
        String SQL = "select * from sigpublicacion where numero_gaceta=" + numero_gaceta
                + " and estado_publicacion ='" + estado_publicacion + "'"
                + " and estado= 'AC'"; //FILTRAR MAYOR A 2015 IMPORTANTE SU
        if (!jdbcTemplate.query(SQL, new SigPublicacionMapper()).isEmpty()) {
            List<SigPublicacion> listapublicacion = jdbcTemplate.query(SQL, new SigPublicacionMapper());
            return listapublicacion.get(0);
        }
        return null;
    }

    @Override
    public void eliminar_SigPublicacion_SigDetallePublicacion(Long idPublicacion) {
        List<SigDetallePublicacion> listaSigDetallePublicacion = sigDetallePublicacionService.listaSigDetallePublicacionXidpublicacion(idPublicacion);
        for (SigDetallePublicacion item : listaSigDetallePublicacion) {
            item.setEstado(EnumEstado.NO_ACTIVO.getCodigo());
            sigDetallePublicacionService.guardar_modificar_listar_SigDetallePublicacion(item, 2);
            try {
                Seguimiento seguimientoEliminar = seguimientoService.listar_SigSeguimiento_etapa_iniciado(item.getIdsignomarca(), "RPP");
                if (seguimientoEliminar != null) {
                    seguimientoEliminar.setEstado(EnumEstado.NO_ACTIVO.getCodigo());
                    seguimientoService.guardar_modificar_listar_Seguimiento(seguimientoEliminar, EnumPrefijoTablas.SIGNO.getCodigo(), 2);
                }
            } catch (Exception ex) {
                Logger.getLogger(SigPublicacionServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        SigPublicacion publicacionEliminar = new SigPublicacion();
        publicacionEliminar.setIdpublicacion(idPublicacion);
        SigPublicacion publicacionEliminarFinal = guardar_modificar_listar_SigPublicacion(publicacionEliminar, 4);
        publicacionEliminarFinal.setEstado(EnumEstado.NO_ACTIVO.getCodigo());
        guardar_modificar_listar_SigPublicacion(publicacionEliminarFinal, 2);
    }

    @Override
    public SigPublicacion listar_SigPublicacion_sm(Long sm) {
        try {
            String SQL = "select spub.* "
                    + "from  sigsignomarca sig "
                    + "join sigdetallepublicacion dpub on dpub.idsignomarca = sig.idsignomarca "
                    + "join sigpublicacion spub on spub.idpublicacion = dpub.idpublicacion  "
                    + "where sig.sm= " + sm + "  and sig.estado = 'AC' and dpub.estado= 'AC' order by 1 desc limit 1";
            SigPublicacion publicacion = jdbcTemplate.queryForObject(SQL, new SigPublicacionMapper());
            return publicacion;
        } catch (EmptyResultDataAccessException e) {
            //System.out.println("vacio no existe publicacion para la marca");
            return null;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<Integer> lista_Gaceta_Prepublicacion() {
        List<Integer> listSeccion = new ArrayList<Integer>();
        try {
            String SQL = "select spub.* from sigpublicacion spub \n"
                    + "where (spub.estado_publicacion='PPUB' or spub.estado_publicacion='ENVI') and spub.estado='AC'";

            //SigPublicacion lista= jdbcTemplate.queryForObject(SQL, new SigPublicacionMapper());
            listSeccion = jdbcTemplate.query(SQL, new RowMapper() {
                public Object mapRow(ResultSet resultSet, int i) throws SQLException {
                    return resultSet.getInt(3);
                }
            });
            return listSeccion;
        } catch (EmptyResultDataAccessException e) {
            //System.out.println("vacio no existe publicacion para la marca");
            return new ArrayList<Integer>();
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public SigPublicacion obtenerIdPublicacionXNumeroGaceta(Long numero_gaceta) {
        if (numero_gaceta != null) {
            String SQL = "select * from sigpublicacion where numero_gaceta=" + numero_gaceta
                    + " and estado= 'AC'";

            if (!jdbcTemplate.query(SQL, new SigPublicacionMapper()).isEmpty()) {
                List<SigPublicacion> listapublicacion = jdbcTemplate.query(SQL, new SigPublicacionMapper());
                return listapublicacion.get(0);
            }
            return null;
        }
        return null;
    }

}
