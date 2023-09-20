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
import snp.gob.bo.gimodel.servicio.SistemaService;
import org.springframework.stereotype.Service;
import snp.gob.bo.gimodel.entidad.Menu;
import snp.gob.bo.gimodel.mapper.MenuMapper;
import snp.gob.bo.gimodel.servicio.MenuService;
/**
 *
 * @author levi
 */
@Service("menuService")
public class MenuServiceImpl implements MenuService{
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
    public Menu guardar_modificar_listar_Menu(Menu menu, Integer operacion) {
         Menu menuNuevo = new Menu();
        String SQL = "select * from crud_menu(?,?,?,?,?,?,?);";
        menuNuevo = (Menu) jdbcTemplate.queryForObject(SQL, new MenuMapper(),
                menu.getIdmenu(),
                menu.getIdsistema(),
                menu.getIdlogtrans(),
                menu.getDescripcion(),
                menu.getOrden(),    
                menu.getEstado(),
                
                operacion
                );        
        return menuNuevo;
        
    }
    
    
    @Override
    public List<Menu> listaMenuXIdSistema(Long idsistema){
        String SQL="select * from menu where estado='AC' and idsistema=? order by 1 asc";
      
        List<Menu> listaMenu = jdbcTemplate.query(SQL, new MenuMapper(),idsistema);
        if (!listaMenu.isEmpty()) {
       
            return listaMenu;
        }
        return Collections.EMPTY_LIST;
    
    
    }
     @Override
    public int cuentaMasUnoMenuSistema(Long idSistema) {
        Integer num = 1;
        try {
            String SQL = "select * from obtiene_cuentamaxmasunomenu_idsistema(?);";//Modifica
            //Integer numBloque =  jdbcTemplate.queryForObject(SQL, new Object[]{id_usuariosolicitante}, Integer.class);
            num = jdbcTemplate.queryForObject(SQL, new Object[]{idSistema}, Integer.class);
            //  System.out.println("num::"+num);         
            return num;
        } catch (EmptyResultDataAccessException e) {
            //Como no se encontro ningun registro enviar la instancia de la nueva marca.
            return num;
        } catch (Exception e) {
            throw e;
        }
    }
     
    //
     @Override
    public void eliminaPaginas(Long idmenu)
    {
    
      String SQL = "select * from elimina_pagina_idmenu("+idmenu+");";
             jdbcTemplate.execute(SQL           
                );     
    
                         
             
    }
     
     
}
