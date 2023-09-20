/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package snp.gob.bo.gimodel.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import snp.gob.bo.gimodel.entidad.Deposito;
import snp.gob.bo.gimodel.entidad.Dosificacion;


/**
 *
 * @author Chano Rojas
 */
public class DosificacionMapper implements RowMapper<Dosificacion> {
    @Override
    public Dosificacion mapRow(ResultSet r, int i) throws SQLException {
        Dosificacion dosificacion = new Dosificacion();
        dosificacion.setIdDosificacion(r.getLong("iddosificacion"));
        dosificacion.setIdLogTrans(r.getLong("idlogtrans"));
        dosificacion.setValorInicio(r.getInt("valorinicio"));
        dosificacion.setValorFinal(r.getInt("valorfinal"));
        dosificacion.setSiguiente(r.getInt("siguiente"));
        dosificacion.setGestion(r.getInt("gestion"));
        dosificacion.setFechaDosificacion(r.getTimestamp("fecha_dosificacion"));
        dosificacion.setEstado(r.getString("estado"));
        return dosificacion;
    }


}
