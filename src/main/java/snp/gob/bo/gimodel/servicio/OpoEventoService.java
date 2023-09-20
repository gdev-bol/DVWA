/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.servicio;

import java.util.List;
import javax.sql.DataSource;
import snp.gob.bo.gimodel.entidad.OpoEvento;

/**
 *
 * @author Luis Angel Quispe Limachi
 */
public interface OpoEventoService {

    /**
     * Método que permite setear la conexion con dataSource
     *
     * @param dataSource
     * @throws java.lang.Exception
     */
    public void setDataSource(DataSource dataSource) throws Exception;

    /**
     * Metodo para Guardar el OpoEvento en la base de datos creado: Luis Quispe
     * Fecha: 05/08/2016
     *
     * @param opoevento
     * @return OpoEvento
     * @throws java.lang.Exception
     */
    public OpoEvento guardaOpoEvento(OpoEvento opoevento) throws Exception;

    /**
     * Metodo para extraer el maximo evento de la oposicion creado: Luis Quispe
     * Fecha: 10/08/2016
     *
     * @param idoposi
     * @return Long
     * @throws java.lang.Exception
     */
    public Long extraeridenento(Long idoposi) throws Exception;

    /**
     * Metodo para Obtener el objeto OpoEvento de por idpublicacion creado: Luis
     * Quispe Quispe Fecha: 05/08/2016
     *
     * @param idpublica
     * @return OpoEvento
     * @throws java.lang.Exception
     */
public OpoEvento obtenereventoxidpublica(Long idpublica) throws Exception;

    /**
     * Metodo para obtener la lista de eventos por idoposicion y que su estado
     * este activo Creado: Luis Quispe Fecha: 12/09/2016
     *
     * @param nroopo
     * @return List<OpoEvento>
     * @throws java.lang.Exception
     */
    public List<OpoEvento> obtenerListadoeventoxidoposicion(Long nroopo) throws Exception;

    /**
     * Metodo para Obtener el objeto Modificado deacuerdo a que campo se
     * modifique creado: Luis Quispe Fecha: 05/08/2016
     *
     * @param opoevento
     * @return OpoEvento
     * @throws java.lang.Exception
     */
    public OpoEvento modificarOpoEvento(OpoEvento opoevento) throws Exception;

    /**
     * Metodo para eliminar evento de la oposicion creado: Luis Quispe Fecha: 20/08/2016
     *
     * @param opoevento
     * @throws java.lang.Exception
     */
    public void eliminarOpoEvento(Long opoevento) throws Exception;
    
    /**
     * Metodo para encontrar el  maximo de un evento
     * 
     * Creado: Luis Quispe Fecha: 20/08/2016
     *
     * @param opoevento
     * @return Long
     * @throws java.lang.Exception
     */
    public Long encuentramaximoOpoEventoxnroopo(Long opoevento) throws Exception;
    
    
    
    

}
