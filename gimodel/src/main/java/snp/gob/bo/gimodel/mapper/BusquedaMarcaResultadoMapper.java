/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import snp.gob.bo.gimodel.entidad.BusquedaMarcaResultado;

/**
 *
 * @author Eddy Valero
 * @version 1.0, 17/10/2016
 * @see BusquedaMarcaResultado
 */
public class BusquedaMarcaResultadoMapper implements RowMapper {

    @Override
    public BusquedaMarcaResultado mapRow(ResultSet rs, int i) throws SQLException {
        
        BusquedaMarcaResultado busquedaMarcaResultado = new BusquedaMarcaResultado();
        
        busquedaMarcaResultado.setIdSignoMarca(rs.getLong("idsignomarca"));
        busquedaMarcaResultado.setSm(rs.getLong("sm"));
        busquedaMarcaResultado.setNumero(rs.getInt("numero"));
        busquedaMarcaResultado.setGestion(rs.getInt("gestion"));        
        busquedaMarcaResultado.setMarca(rs.getString("marca"));
        busquedaMarcaResultado.setClase(rs.getInt("claseniza"));
        busquedaMarcaResultado.setParecido(rs.getInt("parecido"));
        busquedaMarcaResultado.setFechaSolicitud(rs.getTimestamp("fecha_solicitud")); 
        busquedaMarcaResultado.setNroPublicacion(rs.getLong("numero_publicacion"));
        busquedaMarcaResultado.setNroRegistro(rs.getLong("numero_registro"));
        busquedaMarcaResultado.setSerieRegistro(rs.getString("serie_registro"));
        busquedaMarcaResultado.setSolicitante(rs.getString("solicitante"));
        busquedaMarcaResultado.setApoderado(rs.getString("apoderado"));
        busquedaMarcaResultado.setEstado(rs.getString("estado"));
        busquedaMarcaResultado.setFechaRegistro(rs.getTimestamp("fecha_registro"));
        busquedaMarcaResultado.setSrFechaResolucion(rs.getTimestamp("fecha_renovacion"));
        busquedaMarcaResultado.setSrTitular(rs.getString("titular"));
        busquedaMarcaResultado.setSr(rs.getString("sr"));
        busquedaMarcaResultado.setGestionSr(rs.getInt("gestion_sr"));

        return busquedaMarcaResultado;
    }

}
