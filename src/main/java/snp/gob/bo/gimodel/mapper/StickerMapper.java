        /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package snp.gob.bo.gimodel.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import snp.gob.bo.gimodel.entidad.Sticker;

/**
 *
 * @author Ruben Ramirez
 * @version 1.0, 05/12/2016
 * 
 */
public class StickerMapper implements RowMapper<Sticker> {
    @Override
    public Sticker mapRow(ResultSet rs, int i) throws SQLException {
            Sticker sticker = new Sticker();
            sticker.setIdSticker(rs.getLong("idsticker"));
            sticker.setIdLogTrans(rs.getLong("idlogtrans"));
            sticker.setTipoTramite(rs.getString("tipo_tramite"));
            sticker.setIncremento(rs.getInt("incremento"));
            sticker.setPrimerNumeroAsignado(rs.getInt("primer_numero_asignado"));
            sticker.setUltimoNumeroAsignado(rs.getInt("ultimo_numero_asignado"));
            sticker.setGestion(rs.getInt("gestion"));
            sticker.setEstado(rs.getString("estado"));
        return sticker;
    }
}
