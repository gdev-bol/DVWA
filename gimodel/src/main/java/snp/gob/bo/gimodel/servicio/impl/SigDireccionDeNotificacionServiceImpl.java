/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.servicio.impl;

import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import snp.gob.bo.gimodel.entidad.SigDireccionDeNotificacion;
import snp.gob.bo.gimodel.mapper.SigDireccionDeNotificacionMapper;
import snp.gob.bo.gimodel.servicio.SigDireccionDeNotificacionService;
import snp.gob.bo.gimodel.solicitudes.entidades.DireccionNotificaciones;

/**
 *
 * @author Eddy Valero
 * @version 1.0, 29/08/2016
 */
@Service("sigDireccionDeNotificacionService")
public class SigDireccionDeNotificacionServiceImpl implements SigDireccionDeNotificacionService {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Override
    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public SigDireccionDeNotificacion registrarSigDireccionNotificacion(SigDireccionDeNotificacion sigDireccionNotificacion) throws Exception {
        try {
            String SQL = "select *from reg_sigdireccionnotificacion(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
            SigDireccionDeNotificacion sigDirecNotificacion = (SigDireccionDeNotificacion) jdbcTemplate.queryForObject(
                    SQL,
                    new SigDireccionDeNotificacionMapper(),
                    sigDireccionNotificacion.getIdSignoMarca(),
                    sigDireccionNotificacion.getIdLogTrans(),
                    sigDireccionNotificacion.getCiudadNotificacion(),
                    sigDireccionNotificacion.getZonaBarrio(),
                    sigDireccionNotificacion.getAvenidaCalle(),
                    sigDireccionNotificacion.getNumero(),
                    sigDireccionNotificacion.getEdificio(),
                    sigDireccionNotificacion.getPiso(),
                    sigDireccionNotificacion.getDepartamento(),
                    sigDireccionNotificacion.getReferenciaDireccion(),
                    sigDireccionNotificacion.getCorreoElectronico(),
                    sigDireccionNotificacion.getTelefono(),
                    sigDireccionNotificacion.getCelular(),
                    sigDireccionNotificacion.getEstado()
            );
            return sigDirecNotificacion;

        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public SigDireccionDeNotificacion obtenerDireccionNotificacionXSignoMarca(Long idSignoMarca) throws Exception {
        try {
            String SQL = "select * from sigdirecciondenotificacion "
                    + " where  idsignomarca = ? and estado='AC' "
                    + "";
            return jdbcTemplate.queryForObject(SQL, new Object[]{idSignoMarca}, new SigDireccionDeNotificacionMapper());

        } catch (Exception e) {
            throw e;
        }
    }

   // @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    @Override
    public SigDireccionDeNotificacion guardarModificarListarSigDireccionDeNotificacion(SigDireccionDeNotificacion sigDireccionDeNotificacion, Long idSignoMarca, Integer operacion) throws Exception {
        try {
            sigDireccionDeNotificacion.setIdSignoMarca(idSignoMarca);
            sigDireccionDeNotificacion.setEstado("AC");
            SigDireccionDeNotificacion sigDireccionDeNotificacionNuevo = new SigDireccionDeNotificacion();

            String SQL = "select * from crud_sigdirecciondenotificacion(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";

            sigDireccionDeNotificacionNuevo = (SigDireccionDeNotificacion) jdbcTemplate.queryForObject(SQL, new SigDireccionDeNotificacionMapper(),
                    sigDireccionDeNotificacion.getIdDireccionDeNotificacion(),
                    sigDireccionDeNotificacion.getIdSignoMarca(),
                    sigDireccionDeNotificacion.getIdLogTrans(),
                    sigDireccionDeNotificacion.getCiudadNotificacion(),
                    sigDireccionDeNotificacion.getZonaBarrio(),
                    sigDireccionDeNotificacion.getAvenidaCalle(),
                    sigDireccionDeNotificacion.getNumero(),
                    sigDireccionDeNotificacion.getEdificio(),
                    sigDireccionDeNotificacion.getPiso(),
                    sigDireccionDeNotificacion.getDepartamento(),
                    sigDireccionDeNotificacion.getCorreoElectronico(),
                    sigDireccionDeNotificacion.getReferenciaDireccion(),
                    sigDireccionDeNotificacion.getTelefono(),
                    sigDireccionDeNotificacion.getCelular(),
                    sigDireccionDeNotificacion.getEstado(),
                    operacion
            );

            return sigDireccionDeNotificacionNuevo;

        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public SigDireccionDeNotificacion crudSigDireccionDeNotificacion(SigDireccionDeNotificacion sigDireccionDeNotificacion, int parametro) throws Exception {
        try {
            String SQL = "select * from crud_sigdirecciondenotificacion("
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
            List<SigDireccionDeNotificacion> listaSigDireccion = jdbcTemplate.query(SQL, new SigDireccionDeNotificacionMapper(),
                    sigDireccionDeNotificacion.getIdDireccionDeNotificacion(),
                    sigDireccionDeNotificacion.getIdSignoMarca(),
                    sigDireccionDeNotificacion.getIdLogTrans(),
                    sigDireccionDeNotificacion.getCiudadNotificacion(),
                    sigDireccionDeNotificacion.getZonaBarrio(),
                    sigDireccionDeNotificacion.getAvenidaCalle(),
                    sigDireccionDeNotificacion.getNumero(),
                    sigDireccionDeNotificacion.getEdificio(),
                    sigDireccionDeNotificacion.getPiso(),
                    sigDireccionDeNotificacion.getDepartamento(),
                    sigDireccionDeNotificacion.getCorreoElectronico(),
                    sigDireccionDeNotificacion.getReferenciaDireccion(),
                    sigDireccionDeNotificacion.getTelefono(),
                    sigDireccionDeNotificacion.getCelular(),
                    sigDireccionDeNotificacion.getEstado(),
                    parametro);
            if (!listaSigDireccion.isEmpty()) {
                return listaSigDireccion.get(0);
            }
            return new SigDireccionDeNotificacion();
        } catch (Exception e) {
            throw e;
        }
    }
    @Override
    public SigDireccionDeNotificacion crudSigDireccionDeNotificacionSubsanacion(SigDireccionDeNotificacion sigDireccionDeNotificacion, List<DireccionNotificaciones> listaDireccionNotificacionSipi, int parametro) throws Exception {
        try {
            DireccionNotificaciones direccionNotificacionSipi;
            direccionNotificacionSipi = listaDireccionNotificacionSipi.get(0);
            if (direccionNotificacionSipi.getId() != null) {
                String SQL = "select * from crud_sigdirecciondenotificacion("
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
                List<SigDireccionDeNotificacion> listaSigDireccion = jdbcTemplate.query(SQL, new SigDireccionDeNotificacionMapper(),
                        sigDireccionDeNotificacion.getIdDireccionDeNotificacion(),
                        sigDireccionDeNotificacion.getIdSignoMarca(),
                        sigDireccionDeNotificacion.getIdLogTrans(),
                        direccionNotificacionSipi.getCiudadNotificacion(), //sigDireccionDeNotificacion.getCiudadNotificacion(),
                        direccionNotificacionSipi.getZonaBarrio(), //sigDireccionDeNotificacion.getZonaBarrio(),
                        direccionNotificacionSipi.getAvenidaCalle(), //sigDireccionDeNotificacion.getAvenidaCalle(),
                        direccionNotificacionSipi.getNumeroDomicilio(), //sigDireccionDeNotificacion.getNumero(),
                        direccionNotificacionSipi.getNombreEdificio(), //sigDireccionDeNotificacion.getEdificio(),
                        direccionNotificacionSipi.getPiso(),//sigDireccionDeNotificacion.getPiso(),
                        direccionNotificacionSipi.getDepartamento(),//sigDireccionDeNotificacion.getDepartamento(),
                        direccionNotificacionSipi.getCorreoelectronico(),//sigDireccionDeNotificacion.getCorreoElectronico(),
                        direccionNotificacionSipi.getReferencia(),//sigDireccionDeNotificacion.getReferenciaDireccion(),
                        direccionNotificacionSipi.getTelefono(),//sigDireccionDeNotificacion.getTelefono(),
                        direccionNotificacionSipi.getCelular(),//sigDireccionDeNotificacion.getCelular(),
                        sigDireccionDeNotificacion.getEstado(),
                        parametro);
                if (!listaSigDireccion.isEmpty()) {
                    return listaSigDireccion.get(0);
                }
            }
            return new SigDireccionDeNotificacion();
        } catch (Exception e) {
            throw e;
        }
    }
}
