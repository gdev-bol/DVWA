/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import snp.gob.bo.gimodel.entidad.SigSignoMarca;

/**
 *
 * @author eddy
 */
public class SigSignoMarcaMapper implements RowMapper<SigSignoMarca> {

    @Override
    public SigSignoMarca mapRow(ResultSet rs, int i) throws SQLException {
        SigSignoMarca sigSignoMarca = new SigSignoMarca();
        sigSignoMarca.setIdSignoMarca(rs.getLong("idsignomarca"));
        sigSignoMarca.setIdLogTrans(rs.getLong("idlogtrans"));
        sigSignoMarca.setNumeroFormulario(rs.getString("numero_formulario"));
        sigSignoMarca.setSm(rs.getLong("sm"));
        sigSignoMarca.setSigno(rs.getString("signo"));
        sigSignoMarca.setTipoGenero(rs.getString("tipo_genero"));
        sigSignoMarca.setDescripcionSigno(rs.getString("descripcion_signo"));
        sigSignoMarca.setDescripcionLogotipo(rs.getString("descripcion_logotipo"));
        sigSignoMarca.setUbicacion(rs.getString("ubicacion"));
        sigSignoMarca.setEstadoMarca(rs.getString("estado_marca"));
        if (rs.getString("nro_recibo") != null) {
            sigSignoMarca.setNumeroRecibo(rs.getLong("nro_recibo"));
        } else {
            sigSignoMarca.setNumeroRecibo(null);
        }

        sigSignoMarca.setSerie(rs.getString("serie"));

        if (rs.getString("numero_titulo") != null) {
            sigSignoMarca.setNumeroTitulo(rs.getLong("numero_titulo"));
        } else {
            sigSignoMarca.setNumeroTitulo(null);
        }

        sigSignoMarca.setSerieTitulo(rs.getString("serie_titulo"));
        sigSignoMarca.setOrigenSolicitud(rs.getString("origen_solicitud"));
        if (rs.getString("numero_gaceta") != null) {
            sigSignoMarca.setNumeroGaceta(rs.getLong("numero_gaceta"));
        } else {
            sigSignoMarca.setNumeroGaceta(null);
        }

        if (rs.getString("numero_publicacion") != null) {
            sigSignoMarca.setNumeroPublicacion(rs.getLong("numero_publicacion"));
        } else {
            sigSignoMarca.setNumeroPublicacion(null);
        }

        sigSignoMarca.setFechaPublicacion(rs.getTimestamp("fecha_publicacion"));

        if (rs.getString("numero_registro") != null) {
            sigSignoMarca.setNumeroRegistro(rs.getLong("numero_registro"));
        } else {
            sigSignoMarca.setNumeroRegistro(null);
        }

        sigSignoMarca.setSerieRegistro(rs.getString("serie_registro"));
        sigSignoMarca.setResolucionRegistro(rs.getString("resolucion_registro"));
        sigSignoMarca.setFechaRegistro(rs.getTimestamp("fecha_registro"));

        if (rs.getString("orden_renovacion") != null) {
            sigSignoMarca.setOrdenRenovacion(rs.getInt("orden_renovacion"));
        } else {
            sigSignoMarca.setOrdenRenovacion(null);
        }

        if (rs.getString("numero_renovacion") != null) {

            sigSignoMarca.setNumeroRenovacion(rs.getInt("numero_renovacion"));
        } else {
            sigSignoMarca.setNumeroRenovacion(null);
        }

        sigSignoMarca.setExtensionRenovacion(rs.getString("extension_renovacion"));

        if (rs.getString("numero_resolucion_renovacion") != null) {
            sigSignoMarca.setNumeroResolucionRenovacion(rs.getLong("numero_resolucion_renovacion"));

        } else {
            sigSignoMarca.setNumeroResolucionRenovacion(null);
        }

        sigSignoMarca.setFechaRenovacion(rs.getTimestamp("fecha_renovacion"));
        sigSignoMarca.setOficina(rs.getString("oficina"));
        sigSignoMarca.setFechaSolicitud(rs.getTimestamp("fecha_solicitud"));
        sigSignoMarca.setFechaIngreso(rs.getTimestamp("fecha_ingreso"));
        sigSignoMarca.setEntregadoUsuario(rs.getBoolean("entregado_usuario"));
        sigSignoMarca.setNotoriedadMarca(rs.getBoolean("notoriedad_marca"));
        sigSignoMarca.setEstado(rs.getString("estado"));
        
        if (rs.getString("numero") != null) {
            sigSignoMarca.setNumero(rs.getInt("numero"));
        } else {
            sigSignoMarca.setNumeroRenovacion(null);
        }
        
        if (rs.getString("gestion") != null) {
            sigSignoMarca.setGestion(rs.getInt("gestion"));
        } else {
            sigSignoMarca.setGestion(null);
        }
        
        if (rs.getString("extension_marca") != null) {
            sigSignoMarca.setExtensionMarca(rs.getInt("extension_marca"));
        } else {
            sigSignoMarca.setExtensionMarca(null);
        }
        

        return sigSignoMarca;
    }

}
