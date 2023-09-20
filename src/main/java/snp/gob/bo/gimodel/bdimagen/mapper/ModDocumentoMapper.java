/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.bdimagen.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import snp.gob.bo.gimodel.bdimagen.entidad.ModDocumento;

/**
 *
 * @author levi
 */
public class ModDocumentoMapper implements RowMapper<ModDocumento>{
    @Override
    public ModDocumento mapRow(ResultSet rs, int i) throws SQLException {
           ModDocumento modDocumento = new ModDocumento();
          modDocumento.setIddocumento(rs.getLong("iddocumento"));
          modDocumento.setIdmodificacion(rs.getLong("idmodificacion"));
          modDocumento.setNombre_archivo(rs.getString("nombre_archivo"));
          modDocumento.setDescripcion(rs.getString("descripcion"));
          modDocumento.setNro_folios(rs.getInt("nro_folios"));
          modDocumento.setTipo_documento(rs.getString("tipo_documento"));
          modDocumento.setExtension_archivo(rs.getString("extension_archivo"));
          modDocumento.setFecha_creacion(rs.getTimestamp("fecha_creacion"));
          modDocumento.setMime(rs.getString("mime"));
           modDocumento.setEstado(rs.getString("estado"));
          
          return modDocumento;    
          
    }
}
