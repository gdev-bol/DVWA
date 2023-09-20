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
//import snp.gob.bo.gimodel.entidad.SigImagen;
//
///**
// *
// * @author Eddy Valero
// * @version 1.0, 27/08/2016
// */
//public class SigImagenMapper implements RowMapper<SigImagen> {
//
//    @Override
//    public SigImagen mapRow(ResultSet rs, int i) throws SQLException {
//        
//        SigImagen sigImagen = new SigImagen();
//        
//        sigImagen.setIdImagen(rs.getLong("idimagen"));
//        sigImagen.setIdLogoTipo(rs.getLong("idlogotipo"));
//        sigImagen.setIdLogTrans(rs.getLong("idlogtrans"));
//        sigImagen.setImagen(rs.getBytes("imagen"));
//
//        return sigImagen;
//    }
//
//}
