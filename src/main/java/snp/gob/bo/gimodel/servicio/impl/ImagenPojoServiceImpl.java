///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package snp.gob.bo.gimodel.servicio.impl;
//
//import javax.sql.DataSource;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Service;
//import snp.gob.bo.gimodel.entidad.ImagenPojo;
//import snp.gob.bo.gimodel.entidad.SigImagen;
//import snp.gob.bo.gimodel.entidad.SigLogoTipo;
//import snp.gob.bo.gimodel.servicio.ImagenPojoService;
//import snp.gob.bo.gimodel.servicio.SigImagenService;
//import snp.gob.bo.gimodel.servicio.SigLogoTipoService;
//
///**
// *
// * @author Eddy Valero
// * @version 1.0, 13/10/2016
// */
//@Service("imagenPojoService")
//public class ImagenPojoServiceImpl implements ImagenPojoService {
//
//    private DataSource dataSource;
//    private JdbcTemplate jdbcTemplate;
//
//    @Autowired
//    SigImagenService sigImagenService;
//
//    @Autowired
//    SigLogoTipoService sigLogoTipoService;
//
//    @Override
//    @Autowired
//    public void setDataSource(DataSource dataSource) {
//        this.dataSource = dataSource;
//        this.jdbcTemplate = new JdbcTemplate(dataSource);
//    }
//
//    @Override
//    public ImagenPojo registrarImagePojo(ImagenPojo imagenPojo) throws Exception {
//        try {
//            SigLogoTipo sigLogoTipo = imagenPojo.getSigLogoTipo();
//            sigLogoTipo = sigLogoTipoService.guardar_modificar_listar_SigLogoTipo(sigLogoTipo, 1);
//
//            SigImagen sigImagen = imagenPojo.getSigImagen();
//            sigImagen.setIdLogoTipo(sigLogoTipo.getIdLogoTipo());
//            sigImagen = sigImagenService.guardar_modificar_listar_SigImagen(sigImagen, 1);
//
//            //obtener ImagenPojo
//            imagenPojo.setSigLogoTipo(sigLogoTipo);
//            imagenPojo.setSigImagen(sigImagen);
//
//            return imagenPojo;
//
//        } catch (Exception e) {
//            throw e;
//        }
//    }
//
//    @Override
//    public ImagenPojo obtenerImagePojoXSignoMarca(Long IdSignoMarca) throws Exception {
//        try {
//
//            ImagenPojo imagenPojo = new ImagenPojo();
//
//            SigLogoTipo sigLogoTipo = sigLogoTipoService.obtenerRegistroSigLogoTipoXIdSignoMarca(IdSignoMarca);
//
//            SigImagen sigImagen = sigImagenService.obtenerSigImagenXIdSigLogoTipo(sigLogoTipo.getIdLogoTipo());
//
//            imagenPojo.setSigImagen(sigImagen);
//            imagenPojo.setSigLogoTipo(sigLogoTipo);
//
//            return imagenPojo;
//
//        } catch (Exception e) {
//            throw e;
//        }
//    }
//
//    @Override
//    public ImagenPojo actualizarRegistroImagenPojoXSignoMarca(ImagenPojo imagenPojo) throws Exception {
//        try {
//            Long idLogTrans = 1L;
//            
//            //se debe actualizar a NA los registros activos
//            String SQL = "update siglogotipo set estado = 'NA' where idsignomarca = ? and estado = 'AC' ";
//            jdbcTemplate.update(SQL, imagenPojo.getSigLogoTipo().getIdSignoMarca());
//            
//            SigLogoTipo sigLogoTipo = imagenPojo.getSigLogoTipo();
//            sigLogoTipo = sigLogoTipoService.guardar_modificar_listar_SigLogoTipo(sigLogoTipo, 1);
//            
//            SigImagen sigImagen = imagenPojo.getSigImagen();
//            sigImagen.setIdLogoTipo(sigLogoTipo.getIdLogoTipo());
//            sigImagen.setIdLogTrans(idLogTrans);
//            sigImagen = sigImagenService.guardar_modificar_listar_SigImagen(sigImagen, 1);
//
//            //obtener ImagenPojo
//            imagenPojo.setSigLogoTipo(sigLogoTipo);
//            imagenPojo.setSigImagen(sigImagen);
//
//            return imagenPojo;
//            
//        } catch (Exception e) {
//            throw e;
//        }
//    }
//
//    @Override
//    public void eliminarRegistroImagenPojoXSignoMarca(Long idSignoMarca, Long idLogTrans) throws Exception {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//}
