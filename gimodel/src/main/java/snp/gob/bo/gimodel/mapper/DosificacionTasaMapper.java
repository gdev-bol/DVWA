/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package snp.gob.bo.gimodel.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import snp.gob.bo.gimodel.entidad.DosificacionTasa;


/**
 *
 * @author Chano Rojas
 */
public class DosificacionTasaMapper implements RowMapper<DosificacionTasa> {
    @Override
    public DosificacionTasa mapRow(ResultSet r, int i) throws SQLException {
        DosificacionTasa dosificacionTasa = new DosificacionTasa();
        dosificacionTasa.setIdDosificacionTasa(r.getLong("iddosificaciontasa"));
        dosificacionTasa.setIdTasa(r.getLong("idtasa"));
        dosificacionTasa.setIdDosificacion(r.getLong("iddosificacion"));
        dosificacionTasa.setIdRegional(r.getLong("idregional"));
        dosificacionTasa.setIdLogTrans(r.getLong("idlogtrans"));
        dosificacionTasa.setSerie(r.getString("serie"));
        dosificacionTasa.setTipoRecibo(r.getString("tiporecibo"));
        dosificacionTasa.setEstado(r.getString("estado"));
        return dosificacionTasa;
    }


}
