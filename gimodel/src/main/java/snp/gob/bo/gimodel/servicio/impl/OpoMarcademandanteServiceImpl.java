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
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import snp.gob.bo.gimodel.entidad.OpoMarcademandante;
import snp.gob.bo.gimodel.mapper.OpoMarcademandanteMapper;
import snp.gob.bo.gimodel.servicio.OpoMarcademandanteService;

/**
 *
 * @author Luis Angel Quispe Limachi
 */
@Service("opoMarcaDemandanteService")
public class OpoMarcademandanteServiceImpl implements OpoMarcademandanteService{

    
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    
    @Override
     @Autowired
    public void setDataSource(DataSource dataSource) {
    try {
            this.dataSource = dataSource;
            this.jdbcTemplate = new JdbcTemplate(dataSource);
        } catch (Exception e) {
            throw e;
        } }

    @Override
    public OpoMarcademandante guardaOpodemandante(OpoMarcademandante opodemandantedato) {
        
        String SQL="select * from inserta_datosnotifi(?);";
        OpoMarcademandante opoDemandante1=(OpoMarcademandante) jdbcTemplate.queryForObject(SQL, new OpoMarcademandanteMapper(),opodemandantedato.getDmte_email());
       return opoDemandante1;
        
    }
@Override
    public OpoMarcademandante guardardemandante(OpoMarcademandante datosdemandante) {
    
        String SQL1="select * from inserta_marcademandante(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
        OpoMarcademandante opomarcadema=(OpoMarcademandante) jdbcTemplate.queryForObject(SQL1, new OpoMarcademandanteMapper(),
                datosdemandante.getIdoposicion(),
                datosdemandante.getIdtramite(),
                datosdemandante.getIdpatente(),
                datosdemandante.getIdarea(),
                datosdemandante.getIdmarca(),
                datosdemandante.getIdlogtrans(),
                datosdemandante.getOrden_opo(),
                datosdemandante.getDmte_reg(),
                datosdemandante.getDmte_serie(),
                datosdemandante.getDmte_public(),
                datosdemandante.getDmte_sm(),
                datosdemandante.getDmte_sp(),
                datosdemandante.getDmte_marca_lnv(),
                datosdemandante.getDmte_uso(),
                datosdemandante.getDmte_clase(),
                datosdemandante.getEstado()
                );
        return opomarcadema;
        
      }
    
    
    @Override
    public void guardalista(List<OpoMarcademandante> listadodema) {
       
        for (int i = 0; i <=listadodema.size()-1; i++) {

                    guardardemandante(listadodema.get(i));
        }
        
        }

    @Override
    public OpoMarcademandante modificardemandante(OpoMarcademandante datosdemandantemodi) {
        
        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<OpoMarcademandante> obtenerListadoOpomarcademandante(Long didoposicion) throws Exception {
   
           try {
            String SQL = "select * from lista_opomarcademandante_idoposicion(?);";
            List<OpoMarcademandante> listaOpoMarcademandante = jdbcTemplate.query(SQL, new OpoMarcademandanteMapper(), didoposicion);
            if (!listaOpoMarcademandante.isEmpty()) {
                return listaOpoMarcademandante;
            }
            return Collections.EMPTY_LIST;
        } catch (Exception e) {
            throw e;
        }
        
        
        
        
        
    }

    @Override
    public List<OpoMarcademandante> obtenerListadoOpomarcademandantexnroopo(Long dnroopo) throws Exception {
    
            try {
            String SQL = "select * from lista_opomarcademandante_nropprue(?);";
            List<OpoMarcademandante> listaOpoMarcademandante = jdbcTemplate.query(SQL, new OpoMarcademandanteMapper(), dnroopo);
            if (!listaOpoMarcademandante.isEmpty()) {
                return listaOpoMarcademandante;
            }
            return Collections.EMPTY_LIST;
        } catch (Exception e) {
            throw e;
        }
        
     
    }

    @Override
    public OpoMarcademandante obtieneelobjetodmtexidoposicion(Long didopo) throws Exception {
         try {
            String SQL = "select * from lista_opomarcademandante_idoposicion(?);";
            OpoMarcademandante objopoMarcademandante = (OpoMarcademandante) jdbcTemplate.queryForObject(SQL, new OpoMarcademandanteMapper(), didopo);
           
                return objopoMarcademandante;         
            
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Long obtenerOpomarcademandnteXidopo(Long idoposicionx) throws Exception {
     
   String SQL = "select idmarcademandante from opomarcademandante where idoposicion=?;";
           return jdbcTemplate.queryForObject(SQL, Long.class, idoposicionx);     
        
    }

    @Override
    public OpoMarcademandante modificarOpomarcademandnte(OpoMarcademandante opodemandantemodi) throws Exception {
       try {
            String SQL = "select * from modifica_marcademandante(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
            OpoMarcademandante modiobjeto1 = (OpoMarcademandante) jdbcTemplate.queryForObject(SQL, new OpoMarcademandanteMapper(),
                   opodemandantemodi.getIdmarcademandante(),
                   opodemandantemodi.getIdoposicion(),
                   opodemandantemodi.getIdtramite(),
                   opodemandantemodi.getIdpatente(),
                   opodemandantemodi.getIdarea(),
                   opodemandantemodi.getIdmarca(),
                   opodemandantemodi.getIdlogtrans(),
                   opodemandantemodi.getOrden_opo(),
                   opodemandantemodi.getDmte_reg(),
                   opodemandantemodi.getDmte_serie(),
                   opodemandantemodi.getDmte_public(),
                   opodemandantemodi.getDmte_sm(),
                   opodemandantemodi.getDmte_sp(),
                   opodemandantemodi.getDmte_marca_lnv(),
                   opodemandantemodi.getDmte_uso(),
                   opodemandantemodi.getDmte_clase(),
                   opodemandantemodi.getEstado());
            return modiobjeto1;

            
            
        } catch (Exception e) {
            throw e;
        }
    
    }

    @Override
    public String eliminarOpomarcademandante(Long idmarcadmte, Long idoposicion) throws Exception {
  
        String SQL = "select * from elimina_opomarcademandante(?,?);";
        return jdbcTemplate.queryForObject(SQL, String.class, idmarcadmte, idoposicion);       
        
    }

    @Override
    public OpoMarcademandante obtieneobjetoxmarcadmte(Long idoposicion) throws Exception {
   
     try {
            String SQL = "select * from opomarcademandante where idoposicion=?;";
            OpoMarcademandante opoDemandante1=(OpoMarcademandante) jdbcTemplate.queryForObject(SQL, new OpoMarcademandanteMapper(),idoposicion);
       return opoDemandante1;
            
            
        } catch (Exception e) {
            throw e;
        }
    
    
    
    
    
    
    
    
    
    
    
    }
    
    
    

 
    
}
