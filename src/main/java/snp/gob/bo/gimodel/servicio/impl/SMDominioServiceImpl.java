/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.servicio.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;
import snp.gob.bo.gimodel.entidad.ObservacionTramite;

import snp.gob.bo.gimodel.servicio.SMDominioService;

/**
 *
 * @author Eddy Valero
 * @see SMDominio
 * @see SMDominioService
 * @version 1.0, 05/06/2016
 */
@Service("smDominioService")
public class SMDominioServiceImpl implements SMDominioService {

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

//    @Override
//    public int registrarSMDominio(SMDominio sMDominio) {
//
//        String SQL = "select inserta_smdominio(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
//
//        System.out.println("ingresar metodo");
//
//        return this.jdbcTemplate.queryForObject(SQL, int.class,
//                sMDominio.getIddominio(),
//                sMDominio.getIdlogtrans(),
//                sMDominio.getDominio(),
//                sMDominio.getCodigo(),
//                sMDominio.getDominioPadre(),
//                sMDominio.getNombre(),
//                sMDominio.getDescripcion(),
//                sMDominio.getOrden(),
//                sMDominio.getFechaInicio(),
//                sMDominio.getFechaFin()
//        );
//
//    }
//
//
//
//    @Override
//    public SMDominio registrarDominio(SMDominio sMDominio) throws Exception {
//        
//        String SQL = "select * from inserta_smdominio3(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
//        
//        
//         SMDominio smDominio= (SMDominio) jdbcTemplate.queryForObject(SQL, new SMDominioMapper(), 
//                sMDominio.getIddominio(), 
//                sMDominio.getIdlogtrans(),
//                sMDominio.getDominio(),
//                sMDominio.getCodigo(),
//                sMDominio.getDominioPadre(),
//                sMDominio.getNombre(),
//                sMDominio.getDescripcion(),
//                sMDominio.getOrden(),
//                sMDominio.getFechaInicio(),
//                sMDominio.getFechaFin()
//                );
//        
//        
//        return smDominio;
//         //return Collections.EMPTY_LIST;
//        
//        
//        
//    }
//
//    @Override
//    public List<SMDominio> obtenerListaDominios() {
//        String sql = "select * from smdominio";
//        List<SMDominio> listado = jdbcTemplate.query(sql, new BeanPropertyRowMapper(SMDominio.class));
//        return listado;
//    }
//    @Override
//    public List<SMDominio> obtenerListadoDominio(String dominio) throws Exception {
//        try {
//            String SQL = "select * from obtenerlistadominio(?);";
//            List<SMDominio> listaDominio = jdbcTemplate.query(SQL, new SMDominioMapper(), dominio);
////        List<SMDominio> listaDominio = jdbcTemplate.query(
//
//            if (!listaDominio.isEmpty()) {
//                return listaDominio;
//            }
//            return Collections.EMPTY_LIST;
//        } catch (Exception e) {
//            throw e;
//        }
//
//    }

//    @Override
//    public List<SMDominio> obtenerListadopaices() throws Exception {
//       String SQL = "select nombre from smdominio where dominio='Pais'";
//        List<SMDominio> listaDominio = jdbcTemplate.query(SQL, new BeanPropertyRowMapper(SMDominio.class));
//      

//        
//        if (!listaDominio.isEmpty()) {
//            return listaDominio;
//        }
//        return Collections.EMPTY_LIST;
//    
//    }
    

}
