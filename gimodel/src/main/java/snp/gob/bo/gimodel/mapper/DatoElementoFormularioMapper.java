/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package snp.gob.bo.gimodel.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import snp.gob.bo.gimodel.entidad.DatoElementoFormulario;

/**
 *
 * @author Eddy Valero
 * @version 1.0, 03/09/2016
 */
public class DatoElementoFormularioMapper implements RowMapper<DatoElementoFormulario> {

    @Override
    public DatoElementoFormulario mapRow(ResultSet rs, int i) throws SQLException {
        
        DatoElementoFormulario  datoElementoFormulario = new DatoElementoFormulario();
        
        datoElementoFormulario.setIdDatoElementoFormulario(rs.getLong("iddatoelementoformulario"));
        datoElementoFormulario.setIdSeguimiento(rs.getLong("idseguimiento"));
        datoElementoFormulario.setIdLogTrans(rs.getLong("idlogtrans"));
        datoElementoFormulario.setNombreTabla(rs.getString("nombre_tabla"));
        datoElementoFormulario.setPestania(rs.getString("pestania"));
        datoElementoFormulario.setSeccion(rs.getInt("seccion"));
        datoElementoFormulario.setFila(rs.getInt("fila"));
        datoElementoFormulario.setTipoElemento(rs.getString("tipo_elemento"));
        datoElementoFormulario.setNombreElemento(rs.getString("nombre_elemento"));
        datoElementoFormulario.setOrden(rs.getInt("orden"));
        datoElementoFormulario.setOrdenLiteral(rs.getString("orden_literal"));
        datoElementoFormulario.setIdpadre(rs.getInt("idpadre"));
        datoElementoFormulario.setRespuesta(rs.getString("respuesta"));
        datoElementoFormulario.setOpcionRespuesta(rs.getString("opcion_respuesta"));
        datoElementoFormulario.setEstado(rs.getString("estado"));
        
        return datoElementoFormulario;
        
    }
    
    
    
}
