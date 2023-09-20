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
import snp.gob.bo.gimodel.entidad.Pagina;
import snp.gob.bo.gimodel.mapper.PaginaMapper;
import snp.gob.bo.gimodel.servicio.PaginaService;

/**
 *
 * @author levi
 */
@Service("paginaService")
public class PaginaServiceImpl implements PaginaService{
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
    public List<Pagina> listaMenuXIdSistema(Long idmenu){
        String SQL="select * from pagina where estado='AC' and idpagina in( " +
                   "   select idpagina from menupagina where idmenu=?  " +
                    "    ) order by 1 asc";
      
        List<Pagina> listaPagina = jdbcTemplate.query(SQL, new PaginaMapper(),idmenu);
        if (!listaPagina.isEmpty()) {
       
            return listaPagina;
        }
        return Collections.EMPTY_LIST;
    
    
    }
    
     @Override
    public Pagina guardar_modificar_listar_Pagina(Pagina pagina, Integer operacion) {
         Pagina paginaNuevo = new Pagina();
        String SQL = "select * from crud_pagina(?,?,?,?,?,?);";
        paginaNuevo = (Pagina) jdbcTemplate.queryForObject(SQL, new PaginaMapper(),
                pagina.getIdpagina(),
                pagina.getIdlogtrans(),
                pagina.getDescripcion(),
                pagina.getUrl(),
                pagina.getEstado(),
                operacion
                );        
        return paginaNuevo;
        
    }
    
    
}
