 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import snp.gob.bo.gimodel.entidad.ModTitularLicenciatarioRegistrado;

/**
 *
 * @author susana
 */
public class ModTitularLicenciatarioRegistradoMapper implements RowMapper<ModTitularLicenciatarioRegistrado> {

    @Override
    public ModTitularLicenciatarioRegistrado mapRow(ResultSet rs, int i) throws SQLException {
        ModTitularLicenciatarioRegistrado modtitularlicenciatarioregistrado = new ModTitularLicenciatarioRegistrado();
        modtitularlicenciatarioregistrado.setIdtitularlicenciatarioregistrado(rs.getLong("idtitularlicenciatarioregistrado"));
        modtitularlicenciatarioregistrado.setIdmodificacion(rs.getLong("idmodificacion"));
        modtitularlicenciatarioregistrado.setIdlogtrans(rs.getLong("idlogtrans"));        
        modtitularlicenciatarioregistrado.setTipo_persona_registrado(rs.getString("tipo_persona_registrado"));
        modtitularlicenciatarioregistrado.setTipo_titular(rs.getString("tipo_titular"));
        modtitularlicenciatarioregistrado.setNombre_razonsocial(rs.getString("nombre_razonsocial"));
        modtitularlicenciatarioregistrado.setPrimer_apellido(rs.getString("primer_apellido"));
        modtitularlicenciatarioregistrado.setSegundo_apellido(rs.getString("segundo_apellido"));
        modtitularlicenciatarioregistrado.setDireccion(rs.getString("direccion"));
        modtitularlicenciatarioregistrado.setEstado(rs.getString("estado"));        
        modtitularlicenciatarioregistrado.setIdSipi(rs.getLong("id_sipi"));
        return modtitularlicenciatarioregistrado;
    }

}
