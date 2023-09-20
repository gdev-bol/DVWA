/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package snp.gob.bo.gimodel.servicio;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import snp.gob.bo.gimodel.entidad.DosificacionTasa;
import snp.gob.bo.gimodel.entidad.Regional;
import snp.gob.bo.gimodel.entidad.Tasa;

/**
 *
 * @author ChanoRojas
 * @see DominioService
 * @version 1.0, 24/10/2016
 */

public interface DosificacionTasaService {
    
    /**
     * Método que permite setear la conexion con dataSource
     *
     * @param dataSource
     * @throws java.lang.Exception
     */
    public void setDataSource(DataSource dataSource) throws Exception;

    public List<DosificacionTasa> encuentraListaDosificacionTasaPorTasa(Tasa tasa, Regional regional);
    
    DosificacionTasa encuentraDosificacionTasaPorTasa(Tasa tasa, Regional regional);
    
    List<DosificacionTasa> listaDosificacionTasa();
    

    
}
