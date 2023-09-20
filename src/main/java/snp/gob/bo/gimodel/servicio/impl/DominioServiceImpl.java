/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.servicio.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import snp.gob.bo.gimodel.entidad.Dominio;
import snp.gob.bo.gimodel.mapper.DominioMapper;
import snp.gob.bo.gimodel.servicio.DominioService;

/**
 *
 * @author Eddy Valero
 * @see Dominio
 * @see SMDominioService
 * @version 1.0, 05/06/2016
 */
@Service("dominioService")
public class DominioServiceImpl implements DominioService {

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
    public List<Dominio> obtenerListadoDominio(String dominio) throws Exception {
        try {
            String SQL = "select * from obt_lista_dominio(?);";
            List<Dominio> listaDominio = jdbcTemplate.query(SQL, new DominioMapper(), dominio);
            if (!listaDominio.isEmpty()) {
                return listaDominio;
            }
            return new ArrayList<Dominio>();
        } catch (Exception e) {
            throw e;
        }
    }
    
    @Override
    public List<Dominio> obtenerListadoTipoTramite(String dominio) throws Exception {
        try {
            System.out.println("dominio"+dominio);
            String SQL = "select * from dominio where dominio='"+dominio+"' order by codigo asc;";
            List<Dominio> listaDominio = jdbcTemplate.query(SQL, new DominioMapper());
            if (!listaDominio.isEmpty()) {
                return listaDominio;
            }
            return new ArrayList<Dominio>();
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<Dominio> obtenerListadoDominioXCodigo(String dominio, String codigo) {
        try {
            String SQL = "select * from obtiene_listadominiocodigo(?,?);";
            List<Dominio> listaDominio = jdbcTemplate.query(SQL, new DominioMapper(), dominio, codigo);
//        List<SMDominio> listaDominio = jdbcTemplate.query(

            if (!listaDominio.isEmpty()) {
                return listaDominio;
            }
            return Collections.EMPTY_LIST;
        } catch (EmptyResultDataAccessException e) {
            //Como no se encontro ningun registro enviar la instancia de la nueva marca.
            return Collections.EMPTY_LIST;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<Dominio> listar_dominio_dominiopadre(String dominio, String dominiopadre) throws Exception {
        try {
            String SQL = "select * from lista_dominio_dominiopadre(?,?);";
            List<Dominio> listaDominio = jdbcTemplate.query(SQL, new DominioMapper(), dominio, dominiopadre);
            if (!listaDominio.isEmpty()) {
                return listaDominio;
            }
            return Collections.EMPTY_LIST;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public String obtenerNombreCodigoDominio(String codigo, String dominio) throws Exception {
         String resultado="";
        try {                      
            String SQL = " select nombre from dominio "
                    + " where dominio = ? "
                    + " and codigo = ?"
                    + " and estado = 'AC' ";
            
            resultado = jdbcTemplate.queryForObject(SQL, String.class, codigo, dominio);
            return resultado;
        } catch (EmptyResultDataAccessException e) {
            //Como no se encontro ningun se devuelve cadena vacia.
            return resultado;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public String obtenerNombrePorCodigoDominio(String codigo, String dominio) throws Exception {
       
       
        try {
            String resultado;
            String SQL = " select nombre from dominio "
                    + " where codigo = ? "
                    + " and dominio = ?"
                    + " and estado = 'AC' ";
            
                        resultado = jdbcTemplate.queryForObject(SQL, String.class, codigo, dominio);
            return resultado;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<Dominio> ListaTipoTramiteReciboDominio(String dominio, String dominioPadre) throws Exception {
        try {
            String SQL = "select * from dominio where dominio=? and dominiopadre=? order by codigo";
            List<Dominio> listaDominio = jdbcTemplate.query(SQL, new DominioMapper(), dominio, dominioPadre);
            if (!listaDominio.isEmpty()) {
                return listaDominio;
            }
            return Collections.EMPTY_LIST;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public String obtenerCodigoDominioPorNombre(String dominio, String nombre) throws Exception {
        try {

            String resultado;
            String SQL = " select codigo from dominio "
                    + " where dominio = ? "
                    + " and nombre = ?"
                    + " and estado = 'AC' ";
            resultado = jdbcTemplate.queryForObject(SQL, String.class, dominio, nombre);
            return resultado;
        } catch (Exception e) {
            throw e;
        }
    }
    
    
    
    
    
//    @Override
//    public List<Dominio> obtenerListadoTipoTramite(String dominio) throws Exception {
//        try {
//            String SQL = "select * from dominio where dominio='"+dominio+"' order by codigo asc;";
//            List<Dominio> listaDominio = jdbcTemplate.query(SQL, new DominioMapper());
//            if (!listaDominio.isEmpty()) {
//                return listaDominio;
//            }
//            return new ArrayList<Dominio>();
//        } catch (Exception e) {
//            throw e;
//        }
//    }
    
}
