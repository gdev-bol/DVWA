/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.bdimagen.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import snp.gob.bo.gimodel.bdimagen.entidad.SigDocumento;

/**
 *
 * @author levi
 */
public class SigDocumentoMapper implements RowMapper<SigDocumento>{
    @Override
    public SigDocumento mapRow(ResultSet rs, int i) throws SQLException {
          SigDocumento sigDocumento = new SigDocumento();
          sigDocumento.setIddocumento(rs.getLong("iddocumento"));
          sigDocumento.setIdsignomarca(rs.getLong("idsignomarca"));
          sigDocumento.setNombre_archivo(rs.getString("nombre_archivo"));
          sigDocumento.setDescripcion(rs.getString("descripcion"));
          sigDocumento.setNro_folios(rs.getInt("nro_folios"));
          sigDocumento.setTipo_documento(rs.getString("tipo_documento"));
          sigDocumento.setExtension_archivo(rs.getString("extension_archivo"));
          sigDocumento.setFecha_creacion(rs.getTimestamp("fecha_creacion"));
          sigDocumento.setMime(rs.getString("mime"));
          sigDocumento.setEstado(rs.getString("estado"));
          
          return sigDocumento;    
    }
  
             // notificacion.setIdnotificacion(rs.getLong("idnotificacion"));

}

