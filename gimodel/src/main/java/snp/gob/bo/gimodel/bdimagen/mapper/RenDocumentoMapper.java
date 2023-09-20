/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.bdimagen.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import snp.gob.bo.gimodel.bdimagen.entidad.RenDocumento;


/**
 *
 * @author levi
 */
public class RenDocumentoMapper implements RowMapper<RenDocumento>{
    @Override
    public RenDocumento mapRow(ResultSet rs, int i) throws SQLException {
          RenDocumento renDocumento = new RenDocumento();
          renDocumento.setIddocumento(rs.getLong("iddocumento"));
          renDocumento.setIdsolicitudrenovacion(rs.getLong("idsolicitudrenovacion"));
          renDocumento.setNombre_archivo(rs.getString("nombre_archivo"));
          renDocumento.setDescripcion(rs.getString("descripcion"));
          renDocumento.setNro_folios(rs.getInt("nro_folios"));
          renDocumento.setTipo_documento(rs.getString("tipo_documento"));
          renDocumento.setExtension_archivo(rs.getString("extension_archivo"));
          renDocumento.setFecha_creacion(rs.getTimestamp("fecha_creacion"));
          renDocumento.setMime(rs.getString("mime"));
          renDocumento.setEstado(rs.getString("estado"));
          
          return renDocumento;    
          
    }
}
