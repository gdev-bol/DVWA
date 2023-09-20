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
import snp.gob.bo.gimodel.bdimagen.entidad.ModArchivoDig;
import snp.gob.bo.gimodel.bdimagen.mapper.ModArchivoDigMapper;
import snp.gob.bo.gimodel.bdimagen.servicio.ModArchivoDigService;

/**
 *
 * @author levi
 */
public class ModArchivoDigServiceImpl implements ModArchivoDigService{
    
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Override
    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
     @Override
      public ModArchivoDig crudModArchivoDig(ModArchivoDig modArchivoDigEntrante , int parametro) throws Exception{
      
      
      String SQL = "select * from crud_modarchivodig("
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?"
                    +" );";
    
      ModArchivoDig sigDocumento = (ModArchivoDig) jdbcTemplate.queryForObject(SQL, new ModArchivoDigMapper(),
              modArchivoDigEntrante.getIdarchivodig(),
              modArchivoDigEntrante.getIddocumento(),
              modArchivoDigEntrante.getArchivo(),
              parametro);
         System.out.println("LLEGA AQUI"+sigDocumento);
      if(sigDocumento == null)
      { return null;
      }    
      else{  return sigDocumento;
      }
            
      
      
      }
      @Override
    public List<ModArchivoDig> listaModArchivoXiddoc(Long iddocumento) throws Exception{
        
        String SQL = "select * from lista_modarchivodig_iddoc("
                    + "?"+
                    ");";
    
      List<ModArchivoDig> lisModDocumento = (List<ModArchivoDig>) jdbcTemplate.query(SQL, new ModArchivoDigMapper(),
              iddocumento);
      if (!lisModDocumento.isEmpty()) {
            
            return lisModDocumento;
        }
        return Collections.EMPTY_LIST;

      
    }
}
