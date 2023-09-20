/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package snp.gob.bo.gimodel.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import snp.gob.bo.gimodel.entidad.FormularioTramite;

/**
 *
 * @author Eddy Valero
 * @version 1.0, 03/09/2016
 */
public class FormularioTramiteMapper implements RowMapper<FormularioTramite> {

    @Override
    public FormularioTramite mapRow(ResultSet rs, int i) throws SQLException {
        FormularioTramite sMFormularioTramite = new FormularioTramite();
        
        sMFormularioTramite.setIdFormularioTramite(rs.getLong("idformulariotramite"));
        sMFormularioTramite.setIdLogTrans(rs.getLong("idlogtrans"));
        sMFormularioTramite.setCodigo(rs.getString("codigo"));
        sMFormularioTramite.setNombreFormulario(rs.getString("nombreformulario"));
        sMFormularioTramite.setFechaInicio(rs.getDate("fechainicio"));
        sMFormularioTramite.setFechaFin(rs.getDate("fechafin"));
        sMFormularioTramite.setEstado(rs.getString("estado"));
        
        return sMFormularioTramite;
        
    }
    
    
    
}
