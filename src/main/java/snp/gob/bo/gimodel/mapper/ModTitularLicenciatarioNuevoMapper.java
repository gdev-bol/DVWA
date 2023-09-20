/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import snp.gob.bo.gimodel.entidad.ModTitularLicenciatarioNuevo;

/**
 *
 * @author susana
 */
public class ModTitularLicenciatarioNuevoMapper implements RowMapper<ModTitularLicenciatarioNuevo>{
       
    @Override
    public ModTitularLicenciatarioNuevo mapRow(ResultSet rs, int i) throws SQLException {
        ModTitularLicenciatarioNuevo modtitularlicencitarionuevo = new ModTitularLicenciatarioNuevo();        
        modtitularlicencitarionuevo.setIdtitularlicenciatario(rs.getLong("idtitularlicenciatario"));
        modtitularlicencitarionuevo.setIdmodificacion(rs.getLong("idmodificacion"));
        modtitularlicencitarionuevo.setIdlogtrans(rs.getLong("idlogtrans"));
        modtitularlicencitarionuevo.setTipo_persona(rs.getString("tipo_persona"));
        modtitularlicencitarionuevo.setTipo_titular(rs.getString("tipo_titular"));       
        modtitularlicencitarionuevo.setNombre_razonsocial(rs.getString("nombre_razonsocial"));
        modtitularlicencitarionuevo.setPrimer_apellido(rs.getString("primer_apellido"));
        modtitularlicencitarionuevo.setSegundo_apellido(rs.getString("segundo_apellido"));
        modtitularlicencitarionuevo.setNumero_documento(rs.getString("numero_documento"));
        modtitularlicencitarionuevo.setTipo_documento(rs.getString("tipo_documento"));
        modtitularlicencitarionuevo.setLugar_expedicion(rs.getString("lugar_expedicion"));
        modtitularlicencitarionuevo.setPais(rs.getString("pais"));
        modtitularlicencitarionuevo.setPais_constitucion(rs.getString("pais_constitucion"));
        modtitularlicencitarionuevo.setGenero(rs.getString("genero"));
        modtitularlicencitarionuevo.setSolicitud_departamento(rs.getString("solicitud_departamento"));
        modtitularlicencitarionuevo.setDireccion(rs.getString("direccion"));
        modtitularlicencitarionuevo.setTelefono(rs.getString("telefono"));
        modtitularlicencitarionuevo.setCelular(rs.getString("celular"));
        modtitularlicencitarionuevo.setEmail(rs.getString("email"));
        modtitularlicencitarionuevo.setFax(rs.getString("fax"));        
        modtitularlicencitarionuevo.setEstado(rs.getString("estado"));
        modtitularlicencitarionuevo.setModificar(rs.getBoolean("modificar"));
        modtitularlicencitarionuevo.setId_referencia(rs.getLong("id_referencia"));
        modtitularlicencitarionuevo.setIdSipi(rs.getLong("id_sipi"));
        return modtitularlicencitarionuevo;
    }
    
}
