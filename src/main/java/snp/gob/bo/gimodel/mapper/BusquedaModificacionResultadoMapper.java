/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import snp.gob.bo.gimodel.entidad.BusquedaModificacionResultado;

/**
 *
 * @author susana
 */
public class BusquedaModificacionResultadoMapper implements RowMapper {

    @Override
    public BusquedaModificacionResultado mapRow(ResultSet rs, int i) throws SQLException {
        BusquedaModificacionResultado busquedaModificacionResultado = new BusquedaModificacionResultado();
        busquedaModificacionResultado.setIdmodificacion(rs.getLong("idmodificacion"));
        busquedaModificacionResultado.setSigla(rs.getString("sigla"));
        busquedaModificacionResultado.setNumero(rs.getLong("numero"));
        busquedaModificacionResultado.setGestion(rs.getInt("gestion"));
        busquedaModificacionResultado.setSm(rs.getLong("sm"));
        busquedaModificacionResultado.setFecha_ingreso(rs.getTimestamp("fecha_ingreso"));
        busquedaModificacionResultado.setOficina(rs.getString("oficina"));
        busquedaModificacionResultado.setNumero_registro(rs.getLong("numero_registro"));
        busquedaModificacionResultado.setSerie_registro(rs.getString("serie_registro"));
        busquedaModificacionResultado.setNumero_renovacion(rs.getLong("numero_renovacion"));
        busquedaModificacionResultado.setSerie_renovacion(rs.getString("serie_renovacion"));
        busquedaModificacionResultado.setNumero_publicacion(rs.getLong("numero_publicacion"));
        busquedaModificacionResultado.setSigno_registrado(rs.getString("signo_registrado"));
        busquedaModificacionResultado.setIdclase_registrado(rs.getLong("idclase_registrado"));
        busquedaModificacionResultado.setTipo_genero_registrado(rs.getString("tipo_genero_registrado"));
        busquedaModificacionResultado.setSolicitante(rs.getString("solicitante"));
        busquedaModificacionResultado.setApoderado(rs.getString("apoderado"));
        busquedaModificacionResultado.setNuevo_titular(rs.getString("nuevo_titular"));
        busquedaModificacionResultado.setPais_nuevo_titular(rs.getString("pais_nuevo_titular"));
        busquedaModificacionResultado.setDescripcion_departamento_titular(rs.getString("descripcion_departamento_titular"));
        busquedaModificacionResultado.setDireccion_titular(rs.getString("direccion_titular"));
        busquedaModificacionResultado.setNumero_resolucion(rs.getInt("numero_resolucion"));
        busquedaModificacionResultado.setGestion_resolucion(rs.getInt("gestion_resolucion"));
        busquedaModificacionResultado.setFecha_resolucion(rs.getTimestamp("fecha_resolucion"));
        busquedaModificacionResultado.setTipo_modificacion(rs.getString("tipo_modificacion"));
        busquedaModificacionResultado.setEstado_modificacion(rs.getString("estado_modificacion"));
        busquedaModificacionResultado.setUsuario(rs.getString("usuario"));
        busquedaModificacionResultado.setTitular_registrado(rs.getString("titular_registrado"));
        busquedaModificacionResultado.setDireccion_registrado(rs.getString("direccion_registrado"));
        return busquedaModificacionResultado;
    }

}
