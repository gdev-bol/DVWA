/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.bdimagen.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import snp.gob.bo.gimodel.bdimagen.entidad.SigArchivoDig;

/**
 *
 * @author levi
 */
public class SigArchivoDigMapper implements RowMapper<SigArchivoDig>{
    @Override
    public SigArchivoDig mapRow(ResultSet rs, int i) throws SQLException {
           SigArchivoDig sigArchivoDig = new SigArchivoDig();
          sigArchivoDig.setIdarchivodig(rs.getLong("idarchivodig"));
          sigArchivoDig.setIddocumento(rs.getLong("iddocumento"));
          sigArchivoDig.setArchivo(rs.getBytes("archivo"));
          
    
      return sigArchivoDig;
    
    }
}
