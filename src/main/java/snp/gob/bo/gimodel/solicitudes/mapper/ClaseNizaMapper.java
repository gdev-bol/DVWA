/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package snp.gob.bo.gimodel.solicitudes.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import snp.gob.bo.gimodel.solicitudes.entidades.ClasesNiza;

/**
 *
 * @author Eddy Valero
 * @version 1.0, 23/08/2016
 */
public class ClaseNizaMapper implements RowMapper<ClasesNiza> {

    @Override
    public ClasesNiza mapRow(ResultSet rs, int i) throws SQLException {
        
        ClasesNiza clasesNiza = new ClasesNiza();
        
        clasesNiza.setId(rs.getLong("id"));
        clasesNiza.setNumeroClaseNiza(rs.getString("numero_clase_niza"));
        clasesNiza.setProteccion(rs.getString("proteccion"));
        clasesNiza.setFechaInicio(rs.getTimestamp("fecha_inicio"));
        clasesNiza.setFechaFin(rs.getTimestamp("fecha_fin"));
        clasesNiza.setNotaTipoClaseNiza(rs.getString("nota_tipo_claseniza"));
        clasesNiza.setNumeroEdicion(rs.getString("numero_edicion"));
        clasesNiza.setVersion(rs.getString("version"));
        
        return clasesNiza;
    }
    
}
