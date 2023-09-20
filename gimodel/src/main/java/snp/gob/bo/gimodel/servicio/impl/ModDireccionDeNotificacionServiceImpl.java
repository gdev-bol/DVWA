/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.servicio.impl;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import snp.gob.bo.gimodel.entidad.ModDireccionDeNotificacion;
import snp.gob.bo.gimodel.enums.EnumEstado;
import snp.gob.bo.gimodel.enums.EnumNombreDominio;
import snp.gob.bo.gimodel.mapper.ModDireccionDeNotificacionMapper;
import snp.gob.bo.gimodel.servicio.DominioService;
import snp.gob.bo.gimodel.servicio.ModDireccionDeNotificacionService;
import snp.gob.bo.gimodel.solicitudes.entidades.DireccionNotificaciones;

/**
 *
 * @author susana
 */
@Service("modDireccionDeNotificacionService")
public class ModDireccionDeNotificacionServiceImpl implements ModDireccionDeNotificacionService {

    @Autowired
    private DominioService dominioService;
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

    // @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    @Override
    public ModDireccionDeNotificacion guardar_modificar_listar_ModDireccionDeNotificacion(ModDireccionDeNotificacion moddirecciondenotificacion, Long idmodificacion, Integer operacion, Long idLogTrans) {
        moddirecciondenotificacion.setIdmodificacion(idmodificacion);
        moddirecciondenotificacion.setIdlogtrans(idLogTrans);
        moddirecciondenotificacion.setEstado(EnumEstado.ACTIVO.getCodigo());
        ModDireccionDeNotificacion modDireccionDeNotificacionNuevo = new ModDireccionDeNotificacion();
        String SQL = "select * from crud_moddirecciondenotificacion(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";

        modDireccionDeNotificacionNuevo = (ModDireccionDeNotificacion) jdbcTemplate.queryForObject(SQL, new ModDireccionDeNotificacionMapper(),
                moddirecciondenotificacion.getIddirecciondenotificacion(),
                moddirecciondenotificacion.getIdmodificacion(),
                moddirecciondenotificacion.getIdlogtrans(),
                moddirecciondenotificacion.getCiudad_notificacion(),
                moddirecciondenotificacion.getZona_barrio(),
                moddirecciondenotificacion.getAvenida_calle(),
                moddirecciondenotificacion.getNumero(),
                moddirecciondenotificacion.getEdificio(),
                moddirecciondenotificacion.getPiso(),
                moddirecciondenotificacion.getDepartamento(),
                moddirecciondenotificacion.getCorreo_electronico(),
                moddirecciondenotificacion.getReferencia_direccion(),
                moddirecciondenotificacion.getTelefono(),
                moddirecciondenotificacion.getCelular(),
                moddirecciondenotificacion.getEstado(),
                operacion
        );
        return modDireccionDeNotificacionNuevo;
    }

