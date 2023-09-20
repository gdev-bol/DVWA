/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.bdimagen.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import snp.gob.bo.gimodel.bdimagen.entidad.ModArchivoDig;

/**
 *
 * @author levi
 */
public class ModArchivoDigMapper implements RowMapper<ModArchivoDig>{
    @Override
    public ModArchivoDig mapRow(ResultSet rs, int i) throws SQLException {
           ModArchivoDig modArchivoDig = new ModArchivoDig();
          modArchivoDig.setIdarchivodig(rs.getLong("idarchivodig"));
          modArchivoDig.setIddocumento(rs.getLong("iddocumento"));
          modArchivoDig.setArchivo(rs.getBytes("archivo"));
          
    
      return modArchivoDig;
    
    }
}
