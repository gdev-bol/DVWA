/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.servicio.impl;

import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import snp.gob.bo.gimodel.entidad.TramiteEntrega;
import snp.gob.bo.gimodel.mapper.TramiteEntregaMapper;
import snp.gob.bo.gimodel.servicio.TramiteEntregaService;

/**
 *
 * @author luisangel
 */
@Service("tramiteEntregaService")
public class TramiteEntregaServiceImpl implements TramiteEntregaService {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Override
    public void setDataSource(DataSource dataSource) {
        try {
            this.dataSource = dataSource;
            this.jdbcTemplate = new JdbcTemplate(dataSource);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public TramiteEntrega guardar_modificar_listar_TramiteEntrega(TramiteEntrega tramiteentrega, Integer operacion) {

        TramiteEntrega tramiteEntregaNuevo = new TramiteEntrega();
        String SQL = "select * from crud_tramiteentrega(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
        tramiteEntregaNuevo = (TramiteEntrega) jdbcTemplate.queryForObject(SQL, new TramiteEntregaMapper(),
                tramiteentrega.getIdtramiteentrega(),
                tramiteentrega.getIdlogtrans(),
                tramiteentrega.getIdprocurador(),
                tramiteentrega.getIdtramite(),
                tramiteentrega.getTipo_tramite(),
                tramiteentrega.getNumerobloque(),
                tramiteentrega.getGestionbloque(),
                tramiteentrega.getSm(),
                tramiteentrega.getSigla(),
                tramiteentrega.getNumerotramite(),
                tramiteentrega.getGestion(),
                tramiteentrega.getExtension(),
                tramiteentrega.getNumero_registro(),
                tramiteentrega.getSerie_registro(),
                tramiteentrega.getNumero_publicacion(),
                tramiteentrega.getMarca(),
                tramiteentrega.getClase_niza(),
                tramiteentrega.getEstadoanteriortramite(),
                tramiteentrega.getUbicacionanterior(),
                tramiteentrega.getIdusuario(),
                tramiteentrega.getIdregionalentrega(),
                tramiteentrega.getFecha_entrega(),
                tramiteentrega.getEstado(), 
                operacion
        );
        return tramiteEntregaNuevo;

    }

}
