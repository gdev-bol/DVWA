/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.servicio.impl;

import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import snp.gob.bo.gimodel.entidad.Poder;
import snp.gob.bo.gimodel.mapper.PoderMapper;
import snp.gob.bo.gimodel.servicio.PoderService;

/**
 *
 * @author levi
 */
@Service("poderService")
public class PoderServiceImpl implements PoderService{
      private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    @Override
    @Autowired
    public void setDataSource(DataSource dataSource) {
         try {
            this.dataSource = dataSource;
            this.jdbcTemplate = new JdbcTemplate(dataSource);
        } catch (Exception e) {
            throw e;
        }
    }
    
     @Override
    public Poder crudPoder(Poder poderentrante, int parametro) throws Exception {
    
      String SQL = "select * from crud_libropoder("
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
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?"
                    +" );";
    
      Poder poder = (Poder) jdbcTemplate.queryForObject(SQL, new PoderMapper(),
                         
                   poderentrante.getIdpoder(),
                   poderentrante.getIdlogtrans(),
                   poderentrante.getTipo_poder(),
                   poderentrante.getTipo_tramite(),
                   poderentrante.getNro_exped(),
                   poderentrante.getGestion(),
                   poderentrante.getNro_testimonio(),
                   poderentrante.getFecha_testimonio(),
                   poderentrante.getNotario(),
                   poderentrante.getNom_confiere_poder(),
                   poderentrante.getDom_confiere_poder(),
                   poderentrante.getApoderado(),
                   poderentrante.getPoder_revocado(),
                   poderentrante.getObs(),
                   poderentrante.getEstado(),
                    parametro
              
      );
      
      
        // System.out.println("tam poder::"+poder.getApoderado());
      return poder;
    }
    @Override
    public List<Poder> listaPoderXFiltro(String tipoTramite,Long nroExped,Integer gestion) throws Exception {
        
        List<Poder> listPoder= new ArrayList<Poder>();
        String SQL = "select * from lista_poder_tipotrages(?,?,?);";//Modifica
        listPoder = (List<Poder>) jdbcTemplate.query(SQL, new PoderMapper(),                                             
                tipoTramite,
                nroExped,
                gestion
                );
      //   System.out.println("tam aqui::"+listPoder.size());
      return listPoder;
        
        
    }
     @Override
    public List<Poder> listaPoderBusqueda(String dato,int opcion) throws Exception {
        List<Poder> listPoder=null;
        String SQL = "select * from lista_poder_filtrosbusqueda(?,?);";//Modifica
        listPoder = (List<Poder>) jdbcTemplate.query(SQL, new PoderMapper(),                                             
                dato,
                opcion
                
                );
      //   System.out.println("tam aqui::"+listPoder.size());
      return listPoder;
        
    }
   
        
    
}
