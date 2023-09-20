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
import snp.gob.bo.gimodel.entidad.UsuarioPagina;
import snp.gob.bo.gimodel.mapper.UsuarioPaginaMapper;
import snp.gob.bo.gimodel.servicio.UsuarioPaginaService;

/**
 *
 * @author levi
 */
@Service("usuarioPaginaService")
public class UsuarioPaginaServiceImpl implements UsuarioPaginaService{
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
     public List<UsuarioPagina>  listaUsuarioPagina(){
        String SQL = "select * from usuariopagina";
        List<UsuarioPagina> listaUsuarioPagina = jdbcTemplate.query(SQL, new UsuarioPaginaMapper());
        if (!listaUsuarioPagina.isEmpty()) {
           /* for(int i=0;i<=listaUsuarioPagina.size()-1;i++)
            { System.out.println("En consulta estado"+listaUsuarioPagina.get(i).getIdUsuarioPagina());
            
            }*/
            return listaUsuarioPagina;
        }
        return Collections.EMPTY_LIST;
     }
     
      @Override
     public Boolean  estadoBotonUsuario(String idUsuario, String descripcion){
      String resul= null;
         String SQL = "select * from usuariopagina where idusuario='"+idUsuario+"' and estado= 'AC' and idpagina in " +
         "( select idpagina from pagina where descripcion ='"+descripcion+"' " +
         ")";
       //List<UsuarioPagina> listaUsuarioPagina = jdbcTemplate.query(SQL, new UsuarioPaginaMapper());
                                            
      List<UsuarioPagina> listaUsuarioPagina =jdbcTemplate.query(SQL, new UsuarioPaginaMapper());
      if(listaUsuarioPagina.size() ==0)
      {  System.out.println("0");
          return false;
          
      }     
      resul=listaUsuarioPagina.get(0).getEstado();
      if(resul== null || resul.equals(""))
      {  
         return false;
      }
      else{
         if(resul.equals("AC"))
         {  
            return false;
         }    
         else{
            
             return true;
         }
      }
     }
           /*Este metodo es simplemente  de prueba para los Reportes. Agarra el campo 'habilitado' (sin uso) de la tabla usuariopagian y en ese campo se guardo en
      su primer registro formato RTF para que este metodo loa garre en un strin y este se refleje en el reporte*/
     
      @Override
     public String  obtieneHabalitado(){
         String hab="";
        String SQL = "select * from usuariopagina";
          List<UsuarioPagina> listaUsuarioPagina = jdbcTemplate.query(SQL, new UsuarioPaginaMapper());
          for(int i=0;i<=listaUsuarioPagina.size()-1;i++)
          { 
                if(listaUsuarioPagina.get(i).getHabilitado() != null){
                    hab=listaUsuarioPagina.get(i).getHabilitado() ;
                }
             
          }
          System.out.println("hab::"+hab);
          return hab;
          
       
     }
     
     @Override
    public UsuarioPagina crudUsuarioPagina(UsuarioPagina usuarioPagina, int parametro) throws Exception {
        try {
            String SQL = "select * from crud_usuariopagina(?,?,?,?,?,?,?,?,?,?,?,?,?)";
            
            List<UsuarioPagina> listaUsuarioPagina = jdbcTemplate.query(SQL,
                    new UsuarioPaginaMapper(),
                    usuarioPagina.getIdUsuarioPagina(),
                    usuarioPagina.getIdUsuario(),
                    usuarioPagina.getIdPagina(),
                    usuarioPagina.getIdLogTrans(),
                    usuarioPagina.getAcceso(),
                    usuarioPagina.getHabilitado(),
                    usuarioPagina.getFecha_vig_ini(),
                    usuarioPagina.getFecha_vig_fin(),
                    usuarioPagina.getHora_vig_ini(),
                    usuarioPagina.getHora_vig_fin(),
                    usuarioPagina.getMuestra(),
                    usuarioPagina.getEstado(),
                    parametro
                         
                    );
            
            //en caso que se encuentre un registro devolver el primer registro
            if (!listaUsuarioPagina.isEmpty()) {
                return listaUsuarioPagina.get(0);
            }
            //en caso que no se pudo insertar devolver un objeto UsuarioEtapa NULL
            return new UsuarioPagina();
            
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void quitaEstadoUsuPagina(Long idusuario,Long idpagina) throws Exception {
        
        
        String SQL = "delete from usuariopagina where idusuario=? and idpagina=?";
            
           jdbcTemplate.update(SQL,idusuario,idpagina);
        
    }
    
    @Override
    public boolean verificaExistePagina(Long idusuario, Long idpagina) throws Exception {
         String SQL = "select * from  usuariopagina where idusuario=? and idpagina=?";
            
           List<UsuarioPagina> listaUsuarioPagina = jdbcTemplate.query(SQL, new UsuarioPaginaMapper(),idusuario,idpagina);
           
           if (!listaUsuarioPagina.isEmpty()) {
                
               return true;
            }
            //en caso que no se pudo insertar devolver un objeto UsuarioEtapa NULL
            return false;
            
    }
    @Override
    public void modificaUsuairoPagEstadoMues(boolean muestra, String estado, Long idpagina, Long idusuario) throws Exception {
    
    
        String SQL = "update usuariopagina set muestra=?, estado=?  where idpagina=? and idusuario=?";
                         jdbcTemplate.update(SQL,muestra,estado,idpagina,idusuario);
    }
    
}
