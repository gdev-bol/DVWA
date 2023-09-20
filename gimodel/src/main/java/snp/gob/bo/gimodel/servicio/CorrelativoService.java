/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.servicio;

import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import snp.gob.bo.gimodel.entidad.Correlativo;

/**
 *
 * @author Chano Rojas
 * @see ReciboService
 * @see ReciboService
 * @version 1.0, 05/06/2016
 */
public interface CorrelativoService {

    /**
     * Método que permite setear la conexion con dataSource
     *
     * @author Chano Rojas
     * @see ReciboService
     *
     * @version 1.0, 05/06/2016
     * @param dataSource
     * @throws java.lang.Exception
     */
    public void setDataSource(DataSource dataSource) throws Exception;

    /**
     * Método que permite setear la conexion con dataSource
     *
     * @author Chano Rojas
     * @see ReciboService
     * @param correlativo
     * @param parametro
     * @return
     * @throws java.lang.Exception
     */
    public Correlativo crudCorrelativo(Correlativo correlativo, int parametro) throws Exception;

    /**
     * Método que permite lista de la tabla correlativo
     *
     * @author Chano Rojas
     * @see ReciboService
     * @param correlativo
     * @param parametro
     * @return
     * @throws java.lang.Exception
     */
    List<Correlativo> listaCorrelativo(Correlativo correlativo, int parametro) throws Exception;

    /**
     * Método que permite obtener un correlativo por id regional y tipotramite
     *
     * @author Chano Rojas
     * @see ReciboService
     * @param idRegional
     * @param tipoTramite
     * @return
     *
     */
        Correlativo encuentraCorrelativoTabla(Long idRegional, String tipoTramite);

}
