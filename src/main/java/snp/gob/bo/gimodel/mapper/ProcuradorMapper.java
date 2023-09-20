/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import snp.gob.bo.gimodel.entidad.Procurador;

/**
 *
 * @author luisangel
 */
public class ProcuradorMapper implements RowMapper<Procurador> {

    @Override
    public Procurador mapRow(ResultSet rs, int i) throws SQLException {
        Procurador procurador = new Procurador();
        procurador.setIdprocurador(rs.getLong("idprocurador"));
        procurador.setIdlogtrans(rs.getLong("idlogtrans"));
        procurador.setNombre_razonsocial(rs.getString("nombre_razonsocial"));
        procurador.setPrimer_apellido(rs.getString("primer_apellido"));
        procurador.setSegundo_apellido(rs.getString("segundo_apellido"));
        procurador.setTipo_titular(rs.getString("tipo_titular"));
        procurador.setNumero_documento(rs.getString("numero_documento"));
        procurador.setTipo_documento(rs.getString("tipo_documento"));
        procurador.setLugar_expedicion(rs.getString("lugar_expedicion"));
        procurador.setTelefono(rs.getString("telefono"));
        procurador.setCelular(rs.getString("celular"));
        procurador.setTipodocumentoautorizacion(rs.getString("tipodocumentoautorizacion"));
        procurador.setDescripciondocumentoautorizacion(rs.getString("descripciondocumentoautorizacion"));
        procurador.setEstado(rs.getString("estado"));

        return procurador;

    }

}
