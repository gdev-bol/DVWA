/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package snp.gob.bo.gimodel.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import snp.gob.bo.gimodel.entidad.Tasa;

/**
 *
 * @author eddy
 */
public class TasaMapper implements RowMapper<Tasa> {

    @Override
    public Tasa mapRow(ResultSet r, int i) throws SQLException {
        Tasa tasa=new Tasa();
        tasa.setIdTasa(r.getLong("idtasa"));
        tasa.setIdLogTrans(r.getLong("idlogtrans"));
        tasa.setDescripcion(r.getString("descripcion"));
        tasa.setCosto(r.getBigDecimal("costo"));
        tasa.setDescuento(r.getBigDecimal("descuento"));
        tasa.setUnidad(r.getString("unidad"));
        tasa.setEstado(r.getString("estado"));
        tasa.setGestion(r.getInt("gestion"));
        tasa.setTipoTramite(r.getString("tipo_tramite"));
        return tasa;
    }
    
    
}
