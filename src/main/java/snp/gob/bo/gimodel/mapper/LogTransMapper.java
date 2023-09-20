/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import snp.gob.bo.gimodel.entidad.Etapa;
import snp.gob.bo.gimodel.entidad.LogTrans;

/**
 *
 * @author Eddy Valero
 * @version 1.0, 22/11/2016
 * @see LogTrans
 */
public class LogTransMapper implements RowMapper<LogTrans>{

    @Override
    public LogTrans mapRow(ResultSet rs, int i) throws SQLException {
        LogTrans logTrans = new LogTrans();
        logTrans.setIdLogTrans(rs.getLong("idlogtrans"));
        logTrans.setIdUsuario(rs.getLong("idusuario"));
        logTrans.setFecha(rs.getTimestamp("fecha"));
        return logTrans;
    }
    
}
