/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package snp.gob.bo.gimodel.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import snp.gob.bo.gimodel.entidad.Dominio;

/**
 *
 * @author Eddy Valero
 * @version 1.0, 23/08/2016
 */
public class DominioMapper implements RowMapper<Dominio> {

    @Override
    public Dominio mapRow(ResultSet rs, int i) throws SQLException {
        
        Dominio sMDominio = new Dominio();
        
        sMDominio.setIddominio(rs.getLong("iddominio"));
        sMDominio.setIdlogtrans(rs.getLong("idlogtrans"));
        sMDominio.setDominio(rs.getString("dominio"));
        sMDominio.setCodigo(rs.getString("codigo"));
        sMDominio.setDominioPadre(rs.getString("dominiopadre"));
        sMDominio.setNombre(rs.getString("nombre"));
        sMDominio.setDescripcion(rs.getString("descripcion"));
        sMDominio.setOrden(rs.getInt("orden"));
        sMDominio.setFechaInicio(rs.getDate("fechainicio"));
        sMDominio.setFechaFin(rs.getDate("fechafin"));
        sMDominio.setEstado(rs.getString("estado"));
        
        
        return sMDominio;
        
    }
    
    
    
}
