/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import snp.gob.bo.gimodel.entidad.Seguimiento;

/**
 * Entidad responsable de la Tabla Seguimiento
 *
 * @author Eddy Valero
 * @see SeguimientoMapper
 * @version 1.0, 08/09/2016
 */
public class SeguimientoMapper implements RowMapper<Seguimiento> {

    @Override
    public Seguimiento mapRow(ResultSet rs, int i) throws SQLException {
        Seguimiento sigSeguimiento = new Seguimiento();
        
        sigSeguimiento.setIdSeguimiento(rs.getLong("idseguimiento"));
        sigSeguimiento.setId(rs.getLong("idsignomarca"));
        sigSeguimiento.setIdUsuario(rs.getLong("idusuario"));
        sigSeguimiento.setIdLogTrans(rs.getLong("idlogtrans"));
        sigSeguimiento.setSm(rs.getLong("sm"));
        sigSeguimiento.setNumeroPublicacion(rs.getLong("numero_publicacion"));
        sigSeguimiento.setNumeroRegistro(rs.getLong("numero_registro"));
        sigSeguimiento.setSerieRegistro(rs.getString("serie_registro"));
        sigSeguimiento.setEtapa(rs.getString("etapa"));
        sigSeguimiento.setFechaRecepcion(rs.getTimestamp("fecha_recepcion"));
        sigSeguimiento.setFechaFin(rs.getTimestamp("fecha_fin"));
        sigSeguimiento.setPlazoReal(rs.getInt("plazo_real"));
        sigSeguimiento.setTotalTiempo(rs.getLong("total_tiempo"));
        sigSeguimiento.setObservacion(rs.getString("observacion"));
        sigSeguimiento.setEditable(rs.getBoolean("editable"));
        sigSeguimiento.setEstado(rs.getString("estado"));
        sigSeguimiento.setPlazo_limite(rs.getInt("plazo_limite"));
        sigSeguimiento.setDia_pasivo(rs.getInt("dia_pasivo"));
        
        return sigSeguimiento;
    }

}
