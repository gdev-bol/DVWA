/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import snp.gob.bo.gimodel.entidad.SigDetallePublicacion;

/**
 *
 * @author susana
 */
public class SigDetallePublicacionMapper implements RowMapper<SigDetallePublicacion> {

    @Override
    public SigDetallePublicacion mapRow(ResultSet rs, int i) throws SQLException {
        SigDetallePublicacion sigDetallePublicacion = new SigDetallePublicacion();
        sigDetallePublicacion.setIddetallepublicacion(rs.getLong("iddetallepublicacion"));
        sigDetallePublicacion.setIdpublicacion(rs.getLong("idpublicacion"));
        sigDetallePublicacion.setIdsignomarca(rs.getLong("idsignomarca"));
        sigDetallePublicacion.setIdlogtrans(rs.getLong("idlogtrans"));
        sigDetallePublicacion.setNumero_publicacion(rs.getLong("numero_publicacion"));
        sigDetallePublicacion.setSm(rs.getLong("sm"));
        sigDetallePublicacion.setSm_descripcion(rs.getString("sm_descripcion"));
        sigDetallePublicacion.setFecha_ingreso(rs.getTimestamp("fecha_ingreso"));
        sigDetallePublicacion.setMarca(rs.getString("marca"));
        sigDetallePublicacion.setClase(rs.getInt("clase"));
        sigDetallePublicacion.setTipo_signo_descripcion(rs.getString("tipo_signo_descripcion"));
        sigDetallePublicacion.setTipo_genero(rs.getString("tipo_genero"));
        sigDetallePublicacion.setLista_productos(rs.getString("lista_productos"));
        sigDetallePublicacion.setDescripcion_signo(rs.getString("descripcion_signo"));
        sigDetallePublicacion.setNombre_titular(rs.getString("nombre_titular"));
        sigDetallePublicacion.setDocumento_titular(rs.getString("documento_titular"));
        sigDetallePublicacion.setPais_titular(rs.getString("pais_titular"));
        sigDetallePublicacion.setDescripcion_pais_titular(rs.getString("descripcion_pais_titular"));
        sigDetallePublicacion.setDescripcion_departamento_titular(rs.getString("descripcion_departamento_titular"));
        sigDetallePublicacion.setDireccion_titular(rs.getString("direccion_titular"));
        sigDetallePublicacion.setNombre_apoderado(rs.getString("nombre_apoderado"));
        sigDetallePublicacion.setDocumento_apoderado(rs.getString("documento_apoderado"));
        sigDetallePublicacion.setDireccion_apoderado(rs.getString("direccion_apoderado"));
        sigDetallePublicacion.setPrioridad(rs.getString("prioridad"));
        sigDetallePublicacion.setFecha_prioridad(rs.getString("fecha_prioridad"));
        sigDetallePublicacion.setPais_prioridad(rs.getString("pais_prioridad"));
        sigDetallePublicacion.setPais_prio_descripcion(rs.getString("pais_prio_descripcion"));
        sigDetallePublicacion.setNumero_seccion(rs.getInt("numero_seccion"));
        sigDetallePublicacion.setSeccion(rs.getString("seccion"));
        sigDetallePublicacion.setSubseccion(rs.getString("subseccion"));
        sigDetallePublicacion.setPublicado(rs.getBoolean("publicado"));
        sigDetallePublicacion.setEstado(rs.getString("estado"));
        
        return sigDetallePublicacion;
    }

}
