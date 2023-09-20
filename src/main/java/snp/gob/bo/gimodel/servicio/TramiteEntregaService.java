/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.servicio;

import javax.sql.DataSource;
import snp.gob.bo.gimodel.entidad.TramiteEntrega;



/**
 *
 * @author luisangel
 */
public interface TramiteEntregaService {
    
     /**
     * Método que permite setear la conexion con dataSource
     *
     * Creado: Luis Angel Quispe Limachi Fecha: 13/11/2017
     *
     * @param dataSource
     */
    public void setDataSource(DataSource dataSource);

    /**
     * Metodo para guardar, modificar, listar registros en la tabla
     * tramiteentrega
     *
     * Creado: Luis Angel Quispe Limachi Fecha: 13/11/2017
     *
     * @param tramiteentrega
     * @param operacion
     * @return
     */
    public TramiteEntrega guardar_modificar_listar_TramiteEntrega(TramiteEntrega tramiteentrega, Integer operacion);
}
