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
import snp.gob.bo.gimodel.entidad.UsuarioEtapa;
import snp.gob.bo.gimodel.mapper.UsuarioEtapaMapper;
import snp.gob.bo.gimodel.servicio.UsuarioEtapaService;

/**
 *
 * @author Ruben Ramirez
 * @see UsuarioEtapaService
 * @version 1.0, 26/10/2016
 */
@Service("usuarioEtapaService")
public class UsuarioEtapaServiceImpl implements UsuarioEtapaService {

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
    public UsuarioEtapa crudUsuarioEtapa(UsuarioEtapa usuarioEtapa, int parametro) throws Exception {
        try {
            String SQL = "select * from crud_usuarioetapa(?,?,?,?,?,?)";
            
            List<UsuarioEtapa> listaUsuarioEtapa = jdbcTemplate.query(SQL,
                    new UsuarioEtapaMapper(),
                    usuarioEtapa.getIdUsuarioEtapa(),
                    usuarioEtapa.getIdLogTrans(),
                    usuarioEtapa.getIdUsuario(),
                    usuarioEtapa.getIdEtapa(),
                    usuarioEtapa.getEstado(),
                    parametro
                    );
            
            //en caso que se encuentre un registro devolver el primer registro
            if (!listaUsuarioEtapa.isEmpty()) {
                return listaUsuarioEtapa.get(0);
            }
            //en caso que no se pudo insertar devolver un objeto UsuarioEtapa NULL
            return new UsuarioEtapa();
            
        } catch (Exception e) {
            throw e;
        }
    }
     @Override
    public List<UsuarioEtapa> listaUsuarioEtapaXIdUsuario(Long idUsuario)throws Exception {
        
            String SQL = "select * from lista_usuarioetapa_idusuario(?)";
            
            List<UsuarioEtapa> listaUsuarioEtapa = jdbcTemplate.query(SQL,new UsuarioEtapaMapper(),idUsuario);
            
            if (!listaUsuarioEtapa.isEmpty()) {
                return listaUsuarioEtapa;
            }
            
            return null;      
           
    }
    
    
     @Override
    public void modificaEstadoUsuario(Long idusuario,Long idetapa) throws Exception {
        
        
        String SQL = "update usuarioetapa set estado='NA' where idusuario=? and idetapa=?";
            
           jdbcTemplate.update(SQL,idusuario,idetapa);
        
    }
    
    
    
    
}
