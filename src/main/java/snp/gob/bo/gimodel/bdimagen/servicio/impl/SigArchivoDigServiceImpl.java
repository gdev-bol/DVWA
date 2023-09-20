/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.bdimagen.servicio.impl;

import java.util.Collections;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import snp.gob.bo.gimodel.bdimagen.entidad.SigArchivoDig;
import snp.gob.bo.gimodel.bdimagen.mapper.SigArchivoDigMapper;
import snp.gob.bo.gimodel.bdimagen.servicio.SigArchivoDigService;

/**
 *
 * @author levi
 */
public class SigArchivoDigServiceImpl implements SigArchivoDigService{
     private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Override
    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
     @Override
    public SigArchivoDig crudSigArchivoDig(SigArchivoDig sigArchivoDigEntrante , int parametro) throws Exception{
    
      String SQL = "select * from crud_sigarchivodig("
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?"
                    +" );";
    
      SigArchivoDig sigDocumento = (SigArchivoDig) jdbcTemplate.queryForObject(SQL, new SigArchivoDigMapper(),
              sigArchivoDigEntrante.getIdarchivodig(),
              sigArchivoDigEntrante.getIddocumento(),
              sigArchivoDigEntrante.getArchivo(),
              parametro);
              return sigDocumento;
              
              
    }
     @Override
    public List<SigArchivoDig> listaSigArchivoXiddoc(Long iddocumento) throws Exception{
        
        String SQL = "select * from lista_sigarchivodig_iddoc("
                    + "?"+
                    ");";
    
      List<SigArchivoDig> lisSigDocumento = (List<SigArchivoDig>) jdbcTemplate.query(SQL, new SigArchivoDigMapper(),
              iddocumento);
      if (!lisSigDocumento.isEmpty()) {
            
            return lisSigDocumento;
        }
        return Collections.EMPTY_LIST;

      
    }
}
