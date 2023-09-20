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
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import snp.gob.bo.gimodel.entidad.Documento;
import snp.gob.bo.gimodel.mapper.DocumentoMapper;
import snp.gob.bo.gimodel.servicio.DocumentoService;
import java.util.Collections;

/**
 *
 * @author susana
 */
@Service("documentoService")
public class DocumentoServiceImpl implements DocumentoService {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
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
    public Documento guardarDocumento(Documento documento) {
        Documento documentoNuevo = new Documento();
        String SQL = "select * from inserta_documento(?,?,?,?,?,?,?,?,?,?,?);";
        documentoNuevo = (Documento) jdbcTemplate.queryForObject(SQL, new DocumentoMapper(),
                documento.getIdarea(),
                documento.getIdtramite(),
                documento.getIdlogtrans(),
                documento.getNombre_archivo(),
                documento.getDescripcion(),
                documento.getNro_folios(),
                documento.getFecha_creacion(),
                documento.getTipo_documentacion(),
                documento.getTipo_archivo(),
                documento.getImagen(),
                documento.getEstado()
        );
        return documentoNuevo;
    }

    @Override
    public List<Documento> obtenerListadoDocumento() {
      String SQL = "select * from obtenerlistadoDocumento();";
        List<Documento> listaDocumento = jdbcTemplate.query(SQL, new DocumentoMapper());
        if (!listaDocumento.isEmpty()) {         
            return listaDocumento;
        }
        return new ArrayList<Documento>();
    }

   

}
