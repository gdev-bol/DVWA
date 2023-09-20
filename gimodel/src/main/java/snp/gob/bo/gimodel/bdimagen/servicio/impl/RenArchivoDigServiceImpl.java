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
import snp.gob.bo.gimodel.bdimagen.entidad.RenArchivoDig;
import snp.gob.bo.gimodel.bdimagen.mapper.RenArchivoDigMapper;
import snp.gob.bo.gimodel.bdimagen.servicio.RenArchivoDigService;

/**
 *
 * @author levi
 */
public class RenArchivoDigServiceImpl implements RenArchivoDigService{
    
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Override
    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    @Override
    public RenArchivoDig crudRenArchivoDig(RenArchivoDig renArchivoDigEntrante , int parametro) throws Exception{ 
    String SQL = "select * from crud_renarchivodig("
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?"
                    +" );";
    
      RenArchivoDig renDocumento = (RenArchivoDig) jdbcTemplate.queryForObject(SQL, new RenArchivoDigMapper(),
              renArchivoDigEntrante.getIdarchivodig(),
              renArchivoDigEntrante.getIddocumento(),
              renArchivoDigEntrante.getArchivo(),
              parametro);
         //System.out.println("LLEGA AQUI"+sigDocumento);
      if(renDocumento == null)
      { return null;
      }    
      else{  return renDocumento;
      }
            
    
    }
    
    @Override
    public List<RenArchivoDig> listaRenArchivoXiddoc(Long iddocumento) throws Exception{
    
    String SQL = "select * from lista_renarchivodig_iddoc("
                    + "?"+
                    ");";
    
      List<RenArchivoDig> lisRenDocumento = (List<RenArchivoDig>) jdbcTemplate.query(SQL, new RenArchivoDigMapper(),
              iddocumento);
      if (!lisRenDocumento.isEmpty()) {
            
            return lisRenDocumento;
        }
        return Collections.EMPTY_LIST;
    
    }
    
    
}
