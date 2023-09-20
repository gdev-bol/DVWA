/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import snp.gob.bo.gimodel.entidad.Documento;

/**
 *
 * @author susana
 */
public class DocumentoMapper implements RowMapper<Documento>{

    @Override
    public Documento mapRow(ResultSet rs, int i) throws SQLException {
        Documento documento = new Documento();
        documento.setIddocumento(rs.getLong("iddocumento"));
        documento.setIdarea(rs.getLong("idarea"));
        documento.setIdtramite(rs.getLong("idtramite"));
        documento.setIdlogtrans(rs.getLong("idlogtrans"));
        documento.setNombre_archivo(rs.getString("nombre_archivo"));
        documento.setDescripcion(rs.getString("descripcion"));
        documento.setNro_folios(rs.getString("nro_folios"));
        documento.setFecha_creacion(rs.getTimestamp("fecha_creacion"));
        documento.setTipo_documentacion(rs.getString("tipo_documentacion"));
        documento.setTipo_archivo(rs.getString("tipo_archivo"));
        documento.setImagen(rs.getBytes("imagen"));
        documento.setEstado(rs.getString("estado"));
    
        return documento;
    }
    
}
