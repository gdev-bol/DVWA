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
import snp.gob.bo.gimodel.entidad.Sticker;
import snp.gob.bo.gimodel.mapper.StickerMapper;
import snp.gob.bo.gimodel.servicio.StickerService;

/**
 *
 * @author Ruben Ramirez
 * @see StickerService
 * @version 1.0, 05/12/2016
 */
@Service("stickerService")
public class StickerServiceImpl implements StickerService {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Override
    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Sticker obtenerStickerXTipoTramiteYGestion(String tipoTramite, int gestion) throws Exception {
        String SQL = "select * from sticker where tipo_tramite = ? and gestion = ? and estado = 'AC';";

        List<Sticker> listaSticker = jdbcTemplate.query(SQL, new StickerMapper(), tipoTramite, gestion);

        if (!listaSticker.isEmpty()) {
            return listaSticker.get(0);
        }
        return null;
    }

    @Override
    public Sticker crudSticker(Sticker sticker, int parametro) throws Exception {
        try {
            String SQL = "select * from crud_sticker(?, ?, ?, ?, ?, ?, ?, ?, ?);";
            List<Sticker> listaSticker = jdbcTemplate.query(SQL,
                    new StickerMapper(),
                    sticker.getIdSticker(),
                    sticker.getIdLogTrans(),
                    sticker.getTipoTramite(),
                    sticker.getIncremento(),
                    sticker.getPrimerNumeroAsignado(),
                    sticker.getUltimoNumeroAsignado(),
                    sticker.getGestion(),
                    sticker.getEstado(),
                    parametro
            );
            if (!listaSticker.isEmpty()) {
                return listaSticker.get(0);
            }
            return new Sticker();
        } catch (Exception e) {
            throw e;
        }
    }

}
