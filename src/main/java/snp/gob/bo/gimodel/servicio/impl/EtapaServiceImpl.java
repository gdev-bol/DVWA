/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.servicio.impl;

import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import snp.gob.bo.gimodel.entidad.Etapa;
import snp.gob.bo.gimodel.mapper.EtapaMapper;
import snp.gob.bo.gimodel.servicio.EtapaService;

/**
 *
 * @author Ruben Ramirez
 * @see EtapaService
 * @version 1.0, 26/10/2016
 */
@Service("etapaService")
public class EtapaServiceImpl implements EtapaService {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

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
    public List<Etapa> listadoPorIdUsuario(Long idusuario, Long idetapa) throws Exception {
        String SQL = "select * \n"
                + "from etapa e\n"
                + "where e.idetapa in\n"
                + "(select idetapa\n"
                + "from usuarioetapa ue, usuario u\n"
                + "where ue.idusuario = u.idusuario AND u.idusuario = ? and ue.idetapa=?);";
        List<Etapa> listaEtapa = jdbcTemplate.query(SQL, new EtapaMapper(), idusuario, idetapa);
        if (!listaEtapa.isEmpty()) {
            return listaEtapa;
        }
        return new ArrayList<Etapa>();
    }

    @Override
    public Etapa listar_etapa_tipoTramite_tipoEtapa(String tipoTramite, String tipoEtapa) {
        try {
          //  String SQL = "select * from etapa where tipo_tramite= '" + tipoTramite + "'\n"
            //          + "and tipo_etapa='" + tipoEtapa + "' and estado = 'AC'";
            String SQL = "select * from etapa where "
                    + " tipo_etapa='" + tipoEtapa + "' and estado = 'AC'";
            Etapa etapa = jdbcTemplate.queryForObject(SQL, new EtapaMapper());
            return etapa;
        } catch (EmptyResultDataAccessException e) {
            //Como no se encontro ningun registro enviar la instancia de la nueva marca.
            return null;
        } catch (Exception e) {
            throw e;
        }

    }

    @Override
    public List<Etapa> listaEtapaXIdUsuario(Long idusuario) throws Exception {
        String SQL = "select *\n"
                + "from etapa e\n"
                + "where e.idetapa in\n"
                + "(select idetapa\n"
                + "from usuarioetapa ue, usuario u\n"
                + "where ue.idusuario = u.idusuario AND ue.estado='AC' and  u.idusuario = ?);";
        List<Etapa> listaEtapa = jdbcTemplate.query(SQL, new EtapaMapper(), idusuario);
        if (!listaEtapa.isEmpty()) {
            return listaEtapa;
        }
        return new ArrayList<Etapa>();
    }

    @Override
    public Etapa etapaXIdEtapa(Long idetapa) throws Exception {
        try {
            String SQL = "select * from etapa where "
                    + " idetapa ='" + idetapa + "' and estado = 'AC'";
            Etapa etapa = jdbcTemplate.queryForObject(SQL, new EtapaMapper());
            return etapa;
        } catch (EmptyResultDataAccessException e) {
            return null;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<Etapa> listaEtapa() throws Exception {
        String SQL = "select * "
                + "from etapa where estado='AC' order by 1 asc";

        List<Etapa> listaEtapa = jdbcTemplate.query(SQL, new EtapaMapper());
        if (!listaEtapa.isEmpty()) {
            return listaEtapa;
        }
        return new ArrayList<Etapa>();
    }

    @Override
    public Etapa crudEtapa(Etapa etapa, int parametro) throws Exception {
        try {
            String SQL = "select * from crud_etapa(?,?,?,?,?,?,?,?,?)";

            List<Etapa> listaEtapa = jdbcTemplate.query(SQL,
                    new EtapaMapper(),
                    etapa.getIdEtapa(),
                    etapa.getIdLogTrans(),
                    etapa.getTipoTramite(),
                    etapa.getTipoEtapa(),
                    etapa.getDescripcion(),
                    etapa.getFechaCreacion(),
                    etapa.getPlazo(),
                    etapa.getEstado(),
                    parametro
            );

            //en caso que se encuentre un registro devolver el primer registro
            if (!listaEtapa.isEmpty()) {
                return listaEtapa.get(0);
            }
            //en caso que no se pudo insertar devolver un objeto UsuarioEtapa NULL
            return new Etapa();

        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Long idetapaXCodigo(String etapa) {
        try {
            String SQL = "select * from etapa where "
                    + " tipo_etapa ='" + etapa + "' and estado = 'AC'";
            Etapa mietapa = jdbcTemplate.queryForObject(SQL, new EtapaMapper());
            return mietapa.getIdEtapa();
        } catch (EmptyResultDataAccessException e) {
            return 0l;

        } catch (Exception e) {
            throw e;
        }
    }

}
