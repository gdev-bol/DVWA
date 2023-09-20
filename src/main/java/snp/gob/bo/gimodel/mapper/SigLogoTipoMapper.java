///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package snp.gob.bo.gimodel.mapper;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import org.springframework.jdbc.core.RowMapper;
//import snp.gob.bo.gimodel.entidad.SigLogoTipo;
//
///**
// *
// * @author Eddy Valero
// * @version 1.0, 27/08/2016
// */
//public class SigLogoTipoMapper implements RowMapper<SigLogoTipo> {
//
//    @Override
//    public SigLogoTipo mapRow(ResultSet rs, int i) throws SQLException {
//        
//        SigLogoTipo sigLogoTipo = new SigLogoTipo();
//        
//        sigLogoTipo.setIdLogoTipo(rs.getLong("idlogotipo"));
//        sigLogoTipo.setIdSignoMarca(rs.getLong("idsignomarca"));
//        sigLogoTipo.setIdLogTrans(rs.getLong("idlogtrans"));
//        sigLogoTipo.setUrlLogoTipo(rs.getString("urllogotipo"));
//        sigLogoTipo.setPrincipal(rs.getBoolean("principal"));
//        sigLogoTipo.setNombreArchivo(rs.getString("nombre_archivo"));
//        sigLogoTipo.setExtensionArchivo(rs.getString("extension_archivo"));
//        sigLogoTipo.setEstado(rs.getString("estado"));
//
//        return sigLogoTipo;
//    }
//
//}