    @Override
    public ModDireccionDeNotificacion modificarModDireccionDeNotificacionSubsanacion(ModDireccionDeNotificacion direccionNotificacionHidra,
            DireccionNotificaciones direccionNotificacionSipi, Long idmodificacion, Long idLogTrans) {

        int operacionModificar = 2;
        direccionNotificacionHidra.setIdmodificacion(idmodificacion);
        direccionNotificacionHidra.setIdlogtrans(idLogTrans);
        direccionNotificacionHidra.setEstado(EnumEstado.ACTIVO.getCodigo());
        ModDireccionDeNotificacion modDireccionDeNotificacionNuevo = new ModDireccionDeNotificacion();
        String SQL = "select * from crud_moddirecciondenotificacion(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";

        modDireccionDeNotificacionNuevo = (ModDireccionDeNotificacion) jdbcTemplate.queryForObject(SQL, new ModDireccionDeNotificacionMapper(),
                direccionNotificacionHidra.getIddirecciondenotificacion(),
                direccionNotificacionHidra.getIdmodificacion(),
                idLogTrans, //moddirecciondenotificacion.getIdlogtrans(),
                direccionNotificacionSipi.getCiudadNotificacion(), //moddirecciondenotificacion.getCiudad_notificacion(),
                direccionNotificacionSipi.getZonaBarrio(), //moddirecciondenotificacion.getZona_barrio(),
                direccionNotificacionSipi.getAvenidaCalle(), //moddirecciondenotificacion.getAvenida_calle(),
                direccionNotificacionSipi.getNumeroDomicilio(),//moddirecciondenotificacion.getNumero(),
                direccionNotificacionSipi.getNombreEdificio(),//moddirecciondenotificacion.getEdificio(),
                direccionNotificacionSipi.getPiso(),//moddirecciondenotificacion.getPiso(),
                direccionNotificacionSipi.getDepartamento(),//moddirecciondenotificacion.getDepartamento(),
                direccionNotificacionSipi.getCorreoelectronico(),//moddirecciondenotificacion.getCorreo_electronico(),
                direccionNotificacionSipi.getReferencia(),//moddirecciondenotificacion.getReferencia_direccion(),
                direccionNotificacionSipi.getTelefono(),//moddirecciondenotificacion.getTelefono(),
                direccionNotificacionSipi.getCelular(),//moddirecciondenotificacion.getCelular(),
                direccionNotificacionHidra.getEstado(),
                operacionModificar
        );
        return modDireccionDeNotificacionNuevo;
    }

    @Override
    public ModDireccionDeNotificacion buscarModDireccionDeNotificacionXidmodificacion(Long idmodificacion) {

        ModDireccionDeNotificacion notificacion = new ModDireccionDeNotificacion();

        String SQL = "select * from lista_moddireccionnotificacion_idmodificacion(?);";
        if (!jdbcTemplate.query(SQL, new ModDireccionDeNotificacionMapper(), idmodificacion).isEmpty()) {
            notificacion = (ModDireccionDeNotificacion) jdbcTemplate.queryForObject(SQL, new ModDireccionDeNotificacionMapper(), idmodificacion);
            return notificacion;
        }
        return notificacion;
    }

    @Override
    public String datos_notificacion(String ciudad, String zonBarrio, String avenidaCalle, String numero, String referencia,
            String edificio, String piso, String departamento, String correo) {

        String direccion = " ";
        try {
            if (!"".equals(ciudad) && ciudad != null) {
                String ciudadAux = dominioService.obtenerListadoDominioXCodigo(EnumNombreDominio.CIUDAD_NOTIFICACION.getCodigo(), ciudad).get(0).getNombre();
                direccion += ciudadAux + ", ";
            }
            if (!"".equals(zonBarrio) && zonBarrio != null) {
                direccion += "Barrio/Zona: " + zonBarrio.trim() + ", ";
            }
            if (!"".equals(avenidaCalle) && avenidaCalle != null) {
                direccion += "Av/Calle: " + avenidaCalle.trim() + ", ";
            }
            if (!"".equals(numero) && numero != null) {
                direccion += "N°: " + numero.trim() + ", ";
            }
            if (!"".equals(referencia) && referencia != null) {
                direccion += "Referencia: " + referencia.trim() + ", ";
            }
            if (!"".equals(edificio) && edificio != null) {
                direccion += "Edificio: " + edificio + ", ";
            }
            if (!"".equals(piso) && piso != null) {
                direccion += "Piso: " + piso + ", ";
            }
            if (!"".equals(departamento) && departamento != null) {
                direccion += "Departamento: " + departamento + ", ";
            }
            if (!"".equals(correo) && correo != null) {
                direccion += "Correo: " + correo;
            }
            direccion = direccion.trim();

        } catch (Exception ex) {
            Logger.getLogger(ModDireccionDeNotificacionServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (direccion.length() > 1) {
            return direccion.substring(0, direccion.length() - 1);
        } else {
            return null;
        }
    }

    @Override
    public String datos_celular(String celular, String telefono) {
        String celularValores = "";
        if (!"".equals(celular) && celular != null) {
            celularValores += celular + ", ";
        }
        if (!"".equals(telefono) && telefono != null) {
            celularValores += telefono;
        }
        return celularValores;
    }

}
