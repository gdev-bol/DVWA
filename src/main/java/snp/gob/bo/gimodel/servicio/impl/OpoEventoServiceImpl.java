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
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import snp.gob.bo.gimodel.entidad.OpoEvento;
import snp.gob.bo.gimodel.mapper.OpoEventoMapper;
import snp.gob.bo.gimodel.servicio.OpoEventoService;

/**
 *
 * @author Luis Angel Quispe Limachi
 */
@Service("opoEventoService")
public class OpoEventoServiceImpl implements OpoEventoService {

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
    public OpoEvento guardaOpoEvento(OpoEvento opoevento) {

        String SQL = "select * from inserta_opoevento(?,?,?,?,?,?,?,?,?);";

        OpoEvento objeven = jdbcTemplate.queryForObject(SQL, new OpoEventoMapper(),
                opoevento.getIdactividad(),
                opoevento.getIdoposicion(),
                opoevento.getIdlogtrans(),
                opoevento.getFecha(),
                opoevento.getObservacion(),
                opoevento.getUsuario(),
                opoevento.getOrden_o(),
                opoevento.getFecha_operacion(),
                opoevento.getEstado()
        );
        return objeven;

    }

    @Override
    public Long extraeridenento(Long idoposi) throws Exception {

        String SQL = "select max(idevento) from opoevento where idoposicion=?";

        return jdbcTemplate.queryForObject(SQL, Long.class, idoposi);
    }
    @Override
    public OpoEvento obtenereventoxidpublica(Long idpublica) throws Exception {
        OpoEvento notidmte=new OpoEvento();        
        try {
            String SQL = "select * from opoevento where idevento = (select max(idevento) from opoevento where idoposicion=? and estado='AC');";
              if(jdbcTemplate.queryForObject(SQL, new OpoEventoMapper(), idpublica)!=null){
                notidmte = jdbcTemplate.queryForObject(SQL, new OpoEventoMapper(), idpublica);
            }       
            return notidmte;
        } catch (EmptyResultDataAccessException e) {
            return new OpoEvento();
        }
        catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<OpoEvento> obtenerListadoeventoxidoposicion(Long nroopo) throws Exception {
        
        try {
            String SQL = "select * from opoevento where idoposicion=? and estado='AC' ORDER BY fecha ASC;";
            List<OpoEvento> listaEvento = jdbcTemplate.query(SQL, new OpoEventoMapper(), nroopo);
            if (!listaEvento.isEmpty()) {
                return listaEvento;
            }
            return Collections.EMPTY_LIST;
        } catch (Exception e) {
            throw e;
        } 
        
    }

    @Override
    public OpoEvento modificarOpoEvento(OpoEvento opoevento) throws Exception {
        
      String SQL = "select * from modifica_opoevento(?,?,?,?,?,?,?,?,?,?);";

        OpoEvento objeven = jdbcTemplate.queryForObject(SQL, new OpoEventoMapper(),
                opoevento.getIdevento(),
                opoevento.getIdactividad(),
                opoevento.getIdoposicion(),
                opoevento.getIdlogtrans(),
                opoevento.getFecha(),
                opoevento.getObservacion(),
                opoevento.getUsuario(),
                opoevento.getOrden_o(),
                opoevento.getFecha_operacion(),
                opoevento.getEstado()
        );
    
        return objeven;
    
    
    }

    @Override
    public void eliminarOpoEvento(Long opoevento) throws Exception {
        
        String SQL = "UPDATE opoevento SET estado = 'NA' WHERE idevento=?;";
          jdbcTemplate.query(SQL, new OpoEventoMapper(),opoevento);
        
       
    }

    @Override
    public Long encuentramaximoOpoEventoxnroopo(Long opoevento) throws Exception {
    try {
    String SQL = "select max(idevento) from opoevento where idoposicion=? and estado='AC';";
        if(opoevento!=0L){
            return jdbcTemplate.queryForObject(SQL, Long.class, opoevento);
        }
        return 0L;
        }
     catch (EmptyResultDataAccessException et) {
            return 0L;
     }         
    catch (Exception e) {
            throw e;
        }
    }
    
}
