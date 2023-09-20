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
import snp.gob.bo.gimodel.servicio.MenuPaginaService;
import org.springframework.stereotype.Service;
import snp.gob.bo.gimodel.entidad.MenuPagina;
import snp.gob.bo.gimodel.entidad.MenuPaginaUsuario;
import snp.gob.bo.gimodel.mapper.MenuPaginaMapper;
import snp.gob.bo.gimodel.mapper.MenuPaginaUsuarioMapper;

/**
 *
 * @author levi
 */
@Service("menuPaginaService")
public class MenuPaginaServiceImpl implements MenuPaginaService{
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
    public MenuPagina guardar_modificar_listar_Pagina(MenuPagina menuPagina, Integer operacion) {
         MenuPagina menuPaginaNuevo = new MenuPagina();
        String SQL = "select * from crud_menupagina(?,?,?,?,?,?,?);";
        menuPaginaNuevo = (MenuPagina) jdbcTemplate.queryForObject(SQL, new MenuPaginaMapper(),
                menuPagina.getIdmenupagina(),
                menuPagina.getIdmenu(),
                menuPagina.getIdpagina(),
                menuPagina.getIdlogtrans(),
                menuPagina.getOrden(),
                menuPagina.getEstado(),
                operacion
                );        
        return menuPaginaNuevo;
        
    }
    
    
     @Override
    public int cuentaMasUnoPagMenu(Long idMenu) {
        Integer num = 1;
        try {
            String SQL = "select * from obtiene_cuentamaxmasunomenupag_idmenu(?);";//Modifica
            //Integer numBloque =  jdbcTemplate.queryForObject(SQL, new Object[]{id_usuariosolicitante}, Integer.class);
            num = jdbcTemplate.queryForObject(SQL, new Object[]{idMenu}, Integer.class);
            //  System.out.println("num::"+num);         
            return num;
        } catch (EmptyResultDataAccessException e) {
            //Como no se encontro ningun registro enviar la instancia de la nueva marca.
            return num;
        } catch (Exception e) {
            throw e;
        }
    }
    
    
    @Override
    public List<MenuPaginaUsuario> obtieneMenusPaginas(Long idsistema) throws Exception{
         /*String SQL = "select u.nombre,r.descripcion,p.descripcion as submenu, p.url,up.acceso, up.fecha_vig_ini, up.fecha_vig_fin, up.estado \n"
                + "  from usuario u,usuariorol ur,rol r, rolpagina rp, pagina p, usuariopagina up , sistema s \n"
                + "  where u.idusuario=ur.idusuario and r.idrol=ur.idrol and rp.idrol=r.idrol and rp.idpagina=p.idpagina \n"
                + "   and u.idusuario=up.idusuario and up.idpagina=p.idpagina and up.muestra=true"
                + " and s.idsistema=ur.idsistema and s.idsistema='" + idSistema + "' and u.login ='" + login + "' and u.password='" + pass + "' order by r.orden asc";*/
        String SQL = "select s.descripcion as sistema,mpmm.idmenu,mpmm.menu,mpmm.idpagina,mpmm.pagina,mpmm.muestra,mpmm.estado from " +
"          (   select  mpm.idsistema,mpm.idmenu, " +
"                     mpm.descripcion as menu, " +
"                     p.idpagina , " +
"                     p.descripcion as pagina, " +
"                     'AC' as muestra,'AC' as estado  from " +
"                     (select * from pagina where estado='AC'  " +
"                     )  p join  " +
"                      (select m.idmenu,m.descripcion,mp.idpagina,m.idsistema from " +
"                        (select * from menu where estado='AC' and idsistema="+idsistema+")  m " +
"                         join " +
"                        (select * from menupagina where estado='AC') as mp " +
"                        on (m.idmenu=mp.idmenu) " +
"                        ) mpm " +
"                        on(p.idpagina=mpm.idpagina) " +
"             ) mpmm " +
"             join " +
"             ( " +
"              select * from sistema  " +
"             ) s" +
"             on(s.idsistema=mpmm.idsistema);"
                    ;
        List<MenuPaginaUsuario> list = jdbcTemplate.query(SQL, new MenuPaginaUsuarioMapper());
        if (!list.isEmpty()) {
            /*for(int i=0;i<=listaUsuarioRol.size()-1;i++)
            { System.out.println("En consulta estado"+listaUsuarioRol.get(i).getEstado());
            
            }*/
            return list;
        }
        return Collections.EMPTY_LIST;

    }
     @Override
    public List<MenuPaginaUsuario> listaMenusPaginasUsuario(String login, String contrasenia) throws Exception{
        
        
          String SQL = "select s.descripcion as sistema, m.idmenu,m.descripcion as menu,p.idpagina,p.descripcion as pagina,up.muestra,up.estado " +
                       "from usuario u,usuariomenu um,menu m, menupagina mp, pagina p, usuariopagina up , sistema s " +
                       "where u.idusuario=um.idusuario and m.idmenu=um.idmenu and mp.idmenu=m.idmenu and mp.idpagina=p.idpagina  " +
                       "and u.idusuario=up.idusuario and up.idpagina=p.idpagina  and p.estado='AC' and s.estado='AC'  " +
                       "and s.idsistema=m.idsistema  and u.login ='"+login+"' and u.contrasenia='"+contrasenia+"'  " +
                       "order by s.idsistema,  m.orden asc,mp.orden asc;";
        List<MenuPaginaUsuario> list = jdbcTemplate.query(SQL, new MenuPaginaUsuarioMapper());
        if (!list.isEmpty()) {
            /*for(int i=0;i<=listaUsuarioRol.size()-1;i++)
            { System.out.println("En consulta estado"+listaUsuarioRol.get(i).getEstado());
            
            }*/
            return list;
        }
        return Collections.EMPTY_LIST;
        
    }
}
