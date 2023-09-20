/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.servicio;

import javax.sql.DataSource;
import snp.gob.bo.gimodel.entidad.Sticker;

/**
 *
 * @author Ruben Ramirez
 * @see StickerServiceImpl
 * @version 1.0, 05/12/2016
 */
public interface StickerService {

    /**
     * Método que permite setear la conexion con dataSource.
     *
     * @author Ruben Ramirez
     * @see StickerServiceImpl
     * @version 1.0, 05/12/2016
     * @param dataSource
     * @throws java.lang.Exception
     */
    public void setDataSource(DataSource dataSource) throws Exception;

    /**
     * Método que obtiene el sticker correspondiente al tipo  de tramite y la gestion.
     *
     * @author Ruben Ramirez
     * @see StickerServiceImpl
     * @version 1.0, 05/12/2016
     * @param tipoTramite
     * @param gestion
     * @return
     * @throws java.lang.Exception
     */
    public Sticker obtenerStickerXTipoTramiteYGestion(String tipoTramite, int gestion) throws Exception;
    
    /**
     * Método crud de la tabla Sticker
     *
     * Ruben Ramirez Fecha: 05/12/2016
     *
     *
     * @param sticker
     * @param parametro
     * @return 
     * @throws java.lang.Exception
     */
    public Sticker crudSticker(Sticker sticker, int parametro) throws Exception;

}
