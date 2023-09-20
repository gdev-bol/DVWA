/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import snp.gob.bo.gimodel.entidad.ObjetoEliminadoGenerico;

/**
 * 
 * 
 * @author Eddy Valero
 * @version 1.0, 29/11/2016
 */
public class ObjetoEliminadoGenericoMapper implements RowMapper<ObjetoEliminadoGenerico> {

    @Override
    public ObjetoEliminadoGenerico mapRow(ResultSet rs, int i) throws SQLException {

        ObjetoEliminadoGenerico objetoEliminadoGenerico = new ObjetoEliminadoGenerico();

        objetoEliminadoGenerico.setId(rs.getLong("id"));
        objetoEliminadoGenerico.setObjetoEliminado(rs.getString("objeto_eliminado"));

        return objetoEliminadoGenerico;
    }

}
