/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.servicio.impl;

import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import snp.gob.bo.gimodel.entidad.UsuarioMenu;
import snp.gob.bo.gimodel.mapper.UsuarioMenuMapper;
import snp.gob.bo.gimodel.servicio.UsuarioMenuService;
/**
 *
 * @author levi
 */

@Service("usuarioEtapaService")
public class UsuarioMenuServiceImpl implements UsuarioMenuService{
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Override
    @Autowired
    public void setDataSource(DataSource dataSource) throws Exception {
        try{
            this.dataSource = dataSource;
            this.jdbcTemplate = new JdbcTemplate(dataSource);
        }catch(Exception e){
            throw e;
        }
    }

    
    
     @Override
    public UsuarioMenu crudUsuarioMenu(UsuarioMenu usuarioMenu, int parametro) throws Exception {
        try {
            String SQL = "select * from crud_usuariomenu(?,?,?,?,?)";
            
            List<UsuarioMenu> listaUsuarioMenu = jdbcTemplate.query(SQL,
                    new UsuarioMenuMapper(),
                    usuarioMenu.getIdusuariomenu(),
                    usuarioMenu.getIdusuario(),
                    usuarioMenu.getIdmenu(),
                    usuarioMenu.getIdlogtrans(),
                    parametro
                    
                    );
            
            //en caso que se encuentre un registro devolver el primer registro
            if (!listaUsuarioMenu.isEmpty()) {
                return listaUsuarioMenu.get(0);
            }
            //en caso que no se pudo insertar devolver un objeto UsuarioEtapa NULL
            return new UsuarioMenu();
            
        } catch (Exception e) {
            throw e;
        }
    }
    
    @Override
    public boolean verificaExisteMenu(Long idusuario, Long idmenu) throws Exception {
         String SQL = "select * from  usuariomenu where idusuario=? and idmenu=?";
            
           List<UsuarioMenu> listaUsuarioMenu = jdbcTemplate.query(SQL, new UsuarioMenuMapper(),idusuario,idmenu);
           
           if (!listaUsuarioMenu.isEmpty()) {
                return true;
            }
            //en caso que no se pudo insertar devolver un objeto UsuarioEtapa NULL
            return false;
            
           
           
    }
    
    
}
