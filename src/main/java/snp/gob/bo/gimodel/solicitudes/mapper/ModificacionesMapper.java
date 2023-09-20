/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.solicitudes.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import snp.gob.bo.gimodel.solicitudes.entidades.Modificaciones;

/**
 *
 * @author Eddy Valero
 * @version 1.0, 24/10/2016
 */
public class ModificacionesMapper implements RowMapper<Modificaciones> {

    @Override
    public Modificaciones mapRow(ResultSet rs, int i) throws SQLException {

        Modificaciones modificaciones = new Modificaciones();

        modificaciones.setId(rs.getLong("id"));
        modificaciones.setSm(rs.getLong("sm"));
        modificaciones.setNumeroFormulario(rs.getString("nroformulario"));
        modificaciones.setOficina(rs.getString("oficina"));
        modificaciones.setNumeroRegistro(rs.getLong("numeroregistro"));
        modificaciones.setSerieRegistro(rs.getString("serieregistro"));

        if (rs.getString("numerorenovacion") != null) {
            modificaciones.setNumeroRenovacion(rs.getLong("numerorenovacion"));

        } else {
            modificaciones.setNumeroRenovacion(null);
        }
        modificaciones.setSerieRenovacion(rs.getString("serierenovacion"));

        if (rs.getString("nropublicacion") != null) {
            modificaciones.setNumeroPublicacion(rs.getLong("nropublicacion"));
        } else {
            modificaciones.setNumeroPublicacion(null);
        }
        modificaciones.setSignoRegistro(rs.getString("signoregistro"));

        if (rs.getString("claseregistrado") != null) {

            modificaciones.setClaseRegistrado(rs.getInt("claseregistrado"));
        } else {
            modificaciones.setClaseRegistrado(null);
        }

        modificaciones.setTipoGeneroRegistrado(rs.getString("tipogeneroregistrado"));
        modificaciones.setMarcaAcomp(rs.getString("marcaacomp"));
        if (rs.getString("reg_lc_registrado") != null) {
            modificaciones.setRegLcRegistrado(rs.getLong("reg_lc_registrado"));
        } else {
            modificaciones.setRegLcRegistrado(null);
        }
        modificaciones.setSerieLcRegistrado(rs.getString("serie_lc_registrado"));
        modificaciones.setFechaLcRegistrado(rs.getTimestamp("fecha_lc_registrado"));
        modificaciones.setNuevoPaisConstitucion(rs.getString("nuevopaisconstitucion"));
        modificaciones.setLuInicio(rs.getTimestamp("luinicio"));
        modificaciones.setLuFin(rs.getTimestamp("lufin"));
        modificaciones.setTipoModificacion(rs.getString("tipomodificacion"));
        modificaciones.setListaProducto(rs.getString("listaproducto"));
        modificaciones.setEstado(rs.getString("estado"));

        if (rs.getString("dependiente_mod") != null) {

            modificaciones.setDependienteMod(rs.getInt("dependiente_mod"));
        } else {
            modificaciones.setDependienteMod(null);
        }

        modificaciones.setFormularioId(rs.getInt("formulario_id"));

        return modificaciones;

    }

}
