/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import snp.gob.bo.gimodel.entidad.Etapa;

/**
 *
 * @author Ruben Ramirez
 * @version 1.0, 26/10/2016
 * @see Etapa
 */
public class EtapaMapper implements RowMapper<Etapa>{

    @Override
    public Etapa mapRow(ResultSet rs, int i) throws SQLException {
        Etapa etapa = new Etapa();
        etapa.setIdEtapa(rs.getLong("idetapa"));
        etapa.setIdLogTrans(rs.getLong("idLogTrans"));
        etapa.setTipoTramite(rs.getString("tipo_tramite"));
        etapa.setTipoEtapa(rs.getString("tipo_etapa"));
        etapa.setDescripcion(rs.getString("descripcion"));
        etapa.setFechaCreacion(rs.getDate("fecha_creacion"));
        etapa.setPlazo(rs.getInt("plazo"));
        etapa.setEstado(rs.getString("estado"));
        return etapa;
    }
    
}
