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
import snp.gob.bo.gimodel.bdimagen.entidad.ModDocumento;
import snp.gob.bo.gimodel.bdimagen.mapper.ModDocumentoMapper;
import snp.gob.bo.gimodel.bdimagen.servicio.ModDocumentoService;

/**
 *
 * @author levi
 */
public class ModDocumentoServiceImpl implements ModDocumentoService{
     private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Override
    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    @Override
    public ModDocumento crudModDocumento(ModDocumento modDocumentoEntrante , int parametro) throws Exception{
    String SQL = "select * from crud_moddocumento("
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
    
      ModDocumento modDocumento = (ModDocumento) jdbcTemplate.queryForObject(SQL, new ModDocumentoMapper(),
                         
                   modDocumentoEntrante.getIddocumento(),
                   modDocumentoEntrante.getIdmodificacion(),
                   modDocumentoEntrante.getNombre_archivo(),
                   modDocumentoEntrante.getDescripcion(),
                   modDocumentoEntrante.getNro_folios(),
                   modDocumentoEntrante.getTipo_documento(),
                   modDocumentoEntrante.getExtension_archivo(),
                   modDocumentoEntrante.getFecha_creacion(),
                   modDocumentoEntrante.getMime(),
                   modDocumentoEntrante.getEstado(),
                   parametro);
                   
    return modDocumento;
    
    
    }
    @Override
    public List<ModDocumento> listaXidModificacion(Long idmodificacion) throws Exception {
         List<ModDocumento> listDocu;
      String SQL = "select * from lista_moddocumento_idmodificacion("
                    + "?)";
      listDocu = (List<ModDocumento>) jdbcTemplate.query(SQL, new ModDocumentoMapper(),
                         
                   idmodificacion
                   );
      
      return listDocu;
    }
}
