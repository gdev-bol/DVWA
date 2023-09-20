/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.servicio.impl;

import java.util.Collections;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import snp.gob.bo.gimodel.entidad.Sistema;
import snp.gob.bo.gimodel.mapper.SigSignoMarcaMapper;
import snp.gob.bo.gimodel.mapper.SistemaMapper;
import snp.gob.bo.gimodel.servicio.SistemaService;

/**
 *
 * @author levi
 */
@Service("sistemaService")
public class SistemaServiceImpl  implements SistemaService{
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
    public Sistema guardar_modificar_listar_Sistema(Sistema sistema, Integer operacion) {
         Sistema sistemaNuevo = new Sistema();
        String SQL = "select * from crud_sistema(?,?,?,?,?);";
        sistemaNuevo = (Sistema) jdbcTemplate.queryForObject(SQL, new SistemaMapper(),
                sistema.getIdsistema(),
                sistema.getIdlogtrans(),
                sistema.getDescripcion(),
                sistema.getEstado(),
                operacion
                );        
        return sistemaNuevo;
        
    }
    
    
    
    
    @Override
    public List<Sistema> listaSistemas(){
        String SQL="select * from sistema where estado='AC' order by 1 asc";
                
     
        
        
        List<Sistema> listaSistema = jdbcTemplate.query(SQL, new SistemaMapper());
        if (!listaSistema.isEmpty()) {
           /* for(int i=0;i<=listaNotiBus.size()-1;i++)
            { System.out.println("En consulta historial"+listaNotiBus.get(i).getDemandante());
            
            }*/
            return listaSistema;
        }
        return Collections.EMPTY_LIST;
    
    
    }
    
    
    @Override
    public void eliminaMenusPaginas(Long idsistema)
    {
    
      String SQL = "select * from elimina_menupagina_idsistema("+idsistema+");";
             jdbcTemplate.execute(SQL           
                );     
    
                         
             
    }
    
    
   
}
