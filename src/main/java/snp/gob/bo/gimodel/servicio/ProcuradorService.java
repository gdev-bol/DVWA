/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.servicio;

import javax.sql.DataSource;
import snp.gob.bo.gimodel.entidad.ModModificacion;
import snp.gob.bo.gimodel.entidad.Procurador;

/**
 *
 * @author luisangel
 */
public interface ProcuradorService {
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
     * procurador
     *
     * Creado: Luis Angel Quispe Limachi Fecha: 13/11/2017
     *
     * @param modModificacion
     * @param operacion
     * @return
     */
    public Procurador guardar_modificar_listar_Procurador(Procurador procurador, Integer operacion);
    /**
     * Metodo para guardar, modificar, listar registros en la tabla
     * procurador
     *
     * Creado: Ruben Ramirez Fecha: 13/11/2017
     *
     * @param modModificacion
     * @param operacion
     * @return
     */
    public Procurador procuradorPorId(Long id);
}
