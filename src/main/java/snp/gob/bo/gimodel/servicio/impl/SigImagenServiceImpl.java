///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package snp.gob.bo.gimodel.servicio.impl;
//
//import java.util.List;
//import javax.sql.DataSource;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.dao.EmptyResultDataAccessException;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Service;
//import snp.gob.bo.gimodel.entidad.SigImagen;
//import snp.gob.bo.gimodel.mapper.SigImagenMapper;
//import snp.gob.bo.gimodel.servicio.SigImagenService;
//
///**
// *
// * @author Eddy Valero
// * @version 1.0, 27/08/2016
// */
//@Service("sigImagenService")
//public class SigImagenServiceImpl implements SigImagenService {
//
//    private DataSource dataSource;
//    private JdbcTemplate jdbcTemplate;
//
//    @Override
//    @Autowired
//    public void setDataSource(DataSource dataSource) {
//        this.dataSource = dataSource;
//        this.jdbcTemplate = new JdbcTemplate(dataSource);
//    }
//
//    @Override
//    public SigImagen registrarSigImagen(SigImagen sigImagen) throws Exception {
//        try {
//            String SQL = "select * from reg_sigimagen(?,?, ?);";
//            SigImagen sigIma = (SigImagen) jdbcTemplate.queryForObject(
//                    SQL,
//                    new SigImagenMapper(),
//                    sigImagen.getIdLogoTipo(),
//                    sigImagen.getIdLogTrans(),
//                    sigImagen.getImagen());
//
//            return sigIma;
//        } catch (Exception e) {
//            throw e;
//        }
//    }
//
//    @Override
//    public List<SigImagen> obtenerListaSigImagenXIdSigLogoTipo(Long idSigLogoTipo) throws Exception {
//        try {
//            String SQL = "select * from sigimagen where idlogotipo = ? ";
//
//            List<SigImagen> listaSigImagen = this.jdbcTemplate.query(SQL, new Object[]{idSigLogoTipo}, new SigImagenMapper());
//            return listaSigImagen;
//
//        } catch (Exception e) {
//            throw e;
//        }
//    }
//
//    @Override
//    public SigImagen obtenerSigImagenXIdSigLogoTipo(Long idSigLogoTipo) throws Exception {
//        try {
//            String SQL = "select * from sigimagen where idlogotipo = ? ";
//
//            SigImagen listaSigImagen = this.jdbcTemplate.queryForObject(SQL, new Object[]{idSigLogoTipo}, new SigImagenMapper());
//            return listaSigImagen;
//
//        }catch (EmptyResultDataAccessException e) {
//            return new SigImagen();
//        } catch (Exception e) {
//            throw e;
//        }
//    }
//
//    @Override
//    public SigImagen guardar_modificar_listar_SigImagen(SigImagen sigImagen, Integer operacion) throws Exception {
//        try {
//            String SQL = "select * from crud_sigimagen( ?, ?, ?, ?, ?); ";
//            SigImagen sigImagenBase = jdbcTemplate.queryForObject(SQL,
//                    new SigImagenMapper(),
//                    sigImagen.getIdImagen(),
//                    sigImagen.getIdLogoTipo(),
//                    sigImagen.getIdLogTrans(),
//                    sigImagen.getImagen(),
//                    operacion
//                    );
//            if (sigImagenBase.getIdImagen() != null) {
//                return sigImagenBase;
//            }
//            return new SigImagen();
//        } catch (Exception e) {
//            throw e;
//        }
//    }
//
//}
