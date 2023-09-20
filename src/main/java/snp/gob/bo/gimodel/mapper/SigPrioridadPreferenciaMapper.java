/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import snp.gob.bo.gimodel.entidad.SigPrioridadPreferencia;

/**
 *
 * @author Eddy Valero
 * @version 1.0, 29/08/2016
 */
public class SigPrioridadPreferenciaMapper implements RowMapper<SigPrioridadPreferencia> {

    @Override
    public SigPrioridadPreferencia mapRow(ResultSet rs, int i) throws SQLException {
        
        SigPrioridadPreferencia sigPrioridadPreferencia = new SigPrioridadPreferencia();
        
        sigPrioridadPreferencia.setIdPrioridadPreferencia(rs.getLong("idprioridadpreferencia"));
        sigPrioridadPreferencia.setIdSignoMarca(rs.getLong("idsignomarca"));
        sigPrioridadPreferencia.setIdLogTrans(rs.getLong("idlogtrans"));
        sigPrioridadPreferencia.setTipoPrioridad(rs.getString("tipo_prioridad"));
        sigPrioridadPreferencia.setTipoInteres(rs.getString("tipo_interes"));
        sigPrioridadPreferencia.setNombreNumero(rs.getString("nombre_numero"));
        sigPrioridadPreferencia.setFecha(rs.getTimestamp("fecha"));
        sigPrioridadPreferencia.setLugar(rs.getString("lugar"));
        sigPrioridadPreferencia.setEstado(rs.getString("estado"));
        sigPrioridadPreferencia.setIdSipi(rs.getLong("id_sipi"));
        

        return sigPrioridadPreferencia;
    }

}
