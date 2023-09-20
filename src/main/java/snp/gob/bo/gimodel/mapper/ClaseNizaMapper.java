/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import snp.gob.bo.gimodel.entidad.ClaseNiza;

/**
 *
 * @author eddy
 */
public class ClaseNizaMapper implements RowMapper<ClaseNiza> {

    @Override
    public ClaseNiza mapRow(ResultSet rs, int i) throws SQLException {
        
        ClaseNiza claseNiza = new ClaseNiza();
        
        claseNiza.setIdClaseNiza(rs.getLong("idclaseniza"));
        claseNiza.setIdLogTrans(rs.getLong("idlogtrans"));
        claseNiza.setNumeroClaseNiza(rs.getLong("numero_clase_niza"));
        claseNiza.setProteccion(rs.getString("proteccion"));
        claseNiza.setFechaInicio(rs.getTimestamp("fecha_inicio"));
        claseNiza.setFechaFin(rs.getTimestamp("fecha_fin"));
        claseNiza.setTipoNiza(rs.getString("tipo_niza"));
        claseNiza.setNotaTipoClaseNiza(rs.getString("nota_tipo_claseniza"));
        claseNiza.setNumeroEdicion(rs.getString("numero_edicion"));
        claseNiza.setVersion(rs.getString("version"));
        claseNiza.setEstado(rs.getString("estado"));

        return claseNiza;
    }

}
