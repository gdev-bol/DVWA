/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.bdimagen.servicio.impl;

import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import snp.gob.bo.gimodel.bdimagen.entidad.SigDocumento;
import snp.gob.bo.gimodel.bdimagen.mapper.SigDocumentoMapper;
import snp.gob.bo.gimodel.bdimagen.servicio.SigDocumentoService;

/**
 *
 * @author levi
 */
public class SigDocumentoServiceImpl implements SigDocumentoService{
     private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Override
    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    @Override
    public SigDocumento crudSigDocumento(SigDocumento sigDocumentoEntrante , int parametro) throws Exception {
    
      String SQL = "select * from crud_sigdocumento("
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?"
                    +" );";
    
      SigDocumento sigDocumento = (SigDocumento) jdbcTemplate.queryForObject(SQL, new SigDocumentoMapper(),
                         
                   sigDocumentoEntrante.getIddocumento(),
                   sigDocumentoEntrante.getIdsignomarca(),
                   sigDocumentoEntrante.getNombre_archivo(),
                   sigDocumentoEntrante.getDescripcion(),
                   sigDocumentoEntrante.getNro_folios(),
                   sigDocumentoEntrante.getTipo_documento(),
                   sigDocumentoEntrante.getExtension_archivo(),
                   sigDocumentoEntrante.getFecha_creacion(),
                   sigDocumentoEntrante.getMime(),
                   sigDocumentoEntrante.getEstado(),
                   parametro);
                   
    return sigDocumento;
    } 
    
    
    
     @Override
    public List<SigDocumento> listaXidSignoMarca(Long idsignomarca) throws Exception {
         List<SigDocumento> listDocu;
                     
      String SQL = "select * from lista_sigdocumento_idsignomarca("
                    + "?)";
      listDocu = (List<SigDocumento>) jdbcTemplate.query(SQL, new SigDocumentoMapper(),
                         
                   idsignomarca
                   );
         
      
      return listDocu;
    }
}
