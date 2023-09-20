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
import snp.gob.bo.gimodel.entidad.ElementoFormularioTramite;
import snp.gob.bo.gimodel.mapper.ElementoFormularioTramiteMapper;
import snp.gob.bo.gimodel.servicio.ElementoFormularioTramiteService;

/**
 *
 * @author Eddy Valero
 * @see ElementoFormularioTramite
 * @see ElementoFormularioTramiteServiceImpl
 * @version 1.0, 06/09/2016
 */
@Service("elementoFormularioTramiteService")
public class ElementoFormularioTramiteServiceImpl implements ElementoFormularioTramiteService {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    @Autowired
    public void setDataSource(DataSource dataSource) throws Exception {
        try {
            this.dataSource = dataSource;
            this.jdbcTemplate = new JdbcTemplate(dataSource);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<ElementoFormularioTramite> obtPlantillaVentanilla(String codigo) throws Exception {
        try {
            String SQL = "select * from listar_elementosventanilla_codigo(?);";
            List<ElementoFormularioTramite> listElementoFormularioTramite = jdbcTemplate.query(SQL, new ElementoFormularioTramiteMapper(), codigo);
            
            if (!listElementoFormularioTramite.isEmpty()) {
                return listElementoFormularioTramite;
            }
            return Collections.EMPTY_LIST;
        } catch (Exception e) {
            throw e;
            
        }
    }
    
    

}
