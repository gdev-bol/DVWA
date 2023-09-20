/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.servicio;

import java.util.List;
import javax.sql.DataSource;
import snp.gob.bo.gimodel.entidad.Tasa;

/**
 *
 * @author chano Rojas
 */
public interface TasaService {

  public void setDataSource(DataSource dataSource)throws Exception;
    
  Tasa crudTasa(Tasa tasa, int parametro) throws Exception;

  List<Tasa> obtenerListaTasaActivas() throws Exception;
  
  List<Tasa> obtenerListaTasas() throws Exception;
  
  List<Tasa> obtenerListaTasaActivasPorTipoTramite(String tipoTramite) throws Exception;
  
  Tasa obtenerTasaPorIdTasa(Long idTasa) throws Exception;
  
  void guardaGeneralTasa(Tasa tasa, Long IdUsuario) throws Exception;
  
  List<Tasa> obtenerListaTasaActivasPorUnidad(String unidad) throws Exception;
  
}
