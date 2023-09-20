/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.servicio.impl;

import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import snp.gob.bo.gimodel.entidad.BusquedaHistorialRenovacion;
import snp.gob.bo.gimodel.entidad.BusquedaModificacionResultado;
import snp.gob.bo.gimodel.mapper.BusquedaHistorialRenovacionMapper;
import snp.gob.bo.gimodel.mapper.BusquedaModificacionResultadoMapper;
import snp.gob.bo.gimodel.servicio.BusquedaHistorialRenovacionService;
import snp.gob.bo.gimodel.servicio.BusquedaModificacionResultadoService;
import snp.gob.bo.gimodel.servicio.RenRenovacionService;

/**
 *
 * @author susana
 */
@Service("busquedaHistorialRenovacionService")
public class BusquedaHistorialRenovacionServiceImpl implements BusquedaHistorialRenovacionService {

     @Autowired
    RenRenovacionService renRenovacionService;
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) throws Exception {
        try {
            this.dataSource = dataSource;
            this.jdbcTemplate = new JdbcTemplate(dataSource);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<BusquedaHistorialRenovacion> lista_HistorialRenovacion_sm_publicacion_registro_or(Long sm, Long publicacion, Long registro, String serie) {
        List<BusquedaHistorialRenovacion> listaHistorialRenovacion;
        try {
            String SQL = "select * from lista_historial_renovacion(?,?,?,? )";                        
            listaHistorialRenovacion = jdbcTemplate.query(SQL, new BusquedaHistorialRenovacionMapper(), registro, serie, sm, publicacion);
            if (!listaHistorialRenovacion.isEmpty()) {
                return listaHistorialRenovacion;
            }
            return new ArrayList<BusquedaHistorialRenovacion>();
        } catch (EmptyResultDataAccessException e) {
            //Como no se encontro ningun registro enviar la instancia de la nueva marca.
            return new ArrayList<BusquedaHistorialRenovacion>();
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public String ordenModificacionLiteral(Integer index, Integer tamaniolista) {
        Integer valor = index+((tamaniolista-1)-((index-1)*2)) ;            
        String literal= "";
        literal = renRenovacionService.numeroOrdinal(valor);      
        return literal;
    }
}
