/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import snp.gob.bo.gimodel.entidad.BusquedaSigRegistro;

/**
 *
 * @author susana
 */
public class BusquedaSigRegistroMapper  implements RowMapper{

    @Override
    public BusquedaSigRegistro mapRow(ResultSet rs, int i) throws SQLException {
       BusquedaSigRegistro busquedaSigRegistro = new BusquedaSigRegistro();
       busquedaSigRegistro.setPosicion(rs.getLong("posicion"));
       busquedaSigRegistro.setIdregistro(rs.getLong("idregistro"));
       busquedaSigRegistro.setFecha_registro(rs.getDate("fecha_registro"));
       busquedaSigRegistro.setNumero_registro(rs.getLong("numero_registro"));
       busquedaSigRegistro.setSerie(rs.getString("serie"));
       busquedaSigRegistro.setNumero_resolucion(rs.getLong("numero_resolucion"));
       busquedaSigRegistro.setSm_descripcion(rs.getString("sm_descripcion"));
       busquedaSigRegistro.setSigno(rs.getString("signo"));
       busquedaSigRegistro.setClase(rs.getInt("clase"));       
       return busquedaSigRegistro;
    }
    
}
