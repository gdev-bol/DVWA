//package snp.gob.bo.gimodel.servicio;
//
//import javax.sql.DataSource;
//import snp.gob.bo.gimodel.entidad.ImagenPojo;
//import snp.gob.bo.gimodel.entidad.SigDireccionDeNotificacion;
//
///**
// *
// * @author Eddy Valero
// * @version 1.0, 13/10/2016
// * @see ModSolicitanteApoderado
// */
//public interface ImagenPojoService {
//
//    /**
//     * Método que setea el dataSource
//     *
//     * Creado: Eddy Valero Fecha: 13/10/2016
//     *
//     * @param dataSource, fuente de datos
//     */
//    public void setDataSource(DataSource dataSource);
//
//    /**
//     * Método que realiza el registro de una dirección de notificación
//     *
//     * Creado: Eddy Valero Fecha: 13/10/2016
//     *
//     * @param imagenPojo
//     * @return SigDireccionNotificacion
//     * @throws java.lang.Exception
//     *
//     */
//    public ImagenPojo registrarImagePojo(ImagenPojo imagenPojo) throws Exception;
//
//    /**
//     * Obtener un registro de imagenpojo por idSignoMarca
//     *
//     * Creado: Eddy Valero Fecha: 13/10/2016
//     *
//     * @param IdSignoMarca
//     * @return SigDireccionNotificacion
//     * @throws java.lang.Exception
//     */
//    public ImagenPojo obtenerImagePojoXSignoMarca(Long IdSignoMarca) throws Exception;
//
//    /**
//     * Actualizar un registro de imagenpojo por idSignoMarca
//     *
//     * Creado: Eddy Valero Fecha: 14/10/2016
//     *
//     * @param imagenPojo
//     * @return SigDireccionNotificacion
//     * @throws java.lang.Exception
//     */
//    public ImagenPojo actualizarRegistroImagenPojoXSignoMarca(ImagenPojo imagenPojo) throws Exception;
//    
//    /**
//     * Eliminar un registro de marca
//     *
//     * Creado: Eddy Valero Fecha: 24/11/2016
//     *
//     * @param idSignoMarca
//     * @param idLogTrans
//     * @throws java.lang.Exception
//     */
//    public void eliminarRegistroImagenPojoXSignoMarca(Long idSignoMarca, Long idLogTrans) throws Exception;
//
//}
