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
import snp.gob.bo.gimodel.bdimagen.entidad.RenDocumento;
import snp.gob.bo.gimodel.bdimagen.mapper.RenDocumentoMapper;
import snp.gob.bo.gimodel.bdimagen.servicio.RenDocumentoService;

/**
 *
 * @author levi
 */
public class RenDocumentoServiceImpl implements RenDocumentoService{
     private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Override
    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
        @Override
    public RenDocumento crudRenDocumento(RenDocumento renDocumentoEntrante , int parametro) throws Exception
    {
          String SQL = "select * from crud_rendocumento("
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
        System.out.println("En servicio renDocumentoEntrante.getIdsolicitudrenovacion()::"+renDocumentoEntrante.getIdsolicitudrenovacion());
      RenDocumento modDocumento = (RenDocumento) jdbcTemplate.queryForObject(SQL, new RenDocumentoMapper(),
                         
                   renDocumentoEntrante.getIddocumento(),
                    
                   renDocumentoEntrante.getIdsolicitudrenovacion(),
                   renDocumentoEntrante.getNombre_archivo(),
                   renDocumentoEntrante.getDescripcion(),
                   renDocumentoEntrante.getNro_folios(),
                   renDocumentoEntrante.getTipo_documento(),
                   renDocumentoEntrante.getExtension_archivo(),
                   renDocumentoEntrante.getFecha_creacion(),
                   renDocumentoEntrante.getMime(),
                   renDocumentoEntrante.getEstado(),
                   parametro);
                   System.out.println("modDocumento::"+modDocumento.getIdsolicitudrenovacion());
         return modDocumento; 
    
    }
        @Override
    public List<RenDocumento> listaXidsolicitudrenovacion(Long idsolicitudrenovacion) throws Exception{
    
    List<RenDocumento> listDocu;
      String SQL = "select * from lista_rendocumento_idmodificacion("
                    + "?)";
      listDocu = (List<RenDocumento>) jdbcTemplate.query(SQL, new RenDocumentoMapper(),
                         
                   idsolicitudrenovacion
                   );
      
      return listDocu;
    }
}
