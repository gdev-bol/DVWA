/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.servicio.impl;

import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import snp.gob.bo.gimodel.entidad.Usuario;
import snp.gob.bo.gimodel.mapper.UsuarioMapper;
import snp.gob.bo.gimodel.servicio.UsuarioService;

/**
 *
 * @author levi
 */
@Service("usuarioService")
public class UsuarioServiceImpl implements UsuarioService {

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
    public List<Usuario> listaUsuarioXidPagina(Long idusuario) {
        String SQL = "select * from lista_usuario_idusuario(?)";
        List<Usuario> listUsuario = null;
        listUsuario = (List<Usuario>) jdbcTemplate.query(SQL, new UsuarioMapper(),
                idusuario
        );
        return listUsuario;

    }

    @Override
    public List<Usuario> listaUsuario() {
        String SQL = "select * from lista_usuario()";
        List<Usuario> listUsuario = null;
        listUsuario = (List<Usuario>) jdbcTemplate.query(SQL, new UsuarioMapper());
        return listUsuario;

    }

    @Override
    public Usuario crudUsuario(Usuario usuarioEntrante, int parametro) throws Exception {

        String SQL = "select * from crud_usuario("
                + "?,"
                + "?,"
                + "?,"
                + "?,"
                + "?,"
                + "?,"
                + "?,"
                + "?,"
                + "?,"
                + "?,"
                + "?,"
                + "?,"
                + "?,"
                + "?,"
                + "?"
                + " );";

        Usuario usuario = (Usuario) jdbcTemplate.queryForObject(SQL, new UsuarioMapper(),
                usuarioEntrante.getIdusuario(),
                usuarioEntrante.getIdlogTrans(),
                usuarioEntrante.getNombre(),
                usuarioEntrante.getPrimer_apellido(),
                usuarioEntrante.getSegundo_apellido(),
                usuarioEntrante.getLogin(),
                usuarioEntrante.getContrasenia(),
                usuarioEntrante.getCargo(),
                usuarioEntrante.getIdregional(),
                usuarioEntrante.getNumero_documento(),
                usuarioEntrante.getTipo_documento(),
                usuarioEntrante.getLugar_expedicion(),
                usuarioEntrante.getCorreo_electronico(),
                usuarioEntrante.getEstado(),
                parametro
        );

        return usuario;
    }

    @Override
    public Usuario obtenerUsuario(Long idUsuario) throws Exception {
        try {
            String SQL = "select * from usuario where idusuario = ? and estado = 'AC'"
                    + "";

            Usuario usuario = (Usuario) jdbcTemplate.queryForObject(SQL,
                    new UsuarioMapper(),
                    idUsuario
            );

            return usuario;

        } catch (EmptyResultDataAccessException e) {
            //Como no se encontro ningun registro enviar la instancia de la nueva marca.
            return new Usuario();
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Usuario buscaUsuarioPorIdUsuario(Long idUsuario) throws Exception {
        try {
            String SQL = "select * from usuario where idusuario=?";
            if (jdbcTemplate.query(SQL, new UsuarioMapper(), idUsuario) != null) {
                return (Usuario) jdbcTemplate.query(SQL, new UsuarioMapper(), idUsuario).get(0);
            }
        } catch (Exception e) {
            throw e;
        }
        return new Usuario();
    }

    @Override
    public String listarNombreCompletoXIdUsuario(Long idusuario) {
        try {
            String SQL = "select string_agg(concat(nombre, ' ',primer_apellido, ' ',segundo_apellido), '; ') from usuario where idusuario = ? and estado = 'AC'"
                    + "";

            String nombre = jdbcTemplate.queryForObject(SQL, String.class, idusuario);
            return nombre;

        } catch (EmptyResultDataAccessException e) {
            //Como no se encontro ningun registro enviar la instancia de la nueva marca.
            return "";
        } catch (Exception e) {
            throw e;
            
        }
    }
    @Override
    public void cambiaContrasenia(String contrasenia, Long idusuario){
         String SQL = "select * from modifica_usuario_contrasenia('"+contrasenia+"',"+idusuario+");";//Modificado
           jdbcTemplate.execute(SQL);
        
        
           
    }
    

}
