/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.bdimagen.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import snp.gob.bo.gimodel.bdimagen.entidad.RenArchivoDig;

/**
 *
 * @author levi
 */
public class RenArchivoDigMapper implements RowMapper<RenArchivoDig>{
    @Override
    public RenArchivoDig mapRow(ResultSet rs, int i) throws SQLException {
           RenArchivoDig renArchivoDig = new RenArchivoDig();
          renArchivoDig.setIdarchivodig(rs.getLong("idarchivodig"));
          renArchivoDig.setIddocumento(rs.getLong("iddocumento"));
          renArchivoDig.setArchivo(rs.getBytes("archivo"));
          
    
      return renArchivoDig;
    
    }
}
