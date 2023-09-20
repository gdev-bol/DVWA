/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package snp.gob.bo.gimodel.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import snp.gob.bo.gimodel.entidad.Recibo;

/**
 *
 * @author jano Rojas
 */
public class ReciboMapper implements RowMapper<Recibo> {

    @Override
    public Recibo mapRow(ResultSet r, int i) throws SQLException {
        Recibo recibo = new Recibo();
                recibo.setIdRecibo(r.getLong("idrecibo"));
                recibo.setIdTasa(r.getLong("idtasa"));
                recibo.setIdLogTrans(r.getLong("idlogtrans"));
                recibo.setNumeroRecibo(r.getLong("numero_recibo"));
                recibo.setSerieRecibo(r.getString("serie_recibo"));
                recibo.setFechaEmisionRecibo(r.getTimestamp("fecha_emision_recibo"));
                recibo.setMonto(r.getBigDecimal("monto"));
                recibo.setLiteralMonto(r.getString("literal_monto"));
                recibo.setConcepto(r.getString("concepto"));
                recibo.setDatoRecibo(r.getString("dato_recibo"));
                recibo.setCantidad(r.getInt("cantidad"));
                recibo.setEstadoRecibo(r.getString("estado_recibo"));
                recibo.setOrigen(r.getString("origen"));
                recibo.setEstado(r.getString("estado"));
                recibo.setSolicitante(r.getString("solicitante"));
                recibo.setApoderado(r.getString("apoderado"));
                recibo.setNumeroTitulo(r.getLong("numero_titulo"));
                recibo.setSerieTitulo(r.getString("serie_titulo"));
                recibo.setTipoTitulo(r.getString("tipo_titulo"));
                recibo.setTipoTramiteIngresado(r.getString("tipo_tramite_ingresado"));
                recibo.setNumeroTramiteIngresado(r.getString("numero_tramite_ingresado"));
                recibo.setGestionTramiteIngresado(r.getString("gestion_tramite_ingresado"));
                recibo.setExtensionTramiteIngresado(r.getString("extension_tramite_ingresado"));
                recibo.setClaseIngresado(r.getLong("clase_ingresado"));
                recibo.setDescIngresado(r.getString("desc_ingresado"));
                recibo.setApoderadoIngresado(r.getString("apoderado_ingresado"));
                recibo.setDepIngresado(r.getLong("dep_ingresado"));
                recibo.setTituloIngresado(r.getLong("titulo_ingresado"));
                recibo.setSerieTituloIngresado(r.getString("serie_titulo_ingresado"));
                recibo.setTipoTituloIngresado(r.getString("tipo_titulo_ingresado"));
                recibo.setTramite(r.getString("tramite"));
                recibo.setNumeroTramite(r.getLong("numero_tramite"));
                recibo.setGestionTramite(r.getLong("gestion_tramite"));
                recibo.setClaseTramite(r.getInt("clasetramite"));
                recibo.setExtensionTramite(r.getString("extension_tramite"));
                recibo.setDescripcionMarca(r.getString("descripcionmarca"));
                recibo.setExpediente(r.getLong("expediente"));
                recibo.setTipoTramite(r.getString("tipo_tramite"));
                recibo.setSigla(r.getString("sigla"));
                recibo.setObservacion(r.getString("observacion"));
                recibo.setIdRegional(r.getLong("idregional"));
                recibo.setIdUsuario(r.getLong("idusuario"));
        return recibo;
    }
    
}
