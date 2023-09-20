/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import snp.gob.bo.gimodel.entidad.TramiteEntrega;

/**
 *
 * @author luisangel
 */
public class TramiteEntregaMapper implements RowMapper<TramiteEntrega>{

    @Override
    public TramiteEntrega mapRow(ResultSet rs, int i) throws SQLException {
        
        TramiteEntrega tramiteentrega = new TramiteEntrega();
        tramiteentrega.setIdtramiteentrega(rs.getLong("idtramiteentrega"));
        tramiteentrega.setIdlogtrans(rs.getLong("idlogtrans"));
        tramiteentrega.setIdprocurador(rs.getLong("idprocurador"));
        tramiteentrega.setIdtramite(rs.getLong("idtramite"));
        tramiteentrega.setTipo_tramite(rs.getString("tipo_tramite"));
        tramiteentrega.setNumerobloque(rs.getInt("numerobloque"));
        tramiteentrega.setGestionbloque(rs.getInt("gestionbloque"));
        tramiteentrega.setSm(rs.getLong("sm"));
        tramiteentrega.setSigla(rs.getString("sigla"));
        tramiteentrega.setNumerotramite(rs.getInt("numerotramite"));
        tramiteentrega.setGestion(rs.getInt("gestion"));
        tramiteentrega.setExtension(rs.getString("extension"));
        tramiteentrega.setNumero_registro(rs.getLong("numero_registro"));
        tramiteentrega.setSerie_registro(rs.getString("serie_registro"));
        tramiteentrega.setNumero_publicacion(rs.getLong("numero_publicacion"));
        tramiteentrega.setMarca(rs.getString("marca"));
        tramiteentrega.setClase_niza(rs.getObject("clase_niza") != null ? rs.getInt("clase_niza") : null);
//        System.out.println(tramiteentrega.getClase_niza());
        //tramiteentrega.setClase_niza(rs.getInt("clase_niza"));
        tramiteentrega.setEstadoanteriortramite(rs.getString("estadoanteriortramite"));
        tramiteentrega.setUbicacionanterior(rs.getString("ubicacionanterior"));
        tramiteentrega.setIdusuario(rs.getLong("idusuario"));
        tramiteentrega.setIdregionalentrega(rs.getLong("idregionalentrega"));
        tramiteentrega.setFecha_entrega(rs.getTimestamp("fecha_entrega"));
        tramiteentrega.setEstado(rs.getString("estado"));
        
        return tramiteentrega;
              
    }
    
}
