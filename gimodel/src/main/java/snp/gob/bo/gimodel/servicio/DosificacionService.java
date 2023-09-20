/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.servicio;

import java.util.List;
import javax.sql.DataSource;
import snp.gob.bo.gimodel.entidad.Dominio;
import snp.gob.bo.gimodel.entidad.Dosificacion;

/**
 *
 * @author ChanoRojas
 * @see Dosificacion
 * @see DosificacionService
 * @version 1.0, 21/10/2016
 */
public interface DosificacionService {

    /**
     * Método que permite setear la conexion con dataSource
     *
     * @param dataSource
     * @throws java.lang.Exception
     */
    public void setDataSource(DataSource dataSource) throws Exception;

    Dosificacion crudDosificacion(Dosificacion dosificacion, int parametro) throws Exception;

    Dosificacion encuentraDosificacionPorIdDosificacion(Long idDosificacion);
    
}
