/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import snp.gob.bo.gimodel.entidad.SigLemaComercial;

/**
 *
 * @author Eddy Valero
 * @version 1.0, 02/09/2016
 */
public class SigLemaComercialMapper implements RowMapper<SigLemaComercial> {

    @Override
    public SigLemaComercial mapRow(ResultSet rs, int i) throws SQLException {
        
        SigLemaComercial sigLemaComercial = new SigLemaComercial();
        
        sigLemaComercial.setIdLemaComercial(rs.getLong("idlemacomercial"));
        sigLemaComercial.setIdSignoMarca(rs.getLong("idsignomarca"));
        sigLemaComercial.setSignoPadre(rs.getString("signo_padre"));
        sigLemaComercial.setSmPadre(rs.getLong("sm_padre"));
        sigLemaComercial.setCodigoSmPadre(rs.getString("codigo_sm_padre"));
        sigLemaComercial.setNumeroRegistroPadre(rs.getLong("numero_registro_padre"));
        sigLemaComercial.setSerieRegistroPadre(rs.getString("serie_registro_padre"));
        sigLemaComercial.setVigencia(rs.getString("vigencia"));
        sigLemaComercial.setEstado(rs.getString("estado"));
        

        return sigLemaComercial;
    }

}
