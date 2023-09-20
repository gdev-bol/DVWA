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
import snp.gob.bo.gimodel.entidad.OpoHistorial;
import snp.gob.bo.gimodel.mapper.OpoHistorialMapper;
import snp.gob.bo.gimodel.servicio.OpoHistorialService;

/**
 *
 * @author Luis Angel Quispe Limachi
 */
@Service("opoHistorialService")
public class OpoHistorialServiceImpl implements OpoHistorialService {

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
        }
    }

    @Override
    public OpoHistorial guardaOpoHistorial(OpoHistorial opohistorial) throws Exception {
        
         String SQL = "select * from inserta_opohistorial(?,?,?,?,?,?,?,?,?,?);";
                
                OpoHistorial objeven=jdbcTemplate.queryForObject(SQL, new OpoHistorialMapper(),
                       opohistorial.getIdoposicion(),
                       opohistorial.getIdlogtrans(),
                       opohistorial.getEstado(),
                       opohistorial.getFecha_lim(),
                       opohistorial.getObservacion(),
                       opohistorial.getUbicacion(),
                       opohistorial.getApoderado(),
                       opohistorial.getOperacion(),
                       opohistorial.getFecha_operacion(),
                       opohistorial.getId_usuario()
                );
       return objeven;
        
    }

    @Override
    public List<OpoHistorial> listarhistorialxnroopo(Long nroopo) throws Exception {
        
   try {
            String SQL = "select * from opohistorial where idoposicion=? order by fecha_operacion desc;";
            List<OpoHistorial> listaOposicion = jdbcTemplate.query(SQL, new OpoHistorialMapper(), nroopo);
            if (!listaOposicion.isEmpty()) {
                return listaOposicion;
            }
            return Collections.EMPTY_LIST;
        } catch (Exception e) {
            throw e;
        }        
        
        
    }
    @Override
    public String mostrarusuarioxid(Long idestudiante) throws Exception {
       
         try {
            String cadnombre="";
            String SQL = "select nombre from usuario where idusuario=?";
            String SQL1 = "select primer_apellido from usuario where idusuario=?";
            String SQL2 = "select segundo_apellido from usuario where idusuario=?";
            
            cadnombre=jdbcTemplate.queryForObject(SQL, String.class, idestudiante)+" "+jdbcTemplate.queryForObject(SQL1, String.class, idestudiante)+" "+jdbcTemplate.queryForObject(SQL2, String.class, idestudiante);
            
            return cadnombre;

        } catch (Exception e) {
            throw e;
        }
        
  
        
        
    
    }
    
}
