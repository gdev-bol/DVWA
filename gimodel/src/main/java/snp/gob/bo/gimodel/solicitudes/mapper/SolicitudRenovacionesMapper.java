/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package snp.gob.bo.gimodel.solicitudes.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import snp.gob.bo.gimodel.solicitudes.entidades.Solicitantes;
import snp.gob.bo.gimodel.solicitudes.entidades.SolicitudRenovaciones;

/**
 *
 * @author Eddy Valero
 * @version 1.0, 23/08/2016
 */
public class SolicitudRenovacionesMapper implements RowMapper<SolicitudRenovaciones> {

    @Override
    public SolicitudRenovaciones mapRow(ResultSet rs, int i) throws SQLException {
        
        SolicitudRenovaciones solicitudRenovaciones  = new  SolicitudRenovaciones();
        
        solicitudRenovaciones.setId(rs.getLong("id"));
        solicitudRenovaciones.setTipoGenero(rs.getString("tipogenero"));
        solicitudRenovaciones.setSignoRegistrado(rs.getString("signoregistrado"));
        solicitudRenovaciones.setClaseNizaRegistrado(rs.getInt("clasenizaregistrado"));
        solicitudRenovaciones.setNumeroRegistro(rs.getLong("numeroregistro"));
        solicitudRenovaciones.setSerieRegistro(rs.getString("serieregistro"));
        solicitudRenovaciones.setFechaOtorgacionMarca(rs.getTimestamp("fechaotorgacionmarca"));
        solicitudRenovaciones.setNumeroUltimaRenovacion(rs.getInt("numeroultimarenovacion"));
        solicitudRenovaciones.setNumeroPenultimaRenovacion(rs.getInt("numeropenultimarenovacion"));
        solicitudRenovaciones.setListaProductosLimitacion(rs.getString("listaproductoslimitacion"));
        solicitudRenovaciones.setClaseNizaReclasificacion(rs.getInt("clasenizareclasificacion"));
        solicitudRenovaciones.setEstado(rs.getString("estado"));
        solicitudRenovaciones.setFormularioId(rs.getLong("formulario_id"));

        return solicitudRenovaciones;
        
    }
    
    
    
}
