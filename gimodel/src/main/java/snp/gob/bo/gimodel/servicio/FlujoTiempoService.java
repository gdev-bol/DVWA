/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.servicio;

import javax.sql.DataSource;

/**
 *
 * @author levi
 */
public interface FlujoTiempoService {
      public void setDataSource(DataSource dataSource);
      
       /**
     * metodo que devuelve el tiempó que deberia tardar una etapa , si por ej: examen de forma puede tardar 14 o 5 dias dependiendo el orden que se le mandde
     *
     * Creado: Levi Laura Fecha: 25/07/2017
     * 
     * @param etapa
     * @param orden
     * @return
     * @throws java.lang.Exception
     */
      public Integer obtieneFlujoXIdetapa(String etapa, Integer orden ) throws Exception;
}
