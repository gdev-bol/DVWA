/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snp.gob.bo.gimodel.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import snp.gob.bo.gimodel.entidad.ModModificacion;

/**
 *
 * @author susana
 */
public class ModModificacionMapper implements RowMapper<ModModificacion> {

    @Override
    public ModModificacion mapRow(ResultSet rs, int i) throws SQLException {
        ModModificacion modmodificacion = new ModModificacion();
        modmodificacion.setIdmodificacion(rs.getLong("idmodificacion"));
        modmodificacion.setIdlogtrans(rs.getLong("idlogtrans"));
        modmodificacion.setSigla(rs.getString("sigla"));
        modmodificacion.setNumero(rs.getLong("numero"));
        modmodificacion.setGestion(rs.getInt("gestion"));
        modmodificacion.setSm(rs.getLong("sm"));
        modmodificacion.setFecha_ingreso(rs.getTimestamp("fecha_ingreso"));
        modmodificacion.setNro_formulario(rs.getString("nro_formulario"));
        modmodificacion.setOficina(rs.getString("oficina"));
        modmodificacion.setNumero_registro(rs.getLong("numero_registro"));
        modmodificacion.setSerie_registro(rs.getString("serie_registro"));
        modmodificacion.setNumero_renovacion(rs.getLong("numero_renovacion"));
        modmodificacion.setSerie_renovacion(rs.getString("serie_renovacion"));
        modmodificacion.setNumero_publicacion(rs.getLong("numero_publicacion"));
        modmodificacion.setSigno_registrado(rs.getString("signo_registrado"));
        modmodificacion.setIdclase_registrado(rs.getLong("idclase_registrado"));
        modmodificacion.setTipo_genero_registrado(rs.getString("tipo_genero_registrado"));        
        modmodificacion.setMarca_acomp(rs.getString("marca_acomp"));
        modmodificacion.setReg_lc_registrado(rs.getLong("reg_lc_registrado"));
        modmodificacion.setSerie_lc_registrado(rs.getString("serie_lc_registrado"));
        modmodificacion.setFecha_lc_registrado(rs.getTimestamp("fecha_lc_registrado"));
        modmodificacion.setNuevo_domicilio(rs.getString("nuevo_domicilio"));
        modmodificacion.setNueva_nacionalidad(rs.getString("nueva_nacionalidad"));
        modmodificacion.setNuevo_pais_constitucion(rs.getString("nuevo_pais_constitucion"));
        modmodificacion.setNuevo_departamento(rs.getString("nuevo_departamento"));
        modmodificacion.setLuinicio(rs.getTimestamp("luinicio"));
        modmodificacion.setLu_fin(rs.getTimestamp("lu_fin"));
        modmodificacion.setFecha_ultima_operacion(rs.getTimestamp("fecha_ultima_operacion"));
        modmodificacion.setUsuario(rs.getLong("usuario"));
        modmodificacion.setTitular_signo(rs.getString("titular_signo"));
        modmodificacion.setNacionalidad_signo(rs.getString("nacionalidad_signo"));
        modmodificacion.setDepartamento_signo(rs.getString("departamento_signo"));
        modmodificacion.setDomicilio_signo(rs.getString("domicilio_signo"));
        modmodificacion.setTelefono_signo(rs.getString("telefono_signo"));
        modmodificacion.setFax_signo(rs.getString("fax_signo"));
        modmodificacion.setEmail_signo(rs.getString("email_signo"));
        modmodificacion.setTipo_modificacion(rs.getString("tipo_modificacion"));
        modmodificacion.setEstado_modificacion(rs.getString("estado_modificacion"));
        modmodificacion.setUbicacion_modificacion(rs.getString("ubicacion_modificacion"));
        modmodificacion.setLista_producto(rs.getString("lista_producto"));
        modmodificacion.setEstado(rs.getString("estado"));
        modmodificacion.setNro_recibo(rs.getLong("nro_recibo"));
        modmodificacion.setSerie_recibo(rs.getString("serie_recibo"));
        return modmodificacion;
    }

}
