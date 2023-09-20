/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package snp.gob.bo.gimodel.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import snp.gob.bo.gimodel.entidad.ElementoFormularioTramite;

/**
 *
 * @author Eddy Valero
 * @version 1.0, 03/09/2016
 */
public class ElementoFormularioTramiteMapper implements RowMapper<ElementoFormularioTramite> {

    @Override
    public ElementoFormularioTramite mapRow(ResultSet rs, int i) throws SQLException {
        
        ElementoFormularioTramite elementoFormularioTramite = new ElementoFormularioTramite();
        
        elementoFormularioTramite.setIdElementoFormularioTramite(rs.getLong("idelementoformulariotramite"));
        elementoFormularioTramite.setIdFormularioTramite(rs.getLong("idformulariotramite"));
        elementoFormularioTramite.setIdLogTrans(rs.getLong("idlogtrans"));
        elementoFormularioTramite.setPestania(rs.getString("pestania"));
        elementoFormularioTramite.setSeccion(rs.getInt("seccion"));
        elementoFormularioTramite.setFila(rs.getInt("fila"));
        elementoFormularioTramite.setTipoElemento(rs.getString("tipo_elemento"));
        elementoFormularioTramite.setNombreElemento(rs.getString("nombre_elemento"));
        elementoFormularioTramite.setOrden(rs.getInt("orden"));
        elementoFormularioTramite.setOrdenLiteral(rs.getString("orden_literal"));
        elementoFormularioTramite.setIdpadre(rs.getInt("idpadre"));
        elementoFormularioTramite.setFechaInicio(rs.getTimestamp("fechainicio"));
        elementoFormularioTramite.setFechaFin(rs.getTimestamp("fechafin"));
        elementoFormularioTramite.setRespuesta(rs.getString("respuesta"));
        elementoFormularioTramite.setOpcionRespuesta(rs.getString("opcion_respuesta"));
        elementoFormularioTramite.setEstado(rs.getString("estado"));
        
        return elementoFormularioTramite;
        
    }
    
    
    
}
