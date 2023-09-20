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
import snp.gob.bo.gimodel.entidad.UsuarioTramite;
import snp.gob.bo.gimodel.mapper.UsuarioMapper;
import snp.gob.bo.gimodel.mapper.UsuarioTramiteMapper;
import snp.gob.bo.gimodel.servicio.UsuarioService;
import snp.gob.bo.gimodel.servicio.UsuarioTramiteService;

/**
 *
 * @author levi
 */
@Service("usuarioTramiteService")
public class UsuarioTramiteServiceImpl implements UsuarioTramiteService {

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
    public UsuarioTramite crudUsuarioTramite(UsuarioTramite usuarioTramite, Long parametro) throws Exception {
        try {

            String SQL = "select * from crud_usuario_tramite("
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?"
                    + ");";

            UsuarioTramite usuarioTramiteRegistro = (UsuarioTramite) jdbcTemplate.queryForObject(SQL, new UsuarioTramiteMapper(),
                    usuarioTramite.getIdUsuarioTramite(),
                    usuarioTramite.getTipoTramite(),
                    usuarioTramite.getIdUsuarioExterno(),
                    usuarioTramite.getId(),
                    usuarioTramite.getIdLogTrans(),
                    usuarioTramite.getEstado(),
                    parametro
            );
            
            return usuarioTramiteRegistro;

        } catch (Exception e) {
            throw e;
        }
    }

}
